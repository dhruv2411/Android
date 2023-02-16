package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zze extends zzam {
    private static final String ID = zzah.ADWORDS_CLICK_REFERRER.toString();
    private static final String zzbEM = zzai.COMPONENT.toString();
    private static final String zzbEN = zzai.CONVERSION_ID.toString();
    private final Context zzqn;

    public zze(Context context) {
        super(ID, zzbEN);
        this.zzqn = context;
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        zzak.zza zza = map.get(zzbEN);
        if (zza == null) {
            return zzdl.zzRR();
        }
        String zze = zzdl.zze(zza);
        zzak.zza zza2 = map.get(zzbEM);
        String zzr = zzbf.zzr(this.zzqn, zze, zza2 != null ? zzdl.zze(zza2) : null);
        return zzr != null ? zzdl.zzR(zzr) : zzdl.zzRR();
    }
}
