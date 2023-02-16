package com.itextpdf.text.pdf;

import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.factories.RomanAlphabetFactory;
import com.itextpdf.text.factories.RomanNumberFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class PdfPageLabels {
    public static final int DECIMAL_ARABIC_NUMERALS = 0;
    public static final int EMPTY = 5;
    public static final int LOWERCASE_LETTERS = 4;
    public static final int LOWERCASE_ROMAN_NUMERALS = 2;
    public static final int UPPERCASE_LETTERS = 3;
    public static final int UPPERCASE_ROMAN_NUMERALS = 1;
    static PdfName[] numberingStyle = {PdfName.D, PdfName.R, new PdfName("r"), PdfName.A, new PdfName("a")};
    private HashMap<Integer, PdfDictionary> map = new HashMap<>();

    public PdfPageLabels() {
        addPageLabel(1, 0, (String) null, 1);
    }

    public void addPageLabel(int i, int i2, String str, int i3) {
        if (i < 1 || i3 < 1) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("in.a.page.label.the.page.numbers.must.be.greater.or.equal.to.1", new Object[0]));
        }
        PdfDictionary pdfDictionary = new PdfDictionary();
        if (i2 >= 0 && i2 < numberingStyle.length) {
            pdfDictionary.put(PdfName.S, numberingStyle[i2]);
        }
        if (str != null) {
            pdfDictionary.put(PdfName.P, new PdfString(str, PdfObject.TEXT_UNICODE));
        }
        if (i3 != 1) {
            pdfDictionary.put(PdfName.ST, new PdfNumber(i3));
        }
        this.map.put(Integer.valueOf(i - 1), pdfDictionary);
    }

    public void addPageLabel(int i, int i2, String str, int i3, boolean z) {
        if (i < 1 || i3 < 1) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("in.a.page.label.the.page.numbers.must.be.greater.or.equal.to.1", new Object[0]));
        }
        PdfDictionary pdfDictionary = new PdfDictionary();
        if (i2 >= 0 && i2 < numberingStyle.length) {
            pdfDictionary.put(PdfName.S, numberingStyle[i2]);
        }
        if (str != null) {
            pdfDictionary.put(PdfName.P, new PdfString(str, PdfObject.TEXT_UNICODE));
        }
        if (i3 != 1 || z) {
            pdfDictionary.put(PdfName.ST, new PdfNumber(i3));
        }
        this.map.put(Integer.valueOf(i - 1), pdfDictionary);
    }

    public void addPageLabel(int i, int i2, String str) {
        addPageLabel(i, i2, str, 1);
    }

    public void addPageLabel(int i, int i2) {
        addPageLabel(i, i2, (String) null, 1);
    }

    public void addPageLabel(PdfPageLabelFormat pdfPageLabelFormat) {
        addPageLabel(pdfPageLabelFormat.physicalPage, pdfPageLabelFormat.numberStyle, pdfPageLabelFormat.prefix, pdfPageLabelFormat.logicalPage);
    }

    public void removePageLabel(int i) {
        if (i > 1) {
            this.map.remove(Integer.valueOf(i - 1));
        }
    }

    public PdfDictionary getDictionary(PdfWriter pdfWriter) {
        try {
            return PdfNumberTree.writeTree(this.map, pdfWriter);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    public static String[] getPageLabels(PdfReader pdfReader) {
        int numberOfPages = pdfReader.getNumberOfPages();
        PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObjectRelease(pdfReader.getCatalog().get(PdfName.PAGELABELS));
        if (pdfDictionary == null) {
            return null;
        }
        String[] strArr = new String[numberOfPages];
        HashMap<Integer, PdfObject> readTree = PdfNumberTree.readTree(pdfDictionary);
        char c = 'D';
        String str = "";
        int i = 1;
        for (int i2 = 0; i2 < numberOfPages; i2++) {
            Integer valueOf = Integer.valueOf(i2);
            if (readTree.containsKey(valueOf)) {
                PdfDictionary pdfDictionary2 = (PdfDictionary) PdfReader.getPdfObjectRelease(readTree.get(valueOf));
                int intValue = pdfDictionary2.contains(PdfName.ST) ? ((PdfNumber) pdfDictionary2.get(PdfName.ST)).intValue() : 1;
                str = pdfDictionary2.contains(PdfName.P) ? ((PdfString) pdfDictionary2.get(PdfName.P)).toUnicodeString() : "";
                if (pdfDictionary2.contains(PdfName.S)) {
                    int i3 = intValue;
                    c = ((PdfName) pdfDictionary2.get(PdfName.S)).toString().charAt(1);
                    i = i3;
                } else {
                    i = intValue;
                    c = 'e';
                }
            }
            if (c == 'A') {
                strArr[i2] = str + RomanAlphabetFactory.getUpperCaseString(i);
            } else if (c == 'R') {
                strArr[i2] = str + RomanNumberFactory.getUpperCaseString(i);
            } else if (c == 'a') {
                strArr[i2] = str + RomanAlphabetFactory.getLowerCaseString(i);
            } else if (c == 'e') {
                strArr[i2] = str;
            } else if (c != 'r') {
                strArr[i2] = str + i;
            } else {
                strArr[i2] = str + RomanNumberFactory.getLowerCaseString(i);
            }
            i++;
        }
        return strArr;
    }

    public static PdfPageLabelFormat[] getPageLabelFormats(PdfReader pdfReader) {
        int i;
        PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObjectRelease(pdfReader.getCatalog().get(PdfName.PAGELABELS));
        if (pdfDictionary == null) {
            return null;
        }
        HashMap<Integer, PdfObject> readTree = PdfNumberTree.readTree(pdfDictionary);
        Integer[] numArr = (Integer[]) readTree.keySet().toArray(new Integer[readTree.size()]);
        Arrays.sort(numArr);
        PdfPageLabelFormat[] pdfPageLabelFormatArr = new PdfPageLabelFormat[readTree.size()];
        for (int i2 = 0; i2 < numArr.length; i2++) {
            Integer num = numArr[i2];
            PdfDictionary pdfDictionary2 = (PdfDictionary) PdfReader.getPdfObjectRelease(readTree.get(num));
            int intValue = pdfDictionary2.contains(PdfName.ST) ? ((PdfNumber) pdfDictionary2.get(PdfName.ST)).intValue() : 1;
            String unicodeString = pdfDictionary2.contains(PdfName.P) ? ((PdfString) pdfDictionary2.get(PdfName.P)).toUnicodeString() : "";
            if (pdfDictionary2.contains(PdfName.S)) {
                char charAt = ((PdfName) pdfDictionary2.get(PdfName.S)).toString().charAt(1);
                i = charAt != 'A' ? charAt != 'R' ? charAt != 'a' ? charAt != 'r' ? 0 : 2 : 4 : 1 : 3;
            } else {
                i = 5;
            }
            pdfPageLabelFormatArr[i2] = new PdfPageLabelFormat(num.intValue() + 1, i, unicodeString, intValue);
        }
        return pdfPageLabelFormatArr;
    }

    public static class PdfPageLabelFormat {
        public int logicalPage;
        public int numberStyle;
        public int physicalPage;
        public String prefix;

        public PdfPageLabelFormat(int i, int i2, String str, int i3) {
            this.physicalPage = i;
            this.numberStyle = i2;
            this.prefix = str;
            this.logicalPage = i3;
        }

        public String toString() {
            return String.format("Physical page %s: style: %s; prefix '%s'; logical page: %s", new Object[]{Integer.valueOf(this.physicalPage), Integer.valueOf(this.numberStyle), this.prefix, Integer.valueOf(this.logicalPage)});
        }
    }
}
