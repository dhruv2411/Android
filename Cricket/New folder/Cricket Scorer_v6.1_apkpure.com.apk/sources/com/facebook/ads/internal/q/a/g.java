package com.facebook.ads.internal.q.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.Nullable;
import com.itextpdf.text.pdf.security.SecurityConstants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;

public class g {
    private static final String a = "g";

    public enum a {
        UNKNOWN(0),
        UNROOTED(1),
        ROOTED(2);
        
        public final int d;

        private a(int i) {
            this.d = i;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001a A[Catch:{ Throwable -> 0x0020 }] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001d A[Catch:{ Throwable -> 0x0020 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.ads.internal.q.a.g.a a() {
        /*
            boolean r0 = c()     // Catch:{ Throwable -> 0x0020 }
            if (r0 != 0) goto L_0x0017
            boolean r0 = b()     // Catch:{ Throwable -> 0x0020 }
            if (r0 != 0) goto L_0x0017
            java.lang.String r0 = "su"
            boolean r0 = a((java.lang.String) r0)     // Catch:{ Throwable -> 0x0020 }
            if (r0 == 0) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r0 = 0
            goto L_0x0018
        L_0x0017:
            r0 = 1
        L_0x0018:
            if (r0 == 0) goto L_0x001d
            com.facebook.ads.internal.q.a.g$a r0 = com.facebook.ads.internal.q.a.g.a.ROOTED     // Catch:{ Throwable -> 0x0020 }
            return r0
        L_0x001d:
            com.facebook.ads.internal.q.a.g$a r0 = com.facebook.ads.internal.q.a.g.a.UNROOTED     // Catch:{ Throwable -> 0x0020 }
            return r0
        L_0x0020:
            com.facebook.ads.internal.q.a.g$a r0 = com.facebook.ads.internal.q.a.g.a.UNKNOWN
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.q.a.g.a():com.facebook.ads.internal.q.a.g$a");
    }

    @Nullable
    public static String a(Context context) {
        try {
            return b(context);
        } catch (Exception unused) {
            return null;
        }
    }

    private static PublicKey a(Signature signature) {
        return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signature.toByteArray())).getPublicKey();
    }

    private static boolean a(String str) {
        File[] listFiles;
        for (String file : System.getenv("PATH").split(":")) {
            File file2 = new File(file);
            if (file2.exists() && file2.isDirectory() && (listFiles = file2.listFiles()) != null) {
                for (File name : listFiles) {
                    if (name.getName().equals(str)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private static String b(Context context) {
        StringBuilder sb = new StringBuilder();
        for (Signature a2 : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
            sb.append(i.a(MessageDigest.getInstance(SecurityConstants.SHA1).digest(a(a2).getEncoded())));
            sb.append(";");
        }
        return sb.toString();
    }

    private static boolean b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean c() {
        return new File("/system/app/Superuser.apk").exists();
    }
}
