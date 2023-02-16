package com.facebook.ads.internal.q.c;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.facebook.ads.internal.g.b;
import com.facebook.ads.internal.i.c;
import com.facebook.ads.internal.p.a.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.google.android.exoplayer2.DefaultLoadControl;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class d {
    private static String a;
    private static final Set<String> b = new HashSet(1);
    private static final Set<String> c = new HashSet(2);

    static {
        b.add("1ww8E0AYsR2oX5lndk2hwp2Uosk=\n");
        c.add("toZ2GRnRjC9P5VVUdCpOrFH8lfQ=\n");
        c.add("3lKvjNsfmrn+WmfDhvr2iVh/yRs=\n");
        c.add("B08QtE4yLCdli4rptyqAEczXOeA=\n");
        c.add("XZXI6anZbdKf+taURdnyUH5ipgM=\n");
    }

    public static a a(Context context) {
        return a(context, true);
    }

    public static a a(Context context, boolean z) {
        a aVar = new a();
        a(context, aVar, z);
        return aVar;
    }

    private static String a(Context context, String str, String str2) {
        Class<?> cls = Class.forName(str);
        Constructor<?> declaredConstructor = cls.getDeclaredConstructor(new Class[]{Context.class, Class.forName(str2)});
        declaredConstructor.setAccessible(true);
        try {
            return (String) cls.getMethod("getUserAgentString", new Class[0]).invoke(declaredConstructor.newInstance(new Object[]{context, null}), new Object[0]);
        } finally {
            declaredConstructor.setAccessible(false);
        }
    }

    private static void a(Context context, a aVar, boolean z) {
        b bVar = new b(context);
        aVar.c((int) DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
        aVar.b(3);
        if (!com.facebook.ads.internal.c.b.c) {
            aVar.a("X-FB-Pool-Routing-Token", new c(context, true).a());
        }
        aVar.a("user-agent", c(context, z) + " [FBAN/AudienceNetworkForAndroid;FBSN/" + "Android" + ";FBSV/" + b.a + ";FBAB/" + bVar.f() + ";FBAV/" + bVar.g() + ";FBBV/" + bVar.h() + ";FBVS/" + "4.99.1" + ";FBLC/" + Locale.getDefault().toString() + "]");
    }

    public static boolean a() {
        String urlPrefix = AdInternalSettings.getUrlPrefix();
        return !TextUtils.isEmpty(urlPrefix) && urlPrefix.endsWith(".sb");
    }

    public static a b(Context context) {
        return b(context, true);
    }

    public static a b(Context context, boolean z) {
        a aVar = new a();
        a(context, aVar, z);
        if (!a()) {
            aVar.b(c);
            aVar.a(b);
        }
        return aVar;
    }

    @TargetApi(17)
    private static String c(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:30|29|31|32|33|34) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0046 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String c(android.content.Context r2, boolean r3) {
        /*
            if (r2 != 0) goto L_0x0005
            java.lang.String r2 = "Unknown"
            return r2
        L_0x0005:
            if (r3 == 0) goto L_0x000e
            java.lang.String r2 = "http.agent"
            java.lang.String r2 = java.lang.System.getProperty(r2)
            return r2
        L_0x000e:
            java.lang.String r3 = a
            if (r3 == 0) goto L_0x0015
            java.lang.String r2 = a
            return r2
        L_0x0015:
            java.lang.Class<com.facebook.ads.internal.q.c.d> r3 = com.facebook.ads.internal.q.c.d.class
            monitor-enter(r3)
            java.lang.String r0 = a     // Catch:{ all -> 0x0060 }
            if (r0 == 0) goto L_0x0020
            java.lang.String r2 = a     // Catch:{ all -> 0x0060 }
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
            return r2
        L_0x0020:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0060 }
            r1 = 17
            if (r0 < r1) goto L_0x0030
            java.lang.String r0 = c(r2)     // Catch:{ Exception -> 0x0030 }
            a = r0     // Catch:{ Exception -> 0x0030 }
            java.lang.String r0 = a     // Catch:{ Exception -> 0x0030 }
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
            return r0
        L_0x0030:
            java.lang.String r0 = "android.webkit.WebSettings"
            java.lang.String r1 = "android.webkit.WebView"
            java.lang.String r0 = a((android.content.Context) r2, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x003b }
            a = r0     // Catch:{ Exception -> 0x003b }
            goto L_0x005c
        L_0x003b:
            java.lang.String r0 = "android.webkit.WebSettingsClassic"
            java.lang.String r1 = "android.webkit.WebViewClassic"
            java.lang.String r0 = a((android.content.Context) r2, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x0046 }
            a = r0     // Catch:{ Exception -> 0x0046 }
            goto L_0x005c
        L_0x0046:
            android.webkit.WebView r0 = new android.webkit.WebView     // Catch:{ all -> 0x0060 }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x0060 }
            r0.<init>(r2)     // Catch:{ all -> 0x0060 }
            android.webkit.WebSettings r2 = r0.getSettings()     // Catch:{ all -> 0x0060 }
            java.lang.String r2 = r2.getUserAgentString()     // Catch:{ all -> 0x0060 }
            a = r2     // Catch:{ all -> 0x0060 }
            r0.destroy()     // Catch:{ all -> 0x0060 }
        L_0x005c:
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
            java.lang.String r2 = a
            return r2
        L_0x0060:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0060 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.q.c.d.c(android.content.Context, boolean):java.lang.String");
    }
}
