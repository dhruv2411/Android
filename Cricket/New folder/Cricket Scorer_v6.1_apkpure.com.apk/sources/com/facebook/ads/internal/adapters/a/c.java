package com.facebook.ads.internal.adapters.a;

import java.io.Serializable;

public class c implements Serializable {
    private static final long serialVersionUID = 5306126965868117466L;
    private final String a;
    private final String b;
    private final String c;

    public static class a {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;

        /* access modifiers changed from: package-private */
        public a a(String str) {
            this.a = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public c a() {
            return new c(this);
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
    }

    private c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
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
}
