package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzs extends zzdg {
    private static final String ID = zzah.CONTAINS.toString();

    public zzs() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public boolean zza(String str, String str2, Map<String, zzak.zza> map) {
        return str.contains(str2);
    }
}
