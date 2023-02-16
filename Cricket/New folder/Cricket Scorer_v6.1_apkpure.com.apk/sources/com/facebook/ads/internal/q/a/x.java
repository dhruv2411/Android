package com.facebook.ads.internal.q.a;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicInteger;

public class x {
    public static final DisplayMetrics a = Resources.getSystem().getDisplayMetrics();
    public static final float b = a.density;
    private static final AtomicInteger c = new AtomicInteger(1);

    public static int a() {
        int i;
        int i2;
        do {
            i = c.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!c.compareAndSet(i, i2));
        return i;
    }

    public static int a(int i) {
        return (int) TypedValue.applyDimension(2, (float) i, a);
    }

    public static void a(View view) {
        view.setId(Build.VERSION.SDK_INT >= 17 ? View.generateViewId() : a());
    }

    public static void a(View view, int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(new ColorDrawable(i));
        } else {
            view.setBackgroundDrawable(new ColorDrawable(i));
        }
    }

    public static void a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void a(TextView textView, boolean z, int i) {
        Typeface typeface;
        Typeface typeface2;
        int i2 = 0;
        if (!z) {
            typeface2 = Typeface.SANS_SERIF;
        } else if (Build.VERSION.SDK_INT >= 21) {
            typeface = Typeface.create("sans-serif-medium", 0);
            textView.setTypeface(typeface);
            textView.setTextSize(2, (float) i);
        } else {
            typeface2 = Typeface.SANS_SERIF;
            i2 = 1;
        }
        typeface = Typeface.create(typeface2, i2);
        textView.setTypeface(typeface);
        textView.setTextSize(2, (float) i);
    }

    public static void a(View... viewArr) {
        for (View b2 : viewArr) {
            b(b2);
        }
    }

    public static void b(View view) {
        ViewGroup viewGroup;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeView(view);
        }
    }
}
