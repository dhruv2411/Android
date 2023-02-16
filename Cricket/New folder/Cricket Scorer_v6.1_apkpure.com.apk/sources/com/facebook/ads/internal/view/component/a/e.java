package com.facebook.ads.internal.view.component.a;

import android.content.res.Resources;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.h;

public class e extends b {
    private static final int c = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final f d;

    public e(d dVar, boolean z, d dVar2) {
        super(dVar, dVar2, z);
        this.d = new f(dVar.a(), dVar.d());
        this.d.a(dVar.h(), dVar.i(), getTitleDescContainer(), z);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        layoutParams.setMargins(a, a, a, a);
        getCtaButton().setLayoutParams(layoutParams);
        FrameLayout frameLayout = new FrameLayout(dVar.a());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(2, getCtaButton().getId());
        frameLayout.setLayoutParams(layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams3.gravity = 17;
        layoutParams3.setMargins(a, 0, a, 0);
        frameLayout.addView(this.d, layoutParams3);
        addView(frameLayout);
        addView(getCtaButton());
    }

    public void a(h hVar, String str, double d2) {
        super.a(hVar, str, d2);
        if (d2 > 0.0d) {
            this.d.a((int) (((double) (c - (a * 2))) / d2));
        }
    }

    public boolean a() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return false;
    }
}
