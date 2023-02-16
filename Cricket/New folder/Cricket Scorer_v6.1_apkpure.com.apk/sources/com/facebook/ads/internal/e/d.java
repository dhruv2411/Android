package com.facebook.ads.internal.e;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.e.f;
import com.facebook.ads.internal.q.d.b;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class d {
    private static final String a = ("SELECT tokens." + h.a.b + ", " + "tokens" + "." + h.b.b + ", " + "events" + "." + c.a.b + ", " + "events" + "." + c.c.b + ", " + "events" + "." + c.d.b + ", " + "events" + "." + c.e.b + ", " + "events" + "." + c.f.b + ", " + "events" + "." + c.g.b + ", " + "events" + "." + c.h.b + ", " + "events" + "." + c.i.b + " FROM " + "events" + " JOIN " + "tokens" + " ON " + "events" + "." + c.b.b + " = " + "tokens" + "." + h.a.b + " ORDER BY " + "events" + "." + c.e.b + " ASC");
    private static final int b = Runtime.getRuntime().availableProcessors();
    private static final int c = Math.max(2, Math.min(b - 1, 4));
    private static final int d = ((b * 2) + 1);
    private static final ThreadFactory e = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "DatabaseTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> f = new LinkedBlockingQueue(128);
    private static final Executor g;
    private static final ReentrantReadWriteLock h = new ReentrantReadWriteLock();
    private static final Lock i = h.readLock();
    /* access modifiers changed from: private */
    public static final Lock j = h.writeLock();
    /* access modifiers changed from: private */
    public final Context k;
    /* access modifiers changed from: private */
    public final h l = new h(this);
    /* access modifiers changed from: private */
    public final c m = new c(this);
    private SQLiteOpenHelper n;

    private static class a<T> extends AsyncTask<Void, Void, T> {
        private final f<T> a;
        private final a<T> b;
        private final Context c;
        private f.a d;

        a(Context context, f<T> fVar, a<T> aVar) {
            this.a = fVar;
            this.b = aVar;
            this.c = context;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public T doInBackground(Void... voidArr) {
            Exception e;
            T t;
            try {
                t = this.a.b();
                try {
                    this.d = this.a.c();
                    return t;
                } catch (Exception e2) {
                    e = e2;
                    com.facebook.ads.internal.q.d.a.a(this.c, "database", b.l, e);
                    this.d = f.a.UNKNOWN;
                    return t;
                }
            } catch (Exception e3) {
                Exception exc = e3;
                t = null;
                e = exc;
                com.facebook.ads.internal.q.d.a.a(this.c, "database", b.l, e);
                this.d = f.a.UNKNOWN;
                return t;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(T t) {
            if (this.d == null) {
                this.b.a(t);
            } else {
                this.b.a(this.d.a(), this.d.b());
            }
            this.b.a();
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(c, d, 30, TimeUnit.SECONDS, f, e);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        g = threadPoolExecutor;
    }

    public d(Context context) {
        this.k = context;
    }

    private synchronized SQLiteDatabase i() {
        if (this.n == null) {
            this.n = new e(this.k, this);
        }
        return this.n.getWritableDatabase();
    }

    @WorkerThread
    public Cursor a(int i2) {
        i.lock();
        try {
            SQLiteDatabase a2 = a();
            return a2.rawQuery(a + " LIMIT " + String.valueOf(i2), (String[]) null);
        } finally {
            i.unlock();
        }
    }

    public SQLiteDatabase a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return i();
        }
        throw new IllegalStateException("Cannot call getDatabase from the UI thread!");
    }

    public <T> AsyncTask a(f<T> fVar, a<T> aVar) {
        return com.facebook.ads.internal.q.a.d.a(g, new a(this.k.getApplicationContext(), fVar, aVar), new Void[0]);
    }

    public AsyncTask a(String str, int i2, String str2, double d2, double d3, String str3, Map<String, String> map, a<String> aVar) {
        final String str4 = str;
        final int i3 = i2;
        final String str5 = str2;
        final double d4 = d2;
        final double d5 = d3;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        return a(new i<String>() {
            /* JADX WARNING: Removed duplicated region for block: B:32:0x0090 A[Catch:{ Exception -> 0x0094 }] */
            /* JADX WARNING: Removed duplicated region for block: B:44:0x00b9 A[Catch:{ Exception -> 0x00bd }] */
            @android.support.annotation.Nullable
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.String b() {
                /*
                    r14 = this;
                    java.lang.String r0 = r2
                    boolean r0 = android.text.TextUtils.isEmpty(r0)
                    r1 = 0
                    if (r0 == 0) goto L_0x000a
                    return r1
                L_0x000a:
                    java.util.concurrent.locks.Lock r0 = com.facebook.ads.internal.e.d.j
                    r0.lock()
                    com.facebook.ads.internal.e.d r0 = com.facebook.ads.internal.e.d.this     // Catch:{ Exception -> 0x006e, all -> 0x0069 }
                    android.database.sqlite.SQLiteDatabase r0 = r0.a()     // Catch:{ Exception -> 0x006e, all -> 0x0069 }
                    r0.beginTransaction()     // Catch:{ Exception -> 0x0067 }
                    com.facebook.ads.internal.e.d r2 = com.facebook.ads.internal.e.d.this     // Catch:{ Exception -> 0x0067 }
                    com.facebook.ads.internal.e.c r3 = r2.m     // Catch:{ Exception -> 0x0067 }
                    com.facebook.ads.internal.e.d r2 = com.facebook.ads.internal.e.d.this     // Catch:{ Exception -> 0x0067 }
                    com.facebook.ads.internal.e.h r2 = r2.l     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r4 = r2     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r4 = r2.a(r4)     // Catch:{ Exception -> 0x0067 }
                    int r5 = r3     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r6 = r4     // Catch:{ Exception -> 0x0067 }
                    double r7 = r5     // Catch:{ Exception -> 0x0067 }
                    double r9 = r7     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r11 = r9     // Catch:{ Exception -> 0x0067 }
                    java.util.Map r12 = r10     // Catch:{ Exception -> 0x0067 }
                    java.lang.String r2 = r3.a(r4, r5, r6, r7, r9, r11, r12)     // Catch:{ Exception -> 0x0067 }
                    r0.setTransactionSuccessful()     // Catch:{ Exception -> 0x0067 }
                    if (r0 == 0) goto L_0x005f
                    boolean r1 = r0.isOpen()
                    if (r1 == 0) goto L_0x005f
                    boolean r1 = r0.inTransaction()     // Catch:{ Exception -> 0x0051 }
                    if (r1 == 0) goto L_0x005f
                    r0.endTransaction()     // Catch:{ Exception -> 0x0051 }
                    goto L_0x005f
                L_0x0051:
                    r0 = move-exception
                    com.facebook.ads.internal.e.d r1 = com.facebook.ads.internal.e.d.this
                    android.content.Context r1 = r1.k
                    java.lang.String r3 = "database"
                    int r4 = com.facebook.ads.internal.q.d.b.k
                    com.facebook.ads.internal.q.d.a.a((android.content.Context) r1, (java.lang.String) r3, (int) r4, (java.lang.Exception) r0)
                L_0x005f:
                    java.util.concurrent.locks.Lock r0 = com.facebook.ads.internal.e.d.j
                    r0.unlock()
                    return r2
                L_0x0067:
                    r2 = move-exception
                    goto L_0x0070
                L_0x0069:
                    r0 = move-exception
                    r13 = r1
                    r1 = r0
                    r0 = r13
                    goto L_0x00ab
                L_0x006e:
                    r2 = move-exception
                    r0 = r1
                L_0x0070:
                    com.facebook.ads.internal.e.f$a r3 = com.facebook.ads.internal.e.f.a.DATABASE_INSERT     // Catch:{ all -> 0x00aa }
                    r14.a(r3)     // Catch:{ all -> 0x00aa }
                    com.facebook.ads.internal.e.d r3 = com.facebook.ads.internal.e.d.this     // Catch:{ all -> 0x00aa }
                    android.content.Context r3 = r3.k     // Catch:{ all -> 0x00aa }
                    java.lang.String r4 = "database"
                    int r5 = com.facebook.ads.internal.q.d.b.i     // Catch:{ all -> 0x00aa }
                    com.facebook.ads.internal.q.d.a.a((android.content.Context) r3, (java.lang.String) r4, (int) r5, (java.lang.Exception) r2)     // Catch:{ all -> 0x00aa }
                    if (r0 == 0) goto L_0x00a2
                    boolean r2 = r0.isOpen()
                    if (r2 == 0) goto L_0x00a2
                    boolean r2 = r0.inTransaction()     // Catch:{ Exception -> 0x0094 }
                    if (r2 == 0) goto L_0x00a2
                    r0.endTransaction()     // Catch:{ Exception -> 0x0094 }
                    goto L_0x00a2
                L_0x0094:
                    r0 = move-exception
                    com.facebook.ads.internal.e.d r2 = com.facebook.ads.internal.e.d.this
                    android.content.Context r2 = r2.k
                    java.lang.String r3 = "database"
                    int r4 = com.facebook.ads.internal.q.d.b.k
                    com.facebook.ads.internal.q.d.a.a((android.content.Context) r2, (java.lang.String) r3, (int) r4, (java.lang.Exception) r0)
                L_0x00a2:
                    java.util.concurrent.locks.Lock r0 = com.facebook.ads.internal.e.d.j
                    r0.unlock()
                    return r1
                L_0x00aa:
                    r1 = move-exception
                L_0x00ab:
                    if (r0 == 0) goto L_0x00cb
                    boolean r2 = r0.isOpen()
                    if (r2 == 0) goto L_0x00cb
                    boolean r2 = r0.inTransaction()     // Catch:{ Exception -> 0x00bd }
                    if (r2 == 0) goto L_0x00cb
                    r0.endTransaction()     // Catch:{ Exception -> 0x00bd }
                    goto L_0x00cb
                L_0x00bd:
                    r0 = move-exception
                    com.facebook.ads.internal.e.d r2 = com.facebook.ads.internal.e.d.this
                    android.content.Context r2 = r2.k
                    java.lang.String r3 = "database"
                    int r4 = com.facebook.ads.internal.q.d.b.k
                    com.facebook.ads.internal.q.d.a.a((android.content.Context) r2, (java.lang.String) r3, (int) r4, (java.lang.Exception) r0)
                L_0x00cb:
                    java.util.concurrent.locks.Lock r0 = com.facebook.ads.internal.e.d.j
                    r0.unlock()
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.e.d.AnonymousClass2.b():java.lang.String");
            }
        }, aVar);
    }

    @WorkerThread
    public boolean a(String str) {
        j.lock();
        boolean z = false;
        try {
            a().execSQL("UPDATE " + "events" + " SET " + c.i.b + "=" + c.i.b + "+1" + " WHERE " + c.a.b + "=?", new String[]{str});
            z = true;
        } catch (SQLiteException unused) {
        }
        j.unlock();
        return z;
    }

    public synchronized void b() {
        for (g e2 : c()) {
            e2.e();
        }
        if (this.n != null) {
            this.n.close();
            this.n = null;
        }
    }

    @WorkerThread
    public boolean b(String str) {
        j.lock();
        try {
            return this.m.a(str);
        } finally {
            j.unlock();
        }
    }

    public g[] c() {
        return new g[]{this.l, this.m};
    }

    public Cursor d() {
        i.lock();
        try {
            return this.m.c();
        } finally {
            i.unlock();
        }
    }

    @WorkerThread
    public Cursor e() {
        i.lock();
        try {
            return this.m.d();
        } finally {
            i.unlock();
        }
    }

    @WorkerThread
    public Cursor f() {
        i.lock();
        try {
            return this.l.c();
        } finally {
            i.unlock();
        }
    }

    @WorkerThread
    public void g() {
        j.lock();
        try {
            this.l.d();
        } finally {
            j.unlock();
        }
    }
}
