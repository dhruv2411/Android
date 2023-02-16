package com.itextpdf.text.pdf.security;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.cert.ocsp.BasicOCSPResp;
import org.spongycastle.cert.ocsp.CertificateStatus;
import org.spongycastle.cert.ocsp.OCSPException;
import org.spongycastle.cert.ocsp.SingleResp;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.bc.BcDigestCalculatorProvider;
import org.spongycastle.operator.jcajce.JcaContentVerifierProviderBuilder;

public class OCSPVerifier extends RootStoreVerifier {
    protected static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) OCSPVerifier.class);
    protected static final String id_kp_OCSPSigning = "1.3.6.1.5.5.7.3.9";
    protected List<BasicOCSPResp> ocsps;

    public OCSPVerifier(CertificateVerifier certificateVerifier, List<BasicOCSPResp> list) {
        super(certificateVerifier);
        this.ocsps = list;
    }

    public List<VerificationOK> verify(X509Certificate x509Certificate, X509Certificate x509Certificate2, Date date) throws GeneralSecurityException, IOException {
        int i;
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        if (this.ocsps != null) {
            i = 0;
            for (BasicOCSPResp verify : this.ocsps) {
                if (verify(verify, x509Certificate, x509Certificate2, date)) {
                    i++;
                }
            }
        } else {
            i = 0;
        }
        if (this.onlineCheckingAllowed && i == 0 && verify(getOcspResponse(x509Certificate, x509Certificate2), x509Certificate, x509Certificate2, date)) {
            i++;
            z = true;
        }
        LOGGER.info("Valid OCSPs found: " + i);
        if (i > 0) {
            Class<?> cls = getClass();
            StringBuilder sb = new StringBuilder();
            sb.append("Valid OCSPs Found: ");
            sb.append(i);
            sb.append(z ? " (online)" : "");
            arrayList.add(new VerificationOK(x509Certificate, cls, sb.toString()));
        }
        if (this.verifier != null) {
            arrayList.addAll(this.verifier.verify(x509Certificate, x509Certificate2, date));
        }
        return arrayList;
    }

    public boolean verify(BasicOCSPResp basicOCSPResp, X509Certificate x509Certificate, X509Certificate x509Certificate2, Date date) throws GeneralSecurityException, IOException {
        if (basicOCSPResp == null) {
            return false;
        }
        SingleResp[] responses = basicOCSPResp.getResponses();
        X509Certificate x509Certificate3 = x509Certificate2;
        for (int i = 0; i < responses.length; i++) {
            if (x509Certificate.getSerialNumber().equals(responses[i].getCertID().getSerialNumber())) {
                if (x509Certificate3 == null) {
                    x509Certificate3 = x509Certificate;
                }
                try {
                    if (!responses[i].getCertID().matchesIssuer(new X509CertificateHolder(x509Certificate3.getEncoded()), new BcDigestCalculatorProvider())) {
                        LOGGER.info("OCSP: Issuers doesn't match.");
                    } else {
                        Date nextUpdate = responses[i].getNextUpdate();
                        if (nextUpdate == null) {
                            nextUpdate = new Date(responses[i].getThisUpdate().getTime() + 180000);
                            LOGGER.info(String.format("No 'next update' for OCSP Response; assuming %s", new Object[]{nextUpdate}));
                        }
                        if (date.after(nextUpdate)) {
                            LOGGER.info(String.format("OCSP no longer valid: %s after %s", new Object[]{date, nextUpdate}));
                        } else if (responses[i].getCertStatus() == CertificateStatus.GOOD) {
                            isValidResponse(basicOCSPResp, x509Certificate3);
                            return true;
                        }
                    }
                } catch (OCSPException unused) {
                    continue;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void isValidResponse(org.spongycastle.cert.ocsp.BasicOCSPResp r9, java.security.cert.X509Certificate r10) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.isSignatureValid(r9, r10)
            r1 = 0
            if (r0 == 0) goto L_0x0009
            r0 = r10
            goto L_0x000a
        L_0x0009:
            r0 = r1
        L_0x000a:
            if (r0 != 0) goto L_0x0082
            org.spongycastle.cert.X509CertificateHolder[] r2 = r9.getCerts()
            if (r2 == 0) goto L_0x0048
            org.spongycastle.cert.X509CertificateHolder[] r2 = r9.getCerts()
            int r3 = r2.length
            r4 = 0
        L_0x0018:
            if (r4 >= r3) goto L_0x003e
            r5 = r2[r4]
            org.spongycastle.cert.jcajce.JcaX509CertificateConverter r6 = new org.spongycastle.cert.jcajce.JcaX509CertificateConverter     // Catch:{ Exception -> 0x003b }
            r6.<init>()     // Catch:{ Exception -> 0x003b }
            java.security.cert.X509Certificate r5 = r6.getCertificate(r5)     // Catch:{ Exception -> 0x003b }
            java.util.List r6 = r5.getExtendedKeyUsage()     // Catch:{  }
            if (r6 == 0) goto L_0x003b
            java.lang.String r7 = "1.3.6.1.5.5.7.3.9"
            boolean r6 = r6.contains(r7)     // Catch:{  }
            if (r6 == 0) goto L_0x003b
            boolean r6 = r8.isSignatureValid(r9, r5)     // Catch:{  }
            if (r6 == 0) goto L_0x003b
            r0 = r5
            goto L_0x003e
        L_0x003b:
            int r4 = r4 + 1
            goto L_0x0018
        L_0x003e:
            if (r0 != 0) goto L_0x0082
            com.itextpdf.text.pdf.security.VerificationException r9 = new com.itextpdf.text.pdf.security.VerificationException
            java.lang.String r0 = "OCSP response could not be verified"
            r9.<init>(r10, r0)
            throw r9
        L_0x0048:
            java.security.KeyStore r2 = r8.rootStore
            if (r2 == 0) goto L_0x0078
            java.security.KeyStore r2 = r8.rootStore     // Catch:{ KeyStoreException -> 0x0077 }
            java.util.Enumeration r2 = r2.aliases()     // Catch:{ KeyStoreException -> 0x0077 }
        L_0x0052:
            boolean r3 = r2.hasMoreElements()     // Catch:{ KeyStoreException -> 0x0077 }
            if (r3 == 0) goto L_0x0078
            java.lang.Object r3 = r2.nextElement()     // Catch:{ KeyStoreException -> 0x0077 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ KeyStoreException -> 0x0077 }
            java.security.KeyStore r4 = r8.rootStore     // Catch:{ GeneralSecurityException -> 0x0052 }
            boolean r4 = r4.isCertificateEntry(r3)     // Catch:{ GeneralSecurityException -> 0x0052 }
            if (r4 != 0) goto L_0x0067
            goto L_0x0052
        L_0x0067:
            java.security.KeyStore r4 = r8.rootStore     // Catch:{ GeneralSecurityException -> 0x0052 }
            java.security.cert.Certificate r3 = r4.getCertificate(r3)     // Catch:{ GeneralSecurityException -> 0x0052 }
            java.security.cert.X509Certificate r3 = (java.security.cert.X509Certificate) r3     // Catch:{ GeneralSecurityException -> 0x0052 }
            boolean r4 = r8.isSignatureValid(r9, r3)     // Catch:{ GeneralSecurityException -> 0x0052 }
            if (r4 == 0) goto L_0x0052
            r0 = r3
            goto L_0x0078
        L_0x0077:
            r0 = r1
        L_0x0078:
            if (r0 != 0) goto L_0x0082
            com.itextpdf.text.pdf.security.VerificationException r9 = new com.itextpdf.text.pdf.security.VerificationException
            java.lang.String r0 = "OCSP response could not be verified"
            r9.<init>(r10, r0)
            throw r9
        L_0x0082:
            java.security.PublicKey r9 = r10.getPublicKey()
            r0.verify(r9)
            org.spongycastle.asn1.ASN1ObjectIdentifier r9 = org.spongycastle.asn1.ocsp.OCSPObjectIdentifiers.id_pkix_ocsp_nocheck
            java.lang.String r9 = r9.getId()
            byte[] r9 = r0.getExtensionValue(r9)
            if (r9 != 0) goto L_0x00bb
            java.security.cert.CRL r9 = com.itextpdf.text.pdf.security.CertificateUtil.getCRL((java.security.cert.X509Certificate) r0)     // Catch:{ Exception -> 0x009a }
            goto L_0x009b
        L_0x009a:
            r9 = r1
        L_0x009b:
            if (r9 == 0) goto L_0x00bb
            boolean r2 = r9 instanceof java.security.cert.X509CRL
            if (r2 == 0) goto L_0x00bb
            com.itextpdf.text.pdf.security.CRLVerifier r2 = new com.itextpdf.text.pdf.security.CRLVerifier
            r2.<init>(r1, r1)
            java.security.KeyStore r1 = r8.rootStore
            r2.setRootStore(r1)
            boolean r1 = r8.onlineCheckingAllowed
            r2.setOnlineCheckingAllowed(r1)
            java.security.cert.X509CRL r9 = (java.security.cert.X509CRL) r9
            java.util.Date r1 = new java.util.Date
            r1.<init>()
            r2.verify(r9, r0, r10, r1)
            return
        L_0x00bb:
            r0.checkValidity()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.security.OCSPVerifier.isValidResponse(org.spongycastle.cert.ocsp.BasicOCSPResp, java.security.cert.X509Certificate):void");
    }

    @Deprecated
    public boolean verifyResponse(BasicOCSPResp basicOCSPResp, X509Certificate x509Certificate) {
        try {
            isValidResponse(basicOCSPResp, x509Certificate);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isSignatureValid(BasicOCSPResp basicOCSPResp, Certificate certificate) {
        try {
            return basicOCSPResp.isSignatureValid(new JcaContentVerifierProviderBuilder().setProvider("BC").build(certificate.getPublicKey()));
        } catch (OperatorCreationException unused) {
            return false;
        } catch (OCSPException unused2) {
            return false;
        }
    }

    public BasicOCSPResp getOcspResponse(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        BasicOCSPResp basicOCSPResp;
        if ((x509Certificate == null && x509Certificate2 == null) || (basicOCSPResp = new OcspClientBouncyCastle().getBasicOCSPResp(x509Certificate, x509Certificate2, (String) null)) == null) {
            return null;
        }
        SingleResp[] responses = basicOCSPResp.getResponses();
        for (SingleResp certStatus : responses) {
            if (certStatus.getCertStatus() == CertificateStatus.GOOD) {
                return basicOCSPResp;
            }
        }
        return null;
    }
}
