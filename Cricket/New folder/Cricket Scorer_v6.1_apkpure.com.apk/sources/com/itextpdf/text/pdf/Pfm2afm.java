package com.itextpdf.text.pdf;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.xmp.XMPError;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public final class Pfm2afm {
    private int[] Win2PSStd = {0, 0, 0, 0, 197, 198, 199, 0, XMPError.BADRDF, 0, 205, 206, 207, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 33, 34, 35, 36, 37, 38, 169, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 193, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 0, 184, 166, 185, 188, 178, 179, 195, PsExtractor.PRIVATE_STREAM_1, 0, 172, 234, 0, 0, 0, 0, 96, 0, 170, 186, 183, 177, 208, 196, 0, 0, 173, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 0, 0, 0, 0, 161, 162, 163, 168, 165, 0, 167, 200, 0, 227, 171, 0, 0, 0, 197, 0, 0, 0, 0, 194, 0, 182, 180, XMPError.BADXMP, 0, 235, 187, 0, 0, 0, 191, 0, 0, 0, 0, 0, 0, 225, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 233, 0, 0, 0, 0, 0, 0, 251, 0, 0, 0, 0, 0, 0, 241, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 249, 0, 0, 0, 0, 0, 0, 0};
    private String[] WinChars = {"W00", "W01", "W02", "W03", "macron", "breve", "dotaccent", "W07", "ring", "W09", "W0a", "W0b", "W0c", "W0d", "W0e", "W0f", "hungarumlaut", "ogonek", "caron", "W13", "W14", "W15", "W16", "W17", "W18", "W19", "W1a", "W1b", "W1c", "W1d", "W1e", "W1f", "space", "exclam", "quotedbl", "numbersign", "dollar", "percent", "ampersand", "quotesingle", "parenleft", "parenright", "asterisk", "plus", "comma", "hyphen", "period", "slash", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "colon", "semicolon", "less", "equal", "greater", "question", "at", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "bracketleft", "backslash", "bracketright", "asciicircum", "underscore", "grave", "a", HtmlTags.B, "c", "d", "e", "f", "g", "h", HtmlTags.I, "j", "k", "l", "m", "n", "o", "p", "q", "r", HtmlTags.S, "t", HtmlTags.U, "v", "w", "x", "y", "z", "braceleft", "bar", "braceright", "asciitilde", "W7f", "euro", "W81", "quotesinglbase", "florin", "quotedblbase", "ellipsis", "dagger", "daggerdbl", "circumflex", "perthousand", "Scaron", "guilsinglleft", "OE", "W8d", "Zcaron", "W8f", "W90", "quoteleft", "quoteright", "quotedblleft", "quotedblright", "bullet", "endash", "emdash", "tilde", "trademark", "scaron", "guilsinglright", "oe", "W9d", "zcaron", "Ydieresis", "reqspace", "exclamdown", "cent", "sterling", FirebaseAnalytics.Param.CURRENCY, "yen", "brokenbar", "section", "dieresis", "copyright", "ordfeminine", "guillemotleft", "logicalnot", "syllable", "registered", "macron", "degree", "plusminus", "twosuperior", "threesuperior", "acute", "mu", "paragraph", "periodcentered", "cedilla", "onesuperior", "ordmasculine", "guillemotright", "onequarter", "onehalf", "threequarters", "questiondown", "Agrave", "Aacute", "Acircumflex", "Atilde", "Adieresis", "Aring", "AE", "Ccedilla", "Egrave", "Eacute", "Ecircumflex", "Edieresis", "Igrave", "Iacute", "Icircumflex", "Idieresis", "Eth", "Ntilde", "Ograve", "Oacute", "Ocircumflex", "Otilde", "Odieresis", "multiply", "Oslash", "Ugrave", "Uacute", "Ucircumflex", "Udieresis", "Yacute", "Thorn", "germandbls", "agrave", "aacute", "acircumflex", "atilde", "adieresis", "aring", "ae", "ccedilla", "egrave", "eacute", "ecircumflex", "edieresis", "igrave", "iacute", "icircumflex", "idieresis", "eth", "ntilde", "ograve", "oacute", "ocircumflex", "otilde", "odieresis", "divide", "oslash", "ugrave", "uacute", "ucircumflex", "udieresis", "yacute", "thorn", "ydieresis"};
    private int[] WinClass = {0, 0, 0, 0, 2, 2, 2, 0, 2, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private short ascender;
    private short ascent;
    private short avgwidth;
    private int bitoff;
    private int bits;
    private byte brkchar;
    private short capheight;
    private byte charset;
    private int chartab;
    private String copyright;
    private byte defchar;
    private short descender;
    private int device;
    private short extleading;
    private short extlen;
    private int face;
    private int firstchar;
    private int fontname;
    private int h_len;
    private short horres;
    private RandomAccessFileOrArray in;
    private short intleading;
    private boolean isMono;
    private byte italic;
    private int kernpairs;
    private byte kind;
    private int lastchar;
    private short maxwidth;
    private PrintWriter out;
    private byte overs;
    private short pixheight;
    private short pixwidth;
    private short points;
    private int psext;
    private int res1;
    private int res2;
    private short type;
    private byte uline;
    private short verres;
    private short vers;
    private short weight;
    private short widthby;
    private short xheight;

    private Pfm2afm(RandomAccessFileOrArray randomAccessFileOrArray, OutputStream outputStream) throws IOException {
        this.in = randomAccessFileOrArray;
        this.out = new PrintWriter(new OutputStreamWriter(outputStream, "ISO-8859-1"));
    }

    public static void convert(RandomAccessFileOrArray randomAccessFileOrArray, OutputStream outputStream) throws IOException {
        Pfm2afm pfm2afm = new Pfm2afm(randomAccessFileOrArray, outputStream);
        pfm2afm.openpfm();
        pfm2afm.putheader();
        pfm2afm.putchartab();
        pfm2afm.putkerntab();
        pfm2afm.puttrailer();
        pfm2afm.out.flush();
    }

    private String readString(int i) throws IOException {
        byte[] bArr = new byte[i];
        this.in.readFully(bArr);
        int i2 = 0;
        while (i2 < bArr.length && bArr[i2] != 0) {
            i2++;
        }
        return new String(bArr, 0, i2, "ISO-8859-1");
    }

    private String readString() throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int read = this.in.read();
            if (read <= 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append((char) read);
        }
    }

    private void outval(int i) {
        this.out.print(' ');
        this.out.print(i);
    }

    private void outchar(int i, int i2, String str) {
        this.out.print("C ");
        outval(i);
        this.out.print(" ; WX ");
        outval(i2);
        if (str != null) {
            this.out.print(" ; N ");
            this.out.print(str);
        }
        this.out.print(" ;\n");
    }

    private void openpfm() throws IOException {
        this.in.seek(0);
        this.vers = this.in.readShortLE();
        this.h_len = this.in.readIntLE();
        this.copyright = readString(60);
        this.type = this.in.readShortLE();
        this.points = this.in.readShortLE();
        this.verres = this.in.readShortLE();
        this.horres = this.in.readShortLE();
        this.ascent = this.in.readShortLE();
        this.intleading = this.in.readShortLE();
        this.extleading = this.in.readShortLE();
        this.italic = (byte) this.in.read();
        this.uline = (byte) this.in.read();
        this.overs = (byte) this.in.read();
        this.weight = this.in.readShortLE();
        this.charset = (byte) this.in.read();
        this.pixwidth = this.in.readShortLE();
        this.pixheight = this.in.readShortLE();
        this.kind = (byte) this.in.read();
        this.avgwidth = this.in.readShortLE();
        this.maxwidth = this.in.readShortLE();
        this.firstchar = this.in.read();
        this.lastchar = this.in.read();
        this.defchar = (byte) this.in.read();
        this.brkchar = (byte) this.in.read();
        this.widthby = this.in.readShortLE();
        this.device = this.in.readIntLE();
        this.face = this.in.readIntLE();
        this.bits = this.in.readIntLE();
        this.bitoff = this.in.readIntLE();
        this.extlen = this.in.readShortLE();
        this.psext = this.in.readIntLE();
        this.chartab = this.in.readIntLE();
        this.res1 = this.in.readIntLE();
        this.kernpairs = this.in.readIntLE();
        this.res2 = this.in.readIntLE();
        this.fontname = this.in.readIntLE();
        if (((long) this.h_len) != this.in.length() || this.extlen != 30 || this.fontname < 75 || this.fontname > 512) {
            throw new IOException(MessageLocalization.getComposedMessage("not.a.valid.pfm.file", new Object[0]));
        }
        this.in.seek((long) (this.psext + 14));
        this.capheight = this.in.readShortLE();
        this.xheight = this.in.readShortLE();
        this.ascender = this.in.readShortLE();
        this.descender = this.in.readShortLE();
    }

    private void putheader() throws IOException {
        this.out.print("StartFontMetrics 2.0\n");
        if (this.copyright.length() > 0) {
            PrintWriter printWriter = this.out;
            printWriter.print("Comment " + this.copyright + 10);
        }
        this.out.print("FontName ");
        this.in.seek((long) this.fontname);
        String readString = readString();
        this.out.print(readString);
        this.out.print("\nEncodingScheme ");
        if (this.charset != 0) {
            this.out.print("FontSpecific\n");
        } else {
            this.out.print("AdobeStandardEncoding\n");
        }
        PrintWriter printWriter2 = this.out;
        printWriter2.print("FullName " + readString.replace('-', ' '));
        if (this.face != 0) {
            this.in.seek((long) this.face);
            PrintWriter printWriter3 = this.out;
            printWriter3.print("\nFamilyName " + readString());
        }
        this.out.print("\nWeight ");
        if (this.weight > 475 || readString.toLowerCase().indexOf("bold") >= 0) {
            this.out.print("Bold");
        } else if ((this.weight < 325 && this.weight != 0) || readString.toLowerCase().indexOf("light") >= 0) {
            this.out.print("Light");
        } else if (readString.toLowerCase().indexOf("black") >= 0) {
            this.out.print("Black");
        } else {
            this.out.print("Medium");
        }
        this.out.print("\nItalicAngle ");
        if (this.italic != 0 || readString.toLowerCase().indexOf("italic") >= 0) {
            this.out.print("-12.00");
        } else {
            this.out.print("0");
        }
        this.out.print("\nIsFixedPitch ");
        if ((this.kind & 1) == 0 || this.avgwidth == this.maxwidth) {
            this.out.print(PdfBoolean.TRUE);
            this.isMono = true;
        } else {
            this.out.print(PdfBoolean.FALSE);
            this.isMono = false;
        }
        this.out.print("\nFontBBox");
        if (this.isMono) {
            outval(-20);
        } else {
            outval(-100);
        }
        outval(-(this.descender + 5));
        outval(this.maxwidth + 10);
        outval(this.ascent + 5);
        this.out.print("\nCapHeight");
        outval(this.capheight);
        this.out.print("\nXHeight");
        outval(this.xheight);
        this.out.print("\nDescender");
        outval(-this.descender);
        this.out.print("\nAscender");
        outval(this.ascender);
        this.out.print(10);
    }

    private void putchartab() throws IOException {
        int i = (this.lastchar - this.firstchar) + 1;
        int[] iArr = new int[i];
        this.in.seek((long) this.chartab);
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = this.in.readUnsignedShortLE();
        }
        int[] iArr2 = new int[256];
        if (this.charset == 0) {
            for (int i3 = this.firstchar; i3 <= this.lastchar; i3++) {
                if (this.Win2PSStd[i3] != 0) {
                    iArr2[this.Win2PSStd[i3]] = i3;
                }
            }
        }
        this.out.print("StartCharMetrics");
        outval(i);
        this.out.print(10);
        if (this.charset != 0) {
            for (int i4 = this.firstchar; i4 <= this.lastchar; i4++) {
                if (iArr[i4 - this.firstchar] != 0) {
                    outchar(i4, iArr[i4 - this.firstchar], (String) null);
                }
            }
        } else {
            for (int i5 = 0; i5 < 256; i5++) {
                int i6 = iArr2[i5];
                if (i6 != 0) {
                    outchar(i5, iArr[i6 - this.firstchar], this.WinChars[i6]);
                    iArr[i6 - this.firstchar] = 0;
                }
            }
            for (int i7 = this.firstchar; i7 <= this.lastchar; i7++) {
                if (iArr[i7 - this.firstchar] != 0) {
                    outchar(-1, iArr[i7 - this.firstchar], this.WinChars[i7]);
                }
            }
        }
        this.out.print("EndCharMetrics\n");
    }

    private void putkerntab() throws IOException {
        if (this.kernpairs != 0) {
            this.in.seek((long) this.kernpairs);
            int[] iArr = new int[(this.in.readUnsignedShortLE() * 3)];
            int i = 0;
            int i2 = 0;
            while (i < iArr.length) {
                int i3 = i + 1;
                iArr[i] = this.in.read();
                int i4 = i3 + 1;
                iArr[i3] = this.in.read();
                int i5 = i4 + 1;
                int readShortLE = this.in.readShortLE();
                iArr[i4] = readShortLE;
                if (readShortLE != 0) {
                    i2++;
                }
                i = i5;
            }
            if (i2 != 0) {
                this.out.print("StartKernData\nStartKernPairs");
                outval(i2);
                this.out.print(10);
                for (int i6 = 0; i6 < iArr.length; i6 += 3) {
                    int i7 = i6 + 2;
                    if (iArr[i7] != 0) {
                        this.out.print("KPX ");
                        this.out.print(this.WinChars[iArr[i6]]);
                        this.out.print(' ');
                        this.out.print(this.WinChars[iArr[i6 + 1]]);
                        outval(iArr[i7]);
                        this.out.print(10);
                    }
                }
                this.out.print("EndKernPairs\nEndKernData\n");
            }
        }
    }

    private void puttrailer() {
        this.out.print("EndFontMetrics\n");
    }
}
