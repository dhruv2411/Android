package com.facebook.ads.internal.q.a;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.itextpdf.text.pdf.PdfBoolean;

public class b {
    private static boolean a = false;
    private static boolean b = false;

    @Nullable
    public static synchronized String a(String str) {
        synchronized (b.class) {
            if (!a()) {
                return null;
            }
            String property = System.getProperty("fb.e2e." + str);
            return property;
        }
    }

    public static synchronized boolean a() {
        boolean z;
        synchronized (b.class) {
            if (!b) {
                a = PdfBoolean.TRUE.equals(System.getProperty("fb.running_e2e"));
                b = true;
            }
            z = a;
        }
        return z;
    }

    public static synchronized boolean b(String str) {
        boolean z;
        synchronized (b.class) {
            z = !TextUtils.isEmpty(a(str));
        }
        return z;
    }
}
