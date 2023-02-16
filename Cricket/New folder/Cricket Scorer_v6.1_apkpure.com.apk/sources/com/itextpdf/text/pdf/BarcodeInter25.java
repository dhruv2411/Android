package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;

public class BarcodeInter25 extends Barcode {
    private static final byte[][] BARS = {new byte[]{0, 0, 1, 1, 0}, new byte[]{1, 0, 0, 0, 1}, new byte[]{0, 1, 0, 0, 1}, new byte[]{1, 1, 0, 0, 0}, new byte[]{0, 0, 1, 0, 1}, new byte[]{1, 0, 1, 0, 0}, new byte[]{0, 1, 1, 0, 0}, new byte[]{0, 0, 0, 1, 1}, new byte[]{1, 0, 0, 1, 0}, new byte[]{0, 1, 0, 1, 0}};

    public BarcodeInter25() {
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
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public static String keepNumbers(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    public static char getChecksum(String str) {
        int i = 3;
        int i2 = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            i2 += (str.charAt(length) - '0') * i;
            i ^= 2;
        }
        return (char) (((10 - (i2 % 10)) % 10) + 48);
    }

    public static byte[] getBarsInter25(String str) {
        String keepNumbers = keepNumbers(str);
        if ((keepNumbers.length() & 1) != 0) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.text.length.must.be.even", new Object[0]));
        }
        byte[] bArr = new byte[((keepNumbers.length() * 5) + 7)];
        bArr[0] = 0;
        bArr[1] = 0;
        bArr[2] = 0;
        int i = 4;
        bArr[3] = 0;
        int length = keepNumbers.length() / 2;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * 2;
            byte[] bArr2 = BARS[keepNumbers.charAt(i3) - '0'];
            byte[] bArr3 = BARS[keepNumbers.charAt(i3 + 1) - '0'];
            int i4 = i;
            for (int i5 = 0; i5 < 5; i5++) {
                int i6 = i4 + 1;
                bArr[i4] = bArr2[i5];
                i4 = i6 + 1;
                bArr[i6] = bArr3[i5];
            }
            i2++;
            i = i4;
        }
        int i7 = i + 1;
        bArr[i] = 1;
        bArr[i7] = 0;
        bArr[i7 + 1] = 0;
        return bArr;
    }

    public Rectangle getBarcodeSize() {
        float f;
        float f2;
        float f3 = 0.0f;
        if (this.font != null) {
            if (this.baseline > 0.0f) {
                f2 = this.baseline - this.font.getFontDescriptor(3, this.size);
            } else {
                f2 = (-this.baseline) + this.size;
            }
            f3 = f2;
            String str = this.code;
            if (this.generateChecksum && this.checksumText) {
                str = str + getChecksum(str);
            }
            BaseFont baseFont = this.font;
            if (this.altText != null) {
                str = this.altText;
            }
            f = baseFont.getWidthPoint(str, this.size);
        } else {
            f = 0.0f;
        }
        int length = keepNumbers(this.code).length();
        if (this.generateChecksum) {
            length++;
        }
        return new Rectangle(Math.max((((float) length) * ((3.0f * this.x) + (2.0f * this.x * this.n))) + ((6.0f + this.n) * this.x), f), this.barHeight + f3);
    }

    public Rectangle placeBarcode(PdfContentByte pdfContentByte, BaseColor baseColor, BaseColor baseColor2) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        String str = this.code;
        float f6 = 0.0f;
        if (this.font != null) {
            if (this.generateChecksum && this.checksumText) {
                str = str + getChecksum(str);
            }
            BaseFont baseFont = this.font;
            if (this.altText != null) {
                str = this.altText;
            }
            f = baseFont.getWidthPoint(str, this.size);
        } else {
            f = 0.0f;
        }
        String keepNumbers = keepNumbers(this.code);
        if (this.generateChecksum) {
            keepNumbers = keepNumbers + getChecksum(keepNumbers);
        }
        float length = (((float) keepNumbers.length()) * ((3.0f * this.x) + (this.x * 2.0f * this.n))) + ((6.0f + this.n) * this.x);
        int i = this.textAlignment;
        if (i != 0) {
            if (i != 2) {
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
        byte[] barsInter25 = getBarsInter25(keepNumbers);
        if (baseColor != null) {
            pdfContentByte.setColorFill(baseColor);
        }
        boolean z = true;
        for (int i2 = 0; i2 < barsInter25.length; i2++) {
            float f8 = barsInter25[i2] == 0 ? this.x : this.x * this.n;
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
