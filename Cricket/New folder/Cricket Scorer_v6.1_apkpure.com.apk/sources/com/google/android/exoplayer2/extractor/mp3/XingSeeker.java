package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class XingSeeker implements Mp3Extractor.Seeker {
    private final long durationUs;
    private final long firstFramePosition;
    private final int headerSize;
    private final long inputLength;
    private final long sizeBytes;
    private final long[] tableOfContents;

    public static XingSeeker create(MpegAudioHeader mpegAudioHeader, ParsableByteArray parsableByteArray, long j, long j2) {
        int readUnsignedIntToInt;
        MpegAudioHeader mpegAudioHeader2 = mpegAudioHeader;
        int i = mpegAudioHeader2.samplesPerFrame;
        int i2 = mpegAudioHeader2.sampleRate;
        long j3 = j + ((long) mpegAudioHeader2.frameSize);
        int readInt = parsableByteArray.readInt();
        if ((readInt & 1) != 1 || (readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt()) == 0) {
            return null;
        }
        long scaleLargeTimestamp = Util.scaleLargeTimestamp((long) readUnsignedIntToInt, ((long) i) * C.MICROS_PER_SECOND, (long) i2);
        if ((readInt & 6) != 6) {
            return new XingSeeker(j3, scaleLargeTimestamp, j2);
        }
        long readUnsignedIntToInt2 = (long) parsableByteArray.readUnsignedIntToInt();
        parsableByteArray.skipBytes(1);
        long[] jArr = new long[99];
        for (int i3 = 0; i3 < 99; i3++) {
            jArr[i3] = (long) parsableByteArray.readUnsignedByte();
        }
        return new XingSeeker(j3, scaleLargeTimestamp, j2, jArr, readUnsignedIntToInt2, mpegAudioHeader2.frameSize);
    }

    private XingSeeker(long j, long j2, long j3) {
        this(j, j2, j3, (long[]) null, 0, 0);
    }

    private XingSeeker(long j, long j2, long j3, long[] jArr, long j4, int i) {
        this.firstFramePosition = j;
        this.durationUs = j2;
        this.inputLength = j3;
        this.tableOfContents = jArr;
        this.sizeBytes = j4;
        this.headerSize = i;
    }

    public boolean isSeekable() {
        return this.tableOfContents != null;
    }

    public long getPosition(long j) {
        if (!isSeekable()) {
            return this.firstFramePosition;
        }
        float f = (((float) j) * 100.0f) / ((float) this.durationUs);
        float f2 = 0.0f;
        float f3 = 256.0f;
        if (f > 0.0f) {
            if (f >= 100.0f) {
                f2 = 256.0f;
            } else {
                int i = (int) f;
                if (i != 0) {
                    f2 = (float) this.tableOfContents[i - 1];
                }
                if (i < 99) {
                    f3 = (float) this.tableOfContents[i];
                }
                f2 += (f3 - f2) * (f - ((float) i));
            }
        }
        return Math.min(Math.round(0.00390625d * ((double) f2) * ((double) this.sizeBytes)) + this.firstFramePosition, this.inputLength != -1 ? this.inputLength - 1 : ((this.firstFramePosition - ((long) this.headerSize)) + this.sizeBytes) - 1);
    }

    public long getTimeUs(long j) {
        long j2;
        long j3;
        long j4 = 0;
        if (!isSeekable() || j < this.firstFramePosition) {
            return 0;
        }
        double d = (256.0d * ((double) (j - this.firstFramePosition))) / ((double) this.sizeBytes);
        int binarySearchFloor = Util.binarySearchFloor(this.tableOfContents, (long) d, true, false) + 1;
        long timeUsForTocPosition = getTimeUsForTocPosition(binarySearchFloor);
        if (binarySearchFloor == 0) {
            j2 = 0;
        } else {
            j2 = this.tableOfContents[binarySearchFloor - 1];
        }
        if (binarySearchFloor == 99) {
            j3 = 256;
        } else {
            j3 = this.tableOfContents[binarySearchFloor];
        }
        long timeUsForTocPosition2 = getTimeUsForTocPosition(binarySearchFloor + 1);
        if (j3 != j2) {
            j4 = (long) ((((double) (timeUsForTocPosition2 - timeUsForTocPosition)) * (d - ((double) j2))) / ((double) (j3 - j2)));
        }
        return timeUsForTocPosition + j4;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    private long getTimeUsForTocPosition(int i) {
        return (this.durationUs * ((long) i)) / 100;
    }
}
