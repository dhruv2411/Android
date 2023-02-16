package com.itextpdf.text.pdf.security;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;

public class PrivateKeySignature implements ExternalSignature {
    private String encryptionAlgorithm;
    private String hashAlgorithm;
    private PrivateKey pk;
    private String provider;

    public PrivateKeySignature(PrivateKey privateKey, String str, String str2) {
        this.pk = privateKey;
        this.provider = str2;
        this.hashAlgorithm = DigestAlgorithms.getDigest(DigestAlgorithms.getAllowedDigests(str));
        this.encryptionAlgorithm = privateKey.getAlgorithm();
        if (this.encryptionAlgorithm.startsWith("EC")) {
            this.encryptionAlgorithm = "ECDSA";
        }
    }

    public String getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public String getEncryptionAlgorithm() {
        return this.encryptionAlgorithm;
    }

    public byte[] sign(byte[] bArr) throws GeneralSecurityException {
        Signature signature;
        String str = this.hashAlgorithm + "with" + this.encryptionAlgorithm;
        if (this.provider == null) {
            signature = Signature.getInstance(str);
        } else {
            signature = Signature.getInstance(str, this.provider);
        }
        signature.initSign(this.pk);
        signature.update(bArr);
        return signature.sign();
    }
}
