package com.itextpdf.text;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.itextpdf.xmp.XMPError;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Jpeg extends Image {
    public static final byte[] JFIF_ID = {74, 70, 73, 70, 0};
    public static final int M_APP0 = 224;
    public static final int M_APP2 = 226;
    public static final int M_APPD = 237;
    public static final int M_APPE = 238;
    public static final int NOPARAM_MARKER = 2;
    public static final int[] NOPARAM_MARKERS = {208, 209, 210, 211, 212, 213, 214, 215, 216, 1};
    public static final int NOT_A_MARKER = -1;
    public static final byte[] PS_8BIM_RESO = {56, 66, 73, 77, 3, -19};
    public static final int UNSUPPORTED_MARKER = 1;
    public static final int[] UNSUPPORTED_MARKERS = {195, 197, 198, 199, 200, XMPError.BADXML, XMPError.BADRDF, XMPError.BADXMP, 205, 206, 207};
    public static final int VALID_MARKER = 0;
    public static final int[] VALID_MARKERS = {PsExtractor.AUDIO_STREAM, 193, 194};
    private byte[][] icc;

    Jpeg(Image image) {
        super(image);
    }

    public Jpeg(URL url) throws BadElementException, IOException {
        super(url);
        processParameters();
    }

    public Jpeg(byte[] bArr) throws BadElementException, IOException {
        super((URL) null);
        this.rawData = bArr;
        this.originalData = bArr;
        processParameters();
    }

    public Jpeg(byte[] bArr, float f, float f2) throws BadElementException, IOException {
        this(bArr);
        this.scaledWidth = f;
        this.scaledHeight = f2;
    }

    private static final int getShort(InputStream inputStream) throws IOException {
        return (inputStream.read() << 8) + inputStream.read();
    }

    private static final int marker(int i) {
        for (int i2 : VALID_MARKERS) {
            if (i == i2) {
                return 0;
            }
        }
        for (int i3 : NOPARAM_MARKERS) {
            if (i == i3) {
                return 2;
            }
        }
        for (int i4 : UNSUPPORTED_MARKERS) {
            if (i == i4) {
                return 1;
            }
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:173:0x0317  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processParameters() throws com.itextpdf.text.BadElementException, java.io.IOException {
        /*
            r18 = this;
            r1 = r18
            r2 = 32
            r1.type = r2
            r2 = 1
            r1.originalType = r2
            byte[] r4 = r1.rawData     // Catch:{ all -> 0x0311 }
            if (r4 != 0) goto L_0x0023
            java.net.URL r4 = r1.url     // Catch:{ all -> 0x001e }
            java.io.InputStream r4 = r4.openStream()     // Catch:{ all -> 0x001e }
            java.net.URL r5 = r1.url     // Catch:{ all -> 0x001a }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x001a }
            goto L_0x002c
        L_0x001a:
            r0 = move-exception
            r2 = r0
            goto L_0x0315
        L_0x001e:
            r0 = move-exception
            r2 = r0
            r4 = 0
            goto L_0x0315
        L_0x0023:
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0311 }
            byte[] r5 = r1.rawData     // Catch:{ all -> 0x0311 }
            r4.<init>(r5)     // Catch:{ all -> 0x0311 }
            java.lang.String r5 = "Byte array"
        L_0x002c:
            int r6 = r4.read()     // Catch:{ all -> 0x001a }
            r7 = 255(0xff, float:3.57E-43)
            r8 = 0
            if (r6 != r7) goto L_0x0300
            int r6 = r4.read()     // Catch:{ all -> 0x001a }
            r9 = 216(0xd8, float:3.03E-43)
            if (r6 == r9) goto L_0x003f
            goto L_0x0300
        L_0x003f:
            r6 = r2
        L_0x0040:
            int r9 = r4.read()     // Catch:{ all -> 0x001a }
            if (r9 >= 0) goto L_0x0054
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x001a }
            java.lang.String r3 = "premature.eof.while.reading.jpg"
            java.lang.Object[] r5 = new java.lang.Object[r8]     // Catch:{ all -> 0x001a }
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r5)     // Catch:{ all -> 0x001a }
            r2.<init>(r3)     // Catch:{ all -> 0x001a }
            throw r2     // Catch:{ all -> 0x001a }
        L_0x0054:
            if (r9 != r7) goto L_0x02fc
            int r9 = r4.read()     // Catch:{ all -> 0x001a }
            r10 = 16
            r11 = 1056964608(0x3f000000, float:0.5)
            r12 = 1076006748(0x40228f5c, float:2.54)
            r13 = 2
            if (r6 == 0) goto L_0x00dc
            r14 = 224(0xe0, float:3.14E-43)
            if (r9 != r14) goto L_0x00dc
            int r6 = getShort(r4)     // Catch:{ all -> 0x001a }
            if (r6 >= r10) goto L_0x0074
            int r6 = r6 + -2
            com.itextpdf.text.Utilities.skip(r4, r6)     // Catch:{ all -> 0x001a }
            goto L_0x00d9
        L_0x0074:
            byte[] r9 = JFIF_ID     // Catch:{ all -> 0x001a }
            int r9 = r9.length     // Catch:{ all -> 0x001a }
            byte[] r9 = new byte[r9]     // Catch:{ all -> 0x001a }
            int r10 = r4.read(r9)     // Catch:{ all -> 0x001a }
            int r14 = r9.length     // Catch:{ all -> 0x001a }
            if (r10 == r14) goto L_0x0090
            com.itextpdf.text.BadElementException r3 = new com.itextpdf.text.BadElementException     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "1.corrupted.jfif.marker"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x001a }
            r2[r8] = r5     // Catch:{ all -> 0x001a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r6, (java.lang.Object[]) r2)     // Catch:{ all -> 0x001a }
            r3.<init>((java.lang.String) r2)     // Catch:{ all -> 0x001a }
            throw r3     // Catch:{ all -> 0x001a }
        L_0x0090:
            r10 = r8
        L_0x0091:
            int r14 = r9.length     // Catch:{ all -> 0x001a }
            if (r10 >= r14) goto L_0x00a1
            byte r14 = r9[r10]     // Catch:{ all -> 0x001a }
            byte[] r15 = JFIF_ID     // Catch:{ all -> 0x001a }
            byte r15 = r15[r10]     // Catch:{ all -> 0x001a }
            if (r14 == r15) goto L_0x009e
            r10 = r8
            goto L_0x00a2
        L_0x009e:
            int r10 = r10 + 1
            goto L_0x0091
        L_0x00a1:
            r10 = r2
        L_0x00a2:
            if (r10 != 0) goto L_0x00ac
            int r6 = r6 + -2
            int r9 = r9.length     // Catch:{ all -> 0x001a }
            int r6 = r6 - r9
            com.itextpdf.text.Utilities.skip(r4, r6)     // Catch:{ all -> 0x001a }
            goto L_0x00d9
        L_0x00ac:
            com.itextpdf.text.Utilities.skip(r4, r13)     // Catch:{ all -> 0x001a }
            int r10 = r4.read()     // Catch:{ all -> 0x001a }
            int r14 = getShort(r4)     // Catch:{ all -> 0x001a }
            int r15 = getShort(r4)     // Catch:{ all -> 0x001a }
            if (r10 != r2) goto L_0x00c2
            r1.dpiX = r14     // Catch:{ all -> 0x001a }
            r1.dpiY = r15     // Catch:{ all -> 0x001a }
            goto L_0x00d0
        L_0x00c2:
            if (r10 != r13) goto L_0x00d0
            float r10 = (float) r14     // Catch:{ all -> 0x001a }
            float r10 = r10 * r12
            float r10 = r10 + r11
            int r10 = (int) r10     // Catch:{ all -> 0x001a }
            r1.dpiX = r10     // Catch:{ all -> 0x001a }
            float r10 = (float) r15     // Catch:{ all -> 0x001a }
            float r10 = r10 * r12
            float r10 = r10 + r11
            int r10 = (int) r10     // Catch:{ all -> 0x001a }
            r1.dpiY = r10     // Catch:{ all -> 0x001a }
        L_0x00d0:
            int r6 = r6 + -2
            int r9 = r9.length     // Catch:{ all -> 0x001a }
            int r6 = r6 - r9
            int r6 = r6 + -7
            com.itextpdf.text.Utilities.skip(r4, r6)     // Catch:{ all -> 0x001a }
        L_0x00d9:
            r6 = r8
            goto L_0x0040
        L_0x00dc:
            r14 = 238(0xee, float:3.34E-43)
            r15 = 12
            if (r9 != r14) goto L_0x010d
            int r9 = getShort(r4)     // Catch:{ all -> 0x001a }
            int r9 = r9 - r13
            byte[] r10 = new byte[r9]     // Catch:{ all -> 0x001a }
            r11 = r8
        L_0x00ea:
            if (r11 >= r9) goto L_0x00f6
            int r12 = r4.read()     // Catch:{ all -> 0x001a }
            byte r12 = (byte) r12     // Catch:{ all -> 0x001a }
            r10[r11] = r12     // Catch:{ all -> 0x001a }
            int r11 = r11 + 1
            goto L_0x00ea
        L_0x00f6:
            int r9 = r10.length     // Catch:{ all -> 0x001a }
            if (r9 < r15) goto L_0x02fd
            java.lang.String r9 = new java.lang.String     // Catch:{ all -> 0x001a }
            r11 = 5
            java.lang.String r12 = "ISO-8859-1"
            r9.<init>(r10, r8, r11, r12)     // Catch:{ all -> 0x001a }
            java.lang.String r10 = "Adobe"
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x001a }
            if (r9 == 0) goto L_0x02fd
            r1.invert = r2     // Catch:{ all -> 0x001a }
            goto L_0x02fd
        L_0x010d:
            r14 = 226(0xe2, float:3.17E-43)
            r3 = 14
            if (r9 != r14) goto L_0x0158
            int r9 = getShort(r4)     // Catch:{ all -> 0x001a }
            int r9 = r9 - r13
            byte[] r10 = new byte[r9]     // Catch:{ all -> 0x001a }
            r11 = r8
        L_0x011b:
            if (r11 >= r9) goto L_0x0127
            int r12 = r4.read()     // Catch:{ all -> 0x001a }
            byte r12 = (byte) r12     // Catch:{ all -> 0x001a }
            r10[r11] = r12     // Catch:{ all -> 0x001a }
            int r11 = r11 + 1
            goto L_0x011b
        L_0x0127:
            int r9 = r10.length     // Catch:{ all -> 0x001a }
            if (r9 < r3) goto L_0x02fd
            java.lang.String r3 = new java.lang.String     // Catch:{ all -> 0x001a }
            r9 = 11
            java.lang.String r11 = "ISO-8859-1"
            r3.<init>(r10, r8, r9, r11)     // Catch:{ all -> 0x001a }
            java.lang.String r9 = "ICC_PROFILE"
            boolean r3 = r3.equals(r9)     // Catch:{ all -> 0x001a }
            if (r3 == 0) goto L_0x02fd
            byte r3 = r10[r15]     // Catch:{ all -> 0x001a }
            r3 = r3 & r7
            r9 = 13
            byte r9 = r10[r9]     // Catch:{ all -> 0x001a }
            r9 = r9 & r7
            if (r3 >= r2) goto L_0x0146
            r3 = r2
        L_0x0146:
            if (r9 >= r2) goto L_0x0149
            r9 = r2
        L_0x0149:
            byte[][] r11 = r1.icc     // Catch:{ all -> 0x001a }
            if (r11 != 0) goto L_0x0151
            byte[][] r9 = new byte[r9][]     // Catch:{ all -> 0x001a }
            r1.icc = r9     // Catch:{ all -> 0x001a }
        L_0x0151:
            byte[][] r9 = r1.icc     // Catch:{ all -> 0x001a }
            int r3 = r3 - r2
            r9[r3] = r10     // Catch:{ all -> 0x001a }
            goto L_0x02fd
        L_0x0158:
            r14 = 237(0xed, float:3.32E-43)
            r15 = 8
            if (r9 != r14) goto L_0x0234
            int r3 = getShort(r4)     // Catch:{ all -> 0x001a }
            int r3 = r3 - r13
            byte[] r9 = new byte[r3]     // Catch:{ all -> 0x001a }
            r14 = r8
        L_0x0166:
            if (r14 >= r3) goto L_0x0173
            int r8 = r4.read()     // Catch:{ all -> 0x001a }
            byte r8 = (byte) r8     // Catch:{ all -> 0x001a }
            r9[r14] = r8     // Catch:{ all -> 0x001a }
            int r14 = r14 + 1
            r8 = 0
            goto L_0x0166
        L_0x0173:
            r8 = 0
        L_0x0174:
            byte[] r14 = PS_8BIM_RESO     // Catch:{ all -> 0x001a }
            int r14 = r14.length     // Catch:{ all -> 0x001a }
            int r14 = r3 - r14
            if (r8 >= r14) goto L_0x019f
            r14 = 0
        L_0x017c:
            byte[] r11 = PS_8BIM_RESO     // Catch:{ all -> 0x001a }
            int r11 = r11.length     // Catch:{ all -> 0x001a }
            if (r14 >= r11) goto L_0x0193
            int r11 = r8 + r14
            byte r11 = r9[r11]     // Catch:{ all -> 0x001a }
            byte[] r16 = PS_8BIM_RESO     // Catch:{ all -> 0x001a }
            byte r12 = r16[r14]     // Catch:{ all -> 0x001a }
            if (r11 == r12) goto L_0x018d
            r11 = 0
            goto L_0x0194
        L_0x018d:
            int r14 = r14 + 1
            r12 = 1076006748(0x40228f5c, float:2.54)
            goto L_0x017c
        L_0x0193:
            r11 = r2
        L_0x0194:
            if (r11 == 0) goto L_0x0197
            goto L_0x019f
        L_0x0197:
            int r8 = r8 + 1
            r11 = 1056964608(0x3f000000, float:0.5)
            r12 = 1076006748(0x40228f5c, float:2.54)
            goto L_0x0174
        L_0x019f:
            byte[] r11 = PS_8BIM_RESO     // Catch:{ all -> 0x001a }
            int r11 = r11.length     // Catch:{ all -> 0x001a }
            int r8 = r8 + r11
            byte[] r11 = PS_8BIM_RESO     // Catch:{ all -> 0x001a }
            int r11 = r11.length     // Catch:{ all -> 0x001a }
            int r3 = r3 - r11
            if (r8 >= r3) goto L_0x02fd
            byte r3 = r9[r8]     // Catch:{ all -> 0x001a }
            int r3 = r3 + r2
            byte r3 = (byte) r3     // Catch:{ all -> 0x001a }
            int r11 = r3 % 2
            if (r11 != r2) goto L_0x01b4
            int r3 = r3 + 1
            byte r3 = (byte) r3     // Catch:{ all -> 0x001a }
        L_0x01b4:
            int r8 = r8 + r3
            byte r3 = r9[r8]     // Catch:{ all -> 0x001a }
            int r3 = r3 << 24
            int r11 = r8 + 1
            byte r11 = r9[r11]     // Catch:{ all -> 0x001a }
            int r11 = r11 << r10
            int r3 = r3 + r11
            int r11 = r8 + 2
            byte r11 = r9[r11]     // Catch:{ all -> 0x001a }
            int r11 = r11 << r15
            int r3 = r3 + r11
            int r11 = r8 + 3
            byte r11 = r9[r11]     // Catch:{ all -> 0x001a }
            int r3 = r3 + r11
            if (r3 == r10) goto L_0x01ce
            goto L_0x02fd
        L_0x01ce:
            int r8 = r8 + 4
            byte r3 = r9[r8]     // Catch:{ all -> 0x001a }
            int r3 = r3 << r15
            int r10 = r8 + 1
            byte r10 = r9[r10]     // Catch:{ all -> 0x001a }
            r10 = r10 & r7
            int r3 = r3 + r10
            int r8 = r8 + 2
            int r8 = r8 + r13
            byte r10 = r9[r8]     // Catch:{ all -> 0x001a }
            int r10 = r10 << r15
            int r11 = r8 + 1
            byte r11 = r9[r11]     // Catch:{ all -> 0x001a }
            r11 = r11 & r7
            int r10 = r10 + r11
            int r8 = r8 + 2
            int r8 = r8 + r13
            byte r11 = r9[r8]     // Catch:{ all -> 0x001a }
            int r11 = r11 << r15
            int r12 = r8 + 1
            byte r12 = r9[r12]     // Catch:{ all -> 0x001a }
            r12 = r12 & r7
            int r11 = r11 + r12
            int r8 = r8 + 2
            int r8 = r8 + r13
            byte r12 = r9[r8]     // Catch:{ all -> 0x001a }
            int r12 = r12 << r15
            int r8 = r8 + 1
            byte r8 = r9[r8]     // Catch:{ all -> 0x001a }
            r8 = r8 & r7
            int r12 = r12 + r8
            if (r10 == r2) goto L_0x0201
            if (r10 != r13) goto L_0x0217
        L_0x0201:
            if (r10 != r13) goto L_0x020c
            float r3 = (float) r3     // Catch:{ all -> 0x001a }
            r8 = 1076006748(0x40228f5c, float:2.54)
            float r3 = r3 * r8
            r8 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 + r8
            int r3 = (int) r3     // Catch:{ all -> 0x001a }
        L_0x020c:
            int r8 = r1.dpiX     // Catch:{ all -> 0x001a }
            if (r8 == 0) goto L_0x0215
            int r8 = r1.dpiX     // Catch:{ all -> 0x001a }
            if (r8 == r3) goto L_0x0215
            goto L_0x0217
        L_0x0215:
            r1.dpiX = r3     // Catch:{ all -> 0x001a }
        L_0x0217:
            if (r12 == r2) goto L_0x021b
            if (r12 != r13) goto L_0x02fd
        L_0x021b:
            if (r12 != r13) goto L_0x0226
            float r3 = (float) r11     // Catch:{ all -> 0x001a }
            r8 = 1076006748(0x40228f5c, float:2.54)
            float r3 = r3 * r8
            r8 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 + r8
            int r11 = (int) r3     // Catch:{ all -> 0x001a }
        L_0x0226:
            int r3 = r1.dpiY     // Catch:{ all -> 0x001a }
            if (r3 == 0) goto L_0x0230
            int r3 = r1.dpiY     // Catch:{ all -> 0x001a }
            if (r3 == r11) goto L_0x0230
            goto L_0x02fd
        L_0x0230:
            r1.dpiY = r11     // Catch:{ all -> 0x001a }
            goto L_0x02fd
        L_0x0234:
            int r6 = marker(r9)     // Catch:{ all -> 0x001a }
            if (r6 != 0) goto L_0x02d6
            com.itextpdf.text.Utilities.skip(r4, r13)     // Catch:{ all -> 0x001a }
            int r6 = r4.read()     // Catch:{ all -> 0x001a }
            if (r6 == r15) goto L_0x0254
            com.itextpdf.text.BadElementException r3 = new com.itextpdf.text.BadElementException     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "1.must.have.8.bits.per.component"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x001a }
            r7 = 0
            r2[r7] = r5     // Catch:{ all -> 0x001a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r6, (java.lang.Object[]) r2)     // Catch:{ all -> 0x001a }
            r3.<init>((java.lang.String) r2)     // Catch:{ all -> 0x001a }
            throw r3     // Catch:{ all -> 0x001a }
        L_0x0254:
            int r2 = getShort(r4)     // Catch:{ all -> 0x001a }
            float r2 = (float) r2     // Catch:{ all -> 0x001a }
            r1.scaledHeight = r2     // Catch:{ all -> 0x001a }
            float r2 = r1.scaledHeight     // Catch:{ all -> 0x001a }
            r1.setTop(r2)     // Catch:{ all -> 0x001a }
            int r2 = getShort(r4)     // Catch:{ all -> 0x001a }
            float r2 = (float) r2     // Catch:{ all -> 0x001a }
            r1.scaledWidth = r2     // Catch:{ all -> 0x001a }
            float r2 = r1.scaledWidth     // Catch:{ all -> 0x001a }
            r1.setRight(r2)     // Catch:{ all -> 0x001a }
            int r2 = r4.read()     // Catch:{ all -> 0x001a }
            r1.colorspace = r2     // Catch:{ all -> 0x001a }
            r1.bpc = r15     // Catch:{ all -> 0x001a }
            if (r4 == 0) goto L_0x0279
            r4.close()
        L_0x0279:
            float r2 = r18.getWidth()
            r1.plainWidth = r2
            float r2 = r18.getHeight()
            r1.plainHeight = r2
            byte[][] r2 = r1.icc
            if (r2 == 0) goto L_0x02d5
            r2 = 0
            r4 = 0
        L_0x028b:
            byte[][] r5 = r1.icc
            int r5 = r5.length
            if (r2 >= r5) goto L_0x02a7
            byte[][] r5 = r1.icc
            r5 = r5[r2]
            if (r5 != 0) goto L_0x029d
            r5 = 0
            r3 = r5
            byte[][] r3 = (byte[][]) r3
            r1.icc = r3
            return
        L_0x029d:
            byte[][] r5 = r1.icc
            r5 = r5[r2]
            int r5 = r5.length
            int r5 = r5 - r3
            int r4 = r4 + r5
            int r2 = r2 + 1
            goto L_0x028b
        L_0x02a7:
            byte[] r2 = new byte[r4]
            r4 = 0
            r5 = 0
        L_0x02ab:
            byte[][] r6 = r1.icc
            int r6 = r6.length
            if (r4 >= r6) goto L_0x02c7
            byte[][] r6 = r1.icc
            r6 = r6[r4]
            byte[][] r7 = r1.icc
            r7 = r7[r4]
            int r7 = r7.length
            int r7 = r7 - r3
            java.lang.System.arraycopy(r6, r3, r2, r5, r7)
            byte[][] r6 = r1.icc
            r6 = r6[r4]
            int r6 = r6.length
            int r6 = r6 - r3
            int r5 = r5 + r6
            int r4 = r4 + 1
            goto L_0x02ab
        L_0x02c7:
            int r3 = r1.colorspace     // Catch:{ IllegalArgumentException -> 0x02d0 }
            com.itextpdf.text.pdf.ICC_Profile r2 = com.itextpdf.text.pdf.ICC_Profile.getInstance(r2, r3)     // Catch:{ IllegalArgumentException -> 0x02d0 }
            r1.tagICC(r2)     // Catch:{ IllegalArgumentException -> 0x02d0 }
        L_0x02d0:
            r3 = 0
            byte[][] r3 = (byte[][]) r3
            r1.icc = r3
        L_0x02d5:
            return
        L_0x02d6:
            r3 = 0
            if (r6 != r2) goto L_0x02f0
            com.itextpdf.text.BadElementException r3 = new com.itextpdf.text.BadElementException     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "1.unsupported.jpeg.marker.2"
            java.lang.Object[] r7 = new java.lang.Object[r13]     // Catch:{ all -> 0x001a }
            r8 = 0
            r7[r8] = r5     // Catch:{ all -> 0x001a }
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x001a }
            r7[r2] = r5     // Catch:{ all -> 0x001a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r6, (java.lang.Object[]) r7)     // Catch:{ all -> 0x001a }
            r3.<init>((java.lang.String) r2)     // Catch:{ all -> 0x001a }
            throw r3     // Catch:{ all -> 0x001a }
        L_0x02f0:
            if (r6 == r13) goto L_0x02fa
            int r6 = getShort(r4)     // Catch:{ all -> 0x001a }
            int r6 = r6 - r13
            com.itextpdf.text.Utilities.skip(r4, r6)     // Catch:{ all -> 0x001a }
        L_0x02fa:
            r6 = 0
            goto L_0x02fd
        L_0x02fc:
            r3 = 0
        L_0x02fd:
            r8 = 0
            goto L_0x0040
        L_0x0300:
            com.itextpdf.text.BadElementException r3 = new com.itextpdf.text.BadElementException     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "1.is.not.a.valid.jpeg.file"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x001a }
            r7 = 0
            r2[r7] = r5     // Catch:{ all -> 0x001a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r6, (java.lang.Object[]) r2)     // Catch:{ all -> 0x001a }
            r3.<init>((java.lang.String) r2)     // Catch:{ all -> 0x001a }
            throw r3     // Catch:{ all -> 0x001a }
        L_0x0311:
            r0 = move-exception
            r3 = 0
            r2 = r0
            r4 = r3
        L_0x0315:
            if (r4 == 0) goto L_0x031a
            r4.close()
        L_0x031a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.Jpeg.processParameters():void");
    }
}
