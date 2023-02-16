package com.facebook.ads.internal.m;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.p.a.n;
import com.facebook.ads.internal.p.a.p;
import com.facebook.ads.internal.q.c.d;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class b {
    private static final String a = "b";
    private final a b;
    private final Context c;
    /* access modifiers changed from: private */
    public final ThreadPoolExecutor d;
    private final ConnectivityManager e;
    private final com.facebook.ads.internal.p.a.a f;
    private final Handler g;
    private final long h;
    private final long i;
    /* access modifiers changed from: private */
    public final Runnable j = new Runnable() {
        public void run() {
            b.a(b.this);
            if (b.this.n > 0) {
                try {
                    Thread.sleep(b.this.n);
                } catch (InterruptedException unused) {
                }
            }
            b.this.d();
        }
    };
    private final Runnable k = new Runnable() {
        public void run() {
            boolean unused = b.this.l = false;
            if (b.this.d.getQueue().isEmpty()) {
                b.this.d.execute(b.this.j);
            }
        }
    };
    /* access modifiers changed from: private */
    public volatile boolean l;
    private int m;
    /* access modifiers changed from: private */
    public long n;

    interface a {
        JSONObject a();

        boolean a(JSONArray jSONArray);

        void b();

        void b(JSONArray jSONArray);

        boolean c();
    }

    b(Context context, a aVar) {
        this.b = aVar;
        this.c = context;
        this.d = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.e = (ConnectivityManager) context.getSystemService("connectivity");
        this.f = d.b(context);
        this.g = new Handler(Looper.getMainLooper());
        this.h = com.facebook.ads.internal.l.a.l(context);
        this.i = com.facebook.ads.internal.l.a.m(context);
    }

    static /* synthetic */ int a(b bVar) {
        int i2 = bVar.m + 1;
        bVar.m = i2;
        return i2;
    }

    private void a(long j2) {
        this.g.postDelayed(this.k, j2);
    }

    private void c() {
        if (this.m >= 5) {
            e();
            b();
            return;
        }
        this.n = this.m == 1 ? 2000 : this.n * 2;
        a();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void d() {
        a aVar;
        JSONArray jSONArray;
        try {
            NetworkInfo activeNetworkInfo = this.e.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnectedOrConnecting()) {
                    JSONObject a2 = this.b.a();
                    if (a2 == null) {
                        e();
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("attempt", String.valueOf(this.m));
                    a2.put("data", jSONObject);
                    p pVar = new p();
                    pVar.put("payload", a2.toString());
                    n b2 = this.f.b(com.facebook.ads.internal.o.d.a(this.c), pVar);
                    String e2 = b2 != null ? b2.e() : null;
                    if (TextUtils.isEmpty(e2)) {
                        if (a2.has("events")) {
                            aVar = this.b;
                            jSONArray = a2.getJSONArray("events");
                        }
                        c();
                        return;
                    }
                    if (b2.a() != 200) {
                        if (a2.has("events")) {
                            aVar = this.b;
                            jSONArray = a2.getJSONArray("events");
                        }
                    } else if (this.b.a(new JSONArray(e2))) {
                        if (!this.b.c()) {
                            e();
                            return;
                        }
                    }
                    c();
                    return;
                    aVar.b(jSONArray);
                    c();
                    return;
                }
            }
            a(this.i);
        } catch (Exception unused) {
            c();
        }
    }

    private void e() {
        this.m = 0;
        this.n = 0;
        if (this.d.getQueue().size() == 0) {
            this.b.b();
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.l = true;
        this.g.removeCallbacks(this.k);
        a(this.h);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (!this.l) {
            this.l = true;
            this.g.removeCallbacks(this.k);
            a(this.i);
        }
    }
}
