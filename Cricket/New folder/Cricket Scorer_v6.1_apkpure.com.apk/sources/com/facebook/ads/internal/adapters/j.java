package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.p;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.d;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.c.d;
import com.facebook.ads.internal.view.f.c.e;
import com.facebook.ads.internal.view.f.c.i;
import com.facebook.ads.internal.view.f.c.k;
import com.google.android.exoplayer2.util.MimeTypes;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class j extends s implements p<Bundle> {
    static final /* synthetic */ boolean e = true;
    @Nullable
    protected c a;
    @Nullable
    protected a b;
    @Nullable
    protected JSONObject c;
    @Nullable
    protected Context d;
    private final f<b> f = new f<b>() {
        public Class<b> a() {
            return b.class;
        }

        public void a(b bVar) {
            if (j.this.j != null) {
                j.this.j.d(j.this);
            }
        }
    };
    private final f<l> g = new f<l>() {
        public Class<l> a() {
            return l.class;
        }

        public void a(l lVar) {
            boolean unused = j.this.l = true;
            if (j.this.j != null) {
                j.this.j.a(j.this);
            }
        }
    };
    private final f<d> h = new f<d>() {
        public Class<d> a() {
            return d.class;
        }

        public void a(d dVar) {
            if (j.this.j != null) {
                j.this.j.a((s) j.this, AdError.INTERNAL_ERROR);
            }
        }
    };
    private final f<com.facebook.ads.internal.view.f.b.a> i = new f<com.facebook.ads.internal.view.f.b.a>() {
        public Class<com.facebook.ads.internal.view.f.b.a> a() {
            return com.facebook.ads.internal.view.f.b.a.class;
        }

        public void a(com.facebook.ads.internal.view.f.b.a aVar) {
            if (j.this.j != null) {
                j.this.j.b(j.this);
            }
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.a.a j;
    @Nullable
    private String k;
    /* access modifiers changed from: private */
    public boolean l = false;
    @Nullable
    private com.facebook.ads.internal.view.f.c m;
    @Nullable
    private String n;
    private boolean o = false;
    private com.facebook.ads.internal.d.b p;

    private void a(Context context, com.facebook.ads.a.a aVar, JSONObject jSONObject, c cVar, @Nullable Bundle bundle, EnumSet<CacheFlag> enumSet, int i2) {
        Context context2 = context;
        JSONObject jSONObject2 = jSONObject;
        Bundle bundle2 = bundle;
        this.d = context2;
        this.j = aVar;
        c cVar2 = cVar;
        this.a = cVar2;
        this.c = jSONObject2;
        this.l = false;
        JSONObject jSONObject3 = jSONObject2.getJSONObject(MimeTypes.BASE_TYPE_VIDEO);
        this.n = jSONObject2.optString("ct");
        this.b = new a(context2);
        this.b.setVideoProgressReportIntervalMs(i2);
        a();
        this.b.getEventBus().a((T[]) new f[]{this.f, this.g, this.h, this.i});
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.facebook.ads.internal.b.b(this, 1.0E-7d, -1.0d, 0.001d, false) {
            final /* synthetic */ j a;

            {
                this.a = r10;
            }

            /* access modifiers changed from: protected */
            public void a(boolean z, boolean z2, com.facebook.ads.internal.b.c cVar) {
                this.a.f();
            }
        });
        if (bundle2 != null) {
            this.m = new com.facebook.ads.internal.view.f.b(context2, cVar2, this.b, arrayList, this.n, bundle2.getBundle("logger"), (Map<String, String>) null);
        } else {
            this.m = new com.facebook.ads.internal.view.f.b(context2, cVar2, this.b, (List<com.facebook.ads.internal.b.b>) arrayList, this.n);
        }
        this.j.a((s) this, (View) this.b);
        this.k = jSONObject3.getString((s.a(context) != s.a.c || !jSONObject3.has("videoHDURL") || jSONObject3.isNull("videoHDURL")) ? AudienceNetworkActivity.VIDEO_URL : "videoHDURL");
        if (enumSet.contains(CacheFlag.VIDEO)) {
            this.p = new com.facebook.ads.internal.d.b(context2);
            this.p.a(this.k);
            this.p.a((com.facebook.ads.internal.d.a) new com.facebook.ads.internal.d.a() {
                public void a() {
                    j.this.b.setVideoURI(j.this.h());
                }

                public void b() {
                    j.this.b.setVideoURI(j.this.h());
                }
            });
            return;
        }
        this.b.setVideoURI(h());
    }

    /* access modifiers changed from: private */
    public String h() {
        String str = "";
        if (!(this.p == null || this.k == null)) {
            str = this.p.b(this.k);
        }
        return TextUtils.isEmpty(str) ? this.k : str;
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (!e && this.d == null) {
            throw new AssertionError();
        } else if (e || this.c != null) {
            JSONObject optJSONObject = this.c.optJSONObject(MimeTypes.BASE_TYPE_TEXT);
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            this.b.a((com.facebook.ads.internal.view.f.a.b) new k(this.d));
            com.facebook.ads.internal.view.f.c.l lVar = new com.facebook.ads.internal.view.f.c.l(this.d);
            this.b.a((com.facebook.ads.internal.view.f.a.b) lVar);
            this.b.a((com.facebook.ads.internal.view.f.a.b) new com.facebook.ads.internal.view.f.c.d(lVar, d.a.INVSIBLE));
            this.b.a((com.facebook.ads.internal.view.f.a.b) new com.facebook.ads.internal.view.f.c.b(this.d));
            String b2 = b();
            if (b2 != null) {
                com.facebook.ads.internal.view.f.c.c cVar = new com.facebook.ads.internal.view.f.c.c(this.d, b2);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                cVar.setLayoutParams(layoutParams);
                cVar.setCountdownTextColor(-1);
                this.b.a((com.facebook.ads.internal.view.f.a.b) cVar);
            }
            if (this.c.has("cta") && !this.c.isNull("cta")) {
                JSONObject jSONObject = this.c.getJSONObject("cta");
                e eVar = new e(this.d, jSONObject.getString("url"), this.a, this.n, jSONObject.getString(MimeTypes.BASE_TYPE_TEXT));
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(10);
                layoutParams2.addRule(11);
                eVar.setLayoutParams(layoutParams2);
                this.b.a((com.facebook.ads.internal.view.f.a.b) eVar);
            }
            String d2 = d();
            if (!TextUtils.isEmpty(d2)) {
                this.b.a((com.facebook.ads.internal.view.f.a.b) new com.facebook.ads.internal.view.f.c.a(this.d, d2, this.n, new float[]{0.0f, 0.0f, 8.0f, 0.0f}));
            }
            int c2 = c();
            if (c2 > 0) {
                i iVar = new i(this.d, c2, optJSONObject.optString("skipAdIn", "Skip Ad in"), optJSONObject.optString("skipAd", "Skip Ad"));
                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams3.addRule(12);
                layoutParams3.addRule(11);
                iVar.setLayoutParams(layoutParams3);
                iVar.setPadding(0, 0, 0, 30);
                this.b.a((com.facebook.ads.internal.view.f.a.b) iVar);
            }
        } else {
            throw new AssertionError();
        }
    }

    public final void a(Context context, com.facebook.ads.a.a aVar, c cVar, Bundle bundle, EnumSet<CacheFlag> enumSet) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("ad_response"));
            a(context, aVar, jSONObject, cVar, bundle, enumSet, jSONObject.optInt("video_time_polling_interval", 200));
        } catch (JSONException unused) {
            aVar.a((s) this, AdError.INTERNAL_ERROR);
        }
    }

    public final void a(Context context, com.facebook.ads.a.a aVar, Map<String, Object> map, c cVar, EnumSet<CacheFlag> enumSet) {
        try {
            JSONObject jSONObject = (JSONObject) map.get("data");
            com.facebook.ads.internal.h.d dVar = (com.facebook.ads.internal.h.d) map.get("definition");
            a(context, aVar, jSONObject, cVar, (Bundle) null, enumSet, dVar == null ? 200 : dVar.k());
        } catch (JSONException unused) {
            aVar.a((s) this, AdError.INTERNAL_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public String b() {
        if (e || this.c != null) {
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (!jSONObject.has("countdown") || jSONObject.isNull("countdown")) {
                    return null;
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("countdown");
                if (jSONObject2.has(DublinCoreProperties.FORMAT)) {
                    return jSONObject2.optString(DublinCoreProperties.FORMAT);
                }
                return null;
            } catch (Exception e2) {
                Log.w(String.valueOf(j.class), "Invalid JSON", e2);
                return null;
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: protected */
    public int c() {
        if (e || this.c != null) {
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (!jSONObject.has("skipButton") || jSONObject.isNull("skipButton")) {
                    return -1;
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("skipButton");
                if (jSONObject2.has("skippableSeconds")) {
                    return jSONObject2.getInt("skippableSeconds");
                }
                return -1;
            } catch (Exception e2) {
                Log.w(String.valueOf(j.class), "Invalid JSON", e2);
                return -1;
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String d() {
        if (e || this.c != null) {
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (!jSONObject.has("adChoices") || jSONObject.isNull("adChoices")) {
                    return null;
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("adChoices");
                if (jSONObject2.has("url")) {
                    return jSONObject2.getString("url");
                }
                return null;
            } catch (Exception e2) {
                Log.w(String.valueOf(j.class), "Invalid JSON", e2);
                return null;
            }
        } else {
            throw new AssertionError();
        }
    }

    public boolean e() {
        if (!this.l || this.b == null) {
            return false;
        }
        if (this.m.j() > 0) {
            this.b.a(this.m.j());
        }
        this.b.a(com.facebook.ads.internal.view.f.a.a.AUTO_STARTED);
        return true;
    }

    /* access modifiers changed from: protected */
    public void f() {
        if (this.a != null && !this.o) {
            this.o = true;
            this.a.a(this.n, new HashMap());
            if (this.j != null) {
                this.j.c(this);
            }
        }
    }

    public Bundle g() {
        if (this.m == null || this.c == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("logger", this.m.g());
        bundle.putString("ad_response", this.c.toString());
        return bundle;
    }

    public void onDestroy() {
        if (this.b != null) {
            this.b.f();
            this.b.k();
        }
        this.j = null;
        this.a = null;
        this.k = null;
        this.l = false;
        this.n = null;
        this.b = null;
        this.m = null;
        this.c = null;
        this.d = null;
        this.o = false;
    }
}
