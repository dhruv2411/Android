package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.b.e;
import com.facebook.ads.internal.view.component.a.b;
import com.facebook.ads.internal.view.component.a.d;
import java.util.HashMap;
import java.util.Map;

public class g extends i {
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.adapters.a.g e;
    /* access modifiers changed from: private */
    public final a f;
    /* access modifiers changed from: private */
    public final u g = new u();
    private final a.C0006a h;
    private long i;

    public g(Context context, com.facebook.ads.internal.adapters.a.g gVar, c cVar) {
        super(context, cVar);
        this.e = gVar;
        this.h = new a.C0006a() {
            public void a() {
                if (!g.this.g.b()) {
                    g.this.g.a();
                    HashMap hashMap = new HashMap();
                    g.this.f.a((Map<String, String>) hashMap);
                    hashMap.put("touch", k.a(g.this.g.e()));
                    g.this.b.a(g.this.e.c(), hashMap);
                    if (g.this.getAudienceNetworkListener() != null) {
                        g.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                    }
                }
            }
        };
        this.f = new a(this, 100, this.h);
        this.f.a(gVar.f());
    }

    private void setUpContent(int i2) {
        h hVar = this.e.d().get(0);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        d a = new d(imageView).a(hVar.c().i(), hVar.c().h());
        a.a((e) new e() {
            public void a(boolean z) {
                if (z) {
                    g.this.f.a();
                }
            }
        });
        a.a(hVar.c().g());
        b a2 = com.facebook.ads.internal.view.component.a.c.a(new d.a(getContext(), this.b, getAudienceNetworkListener(), this.e, imageView, this.f, this.g).a(a).b(i2).a());
        a(a2, a2.a(), i2);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.e);
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.i = System.currentTimeMillis();
    }

    public void a(Bundle bundle) {
    }

    public void i() {
    }

    public void j() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        if (this.e != null) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.i, a.C0004a.XOUT, this.e.e()));
            if (!TextUtils.isEmpty(this.e.c())) {
                HashMap hashMap = new HashMap();
                this.f.a((Map<String, String>) hashMap);
                hashMap.put("touch", k.a(this.g.e()));
                this.b.i(this.e.c(), hashMap);
            }
        }
        this.f.c();
        super.onDestroy();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.g.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
