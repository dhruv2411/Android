package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;

public class LabColor extends ExtendedColor {
    private float a;
    private float b;
    private float l;
    PdfLabColor labColorSpace;

    public LabColor(PdfLabColor pdfLabColor, float f, float f2, float f3) {
        super(7);
        this.labColorSpace = pdfLabColor;
        this.l = f;
        this.a = f2;
        this.b = f3;
        BaseColor lab2Rgb = pdfLabColor.lab2Rgb(f, f2, f3);
        setValue(lab2Rgb.getRed(), lab2Rgb.getGreen(), lab2Rgb.getBlue(), 255);
    }

    public PdfLabColor getLabColorSpace() {
        return this.labColorSpace;
    }

    public float getL() {
        return this.l;
    }

    public float getA() {
        return this.a;
    }

    public float getB() {
        return this.b;
    }

    public BaseColor toRgb() {
        return this.labColorSpace.lab2Rgb(this.l, this.a, this.b);
    }

    /* access modifiers changed from: package-private */
    public CMYKColor toCmyk() {
        return this.labColorSpace.lab2Cmyk(this.l, this.a, this.b);
    }
}
