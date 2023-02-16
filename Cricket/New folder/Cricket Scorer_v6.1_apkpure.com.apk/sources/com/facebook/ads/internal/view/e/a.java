package com.facebook.ads.internal.view.e;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.component.e;
import com.facebook.ads.internal.view.component.h;
import com.facebook.ads.internal.view.f.b.z;
import java.util.HashMap;

public class a extends LinearLayout {
    private static final int a = ((int) (12.0f * x.b));
    private static final int b = ((int) (16.0f * x.b));
    private final h c;
    private final ImageView d;
    private final RelativeLayout e;
    private final com.facebook.ads.internal.view.component.a f;
    private final int g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(Context context, int i, d dVar, c cVar, a.C0008a aVar, boolean z, boolean z2, com.facebook.ads.internal.r.a aVar2, u uVar) {
        super(context);
        Context context2 = context;
        int i2 = i;
        this.g = i2;
        this.d = new e(context2);
        x.a((View) this.d, 0);
        x.a((View) this.d);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, i2);
        layoutParams.addRule(15);
        layoutParams.addRule(9);
        layoutParams.setMargins(0, 0, a, 0);
        if (z2) {
            this.d.setVisibility(8);
        }
        this.c = new h(context2, dVar, true, z, true);
        this.c.setAlignment(GravityCompat.START);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, this.d.getId());
        layoutParams2.addRule(15);
        com.facebook.ads.internal.view.component.a aVar3 = r1;
        com.facebook.ads.internal.view.component.a aVar4 = new com.facebook.ads.internal.view.component.a(context2, true, false, z.REWARDED_VIDEO_AD_CLICK.a(), dVar, cVar, aVar, aVar2, uVar);
        this.f = aVar3;
        this.f.setVisibility(8);
        this.e = new RelativeLayout(context2);
        x.a((View) this.e);
        this.e.addView(this.d, layoutParams);
        this.e.addView(this.c, layoutParams2);
        addView(this.e, new LinearLayout.LayoutParams(-2, -2));
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        x.a((View) this, (Drawable) gradientDrawable);
    }

    public void a() {
        this.f.setVisibility(0);
    }

    public void a(int i) {
        x.b(this.f);
        int i2 = 1;
        if (i != 1) {
            i2 = 0;
        }
        setOrientation(i2);
        int i3 = -1;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2 != 0 ? -1 : 0, -2);
        layoutParams.weight = 1.0f;
        if (i2 == 0) {
            i3 = -2;
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i3, -2);
        layoutParams2.setMargins(i2 != 0 ? 0 : b, i2 != 0 ? b : 0, 0, 0);
        layoutParams2.gravity = 80;
        this.e.setLayoutParams(layoutParams);
        addView(this.f, layoutParams2);
    }

    public void setInfo(k kVar) {
        this.c.a(kVar.b().a(), kVar.b().b(), false, false);
        this.f.a(kVar.c(), kVar.g(), new HashMap());
        new com.facebook.ads.internal.view.b.d(this.d).a(this.g, this.g).a(kVar.a().b());
    }
}
