package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzw;

@zzme
public class zzpz {
    private long zzYE;
    private long zzYF = Long.MIN_VALUE;
    private Object zzrJ = new Object();

    public zzpz(long j) {
        this.zzYE = j;
    }

    public boolean tryAcquire() {
        synchronized (this.zzrJ) {
            long elapsedRealtime = zzw.zzcS().elapsedRealtime();
            if (this.zzYF + this.zzYE > elapsedRealtime) {
                return false;
            }
            this.zzYF = elapsedRealtime;
            return true;
        }
    }
}
