package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.internal.adapters.y;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.protocol.f;
import java.util.ArrayList;
import java.util.List;

public class NativeAd extends NativeAdBase {
    public NativeAd(Context context, y yVar, d dVar) {
        super(context, yVar, dVar);
        a(f.NATIVE_UNKNOWN);
    }

    public NativeAd(Context context, String str) {
        super(context, str);
        a(f.NATIVE_UNKNOWN);
    }

    NativeAd(NativeAdBase nativeAdBase) {
        super(nativeAdBase);
    }

    NativeAd(com.facebook.ads.internal.n.f fVar) {
        super(fVar);
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return g().B();
    }

    /* access modifiers changed from: package-private */
    public void a(NativeAdView.Type type) {
        g().a(type.a());
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return g().C();
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return g().D();
    }

    /* access modifiers changed from: package-private */
    public VideoAutoplayBehavior d() {
        return VideoAutoplayBehavior.fromInternalAutoplayBehavior(g().E());
    }

    /* access modifiers changed from: package-private */
    public List<NativeAd> e() {
        if (g().F() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.facebook.ads.internal.n.f nativeAd : g().F()) {
            arrayList.add(new NativeAd(nativeAd));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public NativeAdView.Type f() {
        if (g().I() == null) {
            return null;
        }
        return NativeAdView.Type.a(g().I());
    }

    public void registerViewForInteraction(View view, MediaView mediaView) {
        registerViewForInteraction(view, mediaView, (AdIconView) null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView imageView) {
        registerViewForInteraction(view, mediaView, imageView, (List<View>) null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView imageView, @Nullable List<View> list) {
        if (imageView != null) {
            com.facebook.ads.internal.n.f.a(g().j(), imageView);
        }
        registerViewForInteraction(view, mediaView, (AdIconView) null, list);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable AdIconView adIconView) {
        registerViewForInteraction(view, mediaView, adIconView, (List<View>) null);
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable AdIconView adIconView, @Nullable List<View> list) {
        if (mediaView != null) {
            mediaView.setNativeAd(this);
        }
        if (adIconView != null) {
            adIconView.setNativeAd(this);
        }
        if (list != null) {
            g().a(view, (g) mediaView, list);
        } else {
            g().a(view, (g) mediaView);
        }
    }

    public void registerViewForInteraction(View view, MediaView mediaView, @Nullable List<View> list) {
        registerViewForInteraction(view, mediaView, (AdIconView) null, list);
    }
}
