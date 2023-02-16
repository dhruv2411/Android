package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzdh extends zzam {
    private static final String ID = zzah.TIME.toString();

    public zzdh() {
        super(ID, new String[0]);
    }

    public boolean zzQb() {
        return false;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        return zzdl.zzR(Long.valueOf(System.currentTimeMillis()));
    }
}
