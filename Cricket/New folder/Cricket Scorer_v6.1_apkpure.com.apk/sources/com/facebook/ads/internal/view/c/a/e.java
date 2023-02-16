package com.facebook.ads.internal.view.c.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b;
import com.facebook.ads.internal.view.f.c.d;
import com.facebook.ads.internal.view.f.c.f;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.c.l;
import com.facebook.ads.internal.view.t;
import java.util.Map;

public class e extends FrameLayout {
    private static final int a = ((int) (16.0f * x.b));
    private final c b;
    private t c;
    private f d;
    /* access modifiers changed from: private */
    public l e;
    private g f;
    @Nullable
    private b g;

    public e(Context context, c cVar) {
        super(context);
        this.b = cVar;
        setUpView(context);
    }

    private void setUpPlugins(Context context) {
        this.c.d();
        this.f = new g(context);
        this.c.a((com.facebook.ads.internal.view.f.a.b) this.f);
        this.d = new f(context);
        this.c.a((com.facebook.ads.internal.view.f.a.b) new com.facebook.ads.internal.view.f.c.b(context));
        this.c.a((com.facebook.ads.internal.view.f.a.b) this.d);
        this.e = new l(context, true);
        this.c.a((com.facebook.ads.internal.view.f.a.b) this.e);
        this.c.a((com.facebook.ads.internal.view.f.a.b) new d(this.e, d.a.FADE_OUT_ON_PLAY, true, true));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        layoutParams.setMargins(a, a, a, a);
        this.d.setLayoutParams(layoutParams);
        this.c.addView(this.d);
    }

    private void setUpVideo(Context context) {
        this.c = new t(context);
        this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        x.a((View) this.c);
        addView(this.c);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e.this.e.performClick();
            }
        });
    }

    private void setUpView(Context context) {
        setUpVideo(context);
        setUpPlugins(context);
    }

    public void a() {
        this.c.a(true);
    }

    public void a(com.facebook.ads.internal.j.f fVar) {
        this.c.getEventBus().a(fVar);
    }

    public void a(c cVar, String str, Map<String, String> map) {
        c();
        this.g = new b(getContext(), cVar, (a) this.c, str, map);
    }

    public void a(com.facebook.ads.internal.view.f.a.a aVar) {
        this.c.a(aVar);
    }

    public boolean b() {
        return this.c.i();
    }

    public void c() {
        if (this.g != null) {
            this.g.a();
            this.g = null;
        }
    }

    public float getVolume() {
        return this.c.getVolume();
    }

    public void setPlaceholderUrl(String str) {
        this.f.setImage(str);
    }

    public void setVideoURI(String str) {
        this.c.setVideoURI(str);
    }

    public void setVolume(float f2) {
        this.c.setVolume(f2);
        this.d.a();
    }
}
