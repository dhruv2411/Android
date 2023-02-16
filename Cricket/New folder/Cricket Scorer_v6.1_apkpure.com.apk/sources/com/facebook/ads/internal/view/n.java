package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.j;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.adapters.aa;
import com.facebook.ads.internal.q.a.f;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.q.c.e;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.b.a;
import com.facebook.ads.internal.view.f;
import com.facebook.ads.internal.view.f.b.z;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(16)
public class n extends RelativeLayout implements f.a, a, a.d {
    private static final RelativeLayout.LayoutParams a = new RelativeLayout.LayoutParams(-1, -1);
    private final RelativeLayout b;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.m.c c;
    /* access modifiers changed from: private */
    public final k d;
    private final j e;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.adapters.a.a f;
    private final f g;
    /* access modifiers changed from: private */
    public final f h;
    private final f i;
    private int j;
    /* access modifiers changed from: private */
    public WeakReference<com.facebook.ads.internal.view.b.a> k;
    /* access modifiers changed from: private */
    public boolean l = false;
    /* access modifiers changed from: private */
    @Nullable
    public Context m;
    @Nullable
    private AudienceNetworkActivity n;
    /* access modifiers changed from: private */
    @Nullable
    public a.C0008a o;
    private a.b p;
    /* access modifiers changed from: private */
    public final AtomicBoolean q = new AtomicBoolean();
    private Executor r = AsyncTask.THREAD_POOL_EXECUTOR;
    private final AudienceNetworkActivity.BackButtonInterceptor s = new AudienceNetworkActivity.BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return !n.this.h.d();
        }
    };
    /* access modifiers changed from: private */
    public aa t;

    private static class a implements View.OnTouchListener {
        final WeakReference<com.facebook.ads.internal.view.b.a> a;
        final com.facebook.ads.internal.m.c b;
        final k c;

        private a(com.facebook.ads.internal.view.b.a aVar, com.facebook.ads.internal.m.c cVar, k kVar) {
            this.a = new WeakReference<>(aVar);
            this.b = cVar;
            this.c = kVar;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.a.get() == null || motionEvent.getAction() != 1) {
                return false;
            }
            HashMap hashMap = new HashMap();
            ((com.facebook.ads.internal.view.b.a) this.a.get()).getViewabilityChecker().a((Map<String, String>) hashMap);
            hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(((com.facebook.ads.internal.view.b.a) this.a.get()).getTouchDataRecorder().e()));
            this.b.d(this.c.g(), hashMap);
            return false;
        }
    }

    private class b {
        private b() {
        }

        @JavascriptInterface
        public void onCTAClick() {
            if (n.this.k.get() != null) {
                com.facebook.ads.internal.view.b.a aVar = (com.facebook.ads.internal.view.b.a) n.this.k.get();
                com.facebook.ads.internal.view.component.a aVar2 = new com.facebook.ads.internal.view.component.a(n.this.m, true, false, z.REWARDED_VIDEO_AD_CLICK.a(), n.this.f.a(), n.this.c, n.this.o, aVar.getViewabilityChecker(), aVar.getTouchDataRecorder());
                aVar2.a(n.this.d.c(), n.this.d.g(), new HashMap());
                aVar2.performClick();
            }
        }
    }

    private static class c implements e.a {
        final WeakReference<a.C0008a> a;

        private c(WeakReference<a.C0008a> weakReference) {
            this.a = weakReference;
        }

        public void a() {
            if (this.a.get() != null) {
                ((a.C0008a) this.a.get()).a(z.REWARD_SERVER_FAILED.a());
            }
        }

        public void a(com.facebook.ads.internal.q.c.f fVar) {
            a.C0008a aVar;
            z zVar;
            if (this.a.get() != null) {
                if (fVar == null || !fVar.a()) {
                    aVar = (a.C0008a) this.a.get();
                    zVar = z.REWARD_SERVER_FAILED;
                } else {
                    aVar = (a.C0008a) this.a.get();
                    zVar = z.REWARD_SERVER_SUCCESS;
                }
                aVar.a(zVar.a());
            }
        }
    }

    public n(Context context, com.facebook.ads.internal.m.c cVar, a.C0008a aVar, k kVar) {
        super(context);
        this.m = context;
        this.o = aVar;
        this.c = cVar;
        this.d = kVar;
        this.e = kVar.e().j();
        this.f = kVar.d();
        this.b = new RelativeLayout(context);
        this.g = new f(context);
        this.h = new f(this.e.b(), this);
        this.i = new f(3, new f.a() {
            public void a() {
                n.this.c();
            }

            public void a(int i) {
            }
        });
    }

    private void a(AudienceNetworkActivity audienceNetworkActivity) {
        this.j = audienceNetworkActivity.getRequestedOrientation();
        audienceNetworkActivity.setRequestedOrientation(1);
    }

    private void a(com.facebook.ads.internal.view.b.a aVar) {
        if (this.m != null) {
            this.t = new aa(this.m, this.c, aVar.getViewabilityChecker(), aVar.getTouchDataRecorder(), new com.facebook.ads.internal.adapters.c() {
                public void a() {
                    if (n.this.o != null) {
                        n.this.o.a(z.REWARDED_VIDEO_IMPRESSION.a());
                    }
                }
            });
            this.t.a(this.d);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        com.facebook.ads.internal.view.b.a d2 = d();
        d2.loadUrl(this.e.a());
        d2.setOnTouchListener(new a(d2, this.c, this.d));
        d2.addJavascriptInterface(new b(), "FbPlayableAd");
        x.a((View) this.b, this.f.a().d(true));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, this.g.getId());
        d2.setLayoutParams(layoutParams);
        d2.setVisibility(4);
        d2.setOnAssetsLoadedListener(this);
        this.b.addView(this.g);
        this.b.addView(d2);
    }

    private com.facebook.ads.internal.view.b.a d() {
        this.p = new a.c() {
            public void a(@Nullable WebResourceError webResourceError) {
                boolean unused = n.this.l = true;
                if (n.this.k.get() != null) {
                    ((com.facebook.ads.internal.view.b.a) n.this.k.get()).setVisibility(4);
                }
                if (n.this.o != null) {
                    n.this.o.a(z.REWARDED_VIDEO_ERROR.a());
                }
            }

            public void b() {
                if (n.this.q.compareAndSet(false, true)) {
                    n.this.h.a();
                    n.this.t.a();
                }
            }
        };
        com.facebook.ads.internal.view.b.a aVar = new com.facebook.ads.internal.view.b.a(this.m, new WeakReference(this.p), 10);
        aVar.setLogMultipleImpressions(false);
        aVar.setWaitForAssetsToLoad(true);
        aVar.setCheckAssetsByJavascriptBridge(false);
        WebSettings settings = aVar.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        a(aVar);
        this.k = new WeakReference<>(aVar);
        return aVar;
    }

    private void e() {
        String a2 = this.d.f().a();
        if (this.m != null || !TextUtils.isEmpty(a2)) {
            e eVar = new e(this.m, new HashMap());
            eVar.a((e.a) new c(new WeakReference(this.o)));
            eVar.executeOnExecutor(this.r, new String[]{a2});
        }
    }

    private void f() {
        if (this.o != null) {
            this.o.a(z.REWARDED_VIDEO_COMPLETE.a(), new com.facebook.ads.internal.view.f.b.b(0, 0));
        }
    }

    public void a() {
        this.g.a(true);
        e();
        f();
    }

    public void a(int i2) {
        this.g.setProgress(100.0f * (1.0f - (((float) i2) / ((float) this.e.b()))));
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.o != null && this.m != null) {
            this.n = audienceNetworkActivity;
            this.n.addBackButtonInterceptor(this.s);
            a(audienceNetworkActivity);
            this.g.a(this.f.a(), true);
            this.g.setShowPageDetails(false);
            this.g.a(this.d.a(), this.d.g(), this.e.b());
            this.g.setToolbarListener(new f.a() {
                public void a() {
                    if (n.this.o != null) {
                        n.this.o.a(z.REWARDED_VIDEO_END_ACTIVITY.a());
                    }
                }
            });
            x.a((View) this.g);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(10);
            this.g.setLayoutParams(layoutParams);
            com.facebook.ads.internal.view.d.a aVar = new com.facebook.ads.internal.view.d.a(this.m, this.d);
            this.b.setLayoutParams(a);
            x.a((View) this.b, this.f.a().d(true));
            this.b.addView(aVar, a);
            addView(this.b);
            setLayoutParams(a);
            this.o.a((View) this);
            this.i.a();
        }
    }

    public void a(Bundle bundle) {
    }

    public void b() {
        if (!this.l && this.k.get() != null) {
            ((com.facebook.ads.internal.view.b.a) this.k.get()).setVisibility(0);
        }
    }

    public void i() {
        this.i.b();
        this.h.b();
    }

    public void j() {
        com.facebook.ads.internal.q.a.f fVar;
        if (!this.i.d()) {
            fVar = this.i;
        } else if (!this.h.c()) {
            fVar = this.h;
        } else {
            return;
        }
        fVar.a();
    }

    public void onDestroy() {
        this.i.b();
        this.h.b();
        this.g.setToolbarListener((f.a) null);
        if (this.n != null) {
            this.n.removeBackButtonInterceptor(this.s);
            this.n.setRequestedOrientation(this.j);
        }
        com.facebook.ads.internal.view.b.a aVar = (com.facebook.ads.internal.view.b.a) this.k.get();
        if (aVar != null) {
            aVar.removeJavascriptInterface("FbPlayableAd");
        }
        if (aVar != null && !TextUtils.isEmpty(this.d.g())) {
            HashMap hashMap = new HashMap();
            aVar.getViewabilityChecker().a((Map<String, String>) hashMap);
            hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(aVar.getTouchDataRecorder().e()));
            this.c.i(this.d.g(), hashMap);
        }
        this.o = null;
        this.p = null;
        this.n = null;
        this.m = null;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.k != null && this.k.get() != null) {
            if (z) {
                j();
            } else {
                i();
            }
        }
    }

    public void setListener(a.C0008a aVar) {
        this.o = aVar;
    }
}
