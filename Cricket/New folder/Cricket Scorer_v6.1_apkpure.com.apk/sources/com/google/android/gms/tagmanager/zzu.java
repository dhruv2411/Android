package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.HashMap;
import java.util.Map;

class zzu extends zzam {
    private static final String ID = zzah.FUNCTION_CALL.toString();
    private static final String zzbEO = zzai.ADDITIONAL_PARAMS.toString();
    private static final String zzbFz = zzai.FUNCTION_CALL_NAME.toString();
    private final zza zzbFA;

    public interface zza {
        Object zze(String str, Map<String, Object> map);
    }

    public zzu(zza zza2) {
        super(ID, zzbFz);
        this.zzbFA = zza2;
    }

    public boolean zzQb() {
        return false;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        String sb;
        String zze = zzdl.zze(map.get(zzbFz));
        HashMap hashMap = new HashMap();
        zzak.zza zza2 = map.get(zzbEO);
        if (zza2 != null) {
            Object zzj = zzdl.zzj(zza2);
            if (!(zzj instanceof Map)) {
                sb = "FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.";
                zzbo.zzbh(sb);
                return zzdl.zzRR();
            }
            for (Map.Entry entry : ((Map) zzj).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzdl.zzR(this.zzbFA.zze(zze, hashMap));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            StringBuilder sb2 = new StringBuilder(34 + String.valueOf(zze).length() + String.valueOf(valueOf).length());
            sb2.append("Custom macro/tag ");
            sb2.append(zze);
            sb2.append(" threw exception ");
            sb2.append(valueOf);
            sb = sb2.toString();
        }
    }
}
