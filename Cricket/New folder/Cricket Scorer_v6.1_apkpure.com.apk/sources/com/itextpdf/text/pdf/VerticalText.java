package com.itextpdf.text.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.error_messages.MessageLocalization;
import java.util.ArrayList;

public class VerticalText {
    public static final int NO_MORE_COLUMN = 2;
    public static final int NO_MORE_TEXT = 1;
    protected int alignment = 0;
    protected ArrayList<PdfChunk> chunks = new ArrayList<>();
    private Float curCharSpace = Float.valueOf(0.0f);
    protected int currentChunkMarker = -1;
    protected PdfChunk currentStandbyChunk;
    protected float height;
    protected float leading;
    protected int maxLines;
    protected String splittedChunkText;
    protected float startX;
    protected float startY;
    protected PdfContentByte text;

    public VerticalText(PdfContentByte pdfContentByte) {
        this.text = pdfContentByte;
    }

    public void addText(Phrase phrase) {
        for (Chunk pdfChunk : phrase.getChunks()) {
            this.chunks.add(new PdfChunk(pdfChunk, (PdfAction) null));
        }
    }

    public void addText(Chunk chunk) {
        this.chunks.add(new PdfChunk(chunk, (PdfAction) null));
    }

    public void setVerticalLayout(float f, float f2, float f3, int i, float f4) {
        this.startX = f;
        this.startY = f2;
        this.height = f3;
        this.maxLines = i;
        setLeading(f4);
    }

    public void setLeading(float f) {
        this.leading = f;
    }

    public float getLeading() {
        return this.leading;
    }

    /* access modifiers changed from: protected */
    public PdfLine createLine(float f) {
        if (this.chunks.isEmpty()) {
            return null;
        }
        this.splittedChunkText = null;
        this.currentStandbyChunk = null;
        PdfLine pdfLine = new PdfLine(0.0f, f, this.alignment, 0.0f);
        int i = 0;
        while (true) {
            this.currentChunkMarker = i;
            if (this.currentChunkMarker >= this.chunks.size()) {
                return pdfLine;
            }
            PdfChunk pdfChunk = this.chunks.get(this.currentChunkMarker);
            String pdfChunk2 = pdfChunk.toString();
            this.currentStandbyChunk = pdfLine.add(pdfChunk);
            if (this.currentStandbyChunk != null) {
                this.splittedChunkText = pdfChunk.toString();
                pdfChunk.setValue(pdfChunk2);
                return pdfLine;
            }
            i = this.currentChunkMarker + 1;
        }
    }

    /* access modifiers changed from: protected */
    public void shortenChunkArray() {
        if (this.currentChunkMarker >= 0) {
            if (this.currentChunkMarker >= this.chunks.size()) {
                this.chunks.clear();
                return;
            }
            this.chunks.get(this.currentChunkMarker).setValue(this.splittedChunkText);
            this.chunks.set(this.currentChunkMarker, this.currentStandbyChunk);
            for (int i = this.currentChunkMarker - 1; i >= 0; i--) {
                this.chunks.remove(i);
            }
        }
    }

    public int go() {
        return go(false);
    }

    public int go(boolean z) {
        PdfContentByte pdfContentByte;
        int i;
        boolean z2 = false;
        if (this.text != null) {
            pdfContentByte = this.text.getDuplicate();
        } else if (!z) {
            throw new NullPointerException(MessageLocalization.getComposedMessage("verticaltext.go.with.simulate.eq.eq.false.and.text.eq.eq.null", new Object[0]));
        } else {
            pdfContentByte = null;
        }
        while (true) {
            i = 1;
            if (this.maxLines <= 0) {
                i = 2;
                if (this.chunks.isEmpty()) {
                    i = 3;
                }
            } else if (this.chunks.isEmpty()) {
                break;
            } else {
                PdfLine createLine = createLine(this.height);
                if (!z && !z2) {
                    this.text.beginText();
                    z2 = true;
                }
                shortenChunkArray();
                if (!z) {
                    this.text.setTextMatrix(this.startX, this.startY - createLine.indentLeft());
                    writeLine(createLine, this.text, pdfContentByte);
                }
                this.maxLines--;
                this.startX -= this.leading;
            }
        }
        if (z2) {
            this.text.endText();
            this.text.add(pdfContentByte);
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0006 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeLine(com.itextpdf.text.pdf.PdfLine r11, com.itextpdf.text.pdf.PdfContentByte r12, com.itextpdf.text.pdf.PdfContentByte r13) {
        /*
            r10 = this;
            java.util.Iterator r11 = r11.iterator()
            r13 = 0
            r0 = r13
        L_0x0006:
            boolean r1 = r11.hasNext()
            if (r1 == 0) goto L_0x00c2
            java.lang.Object r1 = r11.next()
            com.itextpdf.text.pdf.PdfChunk r1 = (com.itextpdf.text.pdf.PdfChunk) r1
            boolean r2 = r1.isImage()
            if (r2 != 0) goto L_0x0031
            com.itextpdf.text.pdf.PdfFont r2 = r1.font()
            int r2 = r2.compareTo((com.itextpdf.text.pdf.PdfFont) r0)
            if (r2 == 0) goto L_0x0031
            com.itextpdf.text.pdf.PdfFont r0 = r1.font()
            com.itextpdf.text.pdf.BaseFont r2 = r0.getFont()
            float r3 = r0.size()
            r12.setFontAndSize(r2, r3)
        L_0x0031:
            java.lang.String r2 = "TEXTRENDERMODE"
            java.lang.Object r2 = r1.getAttribute(r2)
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            com.itextpdf.text.BaseColor r3 = r1.color()
            r4 = 0
            r5 = 1065353216(0x3f800000, float:1.0)
            if (r2 == 0) goto L_0x0076
            r6 = r2[r4]
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r6 = r6 & 3
            if (r6 == 0) goto L_0x0051
            r12.setTextRenderingMode(r6)
        L_0x0051:
            r7 = 2
            r8 = 1
            if (r6 == r8) goto L_0x005a
            if (r6 != r7) goto L_0x0058
            goto L_0x005a
        L_0x0058:
            r2 = r13
            goto L_0x0078
        L_0x005a:
            r8 = r2[r8]
            java.lang.Float r8 = (java.lang.Float) r8
            float r8 = r8.floatValue()
            int r9 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r9 == 0) goto L_0x0069
            r12.setLineWidth((float) r8)
        L_0x0069:
            r2 = r2[r7]
            com.itextpdf.text.BaseColor r2 = (com.itextpdf.text.BaseColor) r2
            if (r2 != 0) goto L_0x0070
            r2 = r3
        L_0x0070:
            if (r2 == 0) goto L_0x0079
            r12.setColorStroke(r2)
            goto L_0x0079
        L_0x0076:
            r2 = r13
            r6 = r4
        L_0x0078:
            r8 = r5
        L_0x0079:
            java.lang.String r7 = "CHAR_SPACING"
            java.lang.Object r7 = r1.getAttribute(r7)
            java.lang.Float r7 = (java.lang.Float) r7
            if (r7 == 0) goto L_0x009e
            java.lang.Float r9 = r10.curCharSpace
            boolean r9 = r9.equals(r7)
            if (r9 != 0) goto L_0x009e
            float r7 = r7.floatValue()
            java.lang.Float r7 = java.lang.Float.valueOf(r7)
            r10.curCharSpace = r7
            java.lang.Float r7 = r10.curCharSpace
            float r7 = r7.floatValue()
            r12.setCharacterSpacing(r7)
        L_0x009e:
            if (r3 == 0) goto L_0x00a3
            r12.setColorFill(r3)
        L_0x00a3:
            java.lang.String r1 = r1.toString()
            r12.showText((java.lang.String) r1)
            if (r3 == 0) goto L_0x00af
            r12.resetRGBColorFill()
        L_0x00af:
            if (r6 == 0) goto L_0x00b4
            r12.setTextRenderingMode(r4)
        L_0x00b4:
            if (r2 == 0) goto L_0x00b9
            r12.resetRGBColorStroke()
        L_0x00b9:
            int r1 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r1 == 0) goto L_0x0006
            r12.setLineWidth((float) r5)
            goto L_0x0006
        L_0x00c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.VerticalText.writeLine(com.itextpdf.text.pdf.PdfLine, com.itextpdf.text.pdf.PdfContentByte, com.itextpdf.text.pdf.PdfContentByte):void");
    }

    public void setOrigin(float f, float f2) {
        this.startX = f;
        this.startY = f2;
    }

    public float getOriginX() {
        return this.startX;
    }

    public float getOriginY() {
        return this.startY;
    }

    public int getMaxLines() {
        return this.maxLines;
    }

    public void setMaxLines(int i) {
        this.maxLines = i;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float f) {
        this.height = f;
    }

    public void setAlignment(int i) {
        this.alignment = i;
    }

    public int getAlignment() {
        return this.alignment;
    }
}
