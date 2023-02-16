package com.facebook.ads.internal.n;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.b.e;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.c.h;
import com.facebook.ads.internal.view.f.d;
import com.facebook.ads.internal.view.j;
import java.util.concurrent.atomic.AtomicBoolean;

public class d {
    private static final String a = "d";
    private final g b;
    private final com.facebook.ads.internal.r.a c;
    private final a.C0006a d;
    private final View e;
    private final d.a f = new d.a() {
        public void a() {
            d.this.n.set(true);
            if (d.this.h != null) {
                d.this.h.a(d.this.m.get());
            }
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public j g;
    /* access modifiers changed from: private */
    @Nullable
    public a h;
    private Context i;
    private boolean j;
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public boolean l;
    /* access modifiers changed from: private */
    public final AtomicBoolean m = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final AtomicBoolean n = new AtomicBoolean(false);
    private m o = m.DEFAULT;

    public interface a {
        void a(boolean z);
    }

    public d(Context context, View view) {
        this.i = context;
        this.e = view;
        this.b = new g(context);
        this.d = k();
        this.c = j();
        g();
    }

    /* access modifiers changed from: private */
    public void a(com.facebook.ads.internal.view.f.a.a aVar) {
        if (this.g != null) {
            this.g.a(aVar);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.e(a, "MediaViewVideo is null; unable to find it.");
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (this.g != null) {
            this.g.a(z);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.e(a, "MediaViewVideo is null; unable to find it.");
        }
    }

    private void g() {
        float f2 = x.b;
        int i2 = (int) (2.0f * f2);
        int i3 = (int) (25.0f * f2);
        h hVar = new h(this.i);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        hVar.setPadding(i2, i3, i3, i2);
        hVar.setLayoutParams(layoutParams);
        int i4 = 0;
        while (true) {
            if (i4 >= ((ViewGroup) this.e).getChildCount()) {
                break;
            }
            View childAt = ((ViewGroup) this.e).getChildAt(0);
            if (childAt instanceof j) {
                this.g = (j) childAt;
                break;
            }
            i4++;
        }
        if (this.g != null) {
            this.g.a((b) this.b);
            this.g.a((b) hVar);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.e(a, "Unable to find MediaViewVideo child.");
        }
        this.c.a(0);
        this.c.b((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    private void h() {
        if (this.g != null) {
            ((com.facebook.ads.internal.view.f.d) this.g.getVideoView()).setViewImplInflationListener(this.f);
        }
    }

    private void i() {
        if (this.g != null) {
            ((com.facebook.ads.internal.view.f.d) this.g.getVideoView()).setViewImplInflationListener((d.a) null);
        }
    }

    private com.facebook.ads.internal.r.a j() {
        return new com.facebook.ads.internal.r.a(this.e, 50, true, this.d);
    }

    private a.C0006a k() {
        return new a.C0006a() {
            public void a() {
                if (d.this.g != null) {
                    if (!d.this.l && (d.this.k || d.this.m())) {
                        d.this.a(com.facebook.ads.internal.view.f.a.a.AUTO_STARTED);
                    }
                    boolean unused = d.this.k = false;
                    boolean unused2 = d.this.l = false;
                }
            }

            public void b() {
                if (d.this.g != null) {
                    if (d.this.g.getState() == com.facebook.ads.internal.view.f.d.d.PAUSED) {
                        boolean unused = d.this.l = true;
                    } else if (d.this.g.getState() == com.facebook.ads.internal.view.f.d.d.STARTED) {
                        boolean unused2 = d.this.k = true;
                    }
                    d.this.a(d.this.l);
                }
            }
        };
    }

    private void l() {
        if (this.e.getVisibility() != 0 || !this.j || !this.e.hasWindowFocus()) {
            if (this.g != null && this.g.getState() == com.facebook.ads.internal.view.f.d.d.PAUSED) {
                this.l = true;
            }
            this.c.c();
            return;
        }
        this.c.a();
    }

    /* access modifiers changed from: private */
    public boolean m() {
        return (this.g == null || this.g.getState() == com.facebook.ads.internal.view.f.d.d.PLAYBACK_COMPLETED || this.o != m.ON) ? false : true;
    }

    public void a() {
        this.o = m.DEFAULT;
        i();
    }

    public void a(f fVar, @Nullable a aVar) {
        this.k = false;
        this.l = false;
        this.h = aVar;
        h();
        this.b.a((fVar == null || fVar.k() == null) ? null : fVar.k().a(), new e() {
            public void a(boolean z) {
                d.this.m.set(z);
                if (d.this.n.get() && d.this.h != null) {
                    d.this.h.a(z);
                }
            }
        });
        this.o = fVar.E();
        this.c.a();
    }

    public void b() {
        if (this.g != null) {
            this.g.getVideoView().setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (d.this.g != null && motionEvent.getAction() == 1) {
                        d.this.g.a();
                    }
                    return true;
                }
            });
        }
    }

    public void c() {
        this.j = true;
        l();
    }

    public void d() {
        this.j = false;
        l();
    }

    public void e() {
        l();
    }

    public void f() {
        l();
    }
}
