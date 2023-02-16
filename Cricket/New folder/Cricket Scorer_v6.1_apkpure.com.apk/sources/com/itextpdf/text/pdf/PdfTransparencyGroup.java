package com.itextpdf.text.pdf;

public class PdfTransparencyGroup extends PdfDictionary {
    public PdfTransparencyGroup() {
        put(PdfName.S, PdfName.TRANSPARENCY);
    }

    public void setIsolated(boolean z) {
        if (z) {
            put(PdfName.I, PdfBoolean.PDFTRUE);
        } else {
            remove(PdfName.I);
        }
    }

    public void setKnockout(boolean z) {
        if (z) {
            put(PdfName.K, PdfBoolean.PDFTRUE);
        } else {
            remove(PdfName.K);
        }
    }
}
