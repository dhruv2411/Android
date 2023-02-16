package com.facebook.ads.internal.n;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.m;
import com.facebook.ads.internal.adapters.x;
import com.facebook.ads.internal.adapters.y;
import com.facebook.ads.internal.adapters.z;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.v;
import com.facebook.ads.internal.view.w;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class f {
    private static final d b = d.ADS;
    private static final String c = "f";
    private static WeakHashMap<View, WeakReference<f>> d = new WeakHashMap<>();
    /* access modifiers changed from: private */
    public boolean A;
    /* access modifiers changed from: private */
    public boolean B;
    /* access modifiers changed from: private */
    public boolean C;
    /* access modifiers changed from: private */
    public boolean D;
    /* access modifiers changed from: private */
    public long E;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.b.c F;
    /* access modifiers changed from: private */
    public e G;
    /* access modifiers changed from: private */
    public x.a H;
    private View I;
    @Nullable
    protected y a;
    /* access modifiers changed from: private */
    public final Context e;
    private final String f;
    private final String g;
    private final com.facebook.ads.internal.d.b h;
    /* access modifiers changed from: private */
    @Nullable
    public i i;
    private final c j;
    /* access modifiers changed from: private */
    public DisplayAdController k;
    private volatile boolean l;
    @Nullable
    private com.facebook.ads.internal.h.d m;
    private com.facebook.ads.internal.protocol.f n;
    /* access modifiers changed from: private */
    @Nullable
    public View o;
    /* access modifiers changed from: private */
    @Nullable
    public g p;
    private final List<View> q;
    /* access modifiers changed from: private */
    public View.OnTouchListener r;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.r.a s;
    private a.C0006a t;
    /* access modifiers changed from: private */
    public WeakReference<a.C0006a> u;
    /* access modifiers changed from: private */
    public final u v;
    /* access modifiers changed from: private */
    @Nullable
    public x w;
    private a x;
    private w y;
    /* access modifiers changed from: private */
    public l z;

    private class a implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
        private a() {
        }

        public void onClick(View view) {
            String str;
            String str2;
            if (!f.this.v.d()) {
                Log.e("FBAudienceNetworkLog", "No touch data recorded, please ensure touch events reach the ad View by returning false if you intercept the event.");
            }
            int p = com.facebook.ads.internal.l.a.p(f.this.e);
            if (p < 0 || f.this.v.c() >= ((long) p)) {
                HashMap hashMap = new HashMap();
                hashMap.put("touch", k.a(f.this.v.e()));
                if (f.this.z != null) {
                    hashMap.put("nti", String.valueOf(f.this.z.c()));
                }
                if (f.this.A) {
                    hashMap.put("nhs", String.valueOf(f.this.A));
                }
                f.this.s.a((Map<String, String>) hashMap);
                if (f.this.a != null) {
                    f.this.a.b(hashMap);
                    return;
                }
                return;
            }
            if (!f.this.v.b()) {
                str = "FBAudienceNetworkLog";
                str2 = "Ad cannot be clicked before it is viewed.";
            } else {
                str = "FBAudienceNetworkLog";
                str2 = "Clicks happened too fast.";
            }
            Log.e(str, str2);
        }

        public boolean onLongClick(View view) {
            if (f.this.o == null || f.this.F == null) {
                return false;
            }
            f.this.F.setBounds(0, 0, f.this.o.getWidth(), f.this.o.getHeight());
            f.this.F.a(!f.this.F.a());
            return true;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            f.this.v.a(motionEvent, f.this.o, view);
            return f.this.r != null && f.this.r.onTouch(view, motionEvent);
        }
    }

    private class b extends com.facebook.ads.internal.adapters.c {
        private b() {
        }

        public void a() {
            if (f.this.i != null) {
                f.this.i.d();
            }
        }

        public void b() {
        }
    }

    public interface c {
        boolean a(View view);
    }

    public f(Context context, y yVar, com.facebook.ads.internal.h.d dVar, c cVar) {
        this(context, (String) null, cVar);
        this.a = yVar;
        this.m = dVar;
        this.l = true;
        this.I = new View(context);
    }

    public f(Context context, String str, c cVar) {
        this.g = UUID.randomUUID().toString();
        this.n = com.facebook.ads.internal.protocol.f.NATIVE_UNKNOWN;
        this.q = new ArrayList();
        this.v = new u();
        this.B = false;
        this.C = false;
        this.G = e.ALL;
        this.H = x.a.ALL;
        this.e = context;
        this.f = str;
        this.j = cVar;
        this.h = new com.facebook.ads.internal.d.b(context);
        this.I = new View(context);
    }

    public f(f fVar) {
        this(fVar.e, (String) null, fVar.j);
        this.m = fVar.m;
        this.a = fVar.a;
        this.l = true;
        this.I = new View(this.e);
    }

    /* access modifiers changed from: private */
    public AdPlacementType K() {
        return this.n == com.facebook.ads.internal.protocol.f.NATIVE_UNKNOWN ? AdPlacementType.NATIVE : AdPlacementType.NATIVE_BANNER;
    }

    /* access modifiers changed from: private */
    public boolean L() {
        return this.a != null && ((m) this.a).L();
    }

    private int M() {
        com.facebook.ads.internal.h.d a2;
        if (this.m != null) {
            a2 = this.m;
        } else if (this.k == null || this.k.a() == null) {
            return 1;
        } else {
            a2 = this.k.a();
        }
        return a2.f();
    }

    private int N() {
        com.facebook.ads.internal.h.d a2;
        if (this.m != null) {
            a2 = this.m;
        } else if (this.k == null || this.k.a() == null) {
            return 0;
        } else {
            a2 = this.k.a();
        }
        return a2.g();
    }

    private int O() {
        if (this.m != null) {
            return this.m.h();
        }
        if (this.a != null) {
            return this.a.i();
        }
        if (this.k == null || this.k.a() == null) {
            return 0;
        }
        return this.k.a().h();
    }

    private int P() {
        if (this.m != null) {
            return this.m.i();
        }
        if (this.a != null) {
            return this.a.j();
        }
        if (this.k == null || this.k.a() == null) {
            return 1000;
        }
        return this.k.a().i();
    }

    /* access modifiers changed from: private */
    public boolean Q() {
        return E() == m.ON;
    }

    private void R() {
        for (View next : this.q) {
            next.setOnClickListener((View.OnClickListener) null);
            next.setOnTouchListener((View.OnTouchListener) null);
            next.setOnLongClickListener((View.OnLongClickListener) null);
        }
        this.q.clear();
    }

    private void a(View view) {
        this.q.add(view);
        view.setOnClickListener(this.x);
        view.setOnTouchListener(this.x);
        if (com.facebook.ads.internal.l.a.b(view.getContext())) {
            view.setOnLongClickListener(this.x);
        }
    }

    /* access modifiers changed from: private */
    public void a(@Nullable final y yVar, final boolean z2) {
        if (yVar != null) {
            if (this.G.equals(e.ALL)) {
                if (yVar.k() != null) {
                    this.h.a(yVar.k().a(), yVar.k().c(), yVar.k().b());
                }
                if (yVar.l() != null) {
                    this.h.a(yVar.l().a(), yVar.l().c(), yVar.l().b());
                }
                if (yVar.F() != null) {
                    for (f next : yVar.F()) {
                        if (next.k() != null) {
                            this.h.a(next.k().a(), next.k().c(), next.k().b());
                        }
                    }
                }
                if (!TextUtils.isEmpty(yVar.A())) {
                    this.h.a(yVar.A());
                }
            }
            this.h.a((com.facebook.ads.internal.d.a) new com.facebook.ads.internal.d.a() {
                public void a() {
                    f.this.a = yVar;
                    if (f.this.i != null) {
                        if (f.this.G.equals(e.ALL) && !f.this.L()) {
                            f.this.i.a();
                        }
                        if (z2) {
                            f.this.i.b();
                        }
                    }
                }

                public void b() {
                    if (f.this.a != null) {
                        f.this.a.b_();
                        f.this.a = null;
                    }
                    if (f.this.i != null) {
                        f.this.i.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.CACHE_FAILURE_ERROR, "Failed to download a media."));
                    }
                }
            });
        }
    }

    public static void a(h hVar, ImageView imageView) {
        if (hVar != null && imageView != null) {
            new com.facebook.ads.internal.view.b.d(imageView).a(hVar.c(), hVar.b()).a(hVar.a());
        }
    }

    private void a(List<View> list, View view) {
        if (this.j != null && this.j.a(view)) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                a(list, viewGroup.getChildAt(i2));
            }
            return;
        }
        list.add(view);
    }

    public String A() {
        if (!g()) {
            return null;
        }
        return this.a.z();
    }

    public String B() {
        if (!g() || TextUtils.isEmpty(this.a.A())) {
            return null;
        }
        return this.h.b(this.a.A());
    }

    public String C() {
        if (!g()) {
            return null;
        }
        return this.a.B();
    }

    public String D() {
        if (!g()) {
            return null;
        }
        return this.a.E();
    }

    public m E() {
        return !g() ? m.DEFAULT : this.a.C();
    }

    public List<f> F() {
        if (!g()) {
            return null;
        }
        return this.a.F();
    }

    @Nullable
    public String G() {
        if (!g()) {
            return null;
        }
        return this.a.c();
    }

    public void H() {
        this.I.performClick();
    }

    public l I() {
        return this.z;
    }

    public void J() {
        if (this.o != null && this.p != null) {
            if (!d.containsKey(this.o) || d.get(this.o).get() != this) {
                throw new IllegalStateException("View not registered with this NativeAd");
            }
            if ((this.o instanceof ViewGroup) && this.y != null) {
                ((ViewGroup) this.o).removeView(this.y);
                this.y = null;
            }
            if (this.a != null) {
                this.a.b_();
            }
            if (this.F != null && com.facebook.ads.internal.l.a.b(this.e)) {
                this.F.b();
                this.o.getOverlay().remove(this.F);
            }
            d.remove(this.o);
            R();
            this.o = null;
            this.p = null;
            if (this.s != null) {
                this.s.c();
                this.s = null;
            }
            this.w = null;
        }
    }

    public y a() {
        return this.a;
    }

    public void a(View.OnTouchListener onTouchListener) {
        this.r = onTouchListener;
    }

    public void a(View view, g gVar) {
        ArrayList arrayList = new ArrayList();
        a((List<View>) arrayList, view);
        a(view, gVar, (List<View>) arrayList);
    }

    public void a(View view, g gVar, List<View> list) {
        com.facebook.ads.internal.view.b.c cVar;
        com.facebook.ads.internal.h.d a2;
        String str;
        String str2;
        if (view == null) {
            throw new IllegalArgumentException("Must provide a View");
        } else if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("Invalid set of clickable views");
        } else if (!g()) {
            Log.e(c, "Ad not loaded");
        } else if (gVar == null) {
            if (this.n == com.facebook.ads.internal.protocol.f.NATIVE_UNKNOWN) {
                if (this.i != null) {
                    this.i.a(new com.facebook.ads.internal.protocol.a(AdErrorType.NO_MEDIAVIEW_IN_NATIVEAD, "MediaView is missing."));
                }
                if (AdInternalSettings.isDebugBuild()) {
                    str = c;
                    str2 = "MediaView is missing.";
                } else {
                    return;
                }
            } else {
                if (this.i != null) {
                    this.i.a(new com.facebook.ads.internal.protocol.a(AdErrorType.NO_ICONVIEW_IN_NATIVEBANNERAD, "AdIconView is missing."));
                }
                if (AdInternalSettings.isDebugBuild()) {
                    str = c;
                    str2 = "AdIconView is missing.";
                } else {
                    return;
                }
            }
            Log.e(str, str2);
        } else if (gVar.getAdContentsView() != null) {
            if (this.o != null) {
                Log.w(c, "Native Ad was already registered with a View. Auto unregistering and proceeding.");
                J();
            }
            if (d.containsKey(view) && d.get(view).get() != null) {
                Log.w(c, "View already registered with a NativeAd. Auto unregistering and proceeding.");
                ((f) d.get(view).get()).J();
            }
            this.x = new a();
            this.o = view;
            this.p = gVar;
            if (view instanceof ViewGroup) {
                this.y = new w(view.getContext(), new v() {
                    public void a(int i) {
                        if (f.this.a != null) {
                            f.this.a.a(i);
                        }
                    }
                });
                ((ViewGroup) view).addView(this.y);
            }
            ArrayList<View> arrayList = new ArrayList<>(list);
            if (this.I != null) {
                arrayList.add(this.I);
            }
            for (View a3 : arrayList) {
                a(a3);
            }
            this.a.a(view, arrayList);
            int M = M();
            this.t = new a.C0006a() {
                public void a() {
                    if (!f.this.v.b()) {
                        f.this.v.a();
                        f.this.s.c();
                        if (!(f.this.u == null || f.this.u.get() == null)) {
                            ((a.C0006a) f.this.u.get()).a();
                        }
                        if (f.this.w != null && f.this.o != null && f.this.p != null) {
                            f.this.w.a(f.this.o);
                            f.this.w.a(f.this.p);
                            f.this.w.a(f.this.z);
                            f.this.w.a(f.this.A);
                            f.this.w.b(f.this.B);
                            f.this.w.d(f.this.C);
                            f.this.w.c(f.this.Q());
                            f.this.w.a(f.this.H);
                            f.this.w.e(f.this.D);
                            f.this.w.a();
                        }
                    }
                }
            };
            this.s = new com.facebook.ads.internal.r.a(gVar != null ? gVar.getAdContentsView() : this.o, M, N(), true, this.t);
            this.s.a(O());
            this.s.b(P());
            this.w = new x(this.e, new b(), this.s, this.a);
            this.w.a((List<View>) arrayList);
            d.put(view, new WeakReference(this));
            if (com.facebook.ads.internal.l.a.b(this.e)) {
                this.F = new com.facebook.ads.internal.view.b.c();
                this.F.a(this.f);
                this.F.b(this.e.getPackageName());
                this.F.a(this.s);
                if (this.a.H() > 0) {
                    this.F.a(this.a.H(), this.a.G());
                }
                if (this.m != null) {
                    cVar = this.F;
                    a2 = this.m;
                } else {
                    if (!(this.k == null || this.k.a() == null)) {
                        cVar = this.F;
                        a2 = this.k.a();
                    }
                    this.o.getOverlay().add(this.F);
                }
                cVar.a(a2.a());
                this.o.getOverlay().add(this.F);
            }
        } else if (this.i != null) {
            this.i.a(new com.facebook.ads.internal.protocol.a(AdErrorType.UNSUPPORTED_AD_ASSET_NATIVEAD, "ad media type is not supported."));
        }
    }

    public void a(z zVar) {
        if (this.a != null) {
            this.a.a(zVar);
        }
    }

    public void a(e eVar, String str) {
        if (this.l) {
            throw new IllegalStateException("loadAd cannot be called more than once");
        }
        this.E = System.currentTimeMillis();
        this.l = true;
        this.G = eVar;
        if (eVar.equals(e.NONE)) {
            this.H = x.a.NONE;
        }
        this.k = new DisplayAdController(this.e, this.f, this.n, K(), (e) null, b, 1, true);
        this.k.a((com.facebook.ads.internal.adapters.a) new com.facebook.ads.internal.adapters.a() {
            public void a() {
                if (f.this.i != null) {
                    f.this.i.c();
                }
            }

            public void a(AdAdapter adAdapter) {
                if (f.this.k != null) {
                    f.this.k.b();
                }
            }

            public void a(y yVar) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(a.b.LOADING_AD, f.this.K().toString(), System.currentTimeMillis() - f.this.E, (String) null));
                f.this.a(yVar, true);
                if (f.this.i != null && yVar.F() != null) {
                    AnonymousClass1 r0 = new z() {
                        public void a(y yVar) {
                        }

                        public void a(y yVar, com.facebook.ads.internal.protocol.a aVar) {
                        }

                        public void b(y yVar) {
                        }

                        public void c(y yVar) {
                            if (f.this.i != null) {
                                f.this.i.c();
                            }
                        }
                    };
                    for (f a2 : yVar.F()) {
                        a2.a((z) r0);
                    }
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (f.this.i != null) {
                    f.this.i.a(aVar);
                }
            }

            public void b() {
                throw new IllegalStateException("Native ads manager their own impressions.");
            }
        });
        this.k.a(str);
    }

    public void a(i iVar) {
        this.i = iVar;
    }

    public void a(l lVar) {
        this.z = lVar;
    }

    public void a(com.facebook.ads.internal.protocol.f fVar) {
        this.n = fVar;
    }

    public void a(a.C0006a aVar) {
        this.u = new WeakReference<>(aVar);
    }

    public void a(boolean z2) {
        this.A = z2;
    }

    public void a(boolean z2, boolean z3) {
        if (z2) {
            if (this.G.equals(e.NONE) && !L() && this.i != null) {
                this.i.a();
            }
            if (this.s != null) {
                this.s.a();
                return;
            }
            return;
        }
        if (this.s != null) {
            this.s.c();
        }
        if (this.i != null && z3) {
            this.i.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.BROKEN_MEDIA_ERROR, "Failed to load Media."));
        }
    }

    @Nullable
    public c b() {
        if (!g() || this.a == null) {
            return null;
        }
        return this.a.I();
    }

    public void b(boolean z2) {
        this.D = z2;
    }

    public void c(boolean z2) {
        this.B = z2;
    }

    public boolean c() {
        return this.k == null || this.k.d();
    }

    public void d() {
        if (this.G.equals(e.NONE)) {
            this.H = x.a.MANUAL;
        }
        this.G = e.ALL;
        a(this.a, false);
    }

    public void d(boolean z2) {
        this.C = z2;
    }

    public void e() {
        if (this.k != null) {
            this.k.b(true);
            this.k = null;
        }
    }

    public String f() {
        return this.f;
    }

    public boolean g() {
        return this.a != null && this.a.c_();
    }

    public boolean h() {
        return g() && this.a.f();
    }

    public boolean i() {
        return this.a != null && this.a.a_();
    }

    public h j() {
        if (!g()) {
            return null;
        }
        return this.a.k();
    }

    public h k() {
        if (!g()) {
            return null;
        }
        return this.a.l();
    }

    public k l() {
        if (!g()) {
            return null;
        }
        return this.a.m();
    }

    public String m() {
        if (!g()) {
            return null;
        }
        return this.a.n();
    }

    public String n() {
        if (!g()) {
            return null;
        }
        return this.a.o();
    }

    public String o() {
        if (!g()) {
            return null;
        }
        return this.a.p();
    }

    public String p() {
        if (!g()) {
            return null;
        }
        return this.a.K();
    }

    public String q() {
        if (!g()) {
            return null;
        }
        return this.a.q();
    }

    public String r() {
        if (!g()) {
            return null;
        }
        return this.a.r();
    }

    public String s() {
        if (!g()) {
            return null;
        }
        return this.a.s();
    }

    public String t() {
        if (!g()) {
            return null;
        }
        return this.a.t();
    }

    public String u() {
        if (!g()) {
            return null;
        }
        return this.a.u();
    }

    public String v() {
        if (!g()) {
            return null;
        }
        return this.a.v();
    }

    public j w() {
        if (!g()) {
            return null;
        }
        return this.a.w();
    }

    public String x() {
        if (!g()) {
            return null;
        }
        return this.g;
    }

    public h y() {
        if (!g()) {
            return null;
        }
        return this.a.x();
    }

    public String z() {
        if (!g()) {
            return null;
        }
        return this.a.y();
    }
}
