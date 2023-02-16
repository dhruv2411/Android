package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzf;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzrn extends zzf<zzrn> {
    private Map<Integer, Double> zzacW = new HashMap(4);

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.zzacW.entrySet()) {
            String valueOf = String.valueOf(next.getKey());
            StringBuilder sb = new StringBuilder(6 + String.valueOf(valueOf).length());
            sb.append("metric");
            sb.append(valueOf);
            hashMap.put(sb.toString(), next.getValue());
        }
        return zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzrn zzrn) {
        zzrn.zzacW.putAll(this.zzacW);
    }

    public Map<Integer, Double> zzni() {
        return Collections.unmodifiableMap(this.zzacW);
    }
}
