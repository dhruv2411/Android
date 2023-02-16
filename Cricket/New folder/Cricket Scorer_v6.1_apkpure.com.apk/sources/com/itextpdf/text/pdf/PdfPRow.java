package com.itextpdf.text.pdf;

import com.itextpdf.text.AccessibleElementId;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.util.HashMap;

public class PdfPRow implements IAccessibleElement {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final float BOTTOM_LIMIT = -1.07374182E9f;
    public static final float RIGHT_LIMIT = 20000.0f;
    private final Logger LOGGER;
    protected HashMap<PdfName, PdfObject> accessibleAttributes;
    protected boolean adjusted;
    protected boolean calculated;
    private int[] canvasesPos;
    protected PdfPCell[] cells;
    protected float[] extraHeights;
    protected AccessibleElementId id;
    protected float maxHeight;
    public boolean mayNotBreak;
    protected PdfName role;
    protected float[] widths;

    public boolean isInline() {
        return false;
    }

    public PdfPRow(PdfPCell[] pdfPCellArr) {
        this(pdfPCellArr, (PdfPRow) null);
    }

    public PdfPRow(PdfPCell[] pdfPCellArr, PdfPRow pdfPRow) {
        this.LOGGER = LoggerFactory.getLogger((Class<?>) PdfPRow.class);
        this.mayNotBreak = false;
        this.maxHeight = 0.0f;
        this.calculated = false;
        this.adjusted = false;
        this.role = PdfName.TR;
        this.accessibleAttributes = null;
        this.id = new AccessibleElementId();
        this.cells = pdfPCellArr;
        this.widths = new float[pdfPCellArr.length];
        initExtraHeights();
        if (pdfPRow != null) {
            this.id = pdfPRow.id;
            this.role = pdfPRow.role;
            if (pdfPRow.accessibleAttributes != null) {
                this.accessibleAttributes = new HashMap<>(pdfPRow.accessibleAttributes);
            }
        }
    }

    public PdfPRow(PdfPRow pdfPRow) {
        this.LOGGER = LoggerFactory.getLogger((Class<?>) PdfPRow.class);
        this.mayNotBreak = false;
        this.maxHeight = 0.0f;
        this.calculated = false;
        this.adjusted = false;
        this.role = PdfName.TR;
        this.accessibleAttributes = null;
        this.id = new AccessibleElementId();
        this.mayNotBreak = pdfPRow.mayNotBreak;
        this.maxHeight = pdfPRow.maxHeight;
        this.calculated = pdfPRow.calculated;
        this.cells = new PdfPCell[pdfPRow.cells.length];
        for (int i = 0; i < this.cells.length; i++) {
            if (pdfPRow.cells[i] != null) {
                if (pdfPRow.cells[i] instanceof PdfPHeaderCell) {
                    this.cells[i] = new PdfPHeaderCell((PdfPHeaderCell) pdfPRow.cells[i]);
                } else {
                    this.cells[i] = new PdfPCell(pdfPRow.cells[i]);
                }
            }
        }
        this.widths = new float[this.cells.length];
        System.arraycopy(pdfPRow.widths, 0, this.widths, 0, this.cells.length);
        initExtraHeights();
        this.id = pdfPRow.id;
        this.role = pdfPRow.role;
        if (pdfPRow.accessibleAttributes != null) {
            this.accessibleAttributes = new HashMap<>(pdfPRow.accessibleAttributes);
        }
    }

    public boolean setWidths(float[] fArr) {
        int i = 0;
        if (fArr.length != this.cells.length) {
            return false;
        }
        System.arraycopy(fArr, 0, this.widths, 0, this.cells.length);
        this.calculated = false;
        float f = 0.0f;
        while (i < fArr.length) {
            PdfPCell pdfPCell = this.cells[i];
            if (pdfPCell == null) {
                f += fArr[i];
            } else {
                pdfPCell.setLeft(f);
                int colspan = pdfPCell.getColspan() + i;
                while (i < colspan) {
                    f += fArr[i];
                    i++;
                }
                i--;
                pdfPCell.setRight(f);
                pdfPCell.setTop(0.0f);
            }
            i++;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void initExtraHeights() {
        this.extraHeights = new float[this.cells.length];
        for (int i = 0; i < this.extraHeights.length; i++) {
            this.extraHeights[i] = 0.0f;
        }
    }

    public void setExtraHeight(int i, float f) {
        if (i >= 0 && i < this.cells.length) {
            this.extraHeights[i] = f;
        }
    }

    /* access modifiers changed from: protected */
    public void calculateHeights() {
        float f;
        this.maxHeight = 0.0f;
        this.LOGGER.info("calculateHeights");
        for (PdfPCell pdfPCell : this.cells) {
            if (pdfPCell != null) {
                if (pdfPCell.hasCalculatedHeight()) {
                    f = pdfPCell.getCalculatedHeight();
                } else {
                    f = pdfPCell.getMaxHeight();
                }
                if (f > this.maxHeight && pdfPCell.getRowspan() == 1) {
                    this.maxHeight = f;
                }
            }
        }
        this.calculated = true;
    }

    public void setMayNotBreak(boolean z) {
        this.mayNotBreak = z;
    }

    public boolean isMayNotBreak() {
        return this.mayNotBreak;
    }

    public void writeBorderAndBackground(float f, float f2, float f3, PdfPCell pdfPCell, PdfContentByte[] pdfContentByteArr) {
        BaseColor backgroundColor = pdfPCell.getBackgroundColor();
        if (backgroundColor != null || pdfPCell.hasBorders()) {
            float right = pdfPCell.getRight() + f;
            float top = pdfPCell.getTop() + f2;
            float left = pdfPCell.getLeft() + f;
            float f4 = top - f3;
            if (backgroundColor != null) {
                PdfContentByte pdfContentByte = pdfContentByteArr[1];
                pdfContentByte.setColorFill(backgroundColor);
                pdfContentByte.rectangle(left, f4, right - left, top - f4);
                pdfContentByte.fill();
            }
            if (pdfPCell.hasBorders()) {
                Rectangle rectangle = new Rectangle(left, f4, right, top);
                rectangle.cloneNonPositionParameters(pdfPCell);
                rectangle.setBackgroundColor((BaseColor) null);
                pdfContentByteArr[2].rectangle(rectangle);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void saveAndRotateCanvases(PdfContentByte[] pdfContentByteArr, float f, float f2, float f3, float f4, float f5, float f6) {
        if (this.canvasesPos == null) {
            this.canvasesPos = new int[8];
        }
        for (int i = 0; i < 4; i++) {
            ByteBuffer internalBuffer = pdfContentByteArr[i].getInternalBuffer();
            int i2 = i * 2;
            this.canvasesPos[i2] = internalBuffer.size();
            pdfContentByteArr[i].saveState();
            pdfContentByteArr[i].concatCTM(f, f2, f3, f4, f5, f6);
            this.canvasesPos[i2 + 1] = internalBuffer.size();
        }
    }

    /* access modifiers changed from: protected */
    public void restoreCanvases(PdfContentByte[] pdfContentByteArr) {
        for (int i = 0; i < 4; i++) {
            ByteBuffer internalBuffer = pdfContentByteArr[i].getInternalBuffer();
            int size = internalBuffer.size();
            pdfContentByteArr[i].restoreState();
            int i2 = i * 2;
            if (size == this.canvasesPos[i2 + 1]) {
                internalBuffer.setSize(this.canvasesPos[i2]);
            }
        }
    }

    public static float setColumn(ColumnText columnText, float f, float f2, float f3, float f4) {
        if (f > f3) {
            f3 = f;
        }
        if (f2 > f4) {
            f4 = f2;
        }
        columnText.setSimpleColumn(f, f2, f3, f4);
        return f4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0167, code lost:
        r3 = r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0406  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x0433 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeCells(int r24, int r25, float r26, float r27, com.itextpdf.text.pdf.PdfContentByte[] r28, boolean r29) {
        /*
            r23 = this;
            r9 = r23
            r1 = r25
            r10 = r28
            boolean r2 = r9.calculated
            if (r2 != 0) goto L_0x000d
            r23.calculateHeights()
        L_0x000d:
            if (r1 >= 0) goto L_0x0014
            com.itextpdf.text.pdf.PdfPCell[] r1 = r9.cells
            int r1 = r1.length
        L_0x0012:
            r12 = r1
            goto L_0x001c
        L_0x0014:
            com.itextpdf.text.pdf.PdfPCell[] r2 = r9.cells
            int r2 = r2.length
            int r1 = java.lang.Math.min(r1, r2)
            goto L_0x0012
        L_0x001c:
            if (r24 >= 0) goto L_0x0020
            r1 = 0
            goto L_0x0022
        L_0x0020:
            r1 = r24
        L_0x0022:
            if (r1 < r12) goto L_0x0025
            return
        L_0x0025:
            r2 = r26
        L_0x0027:
            if (r1 < 0) goto L_0x003c
            com.itextpdf.text.pdf.PdfPCell[] r3 = r9.cells
            r3 = r3[r1]
            if (r3 == 0) goto L_0x0030
            goto L_0x003c
        L_0x0030:
            if (r1 <= 0) goto L_0x0039
            float[] r3 = r9.widths
            int r4 = r1 + -1
            r3 = r3[r4]
            float r2 = r2 - r3
        L_0x0039:
            int r1 = r1 + -1
            goto L_0x0027
        L_0x003c:
            if (r1 >= 0) goto L_0x003f
            r1 = 0
        L_0x003f:
            com.itextpdf.text.pdf.PdfPCell[] r3 = r9.cells
            r3 = r3[r1]
            if (r3 == 0) goto L_0x004e
            com.itextpdf.text.pdf.PdfPCell[] r3 = r9.cells
            r3 = r3[r1]
            float r3 = r3.getLeft()
            float r2 = r2 - r3
        L_0x004e:
            r14 = r2
            r15 = 3
            r2 = r10[r15]
            boolean r2 = isTagged(r2)
            if (r2 == 0) goto L_0x005d
            r2 = r10[r15]
            r2.openMCBlock(r9)
        L_0x005d:
            r8 = r1
        L_0x005e:
            if (r8 >= r12) goto L_0x043e
            com.itextpdf.text.pdf.PdfPCell[] r1 = r9.cells
            r7 = r1[r8]
            if (r7 != 0) goto L_0x006a
        L_0x0066:
            r19 = r8
            goto L_0x0433
        L_0x006a:
            r1 = r10[r15]
            boolean r1 = isTagged(r1)
            if (r1 == 0) goto L_0x0077
            r1 = r10[r15]
            r1.openMCBlock(r7)
        L_0x0077:
            float r1 = r9.maxHeight
            float[] r2 = r9.extraHeights
            r2 = r2[r8]
            float r16 = r1 + r2
            r1 = r9
            r2 = r14
            r3 = r27
            r4 = r16
            r5 = r7
            r6 = r10
            r1.writeBorderAndBackground(r2, r3, r4, r5, r6)
            com.itextpdf.text.Image r1 = r7.getImage()
            float r2 = r7.getTop()
            float r2 = r2 + r27
            float r3 = r7.getEffectivePaddingTop()
            float r2 = r2 - r3
            float r3 = r7.getHeight()
            int r3 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            r4 = 1073741824(0x40000000, float:2.0)
            if (r3 > 0) goto L_0x00d1
            int r3 = r7.getVerticalAlignment()
            switch(r3) {
                case 5: goto L_0x00be;
                case 6: goto L_0x00ab;
                default: goto L_0x00aa;
            }
        L_0x00aa:
            goto L_0x00d1
        L_0x00ab:
            float r2 = r7.getTop()
            float r2 = r2 + r27
            float r2 = r2 - r16
            float r3 = r7.getHeight()
            float r2 = r2 + r3
            float r3 = r7.getEffectivePaddingTop()
            float r2 = r2 - r3
            goto L_0x00d1
        L_0x00be:
            float r2 = r7.getTop()
            float r2 = r2 + r27
            float r3 = r7.getHeight()
            float r3 = r3 - r16
            float r3 = r3 / r4
            float r2 = r2 + r3
            float r3 = r7.getEffectivePaddingTop()
            float r2 = r2 - r3
        L_0x00d1:
            if (r1 == 0) goto L_0x01a4
            int r5 = r7.getRotation()
            if (r5 == 0) goto L_0x00f9
            com.itextpdf.text.Image r1 = com.itextpdf.text.Image.getInstance((com.itextpdf.text.Image) r1)
            float r5 = r1.getImageRotation()
            int r6 = r7.getRotation()
            double r3 = (double) r6
            r19 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            double r3 = r3 * r19
            r19 = 4640537203540230144(0x4066800000000000, double:180.0)
            double r3 = r3 / r19
            float r3 = (float) r3
            float r5 = r5 + r3
            r1.setRotation(r5)
        L_0x00f9:
            float r3 = r7.getHeight()
            int r3 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            if (r3 <= 0) goto L_0x0125
            boolean r3 = r1.isScaleToFitHeight()
            if (r3 != 0) goto L_0x0109
            goto L_0x0066
        L_0x0109:
            r3 = 1120403456(0x42c80000, float:100.0)
            r1.scalePercent(r3)
            float r4 = r7.getEffectivePaddingTop()
            float r4 = r16 - r4
            float r5 = r7.getEffectivePaddingBottom()
            float r4 = r4 - r5
            float r5 = r1.getScaledHeight()
            float r4 = r4 / r5
            float r4 = r4 * r3
            r1.scalePercent(r4)
            r18 = 1
            goto L_0x0127
        L_0x0125:
            r18 = 0
        L_0x0127:
            float r3 = r7.getLeft()
            float r3 = r3 + r14
            float r4 = r7.getEffectivePaddingLeft()
            float r3 = r3 + r4
            if (r18 == 0) goto L_0x0173
            int r2 = r7.getHorizontalAlignment()
            switch(r2) {
                case 1: goto L_0x014b;
                case 2: goto L_0x013b;
                default: goto L_0x013a;
            }
        L_0x013a:
            goto L_0x0168
        L_0x013b:
            float r2 = r7.getRight()
            float r2 = r2 + r14
            float r3 = r7.getEffectivePaddingRight()
            float r2 = r2 - r3
            float r3 = r1.getScaledWidth()
            float r2 = r2 - r3
            goto L_0x0167
        L_0x014b:
            float r2 = r7.getLeft()
            float r3 = r7.getEffectivePaddingLeft()
            float r2 = r2 + r3
            float r3 = r7.getRight()
            float r2 = r2 + r3
            float r3 = r7.getEffectivePaddingRight()
            float r2 = r2 - r3
            float r3 = r1.getScaledWidth()
            float r2 = r2 - r3
            r3 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 / r3
            float r2 = r2 + r14
        L_0x0167:
            r3 = r2
        L_0x0168:
            float r2 = r7.getTop()
            float r2 = r2 + r27
            float r4 = r7.getEffectivePaddingTop()
            float r2 = r2 - r4
        L_0x0173:
            float r4 = r1.getScaledHeight()
            float r2 = r2 - r4
            r1.setAbsolutePosition(r3, r2)
            r2 = r10[r15]     // Catch:{ DocumentException -> 0x019c }
            boolean r2 = isTagged(r2)     // Catch:{ DocumentException -> 0x019c }
            if (r2 == 0) goto L_0x0188
            r2 = r10[r15]     // Catch:{ DocumentException -> 0x019c }
            r2.openMCBlock(r1)     // Catch:{ DocumentException -> 0x019c }
        L_0x0188:
            r2 = r10[r15]     // Catch:{ DocumentException -> 0x019c }
            r2.addImage(r1)     // Catch:{ DocumentException -> 0x019c }
            r2 = r10[r15]     // Catch:{ DocumentException -> 0x019c }
            boolean r2 = isTagged(r2)     // Catch:{ DocumentException -> 0x019c }
            if (r2 == 0) goto L_0x0250
            r2 = r10[r15]     // Catch:{ DocumentException -> 0x019c }
            r2.closeMCBlock(r1)     // Catch:{ DocumentException -> 0x019c }
            goto L_0x0250
        L_0x019c:
            r0 = move-exception
            r1 = r0
            com.itextpdf.text.ExceptionConverter r2 = new com.itextpdf.text.ExceptionConverter
            r2.<init>(r1)
            throw r2
        L_0x01a4:
            int r1 = r7.getRotation()
            r4 = 90
            r5 = 0
            if (r1 == r4) goto L_0x02c5
            int r1 = r7.getRotation()
            r6 = 270(0x10e, float:3.78E-43)
            if (r1 != r6) goto L_0x01b7
            goto L_0x02c5
        L_0x01b7:
            float r1 = r7.getFixedHeight()
            float r4 = r7.getRight()
            float r4 = r4 + r14
            float r6 = r7.getEffectivePaddingRight()
            float r4 = r4 - r6
            float r6 = r7.getLeft()
            float r6 = r6 + r14
            float r17 = r7.getEffectivePaddingLeft()
            float r6 = r6 + r17
            boolean r17 = r7.isNoWrap()
            r13 = 180(0xb4, float:2.52E-43)
            if (r17 == 0) goto L_0x01ff
            int r17 = r7.getHorizontalAlignment()
            r18 = 1184645120(0x469c4000, float:20000.0)
            switch(r17) {
                case 1: goto L_0x01f7;
                case 2: goto L_0x01eb;
                default: goto L_0x01e2;
            }
        L_0x01e2:
            int r3 = r7.getRotation()
            if (r3 != r13) goto L_0x01fd
            float r6 = r6 - r18
            goto L_0x01ff
        L_0x01eb:
            int r3 = r7.getRotation()
            if (r3 != r13) goto L_0x01f4
            float r4 = r4 + r18
            goto L_0x01ff
        L_0x01f4:
            float r6 = r6 - r18
            goto L_0x01ff
        L_0x01f7:
            r3 = 1176256512(0x461c4000, float:10000.0)
            float r4 = r4 + r3
            float r6 = r6 - r3
            goto L_0x01ff
        L_0x01fd:
            float r4 = r4 + r18
        L_0x01ff:
            if (r29 == 0) goto L_0x020a
            com.itextpdf.text.pdf.ColumnText r3 = r7.getColumn()
            com.itextpdf.text.pdf.ColumnText r3 = com.itextpdf.text.pdf.ColumnText.duplicate(r3)
            goto L_0x020e
        L_0x020a:
            com.itextpdf.text.pdf.ColumnText r3 = r7.getColumn()
        L_0x020e:
            r3.setCanvases(r10)
            float r17 = r7.getEffectivePaddingTop()
            float r17 = r16 - r17
            float r18 = r7.getEffectivePaddingBottom()
            float r17 = r17 - r18
            float r17 = r2 - r17
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x0245
            float r1 = r7.getHeight()
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 <= 0) goto L_0x0245
            float r1 = r7.getTop()
            float r1 = r1 + r27
            float r2 = r7.getEffectivePaddingTop()
            float r2 = r1 - r2
            float r1 = r7.getTop()
            float r1 = r1 + r27
            float r1 = r1 - r16
            float r5 = r7.getEffectivePaddingBottom()
            float r17 = r1 + r5
        L_0x0245:
            int r1 = (r2 > r17 ? 1 : (r2 == r17 ? 0 : -1))
            if (r1 > 0) goto L_0x0255
            boolean r1 = r3.zeroHeightElement()
            if (r1 == 0) goto L_0x0250
            goto L_0x0255
        L_0x0250:
            r13 = r7
            r19 = r8
            goto L_0x0400
        L_0x0255:
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 >= 0) goto L_0x0250
            r1 = 981668463(0x3a83126f, float:0.001)
            float r1 = r17 - r1
            r3.setSimpleColumn(r6, r1, r4, r2)
            int r1 = r7.getRotation()
            if (r1 != r13) goto L_0x0292
            float r17 = r6 + r4
            float r1 = r27 + r27
            float r1 = r1 - r16
            float r2 = r7.getEffectivePaddingBottom()
            float r1 = r1 + r2
            float r2 = r7.getEffectivePaddingTop()
            float r18 = r1 - r2
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            r5 = 0
            r6 = 0
            r19 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1 = r9
            r2 = r10
            r13 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r19
            r21 = r7
            r7 = r17
            r19 = r8
            r8 = r18
            r1.saveAndRotateCanvases(r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0297
        L_0x0292:
            r13 = r3
            r21 = r7
            r19 = r8
        L_0x0297:
            r13.go()     // Catch:{ DocumentException -> 0x02ae, all -> 0x02a9 }
            r13 = r21
            int r1 = r13.getRotation()
            r2 = 180(0xb4, float:2.52E-43)
            if (r1 != r2) goto L_0x0400
            r9.restoreCanvases(r10)
            goto L_0x0400
        L_0x02a9:
            r0 = move-exception
            r13 = r21
        L_0x02ac:
            r1 = r0
            goto L_0x02b9
        L_0x02ae:
            r0 = move-exception
            r13 = r21
            com.itextpdf.text.ExceptionConverter r1 = new com.itextpdf.text.ExceptionConverter     // Catch:{ all -> 0x02b7 }
            r1.<init>(r0)     // Catch:{ all -> 0x02b7 }
            throw r1     // Catch:{ all -> 0x02b7 }
        L_0x02b7:
            r0 = move-exception
            goto L_0x02ac
        L_0x02b9:
            int r2 = r13.getRotation()
            r3 = 180(0xb4, float:2.52E-43)
            if (r2 != r3) goto L_0x02c4
            r9.restoreCanvases(r10)
        L_0x02c4:
            throw r1
        L_0x02c5:
            r13 = r7
            r19 = r8
            float r1 = r13.getEffectivePaddingTop()
            float r1 = r16 - r1
            float r2 = r13.getEffectivePaddingBottom()
            float r1 = r1 - r2
            float r2 = r13.getWidth()
            float r3 = r13.getEffectivePaddingLeft()
            float r2 = r2 - r3
            float r3 = r13.getEffectivePaddingRight()
            float r2 = r2 - r3
            com.itextpdf.text.pdf.ColumnText r3 = r13.getColumn()
            com.itextpdf.text.pdf.ColumnText r3 = com.itextpdf.text.pdf.ColumnText.duplicate(r3)
            r3.setCanvases(r10)
            r6 = 981668463(0x3a83126f, float:0.001)
            float r6 = r6 + r1
            float r7 = -r2
            r3.setSimpleColumn(r5, r5, r6, r7)
            r6 = 1
            r3.go(r6)     // Catch:{ DocumentException -> 0x0437 }
            float r6 = r3.getYLine()
            float r6 = -r6
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0305
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 > 0) goto L_0x0306
        L_0x0305:
            r6 = r5
        L_0x0306:
            int r2 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r2 <= 0) goto L_0x0400
            boolean r2 = r13.isUseDescender()
            if (r2 == 0) goto L_0x0315
            float r2 = r3.getDescender()
            float r6 = r6 - r2
        L_0x0315:
            if (r29 == 0) goto L_0x0321
            com.itextpdf.text.pdf.ColumnText r2 = r13.getColumn()
            com.itextpdf.text.pdf.ColumnText r2 = com.itextpdf.text.pdf.ColumnText.duplicate(r2)
        L_0x031f:
            r8 = r2
            goto L_0x0326
        L_0x0321:
            com.itextpdf.text.pdf.ColumnText r2 = r13.getColumn()
            goto L_0x031f
        L_0x0326:
            r8.setCanvases(r10)
            r2 = -1153131610(0xffffffffbb449ba6, float:-0.003)
            r3 = -1165815185(0xffffffffba83126f, float:-0.001)
            r5 = 994352038(0x3b449ba6, float:0.003)
            float r1 = r1 + r5
            r8.setSimpleColumn(r2, r3, r1, r6)
            int r1 = r13.getRotation()
            if (r1 != r4) goto L_0x0396
            float r1 = r13.getTop()
            float r1 = r1 + r27
            float r1 = r1 - r16
            float r2 = r13.getEffectivePaddingBottom()
            float r18 = r1 + r2
            int r1 = r13.getVerticalAlignment()
            switch(r1) {
                case 5: goto L_0x036e;
                case 6: goto L_0x035e;
                default: goto L_0x0351;
            }
        L_0x0351:
            float r1 = r13.getLeft()
            float r1 = r1 + r14
            float r2 = r13.getEffectivePaddingLeft()
            float r1 = r1 + r2
            float r1 = r1 + r6
        L_0x035c:
            r7 = r1
            goto L_0x0387
        L_0x035e:
            float r1 = r13.getLeft()
            float r1 = r1 + r14
            float r2 = r13.getWidth()
            float r1 = r1 + r2
            float r2 = r13.getEffectivePaddingRight()
            float r1 = r1 - r2
            goto L_0x035c
        L_0x036e:
            float r1 = r13.getLeft()
            float r1 = r1 + r14
            float r2 = r13.getWidth()
            float r3 = r13.getEffectivePaddingLeft()
            float r2 = r2 + r3
            float r3 = r13.getEffectivePaddingRight()
            float r2 = r2 - r3
            float r2 = r2 + r6
            r3 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 / r3
            float r1 = r1 + r2
            goto L_0x035c
        L_0x0387:
            r3 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            r6 = 0
            r1 = r9
            r2 = r10
            r11 = r8
            r8 = r18
            r1.saveAndRotateCanvases(r2, r3, r4, r5, r6, r7, r8)
            goto L_0x03eb
        L_0x0396:
            r11 = r8
            float r1 = r13.getTop()
            float r1 = r1 + r27
            float r2 = r13.getEffectivePaddingTop()
            float r8 = r1 - r2
            int r1 = r13.getVerticalAlignment()
            switch(r1) {
                case 5: goto L_0x03c7;
                case 6: goto L_0x03bc;
                default: goto L_0x03aa;
            }
        L_0x03aa:
            float r1 = r13.getLeft()
            float r1 = r1 + r14
            float r2 = r13.getWidth()
            float r1 = r1 + r2
            float r2 = r13.getEffectivePaddingRight()
            float r1 = r1 - r2
            float r1 = r1 - r6
        L_0x03ba:
            r7 = r1
            goto L_0x03e0
        L_0x03bc:
            float r1 = r13.getLeft()
            float r1 = r1 + r14
            float r2 = r13.getEffectivePaddingLeft()
            float r1 = r1 + r2
            goto L_0x03ba
        L_0x03c7:
            float r1 = r13.getLeft()
            float r1 = r1 + r14
            float r2 = r13.getWidth()
            float r3 = r13.getEffectivePaddingLeft()
            float r2 = r2 + r3
            float r3 = r13.getEffectivePaddingRight()
            float r2 = r2 - r3
            float r2 = r2 - r6
            r3 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 / r3
            float r1 = r1 + r2
            goto L_0x03ba
        L_0x03e0:
            r3 = 0
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 0
            r1 = r9
            r2 = r10
            r1.saveAndRotateCanvases(r2, r3, r4, r5, r6, r7, r8)
        L_0x03eb:
            r11.go()     // Catch:{ DocumentException -> 0x03f5 }
            r9.restoreCanvases(r10)
            goto L_0x0400
        L_0x03f2:
            r0 = move-exception
            r1 = r0
            goto L_0x03fc
        L_0x03f5:
            r0 = move-exception
            com.itextpdf.text.ExceptionConverter r1 = new com.itextpdf.text.ExceptionConverter     // Catch:{ all -> 0x03f2 }
            r1.<init>(r0)     // Catch:{ all -> 0x03f2 }
            throw r1     // Catch:{ all -> 0x03f2 }
        L_0x03fc:
            r9.restoreCanvases(r10)
            throw r1
        L_0x0400:
            com.itextpdf.text.pdf.PdfPCellEvent r1 = r13.getCellEvent()
            if (r1 == 0) goto L_0x0426
            com.itextpdf.text.Rectangle r2 = new com.itextpdf.text.Rectangle
            float r3 = r13.getLeft()
            float r3 = r3 + r14
            float r4 = r13.getTop()
            float r4 = r4 + r27
            float r4 = r4 - r16
            float r5 = r13.getRight()
            float r5 = r5 + r14
            float r6 = r13.getTop()
            float r6 = r6 + r27
            r2.<init>(r3, r4, r5, r6)
            r1.cellLayout(r13, r2, r10)
        L_0x0426:
            r1 = r10[r15]
            boolean r1 = isTagged(r1)
            if (r1 == 0) goto L_0x0433
            r1 = r10[r15]
            r1.closeMCBlock(r13)
        L_0x0433:
            int r8 = r19 + 1
            goto L_0x005e
        L_0x0437:
            r0 = move-exception
            com.itextpdf.text.ExceptionConverter r1 = new com.itextpdf.text.ExceptionConverter
            r1.<init>(r0)
            throw r1
        L_0x043e:
            r1 = r10[r15]
            boolean r1 = isTagged(r1)
            if (r1 == 0) goto L_0x044b
            r1 = r10[r15]
            r1.closeMCBlock(r9)
        L_0x044b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfPRow.writeCells(int, int, float, float, com.itextpdf.text.pdf.PdfContentByte[], boolean):void");
    }

    public boolean isCalculated() {
        return this.calculated;
    }

    public float getMaxHeights() {
        if (!this.calculated) {
            calculateHeights();
        }
        return this.maxHeight;
    }

    public void setMaxHeights(float f) {
        this.maxHeight = f;
    }

    /* access modifiers changed from: package-private */
    public float[] getEventWidth(float f, float[] fArr) {
        int i = 1;
        int i2 = 0;
        int i3 = 1;
        while (i2 < this.cells.length) {
            if (this.cells[i2] != null) {
                i3++;
                i2 += this.cells[i2].getColspan();
            } else {
                while (i2 < this.cells.length && this.cells[i2] == null) {
                    i3++;
                    i2++;
                }
            }
        }
        float[] fArr2 = new float[i3];
        fArr2[0] = f;
        int i4 = 0;
        while (i4 < this.cells.length && i < fArr2.length) {
            if (this.cells[i4] != null) {
                int colspan = this.cells[i4].getColspan();
                fArr2[i] = fArr2[i - 1];
                int i5 = i4;
                int i6 = 0;
                while (i6 < colspan && i5 < fArr.length) {
                    fArr2[i] = fArr2[i] + fArr[i5];
                    i6++;
                    i5++;
                }
                i++;
                i4 = i5;
            } else {
                fArr2[i] = fArr2[i - 1];
                while (i4 < this.cells.length && this.cells[i4] == null) {
                    fArr2[i] = fArr2[i] + fArr[i4];
                    i4++;
                }
                i++;
            }
        }
        return fArr2;
    }

    public void copyRowContent(PdfPTable pdfPTable, int i) {
        if (pdfPTable != null) {
            for (int i2 = 0; i2 < this.cells.length; i2++) {
                PdfPCell pdfPCell = pdfPTable.getRow(i).getCells()[i2];
                int i3 = i;
                while (pdfPCell == null && i3 > 0) {
                    i3--;
                    pdfPCell = pdfPTable.getRow(i3).getCells()[i2];
                }
                if (!(this.cells[i2] == null || pdfPCell == null)) {
                    this.cells[i2].setColumn(pdfPCell.getColumn());
                    this.calculated = false;
                }
            }
        }
    }

    public PdfPRow splitRow(PdfPTable pdfPTable, int i, float f) {
        float[] fArr;
        float[] fArr2;
        boolean z;
        float f2;
        PdfPTable pdfPTable2 = pdfPTable;
        int i2 = i;
        float f3 = f;
        boolean z2 = true;
        this.LOGGER.info(String.format("Splitting row %s available height: %s", new Object[]{Integer.valueOf(i), Float.valueOf(f)}));
        PdfPCell[] pdfPCellArr = new PdfPCell[this.cells.length];
        float[] fArr3 = new float[this.cells.length];
        float[] fArr4 = new float[this.cells.length];
        float[] fArr5 = new float[this.cells.length];
        int i3 = 0;
        boolean z3 = true;
        while (i3 < this.cells.length) {
            PdfPCell pdfPCell = this.cells[i3];
            if (pdfPCell == null) {
                if (pdfPTable2.rowSpanAbove(i2, i3)) {
                    int i4 = i2;
                    while (true) {
                        i4--;
                        if (!pdfPTable2.rowSpanAbove(i4, i3)) {
                            break;
                        }
                        pdfPTable2.getRow(i4).getMaxHeights();
                    }
                    PdfPRow row = pdfPTable2.getRow(i4);
                    if (!(row == null || row.getCells()[i3] == null)) {
                        pdfPCellArr[i3] = new PdfPCell(row.getCells()[i3]);
                        pdfPCellArr[i3].setColumn((ColumnText) null);
                        pdfPCellArr[i3].setRowspan((row.getCells()[i3].getRowspan() - i2) + i4);
                        z3 = false;
                    }
                }
                fArr = fArr4;
                fArr2 = fArr5;
                z = z2;
            } else {
                fArr3[i3] = pdfPCell.getCalculatedHeight();
                fArr4[i3] = pdfPCell.getFixedHeight();
                fArr5[i3] = pdfPCell.getMinimumHeight();
                Image image = pdfPCell.getImage();
                PdfPCell pdfPCell2 = new PdfPCell(pdfPCell);
                if (image != null) {
                    float effectivePaddingBottom = pdfPCell.getEffectivePaddingBottom() + pdfPCell.getEffectivePaddingTop() + 2.0f;
                    if ((image.isScaleToFitHeight() || image.getScaledHeight() + effectivePaddingBottom < f3) && f3 > effectivePaddingBottom) {
                        pdfPCell2.setPhrase((Phrase) null);
                        z3 = false;
                    }
                    fArr = fArr4;
                    fArr2 = fArr5;
                    z = z2;
                } else {
                    ColumnText duplicate = ColumnText.duplicate(pdfPCell.getColumn());
                    float left = pdfPCell.getLeft() + pdfPCell.getEffectivePaddingLeft();
                    float top = (pdfPCell.getTop() + pdfPCell.getEffectivePaddingBottom()) - f3;
                    float right = pdfPCell.getRight() - pdfPCell.getEffectivePaddingRight();
                    float top2 = pdfPCell.getTop() - pdfPCell.getEffectivePaddingTop();
                    fArr2 = fArr5;
                    int rotation = pdfPCell.getRotation();
                    fArr = fArr4;
                    if (rotation == 90 || rotation == 270) {
                        f2 = setColumn(duplicate, top, left, top2, right);
                    } else {
                        float f4 = top + 1.0E-5f;
                        if (pdfPCell.isNoWrap()) {
                            right = 20000.0f;
                        }
                        f2 = setColumn(duplicate, left, f4, right, top2);
                    }
                    try {
                        int go = duplicate.go(true);
                        boolean z4 = duplicate.getYLine() == f2;
                        if (z4) {
                            pdfPCell2.setColumn(ColumnText.duplicate(pdfPCell.getColumn()));
                            duplicate.setFilledWidth(0.0f);
                            z = true;
                        } else {
                            z = true;
                            if ((go & 1) == 0) {
                                pdfPCell2.setColumn(duplicate);
                                duplicate.setFilledWidth(0.0f);
                            } else {
                                pdfPCell2.setPhrase((Phrase) null);
                            }
                        }
                        z3 = (!z3 || !z4) ? false : z;
                    } catch (DocumentException e) {
                        throw new ExceptionConverter(e);
                    }
                }
                pdfPCellArr[i3] = pdfPCell2;
                pdfPCell.setCalculatedHeight(f3);
            }
            i3++;
            z2 = z;
            fArr5 = fArr2;
            fArr4 = fArr;
            pdfPTable2 = pdfPTable;
            i2 = i;
        }
        float[] fArr6 = fArr4;
        float[] fArr7 = fArr5;
        if (z3) {
            for (int i5 = 0; i5 < this.cells.length; i5++) {
                PdfPCell pdfPCell3 = this.cells[i5];
                if (pdfPCell3 != null) {
                    pdfPCell3.setCalculatedHeight(fArr3[i5]);
                    if (fArr6[i5] > 0.0f) {
                        pdfPCell3.setFixedHeight(fArr6[i5]);
                    } else {
                        pdfPCell3.setMinimumHeight(fArr7[i5]);
                    }
                }
            }
            return null;
        }
        calculateHeights();
        PdfPRow pdfPRow = new PdfPRow(pdfPCellArr, this);
        pdfPRow.widths = (float[]) this.widths.clone();
        return pdfPRow;
    }

    public float getMaxRowHeightsWithoutCalculating() {
        return this.maxHeight;
    }

    public void setFinalMaxHeights(float f) {
        setMaxHeights(f);
        this.calculated = true;
    }

    public void splitRowspans(PdfPTable pdfPTable, int i, PdfPTable pdfPTable2, int i2) {
        if (pdfPTable != null && pdfPTable2 != null) {
            int i3 = 0;
            while (i3 < this.cells.length) {
                if (this.cells[i3] == null) {
                    int cellStartRowIndex = pdfPTable.getCellStartRowIndex(i, i3);
                    int cellStartRowIndex2 = pdfPTable2.getCellStartRowIndex(i2, i3);
                    PdfPCell pdfPCell = pdfPTable.getRow(cellStartRowIndex).getCells()[i3];
                    PdfPCell pdfPCell2 = pdfPTable2.getRow(cellStartRowIndex2).getCells()[i3];
                    if (pdfPCell != null) {
                        this.cells[i3] = new PdfPCell(pdfPCell2);
                        int i4 = (i2 - cellStartRowIndex2) + 1;
                        this.cells[i3].setRowspan(pdfPCell2.getRowspan() - i4);
                        pdfPCell.setRowspan(i4);
                        this.calculated = false;
                    }
                    i3++;
                } else {
                    i3 += this.cells[i3].getColspan();
                }
            }
        }
    }

    public PdfPCell[] getCells() {
        return this.cells;
    }

    public boolean hasRowspan() {
        for (int i = 0; i < this.cells.length; i++) {
            if (this.cells[i] != null && this.cells[i].getRowspan() > 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdjusted() {
        return this.adjusted;
    }

    public void setAdjusted(boolean z) {
        this.adjusted = z;
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

    private static boolean isTagged(PdfContentByte pdfContentByte) {
        return (pdfContentByte == null || pdfContentByte.writer == null || !pdfContentByte.writer.isTagged()) ? false : true;
    }
}
