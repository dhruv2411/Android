package com.facebook.ads.internal.view.c;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.i;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.component.e;

public class c extends LinearLayout {
    private e a;
    private TextView b;
    private TextView c;

    public c(Context context) {
        super(context);
        a(context);
    }

    public void a(int i, int i2) {
        this.b.setTextColor(i);
        this.c.setTextColor(i2);
    }

    public void a(Context context) {
        int i = (int) (32.0f * x.b);
        setGravity(16);
        this.a = new e(context);
        this.a.setFullCircleCorners(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        layoutParams.setMargins(0, 0, (int) (8.0f * x.b), 0);
        addView(this.a, layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.b = new TextView(context);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        x.a(this.b, true, 16);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        this.b.setSingleLine(true);
        this.c = new TextView(context);
        x.a(this.c, false, 14);
        linearLayout.addView(this.b);
        linearLayout.addView(this.c);
        addView(linearLayout, layoutParams2);
    }

    public void setPageDetails(i iVar) {
        d dVar = new d((ImageView) this.a);
        dVar.a((int) (x.b * 32.0f), (int) (32.0f * x.b));
        dVar.a(iVar.b());
        this.b.setText(iVar.a());
        this.c.setText(iVar.d());
    }
}
