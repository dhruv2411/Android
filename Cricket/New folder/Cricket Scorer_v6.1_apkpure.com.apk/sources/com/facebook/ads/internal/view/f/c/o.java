package com.facebook.ads.internal.view.f.c;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.n;
import java.util.concurrent.atomic.AtomicInteger;

public class o extends RelativeLayout implements b {
    private static final int a = ((int) (6.0f * x.b));
    private ObjectAnimator b;
    private AtomicInteger c;
    private ProgressBar d;
    /* access modifiers changed from: private */
    @Nullable
    public a e;
    private f f;
    private f g;
    private f h;
    private f i;

    public o(Context context) {
        this(context, a, -12549889);
    }

    public o(Context context, int i2, int i3) {
        super(context);
        this.f = new com.facebook.ads.internal.view.f.b.o() {
            public void a(n nVar) {
                if (o.this.e != null) {
                    o.this.a(o.this.e.getDuration(), o.this.e.getCurrentPositionInMillis());
                }
            }
        };
        this.g = new i() {
            public void a(h hVar) {
                o.this.b();
            }
        };
        this.h = new k() {
            public void a(j jVar) {
                if (o.this.e != null) {
                    o.this.a(o.this.e.getDuration(), o.this.e.getCurrentPositionInMillis());
                }
            }
        };
        this.i = new c() {
            public void a(com.facebook.ads.internal.view.f.b.b bVar) {
                if (o.this.e != null) {
                    o.this.c();
                }
            }
        };
        this.c = new AtomicInteger(-1);
        this.d = new ProgressBar(context, (AttributeSet) null, 16842872);
        this.d.setLayoutParams(new RelativeLayout.LayoutParams(-1, i2));
        setProgressBarColor(i3);
        this.d.setMax(10000);
        addView(this.d);
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3) {
        b();
        if (this.c.get() < i3 && i2 > i3) {
            this.b = ObjectAnimator.ofInt(this.d, NotificationCompat.CATEGORY_PROGRESS, new int[]{(i3 * 10000) / i2, (Math.min(i3 + ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, i2) * 10000) / i2});
            this.b.setDuration((long) Math.min(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, i2 - i3));
            this.b.setInterpolator(new LinearInterpolator());
            this.b.start();
            this.c.set(i3);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.b != null) {
            this.b.cancel();
            this.b.setTarget((Object) null);
            this.b = null;
            this.d.clearAnimation();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        b();
        this.b = ObjectAnimator.ofInt(this.d, NotificationCompat.CATEGORY_PROGRESS, new int[]{0, 0});
        this.b.setDuration(0);
        this.b.setInterpolator(new LinearInterpolator());
        this.b.start();
        this.c.set(0);
    }

    public void a() {
        b();
        this.d = null;
        this.e = null;
    }

    public void a(a aVar) {
        this.e = aVar;
        aVar.getEventBus().a((T[]) new f[]{this.g, this.h, this.f, this.i});
    }

    public void b(a aVar) {
        aVar.getEventBus().b((T[]) new f[]{this.f, this.h, this.g, this.i});
        this.e = null;
    }

    public void setProgressBarColor(int i2) {
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new ColorDrawable(0), new ColorDrawable(0), new ScaleDrawable(new ColorDrawable(i2), GravityCompat.START, 1.0f, -1.0f)});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        this.d.setProgressDrawable(layerDrawable);
    }
}
