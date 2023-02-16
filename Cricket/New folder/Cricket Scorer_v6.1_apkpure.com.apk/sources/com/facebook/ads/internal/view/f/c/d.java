package com.facebook.ads.internal.view.f.c;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.t;
import com.facebook.ads.internal.view.f.b.u;

@TargetApi(12)
public class d implements b {
    private final i a;
    private final k b;
    private final c c;
    private final u d;
    /* access modifiers changed from: private */
    public final Handler e;
    /* access modifiers changed from: private */
    public final boolean f;
    /* access modifiers changed from: private */
    public final boolean g;
    /* access modifiers changed from: private */
    public View h;
    /* access modifiers changed from: private */
    @Nullable
    public a i;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.f.a j;
    /* access modifiers changed from: private */
    public boolean k;

    public enum a {
        VISIBLE,
        INVSIBLE,
        FADE_OUT_ON_PLAY
    }

    public d(View view, a aVar) {
        this(view, aVar, false);
    }

    public d(View view, a aVar, boolean z) {
        this(view, aVar, z, false);
    }

    public d(View view, @Nullable a aVar, boolean z, boolean z2) {
        this.a = new i() {
            public void a(h hVar) {
                d.this.a(1, 0);
            }
        };
        this.b = new k() {
            public void a(j jVar) {
                if (d.this.k) {
                    if (d.this.i == a.FADE_OUT_ON_PLAY || d.this.f) {
                        a unused = d.this.i = null;
                        d.this.c();
                        return;
                    }
                    d.this.a(0, 8);
                }
            }
        };
        this.c = new c() {
            public void a(com.facebook.ads.internal.view.f.b.b bVar) {
                if (d.this.i != a.INVSIBLE) {
                    d.this.h.setAlpha(1.0f);
                    d.this.h.setVisibility(0);
                }
            }
        };
        this.d = new u() {
            public void a(t tVar) {
                if (d.this.j != null && tVar.a().getAction() == 0) {
                    d.this.e.removeCallbacksAndMessages((Object) null);
                    d.this.a((AnimatorListenerAdapter) new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            d.this.e.postDelayed(new Runnable() {
                                public void run() {
                                    if (!d.this.g && d.this.k) {
                                        d.this.c();
                                    }
                                }
                            }, 2000);
                        }
                    });
                }
            }
        };
        this.k = true;
        this.e = new Handler();
        this.f = z;
        this.g = z2;
        a(view, aVar);
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3) {
        this.e.removeCallbacksAndMessages((Object) null);
        this.h.clearAnimation();
        this.h.setAlpha((float) i2);
        this.h.setVisibility(i3);
    }

    /* access modifiers changed from: private */
    public void a(AnimatorListenerAdapter animatorListenerAdapter) {
        this.h.setVisibility(0);
        this.h.animate().alpha(1.0f).setDuration(500).setListener(animatorListenerAdapter);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.h.animate().alpha(0.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                d.this.h.setVisibility(8);
            }
        });
    }

    public void a(View view, a aVar) {
        View view2;
        int i2;
        this.i = aVar;
        this.h = view;
        this.h.clearAnimation();
        if (aVar == a.INVSIBLE) {
            this.h.setAlpha(0.0f);
            view2 = this.h;
            i2 = 8;
        } else {
            this.h.setAlpha(1.0f);
            view2 = this.h;
            i2 = 0;
        }
        view2.setVisibility(i2);
    }

    public void a(com.facebook.ads.internal.view.f.a aVar) {
        this.j = aVar;
        aVar.getEventBus().a((T[]) new f[]{this.a, this.b, this.d, this.c});
    }

    public boolean a() {
        return this.k;
    }

    public void b() {
        this.k = false;
        a((AnimatorListenerAdapter) null);
    }

    public void b(com.facebook.ads.internal.view.f.a aVar) {
        a(1, 0);
        aVar.getEventBus().b((T[]) new f[]{this.c, this.d, this.b, this.a});
        this.j = null;
    }
}
