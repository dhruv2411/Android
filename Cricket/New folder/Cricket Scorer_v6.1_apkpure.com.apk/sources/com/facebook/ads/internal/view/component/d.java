package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.ColorUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.ads.internal.q.a.x;
import java.util.ArrayList;
import java.util.List;

public class d extends LinearLayout {
    private final int a;
    private final int b;
    private final int c;
    private int d = -1;
    private List<GradientDrawable> e;

    public d(Context context, com.facebook.ads.internal.adapters.a.d dVar, int i) {
        super(context);
        setOrientation(0);
        setGravity(17);
        float f = x.b;
        int i2 = (int) (8.0f * f);
        int i3 = (int) (6.0f * f);
        this.c = (int) (1.0f * f);
        this.a = dVar.a(false);
        this.b = ColorUtils.setAlphaComponent(this.a, 128);
        this.e = new ArrayList();
        for (int i4 = 0; i4 < i; i4++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setSize(i2, i2);
            gradientDrawable.setStroke(this.c, 0);
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, i3, 0);
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
            imageView.setImageDrawable(gradientDrawable);
            this.e.add(gradientDrawable);
            addView(imageView);
        }
        a(0);
    }

    public void a(int i) {
        int i2;
        int i3;
        if (this.d != i) {
            this.d = i;
            for (int i4 = 0; i4 < this.e.size(); i4++) {
                if (i4 == i) {
                    i3 = this.a;
                    i2 = this.a;
                } else {
                    i3 = this.b;
                    i2 = 0;
                }
                this.e.get(i4).setStroke(this.c, i2);
                this.e.get(i4).setColor(i3);
                this.e.get(i4).invalidateSelf();
            }
        }
    }
}
