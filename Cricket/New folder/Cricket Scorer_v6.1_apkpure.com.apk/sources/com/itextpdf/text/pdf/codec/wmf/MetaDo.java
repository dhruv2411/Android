package com.itextpdf.text.pdf.codec.wmf;

import com.itextpdf.text.Image;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.PdfContentByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MetaDo {
    public static final int META_ANIMATEPALETTE = 1078;
    public static final int META_ARC = 2071;
    public static final int META_BITBLT = 2338;
    public static final int META_CHORD = 2096;
    public static final int META_CREATEBRUSHINDIRECT = 764;
    public static final int META_CREATEFONTINDIRECT = 763;
    public static final int META_CREATEPALETTE = 247;
    public static final int META_CREATEPATTERNBRUSH = 505;
    public static final int META_CREATEPENINDIRECT = 762;
    public static final int META_CREATEREGION = 1791;
    public static final int META_DELETEOBJECT = 496;
    public static final int META_DIBBITBLT = 2368;
    public static final int META_DIBCREATEPATTERNBRUSH = 322;
    public static final int META_DIBSTRETCHBLT = 2881;
    public static final int META_ELLIPSE = 1048;
    public static final int META_ESCAPE = 1574;
    public static final int META_EXCLUDECLIPRECT = 1045;
    public static final int META_EXTFLOODFILL = 1352;
    public static final int META_EXTTEXTOUT = 2610;
    public static final int META_FILLREGION = 552;
    public static final int META_FLOODFILL = 1049;
    public static final int META_FRAMEREGION = 1065;
    public static final int META_INTERSECTCLIPRECT = 1046;
    public static final int META_INVERTREGION = 298;
    public static final int META_LINETO = 531;
    public static final int META_MOVETO = 532;
    public static final int META_OFFSETCLIPRGN = 544;
    public static final int META_OFFSETVIEWPORTORG = 529;
    public static final int META_OFFSETWINDOWORG = 527;
    public static final int META_PAINTREGION = 299;
    public static final int META_PATBLT = 1565;
    public static final int META_PIE = 2074;
    public static final int META_POLYGON = 804;
    public static final int META_POLYLINE = 805;
    public static final int META_POLYPOLYGON = 1336;
    public static final int META_REALIZEPALETTE = 53;
    public static final int META_RECTANGLE = 1051;
    public static final int META_RESIZEPALETTE = 313;
    public static final int META_RESTOREDC = 295;
    public static final int META_ROUNDRECT = 1564;
    public static final int META_SAVEDC = 30;
    public static final int META_SCALEVIEWPORTEXT = 1042;
    public static final int META_SCALEWINDOWEXT = 1040;
    public static final int META_SELECTCLIPREGION = 300;
    public static final int META_SELECTOBJECT = 301;
    public static final int META_SELECTPALETTE = 564;
    public static final int META_SETBKCOLOR = 513;
    public static final int META_SETBKMODE = 258;
    public static final int META_SETDIBTODEV = 3379;
    public static final int META_SETMAPMODE = 259;
    public static final int META_SETMAPPERFLAGS = 561;
    public static final int META_SETPALENTRIES = 55;
    public static final int META_SETPIXEL = 1055;
    public static final int META_SETPOLYFILLMODE = 262;
    public static final int META_SETRELABS = 261;
    public static final int META_SETROP2 = 260;
    public static final int META_SETSTRETCHBLTMODE = 263;
    public static final int META_SETTEXTALIGN = 302;
    public static final int META_SETTEXTCHAREXTRA = 264;
    public static final int META_SETTEXTCOLOR = 521;
    public static final int META_SETTEXTJUSTIFICATION = 522;
    public static final int META_SETVIEWPORTEXT = 526;
    public static final int META_SETVIEWPORTORG = 525;
    public static final int META_SETWINDOWEXT = 524;
    public static final int META_SETWINDOWORG = 523;
    public static final int META_STRETCHBLT = 2851;
    public static final int META_STRETCHDIB = 3907;
    public static final int META_TEXTOUT = 1313;
    int bottom;
    public PdfContentByte cb;
    public InputMeta in;
    int inch;
    int left;
    int right;
    MetaState state = new MetaState();
    int top;

    public MetaDo(InputStream inputStream, PdfContentByte pdfContentByte) {
        this.cb = pdfContentByte;
        this.in = new InputMeta(inputStream);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x06fb, code lost:
        r0 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readAll() throws java.io.IOException, com.itextpdf.text.DocumentException {
        /*
            r40 = this;
            r9 = r40
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readInt()
            r10 = 0
            r1 = -1698247209(0xffffffff9ac6cdd7, float:-8.222343E-23)
            if (r0 == r1) goto L_0x001c
            com.itextpdf.text.DocumentException r0 = new com.itextpdf.text.DocumentException
            java.lang.String r1 = "not.a.placeable.windows.metafile"
            java.lang.Object[] r2 = new java.lang.Object[r10]
            java.lang.String r1 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r1, (java.lang.Object[]) r2)
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x001c:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            r0.readWord()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readShort()
            r9.left = r0
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readShort()
            r9.top = r0
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readShort()
            r9.right = r0
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readShort()
            r9.bottom = r0
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readWord()
            r9.inch = r0
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            int r1 = r9.right
            int r2 = r9.left
            int r1 = r1 - r2
            float r1 = (float) r1
            int r2 = r9.inch
            float r2 = (float) r2
            float r1 = r1 / r2
            r2 = 1116733440(0x42900000, float:72.0)
            float r1 = r1 * r2
            r0.setScalingX(r1)
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            int r1 = r9.bottom
            int r3 = r9.top
            int r1 = r1 - r3
            float r1 = (float) r1
            int r3 = r9.inch
            float r3 = (float) r3
            float r1 = r1 / r3
            float r1 = r1 * r2
            r0.setScalingY(r1)
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            int r1 = r9.left
            r0.setOffsetWx(r1)
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            int r1 = r9.top
            r0.setOffsetWy(r1)
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            int r1 = r9.right
            int r2 = r9.left
            int r1 = r1 - r2
            r0.setExtentWx(r1)
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            int r1 = r9.bottom
            int r2 = r9.top
            int r1 = r1 - r2
            r0.setExtentWy(r1)
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            r0.readInt()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            r0.readWord()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            r1 = 18
            r0.skip(r1)
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r11 = 1
            r0.setLineCap(r11)
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r0.setLineJoin(r11)
        L_0x00a9:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r12 = r0.getLength()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r13 = r0.readInt()
            r0 = 3
            if (r13 >= r0) goto L_0x00c0
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            com.itextpdf.text.pdf.PdfContentByte r1 = r9.cb
            r0.cleanup(r1)
            return
        L_0x00c0:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r1 = r9.in
            int r1 = r1.readWord()
            r16 = 1073741824(0x40000000, float:2.0)
            switch(r1) {
                case 0: goto L_0x00cb;
                case 30: goto L_0x0957;
                case 247: goto L_0x0946;
                case 258: goto L_0x0934;
                case 262: goto L_0x0922;
                case 295: goto L_0x090e;
                case 301: goto L_0x08fa;
                case 302: goto L_0x08e7;
                case 322: goto L_0x0946;
                case 496: goto L_0x08d4;
                case 513: goto L_0x08c1;
                case 521: goto L_0x08ae;
                case 523: goto L_0x0890;
                case 524: goto L_0x0872;
                case 531: goto L_0x0823;
                case 532: goto L_0x0805;
                case 762: goto L_0x07ee;
                case 763: goto L_0x07d7;
                case 764: goto L_0x07c0;
                case 804: goto L_0x0756;
                case 805: goto L_0x06fe;
                case 1046: goto L_0x06b5;
                case 1048: goto L_0x0666;
                case 1051: goto L_0x061e;
                case 1055: goto L_0x05dc;
                case 1313: goto L_0x058c;
                case 1336: goto L_0x0509;
                case 1564: goto L_0x0493;
                case 1791: goto L_0x0946;
                case 2071: goto L_0x03f7;
                case 2074: goto L_0x0301;
                case 2096: goto L_0x0201;
                case 2610: goto L_0x01a0;
                case 2881: goto L_0x00d3;
                case 3907: goto L_0x00d3;
                default: goto L_0x00cb;
            }
        L_0x00cb:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            goto L_0x0964
        L_0x00d3:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            r0.readInt()
            r0 = 3907(0xf43, float:5.475E-42)
            if (r1 != r0) goto L_0x00e1
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            r0.readWord()
        L_0x00e1:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r1 = r9.in
            int r1 = r1.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            float r4 = r4.transformY(r5)
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            float r5 = r5.transformY(r10)
            float r4 = r4 - r5
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r6 = r9.in
            int r6 = r6.readShort()
            float r5 = r5.transformX(r6)
            com.itextpdf.text.pdf.codec.wmf.MetaState r6 = r9.state
            float r6 = r6.transformX(r10)
            float r5 = r5 - r6
            com.itextpdf.text.pdf.codec.wmf.MetaState r6 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r7 = r9.in
            int r7 = r7.readShort()
            float r6 = r6.transformY(r7)
            com.itextpdf.text.pdf.codec.wmf.MetaState r7 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r8 = r9.in
            int r8 = r8.readShort()
            float r7 = r7.transformX(r8)
            int r8 = r13 * 2
            com.itextpdf.text.pdf.codec.wmf.InputMeta r14 = r9.in
            int r14 = r14.getLength()
            int r14 = r14 - r12
            int r8 = r8 - r14
            byte[] r8 = new byte[r8]
            r14 = r10
        L_0x0144:
            int r15 = r8.length
            if (r14 >= r15) goto L_0x0153
            com.itextpdf.text.pdf.codec.wmf.InputMeta r15 = r9.in
            int r15 = r15.readByte()
            byte r15 = (byte) r15
            r8[r14] = r15
            int r14 = r14 + 1
            goto L_0x0144
        L_0x0153:
            java.io.ByteArrayInputStream r14 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x00cb }
            r14.<init>(r8)     // Catch:{ Exception -> 0x00cb }
            int r8 = r8.length     // Catch:{ Exception -> 0x00cb }
            com.itextpdf.text.Image r8 = com.itextpdf.text.pdf.codec.BmpImage.getImage(r14, r11, r8)     // Catch:{ Exception -> 0x00cb }
            com.itextpdf.text.pdf.PdfContentByte r14 = r9.cb     // Catch:{ Exception -> 0x00cb }
            r14.saveState()     // Catch:{ Exception -> 0x00cb }
            com.itextpdf.text.pdf.PdfContentByte r14 = r9.cb     // Catch:{ Exception -> 0x00cb }
            r14.rectangle((float) r7, (float) r6, (float) r5, (float) r4)     // Catch:{ Exception -> 0x00cb }
            com.itextpdf.text.pdf.PdfContentByte r14 = r9.cb     // Catch:{ Exception -> 0x00cb }
            r14.clip()     // Catch:{ Exception -> 0x00cb }
            com.itextpdf.text.pdf.PdfContentByte r14 = r9.cb     // Catch:{ Exception -> 0x00cb }
            r14.newPath()     // Catch:{ Exception -> 0x00cb }
            float r14 = r8.getWidth()     // Catch:{ Exception -> 0x00cb }
            float r14 = r14 * r5
            float r1 = (float) r1     // Catch:{ Exception -> 0x00cb }
            float r14 = r14 / r1
            float r15 = -r4
            float r16 = r8.getHeight()     // Catch:{ Exception -> 0x00cb }
            float r15 = r15 * r16
            float r0 = (float) r0     // Catch:{ Exception -> 0x00cb }
            float r15 = r15 / r0
            r8.scaleAbsolute(r14, r15)     // Catch:{ Exception -> 0x00cb }
            float r3 = (float) r3     // Catch:{ Exception -> 0x00cb }
            float r5 = r5 * r3
            float r5 = r5 / r1
            float r7 = r7 - r5
            float r1 = (float) r2     // Catch:{ Exception -> 0x00cb }
            float r4 = r4 * r1
            float r4 = r4 / r0
            float r6 = r6 + r4
            float r0 = r8.getScaledHeight()     // Catch:{ Exception -> 0x00cb }
            float r6 = r6 - r0
            r8.setAbsolutePosition(r7, r6)     // Catch:{ Exception -> 0x00cb }
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb     // Catch:{ Exception -> 0x00cb }
            r0.addImage(r8)     // Catch:{ Exception -> 0x00cb }
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb     // Catch:{ Exception -> 0x00cb }
            r0.restoreState()     // Catch:{ Exception -> 0x00cb }
            goto L_0x00cb
        L_0x01a0:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r2 = r0.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r1 = r0.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readWord()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readWord()
            r4 = r3 & 6
            if (r4 == 0) goto L_0x01d5
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r6 = r9.in
            int r6 = r6.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r7 = r9.in
            int r7 = r7.readShort()
            goto L_0x01d9
        L_0x01d5:
            r4 = r10
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x01d9:
            byte[] r8 = new byte[r0]
            r14 = r10
        L_0x01dc:
            if (r14 >= r0) goto L_0x01ed
            com.itextpdf.text.pdf.codec.wmf.InputMeta r15 = r9.in
            int r15 = r15.readByte()
            byte r15 = (byte) r15
            if (r15 != 0) goto L_0x01e8
            goto L_0x01ed
        L_0x01e8:
            r8[r14] = r15
            int r14 = r14 + 1
            goto L_0x01dc
        L_0x01ed:
            java.lang.String r0 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x01f5 }
            java.lang.String r15 = "Cp1252"
            r0.<init>(r8, r10, r14, r15)     // Catch:{ UnsupportedEncodingException -> 0x01f5 }
            goto L_0x01fa
        L_0x01f5:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r8, r10, r14)
        L_0x01fa:
            r8 = r0
            r0 = r9
            r0.outputText(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x00cb
        L_0x0201:
            com.itextpdf.text.pdf.codec.wmf.MetaState r1 = r9.state
            boolean r1 = r1.getLineNeutral()
            boolean r1 = r9.isNullStrokeFill(r1)
            if (r1 == 0) goto L_0x020f
            goto L_0x00cb
        L_0x020f:
            com.itextpdf.text.pdf.codec.wmf.MetaState r1 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            float r1 = r1.transformY(r2)
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r14 = r9.in
            int r14 = r14.readShort()
            float r2 = r2.transformX(r14)
            com.itextpdf.text.pdf.codec.wmf.MetaState r14 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            float r3 = r14.transformY(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r14 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            float r4 = r14.transformX(r4)
            com.itextpdf.text.pdf.codec.wmf.MetaState r14 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readShort()
            float r0 = r14.transformY(r0)
            com.itextpdf.text.pdf.codec.wmf.MetaState r14 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r15 = r9.in
            int r15 = r15.readShort()
            float r14 = r14.transformX(r15)
            com.itextpdf.text.pdf.codec.wmf.MetaState r15 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r11 = r9.in
            int r11 = r11.readShort()
            float r11 = r15.transformY(r11)
            com.itextpdf.text.pdf.codec.wmf.MetaState r15 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r10 = r9.in
            int r10 = r10.readShort()
            float r10 = r15.transformX(r10)
            float r15 = r14 + r10
            float r15 = r15 / r16
            double r5 = (double) r15
            float r15 = r11 + r0
            float r15 = r15 / r16
            double r7 = (double) r15
            r25 = r12
            r26 = r13
            double r12 = (double) r4
            double r3 = (double) r3
            r17 = r5
            r19 = r7
            r21 = r12
            r23 = r3
            double r35 = getArc((double) r17, (double) r19, (double) r21, (double) r23)
            double r2 = (double) r2
            double r12 = (double) r1
            r21 = r2
            r23 = r12
            double r1 = getArc((double) r17, (double) r19, (double) r21, (double) r23)
            double r1 = r1 - r35
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x02a3
            r3 = 4645040803167600640(0x4076800000000000, double:360.0)
            double r1 = r1 + r3
        L_0x02a3:
            r37 = r1
            double r1 = (double) r10
            double r3 = (double) r0
            double r5 = (double) r14
            double r7 = (double) r11
            r27 = r1
            r29 = r3
            r31 = r5
            r33 = r7
            java.util.ArrayList r0 = com.itextpdf.text.pdf.PdfContentByte.bezierArc((double) r27, (double) r29, (double) r31, (double) r33, (double) r35, (double) r37)
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x02bd
            goto L_0x0514
        L_0x02bd:
            r1 = 0
            java.lang.Object r2 = r0.get(r1)
            double[] r2 = (double[]) r2
            r3 = r2[r1]
            r1 = 1
            r5 = r2[r1]
            com.itextpdf.text.pdf.PdfContentByte r1 = r9.cb
            r1.moveTo((double) r3, (double) r5)
            r1 = 0
        L_0x02cf:
            int r2 = r0.size()
            if (r1 >= r2) goto L_0x02f7
            java.lang.Object r2 = r0.get(r1)
            double[] r2 = (double[]) r2
            com.itextpdf.text.pdf.PdfContentByte r7 = r9.cb
            r8 = 2
            r28 = r2[r8]
            r8 = 3
            r30 = r2[r8]
            r8 = 4
            r32 = r2[r8]
            r8 = 5
            r34 = r2[r8]
            r8 = 6
            r36 = r2[r8]
            r8 = 7
            r38 = r2[r8]
            r27 = r7
            r27.curveTo((double) r28, (double) r30, (double) r32, (double) r34, (double) r36, (double) r38)
            int r1 = r1 + 1
            goto L_0x02cf
        L_0x02f7:
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r0.lineTo((double) r3, (double) r5)
            r40.strokeAndFill()
            goto L_0x061a
        L_0x0301:
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            boolean r0 = r0.getLineNeutral()
            boolean r0 = r9.isNullStrokeFill(r0)
            if (r0 == 0) goto L_0x0313
            goto L_0x0514
        L_0x0313:
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r1 = r9.in
            int r1 = r1.readShort()
            float r0 = r0.transformY(r1)
            com.itextpdf.text.pdf.codec.wmf.MetaState r1 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            float r1 = r1.transformX(r2)
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            float r2 = r2.transformY(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            float r3 = r3.transformX(r4)
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            float r4 = r4.transformY(r5)
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r6 = r9.in
            int r6 = r6.readShort()
            float r5 = r5.transformX(r6)
            com.itextpdf.text.pdf.codec.wmf.MetaState r6 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r7 = r9.in
            int r7 = r7.readShort()
            float r6 = r6.transformY(r7)
            com.itextpdf.text.pdf.codec.wmf.MetaState r7 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r8 = r9.in
            int r8 = r8.readShort()
            float r7 = r7.transformX(r8)
            float r8 = r5 + r7
            float r8 = r8 / r16
            float r10 = r6 + r4
            float r10 = r10 / r16
            float r2 = getArc((float) r8, (float) r10, (float) r3, (float) r2)
            double r2 = (double) r2
            float r0 = getArc((float) r8, (float) r10, (float) r1, (float) r0)
            double r0 = (double) r0
            double r0 = r0 - r2
            r11 = 0
            int r13 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r13 > 0) goto L_0x0392
            r11 = 4645040803167600640(0x4076800000000000, double:360.0)
            double r0 = r0 + r11
        L_0x0392:
            r37 = r0
            double r0 = (double) r7
            double r11 = (double) r4
            double r4 = (double) r5
            double r6 = (double) r6
            r27 = r0
            r29 = r11
            r31 = r4
            r33 = r6
            r35 = r2
            java.util.ArrayList r0 = com.itextpdf.text.pdf.PdfContentByte.bezierArc((double) r27, (double) r29, (double) r31, (double) r33, (double) r35, (double) r37)
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x03ae
            goto L_0x0514
        L_0x03ae:
            r1 = 0
            java.lang.Object r2 = r0.get(r1)
            double[] r2 = (double[]) r2
            com.itextpdf.text.pdf.PdfContentByte r3 = r9.cb
            r3.moveTo((float) r8, (float) r10)
            com.itextpdf.text.pdf.PdfContentByte r3 = r9.cb
            r4 = r2[r1]
            r1 = 1
            r6 = r2[r1]
            r3.lineTo((double) r4, (double) r6)
            r1 = 0
        L_0x03c5:
            int r2 = r0.size()
            if (r1 >= r2) goto L_0x03ed
            java.lang.Object r2 = r0.get(r1)
            double[] r2 = (double[]) r2
            com.itextpdf.text.pdf.PdfContentByte r3 = r9.cb
            r4 = 2
            r28 = r2[r4]
            r5 = 3
            r30 = r2[r5]
            r6 = 4
            r32 = r2[r6]
            r7 = 5
            r34 = r2[r7]
            r11 = 6
            r36 = r2[r11]
            r12 = 7
            r38 = r2[r12]
            r27 = r3
            r27.curveTo((double) r28, (double) r30, (double) r32, (double) r34, (double) r36, (double) r38)
            int r1 = r1 + 1
            goto L_0x03c5
        L_0x03ed:
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r0.lineTo((float) r8, (float) r10)
            r40.strokeAndFill()
            goto L_0x061a
        L_0x03f7:
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            boolean r0 = r0.getLineNeutral()
            boolean r0 = r9.isNullStrokeFill(r0)
            if (r0 == 0) goto L_0x0409
            goto L_0x0514
        L_0x0409:
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r1 = r9.in
            int r1 = r1.readShort()
            float r0 = r0.transformY(r1)
            com.itextpdf.text.pdf.codec.wmf.MetaState r1 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            float r1 = r1.transformX(r2)
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            float r2 = r2.transformY(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            float r3 = r3.transformX(r4)
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            float r19 = r4.transformY(r5)
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            float r20 = r4.transformX(r5)
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            float r21 = r4.transformY(r5)
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            float r18 = r4.transformX(r5)
            float r4 = r20 + r18
            float r4 = r4 / r16
            float r5 = r21 + r19
            float r5 = r5 / r16
            float r22 = getArc((float) r4, (float) r5, (float) r3, (float) r2)
            float r0 = getArc((float) r4, (float) r5, (float) r1, (float) r0)
            float r0 = r0 - r22
            r1 = 0
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x0483
            r1 = 1135869952(0x43b40000, float:360.0)
            float r0 = r0 + r1
        L_0x0483:
            r23 = r0
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r17 = r0
            r17.arc((float) r18, (float) r19, (float) r20, (float) r21, (float) r22, (float) r23)
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r0.stroke()
            goto L_0x061a
        L_0x0493:
            r0 = r11
            r25 = r12
            r26 = r13
            boolean r1 = r9.isNullStrokeFill(r0)
            if (r1 == 0) goto L_0x049f
            goto L_0x0514
        L_0x049f:
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            r1 = 0
            float r0 = r0.transformY(r1)
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            float r2 = r2.transformY(r3)
            float r0 = r0 - r2
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            float r2 = r2.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            float r3 = r3.transformX(r1)
            float r2 = r2 - r3
            com.itextpdf.text.pdf.codec.wmf.MetaState r1 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            float r12 = r1.transformY(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r1 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            float r1 = r1.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            float r3 = r3.transformY(r4)
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            float r11 = r4.transformX(r5)
            com.itextpdf.text.pdf.PdfContentByte r10 = r9.cb
            float r13 = r1 - r11
            float r14 = r3 - r12
            float r0 = r0 + r2
            r1 = 1082130432(0x40800000, float:4.0)
            float r15 = r0 / r1
            r10.roundRectangle((float) r11, (float) r12, (float) r13, (float) r14, (float) r15)
            r40.strokeAndFill()
            goto L_0x061a
        L_0x0509:
            r0 = r10
            r25 = r12
            r26 = r13
            boolean r1 = r9.isNullStrokeFill(r0)
            if (r1 == 0) goto L_0x0516
        L_0x0514:
            goto L_0x061a
        L_0x0516:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readWord()
            int[] r0 = new int[r0]
            r1 = 0
        L_0x051f:
            int r2 = r0.length
            if (r1 >= r2) goto L_0x052d
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readWord()
            r0[r1] = r2
            int r1 = r1 + 1
            goto L_0x051f
        L_0x052d:
            r1 = 0
        L_0x052e:
            int r2 = r0.length
            if (r1 >= r2) goto L_0x0587
            r2 = r0[r1]
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            com.itextpdf.text.pdf.PdfContentByte r5 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r6 = r9.state
            float r6 = r6.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r7 = r9.state
            float r7 = r7.transformY(r4)
            r5.moveTo((float) r6, (float) r7)
            r5 = 1
        L_0x0551:
            if (r5 >= r2) goto L_0x0573
            com.itextpdf.text.pdf.codec.wmf.InputMeta r6 = r9.in
            int r6 = r6.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r7 = r9.in
            int r7 = r7.readShort()
            com.itextpdf.text.pdf.PdfContentByte r8 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r10 = r9.state
            float r6 = r10.transformX(r6)
            com.itextpdf.text.pdf.codec.wmf.MetaState r10 = r9.state
            float r7 = r10.transformY(r7)
            r8.lineTo((float) r6, (float) r7)
            int r5 = r5 + 1
            goto L_0x0551
        L_0x0573:
            com.itextpdf.text.pdf.PdfContentByte r2 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            float r3 = r5.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            float r4 = r5.transformY(r4)
            r2.lineTo((float) r3, (float) r4)
            int r1 = r1 + 1
            goto L_0x052e
        L_0x0587:
            r40.strokeAndFill()
            goto L_0x061a
        L_0x058c:
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readWord()
            byte[] r1 = new byte[r0]
            r2 = 0
        L_0x0599:
            if (r2 >= r0) goto L_0x05aa
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readByte()
            byte r3 = (byte) r3
            if (r3 != 0) goto L_0x05a5
            goto L_0x05aa
        L_0x05a5:
            r1[r2] = r3
            int r2 = r2 + 1
            goto L_0x0599
        L_0x05aa:
            java.lang.String r3 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x05b3 }
            java.lang.String r4 = "Cp1252"
            r5 = 0
            r3.<init>(r1, r5, r2, r4)     // Catch:{ UnsupportedEncodingException -> 0x05b4 }
            goto L_0x05b9
        L_0x05b3:
            r5 = 0
        L_0x05b4:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r1, r5, r2)
        L_0x05b9:
            r8 = r3
            int r0 = r0 + 1
            r1 = 65534(0xfffe, float:9.1833E-41)
            r0 = r0 & r1
            com.itextpdf.text.pdf.codec.wmf.InputMeta r1 = r9.in
            int r0 = r0 - r2
            r1.skip(r0)
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r2 = r0.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r1 = r0.readShort()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r9
            r0.outputText(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x061a
        L_0x05dc:
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            com.itextpdf.text.BaseColor r0 = r0.readColor()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r1 = r9.in
            int r1 = r1.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            com.itextpdf.text.pdf.PdfContentByte r3 = r9.cb
            r3.saveState()
            com.itextpdf.text.pdf.PdfContentByte r3 = r9.cb
            r3.setColorFill(r0)
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            float r2 = r3.transformX(r2)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            float r1 = r3.transformY(r1)
            r3 = 1045220557(0x3e4ccccd, float:0.2)
            r0.rectangle((float) r2, (float) r1, (float) r3, (float) r3)
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r0.fill()
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r0.restoreState()
        L_0x061a:
            r0 = 0
            r1 = 1
            goto L_0x0964
        L_0x061e:
            r1 = r11
            r25 = r12
            r26 = r13
            boolean r0 = r9.isNullStrokeFill(r1)
            if (r0 == 0) goto L_0x062a
            goto L_0x0677
        L_0x062a:
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            float r0 = r0.transformY(r2)
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            float r2 = r2.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            float r3 = r3.transformY(r4)
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            float r4 = r4.transformX(r5)
            com.itextpdf.text.pdf.PdfContentByte r5 = r9.cb
            float r2 = r2 - r4
            float r3 = r3 - r0
            r5.rectangle((float) r4, (float) r0, (float) r2, (float) r3)
            r40.strokeAndFill()
            goto L_0x06fb
        L_0x0666:
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            boolean r0 = r0.getLineNeutral()
            boolean r0 = r9.isNullStrokeFill(r0)
            if (r0 == 0) goto L_0x0679
        L_0x0677:
            goto L_0x06fb
        L_0x0679:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            com.itextpdf.text.pdf.PdfContentByte r10 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            float r11 = r5.transformX(r4)
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            float r12 = r4.transformY(r0)
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            float r13 = r0.transformX(r2)
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            float r14 = r0.transformY(r3)
            r15 = 0
            r16 = 1135869952(0x43b40000, float:360.0)
            r10.arc((float) r11, (float) r12, (float) r13, (float) r14, (float) r15, (float) r16)
            r40.strokeAndFill()
            goto L_0x06fb
        L_0x06b5:
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            float r0 = r0.transformY(r2)
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            float r2 = r2.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            float r3 = r3.transformY(r4)
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r5 = r9.in
            int r5 = r5.readShort()
            float r4 = r4.transformX(r5)
            com.itextpdf.text.pdf.PdfContentByte r5 = r9.cb
            float r2 = r2 - r4
            float r3 = r3 - r0
            r5.rectangle((float) r4, (float) r0, (float) r2, (float) r3)
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r0.eoClip()
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r0.newPath()
        L_0x06fb:
            r0 = 0
            goto L_0x0964
        L_0x06fe:
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r0 = r9.state
            com.itextpdf.text.pdf.PdfContentByte r2 = r9.cb
            r0.setLineJoinPolygon(r2)
            com.itextpdf.text.pdf.codec.wmf.InputMeta r0 = r9.in
            int r0 = r0.readWord()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            com.itextpdf.text.pdf.PdfContentByte r4 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            float r2 = r5.transformX(r2)
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            float r3 = r5.transformY(r3)
            r4.moveTo((float) r2, (float) r3)
            r2 = r1
        L_0x072e:
            if (r2 >= r0) goto L_0x0750
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            com.itextpdf.text.pdf.PdfContentByte r5 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r6 = r9.state
            float r3 = r6.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r6 = r9.state
            float r4 = r6.transformY(r4)
            r5.lineTo((float) r3, (float) r4)
            int r2 = r2 + 1
            goto L_0x072e
        L_0x0750:
            com.itextpdf.text.pdf.PdfContentByte r0 = r9.cb
            r0.stroke()
            goto L_0x06fb
        L_0x0756:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            boolean r2 = r9.isNullStrokeFill(r0)
            if (r2 == 0) goto L_0x0764
            goto L_0x0964
        L_0x0764:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readWord()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            com.itextpdf.text.pdf.PdfContentByte r5 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r6 = r9.state
            float r6 = r6.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r7 = r9.state
            float r7 = r7.transformY(r4)
            r5.moveTo((float) r6, (float) r7)
            r5 = r1
        L_0x0788:
            if (r5 >= r2) goto L_0x07aa
            com.itextpdf.text.pdf.codec.wmf.InputMeta r6 = r9.in
            int r6 = r6.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r7 = r9.in
            int r7 = r7.readShort()
            com.itextpdf.text.pdf.PdfContentByte r8 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r10 = r9.state
            float r6 = r10.transformX(r6)
            com.itextpdf.text.pdf.codec.wmf.MetaState r10 = r9.state
            float r7 = r10.transformY(r7)
            r8.lineTo((float) r6, (float) r7)
            int r5 = r5 + 1
            goto L_0x0788
        L_0x07aa:
            com.itextpdf.text.pdf.PdfContentByte r2 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            float r3 = r5.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            float r4 = r5.transformY(r4)
            r2.lineTo((float) r3, (float) r4)
            r40.strokeAndFill()
            goto L_0x0964
        L_0x07c0:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaBrush r2 = new com.itextpdf.text.pdf.codec.wmf.MetaBrush
            r2.<init>()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            r2.init(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            r3.addMetaObject(r2)
            goto L_0x0964
        L_0x07d7:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaFont r2 = new com.itextpdf.text.pdf.codec.wmf.MetaFont
            r2.<init>()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            r2.init(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            r3.addMetaObject(r2)
            goto L_0x0964
        L_0x07ee:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaPen r2 = new com.itextpdf.text.pdf.codec.wmf.MetaPen
            r2.<init>()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            r2.init(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            r3.addMetaObject(r2)
            goto L_0x0964
        L_0x0805:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            com.itextpdf.text.pdf.codec.wmf.Point r3 = new com.itextpdf.text.pdf.codec.wmf.Point
            com.itextpdf.text.pdf.codec.wmf.InputMeta r4 = r9.in
            int r4 = r4.readShort()
            r3.<init>(r4, r2)
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            r2.setCurrentPoint(r3)
            goto L_0x0964
        L_0x0823:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.Point r4 = r4.getCurrentPoint()
            com.itextpdf.text.pdf.PdfContentByte r5 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r6 = r9.state
            int r7 = r4.x
            float r6 = r6.transformX(r7)
            com.itextpdf.text.pdf.codec.wmf.MetaState r7 = r9.state
            int r4 = r4.y
            float r4 = r7.transformY(r4)
            r5.moveTo((float) r6, (float) r4)
            com.itextpdf.text.pdf.PdfContentByte r4 = r9.cb
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r9.state
            float r5 = r5.transformX(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r6 = r9.state
            float r6 = r6.transformY(r2)
            r4.lineTo((float) r5, (float) r6)
            com.itextpdf.text.pdf.PdfContentByte r4 = r9.cb
            r4.stroke()
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r9.state
            com.itextpdf.text.pdf.codec.wmf.Point r5 = new com.itextpdf.text.pdf.codec.wmf.Point
            r5.<init>(r3, r2)
            r4.setCurrentPoint(r5)
            goto L_0x0964
        L_0x0872:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            r2.setExtentWy(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            r2.setExtentWx(r3)
            goto L_0x0964
        L_0x0890:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            r2.setOffsetWy(r3)
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readShort()
            r2.setOffsetWx(r3)
            goto L_0x0964
        L_0x08ae:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            com.itextpdf.text.BaseColor r3 = r3.readColor()
            r2.setCurrentTextColor(r3)
            goto L_0x0964
        L_0x08c1:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            com.itextpdf.text.BaseColor r3 = r3.readColor()
            r2.setCurrentBackgroundColor(r3)
            goto L_0x0964
        L_0x08d4:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readWord()
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            r3.deleteMetaObject(r2)
            goto L_0x0964
        L_0x08e7:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readWord()
            r2.setTextAlign(r3)
            goto L_0x0964
        L_0x08fa:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readWord()
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            com.itextpdf.text.pdf.PdfContentByte r4 = r9.cb
            r3.selectMetaObject(r2, r4)
            goto L_0x0964
        L_0x090e:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r2 = r2.readShort()
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r9.state
            com.itextpdf.text.pdf.PdfContentByte r4 = r9.cb
            r3.restoreState(r2, r4)
            goto L_0x0964
        L_0x0922:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readWord()
            r2.setPolyFillMode(r3)
            goto L_0x0964
        L_0x0934:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.readWord()
            r2.setBackgroundMode(r3)
            goto L_0x0964
        L_0x0946:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.codec.wmf.MetaObject r3 = new com.itextpdf.text.pdf.codec.wmf.MetaObject
            r3.<init>()
            r2.addMetaObject(r3)
            goto L_0x0964
        L_0x0957:
            r0 = r10
            r1 = r11
            r25 = r12
            r26 = r13
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r9.state
            com.itextpdf.text.pdf.PdfContentByte r3 = r9.cb
            r2.saveState(r3)
        L_0x0964:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = r9.in
            int r13 = r26 * 2
            com.itextpdf.text.pdf.codec.wmf.InputMeta r3 = r9.in
            int r3 = r3.getLength()
            int r3 = r3 - r25
            int r13 = r13 - r3
            r2.skip(r13)
            r10 = r0
            r11 = r1
            goto L_0x00a9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.wmf.MetaDo.readAll():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void outputText(int r19, int r20, int r21, int r22, int r23, int r24, int r25, java.lang.String r26) {
        /*
            r18 = this;
            r0 = r18
            r1 = r26
            com.itextpdf.text.pdf.codec.wmf.MetaState r2 = r0.state
            com.itextpdf.text.pdf.codec.wmf.MetaFont r2 = r2.getCurrentFont()
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r0.state
            r4 = r19
            float r9 = r3.transformX(r4)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r0.state
            r4 = r20
            float r10 = r3.transformY(r4)
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r0.state
            float r4 = r2.getAngle()
            float r3 = r3.transformAngle(r4)
            double r3 = (double) r3
            double r5 = java.lang.Math.sin(r3)
            float r6 = (float) r5
            double r3 = java.lang.Math.cos(r3)
            float r8 = (float) r3
            com.itextpdf.text.pdf.codec.wmf.MetaState r3 = r0.state
            float r3 = r2.getFontSize(r3)
            com.itextpdf.text.pdf.BaseFont r11 = r2.getFont()
            com.itextpdf.text.pdf.codec.wmf.MetaState r4 = r0.state
            int r12 = r4.getTextAlign()
            float r13 = r11.getWidthPoint((java.lang.String) r1, (float) r3)
            r4 = 3
            float r14 = r11.getFontDescriptor(r4, r3)
            r15 = 8
            float r7 = r11.getFontDescriptor(r15, r3)
            com.itextpdf.text.pdf.PdfContentByte r4 = r0.cb
            r4.saveState()
            com.itextpdf.text.pdf.PdfContentByte r4 = r0.cb
            float r5 = -r6
            r16 = r5
            r5 = r8
            r17 = r7
            r7 = r16
            r4.concatCTM((float) r5, (float) r6, (float) r7, (float) r8, (float) r9, (float) r10)
            r4 = r12 & 6
            r5 = 6
            r6 = 0
            r7 = 2
            if (r4 != r5) goto L_0x006c
            float r4 = -r13
            r5 = 1073741824(0x40000000, float:2.0)
            float r4 = r4 / r5
            goto L_0x0073
        L_0x006c:
            r4 = r12 & 2
            if (r4 != r7) goto L_0x0072
            float r4 = -r13
            goto L_0x0073
        L_0x0072:
            r4 = r6
        L_0x0073:
            r5 = r12 & 24
            r8 = 24
            if (r5 != r8) goto L_0x007c
        L_0x0079:
            r5 = r17
            goto L_0x0085
        L_0x007c:
            r5 = r12 & 8
            if (r5 != r15) goto L_0x0082
            float r6 = -r14
            goto L_0x0079
        L_0x0082:
            r5 = r17
            float r6 = -r5
        L_0x0085:
            com.itextpdf.text.pdf.codec.wmf.MetaState r8 = r0.state
            int r8 = r8.getBackgroundMode()
            if (r8 != r7) goto L_0x00a5
            com.itextpdf.text.pdf.codec.wmf.MetaState r7 = r0.state
            com.itextpdf.text.BaseColor r7 = r7.getCurrentBackgroundColor()
            com.itextpdf.text.pdf.PdfContentByte r8 = r0.cb
            r8.setColorFill(r7)
            com.itextpdf.text.pdf.PdfContentByte r7 = r0.cb
            float r8 = r6 + r14
            float r5 = r5 - r14
            r7.rectangle((float) r4, (float) r8, (float) r13, (float) r5)
            com.itextpdf.text.pdf.PdfContentByte r5 = r0.cb
            r5.fill()
        L_0x00a5:
            com.itextpdf.text.pdf.codec.wmf.MetaState r5 = r0.state
            com.itextpdf.text.BaseColor r5 = r5.getCurrentTextColor()
            com.itextpdf.text.pdf.PdfContentByte r7 = r0.cb
            r7.setColorFill(r5)
            com.itextpdf.text.pdf.PdfContentByte r5 = r0.cb
            r5.beginText()
            com.itextpdf.text.pdf.PdfContentByte r5 = r0.cb
            r5.setFontAndSize(r11, r3)
            com.itextpdf.text.pdf.PdfContentByte r5 = r0.cb
            r5.setTextMatrix(r4, r6)
            com.itextpdf.text.pdf.PdfContentByte r5 = r0.cb
            r5.showText((java.lang.String) r1)
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.cb
            r1.endText()
            boolean r1 = r2.isUnderline()
            r5 = 1097859072(0x41700000, float:15.0)
            if (r1 == 0) goto L_0x00e3
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.cb
            r7 = 1082130432(0x40800000, float:4.0)
            float r7 = r3 / r7
            float r7 = r6 - r7
            float r8 = r3 / r5
            r1.rectangle((float) r4, (float) r7, (float) r13, (float) r8)
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.cb
            r1.fill()
        L_0x00e3:
            boolean r1 = r2.isStrikeout()
            if (r1 == 0) goto L_0x00f9
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.cb
            r2 = 1077936128(0x40400000, float:3.0)
            float r2 = r3 / r2
            float r6 = r6 + r2
            float r3 = r3 / r5
            r1.rectangle((float) r4, (float) r6, (float) r13, (float) r3)
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.cb
            r1.fill()
        L_0x00f9:
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.cb
            r1.restoreState()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.wmf.MetaDo.outputText(int, int, int, int, int, int, int, java.lang.String):void");
    }

    public boolean isNullStrokeFill(boolean z) {
        MetaPen currentPen = this.state.getCurrentPen();
        MetaBrush currentBrush = this.state.getCurrentBrush();
        boolean z2 = false;
        boolean z3 = currentPen.getStyle() == 5;
        int style = currentBrush.getStyle();
        boolean z4 = style == 0 || (style == 2 && this.state.getBackgroundMode() == 2);
        if (z3 && !z4) {
            z2 = true;
        }
        if (!z3) {
            if (z) {
                this.state.setLineJoinRectangle(this.cb);
            } else {
                this.state.setLineJoinPolygon(this.cb);
            }
        }
        return z2;
    }

    public void strokeAndFill() {
        MetaPen currentPen = this.state.getCurrentPen();
        MetaBrush currentBrush = this.state.getCurrentBrush();
        int style = currentPen.getStyle();
        int style2 = currentBrush.getStyle();
        if (style == 5) {
            this.cb.closePath();
            if (this.state.getPolyFillMode() == 1) {
                this.cb.eoFill();
            } else {
                this.cb.fill();
            }
        } else {
            if (!(style2 == 0 || (style2 == 2 && this.state.getBackgroundMode() == 2))) {
                this.cb.closePathStroke();
            } else if (this.state.getPolyFillMode() == 1) {
                this.cb.closePathEoFillStroke();
            } else {
                this.cb.closePathFillStroke();
            }
        }
    }

    static float getArc(float f, float f2, float f3, float f4) {
        return (float) getArc((double) f, (double) f2, (double) f3, (double) f4);
    }

    static double getArc(double d, double d2, double d3, double d4) {
        double atan2 = Math.atan2(d4 - d2, d3 - d);
        if (atan2 < 0.0d) {
            atan2 += 6.283185307179586d;
        }
        return (double) ((float) ((atan2 / 3.141592653589793d) * 180.0d));
    }

    public static byte[] wrapBMP(Image image) throws IOException {
        byte[] bArr;
        if (image.getOriginalType() != 4) {
            throw new IOException(MessageLocalization.getComposedMessage("only.bmp.can.be.wrapped.in.wmf", new Object[0]));
        }
        if (image.getOriginalData() == null) {
            InputStream openStream = image.getUrl().openStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = openStream.read();
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(read);
            }
            openStream.close();
            bArr = byteArrayOutputStream.toByteArray();
        } else {
            bArr = image.getOriginalData();
        }
        int length = ((bArr.length - 14) + 1) >>> 1;
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        writeWord(byteArrayOutputStream2, 1);
        writeWord(byteArrayOutputStream2, 9);
        writeWord(byteArrayOutputStream2, 768);
        writeDWord(byteArrayOutputStream2, 36 + length + 3);
        writeWord(byteArrayOutputStream2, 1);
        writeDWord(byteArrayOutputStream2, 14 + length);
        writeWord(byteArrayOutputStream2, 0);
        writeDWord(byteArrayOutputStream2, 4);
        writeWord(byteArrayOutputStream2, 259);
        writeWord(byteArrayOutputStream2, 8);
        writeDWord(byteArrayOutputStream2, 5);
        writeWord(byteArrayOutputStream2, META_SETWINDOWORG);
        writeWord(byteArrayOutputStream2, 0);
        writeWord(byteArrayOutputStream2, 0);
        writeDWord(byteArrayOutputStream2, 5);
        writeWord(byteArrayOutputStream2, META_SETWINDOWEXT);
        writeWord(byteArrayOutputStream2, (int) image.getHeight());
        writeWord(byteArrayOutputStream2, (int) image.getWidth());
        writeDWord(byteArrayOutputStream2, 13 + length);
        writeWord(byteArrayOutputStream2, META_DIBSTRETCHBLT);
        writeDWord(byteArrayOutputStream2, 13369376);
        writeWord(byteArrayOutputStream2, (int) image.getHeight());
        writeWord(byteArrayOutputStream2, (int) image.getWidth());
        writeWord(byteArrayOutputStream2, 0);
        writeWord(byteArrayOutputStream2, 0);
        writeWord(byteArrayOutputStream2, (int) image.getHeight());
        writeWord(byteArrayOutputStream2, (int) image.getWidth());
        writeWord(byteArrayOutputStream2, 0);
        writeWord(byteArrayOutputStream2, 0);
        byteArrayOutputStream2.write(bArr, 14, bArr.length - 14);
        if ((bArr.length & 1) == 1) {
            byteArrayOutputStream2.write(0);
        }
        writeDWord(byteArrayOutputStream2, 3);
        writeWord(byteArrayOutputStream2, 0);
        byteArrayOutputStream2.close();
        return byteArrayOutputStream2.toByteArray();
    }

    public static void writeWord(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i & 255);
        outputStream.write((i >>> 8) & 255);
    }

    public static void writeDWord(OutputStream outputStream, int i) throws IOException {
        writeWord(outputStream, i & 65535);
        writeWord(outputStream, (i >>> 16) & 65535);
    }
}
