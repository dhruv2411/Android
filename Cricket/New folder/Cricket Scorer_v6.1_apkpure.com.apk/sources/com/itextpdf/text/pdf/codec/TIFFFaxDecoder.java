package com.itextpdf.text.pdf.codec;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.itextpdf.text.DocWriter;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.exceptions.InvalidImageException;
import com.itextpdf.text.pdf.BidiOrder;
import com.itextpdf.text.pdf.ByteBuffer;

public class TIFFFaxDecoder {
    static short[] additionalMakeup = {28679, 28679, 31752, -32759, -31735, -30711, -29687, -28663, 29703, 29703, 30727, 30727, -27639, -26615, -25591, -24567};
    static short[] black = {62, 62, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 588, 588, 588, 588, 588, 588, 588, 588, 1680, 1680, 20499, 22547, 24595, 26643, 1776, 1776, 1808, 1808, -24557, -22509, -20461, -18413, 1904, 1904, 1936, 1936, -16365, -14317, 782, 782, 782, 782, 814, 814, 814, 814, -12269, -10221, 10257, 10257, 12305, 12305, 14353, 14353, 16403, 18451, 1712, 1712, 1744, 1744, 28691, 30739, -32749, -30701, -28653, -26605, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 750, 750, 750, 750, 1616, 1616, 1648, 1648, 1424, 1424, 1456, 1456, 1488, 1488, 1520, 1520, 1840, 1840, 1872, 1872, 1968, 1968, 8209, 8209, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 1552, 1552, 1584, 1584, 2000, 2000, 2032, 2032, 976, 976, 1008, 1008, 1040, 1040, 1072, 1072, 1296, 1296, 1328, 1328, 718, 718, 718, 718, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 4113, 4113, 6161, 6161, 848, 848, 880, 880, 912, 912, 944, 944, 622, 622, 622, 622, 654, 654, 654, 654, 1104, 1104, 1136, 1136, 1168, 1168, 1200, 1200, 1232, 1232, 1264, 1264, 686, 686, 686, 686, 1360, 1360, 1392, 1392, 12, 12, 12, 12, 12, 12, 12, 12, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390};
    static byte[] flipTable = {0, Byte.MIN_VALUE, 64, -64, DocWriter.SPACE, -96, 96, -32, BidiOrder.S, -112, 80, -48, ByteBuffer.ZERO, -80, 112, -16, 8, -120, 72, -56, 40, -88, 104, -24, 24, -104, 88, -40, 56, -72, 120, -8, 4, -124, 68, -60, 36, -92, 100, -28, 20, -108, 84, -44, 52, -76, 116, -12, BidiOrder.CS, -116, 76, -52, 44, -84, 108, -20, 28, -100, 92, -36, DocWriter.LT, -68, 124, -4, 2, -126, 66, -62, DocWriter.QUOTE, -94, 98, -30, 18, -110, 82, -46, 50, -78, 114, -14, 10, -118, 74, -54, 42, -86, 106, -22, 26, -102, 90, -38, 58, -70, 122, -6, 6, -122, 70, -58, 38, -90, 102, -26, 22, -106, 86, -42, 54, -74, 118, -10, BidiOrder.BN, -114, 78, -50, 46, -82, 110, -18, 30, -98, 94, -34, DocWriter.GT, -66, 126, -2, 1, -127, 65, -63, 33, -95, 97, -31, BidiOrder.WS, -111, 81, -47, 49, -79, 113, -15, 9, -119, 73, -55, 41, -87, 105, -23, 25, -103, 89, -39, 57, -71, 121, -7, 5, -123, 69, -59, 37, -91, 101, -27, 21, -107, 85, -43, 53, -75, 117, -11, BidiOrder.NSM, -115, 77, -51, 45, -83, 109, -19, 29, -99, 93, -35, DocWriter.EQUALS, -67, 125, -3, 3, -125, 67, -61, 35, -93, 99, -29, 19, -109, 83, -45, 51, -77, 115, -13, BidiOrder.AN, -117, 75, -53, 43, -85, 107, -21, 27, -101, 91, -37, 59, -69, 123, -5, 7, -121, 71, -57, 39, -89, 103, -25, 23, -105, 87, -41, 55, -73, 119, -9, BidiOrder.B, -113, 79, -49, DocWriter.FORWARD, -81, 111, -17, 31, -97, 95, -33, 63, -65, Byte.MAX_VALUE, -1};
    static short[] initBlack = {3226, 6412, 200, 168, 38, 38, 134, 134, 100, 100, 100, 100, 68, 68, 68, 68};
    static int[] table1 = {0, 1, 3, 7, 15, 31, 63, 127, 255};
    static int[] table2 = {0, 128, PsExtractor.AUDIO_STREAM, 224, PsExtractor.VIDEO_STREAM_MASK, 248, 252, TIFFConstants.TIFFTAG_SUBFILETYPE, 255};
    static short[] twoBitBlack = {292, 260, 226, 226};
    static byte[] twoDCodes = {80, 88, 23, 71, 30, 30, DocWriter.GT, DocWriter.GT, 4, 4, 4, 4, 4, 4, 4, 4, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, BidiOrder.AN, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41};
    static short[] white = {6430, 6400, 6400, 6400, 3225, 3225, 3225, 3225, 944, 944, 944, 944, 976, 976, 976, 976, 1456, 1456, 1456, 1456, 1488, 1488, 1488, 1488, 718, 718, 718, 718, 718, 718, 718, 718, 750, 750, 750, 750, 750, 750, 750, 750, 1520, 1520, 1520, 1520, 1552, 1552, 1552, 1552, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 654, 654, 654, 654, 654, 654, 654, 654, 1072, 1072, 1072, 1072, 1104, 1104, 1104, 1104, 1136, 1136, 1136, 1136, 1168, 1168, 1168, 1168, 1200, 1200, 1200, 1200, 1232, 1232, 1232, 1232, 622, 622, 622, 622, 622, 622, 622, 622, 1008, 1008, 1008, 1008, 1040, 1040, 1040, 1040, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 1712, 1712, 1712, 1712, 1744, 1744, 1744, 1744, 846, 846, 846, 846, 846, 846, 846, 846, 1264, 1264, 1264, 1264, 1296, 1296, 1296, 1296, 1328, 1328, 1328, 1328, 1360, 1360, 1360, 1360, 1392, 1392, 1392, 1392, 1424, 1424, 1424, 1424, 686, 686, 686, 686, 686, 686, 686, 686, 910, 910, 910, 910, 910, 910, 910, 910, 1968, 1968, 1968, 1968, 2000, 2000, 2000, 2000, 2032, 2032, 2032, 2032, 16, 16, 16, 16, 10257, 10257, 10257, 10257, 12305, 12305, 12305, 12305, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 878, 878, 878, 878, 878, 878, 878, 878, 1904, 1904, 1904, 1904, 1936, 1936, 1936, 1936, -18413, -18413, -16365, -16365, -14317, -14317, -10221, -10221, 590, 590, 590, 590, 590, 590, 590, 590, 782, 782, 782, 782, 782, 782, 782, 782, 1584, 1584, 1584, 1584, 1616, 1616, 1616, 1616, 1648, 1648, 1648, 1648, 1680, 1680, 1680, 1680, 814, 814, 814, 814, 814, 814, 814, 814, 1776, 1776, 1776, 1776, 1808, 1808, 1808, 1808, 1840, 1840, 1840, 1840, 1872, 1872, 1872, 1872, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, 14353, 14353, 14353, 14353, 16401, 16401, 16401, 16401, 22547, 22547, 24595, 24595, 20497, 20497, 20497, 20497, 18449, 18449, 18449, 18449, 26643, 26643, 28691, 28691, 30739, 30739, -32749, -32749, -30701, -30701, -28653, -28653, -26605, -26605, -24557, -24557, -22509, -22509, -20461, -20461, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232};
    private int bitPointer;
    private int bytePointer;
    private int changingElemSize = 0;
    private int compression = 2;
    private int[] currChangingElems;
    private byte[] data;
    private int fillBits = 0;
    private long fillOrder;
    private int h;
    private int lastChangingElement = 0;
    private int oneD;
    private int[] prevChangingElems;
    private boolean recoverFromImageError;
    private int uncompressedMode = 0;
    private int w;

    public TIFFFaxDecoder(long j, int i, int i2) {
        this.fillOrder = j;
        this.w = i;
        this.h = i2;
        this.bitPointer = 0;
        this.bytePointer = 0;
        int i3 = 2 * i;
        this.prevChangingElems = new int[i3];
        this.currChangingElems = new int[i3];
    }

    public static void reverseBits(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = flipTable[bArr[i] & 255];
        }
    }

    public void decode1D(byte[] bArr, byte[] bArr2, int i, int i2) {
        this.data = bArr2;
        int i3 = (this.w + 7) / 8;
        this.bitPointer = 0;
        this.bytePointer = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            decodeNextScanline(bArr, i4, i);
            i4 += i3;
        }
    }

    public void decodeNextScanline(byte[] bArr, int i, int i2) {
        this.changingElemSize = 0;
        boolean z = true;
        while (true) {
            if (i2 >= this.w) {
                break;
            }
            while (z) {
                int nextNBits = nextNBits(10);
                short s = white[nextNBits];
                short s2 = s & 1;
                int i3 = (s >>> 1) & 15;
                if (i3 == 12) {
                    short s3 = additionalMakeup[(12 & (nextNBits << 2)) | nextLesserThan8Bits(2)];
                    i2 += (s3 >>> 4) & 4095;
                    updatePointer(4 - ((s3 >>> 1) & 7));
                } else if (i3 == 0) {
                    throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.code.encountered", new Object[0]));
                } else if (i3 == 15) {
                    throw new RuntimeException(MessageLocalization.getComposedMessage("eol.code.word.encountered.in.white.run", new Object[0]));
                } else {
                    i2 += (s >>> 5) & 2047;
                    updatePointer(10 - i3);
                    if (s2 == 0) {
                        int[] iArr = this.currChangingElems;
                        int i4 = this.changingElemSize;
                        this.changingElemSize = i4 + 1;
                        iArr[i4] = i2;
                        z = false;
                    }
                }
            }
            if (i2 != this.w) {
                while (!z) {
                    short s4 = initBlack[nextLesserThan8Bits(4)];
                    int i5 = (s4 >>> 1) & 15;
                    int i6 = (s4 >>> 5) & 2047;
                    if (i6 == 100) {
                        short s5 = black[nextNBits(9)];
                        short s6 = s5 & 1;
                        int i7 = (s5 >>> 1) & 15;
                        int i8 = (s5 >>> 5) & 2047;
                        if (i7 == 12) {
                            updatePointer(5);
                            short s7 = additionalMakeup[nextLesserThan8Bits(4)];
                            int i9 = (s7 >>> 4) & 4095;
                            setToBlack(bArr, i, i2, i9);
                            r15 = i2 + i9;
                            updatePointer(4 - ((s7 >>> 1) & 7));
                        } else if (i7 == 15) {
                            throw new RuntimeException(MessageLocalization.getComposedMessage("eol.code.word.encountered.in.black.run", new Object[0]));
                        } else {
                            setToBlack(bArr, i, i2, i8);
                            r15 = i2 + i8;
                            updatePointer(9 - i7);
                            if (s6 == 0) {
                                int[] iArr2 = this.currChangingElems;
                                int i10 = this.changingElemSize;
                                this.changingElemSize = i10 + 1;
                                iArr2[i10] = r15;
                            }
                        }
                    } else if (i6 == 200) {
                        short s8 = twoBitBlack[nextLesserThan8Bits(2)];
                        int i11 = (s8 >>> 5) & 2047;
                        setToBlack(bArr, i, i2, i11);
                        r15 = i2 + i11;
                        updatePointer(2 - ((s8 >>> 1) & 15));
                        int[] iArr3 = this.currChangingElems;
                        int i12 = this.changingElemSize;
                        this.changingElemSize = i12 + 1;
                        iArr3[i12] = r15;
                    } else {
                        setToBlack(bArr, i, i2, i6);
                        r15 = i2 + i6;
                        updatePointer(4 - i5);
                        int[] iArr4 = this.currChangingElems;
                        int i13 = this.changingElemSize;
                        this.changingElemSize = i13 + 1;
                        iArr4[i13] = r15;
                    }
                    z = true;
                }
                if (i2 == this.w) {
                    if (this.compression == 2) {
                        advancePointer();
                    }
                }
            } else if (this.compression == 2) {
                advancePointer();
            }
        }
        int[] iArr5 = this.currChangingElems;
        int i14 = this.changingElemSize;
        this.changingElemSize = i14 + 1;
        iArr5[i14] = i2;
    }

    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r6v3 */
    /* JADX WARNING: type inference failed for: r12v20 */
    /* JADX WARNING: type inference failed for: r6v23 */
    /* JADX WARNING: type inference failed for: r12v21 */
    /* JADX WARNING: type inference failed for: r6v26 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode2D(byte[] r22, byte[] r23, int r24, int r25, long r26) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r24
            r3 = r23
            r0.data = r3
            r3 = 3
            r0.compression = r3
            r6 = 0
            r0.bitPointer = r6
            r0.bytePointer = r6
            int r7 = r0.w
            r8 = 7
            int r7 = r7 + r8
            r9 = 8
            int r7 = r7 / r9
            r10 = 2
            int[] r11 = new int[r10]
            r12 = 1
            long r14 = r26 & r12
            int r12 = (int) r14
            r0.oneD = r12
            r12 = 2
            long r14 = r26 & r12
            r12 = 1
            long r13 = r14 >> r12
            int r13 = (int) r13
            r0.uncompressedMode = r13
            r13 = 4
            long r15 = r26 & r13
            long r4 = r15 >> r10
            int r4 = (int) r4
            r0.fillBits = r4
            int r4 = r0.readEOL(r12)
            if (r4 == r12) goto L_0x004a
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "first.scanline.must.be.1d.encoded"
            java.lang.Object[] r3 = new java.lang.Object[r6]
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)
            r1.<init>(r2)
            throw r1
        L_0x004a:
            r0.decodeNextScanline(r1, r6, r2)
            int r4 = r6 + r7
            r10 = r4
            r5 = r12
            r4 = r25
        L_0x0053:
            if (r5 >= r4) goto L_0x0124
            int r13 = r0.readEOL(r6)
            if (r13 != 0) goto L_0x0117
            int[] r13 = r0.prevChangingElems
            int[] r14 = r0.currChangingElems
            r0.prevChangingElems = r14
            r0.currChangingElems = r13
            r13 = -1
            r0.lastChangingElement = r6
            r16 = r6
            r15 = r12
            r14 = r13
            r13 = r2
        L_0x006b:
            int r9 = r0.w
            if (r13 >= r9) goto L_0x010b
            r0.getNextChangingElement(r14, r15, r11)
            r9 = r11[r6]
            r14 = r11[r12]
            int r18 = r0.nextLesserThan8Bits(r8)
            byte[] r19 = twoDCodes
            byte r6 = r19[r18]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r18 = r6 & 120(0x78, float:1.68E-43)
            int r12 = r18 >>> 3
            r6 = r6 & r8
            if (r12 != 0) goto L_0x0097
            if (r15 != 0) goto L_0x008e
            int r9 = r14 - r13
            r0.setToBlack(r1, r10, r13, r9)
        L_0x008e:
            int r6 = 7 - r6
            r0.updatePointer(r6)
            r13 = r14
            r6 = 0
            r12 = 1
            goto L_0x006b
        L_0x0097:
            r14 = 1
            if (r12 != r14) goto L_0x00db
            int r6 = 7 - r6
            r0.updatePointer(r6)
            if (r15 == 0) goto L_0x00bd
            int r6 = r21.decodeWhiteCodeWord()
            int r13 = r13 + r6
            int[] r6 = r0.currChangingElems
            int r9 = r16 + 1
            r6[r16] = r13
            int r6 = r21.decodeBlackCodeWord()
            r0.setToBlack(r1, r10, r13, r6)
            int r13 = r13 + r6
            int[] r6 = r0.currChangingElems
            int r12 = r9 + 1
            r6[r9] = r13
        L_0x00ba:
            r16 = r12
            goto L_0x00d7
        L_0x00bd:
            int r6 = r21.decodeBlackCodeWord()
            r0.setToBlack(r1, r10, r13, r6)
            int r13 = r13 + r6
            int[] r6 = r0.currChangingElems
            int r9 = r16 + 1
            r6[r16] = r13
            int r6 = r21.decodeWhiteCodeWord()
            int r13 = r13 + r6
            int[] r6 = r0.currChangingElems
            int r12 = r9 + 1
            r6[r9] = r13
            goto L_0x00ba
        L_0x00d7:
            r12 = r14
        L_0x00d8:
            r6 = 0
            r14 = r13
            goto L_0x006b
        L_0x00db:
            r3 = 8
            if (r12 > r3) goto L_0x00fc
            int r12 = r12 + -5
            int r9 = r9 + r12
            int[] r12 = r0.currChangingElems
            int r17 = r16 + 1
            r12[r16] = r9
            if (r15 != 0) goto L_0x00ef
            int r12 = r9 - r13
            r0.setToBlack(r1, r10, r13, r12)
        L_0x00ef:
            r15 = r15 ^ 1
            int r6 = 7 - r6
            r0.updatePointer(r6)
            r13 = r9
            r12 = r14
            r16 = r17
            r3 = 3
            goto L_0x00d8
        L_0x00fc:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "invalid.code.encountered.while.decoding.2d.group.3.compressed.data"
            r6 = 0
            java.lang.Object[] r3 = new java.lang.Object[r6]
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)
            r1.<init>(r2)
            throw r1
        L_0x010b:
            r14 = r12
            r3 = 8
            int[] r9 = r0.currChangingElems
            int r12 = r16 + 1
            r9[r16] = r13
            r0.changingElemSize = r12
            goto L_0x011c
        L_0x0117:
            r3 = r9
            r14 = r12
            r0.decodeNextScanline(r1, r10, r2)
        L_0x011c:
            int r10 = r10 + r7
            int r5 = r5 + 1
            r9 = r3
            r12 = r14
            r3 = 3
            goto L_0x0053
        L_0x0124:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.TIFFFaxDecoder.decode2D(byte[], byte[], int, int, long):void");
    }

    /* JADX WARNING: type inference failed for: r7v3 */
    /* JADX WARNING: type inference failed for: r7v15 */
    /* JADX WARNING: type inference failed for: r7v16 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decodeT6(byte[] r21, byte[] r22, int r23, int r24, long r25) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            r0.data = r2
            r2 = 4
            r0.compression = r2
            r2 = 0
            r0.bitPointer = r2
            r0.bytePointer = r2
            int r3 = r0.w
            r4 = 7
            int r3 = r3 + r4
            r5 = 8
            int r3 = r3 / r5
            r6 = 2
            int[] r6 = new int[r6]
            r7 = 2
            long r11 = r25 & r7
            r7 = 1
            long r8 = r11 >> r7
            int r8 = (int) r8
            r0.uncompressedMode = r8
            int[] r8 = r0.currChangingElems
            r0.changingElemSize = r2
            int r9 = r0.changingElemSize
            int r10 = r9 + 1
            r0.changingElemSize = r10
            int r10 = r0.w
            r8[r9] = r10
            int r9 = r0.changingElemSize
            int r10 = r9 + 1
            r0.changingElemSize = r10
            int r10 = r0.w
            r8[r9] = r10
            r8 = r24
            r9 = r2
            r10 = r9
        L_0x0040:
            if (r9 >= r8) goto L_0x019a
            r11 = -1
            int[] r12 = r0.prevChangingElems
            int[] r13 = r0.currChangingElems
            r0.prevChangingElems = r13
            r0.currChangingElems = r12
            r0.lastChangingElement = r2
            r13 = r2
            r15 = r7
            r14 = r11
            r11 = r23
        L_0x0052:
            int r5 = r0.w
            if (r11 >= r5) goto L_0x0179
            int r5 = r0.bytePointer
            byte[] r4 = r0.data
            int r4 = r4.length
            if (r5 >= r4) goto L_0x0171
            r0.getNextChangingElement(r14, r15, r6)
            r4 = r6[r2]
            r5 = r6[r7]
            r2 = 7
            int r16 = r0.nextLesserThan8Bits(r2)
            byte[] r17 = twoDCodes
            byte r7 = r17[r16]
            r7 = r7 & 255(0xff, float:3.57E-43)
            r16 = r7 & 120(0x78, float:1.68E-43)
            r18 = r6
            r6 = 3
            int r8 = r16 >>> 3
            r7 = r7 & r2
            if (r8 != 0) goto L_0x008f
            if (r15 != 0) goto L_0x0080
            int r4 = r5 - r11
            r0.setToBlack(r1, r10, r11, r4)
        L_0x0080:
            int r4 = 7 - r7
            r0.updatePointer(r4)
            r4 = r2
            r11 = r5
            r14 = r11
        L_0x0088:
            r6 = r18
            r2 = 0
        L_0x008b:
            r7 = 1
        L_0x008c:
            r8 = r24
            goto L_0x0052
        L_0x008f:
            r5 = 1
            if (r8 != r5) goto L_0x00cd
            int r4 = 7 - r7
            r0.updatePointer(r4)
            if (r15 == 0) goto L_0x00b1
            int r2 = r20.decodeWhiteCodeWord()
            int r11 = r11 + r2
            int r2 = r13 + 1
            r12[r13] = r11
            int r4 = r20.decodeBlackCodeWord()
            r0.setToBlack(r1, r10, r11, r4)
            int r11 = r11 + r4
            int r4 = r2 + 1
            r12[r2] = r11
        L_0x00ae:
            r13 = r4
            r14 = r11
            goto L_0x00c7
        L_0x00b1:
            int r2 = r20.decodeBlackCodeWord()
            r0.setToBlack(r1, r10, r11, r2)
            int r11 = r11 + r2
            int r2 = r13 + 1
            r12[r13] = r11
            int r4 = r20.decodeWhiteCodeWord()
            int r11 = r11 + r4
            int r4 = r2 + 1
            r12[r2] = r11
            goto L_0x00ae
        L_0x00c7:
            r11 = r14
            r6 = r18
            r2 = 0
        L_0x00cb:
            r4 = 7
            goto L_0x008b
        L_0x00cd:
            r2 = 8
            if (r8 > r2) goto L_0x00ec
            int r8 = r8 + -5
            int r14 = r4 + r8
            int r4 = r13 + 1
            r12[r13] = r14
            if (r15 != 0) goto L_0x00e0
            int r5 = r14 - r11
            r0.setToBlack(r1, r10, r11, r5)
        L_0x00e0:
            r15 = r15 ^ 1
            r5 = 7
            int r6 = 7 - r7
            r0.updatePointer(r6)
            r13 = r4
            r4 = r5
            r11 = r14
            goto L_0x0088
        L_0x00ec:
            r5 = 7
            r4 = 11
            if (r8 != r4) goto L_0x0160
            int r4 = r0.nextLesserThan8Bits(r6)
            if (r4 == r5) goto L_0x0106
            com.itextpdf.text.exceptions.InvalidImageException r1 = new com.itextpdf.text.exceptions.InvalidImageException
            java.lang.String r2 = "invalid.code.encountered.while.decoding.2d.group.4.compressed.data"
            r4 = 0
            java.lang.Object[] r3 = new java.lang.Object[r4]
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)
            r1.<init>(r2)
            throw r1
        L_0x0106:
            r4 = 0
            r5 = r4
            r6 = r5
        L_0x0109:
            if (r5 != 0) goto L_0x015b
        L_0x010b:
            r7 = 1
            int r8 = r0.nextLesserThan8Bits(r7)
            if (r8 == r7) goto L_0x0115
            int r6 = r6 + 1
            goto L_0x010b
        L_0x0115:
            r7 = 5
            if (r6 <= r7) goto L_0x0142
            int r6 = r6 + -6
            if (r15 != 0) goto L_0x0123
            if (r6 <= 0) goto L_0x0123
            int r5 = r13 + 1
            r12[r13] = r11
            r13 = r5
        L_0x0123:
            int r11 = r11 + r6
            if (r6 <= 0) goto L_0x0129
            r5 = 1
            r15 = 1
            goto L_0x012a
        L_0x0129:
            r5 = 1
        L_0x012a:
            int r8 = r0.nextLesserThan8Bits(r5)
            if (r8 != 0) goto L_0x0139
            if (r15 != 0) goto L_0x0137
            int r5 = r13 + 1
            r12[r13] = r11
            r13 = r5
        L_0x0137:
            r15 = 1
            goto L_0x0141
        L_0x0139:
            if (r15 == 0) goto L_0x0140
            int r5 = r13 + 1
            r12[r13] = r11
            r13 = r5
        L_0x0140:
            r15 = r4
        L_0x0141:
            r5 = 1
        L_0x0142:
            if (r6 != r7) goto L_0x014e
            if (r15 != 0) goto L_0x014b
            int r7 = r13 + 1
            r12[r13] = r11
            r13 = r7
        L_0x014b:
            int r11 = r11 + r6
            r15 = 1
            goto L_0x0109
        L_0x014e:
            int r11 = r11 + r6
            int r7 = r13 + 1
            r12[r13] = r11
            r8 = 1
            r0.setToBlack(r1, r10, r11, r8)
            int r11 = r11 + r8
            r15 = r4
            r13 = r7
            goto L_0x0109
        L_0x015b:
            r2 = r4
            r6 = r18
            goto L_0x00cb
        L_0x0160:
            r4 = 0
            r8 = 1
            int r11 = r0.w
            r5 = 7
            int r6 = 7 - r7
            r0.updatePointer(r6)
            r2 = r4
            r4 = r5
            r7 = r8
            r6 = r18
            goto L_0x008c
        L_0x0171:
            r4 = r2
            r18 = r6
            r8 = r7
            r2 = 8
            r5 = 7
            goto L_0x0180
        L_0x0179:
            r5 = r4
            r18 = r6
            r8 = r7
            r4 = r2
            r2 = 8
        L_0x0180:
            int r6 = r12.length
            if (r13 >= r6) goto L_0x0188
            int r6 = r13 + 1
            r12[r13] = r11
            r13 = r6
        L_0x0188:
            r0.changingElemSize = r13
            int r10 = r10 + r3
            int r9 = r9 + 1
            r7 = r8
            r6 = r18
            r8 = r24
            r19 = r5
            r5 = r2
            r2 = r4
            r4 = r19
            goto L_0x0040
        L_0x019a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.TIFFFaxDecoder.decodeT6(byte[], byte[], int, int, long):void");
    }

    private void setToBlack(byte[] bArr, int i, int i2, int i3) {
        int i4 = (8 * i) + i2;
        int i5 = i3 + i4;
        int i6 = i4 >> 3;
        int i7 = i4 & 7;
        if (i7 > 0) {
            int i8 = 1 << (7 - i7);
            byte b = bArr[i6];
            while (i8 > 0 && i4 < i5) {
                b = (byte) (b | i8);
                i8 >>= 1;
                i4++;
            }
            bArr[i6] = b;
        }
        int i9 = i4 >> 3;
        while (i4 < i5 - 7) {
            bArr[i9] = -1;
            i4 += 8;
            i9++;
        }
        while (i4 < i5) {
            int i10 = i4 >> 3;
            if (!this.recoverFromImageError || i10 < bArr.length) {
                bArr[i10] = (byte) (bArr[i10] | (1 << (7 - (i4 & 7))));
            }
            i4++;
        }
    }

    private int decodeWhiteCodeWord() {
        boolean z = true;
        int i = 0;
        while (z) {
            int nextNBits = nextNBits(10);
            short s = white[nextNBits];
            short s2 = s & 1;
            int i2 = (s >>> 1) & 15;
            if (i2 == 12) {
                short s3 = additionalMakeup[nextLesserThan8Bits(2) | ((nextNBits << 2) & 12)];
                i += (s3 >>> 4) & 4095;
                updatePointer(4 - ((s3 >>> 1) & 7));
            } else if (i2 == 0) {
                throw new InvalidImageException(MessageLocalization.getComposedMessage("invalid.code.encountered", new Object[0]));
            } else {
                if (i2 != 15) {
                    i += (s >>> 5) & 2047;
                    updatePointer(10 - i2);
                    if (s2 != 0) {
                    }
                } else if (i != 0) {
                    throw new RuntimeException(MessageLocalization.getComposedMessage("eol.code.word.encountered.in.white.run", new Object[0]));
                }
                z = false;
            }
        }
        return i;
    }

    private int decodeBlackCodeWord() {
        boolean z = false;
        int i = 0;
        while (!z) {
            short s = initBlack[nextLesserThan8Bits(4)];
            int i2 = (s >>> 1) & 15;
            int i3 = (s >>> 5) & 2047;
            if (i3 == 100) {
                short s2 = black[nextNBits(9)];
                short s3 = s2 & 1;
                int i4 = (s2 >>> 1) & 15;
                int i5 = (s2 >>> 5) & 2047;
                if (i4 == 12) {
                    updatePointer(5);
                    short s4 = additionalMakeup[nextLesserThan8Bits(4)];
                    i += (s4 >>> 4) & 4095;
                    updatePointer(4 - ((s4 >>> 1) & 7));
                } else if (i4 == 15) {
                    throw new RuntimeException(MessageLocalization.getComposedMessage("eol.code.word.encountered.in.black.run", new Object[0]));
                } else {
                    i += i5;
                    updatePointer(9 - i4);
                    if (s3 != 0) {
                    }
                }
            } else if (i3 == 200) {
                short s5 = twoBitBlack[nextLesserThan8Bits(2)];
                i += (s5 >>> 5) & 2047;
                updatePointer(2 - ((s5 >>> 1) & 15));
            } else {
                i += i3;
                updatePointer(4 - i2);
            }
            z = true;
        }
        return i;
    }

    private int readEOL(boolean z) {
        int nextNBits;
        if (this.fillBits == 0) {
            int nextNBits2 = nextNBits(12);
            if (z && nextNBits2 == 0 && nextNBits(4) == 1) {
                this.fillBits = 1;
                return 1;
            } else if (nextNBits2 != 1) {
                throw new RuntimeException(MessageLocalization.getComposedMessage("scanline.must.begin.with.eol.code.word", new Object[0]));
            }
        } else if (this.fillBits == 1) {
            int i = 8 - this.bitPointer;
            if (nextNBits(i) != 0) {
                throw new RuntimeException(MessageLocalization.getComposedMessage("all.fill.bits.preceding.eol.code.must.be.0", new Object[0]));
            } else if (i >= 4 || nextNBits(8) == 0) {
                do {
                    nextNBits = nextNBits(8);
                    if (nextNBits != 1) {
                    }
                } while (nextNBits == 0);
                throw new RuntimeException(MessageLocalization.getComposedMessage("all.fill.bits.preceding.eol.code.must.be.0", new Object[0]));
            } else {
                throw new RuntimeException(MessageLocalization.getComposedMessage("all.fill.bits.preceding.eol.code.must.be.0", new Object[0]));
            }
        }
        if (this.oneD == 0) {
            return 1;
        }
        return nextLesserThan8Bits(1);
    }

    private void getNextChangingElement(int i, boolean z, int[] iArr) {
        int[] iArr2 = this.prevChangingElems;
        int i2 = this.changingElemSize;
        int i3 = this.lastChangingElement > 0 ? this.lastChangingElement - 1 : 0;
        int i4 = z ? i3 & -2 : i3 | 1;
        while (true) {
            if (i4 >= i2) {
                break;
            }
            int i5 = iArr2[i4];
            if (i5 > i) {
                this.lastChangingElement = i4;
                iArr[0] = i5;
                break;
            }
            i4 += 2;
        }
        int i6 = i4 + 1;
        if (i6 < i2) {
            iArr[1] = iArr2[i6];
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int nextNBits(int r10) {
        /*
            r9 = this;
            byte[] r0 = r9.data
            r1 = 0
            r2 = 1
            int r0 = r0.length
            int r0 = r0 + -1
            int r4 = r9.bytePointer
            long r5 = r9.fillOrder
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 != 0) goto L_0x002e
            byte[] r2 = r9.data
            byte r2 = r2[r4]
            if (r4 != r0) goto L_0x0019
        L_0x0016:
            r0 = r1
            r3 = r0
            goto L_0x0068
        L_0x0019:
            int r3 = r4 + 1
            if (r3 != r0) goto L_0x0023
            byte[] r0 = r9.data
            byte r0 = r0[r3]
        L_0x0021:
            r3 = r1
            goto L_0x0068
        L_0x0023:
            byte[] r0 = r9.data
            byte r0 = r0[r3]
            byte[] r3 = r9.data
            int r4 = r4 + 2
            byte r3 = r3[r4]
            goto L_0x0068
        L_0x002e:
            long r2 = r9.fillOrder
            r5 = 2
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x00b0
            byte[] r2 = flipTable
            byte[] r3 = r9.data
            byte r3 = r3[r4]
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r2 = r2[r3]
            if (r4 != r0) goto L_0x0043
            goto L_0x0016
        L_0x0043:
            int r3 = r4 + 1
            if (r3 != r0) goto L_0x0052
            byte[] r0 = flipTable
            byte[] r4 = r9.data
            byte r3 = r4[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r0 = r0[r3]
            goto L_0x0021
        L_0x0052:
            byte[] r0 = flipTable
            byte[] r5 = r9.data
            byte r3 = r5[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r0 = r0[r3]
            byte[] r3 = flipTable
            byte[] r5 = r9.data
            int r4 = r4 + 2
            byte r4 = r5[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r3 = r3[r4]
        L_0x0068:
            int r4 = r9.bitPointer
            r5 = 8
            int r4 = 8 - r4
            int r10 = r10 - r4
            if (r10 <= r5) goto L_0x0076
            int r6 = r10 + -8
            r7 = r6
            r6 = r5
            goto L_0x0078
        L_0x0076:
            r6 = r10
            r7 = r1
        L_0x0078:
            int r8 = r9.bytePointer
            int r8 = r8 + 1
            r9.bytePointer = r8
            int[] r8 = table1
            r4 = r8[r4]
            r2 = r2 & r4
            int r10 = r2 << r10
            int[] r2 = table2
            r2 = r2[r6]
            r0 = r0 & r2
            int r2 = 8 - r6
            int r0 = r0 >>> r2
            if (r7 == 0) goto L_0x00a1
            int r0 = r0 << r7
            int[] r1 = table2
            r1 = r1[r7]
            r1 = r1 & r3
            int r5 = r5 - r7
            int r1 = r1 >>> r5
            r0 = r0 | r1
            int r1 = r9.bytePointer
            int r1 = r1 + 1
            r9.bytePointer = r1
            r9.bitPointer = r7
            goto L_0x00ae
        L_0x00a1:
            if (r6 != r5) goto L_0x00ac
            r9.bitPointer = r1
            int r1 = r9.bytePointer
            int r1 = r1 + 1
            r9.bytePointer = r1
            goto L_0x00ae
        L_0x00ac:
            r9.bitPointer = r6
        L_0x00ae:
            r10 = r10 | r0
            return r10
        L_0x00b0:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            java.lang.String r0 = "tiff.fill.order.tag.must.be.either.1.or.2"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r1)
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.TIFFFaxDecoder.nextNBits(int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int nextLesserThan8Bits(int r9) {
        /*
            r8 = this;
            byte[] r0 = r8.data
            r1 = 0
            r2 = 1
            int r0 = r0.length
            int r0 = r0 + -1
            int r4 = r8.bytePointer
            long r5 = r8.fillOrder
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 != 0) goto L_0x001f
            byte[] r2 = r8.data
            byte r2 = r2[r4]
            if (r4 != r0) goto L_0x0018
        L_0x0016:
            r0 = r1
            goto L_0x004c
        L_0x0018:
            byte[] r0 = r8.data
            int r4 = r4 + 1
            byte r0 = r0[r4]
            goto L_0x004c
        L_0x001f:
            long r2 = r8.fillOrder
            r5 = 2
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0088
            boolean r2 = r8.recoverFromImageError
            if (r2 == 0) goto L_0x0033
            byte[] r2 = r8.data
            int r2 = r2.length
            if (r4 < r2) goto L_0x0033
            r0 = r1
            r2 = r0
            goto L_0x004c
        L_0x0033:
            byte[] r2 = flipTable
            byte[] r3 = r8.data
            byte r3 = r3[r4]
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r2 = r2[r3]
            if (r4 != r0) goto L_0x0040
            goto L_0x0016
        L_0x0040:
            byte[] r0 = flipTable
            byte[] r3 = r8.data
            int r4 = r4 + 1
            byte r3 = r3[r4]
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r0 = r0[r3]
        L_0x004c:
            int r3 = r8.bitPointer
            r4 = 8
            int r3 = 8 - r3
            int r5 = r9 - r3
            int r6 = r3 - r9
            if (r6 < 0) goto L_0x0070
            int[] r0 = table1
            r0 = r0[r3]
            r0 = r0 & r2
            int r0 = r0 >>> r6
            int r2 = r8.bitPointer
            int r2 = r2 + r9
            r8.bitPointer = r2
            int r9 = r8.bitPointer
            if (r9 != r4) goto L_0x0087
            r8.bitPointer = r1
            int r9 = r8.bytePointer
            int r9 = r9 + 1
            r8.bytePointer = r9
            goto L_0x0087
        L_0x0070:
            int[] r9 = table1
            r9 = r9[r3]
            r9 = r9 & r2
            int r1 = -r6
            int r9 = r9 << r1
            int[] r1 = table2
            r1 = r1[r5]
            r0 = r0 & r1
            int r4 = r4 - r5
            int r0 = r0 >>> r4
            r0 = r0 | r9
            int r9 = r8.bytePointer
            int r9 = r9 + 1
            r8.bytePointer = r9
            r8.bitPointer = r5
        L_0x0087:
            return r0
        L_0x0088:
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            java.lang.String r0 = "tiff.fill.order.tag.must.be.either.1.or.2"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r1)
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.TIFFFaxDecoder.nextLesserThan8Bits(int):int");
    }

    private void updatePointer(int i) {
        int i2 = this.bitPointer - i;
        if (i2 < 0) {
            this.bytePointer--;
            this.bitPointer = 8 + i2;
            return;
        }
        this.bitPointer = i2;
    }

    private boolean advancePointer() {
        if (this.bitPointer != 0) {
            this.bytePointer++;
            this.bitPointer = 0;
        }
        return true;
    }

    public void setRecoverFromImageError(boolean z) {
        this.recoverFromImageError = z;
    }
}
