package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

public class s extends ImageView {
    public s(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            i2 = i;
        } else if (View.MeasureSpec.getMode(i2) == 1073741824) {
            i = i2;
        }
        super.onMeasure(i, i2);
    }
}
