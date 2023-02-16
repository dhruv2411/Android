package com.itextpdf.text.pdf.security;

import java.security.GeneralSecurityException;

public class VerificationException extends GeneralSecurityException {
    private static final long serialVersionUID = 2978604513926438256L;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VerificationException(java.security.cert.Certificate r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "Certificate %s failed: %s"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            if (r4 != 0) goto L_0x000a
            java.lang.String r4 = "Unknown"
            goto L_0x0014
        L_0x000a:
            java.security.cert.X509Certificate r4 = (java.security.cert.X509Certificate) r4
            java.security.Principal r4 = r4.getSubjectDN()
            java.lang.String r4 = r4.getName()
        L_0x0014:
            r2 = 0
            r1[r2] = r4
            r4 = 1
            r1[r4] = r5
            java.lang.String r4 = java.lang.String.format(r0, r1)
            r3.<init>(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.security.VerificationException.<init>(java.security.cert.Certificate, java.lang.String):void");
    }
}
