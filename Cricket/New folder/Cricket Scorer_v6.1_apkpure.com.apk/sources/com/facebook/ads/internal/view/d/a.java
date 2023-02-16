package com.facebook.ads.internal.view.d;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.component.e;
import com.facebook.ads.internal.view.component.h;

public class a extends RelativeLayout {
    private static final int a = ((int) (72.0f * x.b));
    private static final int b = ((int) (16.0f * x.b));
    private static final RelativeLayout.LayoutParams c = new RelativeLayout.LayoutParams(-1, -1);
    private final k d;

    public a(Context context, k kVar) {
        super(context);
        this.d = kVar;
        a();
    }

    private void a() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        e eVar = new e(getContext());
        x.a((View) eVar, 0);
        eVar.setRadius(50);
        new d((ImageView) eVar).a().a(this.d.a().b());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        h hVar = new h(getContext(), this.d.d().a(), true, false, true);
        hVar.a(this.d.b().a(), this.d.b().b(), false, true);
        hVar.setAlignment(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(0, b, 0, b);
        TextView textView = new TextView(getContext());
        textView.setTextColor(-1);
        x.a(textView, false, 16);
        textView.setText(this.d.e().j().c());
        textView.setPadding(b, b / 2, b, b / 2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(0, b / 2, 0, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(100.0f);
        gradientDrawable.setColor(469762047);
        x.a((View) textView, (Drawable) gradientDrawable);
        linearLayout.addView(eVar, layoutParams);
        linearLayout.addView(hVar, layoutParams2);
        linearLayout.addView(textView, layoutParams3);
        x.a((View) this, -14473425);
        addView(linearLayout, c);
    }
}
