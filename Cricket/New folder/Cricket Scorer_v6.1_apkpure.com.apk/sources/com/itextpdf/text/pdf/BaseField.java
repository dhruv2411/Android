package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class BaseField {
    public static final float BORDER_WIDTH_MEDIUM = 2.0f;
    public static final float BORDER_WIDTH_THICK = 3.0f;
    public static final float BORDER_WIDTH_THIN = 1.0f;
    public static final int COMB = 16777216;
    public static final int DO_NOT_SCROLL = 8388608;
    public static final int DO_NOT_SPELL_CHECK = 4194304;
    public static final int EDIT = 262144;
    public static final int FILE_SELECTION = 1048576;
    public static final int HIDDEN = 1;
    public static final int HIDDEN_BUT_PRINTABLE = 3;
    public static final int MULTILINE = 4096;
    public static final int MULTISELECT = 2097152;
    public static final int PASSWORD = 8192;
    public static final int READ_ONLY = 1;
    public static final int REQUIRED = 2;
    public static final int VISIBLE = 0;
    public static final int VISIBLE_BUT_DOES_NOT_PRINT = 2;
    private static final HashMap<PdfName, Integer> fieldKeys = new HashMap<>();
    protected int alignment = 0;
    protected BaseColor backgroundColor;
    protected BaseColor borderColor;
    protected int borderStyle = 0;
    protected float borderWidth = 1.0f;
    protected Rectangle box;
    protected String fieldName;
    protected BaseFont font;
    protected float fontSize = 0.0f;
    protected int maxCharacterLength;
    protected int options;
    protected int rotation = 0;
    protected String text;
    protected BaseColor textColor;
    protected int visibility;
    protected PdfWriter writer;

    static {
        fieldKeys.putAll(PdfCopyFieldsImp.fieldKeys);
        fieldKeys.put(PdfName.T, 1);
    }

    public BaseField(PdfWriter pdfWriter, Rectangle rectangle, String str) {
        this.writer = pdfWriter;
        setBox(rectangle);
        this.fieldName = str;
    }

    /* access modifiers changed from: protected */
    public BaseFont getRealFont() throws IOException, DocumentException {
        if (this.font == null) {
            return BaseFont.createFont("Helvetica", "Cp1252", false);
        }
        return this.font;
    }

    /* access modifiers changed from: protected */
    public PdfAppearance getBorderAppearance() {
        PdfAppearance createAppearance = PdfAppearance.createAppearance(this.writer, this.box.getWidth(), this.box.getHeight());
        int i = this.rotation;
        if (i == 90) {
            createAppearance.setMatrix(0.0f, 1.0f, -1.0f, 0.0f, this.box.getHeight(), 0.0f);
        } else if (i == 180) {
            createAppearance.setMatrix(-1.0f, 0.0f, 0.0f, -1.0f, this.box.getWidth(), this.box.getHeight());
        } else if (i == 270) {
            createAppearance.setMatrix(0.0f, -1.0f, 1.0f, 0.0f, 0.0f, this.box.getWidth());
        }
        createAppearance.saveState();
        if (this.backgroundColor != null) {
            createAppearance.setColorFill(this.backgroundColor);
            createAppearance.rectangle(0.0f, 0.0f, this.box.getWidth(), this.box.getHeight());
            createAppearance.fill();
        }
        if (this.borderStyle == 4) {
            if (!(this.borderWidth == 0.0f || this.borderColor == null)) {
                createAppearance.setColorStroke(this.borderColor);
                createAppearance.setLineWidth(this.borderWidth);
                createAppearance.moveTo(0.0f, this.borderWidth / 2.0f);
                createAppearance.lineTo(this.box.getWidth(), this.borderWidth / 2.0f);
                createAppearance.stroke();
            }
        } else if (this.borderStyle == 2) {
            if (!(this.borderWidth == 0.0f || this.borderColor == null)) {
                createAppearance.setColorStroke(this.borderColor);
                createAppearance.setLineWidth(this.borderWidth);
                createAppearance.rectangle(this.borderWidth / 2.0f, this.borderWidth / 2.0f, this.box.getWidth() - this.borderWidth, this.box.getHeight() - this.borderWidth);
                createAppearance.stroke();
            }
            BaseColor baseColor = this.backgroundColor;
            if (baseColor == null) {
                baseColor = BaseColor.WHITE;
            }
            createAppearance.setGrayFill(1.0f);
            drawTopFrame(createAppearance);
            createAppearance.setColorFill(baseColor.darker());
            drawBottomFrame(createAppearance);
        } else if (this.borderStyle == 3) {
            if (!(this.borderWidth == 0.0f || this.borderColor == null)) {
                createAppearance.setColorStroke(this.borderColor);
                createAppearance.setLineWidth(this.borderWidth);
                createAppearance.rectangle(this.borderWidth / 2.0f, this.borderWidth / 2.0f, this.box.getWidth() - this.borderWidth, this.box.getHeight() - this.borderWidth);
                createAppearance.stroke();
            }
            createAppearance.setGrayFill(0.5f);
            drawTopFrame(createAppearance);
            createAppearance.setGrayFill(0.75f);
            drawBottomFrame(createAppearance);
        } else if (!(this.borderWidth == 0.0f || this.borderColor == null)) {
            if (this.borderStyle == 1) {
                createAppearance.setLineDash(3.0f, 0.0f);
            }
            createAppearance.setColorStroke(this.borderColor);
            createAppearance.setLineWidth(this.borderWidth);
            createAppearance.rectangle(this.borderWidth / 2.0f, this.borderWidth / 2.0f, this.box.getWidth() - this.borderWidth, this.box.getHeight() - this.borderWidth);
            createAppearance.stroke();
            if ((this.options & 16777216) != 0 && this.maxCharacterLength > 1) {
                float width = this.box.getWidth() / ((float) this.maxCharacterLength);
                float f = this.borderWidth / 2.0f;
                float height = this.box.getHeight() - (this.borderWidth / 2.0f);
                for (int i2 = 1; i2 < this.maxCharacterLength; i2++) {
                    float f2 = ((float) i2) * width;
                    createAppearance.moveTo(f2, f);
                    createAppearance.lineTo(f2, height);
                }
                createAppearance.stroke();
            }
        }
        createAppearance.restoreState();
        return createAppearance;
    }

    protected static ArrayList<String> getHardBreaks(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        char[] charArray = str.toCharArray();
        int i = 0;
        int length = charArray.length;
        StringBuffer stringBuffer = new StringBuffer();
        while (i < length) {
            char c = charArray[i];
            if (c == 13) {
                int i2 = i + 1;
                if (i2 < length && charArray[i2] == 10) {
                    i = i2;
                }
                arrayList.add(stringBuffer.toString());
                stringBuffer = new StringBuffer();
            } else if (c == 10) {
                arrayList.add(stringBuffer.toString());
                stringBuffer = new StringBuffer();
            } else {
                stringBuffer.append(c);
            }
            i++;
        }
        arrayList.add(stringBuffer.toString());
        return arrayList;
    }

    protected static void trimRight(StringBuffer stringBuffer) {
        int length = stringBuffer.length();
        while (length != 0) {
            length--;
            if (stringBuffer.charAt(length) == ' ') {
                stringBuffer.setLength(length);
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0091, code lost:
        r16 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d1, code lost:
        r11 = r11 + 1;
        r4 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003d, code lost:
        r13 = 0.0f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.util.ArrayList<java.lang.String> breakLines(java.util.ArrayList<java.lang.String> r19, com.itextpdf.text.pdf.BaseFont r20, float r21, float r22) {
        /*
            r0 = r20
            r1 = r21
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r4 = 0
            r5 = r4
        L_0x0010:
            int r6 = r19.size()
            if (r5 >= r6) goto L_0x00e8
            r3.setLength(r4)
            r6 = r19
            java.lang.Object r7 = r6.get(r5)
            java.lang.String r7 = (java.lang.String) r7
            char[] r7 = r7.toCharArray()
            int r10 = r7.length
            r11 = r4
            r12 = r11
            r15 = r12
            r13 = 0
            r14 = -1
        L_0x002b:
            if (r11 >= r10) goto L_0x00d6
            char r8 = r7[r11]
            r16 = 2
            r9 = 32
            r4 = 1
            switch(r12) {
                case 0: goto L_0x0094;
                case 1: goto L_0x003f;
                case 2: goto L_0x0038;
                default: goto L_0x0037;
            }
        L_0x0037:
            goto L_0x0091
        L_0x0038:
            if (r8 == r9) goto L_0x0091
            int r11 = r11 + -1
            r12 = r4
        L_0x003d:
            r13 = 0
            goto L_0x0091
        L_0x003f:
            float r17 = r0.getWidthPoint((int) r8, (float) r1)
            float r13 = r13 + r17
            r3.append(r8)
            if (r8 != r9) goto L_0x004b
            r14 = r11
        L_0x004b:
            int r17 = (r13 > r22 ? 1 : (r13 == r22 ? 0 : -1))
            if (r17 <= 0) goto L_0x0091
            if (r14 < 0) goto L_0x006e
            int r8 = r14 - r15
            r3.setLength(r8)
            trimRight(r3)
            java.lang.String r8 = r3.toString()
            r2.add(r8)
            r8 = 0
            r3.setLength(r8)
            r11 = r14
            r15 = r11
            r12 = r16
            r13 = 0
            r14 = -1
            r16 = r8
            goto L_0x00d1
        L_0x006e:
            int r13 = r3.length()
            if (r13 <= r4) goto L_0x007e
            int r11 = r11 + -1
            int r13 = r3.length()
            int r13 = r13 - r4
            r3.setLength(r13)
        L_0x007e:
            java.lang.String r13 = r3.toString()
            r2.add(r13)
            r13 = 0
            r3.setLength(r13)
            if (r8 != r9) goto L_0x008f
            r15 = r11
            r12 = r16
            goto L_0x003d
        L_0x008f:
            r15 = r11
            goto L_0x003d
        L_0x0091:
            r16 = 0
            goto L_0x00d1
        L_0x0094:
            float r17 = r0.getWidthPoint((int) r8, (float) r1)
            float r13 = r13 + r17
            r3.append(r8)
            int r17 = (r13 > r22 ? 1 : (r13 == r22 ? 0 : -1))
            if (r17 <= 0) goto L_0x00cc
            int r12 = r3.length()
            if (r12 <= r4) goto L_0x00b1
            int r11 = r11 + -1
            int r12 = r3.length()
            int r12 = r12 - r4
            r3.setLength(r12)
        L_0x00b1:
            java.lang.String r12 = r3.toString()
            r2.add(r12)
            r12 = 0
            r3.setLength(r12)
            if (r8 != r9) goto L_0x00c7
            r15 = r11
            r13 = 0
            r18 = r16
            r16 = r12
            r12 = r18
            goto L_0x00d1
        L_0x00c7:
            r15 = r11
            r16 = r12
            r13 = 0
            goto L_0x00d0
        L_0x00cc:
            r16 = 0
            if (r8 == r9) goto L_0x00d1
        L_0x00d0:
            r12 = r4
        L_0x00d1:
            int r11 = r11 + r4
            r4 = r16
            goto L_0x002b
        L_0x00d6:
            r16 = r4
            trimRight(r3)
            java.lang.String r4 = r3.toString()
            r2.add(r4)
            int r5 = r5 + 1
            r4 = r16
            goto L_0x0010
        L_0x00e8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.BaseField.breakLines(java.util.ArrayList, com.itextpdf.text.pdf.BaseFont, float, float):java.util.ArrayList");
    }

    private void drawTopFrame(PdfAppearance pdfAppearance) {
        pdfAppearance.moveTo(this.borderWidth, this.borderWidth);
        pdfAppearance.lineTo(this.borderWidth, this.box.getHeight() - this.borderWidth);
        pdfAppearance.lineTo(this.box.getWidth() - this.borderWidth, this.box.getHeight() - this.borderWidth);
        pdfAppearance.lineTo(this.box.getWidth() - (this.borderWidth * 2.0f), this.box.getHeight() - (this.borderWidth * 2.0f));
        pdfAppearance.lineTo(this.borderWidth * 2.0f, this.box.getHeight() - (this.borderWidth * 2.0f));
        pdfAppearance.lineTo(this.borderWidth * 2.0f, 2.0f * this.borderWidth);
        pdfAppearance.lineTo(this.borderWidth, this.borderWidth);
        pdfAppearance.fill();
    }

    private void drawBottomFrame(PdfAppearance pdfAppearance) {
        pdfAppearance.moveTo(this.borderWidth, this.borderWidth);
        pdfAppearance.lineTo(this.box.getWidth() - this.borderWidth, this.borderWidth);
        pdfAppearance.lineTo(this.box.getWidth() - this.borderWidth, this.box.getHeight() - this.borderWidth);
        pdfAppearance.lineTo(this.box.getWidth() - (this.borderWidth * 2.0f), this.box.getHeight() - (this.borderWidth * 2.0f));
        pdfAppearance.lineTo(this.box.getWidth() - (this.borderWidth * 2.0f), this.borderWidth * 2.0f);
        pdfAppearance.lineTo(this.borderWidth * 2.0f, 2.0f * this.borderWidth);
        pdfAppearance.lineTo(this.borderWidth, this.borderWidth);
        pdfAppearance.fill();
    }

    public float getBorderWidth() {
        return this.borderWidth;
    }

    public void setBorderWidth(float f) {
        this.borderWidth = f;
    }

    public int getBorderStyle() {
        return this.borderStyle;
    }

    public void setBorderStyle(int i) {
        this.borderStyle = i;
    }

    public BaseColor getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(BaseColor baseColor) {
        this.borderColor = baseColor;
    }

    public BaseColor getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(BaseColor baseColor) {
        this.backgroundColor = baseColor;
    }

    public BaseColor getTextColor() {
        return this.textColor;
    }

    public void setTextColor(BaseColor baseColor) {
        this.textColor = baseColor;
    }

    public BaseFont getFont() {
        return this.font;
    }

    public void setFont(BaseFont baseFont) {
        this.font = baseFont;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(float f) {
        this.fontSize = f;
    }

    public int getAlignment() {
        return this.alignment;
    }

    public void setAlignment(int i) {
        this.alignment = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public Rectangle getBox() {
        return this.box;
    }

    public void setBox(Rectangle rectangle) {
        if (rectangle == null) {
            this.box = null;
            return;
        }
        this.box = new Rectangle(rectangle);
        this.box.normalize();
    }

    public int getRotation() {
        return this.rotation;
    }

    public void setRotation(int i) {
        if (i % 90 != 0) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("rotation.must.be.a.multiple.of.90", new Object[0]));
        }
        int i2 = i % 360;
        if (i2 < 0) {
            i2 += 360;
        }
        this.rotation = i2;
    }

    public void setRotationFromPage(Rectangle rectangle) {
        setRotation(rectangle.getRotation());
    }

    public int getVisibility() {
        return this.visibility;
    }

    public void setVisibility(int i) {
        this.visibility = i;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String str) {
        this.fieldName = str;
    }

    public int getOptions() {
        return this.options;
    }

    public void setOptions(int i) {
        this.options = i;
    }

    public int getMaxCharacterLength() {
        return this.maxCharacterLength;
    }

    public void setMaxCharacterLength(int i) {
        this.maxCharacterLength = i;
    }

    public PdfWriter getWriter() {
        return this.writer;
    }

    public void setWriter(PdfWriter pdfWriter) {
        this.writer = pdfWriter;
    }

    public static void moveFields(PdfDictionary pdfDictionary, PdfDictionary pdfDictionary2) {
        Iterator<PdfName> it = pdfDictionary.getKeys().iterator();
        while (it.hasNext()) {
            PdfName next = it.next();
            if (fieldKeys.containsKey(next)) {
                if (pdfDictionary2 != null) {
                    pdfDictionary2.put(next, pdfDictionary.get(next));
                }
                it.remove();
            }
        }
    }
}
