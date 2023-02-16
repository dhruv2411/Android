package com.itextpdf.text.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.WritableDirectElement;
import com.itextpdf.text.api.Spaceable;
import com.itextpdf.text.pdf.PdfDiv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FloatLayout {
    protected final ColumnText compositeColumn = new ColumnText((PdfContentByte) null);
    protected final List<Element> content;
    protected float filledWidth;
    protected float floatLeftX;
    protected float floatRightX;
    protected float leftX;
    protected float maxY;
    protected float minY;
    protected float rightX;
    protected final boolean useAscender;
    protected float yLine;

    public float getYLine() {
        return this.yLine;
    }

    public void setYLine(float f) {
        this.yLine = f;
    }

    public float getFilledWidth() {
        return this.filledWidth;
    }

    public void setFilledWidth(float f) {
        this.filledWidth = f;
    }

    public int getRunDirection() {
        return this.compositeColumn.getRunDirection();
    }

    public void setRunDirection(int i) {
        this.compositeColumn.setRunDirection(i);
    }

    public FloatLayout(List<Element> list, boolean z) {
        this.compositeColumn.setUseAscender(z);
        this.useAscender = z;
        this.content = list;
    }

    public void setSimpleColumn(float f, float f2, float f3, float f4) {
        this.leftX = Math.min(f, f3);
        this.maxY = Math.max(f2, f4);
        this.minY = Math.min(f2, f4);
        this.rightX = Math.max(f, f3);
        this.floatLeftX = this.leftX;
        this.floatRightX = this.rightX;
        this.yLine = this.maxY;
        this.filledWidth = 0.0f;
    }

    public int layout(PdfContentByte pdfContentByte, boolean z) throws DocumentException {
        PdfDiv pdfDiv;
        this.compositeColumn.setCanvas(pdfContentByte);
        ArrayList arrayList = new ArrayList();
        List arrayList2 = z ? new ArrayList(this.content) : this.content;
        int i = 1;
        while (true) {
            if (arrayList2.isEmpty()) {
                break;
            } else if (arrayList2.get(0) instanceof PdfDiv) {
                pdfDiv = (PdfDiv) arrayList2.get(0);
                if (pdfDiv.getFloatType() == PdfDiv.FloatType.LEFT || pdfDiv.getFloatType() == PdfDiv.FloatType.RIGHT) {
                    arrayList.add(pdfDiv);
                    arrayList2.remove(0);
                } else {
                    if (!arrayList.isEmpty()) {
                        i = floatingLayout(arrayList, z);
                        if ((i & 1) == 0) {
                            break;
                        }
                    }
                    arrayList2.remove(0);
                    i = pdfDiv.layout(pdfContentByte, this.useAscender, true, this.floatLeftX, this.minY, this.floatRightX, this.yLine);
                    if (!pdfDiv.getKeepTogether() || (i & 1) != 0 || (this.compositeColumn.getCanvas().getPdfDocument().currentHeight <= 0.0f && this.yLine == this.maxY)) {
                        if (!z) {
                            pdfContentByte.openMCBlock(pdfDiv);
                            i = pdfDiv.layout(pdfContentByte, this.useAscender, z, this.floatLeftX, this.minY, this.floatRightX, this.yLine);
                            pdfContentByte.closeMCBlock(pdfDiv);
                        }
                        if (pdfDiv.getActualWidth() > this.filledWidth) {
                            this.filledWidth = pdfDiv.getActualWidth();
                        }
                        if ((i & 1) == 0) {
                            arrayList2.add(0, pdfDiv);
                            this.yLine = pdfDiv.getYLine();
                            break;
                        }
                        this.yLine -= pdfDiv.getActualHeight();
                    }
                }
            } else {
                arrayList.add(arrayList2.get(0));
                arrayList2.remove(0);
            }
        }
        arrayList2.add(0, pdfDiv);
        if ((i & 1) != 0 && !arrayList.isEmpty()) {
            i = floatingLayout(arrayList, z);
        }
        arrayList2.addAll(0, arrayList);
        return i;
    }

    private int floatingLayout(List<Element> list, boolean z) throws DocumentException {
        float f;
        float f2;
        Element element;
        ColumnText columnText;
        int i;
        int i2;
        ColumnText columnText2;
        PdfDiv pdfDiv;
        float f3;
        float f4;
        List<Element> list2 = list;
        boolean z2 = z;
        float f5 = this.yLine;
        ColumnText columnText3 = this.compositeColumn;
        if (z2) {
            columnText3 = ColumnText.duplicate(this.compositeColumn);
        }
        ColumnText columnText4 = columnText3;
        int i3 = 0;
        float f6 = f5;
        int i4 = this.maxY == this.yLine ? 1 : 0;
        int i5 = 1;
        float f7 = 0.0f;
        float f8 = 0.0f;
        while (true) {
            if (list.isEmpty()) {
                break;
            }
            Element element2 = list2.get(i3);
            list2.remove(i3);
            if (element2 instanceof PdfDiv) {
                PdfDiv pdfDiv2 = (PdfDiv) element2;
                i5 = pdfDiv2.layout(this.compositeColumn.getCanvas(), this.useAscender, true, this.floatLeftX, this.minY, this.floatRightX, this.yLine);
                if ((i5 & 1) == 0) {
                    this.yLine = f6;
                    this.floatLeftX = this.leftX;
                    this.floatRightX = this.rightX;
                    i5 = pdfDiv2.layout(this.compositeColumn.getCanvas(), this.useAscender, true, this.floatLeftX, this.minY, this.floatRightX, this.yLine);
                    if ((i5 & 1) == 0) {
                        list2.add(i3, pdfDiv2);
                        break;
                    }
                }
                if (pdfDiv2.getFloatType() == PdfDiv.FloatType.LEFT) {
                    PdfContentByte canvas = this.compositeColumn.getCanvas();
                    boolean z3 = this.useAscender;
                    pdfDiv = pdfDiv2;
                    float f9 = this.floatLeftX;
                    element = element2;
                    columnText2 = columnText4;
                    f3 = f7;
                    i5 = pdfDiv2.layout(canvas, z3, z2, f9, this.minY, this.floatRightX, this.yLine);
                    this.floatLeftX += pdfDiv.getActualWidth();
                    f4 = f8 + pdfDiv.getActualWidth();
                } else {
                    pdfDiv = pdfDiv2;
                    element = element2;
                    f4 = f8;
                    columnText2 = columnText4;
                    f3 = f7;
                    if (pdfDiv.getFloatType() == PdfDiv.FloatType.RIGHT) {
                        i5 = pdfDiv.layout(this.compositeColumn.getCanvas(), this.useAscender, z2, (this.floatRightX - pdfDiv.getActualWidth()) - 0.01f, this.minY, this.floatRightX, this.yLine);
                        this.floatRightX -= pdfDiv.getActualWidth();
                        f3 += pdfDiv.getActualWidth();
                    }
                }
                f8 = f4;
                f6 = Math.min(f6, this.yLine - pdfDiv.getActualHeight());
                f7 = f3;
                columnText = columnText2;
                List<Element> list3 = list;
            } else {
                element = element2;
                float f10 = f8;
                ColumnText columnText5 = columnText4;
                f2 = f7;
                if (this.minY > f6) {
                    i5 = 2;
                    f = f10;
                    list.add(0, element);
                    if (columnText5 != null) {
                        columnText5.setText((Phrase) null);
                    }
                } else {
                    float f11 = f10;
                    columnText = columnText5;
                    List<Element> list4 = list;
                    if ((element instanceof Spaceable) && (i4 == 0 || !columnText.isIgnoreSpacingBefore() || ((Spaceable) element).getPaddingTop() != 0.0f)) {
                        this.yLine -= ((Spaceable) element).getSpacingBefore();
                    }
                    if (!z2) {
                        columnText.addElement(element);
                    } else if (element instanceof PdfPTable) {
                        columnText.addElement(new PdfPTable((PdfPTable) element));
                    } else {
                        columnText.addElement(element);
                    }
                    if (this.yLine > f6) {
                        columnText.setSimpleColumn(this.floatLeftX, this.yLine, this.floatRightX, f6);
                    } else {
                        columnText.setSimpleColumn(this.floatLeftX, this.yLine, this.floatRightX, this.minY);
                    }
                    columnText.setFilledWidth(0.0f);
                    i5 = columnText.go(z2);
                    if (this.yLine <= f6 || ((this.floatLeftX <= this.leftX && this.floatRightX >= this.rightX) || (i5 & 1) != 0)) {
                        if (f2 > 0.0f) {
                            f2 += columnText.getFilledWidth();
                        } else if (f11 > 0.0f) {
                            f11 += columnText.getFilledWidth();
                        } else if (columnText.getFilledWidth() > this.filledWidth) {
                            this.filledWidth = columnText.getFilledWidth();
                        }
                        float min = Math.min(columnText.getYLine() + columnText.getDescender(), f6);
                        this.yLine = columnText.getYLine() + columnText.getDescender();
                        f6 = min;
                        f7 = f2;
                        f8 = f11;
                    } else {
                        this.yLine = f6;
                        this.floatLeftX = this.leftX;
                        this.floatRightX = this.rightX;
                        if (f11 == 0.0f || f2 == 0.0f) {
                            if (f11 > this.filledWidth) {
                                this.filledWidth = f11;
                            }
                            if (f2 > this.filledWidth) {
                                this.filledWidth = f2;
                            }
                        } else {
                            this.filledWidth = this.rightX - this.leftX;
                        }
                        if (z2 && (element instanceof PdfPTable)) {
                            columnText.addElement(new PdfPTable((PdfPTable) element));
                        }
                        columnText.setSimpleColumn(this.floatLeftX, this.yLine, this.floatRightX, this.minY);
                        i5 = columnText.go(z2);
                        float yLine2 = columnText.getYLine() + columnText.getDescender();
                        this.yLine = yLine2;
                        if (columnText.getFilledWidth() > this.filledWidth) {
                            this.filledWidth = columnText.getFilledWidth();
                        }
                        f6 = yLine2;
                        f7 = 0.0f;
                        f8 = 0.0f;
                    }
                    if ((i5 & 1) != 0) {
                        columnText.setText((Phrase) null);
                    } else if (!z2) {
                        list4.addAll(0, columnText.getCompositeElements());
                        columnText.getCompositeElements().clear();
                    } else {
                        list4.add(0, element);
                        columnText.setText((Phrase) null);
                    }
                }
            }
            boolean z4 = element instanceof Paragraph;
            if (z4) {
                Iterator it = ((Paragraph) element).iterator();
                while (it.hasNext()) {
                    Element element3 = (Element) it.next();
                    if (element3 instanceof WritableDirectElement) {
                        WritableDirectElement writableDirectElement = (WritableDirectElement) element3;
                        if (writableDirectElement.getDirectElementType() == 1 && !z2) {
                            PdfWriter pdfWriter = this.compositeColumn.getCanvas().getPdfWriter();
                            PdfDocument pdfDocument = this.compositeColumn.getCanvas().getPdfDocument();
                            float f12 = pdfDocument.currentHeight;
                            pdfDocument.currentHeight = (pdfDocument.top() - this.yLine) - pdfDocument.indentation.indentTop;
                            writableDirectElement.write(pdfWriter, pdfDocument);
                            pdfDocument.currentHeight = f12;
                        }
                    }
                    List<Element> list5 = list;
                }
            }
            if (i4 == 0 || element.getChunks().size() != 0) {
                i = 0;
            } else {
                if (z4) {
                    i = 0;
                    Element element4 = (Element) ((Paragraph) element).get(0);
                    if (element4 instanceof WritableDirectElement) {
                        if (((WritableDirectElement) element4).getDirectElementType() != 1) {
                            i2 = 0;
                            i4 = i2;
                        }
                    }
                    i2 = i4;
                    i4 = i2;
                } else {
                    i = 0;
                    if (!(element instanceof Spaceable)) {
                    }
                }
                i3 = i;
                columnText4 = columnText;
                list2 = list;
            }
            i4 = i;
            i3 = i;
            columnText4 = columnText;
            list2 = list;
        }
        f2 = f7;
        f = f8;
        if (f == 0.0f || f2 == 0.0f) {
            if (f > this.filledWidth) {
                this.filledWidth = f;
            }
            if (f2 > this.filledWidth) {
                this.filledWidth = f2;
            }
        } else {
            this.filledWidth = this.rightX - this.leftX;
        }
        this.yLine = f6;
        this.floatLeftX = this.leftX;
        this.floatRightX = this.rightX;
        return i5;
    }
}
