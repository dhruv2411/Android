package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzcm extends zzam {
    private static final String ID = zzah.REGEX_GROUP.toString();
    private static final String zzbHn = zzai.ARG0.toString();
    private static final String zzbHo = zzai.ARG1.toString();
    private static final String zzbHp = zzai.IGNORE_CASE.toString();
    private static final String zzbHq = zzai.GROUP.toString();

    public zzcm() {
        super(ID, zzbHn, zzbHo);
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        zzak.zza zza = map.get(zzbHn);
        zzak.zza zza2 = map.get(zzbHo);
        if (zza == null || zza == zzdl.zzRR() || zza2 == null || zza2 == zzdl.zzRR()) {
            return zzdl.zzRR();
        }
        int i = 64;
        if (zzdl.zzi(map.get(zzbHp)).booleanValue()) {
            i = 66;
        }
        int i2 = 1;
        zzak.zza zza3 = map.get(zzbHq);
        if (zza3 != null) {
            Long zzg = zzdl.zzg(zza3);
            if (zzg == zzdl.zzRM()) {
                return zzdl.zzRR();
            }
            i2 = zzg.intValue();
            if (i2 < 0) {
                return zzdl.zzRR();
            }
        }
        try {
            String zze = zzdl.zze(zza);
            String zze2 = zzdl.zze(zza2);
            String str = null;
            Matcher matcher = Pattern.compile(zze2, i).matcher(zze);
            if (matcher.find() && matcher.groupCount() >= i2) {
                str = matcher.group(i2);
            }
            return str == null ? zzdl.zzRR() : zzdl.zzR(str);
        } catch (PatternSyntaxException unused) {
            return zzdl.zzRR();
        }
    }
}
