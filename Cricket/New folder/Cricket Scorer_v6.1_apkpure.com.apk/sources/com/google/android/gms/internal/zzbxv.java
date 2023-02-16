package com.google.android.gms.internal;

import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import java.io.IOException;
import java.util.Arrays;

final class zzbxv {
    final int tag;
    final byte[] zzbxZ;

    zzbxv(int i, byte[] bArr) {
        this.tag = i;
        this.zzbxZ = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbxv)) {
            return false;
        }
        zzbxv zzbxv = (zzbxv) obj;
        return this.tag == zzbxv.tag && Arrays.equals(this.zzbxZ, zzbxv.zzbxZ);
    }

    public int hashCode() {
        return (31 * (MetaDo.META_OFFSETWINDOWORG + this.tag)) + Arrays.hashCode(this.zzbxZ);
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbxm zzbxm) throws IOException {
        zzbxm.zzrk(this.tag);
        zzbxm.zzaj(this.zzbxZ);
    }

    /* access modifiers changed from: package-private */
    public int zzu() {
        return 0 + zzbxm.zzrl(this.tag) + this.zzbxZ.length;
    }
}
