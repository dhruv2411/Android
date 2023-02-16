package com.itextpdf.text.pdf;

import com.itextpdf.text.log.Counter;
import com.itextpdf.text.log.CounterFactory;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

public class FdfReader extends PdfReader {
    protected static Counter COUNTER = CounterFactory.getCounter(FdfReader.class);
    PdfName encoding;
    HashMap<String, PdfDictionary> fields;
    String fileSpec;

    public FdfReader(String str) throws IOException {
        super(str);
    }

    public FdfReader(byte[] bArr) throws IOException {
        super(bArr);
    }

    public FdfReader(URL url) throws IOException {
        super(url);
    }

    public FdfReader(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    /* access modifiers changed from: protected */
    public Counter getCounter() {
        return COUNTER;
    }

    /* access modifiers changed from: protected */
    public void readPdf() throws IOException {
        this.fields = new HashMap<>();
        this.tokens.checkFdfHeader();
        rebuildXref();
        readDocObj();
        readFields();
    }

    /* access modifiers changed from: protected */
    public void kidNode(PdfDictionary pdfDictionary, String str) {
        String str2;
        PdfArray asArray = pdfDictionary.getAsArray(PdfName.KIDS);
        if (asArray == null || asArray.isEmpty()) {
            if (str.length() > 0) {
                str = str.substring(1);
            }
            this.fields.put(str, pdfDictionary);
            return;
        }
        pdfDictionary.remove(PdfName.KIDS);
        for (int i = 0; i < asArray.size(); i++) {
            PdfDictionary pdfDictionary2 = new PdfDictionary();
            pdfDictionary2.merge(pdfDictionary);
            PdfDictionary asDict = asArray.getAsDict(i);
            PdfString asString = asDict.getAsString(PdfName.T);
            if (asString != null) {
                str2 = str + "." + asString.toUnicodeString();
            } else {
                str2 = str;
            }
            pdfDictionary2.merge(asDict);
            pdfDictionary2.remove(PdfName.T);
            kidNode(pdfDictionary2, str2);
        }
    }

    /* access modifiers changed from: protected */
    public void readFields() {
        this.catalog = this.trailer.getAsDict(PdfName.ROOT);
        PdfDictionary asDict = this.catalog.getAsDict(PdfName.FDF);
        if (asDict != null) {
            PdfString asString = asDict.getAsString(PdfName.F);
            if (asString != null) {
                this.fileSpec = asString.toUnicodeString();
            }
            PdfArray asArray = asDict.getAsArray(PdfName.FIELDS);
            if (asArray != null) {
                this.encoding = asDict.getAsName(PdfName.ENCODING);
                PdfDictionary pdfDictionary = new PdfDictionary();
                pdfDictionary.put(PdfName.KIDS, asArray);
                kidNode(pdfDictionary, "");
            }
        }
    }

    public HashMap<String, PdfDictionary> getFields() {
        return this.fields;
    }

    public PdfDictionary getField(String str) {
        return this.fields.get(str);
    }

    public byte[] getAttachedFile(String str) throws IOException {
        PdfDictionary pdfDictionary = this.fields.get(str);
        if (pdfDictionary != null) {
            return getStreamBytes((PRStream) getPdfObject(((PRIndirectReference) ((PdfDictionary) getPdfObject(((PRIndirectReference) pdfDictionary.get(PdfName.V)).getNumber())).getAsDict(PdfName.EF).get(PdfName.F)).getNumber()));
        }
        return new byte[0];
    }

    public String getFieldValue(String str) {
        PdfObject pdfObject;
        PdfDictionary pdfDictionary = this.fields.get(str);
        if (pdfDictionary == null || (pdfObject = getPdfObject(pdfDictionary.get(PdfName.V))) == null) {
            return null;
        }
        if (pdfObject.isName()) {
            return PdfName.decodeName(((PdfName) pdfObject).toString());
        }
        if (!pdfObject.isString()) {
            return null;
        }
        PdfString pdfString = (PdfString) pdfObject;
        if (this.encoding == null || pdfString.getEncoding() != null) {
            return pdfString.toUnicodeString();
        }
        byte[] bytes = pdfString.getBytes();
        if (bytes.length >= 2 && bytes[0] == -2 && bytes[1] == -1) {
            return pdfString.toUnicodeString();
        }
        try {
            if (this.encoding.equals(PdfName.SHIFT_JIS)) {
                return new String(bytes, "SJIS");
            }
            if (this.encoding.equals(PdfName.UHC)) {
                return new String(bytes, "MS949");
            }
            if (this.encoding.equals(PdfName.GBK)) {
                return new String(bytes, "GBK");
            }
            if (this.encoding.equals(PdfName.BIGFIVE)) {
                return new String(bytes, "Big5");
            }
            if (this.encoding.equals(PdfName.UTF_8)) {
                return new String(bytes, UrlUtils.UTF8);
            }
            return pdfString.toUnicodeString();
        } catch (Exception unused) {
        }
    }

    public String getFileSpec() {
        return this.fileSpec;
    }
}
