package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.x;

public class f extends RelativeLayout {
    private static final int a = ((int) (4.0f * x.b));
    private final Path b = new Path();
    private final RectF c = new RectF();

    public f(Context context) {
        super(context);
        x.a((View) this, 0);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, (Paint) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.c.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.b.reset();
        this.b.addRoundRect(this.c, (float) a, (float) a, Path.Direction.CW);
        canvas.clipPath(this.b);
        super.onDraw(canvas);
    }
}
