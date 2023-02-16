package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzdf extends zzdg {
    private static final String ID = zzah.STARTS_WITH.toString();

    public zzdf() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public boolean zza(String str, String str2, Map<String, zzak.zza> map) {
        return str.startsWith(str2);
    }
}
