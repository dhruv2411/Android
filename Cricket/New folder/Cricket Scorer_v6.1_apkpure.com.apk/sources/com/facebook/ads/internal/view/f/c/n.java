package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.o;

public class n extends View implements b {
    private final Paint a = new Paint();
    private final Rect b;
    /* access modifiers changed from: private */
    public float c;
    private final o d = new o() {
        public void a(com.facebook.ads.internal.view.f.b.n nVar) {
            if (n.this.f != null) {
                int duration = n.this.f.getDuration();
                if (duration > 0) {
                    float unused = n.this.c = ((float) n.this.f.getCurrentPositionInMillis()) / ((float) duration);
                } else {
                    float unused2 = n.this.c = 0.0f;
                }
                n.this.postInvalidate();
            }
        }
    };
    private final c e = new c() {
        public void a(com.facebook.ads.internal.view.f.b.b bVar) {
            if (n.this.f != null) {
                float unused = n.this.c = 0.0f;
                n.this.postInvalidate();
            }
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public a f;

    public n(Context context) {
        super(context);
        this.a.setStyle(Paint.Style.FILL);
        this.a.setColor(-9528840);
        this.b = new Rect();
    }

    public void a(a aVar) {
        this.f = aVar;
        aVar.getEventBus().a((T[]) new f[]{this.d, this.e});
    }

    public void b(a aVar) {
        aVar.getEventBus().b((T[]) new f[]{this.e, this.d});
        this.f = null;
    }

    public void draw(Canvas canvas) {
        this.b.set(0, 0, (int) (((float) getWidth()) * this.c), getHeight());
        canvas.drawRect(this.b, this.a);
        super.draw(canvas);
    }
}
