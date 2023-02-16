package com.itextpdf.text.pdf.security;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.XmlSignatureAppearance;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.crypto.dsig.spec.XPathFilter2ParameterSpec;
import javax.xml.crypto.dsig.spec.XPathType;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.jcp.xml.dsig.internal.dom.DOMKeyInfoFactory;
import org.apache.jcp.xml.dsig.internal.dom.DOMReference;
import org.apache.jcp.xml.dsig.internal.dom.DOMSignedInfo;
import org.apache.jcp.xml.dsig.internal.dom.DOMUtils;
import org.apache.jcp.xml.dsig.internal.dom.DOMXMLSignature;
import org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI;
import org.apache.xml.security.utils.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MakeXmlSignature {

    private static class EmptyKey implements Key {
        private static EmptyKey instance = new EmptyKey();

        public String getAlgorithm() {
            return null;
        }

        public String getFormat() {
            return null;
        }

        private EmptyKey() {
        }

        public static EmptyKey getInstance() {
            return instance;
        }

        public byte[] getEncoded() {
            return new byte[0];
        }
    }

    public static void signXmlDSig(XmlSignatureAppearance xmlSignatureAppearance, ExternalSignature externalSignature, KeyInfo keyInfo) throws GeneralSecurityException, IOException, DocumentException {
        String str;
        verifyArguments(xmlSignatureAppearance, externalSignature);
        XMLSignatureFactory createSignatureFactory = createSignatureFactory();
        Reference generateContentReference = generateContentReference(createSignatureFactory, xmlSignatureAppearance, (String) null);
        if (externalSignature.getEncryptionAlgorithm().equals(SecurityConstants.RSA)) {
            str = "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
        } else {
            str = externalSignature.getEncryptionAlgorithm().equals(SecurityConstants.DSA) ? "http://www.w3.org/2000/09/xmldsig#dsa-sha1" : null;
        }
        sign(createSignatureFactory, externalSignature, xmlSignatureAppearance.getXmlLocator(), createSignatureFactory.newSignedInfo(createSignatureFactory.newCanonicalizationMethod("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (C14NMethodParameterSpec) null), createSignatureFactory.newSignatureMethod(str, (SignatureMethodParameterSpec) null), Collections.singletonList(generateContentReference)), (XMLObject) null, keyInfo, (String) null);
        xmlSignatureAppearance.close();
    }

    public static void signXmlDSig(XmlSignatureAppearance xmlSignatureAppearance, ExternalSignature externalSignature, Certificate[] certificateArr) throws DocumentException, GeneralSecurityException, IOException {
        signXmlDSig(xmlSignatureAppearance, externalSignature, generateKeyInfo(certificateArr, xmlSignatureAppearance));
    }

    public static void signXmlDSig(XmlSignatureAppearance xmlSignatureAppearance, ExternalSignature externalSignature, PublicKey publicKey) throws GeneralSecurityException, DocumentException, IOException {
        signXmlDSig(xmlSignatureAppearance, externalSignature, generateKeyInfo(publicKey));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void signXades(com.itextpdf.text.pdf.XmlSignatureAppearance r18, com.itextpdf.text.pdf.security.ExternalSignature r19, java.security.cert.Certificate[] r20, boolean r21) throws java.security.GeneralSecurityException, com.itextpdf.text.DocumentException, java.io.IOException {
        /*
            r6 = r18
            verifyArguments(r18, r19)
            java.lang.String r0 = r19.getEncryptionAlgorithm()
            java.lang.String r1 = "RSA"
            boolean r0 = r0.equals(r1)
            r7 = 0
            if (r0 == 0) goto L_0x0016
            java.lang.String r0 = "http://www.w3.org/2000/09/xmldsig#rsa-sha1"
        L_0x0014:
            r8 = r0
            goto L_0x0026
        L_0x0016:
            java.lang.String r0 = r19.getEncryptionAlgorithm()
            java.lang.String r1 = "DSA"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0025
            java.lang.String r0 = "http://www.w3.org/2000/09/xmldsig#dsa-sha1"
            goto L_0x0014
        L_0x0025:
            r8 = r7
        L_0x0026:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Reference-"
            r0.append(r1)
            java.lang.String r1 = getRandomId()
            r0.append(r1)
            java.lang.String r9 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "SignedProperties-"
            r0.append(r1)
            java.lang.String r1 = getRandomId()
            r0.append(r1)
            java.lang.String r10 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Signature-"
            r0.append(r1)
            java.lang.String r1 = getRandomId()
            r0.append(r1)
            java.lang.String r17 = r0.toString()
            javax.xml.crypto.dsig.XMLSignatureFactory r11 = createSignatureFactory()
            r0 = r20
            javax.xml.crypto.dsig.keyinfo.KeyInfo r16 = generateKeyInfo(r0, r6)
            r12 = 2
            r13 = 1
            r14 = 0
            if (r21 == 0) goto L_0x0091
            java.lang.String[] r0 = new java.lang.String[r12]
            java.lang.String r1 = "http://www.w3.org/2000/09/xmldsig#rsa-sha1"
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x0087
            java.lang.String r1 = "urn:oid:1.2.840.113549.1.1.5"
            r0[r14] = r1
            java.lang.String r1 = "RSA (PKCS #1 v1.5) with SHA-1 signature"
            r0[r13] = r1
            goto L_0x008f
        L_0x0087:
            java.lang.String r1 = "urn:oid:1.2.840.10040.4.3"
            r0[r14] = r1
            java.lang.String r1 = "ANSI X9.57 DSA signature generated with SHA-1 hash (DSA x9.30)"
            r0[r13] = r1
        L_0x008f:
            r5 = r0
            goto L_0x0092
        L_0x0091:
            r5 = r7
        L_0x0092:
            r0 = r11
            r1 = r6
            r2 = r17
            r3 = r9
            r4 = r10
            javax.xml.crypto.dsig.XMLObject r15 = generateXadesObject(r0, r1, r2, r3, r4, r5)
            javax.xml.crypto.dsig.Reference r0 = generateContentReference(r11, r6, r9)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "#"
            r1.append(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "http://uri.etsi.org/01903#SignedProperties"
            javax.xml.crypto.dsig.Reference r1 = generateCustomReference(r11, r1, r2, r7)
            javax.xml.crypto.dsig.Reference[] r2 = new javax.xml.crypto.dsig.Reference[r12]
            r2[r14] = r1
            r2[r13] = r0
            java.util.List r0 = java.util.Arrays.asList(r2)
            java.lang.String r1 = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315"
            r2 = r7
            javax.xml.crypto.dsig.spec.C14NMethodParameterSpec r2 = (javax.xml.crypto.dsig.spec.C14NMethodParameterSpec) r2
            javax.xml.crypto.dsig.CanonicalizationMethod r1 = r11.newCanonicalizationMethod(r1, r2)
            javax.xml.crypto.dsig.SignatureMethod r2 = r11.newSignatureMethod(r8, r7)
            javax.xml.crypto.dsig.SignedInfo r0 = r11.newSignedInfo(r1, r2, r0, r7)
            r14 = r0
            org.apache.jcp.xml.dsig.internal.dom.DOMSignedInfo r14 = (org.apache.jcp.xml.dsig.internal.dom.DOMSignedInfo) r14
            com.itextpdf.text.pdf.security.XmlLocator r13 = r18.getXmlLocator()
            r12 = r19
            sign(r11, r12, r13, r14, r15, r16, r17)
            r18.close()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.security.MakeXmlSignature.signXades(com.itextpdf.text.pdf.XmlSignatureAppearance, com.itextpdf.text.pdf.security.ExternalSignature, java.security.cert.Certificate[], boolean):void");
    }

    public static void signXadesBes(XmlSignatureAppearance xmlSignatureAppearance, ExternalSignature externalSignature, Certificate[] certificateArr) throws GeneralSecurityException, DocumentException, IOException {
        signXades(xmlSignatureAppearance, externalSignature, certificateArr, false);
    }

    public static void signXadesEpes(XmlSignatureAppearance xmlSignatureAppearance, ExternalSignature externalSignature, Certificate[] certificateArr) throws GeneralSecurityException, DocumentException, IOException {
        signXades(xmlSignatureAppearance, externalSignature, certificateArr, true);
    }

    private static void verifyArguments(XmlSignatureAppearance xmlSignatureAppearance, ExternalSignature externalSignature) throws DocumentException {
        if (xmlSignatureAppearance.getXmlLocator() == null) {
            throw new DocumentException(MessageLocalization.getComposedMessage("xmllocator.cannot.be.null", new Object[0]));
        } else if (!externalSignature.getHashAlgorithm().equals(SecurityConstants.SHA1)) {
            throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("support.only.sha1.hash.algorithm", new Object[0]));
        } else if (!externalSignature.getEncryptionAlgorithm().equals(SecurityConstants.RSA) && !externalSignature.getEncryptionAlgorithm().equals(SecurityConstants.DSA)) {
            throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("support.only.rsa.and.dsa.algorithms", new Object[0]));
        }
    }

    private static Element findElement(NodeList nodeList, String str) {
        for (int length = nodeList.getLength() - 1; length >= 0; length--) {
            Node item = nodeList.item(length);
            if (item.getNodeType() == 1 && item.getLocalName().equals(str)) {
                return (Element) item;
            }
        }
        return null;
    }

    private static KeyInfo generateKeyInfo(Certificate[] certificateArr, XmlSignatureAppearance xmlSignatureAppearance) {
        Certificate certificate = certificateArr[0];
        xmlSignatureAppearance.setCertificate(certificate);
        DOMKeyInfoFactory dOMKeyInfoFactory = new DOMKeyInfoFactory();
        return dOMKeyInfoFactory.newKeyInfo(Collections.singletonList(dOMKeyInfoFactory.newX509Data(Collections.singletonList(certificate))));
    }

    private static KeyInfo generateKeyInfo(PublicKey publicKey) throws GeneralSecurityException {
        DOMKeyInfoFactory dOMKeyInfoFactory = new DOMKeyInfoFactory();
        return dOMKeyInfoFactory.newKeyInfo(Collections.singletonList(dOMKeyInfoFactory.newKeyValue(publicKey)));
    }

    private static String getRandomId() {
        return UUID.randomUUID().toString().substring(24);
    }

    private static XMLSignatureFactory createSignatureFactory() {
        return XMLSignatureFactory.getInstance("DOM", new XMLDSigRI());
    }

    private static XMLObject generateXadesObject(XMLSignatureFactory xMLSignatureFactory, XmlSignatureAppearance xmlSignatureAppearance, String str, String str2, String str3, String[] strArr) throws GeneralSecurityException {
        MessageDigest instance = MessageDigest.getInstance(SecurityConstants.SHA1);
        Certificate certificate = xmlSignatureAppearance.getCertificate();
        Document document = xmlSignatureAppearance.getXmlLocator().getDocument();
        Element createElementNS = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_QualifyingProperties);
        createElementNS.setAttribute(SecurityConstants.Target, "#" + str);
        Element createElementNS2 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_SignedProperties);
        createElementNS2.setAttribute(SecurityConstants.Id, str3);
        createElementNS2.setIdAttribute(SecurityConstants.Id, true);
        Element createElementNS3 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_SignedSignatureProperties);
        Element createElementNS4 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_SigningTime);
        String format = new SimpleDateFormat(SecurityConstants.SigningTimeFormat).format(xmlSignatureAppearance.getSignDate().getTime());
        createElementNS4.appendChild(document.createTextNode(format.substring(0, format.length() - 2).concat(":").concat(format.substring(format.length() - 2))));
        createElementNS3.appendChild(createElementNS4);
        Element createElementNS5 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_SigningCertificate);
        Element createElementNS6 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_Cert);
        Element createElementNS7 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_CertDigest);
        Element createElementNS8 = document.createElementNS(SecurityConstants.XMLDSIG_URI, SecurityConstants.DigestMethod);
        createElementNS8.setAttribute(SecurityConstants.Algorithm, SecurityConstants.SHA1_URI);
        createElementNS7.appendChild(createElementNS8);
        Element createElementNS9 = document.createElementNS(SecurityConstants.XMLDSIG_URI, SecurityConstants.DigestValue);
        createElementNS9.appendChild(document.createTextNode(Base64.encode(instance.digest(certificate.getEncoded()))));
        createElementNS7.appendChild(createElementNS9);
        createElementNS6.appendChild(createElementNS7);
        if (certificate instanceof X509Certificate) {
            Element createElementNS10 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_IssuerSerial);
            Element createElementNS11 = document.createElementNS(SecurityConstants.XMLDSIG_URI, SecurityConstants.X509IssuerName);
            X509Certificate x509Certificate = (X509Certificate) certificate;
            createElementNS11.appendChild(document.createTextNode(getX509IssuerName(x509Certificate)));
            createElementNS10.appendChild(createElementNS11);
            Element createElementNS12 = document.createElementNS(SecurityConstants.XMLDSIG_URI, SecurityConstants.X509SerialNumber);
            createElementNS12.appendChild(document.createTextNode(getX509SerialNumber(x509Certificate)));
            createElementNS10.appendChild(createElementNS12);
            createElementNS6.appendChild(createElementNS10);
        }
        createElementNS5.appendChild(createElementNS6);
        createElementNS3.appendChild(createElementNS5);
        if (strArr != null) {
            Element createElementNS13 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_SignaturePolicyIdentifier);
            Element createElementNS14 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_SignaturePolicyId);
            Element createElementNS15 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_SigPolicyId);
            Element createElementNS16 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_Identifier);
            createElementNS16.appendChild(document.createTextNode(strArr[0]));
            createElementNS16.setAttribute(SecurityConstants.Qualifier, SecurityConstants.OIDAsURN);
            createElementNS15.appendChild(createElementNS16);
            Element createElementNS17 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_Description);
            createElementNS17.appendChild(document.createTextNode(strArr[1]));
            createElementNS15.appendChild(createElementNS17);
            createElementNS14.appendChild(createElementNS15);
            Element createElementNS18 = document.createElementNS(SecurityConstants.XADES_132_URI, SecurityConstants.XADES_SigPolicyHash);
            Element createElementNS19 = document.createElementNS(SecurityConstants.XMLDSIG_URI, SecurityConstants.DigestMethod);
            createElementNS19.setAttribute(SecurityConstants.Algorithm, SecurityConstants.SHA1_URI);
            createElementNS18.appendChild(createElementNS19);
            Element createElementNS20 = document.createElementNS(SecurityConstants.XMLDSIG_URI, SecurityConstants.DigestValue);
            createElementNS20.appendChild(document.createTextNode(Base64.encode(instance.digest(getByteArrayOfNode(createElementNS15)))));
            createElementNS18.appendChild(createElementNS20);
            createElementNS14.appendChild(createElementNS18);
            createElementNS13.appendChild(createElementNS14);
            createElementNS3.appendChild(createElementNS13);
        }
        createElementNS2.appendChild(createElementNS3);
        Element createElement = document.createElement(SecurityConstants.XADES_SignedDataObjectProperties);
        Element createElement2 = document.createElement(SecurityConstants.XADES_DataObjectFormat);
        createElement2.setAttribute(SecurityConstants.ObjectReference, "#" + str2);
        String description = xmlSignatureAppearance.getDescription();
        if (description != null) {
            Element createElement3 = document.createElement(SecurityConstants.XADES_Description);
            createElement3.appendChild(document.createTextNode(description));
            createElement2.appendChild(createElement3);
        }
        Element createElement4 = document.createElement(SecurityConstants.XADES_MimeType);
        createElement4.appendChild(document.createTextNode(xmlSignatureAppearance.getMimeType()));
        createElement2.appendChild(createElement4);
        String encoding = xmlSignatureAppearance.getXmlLocator().getEncoding();
        if (encoding != null) {
            Element createElement5 = document.createElement(SecurityConstants.XADES_Encoding);
            createElement5.appendChild(document.createTextNode(encoding));
            createElement2.appendChild(createElement5);
        }
        createElement.appendChild(createElement2);
        createElementNS2.appendChild(createElement);
        createElementNS.appendChild(createElementNS2);
        return xMLSignatureFactory.newXMLObject(Collections.singletonList(new DOMStructure(createElementNS)), (String) null, (String) null, (String) null);
    }

    private static String getX509IssuerName(X509Certificate x509Certificate) {
        return x509Certificate.getIssuerX500Principal().toString();
    }

    private static String getX509SerialNumber(X509Certificate x509Certificate) {
        return x509Certificate.getSerialNumber().toString();
    }

    private static Reference generateContentReference(XMLSignatureFactory xMLSignatureFactory, XmlSignatureAppearance xmlSignatureAppearance, String str) throws GeneralSecurityException {
        DigestMethod newDigestMethod = xMLSignatureFactory.newDigestMethod(SecurityConstants.SHA1_URI, (DigestMethodParameterSpec) null);
        ArrayList arrayList = new ArrayList();
        arrayList.add(xMLSignatureFactory.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", (TransformParameterSpec) null));
        XpathConstructor xpathConstructor = xmlSignatureAppearance.getXpathConstructor();
        if (xpathConstructor != null && xpathConstructor.getXpathExpression().length() > 0) {
            arrayList.add(xMLSignatureFactory.newTransform("http://www.w3.org/2002/06/xmldsig-filter2", new XPathFilter2ParameterSpec(Collections.singletonList(new XPathType(xpathConstructor.getXpathExpression(), XPathType.Filter.INTERSECT)))));
        }
        return xMLSignatureFactory.newReference("", newDigestMethod, arrayList, (String) null, str);
    }

    private static Reference generateCustomReference(XMLSignatureFactory xMLSignatureFactory, String str, String str2, String str3) throws GeneralSecurityException {
        return xMLSignatureFactory.newReference(str, xMLSignatureFactory.newDigestMethod(SecurityConstants.SHA1_URI, (DigestMethodParameterSpec) null), (List) null, str2, str3);
    }

    private static void sign(XMLSignatureFactory xMLSignatureFactory, ExternalSignature externalSignature, XmlLocator xmlLocator, DOMSignedInfo dOMSignedInfo, XMLObject xMLObject, KeyInfo keyInfo, String str) throws DocumentException {
        Document document = xmlLocator.getDocument();
        DOMSignContext dOMSignContext = new DOMSignContext(EmptyKey.getInstance(), document.getDocumentElement());
        DOMXMLSignature newXMLSignature = xMLSignatureFactory.newXMLSignature(dOMSignedInfo, keyInfo, xMLObject != null ? Collections.singletonList(xMLObject) : null, str, (String) null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            newXMLSignature.marshal(dOMSignContext.getParent(), dOMSignContext.getNextSibling(), DOMUtils.getSignaturePrefix(dOMSignContext), dOMSignContext);
            Element findElement = findElement(document.getDocumentElement().getChildNodes(), SecurityConstants.Signature);
            if (str != null) {
                findElement.setAttributeNS(SecurityConstants.XMLNS_URI, SecurityConstants.XMLNS_XADES, SecurityConstants.XADES_132_URI);
            }
            List references = dOMSignedInfo.getReferences();
            for (int i = 0; i < references.size(); i++) {
                ((DOMReference) references.get(i)).digest(dOMSignContext);
            }
            dOMSignedInfo.canonicalize(dOMSignContext, byteArrayOutputStream);
            findElement(findElement.getChildNodes(), SecurityConstants.SignatureValue).appendChild(document.createTextNode(Base64.encode(externalSignature.sign(byteArrayOutputStream.toByteArray()))));
            xmlLocator.setDocument(document);
        } catch (Exception e) {
            throw new DocumentException(e);
        }
    }

    private static byte[] getByteArrayOfNode(Node node) {
        try {
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
            newTransformer.transform(new DOMSource(node), streamResult);
            return streamResult.getWriter().toString().getBytes();
        } catch (Exception unused) {
            return new ByteArrayOutputStream().toByteArray();
        }
    }
}
