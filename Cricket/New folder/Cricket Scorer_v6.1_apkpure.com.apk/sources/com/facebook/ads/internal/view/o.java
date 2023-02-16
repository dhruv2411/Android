package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.e.b;
import com.facebook.ads.internal.view.f;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.e;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.b.n;
import com.facebook.ads.internal.view.f.b.z;
import com.facebook.ads.internal.view.f.c.d;
import com.facebook.ads.internal.view.f.c.f;
import com.facebook.ads.internal.view.f.c.j;
import com.facebook.ads.internal.view.f.c.l;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class o extends RelativeLayout implements a {
    static final /* synthetic */ boolean a = true;
    private static final int b = ((int) (12.0f * x.b));
    private static final int c = ((int) (18.0f * x.b));
    private static final int d = ((int) (16.0f * x.b));
    private static final int e = ((int) (72.0f * x.b));
    private static final int f = ((int) (x.b * 56.0f));
    private static final int g = ((int) (56.0f * x.b));
    private static final int h = ((int) (28.0f * x.b));
    private static final int i = ((int) (20.0f * x.b));
    private static final RelativeLayout.LayoutParams j = new RelativeLayout.LayoutParams(-1, -1);
    @Nullable
    private Context A;
    /* access modifiers changed from: private */
    @Nullable
    public a B;
    /* access modifiers changed from: private */
    @Nullable
    public a.C0008a C;
    @Nullable
    private com.facebook.ads.internal.view.e.a D;
    /* access modifiers changed from: private */
    @Nullable
    public d E;
    @Nullable
    private l F;
    /* access modifiers changed from: private */
    @Nullable
    public j G;
    @Nullable
    private f H;
    /* access modifiers changed from: private */
    public b I;
    /* access modifiers changed from: private */
    public boolean J = false;
    private final AudienceNetworkActivity.BackButtonInterceptor k = new AudienceNetworkActivity.BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return !o.this.J;
        }
    };
    private final c l = new c() {
        public void a(com.facebook.ads.internal.view.f.b.b bVar) {
            if (o.this.C != null) {
                o.this.I.d();
                o.this.c();
                o.this.C.a(z.REWARDED_VIDEO_COMPLETE.a(), bVar);
            }
        }
    };
    private final e m = new e() {
        public void a(com.facebook.ads.internal.view.f.b.d dVar) {
            if (o.this.C != null) {
                o.this.C.a(z.REWARDED_VIDEO_ERROR.a());
            }
            o.this.a();
        }
    };
    private final m n = new m() {
        public void a(com.facebook.ads.internal.view.f.b.l lVar) {
            if (o.this.B != null) {
                o.this.B.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
                o.this.r.a();
                o.this.z.set(o.this.B.j());
                o.this.f();
            }
        }
    };
    private final com.facebook.ads.internal.view.f.b.o o = new com.facebook.ads.internal.view.f.b.o() {
        public void a(n nVar) {
            if (o.this.B != null && o.this.E != null && o.this.B.getDuration() - o.this.B.getCurrentPositionInMillis() <= 3000 && o.this.E.a()) {
                o.this.E.b();
            }
        }
    };
    /* access modifiers changed from: private */
    public final k p;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.m.c q;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.r.a r;
    private final a.C0006a s;
    /* access modifiers changed from: private */
    public final u t = new u();
    private final com.facebook.ads.internal.view.f.c.o u;
    private final com.facebook.ads.internal.view.f.b v;
    private final RelativeLayout w;
    private final f x;
    private final com.facebook.ads.internal.adapters.a.d y;
    /* access modifiers changed from: private */
    public final AtomicBoolean z = new AtomicBoolean(false);

    public o(Context context, com.facebook.ads.internal.m.c cVar, com.facebook.ads.internal.view.f.a aVar, a.C0008a aVar2, k kVar) {
        super(context);
        this.A = context;
        this.C = aVar2;
        this.B = aVar;
        this.q = cVar;
        this.p = kVar;
        this.y = this.p.d().a();
        this.w = new RelativeLayout(context);
        this.u = new com.facebook.ads.internal.view.f.c.o(this.A);
        this.x = new f(this.A);
        new com.facebook.ads.internal.view.b.d(this.w, i).a().a(com.facebook.ads.internal.l.a.e(this.A)).a(this.p.e().g());
        this.s = new a.C0006a() {
            public void a() {
                if (!o.this.t.b()) {
                    o.this.t.a();
                    HashMap hashMap = new HashMap();
                    if (!TextUtils.isEmpty(o.this.p.g())) {
                        o.this.r.a((Map<String, String>) hashMap);
                        hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(o.this.t.e()));
                        o.this.q.a(o.this.p.g(), hashMap);
                    }
                    if (o.this.C != null) {
                        o.this.C.a(z.REWARDED_VIDEO_IMPRESSION.a());
                    }
                }
            }
        };
        this.r = new com.facebook.ads.internal.r.a(this, 1, this.s);
        this.r.a((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.v = new com.facebook.ads.internal.view.f.b(this.A, this.q, this.B, this.p.g());
        this.I = new b(this.A, this.q, this.p, this.C, this.r, this.t);
        if (a || this.B != null) {
            this.B.setVideoProgressReportIntervalMs(kVar.h());
            x.a((View) this.B, (int) ViewCompat.MEASURED_STATE_MASK);
            this.B.getEventBus().a((T[]) new com.facebook.ads.internal.j.f[]{this.l, this.m, this.n, this.o});
            return;
        }
        throw new AssertionError();
    }

    private void b() {
        com.facebook.ads.internal.view.f.a aVar;
        com.facebook.ads.internal.view.f.a.b bVar;
        if (this.B != null) {
            this.B.d();
            this.B.a((com.facebook.ads.internal.view.f.a.b) new com.facebook.ads.internal.view.f.c.k(this.A));
            this.B.a((com.facebook.ads.internal.view.f.a.b) this.x);
            this.B.a((com.facebook.ads.internal.view.f.a.b) this.u);
            this.F = new l(this.A, true);
            d dVar = new d(this.F, d.a.FADE_OUT_ON_PLAY, true);
            this.B.a((com.facebook.ads.internal.view.f.a.b) this.F);
            this.B.a((com.facebook.ads.internal.view.f.a.b) dVar);
            this.D = new com.facebook.ads.internal.view.e.a(this.A, e, this.y, this.q, this.C, this.I.b() == b.a.INFO, this.I.b() == b.a.INFO, this.r, this.t);
            this.D.setInfo(this.p);
            this.E = new d(this.D, d.a.FADE_OUT_ON_PLAY, true);
            this.B.a((com.facebook.ads.internal.view.f.a.b) this.E);
            if (this.I.a() && this.p.e().c() > 0) {
                this.G = new j(this.A, this.p.e().c(), -12286980);
                this.G.setButtonMode(j.a.SKIP_BUTTON_MODE);
                this.G.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (o.this.G != null && o.this.G.a() && o.this.G.getSkipSeconds() != 0 && o.this.B != null) {
                            o.this.B.e();
                        }
                    }
                });
                aVar = this.B;
                bVar = this.G;
            } else if (!this.I.a()) {
                this.H = new f(this.A);
                this.H.a(this.p.a(), this.p.g(), this.p.e().c());
                if (this.p.e().c() <= 0) {
                    this.H.b();
                }
                if (this.I.b() != b.a.INFO) {
                    this.H.c();
                }
                this.H.setToolbarListener(new f.a() {
                    public void a() {
                        if (!o.this.J && o.this.B != null) {
                            boolean unused = o.this.J = true;
                            o.this.B.e();
                        } else if (o.this.J && o.this.C != null) {
                            o.this.C.a(z.REWARDED_VIDEO_END_ACTIVITY.a());
                        }
                    }
                });
                aVar = this.B;
                bVar = this.H;
            } else {
                return;
            }
            aVar.a(bVar);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        RelativeLayout.LayoutParams layoutParams;
        this.J = true;
        e();
        d();
        if (this.B != null) {
            this.B.d();
            this.B.setVisibility(4);
        }
        if (this.H != null) {
            this.H.a(true);
            this.H.c();
        }
        x.a(this.B, this.G, this.x, this.u);
        Pair<b.a, View> c2 = this.I.c();
        switch ((b.a) c2.first) {
            case MARKUP:
                x.a(this.D);
                this.w.addView((View) c2.second, j);
                return;
            case SCREENSHOTS:
                if (this.D != null) {
                    this.D.setVisibility(0);
                    this.D.a();
                }
                layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.setMargins(0, g, 0, 0);
                layoutParams.addRule(2, this.D.getId());
                break;
            case INFO:
                x.a(this.D);
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(15);
                layoutParams.setMargins(d, d, d, d);
                break;
            default:
                return;
        }
        this.w.addView((View) c2.second, layoutParams);
        this.t.a();
    }

    private void d() {
        if (Build.VERSION.SDK_INT > 19) {
            AutoTransition autoTransition = new AutoTransition();
            autoTransition.setDuration(200);
            autoTransition.setInterpolator(new AccelerateDecelerateInterpolator());
            TransitionManager.beginDelayedTransition(this.w, autoTransition);
        }
    }

    private void e() {
        if (this.A != null) {
            FrameLayout frameLayout = new FrameLayout(this.A);
            frameLayout.setLayoutParams(j);
            x.a((View) frameLayout, -1509949440);
            this.w.addView(frameLayout, 0);
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        this.x.setVisibility(this.z.get() ? 0 : 8);
    }

    private void setUpContentLayoutForVideo(int i2) {
        this.w.removeAllViews();
        this.w.addView(this.B, j);
        if (this.D != null) {
            x.a((View) this.D);
            this.D.a(i2);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(12);
            this.D.setPadding(d, d, d, d);
            this.w.addView(this.D, layoutParams);
        }
        if (this.G != null) {
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(f, f);
            layoutParams2.addRule(10);
            layoutParams2.addRule(11);
            this.G.setPadding(d, d, d, d);
            this.w.addView(this.G, layoutParams2);
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(h, h);
        layoutParams3.addRule(10);
        layoutParams3.addRule(11);
        layoutParams3.setMargins(b, b + g, b, c);
        this.w.addView(this.x, layoutParams3);
        f();
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(12);
        this.w.addView(this.u, layoutParams4);
    }

    public void a() {
        if (this.B != null) {
            this.B.f();
            this.B.k();
        }
        if (this.r != null) {
            this.r.c();
        }
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.B != null && this.C != null) {
            b();
            audienceNetworkActivity.addBackButtonInterceptor(this.k);
            this.B.setVideoURI(!TextUtils.isEmpty(this.p.e().b()) ? this.p.e().b() : this.p.e().a());
            setUpContentLayoutForVideo(audienceNetworkActivity.getResources().getConfiguration().orientation);
            addView(this.w, j);
            if (this.H != null) {
                x.a((View) this.H);
                this.H.a(this.y, true);
                addView(this.H, new RelativeLayout.LayoutParams(-1, g));
            }
            setLayoutParams(j);
            this.C.a((View) this);
        }
    }

    public void a(Bundle bundle) {
    }

    public int getCurrentPosition() {
        if (this.B != null) {
            return this.B.getCurrentPositionInMillis();
        }
        return 0;
    }

    public void i() {
        if (this.B != null) {
            this.B.a(false);
        }
    }

    public void j() {
        if (this.B != null && this.C != null && this.B.l() && !this.B.m()) {
            this.B.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.D != null) {
            this.D.a(configuration.orientation);
        }
    }

    public void onDestroy() {
        a();
        if (this.B != null) {
            this.B.getEventBus().b((T[]) new com.facebook.ads.internal.j.f[]{this.l, this.m, this.n, this.o});
        }
        if (!TextUtils.isEmpty(this.p.g())) {
            HashMap hashMap = new HashMap();
            this.r.a((Map<String, String>) hashMap);
            hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(this.t.e()));
            this.q.i(this.p.g(), hashMap);
        }
        if (this.H != null) {
            this.H.setToolbarListener((f.a) null);
        }
        this.v.a();
        this.B = null;
        this.I.e();
        this.G = null;
        this.D = null;
        this.E = null;
        this.C = null;
        this.A = null;
        this.u.a();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.t.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setEndCardController(b bVar) {
        this.I = bVar;
    }

    public void setListener(a.C0008a aVar) {
    }
}
