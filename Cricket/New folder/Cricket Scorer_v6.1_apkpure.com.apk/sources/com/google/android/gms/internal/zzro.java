package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzf;
import com.google.android.gms.common.internal.zzac;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzro extends zzf<zzro> {
    private final Map<String, Object> zzFP = new HashMap();

    private String zzbA(String str) {
        zzac.zzdr(str);
        if (str != null && str.startsWith("&")) {
            str = str.substring(1);
        }
        zzac.zzh(str, "Name can not be empty or \"&\"");
        return str;
    }

    public void set(String str, String str2) {
        this.zzFP.put(zzbA(str), str2);
    }

    public String toString() {
        return zzj(this.zzFP);
    }

    /* renamed from: zza */
    public void zzb(zzro zzro) {
        zzac.zzw(zzro);
        zzro.zzFP.putAll(this.zzFP);
    }

    public Map<String, Object> zznj() {
        return Collections.unmodifiableMap(this.zzFP);
    }
}
