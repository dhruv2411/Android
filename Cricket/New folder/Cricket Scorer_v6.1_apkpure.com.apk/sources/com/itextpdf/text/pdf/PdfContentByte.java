package com.itextpdf.text.pdf;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.itextpdf.awt.geom.AffineTransform;
import com.itextpdf.text.AccessibleElementId;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.exceptions.IllegalPdfSyntaxException;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PdfContentByte {
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 2;
    public static final int LINE_CAP_BUTT = 0;
    public static final int LINE_CAP_PROJECTING_SQUARE = 2;
    public static final int LINE_CAP_ROUND = 1;
    public static final int LINE_JOIN_BEVEL = 2;
    public static final int LINE_JOIN_MITER = 0;
    public static final int LINE_JOIN_ROUND = 1;
    public static final int TEXT_RENDER_MODE_CLIP = 7;
    public static final int TEXT_RENDER_MODE_FILL = 0;
    public static final int TEXT_RENDER_MODE_FILL_CLIP = 4;
    public static final int TEXT_RENDER_MODE_FILL_STROKE = 2;
    public static final int TEXT_RENDER_MODE_FILL_STROKE_CLIP = 6;
    public static final int TEXT_RENDER_MODE_INVISIBLE = 3;
    public static final int TEXT_RENDER_MODE_STROKE = 1;
    public static final int TEXT_RENDER_MODE_STROKE_CLIP = 5;
    private static HashMap<PdfName, String> abrev = new HashMap<>();
    private static final float[] unitRect = {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
    protected ByteBuffer content = new ByteBuffer();
    protected PdfContentByte duplicatedFrom = null;
    private boolean inText = false;
    protected ArrayList<Integer> layerDepth;
    protected int markedContentSize = 0;
    private int mcDepth = 0;
    private ArrayList<IAccessibleElement> mcElements = new ArrayList<>();
    protected PdfDocument pdf;
    protected int separator = 10;
    protected GraphicState state = new GraphicState();
    protected ArrayList<GraphicState> stateList = new ArrayList<>();
    protected PdfWriter writer;

    public static class GraphicState {
        protected AffineTransform CTM = new AffineTransform();
        protected float aTLM = 1.0f;
        protected float bTLM = 0.0f;
        protected float cTLM = 0.0f;
        protected float charSpace = 0.0f;
        ColorDetails colorDetails;
        protected BaseColor colorFill = new GrayColor(0);
        protected BaseColor colorStroke = new GrayColor(0);
        protected float dTLM = 1.0f;
        protected PdfObject extGState = null;
        FontDetails fontDetails;
        protected float leading = 0.0f;
        protected float scale = 100.0f;
        float size;
        protected int textRenderMode = 0;
        protected float tx = 0.0f;
        protected float wordSpace = 0.0f;
        protected float xTLM = 0.0f;
        protected float yTLM = 0.0f;

        GraphicState() {
        }

        GraphicState(GraphicState graphicState) {
            copyParameters(graphicState);
        }

        /* access modifiers changed from: package-private */
        public void copyParameters(GraphicState graphicState) {
            this.fontDetails = graphicState.fontDetails;
            this.colorDetails = graphicState.colorDetails;
            this.size = graphicState.size;
            this.xTLM = graphicState.xTLM;
            this.yTLM = graphicState.yTLM;
            this.aTLM = graphicState.aTLM;
            this.bTLM = graphicState.bTLM;
            this.cTLM = graphicState.cTLM;
            this.dTLM = graphicState.dTLM;
            this.tx = graphicState.tx;
            this.leading = graphicState.leading;
            this.scale = graphicState.scale;
            this.charSpace = graphicState.charSpace;
            this.wordSpace = graphicState.wordSpace;
            this.colorFill = graphicState.colorFill;
            this.colorStroke = graphicState.colorStroke;
            this.CTM = new AffineTransform(graphicState.CTM);
            this.textRenderMode = graphicState.textRenderMode;
            this.extGState = graphicState.extGState;
        }

        /* access modifiers changed from: package-private */
        public void restore(GraphicState graphicState) {
            copyParameters(graphicState);
        }
    }

    static {
        abrev.put(PdfName.BITSPERCOMPONENT, "/BPC ");
        abrev.put(PdfName.COLORSPACE, "/CS ");
        abrev.put(PdfName.DECODE, "/D ");
        abrev.put(PdfName.DECODEPARMS, "/DP ");
        abrev.put(PdfName.FILTER, "/F ");
        abrev.put(PdfName.HEIGHT, "/H ");
        abrev.put(PdfName.IMAGEMASK, "/IM ");
        abrev.put(PdfName.INTENT, "/Intent ");
        abrev.put(PdfName.INTERPOLATE, "/I ");
        abrev.put(PdfName.WIDTH, "/W ");
    }

    public PdfContentByte(PdfWriter pdfWriter) {
        if (pdfWriter != null) {
            this.writer = pdfWriter;
            this.pdf = this.writer.getPdfDocument();
        }
    }

    public String toString() {
        return this.content.toString();
    }

    public boolean isTagged() {
        return this.writer != null && this.writer.isTagged();
    }

    public ByteBuffer getInternalBuffer() {
        return this.content;
    }

    public byte[] toPdf(PdfWriter pdfWriter) {
        sanityCheck();
        return this.content.toByteArray();
    }

    public void add(PdfContentByte pdfContentByte) {
        if (pdfContentByte.writer == null || this.writer == pdfContentByte.writer) {
            this.content.append(pdfContentByte.content);
            this.markedContentSize += pdfContentByte.markedContentSize;
            return;
        }
        throw new RuntimeException(MessageLocalization.getComposedMessage("inconsistent.writers.are.you.mixing.two.documents", new Object[0]));
    }

    public float getXTLM() {
        return this.state.xTLM;
    }

    public float getYTLM() {
        return this.state.yTLM;
    }

    public float getLeading() {
        return this.state.leading;
    }

    public float getCharacterSpacing() {
        return this.state.charSpace;
    }

    public float getWordSpacing() {
        return this.state.wordSpace;
    }

    public float getHorizontalScaling() {
        return this.state.scale;
    }

    public void setFlatness(float f) {
        setFlatness((double) f);
    }

    public void setFlatness(double d) {
        if (d >= 0.0d && d <= 100.0d) {
            this.content.append(d).append(" i").append_i(this.separator);
        }
    }

    public void setLineCap(int i) {
        if (i >= 0 && i <= 2) {
            this.content.append(i).append(" J").append_i(this.separator);
        }
    }

    public void setRenderingIntent(PdfName pdfName) {
        this.content.append(pdfName.getBytes()).append(" ri").append_i(this.separator);
    }

    public void setLineDash(float f) {
        setLineDash((double) f);
    }

    public void setLineDash(double d) {
        this.content.append("[] ").append(d).append(" d").append_i(this.separator);
    }

    public void setLineDash(float f, float f2) {
        setLineDash((double) f, (double) f2);
    }

    public void setLineDash(double d, double d2) {
        this.content.append("[").append(d).append("] ").append(d2).append(" d").append_i(this.separator);
    }

    public void setLineDash(float f, float f2, float f3) {
        setLineDash((double) f, (double) f2, (double) f3);
    }

    public void setLineDash(double d, double d2, double d3) {
        this.content.append("[").append(d).append(' ').append(d2).append("] ").append(d3).append(" d").append_i(this.separator);
    }

    public final void setLineDash(float[] fArr, float f) {
        this.content.append("[");
        for (int i = 0; i < fArr.length; i++) {
            this.content.append(fArr[i]);
            if (i < fArr.length - 1) {
                this.content.append(' ');
            }
        }
        this.content.append("] ").append(f).append(" d").append_i(this.separator);
    }

    public final void setLineDash(double[] dArr, double d) {
        this.content.append("[");
        for (int i = 0; i < dArr.length; i++) {
            this.content.append(dArr[i]);
            if (i < dArr.length - 1) {
                this.content.append(' ');
            }
        }
        this.content.append("] ").append(d).append(" d").append_i(this.separator);
    }

    public void setLineJoin(int i) {
        if (i >= 0 && i <= 2) {
            this.content.append(i).append(" j").append_i(this.separator);
        }
    }

    public void setLineWidth(float f) {
        setLineWidth((double) f);
    }

    public void setLineWidth(double d) {
        this.content.append(d).append(" w").append_i(this.separator);
    }

    public void setMiterLimit(float f) {
        setMiterLimit((double) f);
    }

    public void setMiterLimit(double d) {
        if (d > 1.0d) {
            this.content.append(d).append(" M").append_i(this.separator);
        }
    }

    public void clip() {
        if (this.inText && isTagged()) {
            endText();
        }
        this.content.append("W").append_i(this.separator);
    }

    public void eoClip() {
        if (this.inText && isTagged()) {
            endText();
        }
        this.content.append("W*").append_i(this.separator);
    }

    public void setGrayFill(float f) {
        saveColor(new GrayColor(f), true);
        this.content.append(f).append(" g").append_i(this.separator);
    }

    public void resetGrayFill() {
        saveColor(new GrayColor(0), true);
        this.content.append("0 g").append_i(this.separator);
    }

    public void setGrayStroke(float f) {
        saveColor(new GrayColor(f), false);
        this.content.append(f).append(" G").append_i(this.separator);
    }

    public void resetGrayStroke() {
        saveColor(new GrayColor(0), false);
        this.content.append("0 G").append_i(this.separator);
    }

    private void HelperRGB(float f, float f2, float f3) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (f3 < 0.0f) {
            f3 = 0.0f;
        } else if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        this.content.append(f).append(' ').append(f2).append(' ').append(f3);
    }

    public void setRGBColorFillF(float f, float f2, float f3) {
        saveColor(new BaseColor(f, f2, f3), true);
        HelperRGB(f, f2, f3);
        this.content.append(" rg").append_i(this.separator);
    }

    public void resetRGBColorFill() {
        resetGrayFill();
    }

    public void setRGBColorStrokeF(float f, float f2, float f3) {
        saveColor(new BaseColor(f, f2, f3), false);
        HelperRGB(f, f2, f3);
        this.content.append(" RG").append_i(this.separator);
    }

    public void resetRGBColorStroke() {
        resetGrayStroke();
    }

    private void HelperCMYK(float f, float f2, float f3, float f4) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (f3 < 0.0f) {
            f3 = 0.0f;
        } else if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        if (f4 < 0.0f) {
            f4 = 0.0f;
        } else if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        this.content.append(f).append(' ').append(f2).append(' ').append(f3).append(' ').append(f4);
    }

    public void setCMYKColorFillF(float f, float f2, float f3, float f4) {
        saveColor(new CMYKColor(f, f2, f3, f4), true);
        HelperCMYK(f, f2, f3, f4);
        this.content.append(" k").append_i(this.separator);
    }

    public void resetCMYKColorFill() {
        saveColor(new CMYKColor(0, 0, 0, 1), true);
        this.content.append("0 0 0 1 k").append_i(this.separator);
    }

    public void setCMYKColorStrokeF(float f, float f2, float f3, float f4) {
        saveColor(new CMYKColor(f, f2, f3, f4), false);
        HelperCMYK(f, f2, f3, f4);
        this.content.append(" K").append_i(this.separator);
    }

    public void resetCMYKColorStroke() {
        saveColor(new CMYKColor(0, 0, 0, 1), false);
        this.content.append("0 0 0 1 K").append_i(this.separator);
    }

    public void moveTo(float f, float f2) {
        moveTo((double) f, (double) f2);
    }

    public void moveTo(double d, double d2) {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        this.content.append(d).append(' ').append(d2).append(" m").append_i(this.separator);
    }

    public void lineTo(float f, float f2) {
        lineTo((double) f, (double) f2);
    }

    public void lineTo(double d, double d2) {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        this.content.append(d).append(' ').append(d2).append(" l").append_i(this.separator);
    }

    public void curveTo(float f, float f2, float f3, float f4, float f5, float f6) {
        curveTo((double) f, (double) f2, (double) f3, (double) f4, (double) f5, (double) f6);
    }

    public void curveTo(double d, double d2, double d3, double d4, double d5, double d6) {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        this.content.append(d).append(' ').append(d2).append(' ').append(d3).append(' ').append(d4).append(' ').append(d5).append(' ').append(d6).append(" c").append_i(this.separator);
    }

    public void curveTo(float f, float f2, float f3, float f4) {
        curveTo((double) f, (double) f2, (double) f3, (double) f4);
    }

    public void curveTo(double d, double d2, double d3, double d4) {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        this.content.append(d).append(' ').append(d2).append(' ').append(d3).append(' ').append(d4).append(" v").append_i(this.separator);
    }

    public void curveFromTo(float f, float f2, float f3, float f4) {
        curveFromTo((double) f, (double) f2, (double) f3, (double) f4);
    }

    public void curveFromTo(double d, double d2, double d3, double d4) {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        this.content.append(d).append(' ').append(d2).append(' ').append(d3).append(' ').append(d4).append(" y").append_i(this.separator);
    }

    public void circle(float f, float f2, float f3) {
        circle((double) f, (double) f2, (double) f3);
    }

    public void circle(double d, double d2, double d3) {
        double d4 = d2;
        double d5 = d + d3;
        moveTo(d5, d4);
        double d6 = d3 * ((double) 0.5523f);
        double d7 = d4 + d6;
        double d8 = d + d6;
        double d9 = d4 + d3;
        double d10 = d5;
        double d11 = d4;
        curveTo(d5, d7, d8, d9, d, d9);
        double d12 = d - d6;
        double d13 = d - d3;
        curveTo(d12, d9, d13, d7, d13, d11);
        double d14 = d11 - d6;
        double d15 = d11 - d3;
        curveTo(d13, d14, d12, d15, d, d15);
        curveTo(d8, d15, d10, d14, d10, d11);
    }

    public void rectangle(float f, float f2, float f3, float f4) {
        rectangle((double) f, (double) f2, (double) f3, (double) f4);
    }

    public void rectangle(double d, double d2, double d3, double d4) {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        this.content.append(d).append(' ').append(d2).append(' ').append(d3).append(' ').append(d4).append(" re").append_i(this.separator);
    }

    private boolean compareColors(BaseColor baseColor, BaseColor baseColor2) {
        if (baseColor == null && baseColor2 == null) {
            return true;
        }
        if (baseColor == null || baseColor2 == null) {
            return false;
        }
        if (baseColor instanceof ExtendedColor) {
            return baseColor.equals(baseColor2);
        }
        return baseColor2.equals(baseColor);
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x0128  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void variableRectangle(com.itextpdf.text.Rectangle r26) {
        /*
            r25 = this;
            r0 = r25
            float r1 = r26.getTop()
            float r2 = r26.getBottom()
            float r3 = r26.getRight()
            float r4 = r26.getLeft()
            float r5 = r26.getBorderWidthTop()
            float r6 = r26.getBorderWidthBottom()
            float r7 = r26.getBorderWidthRight()
            float r8 = r26.getBorderWidthLeft()
            com.itextpdf.text.BaseColor r9 = r26.getBorderColorTop()
            com.itextpdf.text.BaseColor r10 = r26.getBorderColorBottom()
            com.itextpdf.text.BaseColor r11 = r26.getBorderColorRight()
            com.itextpdf.text.BaseColor r12 = r26.getBorderColorLeft()
            r25.saveState()
            r13 = 0
            r0.setLineCap(r13)
            r0.setLineJoin(r13)
            r14 = 0
            int r15 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            r16 = 0
            r17 = 1073741824(0x40000000, float:2.0)
            r18 = 1
            if (r15 <= 0) goto L_0x0065
            r0.setLineWidth((float) r5)
            if (r9 != 0) goto L_0x0050
            r25.resetRGBColorStroke()
            goto L_0x0053
        L_0x0050:
            r0.setColorStroke(r9)
        L_0x0053:
            float r15 = r5 / r17
            float r15 = r1 - r15
            r0.moveTo((float) r4, (float) r15)
            r0.lineTo((float) r3, (float) r15)
            r25.stroke()
            r15 = r5
            r13 = r9
            r19 = r18
            goto L_0x006a
        L_0x0065:
            r19 = r13
            r15 = r14
            r13 = r16
        L_0x006a:
            int r21 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r21 <= 0) goto L_0x0098
            int r21 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r21 == 0) goto L_0x0076
            r0.setLineWidth((float) r6)
            r15 = r6
        L_0x0076:
            if (r19 == 0) goto L_0x007e
            boolean r21 = r0.compareColors(r13, r10)
            if (r21 != 0) goto L_0x008a
        L_0x007e:
            if (r10 != 0) goto L_0x0084
            r25.resetRGBColorStroke()
            goto L_0x0087
        L_0x0084:
            r0.setColorStroke(r10)
        L_0x0087:
            r13 = r10
            r19 = r18
        L_0x008a:
            float r21 = r6 / r17
            float r14 = r2 + r21
            r0.moveTo((float) r3, (float) r14)
            r0.lineTo((float) r4, (float) r14)
            r25.stroke()
            r14 = 0
        L_0x0098:
            int r21 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r21 <= 0) goto L_0x011d
            int r14 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r14 == 0) goto L_0x00a4
            r0.setLineWidth((float) r7)
            r15 = r7
        L_0x00a4:
            if (r19 == 0) goto L_0x00ac
            boolean r14 = r0.compareColors(r13, r11)
            if (r14 != 0) goto L_0x00b8
        L_0x00ac:
            if (r11 != 0) goto L_0x00b2
            r25.resetRGBColorStroke()
            goto L_0x00b5
        L_0x00b2:
            r0.setColorStroke(r11)
        L_0x00b5:
            r13 = r11
            r19 = r18
        L_0x00b8:
            boolean r14 = r0.compareColors(r9, r11)
            boolean r21 = r0.compareColors(r10, r11)
            float r22 = r7 / r17
            r23 = r13
            float r13 = r3 - r22
            if (r14 == 0) goto L_0x00cc
            r24 = r15
            r15 = r1
            goto L_0x00d2
        L_0x00cc:
            float r22 = r1 - r5
            r24 = r15
            r15 = r22
        L_0x00d2:
            r0.moveTo((float) r13, (float) r15)
            if (r21 == 0) goto L_0x00d9
            r15 = r2
            goto L_0x00db
        L_0x00d9:
            float r15 = r2 + r6
        L_0x00db:
            r0.lineTo((float) r13, (float) r15)
            r25.stroke()
            if (r14 == 0) goto L_0x00eb
            if (r21 != 0) goto L_0x00e6
            goto L_0x00eb
        L_0x00e6:
            r11 = r16
            r13 = r23
            goto L_0x0121
        L_0x00eb:
            if (r11 != 0) goto L_0x00f1
            r25.resetRGBColorFill()
            goto L_0x00f4
        L_0x00f1:
            r0.setColorFill(r11)
        L_0x00f4:
            if (r14 != 0) goto L_0x0106
            r0.moveTo((float) r3, (float) r1)
            float r13 = r1 - r5
            r0.lineTo((float) r3, (float) r13)
            float r14 = r3 - r7
            r0.lineTo((float) r14, (float) r13)
            r25.fill()
        L_0x0106:
            if (r21 != 0) goto L_0x0117
            r0.moveTo((float) r3, (float) r2)
            float r13 = r2 + r6
            r0.lineTo((float) r3, (float) r13)
            float r3 = r3 - r7
            r0.lineTo((float) r3, (float) r13)
            r25.fill()
        L_0x0117:
            r20 = r18
            r13 = r23
            r3 = 0
            goto L_0x0124
        L_0x011d:
            r24 = r15
            r11 = r16
        L_0x0121:
            r3 = 0
            r20 = 0
        L_0x0124:
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0196
            int r3 = (r8 > r24 ? 1 : (r8 == r24 ? 0 : -1))
            if (r3 == 0) goto L_0x012f
            r0.setLineWidth((float) r8)
        L_0x012f:
            if (r19 == 0) goto L_0x0137
            boolean r3 = r0.compareColors(r13, r12)
            if (r3 != 0) goto L_0x0140
        L_0x0137:
            if (r12 != 0) goto L_0x013d
            r25.resetRGBColorStroke()
            goto L_0x0140
        L_0x013d:
            r0.setColorStroke(r12)
        L_0x0140:
            boolean r3 = r0.compareColors(r9, r12)
            boolean r7 = r0.compareColors(r10, r12)
            float r9 = r8 / r17
            float r9 = r9 + r4
            if (r3 == 0) goto L_0x014f
            r10 = r1
            goto L_0x0151
        L_0x014f:
            float r10 = r1 - r5
        L_0x0151:
            r0.moveTo((float) r9, (float) r10)
            if (r7 == 0) goto L_0x0158
            r10 = r2
            goto L_0x015a
        L_0x0158:
            float r10 = r2 + r6
        L_0x015a:
            r0.lineTo((float) r9, (float) r10)
            r25.stroke()
            if (r3 == 0) goto L_0x0164
            if (r7 != 0) goto L_0x0196
        L_0x0164:
            if (r20 == 0) goto L_0x016c
            boolean r9 = r0.compareColors(r11, r12)
            if (r9 != 0) goto L_0x0175
        L_0x016c:
            if (r12 != 0) goto L_0x0172
            r25.resetRGBColorFill()
            goto L_0x0175
        L_0x0172:
            r0.setColorFill(r12)
        L_0x0175:
            if (r3 != 0) goto L_0x0186
            r0.moveTo((float) r4, (float) r1)
            float r1 = r1 - r5
            r0.lineTo((float) r4, (float) r1)
            float r3 = r4 + r8
            r0.lineTo((float) r3, (float) r1)
            r25.fill()
        L_0x0186:
            if (r7 != 0) goto L_0x0196
            r0.moveTo((float) r4, (float) r2)
            float r2 = r2 + r6
            r0.lineTo((float) r4, (float) r2)
            float r4 = r4 + r8
            r0.lineTo((float) r4, (float) r2)
            r25.fill()
        L_0x0196:
            r25.restoreState()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfContentByte.variableRectangle(com.itextpdf.text.Rectangle):void");
    }

    public void rectangle(Rectangle rectangle) {
        float left = rectangle.getLeft();
        float bottom = rectangle.getBottom();
        float right = rectangle.getRight();
        float top = rectangle.getTop();
        BaseColor backgroundColor = rectangle.getBackgroundColor();
        if (backgroundColor != null) {
            saveState();
            setColorFill(backgroundColor);
            rectangle(left, bottom, right - left, top - bottom);
            fill();
            restoreState();
        }
        if (rectangle.hasBorders()) {
            if (rectangle.isUseVariableBorders()) {
                variableRectangle(rectangle);
                return;
            }
            if (rectangle.getBorderWidth() != -1.0f) {
                setLineWidth(rectangle.getBorderWidth());
            }
            BaseColor borderColor = rectangle.getBorderColor();
            if (borderColor != null) {
                setColorStroke(borderColor);
            }
            if (rectangle.hasBorder(15)) {
                rectangle(left, bottom, right - left, top - bottom);
            } else {
                if (rectangle.hasBorder(8)) {
                    moveTo(right, bottom);
                    lineTo(right, top);
                }
                if (rectangle.hasBorder(4)) {
                    moveTo(left, bottom);
                    lineTo(left, top);
                }
                if (rectangle.hasBorder(2)) {
                    moveTo(left, bottom);
                    lineTo(right, bottom);
                }
                if (rectangle.hasBorder(1)) {
                    moveTo(left, top);
                    lineTo(right, top);
                }
            }
            stroke();
            if (borderColor != null) {
                resetRGBColorStroke();
            }
        }
    }

    public void closePath() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        this.content.append("h").append_i(this.separator);
    }

    public void newPath() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        this.content.append("n").append_i(this.separator);
    }

    public void stroke() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorStroke);
        PdfWriter.checkPdfIsoConformance(this.writer, 6, this.state.extGState);
        this.content.append("S").append_i(this.separator);
    }

    public void closePathStroke() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorStroke);
        PdfWriter.checkPdfIsoConformance(this.writer, 6, this.state.extGState);
        this.content.append(HtmlTags.S).append_i(this.separator);
    }

    public void fill() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorFill);
        PdfWriter.checkPdfIsoConformance(this.writer, 6, this.state.extGState);
        this.content.append("f").append_i(this.separator);
    }

    public void eoFill() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorFill);
        PdfWriter.checkPdfIsoConformance(this.writer, 6, this.state.extGState);
        this.content.append("f*").append_i(this.separator);
    }

    public void fillStroke() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorFill);
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorStroke);
        PdfWriter.checkPdfIsoConformance(this.writer, 6, this.state.extGState);
        this.content.append("B").append_i(this.separator);
    }

    public void closePathFillStroke() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorFill);
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorStroke);
        PdfWriter.checkPdfIsoConformance(this.writer, 6, this.state.extGState);
        this.content.append(HtmlTags.B).append_i(this.separator);
    }

    public void eoFillStroke() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorFill);
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorStroke);
        PdfWriter.checkPdfIsoConformance(this.writer, 6, this.state.extGState);
        this.content.append("B*").append_i(this.separator);
    }

    public void closePathEoFillStroke() {
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
            }
        }
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorFill);
        PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorStroke);
        PdfWriter.checkPdfIsoConformance(this.writer, 6, this.state.extGState);
        this.content.append("b*").append_i(this.separator);
    }

    public void addImage(Image image) throws DocumentException {
        addImage(image, false);
    }

    public void addImage(Image image, boolean z) throws DocumentException {
        if (!image.hasAbsoluteY()) {
            throw new DocumentException(MessageLocalization.getComposedMessage("the.image.must.have.absolute.positioning", new Object[0]));
        }
        float[] matrix = image.matrix();
        matrix[4] = image.getAbsoluteX() - matrix[4];
        matrix[5] = image.getAbsoluteY() - matrix[5];
        addImage(image, matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5], z);
    }

    public void addImage(Image image, float f, float f2, float f3, float f4, float f5, float f6) throws DocumentException {
        addImage(image, f, f2, f3, f4, f5, f6, false);
    }

    public void addImage(Image image, double d, double d2, double d3, double d4, double d5, double d6) throws DocumentException {
        addImage(image, d, d2, d3, d4, d5, d6, false);
    }

    public void addImage(Image image, AffineTransform affineTransform) throws DocumentException {
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        addImage(image, dArr[0], dArr[1], dArr[2], dArr[3], dArr[4], dArr[5], false);
    }

    public void addImage(Image image, float f, float f2, float f3, float f4, float f5, float f6, boolean z) throws DocumentException {
        addImage(image, (double) f, (double) f2, (double) f3, (double) f4, (double) f5, (double) f6, z);
    }

    public void addImage(Image image, double d, double d2, double d3, double d4, double d5, double d6, boolean z) throws DocumentException {
        addImage(image, d, d2, d3, d4, d5, d6, z, false);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x02c4 A[Catch:{ IOException -> 0x0350 }] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x02dc A[Catch:{ IOException -> 0x0350 }] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0389 A[SYNTHETIC, Splitter:B:136:0x0389] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x03c6  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x03dc A[SYNTHETIC, Splitter:B:147:0x03dc] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x03ea A[Catch:{ IOException -> 0x047b }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x03eb A[Catch:{ IOException -> 0x047b }] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x049f  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x04a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addImage(com.itextpdf.text.Image r34, double r35, double r37, double r39, double r41, double r43, double r45, boolean r47, boolean r48) throws com.itextpdf.text.DocumentException {
        /*
            r33 = this;
            r15 = r33
            r13 = r34
            r11 = r35
            r9 = r37
            r7 = r39
            r5 = r41
            r3 = r43
            r1 = r45
            com.itextpdf.awt.geom.AffineTransform r14 = new com.itextpdf.awt.geom.AffineTransform     // Catch:{ IOException -> 0x0491 }
            r16 = r14
            r17 = r11
            r19 = r9
            r21 = r7
            r23 = r5
            r25 = r3
            r27 = r1
            r16.<init>((double) r17, (double) r19, (double) r21, (double) r23, (double) r25, (double) r27)     // Catch:{ IOException -> 0x0491 }
            com.itextpdf.text.pdf.PdfOCG r16 = r34.getLayer()     // Catch:{ IOException -> 0x0491 }
            if (r16 == 0) goto L_0x0039
            com.itextpdf.text.pdf.PdfOCG r1 = r34.getLayer()     // Catch:{ IOException -> 0x0031 }
            r15.beginLayer(r1)     // Catch:{ IOException -> 0x0031 }
            goto L_0x0039
        L_0x0031:
            r0 = move-exception
            r1 = r0
            r23 = r13
            r7 = r15
        L_0x0036:
            r3 = 1
            goto L_0x0497
        L_0x0039:
            boolean r1 = r33.isTagged()     // Catch:{ IOException -> 0x0491 }
            if (r1 == 0) goto L_0x0105
            boolean r1 = r15.inText     // Catch:{ IOException -> 0x0031 }
            if (r1 == 0) goto L_0x0046
            r33.endText()     // Catch:{ IOException -> 0x0031 }
        L_0x0046:
            r1 = 4
            com.itextpdf.awt.geom.Point2D$Float[] r2 = new com.itextpdf.awt.geom.Point2D.Float[r1]     // Catch:{ IOException -> 0x0031 }
            com.itextpdf.awt.geom.Point2D$Float r1 = new com.itextpdf.awt.geom.Point2D$Float     // Catch:{ IOException -> 0x0031 }
            r3 = 0
            r1.<init>(r3, r3)     // Catch:{ IOException -> 0x0031 }
            r4 = 0
            r2[r4] = r1     // Catch:{ IOException -> 0x0031 }
            com.itextpdf.awt.geom.Point2D$Float r1 = new com.itextpdf.awt.geom.Point2D$Float     // Catch:{ IOException -> 0x0031 }
            r4 = 1065353216(0x3f800000, float:1.0)
            r1.<init>(r4, r3)     // Catch:{ IOException -> 0x0031 }
            r16 = 1
            r2[r16] = r1     // Catch:{ IOException -> 0x00fc }
            com.itextpdf.awt.geom.Point2D$Float r1 = new com.itextpdf.awt.geom.Point2D$Float     // Catch:{ IOException -> 0x0031 }
            r1.<init>(r4, r4)     // Catch:{ IOException -> 0x0031 }
            r16 = 2
            r2[r16] = r1     // Catch:{ IOException -> 0x0031 }
            com.itextpdf.awt.geom.Point2D$Float r1 = new com.itextpdf.awt.geom.Point2D$Float     // Catch:{ IOException -> 0x0031 }
            r1.<init>(r3, r4)     // Catch:{ IOException -> 0x0031 }
            r3 = 3
            r2[r3] = r1     // Catch:{ IOException -> 0x0031 }
            r1 = 4
            com.itextpdf.awt.geom.Point2D$Float[] r3 = new com.itextpdf.awt.geom.Point2D.Float[r1]     // Catch:{ IOException -> 0x0031 }
            r18 = 0
            r20 = 0
            r21 = 4
            r16 = r14
            r17 = r2
            r19 = r3
            r16.transform((com.itextpdf.awt.geom.Point2D[]) r17, (int) r18, (com.itextpdf.awt.geom.Point2D[]) r19, (int) r20, (int) r21)     // Catch:{ IOException -> 0x0031 }
            r1 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            r2 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r5 = r1
            r4 = r2
            r29 = r14
            r6 = 4
            r14 = r5
            r1 = 0
        L_0x008d:
            if (r1 >= r6) goto L_0x00e0
            r6 = r3[r1]     // Catch:{ IOException -> 0x0031 }
            double r16 = r6.getX()     // Catch:{ IOException -> 0x0031 }
            double r6 = (double) r2     // Catch:{ IOException -> 0x0031 }
            int r8 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x00a1
            r2 = r3[r1]     // Catch:{ IOException -> 0x0031 }
            double r6 = r2.getX()     // Catch:{ IOException -> 0x0031 }
            float r2 = (float) r6     // Catch:{ IOException -> 0x0031 }
        L_0x00a1:
            r6 = r3[r1]     // Catch:{ IOException -> 0x0031 }
            double r6 = r6.getX()     // Catch:{ IOException -> 0x0031 }
            double r8 = (double) r14     // Catch:{ IOException -> 0x0031 }
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x00b4
            r6 = r3[r1]     // Catch:{ IOException -> 0x0031 }
            double r6 = r6.getX()     // Catch:{ IOException -> 0x0031 }
            float r6 = (float) r6     // Catch:{ IOException -> 0x0031 }
            r14 = r6
        L_0x00b4:
            r6 = r3[r1]     // Catch:{ IOException -> 0x0031 }
            double r6 = r6.getY()     // Catch:{ IOException -> 0x0031 }
            double r8 = (double) r4     // Catch:{ IOException -> 0x0031 }
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x00c6
            r4 = r3[r1]     // Catch:{ IOException -> 0x0031 }
            double r6 = r4.getY()     // Catch:{ IOException -> 0x0031 }
            float r4 = (float) r6     // Catch:{ IOException -> 0x0031 }
        L_0x00c6:
            r6 = r3[r1]     // Catch:{ IOException -> 0x0031 }
            double r6 = r6.getY()     // Catch:{ IOException -> 0x0031 }
            double r8 = (double) r5     // Catch:{ IOException -> 0x0031 }
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x00d8
            r5 = r3[r1]     // Catch:{ IOException -> 0x0031 }
            double r5 = r5.getY()     // Catch:{ IOException -> 0x0031 }
            float r5 = (float) r5     // Catch:{ IOException -> 0x0031 }
        L_0x00d8:
            int r1 = r1 + 1
            r6 = 4
            r7 = r39
            r9 = r37
            goto L_0x008d
        L_0x00e0:
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.BBOX     // Catch:{ IOException -> 0x0031 }
            com.itextpdf.text.pdf.PdfArray r3 = new com.itextpdf.text.pdf.PdfArray     // Catch:{ IOException -> 0x0031 }
            r6 = 4
            float[] r7 = new float[r6]     // Catch:{ IOException -> 0x0031 }
            r16 = 0
            r7[r16] = r2     // Catch:{ IOException -> 0x0031 }
            r17 = 1
            r7[r17] = r4     // Catch:{ IOException -> 0x018e }
            r2 = 2
            r7[r2] = r14     // Catch:{ IOException -> 0x018e }
            r4 = 3
            r7[r4] = r5     // Catch:{ IOException -> 0x018e }
            r3.<init>((float[]) r7)     // Catch:{ IOException -> 0x018e }
            r13.setAccessibleAttribute(r1, r3)     // Catch:{ IOException -> 0x018e }
            goto L_0x010c
        L_0x00fc:
            r0 = move-exception
            r1 = r0
            r23 = r13
            r7 = r15
            r3 = r16
            goto L_0x0497
        L_0x0105:
            r29 = r14
            r2 = 2
            r16 = 0
            r17 = 1
        L_0x010c:
            com.itextpdf.text.pdf.PdfWriter r1 = r15.writer     // Catch:{ IOException -> 0x048a }
            if (r1 == 0) goto L_0x0197
            boolean r1 = r34.isImgTemplate()     // Catch:{ IOException -> 0x018e }
            if (r1 == 0) goto L_0x0197
            com.itextpdf.text.pdf.PdfWriter r1 = r15.writer     // Catch:{ IOException -> 0x018e }
            r1.addDirectImageSimple(r13)     // Catch:{ IOException -> 0x018e }
            com.itextpdf.text.pdf.PdfTemplate r3 = r34.getTemplateData()     // Catch:{ IOException -> 0x018e }
            java.util.HashMap r1 = r34.getAccessibleAttributes()     // Catch:{ IOException -> 0x018e }
            if (r1 == 0) goto L_0x0145
            java.util.HashMap r1 = r34.getAccessibleAttributes()     // Catch:{ IOException -> 0x018e }
            java.util.Set r1 = r1.keySet()     // Catch:{ IOException -> 0x018e }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ IOException -> 0x018e }
        L_0x0131:
            boolean r4 = r1.hasNext()     // Catch:{ IOException -> 0x018e }
            if (r4 == 0) goto L_0x0145
            java.lang.Object r4 = r1.next()     // Catch:{ IOException -> 0x018e }
            com.itextpdf.text.pdf.PdfName r4 = (com.itextpdf.text.pdf.PdfName) r4     // Catch:{ IOException -> 0x018e }
            com.itextpdf.text.pdf.PdfObject r5 = r13.getAccessibleAttribute(r4)     // Catch:{ IOException -> 0x018e }
            r3.setAccessibleAttribute(r4, r5)     // Catch:{ IOException -> 0x018e }
            goto L_0x0131
        L_0x0145:
            float r1 = r3.getWidth()     // Catch:{ IOException -> 0x018e }
            float r4 = r3.getHeight()     // Catch:{ IOException -> 0x018e }
            double r5 = (double) r1
            double r7 = r11 / r5
            r9 = r37
            double r5 = r9 / r5
            r30 = r3
            double r2 = (double) r4
            r18 = r39
            double r20 = r18 / r2
            r22 = r41
            double r24 = r22 / r2
            r26 = 0
            r27 = 0
            r3 = r45
            r1 = r15
            r14 = 2
            r2 = r30
            r3 = r7
            r7 = r22
            r7 = r20
            r9 = r24
            r11 = r43
            r13 = r45
            r15 = r26
            r16 = r27
            r1.addTemplate(r2, r3, r5, r7, r9, r11, r13, r15, r16)     // Catch:{ IOException -> 0x0188 }
            r3 = r34
            r6 = 1
            r8 = r39
            r10 = r37
            r12 = r35
            r14 = r33
            goto L_0x0383
        L_0x0188:
            r0 = move-exception
            r1 = r0
            r3 = 1
            r7 = r33
            goto L_0x01f2
        L_0x018e:
            r0 = move-exception
            r1 = r0
            r23 = r13
            r7 = r15
            r3 = r17
            goto L_0x0497
        L_0x0197:
            r14 = r15
            com.itextpdf.text.pdf.ByteBuffer r1 = r14.content     // Catch:{ IOException -> 0x0484 }
            java.lang.String r2 = "q "
            r1.append((java.lang.String) r2)     // Catch:{ IOException -> 0x0484 }
            r1 = r29
            boolean r1 = r1.isIdentity()     // Catch:{ IOException -> 0x0484 }
            r2 = 32
            if (r1 != 0) goto L_0x01f6
            com.itextpdf.text.pdf.ByteBuffer r1 = r14.content     // Catch:{ IOException -> 0x01ee }
            r12 = r35
            com.itextpdf.text.pdf.ByteBuffer r1 = r1.append((double) r12)     // Catch:{ IOException -> 0x01ee }
            r1.append((char) r2)     // Catch:{ IOException -> 0x01ee }
            com.itextpdf.text.pdf.ByteBuffer r1 = r14.content     // Catch:{ IOException -> 0x01ee }
            r10 = r37
            com.itextpdf.text.pdf.ByteBuffer r1 = r1.append((double) r10)     // Catch:{ IOException -> 0x01ee }
            r1.append((char) r2)     // Catch:{ IOException -> 0x01ee }
            com.itextpdf.text.pdf.ByteBuffer r1 = r14.content     // Catch:{ IOException -> 0x01ee }
            r8 = r39
            com.itextpdf.text.pdf.ByteBuffer r1 = r1.append((double) r8)     // Catch:{ IOException -> 0x01ee }
            r1.append((char) r2)     // Catch:{ IOException -> 0x01ee }
            com.itextpdf.text.pdf.ByteBuffer r1 = r14.content     // Catch:{ IOException -> 0x01ee }
            r6 = r41
            com.itextpdf.text.pdf.ByteBuffer r1 = r1.append((double) r6)     // Catch:{ IOException -> 0x01ee }
            r1.append((char) r2)     // Catch:{ IOException -> 0x01ee }
            com.itextpdf.text.pdf.ByteBuffer r1 = r14.content     // Catch:{ IOException -> 0x01ee }
            r4 = r43
            com.itextpdf.text.pdf.ByteBuffer r1 = r1.append((double) r4)     // Catch:{ IOException -> 0x01ee }
            r1.append((char) r2)     // Catch:{ IOException -> 0x01ee }
            com.itextpdf.text.pdf.ByteBuffer r1 = r14.content     // Catch:{ IOException -> 0x01ee }
            r2 = r45
            com.itextpdf.text.pdf.ByteBuffer r1 = r1.append((double) r2)     // Catch:{ IOException -> 0x01ee }
            java.lang.String r15 = " cm"
            r1.append((java.lang.String) r15)     // Catch:{ IOException -> 0x01ee }
            goto L_0x0202
        L_0x01ee:
            r0 = move-exception
            r1 = r0
            r7 = r14
            r3 = 1
        L_0x01f2:
            r23 = r34
            goto L_0x0497
        L_0x01f6:
            r4 = r43
            r2 = r45
            r6 = r41
            r8 = r39
            r10 = r37
            r12 = r35
        L_0x0202:
            if (r47 == 0) goto L_0x0333
            com.itextpdf.text.pdf.ByteBuffer r1 = r14.content     // Catch:{ IOException -> 0x01ee }
            java.lang.String r15 = "\nBI\n"
            r1.append((java.lang.String) r15)     // Catch:{ IOException -> 0x01ee }
            com.itextpdf.text.pdf.PdfImage r1 = new com.itextpdf.text.pdf.PdfImage     // Catch:{ IOException -> 0x01ee }
            java.lang.String r15 = ""
            r2 = 0
            r3 = r34
            r1.<init>(r3, r15, r2)     // Catch:{ IOException -> 0x032c }
            boolean r15 = r3 instanceof com.itextpdf.text.ImgJBIG2     // Catch:{ IOException -> 0x032c }
            if (r15 == 0) goto L_0x0237
            r15 = r3
            com.itextpdf.text.ImgJBIG2 r15 = (com.itextpdf.text.ImgJBIG2) r15     // Catch:{ IOException -> 0x032c }
            byte[] r15 = r15.getGlobalBytes()     // Catch:{ IOException -> 0x032c }
            if (r15 == 0) goto L_0x0237
            com.itextpdf.text.pdf.PdfDictionary r2 = new com.itextpdf.text.pdf.PdfDictionary     // Catch:{ IOException -> 0x032c }
            r2.<init>()     // Catch:{ IOException -> 0x032c }
            com.itextpdf.text.pdf.PdfName r4 = com.itextpdf.text.pdf.PdfName.JBIG2GLOBALS     // Catch:{ IOException -> 0x032c }
            com.itextpdf.text.pdf.PdfWriter r5 = r14.writer     // Catch:{ IOException -> 0x032c }
            com.itextpdf.text.pdf.PdfIndirectReference r5 = r5.getReferenceJBIG2Globals(r15)     // Catch:{ IOException -> 0x032c }
            r2.put(r4, r5)     // Catch:{ IOException -> 0x032c }
            com.itextpdf.text.pdf.PdfName r4 = com.itextpdf.text.pdf.PdfName.DECODEPARMS     // Catch:{ IOException -> 0x032c }
            r1.put(r4, r2)     // Catch:{ IOException -> 0x032c }
        L_0x0237:
            com.itextpdf.text.pdf.PdfWriter r2 = r14.writer     // Catch:{ IOException -> 0x032c }
            r4 = 17
            com.itextpdf.text.pdf.PdfWriter.checkPdfIsoConformance(r2, r4, r1)     // Catch:{ IOException -> 0x032c }
            java.util.Set r2 = r1.getKeys()     // Catch:{ IOException -> 0x032c }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ IOException -> 0x032c }
        L_0x0246:
            boolean r4 = r2.hasNext()     // Catch:{ IOException -> 0x032c }
            if (r4 == 0) goto L_0x02f0
            java.lang.Object r4 = r2.next()     // Catch:{ IOException -> 0x032c }
            com.itextpdf.text.pdf.PdfName r4 = (com.itextpdf.text.pdf.PdfName) r4     // Catch:{ IOException -> 0x032c }
            com.itextpdf.text.pdf.PdfObject r5 = r1.get(r4)     // Catch:{ IOException -> 0x032c }
            java.util.HashMap<com.itextpdf.text.pdf.PdfName, java.lang.String> r15 = abrev     // Catch:{ IOException -> 0x032c }
            java.lang.Object r15 = r15.get(r4)     // Catch:{ IOException -> 0x032c }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ IOException -> 0x032c }
            if (r15 != 0) goto L_0x0261
            goto L_0x0246
        L_0x0261:
            r31 = r2
            com.itextpdf.text.pdf.ByteBuffer r2 = r14.content     // Catch:{ IOException -> 0x032c }
            r2.append((java.lang.String) r15)     // Catch:{ IOException -> 0x032c }
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x032c }
            boolean r2 = r4.equals(r2)     // Catch:{ IOException -> 0x032c }
            if (r2 == 0) goto L_0x02b0
            boolean r2 = r5.isArray()     // Catch:{ IOException -> 0x032c }
            if (r2 == 0) goto L_0x02b0
            r2 = r5
            com.itextpdf.text.pdf.PdfArray r2 = (com.itextpdf.text.pdf.PdfArray) r2     // Catch:{ IOException -> 0x032c }
            int r15 = r2.size()     // Catch:{ IOException -> 0x032c }
            r6 = 4
            if (r15 != r6) goto L_0x02b0
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.INDEXED     // Catch:{ IOException -> 0x032c }
            r15 = 0
            com.itextpdf.text.pdf.PdfName r6 = r2.getAsName(r15)     // Catch:{ IOException -> 0x032c }
            boolean r6 = r7.equals(r6)     // Catch:{ IOException -> 0x032c }
            if (r6 == 0) goto L_0x02b0
            r6 = 1
            com.itextpdf.text.pdf.PdfObject r7 = r2.getPdfObject(r6)     // Catch:{ IOException -> 0x0350 }
            boolean r7 = r7.isName()     // Catch:{ IOException -> 0x0350 }
            if (r7 == 0) goto L_0x02b1
            r7 = 2
            com.itextpdf.text.pdf.PdfObject r15 = r2.getPdfObject(r7)     // Catch:{ IOException -> 0x0350 }
            boolean r15 = r15.isNumber()     // Catch:{ IOException -> 0x0350 }
            if (r15 == 0) goto L_0x02b2
            r15 = 3
            com.itextpdf.text.pdf.PdfObject r2 = r2.getPdfObject(r15)     // Catch:{ IOException -> 0x0350 }
            boolean r2 = r2.isString()     // Catch:{ IOException -> 0x0350 }
            if (r2 == 0) goto L_0x02b3
            r2 = 0
            goto L_0x02b4
        L_0x02b0:
            r6 = 1
        L_0x02b1:
            r7 = 2
        L_0x02b2:
            r15 = 3
        L_0x02b3:
            r2 = r6
        L_0x02b4:
            if (r2 == 0) goto L_0x02dc
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.COLORSPACE     // Catch:{ IOException -> 0x0350 }
            boolean r2 = r4.equals(r2)     // Catch:{ IOException -> 0x0350 }
            if (r2 == 0) goto L_0x02dc
            boolean r2 = r5.isName()     // Catch:{ IOException -> 0x0350 }
            if (r2 != 0) goto L_0x02dc
            com.itextpdf.text.pdf.PdfWriter r2 = r14.writer     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.PdfName r2 = r2.getColorspaceName()     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.PageResources r4 = r33.getPageResources()     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.PdfWriter r7 = r14.writer     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.PdfIndirectObject r5 = r7.addToBody(r5)     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.PdfIndirectReference r5 = r5.getIndirectReference()     // Catch:{ IOException -> 0x0350 }
            r4.addColor(r2, r5)     // Catch:{ IOException -> 0x0350 }
            goto L_0x02dd
        L_0x02dc:
            r2 = r5
        L_0x02dd:
            com.itextpdf.text.pdf.ByteBuffer r4 = r14.content     // Catch:{ IOException -> 0x0350 }
            r5 = 0
            r2.toPdf(r5, r4)     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.ByteBuffer r2 = r14.content     // Catch:{ IOException -> 0x0350 }
            r4 = 10
            r2.append((char) r4)     // Catch:{ IOException -> 0x0350 }
            r2 = r31
            r6 = r41
            goto L_0x0246
        L_0x02f0:
            r6 = 1
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0350 }
            r2.<init>()     // Catch:{ IOException -> 0x0350 }
            r1.writeContent(r2)     // Catch:{ IOException -> 0x0350 }
            byte[] r1 = r2.toByteArray()     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.ByteBuffer r2 = r14.content     // Catch:{ IOException -> 0x0350 }
            java.lang.String r4 = "/L %s\n"
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ IOException -> 0x0350 }
            int r7 = r1.length     // Catch:{ IOException -> 0x0350 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ IOException -> 0x0350 }
            r15 = 0
            r5[r15] = r7     // Catch:{ IOException -> 0x0350 }
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch:{ IOException -> 0x0350 }
            r2.append((java.lang.String) r4)     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.ByteBuffer r2 = r14.content     // Catch:{ IOException -> 0x0350 }
            java.lang.String r4 = "ID\n"
            r2.append((java.lang.String) r4)     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.ByteBuffer r2 = r14.content     // Catch:{ IOException -> 0x0350 }
            r2.append((byte[]) r1)     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.ByteBuffer r1 = r14.content     // Catch:{ IOException -> 0x0350 }
            java.lang.String r2 = "\nEI\nQ"
            com.itextpdf.text.pdf.ByteBuffer r1 = r1.append((java.lang.String) r2)     // Catch:{ IOException -> 0x0350 }
            int r2 = r14.separator     // Catch:{ IOException -> 0x0350 }
            r1.append_i(r2)     // Catch:{ IOException -> 0x0350 }
            goto L_0x0383
        L_0x032c:
            r0 = move-exception
            r1 = r0
            r23 = r3
        L_0x0330:
            r7 = r14
            goto L_0x0036
        L_0x0333:
            r3 = r34
            r6 = 1
            com.itextpdf.text.pdf.PageResources r1 = r33.getPageResources()     // Catch:{ IOException -> 0x047e }
            com.itextpdf.text.Image r2 = r34.getImageMask()     // Catch:{ IOException -> 0x047e }
            if (r2 == 0) goto L_0x0358
            com.itextpdf.text.pdf.PdfWriter r4 = r14.writer     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.PdfName r2 = r4.addDirectImageSimple(r2)     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.PdfWriter r4 = r14.writer     // Catch:{ IOException -> 0x0350 }
            com.itextpdf.text.pdf.PdfIndirectReference r4 = r4.getImageReference(r2)     // Catch:{ IOException -> 0x0350 }
            r1.addXObject(r2, r4)     // Catch:{ IOException -> 0x0350 }
            goto L_0x0358
        L_0x0350:
            r0 = move-exception
            r1 = r0
            r23 = r3
        L_0x0354:
            r3 = r6
            r7 = r14
            goto L_0x0497
        L_0x0358:
            com.itextpdf.text.pdf.PdfWriter r2 = r14.writer     // Catch:{ IOException -> 0x047e }
            com.itextpdf.text.pdf.PdfName r2 = r2.addDirectImageSimple(r3)     // Catch:{ IOException -> 0x047e }
            com.itextpdf.text.pdf.PdfWriter r4 = r14.writer     // Catch:{ IOException -> 0x047e }
            com.itextpdf.text.pdf.PdfIndirectReference r4 = r4.getImageReference(r2)     // Catch:{ IOException -> 0x047e }
            com.itextpdf.text.pdf.PdfName r1 = r1.addXObject(r2, r4)     // Catch:{ IOException -> 0x047e }
            com.itextpdf.text.pdf.ByteBuffer r2 = r14.content     // Catch:{ IOException -> 0x047e }
            r4 = 32
            com.itextpdf.text.pdf.ByteBuffer r2 = r2.append((char) r4)     // Catch:{ IOException -> 0x047e }
            byte[] r1 = r1.getBytes()     // Catch:{ IOException -> 0x047e }
            com.itextpdf.text.pdf.ByteBuffer r1 = r2.append((byte[]) r1)     // Catch:{ IOException -> 0x047e }
            java.lang.String r2 = " Do Q"
            com.itextpdf.text.pdf.ByteBuffer r1 = r1.append((java.lang.String) r2)     // Catch:{ IOException -> 0x047e }
            int r2 = r14.separator     // Catch:{ IOException -> 0x047e }
            r1.append_i(r2)     // Catch:{ IOException -> 0x047e }
        L_0x0383:
            boolean r1 = r34.hasBorders()     // Catch:{ IOException -> 0x047e }
            if (r1 == 0) goto L_0x03c6
            r33.saveState()     // Catch:{ IOException -> 0x03c1 }
            float r1 = r34.getWidth()     // Catch:{ IOException -> 0x03c1 }
            float r2 = r34.getHeight()     // Catch:{ IOException -> 0x03c1 }
            double r4 = (double) r1
            double r15 = r12 / r4
            double r4 = r10 / r4
            double r1 = (double) r2
            double r17 = r8 / r1
            r19 = r41
            double r21 = r19 / r1
            r1 = r14
            r23 = r3
            r24 = r45
            r2 = r15
            r15 = r43
            r26 = 2
            r6 = r17
            r17 = r8
            r8 = r21
            r21 = r10
            r10 = r15
            r27 = r12
            r12 = r24
            r1.concatCTM((double) r2, (double) r4, (double) r6, (double) r8, (double) r10, (double) r12)     // Catch:{ IOException -> 0x03e0 }
            r33.rectangle(r34)     // Catch:{ IOException -> 0x03e0 }
            r33.restoreState()     // Catch:{ IOException -> 0x03e0 }
            goto L_0x03d6
        L_0x03c1:
            r0 = move-exception
            r23 = r3
            r1 = r0
            goto L_0x0354
        L_0x03c6:
            r23 = r3
            r17 = r8
            r21 = r10
            r27 = r12
            r15 = r43
            r19 = r41
            r24 = r45
            r26 = 2
        L_0x03d6:
            com.itextpdf.text.pdf.PdfOCG r1 = r34.getLayer()     // Catch:{ IOException -> 0x047b }
            if (r1 == 0) goto L_0x03e4
            r33.endLayer()     // Catch:{ IOException -> 0x03e0 }
            goto L_0x03e4
        L_0x03e0:
            r0 = move-exception
            r1 = r0
            goto L_0x0330
        L_0x03e4:
            com.itextpdf.text.Annotation r1 = r34.getAnnotation()     // Catch:{ IOException -> 0x047b }
            if (r1 != 0) goto L_0x03eb
            return
        L_0x03eb:
            float[] r2 = unitRect     // Catch:{ IOException -> 0x047b }
            int r2 = r2.length     // Catch:{ IOException -> 0x047b }
            double[] r2 = new double[r2]     // Catch:{ IOException -> 0x047b }
            r3 = 0
        L_0x03f1:
            float[] r4 = unitRect     // Catch:{ IOException -> 0x047b }
            int r4 = r4.length     // Catch:{ IOException -> 0x047b }
            if (r3 >= r4) goto L_0x0420
            float[] r4 = unitRect     // Catch:{ IOException -> 0x03e0 }
            r4 = r4[r3]     // Catch:{ IOException -> 0x03e0 }
            double r4 = (double) r4     // Catch:{ IOException -> 0x03e0 }
            double r4 = r4 * r27
            float[] r6 = unitRect     // Catch:{ IOException -> 0x03e0 }
            int r7 = r3 + 1
            r6 = r6[r7]     // Catch:{ IOException -> 0x03e0 }
            double r8 = (double) r6     // Catch:{ IOException -> 0x03e0 }
            double r8 = r8 * r17
            double r4 = r4 + r8
            double r4 = r4 + r15
            r2[r3] = r4     // Catch:{ IOException -> 0x03e0 }
            float[] r4 = unitRect     // Catch:{ IOException -> 0x03e0 }
            r4 = r4[r3]     // Catch:{ IOException -> 0x03e0 }
            double r4 = (double) r4     // Catch:{ IOException -> 0x03e0 }
            double r4 = r4 * r21
            float[] r6 = unitRect     // Catch:{ IOException -> 0x03e0 }
            r6 = r6[r7]     // Catch:{ IOException -> 0x03e0 }
            double r8 = (double) r6     // Catch:{ IOException -> 0x03e0 }
            double r8 = r8 * r19
            double r4 = r4 + r8
            double r4 = r4 + r24
            r2[r7] = r4     // Catch:{ IOException -> 0x03e0 }
            int r3 = r3 + 2
            goto L_0x03f1
        L_0x0420:
            r3 = 0
            r4 = r2[r3]     // Catch:{ IOException -> 0x047b }
            r3 = 1
            r6 = r2[r3]     // Catch:{ IOException -> 0x0479 }
            r9 = r4
            r11 = r6
            r7 = r11
            r4 = r26
            r5 = r9
        L_0x042c:
            int r13 = r2.length     // Catch:{ IOException -> 0x0479 }
            if (r4 >= r13) goto L_0x0453
            r13 = r2[r4]     // Catch:{ IOException -> 0x044e }
            double r5 = java.lang.Math.min(r5, r13)     // Catch:{ IOException -> 0x044e }
            int r13 = r4 + 1
            r14 = r2[r13]     // Catch:{ IOException -> 0x044e }
            double r7 = java.lang.Math.min(r7, r14)     // Catch:{ IOException -> 0x044e }
            r14 = r2[r4]     // Catch:{ IOException -> 0x044e }
            double r9 = java.lang.Math.max(r9, r14)     // Catch:{ IOException -> 0x044e }
            r13 = r2[r13]     // Catch:{ IOException -> 0x044e }
            double r11 = java.lang.Math.max(r11, r13)     // Catch:{ IOException -> 0x044e }
            int r4 = r4 + 2
            r14 = r33
            goto L_0x042c
        L_0x044e:
            r0 = move-exception
            r1 = r0
            r7 = r33
            goto L_0x0497
        L_0x0453:
            com.itextpdf.text.Annotation r2 = new com.itextpdf.text.Annotation     // Catch:{ IOException -> 0x0475 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0475 }
            float r1 = (float) r5     // Catch:{ IOException -> 0x0475 }
            float r4 = (float) r7     // Catch:{ IOException -> 0x0475 }
            float r5 = (float) r9     // Catch:{ IOException -> 0x0475 }
            float r6 = (float) r11     // Catch:{ IOException -> 0x0475 }
            r2.setDimensions(r1, r4, r5, r6)     // Catch:{ IOException -> 0x0475 }
            r7 = r33
            com.itextpdf.text.pdf.PdfWriter r8 = r7.writer     // Catch:{ IOException -> 0x0473 }
            com.itextpdf.text.Rectangle r9 = new com.itextpdf.text.Rectangle     // Catch:{ IOException -> 0x0473 }
            r9.<init>(r1, r4, r5, r6)     // Catch:{ IOException -> 0x0473 }
            com.itextpdf.text.pdf.PdfAnnotation r1 = com.itextpdf.text.pdf.internal.PdfAnnotationsImp.convertAnnotation(r8, r2, r9)     // Catch:{ IOException -> 0x0473 }
            if (r1 != 0) goto L_0x046f
            return
        L_0x046f:
            r7.addAnnotation(r1)     // Catch:{ IOException -> 0x0473 }
            return
        L_0x0473:
            r0 = move-exception
            goto L_0x0496
        L_0x0475:
            r0 = move-exception
            r7 = r33
            goto L_0x0496
        L_0x0479:
            r0 = move-exception
            goto L_0x0482
        L_0x047b:
            r0 = move-exception
            r7 = r14
            goto L_0x0495
        L_0x047e:
            r0 = move-exception
            r23 = r3
            r3 = r6
        L_0x0482:
            r7 = r14
            goto L_0x0496
        L_0x0484:
            r0 = move-exception
            r7 = r14
            r3 = 1
            r23 = r34
            goto L_0x0496
        L_0x048a:
            r0 = move-exception
            r23 = r13
            r7 = r15
            r3 = r17
            goto L_0x0496
        L_0x0491:
            r0 = move-exception
            r23 = r13
            r7 = r15
        L_0x0495:
            r3 = 1
        L_0x0496:
            r1 = r0
        L_0x0497:
            if (r23 == 0) goto L_0x04a9
            java.net.URL r2 = r34.getUrl()
            if (r2 == 0) goto L_0x04a9
            java.net.URL r2 = r34.getUrl()
            java.lang.String r2 = r2.getPath()
            r4 = 0
            goto L_0x04b2
        L_0x04a9:
            java.lang.String r2 = "unknown"
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r5)
        L_0x04b2:
            com.itextpdf.text.DocumentException r5 = new com.itextpdf.text.DocumentException
            java.lang.String r6 = "add.image.exception"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r4] = r2
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r6, (java.lang.Object[]) r3)
            r5.<init>(r2, r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfContentByte.addImage(com.itextpdf.text.Image, double, double, double, double, double, double, boolean, boolean):void");
    }

    public void reset() {
        reset(true);
    }

    public void reset(boolean z) {
        this.content.reset();
        this.markedContentSize = 0;
        if (z) {
            sanityCheck();
        }
        this.state = new GraphicState();
        this.stateList = new ArrayList<>();
    }

    /* access modifiers changed from: protected */
    public void beginText(boolean z) {
        if (!this.inText) {
            this.inText = true;
            this.content.append("BT").append_i(this.separator);
            if (z) {
                float f = this.state.xTLM;
                float f2 = this.state.tx;
                setTextMatrix(this.state.aTLM, this.state.bTLM, this.state.cTLM, this.state.dTLM, this.state.tx, this.state.yTLM);
                this.state.xTLM = f;
                this.state.tx = f2;
                return;
            }
            this.state.xTLM = 0.0f;
            this.state.yTLM = 0.0f;
            this.state.tx = 0.0f;
        } else if (!isTagged()) {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.begin.end.text.operators", new Object[0]));
        }
    }

    public void beginText() {
        beginText(false);
    }

    public void endText() {
        if (this.inText) {
            this.inText = false;
            this.content.append("ET").append_i(this.separator);
        } else if (!isTagged()) {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.begin.end.text.operators", new Object[0]));
        }
    }

    public void saveState() {
        PdfWriter.checkPdfIsoConformance(this.writer, 12, "q");
        if (this.inText && isTagged()) {
            endText();
        }
        this.content.append("q").append_i(this.separator);
        this.stateList.add(new GraphicState(this.state));
    }

    public void restoreState() {
        PdfWriter.checkPdfIsoConformance(this.writer, 12, "Q");
        if (this.inText && isTagged()) {
            endText();
        }
        this.content.append("Q").append_i(this.separator);
        int size = this.stateList.size() - 1;
        if (size < 0) {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.save.restore.state.operators", new Object[0]));
        }
        this.state.restore(this.stateList.get(size));
        this.stateList.remove(size);
    }

    public void setCharacterSpacing(float f) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.charSpace = f;
        this.content.append(f).append(" Tc").append_i(this.separator);
    }

    public void setWordSpacing(float f) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.wordSpace = f;
        this.content.append(f).append(" Tw").append_i(this.separator);
    }

    public void setHorizontalScaling(float f) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.scale = f;
        this.content.append(f).append(" Tz").append_i(this.separator);
    }

    public void setLeading(float f) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.leading = f;
        this.content.append(f).append(" TL").append_i(this.separator);
    }

    public void setFontAndSize(BaseFont baseFont, float f) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        checkWriter();
        if (f >= 1.0E-4f || f <= -1.0E-4f) {
            this.state.size = f;
            this.state.fontDetails = this.writer.addSimple(baseFont);
            this.content.append(getPageResources().addFont(this.state.fontDetails.getFontName(), this.state.fontDetails.getIndirectReference()).getBytes()).append(' ').append(f).append(" Tf").append_i(this.separator);
            return;
        }
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("font.size.too.small.1", String.valueOf(f)));
    }

    public void setTextRenderingMode(int i) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.textRenderMode = i;
        this.content.append(i).append(" Tr").append_i(this.separator);
    }

    public void setTextRise(float f) {
        setTextRise((double) f);
    }

    public void setTextRise(double d) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.content.append(d).append(" Ts").append_i(this.separator);
    }

    private void showText2(String str) {
        if (this.state.fontDetails == null) {
            throw new NullPointerException(MessageLocalization.getComposedMessage("font.and.size.must.be.set.before.writing.any.text", new Object[0]));
        }
        StringUtils.escapeString(this.state.fontDetails.convertToBytes(str), this.content);
    }

    public void showText(String str) {
        checkState();
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        showText2(str);
        updateTx(str, 0.0f);
        this.content.append("Tj").append_i(this.separator);
    }

    public void showTextGid(String str) {
        checkState();
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        if (this.state.fontDetails == null) {
            throw new NullPointerException(MessageLocalization.getComposedMessage("font.and.size.must.be.set.before.writing.any.text", new Object[0]));
        }
        Object[] convertToBytesGid = this.state.fontDetails.convertToBytesGid(str);
        StringUtils.escapeString((byte[]) convertToBytesGid[0], this.content);
        this.state.tx += ((float) ((Integer) convertToBytesGid[2]).intValue()) * 0.001f * this.state.size;
        this.content.append("Tj").append_i(this.separator);
    }

    public static PdfTextArray getKernArray(String str, BaseFont baseFont) {
        PdfTextArray pdfTextArray = new PdfTextArray();
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length() - 1;
        char[] charArray = str.toCharArray();
        if (length >= 0) {
            stringBuffer.append(charArray, 0, 1);
        }
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char c = charArray[i2];
            int kerning = baseFont.getKerning(charArray[i], c);
            if (kerning == 0) {
                stringBuffer.append(c);
            } else {
                pdfTextArray.add(stringBuffer.toString());
                stringBuffer.setLength(0);
                stringBuffer.append(charArray, i2, 1);
                pdfTextArray.add((float) (-kerning));
            }
            i = i2;
        }
        pdfTextArray.add(stringBuffer.toString());
        return pdfTextArray;
    }

    public void showTextKerned(String str) {
        if (this.state.fontDetails == null) {
            throw new NullPointerException(MessageLocalization.getComposedMessage("font.and.size.must.be.set.before.writing.any.text", new Object[0]));
        }
        BaseFont baseFont = this.state.fontDetails.getBaseFont();
        if (baseFont.hasKernPairs()) {
            showText(getKernArray(str, baseFont));
        } else {
            showText(str);
        }
    }

    public void newlineShowText(String str) {
        checkState();
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.yTLM -= this.state.leading;
        showText2(str);
        this.content.append("'").append_i(this.separator);
        this.state.tx = this.state.xTLM;
        updateTx(str, 0.0f);
    }

    public void newlineShowText(float f, float f2, String str) {
        checkState();
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.yTLM -= this.state.leading;
        this.content.append(f).append(' ').append(f2);
        showText2(str);
        this.content.append("\"").append_i(this.separator);
        this.state.charSpace = f2;
        this.state.wordSpace = f;
        this.state.tx = this.state.xTLM;
        updateTx(str, 0.0f);
    }

    public void setTextMatrix(float f, float f2, float f3, float f4, float f5, float f6) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.xTLM = f5;
        this.state.yTLM = f6;
        this.state.aTLM = f;
        this.state.bTLM = f2;
        this.state.cTLM = f3;
        this.state.dTLM = f4;
        this.state.tx = this.state.xTLM;
        this.content.append(f).append(' ').append(f2).append_i(32).append(f3).append_i(32).append(f4).append_i(32).append(f5).append_i(32).append(f6).append(" Tm").append_i(this.separator);
    }

    public void setTextMatrix(AffineTransform affineTransform) {
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        setTextMatrix((float) dArr[0], (float) dArr[1], (float) dArr[2], (float) dArr[3], (float) dArr[4], (float) dArr[5]);
    }

    public void setTextMatrix(float f, float f2) {
        setTextMatrix(1.0f, 0.0f, 0.0f, 1.0f, f, f2);
    }

    public void moveText(float f, float f2) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.xTLM += f;
        this.state.yTLM += f2;
        if (!isTagged() || this.state.xTLM == this.state.tx) {
            this.content.append(f).append(' ').append(f2).append(" Td").append_i(this.separator);
            return;
        }
        setTextMatrix(this.state.aTLM, this.state.bTLM, this.state.cTLM, this.state.dTLM, this.state.xTLM, this.state.yTLM);
    }

    public void moveTextWithLeading(float f, float f2) {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        this.state.xTLM += f;
        this.state.yTLM += f2;
        this.state.leading = -f2;
        if (!isTagged() || this.state.xTLM == this.state.tx) {
            this.content.append(f).append(' ').append(f2).append(" TD").append_i(this.separator);
            return;
        }
        setTextMatrix(this.state.aTLM, this.state.bTLM, this.state.cTLM, this.state.dTLM, this.state.xTLM, this.state.yTLM);
    }

    public void newlineText() {
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        if (isTagged() && this.state.xTLM != this.state.tx) {
            setTextMatrix(this.state.aTLM, this.state.bTLM, this.state.cTLM, this.state.dTLM, this.state.xTLM, this.state.yTLM);
        }
        this.state.yTLM -= this.state.leading;
        this.content.append("T*").append_i(this.separator);
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return size(true);
    }

    /* access modifiers changed from: package-private */
    public int size(boolean z) {
        if (z) {
            return this.content.size();
        }
        return this.content.size() - this.markedContentSize;
    }

    public void addOutline(PdfOutline pdfOutline, String str) {
        checkWriter();
        this.pdf.addOutline(pdfOutline, str);
    }

    public PdfOutline getRootOutline() {
        checkWriter();
        return this.pdf.getRootOutline();
    }

    public float getEffectiveStringWidth(String str, boolean z) {
        float f;
        BaseFont baseFont = this.state.fontDetails.getBaseFont();
        if (z) {
            f = baseFont.getWidthPointKerned(str, this.state.size);
        } else {
            f = baseFont.getWidthPoint(str, this.state.size);
        }
        if (this.state.charSpace != 0.0f && str.length() > 1) {
            f += this.state.charSpace * ((float) (str.length() - 1));
        }
        if (this.state.wordSpace != 0.0f && !baseFont.isVertical()) {
            for (int i = 0; i < str.length() - 1; i++) {
                if (str.charAt(i) == ' ') {
                    f += this.state.wordSpace;
                }
            }
        }
        return ((double) this.state.scale) != 100.0d ? (f * this.state.scale) / 100.0f : f;
    }

    private float getEffectiveStringWidth(String str, boolean z, float f) {
        float f2;
        BaseFont baseFont = this.state.fontDetails.getBaseFont();
        if (z) {
            f2 = baseFont.getWidthPointKerned(str, this.state.size);
        } else {
            f2 = baseFont.getWidthPoint(str, this.state.size);
        }
        if (this.state.charSpace != 0.0f && str.length() > 0) {
            f2 += this.state.charSpace * ((float) str.length());
        }
        if (this.state.wordSpace != 0.0f && !baseFont.isVertical()) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ' ') {
                    f2 += this.state.wordSpace;
                }
            }
        }
        float f3 = f2 - ((f / 1000.0f) * this.state.size);
        return ((double) this.state.scale) != 100.0d ? (f3 * this.state.scale) / 100.0f : f3;
    }

    public void showTextAligned(int i, String str, float f, float f2, float f3) {
        showTextAligned(i, str, f, f2, f3, false);
    }

    private void showTextAligned(int i, String str, float f, float f2, float f3, boolean z) {
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        String str2 = str;
        float f9 = f2;
        float f10 = f3;
        boolean z2 = z;
        if (this.state.fontDetails == null) {
            throw new NullPointerException(MessageLocalization.getComposedMessage("font.and.size.must.be.set.before.writing.any.text", new Object[0]));
        } else if (f10 == 0.0f) {
            switch (i) {
                case 1:
                    f8 = f - (getEffectiveStringWidth(str2, z2) / 2.0f);
                    break;
                case 2:
                    f8 = f - getEffectiveStringWidth(str2, z2);
                    break;
                default:
                    f8 = f;
                    break;
            }
            setTextMatrix(f8, f9);
            if (z2) {
                showTextKerned(str2);
            } else {
                showText(str2);
            }
        } else {
            double d = (((double) f10) * 3.141592653589793d) / 180.0d;
            float cos = (float) Math.cos(d);
            float sin = (float) Math.sin(d);
            switch (i) {
                case 1:
                    float effectiveStringWidth = getEffectiveStringWidth(str2, z2) / 2.0f;
                    f6 = f - (effectiveStringWidth * cos);
                    f7 = f9 - (effectiveStringWidth * sin);
                    break;
                case 2:
                    float effectiveStringWidth2 = getEffectiveStringWidth(str2, z2);
                    f6 = f - (effectiveStringWidth2 * cos);
                    f7 = f9 - (effectiveStringWidth2 * sin);
                    break;
                default:
                    f5 = f;
                    f4 = f9;
                    break;
            }
            f4 = f7;
            f5 = f6;
            setTextMatrix(cos, sin, -sin, cos, f5, f4);
            if (z2) {
                showTextKerned(str2);
            } else {
                showText(str2);
            }
            setTextMatrix(0.0f, 0.0f);
        }
    }

    public void showTextAlignedKerned(int i, String str, float f, float f2, float f3) {
        showTextAligned(i, str, f, f2, f3, true);
    }

    public void concatCTM(float f, float f2, float f3, float f4, float f5, float f6) {
        concatCTM((double) f, (double) f2, (double) f3, (double) f4, (double) f5, (double) f6);
    }

    public void concatCTM(double d, double d2, double d3, double d4, double d5, double d6) {
        if (this.inText && isTagged()) {
            endText();
        }
        this.state.CTM.concatenate(new AffineTransform(d, d2, d3, d4, d5, d6));
        this.content.append(d).append(' ').append(d2).append(' ').append(d3).append(' ');
        this.content.append(d4).append(' ').append(d5).append(' ').append(d6).append(" cm").append_i(this.separator);
    }

    public void concatCTM(AffineTransform affineTransform) {
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        concatCTM(dArr[0], dArr[1], dArr[2], dArr[3], dArr[4], dArr[5]);
    }

    public static ArrayList<double[]> bezierArc(float f, float f2, float f3, float f4, float f5, float f6) {
        return bezierArc((double) f, (double) f2, (double) f3, (double) f4, (double) f5, (double) f6);
    }

    public static ArrayList<double[]> bezierArc(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7;
        double d8;
        double d9;
        double d10;
        double d11;
        int i;
        ArrayList<double[]> arrayList;
        if (d > d3) {
            d7 = d;
            d8 = d3;
        } else {
            d8 = d;
            d7 = d3;
        }
        if (d4 > d2) {
            d9 = d2;
            d10 = d4;
        } else {
            d10 = d2;
            d9 = d4;
        }
        if (Math.abs(d6) <= 90.0d) {
            d11 = d6;
            i = 1;
        } else {
            i = (int) Math.ceil(Math.abs(d6) / 90.0d);
            d11 = d6 / ((double) i);
        }
        double d12 = (d8 + d7) / 2.0d;
        double d13 = (d10 + d9) / 2.0d;
        double d14 = (d7 - d8) / 2.0d;
        double d15 = (d9 - d10) / 2.0d;
        double d16 = (d11 * 3.141592653589793d) / 360.0d;
        double abs = Math.abs((1.3333333333333333d * (1.0d - Math.cos(d16))) / Math.sin(d16));
        ArrayList<double[]> arrayList2 = new ArrayList<>();
        int i2 = 0;
        while (i2 < i) {
            ArrayList<double[]> arrayList3 = arrayList2;
            int i3 = i;
            double d17 = ((d5 + (((double) i2) * d11)) * 3.141592653589793d) / 180.0d;
            i2++;
            double d18 = abs;
            double d19 = ((d5 + (((double) i2) * d11)) * 3.141592653589793d) / 180.0d;
            double cos = Math.cos(d17);
            double cos2 = Math.cos(d19);
            double sin = Math.sin(d17);
            double sin2 = Math.sin(d19);
            if (d11 > 0.0d) {
                arrayList = arrayList3;
                arrayList.add(new double[]{d12 + (d14 * cos), d13 - (d15 * sin), d12 + ((cos - (d18 * sin)) * d14), d13 - ((sin + (cos * d18)) * d15), ((cos2 + (d18 * sin2)) * d14) + d12, d13 - ((sin2 - (d18 * cos2)) * d15), d12 + (cos2 * d14), d13 - (sin2 * d15)});
            } else {
                arrayList = arrayList3;
                arrayList.add(new double[]{d12 + (d14 * cos), d13 - (d15 * sin), d12 + ((cos + (d18 * sin)) * d14), d13 - ((sin - (cos * d18)) * d15), ((cos2 - (d18 * sin2)) * d14) + d12, d13 - (((d18 * cos2) + sin2) * d15), d12 + (cos2 * d14), d13 - (sin2 * d15)});
            }
            arrayList2 = arrayList;
            i = i3;
            abs = d18;
        }
        return arrayList2;
    }

    public void arc(float f, float f2, float f3, float f4, float f5, float f6) {
        arc((double) f, (double) f2, (double) f3, (double) f4, (double) f5, (double) f6);
    }

    public void arc(double d, double d2, double d3, double d4, double d5, double d6) {
        ArrayList<double[]> bezierArc = bezierArc(d, d2, d3, d4, d5, d6);
        if (!bezierArc.isEmpty()) {
            double[] dArr = bezierArc.get(0);
            double d7 = dArr[0];
            double d8 = dArr[1];
            moveTo(d7, d8);
            for (int i = 0; i < bezierArc.size(); i++) {
                double[] dArr2 = bezierArc.get(i);
                curveTo(dArr2[2], dArr2[3], dArr2[4], dArr2[5], dArr2[6], dArr2[7]);
            }
        }
    }

    public void ellipse(float f, float f2, float f3, float f4) {
        ellipse((double) f, (double) f2, (double) f3, (double) f4);
    }

    public void ellipse(double d, double d2, double d3, double d4) {
        arc(d, d2, d3, d4, 0.0d, 360.0d);
    }

    public PdfPatternPainter createPattern(float f, float f2, float f3, float f4) {
        checkWriter();
        if (f3 == 0.0f || f4 == 0.0f) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("xstep.or.ystep.can.not.be.zero", new Object[0]));
        }
        PdfPatternPainter pdfPatternPainter = new PdfPatternPainter(this.writer);
        pdfPatternPainter.setWidth(f);
        pdfPatternPainter.setHeight(f2);
        pdfPatternPainter.setXStep(f3);
        pdfPatternPainter.setYStep(f4);
        this.writer.addSimplePattern(pdfPatternPainter);
        return pdfPatternPainter;
    }

    public PdfPatternPainter createPattern(float f, float f2) {
        return createPattern(f, f2, f, f2);
    }

    public PdfPatternPainter createPattern(float f, float f2, float f3, float f4, BaseColor baseColor) {
        checkWriter();
        if (f3 == 0.0f || f4 == 0.0f) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("xstep.or.ystep.can.not.be.zero", new Object[0]));
        }
        PdfPatternPainter pdfPatternPainter = new PdfPatternPainter(this.writer, baseColor);
        pdfPatternPainter.setWidth(f);
        pdfPatternPainter.setHeight(f2);
        pdfPatternPainter.setXStep(f3);
        pdfPatternPainter.setYStep(f4);
        this.writer.addSimplePattern(pdfPatternPainter);
        return pdfPatternPainter;
    }

    public PdfPatternPainter createPattern(float f, float f2, BaseColor baseColor) {
        return createPattern(f, f2, f, f2, baseColor);
    }

    public PdfTemplate createTemplate(float f, float f2) {
        return createTemplate(f, f2, (PdfName) null);
    }

    /* access modifiers changed from: package-private */
    public PdfTemplate createTemplate(float f, float f2, PdfName pdfName) {
        checkWriter();
        PdfTemplate pdfTemplate = new PdfTemplate(this.writer);
        pdfTemplate.setWidth(f);
        pdfTemplate.setHeight(f2);
        this.writer.addDirectTemplateSimple(pdfTemplate, pdfName);
        return pdfTemplate;
    }

    public PdfAppearance createAppearance(float f, float f2) {
        return createAppearance(f, f2, (PdfName) null);
    }

    /* access modifiers changed from: package-private */
    public PdfAppearance createAppearance(float f, float f2, PdfName pdfName) {
        checkWriter();
        PdfAppearance pdfAppearance = new PdfAppearance(this.writer);
        pdfAppearance.setWidth(f);
        pdfAppearance.setHeight(f2);
        this.writer.addDirectTemplateSimple(pdfAppearance, pdfName);
        return pdfAppearance;
    }

    public void addPSXObject(PdfPSXObject pdfPSXObject) {
        if (this.inText && isTagged()) {
            endText();
        }
        checkWriter();
        this.content.append(getPageResources().addXObject(this.writer.addDirectTemplateSimple(pdfPSXObject, (PdfName) null), pdfPSXObject.getIndirectReference()).getBytes()).append(" Do").append_i(this.separator);
    }

    public void addTemplate(PdfTemplate pdfTemplate, float f, float f2, float f3, float f4, float f5, float f6) {
        addTemplate(pdfTemplate, f, f2, f3, f4, f5, f6, false);
    }

    public void addTemplate(PdfTemplate pdfTemplate, double d, double d2, double d3, double d4, double d5, double d6) {
        addTemplate(pdfTemplate, d, d2, d3, d4, d5, d6, false);
    }

    public void addTemplate(PdfTemplate pdfTemplate, float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
        addTemplate(pdfTemplate, (double) f, (double) f2, (double) f3, (double) f4, (double) f5, (double) f6, z);
    }

    public void addTemplate(PdfTemplate pdfTemplate, double d, double d2, double d3, double d4, double d5, double d6, boolean z) {
        addTemplate(pdfTemplate, d, d2, d3, d4, d5, d6, true, z);
    }

    private void addTemplate(PdfTemplate pdfTemplate, double d, double d2, double d3, double d4, double d5, double d6, boolean z, boolean z2) {
        PdfTemplate pdfTemplate2 = pdfTemplate;
        checkWriter();
        checkNoPattern(pdfTemplate2);
        PdfWriter.checkPdfIsoConformance(this.writer, 20, pdfTemplate2);
        PdfName addXObject = getPageResources().addXObject(this.writer.addDirectTemplateSimple(pdfTemplate2, (PdfName) null), pdfTemplate2.getIndirectReference());
        if (isTagged() && z) {
            if (this.inText) {
                endText();
            }
            if (pdfTemplate2.isContentTagged() || (pdfTemplate2.getPageReference() != null && z2)) {
                throw new RuntimeException(MessageLocalization.getComposedMessage("template.with.tagged.could.not.be.used.more.than.once", new Object[0]));
            }
            pdfTemplate2.setPageReference(this.writer.getCurrentPage());
            if (z2) {
                pdfTemplate2.setContentTagged(true);
                ensureDocumentTagIsOpen();
                ArrayList<IAccessibleElement> mcElements2 = getMcElements();
                if (mcElements2 != null && mcElements2.size() > 0) {
                    pdfTemplate2.getMcElements().add(mcElements2.get(mcElements2.size() - 1));
                }
            } else {
                openMCBlock(pdfTemplate2);
            }
        }
        this.content.append("q ");
        this.content.append(d).append(' ');
        this.content.append(d2).append(' ');
        this.content.append(d3).append(' ');
        this.content.append(d4).append(' ');
        this.content.append(d5).append(' ');
        this.content.append(d6).append(" cm ");
        this.content.append(addXObject.getBytes()).append(" Do Q").append_i(this.separator);
        if (isTagged() && z && !z2) {
            closeMCBlock(pdfTemplate2);
            pdfTemplate2.setId((AccessibleElementId) null);
        }
    }

    public PdfName addFormXObj(PdfStream pdfStream, PdfName pdfName, float f, float f2, float f3, float f4, float f5, float f6) throws IOException {
        return addFormXObj(pdfStream, pdfName, (double) f, (double) f2, (double) f3, (double) f4, (double) f5, (double) f6);
    }

    public PdfName addFormXObj(PdfStream pdfStream, PdfName pdfName, double d, double d2, double d3, double d4, double d5, double d6) throws IOException {
        PdfArtifact pdfArtifact;
        PdfStream pdfStream2 = pdfStream;
        checkWriter();
        PdfWriter.checkPdfIsoConformance(this.writer, 9, pdfStream2);
        PdfName addXObject = getPageResources().addXObject(pdfName, this.writer.addToBody(pdfStream2).getIndirectReference());
        if (isTagged()) {
            if (this.inText) {
                endText();
            }
            pdfArtifact = new PdfArtifact();
            openMCBlock(pdfArtifact);
        } else {
            pdfArtifact = null;
        }
        this.content.append("q ");
        this.content.append(d).append(' ');
        this.content.append(d2).append(' ');
        this.content.append(d3).append(' ');
        this.content.append(d4).append(' ');
        this.content.append(d5).append(' ');
        this.content.append(d6).append(" cm ");
        this.content.append(addXObject.getBytes()).append(" Do Q").append_i(this.separator);
        if (isTagged()) {
            closeMCBlock(pdfArtifact);
        }
        return addXObject;
    }

    public void addTemplate(PdfTemplate pdfTemplate, AffineTransform affineTransform) {
        addTemplate(pdfTemplate, affineTransform, false);
    }

    public void addTemplate(PdfTemplate pdfTemplate, AffineTransform affineTransform, boolean z) {
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        addTemplate(pdfTemplate, dArr[0], dArr[1], dArr[2], dArr[3], dArr[4], dArr[5], z);
    }

    /* access modifiers changed from: package-private */
    public void addTemplateReference(PdfIndirectReference pdfIndirectReference, PdfName pdfName, float f, float f2, float f3, float f4, float f5, float f6) {
        addTemplateReference(pdfIndirectReference, pdfName, (double) f, (double) f2, (double) f3, (double) f4, (double) f5, (double) f6);
    }

    /* access modifiers changed from: package-private */
    public void addTemplateReference(PdfIndirectReference pdfIndirectReference, PdfName pdfName, double d, double d2, double d3, double d4, double d5, double d6) {
        if (this.inText && isTagged()) {
            endText();
        }
        checkWriter();
        PdfName addXObject = getPageResources().addXObject(pdfName, pdfIndirectReference);
        this.content.append("q ");
        this.content.append(d).append(' ');
        this.content.append(d2).append(' ');
        this.content.append(d3).append(' ');
        this.content.append(d4).append(' ');
        this.content.append(d5).append(' ');
        this.content.append(d6).append(" cm ");
        this.content.append(addXObject.getBytes()).append(" Do Q").append_i(this.separator);
    }

    public void addTemplate(PdfTemplate pdfTemplate, float f, float f2) {
        addTemplate(pdfTemplate, 1.0f, 0.0f, 0.0f, 1.0f, f, f2);
    }

    public void addTemplate(PdfTemplate pdfTemplate, double d, double d2) {
        addTemplate(pdfTemplate, 1.0d, 0.0d, 0.0d, 1.0d, d, d2);
    }

    public void addTemplate(PdfTemplate pdfTemplate, float f, float f2, boolean z) {
        addTemplate(pdfTemplate, 1.0f, 0.0f, 0.0f, 1.0f, f, f2, z);
    }

    public void addTemplate(PdfTemplate pdfTemplate, double d, double d2, boolean z) {
        addTemplate(pdfTemplate, 1.0d, 0.0d, 0.0d, 1.0d, d, d2, z);
    }

    public void setCMYKColorFill(int i, int i2, int i3, int i4) {
        saveColor(new CMYKColor(i, i2, i3, i4), true);
        this.content.append(((float) (i & 255)) / 255.0f);
        this.content.append(' ');
        this.content.append(((float) (i2 & 255)) / 255.0f);
        this.content.append(' ');
        this.content.append(((float) (i3 & 255)) / 255.0f);
        this.content.append(' ');
        this.content.append(((float) (i4 & 255)) / 255.0f);
        this.content.append(" k").append_i(this.separator);
    }

    public void setCMYKColorStroke(int i, int i2, int i3, int i4) {
        saveColor(new CMYKColor(i, i2, i3, i4), false);
        this.content.append(((float) (i & 255)) / 255.0f);
        this.content.append(' ');
        this.content.append(((float) (i2 & 255)) / 255.0f);
        this.content.append(' ');
        this.content.append(((float) (i3 & 255)) / 255.0f);
        this.content.append(' ');
        this.content.append(((float) (i4 & 255)) / 255.0f);
        this.content.append(" K").append_i(this.separator);
    }

    public void setRGBColorFill(int i, int i2, int i3) {
        saveColor(new BaseColor(i, i2, i3), true);
        HelperRGB(((float) (i & 255)) / 255.0f, ((float) (i2 & 255)) / 255.0f, ((float) (i3 & 255)) / 255.0f);
        this.content.append(" rg").append_i(this.separator);
    }

    public void setRGBColorStroke(int i, int i2, int i3) {
        saveColor(new BaseColor(i, i2, i3), false);
        HelperRGB(((float) (i & 255)) / 255.0f, ((float) (i2 & 255)) / 255.0f, ((float) (i3 & 255)) / 255.0f);
        this.content.append(" RG").append_i(this.separator);
    }

    public void setColorStroke(BaseColor baseColor) {
        switch (ExtendedColor.getType(baseColor)) {
            case 1:
                setGrayStroke(((GrayColor) baseColor).getGray());
                break;
            case 2:
                CMYKColor cMYKColor = (CMYKColor) baseColor;
                setCMYKColorStrokeF(cMYKColor.getCyan(), cMYKColor.getMagenta(), cMYKColor.getYellow(), cMYKColor.getBlack());
                break;
            case 3:
                SpotColor spotColor = (SpotColor) baseColor;
                setColorStroke(spotColor.getPdfSpotColor(), spotColor.getTint());
                break;
            case 4:
                setPatternStroke(((PatternColor) baseColor).getPainter());
                break;
            case 5:
                setShadingStroke(((ShadingColor) baseColor).getPdfShadingPattern());
                break;
            case 6:
                DeviceNColor deviceNColor = (DeviceNColor) baseColor;
                setColorStroke(deviceNColor.getPdfDeviceNColor(), deviceNColor.getTints());
                break;
            case 7:
                LabColor labColor = (LabColor) baseColor;
                setColorStroke(labColor.getLabColorSpace(), labColor.getL(), labColor.getA(), labColor.getB());
                break;
            default:
                setRGBColorStroke(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue());
                break;
        }
        int alpha = baseColor.getAlpha();
        if (alpha < 255) {
            PdfGState pdfGState = new PdfGState();
            pdfGState.setStrokeOpacity(((float) alpha) / 255.0f);
            setGState(pdfGState);
        }
    }

    public void setColorFill(BaseColor baseColor) {
        switch (ExtendedColor.getType(baseColor)) {
            case 1:
                setGrayFill(((GrayColor) baseColor).getGray());
                break;
            case 2:
                CMYKColor cMYKColor = (CMYKColor) baseColor;
                setCMYKColorFillF(cMYKColor.getCyan(), cMYKColor.getMagenta(), cMYKColor.getYellow(), cMYKColor.getBlack());
                break;
            case 3:
                SpotColor spotColor = (SpotColor) baseColor;
                setColorFill(spotColor.getPdfSpotColor(), spotColor.getTint());
                break;
            case 4:
                setPatternFill(((PatternColor) baseColor).getPainter());
                break;
            case 5:
                setShadingFill(((ShadingColor) baseColor).getPdfShadingPattern());
                break;
            case 6:
                DeviceNColor deviceNColor = (DeviceNColor) baseColor;
                setColorFill(deviceNColor.getPdfDeviceNColor(), deviceNColor.getTints());
                break;
            case 7:
                LabColor labColor = (LabColor) baseColor;
                setColorFill(labColor.getLabColorSpace(), labColor.getL(), labColor.getA(), labColor.getB());
                break;
            default:
                setRGBColorFill(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue());
                break;
        }
        int alpha = baseColor.getAlpha();
        if (alpha < 255) {
            PdfGState pdfGState = new PdfGState();
            pdfGState.setFillOpacity(((float) alpha) / 255.0f);
            setGState(pdfGState);
        }
    }

    public void setColorFill(PdfSpotColor pdfSpotColor, float f) {
        checkWriter();
        this.state.colorDetails = this.writer.addSimple((ICachedColorSpace) pdfSpotColor);
        PdfName addColor = getPageResources().addColor(this.state.colorDetails.getColorSpaceName(), this.state.colorDetails.getIndirectReference());
        saveColor(new SpotColor(pdfSpotColor, f), true);
        this.content.append(addColor.getBytes()).append(" cs ").append(f).append(" scn").append_i(this.separator);
    }

    public void setColorFill(PdfDeviceNColor pdfDeviceNColor, float[] fArr) {
        checkWriter();
        this.state.colorDetails = this.writer.addSimple((ICachedColorSpace) pdfDeviceNColor);
        PdfName addColor = getPageResources().addColor(this.state.colorDetails.getColorSpaceName(), this.state.colorDetails.getIndirectReference());
        saveColor(new DeviceNColor(pdfDeviceNColor, fArr), true);
        this.content.append(addColor.getBytes()).append(" cs ");
        for (float f : fArr) {
            ByteBuffer byteBuffer = this.content;
            byteBuffer.append(f + " ");
        }
        this.content.append("scn").append_i(this.separator);
    }

    public void setColorFill(PdfLabColor pdfLabColor, float f, float f2, float f3) {
        checkWriter();
        this.state.colorDetails = this.writer.addSimple((ICachedColorSpace) pdfLabColor);
        PdfName addColor = getPageResources().addColor(this.state.colorDetails.getColorSpaceName(), this.state.colorDetails.getIndirectReference());
        saveColor(new LabColor(pdfLabColor, f, f2, f3), true);
        this.content.append(addColor.getBytes()).append(" cs ");
        ByteBuffer byteBuffer = this.content;
        byteBuffer.append(f + " " + f2 + " " + f3 + " ");
        this.content.append("scn").append_i(this.separator);
    }

    public void setColorStroke(PdfSpotColor pdfSpotColor, float f) {
        checkWriter();
        this.state.colorDetails = this.writer.addSimple((ICachedColorSpace) pdfSpotColor);
        PdfName addColor = getPageResources().addColor(this.state.colorDetails.getColorSpaceName(), this.state.colorDetails.getIndirectReference());
        saveColor(new SpotColor(pdfSpotColor, f), false);
        this.content.append(addColor.getBytes()).append(" CS ").append(f).append(" SCN").append_i(this.separator);
    }

    public void setColorStroke(PdfDeviceNColor pdfDeviceNColor, float[] fArr) {
        checkWriter();
        this.state.colorDetails = this.writer.addSimple((ICachedColorSpace) pdfDeviceNColor);
        PdfName addColor = getPageResources().addColor(this.state.colorDetails.getColorSpaceName(), this.state.colorDetails.getIndirectReference());
        saveColor(new DeviceNColor(pdfDeviceNColor, fArr), true);
        this.content.append(addColor.getBytes()).append(" CS ");
        for (float f : fArr) {
            ByteBuffer byteBuffer = this.content;
            byteBuffer.append(f + " ");
        }
        this.content.append("SCN").append_i(this.separator);
    }

    public void setColorStroke(PdfLabColor pdfLabColor, float f, float f2, float f3) {
        checkWriter();
        this.state.colorDetails = this.writer.addSimple((ICachedColorSpace) pdfLabColor);
        PdfName addColor = getPageResources().addColor(this.state.colorDetails.getColorSpaceName(), this.state.colorDetails.getIndirectReference());
        saveColor(new LabColor(pdfLabColor, f, f2, f3), true);
        this.content.append(addColor.getBytes()).append(" CS ");
        ByteBuffer byteBuffer = this.content;
        byteBuffer.append(f + " " + f2 + " " + f3 + " ");
        this.content.append("SCN").append_i(this.separator);
    }

    public void setPatternFill(PdfPatternPainter pdfPatternPainter) {
        if (pdfPatternPainter.isStencil()) {
            setPatternFill(pdfPatternPainter, pdfPatternPainter.getDefaultColor());
            return;
        }
        checkWriter();
        PdfName addPattern = getPageResources().addPattern(this.writer.addSimplePattern(pdfPatternPainter), pdfPatternPainter.getIndirectReference());
        saveColor(new PatternColor(pdfPatternPainter), true);
        this.content.append(PdfName.PATTERN.getBytes()).append(" cs ").append(addPattern.getBytes()).append(" scn").append_i(this.separator);
    }

    /* access modifiers changed from: package-private */
    public void outputColorNumbers(BaseColor baseColor, float f) {
        PdfWriter.checkPdfIsoConformance(this.writer, 1, baseColor);
        switch (ExtendedColor.getType(baseColor)) {
            case 0:
                this.content.append(((float) baseColor.getRed()) / 255.0f);
                this.content.append(' ');
                this.content.append(((float) baseColor.getGreen()) / 255.0f);
                this.content.append(' ');
                this.content.append(((float) baseColor.getBlue()) / 255.0f);
                return;
            case 1:
                this.content.append(((GrayColor) baseColor).getGray());
                return;
            case 2:
                CMYKColor cMYKColor = (CMYKColor) baseColor;
                this.content.append(cMYKColor.getCyan()).append(' ').append(cMYKColor.getMagenta());
                this.content.append(' ').append(cMYKColor.getYellow()).append(' ').append(cMYKColor.getBlack());
                return;
            case 3:
                this.content.append(f);
                return;
            default:
                throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.color.type", new Object[0]));
        }
    }

    public void setPatternFill(PdfPatternPainter pdfPatternPainter, BaseColor baseColor) {
        if (ExtendedColor.getType(baseColor) == 3) {
            setPatternFill(pdfPatternPainter, baseColor, ((SpotColor) baseColor).getTint());
        } else {
            setPatternFill(pdfPatternPainter, baseColor, 0.0f);
        }
    }

    public void setPatternFill(PdfPatternPainter pdfPatternPainter, BaseColor baseColor, float f) {
        checkWriter();
        if (!pdfPatternPainter.isStencil()) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("an.uncolored.pattern.was.expected", new Object[0]));
        }
        PageResources pageResources = getPageResources();
        PdfName addPattern = pageResources.addPattern(this.writer.addSimplePattern(pdfPatternPainter), pdfPatternPainter.getIndirectReference());
        ColorDetails addSimplePatternColorspace = this.writer.addSimplePatternColorspace(baseColor);
        PdfName addColor = pageResources.addColor(addSimplePatternColorspace.getColorSpaceName(), addSimplePatternColorspace.getIndirectReference());
        saveColor(new UncoloredPattern(pdfPatternPainter, baseColor, f), true);
        this.content.append(addColor.getBytes()).append(" cs").append_i(this.separator);
        outputColorNumbers(baseColor, f);
        this.content.append(' ').append(addPattern.getBytes()).append(" scn").append_i(this.separator);
    }

    public void setPatternStroke(PdfPatternPainter pdfPatternPainter, BaseColor baseColor) {
        if (ExtendedColor.getType(baseColor) == 3) {
            setPatternStroke(pdfPatternPainter, baseColor, ((SpotColor) baseColor).getTint());
        } else {
            setPatternStroke(pdfPatternPainter, baseColor, 0.0f);
        }
    }

    public void setPatternStroke(PdfPatternPainter pdfPatternPainter, BaseColor baseColor, float f) {
        checkWriter();
        if (!pdfPatternPainter.isStencil()) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("an.uncolored.pattern.was.expected", new Object[0]));
        }
        PageResources pageResources = getPageResources();
        PdfName addPattern = pageResources.addPattern(this.writer.addSimplePattern(pdfPatternPainter), pdfPatternPainter.getIndirectReference());
        ColorDetails addSimplePatternColorspace = this.writer.addSimplePatternColorspace(baseColor);
        PdfName addColor = pageResources.addColor(addSimplePatternColorspace.getColorSpaceName(), addSimplePatternColorspace.getIndirectReference());
        saveColor(new UncoloredPattern(pdfPatternPainter, baseColor, f), false);
        this.content.append(addColor.getBytes()).append(" CS").append_i(this.separator);
        outputColorNumbers(baseColor, f);
        this.content.append(' ').append(addPattern.getBytes()).append(" SCN").append_i(this.separator);
    }

    public void setPatternStroke(PdfPatternPainter pdfPatternPainter) {
        if (pdfPatternPainter.isStencil()) {
            setPatternStroke(pdfPatternPainter, pdfPatternPainter.getDefaultColor());
            return;
        }
        checkWriter();
        PdfName addPattern = getPageResources().addPattern(this.writer.addSimplePattern(pdfPatternPainter), pdfPatternPainter.getIndirectReference());
        saveColor(new PatternColor(pdfPatternPainter), false);
        this.content.append(PdfName.PATTERN.getBytes()).append(" CS ").append(addPattern.getBytes()).append(" SCN").append_i(this.separator);
    }

    public void paintShading(PdfShading pdfShading) {
        this.writer.addSimpleShading(pdfShading);
        PageResources pageResources = getPageResources();
        this.content.append(pageResources.addShading(pdfShading.getShadingName(), pdfShading.getShadingReference()).getBytes()).append(" sh").append_i(this.separator);
        ColorDetails colorDetails = pdfShading.getColorDetails();
        if (colorDetails != null) {
            pageResources.addColor(colorDetails.getColorSpaceName(), colorDetails.getIndirectReference());
        }
    }

    public void paintShading(PdfShadingPattern pdfShadingPattern) {
        paintShading(pdfShadingPattern.getShading());
    }

    public void setShadingFill(PdfShadingPattern pdfShadingPattern) {
        this.writer.addSimpleShadingPattern(pdfShadingPattern);
        PageResources pageResources = getPageResources();
        PdfName addPattern = pageResources.addPattern(pdfShadingPattern.getPatternName(), pdfShadingPattern.getPatternReference());
        saveColor(new ShadingColor(pdfShadingPattern), true);
        this.content.append(PdfName.PATTERN.getBytes()).append(" cs ").append(addPattern.getBytes()).append(" scn").append_i(this.separator);
        ColorDetails colorDetails = pdfShadingPattern.getColorDetails();
        if (colorDetails != null) {
            pageResources.addColor(colorDetails.getColorSpaceName(), colorDetails.getIndirectReference());
        }
    }

    public void setShadingStroke(PdfShadingPattern pdfShadingPattern) {
        this.writer.addSimpleShadingPattern(pdfShadingPattern);
        PageResources pageResources = getPageResources();
        PdfName addPattern = pageResources.addPattern(pdfShadingPattern.getPatternName(), pdfShadingPattern.getPatternReference());
        saveColor(new ShadingColor(pdfShadingPattern), false);
        this.content.append(PdfName.PATTERN.getBytes()).append(" CS ").append(addPattern.getBytes()).append(" SCN").append_i(this.separator);
        ColorDetails colorDetails = pdfShadingPattern.getColorDetails();
        if (colorDetails != null) {
            pageResources.addColor(colorDetails.getColorSpaceName(), colorDetails.getIndirectReference());
        }
    }

    /* access modifiers changed from: protected */
    public void checkWriter() {
        if (this.writer == null) {
            throw new NullPointerException(MessageLocalization.getComposedMessage("the.writer.in.pdfcontentbyte.is.null", new Object[0]));
        }
    }

    public void showText(PdfTextArray pdfTextArray) {
        checkState();
        if (!this.inText && isTagged()) {
            beginText(true);
        }
        if (this.state.fontDetails == null) {
            throw new NullPointerException(MessageLocalization.getComposedMessage("font.and.size.must.be.set.before.writing.any.text", new Object[0]));
        }
        this.content.append("[");
        Iterator<Object> it = pdfTextArray.getArrayList().iterator();
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof String) {
                    String str = (String) next;
                    showText2(str);
                    updateTx(str, 0.0f);
                } else {
                    if (z) {
                        this.content.append(' ');
                    } else {
                        z = true;
                    }
                    Float f = (Float) next;
                    this.content.append(f.floatValue());
                    updateTx("", f.floatValue());
                }
            }
            this.content.append("]TJ").append_i(this.separator);
            return;
        }
    }

    public PdfWriter getPdfWriter() {
        return this.writer;
    }

    public PdfDocument getPdfDocument() {
        return this.pdf;
    }

    public void localGoto(String str, float f, float f2, float f3, float f4) {
        this.pdf.localGoto(str, f, f2, f3, f4);
    }

    public boolean localDestination(String str, PdfDestination pdfDestination) {
        return this.pdf.localDestination(str, pdfDestination);
    }

    public PdfContentByte getDuplicate() {
        PdfContentByte pdfContentByte = new PdfContentByte(this.writer);
        pdfContentByte.duplicatedFrom = this;
        return pdfContentByte;
    }

    public PdfContentByte getDuplicate(boolean z) {
        PdfContentByte duplicate = getDuplicate();
        if (z) {
            duplicate.state = this.state;
            duplicate.stateList = this.stateList;
        }
        return duplicate;
    }

    public void inheritGraphicState(PdfContentByte pdfContentByte) {
        this.state = pdfContentByte.state;
        this.stateList = pdfContentByte.stateList;
    }

    public void remoteGoto(String str, String str2, float f, float f2, float f3, float f4) {
        this.pdf.remoteGoto(str, str2, f, f2, f3, f4);
    }

    public void remoteGoto(String str, int i, float f, float f2, float f3, float f4) {
        this.pdf.remoteGoto(str, i, f, f2, f3, f4);
    }

    public void roundRectangle(float f, float f2, float f3, float f4, float f5) {
        roundRectangle((double) f, (double) f2, (double) f3, (double) f4, (double) f5);
    }

    public void roundRectangle(double d, double d2, double d3, double d4, double d5) {
        double d6;
        double d7;
        double d8;
        double d9 = d3;
        double d10 = d4;
        double d11 = d5;
        if (d9 < 0.0d) {
            double d12 = d + d9;
            d9 = -d9;
            d6 = d12;
        } else {
            d6 = d;
        }
        if (d10 < 0.0d) {
            d7 = -d10;
            d8 = d2 + d10;
        } else {
            d8 = d2;
            d7 = d10;
        }
        double d13 = d11 < 0.0d ? -d11 : d11;
        double d14 = d6 + d13;
        moveTo(d14, d8);
        double d15 = d6 + d9;
        double d16 = d15 - d13;
        lineTo(d16, d8);
        double d17 = d13 * ((double) 0.4477f);
        double d18 = d15 - d17;
        double d19 = d8 + d17;
        double d20 = d8 + d13;
        double d21 = d15;
        double d22 = d6;
        double d23 = d14;
        double d24 = d8;
        curveTo(d18, d8, d15, d19, d21, d20);
        double d25 = d24 + d7;
        double d26 = d25 - d13;
        double d27 = d21;
        lineTo(d27, d26);
        double d28 = d25 - d17;
        double d29 = d25;
        curveTo(d27, d28, d18, d25, d16, d25);
        lineTo(d23, d25);
        double d30 = d22 + d17;
        curveTo(d30, d25, d22, d28, d22, d26);
        double d31 = d22;
        lineTo(d31, d20);
        curveTo(d31, d19, d30, d24, d23, d24);
    }

    public void setAction(PdfAction pdfAction, float f, float f2, float f3, float f4) {
        this.pdf.setAction(pdfAction, f, f2, f3, f4);
    }

    public void setLiteral(String str) {
        this.content.append(str);
    }

    public void setLiteral(char c) {
        this.content.append(c);
    }

    public void setLiteral(float f) {
        this.content.append(f);
    }

    /* access modifiers changed from: package-private */
    public void checkNoPattern(PdfTemplate pdfTemplate) {
        if (pdfTemplate.getType() == 3) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.use.of.a.pattern.a.template.was.expected", new Object[0]));
        }
    }

    public void drawRadioField(float f, float f2, float f3, float f4, boolean z) {
        drawRadioField((double) f, (double) f2, (double) f3, (double) f4, z);
    }

    public void drawRadioField(double d, double d2, double d3, double d4, boolean z) {
        double d5;
        double d6;
        double d7;
        double d8;
        if (d > d3) {
            d5 = d;
            d6 = d3;
        } else {
            d6 = d;
            d5 = d3;
        }
        if (d2 > d4) {
            d7 = d2;
            d8 = d4;
        } else {
            d8 = d2;
            d7 = d4;
        }
        saveState();
        setLineWidth(1.0f);
        setLineCap(1);
        setColorStroke(new BaseColor((int) PsExtractor.AUDIO_STREAM, (int) PsExtractor.AUDIO_STREAM, (int) PsExtractor.AUDIO_STREAM));
        double d9 = d6;
        arc(d6 + 1.0d, d8 + 1.0d, d5 - 1.0d, d7 - 1.0d, 0.0d, 360.0d);
        stroke();
        setLineWidth(1.0f);
        setLineCap(1);
        setColorStroke(new BaseColor(160, 160, 160));
        arc(d9 + 0.5d, d8 + 0.5d, d5 - 0.5d, d7 - 0.5d, 45.0d, 180.0d);
        stroke();
        setLineWidth(1.0f);
        setLineCap(1);
        setColorStroke(new BaseColor(0, 0, 0));
        arc(d9 + 1.5d, d8 + 1.5d, d5 - 1.5d, d7 - 1.5d, 45.0d, 180.0d);
        stroke();
        if (z) {
            setLineWidth(1.0f);
            setLineCap(1);
            setColorFill(new BaseColor(0, 0, 0));
            arc(d9 + 4.0d, d8 + 4.0d, d5 - 4.0d, d7 - 4.0d, 0.0d, 360.0d);
            fill();
        }
        restoreState();
    }

    public void drawTextField(float f, float f2, float f3, float f4) {
        drawTextField((double) f, (double) f2, (double) f3, (double) f4);
    }

    public void drawTextField(double d, double d2, double d3, double d4) {
        double d5;
        double d6;
        double d7;
        double d8;
        if (d > d3) {
            d5 = d;
            d6 = d3;
        } else {
            d6 = d;
            d5 = d3;
        }
        if (d2 > d4) {
            d7 = d2;
            d8 = d4;
        } else {
            d8 = d2;
            d7 = d4;
        }
        saveState();
        setColorStroke(new BaseColor((int) PsExtractor.AUDIO_STREAM, (int) PsExtractor.AUDIO_STREAM, (int) PsExtractor.AUDIO_STREAM));
        setLineWidth(1.0f);
        setLineCap(0);
        double d9 = d5 - d6;
        double d10 = d7 - d8;
        double d11 = d5;
        rectangle(d6, d8, d9, d10);
        stroke();
        setLineWidth(1.0f);
        setLineCap(0);
        setColorFill(new BaseColor(255, 255, 255));
        rectangle(d6 + 0.5d, d8 + 0.5d, d9 - 1.0d, d10 - 1.0d);
        fill();
        setColorStroke(new BaseColor((int) PsExtractor.AUDIO_STREAM, (int) PsExtractor.AUDIO_STREAM, (int) PsExtractor.AUDIO_STREAM));
        setLineWidth(1.0f);
        setLineCap(0);
        double d12 = d6 + 1.0d;
        double d13 = d8 + 1.5d;
        moveTo(d12, d13);
        double d14 = d11 - 1.5d;
        lineTo(d14, d13);
        double d15 = d7 - 1.0d;
        lineTo(d14, d15);
        stroke();
        setColorStroke(new BaseColor(160, 160, 160));
        setLineWidth(1.0f);
        setLineCap(0);
        moveTo(d12, d8 + 1.0d);
        lineTo(d12, d15);
        lineTo(d11 - 1.0d, d15);
        stroke();
        setColorStroke(new BaseColor(0, 0, 0));
        setLineWidth(1.0f);
        setLineCap(0);
        double d16 = d6 + 2.0d;
        moveTo(d16, d8 + 2.0d);
        double d17 = d7 - 2.0d;
        lineTo(d16, d17);
        lineTo(d11 - 2.0d, d17);
        stroke();
        restoreState();
    }

    public void drawButton(float f, float f2, float f3, float f4, String str, BaseFont baseFont, float f5) {
        drawButton((double) f, (double) f2, (double) f3, (double) f4, str, baseFont, f5);
    }

    public void drawButton(double d, double d2, double d3, double d4, String str, BaseFont baseFont, float f) {
        double d5;
        double d6;
        double d7;
        double d8;
        float f2 = f;
        if (d > d3) {
            d5 = d;
            d6 = d3;
        } else {
            d6 = d;
            d5 = d3;
        }
        if (d2 > d4) {
            d7 = d2;
            d8 = d4;
        } else {
            d8 = d2;
            d7 = d4;
        }
        saveState();
        setColorStroke(new BaseColor(0, 0, 0));
        setLineWidth(1.0f);
        setLineCap(0);
        double d9 = d5 - d6;
        double d10 = d7 - d8;
        rectangle(d6, d8, d9, d10);
        stroke();
        setLineWidth(1.0f);
        setLineCap(0);
        setColorFill(new BaseColor((int) PsExtractor.AUDIO_STREAM, (int) PsExtractor.AUDIO_STREAM, (int) PsExtractor.AUDIO_STREAM));
        rectangle(d6 + 0.5d, d8 + 0.5d, d9 - 1.0d, d10 - 1.0d);
        fill();
        setColorStroke(new BaseColor(255, 255, 255));
        setLineWidth(1.0f);
        setLineCap(0);
        double d11 = d6 + 1.0d;
        double d12 = d8 + 1.0d;
        moveTo(d11, d12);
        double d13 = d7 - 1.0d;
        lineTo(d11, d13);
        double d14 = d5 - 1.0d;
        lineTo(d14, d13);
        stroke();
        setColorStroke(new BaseColor(160, 160, 160));
        setLineWidth(1.0f);
        setLineCap(0);
        moveTo(d11, d12);
        lineTo(d14, d12);
        lineTo(d14, d13);
        stroke();
        resetRGBColorFill();
        beginText();
        float f3 = f;
        setFontAndSize(baseFont, f3);
        showTextAligned(1, str, (float) (d6 + (d9 / 2.0d)), (float) (d8 + ((d10 - ((double) f3)) / 2.0d)), 0.0f);
        endText();
        restoreState();
    }

    /* access modifiers changed from: package-private */
    public PageResources getPageResources() {
        return this.pdf.getPageResources();
    }

    public void setGState(PdfGState pdfGState) {
        PdfObject[] addSimpleExtGState = this.writer.addSimpleExtGState(pdfGState);
        PdfName addExtGState = getPageResources().addExtGState((PdfName) addSimpleExtGState[0], (PdfIndirectReference) addSimpleExtGState[1]);
        this.state.extGState = pdfGState;
        this.content.append(addExtGState.getBytes()).append(" gs").append_i(this.separator);
    }

    public void beginLayer(PdfOCG pdfOCG) {
        int i = 0;
        if (!(pdfOCG instanceof PdfLayer) || ((PdfLayer) pdfOCG).getTitle() == null) {
            if (this.layerDepth == null) {
                this.layerDepth = new ArrayList<>();
            }
            if (pdfOCG instanceof PdfLayerMembership) {
                this.layerDepth.add(1);
                beginLayer2(pdfOCG);
                return;
            }
            for (PdfLayer pdfLayer = (PdfLayer) pdfOCG; pdfLayer != null; pdfLayer = pdfLayer.getParent()) {
                if (pdfLayer.getTitle() == null) {
                    beginLayer2(pdfLayer);
                    i++;
                }
            }
            this.layerDepth.add(Integer.valueOf(i));
            return;
        }
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("a.title.is.not.a.layer", new Object[0]));
    }

    private void beginLayer2(PdfOCG pdfOCG) {
        this.content.append("/OC ").append(getPageResources().addProperty((PdfName) this.writer.addSimpleProperty(pdfOCG, pdfOCG.getRef())[0], pdfOCG.getRef()).getBytes()).append(" BDC").append_i(this.separator);
    }

    public void endLayer() {
        if (this.layerDepth == null || this.layerDepth.isEmpty()) {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.layer.operators", new Object[0]));
        }
        int intValue = this.layerDepth.get(this.layerDepth.size() - 1).intValue();
        this.layerDepth.remove(this.layerDepth.size() - 1);
        while (true) {
            int i = intValue - 1;
            if (intValue > 0) {
                this.content.append("EMC").append_i(this.separator);
                intValue = i;
            } else {
                return;
            }
        }
    }

    public void transform(AffineTransform affineTransform) {
        if (this.inText && isTagged()) {
            endText();
        }
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        this.state.CTM.concatenate(affineTransform);
        this.content.append(dArr[0]).append(' ').append(dArr[1]).append(' ').append(dArr[2]).append(' ');
        this.content.append(dArr[3]).append(' ').append(dArr[4]).append(' ').append(dArr[5]).append(" cm").append_i(this.separator);
    }

    /* access modifiers changed from: package-private */
    public void addAnnotation(PdfAnnotation pdfAnnotation) {
        boolean z = isTagged() && pdfAnnotation.getRole() != null && (!(pdfAnnotation instanceof PdfFormField) || ((PdfFormField) pdfAnnotation).getKids() == null);
        if (z) {
            openMCBlock(pdfAnnotation);
        }
        this.writer.addAnnotation(pdfAnnotation);
        if (z) {
            PdfStructureElement structElement = this.pdf.getStructElement(pdfAnnotation.getId());
            if (structElement != null) {
                int structParentIndex = this.pdf.getStructParentIndex(pdfAnnotation);
                pdfAnnotation.put(PdfName.STRUCTPARENT, new PdfNumber(structParentIndex));
                structElement.setAnnotation(pdfAnnotation, getCurrentPage());
                this.writer.getStructureTreeRoot().setAnnotationMark(structParentIndex, structElement.getReference());
            }
            closeMCBlock(pdfAnnotation);
        }
    }

    public void addAnnotation(PdfAnnotation pdfAnnotation, boolean z) {
        if (z && this.state.CTM.getType() != 0) {
            pdfAnnotation.applyCTM(this.state.CTM);
        }
        addAnnotation(pdfAnnotation);
    }

    public void setDefaultColorspace(PdfName pdfName, PdfObject pdfObject) {
        getPageResources().addDefaultColor(pdfName, pdfObject);
    }

    public void beginMarkedContentSequence(PdfStructureElement pdfStructureElement) {
        beginMarkedContentSequence(pdfStructureElement, (String) null);
    }

    private void beginMarkedContentSequence(PdfStructureElement pdfStructureElement, String str) {
        PdfArray pdfArray;
        PdfObject pdfObject = pdfStructureElement.get(PdfName.K);
        int[] structParentIndexAndNextMarkPoint = this.pdf.getStructParentIndexAndNextMarkPoint(getCurrentPage());
        int i = structParentIndexAndNextMarkPoint[0];
        int i2 = structParentIndexAndNextMarkPoint[1];
        if (pdfObject != null) {
            if (pdfObject.isNumber()) {
                pdfArray = new PdfArray();
                pdfArray.add(pdfObject);
                pdfStructureElement.put(PdfName.K, pdfArray);
            } else if (pdfObject.isArray()) {
                pdfArray = (PdfArray) pdfObject;
            } else {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("unknown.object.at.k.1", pdfObject.getClass().toString()));
            }
            if (pdfArray.getAsNumber(0) != null) {
                PdfDictionary pdfDictionary = new PdfDictionary(PdfName.MCR);
                pdfDictionary.put(PdfName.PG, getCurrentPage());
                pdfDictionary.put(PdfName.MCID, new PdfNumber(i2));
                pdfArray.add((PdfObject) pdfDictionary);
            }
            pdfStructureElement.setPageMark(this.pdf.getStructParentIndex(getCurrentPage()), -1);
        } else {
            pdfStructureElement.setPageMark(i, i2);
            pdfStructureElement.put(PdfName.PG, getCurrentPage());
        }
        setMcDepth(getMcDepth() + 1);
        int size = this.content.size();
        this.content.append(pdfStructureElement.get(PdfName.S).getBytes()).append(" <</MCID ").append(i2);
        if (str != null) {
            this.content.append("/E (").append(str).append(")");
        }
        this.content.append(">> BDC").append_i(this.separator);
        this.markedContentSize += this.content.size() - size;
    }

    /* access modifiers changed from: protected */
    public PdfIndirectReference getCurrentPage() {
        return this.writer.getCurrentPage();
    }

    public void endMarkedContentSequence() {
        if (getMcDepth() == 0) {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.begin.end.marked.content.operators", new Object[0]));
        }
        int size = this.content.size();
        setMcDepth(getMcDepth() - 1);
        this.content.append("EMC").append_i(this.separator);
        this.markedContentSize += this.content.size() - size;
    }

    public void beginMarkedContentSequence(PdfName pdfName, PdfDictionary pdfDictionary, boolean z) {
        PdfObject[] pdfObjectArr;
        int size = this.content.size();
        if (pdfDictionary == null) {
            this.content.append(pdfName.getBytes()).append(" BMC").append_i(this.separator);
            setMcDepth(getMcDepth() + 1);
        } else {
            this.content.append(pdfName.getBytes()).append(' ');
            if (z) {
                try {
                    pdfDictionary.toPdf(this.writer, this.content);
                } catch (Exception e) {
                    throw new ExceptionConverter(e);
                }
            } else {
                if (this.writer.propertyExists(pdfDictionary)) {
                    pdfObjectArr = this.writer.addSimpleProperty(pdfDictionary, (PdfIndirectReference) null);
                } else {
                    pdfObjectArr = this.writer.addSimpleProperty(pdfDictionary, this.writer.getPdfIndirectReference());
                }
                this.content.append(getPageResources().addProperty((PdfName) pdfObjectArr[0], (PdfIndirectReference) pdfObjectArr[1]).getBytes());
            }
            this.content.append(" BDC").append_i(this.separator);
            setMcDepth(getMcDepth() + 1);
        }
        this.markedContentSize += this.content.size() - size;
    }

    public void beginMarkedContentSequence(PdfName pdfName) {
        beginMarkedContentSequence(pdfName, (PdfDictionary) null, false);
    }

    public void sanityCheck() {
        if (getMcDepth() != 0) {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.marked.content.operators", new Object[0]));
        }
        if (this.inText) {
            if (isTagged()) {
                endText();
            } else {
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.begin.end.text.operators", new Object[0]));
            }
        }
        if (this.layerDepth != null && !this.layerDepth.isEmpty()) {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.layer.operators", new Object[0]));
        } else if (!this.stateList.isEmpty()) {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.save.restore.state.operators", new Object[0]));
        }
    }

    public void openMCBlock(IAccessibleElement iAccessibleElement) {
        if (isTagged()) {
            ensureDocumentTagIsOpen();
            if (iAccessibleElement != null && !getMcElements().contains(iAccessibleElement)) {
                PdfStructureElement openMCBlockInt = openMCBlockInt(iAccessibleElement);
                getMcElements().add(iAccessibleElement);
                if (openMCBlockInt != null) {
                    this.pdf.saveStructElement(iAccessibleElement.getId(), openMCBlockInt);
                }
            }
        }
    }

    private PdfDictionary getParentStructureElement() {
        PdfStructureElement structElement = getMcElements().size() > 0 ? this.pdf.getStructElement(getMcElements().get(getMcElements().size() - 1).getId()) : null;
        return structElement == null ? this.writer.getStructureTreeRoot() : structElement;
    }

    private PdfStructureElement openMCBlockInt(IAccessibleElement iAccessibleElement) {
        PdfStructureElement pdfStructureElement;
        PdfDictionary pdfDictionary = null;
        if (isTagged()) {
            this.writer.checkElementRole(iAccessibleElement, getMcElements().size() > 0 ? getMcElements().get(getMcElements().size() - 1) : null);
            if (iAccessibleElement.getRole() != null) {
                if (!PdfName.ARTIFACT.equals(iAccessibleElement.getRole())) {
                    pdfStructureElement = this.pdf.getStructElement(iAccessibleElement.getId());
                    if (pdfStructureElement == null) {
                        pdfStructureElement = new PdfStructureElement(getParentStructureElement(), iAccessibleElement.getRole(), iAccessibleElement.getId());
                    }
                } else {
                    pdfStructureElement = null;
                }
                if (PdfName.ARTIFACT.equals(iAccessibleElement.getRole())) {
                    HashMap<PdfName, PdfObject> accessibleAttributes = iAccessibleElement.getAccessibleAttributes();
                    if (accessibleAttributes != null && !accessibleAttributes.isEmpty()) {
                        pdfDictionary = new PdfDictionary();
                        for (Map.Entry next : accessibleAttributes.entrySet()) {
                            pdfDictionary.put((PdfName) next.getKey(), (PdfObject) next.getValue());
                        }
                    }
                    boolean z = this.inText;
                    if (this.inText) {
                        endText();
                    }
                    beginMarkedContentSequence(iAccessibleElement.getRole(), pdfDictionary, true);
                    if (!z) {
                        return pdfStructureElement;
                    }
                    beginText(true);
                    return pdfStructureElement;
                } else if (!this.writer.needToBeMarkedInContent(iAccessibleElement)) {
                    return pdfStructureElement;
                } else {
                    boolean z2 = this.inText;
                    if (this.inText) {
                        endText();
                    }
                    if (iAccessibleElement.getAccessibleAttributes() == null || iAccessibleElement.getAccessibleAttribute(PdfName.E) == null) {
                        beginMarkedContentSequence(pdfStructureElement);
                    } else {
                        beginMarkedContentSequence(pdfStructureElement, iAccessibleElement.getAccessibleAttribute(PdfName.E).toString());
                        iAccessibleElement.setAccessibleAttribute(PdfName.E, (PdfObject) null);
                    }
                    if (!z2) {
                        return pdfStructureElement;
                    }
                    beginText(true);
                    return pdfStructureElement;
                }
            }
        }
        return null;
    }

    public void closeMCBlock(IAccessibleElement iAccessibleElement) {
        if (isTagged() && iAccessibleElement != null && getMcElements().contains(iAccessibleElement)) {
            closeMCBlockInt(iAccessibleElement);
            getMcElements().remove(iAccessibleElement);
        }
    }

    private void closeMCBlockInt(IAccessibleElement iAccessibleElement) {
        if (isTagged() && iAccessibleElement.getRole() != null) {
            PdfStructureElement structElement = this.pdf.getStructElement(iAccessibleElement.getId());
            if (structElement != null) {
                structElement.writeAttributes(iAccessibleElement);
            }
            if (this.writer.needToBeMarkedInContent(iAccessibleElement)) {
                boolean z = this.inText;
                if (this.inText) {
                    endText();
                }
                endMarkedContentSequence();
                if (z) {
                    beginText(true);
                }
            }
        }
    }

    private void ensureDocumentTagIsOpen() {
        if (this.pdf.openMCDocument) {
            this.pdf.openMCDocument = false;
            this.writer.getDirectContentUnder().openMCBlock(this.pdf);
        }
    }

    /* access modifiers changed from: protected */
    public ArrayList<IAccessibleElement> saveMCBlocks() {
        ArrayList<IAccessibleElement> arrayList = new ArrayList<>();
        if (isTagged()) {
            arrayList = getMcElements();
            for (int i = 0; i < arrayList.size(); i++) {
                closeMCBlockInt(arrayList.get(i));
            }
            setMcElements(new ArrayList());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void restoreMCBlocks(ArrayList<IAccessibleElement> arrayList) {
        if (isTagged() && arrayList != null) {
            setMcElements(arrayList);
            for (int i = 0; i < getMcElements().size(); i++) {
                openMCBlockInt(getMcElements().get(i));
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getMcDepth() {
        if (this.duplicatedFrom != null) {
            return this.duplicatedFrom.getMcDepth();
        }
        return this.mcDepth;
    }

    /* access modifiers changed from: protected */
    public void setMcDepth(int i) {
        if (this.duplicatedFrom != null) {
            this.duplicatedFrom.setMcDepth(i);
        } else {
            this.mcDepth = i;
        }
    }

    /* access modifiers changed from: protected */
    public ArrayList<IAccessibleElement> getMcElements() {
        if (this.duplicatedFrom != null) {
            return this.duplicatedFrom.getMcElements();
        }
        return this.mcElements;
    }

    /* access modifiers changed from: protected */
    public void setMcElements(ArrayList<IAccessibleElement> arrayList) {
        if (this.duplicatedFrom != null) {
            this.duplicatedFrom.setMcElements(arrayList);
        } else {
            this.mcElements = arrayList;
        }
    }

    /* access modifiers changed from: protected */
    public void updateTx(String str, float f) {
        this.state.tx += getEffectiveStringWidth(str, false, f);
    }

    private void saveColor(BaseColor baseColor, boolean z) {
        if (z) {
            this.state.colorFill = baseColor;
        } else {
            this.state.colorStroke = baseColor;
        }
    }

    static class UncoloredPattern extends PatternColor {
        protected BaseColor color;
        protected float tint;

        protected UncoloredPattern(PdfPatternPainter pdfPatternPainter, BaseColor baseColor, float f) {
            super(pdfPatternPainter);
            this.color = baseColor;
            this.tint = f;
        }

        public boolean equals(Object obj) {
            if (obj instanceof UncoloredPattern) {
                UncoloredPattern uncoloredPattern = (UncoloredPattern) obj;
                return uncoloredPattern.painter.equals(this.painter) && uncoloredPattern.color.equals(this.color) && uncoloredPattern.tint == this.tint;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean getInText() {
        return this.inText;
    }

    /* access modifiers changed from: protected */
    public void checkState() {
        boolean z;
        boolean z2 = false;
        if (this.state.textRenderMode == 0) {
            z = false;
            z2 = true;
        } else if (this.state.textRenderMode == 1) {
            z = true;
        } else if (this.state.textRenderMode == 2) {
            z = true;
            z2 = true;
        } else {
            z = false;
        }
        if (z2) {
            PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorFill);
        }
        if (z) {
            PdfWriter.checkPdfIsoConformance(this.writer, 1, this.state.colorStroke);
        }
        PdfWriter.checkPdfIsoConformance(this.writer, 6, this.state.extGState);
    }
}
