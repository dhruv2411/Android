package com.itextpdf.text.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.codec.TIFFConstants;
import java.util.HashMap;

public class PdfPage extends PdfDictionary {
    public static final PdfNumber INVERTEDPORTRAIT = new PdfNumber(180);
    public static final PdfNumber LANDSCAPE = new PdfNumber(90);
    public static final PdfNumber PORTRAIT = new PdfNumber(0);
    public static final PdfNumber SEASCAPE = new PdfNumber((int) TIFFConstants.TIFFTAG_IMAGEDESCRIPTION);
    private static final PdfName[] boxNames = {PdfName.CROPBOX, PdfName.TRIMBOX, PdfName.ARTBOX, PdfName.BLEEDBOX};
    private static final String[] boxStrings = {"crop", "trim", "art", "bleed"};
    PdfRectangle mediaBox;

    public boolean isParent() {
        return false;
    }

    PdfPage(PdfRectangle pdfRectangle, HashMap<String, PdfRectangle> hashMap, PdfDictionary pdfDictionary, int i) throws DocumentException {
        super(PAGE);
        this.mediaBox = pdfRectangle;
        if (pdfRectangle == null || (pdfRectangle.width() <= 14400.0f && pdfRectangle.height() <= 14400.0f)) {
            put(PdfName.MEDIABOX, pdfRectangle);
            put(PdfName.RESOURCES, pdfDictionary);
            if (i != 0) {
                put(PdfName.ROTATE, new PdfNumber(i));
            }
            for (int i2 = 0; i2 < boxStrings.length; i2++) {
                PdfObject pdfObject = hashMap.get(boxStrings[i2]);
                if (pdfObject != null) {
                    put(boxNames[i2], pdfObject);
                }
            }
            return;
        }
        throw new DocumentException(MessageLocalization.getComposedMessage("the.page.size.must.be.smaller.than.14400.by.14400.its.1.by.2", Float.valueOf(pdfRectangle.width()), Float.valueOf(pdfRectangle.height())));
    }

    PdfPage(PdfRectangle pdfRectangle, HashMap<String, PdfRectangle> hashMap, PdfDictionary pdfDictionary) throws DocumentException {
        this(pdfRectangle, hashMap, pdfDictionary, 0);
    }

    /* access modifiers changed from: package-private */
    public void add(PdfIndirectReference pdfIndirectReference) {
        put(PdfName.CONTENTS, pdfIndirectReference);
    }

    /* access modifiers changed from: package-private */
    public PdfRectangle rotateMediaBox() {
        this.mediaBox = this.mediaBox.rotate();
        put(PdfName.MEDIABOX, this.mediaBox);
        return this.mediaBox;
    }

    /* access modifiers changed from: package-private */
    public PdfRectangle getMediaBox() {
        return this.mediaBox;
    }
}
