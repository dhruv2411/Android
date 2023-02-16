package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.upstream.Allocation;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public final class DefaultTrackOutput implements TrackOutput {
    private static final int INITIAL_SCRATCH_SIZE = 32;
    private static final int STATE_DISABLED = 2;
    private static final int STATE_ENABLED = 0;
    private static final int STATE_ENABLED_WRITING = 1;
    private final int allocationLength;
    private final Allocator allocator;
    private final LinkedBlockingDeque<Allocation> dataQueue = new LinkedBlockingDeque<>();
    private Format downstreamFormat;
    private final BufferExtrasHolder extrasHolder = new BufferExtrasHolder();
    private final InfoQueue infoQueue = new InfoQueue();
    private Allocation lastAllocation;
    private int lastAllocationOffset = this.allocationLength;
    private Format lastUnadjustedFormat;
    private boolean pendingFormatAdjustment;
    private boolean pendingSplice;
    private long sampleOffsetUs;
    private final ParsableByteArray scratch = new ParsableByteArray(32);
    private final AtomicInteger state = new AtomicInteger();
    private long totalBytesDropped;
    private long totalBytesWritten;
    private UpstreamFormatChangedListener upstreamFormatChangeListener;

    public interface UpstreamFormatChangedListener {
        void onUpstreamFormatChanged(Format format);
    }

    public DefaultTrackOutput(Allocator allocator2) {
        this.allocator = allocator2;
        this.allocationLength = allocator2.getIndividualAllocationLength();
    }

    public void reset(boolean z) {
        int andSet = this.state.getAndSet(z ? 0 : 2);
        clearSampleData();
        this.infoQueue.resetLargestParsedTimestamps();
        if (andSet == 2) {
            this.downstreamFormat = null;
        }
    }

    public void sourceId(int i) {
        this.infoQueue.sourceId(i);
    }

    public void splice() {
        this.pendingSplice = true;
    }

    public int getWriteIndex() {
        return this.infoQueue.getWriteIndex();
    }

    public void discardUpstreamSamples(int i) {
        this.totalBytesWritten = this.infoQueue.discardUpstreamSamples(i);
        dropUpstreamFrom(this.totalBytesWritten);
    }

    private void dropUpstreamFrom(long j) {
        int i = (int) (j - this.totalBytesDropped);
        int i2 = i / this.allocationLength;
        int i3 = i % this.allocationLength;
        int size = (this.dataQueue.size() - i2) - 1;
        if (i3 == 0) {
            size++;
        }
        for (int i4 = 0; i4 < size; i4++) {
            this.allocator.release(this.dataQueue.removeLast());
        }
        this.lastAllocation = this.dataQueue.peekLast();
        if (i3 == 0) {
            i3 = this.allocationLength;
        }
        this.lastAllocationOffset = i3;
    }

    public void disable() {
        if (this.state.getAndSet(2) == 0) {
            clearSampleData();
        }
    }

    public boolean isEmpty() {
        return this.infoQueue.isEmpty();
    }

    public int getReadIndex() {
        return this.infoQueue.getReadIndex();
    }

    public int peekSourceId() {
        return this.infoQueue.peekSourceId();
    }

    public Format getUpstreamFormat() {
        return this.infoQueue.getUpstreamFormat();
    }

    public long getLargestQueuedTimestampUs() {
        return this.infoQueue.getLargestQueuedTimestampUs();
    }

    public void skipAll() {
        long skipAll = this.infoQueue.skipAll();
        if (skipAll != -1) {
            dropDownstreamTo(skipAll);
        }
    }

    public boolean skipToKeyframeBefore(long j, boolean z) {
        long skipToKeyframeBefore = this.infoQueue.skipToKeyframeBefore(j, z);
        if (skipToKeyframeBefore == -1) {
            return false;
        }
        dropDownstreamTo(skipToKeyframeBefore);
        return true;
    }

    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z, boolean z2, long j) {
        switch (this.infoQueue.readData(formatHolder, decoderInputBuffer, z, z2, this.downstreamFormat, this.extrasHolder)) {
            case C.RESULT_FORMAT_READ:
                this.downstreamFormat = formatHolder.format;
                return -5;
            case -4:
                if (decoderInputBuffer.isEndOfStream()) {
                    return -4;
                }
                if (decoderInputBuffer.timeUs < j) {
                    decoderInputBuffer.addFlag(Integer.MIN_VALUE);
                }
                if (decoderInputBuffer.isEncrypted()) {
                    readEncryptionData(decoderInputBuffer, this.extrasHolder);
                }
                decoderInputBuffer.ensureSpaceForWrite(this.extrasHolder.size);
                readData(this.extrasHolder.offset, decoderInputBuffer.data, this.extrasHolder.size);
                dropDownstreamTo(this.extrasHolder.nextOffset);
                return -4;
            case -3:
                return -3;
            default:
                throw new IllegalStateException();
        }
    }

    private void readEncryptionData(DecoderInputBuffer decoderInputBuffer, BufferExtrasHolder bufferExtrasHolder) {
        long j;
        DecoderInputBuffer decoderInputBuffer2 = decoderInputBuffer;
        BufferExtrasHolder bufferExtrasHolder2 = bufferExtrasHolder;
        long j2 = bufferExtrasHolder2.offset;
        int i = 1;
        this.scratch.reset(1);
        readData(j2, this.scratch.data, 1);
        long j3 = j2 + 1;
        byte b = this.scratch.data[0];
        boolean z = (b & 128) != 0;
        byte b2 = b & Byte.MAX_VALUE;
        if (decoderInputBuffer2.cryptoInfo.iv == null) {
            decoderInputBuffer2.cryptoInfo.iv = new byte[16];
        }
        readData(j3, decoderInputBuffer2.cryptoInfo.iv, (int) b2);
        long j4 = j3 + ((long) b2);
        if (z) {
            this.scratch.reset(2);
            readData(j4, this.scratch.data, 2);
            j = j4 + 2;
            i = this.scratch.readUnsignedShort();
        } else {
            j = j4;
        }
        int i2 = i;
        int[] iArr = decoderInputBuffer2.cryptoInfo.numBytesOfClearData;
        if (iArr == null || iArr.length < i2) {
            iArr = new int[i2];
        }
        int[] iArr2 = iArr;
        int[] iArr3 = decoderInputBuffer2.cryptoInfo.numBytesOfEncryptedData;
        if (iArr3 == null || iArr3.length < i2) {
            iArr3 = new int[i2];
        }
        int[] iArr4 = iArr3;
        if (z) {
            int i3 = 6 * i2;
            this.scratch.reset(i3);
            readData(j, this.scratch.data, i3);
            long j5 = j + ((long) i3);
            this.scratch.setPosition(0);
            for (int i4 = 0; i4 < i2; i4++) {
                iArr2[i4] = this.scratch.readUnsignedShort();
                iArr4[i4] = this.scratch.readUnsignedIntToInt();
            }
            j = j5;
        } else {
            iArr2[0] = 0;
            iArr4[0] = bufferExtrasHolder2.size - ((int) (j - bufferExtrasHolder2.offset));
        }
        decoderInputBuffer2.cryptoInfo.set(i2, iArr2, iArr4, bufferExtrasHolder2.encryptionKeyId, decoderInputBuffer2.cryptoInfo.iv, 1);
        int i5 = (int) (j - bufferExtrasHolder2.offset);
        bufferExtrasHolder2.offset += (long) i5;
        bufferExtrasHolder2.size -= i5;
    }

    private void readData(long j, ByteBuffer byteBuffer, int i) {
        while (i > 0) {
            dropDownstreamTo(j);
            int i2 = (int) (j - this.totalBytesDropped);
            int min = Math.min(i, this.allocationLength - i2);
            Allocation peek = this.dataQueue.peek();
            byteBuffer.put(peek.data, peek.translateOffset(i2), min);
            i -= min;
            j += (long) min;
        }
    }

    private void readData(long j, byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            dropDownstreamTo(j);
            int i3 = (int) (j - this.totalBytesDropped);
            int min = Math.min(i - i2, this.allocationLength - i3);
            Allocation peek = this.dataQueue.peek();
            System.arraycopy(peek.data, peek.translateOffset(i3), bArr, i2, min);
            i2 += min;
            j += (long) min;
        }
    }

    private void dropDownstreamTo(long j) {
        int i = ((int) (j - this.totalBytesDropped)) / this.allocationLength;
        for (int i2 = 0; i2 < i; i2++) {
            this.allocator.release(this.dataQueue.remove());
            this.totalBytesDropped += (long) this.allocationLength;
        }
    }

    public void setUpstreamFormatChangeListener(UpstreamFormatChangedListener upstreamFormatChangedListener) {
        this.upstreamFormatChangeListener = upstreamFormatChangedListener;
    }

    public void setSampleOffsetUs(long j) {
        if (this.sampleOffsetUs != j) {
            this.sampleOffsetUs = j;
            this.pendingFormatAdjustment = true;
        }
    }

    public void format(Format format) {
        Format adjustedSampleFormat = getAdjustedSampleFormat(format, this.sampleOffsetUs);
        boolean format2 = this.infoQueue.format(adjustedSampleFormat);
        this.lastUnadjustedFormat = format;
        this.pendingFormatAdjustment = false;
        if (this.upstreamFormatChangeListener != null && format2) {
            this.upstreamFormatChangeListener.onUpstreamFormatChanged(adjustedSampleFormat);
        }
    }

    public int sampleData(ExtractorInput extractorInput, int i, boolean z) throws IOException, InterruptedException {
        if (!startWriteOperation()) {
            int skip = extractorInput.skip(i);
            if (skip != -1) {
                return skip;
            }
            if (z) {
                return -1;
            }
            throw new EOFException();
        }
        try {
            int read = extractorInput.read(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), prepareForAppend(i));
            if (read != -1) {
                this.lastAllocationOffset += read;
                this.totalBytesWritten += (long) read;
                endWriteOperation();
                return read;
            } else if (z) {
                return -1;
            } else {
                throw new EOFException();
            }
        } finally {
            endWriteOperation();
        }
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i) {
        if (!startWriteOperation()) {
            parsableByteArray.skipBytes(i);
            return;
        }
        while (i > 0) {
            int prepareForAppend = prepareForAppend(i);
            parsableByteArray.readBytes(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), prepareForAppend);
            this.lastAllocationOffset += prepareForAppend;
            this.totalBytesWritten += (long) prepareForAppend;
            i -= prepareForAppend;
        }
        endWriteOperation();
    }

    public void sampleMetadata(long j, int i, int i2, int i3, byte[] bArr) {
        long j2 = j;
        if (this.pendingFormatAdjustment) {
            format(this.lastUnadjustedFormat);
        }
        if (!startWriteOperation()) {
            this.infoQueue.commitSampleTimestamp(j2);
            return;
        }
        try {
            if (this.pendingSplice) {
                if ((i & 1) != 0) {
                    if (this.infoQueue.attemptSplice(j2)) {
                        this.pendingSplice = false;
                    }
                }
                endWriteOperation();
                return;
            }
            int i4 = i2;
            this.infoQueue.commitSample(j2 + this.sampleOffsetUs, i, (this.totalBytesWritten - ((long) i4)) - ((long) i3), i4, bArr);
            endWriteOperation();
        } catch (Throwable th) {
            Throwable th2 = th;
            endWriteOperation();
            throw th2;
        }
    }

    private boolean startWriteOperation() {
        return this.state.compareAndSet(0, 1);
    }

    private void endWriteOperation() {
        if (!this.state.compareAndSet(1, 0)) {
            clearSampleData();
        }
    }

    private void clearSampleData() {
        this.infoQueue.clearSampleData();
        this.allocator.release((Allocation[]) this.dataQueue.toArray(new Allocation[this.dataQueue.size()]));
        this.dataQueue.clear();
        this.allocator.trim();
        this.totalBytesDropped = 0;
        this.totalBytesWritten = 0;
        this.lastAllocation = null;
        this.lastAllocationOffset = this.allocationLength;
    }

    private int prepareForAppend(int i) {
        if (this.lastAllocationOffset == this.allocationLength) {
            this.lastAllocationOffset = 0;
            this.lastAllocation = this.allocator.allocate();
            this.dataQueue.add(this.lastAllocation);
        }
        return Math.min(i, this.allocationLength - this.lastAllocationOffset);
    }

    private static Format getAdjustedSampleFormat(Format format, long j) {
        if (format == null) {
            return null;
        }
        return (j == 0 || format.subsampleOffsetUs == Long.MAX_VALUE) ? format : format.copyWithSubsampleOffsetUs(format.subsampleOffsetUs + j);
    }

    private static final class InfoQueue {
        private static final int SAMPLE_CAPACITY_INCREMENT = 1000;
        private int absoluteReadIndex;
        private int capacity = 1000;
        private byte[][] encryptionKeys = new byte[this.capacity][];
        private int[] flags = new int[this.capacity];
        private Format[] formats = new Format[this.capacity];
        private long largestDequeuedTimestampUs = Long.MIN_VALUE;
        private long largestQueuedTimestampUs = Long.MIN_VALUE;
        private long[] offsets = new long[this.capacity];
        private int queueSize;
        private int relativeReadIndex;
        private int relativeWriteIndex;
        private int[] sizes = new int[this.capacity];
        private int[] sourceIds = new int[this.capacity];
        private long[] timesUs = new long[this.capacity];
        private Format upstreamFormat;
        private boolean upstreamFormatRequired = true;
        private boolean upstreamKeyframeRequired = true;
        private int upstreamSourceId;

        public void clearSampleData() {
            this.absoluteReadIndex = 0;
            this.relativeReadIndex = 0;
            this.relativeWriteIndex = 0;
            this.queueSize = 0;
            this.upstreamKeyframeRequired = true;
        }

        public void resetLargestParsedTimestamps() {
            this.largestDequeuedTimestampUs = Long.MIN_VALUE;
            this.largestQueuedTimestampUs = Long.MIN_VALUE;
        }

        public int getWriteIndex() {
            return this.absoluteReadIndex + this.queueSize;
        }

        public long discardUpstreamSamples(int i) {
            int writeIndex = getWriteIndex() - i;
            Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.queueSize);
            if (writeIndex != 0) {
                this.queueSize -= writeIndex;
                this.relativeWriteIndex = ((this.relativeWriteIndex + this.capacity) - writeIndex) % this.capacity;
                this.largestQueuedTimestampUs = Long.MIN_VALUE;
                for (int i2 = this.queueSize - 1; i2 >= 0; i2--) {
                    int i3 = (this.relativeReadIndex + i2) % this.capacity;
                    this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, this.timesUs[i3]);
                    if ((this.flags[i3] & 1) != 0) {
                        break;
                    }
                }
                return this.offsets[this.relativeWriteIndex];
            } else if (this.absoluteReadIndex == 0) {
                return 0;
            } else {
                int i4 = (this.relativeWriteIndex == 0 ? this.capacity : this.relativeWriteIndex) - 1;
                return this.offsets[i4] + ((long) this.sizes[i4]);
            }
        }

        public void sourceId(int i) {
            this.upstreamSourceId = i;
        }

        public int getReadIndex() {
            return this.absoluteReadIndex;
        }

        public int peekSourceId() {
            return this.queueSize == 0 ? this.upstreamSourceId : this.sourceIds[this.relativeReadIndex];
        }

        public synchronized boolean isEmpty() {
            return this.queueSize == 0;
        }

        public synchronized Format getUpstreamFormat() {
            return this.upstreamFormatRequired ? null : this.upstreamFormat;
        }

        public synchronized long getLargestQueuedTimestampUs() {
            return Math.max(this.largestDequeuedTimestampUs, this.largestQueuedTimestampUs);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
            return -3;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized int readData(com.google.android.exoplayer2.FormatHolder r5, com.google.android.exoplayer2.decoder.DecoderInputBuffer r6, boolean r7, boolean r8, com.google.android.exoplayer2.Format r9, com.google.android.exoplayer2.extractor.DefaultTrackOutput.BufferExtrasHolder r10) {
            /*
                r4 = this;
                monitor-enter(r4)
                int r0 = r4.queueSize     // Catch:{ all -> 0x00a4 }
                r1 = -3
                r2 = -5
                r3 = -4
                if (r0 != 0) goto L_0x0022
                if (r8 == 0) goto L_0x0010
                r5 = 4
                r6.setFlags(r5)     // Catch:{ all -> 0x00a4 }
                monitor-exit(r4)
                return r3
            L_0x0010:
                com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x00a4 }
                if (r6 == 0) goto L_0x0020
                if (r7 != 0) goto L_0x001a
                com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x00a4 }
                if (r6 == r9) goto L_0x0020
            L_0x001a:
                com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x00a4 }
                r5.format = r6     // Catch:{ all -> 0x00a4 }
                monitor-exit(r4)
                return r2
            L_0x0020:
                monitor-exit(r4)
                return r1
            L_0x0022:
                if (r7 != 0) goto L_0x009a
                com.google.android.exoplayer2.Format[] r7 = r4.formats     // Catch:{ all -> 0x00a4 }
                int r8 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                r7 = r7[r8]     // Catch:{ all -> 0x00a4 }
                if (r7 == r9) goto L_0x002d
                goto L_0x009a
            L_0x002d:
                boolean r5 = r6.isFlagsOnly()     // Catch:{ all -> 0x00a4 }
                if (r5 == 0) goto L_0x0035
                monitor-exit(r4)
                return r1
            L_0x0035:
                long[] r5 = r4.timesUs     // Catch:{ all -> 0x00a4 }
                int r7 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                r7 = r5[r7]     // Catch:{ all -> 0x00a4 }
                r6.timeUs = r7     // Catch:{ all -> 0x00a4 }
                int[] r5 = r4.flags     // Catch:{ all -> 0x00a4 }
                int r7 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                r5 = r5[r7]     // Catch:{ all -> 0x00a4 }
                r6.setFlags(r5)     // Catch:{ all -> 0x00a4 }
                int[] r5 = r4.sizes     // Catch:{ all -> 0x00a4 }
                int r7 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                r5 = r5[r7]     // Catch:{ all -> 0x00a4 }
                r10.size = r5     // Catch:{ all -> 0x00a4 }
                long[] r5 = r4.offsets     // Catch:{ all -> 0x00a4 }
                int r7 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                r7 = r5[r7]     // Catch:{ all -> 0x00a4 }
                r10.offset = r7     // Catch:{ all -> 0x00a4 }
                byte[][] r5 = r4.encryptionKeys     // Catch:{ all -> 0x00a4 }
                int r7 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                r5 = r5[r7]     // Catch:{ all -> 0x00a4 }
                r10.encryptionKeyId = r5     // Catch:{ all -> 0x00a4 }
                long r7 = r4.largestDequeuedTimestampUs     // Catch:{ all -> 0x00a4 }
                long r5 = r6.timeUs     // Catch:{ all -> 0x00a4 }
                long r5 = java.lang.Math.max(r7, r5)     // Catch:{ all -> 0x00a4 }
                r4.largestDequeuedTimestampUs = r5     // Catch:{ all -> 0x00a4 }
                int r5 = r4.queueSize     // Catch:{ all -> 0x00a4 }
                int r5 = r5 + -1
                r4.queueSize = r5     // Catch:{ all -> 0x00a4 }
                int r5 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                int r5 = r5 + 1
                r4.relativeReadIndex = r5     // Catch:{ all -> 0x00a4 }
                int r5 = r4.absoluteReadIndex     // Catch:{ all -> 0x00a4 }
                int r5 = r5 + 1
                r4.absoluteReadIndex = r5     // Catch:{ all -> 0x00a4 }
                int r5 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                int r6 = r4.capacity     // Catch:{ all -> 0x00a4 }
                if (r5 != r6) goto L_0x0083
                r5 = 0
                r4.relativeReadIndex = r5     // Catch:{ all -> 0x00a4 }
            L_0x0083:
                int r5 = r4.queueSize     // Catch:{ all -> 0x00a4 }
                if (r5 <= 0) goto L_0x008e
                long[] r5 = r4.offsets     // Catch:{ all -> 0x00a4 }
                int r6 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                r6 = r5[r6]     // Catch:{ all -> 0x00a4 }
                goto L_0x0096
            L_0x008e:
                long r5 = r10.offset     // Catch:{ all -> 0x00a4 }
                int r7 = r10.size     // Catch:{ all -> 0x00a4 }
                long r7 = (long) r7     // Catch:{ all -> 0x00a4 }
                long r0 = r5 + r7
                r6 = r0
            L_0x0096:
                r10.nextOffset = r6     // Catch:{ all -> 0x00a4 }
                monitor-exit(r4)
                return r3
            L_0x009a:
                com.google.android.exoplayer2.Format[] r6 = r4.formats     // Catch:{ all -> 0x00a4 }
                int r7 = r4.relativeReadIndex     // Catch:{ all -> 0x00a4 }
                r6 = r6[r7]     // Catch:{ all -> 0x00a4 }
                r5.format = r6     // Catch:{ all -> 0x00a4 }
                monitor-exit(r4)
                return r2
            L_0x00a4:
                r5 = move-exception
                monitor-exit(r4)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.DefaultTrackOutput.InfoQueue.readData(com.google.android.exoplayer2.FormatHolder, com.google.android.exoplayer2.decoder.DecoderInputBuffer, boolean, boolean, com.google.android.exoplayer2.Format, com.google.android.exoplayer2.extractor.DefaultTrackOutput$BufferExtrasHolder):int");
        }

        public synchronized long skipAll() {
            if (this.queueSize == 0) {
                return -1;
            }
            int i = ((this.relativeReadIndex + this.queueSize) - 1) % this.capacity;
            this.relativeReadIndex = (this.relativeReadIndex + this.queueSize) % this.capacity;
            this.absoluteReadIndex += this.queueSize;
            this.queueSize = 0;
            return this.offsets[i] + ((long) this.sizes[i]);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:34:0x005f, code lost:
            return -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized long skipToKeyframeBefore(long r9, boolean r11) {
            /*
                r8 = this;
                monitor-enter(r8)
                int r0 = r8.queueSize     // Catch:{ all -> 0x0060 }
                r1 = -1
                if (r0 == 0) goto L_0x005e
                long[] r0 = r8.timesUs     // Catch:{ all -> 0x0060 }
                int r3 = r8.relativeReadIndex     // Catch:{ all -> 0x0060 }
                r3 = r0[r3]     // Catch:{ all -> 0x0060 }
                int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
                if (r0 >= 0) goto L_0x0012
                goto L_0x005e
            L_0x0012:
                long r3 = r8.largestQueuedTimestampUs     // Catch:{ all -> 0x0060 }
                int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
                if (r0 <= 0) goto L_0x001c
                if (r11 != 0) goto L_0x001c
                monitor-exit(r8)
                return r1
            L_0x001c:
                r11 = 0
                int r0 = r8.relativeReadIndex     // Catch:{ all -> 0x0060 }
                r3 = -1
                r4 = r11
                r11 = r3
            L_0x0022:
                int r5 = r8.relativeWriteIndex     // Catch:{ all -> 0x0060 }
                if (r0 == r5) goto L_0x0040
                long[] r5 = r8.timesUs     // Catch:{ all -> 0x0060 }
                r6 = r5[r0]     // Catch:{ all -> 0x0060 }
                int r5 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
                if (r5 <= 0) goto L_0x002f
                goto L_0x0040
            L_0x002f:
                int[] r5 = r8.flags     // Catch:{ all -> 0x0060 }
                r5 = r5[r0]     // Catch:{ all -> 0x0060 }
                r5 = r5 & 1
                if (r5 == 0) goto L_0x0038
                r11 = r4
            L_0x0038:
                int r0 = r0 + 1
                int r5 = r8.capacity     // Catch:{ all -> 0x0060 }
                int r0 = r0 % r5
                int r4 = r4 + 1
                goto L_0x0022
            L_0x0040:
                if (r11 != r3) goto L_0x0044
                monitor-exit(r8)
                return r1
            L_0x0044:
                int r9 = r8.relativeReadIndex     // Catch:{ all -> 0x0060 }
                int r9 = r9 + r11
                int r10 = r8.capacity     // Catch:{ all -> 0x0060 }
                int r9 = r9 % r10
                r8.relativeReadIndex = r9     // Catch:{ all -> 0x0060 }
                int r9 = r8.absoluteReadIndex     // Catch:{ all -> 0x0060 }
                int r9 = r9 + r11
                r8.absoluteReadIndex = r9     // Catch:{ all -> 0x0060 }
                int r9 = r8.queueSize     // Catch:{ all -> 0x0060 }
                int r9 = r9 - r11
                r8.queueSize = r9     // Catch:{ all -> 0x0060 }
                long[] r9 = r8.offsets     // Catch:{ all -> 0x0060 }
                int r10 = r8.relativeReadIndex     // Catch:{ all -> 0x0060 }
                r10 = r9[r10]     // Catch:{ all -> 0x0060 }
                monitor-exit(r8)
                return r10
            L_0x005e:
                monitor-exit(r8)
                return r1
            L_0x0060:
                r9 = move-exception
                monitor-exit(r8)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.DefaultTrackOutput.InfoQueue.skipToKeyframeBefore(long, boolean):long");
        }

        public synchronized boolean format(Format format) {
            if (format == null) {
                this.upstreamFormatRequired = true;
                return false;
            }
            this.upstreamFormatRequired = false;
            if (Util.areEqual(format, this.upstreamFormat)) {
                return false;
            }
            this.upstreamFormat = format;
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x00e9, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void commitSample(long r6, int r8, long r9, int r11, byte[] r12) {
            /*
                r5 = this;
                monitor-enter(r5)
                boolean r0 = r5.upstreamKeyframeRequired     // Catch:{ all -> 0x00ea }
                r1 = 0
                if (r0 == 0) goto L_0x000e
                r0 = r8 & 1
                if (r0 != 0) goto L_0x000c
                monitor-exit(r5)
                return
            L_0x000c:
                r5.upstreamKeyframeRequired = r1     // Catch:{ all -> 0x00ea }
            L_0x000e:
                boolean r0 = r5.upstreamFormatRequired     // Catch:{ all -> 0x00ea }
                r0 = r0 ^ 1
                com.google.android.exoplayer2.util.Assertions.checkState(r0)     // Catch:{ all -> 0x00ea }
                r5.commitSampleTimestamp(r6)     // Catch:{ all -> 0x00ea }
                long[] r0 = r5.timesUs     // Catch:{ all -> 0x00ea }
                int r2 = r5.relativeWriteIndex     // Catch:{ all -> 0x00ea }
                r0[r2] = r6     // Catch:{ all -> 0x00ea }
                long[] r6 = r5.offsets     // Catch:{ all -> 0x00ea }
                int r7 = r5.relativeWriteIndex     // Catch:{ all -> 0x00ea }
                r6[r7] = r9     // Catch:{ all -> 0x00ea }
                int[] r6 = r5.sizes     // Catch:{ all -> 0x00ea }
                int r7 = r5.relativeWriteIndex     // Catch:{ all -> 0x00ea }
                r6[r7] = r11     // Catch:{ all -> 0x00ea }
                int[] r6 = r5.flags     // Catch:{ all -> 0x00ea }
                int r7 = r5.relativeWriteIndex     // Catch:{ all -> 0x00ea }
                r6[r7] = r8     // Catch:{ all -> 0x00ea }
                byte[][] r6 = r5.encryptionKeys     // Catch:{ all -> 0x00ea }
                int r7 = r5.relativeWriteIndex     // Catch:{ all -> 0x00ea }
                r6[r7] = r12     // Catch:{ all -> 0x00ea }
                com.google.android.exoplayer2.Format[] r6 = r5.formats     // Catch:{ all -> 0x00ea }
                int r7 = r5.relativeWriteIndex     // Catch:{ all -> 0x00ea }
                com.google.android.exoplayer2.Format r8 = r5.upstreamFormat     // Catch:{ all -> 0x00ea }
                r6[r7] = r8     // Catch:{ all -> 0x00ea }
                int[] r6 = r5.sourceIds     // Catch:{ all -> 0x00ea }
                int r7 = r5.relativeWriteIndex     // Catch:{ all -> 0x00ea }
                int r8 = r5.upstreamSourceId     // Catch:{ all -> 0x00ea }
                r6[r7] = r8     // Catch:{ all -> 0x00ea }
                int r6 = r5.queueSize     // Catch:{ all -> 0x00ea }
                int r6 = r6 + 1
                r5.queueSize = r6     // Catch:{ all -> 0x00ea }
                int r6 = r5.queueSize     // Catch:{ all -> 0x00ea }
                int r7 = r5.capacity     // Catch:{ all -> 0x00ea }
                if (r6 != r7) goto L_0x00da
                int r6 = r5.capacity     // Catch:{ all -> 0x00ea }
                int r6 = r6 + 1000
                int[] r7 = new int[r6]     // Catch:{ all -> 0x00ea }
                long[] r8 = new long[r6]     // Catch:{ all -> 0x00ea }
                long[] r9 = new long[r6]     // Catch:{ all -> 0x00ea }
                int[] r10 = new int[r6]     // Catch:{ all -> 0x00ea }
                int[] r11 = new int[r6]     // Catch:{ all -> 0x00ea }
                byte[][] r12 = new byte[r6][]     // Catch:{ all -> 0x00ea }
                com.google.android.exoplayer2.Format[] r0 = new com.google.android.exoplayer2.Format[r6]     // Catch:{ all -> 0x00ea }
                int r2 = r5.capacity     // Catch:{ all -> 0x00ea }
                int r3 = r5.relativeReadIndex     // Catch:{ all -> 0x00ea }
                int r2 = r2 - r3
                long[] r3 = r5.offsets     // Catch:{ all -> 0x00ea }
                int r4 = r5.relativeReadIndex     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r3, r4, r8, r1, r2)     // Catch:{ all -> 0x00ea }
                long[] r3 = r5.timesUs     // Catch:{ all -> 0x00ea }
                int r4 = r5.relativeReadIndex     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r3, r4, r9, r1, r2)     // Catch:{ all -> 0x00ea }
                int[] r3 = r5.flags     // Catch:{ all -> 0x00ea }
                int r4 = r5.relativeReadIndex     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r3, r4, r10, r1, r2)     // Catch:{ all -> 0x00ea }
                int[] r3 = r5.sizes     // Catch:{ all -> 0x00ea }
                int r4 = r5.relativeReadIndex     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r3, r4, r11, r1, r2)     // Catch:{ all -> 0x00ea }
                byte[][] r3 = r5.encryptionKeys     // Catch:{ all -> 0x00ea }
                int r4 = r5.relativeReadIndex     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r3, r4, r12, r1, r2)     // Catch:{ all -> 0x00ea }
                com.google.android.exoplayer2.Format[] r3 = r5.formats     // Catch:{ all -> 0x00ea }
                int r4 = r5.relativeReadIndex     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r3, r4, r0, r1, r2)     // Catch:{ all -> 0x00ea }
                int[] r3 = r5.sourceIds     // Catch:{ all -> 0x00ea }
                int r4 = r5.relativeReadIndex     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r3, r4, r7, r1, r2)     // Catch:{ all -> 0x00ea }
                int r3 = r5.relativeReadIndex     // Catch:{ all -> 0x00ea }
                long[] r4 = r5.offsets     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r4, r1, r8, r2, r3)     // Catch:{ all -> 0x00ea }
                long[] r4 = r5.timesUs     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r4, r1, r9, r2, r3)     // Catch:{ all -> 0x00ea }
                int[] r4 = r5.flags     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r4, r1, r10, r2, r3)     // Catch:{ all -> 0x00ea }
                int[] r4 = r5.sizes     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r4, r1, r11, r2, r3)     // Catch:{ all -> 0x00ea }
                byte[][] r4 = r5.encryptionKeys     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r4, r1, r12, r2, r3)     // Catch:{ all -> 0x00ea }
                com.google.android.exoplayer2.Format[] r4 = r5.formats     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r4, r1, r0, r2, r3)     // Catch:{ all -> 0x00ea }
                int[] r4 = r5.sourceIds     // Catch:{ all -> 0x00ea }
                java.lang.System.arraycopy(r4, r1, r7, r2, r3)     // Catch:{ all -> 0x00ea }
                r5.offsets = r8     // Catch:{ all -> 0x00ea }
                r5.timesUs = r9     // Catch:{ all -> 0x00ea }
                r5.flags = r10     // Catch:{ all -> 0x00ea }
                r5.sizes = r11     // Catch:{ all -> 0x00ea }
                r5.encryptionKeys = r12     // Catch:{ all -> 0x00ea }
                r5.formats = r0     // Catch:{ all -> 0x00ea }
                r5.sourceIds = r7     // Catch:{ all -> 0x00ea }
                r5.relativeReadIndex = r1     // Catch:{ all -> 0x00ea }
                int r7 = r5.capacity     // Catch:{ all -> 0x00ea }
                r5.relativeWriteIndex = r7     // Catch:{ all -> 0x00ea }
                int r7 = r5.capacity     // Catch:{ all -> 0x00ea }
                r5.queueSize = r7     // Catch:{ all -> 0x00ea }
                r5.capacity = r6     // Catch:{ all -> 0x00ea }
                goto L_0x00e8
            L_0x00da:
                int r6 = r5.relativeWriteIndex     // Catch:{ all -> 0x00ea }
                int r6 = r6 + 1
                r5.relativeWriteIndex = r6     // Catch:{ all -> 0x00ea }
                int r6 = r5.relativeWriteIndex     // Catch:{ all -> 0x00ea }
                int r7 = r5.capacity     // Catch:{ all -> 0x00ea }
                if (r6 != r7) goto L_0x00e8
                r5.relativeWriteIndex = r1     // Catch:{ all -> 0x00ea }
            L_0x00e8:
                monitor-exit(r5)
                return
            L_0x00ea:
                r6 = move-exception
                monitor-exit(r5)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.DefaultTrackOutput.InfoQueue.commitSample(long, int, long, int, byte[]):void");
        }

        public synchronized void commitSampleTimestamp(long j) {
            this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, j);
        }

        public synchronized boolean attemptSplice(long j) {
            if (this.largestDequeuedTimestampUs >= j) {
                return false;
            }
            int i = this.queueSize;
            while (i > 0 && this.timesUs[((this.relativeReadIndex + i) - 1) % this.capacity] >= j) {
                i--;
            }
            discardUpstreamSamples(this.absoluteReadIndex + i);
            return true;
        }
    }

    private static final class BufferExtrasHolder {
        public byte[] encryptionKeyId;
        public long nextOffset;
        public long offset;
        public int size;

        private BufferExtrasHolder() {
        }
    }
}
