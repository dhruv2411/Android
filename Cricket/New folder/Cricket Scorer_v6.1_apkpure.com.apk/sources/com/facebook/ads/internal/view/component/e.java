package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.widget.ImageView;
import com.facebook.ads.internal.q.a.x;

public class e extends ImageView {
    private static final int a = ((int) (8.0f * x.b));
    private final Path b = new Path();
    private final RectF c = new RectF();
    private int d = a;
    private boolean e = false;

    public e(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, (Paint) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.c.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.b.reset();
        float min = (float) (this.e ? Math.min(getWidth(), getHeight()) / 2 : this.d);
        this.b.addRoundRect(this.c, min, min, Path.Direction.CW);
        canvas.clipPath(this.b);
        super.onDraw(canvas);
    }

    public void setFullCircleCorners(boolean z) {
        this.e = z;
    }

    public void setRadius(int i) {
        this.d = (int) (((float) i) * x.b);
    }
}
