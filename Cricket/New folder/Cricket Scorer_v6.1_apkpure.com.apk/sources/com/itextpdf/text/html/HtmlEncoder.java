package com.itextpdf.text.html;

import com.itextpdf.text.BaseColor;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public final class HtmlEncoder {
    private static final String[] HTML_CODE = new String[256];
    private static final Set<String> NEWLINETAGS = new HashSet();

    public static String getAlignment(int i) {
        switch (i) {
            case 0:
                return "left";
            case 1:
                return "center";
            case 2:
                return "right";
            case 3:
            case 8:
                return HtmlTags.ALIGN_JUSTIFY;
            case 4:
                return HtmlTags.ALIGN_TOP;
            case 5:
                return HtmlTags.ALIGN_MIDDLE;
            case 6:
                return HtmlTags.ALIGN_BOTTOM;
            case 7:
                return HtmlTags.ALIGN_BASELINE;
            default:
                return "";
        }
    }

    private HtmlEncoder() {
    }

    static {
        int i;
        int i2;
        for (int i3 = 0; i3 < 10; i3++) {
            HTML_CODE[i3] = "&#00" + i3 + ";";
        }
        int i4 = 10;
        while (true) {
            i = 32;
            if (i4 >= 32) {
                break;
            }
            HTML_CODE[i4] = "&#0" + i4 + ";";
            i4++;
        }
        while (true) {
            if (i >= 128) {
                break;
            }
            HTML_CODE[i] = String.valueOf((char) i);
            i++;
        }
        HTML_CODE[9] = "\t";
        HTML_CODE[10] = "<br />\n";
        HTML_CODE[34] = "&quot;";
        HTML_CODE[38] = "&amp;";
        HTML_CODE[60] = "&lt;";
        HTML_CODE[62] = "&gt;";
        for (i2 = 128; i2 < 256; i2++) {
            HTML_CODE[i2] = "&#" + i2 + ";";
        }
        NEWLINETAGS.add("p");
        NEWLINETAGS.add(HtmlTags.BLOCKQUOTE);
        NEWLINETAGS.add("br");
    }

    public static String encode(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt < 256) {
                stringBuffer.append(HTML_CODE[charAt]);
            } else {
                stringBuffer.append("&#");
                stringBuffer.append(charAt);
                stringBuffer.append(';');
            }
        }
        return stringBuffer.toString();
    }

    public static String encode(BaseColor baseColor) {
        StringBuffer stringBuffer = new StringBuffer("#");
        if (baseColor.getRed() < 16) {
            stringBuffer.append('0');
        }
        stringBuffer.append(Integer.toString(baseColor.getRed(), 16));
        if (baseColor.getGreen() < 16) {
            stringBuffer.append('0');
        }
        stringBuffer.append(Integer.toString(baseColor.getGreen(), 16));
        if (baseColor.getBlue() < 16) {
            stringBuffer.append('0');
        }
        stringBuffer.append(Integer.toString(baseColor.getBlue(), 16));
        return stringBuffer.toString();
    }

    public static boolean isNewLineTag(String str) {
        return NEWLINETAGS.contains(str);
    }
}
