package com.itextpdf.text.pdf;

import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.xml.XmlDomWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XfaForm {
    public static final String XFA_DATA_SCHEMA = "http://www.xfa.org/schema/xfa-data/1.0/";
    private AcroFieldsSearch acroFieldsSom;
    private boolean changed;
    private Node datasetsNode;
    private Xml2SomDatasets datasetsSom;
    private Document domDocument;
    private PdfReader reader;
    private Node templateNode;
    private Xml2SomTemplate templateSom;
    private boolean xfaPresent;

    public XfaForm() {
    }

    public static PdfObject getXfaObject(PdfReader pdfReader) {
        PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObjectRelease(pdfReader.getCatalog().get(PdfName.ACROFORM));
        if (pdfDictionary == null) {
            return null;
        }
        return PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.XFA));
    }

    public XfaForm(PdfReader pdfReader) throws IOException, ParserConfigurationException, SAXException {
        this.reader = pdfReader;
        PdfObject xfaObject = getXfaObject(pdfReader);
        if (xfaObject == null) {
            this.xfaPresent = false;
            return;
        }
        this.xfaPresent = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (xfaObject.isArray()) {
            PdfArray pdfArray = (PdfArray) xfaObject;
            for (int i = 1; i < pdfArray.size(); i += 2) {
                PdfObject directObject = pdfArray.getDirectObject(i);
                if (directObject instanceof PRStream) {
                    byteArrayOutputStream.write(PdfReader.getStreamBytes((PRStream) directObject));
                }
            }
        } else if (xfaObject instanceof PRStream) {
            byteArrayOutputStream.write(PdfReader.getStreamBytes((PRStream) xfaObject));
        }
        byteArrayOutputStream.close();
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        this.domDocument = newInstance.newDocumentBuilder().parse(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        extractNodes();
    }

    private void extractNodes() {
        Map<String, Node> extractXFANodes = extractXFANodes(this.domDocument);
        if (extractXFANodes.containsKey("template")) {
            this.templateNode = extractXFANodes.get("template");
            this.templateSom = new Xml2SomTemplate(this.templateNode);
        }
        if (extractXFANodes.containsKey("datasets")) {
            this.datasetsNode = extractXFANodes.get("datasets");
            this.datasetsSom = new Xml2SomDatasets(this.datasetsNode.getFirstChild());
        }
        if (this.datasetsNode == null) {
            createDatasetsNode(this.domDocument.getFirstChild());
        }
    }

    public static Map<String, Node> extractXFANodes(Document document) {
        HashMap hashMap = new HashMap();
        Node firstChild = document.getFirstChild();
        while (firstChild.getChildNodes().getLength() == 0) {
            firstChild = firstChild.getNextSibling();
        }
        for (Node firstChild2 = firstChild.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
            if (firstChild2.getNodeType() == 1) {
                hashMap.put(firstChild2.getLocalName(), firstChild2);
            }
        }
        return hashMap;
    }

    private void createDatasetsNode(Node node) {
        while (node.getChildNodes().getLength() == 0) {
            node = node.getNextSibling();
        }
        if (node != null) {
            Element createElement = node.getOwnerDocument().createElement("xfa:datasets");
            createElement.setAttribute("xmlns:xfa", XFA_DATA_SCHEMA);
            this.datasetsNode = createElement;
            node.appendChild(this.datasetsNode);
        }
    }

    public static void setXfa(XfaForm xfaForm, PdfReader pdfReader, PdfWriter pdfWriter) throws IOException {
        PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObjectRelease(pdfReader.getCatalog().get(PdfName.ACROFORM));
        if (pdfDictionary != null) {
            PdfObject xfaObject = getXfaObject(pdfReader);
            if (xfaObject.isArray()) {
                PdfArray pdfArray = (PdfArray) xfaObject;
                int i = -1;
                int i2 = -1;
                for (int i3 = 0; i3 < pdfArray.size(); i3 += 2) {
                    PdfString asString = pdfArray.getAsString(i3);
                    if ("template".equals(asString.toString())) {
                        i = i3 + 1;
                    }
                    if ("datasets".equals(asString.toString())) {
                        i2 = i3 + 1;
                    }
                }
                if (i > -1 && i2 > -1) {
                    pdfReader.killXref(pdfArray.getAsIndirectObject(i));
                    pdfReader.killXref(pdfArray.getAsIndirectObject(i2));
                    PdfStream pdfStream = new PdfStream(serializeDoc(xfaForm.templateNode));
                    pdfStream.flateCompress(pdfWriter.getCompressionLevel());
                    pdfArray.set(i, pdfWriter.addToBody(pdfStream).getIndirectReference());
                    PdfStream pdfStream2 = new PdfStream(serializeDoc(xfaForm.datasetsNode));
                    pdfStream2.flateCompress(pdfWriter.getCompressionLevel());
                    pdfArray.set(i2, pdfWriter.addToBody(pdfStream2).getIndirectReference());
                    pdfDictionary.put(PdfName.XFA, new PdfArray(pdfArray));
                    return;
                }
            }
            pdfReader.killXref(pdfDictionary.get(PdfName.XFA));
            PdfStream pdfStream3 = new PdfStream(serializeDoc(xfaForm.domDocument));
            pdfStream3.flateCompress(pdfWriter.getCompressionLevel());
            pdfDictionary.put(PdfName.XFA, pdfWriter.addToBody(pdfStream3).getIndirectReference());
        }
    }

    public void setXfa(PdfWriter pdfWriter) throws IOException {
        setXfa(this, this.reader, pdfWriter);
    }

    public static byte[] serializeDoc(Node node) throws IOException {
        XmlDomWriter xmlDomWriter = new XmlDomWriter();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xmlDomWriter.setOutput(byteArrayOutputStream, (String) null);
        xmlDomWriter.setCanonical(false);
        xmlDomWriter.write(node);
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public boolean isXfaPresent() {
        return this.xfaPresent;
    }

    public Document getDomDocument() {
        return this.domDocument;
    }

    public String findFieldName(String str, AcroFields acroFields) {
        Map<String, AcroFields.Item> fields = acroFields.getFields();
        if (fields.containsKey(str)) {
            return str;
        }
        if (this.acroFieldsSom == null) {
            if (!fields.isEmpty() || !this.xfaPresent) {
                this.acroFieldsSom = new AcroFieldsSearch(fields.keySet());
            } else {
                this.acroFieldsSom = new AcroFieldsSearch(this.datasetsSom.getName2Node().keySet());
            }
        }
        if (this.acroFieldsSom.getAcroShort2LongName().containsKey(str)) {
            return this.acroFieldsSom.getAcroShort2LongName().get(str);
        }
        return this.acroFieldsSom.inverseSearchGlobal(Xml2Som.splitParts(str));
    }

    public String findDatasetsName(String str) {
        if (this.datasetsSom.getName2Node().containsKey(str)) {
            return str;
        }
        return this.datasetsSom.inverseSearchGlobal(Xml2Som.splitParts(str));
    }

    public Node findDatasetsNode(String str) {
        String findDatasetsName;
        if (str == null || (findDatasetsName = findDatasetsName(str)) == null) {
            return null;
        }
        return this.datasetsSom.getName2Node().get(findDatasetsName);
    }

    public static String getNodeText(Node node) {
        return node == null ? "" : getNodeText(node, "");
    }

    private static String getNodeText(Node node, String str) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1) {
                str = getNodeText(firstChild, str);
            } else if (firstChild.getNodeType() == 3) {
                str = str + firstChild.getNodeValue();
            }
        }
        return str;
    }

    public void setNodeText(Node node, String str) {
        if (node != null) {
            while (true) {
                Node firstChild = node.getFirstChild();
                if (firstChild == null) {
                    break;
                }
                node.removeChild(firstChild);
            }
            if (node.getAttributes().getNamedItemNS(XFA_DATA_SCHEMA, "dataNode") != null) {
                node.getAttributes().removeNamedItemNS(XFA_DATA_SCHEMA, "dataNode");
            }
            node.appendChild(this.domDocument.createTextNode(str));
            this.changed = true;
        }
    }

    public void setXfaPresent(boolean z) {
        this.xfaPresent = z;
    }

    public void setDomDocument(Document document) {
        this.domDocument = document;
        extractNodes();
    }

    public PdfReader getReader() {
        return this.reader;
    }

    public void setReader(PdfReader pdfReader) {
        this.reader = pdfReader;
    }

    public boolean isChanged() {
        return this.changed;
    }

    public void setChanged(boolean z) {
        this.changed = z;
    }

    public static class InverseStore {
        protected ArrayList<Object> follow = new ArrayList<>();
        protected ArrayList<String> part = new ArrayList<>();

        public String getDefaultName() {
            InverseStore inverseStore = this;
            while (true) {
                Object obj = inverseStore.follow.get(0);
                if (obj instanceof String) {
                    return (String) obj;
                }
                inverseStore = (InverseStore) obj;
            }
        }

        public boolean isSimilar(String str) {
            String substring = str.substring(0, str.indexOf(91) + 1);
            for (int i = 0; i < this.part.size(); i++) {
                if (this.part.get(i).startsWith(substring)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class Stack2<T> extends ArrayList<T> {
        private static final long serialVersionUID = -7451476576174095212L;

        public T peek() {
            if (size() != 0) {
                return get(size() - 1);
            }
            throw new EmptyStackException();
        }

        public T pop() {
            if (size() == 0) {
                throw new EmptyStackException();
            }
            T t = get(size() - 1);
            remove(size() - 1);
            return t;
        }

        public T push(T t) {
            add(t);
            return t;
        }

        public boolean empty() {
            return size() == 0;
        }
    }

    public static class Xml2Som {
        protected int anform;
        protected HashMap<String, InverseStore> inverseSearch;
        protected HashMap<String, Node> name2Node;
        protected ArrayList<String> order;
        protected Stack2<String> stack;

        public static String escapeSom(String str) {
            if (str == null) {
                return "";
            }
            int indexOf = str.indexOf(46);
            if (indexOf < 0) {
                return str;
            }
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (true) {
                int i2 = i;
                i = indexOf;
                int i3 = i2;
                if (i >= 0) {
                    stringBuffer.append(str.substring(i3, i));
                    stringBuffer.append('\\');
                    indexOf = str.indexOf(46, i + 1);
                } else {
                    stringBuffer.append(str.substring(i3));
                    return stringBuffer.toString();
                }
            }
        }

        public static String unescapeSom(String str) {
            int indexOf = str.indexOf(92);
            if (indexOf < 0) {
                return str;
            }
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (indexOf >= 0) {
                stringBuffer.append(str.substring(i, indexOf));
                i = indexOf + 1;
                indexOf = str.indexOf(92, i);
            }
            stringBuffer.append(str.substring(i));
            return stringBuffer.toString();
        }

        /* access modifiers changed from: protected */
        public String printStack() {
            if (this.stack.empty()) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < this.stack.size(); i++) {
                stringBuffer.append('.');
                stringBuffer.append((String) this.stack.get(i));
            }
            return stringBuffer.substring(1);
        }

        public static String getShortName(String str) {
            int indexOf = str.indexOf(".#subform[");
            if (indexOf < 0) {
                return str;
            }
            int i = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while (indexOf >= 0) {
                stringBuffer.append(str.substring(i, indexOf));
                int indexOf2 = str.indexOf("]", indexOf + 10);
                if (indexOf2 < 0) {
                    return stringBuffer.toString();
                }
                i = indexOf2 + 1;
                indexOf = str.indexOf(".#subform[", i);
            }
            stringBuffer.append(str.substring(i));
            return stringBuffer.toString();
        }

        public void inverseSearchAdd(String str) {
            inverseSearchAdd(this.inverseSearch, this.stack, str);
        }

        public static void inverseSearchAdd(HashMap<String, InverseStore> hashMap, Stack2<String> stack2, String str) {
            InverseStore inverseStore;
            String peek = stack2.peek();
            InverseStore inverseStore2 = hashMap.get(peek);
            if (inverseStore2 == null) {
                inverseStore2 = new InverseStore();
                hashMap.put(peek, inverseStore2);
            }
            for (int size = stack2.size() - 2; size >= 0; size--) {
                String str2 = (String) stack2.get(size);
                int indexOf = inverseStore2.part.indexOf(str2);
                if (indexOf < 0) {
                    inverseStore2.part.add(str2);
                    inverseStore = new InverseStore();
                    inverseStore2.follow.add(inverseStore);
                } else {
                    inverseStore = (InverseStore) inverseStore2.follow.get(indexOf);
                }
                inverseStore2 = inverseStore;
            }
            inverseStore2.part.add("");
            inverseStore2.follow.add(str);
        }

        public String inverseSearchGlobal(ArrayList<String> arrayList) {
            InverseStore inverseStore;
            if (arrayList.isEmpty() || (inverseStore = this.inverseSearch.get(arrayList.get(arrayList.size() - 1))) == null) {
                return null;
            }
            int size = arrayList.size() - 2;
            while (size >= 0) {
                String str = arrayList.get(size);
                int indexOf = inverseStore.part.indexOf(str);
                if (indexOf >= 0) {
                    inverseStore = (InverseStore) inverseStore.follow.get(indexOf);
                    size--;
                } else if (inverseStore.isSimilar(str)) {
                    return null;
                } else {
                    return inverseStore.getDefaultName();
                }
            }
            return inverseStore.getDefaultName();
        }

        public static Stack2<String> splitParts(String str) {
            int indexOf;
            while (str.startsWith(".")) {
                str = str.substring(1);
            }
            Stack2<String> stack2 = new Stack2<>();
            int i = 0;
            while (true) {
                int i2 = i;
                while (true) {
                    indexOf = str.indexOf(46, i2);
                    if (indexOf >= 0 && str.charAt(indexOf - 1) == '\\') {
                        i2 = indexOf + 1;
                    }
                }
                if (indexOf < 0) {
                    break;
                }
                String substring = str.substring(i, indexOf);
                if (!substring.endsWith("]")) {
                    substring = substring + "[0]";
                }
                stack2.add(substring);
                i = indexOf + 1;
            }
            String substring2 = str.substring(i);
            if (!substring2.endsWith("]")) {
                substring2 = substring2 + "[0]";
            }
            stack2.add(substring2);
            return stack2;
        }

        public ArrayList<String> getOrder() {
            return this.order;
        }

        public void setOrder(ArrayList<String> arrayList) {
            this.order = arrayList;
        }

        public HashMap<String, Node> getName2Node() {
            return this.name2Node;
        }

        public void setName2Node(HashMap<String, Node> hashMap) {
            this.name2Node = hashMap;
        }

        public HashMap<String, InverseStore> getInverseSearch() {
            return this.inverseSearch;
        }

        public void setInverseSearch(HashMap<String, InverseStore> hashMap) {
            this.inverseSearch = hashMap;
        }
    }

    public static class Xml2SomDatasets extends Xml2Som {
        public Xml2SomDatasets(Node node) {
            this.order = new ArrayList();
            this.name2Node = new HashMap();
            this.stack = new Stack2();
            this.anform = 0;
            this.inverseSearch = new HashMap();
            processDatasetsInternal(node);
        }

        public Node insertNode(Node node, String str) {
            Stack2<String> splitParts = splitParts(str);
            Document ownerDocument = node.getOwnerDocument();
            Node firstChild = node.getFirstChild();
            while (firstChild.getNodeType() != 1) {
                firstChild = firstChild.getNextSibling();
            }
            Node node2 = firstChild;
            int i = 0;
            Node node3 = null;
            while (i < splitParts.size()) {
                String str2 = (String) splitParts.get(i);
                int lastIndexOf = str2.lastIndexOf(91);
                String substring = str2.substring(0, lastIndexOf);
                int parseInt = Integer.parseInt(str2.substring(lastIndexOf + 1, str2.length() - 1));
                int i2 = -1;
                Node firstChild2 = node2.getFirstChild();
                while (firstChild2 != null && (firstChild2.getNodeType() != 1 || !escapeSom(firstChild2.getLocalName()).equals(substring) || (i2 = i2 + 1) != parseInt)) {
                    firstChild2 = firstChild2.getNextSibling();
                }
                while (i2 < parseInt) {
                    firstChild2 = node2.appendChild(ownerDocument.createElementNS((String) null, substring));
                    Attr createAttributeNS = ownerDocument.createAttributeNS(XfaForm.XFA_DATA_SCHEMA, "dataNode");
                    createAttributeNS.setNodeValue("dataGroup");
                    firstChild2.getAttributes().setNamedItemNS(createAttributeNS);
                    i2++;
                }
                i++;
                node2 = firstChild2;
                node3 = node2;
            }
            inverseSearchAdd(this.inverseSearch, splitParts, str);
            this.name2Node.put(str, node3);
            this.order.add(str);
            return node3;
        }

        private static boolean hasChildren(Node node) {
            Node namedItemNS = node.getAttributes().getNamedItemNS(XfaForm.XFA_DATA_SCHEMA, "dataNode");
            if (namedItemNS != null) {
                String nodeValue = namedItemNS.getNodeValue();
                if ("dataGroup".equals(nodeValue)) {
                    return true;
                }
                if ("dataValue".equals(nodeValue)) {
                    return false;
                }
            }
            if (!node.hasChildNodes()) {
                return false;
            }
            for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                if (firstChild.getNodeType() == 1) {
                    return true;
                }
            }
            return false;
        }

        private void processDatasetsInternal(Node node) {
            Integer num;
            if (node != null) {
                HashMap hashMap = new HashMap();
                for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                    if (firstChild.getNodeType() == 1) {
                        String escapeSom = escapeSom(firstChild.getLocalName());
                        Integer num2 = (Integer) hashMap.get(escapeSom);
                        if (num2 == null) {
                            num = 0;
                        } else {
                            num = Integer.valueOf(num2.intValue() + 1);
                        }
                        hashMap.put(escapeSom, num);
                        if (hasChildren(firstChild)) {
                            Stack2 stack2 = this.stack;
                            stack2.push(escapeSom + "[" + num.toString() + "]");
                            processDatasetsInternal(firstChild);
                            this.stack.pop();
                        } else {
                            Stack2 stack22 = this.stack;
                            stack22.push(escapeSom + "[" + num.toString() + "]");
                            String printStack = printStack();
                            this.order.add(printStack);
                            inverseSearchAdd(printStack);
                            this.name2Node.put(printStack, firstChild);
                            this.stack.pop();
                        }
                    }
                }
            }
        }
    }

    public static class AcroFieldsSearch extends Xml2Som {
        private HashMap<String, String> acroShort2LongName = new HashMap<>();

        public AcroFieldsSearch(Collection<String> collection) {
            this.inverseSearch = new HashMap();
            for (String next : collection) {
                String shortName = getShortName(next);
                this.acroShort2LongName.put(shortName, next);
                inverseSearchAdd(this.inverseSearch, splitParts(shortName), next);
            }
        }

        public HashMap<String, String> getAcroShort2LongName() {
            return this.acroShort2LongName;
        }

        public void setAcroShort2LongName(HashMap<String, String> hashMap) {
            this.acroShort2LongName = hashMap;
        }
    }

    public static class Xml2SomTemplate extends Xml2Som {
        private boolean dynamicForm;
        private int templateLevel = 0;

        public Xml2SomTemplate(Node node) {
            this.order = new ArrayList();
            this.name2Node = new HashMap();
            this.stack = new Stack2();
            this.anform = 0;
            this.inverseSearch = new HashMap();
            processTemplate(node, (HashMap<String, Integer>) null);
        }

        public String getFieldType(String str) {
            Node node = (Node) this.name2Node.get(str);
            if (node == null) {
                return null;
            }
            if ("exclGroup".equals(node.getLocalName())) {
                return "exclGroup";
            }
            Node firstChild = node.getFirstChild();
            while (firstChild != null && (firstChild.getNodeType() != 1 || !"ui".equals(firstChild.getLocalName()))) {
                firstChild = firstChild.getNextSibling();
            }
            if (firstChild == null) {
                return null;
            }
            for (Node firstChild2 = firstChild.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
                if (firstChild2.getNodeType() == 1 && (!"extras".equals(firstChild2.getLocalName()) || !"picture".equals(firstChild2.getLocalName()))) {
                    return firstChild2.getLocalName();
                }
            }
            return null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:42:0x00ea A[SYNTHETIC, Splitter:B:42:0x00ea] */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x0104 A[SYNTHETIC, Splitter:B:48:0x0104] */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x0114 A[ADDED_TO_REGION] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void processTemplate(org.w3c.dom.Node r8, java.util.HashMap<java.lang.String, java.lang.Integer> r9) {
            /*
                r7 = this;
                if (r9 != 0) goto L_0x0007
                java.util.HashMap r9 = new java.util.HashMap
                r9.<init>()
            L_0x0007:
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                org.w3c.dom.Node r8 = r8.getFirstChild()
            L_0x0010:
                if (r8 == 0) goto L_0x0184
                short r1 = r8.getNodeType()
                r2 = 1
                if (r1 != r2) goto L_0x017e
                java.lang.String r1 = r8.getLocalName()
                java.lang.String r3 = "subform"
                boolean r3 = r3.equals(r1)
                r4 = 0
                if (r3 == 0) goto L_0x00a3
                org.w3c.dom.NamedNodeMap r1 = r8.getAttributes()
                java.lang.String r3 = "name"
                org.w3c.dom.Node r1 = r1.getNamedItem(r3)
                java.lang.String r3 = "#subform"
                if (r1 == 0) goto L_0x003e
                java.lang.String r1 = r1.getNodeValue()
                java.lang.String r3 = escapeSom(r1)
                r1 = r4
                goto L_0x003f
            L_0x003e:
                r1 = r2
            L_0x003f:
                if (r1 == 0) goto L_0x004d
                int r4 = r7.anform
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                int r5 = r7.anform
                int r5 = r5 + r2
                r7.anform = r5
                goto L_0x0066
            L_0x004d:
                java.lang.Object r5 = r0.get(r3)
                java.lang.Integer r5 = (java.lang.Integer) r5
                if (r5 != 0) goto L_0x005a
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                goto L_0x0063
            L_0x005a:
                int r4 = r5.intValue()
                int r4 = r4 + r2
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            L_0x0063:
                r0.put(r3, r4)
            L_0x0066:
                com.itextpdf.text.pdf.XfaForm$Stack2 r5 = r7.stack
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                r6.append(r3)
                java.lang.String r3 = "["
                r6.append(r3)
                java.lang.String r3 = r4.toString()
                r6.append(r3)
                java.lang.String r3 = "]"
                r6.append(r3)
                java.lang.String r3 = r6.toString()
                r5.push(r3)
                int r3 = r7.templateLevel
                int r3 = r3 + r2
                r7.templateLevel = r3
                if (r1 == 0) goto L_0x0093
                r7.processTemplate(r8, r9)
                goto L_0x0097
            L_0x0093:
                r1 = 0
                r7.processTemplate(r8, r1)
            L_0x0097:
                int r1 = r7.templateLevel
                int r1 = r1 - r2
                r7.templateLevel = r1
                com.itextpdf.text.pdf.XfaForm$Stack2 r1 = r7.stack
                r1.pop()
                goto L_0x017e
            L_0x00a3:
                java.lang.String r3 = "field"
                boolean r3 = r3.equals(r1)
                if (r3 != 0) goto L_0x0119
                java.lang.String r3 = "exclGroup"
                boolean r3 = r3.equals(r1)
                if (r3 == 0) goto L_0x00b4
                goto L_0x0119
            L_0x00b4:
                boolean r3 = r7.dynamicForm
                if (r3 != 0) goto L_0x017e
                int r3 = r7.templateLevel
                if (r3 <= 0) goto L_0x017e
                java.lang.String r3 = "occur"
                boolean r1 = r3.equals(r1)
                if (r1 == 0) goto L_0x017e
                org.w3c.dom.NamedNodeMap r1 = r8.getAttributes()
                java.lang.String r3 = "initial"
                org.w3c.dom.Node r1 = r1.getNamedItem(r3)
                if (r1 == 0) goto L_0x00dd
                java.lang.String r1 = r1.getNodeValue()     // Catch:{ Exception -> 0x00dd }
                java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x00dd }
                int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x00dd }
                goto L_0x00de
            L_0x00dd:
                r1 = r2
            L_0x00de:
                org.w3c.dom.NamedNodeMap r3 = r8.getAttributes()
                java.lang.String r4 = "min"
                org.w3c.dom.Node r3 = r3.getNamedItem(r4)
                if (r3 == 0) goto L_0x00f7
                java.lang.String r3 = r3.getNodeValue()     // Catch:{ Exception -> 0x00f7 }
                java.lang.String r3 = r3.trim()     // Catch:{ Exception -> 0x00f7 }
                int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ Exception -> 0x00f7 }
                goto L_0x00f8
            L_0x00f7:
                r3 = r2
            L_0x00f8:
                org.w3c.dom.NamedNodeMap r4 = r8.getAttributes()
                java.lang.String r5 = "max"
                org.w3c.dom.Node r4 = r4.getNamedItem(r5)
                if (r4 == 0) goto L_0x0111
                java.lang.String r4 = r4.getNodeValue()     // Catch:{ Exception -> 0x0111 }
                java.lang.String r4 = r4.trim()     // Catch:{ Exception -> 0x0111 }
                int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x0111 }
                goto L_0x0112
            L_0x0111:
                r4 = r2
            L_0x0112:
                if (r1 != r3) goto L_0x0116
                if (r3 == r4) goto L_0x017e
            L_0x0116:
                r7.dynamicForm = r2
                goto L_0x017e
            L_0x0119:
                org.w3c.dom.NamedNodeMap r1 = r8.getAttributes()
                java.lang.String r3 = "name"
                org.w3c.dom.Node r1 = r1.getNamedItem(r3)
                if (r1 == 0) goto L_0x017e
                java.lang.String r1 = r1.getNodeValue()
                java.lang.String r1 = escapeSom(r1)
                java.lang.Object r3 = r9.get(r1)
                java.lang.Integer r3 = (java.lang.Integer) r3
                if (r3 != 0) goto L_0x013a
                java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
                goto L_0x0143
            L_0x013a:
                int r3 = r3.intValue()
                int r3 = r3 + r2
                java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            L_0x0143:
                r9.put(r1, r2)
                com.itextpdf.text.pdf.XfaForm$Stack2 r3 = r7.stack
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r1)
                java.lang.String r1 = "["
                r4.append(r1)
                java.lang.String r1 = r2.toString()
                r4.append(r1)
                java.lang.String r1 = "]"
                r4.append(r1)
                java.lang.String r1 = r4.toString()
                r3.push(r1)
                java.lang.String r1 = r7.printStack()
                java.util.ArrayList r2 = r7.order
                r2.add(r1)
                r7.inverseSearchAdd(r1)
                java.util.HashMap r2 = r7.name2Node
                r2.put(r1, r8)
                com.itextpdf.text.pdf.XfaForm$Stack2 r1 = r7.stack
                r1.pop()
            L_0x017e:
                org.w3c.dom.Node r8 = r8.getNextSibling()
                goto L_0x0010
            L_0x0184:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.XfaForm.Xml2SomTemplate.processTemplate(org.w3c.dom.Node, java.util.HashMap):void");
        }

        public boolean isDynamicForm() {
            return this.dynamicForm;
        }

        public void setDynamicForm(boolean z) {
            this.dynamicForm = z;
        }
    }

    public Xml2SomTemplate getTemplateSom() {
        return this.templateSom;
    }

    public void setTemplateSom(Xml2SomTemplate xml2SomTemplate) {
        this.templateSom = xml2SomTemplate;
    }

    public Xml2SomDatasets getDatasetsSom() {
        return this.datasetsSom;
    }

    public void setDatasetsSom(Xml2SomDatasets xml2SomDatasets) {
        this.datasetsSom = xml2SomDatasets;
    }

    public AcroFieldsSearch getAcroFieldsSom() {
        return this.acroFieldsSom;
    }

    public void setAcroFieldsSom(AcroFieldsSearch acroFieldsSearch) {
        this.acroFieldsSom = acroFieldsSearch;
    }

    public Node getDatasetsNode() {
        return this.datasetsNode;
    }

    public void fillXfaForm(File file) throws IOException {
        fillXfaForm(file, false);
    }

    public void fillXfaForm(File file, boolean z) throws IOException {
        fillXfaForm((InputStream) new FileInputStream(file), z);
    }

    public void fillXfaForm(InputStream inputStream) throws IOException {
        fillXfaForm(inputStream, false);
    }

    public void fillXfaForm(InputStream inputStream, boolean z) throws IOException {
        fillXfaForm(new InputSource(inputStream), z);
    }

    public void fillXfaForm(InputSource inputSource) throws IOException {
        fillXfaForm(inputSource, false);
    }

    public void fillXfaForm(InputSource inputSource, boolean z) throws IOException {
        try {
            fillXfaForm((Node) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource).getDocumentElement(), z);
        } catch (ParserConfigurationException e) {
            throw new ExceptionConverter(e);
        } catch (SAXException e2) {
            throw new ExceptionConverter(e2);
        }
    }

    public void fillXfaForm(Node node) {
        fillXfaForm(node, false);
    }

    public void fillXfaForm(Node node, boolean z) {
        int i = 0;
        if (z) {
            NodeList elementsByTagName = this.domDocument.getElementsByTagName("field");
            for (int i2 = 0; i2 < elementsByTagName.getLength(); i2++) {
                ((Element) elementsByTagName.item(i2)).setAttribute("access", "readOnly");
            }
        }
        NodeList childNodes = this.datasetsNode.getChildNodes();
        int length = childNodes.getLength();
        Node node2 = null;
        while (true) {
            if (i >= length) {
                break;
            }
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1 && item.getLocalName().equals("data") && XFA_DATA_SCHEMA.equals(item.getNamespaceURI())) {
                node2 = item;
                break;
            }
            i++;
        }
        if (node2 == null) {
            node2 = this.datasetsNode.getOwnerDocument().createElementNS(XFA_DATA_SCHEMA, "xfa:data");
            this.datasetsNode.appendChild(node2);
        }
        if (node2.getChildNodes().getLength() == 0) {
            node2.appendChild(this.domDocument.importNode(node, true));
        } else {
            Node firstElementNode = getFirstElementNode(node2);
            if (firstElementNode != null) {
                node2.replaceChild(this.domDocument.importNode(node, true), firstElementNode);
            }
        }
        extractNodes();
        setChanged(true);
    }

    private Node getFirstElementNode(Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() == 1) {
                return childNodes.item(i);
            }
        }
        return null;
    }
}
