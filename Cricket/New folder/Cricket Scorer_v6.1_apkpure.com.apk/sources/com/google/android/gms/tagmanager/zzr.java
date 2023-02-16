package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzr extends zzam {
    private static final String ID = zzah.CONTAINER_VERSION.toString();
    private final String zzavB;

    public zzr(String str) {
        super(ID, new String[0]);
        this.zzavB = str;
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        return this.zzavB == null ? zzdl.zzRR() : zzdl.zzR(this.zzavB);
    }
}
