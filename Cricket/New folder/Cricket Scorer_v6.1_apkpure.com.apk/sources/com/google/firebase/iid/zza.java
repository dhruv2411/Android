package com.google.firebase.iid;

import com.itextpdf.text.pdf.security.SecurityConstants;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class zza {
    public static KeyPair zzHg() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance(SecurityConstants.RSA);
            instance.initialize(2048);
            return instance.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}
