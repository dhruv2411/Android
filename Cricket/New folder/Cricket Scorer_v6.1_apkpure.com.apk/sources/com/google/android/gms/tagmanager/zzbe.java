package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzbe extends zzam {
    private static final String ID = zzah.INSTALL_REFERRER.toString();
    private static final String zzbEM = zzai.COMPONENT.toString();
    private final Context zzqn;

    public zzbe(Context context) {
        super(ID, new String[0]);
        this.zzqn = context;
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        String zzO = zzbf.zzO(this.zzqn, map.get(zzbEM) != null ? zzdl.zze(map.get(zzbEM)) : null);
        return zzO != null ? zzdl.zzR(zzO) : zzdl.zzRR();
    }
}
