package com.itextpdf.text.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.exceptions.BadPasswordException;
import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.exceptions.UnsupportedPdfException;
import com.itextpdf.text.io.RandomAccessSource;
import com.itextpdf.text.io.RandomAccessSourceFactory;
import com.itextpdf.text.io.WindowRandomAccessSource;
import com.itextpdf.text.log.Counter;
import com.itextpdf.text.log.CounterFactory;
import com.itextpdf.text.log.Level;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.FilterHandlers;
import com.itextpdf.text.pdf.IntHashtable;
import com.itextpdf.text.pdf.PRTokeniser;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.interfaces.PdfViewerPreferences;
import com.itextpdf.text.pdf.internal.PdfViewerPreferencesImp;
import com.itextpdf.text.pdf.security.ExternalDecryptionProcess;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Key;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.InflaterInputStream;

public class PdfReader implements PdfViewerPreferences {
    protected static Counter COUNTER = CounterFactory.getCounter(PdfReader.class);
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) PdfReader.class);
    public static boolean debugmode = false;
    static final byte[] endobj = PdfEncodings.convertToBytes("endobj", (String) null);
    static final byte[] endstream = PdfEncodings.convertToBytes("endstream", (String) null);
    static final PdfName[] pageInhCandidates = {PdfName.MEDIABOX, PdfName.ROTATE, PdfName.RESOURCES, PdfName.CROPBOX};
    public static boolean unethicalreading = false;
    protected PRAcroForm acroForm;
    protected boolean acroFormParsed;
    private boolean appendable;
    protected PdfDictionary catalog;
    protected Certificate certificate;
    protected Key certificateKey;
    protected String certificateKeyProvider;
    protected boolean consolidateNamedDestinations;
    private PRIndirectReference cryptoRef;
    protected PdfEncryption decrypt;
    protected boolean encrypted;
    private boolean encryptionError;
    protected long eofPos;
    protected ExternalDecryptionProcess externalDecryptionProcess;
    private long fileLength;
    protected int freeXref;
    private boolean hybridXref;
    protected long lastXref;
    /* access modifiers changed from: private */
    public int lastXrefPartial;
    protected boolean newXrefType;
    private int objGen;
    private int objNum;
    protected HashMap<Integer, IntHashtable> objStmMark;
    protected LongHashtable objStmToOffset;
    private boolean ownerPasswordUsed;
    protected long pValue;
    protected PageRefs pageRefs;
    /* access modifiers changed from: private */
    public boolean partial;
    protected byte[] password;
    protected char pdfVersion;
    protected int rValue;
    private int readDepth;
    protected boolean rebuilt;
    protected boolean remoteToLocalNamedDestinations;
    PdfDictionary rootPages;
    protected boolean sharedStreams;
    protected ArrayList<PdfString> strings;
    protected boolean tampered;
    protected PRTokeniser tokens;
    protected PdfDictionary trailer;
    private final PdfViewerPreferencesImp viewerPreferences;
    protected long[] xref;
    protected ArrayList<PdfObject> xrefObj;

    /* access modifiers changed from: protected */
    public Counter getCounter() {
        return COUNTER;
    }

    private PdfReader(RandomAccessSource randomAccessSource, boolean z, byte[] bArr, Certificate certificate2, Key key, String str, ExternalDecryptionProcess externalDecryptionProcess2, boolean z2) throws IOException {
        this.acroForm = null;
        this.acroFormParsed = false;
        this.encrypted = false;
        this.rebuilt = false;
        this.tampered = false;
        this.password = null;
        this.certificateKey = null;
        this.certificate = null;
        this.certificateKeyProvider = null;
        this.externalDecryptionProcess = null;
        this.strings = new ArrayList<>();
        this.sharedStreams = true;
        this.consolidateNamedDestinations = false;
        this.remoteToLocalNamedDestinations = false;
        this.lastXrefPartial = -1;
        this.viewerPreferences = new PdfViewerPreferencesImp();
        this.readDepth = 0;
        this.certificate = certificate2;
        this.certificateKey = key;
        this.certificateKeyProvider = str;
        this.externalDecryptionProcess = externalDecryptionProcess2;
        this.password = bArr;
        this.partial = z;
        try {
            this.tokens = getOffsetTokeniser(randomAccessSource);
            if (z) {
                readPdfPartial();
            } else {
                readPdf();
            }
            getCounter().read(this.fileLength);
        } catch (IOException e) {
            if (z2) {
                randomAccessSource.close();
            }
            throw e;
        }
    }

    public PdfReader(String str) throws IOException {
        this(str, (byte[]) null);
    }

    public PdfReader(String str, byte[] bArr) throws IOException {
        this(str, bArr, false);
    }

    public PdfReader(String str, byte[] bArr, boolean z) throws IOException {
        this(new RandomAccessSourceFactory().setForceRead(false).setUsePlainRandomAccess(Document.plainRandomAccess).createBestSource(str), z, bArr, (Certificate) null, (Key) null, (String) null, (ExternalDecryptionProcess) null, true);
    }

    public PdfReader(byte[] bArr) throws IOException {
        this(bArr, (byte[]) null);
    }

    public PdfReader(byte[] bArr, byte[] bArr2) throws IOException {
        this(new RandomAccessSourceFactory().createSource(bArr), false, bArr2, (Certificate) null, (Key) null, (String) null, (ExternalDecryptionProcess) null, true);
    }

    public PdfReader(String str, Certificate certificate2, Key key, String str2) throws IOException {
        this(new RandomAccessSourceFactory().setForceRead(false).setUsePlainRandomAccess(Document.plainRandomAccess).createBestSource(str), false, (byte[]) null, certificate2, key, str2, (ExternalDecryptionProcess) null, true);
    }

    public PdfReader(String str, Certificate certificate2, ExternalDecryptionProcess externalDecryptionProcess2) throws IOException {
        this(new RandomAccessSourceFactory().setForceRead(false).setUsePlainRandomAccess(Document.plainRandomAccess).createBestSource(str), false, (byte[]) null, certificate2, (Key) null, (String) null, externalDecryptionProcess2, true);
    }

    public PdfReader(byte[] bArr, Certificate certificate2, ExternalDecryptionProcess externalDecryptionProcess2) throws IOException {
        this(new RandomAccessSourceFactory().setForceRead(false).setUsePlainRandomAccess(Document.plainRandomAccess).createSource(bArr), false, (byte[]) null, certificate2, (Key) null, (String) null, externalDecryptionProcess2, true);
    }

    public PdfReader(InputStream inputStream, Certificate certificate2, ExternalDecryptionProcess externalDecryptionProcess2) throws IOException {
        this(new RandomAccessSourceFactory().setForceRead(false).setUsePlainRandomAccess(Document.plainRandomAccess).createSource(inputStream), false, (byte[]) null, certificate2, (Key) null, (String) null, externalDecryptionProcess2, true);
    }

    public PdfReader(URL url) throws IOException {
        this(url, (byte[]) null);
    }

    public PdfReader(URL url, byte[] bArr) throws IOException {
        this(new RandomAccessSourceFactory().createSource(url), false, bArr, (Certificate) null, (Key) null, (String) null, (ExternalDecryptionProcess) null, true);
    }

    public PdfReader(InputStream inputStream, byte[] bArr) throws IOException {
        this(new RandomAccessSourceFactory().createSource(inputStream), false, bArr, (Certificate) null, (Key) null, (String) null, (ExternalDecryptionProcess) null, false);
    }

    public PdfReader(InputStream inputStream) throws IOException {
        this(inputStream, (byte[]) null);
    }

    public PdfReader(RandomAccessFileOrArray randomAccessFileOrArray, byte[] bArr) throws IOException {
        this(randomAccessFileOrArray, bArr, true);
    }

    public PdfReader(RandomAccessFileOrArray randomAccessFileOrArray, byte[] bArr, boolean z) throws IOException {
        this(randomAccessFileOrArray.getByteSource(), z, bArr, (Certificate) null, (Key) null, (String) null, (ExternalDecryptionProcess) null, false);
    }

    public PdfReader(PdfReader pdfReader) {
        this.acroForm = null;
        this.acroFormParsed = false;
        this.encrypted = false;
        this.rebuilt = false;
        this.tampered = false;
        this.password = null;
        this.certificateKey = null;
        this.certificate = null;
        this.certificateKeyProvider = null;
        this.externalDecryptionProcess = null;
        this.strings = new ArrayList<>();
        this.sharedStreams = true;
        this.consolidateNamedDestinations = false;
        this.remoteToLocalNamedDestinations = false;
        this.lastXrefPartial = -1;
        this.viewerPreferences = new PdfViewerPreferencesImp();
        this.readDepth = 0;
        this.appendable = pdfReader.appendable;
        this.consolidateNamedDestinations = pdfReader.consolidateNamedDestinations;
        this.encrypted = pdfReader.encrypted;
        this.rebuilt = pdfReader.rebuilt;
        this.sharedStreams = pdfReader.sharedStreams;
        this.tampered = pdfReader.tampered;
        this.password = pdfReader.password;
        this.pdfVersion = pdfReader.pdfVersion;
        this.eofPos = pdfReader.eofPos;
        this.freeXref = pdfReader.freeXref;
        this.lastXref = pdfReader.lastXref;
        this.newXrefType = pdfReader.newXrefType;
        this.tokens = new PRTokeniser(pdfReader.tokens.getSafeFile());
        if (pdfReader.decrypt != null) {
            this.decrypt = new PdfEncryption(pdfReader.decrypt);
        }
        this.pValue = pdfReader.pValue;
        this.rValue = pdfReader.rValue;
        this.xrefObj = new ArrayList<>(pdfReader.xrefObj);
        for (int i = 0; i < pdfReader.xrefObj.size(); i++) {
            this.xrefObj.set(i, duplicatePdfObject(pdfReader.xrefObj.get(i), this));
        }
        this.pageRefs = new PageRefs(pdfReader.pageRefs, this);
        this.trailer = (PdfDictionary) duplicatePdfObject(pdfReader.trailer, this);
        this.catalog = this.trailer.getAsDict(PdfName.ROOT);
        this.rootPages = this.catalog.getAsDict(PdfName.PAGES);
        this.fileLength = pdfReader.fileLength;
        this.partial = pdfReader.partial;
        this.hybridXref = pdfReader.hybridXref;
        this.objStmToOffset = pdfReader.objStmToOffset;
        this.xref = pdfReader.xref;
        this.cryptoRef = (PRIndirectReference) duplicatePdfObject(pdfReader.cryptoRef, this);
        this.ownerPasswordUsed = pdfReader.ownerPasswordUsed;
    }

    private static PRTokeniser getOffsetTokeniser(RandomAccessSource randomAccessSource) throws IOException {
        PRTokeniser pRTokeniser = new PRTokeniser(new RandomAccessFileOrArray(randomAccessSource));
        int headerOffset = pRTokeniser.getHeaderOffset();
        return headerOffset != 0 ? new PRTokeniser(new RandomAccessFileOrArray((RandomAccessSource) new WindowRandomAccessSource(randomAccessSource, (long) headerOffset))) : pRTokeniser;
    }

    public RandomAccessFileOrArray getSafeFile() {
        return this.tokens.getSafeFile();
    }

    /* access modifiers changed from: protected */
    public PdfReaderInstance getPdfReaderInstance(PdfWriter pdfWriter) {
        return new PdfReaderInstance(this, pdfWriter);
    }

    public int getNumberOfPages() {
        return this.pageRefs.size();
    }

    public PdfDictionary getCatalog() {
        return this.catalog;
    }

    public PRAcroForm getAcroForm() {
        if (!this.acroFormParsed) {
            this.acroFormParsed = true;
            PdfObject pdfObject = this.catalog.get(PdfName.ACROFORM);
            if (pdfObject != null) {
                try {
                    this.acroForm = new PRAcroForm(this);
                    this.acroForm.readAcroForm((PdfDictionary) getPdfObject(pdfObject));
                } catch (Exception unused) {
                    this.acroForm = null;
                }
            }
        }
        return this.acroForm;
    }

    public int getPageRotation(int i) {
        return getPageRotation(this.pageRefs.getPageNRelease(i));
    }

    /* access modifiers changed from: package-private */
    public int getPageRotation(PdfDictionary pdfDictionary) {
        PdfNumber asNumber = pdfDictionary.getAsNumber(PdfName.ROTATE);
        if (asNumber == null) {
            return 0;
        }
        int intValue = asNumber.intValue() % 360;
        return intValue < 0 ? intValue + 360 : intValue;
    }

    public Rectangle getPageSizeWithRotation(int i) {
        return getPageSizeWithRotation(this.pageRefs.getPageNRelease(i));
    }

    public Rectangle getPageSizeWithRotation(PdfDictionary pdfDictionary) {
        Rectangle pageSize = getPageSize(pdfDictionary);
        for (int pageRotation = getPageRotation(pdfDictionary); pageRotation > 0; pageRotation -= 90) {
            pageSize = pageSize.rotate();
        }
        return pageSize;
    }

    public Rectangle getPageSize(int i) {
        return getPageSize(this.pageRefs.getPageNRelease(i));
    }

    public Rectangle getPageSize(PdfDictionary pdfDictionary) {
        return getNormalizedRectangle(pdfDictionary.getAsArray(PdfName.MEDIABOX));
    }

    public Rectangle getCropBox(int i) {
        PdfDictionary pageNRelease = this.pageRefs.getPageNRelease(i);
        PdfArray pdfArray = (PdfArray) getPdfObjectRelease(pageNRelease.get(PdfName.CROPBOX));
        if (pdfArray == null) {
            return getPageSize(pageNRelease);
        }
        return getNormalizedRectangle(pdfArray);
    }

    public Rectangle getBoxSize(int i, String str) {
        PdfArray pdfArray;
        PdfDictionary pageNRelease = this.pageRefs.getPageNRelease(i);
        if (str.equals("trim")) {
            pdfArray = (PdfArray) getPdfObjectRelease(pageNRelease.get(PdfName.TRIMBOX));
        } else if (str.equals("art")) {
            pdfArray = (PdfArray) getPdfObjectRelease(pageNRelease.get(PdfName.ARTBOX));
        } else if (str.equals("bleed")) {
            pdfArray = (PdfArray) getPdfObjectRelease(pageNRelease.get(PdfName.BLEEDBOX));
        } else if (str.equals("crop")) {
            pdfArray = (PdfArray) getPdfObjectRelease(pageNRelease.get(PdfName.CROPBOX));
        } else {
            pdfArray = str.equals("media") ? (PdfArray) getPdfObjectRelease(pageNRelease.get(PdfName.MEDIABOX)) : null;
        }
        if (pdfArray == null) {
            return null;
        }
        return getNormalizedRectangle(pdfArray);
    }

    public HashMap<String, String> getInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        PdfDictionary asDict = this.trailer.getAsDict(PdfName.INFO);
        if (asDict == null) {
            return hashMap;
        }
        for (PdfName next : asDict.getKeys()) {
            PdfObject pdfObject = getPdfObject(asDict.get(next));
            if (pdfObject != null) {
                String pdfObject2 = pdfObject.toString();
                switch (pdfObject.type()) {
                    case 3:
                        pdfObject2 = ((PdfString) pdfObject).toUnicodeString();
                        break;
                    case 4:
                        pdfObject2 = PdfName.decodeName(pdfObject2);
                        break;
                }
                hashMap.put(PdfName.decodeName(next.toString()), pdfObject2);
            }
        }
        return hashMap;
    }

    public static Rectangle getNormalizedRectangle(PdfArray pdfArray) {
        float floatValue = ((PdfNumber) getPdfObjectRelease(pdfArray.getPdfObject(0))).floatValue();
        float floatValue2 = ((PdfNumber) getPdfObjectRelease(pdfArray.getPdfObject(1))).floatValue();
        float floatValue3 = ((PdfNumber) getPdfObjectRelease(pdfArray.getPdfObject(2))).floatValue();
        float floatValue4 = ((PdfNumber) getPdfObjectRelease(pdfArray.getPdfObject(3))).floatValue();
        return new Rectangle(Math.min(floatValue, floatValue3), Math.min(floatValue2, floatValue4), Math.max(floatValue, floatValue3), Math.max(floatValue2, floatValue4));
    }

    public boolean isTagged() {
        PdfDictionary asDict = this.catalog.getAsDict(PdfName.MARKINFO);
        if (asDict == null || !PdfBoolean.PDFTRUE.equals(asDict.getAsBoolean(PdfName.MARKED)) || this.catalog.getAsDict(PdfName.STRUCTTREEROOT) == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void readPdf() throws IOException {
        this.fileLength = this.tokens.getFile().length();
        this.pdfVersion = this.tokens.checkPdfHeader();
        try {
            readXref();
        } catch (Exception e) {
            try {
                this.rebuilt = true;
                rebuildXref();
                this.lastXref = -1;
            } catch (Exception e2) {
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("rebuild.failed.1.original.message.2", e2.getMessage(), e.getMessage()));
            }
        }
        try {
            readDocObj();
        } catch (Exception e3) {
            if (e3 instanceof BadPasswordException) {
                throw new BadPasswordException(e3.getMessage());
            } else if (this.rebuilt || this.encryptionError) {
                throw new InvalidPdfException(e3.getMessage());
            } else {
                this.rebuilt = true;
                this.encrypted = false;
                try {
                    rebuildXref();
                    this.lastXref = -1;
                    readDocObj();
                } catch (Exception e4) {
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("rebuild.failed.1.original.message.2", e4.getMessage(), e3.getMessage()));
                }
            }
        }
        this.strings.clear();
        readPages();
        removeUnusedObjects();
    }

    /* access modifiers changed from: protected */
    public void readPdfPartial() throws IOException {
        this.fileLength = this.tokens.getFile().length();
        this.pdfVersion = this.tokens.checkPdfHeader();
        try {
            readXref();
        } catch (Exception e) {
            try {
                this.rebuilt = true;
                rebuildXref();
                this.lastXref = -1;
            } catch (Exception e2) {
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("rebuild.failed.1.original.message.2", e2.getMessage(), e.getMessage()), e2);
            }
        }
        readDocObjPartial();
        readPages();
    }

    private boolean equalsArray(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0149, code lost:
        r16 = r6;
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x021d, code lost:
        r3 = r9;
        r6 = 4;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0220, code lost:
        r14 = 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readDecryptedDocObj() throws java.io.IOException {
        /*
            r19 = this;
            r1 = r19
            boolean r2 = r1.encrypted
            if (r2 == 0) goto L_0x0007
            return
        L_0x0007:
            com.itextpdf.text.pdf.PdfDictionary r2 = r1.trailer
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.ENCRYPT
            com.itextpdf.text.pdf.PdfObject r2 = r2.get(r3)
            if (r2 == 0) goto L_0x0532
            java.lang.String r3 = r2.toString()
            java.lang.String r4 = "null"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x001f
            goto L_0x0532
        L_0x001f:
            r3 = 1
            r1.encryptionError = r3
            r1.encrypted = r3
            com.itextpdf.text.pdf.PdfObject r4 = getPdfObject((com.itextpdf.text.pdf.PdfObject) r2)
            com.itextpdf.text.pdf.PdfDictionary r4 = (com.itextpdf.text.pdf.PdfDictionary) r4
            com.itextpdf.text.pdf.PdfDictionary r5 = r1.trailer
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.ID
            com.itextpdf.text.pdf.PdfArray r5 = r5.getAsArray(r6)
            r7 = 0
            if (r5 == 0) goto L_0x0056
            com.itextpdf.text.pdf.PdfObject r8 = r5.getPdfObject(r7)
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r9 = r1.strings
            r9.remove(r8)
            java.lang.String r8 = r8.toString()
            byte[] r8 = com.itextpdf.text.DocWriter.getISOBytes(r8)
            int r9 = r5.size()
            if (r9 <= r3) goto L_0x0057
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r9 = r1.strings
            com.itextpdf.text.pdf.PdfObject r5 = r5.getPdfObject(r3)
            r9.remove(r5)
            goto L_0x0057
        L_0x0056:
            r8 = 0
        L_0x0057:
            if (r8 != 0) goto L_0x005b
            byte[] r8 = new byte[r7]
        L_0x005b:
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.FILTER
            com.itextpdf.text.pdf.PdfObject r5 = r4.get(r5)
            com.itextpdf.text.pdf.PdfObject r5 = getPdfObjectRelease((com.itextpdf.text.pdf.PdfObject) r5)
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.STANDARD
            boolean r9 = r5.equals(r9)
            r11 = 40
            r12 = 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0223
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.U
            com.itextpdf.text.pdf.PdfObject r9 = r4.get(r9)
            java.lang.String r9 = r9.toString()
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r13 = r1.strings
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.U
            com.itextpdf.text.pdf.PdfObject r10 = r4.get(r10)
            r13.remove(r10)
            byte[] r9 = com.itextpdf.text.DocWriter.getISOBytes(r9)
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.O
            com.itextpdf.text.pdf.PdfObject r10 = r4.get(r10)
            java.lang.String r10 = r10.toString()
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r13 = r1.strings
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.O
            com.itextpdf.text.pdf.PdfObject r6 = r4.get(r6)
            r13.remove(r6)
            byte[] r6 = com.itextpdf.text.DocWriter.getISOBytes(r10)
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.OE
            boolean r10 = r4.contains(r10)
            if (r10 == 0) goto L_0x00b6
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r10 = r1.strings
            com.itextpdf.text.pdf.PdfName r13 = com.itextpdf.text.pdf.PdfName.OE
            com.itextpdf.text.pdf.PdfObject r13 = r4.get(r13)
            r10.remove(r13)
        L_0x00b6:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.UE
            boolean r10 = r4.contains(r10)
            if (r10 == 0) goto L_0x00c9
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r10 = r1.strings
            com.itextpdf.text.pdf.PdfName r13 = com.itextpdf.text.pdf.PdfName.UE
            com.itextpdf.text.pdf.PdfObject r13 = r4.get(r13)
            r10.remove(r13)
        L_0x00c9:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.PERMS
            boolean r10 = r4.contains(r10)
            if (r10 == 0) goto L_0x00dc
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r10 = r1.strings
            com.itextpdf.text.pdf.PdfName r13 = com.itextpdf.text.pdf.PdfName.PERMS
            com.itextpdf.text.pdf.PdfObject r13 = r4.get(r13)
            r10.remove(r13)
        L_0x00dc:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.P
            com.itextpdf.text.pdf.PdfObject r10 = r4.get(r10)
            boolean r13 = r10.isNumber()
            if (r13 != 0) goto L_0x00f6
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "illegal.p.value"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x00f6:
            com.itextpdf.text.pdf.PdfNumber r10 = (com.itextpdf.text.pdf.PdfNumber) r10
            long r14 = r10.longValue()
            r1.pValue = r14
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.R
            com.itextpdf.text.pdf.PdfObject r10 = r4.get(r10)
            boolean r13 = r10.isNumber()
            if (r13 != 0) goto L_0x0118
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "illegal.r.value"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x0118:
            com.itextpdf.text.pdf.PdfNumber r10 = (com.itextpdf.text.pdf.PdfNumber) r10
            int r10 = r10.intValue()
            r1.rValue = r10
            int r10 = r1.rValue
            switch(r10) {
                case 2: goto L_0x0219;
                case 3: goto L_0x01d8;
                case 4: goto L_0x0157;
                case 5: goto L_0x0133;
                default: goto L_0x0125;
            }
        L_0x0125:
            com.itextpdf.text.exceptions.UnsupportedPdfException r2 = new com.itextpdf.text.exceptions.UnsupportedPdfException
            java.lang.String r3 = "unknown.encryption.type.r.eq.1"
            int r4 = r1.rValue
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (int) r4)
            r2.<init>(r3)
            throw r2
        L_0x0133:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.ENCRYPTMETADATA
            com.itextpdf.text.pdf.PdfObject r10 = r4.get(r10)
            if (r10 == 0) goto L_0x014e
            java.lang.String r10 = r10.toString()
            java.lang.String r11 = "false"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x014e
            r10 = 11
        L_0x0149:
            r16 = r6
            r11 = r7
            goto L_0x021d
        L_0x014e:
            r16 = r6
            r11 = r7
            r3 = r9
            r6 = 4
            r9 = 0
            r10 = 3
            goto L_0x0220
        L_0x0157:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.CF
            com.itextpdf.text.pdf.PdfObject r10 = r4.get(r10)
            com.itextpdf.text.pdf.PdfDictionary r10 = (com.itextpdf.text.pdf.PdfDictionary) r10
            if (r10 != 0) goto L_0x016f
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "cf.not.found.encryption"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x016f:
            com.itextpdf.text.pdf.PdfName r11 = com.itextpdf.text.pdf.PdfName.STDCF
            com.itextpdf.text.pdf.PdfObject r10 = r10.get(r11)
            com.itextpdf.text.pdf.PdfDictionary r10 = (com.itextpdf.text.pdf.PdfDictionary) r10
            if (r10 != 0) goto L_0x0187
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "stdcf.not.found.encryption"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x0187:
            com.itextpdf.text.pdf.PdfName r11 = com.itextpdf.text.pdf.PdfName.V2
            com.itextpdf.text.pdf.PdfName r12 = com.itextpdf.text.pdf.PdfName.CFM
            com.itextpdf.text.pdf.PdfObject r12 = r10.get(r12)
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x0198
            r16 = r3
            goto L_0x01a8
        L_0x0198:
            com.itextpdf.text.pdf.PdfName r11 = com.itextpdf.text.pdf.PdfName.AESV2
            com.itextpdf.text.pdf.PdfName r12 = com.itextpdf.text.pdf.PdfName.CFM
            com.itextpdf.text.pdf.PdfObject r10 = r10.get(r12)
            boolean r10 = r11.equals(r10)
            if (r10 == 0) goto L_0x01ca
            r16 = 2
        L_0x01a8:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.ENCRYPTMETADATA
            com.itextpdf.text.pdf.PdfObject r10 = r4.get(r10)
            if (r10 == 0) goto L_0x01bf
            java.lang.String r10 = r10.toString()
            java.lang.String r11 = "false"
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x01bf
            r10 = r16 | 8
            goto L_0x0149
        L_0x01bf:
            r11 = r7
            r3 = r9
            r10 = r16
            r9 = 0
            r14 = 3
            r16 = r6
            r6 = 4
            goto L_0x0455
        L_0x01ca:
            com.itextpdf.text.exceptions.UnsupportedPdfException r2 = new com.itextpdf.text.exceptions.UnsupportedPdfException
            java.lang.String r3 = "no.compatible.encryption.found"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x01d8:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.LENGTH
            com.itextpdf.text.pdf.PdfObject r10 = r4.get(r10)
            boolean r13 = r10.isNumber()
            if (r13 != 0) goto L_0x01f2
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "illegal.length.value"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x01f2:
            com.itextpdf.text.pdf.PdfNumber r10 = (com.itextpdf.text.pdf.PdfNumber) r10
            int r10 = r10.intValue()
            if (r10 > r12) goto L_0x020b
            if (r10 < r11) goto L_0x020b
            int r11 = r10 % 8
            if (r11 == 0) goto L_0x0201
            goto L_0x020b
        L_0x0201:
            r16 = r6
            r11 = r10
            r6 = 4
            r14 = 3
            r10 = r3
            r3 = r9
            r9 = 0
            goto L_0x0455
        L_0x020b:
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "illegal.length.value"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x0219:
            r16 = r6
            r10 = r7
            r11 = r10
        L_0x021d:
            r3 = r9
            r6 = 4
            r9 = 0
        L_0x0220:
            r14 = 3
            goto L_0x0455
        L_0x0223:
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.PUBSEC
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x044d
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.V
            com.itextpdf.text.pdf.PdfObject r6 = r4.get(r6)
            boolean r9 = r6.isNumber()
            if (r9 != 0) goto L_0x0245
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "illegal.v.value"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x0245:
            com.itextpdf.text.pdf.PdfNumber r6 = (com.itextpdf.text.pdf.PdfNumber) r6
            int r6 = r6.intValue()
            switch(r6) {
                case 1: goto L_0x0330;
                case 2: goto L_0x02ec;
                case 3: goto L_0x024e;
                case 4: goto L_0x025a;
                case 5: goto L_0x025a;
                default: goto L_0x024e;
            }
        L_0x024e:
            com.itextpdf.text.exceptions.UnsupportedPdfException r2 = new com.itextpdf.text.exceptions.UnsupportedPdfException
            java.lang.String r3 = "unknown.encryption.type.v.eq.1"
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (int) r6)
            r2.<init>(r3)
            throw r2
        L_0x025a:
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.CF
            com.itextpdf.text.pdf.PdfObject r6 = r4.get(r6)
            com.itextpdf.text.pdf.PdfDictionary r6 = (com.itextpdf.text.pdf.PdfDictionary) r6
            if (r6 != 0) goto L_0x0272
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "cf.not.found.encryption"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x0272:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.DEFAULTCRYPTFILTER
            com.itextpdf.text.pdf.PdfObject r6 = r6.get(r9)
            com.itextpdf.text.pdf.PdfDictionary r6 = (com.itextpdf.text.pdf.PdfDictionary) r6
            if (r6 != 0) goto L_0x028a
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "defaultcryptfilter.not.found.encryption"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x028a:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.V2
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.CFM
            com.itextpdf.text.pdf.PdfObject r10 = r6.get(r10)
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x029b
            r16 = r3
            goto L_0x02be
        L_0x029b:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.AESV2
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.CFM
            com.itextpdf.text.pdf.PdfObject r10 = r6.get(r10)
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x02ac
            r16 = 2
            goto L_0x02be
        L_0x02ac:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.AESV3
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.CFM
            com.itextpdf.text.pdf.PdfObject r10 = r6.get(r10)
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x02de
            r12 = 256(0x100, float:3.59E-43)
            r16 = 3
        L_0x02be:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.ENCRYPTMETADATA
            com.itextpdf.text.pdf.PdfObject r9 = r6.get(r9)
            if (r9 == 0) goto L_0x02d4
            java.lang.String r9 = r9.toString()
            java.lang.String r10 = "false"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x02d4
            r16 = r16 | 8
        L_0x02d4:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.RECIPIENTS
            com.itextpdf.text.pdf.PdfObject r6 = r6.get(r9)
            com.itextpdf.text.pdf.PdfArray r6 = (com.itextpdf.text.pdf.PdfArray) r6
            r11 = r12
            goto L_0x033a
        L_0x02de:
            com.itextpdf.text.exceptions.UnsupportedPdfException r2 = new com.itextpdf.text.exceptions.UnsupportedPdfException
            java.lang.String r3 = "no.compatible.encryption.found"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x02ec:
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.LENGTH
            com.itextpdf.text.pdf.PdfObject r6 = r4.get(r6)
            boolean r9 = r6.isNumber()
            if (r9 != 0) goto L_0x0306
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "illegal.length.value"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x0306:
            com.itextpdf.text.pdf.PdfNumber r6 = (com.itextpdf.text.pdf.PdfNumber) r6
            int r6 = r6.intValue()
            if (r6 > r12) goto L_0x0322
            if (r6 < r11) goto L_0x0322
            int r9 = r6 % 8
            if (r9 == 0) goto L_0x0315
            goto L_0x0322
        L_0x0315:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.RECIPIENTS
            com.itextpdf.text.pdf.PdfObject r9 = r4.get(r9)
            com.itextpdf.text.pdf.PdfArray r9 = (com.itextpdf.text.pdf.PdfArray) r9
            r16 = r3
            r11 = r6
            r6 = r9
            goto L_0x033a
        L_0x0322:
            com.itextpdf.text.exceptions.InvalidPdfException r2 = new com.itextpdf.text.exceptions.InvalidPdfException
            java.lang.String r3 = "illegal.length.value"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x0330:
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.RECIPIENTS
            com.itextpdf.text.pdf.PdfObject r6 = r4.get(r6)
            com.itextpdf.text.pdf.PdfArray r6 = (com.itextpdf.text.pdf.PdfArray) r6
            r16 = r7
        L_0x033a:
            org.spongycastle.cert.X509CertificateHolder r9 = new org.spongycastle.cert.X509CertificateHolder     // Catch:{ Exception -> 0x0445 }
            java.security.cert.Certificate r10 = r1.certificate     // Catch:{ Exception -> 0x0445 }
            byte[] r10 = r10.getEncoded()     // Catch:{ Exception -> 0x0445 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0445 }
            com.itextpdf.text.pdf.security.ExternalDecryptionProcess r10 = r1.externalDecryptionProcess
            if (r10 != 0) goto L_0x03a3
            r10 = r7
            r12 = r10
            r13 = 0
        L_0x034c:
            int r14 = r6.size()
            if (r10 >= r14) goto L_0x03a1
            com.itextpdf.text.pdf.PdfObject r14 = r6.getPdfObject(r10)
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r15 = r1.strings
            r15.remove(r14)
            org.spongycastle.cms.CMSEnvelopedData r15 = new org.spongycastle.cms.CMSEnvelopedData     // Catch:{ Exception -> 0x0399 }
            byte[] r14 = r14.getBytes()     // Catch:{ Exception -> 0x0399 }
            r15.<init>(r14)     // Catch:{ Exception -> 0x0399 }
            org.spongycastle.cms.RecipientInformationStore r14 = r15.getRecipientInfos()     // Catch:{ Exception -> 0x0399 }
            java.util.Collection r14 = r14.getRecipients()     // Catch:{ Exception -> 0x0399 }
            java.util.Iterator r14 = r14.iterator()     // Catch:{ Exception -> 0x0399 }
        L_0x0370:
            boolean r15 = r14.hasNext()     // Catch:{ Exception -> 0x0399 }
            if (r15 == 0) goto L_0x0395
            java.lang.Object r15 = r14.next()     // Catch:{ Exception -> 0x0399 }
            org.spongycastle.cms.RecipientInformation r15 = (org.spongycastle.cms.RecipientInformation) r15     // Catch:{ Exception -> 0x0399 }
            org.spongycastle.cms.RecipientId r3 = r15.getRID()     // Catch:{ Exception -> 0x0399 }
            boolean r3 = r3.match(r9)     // Catch:{ Exception -> 0x0399 }
            if (r3 == 0) goto L_0x0393
            if (r12 != 0) goto L_0x0393
            java.security.Key r3 = r1.certificateKey     // Catch:{ Exception -> 0x0399 }
            java.security.PrivateKey r3 = (java.security.PrivateKey) r3     // Catch:{ Exception -> 0x0399 }
            java.lang.String r12 = r1.certificateKeyProvider     // Catch:{ Exception -> 0x0399 }
            byte[] r13 = com.itextpdf.text.pdf.PdfEncryptor.getContent(r15, r3, r12)     // Catch:{ Exception -> 0x0399 }
            r12 = 1
        L_0x0393:
            r3 = 1
            goto L_0x0370
        L_0x0395:
            int r10 = r10 + 1
            r3 = 1
            goto L_0x034c
        L_0x0399:
            r0 = move-exception
            r2 = r0
            com.itextpdf.text.ExceptionConverter r3 = new com.itextpdf.text.ExceptionConverter
            r3.<init>(r2)
            throw r3
        L_0x03a1:
            r9 = r12
            goto L_0x03e5
        L_0x03a3:
            r3 = r7
            r9 = r3
            r13 = 0
        L_0x03a6:
            int r10 = r6.size()
            if (r3 >= r10) goto L_0x03e5
            com.itextpdf.text.pdf.PdfObject r10 = r6.getPdfObject(r3)
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r12 = r1.strings
            r12.remove(r10)
            org.spongycastle.cms.CMSEnvelopedData r12 = new org.spongycastle.cms.CMSEnvelopedData     // Catch:{ Exception -> 0x03dd }
            byte[] r10 = r10.getBytes()     // Catch:{ Exception -> 0x03dd }
            r12.<init>(r10)     // Catch:{ Exception -> 0x03dd }
            org.spongycastle.cms.RecipientInformationStore r10 = r12.getRecipientInfos()     // Catch:{ Exception -> 0x03dd }
            com.itextpdf.text.pdf.security.ExternalDecryptionProcess r12 = r1.externalDecryptionProcess     // Catch:{ Exception -> 0x03dd }
            org.spongycastle.cms.RecipientId r12 = r12.getCmsRecipientId()     // Catch:{ Exception -> 0x03dd }
            org.spongycastle.cms.RecipientInformation r10 = r10.get(r12)     // Catch:{ Exception -> 0x03dd }
            if (r10 == 0) goto L_0x03da
            com.itextpdf.text.pdf.security.ExternalDecryptionProcess r9 = r1.externalDecryptionProcess     // Catch:{ Exception -> 0x03dd }
            org.spongycastle.cms.Recipient r9 = r9.getCmsRecipient()     // Catch:{ Exception -> 0x03dd }
            byte[] r9 = r10.getContent(r9)     // Catch:{ Exception -> 0x03dd }
            r13 = r9
            r9 = 1
        L_0x03da:
            int r3 = r3 + 1
            goto L_0x03a6
        L_0x03dd:
            r0 = move-exception
            r2 = r0
            com.itextpdf.text.ExceptionConverter r3 = new com.itextpdf.text.ExceptionConverter
            r3.<init>(r2)
            throw r3
        L_0x03e5:
            if (r9 == 0) goto L_0x0437
            if (r13 != 0) goto L_0x03ea
            goto L_0x0437
        L_0x03ea:
            r3 = r16 & 7
            r14 = 3
            if (r3 != r14) goto L_0x03f9
            java.lang.String r3 = "SHA-256"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch:{ Exception -> 0x03f6 }
            goto L_0x03ff
        L_0x03f6:
            r0 = move-exception
            r2 = r0
            goto L_0x0431
        L_0x03f9:
            java.lang.String r3 = "SHA-1"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch:{ Exception -> 0x03f6 }
        L_0x03ff:
            r9 = 20
            r3.update(r13, r7, r9)     // Catch:{ Exception -> 0x03f6 }
            r9 = r7
        L_0x0405:
            int r10 = r6.size()     // Catch:{ Exception -> 0x03f6 }
            if (r9 >= r10) goto L_0x0419
            com.itextpdf.text.pdf.PdfObject r10 = r6.getPdfObject(r9)     // Catch:{ Exception -> 0x03f6 }
            byte[] r10 = r10.getBytes()     // Catch:{ Exception -> 0x03f6 }
            r3.update(r10)     // Catch:{ Exception -> 0x03f6 }
            int r9 = r9 + 1
            goto L_0x0405
        L_0x0419:
            r6 = r16 & 8
            if (r6 == 0) goto L_0x0427
            r6 = 4
            byte[] r9 = new byte[r6]     // Catch:{ Exception -> 0x03f6 }
            r9 = {-1, -1, -1, -1} // fill-array     // Catch:{ Exception -> 0x03f6 }
            r3.update(r9)     // Catch:{ Exception -> 0x03f6 }
            goto L_0x0428
        L_0x0427:
            r6 = 4
        L_0x0428:
            byte[] r3 = r3.digest()     // Catch:{ Exception -> 0x03f6 }
            r9 = r3
            r10 = r16
            r3 = 0
            goto L_0x0453
        L_0x0431:
            com.itextpdf.text.ExceptionConverter r3 = new com.itextpdf.text.ExceptionConverter
            r3.<init>(r2)
            throw r3
        L_0x0437:
            com.itextpdf.text.exceptions.UnsupportedPdfException r2 = new com.itextpdf.text.exceptions.UnsupportedPdfException
            java.lang.String r3 = "bad.certificate.and.key"
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x0445:
            r0 = move-exception
            r2 = r0
            com.itextpdf.text.ExceptionConverter r3 = new com.itextpdf.text.ExceptionConverter
            r3.<init>(r2)
            throw r3
        L_0x044d:
            r6 = 4
            r14 = 3
            r10 = r7
            r11 = r10
            r3 = 0
            r9 = 0
        L_0x0453:
            r16 = 0
        L_0x0455:
            com.itextpdf.text.pdf.PdfEncryption r12 = new com.itextpdf.text.pdf.PdfEncryption
            r12.<init>()
            r1.decrypt = r12
            com.itextpdf.text.pdf.PdfEncryption r12 = r1.decrypt
            r12.setCryptoMode(r10, r11)
            com.itextpdf.text.pdf.PdfName r12 = com.itextpdf.text.pdf.PdfName.STANDARD
            boolean r12 = r5.equals(r12)
            if (r12 == 0) goto L_0x04e5
            int r5 = r1.rValue
            r9 = 5
            if (r5 != r9) goto L_0x0486
            com.itextpdf.text.pdf.PdfEncryption r3 = r1.decrypt
            byte[] r5 = r1.password
            boolean r3 = r3.readKey(r4, r5)
            r1.ownerPasswordUsed = r3
            com.itextpdf.text.pdf.PdfEncryption r3 = r1.decrypt
            r3.documentID = r8
            com.itextpdf.text.pdf.PdfEncryption r3 = r1.decrypt
            long r3 = r3.getPermissions()
            r1.pValue = r3
            goto L_0x0501
        L_0x0486:
            com.itextpdf.text.pdf.PdfEncryption r9 = r1.decrypt
            byte[] r11 = r1.password
            long r4 = r1.pValue
            r10 = r8
            r12 = r3
            r13 = r16
            r7 = r6
            r6 = r14
            r14 = r4
            r9.setupByOwnerPassword(r10, r11, r12, r13, r14)
            com.itextpdf.text.pdf.PdfEncryption r4 = r1.decrypt
            byte[] r4 = r4.userKey
            int r5 = r1.rValue
            r15 = 16
            r17 = 32
            if (r5 == r6) goto L_0x04aa
            int r5 = r1.rValue
            if (r5 != r7) goto L_0x04a7
            goto L_0x04aa
        L_0x04a7:
            r5 = r17
            goto L_0x04ab
        L_0x04aa:
            r5 = r15
        L_0x04ab:
            boolean r4 = r1.equalsArray(r3, r4, r5)
            if (r4 != 0) goto L_0x04e1
            com.itextpdf.text.pdf.PdfEncryption r9 = r1.decrypt
            byte[] r11 = r1.password
            long r13 = r1.pValue
            r10 = r8
            r12 = r16
            r9.setupByUserPassword(r10, r11, r12, r13)
            com.itextpdf.text.pdf.PdfEncryption r4 = r1.decrypt
            byte[] r4 = r4.userKey
            int r5 = r1.rValue
            if (r5 == r6) goto L_0x04cc
            int r5 = r1.rValue
            if (r5 != r7) goto L_0x04ca
            goto L_0x04cc
        L_0x04ca:
            r15 = r17
        L_0x04cc:
            boolean r3 = r1.equalsArray(r3, r4, r15)
            if (r3 != 0) goto L_0x0501
            com.itextpdf.text.exceptions.BadPasswordException r2 = new com.itextpdf.text.exceptions.BadPasswordException
            java.lang.String r3 = "bad.user.password"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)
            r2.<init>(r3)
            throw r2
        L_0x04e1:
            r3 = 1
            r1.ownerPasswordUsed = r3
            goto L_0x0501
        L_0x04e5:
            r6 = r14
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.PUBSEC
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0501
            r3 = r10 & 7
            if (r3 != r6) goto L_0x04f9
            com.itextpdf.text.pdf.PdfEncryption r3 = r1.decrypt
            r3.setKey(r9)
        L_0x04f7:
            r3 = 1
            goto L_0x04ff
        L_0x04f9:
            com.itextpdf.text.pdf.PdfEncryption r3 = r1.decrypt
            r3.setupByEncryptionKey(r9, r11)
            goto L_0x04f7
        L_0x04ff:
            r1.ownerPasswordUsed = r3
        L_0x0501:
            r3 = 0
        L_0x0502:
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r4 = r1.strings
            int r4 = r4.size()
            if (r3 >= r4) goto L_0x0518
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r4 = r1.strings
            java.lang.Object r4 = r4.get(r3)
            com.itextpdf.text.pdf.PdfString r4 = (com.itextpdf.text.pdf.PdfString) r4
            r4.decrypt(r1)
            int r3 = r3 + 1
            goto L_0x0502
        L_0x0518:
            boolean r3 = r2.isIndirect()
            if (r3 == 0) goto L_0x052e
            com.itextpdf.text.pdf.PRIndirectReference r2 = (com.itextpdf.text.pdf.PRIndirectReference) r2
            r1.cryptoRef = r2
            java.util.ArrayList<com.itextpdf.text.pdf.PdfObject> r2 = r1.xrefObj
            com.itextpdf.text.pdf.PRIndirectReference r3 = r1.cryptoRef
            int r3 = r3.getNumber()
            r4 = 0
            r2.set(r3, r4)
        L_0x052e:
            r2 = 0
            r1.encryptionError = r2
            return
        L_0x0532:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfReader.readDecryptedDocObj():void");
    }

    public static PdfObject getPdfObjectRelease(PdfObject pdfObject) {
        PdfObject pdfObject2 = getPdfObject(pdfObject);
        releaseLastXrefPartial(pdfObject);
        return pdfObject2;
    }

    public static PdfObject getPdfObject(PdfObject pdfObject) {
        PdfObject pdfBoolean;
        if (pdfObject == null) {
            return null;
        }
        if (!pdfObject.isIndirect()) {
            return pdfObject;
        }
        try {
            PRIndirectReference pRIndirectReference = (PRIndirectReference) pdfObject;
            int number = pRIndirectReference.getNumber();
            boolean z = pRIndirectReference.getReader().appendable;
            PdfObject pdfObject2 = pRIndirectReference.getReader().getPdfObject(number);
            if (pdfObject2 == null) {
                return null;
            }
            if (z) {
                int type = pdfObject2.type();
                if (type == 1) {
                    pdfBoolean = new PdfBoolean(((PdfBoolean) pdfObject2).booleanValue());
                } else if (type == 4) {
                    pdfBoolean = new PdfName(pdfObject2.getBytes());
                } else if (type != 8) {
                    pdfObject2.setIndRef(pRIndirectReference);
                } else {
                    pdfBoolean = new PdfNull();
                }
                pdfObject2 = pdfBoolean;
                pdfObject2.setIndRef(pRIndirectReference);
            }
            return pdfObject2;
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public static PdfObject getPdfObjectRelease(PdfObject pdfObject, PdfObject pdfObject2) {
        PdfObject pdfObject3 = getPdfObject(pdfObject, pdfObject2);
        releaseLastXrefPartial(pdfObject);
        return pdfObject3;
    }

    public static PdfObject getPdfObject(PdfObject pdfObject, PdfObject pdfObject2) {
        PRIndirectReference indRef;
        PdfObject pdfObject3;
        if (pdfObject == null) {
            return null;
        }
        if (pdfObject.isIndirect()) {
            return getPdfObject(pdfObject);
        }
        if (!(pdfObject2 == null || (indRef = pdfObject2.getIndRef()) == null || !indRef.getReader().isAppendable())) {
            int type = pdfObject.type();
            if (type == 1) {
                pdfObject3 = new PdfBoolean(((PdfBoolean) pdfObject).booleanValue());
            } else if (type != 4) {
                if (type == 8) {
                    pdfObject = new PdfNull();
                }
                pdfObject.setIndRef(indRef);
            } else {
                pdfObject3 = new PdfName(pdfObject.getBytes());
            }
            pdfObject = pdfObject3;
            pdfObject.setIndRef(indRef);
        }
        return pdfObject;
    }

    public PdfObject getPdfObjectRelease(int i) {
        PdfObject pdfObject = getPdfObject(i);
        releaseLastXrefPartial();
        return pdfObject;
    }

    public PdfObject getPdfObject(int i) {
        try {
            this.lastXrefPartial = -1;
            if (i >= 0) {
                if (i < this.xrefObj.size()) {
                    PdfObject pdfObject = this.xrefObj.get(i);
                    if (this.partial) {
                        if (pdfObject == null) {
                            if (i * 2 >= this.xref.length) {
                                return null;
                            }
                            PdfObject readSingleObject = readSingleObject(i);
                            this.lastXrefPartial = -1;
                            if (readSingleObject != null) {
                                this.lastXrefPartial = i;
                            }
                            return readSingleObject;
                        }
                    }
                    return pdfObject;
                }
            }
            return null;
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public void resetLastXrefPartial() {
        this.lastXrefPartial = -1;
    }

    public void releaseLastXrefPartial() {
        if (this.partial && this.lastXrefPartial != -1) {
            this.xrefObj.set(this.lastXrefPartial, (Object) null);
            this.lastXrefPartial = -1;
        }
    }

    public static void releaseLastXrefPartial(PdfObject pdfObject) {
        if (pdfObject != null && pdfObject.isIndirect() && (pdfObject instanceof PRIndirectReference)) {
            PRIndirectReference pRIndirectReference = (PRIndirectReference) pdfObject;
            PdfReader reader = pRIndirectReference.getReader();
            if (reader.partial && reader.lastXrefPartial != -1 && reader.lastXrefPartial == pRIndirectReference.getNumber()) {
                reader.xrefObj.set(reader.lastXrefPartial, (Object) null);
            }
            reader.lastXrefPartial = -1;
        }
    }

    private void setXrefPartialObject(int i, PdfObject pdfObject) {
        if (this.partial && i >= 0) {
            this.xrefObj.set(i, pdfObject);
        }
    }

    public PRIndirectReference addPdfObject(PdfObject pdfObject) {
        this.xrefObj.add(pdfObject);
        return new PRIndirectReference(this, this.xrefObj.size() - 1);
    }

    /* access modifiers changed from: protected */
    public void readPages() throws IOException {
        this.catalog = this.trailer.getAsDict(PdfName.ROOT);
        if (this.catalog == null) {
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("the.document.has.no.catalog.object", new Object[0]));
        }
        this.rootPages = this.catalog.getAsDict(PdfName.PAGES);
        if (this.rootPages == null || !PdfName.PAGES.equals(this.rootPages.get(PdfName.TYPE))) {
            if (!debugmode) {
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("the.document.has.no.page.root", new Object[0]));
            } else if (LOGGER.isLogging(Level.ERROR)) {
                LOGGER.error(MessageLocalization.getComposedMessage("the.document.has.no.page.root", new Object[0]));
            }
        }
        this.pageRefs = new PageRefs();
    }

    /* access modifiers changed from: protected */
    public void readDocObjPartial() throws IOException {
        this.xrefObj = new ArrayList<>(this.xref.length / 2);
        this.xrefObj.addAll(Collections.nCopies(this.xref.length / 2, (Object) null));
        readDecryptedDocObj();
        if (this.objStmToOffset != null) {
            long[] keys = this.objStmToOffset.getKeys();
            for (long j : keys) {
                int i = (int) (2 * j);
                this.objStmToOffset.put(j, this.xref[i]);
                this.xref[i] = -1;
            }
        }
    }

    /* access modifiers changed from: protected */
    public PdfObject readSingleObject(int i) throws IOException {
        PdfObject pdfObject;
        this.strings.clear();
        int i2 = i * 2;
        long j = this.xref[i2];
        if (j < 0) {
            return null;
        }
        int i3 = i2 + 1;
        if (this.xref[i3] > 0) {
            j = this.objStmToOffset.get(this.xref[i3]);
        }
        if (j == 0) {
            return null;
        }
        this.tokens.seek(j);
        this.tokens.nextValidToken();
        if (this.tokens.getTokenType() != PRTokeniser.TokenType.NUMBER) {
            this.tokens.throwError(MessageLocalization.getComposedMessage("invalid.object.number", new Object[0]));
        }
        this.objNum = this.tokens.intValue();
        this.tokens.nextValidToken();
        if (this.tokens.getTokenType() != PRTokeniser.TokenType.NUMBER) {
            this.tokens.throwError(MessageLocalization.getComposedMessage("invalid.generation.number", new Object[0]));
        }
        this.objGen = this.tokens.intValue();
        this.tokens.nextValidToken();
        if (!this.tokens.getStringValue().equals("obj")) {
            this.tokens.throwError(MessageLocalization.getComposedMessage("token.obj.expected", new Object[0]));
        }
        try {
            pdfObject = readPRObject();
            for (int i4 = 0; i4 < this.strings.size(); i4++) {
                this.strings.get(i4).decrypt(this);
            }
            if (pdfObject.isStream()) {
                checkPRStreamLength((PRStream) pdfObject);
            }
        } catch (IOException e) {
            if (debugmode) {
                if (LOGGER.isLogging(Level.ERROR)) {
                    LOGGER.error(e.getMessage(), e);
                }
                pdfObject = null;
            } else {
                throw e;
            }
        }
        if (this.xref[i3] > 0) {
            pdfObject = readOneObjStm((PRStream) pdfObject, (int) this.xref[i2]);
        }
        this.xrefObj.set(i, pdfObject);
        return pdfObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0046, code lost:
        r4 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.pdf.PdfObject readOneObjStm(com.itextpdf.text.pdf.PRStream r8, int r9) throws java.io.IOException {
        /*
            r7 = this;
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.FIRST
            com.itextpdf.text.pdf.PdfNumber r0 = r8.getAsNumber(r0)
            int r0 = r0.intValue()
            com.itextpdf.text.pdf.PRTokeniser r1 = r7.tokens
            com.itextpdf.text.pdf.RandomAccessFileOrArray r1 = r1.getFile()
            byte[] r8 = getStreamBytes(r8, r1)
            com.itextpdf.text.pdf.PRTokeniser r1 = r7.tokens
            com.itextpdf.text.pdf.PRTokeniser r2 = new com.itextpdf.text.pdf.PRTokeniser
            com.itextpdf.text.pdf.RandomAccessFileOrArray r3 = new com.itextpdf.text.pdf.RandomAccessFileOrArray
            com.itextpdf.text.io.RandomAccessSourceFactory r4 = new com.itextpdf.text.io.RandomAccessSourceFactory
            r4.<init>()
            com.itextpdf.text.io.RandomAccessSource r8 = r4.createSource((byte[]) r8)
            r3.<init>((com.itextpdf.text.io.RandomAccessSource) r8)
            r2.<init>(r3)
            r7.tokens = r2
            r8 = 1
            int r9 = r9 + r8
            r2 = 0
            r4 = r8
            r8 = r2
            r3 = r8
        L_0x0031:
            if (r8 >= r9) goto L_0x0068
            com.itextpdf.text.pdf.PRTokeniser r4 = r7.tokens     // Catch:{ all -> 0x0066 }
            boolean r4 = r4.nextToken()     // Catch:{ all -> 0x0066 }
            if (r4 != 0) goto L_0x003c
            goto L_0x0068
        L_0x003c:
            com.itextpdf.text.pdf.PRTokeniser r4 = r7.tokens     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r4 = r4.getTokenType()     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r5 = com.itextpdf.text.pdf.PRTokeniser.TokenType.NUMBER     // Catch:{ all -> 0x0066 }
            if (r4 == r5) goto L_0x0048
        L_0x0046:
            r4 = r2
            goto L_0x0068
        L_0x0048:
            com.itextpdf.text.pdf.PRTokeniser r4 = r7.tokens     // Catch:{ all -> 0x0066 }
            boolean r4 = r4.nextToken()     // Catch:{ all -> 0x0066 }
            if (r4 != 0) goto L_0x0051
            goto L_0x0068
        L_0x0051:
            com.itextpdf.text.pdf.PRTokeniser r5 = r7.tokens     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r5 = r5.getTokenType()     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r6 = com.itextpdf.text.pdf.PRTokeniser.TokenType.NUMBER     // Catch:{ all -> 0x0066 }
            if (r5 == r6) goto L_0x005c
            goto L_0x0046
        L_0x005c:
            com.itextpdf.text.pdf.PRTokeniser r3 = r7.tokens     // Catch:{ all -> 0x0066 }
            int r3 = r3.intValue()     // Catch:{ all -> 0x0066 }
            int r3 = r3 + r0
            int r8 = r8 + 1
            goto L_0x0031
        L_0x0066:
            r8 = move-exception
            goto L_0x00a5
        L_0x0068:
            if (r4 != 0) goto L_0x0078
            com.itextpdf.text.exceptions.InvalidPdfException r8 = new com.itextpdf.text.exceptions.InvalidPdfException     // Catch:{ all -> 0x0066 }
            java.lang.String r9 = "error.reading.objstm"
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ all -> 0x0066 }
            java.lang.String r9 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r9, (java.lang.Object[]) r0)     // Catch:{ all -> 0x0066 }
            r8.<init>(r9)     // Catch:{ all -> 0x0066 }
            throw r8     // Catch:{ all -> 0x0066 }
        L_0x0078:
            com.itextpdf.text.pdf.PRTokeniser r8 = r7.tokens     // Catch:{ all -> 0x0066 }
            long r2 = (long) r3     // Catch:{ all -> 0x0066 }
            r8.seek(r2)     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PRTokeniser r8 = r7.tokens     // Catch:{ all -> 0x0066 }
            r8.nextToken()     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PRTokeniser r8 = r7.tokens     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r8 = r8.getTokenType()     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r9 = com.itextpdf.text.pdf.PRTokeniser.TokenType.NUMBER     // Catch:{ all -> 0x0066 }
            if (r8 != r9) goto L_0x0099
            com.itextpdf.text.pdf.PdfNumber r8 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PRTokeniser r9 = r7.tokens     // Catch:{ all -> 0x0066 }
            java.lang.String r9 = r9.getStringValue()     // Catch:{ all -> 0x0066 }
            r8.<init>((java.lang.String) r9)     // Catch:{ all -> 0x0066 }
            goto L_0x00a2
        L_0x0099:
            com.itextpdf.text.pdf.PRTokeniser r8 = r7.tokens     // Catch:{ all -> 0x0066 }
            r8.seek(r2)     // Catch:{ all -> 0x0066 }
            com.itextpdf.text.pdf.PdfObject r8 = r7.readPRObject()     // Catch:{ all -> 0x0066 }
        L_0x00a2:
            r7.tokens = r1
            return r8
        L_0x00a5:
            r7.tokens = r1
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfReader.readOneObjStm(com.itextpdf.text.pdf.PRStream, int):com.itextpdf.text.pdf.PdfObject");
    }

    public double dumpPerc() {
        int i = 0;
        for (int i2 = 0; i2 < this.xrefObj.size(); i2++) {
            if (this.xrefObj.get(i2) != null) {
                i++;
            }
        }
        return (((double) i) * 100.0d) / ((double) this.xrefObj.size());
    }

    /* access modifiers changed from: protected */
    public void readDocObj() throws IOException {
        PdfObject pdfObject;
        ArrayList arrayList = new ArrayList();
        int i = 2;
        this.xrefObj = new ArrayList<>(this.xref.length / 2);
        this.xrefObj.addAll(Collections.nCopies(this.xref.length / 2, (Object) null));
        while (true) {
            if (i < this.xref.length) {
                long j = this.xref[i];
                if (j > 0 && this.xref[i + 1] <= 0) {
                    this.tokens.seek(j);
                    this.tokens.nextValidToken();
                    if (this.tokens.getTokenType() != PRTokeniser.TokenType.NUMBER) {
                        this.tokens.throwError(MessageLocalization.getComposedMessage("invalid.object.number", new Object[0]));
                    }
                    this.objNum = this.tokens.intValue();
                    this.tokens.nextValidToken();
                    if (this.tokens.getTokenType() != PRTokeniser.TokenType.NUMBER) {
                        this.tokens.throwError(MessageLocalization.getComposedMessage("invalid.generation.number", new Object[0]));
                    }
                    this.objGen = this.tokens.intValue();
                    this.tokens.nextValidToken();
                    if (!this.tokens.getStringValue().equals("obj")) {
                        this.tokens.throwError(MessageLocalization.getComposedMessage("token.obj.expected", new Object[0]));
                    }
                    try {
                        pdfObject = readPRObject();
                        if (pdfObject.isStream()) {
                            arrayList.add((PRStream) pdfObject);
                        }
                    } catch (IOException e) {
                        if (debugmode) {
                            if (LOGGER.isLogging(Level.ERROR)) {
                                LOGGER.error(e.getMessage(), e);
                            }
                            pdfObject = null;
                        } else {
                            throw e;
                        }
                    }
                    this.xrefObj.set(i / 2, pdfObject);
                }
                i += 2;
            } else {
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    checkPRStreamLength((PRStream) arrayList.get(i2));
                }
                readDecryptedDocObj();
                if (this.objStmMark != null) {
                    for (Map.Entry next : this.objStmMark.entrySet()) {
                        int intValue = ((Integer) next.getKey()).intValue();
                        readObjStm((PRStream) this.xrefObj.get(intValue), (IntHashtable) next.getValue());
                        this.xrefObj.set(intValue, (Object) null);
                    }
                    this.objStmMark = null;
                }
                this.xref = null;
                return;
            }
        }
    }

    private void checkPRStreamLength(PRStream pRStream) throws IOException {
        long j;
        long j2;
        long filePointer;
        PRStream pRStream2 = pRStream;
        long length = this.tokens.length();
        long offset = pRStream.getOffset();
        PdfObject pdfObjectRelease = getPdfObjectRelease(pRStream2.get(PdfName.LENGTH));
        boolean z = true;
        if (pdfObjectRelease == null || pdfObjectRelease.type() != 2) {
            j = 0;
        } else {
            j = (long) ((PdfNumber) pdfObjectRelease).intValue();
            long j3 = j + offset;
            if (j3 <= length - 20) {
                this.tokens.seek(j3);
                String readString = this.tokens.readString(20);
                if (readString.startsWith("\nendstream") || readString.startsWith("\r\nendstream") || readString.startsWith("\rendstream") || readString.startsWith("endstream")) {
                    z = false;
                }
            }
        }
        if (z) {
            byte[] bArr = new byte[16];
            this.tokens.seek(offset);
            while (true) {
                filePointer = this.tokens.getFilePointer();
                if (!this.tokens.readLineSegment(bArr, false)) {
                    break;
                } else if (equalsn(bArr, endstream)) {
                    j = filePointer - offset;
                    break;
                } else if (equalsn(bArr, endobj)) {
                    long j4 = filePointer - 16;
                    this.tokens.seek(j4);
                    int indexOf = this.tokens.readString(16).indexOf("endstream");
                    if (indexOf >= 0) {
                        filePointer = j4 + ((long) indexOf);
                    }
                    j = filePointer - offset;
                }
            }
            this.tokens.seek(filePointer - 2);
            j2 = this.tokens.read() == 13 ? j - 1 : j;
            this.tokens.seek(filePointer - 1);
            if (this.tokens.read() == 10) {
                j2--;
            }
            if (j2 < 0) {
                j2 = 0;
            }
        } else {
            j2 = j;
        }
        pRStream2.setLength((int) j2);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0055, code lost:
        r6 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readObjStm(com.itextpdf.text.pdf.PRStream r10, com.itextpdf.text.pdf.IntHashtable r11) throws java.io.IOException {
        /*
            r9 = this;
            if (r10 != 0) goto L_0x0003
            return
        L_0x0003:
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.FIRST
            com.itextpdf.text.pdf.PdfNumber r0 = r10.getAsNumber(r0)
            int r0 = r0.intValue()
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.N
            com.itextpdf.text.pdf.PdfNumber r1 = r10.getAsNumber(r1)
            int r1 = r1.intValue()
            com.itextpdf.text.pdf.PRTokeniser r2 = r9.tokens
            com.itextpdf.text.pdf.RandomAccessFileOrArray r2 = r2.getFile()
            byte[] r10 = getStreamBytes(r10, r2)
            com.itextpdf.text.pdf.PRTokeniser r2 = r9.tokens
            com.itextpdf.text.pdf.PRTokeniser r3 = new com.itextpdf.text.pdf.PRTokeniser
            com.itextpdf.text.pdf.RandomAccessFileOrArray r4 = new com.itextpdf.text.pdf.RandomAccessFileOrArray
            com.itextpdf.text.io.RandomAccessSourceFactory r5 = new com.itextpdf.text.io.RandomAccessSourceFactory
            r5.<init>()
            com.itextpdf.text.io.RandomAccessSource r10 = r5.createSource((byte[]) r10)
            r4.<init>((com.itextpdf.text.io.RandomAccessSource) r10)
            r3.<init>(r4)
            r9.tokens = r3
            int[] r10 = new int[r1]     // Catch:{ all -> 0x00d3 }
            int[] r3 = new int[r1]     // Catch:{ all -> 0x00d3 }
            r4 = 1
            r5 = 0
            r6 = r4
            r4 = r5
        L_0x0040:
            if (r4 >= r1) goto L_0x007f
            com.itextpdf.text.pdf.PRTokeniser r6 = r9.tokens     // Catch:{ all -> 0x00d3 }
            boolean r6 = r6.nextToken()     // Catch:{ all -> 0x00d3 }
            if (r6 != 0) goto L_0x004b
            goto L_0x007f
        L_0x004b:
            com.itextpdf.text.pdf.PRTokeniser r6 = r9.tokens     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r6 = r6.getTokenType()     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r7 = com.itextpdf.text.pdf.PRTokeniser.TokenType.NUMBER     // Catch:{ all -> 0x00d3 }
            if (r6 == r7) goto L_0x0057
        L_0x0055:
            r6 = r5
            goto L_0x007f
        L_0x0057:
            com.itextpdf.text.pdf.PRTokeniser r6 = r9.tokens     // Catch:{ all -> 0x00d3 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x00d3 }
            r3[r4] = r6     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser r6 = r9.tokens     // Catch:{ all -> 0x00d3 }
            boolean r6 = r6.nextToken()     // Catch:{ all -> 0x00d3 }
            if (r6 != 0) goto L_0x0068
            goto L_0x007f
        L_0x0068:
            com.itextpdf.text.pdf.PRTokeniser r7 = r9.tokens     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r7 = r7.getTokenType()     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r8 = com.itextpdf.text.pdf.PRTokeniser.TokenType.NUMBER     // Catch:{ all -> 0x00d3 }
            if (r7 == r8) goto L_0x0073
            goto L_0x0055
        L_0x0073:
            com.itextpdf.text.pdf.PRTokeniser r7 = r9.tokens     // Catch:{ all -> 0x00d3 }
            int r7 = r7.intValue()     // Catch:{ all -> 0x00d3 }
            int r7 = r7 + r0
            r10[r4] = r7     // Catch:{ all -> 0x00d3 }
            int r4 = r4 + 1
            goto L_0x0040
        L_0x007f:
            if (r6 != 0) goto L_0x008f
            com.itextpdf.text.exceptions.InvalidPdfException r10 = new com.itextpdf.text.exceptions.InvalidPdfException     // Catch:{ all -> 0x00d3 }
            java.lang.String r11 = "error.reading.objstm"
            java.lang.Object[] r0 = new java.lang.Object[r5]     // Catch:{ all -> 0x00d3 }
            java.lang.String r11 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r11, (java.lang.Object[]) r0)     // Catch:{ all -> 0x00d3 }
            r10.<init>(r11)     // Catch:{ all -> 0x00d3 }
            throw r10     // Catch:{ all -> 0x00d3 }
        L_0x008f:
            if (r5 >= r1) goto L_0x00d0
            boolean r0 = r11.containsKey(r5)     // Catch:{ all -> 0x00d3 }
            if (r0 == 0) goto L_0x00cd
            com.itextpdf.text.pdf.PRTokeniser r0 = r9.tokens     // Catch:{ all -> 0x00d3 }
            r4 = r10[r5]     // Catch:{ all -> 0x00d3 }
            long r6 = (long) r4     // Catch:{ all -> 0x00d3 }
            r0.seek(r6)     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser r0 = r9.tokens     // Catch:{ all -> 0x00d3 }
            r0.nextToken()     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser r0 = r9.tokens     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = r0.getTokenType()     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser$TokenType r4 = com.itextpdf.text.pdf.PRTokeniser.TokenType.NUMBER     // Catch:{ all -> 0x00d3 }
            if (r0 != r4) goto L_0x00ba
            com.itextpdf.text.pdf.PdfNumber r0 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PRTokeniser r4 = r9.tokens     // Catch:{ all -> 0x00d3 }
            java.lang.String r4 = r4.getStringValue()     // Catch:{ all -> 0x00d3 }
            r0.<init>((java.lang.String) r4)     // Catch:{ all -> 0x00d3 }
            goto L_0x00c6
        L_0x00ba:
            com.itextpdf.text.pdf.PRTokeniser r0 = r9.tokens     // Catch:{ all -> 0x00d3 }
            r4 = r10[r5]     // Catch:{ all -> 0x00d3 }
            long r6 = (long) r4     // Catch:{ all -> 0x00d3 }
            r0.seek(r6)     // Catch:{ all -> 0x00d3 }
            com.itextpdf.text.pdf.PdfObject r0 = r9.readPRObject()     // Catch:{ all -> 0x00d3 }
        L_0x00c6:
            java.util.ArrayList<com.itextpdf.text.pdf.PdfObject> r4 = r9.xrefObj     // Catch:{ all -> 0x00d3 }
            r6 = r3[r5]     // Catch:{ all -> 0x00d3 }
            r4.set(r6, r0)     // Catch:{ all -> 0x00d3 }
        L_0x00cd:
            int r5 = r5 + 1
            goto L_0x008f
        L_0x00d0:
            r9.tokens = r2
            return
        L_0x00d3:
            r10 = move-exception
            r9.tokens = r2
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfReader.readObjStm(com.itextpdf.text.pdf.PRStream, com.itextpdf.text.pdf.IntHashtable):void");
    }

    public static PdfObject killIndirect(PdfObject pdfObject) {
        if (pdfObject == null || pdfObject.isNull()) {
            return null;
        }
        PdfObject pdfObjectRelease = getPdfObjectRelease(pdfObject);
        if (pdfObject.isIndirect()) {
            PRIndirectReference pRIndirectReference = (PRIndirectReference) pdfObject;
            PdfReader reader = pRIndirectReference.getReader();
            int number = pRIndirectReference.getNumber();
            reader.xrefObj.set(number, (Object) null);
            if (reader.partial) {
                reader.xref[number * 2] = -1;
            }
        }
        return pdfObjectRelease;
    }

    private void ensureXrefSize(int i) {
        if (i != 0) {
            if (this.xref == null) {
                this.xref = new long[i];
            } else if (this.xref.length < i) {
                long[] jArr = new long[i];
                System.arraycopy(this.xref, 0, jArr, 0, this.xref.length);
                this.xref = jArr;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void readXref() throws IOException {
        this.hybridXref = false;
        this.newXrefType = false;
        this.tokens.seek(this.tokens.getStartxref());
        this.tokens.nextToken();
        if (!this.tokens.getStringValue().equals("startxref")) {
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("startxref.not.found", new Object[0]));
        }
        this.tokens.nextToken();
        if (this.tokens.getTokenType() != PRTokeniser.TokenType.NUMBER) {
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("startxref.is.not.followed.by.a.number", new Object[0]));
        }
        long longValue = this.tokens.longValue();
        this.lastXref = longValue;
        this.eofPos = this.tokens.getFilePointer();
        try {
            if (readXRefStream(longValue)) {
                this.newXrefType = true;
                return;
            }
        } catch (Exception unused) {
        }
        this.xref = null;
        this.tokens.seek(longValue);
        this.trailer = readXrefSection();
        PdfDictionary pdfDictionary = this.trailer;
        while (true) {
            PdfNumber pdfNumber = (PdfNumber) pdfDictionary.get(PdfName.PREV);
            if (pdfNumber != null) {
                if (pdfNumber.longValue() == longValue) {
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("trailer.prev.entry.points.to.its.own.cross.reference.section", new Object[0]));
                }
                longValue = pdfNumber.longValue();
                this.tokens.seek(longValue);
                pdfDictionary = readXrefSection();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public PdfDictionary readXrefSection() throws IOException {
        this.tokens.nextValidToken();
        if (!this.tokens.getStringValue().equals("xref")) {
            this.tokens.throwError(MessageLocalization.getComposedMessage("xref.subsection.not.found", new Object[0]));
        }
        while (true) {
            this.tokens.nextValidToken();
            if (this.tokens.getStringValue().equals("trailer")) {
                break;
            }
            if (this.tokens.getTokenType() != PRTokeniser.TokenType.NUMBER) {
                this.tokens.throwError(MessageLocalization.getComposedMessage("object.number.of.the.first.object.in.this.xref.subsection.not.found", new Object[0]));
            }
            int intValue = this.tokens.intValue();
            this.tokens.nextValidToken();
            if (this.tokens.getTokenType() != PRTokeniser.TokenType.NUMBER) {
                this.tokens.throwError(MessageLocalization.getComposedMessage("number.of.entries.in.this.xref.subsection.not.found", new Object[0]));
            }
            int intValue2 = this.tokens.intValue() + intValue;
            if (intValue == 1) {
                long filePointer = this.tokens.getFilePointer();
                this.tokens.nextValidToken();
                long longValue = this.tokens.longValue();
                this.tokens.nextValidToken();
                int intValue3 = this.tokens.intValue();
                if (longValue == 0 && intValue3 == 65535) {
                    intValue--;
                    intValue2--;
                }
                this.tokens.seek(filePointer);
            }
            ensureXrefSize(intValue2 * 2);
            while (intValue < intValue2) {
                this.tokens.nextValidToken();
                long longValue2 = this.tokens.longValue();
                this.tokens.nextValidToken();
                this.tokens.intValue();
                this.tokens.nextValidToken();
                int i = intValue * 2;
                if (this.tokens.getStringValue().equals("n")) {
                    if (this.xref[i] == 0 && this.xref[i + 1] == 0) {
                        this.xref[i] = longValue2;
                    }
                } else if (!this.tokens.getStringValue().equals("f")) {
                    this.tokens.throwError(MessageLocalization.getComposedMessage("invalid.cross.reference.entry.in.this.xref.subsection", new Object[0]));
                } else if (this.xref[i] == 0 && this.xref[i + 1] == 0) {
                    this.xref[i] = -1;
                }
                intValue++;
            }
        }
        PdfDictionary pdfDictionary = (PdfDictionary) readPRObject();
        ensureXrefSize(((PdfNumber) pdfDictionary.get(PdfName.SIZE)).intValue() * 2);
        PdfObject pdfObject = pdfDictionary.get(PdfName.XREFSTM);
        if (pdfObject != null && pdfObject.isNumber()) {
            try {
                readXRefStream((long) ((PdfNumber) pdfObject).intValue());
                this.newXrefType = true;
                this.hybridXref = true;
            } catch (IOException e) {
                this.xref = null;
                throw e;
            }
        }
        return pdfDictionary;
    }

    /* access modifiers changed from: protected */
    public boolean readXRefStream(long j) throws IOException {
        PdfArray pdfArray;
        long j2;
        int i;
        int i2;
        this.tokens.seek(j);
        int i3 = 0;
        if (!this.tokens.nextToken() || this.tokens.getTokenType() != PRTokeniser.TokenType.NUMBER) {
            return false;
        }
        int intValue = this.tokens.intValue();
        if (!this.tokens.nextToken() || this.tokens.getTokenType() != PRTokeniser.TokenType.NUMBER) {
            return false;
        }
        if (!this.tokens.nextToken() || !this.tokens.getStringValue().equals("obj")) {
            return false;
        }
        PdfObject readPRObject = readPRObject();
        if (!readPRObject.isStream()) {
            return false;
        }
        PRStream pRStream = (PRStream) readPRObject;
        if (!PdfName.XREF.equals(pRStream.get(PdfName.TYPE))) {
            return false;
        }
        if (this.trailer == null) {
            this.trailer = new PdfDictionary();
            this.trailer.putAll(pRStream);
        }
        pRStream.setLength(((PdfNumber) pRStream.get(PdfName.LENGTH)).intValue());
        int intValue2 = ((PdfNumber) pRStream.get(PdfName.SIZE)).intValue();
        PdfObject pdfObject = pRStream.get(PdfName.INDEX);
        int i4 = 2;
        int i5 = 1;
        if (pdfObject == null) {
            pdfArray = new PdfArray();
            pdfArray.add(new int[]{0, intValue2});
        } else {
            pdfArray = (PdfArray) pdfObject;
        }
        PdfArray pdfArray2 = (PdfArray) pRStream.get(PdfName.W);
        PdfObject pdfObject2 = pRStream.get(PdfName.PREV);
        long longValue = pdfObject2 != null ? ((PdfNumber) pdfObject2).longValue() : -1;
        ensureXrefSize(intValue2 * 2);
        if (this.objStmMark == null && !this.partial) {
            this.objStmMark = new HashMap<>();
        }
        if (this.objStmToOffset == null && this.partial) {
            this.objStmToOffset = new LongHashtable();
        }
        byte[] streamBytes = getStreamBytes(pRStream, this.tokens.getFile());
        int[] iArr = new int[3];
        for (int i6 = 0; i6 < 3; i6++) {
            iArr[i6] = pdfArray2.getAsNumber(i6).intValue();
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < pdfArray.size()) {
            int intValue3 = pdfArray.getAsNumber(i7).intValue();
            int intValue4 = pdfArray.getAsNumber(i7 + 1).intValue();
            ensureXrefSize((intValue3 + intValue4) * 2);
            while (true) {
                int i9 = intValue4 - 1;
                if (intValue4 > 0) {
                    if (iArr[i3] > 0) {
                        i2 = i3;
                        i = i8;
                        int i10 = i2;
                        while (i10 < iArr[i3]) {
                            int i11 = (i2 << 8) + (streamBytes[i] & 255);
                            i10++;
                            i++;
                            i2 = i11;
                        }
                    } else {
                        i2 = i5;
                        i = i8;
                    }
                    int i12 = i3;
                    byte[] bArr = streamBytes;
                    long j3 = 0;
                    while (i12 < iArr[i5]) {
                        i12++;
                        i++;
                        j3 = (j3 << 8) + ((long) (bArr[i] & 255));
                        i5 = 1;
                    }
                    PdfArray pdfArray3 = pdfArray;
                    int i13 = i;
                    int i14 = 0;
                    int i15 = 0;
                    char c = 2;
                    while (i14 < iArr[c]) {
                        int i16 = (i15 << 8) + (bArr[i13] & 255);
                        i14++;
                        i13++;
                        c = 2;
                        i15 = i16;
                    }
                    int i17 = intValue3 * 2;
                    int i18 = i13;
                    int[] iArr2 = iArr;
                    if (this.xref[i17] == 0) {
                        int i19 = i17 + 1;
                        if (this.xref[i19] == 0) {
                            switch (i2) {
                                case 0:
                                    this.xref[i17] = -1;
                                    break;
                                case 1:
                                    this.xref[i17] = j3;
                                    break;
                                case 2:
                                    this.xref[i17] = (long) i15;
                                    this.xref[i19] = j3;
                                    if (!this.partial) {
                                        Integer valueOf = Integer.valueOf((int) j3);
                                        IntHashtable intHashtable = this.objStmMark.get(valueOf);
                                        if (intHashtable != null) {
                                            intHashtable.put(i15, 1);
                                            break;
                                        } else {
                                            IntHashtable intHashtable2 = new IntHashtable();
                                            intHashtable2.put(i15, 1);
                                            this.objStmMark.put(valueOf, intHashtable2);
                                            break;
                                        }
                                    } else {
                                        this.objStmToOffset.put(j3, 0);
                                        break;
                                    }
                            }
                        }
                    }
                    intValue3++;
                    intValue4 = i9;
                    streamBytes = bArr;
                    pdfArray = pdfArray3;
                    iArr = iArr2;
                    i8 = i18;
                    i3 = 0;
                    i5 = 1;
                } else {
                    byte[] bArr2 = streamBytes;
                    PdfArray pdfArray4 = pdfArray;
                    int[] iArr3 = iArr;
                    i7 += 2;
                    i3 = 0;
                    i4 = 2;
                    i5 = 1;
                }
            }
        }
        int i20 = intValue * i4;
        int i21 = i20 + 1;
        if (i21 < this.xref.length && this.xref[i20] == 0 && this.xref[i21] == 0) {
            j2 = -1;
            this.xref[i20] = -1;
        } else {
            j2 = -1;
        }
        if (longValue == j2) {
            return true;
        }
        return readXRefStream(longValue);
    }

    /* access modifiers changed from: protected */
    public void rebuildXref() throws IOException {
        int i;
        int i2 = 0;
        this.hybridXref = false;
        this.newXrefType = false;
        long j = 0;
        this.tokens.seek(0);
        long[][] jArr = new long[1024][];
        String str = null;
        this.trailer = null;
        byte[] bArr = new byte[64];
        while (true) {
            long filePointer = this.tokens.getFilePointer();
            if (!this.tokens.readLineSegment(bArr, true)) {
                break;
            }
            if (bArr[i2] == 116) {
                if (PdfEncodings.convertToString(bArr, str).startsWith("trailer")) {
                    this.tokens.seek(filePointer);
                    this.tokens.nextToken();
                    long filePointer2 = this.tokens.getFilePointer();
                    try {
                        PdfDictionary pdfDictionary = (PdfDictionary) readPRObject();
                        if (pdfDictionary.get(PdfName.ROOT) != null) {
                            this.trailer = pdfDictionary;
                        } else {
                            this.tokens.seek(filePointer2);
                        }
                    } catch (Exception unused) {
                        this.tokens.seek(filePointer2);
                    }
                }
            } else if (bArr[i2] >= 48 && bArr[i2] <= 57) {
                long[] checkObjectStart = PRTokeniser.checkObjectStart(bArr);
                if (checkObjectStart != null) {
                    long j2 = checkObjectStart[i2];
                    long j3 = checkObjectStart[1];
                    long[][] jArr2 = jArr;
                    if (j2 >= ((long) jArr.length)) {
                        jArr = new long[((int) (2 * j2))][];
                        System.arraycopy(jArr2, 0, jArr, 0, (int) j);
                    } else {
                        jArr = jArr2;
                    }
                    if (j2 >= j) {
                        j = j2 + 1;
                    }
                    int i3 = (int) j2;
                    if (jArr[i3] == null || j3 >= jArr[i3][1]) {
                        i = 0;
                        checkObjectStart[0] = filePointer;
                        jArr[i3] = checkObjectStart;
                    } else {
                        i = 0;
                    }
                    i2 = i;
                    str = null;
                }
            }
            i = i2;
            jArr = jArr;
            i2 = i;
            str = null;
        }
        if (this.trailer == null) {
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("trailer.not.found", new Object[i2]));
        }
        this.xref = new long[((int) (2 * j))];
        for (int i4 = i2; ((long) i4) < j; i4++) {
            long[] jArr3 = jArr[i4];
            if (jArr3 != null) {
                this.xref[i4 * 2] = jArr3[i2];
            }
        }
    }

    /* access modifiers changed from: protected */
    public PdfDictionary readDictionary() throws IOException {
        PdfDictionary pdfDictionary = new PdfDictionary();
        while (true) {
            this.tokens.nextValidToken();
            if (this.tokens.getTokenType() == PRTokeniser.TokenType.END_DIC) {
                return pdfDictionary;
            }
            if (this.tokens.getTokenType() != PRTokeniser.TokenType.NAME) {
                this.tokens.throwError(MessageLocalization.getComposedMessage("dictionary.key.1.is.not.a.name", this.tokens.getStringValue()));
            }
            PdfName pdfName = new PdfName(this.tokens.getStringValue(), false);
            PdfObject readPRObject = readPRObject();
            int i = -readPRObject.type();
            if (i == PRTokeniser.TokenType.END_DIC.ordinal()) {
                this.tokens.throwError(MessageLocalization.getComposedMessage("unexpected.gt.gt", new Object[0]));
            }
            if (i == PRTokeniser.TokenType.END_ARRAY.ordinal()) {
                this.tokens.throwError(MessageLocalization.getComposedMessage("unexpected.close.bracket", new Object[0]));
            }
            pdfDictionary.put(pdfName, readPRObject);
        }
    }

    /* access modifiers changed from: protected */
    public PdfArray readArray() throws IOException {
        PdfArray pdfArray = new PdfArray();
        while (true) {
            PdfObject readPRObject = readPRObject();
            int i = -readPRObject.type();
            if (i == PRTokeniser.TokenType.END_ARRAY.ordinal()) {
                return pdfArray;
            }
            if (i == PRTokeniser.TokenType.END_DIC.ordinal()) {
                this.tokens.throwError(MessageLocalization.getComposedMessage("unexpected.gt.gt", new Object[0]));
            }
            pdfArray.add(readPRObject);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e9 A[LOOP:1: B:34:0x00e9->B:72:0x00e9, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0124  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.pdf.PdfObject readPRObject() throws java.io.IOException {
        /*
            r6 = this;
            com.itextpdf.text.pdf.PRTokeniser r0 = r6.tokens
            r0.nextValidToken()
            com.itextpdf.text.pdf.PRTokeniser r0 = r6.tokens
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = r0.getTokenType()
            int[] r1 = com.itextpdf.text.pdf.PdfReader.AnonymousClass1.$SwitchMap$com$itextpdf$text$pdf$PRTokeniser$TokenType
            int r2 = r0.ordinal()
            r1 = r1[r2]
            r2 = 0
            r3 = 1
            switch(r1) {
                case 1: goto L_0x00b3;
                case 2: goto L_0x00a4;
                case 3: goto L_0x0098;
                case 4: goto L_0x0071;
                case 5: goto L_0x0050;
                case 6: goto L_0x003e;
                case 7: goto L_0x0030;
                default: goto L_0x0018;
            }
        L_0x0018:
            com.itextpdf.text.pdf.PRTokeniser r1 = r6.tokens
            java.lang.String r1 = r1.getStringValue()
            java.lang.String r4 = "null"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x012d
            int r0 = r6.readDepth
            if (r0 != 0) goto L_0x012a
            com.itextpdf.text.pdf.PdfNull r0 = new com.itextpdf.text.pdf.PdfNull
            r0.<init>()
            return r0
        L_0x0030:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "unexpected.end.of.file"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r1 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r1, (java.lang.Object[]) r2)
            r0.<init>(r1)
            throw r0
        L_0x003e:
            com.itextpdf.text.pdf.PRTokeniser r0 = r6.tokens
            int r0 = r0.getReference()
            com.itextpdf.text.pdf.PRIndirectReference r1 = new com.itextpdf.text.pdf.PRIndirectReference
            com.itextpdf.text.pdf.PRTokeniser r2 = r6.tokens
            int r2 = r2.getGeneration()
            r1.<init>(r6, r0, r2)
            return r1
        L_0x0050:
            java.util.Map<java.lang.String, com.itextpdf.text.pdf.PdfName> r0 = com.itextpdf.text.pdf.PdfName.staticNames
            com.itextpdf.text.pdf.PRTokeniser r1 = r6.tokens
            java.lang.String r1 = r1.getStringValue()
            java.lang.Object r0 = r0.get(r1)
            com.itextpdf.text.pdf.PdfName r0 = (com.itextpdf.text.pdf.PdfName) r0
            int r1 = r6.readDepth
            if (r1 <= 0) goto L_0x0065
            if (r0 == 0) goto L_0x0065
            return r0
        L_0x0065:
            com.itextpdf.text.pdf.PdfName r0 = new com.itextpdf.text.pdf.PdfName
            com.itextpdf.text.pdf.PRTokeniser r1 = r6.tokens
            java.lang.String r1 = r1.getStringValue()
            r0.<init>(r1, r2)
            return r0
        L_0x0071:
            com.itextpdf.text.pdf.PdfString r0 = new com.itextpdf.text.pdf.PdfString
            com.itextpdf.text.pdf.PRTokeniser r1 = r6.tokens
            java.lang.String r1 = r1.getStringValue()
            r2 = 0
            r0.<init>(r1, r2)
            com.itextpdf.text.pdf.PRTokeniser r1 = r6.tokens
            boolean r1 = r1.isHexString()
            com.itextpdf.text.pdf.PdfString r0 = r0.setHexWriting(r1)
            int r1 = r6.objNum
            int r2 = r6.objGen
            r0.setObjNum(r1, r2)
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r1 = r6.strings
            if (r1 == 0) goto L_0x0097
            java.util.ArrayList<com.itextpdf.text.pdf.PdfString> r1 = r6.strings
            r1.add(r0)
        L_0x0097:
            return r0
        L_0x0098:
            com.itextpdf.text.pdf.PdfNumber r0 = new com.itextpdf.text.pdf.PdfNumber
            com.itextpdf.text.pdf.PRTokeniser r1 = r6.tokens
            java.lang.String r1 = r1.getStringValue()
            r0.<init>((java.lang.String) r1)
            return r0
        L_0x00a4:
            int r0 = r6.readDepth
            int r0 = r0 + r3
            r6.readDepth = r0
            com.itextpdf.text.pdf.PdfArray r0 = r6.readArray()
            int r1 = r6.readDepth
            int r1 = r1 - r3
            r6.readDepth = r1
            return r0
        L_0x00b3:
            int r0 = r6.readDepth
            int r0 = r0 + r3
            r6.readDepth = r0
            com.itextpdf.text.pdf.PdfDictionary r0 = r6.readDictionary()
            int r1 = r6.readDepth
            int r1 = r1 - r3
            r6.readDepth = r1
            com.itextpdf.text.pdf.PRTokeniser r1 = r6.tokens
            long r1 = r1.getFilePointer()
        L_0x00c7:
            com.itextpdf.text.pdf.PRTokeniser r3 = r6.tokens
            boolean r3 = r3.nextToken()
            if (r3 == 0) goto L_0x00d9
            com.itextpdf.text.pdf.PRTokeniser r4 = r6.tokens
            com.itextpdf.text.pdf.PRTokeniser$TokenType r4 = r4.getTokenType()
            com.itextpdf.text.pdf.PRTokeniser$TokenType r5 = com.itextpdf.text.pdf.PRTokeniser.TokenType.COMMENT
            if (r4 == r5) goto L_0x00c7
        L_0x00d9:
            if (r3 == 0) goto L_0x0124
            com.itextpdf.text.pdf.PRTokeniser r3 = r6.tokens
            java.lang.String r3 = r3.getStringValue()
            java.lang.String r4 = "stream"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0124
        L_0x00e9:
            com.itextpdf.text.pdf.PRTokeniser r1 = r6.tokens
            int r1 = r1.read()
            r2 = 32
            if (r1 == r2) goto L_0x00e9
            r2 = 9
            if (r1 == r2) goto L_0x00e9
            if (r1 == 0) goto L_0x00e9
            r2 = 12
            if (r1 == r2) goto L_0x00e9
            r2 = 10
            if (r1 == r2) goto L_0x0107
            com.itextpdf.text.pdf.PRTokeniser r1 = r6.tokens
            int r1 = r1.read()
        L_0x0107:
            if (r1 == r2) goto L_0x010e
            com.itextpdf.text.pdf.PRTokeniser r2 = r6.tokens
            r2.backOnePosition(r1)
        L_0x010e:
            com.itextpdf.text.pdf.PRStream r1 = new com.itextpdf.text.pdf.PRStream
            com.itextpdf.text.pdf.PRTokeniser r2 = r6.tokens
            long r2 = r2.getFilePointer()
            r1.<init>((com.itextpdf.text.pdf.PdfReader) r6, (long) r2)
            r1.putAll(r0)
            int r0 = r6.objNum
            int r2 = r6.objGen
            r1.setObjNum(r0, r2)
            return r1
        L_0x0124:
            com.itextpdf.text.pdf.PRTokeniser r3 = r6.tokens
            r3.seek(r1)
            return r0
        L_0x012a:
            com.itextpdf.text.pdf.PdfNull r0 = com.itextpdf.text.pdf.PdfNull.PDFNULL
            return r0
        L_0x012d:
            java.lang.String r4 = "true"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x0142
            int r0 = r6.readDepth
            if (r0 != 0) goto L_0x013f
            com.itextpdf.text.pdf.PdfBoolean r0 = new com.itextpdf.text.pdf.PdfBoolean
            r0.<init>((boolean) r3)
            return r0
        L_0x013f:
            com.itextpdf.text.pdf.PdfBoolean r0 = com.itextpdf.text.pdf.PdfBoolean.PDFTRUE
            return r0
        L_0x0142:
            java.lang.String r3 = "false"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0157
            int r0 = r6.readDepth
            if (r0 != 0) goto L_0x0154
            com.itextpdf.text.pdf.PdfBoolean r0 = new com.itextpdf.text.pdf.PdfBoolean
            r0.<init>((boolean) r2)
            return r0
        L_0x0154:
            com.itextpdf.text.pdf.PdfBoolean r0 = com.itextpdf.text.pdf.PdfBoolean.PDFFALSE
            return r0
        L_0x0157:
            com.itextpdf.text.pdf.PdfLiteral r1 = new com.itextpdf.text.pdf.PdfLiteral
            int r0 = r0.ordinal()
            int r0 = -r0
            com.itextpdf.text.pdf.PRTokeniser r2 = r6.tokens
            java.lang.String r2 = r2.getStringValue()
            r1.<init>((int) r0, (java.lang.String) r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfReader.readPRObject():com.itextpdf.text.pdf.PdfObject");
    }

    public static byte[] FlateDecode(byte[] bArr) {
        byte[] FlateDecode = FlateDecode(bArr, true);
        return FlateDecode == null ? FlateDecode(bArr, false) : FlateDecode;
    }

    public static byte[] decodePredictor(byte[] bArr, PdfObject pdfObject) {
        if (pdfObject == null || !pdfObject.isDictionary()) {
            return bArr;
        }
        PdfDictionary pdfDictionary = (PdfDictionary) pdfObject;
        PdfObject pdfObject2 = getPdfObject(pdfDictionary.get(PdfName.PREDICTOR));
        if (pdfObject2 == null || !pdfObject2.isNumber()) {
            return bArr;
        }
        int intValue = ((PdfNumber) pdfObject2).intValue();
        if (intValue < 10 && intValue != 2) {
            return bArr;
        }
        PdfObject pdfObject3 = getPdfObject(pdfDictionary.get(PdfName.COLUMNS));
        int i = 1;
        int intValue2 = (pdfObject3 == null || !pdfObject3.isNumber()) ? 1 : ((PdfNumber) pdfObject3).intValue();
        PdfObject pdfObject4 = getPdfObject(pdfDictionary.get(PdfName.COLORS));
        if (pdfObject4 != null && pdfObject4.isNumber()) {
            i = ((PdfNumber) pdfObject4).intValue();
        }
        PdfObject pdfObject5 = getPdfObject(pdfDictionary.get(PdfName.BITSPERCOMPONENT));
        int intValue3 = (pdfObject5 == null || !pdfObject5.isNumber()) ? 8 : ((PdfNumber) pdfObject5).intValue();
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        int i2 = (i * intValue3) / 8;
        int i3 = (((i * intValue2) * intValue3) + 7) / 8;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[i3];
        if (intValue == 2) {
            if (intValue3 == 8) {
                int length = bArr.length / i3;
                for (int i4 = 0; i4 < length; i4++) {
                    int i5 = i4 * i3;
                    for (int i6 = 0 + i2; i6 < i3; i6++) {
                        int i7 = i5 + i6;
                        bArr[i7] = (byte) (bArr[i7] + bArr[i7 - i2]);
                    }
                }
            }
            return bArr;
        }
        while (true) {
            try {
                int read = dataInputStream.read();
                if (read < 0) {
                    return byteArrayOutputStream.toByteArray();
                }
                dataInputStream.readFully(bArr2, 0, i3);
                switch (read) {
                    case 0:
                        break;
                    case 1:
                        for (int i8 = i2; i8 < i3; i8++) {
                            bArr2[i8] = (byte) (bArr2[i8] + bArr2[i8 - i2]);
                        }
                        break;
                    case 2:
                        for (int i9 = 0; i9 < i3; i9++) {
                            bArr2[i9] = (byte) (bArr2[i9] + bArr3[i9]);
                        }
                        break;
                    case 3:
                        for (int i10 = 0; i10 < i2; i10++) {
                            bArr2[i10] = (byte) (bArr2[i10] + (bArr3[i10] / 2));
                        }
                        for (int i11 = i2; i11 < i3; i11++) {
                            bArr2[i11] = (byte) (bArr2[i11] + (((bArr2[i11 - i2] & 255) + (bArr3[i11] & 255)) / 2));
                        }
                        break;
                    case 4:
                        for (int i12 = 0; i12 < i2; i12++) {
                            bArr2[i12] = (byte) (bArr2[i12] + bArr3[i12]);
                        }
                        for (int i13 = i2; i13 < i3; i13++) {
                            int i14 = i13 - i2;
                            byte b = bArr2[i14] & 255;
                            byte b2 = bArr3[i13] & 255;
                            byte b3 = bArr3[i14] & 255;
                            int i15 = (b + b2) - b3;
                            int abs = Math.abs(i15 - b);
                            int abs2 = Math.abs(i15 - b2);
                            int abs3 = Math.abs(i15 - b3);
                            if (abs <= abs2 && abs <= abs3) {
                                b3 = b;
                            } else if (abs2 <= abs3) {
                                b3 = b2;
                            }
                            bArr2[i13] = (byte) (bArr2[i13] + ((byte) b3));
                        }
                        break;
                    default:
                        throw new RuntimeException(MessageLocalization.getComposedMessage("png.filter.unknown", new Object[0]));
                }
                try {
                    byteArrayOutputStream.write(bArr2);
                } catch (IOException unused) {
                }
                byte[] bArr4 = bArr3;
                bArr3 = bArr2;
                bArr2 = bArr4;
            } catch (Exception unused2) {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static byte[] FlateDecode(byte[] bArr, boolean z) {
        InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(bArr));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[(z ? 4092 : 1)];
        while (true) {
            try {
                int read = inflaterInputStream.read(bArr2);
                if (read >= 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    inflaterInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            } catch (Exception unused) {
                if (z) {
                    return null;
                }
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static byte[] ASCIIHexDecode(byte[] bArr) {
        byte b;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        boolean z = true;
        int i2 = 0;
        while (i2 < bArr.length && (b = bArr[i2] & 255) != 62) {
            if (!PRTokeniser.isWhitespace(b)) {
                int hex = PRTokeniser.getHex(b);
                if (hex == -1) {
                    throw new RuntimeException(MessageLocalization.getComposedMessage("illegal.character.in.asciihexdecode", new Object[0]));
                }
                if (z) {
                    i = hex;
                } else {
                    byteArrayOutputStream.write((byte) ((i << 4) + hex));
                }
                z = !z;
            }
            i2++;
        }
        if (!z) {
            byteArrayOutputStream.write((byte) (i << 4));
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] ASCII85Decode(byte[] bArr) {
        byte b;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int[] iArr = new int[5];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length && (b = bArr[i] & 255) != 126) {
            if (!PRTokeniser.isWhitespace(b)) {
                if (b == 122 && i2 == 0) {
                    byteArrayOutputStream.write(0);
                    byteArrayOutputStream.write(0);
                    byteArrayOutputStream.write(0);
                    byteArrayOutputStream.write(0);
                } else if (b < 33 || b > 117) {
                    throw new RuntimeException(MessageLocalization.getComposedMessage("illegal.character.in.ascii85decode", new Object[0]));
                } else {
                    iArr[i2] = b - 33;
                    i2++;
                    if (i2 == 5) {
                        int i3 = 0;
                        for (int i4 = 0; i4 < 5; i4++) {
                            i3 = (i3 * 85) + iArr[i4];
                        }
                        byteArrayOutputStream.write((byte) (i3 >> 24));
                        byteArrayOutputStream.write((byte) (i3 >> 16));
                        byteArrayOutputStream.write((byte) (i3 >> 8));
                        byteArrayOutputStream.write((byte) i3);
                        i2 = 0;
                    }
                }
            }
            i++;
        }
        if (i2 == 2) {
            byteArrayOutputStream.write((byte) (((((((((iArr[0] * 85) * 85) * 85) * 85) + (((iArr[1] * 85) * 85) * 85)) + 614125) + 7225) + 85) >> 24));
        } else if (i2 == 3) {
            int i5 = (iArr[0] * 85 * 85 * 85 * 85) + (iArr[1] * 85 * 85 * 85) + (iArr[2] * 85 * 85) + 7225 + 85;
            byteArrayOutputStream.write((byte) (i5 >> 24));
            byteArrayOutputStream.write((byte) (i5 >> 16));
        } else if (i2 == 4) {
            int i6 = (iArr[0] * 85 * 85 * 85 * 85) + (iArr[1] * 85 * 85 * 85) + (iArr[2] * 85 * 85) + (iArr[3] * 85) + 85;
            byteArrayOutputStream.write((byte) (i6 >> 24));
            byteArrayOutputStream.write((byte) (i6 >> 16));
            byteArrayOutputStream.write((byte) (i6 >> 8));
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] LZWDecode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new LZWDecoder().decode(bArr, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public boolean isRebuilt() {
        return this.rebuilt;
    }

    public PdfDictionary getPageN(int i) {
        PdfDictionary pageN = this.pageRefs.getPageN(i);
        if (pageN == null) {
            return null;
        }
        if (this.appendable) {
            pageN.setIndRef(this.pageRefs.getPageOrigRef(i));
        }
        return pageN;
    }

    public PdfDictionary getPageNRelease(int i) {
        PdfDictionary pageN = getPageN(i);
        this.pageRefs.releasePage(i);
        return pageN;
    }

    public void releasePage(int i) {
        this.pageRefs.releasePage(i);
    }

    public void resetReleasePage() {
        this.pageRefs.resetReleasePage();
    }

    public PRIndirectReference getPageOrigRef(int i) {
        return this.pageRefs.getPageOrigRef(i);
    }

    public byte[] getPageContent(int i, RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        PdfDictionary pageNRelease = getPageNRelease(i);
        if (pageNRelease == null) {
            return null;
        }
        PdfObject pdfObjectRelease = getPdfObjectRelease(pageNRelease.get(PdfName.CONTENTS));
        if (pdfObjectRelease == null) {
            return new byte[0];
        }
        if (pdfObjectRelease.isStream()) {
            return getStreamBytes((PRStream) pdfObjectRelease, randomAccessFileOrArray);
        }
        if (!pdfObjectRelease.isArray()) {
            return new byte[0];
        }
        PdfArray pdfArray = (PdfArray) pdfObjectRelease;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 0; i2 < pdfArray.size(); i2++) {
            PdfObject pdfObjectRelease2 = getPdfObjectRelease(pdfArray.getPdfObject(i2));
            if (pdfObjectRelease2 != null && pdfObjectRelease2.isStream()) {
                byteArrayOutputStream.write(getStreamBytes((PRStream) pdfObjectRelease2, randomAccessFileOrArray));
                if (i2 != pdfArray.size() - 1) {
                    byteArrayOutputStream.write(10);
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0099 A[SYNTHETIC, Splitter:B:54:0x0099] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] getPageContent(com.itextpdf.text.pdf.PdfDictionary r5) throws java.io.IOException {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.CONTENTS     // Catch:{ all -> 0x0096 }
            com.itextpdf.text.pdf.PdfObject r5 = r5.get(r1)     // Catch:{ all -> 0x0096 }
            com.itextpdf.text.pdf.PdfObject r5 = getPdfObjectRelease((com.itextpdf.text.pdf.PdfObject) r5)     // Catch:{ all -> 0x0096 }
            r1 = 0
            if (r5 != 0) goto L_0x0014
            byte[] r5 = new byte[r1]     // Catch:{ all -> 0x0096 }
            return r5
        L_0x0014:
            boolean r2 = r5.isStream()     // Catch:{ all -> 0x0096 }
            if (r2 == 0) goto L_0x0037
            r1 = r5
            com.itextpdf.text.pdf.PRStream r1 = (com.itextpdf.text.pdf.PRStream) r1     // Catch:{ all -> 0x0096 }
            com.itextpdf.text.pdf.PdfReader r1 = r1.getReader()     // Catch:{ all -> 0x0096 }
            com.itextpdf.text.pdf.RandomAccessFileOrArray r1 = r1.getSafeFile()     // Catch:{ all -> 0x0096 }
            r1.reOpen()     // Catch:{ all -> 0x0034 }
            com.itextpdf.text.pdf.PRStream r5 = (com.itextpdf.text.pdf.PRStream) r5     // Catch:{ all -> 0x0034 }
            byte[] r5 = getStreamBytes(r5, r1)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0033
            r1.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            return r5
        L_0x0034:
            r5 = move-exception
            r0 = r1
            goto L_0x0097
        L_0x0037:
            boolean r2 = r5.isArray()     // Catch:{ all -> 0x0096 }
            if (r2 == 0) goto L_0x0093
            com.itextpdf.text.pdf.PdfArray r5 = (com.itextpdf.text.pdf.PdfArray) r5     // Catch:{ all -> 0x0096 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0096 }
            r2.<init>()     // Catch:{ all -> 0x0096 }
        L_0x0044:
            int r3 = r5.size()     // Catch:{ all -> 0x0096 }
            if (r1 >= r3) goto L_0x0089
            com.itextpdf.text.pdf.PdfObject r3 = r5.getPdfObject(r1)     // Catch:{ all -> 0x0096 }
            com.itextpdf.text.pdf.PdfObject r3 = getPdfObjectRelease((com.itextpdf.text.pdf.PdfObject) r3)     // Catch:{ all -> 0x0096 }
            if (r3 == 0) goto L_0x0086
            boolean r4 = r3.isStream()     // Catch:{ all -> 0x0096 }
            if (r4 != 0) goto L_0x005b
            goto L_0x0086
        L_0x005b:
            if (r0 != 0) goto L_0x0070
            r4 = r3
            com.itextpdf.text.pdf.PRStream r4 = (com.itextpdf.text.pdf.PRStream) r4     // Catch:{ all -> 0x0096 }
            com.itextpdf.text.pdf.PdfReader r4 = r4.getReader()     // Catch:{ all -> 0x0096 }
            com.itextpdf.text.pdf.RandomAccessFileOrArray r4 = r4.getSafeFile()     // Catch:{ all -> 0x0096 }
            r4.reOpen()     // Catch:{ all -> 0x006d }
            r0 = r4
            goto L_0x0070
        L_0x006d:
            r5 = move-exception
            r0 = r4
            goto L_0x0097
        L_0x0070:
            com.itextpdf.text.pdf.PRStream r3 = (com.itextpdf.text.pdf.PRStream) r3     // Catch:{ all -> 0x0096 }
            byte[] r3 = getStreamBytes(r3, r0)     // Catch:{ all -> 0x0096 }
            r2.write(r3)     // Catch:{ all -> 0x0096 }
            int r3 = r5.size()     // Catch:{ all -> 0x0096 }
            int r3 = r3 + -1
            if (r1 == r3) goto L_0x0086
            r3 = 10
            r2.write(r3)     // Catch:{ all -> 0x0096 }
        L_0x0086:
            int r1 = r1 + 1
            goto L_0x0044
        L_0x0089:
            byte[] r5 = r2.toByteArray()     // Catch:{ all -> 0x0096 }
            if (r0 == 0) goto L_0x0092
            r0.close()     // Catch:{ Exception -> 0x0092 }
        L_0x0092:
            return r5
        L_0x0093:
            byte[] r5 = new byte[r1]     // Catch:{ all -> 0x0096 }
            return r5
        L_0x0096:
            r5 = move-exception
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            r0.close()     // Catch:{ Exception -> 0x009c }
        L_0x009c:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfReader.getPageContent(com.itextpdf.text.pdf.PdfDictionary):byte[]");
    }

    public PdfDictionary getPageResources(int i) {
        return getPageResources(getPageN(i));
    }

    public PdfDictionary getPageResources(PdfDictionary pdfDictionary) {
        return pdfDictionary.getAsDict(PdfName.RESOURCES);
    }

    public byte[] getPageContent(int i) throws IOException {
        RandomAccessFileOrArray safeFile = getSafeFile();
        try {
            safeFile.reOpen();
            return getPageContent(i, safeFile);
        } finally {
            try {
                safeFile.close();
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void killXref(PdfObject pdfObject) {
        if (pdfObject != null) {
            if (!(pdfObject instanceof PdfIndirectReference) || pdfObject.isIndirect()) {
                int type = pdfObject.type();
                if (type != 10) {
                    switch (type) {
                        case 5:
                            PdfArray pdfArray = (PdfArray) pdfObject;
                            for (int i = 0; i < pdfArray.size(); i++) {
                                killXref(pdfArray.getPdfObject(i));
                            }
                            return;
                        case 6:
                        case 7:
                            PdfDictionary pdfDictionary = (PdfDictionary) pdfObject;
                            for (PdfName pdfName : pdfDictionary.getKeys()) {
                                killXref(pdfDictionary.get(pdfName));
                            }
                            return;
                        default:
                            return;
                    }
                } else {
                    int number = ((PRIndirectReference) pdfObject).getNumber();
                    this.xrefObj.set(number, (Object) null);
                    this.freeXref = number;
                    killXref(this.xrefObj.get(number));
                }
            }
        }
    }

    public void setPageContent(int i, byte[] bArr) {
        setPageContent(i, bArr, -1);
    }

    public void setPageContent(int i, byte[] bArr, int i2) {
        setPageContent(i, bArr, i2, false);
    }

    public void setPageContent(int i, byte[] bArr, int i2, boolean z) {
        PdfDictionary pageN = getPageN(i);
        if (pageN != null) {
            PdfObject pdfObject = pageN.get(PdfName.CONTENTS);
            this.freeXref = -1;
            if (z) {
                killXref(pdfObject);
            }
            if (this.freeXref == -1) {
                this.xrefObj.add((Object) null);
                this.freeXref = this.xrefObj.size() - 1;
            }
            pageN.put(PdfName.CONTENTS, new PRIndirectReference(this, this.freeXref));
            this.xrefObj.set(this.freeXref, new PRStream(this, bArr, i2));
        }
    }

    public static byte[] decodeBytes(byte[] bArr, PdfDictionary pdfDictionary) throws IOException {
        return decodeBytes(bArr, pdfDictionary, FilterHandlers.getDefaultFilterHandlers());
    }

    public static byte[] decodeBytes(byte[] bArr, PdfDictionary pdfDictionary, Map<PdfName, FilterHandlers.FilterHandler> map) throws IOException {
        PdfObject pdfObjectRelease = getPdfObjectRelease(pdfDictionary.get(PdfName.FILTER));
        ArrayList<PdfObject> arrayList = new ArrayList<>();
        if (pdfObjectRelease != null) {
            if (pdfObjectRelease.isName()) {
                arrayList.add(pdfObjectRelease);
            } else if (pdfObjectRelease.isArray()) {
                arrayList = ((PdfArray) pdfObjectRelease).getArrayList();
            }
        }
        ArrayList<PdfObject> arrayList2 = new ArrayList<>();
        PdfObject pdfObjectRelease2 = getPdfObjectRelease(pdfDictionary.get(PdfName.DECODEPARMS));
        if (pdfObjectRelease2 == null || (!pdfObjectRelease2.isDictionary() && !pdfObjectRelease2.isArray())) {
            pdfObjectRelease2 = getPdfObjectRelease(pdfDictionary.get(PdfName.DP));
        }
        if (pdfObjectRelease2 != null) {
            if (pdfObjectRelease2.isDictionary()) {
                arrayList2.add(pdfObjectRelease2);
            } else if (pdfObjectRelease2.isArray()) {
                arrayList2 = ((PdfArray) pdfObjectRelease2).getArrayList();
            }
        }
        byte[] bArr2 = bArr;
        for (int i = 0; i < arrayList.size(); i++) {
            PdfName pdfName = (PdfName) arrayList.get(i);
            FilterHandlers.FilterHandler filterHandler = map.get(pdfName);
            if (filterHandler == null) {
                throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("the.filter.1.is.not.supported", pdfName));
            }
            PdfDictionary pdfDictionary2 = null;
            if (i < arrayList2.size()) {
                Object pdfObject = getPdfObject(arrayList2.get(i));
                if (pdfObject instanceof PdfDictionary) {
                    pdfDictionary2 = (PdfDictionary) pdfObject;
                } else if (pdfObject != null && !(pdfObject instanceof PdfNull) && (!(pdfObject instanceof PdfLiteral) || !Arrays.equals("null".getBytes(), ((PdfLiteral) pdfObject).getBytes()))) {
                    throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("the.decode.parameter.type.1.is.not.supported", pdfObject.getClass().toString()));
                }
            }
            bArr2 = filterHandler.decode(bArr2, pdfName, pdfDictionary2, pdfDictionary);
        }
        return bArr2;
    }

    public static byte[] getStreamBytes(PRStream pRStream, RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        return decodeBytes(getStreamBytesRaw(pRStream, randomAccessFileOrArray), pRStream);
    }

    public static byte[] getStreamBytes(PRStream pRStream) throws IOException {
        RandomAccessFileOrArray safeFile = pRStream.getReader().getSafeFile();
        try {
            safeFile.reOpen();
            return getStreamBytes(pRStream, safeFile);
        } finally {
            try {
                safeFile.close();
            } catch (Exception unused) {
            }
        }
    }

    public static byte[] getStreamBytesRaw(PRStream pRStream, RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        PdfReader reader = pRStream.getReader();
        if (pRStream.getOffset() < 0) {
            return pRStream.getBytes();
        }
        byte[] bArr = new byte[pRStream.getLength()];
        randomAccessFileOrArray.seek(pRStream.getOffset());
        randomAccessFileOrArray.readFully(bArr);
        PdfEncryption decrypt2 = reader.getDecrypt();
        if (decrypt2 != null) {
            PdfObject pdfObjectRelease = getPdfObjectRelease(pRStream.get(PdfName.FILTER));
            ArrayList<PdfObject> arrayList = new ArrayList<>();
            if (pdfObjectRelease != null) {
                if (pdfObjectRelease.isName()) {
                    arrayList.add(pdfObjectRelease);
                } else if (pdfObjectRelease.isArray()) {
                    arrayList = ((PdfArray) pdfObjectRelease).getArrayList();
                }
            }
            boolean z = false;
            int i = 0;
            while (true) {
                if (i < arrayList.size()) {
                    PdfObject pdfObjectRelease2 = getPdfObjectRelease(arrayList.get(i));
                    if (pdfObjectRelease2 != null && pdfObjectRelease2.toString().equals("/Crypt")) {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            if (!z) {
                decrypt2.setHashKey(pRStream.getObjNum(), pRStream.getObjGen());
                return decrypt2.decryptByteArray(bArr);
            }
        }
        return bArr;
    }

    public static byte[] getStreamBytesRaw(PRStream pRStream) throws IOException {
        RandomAccessFileOrArray safeFile = pRStream.getReader().getSafeFile();
        try {
            safeFile.reOpen();
            return getStreamBytesRaw(pRStream, safeFile);
        } finally {
            try {
                safeFile.close();
            } catch (Exception unused) {
            }
        }
    }

    public void eliminateSharedStreams() {
        PdfObject pdfObject;
        if (this.sharedStreams) {
            this.sharedStreams = false;
            if (this.pageRefs.size() != 1) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                IntHashtable intHashtable = new IntHashtable();
                for (int i = 1; i <= this.pageRefs.size(); i++) {
                    PdfDictionary pageN = this.pageRefs.getPageN(i);
                    if (!(pageN == null || (pdfObject = getPdfObject(pageN.get(PdfName.CONTENTS))) == null)) {
                        if (pdfObject.isStream()) {
                            PRIndirectReference pRIndirectReference = (PRIndirectReference) pageN.get(PdfName.CONTENTS);
                            if (intHashtable.containsKey(pRIndirectReference.getNumber())) {
                                arrayList.add(pRIndirectReference);
                                arrayList2.add(new PRStream((PRStream) pdfObject, (PdfDictionary) null));
                            } else {
                                intHashtable.put(pRIndirectReference.getNumber(), 1);
                            }
                        } else if (pdfObject.isArray()) {
                            PdfArray pdfArray = (PdfArray) pdfObject;
                            for (int i2 = 0; i2 < pdfArray.size(); i2++) {
                                PRIndirectReference pRIndirectReference2 = (PRIndirectReference) pdfArray.getPdfObject(i2);
                                if (intHashtable.containsKey(pRIndirectReference2.getNumber())) {
                                    arrayList.add(pRIndirectReference2);
                                    arrayList2.add(new PRStream((PRStream) getPdfObject((PdfObject) pRIndirectReference2), (PdfDictionary) null));
                                } else {
                                    intHashtable.put(pRIndirectReference2.getNumber(), 1);
                                }
                            }
                        }
                    }
                }
                if (!arrayList2.isEmpty()) {
                    for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                        this.xrefObj.add(arrayList2.get(i3));
                        ((PRIndirectReference) arrayList.get(i3)).setNumber(this.xrefObj.size() - 1, 0);
                    }
                }
            }
        }
    }

    public boolean isTampered() {
        return this.tampered;
    }

    public void setTampered(boolean z) {
        this.tampered = z;
        this.pageRefs.keepPages();
    }

    public byte[] getMetadata() throws IOException {
        PdfObject pdfObject = getPdfObject(this.catalog.get(PdfName.METADATA));
        if (!(pdfObject instanceof PRStream)) {
            return null;
        }
        RandomAccessFileOrArray safeFile = getSafeFile();
        try {
            safeFile.reOpen();
            return getStreamBytes((PRStream) pdfObject, safeFile);
        } finally {
            try {
                safeFile.close();
            } catch (Exception unused) {
            }
        }
    }

    public long getLastXref() {
        return this.lastXref;
    }

    public int getXrefSize() {
        return this.xrefObj.size();
    }

    public long getEofPos() {
        return this.eofPos;
    }

    public char getPdfVersion() {
        return this.pdfVersion;
    }

    public boolean isEncrypted() {
        return this.encrypted;
    }

    public long getPermissions() {
        return this.pValue;
    }

    public boolean is128Key() {
        return this.rValue == 3;
    }

    public PdfDictionary getTrailer() {
        return this.trailer;
    }

    /* access modifiers changed from: package-private */
    public PdfEncryption getDecrypt() {
        return this.decrypt;
    }

    static boolean equalsn(byte[] bArr, byte[] bArr2) {
        int length = bArr2.length;
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean existsName(PdfDictionary pdfDictionary, PdfName pdfName, PdfName pdfName2) {
        PdfObject pdfObjectRelease = getPdfObjectRelease(pdfDictionary.get(pdfName));
        if (pdfObjectRelease == null || !pdfObjectRelease.isName()) {
            return false;
        }
        return ((PdfName) pdfObjectRelease).equals(pdfName2);
    }

    static String getFontName(PdfDictionary pdfDictionary) {
        PdfObject pdfObjectRelease;
        if (pdfDictionary == null || (pdfObjectRelease = getPdfObjectRelease(pdfDictionary.get(PdfName.BASEFONT))) == null || !pdfObjectRelease.isName()) {
            return null;
        }
        return PdfName.decodeName(pdfObjectRelease.toString());
    }

    static String getSubsetPrefix(PdfDictionary pdfDictionary) {
        String fontName;
        if (pdfDictionary == null || (fontName = getFontName(pdfDictionary)) == null || fontName.length() < 8 || fontName.charAt(6) != '+') {
            return null;
        }
        for (int i = 0; i < 6; i++) {
            char charAt = fontName.charAt(i);
            if (charAt < 'A' || charAt > 'Z') {
                return null;
            }
        }
        return fontName;
    }

    public int shuffleSubsetNames() {
        PdfDictionary asDict;
        String subsetPrefix;
        int i = 0;
        for (int i2 = 1; i2 < this.xrefObj.size(); i2++) {
            PdfObject pdfObjectRelease = getPdfObjectRelease(i2);
            if (pdfObjectRelease != null && pdfObjectRelease.isDictionary()) {
                PdfDictionary pdfDictionary = (PdfDictionary) pdfObjectRelease;
                if (existsName(pdfDictionary, PdfName.TYPE, PdfName.FONT)) {
                    if (existsName(pdfDictionary, PdfName.SUBTYPE, PdfName.TYPE1) || existsName(pdfDictionary, PdfName.SUBTYPE, PdfName.MMTYPE1) || existsName(pdfDictionary, PdfName.SUBTYPE, PdfName.TRUETYPE)) {
                        String subsetPrefix2 = getSubsetPrefix(pdfDictionary);
                        if (subsetPrefix2 != null) {
                            PdfName pdfName = new PdfName(BaseFont.createSubsetPrefix() + subsetPrefix2.substring(7));
                            pdfDictionary.put(PdfName.BASEFONT, pdfName);
                            setXrefPartialObject(i2, pdfDictionary);
                            i++;
                            PdfDictionary asDict2 = pdfDictionary.getAsDict(PdfName.FONTDESCRIPTOR);
                            if (asDict2 != null) {
                                asDict2.put(PdfName.FONTNAME, pdfName);
                            }
                        }
                    } else if (existsName(pdfDictionary, PdfName.SUBTYPE, PdfName.TYPE0)) {
                        String subsetPrefix3 = getSubsetPrefix(pdfDictionary);
                        PdfArray asArray = pdfDictionary.getAsArray(PdfName.DESCENDANTFONTS);
                        if (!(asArray == null || asArray.isEmpty() || (subsetPrefix = getSubsetPrefix(asDict)) == null)) {
                            String createSubsetPrefix = BaseFont.createSubsetPrefix();
                            if (subsetPrefix3 != null) {
                                pdfDictionary.put(PdfName.BASEFONT, new PdfName(createSubsetPrefix + subsetPrefix3.substring(7)));
                            }
                            setXrefPartialObject(i2, pdfDictionary);
                            PdfName pdfName2 = new PdfName(createSubsetPrefix + subsetPrefix.substring(7));
                            (asDict = asArray.getAsDict(0)).put(PdfName.BASEFONT, pdfName2);
                            i++;
                            PdfDictionary asDict3 = asDict.getAsDict(PdfName.FONTDESCRIPTOR);
                            if (asDict3 != null) {
                                asDict3.put(PdfName.FONTNAME, pdfName2);
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public int createFakeFontSubsets() {
        String fontName;
        int i = 0;
        for (int i2 = 1; i2 < this.xrefObj.size(); i2++) {
            PdfObject pdfObjectRelease = getPdfObjectRelease(i2);
            if (pdfObjectRelease != null && pdfObjectRelease.isDictionary()) {
                PdfDictionary pdfDictionary = (PdfDictionary) pdfObjectRelease;
                if (existsName(pdfDictionary, PdfName.TYPE, PdfName.FONT) && ((existsName(pdfDictionary, PdfName.SUBTYPE, PdfName.TYPE1) || existsName(pdfDictionary, PdfName.SUBTYPE, PdfName.MMTYPE1) || existsName(pdfDictionary, PdfName.SUBTYPE, PdfName.TRUETYPE)) && getSubsetPrefix(pdfDictionary) == null && (fontName = getFontName(pdfDictionary)) != null)) {
                    String str = BaseFont.createSubsetPrefix() + fontName;
                    PdfDictionary pdfDictionary2 = (PdfDictionary) getPdfObjectRelease(pdfDictionary.get(PdfName.FONTDESCRIPTOR));
                    if (!(pdfDictionary2 == null || (pdfDictionary2.get(PdfName.FONTFILE) == null && pdfDictionary2.get(PdfName.FONTFILE2) == null && pdfDictionary2.get(PdfName.FONTFILE3) == null))) {
                        PdfDictionary asDict = pdfDictionary.getAsDict(PdfName.FONTDESCRIPTOR);
                        PdfName pdfName = new PdfName(str);
                        pdfDictionary.put(PdfName.BASEFONT, pdfName);
                        asDict.put(PdfName.FONTNAME, pdfName);
                        setXrefPartialObject(i2, pdfDictionary);
                        i++;
                    }
                }
            }
        }
        return i;
    }

    private static PdfArray getNameArray(PdfObject pdfObject) {
        PdfObject pdfObjectRelease;
        PdfObject pdfObjectRelease2;
        if (pdfObject == null || (pdfObjectRelease = getPdfObjectRelease(pdfObject)) == null) {
            return null;
        }
        if (pdfObjectRelease.isArray()) {
            return (PdfArray) pdfObjectRelease;
        }
        if (!pdfObjectRelease.isDictionary() || (pdfObjectRelease2 = getPdfObjectRelease(((PdfDictionary) pdfObjectRelease).get(PdfName.D))) == null || !pdfObjectRelease2.isArray()) {
            return null;
        }
        return (PdfArray) pdfObjectRelease2;
    }

    public HashMap<Object, PdfObject> getNamedDestination() {
        return getNamedDestination(false);
    }

    public HashMap<Object, PdfObject> getNamedDestination(boolean z) {
        HashMap<Object, PdfObject> namedDestinationFromNames = getNamedDestinationFromNames(z);
        namedDestinationFromNames.putAll(getNamedDestinationFromStrings());
        return namedDestinationFromNames;
    }

    public HashMap<String, PdfObject> getNamedDestinationFromNames() {
        return new HashMap<>(getNamedDestinationFromNames(false));
    }

    public HashMap<Object, PdfObject> getNamedDestinationFromNames(boolean z) {
        PdfDictionary pdfDictionary;
        HashMap<Object, PdfObject> hashMap = new HashMap<>();
        if (this.catalog.get(PdfName.DESTS) == null || (pdfDictionary = (PdfDictionary) getPdfObjectRelease(this.catalog.get(PdfName.DESTS))) == null) {
            return hashMap;
        }
        for (PdfName next : pdfDictionary.getKeys()) {
            PdfArray nameArray = getNameArray(pdfDictionary.get(next));
            if (nameArray != null) {
                if (z) {
                    hashMap.put(next, nameArray);
                } else {
                    hashMap.put(PdfName.decodeName(next.toString()), nameArray);
                }
            }
        }
        return hashMap;
    }

    public HashMap<String, PdfObject> getNamedDestinationFromStrings() {
        PdfDictionary pdfDictionary;
        PdfDictionary pdfDictionary2;
        if (this.catalog.get(PdfName.NAMES) == null || (pdfDictionary = (PdfDictionary) getPdfObjectRelease(this.catalog.get(PdfName.NAMES))) == null || (pdfDictionary2 = (PdfDictionary) getPdfObjectRelease(pdfDictionary.get(PdfName.DESTS))) == null) {
            return new HashMap<>();
        }
        HashMap<String, PdfObject> readTree = PdfNameTree.readTree(pdfDictionary2);
        Iterator<Map.Entry<String, PdfObject>> it = readTree.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            PdfArray nameArray = getNameArray((PdfObject) next.getValue());
            if (nameArray != null) {
                next.setValue(nameArray);
            } else {
                it.remove();
            }
        }
        return readTree;
    }

    public void removeFields() {
        this.pageRefs.resetReleasePage();
        for (int i = 1; i <= this.pageRefs.size(); i++) {
            PdfDictionary pageN = this.pageRefs.getPageN(i);
            PdfArray asArray = pageN.getAsArray(PdfName.ANNOTS);
            if (asArray == null) {
                this.pageRefs.releasePage(i);
            } else {
                int i2 = 0;
                while (i2 < asArray.size()) {
                    PdfObject pdfObjectRelease = getPdfObjectRelease(asArray.getPdfObject(i2));
                    if (pdfObjectRelease != null && pdfObjectRelease.isDictionary() && PdfName.WIDGET.equals(((PdfDictionary) pdfObjectRelease).get(PdfName.SUBTYPE))) {
                        asArray.remove(i2);
                        i2--;
                    }
                    i2++;
                }
                if (asArray.isEmpty()) {
                    pageN.remove(PdfName.ANNOTS);
                } else {
                    this.pageRefs.releasePage(i);
                }
            }
        }
        this.catalog.remove(PdfName.ACROFORM);
        this.pageRefs.resetReleasePage();
    }

    public void removeAnnotations() {
        this.pageRefs.resetReleasePage();
        for (int i = 1; i <= this.pageRefs.size(); i++) {
            PdfDictionary pageN = this.pageRefs.getPageN(i);
            if (pageN.get(PdfName.ANNOTS) == null) {
                this.pageRefs.releasePage(i);
            } else {
                pageN.remove(PdfName.ANNOTS);
            }
        }
        this.catalog.remove(PdfName.ACROFORM);
        this.pageRefs.resetReleasePage();
    }

    public ArrayList<PdfAnnotation.PdfImportedLink> getLinks(int i) {
        this.pageRefs.resetReleasePage();
        ArrayList<PdfAnnotation.PdfImportedLink> arrayList = new ArrayList<>();
        PdfDictionary pageN = this.pageRefs.getPageN(i);
        if (pageN.get(PdfName.ANNOTS) != null) {
            PdfArray asArray = pageN.getAsArray(PdfName.ANNOTS);
            for (int i2 = 0; i2 < asArray.size(); i2++) {
                PdfDictionary pdfDictionary = (PdfDictionary) getPdfObjectRelease(asArray.getPdfObject(i2));
                if (PdfName.LINK.equals(pdfDictionary.get(PdfName.SUBTYPE))) {
                    arrayList.add(new PdfAnnotation.PdfImportedLink(pdfDictionary));
                }
            }
        }
        this.pageRefs.releasePage(i);
        this.pageRefs.resetReleasePage();
        return arrayList;
    }

    private void iterateBookmarks(PdfObject pdfObject, HashMap<Object, PdfObject> hashMap) {
        while (pdfObject != null) {
            replaceNamedDestination(pdfObject, hashMap);
            PdfDictionary pdfDictionary = (PdfDictionary) getPdfObjectRelease(pdfObject);
            PdfObject pdfObject2 = pdfDictionary.get(PdfName.FIRST);
            if (pdfObject2 != null) {
                iterateBookmarks(pdfObject2, hashMap);
            }
            pdfObject = pdfDictionary.get(PdfName.NEXT);
        }
    }

    public void makeRemoteNamedDestinationsLocal() {
        if (!this.remoteToLocalNamedDestinations) {
            this.remoteToLocalNamedDestinations = true;
            HashMap<Object, PdfObject> namedDestination = getNamedDestination(true);
            if (!namedDestination.isEmpty()) {
                for (int i = 1; i <= this.pageRefs.size(); i++) {
                    PdfObject pdfObject = this.pageRefs.getPageN(i).get(PdfName.ANNOTS);
                    PdfArray pdfArray = (PdfArray) getPdfObject(pdfObject);
                    int i2 = this.lastXrefPartial;
                    releaseLastXrefPartial();
                    if (pdfArray == null) {
                        this.pageRefs.releasePage(i);
                    } else {
                        boolean z = false;
                        for (int i3 = 0; i3 < pdfArray.size(); i3++) {
                            PdfObject pdfObject2 = pdfArray.getPdfObject(i3);
                            if (convertNamedDestination(pdfObject2, namedDestination) && !pdfObject2.isIndirect()) {
                                z = true;
                            }
                        }
                        if (z) {
                            setXrefPartialObject(i2, pdfArray);
                        }
                        if (!z || pdfObject.isIndirect()) {
                            this.pageRefs.releasePage(i);
                        }
                    }
                }
            }
        }
    }

    private boolean convertNamedDestination(PdfObject pdfObject, HashMap<Object, PdfObject> hashMap) {
        PdfObject pdfObject2;
        PdfObject pdfObjectRelease;
        PdfObject pdfObject3 = getPdfObject(pdfObject);
        int i = this.lastXrefPartial;
        releaseLastXrefPartial();
        if (pdfObject3 == null || !pdfObject3.isDictionary() || (pdfObject2 = getPdfObject(((PdfDictionary) pdfObject3).get(PdfName.A))) == null) {
            return false;
        }
        int i2 = this.lastXrefPartial;
        releaseLastXrefPartial();
        PdfDictionary pdfDictionary = (PdfDictionary) pdfObject2;
        if (!PdfName.GOTOR.equals((PdfName) getPdfObjectRelease(pdfDictionary.get(PdfName.S))) || (pdfObjectRelease = getPdfObjectRelease(pdfDictionary.get(PdfName.D))) == null) {
            return false;
        }
        boolean isName = pdfObjectRelease.isName();
        Object obj = pdfObjectRelease;
        if (!isName) {
            obj = pdfObjectRelease.isString() ? pdfObjectRelease.toString() : null;
        }
        if (((PdfArray) hashMap.get(obj)) == null) {
            return false;
        }
        pdfDictionary.remove(PdfName.F);
        pdfDictionary.remove(PdfName.NEWWINDOW);
        pdfDictionary.put(PdfName.S, PdfName.GOTO);
        setXrefPartialObject(i2, pdfObject2);
        setXrefPartialObject(i, pdfObject3);
        return true;
    }

    public void consolidateNamedDestinations() {
        if (!this.consolidateNamedDestinations) {
            this.consolidateNamedDestinations = true;
            HashMap<Object, PdfObject> namedDestination = getNamedDestination(true);
            if (!namedDestination.isEmpty()) {
                for (int i = 1; i <= this.pageRefs.size(); i++) {
                    PdfObject pdfObject = this.pageRefs.getPageN(i).get(PdfName.ANNOTS);
                    PdfArray pdfArray = (PdfArray) getPdfObject(pdfObject);
                    int i2 = this.lastXrefPartial;
                    releaseLastXrefPartial();
                    if (pdfArray == null) {
                        this.pageRefs.releasePage(i);
                    } else {
                        boolean z = false;
                        for (int i3 = 0; i3 < pdfArray.size(); i3++) {
                            PdfObject pdfObject2 = pdfArray.getPdfObject(i3);
                            if (replaceNamedDestination(pdfObject2, namedDestination) && !pdfObject2.isIndirect()) {
                                z = true;
                            }
                        }
                        if (z) {
                            setXrefPartialObject(i2, pdfArray);
                        }
                        if (!z || pdfObject.isIndirect()) {
                            this.pageRefs.releasePage(i);
                        }
                    }
                }
                PdfDictionary pdfDictionary = (PdfDictionary) getPdfObjectRelease(this.catalog.get(PdfName.OUTLINES));
                if (pdfDictionary != null) {
                    iterateBookmarks(pdfDictionary.get(PdfName.FIRST), namedDestination);
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: com.itextpdf.text.pdf.PdfObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean replaceNamedDestination(com.itextpdf.text.pdf.PdfObject r9, java.util.HashMap<java.lang.Object, com.itextpdf.text.pdf.PdfObject> r10) {
        /*
            r8 = this;
            com.itextpdf.text.pdf.PdfObject r9 = getPdfObject((com.itextpdf.text.pdf.PdfObject) r9)
            int r0 = r8.lastXrefPartial
            r8.releaseLastXrefPartial()
            if (r9 == 0) goto L_0x00a0
            boolean r1 = r9.isDictionary()
            if (r1 == 0) goto L_0x00a0
            r1 = r9
            com.itextpdf.text.pdf.PdfDictionary r1 = (com.itextpdf.text.pdf.PdfDictionary) r1
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.DEST
            com.itextpdf.text.pdf.PdfObject r2 = r1.get(r2)
            com.itextpdf.text.pdf.PdfObject r2 = getPdfObjectRelease((com.itextpdf.text.pdf.PdfObject) r2)
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0046
            boolean r5 = r2.isName()
            if (r5 == 0) goto L_0x0029
            goto L_0x0035
        L_0x0029:
            boolean r5 = r2.isString()
            if (r5 == 0) goto L_0x0034
            java.lang.String r2 = r2.toString()
            goto L_0x0035
        L_0x0034:
            r2 = r3
        L_0x0035:
            java.lang.Object r10 = r10.get(r2)
            com.itextpdf.text.pdf.PdfArray r10 = (com.itextpdf.text.pdf.PdfArray) r10
            if (r10 == 0) goto L_0x00a0
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.DEST
            r1.put(r2, r10)
            r8.setXrefPartialObject(r0, r9)
            return r4
        L_0x0046:
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.A
            com.itextpdf.text.pdf.PdfObject r1 = r1.get(r2)
            com.itextpdf.text.pdf.PdfObject r1 = getPdfObject((com.itextpdf.text.pdf.PdfObject) r1)
            if (r1 == 0) goto L_0x00a0
            int r2 = r8.lastXrefPartial
            r8.releaseLastXrefPartial()
            r5 = r1
            com.itextpdf.text.pdf.PdfDictionary r5 = (com.itextpdf.text.pdf.PdfDictionary) r5
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.S
            com.itextpdf.text.pdf.PdfObject r6 = r5.get(r6)
            com.itextpdf.text.pdf.PdfObject r6 = getPdfObjectRelease((com.itextpdf.text.pdf.PdfObject) r6)
            com.itextpdf.text.pdf.PdfName r6 = (com.itextpdf.text.pdf.PdfName) r6
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.GOTO
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x00a0
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.D
            com.itextpdf.text.pdf.PdfObject r6 = r5.get(r6)
            com.itextpdf.text.pdf.PdfObject r6 = getPdfObjectRelease((com.itextpdf.text.pdf.PdfObject) r6)
            if (r6 == 0) goto L_0x008c
            boolean r7 = r6.isName()
            if (r7 == 0) goto L_0x0082
            r3 = r6
            goto L_0x008c
        L_0x0082:
            boolean r7 = r6.isString()
            if (r7 == 0) goto L_0x008c
            java.lang.String r3 = r6.toString()
        L_0x008c:
            java.lang.Object r10 = r10.get(r3)
            com.itextpdf.text.pdf.PdfArray r10 = (com.itextpdf.text.pdf.PdfArray) r10
            if (r10 == 0) goto L_0x00a0
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.D
            r5.put(r3, r10)
            r8.setXrefPartialObject(r2, r1)
            r8.setXrefPartialObject(r0, r9)
            return r4
        L_0x00a0:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfReader.replaceNamedDestination(com.itextpdf.text.pdf.PdfObject, java.util.HashMap):boolean");
    }

    protected static PdfDictionary duplicatePdfDictionary(PdfDictionary pdfDictionary, PdfDictionary pdfDictionary2, PdfReader pdfReader) {
        if (pdfDictionary2 == null) {
            pdfDictionary2 = new PdfDictionary(pdfDictionary.size());
        }
        for (PdfName next : pdfDictionary.getKeys()) {
            pdfDictionary2.put(next, duplicatePdfObject(pdfDictionary.get(next), pdfReader));
        }
        return pdfDictionary2;
    }

    protected static PdfObject duplicatePdfObject(PdfObject pdfObject, PdfReader pdfReader) {
        if (pdfObject == null) {
            return null;
        }
        int type = pdfObject.type();
        if (type != 10) {
            switch (type) {
                case 5:
                    PdfArray pdfArray = (PdfArray) pdfObject;
                    PdfArray pdfArray2 = new PdfArray(pdfArray.size());
                    ListIterator<PdfObject> listIterator = pdfArray.listIterator();
                    while (listIterator.hasNext()) {
                        pdfArray2.add(duplicatePdfObject(listIterator.next(), pdfReader));
                    }
                    return pdfArray2;
                case 6:
                    return duplicatePdfDictionary((PdfDictionary) pdfObject, (PdfDictionary) null, pdfReader);
                case 7:
                    PRStream pRStream = (PRStream) pdfObject;
                    PRStream pRStream2 = new PRStream(pRStream, (PdfDictionary) null, pdfReader);
                    duplicatePdfDictionary(pRStream, pRStream2, pdfReader);
                    return pRStream2;
                default:
                    return pdfObject;
            }
        } else {
            PRIndirectReference pRIndirectReference = (PRIndirectReference) pdfObject;
            return new PRIndirectReference(pdfReader, pRIndirectReference.getNumber(), pRIndirectReference.getGeneration());
        }
    }

    public void close() {
        try {
            this.tokens.close();
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: java.lang.Object[]} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x012d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeUnusedNode(com.itextpdf.text.pdf.PdfObject r13, boolean[] r14) {
        /*
            r12 = this;
            java.util.Stack r0 = new java.util.Stack
            r0.<init>()
            r0.push(r13)
        L_0x0008:
            boolean r13 = r0.empty()
            if (r13 != 0) goto L_0x013d
            java.lang.Object r13 = r0.pop()
            if (r13 != 0) goto L_0x0015
            goto L_0x0008
        L_0x0015:
            boolean r1 = r13 instanceof com.itextpdf.text.pdf.PdfObject
            r2 = 2
            r3 = 0
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L_0x005f
            com.itextpdf.text.pdf.PdfObject r13 = (com.itextpdf.text.pdf.PdfObject) r13
            int r1 = r13.type()
            r6 = 10
            if (r1 == r6) goto L_0x004b
            switch(r1) {
                case 5: goto L_0x003f;
                case 6: goto L_0x002b;
                case 7: goto L_0x002b;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x0008
        L_0x002b:
            com.itextpdf.text.pdf.PdfDictionary r13 = (com.itextpdf.text.pdf.PdfDictionary) r13
            int r1 = r13.size()
            com.itextpdf.text.pdf.PdfName[] r1 = new com.itextpdf.text.pdf.PdfName[r1]
            java.util.Set r6 = r13.getKeys()
            r6.toArray(r1)
            r7 = r1
            r6 = r4
            r1 = r13
            r13 = r3
            goto L_0x008b
        L_0x003f:
            com.itextpdf.text.pdf.PdfArray r13 = (com.itextpdf.text.pdf.PdfArray) r13
            java.util.ArrayList r13 = r13.getArrayList()
            r1 = r3
            r7 = r1
            r6 = r4
            r3 = r13
            r13 = r7
            goto L_0x008b
        L_0x004b:
            com.itextpdf.text.pdf.PRIndirectReference r13 = (com.itextpdf.text.pdf.PRIndirectReference) r13
            int r1 = r13.getNumber()
            boolean r2 = r14[r1]
            if (r2 != 0) goto L_0x0008
            r14[r1] = r5
            com.itextpdf.text.pdf.PdfObject r13 = getPdfObjectRelease((com.itextpdf.text.pdf.PdfObject) r13)
            r0.push(r13)
            goto L_0x0008
        L_0x005f:
            java.lang.Object[] r13 = (java.lang.Object[]) r13
            r1 = r13[r4]
            boolean r1 = r1 instanceof java.util.ArrayList
            if (r1 == 0) goto L_0x0077
            r1 = r13[r4]
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            r6 = r13[r5]
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r7 = r3
            r3 = r1
            r1 = r7
            goto L_0x008b
        L_0x0077:
            r1 = r13[r4]
            com.itextpdf.text.pdf.PdfName[] r1 = (com.itextpdf.text.pdf.PdfName[]) r1
            r6 = r13[r5]
            com.itextpdf.text.pdf.PdfDictionary r6 = (com.itextpdf.text.pdf.PdfDictionary) r6
            r7 = r13[r2]
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r11 = r7
            r7 = r1
            r1 = r6
            r6 = r11
        L_0x008b:
            if (r3 == 0) goto L_0x00e4
        L_0x008d:
            int r1 = r3.size()
            if (r6 >= r1) goto L_0x0008
            java.lang.Object r1 = r3.get(r6)
            com.itextpdf.text.pdf.PdfObject r1 = (com.itextpdf.text.pdf.PdfObject) r1
            boolean r7 = r1.isIndirect()
            if (r7 == 0) goto L_0x00c2
            r7 = r1
            com.itextpdf.text.pdf.PRIndirectReference r7 = (com.itextpdf.text.pdf.PRIndirectReference) r7
            int r7 = r7.getNumber()
            java.util.ArrayList<com.itextpdf.text.pdf.PdfObject> r8 = r12.xrefObj
            int r8 = r8.size()
            if (r7 >= r8) goto L_0x00ba
            boolean r8 = r12.partial
            if (r8 != 0) goto L_0x00c2
            java.util.ArrayList<com.itextpdf.text.pdf.PdfObject> r8 = r12.xrefObj
            java.lang.Object r7 = r8.get(r7)
            if (r7 != 0) goto L_0x00c2
        L_0x00ba:
            com.itextpdf.text.pdf.PdfNull r1 = com.itextpdf.text.pdf.PdfNull.PDFNULL
            r3.set(r6, r1)
            int r6 = r6 + 1
            goto L_0x008d
        L_0x00c2:
            if (r13 != 0) goto L_0x00d4
            java.lang.Object[] r13 = new java.lang.Object[r2]
            r13[r4] = r3
            int r6 = r6 + 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            r13[r5] = r2
            r0.push(r13)
            goto L_0x00df
        L_0x00d4:
            int r6 = r6 + 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            r13[r5] = r2
            r0.push(r13)
        L_0x00df:
            r0.push(r1)
            goto L_0x0008
        L_0x00e4:
            int r3 = r7.length
            if (r6 >= r3) goto L_0x0008
            r3 = r7[r6]
            com.itextpdf.text.pdf.PdfObject r8 = r1.get(r3)
            boolean r9 = r8.isIndirect()
            if (r9 == 0) goto L_0x0118
            r9 = r8
            com.itextpdf.text.pdf.PRIndirectReference r9 = (com.itextpdf.text.pdf.PRIndirectReference) r9
            int r9 = r9.getNumber()
            if (r9 < 0) goto L_0x0110
            java.util.ArrayList<com.itextpdf.text.pdf.PdfObject> r10 = r12.xrefObj
            int r10 = r10.size()
            if (r9 >= r10) goto L_0x0110
            boolean r10 = r12.partial
            if (r10 != 0) goto L_0x0118
            java.util.ArrayList<com.itextpdf.text.pdf.PdfObject> r10 = r12.xrefObj
            java.lang.Object r9 = r10.get(r9)
            if (r9 != 0) goto L_0x0118
        L_0x0110:
            com.itextpdf.text.pdf.PdfNull r8 = com.itextpdf.text.pdf.PdfNull.PDFNULL
            r1.put(r3, r8)
            int r6 = r6 + 1
            goto L_0x00e4
        L_0x0118:
            if (r13 != 0) goto L_0x012d
            r13 = 3
            java.lang.Object[] r13 = new java.lang.Object[r13]
            r13[r4] = r7
            r13[r5] = r1
            int r6 = r6 + 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            r13[r2] = r1
            r0.push(r13)
            goto L_0x0138
        L_0x012d:
            int r6 = r6 + 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            r13[r2] = r1
            r0.push(r13)
        L_0x0138:
            r0.push(r8)
            goto L_0x0008
        L_0x013d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfReader.removeUnusedNode(com.itextpdf.text.pdf.PdfObject, boolean[]):void");
    }

    public int removeUnusedObjects() {
        boolean[] zArr = new boolean[this.xrefObj.size()];
        removeUnusedNode(this.trailer, zArr);
        int i = 0;
        if (this.partial) {
            for (int i2 = 1; i2 < zArr.length; i2++) {
                if (!zArr[i2]) {
                    int i3 = i2 * 2;
                    this.xref[i3] = -1;
                    this.xref[i3 + 1] = 0;
                    this.xrefObj.set(i2, (Object) null);
                    i++;
                }
            }
        } else {
            for (int i4 = 1; i4 < zArr.length; i4++) {
                if (!zArr[i4]) {
                    this.xrefObj.set(i4, (Object) null);
                    i++;
                }
            }
        }
        return i;
    }

    public AcroFields getAcroFields() {
        return new AcroFields(this, (PdfWriter) null);
    }

    public String getJavaScript(RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        PdfDictionary pdfDictionary;
        PdfObject pdfObjectRelease;
        PdfDictionary pdfDictionary2 = (PdfDictionary) getPdfObjectRelease(this.catalog.get(PdfName.NAMES));
        if (pdfDictionary2 == null || (pdfDictionary = (PdfDictionary) getPdfObjectRelease(pdfDictionary2.get(PdfName.JAVASCRIPT))) == null) {
            return null;
        }
        HashMap<String, PdfObject> readTree = PdfNameTree.readTree(pdfDictionary);
        String[] strArr = (String[]) readTree.keySet().toArray(new String[readTree.size()]);
        Arrays.sort(strArr);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : strArr) {
            PdfDictionary pdfDictionary3 = (PdfDictionary) getPdfObjectRelease(readTree.get(str));
            if (!(pdfDictionary3 == null || (pdfObjectRelease = getPdfObjectRelease(pdfDictionary3.get(PdfName.JS))) == null)) {
                if (pdfObjectRelease.isString()) {
                    stringBuffer.append(((PdfString) pdfObjectRelease).toUnicodeString());
                    stringBuffer.append(10);
                } else if (pdfObjectRelease.isStream()) {
                    byte[] streamBytes = getStreamBytes((PRStream) pdfObjectRelease, randomAccessFileOrArray);
                    if (streamBytes.length >= 2 && streamBytes[0] == -2 && streamBytes[1] == -1) {
                        stringBuffer.append(PdfEncodings.convertToString(streamBytes, PdfObject.TEXT_UNICODE));
                    } else {
                        stringBuffer.append(PdfEncodings.convertToString(streamBytes, PdfObject.TEXT_PDFDOCENCODING));
                    }
                    stringBuffer.append(10);
                }
            }
        }
        return stringBuffer.toString();
    }

    public String getJavaScript() throws IOException {
        RandomAccessFileOrArray safeFile = getSafeFile();
        try {
            safeFile.reOpen();
            return getJavaScript(safeFile);
        } finally {
            try {
                safeFile.close();
            } catch (Exception unused) {
            }
        }
    }

    public void selectPages(String str) {
        selectPages(SequenceList.expand(str, getNumberOfPages()));
    }

    public void selectPages(List<Integer> list) {
        selectPages(list, true);
    }

    /* access modifiers changed from: protected */
    public void selectPages(List<Integer> list, boolean z) {
        this.pageRefs.selectPages(list);
        if (z) {
            removeUnusedObjects();
        }
    }

    public void setViewerPreferences(int i) {
        this.viewerPreferences.setViewerPreferences(i);
        setViewerPreferences(this.viewerPreferences);
    }

    public void addViewerPreference(PdfName pdfName, PdfObject pdfObject) {
        this.viewerPreferences.addViewerPreference(pdfName, pdfObject);
        setViewerPreferences(this.viewerPreferences);
    }

    public void setViewerPreferences(PdfViewerPreferencesImp pdfViewerPreferencesImp) {
        pdfViewerPreferencesImp.addToCatalog(this.catalog);
    }

    public int getSimpleViewerPreferences() {
        return PdfViewerPreferencesImp.getViewerPreferences(this.catalog).getPageLayoutAndMode();
    }

    public boolean isAppendable() {
        return this.appendable;
    }

    public void setAppendable(boolean z) {
        this.appendable = z;
        if (z) {
            getPdfObject(this.trailer.get(PdfName.ROOT));
        }
    }

    public boolean isNewXrefType() {
        return this.newXrefType;
    }

    public long getFileLength() {
        return this.fileLength;
    }

    public boolean isHybridXref() {
        return this.hybridXref;
    }

    static class PageRefs {
        private boolean keepPages;
        private int lastPageRead;
        private ArrayList<PdfDictionary> pageInh;
        private Set<PdfObject> pagesNodes;
        private final PdfReader reader;
        private ArrayList<PRIndirectReference> refsn;
        private IntHashtable refsp;
        private int sizep;

        private PageRefs(PdfReader pdfReader) throws IOException {
            this.lastPageRead = -1;
            this.pagesNodes = new HashSet();
            this.reader = pdfReader;
            if (pdfReader.partial) {
                this.refsp = new IntHashtable();
                this.sizep = ((PdfNumber) PdfReader.getPdfObjectRelease(pdfReader.rootPages.get(PdfName.COUNT))).intValue();
                return;
            }
            readPages();
        }

        PageRefs(PageRefs pageRefs, PdfReader pdfReader) {
            this.lastPageRead = -1;
            this.pagesNodes = new HashSet();
            this.reader = pdfReader;
            this.sizep = pageRefs.sizep;
            if (pageRefs.refsn != null) {
                this.refsn = new ArrayList<>(pageRefs.refsn);
                for (int i = 0; i < this.refsn.size(); i++) {
                    this.refsn.set(i, (PRIndirectReference) PdfReader.duplicatePdfObject(this.refsn.get(i), pdfReader));
                }
                return;
            }
            this.refsp = (IntHashtable) pageRefs.refsp.clone();
        }

        /* access modifiers changed from: package-private */
        public int size() {
            if (this.refsn != null) {
                return this.refsn.size();
            }
            return this.sizep;
        }

        /* access modifiers changed from: package-private */
        public void readPages() throws IOException {
            if (this.refsn == null) {
                this.refsp = null;
                this.refsn = new ArrayList<>();
                this.pageInh = new ArrayList<>();
                iteratePages((PRIndirectReference) this.reader.catalog.get(PdfName.PAGES));
                this.pageInh = null;
                this.reader.rootPages.put(PdfName.COUNT, new PdfNumber(this.refsn.size()));
            }
        }

        /* access modifiers changed from: package-private */
        public void reReadPages() throws IOException {
            this.refsn = null;
            readPages();
        }

        public PdfDictionary getPageN(int i) {
            return (PdfDictionary) PdfReader.getPdfObject((PdfObject) getPageOrigRef(i));
        }

        public PdfDictionary getPageNRelease(int i) {
            PdfDictionary pageN = getPageN(i);
            releasePage(i);
            return pageN;
        }

        public PRIndirectReference getPageOrigRefRelease(int i) {
            PRIndirectReference pageOrigRef = getPageOrigRef(i);
            releasePage(i);
            return pageOrigRef;
        }

        public PRIndirectReference getPageOrigRef(int i) {
            int i2 = i - 1;
            if (i2 < 0) {
                return null;
            }
            try {
                if (i2 >= size()) {
                    return null;
                }
                if (this.refsn != null) {
                    return this.refsn.get(i2);
                }
                int i3 = this.refsp.get(i2);
                if (i3 == 0) {
                    PRIndirectReference singlePage = getSinglePage(i2);
                    if (this.reader.lastXrefPartial == -1) {
                        this.lastPageRead = -1;
                    } else {
                        this.lastPageRead = i2;
                    }
                    int unused = this.reader.lastXrefPartial = -1;
                    this.refsp.put(i2, singlePage.getNumber());
                    if (this.keepPages) {
                        this.lastPageRead = -1;
                    }
                    return singlePage;
                }
                if (this.lastPageRead != i2) {
                    this.lastPageRead = -1;
                }
                if (this.keepPages) {
                    this.lastPageRead = -1;
                }
                return new PRIndirectReference(this.reader, i3);
            } catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        }

        /* access modifiers changed from: package-private */
        public void keepPages() {
            if (this.refsp != null && !this.keepPages) {
                this.keepPages = true;
                this.refsp.clear();
            }
        }

        public void releasePage(int i) {
            int i2;
            if (this.refsp != null && i - 1 >= 0 && i2 < size() && i2 == this.lastPageRead) {
                this.lastPageRead = -1;
                int unused = this.reader.lastXrefPartial = this.refsp.get(i2);
                this.reader.releaseLastXrefPartial();
                this.refsp.remove(i2);
            }
        }

        public void resetReleasePage() {
            if (this.refsp != null) {
                this.lastPageRead = -1;
            }
        }

        /* access modifiers changed from: package-private */
        public void insertPage(int i, PRIndirectReference pRIndirectReference) {
            int i2 = i - 1;
            if (this.refsn == null) {
                this.sizep++;
                this.lastPageRead = -1;
                if (i2 >= size()) {
                    this.refsp.put(size(), pRIndirectReference.getNumber());
                    return;
                }
                IntHashtable intHashtable = new IntHashtable((this.refsp.size() + 1) * 2);
                Iterator<IntHashtable.Entry> entryIterator = this.refsp.getEntryIterator();
                while (entryIterator.hasNext()) {
                    IntHashtable.Entry next = entryIterator.next();
                    int key = next.getKey();
                    if (key >= i2) {
                        key++;
                    }
                    intHashtable.put(key, next.getValue());
                }
                intHashtable.put(i2, pRIndirectReference.getNumber());
                this.refsp = intHashtable;
            } else if (i2 >= this.refsn.size()) {
                this.refsn.add(pRIndirectReference);
            } else {
                this.refsn.add(i2, pRIndirectReference);
            }
        }

        private void pushPageAttributes(PdfDictionary pdfDictionary) {
            PdfDictionary pdfDictionary2 = new PdfDictionary();
            if (!this.pageInh.isEmpty()) {
                pdfDictionary2.putAll(this.pageInh.get(this.pageInh.size() - 1));
            }
            for (int i = 0; i < PdfReader.pageInhCandidates.length; i++) {
                PdfObject pdfObject = pdfDictionary.get(PdfReader.pageInhCandidates[i]);
                if (pdfObject != null) {
                    pdfDictionary2.put(PdfReader.pageInhCandidates[i], pdfObject);
                }
            }
            this.pageInh.add(pdfDictionary2);
        }

        private void popPageAttributes() {
            this.pageInh.remove(this.pageInh.size() - 1);
        }

        private void iteratePages(PRIndirectReference pRIndirectReference) throws IOException {
            int i = 0;
            if (!this.pagesNodes.add(PdfReader.getPdfObject((PdfObject) pRIndirectReference))) {
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("illegal.pages.tree", new Object[0]));
            }
            PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObject((PdfObject) pRIndirectReference);
            if (pdfDictionary != null) {
                PdfArray asArray = pdfDictionary.getAsArray(PdfName.KIDS);
                if (asArray == null) {
                    pdfDictionary.put(PdfName.TYPE, PdfName.PAGE);
                    PdfDictionary pdfDictionary2 = this.pageInh.get(this.pageInh.size() - 1);
                    for (PdfName next : pdfDictionary2.getKeys()) {
                        if (pdfDictionary.get(next) == null) {
                            pdfDictionary.put(next, pdfDictionary2.get(next));
                        }
                    }
                    if (pdfDictionary.get(PdfName.MEDIABOX) == null) {
                        pdfDictionary.put(PdfName.MEDIABOX, new PdfArray(new float[]{0.0f, 0.0f, PageSize.LETTER.getRight(), PageSize.LETTER.getTop()}));
                    }
                    this.refsn.add(pRIndirectReference);
                    return;
                }
                pdfDictionary.put(PdfName.TYPE, PdfName.PAGES);
                pushPageAttributes(pdfDictionary);
                while (true) {
                    if (i >= asArray.size()) {
                        break;
                    }
                    PdfObject pdfObject = asArray.getPdfObject(i);
                    if (!pdfObject.isIndirect()) {
                        while (i < asArray.size()) {
                            asArray.remove(i);
                        }
                    } else {
                        iteratePages((PRIndirectReference) pdfObject);
                        i++;
                    }
                }
                popPageAttributes();
            }
        }

        /* access modifiers changed from: protected */
        public PRIndirectReference getSinglePage(int i) {
            PdfDictionary pdfDictionary = new PdfDictionary();
            PdfDictionary pdfDictionary2 = this.reader.rootPages;
            int i2 = 0;
            while (true) {
                for (int i3 = 0; i3 < PdfReader.pageInhCandidates.length; i3++) {
                    PdfObject pdfObject = pdfDictionary2.get(PdfReader.pageInhCandidates[i3]);
                    if (pdfObject != null) {
                        pdfDictionary.put(PdfReader.pageInhCandidates[i3], pdfObject);
                    }
                }
                ListIterator<PdfObject> listIterator = ((PdfArray) PdfReader.getPdfObjectRelease(pdfDictionary2.get(PdfName.KIDS))).listIterator();
                while (true) {
                    if (!listIterator.hasNext()) {
                        break;
                    }
                    PRIndirectReference pRIndirectReference = (PRIndirectReference) listIterator.next();
                    PdfDictionary pdfDictionary3 = (PdfDictionary) PdfReader.getPdfObject((PdfObject) pRIndirectReference);
                    int access$300 = this.reader.lastXrefPartial;
                    PdfObject pdfObjectRelease = PdfReader.getPdfObjectRelease(pdfDictionary3.get(PdfName.COUNT));
                    int unused = this.reader.lastXrefPartial = access$300;
                    int intValue = ((pdfObjectRelease == null || pdfObjectRelease.type() != 2) ? 1 : ((PdfNumber) pdfObjectRelease).intValue()) + i2;
                    if (i >= intValue) {
                        this.reader.releaseLastXrefPartial();
                        i2 = intValue;
                    } else if (pdfObjectRelease == null) {
                        pdfDictionary3.mergeDifferent(pdfDictionary);
                        return pRIndirectReference;
                    } else {
                        this.reader.releaseLastXrefPartial();
                        pdfDictionary2 = pdfDictionary3;
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public void selectPages(List<Integer> list) {
            IntHashtable intHashtable = new IntHashtable();
            ArrayList arrayList = new ArrayList();
            int size = size();
            for (Integer next : list) {
                int intValue = next.intValue();
                if (intValue >= 1 && intValue <= size && intHashtable.put(intValue, 1) == 0) {
                    arrayList.add(next);
                }
            }
            if (this.reader.partial) {
                for (int i = 1; i <= size; i++) {
                    getPageOrigRef(i);
                    resetReleasePage();
                }
            }
            PRIndirectReference pRIndirectReference = (PRIndirectReference) this.reader.catalog.get(PdfName.PAGES);
            PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObject((PdfObject) pRIndirectReference);
            ArrayList<PRIndirectReference> arrayList2 = new ArrayList<>(arrayList.size());
            PdfArray pdfArray = new PdfArray();
            boolean z = false;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                int intValue2 = ((Integer) arrayList.get(i2)).intValue();
                PRIndirectReference pageOrigRef = getPageOrigRef(intValue2);
                resetReleasePage();
                pdfArray.add((PdfObject) pageOrigRef);
                arrayList2.add(pageOrigRef);
                getPageN(intValue2).put(PdfName.PARENT, pRIndirectReference);
            }
            AcroFields acroFields = this.reader.getAcroFields();
            if (acroFields.getFields().size() > 0) {
                z = true;
            }
            for (int i3 = 1; i3 <= size; i3++) {
                if (!intHashtable.containsKey(i3)) {
                    if (z) {
                        acroFields.removeFieldsFromPage(i3);
                    }
                    int number = getPageOrigRef(i3).getNumber();
                    this.reader.xrefObj.set(number, (Object) null);
                    if (this.reader.partial) {
                        int i4 = number * 2;
                        this.reader.xref[i4] = -1;
                        this.reader.xref[i4 + 1] = 0;
                    }
                }
            }
            pdfDictionary.put(PdfName.COUNT, new PdfNumber(arrayList.size()));
            pdfDictionary.put(PdfName.KIDS, pdfArray);
            this.refsp = null;
            this.refsn = arrayList2;
        }
    }

    /* access modifiers changed from: package-private */
    public PdfIndirectReference getCryptoRef() {
        if (this.cryptoRef == null) {
            return null;
        }
        return new PdfIndirectReference(0, this.cryptoRef.getNumber(), this.cryptoRef.getGeneration());
    }

    public boolean hasUsageRights() {
        PdfDictionary asDict = this.catalog.getAsDict(PdfName.PERMS);
        if (asDict == null) {
            return false;
        }
        if (asDict.contains(PdfName.UR) || asDict.contains(PdfName.UR3)) {
            return true;
        }
        return false;
    }

    public void removeUsageRights() {
        PdfDictionary asDict = this.catalog.getAsDict(PdfName.PERMS);
        if (asDict != null) {
            asDict.remove(PdfName.UR);
            asDict.remove(PdfName.UR3);
            if (asDict.size() == 0) {
                this.catalog.remove(PdfName.PERMS);
            }
        }
    }

    public int getCertificationLevel() {
        PdfDictionary asDict;
        PdfArray asArray;
        PdfDictionary asDict2;
        PdfDictionary asDict3;
        PdfNumber asNumber;
        PdfDictionary asDict4 = this.catalog.getAsDict(PdfName.PERMS);
        if (asDict4 == null || (asDict = asDict4.getAsDict(PdfName.DOCMDP)) == null || (asArray = asDict.getAsArray(PdfName.REFERENCE)) == null || asArray.size() == 0 || (asDict2 = asArray.getAsDict(0)) == null || (asDict3 = asDict2.getAsDict(PdfName.TRANSFORMPARAMS)) == null || (asNumber = asDict3.getAsNumber(PdfName.P)) == null) {
            return 0;
        }
        return asNumber.intValue();
    }

    public final boolean isOpenedWithFullPermissions() {
        return !this.encrypted || this.ownerPasswordUsed || unethicalreading;
    }

    public int getCryptoMode() {
        if (this.decrypt == null) {
            return -1;
        }
        return this.decrypt.getCryptoMode();
    }

    public boolean isMetadataEncrypted() {
        if (this.decrypt == null) {
            return false;
        }
        return this.decrypt.isMetadataEncrypted();
    }

    public byte[] computeUserPassword() {
        if (!this.encrypted || !this.ownerPasswordUsed) {
            return null;
        }
        return this.decrypt.computeUserPassword(this.password);
    }
}
