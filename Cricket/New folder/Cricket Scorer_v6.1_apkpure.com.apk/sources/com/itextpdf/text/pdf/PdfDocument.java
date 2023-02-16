package com.itextpdf.text.pdf;

import com.itextpdf.text.AccessibleElementId;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListLabel;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.Version;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.io.TempFileCache;
import com.itextpdf.text.pdf.collection.PdfCollection;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import com.itextpdf.text.pdf.internal.PdfAnnotationsImp;
import com.itextpdf.text.pdf.internal.PdfViewerPreferencesImp;
import com.itextpdf.text.xml.xmp.PdfProperties;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class PdfDocument extends Document {
    protected static final DecimalFormat SIXTEEN_DIGITS = new DecimalFormat("0000000000000000");
    static final String hangingPunctuation = ".,;:'";
    protected PdfDictionary additionalActions;
    protected int alignment = 0;
    protected PdfAction anchorAction = null;
    PdfAnnotationsImp annotationsImp;
    private PdfBody body;
    protected HashMap<String, PdfRectangle> boxSize = new HashMap<>();
    protected PdfCollection collection;
    protected float currentHeight = 0.0f;
    protected PdfOutline currentOutline;
    protected HashMap<String, PdfObject> documentFileAttachment = new HashMap<>();
    protected HashMap<String, PdfObject> documentLevelJS = new HashMap<>();
    private HashMap<AccessibleElementId, AccessibleElementId> elementsParents = new HashMap<>();
    private TempFileCache externalCache;
    private HashMap<AccessibleElementId, TempFileCache.ObjectPosition> externallyStoredStructElements = new HashMap<>();
    protected boolean firstPageEvent = true;
    private ArrayList<Element> floatingElements = new ArrayList<>();
    protected PdfContentByte graphics;
    protected float imageEnd = -1.0f;
    protected Image imageWait = null;
    protected Indentation indentation = new Indentation();
    protected PdfInfo info = new PdfInfo();
    protected boolean isSectionTitle = false;
    private boolean isToUseExternalCache = false;
    int jsCounter;
    protected PdfString language;
    protected int lastElementType = -1;
    protected float leading = 0.0f;
    private Stack<Float> leadingStack = new Stack<>();
    protected PdfLine line = null;
    protected ArrayList<PdfLine> lines = new ArrayList<>();
    protected TreeMap<String, Destination> localDestinations = new TreeMap<>();
    protected HashMap<Object, Integer> markPoints = new HashMap<>();
    protected float nextMarginBottom;
    protected float nextMarginLeft;
    protected float nextMarginRight;
    protected float nextMarginTop;
    protected Rectangle nextPageSize = null;
    protected PdfAction openActionAction;
    protected String openActionName;
    protected boolean openMCDocument = false;
    protected PdfDictionary pageAA = null;
    private boolean pageEmpty = true;
    protected PdfPageLabels pageLabels;
    protected PageResources pageResources;
    protected PdfOutline rootOutline;
    protected boolean strictImageSequence = false;
    private HashMap<AccessibleElementId, PdfStructureElement> structElements = new HashMap<>();
    protected HashMap<Object, int[]> structParentIndices = new HashMap<>();
    protected TabSettings tabSettings;
    protected PdfContentByte text;
    protected int textEmptySize;
    protected HashMap<String, PdfRectangle> thisBoxSize = new HashMap<>();
    protected PdfViewerPreferencesImp viewerPreferences = new PdfViewerPreferencesImp();
    protected PdfWriter writer;

    public static class Indentation {
        float imageIndentLeft = 0.0f;
        float imageIndentRight = 0.0f;
        float indentBottom = 0.0f;
        float indentLeft = 0.0f;
        float indentRight = 0.0f;
        float indentTop = 0.0f;
        float listIndentLeft = 0.0f;
        float sectionIndentLeft = 0.0f;
        float sectionIndentRight = 0.0f;
    }

    public static class PdfInfo extends PdfDictionary {
        PdfInfo() {
            addProducer();
            addCreationDate();
        }

        PdfInfo(String str, String str2, String str3) {
            this();
            addTitle(str2);
            addSubject(str3);
            addAuthor(str);
        }

        /* access modifiers changed from: package-private */
        public void addTitle(String str) {
            put(PdfName.TITLE, new PdfString(str, PdfObject.TEXT_UNICODE));
        }

        /* access modifiers changed from: package-private */
        public void addSubject(String str) {
            put(PdfName.SUBJECT, new PdfString(str, PdfObject.TEXT_UNICODE));
        }

        /* access modifiers changed from: package-private */
        public void addKeywords(String str) {
            put(PdfName.KEYWORDS, new PdfString(str, PdfObject.TEXT_UNICODE));
        }

        /* access modifiers changed from: package-private */
        public void addAuthor(String str) {
            put(PdfName.AUTHOR, new PdfString(str, PdfObject.TEXT_UNICODE));
        }

        /* access modifiers changed from: package-private */
        public void addCreator(String str) {
            put(PdfName.CREATOR, new PdfString(str, PdfObject.TEXT_UNICODE));
        }

        /* access modifiers changed from: package-private */
        public void addProducer() {
            put(PdfName.PRODUCER, new PdfString(Version.getInstance().getVersion()));
        }

        /* access modifiers changed from: package-private */
        public void addCreationDate() {
            PdfDate pdfDate = new PdfDate();
            put(PdfName.CREATIONDATE, pdfDate);
            put(PdfName.MODDATE, pdfDate);
        }

        /* access modifiers changed from: package-private */
        public void addkey(String str, String str2) {
            if (!str.equals(PdfProperties.PRODUCER) && !str.equals("CreationDate")) {
                put(new PdfName(str), new PdfString(str2, PdfObject.TEXT_UNICODE));
            }
        }
    }

    static class PdfCatalog extends PdfDictionary {
        PdfWriter writer;

        PdfCatalog(PdfIndirectReference pdfIndirectReference, PdfWriter pdfWriter) {
            super(CATALOG);
            this.writer = pdfWriter;
            put(PdfName.PAGES, pdfIndirectReference);
        }

        /* access modifiers changed from: package-private */
        public void addNames(TreeMap<String, Destination> treeMap, HashMap<String, PdfObject> hashMap, HashMap<String, PdfObject> hashMap2, PdfWriter pdfWriter) {
            if (!treeMap.isEmpty() || !hashMap.isEmpty() || !hashMap2.isEmpty()) {
                try {
                    PdfDictionary pdfDictionary = new PdfDictionary();
                    if (!treeMap.isEmpty()) {
                        HashMap hashMap3 = new HashMap();
                        for (Map.Entry next : treeMap.entrySet()) {
                            String str = (String) next.getKey();
                            Destination destination = (Destination) next.getValue();
                            if (destination.destination != null) {
                                hashMap3.put(str, destination.reference);
                            }
                        }
                        if (hashMap3.size() > 0) {
                            pdfDictionary.put(PdfName.DESTS, pdfWriter.addToBody(PdfNameTree.writeTree(hashMap3, pdfWriter)).getIndirectReference());
                        }
                    }
                    if (!hashMap.isEmpty()) {
                        pdfDictionary.put(PdfName.JAVASCRIPT, pdfWriter.addToBody(PdfNameTree.writeTree(hashMap, pdfWriter)).getIndirectReference());
                    }
                    if (!hashMap2.isEmpty()) {
                        pdfDictionary.put(PdfName.EMBEDDEDFILES, pdfWriter.addToBody(PdfNameTree.writeTree(hashMap2, pdfWriter)).getIndirectReference());
                    }
                    if (pdfDictionary.size() > 0) {
                        put(PdfName.NAMES, pdfWriter.addToBody(pdfDictionary).getIndirectReference());
                    }
                } catch (IOException e) {
                    throw new ExceptionConverter(e);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setOpenAction(PdfAction pdfAction) {
            put(PdfName.OPENACTION, pdfAction);
        }

        /* access modifiers changed from: package-private */
        public void setAdditionalActions(PdfDictionary pdfDictionary) {
            try {
                put(PdfName.AA, this.writer.addToBody(pdfDictionary).getIndirectReference());
            } catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        }
    }

    public PdfDocument() {
        addProducer();
        addCreationDate();
    }

    public void addWriter(PdfWriter pdfWriter) throws DocumentException {
        if (this.writer == null) {
            this.writer = pdfWriter;
            this.annotationsImp = new PdfAnnotationsImp(pdfWriter);
            return;
        }
        throw new DocumentException(MessageLocalization.getComposedMessage("you.can.only.add.a.writer.to.a.pdfdocument.once", new Object[0]));
    }

    public float getLeading() {
        return this.leading;
    }

    /* access modifiers changed from: package-private */
    public void setLeading(float f) {
        this.leading = f;
    }

    /* access modifiers changed from: protected */
    public void pushLeading() {
        this.leadingStack.push(Float.valueOf(this.leading));
    }

    /* access modifiers changed from: protected */
    public void popLeading() {
        this.leading = this.leadingStack.pop().floatValue();
        if (this.leadingStack.size() > 0) {
            this.leading = this.leadingStack.peek().floatValue();
        }
    }

    public TabSettings getTabSettings() {
        return this.tabSettings;
    }

    public void setTabSettings(TabSettings tabSettings2) {
        this.tabSettings = tabSettings2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean add(com.itextpdf.text.Element r13) throws com.itextpdf.text.DocumentException {
        /*
            r12 = this;
            com.itextpdf.text.pdf.PdfWriter r0 = r12.writer
            r1 = 0
            if (r0 == 0) goto L_0x000e
            com.itextpdf.text.pdf.PdfWriter r0 = r12.writer
            boolean r0 = r0.isPaused()
            if (r0 == 0) goto L_0x000e
            return r1
        L_0x000e:
            int r0 = r13.type()     // Catch:{ Exception -> 0x0603 }
            r2 = 37
            if (r0 == r2) goto L_0x0019
            r12.flushFloatingElements()     // Catch:{ Exception -> 0x0603 }
        L_0x0019:
            int r0 = r13.type()     // Catch:{ Exception -> 0x0603 }
            r2 = 23
            r3 = 1
            if (r0 == r2) goto L_0x05e0
            r2 = 50
            if (r0 == r2) goto L_0x05c9
            r2 = 55
            r4 = 0
            if (r0 == r2) goto L_0x059a
            r2 = 666(0x29a, float:9.33E-43)
            if (r0 == r2) goto L_0x058d
            switch(r0) {
                case 0: goto L_0x0578;
                case 1: goto L_0x056a;
                case 2: goto L_0x055c;
                case 3: goto L_0x054e;
                case 4: goto L_0x0540;
                case 5: goto L_0x0539;
                case 6: goto L_0x0532;
                case 7: goto L_0x0524;
                case 8: goto L_0x0518;
                default: goto L_0x0032;
            }     // Catch:{ Exception -> 0x0603 }
        L_0x0032:
            switch(r0) {
                case 10: goto L_0x04de;
                case 11: goto L_0x04b4;
                case 12: goto L_0x034a;
                case 13: goto L_0x022a;
                case 14: goto L_0x01ca;
                case 15: goto L_0x0127;
                case 16: goto L_0x022a;
                case 17: goto L_0x0103;
                default: goto L_0x0035;
            }     // Catch:{ Exception -> 0x0603 }
        L_0x0035:
            switch(r0) {
                case 29: goto L_0x00a4;
                case 30: goto L_0x0098;
                default: goto L_0x0038;
            }     // Catch:{ Exception -> 0x0603 }
        L_0x0038:
            switch(r0) {
                case 32: goto L_0x0058;
                case 33: goto L_0x0058;
                case 34: goto L_0x0058;
                case 35: goto L_0x0058;
                case 36: goto L_0x0058;
                case 37: goto L_0x0048;
                case 38: goto L_0x003c;
                default: goto L_0x003b;
            }     // Catch:{ Exception -> 0x0603 }
        L_0x003b:
            goto L_0x0047
        L_0x003c:
            com.itextpdf.text.pdf.PdfBody r13 = (com.itextpdf.text.pdf.PdfBody) r13     // Catch:{ Exception -> 0x0603 }
            r12.body = r13     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r13 = r12.graphics     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfBody r0 = r12.body     // Catch:{ Exception -> 0x0603 }
            r13.rectangle(r0)     // Catch:{ Exception -> 0x0603 }
        L_0x0047:
            return r1
        L_0x0048:
            r12.ensureNewLine()     // Catch:{ Exception -> 0x0603 }
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            r0 = r13
            com.itextpdf.text.pdf.PdfDiv r0 = (com.itextpdf.text.pdf.PdfDiv) r0     // Catch:{ Exception -> 0x0603 }
            r12.addDiv(r0)     // Catch:{ Exception -> 0x0603 }
            r12.pageEmpty = r1     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0058:
            com.itextpdf.text.pdf.PdfWriter r0 = r12.writer     // Catch:{ Exception -> 0x0603 }
            boolean r0 = isTagged(r0)     // Catch:{ Exception -> 0x0603 }
            if (r0 == 0) goto L_0x0074
            r0 = r13
            com.itextpdf.text.Image r0 = (com.itextpdf.text.Image) r0     // Catch:{ Exception -> 0x0603 }
            boolean r0 = r0.isImgTemplate()     // Catch:{ Exception -> 0x0603 }
            if (r0 != 0) goto L_0x0074
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r0 = r12.text     // Catch:{ Exception -> 0x0603 }
            r1 = r13
            com.itextpdf.text.Image r1 = (com.itextpdf.text.Image) r1     // Catch:{ Exception -> 0x0603 }
            r0.openMCBlock(r1)     // Catch:{ Exception -> 0x0603 }
        L_0x0074:
            r0 = r13
            com.itextpdf.text.Image r0 = (com.itextpdf.text.Image) r0     // Catch:{ Exception -> 0x0603 }
            r12.add((com.itextpdf.text.Image) r0)     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r0 = r12.writer     // Catch:{ Exception -> 0x0603 }
            boolean r0 = isTagged(r0)     // Catch:{ Exception -> 0x0603 }
            if (r0 == 0) goto L_0x05fc
            r0 = r13
            com.itextpdf.text.Image r0 = (com.itextpdf.text.Image) r0     // Catch:{ Exception -> 0x0603 }
            boolean r0 = r0.isImgTemplate()     // Catch:{ Exception -> 0x0603 }
            if (r0 != 0) goto L_0x05fc
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r0 = r12.text     // Catch:{ Exception -> 0x0603 }
            r1 = r13
            com.itextpdf.text.Image r1 = (com.itextpdf.text.Image) r1     // Catch:{ Exception -> 0x0603 }
            r0.closeMCBlock(r1)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0098:
            r0 = r13
            com.itextpdf.text.Rectangle r0 = (com.itextpdf.text.Rectangle) r0     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r2 = r12.graphics     // Catch:{ Exception -> 0x0603 }
            r2.rectangle(r0)     // Catch:{ Exception -> 0x0603 }
            r12.pageEmpty = r1     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x00a4:
            com.itextpdf.text.pdf.PdfLine r0 = r12.line     // Catch:{ Exception -> 0x0603 }
            if (r0 != 0) goto L_0x00ab
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
        L_0x00ab:
            r0 = r13
            com.itextpdf.text.Annotation r0 = (com.itextpdf.text.Annotation) r0     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.Rectangle r2 = new com.itextpdf.text.Rectangle     // Catch:{ Exception -> 0x0603 }
            r2.<init>(r4, r4)     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfLine r4 = r12.line     // Catch:{ Exception -> 0x0603 }
            if (r4 == 0) goto L_0x00f4
            com.itextpdf.text.Rectangle r2 = new com.itextpdf.text.Rectangle     // Catch:{ Exception -> 0x0603 }
            float r4 = r12.indentRight()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfLine r5 = r12.line     // Catch:{ Exception -> 0x0603 }
            float r5 = r5.widthLeft()     // Catch:{ Exception -> 0x0603 }
            float r4 = r4 - r5
            float r4 = r0.llx(r4)     // Catch:{ Exception -> 0x0603 }
            float r5 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r6 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r5 = r5 - r6
            r6 = 1101004800(0x41a00000, float:20.0)
            float r5 = r5 - r6
            float r5 = r0.ury(r5)     // Catch:{ Exception -> 0x0603 }
            float r7 = r12.indentRight()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfLine r8 = r12.line     // Catch:{ Exception -> 0x0603 }
            float r8 = r8.widthLeft()     // Catch:{ Exception -> 0x0603 }
            float r7 = r7 - r8
            float r7 = r7 + r6
            float r6 = r0.urx(r7)     // Catch:{ Exception -> 0x0603 }
            float r7 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r8 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r7 = r7 - r8
            float r7 = r0.lly(r7)     // Catch:{ Exception -> 0x0603 }
            r2.<init>(r4, r5, r6, r7)     // Catch:{ Exception -> 0x0603 }
        L_0x00f4:
            com.itextpdf.text.pdf.PdfWriter r4 = r12.writer     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfAnnotation r0 = com.itextpdf.text.pdf.internal.PdfAnnotationsImp.convertAnnotation(r4, r0, r2)     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.internal.PdfAnnotationsImp r2 = r12.annotationsImp     // Catch:{ Exception -> 0x0603 }
            r2.addPlainAnnotation(r0)     // Catch:{ Exception -> 0x0603 }
            r12.pageEmpty = r1     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0103:
            r0 = r13
            com.itextpdf.text.Anchor r0 = (com.itextpdf.text.Anchor) r0     // Catch:{ Exception -> 0x0603 }
            java.lang.String r1 = r0.getReference()     // Catch:{ Exception -> 0x0603 }
            float r0 = r0.getLeading()     // Catch:{ Exception -> 0x0603 }
            r12.leading = r0     // Catch:{ Exception -> 0x0603 }
            r12.pushLeading()     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x011c
            com.itextpdf.text.pdf.PdfAction r0 = new com.itextpdf.text.pdf.PdfAction     // Catch:{ Exception -> 0x0603 }
            r0.<init>((java.lang.String) r1)     // Catch:{ Exception -> 0x0603 }
            r12.anchorAction = r0     // Catch:{ Exception -> 0x0603 }
        L_0x011c:
            r13.process(r12)     // Catch:{ Exception -> 0x0603 }
            r0 = 0
            r12.anchorAction = r0     // Catch:{ Exception -> 0x0603 }
            r12.popLeading()     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0127:
            r0 = r13
            com.itextpdf.text.ListItem r0 = (com.itextpdf.text.ListItem) r0     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r1 = r12.writer     // Catch:{ Exception -> 0x0603 }
            boolean r1 = isTagged(r1)     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x013a
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r1 = r12.text     // Catch:{ Exception -> 0x0603 }
            r1.openMCBlock(r0)     // Catch:{ Exception -> 0x0603 }
        L_0x013a:
            float r1 = r0.getSpacingBefore()     // Catch:{ Exception -> 0x0603 }
            float r2 = r12.leading     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.Font r4 = r0.getFont()     // Catch:{ Exception -> 0x0603 }
            r12.addSpacing(r1, r2, r4)     // Catch:{ Exception -> 0x0603 }
            int r1 = r0.getAlignment()     // Catch:{ Exception -> 0x0603 }
            r12.alignment = r1     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r2 = r1.listIndentLeft     // Catch:{ Exception -> 0x0603 }
            float r4 = r0.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r2 = r2 + r4
            r1.listIndentLeft = r2     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r2 = r1.indentRight     // Catch:{ Exception -> 0x0603 }
            float r4 = r0.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r2 = r2 + r4
            r1.indentRight = r2     // Catch:{ Exception -> 0x0603 }
            float r1 = r0.getTotalLeading()     // Catch:{ Exception -> 0x0603 }
            r12.leading = r1     // Catch:{ Exception -> 0x0603 }
            r12.pushLeading()     // Catch:{ Exception -> 0x0603 }
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfLine r1 = r12.line     // Catch:{ Exception -> 0x0603 }
            r1.setListItem(r0)     // Catch:{ Exception -> 0x0603 }
            r13.process(r12)     // Catch:{ Exception -> 0x0603 }
            float r1 = r0.getSpacingAfter()     // Catch:{ Exception -> 0x0603 }
            float r2 = r0.getTotalLeading()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.Font r4 = r0.getFont()     // Catch:{ Exception -> 0x0603 }
            r12.addSpacing(r1, r2, r4, r3)     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfLine r1 = r12.line     // Catch:{ Exception -> 0x0603 }
            boolean r1 = r1.hasToBeJustified()     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x0193
            com.itextpdf.text.pdf.PdfLine r1 = r12.line     // Catch:{ Exception -> 0x0603 }
            r1.resetAlignment()     // Catch:{ Exception -> 0x0603 }
        L_0x0193:
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r2 = r1.listIndentLeft     // Catch:{ Exception -> 0x0603 }
            float r4 = r0.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r2 = r2 - r4
            r1.listIndentLeft = r2     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r2 = r1.indentRight     // Catch:{ Exception -> 0x0603 }
            float r4 = r0.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r2 = r2 - r4
            r1.indentRight = r2     // Catch:{ Exception -> 0x0603 }
            r12.popLeading()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r1 = r12.writer     // Catch:{ Exception -> 0x0603 }
            boolean r1 = isTagged(r1)     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x05fc
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r1 = r12.text     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.ListBody r2 = r0.getListBody()     // Catch:{ Exception -> 0x0603 }
            r1.closeMCBlock(r2)     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r1 = r12.text     // Catch:{ Exception -> 0x0603 }
            r1.closeMCBlock(r0)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x01ca:
            r0 = r13
            com.itextpdf.text.List r0 = (com.itextpdf.text.List) r0     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r1 = r12.writer     // Catch:{ Exception -> 0x0603 }
            boolean r1 = isTagged(r1)     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x01dd
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r1 = r12.text     // Catch:{ Exception -> 0x0603 }
            r1.openMCBlock(r0)     // Catch:{ Exception -> 0x0603 }
        L_0x01dd:
            boolean r1 = r0.isAlignindent()     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x01e6
            r0.normalizeIndentation()     // Catch:{ Exception -> 0x0603 }
        L_0x01e6:
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r2 = r1.listIndentLeft     // Catch:{ Exception -> 0x0603 }
            float r4 = r0.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r2 = r2 + r4
            r1.listIndentLeft = r2     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r2 = r1.indentRight     // Catch:{ Exception -> 0x0603 }
            float r4 = r0.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r2 = r2 + r4
            r1.indentRight = r2     // Catch:{ Exception -> 0x0603 }
            r13.process(r12)     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r2 = r1.listIndentLeft     // Catch:{ Exception -> 0x0603 }
            float r4 = r0.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r2 = r2 - r4
            r1.listIndentLeft = r2     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r2 = r1.indentRight     // Catch:{ Exception -> 0x0603 }
            float r4 = r0.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r2 = r2 - r4
            r1.indentRight = r2     // Catch:{ Exception -> 0x0603 }
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r1 = r12.writer     // Catch:{ Exception -> 0x0603 }
            boolean r1 = isTagged(r1)     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x05fc
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r1 = r12.text     // Catch:{ Exception -> 0x0603 }
            r1.closeMCBlock(r0)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x022a:
            r0 = r13
            com.itextpdf.text.Section r0 = (com.itextpdf.text.Section) r0     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r2 = r12.writer     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfPageEvent r2 = r2.getPageEvent()     // Catch:{ Exception -> 0x0603 }
            boolean r4 = r0.isNotAddedYet()     // Catch:{ Exception -> 0x0603 }
            if (r4 == 0) goto L_0x0241
            com.itextpdf.text.Paragraph r4 = r0.getTitle()     // Catch:{ Exception -> 0x0603 }
            if (r4 == 0) goto L_0x0241
            r10 = r3
            goto L_0x0242
        L_0x0241:
            r10 = r1
        L_0x0242:
            boolean r4 = r0.isTriggerNewPage()     // Catch:{ Exception -> 0x0603 }
            if (r4 == 0) goto L_0x024b
            r12.newPage()     // Catch:{ Exception -> 0x0603 }
        L_0x024b:
            if (r10 == 0) goto L_0x0296
            float r4 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r5 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r4 = r4 - r5
            com.itextpdf.text.Rectangle r5 = r12.pageSize     // Catch:{ Exception -> 0x0603 }
            int r5 = r5.getRotation()     // Catch:{ Exception -> 0x0603 }
            r6 = 90
            if (r5 == r6) goto L_0x0262
            r6 = 180(0xb4, float:2.52E-43)
            if (r5 != r6) goto L_0x026a
        L_0x0262:
            com.itextpdf.text.Rectangle r5 = r12.pageSize     // Catch:{ Exception -> 0x0603 }
            float r5 = r5.getHeight()     // Catch:{ Exception -> 0x0603 }
            float r4 = r5 - r4
        L_0x026a:
            com.itextpdf.text.pdf.PdfDestination r5 = new com.itextpdf.text.pdf.PdfDestination     // Catch:{ Exception -> 0x0603 }
            r6 = 2
            r5.<init>(r6, r4)     // Catch:{ Exception -> 0x0603 }
        L_0x0270:
            com.itextpdf.text.pdf.PdfOutline r4 = r12.currentOutline     // Catch:{ Exception -> 0x0603 }
            int r4 = r4.level()     // Catch:{ Exception -> 0x0603 }
            int r6 = r0.getDepth()     // Catch:{ Exception -> 0x0603 }
            if (r4 < r6) goto L_0x0285
            com.itextpdf.text.pdf.PdfOutline r4 = r12.currentOutline     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfOutline r4 = r4.parent()     // Catch:{ Exception -> 0x0603 }
            r12.currentOutline = r4     // Catch:{ Exception -> 0x0603 }
            goto L_0x0270
        L_0x0285:
            com.itextpdf.text.pdf.PdfOutline r4 = new com.itextpdf.text.pdf.PdfOutline     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfOutline r6 = r12.currentOutline     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.Paragraph r7 = r0.getBookmarkTitle()     // Catch:{ Exception -> 0x0603 }
            boolean r8 = r0.isBookmarkOpen()     // Catch:{ Exception -> 0x0603 }
            r4.<init>((com.itextpdf.text.pdf.PdfOutline) r6, (com.itextpdf.text.pdf.PdfDestination) r5, (com.itextpdf.text.Paragraph) r7, (boolean) r8)     // Catch:{ Exception -> 0x0603 }
            r12.currentOutline = r4     // Catch:{ Exception -> 0x0603 }
        L_0x0296:
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r4 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r5 = r4.sectionIndentLeft     // Catch:{ Exception -> 0x0603 }
            float r6 = r0.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r5 = r5 + r6
            r4.sectionIndentLeft = r5     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r4 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r5 = r4.sectionIndentRight     // Catch:{ Exception -> 0x0603 }
            float r6 = r0.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r5 = r5 + r6
            r4.sectionIndentRight = r5     // Catch:{ Exception -> 0x0603 }
            boolean r4 = r0.isNotAddedYet()     // Catch:{ Exception -> 0x0603 }
            r11 = 16
            if (r4 == 0) goto L_0x02e7
            if (r2 == 0) goto L_0x02e7
            int r4 = r13.type()     // Catch:{ Exception -> 0x0603 }
            if (r4 != r11) goto L_0x02d0
            com.itextpdf.text.pdf.PdfWriter r4 = r12.writer     // Catch:{ Exception -> 0x0603 }
            float r5 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r6 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r5 = r5 - r6
            com.itextpdf.text.Paragraph r6 = r0.getTitle()     // Catch:{ Exception -> 0x0603 }
            r2.onChapter(r4, r12, r5, r6)     // Catch:{ Exception -> 0x0603 }
            goto L_0x02e7
        L_0x02d0:
            com.itextpdf.text.pdf.PdfWriter r5 = r12.writer     // Catch:{ Exception -> 0x0603 }
            float r4 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r6 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r7 = r4 - r6
            int r8 = r0.getDepth()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.Paragraph r9 = r0.getTitle()     // Catch:{ Exception -> 0x0603 }
            r4 = r2
            r6 = r12
            r4.onSection(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0603 }
        L_0x02e7:
            if (r10 == 0) goto L_0x02f4
            r12.isSectionTitle = r3     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.Paragraph r4 = r0.getTitle()     // Catch:{ Exception -> 0x0603 }
            r12.add((com.itextpdf.text.Element) r4)     // Catch:{ Exception -> 0x0603 }
            r12.isSectionTitle = r1     // Catch:{ Exception -> 0x0603 }
        L_0x02f4:
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r4 = r1.sectionIndentLeft     // Catch:{ Exception -> 0x0603 }
            float r5 = r0.getIndentation()     // Catch:{ Exception -> 0x0603 }
            float r4 = r4 + r5
            r1.sectionIndentLeft = r4     // Catch:{ Exception -> 0x0603 }
            r13.process(r12)     // Catch:{ Exception -> 0x0603 }
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r4 = r1.sectionIndentLeft     // Catch:{ Exception -> 0x0603 }
            float r5 = r0.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r6 = r0.getIndentation()     // Catch:{ Exception -> 0x0603 }
            float r5 = r5 + r6
            float r4 = r4 - r5
            r1.sectionIndentLeft = r4     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r4 = r1.sectionIndentRight     // Catch:{ Exception -> 0x0603 }
            float r5 = r0.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r4 = r4 - r5
            r1.sectionIndentRight = r4     // Catch:{ Exception -> 0x0603 }
            boolean r0 = r0.isComplete()     // Catch:{ Exception -> 0x0603 }
            if (r0 == 0) goto L_0x05fc
            if (r2 == 0) goto L_0x05fc
            int r0 = r13.type()     // Catch:{ Exception -> 0x0603 }
            if (r0 != r11) goto L_0x033c
            com.itextpdf.text.pdf.PdfWriter r0 = r12.writer     // Catch:{ Exception -> 0x0603 }
            float r1 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r4 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r1 = r1 - r4
            r2.onChapterEnd(r0, r12, r1)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x033c:
            com.itextpdf.text.pdf.PdfWriter r0 = r12.writer     // Catch:{ Exception -> 0x0603 }
            float r1 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r4 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r1 = r1 - r4
            r2.onSectionEnd(r0, r12, r1)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x034a:
            com.itextpdf.text.TabSettings r0 = r12.tabSettings     // Catch:{ Exception -> 0x0603 }
            r2 = r13
            com.itextpdf.text.Phrase r2 = (com.itextpdf.text.Phrase) r2     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.TabSettings r2 = r2.getTabSettings()     // Catch:{ Exception -> 0x0603 }
            if (r2 == 0) goto L_0x035e
            r2 = r13
            com.itextpdf.text.Phrase r2 = (com.itextpdf.text.Phrase) r2     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.TabSettings r2 = r2.getTabSettings()     // Catch:{ Exception -> 0x0603 }
            r12.tabSettings = r2     // Catch:{ Exception -> 0x0603 }
        L_0x035e:
            r2 = r13
            com.itextpdf.text.Paragraph r2 = (com.itextpdf.text.Paragraph) r2     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r5 = r12.writer     // Catch:{ Exception -> 0x0603 }
            boolean r5 = isTagged(r5)     // Catch:{ Exception -> 0x0603 }
            if (r5 == 0) goto L_0x0371
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r5 = r12.text     // Catch:{ Exception -> 0x0603 }
            r5.openMCBlock(r2)     // Catch:{ Exception -> 0x0603 }
        L_0x0371:
            float r5 = r2.getSpacingBefore()     // Catch:{ Exception -> 0x0603 }
            float r6 = r12.leading     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.Font r7 = r2.getFont()     // Catch:{ Exception -> 0x0603 }
            r12.addSpacing(r5, r6, r7)     // Catch:{ Exception -> 0x0603 }
            int r5 = r2.getAlignment()     // Catch:{ Exception -> 0x0603 }
            r12.alignment = r5     // Catch:{ Exception -> 0x0603 }
            float r5 = r2.getTotalLeading()     // Catch:{ Exception -> 0x0603 }
            r12.leading = r5     // Catch:{ Exception -> 0x0603 }
            r12.pushLeading()     // Catch:{ Exception -> 0x0603 }
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            float r5 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r6 = r12.calculateLineHeight()     // Catch:{ Exception -> 0x0603 }
            float r5 = r5 + r6
            float r6 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r7 = r12.indentBottom()     // Catch:{ Exception -> 0x0603 }
            float r6 = r6 - r7
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L_0x03a7
            r12.newPage()     // Catch:{ Exception -> 0x0603 }
        L_0x03a7:
            com.itextpdf.text.pdf.PdfDocument$Indentation r5 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r6 = r5.indentLeft     // Catch:{ Exception -> 0x0603 }
            float r7 = r2.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r6 = r6 + r7
            r5.indentLeft = r6     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r5 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r6 = r5.indentRight     // Catch:{ Exception -> 0x0603 }
            float r7 = r2.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r6 = r6 + r7
            r5.indentRight = r6     // Catch:{ Exception -> 0x0603 }
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r5 = r12.writer     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfPageEvent r5 = r5.getPageEvent()     // Catch:{ Exception -> 0x0603 }
            if (r5 == 0) goto L_0x03d8
            boolean r6 = r12.isSectionTitle     // Catch:{ Exception -> 0x0603 }
            if (r6 != 0) goto L_0x03d8
            com.itextpdf.text.pdf.PdfWriter r6 = r12.writer     // Catch:{ Exception -> 0x0603 }
            float r7 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r8 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r7 = r7 - r8
            r5.onParagraph(r6, r12, r7)     // Catch:{ Exception -> 0x0603 }
        L_0x03d8:
            boolean r6 = r2.getKeepTogether()     // Catch:{ Exception -> 0x0603 }
            if (r6 == 0) goto L_0x0433
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfPTable r6 = new com.itextpdf.text.pdf.PdfPTable     // Catch:{ Exception -> 0x0603 }
            r6.<init>((int) r3)     // Catch:{ Exception -> 0x0603 }
            boolean r7 = r2.getKeepTogether()     // Catch:{ Exception -> 0x0603 }
            r6.setKeepTogether(r7)     // Catch:{ Exception -> 0x0603 }
            r7 = 1120403456(0x42c80000, float:100.0)
            r6.setWidthPercentage(r7)     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfPCell r7 = new com.itextpdf.text.pdf.PdfPCell     // Catch:{ Exception -> 0x0603 }
            r7.<init>()     // Catch:{ Exception -> 0x0603 }
            r7.addElement(r2)     // Catch:{ Exception -> 0x0603 }
            r7.setBorder(r1)     // Catch:{ Exception -> 0x0603 }
            r7.setPadding(r4)     // Catch:{ Exception -> 0x0603 }
            r6.addCell((com.itextpdf.text.pdf.PdfPCell) r7)     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r4 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r7 = r4.indentLeft     // Catch:{ Exception -> 0x0603 }
            float r8 = r2.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r7 = r7 - r8
            r4.indentLeft = r7     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r4 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r7 = r4.indentRight     // Catch:{ Exception -> 0x0603 }
            float r8 = r2.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r7 = r7 - r8
            r4.indentRight = r7     // Catch:{ Exception -> 0x0603 }
            r12.add((com.itextpdf.text.Element) r6)     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r4 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r6 = r4.indentLeft     // Catch:{ Exception -> 0x0603 }
            float r7 = r2.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r6 = r6 + r7
            r4.indentLeft = r6     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r4 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r6 = r4.indentRight     // Catch:{ Exception -> 0x0603 }
            float r7 = r2.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r6 = r6 + r7
            r4.indentRight = r6     // Catch:{ Exception -> 0x0603 }
            goto L_0x0461
        L_0x0433:
            com.itextpdf.text.pdf.PdfLine r4 = r12.line     // Catch:{ Exception -> 0x0603 }
            float r6 = r2.getFirstLineIndent()     // Catch:{ Exception -> 0x0603 }
            r4.setExtraIndent(r6)     // Catch:{ Exception -> 0x0603 }
            float r4 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            r13.process(r12)     // Catch:{ Exception -> 0x0603 }
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            float r6 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0452
            java.util.ArrayList<com.itextpdf.text.pdf.PdfLine> r4 = r12.lines     // Catch:{ Exception -> 0x0603 }
            int r4 = r4.size()     // Catch:{ Exception -> 0x0603 }
            if (r4 <= 0) goto L_0x0461
        L_0x0452:
            float r4 = r2.getSpacingAfter()     // Catch:{ Exception -> 0x0603 }
            float r6 = r2.getTotalLeading()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.Font r7 = r2.getFont()     // Catch:{ Exception -> 0x0603 }
            r12.addSpacing(r4, r6, r7, r3)     // Catch:{ Exception -> 0x0603 }
        L_0x0461:
            if (r5 == 0) goto L_0x0473
            boolean r4 = r12.isSectionTitle     // Catch:{ Exception -> 0x0603 }
            if (r4 != 0) goto L_0x0473
            com.itextpdf.text.pdf.PdfWriter r4 = r12.writer     // Catch:{ Exception -> 0x0603 }
            float r6 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r7 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r6 = r6 - r7
            r5.onParagraphEnd(r4, r12, r6)     // Catch:{ Exception -> 0x0603 }
        L_0x0473:
            r12.alignment = r1     // Catch:{ Exception -> 0x0603 }
            java.util.ArrayList<com.itextpdf.text.Element> r1 = r12.floatingElements     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x0484
            java.util.ArrayList<com.itextpdf.text.Element> r1 = r12.floatingElements     // Catch:{ Exception -> 0x0603 }
            int r1 = r1.size()     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x0484
            r12.flushFloatingElements()     // Catch:{ Exception -> 0x0603 }
        L_0x0484:
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r4 = r1.indentLeft     // Catch:{ Exception -> 0x0603 }
            float r5 = r2.getIndentationLeft()     // Catch:{ Exception -> 0x0603 }
            float r4 = r4 - r5
            r1.indentLeft = r4     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfDocument$Indentation r1 = r12.indentation     // Catch:{ Exception -> 0x0603 }
            float r4 = r1.indentRight     // Catch:{ Exception -> 0x0603 }
            float r5 = r2.getIndentationRight()     // Catch:{ Exception -> 0x0603 }
            float r4 = r4 - r5
            r1.indentRight = r4     // Catch:{ Exception -> 0x0603 }
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            r12.tabSettings = r0     // Catch:{ Exception -> 0x0603 }
            r12.popLeading()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r0 = r12.writer     // Catch:{ Exception -> 0x0603 }
            boolean r0 = isTagged(r0)     // Catch:{ Exception -> 0x0603 }
            if (r0 == 0) goto L_0x05fc
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r0 = r12.text     // Catch:{ Exception -> 0x0603 }
            r0.closeMCBlock(r2)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x04b4:
            com.itextpdf.text.TabSettings r0 = r12.tabSettings     // Catch:{ Exception -> 0x0603 }
            r1 = r13
            com.itextpdf.text.Phrase r1 = (com.itextpdf.text.Phrase) r1     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.TabSettings r1 = r1.getTabSettings()     // Catch:{ Exception -> 0x0603 }
            if (r1 == 0) goto L_0x04c8
            r1 = r13
            com.itextpdf.text.Phrase r1 = (com.itextpdf.text.Phrase) r1     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.TabSettings r1 = r1.getTabSettings()     // Catch:{ Exception -> 0x0603 }
            r12.tabSettings = r1     // Catch:{ Exception -> 0x0603 }
        L_0x04c8:
            r1 = r13
            com.itextpdf.text.Phrase r1 = (com.itextpdf.text.Phrase) r1     // Catch:{ Exception -> 0x0603 }
            float r1 = r1.getTotalLeading()     // Catch:{ Exception -> 0x0603 }
            r12.leading = r1     // Catch:{ Exception -> 0x0603 }
            r12.pushLeading()     // Catch:{ Exception -> 0x0603 }
            r13.process(r12)     // Catch:{ Exception -> 0x0603 }
            r12.tabSettings = r0     // Catch:{ Exception -> 0x0603 }
            r12.popLeading()     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x04de:
            com.itextpdf.text.pdf.PdfLine r0 = r12.line     // Catch:{ Exception -> 0x0603 }
            if (r0 != 0) goto L_0x04e5
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
        L_0x04e5:
            com.itextpdf.text.pdf.PdfChunk r0 = new com.itextpdf.text.pdf.PdfChunk     // Catch:{ Exception -> 0x0603 }
            r2 = r13
            com.itextpdf.text.Chunk r2 = (com.itextpdf.text.Chunk) r2     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfAction r4 = r12.anchorAction     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.TabSettings r5 = r12.tabSettings     // Catch:{ Exception -> 0x0603 }
            r0.<init>(r2, r4, r5)     // Catch:{ Exception -> 0x0603 }
        L_0x04f1:
            com.itextpdf.text.pdf.PdfLine r2 = r12.line     // Catch:{ Exception -> 0x0603 }
            float r4 = r12.leading     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfChunk r2 = r2.add(r0, r4)     // Catch:{ Exception -> 0x0603 }
            if (r2 == 0) goto L_0x0509
            r12.carriageReturn()     // Catch:{ Exception -> 0x0603 }
            boolean r0 = r0.isNewlineSplit()     // Catch:{ Exception -> 0x0603 }
            if (r0 != 0) goto L_0x0507
            r2.trimFirstSpace()     // Catch:{ Exception -> 0x0603 }
        L_0x0507:
            r0 = r2
            goto L_0x04f1
        L_0x0509:
            r12.pageEmpty = r1     // Catch:{ Exception -> 0x0603 }
            java.lang.String r1 = "NEWPAGE"
            boolean r0 = r0.isAttribute(r1)     // Catch:{ Exception -> 0x0603 }
            if (r0 == 0) goto L_0x05fc
            r12.newPage()     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0518:
            r0 = r13
            com.itextpdf.text.Meta r0 = (com.itextpdf.text.Meta) r0     // Catch:{ Exception -> 0x0603 }
            java.lang.String r0 = r0.getContent()     // Catch:{ Exception -> 0x0603 }
            r12.setLanguage(r0)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0524:
            com.itextpdf.text.pdf.PdfDocument$PdfInfo r0 = r12.info     // Catch:{ Exception -> 0x0603 }
            r1 = r13
            com.itextpdf.text.Meta r1 = (com.itextpdf.text.Meta) r1     // Catch:{ Exception -> 0x0603 }
            java.lang.String r1 = r1.getContent()     // Catch:{ Exception -> 0x0603 }
            r0.addCreator(r1)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0532:
            com.itextpdf.text.pdf.PdfDocument$PdfInfo r0 = r12.info     // Catch:{ Exception -> 0x0603 }
            r0.addCreationDate()     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0539:
            com.itextpdf.text.pdf.PdfDocument$PdfInfo r0 = r12.info     // Catch:{ Exception -> 0x0603 }
            r0.addProducer()     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0540:
            com.itextpdf.text.pdf.PdfDocument$PdfInfo r0 = r12.info     // Catch:{ Exception -> 0x0603 }
            r1 = r13
            com.itextpdf.text.Meta r1 = (com.itextpdf.text.Meta) r1     // Catch:{ Exception -> 0x0603 }
            java.lang.String r1 = r1.getContent()     // Catch:{ Exception -> 0x0603 }
            r0.addAuthor(r1)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x054e:
            com.itextpdf.text.pdf.PdfDocument$PdfInfo r0 = r12.info     // Catch:{ Exception -> 0x0603 }
            r1 = r13
            com.itextpdf.text.Meta r1 = (com.itextpdf.text.Meta) r1     // Catch:{ Exception -> 0x0603 }
            java.lang.String r1 = r1.getContent()     // Catch:{ Exception -> 0x0603 }
            r0.addKeywords(r1)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x055c:
            com.itextpdf.text.pdf.PdfDocument$PdfInfo r0 = r12.info     // Catch:{ Exception -> 0x0603 }
            r1 = r13
            com.itextpdf.text.Meta r1 = (com.itextpdf.text.Meta) r1     // Catch:{ Exception -> 0x0603 }
            java.lang.String r1 = r1.getContent()     // Catch:{ Exception -> 0x0603 }
            r0.addSubject(r1)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x056a:
            com.itextpdf.text.pdf.PdfDocument$PdfInfo r0 = r12.info     // Catch:{ Exception -> 0x0603 }
            r1 = r13
            com.itextpdf.text.Meta r1 = (com.itextpdf.text.Meta) r1     // Catch:{ Exception -> 0x0603 }
            java.lang.String r1 = r1.getContent()     // Catch:{ Exception -> 0x0603 }
            r0.addTitle(r1)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x0578:
            com.itextpdf.text.pdf.PdfDocument$PdfInfo r0 = r12.info     // Catch:{ Exception -> 0x0603 }
            r1 = r13
            com.itextpdf.text.Meta r1 = (com.itextpdf.text.Meta) r1     // Catch:{ Exception -> 0x0603 }
            java.lang.String r1 = r1.getName()     // Catch:{ Exception -> 0x0603 }
            r2 = r13
            com.itextpdf.text.Meta r2 = (com.itextpdf.text.Meta) r2     // Catch:{ Exception -> 0x0603 }
            java.lang.String r2 = r2.getContent()     // Catch:{ Exception -> 0x0603 }
            r0.addkey(r1, r2)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x058d:
            com.itextpdf.text.pdf.PdfWriter r0 = r12.writer     // Catch:{ Exception -> 0x0603 }
            if (r0 == 0) goto L_0x05fc
            r0 = r13
            com.itextpdf.text.api.WriterOperation r0 = (com.itextpdf.text.api.WriterOperation) r0     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfWriter r1 = r12.writer     // Catch:{ Exception -> 0x0603 }
            r0.write(r1, r12)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x059a:
            r0 = r13
            com.itextpdf.text.pdf.draw.DrawInterface r0 = (com.itextpdf.text.pdf.draw.DrawInterface) r0     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.pdf.PdfContentByte r5 = r12.graphics     // Catch:{ Exception -> 0x0603 }
            float r6 = r12.indentLeft()     // Catch:{ Exception -> 0x0603 }
            float r7 = r12.indentBottom()     // Catch:{ Exception -> 0x0603 }
            float r8 = r12.indentRight()     // Catch:{ Exception -> 0x0603 }
            float r9 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r2 = r12.indentTop()     // Catch:{ Exception -> 0x0603 }
            float r10 = r12.currentHeight     // Catch:{ Exception -> 0x0603 }
            float r2 = r2 - r10
            java.util.Stack<java.lang.Float> r10 = r12.leadingStack     // Catch:{ Exception -> 0x0603 }
            int r10 = r10.size()     // Catch:{ Exception -> 0x0603 }
            if (r10 <= 0) goto L_0x05c0
            float r4 = r12.leading     // Catch:{ Exception -> 0x0603 }
        L_0x05c0:
            float r10 = r2 - r4
            r4 = r0
            r4.draw(r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0603 }
            r12.pageEmpty = r1     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x05c9:
            boolean r0 = r13 instanceof com.itextpdf.text.MarkedSection     // Catch:{ Exception -> 0x0603 }
            if (r0 == 0) goto L_0x05d9
            r0 = r13
            com.itextpdf.text.MarkedSection r0 = (com.itextpdf.text.MarkedSection) r0     // Catch:{ Exception -> 0x0603 }
            com.itextpdf.text.MarkedObject r0 = r0.getTitle()     // Catch:{ Exception -> 0x0603 }
            if (r0 == 0) goto L_0x05d9
            r0.process(r12)     // Catch:{ Exception -> 0x0603 }
        L_0x05d9:
            r0 = r13
            com.itextpdf.text.MarkedObject r0 = (com.itextpdf.text.MarkedObject) r0     // Catch:{ Exception -> 0x0603 }
            r0.process(r12)     // Catch:{ Exception -> 0x0603 }
            goto L_0x05fc
        L_0x05e0:
            r0 = r13
            com.itextpdf.text.pdf.PdfPTable r0 = (com.itextpdf.text.pdf.PdfPTable) r0     // Catch:{ Exception -> 0x0603 }
            int r2 = r0.size()     // Catch:{ Exception -> 0x0603 }
            int r4 = r0.getHeaderRows()     // Catch:{ Exception -> 0x0603 }
            if (r2 > r4) goto L_0x05ee
            goto L_0x05fc
        L_0x05ee:
            r12.ensureNewLine()     // Catch:{ Exception -> 0x0603 }
            r12.flushLines()     // Catch:{ Exception -> 0x0603 }
            r12.addPTable(r0)     // Catch:{ Exception -> 0x0603 }
            r12.pageEmpty = r1     // Catch:{ Exception -> 0x0603 }
            r12.newLine()     // Catch:{ Exception -> 0x0603 }
        L_0x05fc:
            int r13 = r13.type()     // Catch:{ Exception -> 0x0603 }
            r12.lastElementType = r13     // Catch:{ Exception -> 0x0603 }
            return r3
        L_0x0603:
            r13 = move-exception
            com.itextpdf.text.DocumentException r0 = new com.itextpdf.text.DocumentException
            r0.<init>((java.lang.Exception) r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfDocument.add(com.itextpdf.text.Element):boolean");
    }

    public void open() {
        if (!this.open) {
            super.open();
            this.writer.open();
            this.rootOutline = new PdfOutline(this.writer);
            this.currentOutline = this.rootOutline;
        }
        try {
            if (isTagged(this.writer)) {
                this.openMCDocument = true;
            }
            initPage();
        } catch (DocumentException e) {
            throw new ExceptionConverter(e);
        }
    }

    public void close() {
        int size;
        if (!this.close) {
            try {
                if (isTagged(this.writer)) {
                    flushFloatingElements();
                    flushLines();
                    this.writer.flushAcroFields();
                    this.writer.flushTaggedObjects();
                    if (isPageEmpty() && (size = this.writer.pageReferences.size()) > 0 && this.writer.currentPageNumber == size) {
                        this.writer.pageReferences.remove(size - 1);
                    }
                } else {
                    this.writer.flushAcroFields();
                }
                if (this.imageWait != null) {
                    newPage();
                }
                endPage();
                if (isTagged(this.writer)) {
                    this.writer.getDirectContent().closeMCBlock(this);
                }
                if (this.annotationsImp.hasUnusedAnnotations()) {
                    throw new RuntimeException(MessageLocalization.getComposedMessage("not.all.annotations.could.be.added.to.the.document.the.document.doesn.t.have.enough.pages", new Object[0]));
                }
                PdfPageEvent pageEvent = this.writer.getPageEvent();
                if (pageEvent != null) {
                    pageEvent.onCloseDocument(this.writer, this);
                }
                super.close();
                this.writer.addLocalDestinations(this.localDestinations);
                calculateOutlineCount();
                writeOutlines();
                this.writer.close();
            } catch (Exception e) {
                throw ExceptionConverter.convertException(e);
            }
        }
    }

    public void setXmpMetadata(byte[] bArr) throws IOException {
        PdfStream pdfStream = new PdfStream(bArr);
        pdfStream.put(PdfName.TYPE, PdfName.METADATA);
        pdfStream.put(PdfName.SUBTYPE, PdfName.XML);
        PdfEncryption encryption = this.writer.getEncryption();
        if (encryption != null && !encryption.isMetadataEncrypted()) {
            PdfArray pdfArray = new PdfArray();
            pdfArray.add((PdfObject) PdfName.CRYPT);
            pdfStream.put(PdfName.FILTER, pdfArray);
        }
        this.writer.addPageDictEntry(PdfName.METADATA, this.writer.addToBody(pdfStream).getIndirectReference());
    }

    public boolean newPage() {
        if (isPageEmpty()) {
            setNewPageSizeAndMargins();
            return false;
        } else if (!this.open || this.close) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("the.document.is.not.open", new Object[0]));
        } else {
            ArrayList<IAccessibleElement> endPage = endPage();
            super.newPage();
            this.indentation.imageIndentLeft = 0.0f;
            this.indentation.imageIndentRight = 0.0f;
            try {
                if (isTagged(this.writer)) {
                    flushStructureElementsOnNewPage();
                    this.writer.getDirectContentUnder().restoreMCBlocks(endPage);
                }
                initPage();
                if (this.body == null || this.body.getBackgroundColor() == null) {
                    return true;
                }
                this.graphics.rectangle(this.body);
                return true;
            } catch (DocumentException e) {
                throw new ExceptionConverter(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0165 A[Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0170 A[Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0185 A[Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.itextpdf.text.pdf.interfaces.IAccessibleElement> endPage() {
        /*
            r11 = this;
            boolean r0 = r11.isPageEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            r11.flushFloatingElements()     // Catch:{ DocumentException -> 0x01b0 }
            r0 = -1
            r11.lastElementType = r0
            com.itextpdf.text.pdf.PdfWriter r0 = r11.writer
            com.itextpdf.text.pdf.PdfPageEvent r0 = r0.getPageEvent()
            if (r0 == 0) goto L_0x001b
            com.itextpdf.text.pdf.PdfWriter r2 = r11.writer
            r0.onEndPage(r2, r11)
        L_0x001b:
            r11.flushLines()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.Rectangle r0 = r11.pageSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            int r0 = r0.getRotation()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r2 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            boolean r2 = r2.isPdfIso()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r2 == 0) goto L_0x0091
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfRectangle> r2 = r11.thisBoxSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r3 = "art"
            boolean r2 = r2.containsKey(r3)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r2 == 0) goto L_0x004f
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfRectangle> r2 = r11.thisBoxSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r3 = "trim"
            boolean r2 = r2.containsKey(r3)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r2 == 0) goto L_0x004f
            com.itextpdf.text.pdf.PdfXConformanceException r0 = new com.itextpdf.text.pdf.PdfXConformanceException     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r1 = "only.one.of.artbox.or.trimbox.can.exist.in.the.page"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r1 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r0.<init>(r1)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            throw r0     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
        L_0x004f:
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfRectangle> r2 = r11.thisBoxSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r3 = "art"
            boolean r2 = r2.containsKey(r3)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r2 != 0) goto L_0x0091
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfRectangle> r2 = r11.thisBoxSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r3 = "trim"
            boolean r2 = r2.containsKey(r3)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r2 != 0) goto L_0x0091
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfRectangle> r2 = r11.thisBoxSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r3 = "crop"
            boolean r2 = r2.containsKey(r3)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r2 == 0) goto L_0x007d
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfRectangle> r2 = r11.thisBoxSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r3 = "trim"
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfRectangle> r4 = r11.thisBoxSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r5 = "crop"
            java.lang.Object r4 = r4.get(r5)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r2.put(r3, r4)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            goto L_0x0091
        L_0x007d:
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfRectangle> r2 = r11.thisBoxSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.lang.String r3 = "trim"
            com.itextpdf.text.pdf.PdfRectangle r4 = new com.itextpdf.text.pdf.PdfRectangle     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.Rectangle r5 = r11.pageSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.Rectangle r6 = r11.pageSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            int r6 = r6.getRotation()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r4.<init>((com.itextpdf.text.Rectangle) r5, (int) r6)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r2.put(r3, r4)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
        L_0x0091:
            com.itextpdf.text.pdf.PageResources r2 = r11.pageResources     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r3 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfDictionary r3 = r3.getDefaultColorspace()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r2.addDefaultColorDiff(r3)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r2 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            boolean r2 = r2.isRgbTransparencyBlending()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r2 == 0) goto L_0x00b5
            com.itextpdf.text.pdf.PdfDictionary r2 = new com.itextpdf.text.pdf.PdfDictionary     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r2.<init>()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.CS     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfName r4 = com.itextpdf.text.pdf.PdfName.DEVICERGB     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r2.put(r3, r4)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PageResources r3 = r11.pageResources     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r3.addDefaultColorDiff(r2)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
        L_0x00b5:
            com.itextpdf.text.pdf.PageResources r2 = r11.pageResources     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfDictionary r2 = r2.getResources()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfPage r3 = new com.itextpdf.text.pdf.PdfPage     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfRectangle r4 = new com.itextpdf.text.pdf.PdfRectangle     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.Rectangle r5 = r11.pageSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r4.<init>((com.itextpdf.text.Rectangle) r5, (int) r0)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.util.HashMap<java.lang.String, com.itextpdf.text.pdf.PdfRectangle> r5 = r11.thisBoxSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r3.<init>(r4, r5, r2, r0)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r0 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            boolean r0 = isTagged(r0)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r0 == 0) goto L_0x00d9
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.TABS     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.S     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r3.put(r0, r2)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            goto L_0x00e4
        L_0x00d9:
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.TABS     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r2 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfName r2 = r2.getTabs()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r3.put(r0, r2)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
        L_0x00e4:
            com.itextpdf.text.pdf.PdfWriter r0 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfDictionary r0 = r0.getPageDictEntries()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r3.putAll(r0)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r0 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r0.resetPageDictEntries()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfDictionary r0 = r11.pageAA     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r0 == 0) goto L_0x0109
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.AA     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r2 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfDictionary r4 = r11.pageAA     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfIndirectObject r2 = r2.addToBody(r4)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfIndirectReference r2 = r2.getIndirectReference()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r3.put(r0, r2)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r11.pageAA = r1     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
        L_0x0109:
            com.itextpdf.text.pdf.internal.PdfAnnotationsImp r0 = r11.annotationsImp     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            boolean r0 = r0.hasUnusedAnnotations()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r0 == 0) goto L_0x0126
            com.itextpdf.text.pdf.internal.PdfAnnotationsImp r0 = r11.annotationsImp     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r2 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.Rectangle r4 = r11.pageSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfArray r0 = r0.rotateAnnotations(r2, r4)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            int r2 = r0.size()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r2 == 0) goto L_0x0126
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.ANNOTS     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r3.put(r2, r0)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
        L_0x0126:
            com.itextpdf.text.pdf.PdfWriter r0 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            boolean r0 = isTagged(r0)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r0 == 0) goto L_0x0142
            com.itextpdf.text.pdf.PdfName r0 = com.itextpdf.text.pdf.PdfName.STRUCTPARENTS     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfNumber r2 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r4 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfIndirectReference r4 = r4.getCurrentPage()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            int r4 = r11.getStructParentIndex(r4)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r2.<init>((int) r4)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r3.put(r0, r2)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
        L_0x0142:
            com.itextpdf.text.pdf.PdfContentByte r0 = r11.text     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            int r0 = r0.size()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            int r2 = r11.textEmptySize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r0 > r2) goto L_0x0158
            com.itextpdf.text.pdf.PdfWriter r0 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            boolean r0 = isTagged(r0)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r0 == 0) goto L_0x0155
            goto L_0x0158
        L_0x0155:
            r11.text = r1     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            goto L_0x015d
        L_0x0158:
            com.itextpdf.text.pdf.PdfContentByte r0 = r11.text     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r0.endText()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
        L_0x015d:
            com.itextpdf.text.pdf.PdfWriter r0 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            boolean r0 = isTagged(r0)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r0 == 0) goto L_0x0170
            com.itextpdf.text.pdf.PdfWriter r0 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfContentByte r0 = r0.getDirectContent()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            java.util.ArrayList r0 = r0.saveMCBlocks()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            goto L_0x0171
        L_0x0170:
            r0 = r1
        L_0x0171:
            com.itextpdf.text.pdf.PdfWriter r2 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfContents r10 = new com.itextpdf.text.pdf.PdfContents     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r4 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfContentByte r5 = r4.getDirectContentUnder()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfContentByte r6 = r11.graphics     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r4 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            boolean r4 = isTagged(r4)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            if (r4 != 0) goto L_0x0187
            com.itextpdf.text.pdf.PdfContentByte r1 = r11.text     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
        L_0x0187:
            r7 = r1
            com.itextpdf.text.pdf.PdfWriter r1 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfContentByte r8 = r1.getDirectContent()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.Rectangle r9 = r11.pageSize     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r4 = r10
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r2.add((com.itextpdf.text.pdf.PdfPage) r3, (com.itextpdf.text.pdf.PdfContents) r10)     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.internal.PdfAnnotationsImp r1 = r11.annotationsImp     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r1.resetAnnotations()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            com.itextpdf.text.pdf.PdfWriter r1 = r11.writer     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            r1.resetContent()     // Catch:{ DocumentException -> 0x01a9, IOException -> 0x01a2 }
            return r0
        L_0x01a2:
            r0 = move-exception
            com.itextpdf.text.ExceptionConverter r1 = new com.itextpdf.text.ExceptionConverter
            r1.<init>(r0)
            throw r1
        L_0x01a9:
            r0 = move-exception
            com.itextpdf.text.ExceptionConverter r1 = new com.itextpdf.text.ExceptionConverter
            r1.<init>(r0)
            throw r1
        L_0x01b0:
            r0 = move-exception
            com.itextpdf.text.ExceptionConverter r1 = new com.itextpdf.text.ExceptionConverter
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfDocument.endPage():java.util.ArrayList");
    }

    public boolean setPageSize(Rectangle rectangle) {
        if (this.writer != null && this.writer.isPaused()) {
            return false;
        }
        this.nextPageSize = new Rectangle(rectangle);
        return true;
    }

    public boolean setMargins(float f, float f2, float f3, float f4) {
        if (this.writer != null && this.writer.isPaused()) {
            return false;
        }
        this.nextMarginLeft = f;
        this.nextMarginRight = f2;
        this.nextMarginTop = f3;
        this.nextMarginBottom = f4;
        return true;
    }

    public boolean setMarginMirroring(boolean z) {
        if (this.writer == null || !this.writer.isPaused()) {
            return super.setMarginMirroring(z);
        }
        return false;
    }

    public boolean setMarginMirroringTopBottom(boolean z) {
        if (this.writer == null || !this.writer.isPaused()) {
            return super.setMarginMirroringTopBottom(z);
        }
        return false;
    }

    public void setPageCount(int i) {
        if (this.writer == null || !this.writer.isPaused()) {
            super.setPageCount(i);
        }
    }

    public void resetPageCount() {
        if (this.writer == null || !this.writer.isPaused()) {
            super.resetPageCount();
        }
    }

    /* access modifiers changed from: protected */
    public void initPage() throws DocumentException {
        this.pageN++;
        this.pageResources = new PageResources();
        if (isTagged(this.writer)) {
            this.graphics = this.writer.getDirectContentUnder().getDuplicate();
            this.writer.getDirectContent().duplicatedFrom = this.graphics;
        } else {
            this.graphics = new PdfContentByte(this.writer);
        }
        setNewPageSizeAndMargins();
        this.imageEnd = -1.0f;
        this.indentation.imageIndentRight = 0.0f;
        this.indentation.imageIndentLeft = 0.0f;
        this.indentation.indentBottom = 0.0f;
        this.indentation.indentTop = 0.0f;
        this.currentHeight = 0.0f;
        this.thisBoxSize = new HashMap<>(this.boxSize);
        if (!(this.pageSize.getBackgroundColor() == null && !this.pageSize.hasBorders() && this.pageSize.getBorderColor() == null)) {
            add((Element) this.pageSize);
        }
        float f = this.leading;
        int i = this.alignment;
        this.pageEmpty = true;
        try {
            if (this.imageWait != null) {
                add(this.imageWait);
                this.imageWait = null;
            }
            this.leading = f;
            this.alignment = i;
            carriageReturn();
            PdfPageEvent pageEvent = this.writer.getPageEvent();
            if (pageEvent != null) {
                if (this.firstPageEvent) {
                    pageEvent.onOpenDocument(this.writer, this);
                }
                pageEvent.onStartPage(this.writer, this);
            }
            this.firstPageEvent = false;
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    /* access modifiers changed from: protected */
    public void newLine() throws DocumentException {
        this.lastElementType = -1;
        carriageReturn();
        if (this.lines != null && !this.lines.isEmpty()) {
            this.lines.add(this.line);
            this.currentHeight += this.line.height();
        }
        this.line = new PdfLine(indentLeft(), indentRight(), this.alignment, this.leading);
    }

    /* access modifiers changed from: protected */
    public float calculateLineHeight() {
        float height = this.line.height();
        return height != this.leading ? height + this.leading : height;
    }

    /* access modifiers changed from: protected */
    public void carriageReturn() {
        if (this.lines == null) {
            this.lines = new ArrayList<>();
        }
        if (this.line != null && this.line.size() > 0) {
            if (this.currentHeight + calculateLineHeight() > indentTop() - indentBottom() && this.currentHeight != 0.0f) {
                PdfLine pdfLine = this.line;
                this.line = null;
                newPage();
                this.line = pdfLine;
                pdfLine.left = indentLeft();
            }
            this.currentHeight += this.line.height();
            this.lines.add(this.line);
            this.pageEmpty = false;
        }
        if (this.imageEnd > -1.0f && this.currentHeight > this.imageEnd) {
            this.imageEnd = -1.0f;
            this.indentation.imageIndentRight = 0.0f;
            this.indentation.imageIndentLeft = 0.0f;
        }
        this.line = new PdfLine(indentLeft(), indentRight(), this.alignment, this.leading);
    }

    public float getVerticalPosition(boolean z) {
        if (z) {
            ensureNewLine();
        }
        return (top() - this.currentHeight) - this.indentation.indentTop;
    }

    /* access modifiers changed from: protected */
    public void ensureNewLine() {
        try {
            if (this.lastElementType == 11 || this.lastElementType == 10) {
                newLine();
                flushLines();
            }
        } catch (DocumentException e) {
            throw new ExceptionConverter(e);
        }
    }

    /* access modifiers changed from: protected */
    public float flushLines() throws DocumentException {
        ListLabel listLabel;
        if (this.lines == null) {
            return 0.0f;
        }
        if (this.line != null && this.line.size() > 0) {
            this.lines.add(this.line);
            this.line = new PdfLine(indentLeft(), indentRight(), this.alignment, this.leading);
        }
        if (this.lines.isEmpty()) {
            return 0.0f;
        }
        Object[] objArr = new Object[2];
        objArr[1] = new Float(0.0f);
        Iterator<PdfLine> it = this.lines.iterator();
        float f = 0.0f;
        PdfFont pdfFont = null;
        while (it.hasNext()) {
            PdfLine next = it.next();
            float indentLeft = (next.indentLeft() - indentLeft()) + this.indentation.indentLeft + this.indentation.listIndentLeft + this.indentation.sectionIndentLeft;
            this.text.moveText(indentLeft, -next.height());
            next.flush();
            if (next.listSymbol() != null) {
                Chunk listSymbol = next.listSymbol();
                if (isTagged(this.writer)) {
                    listLabel = next.listItem().getListLabel();
                    this.graphics.openMCBlock(listLabel);
                    Chunk chunk = new Chunk(listSymbol);
                    chunk.setRole((PdfName) null);
                    listSymbol = chunk;
                } else {
                    listLabel = null;
                }
                ColumnText.showTextAligned(this.graphics, 0, new Phrase(listSymbol), this.text.getXTLM() - next.listIndent(), this.text.getYTLM(), 0.0f);
                if (listLabel != null) {
                    this.graphics.closeMCBlock(listLabel);
                }
            }
            objArr[0] = pdfFont;
            if (isTagged(this.writer) && next.listItem() != null) {
                this.text.openMCBlock(next.listItem().getListBody());
            }
            writeLineToContent(next, this.text, this.graphics, objArr, this.writer.getSpaceCharRatio());
            pdfFont = (PdfFont) objArr[0];
            f += next.height();
            this.text.moveText(-indentLeft, 0.0f);
        }
        this.lines = new ArrayList<>();
        return f;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r58v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r57v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: com.itextpdf.text.BaseColor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r57v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r58v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r58v2, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r57v2, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r58v3, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v52, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r58v4, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r56v0, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v27, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v18, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v64, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v65, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v73, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v74, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v85, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v86, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v93, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v94, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v104, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v105, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v6, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v11, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v14, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v8, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r48v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v82, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v83, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r48v2, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v12, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v28, resolved type: float[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v8, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v3, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v29, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v59, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v60, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v133, resolved type: float[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v61, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v62, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v14, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v63, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v4, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v134, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v65, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v68, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v17, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v18, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v19, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v20, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v21, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v3, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v58, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v4, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v2, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v4, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v77, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v60, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v53, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v18, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v1, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v2, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v2, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v19, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v54, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v26, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v27, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v83, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v20, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v3, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v3, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v55, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v86, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v24, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v4, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v64, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v4, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v65, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v5, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v25, resolved type: float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v5, resolved type: float} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0332  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x03ee  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x03fc  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x04b6  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x04c2  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0533  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x0561  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0576  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x057f  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x05b8  */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x0605  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x060e  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x0612  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x0618  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x0626  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x065a  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x06db  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x076e  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x07a4  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x07aa  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x07b1  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x07b6  */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x07be  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x07c7  */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x07d4  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x08c4  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x08c9  */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x08ce  */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x08d4  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x08dd  */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x08f1  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x08f4  */
    /* JADX WARNING: Removed duplicated region for block: B:327:0x08fd  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0919  */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x0962  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public float writeLineToContent(com.itextpdf.text.pdf.PdfLine r64, com.itextpdf.text.pdf.PdfContentByte r65, com.itextpdf.text.pdf.PdfContentByte r66, java.lang.Object[] r67, float r68) throws com.itextpdf.text.DocumentException {
        /*
            r63 = this;
            r7 = r63
            r8 = r64
            r9 = r65
            r14 = r66
            r15 = 0
            r1 = r67[r15]
            com.itextpdf.text.pdf.PdfFont r1 = (com.itextpdf.text.pdf.PdfFont) r1
            r12 = 1
            r2 = r67[r12]
            java.lang.Float r2 = (java.lang.Float) r2
            float r2 = r2.floatValue()
            float r3 = r65.getXTLM()
            float r4 = r64.getOriginalWidth()
            float r3 = r3 + r4
            int r13 = r64.numberOfSpaces()
            int r4 = r64.getLineLengthUtf32()
            boolean r5 = r64.hasToBeJustified()
            if (r5 == 0) goto L_0x0034
            if (r13 != 0) goto L_0x0031
            if (r4 <= r12) goto L_0x0034
        L_0x0031:
            r26 = r12
            goto L_0x0036
        L_0x0034:
            r26 = r15
        L_0x0036:
            int r5 = r64.getSeparatorCount()
            r11 = 1065353216(0x3f800000, float:1.0)
            r10 = 0
            if (r5 <= 0) goto L_0x004f
            float r0 = r64.widthLeft()
            float r4 = (float) r5
            float r0 = r0 / r4
            r6 = r0
            r29 = r2
            r31 = r3
            r15 = r10
            r30 = r15
            goto L_0x00ed
        L_0x004f:
            if (r26 == 0) goto L_0x00d3
            if (r5 != 0) goto L_0x00d3
            boolean r5 = r64.isNewlineSplit()
            if (r5 == 0) goto L_0x0081
            float r5 = r64.widthLeft()
            float r6 = (float) r13
            float r6 = r6 * r68
            float r15 = (float) r4
            float r6 = r6 + r15
            float r6 = r6 - r11
            float r6 = r6 * r2
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x0081
            boolean r4 = r64.isRTL()
            if (r4 == 0) goto L_0x0076
            float r4 = r64.widthLeft()
            float r4 = r4 - r6
            r9.moveText(r4, r10)
        L_0x0076:
            float r0 = r68 * r2
            r15 = r0
            r29 = r2
            r31 = r3
            r6 = r10
            r30 = r6
            goto L_0x00d0
        L_0x0081:
            float r2 = r64.widthLeft()
            int r5 = r64.size()
            int r5 = r5 - r12
            com.itextpdf.text.pdf.PdfChunk r5 = r8.getChunk(r5)
            if (r5 == 0) goto L_0x00be
            java.lang.String r6 = r5.toString()
            int r15 = r6.length()
            if (r15 <= 0) goto L_0x00be
            java.lang.String r15 = ".,;:'"
            int r16 = r6.length()
            int r10 = r16 + -1
            char r6 = r6.charAt(r10)
            int r10 = r15.indexOf(r6)
            if (r10 < 0) goto L_0x00be
            com.itextpdf.text.pdf.PdfFont r5 = r5.font()
            float r5 = r5.width((int) r6)
            r6 = 1053609165(0x3ecccccd, float:0.4)
            float r5 = r5 * r6
            float r5 = r5 + r2
            float r2 = r5 - r2
            r10 = r2
            r2 = r5
            goto L_0x00bf
        L_0x00be:
            r10 = 0
        L_0x00bf:
            float r5 = (float) r13
            float r5 = r5 * r68
            float r4 = (float) r4
            float r5 = r5 + r4
            float r5 = r5 - r11
            float r2 = r2 / r5
            float r0 = r68 * r2
            r15 = r0
            r29 = r2
            r31 = r3
            r30 = r10
            r6 = 0
        L_0x00d0:
            r10 = r29
            goto L_0x00ed
        L_0x00d3:
            int r0 = r8.alignment
            if (r0 == 0) goto L_0x00e7
            int r0 = r8.alignment
            r4 = -1
            if (r0 != r4) goto L_0x00dd
            goto L_0x00e7
        L_0x00dd:
            r29 = r2
            r31 = r3
            r6 = 0
            r10 = 0
            r15 = 0
            r30 = 0
            goto L_0x00ed
        L_0x00e7:
            float r0 = r64.widthLeft()
            float r3 = r3 - r0
            goto L_0x00dd
        L_0x00ed:
            int r5 = r64.getLastStrokeChunk()
            float r32 = r65.getXTLM()
            float r4 = r65.getYTLM()
            java.util.Iterator r3 = r64.iterator()
            r0 = 2143289344(0x7fc00000, float:NaN)
            r34 = r0
            r2 = r1
            r16 = r32
            r0 = 0
            r1 = 0
            r17 = 0
            r33 = 0
        L_0x010a:
            boolean r18 = r3.hasNext()
            if (r18 == 0) goto L_0x0948
            java.lang.Object r18 = r3.next()
            r11 = r18
            com.itextpdf.text.pdf.PdfChunk r11 = (com.itextpdf.text.pdf.PdfChunk) r11
            com.itextpdf.text.pdf.PdfWriter r12 = r7.writer
            boolean r12 = isTagged(r12)
            if (r12 == 0) goto L_0x012c
            com.itextpdf.text.pdf.interfaces.IAccessibleElement r12 = r11.accessibleElement
            if (r12 == 0) goto L_0x012c
            com.itextpdf.text.pdf.interfaces.IAccessibleElement r0 = r11.accessibleElement
            r9.openMCBlock(r0)
            r35 = 1
            goto L_0x012e
        L_0x012c:
            r35 = r0
        L_0x012e:
            com.itextpdf.text.BaseColor r12 = r11.color()
            com.itextpdf.text.pdf.PdfFont r0 = r11.font()
            float r0 = r0.size()
            boolean r18 = r11.isImage()
            r36 = r12
            if (r18 == 0) goto L_0x0154
            float r0 = r11.height()
            float r18 = r11.height()
            r37 = r2
            r38 = r3
            r19 = r18
            r12 = 0
            r18 = r0
            goto L_0x0177
        L_0x0154:
            com.itextpdf.text.pdf.PdfFont r12 = r11.font()
            com.itextpdf.text.pdf.BaseFont r12 = r12.getFont()
            r37 = r2
            r2 = 1
            float r12 = r12.getFontDescriptor(r2, r0)
            com.itextpdf.text.pdf.PdfFont r2 = r11.font()
            com.itextpdf.text.pdf.BaseFont r2 = r2.getFont()
            r38 = r3
            r3 = 3
            float r2 = r2.getFontDescriptor(r3, r0)
            r19 = r0
            r18 = r12
            r12 = r2
        L_0x0177:
            r3 = 2
            if (r1 > r5) goto L_0x071b
            if (r26 == 0) goto L_0x0181
            float r0 = r11.getWidthCorrected(r10, r15)
            goto L_0x0185
        L_0x0181:
            float r0 = r11.width()
        L_0x0185:
            boolean r2 = r11.isStroked()
            if (r2 == 0) goto L_0x06f2
            int r2 = r1 + 1
            com.itextpdf.text.pdf.PdfChunk r2 = r8.getChunk(r2)
            boolean r20 = r11.isSeparator()
            if (r20 == 0) goto L_0x0201
            java.lang.String r0 = "SEPARATOR"
            java.lang.Object r0 = r11.getAttribute(r0)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r20 = 0
            r21 = r0[r20]
            r20 = r21
            com.itextpdf.text.pdf.draw.DrawInterface r20 = (com.itextpdf.text.pdf.draw.DrawInterface) r20
            r21 = 1
            r0 = r0[r21]
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x01e1
            float r21 = r4 + r12
            float r0 = r64.getOriginalWidth()
            float r22 = r32 + r0
            float r23 = r18 - r12
            r0 = r20
            r39 = r1
            r1 = r14
            r40 = r10
            r8 = r37
            r10 = r2
            r2 = r32
            r41 = r8
            r37 = r38
            r8 = r3
            r3 = r21
            r42 = r4
            r4 = r22
            r38 = r5
            r5 = r23
            r8 = r6
            r6 = r42
            r0.draw(r1, r2, r3, r4, r5, r6)
            r43 = r42
            goto L_0x01fe
        L_0x01e1:
            r39 = r1
            r8 = r6
            r40 = r10
            r41 = r37
            r37 = r38
            r10 = r2
            r6 = r4
            r38 = r5
            float r3 = r6 + r12
            float r4 = r16 + r8
            float r5 = r18 - r12
            r0 = r20
            r1 = r14
            r2 = r16
            r43 = r6
            r0.draw(r1, r2, r3, r4, r5, r6)
        L_0x01fe:
            r20 = r8
            goto L_0x0211
        L_0x0201:
            r39 = r1
            r43 = r4
            r8 = r6
            r40 = r10
            r41 = r37
            r37 = r38
            r10 = r2
            r38 = r5
            r20 = r0
        L_0x0211:
            boolean r0 = r11.isTab()
            if (r0 == 0) goto L_0x028e
            java.lang.String r0 = "TABSETTINGS"
            boolean r0 = r11.isAttribute(r0)
            if (r0 == 0) goto L_0x0250
            com.itextpdf.text.TabStop r0 = r11.getTabStop()
            if (r0 == 0) goto L_0x0249
            float r1 = r0.getPosition()
            float r17 = r1 + r32
            com.itextpdf.text.pdf.draw.DrawInterface r1 = r0.getLeader()
            if (r1 == 0) goto L_0x0246
            com.itextpdf.text.pdf.draw.DrawInterface r0 = r0.getLeader()
            r6 = r43
            float r3 = r6 + r12
            float r5 = r18 - r12
            r1 = r14
            r2 = r16
            r4 = r17
            r44 = r6
            r0.draw(r1, r2, r3, r4, r5, r6)
            goto L_0x024d
        L_0x0246:
            r44 = r43
            goto L_0x024d
        L_0x0249:
            r44 = r43
            r17 = r16
        L_0x024d:
            r45 = r44
            goto L_0x0289
        L_0x0250:
            r44 = r43
            java.lang.String r0 = "TAB"
            java.lang.Object r0 = r11.getAttribute(r0)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r1 = 0
            r2 = r0[r1]
            r1 = r2
            com.itextpdf.text.pdf.draw.DrawInterface r1 = (com.itextpdf.text.pdf.draw.DrawInterface) r1
            r2 = 1
            r3 = r0[r2]
            java.lang.Float r3 = (java.lang.Float) r3
            float r2 = r3.floatValue()
            r3 = 3
            r0 = r0[r3]
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            float r17 = r2 + r0
            int r0 = (r17 > r16 ? 1 : (r17 == r16 ? 0 : -1))
            if (r0 <= 0) goto L_0x024d
            r6 = r44
            float r3 = r6 + r12
            float r5 = r18 - r12
            r0 = r1
            r1 = r14
            r2 = r16
            r4 = r17
            r45 = r6
            r0.draw(r1, r2, r3, r4, r5, r6)
        L_0x0289:
            r42 = r16
            r6 = r17
            goto L_0x0294
        L_0x028e:
            r45 = r43
            r6 = r16
            r42 = r17
        L_0x0294:
            java.lang.String r0 = "BACKGROUND"
            boolean r0 = r11.isAttribute(r0)
            if (r0 == 0) goto L_0x0326
            java.lang.String r0 = "BACKGROUND"
            java.lang.Object r0 = r11.getAttribute(r0)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r1 = 0
            r2 = r0[r1]
            if (r2 == 0) goto L_0x0326
            boolean r1 = r66.getInText()
            if (r1 == 0) goto L_0x02ba
            com.itextpdf.text.pdf.PdfWriter r2 = r7.writer
            boolean r2 = isTagged(r2)
            if (r2 == 0) goto L_0x02ba
            r66.endText()
        L_0x02ba:
            r66.saveState()
            if (r10 == 0) goto L_0x02c9
            java.lang.String r2 = "BACKGROUND"
            boolean r2 = r10.isAttribute(r2)
            if (r2 == 0) goto L_0x02c9
            r2 = 0
            goto L_0x02cb
        L_0x02c9:
            r2 = r29
        L_0x02cb:
            if (r10 != 0) goto L_0x02cf
            float r2 = r2 + r30
        L_0x02cf:
            r3 = 0
            r4 = r0[r3]
            com.itextpdf.text.BaseColor r4 = (com.itextpdf.text.BaseColor) r4
            r14.setColorFill(r4)
            r4 = 1
            r0 = r0[r4]
            float[] r0 = (float[]) r0
            r5 = r0[r3]
            float r5 = r6 - r5
            r3 = r45
            float r16 = r3 + r12
            r17 = r0[r4]
            float r16 = r16 - r17
            float r17 = r11.getTextRise()
            float r4 = r16 + r17
            float r2 = r20 - r2
            r16 = 0
            r17 = r0[r16]
            float r2 = r2 + r17
            r16 = 2
            r17 = r0[r16]
            float r2 = r2 + r17
            float r16 = r18 - r12
            r17 = 1
            r21 = r0[r17]
            float r16 = r16 + r21
            r17 = 3
            r0 = r0[r17]
            float r0 = r16 + r0
            r14.rectangle((float) r5, (float) r4, (float) r2, (float) r0)
            r66.fill()
            r0 = 0
            r14.setGrayFill(r0)
            r66.restoreState()
            if (r1 == 0) goto L_0x0328
            com.itextpdf.text.pdf.PdfWriter r0 = r7.writer
            boolean r0 = isTagged(r0)
            if (r0 == 0) goto L_0x0328
            r0 = 1
            r14.beginText(r0)
            goto L_0x0328
        L_0x0326:
            r3 = r45
        L_0x0328:
            java.lang.String r0 = "UNDERLINE"
            boolean r0 = r11.isAttribute(r0)
            r16 = 4
            if (r0 == 0) goto L_0x03ee
            boolean r0 = r66.getInText()
            if (r0 == 0) goto L_0x0343
            com.itextpdf.text.pdf.PdfWriter r1 = r7.writer
            boolean r1 = isTagged(r1)
            if (r1 == 0) goto L_0x0343
            r66.endText()
        L_0x0343:
            if (r10 == 0) goto L_0x034f
            java.lang.String r1 = "UNDERLINE"
            boolean r1 = r10.isAttribute(r1)
            if (r1 == 0) goto L_0x034f
            r1 = 0
            goto L_0x0351
        L_0x034f:
            r1 = r29
        L_0x0351:
            if (r10 != 0) goto L_0x0355
            float r1 = r1 + r30
        L_0x0355:
            java.lang.String r2 = "UNDERLINE"
            java.lang.Object r2 = r11.getAttribute(r2)
            java.lang.Object[][] r2 = (java.lang.Object[][]) r2
            r4 = 0
        L_0x035e:
            int r5 = r2.length
            if (r4 >= r5) goto L_0x03d6
            r5 = r2[r4]
            r17 = 0
            r21 = r5[r17]
            com.itextpdf.text.BaseColor r21 = (com.itextpdf.text.BaseColor) r21
            r22 = 1
            r5 = r5[r22]
            float[] r5 = (float[]) r5
            if (r21 != 0) goto L_0x0376
            r46 = r2
            r2 = r36
            goto L_0x037a
        L_0x0376:
            r46 = r2
            r2 = r21
        L_0x037a:
            if (r2 == 0) goto L_0x037f
            r14.setColorStroke(r2)
        L_0x037f:
            r21 = r5[r17]
            r47 = r13
            com.itextpdf.text.pdf.PdfFont r13 = r11.font()
            float r13 = r13.size()
            r17 = 1
            r22 = r5[r17]
            float r13 = r13 * r22
            float r13 = r21 + r13
            r14.setLineWidth((float) r13)
            r13 = 2
            r17 = r5[r13]
            com.itextpdf.text.pdf.PdfFont r13 = r11.font()
            float r13 = r13.size()
            r21 = 3
            r22 = r5[r21]
            float r13 = r13 * r22
            float r17 = r17 + r13
            r5 = r5[r16]
            int r5 = (int) r5
            if (r5 == 0) goto L_0x03b1
            r14.setLineCap(r5)
        L_0x03b1:
            float r13 = r3 + r17
            r14.moveTo((float) r6, (float) r13)
            float r17 = r6 + r20
            r48 = r15
            float r15 = r17 - r1
            r14.lineTo((float) r15, (float) r13)
            r66.stroke()
            if (r2 == 0) goto L_0x03c7
            r66.resetGrayStroke()
        L_0x03c7:
            if (r5 == 0) goto L_0x03cd
            r2 = 0
            r14.setLineCap(r2)
        L_0x03cd:
            int r4 = r4 + 1
            r2 = r46
            r13 = r47
            r15 = r48
            goto L_0x035e
        L_0x03d6:
            r47 = r13
            r48 = r15
            r13 = 1065353216(0x3f800000, float:1.0)
            r14.setLineWidth((float) r13)
            if (r0 == 0) goto L_0x03f4
            com.itextpdf.text.pdf.PdfWriter r0 = r7.writer
            boolean r0 = isTagged(r0)
            if (r0 == 0) goto L_0x03f4
            r0 = 1
            r14.beginText(r0)
            goto L_0x03f4
        L_0x03ee:
            r47 = r13
            r48 = r15
            r13 = 1065353216(0x3f800000, float:1.0)
        L_0x03f4:
            java.lang.String r0 = "ACTION"
            boolean r0 = r11.isAttribute(r0)
            if (r0 == 0) goto L_0x04b6
            if (r10 == 0) goto L_0x0408
            java.lang.String r0 = "ACTION"
            boolean r0 = r10.isAttribute(r0)
            if (r0 == 0) goto L_0x0408
            r0 = 0
            goto L_0x040a
        L_0x0408:
            r0 = r29
        L_0x040a:
            if (r10 != 0) goto L_0x040e
            float r0 = r0 + r30
        L_0x040e:
            boolean r1 = r11.isImage()
            if (r1 == 0) goto L_0x0447
            com.itextpdf.text.pdf.PdfWriter r1 = r7.writer
            float r2 = r11.getImageOffsetY()
            float r2 = r2 + r3
            float r4 = r6 + r20
            float r4 = r4 - r0
            float r0 = r11.getImageHeight()
            float r0 = r0 + r3
            float r5 = r11.getImageOffsetY()
            float r5 = r5 + r0
            java.lang.String r0 = "ACTION"
            java.lang.Object r0 = r11.getAttribute(r0)
            r15 = r0
            com.itextpdf.text.pdf.PdfAction r15 = (com.itextpdf.text.pdf.PdfAction) r15
            r17 = 0
            r0 = r1
            r1 = r6
            r49 = r3
            r3 = r4
            r4 = r5
            r5 = r15
            r15 = r6
            r6 = r17
            com.itextpdf.text.pdf.PdfAnnotation r0 = r0.createAnnotation(r1, r2, r3, r4, r5, r6)
            r50 = r8
            r8 = r49
        L_0x0445:
            r1 = 1
            goto L_0x0476
        L_0x0447:
            r49 = r3
            r15 = r6
            com.itextpdf.text.pdf.PdfWriter r1 = r7.writer
            r6 = r49
            float r4 = r6 + r12
            float r2 = r11.getTextRise()
            float r2 = r2 + r4
            float r3 = r15 + r20
            float r3 = r3 - r0
            float r4 = r6 + r18
            float r0 = r11.getTextRise()
            float r4 = r4 + r0
            java.lang.String r0 = "ACTION"
            java.lang.Object r0 = r11.getAttribute(r0)
            r5 = r0
            com.itextpdf.text.pdf.PdfAction r5 = (com.itextpdf.text.pdf.PdfAction) r5
            r17 = 0
            r0 = r1
            r1 = r15
            r50 = r8
            r8 = r6
            r6 = r17
            com.itextpdf.text.pdf.PdfAnnotation r0 = r0.createAnnotation(r1, r2, r3, r4, r5, r6)
            goto L_0x0445
        L_0x0476:
            r9.addAnnotation(r0, r1)
            com.itextpdf.text.pdf.PdfWriter r1 = r7.writer
            boolean r1 = isTagged(r1)
            if (r1 == 0) goto L_0x04ba
            com.itextpdf.text.pdf.interfaces.IAccessibleElement r1 = r11.accessibleElement
            if (r1 == 0) goto L_0x04ba
            com.itextpdf.text.pdf.interfaces.IAccessibleElement r1 = r11.accessibleElement
            com.itextpdf.text.AccessibleElementId r1 = r1.getId()
            com.itextpdf.text.pdf.PdfStructureElement r1 = r7.getStructElement(r1)
            if (r1 == 0) goto L_0x04ba
            int r2 = r7.getStructParentIndex(r0)
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.STRUCTPARENT
            com.itextpdf.text.pdf.PdfNumber r4 = new com.itextpdf.text.pdf.PdfNumber
            r4.<init>((int) r2)
            r0.put(r3, r4)
            com.itextpdf.text.pdf.PdfWriter r3 = r7.writer
            com.itextpdf.text.pdf.PdfIndirectReference r3 = r3.getCurrentPage()
            r1.setAnnotation(r0, r3)
            com.itextpdf.text.pdf.PdfWriter r0 = r7.writer
            com.itextpdf.text.pdf.PdfStructureTreeRoot r0 = r0.getStructureTreeRoot()
            com.itextpdf.text.pdf.PdfIndirectReference r1 = r1.getReference()
            r0.setAnnotationMark(r2, r1)
            goto L_0x04ba
        L_0x04b6:
            r15 = r6
            r50 = r8
            r8 = r3
        L_0x04ba:
            java.lang.String r0 = "REMOTEGOTO"
            boolean r0 = r11.isAttribute(r0)
            if (r0 == 0) goto L_0x052b
            if (r10 == 0) goto L_0x04ce
            java.lang.String r0 = "REMOTEGOTO"
            boolean r0 = r10.isAttribute(r0)
            if (r0 == 0) goto L_0x04ce
            r0 = 0
            goto L_0x04d0
        L_0x04ce:
            r0 = r29
        L_0x04d0:
            if (r10 != 0) goto L_0x04d4
            float r0 = r0 + r30
        L_0x04d4:
            java.lang.String r1 = "REMOTEGOTO"
            java.lang.Object r1 = r11.getAttribute(r1)
            java.lang.Object[] r1 = (java.lang.Object[]) r1
            r2 = 0
            r3 = r1[r2]
            r2 = r3
            java.lang.String r2 = (java.lang.String) r2
            r3 = 1
            r4 = r1[r3]
            boolean r4 = r4 instanceof java.lang.String
            if (r4 == 0) goto L_0x0509
            r1 = r1[r3]
            r3 = r1
            java.lang.String r3 = (java.lang.String) r3
            float r4 = r8 + r12
            float r1 = r11.getTextRise()
            float r4 = r4 + r1
            float r6 = r15 + r20
            float r5 = r6 - r0
            float r0 = r8 + r18
            float r1 = r11.getTextRise()
            float r6 = r0 + r1
            r0 = r7
            r1 = r2
            r2 = r3
            r3 = r15
            r0.remoteGoto((java.lang.String) r1, (java.lang.String) r2, (float) r3, (float) r4, (float) r5, (float) r6)
            goto L_0x052b
        L_0x0509:
            r1 = r1[r3]
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r3 = r1.intValue()
            float r4 = r8 + r12
            float r1 = r11.getTextRise()
            float r4 = r4 + r1
            float r6 = r15 + r20
            float r5 = r6 - r0
            float r0 = r8 + r18
            float r1 = r11.getTextRise()
            float r6 = r0 + r1
            r0 = r7
            r1 = r2
            r2 = r3
            r3 = r15
            r0.remoteGoto((java.lang.String) r1, (int) r2, (float) r3, (float) r4, (float) r5, (float) r6)
        L_0x052b:
            java.lang.String r0 = "LOCALGOTO"
            boolean r0 = r11.isAttribute(r0)
            if (r0 == 0) goto L_0x0559
            if (r10 == 0) goto L_0x053f
            java.lang.String r0 = "LOCALGOTO"
            boolean r0 = r10.isAttribute(r0)
            if (r0 == 0) goto L_0x053f
            r0 = 0
            goto L_0x0541
        L_0x053f:
            r0 = r29
        L_0x0541:
            if (r10 != 0) goto L_0x0545
            float r0 = r0 + r30
        L_0x0545:
            java.lang.String r1 = "LOCALGOTO"
            java.lang.Object r1 = r11.getAttribute(r1)
            java.lang.String r1 = (java.lang.String) r1
            float r6 = r15 + r20
            float r4 = r6 - r0
            float r5 = r8 + r19
            r0 = r7
            r2 = r15
            r3 = r8
            r0.localGoto(r1, r2, r3, r4, r5)
        L_0x0559:
            java.lang.String r0 = "LOCALDESTINATION"
            boolean r0 = r11.isAttribute(r0)
            if (r0 == 0) goto L_0x0576
            java.lang.String r0 = "LOCALDESTINATION"
            java.lang.Object r0 = r11.getAttribute(r0)
            java.lang.String r0 = (java.lang.String) r0
            com.itextpdf.text.pdf.PdfDestination r1 = new com.itextpdf.text.pdf.PdfDestination
            float r4 = r8 + r19
            r2 = 0
            r6 = 0
            r1.<init>(r2, r15, r4, r6)
            r7.localDestination(r0, r1)
            goto L_0x0577
        L_0x0576:
            r6 = 0
        L_0x0577:
            java.lang.String r0 = "GENERICTAG"
            boolean r0 = r11.isAttribute(r0)
            if (r0 == 0) goto L_0x05b0
            if (r10 == 0) goto L_0x058b
            java.lang.String r0 = "GENERICTAG"
            boolean r0 = r10.isAttribute(r0)
            if (r0 == 0) goto L_0x058b
            r0 = r6
            goto L_0x058d
        L_0x058b:
            r0 = r29
        L_0x058d:
            if (r10 != 0) goto L_0x0591
            float r0 = r0 + r30
        L_0x0591:
            com.itextpdf.text.Rectangle r1 = new com.itextpdf.text.Rectangle
            float r2 = r15 + r20
            float r2 = r2 - r0
            float r4 = r8 + r19
            r1.<init>(r15, r8, r2, r4)
            com.itextpdf.text.pdf.PdfWriter r0 = r7.writer
            com.itextpdf.text.pdf.PdfPageEvent r0 = r0.getPageEvent()
            if (r0 == 0) goto L_0x05b0
            com.itextpdf.text.pdf.PdfWriter r2 = r7.writer
            java.lang.String r3 = "GENERICTAG"
            java.lang.Object r3 = r11.getAttribute(r3)
            java.lang.String r3 = (java.lang.String) r3
            r0.onGenericTag(r2, r7, r1, r3)
        L_0x05b0:
            java.lang.String r0 = "PDFANNOTATION"
            boolean r0 = r11.isAttribute(r0)
            if (r0 == 0) goto L_0x05eb
            if (r10 == 0) goto L_0x05c4
            java.lang.String r0 = "PDFANNOTATION"
            boolean r0 = r10.isAttribute(r0)
            if (r0 == 0) goto L_0x05c4
            r0 = r6
            goto L_0x05c6
        L_0x05c4:
            r0 = r29
        L_0x05c6:
            if (r10 != 0) goto L_0x05ca
            float r0 = r0 + r30
        L_0x05ca:
            java.lang.String r1 = "PDFANNOTATION"
            java.lang.Object r1 = r11.getAttribute(r1)
            com.itextpdf.text.pdf.PdfAnnotation r1 = (com.itextpdf.text.pdf.PdfAnnotation) r1
            com.itextpdf.text.pdf.PdfAnnotation r1 = com.itextpdf.text.pdf.PdfFormField.shallowDuplicate(r1)
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.RECT
            com.itextpdf.text.pdf.PdfRectangle r3 = new com.itextpdf.text.pdf.PdfRectangle
            float r4 = r8 + r12
            float r5 = r15 + r20
            float r5 = r5 - r0
            float r0 = r8 + r18
            r3.<init>(r15, r4, r5, r0)
            r1.put(r2, r3)
            r0 = 1
            r9.addAnnotation(r1, r0)
        L_0x05eb:
            java.lang.String r0 = "SKEW"
            java.lang.Object r0 = r11.getAttribute(r0)
            float[] r0 = (float[]) r0
            java.lang.String r1 = "HSCALE"
            java.lang.Object r1 = r11.getAttribute(r1)
            java.lang.Float r1 = (java.lang.Float) r1
            if (r0 != 0) goto L_0x0603
            if (r1 == 0) goto L_0x0600
            goto L_0x0603
        L_0x0600:
            r12 = r6
            r0 = r13
            goto L_0x0624
        L_0x0603:
            if (r0 == 0) goto L_0x060e
            r2 = 0
            r3 = r0[r2]
            r2 = 1
            r0 = r0[r2]
            r2 = r3
            r3 = r0
            goto L_0x0610
        L_0x060e:
            r2 = r6
            r3 = r2
        L_0x0610:
            if (r1 == 0) goto L_0x0618
            float r0 = r1.floatValue()
            r10 = r0
            goto L_0x0619
        L_0x0618:
            r10 = r13
        L_0x0619:
            r4 = 1065353216(0x3f800000, float:1.0)
            r0 = r9
            r1 = r10
            r5 = r15
            r12 = r6
            r6 = r8
            r0.setTextMatrix(r1, r2, r3, r4, r5, r6)
            r0 = r10
        L_0x0624:
            if (r26 != 0) goto L_0x0654
            java.lang.String r1 = "WORD_SPACING"
            boolean r1 = r11.isAttribute(r1)
            if (r1 == 0) goto L_0x063d
            java.lang.String r1 = "WORD_SPACING"
            java.lang.Object r1 = r11.getAttribute(r1)
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            r9.setWordSpacing(r1)
        L_0x063d:
            java.lang.String r1 = "CHAR_SPACING"
            boolean r1 = r11.isAttribute(r1)
            if (r1 == 0) goto L_0x0654
            java.lang.String r1 = "CHAR_SPACING"
            java.lang.Object r1 = r11.getAttribute(r1)
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            r9.setCharacterSpacing(r1)
        L_0x0654:
            boolean r1 = r11.isImage()
            if (r1 == 0) goto L_0x06db
            com.itextpdf.text.Image r1 = r11.getImage()
            float r2 = r11.getImageWidth()
            float r3 = r11.getImageScalePercentage()
            float[] r3 = r1.matrix(r3)
            float r4 = r11.getImageOffsetX()
            float r6 = r15 + r4
            r4 = r3[r16]
            float r6 = r6 - r4
            r3[r16] = r6
            float r4 = r11.getImageOffsetY()
            float r4 = r4 + r8
            r5 = 5
            r6 = r3[r5]
            float r4 = r4 - r6
            r3[r5] = r4
            r4 = 0
            r6 = r3[r4]
            double r12 = (double) r6
            r6 = 1
            r10 = r3[r6]
            double r6 = (double) r10
            r10 = 2
            r4 = r3[r10]
            r51 = r6
            double r5 = (double) r4
            r4 = 3
            r7 = r3[r4]
            r53 = r5
            double r4 = (double) r7
            r6 = r3[r16]
            double r6 = (double) r6
            r10 = 5
            r3 = r3[r10]
            r55 = r2
            double r2 = (double) r3
            r24 = 0
            r56 = r0
            r57 = r8
            r0 = r40
            r8 = 0
            r10 = r14
            r58 = r0
            r8 = r11
            r0 = 1065353216(0x3f800000, float:1.0)
            r11 = r1
            r59 = r36
            r1 = r47
            r0 = 1
            r27 = 3
            r28 = r15
            r60 = r48
            r0 = 0
            r14 = r51
            r16 = r53
            r18 = r4
            r20 = r6
            r22 = r2
            r25 = r35
            r10.addImage(r11, r12, r14, r16, r18, r20, r22, r24, r25)
            float r6 = r28 + r29
            float r2 = r8.getImageWidth()
            float r6 = r6 + r2
            float r2 = r65.getXTLM()
            float r6 = r6 - r2
            r2 = 0
            r9.moveText(r6, r2)
            r2 = r55
            goto L_0x06ef
        L_0x06db:
            r56 = r0
            r57 = r8
            r8 = r11
            r28 = r15
            r59 = r36
            r58 = r40
            r1 = r47
            r60 = r48
            r0 = 0
            r27 = 3
            r2 = r20
        L_0x06ef:
            r11 = r56
            goto L_0x0710
        L_0x06f2:
            r2 = r0
            r39 = r1
            r57 = r4
            r50 = r6
            r58 = r10
            r8 = r11
            r1 = r13
            r60 = r15
            r59 = r36
            r41 = r37
            r37 = r38
            r0 = 0
            r27 = 3
            r38 = r5
            r28 = r16
            r42 = r17
            r11 = 1065353216(0x3f800000, float:1.0)
        L_0x0710:
            float r16 = r28 + r2
            int r2 = r39 + 1
            r39 = r2
            r2 = r16
            r17 = r42
            goto L_0x0736
        L_0x071b:
            r39 = r1
            r57 = r4
            r50 = r6
            r58 = r10
            r8 = r11
            r1 = r13
            r60 = r15
            r59 = r36
            r41 = r37
            r37 = r38
            r0 = 0
            r27 = 3
            r38 = r5
            r2 = r16
            r11 = 1065353216(0x3f800000, float:1.0)
        L_0x0736:
            boolean r3 = r8.isImage()
            if (r3 != 0) goto L_0x0758
            com.itextpdf.text.pdf.PdfFont r3 = r8.font()
            r4 = r41
            int r3 = r3.compareTo((com.itextpdf.text.pdf.PdfFont) r4)
            if (r3 == 0) goto L_0x075a
            com.itextpdf.text.pdf.PdfFont r3 = r8.font()
            com.itextpdf.text.pdf.BaseFont r4 = r3.getFont()
            float r5 = r3.size()
            r9.setFontAndSize(r4, r5)
            goto L_0x075b
        L_0x0758:
            r4 = r41
        L_0x075a:
            r3 = r4
        L_0x075b:
            java.lang.String r4 = "TEXTRENDERMODE"
            java.lang.Object r4 = r8.getAttribute(r4)
            java.lang.Object[] r4 = (java.lang.Object[]) r4
            r5 = 0
            java.lang.String r6 = "SUBSUPSCRIPT"
            java.lang.Object r6 = r8.getAttribute(r6)
            java.lang.Float r6 = (java.lang.Float) r6
            if (r4 == 0) goto L_0x07a4
            r7 = r4[r0]
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r15 = r7 & 3
            if (r15 == 0) goto L_0x077d
            r9.setTextRenderingMode(r15)
        L_0x077d:
            r7 = 1
            if (r15 == r7) goto L_0x0783
            r10 = 2
            if (r15 != r10) goto L_0x07a5
        L_0x0783:
            r5 = r4[r7]
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
            r7 = 1065353216(0x3f800000, float:1.0)
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x0794
            r9.setLineWidth((float) r5)
        L_0x0794:
            r7 = 2
            r4 = r4[r7]
            r12 = r4
            com.itextpdf.text.BaseColor r12 = (com.itextpdf.text.BaseColor) r12
            if (r12 != 0) goto L_0x079e
            r12 = r59
        L_0x079e:
            if (r12 == 0) goto L_0x07a8
            r9.setColorStroke(r12)
            goto L_0x07a8
        L_0x07a4:
            r15 = r0
        L_0x07a5:
            r12 = r5
            r5 = 1065353216(0x3f800000, float:1.0)
        L_0x07a8:
            if (r6 == 0) goto L_0x07b1
            float r10 = r6.floatValue()
            r4 = r59
            goto L_0x07b4
        L_0x07b1:
            r4 = r59
            r10 = 0
        L_0x07b4:
            if (r4 == 0) goto L_0x07b9
            r9.setColorFill(r4)
        L_0x07b9:
            r6 = 0
            int r7 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r7 == 0) goto L_0x07c1
            r9.setTextRise((float) r10)
        L_0x07c1:
            boolean r6 = r8.isImage()
            if (r6 == 0) goto L_0x07d4
            r61 = r1
            r62 = r3
            r13 = r50
            r6 = r60
            r0 = 0
            r33 = 1
            goto L_0x08c0
        L_0x07d4:
            boolean r6 = r8.isHorizontalSeparator()
            r7 = 1148846080(0x447a0000, float:1000.0)
            if (r6 == 0) goto L_0x07f4
            com.itextpdf.text.pdf.PdfTextArray r6 = new com.itextpdf.text.pdf.PdfTextArray
            r6.<init>()
            r13 = r50
            float r14 = -r13
            float r14 = r14 * r7
            com.itextpdf.text.pdf.PdfFont r7 = r8.font
            float r7 = r7.size()
            float r14 = r14 / r7
            float r14 = r14 / r11
            r6.add((float) r14)
            r9.showText((com.itextpdf.text.pdf.PdfTextArray) r6)
            goto L_0x0816
        L_0x07f4:
            r13 = r50
            boolean r6 = r8.isTab()
            if (r6 == 0) goto L_0x081f
            int r6 = (r17 > r2 ? 1 : (r17 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x081f
            com.itextpdf.text.pdf.PdfTextArray r6 = new com.itextpdf.text.pdf.PdfTextArray
            r6.<init>()
            float r14 = r17 - r2
            float r14 = r14 * r7
            com.itextpdf.text.pdf.PdfFont r7 = r8.font
            float r7 = r7.size()
            float r14 = r14 / r7
            float r14 = r14 / r11
            r6.add((float) r14)
            r9.showText((com.itextpdf.text.pdf.PdfTextArray) r6)
        L_0x0816:
            r61 = r1
            r62 = r3
            r6 = r60
        L_0x081c:
            r0 = 0
            goto L_0x08c0
        L_0x081f:
            if (r26 == 0) goto L_0x0897
            if (r1 <= 0) goto L_0x0897
            boolean r6 = r8.isSpecialEncoding()
            if (r6 == 0) goto L_0x0897
            int r6 = (r11 > r34 ? 1 : (r11 == r34 ? 0 : -1))
            if (r6 == 0) goto L_0x0842
            r6 = r60
            float r14 = r6 / r11
            r9.setWordSpacing(r14)
            float r14 = r58 / r11
            float r16 = r65.getCharacterSpacing()
            float r14 = r14 + r16
            r9.setCharacterSpacing(r14)
            r34 = r11
            goto L_0x0844
        L_0x0842:
            r6 = r60
        L_0x0844:
            java.lang.String r14 = r8.toString()
            r0 = 32
            int r7 = r14.indexOf(r0)
            if (r7 >= 0) goto L_0x0858
            r9.showText((java.lang.String) r14)
            r61 = r1
            r62 = r3
            goto L_0x081c
        L_0x0858:
            float r0 = -r6
            r16 = 1148846080(0x447a0000, float:1000.0)
            float r0 = r0 * r16
            r61 = r1
            com.itextpdf.text.pdf.PdfFont r1 = r8.font
            float r1 = r1.size()
            float r0 = r0 / r1
            float r0 = r0 / r11
            com.itextpdf.text.pdf.PdfTextArray r1 = new com.itextpdf.text.pdf.PdfTextArray
            r62 = r3
            r11 = 0
            java.lang.String r3 = r14.substring(r11, r7)
            r1.<init>(r3)
        L_0x0873:
            int r3 = r7 + 1
            r11 = 32
            int r3 = r14.indexOf(r11, r3)
            if (r3 < 0) goto L_0x0889
            r1.add((float) r0)
            java.lang.String r7 = r14.substring(r7, r3)
            r1.add((java.lang.String) r7)
            r7 = r3
            goto L_0x0873
        L_0x0889:
            r1.add((float) r0)
            java.lang.String r0 = r14.substring(r7)
            r1.add((java.lang.String) r0)
            r9.showText((com.itextpdf.text.pdf.PdfTextArray) r1)
            goto L_0x081c
        L_0x0897:
            r61 = r1
            r62 = r3
            r6 = r60
            if (r26 == 0) goto L_0x08b3
            int r0 = (r11 > r34 ? 1 : (r11 == r34 ? 0 : -1))
            if (r0 == 0) goto L_0x08b3
            float r0 = r6 / r11
            r9.setWordSpacing(r0)
            float r0 = r58 / r11
            float r1 = r65.getCharacterSpacing()
            float r0 = r0 + r1
            r9.setCharacterSpacing(r0)
            goto L_0x08b5
        L_0x08b3:
            r11 = r34
        L_0x08b5:
            java.lang.String r0 = r8.toString()
            r9.showText((java.lang.String) r0)
            r34 = r11
            goto L_0x081c
        L_0x08c0:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 == 0) goto L_0x08c7
            r9.setTextRise((float) r0)
        L_0x08c7:
            if (r4 == 0) goto L_0x08cc
            r65.resetRGBColorFill()
        L_0x08cc:
            if (r15 == 0) goto L_0x08d2
            r0 = 0
            r9.setTextRenderingMode(r0)
        L_0x08d2:
            if (r12 == 0) goto L_0x08d7
            r65.resetRGBColorStroke()
        L_0x08d7:
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r1 == 0) goto L_0x08e0
            r9.setLineWidth((float) r0)
        L_0x08e0:
            java.lang.String r1 = "SKEW"
            boolean r1 = r8.isAttribute(r1)
            if (r1 != 0) goto L_0x08f4
            java.lang.String r1 = "HSCALE"
            boolean r1 = r8.isAttribute(r1)
            if (r1 == 0) goto L_0x08f1
            goto L_0x08f4
        L_0x08f1:
            r1 = r57
            goto L_0x08fb
        L_0x08f4:
            r1 = r57
            r9.setTextMatrix(r2, r1)
            r33 = 1
        L_0x08fb:
            if (r26 != 0) goto L_0x0919
            java.lang.String r3 = "CHAR_SPACING"
            boolean r3 = r8.isAttribute(r3)
            if (r3 == 0) goto L_0x090b
            r10 = r58
            r9.setCharacterSpacing(r10)
            goto L_0x090d
        L_0x090b:
            r10 = r58
        L_0x090d:
            java.lang.String r3 = "WORD_SPACING"
            boolean r3 = r8.isAttribute(r3)
            if (r3 == 0) goto L_0x091b
            r9.setWordSpacing(r6)
            goto L_0x091b
        L_0x0919:
            r10 = r58
        L_0x091b:
            r3 = r63
            com.itextpdf.text.pdf.PdfWriter r4 = r3.writer
            boolean r4 = isTagged(r4)
            if (r4 == 0) goto L_0x092e
            com.itextpdf.text.pdf.interfaces.IAccessibleElement r4 = r8.accessibleElement
            if (r4 == 0) goto L_0x092e
            com.itextpdf.text.pdf.interfaces.IAccessibleElement r4 = r8.accessibleElement
            r9.closeMCBlock(r4)
        L_0x092e:
            r14 = r66
            r11 = r0
            r4 = r1
            r16 = r2
            r7 = r3
            r15 = r6
            r6 = r13
            r0 = r35
            r3 = r37
            r5 = r38
            r1 = r39
            r13 = r61
            r2 = r62
            r8 = r64
            r12 = 1
            goto L_0x010a
        L_0x0948:
            r4 = r2
            r3 = r7
            if (r26 == 0) goto L_0x095c
            r2 = 0
            r9.setWordSpacing(r2)
            r9.setCharacterSpacing(r2)
            r1 = r4
            boolean r0 = r64.isNewlineSplit()
            if (r0 == 0) goto L_0x095e
            r0 = r2
            goto L_0x0960
        L_0x095c:
            r1 = r4
            r2 = 0
        L_0x095e:
            r0 = r29
        L_0x0960:
            if (r33 == 0) goto L_0x096b
            float r4 = r65.getXTLM()
            float r4 = r32 - r4
            r9.moveText(r4, r2)
        L_0x096b:
            r2 = 0
            r67[r2] = r1
            java.lang.Float r1 = new java.lang.Float
            r1.<init>(r0)
            r0 = 1
            r67[r0] = r1
            return r31
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfDocument.writeLineToContent(com.itextpdf.text.pdf.PdfLine, com.itextpdf.text.pdf.PdfContentByte, com.itextpdf.text.pdf.PdfContentByte, java.lang.Object[], float):float");
    }

    /* access modifiers changed from: protected */
    public float indentLeft() {
        return left(this.indentation.indentLeft + this.indentation.listIndentLeft + this.indentation.imageIndentLeft + this.indentation.sectionIndentLeft);
    }

    /* access modifiers changed from: protected */
    public float indentRight() {
        return right(this.indentation.indentRight + this.indentation.sectionIndentRight + this.indentation.imageIndentRight);
    }

    /* access modifiers changed from: protected */
    public float indentTop() {
        return top(this.indentation.indentTop);
    }

    /* access modifiers changed from: package-private */
    public float indentBottom() {
        return bottom(this.indentation.indentBottom);
    }

    /* access modifiers changed from: protected */
    public void addSpacing(float f, float f2, Font font) {
        addSpacing(f, f2, font, false);
    }

    /* access modifiers changed from: protected */
    public void addSpacing(float f, float f2, Font font, boolean z) {
        float f3;
        Font font2;
        if (f != 0.0f && !this.pageEmpty) {
            if (z) {
                f3 = f;
            } else {
                f3 = calculateLineHeight();
            }
            if (this.currentHeight + f3 > indentTop() - indentBottom()) {
                newPage();
                return;
            }
            this.leading = f;
            carriageReturn();
            if (font.isUnderlined() || font.isStrikethru()) {
                font2 = new Font(font);
                font2.setStyle(font2.getStyle() & -5 & -9);
            } else {
                font2 = font;
            }
            Chunk chunk = new Chunk(" ", font2);
            if (z && this.pageEmpty) {
                chunk = new Chunk("", font2);
            }
            chunk.process(this);
            carriageReturn();
            this.leading = f2;
        }
    }

    /* access modifiers changed from: package-private */
    public PdfInfo getInfo() {
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public PdfCatalog getCatalog(PdfIndirectReference pdfIndirectReference) {
        PdfCatalog pdfCatalog = new PdfCatalog(pdfIndirectReference, this.writer);
        if (this.rootOutline.getKids().size() > 0) {
            pdfCatalog.put(PdfName.PAGEMODE, PdfName.USEOUTLINES);
            pdfCatalog.put(PdfName.OUTLINES, this.rootOutline.indirectReference());
        }
        this.writer.getPdfVersion().addToCatalog(pdfCatalog);
        this.viewerPreferences.addToCatalog(pdfCatalog);
        if (this.pageLabels != null) {
            pdfCatalog.put(PdfName.PAGELABELS, this.pageLabels.getDictionary(this.writer));
        }
        pdfCatalog.addNames(this.localDestinations, getDocumentLevelJS(), this.documentFileAttachment, this.writer);
        if (this.openActionName != null) {
            pdfCatalog.setOpenAction(getLocalGotoAction(this.openActionName));
        } else if (this.openActionAction != null) {
            pdfCatalog.setOpenAction(this.openActionAction);
        }
        if (this.additionalActions != null) {
            pdfCatalog.setAdditionalActions(this.additionalActions);
        }
        if (this.collection != null) {
            pdfCatalog.put(PdfName.COLLECTION, this.collection);
        }
        if (this.annotationsImp.hasValidAcroForm()) {
            try {
                pdfCatalog.put(PdfName.ACROFORM, this.writer.addToBody(this.annotationsImp.getAcroForm()).getIndirectReference());
            } catch (IOException e) {
                throw new ExceptionConverter(e);
            }
        }
        if (this.language != null) {
            pdfCatalog.put(PdfName.LANG, this.language);
        }
        return pdfCatalog;
    }

    /* access modifiers changed from: package-private */
    public void addOutline(PdfOutline pdfOutline, String str) {
        localDestination(str, pdfOutline.getPdfDestination());
    }

    public PdfOutline getRootOutline() {
        return this.rootOutline;
    }

    /* access modifiers changed from: package-private */
    public void calculateOutlineCount() {
        if (this.rootOutline.getKids().size() != 0) {
            traverseOutlineCount(this.rootOutline);
        }
    }

    /* access modifiers changed from: package-private */
    public void traverseOutlineCount(PdfOutline pdfOutline) {
        ArrayList<PdfOutline> kids = pdfOutline.getKids();
        PdfOutline parent = pdfOutline.parent();
        if (!kids.isEmpty()) {
            for (int i = 0; i < kids.size(); i++) {
                traverseOutlineCount(kids.get(i));
            }
            if (parent == null) {
                return;
            }
            if (pdfOutline.isOpen()) {
                parent.setCount(pdfOutline.getCount() + parent.getCount() + 1);
                return;
            }
            parent.setCount(parent.getCount() + 1);
            pdfOutline.setCount(-pdfOutline.getCount());
        } else if (parent != null) {
            parent.setCount(parent.getCount() + 1);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeOutlines() throws IOException {
        if (this.rootOutline.getKids().size() != 0) {
            outlineTree(this.rootOutline);
            this.writer.addToBody((PdfObject) this.rootOutline, this.rootOutline.indirectReference());
        }
    }

    /* access modifiers changed from: package-private */
    public void outlineTree(PdfOutline pdfOutline) throws IOException {
        pdfOutline.setIndirectReference(this.writer.getPdfIndirectReference());
        if (pdfOutline.parent() != null) {
            pdfOutline.put(PdfName.PARENT, pdfOutline.parent().indirectReference());
        }
        ArrayList<PdfOutline> kids = pdfOutline.getKids();
        int size = kids.size();
        for (int i = 0; i < size; i++) {
            outlineTree(kids.get(i));
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                kids.get(i2).put(PdfName.PREV, kids.get(i2 - 1).indirectReference());
            }
            if (i2 < size - 1) {
                kids.get(i2).put(PdfName.NEXT, kids.get(i2 + 1).indirectReference());
            }
        }
        if (size > 0) {
            pdfOutline.put(PdfName.FIRST, kids.get(0).indirectReference());
            pdfOutline.put(PdfName.LAST, kids.get(size - 1).indirectReference());
        }
        for (int i3 = 0; i3 < size; i3++) {
            PdfOutline pdfOutline2 = kids.get(i3);
            this.writer.addToBody((PdfObject) pdfOutline2, pdfOutline2.indirectReference());
        }
    }

    /* access modifiers changed from: package-private */
    public void setViewerPreferences(int i) {
        this.viewerPreferences.setViewerPreferences(i);
    }

    /* access modifiers changed from: package-private */
    public void addViewerPreference(PdfName pdfName, PdfObject pdfObject) {
        this.viewerPreferences.addViewerPreference(pdfName, pdfObject);
    }

    /* access modifiers changed from: package-private */
    public void setPageLabels(PdfPageLabels pdfPageLabels) {
        this.pageLabels = pdfPageLabels;
    }

    public PdfPageLabels getPageLabels() {
        return this.pageLabels;
    }

    /* access modifiers changed from: package-private */
    public void localGoto(String str, float f, float f2, float f3, float f4) {
        this.annotationsImp.addPlainAnnotation(this.writer.createAnnotation(f, f2, f3, f4, getLocalGotoAction(str), (PdfName) null));
    }

    /* access modifiers changed from: package-private */
    public void remoteGoto(String str, String str2, float f, float f2, float f3, float f4) {
        this.annotationsImp.addPlainAnnotation(this.writer.createAnnotation(f, f2, f3, f4, new PdfAction(str, str2), (PdfName) null));
    }

    /* access modifiers changed from: package-private */
    public void remoteGoto(String str, int i, float f, float f2, float f3, float f4) {
        addAnnotation(this.writer.createAnnotation(f, f2, f3, f4, new PdfAction(str, i), (PdfName) null));
    }

    /* access modifiers changed from: package-private */
    public void setAction(PdfAction pdfAction, float f, float f2, float f3, float f4) {
        addAnnotation(this.writer.createAnnotation(f, f2, f3, f4, pdfAction, (PdfName) null));
    }

    /* access modifiers changed from: package-private */
    public PdfAction getLocalGotoAction(String str) {
        Destination destination = this.localDestinations.get(str);
        if (destination == null) {
            destination = new Destination();
        }
        if (destination.action != null) {
            return destination.action;
        }
        if (destination.reference == null) {
            destination.reference = this.writer.getPdfIndirectReference();
        }
        PdfAction pdfAction = new PdfAction(destination.reference);
        destination.action = pdfAction;
        this.localDestinations.put(str, destination);
        return pdfAction;
    }

    /* access modifiers changed from: package-private */
    public boolean localDestination(String str, PdfDestination pdfDestination) {
        Destination destination = this.localDestinations.get(str);
        if (destination == null) {
            destination = new Destination();
        }
        if (destination.destination != null) {
            return false;
        }
        destination.destination = pdfDestination;
        this.localDestinations.put(str, destination);
        if (pdfDestination.hasPage()) {
            return true;
        }
        pdfDestination.addPage(this.writer.getCurrentPage());
        return true;
    }

    /* access modifiers changed from: package-private */
    public void addJavaScript(PdfAction pdfAction) {
        if (pdfAction.get(PdfName.JS) == null) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("only.javascript.actions.are.allowed", new Object[0]));
        }
        try {
            HashMap<String, PdfObject> hashMap = this.documentLevelJS;
            DecimalFormat decimalFormat = SIXTEEN_DIGITS;
            int i = this.jsCounter;
            this.jsCounter = i + 1;
            hashMap.put(decimalFormat.format((long) i), this.writer.addToBody(pdfAction).getIndirectReference());
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void addJavaScript(String str, PdfAction pdfAction) {
        if (pdfAction.get(PdfName.JS) == null) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("only.javascript.actions.are.allowed", new Object[0]));
        }
        try {
            this.documentLevelJS.put(str, this.writer.addToBody(pdfAction).getIndirectReference());
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, PdfObject> getDocumentLevelJS() {
        return this.documentLevelJS;
    }

    /* access modifiers changed from: package-private */
    public void addFileAttachment(String str, PdfFileSpecification pdfFileSpecification) throws IOException {
        if (str == null) {
            PdfString pdfString = (PdfString) pdfFileSpecification.get(PdfName.DESC);
            if (pdfString == null) {
                str = "";
            } else {
                str = PdfEncodings.convertToString(pdfString.getBytes(), (String) null);
            }
        }
        pdfFileSpecification.addDescription(str, true);
        if (str.length() == 0) {
            str = "Unnamed";
        }
        String convertToString = PdfEncodings.convertToString(new PdfString(str, PdfObject.TEXT_UNICODE).getBytes(), (String) null);
        int i = 0;
        while (this.documentFileAttachment.containsKey(convertToString)) {
            i++;
            convertToString = PdfEncodings.convertToString(new PdfString(str + " " + i, PdfObject.TEXT_UNICODE).getBytes(), (String) null);
        }
        this.documentFileAttachment.put(convertToString, pdfFileSpecification.getReference());
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, PdfObject> getDocumentFileAttachment() {
        return this.documentFileAttachment;
    }

    /* access modifiers changed from: package-private */
    public void setOpenAction(String str) {
        this.openActionName = str;
        this.openActionAction = null;
    }

    /* access modifiers changed from: package-private */
    public void setOpenAction(PdfAction pdfAction) {
        this.openActionAction = pdfAction;
        this.openActionName = null;
    }

    /* access modifiers changed from: package-private */
    public void addAdditionalAction(PdfName pdfName, PdfAction pdfAction) {
        if (this.additionalActions == null) {
            this.additionalActions = new PdfDictionary();
        }
        if (pdfAction == null) {
            this.additionalActions.remove(pdfName);
        } else {
            this.additionalActions.put(pdfName, pdfAction);
        }
        if (this.additionalActions.size() == 0) {
            this.additionalActions = null;
        }
    }

    public void setCollection(PdfCollection pdfCollection) {
        this.collection = pdfCollection;
    }

    /* access modifiers changed from: package-private */
    public PdfAcroForm getAcroForm() {
        return this.annotationsImp.getAcroForm();
    }

    /* access modifiers changed from: package-private */
    public void setSigFlags(int i) {
        this.annotationsImp.setSigFlags(i);
    }

    /* access modifiers changed from: package-private */
    public void addCalculationOrder(PdfFormField pdfFormField) {
        this.annotationsImp.addCalculationOrder(pdfFormField);
    }

    /* access modifiers changed from: package-private */
    public void addAnnotation(PdfAnnotation pdfAnnotation) {
        this.pageEmpty = false;
        this.annotationsImp.addAnnotation(pdfAnnotation);
    }

    /* access modifiers changed from: package-private */
    public void setLanguage(String str) {
        this.language = new PdfString(str);
    }

    /* access modifiers changed from: package-private */
    public void setCropBoxSize(Rectangle rectangle) {
        setBoxSize("crop", rectangle);
    }

    /* access modifiers changed from: package-private */
    public void setBoxSize(String str, Rectangle rectangle) {
        if (rectangle == null) {
            this.boxSize.remove(str);
        } else {
            this.boxSize.put(str, new PdfRectangle(rectangle));
        }
    }

    /* access modifiers changed from: protected */
    public void setNewPageSizeAndMargins() {
        this.pageSize = this.nextPageSize;
        if (!this.marginMirroring || (getPageNumber() & 1) != 0) {
            this.marginLeft = this.nextMarginLeft;
            this.marginRight = this.nextMarginRight;
        } else {
            this.marginRight = this.nextMarginLeft;
            this.marginLeft = this.nextMarginRight;
        }
        if (!this.marginMirroringTopBottom || (getPageNumber() & 1) != 0) {
            this.marginTop = this.nextMarginTop;
            this.marginBottom = this.nextMarginBottom;
        } else {
            this.marginTop = this.nextMarginBottom;
            this.marginBottom = this.nextMarginTop;
        }
        if (!isTagged(this.writer)) {
            this.text = new PdfContentByte(this.writer);
            this.text.reset();
        } else {
            this.text = this.graphics;
        }
        this.text.beginText();
        this.text.moveText(left(), top());
        if (isTagged(this.writer)) {
            this.textEmptySize = this.text.size();
        }
    }

    /* access modifiers changed from: package-private */
    public Rectangle getBoxSize(String str) {
        PdfRectangle pdfRectangle = this.thisBoxSize.get(str);
        if (pdfRectangle != null) {
            return pdfRectangle.getRectangle();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setPageEmpty(boolean z) {
        this.pageEmpty = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isPageEmpty() {
        if (isTagged(this.writer)) {
            if (this.writer == null) {
                return true;
            }
            if (this.writer.getDirectContent().size(false) == 0 && this.writer.getDirectContentUnder().size(false) == 0 && this.text.size(false) - this.textEmptySize == 0 && (this.pageEmpty || this.writer.isPaused())) {
                return true;
            }
            return false;
        } else if (this.writer == null) {
            return true;
        } else {
            if (this.writer.getDirectContent().size() == 0 && this.writer.getDirectContentUnder().size() == 0 && (this.pageEmpty || this.writer.isPaused())) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void setDuration(int i) {
        if (i > 0) {
            this.writer.addPageDictEntry(PdfName.DUR, new PdfNumber(i));
        }
    }

    /* access modifiers changed from: package-private */
    public void setTransition(PdfTransition pdfTransition) {
        this.writer.addPageDictEntry(PdfName.TRANS, pdfTransition.getTransitionDictionary());
    }

    /* access modifiers changed from: package-private */
    public void setPageAction(PdfName pdfName, PdfAction pdfAction) {
        if (this.pageAA == null) {
            this.pageAA = new PdfDictionary();
        }
        this.pageAA.put(pdfName, pdfAction);
    }

    /* access modifiers changed from: package-private */
    public void setThumbnail(Image image) throws PdfException, DocumentException {
        this.writer.addPageDictEntry(PdfName.THUMB, this.writer.getImageReference(this.writer.addDirectImageSimple(image)));
    }

    /* access modifiers changed from: package-private */
    public PageResources getPageResources() {
        return this.pageResources;
    }

    /* access modifiers changed from: package-private */
    public boolean isStrictImageSequence() {
        return this.strictImageSequence;
    }

    /* access modifiers changed from: package-private */
    public void setStrictImageSequence(boolean z) {
        this.strictImageSequence = z;
    }

    public void clearTextWrap() {
        float f = this.imageEnd - this.currentHeight;
        if (this.line != null) {
            f += this.line.height();
        }
        if (this.imageEnd > -1.0f && f > 0.0f) {
            carriageReturn();
            this.currentHeight += f;
        }
    }

    public int getStructParentIndex(Object obj) {
        int[] iArr = this.structParentIndices.get(obj);
        if (iArr == null) {
            iArr = new int[]{this.structParentIndices.size(), 0};
            this.structParentIndices.put(obj, iArr);
        }
        return iArr[0];
    }

    public int getNextMarkPoint(Object obj) {
        int[] iArr = this.structParentIndices.get(obj);
        if (iArr == null) {
            iArr = new int[]{this.structParentIndices.size(), 0};
            this.structParentIndices.put(obj, iArr);
        }
        int i = iArr[1];
        iArr[1] = iArr[1] + 1;
        return i;
    }

    public int[] getStructParentIndexAndNextMarkPoint(Object obj) {
        int[] iArr = this.structParentIndices.get(obj);
        if (iArr == null) {
            iArr = new int[]{this.structParentIndices.size(), 0};
            this.structParentIndices.put(obj, iArr);
        }
        int i = iArr[1];
        iArr[1] = iArr[1] + 1;
        return new int[]{iArr[0], i};
    }

    /* access modifiers changed from: protected */
    public void add(Image image) throws PdfException, DocumentException {
        Image image2 = image;
        if (image.hasAbsoluteY()) {
            this.graphics.addImage(image2);
            this.pageEmpty = false;
            return;
        }
        if (this.currentHeight != 0.0f && (indentTop() - this.currentHeight) - image.getScaledHeight() < indentBottom()) {
            if (this.strictImageSequence || this.imageWait != null) {
                newPage();
                if (this.currentHeight != 0.0f && (indentTop() - this.currentHeight) - image.getScaledHeight() < indentBottom()) {
                    this.imageWait = image2;
                    return;
                }
            } else {
                this.imageWait = image2;
                return;
            }
        }
        this.pageEmpty = false;
        if (image2 == this.imageWait) {
            this.imageWait = null;
        }
        boolean z = (image.getAlignment() & 4) == 4 && (image.getAlignment() & 1) != 1;
        boolean z2 = (image.getAlignment() & 8) == 8;
        float f = this.leading / 2.0f;
        if (z) {
            f += this.leading;
        }
        float f2 = f;
        float indentTop = ((indentTop() - this.currentHeight) - image.getScaledHeight()) - f2;
        float[] matrix = image.matrix();
        float indentLeft = indentLeft() - matrix[4];
        if ((image.getAlignment() & 2) == 2) {
            indentLeft = (indentRight() - image.getScaledWidth()) - matrix[4];
        }
        if ((image.getAlignment() & 1) == 1) {
            indentLeft = (indentLeft() + (((indentRight() - indentLeft()) - image.getScaledWidth()) / 2.0f)) - matrix[4];
        }
        if (image.hasAbsoluteX()) {
            indentLeft = image.getAbsoluteX();
        }
        if (z) {
            if (this.imageEnd < 0.0f || this.imageEnd < this.currentHeight + image.getScaledHeight() + f2) {
                this.imageEnd = this.currentHeight + image.getScaledHeight() + f2;
            }
            if ((image.getAlignment() & 2) == 2) {
                this.indentation.imageIndentRight += image.getScaledWidth() + image.getIndentationLeft();
            } else {
                this.indentation.imageIndentLeft += image.getScaledWidth() + image.getIndentationRight();
            }
        } else if ((image.getAlignment() & 2) == 2) {
            indentLeft -= image.getIndentationRight();
        } else if ((image.getAlignment() & 1) == 1) {
            indentLeft += image.getIndentationLeft() - image.getIndentationRight();
        } else {
            indentLeft += image.getIndentationLeft();
        }
        this.graphics.addImage(image2, matrix[0], matrix[1], matrix[2], matrix[3], indentLeft, indentTop - matrix[5]);
        if (!z && !z2) {
            this.currentHeight += image.getScaledHeight() + f2;
            flushLines();
            this.text.moveText(0.0f, -(image.getScaledHeight() + f2));
            newLine();
        }
    }

    /* access modifiers changed from: package-private */
    public void addPTable(PdfPTable pdfPTable) throws DocumentException {
        ColumnText columnText = new ColumnText(isTagged(this.writer) ? this.text : this.writer.getDirectContent());
        columnText.setRunDirection(pdfPTable.getRunDirection());
        if (pdfPTable.getKeepTogether() && !fitsPage(pdfPTable, 0.0f) && this.currentHeight > 0.0f) {
            newPage();
            if (isTagged(this.writer)) {
                columnText.setCanvas(this.text);
            }
        }
        if (this.currentHeight == 0.0f) {
            columnText.setAdjustFirstLine(false);
        }
        columnText.addElement(pdfPTable);
        boolean isHeadersInEvent = pdfPTable.isHeadersInEvent();
        pdfPTable.setHeadersInEvent(true);
        int i = 0;
        while (true) {
            columnText.setSimpleColumn(indentLeft(), indentBottom(), indentRight(), indentTop() - this.currentHeight);
            if ((columnText.go() & 1) != 0) {
                if (isTagged(this.writer)) {
                    this.text.setTextMatrix(indentLeft(), columnText.getYLine());
                } else {
                    this.text.moveText(0.0f, (columnText.getYLine() - indentTop()) + this.currentHeight);
                }
                this.currentHeight = indentTop() - columnText.getYLine();
                pdfPTable.setHeadersInEvent(isHeadersInEvent);
                return;
            }
            i = indentTop() - this.currentHeight == columnText.getYLine() ? i + 1 : 0;
            if (i == 3) {
                throw new DocumentException(MessageLocalization.getComposedMessage("infinite.table.loop", new Object[0]));
            }
            this.currentHeight = indentTop() - columnText.getYLine();
            newPage();
            if (isTagged(this.writer)) {
                columnText.setCanvas(this.text);
            }
        }
    }

    private void addDiv(PdfDiv pdfDiv) throws DocumentException {
        if (this.floatingElements == null) {
            this.floatingElements = new ArrayList<>();
        }
        this.floatingElements.add(pdfDiv);
    }

    private void flushFloatingElements() throws DocumentException {
        if (this.floatingElements != null && !this.floatingElements.isEmpty()) {
            ArrayList<Element> arrayList = this.floatingElements;
            this.floatingElements = null;
            FloatLayout floatLayout = new FloatLayout(arrayList, false);
            int i = 0;
            while (true) {
                indentLeft();
                floatLayout.setSimpleColumn(indentLeft(), indentBottom(), indentRight(), indentTop() - this.currentHeight);
                try {
                    if ((floatLayout.layout(isTagged(this.writer) ? this.text : this.writer.getDirectContent(), false) & 1) != 0) {
                        if (isTagged(this.writer)) {
                            this.text.setTextMatrix(indentLeft(), floatLayout.getYLine());
                        } else {
                            this.text.moveText(0.0f, (floatLayout.getYLine() - indentTop()) + this.currentHeight);
                        }
                        this.currentHeight = indentTop() - floatLayout.getYLine();
                        return;
                    }
                    i = (indentTop() - this.currentHeight == floatLayout.getYLine() || isPageEmpty()) ? i + 1 : 0;
                    if (i != 2) {
                        newPage();
                    } else {
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean fitsPage(PdfPTable pdfPTable, float f) {
        if (!pdfPTable.isLockedWidth()) {
            pdfPTable.setTotalWidth(((indentRight() - indentLeft()) * pdfPTable.getWidthPercentage()) / 100.0f);
        }
        ensureNewLine();
        float floatValue = Float.valueOf(pdfPTable.isSkipFirstHeader() ? pdfPTable.getTotalHeight() - pdfPTable.getHeaderHeight() : pdfPTable.getTotalHeight()).floatValue();
        float f2 = 0.0f;
        if (this.currentHeight > 0.0f) {
            f2 = pdfPTable.spacingBefore();
        }
        return floatValue + f2 <= ((indentTop() - this.currentHeight) - indentBottom()) - f;
    }

    private static boolean isTagged(PdfWriter pdfWriter) {
        return pdfWriter != null && pdfWriter.isTagged();
    }

    private PdfLine getLastLine() {
        if (this.lines.size() > 0) {
            return this.lines.get(this.lines.size() - 1);
        }
        return null;
    }

    public class Destination {
        public PdfAction action;
        public PdfDestination destination;
        public PdfIndirectReference reference;

        public Destination() {
        }
    }

    /* access modifiers changed from: protected */
    public void useExternalCache(TempFileCache tempFileCache) {
        this.isToUseExternalCache = true;
        this.externalCache = tempFileCache;
    }

    /* access modifiers changed from: protected */
    public void saveStructElement(AccessibleElementId accessibleElementId, PdfStructureElement pdfStructureElement) {
        this.structElements.put(accessibleElementId, pdfStructureElement);
    }

    /* access modifiers changed from: protected */
    public PdfStructureElement getStructElement(AccessibleElementId accessibleElementId) {
        return getStructElement(accessibleElementId, true);
    }

    /* access modifiers changed from: protected */
    public PdfStructureElement getStructElement(AccessibleElementId accessibleElementId, boolean z) {
        TempFileCache.ObjectPosition objectPosition;
        PdfStructureElement pdfStructureElement = this.structElements.get(accessibleElementId);
        if (this.isToUseExternalCache && pdfStructureElement == null && (objectPosition = this.externallyStoredStructElements.get(accessibleElementId)) != null) {
            try {
                pdfStructureElement = (PdfStructureElement) this.externalCache.get(objectPosition);
                pdfStructureElement.setStructureTreeRoot(this.writer.getStructureTreeRoot());
                pdfStructureElement.setStructureElementParent(getStructElement(this.elementsParents.get(pdfStructureElement.getElementId()), z));
                if (z) {
                    this.externallyStoredStructElements.remove(accessibleElementId);
                    this.structElements.put(accessibleElementId, pdfStructureElement);
                }
            } catch (IOException e) {
                throw new ExceptionConverter(e);
            } catch (ClassNotFoundException e2) {
                throw new ExceptionConverter(e2);
            }
        }
        return pdfStructureElement;
    }

    /* access modifiers changed from: protected */
    public void flushStructureElementsOnNewPage() {
        if (this.isToUseExternalCache) {
            Iterator<Map.Entry<AccessibleElementId, PdfStructureElement>> it = this.structElements.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                if (!((PdfStructureElement) next.getValue()).getStructureType().equals(PdfName.DOCUMENT)) {
                    try {
                        PdfStructureElement pdfStructureElement = (PdfStructureElement) next.getValue();
                        PdfDictionary parent = pdfStructureElement.getParent();
                        PdfStructureElement pdfStructureElement2 = null;
                        if (parent instanceof PdfStructureElement) {
                            pdfStructureElement2 = (PdfStructureElement) parent;
                        }
                        if (pdfStructureElement2 != null) {
                            this.elementsParents.put(next.getKey(), pdfStructureElement2.getElementId());
                        }
                        this.externallyStoredStructElements.put(next.getKey(), this.externalCache.put(pdfStructureElement));
                        it.remove();
                    } catch (IOException e) {
                        throw new ExceptionConverter(e);
                    }
                }
            }
        }
    }

    public Set<AccessibleElementId> getStructElements() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.externallyStoredStructElements.keySet());
        hashSet.addAll(this.structElements.keySet());
        return hashSet;
    }
}
