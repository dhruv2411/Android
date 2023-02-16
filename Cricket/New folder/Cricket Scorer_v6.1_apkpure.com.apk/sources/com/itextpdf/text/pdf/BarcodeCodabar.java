package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;

public class BarcodeCodabar extends Barcode {
    private static final byte[][] BARS = {new byte[]{0, 0, 0, 0, 0, 1, 1}, new byte[]{0, 0, 0, 0, 1, 1, 0}, new byte[]{0, 0, 0, 1, 0, 0, 1}, new byte[]{1, 1, 0, 0, 0, 0, 0}, new byte[]{0, 0, 1, 0, 0, 1, 0}, new byte[]{1, 0, 0, 0, 0, 1, 0}, new byte[]{0, 1, 0, 0, 0, 0, 1}, new byte[]{0, 1, 0, 0, 1, 0, 0}, new byte[]{0, 1, 1, 0, 0, 0, 0}, new byte[]{1, 0, 0, 1, 0, 0, 0}, new byte[]{0, 0, 0, 1, 1, 0, 0}, new byte[]{0, 0, 1, 1, 0, 0, 0}, new byte[]{1, 0, 0, 0, 1, 0, 1}, new byte[]{1, 0, 1, 0, 0, 0, 1}, new byte[]{1, 0, 1, 0, 1, 0, 0}, new byte[]{0, 0, 1, 0, 1, 0, 1}, new byte[]{0, 0, 1, 1, 0, 1, 0}, new byte[]{0, 1, 0, 1, 0, 0, 1}, new byte[]{0, 0, 0, 1, 0, 1, 1}, new byte[]{0, 0, 0, 1, 1, 1, 0}};
    private static final String CHARS = "0123456789-$:/.+ABCD";
    private static final int START_STOP_IDX = 16;

    public BarcodeCodabar() {
        try {
            this.x = 0.8f;
            this.n = 2.0f;
            this.font = BaseFont.createFont("Helvetica", "winansi", false);
            this.size = 8.0f;
            this.baseline = this.size;
            this.barHeight = this.size * 3.0f;
            this.textAlignment = 1;
            this.generateChecksum = false;
            this.checksumText = false;
            this.startStopText = false;
            this.codeType = 12;
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public static byte[] getBarsCodabar(String str) {
        String upperCase = str.toUpperCase();
        int length = upperCase.length();
        if (length < 2) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("codabar.must.have.at.least.a.start.and.stop.character", new Object[0]));
        }
        if (CHARS.indexOf(upperCase.charAt(0)) >= 16) {
            int i = length - 1;
            if (CHARS.indexOf(upperCase.charAt(i)) >= 16) {
                byte[] bArr = new byte[((upperCase.length() * 8) - 1)];
                int i2 = 0;
                while (i2 < length) {
                    int indexOf = CHARS.indexOf(upperCase.charAt(i2));
                    if (indexOf >= 16 && i2 > 0 && i2 < i) {
                        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("in.codabar.start.stop.characters.are.only.allowed.at.the.extremes", new Object[0]));
                    } else if (indexOf < 0) {
                        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.character.1.is.illegal.in.codabar", (int) upperCase.charAt(i2)));
                    } else {
                        System.arraycopy(BARS[indexOf], 0, bArr, i2 * 8, 7);
                        i2++;
                    }
                }
                return bArr;
            }
        }
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("codabar.must.have.one.of.abcd.as.start.stop.character", new Object[0]));
    }

    public static String calculateChecksum(String str) {
        if (str.length() < 2) {
            return str;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += CHARS.indexOf(upperCase.charAt(i2));
        }
        StringBuilder sb = new StringBuilder();
        int i3 = length - 1;
        sb.append(str.substring(0, i3));
        sb.append(CHARS.charAt((((i + 15) / 16) * 16) - i));
        sb.append(str.substring(i3));
        return sb.toString();
    }

    public Rectangle getBarcodeSize() {
        float f;
        float f2;
        String str = this.code;
        if (this.generateChecksum && this.checksumText) {
            str = calculateChecksum(this.code);
        }
        if (!this.startStopText) {
            str = str.substring(1, str.length() - 1);
        }
        float f3 = 0.0f;
        if (this.font != null) {
            if (this.baseline > 0.0f) {
                f2 = this.baseline - this.font.getFontDescriptor(3, this.size);
            } else {
                f2 = (-this.baseline) + this.size;
            }
            f3 = f2;
            BaseFont baseFont = this.font;
            if (this.altText != null) {
                str = this.altText;
            }
            f = baseFont.getWidthPoint(str, this.size);
        } else {
            f = 0.0f;
        }
        String str2 = this.code;
        if (this.generateChecksum) {
            str2 = calculateChecksum(this.code);
        }
        byte[] barsCodabar = getBarsCodabar(str2);
        int i = 0;
        for (byte b : barsCodabar) {
            i += b;
        }
        return new Rectangle(Math.max(this.x * (((float) (barsCodabar.length - i)) + (((float) i) * this.n)), f), this.barHeight + f3);
    }

    public Rectangle placeBarcode(PdfContentByte pdfContentByte, BaseColor baseColor, BaseColor baseColor2) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        String str = this.code;
        if (this.generateChecksum && this.checksumText) {
            str = calculateChecksum(this.code);
        }
        boolean z = true;
        if (!this.startStopText) {
            str = str.substring(1, str.length() - 1);
        }
        float f6 = 0.0f;
        if (this.font != null) {
            BaseFont baseFont = this.font;
            if (this.altText != null) {
                str = this.altText;
            }
            f = baseFont.getWidthPoint(str, this.size);
        } else {
            f = 0.0f;
        }
        byte[] barsCodabar = getBarsCodabar(this.generateChecksum ? calculateChecksum(this.code) : this.code);
        int i = 0;
        for (byte b : barsCodabar) {
            i += b;
        }
        float length = this.x * (((float) (barsCodabar.length - i)) + (((float) i) * this.n));
        int i2 = this.textAlignment;
        if (i2 != 0) {
            if (i2 != 2) {
                if (f > length) {
                    f3 = (f - length) / 2.0f;
                } else {
                    f5 = (length - f) / 2.0f;
                    f2 = f5;
                    f3 = 0.0f;
                }
            } else if (f > length) {
                f3 = f - length;
            } else {
                f5 = length - f;
                f2 = f5;
                f3 = 0.0f;
            }
            f2 = 0.0f;
        } else {
            f3 = 0.0f;
            f2 = 0.0f;
        }
        if (this.font == null) {
            f4 = 0.0f;
        } else if (this.baseline <= 0.0f) {
            f4 = this.barHeight - this.baseline;
        } else {
            float f7 = -this.font.getFontDescriptor(3, this.size);
            f4 = f7;
            f6 = this.baseline + f7;
        }
        if (baseColor != null) {
            pdfContentByte.setColorFill(baseColor);
        }
        for (int i3 = 0; i3 < barsCodabar.length; i3++) {
            float f8 = barsCodabar[i3] == 0 ? this.x : this.x * this.n;
            if (z) {
                pdfContentByte.rectangle(f3, f6, f8 - this.inkSpreading, this.barHeight);
            }
            z = !z;
            f3 += f8;
        }
        pdfContentByte.fill();
        if (this.font != null) {
            if (baseColor2 != null) {
                pdfContentByte.setColorFill(baseColor2);
            }
            pdfContentByte.beginText();
            pdfContentByte.setFontAndSize(this.font, this.size);
            pdfContentByte.setTextMatrix(f2, f4);
            pdfContentByte.showText(str);
            pdfContentByte.endText();
        }
        return getBarcodeSize();
    }
}
