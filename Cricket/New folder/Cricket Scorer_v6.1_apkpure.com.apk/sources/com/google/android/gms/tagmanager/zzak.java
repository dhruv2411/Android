package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzak extends zzam {
    private static final String ID = zzah.EVENT.toString();
    private final zzcx zzbEW;

    public zzak(zzcx zzcx) {
        super(ID, new String[0]);
        this.zzbEW = zzcx;
    }

    public boolean zzQb() {
        return false;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        String zzRo = this.zzbEW.zzRo();
        return zzRo == null ? zzdl.zzRR() : zzdl.zzR(zzRo);
    }
}
