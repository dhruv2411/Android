package com.facebook.ads;

import android.content.Context;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.n.a;
import com.facebook.ads.internal.n.k;
import com.facebook.ads.internal.n.l;

public class ANGenericTemplateView extends RelativeLayout {
    private final a a;

    public ANGenericTemplateView(Context context, NativeAdBase nativeAdBase, k kVar) {
        super(context);
        l lVar;
        MediaView mediaView;
        AdIconView adIconView = new AdIconView(getContext());
        if (nativeAdBase instanceof NativeAd) {
            MediaView mediaView2 = new MediaView(getContext());
            NativeAd nativeAd = (NativeAd) nativeAdBase;
            mediaView2.setNativeAd(nativeAd);
            adIconView.setNativeAd(nativeAd);
            mediaView = mediaView2;
            lVar = nativeAd.f().a();
        } else {
            NativeBannerAd nativeBannerAd = (NativeBannerAd) nativeAdBase;
            adIconView.setNativeBannerAd(nativeBannerAd);
            lVar = nativeBannerAd.a().a();
            mediaView = null;
        }
        this.a = new a(context, nativeAdBase.g(), this, new AdChoicesView(getContext(), nativeAdBase, true), mediaView, adIconView, lVar, kVar);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.a();
    }
}
