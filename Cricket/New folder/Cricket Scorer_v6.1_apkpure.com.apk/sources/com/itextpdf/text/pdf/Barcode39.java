package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;

public class Barcode39 extends Barcode {
    private static final byte[][] BARS = {new byte[]{0, 0, 0, 1, 1, 0, 1, 0, 0}, new byte[]{1, 0, 0, 1, 0, 0, 0, 0, 1}, new byte[]{0, 0, 1, 1, 0, 0, 0, 0, 1}, new byte[]{1, 0, 1, 1, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 1, 1, 0, 0, 0, 1}, new byte[]{1, 0, 0, 1, 1, 0, 0, 0, 0}, new byte[]{0, 0, 1, 1, 1, 0, 0, 0, 0}, new byte[]{0, 0, 0, 1, 0, 0, 1, 0, 1}, new byte[]{1, 0, 0, 1, 0, 0, 1, 0, 0}, new byte[]{0, 0, 1, 1, 0, 0, 1, 0, 0}, new byte[]{1, 0, 0, 0, 0, 1, 0, 0, 1}, new byte[]{0, 0, 1, 0, 0, 1, 0, 0, 1}, new byte[]{1, 0, 1, 0, 0, 1, 0, 0, 0}, new byte[]{0, 0, 0, 0, 1, 1, 0, 0, 1}, new byte[]{1, 0, 0, 0, 1, 1, 0, 0, 0}, new byte[]{0, 0, 1, 0, 1, 1, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 1, 1, 0, 1}, new byte[]{1, 0, 0, 0, 0, 1, 1, 0, 0}, new byte[]{0, 0, 1, 0, 0, 1, 1, 0, 0}, new byte[]{0, 0, 0, 0, 1, 1, 1, 0, 0}, new byte[]{1, 0, 0, 0, 0, 0, 0, 1, 1}, new byte[]{0, 0, 1, 0, 0, 0, 0, 1, 1}, new byte[]{1, 0, 1, 0, 0, 0, 0, 1, 0}, new byte[]{0, 0, 0, 0, 1, 0, 0, 1, 1}, new byte[]{1, 0, 0, 0, 1, 0, 0, 1, 0}, new byte[]{0, 0, 1, 0, 1, 0, 0, 1, 0}, new byte[]{0, 0, 0, 0, 0, 0, 1, 1, 1}, new byte[]{1, 0, 0, 0, 0, 0, 1, 1, 0}, new byte[]{0, 0, 1, 0, 0, 0, 1, 1, 0}, new byte[]{0, 0, 0, 0, 1, 0, 1, 1, 0}, new byte[]{1, 1, 0, 0, 0, 0, 0, 0, 1}, new byte[]{0, 1, 1, 0, 0, 0, 0, 0, 1}, new byte[]{1, 1, 1, 0, 0, 0, 0, 0, 0}, new byte[]{0, 1, 0, 0, 1, 0, 0, 0, 1}, new byte[]{1, 1, 0, 0, 1, 0, 0, 0, 0}, new byte[]{0, 1, 1, 0, 1, 0, 0, 0, 0}, new byte[]{0, 1, 0, 0, 0, 0, 1, 0, 1}, new byte[]{1, 1, 0, 0, 0, 0, 1, 0, 0}, new byte[]{0, 1, 1, 0, 0, 0, 1, 0, 0}, new byte[]{0, 1, 0, 1, 0, 1, 0, 0, 0}, new byte[]{0, 1, 0, 1, 0, 0, 0, 1, 0}, new byte[]{0, 1, 0, 0, 0, 1, 0, 1, 0}, new byte[]{0, 0, 0, 1, 0, 1, 0, 1, 0}, new byte[]{0, 1, 0, 0, 1, 0, 1, 0, 0}};
    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%*";
    private static final String EXTENDED = "%U$A$B$C$D$E$F$G$H$I$J$K$L$M$N$O$P$Q$R$S$T$U$V$W$X$Y$Z%A%B%C%D%E  /A/B/C/D/E/F/G/H/I/J/K/L - ./O 0 1 2 3 4 5 6 7 8 9/Z%F%G%H%I%J%V A B C D E F G H I J K L M N O P Q R S T U V W X Y Z%K%L%M%N%O%W+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O+P+Q+R+S+T+U+V+W+X+Y+Z%P%Q%R%S%T";

    public Barcode39() {
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
            this.startStopText = true;
            this.extended = false;
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public static byte[] getBarsCode39(String str) {
        String str2 = "*" + str + "*";
        byte[] bArr = new byte[((str2.length() * 10) - 1)];
        for (int i = 0; i < str2.length(); i++) {
            int indexOf = CHARS.indexOf(str2.charAt(i));
            if (indexOf < 0) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.character.1.is.illegal.in.code.39", (int) str2.charAt(i)));
            }
            System.arraycopy(BARS[indexOf], 0, bArr, i * 10, 9);
        }
        return bArr;
    }

    public static String getCode39Ex(String str) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt > 127) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.character.1.is.illegal.in.code.39.extended", (int) charAt));
            }
            int i2 = charAt * 2;
            char charAt2 = EXTENDED.charAt(i2);
            char charAt3 = EXTENDED.charAt(i2 + 1);
            if (charAt2 != ' ') {
                sb.append(charAt2);
            }
            sb.append(charAt3);
        }
        return sb.toString();
    }

    static char getChecksum(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            int indexOf = CHARS.indexOf(str.charAt(i2));
            if (indexOf < 0) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.character.1.is.illegal.in.code.39", (int) str.charAt(i2)));
            }
            i += indexOf;
        }
        return CHARS.charAt(i % 43);
    }

    public Rectangle getBarcodeSize() {
        float f;
        float f2;
        String str = this.code;
        if (this.extended) {
            str = getCode39Ex(this.code);
        }
        float f3 = 0.0f;
        if (this.font != null) {
            if (this.baseline > 0.0f) {
                f2 = this.baseline - this.font.getFontDescriptor(3, this.size);
            } else {
                f2 = (-this.baseline) + this.size;
            }
            f3 = f2;
            String str2 = this.code;
            if (this.generateChecksum && this.checksumText) {
                str2 = str2 + getChecksum(str);
            }
            if (this.startStopText) {
                str2 = "*" + str2 + "*";
            }
            BaseFont baseFont = this.font;
            if (this.altText != null) {
                str2 = this.altText;
            }
            f = baseFont.getWidthPoint(str2, this.size);
        } else {
            f = 0.0f;
        }
        int length = str.length() + 2;
        if (this.generateChecksum) {
            length++;
        }
        return new Rectangle(Math.max((((float) length) * ((6.0f * this.x) + (3.0f * this.x * this.n))) + (((float) (length - 1)) * this.x), f), this.barHeight + f3);
    }

    public Rectangle placeBarcode(PdfContentByte pdfContentByte, BaseColor baseColor, BaseColor baseColor2) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        String str = this.code;
        String str2 = this.code;
        if (this.extended) {
            str2 = getCode39Ex(this.code);
        }
        float f6 = 0.0f;
        if (this.font != null) {
            if (this.generateChecksum && this.checksumText) {
                str = str + getChecksum(str2);
            }
            if (this.startStopText) {
                str = "*" + str + "*";
            }
            BaseFont baseFont = this.font;
            if (this.altText != null) {
                str = this.altText;
            }
            f = baseFont.getWidthPoint(str, this.size);
        } else {
            f = 0.0f;
        }
        if (this.generateChecksum) {
            str2 = str2 + getChecksum(str2);
        }
        int length = str2.length() + 2;
        boolean z = true;
        float f7 = (((float) length) * ((6.0f * this.x) + (3.0f * this.x * this.n))) + (((float) (length - 1)) * this.x);
        int i = this.textAlignment;
        if (i != 0) {
            if (i != 2) {
                if (f > f7) {
                    f3 = (f - f7) / 2.0f;
                } else {
                    f5 = (f7 - f) / 2.0f;
                    f2 = f5;
                    f3 = 0.0f;
                }
            } else if (f > f7) {
                f3 = f - f7;
            } else {
                f5 = f7 - f;
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
            float f8 = -this.font.getFontDescriptor(3, this.size);
            f4 = f8;
            f6 = this.baseline + f8;
        }
        byte[] barsCode39 = getBarsCode39(str2);
        if (baseColor != null) {
            pdfContentByte.setColorFill(baseColor);
        }
        for (int i2 = 0; i2 < barsCode39.length; i2++) {
            float f9 = barsCode39[i2] == 0 ? this.x : this.x * this.n;
            if (z) {
                pdfContentByte.rectangle(f3, f6, f9 - this.inkSpreading, this.barHeight);
            }
            z = !z;
            f3 += f9;
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
