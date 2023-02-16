package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzak;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class zzdp {
    private static zzce<zzak.zza> zza(zzce<zzak.zza> zzce) {
        try {
            return new zzce<>(zzdl.zzR(zzhD(zzdl.zze(zzce.getObject()))), zzce.zzQZ());
        } catch (UnsupportedEncodingException e) {
            zzbo.zzb("Escape URI: unsupported encoding", e);
            return zzce;
        }
    }

    private static zzce<zzak.zza> zza(zzce<zzak.zza> zzce, int i) {
        String sb;
        if (!zzl(zzce.getObject())) {
            sb = "Escaping can only be applied to strings.";
        } else if (i == 12) {
            return zza(zzce);
        } else {
            StringBuilder sb2 = new StringBuilder(39);
            sb2.append("Unsupported Value Escaping: ");
            sb2.append(i);
            sb = sb2.toString();
        }
        zzbo.e(sb);
        return zzce;
    }

    static zzce<zzak.zza> zza(zzce<zzak.zza> zzce, int... iArr) {
        for (int zza : iArr) {
            zzce = zza(zzce, zza);
        }
        return zzce;
    }

    static String zzhD(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }

    private static boolean zzl(zzak.zza zza) {
        return zzdl.zzj(zza) instanceof String;
    }
}
