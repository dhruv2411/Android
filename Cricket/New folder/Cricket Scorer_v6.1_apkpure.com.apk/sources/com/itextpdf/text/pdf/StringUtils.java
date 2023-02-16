package com.itextpdf.text.pdf;

import com.itextpdf.text.DocWriter;

public class StringUtils {
    private static final byte[] b = DocWriter.getISOBytes("\\b");
    private static final byte[] f = DocWriter.getISOBytes("\\f");
    private static final byte[] n = DocWriter.getISOBytes("\\n");
    private static final byte[] r = DocWriter.getISOBytes("\\r");
    private static final byte[] t = DocWriter.getISOBytes("\\t");

    private StringUtils() {
    }

    public static byte[] escapeString(byte[] bArr) {
        ByteBuffer byteBuffer = new ByteBuffer();
        escapeString(bArr, byteBuffer);
        return byteBuffer.toByteArray();
    }

    public static void escapeString(byte[] bArr, ByteBuffer byteBuffer) {
        byteBuffer.append_i(40);
        for (byte b2 : bArr) {
            switch (b2) {
                case 8:
                    byteBuffer.append(b);
                    break;
                case 9:
                    byteBuffer.append(t);
                    break;
                case 10:
                    byteBuffer.append(n);
                    break;
                case 12:
                    byteBuffer.append(f);
                    break;
                case 13:
                    byteBuffer.append(r);
                    break;
                case 40:
                case 41:
                case 92:
                    byteBuffer.append_i(92).append_i(b2);
                    break;
                default:
                    byteBuffer.append_i(b2);
                    break;
            }
        }
        byteBuffer.append_i(41);
    }

    public static byte[] convertCharsToBytes(char[] cArr) {
        byte[] bArr = new byte[(cArr.length * 2)];
        for (int i = 0; i < cArr.length; i++) {
            int i2 = 2 * i;
            bArr[i2] = (byte) (cArr[i] / 256);
            bArr[i2 + 1] = (byte) (cArr[i] % 256);
        }
        return bArr;
    }
}
