package com.itextpdf.text.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.io.StreamUtil;
import com.itextpdf.text.pdf.fonts.cmaps.CMapCache;
import com.itextpdf.text.pdf.fonts.cmaps.CMapCidByte;
import com.itextpdf.text.pdf.fonts.cmaps.CMapCidUni;
import com.itextpdf.text.pdf.fonts.cmaps.CMapUniCid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

class CJKFont extends BaseFont {
    private static final int BRACKET = 1;
    static final String CJK_ENCODING = "UnicodeBigUnmarked";
    private static final int FIRST = 0;
    public static final String RESOURCE_PATH_CMAP = "com/itextpdf/text/pdf/fonts/cmaps/";
    private static final int SERIAL = 2;
    private static final int V1Y = 880;
    private static final HashMap<String, HashMap<String, Object>> allFonts = new HashMap<>();
    static Properties cjkEncodings = new Properties();
    static Properties cjkFonts = new Properties();
    private static boolean propertiesLoaded = false;
    private static final HashMap<String, Set<String>> registryNames = new HashMap<>();
    private String CMap;
    private CMapCidByte cidByte;
    private boolean cidDirect = false;
    private CMapCidUni cidUni;
    private HashMap<String, Object> fontDesc;
    private String fontName;
    private IntHashtable hMetrics;
    private String style = "";
    private CMapUniCid uniCid;
    private String uniMap;
    private IntHashtable vMetrics;

    public int[] getCharBBox(int i) {
        return null;
    }

    public PdfStream getFullFontStream() {
        return null;
    }

    public int getKerning(int i, int i2) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int[] getRawCharBBox(int i, String str) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getRawWidth(int i, String str) {
        return 0;
    }

    public boolean hasKernPairs() {
        return false;
    }

    public boolean setCharAdvance(int i, int i2) {
        return false;
    }

    public boolean setKerning(int i, int i2, int i3) {
        return false;
    }

    private static void loadProperties() {
        if (!propertiesLoaded) {
            synchronized (allFonts) {
                if (!propertiesLoaded) {
                    try {
                        loadRegistry();
                        for (String str : registryNames.get("fonts")) {
                            allFonts.put(str, readFontProperties(str));
                        }
                    } catch (Exception unused) {
                    }
                    propertiesLoaded = true;
                }
            }
        }
    }

    private static void loadRegistry() throws IOException {
        InputStream resourceStream = StreamUtil.getResourceStream("com/itextpdf/text/pdf/fonts/cmaps/cjk_registry.properties");
        Properties properties = new Properties();
        properties.load(resourceStream);
        resourceStream.close();
        for (String str : properties.keySet()) {
            String[] split = properties.getProperty(str).split(" ");
            HashSet hashSet = new HashSet();
            for (String str2 : split) {
                if (str2.length() > 0) {
                    hashSet.add(str2);
                }
            }
            registryNames.put(str, hashSet);
        }
    }

    CJKFont(String str, String str2, boolean z) throws DocumentException {
        loadProperties();
        this.fontType = 2;
        String baseName = getBaseName(str);
        if (!isCJKFont(baseName, str2)) {
            throw new DocumentException(MessageLocalization.getComposedMessage("font.1.with.2.encoding.is.not.a.cjk.font", str, str2));
        }
        if (baseName.length() < str.length()) {
            this.style = str.substring(baseName.length());
            str = baseName;
        }
        this.fontName = str;
        this.encoding = CJK_ENCODING;
        this.vertical = str2.endsWith("V");
        this.CMap = str2;
        if (str2.equals(BaseFont.IDENTITY_H) || str2.equals(BaseFont.IDENTITY_V)) {
            this.cidDirect = true;
        }
        loadCMaps();
    }

    /* access modifiers changed from: package-private */
    public String getUniMap() {
        return this.uniMap;
    }

    private void loadCMaps() throws DocumentException {
        try {
            this.fontDesc = allFonts.get(this.fontName);
            this.hMetrics = (IntHashtable) this.fontDesc.get("W");
            this.vMetrics = (IntHashtable) this.fontDesc.get("W2");
            this.uniMap = "";
            HashMap<String, Set<String>> hashMap = registryNames;
            Iterator it = hashMap.get(((String) this.fontDesc.get("Registry")) + "_Uni").iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String str = (String) it.next();
                this.uniMap = str;
                if (!str.endsWith("V") || !this.vertical) {
                    if (!str.endsWith("V") && !this.vertical) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (this.cidDirect) {
                this.cidUni = CMapCache.getCachedCMapCidUni(this.uniMap);
                return;
            }
            this.uniCid = CMapCache.getCachedCMapUniCid(this.uniMap);
            this.cidByte = CMapCache.getCachedCMapCidByte(this.CMap);
        } catch (Exception e) {
            throw new DocumentException(e);
        }
    }

    public static String GetCompatibleFont(String str) {
        loadProperties();
        for (Map.Entry next : registryNames.entrySet()) {
            if (((Set) next.getValue()).contains(str)) {
                String str2 = (String) next.getKey();
                for (Map.Entry next2 : allFonts.entrySet()) {
                    if (str2.equals(((HashMap) next2.getValue()).get("Registry"))) {
                        return (String) next2.getKey();
                    }
                }
                continue;
            }
        }
        return null;
    }

    public static boolean isCJKFont(String str, String str2) {
        loadProperties();
        if (!registryNames.containsKey("fonts") || !registryNames.get("fonts").contains(str)) {
            return false;
        }
        if (str2.equals(BaseFont.IDENTITY_H) || str2.equals(BaseFont.IDENTITY_V)) {
            return true;
        }
        Set set = registryNames.get((String) allFonts.get(str).get("Registry"));
        if (set == null || !set.contains(str2)) {
            return false;
        }
        return true;
    }

    public int getWidth(int i) {
        int i2;
        if (!this.cidDirect) {
            i = this.uniCid.lookup(i);
        }
        if (this.vertical) {
            i2 = this.vMetrics.get(i);
        } else {
            i2 = this.hMetrics.get(i);
        }
        if (i2 > 0) {
            return i2;
        }
        return 1000;
    }

    public int getWidth(String str) {
        int i;
        int i2;
        int i3 = 0;
        if (this.cidDirect) {
            i = 0;
            while (i3 < str.length()) {
                i += getWidth((int) str.charAt(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < str.length()) {
                if (Utilities.isSurrogatePair(str, i3)) {
                    i2 = Utilities.convertToUtf32(str, i3);
                    i3++;
                } else {
                    i2 = str.charAt(i3);
                }
                i4 = i + getWidth(i2);
                i3++;
            }
        }
        return i;
    }

    private PdfDictionary getFontDescriptor() {
        PdfDictionary pdfDictionary = new PdfDictionary(PdfName.FONTDESCRIPTOR);
        pdfDictionary.put(PdfName.ASCENT, new PdfLiteral((String) this.fontDesc.get("Ascent")));
        pdfDictionary.put(PdfName.CAPHEIGHT, new PdfLiteral((String) this.fontDesc.get("CapHeight")));
        pdfDictionary.put(PdfName.DESCENT, new PdfLiteral((String) this.fontDesc.get("Descent")));
        pdfDictionary.put(PdfName.FLAGS, new PdfLiteral((String) this.fontDesc.get("Flags")));
        pdfDictionary.put(PdfName.FONTBBOX, new PdfLiteral((String) this.fontDesc.get("FontBBox")));
        PdfName pdfName = PdfName.FONTNAME;
        pdfDictionary.put(pdfName, new PdfName(this.fontName + this.style));
        pdfDictionary.put(PdfName.ITALICANGLE, new PdfLiteral((String) this.fontDesc.get("ItalicAngle")));
        pdfDictionary.put(PdfName.STEMV, new PdfLiteral((String) this.fontDesc.get("StemV")));
        PdfDictionary pdfDictionary2 = new PdfDictionary();
        pdfDictionary2.put(PdfName.PANOSE, new PdfString((String) this.fontDesc.get("Panose"), (String) null));
        pdfDictionary.put(PdfName.STYLE, pdfDictionary2);
        return pdfDictionary;
    }

    private PdfDictionary getCIDFont(PdfIndirectReference pdfIndirectReference, IntHashtable intHashtable) {
        PdfDictionary pdfDictionary = new PdfDictionary(PdfName.FONT);
        pdfDictionary.put(PdfName.SUBTYPE, PdfName.CIDFONTTYPE0);
        PdfName pdfName = PdfName.BASEFONT;
        pdfDictionary.put(pdfName, new PdfName(this.fontName + this.style));
        pdfDictionary.put(PdfName.FONTDESCRIPTOR, pdfIndirectReference);
        int[] orderedKeys = intHashtable.toOrderedKeys();
        String convertToHCIDMetrics = convertToHCIDMetrics(orderedKeys, this.hMetrics);
        if (convertToHCIDMetrics != null) {
            pdfDictionary.put(PdfName.W, new PdfLiteral(convertToHCIDMetrics));
        }
        if (this.vertical) {
            String convertToVCIDMetrics = convertToVCIDMetrics(orderedKeys, this.vMetrics, this.hMetrics);
            if (convertToVCIDMetrics != null) {
                pdfDictionary.put(PdfName.W2, new PdfLiteral(convertToVCIDMetrics));
            }
        } else {
            pdfDictionary.put(PdfName.DW, new PdfNumber(1000));
        }
        PdfDictionary pdfDictionary2 = new PdfDictionary();
        if (this.cidDirect) {
            pdfDictionary2.put(PdfName.REGISTRY, new PdfString(this.cidUni.getRegistry(), (String) null));
            pdfDictionary2.put(PdfName.ORDERING, new PdfString(this.cidUni.getOrdering(), (String) null));
            pdfDictionary2.put(PdfName.SUPPLEMENT, new PdfNumber(this.cidUni.getSupplement()));
        } else {
            pdfDictionary2.put(PdfName.REGISTRY, new PdfString(this.cidByte.getRegistry(), (String) null));
            pdfDictionary2.put(PdfName.ORDERING, new PdfString(this.cidByte.getOrdering(), (String) null));
            pdfDictionary2.put(PdfName.SUPPLEMENT, new PdfNumber(this.cidByte.getSupplement()));
        }
        pdfDictionary.put(PdfName.CIDSYSTEMINFO, pdfDictionary2);
        return pdfDictionary;
    }

    private PdfDictionary getFontBaseType(PdfIndirectReference pdfIndirectReference) {
        PdfDictionary pdfDictionary = new PdfDictionary(PdfName.FONT);
        pdfDictionary.put(PdfName.SUBTYPE, PdfName.TYPE0);
        String str = this.fontName;
        if (this.style.length() > 0) {
            str = str + "-" + this.style.substring(1);
        }
        pdfDictionary.put(PdfName.BASEFONT, new PdfName(str + "-" + this.CMap));
        pdfDictionary.put(PdfName.ENCODING, new PdfName(this.CMap));
        pdfDictionary.put(PdfName.DESCENDANTFONTS, new PdfArray((PdfObject) pdfIndirectReference));
        return pdfDictionary;
    }

    /* access modifiers changed from: package-private */
    public void writeFont(PdfWriter pdfWriter, PdfIndirectReference pdfIndirectReference, Object[] objArr) throws DocumentException, IOException {
        IntHashtable intHashtable = objArr[0];
        PdfDictionary fontDescriptor = getFontDescriptor();
        PdfIndirectReference indirectReference = fontDescriptor != null ? pdfWriter.addToBody(fontDescriptor).getIndirectReference() : null;
        PdfDictionary cIDFont = getCIDFont(indirectReference, intHashtable);
        if (cIDFont != null) {
            indirectReference = pdfWriter.addToBody(cIDFont).getIndirectReference();
        }
        pdfWriter.addToBody((PdfObject) getFontBaseType(indirectReference), pdfIndirectReference);
    }

    private float getDescNumber(String str) {
        return (float) Integer.parseInt((String) this.fontDesc.get(str));
    }

    private float getBBox(int i) {
        StringTokenizer stringTokenizer = new StringTokenizer((String) this.fontDesc.get("FontBBox"), " []\r\n\t\f");
        String nextToken = stringTokenizer.nextToken();
        for (int i2 = 0; i2 < i; i2++) {
            nextToken = stringTokenizer.nextToken();
        }
        return (float) Integer.parseInt(nextToken);
    }

    public float getFontDescriptor(int i, float f) {
        switch (i) {
            case 1:
            case 9:
                return (getDescNumber("Ascent") * f) / 1000.0f;
            case 2:
                return (getDescNumber("CapHeight") * f) / 1000.0f;
            case 3:
            case 10:
                return (getDescNumber("Descent") * f) / 1000.0f;
            case 4:
                return getDescNumber("ItalicAngle");
            case 5:
                return (f * getBBox(0)) / 1000.0f;
            case 6:
                return (f * getBBox(1)) / 1000.0f;
            case 7:
                return (f * getBBox(2)) / 1000.0f;
            case 8:
                return (f * getBBox(3)) / 1000.0f;
            case 11:
                return 0.0f;
            case 12:
                return (f * (getBBox(2) - getBBox(0))) / 1000.0f;
            default:
                return 0.0f;
        }
    }

    public String getPostscriptFontName() {
        return this.fontName;
    }

    public String[][] getFullFontName() {
        return new String[][]{new String[]{"", "", "", this.fontName}};
    }

    public String[][] getAllNameEntries() {
        return new String[][]{new String[]{"4", "", "", "", this.fontName}};
    }

    public String[][] getFamilyFontName() {
        return getFullFontName();
    }

    static IntHashtable createMetric(String str) {
        IntHashtable intHashtable = new IntHashtable();
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            intHashtable.put(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        return intHashtable;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009e, code lost:
        r6 = r9;
        r4 = r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String convertToHCIDMetrics(int[] r13, com.itextpdf.text.pdf.IntHashtable r14) {
        /*
            r0 = 0
            int r1 = r13.length
            if (r1 != 0) goto L_0x0005
            return r0
        L_0x0005:
            r1 = 0
            r2 = r1
            r3 = r2
            r4 = r3
        L_0x0009:
            int r5 = r13.length
            if (r2 >= r5) goto L_0x001a
            r4 = r13[r2]
            int r3 = r14.get(r4)
            if (r3 == 0) goto L_0x0017
            int r2 = r2 + 1
            goto L_0x001a
        L_0x0017:
            int r2 = r2 + 1
            goto L_0x0009
        L_0x001a:
            if (r3 != 0) goto L_0x001d
            return r0
        L_0x001d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r5 = 91
            r0.append(r5)
            r0.append(r4)
            r6 = r4
            r4 = r3
            r3 = r1
        L_0x002d:
            r7 = 93
            r8 = 32
            int r9 = r13.length
            if (r2 >= r9) goto L_0x00a3
            r9 = r13[r2]
            int r10 = r14.get(r9)
            if (r10 != 0) goto L_0x003e
            goto L_0x00a0
        L_0x003e:
            r11 = 2
            switch(r3) {
                case 0: goto L_0x0080;
                case 1: goto L_0x005d;
                case 2: goto L_0x0043;
                default: goto L_0x0042;
            }
        L_0x0042:
            goto L_0x009e
        L_0x0043:
            int r7 = r6 + 1
            if (r9 != r7) goto L_0x0049
            if (r10 == r4) goto L_0x009e
        L_0x0049:
            r0.append(r8)
            r0.append(r6)
            r0.append(r8)
            r0.append(r4)
            r0.append(r8)
            r0.append(r9)
        L_0x005b:
            r3 = r1
            goto L_0x009e
        L_0x005d:
            int r12 = r6 + 1
            if (r9 != r12) goto L_0x006a
            if (r10 != r4) goto L_0x006a
            r0.append(r7)
            r0.append(r6)
            goto L_0x0086
        L_0x006a:
            if (r9 != r12) goto L_0x0073
            r0.append(r8)
            r0.append(r4)
            goto L_0x009e
        L_0x0073:
            r0.append(r8)
            r0.append(r4)
            r0.append(r7)
            r0.append(r9)
            goto L_0x005b
        L_0x0080:
            int r6 = r6 + 1
            if (r9 != r6) goto L_0x0088
            if (r10 != r4) goto L_0x0088
        L_0x0086:
            r3 = r11
            goto L_0x009e
        L_0x0088:
            if (r9 != r6) goto L_0x0092
            r0.append(r5)
            r0.append(r4)
            r3 = 1
            goto L_0x009e
        L_0x0092:
            r0.append(r5)
            r0.append(r4)
            r0.append(r7)
            r0.append(r9)
        L_0x009e:
            r6 = r9
            r4 = r10
        L_0x00a0:
            int r2 = r2 + 1
            goto L_0x002d
        L_0x00a3:
            switch(r3) {
                case 0: goto L_0x00c3;
                case 1: goto L_0x00b7;
                case 2: goto L_0x00a7;
                default: goto L_0x00a6;
            }
        L_0x00a6:
            goto L_0x00ce
        L_0x00a7:
            r0.append(r8)
            r0.append(r6)
            r0.append(r8)
            r0.append(r4)
            r0.append(r7)
            goto L_0x00ce
        L_0x00b7:
            r0.append(r8)
            r0.append(r4)
            java.lang.String r13 = "]]"
            r0.append(r13)
            goto L_0x00ce
        L_0x00c3:
            r0.append(r5)
            r0.append(r4)
            java.lang.String r13 = "]]"
            r0.append(r13)
        L_0x00ce:
            java.lang.String r13 = r0.toString()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.CJKFont.convertToHCIDMetrics(int[], com.itextpdf.text.pdf.IntHashtable):java.lang.String");
    }

    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v1 */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String convertToVCIDMetrics(int[] r18, com.itextpdf.text.pdf.IntHashtable r19, com.itextpdf.text.pdf.IntHashtable r20) {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = 0
            int r4 = r0.length
            if (r4 != 0) goto L_0x000b
            return r3
        L_0x000b:
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x000f:
            int r9 = r0.length
            if (r5 >= r9) goto L_0x0024
            r8 = r0[r5]
            int r6 = r1.get(r8)
            if (r6 == 0) goto L_0x001d
            int r5 = r5 + 1
            goto L_0x0024
        L_0x001d:
            int r7 = r2.get(r8)
            int r5 = r5 + 1
            goto L_0x000f
        L_0x0024:
            if (r6 != 0) goto L_0x0027
            return r3
        L_0x0027:
            if (r7 != 0) goto L_0x002b
            r7 = 1000(0x3e8, float:1.401E-42)
        L_0x002b:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r10 = 91
            r9.append(r10)
            r9.append(r8)
            r10 = 0
        L_0x0039:
            r11 = 880(0x370, float:1.233E-42)
            r12 = 2
            r13 = 32
            int r14 = r0.length
            if (r5 >= r14) goto L_0x00b8
            r14 = r0[r5]
            int r15 = r1.get(r14)
            if (r15 != 0) goto L_0x004b
            goto L_0x00b5
        L_0x004b:
            int r16 = r2.get(r8)
            if (r16 != 0) goto L_0x0054
            r3 = 1000(0x3e8, float:1.401E-42)
            goto L_0x0056
        L_0x0054:
            r3 = r16
        L_0x0056:
            if (r10 == 0) goto L_0x0086
            if (r10 == r12) goto L_0x005b
            goto L_0x00b0
        L_0x005b:
            int r12 = r8 + 1
            if (r14 != r12) goto L_0x0063
            if (r15 != r6) goto L_0x0063
            if (r3 == r7) goto L_0x00b0
        L_0x0063:
            r9.append(r13)
            r9.append(r8)
            r9.append(r13)
            int r6 = -r6
            r9.append(r6)
            r9.append(r13)
            int r7 = r7 / 2
            r9.append(r7)
            r9.append(r13)
            r9.append(r11)
            r9.append(r13)
            r9.append(r14)
            r12 = 0
            goto L_0x00b1
        L_0x0086:
            int r4 = r8 + 1
            if (r14 != r4) goto L_0x008f
            if (r15 != r6) goto L_0x008f
            if (r3 != r7) goto L_0x008f
            goto L_0x00b1
        L_0x008f:
            r9.append(r13)
            r9.append(r8)
            r9.append(r13)
            int r4 = -r6
            r9.append(r4)
            r9.append(r13)
            int r7 = r7 / 2
            r9.append(r7)
            r9.append(r13)
            r9.append(r11)
            r9.append(r13)
            r9.append(r14)
        L_0x00b0:
            r12 = r10
        L_0x00b1:
            r7 = r3
            r10 = r12
            r8 = r14
            r6 = r15
        L_0x00b5:
            int r5 = r5 + 1
            goto L_0x0039
        L_0x00b8:
            r9.append(r13)
            r9.append(r8)
            r9.append(r13)
            int r0 = -r6
            r9.append(r0)
            r9.append(r13)
            int r7 = r7 / r12
            r9.append(r7)
            r9.append(r13)
            r9.append(r11)
            java.lang.String r0 = " ]"
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.CJKFont.convertToVCIDMetrics(int[], com.itextpdf.text.pdf.IntHashtable, com.itextpdf.text.pdf.IntHashtable):java.lang.String");
    }

    private static HashMap<String, Object> readFontProperties(String str) throws IOException {
        InputStream resourceStream = StreamUtil.getResourceStream(RESOURCE_PATH_CMAP + (str + ".properties"));
        Properties properties = new Properties();
        properties.load(resourceStream);
        resourceStream.close();
        IntHashtable createMetric = createMetric(properties.getProperty("W"));
        properties.remove("W");
        IntHashtable createMetric2 = createMetric(properties.getProperty("W2"));
        properties.remove("W2");
        HashMap<String, Object> hashMap = new HashMap<>();
        Enumeration keys = properties.keys();
        while (keys.hasMoreElements()) {
            String str2 = (String) keys.nextElement();
            hashMap.put(str2, properties.getProperty(str2));
        }
        hashMap.put("W", createMetric);
        hashMap.put("W2", createMetric2);
        return hashMap;
    }

    public int getUnicodeEquivalent(int i) {
        if (!this.cidDirect) {
            return i;
        }
        if (i == 32767) {
            return 10;
        }
        return this.cidUni.lookup(i);
    }

    public int getCidCode(int i) {
        if (this.cidDirect) {
            return i;
        }
        return this.uniCid.lookup(i);
    }

    public boolean charExists(int i) {
        if (!this.cidDirect && this.cidByte.lookup(this.uniCid.lookup(i)).length <= 0) {
            return false;
        }
        return true;
    }

    public void setPostscriptFontName(String str) {
        this.fontName = str;
    }

    public byte[] convertToBytes(String str) {
        int i;
        if (this.cidDirect) {
            return super.convertToBytes(str);
        }
        try {
            int i2 = 0;
            if (str.length() == 1) {
                return convertToBytes((int) str.charAt(0));
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (i2 < str.length()) {
                if (Utilities.isSurrogatePair(str, i2)) {
                    i = Utilities.convertToUtf32(str, i2);
                    i2++;
                } else {
                    i = str.charAt(i2);
                }
                byteArrayOutputStream.write(convertToBytes(i));
                i2++;
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] convertToBytes(int i) {
        if (this.cidDirect) {
            return super.convertToBytes(i);
        }
        return this.cidByte.lookup(this.uniCid.lookup(i));
    }

    public boolean isIdentity() {
        return this.cidDirect;
    }
}
