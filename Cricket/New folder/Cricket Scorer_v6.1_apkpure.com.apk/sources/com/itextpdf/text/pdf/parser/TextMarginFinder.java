package com.itextpdf.text.pdf.parser;

import com.itextpdf.awt.geom.Rectangle2D;

public class TextMarginFinder implements RenderListener {
    private Rectangle2D.Float textRectangle = null;

    public void beginTextBlock() {
    }

    public void endTextBlock() {
    }

    public void renderImage(ImageRenderInfo imageRenderInfo) {
    }

    public void renderText(TextRenderInfo textRenderInfo) {
        if (this.textRectangle == null) {
            this.textRectangle = textRenderInfo.getDescentLine().getBoundingRectange();
        } else {
            this.textRectangle.add((Rectangle2D) textRenderInfo.getDescentLine().getBoundingRectange());
        }
        this.textRectangle.add((Rectangle2D) textRenderInfo.getAscentLine().getBoundingRectange());
    }

    public float getLlx() {
        return this.textRectangle.x;
    }

    public float getLly() {
        return this.textRectangle.y;
    }

    public float getUrx() {
        return this.textRectangle.x + this.textRectangle.width;
    }

    public float getUry() {
        return this.textRectangle.y + this.textRectangle.height;
    }

    public float getWidth() {
        return this.textRectangle.width;
    }

    public float getHeight() {
        return this.textRectangle.height;
    }
}
