package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.c.a.a;
import com.facebook.ads.internal.view.component.h;

public class d extends b {
    private static final int c = ((int) (20.0f * x.b));
    private static final int d = ((int) (16.0f * x.b));

    d(com.facebook.ads.internal.view.component.a.d dVar, com.facebook.ads.internal.adapters.a.d dVar2, String str, a aVar) {
        super(dVar, dVar2, false, str, aVar);
    }

    /* access modifiers changed from: protected */
    public void a(Context context) {
        h titleDescContainer = getTitleDescContainer();
        titleDescContainer.setAlignment(3);
        titleDescContainer.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        titleDescContainer.setPadding(0, 0, 0, c);
        getCtaButton().setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout = new LinearLayout(context);
        x.a((View) linearLayout, (Drawable) new ColorDrawable(-1));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, getMediaContainer().getId());
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(d, d, d, d);
        linearLayout.addView(titleDescContainer);
        linearLayout.addView(getCtaButton());
        addView(getMediaContainer());
        addView(linearLayout);
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return false;
    }
}
