package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.b.n;
import com.facebook.ads.internal.view.f.b.o;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class j extends View implements b {
    private final Paint a;
    private final Paint b;
    private final Paint c;
    private a d = a.CLOSE_BUTTON_MODE;
    private final Paint e;
    private final RectF f;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.f.a g;
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public final AtomicInteger i = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public final AtomicBoolean j = new AtomicBoolean(false);
    private final m k = new m() {
        public void a(l lVar) {
            j.this.j.set(true);
        }
    };
    private final o l = new o() {
        public void a(n nVar) {
            if (j.this.g != null) {
                int c = j.this.h;
                int duration = j.this.g.getDuration();
                if (c <= 0) {
                    j.this.i.set(0);
                } else {
                    int min = Math.min(duration, c * 1000);
                    if (min != 0) {
                        j.this.i.set(((min - j.this.g.getCurrentPositionInMillis()) * 100) / min);
                    } else {
                        return;
                    }
                }
                j.this.postInvalidate();
            }
        }
    };
    private final c m = new c() {
        public void a(com.facebook.ads.internal.view.f.b.b bVar) {
            int unused = j.this.h = 0;
            j.this.i.set(0);
            j.this.postInvalidate();
        }
    };

    public enum a {
        CLOSE_BUTTON_MODE,
        SKIP_BUTTON_MODE
    }

    public j(Context context, int i2, int i3) {
        super(context);
        float f2 = getResources().getDisplayMetrics().density;
        this.h = i2;
        this.b = new Paint();
        this.b.setStyle(Paint.Style.FILL);
        this.b.setColor(i3);
        this.c = new Paint();
        this.c.setColor(-1);
        this.c.setAlpha(230);
        this.c.setStyle(Paint.Style.FILL);
        this.c.setStrokeWidth(1.0f * f2);
        this.c.setAntiAlias(true);
        this.a = new Paint();
        this.a.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.a.setStyle(Paint.Style.STROKE);
        this.a.setAlpha(102);
        this.a.setStrokeWidth(1.5f * f2);
        this.a.setAntiAlias(true);
        setLayerType(1, (Paint) null);
        this.a.setMaskFilter(new BlurMaskFilter(6.0f, BlurMaskFilter.Blur.NORMAL));
        this.e = new Paint();
        this.e.setColor(-10066330);
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setStrokeWidth(2.0f * f2);
        this.e.setAntiAlias(true);
        this.f = new RectF();
    }

    public void a(com.facebook.ads.internal.view.f.a aVar) {
        this.g = aVar;
        this.g.getEventBus().a((T[]) new f[]{this.k, this.l, this.m});
    }

    public boolean a() {
        if (this.g != null) {
            return this.h <= 0 || this.i.get() < 0;
        }
        return false;
    }

    public void b(com.facebook.ads.internal.view.f.a aVar) {
        this.g.getEventBus().b((T[]) new f[]{this.m, this.l, this.k});
        this.g = null;
    }

    public int getSkipSeconds() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!this.j.get()) {
            super.onDraw(canvas);
            return;
        }
        int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        int i2 = min / 2;
        float f2 = (float) i2;
        canvas.drawCircle((float) (getPaddingLeft() + i2), (float) (getPaddingTop() + i2), f2, this.a);
        canvas.drawCircle((float) (getPaddingLeft() + i2), (float) (getPaddingTop() + i2), f2, this.c);
        if (this.i.get() > 0) {
            this.f.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()));
            canvas.drawArc(this.f, -90.0f, ((float) (-(this.i.get() * 360))) / 100.0f, true, this.b);
        } else if (this.d == a.SKIP_BUTTON_MODE) {
            int i3 = min / 4;
            int i4 = min / 3;
            Path path = new Path();
            path.moveTo((float) (getPaddingLeft() + i3), (float) (getPaddingTop() + i4));
            path.lineTo((float) (getPaddingLeft() + i2), (float) (getPaddingTop() + i2));
            int i5 = i4 * 2;
            path.lineTo((float) (getPaddingLeft() + i3), (float) (getPaddingTop() + i5));
            canvas.drawPath(path, this.e);
            Path path2 = new Path();
            path2.moveTo((float) (getPaddingLeft() + i2), (float) (i4 + getPaddingTop()));
            path2.lineTo((float) ((i3 * 3) + getPaddingLeft()), (float) (getPaddingTop() + i2));
            path2.lineTo((float) (i2 + getPaddingLeft()), (float) (i5 + getPaddingTop()));
            canvas.drawPath(path2, this.e);
        } else {
            int i6 = min / 3;
            int i7 = i6 * 2;
            Canvas canvas2 = canvas;
            canvas2.drawLine((float) (getPaddingLeft() + i6), (float) (getPaddingTop() + i6), (float) (getPaddingLeft() + i7), (float) (getPaddingTop() + i7), this.e);
            canvas.drawLine((float) (getPaddingLeft() + i7), (float) (getPaddingTop() + i6), (float) (i6 + getPaddingLeft()), (float) (i7 + getPaddingTop()), this.e);
        }
        super.onDraw(canvas);
    }

    public void setButtonMode(a aVar) {
        this.d = aVar;
    }
}
