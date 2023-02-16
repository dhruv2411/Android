package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.NativeBannerAdView;
import com.facebook.ads.internal.adapters.y;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.protocol.f;
import java.util.List;

public class NativeBannerAd extends NativeAdBase {
    public NativeBannerAd(Context context, y yVar, d dVar) {
        super(context, yVar, dVar);
        a(f.NATIVE_BANNER);
    }

    public NativeBannerAd(Context context, String str) {
        super(context, str);
        a(f.NATIVE_BANNER);
    }

    /* access modifiers changed from: package-private */
    public NativeBannerAdView.Type a() {
        if (g().I() == null) {
            return null;
        }
        return NativeBannerAdView.Type.a(g().I());
    }

    /* access modifiers changed from: package-private */
    public void a(NativeBannerAdView.Type type) {
        g().a(type.a());
    }

    public void registerViewForInteraction(View view, AdIconView adIconView) {
        registerViewForInteraction(view, adIconView, (List<View>) null);
    }

    public void registerViewForInteraction(View view, AdIconView adIconView, @Nullable List<View> list) {
        if (adIconView != null) {
            adIconView.setNativeBannerAd(this);
        }
        if (list != null) {
            g().a(view, (g) adIconView, list);
        } else {
            g().a(view, (g) adIconView);
        }
    }
}
