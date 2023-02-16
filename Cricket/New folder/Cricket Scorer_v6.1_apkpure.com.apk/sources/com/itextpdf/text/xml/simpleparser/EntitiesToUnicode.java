package com.itextpdf.text.xml.simpleparser;

import com.crashlytics.android.beta.Beta;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.xml.xmp.PdfProperties;
import java.util.HashMap;
import java.util.Map;

public class EntitiesToUnicode {
    private static final Map<String, Character> MAP = new HashMap();

    static {
        MAP.put("nbsp", 160);
        MAP.put("iexcl", 161);
        MAP.put("cent", 162);
        MAP.put("pound", 163);
        MAP.put("curren", 164);
        MAP.put("yen", 165);
        MAP.put("brvbar", 166);
        MAP.put("sect", 167);
        MAP.put("uml", 168);
        MAP.put("copy", 169);
        MAP.put("ordf", 170);
        MAP.put("laquo", 171);
        MAP.put("not", 172);
        MAP.put("shy", 173);
        MAP.put("reg", 174);
        MAP.put("macr", 175);
        MAP.put("deg", 176);
        MAP.put("plusmn", 177);
        MAP.put("sup2", 178);
        MAP.put("sup3", 179);
        MAP.put("acute", 180);
        MAP.put("micro", 181);
        MAP.put("para", 182);
        MAP.put("middot", 183);
        MAP.put("cedil", 184);
        MAP.put("sup1", 185);
        MAP.put("ordm", 186);
        MAP.put("raquo", 187);
        MAP.put("frac14", 188);
        MAP.put("frac12", 189);
        MAP.put("frac34", 190);
        MAP.put("iquest", 191);
        MAP.put("Agrave", 192);
        MAP.put("Aacute", 193);
        MAP.put("Acirc", 194);
        MAP.put("Atilde", Character.valueOf(Barcode128.DEL));
        MAP.put("Auml", Character.valueOf(Barcode128.FNC3));
        MAP.put("Aring", Character.valueOf(Barcode128.FNC2));
        MAP.put("AElig", Character.valueOf(Barcode128.SHIFT));
        MAP.put("Ccedil", Character.valueOf(Barcode128.CODE_C));
        MAP.put("Egrave", 200);
        MAP.put("Eacute", 201);
        MAP.put("Ecirc", Character.valueOf(Barcode128.FNC1));
        MAP.put("Euml", Character.valueOf(Barcode128.STARTA));
        MAP.put("Igrave", Character.valueOf(Barcode128.STARTB));
        MAP.put("Iacute", Character.valueOf(Barcode128.STARTC));
        MAP.put("Icirc", 206);
        MAP.put("Iuml", 207);
        MAP.put("ETH", 208);
        MAP.put("Ntilde", 209);
        MAP.put("Ograve", 210);
        MAP.put("Oacute", 211);
        MAP.put("Ocirc", 212);
        MAP.put("Otilde", 213);
        MAP.put("Ouml", 214);
        MAP.put("times", 215);
        MAP.put("Oslash", 216);
        MAP.put("Ugrave", 217);
        MAP.put("Uacute", 218);
        MAP.put("Ucirc", 219);
        MAP.put("Uuml", 220);
        MAP.put("Yacute", 221);
        MAP.put("THORN", 222);
        MAP.put("szlig", 223);
        MAP.put("agrave", 224);
        MAP.put("aacute", 225);
        MAP.put("acirc", 226);
        MAP.put("atilde", 227);
        MAP.put("auml", 228);
        MAP.put("aring", 229);
        MAP.put("aelig", 230);
        MAP.put("ccedil", 231);
        MAP.put("egrave", 232);
        MAP.put("eacute", 233);
        MAP.put("ecirc", 234);
        MAP.put("euml", 235);
        MAP.put("igrave", 236);
        MAP.put("iacute", 237);
        MAP.put("icirc", 238);
        MAP.put("iuml", 239);
        MAP.put("eth", 240);
        MAP.put("ntilde", 241);
        MAP.put("ograve", 242);
        MAP.put("oacute", 243);
        MAP.put("ocirc", 244);
        MAP.put("otilde", 245);
        MAP.put("ouml", 246);
        MAP.put("divide", 247);
        MAP.put("oslash", 248);
        MAP.put("ugrave", 249);
        MAP.put("uacute", 250);
        MAP.put("ucirc", 251);
        MAP.put("uuml", 252);
        MAP.put("yacute", 253);
        MAP.put("thorn", 254);
        MAP.put("yuml", 255);
        MAP.put("fnof", 402);
        MAP.put("Alpha", 913);
        MAP.put(Beta.TAG, 914);
        MAP.put("Gamma", 915);
        MAP.put("Delta", 916);
        MAP.put("Epsilon", 917);
        MAP.put("Zeta", 918);
        MAP.put("Eta", 919);
        MAP.put("Theta", 920);
        MAP.put("Iota", 921);
        MAP.put("Kappa", 922);
        MAP.put("Lambda", 923);
        MAP.put("Mu", 924);
        MAP.put("Nu", 925);
        MAP.put("Xi", 926);
        MAP.put("Omicron", 927);
        MAP.put("Pi", 928);
        MAP.put("Rho", 929);
        MAP.put("Sigma", 931);
        MAP.put("Tau", 932);
        MAP.put("Upsilon", 933);
        MAP.put("Phi", 934);
        MAP.put("Chi", 935);
        MAP.put("Psi", 936);
        MAP.put("Omega", 937);
        MAP.put("alpha", 945);
        MAP.put("beta", 946);
        MAP.put("gamma", 947);
        MAP.put("delta", 948);
        MAP.put("epsilon", 949);
        MAP.put("zeta", 950);
        MAP.put("eta", 951);
        MAP.put("theta", 952);
        MAP.put("iota", 953);
        MAP.put("kappa", 954);
        MAP.put("lambda", 955);
        MAP.put("mu", 956);
        MAP.put("nu", 957);
        MAP.put("xi", 958);
        MAP.put("omicron", 959);
        MAP.put("pi", 960);
        MAP.put("rho", 961);
        MAP.put("sigmaf", 962);
        MAP.put("sigma", 963);
        MAP.put("tau", 964);
        MAP.put("upsilon", 965);
        MAP.put("phi", 966);
        MAP.put("chi", 967);
        MAP.put("psi", 968);
        MAP.put("omega", 969);
        MAP.put("thetasym", 977);
        MAP.put("upsih", 978);
        MAP.put("piv", 982);
        MAP.put("bull", 8226);
        MAP.put("hellip", 8230);
        MAP.put("prime", 8242);
        MAP.put("Prime", 8243);
        MAP.put("oline", 8254);
        MAP.put("frasl", 8260);
        MAP.put("weierp", 8472);
        MAP.put("image", 8465);
        MAP.put("real", 8476);
        MAP.put("trade", 8482);
        MAP.put("alefsym", 8501);
        MAP.put("larr", 8592);
        MAP.put("uarr", 8593);
        MAP.put("rarr", 8594);
        MAP.put("darr", 8595);
        MAP.put("harr", 8596);
        MAP.put("crarr", 8629);
        MAP.put("lArr", 8656);
        MAP.put("uArr", 8657);
        MAP.put("rArr", 8658);
        MAP.put("dArr", 8659);
        MAP.put("hArr", 8660);
        MAP.put("forall", 8704);
        MAP.put(PdfProperties.PART, 8706);
        MAP.put("exist", 8707);
        MAP.put("empty", 8709);
        MAP.put("nabla", 8711);
        MAP.put("isin", 8712);
        MAP.put("notin", 8713);
        MAP.put("ni", 8715);
        MAP.put("prod", 8719);
        MAP.put("sum", 8721);
        MAP.put("minus", 8722);
        MAP.put("lowast", 8727);
        MAP.put("radic", 8730);
        MAP.put("prop", 8733);
        MAP.put("infin", 8734);
        MAP.put("ang", 8736);
        MAP.put("and", 8743);
        MAP.put("or", 8744);
        MAP.put("cap", 8745);
        MAP.put("cup", 8746);
        MAP.put("int", 8747);
        MAP.put("there4", 8756);
        MAP.put("sim", 8764);
        MAP.put("cong", 8773);
        MAP.put("asymp", 8776);
        MAP.put("ne", 8800);
        MAP.put("equiv", 8801);
        MAP.put("le", 8804);
        MAP.put("ge", 8805);
        MAP.put(HtmlTags.SUB, 8834);
        MAP.put(HtmlTags.SUP, 8835);
        MAP.put("nsub", 8836);
        MAP.put("sube", 8838);
        MAP.put("supe", 8839);
        MAP.put("oplus", 8853);
        MAP.put("otimes", 8855);
        MAP.put("perp", 8869);
        MAP.put("sdot", 8901);
        MAP.put("lceil", 8968);
        MAP.put("rceil", 8969);
        MAP.put("lfloor", 8970);
        MAP.put("rfloor", 8971);
        MAP.put("lang", 9001);
        MAP.put("rang", 9002);
        MAP.put("loz", 9674);
        MAP.put("spades", 9824);
        MAP.put("clubs", 9827);
        MAP.put("hearts", 9829);
        MAP.put("diams", 9830);
        MAP.put("quot", '\"');
        MAP.put("amp", '&');
        MAP.put("apos", '\'');
        MAP.put("lt", '<');
        MAP.put("gt", '>');
        MAP.put("OElig", 338);
        MAP.put("oelig", 339);
        MAP.put("Scaron", 352);
        MAP.put("scaron", 353);
        MAP.put("Yuml", 376);
        MAP.put("circ", 710);
        MAP.put("tilde", 732);
        MAP.put("ensp", 8194);
        MAP.put("emsp", 8195);
        MAP.put("thinsp", 8201);
        MAP.put("zwnj", 8204);
        MAP.put("zwj", 8205);
        MAP.put("lrm", 8206);
        MAP.put("rlm", 8207);
        MAP.put("ndash", 8211);
        MAP.put("mdash", 8212);
        MAP.put("lsquo", 8216);
        MAP.put("rsquo", 8217);
        MAP.put("sbquo", 8218);
        MAP.put("ldquo", 8220);
        MAP.put("rdquo", 8221);
        MAP.put("bdquo", 8222);
        MAP.put("dagger", 8224);
        MAP.put("Dagger", 8225);
        MAP.put("permil", 8240);
        MAP.put("lsaquo", 8249);
        MAP.put("rsaquo", 8250);
        MAP.put("euro", 8364);
    }

    public static char decodeEntity(String str) {
        if (str.startsWith("#x")) {
            try {
                return (char) Integer.parseInt(str.substring(2), 16);
            } catch (NumberFormatException unused) {
                return 0;
            }
        } else if (str.startsWith("#")) {
            try {
                return (char) Integer.parseInt(str.substring(1));
            } catch (NumberFormatException unused2) {
                return 0;
            }
        } else {
            Character ch = MAP.get(str);
            if (ch == null) {
                return 0;
            }
            return ch.charValue();
        }
    }

    public static String decodeString(String str) {
        int i;
        int indexOf = str.indexOf(38);
        if (indexOf == -1) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.substring(0, indexOf));
        while (true) {
            int indexOf2 = str.indexOf(59, indexOf);
            if (indexOf2 == -1) {
                stringBuffer.append(str.substring(indexOf));
                return stringBuffer.toString();
            }
            int indexOf3 = str.indexOf(38, indexOf + 1);
            while (true) {
                int i2 = indexOf3;
                i = indexOf;
                indexOf = i2;
                if (indexOf == -1 || indexOf >= indexOf2) {
                    char decodeEntity = decodeEntity(str.substring(i + 1, indexOf2));
                    int i3 = indexOf2 + 1;
                } else {
                    stringBuffer.append(str.substring(i, indexOf));
                    indexOf3 = str.indexOf(38, indexOf + 1);
                }
            }
            char decodeEntity2 = decodeEntity(str.substring(i + 1, indexOf2));
            int i32 = indexOf2 + 1;
            if (str.length() < i32) {
                return stringBuffer.toString();
            }
            if (decodeEntity2 == 0) {
                stringBuffer.append(str.substring(i, i32));
            } else {
                stringBuffer.append(decodeEntity2);
            }
            indexOf = str.indexOf(38, indexOf2);
            if (indexOf == -1) {
                stringBuffer.append(str.substring(i32));
                return stringBuffer.toString();
            }
            stringBuffer.append(str.substring(i32, indexOf));
        }
    }
}
