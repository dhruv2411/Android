package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzbs extends zzam {
    private static final String ID = zzah.MOBILE_ADWORDS_UNIQUE_ID.toString();
    private final Context mContext;

    public zzbs(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        String zzbU = zzbU(this.mContext);
        return zzbU == null ? zzdl.zzRR() : zzdl.zzR(zzbU);
    }

    /* access modifiers changed from: protected */
    public String zzbU(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }
}
