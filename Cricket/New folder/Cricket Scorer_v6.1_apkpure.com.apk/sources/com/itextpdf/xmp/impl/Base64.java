package com.itextpdf.xmp.impl;

import com.itextpdf.text.DocWriter;
import com.itextpdf.text.pdf.BidiOrder;
import com.itextpdf.text.pdf.ByteBuffer;

public class Base64 {
    private static final byte EQUAL = -3;
    private static final byte INVALID = -1;
    private static final byte WHITESPACE = -2;
    private static byte[] ascii = new byte[255];
    private static byte[] base64 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, ByteBuffer.ZERO, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, DocWriter.FORWARD};

    static {
        for (int i = 0; i < 255; i++) {
            ascii[i] = INVALID;
        }
        for (int i2 = 0; i2 < base64.length; i2++) {
            ascii[base64[i2]] = (byte) i2;
        }
        ascii[9] = WHITESPACE;
        ascii[10] = WHITESPACE;
        ascii[13] = WHITESPACE;
        ascii[32] = WHITESPACE;
        ascii[61] = EQUAL;
    }

    public static final byte[] encode(byte[] bArr) {
        return encode(bArr, 0);
    }

    public static final byte[] encode(byte[] bArr, int i) {
        int i2 = (i / 4) * 4;
        if (i2 < 0) {
            i2 = 0;
        }
        int length = ((bArr.length + 2) / 3) * 4;
        if (i2 > 0) {
            length += (length - 1) / i2;
        }
        byte[] bArr2 = new byte[length];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 + 3 <= bArr.length) {
            int i6 = i3 + 1;
            int i7 = i6 + 1;
            int i8 = ((bArr[i3] & INVALID) << BidiOrder.S) | ((bArr[i6] & INVALID) << 8);
            int i9 = i7 + 1;
            int i10 = i8 | ((bArr[i7] & INVALID) << 0);
            int i11 = i4 + 1;
            bArr2[i4] = base64[(16515072 & i10) >> 18];
            int i12 = i11 + 1;
            bArr2[i11] = base64[(i10 & 258048) >> 12];
            int i13 = i12 + 1;
            bArr2[i12] = base64[(i10 & 4032) >> 6];
            i4 = i13 + 1;
            bArr2[i13] = base64[i10 & 63];
            i5 += 4;
            if (i4 < length && i2 > 0 && i5 % i2 == 0) {
                bArr2[i4] = 10;
                i4++;
            }
            i3 = i9;
        }
        if (bArr.length - i3 == 2) {
            int i14 = ((bArr[i3 + 1] & INVALID) << 8) | ((bArr[i3] & INVALID) << BidiOrder.S);
            int i15 = i4 + 1;
            bArr2[i4] = base64[(i14 & 16515072) >> 18];
            int i16 = i15 + 1;
            bArr2[i15] = base64[(i14 & 258048) >> 12];
            bArr2[i16] = base64[(i14 & 4032) >> 6];
            bArr2[i16 + 1] = DocWriter.EQUALS;
        } else if (bArr.length - i3 == 1) {
            int i17 = (bArr[i3] & INVALID) << BidiOrder.S;
            int i18 = i4 + 1;
            bArr2[i4] = base64[(i17 & 16515072) >> 18];
            int i19 = i18 + 1;
            bArr2[i18] = base64[(i17 & 258048) >> 12];
            bArr2[i19] = DocWriter.EQUALS;
            bArr2[i19 + 1] = DocWriter.EQUALS;
        }
        return bArr2;
    }

    public static final String encode(String str) {
        return new String(encode(str.getBytes()));
    }

    public static final byte[] decode(byte[] bArr) throws IllegalArgumentException {
        int i = 0;
        int i2 = 0;
        for (byte b : bArr) {
            byte b2 = ascii[b];
            if (b2 >= 0) {
                bArr[i2] = b2;
                i2++;
            } else if (b2 == -1) {
                throw new IllegalArgumentException("Invalid base 64 string");
            }
        }
        while (i2 > 0 && bArr[i2 - 1] == -3) {
            i2--;
        }
        byte[] bArr2 = new byte[((i2 * 3) / 4)];
        int i3 = 0;
        while (i < bArr2.length - 2) {
            int i4 = i3 + 1;
            bArr2[i] = (byte) (((bArr[i3] << 2) & 255) | ((bArr[i4] >>> 4) & 3));
            int i5 = i3 + 2;
            bArr2[i + 1] = (byte) (((bArr[i4] << 4) & 255) | ((bArr[i5] >>> 2) & 15));
            bArr2[i + 2] = (byte) (((bArr[i5] << 6) & INVALID) | (bArr[i3 + 3] & 63));
            i3 += 4;
            i += 3;
        }
        if (i < bArr2.length) {
            bArr2[i] = (byte) (((bArr[i3] << 2) & 255) | ((bArr[i3 + 1] >>> 4) & 3));
        }
        int i6 = i + 1;
        if (i6 < bArr2.length) {
            bArr2[i6] = (byte) (((bArr[i3 + 2] >>> 2) & 15) | ((bArr[i3 + 1] << 4) & 255));
        }
        return bArr2;
    }

    public static final String decode(String str) {
        return new String(decode(str.getBytes()));
    }
}
