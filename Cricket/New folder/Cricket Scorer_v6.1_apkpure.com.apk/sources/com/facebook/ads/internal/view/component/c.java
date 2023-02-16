package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AdIconView;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.k;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.m;
import com.facebook.ads.internal.view.q;

public class c extends LinearLayout {
    private AdIconView a;
    private b b;
    private TextView c;
    private LinearLayout d = new LinearLayout(getContext());

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(Context context, f fVar, k kVar, AdIconView adIconView, boolean z, int i) {
        super(context);
        k kVar2 = kVar;
        boolean z2 = z;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setVerticalGravity(16);
        setOrientation(1);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(displayMetrics.density * 15.0f), Math.round(displayMetrics.density * 15.0f), Math.round(displayMetrics.density * 15.0f), Math.round(displayMetrics.density * 15.0f));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 0);
        this.d.setOrientation(0);
        this.d.setGravity(16);
        layoutParams2.weight = 3.0f;
        this.d.setLayoutParams(layoutParams2);
        linearLayout.addView(this.d);
        this.a = adIconView;
        float a2 = (float) a(z2, i);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(Math.round(displayMetrics.density * a2), Math.round(a2 * displayMetrics.density));
        layoutParams3.setMargins(0, 0, Math.round(displayMetrics.density * 15.0f), 0);
        this.a.setLayoutParams(layoutParams3);
        this.d.addView(this.a);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        this.d.addView(linearLayout2);
        this.b = new b(getContext(), fVar, kVar2);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -1);
        layoutParams4.setMargins(0, 0, Math.round(15.0f * displayMetrics.density), 0);
        layoutParams4.weight = 0.5f;
        this.b.setLayoutParams(layoutParams4);
        linearLayout2.addView(this.b);
        this.c = new TextView(getContext());
        this.c.setPadding(Math.round(displayMetrics.density * 6.0f), Math.round(displayMetrics.density * 6.0f), Math.round(displayMetrics.density * 6.0f), Math.round(6.0f * displayMetrics.density));
        this.c.setText(fVar.q());
        this.c.setTextColor(kVar.f());
        this.c.setTextSize(14.0f);
        this.c.setTypeface(kVar.a(), 1);
        this.c.setMaxLines(2);
        this.c.setEllipsize(TextUtils.TruncateAt.END);
        this.c.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(kVar.e());
        gradientDrawable.setCornerRadius(5.0f * displayMetrics.density);
        gradientDrawable.setStroke(1, kVar.g());
        x.a((View) this.c, (Drawable) gradientDrawable);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.weight = 0.25f;
        this.c.setLayoutParams(layoutParams5);
        if (!fVar.i()) {
            this.c.setVisibility(4);
        }
        linearLayout2.addView(this.c);
        if (z2) {
            q qVar = new q(getContext());
            qVar.setText(fVar.o());
            m.b(qVar, kVar2);
            qVar.setMinTextSize((float) (kVar.i() - 1));
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, 0);
            layoutParams6.weight = 1.0f;
            qVar.setLayoutParams(layoutParams6);
            qVar.setGravity(80);
            linearLayout.addView(qVar);
        }
    }

    private int a(boolean z, int i) {
        return (int) (((double) (i - 30)) * (3.0d / ((double) (true + (z ? 1 : 0)))));
    }

    public TextView getCallToActionView() {
        return this.c;
    }

    public AdIconView getIconView() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        TextView titleTextView = this.b.getTitleTextView();
        if (titleTextView.getLayout().getLineEnd(titleTextView.getLineCount() - 1) < this.b.getMinVisibleTitleCharacters()) {
            this.d.removeView(this.a);
            super.onMeasure(i, i2);
        }
    }
}
