package com.itextpdf.text.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.exceptions.InvalidPdfException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseFont {
    public static final int ASCENT = 1;
    public static final int AWT_ASCENT = 9;
    public static final int AWT_DESCENT = 10;
    public static final int AWT_LEADING = 11;
    public static final int AWT_MAXADVANCE = 12;
    public static final int BBOXLLX = 5;
    public static final int BBOXLLY = 6;
    public static final int BBOXURX = 7;
    public static final int BBOXURY = 8;
    protected static final HashMap<String, PdfName> BuiltinFonts14 = new HashMap<>();
    public static final boolean CACHED = true;
    public static final int CAPHEIGHT = 2;
    public static final int[] CHAR_RANGE_ARABIC = {0, 127, 1536, 1663, 8352, 8399, 64336, 64511, 65136, 65279};
    public static final int[] CHAR_RANGE_CYRILLIC = {0, 127, 1024, 1327, 8192, 8303, 8352, 8399};
    public static final int[] CHAR_RANGE_HEBREW = {0, 127, 1424, 1535, 8352, 8399, 64285, 64335};
    public static final int[] CHAR_RANGE_LATIN = {0, 383, 8192, 8303, 8352, 8399, 64256, 64262};
    public static final char CID_NEWLINE = '翿';
    public static final String COURIER = "Courier";
    public static final String COURIER_BOLD = "Courier-Bold";
    public static final String COURIER_BOLDOBLIQUE = "Courier-BoldOblique";
    public static final String COURIER_OBLIQUE = "Courier-Oblique";
    public static final String CP1250 = "Cp1250";
    public static final String CP1252 = "Cp1252";
    public static final String CP1257 = "Cp1257";
    public static final double[] DEFAULT_FONT_MATRIX = {0.001d, 0.0d, 0.0d, 0.001d, 0.0d, 0.0d};
    public static final int DESCENT = 3;
    public static final boolean EMBEDDED = true;
    public static final int FONT_TYPE_CJK = 2;
    public static final int FONT_TYPE_DOCUMENT = 4;
    public static final int FONT_TYPE_T1 = 0;
    public static final int FONT_TYPE_T3 = 5;
    public static final int FONT_TYPE_TT = 1;
    public static final int FONT_TYPE_TTUNI = 3;
    public static final int FONT_WEIGHT = 23;
    public static final String HELVETICA = "Helvetica";
    public static final String HELVETICA_BOLD = "Helvetica-Bold";
    public static final String HELVETICA_BOLDOBLIQUE = "Helvetica-BoldOblique";
    public static final String HELVETICA_OBLIQUE = "Helvetica-Oblique";
    public static final String IDENTITY_H = "Identity-H";
    public static final String IDENTITY_V = "Identity-V";
    public static final int ITALICANGLE = 4;
    public static final String MACROMAN = "MacRoman";
    public static final boolean NOT_CACHED = false;
    public static final boolean NOT_EMBEDDED = false;
    public static final char PARAGRAPH_SEPARATOR = ' ';
    public static final String RESOURCE_PATH = "com/itextpdf/text/pdf/fonts/";
    public static final int STRIKETHROUGH_POSITION = 15;
    public static final int STRIKETHROUGH_THICKNESS = 16;
    public static final int SUBSCRIPT_OFFSET = 18;
    public static final int SUBSCRIPT_SIZE = 17;
    public static final int SUPERSCRIPT_OFFSET = 20;
    public static final int SUPERSCRIPT_SIZE = 19;
    public static final String SYMBOL = "Symbol";
    public static final String TIMES_BOLD = "Times-Bold";
    public static final String TIMES_BOLDITALIC = "Times-BoldItalic";
    public static final String TIMES_ITALIC = "Times-Italic";
    public static final String TIMES_ROMAN = "Times-Roman";
    public static final int UNDERLINE_POSITION = 13;
    public static final int UNDERLINE_THICKNESS = 14;
    public static final int WEIGHT_CLASS = 21;
    public static final int WIDTH_CLASS = 22;
    public static final String WINANSI = "Cp1252";
    public static final String ZAPFDINGBATS = "ZapfDingbats";
    protected static ConcurrentHashMap<String, BaseFont> fontCache = new ConcurrentHashMap<>();
    public static final String notdef = ".notdef";
    protected int[][] charBBoxes = new int[256][];
    protected int compressionLevel = -1;
    protected String[] differences = new String[256];
    protected boolean directTextToByte = false;
    protected boolean embedded;
    protected String encoding;
    protected boolean fastWinansi = false;
    protected boolean fontSpecific = true;
    int fontType;
    protected boolean forceWidthsOutput = false;
    protected IntHashtable specialMap;
    protected boolean subset = true;
    protected ArrayList<int[]> subsetRanges;
    protected char[] unicodeDifferences = new char[256];
    protected boolean vertical = false;
    protected int[] widths = new int[256];

    public abstract String[][] getAllNameEntries();

    public int getCidCode(int i) {
        return i;
    }

    public abstract String[][] getFamilyFontName();

    public abstract float getFontDescriptor(int i, float f);

    public abstract String[][] getFullFontName();

    /* access modifiers changed from: package-private */
    public abstract PdfStream getFullFontStream() throws IOException, DocumentException;

    public abstract int getKerning(int i, int i2);

    public abstract String getPostscriptFontName();

    /* access modifiers changed from: protected */
    public abstract int[] getRawCharBBox(int i, String str);

    /* access modifiers changed from: package-private */
    public abstract int getRawWidth(int i, String str);

    public String getSubfamily() {
        return "";
    }

    public int getUnicodeEquivalent(int i) {
        return i;
    }

    public abstract boolean hasKernPairs();

    public void setFontDescriptor(int i, float f) {
    }

    public abstract boolean setKerning(int i, int i2, int i3);

    public abstract void setPostscriptFontName(String str);

    /* access modifiers changed from: package-private */
    public abstract void writeFont(PdfWriter pdfWriter, PdfIndirectReference pdfIndirectReference, Object[] objArr) throws DocumentException, IOException;

    static {
        BuiltinFonts14.put("Courier", PdfName.COURIER);
        BuiltinFonts14.put("Courier-Bold", PdfName.COURIER_BOLD);
        BuiltinFonts14.put("Courier-BoldOblique", PdfName.COURIER_BOLDOBLIQUE);
        BuiltinFonts14.put("Courier-Oblique", PdfName.COURIER_OBLIQUE);
        BuiltinFonts14.put("Helvetica", PdfName.HELVETICA);
        BuiltinFonts14.put("Helvetica-Bold", PdfName.HELVETICA_BOLD);
        BuiltinFonts14.put("Helvetica-BoldOblique", PdfName.HELVETICA_BOLDOBLIQUE);
        BuiltinFonts14.put("Helvetica-Oblique", PdfName.HELVETICA_OBLIQUE);
        BuiltinFonts14.put("Symbol", PdfName.SYMBOL);
        BuiltinFonts14.put("Times-Roman", PdfName.TIMES_ROMAN);
        BuiltinFonts14.put("Times-Bold", PdfName.TIMES_BOLD);
        BuiltinFonts14.put("Times-BoldItalic", PdfName.TIMES_BOLDITALIC);
        BuiltinFonts14.put("Times-Italic", PdfName.TIMES_ITALIC);
        BuiltinFonts14.put("ZapfDingbats", PdfName.ZAPFDINGBATS);
    }

    static class StreamFont extends PdfStream {
        public StreamFont(byte[] bArr, int[] iArr, int i) throws DocumentException {
            try {
                this.bytes = bArr;
                put(PdfName.LENGTH, new PdfNumber(this.bytes.length));
                int i2 = 0;
                while (i2 < iArr.length) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Length");
                    int i3 = i2 + 1;
                    sb.append(i3);
                    put(new PdfName(sb.toString()), new PdfNumber(iArr[i2]));
                    i2 = i3;
                }
                flateCompress(i);
            } catch (Exception e) {
                throw new DocumentException(e);
            }
        }

        public StreamFont(byte[] bArr, String str, int i) throws DocumentException {
            try {
                this.bytes = bArr;
                put(PdfName.LENGTH, new PdfNumber(this.bytes.length));
                if (str != null) {
                    put(PdfName.SUBTYPE, new PdfName(str));
                }
                flateCompress(i);
            } catch (Exception e) {
                throw new DocumentException(e);
            }
        }
    }

    protected BaseFont() {
    }

    public static BaseFont createFont() throws DocumentException, IOException {
        return createFont("Helvetica", "Cp1252", false);
    }

    public static BaseFont createFont(String str, String str2, boolean z) throws DocumentException, IOException {
        return createFont(str, str2, z, true, (byte[]) null, (byte[]) null, false);
    }

    public static BaseFont createFont(String str, String str2, boolean z, boolean z2) throws DocumentException, IOException {
        return createFont(str, str2, z, true, (byte[]) null, (byte[]) null, z2);
    }

    public static BaseFont createFont(String str, String str2, boolean z, boolean z2, byte[] bArr, byte[] bArr2) throws DocumentException, IOException {
        return createFont(str, str2, z, z2, bArr, bArr2, false);
    }

    public static BaseFont createFont(String str, String str2, boolean z, boolean z2, byte[] bArr, byte[] bArr2, boolean z3) throws DocumentException, IOException {
        return createFont(str, str2, z, z2, bArr, bArr2, z3, false);
    }

    /* JADX WARNING: type inference failed for: r9v2, types: [com.itextpdf.text.pdf.BaseFont, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r9v10 */
    /* JADX WARNING: type inference failed for: r0v21, types: [com.itextpdf.text.pdf.Type1Font] */
    /* JADX WARNING: type inference failed for: r0v22, types: [com.itextpdf.text.pdf.TrueTypeFontUnicode] */
    /* JADX WARNING: type inference failed for: r0v23, types: [com.itextpdf.text.pdf.TrueTypeFont] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.itextpdf.text.pdf.BaseFont createFont(java.lang.String r10, java.lang.String r11, boolean r12, boolean r13, byte[] r14, byte[] r15, boolean r16, boolean r17) throws com.itextpdf.text.DocumentException, java.io.IOException {
        /*
            r1 = r10
            java.lang.String r0 = getBaseName(r1)
            java.lang.String r7 = normalizeEncoding(r11)
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfName> r2 = BuiltinFonts14
            boolean r2 = r2.containsKey(r1)
            r3 = 0
            if (r2 == 0) goto L_0x0014
            r4 = r3
            goto L_0x0018
        L_0x0014:
            boolean r4 = com.itextpdf.text.pdf.CJKFont.isCJKFont(r0, r7)
        L_0x0018:
            r5 = 1
            if (r2 != 0) goto L_0x0033
            if (r4 == 0) goto L_0x001e
            goto L_0x0033
        L_0x001e:
            java.lang.String r6 = "Identity-H"
            boolean r6 = r7.equals(r6)
            if (r6 != 0) goto L_0x0031
            java.lang.String r6 = "Identity-V"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x002f
            goto L_0x0031
        L_0x002f:
            r6 = r12
            goto L_0x0034
        L_0x0031:
            r6 = r5
            goto L_0x0034
        L_0x0033:
            r6 = r3
        L_0x0034:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            java.lang.String r9 = "\n"
            r8.append(r9)
            r8.append(r7)
            java.lang.String r9 = "\n"
            r8.append(r9)
            r8.append(r6)
            java.lang.String r8 = r8.toString()
            if (r13 == 0) goto L_0x005d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.itextpdf.text.pdf.BaseFont> r9 = fontCache
            java.lang.Object r9 = r9.get(r8)
            com.itextpdf.text.pdf.BaseFont r9 = (com.itextpdf.text.pdf.BaseFont) r9
            if (r9 == 0) goto L_0x005d
            return r9
        L_0x005d:
            if (r2 != 0) goto L_0x00f1
            java.lang.String r2 = r1.toLowerCase()
            java.lang.String r9 = ".afm"
            boolean r2 = r2.endsWith(r9)
            if (r2 != 0) goto L_0x00f1
            java.lang.String r2 = r1.toLowerCase()
            java.lang.String r9 = ".pfm"
            boolean r2 = r2.endsWith(r9)
            if (r2 == 0) goto L_0x0079
            goto L_0x00f1
        L_0x0079:
            java.lang.String r2 = r0.toLowerCase()
            java.lang.String r9 = ".ttf"
            boolean r2 = r2.endsWith(r9)
            if (r2 != 0) goto L_0x00bf
            java.lang.String r2 = r0.toLowerCase()
            java.lang.String r9 = ".otf"
            boolean r2 = r2.endsWith(r9)
            if (r2 != 0) goto L_0x00bf
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r2 = ".ttc,"
            int r0 = r0.indexOf(r2)
            if (r0 <= 0) goto L_0x009e
            goto L_0x00bf
        L_0x009e:
            if (r4 == 0) goto L_0x00a8
            com.itextpdf.text.pdf.CJKFont r0 = new com.itextpdf.text.pdf.CJKFont
            r0.<init>(r1, r7, r6)
            r9 = r0
            goto L_0x0105
        L_0x00a8:
            if (r16 == 0) goto L_0x00ac
            r0 = 0
            return r0
        L_0x00ac:
            com.itextpdf.text.DocumentException r0 = new com.itextpdf.text.DocumentException
            java.lang.String r2 = "font.1.with.2.is.not.recognized"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r3] = r1
            r4[r5] = r7
            java.lang.String r1 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r4)
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x00bf:
            java.lang.String r0 = "Identity-H"
            boolean r0 = r7.equals(r0)
            if (r0 != 0) goto L_0x00e5
            java.lang.String r0 = "Identity-V"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00d0
            goto L_0x00e5
        L_0x00d0:
            com.itextpdf.text.pdf.TrueTypeFont r9 = new com.itextpdf.text.pdf.TrueTypeFont
            r5 = 0
            r0 = r9
            r2 = r7
            r3 = r6
            r4 = r14
            r6 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6)
            java.lang.String r0 = "Cp1252"
            boolean r0 = r7.equals(r0)
            r9.fastWinansi = r0
            goto L_0x0105
        L_0x00e5:
            com.itextpdf.text.pdf.TrueTypeFontUnicode r9 = new com.itextpdf.text.pdf.TrueTypeFontUnicode
            r0 = r9
            r2 = r7
            r3 = r6
            r4 = r14
            r5 = r17
            r0.<init>(r1, r2, r3, r4, r5)
            goto L_0x0105
        L_0x00f1:
            com.itextpdf.text.pdf.Type1Font r9 = new com.itextpdf.text.pdf.Type1Font
            r0 = r9
            r2 = r7
            r3 = r6
            r4 = r14
            r5 = r15
            r6 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6)
            java.lang.String r0 = "Cp1252"
            boolean r0 = r7.equals(r0)
            r9.fastWinansi = r0
        L_0x0105:
            if (r13 == 0) goto L_0x0117
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.itextpdf.text.pdf.BaseFont> r0 = fontCache
            java.lang.Object r0 = r0.get(r8)
            com.itextpdf.text.pdf.BaseFont r0 = (com.itextpdf.text.pdf.BaseFont) r0
            if (r0 == 0) goto L_0x0112
            return r0
        L_0x0112:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.itextpdf.text.pdf.BaseFont> r0 = fontCache
            r0.putIfAbsent(r8, r9)
        L_0x0117:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.BaseFont.createFont(java.lang.String, java.lang.String, boolean, boolean, byte[], byte[], boolean, boolean):com.itextpdf.text.pdf.BaseFont");
    }

    public static BaseFont createFont(PRIndirectReference pRIndirectReference) {
        return new DocumentFont(pRIndirectReference);
    }

    public boolean isVertical() {
        return this.vertical;
    }

    protected static String getBaseName(String str) {
        if (str.endsWith(",Bold")) {
            return str.substring(0, str.length() - 5);
        }
        if (str.endsWith(",Italic")) {
            return str.substring(0, str.length() - 7);
        }
        return str.endsWith(",BoldItalic") ? str.substring(0, str.length() - 11) : str;
    }

    protected static String normalizeEncoding(String str) {
        if (str.equals("winansi") || str.equals("")) {
            return "Cp1252";
        }
        return str.equals("macroman") ? MACROMAN : str;
    }

    /* access modifiers changed from: protected */
    public void createEncoding() {
        int i;
        int i2 = 0;
        if (this.encoding.startsWith("#")) {
            this.specialMap = new IntHashtable();
            StringTokenizer stringTokenizer = new StringTokenizer(this.encoding.substring(1), " ,\t\n\r\f");
            if (stringTokenizer.nextToken().equals("full")) {
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    String nextToken2 = stringTokenizer.nextToken();
                    char parseInt = (char) Integer.parseInt(stringTokenizer.nextToken(), 16);
                    if (nextToken.startsWith("'")) {
                        i = nextToken.charAt(1);
                    } else {
                        i = Integer.parseInt(nextToken);
                    }
                    int i3 = i % 256;
                    this.specialMap.put(parseInt, i3);
                    this.differences[i3] = nextToken2;
                    this.unicodeDifferences[i3] = parseInt;
                    this.widths[i3] = getRawWidth(parseInt, nextToken2);
                    this.charBBoxes[i3] = getRawCharBBox(parseInt, nextToken2);
                }
            } else {
                int parseInt2 = stringTokenizer.hasMoreTokens() ? Integer.parseInt(stringTokenizer.nextToken()) : 0;
                while (stringTokenizer.hasMoreTokens() && parseInt2 < 256) {
                    int parseInt3 = Integer.parseInt(stringTokenizer.nextToken(), 16) % 65536;
                    String unicodeToName = GlyphList.unicodeToName(parseInt3);
                    if (unicodeToName != null) {
                        this.specialMap.put(parseInt3, parseInt2);
                        this.differences[parseInt2] = unicodeToName;
                        this.unicodeDifferences[parseInt2] = (char) parseInt3;
                        this.widths[parseInt2] = getRawWidth(parseInt3, unicodeToName);
                        this.charBBoxes[parseInt2] = getRawCharBBox(parseInt3, unicodeToName);
                        parseInt2++;
                    }
                }
            }
            while (i2 < 256) {
                if (this.differences[i2] == null) {
                    this.differences[i2] = notdef;
                }
                i2++;
            }
        } else if (this.fontSpecific) {
            while (i2 < 256) {
                this.widths[i2] = getRawWidth(i2, (String) null);
                this.charBBoxes[i2] = getRawCharBBox(i2, (String) null);
                i2++;
            }
        } else {
            byte[] bArr = new byte[1];
            for (int i4 = 0; i4 < 256; i4++) {
                bArr[0] = (byte) i4;
                String convertToString = PdfEncodings.convertToString(bArr, this.encoding);
                char charAt = convertToString.length() > 0 ? convertToString.charAt(0) : '?';
                String unicodeToName2 = GlyphList.unicodeToName(charAt);
                if (unicodeToName2 == null) {
                    unicodeToName2 = notdef;
                }
                this.differences[i4] = unicodeToName2;
                this.unicodeDifferences[i4] = charAt;
                this.widths[i4] = getRawWidth(charAt, unicodeToName2);
                this.charBBoxes[i4] = getRawCharBBox(charAt, unicodeToName2);
            }
        }
    }

    public int getWidth(int i) {
        if (!this.fastWinansi) {
            byte[] convertToBytes = convertToBytes(i);
            int i2 = 0;
            for (byte b : convertToBytes) {
                i2 += this.widths[b & 255];
            }
            return i2;
        } else if (i < 128 || (i >= 160 && i <= 255)) {
            return this.widths[i];
        } else {
            return this.widths[PdfEncodings.winansi.get(i)];
        }
    }

    public int getWidth(String str) {
        int i;
        int i2 = 0;
        if (this.fastWinansi) {
            int length = str.length();
            int i3 = 0;
            while (i2 < length) {
                char charAt = str.charAt(i2);
                if (charAt < 128 || (charAt >= 160 && charAt <= 255)) {
                    i = this.widths[charAt];
                } else {
                    i = this.widths[PdfEncodings.winansi.get(charAt)];
                }
                i3 += i;
                i2++;
            }
            return i3;
        }
        byte[] convertToBytes = convertToBytes(str);
        int i4 = 0;
        while (i2 < convertToBytes.length) {
            i4 += this.widths[convertToBytes[i2] & 255];
            i2++;
        }
        return i4;
    }

    public int getDescent(String str) {
        char[] charArray = str.toCharArray();
        int i = 0;
        for (char charBBox : charArray) {
            int[] charBBox2 = getCharBBox(charBBox);
            if (charBBox2 != null && charBBox2[1] < i) {
                i = charBBox2[1];
            }
        }
        return i;
    }

    public int getAscent(String str) {
        char[] charArray = str.toCharArray();
        int i = 0;
        for (char charBBox : charArray) {
            int[] charBBox2 = getCharBBox(charBBox);
            if (charBBox2 != null && charBBox2[3] > i) {
                i = charBBox2[3];
            }
        }
        return i;
    }

    public float getDescentPoint(String str, float f) {
        return ((float) getDescent(str)) * 0.001f * f;
    }

    public float getAscentPoint(String str, float f) {
        return ((float) getAscent(str)) * 0.001f * f;
    }

    public float getWidthPointKerned(String str, float f) {
        float width = ((float) getWidth(str)) * 0.001f * f;
        if (!hasKernPairs()) {
            return width;
        }
        int length = str.length() - 1;
        char[] charArray = str.toCharArray();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char c = charArray[i];
            i++;
            i2 += getKerning(c, charArray[i]);
        }
        return width + (((float) i2) * 0.001f * f);
    }

    public float getWidthPoint(String str, float f) {
        return ((float) getWidth(str)) * 0.001f * f;
    }

    public float getWidthPoint(int i, float f) {
        return ((float) getWidth(i)) * 0.001f * f;
    }

    public byte[] convertToBytes(String str) {
        if (this.directTextToByte) {
            return PdfEncodings.convertToBytes(str, (String) null);
        }
        if (this.specialMap == null) {
            return PdfEncodings.convertToBytes(str, this.encoding);
        }
        byte[] bArr = new byte[str.length()];
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (this.specialMap.containsKey(charAt)) {
                bArr[i] = (byte) this.specialMap.get(charAt);
                i++;
            }
        }
        if (i >= length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    /* access modifiers changed from: package-private */
    public byte[] convertToBytes(int i) {
        if (this.directTextToByte) {
            return PdfEncodings.convertToBytes((char) i, (String) null);
        }
        if (this.specialMap == null) {
            return PdfEncodings.convertToBytes((char) i, this.encoding);
        }
        if (!this.specialMap.containsKey(i)) {
            return new byte[0];
        }
        return new byte[]{(byte) this.specialMap.get(i)};
    }

    public String getEncoding() {
        return this.encoding;
    }

    public int getFontType() {
        return this.fontType;
    }

    public boolean isEmbedded() {
        return this.embedded;
    }

    public boolean isFontSpecific() {
        return this.fontSpecific;
    }

    public static String createSubsetPrefix() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            sb.append((char) ((int) ((Math.random() * 26.0d) + 65.0d)));
        }
        return sb + "+";
    }

    /* access modifiers changed from: package-private */
    public char getUnicodeDifferences(int i) {
        return this.unicodeDifferences[i];
    }

    public static String[][] getFullFontName(String str, String str2, byte[] bArr) throws DocumentException, IOException {
        BaseFont baseFont;
        String baseName = getBaseName(str);
        if (baseName.toLowerCase().endsWith(".ttf") || baseName.toLowerCase().endsWith(".otf") || baseName.toLowerCase().indexOf(".ttc,") > 0) {
            baseFont = new TrueTypeFont(str, "Cp1252", false, bArr, true, false);
        } else {
            baseFont = createFont(str, str2, false, false, bArr, (byte[]) null);
        }
        return baseFont.getFullFontName();
    }

    public static Object[] getAllFontNames(String str, String str2, byte[] bArr) throws DocumentException, IOException {
        BaseFont baseFont;
        String baseName = getBaseName(str);
        if (baseName.toLowerCase().endsWith(".ttf") || baseName.toLowerCase().endsWith(".otf") || baseName.toLowerCase().indexOf(".ttc,") > 0) {
            baseFont = new TrueTypeFont(str, "Cp1252", false, bArr, true, false);
        } else {
            baseFont = createFont(str, str2, false, false, bArr, (byte[]) null);
        }
        return new Object[]{baseFont.getPostscriptFontName(), baseFont.getFamilyFontName(), baseFont.getFullFontName()};
    }

    public static String[][] getAllNameEntries(String str, String str2, byte[] bArr) throws DocumentException, IOException {
        BaseFont baseFont;
        String baseName = getBaseName(str);
        if (baseName.toLowerCase().endsWith(".ttf") || baseName.toLowerCase().endsWith(".otf") || baseName.toLowerCase().indexOf(".ttc,") > 0) {
            baseFont = new TrueTypeFont(str, "Cp1252", false, bArr, true, false);
        } else {
            baseFont = createFont(str, str2, false, false, bArr, (byte[]) null);
        }
        return baseFont.getAllNameEntries();
    }

    public String[] getCodePagesSupported() {
        return new String[0];
    }

    public static String[] enumerateTTCNames(String str) throws DocumentException, IOException {
        return new EnumerateTTC(str).getNames();
    }

    public static String[] enumerateTTCNames(byte[] bArr) throws DocumentException, IOException {
        return new EnumerateTTC(bArr).getNames();
    }

    public int[] getWidths() {
        return this.widths;
    }

    public String[] getDifferences() {
        return this.differences;
    }

    public char[] getUnicodeDifferences() {
        return this.unicodeDifferences;
    }

    public boolean isForceWidthsOutput() {
        return this.forceWidthsOutput;
    }

    public void setForceWidthsOutput(boolean z) {
        this.forceWidthsOutput = z;
    }

    public boolean isDirectTextToByte() {
        return this.directTextToByte;
    }

    public void setDirectTextToByte(boolean z) {
        this.directTextToByte = z;
    }

    public boolean isSubset() {
        return this.subset;
    }

    public void setSubset(boolean z) {
        this.subset = z;
    }

    public boolean charExists(int i) {
        return convertToBytes(i).length > 0;
    }

    public boolean setCharAdvance(int i, int i2) {
        byte[] convertToBytes = convertToBytes(i);
        if (convertToBytes.length == 0) {
            return false;
        }
        this.widths[convertToBytes[0] & 255] = i2;
        return true;
    }

    private static void addFont(PRIndirectReference pRIndirectReference, IntHashtable intHashtable, ArrayList<Object[]> arrayList) {
        PdfObject pdfObject = PdfReader.getPdfObject((PdfObject) pRIndirectReference);
        if (pdfObject != null && pdfObject.isDictionary()) {
            PdfDictionary pdfDictionary = (PdfDictionary) pdfObject;
            PdfName asName = pdfDictionary.getAsName(PdfName.SUBTYPE);
            if (PdfName.TYPE1.equals(asName) || PdfName.TRUETYPE.equals(asName) || PdfName.TYPE0.equals(asName)) {
                arrayList.add(new Object[]{PdfName.decodeName(pdfDictionary.getAsName(PdfName.BASEFONT).toString()), pRIndirectReference});
                intHashtable.put(pRIndirectReference.getNumber(), 1);
            }
        }
    }

    private static void recourseFonts(PdfDictionary pdfDictionary, IntHashtable intHashtable, ArrayList<Object[]> arrayList, int i, HashSet<PdfDictionary> hashSet) {
        PdfDictionary asDict;
        int i2 = i + 1;
        if (i2 <= 50 && pdfDictionary != null && (asDict = pdfDictionary.getAsDict(PdfName.RESOURCES)) != null) {
            PdfDictionary asDict2 = asDict.getAsDict(PdfName.FONT);
            if (asDict2 != null) {
                for (PdfName pdfName : asDict2.getKeys()) {
                    PdfObject pdfObject = asDict2.get(pdfName);
                    if (pdfObject != null && pdfObject.isIndirect()) {
                        PRIndirectReference pRIndirectReference = (PRIndirectReference) pdfObject;
                        if (!intHashtable.containsKey(pRIndirectReference.getNumber())) {
                            addFont(pRIndirectReference, intHashtable, arrayList);
                        }
                    }
                }
            }
            PdfDictionary asDict3 = asDict.getAsDict(PdfName.XOBJECT);
            if (asDict3 == null) {
                return;
            }
            if (hashSet.add(asDict3)) {
                for (PdfName directObject : asDict3.getKeys()) {
                    PdfObject directObject2 = asDict3.getDirectObject(directObject);
                    if (directObject2 instanceof PdfDictionary) {
                        recourseFonts((PdfDictionary) directObject2, intHashtable, arrayList, i2, hashSet);
                    }
                }
                hashSet.remove(asDict3);
                return;
            }
            throw new ExceptionConverter(new InvalidPdfException(MessageLocalization.getComposedMessage("illegal.resources.tree", new Object[0])));
        }
    }

    public static ArrayList<Object[]> getDocumentFonts(PdfReader pdfReader) {
        IntHashtable intHashtable = new IntHashtable();
        ArrayList<Object[]> arrayList = new ArrayList<>();
        int numberOfPages = pdfReader.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            recourseFonts(pdfReader.getPageN(i), intHashtable, arrayList, 1, new HashSet());
        }
        return arrayList;
    }

    public static ArrayList<Object[]> getDocumentFonts(PdfReader pdfReader, int i) {
        IntHashtable intHashtable = new IntHashtable();
        ArrayList<Object[]> arrayList = new ArrayList<>();
        recourseFonts(pdfReader.getPageN(i), intHashtable, arrayList, 1, new HashSet());
        return arrayList;
    }

    public int[] getCharBBox(int i) {
        byte[] convertToBytes = convertToBytes(i);
        if (convertToBytes.length == 0) {
            return null;
        }
        return this.charBBoxes[convertToBytes[0] & 255];
    }

    public double[] getFontMatrix() {
        return DEFAULT_FONT_MATRIX;
    }

    public void correctArabicAdvance() {
        for (char c = 1611; c <= 1624; c = (char) (c + 1)) {
            setCharAdvance(c, 0);
        }
        setCharAdvance(1648, 0);
        for (char c2 = 1750; c2 <= 1756; c2 = (char) (c2 + 1)) {
            setCharAdvance(c2, 0);
        }
        for (char c3 = 1759; c3 <= 1764; c3 = (char) (c3 + 1)) {
            setCharAdvance(c3, 0);
        }
        for (char c4 = 1767; c4 <= 1768; c4 = (char) (c4 + 1)) {
            setCharAdvance(c4, 0);
        }
        for (char c5 = 1770; c5 <= 1773; c5 = (char) (c5 + 1)) {
            setCharAdvance(c5, 0);
        }
    }

    public void addSubsetRange(int[] iArr) {
        if (this.subsetRanges == null) {
            this.subsetRanges = new ArrayList<>();
        }
        this.subsetRanges.add(iArr);
    }

    public int getCompressionLevel() {
        return this.compressionLevel;
    }

    public void setCompressionLevel(int i) {
        if (i < 0 || i > 9) {
            this.compressionLevel = -1;
        } else {
            this.compressionLevel = i;
        }
    }
}
