package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.view.component.a.d;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.d;
import com.facebook.ads.internal.view.f.b.e;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.c.d;
import com.facebook.ads.internal.view.f.c.f;
import com.facebook.ads.internal.view.f.c.o;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class h extends i {
    private final AudienceNetworkActivity.BackButtonInterceptor e = new AudienceNetworkActivity.BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return !h.this.c.a();
        }
    };
    private final e f = new e() {
        public void a(d dVar) {
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", dVar);
            }
            if (!h.this.z) {
                h.this.k.f();
                h.this.k.k();
                boolean unused = h.this.z = true;
            }
            if (h.this.w != null) {
                h.this.w.finish();
            }
        }
    };
    private final k g = new k() {
        public void a(j jVar) {
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", jVar);
            }
        }
    };
    private final i h = new i() {
        public void a(com.facebook.ads.internal.view.f.b.h hVar) {
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", hVar);
            }
        }
    };
    private final c i = new c() {
        public void a(b bVar) {
            h.this.t.set(true);
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", bVar);
            }
        }
    };
    private final m j = new m() {
        public void a(l lVar) {
            if (!h.this.z) {
                h.this.u.set(h.this.k.j());
                h.this.a();
            }
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", lVar);
            }
            h.this.p.a();
        }
    };
    /* access modifiers changed from: private */
    public final a k = new a(getContext());
    private final o l;
    private final f m;
    /* access modifiers changed from: private */
    public final g n;
    private final com.facebook.ads.internal.adapters.a.h o;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.r.a p;
    private final a.C0006a q;
    /* access modifiers changed from: private */
    public final u r = new u();
    @Nullable
    private final com.facebook.ads.internal.d.b s;
    /* access modifiers changed from: private */
    public final AtomicBoolean t = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final AtomicBoolean u = new AtomicBoolean(false);
    private final com.facebook.ads.internal.view.f.c v;
    /* access modifiers changed from: private */
    @Nullable
    public AudienceNetworkActivity w;
    @Nullable
    private com.facebook.ads.internal.view.f.a.a x;
    private long y;
    /* access modifiers changed from: private */
    public boolean z = false;

    public h(Context context, com.facebook.ads.internal.m.c cVar, g gVar, @Nullable com.facebook.ads.internal.d.b bVar) {
        super(context, cVar);
        this.k.setVideoProgressReportIntervalMs(gVar.h());
        x.a((View) this.k);
        x.a((View) this.k, 0);
        this.n = gVar;
        this.o = this.n.d().get(0);
        this.s = bVar;
        this.l = new o(getContext());
        this.m = new f(context);
        this.k.getEventBus().a((T[]) new com.facebook.ads.internal.j.f[]{this.g, this.h, this.i, this.f, this.j});
        setupPlugins(this.o);
        this.q = new a.C0006a() {
            public void a() {
                if (!h.this.r.b()) {
                    h.this.r.a();
                    HashMap hashMap = new HashMap();
                    if (!TextUtils.isEmpty(h.this.n.c())) {
                        h.this.p.a((Map<String, String>) hashMap);
                        hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(h.this.r.e()));
                        h.this.b.a(h.this.n.c(), hashMap);
                        if (h.this.getAudienceNetworkListener() != null) {
                            h.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                        }
                    }
                }
            }
        };
        this.p = new com.facebook.ads.internal.r.a(this, 1, this.q);
        this.p.a(gVar.f());
        this.p.b(gVar.g());
        this.v = new com.facebook.ads.internal.view.f.b(getContext(), this.b, this.k, this.n.c());
        this.k.setVideoURI(a(this.o.c().a()));
    }

    private String a(String str) {
        String str2 = "";
        if (!(this.s == null || str == null)) {
            str2 = this.s.b(str);
        }
        return TextUtils.isEmpty(str2) ? str : str2;
    }

    /* access modifiers changed from: private */
    public void a() {
        this.m.setVisibility(this.u.get() ? 0 : 8);
    }

    private void setUpContent(int i2) {
        com.facebook.ads.internal.view.component.a.b a = com.facebook.ads.internal.view.component.a.c.a(new d.a(getContext(), this.b, getAudienceNetworkListener(), this.n, this.k, this.p, this.r).a(a).b(i2).a(this.l).a((View) this.m).a());
        a();
        a(a, a.a(), i2);
    }

    private void setupPlugins(com.facebook.ads.internal.adapters.a.h hVar) {
        this.k.d();
        this.k.a((com.facebook.ads.internal.view.f.a.b) this.l);
        this.k.a((com.facebook.ads.internal.view.f.a.b) this.m);
        if (!TextUtils.isEmpty(hVar.c().g())) {
            com.facebook.ads.internal.view.f.c.g gVar = new com.facebook.ads.internal.view.f.c.g(getContext());
            this.k.a((com.facebook.ads.internal.view.f.a.b) gVar);
            gVar.setImage(hVar.c().g());
        }
        com.facebook.ads.internal.view.f.c.l lVar = new com.facebook.ads.internal.view.f.c.l(getContext(), true);
        this.k.a((com.facebook.ads.internal.view.f.a.b) lVar);
        this.k.a((com.facebook.ads.internal.view.f.a.b) new com.facebook.ads.internal.view.f.c.d(lVar, hVar.c().e() ? d.a.FADE_OUT_ON_PLAY : d.a.VISIBLE, true));
        this.k.a((com.facebook.ads.internal.view.f.a.b) new com.facebook.ads.internal.view.f.c.k(getContext()));
        this.k.a((com.facebook.ads.internal.view.f.a.b) this.c);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.n);
        this.w = audienceNetworkActivity;
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.w.addBackButtonInterceptor(this.e);
        com.facebook.ads.internal.adapters.a.h hVar = this.n.d().get(0);
        if (hVar.c().e()) {
            this.k.setVolume(hVar.c().f() ? 1.0f : 0.0f);
            this.k.a(com.facebook.ads.internal.view.f.a.a.AUTO_STARTED);
        }
        this.y = System.currentTimeMillis();
    }

    public void a(Bundle bundle) {
    }

    public void i() {
        if (!this.z && this.k.getState() == com.facebook.ads.internal.view.f.d.d.STARTED) {
            this.x = this.k.getVideoStartReason();
            this.k.a(false);
        }
    }

    public void j() {
        if (!this.z && this.x != null) {
            this.k.a(this.x);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        x.b(this.k);
        x.b(this.l);
        x.b(this.m);
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        if (!this.z) {
            if (!this.t.get()) {
                this.k.e();
            }
            if (this.n != null) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.y, a.C0004a.XOUT, this.n.e()));
                if (!TextUtils.isEmpty(this.n.c())) {
                    HashMap hashMap = new HashMap();
                    this.p.a((Map<String, String>) hashMap);
                    hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(this.r.e()));
                    this.b.i(this.n.c(), hashMap);
                }
            }
            this.k.f();
            this.k.k();
            this.z = true;
        }
        this.p.c();
        this.w = null;
        super.onDestroy();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.r.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
