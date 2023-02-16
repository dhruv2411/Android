package com.facebook.ads.internal.q.a;

import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.Map;

public class z {
    private static final String a = "z";

    public static boolean a(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(a, "Invalid Window info in window interactive check, assuming is not a Lockscreen.");
            return false;
        }
        String str = map.get("wfdkg");
        String str2 = map.get("wfswl");
        String str3 = map.get("kgr");
        return str != null && str.equals("1") && str2 != null && str2.equals("1") && str3 != null && str3.equals(PdfBoolean.TRUE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        r4 = r4.get("kgr");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.util.Map<java.lang.String, java.lang.String> r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0043
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L_0x000a
            goto L_0x0043
        L_0x000a:
            java.lang.String r1 = "wfdkg"
            java.lang.Object r1 = r4.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "wfswl"
            java.lang.Object r2 = r4.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r1 == 0) goto L_0x0024
            java.lang.String r3 = "1"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x002e
        L_0x0024:
            if (r2 == 0) goto L_0x002f
            java.lang.String r1 = "1"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x002f
        L_0x002e:
            return r0
        L_0x002f:
            java.lang.String r1 = "kgr"
            java.lang.Object r4 = r4.get(r1)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x0042
            java.lang.String r1 = "true"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x0042
            r0 = 1
        L_0x0042:
            return r0
        L_0x0043:
            java.lang.String r4 = a
            java.lang.String r1 = "Invalid Window info in window interactive check, assuming not obstructed by Keyguard."
            android.util.Log.v(r4, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.q.a.z.b(java.util.Map):boolean");
    }
}
