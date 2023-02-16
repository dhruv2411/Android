package com.facebook.ads.internal.view.component.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.component.h;

final class f extends RelativeLayout {
    private final View a;
    private final com.facebook.ads.internal.view.component.f b;

    public f(Context context, View view) {
        super(context);
        this.a = view;
        this.b = new com.facebook.ads.internal.view.component.f(context);
        x.a((View) this.b);
    }

    public void a(int i) {
        this.a.setLayoutParams(new RelativeLayout.LayoutParams(-1, i));
    }

    public void a(@Nullable View view, @Nullable View view2, @Nullable h hVar, boolean z) {
        this.b.addView(this.a, new RelativeLayout.LayoutParams(-1, -2));
        if (view2 != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(b.b, b.b);
            layoutParams.addRule(6, this.a.getId());
            layoutParams.addRule(7, this.a.getId());
            layoutParams.setMargins(b.a, b.a, b.a, b.a);
            this.b.addView(view2, layoutParams);
        }
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(8, this.a.getId());
        if (hVar != null) {
            if (z) {
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                hVar.setAlignment(3);
                layoutParams3.setMargins(b.a / 2, b.a / 2, b.a / 2, b.a / 2);
                linearLayout.addView(hVar, layoutParams3);
                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
                gradientDrawable.setCornerRadius(0.0f);
                gradientDrawable.setGradientType(0);
                x.a((View) linearLayout, (Drawable) gradientDrawable);
            } else {
                RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams4.addRule(3, this.b.getId());
                layoutParams4.setMargins(0, b.a, 0, 0);
                hVar.setAlignment(17);
                addView(hVar, layoutParams4);
            }
        }
        if (view != null) {
            linearLayout.addView(view, new RelativeLayout.LayoutParams(-1, -2));
        }
        this.b.addView(linearLayout, layoutParams2);
        addView(this.b, new RelativeLayout.LayoutParams(-1, -2));
    }
}
