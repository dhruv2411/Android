package com.itextpdf.text.pdf;

import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;

public class BarcodeEAN extends Barcode {
    private static final byte[][] BARS = {new byte[]{3, 2, 1, 1}, new byte[]{2, 2, 2, 1}, new byte[]{2, 1, 2, 2}, new byte[]{1, 4, 1, 1}, new byte[]{1, 1, 3, 2}, new byte[]{1, 2, 3, 1}, new byte[]{1, 1, 1, 4}, new byte[]{1, 3, 1, 2}, new byte[]{1, 2, 1, 3}, new byte[]{3, 1, 1, 2}};
    private static final int EVEN = 1;
    private static final int[] GUARD_EAN13 = {0, 2, 28, 30, 56, 58};
    private static final int[] GUARD_EAN8 = {0, 2, 20, 22, 40, 42};
    private static final int[] GUARD_EMPTY = new int[0];
    private static final int[] GUARD_UPCA = {0, 2, 4, 6, 28, 30, 52, 54, 56, 58};
    private static final int[] GUARD_UPCE = {0, 2, 28, 30, 32};
    private static final int ODD = 0;
    private static final byte[][] PARITY13 = {new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 1, 0, 1, 1}, new byte[]{0, 0, 1, 1, 0, 1}, new byte[]{0, 0, 1, 1, 1, 0}, new byte[]{0, 1, 0, 0, 1, 1}, new byte[]{0, 1, 1, 0, 0, 1}, new byte[]{0, 1, 1, 1, 0, 0}, new byte[]{0, 1, 0, 1, 0, 1}, new byte[]{0, 1, 0, 1, 1, 0}, new byte[]{0, 1, 1, 0, 1, 0}};
    private static final byte[][] PARITY2 = {new byte[]{0, 0}, new byte[]{0, 1}, new byte[]{1, 0}, new byte[]{1, 1}};
    private static final byte[][] PARITY5 = {new byte[]{1, 1, 0, 0, 0}, new byte[]{1, 0, 1, 0, 0}, new byte[]{1, 0, 0, 1, 0}, new byte[]{1, 0, 0, 0, 1}, new byte[]{0, 1, 1, 0, 0}, new byte[]{0, 0, 1, 1, 0}, new byte[]{0, 0, 0, 1, 1}, new byte[]{0, 1, 0, 1, 0}, new byte[]{0, 1, 0, 0, 1}, new byte[]{0, 0, 1, 0, 1}};
    private static final byte[][] PARITYE = {new byte[]{1, 1, 1, 0, 0, 0}, new byte[]{1, 1, 0, 1, 0, 0}, new byte[]{1, 1, 0, 0, 1, 0}, new byte[]{1, 1, 0, 0, 0, 1}, new byte[]{1, 0, 1, 1, 0, 0}, new byte[]{1, 0, 0, 1, 1, 0}, new byte[]{1, 0, 0, 0, 1, 1}, new byte[]{1, 0, 1, 0, 1, 0}, new byte[]{1, 0, 1, 0, 0, 1}, new byte[]{1, 0, 0, 1, 0, 1}};
    private static final float[] TEXTPOS_EAN13 = {6.5f, 13.5f, 20.5f, 27.5f, 34.5f, 41.5f, 53.5f, 60.5f, 67.5f, 74.5f, 81.5f, 88.5f};
    private static final float[] TEXTPOS_EAN8 = {6.5f, 13.5f, 20.5f, 27.5f, 39.5f, 46.5f, 53.5f, 60.5f};
    private static final int TOTALBARS_EAN13 = 59;
    private static final int TOTALBARS_EAN8 = 43;
    private static final int TOTALBARS_SUPP2 = 13;
    private static final int TOTALBARS_SUPP5 = 31;
    private static final int TOTALBARS_UPCE = 33;

    public BarcodeEAN() {
        try {
            this.x = 0.8f;
            this.font = BaseFont.createFont("Helvetica", "winansi", false);
            this.size = 8.0f;
            this.baseline = this.size;
            this.barHeight = this.size * 3.0f;
            this.guardBars = true;
            this.codeType = 1;
            this.code = "";
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public static int calculateEANParity(String str) {
        int i = 3;
        int i2 = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            i2 += (str.charAt(length) - '0') * i;
            i ^= 2;
        }
        return (10 - (i2 % 10)) % 10;
    }

    public static String convertUPCAtoUPCE(String str) {
        if (str.length() != 12 || (!str.startsWith("0") && !str.startsWith("1"))) {
            return null;
        }
        if (str.substring(3, 6).equals("000") || str.substring(3, 6).equals("100") || str.substring(3, 6).equals("200")) {
            if (str.substring(6, 8).equals("00")) {
                return str.substring(0, 1) + str.substring(1, 3) + str.substring(8, 11) + str.substring(3, 4) + str.substring(11);
            }
        } else if (str.substring(4, 6).equals("00")) {
            if (str.substring(6, 9).equals("000")) {
                return str.substring(0, 1) + str.substring(1, 4) + str.substring(9, 11) + "3" + str.substring(11);
            }
        } else if (str.substring(5, 6).equals("0")) {
            if (str.substring(6, 10).equals("0000")) {
                return str.substring(0, 1) + str.substring(1, 5) + str.substring(10, 11) + "4" + str.substring(11);
            }
        } else if (str.charAt(10) >= '5' && str.substring(6, 10).equals("0000")) {
            return str.substring(0, 1) + str.substring(1, 6) + str.substring(10, 11) + str.substring(11);
        }
        return null;
    }

    public static byte[] getBarsEAN13(String str) {
        int[] iArr = new int[str.length()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = str.charAt(i) - '0';
        }
        byte[] bArr = new byte[59];
        bArr[0] = 1;
        bArr[1] = 1;
        bArr[2] = 1;
        byte[] bArr2 = PARITY13[iArr[0]];
        int i2 = 0;
        int i3 = 3;
        while (i2 < bArr2.length) {
            int i4 = i2 + 1;
            byte[] bArr3 = BARS[iArr[i4]];
            if (bArr2[i2] == 0) {
                int i5 = i3 + 1;
                bArr[i3] = bArr3[0];
                int i6 = i5 + 1;
                bArr[i5] = bArr3[1];
                int i7 = i6 + 1;
                bArr[i6] = bArr3[2];
                i3 = i7 + 1;
                bArr[i7] = bArr3[3];
            } else {
                int i8 = i3 + 1;
                bArr[i3] = bArr3[3];
                int i9 = i8 + 1;
                bArr[i8] = bArr3[2];
                int i10 = i9 + 1;
                bArr[i9] = bArr3[1];
                i3 = i10 + 1;
                bArr[i10] = bArr3[0];
            }
            i2 = i4;
        }
        int i11 = i3 + 1;
        bArr[i3] = 1;
        int i12 = i11 + 1;
        bArr[i11] = 1;
        int i13 = i12 + 1;
        bArr[i12] = 1;
        int i14 = i13 + 1;
        bArr[i13] = 1;
        int i15 = i14 + 1;
        bArr[i14] = 1;
        for (int i16 = 7; i16 < 13; i16++) {
            byte[] bArr4 = BARS[iArr[i16]];
            int i17 = i15 + 1;
            bArr[i15] = bArr4[0];
            int i18 = i17 + 1;
            bArr[i17] = bArr4[1];
            int i19 = i18 + 1;
            bArr[i18] = bArr4[2];
            i15 = i19 + 1;
            bArr[i19] = bArr4[3];
        }
        int i20 = i15 + 1;
        bArr[i15] = 1;
        bArr[i20] = 1;
        bArr[i20 + 1] = 1;
        return bArr;
    }

    public static byte[] getBarsEAN8(String str) {
        int i;
        int[] iArr = new int[str.length()];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = str.charAt(i2) - '0';
        }
        byte[] bArr = new byte[43];
        bArr[0] = 1;
        bArr[1] = 1;
        bArr[2] = 1;
        int i3 = 0;
        int i4 = 3;
        while (true) {
            if (i3 >= 4) {
                break;
            }
            byte[] bArr2 = BARS[iArr[i3]];
            int i5 = i4 + 1;
            bArr[i4] = bArr2[0];
            int i6 = i5 + 1;
            bArr[i5] = bArr2[1];
            int i7 = i6 + 1;
            bArr[i6] = bArr2[2];
            i4 = i7 + 1;
            bArr[i7] = bArr2[3];
            i3++;
        }
        int i8 = i4 + 1;
        bArr[i4] = 1;
        int i9 = i8 + 1;
        bArr[i8] = 1;
        int i10 = i9 + 1;
        bArr[i9] = 1;
        int i11 = i10 + 1;
        bArr[i10] = 1;
        int i12 = i11 + 1;
        bArr[i11] = 1;
        for (i = 4; i < 8; i++) {
            byte[] bArr3 = BARS[iArr[i]];
            int i13 = i12 + 1;
            bArr[i12] = bArr3[0];
            int i14 = i13 + 1;
            bArr[i13] = bArr3[1];
            int i15 = i14 + 1;
            bArr[i14] = bArr3[2];
            i12 = i15 + 1;
            bArr[i15] = bArr3[3];
        }
        int i16 = i12 + 1;
        bArr[i12] = 1;
        bArr[i16] = 1;
        bArr[i16 + 1] = 1;
        return bArr;
    }

    public static byte[] getBarsUPCE(String str) {
        int[] iArr = new int[str.length()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = str.charAt(i) - '0';
        }
        byte[] bArr = new byte[33];
        byte b = iArr[0] != 0 ? (byte) 1 : 0;
        bArr[0] = 1;
        bArr[1] = 1;
        bArr[2] = 1;
        byte[] bArr2 = PARITYE[iArr[iArr.length - 1]];
        int i2 = 3;
        for (int i3 = 1; i3 < iArr.length - 1; i3++) {
            byte[] bArr3 = BARS[iArr[i3]];
            if (bArr2[i3 - 1] == b) {
                int i4 = i2 + 1;
                bArr[i2] = bArr3[0];
                int i5 = i4 + 1;
                bArr[i4] = bArr3[1];
                int i6 = i5 + 1;
                bArr[i5] = bArr3[2];
                i2 = i6 + 1;
                bArr[i6] = bArr3[3];
            } else {
                int i7 = i2 + 1;
                bArr[i2] = bArr3[3];
                int i8 = i7 + 1;
                bArr[i7] = bArr3[2];
                int i9 = i8 + 1;
                bArr[i8] = bArr3[1];
                i2 = i9 + 1;
                bArr[i9] = bArr3[0];
            }
        }
        int i10 = i2 + 1;
        bArr[i2] = 1;
        int i11 = i10 + 1;
        bArr[i10] = 1;
        int i12 = i11 + 1;
        bArr[i11] = 1;
        int i13 = i12 + 1;
        bArr[i12] = 1;
        bArr[i13] = 1;
        bArr[i13 + 1] = 1;
        return bArr;
    }

    public static byte[] getBarsSupplemental2(String str) {
        int[] iArr = new int[2];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = str.charAt(i) - '0';
        }
        byte[] bArr = new byte[13];
        bArr[0] = 1;
        bArr[1] = 1;
        bArr[2] = 2;
        byte[] bArr2 = PARITY2[((iArr[0] * 10) + iArr[1]) % 4];
        int i2 = 3;
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            if (i3 == 1) {
                int i4 = i2 + 1;
                bArr[i2] = 1;
                i2 = i4 + 1;
                bArr[i4] = 1;
            }
            byte[] bArr3 = BARS[iArr[i3]];
            if (bArr2[i3] == 0) {
                int i5 = i2 + 1;
                bArr[i2] = bArr3[0];
                int i6 = i5 + 1;
                bArr[i5] = bArr3[1];
                int i7 = i6 + 1;
                bArr[i6] = bArr3[2];
                i2 = i7 + 1;
                bArr[i7] = bArr3[3];
            } else {
                int i8 = i2 + 1;
                bArr[i2] = bArr3[3];
                int i9 = i8 + 1;
                bArr[i8] = bArr3[2];
                int i10 = i9 + 1;
                bArr[i9] = bArr3[1];
                i2 = i10 + 1;
                bArr[i10] = bArr3[0];
            }
        }
        return bArr;
    }

    public static byte[] getBarsSupplemental5(String str) {
        int[] iArr = new int[5];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = str.charAt(i) - '0';
        }
        byte[] bArr = new byte[31];
        bArr[0] = 1;
        bArr[1] = 1;
        bArr[2] = 2;
        byte[] bArr2 = PARITY5[((((iArr[0] + iArr[2]) + iArr[4]) * 3) + ((iArr[1] + iArr[3]) * 9)) % 10];
        int i2 = 3;
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            if (i3 != 0) {
                int i4 = i2 + 1;
                bArr[i2] = 1;
                i2 = i4 + 1;
                bArr[i4] = 1;
            }
            byte[] bArr3 = BARS[iArr[i3]];
            if (bArr2[i3] == 0) {
                int i5 = i2 + 1;
                bArr[i2] = bArr3[0];
                int i6 = i5 + 1;
                bArr[i5] = bArr3[1];
                int i7 = i6 + 1;
                bArr[i6] = bArr3[2];
                i2 = i7 + 1;
                bArr[i7] = bArr3[3];
            } else {
                int i8 = i2 + 1;
                bArr[i2] = bArr3[3];
                int i9 = i8 + 1;
                bArr[i8] = bArr3[2];
                int i10 = i9 + 1;
                bArr[i9] = bArr3[1];
                i2 = i10 + 1;
                bArr[i10] = bArr3[0];
            }
        }
        return bArr;
    }

    public Rectangle getBarcodeSize() {
        float f;
        float f2 = this.barHeight;
        if (this.font != null) {
            if (this.baseline <= 0.0f) {
                f2 += (-this.baseline) + this.size;
            } else {
                f2 += this.baseline - this.font.getFontDescriptor(3, this.size);
            }
        }
        switch (this.codeType) {
            case 1:
                f = this.x * 95.0f;
                if (this.font != null) {
                    f += this.font.getWidthPoint((int) this.code.charAt(0), this.size);
                    break;
                }
                break;
            case 2:
                f = this.x * 67.0f;
                break;
            case 3:
                f = this.x * 95.0f;
                if (this.font != null) {
                    f += this.font.getWidthPoint((int) this.code.charAt(0), this.size) + this.font.getWidthPoint((int) this.code.charAt(11), this.size);
                    break;
                }
                break;
            case 4:
                f = this.x * 51.0f;
                if (this.font != null) {
                    f += this.font.getWidthPoint((int) this.code.charAt(0), this.size) + this.font.getWidthPoint((int) this.code.charAt(7), this.size);
                    break;
                }
                break;
            case 5:
                f = this.x * 20.0f;
                break;
            case 6:
                f = this.x * 47.0f;
                break;
            default:
                throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.code.type", new Object[0]));
        }
        return new Rectangle(f, f2);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.Rectangle placeBarcode(com.itextpdf.text.pdf.PdfContentByte r18, com.itextpdf.text.BaseColor r19, com.itextpdf.text.BaseColor r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r20
            com.itextpdf.text.Rectangle r3 = r17.getBarcodeSize()
            com.itextpdf.text.pdf.BaseFont r4 = r0.font
            r5 = 0
            if (r4 == 0) goto L_0x002a
            float r4 = r0.baseline
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x001c
            float r4 = r0.barHeight
            float r6 = r0.baseline
            float r4 = r4 - r6
            r6 = r5
            goto L_0x002c
        L_0x001c:
            com.itextpdf.text.pdf.BaseFont r4 = r0.font
            r6 = 3
            float r7 = r0.size
            float r4 = r4.getFontDescriptor(r6, r7)
            float r4 = -r4
            float r6 = r0.baseline
            float r6 = r6 + r4
            goto L_0x002c
        L_0x002a:
            r4 = r5
            r6 = r4
        L_0x002c:
            int r7 = r0.codeType
            r8 = 0
            r9 = 1
            if (r7 == r9) goto L_0x0036
            switch(r7) {
                case 3: goto L_0x0036;
                case 4: goto L_0x0036;
                default: goto L_0x0035;
            }
        L_0x0035:
            goto L_0x004a
        L_0x0036:
            com.itextpdf.text.pdf.BaseFont r7 = r0.font
            if (r7 == 0) goto L_0x004a
            com.itextpdf.text.pdf.BaseFont r7 = r0.font
            java.lang.String r10 = r0.code
            char r10 = r10.charAt(r8)
            float r11 = r0.size
            float r7 = r7.getWidthPoint((int) r10, (float) r11)
            float r7 = r7 + r5
            goto L_0x004b
        L_0x004a:
            r7 = r5
        L_0x004b:
            r10 = 0
            int[] r11 = GUARD_EMPTY
            int r12 = r0.codeType
            switch(r12) {
                case 1: goto L_0x008e;
                case 2: goto L_0x0085;
                case 3: goto L_0x006b;
                case 4: goto L_0x0062;
                case 5: goto L_0x005b;
                case 6: goto L_0x0054;
                default: goto L_0x0053;
            }
        L_0x0053:
            goto L_0x0096
        L_0x0054:
            java.lang.String r10 = r0.code
            byte[] r10 = getBarsSupplemental5(r10)
            goto L_0x0096
        L_0x005b:
            java.lang.String r10 = r0.code
            byte[] r10 = getBarsSupplemental2(r10)
            goto L_0x0096
        L_0x0062:
            java.lang.String r10 = r0.code
            byte[] r10 = getBarsUPCE(r10)
            int[] r11 = GUARD_UPCE
            goto L_0x0096
        L_0x006b:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "0"
            r10.append(r11)
            java.lang.String r11 = r0.code
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            byte[] r10 = getBarsEAN13(r10)
            int[] r11 = GUARD_UPCA
            goto L_0x0096
        L_0x0085:
            java.lang.String r10 = r0.code
            byte[] r10 = getBarsEAN8(r10)
            int[] r11 = GUARD_EAN8
            goto L_0x0096
        L_0x008e:
            java.lang.String r10 = r0.code
            byte[] r10 = getBarsEAN13(r10)
            int[] r11 = GUARD_EAN13
        L_0x0096:
            com.itextpdf.text.pdf.BaseFont r12 = r0.font
            r13 = 1073741824(0x40000000, float:2.0)
            if (r12 == 0) goto L_0x00ab
            float r12 = r0.baseline
            int r12 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r12 <= 0) goto L_0x00ab
            boolean r12 = r0.guardBars
            if (r12 == 0) goto L_0x00ab
            float r12 = r0.baseline
            float r12 = r12 / r13
            r14 = r12
            goto L_0x00ac
        L_0x00ab:
            r14 = r5
        L_0x00ac:
            if (r19 == 0) goto L_0x00b1
            r18.setColorFill(r19)
        L_0x00b1:
            r12 = r8
            r15 = r9
            r8 = r7
        L_0x00b4:
            int r9 = r10.length
            if (r12 >= r9) goto L_0x00ed
            byte r9 = r10[r12]
            float r9 = (float) r9
            float r5 = r0.x
            float r9 = r9 * r5
            if (r15 == 0) goto L_0x00e0
            int r5 = java.util.Arrays.binarySearch(r11, r12)
            if (r5 < 0) goto L_0x00d4
            float r5 = r6 - r14
            float r13 = r0.inkSpreading
            float r13 = r9 - r13
            r16 = r10
            float r10 = r0.barHeight
            float r10 = r10 + r14
            r1.rectangle((float) r8, (float) r5, (float) r13, (float) r10)
            goto L_0x00e2
        L_0x00d4:
            r16 = r10
            float r5 = r0.inkSpreading
            float r5 = r9 - r5
            float r10 = r0.barHeight
            r1.rectangle((float) r8, (float) r6, (float) r5, (float) r10)
            goto L_0x00e2
        L_0x00e0:
            r16 = r10
        L_0x00e2:
            r15 = r15 ^ 1
            float r8 = r8 + r9
            int r12 = r12 + 1
            r10 = r16
            r5 = 0
            r13 = 1073741824(0x40000000, float:2.0)
            goto L_0x00b4
        L_0x00ed:
            r18.fill()
            com.itextpdf.text.pdf.BaseFont r5 = r0.font
            if (r5 == 0) goto L_0x0237
            if (r2 == 0) goto L_0x00f9
            r1.setColorFill(r2)
        L_0x00f9:
            r18.beginText()
            com.itextpdf.text.pdf.BaseFont r2 = r0.font
            float r5 = r0.size
            r1.setFontAndSize(r2, r5)
            int r2 = r0.codeType
            r5 = 8
            switch(r2) {
                case 1: goto L_0x01fb;
                case 2: goto L_0x01d5;
                case 3: goto L_0x0188;
                case 4: goto L_0x013b;
                case 5: goto L_0x010c;
                case 6: goto L_0x010c;
                default: goto L_0x010a;
            }
        L_0x010a:
            goto L_0x0234
        L_0x010c:
            r2 = 0
        L_0x010d:
            java.lang.String r5 = r0.code
            int r5 = r5.length()
            if (r2 >= r5) goto L_0x0234
            java.lang.String r5 = r0.code
            int r6 = r2 + 1
            java.lang.String r5 = r5.substring(r2, r6)
            com.itextpdf.text.pdf.BaseFont r7 = r0.font
            float r8 = r0.size
            float r7 = r7.getWidthPoint((java.lang.String) r5, (float) r8)
            r8 = 1089470464(0x40f00000, float:7.5)
            r9 = 9
            int r9 = r9 * r2
            float r2 = (float) r9
            float r8 = r8 + r2
            float r2 = r0.x
            float r8 = r8 * r2
            r2 = 1073741824(0x40000000, float:2.0)
            float r7 = r7 / r2
            float r8 = r8 - r7
            r1.setTextMatrix(r8, r4)
            r1.showText((java.lang.String) r5)
            r2 = r6
            goto L_0x010d
        L_0x013b:
            r2 = 0
            r1.setTextMatrix(r2, r4)
            java.lang.String r2 = r0.code
            r6 = 1
            r8 = 0
            java.lang.String r2 = r2.substring(r8, r6)
            r1.showText((java.lang.String) r2)
            r2 = 1
        L_0x014b:
            r6 = 7
            if (r2 >= r6) goto L_0x0174
            java.lang.String r6 = r0.code
            int r8 = r2 + 1
            java.lang.String r6 = r6.substring(r2, r8)
            com.itextpdf.text.pdf.BaseFont r9 = r0.font
            float r10 = r0.size
            float r9 = r9.getWidthPoint((java.lang.String) r6, (float) r10)
            float[] r10 = TEXTPOS_EAN13
            int r2 = r2 + -1
            r2 = r10[r2]
            float r10 = r0.x
            float r2 = r2 * r10
            float r2 = r2 + r7
            r10 = 1073741824(0x40000000, float:2.0)
            float r9 = r9 / r10
            float r2 = r2 - r9
            r1.setTextMatrix(r2, r4)
            r1.showText((java.lang.String) r6)
            r2 = r8
            goto L_0x014b
        L_0x0174:
            float r2 = r0.x
            r8 = 1112276992(0x424c0000, float:51.0)
            float r2 = r2 * r8
            float r7 = r7 + r2
            r1.setTextMatrix(r7, r4)
            java.lang.String r2 = r0.code
            java.lang.String r2 = r2.substring(r6, r5)
            r1.showText((java.lang.String) r2)
            goto L_0x0234
        L_0x0188:
            r2 = 0
            r1.setTextMatrix(r2, r4)
            java.lang.String r2 = r0.code
            r5 = 1
            r6 = 0
            java.lang.String r2 = r2.substring(r6, r5)
            r1.showText((java.lang.String) r2)
            r2 = 1
        L_0x0198:
            r5 = 11
            if (r2 >= r5) goto L_0x01c0
            java.lang.String r5 = r0.code
            int r6 = r2 + 1
            java.lang.String r5 = r5.substring(r2, r6)
            com.itextpdf.text.pdf.BaseFont r8 = r0.font
            float r9 = r0.size
            float r8 = r8.getWidthPoint((java.lang.String) r5, (float) r9)
            float[] r9 = TEXTPOS_EAN13
            r2 = r9[r2]
            float r9 = r0.x
            float r2 = r2 * r9
            float r2 = r2 + r7
            r9 = 1073741824(0x40000000, float:2.0)
            float r8 = r8 / r9
            float r2 = r2 - r8
            r1.setTextMatrix(r2, r4)
            r1.showText((java.lang.String) r5)
            r2 = r6
            goto L_0x0198
        L_0x01c0:
            float r2 = r0.x
            r6 = 1119748096(0x42be0000, float:95.0)
            float r2 = r2 * r6
            float r7 = r7 + r2
            r1.setTextMatrix(r7, r4)
            java.lang.String r2 = r0.code
            r4 = 12
            java.lang.String r2 = r2.substring(r5, r4)
            r1.showText((java.lang.String) r2)
            goto L_0x0234
        L_0x01d5:
            r2 = 0
        L_0x01d6:
            if (r2 >= r5) goto L_0x0234
            java.lang.String r6 = r0.code
            int r7 = r2 + 1
            java.lang.String r6 = r6.substring(r2, r7)
            com.itextpdf.text.pdf.BaseFont r8 = r0.font
            float r9 = r0.size
            float r8 = r8.getWidthPoint((java.lang.String) r6, (float) r9)
            float[] r9 = TEXTPOS_EAN8
            r2 = r9[r2]
            float r9 = r0.x
            float r2 = r2 * r9
            r9 = 1073741824(0x40000000, float:2.0)
            float r8 = r8 / r9
            float r2 = r2 - r8
            r1.setTextMatrix(r2, r4)
            r1.showText((java.lang.String) r6)
            r2 = r7
            goto L_0x01d6
        L_0x01fb:
            r2 = 0
            r1.setTextMatrix(r2, r4)
            java.lang.String r2 = r0.code
            r5 = 0
            r6 = 1
            java.lang.String r2 = r2.substring(r5, r6)
            r1.showText((java.lang.String) r2)
        L_0x020a:
            r2 = 13
            if (r6 >= r2) goto L_0x0234
            java.lang.String r2 = r0.code
            int r5 = r6 + 1
            java.lang.String r2 = r2.substring(r6, r5)
            com.itextpdf.text.pdf.BaseFont r8 = r0.font
            float r9 = r0.size
            float r8 = r8.getWidthPoint((java.lang.String) r2, (float) r9)
            float[] r9 = TEXTPOS_EAN13
            int r6 = r6 + -1
            r6 = r9[r6]
            float r9 = r0.x
            float r6 = r6 * r9
            float r6 = r6 + r7
            r9 = 1073741824(0x40000000, float:2.0)
            float r8 = r8 / r9
            float r6 = r6 - r8
            r1.setTextMatrix(r6, r4)
            r1.showText((java.lang.String) r2)
            r6 = r5
            goto L_0x020a
        L_0x0234:
            r18.endText()
        L_0x0237:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.BarcodeEAN.placeBarcode(com.itextpdf.text.pdf.PdfContentByte, com.itextpdf.text.BaseColor, com.itextpdf.text.BaseColor):com.itextpdf.text.Rectangle");
    }
}
