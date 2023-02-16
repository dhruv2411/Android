package com.google.android.gms.internal;

import com.google.android.gms.internal.zzb;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class zzy {
    public static String zza(Map<String, String> map) {
        return zza(map, "ISO-8859-1");
    }

    public static String zza(Map<String, String> map, String str) {
        String str2 = map.get(HttpRequest.HEADER_CONTENT_TYPE);
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals(HttpRequest.PARAM_CHARSET)) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    public static zzb.zza zzb(zzj zzj) {
        boolean z;
        long j;
        boolean z2;
        long j2;
        long j3;
        long j4;
        zzj zzj2 = zzj;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = zzj2.zzz;
        String str = map.get(HttpRequest.HEADER_DATE);
        long zzg = str != null ? zzg(str) : 0;
        String str2 = map.get(HttpRequest.HEADER_CACHE_CONTROL);
        if (str2 != null) {
            String[] split = str2.split(",");
            z2 = false;
            j2 = 0;
            j = 0;
            for (String trim : split) {
                String trim2 = trim.trim();
                if (trim2.equals("no-cache") || trim2.equals("no-store")) {
                    return null;
                }
                if (trim2.startsWith("max-age=")) {
                    try {
                        j2 = Long.parseLong(trim2.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim2.startsWith("stale-while-revalidate=")) {
                    j = Long.parseLong(trim2.substring(23));
                } else if (trim2.equals("must-revalidate") || trim2.equals("proxy-revalidate")) {
                    z2 = true;
                }
            }
            z = true;
        } else {
            z2 = false;
            z = false;
            j2 = 0;
            j = 0;
        }
        String str3 = map.get(HttpRequest.HEADER_EXPIRES);
        long zzg2 = str3 != null ? zzg(str3) : 0;
        String str4 = map.get(HttpRequest.HEADER_LAST_MODIFIED);
        long zzg3 = str4 != null ? zzg(str4) : 0;
        String str5 = map.get(HttpRequest.HEADER_ETAG);
        if (z) {
            long j5 = currentTimeMillis + (j2 * 1000);
            j4 = z2 ? j5 : j5 + (j * 1000);
            j3 = j5;
        } else if (zzg <= 0 || zzg2 < zzg) {
            j4 = 0;
            j3 = 0;
        } else {
            j3 = currentTimeMillis + (zzg2 - zzg);
            j4 = j3;
        }
        zzb.zza zza = new zzb.zza();
        zza.data = zzj2.data;
        zza.zza = str5;
        zza.zze = j3;
        zza.zzd = j4;
        zza.zzb = zzg;
        zza.zzc = zzg3;
        zza.zzf = map;
        return zza;
    }

    public static long zzg(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException unused) {
            return 0;
        }
    }
}
