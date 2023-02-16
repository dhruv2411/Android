package com.facebook.ads.internal.h;

import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class d {
    private static final String c = "d";
    private static final AdPlacementType d = AdPlacementType.UNKNOWN;
    public int a = -1;
    public int b = -1;
    private final long e = System.currentTimeMillis();
    private AdPlacementType f = d;
    private int g = 1;
    private int h = 0;
    private int i = 0;
    private int j = 20;
    private int k = 0;
    private int l = 1000;
    private int m = 10000;
    private int n = 200;
    private int o = 3600;
    private boolean p = false;
    private List<b> q = null;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private d(java.util.Map<java.lang.String, java.lang.String> r11) {
        /*
            r10 = this;
            r10.<init>()
            r0 = -1
            r10.a = r0
            r10.b = r0
            com.facebook.ads.internal.protocol.AdPlacementType r1 = d
            r10.f = r1
            r1 = 1
            r10.g = r1
            r2 = 0
            r10.h = r2
            r10.i = r2
            r3 = 20
            r10.j = r3
            r10.k = r2
            r3 = 1000(0x3e8, float:1.401E-42)
            r10.l = r3
            r3 = 10000(0x2710, float:1.4013E-41)
            r10.m = r3
            r3 = 200(0xc8, float:2.8E-43)
            r10.n = r3
            r4 = 3600(0xe10, float:5.045E-42)
            r10.o = r4
            r10.p = r2
            r4 = 0
            r10.q = r4
            long r4 = java.lang.System.currentTimeMillis()
            r10.e = r4
            java.util.Set r11 = r11.entrySet()
            java.util.Iterator r11 = r11.iterator()
        L_0x003d:
            boolean r4 = r11.hasNext()
            if (r4 == 0) goto L_0x0237
            java.lang.Object r4 = r11.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r4.getKey()
            java.lang.String r5 = (java.lang.String) r5
            int r6 = r5.hashCode()
            switch(r6) {
                case -1899431321: goto L_0x00e4;
                case -1561601017: goto L_0x00da;
                case -856794442: goto L_0x00cf;
                case -726276175: goto L_0x00c4;
                case -634541425: goto L_0x00ba;
                case -553208868: goto L_0x00b0;
                case 3575610: goto L_0x00a6;
                case 700812481: goto L_0x009c;
                case 858630459: goto L_0x0092;
                case 986744879: goto L_0x0086;
                case 1085444827: goto L_0x007b;
                case 1183549815: goto L_0x006f;
                case 1503616961: goto L_0x0063;
                case 2002133996: goto L_0x0058;
                default: goto L_0x0056;
            }
        L_0x0056:
            goto L_0x00ef
        L_0x0058:
            java.lang.String r6 = "placement_width"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 7
            goto L_0x00f0
        L_0x0063:
            java.lang.String r6 = "placement_height"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 8
            goto L_0x00f0
        L_0x006f:
            java.lang.String r6 = "viewability_check_initial_delay"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 9
            goto L_0x00f0
        L_0x007b:
            java.lang.String r6 = "refresh"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 3
            goto L_0x00f0
        L_0x0086:
            java.lang.String r6 = "video_time_polling_interval"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 13
            goto L_0x00f0
        L_0x0092:
            java.lang.String r6 = "viewability_check_ticker"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 2
            goto L_0x00f0
        L_0x009c:
            java.lang.String r6 = "min_viewability_percentage"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = r1
            goto L_0x00f0
        L_0x00a6:
            java.lang.String r6 = "type"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = r2
            goto L_0x00f0
        L_0x00b0:
            java.lang.String r6 = "cacheable"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 6
            goto L_0x00f0
        L_0x00ba:
            java.lang.String r6 = "invalidation_duration_in_seconds"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 5
            goto L_0x00f0
        L_0x00c4:
            java.lang.String r6 = "request_timeout"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 11
            goto L_0x00f0
        L_0x00cf:
            java.lang.String r6 = "viewability_check_interval"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 10
            goto L_0x00f0
        L_0x00da:
            java.lang.String r6 = "refresh_threshold"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 4
            goto L_0x00f0
        L_0x00e4:
            java.lang.String r6 = "conv_tracking_data"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00ef
            r5 = 12
            goto L_0x00f0
        L_0x00ef:
            r5 = r0
        L_0x00f0:
            switch(r5) {
                case 0: goto L_0x0229;
                case 1: goto L_0x021b;
                case 2: goto L_0x020d;
                case 3: goto L_0x01ff;
                case 4: goto L_0x01f1;
                case 5: goto L_0x01e3;
                case 6: goto L_0x01d1;
                case 7: goto L_0x01c3;
                case 8: goto L_0x01b5;
                case 9: goto L_0x01a7;
                case 10: goto L_0x0199;
                case 11: goto L_0x018b;
                case 12: goto L_0x0107;
                case 13: goto L_0x00f5;
                default: goto L_0x00f3;
            }
        L_0x00f3:
            goto L_0x003d
        L_0x00f5:
            java.lang.Object r4 = r4.getValue()     // Catch:{ NumberFormatException -> 0x0103 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ NumberFormatException -> 0x0103 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NumberFormatException -> 0x0103 }
            r10.n = r4     // Catch:{ NumberFormatException -> 0x0103 }
            goto L_0x003d
        L_0x0103:
            r10.n = r3
            goto L_0x003d
        L_0x0107:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            java.util.List r4 = com.facebook.ads.internal.h.b.a(r4)
            r10.q = r4
            android.webkit.CookieManager r4 = android.webkit.CookieManager.getInstance()     // Catch:{ Exception -> 0x0181 }
            boolean r5 = r4.acceptCookie()     // Catch:{ Exception -> 0x0181 }
            r4.setAcceptCookie(r1)     // Catch:{ Exception -> 0x0181 }
            java.util.List<com.facebook.ads.internal.h.b> r6 = r10.q     // Catch:{ Exception -> 0x0181 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x0181 }
        L_0x0124:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x0181 }
            if (r7 == 0) goto L_0x016f
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x0181 }
            com.facebook.ads.internal.h.b r7 = (com.facebook.ads.internal.h.b) r7     // Catch:{ Exception -> 0x0181 }
            boolean r8 = r7.b()     // Catch:{ Exception -> 0x0181 }
            if (r8 == 0) goto L_0x0124
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0181 }
            r8.<init>()     // Catch:{ Exception -> 0x0181 }
            java.lang.String r9 = r7.b     // Catch:{ Exception -> 0x0181 }
            r8.append(r9)     // Catch:{ Exception -> 0x0181 }
            java.lang.String r9 = "="
            r8.append(r9)     // Catch:{ Exception -> 0x0181 }
            java.lang.String r9 = r7.c     // Catch:{ Exception -> 0x0181 }
            r8.append(r9)     // Catch:{ Exception -> 0x0181 }
            java.lang.String r9 = ";Domain="
            r8.append(r9)     // Catch:{ Exception -> 0x0181 }
            java.lang.String r9 = r7.a     // Catch:{ Exception -> 0x0181 }
            r8.append(r9)     // Catch:{ Exception -> 0x0181 }
            java.lang.String r9 = ";Expires="
            r8.append(r9)     // Catch:{ Exception -> 0x0181 }
            java.lang.String r9 = r7.a()     // Catch:{ Exception -> 0x0181 }
            r8.append(r9)     // Catch:{ Exception -> 0x0181 }
            java.lang.String r9 = ";path=/"
            r8.append(r9)     // Catch:{ Exception -> 0x0181 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0181 }
            java.lang.String r7 = r7.a     // Catch:{ Exception -> 0x0181 }
            r4.setCookie(r7, r8)     // Catch:{ Exception -> 0x0181 }
            goto L_0x0124
        L_0x016f:
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0181 }
            r7 = 21
            if (r6 >= r7) goto L_0x017c
            android.webkit.CookieSyncManager r6 = android.webkit.CookieSyncManager.getInstance()     // Catch:{ Exception -> 0x0181 }
            r6.startSync()     // Catch:{ Exception -> 0x0181 }
        L_0x017c:
            r4.setAcceptCookie(r5)     // Catch:{ Exception -> 0x0181 }
            goto L_0x003d
        L_0x0181:
            r4 = move-exception
            java.lang.String r5 = c
            java.lang.String r6 = "Failed to set cookie."
            android.util.Log.w(r5, r6, r4)
            goto L_0x003d
        L_0x018b:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.m = r4
            goto L_0x003d
        L_0x0199:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.l = r4
            goto L_0x003d
        L_0x01a7:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.k = r4
            goto L_0x003d
        L_0x01b5:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.b = r4
            goto L_0x003d
        L_0x01c3:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.a = r4
            goto L_0x003d
        L_0x01d1:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            boolean r4 = r4.booleanValue()
            r10.p = r4
            goto L_0x003d
        L_0x01e3:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.o = r4
            goto L_0x003d
        L_0x01f1:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.j = r4
            goto L_0x003d
        L_0x01ff:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.i = r4
            goto L_0x003d
        L_0x020d:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.h = r4
            goto L_0x003d
        L_0x021b:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            r10.g = r4
            goto L_0x003d
        L_0x0229:
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            com.facebook.ads.internal.protocol.AdPlacementType r4 = com.facebook.ads.internal.protocol.AdPlacementType.fromString(r4)
            r10.f = r4
            goto L_0x003d
        L_0x0237:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.h.d.<init>(java.util.Map):void");
    }

    public static d a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Iterator<String> keys = jSONObject.keys();
        HashMap hashMap = new HashMap();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, String.valueOf(jSONObject.opt(next)));
        }
        return new d(hashMap);
    }

    public long a() {
        return this.e;
    }

    public AdPlacementType b() {
        return this.f;
    }

    public long c() {
        return (long) (this.i * 1000);
    }

    public long d() {
        return (long) (this.j * 1000);
    }

    public boolean e() {
        return this.p;
    }

    public int f() {
        return this.g;
    }

    public int g() {
        return this.h;
    }

    public int h() {
        return this.k;
    }

    public int i() {
        return this.l;
    }

    public int j() {
        return this.m;
    }

    public int k() {
        return this.n;
    }

    public int l() {
        return this.o * 1000;
    }
}
