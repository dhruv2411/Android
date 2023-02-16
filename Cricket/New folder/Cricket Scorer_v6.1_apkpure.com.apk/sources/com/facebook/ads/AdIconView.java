package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.b.e;

public class AdIconView extends g {
    @Nullable
    private ImageView a;
    private boolean b;

    public AdIconView(Context context) {
        super(context);
        a();
    }

    public AdIconView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AdIconView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public AdIconView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        x.b(this.a);
        this.a = new ImageView(getContext());
        this.a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(this.a);
        this.b = true;
    }

    private void a(final NativeAdBase nativeAdBase, boolean z) {
        bringChildToFront(this.a);
        nativeAdBase.a(this);
        d a2 = new d(this.a).a();
        if (z) {
            a2.a((e) new e() {
                public void a(boolean z) {
                    nativeAdBase.g().a(z, true);
                }
            });
        }
        a2.a(nativeAdBase.g().j().a());
    }

    public void addView(View view) {
        if (!this.b) {
            super.addView(view);
        }
    }

    public void addView(View view, int i) {
        if (!this.b) {
            super.addView(view, i);
        }
    }

    public void addView(View view, int i, int i2) {
        if (!this.b) {
            super.addView(view, i, i2);
        }
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (!this.b) {
            super.addView(view, layoutParams);
        }
    }

    public void bringChildToFront(View view) {
        if (view == this.a) {
            super.bringChildToFront(view);
        }
    }

    @VisibleForTesting
    public void forceAddview(View view, ViewGroup.LayoutParams layoutParams) {
        this.b = false;
        addView(view, layoutParams);
        this.b = true;
    }

    /* access modifiers changed from: protected */
    public View getAdContentsView() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public void setNativeAd(NativeAd nativeAd) {
        a(nativeAd, false);
    }

    /* access modifiers changed from: package-private */
    public void setNativeBannerAd(NativeBannerAd nativeBannerAd) {
        a(nativeBannerAd, true);
    }
}
