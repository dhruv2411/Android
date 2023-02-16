package com.facebook.ads.internal.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.adapters.p;
import com.facebook.ads.internal.adapters.q;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.b.a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class e implements a {
    /* access modifiers changed from: private */
    public static final String a = "e";
    /* access modifiers changed from: private */
    public final a.C0008a b;
    private final com.facebook.ads.internal.view.b.a c;
    private final a.b d;
    /* access modifiers changed from: private */
    public final q e;
    private final c f;
    /* access modifiers changed from: private */
    public p g;
    private long h = System.currentTimeMillis();
    /* access modifiers changed from: private */
    public long i;
    /* access modifiers changed from: private */
    public a.C0004a j;

    public e(final AudienceNetworkActivity audienceNetworkActivity, final c cVar, a.C0008a aVar) {
        this.b = aVar;
        this.f = cVar;
        this.d = new a.c() {
            private long d = 0;

            public void a() {
                e.this.e.b();
            }

            public void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if (!"fbad".equals(parse.getScheme()) || !"close".equals(parse.getAuthority())) {
                    long j = this.d;
                    this.d = System.currentTimeMillis();
                    if (this.d - j >= 1000) {
                        if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority())) {
                            e.this.b.a("com.facebook.ads.interstitial.clicked");
                        }
                        b a2 = com.facebook.ads.internal.a.c.a(audienceNetworkActivity, cVar, e.this.g.c(), parse, map);
                        if (a2 != null) {
                            try {
                                a.C0004a unused = e.this.j = a2.a();
                                long unused2 = e.this.i = System.currentTimeMillis();
                                a2.b();
                            } catch (Exception e) {
                                Log.e(e.a, "Error executing action", e);
                            }
                        }
                    }
                } else {
                    audienceNetworkActivity.finish();
                }
            }

            public void b() {
                e.this.e.a();
            }
        };
        this.c = new com.facebook.ads.internal.view.b.a(audienceNetworkActivity, new WeakReference(this.d), 1);
        this.c.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        AudienceNetworkActivity audienceNetworkActivity2 = audienceNetworkActivity;
        c cVar2 = cVar;
        this.e = new q(audienceNetworkActivity2, cVar2, this.c, this.c.getViewabilityChecker(), new com.facebook.ads.internal.adapters.c() {
            public void a() {
                e.this.b.a("com.facebook.ads.interstitial.impression.logged");
            }
        });
        aVar.a((View) this.c);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (bundle == null || !bundle.containsKey("dataModel")) {
            this.g = p.b(intent);
            if (this.g != null) {
                this.e.a(this.g);
                this.c.loadDataWithBaseURL(com.facebook.ads.internal.q.c.b.a(), this.g.d(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, (String) null);
                this.c.a(this.g.h(), this.g.i());
                return;
            }
            return;
        }
        this.g = p.a(bundle.getBundle("dataModel"));
        if (this.g != null) {
            this.c.loadDataWithBaseURL(com.facebook.ads.internal.q.c.b.a(), this.g.d(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, (String) null);
            this.c.a(this.g.h(), this.g.i());
        }
    }

    public void a(Bundle bundle) {
        if (this.g != null) {
            bundle.putBundle("dataModel", this.g.j());
        }
    }

    public void i() {
        this.c.onPause();
    }

    public void j() {
        if (!(this.i <= 0 || this.j == null || this.g == null)) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.i, this.j, this.g.g()));
        }
        this.c.onResume();
    }

    public void onDestroy() {
        if (this.g != null) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.h, a.C0004a.XOUT, this.g.g()));
            if (!TextUtils.isEmpty(this.g.c())) {
                HashMap hashMap = new HashMap();
                this.c.getViewabilityChecker().a((Map<String, String>) hashMap);
                hashMap.put("touch", k.a(this.c.getTouchData()));
                this.f.i(this.g.c(), hashMap);
            }
        }
        com.facebook.ads.internal.q.c.b.a(this.c);
        this.c.destroy();
    }

    public void setListener(a.C0008a aVar) {
    }
}
