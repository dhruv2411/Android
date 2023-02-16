package com.google.android.gms.internal;

import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import java.io.IOException;

public interface zzauu {

    public static final class zza extends zzbxn<zza> {
        private static volatile zza[] zzbwj;
        public Integer zzbwk;
        public zze[] zzbwl;
        public zzb[] zzbwm;

        public zza() {
            zzNk();
        }

        public static zza[] zzNj() {
            if (zzbwj == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbwj == null) {
                        zzbwj = new zza[0];
                    }
                }
            }
            return zzbwj;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.zzbwk == null) {
                if (zza.zzbwk != null) {
                    return false;
                }
            } else if (!this.zzbwk.equals(zza.zzbwk)) {
                return false;
            }
            if (zzbxr.equals((Object[]) this.zzbwl, (Object[]) zza.zzbwl) && zzbxr.equals((Object[]) this.zzbwm, (Object[]) zza.zzbwm)) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zza.zzcuI == null || zza.zzcuI.isEmpty() : this.zzcuI.equals(zza.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + (this.zzbwk == null ? 0 : this.zzbwk.hashCode())) * 31) + zzbxr.hashCode((Object[]) this.zzbwl)) * 31) + zzbxr.hashCode((Object[]) this.zzbwm));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzG */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 8) {
                    this.zzbwk = Integer.valueOf(zzbxl.zzaes());
                } else if (zzaeo == 18) {
                    int zzb = zzbxw.zzb(zzbxl, 18);
                    int length = this.zzbwl == null ? 0 : this.zzbwl.length;
                    zze[] zzeArr = new zze[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzbwl, 0, zzeArr, 0, length);
                    }
                    while (length < zzeArr.length - 1) {
                        zzeArr[length] = new zze();
                        zzbxl.zza(zzeArr[length]);
                        zzbxl.zzaeo();
                        length++;
                    }
                    zzeArr[length] = new zze();
                    zzbxl.zza(zzeArr[length]);
                    this.zzbwl = zzeArr;
                } else if (zzaeo == 26) {
                    int zzb2 = zzbxw.zzb(zzbxl, 26);
                    int length2 = this.zzbwm == null ? 0 : this.zzbwm.length;
                    zzb[] zzbArr = new zzb[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzbwm, 0, zzbArr, 0, length2);
                    }
                    while (length2 < zzbArr.length - 1) {
                        zzbArr[length2] = new zzb();
                        zzbxl.zza(zzbArr[length2]);
                        zzbxl.zzaeo();
                        length2++;
                    }
                    zzbArr[length2] = new zzb();
                    zzbxl.zza(zzbArr[length2]);
                    this.zzbwm = zzbArr;
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        public zza zzNk() {
            this.zzbwk = null;
            this.zzbwl = zze.zzNq();
            this.zzbwm = zzb.zzNl();
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzbwk != null) {
                zzbxm.zzJ(1, this.zzbwk.intValue());
            }
            if (this.zzbwl != null && this.zzbwl.length > 0) {
                for (zze zze : this.zzbwl) {
                    if (zze != null) {
                        zzbxm.zza(2, (zzbxt) zze);
                    }
                }
            }
            if (this.zzbwm != null && this.zzbwm.length > 0) {
                for (zzb zzb : this.zzbwm) {
                    if (zzb != null) {
                        zzbxm.zza(3, (zzbxt) zzb);
                    }
                }
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbwk != null) {
                zzu += zzbxm.zzL(1, this.zzbwk.intValue());
            }
            if (this.zzbwl != null && this.zzbwl.length > 0) {
                int i = zzu;
                for (zze zze : this.zzbwl) {
                    if (zze != null) {
                        i += zzbxm.zzc(2, (zzbxt) zze);
                    }
                }
                zzu = i;
            }
            if (this.zzbwm != null && this.zzbwm.length > 0) {
                for (zzb zzb : this.zzbwm) {
                    if (zzb != null) {
                        zzu += zzbxm.zzc(3, (zzbxt) zzb);
                    }
                }
            }
            return zzu;
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        private static volatile zzb[] zzbwn;
        public Integer zzbwo;
        public String zzbwp;
        public zzc[] zzbwq;
        public Boolean zzbwr;
        public zzd zzbws;

        public zzb() {
            zzNm();
        }

        public static zzb[] zzNl() {
            if (zzbwn == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbwn == null) {
                        zzbwn = new zzb[0];
                    }
                }
            }
            return zzbwn;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzbwo == null) {
                if (zzb.zzbwo != null) {
                    return false;
                }
            } else if (!this.zzbwo.equals(zzb.zzbwo)) {
                return false;
            }
            if (this.zzbwp == null) {
                if (zzb.zzbwp != null) {
                    return false;
                }
            } else if (!this.zzbwp.equals(zzb.zzbwp)) {
                return false;
            }
            if (!zzbxr.equals((Object[]) this.zzbwq, (Object[]) zzb.zzbwq)) {
                return false;
            }
            if (this.zzbwr == null) {
                if (zzb.zzbwr != null) {
                    return false;
                }
            } else if (!this.zzbwr.equals(zzb.zzbwr)) {
                return false;
            }
            if (this.zzbws == null) {
                if (zzb.zzbws != null) {
                    return false;
                }
            } else if (!this.zzbws.equals(zzb.zzbws)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzb.zzcuI == null || zzb.zzcuI.isEmpty() : this.zzcuI.equals(zzb.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + (this.zzbwo == null ? 0 : this.zzbwo.hashCode())) * 31) + (this.zzbwp == null ? 0 : this.zzbwp.hashCode())) * 31) + zzbxr.hashCode((Object[]) this.zzbwq)) * 31) + (this.zzbwr == null ? 0 : this.zzbwr.hashCode())) * 31) + (this.zzbws == null ? 0 : this.zzbws.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzH */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 8) {
                    this.zzbwo = Integer.valueOf(zzbxl.zzaes());
                } else if (zzaeo == 18) {
                    this.zzbwp = zzbxl.readString();
                } else if (zzaeo == 26) {
                    int zzb = zzbxw.zzb(zzbxl, 26);
                    int length = this.zzbwq == null ? 0 : this.zzbwq.length;
                    zzc[] zzcArr = new zzc[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzbwq, 0, zzcArr, 0, length);
                    }
                    while (length < zzcArr.length - 1) {
                        zzcArr[length] = new zzc();
                        zzbxl.zza(zzcArr[length]);
                        zzbxl.zzaeo();
                        length++;
                    }
                    zzcArr[length] = new zzc();
                    zzbxl.zza(zzcArr[length]);
                    this.zzbwq = zzcArr;
                } else if (zzaeo == 32) {
                    this.zzbwr = Boolean.valueOf(zzbxl.zzaeu());
                } else if (zzaeo == 42) {
                    if (this.zzbws == null) {
                        this.zzbws = new zzd();
                    }
                    zzbxl.zza(this.zzbws);
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        public zzb zzNm() {
            this.zzbwo = null;
            this.zzbwp = null;
            this.zzbwq = zzc.zzNn();
            this.zzbwr = null;
            this.zzbws = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzbwo != null) {
                zzbxm.zzJ(1, this.zzbwo.intValue());
            }
            if (this.zzbwp != null) {
                zzbxm.zzq(2, this.zzbwp);
            }
            if (this.zzbwq != null && this.zzbwq.length > 0) {
                for (zzc zzc : this.zzbwq) {
                    if (zzc != null) {
                        zzbxm.zza(3, (zzbxt) zzc);
                    }
                }
            }
            if (this.zzbwr != null) {
                zzbxm.zzg(4, this.zzbwr.booleanValue());
            }
            if (this.zzbws != null) {
                zzbxm.zza(5, (zzbxt) this.zzbws);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbwo != null) {
                zzu += zzbxm.zzL(1, this.zzbwo.intValue());
            }
            if (this.zzbwp != null) {
                zzu += zzbxm.zzr(2, this.zzbwp);
            }
            if (this.zzbwq != null && this.zzbwq.length > 0) {
                for (zzc zzc : this.zzbwq) {
                    if (zzc != null) {
                        zzu += zzbxm.zzc(3, (zzbxt) zzc);
                    }
                }
            }
            if (this.zzbwr != null) {
                zzu += zzbxm.zzh(4, this.zzbwr.booleanValue());
            }
            return this.zzbws != null ? zzu + zzbxm.zzc(5, (zzbxt) this.zzbws) : zzu;
        }
    }

    public static final class zzc extends zzbxn<zzc> {
        private static volatile zzc[] zzbwt;
        public zzf zzbwu;
        public zzd zzbwv;
        public Boolean zzbww;
        public String zzbwx;

        public zzc() {
            zzNo();
        }

        public static zzc[] zzNn() {
            if (zzbwt == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbwt == null) {
                        zzbwt = new zzc[0];
                    }
                }
            }
            return zzbwt;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.zzbwu == null) {
                if (zzc.zzbwu != null) {
                    return false;
                }
            } else if (!this.zzbwu.equals(zzc.zzbwu)) {
                return false;
            }
            if (this.zzbwv == null) {
                if (zzc.zzbwv != null) {
                    return false;
                }
            } else if (!this.zzbwv.equals(zzc.zzbwv)) {
                return false;
            }
            if (this.zzbww == null) {
                if (zzc.zzbww != null) {
                    return false;
                }
            } else if (!this.zzbww.equals(zzc.zzbww)) {
                return false;
            }
            if (this.zzbwx == null) {
                if (zzc.zzbwx != null) {
                    return false;
                }
            } else if (!this.zzbwx.equals(zzc.zzbwx)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzc.zzcuI == null || zzc.zzcuI.isEmpty() : this.zzcuI.equals(zzc.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + (this.zzbwu == null ? 0 : this.zzbwu.hashCode())) * 31) + (this.zzbwv == null ? 0 : this.zzbwv.hashCode())) * 31) + (this.zzbww == null ? 0 : this.zzbww.hashCode())) * 31) + (this.zzbwx == null ? 0 : this.zzbwx.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzI */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            zzbxt zzbxt;
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    if (this.zzbwu == null) {
                        this.zzbwu = new zzf();
                    }
                    zzbxt = this.zzbwu;
                } else if (zzaeo == 18) {
                    if (this.zzbwv == null) {
                        this.zzbwv = new zzd();
                    }
                    zzbxt = this.zzbwv;
                } else if (zzaeo == 24) {
                    this.zzbww = Boolean.valueOf(zzbxl.zzaeu());
                } else if (zzaeo == 34) {
                    this.zzbwx = zzbxl.readString();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
                zzbxl.zza(zzbxt);
            }
        }

        public zzc zzNo() {
            this.zzbwu = null;
            this.zzbwv = null;
            this.zzbww = null;
            this.zzbwx = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzbwu != null) {
                zzbxm.zza(1, (zzbxt) this.zzbwu);
            }
            if (this.zzbwv != null) {
                zzbxm.zza(2, (zzbxt) this.zzbwv);
            }
            if (this.zzbww != null) {
                zzbxm.zzg(3, this.zzbww.booleanValue());
            }
            if (this.zzbwx != null) {
                zzbxm.zzq(4, this.zzbwx);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbwu != null) {
                zzu += zzbxm.zzc(1, (zzbxt) this.zzbwu);
            }
            if (this.zzbwv != null) {
                zzu += zzbxm.zzc(2, (zzbxt) this.zzbwv);
            }
            if (this.zzbww != null) {
                zzu += zzbxm.zzh(3, this.zzbww.booleanValue());
            }
            return this.zzbwx != null ? zzu + zzbxm.zzr(4, this.zzbwx) : zzu;
        }
    }

    public static final class zzd extends zzbxn<zzd> {
        public String zzbwA;
        public String zzbwB;
        public String zzbwC;
        public Integer zzbwy;
        public Boolean zzbwz;

        public zzd() {
            zzNp();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (this.zzbwy == null) {
                if (zzd.zzbwy != null) {
                    return false;
                }
            } else if (!this.zzbwy.equals(zzd.zzbwy)) {
                return false;
            }
            if (this.zzbwz == null) {
                if (zzd.zzbwz != null) {
                    return false;
                }
            } else if (!this.zzbwz.equals(zzd.zzbwz)) {
                return false;
            }
            if (this.zzbwA == null) {
                if (zzd.zzbwA != null) {
                    return false;
                }
            } else if (!this.zzbwA.equals(zzd.zzbwA)) {
                return false;
            }
            if (this.zzbwB == null) {
                if (zzd.zzbwB != null) {
                    return false;
                }
            } else if (!this.zzbwB.equals(zzd.zzbwB)) {
                return false;
            }
            if (this.zzbwC == null) {
                if (zzd.zzbwC != null) {
                    return false;
                }
            } else if (!this.zzbwC.equals(zzd.zzbwC)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzd.zzcuI == null || zzd.zzcuI.isEmpty() : this.zzcuI.equals(zzd.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + (this.zzbwy == null ? 0 : this.zzbwy.intValue())) * 31) + (this.zzbwz == null ? 0 : this.zzbwz.hashCode())) * 31) + (this.zzbwA == null ? 0 : this.zzbwA.hashCode())) * 31) + (this.zzbwB == null ? 0 : this.zzbwB.hashCode())) * 31) + (this.zzbwC == null ? 0 : this.zzbwC.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzJ */
        public zzd zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo != 0) {
                    if (zzaeo == 8) {
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                this.zzbwy = Integer.valueOf(zzaes);
                                break;
                        }
                    } else if (zzaeo == 16) {
                        this.zzbwz = Boolean.valueOf(zzbxl.zzaeu());
                    } else if (zzaeo == 26) {
                        this.zzbwA = zzbxl.readString();
                    } else if (zzaeo == 34) {
                        this.zzbwB = zzbxl.readString();
                    } else if (zzaeo == 42) {
                        this.zzbwC = zzbxl.readString();
                    } else if (!super.zza(zzbxl, zzaeo)) {
                        return this;
                    }
                } else {
                    return this;
                }
            }
        }

        public zzd zzNp() {
            this.zzbwz = null;
            this.zzbwA = null;
            this.zzbwB = null;
            this.zzbwC = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzbwy != null) {
                zzbxm.zzJ(1, this.zzbwy.intValue());
            }
            if (this.zzbwz != null) {
                zzbxm.zzg(2, this.zzbwz.booleanValue());
            }
            if (this.zzbwA != null) {
                zzbxm.zzq(3, this.zzbwA);
            }
            if (this.zzbwB != null) {
                zzbxm.zzq(4, this.zzbwB);
            }
            if (this.zzbwC != null) {
                zzbxm.zzq(5, this.zzbwC);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbwy != null) {
                zzu += zzbxm.zzL(1, this.zzbwy.intValue());
            }
            if (this.zzbwz != null) {
                zzu += zzbxm.zzh(2, this.zzbwz.booleanValue());
            }
            if (this.zzbwA != null) {
                zzu += zzbxm.zzr(3, this.zzbwA);
            }
            if (this.zzbwB != null) {
                zzu += zzbxm.zzr(4, this.zzbwB);
            }
            return this.zzbwC != null ? zzu + zzbxm.zzr(5, this.zzbwC) : zzu;
        }
    }

    public static final class zze extends zzbxn<zze> {
        private static volatile zze[] zzbwD;
        public String zzbwE;
        public zzc zzbwF;
        public Integer zzbwo;

        public zze() {
            zzNr();
        }

        public static zze[] zzNq() {
            if (zzbwD == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbwD == null) {
                        zzbwD = new zze[0];
                    }
                }
            }
            return zzbwD;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.zzbwo == null) {
                if (zze.zzbwo != null) {
                    return false;
                }
            } else if (!this.zzbwo.equals(zze.zzbwo)) {
                return false;
            }
            if (this.zzbwE == null) {
                if (zze.zzbwE != null) {
                    return false;
                }
            } else if (!this.zzbwE.equals(zze.zzbwE)) {
                return false;
            }
            if (this.zzbwF == null) {
                if (zze.zzbwF != null) {
                    return false;
                }
            } else if (!this.zzbwF.equals(zze.zzbwF)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zze.zzcuI == null || zze.zzcuI.isEmpty() : this.zzcuI.equals(zze.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + (this.zzbwo == null ? 0 : this.zzbwo.hashCode())) * 31) + (this.zzbwE == null ? 0 : this.zzbwE.hashCode())) * 31) + (this.zzbwF == null ? 0 : this.zzbwF.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzK */
        public zze zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 8) {
                    this.zzbwo = Integer.valueOf(zzbxl.zzaes());
                } else if (zzaeo == 18) {
                    this.zzbwE = zzbxl.readString();
                } else if (zzaeo == 26) {
                    if (this.zzbwF == null) {
                        this.zzbwF = new zzc();
                    }
                    zzbxl.zza(this.zzbwF);
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        public zze zzNr() {
            this.zzbwo = null;
            this.zzbwE = null;
            this.zzbwF = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzbwo != null) {
                zzbxm.zzJ(1, this.zzbwo.intValue());
            }
            if (this.zzbwE != null) {
                zzbxm.zzq(2, this.zzbwE);
            }
            if (this.zzbwF != null) {
                zzbxm.zza(3, (zzbxt) this.zzbwF);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbwo != null) {
                zzu += zzbxm.zzL(1, this.zzbwo.intValue());
            }
            if (this.zzbwE != null) {
                zzu += zzbxm.zzr(2, this.zzbwE);
            }
            return this.zzbwF != null ? zzu + zzbxm.zzc(3, (zzbxt) this.zzbwF) : zzu;
        }
    }

    public static final class zzf extends zzbxn<zzf> {
        public Integer zzbwG;
        public String zzbwH;
        public Boolean zzbwI;
        public String[] zzbwJ;

        public zzf() {
            zzNs();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (this.zzbwG == null) {
                if (zzf.zzbwG != null) {
                    return false;
                }
            } else if (!this.zzbwG.equals(zzf.zzbwG)) {
                return false;
            }
            if (this.zzbwH == null) {
                if (zzf.zzbwH != null) {
                    return false;
                }
            } else if (!this.zzbwH.equals(zzf.zzbwH)) {
                return false;
            }
            if (this.zzbwI == null) {
                if (zzf.zzbwI != null) {
                    return false;
                }
            } else if (!this.zzbwI.equals(zzf.zzbwI)) {
                return false;
            }
            if (!zzbxr.equals((Object[]) this.zzbwJ, (Object[]) zzf.zzbwJ)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzf.zzcuI == null || zzf.zzcuI.isEmpty() : this.zzcuI.equals(zzf.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + (this.zzbwG == null ? 0 : this.zzbwG.intValue())) * 31) + (this.zzbwH == null ? 0 : this.zzbwH.hashCode())) * 31) + (this.zzbwI == null ? 0 : this.zzbwI.hashCode())) * 31) + zzbxr.hashCode((Object[]) this.zzbwJ));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzL */
        public zzf zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo != 0) {
                    if (zzaeo == 8) {
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                this.zzbwG = Integer.valueOf(zzaes);
                                break;
                        }
                    } else if (zzaeo == 18) {
                        this.zzbwH = zzbxl.readString();
                    } else if (zzaeo == 24) {
                        this.zzbwI = Boolean.valueOf(zzbxl.zzaeu());
                    } else if (zzaeo == 34) {
                        int zzb = zzbxw.zzb(zzbxl, 34);
                        int length = this.zzbwJ == null ? 0 : this.zzbwJ.length;
                        String[] strArr = new String[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbwJ, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = zzbxl.readString();
                            zzbxl.zzaeo();
                            length++;
                        }
                        strArr[length] = zzbxl.readString();
                        this.zzbwJ = strArr;
                    } else if (!super.zza(zzbxl, zzaeo)) {
                        return this;
                    }
                } else {
                    return this;
                }
            }
        }

        public zzf zzNs() {
            this.zzbwH = null;
            this.zzbwI = null;
            this.zzbwJ = zzbxw.zzcvb;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzbwG != null) {
                zzbxm.zzJ(1, this.zzbwG.intValue());
            }
            if (this.zzbwH != null) {
                zzbxm.zzq(2, this.zzbwH);
            }
            if (this.zzbwI != null) {
                zzbxm.zzg(3, this.zzbwI.booleanValue());
            }
            if (this.zzbwJ != null && this.zzbwJ.length > 0) {
                for (String str : this.zzbwJ) {
                    if (str != null) {
                        zzbxm.zzq(4, str);
                    }
                }
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbwG != null) {
                zzu += zzbxm.zzL(1, this.zzbwG.intValue());
            }
            if (this.zzbwH != null) {
                zzu += zzbxm.zzr(2, this.zzbwH);
            }
            if (this.zzbwI != null) {
                zzu += zzbxm.zzh(3, this.zzbwI.booleanValue());
            }
            if (this.zzbwJ == null || this.zzbwJ.length <= 0) {
                return zzu;
            }
            int i = 0;
            int i2 = 0;
            for (String str : this.zzbwJ) {
                if (str != null) {
                    i2++;
                    i += zzbxm.zzkb(str);
                }
            }
            return zzu + i + (1 * i2);
        }
    }
}
