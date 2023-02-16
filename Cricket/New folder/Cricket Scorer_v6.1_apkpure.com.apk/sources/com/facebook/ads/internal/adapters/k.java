package com.facebook.ads.internal.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.settings.a;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONObject;

public class k extends InterstitialAdapter {
    private static final ConcurrentMap<String, com.facebook.ads.internal.view.a> a = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public final String b = UUID.randomUUID().toString();
    private String c;
    private long d;
    private Context e;
    private t f;
    /* access modifiers changed from: private */
    public InterstitialAdapterListener g;
    /* access modifiers changed from: private */
    public boolean h = false;
    private p i;
    /* access modifiers changed from: private */
    public a j = a.UNSPECIFIED;
    private g k;
    private a.C0007a l;
    /* access modifiers changed from: private */
    public boolean m;

    public enum a {
        UNSPECIFIED,
        VERTICAL,
        HORIZONTAL;

        public static a a(int i) {
            return i == 0 ? UNSPECIFIED : i == 2 ? HORIZONTAL : VERTICAL;
        }
    }

    private int a() {
        int rotation = ((WindowManager) this.e.getSystemService("window")).getDefaultDisplay().getRotation();
        if (this.j == a.UNSPECIFIED) {
            return -1;
        }
        if (this.j != a.HORIZONTAL) {
            return rotation != 2 ? 1 : 9;
        }
        switch (rotation) {
            case 2:
            case 3:
                return 8;
            default:
                return 0;
        }
    }

    public static com.facebook.ads.internal.view.a a(String str) {
        return (com.facebook.ads.internal.view.a) a.get(str);
    }

    public static void a(com.facebook.ads.internal.view.a aVar) {
        for (Map.Entry entry : a.entrySet()) {
            if (entry.getValue() == aVar) {
                a.remove(entry.getKey());
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str, com.facebook.ads.internal.view.a aVar) {
        a.put(str, aVar);
    }

    public void loadInterstitialAd(Context context, InterstitialAdapterListener interstitialAdapterListener, Map<String, Object> map, c cVar, final EnumSet<CacheFlag> enumSet) {
        b bVar;
        com.facebook.ads.internal.d.a aVar;
        this.e = context;
        this.g = interstitialAdapterListener;
        this.c = (String) map.get(AudienceNetworkActivity.PLACEMENT_ID);
        this.d = ((Long) map.get(AudienceNetworkActivity.REQUEST_TIME)).longValue();
        JSONObject jSONObject = (JSONObject) map.get("data");
        d dVar = (d) map.get("definition");
        if (jSONObject.has("markup")) {
            this.l = a.C0007a.INTERSTITIAL_WEB_VIEW;
            this.i = p.a(jSONObject);
            if (e.a(context, this.i, cVar)) {
                interstitialAdapterListener.onInterstitialError(this, AdError.NO_FILL);
                return;
            }
            this.f = new t(context, this.b, this, this.g);
            this.f.a();
            Map<String, String> f2 = this.i.f();
            if (f2.containsKey("orientation")) {
                this.j = a.a(Integer.parseInt(f2.get("orientation")));
            }
            this.h = true;
            if (this.g != null) {
                this.g.onInterstitialAdLoaded(this);
            }
        } else if (jSONObject.has(MimeTypes.BASE_TYPE_VIDEO)) {
            this.l = a.C0007a.INTERSTITIAL_OLD_NATIVE_VIDEO;
            this.f = new t(context, this.b, this, this.g);
            this.f.a();
            final l lVar = new l();
            lVar.a(context, (com.facebook.ads.a.a) new com.facebook.ads.a.a() {
                public void a(s sVar) {
                    boolean unused = k.this.h = true;
                    if (k.this.g != null) {
                        k.this.g.onInterstitialAdLoaded(k.this);
                    }
                }

                public void a(s sVar, View view) {
                    a unused = k.this.j = lVar.k();
                    k.b(k.this.b, (com.facebook.ads.internal.view.a) lVar);
                }

                public void a(s sVar, AdError adError) {
                    lVar.l();
                    k.this.g.onInterstitialError(k.this, adError);
                }

                public void b(s sVar) {
                    k.this.g.onInterstitialAdClicked(k.this, "", true);
                }

                public void c(s sVar) {
                    k.this.g.onInterstitialLoggingImpression(k.this);
                }

                public void d(s sVar) {
                }
            }, map, cVar, enumSet);
        } else {
            this.k = g.a(jSONObject, context);
            if (dVar != null) {
                this.k.a(dVar.k());
            }
            if (this.k.d().size() == 0) {
                this.g.onInterstitialError(this, AdError.NO_FILL);
            }
            this.f = new t(context, this.b, this, this.g);
            this.f.a();
            if (jSONObject.has("carousel")) {
                this.l = a.C0007a.INTERSTITIAL_NATIVE_CAROUSEL;
                bVar = new b(context);
                bVar.a(this.k.a().b(), -1, -1);
                List<h> d2 = this.k.d();
                boolean contains = enumSet.contains(CacheFlag.VIDEO);
                for (h next : d2) {
                    bVar.a(next.c().g(), next.c().i(), next.c().h());
                    if (contains && !TextUtils.isEmpty(next.c().a())) {
                        bVar.a(next.c().g());
                    }
                }
                aVar = new com.facebook.ads.internal.d.a() {
                    private void a(boolean z) {
                        boolean unused = k.this.m = z && (enumSet.contains(CacheFlag.NONE) ^ true);
                        boolean unused2 = k.this.h = true;
                        k.this.g.onInterstitialAdLoaded(k.this);
                    }

                    public void a() {
                        a(true);
                    }

                    public void b() {
                        a(false);
                    }
                };
            } else if (jSONObject.has("video_url")) {
                this.l = a.C0007a.INTERSTITIAL_NATIVE_VIDEO;
                bVar = new b(context);
                com.facebook.ads.internal.adapters.a.b c2 = this.k.d().get(0).c();
                bVar.a(c2.g(), c2.i(), c2.h());
                bVar.a(this.k.a().b(), -1, -1);
                if (enumSet.contains(CacheFlag.VIDEO)) {
                    bVar.a(c2.a());
                }
                aVar = new com.facebook.ads.internal.d.a() {
                    private void a(boolean z) {
                        boolean unused = k.this.m = z;
                        boolean unused2 = k.this.h = true;
                        k.this.g.onInterstitialAdLoaded(k.this);
                    }

                    public void a() {
                        a(enumSet.contains(CacheFlag.VIDEO));
                    }

                    public void b() {
                        a(false);
                    }
                };
            } else {
                this.l = a.C0007a.INTERSTITIAL_NATIVE_IMAGE;
                bVar = new b(context);
                com.facebook.ads.internal.adapters.a.b c3 = this.k.d().get(0).c();
                bVar.a(c3.g(), c3.i(), c3.h());
                bVar.a(this.k.a().b(), -1, -1);
                aVar = new com.facebook.ads.internal.d.a() {
                    private void c() {
                        boolean unused = k.this.h = true;
                        k.this.g.onInterstitialAdLoaded(k.this);
                    }

                    public void a() {
                        c();
                    }

                    public void b() {
                        c();
                    }
                };
            }
            bVar.a(aVar);
        }
    }

    public void onDestroy() {
        if (this.f != null) {
            this.f.b();
        }
    }

    public boolean show() {
        if (this.h) {
            Intent intent = new Intent(this.e, AudienceNetworkActivity.class);
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, a());
            intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.b);
            intent.putExtra(AudienceNetworkActivity.PLACEMENT_ID, this.c);
            intent.putExtra(AudienceNetworkActivity.REQUEST_TIME, this.d);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, this.l);
            intent.putExtra(AudienceNetworkActivity.USE_CACHE, this.m);
            if (this.k != null) {
                intent.putExtra("ad_data_bundle", this.k);
            } else if (this.i != null) {
                this.i.a(intent);
            }
            intent.addFlags(268435456);
            try {
                this.e.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException unused) {
                intent.setClass(this.e, InterstitialAdActivity.class);
                this.e.startActivity(intent);
                return true;
            }
        } else if (this.g == null) {
            return false;
        } else {
            this.g.onInterstitialError(this, AdError.INTERNAL_ERROR);
            return false;
        }
    }
}
