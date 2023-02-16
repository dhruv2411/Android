package com.facebook.ads.internal.view.component.a;

import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.component.a;
import com.facebook.ads.internal.view.component.h;
import java.util.HashMap;

public abstract class b extends RelativeLayout {
    static final int a = ((int) (16.0f * x.b));
    static final int b = ((int) (28.0f * x.b));
    private final h c;
    private final a d;
    private final c e;

    protected b(d dVar, d dVar2, boolean z) {
        super(dVar.a());
        this.e = dVar.b();
        this.d = new a(dVar.a(), d(), e(), "com.facebook.ads.interstitial.clicked", dVar2, dVar.b(), dVar.c(), dVar.e(), dVar.f());
        x.a((View) this.d);
        this.c = new h(getContext(), dVar2, z, b(), c());
        x.a((View) this.c);
    }

    public void a(com.facebook.ads.internal.adapters.a.h hVar, String str, double d2) {
        this.c.a(hVar.a().b(), hVar.a().c(), false, !a() && d2 > 0.0d && d2 < 1.0d);
        this.d.a(hVar.b(), str, new HashMap());
    }

    public abstract boolean a();

    /* access modifiers changed from: protected */
    public boolean b() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return true;
    }

    public c getAdEventManager() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public a getCtaButton() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public h getTitleDescContainer() {
        return this.c;
    }
}
