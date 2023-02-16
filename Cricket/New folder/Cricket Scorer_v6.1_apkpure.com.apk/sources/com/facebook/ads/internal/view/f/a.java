package com.facebook.ads.internal.view.f;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.d;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.b.n;
import com.facebook.ads.internal.view.f.b.p;
import com.facebook.ads.internal.view.f.b.r;
import com.facebook.ads.internal.view.f.b.s;
import com.facebook.ads.internal.view.f.b.t;
import com.facebook.ads.internal.view.f.b.v;
import com.facebook.ads.internal.view.f.b.x;
import com.facebook.ads.internal.view.f.b.y;
import com.facebook.ads.internal.view.f.c;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.d.e;
import java.util.ArrayList;
import java.util.List;

public class a extends RelativeLayout implements c.a, e {
    /* access modifiers changed from: private */
    public static final l b = new l();
    /* access modifiers changed from: private */
    public static final d c = new d();
    /* access modifiers changed from: private */
    public static final r d = new r();
    /* access modifiers changed from: private */
    public static final h e = new h();
    /* access modifiers changed from: private */
    public static final s f = new s();
    /* access modifiers changed from: private */
    public static final j g = new j();
    private static final v h = new v();
    private static final y i = new y();
    private static final x j = new x();
    protected final com.facebook.ads.internal.view.f.d.c a;
    private d k;
    private final List<b> l = new ArrayList();
    /* access modifiers changed from: private */
    public final Handler m = new Handler();
    private final Handler n = new Handler();
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.j.e<f, com.facebook.ads.internal.j.d> o = new com.facebook.ads.internal.j.e<>();
    /* access modifiers changed from: private */
    public boolean p;
    private boolean q;
    private boolean r = false;
    /* access modifiers changed from: private */
    public int s = 200;
    private final View.OnTouchListener t = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            a.this.o.a(new t(view, motionEvent));
            return false;
        }
    };

    public a(Context context) {
        super(context);
        this.a = com.facebook.ads.internal.l.a.a(context) ? new com.facebook.ads.internal.view.f.d.a(context) : new com.facebook.ads.internal.view.f.d.b(context);
        a();
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = com.facebook.ads.internal.l.a.a(context) ? new com.facebook.ads.internal.view.f.d.a(context, attributeSet) : new com.facebook.ads.internal.view.f.d.b(context, attributeSet);
        a();
    }

    public a(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = com.facebook.ads.internal.l.a.a(context) ? new com.facebook.ads.internal.view.f.d.a(context, attributeSet, i2) : new com.facebook.ads.internal.view.f.d.b(context, attributeSet, i2);
        a();
    }

    @TargetApi(21)
    public a(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.a = com.facebook.ads.internal.l.a.a(context) ? new com.facebook.ads.internal.view.f.d.a(context, attributeSet, i2, i3) : new com.facebook.ads.internal.view.f.d.b(context, attributeSet, i2, i3);
        a();
    }

    private void a() {
        if (g() && (this.a instanceof com.facebook.ads.internal.view.f.d.a)) {
            ((com.facebook.ads.internal.view.f.d.a) this.a).setTestMode(AdInternalSettings.isTestMode(getContext()));
        }
        this.a.setRequestedVolume(1.0f);
        this.a.setVideoStateChangeListener(this);
        this.k = new d(getContext(), this.a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.k, layoutParams);
        setOnTouchListener(this.t);
    }

    private void a(com.facebook.ads.internal.view.f.a.c cVar) {
        if (cVar.getParent() != null) {
            return;
        }
        if (cVar instanceof g) {
            this.k.a(cVar);
        } else {
            addView(cVar);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.m.postDelayed(new Runnable() {
            public void run() {
                if (!a.this.p) {
                    a.this.o.a(new n(a.this.getCurrentPositionInMillis()));
                    a.this.m.postDelayed(this, (long) a.this.s);
                }
            }
        }, (long) this.s);
    }

    private void b(com.facebook.ads.internal.view.f.a.c cVar) {
        if (cVar instanceof g) {
            this.k.b(cVar);
        } else {
            com.facebook.ads.internal.q.a.x.b(cVar);
        }
    }

    public void a(int i2) {
        this.m.removeCallbacksAndMessages((Object) null);
        this.a.a(i2);
    }

    public void a(final int i2, final int i3) {
        this.n.post(new Runnable() {
            public void run() {
                a.this.o.a(new p(i2, i3));
            }
        });
        b();
    }

    public void a(com.facebook.ads.internal.view.f.a.a aVar) {
        if (this.p && this.a.getState() == com.facebook.ads.internal.view.f.d.d.PLAYBACK_COMPLETED) {
            this.p = false;
        }
        this.a.a(aVar);
    }

    public void a(b bVar) {
        this.l.add(bVar);
    }

    public void a(final com.facebook.ads.internal.view.f.d.d dVar) {
        final int currentPositionInMillis = getCurrentPositionInMillis();
        final int duration = getDuration();
        this.n.post(new Runnable() {
            public void run() {
                com.facebook.ads.internal.j.e b2;
                com.facebook.ads.internal.j.d r;
                com.facebook.ads.internal.j.e b3;
                com.facebook.ads.internal.j.d bVar;
                if (dVar == com.facebook.ads.internal.view.f.d.d.PREPARED) {
                    b3 = a.this.o;
                    bVar = a.b;
                } else if (dVar == com.facebook.ads.internal.view.f.d.d.ERROR) {
                    boolean unused = a.this.p = true;
                    b3 = a.this.o;
                    bVar = a.c;
                } else if (dVar == com.facebook.ads.internal.view.f.d.d.PLAYBACK_COMPLETED) {
                    boolean unused2 = a.this.p = true;
                    a.this.m.removeCallbacksAndMessages((Object) null);
                    b3 = a.this.o;
                    bVar = new com.facebook.ads.internal.view.f.b.b(currentPositionInMillis, duration);
                } else if (dVar == com.facebook.ads.internal.view.f.d.d.STARTED) {
                    a.this.o.a(a.g);
                    a.this.m.removeCallbacksAndMessages((Object) null);
                    a.this.b();
                    return;
                } else {
                    if (dVar == com.facebook.ads.internal.view.f.d.d.PAUSED) {
                        b2 = a.this.o;
                        r = a.e;
                    } else if (dVar == com.facebook.ads.internal.view.f.d.d.IDLE) {
                        b2 = a.this.o;
                        r = a.f;
                    } else {
                        return;
                    }
                    b2.a(r);
                    a.this.m.removeCallbacksAndMessages((Object) null);
                    return;
                }
                b3.a(bVar);
            }
        });
    }

    public void a(boolean z) {
        if (!l()) {
            this.a.a(z);
            this.r = z;
        }
    }

    public void c() {
        for (b next : this.l) {
            if (next instanceof com.facebook.ads.internal.view.f.a.c) {
                a((com.facebook.ads.internal.view.f.a.c) next);
            }
            next.a(this);
        }
    }

    public void d() {
        for (b next : this.l) {
            if (next instanceof com.facebook.ads.internal.view.f.a.c) {
                b((com.facebook.ads.internal.view.f.a.c) next);
            }
            next.b(this);
        }
    }

    public void e() {
        this.n.post(new Runnable() {
            public void run() {
                a.this.getEventBus().a(a.d);
            }
        });
        this.a.b();
    }

    public void f() {
        this.a.c();
    }

    public boolean g() {
        return com.facebook.ads.internal.l.a.a(getContext());
    }

    public int getCurrentPositionInMillis() {
        return this.a.getCurrentPosition();
    }

    public int getDuration() {
        return this.a.getDuration();
    }

    @NonNull
    public com.facebook.ads.internal.j.e<f, com.facebook.ads.internal.j.d> getEventBus() {
        return this.o;
    }

    public long getInitialBufferTime() {
        return this.a.getInitialBufferTime();
    }

    public com.facebook.ads.internal.view.f.d.d getState() {
        return this.a.getState();
    }

    /* access modifiers changed from: protected */
    public Handler getStateHandler() {
        return this.n;
    }

    public TextureView getTextureView() {
        return (TextureView) this.a;
    }

    public int getVideoHeight() {
        return this.a.getVideoHeight();
    }

    public int getVideoProgressReportIntervalMs() {
        return this.s;
    }

    public com.facebook.ads.internal.view.f.a.a getVideoStartReason() {
        return this.a.getStartReason();
    }

    public View getVideoView() {
        return this.k;
    }

    public int getVideoWidth() {
        return this.a.getVideoWidth();
    }

    public float getVolume() {
        return this.a.getVolume();
    }

    public boolean h() {
        return this.q;
    }

    public boolean i() {
        return getState() == com.facebook.ads.internal.view.f.d.d.STARTED;
    }

    public boolean j() {
        return this.a.d();
    }

    public void k() {
        this.a.setVideoStateChangeListener((e) null);
        this.a.e();
    }

    public boolean l() {
        return getState() == com.facebook.ads.internal.view.f.d.d.PAUSED;
    }

    public boolean m() {
        return l() && this.r;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        this.o.a(j);
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.o.a(i);
        super.onDetachedFromWindow();
    }

    public void setControlsAnchorView(View view) {
        if (this.a != null) {
            this.a.setControlsAnchorView(view);
        }
    }

    public void setIsFullScreen(boolean z) {
        this.q = z;
        this.a.setFullScreen(z);
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    public void setVideoMPD(@Nullable String str) {
        this.a.setVideoMPD(str);
    }

    public void setVideoProgressReportIntervalMs(int i2) {
        this.s = i2;
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null) {
            d();
        } else {
            c();
            this.a.setup(uri);
        }
        this.p = false;
    }

    public void setVideoURI(@Nullable String str) {
        setVideoURI(str != null ? Uri.parse(str) : null);
    }

    public void setVolume(float f2) {
        this.a.setRequestedVolume(f2);
        getEventBus().a(h);
    }
}
