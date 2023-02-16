package com.itextpdf.text.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.io.RandomAccessSourceFactory;
import com.itextpdf.text.pdf.codec.TIFFConstants;
import com.itextpdf.text.pdf.fonts.cmaps.CMapParserEx;
import com.itextpdf.text.pdf.fonts.cmaps.CMapToUnicode;
import com.itextpdf.text.pdf.fonts.cmaps.CidLocationFromByte;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DocumentFont extends BaseFont {
    private static final int[] stdEnc = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 33, 34, 35, 36, 37, 38, 8217, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 8216, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 161, 162, 163, 8260, 165, 402, 167, 164, 39, 8220, 171, 8249, 8250, 64257, 64258, 0, 8211, 8224, 8225, 183, 0, 182, 8226, 8218, 8222, 8221, 187, 8230, 8240, 0, 191, 0, 96, 180, 710, 732, 175, 728, 729, 168, 0, 730, 184, 0, 733, 731, 711, 8212, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 198, 0, 170, 0, 0, 0, 0, TIFFConstants.TIFFTAG_HALFTONEHINTS, 216, TIFFConstants.TIFFTAG_EXTRASAMPLES, 186, 0, 0, 0, 0, 0, 230, 0, 0, 0, TIFFConstants.TIFFTAG_SOFTWARE, 0, 0, 322, 248, TIFFConstants.TIFFTAG_SAMPLEFORMAT, 223, 0, 0, 0, 0};
    private float ascender;
    private IntHashtable byte2uni;
    private float capHeight;
    protected String cjkEncoding;
    private BaseFont cjkMirror;
    protected int defaultWidth;
    private float descender;
    private IntHashtable diffmap;
    private PdfDictionary font;
    private String fontName;
    private float fontWeight;
    private IntHashtable hMetrics;
    protected boolean isType0;
    private float italicAngle;
    private float llx;
    private float lly;
    private HashMap<Integer, int[]> metrics;
    private PRIndirectReference refFont;
    private IntHashtable uni2byte;
    protected String uniMap;
    private float urx;
    private float ury;

    public int[] getCharBBox(int i) {
        return null;
    }

    public PdfStream getFullFontStream() {
        return null;
    }

    public int getKerning(int i, int i2) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int[] getRawCharBBox(int i, String str) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getRawWidth(int i, String str) {
        return 0;
    }

    public boolean hasKernPairs() {
        return false;
    }

    public boolean setKerning(int i, int i2, int i3) {
        return false;
    }

    public void setPostscriptFontName(String str) {
    }

    /* access modifiers changed from: package-private */
    public void writeFont(PdfWriter pdfWriter, PdfIndirectReference pdfIndirectReference, Object[] objArr) throws DocumentException, IOException {
    }

    DocumentFont(PdfDictionary pdfDictionary) {
        this.metrics = new HashMap<>();
        this.uni2byte = new IntHashtable();
        this.byte2uni = new IntHashtable();
        this.ascender = 800.0f;
        this.capHeight = 700.0f;
        this.descender = -200.0f;
        this.italicAngle = 0.0f;
        this.fontWeight = 0.0f;
        this.llx = -50.0f;
        this.lly = -200.0f;
        this.urx = 100.0f;
        this.ury = 900.0f;
        this.isType0 = false;
        this.defaultWidth = 1000;
        this.refFont = null;
        this.font = pdfDictionary;
        init();
    }

    DocumentFont(PRIndirectReference pRIndirectReference) {
        this.metrics = new HashMap<>();
        this.uni2byte = new IntHashtable();
        this.byte2uni = new IntHashtable();
        this.ascender = 800.0f;
        this.capHeight = 700.0f;
        this.descender = -200.0f;
        this.italicAngle = 0.0f;
        this.fontWeight = 0.0f;
        this.llx = -50.0f;
        this.lly = -200.0f;
        this.urx = 100.0f;
        this.ury = 900.0f;
        this.isType0 = false;
        this.defaultWidth = 1000;
        this.refFont = pRIndirectReference;
        this.font = (PdfDictionary) PdfReader.getPdfObject((PdfObject) pRIndirectReference);
        init();
    }

    DocumentFont(PRIndirectReference pRIndirectReference, PdfDictionary pdfDictionary) {
        this.metrics = new HashMap<>();
        this.uni2byte = new IntHashtable();
        this.byte2uni = new IntHashtable();
        this.ascender = 800.0f;
        this.capHeight = 700.0f;
        this.descender = -200.0f;
        this.italicAngle = 0.0f;
        this.fontWeight = 0.0f;
        this.llx = -50.0f;
        this.lly = -200.0f;
        this.urx = 100.0f;
        this.ury = 900.0f;
        this.isType0 = false;
        this.defaultWidth = 1000;
        this.refFont = pRIndirectReference;
        this.font = (PdfDictionary) PdfReader.getPdfObject((PdfObject) pRIndirectReference);
        if (this.font.get(PdfName.ENCODING) == null && pdfDictionary != null) {
            for (PdfName pdfName : pdfDictionary.getKeys()) {
                this.font.put(PdfName.ENCODING, pdfDictionary.get(pdfName));
            }
        }
        init();
    }

    public PdfDictionary getFontDictionary() {
        return this.font;
    }

    private void init() {
        this.encoding = "";
        this.fontSpecific = false;
        this.fontType = 4;
        PdfName asName = this.font.getAsName(PdfName.BASEFONT);
        this.fontName = asName != null ? PdfName.decodeName(asName.toString()) : "Unspecified Font Name";
        PdfName asName2 = this.font.getAsName(PdfName.SUBTYPE);
        if (PdfName.TYPE1.equals(asName2) || PdfName.TRUETYPE.equals(asName2)) {
            doType1TT();
        } else if (PdfName.TYPE3.equals(asName2)) {
            fillEncoding((PdfName) null);
            fillDiffMap(this.font.getAsDict(PdfName.ENCODING), (CMapToUnicode) null);
            fillWidths();
        } else {
            PdfName asName3 = this.font.getAsName(PdfName.ENCODING);
            if (asName3 != null) {
                String decodeName = PdfName.decodeName(asName3.toString());
                String GetCompatibleFont = CJKFont.GetCompatibleFont(decodeName);
                if (GetCompatibleFont != null) {
                    try {
                        this.cjkMirror = BaseFont.createFont(GetCompatibleFont, decodeName, false);
                        this.cjkEncoding = decodeName;
                        this.uniMap = ((CJKFont) this.cjkMirror).getUniMap();
                    } catch (Exception e) {
                        throw new ExceptionConverter(e);
                    }
                }
                if (PdfName.TYPE0.equals(asName2)) {
                    this.isType0 = true;
                    if (decodeName.equals(BaseFont.IDENTITY_H) || this.cjkMirror == null) {
                        processType0(this.font);
                        return;
                    }
                    PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObjectRelease(((PdfArray) PdfReader.getPdfObjectRelease(this.font.get(PdfName.DESCENDANTFONTS))).getPdfObject(0));
                    PdfNumber pdfNumber = (PdfNumber) PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.DW));
                    if (pdfNumber != null) {
                        this.defaultWidth = pdfNumber.intValue();
                    }
                    this.hMetrics = readWidths((PdfArray) PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.W)));
                    fillFontDesc((PdfDictionary) PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.FONTDESCRIPTOR)));
                }
            }
        }
    }

    private void processType0(PdfDictionary pdfDictionary) {
        try {
            PdfObject pdfObjectRelease = PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.TOUNICODE));
            PdfDictionary pdfDictionary2 = (PdfDictionary) PdfReader.getPdfObjectRelease(((PdfArray) PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.DESCENDANTFONTS))).getPdfObject(0));
            PdfNumber pdfNumber = (PdfNumber) PdfReader.getPdfObjectRelease(pdfDictionary2.get(PdfName.DW));
            int i = 1000;
            if (pdfNumber != null) {
                i = pdfNumber.intValue();
            }
            IntHashtable readWidths = readWidths((PdfArray) PdfReader.getPdfObjectRelease(pdfDictionary2.get(PdfName.W)));
            fillFontDesc((PdfDictionary) PdfReader.getPdfObjectRelease(pdfDictionary2.get(PdfName.FONTDESCRIPTOR)));
            if (pdfObjectRelease instanceof PRStream) {
                fillMetrics(PdfReader.getStreamBytes((PRStream) pdfObjectRelease), readWidths, i);
            } else if (new PdfName(BaseFont.IDENTITY_H).equals(pdfObjectRelease)) {
                fillMetricsIdentity(readWidths, i);
            }
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    private IntHashtable readWidths(PdfArray pdfArray) {
        IntHashtable intHashtable = new IntHashtable();
        if (pdfArray == null) {
            return intHashtable;
        }
        int i = 0;
        while (i < pdfArray.size()) {
            int intValue = ((PdfNumber) PdfReader.getPdfObjectRelease(pdfArray.getPdfObject(i))).intValue();
            int i2 = i + 1;
            PdfObject pdfObjectRelease = PdfReader.getPdfObjectRelease(pdfArray.getPdfObject(i2));
            if (pdfObjectRelease.isArray()) {
                PdfArray pdfArray2 = (PdfArray) pdfObjectRelease;
                int i3 = intValue;
                int i4 = 0;
                while (i4 < pdfArray2.size()) {
                    intHashtable.put(i3, ((PdfNumber) PdfReader.getPdfObjectRelease(pdfArray2.getPdfObject(i4))).intValue());
                    i4++;
                    i3++;
                }
            } else {
                int intValue2 = ((PdfNumber) pdfObjectRelease).intValue();
                i2++;
                int intValue3 = ((PdfNumber) PdfReader.getPdfObjectRelease(pdfArray.getPdfObject(i2))).intValue();
                while (intValue <= intValue2) {
                    intHashtable.put(intValue, intValue3);
                    intValue++;
                }
            }
            i = i2 + 1;
        }
        return intHashtable;
    }

    private String decodeString(PdfString pdfString) {
        if (pdfString.isHexWriting()) {
            return PdfEncodings.convertToString(pdfString.getBytes(), "UnicodeBigUnmarked");
        }
        return pdfString.toUnicodeString();
    }

    private void fillMetricsIdentity(IntHashtable intHashtable, int i) {
        for (int i2 = 0; i2 < 65536; i2++) {
            this.metrics.put(Integer.valueOf(i2), new int[]{i2, intHashtable.containsKey(i2) ? intHashtable.get(i2) : i});
        }
    }

    private void fillMetrics(byte[] bArr, IntHashtable intHashtable, int i) {
        int i2;
        IntHashtable intHashtable2 = intHashtable;
        try {
            PdfContentParser pdfContentParser = new PdfContentParser(new PRTokeniser(new RandomAccessFileOrArray(new RandomAccessSourceFactory().createSource(bArr))));
            int i3 = 1;
            int i4 = 50;
            int i5 = 0;
            boolean z = true;
            while (true) {
                if (z || i5 > 0) {
                    try {
                        PdfObject readPRObject = pdfContentParser.readPRObject();
                        if (readPRObject != null) {
                            if (readPRObject.type() == 200) {
                                if (readPRObject.toString().equals("begin")) {
                                    i5++;
                                    z = false;
                                } else if (readPRObject.toString().equals(TtmlNode.END)) {
                                    i5--;
                                } else if (readPRObject.toString().equals("beginbfchar")) {
                                    while (true) {
                                        PdfObject readPRObject2 = pdfContentParser.readPRObject();
                                        if (readPRObject2.toString().equals("endbfchar")) {
                                            break;
                                        }
                                        String decodeString = decodeString((PdfString) readPRObject2);
                                        String decodeString2 = decodeString((PdfString) pdfContentParser.readPRObject());
                                        if (decodeString2.length() == i3) {
                                            int charAt = decodeString.charAt(0);
                                            char charAt2 = decodeString2.charAt(decodeString2.length() - i3);
                                            int i6 = intHashtable2.containsKey(charAt) ? intHashtable2.get(charAt) : i;
                                            HashMap<Integer, int[]> hashMap = this.metrics;
                                            Integer valueOf = Integer.valueOf(charAt2);
                                            int[] iArr = new int[2];
                                            iArr[0] = charAt;
                                            iArr[i3] = i6;
                                            hashMap.put(valueOf, iArr);
                                        }
                                    }
                                } else if (readPRObject.toString().equals("beginbfrange")) {
                                    while (true) {
                                        PdfObject readPRObject3 = pdfContentParser.readPRObject();
                                        if (readPRObject3.toString().equals("endbfrange")) {
                                            break;
                                        }
                                        String decodeString3 = decodeString((PdfString) readPRObject3);
                                        String decodeString4 = decodeString((PdfString) pdfContentParser.readPRObject());
                                        int charAt3 = decodeString3.charAt(0);
                                        char charAt4 = decodeString4.charAt(0);
                                        PdfObject readPRObject4 = pdfContentParser.readPRObject();
                                        if (readPRObject4.isString()) {
                                            String decodeString5 = decodeString((PdfString) readPRObject4);
                                            if (decodeString5.length() == i3) {
                                                int charAt5 = decodeString5.charAt(decodeString5.length() - i3);
                                                while (charAt3 <= charAt4) {
                                                    this.metrics.put(Integer.valueOf(charAt5), new int[]{charAt3, intHashtable2.containsKey(charAt3) ? intHashtable2.get(charAt3) : i});
                                                    charAt3++;
                                                    charAt5++;
                                                }
                                            }
                                        } else {
                                            PdfArray pdfArray = (PdfArray) readPRObject4;
                                            int i7 = 0;
                                            while (i7 < pdfArray.size()) {
                                                String decodeString6 = decodeString(pdfArray.getAsString(i7));
                                                if (decodeString6.length() == 1) {
                                                    this.metrics.put(Integer.valueOf(decodeString6.charAt(decodeString6.length() - 1)), new int[]{charAt3, intHashtable2.containsKey(charAt3) ? intHashtable2.get(charAt3) : i});
                                                }
                                                i7++;
                                                charAt3++;
                                            }
                                        }
                                        i3 = 1;
                                    }
                                }
                            }
                            i2 = i3;
                            i3 = i2;
                        } else {
                            return;
                        }
                    } catch (Exception unused) {
                        i2 = i3;
                        i4--;
                        if (i4 < 0) {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    private void doType1TT() {
        PdfObject pdfObject = PdfReader.getPdfObject(this.font.get(PdfName.ENCODING));
        if (pdfObject == null) {
            PdfName asName = this.font.getAsName(PdfName.BASEFONT);
            if (!BuiltinFonts14.containsKey(this.fontName) || (!PdfName.SYMBOL.equals(asName) && !PdfName.ZAPFDINGBATS.equals(asName))) {
                fillEncoding((PdfName) null);
            } else {
                fillEncoding(asName);
            }
            try {
                CMapToUnicode processToUnicode = processToUnicode();
                if (processToUnicode != null) {
                    for (Map.Entry next : processToUnicode.createReverseMapping().entrySet()) {
                        this.uni2byte.put(((Integer) next.getKey()).intValue(), ((Integer) next.getValue()).intValue());
                        this.byte2uni.put(((Integer) next.getValue()).intValue(), ((Integer) next.getKey()).intValue());
                    }
                }
            } catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        } else if (pdfObject.isName()) {
            fillEncoding((PdfName) pdfObject);
        } else if (pdfObject.isDictionary()) {
            PdfDictionary pdfDictionary = (PdfDictionary) pdfObject;
            PdfObject pdfObject2 = PdfReader.getPdfObject(pdfDictionary.get(PdfName.BASEENCODING));
            if (pdfObject2 == null) {
                fillEncoding((PdfName) null);
            } else {
                fillEncoding((PdfName) pdfObject2);
            }
            fillDiffMap(pdfDictionary, (CMapToUnicode) null);
        }
        if (BuiltinFonts14.containsKey(this.fontName)) {
            try {
                BaseFont createFont = BaseFont.createFont(this.fontName, "Cp1252", false);
                int[] orderedKeys = this.uni2byte.toOrderedKeys();
                for (int i = 0; i < orderedKeys.length; i++) {
                    int i2 = this.uni2byte.get(orderedKeys[i]);
                    this.widths[i2] = createFont.getRawWidth(i2, GlyphList.unicodeToName(orderedKeys[i]));
                }
                if (this.diffmap != null) {
                    int[] orderedKeys2 = this.diffmap.toOrderedKeys();
                    for (int i3 = 0; i3 < orderedKeys2.length; i3++) {
                        int i4 = this.diffmap.get(orderedKeys2[i3]);
                        this.widths[i4] = createFont.getRawWidth(i4, GlyphList.unicodeToName(orderedKeys2[i3]));
                    }
                    this.diffmap = null;
                }
                this.ascender = createFont.getFontDescriptor(1, 1000.0f);
                this.capHeight = createFont.getFontDescriptor(2, 1000.0f);
                this.descender = createFont.getFontDescriptor(3, 1000.0f);
                this.italicAngle = createFont.getFontDescriptor(4, 1000.0f);
                this.fontWeight = createFont.getFontDescriptor(23, 1000.0f);
                this.llx = createFont.getFontDescriptor(5, 1000.0f);
                this.lly = createFont.getFontDescriptor(6, 1000.0f);
                this.urx = createFont.getFontDescriptor(7, 1000.0f);
                this.ury = createFont.getFontDescriptor(8, 1000.0f);
            } catch (Exception e2) {
                throw new ExceptionConverter(e2);
            }
        }
        fillWidths();
        fillFontDesc(this.font.getAsDict(PdfName.FONTDESCRIPTOR));
    }

    private void fillWidths() {
        PdfArray asArray = this.font.getAsArray(PdfName.WIDTHS);
        PdfNumber asNumber = this.font.getAsNumber(PdfName.FIRSTCHAR);
        PdfNumber asNumber2 = this.font.getAsNumber(PdfName.LASTCHAR);
        if (asNumber != null && asNumber2 != null && asArray != null) {
            int intValue = asNumber.intValue();
            int size = asArray.size() + intValue;
            if (this.widths.length < size) {
                int[] iArr = new int[size];
                System.arraycopy(this.widths, 0, iArr, 0, intValue);
                this.widths = iArr;
            }
            for (int i = 0; i < asArray.size(); i++) {
                this.widths[intValue + i] = asArray.getAsNumber(i).intValue();
            }
        }
    }

    private void fillDiffMap(PdfDictionary pdfDictionary, CMapToUnicode cMapToUnicode) {
        PdfArray asArray = pdfDictionary.getAsArray(PdfName.DIFFERENCES);
        if (asArray != null) {
            this.diffmap = new IntHashtable();
            CMapToUnicode cMapToUnicode2 = cMapToUnicode;
            int i = 0;
            for (int i2 = 0; i2 < asArray.size(); i2++) {
                PdfObject pdfObject = asArray.getPdfObject(i2);
                if (pdfObject.isNumber()) {
                    i = ((PdfNumber) pdfObject).intValue();
                } else {
                    int[] nameToUnicode = GlyphList.nameToUnicode(PdfName.decodeName(((PdfName) pdfObject).toString()));
                    if (nameToUnicode == null || nameToUnicode.length <= 0) {
                        if (cMapToUnicode2 == null && (cMapToUnicode2 = processToUnicode()) == null) {
                            cMapToUnicode2 = new CMapToUnicode();
                        }
                        String lookup = cMapToUnicode2.lookup(new byte[]{(byte) i}, 0, 1);
                        if (lookup != null && lookup.length() == 1) {
                            this.uni2byte.put(lookup.charAt(0), i);
                            this.byte2uni.put(i, lookup.charAt(0));
                            this.diffmap.put(lookup.charAt(0), i);
                        }
                    } else {
                        this.uni2byte.put(nameToUnicode[0], i);
                        this.byte2uni.put(i, nameToUnicode[0]);
                        this.diffmap.put(nameToUnicode[0], i);
                    }
                    i++;
                }
            }
        }
    }

    private CMapToUnicode processToUnicode() {
        PdfObject pdfObjectRelease = PdfReader.getPdfObjectRelease(this.font.get(PdfName.TOUNICODE));
        if (!(pdfObjectRelease instanceof PRStream)) {
            return null;
        }
        try {
            CidLocationFromByte cidLocationFromByte = new CidLocationFromByte(PdfReader.getStreamBytes((PRStream) pdfObjectRelease));
            CMapToUnicode cMapToUnicode = new CMapToUnicode();
            CMapParserEx.parseCid("", cMapToUnicode, cidLocationFromByte);
            return cMapToUnicode;
        } catch (Exception unused) {
            return null;
        }
    }

    private void fillFontDesc(PdfDictionary pdfDictionary) {
        if (pdfDictionary != null) {
            PdfNumber asNumber = pdfDictionary.getAsNumber(PdfName.ASCENT);
            if (asNumber != null) {
                this.ascender = asNumber.floatValue();
            }
            PdfNumber asNumber2 = pdfDictionary.getAsNumber(PdfName.CAPHEIGHT);
            if (asNumber2 != null) {
                this.capHeight = asNumber2.floatValue();
            }
            PdfNumber asNumber3 = pdfDictionary.getAsNumber(PdfName.DESCENT);
            if (asNumber3 != null) {
                this.descender = asNumber3.floatValue();
            }
            PdfNumber asNumber4 = pdfDictionary.getAsNumber(PdfName.ITALICANGLE);
            if (asNumber4 != null) {
                this.italicAngle = asNumber4.floatValue();
            }
            PdfNumber asNumber5 = pdfDictionary.getAsNumber(PdfName.FONTWEIGHT);
            if (asNumber5 != null) {
                this.fontWeight = asNumber5.floatValue();
            }
            PdfArray asArray = pdfDictionary.getAsArray(PdfName.FONTBBOX);
            if (asArray != null) {
                this.llx = asArray.getAsNumber(0).floatValue();
                this.lly = asArray.getAsNumber(1).floatValue();
                this.urx = asArray.getAsNumber(2).floatValue();
                this.ury = asArray.getAsNumber(3).floatValue();
                if (this.llx > this.urx) {
                    float f = this.llx;
                    this.llx = this.urx;
                    this.urx = f;
                }
                if (this.lly > this.ury) {
                    float f2 = this.lly;
                    this.lly = this.ury;
                    this.ury = f2;
                }
            }
            float max = Math.max(this.ury, this.ascender);
            float min = Math.min(this.lly, this.descender);
            float f3 = max * 1000.0f;
            float f4 = max - min;
            this.ascender = f3 / f4;
            this.descender = (min * 1000.0f) / f4;
        }
    }

    private void fillEncoding(PdfName pdfName) {
        int i = 0;
        if (pdfName == null && isSymbolic()) {
            while (i < 256) {
                this.uni2byte.put(i, i);
                this.byte2uni.put(i, i);
                i++;
            }
        } else if (PdfName.MAC_ROMAN_ENCODING.equals(pdfName) || PdfName.WIN_ANSI_ENCODING.equals(pdfName) || PdfName.SYMBOL.equals(pdfName) || PdfName.ZAPFDINGBATS.equals(pdfName)) {
            byte[] bArr = new byte[256];
            for (int i2 = 0; i2 < 256; i2++) {
                bArr[i2] = (byte) i2;
            }
            String str = "Cp1252";
            if (PdfName.MAC_ROMAN_ENCODING.equals(pdfName)) {
                str = BaseFont.MACROMAN;
            } else if (PdfName.SYMBOL.equals(pdfName)) {
                str = "Symbol";
            } else if (PdfName.ZAPFDINGBATS.equals(pdfName)) {
                str = "ZapfDingbats";
            }
            char[] charArray = PdfEncodings.convertToString(bArr, str).toCharArray();
            while (i < 256) {
                this.uni2byte.put(charArray[i], i);
                this.byte2uni.put(i, charArray[i]);
                i++;
            }
            this.encoding = str;
        } else {
            while (i < 256) {
                this.uni2byte.put(stdEnc[i], i);
                this.byte2uni.put(i, stdEnc[i]);
                i++;
            }
        }
    }

    public String[][] getFamilyFontName() {
        return getFullFontName();
    }

    public float getFontDescriptor(int i, float f) {
        if (this.cjkMirror != null) {
            return this.cjkMirror.getFontDescriptor(i, f);
        }
        if (i == 23) {
            return (this.fontWeight * f) / 1000.0f;
        }
        switch (i) {
            case 1:
            case 9:
                return (this.ascender * f) / 1000.0f;
            case 2:
                return (this.capHeight * f) / 1000.0f;
            case 3:
            case 10:
                return (this.descender * f) / 1000.0f;
            case 4:
                return this.italicAngle;
            case 5:
                return (this.llx * f) / 1000.0f;
            case 6:
                return (this.lly * f) / 1000.0f;
            case 7:
                return (this.urx * f) / 1000.0f;
            case 8:
                return (this.ury * f) / 1000.0f;
            case 11:
                return 0.0f;
            case 12:
                return ((this.urx - this.llx) * f) / 1000.0f;
            default:
                return 0.0f;
        }
    }

    public String[][] getFullFontName() {
        return new String[][]{new String[]{"", "", "", this.fontName}};
    }

    public String[][] getAllNameEntries() {
        return new String[][]{new String[]{"4", "", "", "", this.fontName}};
    }

    public String getPostscriptFontName() {
        return this.fontName;
    }

    public int getWidth(int i) {
        if (this.isType0) {
            if (this.hMetrics == null || this.cjkMirror == null || this.cjkMirror.isVertical()) {
                int[] iArr = this.metrics.get(Integer.valueOf(i));
                if (iArr != null) {
                    return iArr[1];
                }
                return 0;
            }
            int i2 = this.hMetrics.get(this.cjkMirror.getCidCode(i));
            if (i2 > 0) {
                return i2;
            }
            return this.defaultWidth;
        } else if (this.cjkMirror != null) {
            return this.cjkMirror.getWidth(i);
        } else {
            return super.getWidth(i);
        }
    }

    public int getWidth(String str) {
        int i;
        if (this.isType0) {
            int i2 = 0;
            if (this.hMetrics == null || this.cjkMirror == null || this.cjkMirror.isVertical()) {
                char[] charArray = str.toCharArray();
                int length = charArray.length;
                int i3 = 0;
                while (i2 < length) {
                    int[] iArr = this.metrics.get(Integer.valueOf(charArray[i2]));
                    if (iArr != null) {
                        i3 += iArr[1];
                    }
                    i2++;
                }
                return i3;
            } else if (((CJKFont) this.cjkMirror).isIdentity()) {
                int i4 = 0;
                while (i2 < str.length()) {
                    i4 += getWidth((int) str.charAt(i2));
                    i2++;
                }
                return i4;
            } else {
                int i5 = 0;
                while (i2 < str.length()) {
                    if (Utilities.isSurrogatePair(str, i2)) {
                        i = Utilities.convertToUtf32(str, i2);
                        i2++;
                    } else {
                        i = str.charAt(i2);
                    }
                    i5 += getWidth(i);
                    i2++;
                }
                return i5;
            }
        } else if (this.cjkMirror != null) {
            return this.cjkMirror.getWidth(str);
        } else {
            return super.getWidth(str);
        }
    }

    public byte[] convertToBytes(String str) {
        if (this.cjkMirror != null) {
            return this.cjkMirror.convertToBytes(str);
        }
        if (this.isType0) {
            byte[] bArr = new byte[(r0 * 2)];
            int i = 0;
            for (char valueOf : str.toCharArray()) {
                int[] iArr = this.metrics.get(Integer.valueOf(valueOf));
                if (iArr != null) {
                    int i2 = iArr[0];
                    int i3 = i + 1;
                    bArr[i] = (byte) (i2 / 256);
                    i = i3 + 1;
                    bArr[i3] = (byte) i2;
                }
            }
            if (i == bArr.length) {
                return bArr;
            }
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            return bArr2;
        }
        char[] charArray = str.toCharArray();
        byte[] bArr3 = new byte[charArray.length];
        int i4 = 0;
        for (int i5 = 0; i5 < charArray.length; i5++) {
            if (this.uni2byte.containsKey(charArray[i5])) {
                bArr3[i4] = (byte) this.uni2byte.get(charArray[i5]);
                i4++;
            }
        }
        if (i4 == bArr3.length) {
            return bArr3;
        }
        byte[] bArr4 = new byte[i4];
        System.arraycopy(bArr3, 0, bArr4, 0, i4);
        return bArr4;
    }

    /* access modifiers changed from: package-private */
    public byte[] convertToBytes(int i) {
        if (this.cjkMirror != null) {
            return this.cjkMirror.convertToBytes(i);
        }
        if (this.isType0) {
            int[] iArr = this.metrics.get(Integer.valueOf(i));
            if (iArr == null) {
                return new byte[0];
            }
            int i2 = iArr[0];
            return new byte[]{(byte) (i2 / 256), (byte) i2};
        } else if (!this.uni2byte.containsKey(i)) {
            return new byte[0];
        } else {
            return new byte[]{(byte) this.uni2byte.get(i)};
        }
    }

    /* access modifiers changed from: package-private */
    public PdfIndirectReference getIndirectReference() {
        if (this.refFont != null) {
            return this.refFont;
        }
        throw new IllegalArgumentException("Font reuse not allowed with direct font objects.");
    }

    public boolean charExists(int i) {
        if (this.cjkMirror != null) {
            return this.cjkMirror.charExists(i);
        }
        if (this.isType0) {
            return this.metrics.containsKey(Integer.valueOf(i));
        }
        return super.charExists(i);
    }

    public double[] getFontMatrix() {
        if (this.font.getAsArray(PdfName.FONTMATRIX) != null) {
            return this.font.getAsArray(PdfName.FONTMATRIX).asDoubleArray();
        }
        return DEFAULT_FONT_MATRIX;
    }

    public boolean isVertical() {
        if (this.cjkMirror != null) {
            return this.cjkMirror.isVertical();
        }
        return super.isVertical();
    }

    /* access modifiers changed from: package-private */
    public IntHashtable getUni2Byte() {
        return this.uni2byte;
    }

    /* access modifiers changed from: package-private */
    public IntHashtable getByte2Uni() {
        return this.byte2uni;
    }

    /* access modifiers changed from: package-private */
    public IntHashtable getDiffmap() {
        return this.diffmap;
    }

    /* access modifiers changed from: package-private */
    public boolean isSymbolic() {
        PdfNumber asNumber;
        PdfDictionary asDict = this.font.getAsDict(PdfName.FONTDESCRIPTOR);
        if (asDict == null || (asNumber = asDict.getAsNumber(PdfName.FLAGS)) == null || (asNumber.intValue() & 4) == 0) {
            return false;
        }
        return true;
    }
}
