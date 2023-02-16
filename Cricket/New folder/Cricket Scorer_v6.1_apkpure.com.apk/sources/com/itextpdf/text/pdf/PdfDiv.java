package com.itextpdf.text.pdf;

import com.itextpdf.text.AccessibleElementId;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ElementListener;
import com.itextpdf.text.Image;
import com.itextpdf.text.api.Spaceable;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PdfDiv implements Element, Spaceable, IAccessibleElement {
    protected HashMap<PdfName, PdfObject> accessibleAttributes = null;
    private BaseColor backgroundColor = null;
    private Image backgroundImage;
    private Float backgroundImageHeight;
    private Float backgroundImageWidth;
    private BorderTopStyle borderTopStyle;
    private Float bottom = null;
    private ArrayList<Element> content = new ArrayList<>();
    private float contentHeight = 0.0f;
    private float contentWidth = 0.0f;
    private DisplayType display;
    private FloatLayout floatLayout = null;
    private FloatType floatType = FloatType.NONE;
    private Float height = null;
    protected AccessibleElementId id = new AccessibleElementId();
    private boolean keepTogether = false;
    private Float left = null;
    private float paddingBottom = 0.0f;
    private float paddingLeft = 0.0f;
    private float paddingRight = 0.0f;
    private float paddingTop = 0.0f;
    private Float percentageHeight = null;
    private Float percentageWidth = null;
    private PositionType position = PositionType.STATIC;
    private Float right = null;
    protected PdfName role = PdfName.DIV;
    protected int runDirection = 0;
    protected float spacingAfter;
    protected float spacingBefore;
    private int textAlignment = -1;
    private Float top = null;
    private Float width = null;
    private float yLine;

    public enum BorderTopStyle {
        DOTTED,
        DASHED,
        SOLID,
        DOUBLE,
        GROOVE,
        RIDGE,
        INSET,
        OUTSET
    }

    public enum DisplayType {
        NONE,
        BLOCK,
        INLINE,
        INLINE_BLOCK,
        INLINE_TABLE,
        LIST_ITEM,
        RUN_IN,
        TABLE,
        TABLE_CAPTION,
        TABLE_CELL,
        TABLE_COLUMN_GROUP,
        TABLE_COLUMN,
        TABLE_FOOTER_GROUP,
        TABLE_HEADER_GROUP,
        TABLE_ROW,
        TABLE_ROW_GROUP
    }

    public enum FloatType {
        NONE,
        LEFT,
        RIGHT
    }

    public enum PositionType {
        STATIC,
        ABSOLUTE,
        FIXED,
        RELATIVE
    }

    public boolean isContent() {
        return true;
    }

    public boolean isInline() {
        return false;
    }

    public boolean isNestable() {
        return true;
    }

    public int type() {
        return 37;
    }

    public float getContentWidth() {
        return this.contentWidth;
    }

    public void setContentWidth(float f) {
        this.contentWidth = f;
    }

    public float getContentHeight() {
        return this.contentHeight;
    }

    public void setContentHeight(float f) {
        this.contentHeight = f;
    }

    public float getActualHeight() {
        return (this.height == null || this.height.floatValue() < this.contentHeight) ? this.contentHeight : this.height.floatValue();
    }

    public float getActualWidth() {
        return (this.width == null || this.width.floatValue() < this.contentWidth) ? this.contentWidth : this.width.floatValue();
    }

    public Float getPercentageHeight() {
        return this.percentageHeight;
    }

    public void setPercentageHeight(Float f) {
        this.percentageHeight = f;
    }

    public Float getPercentageWidth() {
        return this.percentageWidth;
    }

    public void setPercentageWidth(Float f) {
        this.percentageWidth = f;
    }

    public DisplayType getDisplay() {
        return this.display;
    }

    public void setDisplay(DisplayType displayType) {
        this.display = displayType;
    }

    public BaseColor getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(BaseColor baseColor) {
        this.backgroundColor = baseColor;
    }

    public void setBackgroundImage(Image image) {
        this.backgroundImage = image;
    }

    public void setBackgroundImage(Image image, float f, float f2) {
        this.backgroundImage = image;
        this.backgroundImageWidth = Float.valueOf(f);
        this.backgroundImageHeight = Float.valueOf(f2);
    }

    public float getYLine() {
        return this.yLine;
    }

    public int getRunDirection() {
        return this.runDirection;
    }

    public void setRunDirection(int i) {
        this.runDirection = i;
    }

    public boolean getKeepTogether() {
        return this.keepTogether;
    }

    public void setKeepTogether(boolean z) {
        this.keepTogether = z;
    }

    public List<Chunk> getChunks() {
        return new ArrayList();
    }

    public boolean process(ElementListener elementListener) {
        try {
            return elementListener.add(this);
        } catch (DocumentException unused) {
            return false;
        }
    }

    public void setSpacingBefore(float f) {
        this.spacingBefore = f;
    }

    public void setSpacingAfter(float f) {
        this.spacingAfter = f;
    }

    public float getSpacingBefore() {
        return this.spacingBefore;
    }

    public float getSpacingAfter() {
        return this.spacingAfter;
    }

    public int getTextAlignment() {
        return this.textAlignment;
    }

    public void setTextAlignment(int i) {
        this.textAlignment = i;
    }

    public void addElement(Element element) {
        this.content.add(element);
    }

    public Float getLeft() {
        return this.left;
    }

    public void setLeft(Float f) {
        this.left = f;
    }

    public Float getRight() {
        return this.right;
    }

    public void setRight(Float f) {
        this.right = f;
    }

    public Float getTop() {
        return this.top;
    }

    public void setTop(Float f) {
        this.top = f;
    }

    public Float getBottom() {
        return this.bottom;
    }

    public void setBottom(Float f) {
        this.bottom = f;
    }

    public Float getWidth() {
        return this.width;
    }

    public void setWidth(Float f) {
        this.width = f;
    }

    public Float getHeight() {
        return this.height;
    }

    public void setHeight(Float f) {
        this.height = f;
    }

    public float getPaddingLeft() {
        return this.paddingLeft;
    }

    public void setPaddingLeft(float f) {
        this.paddingLeft = f;
    }

    public float getPaddingRight() {
        return this.paddingRight;
    }

    public void setPaddingRight(float f) {
        this.paddingRight = f;
    }

    public float getPaddingTop() {
        return this.paddingTop;
    }

    public void setPaddingTop(float f) {
        this.paddingTop = f;
    }

    public float getPaddingBottom() {
        return this.paddingBottom;
    }

    public void setPaddingBottom(float f) {
        this.paddingBottom = f;
    }

    public FloatType getFloatType() {
        return this.floatType;
    }

    public void setFloatType(FloatType floatType2) {
        this.floatType = floatType2;
    }

    public PositionType getPosition() {
        return this.position;
    }

    public void setPosition(PositionType positionType) {
        this.position = positionType;
    }

    public ArrayList<Element> getContent() {
        return this.content;
    }

    public void setContent(ArrayList<Element> arrayList) {
        this.content = arrayList;
    }

    public BorderTopStyle getBorderTopStyle() {
        return this.borderTopStyle;
    }

    public void setBorderTopStyle(BorderTopStyle borderTopStyle2) {
        this.borderTopStyle = borderTopStyle2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x01f8  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0268  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int layout(com.itextpdf.text.pdf.PdfContentByte r21, boolean r22, boolean r23, float r24, float r25, float r26, float r27) throws com.itextpdf.text.DocumentException {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r27
            float r7 = java.lang.Math.min(r3, r5)
            float r8 = java.lang.Math.max(r4, r6)
            float r4 = java.lang.Math.min(r4, r6)
            float r3 = java.lang.Math.max(r3, r5)
            r0.yLine = r8
            java.lang.Float r5 = r0.width
            r6 = 2
            r9 = 0
            if (r5 == 0) goto L_0x004f
            java.lang.Float r5 = r0.width
            float r5 = r5.floatValue()
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 <= 0) goto L_0x004f
            java.lang.Float r5 = r0.width
            float r5 = r5.floatValue()
            float r10 = r3 - r7
            int r5 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x0044
            java.lang.Float r3 = r0.width
            float r3 = r3.floatValue()
            float r3 = r3 + r7
            goto L_0x0085
        L_0x0044:
            java.lang.Float r5 = r0.width
            float r5 = r5.floatValue()
            int r5 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r5 <= 0) goto L_0x0085
            return r6
        L_0x004f:
            java.lang.Float r5 = r0.percentageWidth
            if (r5 == 0) goto L_0x0061
            float r3 = r3 - r7
            java.lang.Float r5 = r0.percentageWidth
            float r5 = r5.floatValue()
            float r3 = r3 * r5
            r0.contentWidth = r3
            float r3 = r0.contentWidth
            float r3 = r3 + r7
            goto L_0x0085
        L_0x0061:
            java.lang.Float r5 = r0.percentageWidth
            if (r5 != 0) goto L_0x0085
            com.itextpdf.text.pdf.PdfDiv$FloatType r5 = r0.floatType
            com.itextpdf.text.pdf.PdfDiv$FloatType r10 = com.itextpdf.text.pdf.PdfDiv.FloatType.NONE
            if (r5 != r10) goto L_0x0085
            com.itextpdf.text.pdf.PdfDiv$DisplayType r5 = r0.display
            if (r5 == 0) goto L_0x0081
            com.itextpdf.text.pdf.PdfDiv$DisplayType r5 = r0.display
            com.itextpdf.text.pdf.PdfDiv$DisplayType r10 = com.itextpdf.text.pdf.PdfDiv.DisplayType.BLOCK
            if (r5 == r10) goto L_0x0081
            com.itextpdf.text.pdf.PdfDiv$DisplayType r5 = r0.display
            com.itextpdf.text.pdf.PdfDiv$DisplayType r10 = com.itextpdf.text.pdf.PdfDiv.DisplayType.LIST_ITEM
            if (r5 == r10) goto L_0x0081
            com.itextpdf.text.pdf.PdfDiv$DisplayType r5 = r0.display
            com.itextpdf.text.pdf.PdfDiv$DisplayType r10 = com.itextpdf.text.pdf.PdfDiv.DisplayType.RUN_IN
            if (r5 != r10) goto L_0x0085
        L_0x0081:
            float r5 = r3 - r7
            r0.contentWidth = r5
        L_0x0085:
            java.lang.Float r5 = r0.height
            r10 = 0
            r11 = 1
            if (r5 == 0) goto L_0x00b6
            java.lang.Float r5 = r0.height
            float r5 = r5.floatValue()
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 <= 0) goto L_0x00b6
            java.lang.Float r5 = r0.height
            float r5 = r5.floatValue()
            float r12 = r8 - r4
            int r5 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r5 >= 0) goto L_0x00ab
            java.lang.Float r4 = r0.height
            float r4 = r4.floatValue()
            float r4 = r8 - r4
            r5 = r11
            goto L_0x00db
        L_0x00ab:
            java.lang.Float r5 = r0.height
            float r5 = r5.floatValue()
            int r5 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r5 <= 0) goto L_0x00da
            return r6
        L_0x00b6:
            java.lang.Float r5 = r0.percentageHeight
            if (r5 == 0) goto L_0x00da
            java.lang.Float r5 = r0.percentageHeight
            float r5 = r5.floatValue()
            double r5 = (double) r5
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r14 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x00c9
            r5 = r11
            goto L_0x00ca
        L_0x00c9:
            r5 = r10
        L_0x00ca:
            float r4 = r8 - r4
            java.lang.Float r6 = r0.percentageHeight
            float r6 = r6.floatValue()
            float r4 = r4 * r6
            r0.contentHeight = r4
            float r4 = r0.contentHeight
            float r4 = r8 - r4
            goto L_0x00db
        L_0x00da:
            r5 = r10
        L_0x00db:
            if (r2 != 0) goto L_0x013e
            com.itextpdf.text.pdf.PdfDiv$PositionType r6 = r0.position
            com.itextpdf.text.pdf.PdfDiv$PositionType r12 = com.itextpdf.text.pdf.PdfDiv.PositionType.RELATIVE
            if (r6 != r12) goto L_0x013e
            java.lang.Float r6 = r0.left
            if (r6 == 0) goto L_0x00ea
            java.lang.Float r6 = r0.left
            goto L_0x00fe
        L_0x00ea:
            java.lang.Float r6 = r0.right
            if (r6 == 0) goto L_0x00fa
            java.lang.Float r6 = r0.right
            float r6 = r6.floatValue()
            float r6 = -r6
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            goto L_0x00fe
        L_0x00fa:
            java.lang.Float r6 = java.lang.Float.valueOf(r9)
        L_0x00fe:
            java.lang.Float r12 = r0.top
            if (r12 == 0) goto L_0x010e
            java.lang.Float r12 = r0.top
            float r12 = r12.floatValue()
            float r12 = -r12
            java.lang.Float r12 = java.lang.Float.valueOf(r12)
            goto L_0x0119
        L_0x010e:
            java.lang.Float r12 = r0.bottom
            if (r12 == 0) goto L_0x0115
            java.lang.Float r12 = r0.bottom
            goto L_0x0119
        L_0x0115:
            java.lang.Float r12 = java.lang.Float.valueOf(r9)
        L_0x0119:
            r21.saveState()
            com.itextpdf.awt.geom.AffineTransform r15 = new com.itextpdf.awt.geom.AffineTransform
            r14 = 1065353216(0x3f800000, float:1.0)
            r16 = 0
            r17 = 0
            r18 = 1065353216(0x3f800000, float:1.0)
            float r6 = r6.floatValue()
            float r19 = r12.floatValue()
            r13 = r15
            r12 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            r18 = r6
            r13.<init>((float) r14, (float) r15, (float) r16, (float) r17, (float) r18, (float) r19)
            r1.transform(r12)
        L_0x013e:
            if (r2 != 0) goto L_0x01ee
            com.itextpdf.text.BaseColor r6 = r0.backgroundColor
            if (r6 != 0) goto L_0x0148
            com.itextpdf.text.Image r6 = r0.backgroundImage
            if (r6 == 0) goto L_0x01ee
        L_0x0148:
            float r6 = r20.getActualWidth()
            int r6 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x01ee
            float r6 = r20.getActualHeight()
            int r6 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x01ee
            float r6 = r20.getActualWidth()
            float r12 = r20.getActualHeight()
            java.lang.Float r13 = r0.width
            if (r13 == 0) goto L_0x0176
            java.lang.Float r6 = r0.width
            float r6 = r6.floatValue()
            int r6 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x0175
            java.lang.Float r6 = r0.width
            float r6 = r6.floatValue()
            goto L_0x0176
        L_0x0175:
            r6 = r9
        L_0x0176:
            java.lang.Float r13 = r0.height
            if (r13 == 0) goto L_0x018c
            java.lang.Float r12 = r0.height
            float r12 = r12.floatValue()
            int r12 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
            if (r12 <= 0) goto L_0x018b
            java.lang.Float r12 = r0.height
            float r12 = r12.floatValue()
            goto L_0x018c
        L_0x018b:
            r12 = r9
        L_0x018c:
            int r13 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r13 <= 0) goto L_0x01ee
            int r13 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
            if (r13 <= 0) goto L_0x01ee
            com.itextpdf.text.Rectangle r13 = new com.itextpdf.text.Rectangle
            float r12 = r8 - r12
            float r6 = r6 + r7
            r13.<init>(r7, r12, r6, r8)
            com.itextpdf.text.BaseColor r6 = r0.backgroundColor
            if (r6 == 0) goto L_0x01b3
            com.itextpdf.text.BaseColor r6 = r0.backgroundColor
            r13.setBackgroundColor(r6)
            com.itextpdf.text.pdf.PdfArtifact r6 = new com.itextpdf.text.pdf.PdfArtifact
            r6.<init>()
            r1.openMCBlock(r6)
            r1.rectangle(r13)
            r1.closeMCBlock(r6)
        L_0x01b3:
            com.itextpdf.text.Image r6 = r0.backgroundImage
            if (r6 == 0) goto L_0x01ee
            java.lang.Float r6 = r0.backgroundImageWidth
            if (r6 != 0) goto L_0x01c1
            com.itextpdf.text.Image r6 = r0.backgroundImage
            r6.scaleToFit(r13)
            goto L_0x01d2
        L_0x01c1:
            com.itextpdf.text.Image r6 = r0.backgroundImage
            java.lang.Float r12 = r0.backgroundImageWidth
            float r12 = r12.floatValue()
            java.lang.Float r14 = r0.backgroundImageHeight
            float r14 = r14.floatValue()
            r6.scaleAbsolute(r12, r14)
        L_0x01d2:
            com.itextpdf.text.Image r6 = r0.backgroundImage
            float r12 = r13.getLeft()
            float r13 = r13.getBottom()
            r6.setAbsolutePosition(r12, r13)
            com.itextpdf.text.Image r6 = r0.backgroundImage
            r1.openMCBlock(r6)
            com.itextpdf.text.Image r6 = r0.backgroundImage
            r1.addImage(r6)
            com.itextpdf.text.Image r6 = r0.backgroundImage
            r1.closeMCBlock(r6)
        L_0x01ee:
            java.lang.Float r6 = r0.percentageWidth
            if (r6 != 0) goto L_0x01f4
            r0.contentWidth = r9
        L_0x01f4:
            java.lang.Float r6 = r0.percentageHeight
            if (r6 != 0) goto L_0x01fa
            r0.contentHeight = r9
        L_0x01fa:
            float r6 = r0.paddingBottom
            float r4 = r4 + r6
            float r6 = r0.paddingLeft
            float r7 = r7 + r6
            float r6 = r0.paddingRight
            float r3 = r3 - r6
            float r6 = r0.yLine
            float r9 = r0.paddingTop
            float r6 = r6 - r9
            r0.yLine = r6
            java.util.ArrayList<com.itextpdf.text.Element> r6 = r0.content
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L_0x0268
            com.itextpdf.text.pdf.FloatLayout r6 = r0.floatLayout
            if (r6 != 0) goto L_0x022d
            java.util.ArrayList r6 = new java.util.ArrayList
            java.util.ArrayList<com.itextpdf.text.Element> r9 = r0.content
            r6.<init>(r9)
            com.itextpdf.text.pdf.FloatLayout r9 = new com.itextpdf.text.pdf.FloatLayout
            r12 = r22
            r9.<init>(r6, r12)
            r0.floatLayout = r9
            com.itextpdf.text.pdf.FloatLayout r6 = r0.floatLayout
            int r9 = r0.runDirection
            r6.setRunDirection(r9)
        L_0x022d:
            com.itextpdf.text.pdf.FloatLayout r6 = r0.floatLayout
            float r9 = r0.yLine
            r6.setSimpleColumn(r7, r4, r3, r9)
            com.itextpdf.text.pdf.PdfDiv$BorderTopStyle r3 = r20.getBorderTopStyle()
            if (r3 == 0) goto L_0x0241
            com.itextpdf.text.pdf.FloatLayout r3 = r0.floatLayout
            com.itextpdf.text.pdf.ColumnText r3 = r3.compositeColumn
            r3.setIgnoreSpacingBefore(r10)
        L_0x0241:
            com.itextpdf.text.pdf.FloatLayout r3 = r0.floatLayout
            int r3 = r3.layout(r1, r2)
            com.itextpdf.text.pdf.FloatLayout r4 = r0.floatLayout
            float r4 = r4.getYLine()
            r0.yLine = r4
            java.lang.Float r4 = r0.percentageWidth
            if (r4 != 0) goto L_0x0269
            float r4 = r0.contentWidth
            com.itextpdf.text.pdf.FloatLayout r6 = r0.floatLayout
            float r6 = r6.getFilledWidth()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x0269
            com.itextpdf.text.pdf.FloatLayout r4 = r0.floatLayout
            float r4 = r4.getFilledWidth()
            r0.contentWidth = r4
            goto L_0x0269
        L_0x0268:
            r3 = r11
        L_0x0269:
            if (r2 != 0) goto L_0x0274
            com.itextpdf.text.pdf.PdfDiv$PositionType r2 = r0.position
            com.itextpdf.text.pdf.PdfDiv$PositionType r4 = com.itextpdf.text.pdf.PdfDiv.PositionType.RELATIVE
            if (r2 != r4) goto L_0x0274
            r21.restoreState()
        L_0x0274:
            float r1 = r0.yLine
            float r2 = r0.paddingBottom
            float r1 = r1 - r2
            r0.yLine = r1
            java.lang.Float r1 = r0.percentageHeight
            if (r1 != 0) goto L_0x0284
            float r1 = r0.yLine
            float r8 = r8 - r1
            r0.contentHeight = r8
        L_0x0284:
            java.lang.Float r1 = r0.percentageWidth
            if (r1 != 0) goto L_0x0292
            float r1 = r0.contentWidth
            float r2 = r0.paddingLeft
            float r4 = r0.paddingRight
            float r2 = r2 + r4
            float r1 = r1 + r2
            r0.contentWidth = r1
        L_0x0292:
            if (r5 == 0) goto L_0x0295
            goto L_0x0296
        L_0x0295:
            r11 = r3
        L_0x0296:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfDiv.layout(com.itextpdf.text.pdf.PdfContentByte, boolean, boolean, float, float, float, float):int");
    }

    public PdfObject getAccessibleAttribute(PdfName pdfName) {
        if (this.accessibleAttributes != null) {
            return this.accessibleAttributes.get(pdfName);
        }
        return null;
    }

    public void setAccessibleAttribute(PdfName pdfName, PdfObject pdfObject) {
        if (this.accessibleAttributes == null) {
            this.accessibleAttributes = new HashMap<>();
        }
        this.accessibleAttributes.put(pdfName, pdfObject);
    }

    public HashMap<PdfName, PdfObject> getAccessibleAttributes() {
        return this.accessibleAttributes;
    }

    public PdfName getRole() {
        return this.role;
    }

    public void setRole(PdfName pdfName) {
        this.role = pdfName;
    }

    public AccessibleElementId getId() {
        return this.id;
    }

    public void setId(AccessibleElementId accessibleElementId) {
        this.id = accessibleElementId;
    }
}
