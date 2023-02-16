package com.facebook.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.a.a;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.j;
import com.facebook.ads.internal.adapters.s;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.f;
import com.facebook.ads.internal.view.b.c;
import java.util.EnumSet;

public class InstreamVideoAdView extends RelativeLayout implements Ad {
    /* access modifiers changed from: private */
    public final Context a;
    /* access modifiers changed from: private */
    public final String b;
    private final AdSize c;
    /* access modifiers changed from: private */
    public DisplayAdController d;
    @Nullable
    private j e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    @Nullable
    public InstreamVideoAdListener g;
    /* access modifiers changed from: private */
    @Nullable
    public View h;
    @Nullable
    private Bundle i;
    /* access modifiers changed from: private */
    @Nullable
    public c j;

    public InstreamVideoAdView(Context context, Bundle bundle) {
        this(context, bundle.getString("placementID"), (AdSize) bundle.get("adSize"));
        this.i = bundle;
    }

    public InstreamVideoAdView(Context context, String str, AdSize adSize) {
        super(context);
        this.f = false;
        this.a = context;
        this.b = str;
        this.c = adSize;
        this.d = getController();
    }

    private final void a() {
        if (this.d != null) {
            this.d.b(true);
            this.d = null;
            this.d = getController();
            this.e = null;
            this.f = false;
            removeAllViews();
        }
    }

    private void a(String str) {
        if (this.i != null) {
            this.e = new j();
            this.e.a(getContext(), (a) new a() {
                public void a(s sVar) {
                    boolean unused = InstreamVideoAdView.this.f = true;
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdLoaded(InstreamVideoAdView.this);
                    }
                }

                public void a(s sVar, View view) {
                    if (view == null) {
                        throw new IllegalStateException("Cannot present null view");
                    }
                    View unused = InstreamVideoAdView.this.h = view;
                    InstreamVideoAdView.this.removeAllViews();
                    InstreamVideoAdView.this.h.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                    InstreamVideoAdView.this.addView(InstreamVideoAdView.this.h);
                }

                public void a(s sVar, AdError adError) {
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onError(InstreamVideoAdView.this, adError);
                    }
                }

                public void b(s sVar) {
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdClicked(InstreamVideoAdView.this);
                    }
                }

                public void c(s sVar) {
                }

                public void d(s sVar) {
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdVideoComplete(InstreamVideoAdView.this);
                    }
                }
            }, this.d.e(), this.i.getBundle("adapter"), (EnumSet<CacheFlag>) EnumSet.of(CacheFlag.NONE));
            return;
        }
        this.d.a(str);
    }

    private DisplayAdController getController() {
        this.d = new DisplayAdController(getContext(), this.b, f.INSTREAM_VIDEO, AdPlacementType.INSTREAM, this.c.toInternalAdSize(), d.ADS, 1, true);
        this.d.a((com.facebook.ads.internal.adapters.a) new com.facebook.ads.internal.adapters.a() {
            public void a() {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onAdClicked(InstreamVideoAdView.this);
                }
            }

            public void a(View view) {
                if (view == null) {
                    throw new IllegalStateException("Cannot present null view");
                }
                View unused = InstreamVideoAdView.this.h = view;
                InstreamVideoAdView.this.removeAllViews();
                InstreamVideoAdView.this.h.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                InstreamVideoAdView.this.addView(InstreamVideoAdView.this.h);
                if (com.facebook.ads.internal.l.a.b(InstreamVideoAdView.this.a)) {
                    c unused2 = InstreamVideoAdView.this.j = new c();
                    InstreamVideoAdView.this.j.a(InstreamVideoAdView.this.b);
                    InstreamVideoAdView.this.j.b(InstreamVideoAdView.this.a.getPackageName());
                    if (InstreamVideoAdView.this.d.a() != null) {
                        InstreamVideoAdView.this.j.a(InstreamVideoAdView.this.d.a().a());
                    }
                    InstreamVideoAdView.this.h.getOverlay().add(InstreamVideoAdView.this.j);
                    InstreamVideoAdView.this.h.setOnLongClickListener(new View.OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            if (InstreamVideoAdView.this.h == null || InstreamVideoAdView.this.j == null) {
                                return false;
                            }
                            InstreamVideoAdView.this.j.setBounds(0, 0, InstreamVideoAdView.this.h.getWidth(), InstreamVideoAdView.this.h.getHeight());
                            InstreamVideoAdView.this.j.a(!InstreamVideoAdView.this.j.a());
                            return true;
                        }
                    });
                }
            }

            public void a(AdAdapter adAdapter) {
                if (InstreamVideoAdView.this.d != null) {
                    boolean unused = InstreamVideoAdView.this.f = true;
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdLoaded(InstreamVideoAdView.this);
                    }
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onError(InstreamVideoAdView.this, AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void b() {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onLoggingImpression(InstreamVideoAdView.this);
                }
            }

            public void c() {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onAdVideoComplete(InstreamVideoAdView.this);
                }
            }
        });
        return this.d;
    }

    public void destroy() {
        if (this.j != null && com.facebook.ads.internal.l.a.b(this.a)) {
            this.j.b();
            if (this.h != null) {
                this.h.getOverlay().remove(this.j);
            }
        }
        a();
    }

    public String getPlacementId() {
        return this.b;
    }

    public Bundle getSaveInstanceState() {
        Bundle g2;
        s sVar = this.e != null ? this.e : (s) this.d.f();
        if (sVar == null || (g2 = sVar.g()) == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("adapter", g2);
        bundle.putString("placementID", this.b);
        bundle.putSerializable("adSize", this.c);
        return bundle;
    }

    public boolean isAdInvalidated() {
        return this.d == null || this.d.d();
    }

    public boolean isAdLoaded() {
        return this.f;
    }

    public void loadAd() {
        a((String) null);
    }

    public void loadAdFromBid(String str) {
        a(str);
    }

    public void setAdListener(InstreamVideoAdListener instreamVideoAdListener) {
        this.g = instreamVideoAdListener;
    }

    public boolean show() {
        if (!this.f || (this.d == null && this.e == null)) {
            if (this.g != null) {
                this.g.onError(this, AdError.INTERNAL_ERROR);
            }
            return false;
        }
        if (this.e != null) {
            this.e.e();
        } else {
            this.d.b();
        }
        this.f = false;
        return true;
    }
}
