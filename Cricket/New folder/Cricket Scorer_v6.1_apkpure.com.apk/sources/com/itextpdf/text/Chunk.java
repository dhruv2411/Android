package com.itextpdf.text;

import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.HyphenationEvent;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.draw.DrawInterface;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chunk implements Element, IAccessibleElement {
    public static final String ACTION = "ACTION";
    public static final String BACKGROUND = "BACKGROUND";
    public static final String CHAR_SPACING = "CHAR_SPACING";
    public static final String COLOR = "COLOR";
    public static final String ENCODING = "ENCODING";
    public static final String GENERICTAG = "GENERICTAG";
    public static final String HSCALE = "HSCALE";
    public static final String HYPHENATION = "HYPHENATION";
    public static final String IMAGE = "IMAGE";
    public static final String LINEHEIGHT = "LINEHEIGHT";
    public static final String LOCALDESTINATION = "LOCALDESTINATION";
    public static final String LOCALGOTO = "LOCALGOTO";
    public static final Chunk NEWLINE = new Chunk("\n");
    public static final String NEWPAGE = "NEWPAGE";
    public static final Chunk NEXTPAGE = new Chunk("");
    public static final String OBJECT_REPLACEMENT_CHARACTER = "￼";
    public static final String PDFANNOTATION = "PDFANNOTATION";
    public static final String REMOTEGOTO = "REMOTEGOTO";
    public static final String SEPARATOR = "SEPARATOR";
    public static final String SKEW = "SKEW";
    public static final Chunk SPACETABBING = new Chunk(Float.valueOf(Float.NaN), true);
    public static final String SPLITCHARACTER = "SPLITCHARACTER";
    public static final String SUBSUPSCRIPT = "SUBSUPSCRIPT";
    public static final String TAB = "TAB";
    public static final Chunk TABBING = new Chunk(Float.valueOf(Float.NaN), false);
    public static final String TABSETTINGS = "TABSETTINGS";
    public static final String TEXTRENDERMODE = "TEXTRENDERMODE";
    public static final String UNDERLINE = "UNDERLINE";
    public static final String WHITESPACE = "WHITESPACE";
    public static final String WORD_SPACING = "WORD_SPACING";
    protected HashMap<PdfName, PdfObject> accessibleAttributes;
    protected HashMap<String, Object> attributes;
    protected StringBuffer content;
    private String contentWithNoTabs;
    protected Font font;
    private AccessibleElementId id;
    protected PdfName role;

    public boolean isContent() {
        return true;
    }

    public boolean isInline() {
        return true;
    }

    public boolean isNestable() {
        return true;
    }

    public int type() {
        return 10;
    }

    static {
        NEWLINE.setRole(PdfName.P);
        NEXTPAGE.setNewPage();
    }

    public Chunk() {
        this.content = null;
        this.font = null;
        this.attributes = null;
        this.role = null;
        this.accessibleAttributes = null;
        this.id = null;
        this.contentWithNoTabs = null;
        this.content = new StringBuffer();
        this.font = new Font();
        this.role = PdfName.SPAN;
    }

    public Chunk(Chunk chunk) {
        this.content = null;
        this.font = null;
        this.attributes = null;
        this.role = null;
        this.accessibleAttributes = null;
        this.id = null;
        this.contentWithNoTabs = null;
        if (chunk.content != null) {
            this.content = new StringBuffer(chunk.content.toString());
        }
        if (chunk.font != null) {
            this.font = new Font(chunk.font);
        }
        if (chunk.attributes != null) {
            this.attributes = new HashMap<>(chunk.attributes);
        }
        this.role = chunk.role;
        if (chunk.accessibleAttributes != null) {
            this.accessibleAttributes = new HashMap<>(chunk.accessibleAttributes);
        }
        this.id = chunk.getId();
    }

    public Chunk(String str, Font font2) {
        this.content = null;
        this.font = null;
        this.attributes = null;
        this.role = null;
        this.accessibleAttributes = null;
        this.id = null;
        this.contentWithNoTabs = null;
        this.content = new StringBuffer(str);
        this.font = font2;
        this.role = PdfName.SPAN;
    }

    public Chunk(String str) {
        this(str, new Font());
    }

    public Chunk(char c, Font font2) {
        this.content = null;
        this.font = null;
        this.attributes = null;
        this.role = null;
        this.accessibleAttributes = null;
        this.id = null;
        this.contentWithNoTabs = null;
        this.content = new StringBuffer();
        this.content.append(c);
        this.font = font2;
        this.role = PdfName.SPAN;
    }

    public Chunk(char c) {
        this(c, new Font());
    }

    public Chunk(Image image, float f, float f2) {
        this(OBJECT_REPLACEMENT_CHARACTER, new Font());
        Image instance = Image.getInstance(image);
        instance.setAbsolutePosition(Float.NaN, Float.NaN);
        setAttribute(IMAGE, new Object[]{instance, new Float(f), new Float(f2), Boolean.FALSE});
        this.role = null;
    }

    public Chunk(DrawInterface drawInterface) {
        this(drawInterface, false);
    }

    public Chunk(DrawInterface drawInterface, boolean z) {
        this(OBJECT_REPLACEMENT_CHARACTER, new Font());
        setAttribute(SEPARATOR, new Object[]{drawInterface, Boolean.valueOf(z)});
        this.role = null;
    }

    @Deprecated
    public Chunk(DrawInterface drawInterface, float f) {
        this(drawInterface, f, false);
    }

    @Deprecated
    public Chunk(DrawInterface drawInterface, float f, boolean z) {
        this(OBJECT_REPLACEMENT_CHARACTER, new Font());
        if (f < 0.0f) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("a.tab.position.may.not.be.lower.than.0.yours.is.1", String.valueOf(f)));
        }
        setAttribute(TAB, new Object[]{drawInterface, new Float(f), Boolean.valueOf(z), new Float(0.0f)});
        this.role = PdfName.ARTIFACT;
    }

    private Chunk(Float f, boolean z) {
        this(OBJECT_REPLACEMENT_CHARACTER, new Font());
        if (f.floatValue() < 0.0f) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("a.tab.position.may.not.be.lower.than.0.yours.is.1", String.valueOf(f)));
        }
        setAttribute(TAB, new Object[]{f, Boolean.valueOf(z)});
        setAttribute(SPLITCHARACTER, TabSplitCharacter.TAB);
        setAttribute(TABSETTINGS, (Object) null);
        this.role = PdfName.ARTIFACT;
    }

    public Chunk(Image image, float f, float f2, boolean z) {
        this(OBJECT_REPLACEMENT_CHARACTER, new Font());
        setAttribute(IMAGE, new Object[]{image, new Float(f), new Float(f2), Boolean.valueOf(z)});
        this.role = PdfName.ARTIFACT;
    }

    public boolean process(ElementListener elementListener) {
        try {
            return elementListener.add(this);
        } catch (DocumentException unused) {
            return false;
        }
    }

    public List<Chunk> getChunks() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        return arrayList;
    }

    public StringBuffer append(String str) {
        this.contentWithNoTabs = null;
        StringBuffer stringBuffer = this.content;
        stringBuffer.append(str);
        return stringBuffer;
    }

    public void setFont(Font font2) {
        this.font = font2;
    }

    public Font getFont() {
        return this.font;
    }

    public String getContent() {
        if (this.contentWithNoTabs == null) {
            this.contentWithNoTabs = this.content.toString().replaceAll("\t", "");
        }
        return this.contentWithNoTabs;
    }

    public String toString() {
        return getContent();
    }

    public boolean isEmpty() {
        return this.content.toString().trim().length() == 0 && this.content.toString().indexOf("\n") == -1 && this.attributes == null;
    }

    public float getWidthPoint() {
        if (getImage() != null) {
            return getImage().getScaledWidth();
        }
        return this.font.getCalculatedBaseFont(true).getWidthPoint(getContent(), this.font.getCalculatedSize()) * getHorizontalScaling();
    }

    public boolean hasAttributes() {
        return this.attributes != null && !this.attributes.isEmpty();
    }

    public boolean hasAccessibleAttributes() {
        return this.accessibleAttributes != null && !this.accessibleAttributes.isEmpty();
    }

    public HashMap<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(HashMap<String, Object> hashMap) {
        this.attributes = hashMap;
    }

    private Chunk setAttribute(String str, Object obj) {
        if (this.attributes == null) {
            this.attributes = new HashMap<>();
        }
        this.attributes.put(str, obj);
        return this;
    }

    public Chunk setHorizontalScaling(float f) {
        return setAttribute(HSCALE, new Float(f));
    }

    public float getHorizontalScaling() {
        Float f;
        if (this.attributes == null || (f = (Float) this.attributes.get(HSCALE)) == null) {
            return 1.0f;
        }
        return f.floatValue();
    }

    public Chunk setUnderline(float f, float f2) {
        return setUnderline((BaseColor) null, f, 0.0f, f2, 0.0f, 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.Chunk setUnderline(com.itextpdf.text.BaseColor r4, float r5, float r6, float r7, float r8, int r9) {
        /*
            r3 = this;
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r3.attributes
            if (r0 != 0) goto L_0x000b
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r3.attributes = r0
        L_0x000b:
            r0 = 2
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
            r1[r2] = r4
            r4 = 5
            float[] r4 = new float[r4]
            r4[r2] = r5
            r5 = 1
            r4[r5] = r6
            r4[r0] = r7
            r6 = 3
            r4[r6] = r8
            r6 = 4
            float r7 = (float) r9
            r4[r6] = r7
            r1[r5] = r4
            java.util.HashMap<java.lang.String, java.lang.Object> r4 = r3.attributes
            java.lang.String r5 = "UNDERLINE"
            java.lang.Object r4 = r4.get(r5)
            java.lang.Object[][] r4 = (java.lang.Object[][]) r4
            java.lang.Object[][] r4 = com.itextpdf.text.Utilities.addToArray(r4, r1)
            java.lang.String r5 = "UNDERLINE"
            com.itextpdf.text.Chunk r4 = r3.setAttribute(r5, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.Chunk.setUnderline(com.itextpdf.text.BaseColor, float, float, float, float, int):com.itextpdf.text.Chunk");
    }

    public Chunk setTextRise(float f) {
        return setAttribute(SUBSUPSCRIPT, new Float(f));
    }

    public float getTextRise() {
        if (this.attributes == null || !this.attributes.containsKey(SUBSUPSCRIPT)) {
            return 0.0f;
        }
        return ((Float) this.attributes.get(SUBSUPSCRIPT)).floatValue();
    }

    public Chunk setSkew(float f, float f2) {
        return setAttribute(SKEW, new float[]{(float) Math.tan((((double) f) * 3.141592653589793d) / 180.0d), (float) Math.tan((((double) f2) * 3.141592653589793d) / 180.0d)});
    }

    public Chunk setBackground(BaseColor baseColor) {
        return setBackground(baseColor, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.Chunk setBackground(com.itextpdf.text.BaseColor r5, float r6, float r7, float r8, float r9) {
        /*
            r4 = this;
            java.lang.String r0 = "BACKGROUND"
            r1 = 2
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r3 = 0
            r2[r3] = r5
            r5 = 4
            float[] r5 = new float[r5]
            r5[r3] = r6
            r6 = 1
            r5[r6] = r7
            r5[r1] = r8
            r7 = 3
            r5[r7] = r9
            r2[r6] = r5
            com.itextpdf.text.Chunk r5 = r4.setAttribute(r0, r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.Chunk.setBackground(com.itextpdf.text.BaseColor, float, float, float, float):com.itextpdf.text.Chunk");
    }

    public Chunk setTextRenderMode(int i, float f, BaseColor baseColor) {
        return setAttribute(TEXTRENDERMODE, new Object[]{Integer.valueOf(i), new Float(f), baseColor});
    }

    public Chunk setSplitCharacter(SplitCharacter splitCharacter) {
        return setAttribute(SPLITCHARACTER, splitCharacter);
    }

    public Chunk setHyphenation(HyphenationEvent hyphenationEvent) {
        return setAttribute(HYPHENATION, hyphenationEvent);
    }

    public Chunk setRemoteGoto(String str, String str2) {
        return setAttribute(REMOTEGOTO, new Object[]{str, str2});
    }

    public Chunk setRemoteGoto(String str, int i) {
        return setAttribute(REMOTEGOTO, new Object[]{str, Integer.valueOf(i)});
    }

    public Chunk setLocalGoto(String str) {
        return setAttribute(LOCALGOTO, str);
    }

    public Chunk setLocalDestination(String str) {
        return setAttribute(LOCALDESTINATION, str);
    }

    public Chunk setGenericTag(String str) {
        return setAttribute(GENERICTAG, str);
    }

    public Chunk setLineHeight(float f) {
        return setAttribute(LINEHEIGHT, Float.valueOf(f));
    }

    public Image getImage() {
        Object[] objArr;
        if (this.attributes == null || (objArr = (Object[]) this.attributes.get(IMAGE)) == null) {
            return null;
        }
        return (Image) objArr[0];
    }

    public Chunk setAction(PdfAction pdfAction) {
        setRole(PdfName.LINK);
        return setAttribute(ACTION, pdfAction);
    }

    public Chunk setAnchor(URL url) {
        setRole(PdfName.LINK);
        String externalForm = url.toExternalForm();
        setAccessibleAttribute(PdfName.ALT, new PdfString(externalForm));
        return setAttribute(ACTION, new PdfAction(externalForm));
    }

    public Chunk setAnchor(String str) {
        setRole(PdfName.LINK);
        setAccessibleAttribute(PdfName.ALT, new PdfString(str));
        return setAttribute(ACTION, new PdfAction(str));
    }

    public Chunk setNewPage() {
        return setAttribute(NEWPAGE, (Object) null);
    }

    public Chunk setAnnotation(PdfAnnotation pdfAnnotation) {
        return setAttribute(PDFANNOTATION, pdfAnnotation);
    }

    public HyphenationEvent getHyphenation() {
        if (this.attributes == null) {
            return null;
        }
        return (HyphenationEvent) this.attributes.get(HYPHENATION);
    }

    public Chunk setCharacterSpacing(float f) {
        return setAttribute(CHAR_SPACING, new Float(f));
    }

    public float getCharacterSpacing() {
        if (this.attributes == null || !this.attributes.containsKey(CHAR_SPACING)) {
            return 0.0f;
        }
        return ((Float) this.attributes.get(CHAR_SPACING)).floatValue();
    }

    public Chunk setWordSpacing(float f) {
        return setAttribute(WORD_SPACING, new Float(f));
    }

    public float getWordSpacing() {
        if (this.attributes == null || !this.attributes.containsKey(WORD_SPACING)) {
            return 0.0f;
        }
        return ((Float) this.attributes.get(WORD_SPACING)).floatValue();
    }

    public static Chunk createWhitespace(String str) {
        return createWhitespace(str, false);
    }

    public static Chunk createWhitespace(String str, boolean z) {
        if (z) {
            return new Chunk(str);
        }
        Chunk chunk = new Chunk(' ');
        chunk.setAttribute(WHITESPACE, str);
        return chunk;
    }

    public boolean isWhitespace() {
        return this.attributes != null && this.attributes.containsKey(WHITESPACE);
    }

    @Deprecated
    public static Chunk createTabspace() {
        return createTabspace(60.0f);
    }

    @Deprecated
    public static Chunk createTabspace(float f) {
        return new Chunk(Float.valueOf(f), true);
    }

    @Deprecated
    public boolean isTabspace() {
        return this.attributes != null && this.attributes.containsKey(TAB);
    }

    public PdfObject getAccessibleAttribute(PdfName pdfName) {
        if (getImage() != null) {
            return getImage().getAccessibleAttribute(pdfName);
        }
        if (this.accessibleAttributes != null) {
            return this.accessibleAttributes.get(pdfName);
        }
        return null;
    }

    public void setAccessibleAttribute(PdfName pdfName, PdfObject pdfObject) {
        if (getImage() != null) {
            getImage().setAccessibleAttribute(pdfName, pdfObject);
            return;
        }
        if (this.accessibleAttributes == null) {
            this.accessibleAttributes = new HashMap<>();
        }
        this.accessibleAttributes.put(pdfName, pdfObject);
    }

    public HashMap<PdfName, PdfObject> getAccessibleAttributes() {
        if (getImage() != null) {
            return getImage().getAccessibleAttributes();
        }
        return this.accessibleAttributes;
    }

    public PdfName getRole() {
        if (getImage() != null) {
            return getImage().getRole();
        }
        return this.role;
    }

    public void setRole(PdfName pdfName) {
        if (getImage() != null) {
            getImage().setRole(pdfName);
        } else {
            this.role = pdfName;
        }
    }

    public AccessibleElementId getId() {
        if (this.id == null) {
            this.id = new AccessibleElementId();
        }
        return this.id;
    }

    public void setId(AccessibleElementId accessibleElementId) {
        this.id = accessibleElementId;
    }

    public String getTextExpansion() {
        PdfObject accessibleAttribute = getAccessibleAttribute(PdfName.E);
        if (accessibleAttribute instanceof PdfString) {
            return ((PdfString) accessibleAttribute).toUnicodeString();
        }
        return null;
    }

    public void setTextExpansion(String str) {
        setAccessibleAttribute(PdfName.E, new PdfString(str));
    }
}
