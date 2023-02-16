package com.google.android.gms.internal;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import java.security.MessageDigest;

@zzme
public class zzdj extends zzdg {
    private MessageDigest zzyt;

    public byte[] zzF(String str) {
        byte[] zza = zza(str.split(" "));
        this.zzyt = zzet();
        synchronized (this.zzrJ) {
            if (this.zzyt == null) {
                byte[] bArr = new byte[0];
                return bArr;
            }
            this.zzyt.reset();
            this.zzyt.update(zza);
            byte[] digest = this.zzyt.digest();
            int i = 4;
            if (digest.length <= 4) {
                i = digest.length;
            }
            byte[] bArr2 = new byte[i];
            System.arraycopy(digest, 0, bArr2, 0, bArr2.length);
            return bArr2;
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] zza(String[] strArr) {
        if (strArr.length == 1) {
            return zzdi.zzp(zzdi.zzH(strArr[0]));
        }
        if (strArr.length < 5) {
            byte[] bArr = new byte[(strArr.length * 2)];
            for (int i = 0; i < strArr.length; i++) {
                byte[] zzs = zzs(zzdi.zzH(strArr[i]));
                int i2 = i * 2;
                bArr[i2] = zzs[0];
                bArr[i2 + 1] = zzs[1];
            }
            return bArr;
        }
        byte[] bArr2 = new byte[strArr.length];
        for (int i3 = 0; i3 < strArr.length; i3++) {
            bArr2[i3] = zzr(zzdi.zzH(strArr[i3]));
        }
        return bArr2;
    }

    /* access modifiers changed from: package-private */
    public byte zzr(int i) {
        return (byte) (((i & ViewCompat.MEASURED_STATE_MASK) >> 24) ^ (((i & 255) ^ ((65280 & i) >> 8)) ^ ((16711680 & i) >> 16)));
    }

    /* access modifiers changed from: package-private */
    public byte[] zzs(int i) {
        int i2 = ((i & SupportMenu.CATEGORY_MASK) >> 16) ^ (65535 & i);
        return new byte[]{(byte) i2, (byte) (i2 >> 8)};
    }
}
