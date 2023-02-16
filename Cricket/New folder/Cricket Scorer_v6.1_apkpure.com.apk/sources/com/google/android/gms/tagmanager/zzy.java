package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.List;
import java.util.Map;

class zzy extends zzdj {
    private static final String ID = zzah.DATA_LAYER_WRITE.toString();
    private static final String VALUE = zzai.VALUE.toString();
    private static final String zzbFV = zzai.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzbEV;

    public zzy(DataLayer dataLayer) {
        super(ID, VALUE);
        this.zzbEV = dataLayer;
    }

    private void zza(zzak.zza zza) {
        String zze;
        if (zza != null && zza != zzdl.zzRL() && (zze = zzdl.zze(zza)) != zzdl.zzRQ()) {
            this.zzbEV.zzha(zze);
        }
    }

    private void zzb(zzak.zza zza) {
        if (zza != null && zza != zzdl.zzRL()) {
            Object zzj = zzdl.zzj(zza);
            if (zzj instanceof List) {
                for (Object next : (List) zzj) {
                    if (next instanceof Map) {
                        this.zzbEV.push((Map) next);
                    }
                }
            }
        }
    }

    public void zzab(Map<String, zzak.zza> map) {
        zzb(map.get(VALUE));
        zza(map.get(zzbFV));
    }
}
