package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzcf extends zzam {
    private static final String ID = zzah.OS_VERSION.toString();

    public zzcf() {
        super(ID, new String[0]);
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        return zzdl.zzR(Build.VERSION.RELEASE);
    }
}
