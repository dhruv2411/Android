package com.itextpdf.text.pdf.draw;

import com.itextpdf.text.pdf.PdfContentByte;

public class DottedLineSeparator extends LineSeparator {
    protected float gap = 5.0f;

    public void draw(PdfContentByte pdfContentByte, float f, float f2, float f3, float f4, float f5) {
        pdfContentByte.saveState();
        pdfContentByte.setLineWidth(this.lineWidth);
        pdfContentByte.setLineCap(1);
        pdfContentByte.setLineDash(0.0f, this.gap, this.gap / 2.0f);
        drawLine(pdfContentByte, f, f3, f5);
        pdfContentByte.restoreState();
    }

    public float getGap() {
        return this.gap;
    }

    public void setGap(float f) {
        this.gap = f;
    }
}
