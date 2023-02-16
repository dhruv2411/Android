package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.k;
import com.facebook.ads.internal.view.m;
import com.facebook.ads.internal.view.p;

public class b extends LinearLayout {
    private p a = new p(getContext(), 2);
    private int b;

    public b(Context context, f fVar, k kVar) {
        super(context);
        setOrientation(1);
        setVerticalGravity(16);
        this.a.setMinTextSize((float) (kVar.h() - 2));
        this.a.setText(fVar.m());
        m.a(this.a, kVar);
        this.a.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        addView(this.a);
        this.b = fVar.m() != null ? Math.min(fVar.m().length(), 21) : 21;
        addView(m.a(context, fVar, kVar));
    }

    public int getMinVisibleTitleCharacters() {
        return this.b;
    }

    public TextView getTitleTextView() {
        return this.a;
    }
}
