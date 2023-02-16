package com.facebook.ads.internal.view.c;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.x;
import java.util.HashMap;

public class a extends RelativeLayout {
    /* access modifiers changed from: private */
    public final String a;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.view.f.a b;
    private final Paint c = new Paint();
    private final RectF d;

    public a(Context context, String str, String str2, int i, com.facebook.ads.internal.view.f.a aVar, final c cVar, final String str3) {
        super(context);
        this.a = str;
        this.b = aVar;
        TextView textView = new TextView(context);
        textView.setTextColor(-1);
        textView.setTextSize(16.0f);
        textView.setText(str2);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        setGravity(17);
        addView(textView);
        this.c.setStyle(Paint.Style.FILL);
        this.c.setColor(i);
        this.d = new RectF();
        x.a((View) this, 0);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str;
                String str2;
                try {
                    Uri parse = Uri.parse(a.this.a);
                    a.this.b.getEventBus().a(new com.facebook.ads.internal.view.f.b.a(parse));
                    b a2 = com.facebook.ads.internal.a.c.a(a.this.getContext(), cVar, str3, parse, new HashMap());
                    if (a2 != null) {
                        a2.b();
                    }
                } catch (ActivityNotFoundException e) {
                    e = e;
                    str2 = String.valueOf(a.class);
                    str = "Error while opening " + a.this.a;
                    Log.e(str2, str, e);
                } catch (Exception e2) {
                    e = e2;
                    str2 = String.valueOf(a.class);
                    str = "Error executing action";
                    Log.e(str2, str, e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        float f2 = 10.0f * f;
        canvas.drawRoundRect(this.d, f2, f2, this.c);
        super.onDraw(canvas);
    }
}
