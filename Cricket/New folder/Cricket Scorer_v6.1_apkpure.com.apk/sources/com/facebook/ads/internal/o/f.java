package com.facebook.ads.internal.o;

import com.facebook.ads.internal.h.c;

class f {
    private final a a;
    private final c b;
    private final String c;
    private final String d;
    private final String e;

    enum a {
        UNKNOWN,
        ERROR,
        ADS
    }

    f(a aVar) {
        this(aVar, (c) null, (String) null, (String) null, (String) null);
    }

    f(a aVar, c cVar, String str, String str2, String str3) {
        this.a = aVar;
        this.b = cVar;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public c a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public a b() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public String d() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public String e() {
        return this.e;
    }
}
