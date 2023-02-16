package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.n.c;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.h;
import com.facebook.ads.internal.n.j;
import com.facebook.ads.internal.n.k;
import com.facebook.ads.internal.n.m;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.List;
import java.util.Map;

public abstract class y implements AdAdapter {
    private AdPlacementType a = AdPlacementType.NATIVE;

    public abstract String A();

    public abstract String B();

    public abstract m C();

    public abstract int D();

    public abstract String E();

    public abstract List<f> F();

    public abstract int G();

    public abstract int H();

    public abstract c I();

    public String K() {
        return p();
    }

    public abstract void a(int i);

    public abstract void a(Context context, z zVar, com.facebook.ads.internal.m.c cVar, Map<String, Object> map, f.c cVar2);

    public abstract void a(View view, List<View> list);

    public abstract void a(z zVar);

    /* access modifiers changed from: protected */
    public void a(AdPlacementType adPlacementType) {
        this.a = adPlacementType;
    }

    public abstract void a(Map<String, String> map);

    public boolean a_() {
        return true;
    }

    public abstract void b(Map<String, String> map);

    public abstract void b_();

    public abstract String c();

    public abstract boolean c_();

    public abstract boolean d();

    public abstract boolean e();

    public abstract boolean f();

    public abstract boolean g();

    public final AdPlacementType getPlacementType() {
        return this.a;
    }

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract h k();

    public abstract h l();

    public abstract k m();

    public abstract String n();

    public abstract String o();

    public abstract String p();

    public abstract String q();

    public abstract String r();

    public abstract String s();

    public abstract String t();

    public abstract String u();

    public abstract String v();

    public abstract j w();

    public abstract h x();

    public abstract String y();

    public abstract String z();
}
