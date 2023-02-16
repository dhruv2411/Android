package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzbl extends zzcd {
    private static final String ID = zzah.LESS_THAN.toString();

    public zzbl() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzdk zzdk, zzdk zzdk2, Map<String, zzak.zza> map) {
        return zzdk.compareTo(zzdk2) < 0;
    }
}
