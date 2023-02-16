package com.facebook.ads.internal.adapters.a;

import java.io.Serializable;

public class i implements Serializable {
    private static final long serialVersionUID = 351643298236575728L;
    private final String a;
    private final String b;
    private final String c;
    private final String d;

    public static class a {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;

        /* access modifiers changed from: package-private */
        public a a(String str) {
            this.a = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public i a() {
            return new i(this);
        }

        /* access modifiers changed from: package-private */
        public a b(String str) {
            this.b = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a c(String str) {
            this.c = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a d(String str) {
            this.d = str;
            return this;
        }
    }

    private i(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }
}
