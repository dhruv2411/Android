package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzb extends zzam {
    private static final String ID = zzah.ADVERTISER_ID.toString();
    private final zza zzbEL;

    public zzb(Context context) {
        this(zza.zzbS(context));
    }

    zzb(zza zza) {
        super(ID, new String[0]);
        this.zzbEL = zza;
        this.zzbEL.zzPV();
    }

    public boolean zzQb() {
        return false;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        String zzPV = this.zzbEL.zzPV();
        return zzPV == null ? zzdl.zzRR() : zzdl.zzR(zzPV);
    }
}
