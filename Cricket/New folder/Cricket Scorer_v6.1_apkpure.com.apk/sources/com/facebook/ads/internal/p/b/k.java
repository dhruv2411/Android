package com.facebook.ads.internal.p.b;

import android.util.Log;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;

class k {
    private final n a;
    private final a b;
    private final Object c = new Object();
    private final Object d = new Object();
    private final AtomicInteger e;
    private volatile Thread f;
    private volatile boolean g;
    private volatile int h = -1;

    private class a implements Runnable {
        private a() {
        }

        public void run() {
            k.this.e();
        }
    }

    public k(n nVar, a aVar) {
        this.a = (n) j.a(nVar);
        this.b = (a) j.a(aVar);
        this.e = new AtomicInteger();
    }

    private void b() {
        int i = this.e.get();
        if (i >= 1) {
            this.e.set(0);
            throw new l("Error reading source " + i + " times");
        }
    }

    private void b(long j, long j2) {
        a(j, j2);
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    private synchronized void c() {
        boolean z = (this.f == null || this.f.getState() == Thread.State.TERMINATED) ? false : true;
        if (!this.g && !this.b.d() && !z) {
            a aVar = new a();
            this.f = new Thread(aVar, "Source reader for " + this.a);
            this.f.start();
        }
    }

    private void d() {
        synchronized (this.c) {
            try {
                this.c.wait(1000);
            } catch (InterruptedException e2) {
                throw new l("Waiting source data is interrupted!", e2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        r2 = r2 + r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
            r9 = this;
            r0 = -1
            r1 = 0
            com.facebook.ads.internal.p.b.a r2 = r9.b     // Catch:{ Throwable -> 0x0065, all -> 0x005f }
            int r2 = r2.a()     // Catch:{ Throwable -> 0x0065, all -> 0x005f }
            com.facebook.ads.internal.p.b.n r1 = r9.a     // Catch:{ Throwable -> 0x0059, all -> 0x0054 }
            r1.a((int) r2)     // Catch:{ Throwable -> 0x0059, all -> 0x0054 }
            com.facebook.ads.internal.p.b.n r1 = r9.a     // Catch:{ Throwable -> 0x0059, all -> 0x0054 }
            int r1 = r1.a()     // Catch:{ Throwable -> 0x0059, all -> 0x0054 }
            r3 = 8192(0x2000, float:1.14794E-41)
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
        L_0x0017:
            com.facebook.ads.internal.p.b.n r4 = r9.a     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            int r4 = r4.a((byte[]) r3)     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            if (r4 == r0) goto L_0x0042
            java.lang.Object r5 = r9.d     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            monitor-enter(r5)     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            boolean r6 = r9.g()     // Catch:{ all -> 0x003f }
            if (r6 == 0) goto L_0x0032
            monitor-exit(r5)     // Catch:{ all -> 0x003f }
            r9.h()
            long r2 = (long) r2
            long r0 = (long) r1
            r9.b(r2, r0)
            return
        L_0x0032:
            com.facebook.ads.internal.p.b.a r6 = r9.b     // Catch:{ all -> 0x003f }
            r6.a(r3, r4)     // Catch:{ all -> 0x003f }
            monitor-exit(r5)     // Catch:{ all -> 0x003f }
            int r2 = r2 + r4
            long r4 = (long) r2
            long r6 = (long) r1
            r9.b(r4, r6)     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            goto L_0x0017
        L_0x003f:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x003f }
            throw r0     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
        L_0x0042:
            r9.f()     // Catch:{ Throwable -> 0x0050, all -> 0x004e }
            r9.h()
            long r2 = (long) r2
            long r0 = (long) r1
            r9.b(r2, r0)
            return
        L_0x004e:
            r0 = move-exception
            goto L_0x007e
        L_0x0050:
            r0 = move-exception
            r8 = r2
            r2 = r1
            goto L_0x005d
        L_0x0054:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x007e
        L_0x0059:
            r1 = move-exception
            r8 = r2
            r2 = r0
            r0 = r1
        L_0x005d:
            r1 = r8
            goto L_0x0069
        L_0x005f:
            r2 = move-exception
            r8 = r1
            r1 = r0
            r0 = r2
            r2 = r8
            goto L_0x007e
        L_0x0065:
            r2 = move-exception
            r8 = r2
            r2 = r0
            r0 = r8
        L_0x0069:
            java.util.concurrent.atomic.AtomicInteger r3 = r9.e     // Catch:{ all -> 0x007a }
            r3.incrementAndGet()     // Catch:{ all -> 0x007a }
            r9.a((java.lang.Throwable) r0)     // Catch:{ all -> 0x007a }
            r9.h()
            long r0 = (long) r1
            long r2 = (long) r2
            r9.b(r0, r2)
            return
        L_0x007a:
            r0 = move-exception
            r8 = r2
            r2 = r1
            r1 = r8
        L_0x007e:
            r9.h()
            long r2 = (long) r2
            long r4 = (long) r1
            r9.b(r2, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.b.k.e():void");
    }

    private void f() {
        synchronized (this.d) {
            if (!g() && this.b.a() == this.a.a()) {
                this.b.c();
            }
        }
    }

    private boolean g() {
        return Thread.currentThread().isInterrupted() || this.g;
    }

    private void h() {
        try {
            this.a.b();
        } catch (l e2) {
            a((Throwable) new l("Error closing source " + this.a, e2));
        }
    }

    public int a(byte[] bArr, long j, int i) {
        m.a(bArr, j, i);
        while (!this.b.d() && ((long) this.b.a()) < j + ((long) i) && !this.g) {
            c();
            d();
            b();
        }
        int a2 = this.b.a(bArr, j, i);
        if (this.b.d() && this.h != 100) {
            this.h = 100;
            a(100);
        }
        return a2;
    }

    public void a() {
        synchronized (this.d) {
            Log.d("ProxyCache", "Shutdown proxy for " + this.a);
            try {
                this.g = true;
                if (this.f != null) {
                    this.f.interrupt();
                }
                this.b.b();
            } catch (l e2) {
                a((Throwable) e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
    }

    /* access modifiers changed from: protected */
    public void a(long j, long j2) {
        boolean z = false;
        int i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1)) == 0 ? 100 : (int) ((j * 100) / j2);
        boolean z2 = i != this.h;
        if (j2 >= 0) {
            z = true;
        }
        if (z && z2) {
            a(i);
        }
        this.h = i;
    }

    /* access modifiers changed from: protected */
    public final void a(Throwable th) {
        if (th instanceof i) {
            Log.d("ProxyCache", "ProxyCache is interrupted");
        } else {
            Log.e("ProxyCache", "ProxyCache error", th);
        }
    }
}
