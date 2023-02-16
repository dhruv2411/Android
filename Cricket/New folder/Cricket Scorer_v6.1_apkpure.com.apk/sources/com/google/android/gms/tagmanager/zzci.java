package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class zzci extends zzam {
    private static final String zzbGf = zzai.ARG0.toString();
    private static final String zzbHc = zzai.ARG1.toString();

    public zzci(String str) {
        super(str, zzbGf, zzbHc);
    }

    public /* bridge */ /* synthetic */ String zzQL() {
        return super.zzQL();
    }

    public /* bridge */ /* synthetic */ Set zzQM() {
        return super.zzQM();
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        boolean z;
        Iterator<zzak.zza> it = map.values().iterator();
        while (true) {
            z = false;
            if (it.hasNext()) {
                if (it.next() == zzdl.zzRR()) {
                    break;
                }
            } else {
                zzak.zza zza = map.get(zzbGf);
                zzak.zza zza2 = map.get(zzbHc);
                if (zza != null && zza2 != null) {
                    z = zza(zza, zza2, map);
                }
            }
        }
        return zzdl.zzR(Boolean.valueOf(z));
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzak.zza zza, zzak.zza zza2, Map<String, zzak.zza> map);
}
