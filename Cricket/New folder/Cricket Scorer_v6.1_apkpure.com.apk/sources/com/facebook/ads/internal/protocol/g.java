package com.facebook.ads.internal.protocol;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.h;
import java.util.HashMap;
import java.util.Map;

public class g {
    private static final Map<e, f> a = new HashMap();

    static {
        a.put(e.RECTANGLE_HEIGHT_250, f.WEBVIEW_BANNER_250);
        a.put(e.BANNER_HEIGHT_90, f.WEBVIEW_BANNER_90);
        a.put(e.BANNER_HEIGHT_50, f.WEBVIEW_BANNER_50);
    }

    public static f a(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
        int i2 = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        return h.a(i, i2) ? f.WEBVIEW_INTERSTITIAL_TABLET : i2 > i ? f.WEBVIEW_INTERSTITIAL_VERTICAL : f.WEBVIEW_INTERSTITIAL_HORIZONTAL;
    }

    public static f a(e eVar) {
        f fVar = a.get(eVar);
        return fVar == null ? f.WEBVIEW_BANNER_LEGACY : fVar;
    }

    public static void a(DisplayMetrics displayMetrics, View view, e eVar) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int) (((float) displayMetrics.widthPixels) / displayMetrics.density)) >= eVar.a() ? displayMetrics.widthPixels : (int) Math.ceil((double) (((float) eVar.a()) * displayMetrics.density)), (int) Math.ceil((double) (((float) eVar.b()) * displayMetrics.density)));
        layoutParams.addRule(14, -1);
        view.setLayoutParams(layoutParams);
    }
}
