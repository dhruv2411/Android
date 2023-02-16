package com.itextpdf.text;

import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.pdf.BaseFont;

public class Font implements Comparable<Font> {
    public static final int BOLD = 1;
    public static final int BOLDITALIC = 3;
    public static final int DEFAULTSIZE = 12;
    public static final int ITALIC = 2;
    public static final int NORMAL = 0;
    public static final int STRIKETHRU = 8;
    public static final int UNDEFINED = -1;
    public static final int UNDERLINE = 4;
    private BaseFont baseFont;
    private BaseColor color;
    private FontFamily family;
    private float size;
    private int style;

    public enum FontFamily {
        COURIER,
        HELVETICA,
        TIMES_ROMAN,
        SYMBOL,
        ZAPFDINGBATS,
        UNDEFINED
    }

    public enum FontStyle {
        NORMAL(HtmlTags.NORMAL),
        BOLD("bold"),
        ITALIC("italic"),
        OBLIQUE(HtmlTags.OBLIQUE),
        UNDERLINE("underline"),
        LINETHROUGH(HtmlTags.LINETHROUGH);
        
        private String code;

        private FontStyle(String str) {
            this.code = str;
        }

        public String getValue() {
            return this.code;
        }
    }

    public Font(Font font) {
        this.family = FontFamily.UNDEFINED;
        this.size = -1.0f;
        this.style = -1;
        this.color = null;
        this.baseFont = null;
        this.family = font.family;
        this.size = font.size;
        this.style = font.style;
        this.color = font.color;
        this.baseFont = font.baseFont;
    }

    public Font(FontFamily fontFamily, float f, int i, BaseColor baseColor) {
        this.family = FontFamily.UNDEFINED;
        this.size = -1.0f;
        this.style = -1;
        this.color = null;
        this.baseFont = null;
        this.family = fontFamily;
        this.size = f;
        this.style = i;
        this.color = baseColor;
    }

    public Font(BaseFont baseFont2, float f, int i, BaseColor baseColor) {
        this.family = FontFamily.UNDEFINED;
        this.size = -1.0f;
        this.style = -1;
        this.color = null;
        this.baseFont = null;
        this.baseFont = baseFont2;
        this.size = f;
        this.style = i;
        this.color = baseColor;
    }

    public Font(BaseFont baseFont2, float f, int i) {
        this(baseFont2, f, i, (BaseColor) null);
    }

    public Font(BaseFont baseFont2, float f) {
        this(baseFont2, f, -1, (BaseColor) null);
    }

    public Font(BaseFont baseFont2) {
        this(baseFont2, -1.0f, -1, (BaseColor) null);
    }

    public Font(FontFamily fontFamily, float f, int i) {
        this(fontFamily, f, i, (BaseColor) null);
    }

    public Font(FontFamily fontFamily, float f) {
        this(fontFamily, f, -1, (BaseColor) null);
    }

    public Font(FontFamily fontFamily) {
        this(fontFamily, -1.0f, -1, (BaseColor) null);
    }

    public Font() {
        this(FontFamily.UNDEFINED, -1.0f, -1, (BaseColor) null);
    }

    public int compareTo(Font font) {
        if (font == null) {
            return -1;
        }
        try {
            if (this.baseFont != null && !this.baseFont.equals(font.getBaseFont())) {
                return -2;
            }
            if (this.family != font.getFamily()) {
                return 1;
            }
            if (this.size != font.getSize()) {
                return 2;
            }
            if (this.style != font.getStyle()) {
                return 3;
            }
            return this.color == null ? font.color == null ? 0 : 4 : (font.color != null && this.color.equals(font.getColor())) ? 0 : 4;
        } catch (ClassCastException unused) {
            return -3;
        }
    }

    public FontFamily getFamily() {
        return this.family;
    }

    public String getFamilyname() {
        String str = "unknown";
        switch (getFamily()) {
            case COURIER:
                return "Courier";
            case HELVETICA:
                return "Helvetica";
            case TIMES_ROMAN:
                return "Times-Roman";
            case SYMBOL:
                return "Symbol";
            case ZAPFDINGBATS:
                return "ZapfDingbats";
            default:
                if (this.baseFont != null) {
                    for (String[] strArr : this.baseFont.getFamilyFontName()) {
                        if ("0".equals(strArr[2])) {
                            return strArr[3];
                        }
                        if ("1033".equals(strArr[2])) {
                            str = strArr[3];
                        }
                        if ("".equals(strArr[2])) {
                            str = strArr[3];
                        }
                    }
                }
                return str;
        }
    }

    public void setFamily(String str) {
        this.family = getFamily(str);
    }

    public static FontFamily getFamily(String str) {
        if (str.equalsIgnoreCase("Courier")) {
            return FontFamily.COURIER;
        }
        if (str.equalsIgnoreCase("Helvetica")) {
            return FontFamily.HELVETICA;
        }
        if (str.equalsIgnoreCase("Times-Roman")) {
            return FontFamily.TIMES_ROMAN;
        }
        if (str.equalsIgnoreCase("Symbol")) {
            return FontFamily.SYMBOL;
        }
        if (str.equalsIgnoreCase("ZapfDingbats")) {
            return FontFamily.ZAPFDINGBATS;
        }
        return FontFamily.UNDEFINED;
    }

    public float getSize() {
        return this.size;
    }

    public float getCalculatedSize() {
        float f = this.size;
        if (f == -1.0f) {
            return 12.0f;
        }
        return f;
    }

    public float getCalculatedLeading(float f) {
        return f * getCalculatedSize();
    }

    public void setSize(float f) {
        this.size = f;
    }

    public int getStyle() {
        return this.style;
    }

    public int getCalculatedStyle() {
        int i = this.style;
        if (i == -1) {
            i = 0;
        }
        return (this.baseFont != null || this.family == FontFamily.SYMBOL || this.family == FontFamily.ZAPFDINGBATS) ? i : i & -4;
    }

    public boolean isBold() {
        if (this.style != -1 && (this.style & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean isItalic() {
        if (this.style != -1 && (this.style & 2) == 2) {
            return true;
        }
        return false;
    }

    public boolean isUnderlined() {
        if (this.style != -1 && (this.style & 4) == 4) {
            return true;
        }
        return false;
    }

    public boolean isStrikethru() {
        if (this.style != -1 && (this.style & 8) == 8) {
            return true;
        }
        return false;
    }

    public void setStyle(int i) {
        this.style = i;
    }

    public void setStyle(String str) {
        if (this.style == -1) {
            this.style = 0;
        }
        this.style = getStyleValue(str) | this.style;
    }

    public static int getStyleValue(String str) {
        int indexOf = str.indexOf(FontStyle.NORMAL.getValue());
        int i = 0;
        if (str.indexOf(FontStyle.BOLD.getValue()) != -1) {
            i = 1;
        }
        if (str.indexOf(FontStyle.ITALIC.getValue()) != -1) {
            i |= 2;
        }
        if (str.indexOf(FontStyle.OBLIQUE.getValue()) != -1) {
            i |= 2;
        }
        if (str.indexOf(FontStyle.UNDERLINE.getValue()) != -1) {
            i |= 4;
        }
        return str.indexOf(FontStyle.LINETHROUGH.getValue()) != -1 ? i | 8 : i;
    }

    public BaseColor getColor() {
        return this.color;
    }

    public void setColor(BaseColor baseColor) {
        this.color = baseColor;
    }

    public void setColor(int i, int i2, int i3) {
        this.color = new BaseColor(i, i2, i3);
    }

    public BaseFont getBaseFont() {
        return this.baseFont;
    }

    public BaseFont getCalculatedBaseFont(boolean z) {
        String str;
        String str2;
        if (this.baseFont != null) {
            return this.baseFont;
        }
        int i = this.style;
        if (i == -1) {
            i = 0;
        }
        String str3 = "Cp1252";
        int i2 = AnonymousClass1.$SwitchMap$com$itextpdf$text$Font$FontFamily[this.family.ordinal()];
        if (i2 == 1) {
            switch (i & 3) {
                case 1:
                    str = "Courier-Bold";
                    break;
                case 2:
                    str = "Courier-Oblique";
                    break;
                case 3:
                    str = "Courier-BoldOblique";
                    break;
                default:
                    str = "Courier";
                    break;
            }
        } else {
            switch (i2) {
                case 3:
                    switch (i & 3) {
                        case 1:
                            str = "Times-Bold";
                            break;
                        case 2:
                            str = "Times-Italic";
                            break;
                        case 3:
                            str = "Times-BoldItalic";
                            break;
                        default:
                            str = "Times-Roman";
                            break;
                    }
                case 4:
                    str2 = "Symbol";
                    if (z) {
                        str3 = "Symbol";
                        break;
                    }
                    break;
                case 5:
                    str2 = "ZapfDingbats";
                    if (z) {
                        str3 = "ZapfDingbats";
                        break;
                    }
                    break;
                default:
                    switch (i & 3) {
                        case 1:
                            str = "Helvetica-Bold";
                            break;
                        case 2:
                            str = "Helvetica-Oblique";
                            break;
                        case 3:
                            str = "Helvetica-BoldOblique";
                            break;
                        default:
                            str = "Helvetica";
                            break;
                    }
            }
            str = str2;
        }
        try {
            return BaseFont.createFont(str, str3, false);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public boolean isStandardFont() {
        return this.family == FontFamily.UNDEFINED && this.size == -1.0f && this.style == -1 && this.color == null && this.baseFont == null;
    }

    public Font difference(Font font) {
        if (font == null) {
            return this;
        }
        float f = font.size;
        if (f == -1.0f) {
            f = this.size;
        }
        int i = this.style;
        int style2 = font.getStyle();
        int i2 = -1;
        if (!(i == -1 && style2 == -1)) {
            if (i == -1) {
                i = 0;
            }
            if (style2 == -1) {
                style2 = 0;
            }
            i2 = i | style2;
        }
        BaseColor baseColor = font.color;
        if (baseColor == null) {
            baseColor = this.color;
        }
        if (font.baseFont != null) {
            return new Font(font.baseFont, f, i2, baseColor);
        }
        if (font.getFamily() != FontFamily.UNDEFINED) {
            return new Font(font.family, f, i2, baseColor);
        }
        if (this.baseFont == null) {
            return new Font(this.family, f, i2, baseColor);
        }
        if (i2 == i) {
            return new Font(this.baseFont, f, i2, baseColor);
        }
        return FontFactory.getFont(getFamilyname(), f, i2, baseColor);
    }
}
