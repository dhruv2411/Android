package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.h;
import com.facebook.ads.internal.l.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.m.d;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.q.a.j;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.b.b;
import com.facebook.ads.internal.view.b.e;
import com.facebook.ads.internal.view.k;

public class MediaView extends g {
    private static final String a = "MediaView";
    private static final int b = Color.argb(51, 145, 150, 165);
    private b c;
    private com.facebook.ads.internal.view.hscroll.b d;
    /* access modifiers changed from: private */
    public MediaViewVideoRenderer e;
    private View f;
    @Nullable
    private MediaViewListener g;
    private boolean h;
    private boolean i;
    private boolean j;

    public MediaView(Context context) {
        super(context);
        setImageRenderer(new b(context));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context));
        a();
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setImageRenderer(new b(context, attributeSet));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet));
        a();
    }

    public MediaView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setImageRenderer(new b(context, attributeSet, i2));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet, i2));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet, i2));
        a();
    }

    @TargetApi(21)
    public MediaView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        setImageRenderer(new b(context, attributeSet, i2, i3));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet, i2));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet, i2, i3));
        a();
    }

    private void a() {
        x.a((View) this, b);
        j.a(this, j.INTERNAL_AD_MEDIA);
        j.a(this.c, j.INTERNAL_AD_MEDIA);
        j.a(this.e, j.INTERNAL_AD_MEDIA);
        j.a(this.d, j.INTERNAL_AD_MEDIA);
        this.i = true;
    }

    private boolean a(NativeAd nativeAd) {
        return Build.VERSION.SDK_INT >= 14 && !TextUtils.isEmpty(nativeAd.a());
    }

    private boolean b(NativeAd nativeAd) {
        if (nativeAd.e() == null) {
            return false;
        }
        for (NativeAd adCoverImage : nativeAd.e()) {
            if (adCoverImage.getAdCoverImage() == null) {
                return false;
            }
        }
        return true;
    }

    private void setCarouselRenderer(com.facebook.ads.internal.view.hscroll.b bVar) {
        if (this.h) {
            throw new IllegalStateException("Carousel renderer must be set before nativeAd.");
        }
        if (this.d != null) {
            removeView(this.d);
        }
        float f2 = x.b;
        int round = Math.round(4.0f * f2);
        int round2 = Math.round(f2 * 12.0f);
        bVar.setChildSpacing(round);
        bVar.setPadding(0, round2, 0, round2);
        bVar.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView((View) bVar, (ViewGroup.LayoutParams) layoutParams);
        this.d = bVar;
    }

    private void setImageRenderer(b bVar) {
        if (this.h) {
            throw new IllegalStateException("Image renderer must be set before nativeAd.");
        }
        if (this.c != null) {
            removeView(this.c);
        }
        bVar.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView((View) bVar, (ViewGroup.LayoutParams) layoutParams);
        this.c = bVar;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        this.i = false;
        addView(view, layoutParams);
        this.i = true;
    }

    public void addView(View view) {
        if (!this.i) {
            super.addView(view);
        }
    }

    public void addView(View view, int i2) {
        if (!this.i) {
            super.addView(view, i2);
        }
    }

    public void addView(View view, int i2, int i3) {
        if (!this.i) {
            super.addView(view, i2, i3);
        }
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!this.i) {
            super.addView(view, i2, layoutParams);
        }
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (!this.i) {
            super.addView(view, layoutParams);
        }
    }

    public void bringChildToFront(View view) {
        if (view == this.d || view == this.e || view == this.c) {
            super.bringChildToFront(view);
        }
    }

    public void destroy() {
        this.e.pause(false);
        this.e.destroy();
    }

    /* access modifiers changed from: protected */
    public View getAdContentsView() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public c getAdEventManager() {
        return d.a(getContext());
    }

    public void setListener(final MediaViewListener mediaViewListener) {
        this.g = mediaViewListener;
        if (mediaViewListener == null) {
            this.e.setListener((k) null);
        } else {
            this.e.setListener(new k() {
                public void a() {
                    mediaViewListener.onVolumeChange(MediaView.this, MediaView.this.e.getVolume());
                }

                public void b() {
                    mediaViewListener.onPause(MediaView.this);
                }

                public void c() {
                    mediaViewListener.onPlay(MediaView.this);
                }

                public void d() {
                    mediaViewListener.onFullscreenBackground(MediaView.this);
                }

                public void e() {
                    mediaViewListener.onFullscreenForeground(MediaView.this);
                }

                public void f() {
                    mediaViewListener.onExitFullscreen(MediaView.this);
                }

                public void g() {
                    mediaViewListener.onEnterFullscreen(MediaView.this);
                }

                public void h() {
                    mediaViewListener.onComplete(MediaView.this);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void setNativeAd(final NativeAd nativeAd) {
        this.h = true;
        nativeAd.a(this);
        if (b(nativeAd)) {
            this.f = this.d;
            this.c.setVisibility(8);
            this.c.a((Bitmap) null, (Bitmap) null);
            this.e.setVisibility(8);
            this.e.a();
            bringChildToFront(this.d);
            this.d.setCurrentPosition(0);
            h hVar = new h(this.d, nativeAd.g().F());
            hVar.a((h.a) new h.a() {
                public void a() {
                    nativeAd.g().a(true, true);
                }
            });
            this.d.setAdapter(hVar);
            this.d.setVisibility(0);
        } else if (a(nativeAd)) {
            nativeAd.g().b(this.j);
            this.f = this.e.getVideoView();
            this.c.setVisibility(8);
            this.c.a((Bitmap) null, (Bitmap) null);
            this.d.setVisibility(8);
            this.d.setAdapter((RecyclerView.Adapter) null);
            bringChildToFront(this.e);
            this.e.setNativeAd(nativeAd);
            this.e.setVisibility(0);
        } else if (nativeAd.getAdCoverImage() != null) {
            this.f = this.c.getBodyImageView();
            this.e.setVisibility(8);
            this.e.a();
            this.d.setVisibility(8);
            this.d.setAdapter((RecyclerView.Adapter) null);
            bringChildToFront(this.c);
            this.c.setVisibility(0);
            new com.facebook.ads.internal.view.b.d(this.c).a(getHeight(), getWidth()).a(a.e(getContext())).a((e) new e() {
                public void a(boolean z) {
                    nativeAd.g().a(z, true);
                }
            }).a(nativeAd.g().k().a());
        }
    }

    public void setVideoRenderer(MediaViewVideoRenderer mediaViewVideoRenderer) {
        if (this.h) {
            throw new IllegalStateException("Video renderer must be set before nativeAd.");
        }
        if (this.e != null) {
            removeView(this.e);
            this.e.destroy();
        }
        mediaViewVideoRenderer.setAdEventManager(getAdEventManager());
        mediaViewVideoRenderer.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        a(mediaViewVideoRenderer, layoutParams);
        this.e = mediaViewVideoRenderer;
        this.j = !(this.e instanceof DefaultMediaViewVideoRenderer);
    }
}
