package com.itextpdf.text.pdf;

import com.itextpdf.text.error_messages.MessageLocalization;

public class DeviceNColor extends ExtendedColor {
    PdfDeviceNColor pdfDeviceNColor;
    float[] tints;

    public DeviceNColor(PdfDeviceNColor pdfDeviceNColor2, float[] fArr) {
        super(6);
        if (pdfDeviceNColor2.getSpotColors().length != fArr.length) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("devicen.color.shall.have.the.same.number.of.colorants.as.the.destination.DeviceN.color.space", new Object[0]));
        }
        this.pdfDeviceNColor = pdfDeviceNColor2;
        this.tints = fArr;
    }

    public PdfDeviceNColor getPdfDeviceNColor() {
        return this.pdfDeviceNColor;
    }

    public float[] getTints() {
        return this.tints;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DeviceNColor) {
            DeviceNColor deviceNColor = (DeviceNColor) obj;
            if (deviceNColor.tints.length == this.tints.length) {
                int i = 0;
                for (float f : this.tints) {
                    if (f != deviceNColor.tints[i]) {
                        return false;
                    }
                    i++;
                }
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.pdfDeviceNColor.hashCode();
        for (float valueOf : this.tints) {
            hashCode ^= Float.valueOf(valueOf).hashCode();
        }
        return hashCode;
    }
}
