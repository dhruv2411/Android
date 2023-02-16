package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TextField extends BaseField {
    private String[] choiceExports;
    private ArrayList<Integer> choiceSelections = new ArrayList<>();
    private String[] choices;
    private String defaultText;
    private BaseFont extensionFont;
    private float extraMarginLeft;
    private float extraMarginTop;
    private ArrayList<BaseFont> substitutionFonts;
    private int topFirst;
    private int visibleTopChoice = -1;

    public TextField(PdfWriter pdfWriter, Rectangle rectangle, String str) {
        super(pdfWriter, rectangle, str);
    }

    private static boolean checkRTL(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (c >= 1424 && c < 1920) {
                return true;
            }
        }
        return false;
    }

    private static void changeFontSize(Phrase phrase, float f) {
        for (int i = 0; i < phrase.size(); i++) {
            ((Chunk) phrase.get(i)).getFont().setSize(f);
        }
    }

    private Phrase composePhrase(String str, BaseFont baseFont, BaseColor baseColor, float f) {
        if (this.extensionFont == null && (this.substitutionFonts == null || this.substitutionFonts.isEmpty())) {
            return new Phrase(new Chunk(str, new Font(baseFont, f, 0, baseColor)));
        }
        FontSelector fontSelector = new FontSelector();
        fontSelector.addFont(new Font(baseFont, f, 0, baseColor));
        if (this.extensionFont != null) {
            fontSelector.addFont(new Font(this.extensionFont, f, 0, baseColor));
        }
        if (this.substitutionFonts != null) {
            for (int i = 0; i < this.substitutionFonts.size(); i++) {
                fontSelector.addFont(new Font(this.substitutionFonts.get(i), f, 0, baseColor));
            }
        }
        return fontSelector.process(str);
    }

    public static String removeCRLF(String str) {
        if (str.indexOf(10) < 0 && str.indexOf(13) < 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer(charArray.length);
        int i = 0;
        while (i < charArray.length) {
            char c = charArray[i];
            if (c == 10) {
                stringBuffer.append(' ');
            } else if (c == 13) {
                stringBuffer.append(' ');
                if (i < charArray.length - 1) {
                    int i2 = i + 1;
                    if (charArray[i2] == 10) {
                        i = i2;
                    }
                }
            } else {
                stringBuffer.append(c);
            }
            i++;
        }
        return stringBuffer.toString();
    }

    public static String obfuscatePassword(String str) {
        char[] cArr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            cArr[i] = '*';
        }
        return new String(cArr);
    }

    public PdfAppearance getAppearance() throws IOException, DocumentException {
        String str;
        int i;
        float width;
        int i2;
        float min;
        PdfAppearance borderAppearance = getBorderAppearance();
        borderAppearance.beginVariableText();
        if (this.text == null || this.text.length() == 0) {
            borderAppearance.endVariableText();
            return borderAppearance;
        }
        boolean z = this.borderStyle == 2 || this.borderStyle == 3;
        float height = (this.box.getHeight() - (this.borderWidth * 2.0f)) - this.extraMarginTop;
        float f = this.borderWidth;
        if (z) {
            height -= this.borderWidth * 2.0f;
            f *= 2.0f;
        }
        float max = Math.max(f, 1.0f);
        float min2 = Math.min(f, max);
        borderAppearance.saveState();
        float f2 = 2.0f * min2;
        borderAppearance.rectangle(min2, min2, this.box.getWidth() - f2, this.box.getHeight() - f2);
        borderAppearance.clip();
        borderAppearance.newPath();
        if ((this.options & 8192) != 0) {
            str = obfuscatePassword(this.text);
        } else if ((this.options & 4096) == 0) {
            str = removeCRLF(this.text);
        } else {
            str = this.text;
        }
        BaseFont realFont = getRealFont();
        BaseColor baseColor = this.textColor == null ? GrayColor.GRAYBLACK : this.textColor;
        int i3 = checkRTL(str) ? 2 : 1;
        float f3 = this.fontSize;
        Phrase composePhrase = composePhrase(str, realFont, baseColor, f3);
        if ((this.options & 4096) != 0) {
            float width2 = (this.box.getWidth() - (4.0f * max)) - this.extraMarginLeft;
            float fontDescriptor = realFont.getFontDescriptor(8, 1.0f) - realFont.getFontDescriptor(6, 1.0f);
            ColumnText columnText = new ColumnText((PdfContentByte) null);
            if (f3 == 0.0f) {
                f3 = height / fontDescriptor;
                if (f3 > 4.0f) {
                    if (f3 > 12.0f) {
                        f3 = 12.0f;
                    }
                    float max2 = Math.max((f3 - 4.0f) / 10.0f, 0.2f);
                    columnText.setSimpleColumn(0.0f, -height, width2, 0.0f);
                    columnText.setAlignment(this.alignment);
                    columnText.setRunDirection(i3);
                    while (f3 > 4.0f) {
                        columnText.setYLine(0.0f);
                        changeFontSize(composePhrase, f3);
                        columnText.setText(composePhrase);
                        columnText.setLeading(fontDescriptor * f3);
                        if ((columnText.go(true) & 2) == 0) {
                            break;
                        }
                        f3 -= max2;
                    }
                }
                if (f3 < 4.0f) {
                    f3 = 4.0f;
                }
            }
            changeFontSize(composePhrase, f3);
            columnText.setCanvas(borderAppearance);
            float f4 = fontDescriptor * f3;
            float f5 = 2.0f * max;
            columnText.setSimpleColumn(this.extraMarginLeft + f5, -20000.0f, this.box.getWidth() - f5, ((height + max) - realFont.getFontDescriptor(8, f3)) + f4);
            columnText.setLeading(f4);
            columnText.setAlignment(this.alignment);
            columnText.setRunDirection(i3);
            columnText.setText(composePhrase);
            columnText.go();
        } else {
            if (f3 == 0.0f) {
                float fontDescriptor2 = height / (realFont.getFontDescriptor(7, 1.0f) - realFont.getFontDescriptor(6, 1.0f));
                changeFontSize(composePhrase, 1.0f);
                i = 0;
                float width3 = ColumnText.getWidth(composePhrase, i3, 0);
                if (width3 == 0.0f) {
                    min = fontDescriptor2;
                } else {
                    min = Math.min(fontDescriptor2, ((this.box.getWidth() - this.extraMarginLeft) - (4.0f * max)) / width3);
                }
                if (f3 < 4.0f) {
                    f3 = 4.0f;
                }
            } else {
                i = 0;
            }
            changeFontSize(composePhrase, f3);
            float height2 = (((this.box.getHeight() - f2) - realFont.getFontDescriptor(1, f3)) / 2.0f) + min2;
            if (height2 < min2) {
                height2 = min2;
            }
            if (height2 - min2 < (-realFont.getFontDescriptor(3, f3))) {
                height2 = Math.min((-realFont.getFontDescriptor(3, f3)) + min2, Math.max(height2, (this.box.getHeight() - min2) - realFont.getFontDescriptor(1, f3)));
            }
            if ((this.options & 16777216) == 0 || this.maxCharacterLength <= 0) {
                switch (this.alignment) {
                    case 1:
                        width = this.extraMarginLeft + (this.box.getWidth() / 2.0f);
                        break;
                    case 2:
                        width = (this.extraMarginLeft + this.box.getWidth()) - (2.0f * max);
                        break;
                    default:
                        width = this.extraMarginLeft + (2.0f * max);
                        break;
                }
                ColumnText.showTextAligned(borderAppearance, this.alignment, composePhrase, width, height2 - this.extraMarginTop, 0.0f, i3, 0);
            } else {
                int min3 = Math.min(this.maxCharacterLength, str.length());
                if (this.alignment == 2) {
                    i2 = this.maxCharacterLength - min3;
                } else {
                    i2 = this.alignment == 1 ? (this.maxCharacterLength - min3) / 2 : i;
                }
                float width4 = (this.box.getWidth() - this.extraMarginLeft) / ((float) this.maxCharacterLength);
                float f6 = (width4 / 2.0f) + (((float) i2) * width4);
                if (this.textColor == null) {
                    borderAppearance.setGrayFill(0.0f);
                } else {
                    borderAppearance.setColorFill(this.textColor);
                }
                borderAppearance.beginText();
                int i4 = i;
                while (i4 < composePhrase.size()) {
                    Chunk chunk = (Chunk) composePhrase.get(i4);
                    BaseFont baseFont = chunk.getFont().getBaseFont();
                    borderAppearance.setFontAndSize(baseFont, f3);
                    StringBuffer append = chunk.append("");
                    float f7 = f6;
                    int i5 = i;
                    while (i5 < append.length()) {
                        int i6 = i5 + 1;
                        String substring = append.substring(i5, i6);
                        borderAppearance.setTextMatrix((this.extraMarginLeft + f7) - (baseFont.getWidthPoint(substring, f3) / 2.0f), height2 - this.extraMarginTop);
                        borderAppearance.showText(substring);
                        f7 += width4;
                        i5 = i6;
                    }
                    i4++;
                    f6 = f7;
                }
                borderAppearance.endText();
            }
        }
        borderAppearance.restoreState();
        borderAppearance.endVariableText();
        return borderAppearance;
    }

    /* access modifiers changed from: package-private */
    public PdfAppearance getListAppearance() throws IOException, DocumentException {
        PdfAppearance borderAppearance = getBorderAppearance();
        if (this.choices == null || this.choices.length == 0) {
            return borderAppearance;
        }
        borderAppearance.beginVariableText();
        int topChoice = getTopChoice();
        BaseFont realFont = getRealFont();
        float f = this.fontSize;
        if (f == 0.0f) {
            f = 12.0f;
        }
        float f2 = f;
        boolean z = this.borderStyle == 2 || this.borderStyle == 3;
        float height = this.box.getHeight() - (this.borderWidth * 2.0f);
        float f3 = this.borderWidth;
        if (z) {
            height -= this.borderWidth * 2.0f;
            f3 *= 2.0f;
        }
        float fontDescriptor = realFont.getFontDescriptor(8, f2) - realFont.getFontDescriptor(6, f2);
        int i = ((int) (height / fontDescriptor)) + 1 + topChoice;
        if (i > this.choices.length) {
            i = this.choices.length;
        }
        int i2 = i;
        this.topFirst = topChoice;
        borderAppearance.saveState();
        float f4 = 2.0f * f3;
        borderAppearance.rectangle(f3, f3, this.box.getWidth() - f4, this.box.getHeight() - f4);
        borderAppearance.clip();
        borderAppearance.newPath();
        BaseColor baseColor = this.textColor == null ? GrayColor.GRAYBLACK : this.textColor;
        borderAppearance.setColorFill(new BaseColor(10, 36, 106));
        for (int i3 = 0; i3 < this.choiceSelections.size(); i3++) {
            int intValue = this.choiceSelections.get(i3).intValue();
            if (intValue >= topChoice && intValue <= i2) {
                borderAppearance.rectangle(f3, (f3 + height) - (((float) ((intValue - topChoice) + 1)) * fontDescriptor), this.box.getWidth() - f4, fontDescriptor);
                borderAppearance.fill();
            }
        }
        int i4 = topChoice;
        float fontDescriptor2 = (f3 + height) - realFont.getFontDescriptor(8, f2);
        while (i4 < i2) {
            String str = this.choices[i4];
            ColumnText.showTextAligned(borderAppearance, 0, composePhrase(removeCRLF(str), realFont, this.choiceSelections.contains(Integer.valueOf(i4)) ? GrayColor.GRAYWHITE : baseColor, f2), f4, fontDescriptor2, 0.0f, checkRTL(str) ? 2 : 1, 0);
            i4++;
            fontDescriptor2 -= fontDescriptor;
        }
        borderAppearance.restoreState();
        borderAppearance.endVariableText();
        return borderAppearance;
    }

    public PdfFormField getTextField() throws IOException, DocumentException {
        if (this.maxCharacterLength <= 0) {
            this.options &= -16777217;
        }
        if ((this.options & 16777216) != 0) {
            this.options &= -4097;
        }
        PdfFormField createTextField = PdfFormField.createTextField(this.writer, false, false, this.maxCharacterLength);
        createTextField.setWidget(this.box, PdfAnnotation.HIGHLIGHT_INVERT);
        switch (this.alignment) {
            case 1:
                createTextField.setQuadding(1);
                break;
            case 2:
                createTextField.setQuadding(2);
                break;
        }
        if (this.rotation != 0) {
            createTextField.setMKRotation(this.rotation);
        }
        if (this.fieldName != null) {
            createTextField.setFieldName(this.fieldName);
            if (!"".equals(this.text)) {
                createTextField.setValueAsString(this.text);
            }
            if (this.defaultText != null) {
                createTextField.setDefaultValueAsString(this.defaultText);
            }
            if ((this.options & 1) != 0) {
                createTextField.setFieldFlags(1);
            }
            if ((this.options & 2) != 0) {
                createTextField.setFieldFlags(2);
            }
            if ((this.options & 4096) != 0) {
                createTextField.setFieldFlags(4096);
            }
            if ((this.options & 8388608) != 0) {
                createTextField.setFieldFlags(8388608);
            }
            if ((this.options & 8192) != 0) {
                createTextField.setFieldFlags(8192);
            }
            if ((this.options & 1048576) != 0) {
                createTextField.setFieldFlags(1048576);
            }
            if ((this.options & 4194304) != 0) {
                createTextField.setFieldFlags(4194304);
            }
            if ((this.options & 16777216) != 0) {
                createTextField.setFieldFlags(16777216);
            }
        }
        createTextField.setBorderStyle(new PdfBorderDictionary(this.borderWidth, this.borderStyle, new PdfDashPattern(3.0f)));
        PdfAppearance appearance = getAppearance();
        createTextField.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, appearance);
        PdfAppearance pdfAppearance = (PdfAppearance) appearance.getDuplicate();
        pdfAppearance.setFontAndSize(getRealFont(), this.fontSize);
        if (this.textColor == null) {
            pdfAppearance.setGrayFill(0.0f);
        } else {
            pdfAppearance.setColorFill(this.textColor);
        }
        createTextField.setDefaultAppearanceString(pdfAppearance);
        if (this.borderColor != null) {
            createTextField.setMKBorderColor(this.borderColor);
        }
        if (this.backgroundColor != null) {
            createTextField.setMKBackgroundColor(this.backgroundColor);
        }
        switch (this.visibility) {
            case 1:
                createTextField.setFlags(6);
                break;
            case 2:
                break;
            case 3:
                createTextField.setFlags(36);
                break;
            default:
                createTextField.setFlags(4);
                break;
        }
        return createTextField;
    }

    public PdfFormField getComboField() throws IOException, DocumentException {
        return getChoiceField(false);
    }

    public PdfFormField getListField() throws IOException, DocumentException {
        return getChoiceField(true);
    }

    private int getTopChoice() {
        Integer num;
        if (this.choiceSelections == null || this.choiceSelections.size() == 0 || (num = this.choiceSelections.get(0)) == null || this.choices == null) {
            return 0;
        }
        if (this.visibleTopChoice != -1) {
            return this.visibleTopChoice;
        }
        return Math.max(0, Math.min(num.intValue(), this.choices.length));
    }

    /* access modifiers changed from: protected */
    public PdfFormField getChoiceField(boolean z) throws IOException, DocumentException {
        String[][] strArr;
        PdfFormField pdfFormField;
        PdfAppearance pdfAppearance;
        PdfFormField createCombo;
        this.options &= -16781313;
        String[] strArr2 = this.choices;
        if (strArr2 == null) {
            strArr2 = new String[0];
        }
        int topChoice = getTopChoice();
        if (strArr2.length > 0 && topChoice >= 0) {
            this.text = strArr2[topChoice];
        }
        if (this.text == null) {
            this.text = "";
        }
        String[][] strArr3 = null;
        if (this.choiceExports == null) {
            if (z) {
                pdfFormField = PdfFormField.createList(this.writer, strArr2, topChoice);
            } else {
                pdfFormField = PdfFormField.createCombo(this.writer, (262144 & this.options) != 0, strArr2, topChoice);
            }
            strArr = strArr3;
        } else {
            String[][] strArr4 = (String[][]) Array.newInstance(String.class, new int[]{strArr2.length, 2});
            for (int i = 0; i < strArr4.length; i++) {
                String[] strArr5 = strArr4[i];
                String[] strArr6 = strArr4[i];
                String str = strArr2[i];
                strArr6[1] = str;
                strArr5[0] = str;
            }
            int min = Math.min(strArr2.length, this.choiceExports.length);
            for (int i2 = 0; i2 < min; i2++) {
                if (this.choiceExports[i2] != null) {
                    strArr4[i2][0] = this.choiceExports[i2];
                }
            }
            if (z) {
                createCombo = PdfFormField.createList(this.writer, strArr4, topChoice);
            } else {
                createCombo = PdfFormField.createCombo(this.writer, (262144 & this.options) != 0, strArr4, topChoice);
            }
            PdfFormField pdfFormField2 = createCombo;
            strArr = strArr4;
            pdfFormField = pdfFormField2;
        }
        pdfFormField.setWidget(this.box, PdfAnnotation.HIGHLIGHT_INVERT);
        if (this.rotation != 0) {
            pdfFormField.setMKRotation(this.rotation);
        }
        if (this.fieldName != null) {
            pdfFormField.setFieldName(this.fieldName);
            if (strArr2.length > 0) {
                if (strArr != null) {
                    if (this.choiceSelections.size() < 2) {
                        pdfFormField.setValueAsString(strArr[topChoice][0]);
                        pdfFormField.setDefaultValueAsString(strArr[topChoice][0]);
                    } else {
                        writeMultipleValues(pdfFormField, strArr);
                    }
                } else if (this.choiceSelections.size() < 2) {
                    pdfFormField.setValueAsString(this.text);
                    pdfFormField.setDefaultValueAsString(this.text);
                } else {
                    writeMultipleValues(pdfFormField, strArr3);
                }
            }
            if ((this.options & 1) != 0) {
                pdfFormField.setFieldFlags(1);
            }
            if ((this.options & 2) != 0) {
                pdfFormField.setFieldFlags(2);
            }
            if ((this.options & 4194304) != 0) {
                pdfFormField.setFieldFlags(4194304);
            }
            if ((this.options & 2097152) != 0) {
                pdfFormField.setFieldFlags(2097152);
            }
        }
        pdfFormField.setBorderStyle(new PdfBorderDictionary(this.borderWidth, this.borderStyle, new PdfDashPattern(3.0f)));
        if (z) {
            pdfAppearance = getListAppearance();
            if (this.topFirst > 0) {
                pdfFormField.put(PdfName.TI, new PdfNumber(this.topFirst));
            }
        } else {
            pdfAppearance = getAppearance();
        }
        pdfFormField.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, pdfAppearance);
        PdfAppearance pdfAppearance2 = (PdfAppearance) pdfAppearance.getDuplicate();
        pdfAppearance2.setFontAndSize(getRealFont(), this.fontSize);
        if (this.textColor == null) {
            pdfAppearance2.setGrayFill(0.0f);
        } else {
            pdfAppearance2.setColorFill(this.textColor);
        }
        pdfFormField.setDefaultAppearanceString(pdfAppearance2);
        if (this.borderColor != null) {
            pdfFormField.setMKBorderColor(this.borderColor);
        }
        if (this.backgroundColor != null) {
            pdfFormField.setMKBackgroundColor(this.backgroundColor);
        }
        switch (this.visibility) {
            case 1:
                pdfFormField.setFlags(6);
                break;
            case 2:
                break;
            case 3:
                pdfFormField.setFlags(36);
                break;
            default:
                pdfFormField.setFlags(4);
                break;
        }
        return pdfFormField;
    }

    private void writeMultipleValues(PdfFormField pdfFormField, String[][] strArr) {
        PdfArray pdfArray = new PdfArray();
        PdfArray pdfArray2 = new PdfArray();
        for (int i = 0; i < this.choiceSelections.size(); i++) {
            int intValue = this.choiceSelections.get(i).intValue();
            pdfArray.add((PdfObject) new PdfNumber(intValue));
            if (strArr != null) {
                pdfArray2.add((PdfObject) new PdfString(strArr[intValue][0]));
            } else if (this.choices != null) {
                pdfArray2.add((PdfObject) new PdfString(this.choices[intValue]));
            }
        }
        pdfFormField.put(PdfName.V, pdfArray2);
        pdfFormField.put(PdfName.I, pdfArray);
    }

    public String getDefaultText() {
        return this.defaultText;
    }

    public void setDefaultText(String str) {
        this.defaultText = str;
    }

    public String[] getChoices() {
        return this.choices;
    }

    public void setChoices(String[] strArr) {
        this.choices = strArr;
    }

    public String[] getChoiceExports() {
        return this.choiceExports;
    }

    public void setChoiceExports(String[] strArr) {
        this.choiceExports = strArr;
    }

    public int getChoiceSelection() {
        return getTopChoice();
    }

    public ArrayList<Integer> getChoiceSelections() {
        return this.choiceSelections;
    }

    public void setVisibleTopChoice(int i) {
        if (i >= 0 && this.choices != null && i < this.choices.length) {
            this.visibleTopChoice = i;
        }
    }

    public int getVisibleTopChoice() {
        return this.visibleTopChoice;
    }

    public void setChoiceSelection(int i) {
        this.choiceSelections = new ArrayList<>();
        this.choiceSelections.add(Integer.valueOf(i));
    }

    public void addChoiceSelection(int i) {
        if ((this.options & 2097152) != 0) {
            this.choiceSelections.add(Integer.valueOf(i));
        }
    }

    public void setChoiceSelections(ArrayList<Integer> arrayList) {
        if (arrayList != null) {
            this.choiceSelections = new ArrayList<>(arrayList);
            if (this.choiceSelections.size() > 1 && (this.options & 2097152) == 0) {
                while (this.choiceSelections.size() > 1) {
                    this.choiceSelections.remove(1);
                }
                return;
            }
            return;
        }
        this.choiceSelections.clear();
    }

    /* access modifiers changed from: package-private */
    public int getTopFirst() {
        return this.topFirst;
    }

    public void setExtraMargin(float f, float f2) {
        this.extraMarginLeft = f;
        this.extraMarginTop = f2;
    }

    public ArrayList<BaseFont> getSubstitutionFonts() {
        return this.substitutionFonts;
    }

    public void setSubstitutionFonts(ArrayList<BaseFont> arrayList) {
        this.substitutionFonts = arrayList;
    }

    public BaseFont getExtensionFont() {
        return this.extensionFont;
    }

    public void setExtensionFont(BaseFont baseFont) {
        this.extensionFont = baseFont;
    }
}
