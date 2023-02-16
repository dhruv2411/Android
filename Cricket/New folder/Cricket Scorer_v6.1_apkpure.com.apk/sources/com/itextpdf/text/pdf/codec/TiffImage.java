package com.itextpdf.text.pdf.codec;

import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

public class TiffImage {
    public static int getNumberOfPages(RandomAccessFileOrArray randomAccessFileOrArray) {
        try {
            return TIFFDirectory.getNumDirectories(randomAccessFileOrArray);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    static int getDpi(TIFFField tIFFField, int i) {
        if (tIFFField == null) {
            return 0;
        }
        long[] asRational = tIFFField.getAsRational(0);
        float f = ((float) asRational[0]) / ((float) asRational[1]);
        switch (i) {
            case 1:
            case 2:
                return (int) (((double) f) + 0.5d);
            case 3:
                return (int) ((((double) f) * 2.54d) + 0.5d);
            default:
                return 0;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0207, code lost:
        r44 = r3;
        r45 = r11;
        r2 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0257, code lost:
        r3 = r30;
        r1 = r31;
        r8 = r40;
     */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x02f3  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0315 A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008d A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0093 A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00aa A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b6 A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00bf A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c7 A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0115 A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x011a A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0132 A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0134 A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x013a A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01a0 A[ADDED_TO_REGION, Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01d6 A[Catch:{ RuntimeException -> 0x025f, InvalidImageException -> 0x021e, Exception -> 0x0319 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.itextpdf.text.Image getTiffImage(com.itextpdf.text.pdf.RandomAccessFileOrArray r46, boolean r47, int r48, boolean r49) {
        /*
            r1 = r46
            r2 = r47
            r3 = r48
            r4 = 1
            r5 = 0
            if (r3 >= r4) goto L_0x0018
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "the.page.number.must.be.gt.eq.1"
            java.lang.Object[] r3 = new java.lang.Object[r5]
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)
            r1.<init>(r2)
            throw r1
        L_0x0018:
            com.itextpdf.text.pdf.codec.TIFFDirectory r6 = new com.itextpdf.text.pdf.codec.TIFFDirectory     // Catch:{ Exception -> 0x0319 }
            int r3 = r3 - r4
            r6.<init>(r1, r3)     // Catch:{ Exception -> 0x0319 }
            r3 = 322(0x142, float:4.51E-43)
            boolean r3 = r6.isTagPresent(r3)     // Catch:{ Exception -> 0x0319 }
            if (r3 == 0) goto L_0x0034
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x0319 }
            java.lang.String r2 = "tiles.are.not.supported"
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x0319 }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ Exception -> 0x0319 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0319 }
            throw r1     // Catch:{ Exception -> 0x0319 }
        L_0x0034:
            r3 = 259(0x103, float:3.63E-43)
            long r7 = r6.getFieldAsLong(r3)     // Catch:{ Exception -> 0x0319 }
            int r3 = (int) r7     // Catch:{ Exception -> 0x0319 }
            r7 = 32771(0x8003, float:4.5922E-41)
            if (r3 == r7) goto L_0x0048
            switch(r3) {
                case 2: goto L_0x0048;
                case 3: goto L_0x0048;
                case 4: goto L_0x0048;
                default: goto L_0x0043;
            }     // Catch:{ Exception -> 0x0319 }
        L_0x0043:
            com.itextpdf.text.Image r1 = getTiffImageColor(r6, r1)     // Catch:{ Exception -> 0x0319 }
            return r1
        L_0x0048:
            r8 = 274(0x112, float:3.84E-43)
            boolean r9 = r6.isTagPresent(r8)     // Catch:{ Exception -> 0x0319 }
            r10 = 8
            r11 = 5
            if (r9 == 0) goto L_0x0076
            long r8 = r6.getFieldAsLong(r8)     // Catch:{ Exception -> 0x0319 }
            int r8 = (int) r8     // Catch:{ Exception -> 0x0319 }
            r9 = 3
            if (r8 == r9) goto L_0x0072
            r9 = 4
            if (r8 != r9) goto L_0x005f
            goto L_0x0072
        L_0x005f:
            if (r8 == r11) goto L_0x006e
            if (r8 != r10) goto L_0x0064
            goto L_0x006e
        L_0x0064:
            r9 = 6
            if (r8 == r9) goto L_0x006a
            r9 = 7
            if (r8 != r9) goto L_0x0076
        L_0x006a:
            r8 = -1077342245(0xffffffffbfc90fdb, float:-1.5707964)
            goto L_0x0077
        L_0x006e:
            r8 = 1070141403(0x3fc90fdb, float:1.5707964)
            goto L_0x0077
        L_0x0072:
            r8 = 1078530011(0x40490fdb, float:3.1415927)
            goto L_0x0077
        L_0x0076:
            r8 = 0
        L_0x0077:
            r9 = 257(0x101, float:3.6E-43)
            long r13 = r6.getFieldAsLong(r9)     // Catch:{ Exception -> 0x0319 }
            int r13 = (int) r13     // Catch:{ Exception -> 0x0319 }
            r14 = 256(0x100, float:3.59E-43)
            long r11 = r6.getFieldAsLong(r14)     // Catch:{ Exception -> 0x0319 }
            int r15 = (int) r11     // Catch:{ Exception -> 0x0319 }
            r11 = 296(0x128, float:4.15E-43)
            boolean r12 = r6.isTagPresent(r11)     // Catch:{ Exception -> 0x0319 }
            if (r12 == 0) goto L_0x0093
            long r11 = r6.getFieldAsLong(r11)     // Catch:{ Exception -> 0x0319 }
            int r11 = (int) r11     // Catch:{ Exception -> 0x0319 }
            goto L_0x0094
        L_0x0093:
            r11 = 2
        L_0x0094:
            r12 = 282(0x11a, float:3.95E-43)
            com.itextpdf.text.pdf.codec.TIFFField r12 = r6.getField(r12)     // Catch:{ Exception -> 0x0319 }
            int r12 = getDpi(r12, r11)     // Catch:{ Exception -> 0x0319 }
            r9 = 283(0x11b, float:3.97E-43)
            com.itextpdf.text.pdf.codec.TIFFField r9 = r6.getField(r9)     // Catch:{ Exception -> 0x0319 }
            int r9 = getDpi(r9, r11)     // Catch:{ Exception -> 0x0319 }
            if (r11 != r4) goto L_0x00b6
            if (r9 == 0) goto L_0x00b1
            float r11 = (float) r12     // Catch:{ Exception -> 0x0319 }
            float r9 = (float) r9     // Catch:{ Exception -> 0x0319 }
            float r11 = r11 / r9
            r12 = r11
            goto L_0x00b2
        L_0x00b1:
            r12 = 0
        L_0x00b2:
            r9 = r5
            r11 = r12
            r12 = r9
            goto L_0x00b7
        L_0x00b6:
            r11 = 0
        L_0x00b7:
            r14 = 278(0x116, float:3.9E-43)
            boolean r16 = r6.isTagPresent(r14)     // Catch:{ Exception -> 0x0319 }
            if (r16 == 0) goto L_0x00c7
            r24 = r11
            long r10 = r6.getFieldAsLong(r14)     // Catch:{ Exception -> 0x0319 }
            int r10 = (int) r10     // Catch:{ Exception -> 0x0319 }
            goto L_0x00ca
        L_0x00c7:
            r24 = r11
            r10 = r13
        L_0x00ca:
            if (r10 <= 0) goto L_0x00ce
            if (r10 <= r13) goto L_0x00cf
        L_0x00ce:
            r10 = r13
        L_0x00cf:
            r11 = 273(0x111, float:3.83E-43)
            long[] r11 = getArrayLongShort(r6, r11)     // Catch:{ Exception -> 0x0319 }
            r14 = 279(0x117, float:3.91E-43)
            long[] r14 = getArrayLongShort(r6, r14)     // Catch:{ Exception -> 0x0319 }
            r16 = 0
            if (r14 == 0) goto L_0x00f6
            int r7 = r14.length     // Catch:{ Exception -> 0x0319 }
            if (r7 != r4) goto L_0x0109
            r18 = r14[r5]     // Catch:{ Exception -> 0x0319 }
            int r7 = (r18 > r16 ? 1 : (r18 == r16 ? 0 : -1))
            if (r7 == 0) goto L_0x00f6
            r18 = r14[r5]     // Catch:{ Exception -> 0x0319 }
            r26 = r11[r5]     // Catch:{ Exception -> 0x0319 }
            long r28 = r18 + r26
            long r18 = r46.length()     // Catch:{ Exception -> 0x0319 }
            int r7 = (r28 > r18 ? 1 : (r28 == r18 ? 0 : -1))
            if (r7 <= 0) goto L_0x0109
        L_0x00f6:
            if (r13 != r10) goto L_0x0109
            long[] r14 = new long[r4]     // Catch:{ Exception -> 0x0319 }
            long r18 = r46.length()     // Catch:{ Exception -> 0x0319 }
            r30 = r8
            r7 = r11[r5]     // Catch:{ Exception -> 0x0319 }
            int r7 = (int) r7     // Catch:{ Exception -> 0x0319 }
            long r7 = (long) r7     // Catch:{ Exception -> 0x0319 }
            long r26 = r18 - r7
            r14[r5] = r26     // Catch:{ Exception -> 0x0319 }
            goto L_0x010b
        L_0x0109:
            r30 = r8
        L_0x010b:
            r7 = 266(0x10a, float:3.73E-43)
            com.itextpdf.text.pdf.codec.TIFFField r7 = r6.getField(r7)     // Catch:{ Exception -> 0x0319 }
            r18 = 1
            if (r7 == 0) goto L_0x011a
            long r7 = r7.getAsLong(r5)     // Catch:{ Exception -> 0x0319 }
            goto L_0x011c
        L_0x011a:
            r7 = r18
        L_0x011c:
            r26 = 2
            int r20 = (r7 > r26 ? 1 : (r7 == r26 ? 0 : -1))
            r4 = 262(0x106, float:3.67E-43)
            boolean r4 = r6.isTagPresent(r4)     // Catch:{ Exception -> 0x0319 }
            if (r4 == 0) goto L_0x0134
            r4 = 262(0x106, float:3.67E-43)
            long r26 = r6.getFieldAsLong(r4)     // Catch:{ Exception -> 0x0319 }
            int r4 = (r26 > r18 ? 1 : (r26 == r18 ? 0 : -1))
            if (r4 != 0) goto L_0x0134
            r4 = 1
            goto L_0x0135
        L_0x0134:
            r4 = r5
        L_0x0135:
            r5 = 32771(0x8003, float:4.5922E-41)
            if (r3 == r5) goto L_0x0194
            switch(r3) {
                case 2: goto L_0x0194;
                case 3: goto L_0x0165;
                case 4: goto L_0x0146;
                default: goto L_0x013d;
            }     // Catch:{ Exception -> 0x0319 }
        L_0x013d:
            r19 = r4
            r31 = r9
            r21 = r16
            r18 = 0
            goto L_0x019e
        L_0x0146:
            r5 = 293(0x125, float:4.1E-43)
            com.itextpdf.text.pdf.codec.TIFFField r5 = r6.getField(r5)     // Catch:{ Exception -> 0x0319 }
            if (r5 == 0) goto L_0x015c
            r31 = r9
            r9 = 0
            long r18 = r5.getAsLong(r9)     // Catch:{ Exception -> 0x0319 }
            r21 = r18
            r18 = 256(0x100, float:3.59E-43)
            r19 = r4
            goto L_0x019e
        L_0x015c:
            r31 = r9
            r19 = r4
            r21 = r16
            r18 = 256(0x100, float:3.59E-43)
            goto L_0x019e
        L_0x0165:
            r31 = r9
            r4 = r4 | 12
            r5 = 292(0x124, float:4.09E-43)
            com.itextpdf.text.pdf.codec.TIFFField r5 = r6.getField(r5)     // Catch:{ Exception -> 0x0319 }
            if (r5 == 0) goto L_0x0198
            r9 = 0
            long r26 = r5.getAsLong(r9)     // Catch:{ Exception -> 0x0319 }
            long r28 = r26 & r18
            int r5 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r5 == 0) goto L_0x017f
            r5 = 258(0x102, float:3.62E-43)
            goto L_0x0181
        L_0x017f:
            r5 = 257(0x101, float:3.6E-43)
        L_0x0181:
            r18 = 4
            long r21 = r26 & r18
            int r9 = (r21 > r16 ? 1 : (r21 == r16 ? 0 : -1))
            if (r9 == 0) goto L_0x018b
            r4 = r4 | 2
        L_0x018b:
            r19 = r4
            r18 = r5
            r21 = r16
            r16 = r26
            goto L_0x019e
        L_0x0194:
            r31 = r9
            r4 = r4 | 10
        L_0x0198:
            r19 = r4
            r21 = r16
            r18 = 257(0x101, float:3.6E-43)
        L_0x019e:
            if (r49 == 0) goto L_0x01ca
            if (r10 != r13) goto L_0x01ca
            r4 = 0
            r2 = r14[r4]     // Catch:{ Exception -> 0x0319 }
            int r2 = (int) r2     // Catch:{ Exception -> 0x0319 }
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0319 }
            r3 = r11[r4]     // Catch:{ Exception -> 0x0319 }
            r1.seek(r3)     // Catch:{ Exception -> 0x0319 }
            r1.readFully(r2)     // Catch:{ Exception -> 0x0319 }
            r17 = 0
            r16 = r13
            r20 = r2
            com.itextpdf.text.Image r1 = com.itextpdf.text.Image.getInstance((int) r15, (int) r16, (boolean) r17, (int) r18, (int) r19, (byte[]) r20)     // Catch:{ Exception -> 0x0319 }
            r2 = 1
            r1.setInverted(r2)     // Catch:{ Exception -> 0x0319 }
            r4 = r1
            r39 = r6
            r8 = r12
            r2 = r24
            r3 = r30
            r1 = r31
            goto L_0x02e2
        L_0x01ca:
            com.itextpdf.text.pdf.codec.CCITTG4Encoder r4 = new com.itextpdf.text.pdf.codec.CCITTG4Encoder     // Catch:{ Exception -> 0x0319 }
            r4.<init>(r15)     // Catch:{ Exception -> 0x0319 }
            r39 = r6
            r9 = r13
            r5 = 0
        L_0x01d3:
            int r6 = r11.length     // Catch:{ Exception -> 0x0319 }
            if (r5 >= r6) goto L_0x02c8
            r40 = r12
            r41 = r13
            r12 = r14[r5]     // Catch:{ Exception -> 0x0319 }
            int r6 = (int) r12     // Catch:{ Exception -> 0x0319 }
            byte[] r6 = new byte[r6]     // Catch:{ Exception -> 0x0319 }
            r12 = r11[r5]     // Catch:{ Exception -> 0x0319 }
            r1.seek(r12)     // Catch:{ Exception -> 0x0319 }
            r1.readFully(r6)     // Catch:{ Exception -> 0x0319 }
            int r12 = java.lang.Math.min(r10, r9)     // Catch:{ Exception -> 0x0319 }
            com.itextpdf.text.pdf.codec.TIFFFaxDecoder r13 = new com.itextpdf.text.pdf.codec.TIFFFaxDecoder     // Catch:{ Exception -> 0x0319 }
            r13.<init>(r7, r15, r12)     // Catch:{ Exception -> 0x0319 }
            r13.setRecoverFromImageError(r2)     // Catch:{ Exception -> 0x0319 }
            int r20 = r15 + 7
            r23 = 8
            int r20 = r20 / 8
            r42 = r7
            int r7 = r20 * r12
            byte[] r7 = new byte[r7]     // Catch:{ Exception -> 0x0319 }
            r8 = 32771(0x8003, float:4.5922E-41)
            if (r3 == r8) goto L_0x029d
            switch(r3) {
                case 2: goto L_0x029d;
                case 3: goto L_0x0226;
                case 4: goto L_0x020e;
                default: goto L_0x0207;
            }
        L_0x0207:
            r44 = r3
            r45 = r11
            r2 = r24
            goto L_0x0257
        L_0x020e:
            r35 = 0
            r32 = r13
            r33 = r7
            r34 = r6
            r36 = r12
            r37 = r21
            r32.decodeT6(r33, r34, r35, r36, r37)     // Catch:{ InvalidImageException -> 0x021e }
            goto L_0x0222
        L_0x021e:
            r0 = move-exception
            if (r2 != 0) goto L_0x0222
            throw r0     // Catch:{ Exception -> 0x0319 }
        L_0x0222:
            r4.fax4Encode(r7, r12)     // Catch:{ Exception -> 0x0319 }
            goto L_0x0207
        L_0x0226:
            r35 = 0
            r32 = r13
            r33 = r7
            r34 = r6
            r36 = r12
            r37 = r16
            r32.decode2D(r33, r34, r35, r36, r37)     // Catch:{ RuntimeException -> 0x0238 }
            r27 = r16
            goto L_0x024c
        L_0x0238:
            r0 = move-exception
            r25 = 4
            long r27 = r16 ^ r25
            r35 = 0
            r32 = r13
            r33 = r7
            r34 = r6
            r36 = r12
            r37 = r27
            r32.decode2D(r33, r34, r35, r36, r37)     // Catch:{ RuntimeException -> 0x025f }
        L_0x024c:
            r4.fax4Encode(r7, r12)     // Catch:{ Exception -> 0x0319 }
            r44 = r3
            r45 = r11
            r2 = r24
            r16 = r27
        L_0x0257:
            r3 = r30
            r1 = r31
            r8 = r40
            r11 = 0
            goto L_0x02b0
        L_0x025f:
            if (r2 != 0) goto L_0x0262
            throw r0     // Catch:{ Exception -> 0x0319 }
        L_0x0262:
            r2 = 1
            if (r10 != r2) goto L_0x0266
            throw r0     // Catch:{ Exception -> 0x0319 }
        L_0x0266:
            r2 = 0
            r3 = r14[r2]     // Catch:{ Exception -> 0x0319 }
            int r3 = (int) r3     // Catch:{ Exception -> 0x0319 }
            byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x0319 }
            r4 = r11[r2]     // Catch:{ Exception -> 0x0319 }
            r1.seek(r4)     // Catch:{ Exception -> 0x0319 }
            r1.readFully(r3)     // Catch:{ Exception -> 0x0319 }
            r17 = 0
            r16 = r41
            r20 = r3
            com.itextpdf.text.Image r1 = com.itextpdf.text.Image.getInstance((int) r15, (int) r16, (boolean) r17, (int) r18, (int) r19, (byte[]) r20)     // Catch:{ Exception -> 0x0319 }
            r2 = 1
            r1.setInverted(r2)     // Catch:{ Exception -> 0x0319 }
            r3 = r31
            r2 = r40
            r1.setDpi(r2, r3)     // Catch:{ Exception -> 0x0319 }
            r2 = r24
            r1.setXYRatio(r2)     // Catch:{ Exception -> 0x0319 }
            r2 = 5
            r1.setOriginalType(r2)     // Catch:{ Exception -> 0x0319 }
            r2 = 0
            int r2 = (r30 > r2 ? 1 : (r30 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x029c
            r2 = r30
            r1.setInitialRotation(r2)     // Catch:{ Exception -> 0x0319 }
        L_0x029c:
            return r1
        L_0x029d:
            r44 = r3
            r2 = r24
            r3 = r30
            r1 = r31
            r8 = r40
            r45 = r11
            r11 = 0
            r13.decode1D(r7, r6, r11, r12)     // Catch:{ Exception -> 0x0319 }
            r4.fax4Encode(r7, r12)     // Catch:{ Exception -> 0x0319 }
        L_0x02b0:
            int r9 = r9 - r10
            int r5 = r5 + 1
            r31 = r1
            r24 = r2
            r30 = r3
            r12 = r8
            r13 = r41
            r7 = r42
            r3 = r44
            r11 = r45
            r1 = r46
            r2 = r47
            goto L_0x01d3
        L_0x02c8:
            r8 = r12
            r41 = r13
            r2 = r24
            r3 = r30
            r1 = r31
            byte[] r20 = r4.close()     // Catch:{ Exception -> 0x0319 }
            r17 = 0
            r18 = 256(0x100, float:3.59E-43)
            r4 = 1
            r19 = r19 & 1
            r16 = r41
            com.itextpdf.text.Image r4 = com.itextpdf.text.Image.getInstance((int) r15, (int) r16, (boolean) r17, (int) r18, (int) r19, (byte[]) r20)     // Catch:{ Exception -> 0x0319 }
        L_0x02e2:
            r4.setDpi(r8, r1)     // Catch:{ Exception -> 0x0319 }
            r4.setXYRatio(r2)     // Catch:{ Exception -> 0x0319 }
            r1 = 34675(0x8773, float:4.859E-41)
            r2 = r39
            boolean r1 = r2.isTagPresent(r1)     // Catch:{ Exception -> 0x0319 }
            if (r1 == 0) goto L_0x030c
            r1 = 34675(0x8773, float:4.859E-41)
            com.itextpdf.text.pdf.codec.TIFFField r1 = r2.getField(r1)     // Catch:{ RuntimeException -> 0x030c }
            byte[] r1 = r1.getAsBytes()     // Catch:{ RuntimeException -> 0x030c }
            com.itextpdf.text.pdf.ICC_Profile r1 = com.itextpdf.text.pdf.ICC_Profile.getInstance((byte[]) r1)     // Catch:{ RuntimeException -> 0x030c }
            int r2 = r1.getNumComponents()     // Catch:{ RuntimeException -> 0x030c }
            r5 = 1
            if (r2 != r5) goto L_0x030c
            r4.tagICC(r1)     // Catch:{ RuntimeException -> 0x030c }
        L_0x030c:
            r1 = 5
            r4.setOriginalType(r1)     // Catch:{ Exception -> 0x0319 }
            r1 = 0
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0318
            r4.setInitialRotation(r3)     // Catch:{ Exception -> 0x0319 }
        L_0x0318:
            return r4
        L_0x0319:
            r0 = move-exception
            r1 = r0
            com.itextpdf.text.ExceptionConverter r2 = new com.itextpdf.text.ExceptionConverter
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.TiffImage.getTiffImage(com.itextpdf.text.pdf.RandomAccessFileOrArray, boolean, int, boolean):com.itextpdf.text.Image");
    }

    public static Image getTiffImage(RandomAccessFileOrArray randomAccessFileOrArray, boolean z, int i) {
        return getTiffImage(randomAccessFileOrArray, z, i, false);
    }

    public static Image getTiffImage(RandomAccessFileOrArray randomAccessFileOrArray, int i) {
        return getTiffImage(randomAccessFileOrArray, i, false);
    }

    public static Image getTiffImage(RandomAccessFileOrArray randomAccessFileOrArray, int i, boolean z) {
        return getTiffImage(randomAccessFileOrArray, false, i, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0335, code lost:
        if (r3 != 32946) goto L_0x0337;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01bf A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01c3 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01c9 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01cc A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x01da A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x01e0 A[ADDED_TO_REGION, Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0200 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x020b A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0214 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0269 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x03e6  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x0408 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x04a1 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x04aa A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x04b1 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0085 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0093 A[Catch:{ Exception -> 0x04cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0191 A[Catch:{ Exception -> 0x04cd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.itextpdf.text.Image getTiffImageColor(com.itextpdf.text.pdf.codec.TIFFDirectory r42, com.itextpdf.text.pdf.RandomAccessFileOrArray r43) {
        /*
            r1 = r42
            r2 = r43
            r3 = 259(0x103, float:3.63E-43)
            long r3 = r1.getFieldAsLong(r3)     // Catch:{ Exception -> 0x04cd }
            int r3 = (int) r3     // Catch:{ Exception -> 0x04cd }
            r4 = 32773(0x8005, float:4.5925E-41)
            r5 = 32946(0x80b2, float:4.6167E-41)
            r6 = 1
            if (r3 == r6) goto L_0x0027
            if (r3 == r4) goto L_0x0027
            if (r3 == r5) goto L_0x0027
            switch(r3) {
                case 5: goto L_0x0027;
                case 6: goto L_0x0027;
                case 7: goto L_0x0027;
                case 8: goto L_0x0027;
                default: goto L_0x001b;
            }     // Catch:{ Exception -> 0x04cd }
        L_0x001b:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = "the.compression.1.is.not.supported"
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (int) r3)     // Catch:{ Exception -> 0x04cd }
            r1.<init>(r2)     // Catch:{ Exception -> 0x04cd }
            throw r1     // Catch:{ Exception -> 0x04cd }
        L_0x0027:
            r7 = 262(0x106, float:3.67E-43)
            long r7 = r1.getFieldAsLong(r7)     // Catch:{ Exception -> 0x04cd }
            int r7 = (int) r7     // Catch:{ Exception -> 0x04cd }
            r8 = 6
            r9 = 7
            r10 = 5
            if (r7 == r10) goto L_0x0046
            switch(r7) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0046;
                case 2: goto L_0x0046;
                case 3: goto L_0x0046;
                default: goto L_0x0036;
            }     // Catch:{ Exception -> 0x04cd }
        L_0x0036:
            if (r3 == r8) goto L_0x0046
            if (r3 == r9) goto L_0x0046
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = "the.photometric.1.is.not.supported"
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (int) r7)     // Catch:{ Exception -> 0x04cd }
            r1.<init>(r2)     // Catch:{ Exception -> 0x04cd }
            throw r1     // Catch:{ Exception -> 0x04cd }
        L_0x0046:
            r11 = 274(0x112, float:3.84E-43)
            boolean r12 = r1.isTagPresent(r11)     // Catch:{ Exception -> 0x04cd }
            r13 = 4
            r15 = 3
            r14 = 8
            if (r12 == 0) goto L_0x0071
            long r11 = r1.getFieldAsLong(r11)     // Catch:{ Exception -> 0x04cd }
            int r11 = (int) r11     // Catch:{ Exception -> 0x04cd }
            if (r11 == r15) goto L_0x006d
            if (r11 != r13) goto L_0x005c
            goto L_0x006d
        L_0x005c:
            if (r11 == r10) goto L_0x0069
            if (r11 != r14) goto L_0x0061
            goto L_0x0069
        L_0x0061:
            if (r11 == r8) goto L_0x0065
            if (r11 != r9) goto L_0x0071
        L_0x0065:
            r11 = -1077342245(0xffffffffbfc90fdb, float:-1.5707964)
            goto L_0x0072
        L_0x0069:
            r11 = 1070141403(0x3fc90fdb, float:1.5707964)
            goto L_0x0072
        L_0x006d:
            r11 = 1078530011(0x40490fdb, float:3.1415927)
            goto L_0x0072
        L_0x0071:
            r11 = 0
        L_0x0072:
            r12 = 284(0x11c, float:3.98E-43)
            boolean r16 = r1.isTagPresent(r12)     // Catch:{ Exception -> 0x04cd }
            r4 = 0
            if (r16 == 0) goto L_0x0093
            long r17 = r1.getFieldAsLong(r12)     // Catch:{ Exception -> 0x04cd }
            r19 = 2
            int r12 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r12 != 0) goto L_0x0093
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = "planar.images.are.not.supported"
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ Exception -> 0x04cd }
            r1.<init>(r2)     // Catch:{ Exception -> 0x04cd }
            throw r1     // Catch:{ Exception -> 0x04cd }
        L_0x0093:
            r12 = 338(0x152, float:4.74E-43)
            boolean r12 = r1.isTagPresent(r12)     // Catch:{ Exception -> 0x04cd }
            r9 = 277(0x115, float:3.88E-43)
            boolean r9 = r1.isTagPresent(r9)     // Catch:{ Exception -> 0x04cd }
            if (r9 == 0) goto L_0x00a9
            r9 = 277(0x115, float:3.88E-43)
            long r8 = r1.getFieldAsLong(r9)     // Catch:{ Exception -> 0x04cd }
            int r8 = (int) r8     // Catch:{ Exception -> 0x04cd }
            goto L_0x00aa
        L_0x00a9:
            r8 = r6
        L_0x00aa:
            r9 = 258(0x102, float:3.62E-43)
            boolean r9 = r1.isTagPresent(r9)     // Catch:{ Exception -> 0x04cd }
            if (r9 == 0) goto L_0x00bc
            r9 = 258(0x102, float:3.62E-43)
            r28 = r11
            long r10 = r1.getFieldAsLong(r9)     // Catch:{ Exception -> 0x04cd }
            int r9 = (int) r10     // Catch:{ Exception -> 0x04cd }
            goto L_0x00bf
        L_0x00bc:
            r28 = r11
            r9 = r6
        L_0x00bf:
            if (r9 == r13) goto L_0x00d2
            if (r9 == r14) goto L_0x00d2
            switch(r9) {
                case 1: goto L_0x00d2;
                case 2: goto L_0x00d2;
                default: goto L_0x00c6;
            }     // Catch:{ Exception -> 0x04cd }
        L_0x00c6:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = "bits.per.sample.1.is.not.supported"
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (int) r9)     // Catch:{ Exception -> 0x04cd }
            r1.<init>(r2)     // Catch:{ Exception -> 0x04cd }
            throw r1     // Catch:{ Exception -> 0x04cd }
        L_0x00d2:
            r10 = 257(0x101, float:3.6E-43)
            long r10 = r1.getFieldAsLong(r10)     // Catch:{ Exception -> 0x04cd }
            int r10 = (int) r10     // Catch:{ Exception -> 0x04cd }
            r11 = 256(0x100, float:3.59E-43)
            long r14 = r1.getFieldAsLong(r11)     // Catch:{ Exception -> 0x04cd }
            int r11 = (int) r14     // Catch:{ Exception -> 0x04cd }
            r13 = 296(0x128, float:4.15E-43)
            boolean r13 = r1.isTagPresent(r13)     // Catch:{ Exception -> 0x04cd }
            r14 = 2
            if (r13 == 0) goto L_0x00f1
            r13 = 296(0x128, float:4.15E-43)
            long r5 = r1.getFieldAsLong(r13)     // Catch:{ Exception -> 0x04cd }
            int r5 = (int) r5     // Catch:{ Exception -> 0x04cd }
            goto L_0x00f2
        L_0x00f1:
            r5 = r14
        L_0x00f2:
            r6 = 282(0x11a, float:3.95E-43)
            com.itextpdf.text.pdf.codec.TIFFField r6 = r1.getField(r6)     // Catch:{ Exception -> 0x04cd }
            int r6 = getDpi(r6, r5)     // Catch:{ Exception -> 0x04cd }
            r13 = 283(0x11b, float:3.97E-43)
            com.itextpdf.text.pdf.codec.TIFFField r13 = r1.getField(r13)     // Catch:{ Exception -> 0x04cd }
            int r5 = getDpi(r13, r5)     // Catch:{ Exception -> 0x04cd }
            r13 = 266(0x10a, float:3.73E-43)
            com.itextpdf.text.pdf.codec.TIFFField r13 = r1.getField(r13)     // Catch:{ Exception -> 0x04cd }
            if (r13 == 0) goto L_0x0113
            int r13 = r13.getAsInt(r4)     // Catch:{ Exception -> 0x04cd }
            goto L_0x0114
        L_0x0113:
            r13 = 1
        L_0x0114:
            if (r13 != r14) goto L_0x0118
            r13 = 1
            goto L_0x0119
        L_0x0118:
            r13 = r4
        L_0x0119:
            r15 = 278(0x116, float:3.9E-43)
            boolean r15 = r1.isTagPresent(r15)     // Catch:{ Exception -> 0x04cd }
            if (r15 == 0) goto L_0x0129
            r15 = 278(0x116, float:3.9E-43)
            long r14 = r1.getFieldAsLong(r15)     // Catch:{ Exception -> 0x04cd }
            int r14 = (int) r14     // Catch:{ Exception -> 0x04cd }
            goto L_0x012a
        L_0x0129:
            r14 = r10
        L_0x012a:
            if (r14 <= 0) goto L_0x012e
            if (r14 <= r10) goto L_0x012f
        L_0x012e:
            r14 = r10
        L_0x012f:
            r15 = 273(0x111, float:3.83E-43)
            long[] r15 = getArrayLongShort(r1, r15)     // Catch:{ Exception -> 0x04cd }
            r4 = 279(0x117, float:3.91E-43)
            long[] r4 = getArrayLongShort(r1, r4)     // Catch:{ Exception -> 0x04cd }
            if (r4 == 0) goto L_0x015d
            r29 = r5
            int r5 = r4.length     // Catch:{ Exception -> 0x04cd }
            r30 = r6
            r6 = 1
            if (r5 != r6) goto L_0x0179
            r5 = 0
            r17 = r4[r5]     // Catch:{ Exception -> 0x04cd }
            r19 = 0
            int r6 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r6 == 0) goto L_0x0161
            r17 = r4[r5]     // Catch:{ Exception -> 0x04cd }
            r19 = r15[r5]     // Catch:{ Exception -> 0x04cd }
            long r5 = r17 + r19
            long r17 = r43.length()     // Catch:{ Exception -> 0x04cd }
            int r16 = (r5 > r17 ? 1 : (r5 == r17 ? 0 : -1))
            if (r16 <= 0) goto L_0x0179
            goto L_0x0161
        L_0x015d:
            r29 = r5
            r30 = r6
        L_0x0161:
            if (r10 != r14) goto L_0x0179
            r5 = 1
            long[] r4 = new long[r5]     // Catch:{ Exception -> 0x04cd }
            long r5 = r43.length()     // Catch:{ Exception -> 0x04cd }
            r31 = r13
            r32 = r14
            r16 = 0
            r13 = r15[r16]     // Catch:{ Exception -> 0x04cd }
            int r13 = (int) r13     // Catch:{ Exception -> 0x04cd }
            long r13 = (long) r13     // Catch:{ Exception -> 0x04cd }
            long r17 = r5 - r13
            r4[r16] = r17     // Catch:{ Exception -> 0x04cd }
            goto L_0x017d
        L_0x0179:
            r31 = r13
            r32 = r14
        L_0x017d:
            r5 = 5
            if (r3 == r5) goto L_0x0189
            r5 = 32946(0x80b2, float:4.6167E-41)
            if (r3 == r5) goto L_0x0189
            r5 = 8
            if (r3 != r5) goto L_0x01bf
        L_0x0189:
            r5 = 317(0x13d, float:4.44E-43)
            com.itextpdf.text.pdf.codec.TIFFField r5 = r1.getField(r5)     // Catch:{ Exception -> 0x04cd }
            if (r5 == 0) goto L_0x01bf
            r6 = 0
            int r5 = r5.getAsInt(r6)     // Catch:{ Exception -> 0x04cd }
            r13 = 1
            if (r5 == r13) goto L_0x01ac
            r13 = 2
            if (r5 == r13) goto L_0x01aa
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = "illegal.value.for.predictor.in.tiff.file"
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ Exception -> 0x04cd }
            r1.<init>(r2)     // Catch:{ Exception -> 0x04cd }
            throw r1     // Catch:{ Exception -> 0x04cd }
        L_0x01aa:
            r6 = r13
            goto L_0x01ad
        L_0x01ac:
            r6 = 2
        L_0x01ad:
            if (r5 != r6) goto L_0x01c0
            r6 = 8
            if (r9 == r6) goto L_0x01c0
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = "1.bit.samples.are.not.supported.for.horizontal.differencing.predictor"
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (int) r9)     // Catch:{ Exception -> 0x04cd }
            r1.<init>(r2)     // Catch:{ Exception -> 0x04cd }
            throw r1     // Catch:{ Exception -> 0x04cd }
        L_0x01bf:
            r5 = 1
        L_0x01c0:
            r13 = 5
            if (r3 != r13) goto L_0x01c9
            com.itextpdf.text.pdf.codec.TIFFLZWDecoder r13 = new com.itextpdf.text.pdf.codec.TIFFLZWDecoder     // Catch:{ Exception -> 0x04cd }
            r13.<init>(r11, r5, r8)     // Catch:{ Exception -> 0x04cd }
            goto L_0x01ca
        L_0x01c9:
            r13 = 0
        L_0x01ca:
            if (r12 <= 0) goto L_0x01da
            java.io.ByteArrayOutputStream r14 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x04cd }
            r14.<init>()     // Catch:{ Exception -> 0x04cd }
            java.util.zip.DeflaterOutputStream r6 = new java.util.zip.DeflaterOutputStream     // Catch:{ Exception -> 0x04cd }
            r6.<init>(r14)     // Catch:{ Exception -> 0x04cd }
            r33 = r14
            r14 = 1
            goto L_0x01de
        L_0x01da:
            r6 = 0
            r14 = 1
            r33 = 0
        L_0x01de:
            if (r9 != r14) goto L_0x01f3
            if (r8 != r14) goto L_0x01f3
            r14 = 3
            if (r7 == r14) goto L_0x01f3
            com.itextpdf.text.pdf.codec.CCITTG4Encoder r14 = new com.itextpdf.text.pdf.codec.CCITTG4Encoder     // Catch:{ Exception -> 0x04cd }
            r14.<init>(r11)     // Catch:{ Exception -> 0x04cd }
            r36 = r6
            r34 = r10
            r6 = 6
            r10 = 0
            r35 = 0
            goto L_0x0212
        L_0x01f3:
            java.io.ByteArrayOutputStream r14 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x04cd }
            r14.<init>()     // Catch:{ Exception -> 0x04cd }
            r34 = r10
            r10 = 6
            if (r3 == r10) goto L_0x020b
            r10 = 7
            if (r3 == r10) goto L_0x020b
            java.util.zip.DeflaterOutputStream r10 = new java.util.zip.DeflaterOutputStream     // Catch:{ Exception -> 0x04cd }
            r10.<init>(r14)     // Catch:{ Exception -> 0x04cd }
            r36 = r6
            r35 = r14
            r6 = 6
            goto L_0x0211
        L_0x020b:
            r36 = r6
            r35 = r14
            r6 = 6
            r10 = 0
        L_0x0211:
            r14 = 0
        L_0x0212:
            if (r3 != r6) goto L_0x0269
            r5 = 513(0x201, float:7.19E-43)
            boolean r5 = r1.isTagPresent(r5)     // Catch:{ Exception -> 0x04cd }
            if (r5 != 0) goto L_0x022b
            java.io.IOException r1 = new java.io.IOException     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = "missing.tag.s.for.ojpeg.compression"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ Exception -> 0x04cd }
            r1.<init>(r2)     // Catch:{ Exception -> 0x04cd }
            throw r1     // Catch:{ Exception -> 0x04cd }
        L_0x022b:
            r5 = 513(0x201, float:7.19E-43)
            long r5 = r1.getFieldAsLong(r5)     // Catch:{ Exception -> 0x04cd }
            int r5 = (int) r5     // Catch:{ Exception -> 0x04cd }
            long r13 = r43.length()     // Catch:{ Exception -> 0x04cd }
            int r6 = (int) r13     // Catch:{ Exception -> 0x04cd }
            int r6 = r6 - r5
            r10 = 514(0x202, float:7.2E-43)
            boolean r10 = r1.isTagPresent(r10)     // Catch:{ Exception -> 0x04cd }
            if (r10 == 0) goto L_0x024c
            r6 = 514(0x202, float:7.2E-43)
            long r13 = r1.getFieldAsLong(r6)     // Catch:{ Exception -> 0x04cd }
            int r6 = (int) r13     // Catch:{ Exception -> 0x04cd }
            r10 = 0
            r13 = r4[r10]     // Catch:{ Exception -> 0x04cd }
            int r4 = (int) r13     // Catch:{ Exception -> 0x04cd }
            int r6 = r6 + r4
        L_0x024c:
            long r13 = r43.length()     // Catch:{ Exception -> 0x04cd }
            int r4 = (int) r13     // Catch:{ Exception -> 0x04cd }
            int r4 = r4 - r5
            int r4 = java.lang.Math.min(r6, r4)     // Catch:{ Exception -> 0x04cd }
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x04cd }
            long r5 = (long) r5     // Catch:{ Exception -> 0x04cd }
            r2.seek(r5)     // Catch:{ Exception -> 0x04cd }
            r2.readFully(r4)     // Catch:{ Exception -> 0x04cd }
            com.itextpdf.text.Jpeg r2 = new com.itextpdf.text.Jpeg     // Catch:{ Exception -> 0x04cd }
            r2.<init>((byte[]) r4)     // Catch:{ Exception -> 0x04cd }
            r4 = r2
        L_0x0265:
            r38 = r12
            goto L_0x03ce
        L_0x0269:
            r6 = 7
            if (r3 != r6) goto L_0x02ea
            int r5 = r4.length     // Catch:{ Exception -> 0x04cd }
            r6 = 1
            if (r5 <= r6) goto L_0x027d
            java.io.IOException r1 = new java.io.IOException     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = "compression.jpeg.is.only.supported.with.a.single.strip.this.image.has.1.strips"
            int r3 = r4.length     // Catch:{ Exception -> 0x04cd }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (int) r3)     // Catch:{ Exception -> 0x04cd }
            r1.<init>(r2)     // Catch:{ Exception -> 0x04cd }
            throw r1     // Catch:{ Exception -> 0x04cd }
        L_0x027d:
            r5 = 0
            r13 = r4[r5]     // Catch:{ Exception -> 0x04cd }
            int r4 = (int) r13     // Catch:{ Exception -> 0x04cd }
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x04cd }
            r13 = r15[r5]     // Catch:{ Exception -> 0x04cd }
            r2.seek(r13)     // Catch:{ Exception -> 0x04cd }
            r2.readFully(r4)     // Catch:{ Exception -> 0x04cd }
            r2 = 347(0x15b, float:4.86E-43)
            com.itextpdf.text.pdf.codec.TIFFField r2 = r1.getField(r2)     // Catch:{ Exception -> 0x04cd }
            if (r2 == 0) goto L_0x02d8
            byte[] r2 = r2.getAsBytes()     // Catch:{ Exception -> 0x04cd }
            int r5 = r2.length     // Catch:{ Exception -> 0x04cd }
            r6 = 0
            byte r10 = r2[r6]     // Catch:{ Exception -> 0x04cd }
            r6 = -1
            if (r10 != r6) goto L_0x02a9
            r6 = 1
            byte r10 = r2[r6]     // Catch:{ Exception -> 0x04cd }
            r6 = -40
            if (r10 != r6) goto L_0x02a9
            int r5 = r5 + -2
            r6 = 2
            goto L_0x02aa
        L_0x02a9:
            r6 = 0
        L_0x02aa:
            int r10 = r2.length     // Catch:{ Exception -> 0x04cd }
            r13 = 2
            int r10 = r10 - r13
            byte r10 = r2[r10]     // Catch:{ Exception -> 0x04cd }
            r13 = -1
            if (r10 != r13) goto L_0x02bd
            int r10 = r2.length     // Catch:{ Exception -> 0x04cd }
            r13 = 1
            int r10 = r10 - r13
            byte r10 = r2[r10]     // Catch:{ Exception -> 0x04cd }
            r13 = -39
            if (r10 != r13) goto L_0x02bd
            int r5 = r5 + -2
        L_0x02bd:
            byte[] r10 = new byte[r5]     // Catch:{ Exception -> 0x04cd }
            r13 = 0
            java.lang.System.arraycopy(r2, r6, r10, r13, r5)     // Catch:{ Exception -> 0x04cd }
            int r2 = r4.length     // Catch:{ Exception -> 0x04cd }
            int r5 = r10.length     // Catch:{ Exception -> 0x04cd }
            int r2 = r2 + r5
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x04cd }
            r5 = 2
            java.lang.System.arraycopy(r4, r13, r2, r13, r5)     // Catch:{ Exception -> 0x04cd }
            int r6 = r10.length     // Catch:{ Exception -> 0x04cd }
            java.lang.System.arraycopy(r10, r13, r2, r5, r6)     // Catch:{ Exception -> 0x04cd }
            int r6 = r10.length     // Catch:{ Exception -> 0x04cd }
            int r6 = r6 + r5
            int r10 = r4.length     // Catch:{ Exception -> 0x04cd }
            int r10 = r10 - r5
            java.lang.System.arraycopy(r4, r5, r2, r6, r10)     // Catch:{ Exception -> 0x04cd }
            goto L_0x02d9
        L_0x02d8:
            r2 = r4
        L_0x02d9:
            com.itextpdf.text.Jpeg r4 = new com.itextpdf.text.Jpeg     // Catch:{ Exception -> 0x04cd }
            r4.<init>((byte[]) r2)     // Catch:{ Exception -> 0x04cd }
            r2 = 2
            if (r7 != r2) goto L_0x02e7
            r6 = 0
            r4.setColorTransform(r6)     // Catch:{ Exception -> 0x04cd }
            goto L_0x0265
        L_0x02e7:
            r6 = 0
            goto L_0x0265
        L_0x02ea:
            r6 = 0
            r37 = r10
            r1 = r34
        L_0x02ef:
            int r10 = r15.length     // Catch:{ Exception -> 0x04cd }
            if (r6 >= r10) goto L_0x038c
            r38 = r12
            r39 = r13
            r12 = r4[r6]     // Catch:{ Exception -> 0x04cd }
            int r10 = (int) r12     // Catch:{ Exception -> 0x04cd }
            byte[] r10 = new byte[r10]     // Catch:{ Exception -> 0x04cd }
            r12 = r15[r6]     // Catch:{ Exception -> 0x04cd }
            r2.seek(r12)     // Catch:{ Exception -> 0x04cd }
            r2.readFully(r10)     // Catch:{ Exception -> 0x04cd }
            r12 = r32
            int r13 = java.lang.Math.min(r12, r1)     // Catch:{ Exception -> 0x04cd }
            r2 = 1
            if (r3 == r2) goto L_0x031b
            int r2 = r11 * r9
            int r2 = r2 * r8
            r16 = 7
            int r2 = r2 + 7
            r16 = 8
            int r2 = r2 / 8
            int r2 = r2 * r13
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x04cd }
            goto L_0x031c
        L_0x031b:
            r2 = 0
        L_0x031c:
            if (r31 == 0) goto L_0x0321
            com.itextpdf.text.pdf.codec.TIFFFaxDecoder.reverseBits(r10)     // Catch:{ Exception -> 0x04cd }
        L_0x0321:
            r40 = r4
            r4 = 1
            if (r3 == r4) goto L_0x0352
            r4 = 5
            if (r3 == r4) goto L_0x034b
            r4 = 8
            if (r3 == r4) goto L_0x0341
            r4 = 32773(0x8005, float:4.5925E-41)
            if (r3 == r4) goto L_0x033a
            r4 = 32946(0x80b2, float:4.6167E-41)
            if (r3 == r4) goto L_0x0344
        L_0x0337:
            r4 = r39
            goto L_0x0350
        L_0x033a:
            r4 = 32946(0x80b2, float:4.6167E-41)
            decodePackbits(r10, r2)     // Catch:{ Exception -> 0x04cd }
            goto L_0x0337
        L_0x0341:
            r4 = 32946(0x80b2, float:4.6167E-41)
        L_0x0344:
            inflate(r10, r2)     // Catch:{ Exception -> 0x04cd }
            applyPredictor(r2, r5, r11, r13, r8)     // Catch:{ Exception -> 0x04cd }
            goto L_0x0337
        L_0x034b:
            r4 = r39
            r4.decode(r10, r2, r13)     // Catch:{ Exception -> 0x04cd }
        L_0x0350:
            r10 = 1
            goto L_0x0356
        L_0x0352:
            r4 = r39
            r2 = r10
            goto L_0x0350
        L_0x0356:
            if (r9 != r10) goto L_0x0361
            if (r8 != r10) goto L_0x0361
            r10 = 3
            if (r7 == r10) goto L_0x0361
            r14.fax4Encode(r2, r13)     // Catch:{ Exception -> 0x04cd }
            goto L_0x0374
        L_0x0361:
            if (r38 <= 0) goto L_0x0377
            r21 = r37
            r22 = r36
            r23 = r2
            r24 = r8
            r25 = r9
            r26 = r11
            r27 = r13
            ProcessExtraSamples(r21, r22, r23, r24, r25, r26, r27)     // Catch:{ Exception -> 0x04cd }
        L_0x0374:
            r10 = r37
            goto L_0x037c
        L_0x0377:
            r10 = r37
            r10.write(r2)     // Catch:{ Exception -> 0x04cd }
        L_0x037c:
            int r1 = r1 - r12
            int r6 = r6 + 1
            r13 = r4
            r37 = r10
            r32 = r12
            r12 = r38
            r4 = r40
            r2 = r43
            goto L_0x02ef
        L_0x038c:
            r38 = r12
            r10 = r37
            r1 = 1
            if (r9 != r1) goto L_0x03b1
            if (r8 != r1) goto L_0x03b1
            r2 = 3
            if (r7 == r2) goto L_0x03b1
            r23 = 0
            r24 = 256(0x100, float:3.59E-43)
            if (r7 != r1) goto L_0x03a1
            r25 = 1
            goto L_0x03a3
        L_0x03a1:
            r25 = 0
        L_0x03a3:
            byte[] r26 = r14.close()     // Catch:{ Exception -> 0x04cd }
            r21 = r11
            r22 = r34
            com.itextpdf.text.Image r2 = com.itextpdf.text.Image.getInstance((int) r21, (int) r22, (boolean) r23, (int) r24, (int) r25, (byte[]) r26)     // Catch:{ Exception -> 0x04cd }
            r4 = r2
            goto L_0x03ce
        L_0x03b1:
            r10.close()     // Catch:{ Exception -> 0x04cd }
            com.itextpdf.text.ImgRaw r1 = new com.itextpdf.text.ImgRaw     // Catch:{ Exception -> 0x04cd }
            int r24 = r8 - r38
            r14 = r35
            byte[] r26 = r14.toByteArray()     // Catch:{ Exception -> 0x04cd }
            r21 = r1
            r22 = r11
            r23 = r34
            r25 = r9
            r21.<init>(r22, r23, r24, r25, r26)     // Catch:{ Exception -> 0x04cd }
            r2 = 1
            r1.setDeflated(r2)     // Catch:{ Exception -> 0x04cd }
            r4 = r1
        L_0x03ce:
            r2 = r29
            r1 = r30
            r4.setDpi(r1, r2)     // Catch:{ Exception -> 0x04cd }
            r1 = 6
            if (r3 == r1) goto L_0x049f
            r1 = 7
            if (r3 == r1) goto L_0x049f
            r1 = 34675(0x8773, float:4.859E-41)
            r2 = r42
            boolean r1 = r2.isTagPresent(r1)     // Catch:{ Exception -> 0x04cd }
            if (r1 == 0) goto L_0x0400
            r1 = 34675(0x8773, float:4.859E-41)
            com.itextpdf.text.pdf.codec.TIFFField r1 = r2.getField(r1)     // Catch:{ RuntimeException -> 0x0400 }
            byte[] r1 = r1.getAsBytes()     // Catch:{ RuntimeException -> 0x0400 }
            com.itextpdf.text.pdf.ICC_Profile r1 = com.itextpdf.text.pdf.ICC_Profile.getInstance((byte[]) r1)     // Catch:{ RuntimeException -> 0x0400 }
            int r8 = r8 - r38
            int r3 = r1.getNumComponents()     // Catch:{ RuntimeException -> 0x0400 }
            if (r8 != r3) goto L_0x0400
            r4.tagICC(r1)     // Catch:{ RuntimeException -> 0x0400 }
        L_0x0400:
            r1 = 320(0x140, float:4.48E-43)
            boolean r1 = r2.isTagPresent(r1)     // Catch:{ Exception -> 0x04cd }
            if (r1 == 0) goto L_0x049b
            r1 = 320(0x140, float:4.48E-43)
            com.itextpdf.text.pdf.codec.TIFFField r1 = r2.getField(r1)     // Catch:{ Exception -> 0x04cd }
            char[] r1 = r1.getAsChars()     // Catch:{ Exception -> 0x04cd }
            int r2 = r1.length     // Catch:{ Exception -> 0x04cd }
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x04cd }
            int r3 = r1.length     // Catch:{ Exception -> 0x04cd }
            r5 = 3
            int r3 = r3 / r5
            int r5 = r3 * 2
            r6 = 0
        L_0x041b:
            if (r6 >= r3) goto L_0x043e
            int r8 = r6 * 3
            char r10 = r1[r6]     // Catch:{ Exception -> 0x04cd }
            r12 = 8
            int r10 = r10 >>> r12
            byte r10 = (byte) r10     // Catch:{ Exception -> 0x04cd }
            r2[r8] = r10     // Catch:{ Exception -> 0x04cd }
            int r10 = r8 + 1
            int r13 = r6 + r3
            char r13 = r1[r13]     // Catch:{ Exception -> 0x04cd }
            int r13 = r13 >>> r12
            byte r13 = (byte) r13     // Catch:{ Exception -> 0x04cd }
            r2[r10] = r13     // Catch:{ Exception -> 0x04cd }
            int r8 = r8 + 2
            int r10 = r6 + r5
            char r10 = r1[r10]     // Catch:{ Exception -> 0x04cd }
            int r10 = r10 >>> r12
            byte r10 = (byte) r10     // Catch:{ Exception -> 0x04cd }
            r2[r8] = r10     // Catch:{ Exception -> 0x04cd }
            int r6 = r6 + 1
            goto L_0x041b
        L_0x043e:
            r6 = 0
        L_0x043f:
            int r8 = r2.length     // Catch:{ Exception -> 0x04cd }
            if (r6 >= r8) goto L_0x044b
            byte r8 = r2[r6]     // Catch:{ Exception -> 0x04cd }
            if (r8 == 0) goto L_0x0448
            r6 = 0
            goto L_0x044c
        L_0x0448:
            int r6 = r6 + 1
            goto L_0x043f
        L_0x044b:
            r6 = 1
        L_0x044c:
            if (r6 == 0) goto L_0x046d
            r6 = 0
        L_0x044f:
            if (r6 >= r3) goto L_0x046d
            int r8 = r6 * 3
            char r10 = r1[r6]     // Catch:{ Exception -> 0x04cd }
            byte r10 = (byte) r10     // Catch:{ Exception -> 0x04cd }
            r2[r8] = r10     // Catch:{ Exception -> 0x04cd }
            int r10 = r8 + 1
            int r12 = r6 + r3
            char r12 = r1[r12]     // Catch:{ Exception -> 0x04cd }
            byte r12 = (byte) r12     // Catch:{ Exception -> 0x04cd }
            r2[r10] = r12     // Catch:{ Exception -> 0x04cd }
            int r8 = r8 + 2
            int r10 = r6 + r5
            char r10 = r1[r10]     // Catch:{ Exception -> 0x04cd }
            byte r10 = (byte) r10     // Catch:{ Exception -> 0x04cd }
            r2[r8] = r10     // Catch:{ Exception -> 0x04cd }
            int r6 = r6 + 1
            goto L_0x044f
        L_0x046d:
            com.itextpdf.text.pdf.PdfArray r1 = new com.itextpdf.text.pdf.PdfArray     // Catch:{ Exception -> 0x04cd }
            r1.<init>()     // Catch:{ Exception -> 0x04cd }
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.INDEXED     // Catch:{ Exception -> 0x04cd }
            r1.add((com.itextpdf.text.pdf.PdfObject) r5)     // Catch:{ Exception -> 0x04cd }
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.DEVICERGB     // Catch:{ Exception -> 0x04cd }
            r1.add((com.itextpdf.text.pdf.PdfObject) r5)     // Catch:{ Exception -> 0x04cd }
            com.itextpdf.text.pdf.PdfNumber r5 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ Exception -> 0x04cd }
            r6 = 1
            int r3 = r3 - r6
            r5.<init>((int) r3)     // Catch:{ Exception -> 0x04cd }
            r1.add((com.itextpdf.text.pdf.PdfObject) r5)     // Catch:{ Exception -> 0x04cd }
            com.itextpdf.text.pdf.PdfString r3 = new com.itextpdf.text.pdf.PdfString     // Catch:{ Exception -> 0x04cd }
            r3.<init>((byte[]) r2)     // Catch:{ Exception -> 0x04cd }
            r1.add((com.itextpdf.text.pdf.PdfObject) r3)     // Catch:{ Exception -> 0x04cd }
            com.itextpdf.text.pdf.PdfDictionary r2 = new com.itextpdf.text.pdf.PdfDictionary     // Catch:{ Exception -> 0x04cd }
            r2.<init>()     // Catch:{ Exception -> 0x04cd }
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ Exception -> 0x04cd }
            r2.put(r3, r1)     // Catch:{ Exception -> 0x04cd }
            r4.setAdditional(r2)     // Catch:{ Exception -> 0x04cd }
        L_0x049b:
            r1 = 5
            r4.setOriginalType(r1)     // Catch:{ Exception -> 0x04cd }
        L_0x049f:
            if (r7 != 0) goto L_0x04a5
            r1 = 1
            r4.setInverted(r1)     // Catch:{ Exception -> 0x04cd }
        L_0x04a5:
            r1 = 0
            int r1 = (r28 > r1 ? 1 : (r28 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x04af
            r1 = r28
            r4.setInitialRotation(r1)     // Catch:{ Exception -> 0x04cd }
        L_0x04af:
            if (r38 <= 0) goto L_0x04cc
            r6 = r36
            r6.close()     // Catch:{ Exception -> 0x04cd }
            r14 = r33
            byte[] r1 = r14.toByteArray()     // Catch:{ Exception -> 0x04cd }
            r2 = r34
            r3 = 1
            com.itextpdf.text.Image r1 = com.itextpdf.text.Image.getInstance(r11, r2, r3, r9, r1)     // Catch:{ Exception -> 0x04cd }
            r1.makeMask()     // Catch:{ Exception -> 0x04cd }
            r1.setDeflated(r3)     // Catch:{ Exception -> 0x04cd }
            r4.setImageMask(r1)     // Catch:{ Exception -> 0x04cd }
        L_0x04cc:
            return r4
        L_0x04cd:
            r0 = move-exception
            r1 = r0
            com.itextpdf.text.ExceptionConverter r2 = new com.itextpdf.text.ExceptionConverter
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.TiffImage.getTiffImageColor(com.itextpdf.text.pdf.codec.TIFFDirectory, com.itextpdf.text.pdf.RandomAccessFileOrArray):com.itextpdf.text.Image");
    }

    static Image ProcessExtraSamples(DeflaterOutputStream deflaterOutputStream, DeflaterOutputStream deflaterOutputStream2, byte[] bArr, int i, int i2, int i3, int i4) throws IOException {
        if (i2 == 8) {
            int i5 = i3 * i4;
            byte[] bArr2 = new byte[i5];
            int i6 = i5 * i;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i7 < i6) {
                int i10 = i8;
                int i11 = 0;
                while (i11 < i - 1) {
                    bArr[i10] = bArr[i7 + i11];
                    i11++;
                    i10++;
                }
                i7 += i;
                bArr2[i9] = bArr[i7 - 1];
                i9++;
                i8 = i10;
            }
            deflaterOutputStream.write(bArr, 0, i8);
            deflaterOutputStream2.write(bArr2, 0, i9);
            return null;
        }
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("extra.samples.are.not.supported", new Object[0]));
    }

    static long[] getArrayLongShort(TIFFDirectory tIFFDirectory, int i) {
        TIFFField field = tIFFDirectory.getField(i);
        if (field == null) {
            return null;
        }
        if (field.getType() == 4) {
            return field.getAsLongs();
        }
        char[] asChars = field.getAsChars();
        long[] jArr = new long[asChars.length];
        for (int i2 = 0; i2 < asChars.length; i2++) {
            jArr[i2] = (long) asChars[i2];
        }
        return jArr;
    }

    public static void decodePackbits(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int i2 = 0;
        while (i < bArr2.length) {
            try {
                int i3 = i2 + 1;
                byte b = bArr[i2];
                if (b >= 0 && b <= Byte.MAX_VALUE) {
                    int i4 = i3;
                    int i5 = i;
                    int i6 = 0;
                    while (i6 < b + 1) {
                        bArr2[i5] = bArr[i4];
                        i6++;
                        i5++;
                        i4++;
                    }
                    i = i5;
                    i2 = i4;
                } else if (b > -1 || b < -127) {
                    i2 = i3 + 1;
                } else {
                    int i7 = i3 + 1;
                    byte b2 = bArr[i3];
                    int i8 = i;
                    int i9 = 0;
                    while (i9 < (-b) + 1) {
                        int i10 = i8 + 1;
                        bArr2[i8] = b2;
                        i9++;
                        i8 = i10;
                    }
                    i2 = i7;
                    i = i8;
                }
            } catch (Exception unused) {
                return;
            }
        }
    }

    public static void inflate(byte[] bArr, byte[] bArr2) {
        Inflater inflater = new Inflater();
        inflater.setInput(bArr);
        try {
            inflater.inflate(bArr2);
        } catch (DataFormatException e) {
            throw new ExceptionConverter(e);
        }
    }

    public static void applyPredictor(byte[] bArr, int i, int i2, int i3, int i4) {
        if (i == 2) {
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = ((i5 * i2) + 1) * i4;
                for (int i7 = i4; i7 < i2 * i4; i7++) {
                    bArr[i6] = (byte) (bArr[i6] + bArr[i6 - i4]);
                    i6++;
                }
            }
        }
    }
}
