package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzaf extends zzam {
    private static final String ID = zzah.ENCODE.toString();
    private static final String zzbGf = zzai.ARG0.toString();
    private static final String zzbGg = zzai.NO_PADDING.toString();
    private static final String zzbGh = zzai.INPUT_FORMAT.toString();
    private static final String zzbGi = zzai.OUTPUT_FORMAT.toString();

    public zzaf() {
        super(ID, zzbGf);
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        String str;
        byte[] bArr;
        String str2;
        zzak.zza zza = map.get(zzbGf);
        if (zza == null || zza == zzdl.zzRR()) {
            return zzdl.zzRR();
        }
        String zze = zzdl.zze(zza);
        zzak.zza zza2 = map.get(zzbGh);
        String zze2 = zza2 == null ? MimeTypes.BASE_TYPE_TEXT : zzdl.zze(zza2);
        zzak.zza zza3 = map.get(zzbGi);
        String zze3 = zza3 == null ? "base16" : zzdl.zze(zza3);
        int i = 2;
        zzak.zza zza4 = map.get(zzbGg);
        if (zza4 != null && zzdl.zzi(zza4).booleanValue()) {
            i = 3;
        }
        try {
            if (MimeTypes.BASE_TYPE_TEXT.equals(zze2)) {
                bArr = zze.getBytes();
            } else if ("base16".equals(zze2)) {
                bArr = zzk.zzgR(zze);
            } else if ("base64".equals(zze2)) {
                bArr = Base64.decode(zze, i);
            } else if ("base64url".equals(zze2)) {
                bArr = Base64.decode(zze, i | 8);
            } else {
                String valueOf = String.valueOf(zze2);
                zzbo.e(valueOf.length() != 0 ? "Encode: unknown input format: ".concat(valueOf) : new String("Encode: unknown input format: "));
                return zzdl.zzRR();
            }
            if ("base16".equals(zze3)) {
                str2 = zzk.zzq(bArr);
            } else if ("base64".equals(zze3)) {
                str2 = Base64.encodeToString(bArr, i);
            } else if ("base64url".equals(zze3)) {
                str2 = Base64.encodeToString(bArr, i | 8);
            } else {
                String valueOf2 = String.valueOf(zze3);
                str = valueOf2.length() != 0 ? "Encode: unknown output format: ".concat(valueOf2) : new String("Encode: unknown output format: ");
                zzbo.e(str);
                return zzdl.zzRR();
            }
            return zzdl.zzR(str2);
        } catch (IllegalArgumentException unused) {
            str = "Encode: invalid input:";
        }
    }
}
