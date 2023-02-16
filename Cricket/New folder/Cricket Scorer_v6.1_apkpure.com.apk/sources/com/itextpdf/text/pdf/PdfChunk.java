package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.SplitCharacter;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.util.HashMap;
import java.util.HashSet;

public class PdfChunk {
    private static final float ITALIC_ANGLE = 0.21256f;
    private static final String TABSTOP = "TABSTOP";
    public static final float UNDERLINE_OFFSET = -0.33333334f;
    public static final float UNDERLINE_THICKNESS = 0.06666667f;
    private static final HashSet<String> keysAttributes = new HashSet<>();
    private static final HashSet<String> keysNoStroke = new HashSet<>();
    private static final char[] singleSpace = {' '};
    protected IAccessibleElement accessibleElement;
    protected HashMap<String, Object> attributes;
    protected BaseFont baseFont;
    protected boolean changeLeading;
    protected String encoding;
    protected PdfFont font;
    protected Image image;
    protected float imageScalePercentage;
    protected float leading;
    protected boolean newlineSplit;
    protected HashMap<String, Object> noStroke;
    protected float offsetX;
    protected float offsetY;
    protected SplitCharacter splitCharacter;
    protected String value;

    public static boolean noPrint(int i) {
        return (i >= 8203 && i <= 8207) || (i >= 8234 && i <= 8238);
    }

    static {
        keysAttributes.add(Chunk.ACTION);
        keysAttributes.add(Chunk.UNDERLINE);
        keysAttributes.add(Chunk.REMOTEGOTO);
        keysAttributes.add(Chunk.LOCALGOTO);
        keysAttributes.add(Chunk.LOCALDESTINATION);
        keysAttributes.add(Chunk.GENERICTAG);
        keysAttributes.add(Chunk.NEWPAGE);
        keysAttributes.add(Chunk.IMAGE);
        keysAttributes.add(Chunk.BACKGROUND);
        keysAttributes.add(Chunk.PDFANNOTATION);
        keysAttributes.add(Chunk.SKEW);
        keysAttributes.add(Chunk.HSCALE);
        keysAttributes.add(Chunk.SEPARATOR);
        keysAttributes.add(Chunk.TAB);
        keysAttributes.add(Chunk.TABSETTINGS);
        keysAttributes.add(Chunk.CHAR_SPACING);
        keysAttributes.add(Chunk.WORD_SPACING);
        keysAttributes.add(Chunk.LINEHEIGHT);
        keysNoStroke.add(Chunk.SUBSUPSCRIPT);
        keysNoStroke.add(Chunk.SPLITCHARACTER);
        keysNoStroke.add(Chunk.HYPHENATION);
        keysNoStroke.add(Chunk.TEXTRENDERMODE);
    }

    PdfChunk(String str, PdfChunk pdfChunk) {
        this.value = "";
        this.encoding = "Cp1252";
        this.attributes = new HashMap<>();
        this.noStroke = new HashMap<>();
        this.imageScalePercentage = 1.0f;
        this.changeLeading = false;
        this.leading = 0.0f;
        this.accessibleElement = null;
        this.value = str;
        this.font = pdfChunk.font;
        this.attributes = pdfChunk.attributes;
        this.noStroke = pdfChunk.noStroke;
        this.baseFont = pdfChunk.baseFont;
        this.changeLeading = pdfChunk.changeLeading;
        this.leading = pdfChunk.leading;
        Object[] objArr = (Object[]) this.attributes.get(Chunk.IMAGE);
        if (objArr == null) {
            this.image = null;
        } else {
            this.image = (Image) objArr[0];
            this.offsetX = ((Float) objArr[1]).floatValue();
            this.offsetY = ((Float) objArr[2]).floatValue();
            this.changeLeading = ((Boolean) objArr[3]).booleanValue();
        }
        this.encoding = this.font.getFont().getEncoding();
        this.splitCharacter = (SplitCharacter) this.noStroke.get(Chunk.SPLITCHARACTER);
        if (this.splitCharacter == null) {
            this.splitCharacter = DefaultSplitCharacter.DEFAULT;
        }
        this.accessibleElement = pdfChunk.accessibleElement;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    PdfChunk(com.itextpdf.text.Chunk r14, com.itextpdf.text.pdf.PdfAction r15) {
        /*
            r13 = this;
            r13.<init>()
            java.lang.String r0 = ""
            r13.value = r0
            java.lang.String r0 = "Cp1252"
            r13.encoding = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r13.attributes = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r13.noStroke = r0
            r0 = 1065353216(0x3f800000, float:1.0)
            r13.imageScalePercentage = r0
            r0 = 0
            r13.changeLeading = r0
            r1 = 0
            r13.leading = r1
            r1 = 0
            r13.accessibleElement = r1
            java.lang.String r2 = r14.getContent()
            r13.value = r2
            com.itextpdf.text.Font r2 = r14.getFont()
            float r3 = r2.getSize()
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x003c
            r3 = 1094713344(0x41400000, float:12.0)
        L_0x003c:
            com.itextpdf.text.pdf.BaseFont r4 = r2.getBaseFont()
            r13.baseFont = r4
            int r4 = r2.getStyle()
            r5 = -1
            if (r4 != r5) goto L_0x004a
            r4 = r0
        L_0x004a:
            com.itextpdf.text.pdf.BaseFont r5 = r13.baseFont
            r6 = 3
            r7 = 1
            r8 = 2
            if (r5 != 0) goto L_0x0058
            com.itextpdf.text.pdf.BaseFont r4 = r2.getCalculatedBaseFont(r0)
            r13.baseFont = r4
            goto L_0x0087
        L_0x0058:
            r5 = r4 & 1
            if (r5 == 0) goto L_0x0078
            java.util.HashMap<java.lang.String, java.lang.Object> r5 = r13.attributes
            java.lang.String r9 = "TEXTRENDERMODE"
            java.lang.Object[] r10 = new java.lang.Object[r6]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r8)
            r10[r0] = r11
            java.lang.Float r11 = new java.lang.Float
            r12 = 1106247680(0x41f00000, float:30.0)
            float r12 = r3 / r12
            r11.<init>(r12)
            r10[r7] = r11
            r10[r8] = r1
            r5.put(r9, r10)
        L_0x0078:
            r4 = r4 & r8
            if (r4 == 0) goto L_0x0087
            java.util.HashMap<java.lang.String, java.lang.Object> r4 = r13.attributes
            java.lang.String r5 = "SKEW"
            float[] r9 = new float[r8]
            r9 = {0, 1046063444} // fill-array
            r4.put(r5, r9)
        L_0x0087:
            com.itextpdf.text.pdf.PdfFont r4 = new com.itextpdf.text.pdf.PdfFont
            com.itextpdf.text.pdf.BaseFont r5 = r13.baseFont
            r4.<init>(r5, r3)
            r13.font = r4
            java.util.HashMap r3 = r14.getAttributes()
            if (r3 == 0) goto L_0x00ed
            java.util.Set r4 = r3.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x009e:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00d4
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r9 = r5.getKey()
            java.lang.String r9 = (java.lang.String) r9
            java.util.HashSet<java.lang.String> r10 = keysAttributes
            boolean r10 = r10.contains(r9)
            if (r10 == 0) goto L_0x00c2
            java.util.HashMap<java.lang.String, java.lang.Object> r10 = r13.attributes
            java.lang.Object r5 = r5.getValue()
            r10.put(r9, r5)
            goto L_0x009e
        L_0x00c2:
            java.util.HashSet<java.lang.String> r10 = keysNoStroke
            boolean r10 = r10.contains(r9)
            if (r10 == 0) goto L_0x009e
            java.util.HashMap<java.lang.String, java.lang.Object> r10 = r13.noStroke
            java.lang.Object r5 = r5.getValue()
            r10.put(r9, r5)
            goto L_0x009e
        L_0x00d4:
            java.lang.String r4 = ""
            java.lang.String r5 = "GENERICTAG"
            java.lang.Object r3 = r3.get(r5)
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x00ed
            java.util.HashMap<java.lang.String, java.lang.Object> r3 = r13.attributes
            java.lang.String r4 = "GENERICTAG"
            java.lang.String r5 = r14.getContent()
            r3.put(r4, r5)
        L_0x00ed:
            boolean r3 = r2.isUnderlined()
            r4 = 5
            if (r3 == 0) goto L_0x0114
            java.lang.Object[] r3 = new java.lang.Object[r8]
            r3[r0] = r1
            float[] r5 = new float[r4]
            r5 = {0, 1032358025, 0, -1096111445, 0} // fill-array
            r3[r7] = r5
            java.util.HashMap<java.lang.String, java.lang.Object> r5 = r13.attributes
            java.lang.String r9 = "UNDERLINE"
            java.lang.Object r5 = r5.get(r9)
            java.lang.Object[][] r5 = (java.lang.Object[][]) r5
            java.lang.Object[][] r3 = com.itextpdf.text.Utilities.addToArray(r5, r3)
            java.util.HashMap<java.lang.String, java.lang.Object> r5 = r13.attributes
            java.lang.String r9 = "UNDERLINE"
            r5.put(r9, r3)
        L_0x0114:
            boolean r3 = r2.isStrikethru()
            if (r3 == 0) goto L_0x013a
            java.lang.Object[] r3 = new java.lang.Object[r8]
            r3[r0] = r1
            float[] r4 = new float[r4]
            r4 = {0, 1032358025, 0, 1051372203, 0} // fill-array
            r3[r7] = r4
            java.util.HashMap<java.lang.String, java.lang.Object> r4 = r13.attributes
            java.lang.String r5 = "UNDERLINE"
            java.lang.Object r4 = r4.get(r5)
            java.lang.Object[][] r4 = (java.lang.Object[][]) r4
            java.lang.Object[][] r3 = com.itextpdf.text.Utilities.addToArray(r4, r3)
            java.util.HashMap<java.lang.String, java.lang.Object> r4 = r13.attributes
            java.lang.String r5 = "UNDERLINE"
            r4.put(r5, r3)
        L_0x013a:
            if (r15 == 0) goto L_0x0143
            java.util.HashMap<java.lang.String, java.lang.Object> r3 = r13.attributes
            java.lang.String r4 = "ACTION"
            r3.put(r4, r15)
        L_0x0143:
            java.util.HashMap<java.lang.String, java.lang.Object> r15 = r13.noStroke
            java.lang.String r3 = "COLOR"
            com.itextpdf.text.BaseColor r2 = r2.getColor()
            r15.put(r3, r2)
            java.util.HashMap<java.lang.String, java.lang.Object> r15 = r13.noStroke
            java.lang.String r2 = "ENCODING"
            com.itextpdf.text.pdf.PdfFont r3 = r13.font
            com.itextpdf.text.pdf.BaseFont r3 = r3.getFont()
            java.lang.String r3 = r3.getEncoding()
            r15.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.Object> r15 = r13.attributes
            java.lang.String r2 = "LINEHEIGHT"
            java.lang.Object r15 = r15.get(r2)
            java.lang.Float r15 = (java.lang.Float) r15
            if (r15 == 0) goto L_0x0173
            r13.changeLeading = r7
            float r15 = r15.floatValue()
            r13.leading = r15
        L_0x0173:
            java.util.HashMap<java.lang.String, java.lang.Object> r15 = r13.attributes
            java.lang.String r2 = "IMAGE"
            java.lang.Object r15 = r15.get(r2)
            java.lang.Object[] r15 = (java.lang.Object[]) r15
            if (r15 != 0) goto L_0x0182
            r13.image = r1
            goto L_0x01ad
        L_0x0182:
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = r13.attributes
            java.lang.String r2 = "HSCALE"
            r1.remove(r2)
            r0 = r15[r0]
            com.itextpdf.text.Image r0 = (com.itextpdf.text.Image) r0
            r13.image = r0
            r0 = r15[r7]
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r13.offsetX = r0
            r0 = r15[r8]
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r13.offsetY = r0
            r15 = r15[r6]
            java.lang.Boolean r15 = (java.lang.Boolean) r15
            boolean r15 = r15.booleanValue()
            r13.changeLeading = r15
        L_0x01ad:
            java.util.HashMap<java.lang.String, java.lang.Object> r15 = r13.attributes
            java.lang.String r0 = "HSCALE"
            java.lang.Object r15 = r15.get(r0)
            java.lang.Float r15 = (java.lang.Float) r15
            if (r15 == 0) goto L_0x01c2
            com.itextpdf.text.pdf.PdfFont r0 = r13.font
            float r15 = r15.floatValue()
            r0.setHorizontalScaling(r15)
        L_0x01c2:
            com.itextpdf.text.pdf.PdfFont r15 = r13.font
            com.itextpdf.text.pdf.BaseFont r15 = r15.getFont()
            java.lang.String r15 = r15.getEncoding()
            r13.encoding = r15
            java.util.HashMap<java.lang.String, java.lang.Object> r15 = r13.noStroke
            java.lang.String r0 = "SPLITCHARACTER"
            java.lang.Object r15 = r15.get(r0)
            com.itextpdf.text.SplitCharacter r15 = (com.itextpdf.text.SplitCharacter) r15
            r13.splitCharacter = r15
            com.itextpdf.text.SplitCharacter r15 = r13.splitCharacter
            if (r15 != 0) goto L_0x01e2
            com.itextpdf.text.SplitCharacter r15 = com.itextpdf.text.pdf.DefaultSplitCharacter.DEFAULT
            r13.splitCharacter = r15
        L_0x01e2:
            r13.accessibleElement = r14
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfChunk.<init>(com.itextpdf.text.Chunk, com.itextpdf.text.pdf.PdfAction):void");
    }

    PdfChunk(Chunk chunk, PdfAction pdfAction, TabSettings tabSettings) {
        this(chunk, pdfAction);
        if (tabSettings != null && this.attributes.get(Chunk.TABSETTINGS) == null) {
            this.attributes.put(Chunk.TABSETTINGS, tabSettings);
        }
    }

    public int getUnicodeEquivalent(int i) {
        return this.baseFont.getUnicodeEquivalent(i);
    }

    /* access modifiers changed from: protected */
    public int getWord(String str, int i) {
        int length = str.length();
        while (i < length && Character.isLetter(str.charAt(i))) {
            i++;
        }
        return i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r10v6 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r10v4, types: [int, boolean] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x016a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x016c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.pdf.PdfChunk split(float r27) {
        /*
            r26 = this;
            r0 = r26
            r2 = 0
            r0.newlineSplit = r2
            com.itextpdf.text.Image r3 = r0.image
            r4 = 0
            if (r3 == 0) goto L_0x0030
            com.itextpdf.text.Image r2 = r0.image
            float r2 = r2.getScaledWidth()
            int r1 = (r2 > r27 ? 1 : (r2 == r27 ? 0 : -1))
            if (r1 <= 0) goto L_0x002f
            com.itextpdf.text.pdf.PdfChunk r1 = new com.itextpdf.text.pdf.PdfChunk
            java.lang.String r2 = "￼"
            r1.<init>((java.lang.String) r2, (com.itextpdf.text.pdf.PdfChunk) r0)
            java.lang.String r2 = ""
            r0.value = r2
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0.attributes = r2
            r0.image = r4
            com.itextpdf.text.pdf.PdfFont r2 = com.itextpdf.text.pdf.PdfFont.getDefaultFont()
            r0.font = r2
            return r1
        L_0x002f:
            return r4
        L_0x0030:
            java.util.HashMap<java.lang.String, java.lang.Object> r3 = r0.noStroke
            java.lang.String r5 = "HYPHENATION"
            java.lang.Object r3 = r3.get(r5)
            com.itextpdf.text.pdf.HyphenationEvent r3 = (com.itextpdf.text.pdf.HyphenationEvent) r3
            java.lang.String r5 = r0.value
            int r5 = r5.length()
            java.lang.String r6 = r0.value
            char[] r12 = r6.toCharArray()
            com.itextpdf.text.pdf.PdfFont r6 = r0.font
            com.itextpdf.text.pdf.BaseFont r13 = r6.getFont()
            int r6 = r13.getFontType()
            r14 = 2
            r7 = 0
            r8 = -1
            r15 = 10
            r11 = 32
            r10 = 1
            if (r6 != r14) goto L_0x00d0
            int r6 = r13.getUnicodeEquivalent(r11)
            if (r6 == r11) goto L_0x00d0
            r14 = r2
            r6 = r7
            r16 = r8
        L_0x0064:
            if (r14 >= r5) goto L_0x0164
            char r9 = r12[r14]
            int r4 = r13.getUnicodeEquivalent(r9)
            char r4 = (char) r4
            if (r4 != r15) goto L_0x0093
            r0.newlineSplit = r10
            java.lang.String r1 = r0.value
            int r3 = r14 + 1
            java.lang.String r1 = r1.substring(r3)
            java.lang.String r3 = r0.value
            java.lang.String r2 = r3.substring(r2, r14)
            r0.value = r2
            java.lang.String r2 = r0.value
            int r2 = r2.length()
            if (r2 >= r10) goto L_0x008d
            java.lang.String r2 = "\u0001"
            r0.value = r2
        L_0x008d:
            com.itextpdf.text.pdf.PdfChunk r2 = new com.itextpdf.text.pdf.PdfChunk
            r2.<init>((java.lang.String) r1, (com.itextpdf.text.pdf.PdfChunk) r0)
            return r2
        L_0x0093:
            float r9 = r0.getCharWidth(r9)
            float r17 = r7 + r9
            if (r4 != r11) goto L_0x00a0
            int r4 = r14 + 1
            r18 = r17
            goto L_0x00a3
        L_0x00a0:
            r18 = r6
            r4 = r8
        L_0x00a3:
            int r6 = (r17 > r27 ? 1 : (r17 == r27 ? 0 : -1))
            if (r6 <= 0) goto L_0x00aa
            r8 = r4
            goto L_0x0166
        L_0x00aa:
            com.itextpdf.text.SplitCharacter r6 = r0.splitCharacter
            r7 = 0
            com.itextpdf.text.pdf.PdfChunk[] r9 = new com.itextpdf.text.pdf.PdfChunk[r10]
            r9[r2] = r0
            r8 = r14
            r19 = r9
            r9 = r5
            r2 = r10
            r10 = r12
            r2 = r11
            r11 = r19
            boolean r6 = r6.isSplitCharacter(r7, r8, r9, r10, r11)
            if (r6 == 0) goto L_0x00c4
            int r6 = r14 + 1
            r16 = r6
        L_0x00c4:
            int r14 = r14 + 1
            r11 = r2
            r8 = r4
            r7 = r17
            r6 = r18
            r2 = 0
            r4 = 0
            r10 = 1
            goto L_0x0064
        L_0x00d0:
            r2 = r11
            r6 = r7
            r16 = r8
            r4 = 0
        L_0x00d5:
            if (r4 >= r5) goto L_0x0163
            char r9 = r12[r4]
            r10 = 13
            if (r9 == r10) goto L_0x0131
            if (r9 != r15) goto L_0x00e0
            goto L_0x0131
        L_0x00e0:
            boolean r10 = com.itextpdf.text.Utilities.isSurrogatePair((char[]) r12, (int) r4)
            if (r10 == 0) goto L_0x00f7
            char r11 = r12[r4]
            int r13 = r4 + 1
            char r13 = r12[r13]
            int r11 = com.itextpdf.text.Utilities.convertToUtf32((char) r11, (char) r13)
            float r11 = r0.getCharWidth(r11)
            float r7 = r7 + r11
        L_0x00f5:
            r13 = r7
            goto L_0x00fd
        L_0x00f7:
            float r11 = r0.getCharWidth(r9)
            float r7 = r7 + r11
            goto L_0x00f5
        L_0x00fd:
            if (r9 != r2) goto L_0x0106
            int r6 = r4 + 1
            r17 = r6
            r18 = r13
            goto L_0x010a
        L_0x0106:
            r18 = r6
            r17 = r8
        L_0x010a:
            if (r10 == 0) goto L_0x010e
            int r4 = r4 + 1
        L_0x010e:
            int r6 = (r13 > r27 ? 1 : (r13 == r27 ? 0 : -1))
            if (r6 <= 0) goto L_0x0118
            r14 = r4
            r2 = r16
            r8 = r17
            goto L_0x0168
        L_0x0118:
            com.itextpdf.text.SplitCharacter r6 = r0.splitCharacter
            r7 = 0
            r11 = 0
            r8 = r4
            r9 = r5
            r10 = r12
            boolean r6 = r6.isSplitCharacter(r7, r8, r9, r10, r11)
            if (r6 == 0) goto L_0x0129
            int r6 = r4 + 1
            r16 = r6
        L_0x0129:
            int r4 = r4 + 1
            r7 = r13
            r8 = r17
            r6 = r18
            goto L_0x00d5
        L_0x0131:
            r1 = 1
            r0.newlineSplit = r1
            if (r9 != r10) goto L_0x013f
            int r1 = r4 + 1
            if (r1 >= r5) goto L_0x013f
            char r1 = r12[r1]
            if (r1 != r15) goto L_0x013f
            goto L_0x0140
        L_0x013f:
            r14 = 1
        L_0x0140:
            java.lang.String r1 = r0.value
            int r14 = r14 + r4
            java.lang.String r1 = r1.substring(r14)
            java.lang.String r2 = r0.value
            r3 = 0
            java.lang.String r2 = r2.substring(r3, r4)
            r0.value = r2
            java.lang.String r2 = r0.value
            int r2 = r2.length()
            r3 = 1
            if (r2 >= r3) goto L_0x015d
            java.lang.String r2 = " "
            r0.value = r2
        L_0x015d:
            com.itextpdf.text.pdf.PdfChunk r2 = new com.itextpdf.text.pdf.PdfChunk
            r2.<init>((java.lang.String) r1, (com.itextpdf.text.pdf.PdfChunk) r0)
            return r2
        L_0x0163:
            r14 = r4
        L_0x0164:
            r18 = r6
        L_0x0166:
            r2 = r16
        L_0x0168:
            if (r14 != r5) goto L_0x016c
            r4 = 0
            return r4
        L_0x016c:
            if (r2 >= 0) goto L_0x017a
            java.lang.String r1 = r0.value
            java.lang.String r2 = ""
            r0.value = r2
            com.itextpdf.text.pdf.PdfChunk r2 = new com.itextpdf.text.pdf.PdfChunk
            r2.<init>((java.lang.String) r1, (com.itextpdf.text.pdf.PdfChunk) r0)
            return r2
        L_0x017a:
            if (r8 <= r2) goto L_0x0191
            com.itextpdf.text.SplitCharacter r4 = r0.splitCharacter
            r21 = 0
            r22 = 0
            r23 = 1
            char[] r24 = singleSpace
            r25 = 0
            r20 = r4
            boolean r4 = r20.isSplitCharacter(r21, r22, r23, r24, r25)
            if (r4 == 0) goto L_0x0191
            r2 = r8
        L_0x0191:
            if (r3 == 0) goto L_0x01f8
            if (r8 < 0) goto L_0x01f8
            if (r8 >= r14) goto L_0x01f8
            java.lang.String r4 = r0.value
            int r4 = r0.getWord(r4, r8)
            if (r4 <= r8) goto L_0x01f8
            java.lang.String r5 = r0.value
            java.lang.String r5 = r5.substring(r8, r4)
            com.itextpdf.text.pdf.PdfFont r6 = r0.font
            com.itextpdf.text.pdf.BaseFont r6 = r6.getFont()
            com.itextpdf.text.pdf.PdfFont r7 = r0.font
            float r7 = r7.size()
            float r1 = r27 - r18
            java.lang.String r1 = r3.getHyphenatedWordPre(r5, r6, r7, r1)
            java.lang.String r3 = r3.getHyphenatedWordPost()
            int r5 = r1.length()
            if (r5 <= 0) goto L_0x01f8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r3)
            java.lang.String r3 = r0.value
            java.lang.String r3 = r3.substring(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r0.value
            r5 = 0
            java.lang.String r4 = r4.substring(r5, r8)
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            java.lang.String r1 = r0.trim(r1)
            r0.value = r1
            com.itextpdf.text.pdf.PdfChunk r1 = new com.itextpdf.text.pdf.PdfChunk
            r1.<init>((java.lang.String) r2, (com.itextpdf.text.pdf.PdfChunk) r0)
            return r1
        L_0x01f8:
            java.lang.String r1 = r0.value
            java.lang.String r1 = r1.substring(r2)
            java.lang.String r3 = r0.value
            r4 = 0
            java.lang.String r2 = r3.substring(r4, r2)
            java.lang.String r2 = r0.trim(r2)
            r0.value = r2
            com.itextpdf.text.pdf.PdfChunk r2 = new com.itextpdf.text.pdf.PdfChunk
            r2.<init>((java.lang.String) r1, (com.itextpdf.text.pdf.PdfChunk) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfChunk.split(float):com.itextpdf.text.pdf.PdfChunk");
    }

    /* access modifiers changed from: package-private */
    public PdfChunk truncate(float f) {
        if (this.image == null) {
            int i = 1;
            if (f < this.font.width()) {
                String substring = this.value.substring(1);
                this.value = this.value.substring(0, 1);
                return new PdfChunk(substring, this);
            }
            int length = this.value.length();
            float f2 = 0.0f;
            int i2 = 0;
            boolean z = false;
            while (i2 < length) {
                z = Utilities.isSurrogatePair(this.value, i2);
                if (z) {
                    f2 += getCharWidth(Utilities.convertToUtf32(this.value, i2));
                } else {
                    f2 += getCharWidth(this.value.charAt(i2));
                }
                if (f2 > f) {
                    break;
                }
                if (z) {
                    i2++;
                }
                i2++;
            }
            if (i2 == length) {
                return null;
            }
            if (i2 != 0) {
                i = i2;
            } else if (z) {
                i = 2;
            }
            String substring2 = this.value.substring(i);
            this.value = this.value.substring(0, i);
            return new PdfChunk(substring2, this);
        } else if (this.image.getScaledWidth() <= f) {
            return null;
        } else {
            if (this.image.isScaleToFitLineWhenOverflow()) {
                setImageScalePercentage(f / this.image.getWidth());
                return null;
            }
            PdfChunk pdfChunk = new PdfChunk("", this);
            this.value = "";
            this.attributes.remove(Chunk.IMAGE);
            this.image = null;
            this.font = PdfFont.getDefaultFont();
            return pdfChunk;
        }
    }

    /* access modifiers changed from: package-private */
    public PdfFont font() {
        return this.font;
    }

    /* access modifiers changed from: package-private */
    public BaseColor color() {
        return (BaseColor) this.noStroke.get(Chunk.COLOR);
    }

    /* access modifiers changed from: package-private */
    public float width() {
        return width(this.value);
    }

    /* access modifiers changed from: package-private */
    public float width(String str) {
        if (isAttribute(Chunk.SEPARATOR)) {
            return 0.0f;
        }
        if (isImage()) {
            return getImageWidth();
        }
        float width = this.font.width(str);
        if (isAttribute(Chunk.CHAR_SPACING)) {
            width += ((float) str.length()) * ((Float) getAttribute(Chunk.CHAR_SPACING)).floatValue();
        }
        if (!isAttribute(Chunk.WORD_SPACING)) {
            return width;
        }
        int i = 0;
        int i2 = -1;
        while (true) {
            i2 = str.indexOf(32, i2 + 1);
            if (i2 < 0) {
                return width + (((float) i) * ((Float) getAttribute(Chunk.WORD_SPACING)).floatValue());
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public float height() {
        if (isImage()) {
            return getImageHeight();
        }
        return this.font.size();
    }

    public boolean isNewlineSplit() {
        return this.newlineSplit;
    }

    public float getWidthCorrected(float f, float f2) {
        if (this.image != null) {
            return this.image.getScaledWidth() + f;
        }
        int i = 0;
        int i2 = -1;
        while (true) {
            i2 = this.value.indexOf(32, i2 + 1);
            if (i2 < 0) {
                return this.font.width(this.value) + (((float) this.value.length()) * f) + (((float) i) * f2);
            }
            i++;
        }
    }

    public float getTextRise() {
        Float f = (Float) getAttribute(Chunk.SUBSUPSCRIPT);
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    public float trimLastSpace() {
        BaseFont font2 = this.font.getFont();
        if (font2.getFontType() != 2 || font2.getUnicodeEquivalent(32) == 32) {
            if (this.value.length() <= 1 || !this.value.endsWith(" ")) {
                return 0.0f;
            }
            this.value = this.value.substring(0, this.value.length() - 1);
            return this.font.width(32);
        } else if (this.value.length() <= 1 || !this.value.endsWith("\u0001")) {
            return 0.0f;
        } else {
            this.value = this.value.substring(0, this.value.length() - 1);
            return this.font.width(1);
        }
    }

    public float trimFirstSpace() {
        BaseFont font2 = this.font.getFont();
        if (font2.getFontType() != 2 || font2.getUnicodeEquivalent(32) == 32) {
            if (this.value.length() <= 1 || !this.value.startsWith(" ")) {
                return 0.0f;
            }
            this.value = this.value.substring(1);
            return this.font.width(32);
        } else if (this.value.length() <= 1 || !this.value.startsWith("\u0001")) {
            return 0.0f;
        } else {
            this.value = this.value.substring(1);
            return this.font.width(1);
        }
    }

    /* access modifiers changed from: package-private */
    public Object getAttribute(String str) {
        if (this.attributes.containsKey(str)) {
            return this.attributes.get(str);
        }
        return this.noStroke.get(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isAttribute(String str) {
        if (this.attributes.containsKey(str)) {
            return true;
        }
        return this.noStroke.containsKey(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isStroked() {
        return !this.attributes.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean isSeparator() {
        return isAttribute(Chunk.SEPARATOR);
    }

    /* access modifiers changed from: package-private */
    public boolean isHorizontalSeparator() {
        if (isAttribute(Chunk.SEPARATOR)) {
            return !((Boolean) ((Object[]) getAttribute(Chunk.SEPARATOR))[1]).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isTab() {
        return isAttribute(Chunk.TAB);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void adjustLeft(float f) {
        Object[] objArr = (Object[]) this.attributes.get(Chunk.TAB);
        if (objArr != null) {
            this.attributes.put(Chunk.TAB, new Object[]{objArr[0], objArr[1], objArr[2], new Float(f)});
        }
    }

    static TabStop getTabStop(PdfChunk pdfChunk, float f) {
        Object[] objArr = (Object[]) pdfChunk.attributes.get(Chunk.TAB);
        if (objArr == null) {
            return null;
        }
        Float f2 = (Float) objArr[0];
        if (Float.isNaN(f2.floatValue())) {
            return TabSettings.getTabStopNewInstance(f, (TabSettings) pdfChunk.attributes.get(Chunk.TABSETTINGS));
        }
        return TabStop.newInstance(f, f2.floatValue());
    }

    /* access modifiers changed from: package-private */
    public TabStop getTabStop() {
        return (TabStop) this.attributes.get(TABSTOP);
    }

    /* access modifiers changed from: package-private */
    public void setTabStop(TabStop tabStop) {
        this.attributes.put(TABSTOP, tabStop);
    }

    /* access modifiers changed from: package-private */
    public boolean isImage() {
        return this.image != null;
    }

    /* access modifiers changed from: package-private */
    public Image getImage() {
        return this.image;
    }

    /* access modifiers changed from: package-private */
    public float getImageHeight() {
        return this.image.getScaledHeight() * this.imageScalePercentage;
    }

    /* access modifiers changed from: package-private */
    public float getImageWidth() {
        return this.image.getScaledWidth() * this.imageScalePercentage;
    }

    public float getImageScalePercentage() {
        return this.imageScalePercentage;
    }

    public void setImageScalePercentage(float f) {
        this.imageScalePercentage = f;
    }

    /* access modifiers changed from: package-private */
    public void setImageOffsetX(float f) {
        this.offsetX = f;
    }

    /* access modifiers changed from: package-private */
    public float getImageOffsetX() {
        return this.offsetX;
    }

    /* access modifiers changed from: package-private */
    public void setImageOffsetY(float f) {
        this.offsetY = f;
    }

    /* access modifiers changed from: package-private */
    public float getImageOffsetY() {
        return this.offsetY;
    }

    /* access modifiers changed from: package-private */
    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public boolean isSpecialEncoding() {
        return this.encoding.equals("UnicodeBigUnmarked") || this.encoding.equals(BaseFont.IDENTITY_H);
    }

    /* access modifiers changed from: package-private */
    public String getEncoding() {
        return this.encoding;
    }

    /* access modifiers changed from: package-private */
    public int length() {
        return this.value.length();
    }

    /* access modifiers changed from: package-private */
    public int lengthUtf32() {
        if (!BaseFont.IDENTITY_H.equals(this.encoding)) {
            return this.value.length();
        }
        int length = this.value.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (Utilities.isSurrogateHigh(this.value.charAt(i))) {
                i++;
            }
            i2++;
            i++;
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public boolean isExtSplitCharacter(int i, int i2, int i3, char[] cArr, PdfChunk[] pdfChunkArr) {
        return this.splitCharacter.isSplitCharacter(i, i2, i3, cArr, pdfChunkArr);
    }

    /* access modifiers changed from: package-private */
    public String trim(String str) {
        BaseFont font2 = this.font.getFont();
        if (font2.getFontType() != 2 || font2.getUnicodeEquivalent(32) == 32) {
            while (true) {
                if (!str.endsWith(" ") && !str.endsWith("\t")) {
                    break;
                }
                str = str.substring(0, str.length() - 1);
            }
        } else {
            while (str.endsWith("\u0001")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public boolean changeLeading() {
        return this.changeLeading;
    }

    public float getLeading() {
        return this.leading;
    }

    /* access modifiers changed from: package-private */
    public float getCharWidth(int i) {
        if (noPrint(i)) {
            return 0.0f;
        }
        if (isAttribute(Chunk.CHAR_SPACING)) {
            return this.font.width(i) + (((Float) getAttribute(Chunk.CHAR_SPACING)).floatValue() * this.font.getHorizontalScaling());
        }
        if (isImage()) {
            return getImageWidth();
        }
        return this.font.width(i);
    }
}
