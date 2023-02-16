package com.facebook.ads.internal.view;

import android.content.Context;
import android.widget.RelativeLayout;

public class r extends RelativeLayout {
    private int a = 0;
    private int b = 0;

    public r(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        if (this.b > 0 && getMeasuredWidth() > this.b) {
            i3 = this.b;
        } else if (getMeasuredWidth() < this.a) {
            i3 = this.a;
        } else {
            return;
        }
        setMeasuredDimension(i3, getMeasuredHeight());
    }

    public void setMaxWidth(int i) {
        this.b = i;
    }

    public void setMinWidth(int i) {
        this.a = i;
    }
}
