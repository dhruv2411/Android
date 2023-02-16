package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.adapters.y;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.h;
import com.facebook.ads.internal.n.i;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.q.a.j;
import com.facebook.ads.internal.view.hscroll.b;
import org.json.JSONObject;

public abstract class NativeAdBase implements Ad {
    private final f a;

    public static class Image {
        private final h a;

        Image(h hVar) {
            this.a = hVar;
        }

        public Image(String str, int i, int i2) {
            this.a = new h(str, i, i2);
        }

        @Nullable
        public static Image fromJSONObject(JSONObject jSONObject) {
            h a2 = h.a(jSONObject);
            if (a2 == null) {
                return null;
            }
            return new Image(a2);
        }

        public int getHeight() {
            return this.a.c();
        }

        public int getWidth() {
            return this.a.b();
        }
    }

    public enum MediaCacheFlag {
        NONE(e.NONE),
        ALL(e.ALL);
        
        private final e a;

        private MediaCacheFlag(e eVar) {
            this.a = eVar;
        }

        /* access modifiers changed from: package-private */
        public e a() {
            return this.a;
        }

        public long getCacheFlagValue() {
            return this.a.a();
        }
    }

    public enum NativeComponentTag {
        AD_ICON(j.INTERNAL_AD_ICON),
        AD_TITLE(j.INTERNAL_AD_TITLE),
        AD_COVER_IMAGE(j.INTERNAL_AD_COVER_IMAGE),
        AD_SUBTITLE(j.INTERNAL_AD_SUBTITLE),
        AD_BODY(j.INTERNAL_AD_BODY),
        AD_CALL_TO_ACTION(j.INTERNAL_AD_CALL_TO_ACTION),
        AD_SOCIAL_CONTEXT(j.INTERNAL_AD_SOCIAL_CONTEXT),
        AD_CHOICES_ICON(j.INTERNAL_AD_CHOICES_ICON),
        AD_MEDIA(j.INTERNAL_AD_MEDIA);
        
        private final j a;

        private NativeComponentTag(j jVar) {
            this.a = jVar;
        }

        public static void tagView(View view, NativeComponentTag nativeComponentTag) {
            if (view != null && nativeComponentTag != null) {
                j.a(view, nativeComponentTag.a);
            }
        }
    }

    public static class Rating {
        private final com.facebook.ads.internal.n.j a;

        public Rating(double d, double d2) {
            this.a = new com.facebook.ads.internal.n.j(d, d2);
        }

        Rating(com.facebook.ads.internal.n.j jVar) {
            this.a = jVar;
        }

        @Nullable
        public static Rating fromJSONObject(JSONObject jSONObject) {
            com.facebook.ads.internal.n.j a2 = com.facebook.ads.internal.n.j.a(jSONObject);
            if (a2 == null) {
                return null;
            }
            return new Rating(a2);
        }

        public double getScale() {
            return this.a.b();
        }

        public double getValue() {
            return this.a.a();
        }
    }

    public NativeAdBase(Context context, y yVar, d dVar) {
        this.a = new f(context, yVar, dVar, getViewTraversalPredicate());
    }

    public NativeAdBase(Context context, String str) {
        this.a = new f(context, str, getViewTraversalPredicate());
    }

    NativeAdBase(NativeAdBase nativeAdBase) {
        this.a = new f(nativeAdBase.a);
    }

    NativeAdBase(f fVar) {
        this.a = fVar;
    }

    public static f.c getViewTraversalPredicate() {
        return new f.c() {
            public boolean a(View view) {
                return (view instanceof MediaViewVideoRenderer) || (view instanceof AdChoicesView) || (view instanceof b);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void a(AdIconView adIconView) {
        if (adIconView != null) {
            this.a.d(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(MediaView mediaView) {
        if (mediaView != null) {
            this.a.c(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(com.facebook.ads.internal.protocol.f fVar) {
        this.a.a(fVar);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.a.a(z);
    }

    public void destroy() {
        this.a.e();
    }

    public void downloadMedia() {
        this.a.d();
    }

    /* access modifiers changed from: package-private */
    public f g() {
        return this.a;
    }

    public String getAdBodyText() {
        return this.a.o();
    }

    public String getAdCallToAction() {
        return this.a.q();
    }

    public Image getAdChoicesIcon() {
        if (this.a.y() == null) {
            return null;
        }
        return new Image(this.a.y());
    }

    @Nullable
    public String getAdChoicesImageUrl() {
        if (this.a.y() == null) {
            return null;
        }
        return this.a.y().a();
    }

    public String getAdChoicesLinkUrl() {
        return this.a.z();
    }

    public String getAdChoicesText() {
        return this.a.A();
    }

    public Image getAdCoverImage() {
        if (this.a.k() == null) {
            return null;
        }
        return new Image(this.a.k());
    }

    public String getAdHeadline() {
        return this.a.n();
    }

    public Image getAdIcon() {
        if (this.a.j() == null) {
            return null;
        }
        return new Image(this.a.j());
    }

    public String getAdLinkDescription() {
        return this.a.s();
    }

    @Nullable
    public AdNetwork getAdNetwork() {
        return AdNetwork.fromInternalAdNetwork(this.a.b());
    }

    public String getAdSocialContext() {
        return this.a.r();
    }

    @Deprecated
    public Rating getAdStarRating() {
        if (this.a.w() == null) {
            return null;
        }
        return new Rating(this.a.w());
    }

    public String getAdTranslation() {
        return this.a.u();
    }

    public String getAdUntrimmedBodyText() {
        return this.a.p();
    }

    public NativeAdViewAttributes getAdViewAttributes() {
        if (this.a.l() == null) {
            return null;
        }
        return new NativeAdViewAttributes(this.a.l());
    }

    public String getAdvertiserName() {
        return this.a.m();
    }

    public String getId() {
        return this.a.x();
    }

    public String getPlacementId() {
        return this.a.f();
    }

    public String getPromotedTranslation() {
        return this.a.v();
    }

    public String getSponsoredTranslation() {
        return this.a.t();
    }

    /* access modifiers changed from: package-private */
    public y h() {
        return this.a.a();
    }

    public boolean hasCallToAction() {
        return this.a.i();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public String i() {
        return this.a.G();
    }

    public boolean isAdInvalidated() {
        return this.a.c();
    }

    public boolean isAdLoaded() {
        return this.a.g();
    }

    public boolean isNativeConfigEnabled() {
        return this.a.h();
    }

    public void loadAd() {
        loadAd(MediaCacheFlag.ALL);
    }

    public void loadAd(MediaCacheFlag mediaCacheFlag) {
        this.a.a(mediaCacheFlag.a(), (String) null);
    }

    public void loadAdFromBid(String str) {
        loadAdFromBid(str, MediaCacheFlag.ALL);
    }

    public void loadAdFromBid(String str, MediaCacheFlag mediaCacheFlag) {
        this.a.a(mediaCacheFlag.a(), str);
    }

    public void onCtaBroadcast() {
        this.a.H();
    }

    public void setAdListener(final NativeAdListener nativeAdListener) {
        if (nativeAdListener != null) {
            this.a.a((i) new i() {
                public void a() {
                    nativeAdListener.onMediaDownloaded(NativeAdBase.this);
                }

                public void a(a aVar) {
                    nativeAdListener.onError(NativeAdBase.this, AdError.getAdErrorFromWrapper(aVar));
                }

                public void b() {
                    nativeAdListener.onAdLoaded(NativeAdBase.this);
                }

                public void c() {
                    nativeAdListener.onAdClicked(NativeAdBase.this);
                }

                public void d() {
                    nativeAdListener.onLoggingImpression(NativeAdBase.this);
                }
            });
        }
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.a.a(onTouchListener);
    }

    public void unregisterView() {
        this.a.J();
    }
}
