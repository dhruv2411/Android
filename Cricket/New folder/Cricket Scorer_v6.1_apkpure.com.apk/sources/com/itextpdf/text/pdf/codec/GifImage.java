package com.itextpdf.text.pdf.codec;

import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.ImgRaw;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfString;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class GifImage {
    protected static final int MaxStackSize = 4096;
    protected int bgColor;
    protected int bgIndex;
    protected byte[] block;
    protected int blockSize;
    protected int delay;
    protected int dispose;
    protected ArrayList<GifFrame> frames;
    protected byte[] fromData;
    protected URL fromUrl;
    protected boolean gctFlag;
    protected int height;
    protected int ih;
    protected DataInputStream in;
    protected boolean interlace;
    protected int iw;
    protected int ix;
    protected int iy;
    protected boolean lctFlag;
    protected int lctSize;
    protected int m_bpc;
    protected byte[] m_curr_table;
    protected int m_gbpc;
    protected byte[] m_global_table;
    protected int m_line_stride;
    protected byte[] m_local_table;
    protected byte[] m_out;
    protected int pixelAspect;
    protected byte[] pixelStack;
    protected byte[] pixels;
    protected short[] prefix;
    protected byte[] suffix;
    protected int transIndex;
    protected boolean transparency;
    protected int width;

    protected static int newBpc(int i) {
        switch (i) {
            case 1:
            case 2:
            case 4:
                return i;
            case 3:
                return 4;
            default:
                return 8;
        }
    }

    /* access modifiers changed from: protected */
    public void resetFrame() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0059  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GifImage(java.net.URL r7) throws java.io.IOException {
        /*
            r6 = this;
            r6.<init>()
            r0 = 256(0x100, float:3.59E-43)
            byte[] r0 = new byte[r0]
            r6.block = r0
            r0 = 0
            r6.blockSize = r0
            r6.dispose = r0
            r6.transparency = r0
            r6.delay = r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r6.frames = r1
            r6.fromUrl = r7
            java.io.InputStream r7 = r7.openStream()     // Catch:{ all -> 0x0055 }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0053 }
            r1.<init>()     // Catch:{ all -> 0x0053 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x0053 }
        L_0x0028:
            int r3 = r7.read(r2)     // Catch:{ all -> 0x0053 }
            r4 = -1
            if (r3 == r4) goto L_0x0033
            r1.write(r2, r0, r3)     // Catch:{ all -> 0x0053 }
            goto L_0x0028
        L_0x0033:
            r7.close()     // Catch:{ all -> 0x0053 }
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0053 }
            byte[] r2 = r1.toByteArray()     // Catch:{ all -> 0x0053 }
            r0.<init>(r2)     // Catch:{ all -> 0x0053 }
            r1.flush()     // Catch:{ all -> 0x004e }
            r1.close()     // Catch:{ all -> 0x004e }
            r6.process(r0)     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x004d
            r0.close()
        L_0x004d:
            return
        L_0x004e:
            r7 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto L_0x0057
        L_0x0053:
            r0 = move-exception
            goto L_0x0057
        L_0x0055:
            r0 = move-exception
            r7 = 0
        L_0x0057:
            if (r7 == 0) goto L_0x005c
            r7.close()
        L_0x005c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.GifImage.<init>(java.net.URL):void");
    }

    public GifImage(String str) throws IOException {
        this(Utilities.toURL(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GifImage(byte[] r3) throws java.io.IOException {
        /*
            r2 = this;
            r2.<init>()
            r0 = 256(0x100, float:3.59E-43)
            byte[] r0 = new byte[r0]
            r2.block = r0
            r0 = 0
            r2.blockSize = r0
            r2.dispose = r0
            r2.transparency = r0
            r2.delay = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2.frames = r0
            r2.fromData = r3
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x002d }
            r1.<init>(r3)     // Catch:{ all -> 0x002d }
            r2.process(r1)     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0029
            r1.close()
        L_0x0029:
            return
        L_0x002a:
            r3 = move-exception
            r0 = r1
            goto L_0x002e
        L_0x002d:
            r3 = move-exception
        L_0x002e:
            if (r0 == 0) goto L_0x0033
            r0.close()
        L_0x0033:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.GifImage.<init>(byte[]):void");
    }

    public GifImage(InputStream inputStream) throws IOException {
        this.block = new byte[256];
        this.blockSize = 0;
        this.dispose = 0;
        this.transparency = false;
        this.delay = 0;
        this.frames = new ArrayList<>();
        process(inputStream);
    }

    public int getFrameCount() {
        return this.frames.size();
    }

    public Image getImage(int i) {
        return this.frames.get(i - 1).image;
    }

    public int[] getFramePosition(int i) {
        GifFrame gifFrame = this.frames.get(i - 1);
        return new int[]{gifFrame.ix, gifFrame.iy};
    }

    public int[] getLogicalScreen() {
        return new int[]{this.width, this.height};
    }

    /* access modifiers changed from: package-private */
    public void process(InputStream inputStream) throws IOException {
        this.in = new DataInputStream(new BufferedInputStream(inputStream));
        readHeader();
        readContents();
        if (this.frames.isEmpty()) {
            throw new IOException(MessageLocalization.getComposedMessage("the.file.does.not.contain.any.valid.image", new Object[0]));
        }
    }

    /* access modifiers changed from: protected */
    public void readHeader() throws IOException {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            sb.append((char) this.in.read());
        }
        if (!sb.toString().startsWith("GIF8")) {
            throw new IOException(MessageLocalization.getComposedMessage("gif.signature.nor.found", new Object[0]));
        }
        readLSD();
        if (this.gctFlag) {
            this.m_global_table = readColorTable(this.m_gbpc);
        }
    }

    /* access modifiers changed from: protected */
    public void readLSD() throws IOException {
        this.width = readShort();
        this.height = readShort();
        int read = this.in.read();
        this.gctFlag = (read & 128) != 0;
        this.m_gbpc = (read & 7) + 1;
        this.bgIndex = this.in.read();
        this.pixelAspect = this.in.read();
    }

    /* access modifiers changed from: protected */
    public int readShort() throws IOException {
        return this.in.read() | (this.in.read() << 8);
    }

    /* access modifiers changed from: protected */
    public int readBlock() throws IOException {
        this.blockSize = this.in.read();
        if (this.blockSize <= 0) {
            this.blockSize = 0;
            return 0;
        }
        this.blockSize = this.in.read(this.block, 0, this.blockSize);
        return this.blockSize;
    }

    /* access modifiers changed from: protected */
    public byte[] readColorTable(int i) throws IOException {
        byte[] bArr = new byte[((1 << newBpc(i)) * 3)];
        this.in.readFully(bArr, 0, (1 << i) * 3);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public void readContents() throws IOException {
        boolean z = false;
        while (!z) {
            int read = this.in.read();
            if (read == 33) {
                int read2 = this.in.read();
                if (read2 == 249) {
                    readGraphicControlExt();
                } else if (read2 != 255) {
                    skip();
                } else {
                    readBlock();
                    skip();
                }
            } else if (read != 44) {
                z = true;
            } else {
                readImage();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void readImage() throws IOException {
        this.ix = readShort();
        this.iy = readShort();
        this.iw = readShort();
        this.ih = readShort();
        int read = this.in.read();
        this.lctFlag = (read & 128) != 0;
        this.interlace = (read & 64) != 0;
        int i = read & 7;
        this.lctSize = 2 << i;
        this.m_bpc = newBpc(this.m_gbpc);
        if (this.lctFlag) {
            int i2 = i + 1;
            this.m_curr_table = readColorTable(i2);
            this.m_bpc = newBpc(i2);
        } else {
            this.m_curr_table = this.m_global_table;
        }
        if (this.transparency && this.transIndex >= this.m_curr_table.length / 3) {
            this.transparency = false;
        }
        if (this.transparency && this.m_bpc == 1) {
            byte[] bArr = new byte[12];
            System.arraycopy(this.m_curr_table, 0, bArr, 0, 6);
            this.m_curr_table = bArr;
            this.m_bpc = 2;
        }
        if (!decodeImageData()) {
            skip();
        }
        try {
            ImgRaw imgRaw = new ImgRaw(this.iw, this.ih, 1, this.m_bpc, this.m_out);
            PdfArray pdfArray = new PdfArray();
            pdfArray.add((PdfObject) PdfName.INDEXED);
            pdfArray.add((PdfObject) PdfName.DEVICERGB);
            pdfArray.add((PdfObject) new PdfNumber((this.m_curr_table.length / 3) - 1));
            pdfArray.add((PdfObject) new PdfString(this.m_curr_table));
            PdfDictionary pdfDictionary = new PdfDictionary();
            pdfDictionary.put(PdfName.COLORSPACE, pdfArray);
            imgRaw.setAdditional(pdfDictionary);
            if (this.transparency) {
                imgRaw.setTransparency(new int[]{this.transIndex, this.transIndex});
            }
            imgRaw.setOriginalType(3);
            imgRaw.setOriginalData(this.fromData);
            imgRaw.setUrl(this.fromUrl);
            GifFrame gifFrame = new GifFrame();
            gifFrame.image = imgRaw;
            gifFrame.ix = this.ix;
            gifFrame.iy = this.iy;
            this.frames.add(gifFrame);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean decodeImageData() throws java.io.IOException {
        /*
            r29 = this;
            r0 = r29
            int r1 = r0.iw
            int r2 = r0.ih
            int r1 = r1 * r2
            short[] r2 = r0.prefix
            r3 = 4096(0x1000, float:5.74E-42)
            if (r2 != 0) goto L_0x0011
            short[] r2 = new short[r3]
            r0.prefix = r2
        L_0x0011:
            byte[] r2 = r0.suffix
            if (r2 != 0) goto L_0x0019
            byte[] r2 = new byte[r3]
            r0.suffix = r2
        L_0x0019:
            byte[] r2 = r0.pixelStack
            if (r2 != 0) goto L_0x0023
            r2 = 4097(0x1001, float:5.741E-42)
            byte[] r2 = new byte[r2]
            r0.pixelStack = r2
        L_0x0023:
            int r2 = r0.iw
            int r4 = r0.m_bpc
            int r2 = r2 * r4
            int r2 = r2 + 7
            r4 = 8
            int r2 = r2 / r4
            r0.m_line_stride = r2
            int r2 = r0.m_line_stride
            int r5 = r0.ih
            int r2 = r2 * r5
            byte[] r2 = new byte[r2]
            r0.m_out = r2
            boolean r2 = r0.interlace
            r5 = 1
            if (r2 == 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            r4 = r5
        L_0x003f:
            java.io.DataInputStream r2 = r0.in
            int r2 = r2.read()
            int r6 = r5 << r2
            int r7 = r6 + 1
            int r8 = r6 + 2
            int r2 = r2 + r5
            int r9 = r5 << r2
            int r9 = r9 - r5
            r10 = 0
            r11 = r10
        L_0x0051:
            if (r11 >= r6) goto L_0x005f
            short[] r12 = r0.prefix
            r12[r11] = r10
            byte[] r12 = r0.suffix
            byte r13 = (byte) r11
            r12[r11] = r13
            int r11 = r11 + 1
            goto L_0x0051
        L_0x005f:
            r14 = r2
            r22 = r4
            r21 = r5
            r17 = r9
            r4 = r10
            r12 = r4
            r13 = r12
            r15 = r13
            r16 = r15
            r19 = r16
            r20 = r19
            r23 = r20
            r24 = r23
            r3 = -1
            r10 = r8
        L_0x0076:
            if (r4 >= r1) goto L_0x018f
            if (r12 != 0) goto L_0x0124
            if (r13 >= r14) goto L_0x009a
            if (r15 != 0) goto L_0x008a
            int r15 = r29.readBlock()
            if (r15 > 0) goto L_0x0088
            r18 = r5
            goto L_0x0191
        L_0x0088:
            r19 = 0
        L_0x008a:
            byte[] r11 = r0.block
            byte r11 = r11[r19]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << r13
            int r16 = r16 + r11
            int r13 = r13 + 8
            int r19 = r19 + 1
            r11 = -1
            int r15 = r15 + r11
            goto L_0x0076
        L_0x009a:
            r11 = -1
            r5 = r16 & r17
            int r16 = r16 >> r14
            int r13 = r13 - r14
            if (r5 > r10) goto L_0x018f
            if (r5 != r7) goto L_0x00a6
            goto L_0x018f
        L_0x00a6:
            if (r5 != r6) goto L_0x00af
            r14 = r2
            r10 = r8
            r17 = r9
            r3 = r11
            goto L_0x018c
        L_0x00af:
            if (r3 != r11) goto L_0x00c5
            byte[] r3 = r0.pixelStack
            int r11 = r12 + 1
            r25 = r1
            byte[] r1 = r0.suffix
            byte r1 = r1[r5]
            r3[r12] = r1
            r3 = r5
            r20 = r3
            r12 = r11
            r1 = r25
            goto L_0x018c
        L_0x00c5:
            r25 = r1
            if (r5 != r10) goto L_0x00d6
            byte[] r1 = r0.pixelStack
            int r11 = r12 + 1
            r26 = r2
            r2 = r20
            byte r2 = (byte) r2
            r1[r12] = r2
            r1 = r3
            goto L_0x00da
        L_0x00d6:
            r26 = r2
            r1 = r5
            r11 = r12
        L_0x00da:
            if (r1 <= r6) goto L_0x00f0
            byte[] r2 = r0.pixelStack
            int r12 = r11 + 1
            r27 = r5
            byte[] r5 = r0.suffix
            byte r5 = r5[r1]
            r2[r11] = r5
            short[] r2 = r0.prefix
            short r1 = r2[r1]
            r11 = r12
            r5 = r27
            goto L_0x00da
        L_0x00f0:
            r27 = r5
            byte[] r2 = r0.suffix
            byte r1 = r2[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 4096(0x1000, float:5.74E-42)
            if (r10 < r2) goto L_0x00fe
            goto L_0x018f
        L_0x00fe:
            byte[] r2 = r0.pixelStack
            int r12 = r11 + 1
            byte r5 = (byte) r1
            r2[r11] = r5
            short[] r2 = r0.prefix
            short r3 = (short) r3
            r2[r10] = r3
            byte[] r2 = r0.suffix
            r2[r10] = r5
            int r10 = r10 + 1
            r2 = r10 & r17
            if (r2 != 0) goto L_0x011d
            r5 = 4096(0x1000, float:5.74E-42)
            if (r10 >= r5) goto L_0x011f
            int r14 = r14 + 1
            int r17 = r17 + r10
            goto L_0x011f
        L_0x011d:
            r5 = 4096(0x1000, float:5.74E-42)
        L_0x011f:
            r20 = r1
            r3 = r27
            goto L_0x012c
        L_0x0124:
            r25 = r1
            r26 = r2
            r2 = r20
            r5 = 4096(0x1000, float:5.74E-42)
        L_0x012c:
            r1 = -1
            int r12 = r12 + r1
            int r4 = r4 + 1
            byte[] r2 = r0.pixelStack
            byte r2 = r2[r12]
            r11 = r23
            r1 = r24
            r0.setPixel(r1, r11, r2)
            int r1 = r1 + 1
            int r2 = r0.iw
            if (r1 < r2) goto L_0x0184
            int r1 = r11 + r22
            int r2 = r0.ih
            if (r1 < r2) goto L_0x0179
            boolean r1 = r0.interlace
            if (r1 == 0) goto L_0x016c
            r2 = 1
        L_0x014c:
            int r21 = r21 + 1
            r1 = 4
            r11 = 2
            switch(r21) {
                case 2: goto L_0x0160;
                case 3: goto L_0x015d;
                case 4: goto L_0x0159;
                default: goto L_0x0153;
            }
        L_0x0153:
            int r1 = r0.ih
            int r1 = r1 - r2
            r22 = 0
            goto L_0x0160
        L_0x0159:
            r1 = r2
            r22 = r11
            goto L_0x0160
        L_0x015d:
            r22 = r1
            r1 = r11
        L_0x0160:
            int r11 = r0.ih
            if (r1 >= r11) goto L_0x014c
            r23 = r1
            r5 = r2
            r1 = r25
            r2 = r26
            goto L_0x0180
        L_0x016c:
            r2 = 1
            int r1 = r0.ih
            int r23 = r1 + -1
            r5 = r2
            r1 = r25
            r2 = r26
            r22 = 0
            goto L_0x0180
        L_0x0179:
            r23 = r1
            r1 = r25
            r2 = r26
            r5 = 1
        L_0x0180:
            r24 = 0
            goto L_0x0076
        L_0x0184:
            r24 = r1
            r23 = r11
            r1 = r25
            r2 = r26
        L_0x018c:
            r5 = 1
            goto L_0x0076
        L_0x018f:
            r18 = 0
        L_0x0191:
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.GifImage.decodeImageData():boolean");
    }

    /* access modifiers changed from: protected */
    public void setPixel(int i, int i2, int i3) {
        if (this.m_bpc == 8) {
            this.m_out[i + (this.iw * i2)] = (byte) i3;
            return;
        }
        int i4 = (this.m_line_stride * i2) + (i / (8 / this.m_bpc));
        byte[] bArr = this.m_out;
        bArr[i4] = (byte) ((i3 << ((8 - (this.m_bpc * (i % (8 / this.m_bpc)))) - this.m_bpc)) | bArr[i4]);
    }

    /* access modifiers changed from: protected */
    public void readGraphicControlExt() throws IOException {
        this.in.read();
        int read = this.in.read();
        this.dispose = (read & 28) >> 2;
        boolean z = true;
        if (this.dispose == 0) {
            this.dispose = 1;
        }
        if ((read & 1) == 0) {
            z = false;
        }
        this.transparency = z;
        this.delay = readShort() * 10;
        this.transIndex = this.in.read();
        this.in.read();
    }

    /* access modifiers changed from: protected */
    public void skip() throws IOException {
        do {
            readBlock();
        } while (this.blockSize > 0);
    }

    static class GifFrame {
        Image image;
        int ix;
        int iy;

        GifFrame() {
        }
    }
}
