package com.itextpdf.xmp.impl;

import com.itextpdf.xmp.XMPError;
import com.itextpdf.xmp.XMPException;
import com.itextpdf.xmp.XMPMeta;
import com.itextpdf.xmp.options.ParseOptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMPMetaParser {
    private static final Object XMP_RDF = new Object();
    private static DocumentBuilderFactory factory = createDocumentBuilderFactory();

    private XMPMetaParser() {
    }

    public static XMPMeta parse(Object obj, ParseOptions parseOptions) throws XMPException {
        ParameterAsserts.assertNotNull(obj);
        if (parseOptions == null) {
            parseOptions = new ParseOptions();
        }
        Object[] findRootNode = findRootNode(parseXml(obj, parseOptions), parseOptions.getRequireXMPMeta(), new Object[3]);
        if (findRootNode == null || findRootNode[1] != XMP_RDF) {
            return new XMPMetaImpl();
        }
        XMPMetaImpl parse = ParseRDF.parse((Node) findRootNode[0]);
        parse.setPacketHeader((String) findRootNode[2]);
        return !parseOptions.getOmitNormalization() ? XMPNormalizer.process(parse, parseOptions) : parse;
    }

    private static Document parseXml(Object obj, ParseOptions parseOptions) throws XMPException {
        if (obj instanceof InputStream) {
            return parseXmlFromInputStream((InputStream) obj, parseOptions);
        }
        if (obj instanceof byte[]) {
            return parseXmlFromBytebuffer(new ByteBuffer((byte[]) obj), parseOptions);
        }
        return parseXmlFromString((String) obj, parseOptions);
    }

    private static Document parseXmlFromInputStream(InputStream inputStream, ParseOptions parseOptions) throws XMPException {
        if (!parseOptions.getAcceptLatin1() && !parseOptions.getFixControlChars()) {
            return parseInputSource(new InputSource(inputStream));
        }
        try {
            return parseXmlFromBytebuffer(new ByteBuffer(inputStream), parseOptions);
        } catch (IOException e) {
            throw new XMPException("Error reading the XML-file", XMPError.BADSTREAM, e);
        }
    }

    private static Document parseXmlFromBytebuffer(ByteBuffer byteBuffer, ParseOptions parseOptions) throws XMPException {
        try {
            return parseInputSource(new InputSource(byteBuffer.getByteStream()));
        } catch (XMPException e) {
            if (e.getErrorCode() == 201 || e.getErrorCode() == 204) {
                if (parseOptions.getAcceptLatin1()) {
                    byteBuffer = Latin1Converter.convert(byteBuffer);
                }
                if (!parseOptions.getFixControlChars()) {
                    return parseInputSource(new InputSource(byteBuffer.getByteStream()));
                }
                try {
                    return parseInputSource(new InputSource(new FixASCIIControlsReader(new InputStreamReader(byteBuffer.getByteStream(), byteBuffer.getEncoding()))));
                } catch (UnsupportedEncodingException unused) {
                    throw new XMPException("Unsupported Encoding", 9, e);
                }
            } else {
                throw e;
            }
        }
    }

    private static Document parseXmlFromString(String str, ParseOptions parseOptions) throws XMPException {
        try {
            return parseInputSource(new InputSource(new StringReader(str)));
        } catch (XMPException e) {
            if (e.getErrorCode() == 201 && parseOptions.getFixControlChars()) {
                return parseInputSource(new InputSource(new FixASCIIControlsReader(new StringReader(str))));
            }
            throw e;
        }
    }

    private static Document parseInputSource(InputSource inputSource) throws XMPException {
        try {
            DocumentBuilder newDocumentBuilder = factory.newDocumentBuilder();
            newDocumentBuilder.setErrorHandler((ErrorHandler) null);
            return newDocumentBuilder.parse(inputSource);
        } catch (SAXException e) {
            throw new XMPException("XML parsing failure", XMPError.BADXML, e);
        } catch (ParserConfigurationException e2) {
            throw new XMPException("XML Parser not correctly configured", 0, e2);
        } catch (IOException e3) {
            throw new XMPException("Error reading the XML-file", XMPError.BADSTREAM, e3);
        }
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object[] findRootNode(org.w3c.dom.Node r7, boolean r8, java.lang.Object[] r9) {
        /*
            org.w3c.dom.NodeList r7 = r7.getChildNodes()
            r0 = 0
            r1 = r0
        L_0x0006:
            int r2 = r7.getLength()
            if (r1 >= r2) goto L_0x0089
            org.w3c.dom.Node r2 = r7.item(r1)
            short r3 = r2.getNodeType()
            r4 = 7
            if (r4 != r3) goto L_0x0030
            java.lang.String r3 = "xpacket"
            r5 = r2
            org.w3c.dom.ProcessingInstruction r5 = (org.w3c.dom.ProcessingInstruction) r5
            java.lang.String r6 = r5.getTarget()
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L_0x0030
            if (r9 == 0) goto L_0x0085
            r2 = 2
            java.lang.String r3 = r5.getData()
            r9[r2] = r3
            goto L_0x0085
        L_0x0030:
            r3 = 3
            short r5 = r2.getNodeType()
            if (r3 == r5) goto L_0x0085
            short r3 = r2.getNodeType()
            if (r4 == r3) goto L_0x0085
            java.lang.String r3 = r2.getNamespaceURI()
            java.lang.String r4 = r2.getLocalName()
            java.lang.String r5 = "xmpmeta"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x0055
            java.lang.String r5 = "xapmeta"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0062
        L_0x0055:
            java.lang.String r5 = "adobe:ns:meta/"
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x0062
            java.lang.Object[] r7 = findRootNode(r2, r0, r9)
            return r7
        L_0x0062:
            if (r8 != 0) goto L_0x007e
            java.lang.String r5 = "RDF"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x007e
            java.lang.String r4 = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x007e
            if (r9 == 0) goto L_0x007d
            r9[r0] = r2
            java.lang.Object r7 = XMP_RDF
            r8 = 1
            r9[r8] = r7
        L_0x007d:
            return r9
        L_0x007e:
            java.lang.Object[] r2 = findRootNode(r2, r8, r9)
            if (r2 == 0) goto L_0x0085
            return r2
        L_0x0085:
            int r1 = r1 + 1
            goto L_0x0006
        L_0x0089:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.xmp.impl.XMPMetaParser.findRootNode(org.w3c.dom.Node, boolean, java.lang.Object[]):java.lang.Object[]");
    }

    private static DocumentBuilderFactory createDocumentBuilderFactory() {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        newInstance.setIgnoringComments(true);
        try {
            newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
            newInstance.setXIncludeAware(false);
            newInstance.setExpandEntityReferences(false);
        } catch (Exception unused) {
        }
        return newInstance;
    }
}
