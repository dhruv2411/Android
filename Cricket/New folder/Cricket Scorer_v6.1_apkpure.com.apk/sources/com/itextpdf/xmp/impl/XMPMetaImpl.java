package com.itextpdf.xmp.impl;

import com.itextpdf.xmp.XMPConst;
import com.itextpdf.xmp.XMPDateTime;
import com.itextpdf.xmp.XMPException;
import com.itextpdf.xmp.XMPIterator;
import com.itextpdf.xmp.XMPMeta;
import com.itextpdf.xmp.XMPPathFactory;
import com.itextpdf.xmp.XMPUtils;
import com.itextpdf.xmp.impl.xpath.XMPPath;
import com.itextpdf.xmp.impl.xpath.XMPPathParser;
import com.itextpdf.xmp.options.IteratorOptions;
import com.itextpdf.xmp.options.ParseOptions;
import com.itextpdf.xmp.options.PropertyOptions;
import com.itextpdf.xmp.properties.XMPProperty;
import java.util.Calendar;

public class XMPMetaImpl implements XMPMeta, XMPConst {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int VALUE_BASE64 = 7;
    private static final int VALUE_BOOLEAN = 1;
    private static final int VALUE_CALENDAR = 6;
    private static final int VALUE_DATE = 5;
    private static final int VALUE_DOUBLE = 4;
    private static final int VALUE_INTEGER = 2;
    private static final int VALUE_LONG = 3;
    private static final int VALUE_STRING = 0;
    private String packetHeader;
    private XMPNode tree;

    public XMPMetaImpl() {
        this.packetHeader = null;
        this.tree = new XMPNode((String) null, (String) null, (PropertyOptions) null);
    }

    public XMPMetaImpl(XMPNode xMPNode) {
        this.packetHeader = null;
        this.tree = xMPNode;
    }

    public void appendArrayItem(String str, String str2, PropertyOptions propertyOptions, String str3, PropertyOptions propertyOptions2) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        if (propertyOptions == null) {
            propertyOptions = new PropertyOptions();
        }
        if (!propertyOptions.isOnlyArrayOptions()) {
            throw new XMPException("Only array form flags allowed for arrayOptions", 103);
        }
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, (Object) null);
        XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, expandXPath, false, (PropertyOptions) null);
        if (findNode != null) {
            if (!findNode.getOptions().isArray()) {
                throw new XMPException("The named property is not an array", 102);
            }
        } else if (verifySetOptions.isArray()) {
            findNode = XMPNodeUtils.findNode(this.tree, expandXPath, true, verifySetOptions);
            if (findNode == null) {
                throw new XMPException("Failure creating array node", 102);
            }
        } else {
            throw new XMPException("Explicit arrayOptions required to create new array", 103);
        }
        doSetArrayItem(findNode, -1, str3, propertyOptions2, true);
    }

    public void appendArrayItem(String str, String str2, String str3) throws XMPException {
        appendArrayItem(str, str2, (PropertyOptions) null, str3, (PropertyOptions) null);
    }

    public int countArrayItems(String str, String str2) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return 0;
        }
        if (findNode.getOptions().isArray()) {
            return findNode.getChildrenLength();
        }
        throw new XMPException("The named property is not an array", 102);
    }

    public void deleteArrayItem(String str, String str2, int i) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertArrayName(str2);
            deleteProperty(str, XMPPathFactory.composeArrayItemPath(str2, i));
        } catch (XMPException unused) {
        }
    }

    public void deleteProperty(String str, String str2) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
            if (findNode != null) {
                XMPNodeUtils.deleteNode(findNode);
            }
        } catch (XMPException unused) {
        }
    }

    public void deleteQualifier(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            deleteProperty(str, str2 + XMPPathFactory.composeQualifierPath(str3, str4));
        } catch (XMPException unused) {
        }
    }

    public void deleteStructField(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertStructName(str2);
            deleteProperty(str, str2 + XMPPathFactory.composeStructFieldPath(str3, str4));
        } catch (XMPException unused) {
        }
    }

    public boolean doesPropertyExist(String str, String str2) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            if (XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null) != null) {
                return true;
            }
            return false;
        } catch (XMPException unused) {
            return false;
        }
    }

    public boolean doesArrayItemExist(String str, String str2, int i) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertArrayName(str2);
            return doesPropertyExist(str, XMPPathFactory.composeArrayItemPath(str2, i));
        } catch (XMPException unused) {
            return false;
        }
    }

    public boolean doesStructFieldExist(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertStructName(str2);
            String composeStructFieldPath = XMPPathFactory.composeStructFieldPath(str3, str4);
            return doesPropertyExist(str, str2 + composeStructFieldPath);
        } catch (XMPException unused) {
            return false;
        }
    }

    public boolean doesQualifierExist(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            String composeQualifierPath = XMPPathFactory.composeQualifierPath(str3, str4);
            return doesPropertyExist(str, str2 + composeQualifierPath);
        } catch (XMPException unused) {
            return false;
        }
    }

    public XMPProperty getArrayItem(String str, String str2, int i) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        return getProperty(str, XMPPathFactory.composeArrayItemPath(str2, i));
    }

    public XMPProperty getLocalizedText(String str, String str2, String str3, String str4) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        ParameterAsserts.assertSpecificLang(str4);
        String normalizeLangValue = str3 != null ? Utils.normalizeLangValue(str3) : null;
        String normalizeLangValue2 = Utils.normalizeLangValue(str4);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return null;
        }
        Object[] chooseLocalizedText = XMPNodeUtils.chooseLocalizedText(findNode, normalizeLangValue, normalizeLangValue2);
        int intValue = ((Integer) chooseLocalizedText[0]).intValue();
        final XMPNode xMPNode = (XMPNode) chooseLocalizedText[1];
        if (intValue != 0) {
            return new XMPProperty() {
                public String getValue() {
                    return xMPNode.getValue();
                }

                public PropertyOptions getOptions() {
                    return xMPNode.getOptions();
                }

                public String getLanguage() {
                    return xMPNode.getQualifier(1).getValue();
                }

                public String toString() {
                    return xMPNode.getValue().toString();
                }
            };
        }
        return null;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009f, code lost:
        throw new com.itextpdf.xmp.XMPException("Language qualifier must be first", 102);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d6, code lost:
        if (r3 != false) goto L_0x0162;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ed, code lost:
        if (r3 != false) goto L_0x0162;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setLocalizedText(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, com.itextpdf.xmp.options.PropertyOptions r12) throws com.itextpdf.xmp.XMPException {
        /*
            r6 = this;
            com.itextpdf.xmp.impl.ParameterAsserts.assertSchemaNS(r7)
            com.itextpdf.xmp.impl.ParameterAsserts.assertArrayName(r8)
            com.itextpdf.xmp.impl.ParameterAsserts.assertSpecificLang(r10)
            r12 = 0
            if (r9 == 0) goto L_0x0011
            java.lang.String r9 = com.itextpdf.xmp.impl.Utils.normalizeLangValue(r9)
            goto L_0x0012
        L_0x0011:
            r9 = r12
        L_0x0012:
            java.lang.String r10 = com.itextpdf.xmp.impl.Utils.normalizeLangValue(r10)
            com.itextpdf.xmp.impl.xpath.XMPPath r7 = com.itextpdf.xmp.impl.xpath.XMPPathParser.expandXPath(r7, r8)
            com.itextpdf.xmp.impl.XMPNode r8 = r6.tree
            com.itextpdf.xmp.options.PropertyOptions r0 = new com.itextpdf.xmp.options.PropertyOptions
            r1 = 7680(0x1e00, float:1.0762E-41)
            r0.<init>(r1)
            r1 = 1
            com.itextpdf.xmp.impl.XMPNode r7 = com.itextpdf.xmp.impl.XMPNodeUtils.findNode(r8, r7, r1, r0)
            r8 = 102(0x66, float:1.43E-43)
            if (r7 != 0) goto L_0x0034
            com.itextpdf.xmp.XMPException r7 = new com.itextpdf.xmp.XMPException
            java.lang.String r9 = "Failed to find or create array node"
            r7.<init>(r9, r8)
            throw r7
        L_0x0034:
            com.itextpdf.xmp.options.PropertyOptions r0 = r7.getOptions()
            boolean r0 = r0.isArrayAltText()
            if (r0 != 0) goto L_0x005e
            boolean r0 = r7.hasChildren()
            if (r0 != 0) goto L_0x0056
            com.itextpdf.xmp.options.PropertyOptions r0 = r7.getOptions()
            boolean r0 = r0.isArrayAlternate()
            if (r0 == 0) goto L_0x0056
            com.itextpdf.xmp.options.PropertyOptions r0 = r7.getOptions()
            r0.setArrayAltText(r1)
            goto L_0x005e
        L_0x0056:
            com.itextpdf.xmp.XMPException r7 = new com.itextpdf.xmp.XMPException
            java.lang.String r9 = "Specified property is no alt-text array"
            r7.<init>(r9, r8)
            throw r7
        L_0x005e:
            java.util.Iterator r0 = r7.iterateChildren()
        L_0x0062:
            boolean r2 = r0.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x00a0
            java.lang.Object r2 = r0.next()
            com.itextpdf.xmp.impl.XMPNode r2 = (com.itextpdf.xmp.impl.XMPNode) r2
            boolean r4 = r2.hasQualifier()
            if (r4 == 0) goto L_0x0098
            java.lang.String r4 = "xml:lang"
            com.itextpdf.xmp.impl.XMPNode r5 = r2.getQualifier(r1)
            java.lang.String r5 = r5.getName()
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0086
            goto L_0x0098
        L_0x0086:
            java.lang.String r4 = "x-default"
            com.itextpdf.xmp.impl.XMPNode r5 = r2.getQualifier(r1)
            java.lang.String r5 = r5.getValue()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0062
            r8 = r1
            goto L_0x00a2
        L_0x0098:
            com.itextpdf.xmp.XMPException r7 = new com.itextpdf.xmp.XMPException
            java.lang.String r9 = "Language qualifier must be first"
            r7.<init>(r9, r8)
            throw r7
        L_0x00a0:
            r2 = r12
            r8 = r3
        L_0x00a2:
            if (r2 == 0) goto L_0x00b0
            int r0 = r7.getChildrenLength()
            if (r0 <= r1) goto L_0x00b0
            r7.removeChild((com.itextpdf.xmp.impl.XMPNode) r2)
            r7.addChild(r1, r2)
        L_0x00b0:
            java.lang.Object[] r9 = com.itextpdf.xmp.impl.XMPNodeUtils.chooseLocalizedText(r7, r9, r10)
            r0 = r9[r3]
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r9 = r9[r1]
            com.itextpdf.xmp.impl.XMPNode r9 = (com.itextpdf.xmp.impl.XMPNode) r9
            java.lang.String r3 = "x-default"
            boolean r3 = r3.equals(r10)
            switch(r0) {
                case 0: goto L_0x0158;
                case 1: goto L_0x010c;
                case 2: goto L_0x00f1;
                case 3: goto L_0x00ea;
                case 4: goto L_0x00da;
                case 5: goto L_0x00d3;
                default: goto L_0x00c9;
            }
        L_0x00c9:
            com.itextpdf.xmp.XMPException r7 = new com.itextpdf.xmp.XMPException
            java.lang.String r8 = "Unexpected result from ChooseLocalizedText"
            r9 = 9
            r7.<init>(r8, r9)
            throw r7
        L_0x00d3:
            com.itextpdf.xmp.impl.XMPNodeUtils.appendLangItem(r7, r10, r11)
            if (r3 == 0) goto L_0x0163
            goto L_0x0162
        L_0x00da:
            if (r2 == 0) goto L_0x00e5
            int r9 = r7.getChildrenLength()
            if (r9 != r1) goto L_0x00e5
            r2.setValue(r11)
        L_0x00e5:
            com.itextpdf.xmp.impl.XMPNodeUtils.appendLangItem(r7, r10, r11)
            goto L_0x0163
        L_0x00ea:
            com.itextpdf.xmp.impl.XMPNodeUtils.appendLangItem(r7, r10, r11)
            if (r3 == 0) goto L_0x0163
            goto L_0x0162
        L_0x00f1:
            if (r8 == 0) goto L_0x0108
            if (r2 == r9) goto L_0x0108
            if (r2 == 0) goto L_0x0108
            java.lang.String r10 = r2.getValue()
            java.lang.String r12 = r9.getValue()
            boolean r10 = r10.equals(r12)
            if (r10 == 0) goto L_0x0108
            r2.setValue(r11)
        L_0x0108:
            r9.setValue(r11)
            goto L_0x0163
        L_0x010c:
            if (r3 != 0) goto L_0x0129
            if (r8 == 0) goto L_0x0125
            if (r2 == r9) goto L_0x0125
            if (r2 == 0) goto L_0x0125
            java.lang.String r10 = r2.getValue()
            java.lang.String r12 = r9.getValue()
            boolean r10 = r10.equals(r12)
            if (r10 == 0) goto L_0x0125
            r2.setValue(r11)
        L_0x0125:
            r9.setValue(r11)
            goto L_0x0163
        L_0x0129:
            java.util.Iterator r9 = r7.iterateChildren()
        L_0x012d:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0152
            java.lang.Object r10 = r9.next()
            com.itextpdf.xmp.impl.XMPNode r10 = (com.itextpdf.xmp.impl.XMPNode) r10
            if (r10 == r2) goto L_0x012d
            java.lang.String r0 = r10.getValue()
            if (r2 == 0) goto L_0x0146
            java.lang.String r3 = r2.getValue()
            goto L_0x0147
        L_0x0146:
            r3 = r12
        L_0x0147:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x014e
            goto L_0x012d
        L_0x014e:
            r10.setValue(r11)
            goto L_0x012d
        L_0x0152:
            if (r2 == 0) goto L_0x0163
            r2.setValue(r11)
            goto L_0x0163
        L_0x0158:
            java.lang.String r8 = "x-default"
            com.itextpdf.xmp.impl.XMPNodeUtils.appendLangItem(r7, r8, r11)
            if (r3 != 0) goto L_0x0162
            com.itextpdf.xmp.impl.XMPNodeUtils.appendLangItem(r7, r10, r11)
        L_0x0162:
            r8 = r1
        L_0x0163:
            if (r8 != 0) goto L_0x0170
            int r8 = r7.getChildrenLength()
            if (r8 != r1) goto L_0x0170
            java.lang.String r8 = "x-default"
            com.itextpdf.xmp.impl.XMPNodeUtils.appendLangItem(r7, r8, r11)
        L_0x0170:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.xmp.impl.XMPMetaImpl.setLocalizedText(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.itextpdf.xmp.options.PropertyOptions):void");
    }

    public void setLocalizedText(String str, String str2, String str3, String str4, String str5) throws XMPException {
        setLocalizedText(str, str2, str3, str4, str5, (PropertyOptions) null);
    }

    public XMPProperty getProperty(String str, String str2) throws XMPException {
        return getProperty(str, str2, 0);
    }

    /* access modifiers changed from: protected */
    public XMPProperty getProperty(String str, String str2, int i) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        final XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return null;
        }
        if (i == 0 || !findNode.getOptions().isCompositeProperty()) {
            final Object evaluateNodeValue = evaluateNodeValue(i, findNode);
            return new XMPProperty() {
                public String getLanguage() {
                    return null;
                }

                public String getValue() {
                    if (evaluateNodeValue != null) {
                        return evaluateNodeValue.toString();
                    }
                    return null;
                }

                public PropertyOptions getOptions() {
                    return findNode.getOptions();
                }

                public String toString() {
                    return evaluateNodeValue.toString();
                }
            };
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    /* access modifiers changed from: protected */
    public Object getPropertyObject(String str, String str2, int i) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return null;
        }
        if (i == 0 || !findNode.getOptions().isCompositeProperty()) {
            return evaluateNodeValue(i, findNode);
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    public Boolean getPropertyBoolean(String str, String str2) throws XMPException {
        return (Boolean) getPropertyObject(str, str2, 1);
    }

    public void setPropertyBoolean(String str, String str2, boolean z, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, z ? XMPConst.TRUESTR : XMPConst.FALSESTR, propertyOptions);
    }

    public void setPropertyBoolean(String str, String str2, boolean z) throws XMPException {
        setProperty(str, str2, z ? XMPConst.TRUESTR : XMPConst.FALSESTR, (PropertyOptions) null);
    }

    public Integer getPropertyInteger(String str, String str2) throws XMPException {
        return (Integer) getPropertyObject(str, str2, 2);
    }

    public void setPropertyInteger(String str, String str2, int i, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, new Integer(i), propertyOptions);
    }

    public void setPropertyInteger(String str, String str2, int i) throws XMPException {
        setProperty(str, str2, new Integer(i), (PropertyOptions) null);
    }

    public Long getPropertyLong(String str, String str2) throws XMPException {
        return (Long) getPropertyObject(str, str2, 3);
    }

    public void setPropertyLong(String str, String str2, long j, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, new Long(j), propertyOptions);
    }

    public void setPropertyLong(String str, String str2, long j) throws XMPException {
        setProperty(str, str2, new Long(j), (PropertyOptions) null);
    }

    public Double getPropertyDouble(String str, String str2) throws XMPException {
        return (Double) getPropertyObject(str, str2, 4);
    }

    public void setPropertyDouble(String str, String str2, double d, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, new Double(d), propertyOptions);
    }

    public void setPropertyDouble(String str, String str2, double d) throws XMPException {
        setProperty(str, str2, new Double(d), (PropertyOptions) null);
    }

    public XMPDateTime getPropertyDate(String str, String str2) throws XMPException {
        return (XMPDateTime) getPropertyObject(str, str2, 5);
    }

    public void setPropertyDate(String str, String str2, XMPDateTime xMPDateTime, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, xMPDateTime, propertyOptions);
    }

    public void setPropertyDate(String str, String str2, XMPDateTime xMPDateTime) throws XMPException {
        setProperty(str, str2, xMPDateTime, (PropertyOptions) null);
    }

    public Calendar getPropertyCalendar(String str, String str2) throws XMPException {
        return (Calendar) getPropertyObject(str, str2, 6);
    }

    public void setPropertyCalendar(String str, String str2, Calendar calendar, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, calendar, propertyOptions);
    }

    public void setPropertyCalendar(String str, String str2, Calendar calendar) throws XMPException {
        setProperty(str, str2, calendar, (PropertyOptions) null);
    }

    public byte[] getPropertyBase64(String str, String str2) throws XMPException {
        return (byte[]) getPropertyObject(str, str2, 7);
    }

    public String getPropertyString(String str, String str2) throws XMPException {
        return (String) getPropertyObject(str, str2, 0);
    }

    public void setPropertyBase64(String str, String str2, byte[] bArr, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, bArr, propertyOptions);
    }

    public void setPropertyBase64(String str, String str2, byte[] bArr) throws XMPException {
        setProperty(str, str2, bArr, (PropertyOptions) null);
    }

    public XMPProperty getQualifier(String str, String str2, String str3, String str4) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        return getProperty(str, str2 + XMPPathFactory.composeQualifierPath(str3, str4));
    }

    public XMPProperty getStructField(String str, String str2, String str3, String str4) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertStructName(str2);
        return getProperty(str, str2 + XMPPathFactory.composeStructFieldPath(str3, str4));
    }

    public XMPIterator iterator() throws XMPException {
        return iterator((String) null, (String) null, (IteratorOptions) null);
    }

    public XMPIterator iterator(IteratorOptions iteratorOptions) throws XMPException {
        return iterator((String) null, (String) null, iteratorOptions);
    }

    public XMPIterator iterator(String str, String str2, IteratorOptions iteratorOptions) throws XMPException {
        return new XMPIteratorImpl(this, str, str2, iteratorOptions);
    }

    public void setArrayItem(String str, String str2, int i, String str3, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode != null) {
            doSetArrayItem(findNode, i, str3, propertyOptions, false);
            return;
        }
        throw new XMPException("Specified array does not exist", 102);
    }

    public void setArrayItem(String str, String str2, int i, String str3) throws XMPException {
        setArrayItem(str, str2, i, str3, (PropertyOptions) null);
    }

    public void insertArrayItem(String str, String str2, int i, String str3, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode != null) {
            doSetArrayItem(findNode, i, str3, propertyOptions, true);
            return;
        }
        throw new XMPException("Specified array does not exist", 102);
    }

    public void insertArrayItem(String str, String str2, int i, String str3) throws XMPException {
        insertArrayItem(str, str2, i, str3, (PropertyOptions) null);
    }

    public void setProperty(String str, String str2, Object obj, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, obj);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), true, verifySetOptions);
        if (findNode != null) {
            setNode(findNode, obj, verifySetOptions, false);
            return;
        }
        throw new XMPException("Specified property does not exist", 102);
    }

    public void setProperty(String str, String str2, Object obj) throws XMPException {
        setProperty(str, str2, obj, (PropertyOptions) null);
    }

    public void setQualifier(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        if (!doesPropertyExist(str, str2)) {
            throw new XMPException("Specified property does not exist!", 102);
        }
        setProperty(str, str2 + XMPPathFactory.composeQualifierPath(str3, str4), str5, propertyOptions);
    }

    public void setQualifier(String str, String str2, String str3, String str4, String str5) throws XMPException {
        setQualifier(str, str2, str3, str4, str5, (PropertyOptions) null);
    }

    public void setStructField(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertStructName(str2);
        setProperty(str, str2 + XMPPathFactory.composeStructFieldPath(str3, str4), str5, propertyOptions);
    }

    public void setStructField(String str, String str2, String str3, String str4, String str5) throws XMPException {
        setStructField(str, str2, str3, str4, str5, (PropertyOptions) null);
    }

    public String getObjectName() {
        return this.tree.getName() != null ? this.tree.getName() : "";
    }

    public void setObjectName(String str) {
        this.tree.setName(str);
    }

    public String getPacketHeader() {
        return this.packetHeader;
    }

    public void setPacketHeader(String str) {
        this.packetHeader = str;
    }

    public Object clone() {
        return new XMPMetaImpl((XMPNode) this.tree.clone());
    }

    public String dumpObject() {
        return getRoot().dumpNode(true);
    }

    public void sort() {
        this.tree.sort();
    }

    public void normalize(ParseOptions parseOptions) throws XMPException {
        if (parseOptions == null) {
            parseOptions = new ParseOptions();
        }
        XMPNormalizer.process(this, parseOptions);
    }

    public XMPNode getRoot() {
        return this.tree;
    }

    private void doSetArrayItem(XMPNode xMPNode, int i, String str, PropertyOptions propertyOptions, boolean z) throws XMPException {
        XMPNode xMPNode2 = new XMPNode(XMPConst.ARRAY_ITEM_NAME, (PropertyOptions) null);
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, str);
        int childrenLength = z ? xMPNode.getChildrenLength() + 1 : xMPNode.getChildrenLength();
        if (i == -1) {
            i = childrenLength;
        }
        if (1 > i || i > childrenLength) {
            throw new XMPException("Array index out of bounds", 104);
        }
        if (!z) {
            xMPNode.removeChild(i);
        }
        xMPNode.addChild(i, xMPNode2);
        setNode(xMPNode2, str, verifySetOptions, false);
    }

    /* access modifiers changed from: package-private */
    public void setNode(XMPNode xMPNode, Object obj, PropertyOptions propertyOptions, boolean z) throws XMPException {
        if (z) {
            xMPNode.clear();
        }
        xMPNode.getOptions().mergeWith(propertyOptions);
        if (!xMPNode.getOptions().isCompositeProperty()) {
            XMPNodeUtils.setNodeValue(xMPNode, obj);
        } else if (obj == null || obj.toString().length() <= 0) {
            xMPNode.removeChildren();
        } else {
            throw new XMPException("Composite nodes can't have values", 102);
        }
    }

    private Object evaluateNodeValue(int i, XMPNode xMPNode) throws XMPException {
        String value = xMPNode.getValue();
        switch (i) {
            case 1:
                return new Boolean(XMPUtils.convertToBoolean(value));
            case 2:
                return new Integer(XMPUtils.convertToInteger(value));
            case 3:
                return new Long(XMPUtils.convertToLong(value));
            case 4:
                return new Double(XMPUtils.convertToDouble(value));
            case 5:
                return XMPUtils.convertToDate(value);
            case 6:
                return XMPUtils.convertToDate(value).getCalendar();
            case 7:
                return XMPUtils.decodeBase64(value);
            default:
                if (value == null && !xMPNode.getOptions().isCompositeProperty()) {
                    value = "";
                }
                return value;
        }
    }
}
