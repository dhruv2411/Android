package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.facebook.ads.internal.q.b.c;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.v;
import com.facebook.ads.internal.view.f.b.w;

public class f extends ImageView implements b {
    private static final int a = ((int) (4.0f * Resources.getSystem().getDisplayMetrics().density));
    private final Paint b = new Paint();
    /* access modifiers changed from: private */
    @Nullable
    public a c;
    private final w d = new w() {
        public void a(v vVar) {
            f.this.a();
        }
    };

    public f(Context context) {
        super(context);
        this.b.setColor(-1728053248);
        setColorFilter(-1);
        setPadding(a, a, a, a);
        c();
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                a a2;
                float f;
                if (f.this.c != null) {
                    if (f.this.b()) {
                        a2 = f.this.c;
                        f = 1.0f;
                    } else {
                        a2 = f.this.c;
                        f = 0.0f;
                    }
                    a2.setVolume(f);
                    f.this.a();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean b() {
        return this.c != null && this.c.getVolume() == 0.0f;
    }

    private void c() {
        setImageBitmap(c.a(com.facebook.ads.internal.q.b.b.SOUND_ON));
    }

    private void d() {
        setImageBitmap(c.a(com.facebook.ads.internal.q.b.b.SOUND_OFF));
    }

    public final void a() {
        if (this.c != null) {
            if (b()) {
                d();
            } else {
                c();
            }
        }
    }

    public void a(a aVar) {
        this.c = aVar;
        if (this.c != null) {
            this.c.getEventBus().a(this.d);
        }
    }

    public void b(a aVar) {
        if (this.c != null) {
            this.c.getEventBus().b(this.d);
        }
        this.c = null;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        canvas.drawCircle((float) width, (float) height, (float) Math.min(width, height), this.b);
        super.onDraw(canvas);
    }
}
