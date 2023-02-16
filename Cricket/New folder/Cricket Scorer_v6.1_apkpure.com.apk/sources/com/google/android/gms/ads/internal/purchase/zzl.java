package com.google.android.gms.ads.internal.purchase;

import android.text.TextUtils;
import android.util.Base64;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import com.itextpdf.text.pdf.security.SecurityConstants;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@zzme
public class zzl {
    public static boolean zza(PublicKey publicKey, String str, String str2) {
        String str3;
        try {
            Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initVerify(publicKey);
            instance.update(str.getBytes());
            if (instance.verify(Base64.decode(str2, 0))) {
                return true;
            }
            zzpk.e("Signature verification failed.");
            return false;
        } catch (NoSuchAlgorithmException unused) {
            str3 = "NoSuchAlgorithmException.";
            zzpk.e(str3);
            return false;
        } catch (InvalidKeyException unused2) {
            str3 = "Invalid key specification.";
            zzpk.e(str3);
            return false;
        } catch (SignatureException unused3) {
            str3 = "Signature exception.";
            zzpk.e(str3);
            return false;
        }
    }

    public static PublicKey zzaG(String str) {
        try {
            return KeyFactory.getInstance(SecurityConstants.RSA).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e2) {
            zzpk.e("Invalid key specification.");
            throw new IllegalArgumentException(e2);
        }
    }

    public static boolean zzb(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            return zza(zzaG(str), str2, str3);
        }
        zzpk.e("Purchase verification failed: missing data.");
        return false;
    }
}
