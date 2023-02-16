package com.facebook.ads;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.q.d.a;
import com.facebook.ads.internal.q.d.b;
import java.util.EnumSet;

public class InterstitialAd implements Ad {
    private static final d a = d.ADS;
    private final Context b;
    private final String c;
    /* access modifiers changed from: private */
    public DisplayAdController d;
    /* access modifiers changed from: private */
    public boolean e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    public InterstitialAdListener g;

    public InterstitialAd(Context context, String str) {
        this.b = context;
        this.c = str;
    }

    private void a(EnumSet<CacheFlag> enumSet, String str) {
        this.e = false;
        if (this.f) {
            a.a(this.b, "api", b.f, (Exception) new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_LOAD, "Interstitial load called while showing interstitial."));
            if (this.g != null) {
                this.g.onError(this, new AdError(AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getErrorCode(), AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getDefaultErrorMessage()));
                return;
            }
            return;
        }
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
        this.d = new DisplayAdController(this.b, this.c, g.a(this.b.getResources().getDisplayMetrics()), AdPlacementType.INTERSTITIAL, e.INTERSTITIAL, a, 1, true, enumSet);
        this.d.a((com.facebook.ads.internal.adapters.a) new com.facebook.ads.internal.adapters.a() {
            public void a() {
                if (InterstitialAd.this.g != null) {
                    InterstitialAd.this.g.onAdClicked(InterstitialAd.this);
                }
            }

            public void a(View view) {
            }

            public void a(AdAdapter adAdapter) {
                boolean unused = InterstitialAd.this.e = true;
                if (InterstitialAd.this.g != null) {
                    InterstitialAd.this.g.onAdLoaded(InterstitialAd.this);
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (InterstitialAd.this.g != null) {
                    InterstitialAd.this.g.onError(InterstitialAd.this, AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void b() {
                if (InterstitialAd.this.g != null) {
                    InterstitialAd.this.g.onLoggingImpression(InterstitialAd.this);
                }
            }

            public void d() {
                if (InterstitialAd.this.g != null) {
                    InterstitialAd.this.g.onInterstitialDisplayed(InterstitialAd.this);
                }
            }

            public void e() {
                boolean unused = InterstitialAd.this.f = false;
                if (InterstitialAd.this.d != null) {
                    InterstitialAd.this.d.c();
                    DisplayAdController unused2 = InterstitialAd.this.d = null;
                }
                if (InterstitialAd.this.g != null) {
                    InterstitialAd.this.g.onInterstitialDismissed(InterstitialAd.this);
                }
            }

            public void f() {
                if (InterstitialAd.this.g instanceof InterstitialAdExtendedListener) {
                    ((InterstitialAdExtendedListener) InterstitialAd.this.g).onInterstitialActivityDestroyed();
                }
            }
        });
        this.d.a(str);
    }

    public void destroy() {
        if (this.d != null) {
            this.d.b(true);
            this.d = null;
        }
    }

    public String getPlacementId() {
        return this.c;
    }

    public boolean isAdInvalidated() {
        return this.d == null || this.d.d();
    }

    public boolean isAdLoaded() {
        return this.e;
    }

    public void loadAd() {
        loadAd(EnumSet.of(CacheFlag.NONE));
    }

    public void loadAd(EnumSet<CacheFlag> enumSet) {
        a(enumSet, (String) null);
    }

    public void loadAdFromBid(String str) {
        a((EnumSet<CacheFlag>) EnumSet.of(CacheFlag.NONE), str);
    }

    public void loadAdFromBid(EnumSet<CacheFlag> enumSet, String str) {
        a(enumSet, str);
    }

    public void setAdListener(InterstitialAdListener interstitialAdListener) {
        this.g = interstitialAdListener;
    }

    public boolean show() {
        if (!this.e) {
            if (this.g != null) {
                this.g.onError(this, AdError.INTERNAL_ERROR);
            }
            return false;
        } else if (this.d == null) {
            a.a(this.b, "api", b.g, (Exception) new com.facebook.ads.internal.protocol.b(AdErrorType.INTERSTITIAL_CONTROLLER_IS_NULL, AdErrorType.INTERSTITIAL_CONTROLLER_IS_NULL.getDefaultErrorMessage()));
            if (this.g != null) {
                this.g.onError(this, AdError.INTERNAL_ERROR);
            }
            return false;
        } else {
            this.d.b();
            this.f = true;
            this.e = false;
            return true;
        }
    }
}
