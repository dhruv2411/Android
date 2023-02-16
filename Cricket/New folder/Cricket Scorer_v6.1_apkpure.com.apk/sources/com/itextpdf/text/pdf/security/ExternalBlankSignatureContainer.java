package com.itextpdf.text.pdf.security;

import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import java.io.InputStream;
import java.security.GeneralSecurityException;

public class ExternalBlankSignatureContainer implements ExternalSignatureContainer {
    private PdfDictionary sigDic;

    public ExternalBlankSignatureContainer(PdfDictionary pdfDictionary) {
        this.sigDic = pdfDictionary;
    }

    public ExternalBlankSignatureContainer(PdfName pdfName, PdfName pdfName2) {
        this.sigDic = new PdfDictionary();
        this.sigDic.put(PdfName.FILTER, pdfName);
        this.sigDic.put(PdfName.SUBFILTER, pdfName2);
    }

    public byte[] sign(InputStream inputStream) throws GeneralSecurityException {
        return new byte[0];
    }

    public void modifySigningDictionary(PdfDictionary pdfDictionary) {
        pdfDictionary.putAll(this.sigDic);
    }
}
