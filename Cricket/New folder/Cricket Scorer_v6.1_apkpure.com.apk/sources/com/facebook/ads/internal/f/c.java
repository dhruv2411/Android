package com.facebook.ads.internal.f;

import android.content.Context;
import android.os.Process;
import android.support.annotation.Nullable;
import com.facebook.ads.BuildConfig;
import com.facebook.ads.internal.q.a.n;
import com.facebook.ads.internal.q.a.q;
import java.lang.Thread;
import java.util.Map;

public class c implements Thread.UncaughtExceptionHandler {
    private final Thread.UncaughtExceptionHandler a;
    private final Context b;
    private final Map<String, String> c;

    public c(@Nullable Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Context context, Map<String, String> map) {
        this.a = uncaughtExceptionHandler;
        if (context == null) {
            throw new IllegalArgumentException("Missing Context");
        }
        this.b = context.getApplicationContext();
        this.c = map;
    }

    private void a(Thread thread, Throwable th) {
        if (this.a != null) {
            this.a.uncaughtException(thread, th);
            return;
        }
        try {
            Process.killProcess(Process.myPid());
        } catch (Throwable unused) {
        }
        try {
            System.exit(10);
        } catch (Throwable unused2) {
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            String a2 = q.a(th);
            if (a2 != null && a2.contains(BuildConfig.APPLICATION_ID)) {
                Map<String, String> a3 = new b(a2, this.c).a();
                a3.put("subtype", "crash");
                a3.put("subtype_code", "0");
                e.a(new d(n.b(), n.c(), a3), this.b);
            }
        } catch (Exception unused) {
        }
        a(thread, th);
    }
}
