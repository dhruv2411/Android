package com.facebook.ads.internal.q.b;

import android.graphics.Bitmap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class e {
    static final int a = Runtime.getRuntime().availableProcessors();
    static final ExecutorService b = Executors.newFixedThreadPool(a);
    private static volatile boolean c = true;
    private final Bitmap d;
    private Bitmap e;
    private final a f = new d();

    public e(Bitmap bitmap) {
        this.d = bitmap;
    }

    public Bitmap a() {
        return this.e;
    }

    public Bitmap a(int i) {
        this.e = this.f.a(this.d, (float) i);
        return this.e;
    }
}
