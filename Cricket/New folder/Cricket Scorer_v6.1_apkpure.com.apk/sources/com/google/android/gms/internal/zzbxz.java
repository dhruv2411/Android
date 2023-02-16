package com.google.android.gms.internal;

import java.io.IOException;

public interface zzbxz {

    public static final class zza extends zzbxn<zza> {
        private static volatile zza[] zzcvO;
        public String zzcvP;

        public zza() {
            zzafj();
        }

        public static zza[] zzafi() {
            if (zzcvO == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzcvO == null) {
                        zzcvO = new zza[0];
                    }
                }
            }
            return zzcvO;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvP != null && !this.zzcvP.equals("")) {
                zzbxm.zzq(1, this.zzcvP);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaV */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    this.zzcvP = zzbxl.readString();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        public zza zzafj() {
            this.zzcvP = "";
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            return (this.zzcvP == null || this.zzcvP.equals("")) ? zzu : zzu + zzbxm.zzr(1, this.zzcvP);
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        public String zzcvP;
        public String zzcvQ;
        public long zzcvR;
        public String zzcvS;
        public int zzcvT;
        public int zzcvU;
        public String zzcvV;
        public String zzcvW;
        public String zzcvX;
        public String zzcvY;
        public String zzcvZ;
        public int zzcwa;
        public zza[] zzcwb;

        public zzb() {
            zzafk();
        }

        public static zzb zzak(byte[] bArr) throws zzbxs {
            return (zzb) zzbxt.zza(new zzb(), bArr);
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvP != null && !this.zzcvP.equals("")) {
                zzbxm.zzq(1, this.zzcvP);
            }
            if (this.zzcvQ != null && !this.zzcvQ.equals("")) {
                zzbxm.zzq(2, this.zzcvQ);
            }
            if (this.zzcvR != 0) {
                zzbxm.zzb(3, this.zzcvR);
            }
            if (this.zzcvS != null && !this.zzcvS.equals("")) {
                zzbxm.zzq(4, this.zzcvS);
            }
            if (this.zzcvT != 0) {
                zzbxm.zzJ(5, this.zzcvT);
            }
            if (this.zzcvU != 0) {
                zzbxm.zzJ(6, this.zzcvU);
            }
            if (this.zzcvV != null && !this.zzcvV.equals("")) {
                zzbxm.zzq(7, this.zzcvV);
            }
            if (this.zzcvW != null && !this.zzcvW.equals("")) {
                zzbxm.zzq(8, this.zzcvW);
            }
            if (this.zzcvX != null && !this.zzcvX.equals("")) {
                zzbxm.zzq(9, this.zzcvX);
            }
            if (this.zzcvY != null && !this.zzcvY.equals("")) {
                zzbxm.zzq(10, this.zzcvY);
            }
            if (this.zzcvZ != null && !this.zzcvZ.equals("")) {
                zzbxm.zzq(11, this.zzcvZ);
            }
            if (this.zzcwa != 0) {
                zzbxm.zzJ(12, this.zzcwa);
            }
            if (this.zzcwb != null && this.zzcwb.length > 0) {
                for (zza zza : this.zzcwb) {
                    if (zza != null) {
                        zzbxm.zza(13, (zzbxt) zza);
                    }
                }
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaW */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        return this;
                    case 10:
                        this.zzcvP = zzbxl.readString();
                        break;
                    case 18:
                        this.zzcvQ = zzbxl.readString();
                        break;
                    case 24:
                        this.zzcvR = zzbxl.zzaer();
                        break;
                    case 34:
                        this.zzcvS = zzbxl.readString();
                        break;
                    case 40:
                        this.zzcvT = zzbxl.zzaes();
                        break;
                    case 48:
                        this.zzcvU = zzbxl.zzaes();
                        break;
                    case 58:
                        this.zzcvV = zzbxl.readString();
                        break;
                    case 66:
                        this.zzcvW = zzbxl.readString();
                        break;
                    case 74:
                        this.zzcvX = zzbxl.readString();
                        break;
                    case 82:
                        this.zzcvY = zzbxl.readString();
                        break;
                    case 90:
                        this.zzcvZ = zzbxl.readString();
                        break;
                    case 96:
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 0:
                            case 1:
                            case 2:
                                this.zzcwa = zzaes;
                                break;
                        }
                    case 106:
                        int zzb = zzbxw.zzb(zzbxl, 106);
                        int length = this.zzcwb == null ? 0 : this.zzcwb.length;
                        zza[] zzaArr = new zza[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzcwb, 0, zzaArr, 0, length);
                        }
                        while (length < zzaArr.length - 1) {
                            zzaArr[length] = new zza();
                            zzbxl.zza(zzaArr[length]);
                            zzbxl.zzaeo();
                            length++;
                        }
                        zzaArr[length] = new zza();
                        zzbxl.zza(zzaArr[length]);
                        this.zzcwb = zzaArr;
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

        public zzb zzafk() {
            this.zzcvP = "";
            this.zzcvQ = "";
            this.zzcvR = 0;
            this.zzcvS = "";
            this.zzcvT = 0;
            this.zzcvU = 0;
            this.zzcvV = "";
            this.zzcvW = "";
            this.zzcvX = "";
            this.zzcvY = "";
            this.zzcvZ = "";
            this.zzcwa = 0;
            this.zzcwb = zza.zzafi();
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcvP != null && !this.zzcvP.equals("")) {
                zzu += zzbxm.zzr(1, this.zzcvP);
            }
            if (this.zzcvQ != null && !this.zzcvQ.equals("")) {
                zzu += zzbxm.zzr(2, this.zzcvQ);
            }
            if (this.zzcvR != 0) {
                zzu += zzbxm.zzf(3, this.zzcvR);
            }
            if (this.zzcvS != null && !this.zzcvS.equals("")) {
                zzu += zzbxm.zzr(4, this.zzcvS);
            }
            if (this.zzcvT != 0) {
                zzu += zzbxm.zzL(5, this.zzcvT);
            }
            if (this.zzcvU != 0) {
                zzu += zzbxm.zzL(6, this.zzcvU);
            }
            if (this.zzcvV != null && !this.zzcvV.equals("")) {
                zzu += zzbxm.zzr(7, this.zzcvV);
            }
            if (this.zzcvW != null && !this.zzcvW.equals("")) {
                zzu += zzbxm.zzr(8, this.zzcvW);
            }
            if (this.zzcvX != null && !this.zzcvX.equals("")) {
                zzu += zzbxm.zzr(9, this.zzcvX);
            }
            if (this.zzcvY != null && !this.zzcvY.equals("")) {
                zzu += zzbxm.zzr(10, this.zzcvY);
            }
            if (this.zzcvZ != null && !this.zzcvZ.equals("")) {
                zzu += zzbxm.zzr(11, this.zzcvZ);
            }
            if (this.zzcwa != 0) {
                zzu += zzbxm.zzL(12, this.zzcwa);
            }
            if (this.zzcwb != null && this.zzcwb.length > 0) {
                for (zza zza : this.zzcwb) {
                    if (zza != null) {
                        zzu += zzbxm.zzc(13, (zzbxt) zza);
                    }
                }
            }
            return zzu;
        }
    }
}
