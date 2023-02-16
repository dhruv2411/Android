package com.itextpdf.text.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.fonts.FontsResourceAnchor;
import java.io.IOException;
import java.util.HashMap;

class Type1Font extends BaseFont {
    private static final int[] PFB_TYPES = {1, 2, 1};
    private static FontsResourceAnchor resourceAnchor;
    private int Ascender = 800;
    private int CapHeight = 700;
    private HashMap<Object, Object[]> CharMetrics = new HashMap<>();
    private String CharacterSet;
    private int Descender = -200;
    private String EncodingScheme = "FontSpecific";
    private String FamilyName;
    private String FontName;
    private String FullName;
    private boolean IsFixedPitch = false;
    private float ItalicAngle = 0.0f;
    private HashMap<String, Object[]> KernPairs = new HashMap<>();
    private int StdHW;
    private int StdVW = 80;
    private int UnderlinePosition = -100;
    private int UnderlineThickness = 50;
    private String Weight = "";
    private int XHeight = 480;
    private boolean builtinFont = false;
    private String fileName;
    private int llx = -50;
    private int lly = -200;
    protected byte[] pfb;
    private int urx = 1000;
    private int ury = 900;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: com.itextpdf.text.pdf.RandomAccessFileOrArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: com.itextpdf.text.pdf.RandomAccessFileOrArray} */
    /* JADX WARNING: type inference failed for: r8v1, types: [com.itextpdf.text.pdf.RandomAccessFileOrArray] */
    /* JADX WARNING: type inference failed for: r8v4 */
    /* JADX WARNING: type inference failed for: r8v10, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r8v12 */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:24|(2:25|(1:105)(2:46|47))|27|(2:29|30)|31|32|(3:33|34|(2:36|37))) */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ee, code lost:
        r4 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00dc */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e6 A[SYNTHETIC, Splitter:B:36:0x00e6] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f1 A[SYNTHETIC, Splitter:B:42:0x00f1] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x019f A[SYNTHETIC, Splitter:B:99:0x019f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    Type1Font(java.lang.String r4, java.lang.String r5, boolean r6, byte[] r7, byte[] r8, boolean r9) throws com.itextpdf.text.DocumentException, java.io.IOException {
        /*
            r3 = this;
            r3.<init>()
            java.lang.String r0 = ""
            r3.Weight = r0
            r0 = 0
            r3.ItalicAngle = r0
            r0 = 0
            r3.IsFixedPitch = r0
            r1 = -50
            r3.llx = r1
            r1 = -200(0xffffffffffffff38, float:NaN)
            r3.lly = r1
            r2 = 1000(0x3e8, float:1.401E-42)
            r3.urx = r2
            r2 = 900(0x384, float:1.261E-42)
            r3.ury = r2
            r2 = -100
            r3.UnderlinePosition = r2
            r2 = 50
            r3.UnderlineThickness = r2
            java.lang.String r2 = "FontSpecific"
            r3.EncodingScheme = r2
            r2 = 700(0x2bc, float:9.81E-43)
            r3.CapHeight = r2
            r2 = 480(0x1e0, float:6.73E-43)
            r3.XHeight = r2
            r2 = 800(0x320, float:1.121E-42)
            r3.Ascender = r2
            r3.Descender = r1
            r1 = 80
            r3.StdVW = r1
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r3.CharMetrics = r1
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r3.KernPairs = r1
            r3.builtinFont = r0
            if (r6 == 0) goto L_0x005f
            if (r7 == 0) goto L_0x005f
            if (r8 != 0) goto L_0x005f
            com.itextpdf.text.DocumentException r4 = new com.itextpdf.text.DocumentException
            java.lang.String r5 = "two.byte.arrays.are.needed.if.the.type1.font.is.embedded"
            java.lang.Object[] r6 = new java.lang.Object[r0]
            java.lang.String r5 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r5, (java.lang.Object[]) r6)
            r4.<init>((java.lang.String) r5)
            throw r4
        L_0x005f:
            if (r6 == 0) goto L_0x0065
            if (r7 == 0) goto L_0x0065
            r3.pfb = r8
        L_0x0065:
            r3.encoding = r5
            r3.embedded = r6
            r3.fileName = r4
            r3.fontType = r0
            java.util.HashMap r6 = BuiltinFonts14
            boolean r6 = r6.containsKey(r4)
            r8 = 0
            r1 = 1
            if (r6 == 0) goto L_0x0100
            r3.embedded = r0
            r3.builtinFont = r1
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]
            com.itextpdf.text.pdf.fonts.FontsResourceAnchor r7 = resourceAnchor     // Catch:{ all -> 0x00f9 }
            if (r7 != 0) goto L_0x008a
            com.itextpdf.text.pdf.fonts.FontsResourceAnchor r7 = new com.itextpdf.text.pdf.fonts.FontsResourceAnchor     // Catch:{ all -> 0x00f9 }
            r7.<init>()     // Catch:{ all -> 0x00f9 }
            resourceAnchor = r7     // Catch:{ all -> 0x00f9 }
        L_0x008a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f9 }
            r7.<init>()     // Catch:{ all -> 0x00f9 }
            java.lang.String r9 = "com/itextpdf/text/pdf/fonts/"
            r7.append(r9)     // Catch:{ all -> 0x00f9 }
            r7.append(r4)     // Catch:{ all -> 0x00f9 }
            java.lang.String r9 = ".afm"
            r7.append(r9)     // Catch:{ all -> 0x00f9 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00f9 }
            com.itextpdf.text.pdf.fonts.FontsResourceAnchor r9 = resourceAnchor     // Catch:{ all -> 0x00f9 }
            java.lang.Class r9 = r9.getClass()     // Catch:{ all -> 0x00f9 }
            java.lang.ClassLoader r9 = r9.getClassLoader()     // Catch:{ all -> 0x00f9 }
            java.io.InputStream r7 = com.itextpdf.text.io.StreamUtil.getResourceStream(r7, r9)     // Catch:{ all -> 0x00f9 }
            if (r7 != 0) goto L_0x00c8
            java.lang.String r5 = "1.not.found.as.resource"
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c5 }
            r6[r0] = r4     // Catch:{ all -> 0x00c5 }
            java.lang.String r4 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r5, (java.lang.Object[]) r6)     // Catch:{ all -> 0x00c5 }
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ all -> 0x00c5 }
            r5.println(r4)     // Catch:{ all -> 0x00c5 }
            com.itextpdf.text.DocumentException r5 = new com.itextpdf.text.DocumentException     // Catch:{ all -> 0x00c5 }
            r5.<init>((java.lang.String) r4)     // Catch:{ all -> 0x00c5 }
            throw r5     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r4 = move-exception
            r8 = r7
            goto L_0x00fa
        L_0x00c8:
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x00c5 }
            r4.<init>()     // Catch:{ all -> 0x00c5 }
        L_0x00cd:
            int r9 = r7.read(r6)     // Catch:{ all -> 0x00c5 }
            if (r9 >= 0) goto L_0x00f5
            byte[] r4 = r4.toByteArray()     // Catch:{ all -> 0x00c5 }
            if (r7 == 0) goto L_0x00dc
            r7.close()     // Catch:{ Exception -> 0x00dc }
        L_0x00dc:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r6 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x00ee }
            r6.<init>((byte[]) r4)     // Catch:{ all -> 0x00ee }
            r3.process(r6)     // Catch:{ all -> 0x00eb }
            if (r6 == 0) goto L_0x0167
            r6.close()     // Catch:{ Exception -> 0x0167 }
            goto L_0x0167
        L_0x00eb:
            r4 = move-exception
            r8 = r6
            goto L_0x00ef
        L_0x00ee:
            r4 = move-exception
        L_0x00ef:
            if (r8 == 0) goto L_0x00f4
            r8.close()     // Catch:{ Exception -> 0x00f4 }
        L_0x00f4:
            throw r4
        L_0x00f5:
            r4.write(r6, r0, r9)     // Catch:{ all -> 0x00c5 }
            goto L_0x00cd
        L_0x00f9:
            r4 = move-exception
        L_0x00fa:
            if (r8 == 0) goto L_0x00ff
            r8.close()     // Catch:{ Exception -> 0x00ff }
        L_0x00ff:
            throw r4
        L_0x0100:
            java.lang.String r6 = r4.toLowerCase()
            java.lang.String r2 = ".afm"
            boolean r6 = r6.endsWith(r2)
            if (r6 == 0) goto L_0x012e
            if (r7 != 0) goto L_0x0119
            com.itextpdf.text.pdf.RandomAccessFileOrArray r6 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x0117 }
            boolean r7 = com.itextpdf.text.Document.plainRandomAccess     // Catch:{ all -> 0x0117 }
            r6.<init>(r4, r9, r7)     // Catch:{ all -> 0x0117 }
            r8 = r6
            goto L_0x011f
        L_0x0117:
            r4 = move-exception
            goto L_0x0128
        L_0x0119:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r4 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x0117 }
            r4.<init>((byte[]) r7)     // Catch:{ all -> 0x0117 }
            r8 = r4
        L_0x011f:
            r3.process(r8)     // Catch:{ all -> 0x0117 }
            if (r8 == 0) goto L_0x0167
            r8.close()     // Catch:{ Exception -> 0x0167 }
            goto L_0x0167
        L_0x0128:
            if (r8 == 0) goto L_0x012d
            r8.close()     // Catch:{ Exception -> 0x012d }
        L_0x012d:
            throw r4
        L_0x012e:
            java.lang.String r6 = r4.toLowerCase()
            java.lang.String r2 = ".pfm"
            boolean r6 = r6.endsWith(r2)
            if (r6 == 0) goto L_0x01a3
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x019c }
            r6.<init>()     // Catch:{ all -> 0x019c }
            if (r7 != 0) goto L_0x014a
            com.itextpdf.text.pdf.RandomAccessFileOrArray r7 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x019c }
            boolean r1 = com.itextpdf.text.Document.plainRandomAccess     // Catch:{ all -> 0x019c }
            r7.<init>(r4, r9, r1)     // Catch:{ all -> 0x019c }
            r8 = r7
            goto L_0x0150
        L_0x014a:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r4 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x019c }
            r4.<init>((byte[]) r7)     // Catch:{ all -> 0x019c }
            r8 = r4
        L_0x0150:
            com.itextpdf.text.pdf.Pfm2afm.convert(r8, r6)     // Catch:{ all -> 0x019c }
            r8.close()     // Catch:{ all -> 0x019c }
            com.itextpdf.text.pdf.RandomAccessFileOrArray r4 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x019c }
            byte[] r6 = r6.toByteArray()     // Catch:{ all -> 0x019c }
            r4.<init>((byte[]) r6)     // Catch:{ all -> 0x019c }
            r3.process(r4)     // Catch:{ all -> 0x0198 }
            if (r4 == 0) goto L_0x0167
            r4.close()     // Catch:{ Exception -> 0x0167 }
        L_0x0167:
            java.lang.String r4 = r3.EncodingScheme
            java.lang.String r4 = r4.trim()
            r3.EncodingScheme = r4
            java.lang.String r4 = r3.EncodingScheme
            java.lang.String r6 = "AdobeStandardEncoding"
            boolean r4 = r4.equals(r6)
            if (r4 != 0) goto L_0x0183
            java.lang.String r4 = r3.EncodingScheme
            java.lang.String r6 = "StandardEncoding"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x0185
        L_0x0183:
            r3.fontSpecific = r0
        L_0x0185:
            java.lang.String r4 = r3.encoding
            java.lang.String r6 = "#"
            boolean r4 = r4.startsWith(r6)
            if (r4 != 0) goto L_0x0194
            java.lang.String r4 = " "
            com.itextpdf.text.pdf.PdfEncodings.convertToBytes((java.lang.String) r4, (java.lang.String) r5)
        L_0x0194:
            r3.createEncoding()
            return
        L_0x0198:
            r5 = move-exception
            r8 = r4
            r4 = r5
            goto L_0x019d
        L_0x019c:
            r4 = move-exception
        L_0x019d:
            if (r8 == 0) goto L_0x01a2
            r8.close()     // Catch:{ Exception -> 0x01a2 }
        L_0x01a2:
            throw r4
        L_0x01a3:
            com.itextpdf.text.DocumentException r5 = new com.itextpdf.text.DocumentException
            java.lang.String r6 = "1.is.not.an.afm.or.pfm.font.file"
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r7[r0] = r4
            java.lang.String r4 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r6, (java.lang.Object[]) r7)
            r5.<init>((java.lang.String) r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.Type1Font.<init>(java.lang.String, java.lang.String, boolean, byte[], byte[], boolean):void");
    }

    /* access modifiers changed from: package-private */
    public int getRawWidth(int i, String str) {
        Object[] objArr;
        if (str == null) {
            objArr = this.CharMetrics.get(Integer.valueOf(i));
        } else if (str.equals(BaseFont.notdef)) {
            return 0;
        } else {
            objArr = this.CharMetrics.get(str);
        }
        if (objArr != null) {
            return ((Integer) objArr[1]).intValue();
        }
        return 0;
    }

    public int getKerning(int i, int i2) {
        String unicodeToName;
        Object[] objArr;
        String unicodeToName2 = GlyphList.unicodeToName(i);
        if (unicodeToName2 == null || (unicodeToName = GlyphList.unicodeToName(i2)) == null || (objArr = this.KernPairs.get(unicodeToName2)) == null) {
            return 0;
        }
        for (int i3 = 0; i3 < objArr.length; i3 += 2) {
            if (unicodeToName.equals(objArr[i3])) {
                return ((Integer) objArr[i3 + 1]).intValue();
            }
        }
        return 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void process(com.itextpdf.text.pdf.RandomAccessFileOrArray r15) throws com.itextpdf.text.DocumentException, java.io.IOException {
        /*
            r14 = this;
        L_0x0000:
            java.lang.String r0 = r15.readLine()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x01ac
            java.util.StringTokenizer r3 = new java.util.StringTokenizer
            java.lang.String r4 = " ,\n\r\t\f"
            r3.<init>(r0, r4)
            boolean r0 = r3.hasMoreTokens()
            if (r0 != 0) goto L_0x0016
            goto L_0x0000
        L_0x0016:
            java.lang.String r0 = r3.nextToken()
            java.lang.String r4 = "FontName"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x002f
            java.lang.String r0 = "ÿ"
            java.lang.String r0 = r3.nextToken(r0)
            java.lang.String r0 = r0.substring(r2)
            r14.FontName = r0
            goto L_0x0000
        L_0x002f:
            java.lang.String r4 = "FullName"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0044
            java.lang.String r0 = "ÿ"
            java.lang.String r0 = r3.nextToken(r0)
            java.lang.String r0 = r0.substring(r2)
            r14.FullName = r0
            goto L_0x0000
        L_0x0044:
            java.lang.String r4 = "FamilyName"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0059
            java.lang.String r0 = "ÿ"
            java.lang.String r0 = r3.nextToken(r0)
            java.lang.String r0 = r0.substring(r2)
            r14.FamilyName = r0
            goto L_0x0000
        L_0x0059:
            java.lang.String r4 = "Weight"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x006e
            java.lang.String r0 = "ÿ"
            java.lang.String r0 = r3.nextToken(r0)
            java.lang.String r0 = r0.substring(r2)
            r14.Weight = r0
            goto L_0x0000
        L_0x006e:
            java.lang.String r4 = "ItalicAngle"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0082
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            r14.ItalicAngle = r0
            goto L_0x0000
        L_0x0082:
            java.lang.String r4 = "IsFixedPitch"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0098
            java.lang.String r0 = r3.nextToken()
            java.lang.String r1 = "true"
            boolean r0 = r0.equals(r1)
            r14.IsFixedPitch = r0
            goto L_0x0000
        L_0x0098:
            java.lang.String r4 = "CharacterSet"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00ae
            java.lang.String r0 = "ÿ"
            java.lang.String r0 = r3.nextToken(r0)
            java.lang.String r0 = r0.substring(r2)
            r14.CharacterSet = r0
            goto L_0x0000
        L_0x00ae:
            java.lang.String r4 = "FontBBox"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00e4
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.llx = r0
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.lly = r0
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.urx = r0
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.ury = r0
            goto L_0x0000
        L_0x00e4:
            java.lang.String r4 = "UnderlinePosition"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x00f9
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.UnderlinePosition = r0
            goto L_0x0000
        L_0x00f9:
            java.lang.String r4 = "UnderlineThickness"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x010e
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.UnderlineThickness = r0
            goto L_0x0000
        L_0x010e:
            java.lang.String r4 = "EncodingScheme"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0124
            java.lang.String r0 = "ÿ"
            java.lang.String r0 = r3.nextToken(r0)
            java.lang.String r0 = r0.substring(r2)
            r14.EncodingScheme = r0
            goto L_0x0000
        L_0x0124:
            java.lang.String r4 = "CapHeight"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0139
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.CapHeight = r0
            goto L_0x0000
        L_0x0139:
            java.lang.String r4 = "XHeight"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x014e
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.XHeight = r0
            goto L_0x0000
        L_0x014e:
            java.lang.String r4 = "Ascender"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0163
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.Ascender = r0
            goto L_0x0000
        L_0x0163:
            java.lang.String r4 = "Descender"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0178
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.Descender = r0
            goto L_0x0000
        L_0x0178:
            java.lang.String r4 = "StdHW"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x018d
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.StdHW = r0
            goto L_0x0000
        L_0x018d:
            java.lang.String r4 = "StdVW"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x01a2
            java.lang.String r0 = r3.nextToken()
            float r0 = java.lang.Float.parseFloat(r0)
            int r0 = (int) r0
            r14.StdVW = r0
            goto L_0x0000
        L_0x01a2:
            java.lang.String r3 = "StartCharMetrics"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0000
            r0 = r2
            goto L_0x01ad
        L_0x01ac:
            r0 = r1
        L_0x01ad:
            if (r0 != 0) goto L_0x01c1
            com.itextpdf.text.DocumentException r15 = new com.itextpdf.text.DocumentException
            java.lang.String r0 = "missing.startcharmetrics.in.1"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = r14.fileName
            r2[r1] = r3
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r2)
            r15.<init>((java.lang.String) r0)
            throw r15
        L_0x01c1:
            java.lang.String r3 = r15.readLine()
            r4 = 2
            if (r3 == 0) goto L_0x029b
            java.util.StringTokenizer r5 = new java.util.StringTokenizer
            r5.<init>(r3)
            boolean r6 = r5.hasMoreTokens()
            if (r6 != 0) goto L_0x01d4
            goto L_0x01c1
        L_0x01d4:
            java.lang.String r5 = r5.nextToken()
            java.lang.String r6 = "EndCharMetrics"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x01e3
            r0 = r1
            goto L_0x029b
        L_0x01e3:
            r5 = -1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6 = 250(0xfa, float:3.5E-43)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.String r7 = ""
            r8 = 0
            java.util.StringTokenizer r9 = new java.util.StringTokenizer
            java.lang.String r10 = ";"
            r9.<init>(r3, r10)
        L_0x01f8:
            boolean r3 = r9.hasMoreTokens()
            r10 = 3
            r11 = 4
            if (r3 == 0) goto L_0x027f
            java.util.StringTokenizer r3 = new java.util.StringTokenizer
            java.lang.String r12 = r9.nextToken()
            r3.<init>(r12)
            boolean r12 = r3.hasMoreTokens()
            if (r12 != 0) goto L_0x0210
            goto L_0x01f8
        L_0x0210:
            java.lang.String r12 = r3.nextToken()
            java.lang.String r13 = "C"
            boolean r13 = r12.equals(r13)
            if (r13 == 0) goto L_0x0226
            java.lang.String r3 = r3.nextToken()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r5 = r3
            goto L_0x01f8
        L_0x0226:
            java.lang.String r13 = "WX"
            boolean r13 = r12.equals(r13)
            if (r13 == 0) goto L_0x023d
            java.lang.String r3 = r3.nextToken()
            float r3 = java.lang.Float.parseFloat(r3)
            int r3 = (int) r3
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r6 = r3
            goto L_0x01f8
        L_0x023d:
            java.lang.String r13 = "N"
            boolean r13 = r12.equals(r13)
            if (r13 == 0) goto L_0x024b
            java.lang.String r3 = r3.nextToken()
            r7 = r3
            goto L_0x01f8
        L_0x024b:
            java.lang.String r13 = "B"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x01f8
            int[] r8 = new int[r11]
            java.lang.String r11 = r3.nextToken()
            int r11 = java.lang.Integer.parseInt(r11)
            r8[r1] = r11
            java.lang.String r11 = r3.nextToken()
            int r11 = java.lang.Integer.parseInt(r11)
            r8[r2] = r11
            java.lang.String r11 = r3.nextToken()
            int r11 = java.lang.Integer.parseInt(r11)
            r8[r4] = r11
            java.lang.String r3 = r3.nextToken()
            int r3 = java.lang.Integer.parseInt(r3)
            r8[r10] = r3
            goto L_0x01f8
        L_0x027f:
            java.lang.Object[] r3 = new java.lang.Object[r11]
            r3[r1] = r5
            r3[r2] = r6
            r3[r4] = r7
            r3[r10] = r8
            int r4 = r5.intValue()
            if (r4 < 0) goto L_0x0294
            java.util.HashMap<java.lang.Object, java.lang.Object[]> r4 = r14.CharMetrics
            r4.put(r5, r3)
        L_0x0294:
            java.util.HashMap<java.lang.Object, java.lang.Object[]> r4 = r14.CharMetrics
            r4.put(r7, r3)
            goto L_0x01c1
        L_0x029b:
            if (r0 == 0) goto L_0x02af
            com.itextpdf.text.DocumentException r15 = new com.itextpdf.text.DocumentException
            java.lang.String r0 = "missing.endcharmetrics.in.1"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = r14.fileName
            r2[r1] = r3
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r2)
            r15.<init>((java.lang.String) r0)
            throw r15
        L_0x02af:
            java.util.HashMap<java.lang.Object, java.lang.Object[]> r3 = r14.CharMetrics
            java.lang.String r5 = "nonbreakingspace"
            boolean r3 = r3.containsKey(r5)
            if (r3 != 0) goto L_0x02cc
            java.util.HashMap<java.lang.Object, java.lang.Object[]> r3 = r14.CharMetrics
            java.lang.String r5 = "space"
            java.lang.Object r3 = r3.get(r5)
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            if (r3 == 0) goto L_0x02cc
            java.util.HashMap<java.lang.Object, java.lang.Object[]> r5 = r14.CharMetrics
            java.lang.String r6 = "nonbreakingspace"
            r5.put(r6, r3)
        L_0x02cc:
            java.lang.String r3 = r15.readLine()
            if (r3 == 0) goto L_0x02f4
            java.util.StringTokenizer r5 = new java.util.StringTokenizer
            r5.<init>(r3)
            boolean r3 = r5.hasMoreTokens()
            if (r3 != 0) goto L_0x02de
            goto L_0x02cc
        L_0x02de:
            java.lang.String r3 = r5.nextToken()
            java.lang.String r5 = "EndFontMetrics"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x02eb
            return
        L_0x02eb:
            java.lang.String r5 = "StartKernPairs"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x02cc
            r0 = r2
        L_0x02f4:
            if (r0 != 0) goto L_0x0308
            com.itextpdf.text.DocumentException r15 = new com.itextpdf.text.DocumentException
            java.lang.String r0 = "missing.endfontmetrics.in.1"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = r14.fileName
            r2[r1] = r3
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r2)
            r15.<init>((java.lang.String) r0)
            throw r15
        L_0x0308:
            java.lang.String r3 = r15.readLine()
            if (r3 == 0) goto L_0x036d
            java.util.StringTokenizer r5 = new java.util.StringTokenizer
            r5.<init>(r3)
            boolean r3 = r5.hasMoreTokens()
            if (r3 != 0) goto L_0x031a
            goto L_0x0308
        L_0x031a:
            java.lang.String r3 = r5.nextToken()
            java.lang.String r6 = "KPX"
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x0364
            java.lang.String r3 = r5.nextToken()
            java.lang.String r6 = r5.nextToken()
            java.lang.String r5 = r5.nextToken()
            float r5 = java.lang.Float.parseFloat(r5)
            int r5 = (int) r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.util.HashMap<java.lang.String, java.lang.Object[]> r7 = r14.KernPairs
            java.lang.Object r7 = r7.get(r3)
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            if (r7 != 0) goto L_0x0351
            java.util.HashMap<java.lang.String, java.lang.Object[]> r7 = r14.KernPairs
            java.lang.Object[] r8 = new java.lang.Object[r4]
            r8[r1] = r6
            r8[r2] = r5
            r7.put(r3, r8)
            goto L_0x0308
        L_0x0351:
            int r8 = r7.length
            int r9 = r8 + 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            java.lang.System.arraycopy(r7, r1, r9, r1, r8)
            r9[r8] = r6
            int r8 = r8 + r2
            r9[r8] = r5
            java.util.HashMap<java.lang.String, java.lang.Object[]> r5 = r14.KernPairs
            r5.put(r3, r9)
            goto L_0x0308
        L_0x0364:
            java.lang.String r5 = "EndKernPairs"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0308
            r0 = r1
        L_0x036d:
            if (r0 == 0) goto L_0x0381
            com.itextpdf.text.DocumentException r15 = new com.itextpdf.text.DocumentException
            java.lang.String r0 = "missing.endkernpairs.in.1"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = r14.fileName
            r2[r1] = r3
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r2)
            r15.<init>((java.lang.String) r0)
            throw r15
        L_0x0381:
            r15.close()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.Type1Font.process(com.itextpdf.text.pdf.RandomAccessFileOrArray):void");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.itextpdf.text.pdf.PdfStream, com.itextpdf.text.pdf.RandomAccessFileOrArray] */
    public PdfStream getFullFontStream() throws DocumentException {
        RandomAccessFileOrArray randomAccessFileOrArray;
        ? r1 = 0;
        if (this.builtinFont || !this.embedded) {
            return r1;
        }
        try {
            String str = this.fileName.substring(0, this.fileName.length() - 3) + "pfb";
            if (this.pfb == null) {
                randomAccessFileOrArray = new RandomAccessFileOrArray(str, true, Document.plainRandomAccess);
            } else {
                randomAccessFileOrArray = new RandomAccessFileOrArray(this.pfb);
            }
            RandomAccessFileOrArray randomAccessFileOrArray2 = randomAccessFileOrArray;
            byte[] bArr = new byte[(((int) randomAccessFileOrArray2.length()) - 18)];
            int[] iArr = new int[3];
            int i = 0;
            int i2 = 0;
            while (i < 3) {
                if (randomAccessFileOrArray2.read() != 128) {
                    throw new DocumentException(MessageLocalization.getComposedMessage("start.marker.missing.in.1", str));
                } else if (randomAccessFileOrArray2.read() != PFB_TYPES[i]) {
                    throw new DocumentException(MessageLocalization.getComposedMessage("incorrect.segment.type.in.1", str));
                } else {
                    int read = randomAccessFileOrArray2.read() + (randomAccessFileOrArray2.read() << 8) + (randomAccessFileOrArray2.read() << 16) + (randomAccessFileOrArray2.read() << 24);
                    iArr[i] = read;
                    while (read != 0) {
                        int read2 = randomAccessFileOrArray2.read(bArr, i2, read);
                        if (read2 < 0) {
                            throw new DocumentException(MessageLocalization.getComposedMessage("premature.end.in.1", str));
                        }
                        i2 += read2;
                        read -= read2;
                    }
                    i++;
                }
            }
            BaseFont.StreamFont streamFont = new BaseFont.StreamFont(bArr, iArr, this.compressionLevel);
            if (randomAccessFileOrArray2 != null) {
                try {
                    randomAccessFileOrArray2.close();
                } catch (Exception unused) {
                }
            }
            return streamFont;
        } catch (Exception e) {
            throw new DocumentException(e);
        } catch (Throwable th) {
            if (r1 != 0) {
                try {
                    r1.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    private PdfDictionary getFontDescriptor(PdfIndirectReference pdfIndirectReference) {
        if (this.builtinFont) {
            return null;
        }
        PdfDictionary pdfDictionary = new PdfDictionary(PdfName.FONTDESCRIPTOR);
        pdfDictionary.put(PdfName.ASCENT, new PdfNumber(this.Ascender));
        pdfDictionary.put(PdfName.CAPHEIGHT, new PdfNumber(this.CapHeight));
        pdfDictionary.put(PdfName.DESCENT, new PdfNumber(this.Descender));
        pdfDictionary.put(PdfName.FONTBBOX, new PdfRectangle((float) this.llx, (float) this.lly, (float) this.urx, (float) this.ury));
        pdfDictionary.put(PdfName.FONTNAME, new PdfName(this.FontName));
        pdfDictionary.put(PdfName.ITALICANGLE, new PdfNumber(this.ItalicAngle));
        pdfDictionary.put(PdfName.STEMV, new PdfNumber(this.StdVW));
        if (pdfIndirectReference != null) {
            pdfDictionary.put(PdfName.FONTFILE, pdfIndirectReference);
        }
        int i = 0;
        if (this.IsFixedPitch) {
            i = 1;
        }
        int i2 = i | (this.fontSpecific ? 4 : 32);
        if (this.ItalicAngle < 0.0f) {
            i2 |= 64;
        }
        if (this.FontName.indexOf("Caps") >= 0 || this.FontName.endsWith("SC")) {
            i2 |= 131072;
        }
        if (this.Weight.equals("Bold")) {
            i2 |= 262144;
        }
        pdfDictionary.put(PdfName.FLAGS, new PdfNumber(i2));
        return pdfDictionary;
    }

    private PdfDictionary getFontBaseType(PdfIndirectReference pdfIndirectReference, int i, int i2, byte[] bArr) {
        PdfDictionary pdfDictionary = new PdfDictionary(PdfName.FONT);
        pdfDictionary.put(PdfName.SUBTYPE, PdfName.TYPE1);
        pdfDictionary.put(PdfName.BASEFONT, new PdfName(this.FontName));
        boolean z = this.encoding.equals("Cp1252") || this.encoding.equals(BaseFont.MACROMAN);
        if (!this.fontSpecific || this.specialMap != null) {
            int i3 = i;
            while (true) {
                if (i3 > i2) {
                    break;
                } else if (!this.differences[i3].equals(BaseFont.notdef)) {
                    i = i3;
                    break;
                } else {
                    i3++;
                }
            }
            if (z) {
                pdfDictionary.put(PdfName.ENCODING, this.encoding.equals("Cp1252") ? PdfName.WIN_ANSI_ENCODING : PdfName.MAC_ROMAN_ENCODING);
            } else {
                PdfDictionary pdfDictionary2 = new PdfDictionary(PdfName.ENCODING);
                PdfArray pdfArray = new PdfArray();
                boolean z2 = true;
                for (int i4 = i; i4 <= i2; i4++) {
                    if (bArr[i4] != 0) {
                        if (z2) {
                            pdfArray.add((PdfObject) new PdfNumber(i4));
                            z2 = false;
                        }
                        pdfArray.add((PdfObject) new PdfName(this.differences[i4]));
                    } else {
                        z2 = true;
                    }
                }
                pdfDictionary2.put(PdfName.DIFFERENCES, pdfArray);
                pdfDictionary.put(PdfName.ENCODING, pdfDictionary2);
            }
        }
        if (this.specialMap != null || this.forceWidthsOutput || !this.builtinFont || (!this.fontSpecific && !z)) {
            pdfDictionary.put(PdfName.FIRSTCHAR, new PdfNumber(i));
            pdfDictionary.put(PdfName.LASTCHAR, new PdfNumber(i2));
            PdfArray pdfArray2 = new PdfArray();
            while (i <= i2) {
                if (bArr[i] == 0) {
                    pdfArray2.add((PdfObject) new PdfNumber(0));
                } else {
                    pdfArray2.add((PdfObject) new PdfNumber(this.widths[i]));
                }
                i++;
            }
            pdfDictionary.put(PdfName.WIDTHS, pdfArray2);
        }
        if (!this.builtinFont && pdfIndirectReference != null) {
            pdfDictionary.put(PdfName.FONTDESCRIPTOR, pdfIndirectReference);
        }
        return pdfDictionary;
    }

    /* access modifiers changed from: package-private */
    public void writeFont(PdfWriter pdfWriter, PdfIndirectReference pdfIndirectReference, Object[] objArr) throws DocumentException, IOException {
        int i = 0;
        int intValue = objArr[0].intValue();
        int intValue2 = objArr[1].intValue();
        byte[] bArr = objArr[2];
        if (!(objArr[3].booleanValue() && this.subset) || !this.embedded) {
            intValue2 = bArr.length - 1;
            for (int i2 = 0; i2 < bArr.length; i2++) {
                bArr[i2] = 1;
            }
        } else {
            i = intValue;
        }
        PdfIndirectReference pdfIndirectReference2 = null;
        PdfStream fullFontStream = getFullFontStream();
        if (fullFontStream != null) {
            pdfIndirectReference2 = pdfWriter.addToBody(fullFontStream).getIndirectReference();
        }
        PdfDictionary fontDescriptor = getFontDescriptor(pdfIndirectReference2);
        if (fontDescriptor != null) {
            pdfIndirectReference2 = pdfWriter.addToBody(fontDescriptor).getIndirectReference();
        }
        pdfWriter.addToBody((PdfObject) getFontBaseType(pdfIndirectReference2, i, intValue2, bArr), pdfIndirectReference);
    }

    public float getFontDescriptor(int i, float f) {
        switch (i) {
            case 1:
            case 9:
                return (((float) this.Ascender) * f) / 1000.0f;
            case 2:
                return (((float) this.CapHeight) * f) / 1000.0f;
            case 3:
            case 10:
                return (((float) this.Descender) * f) / 1000.0f;
            case 4:
                return this.ItalicAngle;
            case 5:
                return (((float) this.llx) * f) / 1000.0f;
            case 6:
                return (((float) this.lly) * f) / 1000.0f;
            case 7:
                return (((float) this.urx) * f) / 1000.0f;
            case 8:
                return (((float) this.ury) * f) / 1000.0f;
            case 11:
                return 0.0f;
            case 12:
                return (((float) (this.urx - this.llx)) * f) / 1000.0f;
            case 13:
                return (((float) this.UnderlinePosition) * f) / 1000.0f;
            case 14:
                return (((float) this.UnderlineThickness) * f) / 1000.0f;
            default:
                return 0.0f;
        }
    }

    public void setFontDescriptor(int i, float f) {
        if (i != 1) {
            if (i != 3) {
                switch (i) {
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        return;
                }
            }
            this.Descender = (int) f;
            return;
        }
        this.Ascender = (int) f;
    }

    public String getPostscriptFontName() {
        return this.FontName;
    }

    public String[][] getFullFontName() {
        return new String[][]{new String[]{"", "", "", this.FullName}};
    }

    public String[][] getAllNameEntries() {
        return new String[][]{new String[]{"4", "", "", "", this.FullName}};
    }

    public String[][] getFamilyFontName() {
        return new String[][]{new String[]{"", "", "", this.FamilyName}};
    }

    public boolean hasKernPairs() {
        return !this.KernPairs.isEmpty();
    }

    public void setPostscriptFontName(String str) {
        this.FontName = str;
    }

    public boolean setKerning(int i, int i2, int i3) {
        String unicodeToName;
        String unicodeToName2 = GlyphList.unicodeToName(i);
        if (unicodeToName2 == null || (unicodeToName = GlyphList.unicodeToName(i2)) == null) {
            return false;
        }
        Object[] objArr = this.KernPairs.get(unicodeToName2);
        if (objArr == null) {
            this.KernPairs.put(unicodeToName2, new Object[]{unicodeToName, Integer.valueOf(i3)});
            return true;
        }
        for (int i4 = 0; i4 < objArr.length; i4 += 2) {
            if (unicodeToName.equals(objArr[i4])) {
                objArr[i4 + 1] = Integer.valueOf(i3);
                return true;
            }
        }
        int length = objArr.length;
        Object[] objArr2 = new Object[(length + 2)];
        System.arraycopy(objArr, 0, objArr2, 0, length);
        objArr2[length] = unicodeToName;
        objArr2[length + 1] = Integer.valueOf(i3);
        this.KernPairs.put(unicodeToName2, objArr2);
        return true;
    }

    /* access modifiers changed from: protected */
    public int[] getRawCharBBox(int i, String str) {
        Object[] objArr;
        if (str == null) {
            objArr = this.CharMetrics.get(Integer.valueOf(i));
        } else if (str.equals(BaseFont.notdef)) {
            return null;
        } else {
            objArr = this.CharMetrics.get(str);
        }
        if (objArr != null) {
            return (int[]) objArr[3];
        }
        return null;
    }
}
