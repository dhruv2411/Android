package com.facebook.ads.internal.i;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.f.e;
import com.facebook.ads.internal.g.a;
import com.facebook.ads.internal.g.b;
import com.facebook.ads.internal.q.a.g;
import com.facebook.ads.internal.q.a.h;
import com.facebook.ads.internal.q.a.i;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.n;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.q.a.t;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.settings.AdInternalSettings;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class c {
    public static int a = 1303;
    /* access modifiers changed from: private */
    public static final AtomicInteger b = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public static String c;
    private static final g.a d = g.a();
    private final Context e;
    private final b f;

    public c(Context context, boolean z) {
        this.e = context;
        this.f = new b(context);
        a(context, z);
    }

    private static void a(final Context context, boolean z) {
        if (b.compareAndSet(0, 1)) {
            try {
                n.a();
                final SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
                b bVar = new b(context);
                final String str = "AFP;" + bVar.g();
                c = sharedPreferences.getString(str, (String) null);
                FutureTask futureTask = new FutureTask(new Callable<Boolean>() {
                    /* renamed from: a */
                    public Boolean call() {
                        String unused = c.c = c.b(context, context.getPackageName());
                        sharedPreferences.edit().putString(str, c.c).apply();
                        c.b.set(2);
                        return true;
                    }
                });
                Executors.newSingleThreadExecutor().submit(futureTask);
                if (z) {
                    futureTask.get();
                }
            } catch (Exception unused) {
                b.set(0);
            }
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public static String b(Context context, String str) {
        try {
            return i.a(context.getPackageManager().getApplicationInfo(str, 0).sourceDir);
        } catch (Exception e2) {
            Map<String, String> b2 = new c(context, false).b();
            b2.put("subtype", "generic");
            b2.put("subtype_code", String.valueOf(a));
            e.a(e2, context, b2);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x008a A[SYNTHETIC, Splitter:B:39:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008f A[Catch:{ IOException -> 0x0097 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0094 A[Catch:{ IOException -> 0x0097 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a() {
        /*
            r7 = this;
            android.content.Context r0 = r7.e
            r1 = 1
            a((android.content.Context) r0, (boolean) r1)
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x007a, all -> 0x0074 }
            r1.<init>()     // Catch:{ IOException -> 0x007a, all -> 0x0074 }
            android.util.Base64OutputStream r2 = new android.util.Base64OutputStream     // Catch:{ IOException -> 0x006f, all -> 0x006a }
            r3 = 0
            r2.<init>(r1, r3)     // Catch:{ IOException -> 0x006f, all -> 0x006a }
            java.util.zip.DeflaterOutputStream r3 = new java.util.zip.DeflaterOutputStream     // Catch:{ IOException -> 0x0065, all -> 0x0060 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0065, all -> 0x0060 }
            java.util.Map r0 = r7.b()     // Catch:{ IOException -> 0x005e }
            java.lang.String r4 = com.facebook.ads.internal.c.b.b     // Catch:{ IOException -> 0x005e }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IOException -> 0x005e }
            if (r4 == 0) goto L_0x0028
            android.content.Context r4 = r7.e     // Catch:{ IOException -> 0x005e }
            com.facebook.ads.internal.c.b.a(r4)     // Catch:{ IOException -> 0x005e }
        L_0x0028:
            java.lang.String r4 = "IDFA"
            java.lang.String r5 = com.facebook.ads.internal.c.b.b     // Catch:{ IOException -> 0x005e }
            r0.put(r4, r5)     // Catch:{ IOException -> 0x005e }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ IOException -> 0x005e }
            r4.<init>(r0)     // Catch:{ IOException -> 0x005e }
            java.lang.String r0 = r4.toString()     // Catch:{ IOException -> 0x005e }
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x005e }
            r3.write(r0)     // Catch:{ IOException -> 0x005e }
            r3.close()     // Catch:{ IOException -> 0x005e }
            java.lang.String r0 = r1.toString()     // Catch:{ IOException -> 0x005e }
            java.lang.String r4 = "\n"
            java.lang.String r5 = ""
            java.lang.String r0 = r0.replaceAll(r4, r5)     // Catch:{ IOException -> 0x005e }
            if (r3 == 0) goto L_0x0053
            r3.close()     // Catch:{ IOException -> 0x005d }
        L_0x0053:
            if (r2 == 0) goto L_0x0058
            r2.close()     // Catch:{ IOException -> 0x005d }
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            return r0
        L_0x005e:
            r0 = move-exception
            goto L_0x007f
        L_0x0060:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L_0x0088
        L_0x0065:
            r3 = move-exception
            r6 = r3
            r3 = r0
            r0 = r6
            goto L_0x007f
        L_0x006a:
            r2 = move-exception
            r3 = r0
            r0 = r2
            r2 = r3
            goto L_0x0088
        L_0x006f:
            r2 = move-exception
            r3 = r0
            r0 = r2
            r2 = r3
            goto L_0x007f
        L_0x0074:
            r1 = move-exception
            r2 = r0
            r3 = r2
            r0 = r1
            r1 = r3
            goto L_0x0088
        L_0x007a:
            r1 = move-exception
            r2 = r0
            r3 = r2
            r0 = r1
            r1 = r3
        L_0x007f:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch:{ all -> 0x0087 }
            java.lang.String r5 = "Failed to build user token"
            r4.<init>(r5, r0)     // Catch:{ all -> 0x0087 }
            throw r4     // Catch:{ all -> 0x0087 }
        L_0x0087:
            r0 = move-exception
        L_0x0088:
            if (r3 == 0) goto L_0x008d
            r3.close()     // Catch:{ IOException -> 0x0097 }
        L_0x008d:
            if (r2 == 0) goto L_0x0092
            r2.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0092:
            if (r1 == 0) goto L_0x0097
            r1.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0097:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.i.c.a():java.lang.String");
    }

    public Map<String, String> b() {
        a(this.e, false);
        a.a(this.e);
        HashMap hashMap = new HashMap();
        hashMap.put("SDK", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        hashMap.put("SDK_VERSION", "4.99.1");
        hashMap.put("LOCALE", Locale.getDefault().toString());
        float f2 = x.b;
        int i = this.e.getResources().getDisplayMetrics().widthPixels;
        int i2 = this.e.getResources().getDisplayMetrics().heightPixels;
        hashMap.put("DENSITY", String.valueOf(f2));
        hashMap.put("SCREEN_WIDTH", String.valueOf((int) (((float) i) / f2)));
        hashMap.put("SCREEN_HEIGHT", String.valueOf((int) (((float) i2) / f2)));
        hashMap.put("ATTRIBUTION_ID", com.facebook.ads.internal.c.b.a);
        hashMap.put("ID_SOURCE", com.facebook.ads.internal.c.b.d);
        hashMap.put("OS", "Android");
        hashMap.put("OSVERS", b.a);
        hashMap.put("BUNDLE", this.f.f());
        hashMap.put("APPNAME", this.f.d());
        hashMap.put("APPVERS", this.f.g());
        hashMap.put("APPBUILD", String.valueOf(this.f.h()));
        hashMap.put("CARRIER", this.f.c());
        hashMap.put("MAKE", this.f.a());
        hashMap.put("MODEL", this.f.b());
        hashMap.put("ROOTED", String.valueOf(d.d));
        hashMap.put("INSTALLER", this.f.e());
        hashMap.put("SDK_CAPABILITY", com.facebook.ads.internal.q.a.c.b());
        hashMap.put("NETWORK_TYPE", String.valueOf(s.a(this.e).g));
        hashMap.put("SESSION_TIME", t.a(n.b()));
        hashMap.put("SESSION_ID", n.c());
        if (c != null) {
            hashMap.put("AFP", c);
        }
        String a2 = g.a(this.e);
        if (a2 != null) {
            hashMap.put("ASHAS", a2);
        }
        hashMap.put("UNITY", String.valueOf(h.a(this.e)));
        String mediationService = AdInternalSettings.getMediationService();
        if (mediationService != null) {
            hashMap.put("MEDIATION_SERVICE", mediationService);
        }
        hashMap.put("ACCESSIBILITY_ENABLED", String.valueOf(this.f.i()));
        if (this.f.j() != -1) {
            hashMap.put("APP_MIN_SDK_VERSION", String.valueOf(this.f.j()));
        }
        hashMap.put("VALPARAMS", b.a());
        hashMap.put("ANALOG", k.a(a.a()));
        return hashMap;
    }
}
