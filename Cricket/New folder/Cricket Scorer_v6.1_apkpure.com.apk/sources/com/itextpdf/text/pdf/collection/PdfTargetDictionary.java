package com.itextpdf.text.pdf.collection;

import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfString;

public class PdfTargetDictionary extends PdfDictionary {
    public PdfTargetDictionary(PdfTargetDictionary pdfTargetDictionary) {
        put(PdfName.R, PdfName.P);
        if (pdfTargetDictionary != null) {
            setAdditionalPath(pdfTargetDictionary);
        }
    }

    public PdfTargetDictionary(boolean z) {
        if (z) {
            put(PdfName.R, PdfName.C);
        } else {
            put(PdfName.R, PdfName.P);
        }
    }

    public void setEmbeddedFileName(String str) {
        put(PdfName.N, new PdfString(str, (String) null));
    }

    public void setFileAttachmentPagename(String str) {
        put(PdfName.P, new PdfString(str, (String) null));
    }

    public void setFileAttachmentPage(int i) {
        put(PdfName.P, new PdfNumber(i));
    }

    public void setFileAttachmentName(String str) {
        put(PdfName.A, new PdfString(str, PdfObject.TEXT_UNICODE));
    }

    public void setFileAttachmentIndex(int i) {
        put(PdfName.A, new PdfNumber(i));
    }

    public void setAdditionalPath(PdfTargetDictionary pdfTargetDictionary) {
        put(PdfName.T, pdfTargetDictionary);
    }
}
