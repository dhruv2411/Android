package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzcz extends zzam {
    private static final String ID = zzah.SDK_VERSION.toString();

    public zzcz() {
        super(ID, new String[0]);
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        return zzdl.zzR(Integer.valueOf(Build.VERSION.SDK_INT));
    }
}
