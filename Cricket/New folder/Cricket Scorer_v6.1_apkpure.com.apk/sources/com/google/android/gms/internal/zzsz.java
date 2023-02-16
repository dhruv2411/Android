package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class zzsz {
    private final Map<String, String> zzFP;
    private final List<zzsl> zzagg;
    private final long zzagh;
    private final long zzagi;
    private final int zzagj;
    private final boolean zzagk;
    private final String zzagl;

    public zzsz(zzrz zzrz, Map<String, String> map, long j, boolean z) {
        this(zzrz, map, j, z, 0, 0, (List<zzsl>) null);
    }

    public zzsz(zzrz zzrz, Map<String, String> map, long j, boolean z, long j2, int i) {
        this(zzrz, map, j, z, j2, i, (List<zzsl>) null);
    }

    public zzsz(zzrz zzrz, Map<String, String> map, long j, boolean z, long j2, int i, List<zzsl> list) {
        String zza;
        String zza2;
        zzac.zzw(zzrz);
        zzac.zzw(map);
        this.zzagi = j;
        this.zzagk = z;
        this.zzagh = j2;
        this.zzagj = i;
        this.zzagg = list != null ? list : Collections.emptyList();
        this.zzagl = zzs(list);
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            if (zzl(next.getKey()) && (zza2 = zza(zzrz, next.getKey())) != null) {
                hashMap.put(zza2, zzb(zzrz, next.getValue()));
            }
        }
        for (Map.Entry next2 : map.entrySet()) {
            if (!zzl(next2.getKey()) && (zza = zza(zzrz, next2.getKey())) != null) {
                hashMap.put(zza, zzb(zzrz, next2.getValue()));
            }
        }
        if (!TextUtils.isEmpty(this.zzagl)) {
            zztm.zzc(hashMap, "_v", this.zzagl);
            if (this.zzagl.equals("ma4.0.0") || this.zzagl.equals("ma4.0.1")) {
                hashMap.remove("adid");
            }
        }
        this.zzFP = Collections.unmodifiableMap(hashMap);
    }

    public static zzsz zza(zzrz zzrz, zzsz zzsz, Map<String, String> map) {
        return new zzsz(zzrz, map, zzsz.zzpQ(), zzsz.zzpS(), zzsz.zzpP(), zzsz.zzpO(), zzsz.zzpR());
    }

    private static String zza(zzrz zzrz, Object obj) {
        if (obj == null) {
            return null;
        }
        String obj2 = obj.toString();
        if (obj2.startsWith("&")) {
            obj2 = obj2.substring(1);
        }
        int length = obj2.length();
        if (length > 256) {
            obj2 = obj2.substring(0, 256);
            zzrz.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(length), obj2);
        }
        if (TextUtils.isEmpty(obj2)) {
            return null;
        }
        return obj2;
    }

    private static String zzb(zzrz zzrz, Object obj) {
        String obj2 = obj == null ? "" : obj.toString();
        int length = obj2.length();
        if (length <= 8192) {
            return obj2;
        }
        String substring = obj2.substring(0, 8192);
        zzrz.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(length), substring);
        return substring;
    }

    private static boolean zzl(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.toString().startsWith("&");
    }

    private String zzs(String str, String str2) {
        zzac.zzdr(str);
        zzac.zzb(!str.startsWith("&"), (Object) "Short param name required");
        String str3 = this.zzFP.get(str);
        return str3 != null ? str3 : str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzs(java.util.List<com.google.android.gms.internal.zzsl> r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0024
            java.util.Iterator r4 = r4.iterator()
        L_0x0007:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0024
            java.lang.Object r1 = r4.next()
            com.google.android.gms.internal.zzsl r1 = (com.google.android.gms.internal.zzsl) r1
            java.lang.String r2 = "appendVersion"
            java.lang.String r3 = r1.getId()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0007
            java.lang.String r4 = r1.getValue()
            goto L_0x0025
        L_0x0024:
            r4 = r0
        L_0x0025:
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 == 0) goto L_0x002c
            return r0
        L_0x002c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsz.zzs(java.util.List):java.lang.String");
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ht=");
        stringBuffer.append(this.zzagi);
        if (this.zzagh != 0) {
            stringBuffer.append(", dbId=");
            stringBuffer.append(this.zzagh);
        }
        if (this.zzagj != 0) {
            stringBuffer.append(", appUID=");
            stringBuffer.append(this.zzagj);
        }
        ArrayList<String> arrayList = new ArrayList<>(this.zzFP.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            stringBuffer.append(", ");
            stringBuffer.append(str);
            stringBuffer.append("=");
            stringBuffer.append(this.zzFP.get(str));
        }
        return stringBuffer.toString();
    }

    public Map<String, String> zzfE() {
        return this.zzFP;
    }

    public int zzpO() {
        return this.zzagj;
    }

    public long zzpP() {
        return this.zzagh;
    }

    public long zzpQ() {
        return this.zzagi;
    }

    public List<zzsl> zzpR() {
        return this.zzagg;
    }

    public boolean zzpS() {
        return this.zzagk;
    }

    public long zzpT() {
        return zztm.zzcf(zzs("_s", "0"));
    }

    public String zzpU() {
        return zzs("_m", "");
    }
}
