package com.facebook.ads.internal.view.component;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.e;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.a;
import java.util.Locale;
import java.util.Map;

public class a extends Button {
    public static final int a = ((int) (16.0f * x.b));
    private static final int b = ((int) (4.0f * x.b));
    private final Paint c = new Paint();
    private final RectF d;
    private final boolean e;
    /* access modifiers changed from: private */
    public final String f;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.r.a g;
    /* access modifiers changed from: private */
    public final u h;
    /* access modifiers changed from: private */
    @Nullable
    public final c i;
    /* access modifiers changed from: private */
    @Nullable
    public final a.C0008a j;

    public a(Context context, boolean z, boolean z2, String str, d dVar, c cVar, a.C0008a aVar, com.facebook.ads.internal.r.a aVar2, u uVar) {
        super(context);
        this.i = cVar;
        this.j = aVar;
        this.e = z;
        this.f = str;
        this.g = aVar2;
        this.h = uVar;
        x.a(this, false, 16);
        setGravity(17);
        setPadding(a, a, a, a);
        setTextColor(dVar.f(z2));
        int e2 = dVar.e(z2);
        int blendARGB = ColorUtils.blendARGB(e2, ViewCompat.MEASURED_STATE_MASK, 0.1f);
        this.c.setStyle(Paint.Style.FILL);
        this.c.setColor(e2);
        this.d = new RectF();
        if (!z) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(blendARGB));
            stateListDrawable.addState(new int[0], new ColorDrawable(e2));
            x.a((View) this, (Drawable) stateListDrawable);
        }
    }

    public void a(e eVar, String str, Map<String, String> map) {
        a(eVar.b(), eVar.a(), str, map);
    }

    public void a(String str, final String str2, final String str3, final Map<String, String> map) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.i == null) {
            setVisibility(8);
            return;
        }
        setText(str.toUpperCase(Locale.US));
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str;
                String str2;
                try {
                    Uri parse = Uri.parse(str2);
                    a.this.g.a((Map<String, String>) map);
                    map.put("touch", k.a(a.this.h.e()));
                    b a2 = com.facebook.ads.internal.a.c.a(a.this.getContext(), a.this.i, str3, parse, map);
                    if (a2 != null) {
                        a2.b();
                    }
                    if (a.this.j != null) {
                        a.this.j.a(a.this.f);
                    }
                } catch (ActivityNotFoundException e) {
                    e = e;
                    str2 = String.valueOf(a.class);
                    str = "Error while opening " + str2;
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
        if (this.e) {
            this.d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            canvas.drawRoundRect(this.d, (float) b, (float) b, this.c);
        }
        super.onDraw(canvas);
    }
}
