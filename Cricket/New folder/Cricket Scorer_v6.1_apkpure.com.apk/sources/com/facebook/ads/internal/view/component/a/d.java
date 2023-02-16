package com.facebook.ads.internal.view.component.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.f.c.o;

public class d {
    private final Context a;
    private final c b;
    private final a.C0008a c;
    private final g d;
    private final View e;
    private final com.facebook.ads.internal.r.a f;
    private final u g;
    private final int h;
    private final int i;
    @Nullable
    private final o j;
    @Nullable
    private final View k;

    public static class a {
        /* access modifiers changed from: private */
        public final Context a;
        /* access modifiers changed from: private */
        public final c b;
        /* access modifiers changed from: private */
        public final a.C0008a c;
        /* access modifiers changed from: private */
        public final g d;
        /* access modifiers changed from: private */
        public final View e;
        /* access modifiers changed from: private */
        public final com.facebook.ads.internal.r.a f;
        /* access modifiers changed from: private */
        public final u g;
        /* access modifiers changed from: private */
        public int h = 0;
        /* access modifiers changed from: private */
        public int i = 1;
        /* access modifiers changed from: private */
        @Nullable
        public o j;
        /* access modifiers changed from: private */
        @Nullable
        public View k;

        public a(Context context, c cVar, a.C0008a aVar, g gVar, View view, com.facebook.ads.internal.r.a aVar2, u uVar) {
            this.a = context;
            this.b = cVar;
            this.c = aVar;
            this.d = gVar;
            this.e = view;
            this.f = aVar2;
            this.g = uVar;
        }

        public a a(int i2) {
            this.h = i2;
            return this;
        }

        public a a(View view) {
            this.k = view;
            return this;
        }

        public a a(o oVar) {
            this.j = oVar;
            return this;
        }

        public d a() {
            return new d(this);
        }

        public a b(int i2) {
            this.i = i2;
            return this;
        }
    }

    private d(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
    }

    /* access modifiers changed from: package-private */
    public Context a() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public c b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public a.C0008a c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public View d() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public com.facebook.ads.internal.r.a e() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public u f() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public g g() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public o h() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public View i() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public int j() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public int k() {
        return this.i;
    }
}
