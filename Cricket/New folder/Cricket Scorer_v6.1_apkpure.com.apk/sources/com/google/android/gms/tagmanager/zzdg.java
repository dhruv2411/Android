package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzak;
import java.util.Map;

abstract class zzdg extends zzci {
    public zzdg(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzak.zza zza, zzak.zza zza2, Map<String, zzak.zza> map) {
        String zze = zzdl.zze(zza);
        String zze2 = zzdl.zze(zza2);
        if (zze == zzdl.zzRQ() || zze2 == zzdl.zzRQ()) {
            return false;
        }
        return zza(zze, zze2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(String str, String str2, Map<String, zzak.zza> map);
}
