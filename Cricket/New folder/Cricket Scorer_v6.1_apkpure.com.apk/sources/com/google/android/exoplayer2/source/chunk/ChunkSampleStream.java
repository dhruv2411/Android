package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.DefaultTrackOutput;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.chunk.ChunkSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ChunkSampleStream<T extends ChunkSource> implements SampleStream, SequenceableLoader, Loader.Callback<Chunk> {
    private final SequenceableLoader.Callback<ChunkSampleStream<T>> callback;
    private final T chunkSource;
    private final DefaultTrackOutput[] embeddedSampleQueues;
    private final int[] embeddedTrackTypes;
    /* access modifiers changed from: private */
    public final boolean[] embeddedTracksSelected;
    private final AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher;
    long lastSeekPositionUs;
    private final Loader loader = new Loader("Loader:ChunkSampleStream");
    boolean loadingFinished;
    private final BaseMediaChunkOutput mediaChunkOutput;
    private final LinkedList<BaseMediaChunk> mediaChunks = new LinkedList<>();
    private final int minLoadableRetryCount;
    private final ChunkHolder nextChunkHolder = new ChunkHolder();
    private long pendingResetPositionUs;
    private Format primaryDownstreamTrackFormat;
    private final DefaultTrackOutput primarySampleQueue;
    private final int primaryTrackType;
    private final List<BaseMediaChunk> readOnlyMediaChunks = Collections.unmodifiableList(this.mediaChunks);

    public ChunkSampleStream(int i, int[] iArr, T t, SequenceableLoader.Callback<ChunkSampleStream<T>> callback2, Allocator allocator, long j, int i2, AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher2) {
        int i3;
        this.primaryTrackType = i;
        this.embeddedTrackTypes = iArr;
        this.chunkSource = t;
        this.callback = callback2;
        this.eventDispatcher = eventDispatcher2;
        this.minLoadableRetryCount = i2;
        int i4 = 0;
        if (iArr == null) {
            i3 = 0;
        } else {
            i3 = iArr.length;
        }
        this.embeddedSampleQueues = new DefaultTrackOutput[i3];
        this.embeddedTracksSelected = new boolean[i3];
        int i5 = 1 + i3;
        int[] iArr2 = new int[i5];
        DefaultTrackOutput[] defaultTrackOutputArr = new DefaultTrackOutput[i5];
        this.primarySampleQueue = new DefaultTrackOutput(allocator);
        iArr2[0] = i;
        defaultTrackOutputArr[0] = this.primarySampleQueue;
        while (i4 < i3) {
            DefaultTrackOutput defaultTrackOutput = new DefaultTrackOutput(allocator);
            this.embeddedSampleQueues[i4] = defaultTrackOutput;
            int i6 = i4 + 1;
            defaultTrackOutputArr[i6] = defaultTrackOutput;
            iArr2[i6] = iArr[i4];
            i4 = i6;
        }
        this.mediaChunkOutput = new BaseMediaChunkOutput(iArr2, defaultTrackOutputArr);
        this.pendingResetPositionUs = j;
        this.lastSeekPositionUs = j;
    }

    public void discardUnselectedEmbeddedTracksTo(long j) {
        for (int i = 0; i < this.embeddedSampleQueues.length; i++) {
            if (!this.embeddedTracksSelected[i]) {
                this.embeddedSampleQueues[i].skipToKeyframeBefore(j, true);
            }
        }
    }

    public ChunkSampleStream<T>.EmbeddedSampleStream selectEmbeddedTrack(long j, int i) {
        for (int i2 = 0; i2 < this.embeddedSampleQueues.length; i2++) {
            if (this.embeddedTrackTypes[i2] == i) {
                Assertions.checkState(!this.embeddedTracksSelected[i2]);
                this.embeddedTracksSelected[i2] = true;
                this.embeddedSampleQueues[i2].skipToKeyframeBefore(j, true);
                return new EmbeddedSampleStream(this, this.embeddedSampleQueues[i2], i2);
            }
        }
        throw new IllegalStateException();
    }

    public T getChunkSource() {
        return this.chunkSource;
    }

    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long j = this.lastSeekPositionUs;
        BaseMediaChunk last = this.mediaChunks.getLast();
        if (!last.isLoadCompleted()) {
            last = this.mediaChunks.size() > 1 ? this.mediaChunks.get(this.mediaChunks.size() - 2) : null;
        }
        if (last != null) {
            j = Math.max(j, last.endTimeUs);
        }
        return Math.max(j, this.primarySampleQueue.getLargestQueuedTimestampUs());
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022 A[LOOP:0: B:11:0x0022->B:15:0x003e, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void seekToUs(long r7) {
        /*
            r6 = this;
            r6.lastSeekPositionUs = r7
            boolean r0 = r6.isPendingReset()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x001f
            com.google.android.exoplayer2.extractor.DefaultTrackOutput r0 = r6.primarySampleQueue
            long r3 = r6.getNextLoadPositionUs()
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0016
            r3 = r2
            goto L_0x0017
        L_0x0016:
            r3 = r1
        L_0x0017:
            boolean r0 = r0.skipToKeyframeBefore(r7, r3)
            if (r0 == 0) goto L_0x001f
            r0 = r2
            goto L_0x0020
        L_0x001f:
            r0 = r1
        L_0x0020:
            if (r0 == 0) goto L_0x0051
        L_0x0022:
            java.util.LinkedList<com.google.android.exoplayer2.source.chunk.BaseMediaChunk> r0 = r6.mediaChunks
            int r0 = r0.size()
            if (r0 <= r2) goto L_0x0044
            java.util.LinkedList<com.google.android.exoplayer2.source.chunk.BaseMediaChunk> r0 = r6.mediaChunks
            java.lang.Object r0 = r0.get(r2)
            com.google.android.exoplayer2.source.chunk.BaseMediaChunk r0 = (com.google.android.exoplayer2.source.chunk.BaseMediaChunk) r0
            int r0 = r0.getFirstSampleIndex(r1)
            com.google.android.exoplayer2.extractor.DefaultTrackOutput r3 = r6.primarySampleQueue
            int r3 = r3.getReadIndex()
            if (r0 > r3) goto L_0x0044
            java.util.LinkedList<com.google.android.exoplayer2.source.chunk.BaseMediaChunk> r0 = r6.mediaChunks
            r0.removeFirst()
            goto L_0x0022
        L_0x0044:
            com.google.android.exoplayer2.extractor.DefaultTrackOutput[] r0 = r6.embeddedSampleQueues
            int r3 = r0.length
        L_0x0047:
            if (r1 >= r3) goto L_0x007a
            r4 = r0[r1]
            r4.skipToKeyframeBefore(r7, r2)
            int r1 = r1 + 1
            goto L_0x0047
        L_0x0051:
            r6.pendingResetPositionUs = r7
            r6.loadingFinished = r1
            java.util.LinkedList<com.google.android.exoplayer2.source.chunk.BaseMediaChunk> r7 = r6.mediaChunks
            r7.clear()
            com.google.android.exoplayer2.upstream.Loader r7 = r6.loader
            boolean r7 = r7.isLoading()
            if (r7 == 0) goto L_0x0068
            com.google.android.exoplayer2.upstream.Loader r7 = r6.loader
            r7.cancelLoading()
            goto L_0x007a
        L_0x0068:
            com.google.android.exoplayer2.extractor.DefaultTrackOutput r7 = r6.primarySampleQueue
            r7.reset(r2)
            com.google.android.exoplayer2.extractor.DefaultTrackOutput[] r7 = r6.embeddedSampleQueues
            int r8 = r7.length
        L_0x0070:
            if (r1 >= r8) goto L_0x007a
            r0 = r7[r1]
            r0.reset(r2)
            int r1 = r1 + 1
            goto L_0x0070
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.chunk.ChunkSampleStream.seekToUs(long):void");
    }

    public void release() {
        this.primarySampleQueue.disable();
        for (DefaultTrackOutput disable : this.embeddedSampleQueues) {
            disable.disable();
        }
        this.loader.release();
    }

    public boolean isReady() {
        return this.loadingFinished || (!isPendingReset() && !this.primarySampleQueue.isEmpty());
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        if (!this.loader.isLoading()) {
            this.chunkSource.maybeThrowError();
        }
    }

    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (isPendingReset()) {
            return -3;
        }
        discardDownstreamMediaChunks(this.primarySampleQueue.getReadIndex());
        return this.primarySampleQueue.readData(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.lastSeekPositionUs);
    }

    public void skipData(long j) {
        if (!this.loadingFinished || j <= this.primarySampleQueue.getLargestQueuedTimestampUs()) {
            this.primarySampleQueue.skipToKeyframeBefore(j, true);
        } else {
            this.primarySampleQueue.skipAll();
        }
    }

    public void onLoadCompleted(Chunk chunk, long j, long j2) {
        Chunk chunk2 = chunk;
        this.chunkSource.onChunkLoadCompleted(chunk2);
        this.eventDispatcher.loadCompleted(chunk2.dataSpec, chunk2.type, this.primaryTrackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, j, j2, chunk.bytesLoaded());
        this.callback.onContinueLoadingRequested(this);
    }

    public void onLoadCanceled(Chunk chunk, long j, long j2, boolean z) {
        Chunk chunk2 = chunk;
        this.eventDispatcher.loadCanceled(chunk2.dataSpec, chunk2.type, this.primaryTrackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, j, j2, chunk.bytesLoaded());
        if (!z) {
            this.primarySampleQueue.reset(true);
            for (DefaultTrackOutput reset : this.embeddedSampleQueues) {
                reset.reset(true);
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    public int onLoadError(Chunk chunk, long j, long j2, IOException iOException) {
        boolean z;
        Chunk chunk2 = chunk;
        long bytesLoaded = chunk.bytesLoaded();
        boolean isMediaChunk = isMediaChunk(chunk);
        if (this.chunkSource.onChunkLoadError(chunk2, !isMediaChunk || bytesLoaded == 0 || this.mediaChunks.size() > 1, iOException)) {
            if (isMediaChunk) {
                BaseMediaChunk removeLast = this.mediaChunks.removeLast();
                Assertions.checkState(removeLast == chunk2);
                this.primarySampleQueue.discardUpstreamSamples(removeLast.getFirstSampleIndex(0));
                int i = 0;
                while (i < this.embeddedSampleQueues.length) {
                    DefaultTrackOutput defaultTrackOutput = this.embeddedSampleQueues[i];
                    i++;
                    defaultTrackOutput.discardUpstreamSamples(removeLast.getFirstSampleIndex(i));
                }
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                }
            }
            z = true;
        } else {
            z = false;
        }
        this.eventDispatcher.loadError(chunk2.dataSpec, chunk2.type, this.primaryTrackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, j, j2, bytesLoaded, iOException, z);
        if (!z) {
            return 0;
        }
        this.callback.onContinueLoadingRequested(this);
        return 2;
    }

    public boolean continueLoading(long j) {
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        this.chunkSource.getNextChunk(this.mediaChunks.isEmpty() ? null : this.mediaChunks.getLast(), this.pendingResetPositionUs != C.TIME_UNSET ? this.pendingResetPositionUs : j, this.nextChunkHolder);
        boolean z = this.nextChunkHolder.endOfStream;
        Chunk chunk = this.nextChunkHolder.chunk;
        this.nextChunkHolder.clear();
        if (z) {
            this.loadingFinished = true;
            return true;
        } else if (chunk == null) {
            return false;
        } else {
            if (isMediaChunk(chunk)) {
                this.pendingResetPositionUs = C.TIME_UNSET;
                BaseMediaChunk baseMediaChunk = (BaseMediaChunk) chunk;
                baseMediaChunk.init(this.mediaChunkOutput);
                this.mediaChunks.add(baseMediaChunk);
            }
            this.eventDispatcher.loadStarted(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, this.loader.startLoading(chunk, this, this.minLoadableRetryCount));
            return true;
        }
    }

    public long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        return this.mediaChunks.getLast().endTimeUs;
    }

    private void maybeDiscardUpstream(long j) {
        discardUpstreamMediaChunks(Math.max(1, this.chunkSource.getPreferredQueueSize(j, this.readOnlyMediaChunks)));
    }

    private boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof BaseMediaChunk;
    }

    /* access modifiers changed from: package-private */
    public boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    private void discardDownstreamMediaChunks(int i) {
        while (this.mediaChunks.size() > 1 && this.mediaChunks.get(1).getFirstSampleIndex(0) <= i) {
            this.mediaChunks.removeFirst();
        }
        BaseMediaChunk first = this.mediaChunks.getFirst();
        Format format = first.trackFormat;
        if (!format.equals(this.primaryDownstreamTrackFormat)) {
            this.eventDispatcher.downstreamFormatChanged(this.primaryTrackType, format, first.trackSelectionReason, first.trackSelectionData, first.startTimeUs);
        }
        this.primaryDownstreamTrackFormat = format;
    }

    private boolean discardUpstreamMediaChunks(int i) {
        int i2 = 0;
        if (this.mediaChunks.size() <= i) {
            return false;
        }
        long j = this.mediaChunks.getLast().endTimeUs;
        BaseMediaChunk baseMediaChunk = null;
        long j2 = 0;
        while (this.mediaChunks.size() > i) {
            baseMediaChunk = this.mediaChunks.removeLast();
            j2 = baseMediaChunk.startTimeUs;
            this.loadingFinished = false;
        }
        this.primarySampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(0));
        while (i2 < this.embeddedSampleQueues.length) {
            DefaultTrackOutput defaultTrackOutput = this.embeddedSampleQueues[i2];
            i2++;
            defaultTrackOutput.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(i2));
        }
        this.eventDispatcher.upstreamDiscarded(this.primaryTrackType, j2, j);
        return true;
    }

    public final class EmbeddedSampleStream implements SampleStream {
        private final int index;
        public final ChunkSampleStream<T> parent;
        private final DefaultTrackOutput sampleQueue;

        public void maybeThrowError() throws IOException {
        }

        public EmbeddedSampleStream(ChunkSampleStream<T> chunkSampleStream, DefaultTrackOutput defaultTrackOutput, int i) {
            this.parent = chunkSampleStream;
            this.sampleQueue = defaultTrackOutput;
            this.index = i;
        }

        public boolean isReady() {
            return ChunkSampleStream.this.loadingFinished || (!ChunkSampleStream.this.isPendingReset() && !this.sampleQueue.isEmpty());
        }

        public void skipData(long j) {
            if (!ChunkSampleStream.this.loadingFinished || j <= this.sampleQueue.getLargestQueuedTimestampUs()) {
                this.sampleQueue.skipToKeyframeBefore(j, true);
            } else {
                this.sampleQueue.skipAll();
            }
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            if (ChunkSampleStream.this.isPendingReset()) {
                return -3;
            }
            return this.sampleQueue.readData(formatHolder, decoderInputBuffer, z, ChunkSampleStream.this.loadingFinished, ChunkSampleStream.this.lastSeekPositionUs);
        }

        public void release() {
            Assertions.checkState(ChunkSampleStream.this.embeddedTracksSelected[this.index]);
            ChunkSampleStream.this.embeddedTracksSelected[this.index] = false;
        }
    }
}
