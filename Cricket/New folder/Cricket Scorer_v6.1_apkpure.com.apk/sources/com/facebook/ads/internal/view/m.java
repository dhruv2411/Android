package com.facebook.ads.internal.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.k;

public abstract class m {
    public static LinearLayout a(Context context, f fVar, k kVar) {
        LinearLayout linearLayout = new LinearLayout(context);
        q qVar = new q(context);
        qVar.setText(fVar.r());
        b(qVar, kVar);
        linearLayout.addView(qVar);
        return linearLayout;
    }

    public static void a(TextView textView, k kVar) {
        textView.setTextColor(kVar.c());
        textView.setTextSize((float) kVar.h());
        textView.setTypeface(kVar.a(), 1);
    }

    public static void b(TextView textView, k kVar) {
        textView.setTextColor(kVar.d());
        textView.setTextSize((float) kVar.i());
        textView.setTypeface(kVar.a());
    }
}
