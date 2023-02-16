package com.google.android.gms.internal;

import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import java.io.IOException;

public interface zzak {

    public static final class zza extends zzbxn<zza> {
        private static volatile zza[] zzlt;
        public String string;
        public int type;
        public boolean zzlA;
        public zza[] zzlB;
        public int[] zzlC;
        public boolean zzlD;
        public zza[] zzlu;
        public zza[] zzlv;
        public zza[] zzlw;
        public String zzlx;
        public String zzly;
        public long zzlz;

        public zza() {
            zzM();
        }

        public static zza[] zzL() {
            if (zzlt == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzlt == null) {
                        zzlt = new zza[0];
                    }
                }
            }
            return zzlt;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.type != zza.type) {
                return false;
            }
            if (this.string == null) {
                if (zza.string != null) {
                    return false;
                }
            } else if (!this.string.equals(zza.string)) {
                return false;
            }
            if (!zzbxr.equals((Object[]) this.zzlu, (Object[]) zza.zzlu) || !zzbxr.equals((Object[]) this.zzlv, (Object[]) zza.zzlv) || !zzbxr.equals((Object[]) this.zzlw, (Object[]) zza.zzlw)) {
                return false;
            }
            if (this.zzlx == null) {
                if (zza.zzlx != null) {
                    return false;
                }
            } else if (!this.zzlx.equals(zza.zzlx)) {
                return false;
            }
            if (this.zzly == null) {
                if (zza.zzly != null) {
                    return false;
                }
            } else if (!this.zzly.equals(zza.zzly)) {
                return false;
            }
            if (this.zzlz == zza.zzlz && this.zzlA == zza.zzlA && zzbxr.equals((Object[]) this.zzlB, (Object[]) zza.zzlB) && zzbxr.equals(this.zzlC, zza.zzlC) && this.zzlD == zza.zzlD) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zza.zzcuI == null || zza.zzcuI.isEmpty() : this.zzcuI.equals(zza.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int i2 = 1237;
            int hashCode = (((((((((((((((((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + this.type) * 31) + (this.string == null ? 0 : this.string.hashCode())) * 31) + zzbxr.hashCode((Object[]) this.zzlu)) * 31) + zzbxr.hashCode((Object[]) this.zzlv)) * 31) + zzbxr.hashCode((Object[]) this.zzlw)) * 31) + (this.zzlx == null ? 0 : this.zzlx.hashCode())) * 31) + (this.zzly == null ? 0 : this.zzly.hashCode())) * 31) + ((int) (this.zzlz ^ (this.zzlz >>> 32)))) * 31) + (this.zzlA ? 1231 : 1237)) * 31) + zzbxr.hashCode((Object[]) this.zzlB)) * 31) + zzbxr.hashCode(this.zzlC)) * 31;
            if (this.zzlD) {
                i2 = 1231;
            }
            int i3 = 31 * (hashCode + i2);
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return i3 + i;
        }

        public zza zzM() {
            this.type = 1;
            this.string = "";
            this.zzlu = zzL();
            this.zzlv = zzL();
            this.zzlw = zzL();
            this.zzlx = "";
            this.zzly = "";
            this.zzlz = 0;
            this.zzlA = false;
            this.zzlB = zzL();
            this.zzlC = zzbxw.zzcuW;
            this.zzlD = false;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            zzbxm.zzJ(1, this.type);
            if (this.string != null && !this.string.equals("")) {
                zzbxm.zzq(2, this.string);
            }
            if (this.zzlu != null && this.zzlu.length > 0) {
                for (zza zza : this.zzlu) {
                    if (zza != null) {
                        zzbxm.zza(3, (zzbxt) zza);
                    }
                }
            }
            if (this.zzlv != null && this.zzlv.length > 0) {
                for (zza zza2 : this.zzlv) {
                    if (zza2 != null) {
                        zzbxm.zza(4, (zzbxt) zza2);
                    }
                }
            }
            if (this.zzlw != null && this.zzlw.length > 0) {
                for (zza zza3 : this.zzlw) {
                    if (zza3 != null) {
                        zzbxm.zza(5, (zzbxt) zza3);
                    }
                }
            }
            if (this.zzlx != null && !this.zzlx.equals("")) {
                zzbxm.zzq(6, this.zzlx);
            }
            if (this.zzly != null && !this.zzly.equals("")) {
                zzbxm.zzq(7, this.zzly);
            }
            if (this.zzlz != 0) {
                zzbxm.zzb(8, this.zzlz);
            }
            if (this.zzlD) {
                zzbxm.zzg(9, this.zzlD);
            }
            if (this.zzlC != null && this.zzlC.length > 0) {
                for (int zzJ : this.zzlC) {
                    zzbxm.zzJ(10, zzJ);
                }
            }
            if (this.zzlB != null && this.zzlB.length > 0) {
                for (zza zza4 : this.zzlB) {
                    if (zza4 != null) {
                        zzbxm.zza(11, (zzbxt) zza4);
                    }
                }
            }
            if (this.zzlA) {
                zzbxm.zzg(12, this.zzlA);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu() + zzbxm.zzL(1, this.type);
            if (this.string != null && !this.string.equals("")) {
                zzu += zzbxm.zzr(2, this.string);
            }
            if (this.zzlu != null && this.zzlu.length > 0) {
                int i = zzu;
                for (zza zza : this.zzlu) {
                    if (zza != null) {
                        i += zzbxm.zzc(3, (zzbxt) zza);
                    }
                }
                zzu = i;
            }
            if (this.zzlv != null && this.zzlv.length > 0) {
                int i2 = zzu;
                for (zza zza2 : this.zzlv) {
                    if (zza2 != null) {
                        i2 += zzbxm.zzc(4, (zzbxt) zza2);
                    }
                }
                zzu = i2;
            }
            if (this.zzlw != null && this.zzlw.length > 0) {
                int i3 = zzu;
                for (zza zza3 : this.zzlw) {
                    if (zza3 != null) {
                        i3 += zzbxm.zzc(5, (zzbxt) zza3);
                    }
                }
                zzu = i3;
            }
            if (this.zzlx != null && !this.zzlx.equals("")) {
                zzu += zzbxm.zzr(6, this.zzlx);
            }
            if (this.zzly != null && !this.zzly.equals("")) {
                zzu += zzbxm.zzr(7, this.zzly);
            }
            if (this.zzlz != 0) {
                zzu += zzbxm.zzf(8, this.zzlz);
            }
            if (this.zzlD) {
                zzu += zzbxm.zzh(9, this.zzlD);
            }
            if (this.zzlC != null && this.zzlC.length > 0) {
                int i4 = 0;
                for (int zzrg : this.zzlC) {
                    i4 += zzbxm.zzrg(zzrg);
                }
                zzu = zzu + i4 + (1 * this.zzlC.length);
            }
            if (this.zzlB != null && this.zzlB.length > 0) {
                for (zza zza4 : this.zzlB) {
                    if (zza4 != null) {
                        zzu += zzbxm.zzc(11, (zzbxt) zza4);
                    }
                }
            }
            return this.zzlA ? zzu + zzbxm.zzh(12, this.zzlA) : zzu;
        }

        /* renamed from: zzx */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        return this;
                    case 8:
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                this.type = zzaes;
                                break;
                        }
                    case 18:
                        this.string = zzbxl.readString();
                        break;
                    case 26:
                        int zzb = zzbxw.zzb(zzbxl, 26);
                        int length = this.zzlu == null ? 0 : this.zzlu.length;
                        zza[] zzaArr = new zza[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzlu, 0, zzaArr, 0, length);
                        }
                        while (length < zzaArr.length - 1) {
                            zzaArr[length] = new zza();
                            zzbxl.zza(zzaArr[length]);
                            zzbxl.zzaeo();
                            length++;
                        }
                        zzaArr[length] = new zza();
                        zzbxl.zza(zzaArr[length]);
                        this.zzlu = zzaArr;
                        break;
                    case 34:
                        int zzb2 = zzbxw.zzb(zzbxl, 34);
                        int length2 = this.zzlv == null ? 0 : this.zzlv.length;
                        zza[] zzaArr2 = new zza[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzlv, 0, zzaArr2, 0, length2);
                        }
                        while (length2 < zzaArr2.length - 1) {
                            zzaArr2[length2] = new zza();
                            zzbxl.zza(zzaArr2[length2]);
                            zzbxl.zzaeo();
                            length2++;
                        }
                        zzaArr2[length2] = new zza();
                        zzbxl.zza(zzaArr2[length2]);
                        this.zzlv = zzaArr2;
                        break;
                    case 42:
                        int zzb3 = zzbxw.zzb(zzbxl, 42);
                        int length3 = this.zzlw == null ? 0 : this.zzlw.length;
                        zza[] zzaArr3 = new zza[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzlw, 0, zzaArr3, 0, length3);
                        }
                        while (length3 < zzaArr3.length - 1) {
                            zzaArr3[length3] = new zza();
                            zzbxl.zza(zzaArr3[length3]);
                            zzbxl.zzaeo();
                            length3++;
                        }
                        zzaArr3[length3] = new zza();
                        zzbxl.zza(zzaArr3[length3]);
                        this.zzlw = zzaArr3;
                        break;
                    case 50:
                        this.zzlx = zzbxl.readString();
                        break;
                    case 58:
                        this.zzly = zzbxl.readString();
                        break;
                    case 64:
                        this.zzlz = zzbxl.zzaer();
                        break;
                    case 72:
                        this.zzlD = zzbxl.zzaeu();
                        break;
                    case 80:
                        int zzb4 = zzbxw.zzb(zzbxl, 80);
                        int[] iArr = new int[zzb4];
                        int i = 0;
                        for (int i2 = 0; i2 < zzb4; i2++) {
                            if (i2 != 0) {
                                zzbxl.zzaeo();
                            }
                            int zzaes2 = zzbxl.zzaes();
                            switch (zzaes2) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                    iArr[i] = zzaes2;
                                    i++;
                                    break;
                            }
                        }
                        if (i != 0) {
                            int length4 = this.zzlC == null ? 0 : this.zzlC.length;
                            if (length4 != 0 || i != iArr.length) {
                                int[] iArr2 = new int[(length4 + i)];
                                if (length4 != 0) {
                                    System.arraycopy(this.zzlC, 0, iArr2, 0, length4);
                                }
                                System.arraycopy(iArr, 0, iArr2, length4, i);
                                this.zzlC = iArr2;
                                break;
                            } else {
                                this.zzlC = iArr;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 82:
                        int zzra = zzbxl.zzra(zzbxl.zzaex());
                        int position = zzbxl.getPosition();
                        int i3 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            switch (zzbxl.zzaes()) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                    i3++;
                                    break;
                            }
                        }
                        if (i3 != 0) {
                            zzbxl.zzrc(position);
                            int length5 = this.zzlC == null ? 0 : this.zzlC.length;
                            int[] iArr3 = new int[(i3 + length5)];
                            if (length5 != 0) {
                                System.arraycopy(this.zzlC, 0, iArr3, 0, length5);
                            }
                            while (zzbxl.zzaeC() > 0) {
                                int zzaes3 = zzbxl.zzaes();
                                switch (zzaes3) {
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                    case 5:
                                    case 6:
                                    case 7:
                                    case 8:
                                    case 9:
                                    case 10:
                                    case 11:
                                    case 12:
                                    case 13:
                                    case 14:
                                    case 15:
                                    case 16:
                                    case 17:
                                        iArr3[length5] = zzaes3;
                                        length5++;
                                        break;
                                }
                            }
                            this.zzlC = iArr3;
                        }
                        zzbxl.zzrb(zzra);
                        break;
                    case 90:
                        int zzb5 = zzbxw.zzb(zzbxl, 90);
                        int length6 = this.zzlB == null ? 0 : this.zzlB.length;
                        zza[] zzaArr4 = new zza[(zzb5 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzlB, 0, zzaArr4, 0, length6);
                        }
                        while (length6 < zzaArr4.length - 1) {
                            zzaArr4[length6] = new zza();
                            zzbxl.zza(zzaArr4[length6]);
                            zzbxl.zzaeo();
                            length6++;
                        }
                        zzaArr4[length6] = new zza();
                        zzbxl.zza(zzaArr4[length6]);
                        this.zzlB = zzaArr4;
                        break;
                    case 96:
                        this.zzlA = zzbxl.zzaeu();
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
    }
}
