package com.itextpdf.text.pdf;

import java.util.ArrayList;

public class PdfTextArray {
    ArrayList<Object> arrayList = new ArrayList<>();
    private Float lastNum;
    private String lastStr;

    public PdfTextArray(String str) {
        add(str);
    }

    public PdfTextArray() {
    }

    public void add(PdfNumber pdfNumber) {
        add((float) pdfNumber.doubleValue());
    }

    public void add(float f) {
        if (f != 0.0f) {
            if (this.lastNum != null) {
                this.lastNum = new Float(f + this.lastNum.floatValue());
                if (this.lastNum.floatValue() != 0.0f) {
                    replaceLast(this.lastNum);
                } else {
                    this.arrayList.remove(this.arrayList.size() - 1);
                }
            } else {
                this.lastNum = new Float(f);
                this.arrayList.add(this.lastNum);
            }
            this.lastStr = null;
        }
    }

    public void add(String str) {
        if (str.length() > 0) {
            if (this.lastStr != null) {
                this.lastStr += str;
                replaceLast(this.lastStr);
            } else {
                this.lastStr = str;
                this.arrayList.add(this.lastStr);
            }
            this.lastNum = null;
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Object> getArrayList() {
        return this.arrayList;
    }

    private void replaceLast(Object obj) {
        this.arrayList.set(this.arrayList.size() - 1, obj);
    }
}
