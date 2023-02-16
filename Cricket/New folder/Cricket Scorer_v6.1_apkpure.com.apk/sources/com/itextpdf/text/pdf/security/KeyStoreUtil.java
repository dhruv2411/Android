package com.itextpdf.text.pdf.security;

import java.security.KeyStore;

public class KeyStoreUtil {
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004a A[SYNTHETIC, Splitter:B:24:0x004a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.security.KeyStore loadCacertsKeyStore(java.lang.String r3) {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "java.home"
            java.lang.String r1 = java.lang.System.getProperty(r1)
            java.lang.String r2 = "lib"
            r0.<init>(r1, r2)
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "security"
            r1.<init>(r0, r2)
            java.io.File r0 = new java.io.File
            java.lang.String r2 = "cacerts"
            r0.<init>(r1, r2)
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0041 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0041 }
            if (r3 != 0) goto L_0x0030
            java.lang.String r3 = "JKS"
            java.security.KeyStore r3 = java.security.KeyStore.getInstance(r3)     // Catch:{ Exception -> 0x002d, all -> 0x002a }
            goto L_0x0036
        L_0x002a:
            r3 = move-exception
            r1 = r2
            goto L_0x0048
        L_0x002d:
            r3 = move-exception
            r1 = r2
            goto L_0x0042
        L_0x0030:
            java.lang.String r0 = "JKS"
            java.security.KeyStore r3 = java.security.KeyStore.getInstance(r0, r3)     // Catch:{ Exception -> 0x002d, all -> 0x002a }
        L_0x0036:
            r3.load(r2, r1)     // Catch:{ Exception -> 0x002d, all -> 0x002a }
            if (r2 == 0) goto L_0x003e
            r2.close()     // Catch:{ Exception -> 0x003e }
        L_0x003e:
            return r3
        L_0x003f:
            r3 = move-exception
            goto L_0x0048
        L_0x0041:
            r3 = move-exception
        L_0x0042:
            com.itextpdf.text.ExceptionConverter r0 = new com.itextpdf.text.ExceptionConverter     // Catch:{ all -> 0x003f }
            r0.<init>(r3)     // Catch:{ all -> 0x003f }
            throw r0     // Catch:{ all -> 0x003f }
        L_0x0048:
            if (r1 == 0) goto L_0x004d
            r1.close()     // Catch:{ Exception -> 0x004d }
        L_0x004d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.security.KeyStoreUtil.loadCacertsKeyStore(java.lang.String):java.security.KeyStore");
    }

    public static KeyStore loadCacertsKeyStore() {
        return loadCacertsKeyStore((String) null);
    }
}
