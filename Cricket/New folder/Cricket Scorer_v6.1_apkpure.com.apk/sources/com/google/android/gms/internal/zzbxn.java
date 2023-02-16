package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbxn;
import java.io.IOException;

public abstract class zzbxn<M extends zzbxn<M>> extends zzbxt {
    protected zzbxp zzcuI;

    private void zza(int i, zzbxv zzbxv) {
        zzbxq zzbxq;
        if (this.zzcuI == null) {
            this.zzcuI = new zzbxp();
            zzbxq = null;
        } else {
            zzbxq = this.zzcuI.zzro(i);
        }
        if (zzbxq == null) {
            zzbxq = new zzbxq();
            this.zzcuI.zza(i, zzbxq);
        }
        zzbxq.zza(zzbxv);
    }

    public final <T> T zza(zzbxo<M, T> zzbxo) {
        zzbxq zzro;
        if (this.zzcuI == null || (zzro = this.zzcuI.zzro(zzbxw.zzrs(zzbxo.tag))) == null) {
            return null;
        }
        return zzro.zzb(zzbxo);
    }

    public void zza(zzbxm zzbxm) throws IOException {
        if (this.zzcuI != null) {
            for (int i = 0; i < this.zzcuI.size(); i++) {
                this.zzcuI.zzrp(i).zza(zzbxm);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbxl zzbxl, int i) throws IOException {
        int position = zzbxl.getPosition();
        if (!zzbxl.zzqY(i)) {
            return false;
        }
        zza(zzbxw.zzrs(i), new zzbxv(i, zzbxl.zzI(position, zzbxl.getPosition() - position)));
        return true;
    }

    /* renamed from: zzaeH */
    public M clone() throws CloneNotSupportedException {
        M m = (zzbxn) super.clone();
        zzbxr.zza(this, (zzbxn) m);
        return m;
    }

    public /* synthetic */ zzbxt zzaeI() throws CloneNotSupportedException {
        return (zzbxn) clone();
    }

    /* access modifiers changed from: protected */
    public int zzu() {
        if (this.zzcuI == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzcuI.size(); i2++) {
            i += this.zzcuI.zzrp(i2).zzu();
        }
        return i;
    }
}
