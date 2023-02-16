package com.google.android.gms.internal;

import java.io.IOException;

public interface zzaf {

    public static final class zza extends zzbxn<zza> {
        public String stackTrace = null;
        public String zzaS = null;
        public Long zzaT = null;
        public String zzaU = null;
        public String zzaV = null;
        public Long zzaW = null;
        public Long zzaX = null;
        public String zzaY = null;
        public Long zzaZ = null;
        public String zzba = null;

        public zza() {
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaS != null) {
                zzbxm.zzq(1, this.zzaS);
            }
            if (this.zzaT != null) {
                zzbxm.zzb(2, this.zzaT.longValue());
            }
            if (this.stackTrace != null) {
                zzbxm.zzq(3, this.stackTrace);
            }
            if (this.zzaU != null) {
                zzbxm.zzq(4, this.zzaU);
            }
            if (this.zzaV != null) {
                zzbxm.zzq(5, this.zzaV);
            }
            if (this.zzaW != null) {
                zzbxm.zzb(6, this.zzaW.longValue());
            }
            if (this.zzaX != null) {
                zzbxm.zzb(7, this.zzaX.longValue());
            }
            if (this.zzaY != null) {
                zzbxm.zzq(8, this.zzaY);
            }
            if (this.zzaZ != null) {
                zzbxm.zzb(9, this.zzaZ.longValue());
            }
            if (this.zzba != null) {
                zzbxm.zzq(10, this.zzba);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zze */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        return this;
                    case 10:
                        this.zzaS = zzbxl.readString();
                        break;
                    case 16:
                        this.zzaT = Long.valueOf(zzbxl.zzaer());
                        break;
                    case 26:
                        this.stackTrace = zzbxl.readString();
                        break;
                    case 34:
                        this.zzaU = zzbxl.readString();
                        break;
                    case 42:
                        this.zzaV = zzbxl.readString();
                        break;
                    case 48:
                        this.zzaW = Long.valueOf(zzbxl.zzaer());
                        break;
                    case 56:
                        this.zzaX = Long.valueOf(zzbxl.zzaer());
                        break;
                    case 66:
                        this.zzaY = zzbxl.readString();
                        break;
                    case 72:
                        this.zzaZ = Long.valueOf(zzbxl.zzaer());
                        break;
                    case 82:
                        this.zzba = zzbxl.readString();
                        break;
                    default:
                        if (super.zza(zzbxl, zzaeo)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaS != null) {
                zzu += zzbxm.zzr(1, this.zzaS);
            }
            if (this.zzaT != null) {
                zzu += zzbxm.zzf(2, this.zzaT.longValue());
            }
            if (this.stackTrace != null) {
                zzu += zzbxm.zzr(3, this.stackTrace);
            }
            if (this.zzaU != null) {
                zzu += zzbxm.zzr(4, this.zzaU);
            }
            if (this.zzaV != null) {
                zzu += zzbxm.zzr(5, this.zzaV);
            }
            if (this.zzaW != null) {
                zzu += zzbxm.zzf(6, this.zzaW.longValue());
            }
            if (this.zzaX != null) {
                zzu += zzbxm.zzf(7, this.zzaX.longValue());
            }
            if (this.zzaY != null) {
                zzu += zzbxm.zzr(8, this.zzaY);
            }
            if (this.zzaZ != null) {
                zzu += zzbxm.zzf(9, this.zzaZ.longValue());
            }
            return this.zzba != null ? zzu + zzbxm.zzr(10, this.zzba) : zzu;
        }
    }
}
