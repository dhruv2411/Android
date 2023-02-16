package com.itextpdf.text.pdf.internal;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ExtendedColor;
import com.itextpdf.text.pdf.PatternColor;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfXConformanceException;
import com.itextpdf.text.pdf.ShadingColor;
import com.itextpdf.text.pdf.SpotColor;
import com.itextpdf.text.pdf.interfaces.PdfXConformance;

public class PdfXConformanceImp implements PdfXConformance {
    protected int pdfxConformance = 0;
    protected PdfWriter writer;

    public PdfXConformanceImp(PdfWriter pdfWriter) {
        this.writer = pdfWriter;
    }

    public void setPDFXConformance(int i) {
        this.pdfxConformance = i;
    }

    public int getPDFXConformance() {
        return this.pdfxConformance;
    }

    public boolean isPdfIso() {
        return isPdfX();
    }

    public boolean isPdfX() {
        return this.pdfxConformance != 0;
    }

    public boolean isPdfX1A2001() {
        return this.pdfxConformance == 1;
    }

    public boolean isPdfX32002() {
        return this.pdfxConformance == 2;
    }

    public void checkPdfIsoConformance(int i, Object obj) {
        PdfObject pdfObject;
        if (this.writer != null && this.writer.isPdfX()) {
            int pDFXConformance = this.writer.getPDFXConformance();
            switch (i) {
                case 1:
                    if (pDFXConformance == 1) {
                        if (obj instanceof ExtendedColor) {
                            ExtendedColor extendedColor = (ExtendedColor) obj;
                            switch (extendedColor.getType()) {
                                case 0:
                                    throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.rgb.is.not.allowed", new Object[0]));
                                case 1:
                                case 2:
                                    return;
                                case 3:
                                    checkPdfIsoConformance(1, ((SpotColor) extendedColor).getPdfSpotColor().getAlternativeCS());
                                    return;
                                case 4:
                                    checkPdfIsoConformance(1, ((PatternColor) extendedColor).getPainter().getDefaultColor());
                                    return;
                                case 5:
                                    checkPdfIsoConformance(1, ((ShadingColor) extendedColor).getPdfShadingPattern().getShading().getColorSpace());
                                    return;
                                default:
                                    return;
                            }
                        } else if (obj instanceof BaseColor) {
                            throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.rgb.is.not.allowed", new Object[0]));
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                case 3:
                    if (pDFXConformance == 1) {
                        throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.rgb.is.not.allowed", new Object[0]));
                    }
                    return;
                case 4:
                    BaseFont baseFont = (BaseFont) obj;
                    if (!baseFont.isEmbedded()) {
                        throw new PdfXConformanceException(MessageLocalization.getComposedMessage("all.the.fonts.must.be.embedded.this.one.isn.t.1", baseFont.getPostscriptFontName()));
                    }
                    return;
                case 5:
                    PdfImage pdfImage = (PdfImage) obj;
                    if (pdfImage.get(PdfName.SMASK) != null) {
                        throw new PdfXConformanceException(MessageLocalization.getComposedMessage("the.smask.key.is.not.allowed.in.images", new Object[0]));
                    } else if (pDFXConformance != 1 || (pdfObject = pdfImage.get(PdfName.COLORSPACE)) == null) {
                        return;
                    } else {
                        if (pdfObject.isName()) {
                            if (PdfName.DEVICERGB.equals(pdfObject)) {
                                throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.rgb.is.not.allowed", new Object[0]));
                            }
                            return;
                        } else if (pdfObject.isArray() && PdfName.CALRGB.equals(((PdfArray) pdfObject).getPdfObject(0))) {
                            throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.calrgb.is.not.allowed", new Object[0]));
                        } else {
                            return;
                        }
                    }
                case 6:
                    PdfDictionary pdfDictionary = (PdfDictionary) obj;
                    if (pdfDictionary != null) {
                        PdfObject pdfObject2 = pdfDictionary.get(PdfName.BM);
                        if (pdfObject2 == null || PdfGState.BM_NORMAL.equals(pdfObject2) || PdfGState.BM_COMPATIBLE.equals(pdfObject2)) {
                            PdfObject pdfObject3 = pdfDictionary.get(PdfName.CA);
                            if (pdfObject3 != null) {
                                double doubleValue = ((PdfNumber) pdfObject3).doubleValue();
                                if (doubleValue != 1.0d) {
                                    throw new PdfXConformanceException(MessageLocalization.getComposedMessage("transparency.is.not.allowed.ca.eq.1", String.valueOf(doubleValue)));
                                }
                            }
                            PdfObject pdfObject4 = pdfDictionary.get(PdfName.ca);
                            if (pdfObject4 != null) {
                                double doubleValue2 = ((PdfNumber) pdfObject4).doubleValue();
                                if (doubleValue2 != 1.0d) {
                                    throw new PdfXConformanceException(MessageLocalization.getComposedMessage("transparency.is.not.allowed.ca.eq.1", String.valueOf(doubleValue2)));
                                }
                                return;
                            }
                            return;
                        }
                        throw new PdfXConformanceException(MessageLocalization.getComposedMessage("blend.mode.1.not.allowed", pdfObject2.toString()));
                    }
                    return;
                case 7:
                    throw new PdfXConformanceException(MessageLocalization.getComposedMessage("layers.are.not.allowed", new Object[0]));
                default:
                    return;
            }
        }
    }
}
