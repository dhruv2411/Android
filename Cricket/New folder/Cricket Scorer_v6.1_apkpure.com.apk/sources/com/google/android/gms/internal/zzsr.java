package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzac;

abstract class zzsr {
    private static volatile Handler zzafd;
    /* access modifiers changed from: private */
    public final zzsc zzadO;
    /* access modifiers changed from: private */
    public volatile long zzafe;
    private final Runnable zzw = new Runnable() {
        public void run() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                zzsr.this.zzadO.zznU().zzg(this);
                return;
            }
            boolean zzcy = zzsr.this.zzcy();
            long unused = zzsr.this.zzafe = 0;
            if (zzcy) {
                zzsr.this.run();
            }
        }
    };

    zzsr(zzsc zzsc) {
        zzac.zzw(zzsc);
        this.zzadO = zzsc;
    }

    private Handler getHandler() {
        Handler handler;
        if (zzafd != null) {
            return zzafd;
        }
        synchronized (zzsr.class) {
            if (zzafd == null) {
                zzafd = new Handler(this.zzadO.getContext().getMainLooper());
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

    public long zzpA() {
        if (this.zzafe == 0) {
            return 0;
        }
        return Math.abs(this.zzadO.zznR().currentTimeMillis() - this.zzafe);
    }

    public void zzy(long j) {
        cancel();
        if (j >= 0) {
            this.zzafe = this.zzadO.zznR().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzw, j)) {
                this.zzadO.zznS().zze("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public void zzz(long j) {
        if (zzcy()) {
            long j2 = 0;
            if (j < 0) {
                cancel();
                return;
            }
            long abs = j - Math.abs(this.zzadO.zznR().currentTimeMillis() - this.zzafe);
            if (abs >= 0) {
                j2 = abs;
            }
            getHandler().removeCallbacks(this.zzw);
            if (!getHandler().postDelayed(this.zzw, j2)) {
                this.zzadO.zznS().zze("Failed to adjust delayed post. time", Long.valueOf(j2));
            }
        }
    }
}
