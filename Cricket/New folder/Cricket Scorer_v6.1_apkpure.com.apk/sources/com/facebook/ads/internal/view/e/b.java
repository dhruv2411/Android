package com.facebook.ads.internal.view.e;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.q.c.e;
import com.facebook.ads.internal.q.c.f;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.b.a;
import com.facebook.ads.internal.view.component.e;
import com.facebook.ads.internal.view.component.h;
import com.facebook.ads.internal.view.f.b.z;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class b {
    /* access modifiers changed from: private */
    public static final String a = "b";
    private static final int b = ((int) (4.0f * x.b));
    private static final int c = ((int) (72.0f * x.b));
    private static final int d = ((int) (8.0f * x.b));
    /* access modifiers changed from: private */
    public final Context e;
    /* access modifiers changed from: private */
    public final c f;
    /* access modifiers changed from: private */
    public final k g;
    private final String h;
    private final d i;
    private final com.facebook.ads.internal.r.a j;
    private final u k;
    private Executor l = AsyncTask.THREAD_POOL_EXECUTOR;
    /* access modifiers changed from: private */
    @Nullable
    public a.C0008a m;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.b.a n;
    @Nullable
    private a.b o;

    public enum a {
        SCREENSHOTS,
        MARKUP,
        INFO
    }

    public b(Context context, c cVar, k kVar, a.C0008a aVar, com.facebook.ads.internal.r.a aVar2, u uVar) {
        this.e = context;
        this.f = cVar;
        this.g = kVar;
        this.m = aVar;
        this.h = com.facebook.ads.internal.j.c.a(this.g.f().b());
        this.i = this.g.d().a();
        this.j = aVar2;
        this.k = uVar;
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.m != null) {
            this.m.a(z.REWARDED_VIDEO_END_ACTIVITY.a());
        }
    }

    private View h() {
        h hVar = new h(this.e, this.i, true, false, false);
        hVar.a(this.g.b().a(), this.g.b().c(), false, true);
        hVar.setAlignment(17);
        com.facebook.ads.internal.view.component.a aVar = new com.facebook.ads.internal.view.component.a(this.e, true, false, z.REWARDED_VIDEO_AD_CLICK.a(), this.i, this.f, this.m, this.j, this.k);
        aVar.a(this.g.c(), this.g.g(), new HashMap());
        e eVar = new e(this.e);
        x.a((View) eVar, 0);
        eVar.setRadius(50);
        new com.facebook.ads.internal.view.b.d((ImageView) eVar).a().a(this.g.a().b());
        LinearLayout linearLayout = new LinearLayout(this.e);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.addView(eVar, new LinearLayout.LayoutParams(c, c));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, d, 0, d);
        linearLayout.addView(hVar, layoutParams);
        linearLayout.addView(aVar, layoutParams);
        return linearLayout;
    }

    private View i() {
        RecyclerView recyclerView = new RecyclerView(this.e);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.e, 0, false));
        recyclerView.setAdapter(new c(this.g.f().d(), b));
        return recyclerView;
    }

    private View j() {
        this.o = new a.c() {
            public void a() {
                if (b.this.n != null && !TextUtils.isEmpty(b.this.g.f().c())) {
                    b.this.n.post(new Runnable() {
                        public void run() {
                            if (b.this.n == null || b.this.n.c()) {
                                Log.w(b.a, "Webview already destroyed, cannot activate");
                                return;
                            }
                            com.facebook.ads.internal.view.b.a f = b.this.n;
                            f.loadUrl("javascript:" + b.this.g.f().c());
                        }
                    });
                }
            }

            public void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if (!"fbad".equals(parse.getScheme()) || !parse.getAuthority().equals("close")) {
                    if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && b.this.m != null) {
                        b.this.m.a(z.REWARDED_VIDEO_AD_CLICK.a());
                    }
                    com.facebook.ads.internal.a.b a2 = com.facebook.ads.internal.a.c.a(b.this.e, b.this.f, b.this.g.g(), parse, map);
                    if (a2 != null) {
                        try {
                            a2.b();
                        } catch (Exception e) {
                            Log.e(b.a, "Error executing action", e);
                        }
                    }
                } else {
                    b.this.g();
                }
            }
        };
        this.n = new com.facebook.ads.internal.view.b.a(this.e, new WeakReference(this.o), 1);
        this.n.loadDataWithBaseURL(com.facebook.ads.internal.q.c.b.a(), this.h, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, (String) null);
        return this.n;
    }

    public boolean a() {
        return b() == a.MARKUP;
    }

    public a b() {
        return !this.g.f().d().isEmpty() ? a.SCREENSHOTS : !TextUtils.isEmpty(this.h) ? a.MARKUP : a.INFO;
    }

    public Pair<a, View> c() {
        a b2 = b();
        switch (b2) {
            case MARKUP:
                return new Pair<>(b2, j());
            case SCREENSHOTS:
                return new Pair<>(b2, i());
            default:
                return new Pair<>(b2, h());
        }
    }

    public void d() {
        String a2 = this.g.f().a();
        if (!TextUtils.isEmpty(a2)) {
            com.facebook.ads.internal.q.c.e eVar = new com.facebook.ads.internal.q.c.e(this.e, new HashMap());
            eVar.a((e.a) new e.a() {
                public void a() {
                    if (b.this.m != null) {
                        b.this.m.a(z.REWARD_SERVER_FAILED.a());
                    }
                }

                public void a(f fVar) {
                    a.C0008a aVar;
                    z zVar;
                    if (b.this.m != null) {
                        if (fVar == null || !fVar.a()) {
                            aVar = b.this.m;
                            zVar = z.REWARD_SERVER_FAILED;
                        } else {
                            aVar = b.this.m;
                            zVar = z.REWARD_SERVER_SUCCESS;
                        }
                        aVar.a(zVar.a());
                    }
                }
            });
            eVar.executeOnExecutor(this.l, new String[]{a2});
        }
    }

    public void e() {
        if (this.n != null) {
            this.n.destroy();
            this.n = null;
            this.o = null;
        }
    }
}
