package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaj;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzal {
    private static void zza(DataLayer dataLayer, zzaj.zzd zzd) {
        for (zzak.zza zze : zzd.zzkA) {
            dataLayer.zzha(zzdl.zze(zze));
        }
    }

    public static void zza(DataLayer dataLayer, zzaj.zzi zzi) {
        if (zzi.zzlp == null) {
            zzbo.zzbh("supplemental missing experimentSupplemental");
            return;
        }
        zza(dataLayer, zzi.zzlp);
        zzb(dataLayer, zzi.zzlp);
        zzc(dataLayer, zzi.zzlp);
    }

    private static void zzb(DataLayer dataLayer, zzaj.zzd zzd) {
        for (zzak.zza zzc : zzd.zzkz) {
            Map<String, Object> zzc2 = zzc(zzc);
            if (zzc2 != null) {
                dataLayer.push(zzc2);
            }
        }
    }

    private static Map<String, Object> zzc(zzak.zza zza) {
        Object zzj = zzdl.zzj(zza);
        if (zzj instanceof Map) {
            return (Map) zzj;
        }
        String valueOf = String.valueOf(zzj);
        StringBuilder sb = new StringBuilder(36 + String.valueOf(valueOf).length());
        sb.append("value: ");
        sb.append(valueOf);
        sb.append(" is not a map value, ignored.");
        zzbo.zzbh(sb.toString());
        return null;
    }

    private static void zzc(DataLayer dataLayer, zzaj.zzd zzd) {
        String str;
        for (zzaj.zzc zzc : zzd.zzkB) {
            if (zzc.zzaB == null) {
                str = "GaExperimentRandom: No key";
            } else {
                Object obj = dataLayer.get(zzc.zzaB);
                Long valueOf = !(obj instanceof Number) ? null : Long.valueOf(((Number) obj).longValue());
                long j = zzc.zzkv;
                long j2 = zzc.zzkw;
                if (!zzc.zzkx || valueOf == null || valueOf.longValue() < j || valueOf.longValue() > j2) {
                    if (j <= j2) {
                        obj = Long.valueOf(Math.round((Math.random() * ((double) (j2 - j))) + ((double) j)));
                    } else {
                        str = "GaExperimentRandom: random range invalid";
                    }
                }
                dataLayer.zzha(zzc.zzaB);
                Map<String, Object> zzo = dataLayer.zzo(zzc.zzaB, obj);
                if (zzc.zzky > 0) {
                    if (!zzo.containsKey("gtm")) {
                        zzo.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(zzc.zzky)));
                    } else {
                        Object obj2 = zzo.get("gtm");
                        if (obj2 instanceof Map) {
                            ((Map) obj2).put("lifetime", Long.valueOf(zzc.zzky));
                        } else {
                            zzbo.zzbh("GaExperimentRandom: gtm not a map");
                        }
                    }
                }
                dataLayer.push(zzo);
            }
            zzbo.zzbh(str);
        }
    }
}
