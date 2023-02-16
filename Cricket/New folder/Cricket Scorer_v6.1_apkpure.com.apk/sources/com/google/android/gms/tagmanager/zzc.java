package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzc extends zzam {
    private static final String ID = zzah.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza zzbEL;

    public zzc(Context context) {
        this(zza.zzbS(context));
    }

    zzc(zza zza) {
        super(ID, new String[0]);
        this.zzbEL = zza;
    }

    public boolean zzQb() {
        return false;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        return zzdl.zzR(Boolean.valueOf(!this.zzbEL.isLimitAdTrackingEnabled()));
    }
}
