package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TsExtractor implements Extractor {
    /* access modifiers changed from: private */
    public static final long AC3_FORMAT_IDENTIFIER = ((long) Util.getIntegerCodeForString("AC-3"));
    private static final int BUFFER_PACKET_COUNT = 5;
    private static final int BUFFER_SIZE = 940;
    /* access modifiers changed from: private */
    public static final long E_AC3_FORMAT_IDENTIFIER = ((long) Util.getIntegerCodeForString("EAC3"));
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() {
        public Extractor[] createExtractors() {
            return new Extractor[]{new TsExtractor()};
        }
    };
    /* access modifiers changed from: private */
    public static final long HEVC_FORMAT_IDENTIFIER = ((long) Util.getIntegerCodeForString("HEVC"));
    private static final int MAX_PID_PLUS_ONE = 8192;
    public static final int MODE_HLS = 2;
    public static final int MODE_MULTI_PMT = 0;
    public static final int MODE_SINGLE_PMT = 1;
    private static final int TS_PACKET_SIZE = 188;
    private static final int TS_PAT_PID = 0;
    public static final int TS_STREAM_TYPE_AAC = 15;
    public static final int TS_STREAM_TYPE_AC3 = 129;
    public static final int TS_STREAM_TYPE_DTS = 138;
    public static final int TS_STREAM_TYPE_DVBSUBS = 89;
    public static final int TS_STREAM_TYPE_E_AC3 = 135;
    public static final int TS_STREAM_TYPE_H262 = 2;
    public static final int TS_STREAM_TYPE_H264 = 27;
    public static final int TS_STREAM_TYPE_H265 = 36;
    public static final int TS_STREAM_TYPE_HDMV_DTS = 130;
    public static final int TS_STREAM_TYPE_ID3 = 21;
    public static final int TS_STREAM_TYPE_MPA = 3;
    public static final int TS_STREAM_TYPE_MPA_LSF = 4;
    public static final int TS_STREAM_TYPE_SPLICE_INFO = 134;
    private static final int TS_SYNC_BYTE = 71;
    private final SparseIntArray continuityCounters;
    /* access modifiers changed from: private */
    public TsPayloadReader id3Reader;
    /* access modifiers changed from: private */
    public final int mode;
    /* access modifiers changed from: private */
    public ExtractorOutput output;
    /* access modifiers changed from: private */
    public final TsPayloadReader.Factory payloadReaderFactory;
    /* access modifiers changed from: private */
    public int remainingPmts;
    /* access modifiers changed from: private */
    public final List<TimestampAdjuster> timestampAdjusters;
    /* access modifiers changed from: private */
    public final SparseBooleanArray trackIds;
    /* access modifiers changed from: private */
    public boolean tracksEnded;
    private final ParsableByteArray tsPacketBuffer;
    /* access modifiers changed from: private */
    public final SparseArray<TsPayloadReader> tsPayloadReaders;
    private final ParsableBitArray tsScratch;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public void release() {
    }

    static /* synthetic */ int access$108(TsExtractor tsExtractor) {
        int i = tsExtractor.remainingPmts;
        tsExtractor.remainingPmts = i + 1;
        return i;
    }

    public TsExtractor() {
        this(0);
    }

    public TsExtractor(int i) {
        this(1, i);
    }

    public TsExtractor(int i, int i2) {
        this(i, new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory(i2));
    }

    public TsExtractor(int i, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory) {
        this.payloadReaderFactory = (TsPayloadReader.Factory) Assertions.checkNotNull(factory);
        this.mode = i;
        if (i == 1 || i == 2) {
            this.timestampAdjusters = Collections.singletonList(timestampAdjuster);
        } else {
            this.timestampAdjusters = new ArrayList();
            this.timestampAdjusters.add(timestampAdjuster);
        }
        this.tsPacketBuffer = new ParsableByteArray((int) BUFFER_SIZE);
        this.tsScratch = new ParsableBitArray(new byte[3]);
        this.trackIds = new SparseBooleanArray();
        this.tsPayloadReaders = new SparseArray<>();
        this.continuityCounters = new SparseIntArray();
        resetPayloadReaders();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        byte[] bArr = this.tsPacketBuffer.data;
        extractorInput.peekFully(bArr, 0, BUFFER_SIZE);
        int i = 0;
        while (i < TS_PACKET_SIZE) {
            int i2 = 0;
            while (i2 != 5) {
                if (bArr[(i2 * TS_PACKET_SIZE) + i] != 71) {
                    i++;
                } else {
                    i2++;
                }
            }
            extractorInput.skipFully(i);
            return true;
        }
        return false;
    }

    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
        extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
    }

    public void seek(long j, long j2) {
        int size = this.timestampAdjusters.size();
        for (int i = 0; i < size; i++) {
            this.timestampAdjusters.get(i).reset();
        }
        this.tsPacketBuffer.reset();
        this.continuityCounters.clear();
        resetPayloadReaders();
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(com.google.android.exoplayer2.extractor.ExtractorInput r10, com.google.android.exoplayer2.extractor.PositionHolder r11) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r9 = this;
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r9.tsPacketBuffer
            byte[] r11 = r11.data
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.tsPacketBuffer
            int r0 = r0.getPosition()
            int r0 = 940 - r0
            r1 = 188(0xbc, float:2.63E-43)
            r2 = 0
            if (r0 >= r1) goto L_0x0027
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.tsPacketBuffer
            int r0 = r0.bytesLeft()
            if (r0 <= 0) goto L_0x0022
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r9.tsPacketBuffer
            int r3 = r3.getPosition()
            java.lang.System.arraycopy(r11, r3, r11, r2, r0)
        L_0x0022:
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r9.tsPacketBuffer
            r3.reset(r11, r0)
        L_0x0027:
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.tsPacketBuffer
            int r0 = r0.bytesLeft()
            if (r0 >= r1) goto L_0x0046
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.tsPacketBuffer
            int r0 = r0.limit()
            int r3 = 940 - r0
            int r3 = r10.read(r11, r0, r3)
            r4 = -1
            if (r3 != r4) goto L_0x003f
            return r4
        L_0x003f:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r9.tsPacketBuffer
            int r0 = r0 + r3
            r4.setLimit(r0)
            goto L_0x0027
        L_0x0046:
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.tsPacketBuffer
            int r10 = r10.limit()
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.tsPacketBuffer
            int r0 = r0.getPosition()
        L_0x0052:
            if (r0 >= r10) goto L_0x005d
            byte r3 = r11[r0]
            r4 = 71
            if (r3 == r4) goto L_0x005d
            int r0 = r0 + 1
            goto L_0x0052
        L_0x005d:
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r9.tsPacketBuffer
            r11.setPosition(r0)
            int r0 = r0 + r1
            if (r0 <= r10) goto L_0x0066
            return r2
        L_0x0066:
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r9.tsPacketBuffer
            r1 = 1
            r11.skipBytes(r1)
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r9.tsPacketBuffer
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r9.tsScratch
            r4 = 3
            r11.readBytes((com.google.android.exoplayer2.util.ParsableBitArray) r3, (int) r4)
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r9.tsScratch
            boolean r11 = r11.readBit()
            if (r11 == 0) goto L_0x0082
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.tsPacketBuffer
            r10.setPosition(r0)
            return r2
        L_0x0082:
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r9.tsScratch
            boolean r11 = r11.readBit()
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r9.tsScratch
            r3.skipBits(r1)
            com.google.android.exoplayer2.util.ParsableBitArray r3 = r9.tsScratch
            r4 = 13
            int r3 = r3.readBits(r4)
            com.google.android.exoplayer2.util.ParsableBitArray r4 = r9.tsScratch
            r5 = 2
            r4.skipBits(r5)
            com.google.android.exoplayer2.util.ParsableBitArray r4 = r9.tsScratch
            boolean r4 = r4.readBit()
            com.google.android.exoplayer2.util.ParsableBitArray r6 = r9.tsScratch
            boolean r6 = r6.readBit()
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r9.tsScratch
            r8 = 4
            int r7 = r7.readBits(r8)
            int r8 = r9.mode
            if (r8 == r5) goto L_0x00d0
            android.util.SparseIntArray r5 = r9.continuityCounters
            int r8 = r7 + -1
            int r5 = r5.get(r3, r8)
            android.util.SparseIntArray r8 = r9.continuityCounters
            r8.put(r3, r7)
            if (r5 != r7) goto L_0x00c9
            if (r6 == 0) goto L_0x00d0
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.tsPacketBuffer
            r10.setPosition(r0)
            return r2
        L_0x00c9:
            int r5 = r5 + r1
            int r5 = r5 % 16
            if (r7 == r5) goto L_0x00d0
            r5 = r1
            goto L_0x00d1
        L_0x00d0:
            r5 = r2
        L_0x00d1:
            if (r4 == 0) goto L_0x00de
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r9.tsPacketBuffer
            int r4 = r4.readUnsignedByte()
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r9.tsPacketBuffer
            r7.skipBytes(r4)
        L_0x00de:
            if (r6 == 0) goto L_0x010b
            android.util.SparseArray<com.google.android.exoplayer2.extractor.ts.TsPayloadReader> r4 = r9.tsPayloadReaders
            java.lang.Object r3 = r4.get(r3)
            com.google.android.exoplayer2.extractor.ts.TsPayloadReader r3 = (com.google.android.exoplayer2.extractor.ts.TsPayloadReader) r3
            if (r3 == 0) goto L_0x010b
            if (r5 == 0) goto L_0x00ef
            r3.seek()
        L_0x00ef:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r9.tsPacketBuffer
            r4.setLimit(r0)
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r9.tsPacketBuffer
            r3.consume(r4, r11)
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r9.tsPacketBuffer
            int r11 = r11.getPosition()
            if (r11 > r0) goto L_0x0102
            goto L_0x0103
        L_0x0102:
            r1 = r2
        L_0x0103:
            com.google.android.exoplayer2.util.Assertions.checkState(r1)
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r9.tsPacketBuffer
            r11.setLimit(r10)
        L_0x010b:
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.tsPacketBuffer
            r10.setPosition(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.TsExtractor.read(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    private void resetPayloadReaders() {
        this.trackIds.clear();
        this.tsPayloadReaders.clear();
        SparseArray<TsPayloadReader> createInitialPayloadReaders = this.payloadReaderFactory.createInitialPayloadReaders();
        int size = createInitialPayloadReaders.size();
        for (int i = 0; i < size; i++) {
            this.tsPayloadReaders.put(createInitialPayloadReaders.keyAt(i), createInitialPayloadReaders.valueAt(i));
        }
        this.tsPayloadReaders.put(0, new SectionReader(new PatReader()));
        this.id3Reader = null;
    }

    private class PatReader implements SectionPayloadReader {
        private final ParsableBitArray patScratch = new ParsableBitArray(new byte[4]);

        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }

        public PatReader() {
        }

        public void consume(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.readUnsignedByte() == 0) {
                parsableByteArray.skipBytes(7);
                int bytesLeft = parsableByteArray.bytesLeft() / 4;
                for (int i = 0; i < bytesLeft; i++) {
                    parsableByteArray.readBytes(this.patScratch, 4);
                    int readBits = this.patScratch.readBits(16);
                    this.patScratch.skipBits(3);
                    if (readBits == 0) {
                        this.patScratch.skipBits(13);
                    } else {
                        int readBits2 = this.patScratch.readBits(13);
                        TsExtractor.this.tsPayloadReaders.put(readBits2, new SectionReader(new PmtReader(readBits2)));
                        TsExtractor.access$108(TsExtractor.this);
                    }
                }
                if (TsExtractor.this.mode != 2) {
                    TsExtractor.this.tsPayloadReaders.remove(0);
                }
            }
        }
    }

    private class PmtReader implements SectionPayloadReader {
        private static final int TS_PMT_DESC_AC3 = 106;
        private static final int TS_PMT_DESC_DTS = 123;
        private static final int TS_PMT_DESC_DVBSUBS = 89;
        private static final int TS_PMT_DESC_EAC3 = 122;
        private static final int TS_PMT_DESC_ISO639_LANG = 10;
        private static final int TS_PMT_DESC_REGISTRATION = 5;
        private final int pid;
        private final ParsableBitArray pmtScratch = new ParsableBitArray(new byte[5]);
        private final SparseIntArray trackIdToPidScratch = new SparseIntArray();
        private final SparseArray<TsPayloadReader> trackIdToReaderScratch = new SparseArray<>();

        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }

        public PmtReader(int i) {
            this.pid = i;
        }

        public void consume(ParsableByteArray parsableByteArray) {
            TimestampAdjuster timestampAdjuster;
            TsPayloadReader tsPayloadReader;
            ParsableByteArray parsableByteArray2 = parsableByteArray;
            if (parsableByteArray.readUnsignedByte() == 2) {
                int i = 0;
                if (TsExtractor.this.mode == 1 || TsExtractor.this.mode == 2 || TsExtractor.this.remainingPmts == 1) {
                    timestampAdjuster = (TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0);
                } else {
                    timestampAdjuster = new TimestampAdjuster(((TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0)).getFirstSampleTimestampUs());
                    TsExtractor.this.timestampAdjusters.add(timestampAdjuster);
                }
                parsableByteArray2.skipBytes(2);
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                int i2 = 5;
                parsableByteArray2.skipBytes(5);
                parsableByteArray2.readBytes(this.pmtScratch, 2);
                int i3 = 4;
                this.pmtScratch.skipBits(4);
                parsableByteArray2.skipBytes(this.pmtScratch.readBits(12));
                if (TsExtractor.this.mode == 2 && TsExtractor.this.id3Reader == null) {
                    TsPayloadReader unused = TsExtractor.this.id3Reader = TsExtractor.this.payloadReaderFactory.createPayloadReader(21, new TsPayloadReader.EsInfo(21, (String) null, (List<TsPayloadReader.DvbSubtitleInfo>) null, new byte[0]));
                    TsExtractor.this.id3Reader.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(readUnsignedShort, 21, 8192));
                }
                this.trackIdToReaderScratch.clear();
                this.trackIdToPidScratch.clear();
                int bytesLeft = parsableByteArray.bytesLeft();
                while (bytesLeft > 0) {
                    parsableByteArray2.readBytes(this.pmtScratch, i2);
                    int readBits = this.pmtScratch.readBits(8);
                    this.pmtScratch.skipBits(3);
                    int readBits2 = this.pmtScratch.readBits(13);
                    this.pmtScratch.skipBits(i3);
                    int readBits3 = this.pmtScratch.readBits(12);
                    TsPayloadReader.EsInfo readEsInfo = readEsInfo(parsableByteArray2, readBits3);
                    if (readBits == 6) {
                        readBits = readEsInfo.streamType;
                    }
                    bytesLeft -= readBits3 + 5;
                    int i4 = TsExtractor.this.mode == 2 ? readBits : readBits2;
                    if (!TsExtractor.this.trackIds.get(i4)) {
                        if (TsExtractor.this.mode == 2 && readBits == 21) {
                            tsPayloadReader = TsExtractor.this.id3Reader;
                        } else {
                            tsPayloadReader = TsExtractor.this.payloadReaderFactory.createPayloadReader(readBits, readEsInfo);
                        }
                        if (TsExtractor.this.mode != 2 || readBits2 < this.trackIdToPidScratch.get(i4, 8192)) {
                            this.trackIdToPidScratch.put(i4, readBits2);
                            this.trackIdToReaderScratch.put(i4, tsPayloadReader);
                        }
                    }
                    i2 = 5;
                    i3 = 4;
                }
                int size = this.trackIdToPidScratch.size();
                for (int i5 = 0; i5 < size; i5++) {
                    int keyAt = this.trackIdToPidScratch.keyAt(i5);
                    TsExtractor.this.trackIds.put(keyAt, true);
                    TsPayloadReader valueAt = this.trackIdToReaderScratch.valueAt(i5);
                    if (valueAt != null) {
                        if (valueAt != TsExtractor.this.id3Reader) {
                            valueAt.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(readUnsignedShort, keyAt, 8192));
                        }
                        TsExtractor.this.tsPayloadReaders.put(this.trackIdToPidScratch.valueAt(i5), valueAt);
                    }
                }
                if (TsExtractor.this.mode != 2) {
                    TsExtractor.this.tsPayloadReaders.remove(this.pid);
                    TsExtractor tsExtractor = TsExtractor.this;
                    if (TsExtractor.this.mode != 1) {
                        i = TsExtractor.this.remainingPmts - 1;
                    }
                    int unused2 = tsExtractor.remainingPmts = i;
                    if (TsExtractor.this.remainingPmts == 0) {
                        TsExtractor.this.output.endTracks();
                        boolean unused3 = TsExtractor.this.tracksEnded = true;
                    }
                } else if (!TsExtractor.this.tracksEnded) {
                    TsExtractor.this.output.endTracks();
                    int unused4 = TsExtractor.this.remainingPmts = 0;
                    boolean unused5 = TsExtractor.this.tracksEnded = true;
                }
            }
        }

        private TsPayloadReader.EsInfo readEsInfo(ParsableByteArray parsableByteArray, int i) {
            int position = parsableByteArray.getPosition();
            int i2 = i + position;
            String str = null;
            int i3 = -1;
            ArrayList arrayList = null;
            while (parsableByteArray.getPosition() < i2) {
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                int position2 = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
                if (readUnsignedByte == 5) {
                    long readUnsignedInt = parsableByteArray.readUnsignedInt();
                    if (readUnsignedInt != TsExtractor.AC3_FORMAT_IDENTIFIER) {
                        if (readUnsignedInt != TsExtractor.E_AC3_FORMAT_IDENTIFIER) {
                            if (readUnsignedInt == TsExtractor.HEVC_FORMAT_IDENTIFIER) {
                                i3 = 36;
                            }
                            parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                        }
                        i3 = 135;
                        parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                    }
                } else if (readUnsignedByte != 106) {
                    if (readUnsignedByte != TS_PMT_DESC_EAC3) {
                        if (readUnsignedByte == TS_PMT_DESC_DTS) {
                            i3 = TsExtractor.TS_STREAM_TYPE_DTS;
                        } else if (readUnsignedByte == 10) {
                            str = parsableByteArray.readString(3).trim();
                        } else if (readUnsignedByte == 89) {
                            ArrayList arrayList2 = new ArrayList();
                            while (parsableByteArray.getPosition() < position2) {
                                String trim = parsableByteArray.readString(3).trim();
                                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                                byte[] bArr = new byte[4];
                                parsableByteArray.readBytes(bArr, 0, 4);
                                arrayList2.add(new TsPayloadReader.DvbSubtitleInfo(trim, readUnsignedByte2, bArr));
                            }
                            arrayList = arrayList2;
                            i3 = 89;
                        }
                        parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                    }
                    i3 = 135;
                    parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                }
                i3 = 129;
                parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
            }
            parsableByteArray.setPosition(i2);
            return new TsPayloadReader.EsInfo(i3, str, arrayList, Arrays.copyOfRange(parsableByteArray.data, position, i2));
        }
    }
}
