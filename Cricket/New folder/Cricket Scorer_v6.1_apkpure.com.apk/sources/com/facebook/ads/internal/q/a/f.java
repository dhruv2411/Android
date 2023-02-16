package com.facebook.ads.internal.q.a;

import android.os.Handler;
import android.support.annotation.VisibleForTesting;

public class f {
    /* access modifiers changed from: private */
    public final Handler a;
    private final a b;
    private int c;
    private boolean d;

    public interface a {
        void a();

        void a(int i);
    }

    public f(int i, a aVar) {
        this(i, aVar, new Handler());
    }

    @VisibleForTesting
    f(int i, a aVar, Handler handler) {
        this.d = false;
        this.c = i;
        this.b = aVar;
        this.a = handler;
    }

    /* access modifiers changed from: private */
    public void e() {
        this.c--;
        this.b.a(this.c);
        if (this.c == 0) {
            this.b.a();
            this.d = false;
        }
    }

    public boolean a() {
        if (this.c <= 0 || c()) {
            return false;
        }
        this.d = true;
        this.b.a(this.c);
        this.a.postDelayed(new Runnable() {
            public void run() {
                if (f.this.c()) {
                    f.this.e();
                    f.this.a.postDelayed(this, 1000);
                }
            }
        }, 1000);
        return true;
    }

    public boolean b() {
        if (!c()) {
            return false;
        }
        this.d = false;
        return true;
    }

    public boolean c() {
        return this.d;
    }

    public boolean d() {
        return this.c <= 0;
    }
}
