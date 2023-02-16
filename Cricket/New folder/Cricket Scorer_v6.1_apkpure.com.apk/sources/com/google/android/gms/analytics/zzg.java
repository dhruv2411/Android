package com.google.android.gms.analytics;

import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class zzg<T extends zzg> {
    private final zzh zzace;
    protected final zze zzacf;
    private final List<Object> zzacg = new ArrayList();

    protected zzg(zzh zzh, zze zze) {
        zzac.zzw(zzh);
        this.zzace = zzh;
        zze zze2 = new zze(this, zze);
        zze2.zzmM();
        this.zzacf = zze2;
    }

    /* access modifiers changed from: protected */
    public void zza(zze zze) {
    }

    /* access modifiers changed from: protected */
    public void zzd(zze zze) {
        Iterator<Object> it = this.zzacg.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    /* access modifiers changed from: protected */
    public zzh zzmK() {
        return this.zzace;
    }

    public zze zzmN() {
        return this.zzacf;
    }

    public List<zzi> zzmO() {
        return this.zzacf.zzmE();
    }

    public zze zzmo() {
        zze zzmC = this.zzacf.zzmC();
        zzd(zzmC);
        return zzmC;
    }
}
