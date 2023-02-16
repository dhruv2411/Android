package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaj;
import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import java.io.IOException;

public interface zzbjd {

    public static final class zza extends zzbxn<zza> {
        public long zzbNe;
        public zzaj.zzj zzbNf;
        public zzaj.zzf zzlr;

        public zza() {
            zzTv();
        }

        public static zza zzQ(byte[] bArr) throws zzbxs {
            return (zza) zzbxt.zza(new zza(), bArr);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.zzbNe != zza.zzbNe) {
                return false;
            }
            if (this.zzlr == null) {
                if (zza.zzlr != null) {
                    return false;
                }
            } else if (!this.zzlr.equals(zza.zzlr)) {
                return false;
            }
            if (this.zzbNf == null) {
                if (zza.zzbNf != null) {
                    return false;
                }
            } else if (!this.zzbNf.equals(zza.zzbNf)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zza.zzcuI == null || zza.zzcuI.isEmpty() : this.zzcuI.equals(zza.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + ((int) (this.zzbNe ^ (this.zzbNe >>> 32)))) * 31) + (this.zzlr == null ? 0 : this.zzlr.hashCode())) * 31) + (this.zzbNf == null ? 0 : this.zzbNf.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zza zzTv() {
            this.zzbNe = 0;
            this.zzlr = null;
            this.zzbNf = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzW */
        public zza zzb(zzbxl zzbxl) throws IOException {
            zzbxt zzbxt;
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo != 8) {
                    if (zzaeo == 18) {
                        if (this.zzlr == null) {
                            this.zzlr = new zzaj.zzf();
                        }
                        zzbxt = this.zzlr;
                    } else if (zzaeo == 26) {
                        if (this.zzbNf == null) {
                            this.zzbNf = new zzaj.zzj();
                        }
                        zzbxt = this.zzbNf;
                    } else if (!super.zza(zzbxl, zzaeo)) {
                        return this;
                    }
                    zzbxl.zza(zzbxt);
                } else {
                    this.zzbNe = zzbxl.zzaer();
                }
            }
        }

        public void zza(zzbxm zzbxm) throws IOException {
            zzbxm.zzb(1, this.zzbNe);
            if (this.zzlr != null) {
                zzbxm.zza(2, (zzbxt) this.zzlr);
            }
            if (this.zzbNf != null) {
                zzbxm.zza(3, (zzbxt) this.zzbNf);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu() + zzbxm.zzf(1, this.zzbNe);
            if (this.zzlr != null) {
                zzu += zzbxm.zzc(2, (zzbxt) this.zzlr);
            }
            return this.zzbNf != null ? zzu + zzbxm.zzc(3, (zzbxt) this.zzbNf) : zzu;
        }
    }
}
