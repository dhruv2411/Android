package com.facebook.ads.internal.adapters.a;

import android.support.annotation.Nullable;
import java.io.Serializable;

public class b implements Serializable {
    private static final long serialVersionUID = -268645651038092386L;
    private final String a;
    private final int b;
    private final int c;
    private final boolean d;
    private final boolean e;
    private final String f;
    private final int g;
    private final int h;
    @Nullable
    private final j i;
    private String j;

    static class a {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public boolean e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public int g;
        /* access modifiers changed from: private */
        public int h;
        /* access modifiers changed from: private */
        public j i;

        a() {
        }

        /* access modifiers changed from: package-private */
        public a a(int i2) {
            this.b = i2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(@Nullable j jVar) {
            this.i = jVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(String str) {
            this.a = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(boolean z) {
            this.d = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public b a() {
            return new b(this);
        }

        /* access modifiers changed from: package-private */
        public a b(int i2) {
            this.c = i2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a b(String str) {
            this.f = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a b(boolean z) {
            this.e = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a c(int i2) {
            this.g = i2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a d(int i2) {
            this.h = i2;
            return this;
        }
    }

    private b(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
    }

    public String a() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.j = str;
    }

    public String b() {
        return this.j;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public boolean f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public int h() {
        return this.g;
    }

    public int i() {
        return this.h;
    }

    @Nullable
    public j j() {
        return this.i;
    }
}
