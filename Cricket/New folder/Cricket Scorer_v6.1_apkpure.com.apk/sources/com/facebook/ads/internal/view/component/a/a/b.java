package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.b.v;
import com.facebook.ads.internal.view.f.b.w;
import com.facebook.ads.internal.view.s;
import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class b extends com.facebook.ads.internal.view.component.a.b {
    private static final int c = ((int) (1.0f * x.b));
    private static final int d = ((int) (4.0f * x.b));
    private static final int e = ((int) (6.0f * x.b));
    private s f;
    private com.facebook.ads.internal.view.c.a.e g;
    private RelativeLayout h;
    private final String i;
    private final Paint j;
    private boolean k;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.view.c.a.a l;
    private final Path m = new Path();
    private final RectF n = new RectF();
    /* access modifiers changed from: private */
    public boolean o;
    /* access modifiers changed from: private */
    public boolean p;
    private a q;
    private final w r = new w() {
        public void a(v vVar) {
            b.this.l.a().a(b.this.getVideoView().getVolume());
        }
    };
    private final com.facebook.ads.internal.view.f.b.c s = new com.facebook.ads.internal.view.f.b.c() {
        public void a(com.facebook.ads.internal.view.f.b.b bVar) {
            b.this.l.b().a(((Integer) b.this.getTag(-1593835536)).intValue());
        }
    };
    private final k t = new k() {
        public void a(j jVar) {
            b.this.l.c().a(b.this);
        }
    };
    private final i u = new i() {
        public void a(h hVar) {
            b.this.l.c().b(b.this);
        }
    };
    private final m v = new m() {
        public void a(l lVar) {
            boolean unused = b.this.p = true;
            b.this.k();
        }
    };

    public interface a {
        void a();
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$b  reason: collision with other inner class name */
    private static class C0011b implements com.facebook.ads.internal.view.b.e {
        final WeakReference<b> a;

        private C0011b(b bVar) {
            this.a = new WeakReference<>(bVar);
        }

        public void a(boolean z) {
            b bVar = (b) this.a.get();
            if (bVar != null) {
                boolean unused = bVar.o = z;
                bVar.k();
            }
        }
    }

    public interface c {
        void a(int i);
    }

    public interface d {
        void a(View view);

        void b(View view);
    }

    public interface e {
        float a();

        void a(float f);
    }

    b(com.facebook.ads.internal.view.component.a.d dVar, com.facebook.ads.internal.adapters.a.d dVar2, boolean z, String str, com.facebook.ads.internal.view.c.a.a aVar) {
        super(dVar, dVar2, z);
        this.l = aVar;
        this.i = str;
        setGravity(17);
        setPadding(c, 0, c, c);
        x.a((View) this, 0);
        setUpView(getContext());
        this.j = new Paint();
        this.j.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.j.setStyle(Paint.Style.FILL);
        this.j.setAlpha(16);
        this.j.setAntiAlias(true);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, (Paint) null);
        }
    }

    private void a(View view) {
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        x.a(view);
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.q != null) {
            if ((f() && this.p) || (!f() && this.o)) {
                this.q.a();
            }
        }
    }

    private void setUpView(Context context) {
        setUpImageView(context);
        setUpVideoView(context);
        setUpMediaContainer(context);
        this.h.addView(this.f);
        this.h.addView(this.g);
        a(context);
    }

    /* access modifiers changed from: protected */
    public abstract void a(Context context);

    public void a(String str, String str2) {
        getTitleDescContainer().a(str, str2, true, false);
    }

    public void a(String str, String str2, Map<String, String> map) {
        getCtaButton().a(str, str2, this.i, map);
    }

    public void a(Map<String, String> map) {
        this.g.c();
        if (f()) {
            this.g.a(getAdEventManager(), this.i, map);
        }
    }

    public boolean a() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return false;
    }

    public boolean f() {
        return this.k;
    }

    public boolean g() {
        return f() && this.g.b();
    }

    /* access modifiers changed from: protected */
    public final RelativeLayout getMediaContainer() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public final com.facebook.ads.internal.view.c.a.e getVideoView() {
        return this.g;
    }

    public void h() {
        if (f()) {
            j();
            this.g.a(com.facebook.ads.internal.view.f.a.a.AUTO_STARTED);
        }
    }

    public void i() {
        if (f()) {
            this.g.a();
        }
    }

    public void j() {
        float a2 = this.l.a().a();
        if (f() && a2 != this.g.getVolume()) {
            this.g.setVolume(a2);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.m.reset();
        this.n.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.m.addRoundRect(this.n, (float) e, (float) e, Path.Direction.CW);
        canvas.drawPath(this.m, this.j);
        this.n.set((float) c, 0.0f, (float) (getWidth() - c), (float) (getHeight() - c));
        this.m.addRoundRect(this.n, (float) d, (float) d, Path.Direction.CW);
        canvas.clipPath(this.m);
        super.onDraw(canvas);
    }

    public void setImageUrl(String str) {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        new com.facebook.ads.internal.view.b.d((ImageView) this.f).a().a((com.facebook.ads.internal.view.b.e) new C0011b()).a(str);
    }

    public void setIsVideo(boolean z) {
        this.k = z;
    }

    public void setOnAssetsLoadedListener(a aVar) {
        this.q = aVar;
    }

    /* access modifiers changed from: protected */
    public void setUpImageView(Context context) {
        this.f = new s(context);
        a((View) this.f);
    }

    /* access modifiers changed from: protected */
    public void setUpMediaContainer(Context context) {
        this.h = new RelativeLayout(context);
        a((View) this.h);
    }

    /* access modifiers changed from: protected */
    public void setUpVideoView(Context context) {
        this.g = new com.facebook.ads.internal.view.c.a.e(context, getAdEventManager());
        a((View) this.g);
    }

    public void setVideoPlaceholderUrl(String str) {
        this.g.setPlaceholderUrl(str);
    }

    public void setVideoUrl(String str) {
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.g.setVideoURI(str);
        this.g.a((f) this.r);
        this.g.a((f) this.s);
        this.g.a((f) this.t);
        this.g.a((f) this.u);
        this.g.a((f) this.v);
    }
}
