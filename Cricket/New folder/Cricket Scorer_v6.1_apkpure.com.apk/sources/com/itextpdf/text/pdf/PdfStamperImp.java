package com.itextpdf.text.pdf;

import com.itextpdf.awt.geom.Point;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Version;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.exceptions.BadPasswordException;
import com.itextpdf.text.log.Counter;
import com.itextpdf.text.log.CounterFactory;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.collection.PdfCollection;
import com.itextpdf.text.pdf.internal.PdfViewerPreferencesImp;
import com.itextpdf.text.xml.xmp.PdfProperties;
import com.itextpdf.text.xml.xmp.XmpBasicProperties;
import com.itextpdf.xmp.XMPException;
import com.itextpdf.xmp.XMPMeta;
import com.itextpdf.xmp.XMPMetaFactory;
import com.itextpdf.xmp.options.SerializeOptions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

class PdfStamperImp extends PdfWriter {
    protected Counter COUNTER = CounterFactory.getCounter(PdfStamper.class);
    private double[] DEFAULT_MATRIX = {1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d};
    protected AcroFields acroFields;
    protected boolean append;
    protected boolean closed = false;
    protected HashSet<PdfTemplate> fieldTemplates = new HashSet<>();
    protected boolean fieldsAdded = false;
    protected RandomAccessFileOrArray file;
    protected boolean flat = false;
    protected boolean flatFreeText = false;
    protected boolean flatannotations = false;
    protected int initialXrefSize;
    protected IntHashtable marked;
    IntHashtable myXref = new IntHashtable();
    protected int[] namePtr = {0};
    protected HashMap<Object, PdfObject> namedDestinations = new HashMap<>();
    protected PdfAction openAction;
    private boolean originalLayersAreRead = false;
    HashMap<PdfDictionary, PageStamp> pagesToContent = new HashMap<>();
    protected HashSet<String> partialFlattening = new HashSet<>();
    PdfReader reader;
    HashMap<PdfReader, RandomAccessFileOrArray> readers2file = new HashMap<>();
    HashMap<PdfReader, IntHashtable> readers2intrefs = new HashMap<>();
    private boolean rotateContents = true;
    protected int sigFlags = 0;
    protected boolean useVp = false;
    protected PdfViewerPreferencesImp viewerPreferences = new PdfViewerPreferencesImp();

    /* access modifiers changed from: protected */
    public Counter getCounter() {
        return this.COUNTER;
    }

    protected PdfStamperImp(PdfReader pdfReader, OutputStream outputStream, char c, boolean z) throws DocumentException, IOException {
        super(new PdfDocument(), outputStream);
        if (!pdfReader.isOpenedWithFullPermissions()) {
            throw new BadPasswordException(MessageLocalization.getComposedMessage("pdfreader.not.opened.with.owner.password", new Object[0]));
        } else if (pdfReader.isTampered()) {
            throw new DocumentException(MessageLocalization.getComposedMessage("the.original.document.was.reused.read.it.again.from.file", new Object[0]));
        } else {
            pdfReader.setTampered(true);
            this.reader = pdfReader;
            this.file = pdfReader.getSafeFile();
            this.append = z;
            if (pdfReader.isEncrypted() && (z || PdfReader.unethicalreading)) {
                this.crypto = new PdfEncryption(pdfReader.getDecrypt());
            }
            if (z) {
                if (pdfReader.isRebuilt()) {
                    throw new DocumentException(MessageLocalization.getComposedMessage("append.mode.requires.a.document.without.errors.even.if.recovery.was.possible", new Object[0]));
                }
                this.pdf_version.setAppendmode(true);
                if (c == 0) {
                    this.pdf_version.setPdfVersion(pdfReader.getPdfVersion());
                } else {
                    this.pdf_version.setPdfVersion(c);
                }
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = this.file.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    this.os.write(bArr, 0, read);
                }
                this.prevxref = pdfReader.getLastXref();
                pdfReader.setAppendable(true);
            } else if (c == 0) {
                super.setPdfVersion(pdfReader.getPdfVersion());
            } else {
                super.setPdfVersion(c);
            }
            if (pdfReader.isTagged()) {
                setTagged();
            }
            super.open();
            this.pdf.addWriter(this);
            if (z) {
                this.body.setRefnum(pdfReader.getXrefSize());
                this.marked = new IntHashtable();
                if (pdfReader.isNewXrefType()) {
                    this.fullCompression = true;
                }
                if (pdfReader.isHybridXref()) {
                    this.fullCompression = false;
                }
            }
            this.initialXrefSize = pdfReader.getXrefSize();
            readColorProfile();
        }
    }

    /* access modifiers changed from: protected */
    public void readColorProfile() {
        PdfArray asArray = this.reader.getCatalog().getAsArray(PdfName.OUTPUTINTENTS);
        if (asArray != null) {
            PdfArray pdfArray = asArray;
            if (pdfArray.size() > 0) {
                PdfStream pdfStream = null;
                int i = 0;
                while (i < pdfArray.size() && ((r3 = pdfArray.getAsDict(i)) == null || (pdfStream = r3.getAsStream(PdfName.DESTOUTPUTPROFILE)) == null)) {
                    i++;
                }
                if (pdfStream instanceof PRStream) {
                    try {
                        this.colorProfile = ICC_Profile.getInstance(PdfReader.getStreamBytes((PRStream) pdfStream));
                    } catch (IOException e) {
                        throw new ExceptionConverter(e);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setViewerPreferences() {
        this.reader.setViewerPreferences(this.viewerPreferences);
        markUsed(this.reader.getTrailer().get(PdfName.ROOT));
    }

    /* access modifiers changed from: protected */
    public void close(Map<String, String> map) throws IOException {
        String str;
        PdfIndirectReference pdfIndirectReference;
        byte[] bArr;
        StringBuffer stringBuffer;
        if (!this.closed) {
            if (this.useVp) {
                setViewerPreferences();
            }
            if (this.flat) {
                flatFields();
            }
            if (this.flatFreeText) {
                flatFreeTextFields();
            }
            if (this.flatannotations) {
                flattenAnnotations();
            }
            addFieldResources();
            PdfDictionary catalog = this.reader.getCatalog();
            getPdfVersion().addToCatalog(catalog);
            PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObject(catalog.get(PdfName.ACROFORM), this.reader.getCatalog());
            if (this.acroFields != null && this.acroFields.getXfa().isChanged()) {
                markUsed((PdfObject) pdfDictionary);
                if (!this.flat) {
                    this.acroFields.getXfa().setXfa(this);
                }
            }
            if (!(this.sigFlags == 0 || pdfDictionary == null)) {
                pdfDictionary.put(PdfName.SIGFLAGS, new PdfNumber(this.sigFlags));
                markUsed((PdfObject) pdfDictionary);
                markUsed((PdfObject) catalog);
            }
            this.closed = true;
            addSharedObjectsToBody();
            setOutlines();
            setJavaScript();
            addFileAttachments();
            if (this.extraCatalog != null) {
                catalog.mergeDifferent(this.extraCatalog);
            }
            if (this.openAction != null) {
                catalog.put(PdfName.OPENACTION, this.openAction);
            }
            if (this.pdf.pageLabels != null) {
                catalog.put(PdfName.PAGELABELS, this.pdf.pageLabels.getDictionary(this));
            }
            if (!this.documentOCG.isEmpty()) {
                fillOCProperties(false);
                PdfDictionary asDict = catalog.getAsDict(PdfName.OCPROPERTIES);
                if (asDict == null) {
                    this.reader.getCatalog().put(PdfName.OCPROPERTIES, this.OCProperties);
                } else {
                    asDict.put(PdfName.OCGS, this.OCProperties.get(PdfName.OCGS));
                    PdfDictionary asDict2 = asDict.getAsDict(PdfName.D);
                    if (asDict2 == null) {
                        asDict2 = new PdfDictionary();
                        asDict.put(PdfName.D, asDict2);
                    }
                    asDict2.put(PdfName.ORDER, this.OCProperties.getAsDict(PdfName.D).get(PdfName.ORDER));
                    asDict2.put(PdfName.RBGROUPS, this.OCProperties.getAsDict(PdfName.D).get(PdfName.RBGROUPS));
                    asDict2.put(PdfName.OFF, this.OCProperties.getAsDict(PdfName.D).get(PdfName.OFF));
                    asDict2.put(PdfName.AS, this.OCProperties.getAsDict(PdfName.D).get(PdfName.AS));
                }
                PdfWriter.checkPdfIsoConformance(this, 7, this.OCProperties);
            }
            PdfIndirectReference asIndirectObject = this.reader.getTrailer().getAsIndirectObject(PdfName.INFO);
            int number = asIndirectObject != null ? asIndirectObject.getNumber() : -1;
            PdfDictionary asDict3 = this.reader.getTrailer().getAsDict(PdfName.INFO);
            PdfStream pdfStream = null;
            String unicodeString = (asDict3 == null || asDict3.get(PdfName.PRODUCER) == null) ? null : asDict3.getAsString(PdfName.PRODUCER).toUnicodeString();
            Version instance = Version.getInstance();
            if (unicodeString == null || instance.getVersion().indexOf(instance.getProduct()) == -1) {
                str = instance.getVersion();
            } else {
                int indexOf = unicodeString.indexOf("; modified using");
                if (indexOf == -1) {
                    stringBuffer = new StringBuffer(unicodeString);
                } else {
                    stringBuffer = new StringBuffer(unicodeString.substring(0, indexOf));
                }
                stringBuffer.append("; modified using ");
                stringBuffer.append(instance.getVersion());
                str = stringBuffer.toString();
            }
            PdfDictionary pdfDictionary2 = new PdfDictionary();
            if (asDict3 != null) {
                for (PdfName next : asDict3.getKeys()) {
                    pdfDictionary2.put(next, PdfReader.getPdfObject(asDict3.get(next)));
                }
            }
            if (map != null) {
                for (Map.Entry next2 : map.entrySet()) {
                    PdfName pdfName = new PdfName((String) next2.getKey());
                    String str2 = (String) next2.getValue();
                    if (str2 == null) {
                        pdfDictionary2.remove(pdfName);
                    } else {
                        pdfDictionary2.put(pdfName, new PdfString(str2, PdfObject.TEXT_UNICODE));
                    }
                }
            }
            PdfDate pdfDate = new PdfDate();
            pdfDictionary2.put(PdfName.MODDATE, pdfDate);
            pdfDictionary2.put(PdfName.PRODUCER, new PdfString(str, PdfObject.TEXT_UNICODE));
            if (!this.append) {
                pdfIndirectReference = addToBody((PdfObject) pdfDictionary2, false).getIndirectReference();
            } else if (asIndirectObject == null) {
                pdfIndirectReference = addToBody((PdfObject) pdfDictionary2, false).getIndirectReference();
            } else {
                pdfIndirectReference = addToBody((PdfObject) pdfDictionary2, asIndirectObject.getNumber(), false).getIndirectReference();
            }
            PdfObject pdfObject = PdfReader.getPdfObject(catalog.get(PdfName.METADATA));
            if (pdfObject == null || !pdfObject.isStream()) {
                bArr = null;
            } else {
                bArr = PdfReader.getStreamBytesRaw((PRStream) pdfObject);
                PdfReader.killIndirect(catalog.get(PdfName.METADATA));
            }
            if (this.xmpMetadata != null) {
                bArr = this.xmpMetadata;
            } else if (this.xmpWriter != null) {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    PdfProperties.setProducer(this.xmpWriter.getXmpMeta(), str);
                    XmpBasicProperties.setModDate(this.xmpWriter.getXmpMeta(), pdfDate.getW3CDate());
                    XmpBasicProperties.setMetaDataDate(this.xmpWriter.getXmpMeta(), pdfDate.getW3CDate());
                    this.xmpWriter.serialize(byteArrayOutputStream);
                    this.xmpWriter.close();
                    pdfStream = new PdfStream(byteArrayOutputStream.toByteArray());
                } catch (XMPException unused) {
                    this.xmpWriter = null;
                }
            }
            if (pdfStream == null && bArr != null) {
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    if (map != null) {
                        if (this.xmpMetadata == null) {
                            createXmpWriter(byteArrayOutputStream2, pdfDictionary2).close();
                            pdfStream = new PdfStream(byteArrayOutputStream2.toByteArray());
                        }
                    }
                    XMPMeta parseFromBuffer = XMPMetaFactory.parseFromBuffer(bArr);
                    PdfProperties.setProducer(parseFromBuffer, str);
                    XmpBasicProperties.setModDate(parseFromBuffer, pdfDate.getW3CDate());
                    XmpBasicProperties.setMetaDataDate(parseFromBuffer, pdfDate.getW3CDate());
                    SerializeOptions serializeOptions = new SerializeOptions();
                    serializeOptions.setPadding(2000);
                    XMPMetaFactory.serialize(parseFromBuffer, byteArrayOutputStream2, serializeOptions);
                    pdfStream = new PdfStream(byteArrayOutputStream2.toByteArray());
                } catch (XMPException unused2) {
                    pdfStream = new PdfStream(bArr);
                } catch (IOException unused3) {
                    pdfStream = new PdfStream(bArr);
                }
            }
            if (pdfStream != null) {
                pdfStream.put(PdfName.TYPE, PdfName.METADATA);
                pdfStream.put(PdfName.SUBTYPE, PdfName.XML);
                if (this.crypto != null && !this.crypto.isMetadataEncrypted()) {
                    PdfArray pdfArray = new PdfArray();
                    pdfArray.add((PdfObject) PdfName.CRYPT);
                    pdfStream.put(PdfName.FILTER, pdfArray);
                }
                if (!this.append || pdfObject == null) {
                    catalog.put(PdfName.METADATA, this.body.add(pdfStream).getIndirectReference());
                    markUsed((PdfObject) catalog);
                } else {
                    this.body.add((PdfObject) pdfStream, (PdfIndirectReference) pdfObject.getIndRef());
                }
            }
            if (!this.namedDestinations.isEmpty()) {
                updateNamedDestinations();
            }
            close(pdfIndirectReference, number);
        }
    }

    /* access modifiers changed from: protected */
    public void close(PdfIndirectReference pdfIndirectReference, int i) throws IOException {
        PdfObject pdfObject;
        int i2 = i;
        alterContents();
        int number = ((PRIndirectReference) this.reader.trailer.get(PdfName.ROOT)).getNumber();
        if (this.append) {
            int[] keys = this.marked.getKeys();
            for (int i3 = 0; i3 < keys.length; i3++) {
                int i4 = keys[i3];
                PdfObject pdfObjectRelease = this.reader.getPdfObjectRelease(i4);
                if (!(pdfObjectRelease == null || i2 == i4 || i4 >= this.initialXrefSize)) {
                    addToBody(pdfObjectRelease, (PdfIndirectReference) pdfObjectRelease.getIndRef(), i4 != number);
                }
            }
            for (int i5 = this.initialXrefSize; i5 < this.reader.getXrefSize(); i5++) {
                PdfObject pdfObject2 = this.reader.getPdfObject(i5);
                if (pdfObject2 != null) {
                    addToBody(pdfObject2, getNewObjectNumber(this.reader, i5, 0));
                }
            }
        } else {
            int i6 = 1;
            while (i6 < this.reader.getXrefSize()) {
                PdfObject pdfObjectRelease2 = this.reader.getPdfObjectRelease(i6);
                if (!(pdfObjectRelease2 == null || i2 == i6)) {
                    addToBody(pdfObjectRelease2, getNewObjectNumber(this.reader, i6, 0), i6 != number);
                }
                i6++;
            }
        }
        PdfIndirectReference pdfIndirectReference2 = null;
        if (this.crypto != null) {
            if (this.append) {
                pdfIndirectReference2 = this.reader.getCryptoRef();
            } else {
                pdfIndirectReference2 = addToBody((PdfObject) this.crypto.getEncryptionDictionary(), false).getIndirectReference();
            }
            pdfObject = this.crypto.getFileID(true);
        } else {
            PdfArray asArray = this.reader.trailer.getAsArray(PdfName.ID);
            if (asArray == null || asArray.getAsString(0) == null) {
                pdfObject = PdfEncryption.createInfoId(PdfEncryption.createDocumentId(), true);
            } else {
                pdfObject = PdfEncryption.createInfoId(asArray.getAsString(0).getBytes(), true);
            }
        }
        PdfIndirectReference pdfIndirectReference3 = new PdfIndirectReference(0, getNewObjectNumber(this.reader, ((PRIndirectReference) this.reader.trailer.get(PdfName.ROOT)).getNumber(), 0));
        this.body.writeCrossReferenceTable(this.os, pdfIndirectReference3, pdfIndirectReference, pdfIndirectReference2, pdfObject, this.prevxref);
        if (this.fullCompression) {
            writeKeyInfo(this.os);
            this.os.write(getISOBytes("startxref\n"));
            this.os.write(getISOBytes(String.valueOf(this.body.offset())));
            this.os.write(getISOBytes("\n%%EOF\n"));
        } else {
            new PdfWriter.PdfTrailer(this.body.size(), this.body.offset(), pdfIndirectReference3, pdfIndirectReference, pdfIndirectReference2, pdfObject, this.prevxref).toPdf(this, this.os);
        }
        this.os.flush();
        if (isCloseStream()) {
            this.os.close();
        }
        getCounter().written(this.os.getCounter());
    }

    /* access modifiers changed from: package-private */
    public void applyRotation(PdfDictionary pdfDictionary, ByteBuffer byteBuffer) {
        if (this.rotateContents) {
            Rectangle pageSizeWithRotation = this.reader.getPageSizeWithRotation(pdfDictionary);
            int rotation = pageSizeWithRotation.getRotation();
            if (rotation == 90) {
                byteBuffer.append(PdfContents.ROTATE90);
                byteBuffer.append(pageSizeWithRotation.getTop());
                byteBuffer.append(' ').append('0').append(PdfContents.ROTATEFINAL);
            } else if (rotation == 180) {
                byteBuffer.append(PdfContents.ROTATE180);
                byteBuffer.append(pageSizeWithRotation.getRight());
                byteBuffer.append(' ');
                byteBuffer.append(pageSizeWithRotation.getTop());
                byteBuffer.append(PdfContents.ROTATEFINAL);
            } else if (rotation == 270) {
                byteBuffer.append(PdfContents.ROTATE270);
                byteBuffer.append('0').append(' ');
                byteBuffer.append(pageSizeWithRotation.getRight());
                byteBuffer.append(PdfContents.ROTATEFINAL);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void alterContents() throws IOException {
        PdfArray pdfArray;
        for (PageStamp next : this.pagesToContent.values()) {
            PdfDictionary pdfDictionary = next.pageN;
            markUsed((PdfObject) pdfDictionary);
            PdfObject pdfObject = PdfReader.getPdfObject(pdfDictionary.get(PdfName.CONTENTS), pdfDictionary);
            if (pdfObject == null) {
                pdfArray = new PdfArray();
                pdfDictionary.put(PdfName.CONTENTS, pdfArray);
            } else if (pdfObject.isArray()) {
                PdfArray pdfArray2 = new PdfArray((PdfArray) pdfObject);
                pdfDictionary.put(PdfName.CONTENTS, pdfArray2);
                pdfArray = pdfArray2;
            } else if (pdfObject.isStream()) {
                pdfArray = new PdfArray();
                pdfArray.add(pdfDictionary.get(PdfName.CONTENTS));
                pdfDictionary.put(PdfName.CONTENTS, pdfArray);
            } else {
                pdfArray = new PdfArray();
                pdfDictionary.put(PdfName.CONTENTS, pdfArray);
            }
            ByteBuffer byteBuffer = new ByteBuffer();
            if (next.under != null) {
                byteBuffer.append(PdfContents.SAVESTATE);
                applyRotation(pdfDictionary, byteBuffer);
                byteBuffer.append(next.under.getInternalBuffer());
                byteBuffer.append(PdfContents.RESTORESTATE);
            }
            if (next.over != null) {
                byteBuffer.append(PdfContents.SAVESTATE);
            }
            PdfStream pdfStream = new PdfStream(byteBuffer.toByteArray());
            pdfStream.flateCompress(this.compressionLevel);
            pdfArray.addFirst(addToBody(pdfStream).getIndirectReference());
            byteBuffer.reset();
            if (next.over != null) {
                byteBuffer.append(' ');
                byteBuffer.append(PdfContents.RESTORESTATE);
                ByteBuffer internalBuffer = next.over.getInternalBuffer();
                byteBuffer.append(internalBuffer.getBuffer(), 0, next.replacePoint);
                byteBuffer.append(PdfContents.SAVESTATE);
                applyRotation(pdfDictionary, byteBuffer);
                byteBuffer.append(internalBuffer.getBuffer(), next.replacePoint, internalBuffer.size() - next.replacePoint);
                byteBuffer.append(PdfContents.RESTORESTATE);
                PdfStream pdfStream2 = new PdfStream(byteBuffer.toByteArray());
                pdfStream2.flateCompress(this.compressionLevel);
                pdfArray.add((PdfObject) addToBody(pdfStream2).getIndirectReference());
            }
            alterResources(next);
        }
    }

    /* access modifiers changed from: package-private */
    public void alterResources(PageStamp pageStamp) {
        pageStamp.pageN.put(PdfName.RESOURCES, pageStamp.pageResources.getResources());
    }

    /* access modifiers changed from: protected */
    public int getNewObjectNumber(PdfReader pdfReader, int i, int i2) {
        IntHashtable intHashtable = this.readers2intrefs.get(pdfReader);
        if (intHashtable != null) {
            int i3 = intHashtable.get(i);
            if (i3 != 0) {
                return i3;
            }
            int indirectReferenceNumber = getIndirectReferenceNumber();
            intHashtable.put(i, indirectReferenceNumber);
            return indirectReferenceNumber;
        } else if (this.currentPdfReaderInstance != null) {
            return this.currentPdfReaderInstance.getNewObjectNumber(i, i2);
        } else {
            if (this.append && i < this.initialXrefSize) {
                return i;
            }
            int i4 = this.myXref.get(i);
            if (i4 != 0) {
                return i4;
            }
            int indirectReferenceNumber2 = getIndirectReferenceNumber();
            this.myXref.put(i, indirectReferenceNumber2);
            return indirectReferenceNumber2;
        }
    }

    /* access modifiers changed from: package-private */
    public RandomAccessFileOrArray getReaderFile(PdfReader pdfReader) {
        if (this.readers2intrefs.containsKey(pdfReader)) {
            RandomAccessFileOrArray randomAccessFileOrArray = this.readers2file.get(pdfReader);
            if (randomAccessFileOrArray != null) {
                return randomAccessFileOrArray;
            }
            return pdfReader.getSafeFile();
        } else if (this.currentPdfReaderInstance == null) {
            return this.file;
        } else {
            return this.currentPdfReaderInstance.getReaderFile();
        }
    }

    public void registerReader(PdfReader pdfReader, boolean z) throws IOException {
        if (!this.readers2intrefs.containsKey(pdfReader)) {
            this.readers2intrefs.put(pdfReader, new IntHashtable());
            if (z) {
                RandomAccessFileOrArray safeFile = pdfReader.getSafeFile();
                this.readers2file.put(pdfReader, safeFile);
                safeFile.reOpen();
            }
        }
    }

    public void unRegisterReader(PdfReader pdfReader) {
        if (this.readers2intrefs.containsKey(pdfReader)) {
            this.readers2intrefs.remove(pdfReader);
            RandomAccessFileOrArray randomAccessFileOrArray = this.readers2file.get(pdfReader);
            if (randomAccessFileOrArray != null) {
                this.readers2file.remove(pdfReader);
                try {
                    randomAccessFileOrArray.close();
                } catch (Exception unused) {
                }
            }
        }
    }

    static void findAllObjects(PdfReader pdfReader, PdfObject pdfObject, IntHashtable intHashtable) {
        if (pdfObject != null) {
            int type = pdfObject.type();
            if (type != 10) {
                switch (type) {
                    case 5:
                        PdfArray pdfArray = (PdfArray) pdfObject;
                        for (int i = 0; i < pdfArray.size(); i++) {
                            findAllObjects(pdfReader, pdfArray.getPdfObject(i), intHashtable);
                        }
                        return;
                    case 6:
                    case 7:
                        PdfDictionary pdfDictionary = (PdfDictionary) pdfObject;
                        for (PdfName pdfName : pdfDictionary.getKeys()) {
                            findAllObjects(pdfReader, pdfDictionary.get(pdfName), intHashtable);
                        }
                        return;
                    default:
                        return;
                }
            } else {
                PRIndirectReference pRIndirectReference = (PRIndirectReference) pdfObject;
                if (pdfReader == pRIndirectReference.getReader() && !intHashtable.containsKey(pRIndirectReference.getNumber())) {
                    intHashtable.put(pRIndirectReference.getNumber(), 1);
                    findAllObjects(pdfReader, PdfReader.getPdfObject(pdfObject), intHashtable);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00cd, code lost:
        r6 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00cd, code lost:
        r6 = r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addComments(com.itextpdf.text.pdf.FdfReader r12) throws java.io.IOException {
        /*
            r11 = this;
            java.util.HashMap<com.itextpdf.text.pdf.PdfReader, com.itextpdf.text.pdf.IntHashtable> r0 = r11.readers2intrefs
            boolean r0 = r0.containsKey(r12)
            if (r0 == 0) goto L_0x0009
            return
        L_0x0009:
            com.itextpdf.text.pdf.PdfDictionary r0 = r12.getCatalog()
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.FDF
            com.itextpdf.text.pdf.PdfDictionary r0 = r0.getAsDict(r1)
            if (r0 != 0) goto L_0x0016
            return
        L_0x0016:
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.ANNOTS
            com.itextpdf.text.pdf.PdfArray r0 = r0.getAsArray(r1)
            if (r0 == 0) goto L_0x0120
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0026
            goto L_0x0120
        L_0x0026:
            r1 = 0
            r11.registerReader(r12, r1)
            com.itextpdf.text.pdf.IntHashtable r2 = new com.itextpdf.text.pdf.IntHashtable
            r2.<init>()
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r5 = r1
        L_0x003a:
            int r6 = r0.size()
            r7 = 3
            if (r5 >= r6) goto L_0x008a
            com.itextpdf.text.pdf.PdfObject r6 = r0.getPdfObject(r5)
            com.itextpdf.text.pdf.PdfObject r8 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r6)
            com.itextpdf.text.pdf.PdfDictionary r8 = (com.itextpdf.text.pdf.PdfDictionary) r8
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.PAGE
            com.itextpdf.text.pdf.PdfNumber r9 = r8.getAsNumber(r9)
            if (r9 == 0) goto L_0x0087
            int r9 = r9.intValue()
            com.itextpdf.text.pdf.PdfReader r10 = r11.reader
            int r10 = r10.getNumberOfPages()
            if (r9 < r10) goto L_0x0060
            goto L_0x0087
        L_0x0060:
            findAllObjects(r12, r6, r2)
            r4.add(r6)
            int r9 = r6.type()
            r10 = 10
            if (r9 != r10) goto L_0x0087
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.NM
            com.itextpdf.text.pdf.PdfObject r8 = r8.get(r9)
            com.itextpdf.text.pdf.PdfObject r8 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r8)
            if (r8 == 0) goto L_0x0087
            int r9 = r8.type()
            if (r9 != r7) goto L_0x0087
            java.lang.String r7 = r8.toString()
            r3.put(r7, r6)
        L_0x0087:
            int r5 = r5 + 1
            goto L_0x003a
        L_0x008a:
            int[] r0 = r2.getKeys()
            r2 = r1
        L_0x008f:
            int r5 = r0.length
            if (r2 >= r5) goto L_0x00d7
            r5 = r0[r2]
            com.itextpdf.text.pdf.PdfObject r6 = r12.getPdfObject((int) r5)
            int r8 = r6.type()
            r9 = 6
            if (r8 != r9) goto L_0x00cd
            r8 = r6
            com.itextpdf.text.pdf.PdfDictionary r8 = (com.itextpdf.text.pdf.PdfDictionary) r8
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.IRT
            com.itextpdf.text.pdf.PdfObject r9 = r8.get(r9)
            com.itextpdf.text.pdf.PdfObject r9 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r9)
            if (r9 == 0) goto L_0x00cd
            int r10 = r9.type()
            if (r10 != r7) goto L_0x00cd
            java.lang.String r9 = r9.toString()
            java.lang.Object r9 = r3.get(r9)
            com.itextpdf.text.pdf.PdfObject r9 = (com.itextpdf.text.pdf.PdfObject) r9
            if (r9 == 0) goto L_0x00cd
            com.itextpdf.text.pdf.PdfDictionary r6 = new com.itextpdf.text.pdf.PdfDictionary
            r6.<init>()
            r6.merge(r8)
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.IRT
            r6.put(r8, r9)
        L_0x00cd:
            int r5 = r11.getNewObjectNumber(r12, r5, r1)
            r11.addToBody((com.itextpdf.text.pdf.PdfObject) r6, (int) r5)
            int r2 = r2 + 1
            goto L_0x008f
        L_0x00d7:
            int r12 = r4.size()
            if (r1 >= r12) goto L_0x011f
            java.lang.Object r12 = r4.get(r1)
            com.itextpdf.text.pdf.PdfObject r12 = (com.itextpdf.text.pdf.PdfObject) r12
            com.itextpdf.text.pdf.PdfObject r0 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r12)
            com.itextpdf.text.pdf.PdfDictionary r0 = (com.itextpdf.text.pdf.PdfDictionary) r0
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.PAGE
            com.itextpdf.text.pdf.PdfNumber r0 = r0.getAsNumber(r2)
            com.itextpdf.text.pdf.PdfReader r2 = r11.reader
            int r0 = r0.intValue()
            int r0 = r0 + 1
            com.itextpdf.text.pdf.PdfDictionary r0 = r2.getPageN(r0)
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.ANNOTS
            com.itextpdf.text.pdf.PdfObject r2 = r0.get(r2)
            com.itextpdf.text.pdf.PdfObject r2 = com.itextpdf.text.pdf.PdfReader.getPdfObject(r2, r0)
            com.itextpdf.text.pdf.PdfArray r2 = (com.itextpdf.text.pdf.PdfArray) r2
            if (r2 != 0) goto L_0x0116
            com.itextpdf.text.pdf.PdfArray r2 = new com.itextpdf.text.pdf.PdfArray
            r2.<init>()
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.ANNOTS
            r0.put(r3, r2)
            r11.markUsed((com.itextpdf.text.pdf.PdfObject) r0)
        L_0x0116:
            r11.markUsed((com.itextpdf.text.pdf.PdfObject) r2)
            r2.add((com.itextpdf.text.pdf.PdfObject) r12)
            int r1 = r1 + 1
            goto L_0x00d7
        L_0x011f:
            return
        L_0x0120:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfStamperImp.addComments(com.itextpdf.text.pdf.FdfReader):void");
    }

    /* access modifiers changed from: package-private */
    public PageStamp getPageStamp(int i) {
        PdfDictionary pageN = this.reader.getPageN(i);
        PageStamp pageStamp = this.pagesToContent.get(pageN);
        if (pageStamp != null) {
            return pageStamp;
        }
        PageStamp pageStamp2 = new PageStamp(this, this.reader, pageN);
        this.pagesToContent.put(pageN, pageStamp2);
        return pageStamp2;
    }

    /* access modifiers changed from: package-private */
    public PdfContentByte getUnderContent(int i) {
        if (i < 1 || i > this.reader.getNumberOfPages()) {
            return null;
        }
        PageStamp pageStamp = getPageStamp(i);
        if (pageStamp.under == null) {
            pageStamp.under = new StampContent(this, pageStamp);
        }
        return pageStamp.under;
    }

    /* access modifiers changed from: package-private */
    public PdfContentByte getOverContent(int i) {
        if (i < 1 || i > this.reader.getNumberOfPages()) {
            return null;
        }
        PageStamp pageStamp = getPageStamp(i);
        if (pageStamp.over == null) {
            pageStamp.over = new StampContent(this, pageStamp);
        }
        return pageStamp.over;
    }

    /* access modifiers changed from: package-private */
    public void correctAcroFieldPages(int i) {
        if (this.acroFields != null && i <= this.reader.getNumberOfPages()) {
            for (AcroFields.Item next : this.acroFields.getFields().values()) {
                for (int i2 = 0; i2 < next.size(); i2++) {
                    int intValue = next.getPage(i2).intValue();
                    if (intValue >= i) {
                        next.forcePage(i2, intValue + 1);
                    }
                }
            }
        }
    }

    private static void moveRectangle(PdfDictionary pdfDictionary, PdfReader pdfReader, int i, PdfName pdfName, String str) {
        Rectangle boxSize = pdfReader.getBoxSize(i, str);
        if (boxSize == null) {
            pdfDictionary.remove(pdfName);
        } else {
            pdfDictionary.put(pdfName, new PdfRectangle(boxSize));
        }
    }

    /* access modifiers changed from: package-private */
    public void replacePage(PdfReader pdfReader, int i, int i2) {
        PdfDictionary pageN = this.reader.getPageN(i2);
        if (this.pagesToContent.containsKey(pageN)) {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.page.cannot.be.replaced.new.content.was.already.added", new Object[0]));
        }
        PdfImportedPage importedPage = getImportedPage(pdfReader, i);
        PdfDictionary pageNRelease = this.reader.getPageNRelease(i2);
        pageNRelease.remove(PdfName.RESOURCES);
        pageNRelease.remove(PdfName.CONTENTS);
        moveRectangle(pageNRelease, pdfReader, i, PdfName.MEDIABOX, "media");
        moveRectangle(pageNRelease, pdfReader, i, PdfName.CROPBOX, "crop");
        moveRectangle(pageNRelease, pdfReader, i, PdfName.TRIMBOX, "trim");
        moveRectangle(pageNRelease, pdfReader, i, PdfName.ARTBOX, "art");
        moveRectangle(pageNRelease, pdfReader, i, PdfName.BLEEDBOX, "bleed");
        pageNRelease.put(PdfName.ROTATE, new PdfNumber(pdfReader.getPageRotation(i)));
        getOverContent(i2).addTemplate((PdfTemplate) importedPage, 0.0f, 0.0f);
        PageStamp pageStamp = this.pagesToContent.get(pageN);
        pageStamp.replacePoint = pageStamp.over.getInternalBuffer().size();
    }

    /* access modifiers changed from: package-private */
    public void insertPage(int i, Rectangle rectangle) {
        PRIndirectReference pRIndirectReference;
        PdfDictionary pdfDictionary;
        Rectangle rectangle2 = new Rectangle(rectangle);
        int rotation = rectangle2.getRotation() % 360;
        PdfDictionary pdfDictionary2 = new PdfDictionary(PdfName.PAGE);
        pdfDictionary2.put(PdfName.RESOURCES, new PdfDictionary());
        pdfDictionary2.put(PdfName.ROTATE, new PdfNumber(rotation));
        pdfDictionary2.put(PdfName.MEDIABOX, new PdfRectangle(rectangle2, rotation));
        PRIndirectReference addPdfObject = this.reader.addPdfObject(pdfDictionary2);
        if (i > this.reader.getNumberOfPages()) {
            pRIndirectReference = new PRIndirectReference(this.reader, ((PRIndirectReference) this.reader.getPageNRelease(this.reader.getNumberOfPages()).get(PdfName.PARENT)).getNumber());
            pdfDictionary = (PdfDictionary) PdfReader.getPdfObject((PdfObject) pRIndirectReference);
            PdfArray pdfArray = (PdfArray) PdfReader.getPdfObject(pdfDictionary.get(PdfName.KIDS), pdfDictionary);
            pdfArray.add((PdfObject) addPdfObject);
            markUsed((PdfObject) pdfArray);
            this.reader.pageRefs.insertPage(i, addPdfObject);
        } else {
            if (i < 1) {
                i = 1;
            }
            PdfDictionary pageN = this.reader.getPageN(i);
            PRIndirectReference pageOrigRef = this.reader.getPageOrigRef(i);
            this.reader.releasePage(i);
            PRIndirectReference pRIndirectReference2 = new PRIndirectReference(this.reader, ((PRIndirectReference) pageN.get(PdfName.PARENT)).getNumber());
            pdfDictionary = (PdfDictionary) PdfReader.getPdfObject((PdfObject) pRIndirectReference2);
            PdfArray pdfArray2 = (PdfArray) PdfReader.getPdfObject(pdfDictionary.get(PdfName.KIDS), pdfDictionary);
            int size = pdfArray2.size();
            int number = pageOrigRef.getNumber();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                } else if (number == ((PRIndirectReference) pdfArray2.getPdfObject(i2)).getNumber()) {
                    pdfArray2.add(i2, addPdfObject);
                    break;
                } else {
                    i2++;
                }
            }
            if (size == pdfArray2.size()) {
                throw new RuntimeException(MessageLocalization.getComposedMessage("internal.inconsistence", new Object[0]));
            }
            markUsed((PdfObject) pdfArray2);
            this.reader.pageRefs.insertPage(i, addPdfObject);
            correctAcroFieldPages(i);
            pRIndirectReference = pRIndirectReference2;
        }
        pdfDictionary2.put(PdfName.PARENT, pRIndirectReference);
        while (pdfDictionary != null) {
            markUsed((PdfObject) pdfDictionary);
            pdfDictionary.put(PdfName.COUNT, new PdfNumber(((PdfNumber) PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.COUNT))).intValue() + 1));
            pdfDictionary = pdfDictionary.getAsDict(PdfName.PARENT);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isRotateContents() {
        return this.rotateContents;
    }

    /* access modifiers changed from: package-private */
    public void setRotateContents(boolean z) {
        this.rotateContents = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isContentWritten() {
        return this.body.size() > 1;
    }

    /* access modifiers changed from: package-private */
    public AcroFields getAcroFields() {
        if (this.acroFields == null) {
            this.acroFields = new AcroFields(this.reader, this);
        }
        return this.acroFields;
    }

    /* access modifiers changed from: package-private */
    public void setFormFlattening(boolean z) {
        this.flat = z;
    }

    /* access modifiers changed from: package-private */
    public void setFreeTextFlattening(boolean z) {
        this.flatFreeText = z;
    }

    /* access modifiers changed from: package-private */
    public boolean partialFormFlattening(String str) {
        getAcroFields();
        if (this.acroFields.getXfa().isXfaPresent()) {
            throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("partial.form.flattening.is.not.supported.with.xfa.forms", new Object[0]));
        } else if (!this.acroFields.getFields().containsKey(str)) {
            return false;
        } else {
            this.partialFlattening.add(str);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x02ef  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0302  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0263  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void flatFields() {
        /*
            r18 = this;
            r0 = r18
            boolean r1 = r0.append
            r2 = 0
            if (r1 == 0) goto L_0x0015
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "field.flattening.is.not.supported.in.append.mode"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r2)
            r1.<init>(r2)
            throw r1
        L_0x0015:
            r18.getAcroFields()
            com.itextpdf.text.pdf.AcroFields r1 = r0.acroFields
            java.util.Map r1 = r1.getFields()
            boolean r3 = r0.fieldsAdded
            if (r3 == 0) goto L_0x0044
            java.util.HashSet<java.lang.String> r3 = r0.partialFlattening
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0044
            java.util.Set r3 = r1.keySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x0032:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0044
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            java.util.HashSet<java.lang.String> r5 = r0.partialFlattening
            r5.add(r4)
            goto L_0x0032
        L_0x0044:
            com.itextpdf.text.pdf.PdfReader r3 = r0.reader
            com.itextpdf.text.pdf.PdfDictionary r3 = r3.getCatalog()
            com.itextpdf.text.pdf.PdfName r4 = com.itextpdf.text.pdf.PdfName.ACROFORM
            com.itextpdf.text.pdf.PdfDictionary r3 = r3.getAsDict(r4)
            if (r3 == 0) goto L_0x005f
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.FIELDS
            com.itextpdf.text.pdf.PdfObject r5 = r3.get(r5)
            com.itextpdf.text.pdf.PdfObject r3 = com.itextpdf.text.pdf.PdfReader.getPdfObject(r5, r3)
            com.itextpdf.text.pdf.PdfArray r3 = (com.itextpdf.text.pdf.PdfArray) r3
            goto L_0x0060
        L_0x005f:
            r3 = 0
        L_0x0060:
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0068:
            boolean r5 = r1.hasNext()
            r6 = 1
            if (r5 == 0) goto L_0x03d6
            java.lang.Object r5 = r1.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r7 = r5.getKey()
            java.lang.String r7 = (java.lang.String) r7
            java.util.HashSet<java.lang.String> r8 = r0.partialFlattening
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x008c
            java.util.HashSet<java.lang.String> r8 = r0.partialFlattening
            boolean r8 = r8.contains(r7)
            if (r8 != 0) goto L_0x008c
            goto L_0x0068
        L_0x008c:
            java.lang.Object r5 = r5.getValue()
            com.itextpdf.text.pdf.AcroFields$Item r5 = (com.itextpdf.text.pdf.AcroFields.Item) r5
            r8 = r2
        L_0x0093:
            int r9 = r5.size()
            if (r8 >= r9) goto L_0x0068
            com.itextpdf.text.pdf.PdfDictionary r9 = r5.getMerged(r8)
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.F
            com.itextpdf.text.pdf.PdfNumber r10 = r9.getAsNumber(r10)
            if (r10 == 0) goto L_0x00aa
            int r10 = r10.intValue()
            goto L_0x00ab
        L_0x00aa:
            r10 = r2
        L_0x00ab:
            java.lang.Integer r11 = r5.getPage(r8)
            int r11 = r11.intValue()
            if (r11 >= r6) goto L_0x00bb
            r16 = r1
            r14 = r2
            r9 = r3
            goto L_0x03cd
        L_0x00bb:
            com.itextpdf.text.pdf.PdfName r12 = com.itextpdf.text.pdf.PdfName.AP
            com.itextpdf.text.pdf.PdfDictionary r12 = r9.getAsDict(r12)
            if (r12 == 0) goto L_0x00d2
            com.itextpdf.text.pdf.PdfName r13 = com.itextpdf.text.pdf.PdfName.N
            com.itextpdf.text.pdf.PdfStream r13 = r12.getAsStream(r13)
            if (r13 != 0) goto L_0x00d3
            com.itextpdf.text.pdf.PdfName r13 = com.itextpdf.text.pdf.PdfName.N
            com.itextpdf.text.pdf.PdfDictionary r13 = r12.getAsDict(r13)
            goto L_0x00d3
        L_0x00d2:
            r13 = 0
        L_0x00d3:
            com.itextpdf.text.pdf.AcroFields r14 = r0.acroFields
            boolean r14 = r14.isGenerateAppearances()
            r15 = 2
            if (r14 == 0) goto L_0x01b7
            if (r12 == 0) goto L_0x019b
            if (r13 != 0) goto L_0x00e2
            goto L_0x019b
        L_0x00e2:
            boolean r14 = r13.isStream()
            if (r14 == 0) goto L_0x0194
            com.itextpdf.text.pdf.PdfStream r13 = (com.itextpdf.text.pdf.PdfStream) r13
            com.itextpdf.text.pdf.PdfName r14 = com.itextpdf.text.pdf.PdfName.BBOX
            com.itextpdf.text.pdf.PdfArray r14 = r13.getAsArray(r14)
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.RECT
            com.itextpdf.text.pdf.PdfArray r6 = r9.getAsArray(r6)
            if (r14 == 0) goto L_0x018e
            if (r6 == 0) goto L_0x018e
            com.itextpdf.text.pdf.PdfNumber r4 = r6.getAsNumber(r15)
            float r4 = r4.floatValue()
            com.itextpdf.text.pdf.PdfNumber r15 = r6.getAsNumber(r2)
            float r15 = r15.floatValue()
            float r4 = r4 - r15
            r16 = r1
            r15 = 2
            com.itextpdf.text.pdf.PdfNumber r1 = r14.getAsNumber(r15)
            float r1 = r1.floatValue()
            com.itextpdf.text.pdf.PdfNumber r15 = r14.getAsNumber(r2)
            float r15 = r15.floatValue()
            float r1 = r1 - r15
            r15 = 3
            com.itextpdf.text.pdf.PdfNumber r2 = r6.getAsNumber(r15)
            float r2 = r2.floatValue()
            r15 = 1
            com.itextpdf.text.pdf.PdfNumber r6 = r6.getAsNumber(r15)
            float r6 = r6.floatValue()
            float r2 = r2 - r6
            r17 = r3
            r6 = 3
            com.itextpdf.text.pdf.PdfNumber r3 = r14.getAsNumber(r6)
            float r3 = r3.floatValue()
            com.itextpdf.text.pdf.PdfNumber r6 = r14.getAsNumber(r15)
            float r6 = r6.floatValue()
            float r3 = r3 - r6
            r6 = 0
            int r14 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            r15 = 2139095039(0x7f7fffff, float:3.4028235E38)
            if (r14 == 0) goto L_0x0151
            float r1 = r4 / r1
            goto L_0x0152
        L_0x0151:
            r1 = r15
        L_0x0152:
            float r1 = java.lang.Math.abs(r1)
            int r4 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x015c
            float r15 = r2 / r3
        L_0x015c:
            float r2 = java.lang.Math.abs(r15)
            r3 = 1065353216(0x3f800000, float:1.0)
            int r4 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r4 != 0) goto L_0x016a
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x024f
        L_0x016a:
            com.itextpdf.text.pdf.NumberArray r3 = new com.itextpdf.text.pdf.NumberArray
            r4 = 6
            float[] r4 = new float[r4]
            r14 = 0
            r4[r14] = r1
            r1 = 1
            r4[r1] = r6
            r1 = 2
            r4[r1] = r6
            r1 = 3
            r4[r1] = r2
            r1 = 4
            r4[r1] = r6
            r1 = 5
            r4[r1] = r6
            r3.<init>((float[]) r4)
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.MATRIX
            r13.put(r1, r3)
            r0.markUsed((com.itextpdf.text.pdf.PdfObject) r13)
            goto L_0x024f
        L_0x018e:
            r16 = r1
            r17 = r3
            goto L_0x024f
        L_0x0194:
            r16 = r1
            r17 = r3
            r14 = r2
            goto L_0x0250
        L_0x019b:
            r16 = r1
            r17 = r3
            com.itextpdf.text.pdf.AcroFields r1 = r0.acroFields     // Catch:{ DocumentException | IOException -> 0x024f }
            r1.regenerateField(r7)     // Catch:{ DocumentException | IOException -> 0x024f }
            com.itextpdf.text.pdf.AcroFields r1 = r0.acroFields     // Catch:{ DocumentException | IOException -> 0x024f }
            com.itextpdf.text.pdf.AcroFields$Item r1 = r1.getFieldItem(r7)     // Catch:{ DocumentException | IOException -> 0x024f }
            com.itextpdf.text.pdf.PdfDictionary r1 = r1.getMerged(r8)     // Catch:{ DocumentException | IOException -> 0x024f }
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.AP     // Catch:{ DocumentException | IOException -> 0x024f }
            com.itextpdf.text.pdf.PdfDictionary r1 = r1.getAsDict(r2)     // Catch:{ DocumentException | IOException -> 0x024f }
            r14 = 0
            goto L_0x0251
        L_0x01b7:
            r16 = r1
            r17 = r3
            if (r12 == 0) goto L_0x024f
            if (r13 == 0) goto L_0x024f
            com.itextpdf.text.pdf.PdfDictionary r13 = (com.itextpdf.text.pdf.PdfDictionary) r13
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.BBOX
            com.itextpdf.text.pdf.PdfArray r1 = r13.getAsArray(r1)
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.RECT
            com.itextpdf.text.pdf.PdfArray r2 = r9.getAsArray(r2)
            if (r1 == 0) goto L_0x024f
            if (r2 == 0) goto L_0x024f
            r3 = 2
            com.itextpdf.text.pdf.PdfNumber r4 = r1.getAsNumber(r3)
            float r4 = r4.floatValue()
            r6 = 0
            com.itextpdf.text.pdf.PdfNumber r13 = r1.getAsNumber(r6)
            float r13 = r13.floatValue()
            float r4 = r4 - r13
            com.itextpdf.text.pdf.PdfNumber r3 = r2.getAsNumber(r3)
            float r3 = r3.floatValue()
            com.itextpdf.text.pdf.PdfNumber r13 = r2.getAsNumber(r6)
            float r6 = r13.floatValue()
            float r3 = r3 - r6
            float r4 = r4 - r3
            r3 = 3
            com.itextpdf.text.pdf.PdfNumber r6 = r1.getAsNumber(r3)
            float r6 = r6.floatValue()
            r13 = 1
            com.itextpdf.text.pdf.PdfNumber r1 = r1.getAsNumber(r13)
            float r1 = r1.floatValue()
            float r6 = r6 - r1
            com.itextpdf.text.pdf.PdfNumber r1 = r2.getAsNumber(r3)
            float r1 = r1.floatValue()
            com.itextpdf.text.pdf.PdfNumber r2 = r2.getAsNumber(r13)
            float r2 = r2.floatValue()
            float r1 = r1 - r2
            float r6 = r6 - r1
            float r1 = java.lang.Math.abs(r4)
            r2 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x022d
            float r1 = java.lang.Math.abs(r6)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x024f
        L_0x022d:
            com.itextpdf.text.pdf.AcroFields r1 = r0.acroFields     // Catch:{ DocumentException | IOException -> 0x024f }
            r2 = 1
            r1.setGenerateAppearances(r2)     // Catch:{ DocumentException | IOException -> 0x024f }
            com.itextpdf.text.pdf.AcroFields r1 = r0.acroFields     // Catch:{ DocumentException | IOException -> 0x024f }
            r1.regenerateField(r7)     // Catch:{ DocumentException | IOException -> 0x024f }
            com.itextpdf.text.pdf.AcroFields r1 = r0.acroFields     // Catch:{ DocumentException | IOException -> 0x024f }
            r14 = 0
            r1.setGenerateAppearances(r14)     // Catch:{ DocumentException | IOException -> 0x0250 }
            com.itextpdf.text.pdf.AcroFields r1 = r0.acroFields     // Catch:{ DocumentException | IOException -> 0x0250 }
            com.itextpdf.text.pdf.AcroFields$Item r1 = r1.getFieldItem(r7)     // Catch:{ DocumentException | IOException -> 0x0250 }
            com.itextpdf.text.pdf.PdfDictionary r1 = r1.getMerged(r8)     // Catch:{ DocumentException | IOException -> 0x0250 }
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.AP     // Catch:{ DocumentException | IOException -> 0x0250 }
            com.itextpdf.text.pdf.PdfDictionary r1 = r1.getAsDict(r2)     // Catch:{ DocumentException | IOException -> 0x0250 }
            goto L_0x0251
        L_0x024f:
            r14 = 0
        L_0x0250:
            r1 = r12
        L_0x0251:
            if (r1 == 0) goto L_0x02e7
            r2 = r10 & 4
            if (r2 == 0) goto L_0x02e7
            r2 = r10 & 2
            if (r2 != 0) goto L_0x02e7
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.N
            com.itextpdf.text.pdf.PdfObject r1 = r1.get(r2)
            if (r1 == 0) goto L_0x02c1
            com.itextpdf.text.pdf.PdfObject r2 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r1)
            boolean r3 = r1 instanceof com.itextpdf.text.pdf.PdfIndirectReference
            if (r3 == 0) goto L_0x0279
            boolean r3 = r1.isIndirect()
            if (r3 != 0) goto L_0x0279
            com.itextpdf.text.pdf.PdfAppearance r4 = new com.itextpdf.text.pdf.PdfAppearance
            com.itextpdf.text.pdf.PdfIndirectReference r1 = (com.itextpdf.text.pdf.PdfIndirectReference) r1
            r4.<init>((com.itextpdf.text.pdf.PdfIndirectReference) r1)
            goto L_0x02c2
        L_0x0279:
            boolean r3 = r2 instanceof com.itextpdf.text.pdf.PdfStream
            if (r3 == 0) goto L_0x028e
            com.itextpdf.text.pdf.PdfDictionary r2 = (com.itextpdf.text.pdf.PdfDictionary) r2
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.SUBTYPE
            com.itextpdf.text.pdf.PdfName r4 = com.itextpdf.text.pdf.PdfName.FORM
            r2.put(r3, r4)
            com.itextpdf.text.pdf.PdfAppearance r4 = new com.itextpdf.text.pdf.PdfAppearance
            com.itextpdf.text.pdf.PdfIndirectReference r1 = (com.itextpdf.text.pdf.PdfIndirectReference) r1
            r4.<init>((com.itextpdf.text.pdf.PdfIndirectReference) r1)
            goto L_0x02c2
        L_0x028e:
            if (r2 == 0) goto L_0x02c1
            boolean r1 = r2.isDictionary()
            if (r1 == 0) goto L_0x02c1
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.AS
            com.itextpdf.text.pdf.PdfName r1 = r9.getAsName(r1)
            if (r1 == 0) goto L_0x02c1
            com.itextpdf.text.pdf.PdfDictionary r2 = (com.itextpdf.text.pdf.PdfDictionary) r2
            com.itextpdf.text.pdf.PdfObject r1 = r2.get(r1)
            com.itextpdf.text.pdf.PdfIndirectReference r1 = (com.itextpdf.text.pdf.PdfIndirectReference) r1
            if (r1 == 0) goto L_0x02c1
            com.itextpdf.text.pdf.PdfAppearance r4 = new com.itextpdf.text.pdf.PdfAppearance
            r4.<init>((com.itextpdf.text.pdf.PdfIndirectReference) r1)
            boolean r2 = r1.isIndirect()
            if (r2 == 0) goto L_0x02c2
            com.itextpdf.text.pdf.PdfObject r1 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r1)
            com.itextpdf.text.pdf.PdfDictionary r1 = (com.itextpdf.text.pdf.PdfDictionary) r1
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.SUBTYPE
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.FORM
            r1.put(r2, r3)
            goto L_0x02c2
        L_0x02c1:
            r4 = 0
        L_0x02c2:
            if (r4 == 0) goto L_0x02e7
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.RECT
            com.itextpdf.text.pdf.PdfArray r1 = r9.getAsArray(r1)
            com.itextpdf.text.Rectangle r1 = com.itextpdf.text.pdf.PdfReader.getNormalizedRectangle(r1)
            com.itextpdf.text.pdf.PdfContentByte r2 = r0.getOverContent(r11)
            java.lang.String r3 = "Q "
            r2.setLiteral((java.lang.String) r3)
            float r3 = r1.getLeft()
            float r1 = r1.getBottom()
            r2.addTemplate((com.itextpdf.text.pdf.PdfTemplate) r4, (float) r3, (float) r1)
            java.lang.String r1 = "q "
            r2.setLiteral((java.lang.String) r1)
        L_0x02e7:
            java.util.HashSet<java.lang.String> r1 = r0.partialFlattening
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x02f3
        L_0x02ef:
            r9 = r17
            goto L_0x03cd
        L_0x02f3:
            com.itextpdf.text.pdf.PdfReader r1 = r0.reader
            com.itextpdf.text.pdf.PdfDictionary r1 = r1.getPageN(r11)
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.ANNOTS
            com.itextpdf.text.pdf.PdfArray r2 = r1.getAsArray(r2)
            if (r2 != 0) goto L_0x0302
            goto L_0x02ef
        L_0x0302:
            r3 = r14
        L_0x0303:
            int r4 = r2.size()
            if (r3 >= r4) goto L_0x03b7
            com.itextpdf.text.pdf.PdfObject r4 = r2.getPdfObject(r3)
            boolean r6 = r4.isIndirect()
            if (r6 != 0) goto L_0x0318
        L_0x0313:
            r9 = r17
            r12 = 1
            goto L_0x03b2
        L_0x0318:
            com.itextpdf.text.pdf.PdfIndirectReference r6 = r5.getWidgetRef(r8)
            boolean r9 = r6.isIndirect()
            if (r9 != 0) goto L_0x0323
            goto L_0x0313
        L_0x0323:
            com.itextpdf.text.pdf.PRIndirectReference r4 = (com.itextpdf.text.pdf.PRIndirectReference) r4
            int r4 = r4.getNumber()
            com.itextpdf.text.pdf.PRIndirectReference r6 = (com.itextpdf.text.pdf.PRIndirectReference) r6
            int r9 = r6.getNumber()
            if (r4 != r9) goto L_0x0313
            int r4 = r3 + -1
            r2.remove(r3)
        L_0x0336:
            com.itextpdf.text.pdf.PdfObject r3 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r6)
            com.itextpdf.text.pdf.PdfDictionary r3 = (com.itextpdf.text.pdf.PdfDictionary) r3
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.PARENT
            com.itextpdf.text.pdf.PdfObject r3 = r3.get(r9)
            com.itextpdf.text.pdf.PRIndirectReference r3 = (com.itextpdf.text.pdf.PRIndirectReference) r3
            com.itextpdf.text.pdf.PdfReader.killIndirect(r6)
            if (r3 != 0) goto L_0x0372
            r3 = r14
            r9 = r17
        L_0x034c:
            int r10 = r9.size()
            if (r3 >= r10) goto L_0x0370
            com.itextpdf.text.pdf.PdfObject r10 = r9.getPdfObject(r3)
            boolean r11 = r10.isIndirect()
            if (r11 == 0) goto L_0x036d
            com.itextpdf.text.pdf.PRIndirectReference r10 = (com.itextpdf.text.pdf.PRIndirectReference) r10
            int r10 = r10.getNumber()
            int r11 = r6.getNumber()
            if (r10 != r11) goto L_0x036d
            r9.remove(r3)
            int r3 = r3 + -1
        L_0x036d:
            r10 = 1
            int r3 = r3 + r10
            goto L_0x034c
        L_0x0370:
            r12 = 1
            goto L_0x03ac
        L_0x0372:
            r9 = r17
            com.itextpdf.text.pdf.PdfObject r10 = com.itextpdf.text.pdf.PdfReader.getPdfObject((com.itextpdf.text.pdf.PdfObject) r3)
            com.itextpdf.text.pdf.PdfDictionary r10 = (com.itextpdf.text.pdf.PdfDictionary) r10
            com.itextpdf.text.pdf.PdfName r11 = com.itextpdf.text.pdf.PdfName.KIDS
            com.itextpdf.text.pdf.PdfArray r10 = r10.getAsArray(r11)
            r11 = r14
        L_0x0381:
            int r12 = r10.size()
            if (r11 >= r12) goto L_0x03a5
            com.itextpdf.text.pdf.PdfObject r12 = r10.getPdfObject(r11)
            boolean r13 = r12.isIndirect()
            if (r13 == 0) goto L_0x03a2
            com.itextpdf.text.pdf.PRIndirectReference r12 = (com.itextpdf.text.pdf.PRIndirectReference) r12
            int r12 = r12.getNumber()
            int r13 = r6.getNumber()
            if (r12 != r13) goto L_0x03a2
            r10.remove(r11)
            int r11 = r11 + -1
        L_0x03a2:
            r12 = 1
            int r11 = r11 + r12
            goto L_0x0381
        L_0x03a5:
            r12 = 1
            boolean r6 = r10.isEmpty()
            if (r6 != 0) goto L_0x03ae
        L_0x03ac:
            r3 = r4
            goto L_0x03b2
        L_0x03ae:
            r6 = r3
            r17 = r9
            goto L_0x0336
        L_0x03b2:
            int r3 = r3 + r12
            r17 = r9
            goto L_0x0303
        L_0x03b7:
            r9 = r17
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x03cd
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.ANNOTS
            com.itextpdf.text.pdf.PdfObject r2 = r1.get(r2)
            com.itextpdf.text.pdf.PdfReader.killIndirect(r2)
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.ANNOTS
            r1.remove(r2)
        L_0x03cd:
            int r8 = r8 + 1
            r3 = r9
            r2 = r14
            r1 = r16
            r6 = 1
            goto L_0x0093
        L_0x03d6:
            r14 = r2
            boolean r1 = r0.fieldsAdded
            if (r1 != 0) goto L_0x044e
            java.util.HashSet<java.lang.String> r1 = r0.partialFlattening
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x044e
            r1 = 1
        L_0x03e4:
            com.itextpdf.text.pdf.PdfReader r2 = r0.reader
            int r2 = r2.getNumberOfPages()
            if (r1 > r2) goto L_0x044b
            com.itextpdf.text.pdf.PdfReader r2 = r0.reader
            com.itextpdf.text.pdf.PdfDictionary r2 = r2.getPageN(r1)
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.ANNOTS
            com.itextpdf.text.pdf.PdfArray r3 = r2.getAsArray(r3)
            if (r3 != 0) goto L_0x03fc
            r5 = 1
            goto L_0x0448
        L_0x03fc:
            r4 = r14
        L_0x03fd:
            int r5 = r3.size()
            if (r4 >= r5) goto L_0x0433
            com.itextpdf.text.pdf.PdfObject r5 = r3.getDirectObject(r4)
            boolean r6 = r5 instanceof com.itextpdf.text.pdf.PdfIndirectReference
            if (r6 == 0) goto L_0x0412
            boolean r6 = r5.isIndirect()
            if (r6 != 0) goto L_0x0412
            goto L_0x0429
        L_0x0412:
            boolean r6 = r5.isDictionary()
            if (r6 == 0) goto L_0x042b
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.WIDGET
            com.itextpdf.text.pdf.PdfDictionary r5 = (com.itextpdf.text.pdf.PdfDictionary) r5
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.SUBTYPE
            com.itextpdf.text.pdf.PdfObject r5 = r5.get(r7)
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x0429
            goto L_0x042b
        L_0x0429:
            r5 = 1
            goto L_0x0431
        L_0x042b:
            r3.remove(r4)
            int r4 = r4 + -1
            goto L_0x0429
        L_0x0431:
            int r4 = r4 + r5
            goto L_0x03fd
        L_0x0433:
            r5 = 1
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0448
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.ANNOTS
            com.itextpdf.text.pdf.PdfObject r3 = r2.get(r3)
            com.itextpdf.text.pdf.PdfReader.killIndirect(r3)
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.ANNOTS
            r2.remove(r3)
        L_0x0448:
            int r1 = r1 + 1
            goto L_0x03e4
        L_0x044b:
            r18.eliminateAcroformObjects()
        L_0x044e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfStamperImp.flatFields():void");
    }

    /* access modifiers changed from: package-private */
    public void eliminateAcroformObjects() {
        PdfObject pdfObject = this.reader.getCatalog().get(PdfName.ACROFORM);
        if (pdfObject != null) {
            PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObject(pdfObject);
            this.reader.killXref(pdfDictionary.get(PdfName.XFA));
            pdfDictionary.remove(PdfName.XFA);
            PdfObject pdfObject2 = pdfDictionary.get(PdfName.FIELDS);
            if (pdfObject2 != null) {
                PdfDictionary pdfDictionary2 = new PdfDictionary();
                pdfDictionary2.put(PdfName.KIDS, pdfObject2);
                sweepKids(pdfDictionary2);
                PdfReader.killIndirect(pdfObject2);
                pdfDictionary.put(PdfName.FIELDS, new PdfArray());
            }
            pdfDictionary.remove(PdfName.SIGFLAGS);
            pdfDictionary.remove(PdfName.NEEDAPPEARANCES);
            pdfDictionary.remove(PdfName.DR);
        }
    }

    /* access modifiers changed from: package-private */
    public void sweepKids(PdfObject pdfObject) {
        PdfArray pdfArray;
        PdfObject killIndirect = PdfReader.killIndirect(pdfObject);
        if (killIndirect != null && killIndirect.isDictionary() && (pdfArray = (PdfArray) PdfReader.killIndirect(((PdfDictionary) killIndirect).get(PdfName.KIDS))) != null) {
            for (int i = 0; i < pdfArray.size(); i++) {
                sweepKids(pdfArray.getPdfObject(i));
            }
        }
    }

    public void setFlatAnnotations(boolean z) {
        this.flatannotations = z;
    }

    /* access modifiers changed from: protected */
    public void flattenAnnotations() {
        flattenAnnotations(false);
    }

    private void flattenAnnotations(boolean z) {
        PdfObject pdfObject;
        PdfName asName;
        PdfIndirectReference pdfIndirectReference;
        if (!this.append) {
            for (int i = 1; i <= this.reader.getNumberOfPages(); i++) {
                PdfDictionary pageN = this.reader.getPageN(i);
                PdfArray asArray = pageN.getAsArray(PdfName.ANNOTS);
                if (asArray != null) {
                    int i2 = 0;
                    while (i2 < asArray.size()) {
                        PdfObject directObject = asArray.getDirectObject(i2);
                        if ((!(directObject instanceof PdfIndirectReference) || directObject.isIndirect()) && (directObject instanceof PdfDictionary)) {
                            PdfDictionary pdfDictionary = (PdfDictionary) directObject;
                            if (z) {
                                if (!pdfDictionary.get(PdfName.SUBTYPE).equals(PdfName.FREETEXT)) {
                                }
                            } else if (pdfDictionary.get(PdfName.SUBTYPE).equals(PdfName.WIDGET)) {
                            }
                            PdfNumber asNumber = pdfDictionary.getAsNumber(PdfName.F);
                            int intValue = asNumber != null ? asNumber.intValue() : 0;
                            if (!((intValue & 4) == 0 || (intValue & 2) != 0 || (pdfObject = pdfDictionary.get(PdfName.AP)) == null)) {
                                if (pdfObject instanceof PdfIndirectReference) {
                                    pdfObject = PdfReader.getPdfObject(pdfObject);
                                }
                                PdfDictionary pdfDictionary2 = (PdfDictionary) pdfObject;
                                PdfObject pdfObject2 = pdfDictionary2.get(PdfName.N);
                                PdfStream asStream = pdfDictionary2.getAsStream(PdfName.N);
                                PdfAppearance pdfAppearance = null;
                                PdfObject pdfObject3 = PdfReader.getPdfObject(pdfObject2);
                                if ((pdfObject2 instanceof PdfIndirectReference) && !pdfObject2.isIndirect()) {
                                    pdfAppearance = new PdfAppearance((PdfIndirectReference) pdfObject2);
                                } else if (pdfObject3 instanceof PdfStream) {
                                    ((PdfDictionary) pdfObject3).put(PdfName.SUBTYPE, PdfName.FORM);
                                    pdfAppearance = new PdfAppearance((PdfIndirectReference) pdfObject2);
                                } else if (!(!pdfObject3.isDictionary() || (asName = pdfDictionary2.getAsName(PdfName.AS)) == null || (pdfIndirectReference = (PdfIndirectReference) ((PdfDictionary) pdfObject3).get(asName)) == null)) {
                                    pdfAppearance = new PdfAppearance(pdfIndirectReference);
                                    if (pdfIndirectReference.isIndirect()) {
                                        ((PdfDictionary) PdfReader.getPdfObject((PdfObject) pdfIndirectReference)).put(PdfName.SUBTYPE, PdfName.FORM);
                                    }
                                }
                                PdfAppearance pdfAppearance2 = pdfAppearance;
                                if (pdfAppearance2 != null) {
                                    Rectangle normalizedRectangle = PdfReader.getNormalizedRectangle(pdfDictionary.getAsArray(PdfName.RECT));
                                    Rectangle normalizedRectangle2 = PdfReader.getNormalizedRectangle(asStream.getAsArray(PdfName.BBOX));
                                    PdfContentByte overContent = getOverContent(i);
                                    overContent.setLiteral("Q ");
                                    if (asStream.getAsArray(PdfName.MATRIX) == null || Arrays.equals(this.DEFAULT_MATRIX, asStream.getAsArray(PdfName.MATRIX).asDoubleArray())) {
                                        overContent.addTemplate((PdfTemplate) pdfAppearance2, normalizedRectangle.getWidth() / normalizedRectangle2.getWidth(), 0.0f, 0.0f, normalizedRectangle.getHeight() / normalizedRectangle2.getHeight(), normalizedRectangle.getLeft(), normalizedRectangle.getBottom());
                                    } else {
                                        Rectangle transformBBoxByMatrix = transformBBoxByMatrix(normalizedRectangle2, asStream.getAsArray(PdfName.MATRIX).asDoubleArray());
                                        overContent.addTemplate((PdfTemplate) pdfAppearance2, normalizedRectangle.getWidth() / transformBBoxByMatrix.getWidth(), 0.0f, 0.0f, normalizedRectangle.getHeight() / transformBBoxByMatrix.getHeight(), normalizedRectangle.getLeft(), normalizedRectangle.getBottom());
                                    }
                                    overContent.setLiteral("q ");
                                    asArray.remove(i2);
                                    i2--;
                                }
                            }
                        }
                        i2++;
                    }
                    if (asArray.isEmpty()) {
                        PdfReader.killIndirect(pageN.get(PdfName.ANNOTS));
                        pageN.remove(PdfName.ANNOTS);
                    }
                }
            }
        } else if (z) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("freetext.flattening.is.not.supported.in.append.mode", new Object[0]));
        } else {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("annotation.flattening.is.not.supported.in.append.mode", new Object[0]));
        }
    }

    private Rectangle transformBBoxByMatrix(Rectangle rectangle, double[] dArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        double[] dArr2 = dArr;
        Point transformPoint = transformPoint((double) rectangle.getLeft(), (double) rectangle.getBottom(), dArr2);
        arrayList.add(Double.valueOf(transformPoint.x));
        arrayList2.add(Double.valueOf(transformPoint.y));
        Point transformPoint2 = transformPoint((double) rectangle.getRight(), (double) rectangle.getTop(), dArr2);
        arrayList.add(Double.valueOf(transformPoint2.x));
        arrayList2.add(Double.valueOf(transformPoint2.y));
        Point transformPoint3 = transformPoint((double) rectangle.getLeft(), (double) rectangle.getTop(), dArr2);
        arrayList.add(Double.valueOf(transformPoint3.x));
        arrayList2.add(Double.valueOf(transformPoint3.y));
        Point transformPoint4 = transformPoint((double) rectangle.getRight(), (double) rectangle.getBottom(), dArr2);
        arrayList.add(Double.valueOf(transformPoint4.x));
        arrayList2.add(Double.valueOf(transformPoint4.y));
        return new Rectangle(((Double) Collections.min(arrayList)).floatValue(), ((Double) Collections.min(arrayList2)).floatValue(), ((Double) Collections.max(arrayList)).floatValue(), ((Double) Collections.max(arrayList2)).floatValue());
    }

    private Point transformPoint(double d, double d2, double[] dArr) {
        Point point = new Point();
        point.x = (dArr[0] * d) + (dArr[2] * d2) + dArr[4];
        point.y = (dArr[1] * d) + (dArr[3] * d2) + dArr[5];
        return point;
    }

    /* access modifiers changed from: protected */
    public void flatFreeTextFields() {
        flattenAnnotations(true);
    }

    public PdfIndirectReference getPageReference(int i) {
        PRIndirectReference pageOrigRef = this.reader.getPageOrigRef(i);
        if (pageOrigRef != null) {
            return pageOrigRef;
        }
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.page.number.1", i));
    }

    public void addAnnotation(PdfAnnotation pdfAnnotation) {
        throw new RuntimeException(MessageLocalization.getComposedMessage("unsupported.in.this.context.use.pdfstamper.addannotation", new Object[0]));
    }

    /* access modifiers changed from: package-private */
    public void addDocumentField(PdfIndirectReference pdfIndirectReference) {
        PdfDictionary catalog = this.reader.getCatalog();
        PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObject(catalog.get(PdfName.ACROFORM), catalog);
        if (pdfDictionary == null) {
            pdfDictionary = new PdfDictionary();
            catalog.put(PdfName.ACROFORM, pdfDictionary);
            markUsed((PdfObject) catalog);
        }
        PdfArray pdfArray = (PdfArray) PdfReader.getPdfObject(pdfDictionary.get(PdfName.FIELDS), pdfDictionary);
        if (pdfArray == null) {
            pdfArray = new PdfArray();
            pdfDictionary.put(PdfName.FIELDS, pdfArray);
            markUsed((PdfObject) pdfDictionary);
        }
        if (!pdfDictionary.contains(PdfName.DA)) {
            pdfDictionary.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g "));
            markUsed((PdfObject) pdfDictionary);
        }
        pdfArray.add((PdfObject) pdfIndirectReference);
        markUsed((PdfObject) pdfArray);
    }

    /* access modifiers changed from: protected */
    public void addFieldResources() throws IOException {
        if (!this.fieldTemplates.isEmpty()) {
            PdfDictionary catalog = this.reader.getCatalog();
            PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObject(catalog.get(PdfName.ACROFORM), catalog);
            if (pdfDictionary == null) {
                pdfDictionary = new PdfDictionary();
                catalog.put(PdfName.ACROFORM, pdfDictionary);
                markUsed((PdfObject) catalog);
            }
            PdfDictionary pdfDictionary2 = (PdfDictionary) PdfReader.getPdfObject(pdfDictionary.get(PdfName.DR), pdfDictionary);
            if (pdfDictionary2 == null) {
                pdfDictionary2 = new PdfDictionary();
                pdfDictionary.put(PdfName.DR, pdfDictionary2);
                markUsed((PdfObject) pdfDictionary);
            }
            markUsed((PdfObject) pdfDictionary2);
            Iterator<PdfTemplate> it = this.fieldTemplates.iterator();
            while (it.hasNext()) {
                PdfFormField.mergeResources(pdfDictionary2, (PdfDictionary) it.next().getResources(), this);
            }
            PdfDictionary asDict = pdfDictionary2.getAsDict(PdfName.FONT);
            if (asDict == null) {
                asDict = new PdfDictionary();
                pdfDictionary2.put(PdfName.FONT, asDict);
            }
            if (!asDict.contains(PdfName.HELV)) {
                PdfDictionary pdfDictionary3 = new PdfDictionary(PdfName.FONT);
                pdfDictionary3.put(PdfName.BASEFONT, PdfName.HELVETICA);
                pdfDictionary3.put(PdfName.ENCODING, PdfName.WIN_ANSI_ENCODING);
                pdfDictionary3.put(PdfName.NAME, PdfName.HELV);
                pdfDictionary3.put(PdfName.SUBTYPE, PdfName.TYPE1);
                asDict.put(PdfName.HELV, addToBody(pdfDictionary3).getIndirectReference());
            }
            if (!asDict.contains(PdfName.ZADB)) {
                PdfDictionary pdfDictionary4 = new PdfDictionary(PdfName.FONT);
                pdfDictionary4.put(PdfName.BASEFONT, PdfName.ZAPFDINGBATS);
                pdfDictionary4.put(PdfName.NAME, PdfName.ZADB);
                pdfDictionary4.put(PdfName.SUBTYPE, PdfName.TYPE1);
                asDict.put(PdfName.ZADB, addToBody(pdfDictionary4).getIndirectReference());
            }
            if (pdfDictionary.get(PdfName.DA) == null) {
                pdfDictionary.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g "));
                markUsed((PdfObject) pdfDictionary);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void expandFields(PdfFormField pdfFormField, ArrayList<PdfAnnotation> arrayList) {
        arrayList.add(pdfFormField);
        ArrayList<PdfFormField> kids = pdfFormField.getKids();
        if (kids != null) {
            for (int i = 0; i < kids.size(); i++) {
                expandFields(kids.get(i), arrayList);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d9 A[Catch:{ IOException -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0136 A[Catch:{ IOException -> 0x016f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addAnnotation(com.itextpdf.text.pdf.PdfAnnotation r11, com.itextpdf.text.pdf.PdfDictionary r12) {
        /*
            r10 = this;
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ IOException -> 0x016f }
            r0.<init>()     // Catch:{ IOException -> 0x016f }
            boolean r1 = r11.isForm()     // Catch:{ IOException -> 0x016f }
            if (r1 == 0) goto L_0x001e
            r1 = 1
            r10.fieldsAdded = r1     // Catch:{ IOException -> 0x016f }
            r10.getAcroFields()     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfFormField r11 = (com.itextpdf.text.pdf.PdfFormField) r11     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfFormField r1 = r11.getParent()     // Catch:{ IOException -> 0x016f }
            if (r1 == 0) goto L_0x001a
            return
        L_0x001a:
            r10.expandFields(r11, r0)     // Catch:{ IOException -> 0x016f }
            goto L_0x0021
        L_0x001e:
            r0.add(r11)     // Catch:{ IOException -> 0x016f }
        L_0x0021:
            r11 = 0
        L_0x0022:
            int r1 = r0.size()     // Catch:{ IOException -> 0x016f }
            if (r11 >= r1) goto L_0x016e
            java.lang.Object r1 = r0.get(r11)     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfAnnotation r1 = (com.itextpdf.text.pdf.PdfAnnotation) r1     // Catch:{ IOException -> 0x016f }
            int r2 = r1.getPlaceInPage()     // Catch:{ IOException -> 0x016f }
            if (r2 <= 0) goto L_0x003e
            com.itextpdf.text.pdf.PdfReader r12 = r10.reader     // Catch:{ IOException -> 0x016f }
            int r2 = r1.getPlaceInPage()     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfDictionary r12 = r12.getPageN(r2)     // Catch:{ IOException -> 0x016f }
        L_0x003e:
            boolean r2 = r1.isForm()     // Catch:{ IOException -> 0x016f }
            if (r2 == 0) goto L_0x0065
            boolean r2 = r1.isUsed()     // Catch:{ IOException -> 0x016f }
            if (r2 != 0) goto L_0x0055
            java.util.HashSet r2 = r1.getTemplates()     // Catch:{ IOException -> 0x016f }
            if (r2 == 0) goto L_0x0055
            java.util.HashSet<com.itextpdf.text.pdf.PdfTemplate> r3 = r10.fieldTemplates     // Catch:{ IOException -> 0x016f }
            r3.addAll(r2)     // Catch:{ IOException -> 0x016f }
        L_0x0055:
            r2 = r1
            com.itextpdf.text.pdf.PdfFormField r2 = (com.itextpdf.text.pdf.PdfFormField) r2     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfFormField r3 = r2.getParent()     // Catch:{ IOException -> 0x016f }
            if (r3 != 0) goto L_0x0065
            com.itextpdf.text.pdf.PdfIndirectReference r2 = r2.getIndirectReference()     // Catch:{ IOException -> 0x016f }
            r10.addDocumentField(r2)     // Catch:{ IOException -> 0x016f }
        L_0x0065:
            boolean r2 = r1.isAnnotation()     // Catch:{ IOException -> 0x016f }
            if (r2 == 0) goto L_0x015a
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.ANNOTS     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfObject r2 = r12.get(r2)     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfObject r2 = com.itextpdf.text.pdf.PdfReader.getPdfObject(r2, r12)     // Catch:{ IOException -> 0x016f }
            if (r2 == 0) goto L_0x0081
            boolean r3 = r2.isArray()     // Catch:{ IOException -> 0x016f }
            if (r3 != 0) goto L_0x007e
            goto L_0x0081
        L_0x007e:
            com.itextpdf.text.pdf.PdfArray r2 = (com.itextpdf.text.pdf.PdfArray) r2     // Catch:{ IOException -> 0x016f }
            goto L_0x008e
        L_0x0081:
            com.itextpdf.text.pdf.PdfArray r2 = new com.itextpdf.text.pdf.PdfArray     // Catch:{ IOException -> 0x016f }
            r2.<init>()     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.ANNOTS     // Catch:{ IOException -> 0x016f }
            r12.put(r3, r2)     // Catch:{ IOException -> 0x016f }
            r10.markUsed((com.itextpdf.text.pdf.PdfObject) r12)     // Catch:{ IOException -> 0x016f }
        L_0x008e:
            com.itextpdf.text.pdf.PdfIndirectReference r3 = r1.getIndirectReference()     // Catch:{ IOException -> 0x016f }
            r2.add((com.itextpdf.text.pdf.PdfObject) r3)     // Catch:{ IOException -> 0x016f }
            r10.markUsed((com.itextpdf.text.pdf.PdfObject) r2)     // Catch:{ IOException -> 0x016f }
            boolean r2 = r1.isUsed()     // Catch:{ IOException -> 0x016f }
            if (r2 != 0) goto L_0x015a
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.RECT     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfObject r2 = r1.get(r2)     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfRectangle r2 = (com.itextpdf.text.pdf.PdfRectangle) r2     // Catch:{ IOException -> 0x016f }
            if (r2 == 0) goto L_0x015a
            float r3 = r2.left()     // Catch:{ IOException -> 0x016f }
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x00c9
            float r3 = r2.right()     // Catch:{ IOException -> 0x016f }
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x00c9
            float r3 = r2.top()     // Catch:{ IOException -> 0x016f }
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x00c9
            float r3 = r2.bottom()     // Catch:{ IOException -> 0x016f }
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 == 0) goto L_0x015a
        L_0x00c9:
            com.itextpdf.text.pdf.PdfReader r3 = r10.reader     // Catch:{ IOException -> 0x016f }
            int r3 = r3.getPageRotation((com.itextpdf.text.pdf.PdfDictionary) r12)     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfReader r4 = r10.reader     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.Rectangle r4 = r4.getPageSizeWithRotation((com.itextpdf.text.pdf.PdfDictionary) r12)     // Catch:{ IOException -> 0x016f }
            r5 = 90
            if (r3 == r5) goto L_0x0136
            r5 = 180(0xb4, float:2.52E-43)
            if (r3 == r5) goto L_0x0107
            r5 = 270(0x10e, float:3.78E-43)
            if (r3 == r5) goto L_0x00e2
            goto L_0x015a
        L_0x00e2:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.RECT     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfRectangle r5 = new com.itextpdf.text.pdf.PdfRectangle     // Catch:{ IOException -> 0x016f }
            float r6 = r2.bottom()     // Catch:{ IOException -> 0x016f }
            float r7 = r4.getRight()     // Catch:{ IOException -> 0x016f }
            float r8 = r2.left()     // Catch:{ IOException -> 0x016f }
            float r7 = r7 - r8
            float r8 = r2.top()     // Catch:{ IOException -> 0x016f }
            float r4 = r4.getRight()     // Catch:{ IOException -> 0x016f }
            float r2 = r2.right()     // Catch:{ IOException -> 0x016f }
            float r4 = r4 - r2
            r5.<init>(r6, r7, r8, r4)     // Catch:{ IOException -> 0x016f }
            r1.put(r3, r5)     // Catch:{ IOException -> 0x016f }
            goto L_0x015a
        L_0x0107:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.RECT     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfRectangle r5 = new com.itextpdf.text.pdf.PdfRectangle     // Catch:{ IOException -> 0x016f }
            float r6 = r4.getRight()     // Catch:{ IOException -> 0x016f }
            float r7 = r2.left()     // Catch:{ IOException -> 0x016f }
            float r6 = r6 - r7
            float r7 = r4.getTop()     // Catch:{ IOException -> 0x016f }
            float r8 = r2.bottom()     // Catch:{ IOException -> 0x016f }
            float r7 = r7 - r8
            float r8 = r4.getRight()     // Catch:{ IOException -> 0x016f }
            float r9 = r2.right()     // Catch:{ IOException -> 0x016f }
            float r8 = r8 - r9
            float r4 = r4.getTop()     // Catch:{ IOException -> 0x016f }
            float r2 = r2.top()     // Catch:{ IOException -> 0x016f }
            float r4 = r4 - r2
            r5.<init>(r6, r7, r8, r4)     // Catch:{ IOException -> 0x016f }
            r1.put(r3, r5)     // Catch:{ IOException -> 0x016f }
            goto L_0x015a
        L_0x0136:
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.RECT     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfRectangle r5 = new com.itextpdf.text.pdf.PdfRectangle     // Catch:{ IOException -> 0x016f }
            float r6 = r4.getTop()     // Catch:{ IOException -> 0x016f }
            float r7 = r2.top()     // Catch:{ IOException -> 0x016f }
            float r6 = r6 - r7
            float r7 = r2.right()     // Catch:{ IOException -> 0x016f }
            float r4 = r4.getTop()     // Catch:{ IOException -> 0x016f }
            float r8 = r2.bottom()     // Catch:{ IOException -> 0x016f }
            float r4 = r4 - r8
            float r2 = r2.left()     // Catch:{ IOException -> 0x016f }
            r5.<init>(r6, r7, r4, r2)     // Catch:{ IOException -> 0x016f }
            r1.put(r3, r5)     // Catch:{ IOException -> 0x016f }
        L_0x015a:
            boolean r2 = r1.isUsed()     // Catch:{ IOException -> 0x016f }
            if (r2 != 0) goto L_0x016a
            r1.setUsed()     // Catch:{ IOException -> 0x016f }
            com.itextpdf.text.pdf.PdfIndirectReference r2 = r1.getIndirectReference()     // Catch:{ IOException -> 0x016f }
            r10.addToBody((com.itextpdf.text.pdf.PdfObject) r1, (com.itextpdf.text.pdf.PdfIndirectReference) r2)     // Catch:{ IOException -> 0x016f }
        L_0x016a:
            int r11 = r11 + 1
            goto L_0x0022
        L_0x016e:
            return
        L_0x016f:
            r11 = move-exception
            com.itextpdf.text.ExceptionConverter r12 = new com.itextpdf.text.ExceptionConverter
            r12.<init>(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfStamperImp.addAnnotation(com.itextpdf.text.pdf.PdfAnnotation, com.itextpdf.text.pdf.PdfDictionary):void");
    }

    /* access modifiers changed from: package-private */
    public void addAnnotation(PdfAnnotation pdfAnnotation, int i) {
        if (pdfAnnotation.isAnnotation()) {
            pdfAnnotation.setPage(i);
        }
        addAnnotation(pdfAnnotation, this.reader.getPageN(i));
    }

    private void outlineTravel(PRIndirectReference pRIndirectReference) {
        while (pRIndirectReference != null) {
            PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObjectRelease((PdfObject) pRIndirectReference);
            PRIndirectReference pRIndirectReference2 = (PRIndirectReference) pdfDictionary.get(PdfName.FIRST);
            if (pRIndirectReference2 != null) {
                outlineTravel(pRIndirectReference2);
            }
            PdfReader.killIndirect(pdfDictionary.get(PdfName.DEST));
            PdfReader.killIndirect(pdfDictionary.get(PdfName.A));
            PdfReader.killIndirect(pRIndirectReference);
            pRIndirectReference = (PRIndirectReference) pdfDictionary.get(PdfName.NEXT);
        }
    }

    /* access modifiers changed from: package-private */
    public void deleteOutlines() {
        PdfDictionary catalog = this.reader.getCatalog();
        PdfObject pdfObject = catalog.get(PdfName.OUTLINES);
        if (pdfObject != null) {
            if (pdfObject instanceof PRIndirectReference) {
                PRIndirectReference pRIndirectReference = (PRIndirectReference) pdfObject;
                outlineTravel(pRIndirectReference);
                PdfReader.killIndirect(pRIndirectReference);
            }
            catalog.remove(PdfName.OUTLINES);
            markUsed((PdfObject) catalog);
        }
    }

    /* access modifiers changed from: protected */
    public void setJavaScript() throws IOException {
        HashMap<String, PdfObject> documentLevelJS = this.pdf.getDocumentLevelJS();
        if (!documentLevelJS.isEmpty()) {
            PdfDictionary catalog = this.reader.getCatalog();
            PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObject(catalog.get(PdfName.NAMES), catalog);
            if (pdfDictionary == null) {
                pdfDictionary = new PdfDictionary();
                catalog.put(PdfName.NAMES, pdfDictionary);
                markUsed((PdfObject) catalog);
            }
            markUsed((PdfObject) pdfDictionary);
            pdfDictionary.put(PdfName.JAVASCRIPT, addToBody(PdfNameTree.writeTree(documentLevelJS, this)).getIndirectReference());
        }
    }

    /* access modifiers changed from: protected */
    public void addFileAttachments() throws IOException {
        HashMap<String, PdfObject> documentFileAttachment = this.pdf.getDocumentFileAttachment();
        if (!documentFileAttachment.isEmpty()) {
            PdfDictionary catalog = this.reader.getCatalog();
            PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObject(catalog.get(PdfName.NAMES), catalog);
            if (pdfDictionary == null) {
                pdfDictionary = new PdfDictionary();
                catalog.put(PdfName.NAMES, pdfDictionary);
                markUsed((PdfObject) catalog);
            }
            markUsed((PdfObject) pdfDictionary);
            HashMap<String, PdfObject> readTree = PdfNameTree.readTree((PdfDictionary) PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.EMBEDDEDFILES)));
            for (Map.Entry next : documentFileAttachment.entrySet()) {
                int i = 0;
                StringBuilder sb = new StringBuilder((String) next.getKey());
                while (readTree.containsKey(sb.toString())) {
                    i++;
                    sb.append(" ");
                    sb.append(i);
                }
                readTree.put(sb.toString(), next.getValue());
            }
            PdfDictionary writeTree = PdfNameTree.writeTree(readTree, this);
            PdfObject pdfObject = pdfDictionary.get(PdfName.EMBEDDEDFILES);
            if (pdfObject != null) {
                PdfReader.killIndirect(pdfObject);
            }
            pdfDictionary.put(PdfName.EMBEDDEDFILES, addToBody(writeTree).getIndirectReference());
        }
    }

    /* access modifiers changed from: package-private */
    public void makePackage(PdfCollection pdfCollection) {
        this.reader.getCatalog().put(PdfName.COLLECTION, pdfCollection);
    }

    /* access modifiers changed from: protected */
    public void setOutlines() throws IOException {
        if (this.newBookmarks != null) {
            deleteOutlines();
            if (!this.newBookmarks.isEmpty()) {
                PdfDictionary catalog = this.reader.getCatalog();
                writeOutlines(catalog, catalog.get(PdfName.DESTS) != null);
                markUsed((PdfObject) catalog);
            }
        }
    }

    public void setViewerPreferences(int i) {
        this.useVp = true;
        this.viewerPreferences.setViewerPreferences(i);
    }

    public void addViewerPreference(PdfName pdfName, PdfObject pdfObject) {
        this.useVp = true;
        this.viewerPreferences.addViewerPreference(pdfName, pdfObject);
    }

    public void setSigFlags(int i) {
        this.sigFlags = i | this.sigFlags;
    }

    public void setPageAction(PdfName pdfName, PdfAction pdfAction) throws PdfException {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.setpageaction.pdfname.actiontype.pdfaction.action.int.page", new Object[0]));
    }

    /* access modifiers changed from: package-private */
    public void setPageAction(PdfName pdfName, PdfAction pdfAction, int i) throws PdfException {
        if (pdfName.equals(PAGE_OPEN) || pdfName.equals(PAGE_CLOSE)) {
            PdfDictionary pageN = this.reader.getPageN(i);
            PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObject(pageN.get(PdfName.AA), pageN);
            if (pdfDictionary == null) {
                pdfDictionary = new PdfDictionary();
                pageN.put(PdfName.AA, pdfDictionary);
                markUsed((PdfObject) pageN);
            }
            pdfDictionary.put(pdfName, pdfAction);
            markUsed((PdfObject) pdfDictionary);
            return;
        }
        throw new PdfException(MessageLocalization.getComposedMessage("invalid.page.additional.action.type.1", pdfName.toString()));
    }

    public void setDuration(int i) {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.setpageaction.pdfname.actiontype.pdfaction.action.int.page", new Object[0]));
    }

    public void setTransition(PdfTransition pdfTransition) {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.setpageaction.pdfname.actiontype.pdfaction.action.int.page", new Object[0]));
    }

    /* access modifiers changed from: package-private */
    public void setDuration(int i, int i2) {
        PdfDictionary pageN = this.reader.getPageN(i2);
        if (i < 0) {
            pageN.remove(PdfName.DUR);
        } else {
            pageN.put(PdfName.DUR, new PdfNumber(i));
        }
        markUsed((PdfObject) pageN);
    }

    /* access modifiers changed from: package-private */
    public void setTransition(PdfTransition pdfTransition, int i) {
        PdfDictionary pageN = this.reader.getPageN(i);
        if (pdfTransition == null) {
            pageN.remove(PdfName.TRANS);
        } else {
            pageN.put(PdfName.TRANS, pdfTransition.getTransitionDictionary());
        }
        markUsed((PdfObject) pageN);
    }

    /* access modifiers changed from: protected */
    public void markUsed(PdfObject pdfObject) {
        PRIndirectReference pRIndirectReference;
        if (this.append && pdfObject != null) {
            if (pdfObject.type() == 10) {
                pRIndirectReference = (PRIndirectReference) pdfObject;
            } else {
                pRIndirectReference = pdfObject.getIndRef();
            }
            if (pRIndirectReference != null) {
                this.marked.put(pRIndirectReference.getNumber(), 1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void markUsed(int i) {
        if (this.append) {
            this.marked.put(i, 1);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isAppend() {
        return this.append;
    }

    public PdfReader getPdfReader() {
        return this.reader;
    }

    public void setAdditionalAction(PdfName pdfName, PdfAction pdfAction) throws PdfException {
        if (pdfName.equals(DOCUMENT_CLOSE) || pdfName.equals(WILL_SAVE) || pdfName.equals(DID_SAVE) || pdfName.equals(WILL_PRINT) || pdfName.equals(DID_PRINT)) {
            PdfDictionary asDict = this.reader.getCatalog().getAsDict(PdfName.AA);
            if (asDict == null) {
                if (pdfAction != null) {
                    asDict = new PdfDictionary();
                    this.reader.getCatalog().put(PdfName.AA, asDict);
                } else {
                    return;
                }
            }
            markUsed((PdfObject) asDict);
            if (pdfAction == null) {
                asDict.remove(pdfName);
            } else {
                asDict.put(pdfName, pdfAction);
            }
        } else {
            throw new PdfException(MessageLocalization.getComposedMessage("invalid.additional.action.type.1", pdfName.toString()));
        }
    }

    public void setOpenAction(PdfAction pdfAction) {
        this.openAction = pdfAction;
    }

    public void setOpenAction(String str) {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("open.actions.by.name.are.not.supported", new Object[0]));
    }

    public void setThumbnail(Image image) {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.pdfstamper.setthumbnail", new Object[0]));
    }

    /* access modifiers changed from: package-private */
    public void setThumbnail(Image image, int i) throws PdfException, DocumentException {
        PdfIndirectReference imageReference = getImageReference(addDirectImageSimple(image));
        this.reader.resetReleasePage();
        this.reader.getPageN(i).put(PdfName.THUMB, imageReference);
        this.reader.resetReleasePage();
    }

    public PdfContentByte getDirectContentUnder() {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.pdfstamper.getundercontent.or.pdfstamper.getovercontent", new Object[0]));
    }

    public PdfContentByte getDirectContent() {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.pdfstamper.getundercontent.or.pdfstamper.getovercontent", new Object[0]));
    }

    /* access modifiers changed from: protected */
    public void readOCProperties() {
        PdfDictionary asDict;
        if (this.documentOCG.isEmpty() && (asDict = this.reader.getCatalog().getAsDict(PdfName.OCPROPERTIES)) != null) {
            PdfArray asArray = asDict.getAsArray(PdfName.OCGS);
            HashMap hashMap = new HashMap();
            ListIterator<PdfObject> listIterator = asArray.listIterator();
            while (listIterator.hasNext()) {
                PdfIndirectReference pdfIndirectReference = (PdfIndirectReference) listIterator.next();
                PdfLayer pdfLayer = new PdfLayer((String) null);
                pdfLayer.setRef(pdfIndirectReference);
                pdfLayer.setOnPanel(false);
                pdfLayer.merge((PdfDictionary) PdfReader.getPdfObject((PdfObject) pdfIndirectReference));
                hashMap.put(pdfIndirectReference.toString(), pdfLayer);
            }
            PdfDictionary asDict2 = asDict.getAsDict(PdfName.D);
            PdfArray asArray2 = asDict2.getAsArray(PdfName.OFF);
            if (asArray2 != null) {
                ListIterator<PdfObject> listIterator2 = asArray2.listIterator();
                while (listIterator2.hasNext()) {
                    ((PdfLayer) hashMap.get(((PdfIndirectReference) listIterator2.next()).toString())).setOn(false);
                }
            }
            PdfArray asArray3 = asDict2.getAsArray(PdfName.ORDER);
            if (asArray3 != null) {
                addOrder((PdfLayer) null, asArray3, hashMap);
            }
            this.documentOCG.addAll(hashMap.values());
            this.OCGRadioGroup = asDict2.getAsArray(PdfName.RBGROUPS);
            if (this.OCGRadioGroup == null) {
                this.OCGRadioGroup = new PdfArray();
            }
            this.OCGLocked = asDict2.getAsArray(PdfName.LOCKED);
            if (this.OCGLocked == null) {
                this.OCGLocked = new PdfArray();
            }
        }
    }

    private void addOrder(PdfLayer pdfLayer, PdfArray pdfArray, Map<String, PdfLayer> map) {
        int i = 0;
        while (i < pdfArray.size()) {
            PdfObject pdfObject = pdfArray.getPdfObject(i);
            if (pdfObject.isIndirect()) {
                PdfLayer pdfLayer2 = map.get(pdfObject.toString());
                if (pdfLayer2 != null) {
                    pdfLayer2.setOnPanel(true);
                    registerLayer(pdfLayer2);
                    if (pdfLayer != null) {
                        pdfLayer.addChild(pdfLayer2);
                    }
                    int i2 = i + 1;
                    if (pdfArray.size() > i2 && pdfArray.getPdfObject(i2).isArray()) {
                        addOrder(pdfLayer2, (PdfArray) pdfArray.getPdfObject(i2), map);
                        i = i2;
                    }
                }
            } else if (pdfObject.isArray()) {
                PdfArray pdfArray2 = (PdfArray) pdfObject;
                if (!pdfArray2.isEmpty()) {
                    PdfObject pdfObject2 = pdfArray2.getPdfObject(0);
                    if (pdfObject2.isString()) {
                        PdfLayer pdfLayer3 = new PdfLayer(pdfObject2.toString());
                        pdfLayer3.setOnPanel(true);
                        registerLayer(pdfLayer3);
                        if (pdfLayer != null) {
                            pdfLayer.addChild(pdfLayer3);
                        }
                        PdfArray pdfArray3 = new PdfArray();
                        ListIterator<PdfObject> listIterator = pdfArray2.listIterator();
                        while (listIterator.hasNext()) {
                            pdfArray3.add(listIterator.next());
                        }
                        addOrder(pdfLayer3, pdfArray3, map);
                    } else {
                        addOrder(pdfLayer, (PdfArray) pdfObject2, map);
                    }
                } else {
                    return;
                }
            } else {
                continue;
            }
            i++;
        }
    }

    public Map<String, PdfLayer> getPdfLayers() {
        String str;
        if (!this.originalLayersAreRead) {
            this.originalLayersAreRead = true;
            readOCProperties();
        }
        HashMap hashMap = new HashMap();
        Iterator it = this.documentOCG.iterator();
        while (it.hasNext()) {
            PdfLayer pdfLayer = (PdfLayer) ((PdfOCG) it.next());
            if (pdfLayer.getTitle() == null) {
                str = pdfLayer.getAsString(PdfName.NAME).toString();
            } else {
                str = pdfLayer.getTitle();
            }
            if (hashMap.containsKey(str)) {
                int i = 2;
                String str2 = str + "(" + 2 + ")";
                while (hashMap.containsKey(str2)) {
                    i++;
                    str2 = str + "(" + i + ")";
                }
                str = str2;
            }
            hashMap.put(str, pdfLayer);
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void registerLayer(PdfOCG pdfOCG) {
        if (!this.originalLayersAreRead) {
            this.originalLayersAreRead = true;
            readOCProperties();
        }
        super.registerLayer(pdfOCG);
    }

    public void createXmpMetadata() {
        try {
            this.xmpWriter = createXmpWriter((ByteArrayOutputStream) null, this.reader.getInfo());
            this.xmpMetadata = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public HashMap<Object, PdfObject> getNamedDestinations() {
        return this.namedDestinations;
    }

    /* access modifiers changed from: protected */
    public void updateNamedDestinations() throws IOException {
        PdfDictionary asDict = this.reader.getCatalog().getAsDict(PdfName.NAMES);
        if (asDict != null) {
            asDict = asDict.getAsDict(PdfName.DESTS);
        }
        if (asDict == null) {
            asDict = this.reader.getCatalog().getAsDict(PdfName.DESTS);
        }
        if (asDict == null) {
            asDict = new PdfDictionary();
            PdfDictionary pdfDictionary = new PdfDictionary();
            asDict.put(PdfName.NAMES, new PdfArray());
            pdfDictionary.put(PdfName.DESTS, asDict);
            this.reader.getCatalog().put(PdfName.NAMES, pdfDictionary);
        }
        PdfArray lastChildInNameTree = getLastChildInNameTree(asDict);
        for (Object next : this.namedDestinations.keySet()) {
            lastChildInNameTree.add((PdfObject) new PdfString(next.toString()));
            lastChildInNameTree.add((PdfObject) addToBody(this.namedDestinations.get(next), getPdfIndirectReference()).getIndirectReference());
        }
    }

    private PdfArray getLastChildInNameTree(PdfDictionary pdfDictionary) {
        PdfArray asArray = pdfDictionary.getAsArray(PdfName.KIDS);
        if (asArray != null) {
            return getLastChildInNameTree(asArray.getAsDict(asArray.size() - 1));
        }
        return pdfDictionary.getAsArray(PdfName.NAMES);
    }

    static class PageStamp {
        StampContent over;
        PdfDictionary pageN;
        PageResources pageResources;
        int replacePoint = 0;
        StampContent under;

        PageStamp(PdfStamperImp pdfStamperImp, PdfReader pdfReader, PdfDictionary pdfDictionary) {
            this.pageN = pdfDictionary;
            this.pageResources = new PageResources();
            this.pageResources.setOriginalResources(pdfDictionary.getAsDict(PdfName.RESOURCES), pdfStamperImp.namePtr);
        }
    }
}
