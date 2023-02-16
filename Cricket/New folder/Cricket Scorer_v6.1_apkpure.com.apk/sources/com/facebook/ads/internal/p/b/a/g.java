package com.facebook.ads.internal.p.b.a;

import java.io.File;

public class g extends e {
    private final long a;

    public g(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.a = j;
    }

    public /* bridge */ /* synthetic */ void a(File file) {
        super.a(file);
    }

    /* access modifiers changed from: protected */
    public boolean a(File file, long j, int i) {
        return j <= this.a;
    }
}
