package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzdn extends zzam {
    private static final String ID = zzah.UPPERCASE_STRING.toString();
    private static final String zzbGf = zzai.ARG0.toString();

    public zzdn() {
        super(ID, zzbGf);
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        return zzdl.zzR(zzdl.zze(map.get(zzbGf)).toUpperCase());
    }
}
