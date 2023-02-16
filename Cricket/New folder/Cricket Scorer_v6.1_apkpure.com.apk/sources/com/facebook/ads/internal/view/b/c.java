package com.facebook.ads.internal.view.b;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.facebook.ads.internal.r.a;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.lang.ref.WeakReference;

public class c extends Drawable {
    private final Paint a = new Paint();
    private final Paint b = new Paint();
    private final Path c = new Path();
    private final TextPaint d = new TextPaint();
    private final Paint e = new Paint();
    private int f;
    private int g;
    private String h;
    private int i;
    /* access modifiers changed from: private */
    public boolean j;
    @Nullable
    private String k;
    private String l;
    private long m;
    /* access modifiers changed from: private */
    public final Handler n = new Handler();
    @Nullable
    private WeakReference<a> o;
    /* access modifiers changed from: private */
    public final Runnable p = new Runnable() {
        public void run() {
            c.this.c();
            if (c.this.j) {
                c.this.n.postDelayed(c.this.p, 250);
            }
        }
    };

    public c() {
        this.a.setColor(Color.argb(127, 36, 36, 36));
        this.a.setStyle(Paint.Style.FILL_AND_STROKE);
        this.b.setAntiAlias(true);
        this.b.setColor(Color.argb(191, 0, 255, 0));
        this.b.setStrokeWidth(20.0f);
        this.b.setStyle(Paint.Style.STROKE);
        this.d.setAntiAlias(true);
        this.d.setColor(-1);
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
        this.d.setTextSize(30.0f);
        this.e.setColor(Color.argb(212, 0, 0, 0));
        this.e.setStyle(Paint.Style.FILL_AND_STROKE);
        b();
    }

    /* access modifiers changed from: private */
    public void c() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.f <= 0) {
            if (!TextUtils.isEmpty(this.k)) {
                sb.append(this.k);
                sb.append("\n");
            }
            if (!TextUtils.isEmpty(this.l)) {
                sb.append(this.l);
                sb.append("\n");
            }
            sb.append("Sdk ");
            sb.append("4.99.1");
            sb.append(", Loaded ");
            if (this.m > 0) {
                long max = Math.max(0, System.currentTimeMillis() - this.m);
                int i2 = (int) (max / 3600000);
                long j2 = max % 3600000;
                int i3 = (int) (j2 / ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
                int i4 = (int) ((j2 % ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS) / 1000);
                if (i2 > 0) {
                    sb.append(i2);
                    sb.append("h ");
                }
                if (i2 > 0 || i3 > 0) {
                    sb.append(i3);
                    sb.append("m ");
                }
                sb.append(i4);
                str = "s ago";
            } else {
                str = "Unknown";
            }
            sb.append(str);
        } else {
            sb.append("Card ");
            sb.append(this.g + 1);
            sb.append(" of ");
            sb.append(this.f);
        }
        sb.append("\nView: ");
        sb.append((this.o == null || this.o.get() == null) ? "Viewability Checker not set" : ((a) this.o.get()).d());
        this.h = sb.toString();
        float f2 = -2.14748365E9f;
        for (String str2 : this.h.split("\n")) {
            f2 = Math.max(f2, this.d.measureText(str2, 0, str2.length()));
        }
        this.i = (int) (f2 + 0.5f);
        invalidateSelf();
    }

    public void a(int i2, int i3) {
        this.f = i2;
        this.g = i3;
        c();
    }

    public void a(long j2) {
        this.m = j2;
        c();
    }

    public void a(a aVar) {
        this.o = new WeakReference<>(aVar);
        c();
    }

    public void a(String str) {
        this.k = str;
        c();
    }

    public void a(boolean z) {
        this.j = z;
        if (this.j) {
            this.n.post(this.p);
        } else {
            this.n.removeCallbacks(this.p);
        }
        invalidateSelf();
    }

    public boolean a() {
        return this.j;
    }

    public void b() {
        this.f = 0;
        this.g = -1;
        this.h = "Initializing...";
        this.i = 100;
        this.k = null;
        this.m = -1;
        this.o = null;
        a(false);
    }

    public void b(String str) {
        this.l = str;
        c();
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.j) {
            float width = (float) canvas.getWidth();
            float height = (float) canvas.getHeight();
            canvas2.drawRect(0.0f, 0.0f, width, height, this.a);
            StaticLayout staticLayout = new StaticLayout(this.h, this.d, this.i, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            float f2 = width / 2.0f;
            float f3 = height / 2.0f;
            float width2 = ((float) staticLayout.getWidth()) / 2.0f;
            float height2 = ((float) staticLayout.getHeight()) / 2.0f;
            float f4 = f2 - width2;
            float f5 = f3 - height2;
            canvas2.drawRect(f4 - 40.0f, f5 - 40.0f, f2 + width2 + 40.0f, f3 + height2 + 40.0f, this.e);
            canvas.save();
            canvas2.translate(f4, f5);
            staticLayout.draw(canvas2);
            canvas.restore();
            this.c.reset();
            this.c.moveTo(0.0f, 0.0f);
            this.c.lineTo(width, 0.0f);
            this.c.lineTo(width, height);
            this.c.lineTo(0.0f, height);
            this.c.lineTo(0.0f, 0.0f);
            canvas2.drawPath(this.c, this.b);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i2) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
