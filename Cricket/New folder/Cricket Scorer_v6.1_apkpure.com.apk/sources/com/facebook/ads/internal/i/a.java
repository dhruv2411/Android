package com.facebook.ads.internal.i;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.f.c;

public class a {
    private static final String a = "com.facebook.ads.internal.i.a";
    private static a b = null;
    private static boolean c = false;
    private Context d;

    private a(Context context) {
        this.d = context;
    }

    public static a a(Context context) {
        if (b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (applicationContext) {
                if (b == null) {
                    b = new a(applicationContext);
                }
            }
        }
        return b;
    }

    public synchronized void a() {
        if (!c) {
            if (com.facebook.ads.internal.l.a.h(this.d)) {
                try {
                    Thread.setDefaultUncaughtExceptionHandler(new c(Thread.getDefaultUncaughtExceptionHandler(), this.d, new c(this.d, false).b()));
                } catch (SecurityException e) {
                    Log.e(a, "No permissions to set the default uncaught exception handler", e);
                }
            }
            c = true;
        }
    }
}
