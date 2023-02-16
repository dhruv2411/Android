package com.itextpdf.text.pdf;

import java.io.IOException;

public class PdfRendition extends PdfDictionary {
    PdfRendition(String str, PdfFileSpecification pdfFileSpecification, String str2) throws IOException {
        put(PdfName.S, new PdfName("MR"));
        PdfName pdfName = PdfName.N;
        put(pdfName, new PdfString("Rendition for " + str));
        put(PdfName.C, new PdfMediaClipData(str, pdfFileSpecification, str2));
    }
}
