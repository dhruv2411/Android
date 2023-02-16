package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.j.b;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.view.b.a;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

public class i extends BannerAdapter {
    /* access modifiers changed from: private */
    public static final String a = "i";
    @Nullable
    private a.b b;
    @Nullable
    private a c;
    /* access modifiers changed from: private */
    public q d;
    /* access modifiers changed from: private */
    public BannerAdapterListener e;
    private Map<String, Object> f;
    /* access modifiers changed from: private */
    @Nullable
    public c g;
    /* access modifiers changed from: private */
    public Context h;
    /* access modifiers changed from: private */
    public long i;
    /* access modifiers changed from: private */
    public a.C0004a j;

    private void a(d dVar) {
        this.i = 0;
        this.j = null;
        final p a2 = p.a((JSONObject) this.f.get("data"));
        if (e.a(this.h, a2, this.g)) {
            this.e.onBannerError(this, AdError.NO_FILL);
            return;
        }
        this.b = new a.c() {
            public void a() {
                i.this.d.b();
            }

            public void a(int i) {
                if (i == 0 && i.this.i > 0 && i.this.j != null) {
                    b.a(com.facebook.ads.internal.j.a.a(i.this.i, i.this.j, a2.g()));
                    long unused = i.this.i = 0;
                    a.C0004a unused2 = i.this.j = null;
                }
            }

            public void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && i.this.e != null) {
                    i.this.e.onBannerAdClicked(i.this);
                }
                com.facebook.ads.internal.a.b a2 = com.facebook.ads.internal.a.c.a(i.this.h, i.this.g, a2.c(), parse, map);
                if (a2 != null) {
                    try {
                        a.C0004a unused = i.this.j = a2.a();
                        long unused2 = i.this.i = System.currentTimeMillis();
                        a2.b();
                    } catch (Exception e) {
                        Log.e(i.a, "Error executing action", e);
                    }
                }
            }

            public void b() {
                if (i.this.d != null) {
                    i.this.d.a();
                }
            }
        };
        this.c = new com.facebook.ads.internal.view.b.a(this.h, new WeakReference(this.b), dVar.f());
        this.c.a(dVar.h(), dVar.i());
        this.d = new q(this.h, this.g, this.c, this.c.getViewabilityChecker(), new c() {
            public void a() {
                if (i.this.e != null) {
                    i.this.e.onBannerLoggingImpression(i.this);
                }
            }
        });
        this.d.a(a2);
        this.c.loadDataWithBaseURL(com.facebook.ads.internal.q.c.b.a(), a2.d(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, (String) null);
        if (this.e != null) {
            this.e.onBannerAdLoaded(this, this.c);
        }
    }

    public void loadBannerAd(Context context, c cVar, com.facebook.ads.internal.protocol.e eVar, BannerAdapterListener bannerAdapterListener, Map<String, Object> map) {
        this.h = context;
        this.g = cVar;
        this.e = bannerAdapterListener;
        this.f = map;
        a((d) this.f.get("definition"));
    }

    public void onDestroy() {
        if (this.c != null) {
            this.c.destroy();
            this.c = null;
            this.b = null;
        }
    }
}
