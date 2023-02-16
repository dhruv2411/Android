package com.itextpdf.text.pdf;

import com.itextpdf.text.Image;
import com.itextpdf.text.html.HtmlTags;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PdfImage extends PdfStream {
    static final int TRANSFERSIZE = 4096;
    protected Image image = null;
    protected PdfName name = null;

    /* JADX WARNING: Removed duplicated region for block: B:165:0x03c6 A[SYNTHETIC, Splitter:B:165:0x03c6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PdfImage(com.itextpdf.text.Image r8, java.lang.String r9, com.itextpdf.text.pdf.PdfIndirectReference r10) throws com.itextpdf.text.pdf.BadPdfFormatException {
        /*
            r7 = this;
            r7.<init>()
            r0 = 0
            r7.name = r0
            r7.image = r0
            r7.image = r8
            if (r9 != 0) goto L_0x0010
            r7.generateImgResName(r8)
            goto L_0x0017
        L_0x0010:
            com.itextpdf.text.pdf.PdfName r1 = new com.itextpdf.text.pdf.PdfName
            r1.<init>((java.lang.String) r9)
            r7.name = r1
        L_0x0017:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.TYPE
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.XOBJECT
            r7.put(r9, r1)
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.SUBTYPE
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.IMAGE
            r7.put(r9, r1)
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.WIDTH
            com.itextpdf.text.pdf.PdfNumber r1 = new com.itextpdf.text.pdf.PdfNumber
            float r2 = r8.getWidth()
            r1.<init>((float) r2)
            r7.put(r9, r1)
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.HEIGHT
            com.itextpdf.text.pdf.PdfNumber r1 = new com.itextpdf.text.pdf.PdfNumber
            float r2 = r8.getHeight()
            r1.<init>((float) r2)
            r7.put(r9, r1)
            com.itextpdf.text.pdf.PdfOCG r9 = r8.getLayer()
            if (r9 == 0) goto L_0x0054
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.OC
            com.itextpdf.text.pdf.PdfOCG r1 = r8.getLayer()
            com.itextpdf.text.pdf.PdfIndirectReference r1 = r1.getRef()
            r7.put(r9, r1)
        L_0x0054:
            boolean r9 = r8.isMask()
            r1 = 255(0xff, float:3.57E-43)
            r2 = 1
            if (r9 == 0) goto L_0x0070
            int r9 = r8.getBpc()
            if (r9 == r2) goto L_0x0069
            int r9 = r8.getBpc()
            if (r9 <= r1) goto L_0x0070
        L_0x0069:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.IMAGEMASK
            com.itextpdf.text.pdf.PdfBoolean r3 = com.itextpdf.text.pdf.PdfBoolean.PDFTRUE
            r7.put(r9, r3)
        L_0x0070:
            if (r10 == 0) goto L_0x0083
            boolean r9 = r8.isSmask()
            if (r9 == 0) goto L_0x007e
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.SMASK
            r7.put(r9, r10)
            goto L_0x0083
        L_0x007e:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.MASK
            r7.put(r9, r10)
        L_0x0083:
            boolean r9 = r8.isMask()
            if (r9 == 0) goto L_0x009b
            boolean r9 = r8.isInverted()
            if (r9 == 0) goto L_0x009b
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.DECODE
            com.itextpdf.text.pdf.PdfLiteral r3 = new com.itextpdf.text.pdf.PdfLiteral
            java.lang.String r4 = "[1 0]"
            r3.<init>((java.lang.String) r4)
            r7.put(r9, r3)
        L_0x009b:
            boolean r9 = r8.isInterpolation()
            if (r9 == 0) goto L_0x00a8
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.INTERPOLATE
            com.itextpdf.text.pdf.PdfBoolean r3 = com.itextpdf.text.pdf.PdfBoolean.PDFTRUE
            r7.put(r9, r3)
        L_0x00a8:
            int[] r9 = r8.getTransparency()     // Catch:{ IOException -> 0x03b9 }
            r3 = 0
            if (r9 == 0) goto L_0x00e2
            boolean r4 = r8.isMask()     // Catch:{ IOException -> 0x03b9 }
            if (r4 != 0) goto L_0x00e2
            if (r10 != 0) goto L_0x00e2
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03b9 }
            java.lang.String r4 = "["
            r10.<init>(r4)     // Catch:{ IOException -> 0x03b9 }
            r4 = r3
        L_0x00bf:
            int r5 = r9.length     // Catch:{ IOException -> 0x03b9 }
            if (r4 >= r5) goto L_0x00cf
            r5 = r9[r4]     // Catch:{ IOException -> 0x03b9 }
            r10.append(r5)     // Catch:{ IOException -> 0x03b9 }
            java.lang.String r5 = " "
            r10.append(r5)     // Catch:{ IOException -> 0x03b9 }
            int r4 = r4 + 1
            goto L_0x00bf
        L_0x00cf:
            java.lang.String r9 = "]"
            r10.append(r9)     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.MASK     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfLiteral r4 = new com.itextpdf.text.pdf.PdfLiteral     // Catch:{ IOException -> 0x03b9 }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x03b9 }
            r4.<init>((java.lang.String) r10)     // Catch:{ IOException -> 0x03b9 }
            r7.put(r9, r4)     // Catch:{ IOException -> 0x03b9 }
        L_0x00e2:
            boolean r9 = r8.isImgRaw()     // Catch:{ IOException -> 0x03b9 }
            r10 = 3
            r4 = 8
            if (r9 == 0) goto L_0x021c
            int r9 = r8.getColorspace()     // Catch:{ IOException -> 0x03b9 }
            byte[] r3 = r8.getRawData()     // Catch:{ IOException -> 0x03b9 }
            r7.bytes = r3     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.LENGTH     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfNumber r5 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x03b9 }
            byte[] r6 = r7.bytes     // Catch:{ IOException -> 0x03b9 }
            int r6 = r6.length     // Catch:{ IOException -> 0x03b9 }
            r5.<init>((int) r6)     // Catch:{ IOException -> 0x03b9 }
            r7.put(r3, r5)     // Catch:{ IOException -> 0x03b9 }
            int r3 = r8.getBpc()     // Catch:{ IOException -> 0x03b9 }
            if (r3 <= r1) goto L_0x0187
            boolean r10 = r8.isMask()     // Catch:{ IOException -> 0x03b9 }
            if (r10 != 0) goto L_0x0115
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.DEVICEGRAY     // Catch:{ IOException -> 0x03b9 }
            r7.put(r10, r1)     // Catch:{ IOException -> 0x03b9 }
        L_0x0115:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.BITSPERCOMPONENT     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfNumber r1 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x03b9 }
            r1.<init>((int) r2)     // Catch:{ IOException -> 0x03b9 }
            r7.put(r10, r1)     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.FILTER     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.CCITTFAXDECODE     // Catch:{ IOException -> 0x03b9 }
            r7.put(r10, r1)     // Catch:{ IOException -> 0x03b9 }
            int r3 = r3 + -257
            com.itextpdf.text.pdf.PdfDictionary r10 = new com.itextpdf.text.pdf.PdfDictionary     // Catch:{ IOException -> 0x03b9 }
            r10.<init>()     // Catch:{ IOException -> 0x03b9 }
            if (r3 == 0) goto L_0x0139
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.K     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfNumber r2 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x03b9 }
            r2.<init>((int) r3)     // Catch:{ IOException -> 0x03b9 }
            r10.put(r1, r2)     // Catch:{ IOException -> 0x03b9 }
        L_0x0139:
            r1 = r9 & 1
            if (r1 == 0) goto L_0x0144
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.BLACKIS1     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfBoolean r2 = com.itextpdf.text.pdf.PdfBoolean.PDFTRUE     // Catch:{ IOException -> 0x03b9 }
            r10.put(r1, r2)     // Catch:{ IOException -> 0x03b9 }
        L_0x0144:
            r1 = r9 & 2
            if (r1 == 0) goto L_0x014f
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.ENCODEDBYTEALIGN     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfBoolean r2 = com.itextpdf.text.pdf.PdfBoolean.PDFTRUE     // Catch:{ IOException -> 0x03b9 }
            r10.put(r1, r2)     // Catch:{ IOException -> 0x03b9 }
        L_0x014f:
            r1 = r9 & 4
            if (r1 == 0) goto L_0x015a
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.ENDOFLINE     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfBoolean r2 = com.itextpdf.text.pdf.PdfBoolean.PDFTRUE     // Catch:{ IOException -> 0x03b9 }
            r10.put(r1, r2)     // Catch:{ IOException -> 0x03b9 }
        L_0x015a:
            r9 = r9 & r4
            if (r9 == 0) goto L_0x0164
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.ENDOFBLOCK     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfBoolean r1 = com.itextpdf.text.pdf.PdfBoolean.PDFFALSE     // Catch:{ IOException -> 0x03b9 }
            r10.put(r9, r1)     // Catch:{ IOException -> 0x03b9 }
        L_0x0164:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.COLUMNS     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfNumber r1 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x03b9 }
            float r2 = r8.getWidth()     // Catch:{ IOException -> 0x03b9 }
            r1.<init>((float) r2)     // Catch:{ IOException -> 0x03b9 }
            r10.put(r9, r1)     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.ROWS     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfNumber r1 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x03b9 }
            float r8 = r8.getHeight()     // Catch:{ IOException -> 0x03b9 }
            r1.<init>((float) r8)     // Catch:{ IOException -> 0x03b9 }
            r10.put(r9, r1)     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.DECODEPARMS     // Catch:{ IOException -> 0x03b9 }
            r7.put(r8, r10)     // Catch:{ IOException -> 0x03b9 }
            goto L_0x021b
        L_0x0187:
            if (r9 == r2) goto L_0x01bf
            if (r9 == r10) goto L_0x01a5
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.DEVICECMYK     // Catch:{ IOException -> 0x03b9 }
            r7.put(r9, r10)     // Catch:{ IOException -> 0x03b9 }
            boolean r9 = r8.isInverted()     // Catch:{ IOException -> 0x03b9 }
            if (r9 == 0) goto L_0x01d8
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.DECODE     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfLiteral r10 = new com.itextpdf.text.pdf.PdfLiteral     // Catch:{ IOException -> 0x03b9 }
            java.lang.String r1 = "[1 0 1 0 1 0 1 0]"
            r10.<init>((java.lang.String) r1)     // Catch:{ IOException -> 0x03b9 }
            r7.put(r9, r10)     // Catch:{ IOException -> 0x03b9 }
            goto L_0x01d8
        L_0x01a5:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.DEVICERGB     // Catch:{ IOException -> 0x03b9 }
            r7.put(r9, r10)     // Catch:{ IOException -> 0x03b9 }
            boolean r9 = r8.isInverted()     // Catch:{ IOException -> 0x03b9 }
            if (r9 == 0) goto L_0x01d8
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.DECODE     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfLiteral r10 = new com.itextpdf.text.pdf.PdfLiteral     // Catch:{ IOException -> 0x03b9 }
            java.lang.String r1 = "[1 0 1 0 1 0]"
            r10.<init>((java.lang.String) r1)     // Catch:{ IOException -> 0x03b9 }
            r7.put(r9, r10)     // Catch:{ IOException -> 0x03b9 }
            goto L_0x01d8
        L_0x01bf:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.DEVICEGRAY     // Catch:{ IOException -> 0x03b9 }
            r7.put(r9, r10)     // Catch:{ IOException -> 0x03b9 }
            boolean r9 = r8.isInverted()     // Catch:{ IOException -> 0x03b9 }
            if (r9 == 0) goto L_0x01d8
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.DECODE     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfLiteral r10 = new com.itextpdf.text.pdf.PdfLiteral     // Catch:{ IOException -> 0x03b9 }
            java.lang.String r1 = "[1 0]"
            r10.<init>((java.lang.String) r1)     // Catch:{ IOException -> 0x03b9 }
            r7.put(r9, r10)     // Catch:{ IOException -> 0x03b9 }
        L_0x01d8:
            com.itextpdf.text.pdf.PdfDictionary r9 = r8.getAdditional()     // Catch:{ IOException -> 0x03b9 }
            if (r9 == 0) goto L_0x01e1
            r7.putAll(r9)     // Catch:{ IOException -> 0x03b9 }
        L_0x01e1:
            boolean r9 = r8.isMask()     // Catch:{ IOException -> 0x03b9 }
            if (r9 == 0) goto L_0x01f8
            int r9 = r8.getBpc()     // Catch:{ IOException -> 0x03b9 }
            if (r9 == r2) goto L_0x01f3
            int r9 = r8.getBpc()     // Catch:{ IOException -> 0x03b9 }
            if (r9 <= r4) goto L_0x01f8
        L_0x01f3:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x03b9 }
            r7.remove(r9)     // Catch:{ IOException -> 0x03b9 }
        L_0x01f8:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.BITSPERCOMPONENT     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfNumber r10 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x03b9 }
            int r1 = r8.getBpc()     // Catch:{ IOException -> 0x03b9 }
            r10.<init>((int) r1)     // Catch:{ IOException -> 0x03b9 }
            r7.put(r9, r10)     // Catch:{ IOException -> 0x03b9 }
            boolean r9 = r8.isDeflated()     // Catch:{ IOException -> 0x03b9 }
            if (r9 == 0) goto L_0x0214
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.FILTER     // Catch:{ IOException -> 0x03b9 }
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.FLATEDECODE     // Catch:{ IOException -> 0x03b9 }
            r7.put(r8, r9)     // Catch:{ IOException -> 0x03b9 }
            goto L_0x021b
        L_0x0214:
            int r8 = r8.getCompressionLevel()     // Catch:{ IOException -> 0x03b9 }
            r7.flateCompress(r8)     // Catch:{ IOException -> 0x03b9 }
        L_0x021b:
            return
        L_0x021c:
            byte[] r9 = r8.getRawData()     // Catch:{ IOException -> 0x03b9 }
            if (r9 != 0) goto L_0x023b
            java.net.URL r9 = r8.getUrl()     // Catch:{ IOException -> 0x03b9 }
            java.io.InputStream r9 = r9.openStream()     // Catch:{ IOException -> 0x03b9 }
            java.net.URL r0 = r8.getUrl()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            goto L_0x0246
        L_0x0233:
            r8 = move-exception
            r0 = r9
            goto L_0x03c4
        L_0x0237:
            r8 = move-exception
            r0 = r9
            goto L_0x03ba
        L_0x023b:
            java.io.ByteArrayInputStream r9 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x03b9 }
            byte[] r1 = r8.getRawData()     // Catch:{ IOException -> 0x03b9 }
            r9.<init>(r1)     // Catch:{ IOException -> 0x03b9 }
            java.lang.String r0 = "Byte array"
        L_0x0246:
            int r1 = r8.type()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r5 = 36
            r6 = -1
            if (r1 == r5) goto L_0x0351
            switch(r1) {
                case 32: goto L_0x02c9;
                case 33: goto L_0x0262;
                default: goto L_0x0252;
            }     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
        L_0x0252:
            com.itextpdf.text.pdf.BadPdfFormatException r8 = new com.itextpdf.text.pdf.BadPdfFormatException     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            java.lang.String r10 = "1.is.an.unknown.image.format"
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r1[r3] = r0     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            java.lang.String r10 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r10, (java.lang.Object[]) r1)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r8.<init>(r10)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            throw r8     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
        L_0x0262:
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.FILTER     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.JPXDECODE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r0, r1)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            int r0 = r8.getColorspace()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r0 <= 0) goto L_0x029c
            int r0 = r8.getColorspace()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r0 == r2) goto L_0x0287
            if (r0 == r10) goto L_0x027f
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.DEVICECMYK     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            goto L_0x028e
        L_0x027f:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.DEVICERGB     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            goto L_0x028e
        L_0x0287:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.DEVICEGRAY     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
        L_0x028e:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.BITSPERCOMPONENT     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfNumber r0 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            int r1 = r8.getBpc()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r0.<init>((int) r1)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
        L_0x029c:
            byte[] r10 = r8.getRawData()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r10 == 0) goto L_0x02bb
            byte[] r8 = r8.getRawData()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.bytes = r8     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.LENGTH     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfNumber r10 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            byte[] r0 = r7.bytes     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            int r0 = r0.length     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r10.<init>((int) r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r8, r10)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r9 == 0) goto L_0x02ba
            r9.close()     // Catch:{ Exception -> 0x02ba }
        L_0x02ba:
            return
        L_0x02bb:
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r10.<init>()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.streamBytes = r10     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            java.io.ByteArrayOutputStream r10 = r7.streamBytes     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            transferBytes(r9, r10, r6)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            goto L_0x0394
        L_0x02c9:
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.FILTER     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.DCTDECODE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r0, r1)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            int r0 = r8.getColorTransform()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r0 != 0) goto L_0x02ea
            com.itextpdf.text.pdf.PdfDictionary r0 = new com.itextpdf.text.pdf.PdfDictionary     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r0.<init>()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.COLORTRANSFORM     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfNumber r5 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r5.<init>((int) r3)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r0.put(r1, r5)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.DECODEPARMS     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r1, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
        L_0x02ea:
            int r0 = r8.getColorspace()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r0 == r2) goto L_0x0314
            if (r0 == r10) goto L_0x030c
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.DEVICECMYK     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            boolean r10 = r8.isInverted()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r10 == 0) goto L_0x031b
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.DECODE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfLiteral r0 = new com.itextpdf.text.pdf.PdfLiteral     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            java.lang.String r1 = "[1 0 1 0 1 0 1 0]"
            r0.<init>((java.lang.String) r1)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            goto L_0x031b
        L_0x030c:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.DEVICERGB     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            goto L_0x031b
        L_0x0314:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.DEVICEGRAY     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
        L_0x031b:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.BITSPERCOMPONENT     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfNumber r0 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r0.<init>((int) r4)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            byte[] r10 = r8.getRawData()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r10 == 0) goto L_0x0344
            byte[] r8 = r8.getRawData()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.bytes = r8     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.LENGTH     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfNumber r10 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            byte[] r0 = r7.bytes     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            int r0 = r0.length     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r10.<init>((int) r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r8, r10)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r9 == 0) goto L_0x0343
            r9.close()     // Catch:{ Exception -> 0x0343 }
        L_0x0343:
            return
        L_0x0344:
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r10.<init>()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.streamBytes = r10     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            java.io.ByteArrayOutputStream r10 = r7.streamBytes     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            transferBytes(r9, r10, r6)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            goto L_0x0394
        L_0x0351:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.FILTER     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.JBIG2DECODE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.DEVICEGRAY     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.BITSPERCOMPONENT     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfNumber r0 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r0.<init>((int) r2)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r10, r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            byte[] r10 = r8.getRawData()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r10 == 0) goto L_0x0388
            byte[] r8 = r8.getRawData()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.bytes = r8     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.LENGTH     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfNumber r10 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            byte[] r0 = r7.bytes     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            int r0 = r0.length     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r10.<init>((int) r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r8, r10)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r9 == 0) goto L_0x0387
            r9.close()     // Catch:{ Exception -> 0x0387 }
        L_0x0387:
            return
        L_0x0388:
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r10.<init>()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.streamBytes = r10     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            java.io.ByteArrayOutputStream r10 = r7.streamBytes     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            transferBytes(r9, r10, r6)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
        L_0x0394:
            int r10 = r8.getCompressionLevel()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r10 <= 0) goto L_0x03a1
            int r8 = r8.getCompressionLevel()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.flateCompress(r8)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
        L_0x03a1:
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.LENGTH     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            com.itextpdf.text.pdf.PdfNumber r10 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            java.io.ByteArrayOutputStream r0 = r7.streamBytes     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            int r0 = r0.size()     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r10.<init>((int) r0)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            r7.put(r8, r10)     // Catch:{ IOException -> 0x0237, all -> 0x0233 }
            if (r9 == 0) goto L_0x03b6
            r9.close()     // Catch:{ Exception -> 0x03b6 }
        L_0x03b6:
            return
        L_0x03b7:
            r8 = move-exception
            goto L_0x03c4
        L_0x03b9:
            r8 = move-exception
        L_0x03ba:
            com.itextpdf.text.pdf.BadPdfFormatException r9 = new com.itextpdf.text.pdf.BadPdfFormatException     // Catch:{ all -> 0x03b7 }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x03b7 }
            r9.<init>(r8)     // Catch:{ all -> 0x03b7 }
            throw r9     // Catch:{ all -> 0x03b7 }
        L_0x03c4:
            if (r0 == 0) goto L_0x03c9
            r0.close()     // Catch:{ Exception -> 0x03c9 }
        L_0x03c9:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfImage.<init>(com.itextpdf.text.Image, java.lang.String, com.itextpdf.text.pdf.PdfIndirectReference):void");
    }

    public PdfName name() {
        return this.name;
    }

    public Image getImage() {
        return this.image;
    }

    static void transferBytes(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        byte[] bArr = new byte[4096];
        if (i < 0) {
            i = 2147418112;
        }
        while (i != 0) {
            int read = inputStream.read(bArr, 0, Math.min(i, 4096));
            if (read >= 0) {
                outputStream.write(bArr, 0, read);
                i -= read;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void importAll(PdfImage pdfImage) {
        this.name = pdfImage.name;
        this.compressed = pdfImage.compressed;
        this.compressionLevel = pdfImage.compressionLevel;
        this.streamBytes = pdfImage.streamBytes;
        this.bytes = pdfImage.bytes;
        this.hashMap = pdfImage.hashMap;
    }

    private void generateImgResName(Image image2) {
        this.name = new PdfName(HtmlTags.IMG + Long.toHexString(image2.getMySerialId().longValue()));
    }
}
