package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzf;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzrm extends zzf<zzrm> {
    private Map<Integer, String> zzacV = new HashMap(4);

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.zzacV.entrySet()) {
            String valueOf = String.valueOf(next.getKey());
            StringBuilder sb = new StringBuilder(9 + String.valueOf(valueOf).length());
            sb.append("dimension");
            sb.append(valueOf);
            hashMap.put(sb.toString(), next.getValue());
        }
        return zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzrm zzrm) {
        zzrm.zzacV.putAll(this.zzacV);
    }

    public Map<Integer, String> zznh() {
        return Collections.unmodifiableMap(this.zzacV);
    }
}
