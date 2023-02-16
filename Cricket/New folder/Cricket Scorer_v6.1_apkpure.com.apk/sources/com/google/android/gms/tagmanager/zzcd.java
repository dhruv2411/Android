package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzak;
import java.util.Map;

abstract class zzcd extends zzci {
    public zzcd(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzak.zza zza, zzak.zza zza2, Map<String, zzak.zza> map) {
        zzdk zzf = zzdl.zzf(zza);
        zzdk zzf2 = zzdl.zzf(zza2);
        if (zzf == zzdl.zzRP() || zzf2 == zzdl.zzRP()) {
            return false;
        }
        return zza(zzf, zzf2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzdk zzdk, zzdk zzdk2, Map<String, zzak.zza> map);
}
