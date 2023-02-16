package com.facebook.ads.internal.view.c.a;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.view.c.a.d;
import com.facebook.ads.internal.view.component.d;
import com.facebook.ads.internal.view.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class f extends i {
    private static final int e = ((int) (48.0f * x.b));
    private static final int f = ((int) (x.b * 8.0f));
    private static final int g = ((int) (8.0f * x.b));
    private static final int h = ((int) (56.0f * x.b));
    private static final int i = ((int) (12.0f * x.b));
    /* access modifiers changed from: private */
    public final u j = new u();
    @Nullable
    private b k;
    @Nullable
    private LinearLayout l;
    /* access modifiers changed from: private */
    public String m;
    private long n;
    private String o;
    private List<b> p;
    /* access modifiers changed from: private */
    @Nullable
    public d q;
    @Nullable
    private c r;
    /* access modifiers changed from: private */
    public a s;
    private a.C0006a t;
    private int u;
    private int v;

    public f(Context context, c cVar, @Nullable b bVar) {
        super(context, cVar);
        this.k = bVar;
    }

    private void a(g gVar) {
        this.m = gVar.c();
        this.o = gVar.e();
        this.u = gVar.f();
        this.v = gVar.g();
        List<h> d = gVar.d();
        this.p = new ArrayList(d.size());
        for (int i2 = 0; i2 < d.size(); i2++) {
            this.p.add(new b(i2, d.size(), d.get(i2)));
        }
    }

    private void a(a aVar) {
        new PagerSnapHelper().attachToRecyclerView(this.r);
        aVar.a((d.a) new d.a() {
            public void a(int i) {
                if (f.this.q != null) {
                    f.this.q.a(i);
                }
            }
        });
        this.q = new com.facebook.ads.internal.view.component.d(getContext(), this.d.a(), this.p.size());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, g);
        layoutParams.setMargins(0, i, 0, 0);
        this.q.setLayoutParams(layoutParams);
    }

    public void a() {
        if (this.l != null) {
            this.l.removeAllViews();
            this.l = null;
        }
        if (this.r != null) {
            this.r.removeAllViews();
            this.r = null;
        }
        if (this.q != null) {
            this.q.removeAllViews();
            this.q = null;
        }
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        g gVar = (g) intent.getSerializableExtra("ad_data_bundle");
        super.a(audienceNetworkActivity, gVar);
        a(gVar);
        setUpLayout(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.n = System.currentTimeMillis();
    }

    public void a(Bundle bundle) {
    }

    public void i() {
    }

    public void j() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        a();
        setUpLayout(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        super.onDestroy();
        com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.n, a.C0004a.XOUT, this.o));
        if (!TextUtils.isEmpty(this.m)) {
            HashMap hashMap = new HashMap();
            this.s.a((Map<String, String>) hashMap);
            hashMap.put("touch", k.a(this.j.e()));
            this.b.i(this.m, hashMap);
        }
        a();
        this.s.c();
        this.s = null;
        this.t = null;
        this.p = null;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.j.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setUpLayout(int i2) {
        LinearLayout linearLayout;
        int i3;
        int i4;
        int i5;
        int i6;
        f fVar;
        int i7 = i2;
        this.l = new LinearLayout(getContext());
        if (i7 == 1) {
            linearLayout = this.l;
            i3 = 17;
        } else {
            linearLayout = this.l;
            i3 = 48;
        }
        linearLayout.setGravity(i3);
        this.l.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.l.setOrientation(1);
        int i8 = x.a.widthPixels;
        int i9 = x.a.heightPixels;
        if (i7 == 1) {
            int min = Math.min(i8 - (f * 4), i9 / 2);
            int i10 = (i8 - min) / 8;
            i5 = i10;
            i6 = min;
            i4 = 4 * i10;
        } else {
            int i11 = i9 - ((h + e) + (f * 2));
            int i12 = f;
            i5 = i12;
            i6 = i11;
            i4 = 2 * i12;
        }
        this.t = new a.C0006a() {
            public void a() {
                HashMap hashMap = new HashMap();
                if (!f.this.j.b()) {
                    f.this.j.a();
                    if (f.this.getAudienceNetworkListener() != null) {
                        f.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                    }
                    if (!TextUtils.isEmpty(f.this.m)) {
                        f.this.s.a((Map<String, String>) hashMap);
                        hashMap.put("touch", k.a(f.this.j.e()));
                        f.this.b.a(f.this.m, hashMap);
                    }
                }
            }
        };
        this.s = new com.facebook.ads.internal.r.a(this, 1, this.t);
        this.s.a(this.u);
        this.s.b(this.v);
        this.r = new c(getContext());
        this.r.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        a aVar = new a(this.r, i7, this.p, this.s);
        c cVar = this.r;
        d dVar = r1;
        a aVar2 = aVar;
        d dVar2 = new d(this.p, this.b, this.k, this.s, this.j, getAudienceNetworkListener(), i7 == 1 ? this.d.a() : this.d.b(), this.m, i6, i5, i4, i7, aVar2);
        cVar.setAdapter(dVar);
        int i13 = i2;
        if (i13 == 1) {
            fVar = this;
            fVar.a(aVar2);
        } else {
            fVar = this;
        }
        fVar.l.addView(fVar.r);
        if (fVar.q != null) {
            fVar.l.addView(fVar.q);
        }
        fVar.a(fVar.l, false, i13);
    }
}
