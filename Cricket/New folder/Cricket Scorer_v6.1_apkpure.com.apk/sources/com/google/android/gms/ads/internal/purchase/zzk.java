package com.google.android.gms.ads.internal.purchase;

import android.content.Intent;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;

@zzme
public class zzk {
    private final String zzAs;

    public zzk(String str) {
        this.zzAs = str;
    }

    public boolean zza(String str, int i, Intent intent) {
        String str2;
        if (str == null || intent == null) {
            return false;
        }
        String zze = zzw.zzda().zze(intent);
        String zzf = zzw.zzda().zzf(intent);
        if (zze == null || zzf == null) {
            return false;
        }
        if (!str.equals(zzw.zzda().zzaE(zze))) {
            str2 = "Developer payload not match.";
        } else if (this.zzAs == null || zzl.zzb(this.zzAs, zze, zzf)) {
            return true;
        } else {
            str2 = "Fail to verify signature.";
        }
        zzpk.zzbh(str2);
        return false;
    }

    public String zziM() {
        return zzw.zzcM().zzkM();
    }
}
