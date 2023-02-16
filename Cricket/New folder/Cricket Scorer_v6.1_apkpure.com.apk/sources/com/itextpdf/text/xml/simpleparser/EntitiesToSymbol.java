package com.itextpdf.text.xml.simpleparser;

import com.crashlytics.android.beta.Beta;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.xml.xmp.PdfProperties;
import java.util.HashMap;
import java.util.Map;

public class EntitiesToSymbol {
    private static final Map<String, Character> MAP = new HashMap();

    static {
        MAP.put("169", 227);
        MAP.put("172", 216);
        MAP.put("174", 210);
        MAP.put("177", 177);
        MAP.put("215", 180);
        MAP.put("247", 184);
        MAP.put("8230", 188);
        MAP.put("8242", 162);
        MAP.put("8243", 178);
        MAP.put("8260", 164);
        MAP.put("8364", 240);
        MAP.put("8465", 193);
        MAP.put("8472", Character.valueOf(Barcode128.DEL));
        MAP.put("8476", 194);
        MAP.put("8482", 212);
        MAP.put("8501", 192);
        MAP.put("8592", 172);
        MAP.put("8593", 173);
        MAP.put("8594", 174);
        MAP.put("8595", 175);
        MAP.put("8596", 171);
        MAP.put("8629", 191);
        MAP.put("8656", 220);
        MAP.put("8657", 221);
        MAP.put("8658", 222);
        MAP.put("8659", 223);
        MAP.put("8660", 219);
        MAP.put("8704", '\"');
        MAP.put("8706", 182);
        MAP.put("8707", '$');
        MAP.put("8709", Character.valueOf(Barcode128.SHIFT));
        MAP.put("8711", 209);
        MAP.put("8712", 206);
        MAP.put("8713", 207);
        MAP.put("8717", '\'');
        MAP.put("8719", 213);
        MAP.put("8721", 229);
        MAP.put("8722", '-');
        MAP.put("8727", '*');
        MAP.put("8729", 183);
        MAP.put("8730", 214);
        MAP.put("8733", 181);
        MAP.put("8734", 165);
        MAP.put("8736", 208);
        MAP.put("8743", 217);
        MAP.put("8744", 218);
        MAP.put("8745", Character.valueOf(Barcode128.CODE_C));
        MAP.put("8746", 200);
        MAP.put("8747", 242);
        MAP.put("8756", '\\');
        MAP.put("8764", '~');
        MAP.put("8773", '@');
        MAP.put("8776", 187);
        MAP.put("8800", 185);
        MAP.put("8801", 186);
        MAP.put("8804", 163);
        MAP.put("8805", 179);
        MAP.put("8834", Character.valueOf(Barcode128.STARTB));
        MAP.put("8835", 201);
        MAP.put("8836", Character.valueOf(Barcode128.STARTA));
        MAP.put("8838", Character.valueOf(Barcode128.STARTC));
        MAP.put("8839", Character.valueOf(Barcode128.FNC1));
        MAP.put("8853", Character.valueOf(Barcode128.FNC2));
        MAP.put("8855", Character.valueOf(Barcode128.FNC3));
        MAP.put("8869", '^');
        MAP.put("8901", 215);
        MAP.put("8992", 243);
        MAP.put("8993", 245);
        MAP.put("9001", 225);
        MAP.put("9002", 241);
        MAP.put("913", 'A');
        MAP.put("914", 'B');
        MAP.put("915", 'G');
        MAP.put("916", 'D');
        MAP.put("917", 'E');
        MAP.put("918", 'Z');
        MAP.put("919", 'H');
        MAP.put("920", 'Q');
        MAP.put("921", 'I');
        MAP.put("922", 'K');
        MAP.put("923", 'L');
        MAP.put("924", 'M');
        MAP.put("925", 'N');
        MAP.put("926", 'X');
        MAP.put("927", 'O');
        MAP.put("928", 'P');
        MAP.put("929", 'R');
        MAP.put("931", 'S');
        MAP.put("932", 'T');
        MAP.put("933", 'U');
        MAP.put("934", 'F');
        MAP.put("935", 'C');
        MAP.put("936", 'Y');
        MAP.put("937", 'W');
        MAP.put("945", 'a');
        MAP.put("946", 'b');
        MAP.put("947", Character.valueOf(Barcode128.START_A));
        MAP.put("948", Character.valueOf(Barcode128.CODE_AC_TO_B));
        MAP.put("949", Character.valueOf(Barcode128.CODE_BC_TO_A));
        MAP.put("950", 'z');
        MAP.put("951", Character.valueOf(Barcode128.START_B));
        MAP.put("952", 'q');
        MAP.put("953", Character.valueOf(Barcode128.START_C));
        MAP.put("954", 'k');
        MAP.put("955", 'l');
        MAP.put("956", 'm');
        MAP.put("957", 'n');
        MAP.put("958", 'x');
        MAP.put("959", 'o');
        MAP.put("960", 'p');
        MAP.put("961", 'r');
        MAP.put("962", 'V');
        MAP.put("963", 's');
        MAP.put("964", 't');
        MAP.put("965", 'u');
        MAP.put("966", Character.valueOf(Barcode128.FNC1_INDEX));
        MAP.put("967", Character.valueOf(Barcode128.CODE_AB_TO_C));
        MAP.put("9674", 224);
        MAP.put("968", 'y');
        MAP.put("969", 'w');
        MAP.put("977", 'J');
        MAP.put("978", 161);
        MAP.put("981", 'j');
        MAP.put("982", 'v');
        MAP.put("9824", 170);
        MAP.put("9827", 167);
        MAP.put("9829", 169);
        MAP.put("9830", 168);
        MAP.put("Alpha", 'A');
        MAP.put(Beta.TAG, 'B');
        MAP.put("Chi", 'C');
        MAP.put("Delta", 'D');
        MAP.put("Epsilon", 'E');
        MAP.put("Eta", 'H');
        MAP.put("Gamma", 'G');
        MAP.put("Iota", 'I');
        MAP.put("Kappa", 'K');
        MAP.put("Lambda", 'L');
        MAP.put("Mu", 'M');
        MAP.put("Nu", 'N');
        MAP.put("Omega", 'W');
        MAP.put("Omicron", 'O');
        MAP.put("Phi", 'F');
        MAP.put("Pi", 'P');
        MAP.put("Prime", 178);
        MAP.put("Psi", 'Y');
        MAP.put("Rho", 'R');
        MAP.put("Sigma", 'S');
        MAP.put("Tau", 'T');
        MAP.put("Theta", 'Q');
        MAP.put("Upsilon", 'U');
        MAP.put("Xi", 'X');
        MAP.put("Zeta", 'Z');
        MAP.put("alefsym", 192);
        MAP.put("alpha", 'a');
        MAP.put("and", 217);
        MAP.put("ang", 208);
        MAP.put("asymp", 187);
        MAP.put("beta", 'b');
        MAP.put("cap", Character.valueOf(Barcode128.CODE_C));
        MAP.put("chi", Character.valueOf(Barcode128.CODE_AB_TO_C));
        MAP.put("clubs", 167);
        MAP.put("cong", '@');
        MAP.put("copy", 211);
        MAP.put("crarr", 191);
        MAP.put("cup", 200);
        MAP.put("dArr", 223);
        MAP.put("darr", 175);
        MAP.put("delta", Character.valueOf(Barcode128.CODE_AC_TO_B));
        MAP.put("diams", 168);
        MAP.put("divide", 184);
        MAP.put("empty", Character.valueOf(Barcode128.SHIFT));
        MAP.put("epsilon", Character.valueOf(Barcode128.CODE_BC_TO_A));
        MAP.put("equiv", 186);
        MAP.put("eta", Character.valueOf(Barcode128.START_B));
        MAP.put("euro", 240);
        MAP.put("exist", '$');
        MAP.put("forall", '\"');
        MAP.put("frasl", 164);
        MAP.put("gamma", Character.valueOf(Barcode128.START_A));
        MAP.put("ge", 179);
        MAP.put("hArr", 219);
        MAP.put("harr", 171);
        MAP.put("hearts", 169);
        MAP.put("hellip", 188);
        MAP.put("horizontal arrow extender", 190);
        MAP.put("image", 193);
        MAP.put("infin", 165);
        MAP.put("int", 242);
        MAP.put("iota", Character.valueOf(Barcode128.START_C));
        MAP.put("isin", 206);
        MAP.put("kappa", 'k');
        MAP.put("lArr", 220);
        MAP.put("lambda", 'l');
        MAP.put("lang", 225);
        MAP.put("large brace extender", 239);
        MAP.put("large integral extender", 244);
        MAP.put("large left brace (bottom)", 238);
        MAP.put("large left brace (middle)", 237);
        MAP.put("large left brace (top)", 236);
        MAP.put("large left bracket (bottom)", 235);
        MAP.put("large left bracket (extender)", 234);
        MAP.put("large left bracket (top)", 233);
        MAP.put("large left parenthesis (bottom)", 232);
        MAP.put("large left parenthesis (extender)", 231);
        MAP.put("large left parenthesis (top)", 230);
        MAP.put("large right brace (bottom)", 254);
        MAP.put("large right brace (middle)", 253);
        MAP.put("large right brace (top)", 252);
        MAP.put("large right bracket (bottom)", 251);
        MAP.put("large right bracket (extender)", 250);
        MAP.put("large right bracket (top)", 249);
        MAP.put("large right parenthesis (bottom)", 248);
        MAP.put("large right parenthesis (extender)", 247);
        MAP.put("large right parenthesis (top)", 246);
        MAP.put("larr", 172);
        MAP.put("le", 163);
        MAP.put("lowast", '*');
        MAP.put("loz", 224);
        MAP.put("minus", '-');
        MAP.put("mu", 'm');
        MAP.put("nabla", 209);
        MAP.put("ne", 185);
        MAP.put("not", 216);
        MAP.put("notin", 207);
        MAP.put("nsub", Character.valueOf(Barcode128.STARTA));
        MAP.put("nu", 'n');
        MAP.put("omega", 'w');
        MAP.put("omicron", 'o');
        MAP.put("oplus", Character.valueOf(Barcode128.FNC2));
        MAP.put("or", 218);
        MAP.put("otimes", Character.valueOf(Barcode128.FNC3));
        MAP.put(PdfProperties.PART, 182);
        MAP.put("perp", '^');
        MAP.put("phi", Character.valueOf(Barcode128.FNC1_INDEX));
        MAP.put("pi", 'p');
        MAP.put("piv", 'v');
        MAP.put("plusmn", 177);
        MAP.put("prime", 162);
        MAP.put("prod", 213);
        MAP.put("prop", 181);
        MAP.put("psi", 'y');
        MAP.put("rArr", 222);
        MAP.put("radic", 214);
        MAP.put("radical extender", '`');
        MAP.put("rang", 241);
        MAP.put("rarr", 174);
        MAP.put("real", 194);
        MAP.put("reg", 210);
        MAP.put("rho", 'r');
        MAP.put("sdot", 215);
        MAP.put("sigma", 's');
        MAP.put("sigmaf", 'V');
        MAP.put("sim", '~');
        MAP.put("spades", 170);
        MAP.put(HtmlTags.SUB, Character.valueOf(Barcode128.STARTB));
        MAP.put("sube", Character.valueOf(Barcode128.STARTC));
        MAP.put("sum", 229);
        MAP.put(HtmlTags.SUP, 201);
        MAP.put("supe", Character.valueOf(Barcode128.FNC1));
        MAP.put("tau", 't');
        MAP.put("there4", '\\');
        MAP.put("theta", 'q');
        MAP.put("thetasym", 'J');
        MAP.put("times", 180);
        MAP.put("trade", 212);
        MAP.put("uArr", 221);
        MAP.put("uarr", 173);
        MAP.put("upsih", 161);
        MAP.put("upsilon", 'u');
        MAP.put("vertical arrow extender", 189);
        MAP.put("weierp", Character.valueOf(Barcode128.DEL));
        MAP.put("xi", 'x');
        MAP.put("zeta", 'z');
    }

    public static Chunk get(String str, Font font) {
        char correspondingSymbol = getCorrespondingSymbol(str);
        if (correspondingSymbol == 0) {
            try {
                return new Chunk(String.valueOf((char) Integer.parseInt(str)), font);
            } catch (Exception unused) {
                return new Chunk(str, font);
            }
        } else {
            return new Chunk(String.valueOf(correspondingSymbol), new Font(Font.FontFamily.SYMBOL, font.getSize(), font.getStyle(), font.getColor()));
        }
    }

    public static char getCorrespondingSymbol(String str) {
        Character ch = MAP.get(str);
        if (ch == null) {
            return 0;
        }
        return ch.charValue();
    }
}
