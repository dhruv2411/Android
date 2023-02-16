package com.itextpdf.text.pdf.parser;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfIndirectReference;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import java.io.IOException;

public class ImageRenderInfo {
    private final PdfDictionary colorSpaceDictionary;
    private final GraphicsState gs;
    private PdfImageObject imageObject = null;
    private final InlineImageInfo inlineImageInfo;
    private final PdfIndirectReference ref;

    private ImageRenderInfo(GraphicsState graphicsState, PdfIndirectReference pdfIndirectReference, PdfDictionary pdfDictionary) {
        this.gs = graphicsState;
        this.ref = pdfIndirectReference;
        this.inlineImageInfo = null;
        this.colorSpaceDictionary = pdfDictionary;
    }

    private ImageRenderInfo(GraphicsState graphicsState, InlineImageInfo inlineImageInfo2, PdfDictionary pdfDictionary) {
        this.gs = graphicsState;
        this.ref = null;
        this.inlineImageInfo = inlineImageInfo2;
        this.colorSpaceDictionary = pdfDictionary;
    }

    public static ImageRenderInfo createForXObject(GraphicsState graphicsState, PdfIndirectReference pdfIndirectReference, PdfDictionary pdfDictionary) {
        return new ImageRenderInfo(graphicsState, pdfIndirectReference, pdfDictionary);
    }

    protected static ImageRenderInfo createForEmbeddedImage(GraphicsState graphicsState, InlineImageInfo inlineImageInfo2, PdfDictionary pdfDictionary) {
        return new ImageRenderInfo(graphicsState, inlineImageInfo2, pdfDictionary);
    }

    public PdfImageObject getImage() throws IOException {
        prepareImageObject();
        return this.imageObject;
    }

    private void prepareImageObject() throws IOException {
        if (this.imageObject == null) {
            if (this.ref != null) {
                this.imageObject = new PdfImageObject((PRStream) PdfReader.getPdfObject((PdfObject) this.ref), this.colorSpaceDictionary);
            } else if (this.inlineImageInfo != null) {
                this.imageObject = new PdfImageObject(this.inlineImageInfo.getImageDictionary(), this.inlineImageInfo.getSamples(), this.colorSpaceDictionary);
            }
        }
    }

    public Vector getStartPoint() {
        return new Vector(0.0f, 0.0f, 1.0f).cross(this.gs.ctm);
    }

    public Matrix getImageCTM() {
        return this.gs.ctm;
    }

    public float getArea() {
        return this.gs.ctm.getDeterminant();
    }

    public PdfIndirectReference getRef() {
        return this.ref;
    }

    public BaseColor getCurrentFillColor() {
        return this.gs.fillColor;
    }
}
