package com.itextpdf.text.pdf.security;

import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.security.MakeSignature;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRL;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OutputStream;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.ocsp.BasicOCSPResponse;
import org.spongycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.spongycastle.asn1.tsp.MessageImprint;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cert.jcajce.JcaX509CertificateHolder;
import org.spongycastle.cert.ocsp.BasicOCSPResp;
import org.spongycastle.cert.ocsp.CertificateID;
import org.spongycastle.jce.provider.X509CertParser;
import org.spongycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.spongycastle.tsp.TimeStampToken;
import org.spongycastle.tsp.TimeStampTokenInfo;

public class PdfPKCS7 {
    private byte[] RSAdata;
    private BasicOCSPResp basicResp;
    private Collection<Certificate> certs;
    private Collection<CRL> crls;
    private byte[] digest;
    private String digestAlgorithmOid;
    private byte[] digestAttr;
    private String digestEncryptionAlgorithmOid;
    private Set<String> digestalgos;
    private MessageDigest encContDigest;
    private byte[] externalDigest;
    private byte[] externalRSAdata;
    private PdfName filterSubtype;
    private ExternalDigest interfaceDigest;
    private boolean isCades;
    private boolean isTsp;
    private String location;
    private MessageDigest messageDigest;
    private String provider;
    private String reason;
    private Signature sig;
    private byte[] sigAttr;
    private byte[] sigAttrDer;
    private X509Certificate signCert;
    private Collection<Certificate> signCerts;
    private Calendar signDate;
    private String signName;
    private int signerversion = 1;
    private TimeStampToken timeStampToken;
    private boolean verified;
    private boolean verifyResult;
    private int version = 1;

    public PdfPKCS7(PrivateKey privateKey, Certificate[] certificateArr, String str, String str2, ExternalDigest externalDigest2, boolean z) throws InvalidKeyException, NoSuchProviderException, NoSuchAlgorithmException {
        this.provider = str2;
        this.interfaceDigest = externalDigest2;
        this.digestAlgorithmOid = DigestAlgorithms.getAllowedDigests(str);
        if (this.digestAlgorithmOid == null) {
            throw new NoSuchAlgorithmException(MessageLocalization.getComposedMessage("unknown.hash.algorithm.1", str));
        }
        this.signCert = certificateArr[0];
        this.certs = new ArrayList();
        for (Certificate add : certificateArr) {
            this.certs.add(add);
        }
        this.digestalgos = new HashSet();
        this.digestalgos.add(this.digestAlgorithmOid);
        if (privateKey != null) {
            this.digestEncryptionAlgorithmOid = privateKey.getAlgorithm();
            if (this.digestEncryptionAlgorithmOid.equals(SecurityConstants.RSA)) {
                this.digestEncryptionAlgorithmOid = SecurityIDs.ID_RSA;
            } else if (this.digestEncryptionAlgorithmOid.equals(SecurityConstants.DSA)) {
                this.digestEncryptionAlgorithmOid = SecurityIDs.ID_DSA;
            } else {
                throw new NoSuchAlgorithmException(MessageLocalization.getComposedMessage("unknown.key.algorithm.1", this.digestEncryptionAlgorithmOid));
            }
        }
        if (z) {
            this.RSAdata = new byte[0];
            this.messageDigest = DigestAlgorithms.getMessageDigest(getHashAlgorithm(), str2);
        }
        if (privateKey != null) {
            this.sig = initSignature(privateKey);
        }
    }

    public PdfPKCS7(byte[] bArr, byte[] bArr2, String str) {
        try {
            this.provider = str;
            X509CertParser x509CertParser = new X509CertParser();
            x509CertParser.engineInit(new ByteArrayInputStream(bArr2));
            this.certs = x509CertParser.engineReadAll();
            this.signCerts = this.certs;
            this.signCert = (X509Certificate) this.certs.iterator().next();
            this.crls = new ArrayList();
            this.digest = new ASN1InputStream(new ByteArrayInputStream(bArr)).readObject().getOctets();
            if (str == null) {
                this.sig = Signature.getInstance("SHA1withRSA");
            } else {
                this.sig = Signature.getInstance("SHA1withRSA", str);
            }
            this.sig.initVerify(this.signCert.getPublicKey());
            this.digestAlgorithmOid = "1.2.840.10040.4.3";
            this.digestEncryptionAlgorithmOid = "1.3.36.3.3.1.2";
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PdfPKCS7(byte[] r10, com.itextpdf.text.pdf.PdfName r11, java.lang.String r12) {
        /*
            r9 = this;
            r9.<init>()
            r0 = 1
            r9.version = r0
            r9.signerversion = r0
            r9.filterSubtype = r11
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.ETSI_RFC3161
            boolean r1 = r1.equals(r11)
            r9.isTsp = r1
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.ETSI_CADES_DETACHED
            boolean r11 = r1.equals(r11)
            r9.isCades = r11
            r9.provider = r12     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1InputStream r11 = new org.spongycastle.asn1.ASN1InputStream     // Catch:{ Exception -> 0x03e2 }
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x03e2 }
            r1.<init>(r10)     // Catch:{ Exception -> 0x03e2 }
            r11.<init>(r1)     // Catch:{ Exception -> 0x03e2 }
            r1 = 0
            org.spongycastle.asn1.ASN1Primitive r11 = r11.readObject()     // Catch:{ IOException -> 0x03d4 }
            boolean r2 = r11 instanceof org.spongycastle.asn1.ASN1Sequence     // Catch:{ Exception -> 0x03e2 }
            if (r2 != 0) goto L_0x003d
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = "not.a.valid.pkcs.7.object.not.a.sequence"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r11, (java.lang.Object[]) r12)     // Catch:{ Exception -> 0x03e2 }
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            throw r10     // Catch:{ Exception -> 0x03e2 }
        L_0x003d:
            org.spongycastle.asn1.ASN1Sequence r11 = (org.spongycastle.asn1.ASN1Sequence) r11     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r2 = r11.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r2 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r2     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r2 = r2.getId()     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r3 = "1.2.840.113549.1.7.2"
            boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x03e2 }
            if (r2 != 0) goto L_0x005f
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = "not.a.valid.pkcs.7.object.not.signed.data"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r11, (java.lang.Object[]) r12)     // Catch:{ Exception -> 0x03e2 }
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            throw r10     // Catch:{ Exception -> 0x03e2 }
        L_0x005f:
            org.spongycastle.asn1.ASN1Encodable r2 = r11.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1TaggedObject r2 = (org.spongycastle.asn1.ASN1TaggedObject) r2     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Primitive r2 = r2.getObject()     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r2 = (org.spongycastle.asn1.ASN1Sequence) r2     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r3 = r2.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Integer r3 = (org.spongycastle.asn1.ASN1Integer) r3     // Catch:{ Exception -> 0x03e2 }
            java.math.BigInteger r3 = r3.getValue()     // Catch:{ Exception -> 0x03e2 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x03e2 }
            r9.version = r3     // Catch:{ Exception -> 0x03e2 }
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ Exception -> 0x03e2 }
            r3.<init>()     // Catch:{ Exception -> 0x03e2 }
            r9.digestalgos = r3     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r3 = r2.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Set r3 = (org.spongycastle.asn1.ASN1Set) r3     // Catch:{ Exception -> 0x03e2 }
            java.util.Enumeration r3 = r3.getObjects()     // Catch:{ Exception -> 0x03e2 }
        L_0x008c:
            boolean r4 = r3.hasMoreElements()     // Catch:{ Exception -> 0x03e2 }
            if (r4 == 0) goto L_0x00a8
            java.lang.Object r4 = r3.nextElement()     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r4 = (org.spongycastle.asn1.ASN1Sequence) r4     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r4 = r4.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r4     // Catch:{ Exception -> 0x03e2 }
            java.util.Set<java.lang.String> r5 = r9.digestalgos     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r4 = r4.getId()     // Catch:{ Exception -> 0x03e2 }
            r5.add(r4)     // Catch:{ Exception -> 0x03e2 }
            goto L_0x008c
        L_0x00a8:
            r3 = 2
            org.spongycastle.asn1.ASN1Encodable r4 = r2.getObjectAt(r3)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r4 = (org.spongycastle.asn1.ASN1Sequence) r4     // Catch:{ Exception -> 0x03e2 }
            int r5 = r4.size()     // Catch:{ Exception -> 0x03e2 }
            if (r5 <= r0) goto L_0x00c7
            org.spongycastle.asn1.ASN1Encodable r4 = r4.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1TaggedObject r4 = (org.spongycastle.asn1.ASN1TaggedObject) r4     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Primitive r4 = r4.getObject()     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1OctetString r4 = (org.spongycastle.asn1.ASN1OctetString) r4     // Catch:{ Exception -> 0x03e2 }
            byte[] r4 = r4.getOctets()     // Catch:{ Exception -> 0x03e2 }
            r9.RSAdata = r4     // Catch:{ Exception -> 0x03e2 }
        L_0x00c7:
            r4 = 3
            r5 = r4
        L_0x00c9:
            org.spongycastle.asn1.ASN1Encodable r6 = r2.getObjectAt(r5)     // Catch:{ Exception -> 0x03e2 }
            boolean r6 = r6 instanceof org.spongycastle.asn1.ASN1TaggedObject     // Catch:{ Exception -> 0x03e2 }
            if (r6 == 0) goto L_0x00d4
            int r5 = r5 + 1
            goto L_0x00c9
        L_0x00d4:
            org.spongycastle.jce.provider.X509CertParser r6 = new org.spongycastle.jce.provider.X509CertParser     // Catch:{ Exception -> 0x03e2 }
            r6.<init>()     // Catch:{ Exception -> 0x03e2 }
            java.io.ByteArrayInputStream r7 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x03e2 }
            r7.<init>(r10)     // Catch:{ Exception -> 0x03e2 }
            r6.engineInit(r7)     // Catch:{ Exception -> 0x03e2 }
            java.util.Collection r10 = r6.engineReadAll()     // Catch:{ Exception -> 0x03e2 }
            r9.certs = r10     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r10 = r2.getObjectAt(r5)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Set r10 = (org.spongycastle.asn1.ASN1Set) r10     // Catch:{ Exception -> 0x03e2 }
            int r2 = r10.size()     // Catch:{ Exception -> 0x03e2 }
            if (r2 == r0) goto L_0x0101
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = "this.pkcs.7.object.has.multiple.signerinfos.only.one.is.supported.at.this.time"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r11, (java.lang.Object[]) r12)     // Catch:{ Exception -> 0x03e2 }
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            throw r10     // Catch:{ Exception -> 0x03e2 }
        L_0x0101:
            org.spongycastle.asn1.ASN1Encodable r10 = r10.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r10 = (org.spongycastle.asn1.ASN1Sequence) r10     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r2 = r10.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Integer r2 = (org.spongycastle.asn1.ASN1Integer) r2     // Catch:{ Exception -> 0x03e2 }
            java.math.BigInteger r2 = r2.getValue()     // Catch:{ Exception -> 0x03e2 }
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x03e2 }
            r9.signerversion = r2     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r2 = r10.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r2 = (org.spongycastle.asn1.ASN1Sequence) r2     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.jce.X509Principal r5 = new org.spongycastle.jce.X509Principal     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r6 = r2.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Primitive r6 = r6.toASN1Primitive()     // Catch:{ Exception -> 0x03e2 }
            byte[] r6 = r6.getEncoded()     // Catch:{ Exception -> 0x03e2 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r2 = r2.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Integer r2 = (org.spongycastle.asn1.ASN1Integer) r2     // Catch:{ Exception -> 0x03e2 }
            java.math.BigInteger r2 = r2.getValue()     // Catch:{ Exception -> 0x03e2 }
            java.util.Collection<java.security.cert.Certificate> r6 = r9.certs     // Catch:{ Exception -> 0x03e2 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x03e2 }
        L_0x013e:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x03e2 }
            if (r7 == 0) goto L_0x0160
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x03e2 }
            java.security.cert.X509Certificate r7 = (java.security.cert.X509Certificate) r7     // Catch:{ Exception -> 0x03e2 }
            java.security.Principal r8 = r7.getIssuerDN()     // Catch:{ Exception -> 0x03e2 }
            boolean r8 = r8.equals(r5)     // Catch:{ Exception -> 0x03e2 }
            if (r8 == 0) goto L_0x013e
            java.math.BigInteger r8 = r7.getSerialNumber()     // Catch:{ Exception -> 0x03e2 }
            boolean r8 = r2.equals(r8)     // Catch:{ Exception -> 0x03e2 }
            if (r8 == 0) goto L_0x013e
            r9.signCert = r7     // Catch:{ Exception -> 0x03e2 }
        L_0x0160:
            java.security.cert.X509Certificate r6 = r9.signCert     // Catch:{ Exception -> 0x03e2 }
            if (r6 != 0) goto L_0x0192
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = "can.t.find.signing.certificate.with.serial.1"
            java.lang.Object[] r12 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x03e2 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03e2 }
            r0.<init>()     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r3 = r5.getName()     // Catch:{ Exception -> 0x03e2 }
            r0.append(r3)     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r3 = " / "
            r0.append(r3)     // Catch:{ Exception -> 0x03e2 }
            r3 = 16
            java.lang.String r2 = r2.toString(r3)     // Catch:{ Exception -> 0x03e2 }
            r0.append(r2)     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x03e2 }
            r12[r1] = r0     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r11, (java.lang.Object[]) r12)     // Catch:{ Exception -> 0x03e2 }
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            throw r10     // Catch:{ Exception -> 0x03e2 }
        L_0x0192:
            r9.signCertificateChain()     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r2 = r10.getObjectAt(r3)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r2 = (org.spongycastle.asn1.ASN1Sequence) r2     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r2 = r2.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r2 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r2     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r2 = r2.getId()     // Catch:{ Exception -> 0x03e2 }
            r9.digestAlgorithmOid = r2     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r2 = r10.getObjectAt(r4)     // Catch:{ Exception -> 0x03e2 }
            boolean r2 = r2 instanceof org.spongycastle.asn1.ASN1TaggedObject     // Catch:{ Exception -> 0x03e2 }
            if (r2 == 0) goto L_0x02f7
            org.spongycastle.asn1.ASN1Encodable r2 = r10.getObjectAt(r4)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1TaggedObject r2 = (org.spongycastle.asn1.ASN1TaggedObject) r2     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Set r2 = org.spongycastle.asn1.ASN1Set.getInstance(r2, r1)     // Catch:{ Exception -> 0x03e2 }
            byte[] r3 = r2.getEncoded()     // Catch:{ Exception -> 0x03e2 }
            r9.sigAttr = r3     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r3 = "DER"
            byte[] r3 = r2.getEncoded(r3)     // Catch:{ Exception -> 0x03e2 }
            r9.sigAttrDer = r3     // Catch:{ Exception -> 0x03e2 }
            r3 = r1
            r4 = r3
        L_0x01c9:
            int r5 = r2.size()     // Catch:{ Exception -> 0x03e2 }
            if (r3 >= r5) goto L_0x02e3
            org.spongycastle.asn1.ASN1Encodable r5 = r2.getObjectAt(r3)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r5 = (org.spongycastle.asn1.ASN1Sequence) r5     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r6 = r5.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r6 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r6     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r6 = r6.getId()     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r7 = "1.2.840.113549.1.9.4"
            boolean r7 = r6.equals(r7)     // Catch:{ Exception -> 0x03e2 }
            if (r7 == 0) goto L_0x01fb
            org.spongycastle.asn1.ASN1Encodable r5 = r5.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Set r5 = (org.spongycastle.asn1.ASN1Set) r5     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r5 = r5.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1OctetString r5 = (org.spongycastle.asn1.ASN1OctetString) r5     // Catch:{ Exception -> 0x03e2 }
            byte[] r5 = r5.getOctets()     // Catch:{ Exception -> 0x03e2 }
            r9.digestAttr = r5     // Catch:{ Exception -> 0x03e2 }
            goto L_0x02df
        L_0x01fb:
            java.lang.String r7 = "1.2.840.113583.1.1.8"
            boolean r7 = r6.equals(r7)     // Catch:{ Exception -> 0x03e2 }
            if (r7 == 0) goto L_0x023d
            org.spongycastle.asn1.ASN1Encodable r5 = r5.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Set r5 = (org.spongycastle.asn1.ASN1Set) r5     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r5 = r5.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r5 = (org.spongycastle.asn1.ASN1Sequence) r5     // Catch:{ Exception -> 0x03e2 }
            r6 = r1
        L_0x0210:
            int r7 = r5.size()     // Catch:{ Exception -> 0x03e2 }
            if (r6 >= r7) goto L_0x02df
            org.spongycastle.asn1.ASN1Encodable r7 = r5.getObjectAt(r6)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1TaggedObject r7 = (org.spongycastle.asn1.ASN1TaggedObject) r7     // Catch:{ Exception -> 0x03e2 }
            int r8 = r7.getTagNo()     // Catch:{ Exception -> 0x03e2 }
            if (r8 != 0) goto L_0x022b
            org.spongycastle.asn1.ASN1Primitive r8 = r7.getObject()     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r8 = (org.spongycastle.asn1.ASN1Sequence) r8     // Catch:{ Exception -> 0x03e2 }
            r9.findCRL(r8)     // Catch:{ Exception -> 0x03e2 }
        L_0x022b:
            int r8 = r7.getTagNo()     // Catch:{ Exception -> 0x03e2 }
            if (r8 != r0) goto L_0x023a
            org.spongycastle.asn1.ASN1Primitive r7 = r7.getObject()     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r7 = (org.spongycastle.asn1.ASN1Sequence) r7     // Catch:{ Exception -> 0x03e2 }
            r9.findOcsp(r7)     // Catch:{ Exception -> 0x03e2 }
        L_0x023a:
            int r6 = r6 + 1
            goto L_0x0210
        L_0x023d:
            boolean r7 = r9.isCades     // Catch:{ Exception -> 0x03e2 }
            if (r7 == 0) goto L_0x0288
            java.lang.String r7 = "1.2.840.113549.1.9.16.2.12"
            boolean r7 = r6.equals(r7)     // Catch:{ Exception -> 0x03e2 }
            if (r7 == 0) goto L_0x0288
            org.spongycastle.asn1.ASN1Encodable r4 = r5.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Set r4 = (org.spongycastle.asn1.ASN1Set) r4     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r4 = r4.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r4 = (org.spongycastle.asn1.ASN1Sequence) r4     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ess.SigningCertificate r4 = org.spongycastle.asn1.ess.SigningCertificate.getInstance(r4)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ess.ESSCertID[] r4 = r4.getCerts()     // Catch:{ Exception -> 0x03e2 }
            r4 = r4[r1]     // Catch:{ Exception -> 0x03e2 }
            java.security.cert.X509Certificate r5 = r9.signCert     // Catch:{ Exception -> 0x03e2 }
            byte[] r5 = r5.getEncoded()     // Catch:{ Exception -> 0x03e2 }
            com.itextpdf.text.pdf.security.BouncyCastleDigest r6 = new com.itextpdf.text.pdf.security.BouncyCastleDigest     // Catch:{ Exception -> 0x03e2 }
            r6.<init>()     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r7 = "SHA-1"
            java.security.MessageDigest r6 = r6.getMessageDigest(r7)     // Catch:{ Exception -> 0x03e2 }
            byte[] r5 = r6.digest(r5)     // Catch:{ Exception -> 0x03e2 }
            byte[] r4 = r4.getCertHash()     // Catch:{ Exception -> 0x03e2 }
            boolean r4 = java.util.Arrays.equals(r5, r4)     // Catch:{ Exception -> 0x03e2 }
            if (r4 != 0) goto L_0x0286
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = "Signing certificate doesn't match the ESS information."
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            throw r10     // Catch:{ Exception -> 0x03e2 }
        L_0x0286:
            r4 = r0
            goto L_0x02df
        L_0x0288:
            boolean r7 = r9.isCades     // Catch:{ Exception -> 0x03e2 }
            if (r7 == 0) goto L_0x02df
            java.lang.String r7 = "1.2.840.113549.1.9.16.2.47"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x03e2 }
            if (r6 == 0) goto L_0x02df
            org.spongycastle.asn1.ASN1Encodable r4 = r5.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Set r4 = (org.spongycastle.asn1.ASN1Set) r4     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r4 = r4.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r4 = (org.spongycastle.asn1.ASN1Sequence) r4     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ess.SigningCertificateV2 r4 = org.spongycastle.asn1.ess.SigningCertificateV2.getInstance(r4)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ess.ESSCertIDv2[] r4 = r4.getCerts()     // Catch:{ Exception -> 0x03e2 }
            r4 = r4[r1]     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.x509.AlgorithmIdentifier r5 = r4.getHashAlgorithm()     // Catch:{ Exception -> 0x03e2 }
            java.security.cert.X509Certificate r6 = r9.signCert     // Catch:{ Exception -> 0x03e2 }
            byte[] r6 = r6.getEncoded()     // Catch:{ Exception -> 0x03e2 }
            com.itextpdf.text.pdf.security.BouncyCastleDigest r7 = new com.itextpdf.text.pdf.security.BouncyCastleDigest     // Catch:{ Exception -> 0x03e2 }
            r7.<init>()     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r5 = r5.getAlgorithm()     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r5 = r5.getId()     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r5 = com.itextpdf.text.pdf.security.DigestAlgorithms.getDigest(r5)     // Catch:{ Exception -> 0x03e2 }
            java.security.MessageDigest r5 = r7.getMessageDigest(r5)     // Catch:{ Exception -> 0x03e2 }
            byte[] r5 = r5.digest(r6)     // Catch:{ Exception -> 0x03e2 }
            byte[] r4 = r4.getCertHash()     // Catch:{ Exception -> 0x03e2 }
            boolean r4 = java.util.Arrays.equals(r5, r4)     // Catch:{ Exception -> 0x03e2 }
            if (r4 != 0) goto L_0x0286
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = "Signing certificate doesn't match the ESS information."
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            throw r10     // Catch:{ Exception -> 0x03e2 }
        L_0x02df:
            int r3 = r3 + 1
            goto L_0x01c9
        L_0x02e3:
            byte[] r0 = r9.digestAttr     // Catch:{ Exception -> 0x03e2 }
            if (r0 != 0) goto L_0x02f5
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = "authenticated.attribute.is.missing.the.digest"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r11, (java.lang.Object[]) r12)     // Catch:{ Exception -> 0x03e2 }
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            throw r10     // Catch:{ Exception -> 0x03e2 }
        L_0x02f5:
            r0 = 4
            goto L_0x02f9
        L_0x02f7:
            r0 = r4
            r4 = r1
        L_0x02f9:
            boolean r2 = r9.isCades     // Catch:{ Exception -> 0x03e2 }
            if (r2 == 0) goto L_0x0307
            if (r4 != 0) goto L_0x0307
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = "CAdES ESS information missing."
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            throw r10     // Catch:{ Exception -> 0x03e2 }
        L_0x0307:
            int r2 = r0 + 1
            org.spongycastle.asn1.ASN1Encodable r0 = r10.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r0 = (org.spongycastle.asn1.ASN1Sequence) r0     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r0 = r0.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r0 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r0     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r0 = r0.getId()     // Catch:{ Exception -> 0x03e2 }
            r9.digestEncryptionAlgorithmOid = r0     // Catch:{ Exception -> 0x03e2 }
            int r0 = r2 + 1
            org.spongycastle.asn1.ASN1Encodable r2 = r10.getObjectAt(r2)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1OctetString r2 = (org.spongycastle.asn1.ASN1OctetString) r2     // Catch:{ Exception -> 0x03e2 }
            byte[] r2 = r2.getOctets()     // Catch:{ Exception -> 0x03e2 }
            r9.digest = r2     // Catch:{ Exception -> 0x03e2 }
            int r2 = r10.size()     // Catch:{ Exception -> 0x03e2 }
            if (r0 >= r2) goto L_0x0370
            org.spongycastle.asn1.ASN1Encodable r2 = r10.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            boolean r2 = r2 instanceof org.spongycastle.asn1.ASN1TaggedObject     // Catch:{ Exception -> 0x03e2 }
            if (r2 == 0) goto L_0x0370
            org.spongycastle.asn1.ASN1Encodable r10 = r10.getObjectAt(r0)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1TaggedObject r10 = (org.spongycastle.asn1.ASN1TaggedObject) r10     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Set r10 = org.spongycastle.asn1.ASN1Set.getInstance(r10, r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.cms.AttributeTable r0 = new org.spongycastle.asn1.cms.AttributeTable     // Catch:{ Exception -> 0x03e2 }
            r0.<init>(r10)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r10 = org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers.id_aa_signatureTimeStampToken     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.cms.Attribute r10 = r0.get(r10)     // Catch:{ Exception -> 0x03e2 }
            if (r10 == 0) goto L_0x0370
            org.spongycastle.asn1.ASN1Set r0 = r10.getAttrValues()     // Catch:{ Exception -> 0x03e2 }
            int r0 = r0.size()     // Catch:{ Exception -> 0x03e2 }
            if (r0 <= 0) goto L_0x0370
            org.spongycastle.asn1.ASN1Set r10 = r10.getAttrValues()     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Encodable r10 = r10.getObjectAt(r1)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1Sequence r10 = org.spongycastle.asn1.ASN1Sequence.getInstance(r10)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.cms.ContentInfo r0 = new org.spongycastle.asn1.cms.ContentInfo     // Catch:{ Exception -> 0x03e2 }
            r0.<init>(r10)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.tsp.TimeStampToken r10 = new org.spongycastle.tsp.TimeStampToken     // Catch:{ Exception -> 0x03e2 }
            r10.<init>(r0)     // Catch:{ Exception -> 0x03e2 }
            r9.timeStampToken = r10     // Catch:{ Exception -> 0x03e2 }
        L_0x0370:
            boolean r10 = r9.isTsp     // Catch:{ Exception -> 0x03e2 }
            if (r10 == 0) goto L_0x0396
            org.spongycastle.asn1.cms.ContentInfo r10 = new org.spongycastle.asn1.cms.ContentInfo     // Catch:{ Exception -> 0x03e2 }
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.tsp.TimeStampToken r11 = new org.spongycastle.tsp.TimeStampToken     // Catch:{ Exception -> 0x03e2 }
            r11.<init>(r10)     // Catch:{ Exception -> 0x03e2 }
            r9.timeStampToken = r11     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.tsp.TimeStampToken r10 = r9.timeStampToken     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.tsp.TimeStampTokenInfo r10 = r10.getTimeStampInfo()     // Catch:{ Exception -> 0x03e2 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r10 = r10.getMessageImprintAlgOID()     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r10 = r10.getId()     // Catch:{ Exception -> 0x03e2 }
            r11 = 0
            java.security.MessageDigest r10 = com.itextpdf.text.pdf.security.DigestAlgorithms.getMessageDigestFromOid(r10, r11)     // Catch:{ Exception -> 0x03e2 }
            r9.messageDigest = r10     // Catch:{ Exception -> 0x03e2 }
            goto L_0x03d3
        L_0x0396:
            byte[] r10 = r9.RSAdata     // Catch:{ Exception -> 0x03e2 }
            if (r10 != 0) goto L_0x039e
            byte[] r10 = r9.digestAttr     // Catch:{ Exception -> 0x03e2 }
            if (r10 == 0) goto L_0x03c7
        L_0x039e:
            com.itextpdf.text.pdf.PdfName r10 = com.itextpdf.text.pdf.PdfName.ADBE_PKCS7_SHA1     // Catch:{ Exception -> 0x03e2 }
            com.itextpdf.text.pdf.PdfName r11 = r9.getFilterSubtype()     // Catch:{ Exception -> 0x03e2 }
            boolean r10 = r10.equals(r11)     // Catch:{ Exception -> 0x03e2 }
            if (r10 == 0) goto L_0x03b3
            java.lang.String r10 = "SHA1"
            java.security.MessageDigest r10 = com.itextpdf.text.pdf.security.DigestAlgorithms.getMessageDigest(r10, r12)     // Catch:{ Exception -> 0x03e2 }
            r9.messageDigest = r10     // Catch:{ Exception -> 0x03e2 }
            goto L_0x03bd
        L_0x03b3:
            java.lang.String r10 = r9.getHashAlgorithm()     // Catch:{ Exception -> 0x03e2 }
            java.security.MessageDigest r10 = com.itextpdf.text.pdf.security.DigestAlgorithms.getMessageDigest(r10, r12)     // Catch:{ Exception -> 0x03e2 }
            r9.messageDigest = r10     // Catch:{ Exception -> 0x03e2 }
        L_0x03bd:
            java.lang.String r10 = r9.getHashAlgorithm()     // Catch:{ Exception -> 0x03e2 }
            java.security.MessageDigest r10 = com.itextpdf.text.pdf.security.DigestAlgorithms.getMessageDigest(r10, r12)     // Catch:{ Exception -> 0x03e2 }
            r9.encContDigest = r10     // Catch:{ Exception -> 0x03e2 }
        L_0x03c7:
            java.security.cert.X509Certificate r10 = r9.signCert     // Catch:{ Exception -> 0x03e2 }
            java.security.PublicKey r10 = r10.getPublicKey()     // Catch:{ Exception -> 0x03e2 }
            java.security.Signature r10 = r9.initSignature((java.security.PublicKey) r10)     // Catch:{ Exception -> 0x03e2 }
            r9.sig = r10     // Catch:{ Exception -> 0x03e2 }
        L_0x03d3:
            return
        L_0x03d4:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = "can.t.decode.pkcs7signeddata.object"
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x03e2 }
            java.lang.String r11 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r11, (java.lang.Object[]) r12)     // Catch:{ Exception -> 0x03e2 }
            r10.<init>(r11)     // Catch:{ Exception -> 0x03e2 }
            throw r10     // Catch:{ Exception -> 0x03e2 }
        L_0x03e2:
            r10 = move-exception
            com.itextpdf.text.ExceptionConverter r11 = new com.itextpdf.text.ExceptionConverter
            r11.<init>(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.security.PdfPKCS7.<init>(byte[], com.itextpdf.text.pdf.PdfName, java.lang.String):void");
    }

    public String getSignName() {
        return this.signName;
    }

    public void setSignName(String str) {
        this.signName = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public Calendar getSignDate() {
        Calendar timeStampDate = getTimeStampDate();
        return timeStampDate == null ? this.signDate : timeStampDate;
    }

    public void setSignDate(Calendar calendar) {
        this.signDate = calendar;
    }

    public int getVersion() {
        return this.version;
    }

    public int getSigningInfoVersion() {
        return this.signerversion;
    }

    public String getDigestAlgorithmOid() {
        return this.digestAlgorithmOid;
    }

    public String getHashAlgorithm() {
        return DigestAlgorithms.getDigest(this.digestAlgorithmOid);
    }

    public String getDigestEncryptionAlgorithmOid() {
        return this.digestEncryptionAlgorithmOid;
    }

    public String getDigestAlgorithm() {
        return getHashAlgorithm() + "with" + getEncryptionAlgorithm();
    }

    public void setExternalDigest(byte[] bArr, byte[] bArr2, String str) {
        this.externalDigest = bArr;
        this.externalRSAdata = bArr2;
        if (str == null) {
            return;
        }
        if (str.equals(SecurityConstants.RSA)) {
            this.digestEncryptionAlgorithmOid = SecurityIDs.ID_RSA;
        } else if (str.equals(SecurityConstants.DSA)) {
            this.digestEncryptionAlgorithmOid = SecurityIDs.ID_DSA;
        } else if (str.equals("ECDSA")) {
            this.digestEncryptionAlgorithmOid = SecurityIDs.ID_ECDSA;
        } else {
            throw new ExceptionConverter(new NoSuchAlgorithmException(MessageLocalization.getComposedMessage("unknown.key.algorithm.1", str)));
        }
    }

    private Signature initSignature(PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        Signature signature;
        if (this.provider == null) {
            signature = Signature.getInstance(getDigestAlgorithm());
        } else {
            signature = Signature.getInstance(getDigestAlgorithm(), this.provider);
        }
        signature.initSign(privateKey);
        return signature;
    }

    private Signature initSignature(PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        Signature signature;
        String digestAlgorithm = getDigestAlgorithm();
        if (PdfName.ADBE_X509_RSA_SHA1.equals(getFilterSubtype())) {
            digestAlgorithm = "SHA1withRSA";
        }
        if (this.provider == null) {
            signature = Signature.getInstance(digestAlgorithm);
        } else {
            signature = Signature.getInstance(digestAlgorithm, this.provider);
        }
        signature.initVerify(publicKey);
        return signature;
    }

    public void update(byte[] bArr, int i, int i2) throws SignatureException {
        if (this.RSAdata == null && this.digestAttr == null && !this.isTsp) {
            this.sig.update(bArr, i, i2);
        } else {
            this.messageDigest.update(bArr, i, i2);
        }
    }

    public byte[] getEncodedPKCS1() {
        try {
            if (this.externalDigest != null) {
                this.digest = this.externalDigest;
            } else {
                this.digest = this.sig.sign();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
            aSN1OutputStream.writeObject(new DEROctetString(this.digest));
            aSN1OutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public byte[] getEncodedPKCS7() {
        return getEncodedPKCS7((byte[]) null, (TSAClient) null, (byte[]) null, (Collection<byte[]>) null, MakeSignature.CryptoStandard.CMS);
    }

    public byte[] getEncodedPKCS7(byte[] bArr) {
        return getEncodedPKCS7(bArr, (TSAClient) null, (byte[]) null, (Collection<byte[]>) null, MakeSignature.CryptoStandard.CMS);
    }

    public byte[] getEncodedPKCS7(byte[] bArr, TSAClient tSAClient, byte[] bArr2, Collection<byte[]> collection, MakeSignature.CryptoStandard cryptoStandard) {
        byte[] timeStampToken2;
        ASN1EncodableVector buildUnauthenticatedAttributes;
        try {
            if (this.externalDigest != null) {
                this.digest = this.externalDigest;
                if (this.RSAdata != null) {
                    this.RSAdata = this.externalRSAdata;
                }
            } else if (this.externalRSAdata == null || this.RSAdata == null) {
                if (this.RSAdata != null) {
                    this.RSAdata = this.messageDigest.digest();
                    this.sig.update(this.RSAdata);
                }
                this.digest = this.sig.sign();
            } else {
                this.RSAdata = this.externalRSAdata;
                this.sig.update(this.RSAdata);
                this.digest = this.sig.sign();
            }
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            for (String next : this.digestalgos) {
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                aSN1EncodableVector2.add(new ASN1ObjectIdentifier(next));
                aSN1EncodableVector2.add(DERNull.INSTANCE);
                aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
            }
            ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
            aSN1EncodableVector3.add(new ASN1ObjectIdentifier(SecurityIDs.ID_PKCS7_DATA));
            if (this.RSAdata != null) {
                aSN1EncodableVector3.add(new DERTaggedObject(0, new DEROctetString(this.RSAdata)));
            }
            DERSequence dERSequence = new DERSequence(aSN1EncodableVector3);
            ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
            Iterator<Certificate> it = this.certs.iterator();
            while (it.hasNext()) {
                aSN1EncodableVector4.add(new ASN1InputStream(new ByteArrayInputStream(((X509Certificate) it.next()).getEncoded())).readObject());
            }
            DERSet dERSet = new DERSet(aSN1EncodableVector4);
            ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
            aSN1EncodableVector5.add(new ASN1Integer(this.signerversion));
            ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
            aSN1EncodableVector6.add(CertificateInfo.getIssuer(this.signCert.getTBSCertificate()));
            aSN1EncodableVector6.add(new ASN1Integer(this.signCert.getSerialNumber()));
            aSN1EncodableVector5.add(new DERSequence(aSN1EncodableVector6));
            ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
            aSN1EncodableVector7.add(new ASN1ObjectIdentifier(this.digestAlgorithmOid));
            aSN1EncodableVector7.add(new DERNull());
            aSN1EncodableVector5.add(new DERSequence(aSN1EncodableVector7));
            if (bArr != null) {
                aSN1EncodableVector5.add(new DERTaggedObject(false, 0, getAuthenticatedAttributeSet(bArr, bArr2, collection, cryptoStandard)));
            }
            ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
            aSN1EncodableVector8.add(new ASN1ObjectIdentifier(this.digestEncryptionAlgorithmOid));
            aSN1EncodableVector8.add(new DERNull());
            aSN1EncodableVector5.add(new DERSequence(aSN1EncodableVector8));
            aSN1EncodableVector5.add(new DEROctetString(this.digest));
            if (!(tSAClient == null || (timeStampToken2 = tSAClient.getTimeStampToken(tSAClient.getMessageDigest().digest(this.digest))) == null || (buildUnauthenticatedAttributes = buildUnauthenticatedAttributes(timeStampToken2)) == null)) {
                aSN1EncodableVector5.add(new DERTaggedObject(false, 1, new DERSet(buildUnauthenticatedAttributes)));
            }
            ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
            aSN1EncodableVector9.add(new ASN1Integer(this.version));
            aSN1EncodableVector9.add(new DERSet(aSN1EncodableVector));
            aSN1EncodableVector9.add(dERSequence);
            aSN1EncodableVector9.add(new DERTaggedObject(false, 0, dERSet));
            aSN1EncodableVector9.add(new DERSet(new DERSequence(aSN1EncodableVector5)));
            ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
            aSN1EncodableVector10.add(new ASN1ObjectIdentifier(SecurityIDs.ID_PKCS7_SIGNED_DATA));
            aSN1EncodableVector10.add(new DERTaggedObject(0, new DERSequence(aSN1EncodableVector9)));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
            aSN1OutputStream.writeObject(new DERSequence(aSN1EncodableVector10));
            aSN1OutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    private ASN1EncodableVector buildUnauthenticatedAttributes(byte[] bArr) throws IOException {
        if (bArr == null) {
            return null;
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(new ByteArrayInputStream(bArr));
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        aSN1EncodableVector2.add(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.2.14"));
        aSN1EncodableVector2.add(new DERSet(aSN1InputStream.readObject()));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        return aSN1EncodableVector;
    }

    public byte[] getAuthenticatedAttributeBytes(byte[] bArr, byte[] bArr2, Collection<byte[]> collection, MakeSignature.CryptoStandard cryptoStandard) {
        try {
            return getAuthenticatedAttributeSet(bArr, bArr2, collection, cryptoStandard).getEncoded("DER");
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    private DERSet getAuthenticatedAttributeSet(byte[] bArr, byte[] bArr2, Collection<byte[]> collection, MakeSignature.CryptoStandard cryptoStandard) {
        boolean z;
        try {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.add(new ASN1ObjectIdentifier(SecurityIDs.ID_CONTENT_TYPE));
            aSN1EncodableVector2.add(new DERSet(new ASN1ObjectIdentifier(SecurityIDs.ID_PKCS7_DATA)));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
            ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
            aSN1EncodableVector3.add(new ASN1ObjectIdentifier(SecurityIDs.ID_MESSAGE_DIGEST));
            aSN1EncodableVector3.add(new DERSet(new DEROctetString(bArr)));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector3));
            if (collection != null) {
                Iterator<byte[]> it = collection.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next() != null) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            z = false;
            if (bArr2 != null || z) {
                ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
                aSN1EncodableVector4.add(new ASN1ObjectIdentifier(SecurityIDs.ID_ADBE_REVOCATION));
                ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
                if (z) {
                    ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
                    for (byte[] next : collection) {
                        if (next != null) {
                            aSN1EncodableVector6.add(new ASN1InputStream(new ByteArrayInputStream(next)).readObject());
                        }
                    }
                    aSN1EncodableVector5.add(new DERTaggedObject(true, 0, new DERSequence(aSN1EncodableVector6)));
                }
                if (bArr2 != null) {
                    DEROctetString dEROctetString = new DEROctetString(bArr2);
                    ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
                    ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
                    aSN1EncodableVector8.add(OCSPObjectIdentifiers.id_pkix_ocsp_basic);
                    aSN1EncodableVector8.add(dEROctetString);
                    ASN1Enumerated aSN1Enumerated = new ASN1Enumerated(0);
                    ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
                    aSN1EncodableVector9.add(aSN1Enumerated);
                    aSN1EncodableVector9.add(new DERTaggedObject(true, 0, new DERSequence(aSN1EncodableVector8)));
                    aSN1EncodableVector7.add(new DERSequence(aSN1EncodableVector9));
                    aSN1EncodableVector5.add(new DERTaggedObject(true, 1, new DERSequence(aSN1EncodableVector7)));
                }
                aSN1EncodableVector4.add(new DERSet(new DERSequence(aSN1EncodableVector5)));
                aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector4));
            }
            if (cryptoStandard == MakeSignature.CryptoStandard.CADES) {
                ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
                aSN1EncodableVector10.add(new ASN1ObjectIdentifier(SecurityIDs.ID_AA_SIGNING_CERTIFICATE_V2));
                ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
                if (!DigestAlgorithms.getAllowedDigests("SHA-256").equals(this.digestAlgorithmOid)) {
                    aSN1EncodableVector11.add(new AlgorithmIdentifier(new ASN1ObjectIdentifier(this.digestAlgorithmOid)));
                }
                aSN1EncodableVector11.add(new DEROctetString(this.interfaceDigest.getMessageDigest(getHashAlgorithm()).digest(this.signCert.getEncoded())));
                aSN1EncodableVector10.add(new DERSet(new DERSequence(new DERSequence(new DERSequence(aSN1EncodableVector11)))));
                aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector10));
            }
            return new DERSet(aSN1EncodableVector);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public boolean verify() throws GeneralSecurityException {
        boolean z;
        boolean z2;
        if (this.verified) {
            return this.verifyResult;
        }
        if (this.isTsp) {
            this.verifyResult = Arrays.equals(this.messageDigest.digest(), this.timeStampToken.getTimeStampInfo().toASN1Structure().getMessageImprint().getHashedMessage());
        } else if (this.sigAttr == null && this.sigAttrDer == null) {
            if (this.RSAdata != null) {
                this.sig.update(this.messageDigest.digest());
            }
            this.verifyResult = this.sig.verify(this.digest);
        } else {
            byte[] digest2 = this.messageDigest.digest();
            boolean z3 = false;
            if (this.RSAdata != null) {
                z2 = Arrays.equals(digest2, this.RSAdata);
                this.encContDigest.update(this.RSAdata);
                z = Arrays.equals(this.encContDigest.digest(), this.digestAttr);
            } else {
                z2 = true;
                z = false;
            }
            boolean z4 = Arrays.equals(digest2, this.digestAttr) || z;
            boolean z5 = verifySigAttributes(this.sigAttr) || verifySigAttributes(this.sigAttrDer);
            if (z4 && z5 && z2) {
                z3 = true;
            }
            this.verifyResult = z3;
        }
        this.verified = true;
        return this.verifyResult;
    }

    private boolean verifySigAttributes(byte[] bArr) throws GeneralSecurityException {
        Signature initSignature = initSignature(this.signCert.getPublicKey());
        initSignature.update(bArr);
        return initSignature.verify(this.digest);
    }

    public boolean verifyTimestampImprint() throws GeneralSecurityException {
        if (this.timeStampToken == null) {
            return false;
        }
        TimeStampTokenInfo timeStampInfo = this.timeStampToken.getTimeStampInfo();
        MessageImprint messageImprint = timeStampInfo.toASN1Structure().getMessageImprint();
        return Arrays.equals(new BouncyCastleDigest().getMessageDigest(DigestAlgorithms.getDigest(timeStampInfo.getMessageImprintAlgOID().getId())).digest(this.digest), messageImprint.getHashedMessage());
    }

    public Certificate[] getCertificates() {
        return (Certificate[]) this.certs.toArray(new X509Certificate[this.certs.size()]);
    }

    public Certificate[] getSignCertificateChain() {
        return (Certificate[]) this.signCerts.toArray(new X509Certificate[this.signCerts.size()]);
    }

    public X509Certificate getSigningCertificate() {
        return this.signCert;
    }

    private void signCertificateChain() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.signCert);
        ArrayList arrayList2 = new ArrayList(this.certs);
        int i = 0;
        while (i < arrayList2.size()) {
            if (this.signCert.equals(arrayList2.get(i))) {
                arrayList2.remove(i);
                i--;
            }
            i++;
        }
        while (true) {
            boolean z2 = true;
            while (true) {
                if (z2) {
                    X509Certificate x509Certificate = (X509Certificate) arrayList.get(arrayList.size() - 1);
                    int i2 = 0;
                    z = false;
                    while (true) {
                        if (i2 >= arrayList2.size()) {
                            break;
                        }
                        X509Certificate x509Certificate2 = (X509Certificate) arrayList2.get(i2);
                        try {
                            if (this.provider == null) {
                                x509Certificate.verify(x509Certificate2.getPublicKey());
                            } else {
                                x509Certificate.verify(x509Certificate2.getPublicKey(), this.provider);
                            }
                            try {
                                arrayList.add(arrayList2.get(i2));
                                arrayList2.remove(i2);
                            } catch (Exception unused) {
                                z = true;
                            }
                        } catch (Exception unused2) {
                        }
                        i2++;
                    }
                } else {
                    this.signCerts = arrayList;
                    return;
                }
                z2 = z;
            }
        }
    }

    public Collection<CRL> getCRLs() {
        return this.crls;
    }

    private void findCRL(ASN1Sequence aSN1Sequence) {
        try {
            this.crls = new ArrayList();
            for (int i = 0; i < aSN1Sequence.size(); i++) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(aSN1Sequence.getObjectAt(i).toASN1Primitive().getEncoded("DER"));
                this.crls.add((X509CRL) CertificateFactory.getInstance("X.509").generateCRL(byteArrayInputStream));
            }
        } catch (Exception unused) {
        }
    }

    public BasicOCSPResp getOcsp() {
        return this.basicResp;
    }

    public boolean isRevocationValid() {
        if (this.basicResp == null || this.signCerts.size() < 2) {
            return false;
        }
        try {
            CertificateID certID = this.basicResp.getResponses()[0].getCertID();
            return new CertificateID(new JcaDigestCalculatorProviderBuilder().build().get(new AlgorithmIdentifier(certID.getHashAlgOID(), DERNull.INSTANCE)), new JcaX509CertificateHolder(((X509Certificate[]) getSignCertificateChain())[1]), getSigningCertificate().getSerialNumber()).equals(certID);
        } catch (Exception unused) {
            return false;
        }
    }

    private void findOcsp(ASN1Sequence aSN1Sequence) throws IOException {
        boolean z;
        this.basicResp = null;
        do {
            z = false;
            if (!(aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier) || !aSN1Sequence.getObjectAt(0).getId().equals(OCSPObjectIdentifiers.id_pkix_ocsp_basic.getId())) {
                int i = 0;
                while (true) {
                    if (i >= aSN1Sequence.size()) {
                        z = true;
                        continue;
                        break;
                    } else if (aSN1Sequence.getObjectAt(i) instanceof ASN1Sequence) {
                        aSN1Sequence = (ASN1Sequence) aSN1Sequence.getObjectAt(0);
                        continue;
                        break;
                    } else if (aSN1Sequence.getObjectAt(i) instanceof ASN1TaggedObject) {
                        ASN1TaggedObject objectAt = aSN1Sequence.getObjectAt(i);
                        if (objectAt.getObject() instanceof ASN1Sequence) {
                            aSN1Sequence = (ASN1Sequence) objectAt.getObject();
                            continue;
                        } else {
                            return;
                        }
                    } else {
                        i++;
                    }
                }
            } else {
                this.basicResp = new BasicOCSPResp(BasicOCSPResponse.getInstance(new ASN1InputStream(aSN1Sequence.getObjectAt(1).getOctets()).readObject()));
                return;
            }
        } while (!z);
    }

    public boolean isTsp() {
        return this.isTsp;
    }

    public TimeStampToken getTimeStampToken() {
        return this.timeStampToken;
    }

    public Calendar getTimeStampDate() {
        if (this.timeStampToken == null) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(this.timeStampToken.getTimeStampInfo().getGenTime());
        return gregorianCalendar;
    }

    public PdfName getFilterSubtype() {
        return this.filterSubtype;
    }

    public String getEncryptionAlgorithm() {
        String algorithm = EncryptionAlgorithms.getAlgorithm(this.digestEncryptionAlgorithmOid);
        return algorithm == null ? this.digestEncryptionAlgorithmOid : algorithm;
    }
}
