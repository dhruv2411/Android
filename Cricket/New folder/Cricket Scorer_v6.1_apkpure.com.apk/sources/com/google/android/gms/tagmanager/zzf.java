package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzf extends zzam {
    private static final String ID = zzah.APP_ID.toString();
    private final Context mContext;

    public zzf(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        return zzdl.zzR(this.mContext.getPackageName());
    }
}
