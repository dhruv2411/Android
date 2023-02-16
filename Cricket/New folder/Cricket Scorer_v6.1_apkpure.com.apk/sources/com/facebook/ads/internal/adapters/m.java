package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.j.b;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.h;
import com.facebook.ads.internal.n.j;
import com.facebook.ads.internal.n.k;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.q.a.q;
import com.facebook.ads.internal.q.a.z;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class m extends y implements e.a {
    private static final String a = "m";
    private int A;
    private String B;
    private String C;
    private com.facebook.ads.internal.n.m D;
    private int E = 200;
    private String F;
    private h G;
    private String H;
    private String I;
    private k J;
    private List<f> K;
    private int L = -1;
    private int M;
    /* access modifiers changed from: private */
    public String N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private long T = 0;
    private a.C0004a U = null;
    /* access modifiers changed from: private */
    @Nullable
    public c V;
    private f.c W;
    private Context b;
    private z c;
    private Uri d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private h p;
    private h q;
    private j r;
    private String s;
    private d t;
    private Collection<String> u;
    private boolean v;
    private boolean w;
    private int x;
    private int y;
    private int z;

    private boolean M() {
        if ((this.O || TextUtils.isEmpty(this.e)) && (TextUtils.isEmpty(this.f) || !this.O)) {
            return false;
        }
        if (this.p != null || this.O) {
            return this.q != null || getPlacementType() == AdPlacementType.NATIVE_BANNER;
        }
        return false;
    }

    private void N() {
        if (!this.S) {
            if (this.V != null) {
                this.V.a(this.s);
            }
            this.S = true;
        }
    }

    private void a(Context context, JSONObject jSONObject, c cVar, String str, int i2, int i3) {
        this.O = true;
        this.b = context;
        this.V = cVar;
        this.L = i2;
        this.M = i3;
        a(jSONObject, str);
    }

    private void a(Map<String, String> map, final Map<String, String> map2) {
        try {
            final Map<String, String> c2 = c(map);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(m.this.N)) {
                        HashMap hashMap = new HashMap();
                        hashMap.putAll(map2);
                        hashMap.putAll(c2);
                        if (m.this.V != null) {
                            m.this.V.f(m.this.N, hashMap);
                        }
                    }
                }
            }, (long) (this.x * 1000));
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0197 A[Catch:{ JSONException -> 0x01bd }, LOOP:0: B:46:0x0195->B:47:0x0197, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.json.JSONObject r13, java.lang.String r14) {
        /*
            r12 = this;
            boolean r0 = r12.P
            if (r0 == 0) goto L_0x000c
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "Adapter already loaded data"
            r13.<init>(r14)
            throw r13
        L_0x000c:
            if (r13 != 0) goto L_0x000f
            return
        L_0x000f:
            android.content.Context r0 = r12.b
            java.lang.String r1 = "Audience Network Loaded"
            com.facebook.ads.internal.q.a.d.a(r0, r1)
            r12.N = r14
            java.lang.String r0 = "fbad_command"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 == 0) goto L_0x0027
            r0 = r2
            goto L_0x002b
        L_0x0027:
            android.net.Uri r0 = android.net.Uri.parse(r0)
        L_0x002b:
            r12.d = r0
            java.lang.String r0 = "advertiser_name"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.e = r0
            java.lang.String r0 = "title"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.f = r0
            java.lang.String r0 = "subtitle"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.g = r0
            java.lang.String r0 = "headline"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.h = r0
            java.lang.String r0 = "body"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.i = r0
            java.lang.String r0 = "call_to_action"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.j = r0
            java.lang.String r0 = r12.j
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0067
            r12.j = r2
        L_0x0067:
            java.lang.String r0 = "social_context"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.k = r0
            java.lang.String r0 = "link_description"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.l = r0
            java.lang.String r0 = "sponsored_translation"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.m = r0
            java.lang.String r0 = "ad_translation"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.n = r0
            java.lang.String r0 = "promoted_translation"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.o = r0
            java.lang.String r0 = "icon"
            org.json.JSONObject r0 = r13.optJSONObject(r0)
            com.facebook.ads.internal.n.h r0 = com.facebook.ads.internal.n.h.a(r0)
            r12.p = r0
            java.lang.String r0 = "image"
            org.json.JSONObject r0 = r13.optJSONObject(r0)
            com.facebook.ads.internal.n.h r0 = com.facebook.ads.internal.n.h.a(r0)
            r12.q = r0
            java.lang.String r0 = "star_rating"
            org.json.JSONObject r0 = r13.optJSONObject(r0)
            com.facebook.ads.internal.n.j r0 = com.facebook.ads.internal.n.j.a(r0)
            r12.r = r0
            java.lang.String r0 = "used_report_url"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.s = r0
            java.lang.String r0 = "enable_view_log"
            boolean r0 = r13.optBoolean(r0)
            r12.v = r0
            java.lang.String r0 = "enable_snapshot_log"
            boolean r0 = r13.optBoolean(r0)
            r12.w = r0
            java.lang.String r0 = "snapshot_log_delay_second"
            r1 = 4
            int r0 = r13.optInt(r0, r1)
            r12.x = r0
            java.lang.String r0 = "snapshot_compress_quality"
            r1 = 0
            int r0 = r13.optInt(r0, r1)
            r12.y = r0
            java.lang.String r0 = "viewability_check_initial_delay"
            int r0 = r13.optInt(r0, r1)
            r12.z = r0
            java.lang.String r0 = "viewability_check_interval"
            r3 = 1000(0x3e8, float:1.401E-42)
            int r0 = r13.optInt(r0, r3)
            r12.A = r0
            java.lang.String r0 = "ad_choices_icon"
            org.json.JSONObject r0 = r13.optJSONObject(r0)
            java.lang.String r3 = "native_ui_config"
            org.json.JSONObject r3 = r13.optJSONObject(r3)
            if (r3 == 0) goto L_0x010a
            int r4 = r3.length()     // Catch:{ JSONException -> 0x010e }
            if (r4 != 0) goto L_0x0104
            goto L_0x010a
        L_0x0104:
            com.facebook.ads.internal.n.k r4 = new com.facebook.ads.internal.n.k     // Catch:{ JSONException -> 0x010e }
            r4.<init>(r3)     // Catch:{ JSONException -> 0x010e }
            goto L_0x010b
        L_0x010a:
            r4 = r2
        L_0x010b:
            r12.J = r4     // Catch:{ JSONException -> 0x010e }
            goto L_0x0110
        L_0x010e:
            r12.J = r2
        L_0x0110:
            if (r0 == 0) goto L_0x0118
            com.facebook.ads.internal.n.h r0 = com.facebook.ads.internal.n.h.a(r0)
            r12.G = r0
        L_0x0118:
            java.lang.String r0 = "ad_choices_link_url"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.H = r0
            java.lang.String r0 = "request_id"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.I = r0
            java.lang.String r0 = "invalidation_behavior"
            java.lang.String r0 = r13.optString(r0)
            com.facebook.ads.internal.a.d r0 = com.facebook.ads.internal.a.d.a(r0)
            r12.t = r0
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0140 }
            java.lang.String r3 = "detection_strings"
            java.lang.String r3 = r13.optString(r3)     // Catch:{ JSONException -> 0x0140 }
            r0.<init>(r3)     // Catch:{ JSONException -> 0x0140 }
            goto L_0x0145
        L_0x0140:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r2
        L_0x0145:
            java.util.Collection r0 = com.facebook.ads.internal.a.e.a(r0)
            r12.u = r0
            java.lang.String r0 = "video_url"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.B = r0
            java.lang.String r0 = "video_mpd"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.C = r0
            java.lang.String r0 = "video_autoplay_enabled"
            boolean r0 = r13.has(r0)
            if (r0 != 0) goto L_0x0168
            com.facebook.ads.internal.n.m r0 = com.facebook.ads.internal.n.m.DEFAULT
        L_0x0165:
            r12.D = r0
            goto L_0x0176
        L_0x0168:
            java.lang.String r0 = "video_autoplay_enabled"
            boolean r0 = r13.optBoolean(r0)
            if (r0 == 0) goto L_0x0173
            com.facebook.ads.internal.n.m r0 = com.facebook.ads.internal.n.m.ON
            goto L_0x0165
        L_0x0173:
            com.facebook.ads.internal.n.m r0 = com.facebook.ads.internal.n.m.OFF
            goto L_0x0165
        L_0x0176:
            java.lang.String r0 = "video_report_url"
            java.lang.String r0 = com.facebook.ads.internal.q.a.k.a(r13, r0)
            r12.F = r0
            java.lang.String r0 = "carousel"
            org.json.JSONArray r13 = r13.optJSONArray(r0)     // Catch:{ JSONException -> 0x01bd }
            if (r13 == 0) goto L_0x01c5
            int r0 = r13.length()     // Catch:{ JSONException -> 0x01bd }
            if (r0 <= 0) goto L_0x01c5
            int r0 = r13.length()     // Catch:{ JSONException -> 0x01bd }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ JSONException -> 0x01bd }
            r10.<init>(r0)     // Catch:{ JSONException -> 0x01bd }
        L_0x0195:
            if (r1 >= r0) goto L_0x01ba
            com.facebook.ads.internal.adapters.m r11 = new com.facebook.ads.internal.adapters.m     // Catch:{ JSONException -> 0x01bd }
            r11.<init>()     // Catch:{ JSONException -> 0x01bd }
            android.content.Context r4 = r12.b     // Catch:{ JSONException -> 0x01bd }
            org.json.JSONObject r5 = r13.getJSONObject(r1)     // Catch:{ JSONException -> 0x01bd }
            com.facebook.ads.internal.m.c r6 = r12.V     // Catch:{ JSONException -> 0x01bd }
            r3 = r11
            r7 = r14
            r8 = r1
            r9 = r0
            r3.a(r4, r5, r6, r7, r8, r9)     // Catch:{ JSONException -> 0x01bd }
            com.facebook.ads.internal.n.f r3 = new com.facebook.ads.internal.n.f     // Catch:{ JSONException -> 0x01bd }
            android.content.Context r4 = r12.b     // Catch:{ JSONException -> 0x01bd }
            com.facebook.ads.internal.n.f$c r5 = r12.W     // Catch:{ JSONException -> 0x01bd }
            r3.<init>(r4, r11, r2, r5)     // Catch:{ JSONException -> 0x01bd }
            r10.add(r3)     // Catch:{ JSONException -> 0x01bd }
            int r1 = r1 + 1
            goto L_0x0195
        L_0x01ba:
            r12.K = r10     // Catch:{ JSONException -> 0x01bd }
            goto L_0x01c5
        L_0x01bd:
            r13 = move-exception
            java.lang.String r14 = a
            java.lang.String r0 = "Unable to parse carousel data."
            android.util.Log.e(r14, r0, r13)
        L_0x01c5:
            r13 = 1
            r12.P = r13
            boolean r13 = r12.M()
            r12.Q = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.m.a(org.json.JSONObject, java.lang.String):void");
    }

    private Map<String, String> c(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (map.containsKey(Promotion.ACTION_VIEW)) {
            hashMap.put(Promotion.ACTION_VIEW, map.get(Promotion.ACTION_VIEW));
        }
        if (map.containsKey("snapshot")) {
            hashMap.put("snapshot", map.get("snapshot"));
        }
        return hashMap;
    }

    public String A() {
        if (!c_()) {
            return null;
        }
        return this.B;
    }

    public String B() {
        if (!c_()) {
            return null;
        }
        return this.C;
    }

    public com.facebook.ads.internal.n.m C() {
        return !c_() ? com.facebook.ads.internal.n.m.DEFAULT : this.D;
    }

    public int D() {
        return this.E;
    }

    public String E() {
        return this.F;
    }

    public List<f> F() {
        if (!c_()) {
            return null;
        }
        return this.K;
    }

    public int G() {
        return this.L;
    }

    public int H() {
        return this.M;
    }

    public com.facebook.ads.internal.n.c I() {
        return com.facebook.ads.internal.n.c.AN;
    }

    public String K() {
        if (!c_()) {
            return null;
        }
        N();
        return this.i;
    }

    public boolean L() {
        return this.O;
    }

    public d a() {
        return this.t;
    }

    public void a(int i2) {
        if (c_() && i2 == 0 && this.T > 0 && this.U != null) {
            b.a(a.a(this.T, this.U, this.I));
            this.T = 0;
            this.U = null;
        }
    }

    public void a(Context context, z zVar, c cVar, Map<String, Object> map, f.c cVar2) {
        this.b = context;
        this.c = zVar;
        this.V = cVar;
        this.W = cVar2;
        JSONObject jSONObject = (JSONObject) map.get("data");
        com.facebook.ads.internal.h.d dVar = (com.facebook.ads.internal.h.d) map.get("definition");
        this.E = dVar != null ? dVar.k() : 200;
        a(jSONObject, com.facebook.ads.internal.q.a.k.a(jSONObject, "ct"));
        if (e.a(context, this, cVar)) {
            zVar.a(this, new com.facebook.ads.internal.protocol.a(AdErrorType.NO_FILL, "No Fill"));
            return;
        }
        if (zVar != null) {
            zVar.a(this);
        }
        a.a = this.I;
    }

    public void a(View view, List<View> list) {
    }

    public void a(z zVar) {
        this.c = zVar;
    }

    public void a(Map<String, String> map) {
        if (c_() && !this.R) {
            if (this.c != null) {
                this.c.b(this);
            }
            HashMap hashMap = new HashMap();
            if (map != null) {
                hashMap.putAll(map);
            }
            if (this.O) {
                hashMap.put("cardind", String.valueOf(this.L));
                hashMap.put("cardcnt", String.valueOf(this.M));
            }
            if (!TextUtils.isEmpty(c()) && this.V != null) {
                this.V.a(c(), hashMap);
            }
            if (e() || d()) {
                a(map, (Map<String, String>) hashMap);
            }
            this.R = true;
        }
    }

    public boolean a_() {
        return c_() && this.d != null;
    }

    public Collection<String> b() {
        return this.u;
    }

    public void b(Map<String, String> map) {
        if (c_()) {
            if (!com.facebook.ads.internal.l.a.c(this.b) || !z.a(map)) {
                HashMap hashMap = new HashMap();
                if (map != null) {
                    hashMap.putAll(map);
                }
                com.facebook.ads.internal.q.a.d.a(this.b, "Click logged");
                if (this.c != null) {
                    this.c.c(this);
                }
                if (this.O) {
                    hashMap.put("cardind", String.valueOf(this.L));
                    hashMap.put("cardcnt", String.valueOf(this.M));
                }
                com.facebook.ads.internal.a.b a2 = com.facebook.ads.internal.a.c.a(this.b, this.V, this.N, this.d, hashMap);
                if (a2 != null) {
                    try {
                        this.T = System.currentTimeMillis();
                        this.U = a2.a();
                        a2.b();
                    } catch (Exception e2) {
                        Log.e(a, "Error executing action", e2);
                    }
                }
            } else {
                Log.e(a, "Click happened on lockscreen ad");
            }
        }
    }

    public void b_() {
        if (this.K != null && !this.K.isEmpty()) {
            for (f J2 : this.K) {
                J2.J();
            }
        }
    }

    public String c() {
        return this.N;
    }

    public boolean c_() {
        return this.P && this.Q;
    }

    public boolean d() {
        return c_() && this.w;
    }

    public boolean e() {
        return c_() && this.v;
    }

    public boolean f() {
        return c_() && this.J != null;
    }

    public boolean g() {
        return true;
    }

    public int h() {
        if (this.y < 0 || this.y > 100) {
            return 0;
        }
        return this.y;
    }

    public int i() {
        return this.z;
    }

    public int j() {
        return this.A;
    }

    public h k() {
        if (!c_()) {
            return null;
        }
        return this.p;
    }

    public h l() {
        if (!c_()) {
            return null;
        }
        return this.q;
    }

    public k m() {
        if (!c_()) {
            return null;
        }
        return this.J;
    }

    public String n() {
        if (!c_()) {
            return null;
        }
        N();
        return this.e;
    }

    public String o() {
        if (!c_()) {
            return null;
        }
        N();
        return this.h;
    }

    public void onDestroy() {
    }

    public String p() {
        if (!c_()) {
            return null;
        }
        N();
        return q.a(this.i);
    }

    public String q() {
        if (!c_()) {
            return null;
        }
        N();
        return this.j;
    }

    public String r() {
        if (!c_()) {
            return null;
        }
        N();
        return this.k;
    }

    public String s() {
        if (!c_()) {
            return null;
        }
        N();
        return this.l;
    }

    public String t() {
        if (!c_()) {
            return null;
        }
        N();
        return this.m;
    }

    public String u() {
        if (!c_()) {
            return null;
        }
        N();
        return this.n;
    }

    public String v() {
        if (!c_()) {
            return null;
        }
        N();
        return this.o;
    }

    public j w() {
        if (!c_()) {
            return null;
        }
        N();
        return this.r;
    }

    public h x() {
        if (!c_()) {
            return null;
        }
        return this.G;
    }

    public String y() {
        if (!c_()) {
            return null;
        }
        return this.H;
    }

    public String z() {
        if (!c_()) {
            return null;
        }
        return "AdChoices";
    }
}
