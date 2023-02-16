package com.facebook.ads;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.view.b.c;

public class AdView extends RelativeLayout implements Ad {
    private static final d a = d.ADS;
    /* access modifiers changed from: private */
    public final DisplayMetrics b;
    /* access modifiers changed from: private */
    public final e c;
    private final String d;
    /* access modifiers changed from: private */
    public DisplayAdController e;
    /* access modifiers changed from: private */
    public AdListener f;
    /* access modifiers changed from: private */
    public View g;
    /* access modifiers changed from: private */
    public c h;

    public AdView(Context context, final String str, AdSize adSize) {
        super(context);
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        this.b = getContext().getResources().getDisplayMetrics();
        this.c = adSize.toInternalAdSize();
        this.d = str;
        this.e = new DisplayAdController(context, str, g.a(this.c), AdPlacementType.BANNER, adSize.toInternalAdSize(), a, 1, true);
        this.e.a((a) new a() {
            public void a() {
                if (AdView.this.f != null) {
                    AdView.this.f.onAdClicked(AdView.this);
                }
            }

            public void a(View view) {
                if (view == null) {
                    throw new IllegalStateException("Cannot present null view");
                }
                View unused = AdView.this.g = view;
                AdView.this.removeAllViews();
                AdView.this.addView(AdView.this.g);
                if (AdView.this.g instanceof com.facebook.ads.internal.view.b.a) {
                    g.a(AdView.this.b, AdView.this.g, AdView.this.c);
                }
                if (AdView.this.f != null) {
                    AdView.this.f.onAdLoaded(AdView.this);
                }
                if (com.facebook.ads.internal.l.a.b(AdView.this.getContext())) {
                    c unused2 = AdView.this.h = new c();
                    AdView.this.h.a(str);
                    AdView.this.h.b(AdView.this.getContext().getPackageName());
                    if (AdView.this.e.a() != null) {
                        AdView.this.h.a(AdView.this.e.a().a());
                    }
                    if (AdView.this.g instanceof com.facebook.ads.internal.view.b.a) {
                        AdView.this.h.a(((com.facebook.ads.internal.view.b.a) AdView.this.g).getViewabilityChecker());
                    }
                    AdView.this.g.setOnLongClickListener(new View.OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            AdView.this.h.setBounds(0, 0, AdView.this.g.getWidth(), AdView.this.g.getHeight());
                            AdView.this.h.a(!AdView.this.h.a());
                            return true;
                        }
                    });
                    AdView.this.g.getOverlay().add(AdView.this.h);
                }
            }

            public void a(AdAdapter adAdapter) {
                if (AdView.this.e != null) {
                    AdView.this.e.b();
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (AdView.this.f != null) {
                    AdView.this.f.onError(AdView.this, AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void b() {
                if (AdView.this.f != null) {
                    AdView.this.f.onLoggingImpression(AdView.this);
                }
            }
        });
    }

    private void a(String str) {
        this.e.a(str);
    }

    public void destroy() {
        if (this.e != null) {
            this.e.b(true);
            this.e = null;
        }
        if (this.h != null && com.facebook.ads.internal.l.a.b(getContext())) {
            this.h.b();
            this.g.getOverlay().remove(this.h);
        }
        removeAllViews();
        this.g = null;
        this.f = null;
    }

    @Deprecated
    public void disableAutoRefresh() {
    }

    public String getPlacementId() {
        return this.d;
    }

    public boolean isAdInvalidated() {
        return this.e == null || this.e.d();
    }

    public void loadAd() {
        a((String) null);
    }

    public void loadAdFromBid(String str) {
        a(str);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.g != null) {
            g.a(this.b, this.g, this.c);
        }
    }

    public void setAdListener(AdListener adListener) {
        this.f = adListener;
    }
}
