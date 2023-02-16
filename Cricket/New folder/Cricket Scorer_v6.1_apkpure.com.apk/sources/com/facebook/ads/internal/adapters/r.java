package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.facebook.ads.internal.n.c;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.h;
import com.facebook.ads.internal.n.j;
import com.facebook.ads.internal.n.k;
import com.facebook.ads.internal.n.m;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.q.a.d;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;
import com.inmobi.sdk.InMobiSdk;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class r extends y implements u {
    /* access modifiers changed from: private */
    public z a;
    /* access modifiers changed from: private */
    public InMobiNative b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public View d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public j h;
    /* access modifiers changed from: private */
    public h i;
    /* access modifiers changed from: private */
    public h j;

    public String A() {
        return null;
    }

    public String B() {
        return null;
    }

    public m C() {
        return m.DEFAULT;
    }

    public int D() {
        return 0;
    }

    public String E() {
        return null;
    }

    public List<f> F() {
        return null;
    }

    public int G() {
        return 0;
    }

    public int H() {
        return 0;
    }

    public c I() {
        return c.INMOBI;
    }

    public f J() {
        return f.INMOBI;
    }

    public void a(int i2) {
    }

    public void a(final Context context, z zVar, com.facebook.ads.internal.m.c cVar, Map<String, Object> map, f.c cVar2) {
        d.a(context, v.a(J()) + " Loading");
        JSONObject jSONObject = (JSONObject) map.get("data");
        String optString = jSONObject.optString("account_id");
        Long valueOf = Long.valueOf(jSONObject.optLong("placement_id"));
        if (TextUtils.isEmpty(optString) || valueOf == null) {
            zVar.a(this, new a(AdErrorType.MEDIATION_ERROR, "Mediation Error"));
            return;
        }
        this.a = zVar;
        InMobiSdk.init(context, optString);
        this.b = new InMobiNative(valueOf.longValue(), new InMobiNative.NativeAdListener() {
            public void onAdDismissed(InMobiNative inMobiNative) {
            }

            public void onAdDisplayed(InMobiNative inMobiNative) {
            }

            public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
                Context context = context;
                d.a(context, v.a(r.this.J()) + " Failed with InMobi error: " + inMobiAdRequestStatus.getMessage());
                if (r.this.a != null) {
                    r.this.a.a(r.this, new a(AdErrorType.MEDIATION_ERROR.getErrorCode(), inMobiAdRequestStatus.getMessage()));
                }
            }

            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x008a */
            /* JADX WARNING: Removed duplicated region for block: B:13:0x0098 A[Catch:{ Exception -> 0x00da }] */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x00ae A[Catch:{ Exception -> 0x00da }] */
            /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onAdLoadSucceeded(com.inmobi.ads.InMobiNative r7) {
                /*
                    r6 = this;
                    org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x00da }
                    java.lang.Object r1 = r7.getAdContent()     // Catch:{ Exception -> 0x00da }
                    java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x00da }
                    r0.<init>(r1)     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r1 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    java.lang.String r2 = "title"
                    java.lang.String r2 = r0.optString(r2)     // Catch:{ Exception -> 0x00da }
                    java.lang.String unused = r1.e = r2     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r1 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    java.lang.String r2 = "description"
                    java.lang.String r2 = r0.optString(r2)     // Catch:{ Exception -> 0x00da }
                    java.lang.String unused = r1.f = r2     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r1 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    java.lang.String r2 = "cta"
                    java.lang.String r2 = r0.optString(r2)     // Catch:{ Exception -> 0x00da }
                    java.lang.String unused = r1.g = r2     // Catch:{ Exception -> 0x00da }
                    java.lang.String r1 = "icon"
                    org.json.JSONObject r1 = r0.optJSONObject(r1)     // Catch:{ Exception -> 0x00da }
                    if (r1 == 0) goto L_0x0050
                    java.lang.String r2 = "width"
                    int r2 = r1.optInt(r2)     // Catch:{ Exception -> 0x00da }
                    java.lang.String r3 = "height"
                    int r3 = r1.optInt(r3)     // Catch:{ Exception -> 0x00da }
                    java.lang.String r4 = "url"
                    java.lang.String r1 = r1.optString(r4)     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r4 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.n.h r5 = new com.facebook.ads.internal.n.h     // Catch:{ Exception -> 0x00da }
                    r5.<init>(r1, r2, r3)     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.n.h unused = r4.i = r5     // Catch:{ Exception -> 0x00da }
                L_0x0050:
                    java.lang.String r1 = "screenshots"
                    org.json.JSONObject r1 = r0.optJSONObject(r1)     // Catch:{ Exception -> 0x00da }
                    if (r1 == 0) goto L_0x0074
                    java.lang.String r2 = "width"
                    int r2 = r1.optInt(r2)     // Catch:{ Exception -> 0x00da }
                    java.lang.String r3 = "height"
                    int r3 = r1.optInt(r3)     // Catch:{ Exception -> 0x00da }
                    java.lang.String r4 = "url"
                    java.lang.String r1 = r1.optString(r4)     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r4 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.n.h r5 = new com.facebook.ads.internal.n.h     // Catch:{ Exception -> 0x00da }
                    r5.<init>(r1, r2, r3)     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.n.h unused = r4.j = r5     // Catch:{ Exception -> 0x00da }
                L_0x0074:
                    java.lang.String r1 = "rating"
                    java.lang.String r0 = r0.optString(r1)     // Catch:{ Exception -> 0x00da }
                    double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ Exception -> 0x008a }
                    com.facebook.ads.internal.adapters.r r2 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x008a }
                    com.facebook.ads.internal.n.j r3 = new com.facebook.ads.internal.n.j     // Catch:{ Exception -> 0x008a }
                    r4 = 4617315517961601024(0x4014000000000000, double:5.0)
                    r3.<init>(r0, r4)     // Catch:{ Exception -> 0x008a }
                    com.facebook.ads.internal.n.j unused = r2.h = r3     // Catch:{ Exception -> 0x008a }
                L_0x008a:
                    com.facebook.ads.internal.adapters.r r0 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    r1 = 1
                    boolean unused = r0.c = r1     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r0 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    android.view.View r0 = r0.d     // Catch:{ Exception -> 0x00da }
                    if (r0 == 0) goto L_0x00a6
                    com.facebook.ads.internal.adapters.r r0 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    com.inmobi.ads.InMobiNative unused = r0.b     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r0 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    android.view.View r0 = r0.d     // Catch:{ Exception -> 0x00da }
                    com.inmobi.ads.InMobiNative.bind(r0, r7)     // Catch:{ Exception -> 0x00da }
                L_0x00a6:
                    com.facebook.ads.internal.adapters.r r7 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.z r7 = r7.a     // Catch:{ Exception -> 0x00da }
                    if (r7 == 0) goto L_0x0115
                    android.content.Context r7 = r3     // Catch:{ Exception -> 0x00da }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00da }
                    r0.<init>()     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r1 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.f r1 = r1.J()     // Catch:{ Exception -> 0x00da }
                    java.lang.String r1 = com.facebook.ads.internal.adapters.v.a(r1)     // Catch:{ Exception -> 0x00da }
                    r0.append(r1)     // Catch:{ Exception -> 0x00da }
                    java.lang.String r1 = " Loaded"
                    r0.append(r1)     // Catch:{ Exception -> 0x00da }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.q.a.d.a(r7, r0)     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r7 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.z r7 = r7.a     // Catch:{ Exception -> 0x00da }
                    com.facebook.ads.internal.adapters.r r0 = com.facebook.ads.internal.adapters.r.this     // Catch:{ Exception -> 0x00da }
                    r7.a(r0)     // Catch:{ Exception -> 0x00da }
                    return
                L_0x00da:
                    com.facebook.ads.internal.adapters.r r7 = com.facebook.ads.internal.adapters.r.this
                    com.facebook.ads.internal.adapters.z r7 = r7.a
                    if (r7 == 0) goto L_0x0115
                    android.content.Context r7 = r3
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    com.facebook.ads.internal.adapters.r r1 = com.facebook.ads.internal.adapters.r.this
                    com.facebook.ads.internal.adapters.f r1 = r1.J()
                    java.lang.String r1 = com.facebook.ads.internal.adapters.v.a(r1)
                    r0.append(r1)
                    java.lang.String r1 = " Failed. Internal AN SDK error"
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    com.facebook.ads.internal.q.a.d.a(r7, r0)
                    com.facebook.ads.internal.adapters.r r7 = com.facebook.ads.internal.adapters.r.this
                    com.facebook.ads.internal.adapters.z r7 = r7.a
                    com.facebook.ads.internal.adapters.r r0 = com.facebook.ads.internal.adapters.r.this
                    com.facebook.ads.internal.protocol.AdErrorType r1 = com.facebook.ads.internal.protocol.AdErrorType.INTERNAL_ERROR
                    java.lang.String r2 = "Internal Error"
                    com.facebook.ads.internal.protocol.a r1 = com.facebook.ads.internal.protocol.a.a(r1, r2)
                    r7.a(r0, r1)
                L_0x0115:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.r.AnonymousClass1.onAdLoadSucceeded(com.inmobi.ads.InMobiNative):void");
            }

            public void onUserLeftApplication(InMobiNative inMobiNative) {
            }
        });
        this.b.load();
    }

    public void a(View view, List<View> list) {
        this.d = view;
        if (c_()) {
            InMobiNative inMobiNative = this.b;
            InMobiNative.bind(this.d, this.b);
        }
    }

    public void a(z zVar) {
        this.a = zVar;
    }

    public void a(Map<String, String> map) {
        this.a.b(this);
    }

    public void b(Map<String, String> map) {
        if (c_()) {
            this.a.c(this);
            this.b.reportAdClickAndOpenLandingPage((Map) null);
        }
    }

    public void b_() {
        if (c_()) {
            InMobiNative inMobiNative = this.b;
            InMobiNative.unbind(this.d);
        }
        this.d = null;
    }

    public String c() {
        return null;
    }

    public boolean c_() {
        return this.b != null && this.c;
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return false;
    }

    public boolean g() {
        return true;
    }

    public int h() {
        return 0;
    }

    public int i() {
        return 0;
    }

    public int j() {
        return 0;
    }

    public h k() {
        return this.i;
    }

    public h l() {
        return this.j;
    }

    public k m() {
        return null;
    }

    public String n() {
        return null;
    }

    public String o() {
        return null;
    }

    public void onDestroy() {
        b_();
        this.b = null;
        this.a = null;
    }

    public String p() {
        return this.f;
    }

    public String q() {
        return this.g;
    }

    public String r() {
        return null;
    }

    public String s() {
        return null;
    }

    public String t() {
        return null;
    }

    public String u() {
        return null;
    }

    public String v() {
        return null;
    }

    public j w() {
        return null;
    }

    public h x() {
        return null;
    }

    public String y() {
        return null;
    }

    public String z() {
        return "Ad";
    }
}
