package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class zzar extends zzam {
    private static final String ID = zzah.HASH.toString();
    private static final String zzbGf = zzai.ARG0.toString();
    private static final String zzbGh = zzai.INPUT_FORMAT.toString();
    private static final String zzbGl = zzai.ALGORITHM.toString();

    public zzar() {
        super(ID, zzbGf);
    }

    private byte[] zzf(String str, byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(bArr);
        return instance.digest();
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        String str;
        byte[] bArr;
        zzak.zza zza = map.get(zzbGf);
        if (zza == null || zza == zzdl.zzRR()) {
            return zzdl.zzRR();
        }
        String zze = zzdl.zze(zza);
        zzak.zza zza2 = map.get(zzbGl);
        String zze2 = zza2 == null ? "MD5" : zzdl.zze(zza2);
        zzak.zza zza3 = map.get(zzbGh);
        String zze3 = zza3 == null ? MimeTypes.BASE_TYPE_TEXT : zzdl.zze(zza3);
        if (MimeTypes.BASE_TYPE_TEXT.equals(zze3)) {
            bArr = zze.getBytes();
        } else if ("base16".equals(zze3)) {
            bArr = zzk.zzgR(zze);
        } else {
            String valueOf = String.valueOf(zze3);
            str = valueOf.length() != 0 ? "Hash: unknown input format: ".concat(valueOf) : new String("Hash: unknown input format: ");
            zzbo.e(str);
            return zzdl.zzRR();
        }
        try {
            return zzdl.zzR(zzk.zzq(zzf(zze2, bArr)));
        } catch (NoSuchAlgorithmException unused) {
            String valueOf2 = String.valueOf(zze2);
            str = valueOf2.length() != 0 ? "Hash: unknown algorithm: ".concat(valueOf2) : new String("Hash: unknown algorithm: ");
        }
    }
}
