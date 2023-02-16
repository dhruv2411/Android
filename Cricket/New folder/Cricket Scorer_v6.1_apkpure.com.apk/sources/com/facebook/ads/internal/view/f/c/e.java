package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.a;
import java.util.HashMap;

public class e extends c {
    /* access modifiers changed from: private */
    public final String a;
    private final TextView b = new TextView(getContext());
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.m.c c;
    /* access modifiers changed from: private */
    public final String d;
    private final Paint e;
    private final RectF f;

    public e(Context context, String str, com.facebook.ads.internal.m.c cVar, String str2, String str3) {
        super(context);
        this.a = str;
        this.c = cVar;
        this.d = str2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.b.setTextColor(-3355444);
        this.b.setTextSize(16.0f);
        this.b.setPadding((int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f), (int) (6.0f * displayMetrics.density), (int) (4.0f * displayMetrics.density));
        this.e = new Paint();
        this.e.setStyle(Paint.Style.FILL);
        this.e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.e.setAlpha(178);
        this.f = new RectF();
        x.a((View) this, 0);
        this.b.setText(str3);
        addView(this.b, new RelativeLayout.LayoutParams(-2, -2));
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (e.this.getVideoView() != null) {
                    Uri parse = Uri.parse(e.this.a);
                    e.this.getVideoView().getEventBus().a(new a(parse));
                    b a2 = com.facebook.ads.internal.a.c.a(e.this.getContext(), e.this.c, e.this.d, parse, new HashMap());
                    if (a2 != null) {
                        a2.b();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.b.setOnClickListener((View.OnClickListener) null);
        super.b();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.f.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f, 0.0f, 0.0f, this.e);
        super.onDraw(canvas);
    }
}
