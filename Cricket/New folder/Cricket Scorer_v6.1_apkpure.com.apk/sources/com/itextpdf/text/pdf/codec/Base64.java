package com.itextpdf.text.pdf.codec;

import com.itextpdf.text.DocWriter;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.BidiOrder;
import com.itextpdf.text.pdf.ByteBuffer;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;

public class Base64 {
    public static final int DECODE = 0;
    public static final int DONT_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_ENC = -1;
    public static final int GZIP = 2;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    private static final String PREFERRED_ENCODING = "UTF-8";
    public static final int URL_SAFE = 16;
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte[] _ORDERED_ALPHABET = {45, ByteBuffer.ZERO, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
    private static final byte[] _ORDERED_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, EQUALS_SIGN_ENC, -9, -9, -9, BidiOrder.AN, BidiOrder.CS, BidiOrder.NSM, BidiOrder.BN, BidiOrder.B, BidiOrder.S, BidiOrder.WS, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, DocWriter.SPACE, 33, DocWriter.QUOTE, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, DocWriter.FORWARD, ByteBuffer.ZERO, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, DocWriter.LT, 61, DocWriter.GT, 63, -9, -9, -9, -9};
    private static final byte[] _STANDARD_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, ByteBuffer.ZERO, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, DocWriter.FORWARD};
    private static final byte[] _STANDARD_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, DocWriter.GT, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, DocWriter.LT, 61, -9, -9, -9, EQUALS_SIGN_ENC, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, BidiOrder.AN, BidiOrder.CS, BidiOrder.NSM, BidiOrder.BN, BidiOrder.B, BidiOrder.S, BidiOrder.WS, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, DocWriter.SPACE, 33, DocWriter.QUOTE, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, DocWriter.FORWARD, ByteBuffer.ZERO, 49, 50, 51, -9, -9, -9, -9};
    private static final byte[] _URL_SAFE_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, ByteBuffer.ZERO, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] _URL_SAFE_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, DocWriter.GT, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, DocWriter.LT, 61, -9, -9, -9, EQUALS_SIGN_ENC, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, BidiOrder.AN, BidiOrder.CS, BidiOrder.NSM, BidiOrder.BN, BidiOrder.B, BidiOrder.S, BidiOrder.WS, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, DocWriter.SPACE, 33, DocWriter.QUOTE, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, DocWriter.FORWARD, ByteBuffer.ZERO, 49, 50, 51, -9, -9, -9, -9};

    /* access modifiers changed from: private */
    public static final byte[] getAlphabet(int i) {
        if ((i & 16) == 16) {
            return _URL_SAFE_ALPHABET;
        }
        if ((i & 32) == 32) {
            return _ORDERED_ALPHABET;
        }
        return _STANDARD_ALPHABET;
    }

    /* access modifiers changed from: private */
    public static final byte[] getDecodabet(int i) {
        if ((i & 16) == 16) {
            return _URL_SAFE_DECODABET;
        }
        if ((i & 32) == 32) {
            return _ORDERED_DECODABET;
        }
        return _STANDARD_DECODABET;
    }

    private Base64() {
    }

    private static final void usage(String str) {
        System.err.println(str);
        System.err.println("Usage: java Base64 -e|-d inputfile outputfile");
    }

    /* access modifiers changed from: private */
    public static byte[] encode3to4(byte[] bArr, byte[] bArr2, int i, int i2) {
        encode3to4(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    /* access modifiers changed from: private */
    public static byte[] encode3to4(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] alphabet = getAlphabet(i4);
        int i5 = 0;
        int i6 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0);
        if (i2 > 2) {
            i5 = (bArr[i + 2] << 24) >>> 24;
        }
        int i7 = i6 | i5;
        switch (i2) {
            case 1:
                bArr2[i3] = alphabet[i7 >>> 18];
                bArr2[i3 + 1] = alphabet[(i7 >>> 12) & 63];
                bArr2[i3 + 2] = 61;
                bArr2[i3 + 3] = 61;
                return bArr2;
            case 2:
                bArr2[i3] = alphabet[i7 >>> 18];
                bArr2[i3 + 1] = alphabet[(i7 >>> 12) & 63];
                bArr2[i3 + 2] = alphabet[(i7 >>> 6) & 63];
                bArr2[i3 + 3] = 61;
                return bArr2;
            case 3:
                bArr2[i3] = alphabet[i7 >>> 18];
                bArr2[i3 + 1] = alphabet[(i7 >>> 12) & 63];
                bArr2[i3 + 2] = alphabet[(i7 >>> 6) & 63];
                bArr2[i3 + 3] = alphabet[i7 & 63];
                return bArr2;
            default:
                return bArr2;
        }
    }

    public static String encodeObject(Serializable serializable) {
        return encodeObject(serializable, 0);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:47|48|49|50|51|52|53|54|55|56|57) */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:59|60|61|62|63|64|65|66|67|68|69) */
    /* JADX WARNING: Can't wrap try/catch for region: R(13:22|23|24|25|26|27|28|29|30|31|32|33|34) */
    /* JADX WARNING: Can't wrap try/catch for region: R(19:1|2|3|4|5|(5:7|8|9|10|11)(3:19|20|21)|22|23|24|25|26|27|28|29|30|31|32|33|34) */
    /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|(5:7|8|9|10|11)(3:19|20|21)|22|23|24|25|26|27|28|29|30|31|32|33|34) */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0059, code lost:
        return new java.lang.String(r2.toByteArray());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x003b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x003e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0041 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0044 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x0075 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0078 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x007b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0085 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0088 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x008b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encodeObject(java.io.Serializable r6, int r7) {
        /*
            r0 = r7 & 2
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x006a, all -> 0x0065 }
            r2.<init>()     // Catch:{ IOException -> 0x006a, all -> 0x0065 }
            com.itextpdf.text.pdf.codec.Base64$OutputStream r3 = new com.itextpdf.text.pdf.codec.Base64$OutputStream     // Catch:{ IOException -> 0x0060, all -> 0x005c }
            r4 = 1
            r7 = r7 | r4
            r3.<init>(r2, r7)     // Catch:{ IOException -> 0x0060, all -> 0x005c }
            r7 = 2
            if (r0 != r7) goto L_0x002f
            java.util.zip.GZIPOutputStream r7 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x002b, all -> 0x0027 }
            r7.<init>(r3)     // Catch:{ IOException -> 0x002b, all -> 0x0027 }
            java.io.ObjectOutputStream r0 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            r0.<init>(r7)     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            r5 = r0
            r0 = r7
            r7 = r5
            goto L_0x0035
        L_0x0020:
            r6 = move-exception
            goto L_0x0082
        L_0x0023:
            r6 = move-exception
            r0 = r7
            r7 = r1
            goto L_0x006f
        L_0x0027:
            r6 = move-exception
            r7 = r1
            goto L_0x0082
        L_0x002b:
            r6 = move-exception
            r7 = r1
            r0 = r7
            goto L_0x006f
        L_0x002f:
            java.io.ObjectOutputStream r7 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x002b, all -> 0x0027 }
            r7.<init>(r3)     // Catch:{ IOException -> 0x002b, all -> 0x0027 }
            r0 = r1
        L_0x0035:
            r7.writeObject(r6)     // Catch:{ IOException -> 0x005a }
            r7.close()     // Catch:{ Exception -> 0x003b }
        L_0x003b:
            r0.close()     // Catch:{ Exception -> 0x003e }
        L_0x003e:
            r3.close()     // Catch:{ Exception -> 0x0041 }
        L_0x0041:
            r2.close()     // Catch:{ Exception -> 0x0044 }
        L_0x0044:
            java.lang.String r6 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0050 }
            byte[] r7 = r2.toByteArray()     // Catch:{ UnsupportedEncodingException -> 0x0050 }
            java.lang.String r0 = "UTF-8"
            r6.<init>(r7, r0)     // Catch:{ UnsupportedEncodingException -> 0x0050 }
            return r6
        L_0x0050:
            java.lang.String r6 = new java.lang.String
            byte[] r7 = r2.toByteArray()
            r6.<init>(r7)
            return r6
        L_0x005a:
            r6 = move-exception
            goto L_0x006f
        L_0x005c:
            r6 = move-exception
            r7 = r1
            r3 = r7
            goto L_0x0082
        L_0x0060:
            r6 = move-exception
            r7 = r1
            r0 = r7
            r3 = r0
            goto L_0x006f
        L_0x0065:
            r6 = move-exception
            r7 = r1
            r2 = r7
            r3 = r2
            goto L_0x0082
        L_0x006a:
            r6 = move-exception
            r7 = r1
            r0 = r7
            r2 = r0
            r3 = r2
        L_0x006f:
            r6.printStackTrace()     // Catch:{ all -> 0x007f }
            r7.close()     // Catch:{ Exception -> 0x0075 }
        L_0x0075:
            r0.close()     // Catch:{ Exception -> 0x0078 }
        L_0x0078:
            r3.close()     // Catch:{ Exception -> 0x007b }
        L_0x007b:
            r2.close()     // Catch:{ Exception -> 0x007e }
        L_0x007e:
            return r1
        L_0x007f:
            r6 = move-exception
            r1 = r7
            r7 = r0
        L_0x0082:
            r1.close()     // Catch:{ Exception -> 0x0085 }
        L_0x0085:
            r7.close()     // Catch:{ Exception -> 0x0088 }
        L_0x0088:
            r3.close()     // Catch:{ Exception -> 0x008b }
        L_0x008b:
            r2.close()     // Catch:{ Exception -> 0x008e }
        L_0x008e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.Base64.encodeObject(java.io.Serializable, int):java.lang.String");
    }

    public static String encodeBytes(byte[] bArr) {
        return encodeBytes(bArr, 0, bArr.length, 0);
    }

    public static String encodeBytes(byte[] bArr, int i) {
        return encodeBytes(bArr, 0, bArr.length, i);
    }

    public static String encodeBytes(byte[] bArr, int i, int i2) {
        return encodeBytes(bArr, i, i2, 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: com.itextpdf.text.pdf.codec.Base64$OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: com.itextpdf.text.pdf.codec.Base64$OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: com.itextpdf.text.pdf.codec.Base64$OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: com.itextpdf.text.pdf.codec.Base64$OutputStream} */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:10|11|12|13|14|15|16|17|18|19|20) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:39|40|41|42|43|44|45|46|47) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:49|50|51|52|53|54|55|56|57) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x002f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x0067 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x006a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0074 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0077 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encodeBytes(byte[] r18, int r19, int r20, int r21) {
        /*
            r1 = r19
            r2 = r20
            r3 = r21 & 8
            r4 = r21 & 2
            r5 = 1
            r6 = 2
            if (r4 != r6) goto L_0x007b
            r3 = 0
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            r4.<init>()     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            com.itextpdf.text.pdf.codec.Base64$OutputStream r6 = new com.itextpdf.text.pdf.codec.Base64$OutputStream     // Catch:{ IOException -> 0x0053, all -> 0x004f }
            r5 = r5 | r21
            r6.<init>(r4, r5)     // Catch:{ IOException -> 0x0053, all -> 0x004f }
            java.util.zip.GZIPOutputStream r5 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            r5.<init>(r6)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            r9 = r18
            r5.write(r9, r1, r2)     // Catch:{ IOException -> 0x0045 }
            r5.close()     // Catch:{ IOException -> 0x0045 }
            r5.close()     // Catch:{ Exception -> 0x0029 }
        L_0x0029:
            r6.close()     // Catch:{ Exception -> 0x002c }
        L_0x002c:
            r4.close()     // Catch:{ Exception -> 0x002f }
        L_0x002f:
            java.lang.String r1 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x003b }
            byte[] r2 = r4.toByteArray()     // Catch:{ UnsupportedEncodingException -> 0x003b }
            java.lang.String r3 = "UTF-8"
            r1.<init>(r2, r3)     // Catch:{ UnsupportedEncodingException -> 0x003b }
            return r1
        L_0x003b:
            java.lang.String r1 = new java.lang.String
            byte[] r2 = r4.toByteArray()
            r1.<init>(r2)
            return r1
        L_0x0045:
            r0 = move-exception
            r1 = r0
            goto L_0x0061
        L_0x0048:
            r0 = move-exception
            r1 = r0
            goto L_0x0071
        L_0x004b:
            r0 = move-exception
            r1 = r0
            r5 = r3
            goto L_0x0061
        L_0x004f:
            r0 = move-exception
            r1 = r0
            r6 = r3
            goto L_0x0071
        L_0x0053:
            r0 = move-exception
            r1 = r0
            r5 = r3
            goto L_0x0060
        L_0x0057:
            r0 = move-exception
            r1 = r0
            r4 = r3
            r6 = r4
            goto L_0x0071
        L_0x005c:
            r0 = move-exception
            r1 = r0
            r4 = r3
            r5 = r4
        L_0x0060:
            r6 = r5
        L_0x0061:
            r1.printStackTrace()     // Catch:{ all -> 0x006e }
            r5.close()     // Catch:{ Exception -> 0x0067 }
        L_0x0067:
            r6.close()     // Catch:{ Exception -> 0x006a }
        L_0x006a:
            r4.close()     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            return r3
        L_0x006e:
            r0 = move-exception
            r1 = r0
            r3 = r5
        L_0x0071:
            r3.close()     // Catch:{ Exception -> 0x0074 }
        L_0x0074:
            r6.close()     // Catch:{ Exception -> 0x0077 }
        L_0x0077:
            r4.close()     // Catch:{ Exception -> 0x007a }
        L_0x007a:
            throw r1
        L_0x007b:
            r9 = r18
            if (r3 != 0) goto L_0x0081
            r11 = r5
            goto L_0x0082
        L_0x0081:
            r11 = 0
        L_0x0082:
            int r3 = r2 * 4
            int r3 = r3 / 3
            int r4 = r2 % 3
            r12 = 4
            if (r4 <= 0) goto L_0x008d
            r4 = r12
            goto L_0x008e
        L_0x008d:
            r4 = 0
        L_0x008e:
            int r4 = r4 + r3
            r13 = 76
            if (r11 == 0) goto L_0x0095
            int r3 = r3 / r13
            goto L_0x0096
        L_0x0095:
            r3 = 0
        L_0x0096:
            int r4 = r4 + r3
            byte[] r14 = new byte[r4]
            int r8 = r2 + -2
            r7 = 0
            r15 = 0
            r16 = 0
        L_0x009f:
            if (r7 >= r8) goto L_0x00c8
            int r4 = r7 + r1
            r5 = 3
            r3 = r9
            r6 = r14
            r10 = r7
            r7 = r15
            r17 = r8
            r8 = r21
            encode3to4(r3, r4, r5, r6, r7, r8)
            int r3 = r16 + 4
            if (r11 == 0) goto L_0x00c0
            if (r3 != r13) goto L_0x00c0
            int r3 = r15 + 4
            r4 = 10
            r14[r3] = r4
            int r15 = r15 + 1
            r16 = 0
            goto L_0x00c2
        L_0x00c0:
            r16 = r3
        L_0x00c2:
            int r7 = r10 + 3
            int r15 = r15 + r12
            r8 = r17
            goto L_0x009f
        L_0x00c8:
            r10 = r7
            if (r10 >= r2) goto L_0x00db
            int r3 = r10 + r1
            int r4 = r2 - r10
            r1 = r9
            r2 = r3
            r3 = r4
            r4 = r14
            r5 = r15
            r6 = r21
            encode3to4(r1, r2, r3, r4, r5, r6)
            int r15 = r15 + 4
        L_0x00db:
            r1 = r15
            java.lang.String r2 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x00e5 }
            java.lang.String r3 = "UTF-8"
            r4 = 0
            r2.<init>(r14, r4, r1, r3)     // Catch:{ UnsupportedEncodingException -> 0x00e6 }
            return r2
        L_0x00e5:
            r4 = 0
        L_0x00e6:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r14, r4, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.Base64.encodeBytes(byte[], int, int, int):java.lang.String");
    }

    /* access modifiers changed from: private */
    public static int decode4to3(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        byte[] decodabet = getDecodabet(i3);
        int i4 = i + 2;
        if (bArr[i4] == 61) {
            bArr2[i2] = (byte) ((((decodabet[bArr[i + 1]] & EQUALS_SIGN_ENC) << BidiOrder.CS) | ((decodabet[bArr[i]] & EQUALS_SIGN_ENC) << 18)) >>> 16);
            return 1;
        }
        int i5 = i + 3;
        if (bArr[i5] == 61) {
            int i6 = (decodabet[bArr[i + 1]] & EQUALS_SIGN_ENC) << BidiOrder.CS;
            int i7 = ((decodabet[bArr[i4]] & EQUALS_SIGN_ENC) << 6) | i6 | ((decodabet[bArr[i]] & EQUALS_SIGN_ENC) << 18);
            bArr2[i2] = (byte) (i7 >>> 16);
            bArr2[i2 + 1] = (byte) (i7 >>> 8);
            return 2;
        }
        try {
            byte b = ((decodabet[bArr[i]] & EQUALS_SIGN_ENC) << 18) | ((decodabet[bArr[i + 1]] & EQUALS_SIGN_ENC) << BidiOrder.CS) | ((decodabet[bArr[i4]] & EQUALS_SIGN_ENC) << 6) | (decodabet[bArr[i5]] & EQUALS_SIGN_ENC);
            bArr2[i2] = (byte) (b >> BidiOrder.S);
            bArr2[i2 + 1] = (byte) (b >> 8);
            bArr2[i2 + 2] = (byte) b;
            return 3;
        } catch (Exception unused) {
            PrintStream printStream = System.out;
            printStream.println("" + bArr[i] + ": " + decodabet[bArr[i]]);
            PrintStream printStream2 = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("");
            int i8 = i + 1;
            sb.append(bArr[i8]);
            sb.append(": ");
            sb.append(decodabet[bArr[i8]]);
            printStream2.println(sb.toString());
            PrintStream printStream3 = System.out;
            printStream3.println("" + bArr[i4] + ": " + decodabet[bArr[i4]]);
            PrintStream printStream4 = System.out;
            printStream4.println("" + bArr[i5] + ": " + decodabet[bArr[i5]]);
            return -1;
        }
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        byte[] decodabet = getDecodabet(i3);
        byte[] bArr2 = new byte[((i2 * 3) / 4)];
        byte[] bArr3 = new byte[4];
        int i4 = i;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i + i2) {
            byte b = (byte) (bArr[i4] & Byte.MAX_VALUE);
            byte b2 = decodabet[b];
            if (b2 >= -5) {
                if (b2 >= -1) {
                    int i7 = i5 + 1;
                    bArr3[i5] = b;
                    if (i7 > 3) {
                        i6 += decode4to3(bArr3, 0, bArr2, i6, i3);
                        if (b == 61) {
                            break;
                        }
                        i5 = 0;
                    } else {
                        i5 = i7;
                    }
                }
                i4++;
            } else {
                System.err.println("Bad Base64 input character at " + i4 + ": " + bArr[i4] + "(decimal)");
                return null;
            }
        }
        byte[] bArr4 = new byte[i6];
        System.arraycopy(bArr2, 0, bArr4, 0, i6);
        return bArr4;
    }

    public static byte[] decode(String str) {
        return decode(str, 0);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:32|31|50|51|52|53|54|55|56|60) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:16|17|(3:18|19|(1:21)(1:59))|22|23|24|25|26|(3:27|28|62)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:29|30|40|41|42|43|44|45|46) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x004f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0052 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x0068 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x006b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:50:0x0071 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x0074 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x0077 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decode(java.lang.String r5, int r6) {
        /*
            java.lang.String r0 = "UTF-8"
            byte[] r0 = r5.getBytes(r0)     // Catch:{ UnsupportedEncodingException -> 0x0007 }
            goto L_0x000b
        L_0x0007:
            byte[] r0 = r5.getBytes()
        L_0x000b:
            r5 = 0
            int r1 = r0.length
            byte[] r6 = decode(r0, r5, r1, r6)
            if (r6 == 0) goto L_0x007a
            int r0 = r6.length
            r1 = 4
            if (r0 < r1) goto L_0x007a
            byte r0 = r6[r5]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 1
            byte r1 = r6[r1]
            int r1 = r1 << 8
            r2 = 65280(0xff00, float:9.1477E-41)
            r1 = r1 & r2
            r0 = r0 | r1
            r1 = 35615(0x8b1f, float:4.9907E-41)
            if (r1 != r0) goto L_0x007a
            r0 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x006f, all -> 0x0062 }
            r2.<init>()     // Catch:{ IOException -> 0x006f, all -> 0x0062 }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r3.<init>(r6)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x0071, all -> 0x005b }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0071, all -> 0x005b }
        L_0x003e:
            int r1 = r4.read(r0)     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            if (r1 < 0) goto L_0x0048
            r2.write(r0, r5, r1)     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            goto L_0x003e
        L_0x0048:
            byte[] r5 = r2.toByteArray()     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            r2.close()     // Catch:{ Exception -> 0x004f }
        L_0x004f:
            r4.close()     // Catch:{ Exception -> 0x0052 }
        L_0x0052:
            r3.close()     // Catch:{ Exception -> 0x007b }
            goto L_0x007b
        L_0x0056:
            r5 = move-exception
            r1 = r4
            goto L_0x0065
        L_0x0059:
            r1 = r4
            goto L_0x0071
        L_0x005b:
            r5 = move-exception
            goto L_0x0065
        L_0x005d:
            r5 = move-exception
            r3 = r1
            goto L_0x0065
        L_0x0060:
            r3 = r1
            goto L_0x0071
        L_0x0062:
            r5 = move-exception
            r2 = r1
            r3 = r2
        L_0x0065:
            r2.close()     // Catch:{ Exception -> 0x0068 }
        L_0x0068:
            r1.close()     // Catch:{ Exception -> 0x006b }
        L_0x006b:
            r3.close()     // Catch:{ Exception -> 0x006e }
        L_0x006e:
            throw r5
        L_0x006f:
            r2 = r1
            r3 = r2
        L_0x0071:
            r2.close()     // Catch:{ Exception -> 0x0074 }
        L_0x0074:
            r1.close()     // Catch:{ Exception -> 0x0077 }
        L_0x0077:
            r3.close()     // Catch:{ Exception -> 0x007a }
        L_0x007a:
            r5 = r6
        L_0x007b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.Base64.decode(java.lang.String, int):byte[]");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:1|2|3|4|5|6|7|8|9|10|11|44) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:15|37|38|39|40|41) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:14|32|33|26|27|28|29|45) */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0033 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0046 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0016 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:32:0x003a=Splitter:B:32:0x003a, B:24:0x002d=Splitter:B:24:0x002d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object decodeToObject(java.lang.String r4) {
        /*
            byte[] r4 = decode(r4)
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0037, ClassNotFoundException -> 0x002a, all -> 0x0027 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0037, ClassNotFoundException -> 0x002a, all -> 0x0027 }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0024, ClassNotFoundException -> 0x0021, all -> 0x001f }
            r4.<init>(r1)     // Catch:{ IOException -> 0x0024, ClassNotFoundException -> 0x0021, all -> 0x001f }
            java.lang.Object r2 = r4.readObject()     // Catch:{ IOException -> 0x001d, ClassNotFoundException -> 0x001b }
            r1.close()     // Catch:{ Exception -> 0x0016 }
        L_0x0016:
            r4.close()     // Catch:{ Exception -> 0x0019 }
        L_0x0019:
            r0 = r2
            goto L_0x003e
        L_0x001b:
            r2 = move-exception
            goto L_0x002d
        L_0x001d:
            r2 = move-exception
            goto L_0x003a
        L_0x001f:
            r4 = move-exception
            goto L_0x0043
        L_0x0021:
            r2 = move-exception
            r4 = r0
            goto L_0x002d
        L_0x0024:
            r2 = move-exception
            r4 = r0
            goto L_0x003a
        L_0x0027:
            r4 = move-exception
            r1 = r0
            goto L_0x0043
        L_0x002a:
            r2 = move-exception
            r4 = r0
            r1 = r4
        L_0x002d:
            r2.printStackTrace()     // Catch:{ all -> 0x003f }
        L_0x0030:
            r1.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            r4.close()     // Catch:{ Exception -> 0x003e }
            goto L_0x003e
        L_0x0037:
            r2 = move-exception
            r4 = r0
            r1 = r4
        L_0x003a:
            r2.printStackTrace()     // Catch:{ all -> 0x003f }
            goto L_0x0030
        L_0x003e:
            return r0
        L_0x003f:
            r0 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
        L_0x0043:
            r1.close()     // Catch:{ Exception -> 0x0046 }
        L_0x0046:
            r0.close()     // Catch:{ Exception -> 0x0049 }
        L_0x0049:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.Base64.decodeToObject(java.lang.String):java.lang.Object");
    }

    public static boolean encodeToFile(byte[] bArr, String str) {
        boolean z = true;
        OutputStream outputStream = null;
        try {
            OutputStream outputStream2 = new OutputStream(new FileOutputStream(str), 1);
            try {
                outputStream2.write(bArr);
                try {
                    outputStream2.close();
                } catch (Exception unused) {
                }
            } catch (IOException unused2) {
                outputStream = outputStream2;
                z = false;
                outputStream.close();
                return z;
            } catch (Throwable th) {
                th = th;
                outputStream = outputStream2;
                try {
                    outputStream.close();
                } catch (Exception unused3) {
                }
                throw th;
            }
        } catch (IOException unused4) {
            z = false;
            outputStream.close();
            return z;
        } catch (Throwable th2) {
            th = th2;
            outputStream.close();
            throw th;
        }
        return z;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean decodeToFile(java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            r1 = 0
            com.itextpdf.text.pdf.codec.Base64$OutputStream r2 = new com.itextpdf.text.pdf.codec.Base64$OutputStream     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            r3.<init>(r5)     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            r2.<init>(r3, r0)     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            java.lang.String r5 = "UTF-8"
            byte[] r4 = r4.getBytes(r5)     // Catch:{ IOException -> 0x001d, all -> 0x001a }
            r2.write(r4)     // Catch:{ IOException -> 0x001d, all -> 0x001a }
            r0 = 1
            r2.close()     // Catch:{ Exception -> 0x0027 }
            goto L_0x0027
        L_0x001a:
            r4 = move-exception
            r1 = r2
            goto L_0x0020
        L_0x001d:
            r1 = r2
            goto L_0x0024
        L_0x001f:
            r4 = move-exception
        L_0x0020:
            r1.close()     // Catch:{ Exception -> 0x0023 }
        L_0x0023:
            throw r4
        L_0x0024:
            r1.close()     // Catch:{ Exception -> 0x0027 }
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.Base64.decodeToFile(java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005e, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007f A[SYNTHETIC, Splitter:B:31:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0085 A[SYNTHETIC, Splitter:B:36:0x0085] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decodeFromFile(java.lang.String r7) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0066 }
            r1.<init>(r7)     // Catch:{ IOException -> 0x0066 }
            long r2 = r1.length()     // Catch:{ IOException -> 0x0066 }
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0031
            java.io.PrintStream r2 = java.lang.System.err     // Catch:{ IOException -> 0x0066 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0066 }
            r3.<init>()     // Catch:{ IOException -> 0x0066 }
            java.lang.String r4 = "File is too big for this convenience method ("
            r3.append(r4)     // Catch:{ IOException -> 0x0066 }
            long r4 = r1.length()     // Catch:{ IOException -> 0x0066 }
            r3.append(r4)     // Catch:{ IOException -> 0x0066 }
            java.lang.String r1 = " bytes)."
            r3.append(r1)     // Catch:{ IOException -> 0x0066 }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x0066 }
            r2.println(r1)     // Catch:{ IOException -> 0x0066 }
            return r0
        L_0x0031:
            long r2 = r1.length()     // Catch:{ IOException -> 0x0066 }
            int r2 = (int) r2     // Catch:{ IOException -> 0x0066 }
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0066 }
            com.itextpdf.text.pdf.codec.Base64$InputStream r3 = new com.itextpdf.text.pdf.codec.Base64$InputStream     // Catch:{ IOException -> 0x0066 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0066 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0066 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0066 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0066 }
            r1 = 0
            r3.<init>(r4, r1)     // Catch:{ IOException -> 0x0066 }
            r4 = r1
        L_0x0049:
            r5 = 4096(0x1000, float:5.74E-42)
            int r5 = r3.read(r2, r4, r5)     // Catch:{ IOException -> 0x0061, all -> 0x005e }
            if (r5 < 0) goto L_0x0053
            int r4 = r4 + r5
            goto L_0x0049
        L_0x0053:
            byte[] r5 = new byte[r4]     // Catch:{ IOException -> 0x0061, all -> 0x005e }
            java.lang.System.arraycopy(r2, r1, r5, r1, r4)     // Catch:{ IOException -> 0x0062, all -> 0x005e }
            if (r3 == 0) goto L_0x0082
            r3.close()     // Catch:{ Exception -> 0x0082 }
            goto L_0x0082
        L_0x005e:
            r7 = move-exception
            r0 = r3
            goto L_0x0083
        L_0x0061:
            r5 = r0
        L_0x0062:
            r0 = r3
            goto L_0x0067
        L_0x0064:
            r7 = move-exception
            goto L_0x0083
        L_0x0066:
            r5 = r0
        L_0x0067:
            java.io.PrintStream r1 = java.lang.System.err     // Catch:{ all -> 0x0064 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            r2.<init>()     // Catch:{ all -> 0x0064 }
            java.lang.String r3 = "Error decoding from file "
            r2.append(r3)     // Catch:{ all -> 0x0064 }
            r2.append(r7)     // Catch:{ all -> 0x0064 }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x0064 }
            r1.println(r7)     // Catch:{ all -> 0x0064 }
            if (r0 == 0) goto L_0x0082
            r0.close()     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            return r5
        L_0x0083:
            if (r0 == 0) goto L_0x0088
            r0.close()     // Catch:{ Exception -> 0x0088 }
        L_0x0088:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.Base64.decodeFromFile(java.lang.String):byte[]");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:18|19|(3:20|21|29)) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        java.lang.System.err.println("Error encoding from file " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0060, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0046 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encodeFromFile(java.lang.String r7) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            r1.<init>(r7)     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            long r2 = r1.length()     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            double r2 = (double) r2     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            r4 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            double r2 = r2 * r4
            int r2 = (int) r2     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            r3 = 40
            int r2 = java.lang.Math.max(r2, r3)     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            com.itextpdf.text.pdf.codec.Base64$InputStream r3 = new com.itextpdf.text.pdf.codec.Base64$InputStream     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            r1 = 1
            r3.<init>(r4, r1)     // Catch:{ IOException -> 0x0045, all -> 0x0042 }
            r1 = 0
            r4 = r1
        L_0x002c:
            r5 = 4096(0x1000, float:5.74E-42)
            int r5 = r3.read(r2, r4, r5)     // Catch:{ IOException -> 0x0046 }
            if (r5 < 0) goto L_0x0036
            int r4 = r4 + r5
            goto L_0x002c
        L_0x0036:
            java.lang.String r5 = new java.lang.String     // Catch:{ IOException -> 0x0046 }
            java.lang.String r6 = "UTF-8"
            r5.<init>(r2, r1, r4, r6)     // Catch:{ IOException -> 0x0046 }
            r3.close()     // Catch:{ Exception -> 0x0040 }
        L_0x0040:
            r0 = r5
            goto L_0x005f
        L_0x0042:
            r7 = move-exception
            r3 = r0
            goto L_0x0061
        L_0x0045:
            r3 = r0
        L_0x0046:
            java.io.PrintStream r1 = java.lang.System.err     // Catch:{ all -> 0x0060 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0060 }
            r2.<init>()     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = "Error encoding from file "
            r2.append(r4)     // Catch:{ all -> 0x0060 }
            r2.append(r7)     // Catch:{ all -> 0x0060 }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x0060 }
            r1.println(r7)     // Catch:{ all -> 0x0060 }
            r3.close()     // Catch:{ Exception -> 0x005f }
        L_0x005f:
            return r0
        L_0x0060:
            r7 = move-exception
        L_0x0061:
            r3.close()     // Catch:{ Exception -> 0x0064 }
        L_0x0064:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.Base64.encodeFromFile(java.lang.String):java.lang.String");
    }

    public static void encodeFileToFile(String str, String str2) {
        String encodeFromFile = encodeFromFile(str);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str2));
            try {
                bufferedOutputStream2.write(encodeFromFile.getBytes("US-ASCII"));
            } catch (IOException e) {
                e = e;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    e.printStackTrace();
                    bufferedOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception unused) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = bufferedOutputStream2;
                bufferedOutputStream.close();
                throw th;
            }
            try {
                bufferedOutputStream2.close();
            } catch (Exception unused2) {
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            bufferedOutputStream.close();
        }
    }

    public static void decodeFileToFile(String str, String str2) {
        byte[] decodeFromFile = decodeFromFile(str);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str2));
            try {
                bufferedOutputStream2.write(decodeFromFile);
            } catch (IOException e) {
                e = e;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    e.printStackTrace();
                    bufferedOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception unused) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = bufferedOutputStream2;
                bufferedOutputStream.close();
                throw th;
            }
            try {
                bufferedOutputStream2.close();
            } catch (Exception unused2) {
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            bufferedOutputStream.close();
        }
    }

    public static class InputStream extends FilterInputStream {
        private byte[] alphabet;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int numSigBytes;
        private int options;
        private int position;

        public InputStream(java.io.InputStream inputStream) {
            this(inputStream, 0);
        }

        public InputStream(java.io.InputStream inputStream, int i) {
            super(inputStream);
            boolean z = true;
            this.breakLines = (i & 8) != 8;
            this.encode = (i & 1) != 1 ? false : z;
            this.bufferLength = this.encode ? 4 : 3;
            this.buffer = new byte[this.bufferLength];
            this.position = -1;
            this.lineLength = 0;
            this.options = i;
            this.alphabet = Base64.getAlphabet(i);
            this.decodabet = Base64.getDecodabet(i);
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x0051 A[LOOP:1: B:19:0x003b->B:25:0x0051, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0057 A[EDGE_INSN: B:58:0x0057->B:26:0x0057 ?: BREAK  , SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int read() throws java.io.IOException {
            /*
                r10 = this;
                int r0 = r10.position
                r1 = -1
                r2 = 0
                if (r0 >= 0) goto L_0x0077
                boolean r0 = r10.encode
                r3 = 4
                if (r0 == 0) goto L_0x0038
                r0 = 3
                byte[] r4 = new byte[r0]
                r5 = r2
                r6 = r5
            L_0x0010:
                if (r5 >= r0) goto L_0x0027
                java.io.InputStream r7 = r10.in     // Catch:{ IOException -> 0x0020 }
                int r7 = r7.read()     // Catch:{ IOException -> 0x0020 }
                if (r7 < 0) goto L_0x0024
                byte r7 = (byte) r7     // Catch:{ IOException -> 0x0020 }
                r4[r5] = r7     // Catch:{ IOException -> 0x0020 }
                int r6 = r6 + 1
                goto L_0x0024
            L_0x0020:
                r7 = move-exception
                if (r5 != 0) goto L_0x0024
                throw r7
            L_0x0024:
                int r5 = r5 + 1
                goto L_0x0010
            L_0x0027:
                if (r6 <= 0) goto L_0x0037
                r5 = 0
                byte[] r7 = r10.buffer
                r8 = 0
                int r9 = r10.options
                byte[] unused = com.itextpdf.text.pdf.codec.Base64.encode3to4(r4, r5, r6, r7, r8, r9)
                r10.position = r2
                r10.numSigBytes = r3
                goto L_0x0077
            L_0x0037:
                return r1
            L_0x0038:
                byte[] r0 = new byte[r3]
                r4 = r2
            L_0x003b:
                if (r4 >= r3) goto L_0x0057
            L_0x003d:
                java.io.InputStream r5 = r10.in
                int r5 = r5.read()
                if (r5 < 0) goto L_0x004e
                byte[] r6 = r10.decodabet
                r7 = r5 & 127(0x7f, float:1.78E-43)
                byte r6 = r6[r7]
                r7 = -5
                if (r6 <= r7) goto L_0x003d
            L_0x004e:
                if (r5 >= 0) goto L_0x0051
                goto L_0x0057
            L_0x0051:
                byte r5 = (byte) r5
                r0[r4] = r5
                int r4 = r4 + 1
                goto L_0x003b
            L_0x0057:
                if (r4 != r3) goto L_0x0066
                byte[] r3 = r10.buffer
                int r4 = r10.options
                int r0 = com.itextpdf.text.pdf.codec.Base64.decode4to3(r0, r2, r3, r2, r4)
                r10.numSigBytes = r0
                r10.position = r2
                goto L_0x0077
            L_0x0066:
                if (r4 != 0) goto L_0x0069
                return r1
            L_0x0069:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "improperly.padded.base64.input"
                java.lang.Object[] r2 = new java.lang.Object[r2]
                java.lang.String r1 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r1, (java.lang.Object[]) r2)
                r0.<init>(r1)
                throw r0
            L_0x0077:
                int r0 = r10.position
                if (r0 < 0) goto L_0x00b0
                int r0 = r10.position
                int r3 = r10.numSigBytes
                if (r0 < r3) goto L_0x0082
                return r1
            L_0x0082:
                boolean r0 = r10.encode
                if (r0 == 0) goto L_0x0095
                boolean r0 = r10.breakLines
                if (r0 == 0) goto L_0x0095
                int r0 = r10.lineLength
                r3 = 76
                if (r0 < r3) goto L_0x0095
                r10.lineLength = r2
                r0 = 10
                return r0
            L_0x0095:
                int r0 = r10.lineLength
                int r0 = r0 + 1
                r10.lineLength = r0
                byte[] r0 = r10.buffer
                int r2 = r10.position
                int r3 = r2 + 1
                r10.position = r3
                byte r0 = r0[r2]
                int r2 = r10.position
                int r3 = r10.bufferLength
                if (r2 < r3) goto L_0x00ad
                r10.position = r1
            L_0x00ad:
                r0 = r0 & 255(0xff, float:3.57E-43)
                return r0
            L_0x00b0:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "error.in.base64.code.reading.stream"
                java.lang.Object[] r2 = new java.lang.Object[r2]
                java.lang.String r1 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r1, (java.lang.Object[]) r2)
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.Base64.InputStream.read():int");
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                int read = read();
                if (read >= 0) {
                    bArr[i + i3] = (byte) read;
                    i3++;
                } else if (i3 == 0) {
                    return -1;
                }
            }
            return i3;
        }
    }

    public static class OutputStream extends FilterOutputStream {
        private byte[] alphabet;
        private byte[] b4;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;

        public OutputStream(java.io.OutputStream outputStream) {
            this(outputStream, 1);
        }

        public OutputStream(java.io.OutputStream outputStream, int i) {
            super(outputStream);
            boolean z = true;
            this.breakLines = (i & 8) != 8;
            this.encode = (i & 1) != 1 ? false : z;
            this.bufferLength = this.encode ? 3 : 4;
            this.buffer = new byte[this.bufferLength];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = false;
            this.b4 = new byte[4];
            this.options = i;
            this.alphabet = Base64.getAlphabet(i);
            this.decodabet = Base64.getDecodabet(i);
        }

        public void write(int i) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(i);
            } else if (this.encode) {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                if (this.position >= this.bufferLength) {
                    this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                    this.lineLength += 4;
                    if (this.breakLines && this.lineLength >= 76) {
                        this.out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                }
            } else {
                int i3 = i & 127;
                if (this.decodabet[i3] > -5) {
                    byte[] bArr2 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    bArr2[i4] = (byte) i;
                    if (this.position >= this.bufferLength) {
                        this.out.write(this.b4, 0, Base64.decode4to3(this.buffer, 0, this.b4, 0, this.options));
                        this.position = 0;
                    }
                } else if (this.decodabet[i3] != -5) {
                    throw new IOException(MessageLocalization.getComposedMessage("invalid.character.in.base64.data", new Object[0]));
                }
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void flushBase64() throws IOException {
            if (this.position <= 0) {
                return;
            }
            if (this.encode) {
                this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position, this.options));
                this.position = 0;
                return;
            }
            throw new IOException(MessageLocalization.getComposedMessage("base64.input.not.properly.padded", new Object[0]));
        }

        public void close() throws IOException {
            flushBase64();
            super.close();
            this.buffer = null;
            this.out = null;
        }

        public void suspendEncoding() throws IOException {
            flushBase64();
            this.suspendEncoding = true;
        }

        public void resumeEncoding() {
            this.suspendEncoding = false;
        }
    }
}
