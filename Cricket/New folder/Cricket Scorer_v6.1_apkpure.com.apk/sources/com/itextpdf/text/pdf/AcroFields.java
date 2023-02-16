package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.io.RASInputStream;
import com.itextpdf.text.io.RandomAccessSourceFactory;
import com.itextpdf.text.io.WindowRandomAccessSource;
import com.itextpdf.text.pdf.PRTokeniser;
import com.itextpdf.text.pdf.XfaForm;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import com.itextpdf.text.xml.XmlToTxt;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.w3c.dom.Node;

public class AcroFields {
    public static final int DA_COLOR = 2;
    public static final int DA_FONT = 0;
    public static final int DA_SIZE = 1;
    public static final int FIELD_TYPE_CHECKBOX = 2;
    public static final int FIELD_TYPE_COMBO = 6;
    public static final int FIELD_TYPE_LIST = 5;
    public static final int FIELD_TYPE_NONE = 0;
    public static final int FIELD_TYPE_PUSHBUTTON = 1;
    public static final int FIELD_TYPE_RADIOBUTTON = 3;
    public static final int FIELD_TYPE_SIGNATURE = 7;
    public static final int FIELD_TYPE_TEXT = 4;
    private static final PdfName[] buttonRemove = {PdfName.MK, PdfName.F, PdfName.FF, PdfName.Q, PdfName.BS, PdfName.BORDER};
    private static final HashMap<String, String[]> stdFieldFontNames = new HashMap<>();
    private boolean append;
    private HashMap<Integer, BaseFont> extensionFonts = new HashMap<>();
    private float extraMarginLeft;
    private float extraMarginTop;
    private Map<String, TextField> fieldCache;
    Map<String, Item> fields;
    private boolean generateAppearances = true;
    private boolean lastWasString;
    private HashMap<String, BaseFont> localFonts = new HashMap<>();
    private ArrayList<String> orderedSignatureNames;
    PdfReader reader;
    private HashMap<String, int[]> sigNames;
    private ArrayList<BaseFont> substitutionFonts;
    private int topFirst;
    private int totalRevisions;
    PdfWriter writer;
    private XfaForm xfa;

    public static class FieldPosition {
        public int page;
        public Rectangle position;
    }

    AcroFields(PdfReader pdfReader, PdfWriter pdfWriter) {
        this.reader = pdfReader;
        this.writer = pdfWriter;
        try {
            this.xfa = new XfaForm(pdfReader);
            if (pdfWriter instanceof PdfStamperImp) {
                this.append = ((PdfStamperImp) pdfWriter).isAppend();
            }
            fill();
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void fill() {
        int i;
        this.fields = new LinkedHashMap();
        PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObjectRelease(this.reader.getCatalog().get(PdfName.ACROFORM));
        if (pdfDictionary != null) {
            PdfBoolean asBoolean = pdfDictionary.getAsBoolean(PdfName.NEEDAPPEARANCES);
            int i2 = 0;
            if (asBoolean == null || !asBoolean.booleanValue()) {
                setGenerateAppearances(true);
            } else {
                setGenerateAppearances(false);
            }
            PdfArray pdfArray = (PdfArray) PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.FIELDS));
            if (pdfArray != null && pdfArray.size() != 0) {
                int i3 = 1;
                while (i3 <= this.reader.getNumberOfPages()) {
                    PdfDictionary pageNRelease = this.reader.getPageNRelease(i3);
                    PdfArray pdfArray2 = (PdfArray) PdfReader.getPdfObjectRelease(pageNRelease.get(PdfName.ANNOTS), pageNRelease);
                    if (pdfArray2 != null) {
                        int i4 = i2;
                        while (i4 < pdfArray2.size()) {
                            PdfDictionary asDict = pdfArray2.getAsDict(i4);
                            if (asDict == null) {
                                PdfReader.releaseLastXrefPartial(pdfArray2.getAsIndirectObject(i4));
                            } else if (!PdfName.WIDGET.equals(asDict.getAsName(PdfName.SUBTYPE))) {
                                PdfReader.releaseLastXrefPartial(pdfArray2.getAsIndirectObject(i4));
                            } else {
                                PdfDictionary pdfDictionary2 = new PdfDictionary();
                                pdfDictionary2.putAll(asDict);
                                PdfObject pdfObject = null;
                                PdfDictionary pdfDictionary3 = null;
                                String str = "";
                                for (PdfDictionary pdfDictionary4 = asDict; pdfDictionary4 != null; pdfDictionary4 = pdfDictionary4.getAsDict(PdfName.PARENT)) {
                                    pdfDictionary2.mergeDifferent(pdfDictionary4);
                                    PdfString asString = pdfDictionary4.getAsString(PdfName.T);
                                    if (asString != null) {
                                        str = asString.toUnicodeString() + "." + str;
                                    }
                                    if (pdfObject == null && pdfDictionary4.get(PdfName.V) != null) {
                                        pdfObject = PdfReader.getPdfObjectRelease(pdfDictionary4.get(PdfName.V));
                                    }
                                    if (pdfDictionary3 == null && asString != null) {
                                        if (pdfDictionary4.get(PdfName.V) == null && pdfObject != null) {
                                            pdfDictionary4.put(PdfName.V, pdfObject);
                                        }
                                        pdfDictionary3 = pdfDictionary4;
                                    }
                                }
                                if (str.length() > 0) {
                                    i = 0;
                                    str = str.substring(0, str.length() - 1);
                                } else {
                                    i = 0;
                                }
                                Item item = this.fields.get(str);
                                if (item == null) {
                                    item = new Item();
                                    this.fields.put(str, item);
                                }
                                if (pdfDictionary3 == null) {
                                    item.addValue(asDict);
                                } else {
                                    item.addValue(pdfDictionary3);
                                }
                                item.addWidget(asDict);
                                item.addWidgetRef(pdfArray2.getAsIndirectObject(i4));
                                if (pdfDictionary != null) {
                                    pdfDictionary2.mergeDifferent(pdfDictionary);
                                }
                                item.addMerged(pdfDictionary2);
                                item.addPage(i3);
                                item.addTabOrder(i4);
                                i4++;
                                i2 = i;
                            }
                            i = i2;
                            i4++;
                            i2 = i;
                        }
                    }
                    i3++;
                    i2 = i2;
                }
                PdfNumber asNumber = pdfDictionary.getAsNumber(PdfName.SIGFLAGS);
                if (asNumber != null && (asNumber.intValue() & 1) == 1) {
                    for (int i5 = i2; i5 < pdfArray.size(); i5++) {
                        PdfDictionary asDict2 = pdfArray.getAsDict(i5);
                        if (asDict2 == null) {
                            PdfReader.releaseLastXrefPartial(pdfArray.getAsIndirectObject(i5));
                        } else if (!PdfName.WIDGET.equals(asDict2.getAsName(PdfName.SUBTYPE))) {
                            PdfReader.releaseLastXrefPartial(pdfArray.getAsIndirectObject(i5));
                        } else if (((PdfArray) PdfReader.getPdfObjectRelease(asDict2.get(PdfName.KIDS))) == null) {
                            PdfDictionary pdfDictionary5 = new PdfDictionary();
                            pdfDictionary5.putAll(asDict2);
                            PdfString asString2 = asDict2.getAsString(PdfName.T);
                            if (asString2 != null) {
                                String unicodeString = asString2.toUnicodeString();
                                if (!this.fields.containsKey(unicodeString)) {
                                    Item item2 = new Item();
                                    this.fields.put(unicodeString, item2);
                                    item2.addValue(pdfDictionary5);
                                    item2.addWidget(pdfDictionary5);
                                    item2.addWidgetRef(pdfArray.getAsIndirectObject(i5));
                                    item2.addMerged(pdfDictionary5);
                                    item2.addPage(-1);
                                    item2.addTabOrder(-1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String[] getAppearanceStates(String str) {
        PdfDictionary asDict;
        PdfString pdfString;
        Item item = this.fields.get(str);
        if (item == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        PdfDictionary value = item.getValue(0);
        PdfString asString = value.getAsString(PdfName.OPT);
        if (asString != null) {
            linkedHashSet.add(asString.toUnicodeString());
        } else {
            PdfArray asArray = value.getAsArray(PdfName.OPT);
            if (asArray != null) {
                for (int i = 0; i < asArray.size(); i++) {
                    PdfObject directObject = asArray.getDirectObject(i);
                    int type = directObject.type();
                    if (type == 3) {
                        pdfString = (PdfString) directObject;
                    } else if (type != 5) {
                        pdfString = null;
                    } else {
                        pdfString = ((PdfArray) directObject).getAsString(1);
                    }
                    if (pdfString != null) {
                        linkedHashSet.add(pdfString.toUnicodeString());
                    }
                }
            }
        }
        for (int i2 = 0; i2 < item.size(); i2++) {
            PdfDictionary asDict2 = item.getWidget(i2).getAsDict(PdfName.AP);
            if (!(asDict2 == null || (asDict = asDict2.getAsDict(PdfName.N)) == null)) {
                for (PdfName pdfName : asDict.getKeys()) {
                    linkedHashSet.add(PdfName.decodeName(pdfName.toString()));
                }
            }
        }
        return (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
    }

    private String[] getListOption(String str, int i) {
        Item fieldItem = getFieldItem(str);
        if (fieldItem == null) {
            return null;
        }
        PdfArray asArray = fieldItem.getMerged(0).getAsArray(PdfName.OPT);
        if (asArray == null) {
            return null;
        }
        String[] strArr = new String[asArray.size()];
        for (int i2 = 0; i2 < asArray.size(); i2++) {
            PdfObject directObject = asArray.getDirectObject(i2);
            try {
                if (directObject.isArray()) {
                    directObject = ((PdfArray) directObject).getDirectObject(i);
                }
                if (directObject.isString()) {
                    strArr[i2] = ((PdfString) directObject).toUnicodeString();
                } else {
                    strArr[i2] = directObject.toString();
                }
            } catch (Exception unused) {
                strArr[i2] = "";
            }
        }
        return strArr;
    }

    public String[] getListOptionExport(String str) {
        return getListOption(str, 0);
    }

    public String[] getListOptionDisplay(String str) {
        return getListOption(str, 1);
    }

    public boolean setListOption(String str, String[] strArr, String[] strArr2) {
        int i = 0;
        if (strArr == null && strArr2 == null) {
            return false;
        }
        if (strArr == null || strArr2 == null || strArr.length == strArr2.length) {
            int fieldType = getFieldType(str);
            if (fieldType != 6 && fieldType != 5) {
                return false;
            }
            Item item = this.fields.get(str);
            String[] strArr3 = null;
            if (strArr == null && strArr2 != null) {
                strArr3 = strArr2;
            } else if (strArr != null && strArr2 == null) {
                strArr3 = strArr;
            }
            PdfArray pdfArray = new PdfArray();
            if (strArr3 != null) {
                while (i < strArr3.length) {
                    pdfArray.add((PdfObject) new PdfString(strArr3[i], PdfObject.TEXT_UNICODE));
                    i++;
                }
            } else {
                while (i < strArr.length) {
                    PdfArray pdfArray2 = new PdfArray();
                    pdfArray2.add((PdfObject) new PdfString(strArr[i], PdfObject.TEXT_UNICODE));
                    pdfArray2.add((PdfObject) new PdfString(strArr2[i], PdfObject.TEXT_UNICODE));
                    pdfArray.add((PdfObject) pdfArray2);
                    i++;
                }
            }
            item.writeToAll(PdfName.OPT, pdfArray, 5);
            return true;
        }
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.export.and.the.display.array.must.have.the.same.size", new Object[0]));
    }

    public int getFieldType(String str) {
        PdfDictionary merged;
        PdfName asName;
        Item fieldItem = getFieldItem(str);
        if (fieldItem == null || (asName = merged.getAsName(PdfName.FT)) == null) {
            return 0;
        }
        PdfNumber asNumber = (merged = fieldItem.getMerged(0)).getAsNumber(PdfName.FF);
        int intValue = asNumber != null ? asNumber.intValue() : 0;
        if (PdfName.BTN.equals(asName)) {
            if ((65536 & intValue) != 0) {
                return 1;
            }
            return (intValue & 32768) != 0 ? 3 : 2;
        } else if (PdfName.TX.equals(asName)) {
            return 4;
        } else {
            if (PdfName.CH.equals(asName)) {
                return (intValue & 131072) != 0 ? 6 : 5;
            }
            if (PdfName.SIG.equals(asName)) {
                return 7;
            }
            return 0;
        }
    }

    public void exportAsFdf(FdfWriter fdfWriter) {
        for (Map.Entry next : this.fields.entrySet()) {
            String str = (String) next.getKey();
            if (((Item) next.getValue()).getMerged(0).get(PdfName.V) != null) {
                String field = getField(str);
                if (this.lastWasString) {
                    fdfWriter.setFieldAsString(str, field);
                } else {
                    fdfWriter.setFieldAsName(str, field);
                }
            }
        }
    }

    public boolean renameField(String str, String str2) {
        Item item;
        int lastIndexOf = str.lastIndexOf(46) + 1;
        int lastIndexOf2 = str2.lastIndexOf(46) + 1;
        if (lastIndexOf != lastIndexOf2 || !str.substring(0, lastIndexOf).equals(str2.substring(0, lastIndexOf2)) || this.fields.containsKey(str2) || (item = this.fields.get(str)) == null) {
            return false;
        }
        String substring = str2.substring(lastIndexOf2);
        item.writeToAll(PdfName.T, new PdfString(substring, PdfObject.TEXT_UNICODE), 5);
        item.markUsed(this, 4);
        this.fields.remove(str);
        this.fields.put(substring, item);
        return true;
    }

    public static Object[] splitDAelements(String str) {
        try {
            PRTokeniser pRTokeniser = new PRTokeniser(new RandomAccessFileOrArray(new RandomAccessSourceFactory().createSource(PdfEncodings.convertToBytes(str, (String) null))));
            ArrayList arrayList = new ArrayList();
            Object[] objArr = new Object[3];
            while (pRTokeniser.nextToken()) {
                if (pRTokeniser.getTokenType() != PRTokeniser.TokenType.COMMENT) {
                    if (pRTokeniser.getTokenType() == PRTokeniser.TokenType.OTHER) {
                        String stringValue = pRTokeniser.getStringValue();
                        if (stringValue.equals("Tf")) {
                            if (arrayList.size() >= 2) {
                                objArr[0] = arrayList.get(arrayList.size() - 2);
                                objArr[1] = new Float((String) arrayList.get(arrayList.size() - 1));
                            }
                        } else if (stringValue.equals("g")) {
                            if (arrayList.size() >= 1) {
                                float floatValue = new Float((String) arrayList.get(arrayList.size() - 1)).floatValue();
                                if (floatValue != 0.0f) {
                                    objArr[2] = new GrayColor(floatValue);
                                }
                            }
                        } else if (stringValue.equals("rg")) {
                            if (arrayList.size() >= 3) {
                                objArr[2] = new BaseColor(new Float((String) arrayList.get(arrayList.size() - 3)).floatValue(), new Float((String) arrayList.get(arrayList.size() - 2)).floatValue(), new Float((String) arrayList.get(arrayList.size() - 1)).floatValue());
                            }
                        } else if (stringValue.equals("k") && arrayList.size() >= 4) {
                            objArr[2] = new CMYKColor(new Float((String) arrayList.get(arrayList.size() - 4)).floatValue(), new Float((String) arrayList.get(arrayList.size() - 3)).floatValue(), new Float((String) arrayList.get(arrayList.size() - 2)).floatValue(), new Float((String) arrayList.get(arrayList.size() - 1)).floatValue());
                        }
                        arrayList.clear();
                    } else {
                        arrayList.add(pRTokeniser.getStringValue());
                    }
                }
            }
            return objArr;
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    /* JADX WARNING: type inference failed for: r8v14, types: [com.itextpdf.text.pdf.PdfObject] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decodeGenericDictionary(com.itextpdf.text.pdf.PdfDictionary r18, com.itextpdf.text.pdf.BaseField r19) throws java.io.IOException, com.itextpdf.text.DocumentException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.DA
            com.itextpdf.text.pdf.PdfString r3 = r1.getAsString(r3)
            r4 = 2
            r5 = 0
            r6 = 1
            if (r3 == 0) goto L_0x010d
            java.lang.String r3 = r3.toUnicodeString()
            java.lang.Object[] r3 = splitDAelements(r3)
            r7 = r3[r6]
            if (r7 == 0) goto L_0x0028
            r7 = r3[r6]
            java.lang.Float r7 = (java.lang.Float) r7
            float r7 = r7.floatValue()
            r2.setFontSize(r7)
        L_0x0028:
            r7 = r3[r4]
            if (r7 == 0) goto L_0x0033
            r7 = r3[r4]
            com.itextpdf.text.BaseColor r7 = (com.itextpdf.text.BaseColor) r7
            r2.setTextColor(r7)
        L_0x0033:
            r7 = r3[r5]
            if (r7 == 0) goto L_0x00de
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.DR
            com.itextpdf.text.pdf.PdfDictionary r7 = r1.getAsDict(r7)
            if (r7 == 0) goto L_0x00dc
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.FONT
            com.itextpdf.text.pdf.PdfDictionary r8 = r7.getAsDict(r8)
            if (r8 == 0) goto L_0x00dc
            com.itextpdf.text.pdf.PdfName r9 = new com.itextpdf.text.pdf.PdfName
            r10 = r3[r5]
            java.lang.String r10 = (java.lang.String) r10
            r9.<init>((java.lang.String) r10)
            com.itextpdf.text.pdf.PdfObject r8 = r8.get(r9)
            if (r8 == 0) goto L_0x00dc
            int r9 = r8.type()
            r10 = 10
            if (r9 != r10) goto L_0x00dc
            r9 = r8
            com.itextpdf.text.pdf.PRIndirectReference r9 = (com.itextpdf.text.pdf.PRIndirectReference) r9
            com.itextpdf.text.pdf.DocumentFont r10 = new com.itextpdf.text.pdf.DocumentFont
            com.itextpdf.text.pdf.PdfName r11 = com.itextpdf.text.pdf.PdfName.ENCODING
            com.itextpdf.text.pdf.PdfDictionary r7 = r7.getAsDict(r11)
            r10.<init>(r9, r7)
            r2.setFont(r10)
            int r7 = r9.getNumber()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.util.HashMap<java.lang.Integer, com.itextpdf.text.pdf.BaseFont> r9 = r0.extensionFonts
            java.lang.Object r9 = r9.get(r7)
            com.itextpdf.text.pdf.BaseFont r9 = (com.itextpdf.text.pdf.BaseFont) r9
            if (r9 != 0) goto L_0x00d1
            java.util.HashMap<java.lang.Integer, com.itextpdf.text.pdf.BaseFont> r10 = r0.extensionFonts
            boolean r10 = r10.containsKey(r7)
            if (r10 != 0) goto L_0x00d1
            com.itextpdf.text.pdf.PdfObject r8 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r8)
            com.itextpdf.text.pdf.PdfDictionary r8 = (com.itextpdf.text.pdf.PdfDictionary) r8
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.FONTDESCRIPTOR
            com.itextpdf.text.pdf.PdfDictionary r8 = r8.getAsDict(r10)
            if (r8 == 0) goto L_0x00d1
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.FONTFILE2
            com.itextpdf.text.pdf.PdfObject r10 = r8.get(r10)
            com.itextpdf.text.pdf.PdfObject r10 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r10)
            com.itextpdf.text.pdf.PRStream r10 = (com.itextpdf.text.pdf.PRStream) r10
            if (r10 != 0) goto L_0x00b2
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.FONTFILE3
            com.itextpdf.text.pdf.PdfObject r8 = r8.get(r10)
            com.itextpdf.text.pdf.PdfObject r8 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r8)
            r10 = r8
            com.itextpdf.text.pdf.PRStream r10 = (com.itextpdf.text.pdf.PRStream) r10
        L_0x00b2:
            if (r10 != 0) goto L_0x00bb
            java.util.HashMap<java.lang.Integer, com.itextpdf.text.pdf.BaseFont> r8 = r0.extensionFonts
            r10 = 0
            r8.put(r7, r10)
            goto L_0x00d1
        L_0x00bb:
            java.lang.String r11 = "font.ttf"
            java.lang.String r12 = "Identity-H"
            r13 = 1
            r14 = 0
            byte[] r15 = com.itextpdf.text.pdf.PdfReader.getStreamBytes(r10)     // Catch:{ Exception -> 0x00cc }
            r16 = 0
            com.itextpdf.text.pdf.BaseFont r8 = com.itextpdf.text.pdf.BaseFont.createFont(r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x00cc }
            r9 = r8
        L_0x00cc:
            java.util.HashMap<java.lang.Integer, com.itextpdf.text.pdf.BaseFont> r8 = r0.extensionFonts
            r8.put(r7, r9)
        L_0x00d1:
            boolean r7 = r2 instanceof com.itextpdf.text.pdf.TextField
            if (r7 == 0) goto L_0x00de
            r7 = r2
            com.itextpdf.text.pdf.TextField r7 = (com.itextpdf.text.pdf.TextField) r7
            r7.setExtensionFont(r9)
            goto L_0x00de
        L_0x00dc:
            r7 = r6
            goto L_0x00df
        L_0x00de:
            r7 = r5
        L_0x00df:
            if (r7 == 0) goto L_0x010d
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.BaseFont> r7 = r0.localFonts
            r8 = r3[r5]
            java.lang.Object r7 = r7.get(r8)
            com.itextpdf.text.pdf.BaseFont r7 = (com.itextpdf.text.pdf.BaseFont) r7
            if (r7 != 0) goto L_0x010a
            java.util.HashMap<java.lang.String, java.lang.String[]> r7 = stdFieldFontNames
            r3 = r3[r5]
            java.lang.Object r3 = r7.get(r3)
            java.lang.String[] r3 = (java.lang.String[]) r3
            if (r3 == 0) goto L_0x010d
            java.lang.String r7 = "winansi"
            int r8 = r3.length     // Catch:{ Exception -> 0x010d }
            if (r8 <= r6) goto L_0x0100
            r7 = r3[r6]     // Catch:{ Exception -> 0x010d }
        L_0x0100:
            r3 = r3[r5]     // Catch:{ Exception -> 0x010d }
            com.itextpdf.text.pdf.BaseFont r3 = com.itextpdf.text.pdf.BaseFont.createFont(r3, r7, r5)     // Catch:{ Exception -> 0x010d }
            r2.setFont(r3)     // Catch:{ Exception -> 0x010d }
            goto L_0x010d
        L_0x010a:
            r2.setFont(r7)
        L_0x010d:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.MK
            com.itextpdf.text.pdf.PdfDictionary r3 = r1.getAsDict(r3)
            if (r3 == 0) goto L_0x0145
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.BC
            com.itextpdf.text.pdf.PdfArray r7 = r3.getAsArray(r7)
            com.itextpdf.text.BaseColor r7 = r0.getMKColor(r7)
            r2.setBorderColor(r7)
            if (r7 == 0) goto L_0x0129
            r7 = 1065353216(0x3f800000, float:1.0)
            r2.setBorderWidth(r7)
        L_0x0129:
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.BG
            com.itextpdf.text.pdf.PdfArray r7 = r3.getAsArray(r7)
            com.itextpdf.text.BaseColor r7 = r0.getMKColor(r7)
            r2.setBackgroundColor(r7)
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.R
            com.itextpdf.text.pdf.PdfNumber r3 = r3.getAsNumber(r7)
            if (r3 == 0) goto L_0x0145
            int r3 = r3.intValue()
            r2.setRotation(r3)
        L_0x0145:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.F
            com.itextpdf.text.pdf.PdfNumber r3 = r1.getAsNumber(r3)
            r2.setVisibility(r4)
            r7 = 3
            if (r3 == 0) goto L_0x0170
            int r3 = r3.intValue()
            r8 = r3 & 4
            if (r8 == 0) goto L_0x0161
            r9 = r3 & 2
            if (r9 == 0) goto L_0x0161
            r2.setVisibility(r6)
            goto L_0x0170
        L_0x0161:
            if (r8 == 0) goto L_0x016b
            r3 = r3 & 32
            if (r3 == 0) goto L_0x016b
            r2.setVisibility(r7)
            goto L_0x0170
        L_0x016b:
            if (r8 == 0) goto L_0x0170
            r2.setVisibility(r5)
        L_0x0170:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.FF
            com.itextpdf.text.pdf.PdfNumber r3 = r1.getAsNumber(r3)
            if (r3 == 0) goto L_0x017d
            int r3 = r3.intValue()
            goto L_0x017e
        L_0x017d:
            r3 = r5
        L_0x017e:
            r2.setOptions(r3)
            r8 = 16777216(0x1000000, float:2.3509887E-38)
            r3 = r3 & r8
            if (r3 == 0) goto L_0x0195
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.MAXLEN
            com.itextpdf.text.pdf.PdfNumber r3 = r1.getAsNumber(r3)
            if (r3 == 0) goto L_0x0192
            int r5 = r3.intValue()
        L_0x0192:
            r2.setMaxCharacterLength(r5)
        L_0x0195:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.Q
            com.itextpdf.text.pdf.PdfNumber r3 = r1.getAsNumber(r3)
            if (r3 == 0) goto L_0x01b0
            int r5 = r3.intValue()
            if (r5 != r6) goto L_0x01a7
            r2.setAlignment(r6)
            goto L_0x01b0
        L_0x01a7:
            int r3 = r3.intValue()
            if (r3 != r4) goto L_0x01b0
            r2.setAlignment(r4)
        L_0x01b0:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.BS
            com.itextpdf.text.pdf.PdfDictionary r3 = r1.getAsDict(r3)
            r5 = 4
            if (r3 == 0) goto L_0x01fe
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.W
            com.itextpdf.text.pdf.PdfNumber r1 = r3.getAsNumber(r1)
            if (r1 == 0) goto L_0x01c8
            float r1 = r1.floatValue()
            r2.setBorderWidth(r1)
        L_0x01c8:
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.S
            com.itextpdf.text.pdf.PdfName r1 = r3.getAsName(r1)
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.D
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x01da
            r2.setBorderStyle(r6)
            goto L_0x0220
        L_0x01da:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.B
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x01e6
            r2.setBorderStyle(r4)
            goto L_0x0220
        L_0x01e6:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.I
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x01f2
            r2.setBorderStyle(r7)
            goto L_0x0220
        L_0x01f2:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.U
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0220
            r2.setBorderStyle(r5)
            goto L_0x0220
        L_0x01fe:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.BORDER
            com.itextpdf.text.pdf.PdfArray r1 = r1.getAsArray(r3)
            if (r1 == 0) goto L_0x0220
            int r3 = r1.size()
            if (r3 < r7) goto L_0x0217
            com.itextpdf.text.pdf.PdfNumber r3 = r1.getAsNumber(r4)
            float r3 = r3.floatValue()
            r2.setBorderWidth(r3)
        L_0x0217:
            int r1 = r1.size()
            if (r1 < r5) goto L_0x0220
            r2.setBorderStyle(r6)
        L_0x0220:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.AcroFields.decodeGenericDictionary(com.itextpdf.text.pdf.PdfDictionary, com.itextpdf.text.pdf.BaseField):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v1, types: [int] */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: type inference failed for: r6v5 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.pdf.PdfAppearance getAppearance(com.itextpdf.text.pdf.PdfDictionary r10, java.lang.String[] r11, java.lang.String r12) throws java.io.IOException, com.itextpdf.text.DocumentException {
        /*
            r9 = this;
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.FT
            com.itextpdf.text.pdf.PdfName r0 = r10.getAsName(r0)
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.BTN
            boolean r1 = r1.equals(r0)
            r2 = 270(0x10e, float:3.78E-43)
            r3 = 90
            r4 = 0
            r5 = 1
            r6 = 0
            if (r1 == 0) goto L_0x0067
            com.itextpdf.text.pdf.PdfName r11 = com.itextpdf.text.pdf.PdfName.FF
            com.itextpdf.text.pdf.PdfNumber r11 = r10.getAsNumber(r11)
            if (r11 == 0) goto L_0x0028
            int r11 = r11.intValue()
            r12 = 32768(0x8000, float:4.5918E-41)
            r11 = r11 & r12
            if (r11 == 0) goto L_0x0028
            r6 = r5
        L_0x0028:
            com.itextpdf.text.pdf.RadioCheckField r11 = new com.itextpdf.text.pdf.RadioCheckField
            com.itextpdf.text.pdf.PdfWriter r12 = r9.writer
            r11.<init>(r12, r4, r4, r4)
            r9.decodeGenericDictionary(r10, r11)
            com.itextpdf.text.pdf.PdfName r12 = com.itextpdf.text.pdf.PdfName.RECT
            com.itextpdf.text.pdf.PdfArray r12 = r10.getAsArray(r12)
            com.itextpdf.text.Rectangle r12 = com.itextpdf.text.pdf.PdfReader.getNormalizedRectangle(r12)
            int r0 = r11.getRotation()
            if (r0 == r3) goto L_0x0048
            int r0 = r11.getRotation()
            if (r0 != r2) goto L_0x004c
        L_0x0048:
            com.itextpdf.text.Rectangle r12 = r12.rotate()
        L_0x004c:
            r11.setBox(r12)
            if (r6 != 0) goto L_0x0055
            r12 = 3
            r11.setCheckType(r12)
        L_0x0055:
            com.itextpdf.text.pdf.PdfName r12 = com.itextpdf.text.pdf.PdfName.AS
            com.itextpdf.text.pdf.PdfName r10 = r10.getAsName(r12)
            com.itextpdf.text.pdf.PdfName r12 = com.itextpdf.text.pdf.PdfName.Off
            boolean r10 = r10.equals(r12)
            r10 = r10 ^ r5
            com.itextpdf.text.pdf.PdfAppearance r10 = r11.getAppearance(r6, r10)
            return r10
        L_0x0067:
            r9.topFirst = r6
            int r1 = r11.length
            if (r1 <= 0) goto L_0x006f
            r1 = r11[r6]
            goto L_0x0070
        L_0x006f:
            r1 = r4
        L_0x0070:
            java.util.Map<java.lang.String, com.itextpdf.text.pdf.TextField> r7 = r9.fieldCache
            if (r7 == 0) goto L_0x008b
            java.util.Map<java.lang.String, com.itextpdf.text.pdf.TextField> r7 = r9.fieldCache
            boolean r7 = r7.containsKey(r12)
            if (r7 != 0) goto L_0x007d
            goto L_0x008b
        L_0x007d:
            java.util.Map<java.lang.String, com.itextpdf.text.pdf.TextField> r2 = r9.fieldCache
            java.lang.Object r12 = r2.get(r12)
            com.itextpdf.text.pdf.TextField r12 = (com.itextpdf.text.pdf.TextField) r12
            com.itextpdf.text.pdf.PdfWriter r2 = r9.writer
            r12.setWriter(r2)
            goto L_0x00cc
        L_0x008b:
            com.itextpdf.text.pdf.TextField r7 = new com.itextpdf.text.pdf.TextField
            com.itextpdf.text.pdf.PdfWriter r8 = r9.writer
            r7.<init>(r8, r4, r4)
            float r4 = r9.extraMarginLeft
            float r8 = r9.extraMarginTop
            r7.setExtraMargin(r4, r8)
            r4 = 0
            r7.setBorderWidth(r4)
            java.util.ArrayList<com.itextpdf.text.pdf.BaseFont> r4 = r9.substitutionFonts
            r7.setSubstitutionFonts(r4)
            r9.decodeGenericDictionary(r10, r7)
            com.itextpdf.text.pdf.PdfName r4 = com.itextpdf.text.pdf.PdfName.RECT
            com.itextpdf.text.pdf.PdfArray r4 = r10.getAsArray(r4)
            com.itextpdf.text.Rectangle r4 = com.itextpdf.text.pdf.PdfReader.getNormalizedRectangle(r4)
            int r8 = r7.getRotation()
            if (r8 == r3) goto L_0x00bb
            int r3 = r7.getRotation()
            if (r3 != r2) goto L_0x00bf
        L_0x00bb:
            com.itextpdf.text.Rectangle r4 = r4.rotate()
        L_0x00bf:
            r7.setBox(r4)
            java.util.Map<java.lang.String, com.itextpdf.text.pdf.TextField> r2 = r9.fieldCache
            if (r2 == 0) goto L_0x00cb
            java.util.Map<java.lang.String, com.itextpdf.text.pdf.TextField> r2 = r9.fieldCache
            r2.put(r12, r7)
        L_0x00cb:
            r12 = r7
        L_0x00cc:
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.TX
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00e5
            int r10 = r11.length
            if (r10 <= 0) goto L_0x00e0
            r10 = r11[r6]
            if (r10 == 0) goto L_0x00e0
            r10 = r11[r6]
            r12.setText(r10)
        L_0x00e0:
            com.itextpdf.text.pdf.PdfAppearance r10 = r12.getAppearance()
            return r10
        L_0x00e5:
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.CH
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x00fb
            com.itextpdf.text.DocumentException r10 = new com.itextpdf.text.DocumentException
            java.lang.String r11 = "an.appearance.was.requested.without.a.variable.text.field"
            java.lang.Object[] r12 = new java.lang.Object[r6]
            java.lang.String r11 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r11, (java.lang.Object[]) r12)
            r10.<init>((java.lang.String) r11)
            throw r10
        L_0x00fb:
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.OPT
            com.itextpdf.text.pdf.PdfArray r0 = r10.getAsArray(r0)
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.FF
            com.itextpdf.text.pdf.PdfNumber r10 = r10.getAsNumber(r2)
            if (r10 == 0) goto L_0x010e
            int r10 = r10.intValue()
            goto L_0x010f
        L_0x010e:
            r10 = r6
        L_0x010f:
            r2 = 131072(0x20000, float:1.83671E-40)
            r10 = r10 & r2
            if (r10 == 0) goto L_0x011e
            if (r0 != 0) goto L_0x011e
            r12.setText(r1)
            com.itextpdf.text.pdf.PdfAppearance r10 = r12.getAppearance()
            return r10
        L_0x011e:
            if (r0 == 0) goto L_0x01ac
            int r2 = r0.size()
            java.lang.String[] r2 = new java.lang.String[r2]
            int r3 = r0.size()
            java.lang.String[] r3 = new java.lang.String[r3]
            r4 = r6
        L_0x012d:
            int r7 = r0.size()
            if (r4 >= r7) goto L_0x0161
            com.itextpdf.text.pdf.PdfObject r7 = r0.getPdfObject(r4)
            boolean r8 = r7.isString()
            if (r8 == 0) goto L_0x0148
            com.itextpdf.text.pdf.PdfString r7 = (com.itextpdf.text.pdf.PdfString) r7
            java.lang.String r7 = r7.toUnicodeString()
            r3[r4] = r7
            r2[r4] = r7
            goto L_0x015e
        L_0x0148:
            com.itextpdf.text.pdf.PdfArray r7 = (com.itextpdf.text.pdf.PdfArray) r7
            com.itextpdf.text.pdf.PdfString r8 = r7.getAsString(r6)
            java.lang.String r8 = r8.toUnicodeString()
            r3[r4] = r8
            com.itextpdf.text.pdf.PdfString r7 = r7.getAsString(r5)
            java.lang.String r7 = r7.toUnicodeString()
            r2[r4] = r7
        L_0x015e:
            int r4 = r4 + 1
            goto L_0x012d
        L_0x0161:
            if (r10 == 0) goto L_0x017c
        L_0x0163:
            int r10 = r2.length
            if (r6 >= r10) goto L_0x0174
            r10 = r3[r6]
            boolean r10 = r1.equals(r10)
            if (r10 == 0) goto L_0x0171
            r1 = r2[r6]
            goto L_0x0174
        L_0x0171:
            int r6 = r6 + 1
            goto L_0x0163
        L_0x0174:
            r12.setText(r1)
            com.itextpdf.text.pdf.PdfAppearance r10 = r12.getAppearance()
            return r10
        L_0x017c:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            r0 = r6
        L_0x0182:
            int r1 = r3.length
            if (r0 >= r1) goto L_0x01a3
            r1 = r6
        L_0x0186:
            int r4 = r11.length
            if (r1 >= r4) goto L_0x01a0
            r4 = r11[r1]
            if (r4 == 0) goto L_0x019d
            r5 = r3[r0]
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x019d
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            r10.add(r1)
            goto L_0x01a0
        L_0x019d:
            int r1 = r1 + 1
            goto L_0x0186
        L_0x01a0:
            int r0 = r0 + 1
            goto L_0x0182
        L_0x01a3:
            r12.setChoices(r2)
            r12.setChoiceExports(r3)
            r12.setChoiceSelections(r10)
        L_0x01ac:
            com.itextpdf.text.pdf.PdfAppearance r10 = r12.getListAppearance()
            int r11 = r12.getTopFirst()
            r9.topFirst = r11
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.AcroFields.getAppearance(com.itextpdf.text.pdf.PdfDictionary, java.lang.String[], java.lang.String):com.itextpdf.text.pdf.PdfAppearance");
    }

    /* access modifiers changed from: package-private */
    public PdfAppearance getAppearance(PdfDictionary pdfDictionary, String str, String str2) throws IOException, DocumentException {
        return getAppearance(pdfDictionary, new String[]{str}, str2);
    }

    /* access modifiers changed from: package-private */
    public BaseColor getMKColor(PdfArray pdfArray) {
        if (pdfArray == null) {
            return null;
        }
        int size = pdfArray.size();
        if (size == 1) {
            return new GrayColor(pdfArray.getAsNumber(0).floatValue());
        }
        switch (size) {
            case 3:
                return new BaseColor(ExtendedColor.normalize(pdfArray.getAsNumber(0).floatValue()), ExtendedColor.normalize(pdfArray.getAsNumber(1).floatValue()), ExtendedColor.normalize(pdfArray.getAsNumber(2).floatValue()));
            case 4:
                return new CMYKColor(pdfArray.getAsNumber(0).floatValue(), pdfArray.getAsNumber(1).floatValue(), pdfArray.getAsNumber(2).floatValue(), pdfArray.getAsNumber(3).floatValue());
            default:
                return null;
        }
    }

    public String getFieldRichValue(String str) {
        Item item;
        PdfString asString;
        if (this.xfa.isXfaPresent() || (item = this.fields.get(str)) == null || (asString = item.getMerged(0).getAsString(PdfName.RV)) == null) {
            return null;
        }
        return asString.toString();
    }

    public String getField(String str) {
        if (this.xfa.isXfaPresent()) {
            String findFieldName = this.xfa.findFieldName(str, this);
            if (findFieldName == null) {
                return null;
            }
            return XfaForm.getNodeText(this.xfa.findDatasetsNode(XfaForm.Xml2Som.getShortName(findFieldName)));
        }
        Item item = this.fields.get(str);
        if (item == null) {
            return null;
        }
        this.lastWasString = false;
        PdfDictionary merged = item.getMerged(0);
        PdfObject pdfObject = PdfReader.getPdfObject(merged.get(PdfName.V));
        if (pdfObject == null) {
            return "";
        }
        if (pdfObject instanceof PRStream) {
            try {
                return new String(PdfReader.getStreamBytes((PRStream) pdfObject));
            } catch (IOException e) {
                throw new ExceptionConverter(e);
            }
        } else {
            if (PdfName.BTN.equals(merged.getAsName(PdfName.FT))) {
                PdfNumber asNumber = merged.getAsNumber(PdfName.FF);
                if (((asNumber != null ? asNumber.intValue() : 0) & 65536) != 0) {
                    return "";
                }
                String str2 = "";
                if (pdfObject instanceof PdfName) {
                    str2 = PdfName.decodeName(pdfObject.toString());
                } else if (pdfObject instanceof PdfString) {
                    str2 = ((PdfString) pdfObject).toUnicodeString();
                }
                PdfArray asArray = item.getValue(0).getAsArray(PdfName.OPT);
                if (asArray == null) {
                    return str2;
                }
                try {
                    String unicodeString = asArray.getAsString(Integer.parseInt(str2)).toUnicodeString();
                    try {
                        this.lastWasString = true;
                    } catch (Exception unused) {
                    }
                    return unicodeString;
                } catch (Exception unused2) {
                    return str2;
                }
            } else if (!(pdfObject instanceof PdfString)) {
                return pdfObject instanceof PdfName ? PdfName.decodeName(pdfObject.toString()) : "";
            } else {
                this.lastWasString = true;
                return ((PdfString) pdfObject).toUnicodeString();
            }
        }
    }

    public String[] getListSelection(String str) {
        String[] strArr;
        PdfArray asArray;
        String field = getField(str);
        int i = 0;
        if (field == null) {
            strArr = new String[0];
        } else {
            strArr = new String[]{field};
        }
        Item item = this.fields.get(str);
        if (item == null || (asArray = item.getMerged(0).getAsArray(PdfName.I)) == null) {
            return strArr;
        }
        String[] strArr2 = new String[asArray.size()];
        String[] listOptionExport = getListOptionExport(str);
        ListIterator<PdfObject> listIterator = asArray.listIterator();
        while (listIterator.hasNext()) {
            strArr2[i] = listOptionExport[((PdfNumber) listIterator.next()).intValue()];
            i++;
        }
        return strArr2;
    }

    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v41, types: [boolean] */
    /* JADX WARNING: type inference failed for: r5v42 */
    public boolean setFieldProperty(String str, String str2, Object obj, int[] iArr) {
        PdfString asString;
        PdfString asString2;
        FontDetails fontDetails;
        String str3 = str2;
        ? r5 = 0;
        if (this.writer == null) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("this.acrofields.instance.is.read.only", new Object[0]));
        }
        try {
            Item item = this.fields.get(str);
            if (item == null) {
                return false;
            }
            InstHit instHit = new InstHit(iArr);
            if (str3.equalsIgnoreCase("textfont")) {
                int i = 0;
                while (i < item.size()) {
                    if (instHit.isHit(i)) {
                        PdfDictionary merged = item.getMerged(i);
                        PdfString asString3 = merged.getAsString(PdfName.DA);
                        PdfDictionary asDict = merged.getAsDict(PdfName.DR);
                        if (asString3 != null) {
                            if (asDict == null) {
                                asDict = new PdfDictionary();
                                merged.put(PdfName.DR, asDict);
                            }
                            Object[] splitDAelements = splitDAelements(asString3.toUnicodeString());
                            PdfAppearance pdfAppearance = new PdfAppearance();
                            if (splitDAelements[r5] != null) {
                                BaseFont baseFont = (BaseFont) obj;
                                PdfName pdfName = PdfAppearance.stdFieldFontNames.get(baseFont.getPostscriptFontName());
                                if (pdfName == null) {
                                    pdfName = new PdfName(baseFont.getPostscriptFontName());
                                }
                                PdfDictionary asDict2 = asDict.getAsDict(PdfName.FONT);
                                if (asDict2 == null) {
                                    asDict2 = new PdfDictionary();
                                    asDict.put(PdfName.FONT, asDict2);
                                }
                                PdfIndirectReference pdfIndirectReference = (PdfIndirectReference) asDict2.get(pdfName);
                                PdfDictionary asDict3 = this.reader.getCatalog().getAsDict(PdfName.ACROFORM);
                                markUsed(asDict3);
                                PdfDictionary asDict4 = asDict3.getAsDict(PdfName.DR);
                                if (asDict4 == null) {
                                    asDict4 = new PdfDictionary();
                                    asDict3.put(PdfName.DR, asDict4);
                                }
                                markUsed(asDict4);
                                PdfDictionary asDict5 = asDict4.getAsDict(PdfName.FONT);
                                if (asDict5 == null) {
                                    asDict5 = new PdfDictionary();
                                    asDict4.put(PdfName.FONT, asDict5);
                                }
                                markUsed(asDict5);
                                PdfIndirectReference pdfIndirectReference2 = (PdfIndirectReference) asDict5.get(pdfName);
                                if (pdfIndirectReference2 != null) {
                                    if (pdfIndirectReference == null) {
                                        asDict2.put(pdfName, pdfIndirectReference2);
                                    }
                                } else if (pdfIndirectReference == null) {
                                    if (baseFont.getFontType() == 4) {
                                        fontDetails = new FontDetails((PdfName) null, ((DocumentFont) baseFont).getIndirectReference(), baseFont);
                                    } else {
                                        baseFont.setSubset(r5);
                                        fontDetails = this.writer.addSimple(baseFont);
                                        this.localFonts.put(pdfName.toString().substring(1), baseFont);
                                    }
                                    asDict5.put(pdfName, fontDetails.getIndirectReference());
                                    asDict2.put(pdfName, fontDetails.getIndirectReference());
                                }
                                pdfAppearance.getInternalBuffer().append(pdfName.getBytes()).append(' ').append(((Float) splitDAelements[1]).floatValue()).append(" Tf ");
                                if (splitDAelements[2] != null) {
                                    pdfAppearance.setColorFill((BaseColor) splitDAelements[2]);
                                }
                                PdfString pdfString = new PdfString(pdfAppearance.toString());
                                item.getMerged(i).put(PdfName.DA, pdfString);
                                item.getWidget(i).put(PdfName.DA, pdfString);
                                markUsed(item.getWidget(i));
                            }
                        }
                    }
                    i++;
                    r5 = 0;
                }
            } else if (str3.equalsIgnoreCase("textcolor")) {
                for (int i2 = 0; i2 < item.size(); i2++) {
                    if (instHit.isHit(i2) && (asString2 = item.getMerged(i2).getAsString(PdfName.DA)) != null) {
                        Object[] splitDAelements2 = splitDAelements(asString2.toUnicodeString());
                        PdfAppearance pdfAppearance2 = new PdfAppearance();
                        if (splitDAelements2[0] != null) {
                            pdfAppearance2.getInternalBuffer().append(new PdfName((String) splitDAelements2[0]).getBytes()).append(' ').append(((Float) splitDAelements2[1]).floatValue()).append(" Tf ");
                            pdfAppearance2.setColorFill((BaseColor) obj);
                            PdfString pdfString2 = new PdfString(pdfAppearance2.toString());
                            item.getMerged(i2).put(PdfName.DA, pdfString2);
                            item.getWidget(i2).put(PdfName.DA, pdfString2);
                            markUsed(item.getWidget(i2));
                        }
                    }
                }
            } else if (str3.equalsIgnoreCase("textsize")) {
                for (int i3 = 0; i3 < item.size(); i3++) {
                    if (instHit.isHit(i3) && (asString = item.getMerged(i3).getAsString(PdfName.DA)) != null) {
                        Object[] splitDAelements3 = splitDAelements(asString.toUnicodeString());
                        PdfAppearance pdfAppearance3 = new PdfAppearance();
                        if (splitDAelements3[0] != null) {
                            pdfAppearance3.getInternalBuffer().append(new PdfName((String) splitDAelements3[0]).getBytes()).append(' ').append(((Float) obj).floatValue()).append(" Tf ");
                            if (splitDAelements3[2] != null) {
                                pdfAppearance3.setColorFill((BaseColor) splitDAelements3[2]);
                            }
                            PdfString pdfString3 = new PdfString(pdfAppearance3.toString());
                            item.getMerged(i3).put(PdfName.DA, pdfString3);
                            item.getWidget(i3).put(PdfName.DA, pdfString3);
                            markUsed(item.getWidget(i3));
                        }
                    }
                }
            } else {
                if (!str3.equalsIgnoreCase(HtmlTags.BGCOLOR)) {
                    if (!str3.equalsIgnoreCase("bordercolor")) {
                        return false;
                    }
                }
                PdfName pdfName2 = str3.equalsIgnoreCase(HtmlTags.BGCOLOR) ? PdfName.BG : PdfName.BC;
                for (int i4 = 0; i4 < item.size(); i4++) {
                    if (instHit.isHit(i4)) {
                        PdfDictionary asDict6 = item.getMerged(i4).getAsDict(PdfName.MK);
                        if (asDict6 != null) {
                            markUsed(asDict6);
                        } else if (obj == null) {
                            return true;
                        } else {
                            asDict6 = new PdfDictionary();
                            item.getMerged(i4).put(PdfName.MK, asDict6);
                            item.getWidget(i4).put(PdfName.MK, asDict6);
                            markUsed(item.getWidget(i4));
                        }
                        if (obj == null) {
                            asDict6.remove(pdfName2);
                        } else {
                            asDict6.put(pdfName2, PdfFormField.getMKColor((BaseColor) obj));
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public boolean setFieldProperty(String str, String str2, int i, int[] iArr) {
        int i2 = 0;
        if (this.writer == null) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("this.acrofields.instance.is.read.only", new Object[0]));
        }
        Item item = this.fields.get(str);
        if (item == null) {
            return false;
        }
        InstHit instHit = new InstHit(iArr);
        if (str2.equalsIgnoreCase("flags")) {
            PdfNumber pdfNumber = new PdfNumber(i);
            while (i2 < item.size()) {
                if (instHit.isHit(i2)) {
                    item.getMerged(i2).put(PdfName.F, pdfNumber);
                    item.getWidget(i2).put(PdfName.F, pdfNumber);
                    markUsed(item.getWidget(i2));
                }
                i2++;
            }
            return true;
        } else if (str2.equalsIgnoreCase("setflags")) {
            for (int i3 = 0; i3 < item.size(); i3++) {
                if (instHit.isHit(i3)) {
                    PdfNumber asNumber = item.getWidget(i3).getAsNumber(PdfName.F);
                    PdfNumber pdfNumber2 = new PdfNumber((asNumber != null ? asNumber.intValue() : 0) | i);
                    item.getMerged(i3).put(PdfName.F, pdfNumber2);
                    item.getWidget(i3).put(PdfName.F, pdfNumber2);
                    markUsed(item.getWidget(i3));
                }
            }
            return true;
        } else if (str2.equalsIgnoreCase("clrflags")) {
            for (int i4 = 0; i4 < item.size(); i4++) {
                if (instHit.isHit(i4)) {
                    PdfDictionary widget = item.getWidget(i4);
                    PdfNumber asNumber2 = widget.getAsNumber(PdfName.F);
                    PdfNumber pdfNumber3 = new PdfNumber((asNumber2 != null ? asNumber2.intValue() : 0) & (i ^ -1));
                    item.getMerged(i4).put(PdfName.F, pdfNumber3);
                    widget.put(PdfName.F, pdfNumber3);
                    markUsed(widget);
                }
            }
            return true;
        } else if (str2.equalsIgnoreCase("fflags")) {
            PdfNumber pdfNumber4 = new PdfNumber(i);
            while (i2 < item.size()) {
                if (instHit.isHit(i2)) {
                    item.getMerged(i2).put(PdfName.FF, pdfNumber4);
                    item.getValue(i2).put(PdfName.FF, pdfNumber4);
                    markUsed(item.getValue(i2));
                }
                i2++;
            }
            return true;
        } else if (str2.equalsIgnoreCase("setfflags")) {
            for (int i5 = 0; i5 < item.size(); i5++) {
                if (instHit.isHit(i5)) {
                    PdfDictionary value = item.getValue(i5);
                    PdfNumber asNumber3 = value.getAsNumber(PdfName.FF);
                    PdfNumber pdfNumber5 = new PdfNumber((asNumber3 != null ? asNumber3.intValue() : 0) | i);
                    item.getMerged(i5).put(PdfName.FF, pdfNumber5);
                    value.put(PdfName.FF, pdfNumber5);
                    markUsed(value);
                }
            }
            return true;
        } else if (!str2.equalsIgnoreCase("clrfflags")) {
            return false;
        } else {
            for (int i6 = 0; i6 < item.size(); i6++) {
                if (instHit.isHit(i6)) {
                    PdfDictionary value2 = item.getValue(i6);
                    PdfNumber asNumber4 = value2.getAsNumber(PdfName.FF);
                    PdfNumber pdfNumber6 = new PdfNumber((asNumber4 != null ? asNumber4.intValue() : 0) & (i ^ -1));
                    item.getMerged(i6).put(PdfName.FF, pdfNumber6);
                    value2.put(PdfName.FF, pdfNumber6);
                    markUsed(value2);
                }
            }
            return true;
        }
    }

    public void mergeXfaData(Node node) throws IOException, DocumentException {
        XfaForm.Xml2SomDatasets xml2SomDatasets = new XfaForm.Xml2SomDatasets(node);
        Iterator<String> it = xml2SomDatasets.getOrder().iterator();
        while (it.hasNext()) {
            String next = it.next();
            setField(next, XfaForm.getNodeText(xml2SomDatasets.getName2Node().get(next)));
        }
    }

    public void setFields(FdfReader fdfReader) throws IOException, DocumentException {
        for (String next : fdfReader.getFields().keySet()) {
            String fieldValue = fdfReader.getFieldValue(next);
            if (fieldValue != null) {
                setField(next, fieldValue);
            }
        }
    }

    public void setFields(XfdfReader xfdfReader) throws IOException, DocumentException {
        for (String next : xfdfReader.getFields().keySet()) {
            String fieldValue = xfdfReader.getFieldValue(next);
            if (fieldValue != null) {
                setField(next, fieldValue);
            }
            List<String> listValues = xfdfReader.getListValues(next);
            if (listValues != null) {
                setListSelection(fieldValue, (String[]) listValues.toArray(new String[listValues.size()]));
            }
        }
    }

    public boolean regenerateField(String str) throws IOException, DocumentException {
        String field = getField(str);
        return setField(str, field, field);
    }

    public boolean setField(String str, String str2) throws IOException, DocumentException {
        return setField(str, str2, (String) null);
    }

    public boolean setField(String str, String str2, boolean z) throws IOException, DocumentException {
        return setField(str, str2, (String) null, z);
    }

    public boolean setFieldRichValue(String str, String str2) throws DocumentException, IOException {
        if (this.writer == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("this.acrofields.instance.is.read.only", new Object[0]));
        }
        Item fieldItem = getFieldItem(str);
        if (fieldItem == null || getFieldType(str) != 4) {
            return false;
        }
        PdfNumber asNumber = fieldItem.getMerged(0).getAsNumber(PdfName.FF);
        if (((asNumber != null ? asNumber.intValue() : 0) & 33554432) == 0) {
            return false;
        }
        fieldItem.writeToAll(PdfName.RV, new PdfString(str2), 5);
        fieldItem.writeToAll(PdfName.V, new PdfString(XmlToTxt.parse(new ByteArrayInputStream(str2.getBytes()))), 5);
        return true;
    }

    public boolean setField(String str, String str2, String str3) throws IOException, DocumentException {
        return setField(str, str2, str3, false);
    }

    public boolean setField(String str, String str2, String str3, boolean z) throws IOException, DocumentException {
        if (this.writer == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("this.acrofields.instance.is.read.only", new Object[0]));
        }
        if (this.xfa.isXfaPresent()) {
            str = this.xfa.findFieldName(str, this);
            if (str == null) {
                return false;
            }
            String shortName = XfaForm.Xml2Som.getShortName(str);
            Node findDatasetsNode = this.xfa.findDatasetsNode(shortName);
            if (findDatasetsNode == null) {
                findDatasetsNode = this.xfa.getDatasetsSom().insertNode(this.xfa.getDatasetsNode(), shortName);
            }
            this.xfa.setNodeText(findDatasetsNode, str2);
        }
        Item item = this.fields.get(str);
        if (item == null) {
            return false;
        }
        PdfDictionary merged = item.getMerged(0);
        PdfName asName = merged.getAsName(PdfName.FT);
        if (PdfName.TX.equals(asName)) {
            PdfNumber asNumber = merged.getAsNumber(PdfName.MAXLEN);
            int intValue = asNumber != null ? asNumber.intValue() : 0;
            if (intValue > 0) {
                str2 = str2.substring(0, Math.min(intValue, str2.length()));
            }
        }
        if (str3 == null) {
            str3 = str2;
        }
        if (PdfName.TX.equals(asName) || PdfName.CH.equals(asName)) {
            PdfString pdfString = new PdfString(str2, PdfObject.TEXT_UNICODE);
            for (int i = 0; i < item.size(); i++) {
                PdfDictionary value = item.getValue(i);
                value.put(PdfName.V, pdfString);
                value.remove(PdfName.I);
                markUsed(value);
                PdfDictionary merged2 = item.getMerged(i);
                merged2.remove(PdfName.I);
                merged2.put(PdfName.V, pdfString);
                PdfDictionary widget = item.getWidget(i);
                if (this.generateAppearances) {
                    PdfAppearance appearance = getAppearance(merged2, str3, str);
                    if (PdfName.CH.equals(asName)) {
                        PdfNumber pdfNumber = new PdfNumber(this.topFirst);
                        widget.put(PdfName.TI, pdfNumber);
                        merged2.put(PdfName.TI, pdfNumber);
                    }
                    PdfDictionary asDict = widget.getAsDict(PdfName.AP);
                    if (asDict == null) {
                        asDict = new PdfDictionary();
                        widget.put(PdfName.AP, asDict);
                        merged2.put(PdfName.AP, asDict);
                    }
                    asDict.put(PdfName.N, appearance.getIndirectReference());
                    this.writer.releaseTemplate(appearance);
                } else {
                    widget.remove(PdfName.AP);
                    merged2.remove(PdfName.AP);
                }
                markUsed(widget);
            }
            return true;
        } else if (!PdfName.BTN.equals(asName)) {
            return false;
        } else {
            PdfNumber asNumber2 = item.getMerged(0).getAsNumber(PdfName.FF);
            if (((asNumber2 != null ? asNumber2.intValue() : 0) & 65536) != 0) {
                try {
                    Image instance = Image.getInstance(Base64.decode(str2));
                    PushbuttonField newPushbuttonFromField = getNewPushbuttonFromField(str);
                    newPushbuttonFromField.setImage(instance);
                    replacePushbuttonField(str, newPushbuttonFromField.getField());
                    return true;
                } catch (Exception unused) {
                    return false;
                }
            } else {
                PdfName pdfName = new PdfName(str2);
                ArrayList arrayList = new ArrayList();
                PdfArray asArray = item.getValue(0).getAsArray(PdfName.OPT);
                if (asArray != null) {
                    for (int i2 = 0; i2 < asArray.size(); i2++) {
                        PdfString asString = asArray.getAsString(i2);
                        if (asString != null) {
                            arrayList.add(asString.toUnicodeString());
                        } else {
                            arrayList.add((Object) null);
                        }
                    }
                }
                int indexOf = arrayList.indexOf(str2);
                if (indexOf >= 0) {
                    pdfName = new PdfName(String.valueOf(indexOf));
                }
                for (int i3 = 0; i3 < item.size(); i3++) {
                    PdfDictionary merged3 = item.getMerged(i3);
                    PdfDictionary widget2 = item.getWidget(i3);
                    PdfDictionary value2 = item.getValue(i3);
                    markUsed(item.getValue(i3));
                    value2.put(PdfName.V, pdfName);
                    merged3.put(PdfName.V, pdfName);
                    markUsed(widget2);
                    PdfDictionary asDict2 = widget2.getAsDict(PdfName.AP);
                    if (asDict2 == null) {
                        return false;
                    }
                    PdfDictionary asDict3 = asDict2.getAsDict(PdfName.N);
                    if (isInAP(asDict3, pdfName) || asDict3 == null) {
                        merged3.put(PdfName.AS, pdfName);
                        widget2.put(PdfName.AS, pdfName);
                    } else {
                        merged3.put(PdfName.AS, PdfName.Off);
                        widget2.put(PdfName.AS, PdfName.Off);
                    }
                    if (this.generateAppearances && !z) {
                        PdfAppearance appearance2 = getAppearance(merged3, str3, str);
                        if (asDict3 != null) {
                            asDict3.put(merged3.getAsName(PdfName.AS), appearance2.getIndirectReference());
                        } else {
                            asDict2.put(PdfName.N, appearance2.getIndirectReference());
                        }
                        this.writer.releaseTemplate(appearance2);
                    }
                }
                return true;
            }
        }
    }

    public boolean setListSelection(String str, String[] strArr) throws IOException, DocumentException {
        Item fieldItem = getFieldItem(str);
        if (fieldItem == null) {
            return false;
        }
        PdfDictionary merged = fieldItem.getMerged(0);
        if (!PdfName.CH.equals(merged.getAsName(PdfName.FT))) {
            return false;
        }
        String[] listOptionExport = getListOptionExport(str);
        PdfArray pdfArray = new PdfArray();
        for (String str2 : strArr) {
            int i = 0;
            while (true) {
                if (i >= listOptionExport.length) {
                    break;
                } else if (listOptionExport[i].equals(str2)) {
                    pdfArray.add((PdfObject) new PdfNumber(i));
                    break;
                } else {
                    i++;
                }
            }
        }
        fieldItem.writeToAll(PdfName.I, pdfArray, 5);
        PdfArray pdfArray2 = new PdfArray();
        for (String pdfString : strArr) {
            pdfArray2.add((PdfObject) new PdfString(pdfString));
        }
        fieldItem.writeToAll(PdfName.V, pdfArray2, 5);
        PdfAppearance appearance = getAppearance(merged, strArr, str);
        PdfDictionary pdfDictionary = new PdfDictionary();
        pdfDictionary.put(PdfName.N, appearance.getIndirectReference());
        fieldItem.writeToAll(PdfName.AP, pdfDictionary, 3);
        this.writer.releaseTemplate(appearance);
        fieldItem.markUsed(this, 6);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isInAP(PdfDictionary pdfDictionary, PdfName pdfName) {
        return (pdfDictionary == null || pdfDictionary.get(pdfName) == null) ? false : true;
    }

    public Map<String, Item> getFields() {
        return this.fields;
    }

    public Item getFieldItem(String str) {
        if (!this.xfa.isXfaPresent() || (str = this.xfa.findFieldName(str, this)) != null) {
            return this.fields.get(str);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r1.xfa.findFieldName(r2, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getTranslatedFieldName(java.lang.String r2) {
        /*
            r1 = this;
            com.itextpdf.text.pdf.XfaForm r0 = r1.xfa
            boolean r0 = r0.isXfaPresent()
            if (r0 == 0) goto L_0x0011
            com.itextpdf.text.pdf.XfaForm r0 = r1.xfa
            java.lang.String r0 = r0.findFieldName(r2, r1)
            if (r0 == 0) goto L_0x0011
            r2 = r0
        L_0x0011:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.AcroFields.getTranslatedFieldName(java.lang.String):java.lang.String");
    }

    public List<FieldPosition> getFieldPositions(String str) {
        Rectangle rectangle;
        Item fieldItem = getFieldItem(str);
        if (fieldItem == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < fieldItem.size(); i++) {
            try {
                PdfArray asArray = fieldItem.getWidget(i).getAsArray(PdfName.RECT);
                if (asArray != null) {
                    Rectangle normalizedRectangle = PdfReader.getNormalizedRectangle(asArray);
                    int intValue = fieldItem.getPage(i).intValue();
                    int pageRotation = this.reader.getPageRotation(intValue);
                    FieldPosition fieldPosition = new FieldPosition();
                    fieldPosition.page = intValue;
                    if (pageRotation != 0) {
                        Rectangle pageSize = this.reader.getPageSize(intValue);
                        if (pageRotation == 90) {
                            rectangle = new Rectangle(normalizedRectangle.getBottom(), pageSize.getRight() - normalizedRectangle.getLeft(), normalizedRectangle.getTop(), pageSize.getRight() - normalizedRectangle.getRight());
                        } else if (pageRotation == 180) {
                            rectangle = new Rectangle(pageSize.getRight() - normalizedRectangle.getLeft(), pageSize.getTop() - normalizedRectangle.getBottom(), pageSize.getRight() - normalizedRectangle.getRight(), pageSize.getTop() - normalizedRectangle.getTop());
                        } else if (pageRotation != 270) {
                            normalizedRectangle.normalize();
                        } else {
                            rectangle = new Rectangle(pageSize.getTop() - normalizedRectangle.getBottom(), normalizedRectangle.getLeft(), pageSize.getTop() - normalizedRectangle.getTop(), normalizedRectangle.getRight());
                        }
                        normalizedRectangle = rectangle;
                        normalizedRectangle.normalize();
                    }
                    fieldPosition.position = normalizedRectangle;
                    arrayList.add(fieldPosition);
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    private int removeRefFromArray(PdfArray pdfArray, PdfObject pdfObject) {
        if (pdfObject == null || !pdfObject.isIndirect()) {
            return pdfArray.size();
        }
        PdfIndirectReference pdfIndirectReference = (PdfIndirectReference) pdfObject;
        int i = 0;
        while (i < pdfArray.size()) {
            PdfObject pdfObject2 = pdfArray.getPdfObject(i);
            if (pdfObject2.isIndirect() && ((PdfIndirectReference) pdfObject2).getNumber() == pdfIndirectReference.getNumber()) {
                pdfArray.remove(i);
                i--;
            }
            i++;
        }
        return pdfArray.size();
    }

    public boolean removeFieldsFromPage(int i) {
        if (i < 1) {
            return false;
        }
        String[] strArr = new String[this.fields.size()];
        this.fields.keySet().toArray(strArr);
        boolean z = false;
        for (String removeField : strArr) {
            z = z || removeField(removeField, i);
        }
        return z;
    }

    public boolean removeField(String str, int i) {
        PdfDictionary pdfDictionary;
        PdfArray asArray;
        PdfIndirectReference asIndirectObject;
        Item fieldItem = getFieldItem(str);
        int i2 = 0;
        if (fieldItem == null || (pdfDictionary = (PdfDictionary) PdfReader.getPdfObject(this.reader.getCatalog().get(PdfName.ACROFORM), this.reader.getCatalog())) == null || (asArray = pdfDictionary.getAsArray(PdfName.FIELDS)) == null) {
            return false;
        }
        while (i2 < fieldItem.size()) {
            int intValue = fieldItem.getPage(i2).intValue();
            if (i == -1 || i == intValue) {
                PdfIndirectReference widgetRef = fieldItem.getWidgetRef(i2);
                PdfDictionary widget = fieldItem.getWidget(i2);
                PdfDictionary pageN = this.reader.getPageN(intValue);
                PdfArray asArray2 = pageN.getAsArray(PdfName.ANNOTS);
                if (asArray2 != null) {
                    if (removeRefFromArray(asArray2, widgetRef) == 0) {
                        pageN.remove(PdfName.ANNOTS);
                        markUsed(pageN);
                    } else {
                        markUsed(asArray2);
                    }
                }
                PdfReader.killIndirect(widgetRef);
                while (true) {
                    asIndirectObject = widget.getAsIndirectObject(PdfName.PARENT);
                    if (asIndirectObject == null) {
                        break;
                    }
                    widget = widget.getAsDict(PdfName.PARENT);
                    if (removeRefFromArray(widget.getAsArray(PdfName.KIDS), widgetRef) != 0) {
                        break;
                    }
                    PdfReader.killIndirect(asIndirectObject);
                    widgetRef = asIndirectObject;
                }
                if (asIndirectObject == null) {
                    removeRefFromArray(asArray, widgetRef);
                    markUsed(asArray);
                }
                if (i != -1) {
                    fieldItem.remove(i2);
                    i2--;
                }
            }
            i2++;
        }
        if (i == -1 || fieldItem.size() == 0) {
            this.fields.remove(str);
        }
        return true;
    }

    public boolean removeField(String str) {
        return removeField(str, -1);
    }

    public boolean isGenerateAppearances() {
        return this.generateAppearances;
    }

    public void setGenerateAppearances(boolean z) {
        this.generateAppearances = z;
        PdfDictionary asDict = this.reader.getCatalog().getAsDict(PdfName.ACROFORM);
        if (z) {
            asDict.remove(PdfName.NEEDAPPEARANCES);
        } else {
            asDict.put(PdfName.NEEDAPPEARANCES, PdfBoolean.PDFTRUE);
        }
    }

    public static class Item {
        public static final int WRITE_MERGED = 1;
        public static final int WRITE_VALUE = 4;
        public static final int WRITE_WIDGET = 2;
        protected ArrayList<PdfDictionary> merged = new ArrayList<>();
        protected ArrayList<Integer> page = new ArrayList<>();
        protected ArrayList<Integer> tabOrder = new ArrayList<>();
        protected ArrayList<PdfDictionary> values = new ArrayList<>();
        protected ArrayList<PdfIndirectReference> widget_refs = new ArrayList<>();
        protected ArrayList<PdfDictionary> widgets = new ArrayList<>();

        public void writeToAll(PdfName pdfName, PdfObject pdfObject, int i) {
            if ((i & 1) != 0) {
                for (int i2 = 0; i2 < this.merged.size(); i2++) {
                    getMerged(i2).put(pdfName, pdfObject);
                }
            }
            if ((i & 2) != 0) {
                for (int i3 = 0; i3 < this.widgets.size(); i3++) {
                    getWidget(i3).put(pdfName, pdfObject);
                }
            }
            if ((i & 4) != 0) {
                for (int i4 = 0; i4 < this.values.size(); i4++) {
                    getValue(i4).put(pdfName, pdfObject);
                }
            }
        }

        public void markUsed(AcroFields acroFields, int i) {
            if ((i & 4) != 0) {
                for (int i2 = 0; i2 < size(); i2++) {
                    acroFields.markUsed(getValue(i2));
                }
            }
            if ((i & 2) != 0) {
                for (int i3 = 0; i3 < size(); i3++) {
                    acroFields.markUsed(getWidget(i3));
                }
            }
        }

        public int size() {
            return this.values.size();
        }

        /* access modifiers changed from: package-private */
        public void remove(int i) {
            this.values.remove(i);
            this.widgets.remove(i);
            this.widget_refs.remove(i);
            this.merged.remove(i);
            this.page.remove(i);
            this.tabOrder.remove(i);
        }

        public PdfDictionary getValue(int i) {
            return this.values.get(i);
        }

        /* access modifiers changed from: package-private */
        public void addValue(PdfDictionary pdfDictionary) {
            this.values.add(pdfDictionary);
        }

        public PdfDictionary getWidget(int i) {
            return this.widgets.get(i);
        }

        /* access modifiers changed from: package-private */
        public void addWidget(PdfDictionary pdfDictionary) {
            this.widgets.add(pdfDictionary);
        }

        public PdfIndirectReference getWidgetRef(int i) {
            return this.widget_refs.get(i);
        }

        /* access modifiers changed from: package-private */
        public void addWidgetRef(PdfIndirectReference pdfIndirectReference) {
            this.widget_refs.add(pdfIndirectReference);
        }

        public PdfDictionary getMerged(int i) {
            return this.merged.get(i);
        }

        /* access modifiers changed from: package-private */
        public void addMerged(PdfDictionary pdfDictionary) {
            this.merged.add(pdfDictionary);
        }

        public Integer getPage(int i) {
            return this.page.get(i);
        }

        /* access modifiers changed from: package-private */
        public void addPage(int i) {
            this.page.add(Integer.valueOf(i));
        }

        /* access modifiers changed from: package-private */
        public void forcePage(int i, int i2) {
            this.page.set(i, Integer.valueOf(i2));
        }

        public Integer getTabOrder(int i) {
            return this.tabOrder.get(i);
        }

        /* access modifiers changed from: package-private */
        public void addTabOrder(int i) {
            this.tabOrder.add(Integer.valueOf(i));
        }
    }

    private static class InstHit {
        IntHashtable hits;

        public InstHit(int[] iArr) {
            if (iArr != null) {
                this.hits = new IntHashtable();
                for (int put : iArr) {
                    this.hits.put(put, 1);
                }
            }
        }

        public boolean isHit(int i) {
            if (this.hits == null) {
                return true;
            }
            return this.hits.containsKey(i);
        }
    }

    public boolean clearSignatureField(String str) {
        this.sigNames = null;
        getSignatureNames();
        if (!this.sigNames.containsKey(str)) {
            return false;
        }
        Item item = this.fields.get(str);
        item.markUsed(this, 6);
        int size = item.size();
        for (int i = 0; i < size; i++) {
            clearSigDic(item.getMerged(i));
            clearSigDic(item.getWidget(i));
            clearSigDic(item.getValue(i));
        }
        return true;
    }

    private static void clearSigDic(PdfDictionary pdfDictionary) {
        pdfDictionary.remove(PdfName.AP);
        pdfDictionary.remove(PdfName.AS);
        pdfDictionary.remove(PdfName.V);
        pdfDictionary.remove(PdfName.DV);
        pdfDictionary.remove(PdfName.SV);
        pdfDictionary.remove(PdfName.FF);
        pdfDictionary.put(PdfName.F, new PdfNumber(4));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<java.lang.String> getSignatureNames() {
        /*
            r9 = this;
            java.util.HashMap<java.lang.String, int[]> r0 = r9.sigNames
            if (r0 == 0) goto L_0x000c
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.ArrayList<java.lang.String> r1 = r9.orderedSignatureNames
            r0.<init>(r1)
            return r0
        L_0x000c:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r9.sigNames = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r9.orderedSignatureNames = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Map<java.lang.String, com.itextpdf.text.pdf.AcroFields$Item> r1 = r9.fields
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0029:
            boolean r2 = r1.hasNext()
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x009c
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r5 = r2.getValue()
            com.itextpdf.text.pdf.AcroFields$Item r5 = (com.itextpdf.text.pdf.AcroFields.Item) r5
            com.itextpdf.text.pdf.PdfDictionary r5 = r5.getMerged(r3)
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.SIG
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.FT
            com.itextpdf.text.pdf.PdfObject r7 = r5.get(r7)
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0050
            goto L_0x0029
        L_0x0050:
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.V
            com.itextpdf.text.pdf.PdfDictionary r5 = r5.getAsDict(r6)
            if (r5 != 0) goto L_0x0059
            goto L_0x0029
        L_0x0059:
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.CONTENTS
            com.itextpdf.text.pdf.PdfString r6 = r5.getAsString(r6)
            if (r6 != 0) goto L_0x0062
            goto L_0x0029
        L_0x0062:
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.BYTERANGE
            com.itextpdf.text.pdf.PdfArray r5 = r5.getAsArray(r6)
            if (r5 != 0) goto L_0x006b
            goto L_0x0029
        L_0x006b:
            int r6 = r5.size()
            r7 = 2
            if (r6 >= r7) goto L_0x0073
            goto L_0x0029
        L_0x0073:
            int r8 = r6 + -1
            com.itextpdf.text.pdf.PdfNumber r8 = r5.getAsNumber(r8)
            int r8 = r8.intValue()
            int r6 = r6 + -2
            com.itextpdf.text.pdf.PdfNumber r5 = r5.getAsNumber(r6)
            int r5 = r5.intValue()
            int r8 = r8 + r5
            java.lang.Object[] r5 = new java.lang.Object[r7]
            java.lang.Object r2 = r2.getKey()
            r5[r3] = r2
            int[] r2 = new int[r7]
            r2[r3] = r8
            r2[r4] = r3
            r5[r4] = r2
            r0.add(r5)
            goto L_0x0029
        L_0x009c:
            com.itextpdf.text.pdf.AcroFields$SorterComparator r1 = new com.itextpdf.text.pdf.AcroFields$SorterComparator
            r2 = 0
            r1.<init>()
            java.util.Collections.sort(r0, r1)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x00f9
            int r1 = r0.size()
            int r1 = r1 - r4
            java.lang.Object r1 = r0.get(r1)
            java.lang.Object[] r1 = (java.lang.Object[]) r1
            r1 = r1[r4]
            int[] r1 = (int[]) r1
            r1 = r1[r3]
            long r1 = (long) r1
            com.itextpdf.text.pdf.PdfReader r5 = r9.reader
            long r5 = r5.getFileLength()
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x00ce
            int r1 = r0.size()
            r9.totalRevisions = r1
            goto L_0x00d5
        L_0x00ce:
            int r1 = r0.size()
            int r1 = r1 + r4
            r9.totalRevisions = r1
        L_0x00d5:
            r1 = r3
        L_0x00d6:
            int r2 = r0.size()
            if (r1 >= r2) goto L_0x00f9
            java.lang.Object r2 = r0.get(r1)
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            r5 = r2[r3]
            java.lang.String r5 = (java.lang.String) r5
            r2 = r2[r4]
            int[] r2 = (int[]) r2
            int r1 = r1 + 1
            r2[r4] = r1
            java.util.HashMap<java.lang.String, int[]> r6 = r9.sigNames
            r6.put(r5, r2)
            java.util.ArrayList<java.lang.String> r2 = r9.orderedSignatureNames
            r2.add(r5)
            goto L_0x00d6
        L_0x00f9:
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.ArrayList<java.lang.String> r1 = r9.orderedSignatureNames
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.AcroFields.getSignatureNames():java.util.ArrayList");
    }

    public ArrayList<String> getBlankSignatureNames() {
        getSignatureNames();
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry next : this.fields.entrySet()) {
            if (PdfName.SIG.equals(((Item) next.getValue()).getMerged(0).getAsName(PdfName.FT)) && !this.sigNames.containsKey(next.getKey())) {
                arrayList.add(next.getKey());
            }
        }
        return arrayList;
    }

    public PdfDictionary getSignatureDictionary(String str) {
        getSignatureNames();
        String translatedFieldName = getTranslatedFieldName(str);
        if (!this.sigNames.containsKey(translatedFieldName)) {
            return null;
        }
        return this.fields.get(translatedFieldName).getMerged(0).getAsDict(PdfName.V);
    }

    public PdfIndirectReference getNormalAppearance(String str) {
        PdfDictionary asDict;
        PdfIndirectReference asIndirectObject;
        getSignatureNames();
        Item item = this.fields.get(getTranslatedFieldName(str));
        if (item == null || (asDict = item.getMerged(0).getAsDict(PdfName.AP)) == null || (asIndirectObject = asDict.getAsIndirectObject(PdfName.N)) == null) {
            return null;
        }
        return asIndirectObject;
    }

    public boolean signatureCoversWholeDocument(String str) {
        getSignatureNames();
        String translatedFieldName = getTranslatedFieldName(str);
        if (this.sigNames.containsKey(translatedFieldName) && ((long) this.sigNames.get(translatedFieldName)[0]) == this.reader.getFileLength()) {
            return true;
        }
        return false;
    }

    public PdfPKCS7 verifySignature(String str) {
        return verifySignature(str, (String) null);
    }

    public PdfPKCS7 verifySignature(String str, String str2) {
        PdfPKCS7 pdfPKCS7;
        PdfDictionary signatureDictionary = getSignatureDictionary(str);
        if (signatureDictionary == null) {
            return null;
        }
        try {
            PdfName asName = signatureDictionary.getAsName(PdfName.SUBFILTER);
            PdfString asString = signatureDictionary.getAsString(PdfName.CONTENTS);
            if (asName.equals(PdfName.ADBE_X509_RSA_SHA1)) {
                PdfString asString2 = signatureDictionary.getAsString(PdfName.CERT);
                if (asString2 == null) {
                    asString2 = signatureDictionary.getAsArray(PdfName.CERT).getAsString(0);
                }
                pdfPKCS7 = new PdfPKCS7(asString.getOriginalBytes(), asString2.getBytes(), str2);
            } else {
                pdfPKCS7 = new PdfPKCS7(asString.getOriginalBytes(), asName, str2);
            }
            updateByteRange(pdfPKCS7, signatureDictionary);
            PdfString asString3 = signatureDictionary.getAsString(PdfName.M);
            if (asString3 != null) {
                pdfPKCS7.setSignDate(PdfDate.decode(asString3.toString()));
            }
            PdfObject pdfObject = PdfReader.getPdfObject(signatureDictionary.get(PdfName.NAME));
            if (pdfObject != null) {
                if (pdfObject.isString()) {
                    pdfPKCS7.setSignName(((PdfString) pdfObject).toUnicodeString());
                } else if (pdfObject.isName()) {
                    pdfPKCS7.setSignName(PdfName.decodeName(pdfObject.toString()));
                }
            }
            PdfString asString4 = signatureDictionary.getAsString(PdfName.REASON);
            if (asString4 != null) {
                pdfPKCS7.setReason(asString4.toUnicodeString());
            }
            PdfString asString5 = signatureDictionary.getAsString(PdfName.LOCATION);
            if (asString5 != null) {
                pdfPKCS7.setLocation(asString5.toUnicodeString());
            }
            return pdfPKCS7;
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0052 A[SYNTHETIC, Splitter:B:25:0x0052] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateByteRange(com.itextpdf.text.pdf.security.PdfPKCS7 r5, com.itextpdf.text.pdf.PdfDictionary r6) {
        /*
            r4 = this;
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.BYTERANGE
            com.itextpdf.text.pdf.PdfArray r6 = r6.getAsArray(r0)
            com.itextpdf.text.pdf.PdfReader r0 = r4.reader
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r0.getSafeFile()
            r1 = 0
            com.itextpdf.text.io.RASInputStream r2 = new com.itextpdf.text.io.RASInputStream     // Catch:{ Exception -> 0x0049 }
            com.itextpdf.text.io.RandomAccessSourceFactory r3 = new com.itextpdf.text.io.RandomAccessSourceFactory     // Catch:{ Exception -> 0x0049 }
            r3.<init>()     // Catch:{ Exception -> 0x0049 }
            com.itextpdf.text.io.RandomAccessSource r0 = r0.createSourceView()     // Catch:{ Exception -> 0x0049 }
            long[] r6 = r6.asLongArray()     // Catch:{ Exception -> 0x0049 }
            com.itextpdf.text.io.RandomAccessSource r6 = r3.createRanged(r0, r6)     // Catch:{ Exception -> 0x0049 }
            r2.<init>(r6)     // Catch:{ Exception -> 0x0049 }
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r6]     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
        L_0x0027:
            int r0 = r6.length     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            r1 = 0
            int r0 = r2.read(r6, r1, r0)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            if (r0 <= 0) goto L_0x0033
            r5.update(r6, r1, r0)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            goto L_0x0027
        L_0x0033:
            if (r2 == 0) goto L_0x0040
            r2.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x0040
        L_0x0039:
            r5 = move-exception
            com.itextpdf.text.ExceptionConverter r6 = new com.itextpdf.text.ExceptionConverter
            r6.<init>(r5)
            throw r6
        L_0x0040:
            return
        L_0x0041:
            r5 = move-exception
            r1 = r2
            goto L_0x0050
        L_0x0044:
            r5 = move-exception
            r1 = r2
            goto L_0x004a
        L_0x0047:
            r5 = move-exception
            goto L_0x0050
        L_0x0049:
            r5 = move-exception
        L_0x004a:
            com.itextpdf.text.ExceptionConverter r6 = new com.itextpdf.text.ExceptionConverter     // Catch:{ all -> 0x0047 }
            r6.<init>(r5)     // Catch:{ all -> 0x0047 }
            throw r6     // Catch:{ all -> 0x0047 }
        L_0x0050:
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x005d
        L_0x0056:
            r5 = move-exception
            com.itextpdf.text.ExceptionConverter r6 = new com.itextpdf.text.ExceptionConverter
            r6.<init>(r5)
            throw r6
        L_0x005d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.AcroFields.updateByteRange(com.itextpdf.text.pdf.security.PdfPKCS7, com.itextpdf.text.pdf.PdfDictionary):void");
    }

    /* access modifiers changed from: private */
    public void markUsed(PdfObject pdfObject) {
        if (this.append) {
            ((PdfStamperImp) this.writer).markUsed(pdfObject);
        }
    }

    public int getTotalRevisions() {
        getSignatureNames();
        return this.totalRevisions;
    }

    public int getRevision(String str) {
        getSignatureNames();
        String translatedFieldName = getTranslatedFieldName(str);
        if (!this.sigNames.containsKey(translatedFieldName)) {
            return 0;
        }
        return this.sigNames.get(translatedFieldName)[1];
    }

    public InputStream extractRevision(String str) throws IOException {
        getSignatureNames();
        String translatedFieldName = getTranslatedFieldName(str);
        if (!this.sigNames.containsKey(translatedFieldName)) {
            return null;
        }
        return new RASInputStream(new WindowRandomAccessSource(this.reader.getSafeFile().createSourceView(), 0, (long) this.sigNames.get(translatedFieldName)[0]));
    }

    public Map<String, TextField> getFieldCache() {
        return this.fieldCache;
    }

    public void setFieldCache(Map<String, TextField> map) {
        this.fieldCache = map;
    }

    public void setExtraMargin(float f, float f2) {
        this.extraMarginLeft = f;
        this.extraMarginTop = f2;
    }

    public void addSubstitutionFont(BaseFont baseFont) {
        if (this.substitutionFonts == null) {
            this.substitutionFonts = new ArrayList<>();
        }
        this.substitutionFonts.add(baseFont);
    }

    static {
        stdFieldFontNames.put("CoBO", new String[]{"Courier-BoldOblique"});
        stdFieldFontNames.put("CoBo", new String[]{"Courier-Bold"});
        stdFieldFontNames.put("CoOb", new String[]{"Courier-Oblique"});
        stdFieldFontNames.put("Cour", new String[]{"Courier"});
        stdFieldFontNames.put("HeBO", new String[]{"Helvetica-BoldOblique"});
        stdFieldFontNames.put("HeBo", new String[]{"Helvetica-Bold"});
        stdFieldFontNames.put("HeOb", new String[]{"Helvetica-Oblique"});
        stdFieldFontNames.put("Helv", new String[]{"Helvetica"});
        stdFieldFontNames.put("Symb", new String[]{"Symbol"});
        stdFieldFontNames.put("TiBI", new String[]{"Times-BoldItalic"});
        stdFieldFontNames.put("TiBo", new String[]{"Times-Bold"});
        stdFieldFontNames.put("TiIt", new String[]{"Times-Italic"});
        stdFieldFontNames.put("TiRo", new String[]{"Times-Roman"});
        stdFieldFontNames.put("ZaDb", new String[]{"ZapfDingbats"});
        stdFieldFontNames.put("HySm", new String[]{"HYSMyeongJo-Medium", "UniKS-UCS2-H"});
        stdFieldFontNames.put("HyGo", new String[]{"HYGoThic-Medium", "UniKS-UCS2-H"});
        stdFieldFontNames.put("KaGo", new String[]{"HeiseiKakuGo-W5", "UniKS-UCS2-H"});
        stdFieldFontNames.put("KaMi", new String[]{"HeiseiMin-W3", "UniJIS-UCS2-H"});
        stdFieldFontNames.put("MHei", new String[]{"MHei-Medium", "UniCNS-UCS2-H"});
        stdFieldFontNames.put("MSun", new String[]{"MSung-Light", "UniCNS-UCS2-H"});
        stdFieldFontNames.put("STSo", new String[]{"STSong-Light", "UniGB-UCS2-H"});
    }

    private static class SorterComparator implements Comparator<Object[]> {
        private SorterComparator() {
        }

        public int compare(Object[] objArr, Object[] objArr2) {
            return objArr[1][0] - objArr2[1][0];
        }
    }

    public ArrayList<BaseFont> getSubstitutionFonts() {
        return this.substitutionFonts;
    }

    public void setSubstitutionFonts(ArrayList<BaseFont> arrayList) {
        this.substitutionFonts = arrayList;
    }

    public XfaForm getXfa() {
        return this.xfa;
    }

    public void removeXfa() {
        this.reader.getCatalog().getAsDict(PdfName.ACROFORM).remove(PdfName.XFA);
        try {
            this.xfa = new XfaForm(this.reader);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public PushbuttonField getNewPushbuttonFromField(String str) {
        return getNewPushbuttonFromField(str, 0);
    }

    public PushbuttonField getNewPushbuttonFromField(String str, int i) {
        int i2;
        try {
            if (getFieldType(str) != 1) {
                return null;
            }
            Item fieldItem = getFieldItem(str);
            if (i >= fieldItem.size()) {
                return null;
            }
            PushbuttonField pushbuttonField = new PushbuttonField(this.writer, getFieldPositions(str).get(i).position, (String) null);
            PdfDictionary merged = fieldItem.getMerged(i);
            decodeGenericDictionary(merged, pushbuttonField);
            PdfDictionary asDict = merged.getAsDict(PdfName.MK);
            if (asDict != null) {
                PdfString asString = asDict.getAsString(PdfName.CA);
                if (asString != null) {
                    pushbuttonField.setText(asString.toUnicodeString());
                }
                PdfNumber asNumber = asDict.getAsNumber(PdfName.TP);
                if (asNumber != null) {
                    pushbuttonField.setLayout(asNumber.intValue() + 1);
                }
                PdfDictionary asDict2 = asDict.getAsDict(PdfName.IF);
                if (asDict2 != null) {
                    PdfName asName = asDict2.getAsName(PdfName.SW);
                    if (asName != null) {
                        if (asName.equals(PdfName.B)) {
                            i2 = 3;
                        } else if (asName.equals(PdfName.S)) {
                            i2 = 4;
                        } else {
                            i2 = asName.equals(PdfName.N) ? 2 : 1;
                        }
                        pushbuttonField.setScaleIcon(i2);
                    }
                    PdfName asName2 = asDict2.getAsName(PdfName.S);
                    if (asName2 != null && asName2.equals(PdfName.A)) {
                        pushbuttonField.setProportionalIcon(false);
                    }
                    PdfArray asArray = asDict2.getAsArray(PdfName.A);
                    if (asArray != null && asArray.size() == 2) {
                        float floatValue = asArray.getAsNumber(0).floatValue();
                        float floatValue2 = asArray.getAsNumber(1).floatValue();
                        pushbuttonField.setIconHorizontalAdjustment(floatValue);
                        pushbuttonField.setIconVerticalAdjustment(floatValue2);
                    }
                    PdfBoolean asBoolean = asDict2.getAsBoolean(PdfName.FB);
                    if (asBoolean != null && asBoolean.booleanValue()) {
                        pushbuttonField.setIconFitToBounds(true);
                    }
                }
                PdfObject pdfObject = asDict.get(PdfName.I);
                if (pdfObject != null && pdfObject.isIndirect()) {
                    pushbuttonField.setIconReference((PRIndirectReference) pdfObject);
                }
            }
            return pushbuttonField;
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public boolean replacePushbuttonField(String str, PdfFormField pdfFormField) {
        return replacePushbuttonField(str, pdfFormField, 0);
    }

    public boolean replacePushbuttonField(String str, PdfFormField pdfFormField, int i) {
        if (getFieldType(str) != 1) {
            return false;
        }
        Item fieldItem = getFieldItem(str);
        if (i >= fieldItem.size()) {
            return false;
        }
        PdfDictionary merged = fieldItem.getMerged(i);
        PdfDictionary value = fieldItem.getValue(i);
        PdfDictionary widget = fieldItem.getWidget(i);
        for (int i2 = 0; i2 < buttonRemove.length; i2++) {
            merged.remove(buttonRemove[i2]);
            value.remove(buttonRemove[i2]);
            widget.remove(buttonRemove[i2]);
        }
        for (PdfName next : pdfFormField.getKeys()) {
            if (!next.equals(PdfName.T)) {
                if (next.equals(PdfName.FF)) {
                    value.put(next, pdfFormField.get(next));
                } else {
                    widget.put(next, pdfFormField.get(next));
                }
                merged.put(next, pdfFormField.get(next));
                markUsed(value);
                markUsed(widget);
            }
        }
        return true;
    }

    public boolean doesSignatureFieldExist(String str) {
        return getBlankSignatureNames().contains(str) || getSignatureNames().contains(str);
    }
}
