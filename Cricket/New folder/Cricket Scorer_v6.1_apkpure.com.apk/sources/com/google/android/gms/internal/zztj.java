package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;

class zztj {
    private long zzLp;
    private final zze zzuP;

    public zztj(zze zze) {
        zzac.zzw(zze);
        this.zzuP = zze;
    }

    public zztj(zze zze, long j) {
        zzac.zzw(zze);
        this.zzuP = zze;
        this.zzLp = j;
    }

    public void clear() {
        this.zzLp = 0;
    }

    public void start() {
        this.zzLp = this.zzuP.elapsedRealtime();
    }

    public boolean zzA(long j) {
        return this.zzLp == 0 || this.zzuP.elapsedRealtime() - this.zzLp > j;
    }
}
