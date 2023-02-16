package com.facebook.ads.internal.q.d;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.f.e;
import com.facebook.ads.internal.i.c;
import java.util.Map;
import java.util.Set;

public class a {
    public static void a(Context context, String str, int i, Exception exc) {
        if (a(context, str, i, Math.random())) {
            b(context, str, i, exc);
        }
    }

    @VisibleForTesting
    static boolean a(Context context, String str, int i, double d) {
        double k;
        double d2;
        Set<String> i2 = com.facebook.ads.internal.l.a.i(context);
        if (i2.contains(str + ":" + i)) {
            k = (double) (com.facebook.ads.internal.l.a.k(context) * com.facebook.ads.internal.l.a.j(context));
            d2 = 10000.0d;
        } else {
            k = (double) com.facebook.ads.internal.l.a.k(context);
            d2 = 100.0d;
        }
        return d >= 1.0d - (k / d2);
    }

    private static void b(Context context, String str, int i, Exception exc) {
        Map<String, String> b = new c(context, false).b();
        b.put("subtype", str);
        b.put("subtype_code", String.valueOf(i));
        e.a(exc, context, b);
    }
}
