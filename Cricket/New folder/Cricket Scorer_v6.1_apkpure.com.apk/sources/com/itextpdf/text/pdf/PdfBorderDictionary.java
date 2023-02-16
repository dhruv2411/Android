package com.itextpdf.text.pdf;

import com.itextpdf.text.error_messages.MessageLocalization;

public class PdfBorderDictionary extends PdfDictionary {
    public static final int STYLE_BEVELED = 2;
    public static final int STYLE_DASHED = 1;
    public static final int STYLE_INSET = 3;
    public static final int STYLE_SOLID = 0;
    public static final int STYLE_UNDERLINE = 4;

    public PdfBorderDictionary(float f, int i, PdfDashPattern pdfDashPattern) {
        put(PdfName.W, new PdfNumber(f));
        switch (i) {
            case 0:
                put(PdfName.S, PdfName.S);
                return;
            case 1:
                if (pdfDashPattern != null) {
                    put(PdfName.D, pdfDashPattern);
                }
                put(PdfName.S, PdfName.D);
                return;
            case 2:
                put(PdfName.S, PdfName.B);
                return;
            case 3:
                put(PdfName.S, PdfName.I);
                return;
            case 4:
                put(PdfName.S, PdfName.U);
                return;
            default:
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.border.style", new Object[0]));
        }
    }

    public PdfBorderDictionary(float f, int i) {
        this(f, i, (PdfDashPattern) null);
    }
}
