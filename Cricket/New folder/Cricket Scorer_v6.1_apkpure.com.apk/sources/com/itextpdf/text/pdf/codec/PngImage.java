package com.itextpdf.text.pdf.codec;

import apps.shehryar.com.cricketscroingappPro.BuildConfig;
import com.itextpdf.text.Image;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.ByteBuffer;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfLiteral;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfString;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class PngImage {
    public static final String IDAT = "IDAT";
    public static final String IEND = "IEND";
    public static final String IHDR = "IHDR";
    public static final String PLTE = "PLTE";
    public static final int[] PNGID = {BuildConfig.VERSION_CODE, 80, 78, 71, 13, 10, 26, 10};
    private static final int PNG_FILTER_AVERAGE = 3;
    private static final int PNG_FILTER_NONE = 0;
    private static final int PNG_FILTER_PAETH = 4;
    private static final int PNG_FILTER_SUB = 1;
    private static final int PNG_FILTER_UP = 2;
    private static final int TRANSFERSIZE = 4096;
    public static final String cHRM = "cHRM";
    public static final String gAMA = "gAMA";
    public static final String iCCP = "iCCP";
    private static final PdfName[] intents = {PdfName.PERCEPTUAL, PdfName.RELATIVECOLORIMETRIC, PdfName.SATURATION, PdfName.ABSOLUTECOLORIMETRIC};
    public static final String pHYs = "pHYs";
    public static final String sRGB = "sRGB";
    public static final String tRNS = "tRNS";
    float XYRatio;
    PdfDictionary additional = new PdfDictionary();
    int bitDepth;
    int bytesPerPixel;
    byte[] colorTable;
    int colorType;
    int compressionMethod;
    DataInputStream dataStream;
    int dpiX;
    int dpiY;
    int filterMethod;
    float gamma = 1.0f;
    boolean genBWMask;
    boolean hasCHRM = false;
    int height;
    ICC_Profile icc_profile;
    NewByteArrayOutputStream idat = new NewByteArrayOutputStream();
    byte[] image;
    int inputBands;
    PdfName intent;
    int interlaceMethod;
    InputStream is;
    boolean palShades;
    byte[] smask;
    byte[] trans;
    int transBlue = -1;
    int transGreen = -1;
    int transRedGray = -1;
    int width;
    float xB;
    float xG;
    float xR;
    float xW;
    float yB;
    float yG;
    float yR;
    float yW;

    PngImage(InputStream inputStream) {
        this.is = inputStream;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.itextpdf.text.Image getImage(java.net.URL r2) throws java.io.IOException {
        /*
            java.io.InputStream r0 = r2.openStream()     // Catch:{ all -> 0x0013 }
            com.itextpdf.text.Image r1 = getImage((java.io.InputStream) r0)     // Catch:{ all -> 0x0011 }
            r1.setUrl(r2)     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x0010
            r0.close()
        L_0x0010:
            return r1
        L_0x0011:
            r2 = move-exception
            goto L_0x0015
        L_0x0013:
            r2 = move-exception
            r0 = 0
        L_0x0015:
            if (r0 == 0) goto L_0x001a
            r0.close()
        L_0x001a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.PngImage.getImage(java.net.URL):com.itextpdf.text.Image");
    }

    public static Image getImage(InputStream inputStream) throws IOException {
        return new PngImage(inputStream).getImage();
    }

    public static Image getImage(String str) throws IOException {
        return getImage(Utilities.toURL(str));
    }

    public static Image getImage(byte[] bArr) throws IOException {
        Image image2 = getImage((InputStream) new ByteArrayInputStream(bArr));
        image2.setOriginalData(bArr);
        return image2;
    }

    /* access modifiers changed from: package-private */
    public boolean checkMarker(String str) {
        if (str.length() != 4) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            char charAt = str.charAt(i);
            if ((charAt < 'a' || charAt > 'z') && (charAt < 'A' || charAt > 'Z')) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void readPng() throws IOException {
        for (int i : PNGID) {
            if (i != this.is.read()) {
                throw new IOException(MessageLocalization.getComposedMessage("file.is.not.a.valid.png", new Object[0]));
            }
        }
        byte[] bArr = new byte[4096];
        while (true) {
            int i2 = getInt(this.is);
            String string = getString(this.is);
            if (i2 >= 0 && checkMarker(string)) {
                if (IDAT.equals(string)) {
                    while (i2 != 0) {
                        int read = this.is.read(bArr, 0, Math.min(i2, 4096));
                        if (read >= 0) {
                            this.idat.write(bArr, 0, read);
                            i2 -= read;
                        } else {
                            return;
                        }
                    }
                    continue;
                } else if (tRNS.equals(string)) {
                    int i3 = this.colorType;
                    if (i3 != 0) {
                        switch (i3) {
                            case 2:
                                if (i2 >= 6) {
                                    i2 -= 6;
                                    int word = getWord(this.is);
                                    int word2 = getWord(this.is);
                                    int word3 = getWord(this.is);
                                    if (this.bitDepth != 16) {
                                        this.additional.put(PdfName.MASK, new PdfLiteral("[" + word + " " + word + " " + word2 + " " + word2 + " " + word3 + " " + word3 + "]"));
                                        break;
                                    } else {
                                        this.transRedGray = word;
                                        this.transGreen = word2;
                                        this.transBlue = word3;
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                if (i2 > 0) {
                                    this.trans = new byte[i2];
                                    for (int i4 = 0; i4 < i2; i4++) {
                                        this.trans[i4] = (byte) this.is.read();
                                    }
                                    i2 = 0;
                                    break;
                                }
                                break;
                        }
                    } else if (i2 >= 2) {
                        i2 -= 2;
                        int word4 = getWord(this.is);
                        if (this.bitDepth == 16) {
                            this.transRedGray = word4;
                        } else {
                            this.additional.put(PdfName.MASK, new PdfLiteral("[" + word4 + " " + word4 + "]"));
                        }
                    }
                    Utilities.skip(this.is, i2);
                } else if (IHDR.equals(string)) {
                    this.width = getInt(this.is);
                    this.height = getInt(this.is);
                    this.bitDepth = this.is.read();
                    this.colorType = this.is.read();
                    this.compressionMethod = this.is.read();
                    this.filterMethod = this.is.read();
                    this.interlaceMethod = this.is.read();
                } else {
                    boolean z = true;
                    if (PLTE.equals(string)) {
                        if (this.colorType == 3) {
                            PdfArray pdfArray = new PdfArray();
                            pdfArray.add((PdfObject) PdfName.INDEXED);
                            pdfArray.add(getColorspace());
                            pdfArray.add((PdfObject) new PdfNumber((i2 / 3) - 1));
                            ByteBuffer byteBuffer = new ByteBuffer();
                            while (true) {
                                int i5 = i2 - 1;
                                if (i2 > 0) {
                                    byteBuffer.append_i(this.is.read());
                                    i2 = i5;
                                } else {
                                    byte[] byteArray = byteBuffer.toByteArray();
                                    this.colorTable = byteArray;
                                    pdfArray.add((PdfObject) new PdfString(byteArray));
                                    this.additional.put(PdfName.COLORSPACE, pdfArray);
                                }
                            }
                        } else {
                            Utilities.skip(this.is, i2);
                        }
                    } else if (pHYs.equals(string)) {
                        int i6 = getInt(this.is);
                        int i7 = getInt(this.is);
                        if (this.is.read() == 1) {
                            this.dpiX = (int) ((((float) i6) * 0.0254f) + 0.5f);
                            this.dpiY = (int) ((((float) i7) * 0.0254f) + 0.5f);
                        } else if (i7 != 0) {
                            this.XYRatio = ((float) i6) / ((float) i7);
                        }
                    } else if (cHRM.equals(string)) {
                        this.xW = ((float) getInt(this.is)) / 100000.0f;
                        this.yW = ((float) getInt(this.is)) / 100000.0f;
                        this.xR = ((float) getInt(this.is)) / 100000.0f;
                        this.yR = ((float) getInt(this.is)) / 100000.0f;
                        this.xG = ((float) getInt(this.is)) / 100000.0f;
                        this.yG = ((float) getInt(this.is)) / 100000.0f;
                        this.xB = ((float) getInt(this.is)) / 100000.0f;
                        this.yB = ((float) getInt(this.is)) / 100000.0f;
                        if (Math.abs(this.xW) < 1.0E-4f || Math.abs(this.yW) < 1.0E-4f || Math.abs(this.xR) < 1.0E-4f || Math.abs(this.yR) < 1.0E-4f || Math.abs(this.xG) < 1.0E-4f || Math.abs(this.yG) < 1.0E-4f || Math.abs(this.xB) < 1.0E-4f || Math.abs(this.yB) < 1.0E-4f) {
                            z = false;
                        }
                        this.hasCHRM = z;
                    } else if (sRGB.equals(string)) {
                        this.intent = intents[this.is.read()];
                        this.gamma = 2.2f;
                        this.xW = 0.3127f;
                        this.yW = 0.329f;
                        this.xR = 0.64f;
                        this.yR = 0.33f;
                        this.xG = 0.3f;
                        this.yG = 0.6f;
                        this.xB = 0.15f;
                        this.yB = 0.06f;
                        this.hasCHRM = true;
                    } else if (gAMA.equals(string)) {
                        int i8 = getInt(this.is);
                        if (i8 != 0) {
                            this.gamma = 100000.0f / ((float) i8);
                            if (!this.hasCHRM) {
                                this.xW = 0.3127f;
                                this.yW = 0.329f;
                                this.xR = 0.64f;
                                this.yR = 0.33f;
                                this.xG = 0.3f;
                                this.yG = 0.6f;
                                this.xB = 0.15f;
                                this.yB = 0.06f;
                                this.hasCHRM = true;
                            }
                        }
                    } else if (iCCP.equals(string)) {
                        do {
                            i2--;
                        } while (this.is.read() != 0);
                        this.is.read();
                        int i9 = i2 - 1;
                        byte[] bArr2 = new byte[i9];
                        int i10 = 0;
                        while (i9 > 0) {
                            int read2 = this.is.read(bArr2, i10, i9);
                            if (read2 < 0) {
                                throw new IOException(MessageLocalization.getComposedMessage("premature.end.of.file", new Object[0]));
                            }
                            i10 += read2;
                            i9 -= read2;
                        }
                        try {
                            this.icc_profile = ICC_Profile.getInstance(PdfReader.FlateDecode(bArr2, true));
                        } catch (RuntimeException unused) {
                            this.icc_profile = null;
                        }
                    } else if (!IEND.equals(string)) {
                        Utilities.skip(this.is, i2);
                    } else {
                        return;
                    }
                }
                Utilities.skip(this.is, 4);
            }
        }
        throw new IOException(MessageLocalization.getComposedMessage("corrupted.png.file", new Object[0]));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v0, resolved type: com.itextpdf.text.pdf.PdfLiteral} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: com.itextpdf.text.pdf.PdfLiteral} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: com.itextpdf.text.pdf.PdfArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: com.itextpdf.text.pdf.PdfLiteral} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.pdf.PdfObject getColorspace() {
        /*
            r17 = this;
            r0 = r17
            com.itextpdf.text.pdf.ICC_Profile r1 = r0.icc_profile
            if (r1 == 0) goto L_0x0012
            int r1 = r0.colorType
            r1 = r1 & 2
            if (r1 != 0) goto L_0x000f
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.DEVICEGRAY
            return r1
        L_0x000f:
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.DEVICERGB
            return r1
        L_0x0012:
            float r1 = r0.gamma
            r2 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 != 0) goto L_0x002a
            boolean r1 = r0.hasCHRM
            if (r1 != 0) goto L_0x002a
            int r1 = r0.colorType
            r1 = r1 & 2
            if (r1 != 0) goto L_0x0027
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.DEVICEGRAY
            return r1
        L_0x0027:
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.DEVICERGB
            return r1
        L_0x002a:
            com.itextpdf.text.pdf.PdfArray r1 = new com.itextpdf.text.pdf.PdfArray
            r1.<init>()
            com.itextpdf.text.pdf.PdfDictionary r3 = new com.itextpdf.text.pdf.PdfDictionary
            r3.<init>()
            int r4 = r0.colorType
            r4 = r4 & 2
            if (r4 != 0) goto L_0x0065
            float r4 = r0.gamma
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x0043
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.DEVICEGRAY
            return r1
        L_0x0043:
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.CALGRAY
            r1.add((com.itextpdf.text.pdf.PdfObject) r2)
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.GAMMA
            com.itextpdf.text.pdf.PdfNumber r4 = new com.itextpdf.text.pdf.PdfNumber
            float r5 = r0.gamma
            r4.<init>((float) r5)
            r3.put(r2, r4)
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.WHITEPOINT
            com.itextpdf.text.pdf.PdfLiteral r4 = new com.itextpdf.text.pdf.PdfLiteral
            java.lang.String r5 = "[1 1 1]"
            r4.<init>((java.lang.String) r5)
            r3.put(r2, r4)
            r1.add((com.itextpdf.text.pdf.PdfObject) r3)
            goto L_0x01bb
        L_0x0065:
            com.itextpdf.text.pdf.PdfLiteral r4 = new com.itextpdf.text.pdf.PdfLiteral
            java.lang.String r5 = "[1 1 1]"
            r4.<init>((java.lang.String) r5)
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.CALRGB
            r1.add((com.itextpdf.text.pdf.PdfObject) r5)
            float r5 = r0.gamma
            int r5 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x0091
            com.itextpdf.text.pdf.PdfArray r5 = new com.itextpdf.text.pdf.PdfArray
            r5.<init>()
            com.itextpdf.text.pdf.PdfNumber r6 = new com.itextpdf.text.pdf.PdfNumber
            float r7 = r0.gamma
            r6.<init>((float) r7)
            r5.add((com.itextpdf.text.pdf.PdfObject) r6)
            r5.add((com.itextpdf.text.pdf.PdfObject) r6)
            r5.add((com.itextpdf.text.pdf.PdfObject) r6)
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.GAMMA
            r3.put(r6, r5)
        L_0x0091:
            boolean r5 = r0.hasCHRM
            if (r5 == 0) goto L_0x01b2
            float r4 = r0.yW
            float r5 = r0.xG
            float r6 = r0.xB
            float r5 = r5 - r6
            float r6 = r0.yR
            float r5 = r5 * r6
            float r6 = r0.xR
            float r7 = r0.xB
            float r6 = r6 - r7
            float r7 = r0.yG
            float r6 = r6 * r7
            float r5 = r5 - r6
            float r6 = r0.xR
            float r7 = r0.xG
            float r6 = r6 - r7
            float r7 = r0.yB
            float r6 = r6 * r7
            float r5 = r5 + r6
            float r4 = r4 * r5
            float r5 = r0.yR
            float r6 = r0.xG
            float r7 = r0.xB
            float r6 = r6 - r7
            float r7 = r0.yW
            float r6 = r6 * r7
            float r7 = r0.xW
            float r8 = r0.xB
            float r7 = r7 - r8
            float r8 = r0.yG
            float r7 = r7 * r8
            float r6 = r6 - r7
            float r7 = r0.xW
            float r8 = r0.xG
            float r7 = r7 - r8
            float r8 = r0.yB
            float r7 = r7 * r8
            float r6 = r6 + r7
            float r5 = r5 * r6
            float r5 = r5 / r4
            float r6 = r0.xR
            float r6 = r6 * r5
            float r7 = r0.yR
            float r6 = r6 / r7
            float r7 = r0.xR
            float r7 = r2 - r7
            float r8 = r0.yR
            float r7 = r7 / r8
            float r7 = r7 - r2
            float r7 = r7 * r5
            float r8 = r0.yG
            float r8 = -r8
            float r9 = r0.xR
            float r10 = r0.xB
            float r9 = r9 - r10
            float r10 = r0.yW
            float r9 = r9 * r10
            float r10 = r0.xW
            float r11 = r0.xB
            float r10 = r10 - r11
            float r11 = r0.yR
            float r10 = r10 * r11
            float r9 = r9 - r10
            float r10 = r0.xW
            float r11 = r0.xR
            float r10 = r10 - r11
            float r11 = r0.yB
            float r10 = r10 * r11
            float r9 = r9 + r10
            float r8 = r8 * r9
            float r8 = r8 / r4
            float r9 = r0.xG
            float r9 = r9 * r8
            float r10 = r0.yG
            float r9 = r9 / r10
            float r10 = r0.xG
            float r10 = r2 - r10
            float r11 = r0.yG
            float r10 = r10 / r11
            float r10 = r10 - r2
            float r10 = r10 * r8
            float r11 = r0.yB
            float r12 = r0.xR
            float r13 = r0.xG
            float r12 = r12 - r13
            float r13 = r0.yW
            float r12 = r12 * r13
            float r13 = r0.xW
            float r14 = r0.xG
            float r13 = r13 - r14
            float r14 = r0.yW
            float r13 = r13 * r14
            float r12 = r12 - r13
            float r13 = r0.xW
            float r14 = r0.xR
            float r13 = r13 - r14
            float r14 = r0.yG
            float r13 = r13 * r14
            float r12 = r12 + r13
            float r11 = r11 * r12
            float r11 = r11 / r4
            float r4 = r0.xB
            float r4 = r4 * r11
            float r12 = r0.yB
            float r4 = r4 / r12
            float r12 = r0.xB
            float r12 = r2 - r12
            float r13 = r0.yB
            float r12 = r12 / r13
            float r12 = r12 - r2
            float r12 = r12 * r11
            float r13 = r6 + r9
            float r13 = r13 + r4
            float r14 = r7 + r10
            float r14 = r14 + r12
            com.itextpdf.text.pdf.PdfArray r15 = new com.itextpdf.text.pdf.PdfArray
            r15.<init>()
            com.itextpdf.text.pdf.PdfNumber r2 = new com.itextpdf.text.pdf.PdfNumber
            r2.<init>((float) r13)
            r15.add((com.itextpdf.text.pdf.PdfObject) r2)
            com.itextpdf.text.pdf.PdfNumber r2 = new com.itextpdf.text.pdf.PdfNumber
            r13 = 1065353216(0x3f800000, float:1.0)
            r2.<init>((float) r13)
            r15.add((com.itextpdf.text.pdf.PdfObject) r2)
            com.itextpdf.text.pdf.PdfNumber r2 = new com.itextpdf.text.pdf.PdfNumber
            r2.<init>((float) r14)
            r15.add((com.itextpdf.text.pdf.PdfObject) r2)
            com.itextpdf.text.pdf.PdfArray r2 = new com.itextpdf.text.pdf.PdfArray
            r2.<init>()
            com.itextpdf.text.pdf.PdfNumber r13 = new com.itextpdf.text.pdf.PdfNumber
            r13.<init>((float) r6)
            r2.add((com.itextpdf.text.pdf.PdfObject) r13)
            com.itextpdf.text.pdf.PdfNumber r6 = new com.itextpdf.text.pdf.PdfNumber
            r6.<init>((float) r5)
            r2.add((com.itextpdf.text.pdf.PdfObject) r6)
            com.itextpdf.text.pdf.PdfNumber r5 = new com.itextpdf.text.pdf.PdfNumber
            r5.<init>((float) r7)
            r2.add((com.itextpdf.text.pdf.PdfObject) r5)
            com.itextpdf.text.pdf.PdfNumber r5 = new com.itextpdf.text.pdf.PdfNumber
            r5.<init>((float) r9)
            r2.add((com.itextpdf.text.pdf.PdfObject) r5)
            com.itextpdf.text.pdf.PdfNumber r5 = new com.itextpdf.text.pdf.PdfNumber
            r5.<init>((float) r8)
            r2.add((com.itextpdf.text.pdf.PdfObject) r5)
            com.itextpdf.text.pdf.PdfNumber r5 = new com.itextpdf.text.pdf.PdfNumber
            r5.<init>((float) r10)
            r2.add((com.itextpdf.text.pdf.PdfObject) r5)
            com.itextpdf.text.pdf.PdfNumber r5 = new com.itextpdf.text.pdf.PdfNumber
            r5.<init>((float) r4)
            r2.add((com.itextpdf.text.pdf.PdfObject) r5)
            com.itextpdf.text.pdf.PdfNumber r4 = new com.itextpdf.text.pdf.PdfNumber
            r4.<init>((float) r11)
            r2.add((com.itextpdf.text.pdf.PdfObject) r4)
            com.itextpdf.text.pdf.PdfNumber r4 = new com.itextpdf.text.pdf.PdfNumber
            r4.<init>((float) r12)
            r2.add((com.itextpdf.text.pdf.PdfObject) r4)
            com.itextpdf.text.pdf.PdfName r4 = com.itextpdf.text.pdf.PdfName.MATRIX
            r3.put(r4, r2)
            goto L_0x01b3
        L_0x01b2:
            r15 = r4
        L_0x01b3:
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.WHITEPOINT
            r3.put(r2, r15)
            r1.add((com.itextpdf.text.pdf.PdfObject) r3)
        L_0x01bb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.PngImage.getColorspace():com.itextpdf.text.pdf.PdfObject");
    }

    /* JADX WARNING: type inference failed for: r0v11, types: [com.itextpdf.text.Image] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.Image getImage() throws java.io.IOException {
        /*
            r13 = this;
            r13.readPng()
            r0 = 0
            r13.palShades = r0     // Catch:{ Exception -> 0x01a8 }
            byte[] r1 = r13.trans     // Catch:{ Exception -> 0x01a8 }
            r2 = 1
            if (r1 == 0) goto L_0x0029
            r1 = r0
            r3 = r1
            r4 = r3
        L_0x000e:
            byte[] r5 = r13.trans     // Catch:{ Exception -> 0x01a8 }
            int r5 = r5.length     // Catch:{ Exception -> 0x01a8 }
            if (r1 >= r5) goto L_0x002b
            byte[] r5 = r13.trans     // Catch:{ Exception -> 0x01a8 }
            byte r5 = r5[r1]     // Catch:{ Exception -> 0x01a8 }
            r6 = 255(0xff, float:3.57E-43)
            r5 = r5 & r6
            if (r5 != 0) goto L_0x001f
            int r3 = r3 + 1
            r4 = r1
        L_0x001f:
            if (r5 == 0) goto L_0x0026
            if (r5 == r6) goto L_0x0026
            r13.palShades = r2     // Catch:{ Exception -> 0x01a8 }
            goto L_0x002b
        L_0x0026:
            int r1 = r1 + 1
            goto L_0x000e
        L_0x0029:
            r3 = r0
            r4 = r3
        L_0x002b:
            int r1 = r13.colorType     // Catch:{ Exception -> 0x01a8 }
            r5 = 4
            r1 = r1 & r5
            if (r1 == 0) goto L_0x0033
            r13.palShades = r2     // Catch:{ Exception -> 0x01a8 }
        L_0x0033:
            boolean r1 = r13.palShades     // Catch:{ Exception -> 0x01a8 }
            if (r1 != 0) goto L_0x003f
            if (r3 > r2) goto L_0x003d
            int r1 = r13.transRedGray     // Catch:{ Exception -> 0x01a8 }
            if (r1 < 0) goto L_0x003f
        L_0x003d:
            r1 = r2
            goto L_0x0040
        L_0x003f:
            r1 = r0
        L_0x0040:
            r13.genBWMask = r1     // Catch:{ Exception -> 0x01a8 }
            boolean r1 = r13.palShades     // Catch:{ Exception -> 0x01a8 }
            if (r1 != 0) goto L_0x0076
            boolean r1 = r13.genBWMask     // Catch:{ Exception -> 0x01a8 }
            if (r1 != 0) goto L_0x0076
            if (r3 != r2) goto L_0x0076
            com.itextpdf.text.pdf.PdfDictionary r1 = r13.additional     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.MASK     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfLiteral r6 = new com.itextpdf.text.pdf.PdfLiteral     // Catch:{ Exception -> 0x01a8 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01a8 }
            r7.<init>()     // Catch:{ Exception -> 0x01a8 }
            java.lang.String r8 = "["
            r7.append(r8)     // Catch:{ Exception -> 0x01a8 }
            r7.append(r4)     // Catch:{ Exception -> 0x01a8 }
            java.lang.String r8 = " "
            r7.append(r8)     // Catch:{ Exception -> 0x01a8 }
            r7.append(r4)     // Catch:{ Exception -> 0x01a8 }
            java.lang.String r4 = "]"
            r7.append(r4)     // Catch:{ Exception -> 0x01a8 }
            java.lang.String r4 = r7.toString()     // Catch:{ Exception -> 0x01a8 }
            r6.<init>((java.lang.String) r4)     // Catch:{ Exception -> 0x01a8 }
            r1.put(r3, r6)     // Catch:{ Exception -> 0x01a8 }
        L_0x0076:
            int r1 = r13.interlaceMethod     // Catch:{ Exception -> 0x01a8 }
            r3 = 16
            if (r1 == r2) goto L_0x008d
            int r1 = r13.bitDepth     // Catch:{ Exception -> 0x01a8 }
            if (r1 == r3) goto L_0x008d
            int r1 = r13.colorType     // Catch:{ Exception -> 0x01a8 }
            r1 = r1 & r5
            if (r1 != 0) goto L_0x008d
            boolean r1 = r13.palShades     // Catch:{ Exception -> 0x01a8 }
            if (r1 != 0) goto L_0x008d
            boolean r1 = r13.genBWMask     // Catch:{ Exception -> 0x01a8 }
            if (r1 == 0) goto L_0x008e
        L_0x008d:
            r0 = r2
        L_0x008e:
            int r1 = r13.colorType     // Catch:{ Exception -> 0x01a8 }
            r4 = 2
            r6 = 3
            if (r1 == 0) goto L_0x00a7
            r7 = 6
            if (r1 == r7) goto L_0x00a4
            switch(r1) {
                case 2: goto L_0x00a1;
                case 3: goto L_0x009e;
                case 4: goto L_0x009b;
                default: goto L_0x009a;
            }     // Catch:{ Exception -> 0x01a8 }
        L_0x009a:
            goto L_0x00a9
        L_0x009b:
            r13.inputBands = r4     // Catch:{ Exception -> 0x01a8 }
            goto L_0x00a9
        L_0x009e:
            r13.inputBands = r2     // Catch:{ Exception -> 0x01a8 }
            goto L_0x00a9
        L_0x00a1:
            r13.inputBands = r6     // Catch:{ Exception -> 0x01a8 }
            goto L_0x00a9
        L_0x00a4:
            r13.inputBands = r5     // Catch:{ Exception -> 0x01a8 }
            goto L_0x00a9
        L_0x00a7:
            r13.inputBands = r2     // Catch:{ Exception -> 0x01a8 }
        L_0x00a9:
            if (r0 == 0) goto L_0x00ae
            r13.decodeIdat()     // Catch:{ Exception -> 0x01a8 }
        L_0x00ae:
            int r0 = r13.inputBands     // Catch:{ Exception -> 0x01a8 }
            int r1 = r13.colorType     // Catch:{ Exception -> 0x01a8 }
            r1 = r1 & r5
            if (r1 == 0) goto L_0x00b7
            int r0 = r0 + -1
        L_0x00b7:
            r10 = r0
            int r0 = r13.bitDepth     // Catch:{ Exception -> 0x01a8 }
            r1 = 8
            if (r0 != r3) goto L_0x00c0
            r11 = r1
            goto L_0x00c1
        L_0x00c0:
            r11 = r0
        L_0x00c1:
            byte[] r0 = r13.image     // Catch:{ Exception -> 0x01a8 }
            if (r0 == 0) goto L_0x00e1
            int r0 = r13.colorType     // Catch:{ Exception -> 0x01a8 }
            if (r0 != r6) goto L_0x00d6
            com.itextpdf.text.ImgRaw r0 = new com.itextpdf.text.ImgRaw     // Catch:{ Exception -> 0x01a8 }
            int r8 = r13.width     // Catch:{ Exception -> 0x01a8 }
            int r9 = r13.height     // Catch:{ Exception -> 0x01a8 }
            byte[] r12 = r13.image     // Catch:{ Exception -> 0x01a8 }
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x01a8 }
            goto L_0x0138
        L_0x00d6:
            int r0 = r13.width     // Catch:{ Exception -> 0x01a8 }
            int r3 = r13.height     // Catch:{ Exception -> 0x01a8 }
            byte[] r5 = r13.image     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.Image r0 = com.itextpdf.text.Image.getInstance(r0, r3, r10, r11, r5)     // Catch:{ Exception -> 0x01a8 }
            goto L_0x0138
        L_0x00e1:
            com.itextpdf.text.ImgRaw r0 = new com.itextpdf.text.ImgRaw     // Catch:{ Exception -> 0x01a8 }
            int r8 = r13.width     // Catch:{ Exception -> 0x01a8 }
            int r9 = r13.height     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.codec.PngImage$NewByteArrayOutputStream r3 = r13.idat     // Catch:{ Exception -> 0x01a8 }
            byte[] r12 = r3.toByteArray()     // Catch:{ Exception -> 0x01a8 }
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x01a8 }
            r0.setDeflated(r2)     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfDictionary r3 = new com.itextpdf.text.pdf.PdfDictionary     // Catch:{ Exception -> 0x01a8 }
            r3.<init>()     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.BITSPERCOMPONENT     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfNumber r7 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ Exception -> 0x01a8 }
            int r8 = r13.bitDepth     // Catch:{ Exception -> 0x01a8 }
            r7.<init>((int) r8)     // Catch:{ Exception -> 0x01a8 }
            r3.put(r5, r7)     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.PREDICTOR     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfNumber r7 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ Exception -> 0x01a8 }
            r8 = 15
            r7.<init>((int) r8)     // Catch:{ Exception -> 0x01a8 }
            r3.put(r5, r7)     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.COLUMNS     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfNumber r7 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ Exception -> 0x01a8 }
            int r8 = r13.width     // Catch:{ Exception -> 0x01a8 }
            r7.<init>((int) r8)     // Catch:{ Exception -> 0x01a8 }
            r3.put(r5, r7)     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.COLORS     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfNumber r7 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ Exception -> 0x01a8 }
            int r8 = r13.colorType     // Catch:{ Exception -> 0x01a8 }
            if (r8 == r6) goto L_0x012a
            int r8 = r13.colorType     // Catch:{ Exception -> 0x01a8 }
            r8 = r8 & r4
            if (r8 != 0) goto L_0x012b
        L_0x012a:
            r6 = r2
        L_0x012b:
            r7.<init>((int) r6)     // Catch:{ Exception -> 0x01a8 }
            r3.put(r5, r7)     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfDictionary r5 = r13.additional     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.DECODEPARMS     // Catch:{ Exception -> 0x01a8 }
            r5.put(r6, r3)     // Catch:{ Exception -> 0x01a8 }
        L_0x0138:
            com.itextpdf.text.pdf.PdfDictionary r3 = r13.additional     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfObject r3 = r3.get(r5)     // Catch:{ Exception -> 0x01a8 }
            if (r3 != 0) goto L_0x014d
            com.itextpdf.text.pdf.PdfDictionary r3 = r13.additional     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfObject r6 = r13.getColorspace()     // Catch:{ Exception -> 0x01a8 }
            r3.put(r5, r6)     // Catch:{ Exception -> 0x01a8 }
        L_0x014d:
            com.itextpdf.text.pdf.PdfName r3 = r13.intent     // Catch:{ Exception -> 0x01a8 }
            if (r3 == 0) goto L_0x015a
            com.itextpdf.text.pdf.PdfDictionary r3 = r13.additional     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.INTENT     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.pdf.PdfName r6 = r13.intent     // Catch:{ Exception -> 0x01a8 }
            r3.put(r5, r6)     // Catch:{ Exception -> 0x01a8 }
        L_0x015a:
            com.itextpdf.text.pdf.PdfDictionary r3 = r13.additional     // Catch:{ Exception -> 0x01a8 }
            int r3 = r3.size()     // Catch:{ Exception -> 0x01a8 }
            if (r3 <= 0) goto L_0x0167
            com.itextpdf.text.pdf.PdfDictionary r3 = r13.additional     // Catch:{ Exception -> 0x01a8 }
            r0.setAdditional(r3)     // Catch:{ Exception -> 0x01a8 }
        L_0x0167:
            com.itextpdf.text.pdf.ICC_Profile r3 = r13.icc_profile     // Catch:{ Exception -> 0x01a8 }
            if (r3 == 0) goto L_0x0170
            com.itextpdf.text.pdf.ICC_Profile r3 = r13.icc_profile     // Catch:{ Exception -> 0x01a8 }
            r0.tagICC(r3)     // Catch:{ Exception -> 0x01a8 }
        L_0x0170:
            boolean r3 = r13.palShades     // Catch:{ Exception -> 0x01a8 }
            if (r3 == 0) goto L_0x0184
            int r3 = r13.width     // Catch:{ Exception -> 0x01a8 }
            int r5 = r13.height     // Catch:{ Exception -> 0x01a8 }
            byte[] r6 = r13.smask     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.Image r1 = com.itextpdf.text.Image.getInstance(r3, r5, r2, r1, r6)     // Catch:{ Exception -> 0x01a8 }
            r1.makeMask()     // Catch:{ Exception -> 0x01a8 }
            r0.setImageMask(r1)     // Catch:{ Exception -> 0x01a8 }
        L_0x0184:
            boolean r1 = r13.genBWMask     // Catch:{ Exception -> 0x01a8 }
            if (r1 == 0) goto L_0x0198
            int r1 = r13.width     // Catch:{ Exception -> 0x01a8 }
            int r3 = r13.height     // Catch:{ Exception -> 0x01a8 }
            byte[] r5 = r13.smask     // Catch:{ Exception -> 0x01a8 }
            com.itextpdf.text.Image r1 = com.itextpdf.text.Image.getInstance(r1, r3, r2, r2, r5)     // Catch:{ Exception -> 0x01a8 }
            r1.makeMask()     // Catch:{ Exception -> 0x01a8 }
            r0.setImageMask(r1)     // Catch:{ Exception -> 0x01a8 }
        L_0x0198:
            int r1 = r13.dpiX     // Catch:{ Exception -> 0x01a8 }
            int r2 = r13.dpiY     // Catch:{ Exception -> 0x01a8 }
            r0.setDpi(r1, r2)     // Catch:{ Exception -> 0x01a8 }
            float r1 = r13.XYRatio     // Catch:{ Exception -> 0x01a8 }
            r0.setXYRatio(r1)     // Catch:{ Exception -> 0x01a8 }
            r0.setOriginalType(r4)     // Catch:{ Exception -> 0x01a8 }
            return r0
        L_0x01a8:
            r0 = move-exception
            com.itextpdf.text.ExceptionConverter r1 = new com.itextpdf.text.ExceptionConverter
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.PngImage.getImage():com.itextpdf.text.Image");
    }

    /* access modifiers changed from: package-private */
    public void decodeIdat() {
        int i = this.bitDepth;
        if (i == 16) {
            i = 8;
        }
        int i2 = -1;
        this.bytesPerPixel = this.bitDepth == 16 ? 2 : 1;
        int i3 = this.colorType;
        if (i3 == 0) {
            i2 = (((i * this.width) + 7) / 8) * this.height;
        } else if (i3 != 6) {
            switch (i3) {
                case 2:
                    i2 = this.width * 3 * this.height;
                    this.bytesPerPixel *= 3;
                    break;
                case 3:
                    if (this.interlaceMethod == 1) {
                        i2 = (((i * this.width) + 7) / 8) * this.height;
                    }
                    this.bytesPerPixel = 1;
                    break;
                case 4:
                    i2 = this.width * this.height;
                    this.bytesPerPixel *= 2;
                    break;
            }
        } else {
            i2 = this.width * 3 * this.height;
            this.bytesPerPixel *= 4;
        }
        if (i2 >= 0) {
            this.image = new byte[i2];
        }
        if (this.palShades) {
            this.smask = new byte[(this.width * this.height)];
        } else if (this.genBWMask) {
            this.smask = new byte[(((this.width + 7) / 8) * this.height)];
        }
        this.dataStream = new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(this.idat.getBuf(), 0, this.idat.size()), new Inflater()));
        if (this.interlaceMethod != 1) {
            decodePass(0, 0, 1, 1, this.width, this.height);
            return;
        }
        decodePass(0, 0, 8, 8, (this.width + 7) / 8, (this.height + 7) / 8);
        decodePass(4, 0, 8, 8, (this.width + 3) / 8, (this.height + 7) / 8);
        decodePass(0, 4, 4, 8, (this.width + 3) / 4, (this.height + 3) / 8);
        decodePass(2, 0, 4, 4, (this.width + 1) / 4, (this.height + 3) / 4);
        decodePass(0, 2, 2, 4, (this.width + 1) / 2, (this.height + 1) / 4);
        decodePass(1, 0, 2, 2, this.width / 2, (this.height + 1) / 2);
        decodePass(0, 1, 1, 2, this.width, this.height / 2);
    }

    /* access modifiers changed from: package-private */
    public void decodePass(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8 = i6;
        if (i5 != 0 && i8 != 0) {
            int i9 = (((this.inputBands * i5) * this.bitDepth) + 7) / 8;
            int i10 = i2;
            byte[] bArr = new byte[i9];
            byte[] bArr2 = new byte[i9];
            int i11 = 0;
            while (i11 < i8) {
                try {
                    i7 = this.dataStream.read();
                    try {
                        this.dataStream.readFully(bArr, 0, i9);
                    } catch (Exception unused) {
                    }
                } catch (Exception unused2) {
                    i7 = 0;
                }
                switch (i7) {
                    case 0:
                        break;
                    case 1:
                        decodeSubFilter(bArr, i9, this.bytesPerPixel);
                        break;
                    case 2:
                        decodeUpFilter(bArr, bArr2, i9);
                        break;
                    case 3:
                        decodeAverageFilter(bArr, bArr2, i9, this.bytesPerPixel);
                        break;
                    case 4:
                        decodePaethFilter(bArr, bArr2, i9, this.bytesPerPixel);
                        break;
                    default:
                        throw new RuntimeException(MessageLocalization.getComposedMessage("png.filter.unknown", new Object[0]));
                }
                processPixels(bArr, i, i3, i10, i5);
                i11++;
                i10 += i4;
                byte[] bArr3 = bArr2;
                bArr2 = bArr;
                bArr = bArr3;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v6, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r2v19, types: [byte] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processPixels(byte[] r25, int r26, int r27, int r28, int r29) {
        /*
            r24 = this;
            r0 = r24
            r1 = r29
            int[] r10 = r24.getPixel(r25)
            int r2 = r0.colorType
            r11 = 1
            r12 = 0
            if (r2 == 0) goto L_0x0019
            r3 = 6
            if (r2 == r3) goto L_0x0016
            switch(r2) {
                case 2: goto L_0x0016;
                case 3: goto L_0x0019;
                case 4: goto L_0x0019;
                default: goto L_0x0014;
            }
        L_0x0014:
            r13 = r12
            goto L_0x001a
        L_0x0016:
            r2 = 3
            r13 = r2
            goto L_0x001a
        L_0x0019:
            r13 = r11
        L_0x001a:
            byte[] r2 = r0.image
            r14 = 16
            r15 = 8
            if (r2 == 0) goto L_0x0051
            int r2 = r0.width
            int r2 = r2 * r13
            int r3 = r0.bitDepth
            if (r3 != r14) goto L_0x002b
            r3 = r15
            goto L_0x002d
        L_0x002b:
            int r3 = r0.bitDepth
        L_0x002d:
            int r2 = r2 * r3
            int r2 = r2 + 7
            int r16 = r2 / 8
            r17 = r26
            r9 = r12
        L_0x0035:
            if (r9 >= r1) goto L_0x0051
            byte[] r2 = r0.image
            int r3 = r0.inputBands
            int r4 = r3 * r9
            int r8 = r0.bitDepth
            r3 = r10
            r5 = r13
            r6 = r17
            r7 = r28
            r18 = r9
            r9 = r16
            setPixel(r2, r3, r4, r5, r6, r7, r8, r9)
            int r17 = r17 + r27
            int r9 = r18 + 1
            goto L_0x0035
        L_0x0051:
            boolean r2 = r0.palShades
            if (r2 == 0) goto L_0x00b9
            int r2 = r0.colorType
            r2 = r2 & 4
            if (r2 == 0) goto L_0x008b
            int r2 = r0.bitDepth
            if (r2 != r14) goto L_0x006e
            r2 = r12
        L_0x0060:
            if (r2 >= r1) goto L_0x006e
            int r3 = r0.inputBands
            int r3 = r3 * r2
            int r3 = r3 + r13
            r4 = r10[r3]
            int r4 = r4 >>> r15
            r10[r3] = r4
            int r2 = r2 + 1
            goto L_0x0060
        L_0x006e:
            int r11 = r0.width
            r14 = r26
        L_0x0072:
            if (r12 >= r1) goto L_0x0173
            byte[] r2 = r0.smask
            int r3 = r0.inputBands
            int r3 = r3 * r12
            int r4 = r3 + r13
            r5 = 1
            r8 = 8
            r3 = r10
            r6 = r14
            r7 = r28
            r9 = r11
            setPixel(r2, r3, r4, r5, r6, r7, r8, r9)
            int r14 = r14 + r27
            int r12 = r12 + 1
            goto L_0x0072
        L_0x008b:
            int r13 = r0.width
            int[] r11 = new int[r11]
            r15 = r26
            r14 = r12
        L_0x0092:
            if (r14 >= r1) goto L_0x0173
            r2 = r10[r14]
            byte[] r3 = r0.trans
            int r3 = r3.length
            if (r2 >= r3) goto L_0x00a2
            byte[] r3 = r0.trans
            byte r2 = r3[r2]
            r11[r12] = r2
            goto L_0x00a6
        L_0x00a2:
            r2 = 255(0xff, float:3.57E-43)
            r11[r12] = r2
        L_0x00a6:
            byte[] r2 = r0.smask
            r4 = 0
            r5 = 1
            r8 = 8
            r3 = r11
            r6 = r15
            r7 = r28
            r9 = r13
            setPixel(r2, r3, r4, r5, r6, r7, r8, r9)
            int r15 = r15 + r27
            int r14 = r14 + 1
            goto L_0x0092
        L_0x00b9:
            boolean r2 = r0.genBWMask
            if (r2 == 0) goto L_0x0173
            int r2 = r0.colorType
            if (r2 == 0) goto L_0x0142
            switch(r2) {
                case 2: goto L_0x00fe;
                case 3: goto L_0x00c6;
                default: goto L_0x00c4;
            }
        L_0x00c4:
            goto L_0x0173
        L_0x00c6:
            int r2 = r0.width
            int r2 = r2 + 7
            int r2 = r2 / r15
            int[] r3 = new int[r11]
            r5 = r26
            r4 = r12
        L_0x00d0:
            if (r4 >= r1) goto L_0x0173
            r6 = r10[r4]
            byte[] r7 = r0.trans
            int r7 = r7.length
            if (r6 >= r7) goto L_0x00e1
            byte[] r7 = r0.trans
            byte r6 = r7[r6]
            if (r6 != 0) goto L_0x00e1
            r6 = r11
            goto L_0x00e2
        L_0x00e1:
            r6 = r12
        L_0x00e2:
            r3[r12] = r6
            byte[] r6 = r0.smask
            r18 = 0
            r19 = 1
            r22 = 1
            r16 = r6
            r17 = r3
            r20 = r5
            r21 = r28
            r23 = r2
            setPixel(r16, r17, r18, r19, r20, r21, r22, r23)
            int r5 = r5 + r27
            int r4 = r4 + 1
            goto L_0x00d0
        L_0x00fe:
            int r2 = r0.width
            int r2 = r2 + 7
            int r2 = r2 / r15
            int[] r3 = new int[r11]
            r5 = r26
            r4 = r12
        L_0x0108:
            if (r4 >= r1) goto L_0x0173
            int r6 = r0.inputBands
            int r6 = r6 * r4
            r7 = r10[r6]
            int r8 = r0.transRedGray
            if (r7 != r8) goto L_0x0125
            int r7 = r6 + 1
            r7 = r10[r7]
            int r8 = r0.transGreen
            if (r7 != r8) goto L_0x0125
            int r6 = r6 + 2
            r6 = r10[r6]
            int r7 = r0.transBlue
            if (r6 != r7) goto L_0x0125
            r6 = r11
            goto L_0x0126
        L_0x0125:
            r6 = r12
        L_0x0126:
            r3[r12] = r6
            byte[] r6 = r0.smask
            r18 = 0
            r19 = 1
            r22 = 1
            r16 = r6
            r17 = r3
            r20 = r5
            r21 = r28
            r23 = r2
            setPixel(r16, r17, r18, r19, r20, r21, r22, r23)
            int r5 = r5 + r27
            int r4 = r4 + 1
            goto L_0x0108
        L_0x0142:
            int r2 = r0.width
            int r2 = r2 + 7
            int r2 = r2 / r15
            int[] r3 = new int[r11]
            r5 = r26
            r4 = r12
        L_0x014c:
            if (r4 >= r1) goto L_0x0173
            r6 = r10[r4]
            int r7 = r0.transRedGray
            if (r6 != r7) goto L_0x0156
            r6 = r11
            goto L_0x0157
        L_0x0156:
            r6 = r12
        L_0x0157:
            r3[r12] = r6
            byte[] r6 = r0.smask
            r18 = 0
            r19 = 1
            r22 = 1
            r16 = r6
            r17 = r3
            r20 = r5
            r21 = r28
            r23 = r2
            setPixel(r16, r17, r18, r19, r20, r21, r22, r23)
            int r5 = r5 + r27
            int r4 = r4 + 1
            goto L_0x014c
        L_0x0173:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.PngImage.processPixels(byte[], int, int, int, int):void");
    }

    static int getPixel(byte[] bArr, int i, int i2, int i3, int i4) {
        if (i3 == 8) {
            return bArr[(i4 * i2) + i] & 255;
        }
        int i5 = i4 * i2;
        int i6 = 8 / i3;
        return (bArr[i5 + (i / i6)] >> ((8 - ((i % i6) * i3)) - i3)) & ((1 << i3) - 1);
    }

    static void setPixel(byte[] bArr, int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = 0;
        if (i5 == 8) {
            int i8 = (i6 * i4) + (i3 * i2);
            while (i7 < i2) {
                bArr[i8 + i7] = (byte) iArr[i7 + i];
                i7++;
            }
        } else if (i5 == 16) {
            int i9 = (i6 * i4) + (i3 * i2);
            while (i7 < i2) {
                bArr[i9 + i7] = (byte) (iArr[i7 + i] >>> 8);
                i7++;
            }
        } else {
            int i10 = 8 / i5;
            int i11 = (i6 * i4) + (i3 / i10);
            bArr[i11] = (byte) ((iArr[i] << ((8 - ((i3 % i10) * i5)) - i5)) | bArr[i11]);
        }
    }

    /* access modifiers changed from: package-private */
    public int[] getPixel(byte[] bArr) {
        int i = this.bitDepth;
        int i2 = 0;
        if (i == 8) {
            int[] iArr = new int[bArr.length];
            while (i2 < iArr.length) {
                iArr[i2] = bArr[i2] & 255;
                i2++;
            }
            return iArr;
        } else if (i != 16) {
            int[] iArr2 = new int[((bArr.length * 8) / this.bitDepth)];
            int i3 = 8 / this.bitDepth;
            int i4 = (1 << this.bitDepth) - 1;
            int i5 = 0;
            while (i2 < bArr.length) {
                int i6 = i3 - 1;
                while (i6 >= 0) {
                    iArr2[i5] = (bArr[i2] >>> (this.bitDepth * i6)) & i4;
                    i6--;
                    i5++;
                }
                i2++;
            }
            return iArr2;
        } else {
            int[] iArr3 = new int[(bArr.length / 2)];
            while (i2 < iArr3.length) {
                int i7 = i2 * 2;
                iArr3[i2] = ((bArr[i7] & 255) << 8) + (bArr[i7 + 1] & 255);
                i2++;
            }
            return iArr3;
        }
    }

    private static void decodeSubFilter(byte[] bArr, int i, int i2) {
        for (int i3 = i2; i3 < i; i3++) {
            bArr[i3] = (byte) ((bArr[i3] & 255) + (bArr[i3 - i2] & 255));
        }
    }

    private static void decodeUpFilter(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((bArr[i2] & 255) + (bArr2[i2] & 255));
        }
    }

    private static void decodeAverageFilter(byte[] bArr, byte[] bArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((bArr[i3] & 255) + ((bArr2[i3] & 255) / 2));
        }
        for (int i4 = i2; i4 < i; i4++) {
            bArr[i4] = (byte) ((bArr[i4] & 255) + (((bArr[i4 - i2] & 255) + (bArr2[i4] & 255)) / 2));
        }
    }

    private static int paethPredictor(int i, int i2, int i3) {
        int i4 = (i + i2) - i3;
        int abs = Math.abs(i4 - i);
        int abs2 = Math.abs(i4 - i2);
        int abs3 = Math.abs(i4 - i3);
        if (abs > abs2 || abs > abs3) {
            return abs2 <= abs3 ? i2 : i3;
        }
        return i;
    }

    private static void decodePaethFilter(byte[] bArr, byte[] bArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((bArr[i3] & 255) + (bArr2[i3] & 255));
        }
        for (int i4 = i2; i4 < i; i4++) {
            int i5 = i4 - i2;
            bArr[i4] = (byte) ((bArr[i4] & 255) + paethPredictor(bArr[i5] & 255, bArr2[i4] & 255, bArr2[i5] & 255));
        }
    }

    static class NewByteArrayOutputStream extends ByteArrayOutputStream {
        NewByteArrayOutputStream() {
        }

        public byte[] getBuf() {
            return this.buf;
        }
    }

    public static final int getInt(InputStream inputStream) throws IOException {
        return (inputStream.read() << 24) + (inputStream.read() << 16) + (inputStream.read() << 8) + inputStream.read();
    }

    public static final int getWord(InputStream inputStream) throws IOException {
        return (inputStream.read() << 8) + inputStream.read();
    }

    public static final String getString(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            stringBuffer.append((char) inputStream.read());
        }
        return stringBuffer.toString();
    }
}
