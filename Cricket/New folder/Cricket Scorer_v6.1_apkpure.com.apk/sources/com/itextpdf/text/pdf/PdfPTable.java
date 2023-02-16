package com.itextpdf.text.pdf;

import com.itextpdf.text.AccessibleElementId;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ElementListener;
import com.itextpdf.text.Image;
import com.itextpdf.text.LargeElement;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.api.Spaceable;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.events.PdfPTableEventForwarder;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PdfPTable implements LargeElement, Spaceable, IAccessibleElement {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BACKGROUNDCANVAS = 1;
    public static final int BASECANVAS = 0;
    public static final int LINECANVAS = 2;
    public static final int TEXTCANVAS = 3;
    private final Logger LOGGER;
    protected float[] absoluteWidths;
    protected HashMap<PdfName, PdfObject> accessibleAttributes;
    private PdfPTableBody body;
    protected boolean complete;
    protected int currentColIdx;
    protected PdfPCell[] currentRow;
    protected PdfPCell defaultCell;
    private boolean[] extendLastRow;
    private PdfPTableFooter footer;
    private int footerRows;
    private PdfPTableHeader header;
    protected int headerRows;
    private boolean headersInEvent;
    private int horizontalAlignment;
    protected AccessibleElementId id;
    protected boolean isColspan;
    private boolean keepTogether;
    private boolean lockedWidth;
    protected boolean loopCheck;
    private int numberOfWrittenRows;
    protected float paddingTop;
    protected float[] relativeWidths;
    protected PdfName role;
    protected boolean rowCompleted;
    protected ArrayList<PdfPRow> rows;
    protected boolean rowsNotChecked;
    protected int runDirection;
    private boolean skipFirstHeader;
    private boolean skipLastFooter;
    protected float spacingAfter;
    protected float spacingBefore;
    private boolean splitLate;
    private boolean splitRows;
    protected PdfPTableEvent tableEvent;
    protected float totalHeight;
    protected float totalWidth;
    protected float widthPercentage;

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
        return 23;
    }

    protected PdfPTable() {
        this.LOGGER = LoggerFactory.getLogger((Class<?>) PdfPTable.class);
        this.rows = new ArrayList<>();
        this.totalHeight = 0.0f;
        this.currentColIdx = 0;
        this.defaultCell = new PdfPCell((Phrase) null);
        this.totalWidth = 0.0f;
        this.widthPercentage = 80.0f;
        this.horizontalAlignment = 1;
        this.skipFirstHeader = false;
        this.skipLastFooter = false;
        this.isColspan = false;
        this.runDirection = 0;
        this.lockedWidth = false;
        this.splitRows = true;
        this.extendLastRow = new boolean[]{false, false};
        this.splitLate = true;
        this.complete = true;
        this.rowCompleted = true;
        this.loopCheck = true;
        this.rowsNotChecked = true;
        this.role = PdfName.TABLE;
        this.accessibleAttributes = null;
        this.id = new AccessibleElementId();
        this.header = null;
        this.body = null;
        this.footer = null;
    }

    public PdfPTable(float[] fArr) {
        this.LOGGER = LoggerFactory.getLogger((Class<?>) PdfPTable.class);
        this.rows = new ArrayList<>();
        this.totalHeight = 0.0f;
        this.currentColIdx = 0;
        this.defaultCell = new PdfPCell((Phrase) null);
        this.totalWidth = 0.0f;
        this.widthPercentage = 80.0f;
        this.horizontalAlignment = 1;
        this.skipFirstHeader = false;
        this.skipLastFooter = false;
        this.isColspan = false;
        this.runDirection = 0;
        this.lockedWidth = false;
        this.splitRows = true;
        this.extendLastRow = new boolean[]{false, false};
        this.splitLate = true;
        this.complete = true;
        this.rowCompleted = true;
        this.loopCheck = true;
        this.rowsNotChecked = true;
        this.role = PdfName.TABLE;
        this.accessibleAttributes = null;
        this.id = new AccessibleElementId();
        this.header = null;
        this.body = null;
        this.footer = null;
        if (fArr == null) {
            throw new NullPointerException(MessageLocalization.getComposedMessage("the.widths.array.in.pdfptable.constructor.can.not.be.null", new Object[0]));
        } else if (fArr.length == 0) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.widths.array.in.pdfptable.constructor.can.not.have.zero.length", new Object[0]));
        } else {
            this.relativeWidths = new float[fArr.length];
            System.arraycopy(fArr, 0, this.relativeWidths, 0, fArr.length);
            this.absoluteWidths = new float[fArr.length];
            calculateWidths();
            this.currentRow = new PdfPCell[this.absoluteWidths.length];
            this.keepTogether = false;
        }
    }

    public PdfPTable(int i) {
        this.LOGGER = LoggerFactory.getLogger((Class<?>) PdfPTable.class);
        this.rows = new ArrayList<>();
        this.totalHeight = 0.0f;
        this.currentColIdx = 0;
        this.defaultCell = new PdfPCell((Phrase) null);
        this.totalWidth = 0.0f;
        this.widthPercentage = 80.0f;
        this.horizontalAlignment = 1;
        this.skipFirstHeader = false;
        this.skipLastFooter = false;
        this.isColspan = false;
        this.runDirection = 0;
        this.lockedWidth = false;
        this.splitRows = true;
        this.extendLastRow = new boolean[]{false, false};
        this.splitLate = true;
        this.complete = true;
        this.rowCompleted = true;
        this.loopCheck = true;
        this.rowsNotChecked = true;
        this.role = PdfName.TABLE;
        this.accessibleAttributes = null;
        this.id = new AccessibleElementId();
        this.header = null;
        this.body = null;
        this.footer = null;
        if (i <= 0) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.number.of.columns.in.pdfptable.constructor.must.be.greater.than.zero", new Object[0]));
        }
        this.relativeWidths = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.relativeWidths[i2] = 1.0f;
        }
        this.absoluteWidths = new float[this.relativeWidths.length];
        calculateWidths();
        this.currentRow = new PdfPCell[this.absoluteWidths.length];
        this.keepTogether = false;
    }

    public PdfPTable(PdfPTable pdfPTable) {
        this.LOGGER = LoggerFactory.getLogger((Class<?>) PdfPTable.class);
        this.rows = new ArrayList<>();
        this.totalHeight = 0.0f;
        this.currentColIdx = 0;
        this.defaultCell = new PdfPCell((Phrase) null);
        this.totalWidth = 0.0f;
        this.widthPercentage = 80.0f;
        this.horizontalAlignment = 1;
        this.skipFirstHeader = false;
        this.skipLastFooter = false;
        this.isColspan = false;
        this.runDirection = 0;
        this.lockedWidth = false;
        this.splitRows = true;
        this.extendLastRow = new boolean[]{false, false};
        this.splitLate = true;
        this.complete = true;
        this.rowCompleted = true;
        this.loopCheck = true;
        this.rowsNotChecked = true;
        this.role = PdfName.TABLE;
        this.accessibleAttributes = null;
        this.id = new AccessibleElementId();
        this.header = null;
        this.body = null;
        this.footer = null;
        copyFormat(pdfPTable);
        int i = 0;
        while (i < this.currentRow.length && pdfPTable.currentRow[i] != null) {
            this.currentRow[i] = new PdfPCell(pdfPTable.currentRow[i]);
            i++;
        }
        for (int i2 = 0; i2 < pdfPTable.rows.size(); i2++) {
            PdfPRow pdfPRow = pdfPTable.rows.get(i2);
            if (pdfPRow != null) {
                pdfPRow = new PdfPRow(pdfPRow);
            }
            this.rows.add(pdfPRow);
        }
    }

    public void init() {
        this.LOGGER.info("Initialize row and cell heights");
        Iterator<PdfPRow> it = getRows().iterator();
        while (it.hasNext()) {
            PdfPRow next = it.next();
            if (next != null) {
                next.calculated = false;
                for (PdfPCell pdfPCell : next.getCells()) {
                    if (pdfPCell != null) {
                        pdfPCell.setCalculatedHeight(0.0f);
                    }
                }
            }
        }
    }

    public static PdfPTable shallowCopy(PdfPTable pdfPTable) {
        PdfPTable pdfPTable2 = new PdfPTable();
        pdfPTable2.copyFormat(pdfPTable);
        return pdfPTable2;
    }

    /* access modifiers changed from: protected */
    public void copyFormat(PdfPTable pdfPTable) {
        this.rowsNotChecked = pdfPTable.rowsNotChecked;
        this.relativeWidths = new float[pdfPTable.getNumberOfColumns()];
        this.absoluteWidths = new float[pdfPTable.getNumberOfColumns()];
        System.arraycopy(pdfPTable.relativeWidths, 0, this.relativeWidths, 0, getNumberOfColumns());
        System.arraycopy(pdfPTable.absoluteWidths, 0, this.absoluteWidths, 0, getNumberOfColumns());
        this.totalWidth = pdfPTable.totalWidth;
        this.totalHeight = pdfPTable.totalHeight;
        this.currentColIdx = 0;
        this.tableEvent = pdfPTable.tableEvent;
        this.runDirection = pdfPTable.runDirection;
        if (pdfPTable.defaultCell instanceof PdfPHeaderCell) {
            this.defaultCell = new PdfPHeaderCell((PdfPHeaderCell) pdfPTable.defaultCell);
        } else {
            this.defaultCell = new PdfPCell(pdfPTable.defaultCell);
        }
        this.currentRow = new PdfPCell[pdfPTable.currentRow.length];
        this.isColspan = pdfPTable.isColspan;
        this.splitRows = pdfPTable.splitRows;
        this.spacingAfter = pdfPTable.spacingAfter;
        this.spacingBefore = pdfPTable.spacingBefore;
        this.headerRows = pdfPTable.headerRows;
        this.footerRows = pdfPTable.footerRows;
        this.lockedWidth = pdfPTable.lockedWidth;
        this.extendLastRow = pdfPTable.extendLastRow;
        this.headersInEvent = pdfPTable.headersInEvent;
        this.widthPercentage = pdfPTable.widthPercentage;
        this.splitLate = pdfPTable.splitLate;
        this.skipFirstHeader = pdfPTable.skipFirstHeader;
        this.skipLastFooter = pdfPTable.skipLastFooter;
        this.horizontalAlignment = pdfPTable.horizontalAlignment;
        this.keepTogether = pdfPTable.keepTogether;
        this.complete = pdfPTable.complete;
        this.loopCheck = pdfPTable.loopCheck;
        this.id = pdfPTable.id;
        this.role = pdfPTable.role;
        if (pdfPTable.accessibleAttributes != null) {
            this.accessibleAttributes = new HashMap<>(pdfPTable.accessibleAttributes);
        }
        this.header = pdfPTable.getHeader();
        this.body = pdfPTable.getBody();
        this.footer = pdfPTable.getFooter();
    }

    public void setWidths(float[] fArr) throws DocumentException {
        if (fArr.length != getNumberOfColumns()) {
            throw new DocumentException(MessageLocalization.getComposedMessage("wrong.number.of.columns", new Object[0]));
        }
        this.relativeWidths = new float[fArr.length];
        System.arraycopy(fArr, 0, this.relativeWidths, 0, fArr.length);
        this.absoluteWidths = new float[fArr.length];
        this.totalHeight = 0.0f;
        calculateWidths();
        calculateHeights();
    }

    public void setWidths(int[] iArr) throws DocumentException {
        float[] fArr = new float[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            fArr[i] = (float) iArr[i];
        }
        setWidths(fArr);
    }

    /* access modifiers changed from: protected */
    public void calculateWidths() {
        if (this.totalWidth > 0.0f) {
            int numberOfColumns = getNumberOfColumns();
            float f = 0.0f;
            for (int i = 0; i < numberOfColumns; i++) {
                f += this.relativeWidths[i];
            }
            for (int i2 = 0; i2 < numberOfColumns; i2++) {
                this.absoluteWidths[i2] = (this.totalWidth * this.relativeWidths[i2]) / f;
            }
        }
    }

    public void setTotalWidth(float f) {
        if (this.totalWidth != f) {
            this.totalWidth = f;
            this.totalHeight = 0.0f;
            calculateWidths();
            calculateHeights();
        }
    }

    public void setTotalWidth(float[] fArr) throws DocumentException {
        if (fArr.length != getNumberOfColumns()) {
            throw new DocumentException(MessageLocalization.getComposedMessage("wrong.number.of.columns", new Object[0]));
        }
        this.totalWidth = 0.0f;
        for (int i = 0; i < fArr.length; i++) {
            this.totalWidth += fArr[i];
        }
        setWidths(fArr);
    }

    public void setWidthPercentage(float[] fArr, Rectangle rectangle) throws DocumentException {
        if (fArr.length != getNumberOfColumns()) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("wrong.number.of.columns", new Object[0]));
        }
        setTotalWidth(fArr);
        this.widthPercentage = (this.totalWidth / (rectangle.getRight() - rectangle.getLeft())) * 100.0f;
    }

    public float getTotalWidth() {
        return this.totalWidth;
    }

    public float calculateHeights() {
        if (this.totalWidth <= 0.0f) {
            return 0.0f;
        }
        this.totalHeight = 0.0f;
        for (int i = 0; i < this.rows.size(); i++) {
            this.totalHeight += getRowHeight(i, true);
        }
        return this.totalHeight;
    }

    public void resetColumnCount(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.number.of.columns.in.pdfptable.constructor.must.be.greater.than.zero", new Object[0]));
        }
        this.relativeWidths = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.relativeWidths[i2] = 1.0f;
        }
        this.absoluteWidths = new float[this.relativeWidths.length];
        calculateWidths();
        this.currentRow = new PdfPCell[this.absoluteWidths.length];
        this.totalHeight = 0.0f;
    }

    public PdfPCell getDefaultCell() {
        return this.defaultCell;
    }

    public PdfPCell addCell(PdfPCell pdfPCell) {
        PdfPCell pdfPCell2;
        boolean z;
        this.rowCompleted = false;
        if (pdfPCell instanceof PdfPHeaderCell) {
            pdfPCell2 = new PdfPHeaderCell((PdfPHeaderCell) pdfPCell);
        } else {
            pdfPCell2 = new PdfPCell(pdfPCell);
        }
        int min = Math.min(Math.max(pdfPCell2.getColspan(), 1), this.currentRow.length - this.currentColIdx);
        pdfPCell2.setColspan(min);
        if (min != 1) {
            this.isColspan = true;
        }
        if (pdfPCell2.getRunDirection() == 0) {
            pdfPCell2.setRunDirection(this.runDirection);
        }
        skipColsWithRowspanAbove();
        if (this.currentColIdx < this.currentRow.length) {
            this.currentRow[this.currentColIdx] = pdfPCell2;
            this.currentColIdx += min;
            z = true;
        } else {
            z = false;
        }
        skipColsWithRowspanAbove();
        while (this.currentColIdx >= this.currentRow.length) {
            int numberOfColumns = getNumberOfColumns();
            if (this.runDirection == 3) {
                PdfPCell[] pdfPCellArr = new PdfPCell[numberOfColumns];
                int length = this.currentRow.length;
                int i = 0;
                while (i < this.currentRow.length) {
                    PdfPCell pdfPCell3 = this.currentRow[i];
                    int colspan = pdfPCell3.getColspan();
                    length -= colspan;
                    pdfPCellArr[length] = pdfPCell3;
                    i = i + (colspan - 1) + 1;
                }
                this.currentRow = pdfPCellArr;
            }
            PdfPRow pdfPRow = new PdfPRow(this.currentRow);
            if (this.totalWidth > 0.0f) {
                pdfPRow.setWidths(this.absoluteWidths);
                this.totalHeight += pdfPRow.getMaxHeights();
            }
            this.rows.add(pdfPRow);
            this.currentRow = new PdfPCell[numberOfColumns];
            this.currentColIdx = 0;
            skipColsWithRowspanAbove();
            this.rowCompleted = true;
        }
        if (!z) {
            this.currentRow[this.currentColIdx] = pdfPCell2;
            this.currentColIdx += min;
        }
        return pdfPCell2;
    }

    private void skipColsWithRowspanAbove() {
        int i = this.runDirection == 3 ? -1 : 1;
        while (rowSpanAbove(this.rows.size(), this.currentColIdx)) {
            this.currentColIdx += i;
        }
    }

    /* access modifiers changed from: package-private */
    public PdfPCell cellAt(int i, int i2) {
        PdfPCell[] cells = this.rows.get(i).getCells();
        for (int i3 = 0; i3 < cells.length; i3++) {
            if (cells[i3] != null && i2 >= i3 && i2 < cells[i3].getColspan() + i3) {
                return cells[i3];
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean rowSpanAbove(int i, int i2) {
        PdfPCell pdfPCell;
        if (i2 >= getNumberOfColumns() || i2 < 0 || i < 1) {
            return false;
        }
        int i3 = i - 1;
        if (this.rows.get(i3) == null) {
            return false;
        }
        PdfPCell cellAt = cellAt(i3, i2);
        while (pdfPCell == null && i3 > 0) {
            i3--;
            if (this.rows.get(i3) == null) {
                return false;
            }
            cellAt = cellAt(i3, i2);
        }
        int i4 = i - i3;
        if (pdfPCell.getRowspan() == 1 && i4 > 1) {
            int i5 = i2 - 1;
            PdfPRow pdfPRow = this.rows.get(i3 + 1);
            i4--;
            pdfPCell = pdfPRow.getCells()[i5];
            while (pdfPCell == null && i5 > 0) {
                i5--;
                pdfPCell = pdfPRow.getCells()[i5];
            }
        }
        if (pdfPCell == null || pdfPCell.getRowspan() <= i4) {
            return false;
        }
        return true;
    }

    public void addCell(String str) {
        addCell(new Phrase(str));
    }

    public void addCell(PdfPTable pdfPTable) {
        this.defaultCell.setTable(pdfPTable);
        addCell(this.defaultCell).id = new AccessibleElementId();
        this.defaultCell.setTable((PdfPTable) null);
    }

    public void addCell(Image image) {
        this.defaultCell.setImage(image);
        addCell(this.defaultCell).id = new AccessibleElementId();
        this.defaultCell.setImage((Image) null);
    }

    public void addCell(Phrase phrase) {
        this.defaultCell.setPhrase(phrase);
        addCell(this.defaultCell).id = new AccessibleElementId();
        this.defaultCell.setPhrase((Phrase) null);
    }

    public float writeSelectedRows(int i, int i2, float f, float f2, PdfContentByte[] pdfContentByteArr) {
        return writeSelectedRows(0, -1, i, i2, f, f2, pdfContentByteArr);
    }

    public float writeSelectedRows(int i, int i2, int i3, int i4, float f, float f2, PdfContentByte[] pdfContentByteArr) {
        return writeSelectedRows(i, i2, i3, i4, f, f2, pdfContentByteArr, true);
    }

    public float writeSelectedRows(int i, int i2, int i3, int i4, float f, float f2, PdfContentByte[] pdfContentByteArr, boolean z) {
        int i5;
        int i6;
        int i7;
        Iterator<PdfPRow> it;
        int i8;
        PdfPRow pdfPRow;
        ArrayList<PdfPRow> arrayList;
        PdfPTableBody closeTableBlock;
        int i9 = i;
        int i10 = i2;
        int i11 = i4;
        if (this.totalWidth <= 0.0f) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("the.table.width.must.be.greater.than.zero", new Object[0]));
        }
        int size = this.rows.size();
        int i12 = i3 < 0 ? 0 : i3;
        if (i11 >= 0) {
            size = Math.min(i11, size);
        }
        if (i12 >= size) {
            return f2;
        }
        int numberOfColumns = getNumberOfColumns();
        if (i9 < 0) {
            i5 = 0;
        } else {
            i5 = Math.min(i9, numberOfColumns);
        }
        if (i10 < 0) {
            i6 = numberOfColumns;
        } else {
            i6 = Math.min(i10, numberOfColumns);
        }
        this.LOGGER.info(String.format("Writing row %s to %s; column %s to %s", new Object[]{Integer.valueOf(i12), Integer.valueOf(size), Integer.valueOf(i5), Integer.valueOf(i6)}));
        PdfPTableBody pdfPTableBody = null;
        if (this.rowsNotChecked) {
            getFittingRows(Float.MAX_VALUE, i12);
        }
        ArrayList<PdfPRow> rows2 = getRows(i12, size);
        Iterator<PdfPRow> it2 = rows2.iterator();
        float f3 = f2;
        int i13 = i12;
        while (it2.hasNext()) {
            PdfPRow next = it2.next();
            if (getHeader().rows != null && getHeader().rows.contains(next) && pdfPTableBody == null) {
                pdfPTableBody = openTableBlock(getHeader(), pdfContentByteArr[3]);
            } else if (getBody().rows != null && getBody().rows.contains(next) && pdfPTableBody == null) {
                pdfPTableBody = openTableBlock(getBody(), pdfContentByteArr[3]);
            } else if (getFooter().rows != null && getFooter().rows.contains(next) && pdfPTableBody == null) {
                pdfPTableBody = openTableBlock(getFooter(), pdfContentByteArr[3]);
            }
            PdfPTableBody pdfPTableBody2 = pdfPTableBody;
            if (next != null) {
                pdfPRow = next;
                i8 = i13;
                it = it2;
                arrayList = rows2;
                i7 = i12;
                next.writeCells(i5, i6, f, f3, pdfContentByteArr, z);
                f3 -= pdfPRow.getMaxHeights();
            } else {
                pdfPRow = next;
                i8 = i13;
                it = it2;
                arrayList = rows2;
                i7 = i12;
            }
            if (getHeader().rows != null && getHeader().rows.contains(pdfPRow) && (i8 == size - 1 || !getHeader().rows.contains(arrayList.get(i8 + 1)))) {
                closeTableBlock = closeTableBlock(getHeader(), pdfContentByteArr[3]);
            } else if (getBody().rows != null && getBody().rows.contains(pdfPRow) && (i8 == size - 1 || !getBody().rows.contains(arrayList.get(i8 + 1)))) {
                closeTableBlock = closeTableBlock(getBody(), pdfContentByteArr[3]);
            } else if (getFooter().rows == null || !getFooter().rows.contains(pdfPRow) || (i8 != size - 1 && getFooter().rows.contains(arrayList.get(i8 + 1)))) {
                pdfPTableBody = pdfPTableBody2;
                i13 = i8 + 1;
                rows2 = arrayList;
                it2 = it;
                i12 = i7;
            } else {
                closeTableBlock = closeTableBlock(getFooter(), pdfContentByteArr[3]);
            }
            pdfPTableBody = closeTableBlock;
            i13 = i8 + 1;
            rows2 = arrayList;
            it2 = it;
            i12 = i7;
        }
        ArrayList<PdfPRow> arrayList2 = rows2;
        int i14 = i12;
        if (this.tableEvent != null && i5 == 0 && i6 == numberOfColumns) {
            int i15 = i14;
            float[] fArr = new float[((size - i15) + 1)];
            ArrayList<PdfPRow> arrayList3 = arrayList2;
            fArr[0] = f2;
            for (int i16 = i15; i16 < size; i16++) {
                PdfPRow pdfPRow2 = arrayList3.get(i16);
                int i17 = i16 - i15;
                fArr[i17 + 1] = fArr[i17] - (pdfPRow2 != null ? pdfPRow2.getMaxHeights() : 0.0f);
            }
            this.tableEvent.tableLayout(this, getEventWidths(f, i15, size, this.headersInEvent), fArr, this.headersInEvent ? this.headerRows : 0, i15, pdfContentByteArr);
        }
        return f3;
    }

    private PdfPTableBody openTableBlock(PdfPTableBody pdfPTableBody, PdfContentByte pdfContentByte) {
        if (!pdfContentByte.writer.getStandardStructElems().contains(pdfPTableBody.getRole())) {
            return null;
        }
        pdfContentByte.openMCBlock(pdfPTableBody);
        return pdfPTableBody;
    }

    private PdfPTableBody closeTableBlock(PdfPTableBody pdfPTableBody, PdfContentByte pdfContentByte) {
        if (!pdfContentByte.writer.getStandardStructElems().contains(pdfPTableBody.getRole())) {
            return null;
        }
        pdfContentByte.closeMCBlock(pdfPTableBody);
        return null;
    }

    public float writeSelectedRows(int i, int i2, float f, float f2, PdfContentByte pdfContentByte) {
        return writeSelectedRows(0, -1, i, i2, f, f2, pdfContentByte);
    }

    public float writeSelectedRows(int i, int i2, int i3, int i4, float f, float f2, PdfContentByte pdfContentByte) {
        return writeSelectedRows(i, i2, i3, i4, f, f2, pdfContentByte, true);
    }

    public float writeSelectedRows(int i, int i2, int i3, int i4, float f, float f2, PdfContentByte pdfContentByte, boolean z) {
        int i5;
        int i6;
        PdfPTable pdfPTable;
        int i7 = i;
        int i8 = i2;
        int numberOfColumns = getNumberOfColumns();
        boolean z2 = false;
        if (i7 < 0) {
            i5 = 0;
        } else {
            i5 = Math.min(i7, numberOfColumns);
        }
        if (i8 < 0) {
            i6 = numberOfColumns;
        } else {
            i6 = Math.min(i8, numberOfColumns);
        }
        if (!(i5 == 0 && i6 == numberOfColumns)) {
            z2 = true;
        }
        if (z2) {
            float f3 = 0.0f;
            for (int i9 = i5; i9 < i6; i9++) {
                f3 += this.absoluteWidths[i9];
            }
            pdfPTable = this;
            pdfContentByte.saveState();
            float f4 = 10000.0f;
            float f5 = i5 == 0 ? 10000.0f : 0.0f;
            if (i6 != numberOfColumns) {
                f4 = 0.0f;
            }
            pdfContentByte.rectangle(f - f5, -10000.0f, f3 + f5 + f4, 20000.0f);
            pdfContentByte.clip();
            pdfContentByte.newPath();
        } else {
            pdfPTable = this;
            PdfContentByte pdfContentByte2 = pdfContentByte;
        }
        PdfContentByte[] beginWritingRows = beginWritingRows(pdfContentByte);
        float writeSelectedRows = pdfPTable.writeSelectedRows(i5, i6, i3, i4, f, f2, beginWritingRows, z);
        endWritingRows(beginWritingRows);
        if (z2) {
            pdfContentByte.restoreState();
        }
        return writeSelectedRows;
    }

    public static PdfContentByte[] beginWritingRows(PdfContentByte pdfContentByte) {
        return new PdfContentByte[]{pdfContentByte, pdfContentByte.getDuplicate(), pdfContentByte.getDuplicate(), pdfContentByte.getDuplicate()};
    }

    public static void endWritingRows(PdfContentByte[] pdfContentByteArr) {
        PdfContentByte pdfContentByte = pdfContentByteArr[0];
        PdfArtifact pdfArtifact = new PdfArtifact();
        pdfContentByte.openMCBlock(pdfArtifact);
        pdfContentByte.saveState();
        pdfContentByte.add(pdfContentByteArr[1]);
        pdfContentByte.restoreState();
        pdfContentByte.saveState();
        pdfContentByte.setLineCap(2);
        pdfContentByte.resetRGBColorStroke();
        pdfContentByte.add(pdfContentByteArr[2]);
        pdfContentByte.restoreState();
        pdfContentByte.closeMCBlock(pdfArtifact);
        pdfContentByte.add(pdfContentByteArr[3]);
    }

    public int size() {
        return this.rows.size();
    }

    public float getTotalHeight() {
        return this.totalHeight;
    }

    public float getRowHeight(int i) {
        return getRowHeight(i, false);
    }

    /* access modifiers changed from: protected */
    public float getRowHeight(int i, boolean z) {
        PdfPRow pdfPRow;
        int i2;
        float f;
        if (this.totalWidth <= 0.0f || i < 0 || i >= this.rows.size() || (pdfPRow = this.rows.get(i)) == null) {
            return 0.0f;
        }
        if (z) {
            pdfPRow.setWidths(this.absoluteWidths);
        }
        float maxHeights = pdfPRow.getMaxHeights();
        for (int i3 = 0; i3 < this.relativeWidths.length; i3++) {
            if (rowSpanAbove(i, i3)) {
                int i4 = 1;
                while (true) {
                    i2 = i - i4;
                    if (!rowSpanAbove(i2, i3)) {
                        break;
                    }
                    i4++;
                }
                PdfPCell pdfPCell = this.rows.get(i2).getCells()[i3];
                if (pdfPCell == null || pdfPCell.getRowspan() != i4 + 1) {
                    f = 0.0f;
                } else {
                    f = pdfPCell.getMaxHeight();
                    while (i4 > 0) {
                        f -= getRowHeight(i - i4);
                        i4--;
                    }
                }
                if (f > maxHeights) {
                    maxHeights = f;
                }
            }
        }
        pdfPRow.setMaxHeights(maxHeights);
        return maxHeights;
    }

    public float getRowspanHeight(int i, int i2) {
        PdfPRow pdfPRow;
        PdfPCell pdfPCell;
        float f = 0.0f;
        if (this.totalWidth <= 0.0f || i < 0 || i >= this.rows.size() || (pdfPRow = this.rows.get(i)) == null || i2 >= pdfPRow.getCells().length || (pdfPCell = pdfPRow.getCells()[i2]) == null) {
            return 0.0f;
        }
        for (int i3 = 0; i3 < pdfPCell.getRowspan(); i3++) {
            f += getRowHeight(i + i3);
        }
        return f;
    }

    public boolean hasRowspan(int i) {
        if (i < this.rows.size() && getRow(i).hasRowspan()) {
            return true;
        }
        PdfPRow row = i > 0 ? getRow(i - 1) : null;
        if (row != null && row.hasRowspan()) {
            return true;
        }
        for (int i2 = 0; i2 < getNumberOfColumns(); i2++) {
            if (rowSpanAbove(i - 1, i2)) {
                return true;
            }
        }
        return false;
    }

    public void normalizeHeadersFooters() {
        if (this.footerRows > this.headerRows) {
            this.footerRows = this.headerRows;
        }
    }

    public float getHeaderHeight() {
        int min = Math.min(this.rows.size(), this.headerRows);
        float f = 0.0f;
        for (int i = 0; i < min; i++) {
            PdfPRow pdfPRow = this.rows.get(i);
            if (pdfPRow != null) {
                f += pdfPRow.getMaxHeights();
            }
        }
        return f;
    }

    public float getFooterHeight() {
        int min = Math.min(this.rows.size(), this.headerRows);
        float f = 0.0f;
        for (int max = Math.max(0, this.headerRows - this.footerRows); max < min; max++) {
            PdfPRow pdfPRow = this.rows.get(max);
            if (pdfPRow != null) {
                f += pdfPRow.getMaxHeights();
            }
        }
        return f;
    }

    public boolean deleteRow(int i) {
        PdfPRow pdfPRow;
        if (i < 0 || i >= this.rows.size()) {
            return false;
        }
        if (this.totalWidth > 0.0f && (pdfPRow = this.rows.get(i)) != null) {
            this.totalHeight -= pdfPRow.getMaxHeights();
        }
        this.rows.remove(i);
        if (i < this.headerRows) {
            this.headerRows--;
            if (i >= this.headerRows - this.footerRows) {
                this.footerRows--;
            }
        }
        return true;
    }

    public boolean deleteLastRow() {
        return deleteRow(this.rows.size() - 1);
    }

    public void deleteBodyRows() {
        ArrayList<PdfPRow> arrayList = new ArrayList<>();
        for (int i = 0; i < this.headerRows; i++) {
            arrayList.add(this.rows.get(i));
        }
        this.rows = arrayList;
        this.totalHeight = 0.0f;
        if (this.totalWidth > 0.0f) {
            this.totalHeight = getHeaderHeight();
        }
    }

    public int getNumberOfColumns() {
        return this.relativeWidths.length;
    }

    public int getHeaderRows() {
        return this.headerRows;
    }

    public void setHeaderRows(int i) {
        if (i < 0) {
            i = 0;
        }
        this.headerRows = i;
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

    public String getSummary() {
        return getAccessibleAttribute(PdfName.SUMMARY).toString();
    }

    public void setSummary(String str) {
        setAccessibleAttribute(PdfName.SUMMARY, new PdfString(str));
    }

    public float getWidthPercentage() {
        return this.widthPercentage;
    }

    public void setWidthPercentage(float f) {
        this.widthPercentage = f;
    }

    public int getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    public void setHorizontalAlignment(int i) {
        this.horizontalAlignment = i;
    }

    public PdfPRow getRow(int i) {
        return this.rows.get(i);
    }

    public ArrayList<PdfPRow> getRows() {
        return this.rows;
    }

    public int getLastCompletedRowIndex() {
        return this.rows.size() - 1;
    }

    public void setBreakPoints(int... iArr) {
        keepRowsTogether(0, this.rows.size());
        for (int row : iArr) {
            getRow(row).setMayNotBreak(false);
        }
    }

    public void keepRowsTogether(int[] iArr) {
        for (int row : iArr) {
            getRow(row).setMayNotBreak(true);
        }
    }

    public void keepRowsTogether(int i, int i2) {
        if (i < i2) {
            while (i < i2) {
                getRow(i).setMayNotBreak(true);
                i++;
            }
        }
    }

    public void keepRowsTogether(int i) {
        keepRowsTogether(i, this.rows.size());
    }

    public ArrayList<PdfPRow> getRows(int i, int i2) {
        ArrayList<PdfPRow> arrayList = new ArrayList<>();
        if (i < 0 || i2 > size()) {
            return arrayList;
        }
        while (i < i2) {
            arrayList.add(adjustCellsInRow(i, i2));
            i++;
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public PdfPRow adjustCellsInRow(int i, int i2) {
        PdfPRow row = getRow(i);
        if (row.isAdjusted()) {
            return row;
        }
        PdfPRow pdfPRow = new PdfPRow(row);
        PdfPCell[] cells = pdfPRow.getCells();
        for (int i3 = 0; i3 < cells.length; i3++) {
            PdfPCell pdfPCell = cells[i3];
            if (!(pdfPCell == null || pdfPCell.getRowspan() == 1)) {
                int min = Math.min(i2, pdfPCell.getRowspan() + i);
                float f = 0.0f;
                for (int i4 = 1 + i; i4 < min; i4++) {
                    f += getRow(i4).getMaxHeights();
                }
                pdfPRow.setExtraHeight(i3, f);
            }
        }
        pdfPRow.setAdjusted(true);
        return pdfPRow;
    }

    public void setTableEvent(PdfPTableEvent pdfPTableEvent) {
        if (pdfPTableEvent == null) {
            this.tableEvent = null;
        } else if (this.tableEvent == null) {
            this.tableEvent = pdfPTableEvent;
        } else if (this.tableEvent instanceof PdfPTableEventForwarder) {
            ((PdfPTableEventForwarder) this.tableEvent).addTableEvent(pdfPTableEvent);
        } else {
            PdfPTableEventForwarder pdfPTableEventForwarder = new PdfPTableEventForwarder();
            pdfPTableEventForwarder.addTableEvent(this.tableEvent);
            pdfPTableEventForwarder.addTableEvent(pdfPTableEvent);
            this.tableEvent = pdfPTableEventForwarder;
        }
    }

    public PdfPTableEvent getTableEvent() {
        return this.tableEvent;
    }

    public float[] getAbsoluteWidths() {
        return this.absoluteWidths;
    }

    /* access modifiers changed from: package-private */
    public float[][] getEventWidths(float f, int i, int i2, boolean z) {
        int i3;
        int i4;
        if (z) {
            i = Math.max(i, this.headerRows);
            i2 = Math.max(i2, this.headerRows);
        }
        int i5 = 0;
        float[][] fArr = new float[(((z ? this.headerRows : 0) + i2) - i)][];
        if (this.isColspan) {
            if (z) {
                i3 = 0;
                while (i5 < this.headerRows) {
                    PdfPRow pdfPRow = this.rows.get(i5);
                    if (pdfPRow == null) {
                        i3++;
                    } else {
                        fArr[i3] = pdfPRow.getEventWidth(f, this.absoluteWidths);
                        i3++;
                    }
                    i5++;
                }
            } else {
                i3 = 0;
            }
            while (i < i2) {
                PdfPRow pdfPRow2 = this.rows.get(i);
                if (pdfPRow2 == null) {
                    i4 = i3 + 1;
                } else {
                    fArr[i3] = pdfPRow2.getEventWidth(f, this.absoluteWidths);
                    i4 = i3 + 1;
                }
                i++;
            }
        } else {
            int numberOfColumns = getNumberOfColumns();
            float[] fArr2 = new float[(numberOfColumns + 1)];
            fArr2[0] = f;
            int i6 = 0;
            while (i6 < numberOfColumns) {
                int i7 = i6 + 1;
                fArr2[i7] = fArr2[i6] + this.absoluteWidths[i6];
                i6 = i7;
            }
            while (i5 < fArr.length) {
                fArr[i5] = fArr2;
                i5++;
            }
        }
        return fArr;
    }

    public boolean isSkipFirstHeader() {
        return this.skipFirstHeader;
    }

    public boolean isSkipLastFooter() {
        return this.skipLastFooter;
    }

    public void setSkipFirstHeader(boolean z) {
        this.skipFirstHeader = z;
    }

    public void setSkipLastFooter(boolean z) {
        this.skipLastFooter = z;
    }

    public void setRunDirection(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
                this.runDirection = i;
                return;
            default:
                throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.run.direction.1", i));
        }
    }

    public int getRunDirection() {
        return this.runDirection;
    }

    public boolean isLockedWidth() {
        return this.lockedWidth;
    }

    public void setLockedWidth(boolean z) {
        this.lockedWidth = z;
    }

    public boolean isSplitRows() {
        return this.splitRows;
    }

    public void setSplitRows(boolean z) {
        this.splitRows = z;
    }

    public void setSpacingBefore(float f) {
        this.spacingBefore = f;
    }

    public void setSpacingAfter(float f) {
        this.spacingAfter = f;
    }

    public float spacingBefore() {
        return this.spacingBefore;
    }

    public float spacingAfter() {
        return this.spacingAfter;
    }

    public float getPaddingTop() {
        return this.paddingTop;
    }

    public void setPaddingTop(float f) {
        this.paddingTop = f;
    }

    public boolean isExtendLastRow() {
        return this.extendLastRow[0];
    }

    public void setExtendLastRow(boolean z) {
        this.extendLastRow[0] = z;
        this.extendLastRow[1] = z;
    }

    public void setExtendLastRow(boolean z, boolean z2) {
        this.extendLastRow[0] = z;
        this.extendLastRow[1] = z2;
    }

    public boolean isExtendLastRow(boolean z) {
        if (z) {
            return this.extendLastRow[0];
        }
        return this.extendLastRow[1];
    }

    public boolean isHeadersInEvent() {
        return this.headersInEvent;
    }

    public void setHeadersInEvent(boolean z) {
        this.headersInEvent = z;
    }

    public boolean isSplitLate() {
        return this.splitLate;
    }

    public void setSplitLate(boolean z) {
        this.splitLate = z;
    }

    public void setKeepTogether(boolean z) {
        this.keepTogether = z;
    }

    public boolean getKeepTogether() {
        return this.keepTogether;
    }

    public int getFooterRows() {
        return this.footerRows;
    }

    public void setFooterRows(int i) {
        if (i < 0) {
            i = 0;
        }
        this.footerRows = i;
    }

    public void completeRow() {
        while (!this.rowCompleted) {
            addCell(this.defaultCell);
        }
    }

    public void flushContent() {
        deleteBodyRows();
        if (this.numberOfWrittenRows > 0) {
            setSkipFirstHeader(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void addNumberOfRowsWritten(int i) {
        this.numberOfWrittenRows += i;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public void setComplete(boolean z) {
        this.complete = z;
    }

    public float getSpacingBefore() {
        return this.spacingBefore;
    }

    public float getSpacingAfter() {
        return this.spacingAfter;
    }

    public boolean isLoopCheck() {
        return this.loopCheck;
    }

    public void setLoopCheck(boolean z) {
        this.loopCheck = z;
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

    public PdfPTableHeader getHeader() {
        if (this.header == null) {
            this.header = new PdfPTableHeader();
        }
        return this.header;
    }

    public PdfPTableBody getBody() {
        if (this.body == null) {
            this.body = new PdfPTableBody();
        }
        return this.body;
    }

    public PdfPTableFooter getFooter() {
        if (this.footer == null) {
            this.footer = new PdfPTableFooter();
        }
        return this.footer;
    }

    public int getCellStartRowIndex(int i, int i2) {
        while (getRow(i).getCells()[i2] == null && i > 0) {
            i--;
        }
        return i;
    }

    public static class FittingRows {
        public final float completedRowsHeight;
        private final Map<Integer, Float> correctedHeightsForLastRow;
        public final int firstRow;
        public final float height;
        public final int lastRow;

        public FittingRows(int i, int i2, float f, float f2, Map<Integer, Float> map) {
            this.firstRow = i;
            this.lastRow = i2;
            this.height = f;
            this.completedRowsHeight = f2;
            this.correctedHeightsForLastRow = map;
        }

        public void correctLastRowChosen(PdfPTable pdfPTable, int i) {
            PdfPRow row = pdfPTable.getRow(i);
            Float f = this.correctedHeightsForLastRow.get(Integer.valueOf(i));
            if (f != null) {
                row.setFinalMaxHeights(f.floatValue());
            }
        }
    }

    public static class ColumnMeasurementState {
        public int colspan = 1;
        public float height = 0.0f;
        public int rowspan = 1;

        public void beginCell(PdfPCell pdfPCell, float f, float f2) {
            this.rowspan = pdfPCell.getRowspan();
            this.colspan = pdfPCell.getColspan();
            this.height = f + Math.max(pdfPCell.hasCachedMaxHeight() ? pdfPCell.getCachedMaxHeight() : pdfPCell.getMaxHeight(), f2);
        }

        public void consumeRowspan(float f, float f2) {
            this.rowspan--;
        }

        public boolean cellEnds() {
            return this.rowspan == 1;
        }
    }

    public FittingRows getFittingRows(float f, int i) {
        boolean z;
        float f2;
        int i2;
        int i3 = i;
        int i4 = 2;
        boolean z2 = false;
        this.LOGGER.info(String.format("getFittingRows(%s, %s)", new Object[]{Float.valueOf(f), Integer.valueOf(i)}));
        if (i3 > 0) {
            int size = this.rows.size();
        }
        int numberOfColumns = getNumberOfColumns();
        ColumnMeasurementState[] columnMeasurementStateArr = new ColumnMeasurementState[numberOfColumns];
        for (int i5 = 0; i5 < numberOfColumns; i5++) {
            columnMeasurementStateArr[i5] = new ColumnMeasurementState();
        }
        HashMap hashMap = new HashMap();
        int i6 = i3;
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (true) {
            if (i6 >= size()) {
                z = z2;
                break;
            }
            PdfPRow row = getRow(i6);
            float maxRowHeightsWithoutCalculating = row.getMaxRowHeightsWithoutCalculating();
            int i7 = z2;
            float f5 = 0.0f;
            while (i7 < numberOfColumns) {
                PdfPCell pdfPCell = row.getCells()[i7];
                ColumnMeasurementState columnMeasurementState = columnMeasurementStateArr[i7];
                if (pdfPCell == null) {
                    columnMeasurementState.consumeRowspan(f3, maxRowHeightsWithoutCalculating);
                    i2 = numberOfColumns;
                    f2 = maxRowHeightsWithoutCalculating;
                } else {
                    columnMeasurementState.beginCell(pdfPCell, f3, maxRowHeightsWithoutCalculating);
                    Logger logger = this.LOGGER;
                    i2 = numberOfColumns;
                    f2 = maxRowHeightsWithoutCalculating;
                    Object[] objArr = new Object[i4];
                    objArr[0] = Float.valueOf(columnMeasurementState.height);
                    objArr[1] = Float.valueOf(pdfPCell.getCachedMaxHeight());
                    logger.info(String.format("Height after beginCell: %s (cell: %s)", objArr));
                }
                if (columnMeasurementState.cellEnds() && columnMeasurementState.height > f5) {
                    f5 = columnMeasurementState.height;
                }
                for (int i8 = 1; i8 < columnMeasurementState.colspan; i8++) {
                    columnMeasurementStateArr[i7 + i8].height = columnMeasurementState.height;
                }
                i7 += columnMeasurementState.colspan;
                numberOfColumns = i2;
                maxRowHeightsWithoutCalculating = f2;
                i4 = 2;
            }
            int i9 = numberOfColumns;
            float f6 = 0.0f;
            for (ColumnMeasurementState columnMeasurementState2 : columnMeasurementStateArr) {
                if (columnMeasurementState2.height > f6) {
                    f6 = columnMeasurementState2.height;
                }
            }
            row.setFinalMaxHeights(f5 - f3);
            if (f - (isSplitLate() ? f6 : f5) < 0.0f) {
                z = false;
                break;
            }
            hashMap.put(Integer.valueOf(i6), Float.valueOf(f6 - f3));
            i6++;
            f4 = f6;
            f3 = f5;
            numberOfColumns = i9;
            i4 = 2;
            z2 = false;
        }
        this.rowsNotChecked = z;
        return new FittingRows(i3, i6 - 1, f4, f3, hashMap);
    }
}
