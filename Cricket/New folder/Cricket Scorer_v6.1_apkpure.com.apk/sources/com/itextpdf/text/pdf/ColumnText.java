package com.itextpdf.text.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ColumnText {
    public static final int AR_COMPOSEDTASHKEEL = 4;
    public static final int AR_LIG = 8;
    public static final int AR_NOVOWEL = 1;
    public static final int DIGITS_AN2EN = 64;
    public static final int DIGITS_EN2AN = 32;
    public static final int DIGITS_EN2AN_INIT_AL = 128;
    public static final int DIGITS_EN2AN_INIT_LR = 96;
    public static final int DIGIT_TYPE_AN = 0;
    public static final int DIGIT_TYPE_AN_EXTENDED = 256;
    public static final float GLOBAL_SPACE_CHAR_RATIO = 0.0f;
    protected static final int LINE_STATUS_NOLINE = 2;
    protected static final int LINE_STATUS_OFFLIMITS = 1;
    protected static final int LINE_STATUS_OK = 0;
    public static final int NO_MORE_COLUMN = 2;
    public static final int NO_MORE_TEXT = 1;
    public static final int START_COLUMN = 0;
    private final Logger LOGGER = LoggerFactory.getLogger((Class<?>) ColumnText.class);
    private boolean adjustFirstLine = true;
    protected int alignment = 0;
    private int arabicOptions = 0;
    protected BidiLine bidiLine;
    protected PdfContentByte canvas;
    protected PdfContentByte[] canvases;
    protected boolean composite = false;
    protected ColumnText compositeColumn;
    protected LinkedList<Element> compositeElements;
    protected float currentLeading = 16.0f;
    protected float descender;
    protected float extraParagraphSpace = 0.0f;
    private float filledWidth;
    private float firstLineY;
    private boolean firstLineYDone = false;
    protected float fixedLeading = 16.0f;
    protected float followingIndent = 0.0f;
    private boolean ignoreSpacingBefore = true;
    protected float indent = 0.0f;
    private boolean inheritGraphicState = false;
    protected boolean isWordSplit;
    private boolean lastWasNewline = true;
    protected float lastX;
    protected ArrayList<float[]> leftWall;
    protected float leftX;
    protected int lineStatus;
    private int linesWritten;
    protected int listIdx = 0;
    protected float maxY;
    protected float minY;
    protected float multipliedLeading = 0.0f;
    protected boolean rectangularMode = false;
    protected float rectangularWidth = -1.0f;
    private boolean repeatFirstLineIndent = true;
    protected float rightIndent = 0.0f;
    protected ArrayList<float[]> rightWall;
    protected float rightX;
    protected int rowIdx = 0;
    protected int runDirection = 0;
    private float spaceCharRatio = 0.0f;
    private int splittedRow = -2;
    private boolean useAscender = false;
    protected Phrase waitPhrase;
    protected float yLine;

    public static boolean hasMoreText(int i) {
        return (i & 1) == 0;
    }

    public ColumnText(PdfContentByte pdfContentByte) {
        this.canvas = pdfContentByte;
    }

    public static ColumnText duplicate(ColumnText columnText) {
        ColumnText columnText2 = new ColumnText((PdfContentByte) null);
        columnText2.setACopy(columnText);
        return columnText2;
    }

    public ColumnText setACopy(ColumnText columnText) {
        if (columnText != null) {
            setSimpleVars(columnText);
            if (columnText.bidiLine != null) {
                this.bidiLine = new BidiLine(columnText.bidiLine);
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void setSimpleVars(ColumnText columnText) {
        this.maxY = columnText.maxY;
        this.minY = columnText.minY;
        this.alignment = columnText.alignment;
        this.leftWall = null;
        if (columnText.leftWall != null) {
            this.leftWall = new ArrayList<>(columnText.leftWall);
        }
        this.rightWall = null;
        if (columnText.rightWall != null) {
            this.rightWall = new ArrayList<>(columnText.rightWall);
        }
        this.yLine = columnText.yLine;
        this.currentLeading = columnText.currentLeading;
        this.fixedLeading = columnText.fixedLeading;
        this.multipliedLeading = columnText.multipliedLeading;
        this.canvas = columnText.canvas;
        this.canvases = columnText.canvases;
        this.lineStatus = columnText.lineStatus;
        this.indent = columnText.indent;
        this.followingIndent = columnText.followingIndent;
        this.rightIndent = columnText.rightIndent;
        this.extraParagraphSpace = columnText.extraParagraphSpace;
        this.rectangularWidth = columnText.rectangularWidth;
        this.rectangularMode = columnText.rectangularMode;
        this.spaceCharRatio = columnText.spaceCharRatio;
        this.lastWasNewline = columnText.lastWasNewline;
        this.repeatFirstLineIndent = columnText.repeatFirstLineIndent;
        this.linesWritten = columnText.linesWritten;
        this.arabicOptions = columnText.arabicOptions;
        this.runDirection = columnText.runDirection;
        this.descender = columnText.descender;
        this.composite = columnText.composite;
        this.splittedRow = columnText.splittedRow;
        if (columnText.composite) {
            this.compositeElements = new LinkedList<>();
            Iterator it = columnText.compositeElements.iterator();
            while (it.hasNext()) {
                Element element = (Element) it.next();
                if (element instanceof PdfPTable) {
                    this.compositeElements.add(new PdfPTable((PdfPTable) element));
                } else {
                    this.compositeElements.add(element);
                }
            }
            if (columnText.compositeColumn != null) {
                this.compositeColumn = duplicate(columnText.compositeColumn);
            }
        }
        this.listIdx = columnText.listIdx;
        this.rowIdx = columnText.rowIdx;
        this.firstLineY = columnText.firstLineY;
        this.leftX = columnText.leftX;
        this.rightX = columnText.rightX;
        this.firstLineYDone = columnText.firstLineYDone;
        this.waitPhrase = columnText.waitPhrase;
        this.useAscender = columnText.useAscender;
        this.filledWidth = columnText.filledWidth;
        this.adjustFirstLine = columnText.adjustFirstLine;
        this.inheritGraphicState = columnText.inheritGraphicState;
        this.ignoreSpacingBefore = columnText.ignoreSpacingBefore;
    }

    private void addWaitingPhrase() {
        if (this.bidiLine == null && this.waitPhrase != null) {
            this.bidiLine = new BidiLine();
            for (Chunk pdfChunk : this.waitPhrase.getChunks()) {
                this.bidiLine.addChunk(new PdfChunk(pdfChunk, (PdfAction) null, this.waitPhrase.getTabSettings()));
            }
            this.waitPhrase = null;
        }
    }

    public void addText(Phrase phrase) {
        if (phrase != null && !this.composite) {
            addWaitingPhrase();
            if (this.bidiLine == null) {
                this.waitPhrase = phrase;
                return;
            }
            for (Chunk pdfChunk : phrase.getChunks()) {
                this.bidiLine.addChunk(new PdfChunk(pdfChunk, (PdfAction) null, phrase.getTabSettings()));
            }
        }
    }

    public void setText(Phrase phrase) {
        this.bidiLine = null;
        this.composite = false;
        this.compositeColumn = null;
        this.compositeElements = null;
        this.listIdx = 0;
        this.rowIdx = 0;
        this.splittedRow = -1;
        this.waitPhrase = phrase;
    }

    public void addText(Chunk chunk) {
        if (chunk != null && !this.composite) {
            addText(new Phrase(chunk));
        }
    }

    public void addElement(Element element) {
        Element element2;
        if (element != null) {
            if (element instanceof Image) {
                Image image = (Image) element;
                PdfPTable pdfPTable = new PdfPTable(1);
                float widthPercentage = image.getWidthPercentage();
                if (widthPercentage == 0.0f) {
                    pdfPTable.setTotalWidth(image.getScaledWidth());
                    pdfPTable.setLockedWidth(true);
                } else {
                    pdfPTable.setWidthPercentage(widthPercentage);
                }
                pdfPTable.setSpacingAfter(image.getSpacingAfter());
                pdfPTable.setSpacingBefore(image.getSpacingBefore());
                int alignment2 = image.getAlignment();
                if (alignment2 == 0) {
                    pdfPTable.setHorizontalAlignment(0);
                } else if (alignment2 != 2) {
                    pdfPTable.setHorizontalAlignment(1);
                } else {
                    pdfPTable.setHorizontalAlignment(2);
                }
                PdfPCell pdfPCell = new PdfPCell(image, true);
                pdfPCell.setPadding(0.0f);
                pdfPCell.setBorder(image.getBorder());
                pdfPCell.setBorderColor(image.getBorderColor());
                pdfPCell.setBorderWidth(image.getBorderWidth());
                pdfPCell.setBackgroundColor(image.getBackgroundColor());
                pdfPTable.addCell(pdfPCell);
                element = pdfPTable;
            }
            if (element.type() == 10) {
                element2 = new Paragraph((Chunk) element);
            } else if (element.type() == 11) {
                element2 = new Paragraph((Phrase) element);
            } else {
                if (element.type() == 23) {
                    ((PdfPTable) element).init();
                }
                element2 = element;
            }
            if (element2.type() == 12 || element2.type() == 14 || element2.type() == 23 || element2.type() == 55 || element2.type() == 37) {
                if (!this.composite) {
                    this.composite = true;
                    this.compositeElements = new LinkedList<>();
                    this.bidiLine = null;
                    this.waitPhrase = null;
                }
                if (element2.type() == 12) {
                    this.compositeElements.addAll(((Paragraph) element2).breakUp());
                } else {
                    this.compositeElements.add(element2);
                }
            } else {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("element.not.allowed", new Object[0]));
            }
        }
    }

    public static boolean isAllowedElement(Element element) {
        int type = element.type();
        if (type == 10 || type == 11 || type == 37 || type == 12 || type == 14 || type == 55 || type == 23 || (element instanceof Image)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ArrayList<float[]> convertColumn(float[] fArr) {
        if (fArr.length < 4) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("no.valid.column.line.found", new Object[0]));
        }
        ArrayList<float[]> arrayList = new ArrayList<>();
        int i = 0;
        while (i < fArr.length - 2) {
            float f = fArr[i];
            float f2 = fArr[i + 1];
            int i2 = i + 2;
            float f3 = fArr[i2];
            float f4 = fArr[i + 3];
            if (f2 != f4) {
                float f5 = (f - f3) / (f2 - f4);
                float[] fArr2 = {Math.min(f2, f4), Math.max(f2, f4), f5, f - (f5 * f2)};
                arrayList.add(fArr2);
                this.maxY = Math.max(this.maxY, fArr2[1]);
                this.minY = Math.min(this.minY, fArr2[0]);
            }
            i = i2;
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        throw new RuntimeException(MessageLocalization.getComposedMessage("no.valid.column.line.found", new Object[0]));
    }

    /* access modifiers changed from: protected */
    public float findLimitsPoint(ArrayList<float[]> arrayList) {
        this.lineStatus = 0;
        if (this.yLine < this.minY || this.yLine > this.maxY) {
            this.lineStatus = 1;
            return 0.0f;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            float[] fArr = arrayList.get(i);
            if (this.yLine >= fArr[0] && this.yLine <= fArr[1]) {
                return (fArr[2] * this.yLine) + fArr[3];
            }
        }
        this.lineStatus = 2;
        return 0.0f;
    }

    /* access modifiers changed from: protected */
    public float[] findLimitsOneLine() {
        float findLimitsPoint = findLimitsPoint(this.leftWall);
        if (this.lineStatus == 1 || this.lineStatus == 2) {
            return null;
        }
        float findLimitsPoint2 = findLimitsPoint(this.rightWall);
        if (this.lineStatus == 2) {
            return null;
        }
        return new float[]{findLimitsPoint, findLimitsPoint2};
    }

    /* access modifiers changed from: protected */
    public float[] findLimitsTwoLines() {
        boolean z = false;
        while (true) {
            if (z && this.currentLeading == 0.0f) {
                return null;
            }
            float[] findLimitsOneLine = findLimitsOneLine();
            if (this.lineStatus == 1) {
                return null;
            }
            this.yLine -= this.currentLeading;
            if (this.lineStatus != 2) {
                float[] findLimitsOneLine2 = findLimitsOneLine();
                if (this.lineStatus == 1) {
                    return null;
                }
                if (this.lineStatus == 2) {
                    this.yLine -= this.currentLeading;
                } else if (findLimitsOneLine[0] < findLimitsOneLine2[1] && findLimitsOneLine2[0] < findLimitsOneLine[1]) {
                    return new float[]{findLimitsOneLine[0], findLimitsOneLine[1], findLimitsOneLine2[0], findLimitsOneLine2[1]};
                }
            }
            z = true;
        }
    }

    public void setColumns(float[] fArr, float[] fArr2) {
        this.maxY = -1.0E21f;
        this.minY = 1.0E21f;
        setYLine(Math.max(fArr[1], fArr[fArr.length - 1]));
        this.rightWall = convertColumn(fArr2);
        this.leftWall = convertColumn(fArr);
        this.rectangularWidth = -1.0f;
        this.rectangularMode = false;
    }

    public void setSimpleColumn(Phrase phrase, float f, float f2, float f3, float f4, float f5, int i) {
        addText(phrase);
        setSimpleColumn(f, f2, f3, f4, f5, i);
    }

    public void setSimpleColumn(float f, float f2, float f3, float f4, float f5, int i) {
        setLeading(f5);
        this.alignment = i;
        setSimpleColumn(f, f2, f3, f4);
    }

    public void setSimpleColumn(float f, float f2, float f3, float f4) {
        this.leftX = Math.min(f, f3);
        this.maxY = Math.max(f2, f4);
        this.minY = Math.min(f2, f4);
        this.rightX = Math.max(f, f3);
        this.yLine = this.maxY;
        this.rectangularWidth = this.rightX - this.leftX;
        if (this.rectangularWidth < 0.0f) {
            this.rectangularWidth = 0.0f;
        }
        this.rectangularMode = true;
    }

    public void setSimpleColumn(Rectangle rectangle) {
        setSimpleColumn(rectangle.getLeft(), rectangle.getBottom(), rectangle.getRight(), rectangle.getTop());
    }

    public void setLeading(float f) {
        this.fixedLeading = f;
        this.multipliedLeading = 0.0f;
    }

    public void setLeading(float f, float f2) {
        this.fixedLeading = f;
        this.multipliedLeading = f2;
    }

    public float getLeading() {
        return this.fixedLeading;
    }

    public float getMultipliedLeading() {
        return this.multipliedLeading;
    }

    public void setYLine(float f) {
        this.yLine = f;
    }

    public float getYLine() {
        return this.yLine;
    }

    public int getRowsDrawn() {
        return this.rowIdx;
    }

    public void setAlignment(int i) {
        this.alignment = i;
    }

    public int getAlignment() {
        return this.alignment;
    }

    public void setIndent(float f) {
        setIndent(f, true);
    }

    public void setIndent(float f, boolean z) {
        this.indent = f;
        this.lastWasNewline = true;
        this.repeatFirstLineIndent = z;
    }

    public float getIndent() {
        return this.indent;
    }

    public void setFollowingIndent(float f) {
        this.followingIndent = f;
        this.lastWasNewline = true;
    }

    public float getFollowingIndent() {
        return this.followingIndent;
    }

    public void setRightIndent(float f) {
        this.rightIndent = f;
        this.lastWasNewline = true;
    }

    public float getRightIndent() {
        return this.rightIndent;
    }

    public float getCurrentLeading() {
        return this.currentLeading;
    }

    public boolean getInheritGraphicState() {
        return this.inheritGraphicState;
    }

    public void setInheritGraphicState(boolean z) {
        this.inheritGraphicState = z;
    }

    public boolean isIgnoreSpacingBefore() {
        return this.ignoreSpacingBefore;
    }

    public void setIgnoreSpacingBefore(boolean z) {
        this.ignoreSpacingBefore = z;
    }

    public int go() throws DocumentException {
        return go(false);
    }

    public int go(boolean z) throws DocumentException {
        return go(z, (IAccessibleElement) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f7, code lost:
        r29 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01fa, code lost:
        r28 = 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x02ad  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x02f1  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x02f4  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0330  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x033d  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x033f  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x034a  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x034d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int go(boolean r33, com.itextpdf.text.pdf.interfaces.IAccessibleElement r34) throws com.itextpdf.text.DocumentException {
        /*
            r32 = this;
            r0 = r32
            r2 = r34
            r3 = 0
            r0.isWordSplit = r3
            boolean r4 = r0.composite
            if (r4 == 0) goto L_0x0010
            int r1 = r32.goComposite(r33)
            return r1
        L_0x0010:
            com.itextpdf.text.pdf.PdfContentByte r4 = r0.canvas
            boolean r4 = isTagged(r4)
            if (r4 == 0) goto L_0x0024
            boolean r4 = r2 instanceof com.itextpdf.text.ListItem
            if (r4 == 0) goto L_0x0024
            r4 = r2
            com.itextpdf.text.ListItem r4 = (com.itextpdf.text.ListItem) r4
            com.itextpdf.text.ListBody r4 = r4.getListBody()
            goto L_0x0025
        L_0x0024:
            r4 = 0
        L_0x0025:
            r32.addWaitingPhrase()
            com.itextpdf.text.pdf.BidiLine r6 = r0.bidiLine
            r7 = 1
            if (r6 != 0) goto L_0x002e
            return r7
        L_0x002e:
            r6 = 0
            r0.descender = r6
            r0.linesWritten = r3
            r0.lastX = r6
            float r8 = r0.spaceCharRatio
            r9 = 2
            java.lang.Object[] r15 = new java.lang.Object[r9]
            java.lang.Float r10 = new java.lang.Float
            r10.<init>(r6)
            r15[r7] = r10
            r10 = 2143289344(0x7fc00000, float:NaN)
            r0.firstLineY = r10
            int r10 = r0.runDirection
            if (r10 == 0) goto L_0x004e
            int r10 = r0.runDirection
            r25 = r10
            goto L_0x0050
        L_0x004e:
            r25 = r7
        L_0x0050:
            com.itextpdf.text.pdf.PdfContentByte r10 = r0.canvas
            if (r10 == 0) goto L_0x0075
            com.itextpdf.text.pdf.PdfContentByte r10 = r0.canvas
            com.itextpdf.text.pdf.PdfContentByte r11 = r0.canvas
            com.itextpdf.text.pdf.PdfDocument r11 = r11.getPdfDocument()
            com.itextpdf.text.pdf.PdfContentByte r12 = r0.canvas
            boolean r12 = isTagged(r12)
            if (r12 != 0) goto L_0x0072
            com.itextpdf.text.pdf.PdfContentByte r12 = r0.canvas
            boolean r13 = r0.inheritGraphicState
            com.itextpdf.text.pdf.PdfContentByte r12 = r12.getDuplicate(r13)
        L_0x006c:
            r27 = r10
            r26 = r11
            r14 = r12
            goto L_0x008a
        L_0x0072:
            com.itextpdf.text.pdf.PdfContentByte r12 = r0.canvas
            goto L_0x006c
        L_0x0075:
            if (r33 != 0) goto L_0x0085
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "columntext.go.with.simulate.eq.eq.false.and.text.eq.eq.null"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)
            r1.<init>(r2)
            throw r1
        L_0x0085:
            r14 = 0
            r26 = 0
            r27 = 0
        L_0x008a:
            r10 = 981668463(0x3a83126f, float:0.001)
            if (r33 != 0) goto L_0x00a1
            int r11 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r11 != 0) goto L_0x009c
            com.itextpdf.text.pdf.PdfWriter r8 = r14.getPdfWriter()
            float r8 = r8.getSpaceCharRatio()
            goto L_0x00a1
        L_0x009c:
            int r11 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r11 >= 0) goto L_0x00a1
            r8 = r10
        L_0x00a1:
            boolean r10 = r0.rectangularMode
            if (r10 != 0) goto L_0x00cb
            com.itextpdf.text.pdf.BidiLine r10 = r0.bidiLine
            java.util.ArrayList<com.itextpdf.text.pdf.PdfChunk> r10 = r10.chunks
            java.util.Iterator r10 = r10.iterator()
            r11 = r6
        L_0x00ae:
            boolean r12 = r10.hasNext()
            if (r12 == 0) goto L_0x00c3
            java.lang.Object r12 = r10.next()
            com.itextpdf.text.pdf.PdfChunk r12 = (com.itextpdf.text.pdf.PdfChunk) r12
            float r12 = r12.height()
            float r11 = java.lang.Math.max(r11, r12)
            goto L_0x00ae
        L_0x00c3:
            float r10 = r0.fixedLeading
            float r12 = r0.multipliedLeading
            float r11 = r11 * r12
            float r10 = r10 + r11
            r0.currentLeading = r10
        L_0x00cb:
            r10 = r3
            r11 = r4
            r12 = 0
            r4 = r10
        L_0x00cf:
            boolean r13 = r0.lastWasNewline
            if (r13 == 0) goto L_0x00d6
            float r13 = r0.indent
            goto L_0x00d8
        L_0x00d6:
            float r13 = r0.followingIndent
        L_0x00d8:
            boolean r6 = r0.rectangularMode
            r16 = 3
            if (r6 == 0) goto L_0x01cf
            float r6 = r0.rectangularWidth
            float r5 = r0.rightIndent
            float r5 = r5 + r13
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x00fb
            com.itextpdf.text.pdf.BidiLine r1 = r0.bidiLine
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x00f5
            r29 = r10
            r28 = r16
            goto L_0x0273
        L_0x00f5:
            r28 = r9
        L_0x00f7:
            r29 = r10
            goto L_0x0273
        L_0x00fb:
            com.itextpdf.text.pdf.BidiLine r5 = r0.bidiLine
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0106
            r28 = r7
            goto L_0x00f7
        L_0x0106:
            com.itextpdf.text.pdf.BidiLine r5 = r0.bidiLine
            float r6 = r0.leftX
            float r9 = r0.rectangularWidth
            float r9 = r9 - r13
            float r7 = r0.rightIndent
            float r18 = r9 - r7
            int r7 = r0.alignment
            int r9 = r0.arabicOptions
            float r3 = r0.minY
            r29 = r10
            float r10 = r0.yLine
            r30 = r8
            float r8 = r0.descender
            r16 = r5
            r17 = r6
            r19 = r7
            r20 = r25
            r21 = r9
            r22 = r3
            r23 = r10
            r24 = r8
            com.itextpdf.text.pdf.PdfLine r3 = r16.processLine(r17, r18, r19, r20, r21, r22, r23, r24)
            boolean r5 = r0.isWordSplit
            com.itextpdf.text.pdf.BidiLine r6 = r0.bidiLine
            boolean r6 = r6.isWordSplit()
            r5 = r5 | r6
            r0.isWordSplit = r5
            if (r3 != 0) goto L_0x0142
            goto L_0x01fa
        L_0x0142:
            float r5 = r0.fixedLeading
            float r6 = r0.multipliedLeading
            float[] r5 = r3.getMaxSize(r5, r6)
            boolean r6 = r32.isUseAscender()
            if (r6 == 0) goto L_0x015f
            float r6 = r0.firstLineY
            boolean r6 = java.lang.Float.isNaN(r6)
            if (r6 == 0) goto L_0x015f
            float r5 = r3.getAscender()
            r0.currentLeading = r5
            goto L_0x016e
        L_0x015f:
            r6 = 0
            r7 = r5[r6]
            r6 = 1
            r5 = r5[r6]
            float r6 = r0.descender
            float r5 = r5 - r6
            float r5 = java.lang.Math.max(r7, r5)
            r0.currentLeading = r5
        L_0x016e:
            float r5 = r0.yLine
            float r6 = r0.maxY
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 > 0) goto L_0x01c6
            float r5 = r0.yLine
            float r6 = r0.currentLeading
            float r5 = r5 - r6
            float r6 = r0.minY
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 >= 0) goto L_0x0182
            goto L_0x01c6
        L_0x0182:
            float r5 = r0.yLine
            float r6 = r0.currentLeading
            float r5 = r5 - r6
            r0.yLine = r5
            if (r33 != 0) goto L_0x01aa
            if (r4 != 0) goto L_0x01aa
            boolean r4 = r3.isRTL
            if (r4 == 0) goto L_0x01a2
            com.itextpdf.text.pdf.PdfContentByte r4 = r0.canvas
            boolean r4 = r4.isTagged()
            if (r4 == 0) goto L_0x01a2
            com.itextpdf.text.pdf.PdfContentByte r4 = r0.canvas
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.REVERSEDCHARS
            r4.beginMarkedContentSequence((com.itextpdf.text.pdf.PdfName) r5)
            r10 = 1
            goto L_0x01a4
        L_0x01a2:
            r10 = r29
        L_0x01a4:
            r14.beginText()
            r29 = r10
            r4 = 1
        L_0x01aa:
            float r5 = r0.firstLineY
            boolean r5 = java.lang.Float.isNaN(r5)
            if (r5 == 0) goto L_0x01b6
            float r5 = r0.yLine
            r0.firstLineY = r5
        L_0x01b6:
            float r5 = r0.rectangularWidth
            float r6 = r3.widthLeft()
            float r5 = r5 - r6
            r0.updateFilledWidth(r5)
            float r5 = r0.leftX
            r31 = r13
            goto L_0x0295
        L_0x01c6:
            com.itextpdf.text.pdf.BidiLine r1 = r0.bidiLine
            r1.restore()
            r28 = 2
            goto L_0x0273
        L_0x01cf:
            r30 = r8
            r29 = r10
            float r3 = r0.yLine
            float r5 = r0.currentLeading
            float r3 = r3 - r5
            float[] r5 = r32.findLimitsTwoLines()
            if (r5 != 0) goto L_0x01f0
            com.itextpdf.text.pdf.BidiLine r1 = r0.bidiLine
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x01e9
            r7 = r16
            goto L_0x01ea
        L_0x01e9:
            r7 = 2
        L_0x01ea:
            r0.yLine = r3
            r28 = r7
            goto L_0x0273
        L_0x01f0:
            com.itextpdf.text.pdf.BidiLine r6 = r0.bidiLine
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x01fe
            r0.yLine = r3
        L_0x01fa:
            r28 = 1
            goto L_0x0273
        L_0x01fe:
            r6 = 0
            r7 = r5[r6]
            r6 = 2
            r8 = r5[r6]
            float r7 = java.lang.Math.max(r7, r8)
            r8 = 1
            r9 = r5[r8]
            r5 = r5[r16]
            float r5 = java.lang.Math.min(r9, r5)
            float r5 = r5 - r7
            float r8 = r0.rightIndent
            float r8 = r8 + r13
            int r8 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r8 > 0) goto L_0x0223
            r9 = r6
            r10 = r29
            r8 = r30
            r3 = 0
            r6 = 0
            r7 = 1
            goto L_0x00cf
        L_0x0223:
            com.itextpdf.text.pdf.BidiLine r8 = r0.bidiLine
            float r5 = r5 - r13
            float r9 = r0.rightIndent
            float r18 = r5 - r9
            int r5 = r0.alignment
            int r9 = r0.arabicOptions
            float r10 = r0.minY
            float r6 = r0.yLine
            r31 = r13
            float r13 = r0.descender
            r16 = r8
            r17 = r7
            r19 = r5
            r20 = r25
            r21 = r9
            r22 = r10
            r23 = r6
            r24 = r13
            com.itextpdf.text.pdf.PdfLine r5 = r16.processLine(r17, r18, r19, r20, r21, r22, r23, r24)
            if (r33 != 0) goto L_0x026a
            if (r4 != 0) goto L_0x026a
            boolean r4 = r5.isRTL
            if (r4 == 0) goto L_0x0263
            com.itextpdf.text.pdf.PdfContentByte r4 = r0.canvas
            boolean r4 = r4.isTagged()
            if (r4 == 0) goto L_0x0263
            com.itextpdf.text.pdf.PdfContentByte r4 = r0.canvas
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.REVERSEDCHARS
            r4.beginMarkedContentSequence((com.itextpdf.text.pdf.PdfName) r6)
            r29 = 1
        L_0x0263:
            r14.beginText()
            r10 = r29
            r4 = 1
            goto L_0x026c
        L_0x026a:
            r10 = r29
        L_0x026c:
            if (r5 != 0) goto L_0x0291
            r0.yLine = r3
            r29 = r10
            goto L_0x01fa
        L_0x0273:
            if (r4 == 0) goto L_0x0290
            r14.endText()
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.canvas
            if (r1 == r14) goto L_0x0281
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.canvas
            r1.add(r14)
        L_0x0281:
            if (r29 == 0) goto L_0x0290
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.canvas
            boolean r1 = r1.isTagged()
            if (r1 == 0) goto L_0x0290
            com.itextpdf.text.pdf.PdfContentByte r1 = r0.canvas
            r1.endMarkedContentSequence()
        L_0x0290:
            return r28
        L_0x0291:
            r3 = r5
            r5 = r7
            r29 = r10
        L_0x0295:
            com.itextpdf.text.pdf.PdfContentByte r6 = r0.canvas
            boolean r6 = isTagged(r6)
            if (r6 == 0) goto L_0x02f1
            boolean r6 = r2 instanceof com.itextpdf.text.ListItem
            if (r6 == 0) goto L_0x02f1
            float r6 = r0.firstLineY
            boolean r6 = java.lang.Float.isNaN(r6)
            if (r6 != 0) goto L_0x02f1
            boolean r6 = r0.firstLineYDone
            if (r6 != 0) goto L_0x02f1
            if (r33 != 0) goto L_0x02ec
            r6 = r2
            com.itextpdf.text.ListItem r6 = (com.itextpdf.text.ListItem) r6
            com.itextpdf.text.ListLabel r7 = r6.getListLabel()
            com.itextpdf.text.pdf.PdfContentByte r8 = r0.canvas
            r8.openMCBlock(r7)
            com.itextpdf.text.Chunk r8 = new com.itextpdf.text.Chunk
            com.itextpdf.text.Chunk r6 = r6.getListSymbol()
            r8.<init>((com.itextpdf.text.Chunk) r6)
            r6 = 0
            r8.setRole(r6)
            com.itextpdf.text.pdf.PdfContentByte r9 = r0.canvas
            r17 = 0
            com.itextpdf.text.Phrase r10 = new com.itextpdf.text.Phrase
            r10.<init>((com.itextpdf.text.Chunk) r8)
            float r8 = r0.leftX
            float r13 = r7.getIndentation()
            float r19 = r8 + r13
            float r8 = r0.firstLineY
            r21 = 0
            r16 = r9
            r18 = r10
            r20 = r8
            showTextAligned(r16, r17, r18, r19, r20, r21)
            com.itextpdf.text.pdf.PdfContentByte r8 = r0.canvas
            r8.closeMCBlock(r7)
            goto L_0x02ed
        L_0x02ec:
            r6 = 0
        L_0x02ed:
            r7 = 1
            r0.firstLineYDone = r7
            goto L_0x02f2
        L_0x02f1:
            r6 = 0
        L_0x02f2:
            if (r33 != 0) goto L_0x0330
            if (r11 == 0) goto L_0x02fd
            com.itextpdf.text.pdf.PdfContentByte r7 = r0.canvas
            r7.openMCBlock(r11)
            r7 = r6
            goto L_0x02fe
        L_0x02fd:
            r7 = r11
        L_0x02fe:
            r8 = 0
            r15[r8] = r12
            boolean r8 = r3.isRTL()
            if (r8 == 0) goto L_0x030a
            float r13 = r0.rightIndent
            goto L_0x030c
        L_0x030a:
            r13 = r31
        L_0x030c:
            float r5 = r5 + r13
            float r8 = r3.indentLeft()
            float r5 = r5 + r8
            float r8 = r0.yLine
            r14.setTextMatrix(r5, r8)
            r10 = r26
            r11 = r3
            r12 = r14
            r13 = r27
            r5 = r14
            r14 = r15
            r8 = r15
            r15 = r30
            float r9 = r10.writeLineToContent(r11, r12, r13, r14, r15)
            r0.lastX = r9
            r9 = 0
            r10 = r8[r9]
            com.itextpdf.text.pdf.PdfFont r10 = (com.itextpdf.text.pdf.PdfFont) r10
            r11 = r7
            r12 = r10
            goto L_0x0333
        L_0x0330:
            r5 = r14
            r8 = r15
            r9 = 0
        L_0x0333:
            boolean r7 = r0.repeatFirstLineIndent
            if (r7 == 0) goto L_0x033f
            boolean r7 = r3.isNewlineSplit()
            if (r7 == 0) goto L_0x033f
            r7 = 1
            goto L_0x0340
        L_0x033f:
            r7 = r9
        L_0x0340:
            r0.lastWasNewline = r7
            float r7 = r0.yLine
            boolean r10 = r3.isNewlineSplit()
            if (r10 == 0) goto L_0x034d
            float r10 = r0.extraParagraphSpace
            goto L_0x034e
        L_0x034d:
            r10 = 0
        L_0x034e:
            float r7 = r7 - r10
            r0.yLine = r7
            int r7 = r0.linesWritten
            r10 = 1
            int r7 = r7 + r10
            r0.linesWritten = r7
            float r3 = r3.getDescender()
            r0.descender = r3
            r14 = r5
            r15 = r8
            r3 = r9
            r7 = r10
            r10 = r29
            r8 = r30
            r6 = 0
            r9 = 2
            goto L_0x00cf
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.ColumnText.go(boolean, com.itextpdf.text.pdf.interfaces.IAccessibleElement):int");
    }

    public boolean isWordSplit() {
        return this.isWordSplit;
    }

    public float getExtraParagraphSpace() {
        return this.extraParagraphSpace;
    }

    public void setExtraParagraphSpace(float f) {
        this.extraParagraphSpace = f;
    }

    public void clearChunks() {
        if (this.bidiLine != null) {
            this.bidiLine.clearChunks();
        }
    }

    public float getSpaceCharRatio() {
        return this.spaceCharRatio;
    }

    public void setSpaceCharRatio(float f) {
        this.spaceCharRatio = f;
    }

    public void setRunDirection(int i) {
        if (i < 0 || i > 3) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.run.direction.1", i));
        }
        this.runDirection = i;
    }

    public int getRunDirection() {
        return this.runDirection;
    }

    public int getLinesWritten() {
        return this.linesWritten;
    }

    public float getLastX() {
        return this.lastX;
    }

    public int getArabicOptions() {
        return this.arabicOptions;
    }

    public void setArabicOptions(int i) {
        this.arabicOptions = i;
    }

    public float getDescender() {
        return this.descender;
    }

    public static float getWidth(Phrase phrase, int i, int i2) {
        ColumnText columnText = new ColumnText((PdfContentByte) null);
        columnText.addText(phrase);
        columnText.addWaitingPhrase();
        PdfLine processLine = columnText.bidiLine.processLine(0.0f, 20000.0f, 0, i, i2, 0.0f, 0.0f, 0.0f);
        if (processLine == null) {
            return 0.0f;
        }
        return 20000.0f - processLine.widthLeft();
    }

    public static float getWidth(Phrase phrase) {
        return getWidth(phrase, 1, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0086, code lost:
        if (r1 == 2) goto L_0x008a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void showTextAligned(com.itextpdf.text.pdf.PdfContentByte r19, int r20, com.itextpdf.text.Phrase r21, float r22, float r23, float r24, int r25, int r26) {
        /*
            r1 = r20
            r2 = r24
            r8 = r25
            r9 = 0
            r10 = 2
            if (r1 == 0) goto L_0x0011
            r3 = 1
            if (r1 == r3) goto L_0x0011
            if (r1 == r10) goto L_0x0011
            r15 = r9
            goto L_0x0012
        L_0x0011:
            r15 = r1
        L_0x0012:
            r19.saveState()
            com.itextpdf.text.pdf.ColumnText r14 = new com.itextpdf.text.pdf.ColumnText
            r13 = r19
            r14.<init>(r13)
            r11 = -1082130432(0xffffffffbf800000, float:-1.0)
            r12 = 1073741824(0x40000000, float:2.0)
            r1 = 1184645120(0x469c4000, float:20000.0)
            r3 = -962838528(0xffffffffc69c4000, float:-20000.0)
            r4 = 0
            if (r15 == 0) goto L_0x0035
            if (r15 == r10) goto L_0x0030
            r17 = r1
            r16 = r3
            goto L_0x0039
        L_0x0030:
            r16 = r3
            r17 = r4
            goto L_0x0039
        L_0x0035:
            r17 = r1
            r16 = r4
        L_0x0039:
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 != 0) goto L_0x0048
            float r16 = r16 + r22
            float r11 = r11 + r23
            float r17 = r17 + r22
            float r12 = r12 + r23
        L_0x0045:
            r1 = r11
            r2 = r12
            goto L_0x006a
        L_0x0048:
            double r1 = (double) r2
            r3 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            double r1 = r1 * r3
            r3 = 4640537203540230144(0x4066800000000000, double:180.0)
            double r1 = r1 / r3
            double r3 = java.lang.Math.cos(r1)
            float r5 = (float) r3
            double r1 = java.lang.Math.sin(r1)
            float r3 = (float) r1
            float r4 = -r3
            r1 = r13
            r2 = r5
            r6 = r22
            r7 = r23
            r1.concatCTM((float) r2, (float) r3, (float) r4, (float) r5, (float) r6, (float) r7)
            goto L_0x0045
        L_0x006a:
            r3 = 1073741824(0x40000000, float:2.0)
            r11 = r14
            r12 = r21
            r13 = r16
            r4 = r14
            r14 = r1
            r1 = r15
            r15 = r17
            r16 = r2
            r17 = r3
            r18 = r1
            r11.setSimpleColumn(r12, r13, r14, r15, r16, r17, r18)
            r2 = 3
            if (r8 != r2) goto L_0x0089
            if (r1 != 0) goto L_0x0086
            r9 = r10
            goto L_0x008a
        L_0x0086:
            if (r1 != r10) goto L_0x0089
            goto L_0x008a
        L_0x0089:
            r9 = r1
        L_0x008a:
            r4.setAlignment(r9)
            r1 = r26
            r4.setArabicOptions(r1)
            r4.setRunDirection(r8)
            r4.go()     // Catch:{ DocumentException -> 0x009c }
            r19.restoreState()
            return
        L_0x009c:
            r0 = move-exception
            com.itextpdf.text.ExceptionConverter r1 = new com.itextpdf.text.ExceptionConverter
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.ColumnText.showTextAligned(com.itextpdf.text.pdf.PdfContentByte, int, com.itextpdf.text.Phrase, float, float, float, int, int):void");
    }

    public static void showTextAligned(PdfContentByte pdfContentByte, int i, Phrase phrase, float f, float f2, float f3) {
        showTextAligned(pdfContentByte, i, phrase, f, f2, f3, 1, 0);
    }

    public static float fitText(Font font, String str, Rectangle rectangle, float f, int i) {
        float f2;
        Font font2 = font;
        String str2 = str;
        int i2 = i;
        if (f <= 0.0f) {
            try {
                char[] charArray = str.toCharArray();
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < charArray.length; i5++) {
                    if (charArray[i5] == 10) {
                        i4++;
                    } else if (charArray[i5] == 13) {
                        i3++;
                    }
                }
                f2 = (Math.abs(rectangle.getHeight()) / ((float) (Math.max(i3, i4) + 1))) - 0.001f;
            } catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        } else {
            f2 = f;
        }
        font2.setSize(f2);
        Phrase phrase = new Phrase(str2, font2);
        ColumnText columnText = new ColumnText((PdfContentByte) null);
        columnText.setSimpleColumn(phrase, rectangle.getLeft(), rectangle.getBottom(), rectangle.getRight(), rectangle.getTop(), f2, 0);
        columnText.setRunDirection(i2);
        if ((columnText.go(true) & 1) != 0) {
            return f2;
        }
        float f3 = f2;
        float f4 = 0.0f;
        for (int i6 = 0; i6 < 50; i6++) {
            f3 = (f4 + f2) / 2.0f;
            ColumnText columnText2 = new ColumnText((PdfContentByte) null);
            font2.setSize(f3);
            columnText2.setSimpleColumn(new Phrase(str2, font2), rectangle.getLeft(), rectangle.getBottom(), rectangle.getRight(), rectangle.getTop(), f3, 0);
            columnText2.setRunDirection(i2);
            if ((columnText2.go(true) & 1) == 0) {
                f2 = f3;
            } else if (f2 - f4 < f3 * 0.1f) {
                return f3;
            } else {
                f4 = f3;
            }
        }
        return f3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v101, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v102, resolved type: java.lang.Object[]} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v1, types: [int, boolean] */
    /* JADX WARNING: type inference failed for: r3v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r8v28 */
    /* JADX WARNING: type inference failed for: r6v26 */
    /* JADX WARNING: type inference failed for: r3v63 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0555, code lost:
        if (r12.lastRow < (r8.size() - 1)) goto L_0x055a;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:431:0x092f  */
    /* JADX WARNING: Removed duplicated region for block: B:432:0x0939  */
    /* JADX WARNING: Removed duplicated region for block: B:451:0x0970 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int goComposite(boolean r38) throws com.itextpdf.text.DocumentException {
        /*
            r37 = this;
            r0 = r37
            r1 = r38
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.canvas
            if (r2 == 0) goto L_0x000c
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.canvas
            com.itextpdf.text.pdf.PdfDocument r2 = r2.pdf
        L_0x000c:
            boolean r2 = r0.rectangularMode
            r3 = 0
            if (r2 != 0) goto L_0x001f
            com.itextpdf.text.DocumentException r1 = new com.itextpdf.text.DocumentException
            java.lang.String r2 = "irregular.columns.are.not.supported.in.composite.mode"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x001f:
            r0.linesWritten = r3
            r2 = 0
            r0.descender = r2
            int r4 = r0.runDirection
            r5 = 3
            r6 = 1
            if (r4 != r5) goto L_0x002c
            r4 = r6
            goto L_0x002d
        L_0x002c:
            r4 = r3
        L_0x002d:
            r7 = r6
        L_0x002e:
            java.util.LinkedList<com.itextpdf.text.Element> r8 = r0.compositeElements
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L_0x0037
            return r6
        L_0x0037:
            java.util.LinkedList<com.itextpdf.text.Element> r8 = r0.compositeElements
            java.lang.Object r8 = r8.getFirst()
            com.itextpdf.text.Element r8 = (com.itextpdf.text.Element) r8
            int r9 = r8.type()
            r10 = 12
            r11 = 0
            r12 = 2
            if (r9 != r10) goto L_0x01c7
            com.itextpdf.text.Paragraph r8 = (com.itextpdf.text.Paragraph) r8
            r9 = r3
            r10 = r9
        L_0x004d:
            if (r9 >= r12) goto L_0x0179
            float r10 = r0.yLine
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            if (r13 != 0) goto L_0x00c8
            com.itextpdf.text.pdf.ColumnText r13 = new com.itextpdf.text.pdf.ColumnText
            com.itextpdf.text.pdf.PdfContentByte r14 = r0.canvas
            r13.<init>(r14)
            r0.compositeColumn = r13
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            int r14 = r8.getAlignment()
            r13.setAlignment(r14)
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            float r14 = r8.getIndentationLeft()
            float r15 = r8.getFirstLineIndent()
            float r14 = r14 + r15
            r13.setIndent(r14, r3)
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            float r14 = r8.getExtraParagraphSpace()
            r13.setExtraParagraphSpace(r14)
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            float r14 = r8.getIndentationLeft()
            r13.setFollowingIndent(r14)
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            float r14 = r8.getIndentationRight()
            r13.setRightIndent(r14)
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            float r14 = r8.getLeading()
            float r15 = r8.getMultipliedLeading()
            r13.setLeading(r14, r15)
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            int r14 = r0.runDirection
            r13.setRunDirection(r14)
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            int r14 = r0.arabicOptions
            r13.setArabicOptions(r14)
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            float r14 = r0.spaceCharRatio
            r13.setSpaceCharRatio(r14)
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            r13.addText((com.itextpdf.text.Phrase) r8)
            if (r7 == 0) goto L_0x00bd
            boolean r13 = r0.adjustFirstLine
            if (r13 != 0) goto L_0x00c6
        L_0x00bd:
            float r13 = r0.yLine
            float r14 = r8.getSpacingBefore()
            float r13 = r13 - r14
            r0.yLine = r13
        L_0x00c6:
            r13 = r6
            goto L_0x00c9
        L_0x00c8:
            r13 = r3
        L_0x00c9:
            com.itextpdf.text.pdf.ColumnText r14 = r0.compositeColumn
            if (r7 != 0) goto L_0x00d3
            float r15 = r0.descender
            int r15 = (r15 > r2 ? 1 : (r15 == r2 ? 0 : -1))
            if (r15 != 0) goto L_0x00da
        L_0x00d3:
            boolean r15 = r0.adjustFirstLine
            if (r15 == 0) goto L_0x00da
            boolean r15 = r0.useAscender
            goto L_0x00db
        L_0x00da:
            r15 = r3
        L_0x00db:
            r14.setUseAscender(r15)
            com.itextpdf.text.pdf.ColumnText r14 = r0.compositeColumn
            boolean r15 = r0.inheritGraphicState
            r14.setInheritGraphicState(r15)
            com.itextpdf.text.pdf.ColumnText r14 = r0.compositeColumn
            float r15 = r0.leftX
            r14.leftX = r15
            com.itextpdf.text.pdf.ColumnText r14 = r0.compositeColumn
            float r15 = r0.rightX
            r14.rightX = r15
            com.itextpdf.text.pdf.ColumnText r14 = r0.compositeColumn
            float r15 = r0.yLine
            r14.yLine = r15
            com.itextpdf.text.pdf.ColumnText r14 = r0.compositeColumn
            float r15 = r0.rectangularWidth
            r14.rectangularWidth = r15
            com.itextpdf.text.pdf.ColumnText r14 = r0.compositeColumn
            boolean r15 = r0.rectangularMode
            r14.rectangularMode = r15
            com.itextpdf.text.pdf.ColumnText r14 = r0.compositeColumn
            float r15 = r0.minY
            r14.minY = r15
            com.itextpdf.text.pdf.ColumnText r14 = r0.compositeColumn
            float r15 = r0.maxY
            r14.maxY = r15
            boolean r14 = r8.getKeepTogether()
            if (r14 == 0) goto L_0x011f
            if (r13 == 0) goto L_0x011f
            if (r7 == 0) goto L_0x011d
            boolean r13 = r0.adjustFirstLine
            if (r13 != 0) goto L_0x011f
        L_0x011d:
            r13 = r6
            goto L_0x0120
        L_0x011f:
            r13 = r3
        L_0x0120:
            if (r1 != 0) goto L_0x0129
            if (r13 == 0) goto L_0x0127
            if (r9 != 0) goto L_0x0127
            goto L_0x0129
        L_0x0127:
            r14 = r3
            goto L_0x012a
        L_0x0129:
            r14 = r6
        L_0x012a:
            com.itextpdf.text.pdf.PdfContentByte r15 = r0.canvas
            boolean r15 = isTagged(r15)
            if (r15 == 0) goto L_0x0139
            if (r14 != 0) goto L_0x0139
            com.itextpdf.text.pdf.PdfContentByte r15 = r0.canvas
            r15.openMCBlock(r8)
        L_0x0139:
            com.itextpdf.text.pdf.ColumnText r15 = r0.compositeColumn
            int r15 = r15.go(r14)
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.canvas
            boolean r2 = isTagged(r2)
            if (r2 == 0) goto L_0x014e
            if (r14 != 0) goto L_0x014e
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.canvas
            r2.closeMCBlock(r8)
        L_0x014e:
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            float r2 = r2.getLastX()
            r0.lastX = r2
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            float r2 = r2.filledWidth
            r0.updateFilledWidth(r2)
            r2 = r15 & 1
            if (r2 != 0) goto L_0x0168
            if (r13 == 0) goto L_0x0168
            r0.compositeColumn = r11
            r0.yLine = r10
            return r12
        L_0x0168:
            if (r1 != 0) goto L_0x017a
            if (r13 != 0) goto L_0x016d
            goto L_0x017a
        L_0x016d:
            if (r9 != 0) goto L_0x0173
            r0.compositeColumn = r11
            r0.yLine = r10
        L_0x0173:
            int r9 = r9 + 1
            r10 = r15
            r2 = 0
            goto L_0x004d
        L_0x0179:
            r15 = r10
        L_0x017a:
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            int r2 = r2.getLinesWritten()
            if (r2 <= 0) goto L_0x01a2
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            float r2 = r2.yLine
            r0.yLine = r2
            int r2 = r0.linesWritten
            com.itextpdf.text.pdf.ColumnText r7 = r0.compositeColumn
            int r7 = r7.linesWritten
            int r2 = r2 + r7
            r0.linesWritten = r2
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            float r2 = r2.descender
            r0.descender = r2
            boolean r2 = r0.isWordSplit
            com.itextpdf.text.pdf.ColumnText r7 = r0.compositeColumn
            boolean r7 = r7.isWordSplit()
            r2 = r2 | r7
            r0.isWordSplit = r2
        L_0x01a2:
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            float r2 = r2.currentLeading
            r0.currentLeading = r2
            r2 = r15 & 1
            if (r2 == 0) goto L_0x01bc
            r0.compositeColumn = r11
            java.util.LinkedList<com.itextpdf.text.Element> r2 = r0.compositeElements
            r2.removeFirst()
            float r2 = r0.yLine
            float r7 = r8.getSpacingAfter()
            float r2 = r2 - r7
            r0.yLine = r2
        L_0x01bc:
            r2 = r15 & 2
            if (r2 == 0) goto L_0x01c1
            return r12
        L_0x01c1:
            r2 = r3
            r7 = r2
            r15 = r5
        L_0x01c4:
            r3 = 0
            goto L_0x097e
        L_0x01c7:
            int r2 = r8.type()
            r9 = 14
            if (r2 != r9) goto L_0x049a
            com.itextpdf.text.List r8 = (com.itextpdf.text.List) r8
            java.util.ArrayList r2 = r8.getItems()
            float r9 = r8.getIndentationLeft()
            java.util.Stack r13 = new java.util.Stack
            r13.<init>()
            r14 = r8
            r15 = r9
            r8 = r2
            r2 = r3
            r9 = r2
        L_0x01e3:
            int r10 = r8.size()
            if (r2 >= r10) goto L_0x0255
            java.lang.Object r10 = r8.get(r2)
            boolean r11 = r10 instanceof com.itextpdf.text.ListItem
            if (r11 == 0) goto L_0x01fc
            int r11 = r0.listIdx
            if (r9 != r11) goto L_0x01f9
            r11 = r10
            com.itextpdf.text.ListItem r11 = (com.itextpdf.text.ListItem) r11
            goto L_0x0256
        L_0x01f9:
            int r9 = r9 + 1
            goto L_0x0223
        L_0x01fc:
            boolean r11 = r10 instanceof com.itextpdf.text.List
            if (r11 == 0) goto L_0x0223
            java.lang.Object[] r8 = new java.lang.Object[r5]
            r8[r3] = r14
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r8[r6] = r2
            java.lang.Float r2 = new java.lang.Float
            r2.<init>(r15)
            r8[r12] = r2
            r13.push(r8)
            com.itextpdf.text.List r10 = (com.itextpdf.text.List) r10
            java.util.ArrayList r2 = r10.getItems()
            float r8 = r10.getIndentationLeft()
            float r15 = r15 + r8
            r8 = r2
            r14 = r10
            r10 = -1
            goto L_0x0251
        L_0x0223:
            r10 = r2
        L_0x0224:
            int r2 = r8.size()
            int r2 = r2 - r6
            if (r10 != r2) goto L_0x0251
            boolean r2 = r13.isEmpty()
            if (r2 != 0) goto L_0x0251
            java.lang.Object r2 = r13.pop()
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            r8 = r2[r3]
            r14 = r8
            com.itextpdf.text.List r14 = (com.itextpdf.text.List) r14
            java.util.ArrayList r8 = r14.getItems()
            r10 = r2[r6]
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r2 = r2[r12]
            java.lang.Float r2 = (java.lang.Float) r2
            float r15 = r2.floatValue()
            goto L_0x0224
        L_0x0251:
            int r2 = r10 + 1
            r11 = 0
            goto L_0x01e3
        L_0x0255:
            r11 = 0
        L_0x0256:
            r2 = r3
            r8 = r2
            r9 = r8
        L_0x0259:
            if (r2 >= r12) goto L_0x03c7
            float r10 = r0.yLine
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            if (r13 != 0) goto L_0x0307
            if (r11 != 0) goto L_0x026c
            r0.listIdx = r3
            java.util.LinkedList<com.itextpdf.text.Element> r2 = r0.compositeElements
            r2.removeFirst()
            goto L_0x03c7
        L_0x026c:
            com.itextpdf.text.pdf.ColumnText r9 = new com.itextpdf.text.pdf.ColumnText
            com.itextpdf.text.pdf.PdfContentByte r13 = r0.canvas
            r9.<init>(r13)
            r0.compositeColumn = r9
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            if (r7 != 0) goto L_0x0281
            float r13 = r0.descender
            r16 = 0
            int r13 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r13 != 0) goto L_0x0288
        L_0x0281:
            boolean r13 = r0.adjustFirstLine
            if (r13 == 0) goto L_0x0288
            boolean r13 = r0.useAscender
            goto L_0x0289
        L_0x0288:
            r13 = r3
        L_0x0289:
            r9.setUseAscender(r13)
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            boolean r13 = r0.inheritGraphicState
            r9.setInheritGraphicState(r13)
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            int r13 = r11.getAlignment()
            r9.setAlignment(r13)
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r11.getIndentationLeft()
            float r13 = r13 + r15
            float r17 = r11.getFirstLineIndent()
            float r13 = r13 + r17
            r9.setIndent(r13, r3)
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r11.getExtraParagraphSpace()
            r9.setExtraParagraphSpace(r13)
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            float r13 = r13.getIndent()
            r9.setFollowingIndent(r13)
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r11.getIndentationRight()
            float r17 = r14.getIndentationRight()
            float r13 = r13 + r17
            r9.setRightIndent(r13)
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r11.getLeading()
            float r5 = r11.getMultipliedLeading()
            r9.setLeading(r13, r5)
            com.itextpdf.text.pdf.ColumnText r5 = r0.compositeColumn
            int r9 = r0.runDirection
            r5.setRunDirection(r9)
            com.itextpdf.text.pdf.ColumnText r5 = r0.compositeColumn
            int r9 = r0.arabicOptions
            r5.setArabicOptions(r9)
            com.itextpdf.text.pdf.ColumnText r5 = r0.compositeColumn
            float r9 = r0.spaceCharRatio
            r5.setSpaceCharRatio(r9)
            com.itextpdf.text.pdf.ColumnText r5 = r0.compositeColumn
            r5.addText((com.itextpdf.text.Phrase) r11)
            if (r7 == 0) goto L_0x02fc
            boolean r5 = r0.adjustFirstLine
            if (r5 != 0) goto L_0x0305
        L_0x02fc:
            float r5 = r0.yLine
            float r9 = r11.getSpacingBefore()
            float r5 = r5 - r9
            r0.yLine = r5
        L_0x0305:
            r5 = r6
            goto L_0x0308
        L_0x0307:
            r5 = r3
        L_0x0308:
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r0.leftX
            r9.leftX = r13
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r0.rightX
            r9.rightX = r13
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r0.yLine
            r9.yLine = r13
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r0.rectangularWidth
            r9.rectangularWidth = r13
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            boolean r13 = r0.rectangularMode
            r9.rectangularMode = r13
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r0.minY
            r9.minY = r13
            com.itextpdf.text.pdf.ColumnText r9 = r0.compositeColumn
            float r13 = r0.maxY
            r9.maxY = r13
            boolean r9 = r11.getKeepTogether()
            if (r9 == 0) goto L_0x0342
            if (r5 == 0) goto L_0x0342
            if (r7 == 0) goto L_0x0340
            boolean r5 = r0.adjustFirstLine
            if (r5 != 0) goto L_0x0342
        L_0x0340:
            r5 = r6
            goto L_0x0343
        L_0x0342:
            r5 = r3
        L_0x0343:
            if (r1 != 0) goto L_0x034c
            if (r5 == 0) goto L_0x034a
            if (r2 != 0) goto L_0x034a
            goto L_0x034c
        L_0x034a:
            r9 = r3
            goto L_0x034d
        L_0x034c:
            r9 = r6
        L_0x034d:
            com.itextpdf.text.pdf.PdfContentByte r13 = r0.canvas
            boolean r13 = isTagged(r13)
            if (r13 == 0) goto L_0x0378
            if (r9 != 0) goto L_0x0378
            com.itextpdf.text.ListLabel r13 = r11.getListLabel()
            r13.setIndentation(r15)
            com.itextpdf.text.ListItem r13 = r14.getFirstItem()
            if (r13 == r11) goto L_0x036e
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            if (r13 == 0) goto L_0x0373
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            com.itextpdf.text.pdf.BidiLine r13 = r13.bidiLine
            if (r13 == 0) goto L_0x0373
        L_0x036e:
            com.itextpdf.text.pdf.PdfContentByte r13 = r0.canvas
            r13.openMCBlock(r14)
        L_0x0373:
            com.itextpdf.text.pdf.PdfContentByte r13 = r0.canvas
            r13.openMCBlock(r11)
        L_0x0378:
            com.itextpdf.text.pdf.ColumnText r13 = r0.compositeColumn
            int r13 = r13.go(r9, r11)
            com.itextpdf.text.pdf.PdfContentByte r3 = r0.canvas
            boolean r3 = isTagged(r3)
            if (r3 == 0) goto L_0x0396
            if (r9 != 0) goto L_0x0396
            com.itextpdf.text.pdf.PdfContentByte r3 = r0.canvas
            com.itextpdf.text.ListBody r9 = r11.getListBody()
            r3.closeMCBlock(r9)
            com.itextpdf.text.pdf.PdfContentByte r3 = r0.canvas
            r3.closeMCBlock(r11)
        L_0x0396:
            com.itextpdf.text.pdf.ColumnText r3 = r0.compositeColumn
            float r3 = r3.getLastX()
            r0.lastX = r3
            com.itextpdf.text.pdf.ColumnText r3 = r0.compositeColumn
            float r3 = r3.filledWidth
            r0.updateFilledWidth(r3)
            r3 = r13 & 1
            if (r3 != 0) goto L_0x03b2
            if (r5 == 0) goto L_0x03b2
            r3 = 0
            r0.compositeColumn = r3
            r0.yLine = r10
            r8 = r6
            goto L_0x03b3
        L_0x03b2:
            r3 = 0
        L_0x03b3:
            if (r1 != 0) goto L_0x03c8
            if (r5 == 0) goto L_0x03c8
            if (r8 == 0) goto L_0x03ba
            goto L_0x03c8
        L_0x03ba:
            if (r2 != 0) goto L_0x03c0
            r0.compositeColumn = r3
            r0.yLine = r10
        L_0x03c0:
            int r2 = r2 + 1
            r9 = r13
            r3 = 0
            r5 = 3
            goto L_0x0259
        L_0x03c7:
            r13 = r9
        L_0x03c8:
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.canvas
            boolean r2 = isTagged(r2)
            if (r2 == 0) goto L_0x03e7
            if (r1 != 0) goto L_0x03e7
            if (r11 == 0) goto L_0x03e2
            com.itextpdf.text.ListItem r2 = r14.getLastItem()
            if (r2 != r11) goto L_0x03de
            r2 = r13 & 1
            if (r2 != 0) goto L_0x03e2
        L_0x03de:
            r2 = r13 & 2
            if (r2 == 0) goto L_0x03e7
        L_0x03e2:
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.canvas
            r2.closeMCBlock(r14)
        L_0x03e7:
            if (r8 == 0) goto L_0x03ea
            return r12
        L_0x03ea:
            if (r11 != 0) goto L_0x03ee
            goto L_0x04b3
        L_0x03ee:
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            float r2 = r2.yLine
            r0.yLine = r2
            int r2 = r0.linesWritten
            com.itextpdf.text.pdf.ColumnText r3 = r0.compositeColumn
            int r3 = r3.linesWritten
            int r2 = r2 + r3
            r0.linesWritten = r2
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            float r2 = r2.descender
            r0.descender = r2
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            float r2 = r2.currentLeading
            r0.currentLeading = r2
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.canvas
            boolean r2 = isTagged(r2)
            if (r2 != 0) goto L_0x047a
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            float r2 = r2.firstLineY
            boolean r2 = java.lang.Float.isNaN(r2)
            if (r2 != 0) goto L_0x047a
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            boolean r2 = r2.firstLineYDone
            if (r2 != 0) goto L_0x047a
            if (r1 != 0) goto L_0x0476
            if (r4 == 0) goto L_0x0454
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.canvas
            r22 = 2
            com.itextpdf.text.Phrase r3 = new com.itextpdf.text.Phrase
            com.itextpdf.text.Chunk r5 = r11.getListSymbol()
            r3.<init>((com.itextpdf.text.Chunk) r5)
            com.itextpdf.text.pdf.ColumnText r5 = r0.compositeColumn
            float r5 = r5.lastX
            float r7 = r11.getIndentationLeft()
            float r24 = r5 + r7
            com.itextpdf.text.pdf.ColumnText r5 = r0.compositeColumn
            float r5 = r5.firstLineY
            r26 = 0
            int r7 = r0.runDirection
            int r8 = r0.arabicOptions
            r21 = r2
            r23 = r3
            r25 = r5
            r27 = r7
            r28 = r8
            showTextAligned(r21, r22, r23, r24, r25, r26, r27, r28)
            goto L_0x0476
        L_0x0454:
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.canvas
            r28 = 0
            com.itextpdf.text.Phrase r3 = new com.itextpdf.text.Phrase
            com.itextpdf.text.Chunk r5 = r11.getListSymbol()
            r3.<init>((com.itextpdf.text.Chunk) r5)
            com.itextpdf.text.pdf.ColumnText r5 = r0.compositeColumn
            float r5 = r5.leftX
            float r30 = r5 + r15
            com.itextpdf.text.pdf.ColumnText r5 = r0.compositeColumn
            float r5 = r5.firstLineY
            r32 = 0
            r27 = r2
            r29 = r3
            r31 = r5
            showTextAligned(r27, r28, r29, r30, r31, r32)
        L_0x0476:
            com.itextpdf.text.pdf.ColumnText r2 = r0.compositeColumn
            r2.firstLineYDone = r6
        L_0x047a:
            r2 = r13 & 1
            if (r2 == 0) goto L_0x048f
            r2 = 0
            r0.compositeColumn = r2
            int r2 = r0.listIdx
            int r2 = r2 + r6
            r0.listIdx = r2
            float r2 = r0.yLine
            float r3 = r11.getSpacingAfter()
            float r2 = r2 - r3
            r0.yLine = r2
        L_0x048f:
            r2 = r13 & 2
            if (r2 == 0) goto L_0x0494
            return r12
        L_0x0494:
            r2 = 0
            r3 = 0
            r7 = 0
            r15 = 3
            goto L_0x097e
        L_0x049a:
            int r2 = r8.type()
            r3 = 23
            if (r2 != r3) goto L_0x08dd
            com.itextpdf.text.pdf.PdfPTable r8 = (com.itextpdf.text.pdf.PdfPTable) r8
            int r2 = r8.size()
            int r3 = r8.getHeaderRows()
            if (r2 > r3) goto L_0x04b7
            java.util.LinkedList<com.itextpdf.text.Element> r2 = r0.compositeElements
            r2.removeFirst()
        L_0x04b3:
            r33 = r7
            goto L_0x0623
        L_0x04b7:
            float r2 = r0.yLine
            float r3 = r0.descender
            float r2 = r2 + r3
            int r3 = r0.rowIdx
            if (r3 != 0) goto L_0x04c9
            boolean r3 = r0.adjustFirstLine
            if (r3 == 0) goto L_0x04c9
            float r3 = r8.spacingBefore()
            float r2 = r2 - r3
        L_0x04c9:
            r27 = r2
            float r2 = r0.minY
            int r2 = (r27 > r2 ? 1 : (r27 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x08db
            float r2 = r0.maxY
            int r2 = (r27 > r2 ? 1 : (r27 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x04d9
            goto L_0x08db
        L_0x04d9:
            float r2 = r0.leftX
            r3 = 0
            r0.currentLeading = r3
            boolean r3 = r8.isLockedWidth()
            if (r3 == 0) goto L_0x04ec
            float r3 = r8.getTotalWidth()
            r0.updateFilledWidth(r3)
            goto L_0x04f9
        L_0x04ec:
            float r3 = r0.rectangularWidth
            float r5 = r8.getWidthPercentage()
            float r3 = r3 * r5
            r5 = 1120403456(0x42c80000, float:100.0)
            float r3 = r3 / r5
            r8.setTotalWidth((float) r3)
        L_0x04f9:
            r8.normalizeHeadersFooters()
            int r5 = r8.getHeaderRows()
            int r9 = r8.getFooterRows()
            int r10 = r5 - r9
            float r11 = r8.getFooterHeight()
            float r13 = r8.getHeaderHeight()
            float r13 = r13 - r11
            boolean r14 = r8.isSkipFirstHeader()
            if (r14 == 0) goto L_0x0525
            int r14 = r0.rowIdx
            if (r14 > r10) goto L_0x0525
            boolean r14 = r8.isComplete()
            if (r14 != 0) goto L_0x0523
            int r14 = r0.rowIdx
            if (r14 == r10) goto L_0x0525
        L_0x0523:
            r14 = r6
            goto L_0x0526
        L_0x0525:
            r14 = 0
        L_0x0526:
            if (r14 != 0) goto L_0x052b
            float r13 = r27 - r13
            goto L_0x052d
        L_0x052b:
            r13 = r27
        L_0x052d:
            int r15 = r0.rowIdx
            if (r15 >= r5) goto L_0x0533
            r0.rowIdx = r5
        L_0x0533:
            boolean r15 = r8.isSkipLastFooter()
            if (r15 == 0) goto L_0x0544
            float r15 = r0.minY
            float r15 = r13 - r15
            int r12 = r0.rowIdx
            com.itextpdf.text.pdf.PdfPTable$FittingRows r12 = r8.getFittingRows(r15, r12)
            goto L_0x0545
        L_0x0544:
            r12 = 0
        L_0x0545:
            boolean r15 = r8.isSkipLastFooter()
            if (r15 == 0) goto L_0x0558
            int r15 = r12.lastRow
            int r21 = r8.size()
            r33 = r7
            int r7 = r21 + -1
            if (r15 >= r7) goto L_0x0565
            goto L_0x055a
        L_0x0558:
            r33 = r7
        L_0x055a:
            float r13 = r13 - r11
            float r7 = r0.minY
            float r7 = r13 - r7
            int r12 = r0.rowIdx
            com.itextpdf.text.pdf.PdfPTable$FittingRows r12 = r8.getFittingRows(r7, r12)
        L_0x0565:
            float r7 = r0.minY
            int r7 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x08d9
            float r7 = r0.maxY
            int r7 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r7 <= 0) goto L_0x0573
            goto L_0x08d9
        L_0x0573:
            int r7 = r12.lastRow
            int r7 = r7 + r6
            float r15 = r12.height
            float r13 = r13 - r15
            com.itextpdf.text.log.Logger r15 = r0.LOGGER
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r34 = r13
            java.lang.String r13 = "Want to split at row "
            r6.append(r13)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r15.info(r6)
            r6 = r7
        L_0x0592:
            int r13 = r0.rowIdx
            if (r6 <= r13) goto L_0x05a9
            int r13 = r8.size()
            if (r6 >= r13) goto L_0x05a9
            com.itextpdf.text.pdf.PdfPRow r13 = r8.getRow(r6)
            boolean r13 = r13.isMayNotBreak()
            if (r13 == 0) goto L_0x05a9
            int r6 = r6 + -1
            goto L_0x0592
        L_0x05a9:
            int r13 = r8.size()
            r15 = 1
            int r13 = r13 - r15
            if (r6 >= r13) goto L_0x05bd
            com.itextpdf.text.pdf.PdfPRow r13 = r8.getRow(r6)
            boolean r13 = r13.isMayNotBreak()
            if (r13 != 0) goto L_0x05bd
            int r6 = r6 + 1
        L_0x05bd:
            int r13 = r0.rowIdx
            if (r6 <= r13) goto L_0x05c3
            if (r6 < r7) goto L_0x05d5
        L_0x05c3:
            if (r6 != r5) goto L_0x05dc
            com.itextpdf.text.pdf.PdfPRow r13 = r8.getRow(r5)
            boolean r13 = r13.isMayNotBreak()
            if (r13 == 0) goto L_0x05dc
            boolean r13 = r8.isLoopCheck()
            if (r13 == 0) goto L_0x05dc
        L_0x05d5:
            float r13 = r0.minY
            r7 = 0
            r8.setLoopCheck(r7)
            goto L_0x05df
        L_0x05dc:
            r6 = r7
            r13 = r34
        L_0x05df:
            com.itextpdf.text.log.Logger r7 = r0.LOGGER
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r35 = r5
            java.lang.String r5 = "Will split at row "
            r15.append(r5)
            r15.append(r6)
            java.lang.String r5 = r15.toString()
            r7.info(r5)
            boolean r5 = r8.isSplitLate()
            if (r5 == 0) goto L_0x0604
            if (r6 <= 0) goto L_0x0604
            int r5 = r6 + -1
            r12.correctLastRowChosen(r8, r5)
        L_0x0604:
            boolean r5 = r8.isComplete()
            if (r5 != 0) goto L_0x060b
            float r13 = r13 + r11
        L_0x060b:
            boolean r5 = r8.isSplitRows()
            if (r5 != 0) goto L_0x0640
            r5 = -1
            r0.splittedRow = r5
            int r5 = r0.rowIdx
            if (r6 != r5) goto L_0x06cf
            int r2 = r8.size()
            if (r6 != r2) goto L_0x062b
            java.util.LinkedList<com.itextpdf.text.Element> r2 = r0.compositeElements
            r2.removeFirst()
        L_0x0623:
            r7 = r33
            r2 = 0
            r3 = 0
            r5 = 3
            r6 = 1
            goto L_0x002e
        L_0x062b:
            boolean r1 = r8.isComplete()
            if (r1 != 0) goto L_0x0637
            r1 = 1
            if (r6 == r1) goto L_0x0635
            goto L_0x0637
        L_0x0635:
            r1 = 2
            goto L_0x063f
        L_0x0637:
            java.util.ArrayList r1 = r8.getRows()
            r1.remove(r6)
            goto L_0x0635
        L_0x063f:
            return r1
        L_0x0640:
            boolean r5 = r8.isSplitLate()
            if (r5 == 0) goto L_0x065f
            int r5 = r0.rowIdx
            if (r5 < r6) goto L_0x065b
            int r5 = r0.splittedRow
            r7 = -2
            if (r5 != r7) goto L_0x065f
            int r5 = r8.getHeaderRows()
            if (r5 == 0) goto L_0x065b
            boolean r5 = r8.isSkipFirstHeader()
            if (r5 == 0) goto L_0x065f
        L_0x065b:
            r5 = -1
            r0.splittedRow = r5
            goto L_0x06cf
        L_0x065f:
            int r5 = r8.size()
            if (r6 >= r5) goto L_0x06cf
            float r5 = r12.completedRowsHeight
            float r7 = r12.height
            float r5 = r5 - r7
            float r13 = r13 - r5
            float r5 = r0.minY
            float r5 = r13 - r5
            com.itextpdf.text.pdf.PdfPRow r7 = r8.getRow(r6)
            com.itextpdf.text.pdf.PdfPRow r5 = r7.splitRow(r8, r6, r5)
            if (r5 != 0) goto L_0x0689
            com.itextpdf.text.log.Logger r5 = r0.LOGGER
            java.lang.String r7 = "Didn't split row!"
            r5.info(r7)
            r5 = -1
            r0.splittedRow = r5
            int r5 = r0.rowIdx
            if (r5 != r6) goto L_0x06cf
            r5 = 2
            return r5
        L_0x0689:
            int r7 = r0.splittedRow
            if (r6 == r7) goto L_0x06ae
            int r7 = r6 + 1
            r0.splittedRow = r7
            com.itextpdf.text.pdf.PdfPTable r7 = new com.itextpdf.text.pdf.PdfPTable
            r7.<init>((com.itextpdf.text.pdf.PdfPTable) r8)
            java.util.LinkedList<com.itextpdf.text.Element> r8 = r0.compositeElements
            r12 = 0
            r8.set(r12, r7)
            java.util.ArrayList r8 = r7.getRows()
            r12 = r35
        L_0x06a2:
            int r13 = r0.rowIdx
            if (r12 >= r13) goto L_0x06ad
            r13 = 0
            r8.set(r12, r13)
            int r12 = r12 + 1
            goto L_0x06a2
        L_0x06ad:
            r8 = r7
        L_0x06ae:
            float r13 = r0.minY
            java.util.ArrayList r7 = r8.getRows()
            int r6 = r6 + 1
            r7.add(r6, r5)
            com.itextpdf.text.log.Logger r5 = r0.LOGGER
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r12 = "Inserting row at position "
            r7.append(r12)
            r7.append(r6)
            java.lang.String r7 = r7.toString()
            r5.info(r7)
        L_0x06cf:
            if (r1 != 0) goto L_0x0854
            int r5 = r8.getHorizontalAlignment()
            switch(r5) {
                case 1: goto L_0x06e6;
                case 2: goto L_0x06df;
                default: goto L_0x06d8;
            }
        L_0x06d8:
            if (r4 == 0) goto L_0x06ed
            float r5 = r0.rectangularWidth
            float r5 = r5 - r3
            float r2 = r2 + r5
            goto L_0x06ed
        L_0x06df:
            if (r4 != 0) goto L_0x06ed
            float r5 = r0.rectangularWidth
            float r5 = r5 - r3
            float r2 = r2 + r5
            goto L_0x06ed
        L_0x06e6:
            float r5 = r0.rectangularWidth
            float r5 = r5 - r3
            r3 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 / r3
            float r2 = r2 + r5
        L_0x06ed:
            r26 = r2
            com.itextpdf.text.pdf.PdfPTable r2 = com.itextpdf.text.pdf.PdfPTable.shallowCopy(r8)
            java.util.ArrayList r3 = r2.getRows()
            if (r14 != 0) goto L_0x0712
            if (r10 <= 0) goto L_0x0712
            r5 = 0
            java.util.ArrayList r7 = r8.getRows(r5, r10)
            com.itextpdf.text.pdf.PdfContentByte r5 = r0.canvas
            boolean r5 = isTagged(r5)
            if (r5 == 0) goto L_0x070e
            com.itextpdf.text.pdf.PdfPTableHeader r5 = r2.getHeader()
            r5.rows = r7
        L_0x070e:
            r3.addAll(r7)
            goto L_0x0715
        L_0x0712:
            r2.setHeaderRows(r9)
        L_0x0715:
            int r5 = r0.rowIdx
            java.util.ArrayList r5 = r8.getRows(r5, r6)
            com.itextpdf.text.pdf.PdfContentByte r7 = r0.canvas
            boolean r7 = isTagged(r7)
            if (r7 == 0) goto L_0x0729
            com.itextpdf.text.pdf.PdfPTableBody r7 = r2.getBody()
            r7.rows = r5
        L_0x0729:
            r3.addAll(r5)
            boolean r5 = r8.isSkipLastFooter()
            r7 = 1
            r5 = r5 ^ r7
            int r12 = r8.size()
            if (r6 >= r12) goto L_0x073e
            r2.setComplete(r7)
            r5 = 1
            r7 = 1
            goto L_0x073f
        L_0x073e:
            r7 = 0
        L_0x073f:
            if (r9 <= 0) goto L_0x0761
            boolean r12 = r2.isComplete()
            if (r12 == 0) goto L_0x0761
            if (r5 == 0) goto L_0x0761
            int r5 = r10 + r9
            java.util.ArrayList r5 = r8.getRows(r10, r5)
            com.itextpdf.text.pdf.PdfContentByte r10 = r0.canvas
            boolean r10 = isTagged(r10)
            if (r10 == 0) goto L_0x075d
            com.itextpdf.text.pdf.PdfPTableFooter r10 = r2.getFooter()
            r10.rows = r5
        L_0x075d:
            r3.addAll(r5)
            goto L_0x0762
        L_0x0761:
            r9 = 0
        L_0x0762:
            int r5 = r3.size()
            if (r5 <= 0) goto L_0x0852
            int r5 = r3.size()
            r10 = 1
            int r5 = r5 - r10
            int r5 = r5 - r9
            java.lang.Object r3 = r3.get(r5)
            com.itextpdf.text.pdf.PdfPRow r3 = (com.itextpdf.text.pdf.PdfPRow) r3
            boolean r9 = r8.isExtendLastRow(r7)
            if (r9 == 0) goto L_0x0789
            float r9 = r3.getMaxHeights()
            float r12 = r0.minY
            float r13 = r13 - r12
            float r13 = r13 + r9
            r3.setMaxHeights(r13)
            float r13 = r0.minY
            goto L_0x078a
        L_0x0789:
            r9 = 0
        L_0x078a:
            if (r7 == 0) goto L_0x0799
            com.itextpdf.text.pdf.PdfPTableEvent r12 = r8.getTableEvent()
            boolean r15 = r12 instanceof com.itextpdf.text.pdf.PdfPTableEventSplit
            if (r15 == 0) goto L_0x0799
            com.itextpdf.text.pdf.PdfPTableEventSplit r12 = (com.itextpdf.text.pdf.PdfPTableEventSplit) r12
            r12.splitTable(r8)
        L_0x0799:
            com.itextpdf.text.pdf.PdfContentByte[] r12 = r0.canvases
            if (r12 == 0) goto L_0x07d5
            com.itextpdf.text.pdf.PdfContentByte[] r12 = r0.canvases
            r15 = 3
            r12 = r12[r15]
            boolean r12 = isTagged(r12)
            if (r12 == 0) goto L_0x07af
            com.itextpdf.text.pdf.PdfContentByte[] r12 = r0.canvases
            r12 = r12[r15]
            r12.openMCBlock(r8)
        L_0x07af:
            r22 = 0
            r23 = -1
            r24 = 0
            r25 = -1
            com.itextpdf.text.pdf.PdfContentByte[] r12 = r0.canvases
            r29 = 0
            r21 = r2
            r28 = r12
            r21.writeSelectedRows((int) r22, (int) r23, (int) r24, (int) r25, (float) r26, (float) r27, (com.itextpdf.text.pdf.PdfContentByte[]) r28, (boolean) r29)
            com.itextpdf.text.pdf.PdfContentByte[] r12 = r0.canvases
            r15 = 3
            r12 = r12[r15]
            boolean r12 = isTagged(r12)
            if (r12 == 0) goto L_0x0803
            com.itextpdf.text.pdf.PdfContentByte[] r12 = r0.canvases
            r12 = r12[r15]
            r12.closeMCBlock(r8)
            goto L_0x0803
        L_0x07d5:
            r15 = 3
            com.itextpdf.text.pdf.PdfContentByte r12 = r0.canvas
            boolean r12 = isTagged(r12)
            if (r12 == 0) goto L_0x07e3
            com.itextpdf.text.pdf.PdfContentByte r12 = r0.canvas
            r12.openMCBlock(r8)
        L_0x07e3:
            r22 = 0
            r23 = -1
            r24 = 0
            r25 = -1
            com.itextpdf.text.pdf.PdfContentByte r12 = r0.canvas
            r29 = 0
            r21 = r2
            r28 = r12
            r21.writeSelectedRows((int) r22, (int) r23, (int) r24, (int) r25, (float) r26, (float) r27, (com.itextpdf.text.pdf.PdfContentByte) r28, (boolean) r29)
            com.itextpdf.text.pdf.PdfContentByte r12 = r0.canvas
            boolean r12 = isTagged(r12)
            if (r12 == 0) goto L_0x0803
            com.itextpdf.text.pdf.PdfContentByte r12 = r0.canvas
            r12.closeMCBlock(r8)
        L_0x0803:
            boolean r12 = r8.isComplete()
            if (r12 != 0) goto L_0x080c
            r8.addNumberOfRowsWritten(r6)
        L_0x080c:
            int r12 = r0.splittedRow
            if (r12 != r6) goto L_0x0824
            int r12 = r8.size()
            if (r6 >= r12) goto L_0x0824
            java.util.ArrayList r12 = r8.getRows()
            java.lang.Object r12 = r12.get(r6)
            com.itextpdf.text.pdf.PdfPRow r12 = (com.itextpdf.text.pdf.PdfPRow) r12
            r12.copyRowContent(r2, r5)
            goto L_0x0835
        L_0x0824:
            if (r6 <= 0) goto L_0x0835
            int r12 = r8.size()
            if (r6 >= r12) goto L_0x0835
            com.itextpdf.text.pdf.PdfPRow r12 = r8.getRow(r6)
            int r10 = r6 + -1
            r12.splitRowspans(r8, r10, r2, r5)
        L_0x0835:
            boolean r2 = r8.isExtendLastRow(r7)
            if (r2 == 0) goto L_0x083e
            r3.setMaxHeights(r9)
        L_0x083e:
            if (r7 == 0) goto L_0x0865
            com.itextpdf.text.pdf.PdfPTableEvent r2 = r8.getTableEvent()
            boolean r3 = r2 instanceof com.itextpdf.text.pdf.PdfPTableEventAfterSplit
            if (r3 == 0) goto L_0x0865
            com.itextpdf.text.pdf.PdfPRow r3 = r8.getRow(r6)
            com.itextpdf.text.pdf.PdfPTableEventAfterSplit r2 = (com.itextpdf.text.pdf.PdfPTableEventAfterSplit) r2
            r2.afterSplitTable(r8, r3, r6)
            goto L_0x0865
        L_0x0852:
            r15 = 3
            goto L_0x0865
        L_0x0854:
            r15 = 3
            boolean r2 = r8.isExtendLastRow()
            if (r2 == 0) goto L_0x0865
            float r2 = r0.minY
            r3 = -830472192(0xffffffffce800000, float:-1.07374182E9)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x0865
            float r13 = r0.minY
        L_0x0865:
            r0.yLine = r13
            r2 = 0
            r0.descender = r2
            r0.currentLeading = r2
            if (r14 != 0) goto L_0x0879
            boolean r2 = r8.isComplete()
            if (r2 != 0) goto L_0x0879
            float r2 = r0.yLine
            float r2 = r2 + r11
            r0.yLine = r2
        L_0x0879:
            int r2 = r8.size()
            if (r6 >= r2) goto L_0x0892
            float r2 = r8.getRowHeight(r6)
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0892
            boolean r2 = r8.hasRowspan(r6)
            if (r2 == 0) goto L_0x088f
            goto L_0x0892
        L_0x088f:
            int r6 = r6 + 1
            goto L_0x0879
        L_0x0892:
            int r2 = r8.size()
            if (r6 < r2) goto L_0x08c1
            float r2 = r0.yLine
            float r3 = r8.spacingAfter()
            float r2 = r2 - r3
            float r3 = r0.minY
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x08aa
            float r2 = r0.minY
            r0.yLine = r2
            goto L_0x08b3
        L_0x08aa:
            float r2 = r0.yLine
            float r3 = r8.spacingAfter()
            float r2 = r2 - r3
            r0.yLine = r2
        L_0x08b3:
            java.util.LinkedList<com.itextpdf.text.Element> r2 = r0.compositeElements
            r2.removeFirst()
            r2 = -1
            r0.splittedRow = r2
            r2 = 0
            r0.rowIdx = r2
            r7 = r2
            goto L_0x01c4
        L_0x08c1:
            r2 = -1
            int r1 = r0.splittedRow
            if (r1 <= r2) goto L_0x08d5
            java.util.ArrayList r1 = r8.getRows()
            int r2 = r0.rowIdx
        L_0x08cc:
            if (r2 >= r6) goto L_0x08d5
            r3 = 0
            r1.set(r2, r3)
            int r2 = r2 + 1
            goto L_0x08cc
        L_0x08d5:
            r0.rowIdx = r6
            r1 = 2
            return r1
        L_0x08d9:
            r1 = 2
            return r1
        L_0x08db:
            r1 = r12
            return r1
        L_0x08dd:
            r33 = r7
            r2 = 0
            r3 = 0
            r15 = 3
            int r5 = r8.type()
            r6 = 55
            if (r5 != r6) goto L_0x0912
            if (r1 != 0) goto L_0x090b
            r17 = r8
            com.itextpdf.text.pdf.draw.DrawInterface r17 = (com.itextpdf.text.pdf.draw.DrawInterface) r17
            com.itextpdf.text.pdf.PdfContentByte r3 = r0.canvas
            float r5 = r0.leftX
            float r6 = r0.minY
            float r7 = r0.rightX
            float r8 = r0.maxY
            float r9 = r0.yLine
            r18 = r3
            r19 = r5
            r20 = r6
            r21 = r7
            r22 = r8
            r23 = r9
            r17.draw(r18, r19, r20, r21, r22, r23)
        L_0x090b:
            java.util.LinkedList<com.itextpdf.text.Element> r3 = r0.compositeElements
            r3.removeFirst()
            r3 = 0
            goto L_0x097c
        L_0x0912:
            int r5 = r8.type()
            r6 = 37
            if (r5 != r6) goto L_0x0976
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
        L_0x091f:
            r5.add(r8)
            java.util.LinkedList<com.itextpdf.text.Element> r7 = r0.compositeElements
            r7.removeFirst()
            java.util.LinkedList<com.itextpdf.text.Element> r7 = r0.compositeElements
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L_0x0939
            java.util.LinkedList<com.itextpdf.text.Element> r7 = r0.compositeElements
            java.lang.Object r7 = r7.getFirst()
            com.itextpdf.text.Element r7 = (com.itextpdf.text.Element) r7
            r8 = r7
            goto L_0x093a
        L_0x0939:
            r8 = r3
        L_0x093a:
            if (r8 == 0) goto L_0x0942
            int r7 = r8.type()
            if (r7 == r6) goto L_0x091f
        L_0x0942:
            com.itextpdf.text.pdf.FloatLayout r3 = new com.itextpdf.text.pdf.FloatLayout
            boolean r6 = r0.useAscender
            r3.<init>(r5, r6)
            float r6 = r0.leftX
            float r7 = r0.minY
            float r8 = r0.rightX
            float r9 = r0.yLine
            r3.setSimpleColumn(r6, r7, r8, r9)
            com.itextpdf.text.pdf.ColumnText r6 = r3.compositeColumn
            boolean r7 = r37.isIgnoreSpacingBefore()
            r6.setIgnoreSpacingBefore(r7)
            com.itextpdf.text.pdf.PdfContentByte r6 = r0.canvas
            int r6 = r3.layout(r6, r1)
            float r3 = r3.getYLine()
            r0.yLine = r3
            r3 = 0
            r0.descender = r3
            r7 = r6 & 1
            if (r7 != 0) goto L_0x097c
            java.util.LinkedList<com.itextpdf.text.Element> r1 = r0.compositeElements
            r1.addAll(r5)
            return r6
        L_0x0976:
            r3 = 0
            java.util.LinkedList<com.itextpdf.text.Element> r5 = r0.compositeElements
            r5.removeFirst()
        L_0x097c:
            r7 = r33
        L_0x097e:
            r5 = r15
            r6 = 1
            r36 = r3
            r3 = r2
            r2 = r36
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.ColumnText.goComposite(boolean):int");
    }

    public PdfContentByte getCanvas() {
        return this.canvas;
    }

    public void setCanvas(PdfContentByte pdfContentByte) {
        this.canvas = pdfContentByte;
        this.canvases = null;
        if (this.compositeColumn != null) {
            this.compositeColumn.setCanvas(pdfContentByte);
        }
    }

    public void setCanvases(PdfContentByte[] pdfContentByteArr) {
        this.canvases = pdfContentByteArr;
        this.canvas = pdfContentByteArr[3];
        if (this.compositeColumn != null) {
            this.compositeColumn.setCanvases(pdfContentByteArr);
        }
    }

    public PdfContentByte[] getCanvases() {
        return this.canvases;
    }

    public boolean zeroHeightElement() {
        return this.composite && !this.compositeElements.isEmpty() && this.compositeElements.getFirst().type() == 55;
    }

    public List<Element> getCompositeElements() {
        return this.compositeElements;
    }

    public boolean isUseAscender() {
        return this.useAscender;
    }

    public void setUseAscender(boolean z) {
        this.useAscender = z;
    }

    public float getFilledWidth() {
        return this.filledWidth;
    }

    public void setFilledWidth(float f) {
        this.filledWidth = f;
    }

    public void updateFilledWidth(float f) {
        if (f > this.filledWidth) {
            this.filledWidth = f;
        }
    }

    public boolean isAdjustFirstLine() {
        return this.adjustFirstLine;
    }

    public void setAdjustFirstLine(boolean z) {
        this.adjustFirstLine = z;
    }

    private static boolean isTagged(PdfContentByte pdfContentByte) {
        return (pdfContentByte == null || pdfContentByte.pdf == null || pdfContentByte.writer == null || !pdfContentByte.writer.isTagged()) ? false : true;
    }
}
