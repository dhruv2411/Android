package com.facebook.ads;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.internal.a;
import com.facebook.ads.internal.adapters.y;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.f;
import java.util.ArrayList;
import java.util.List;

public class NativeAdsManager {
    private static final String a = "NativeAdsManager";
    private static final d b = d.ADS;
    /* access modifiers changed from: private */
    public final Context c;
    private final String d;
    private final int e;
    /* access modifiers changed from: private */
    public final List<NativeAd> f;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public Listener h;
    private a i;
    private boolean j;
    /* access modifiers changed from: private */
    public boolean k;

    public interface Listener {
        void onAdError(AdError adError);

        void onAdsLoaded();
    }

    public NativeAdsManager(Context context, String str, int i2) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        this.c = context;
        this.d = str;
        this.e = Math.max(i2, 0);
        this.f = new ArrayList(i2);
        this.g = -1;
        this.k = false;
        this.j = false;
        try {
            CookieManager.getInstance();
            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(context);
            }
        } catch (Exception e2) {
            Log.w(a, "Failed to initialize CookieManager.", e2);
        }
    }

    public void disableAutoRefresh() {
        this.j = true;
        if (this.i != null) {
            this.i.c();
        }
    }

    public int getUniqueNativeAdCount() {
        return this.f.size();
    }

    public boolean isLoaded() {
        return this.k;
    }

    public void loadAds() {
        loadAds(NativeAdBase.MediaCacheFlag.ALL);
    }

    public void loadAds(final NativeAdBase.MediaCacheFlag mediaCacheFlag) {
        f fVar = f.NATIVE_UNKNOWN;
        int i2 = this.e;
        if (this.i != null) {
            this.i.b();
        }
        this.i = new a(this.c, this.d, fVar, (AdSize) null, b, i2);
        if (this.j) {
            this.i.c();
        }
        this.i.a((a.C0000a) new a.C0000a() {
            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (NativeAdsManager.this.h != null) {
                    NativeAdsManager.this.h.onAdError(AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void a(final List<y> list) {
                b bVar = new b(NativeAdsManager.this.c);
                for (y next : list) {
                    if (mediaCacheFlag.equals(NativeAdBase.MediaCacheFlag.ALL)) {
                        if (next.k() != null) {
                            bVar.a(next.k().a(), next.k().c(), next.k().b());
                        }
                        if (next.l() != null) {
                            bVar.a(next.l().a(), next.l().c(), next.l().b());
                        }
                        if (!TextUtils.isEmpty(next.A())) {
                            bVar.a(next.A());
                        }
                    }
                }
                bVar.a((com.facebook.ads.internal.d.a) new com.facebook.ads.internal.d.a() {
                    private void c() {
                        boolean unused = NativeAdsManager.this.k = true;
                        NativeAdsManager.this.f.clear();
                        int unused2 = NativeAdsManager.this.g = 0;
                        for (y nativeAd : list) {
                            NativeAdsManager.this.f.add(new NativeAd(NativeAdsManager.this.c, nativeAd, (com.facebook.ads.internal.h.d) null));
                        }
                        if (NativeAdsManager.this.h != null) {
                            NativeAdsManager.this.h.onAdsLoaded();
                        }
                    }

                    public void a() {
                        c();
                    }

                    public void b() {
                        c();
                    }
                });
            }
        });
        this.i.a();
    }

    public NativeAd nextNativeAd() {
        if (this.f.size() == 0) {
            return null;
        }
        int i2 = this.g;
        this.g = i2 + 1;
        NativeAd nativeAd = this.f.get(i2 % this.f.size());
        return i2 >= this.f.size() ? new NativeAd((NativeAdBase) nativeAd) : nativeAd;
    }

    public void setListener(Listener listener) {
        this.h = listener;
    }
}
