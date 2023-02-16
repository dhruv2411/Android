package com.itextpdf.text.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.BaseFont;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class TrueTypeFont extends BaseFont {
    static final String[] codePages = {"1252 Latin 1", "1250 Latin 2: Eastern Europe", "1251 Cyrillic", "1253 Greek", "1254 Turkish", "1255 Hebrew", "1256 Arabic", "1257 Windows Baltic", "1258 Vietnamese", null, null, null, null, null, null, null, "874 Thai", "932 JIS/Japan", "936 Chinese: Simplified chars--PRC and Singapore", "949 Korean Wansung", "950 Chinese: Traditional chars--Taiwan and Hong Kong", "1361 Korean Johab", null, null, null, null, null, null, null, "Macintosh Character Set (US Roman)", "OEM Character Set", "Symbol Character Set", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "869 IBM Greek", "866 MS-DOS Russian", "865 MS-DOS Nordic", "864 Arabic", "863 MS-DOS Canadian French", "862 Hebrew", "861 MS-DOS Icelandic", "860 MS-DOS Portuguese", "857 IBM Turkish", "855 IBM Cyrillic; primarily Russian", "852 Latin 2", "775 MS-DOS Baltic", "737 Greek; former 437 G", "708 Arabic; ASMO 708", "850 WE/Latin 1", "437 US"};
    protected String[][] allNameEntries;
    protected int[][] bboxes;
    protected boolean cff = false;
    protected int cffLength;
    protected int cffOffset;
    protected HashMap<Integer, int[]> cmap10;
    protected HashMap<Integer, int[]> cmap31;
    protected HashMap<Integer, int[]> cmapExt;
    protected int directoryOffset;
    protected String[][] familyName;
    protected String fileName;
    protected String fontName;
    protected String[][] fullName;
    protected int[] glyphIdToChar;
    protected int[] glyphWidthsByIndex;
    protected FontHeader head = new FontHeader();
    protected HorizontalHeader hhea = new HorizontalHeader();
    protected boolean isFixedPitch = false;
    protected double italicAngle;
    protected boolean justNames = false;
    protected IntHashtable kerning = new IntHashtable();
    protected int maxGlyphId;
    protected WindowsMetrics os_2 = new WindowsMetrics();
    protected RandomAccessFileOrArray rf;
    protected String style = "";
    protected String[][] subFamily;
    protected HashMap<String, int[]> tables;
    protected String ttcIndex;
    protected int underlinePosition;
    protected int underlineThickness;

    protected static class FontHeader {
        int flags;
        int macStyle;
        int unitsPerEm;
        short xMax;
        short xMin;
        short yMax;
        short yMin;

        protected FontHeader() {
        }
    }

    protected static class HorizontalHeader {
        short Ascender;
        short Descender;
        short LineGap;
        int advanceWidthMax;
        short caretSlopeRise;
        short caretSlopeRun;
        short minLeftSideBearing;
        short minRightSideBearing;
        int numberOfHMetrics;
        short xMaxExtent;

        protected HorizontalHeader() {
        }
    }

    protected static class WindowsMetrics {
        byte[] achVendID = new byte[4];
        int fsSelection;
        short fsType;
        byte[] panose = new byte[10];
        int sCapHeight;
        short sFamilyClass;
        short sTypoAscender;
        short sTypoDescender;
        short sTypoLineGap;
        int ulCodePageRange1;
        int ulCodePageRange2;
        int usFirstCharIndex;
        int usLastCharIndex;
        int usWeightClass;
        int usWidthClass;
        int usWinAscent;
        int usWinDescent;
        short xAvgCharWidth;
        short yStrikeoutPosition;
        short yStrikeoutSize;
        short ySubscriptXOffset;
        short ySubscriptXSize;
        short ySubscriptYOffset;
        short ySubscriptYSize;
        short ySuperscriptXOffset;
        short ySuperscriptXSize;
        short ySuperscriptYOffset;
        short ySuperscriptYSize;

        protected WindowsMetrics() {
        }
    }

    protected TrueTypeFont() {
    }

    TrueTypeFont(String str, String str2, boolean z, byte[] bArr, boolean z2, boolean z3) throws DocumentException, IOException {
        this.justNames = z2;
        String baseName = getBaseName(str);
        String tTCName = getTTCName(baseName);
        if (baseName.length() < str.length()) {
            this.style = str.substring(baseName.length());
        }
        this.encoding = str2;
        this.embedded = z;
        this.fileName = tTCName;
        this.fontType = 1;
        this.ttcIndex = "";
        if (tTCName.length() < baseName.length()) {
            this.ttcIndex = baseName.substring(tTCName.length() + 1);
        }
        if (this.fileName.toLowerCase().endsWith(".ttf") || this.fileName.toLowerCase().endsWith(".otf") || this.fileName.toLowerCase().endsWith(".ttc")) {
            process(bArr, z3);
            if (z2 || !this.embedded || this.os_2.fsType != 2) {
                if (!this.encoding.startsWith("#")) {
                    PdfEncodings.convertToBytes(" ", str2);
                }
                createEncoding();
                return;
            }
            throw new DocumentException(MessageLocalization.getComposedMessage("1.cannot.be.embedded.due.to.licensing.restrictions", this.fileName + this.style));
        }
        throw new DocumentException(MessageLocalization.getComposedMessage("1.is.not.a.ttf.otf.or.ttc.font.file", this.fileName + this.style));
    }

    protected static String getTTCName(String str) {
        int indexOf = str.toLowerCase().indexOf(".ttc,");
        if (indexOf < 0) {
            return str;
        }
        return str.substring(0, indexOf + 4);
    }

    /* access modifiers changed from: package-private */
    public void fillTables() throws DocumentException, IOException {
        int[] iArr = this.tables.get(TtmlNode.TAG_HEAD);
        boolean z = true;
        if (iArr == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("table.1.does.not.exist.in.2", TtmlNode.TAG_HEAD, this.fileName + this.style));
        }
        this.rf.seek((long) (iArr[0] + 16));
        this.head.flags = this.rf.readUnsignedShort();
        this.head.unitsPerEm = this.rf.readUnsignedShort();
        this.rf.skipBytes(16);
        this.head.xMin = this.rf.readShort();
        this.head.yMin = this.rf.readShort();
        this.head.xMax = this.rf.readShort();
        this.head.yMax = this.rf.readShort();
        this.head.macStyle = this.rf.readUnsignedShort();
        int[] iArr2 = this.tables.get("hhea");
        if (iArr2 == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("table.1.does.not.exist.in.2", "hhea", this.fileName + this.style));
        }
        this.rf.seek((long) (iArr2[0] + 4));
        this.hhea.Ascender = this.rf.readShort();
        this.hhea.Descender = this.rf.readShort();
        this.hhea.LineGap = this.rf.readShort();
        this.hhea.advanceWidthMax = this.rf.readUnsignedShort();
        this.hhea.minLeftSideBearing = this.rf.readShort();
        this.hhea.minRightSideBearing = this.rf.readShort();
        this.hhea.xMaxExtent = this.rf.readShort();
        this.hhea.caretSlopeRise = this.rf.readShort();
        this.hhea.caretSlopeRun = this.rf.readShort();
        this.rf.skipBytes(12);
        this.hhea.numberOfHMetrics = this.rf.readUnsignedShort();
        int[] iArr3 = this.tables.get("OS/2");
        if (iArr3 != null) {
            this.rf.seek((long) iArr3[0]);
            int readUnsignedShort = this.rf.readUnsignedShort();
            this.os_2.xAvgCharWidth = this.rf.readShort();
            this.os_2.usWeightClass = this.rf.readUnsignedShort();
            this.os_2.usWidthClass = this.rf.readUnsignedShort();
            this.os_2.fsType = this.rf.readShort();
            this.os_2.ySubscriptXSize = this.rf.readShort();
            this.os_2.ySubscriptYSize = this.rf.readShort();
            this.os_2.ySubscriptXOffset = this.rf.readShort();
            this.os_2.ySubscriptYOffset = this.rf.readShort();
            this.os_2.ySuperscriptXSize = this.rf.readShort();
            this.os_2.ySuperscriptYSize = this.rf.readShort();
            this.os_2.ySuperscriptXOffset = this.rf.readShort();
            this.os_2.ySuperscriptYOffset = this.rf.readShort();
            this.os_2.yStrikeoutSize = this.rf.readShort();
            this.os_2.yStrikeoutPosition = this.rf.readShort();
            this.os_2.sFamilyClass = this.rf.readShort();
            this.rf.readFully(this.os_2.panose);
            this.rf.skipBytes(16);
            this.rf.readFully(this.os_2.achVendID);
            this.os_2.fsSelection = this.rf.readUnsignedShort();
            this.os_2.usFirstCharIndex = this.rf.readUnsignedShort();
            this.os_2.usLastCharIndex = this.rf.readUnsignedShort();
            this.os_2.sTypoAscender = this.rf.readShort();
            this.os_2.sTypoDescender = this.rf.readShort();
            if (this.os_2.sTypoDescender > 0) {
                this.os_2.sTypoDescender = (short) (-this.os_2.sTypoDescender);
            }
            this.os_2.sTypoLineGap = this.rf.readShort();
            this.os_2.usWinAscent = this.rf.readUnsignedShort();
            this.os_2.usWinDescent = this.rf.readUnsignedShort();
            this.os_2.ulCodePageRange1 = 0;
            this.os_2.ulCodePageRange2 = 0;
            if (readUnsignedShort > 0) {
                this.os_2.ulCodePageRange1 = this.rf.readInt();
                this.os_2.ulCodePageRange2 = this.rf.readInt();
            }
            if (readUnsignedShort > 1) {
                this.rf.skipBytes(2);
                this.os_2.sCapHeight = this.rf.readShort();
            } else {
                this.os_2.sCapHeight = (int) (0.7d * ((double) this.head.unitsPerEm));
            }
        } else if (!(this.tables.get("hhea") == null || this.tables.get(TtmlNode.TAG_HEAD) == null)) {
            if (this.head.macStyle == 0) {
                this.os_2.usWeightClass = 700;
                this.os_2.usWidthClass = 5;
            } else if (this.head.macStyle == 5) {
                this.os_2.usWeightClass = 400;
                this.os_2.usWidthClass = 3;
            } else if (this.head.macStyle == 6) {
                this.os_2.usWeightClass = 400;
                this.os_2.usWidthClass = 7;
            } else {
                this.os_2.usWeightClass = 400;
                this.os_2.usWidthClass = 5;
            }
            this.os_2.fsType = 0;
            this.os_2.ySubscriptYSize = 0;
            this.os_2.ySubscriptYOffset = 0;
            this.os_2.ySuperscriptYSize = 0;
            this.os_2.ySuperscriptYOffset = 0;
            this.os_2.yStrikeoutSize = 0;
            this.os_2.yStrikeoutPosition = 0;
            this.os_2.sTypoAscender = (short) ((int) (((double) this.hhea.Ascender) - (0.21d * ((double) this.hhea.Ascender))));
            this.os_2.sTypoDescender = (short) ((int) (-(((double) Math.abs(this.hhea.Descender)) - (((double) Math.abs(this.hhea.Descender)) * 0.07d))));
            this.os_2.sTypoLineGap = (short) (this.hhea.LineGap * 2);
            this.os_2.usWinAscent = this.hhea.Ascender;
            this.os_2.usWinDescent = this.hhea.Descender;
            this.os_2.ulCodePageRange1 = 0;
            this.os_2.ulCodePageRange2 = 0;
            this.os_2.sCapHeight = (int) (0.7d * ((double) this.head.unitsPerEm));
        }
        int[] iArr4 = this.tables.get("post");
        if (iArr4 == null) {
            this.italicAngle = ((-Math.atan2((double) this.hhea.caretSlopeRun, (double) this.hhea.caretSlopeRise)) * 180.0d) / 3.141592653589793d;
        } else {
            this.rf.seek((long) (iArr4[0] + 4));
            this.italicAngle = ((double) this.rf.readShort()) + (((double) this.rf.readUnsignedShort()) / 16384.0d);
            this.underlinePosition = this.rf.readShort();
            this.underlineThickness = this.rf.readShort();
            if (this.rf.readInt() == 0) {
                z = false;
            }
            this.isFixedPitch = z;
        }
        int[] iArr5 = this.tables.get("maxp");
        if (iArr5 == null) {
            this.maxGlyphId = 65536;
            return;
        }
        this.rf.seek((long) (iArr5[0] + 4));
        this.maxGlyphId = this.rf.readUnsignedShort();
    }

    /* access modifiers changed from: package-private */
    public String getBaseFont() throws DocumentException, IOException {
        int[] iArr = this.tables.get("name");
        if (iArr == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("table.1.does.not.exist.in.2", "name", this.fileName + this.style));
        }
        this.rf.seek((long) (iArr[0] + 2));
        int readUnsignedShort = this.rf.readUnsignedShort();
        int readUnsignedShort2 = this.rf.readUnsignedShort();
        for (int i = 0; i < readUnsignedShort; i++) {
            int readUnsignedShort3 = this.rf.readUnsignedShort();
            this.rf.readUnsignedShort();
            this.rf.readUnsignedShort();
            int readUnsignedShort4 = this.rf.readUnsignedShort();
            int readUnsignedShort5 = this.rf.readUnsignedShort();
            int readUnsignedShort6 = this.rf.readUnsignedShort();
            if (readUnsignedShort4 == 6) {
                this.rf.seek((long) (iArr[0] + readUnsignedShort2 + readUnsignedShort6));
                if (readUnsignedShort3 == 0 || readUnsignedShort3 == 3) {
                    return readUnicodeString(readUnsignedShort5);
                }
                return readStandardString(readUnsignedShort5);
            }
        }
        return new File(this.fileName).getName().replace(' ', '-');
    }

    /* access modifiers changed from: package-private */
    public String[][] getNames(int i) throws DocumentException, IOException {
        int i2;
        int i3;
        char c;
        boolean z;
        String str;
        int[] iArr = this.tables.get("name");
        char c2 = 2;
        boolean z2 = true;
        int i4 = 0;
        if (iArr == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("table.1.does.not.exist.in.2", "name", this.fileName + this.style));
        }
        this.rf.seek((long) (iArr[0] + 2));
        int readUnsignedShort = this.rf.readUnsignedShort();
        int readUnsignedShort2 = this.rf.readUnsignedShort();
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        while (i5 < readUnsignedShort) {
            int readUnsignedShort3 = this.rf.readUnsignedShort();
            int readUnsignedShort4 = this.rf.readUnsignedShort();
            int readUnsignedShort5 = this.rf.readUnsignedShort();
            int readUnsignedShort6 = this.rf.readUnsignedShort();
            int readUnsignedShort7 = this.rf.readUnsignedShort();
            int readUnsignedShort8 = this.rf.readUnsignedShort();
            if (readUnsignedShort6 == i) {
                int filePointer = (int) this.rf.getFilePointer();
                i2 = readUnsignedShort;
                this.rf.seek((long) (iArr[i4] + readUnsignedShort2 + readUnsignedShort8));
                if (readUnsignedShort3 == 0 || readUnsignedShort3 == 3 || (readUnsignedShort3 == 2 && readUnsignedShort4 == 1)) {
                    str = readUnicodeString(readUnsignedShort7);
                } else {
                    str = readStandardString(readUnsignedShort7);
                }
                i3 = 0;
                String valueOf = String.valueOf(readUnsignedShort4);
                z = true;
                String valueOf2 = String.valueOf(readUnsignedShort5);
                c = 2;
                arrayList.add(new String[]{String.valueOf(readUnsignedShort3), valueOf, valueOf2, str});
                this.rf.seek((long) filePointer);
            } else {
                c = c2;
                z = z2;
                i3 = i4;
                i2 = readUnsignedShort;
            }
            i5++;
            z2 = z;
            c2 = c;
            i4 = i3;
            readUnsignedShort = i2;
        }
        String[][] strArr = new String[arrayList.size()][];
        for (int i6 = i4; i6 < arrayList.size(); i6++) {
            strArr[i6] = (String[]) arrayList.get(i6);
        }
        return strArr;
    }

    /* access modifiers changed from: package-private */
    public String[][] getAllNames() throws DocumentException, IOException {
        String str;
        int[] iArr = this.tables.get("name");
        if (iArr == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("table.1.does.not.exist.in.2", "name", this.fileName + this.style));
        }
        this.rf.seek((long) (iArr[0] + 2));
        int readUnsignedShort = this.rf.readUnsignedShort();
        int readUnsignedShort2 = this.rf.readUnsignedShort();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readUnsignedShort; i++) {
            int readUnsignedShort3 = this.rf.readUnsignedShort();
            int readUnsignedShort4 = this.rf.readUnsignedShort();
            int readUnsignedShort5 = this.rf.readUnsignedShort();
            int readUnsignedShort6 = this.rf.readUnsignedShort();
            int readUnsignedShort7 = this.rf.readUnsignedShort();
            int readUnsignedShort8 = this.rf.readUnsignedShort();
            int filePointer = (int) this.rf.getFilePointer();
            this.rf.seek((long) (iArr[0] + readUnsignedShort2 + readUnsignedShort8));
            if (readUnsignedShort3 == 0 || readUnsignedShort3 == 3 || (readUnsignedShort3 == 2 && readUnsignedShort4 == 1)) {
                str = readUnicodeString(readUnsignedShort7);
            } else {
                str = readStandardString(readUnsignedShort7);
            }
            arrayList.add(new String[]{String.valueOf(readUnsignedShort6), String.valueOf(readUnsignedShort3), String.valueOf(readUnsignedShort4), String.valueOf(readUnsignedShort5), str});
            this.rf.seek((long) filePointer);
        }
        String[][] strArr = new String[arrayList.size()][];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            strArr[i2] = (String[]) arrayList.get(i2);
        }
        return strArr;
    }

    /* access modifiers changed from: package-private */
    public void checkCff() {
        int[] iArr = this.tables.get("CFF ");
        if (iArr != null) {
            this.cff = true;
            this.cffOffset = iArr[0];
            this.cffLength = iArr[1];
        }
    }

    /* access modifiers changed from: package-private */
    public void process(byte[] bArr, boolean z) throws DocumentException, IOException {
        boolean z2;
        this.tables = new HashMap<>();
        if (bArr == null) {
            this.rf = new RandomAccessFileOrArray(this.fileName, z, Document.plainRandomAccess);
        } else {
            this.rf = new RandomAccessFileOrArray(bArr);
        }
        try {
            if (this.ttcIndex.length() > 0) {
                int parseInt = Integer.parseInt(this.ttcIndex);
                if (parseInt < 0) {
                    throw new DocumentException(MessageLocalization.getComposedMessage("the.font.index.for.1.must.be.positive", this.fileName));
                } else if (!readStandardString(4).equals("ttcf")) {
                    throw new DocumentException(MessageLocalization.getComposedMessage("1.is.not.a.valid.ttc.file", this.fileName));
                } else {
                    this.rf.skipBytes(4);
                    int readInt = this.rf.readInt();
                    if (parseInt >= readInt) {
                        throw new DocumentException(MessageLocalization.getComposedMessage("the.font.index.for.1.must.be.between.0.and.2.it.was.3", this.fileName, String.valueOf(readInt - 1), String.valueOf(parseInt)));
                    } else {
                        this.rf.skipBytes(parseInt * 4);
                        this.directoryOffset = this.rf.readInt();
                    }
                }
            }
            this.rf.seek((long) this.directoryOffset);
            int readInt2 = this.rf.readInt();
            if (readInt2 == 65536 || readInt2 == 1330926671) {
                int readUnsignedShort = this.rf.readUnsignedShort();
                this.rf.skipBytes(6);
                for (int i = 0; i < readUnsignedShort; i++) {
                    String readStandardString = readStandardString(4);
                    this.rf.skipBytes(4);
                    this.tables.put(readStandardString, new int[]{this.rf.readInt(), this.rf.readInt()});
                }
                checkCff();
                this.fontName = getBaseFont();
                this.fullName = getNames(4);
                String[][] names = getNames(16);
                if (names.length > 0) {
                    this.familyName = names;
                } else {
                    this.familyName = getNames(1);
                }
                String[][] names2 = getNames(17);
                if (names.length > 0) {
                    this.subFamily = names2;
                } else {
                    this.subFamily = getNames(2);
                }
                this.allNameEntries = getAllNames();
                if (!this.justNames) {
                    fillTables();
                    readGlyphWidths();
                    readCMaps();
                    readKerning();
                    readBbox();
                }
                if (z2) {
                    return;
                }
                return;
            }
            throw new DocumentException(MessageLocalization.getComposedMessage("1.is.not.a.valid.ttf.or.otf.file", this.fileName));
        } finally {
            if (!this.embedded) {
                this.rf.close();
                this.rf = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public String readStandardString(int i) throws IOException {
        return this.rf.readString(i, "Cp1252");
    }

    /* access modifiers changed from: protected */
    public String readUnicodeString(int i) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = i / 2;
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append(this.rf.readChar());
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public void readGlyphWidths() throws DocumentException, IOException {
        int[] iArr = this.tables.get("hmtx");
        if (iArr == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("table.1.does.not.exist.in.2", "hmtx", this.fileName + this.style));
        }
        this.rf.seek((long) iArr[0]);
        this.glyphWidthsByIndex = new int[this.hhea.numberOfHMetrics];
        for (int i = 0; i < this.hhea.numberOfHMetrics; i++) {
            this.glyphWidthsByIndex[i] = (this.rf.readUnsignedShort() * 1000) / this.head.unitsPerEm;
            int readShort = (this.rf.readShort() * 1000) / this.head.unitsPerEm;
        }
    }

    /* access modifiers changed from: protected */
    public int getGlyphWidth(int i) {
        if (i >= this.glyphWidthsByIndex.length) {
            i = this.glyphWidthsByIndex.length - 1;
        }
        return this.glyphWidthsByIndex[i];
    }

    private void readBbox() throws DocumentException, IOException {
        int[] iArr;
        int[] iArr2 = this.tables.get(TtmlNode.TAG_HEAD);
        if (iArr2 == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("table.1.does.not.exist.in.2", TtmlNode.TAG_HEAD, this.fileName + this.style));
        }
        this.rf.seek((long) (iArr2[0] + 51));
        boolean z = this.rf.readUnsignedShort() == 0;
        int[] iArr3 = this.tables.get("loca");
        if (iArr3 != null) {
            this.rf.seek((long) iArr3[0]);
            if (z) {
                int i = iArr3[1] / 2;
                iArr = new int[i];
                for (int i2 = 0; i2 < i; i2++) {
                    iArr[i2] = this.rf.readUnsignedShort() * 2;
                }
            } else {
                int i3 = iArr3[1] / 4;
                iArr = new int[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    iArr[i4] = this.rf.readInt();
                }
            }
            int[] iArr4 = this.tables.get("glyf");
            if (iArr4 == null) {
                throw new DocumentException(MessageLocalization.getComposedMessage("table.1.does.not.exist.in.2", "glyf", this.fileName + this.style));
            }
            int i5 = iArr4[0];
            this.bboxes = new int[(iArr.length - 1)][];
            int i6 = 0;
            while (i6 < iArr.length - 1) {
                int i7 = iArr[i6];
                int i8 = i6 + 1;
                if (i7 != iArr[i8]) {
                    this.rf.seek((long) (i7 + i5 + 2));
                    this.bboxes[i6] = new int[]{(this.rf.readShort() * 1000) / this.head.unitsPerEm, (this.rf.readShort() * 1000) / this.head.unitsPerEm, (this.rf.readShort() * 1000) / this.head.unitsPerEm, (this.rf.readShort() * 1000) / this.head.unitsPerEm};
                }
                i6 = i8;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void readCMaps() throws DocumentException, IOException {
        int[] iArr = this.tables.get("cmap");
        if (iArr == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("table.1.does.not.exist.in.2", "cmap", this.fileName + this.style));
        }
        this.rf.seek((long) iArr[0]);
        this.rf.skipBytes(2);
        int readUnsignedShort = this.rf.readUnsignedShort();
        this.fontSpecific = false;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < readUnsignedShort; i5++) {
            int readUnsignedShort2 = this.rf.readUnsignedShort();
            int readUnsignedShort3 = this.rf.readUnsignedShort();
            int readInt = this.rf.readInt();
            if (readUnsignedShort2 == 3 && readUnsignedShort3 == 0) {
                this.fontSpecific = true;
                i3 = readInt;
            } else if (readUnsignedShort2 == 3 && readUnsignedShort3 == 1) {
                i2 = readInt;
            } else if (readUnsignedShort2 == 3 && readUnsignedShort3 == 10) {
                i4 = readInt;
            }
            if (readUnsignedShort2 == 1 && readUnsignedShort3 == 0) {
                i = readInt;
            }
        }
        if (i > 0) {
            this.rf.seek((long) (iArr[0] + i));
            int readUnsignedShort4 = this.rf.readUnsignedShort();
            if (readUnsignedShort4 == 0) {
                this.cmap10 = readFormat0();
            } else if (readUnsignedShort4 == 4) {
                this.cmap10 = readFormat4();
            } else if (readUnsignedShort4 == 6) {
                this.cmap10 = readFormat6();
            }
        }
        if (i2 > 0) {
            this.rf.seek((long) (iArr[0] + i2));
            if (this.rf.readUnsignedShort() == 4) {
                this.cmap31 = readFormat4();
            }
        }
        if (i3 > 0) {
            this.rf.seek((long) (iArr[0] + i3));
            if (this.rf.readUnsignedShort() == 4) {
                this.cmap10 = readFormat4();
            }
        }
        if (i4 > 0) {
            this.rf.seek((long) (iArr[0] + i4));
            int readUnsignedShort5 = this.rf.readUnsignedShort();
            if (readUnsignedShort5 == 0) {
                this.cmapExt = readFormat0();
            } else if (readUnsignedShort5 == 4) {
                this.cmapExt = readFormat4();
            } else if (readUnsignedShort5 == 6) {
                this.cmapExt = readFormat6();
            } else if (readUnsignedShort5 == 12) {
                this.cmapExt = readFormat12();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, int[]> readFormat12() throws IOException {
        HashMap<Integer, int[]> hashMap = new HashMap<>();
        this.rf.skipBytes(2);
        this.rf.readInt();
        this.rf.skipBytes(4);
        int readInt = this.rf.readInt();
        for (int i = 0; i < readInt; i++) {
            int readInt2 = this.rf.readInt();
            int readInt3 = this.rf.readInt();
            for (int readInt4 = this.rf.readInt(); readInt4 <= readInt2; readInt4++) {
                int[] iArr = new int[2];
                iArr[0] = readInt3;
                iArr[1] = getGlyphWidth(iArr[0]);
                hashMap.put(Integer.valueOf(readInt4), iArr);
                readInt3++;
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, int[]> readFormat0() throws IOException {
        HashMap<Integer, int[]> hashMap = new HashMap<>();
        this.rf.skipBytes(4);
        for (int i = 0; i < 256; i++) {
            int[] iArr = new int[2];
            iArr[0] = this.rf.readUnsignedByte();
            iArr[1] = getGlyphWidth(iArr[0]);
            hashMap.put(Integer.valueOf(i), iArr);
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, int[]> readFormat4() throws IOException {
        int i;
        HashMap<Integer, int[]> hashMap = new HashMap<>();
        int readUnsignedShort = this.rf.readUnsignedShort();
        this.rf.skipBytes(2);
        int readUnsignedShort2 = this.rf.readUnsignedShort() / 2;
        this.rf.skipBytes(6);
        int[] iArr = new int[readUnsignedShort2];
        for (int i2 = 0; i2 < readUnsignedShort2; i2++) {
            iArr[i2] = this.rf.readUnsignedShort();
        }
        this.rf.skipBytes(2);
        int[] iArr2 = new int[readUnsignedShort2];
        for (int i3 = 0; i3 < readUnsignedShort2; i3++) {
            iArr2[i3] = this.rf.readUnsignedShort();
        }
        int[] iArr3 = new int[readUnsignedShort2];
        for (int i4 = 0; i4 < readUnsignedShort2; i4++) {
            iArr3[i4] = this.rf.readUnsignedShort();
        }
        int[] iArr4 = new int[readUnsignedShort2];
        for (int i5 = 0; i5 < readUnsignedShort2; i5++) {
            iArr4[i5] = this.rf.readUnsignedShort();
        }
        int[] iArr5 = new int[(((readUnsignedShort / 2) - 8) - (readUnsignedShort2 * 4))];
        for (int i6 = 0; i6 < iArr5.length; i6++) {
            iArr5[i6] = this.rf.readUnsignedShort();
        }
        for (int i7 = 0; i7 < readUnsignedShort2; i7++) {
            int i8 = iArr2[i7];
            while (i8 <= iArr[i7] && i8 != 65535) {
                if (iArr4[i7] == 0) {
                    i = 65535 & (iArr3[i7] + i8);
                } else {
                    int i9 = ((((iArr4[i7] / 2) + i7) - readUnsignedShort2) + i8) - iArr2[i7];
                    if (i9 >= iArr5.length) {
                        i8++;
                    } else {
                        i = 65535 & (iArr5[i9] + iArr3[i7]);
                    }
                }
                int[] iArr6 = new int[2];
                iArr6[0] = i;
                iArr6[1] = getGlyphWidth(iArr6[0]);
                hashMap.put(Integer.valueOf((!this.fontSpecific || (65280 & i8) != 61440) ? i8 : i8 & 255), iArr6);
                i8++;
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public HashMap<Integer, int[]> readFormat6() throws IOException {
        HashMap<Integer, int[]> hashMap = new HashMap<>();
        this.rf.skipBytes(4);
        int readUnsignedShort = this.rf.readUnsignedShort();
        int readUnsignedShort2 = this.rf.readUnsignedShort();
        for (int i = 0; i < readUnsignedShort2; i++) {
            int[] iArr = new int[2];
            iArr[0] = this.rf.readUnsignedShort();
            iArr[1] = getGlyphWidth(iArr[0]);
            hashMap.put(Integer.valueOf(i + readUnsignedShort), iArr);
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void readKerning() throws IOException {
        int[] iArr = this.tables.get("kern");
        if (iArr != null) {
            this.rf.seek((long) (iArr[0] + 2));
            int readUnsignedShort = this.rf.readUnsignedShort();
            int i = iArr[0] + 4;
            int i2 = 0;
            for (int i3 = 0; i3 < readUnsignedShort; i3++) {
                i += i2;
                this.rf.seek((long) i);
                this.rf.skipBytes(2);
                i2 = this.rf.readUnsignedShort();
                if ((this.rf.readUnsignedShort() & 65527) == 1) {
                    int readUnsignedShort2 = this.rf.readUnsignedShort();
                    this.rf.skipBytes(6);
                    for (int i4 = 0; i4 < readUnsignedShort2; i4++) {
                        this.kerning.put(this.rf.readInt(), (this.rf.readShort() * 1000) / this.head.unitsPerEm);
                    }
                }
            }
        }
    }

    public int getKerning(int i, int i2) {
        int[] metricsTT = getMetricsTT(i);
        if (metricsTT == null) {
            return 0;
        }
        int i3 = metricsTT[0];
        int[] metricsTT2 = getMetricsTT(i2);
        if (metricsTT2 == null) {
            return 0;
        }
        return this.kerning.get((i3 << 16) + metricsTT2[0]);
    }

    /* access modifiers changed from: package-private */
    public int getRawWidth(int i, String str) {
        int[] metricsTT = getMetricsTT(i);
        if (metricsTT == null) {
            return 0;
        }
        return metricsTT[1];
    }

    /* access modifiers changed from: protected */
    public PdfDictionary getFontDescriptor(PdfIndirectReference pdfIndirectReference, String str, PdfIndirectReference pdfIndirectReference2) {
        PdfDictionary pdfDictionary = new PdfDictionary(PdfName.FONTDESCRIPTOR);
        pdfDictionary.put(PdfName.ASCENT, new PdfNumber((this.os_2.sTypoAscender * 1000) / this.head.unitsPerEm));
        pdfDictionary.put(PdfName.CAPHEIGHT, new PdfNumber((this.os_2.sCapHeight * 1000) / this.head.unitsPerEm));
        pdfDictionary.put(PdfName.DESCENT, new PdfNumber((this.os_2.sTypoDescender * 1000) / this.head.unitsPerEm));
        pdfDictionary.put(PdfName.FONTBBOX, new PdfRectangle((float) ((this.head.xMin * 1000) / this.head.unitsPerEm), (float) ((this.head.yMin * 1000) / this.head.unitsPerEm), (float) ((this.head.xMax * 1000) / this.head.unitsPerEm), (float) ((this.head.yMax * 1000) / this.head.unitsPerEm)));
        if (pdfIndirectReference2 != null) {
            pdfDictionary.put(PdfName.CIDSET, pdfIndirectReference2);
        }
        if (!this.cff) {
            pdfDictionary.put(PdfName.FONTNAME, new PdfName(str + this.fontName + this.style));
        } else if (this.encoding.startsWith("Identity-")) {
            pdfDictionary.put(PdfName.FONTNAME, new PdfName(str + this.fontName + "-" + this.encoding));
        } else {
            pdfDictionary.put(PdfName.FONTNAME, new PdfName(str + this.fontName + this.style));
        }
        pdfDictionary.put(PdfName.ITALICANGLE, new PdfNumber(this.italicAngle));
        pdfDictionary.put(PdfName.STEMV, new PdfNumber(80));
        if (pdfIndirectReference != null) {
            if (this.cff) {
                pdfDictionary.put(PdfName.FONTFILE3, pdfIndirectReference);
            } else {
                pdfDictionary.put(PdfName.FONTFILE2, pdfIndirectReference);
            }
        }
        int i = 0;
        if (this.isFixedPitch) {
            i = 1;
        }
        int i2 = i | (this.fontSpecific ? 4 : 32);
        if ((this.head.macStyle & 2) != 0) {
            i2 |= 64;
        }
        if ((this.head.macStyle & 1) != 0) {
            i2 |= 262144;
        }
        pdfDictionary.put(PdfName.FLAGS, new PdfNumber(i2));
        return pdfDictionary;
    }

    /* access modifiers changed from: protected */
    public PdfDictionary getFontBaseType(PdfIndirectReference pdfIndirectReference, String str, int i, int i2, byte[] bArr) {
        PdfDictionary pdfDictionary = new PdfDictionary(PdfName.FONT);
        if (this.cff) {
            pdfDictionary.put(PdfName.SUBTYPE, PdfName.TYPE1);
            pdfDictionary.put(PdfName.BASEFONT, new PdfName(this.fontName + this.style));
        } else {
            pdfDictionary.put(PdfName.SUBTYPE, PdfName.TRUETYPE);
            pdfDictionary.put(PdfName.BASEFONT, new PdfName(str + this.fontName + this.style));
        }
        if (!this.fontSpecific) {
            int i3 = i;
            while (true) {
                if (i3 > i2) {
                    break;
                } else if (!this.differences[i3].equals(BaseFont.notdef)) {
                    i = i3;
                    break;
                } else {
                    i3++;
                }
            }
            if (this.encoding.equals("Cp1252") || this.encoding.equals(BaseFont.MACROMAN)) {
                pdfDictionary.put(PdfName.ENCODING, this.encoding.equals("Cp1252") ? PdfName.WIN_ANSI_ENCODING : PdfName.MAC_ROMAN_ENCODING);
            } else {
                PdfDictionary pdfDictionary2 = new PdfDictionary(PdfName.ENCODING);
                PdfArray pdfArray = new PdfArray();
                boolean z = true;
                for (int i4 = i; i4 <= i2; i4++) {
                    if (bArr[i4] != 0) {
                        if (z) {
                            pdfArray.add((PdfObject) new PdfNumber(i4));
                            z = false;
                        }
                        pdfArray.add((PdfObject) new PdfName(this.differences[i4]));
                    } else {
                        z = true;
                    }
                }
                pdfDictionary2.put(PdfName.DIFFERENCES, pdfArray);
                pdfDictionary.put(PdfName.ENCODING, pdfDictionary2);
            }
        }
        pdfDictionary.put(PdfName.FIRSTCHAR, new PdfNumber(i));
        pdfDictionary.put(PdfName.LASTCHAR, new PdfNumber(i2));
        PdfArray pdfArray2 = new PdfArray();
        while (i <= i2) {
            if (bArr[i] == 0) {
                pdfArray2.add((PdfObject) new PdfNumber(0));
            } else {
                pdfArray2.add((PdfObject) new PdfNumber(this.widths[i]));
            }
            i++;
        }
        pdfDictionary.put(PdfName.WIDTHS, pdfArray2);
        if (pdfIndirectReference != null) {
            pdfDictionary.put(PdfName.FONTDESCRIPTOR, pdfIndirectReference);
        }
        return pdfDictionary;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0023 A[SYNTHETIC, Splitter:B:14:0x0023] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getFullFont() throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            com.itextpdf.text.pdf.RandomAccessFileOrArray r1 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x001d }
            com.itextpdf.text.pdf.RandomAccessFileOrArray r2 = r5.rf     // Catch:{ all -> 0x001d }
            r1.<init>((com.itextpdf.text.pdf.RandomAccessFileOrArray) r2)     // Catch:{ all -> 0x001d }
            r1.reOpen()     // Catch:{ all -> 0x001b }
            long r2 = r1.length()     // Catch:{ all -> 0x001b }
            int r0 = (int) r2     // Catch:{ all -> 0x001b }
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x001b }
            r1.readFully(r0)     // Catch:{ all -> 0x001b }
            if (r1 == 0) goto L_0x001a
            r1.close()     // Catch:{ Exception -> 0x001a }
        L_0x001a:
            return r0
        L_0x001b:
            r0 = move-exception
            goto L_0x0021
        L_0x001d:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0021:
            if (r1 == 0) goto L_0x0026
            r1.close()     // Catch:{ Exception -> 0x0026 }
        L_0x0026:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.TrueTypeFont.getFullFont():byte[]");
    }

    /* access modifiers changed from: protected */
    public synchronized byte[] getSubSet(HashSet hashSet, boolean z) throws IOException, DocumentException {
        return new TrueTypeFontSubSet(this.fileName, new RandomAccessFileOrArray(this.rf), hashSet, this.directoryOffset, true, !z).process();
    }

    protected static int[] compactRanges(ArrayList<int[]> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            int[] iArr = arrayList.get(i);
            for (int i2 = 0; i2 < iArr.length; i2 += 2) {
                int i3 = i2 + 1;
                arrayList2.add(new int[]{Math.max(0, Math.min(iArr[i2], iArr[i3])), Math.min(65535, Math.max(iArr[i2], iArr[i3]))});
            }
        }
        int i4 = 0;
        while (i4 < arrayList2.size() - 1) {
            int i5 = i4 + 1;
            int i6 = i5;
            while (i6 < arrayList2.size()) {
                int[] iArr2 = (int[]) arrayList2.get(i4);
                int[] iArr3 = (int[]) arrayList2.get(i6);
                if ((iArr2[0] >= iArr3[0] && iArr2[0] <= iArr3[1]) || (iArr2[1] >= iArr3[0] && iArr2[0] <= iArr3[1])) {
                    iArr2[0] = Math.min(iArr2[0], iArr3[0]);
                    iArr2[1] = Math.max(iArr2[1], iArr3[1]);
                    arrayList2.remove(i6);
                    i6--;
                }
                i6++;
            }
            i4 = i5;
        }
        int[] iArr4 = new int[(arrayList2.size() * 2)];
        for (int i7 = 0; i7 < arrayList2.size(); i7++) {
            int[] iArr5 = (int[]) arrayList2.get(i7);
            int i8 = i7 * 2;
            iArr4[i8] = iArr5[0];
            iArr4[i8 + 1] = iArr5[1];
        }
        return iArr4;
    }

    /* access modifiers changed from: protected */
    public void addRangeUni(HashMap<Integer, int[]> hashMap, boolean z, boolean z2) {
        HashMap<Integer, int[]> hashMap2;
        boolean z3;
        if (z2) {
            return;
        }
        if (this.subsetRanges != null || this.directoryOffset > 0) {
            int[] compactRanges = (this.subsetRanges != null || this.directoryOffset <= 0) ? compactRanges(this.subsetRanges) : new int[]{0, 65535};
            if (!this.fontSpecific && this.cmap31 != null) {
                hashMap2 = this.cmap31;
            } else if (this.fontSpecific && this.cmap10 != null) {
                hashMap2 = this.cmap10;
            } else if (this.cmap31 != null) {
                hashMap2 = this.cmap31;
            } else {
                hashMap2 = this.cmap10;
            }
            for (Map.Entry next : hashMap2.entrySet()) {
                int[] iArr = (int[]) next.getValue();
                Integer valueOf = Integer.valueOf(iArr[0]);
                if (!hashMap.containsKey(valueOf)) {
                    int intValue = ((Integer) next.getKey()).intValue();
                    int i = 0;
                    while (true) {
                        if (i < compactRanges.length) {
                            if (intValue >= compactRanges[i] && intValue <= compactRanges[i + 1]) {
                                z3 = false;
                                break;
                            }
                            i += 2;
                        } else {
                            z3 = true;
                            break;
                        }
                    }
                    if (!z3) {
                        hashMap.put(valueOf, z ? new int[]{iArr[0], iArr[1], intValue} : null);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addRangeUni(HashSet<Integer> hashSet, boolean z) {
        HashMap<Integer, int[]> hashMap;
        if (z) {
            return;
        }
        if (this.subsetRanges != null || this.directoryOffset > 0) {
            int[] compactRanges = (this.subsetRanges != null || this.directoryOffset <= 0) ? compactRanges(this.subsetRanges) : new int[]{0, 65535};
            if (!this.fontSpecific && this.cmap31 != null) {
                hashMap = this.cmap31;
            } else if (this.fontSpecific && this.cmap10 != null) {
                hashMap = this.cmap10;
            } else if (this.cmap31 != null) {
                hashMap = this.cmap31;
            } else {
                hashMap = this.cmap10;
            }
            for (Map.Entry next : hashMap.entrySet()) {
                boolean z2 = false;
                Integer valueOf = Integer.valueOf(((int[]) next.getValue())[0]);
                if (!hashSet.contains(valueOf)) {
                    int intValue = ((Integer) next.getKey()).intValue();
                    int i = 0;
                    while (true) {
                        if (i < compactRanges.length) {
                            if (intValue >= compactRanges[i] && intValue <= compactRanges[i + 1]) {
                                break;
                            }
                            i += 2;
                        } else {
                            z2 = true;
                            break;
                        }
                    }
                    if (!z2) {
                        hashSet.add(valueOf);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void writeFont(PdfWriter pdfWriter, PdfIndirectReference pdfIndirectReference, Object[] objArr) throws DocumentException, IOException {
        int i;
        PdfIndirectReference pdfIndirectReference2;
        String str;
        byte[] bArr;
        int[] iArr;
        int intValue = objArr[0].intValue();
        int intValue2 = objArr[1].intValue();
        byte[] bArr2 = objArr[2];
        boolean z = objArr[3].booleanValue() && this.subset;
        if (!z) {
            intValue2 = bArr2.length - 1;
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                bArr2[i2] = 1;
            }
            i = 0;
        } else {
            i = intValue;
        }
        int i3 = intValue2;
        String str2 = "";
        if (this.embedded) {
            if (this.cff) {
                pdfIndirectReference2 = pdfWriter.addToBody(new BaseFont.StreamFont(readCffFont(), "Type1C", this.compressionLevel)).getIndirectReference();
            } else {
                if (z) {
                    str2 = createSubsetPrefix();
                }
                HashSet hashSet = new HashSet();
                for (int i4 = i; i4 <= i3; i4++) {
                    if (bArr2[i4] != 0) {
                        if (this.specialMap != null) {
                            int[] nameToUnicode = GlyphList.nameToUnicode(this.differences[i4]);
                            iArr = nameToUnicode != null ? getMetricsTT(nameToUnicode[0]) : null;
                        } else if (this.fontSpecific) {
                            iArr = getMetricsTT(i4);
                        } else {
                            iArr = getMetricsTT(this.unicodeDifferences[i4]);
                        }
                        if (iArr != null) {
                            hashSet.add(Integer.valueOf(iArr[0]));
                        }
                    }
                }
                addRangeUni(hashSet, z);
                if (!z && this.directoryOffset == 0 && this.subsetRanges == null) {
                    bArr = getFullFont();
                } else {
                    bArr = getSubSet(new HashSet(hashSet), z);
                }
                pdfIndirectReference2 = pdfWriter.addToBody(new BaseFont.StreamFont(bArr, new int[]{bArr.length}, this.compressionLevel)).getIndirectReference();
            }
            str = str2;
        } else {
            str = str2;
            pdfIndirectReference2 = null;
        }
        PdfDictionary fontDescriptor = getFontDescriptor(pdfIndirectReference2, str, (PdfIndirectReference) null);
        if (fontDescriptor != null) {
            pdfIndirectReference2 = pdfWriter.addToBody(fontDescriptor).getIndirectReference();
        }
        pdfWriter.addToBody((PdfObject) getFontBaseType(pdfIndirectReference2, str, i, i3, bArr2), pdfIndirectReference);
    }

    /* access modifiers changed from: protected */
    public byte[] readCffFont() throws IOException {
        RandomAccessFileOrArray randomAccessFileOrArray = new RandomAccessFileOrArray(this.rf);
        byte[] bArr = new byte[this.cffLength];
        try {
            randomAccessFileOrArray.reOpen();
            randomAccessFileOrArray.seek((long) this.cffOffset);
            randomAccessFileOrArray.readFully(bArr);
            return bArr;
        } finally {
            try {
                randomAccessFileOrArray.close();
            } catch (Exception unused) {
            }
        }
    }

    public PdfStream getFullFontStream() throws IOException, DocumentException {
        if (this.cff) {
            return new BaseFont.StreamFont(readCffFont(), "Type1C", this.compressionLevel);
        }
        byte[] fullFont = getFullFont();
        return new BaseFont.StreamFont(fullFont, new int[]{fullFont.length}, this.compressionLevel);
    }

    public float getFontDescriptor(int i, float f) {
        switch (i) {
            case 1:
                return (((float) this.os_2.sTypoAscender) * f) / ((float) this.head.unitsPerEm);
            case 2:
                return (((float) this.os_2.sCapHeight) * f) / ((float) this.head.unitsPerEm);
            case 3:
                return (((float) this.os_2.sTypoDescender) * f) / ((float) this.head.unitsPerEm);
            case 4:
                return (float) this.italicAngle;
            case 5:
                return (f * ((float) this.head.xMin)) / ((float) this.head.unitsPerEm);
            case 6:
                return (f * ((float) this.head.yMin)) / ((float) this.head.unitsPerEm);
            case 7:
                return (f * ((float) this.head.xMax)) / ((float) this.head.unitsPerEm);
            case 8:
                return (f * ((float) this.head.yMax)) / ((float) this.head.unitsPerEm);
            case 9:
                return (f * ((float) this.hhea.Ascender)) / ((float) this.head.unitsPerEm);
            case 10:
                return (f * ((float) this.hhea.Descender)) / ((float) this.head.unitsPerEm);
            case 11:
                return (f * ((float) this.hhea.LineGap)) / ((float) this.head.unitsPerEm);
            case 12:
                return (f * ((float) this.hhea.advanceWidthMax)) / ((float) this.head.unitsPerEm);
            case 13:
                return (((float) (this.underlinePosition - (this.underlineThickness / 2))) * f) / ((float) this.head.unitsPerEm);
            case 14:
                return (((float) this.underlineThickness) * f) / ((float) this.head.unitsPerEm);
            case 15:
                return (((float) this.os_2.yStrikeoutPosition) * f) / ((float) this.head.unitsPerEm);
            case 16:
                return (((float) this.os_2.yStrikeoutSize) * f) / ((float) this.head.unitsPerEm);
            case 17:
                return (((float) this.os_2.ySubscriptYSize) * f) / ((float) this.head.unitsPerEm);
            case 18:
                return (((float) (-this.os_2.ySubscriptYOffset)) * f) / ((float) this.head.unitsPerEm);
            case 19:
                return (((float) this.os_2.ySuperscriptYSize) * f) / ((float) this.head.unitsPerEm);
            case 20:
                return (((float) this.os_2.ySuperscriptYOffset) * f) / ((float) this.head.unitsPerEm);
            case 21:
                return (float) this.os_2.usWeightClass;
            case 22:
                return (float) this.os_2.usWidthClass;
            default:
                return 0.0f;
        }
    }

    public int[] getMetricsTT(int i) {
        if (this.cmapExt != null) {
            return this.cmapExt.get(Integer.valueOf(i));
        }
        if (!this.fontSpecific && this.cmap31 != null) {
            return this.cmap31.get(Integer.valueOf(i));
        }
        if (this.fontSpecific && this.cmap10 != null) {
            return this.cmap10.get(Integer.valueOf(i));
        }
        if (this.cmap31 != null) {
            return this.cmap31.get(Integer.valueOf(i));
        }
        if (this.cmap10 != null) {
            return this.cmap10.get(Integer.valueOf(i));
        }
        return null;
    }

    public String getPostscriptFontName() {
        return this.fontName;
    }

    public String[] getCodePagesSupported() {
        long j = (((long) this.os_2.ulCodePageRange2) << 32) + (((long) this.os_2.ulCodePageRange1) & 4294967295L);
        long j2 = 1;
        long j3 = 1;
        int i = 0;
        for (int i2 = 0; i2 < 64; i2++) {
            if (!((j & j3) == 0 || codePages[i2] == null)) {
                i++;
            }
            j3 <<= 1;
        }
        String[] strArr = new String[i];
        int i3 = 0;
        for (int i4 = 0; i4 < 64; i4++) {
            if (!((j & j2) == 0 || codePages[i4] == null)) {
                strArr[i3] = codePages[i4];
                i3++;
            }
            j2 <<= 1;
        }
        return strArr;
    }

    public String[][] getFullFontName() {
        return this.fullName;
    }

    public String getSubfamily() {
        if (this.subFamily == null || this.subFamily.length <= 0) {
            return super.getSubfamily();
        }
        return this.subFamily[0][3];
    }

    public String[][] getAllNameEntries() {
        return this.allNameEntries;
    }

    public String[][] getFamilyFontName() {
        return this.familyName;
    }

    public boolean hasKernPairs() {
        return this.kerning.size() > 0;
    }

    public void setPostscriptFontName(String str) {
        this.fontName = str;
    }

    public boolean setKerning(int i, int i2, int i3) {
        int[] metricsTT = getMetricsTT(i);
        if (metricsTT == null) {
            return false;
        }
        int i4 = metricsTT[0];
        int[] metricsTT2 = getMetricsTT(i2);
        if (metricsTT2 == null) {
            return false;
        }
        this.kerning.put((i4 << 16) + metricsTT2[0], i3);
        return true;
    }

    /* access modifiers changed from: protected */
    public int[] getRawCharBBox(int i, String str) {
        HashMap<Integer, int[]> hashMap;
        int[] iArr;
        if (str == null || this.cmap31 == null) {
            hashMap = this.cmap10;
        } else {
            hashMap = this.cmap31;
        }
        if (hashMap == null || (iArr = hashMap.get(Integer.valueOf(i))) == null || this.bboxes == null) {
            return null;
        }
        return this.bboxes[iArr[0]];
    }
}
