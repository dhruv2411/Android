package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzw extends zzam {
    private static final String ID = zzah.CUSTOM_VAR.toString();
    private static final String NAME = zzai.NAME.toString();
    private static final String zzbFK = zzai.DEFAULT_VALUE.toString();
    private final DataLayer zzbEV;

    public zzw(DataLayer dataLayer) {
        super(ID, NAME);
        this.zzbEV = dataLayer;
    }

    public boolean zzQb() {
        return false;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        Object obj = this.zzbEV.get(zzdl.zze(map.get(NAME)));
        if (obj != null) {
            return zzdl.zzR(obj);
        }
        zzak.zza zza = map.get(zzbFK);
        return zza != null ? zza : zzdl.zzRR();
    }
}
