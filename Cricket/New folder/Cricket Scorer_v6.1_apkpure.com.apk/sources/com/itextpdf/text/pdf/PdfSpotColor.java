package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.error_messages.MessageLocalization;

public class PdfSpotColor implements ICachedColorSpace, IPdfSpecialColorSpace {
    public ColorDetails altColorDetails;
    public BaseColor altcs;
    public PdfName name;

    public PdfSpotColor(String str, BaseColor baseColor) {
        this.name = new PdfName(str);
        this.altcs = baseColor;
    }

    public ColorDetails[] getColorantDetails(PdfWriter pdfWriter) {
        if (this.altColorDetails == null && (this.altcs instanceof ExtendedColor) && ((ExtendedColor) this.altcs).getType() == 7) {
            this.altColorDetails = pdfWriter.addSimple((ICachedColorSpace) ((LabColor) this.altcs).getLabColorSpace());
        }
        return new ColorDetails[]{this.altColorDetails};
    }

    public BaseColor getAlternativeCS() {
        return this.altcs;
    }

    public PdfName getName() {
        return this.name;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public PdfObject getSpotObject(PdfWriter pdfWriter) {
        return getPdfObject(pdfWriter);
    }

    public PdfObject getPdfObject(PdfWriter pdfWriter) {
        PdfFunction pdfFunction;
        PdfArray pdfArray = new PdfArray((PdfObject) PdfName.SEPARATION);
        pdfArray.add((PdfObject) this.name);
        if (this.altcs instanceof ExtendedColor) {
            int i = ((ExtendedColor) this.altcs).type;
            if (i != 7) {
                switch (i) {
                    case 1:
                        pdfArray.add((PdfObject) PdfName.DEVICEGRAY);
                        PdfWriter pdfWriter2 = pdfWriter;
                        pdfFunction = PdfFunction.type2(pdfWriter2, new float[]{0.0f, 1.0f}, (float[]) null, new float[]{1.0f}, new float[]{((GrayColor) this.altcs).getGray()}, 1.0f);
                        break;
                    case 2:
                        pdfArray.add((PdfObject) PdfName.DEVICECMYK);
                        CMYKColor cMYKColor = (CMYKColor) this.altcs;
                        PdfWriter pdfWriter3 = pdfWriter;
                        pdfFunction = PdfFunction.type2(pdfWriter3, new float[]{0.0f, 1.0f}, (float[]) null, new float[]{0.0f, 0.0f, 0.0f, 0.0f}, new float[]{cMYKColor.getCyan(), cMYKColor.getMagenta(), cMYKColor.getYellow(), cMYKColor.getBlack()}, 1.0f);
                        break;
                    default:
                        throw new RuntimeException(MessageLocalization.getComposedMessage("only.rgb.gray.and.cmyk.are.supported.as.alternative.color.spaces", new Object[0]));
                }
            } else {
                LabColor labColor = (LabColor) this.altcs;
                if (this.altColorDetails != null) {
                    pdfArray.add((PdfObject) this.altColorDetails.getIndirectReference());
                } else {
                    pdfArray.add(labColor.getLabColorSpace().getPdfObject(pdfWriter));
                }
                PdfWriter pdfWriter4 = pdfWriter;
                pdfFunction = PdfFunction.type2(pdfWriter4, new float[]{0.0f, 1.0f}, (float[]) null, new float[]{100.0f, 0.0f, 0.0f}, new float[]{labColor.getL(), labColor.getA(), labColor.getB()}, 1.0f);
            }
        } else {
            pdfArray.add((PdfObject) PdfName.DEVICERGB);
            PdfWriter pdfWriter5 = pdfWriter;
            pdfFunction = PdfFunction.type2(pdfWriter5, new float[]{0.0f, 1.0f}, (float[]) null, new float[]{1.0f, 1.0f, 1.0f}, new float[]{((float) this.altcs.getRed()) / 255.0f, ((float) this.altcs.getGreen()) / 255.0f, ((float) this.altcs.getBlue()) / 255.0f}, 1.0f);
        }
        pdfArray.add((PdfObject) pdfFunction.getReference());
        return pdfArray;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PdfSpotColor)) {
            return false;
        }
        PdfSpotColor pdfSpotColor = (PdfSpotColor) obj;
        return this.altcs.equals(pdfSpotColor.altcs) && this.name.equals(pdfSpotColor.name);
    }

    public int hashCode() {
        return (31 * this.name.hashCode()) + this.altcs.hashCode();
    }
}
