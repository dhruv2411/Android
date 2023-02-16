package com.facebook.ads.internal.adapters;

import android.content.Context;
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
import com.flurry.android.FlurryAgent;
import com.flurry.android.ads.FlurryAdErrorType;
import com.flurry.android.ads.FlurryAdNative;
import com.flurry.android.ads.FlurryAdNativeAsset;
import com.flurry.android.ads.FlurryAdNativeListener;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class o extends y implements u {
    private static volatile boolean a;
    /* access modifiers changed from: private */
    public z b;
    private FlurryAdNative c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public h j;
    /* access modifiers changed from: private */
    public h k;
    /* access modifiers changed from: private */
    public h l;

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
        return c.FLURRY;
    }

    public f J() {
        return f.YAHOO;
    }

    public void a(int i2) {
    }

    public void a(final Context context, z zVar, com.facebook.ads.internal.m.c cVar, Map<String, Object> map, f.c cVar2) {
        JSONObject jSONObject = (JSONObject) map.get("data");
        String optString = jSONObject.optString("api_key");
        String optString2 = jSONObject.optString("placement_id");
        synchronized (o.class) {
            if (!a) {
                d.a(context, v.a(J()) + " Initializing");
                FlurryAgent.setLogEnabled(true);
                FlurryAgent.init(context, optString);
                a = true;
            }
        }
        d.a(context, v.a(J()) + " Loading");
        this.b = zVar;
        this.c = new FlurryAdNative(context, optString2);
        this.c.setListener(new FlurryAdNativeListener() {
            public void onAppExit(FlurryAdNative flurryAdNative) {
            }

            public void onClicked(FlurryAdNative flurryAdNative) {
                if (o.this.b != null) {
                    o.this.b.c(o.this);
                }
            }

            public void onCloseFullscreen(FlurryAdNative flurryAdNative) {
            }

            public void onCollapsed(FlurryAdNative flurryAdNative) {
            }

            public void onError(FlurryAdNative flurryAdNative, FlurryAdErrorType flurryAdErrorType, int i) {
                Context context = context;
                d.a(context, v.a(o.this.J()) + " Failed with FlurryError: " + flurryAdErrorType.toString());
                if (o.this.b != null) {
                    o.this.b.a(o.this, a.a(AdErrorType.MEDIATION_ERROR, flurryAdErrorType.toString()));
                }
            }

            public void onExpanded(FlurryAdNative flurryAdNative) {
            }

            public void onFetched(FlurryAdNative flurryAdNative) {
                o oVar;
                String str;
                if (o.this.b != null) {
                    if (flurryAdNative.isVideoAd()) {
                        Context context = context;
                        d.a(context, v.a(o.this.J()) + " Failed. AN does not support Flurry video ads");
                        o.this.b.a(o.this, new a(AdErrorType.MEDIATION_ERROR, "video ad"));
                        return;
                    }
                    boolean unused = o.this.d = true;
                    FlurryAdNativeAsset asset = flurryAdNative.getAsset("headline");
                    if (asset != null) {
                        String unused2 = o.this.e = asset.getValue();
                    }
                    FlurryAdNativeAsset asset2 = flurryAdNative.getAsset("summary");
                    if (asset2 != null) {
                        String unused3 = o.this.f = asset2.getValue();
                    }
                    FlurryAdNativeAsset asset3 = flurryAdNative.getAsset("source");
                    if (asset3 != null) {
                        String unused4 = o.this.g = asset3.getValue();
                    }
                    FlurryAdNativeAsset asset4 = flurryAdNative.getAsset("appCategory");
                    if (asset4 != null) {
                        String unused5 = o.this.i = asset4.getValue();
                    }
                    FlurryAdNativeAsset asset5 = flurryAdNative.getAsset("callToAction");
                    if (asset5 != null) {
                        String unused6 = o.this.h = asset5.getValue();
                    } else {
                        if (flurryAdNative.getAsset("appRating") != null) {
                            oVar = o.this;
                            str = "Install Now";
                        } else {
                            oVar = o.this;
                            str = "Learn More";
                        }
                        String unused7 = oVar.h = str;
                    }
                    FlurryAdNativeAsset asset6 = flurryAdNative.getAsset("secImage");
                    if (asset6 != null) {
                        h unused8 = o.this.j = new h(asset6.getValue(), 82, 82);
                    }
                    FlurryAdNativeAsset asset7 = flurryAdNative.getAsset("secHqImage");
                    if (asset7 != null) {
                        h unused9 = o.this.k = new h(asset7.getValue(), 1200, 627);
                    }
                    FlurryAdNativeAsset asset8 = flurryAdNative.getAsset("secBrandingLogo");
                    if (asset8 != null) {
                        h unused10 = o.this.l = new h(asset8.getValue(), 20, 20);
                    }
                    Context context2 = context;
                    d.a(context2, v.a(o.this.J()) + " Loaded");
                    o.this.b.a(o.this);
                }
            }

            public void onImpressionLogged(FlurryAdNative flurryAdNative) {
                if (o.this.b != null) {
                    o.this.b.b(o.this);
                }
            }

            public void onShowFullscreen(FlurryAdNative flurryAdNative) {
            }
        });
        this.c.fetchAd();
    }

    public void a(View view, List<View> list) {
        if (this.c != null) {
            this.c.setTrackingView(view);
        }
    }

    public void a(z zVar) {
        this.b = zVar;
    }

    public void a(Map<String, String> map) {
    }

    public void b(Map<String, String> map) {
    }

    public void b_() {
        if (this.c != null) {
            this.c.removeTrackingView();
        }
    }

    public String c() {
        return null;
    }

    public boolean c_() {
        return this.d;
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
        return this.j;
    }

    public h l() {
        return this.k;
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
        if (this.c != null) {
            this.c.destroy();
            this.c = null;
        }
    }

    public String p() {
        return this.f;
    }

    public String q() {
        return this.h;
    }

    public String r() {
        return this.i;
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
        return this.l;
    }

    public String y() {
        return null;
    }

    public String z() {
        return "Ad";
    }
}
