package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzcn extends zzdg {
    private static final String ID = zzah.REGEX.toString();
    private static final String zzbHp = zzai.IGNORE_CASE.toString();

    public zzcn() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public boolean zza(String str, String str2, Map<String, zzak.zza> map) {
        try {
            return Pattern.compile(str2, zzdl.zzi(map.get(zzbHp)).booleanValue() ? 66 : 64).matcher(str).find();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }
}
