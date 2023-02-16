package com.facebook.ads.internal.q.c;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.facebook.ads.internal.p.a.n;
import com.facebook.ads.internal.q.a.k;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class e extends AsyncTask<String, Void, f> {
    private static final String a = "e";
    private static final Set<String> b = new HashSet();
    private Context c;
    private Map<String, String> d;
    private Map<String, String> e;
    private n f;
    private a g;

    public interface a {
        void a();

        void a(f fVar);
    }

    static {
        b.add("#");
        b.add("null");
    }

    public e(Context context) {
        this(context, (Map<String, String>) null, (Map<String, String>) null);
    }

    public e(Context context, Map<String, String> map) {
        this(context, map, (Map<String, String>) null);
    }

    public e(Context context, Map<String, String> map, Map<String, String> map2) {
        this.c = context;
        HashMap hashMap = null;
        this.d = map != null ? new HashMap(map) : null;
        this.e = map2 != null ? new HashMap(map2) : hashMap;
    }

    private String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return str;
        }
        String str4 = str.contains("?") ? "&" : "?";
        return str + str4 + str2 + "=" + URLEncoder.encode(str3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.lang.String r6) {
        /*
            r5 = this;
            android.content.Context r0 = r5.c
            com.facebook.ads.internal.p.a.a r0 = com.facebook.ads.internal.q.c.d.a(r0)
            r1 = 0
            java.util.Map<java.lang.String, java.lang.String> r2 = r5.e     // Catch:{ Exception -> 0x003b }
            if (r2 == 0) goto L_0x0025
            java.util.Map<java.lang.String, java.lang.String> r2 = r5.e     // Catch:{ Exception -> 0x003b }
            int r2 = r2.size()     // Catch:{ Exception -> 0x003b }
            if (r2 != 0) goto L_0x0014
            goto L_0x0025
        L_0x0014:
            com.facebook.ads.internal.p.a.p r2 = new com.facebook.ads.internal.p.a.p     // Catch:{ Exception -> 0x003b }
            r2.<init>()     // Catch:{ Exception -> 0x003b }
            java.util.Map<java.lang.String, java.lang.String> r3 = r5.e     // Catch:{ Exception -> 0x003b }
            r2.a((java.util.Map<? extends java.lang.String, ? extends java.lang.String>) r3)     // Catch:{ Exception -> 0x003b }
            com.facebook.ads.internal.p.a.n r0 = r0.b(r6, r2)     // Catch:{ Exception -> 0x003b }
        L_0x0022:
            r5.f = r0     // Catch:{ Exception -> 0x003b }
            goto L_0x002b
        L_0x0025:
            r2 = 0
            com.facebook.ads.internal.p.a.n r0 = r0.a((java.lang.String) r6, (com.facebook.ads.internal.p.a.p) r2)     // Catch:{ Exception -> 0x003b }
            goto L_0x0022
        L_0x002b:
            com.facebook.ads.internal.p.a.n r0 = r5.f     // Catch:{ Exception -> 0x003b }
            if (r0 == 0) goto L_0x003a
            com.facebook.ads.internal.p.a.n r0 = r5.f     // Catch:{ Exception -> 0x003b }
            int r0 = r0.a()     // Catch:{ Exception -> 0x003b }
            r6 = 200(0xc8, float:2.8E-43)
            if (r0 != r6) goto L_0x003a
            r1 = 1
        L_0x003a:
            return r1
        L_0x003b:
            r0 = move-exception
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error opening url: "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            android.util.Log.e(r2, r6, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.q.c.e.a(java.lang.String):boolean");
    }

    private String b(String str) {
        try {
            return a(str, "analog", k.a(com.facebook.ads.internal.g.a.a()));
        } catch (Exception unused) {
            return str;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public f doInBackground(String... strArr) {
        String str = strArr[0];
        if (TextUtils.isEmpty(str) || b.contains(str)) {
            return null;
        }
        String b2 = b(str);
        if (this.d != null && !this.d.isEmpty()) {
            for (Map.Entry next : this.d.entrySet()) {
                b2 = a(b2, (String) next.getKey(), (String) next.getValue());
            }
        }
        int i = 1;
        while (true) {
            int i2 = i + 1;
            if (i > 2) {
                break;
            } else if (a(b2)) {
                return new f(this.f);
            } else {
                i = i2;
            }
        }
        return null;
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(f fVar) {
        if (this.g != null) {
            this.g.a(fVar);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        if (this.g != null) {
            this.g.a();
        }
    }
}
