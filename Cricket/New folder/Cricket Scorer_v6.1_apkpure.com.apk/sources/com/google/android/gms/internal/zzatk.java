package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzac;

abstract class zzatk {
    private static volatile Handler zzafd;
    /* access modifiers changed from: private */
    public volatile long zzafe;
    /* access modifiers changed from: private */
    public final zzaue zzbqc;
    /* access modifiers changed from: private */
    public boolean zzbrt = true;
    private final Runnable zzw = new Runnable() {
        public void run() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                zzatk.this.zzbqc.zzKk().zzm(this);
                return;
            }
            boolean zzcy = zzatk.this.zzcy();
            long unused = zzatk.this.zzafe = 0;
            if (zzcy && zzatk.this.zzbrt) {
                zzatk.this.run();
            }
        }
    };

    zzatk(zzaue zzaue) {
        zzac.zzw(zzaue);
        this.zzbqc = zzaue;
    }

    private Handler getHandler() {
        Handler handler;
        if (zzafd != null) {
            return zzafd;
        }
        synchronized (zzatk.class) {
            if (zzafd == null) {
                zzafd = new Handler(this.zzbqc.getContext().getMainLooper());
            }
            handler = zzafd;
        }
        return handler;
    }

    public void cancel() {
        this.zzafe = 0;
        getHandler().removeCallbacks(this.zzw);
    }

    public abstract void run();

    public boolean zzcy() {
        return this.zzafe != 0;
    }

    public void zzy(long j) {
        cancel();
        if (j >= 0) {
            this.zzafe = this.zzbqc.zznR().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzw, j)) {
                this.zzbqc.zzKl().zzLY().zzj("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }
}
