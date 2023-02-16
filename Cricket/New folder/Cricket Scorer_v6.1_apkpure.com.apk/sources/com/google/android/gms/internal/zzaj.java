package com.google.android.gms.internal;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.internal.zzak;
import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import java.io.IOException;

public interface zzaj {

    public static final class zza extends zzbxn<zza> {
        public int level;
        public int zzkn;
        public int zzko;

        public zza() {
            zzw();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.level == zza.level && this.zzkn == zza.zzkn && this.zzko == zza.zzko) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zza.zzcuI == null || zza.zzcuI.isEmpty() : this.zzcuI.equals(zza.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            return (31 * (((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + this.level) * 31) + this.zzkn) * 31) + this.zzko)) + ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode());
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.level != 1) {
                zzbxm.zzJ(1, this.level);
            }
            if (this.zzkn != 0) {
                zzbxm.zzJ(2, this.zzkn);
            }
            if (this.zzko != 0) {
                zzbxm.zzJ(3, this.zzko);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzn */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo != 0) {
                    if (zzaeo == 8) {
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 1:
                            case 2:
                            case 3:
                                this.level = zzaes;
                                break;
                        }
                    } else if (zzaeo == 16) {
                        this.zzkn = zzbxl.zzaes();
                    } else if (zzaeo == 24) {
                        this.zzko = zzbxl.zzaes();
                    } else if (!super.zza(zzbxl, zzaeo)) {
                        return this;
                    }
                } else {
                    return this;
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.level != 1) {
                zzu += zzbxm.zzL(1, this.level);
            }
            if (this.zzkn != 0) {
                zzu += zzbxm.zzL(2, this.zzkn);
            }
            return this.zzko != 0 ? zzu + zzbxm.zzL(3, this.zzko) : zzu;
        }

        public zza zzw() {
            this.level = 1;
            this.zzkn = 0;
            this.zzko = 0;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        private static volatile zzb[] zzkp;
        public int name;
        public int[] zzkq;
        public int zzkr;
        public boolean zzks;
        public boolean zzkt;

        public zzb() {
            zzy();
        }

        public static zzb[] zzx() {
            if (zzkp == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzkp == null) {
                        zzkp = new zzb[0];
                    }
                }
            }
            return zzkp;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (zzbxr.equals(this.zzkq, zzb.zzkq) && this.zzkr == zzb.zzkr && this.name == zzb.name && this.zzks == zzb.zzks && this.zzkt == zzb.zzkt) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzb.zzcuI == null || zzb.zzcuI.isEmpty() : this.zzcuI.equals(zzb.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            int i = 1237;
            int hashCode = (((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + zzbxr.hashCode(this.zzkq)) * 31) + this.zzkr) * 31) + this.name) * 31) + (this.zzks ? 1231 : 1237)) * 31;
            if (this.zzkt) {
                i = 1231;
            }
            return (31 * (hashCode + i)) + ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode());
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzkt) {
                zzbxm.zzg(1, this.zzkt);
            }
            zzbxm.zzJ(2, this.zzkr);
            if (this.zzkq != null && this.zzkq.length > 0) {
                for (int zzJ : this.zzkq) {
                    zzbxm.zzJ(3, zzJ);
                }
            }
            if (this.name != 0) {
                zzbxm.zzJ(4, this.name);
            }
            if (this.zzks) {
                zzbxm.zzg(6, this.zzks);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzo */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 8) {
                    this.zzkt = zzbxl.zzaeu();
                } else if (zzaeo == 16) {
                    this.zzkr = zzbxl.zzaes();
                } else if (zzaeo == 24) {
                    int zzb = zzbxw.zzb(zzbxl, 24);
                    int length = this.zzkq == null ? 0 : this.zzkq.length;
                    int[] iArr = new int[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzkq, 0, iArr, 0, length);
                    }
                    while (length < iArr.length - 1) {
                        iArr[length] = zzbxl.zzaes();
                        zzbxl.zzaeo();
                        length++;
                    }
                    iArr[length] = zzbxl.zzaes();
                    this.zzkq = iArr;
                } else if (zzaeo == 26) {
                    int zzra = zzbxl.zzra(zzbxl.zzaex());
                    int position = zzbxl.getPosition();
                    int i = 0;
                    while (zzbxl.zzaeC() > 0) {
                        zzbxl.zzaes();
                        i++;
                    }
                    zzbxl.zzrc(position);
                    int length2 = this.zzkq == null ? 0 : this.zzkq.length;
                    int[] iArr2 = new int[(i + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzkq, 0, iArr2, 0, length2);
                    }
                    while (length2 < iArr2.length) {
                        iArr2[length2] = zzbxl.zzaes();
                        length2++;
                    }
                    this.zzkq = iArr2;
                    zzbxl.zzrb(zzra);
                } else if (zzaeo == 32) {
                    this.name = zzbxl.zzaes();
                } else if (zzaeo == 48) {
                    this.zzks = zzbxl.zzaeu();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzkt) {
                zzu += zzbxm.zzh(1, this.zzkt);
            }
            int zzL = zzu + zzbxm.zzL(2, this.zzkr);
            if (this.zzkq != null && this.zzkq.length > 0) {
                int i = 0;
                for (int zzrg : this.zzkq) {
                    i += zzbxm.zzrg(zzrg);
                }
                zzL = zzL + i + (1 * this.zzkq.length);
            }
            if (this.name != 0) {
                zzL += zzbxm.zzL(4, this.name);
            }
            return this.zzks ? zzL + zzbxm.zzh(6, this.zzks) : zzL;
        }

        public zzb zzy() {
            this.zzkq = zzbxw.zzcuW;
            this.zzkr = 0;
            this.name = 0;
            this.zzks = false;
            this.zzkt = false;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }
    }

    public static final class zzc extends zzbxn<zzc> {
        private static volatile zzc[] zzku;
        public String zzaB;
        public long zzkv;
        public long zzkw;
        public boolean zzkx;
        public long zzky;

        public zzc() {
            zzA();
        }

        public static zzc[] zzz() {
            if (zzku == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzku == null) {
                        zzku = new zzc[0];
                    }
                }
            }
            return zzku;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.zzaB == null) {
                if (zzc.zzaB != null) {
                    return false;
                }
            } else if (!this.zzaB.equals(zzc.zzaB)) {
                return false;
            }
            if (this.zzkv == zzc.zzkv && this.zzkw == zzc.zzkw && this.zzkx == zzc.zzkx && this.zzky == zzc.zzky) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzc.zzcuI == null || zzc.zzcuI.isEmpty() : this.zzcuI.equals(zzc.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + (this.zzaB == null ? 0 : this.zzaB.hashCode())) * 31) + ((int) (this.zzkv ^ (this.zzkv >>> 32)))) * 31) + ((int) (this.zzkw ^ (this.zzkw >>> 32)))) * 31) + (this.zzkx ? 1231 : 1237)) * 31) + ((int) (this.zzky ^ (this.zzky >>> 32))));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zzc zzA() {
            this.zzaB = "";
            this.zzkv = 0;
            this.zzkw = 2147483647L;
            this.zzkx = false;
            this.zzky = 0;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaB != null && !this.zzaB.equals("")) {
                zzbxm.zzq(1, this.zzaB);
            }
            if (this.zzkv != 0) {
                zzbxm.zzb(2, this.zzkv);
            }
            if (this.zzkw != 2147483647L) {
                zzbxm.zzb(3, this.zzkw);
            }
            if (this.zzkx) {
                zzbxm.zzg(4, this.zzkx);
            }
            if (this.zzky != 0) {
                zzbxm.zzb(5, this.zzky);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzp */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    this.zzaB = zzbxl.readString();
                } else if (zzaeo == 16) {
                    this.zzkv = zzbxl.zzaer();
                } else if (zzaeo == 24) {
                    this.zzkw = zzbxl.zzaer();
                } else if (zzaeo == 32) {
                    this.zzkx = zzbxl.zzaeu();
                } else if (zzaeo == 40) {
                    this.zzky = zzbxl.zzaer();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaB != null && !this.zzaB.equals("")) {
                zzu += zzbxm.zzr(1, this.zzaB);
            }
            if (this.zzkv != 0) {
                zzu += zzbxm.zzf(2, this.zzkv);
            }
            if (this.zzkw != 2147483647L) {
                zzu += zzbxm.zzf(3, this.zzkw);
            }
            if (this.zzkx) {
                zzu += zzbxm.zzh(4, this.zzkx);
            }
            return this.zzky != 0 ? zzu + zzbxm.zzf(5, this.zzky) : zzu;
        }
    }

    public static final class zzd extends zzbxn<zzd> {
        public zzak.zza[] zzkA;
        public zzc[] zzkB;
        public zzak.zza[] zzkz;

        public zzd() {
            zzB();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (zzbxr.equals((Object[]) this.zzkz, (Object[]) zzd.zzkz) && zzbxr.equals((Object[]) this.zzkA, (Object[]) zzd.zzkA) && zzbxr.equals((Object[]) this.zzkB, (Object[]) zzd.zzkB)) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzd.zzcuI == null || zzd.zzcuI.isEmpty() : this.zzcuI.equals(zzd.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            return (31 * (((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + zzbxr.hashCode((Object[]) this.zzkz)) * 31) + zzbxr.hashCode((Object[]) this.zzkA)) * 31) + zzbxr.hashCode((Object[]) this.zzkB))) + ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode());
        }

        public zzd zzB() {
            this.zzkz = zzak.zza.zzL();
            this.zzkA = zzak.zza.zzL();
            this.zzkB = zzc.zzz();
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzkz != null && this.zzkz.length > 0) {
                for (zzak.zza zza : this.zzkz) {
                    if (zza != null) {
                        zzbxm.zza(1, (zzbxt) zza);
                    }
                }
            }
            if (this.zzkA != null && this.zzkA.length > 0) {
                for (zzak.zza zza2 : this.zzkA) {
                    if (zza2 != null) {
                        zzbxm.zza(2, (zzbxt) zza2);
                    }
                }
            }
            if (this.zzkB != null && this.zzkB.length > 0) {
                for (zzc zzc : this.zzkB) {
                    if (zzc != null) {
                        zzbxm.zza(3, (zzbxt) zzc);
                    }
                }
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzq */
        public zzd zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    int zzb = zzbxw.zzb(zzbxl, 10);
                    int length = this.zzkz == null ? 0 : this.zzkz.length;
                    zzak.zza[] zzaArr = new zzak.zza[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzkz, 0, zzaArr, 0, length);
                    }
                    while (length < zzaArr.length - 1) {
                        zzaArr[length] = new zzak.zza();
                        zzbxl.zza(zzaArr[length]);
                        zzbxl.zzaeo();
                        length++;
                    }
                    zzaArr[length] = new zzak.zza();
                    zzbxl.zza(zzaArr[length]);
                    this.zzkz = zzaArr;
                } else if (zzaeo == 18) {
                    int zzb2 = zzbxw.zzb(zzbxl, 18);
                    int length2 = this.zzkA == null ? 0 : this.zzkA.length;
                    zzak.zza[] zzaArr2 = new zzak.zza[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzkA, 0, zzaArr2, 0, length2);
                    }
                    while (length2 < zzaArr2.length - 1) {
                        zzaArr2[length2] = new zzak.zza();
                        zzbxl.zza(zzaArr2[length2]);
                        zzbxl.zzaeo();
                        length2++;
                    }
                    zzaArr2[length2] = new zzak.zza();
                    zzbxl.zza(zzaArr2[length2]);
                    this.zzkA = zzaArr2;
                } else if (zzaeo == 26) {
                    int zzb3 = zzbxw.zzb(zzbxl, 26);
                    int length3 = this.zzkB == null ? 0 : this.zzkB.length;
                    zzc[] zzcArr = new zzc[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzkB, 0, zzcArr, 0, length3);
                    }
                    while (length3 < zzcArr.length - 1) {
                        zzcArr[length3] = new zzc();
                        zzbxl.zza(zzcArr[length3]);
                        zzbxl.zzaeo();
                        length3++;
                    }
                    zzcArr[length3] = new zzc();
                    zzbxl.zza(zzcArr[length3]);
                    this.zzkB = zzcArr;
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzkz != null && this.zzkz.length > 0) {
                int i = zzu;
                for (zzak.zza zza : this.zzkz) {
                    if (zza != null) {
                        i += zzbxm.zzc(1, (zzbxt) zza);
                    }
                }
                zzu = i;
            }
            if (this.zzkA != null && this.zzkA.length > 0) {
                int i2 = zzu;
                for (zzak.zza zza2 : this.zzkA) {
                    if (zza2 != null) {
                        i2 += zzbxm.zzc(2, (zzbxt) zza2);
                    }
                }
                zzu = i2;
            }
            if (this.zzkB != null && this.zzkB.length > 0) {
                for (zzc zzc : this.zzkB) {
                    if (zzc != null) {
                        zzu += zzbxm.zzc(3, (zzbxt) zzc);
                    }
                }
            }
            return zzu;
        }
    }

    public static final class zze extends zzbxn<zze> {
        private static volatile zze[] zzkC;
        public int key;
        public int value;

        public zze() {
            zzD();
        }

        public static zze[] zzC() {
            if (zzkC == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzkC == null) {
                        zzkC = new zze[0];
                    }
                }
            }
            return zzkC;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.key == zze.key && this.value == zze.value) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zze.zzcuI == null || zze.zzcuI.isEmpty() : this.zzcuI.equals(zze.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            return (31 * (((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + this.key) * 31) + this.value)) + ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode());
        }

        public zze zzD() {
            this.key = 0;
            this.value = 0;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            zzbxm.zzJ(1, this.key);
            zzbxm.zzJ(2, this.value);
            super.zza(zzbxm);
        }

        /* renamed from: zzr */
        public zze zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 8) {
                    this.key = zzbxl.zzaes();
                } else if (zzaeo == 16) {
                    this.value = zzbxl.zzaes();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            return super.zzu() + zzbxm.zzL(1, this.key) + zzbxm.zzL(2, this.value);
        }
    }

    public static final class zzf extends zzbxn<zzf> {
        public String version;
        public String[] zzkD;
        public String[] zzkE;
        public zzak.zza[] zzkF;
        public zze[] zzkG;
        public zzb[] zzkH;
        public zzb[] zzkI;
        public zzb[] zzkJ;
        public zzg[] zzkK;
        public String zzkL;
        public String zzkM;
        public String zzkN;
        public zza zzkO;
        public float zzkP;
        public boolean zzkQ;
        public String[] zzkR;
        public int zzkS;

        public zzf() {
            zzE();
        }

        public static zzf zzf(byte[] bArr) throws zzbxs {
            return (zzf) zzbxt.zza(new zzf(), bArr);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (!zzbxr.equals((Object[]) this.zzkD, (Object[]) zzf.zzkD) || !zzbxr.equals((Object[]) this.zzkE, (Object[]) zzf.zzkE) || !zzbxr.equals((Object[]) this.zzkF, (Object[]) zzf.zzkF) || !zzbxr.equals((Object[]) this.zzkG, (Object[]) zzf.zzkG) || !zzbxr.equals((Object[]) this.zzkH, (Object[]) zzf.zzkH) || !zzbxr.equals((Object[]) this.zzkI, (Object[]) zzf.zzkI) || !zzbxr.equals((Object[]) this.zzkJ, (Object[]) zzf.zzkJ) || !zzbxr.equals((Object[]) this.zzkK, (Object[]) zzf.zzkK)) {
                return false;
            }
            if (this.zzkL == null) {
                if (zzf.zzkL != null) {
                    return false;
                }
            } else if (!this.zzkL.equals(zzf.zzkL)) {
                return false;
            }
            if (this.zzkM == null) {
                if (zzf.zzkM != null) {
                    return false;
                }
            } else if (!this.zzkM.equals(zzf.zzkM)) {
                return false;
            }
            if (this.zzkN == null) {
                if (zzf.zzkN != null) {
                    return false;
                }
            } else if (!this.zzkN.equals(zzf.zzkN)) {
                return false;
            }
            if (this.version == null) {
                if (zzf.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzf.version)) {
                return false;
            }
            if (this.zzkO == null) {
                if (zzf.zzkO != null) {
                    return false;
                }
            } else if (!this.zzkO.equals(zzf.zzkO)) {
                return false;
            }
            if (Float.floatToIntBits(this.zzkP) == Float.floatToIntBits(zzf.zzkP) && this.zzkQ == zzf.zzkQ && zzbxr.equals((Object[]) this.zzkR, (Object[]) zzf.zzkR) && this.zzkS == zzf.zzkS) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzf.zzcuI == null || zzf.zzcuI.isEmpty() : this.zzcuI.equals(zzf.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((((((((((((((((((((((((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + zzbxr.hashCode((Object[]) this.zzkD)) * 31) + zzbxr.hashCode((Object[]) this.zzkE)) * 31) + zzbxr.hashCode((Object[]) this.zzkF)) * 31) + zzbxr.hashCode((Object[]) this.zzkG)) * 31) + zzbxr.hashCode((Object[]) this.zzkH)) * 31) + zzbxr.hashCode((Object[]) this.zzkI)) * 31) + zzbxr.hashCode((Object[]) this.zzkJ)) * 31) + zzbxr.hashCode((Object[]) this.zzkK)) * 31) + (this.zzkL == null ? 0 : this.zzkL.hashCode())) * 31) + (this.zzkM == null ? 0 : this.zzkM.hashCode())) * 31) + (this.zzkN == null ? 0 : this.zzkN.hashCode())) * 31) + (this.version == null ? 0 : this.version.hashCode())) * 31) + (this.zzkO == null ? 0 : this.zzkO.hashCode())) * 31) + Float.floatToIntBits(this.zzkP)) * 31) + (this.zzkQ ? 1231 : 1237)) * 31) + zzbxr.hashCode((Object[]) this.zzkR)) * 31) + this.zzkS);
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zzf zzE() {
            this.zzkD = zzbxw.zzcvb;
            this.zzkE = zzbxw.zzcvb;
            this.zzkF = zzak.zza.zzL();
            this.zzkG = zze.zzC();
            this.zzkH = zzb.zzx();
            this.zzkI = zzb.zzx();
            this.zzkJ = zzb.zzx();
            this.zzkK = zzg.zzF();
            this.zzkL = "";
            this.zzkM = "";
            this.zzkN = "0";
            this.version = "";
            this.zzkO = null;
            this.zzkP = 0.0f;
            this.zzkQ = false;
            this.zzkR = zzbxw.zzcvb;
            this.zzkS = 0;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzkE != null && this.zzkE.length > 0) {
                for (String str : this.zzkE) {
                    if (str != null) {
                        zzbxm.zzq(1, str);
                    }
                }
            }
            if (this.zzkF != null && this.zzkF.length > 0) {
                for (zzak.zza zza : this.zzkF) {
                    if (zza != null) {
                        zzbxm.zza(2, (zzbxt) zza);
                    }
                }
            }
            if (this.zzkG != null && this.zzkG.length > 0) {
                for (zze zze : this.zzkG) {
                    if (zze != null) {
                        zzbxm.zza(3, (zzbxt) zze);
                    }
                }
            }
            if (this.zzkH != null && this.zzkH.length > 0) {
                for (zzb zzb : this.zzkH) {
                    if (zzb != null) {
                        zzbxm.zza(4, (zzbxt) zzb);
                    }
                }
            }
            if (this.zzkI != null && this.zzkI.length > 0) {
                for (zzb zzb2 : this.zzkI) {
                    if (zzb2 != null) {
                        zzbxm.zza(5, (zzbxt) zzb2);
                    }
                }
            }
            if (this.zzkJ != null && this.zzkJ.length > 0) {
                for (zzb zzb3 : this.zzkJ) {
                    if (zzb3 != null) {
                        zzbxm.zza(6, (zzbxt) zzb3);
                    }
                }
            }
            if (this.zzkK != null && this.zzkK.length > 0) {
                for (zzg zzg : this.zzkK) {
                    if (zzg != null) {
                        zzbxm.zza(7, (zzbxt) zzg);
                    }
                }
            }
            if (this.zzkL != null && !this.zzkL.equals("")) {
                zzbxm.zzq(9, this.zzkL);
            }
            if (this.zzkM != null && !this.zzkM.equals("")) {
                zzbxm.zzq(10, this.zzkM);
            }
            if (this.zzkN != null && !this.zzkN.equals("0")) {
                zzbxm.zzq(12, this.zzkN);
            }
            if (this.version != null && !this.version.equals("")) {
                zzbxm.zzq(13, this.version);
            }
            if (this.zzkO != null) {
                zzbxm.zza(14, (zzbxt) this.zzkO);
            }
            if (Float.floatToIntBits(this.zzkP) != Float.floatToIntBits(0.0f)) {
                zzbxm.zzc(15, this.zzkP);
            }
            if (this.zzkR != null && this.zzkR.length > 0) {
                for (String str2 : this.zzkR) {
                    if (str2 != null) {
                        zzbxm.zzq(16, str2);
                    }
                }
            }
            if (this.zzkS != 0) {
                zzbxm.zzJ(17, this.zzkS);
            }
            if (this.zzkQ) {
                zzbxm.zzg(18, this.zzkQ);
            }
            if (this.zzkD != null && this.zzkD.length > 0) {
                for (String str3 : this.zzkD) {
                    if (str3 != null) {
                        zzbxm.zzq(19, str3);
                    }
                }
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzs */
        public zzf zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        return this;
                    case 10:
                        int zzb = zzbxw.zzb(zzbxl, 10);
                        int length = this.zzkE == null ? 0 : this.zzkE.length;
                        String[] strArr = new String[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzkE, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = zzbxl.readString();
                            zzbxl.zzaeo();
                            length++;
                        }
                        strArr[length] = zzbxl.readString();
                        this.zzkE = strArr;
                        break;
                    case 18:
                        int zzb2 = zzbxw.zzb(zzbxl, 18);
                        int length2 = this.zzkF == null ? 0 : this.zzkF.length;
                        zzak.zza[] zzaArr = new zzak.zza[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzkF, 0, zzaArr, 0, length2);
                        }
                        while (length2 < zzaArr.length - 1) {
                            zzaArr[length2] = new zzak.zza();
                            zzbxl.zza(zzaArr[length2]);
                            zzbxl.zzaeo();
                            length2++;
                        }
                        zzaArr[length2] = new zzak.zza();
                        zzbxl.zza(zzaArr[length2]);
                        this.zzkF = zzaArr;
                        break;
                    case 26:
                        int zzb3 = zzbxw.zzb(zzbxl, 26);
                        int length3 = this.zzkG == null ? 0 : this.zzkG.length;
                        zze[] zzeArr = new zze[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzkG, 0, zzeArr, 0, length3);
                        }
                        while (length3 < zzeArr.length - 1) {
                            zzeArr[length3] = new zze();
                            zzbxl.zza(zzeArr[length3]);
                            zzbxl.zzaeo();
                            length3++;
                        }
                        zzeArr[length3] = new zze();
                        zzbxl.zza(zzeArr[length3]);
                        this.zzkG = zzeArr;
                        break;
                    case 34:
                        int zzb4 = zzbxw.zzb(zzbxl, 34);
                        int length4 = this.zzkH == null ? 0 : this.zzkH.length;
                        zzb[] zzbArr = new zzb[(zzb4 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzkH, 0, zzbArr, 0, length4);
                        }
                        while (length4 < zzbArr.length - 1) {
                            zzbArr[length4] = new zzb();
                            zzbxl.zza(zzbArr[length4]);
                            zzbxl.zzaeo();
                            length4++;
                        }
                        zzbArr[length4] = new zzb();
                        zzbxl.zza(zzbArr[length4]);
                        this.zzkH = zzbArr;
                        break;
                    case 42:
                        int zzb5 = zzbxw.zzb(zzbxl, 42);
                        int length5 = this.zzkI == null ? 0 : this.zzkI.length;
                        zzb[] zzbArr2 = new zzb[(zzb5 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzkI, 0, zzbArr2, 0, length5);
                        }
                        while (length5 < zzbArr2.length - 1) {
                            zzbArr2[length5] = new zzb();
                            zzbxl.zza(zzbArr2[length5]);
                            zzbxl.zzaeo();
                            length5++;
                        }
                        zzbArr2[length5] = new zzb();
                        zzbxl.zza(zzbArr2[length5]);
                        this.zzkI = zzbArr2;
                        break;
                    case 50:
                        int zzb6 = zzbxw.zzb(zzbxl, 50);
                        int length6 = this.zzkJ == null ? 0 : this.zzkJ.length;
                        zzb[] zzbArr3 = new zzb[(zzb6 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzkJ, 0, zzbArr3, 0, length6);
                        }
                        while (length6 < zzbArr3.length - 1) {
                            zzbArr3[length6] = new zzb();
                            zzbxl.zza(zzbArr3[length6]);
                            zzbxl.zzaeo();
                            length6++;
                        }
                        zzbArr3[length6] = new zzb();
                        zzbxl.zza(zzbArr3[length6]);
                        this.zzkJ = zzbArr3;
                        break;
                    case 58:
                        int zzb7 = zzbxw.zzb(zzbxl, 58);
                        int length7 = this.zzkK == null ? 0 : this.zzkK.length;
                        zzg[] zzgArr = new zzg[(zzb7 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzkK, 0, zzgArr, 0, length7);
                        }
                        while (length7 < zzgArr.length - 1) {
                            zzgArr[length7] = new zzg();
                            zzbxl.zza(zzgArr[length7]);
                            zzbxl.zzaeo();
                            length7++;
                        }
                        zzgArr[length7] = new zzg();
                        zzbxl.zza(zzgArr[length7]);
                        this.zzkK = zzgArr;
                        break;
                    case 74:
                        this.zzkL = zzbxl.readString();
                        break;
                    case 82:
                        this.zzkM = zzbxl.readString();
                        break;
                    case 98:
                        this.zzkN = zzbxl.readString();
                        break;
                    case 106:
                        this.version = zzbxl.readString();
                        break;
                    case 114:
                        if (this.zzkO == null) {
                            this.zzkO = new zza();
                        }
                        zzbxl.zza(this.zzkO);
                        break;
                    case 125:
                        this.zzkP = zzbxl.readFloat();
                        break;
                    case TsExtractor.TS_STREAM_TYPE_HDMV_DTS:
                        int zzb8 = zzbxw.zzb(zzbxl, TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
                        int length8 = this.zzkR == null ? 0 : this.zzkR.length;
                        String[] strArr2 = new String[(zzb8 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzkR, 0, strArr2, 0, length8);
                        }
                        while (length8 < strArr2.length - 1) {
                            strArr2[length8] = zzbxl.readString();
                            zzbxl.zzaeo();
                            length8++;
                        }
                        strArr2[length8] = zzbxl.readString();
                        this.zzkR = strArr2;
                        break;
                    case 136:
                        this.zzkS = zzbxl.zzaes();
                        break;
                    case 144:
                        this.zzkQ = zzbxl.zzaeu();
                        break;
                    case 154:
                        int zzb9 = zzbxw.zzb(zzbxl, 154);
                        int length9 = this.zzkD == null ? 0 : this.zzkD.length;
                        String[] strArr3 = new String[(zzb9 + length9)];
                        if (length9 != 0) {
                            System.arraycopy(this.zzkD, 0, strArr3, 0, length9);
                        }
                        while (length9 < strArr3.length - 1) {
                            strArr3[length9] = zzbxl.readString();
                            zzbxl.zzaeo();
                            length9++;
                        }
                        strArr3[length9] = zzbxl.readString();
                        this.zzkD = strArr3;
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
            if (this.zzkE != null && this.zzkE.length > 0) {
                int i = 0;
                int i2 = 0;
                for (String str : this.zzkE) {
                    if (str != null) {
                        i2++;
                        i += zzbxm.zzkb(str);
                    }
                }
                zzu = zzu + i + (1 * i2);
            }
            if (this.zzkF != null && this.zzkF.length > 0) {
                int i3 = zzu;
                for (zzak.zza zza : this.zzkF) {
                    if (zza != null) {
                        i3 += zzbxm.zzc(2, (zzbxt) zza);
                    }
                }
                zzu = i3;
            }
            if (this.zzkG != null && this.zzkG.length > 0) {
                int i4 = zzu;
                for (zze zze : this.zzkG) {
                    if (zze != null) {
                        i4 += zzbxm.zzc(3, (zzbxt) zze);
                    }
                }
                zzu = i4;
            }
            if (this.zzkH != null && this.zzkH.length > 0) {
                int i5 = zzu;
                for (zzb zzb : this.zzkH) {
                    if (zzb != null) {
                        i5 += zzbxm.zzc(4, (zzbxt) zzb);
                    }
                }
                zzu = i5;
            }
            if (this.zzkI != null && this.zzkI.length > 0) {
                int i6 = zzu;
                for (zzb zzb2 : this.zzkI) {
                    if (zzb2 != null) {
                        i6 += zzbxm.zzc(5, (zzbxt) zzb2);
                    }
                }
                zzu = i6;
            }
            if (this.zzkJ != null && this.zzkJ.length > 0) {
                int i7 = zzu;
                for (zzb zzb3 : this.zzkJ) {
                    if (zzb3 != null) {
                        i7 += zzbxm.zzc(6, (zzbxt) zzb3);
                    }
                }
                zzu = i7;
            }
            if (this.zzkK != null && this.zzkK.length > 0) {
                int i8 = zzu;
                for (zzg zzg : this.zzkK) {
                    if (zzg != null) {
                        i8 += zzbxm.zzc(7, (zzbxt) zzg);
                    }
                }
                zzu = i8;
            }
            if (this.zzkL != null && !this.zzkL.equals("")) {
                zzu += zzbxm.zzr(9, this.zzkL);
            }
            if (this.zzkM != null && !this.zzkM.equals("")) {
                zzu += zzbxm.zzr(10, this.zzkM);
            }
            if (this.zzkN != null && !this.zzkN.equals("0")) {
                zzu += zzbxm.zzr(12, this.zzkN);
            }
            if (this.version != null && !this.version.equals("")) {
                zzu += zzbxm.zzr(13, this.version);
            }
            if (this.zzkO != null) {
                zzu += zzbxm.zzc(14, (zzbxt) this.zzkO);
            }
            if (Float.floatToIntBits(this.zzkP) != Float.floatToIntBits(0.0f)) {
                zzu += zzbxm.zzd(15, this.zzkP);
            }
            if (this.zzkR != null && this.zzkR.length > 0) {
                int i9 = 0;
                int i10 = 0;
                for (String str2 : this.zzkR) {
                    if (str2 != null) {
                        i10++;
                        i9 += zzbxm.zzkb(str2);
                    }
                }
                zzu = zzu + i9 + (i10 * 2);
            }
            if (this.zzkS != 0) {
                zzu += zzbxm.zzL(17, this.zzkS);
            }
            if (this.zzkQ) {
                zzu += zzbxm.zzh(18, this.zzkQ);
            }
            if (this.zzkD == null || this.zzkD.length <= 0) {
                return zzu;
            }
            int i11 = 0;
            int i12 = 0;
            for (String str3 : this.zzkD) {
                if (str3 != null) {
                    i12++;
                    i11 += zzbxm.zzkb(str3);
                }
            }
            return zzu + i11 + (2 * i12);
        }
    }

    public static final class zzg extends zzbxn<zzg> {
        private static volatile zzg[] zzkT;
        public int[] zzkU;
        public int[] zzkV;
        public int[] zzkW;
        public int[] zzkX;
        public int[] zzkY;
        public int[] zzkZ;
        public int[] zzla;
        public int[] zzlb;
        public int[] zzlc;
        public int[] zzld;

        public zzg() {
            zzG();
        }

        public static zzg[] zzF() {
            if (zzkT == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzkT == null) {
                        zzkT = new zzg[0];
                    }
                }
            }
            return zzkT;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzg)) {
                return false;
            }
            zzg zzg = (zzg) obj;
            if (zzbxr.equals(this.zzkU, zzg.zzkU) && zzbxr.equals(this.zzkV, zzg.zzkV) && zzbxr.equals(this.zzkW, zzg.zzkW) && zzbxr.equals(this.zzkX, zzg.zzkX) && zzbxr.equals(this.zzkY, zzg.zzkY) && zzbxr.equals(this.zzkZ, zzg.zzkZ) && zzbxr.equals(this.zzla, zzg.zzla) && zzbxr.equals(this.zzlb, zzg.zzlb) && zzbxr.equals(this.zzlc, zzg.zzlc) && zzbxr.equals(this.zzld, zzg.zzld)) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzg.zzcuI == null || zzg.zzcuI.isEmpty() : this.zzcuI.equals(zzg.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            return (31 * (((((((((((((((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + zzbxr.hashCode(this.zzkU)) * 31) + zzbxr.hashCode(this.zzkV)) * 31) + zzbxr.hashCode(this.zzkW)) * 31) + zzbxr.hashCode(this.zzkX)) * 31) + zzbxr.hashCode(this.zzkY)) * 31) + zzbxr.hashCode(this.zzkZ)) * 31) + zzbxr.hashCode(this.zzla)) * 31) + zzbxr.hashCode(this.zzlb)) * 31) + zzbxr.hashCode(this.zzlc)) * 31) + zzbxr.hashCode(this.zzld))) + ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode());
        }

        public zzg zzG() {
            this.zzkU = zzbxw.zzcuW;
            this.zzkV = zzbxw.zzcuW;
            this.zzkW = zzbxw.zzcuW;
            this.zzkX = zzbxw.zzcuW;
            this.zzkY = zzbxw.zzcuW;
            this.zzkZ = zzbxw.zzcuW;
            this.zzla = zzbxw.zzcuW;
            this.zzlb = zzbxw.zzcuW;
            this.zzlc = zzbxw.zzcuW;
            this.zzld = zzbxw.zzcuW;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzkU != null && this.zzkU.length > 0) {
                for (int zzJ : this.zzkU) {
                    zzbxm.zzJ(1, zzJ);
                }
            }
            if (this.zzkV != null && this.zzkV.length > 0) {
                for (int zzJ2 : this.zzkV) {
                    zzbxm.zzJ(2, zzJ2);
                }
            }
            if (this.zzkW != null && this.zzkW.length > 0) {
                for (int zzJ3 : this.zzkW) {
                    zzbxm.zzJ(3, zzJ3);
                }
            }
            if (this.zzkX != null && this.zzkX.length > 0) {
                for (int zzJ4 : this.zzkX) {
                    zzbxm.zzJ(4, zzJ4);
                }
            }
            if (this.zzkY != null && this.zzkY.length > 0) {
                for (int zzJ5 : this.zzkY) {
                    zzbxm.zzJ(5, zzJ5);
                }
            }
            if (this.zzkZ != null && this.zzkZ.length > 0) {
                for (int zzJ6 : this.zzkZ) {
                    zzbxm.zzJ(6, zzJ6);
                }
            }
            if (this.zzla != null && this.zzla.length > 0) {
                for (int zzJ7 : this.zzla) {
                    zzbxm.zzJ(7, zzJ7);
                }
            }
            if (this.zzlb != null && this.zzlb.length > 0) {
                for (int zzJ8 : this.zzlb) {
                    zzbxm.zzJ(8, zzJ8);
                }
            }
            if (this.zzlc != null && this.zzlc.length > 0) {
                for (int zzJ9 : this.zzlc) {
                    zzbxm.zzJ(9, zzJ9);
                }
            }
            if (this.zzld != null && this.zzld.length > 0) {
                for (int zzJ10 : this.zzld) {
                    zzbxm.zzJ(10, zzJ10);
                }
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzt */
        public zzg zzb(zzbxl zzbxl) throws IOException {
            int i;
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        return this;
                    case 8:
                        int zzb = zzbxw.zzb(zzbxl, 8);
                        int length = this.zzkU == null ? 0 : this.zzkU.length;
                        int[] iArr = new int[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzkU, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length++;
                        }
                        iArr[length] = zzbxl.zzaes();
                        this.zzkU = iArr;
                        continue;
                    case 10:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position = zzbxl.getPosition();
                        int i2 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i2++;
                        }
                        zzbxl.zzrc(position);
                        int length2 = this.zzkU == null ? 0 : this.zzkU.length;
                        int[] iArr2 = new int[(i2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzkU, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = zzbxl.zzaes();
                            length2++;
                        }
                        this.zzkU = iArr2;
                        break;
                    case 16:
                        int zzb2 = zzbxw.zzb(zzbxl, 16);
                        int length3 = this.zzkV == null ? 0 : this.zzkV.length;
                        int[] iArr3 = new int[(zzb2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzkV, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length3++;
                        }
                        iArr3[length3] = zzbxl.zzaes();
                        this.zzkV = iArr3;
                        continue;
                    case 18:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position2 = zzbxl.getPosition();
                        int i3 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i3++;
                        }
                        zzbxl.zzrc(position2);
                        int length4 = this.zzkV == null ? 0 : this.zzkV.length;
                        int[] iArr4 = new int[(i3 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzkV, 0, iArr4, 0, length4);
                        }
                        while (length4 < iArr4.length) {
                            iArr4[length4] = zzbxl.zzaes();
                            length4++;
                        }
                        this.zzkV = iArr4;
                        break;
                    case 24:
                        int zzb3 = zzbxw.zzb(zzbxl, 24);
                        int length5 = this.zzkW == null ? 0 : this.zzkW.length;
                        int[] iArr5 = new int[(zzb3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzkW, 0, iArr5, 0, length5);
                        }
                        while (length5 < iArr5.length - 1) {
                            iArr5[length5] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length5++;
                        }
                        iArr5[length5] = zzbxl.zzaes();
                        this.zzkW = iArr5;
                        continue;
                    case 26:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position3 = zzbxl.getPosition();
                        int i4 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i4++;
                        }
                        zzbxl.zzrc(position3);
                        int length6 = this.zzkW == null ? 0 : this.zzkW.length;
                        int[] iArr6 = new int[(i4 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzkW, 0, iArr6, 0, length6);
                        }
                        while (length6 < iArr6.length) {
                            iArr6[length6] = zzbxl.zzaes();
                            length6++;
                        }
                        this.zzkW = iArr6;
                        break;
                    case 32:
                        int zzb4 = zzbxw.zzb(zzbxl, 32);
                        int length7 = this.zzkX == null ? 0 : this.zzkX.length;
                        int[] iArr7 = new int[(zzb4 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzkX, 0, iArr7, 0, length7);
                        }
                        while (length7 < iArr7.length - 1) {
                            iArr7[length7] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length7++;
                        }
                        iArr7[length7] = zzbxl.zzaes();
                        this.zzkX = iArr7;
                        continue;
                    case 34:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position4 = zzbxl.getPosition();
                        int i5 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i5++;
                        }
                        zzbxl.zzrc(position4);
                        int length8 = this.zzkX == null ? 0 : this.zzkX.length;
                        int[] iArr8 = new int[(i5 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzkX, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = zzbxl.zzaes();
                            length8++;
                        }
                        this.zzkX = iArr8;
                        break;
                    case 40:
                        int zzb5 = zzbxw.zzb(zzbxl, 40);
                        int length9 = this.zzkY == null ? 0 : this.zzkY.length;
                        int[] iArr9 = new int[(zzb5 + length9)];
                        if (length9 != 0) {
                            System.arraycopy(this.zzkY, 0, iArr9, 0, length9);
                        }
                        while (length9 < iArr9.length - 1) {
                            iArr9[length9] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length9++;
                        }
                        iArr9[length9] = zzbxl.zzaes();
                        this.zzkY = iArr9;
                        continue;
                    case 42:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position5 = zzbxl.getPosition();
                        int i6 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i6++;
                        }
                        zzbxl.zzrc(position5);
                        int length10 = this.zzkY == null ? 0 : this.zzkY.length;
                        int[] iArr10 = new int[(i6 + length10)];
                        if (length10 != 0) {
                            System.arraycopy(this.zzkY, 0, iArr10, 0, length10);
                        }
                        while (length10 < iArr10.length) {
                            iArr10[length10] = zzbxl.zzaes();
                            length10++;
                        }
                        this.zzkY = iArr10;
                        break;
                    case 48:
                        int zzb6 = zzbxw.zzb(zzbxl, 48);
                        int length11 = this.zzkZ == null ? 0 : this.zzkZ.length;
                        int[] iArr11 = new int[(zzb6 + length11)];
                        if (length11 != 0) {
                            System.arraycopy(this.zzkZ, 0, iArr11, 0, length11);
                        }
                        while (length11 < iArr11.length - 1) {
                            iArr11[length11] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length11++;
                        }
                        iArr11[length11] = zzbxl.zzaes();
                        this.zzkZ = iArr11;
                        continue;
                    case 50:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position6 = zzbxl.getPosition();
                        int i7 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i7++;
                        }
                        zzbxl.zzrc(position6);
                        int length12 = this.zzkZ == null ? 0 : this.zzkZ.length;
                        int[] iArr12 = new int[(i7 + length12)];
                        if (length12 != 0) {
                            System.arraycopy(this.zzkZ, 0, iArr12, 0, length12);
                        }
                        while (length12 < iArr12.length) {
                            iArr12[length12] = zzbxl.zzaes();
                            length12++;
                        }
                        this.zzkZ = iArr12;
                        break;
                    case 56:
                        int zzb7 = zzbxw.zzb(zzbxl, 56);
                        int length13 = this.zzla == null ? 0 : this.zzla.length;
                        int[] iArr13 = new int[(zzb7 + length13)];
                        if (length13 != 0) {
                            System.arraycopy(this.zzla, 0, iArr13, 0, length13);
                        }
                        while (length13 < iArr13.length - 1) {
                            iArr13[length13] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length13++;
                        }
                        iArr13[length13] = zzbxl.zzaes();
                        this.zzla = iArr13;
                        continue;
                    case 58:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position7 = zzbxl.getPosition();
                        int i8 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i8++;
                        }
                        zzbxl.zzrc(position7);
                        int length14 = this.zzla == null ? 0 : this.zzla.length;
                        int[] iArr14 = new int[(i8 + length14)];
                        if (length14 != 0) {
                            System.arraycopy(this.zzla, 0, iArr14, 0, length14);
                        }
                        while (length14 < iArr14.length) {
                            iArr14[length14] = zzbxl.zzaes();
                            length14++;
                        }
                        this.zzla = iArr14;
                        break;
                    case 64:
                        int zzb8 = zzbxw.zzb(zzbxl, 64);
                        int length15 = this.zzlb == null ? 0 : this.zzlb.length;
                        int[] iArr15 = new int[(zzb8 + length15)];
                        if (length15 != 0) {
                            System.arraycopy(this.zzlb, 0, iArr15, 0, length15);
                        }
                        while (length15 < iArr15.length - 1) {
                            iArr15[length15] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length15++;
                        }
                        iArr15[length15] = zzbxl.zzaes();
                        this.zzlb = iArr15;
                        continue;
                    case 66:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position8 = zzbxl.getPosition();
                        int i9 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i9++;
                        }
                        zzbxl.zzrc(position8);
                        int length16 = this.zzlb == null ? 0 : this.zzlb.length;
                        int[] iArr16 = new int[(i9 + length16)];
                        if (length16 != 0) {
                            System.arraycopy(this.zzlb, 0, iArr16, 0, length16);
                        }
                        while (length16 < iArr16.length) {
                            iArr16[length16] = zzbxl.zzaes();
                            length16++;
                        }
                        this.zzlb = iArr16;
                        break;
                    case 72:
                        int zzb9 = zzbxw.zzb(zzbxl, 72);
                        int length17 = this.zzlc == null ? 0 : this.zzlc.length;
                        int[] iArr17 = new int[(zzb9 + length17)];
                        if (length17 != 0) {
                            System.arraycopy(this.zzlc, 0, iArr17, 0, length17);
                        }
                        while (length17 < iArr17.length - 1) {
                            iArr17[length17] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length17++;
                        }
                        iArr17[length17] = zzbxl.zzaes();
                        this.zzlc = iArr17;
                        continue;
                    case 74:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position9 = zzbxl.getPosition();
                        int i10 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i10++;
                        }
                        zzbxl.zzrc(position9);
                        int length18 = this.zzlc == null ? 0 : this.zzlc.length;
                        int[] iArr18 = new int[(i10 + length18)];
                        if (length18 != 0) {
                            System.arraycopy(this.zzlc, 0, iArr18, 0, length18);
                        }
                        while (length18 < iArr18.length) {
                            iArr18[length18] = zzbxl.zzaes();
                            length18++;
                        }
                        this.zzlc = iArr18;
                        break;
                    case 80:
                        int zzb10 = zzbxw.zzb(zzbxl, 80);
                        int length19 = this.zzld == null ? 0 : this.zzld.length;
                        int[] iArr19 = new int[(zzb10 + length19)];
                        if (length19 != 0) {
                            System.arraycopy(this.zzld, 0, iArr19, 0, length19);
                        }
                        while (length19 < iArr19.length - 1) {
                            iArr19[length19] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length19++;
                        }
                        iArr19[length19] = zzbxl.zzaes();
                        this.zzld = iArr19;
                        continue;
                    case 82:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position10 = zzbxl.getPosition();
                        int i11 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i11++;
                        }
                        zzbxl.zzrc(position10);
                        int length20 = this.zzld == null ? 0 : this.zzld.length;
                        int[] iArr20 = new int[(i11 + length20)];
                        if (length20 != 0) {
                            System.arraycopy(this.zzld, 0, iArr20, 0, length20);
                        }
                        while (length20 < iArr20.length) {
                            iArr20[length20] = zzbxl.zzaes();
                            length20++;
                        }
                        this.zzld = iArr20;
                        break;
                    default:
                        if (!super.zza(zzbxl, zzaeo)) {
                            return this;
                        }
                        continue;
                }
                zzbxl.zzrb(i);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzkU != null && this.zzkU.length > 0) {
                int i = 0;
                for (int zzrg : this.zzkU) {
                    i += zzbxm.zzrg(zzrg);
                }
                zzu = zzu + i + (this.zzkU.length * 1);
            }
            if (this.zzkV != null && this.zzkV.length > 0) {
                int i2 = 0;
                for (int zzrg2 : this.zzkV) {
                    i2 += zzbxm.zzrg(zzrg2);
                }
                zzu = zzu + i2 + (this.zzkV.length * 1);
            }
            if (this.zzkW != null && this.zzkW.length > 0) {
                int i3 = 0;
                for (int zzrg3 : this.zzkW) {
                    i3 += zzbxm.zzrg(zzrg3);
                }
                zzu = zzu + i3 + (this.zzkW.length * 1);
            }
            if (this.zzkX != null && this.zzkX.length > 0) {
                int i4 = 0;
                for (int zzrg4 : this.zzkX) {
                    i4 += zzbxm.zzrg(zzrg4);
                }
                zzu = zzu + i4 + (this.zzkX.length * 1);
            }
            if (this.zzkY != null && this.zzkY.length > 0) {
                int i5 = 0;
                for (int zzrg5 : this.zzkY) {
                    i5 += zzbxm.zzrg(zzrg5);
                }
                zzu = zzu + i5 + (this.zzkY.length * 1);
            }
            if (this.zzkZ != null && this.zzkZ.length > 0) {
                int i6 = 0;
                for (int zzrg6 : this.zzkZ) {
                    i6 += zzbxm.zzrg(zzrg6);
                }
                zzu = zzu + i6 + (this.zzkZ.length * 1);
            }
            if (this.zzla != null && this.zzla.length > 0) {
                int i7 = 0;
                for (int zzrg7 : this.zzla) {
                    i7 += zzbxm.zzrg(zzrg7);
                }
                zzu = zzu + i7 + (this.zzla.length * 1);
            }
            if (this.zzlb != null && this.zzlb.length > 0) {
                int i8 = 0;
                for (int zzrg8 : this.zzlb) {
                    i8 += zzbxm.zzrg(zzrg8);
                }
                zzu = zzu + i8 + (this.zzlb.length * 1);
            }
            if (this.zzlc != null && this.zzlc.length > 0) {
                int i9 = 0;
                for (int zzrg9 : this.zzlc) {
                    i9 += zzbxm.zzrg(zzrg9);
                }
                zzu = zzu + i9 + (this.zzlc.length * 1);
            }
            if (this.zzld == null || this.zzld.length <= 0) {
                return zzu;
            }
            int i10 = 0;
            for (int zzrg10 : this.zzld) {
                i10 += zzbxm.zzrg(zzrg10);
            }
            return zzu + i10 + (1 * this.zzld.length);
        }
    }

    public static final class zzh extends zzbxn<zzh> {
        public static final zzbxo<zzak.zza, zzh> zzle = zzbxo.zza(11, zzh.class, 810);
        private static final zzh[] zzlf = new zzh[0];
        public int[] zzlg;
        public int[] zzlh;
        public int[] zzli;
        public int zzlj;
        public int[] zzlk;
        public int zzll;
        public int zzlm;

        public zzh() {
            zzH();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzh)) {
                return false;
            }
            zzh zzh = (zzh) obj;
            if (zzbxr.equals(this.zzlg, zzh.zzlg) && zzbxr.equals(this.zzlh, zzh.zzlh) && zzbxr.equals(this.zzli, zzh.zzli) && this.zzlj == zzh.zzlj && zzbxr.equals(this.zzlk, zzh.zzlk) && this.zzll == zzh.zzll && this.zzlm == zzh.zzlm) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzh.zzcuI == null || zzh.zzcuI.isEmpty() : this.zzcuI.equals(zzh.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            return (31 * (((((((((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + zzbxr.hashCode(this.zzlg)) * 31) + zzbxr.hashCode(this.zzlh)) * 31) + zzbxr.hashCode(this.zzli)) * 31) + this.zzlj) * 31) + zzbxr.hashCode(this.zzlk)) * 31) + this.zzll) * 31) + this.zzlm)) + ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode());
        }

        public zzh zzH() {
            this.zzlg = zzbxw.zzcuW;
            this.zzlh = zzbxw.zzcuW;
            this.zzli = zzbxw.zzcuW;
            this.zzlj = 0;
            this.zzlk = zzbxw.zzcuW;
            this.zzll = 0;
            this.zzlm = 0;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzlg != null && this.zzlg.length > 0) {
                for (int zzJ : this.zzlg) {
                    zzbxm.zzJ(1, zzJ);
                }
            }
            if (this.zzlh != null && this.zzlh.length > 0) {
                for (int zzJ2 : this.zzlh) {
                    zzbxm.zzJ(2, zzJ2);
                }
            }
            if (this.zzli != null && this.zzli.length > 0) {
                for (int zzJ3 : this.zzli) {
                    zzbxm.zzJ(3, zzJ3);
                }
            }
            if (this.zzlj != 0) {
                zzbxm.zzJ(4, this.zzlj);
            }
            if (this.zzlk != null && this.zzlk.length > 0) {
                for (int zzJ4 : this.zzlk) {
                    zzbxm.zzJ(5, zzJ4);
                }
            }
            if (this.zzll != 0) {
                zzbxm.zzJ(6, this.zzll);
            }
            if (this.zzlm != 0) {
                zzbxm.zzJ(7, this.zzlm);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzlg != null && this.zzlg.length > 0) {
                int i = 0;
                for (int zzrg : this.zzlg) {
                    i += zzbxm.zzrg(zzrg);
                }
                zzu = zzu + i + (this.zzlg.length * 1);
            }
            if (this.zzlh != null && this.zzlh.length > 0) {
                int i2 = 0;
                for (int zzrg2 : this.zzlh) {
                    i2 += zzbxm.zzrg(zzrg2);
                }
                zzu = zzu + i2 + (this.zzlh.length * 1);
            }
            if (this.zzli != null && this.zzli.length > 0) {
                int i3 = 0;
                for (int zzrg3 : this.zzli) {
                    i3 += zzbxm.zzrg(zzrg3);
                }
                zzu = zzu + i3 + (this.zzli.length * 1);
            }
            if (this.zzlj != 0) {
                zzu += zzbxm.zzL(4, this.zzlj);
            }
            if (this.zzlk != null && this.zzlk.length > 0) {
                int i4 = 0;
                for (int zzrg4 : this.zzlk) {
                    i4 += zzbxm.zzrg(zzrg4);
                }
                zzu = zzu + i4 + (1 * this.zzlk.length);
            }
            if (this.zzll != 0) {
                zzu += zzbxm.zzL(6, this.zzll);
            }
            return this.zzlm != 0 ? zzu + zzbxm.zzL(7, this.zzlm) : zzu;
        }

        /* renamed from: zzu */
        public zzh zzb(zzbxl zzbxl) throws IOException {
            int i;
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        return this;
                    case 8:
                        int zzb = zzbxw.zzb(zzbxl, 8);
                        int length = this.zzlg == null ? 0 : this.zzlg.length;
                        int[] iArr = new int[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzlg, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length++;
                        }
                        iArr[length] = zzbxl.zzaes();
                        this.zzlg = iArr;
                        continue;
                    case 10:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position = zzbxl.getPosition();
                        int i2 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i2++;
                        }
                        zzbxl.zzrc(position);
                        int length2 = this.zzlg == null ? 0 : this.zzlg.length;
                        int[] iArr2 = new int[(i2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzlg, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = zzbxl.zzaes();
                            length2++;
                        }
                        this.zzlg = iArr2;
                        break;
                    case 16:
                        int zzb2 = zzbxw.zzb(zzbxl, 16);
                        int length3 = this.zzlh == null ? 0 : this.zzlh.length;
                        int[] iArr3 = new int[(zzb2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzlh, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length3++;
                        }
                        iArr3[length3] = zzbxl.zzaes();
                        this.zzlh = iArr3;
                        continue;
                    case 18:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position2 = zzbxl.getPosition();
                        int i3 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i3++;
                        }
                        zzbxl.zzrc(position2);
                        int length4 = this.zzlh == null ? 0 : this.zzlh.length;
                        int[] iArr4 = new int[(i3 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzlh, 0, iArr4, 0, length4);
                        }
                        while (length4 < iArr4.length) {
                            iArr4[length4] = zzbxl.zzaes();
                            length4++;
                        }
                        this.zzlh = iArr4;
                        break;
                    case 24:
                        int zzb3 = zzbxw.zzb(zzbxl, 24);
                        int length5 = this.zzli == null ? 0 : this.zzli.length;
                        int[] iArr5 = new int[(zzb3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzli, 0, iArr5, 0, length5);
                        }
                        while (length5 < iArr5.length - 1) {
                            iArr5[length5] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length5++;
                        }
                        iArr5[length5] = zzbxl.zzaes();
                        this.zzli = iArr5;
                        continue;
                    case 26:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position3 = zzbxl.getPosition();
                        int i4 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i4++;
                        }
                        zzbxl.zzrc(position3);
                        int length6 = this.zzli == null ? 0 : this.zzli.length;
                        int[] iArr6 = new int[(i4 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzli, 0, iArr6, 0, length6);
                        }
                        while (length6 < iArr6.length) {
                            iArr6[length6] = zzbxl.zzaes();
                            length6++;
                        }
                        this.zzli = iArr6;
                        break;
                    case 32:
                        this.zzlj = zzbxl.zzaes();
                        continue;
                    case 40:
                        int zzb4 = zzbxw.zzb(zzbxl, 40);
                        int length7 = this.zzlk == null ? 0 : this.zzlk.length;
                        int[] iArr7 = new int[(zzb4 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzlk, 0, iArr7, 0, length7);
                        }
                        while (length7 < iArr7.length - 1) {
                            iArr7[length7] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length7++;
                        }
                        iArr7[length7] = zzbxl.zzaes();
                        this.zzlk = iArr7;
                        continue;
                    case 42:
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position4 = zzbxl.getPosition();
                        int i5 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i5++;
                        }
                        zzbxl.zzrc(position4);
                        int length8 = this.zzlk == null ? 0 : this.zzlk.length;
                        int[] iArr8 = new int[(i5 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzlk, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = zzbxl.zzaes();
                            length8++;
                        }
                        this.zzlk = iArr8;
                        break;
                    case 48:
                        this.zzll = zzbxl.zzaes();
                        continue;
                    case 56:
                        this.zzlm = zzbxl.zzaes();
                        continue;
                    default:
                        if (!super.zza(zzbxl, zzaeo)) {
                            return this;
                        }
                        continue;
                }
                zzbxl.zzrb(i);
            }
        }
    }

    public static final class zzi extends zzbxn<zzi> {
        private static volatile zzi[] zzln;
        public String name;
        public zzak.zza zzlo;
        public zzd zzlp;

        public zzi() {
            zzJ();
        }

        public static zzi[] zzI() {
            if (zzln == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzln == null) {
                        zzln = new zzi[0];
                    }
                }
            }
            return zzln;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzi)) {
                return false;
            }
            zzi zzi = (zzi) obj;
            if (this.name == null) {
                if (zzi.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzi.name)) {
                return false;
            }
            if (this.zzlo == null) {
                if (zzi.zzlo != null) {
                    return false;
                }
            } else if (!this.zzlo.equals(zzi.zzlo)) {
                return false;
            }
            if (this.zzlp == null) {
                if (zzi.zzlp != null) {
                    return false;
                }
            } else if (!this.zzlp.equals(zzi.zzlp)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzi.zzcuI == null || zzi.zzcuI.isEmpty() : this.zzcuI.equals(zzi.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzlo == null ? 0 : this.zzlo.hashCode())) * 31) + (this.zzlp == null ? 0 : this.zzlp.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zzi zzJ() {
            this.name = "";
            this.zzlo = null;
            this.zzlp = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.name != null && !this.name.equals("")) {
                zzbxm.zzq(1, this.name);
            }
            if (this.zzlo != null) {
                zzbxm.zza(2, (zzbxt) this.zzlo);
            }
            if (this.zzlp != null) {
                zzbxm.zza(3, (zzbxt) this.zzlp);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.name != null && !this.name.equals("")) {
                zzu += zzbxm.zzr(1, this.name);
            }
            if (this.zzlo != null) {
                zzu += zzbxm.zzc(2, (zzbxt) this.zzlo);
            }
            return this.zzlp != null ? zzu + zzbxm.zzc(3, (zzbxt) this.zzlp) : zzu;
        }

        /* renamed from: zzv */
        public zzi zzb(zzbxl zzbxl) throws IOException {
            zzbxt zzbxt;
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo != 10) {
                    if (zzaeo == 18) {
                        if (this.zzlo == null) {
                            this.zzlo = new zzak.zza();
                        }
                        zzbxt = this.zzlo;
                    } else if (zzaeo == 26) {
                        if (this.zzlp == null) {
                            this.zzlp = new zzd();
                        }
                        zzbxt = this.zzlp;
                    } else if (!super.zza(zzbxl, zzaeo)) {
                        return this;
                    }
                    zzbxl.zza(zzbxt);
                } else {
                    this.name = zzbxl.readString();
                }
            }
        }
    }

    public static final class zzj extends zzbxn<zzj> {
        public zzi[] zzlq;
        public zzf zzlr;
        public String zzls;

        public zzj() {
            zzK();
        }

        public static zzj zzg(byte[] bArr) throws zzbxs {
            return (zzj) zzbxt.zza(new zzj(), bArr);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzj)) {
                return false;
            }
            zzj zzj = (zzj) obj;
            if (!zzbxr.equals((Object[]) this.zzlq, (Object[]) zzj.zzlq)) {
                return false;
            }
            if (this.zzlr == null) {
                if (zzj.zzlr != null) {
                    return false;
                }
            } else if (!this.zzlr.equals(zzj.zzlr)) {
                return false;
            }
            if (this.zzls == null) {
                if (zzj.zzls != null) {
                    return false;
                }
            } else if (!this.zzls.equals(zzj.zzls)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzj.zzcuI == null || zzj.zzcuI.isEmpty() : this.zzcuI.equals(zzj.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + zzbxr.hashCode((Object[]) this.zzlq)) * 31) + (this.zzlr == null ? 0 : this.zzlr.hashCode())) * 31) + (this.zzls == null ? 0 : this.zzls.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zzj zzK() {
            this.zzlq = zzi.zzI();
            this.zzlr = null;
            this.zzls = "";
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzlq != null && this.zzlq.length > 0) {
                for (zzi zzi : this.zzlq) {
                    if (zzi != null) {
                        zzbxm.zza(1, (zzbxt) zzi);
                    }
                }
            }
            if (this.zzlr != null) {
                zzbxm.zza(2, (zzbxt) this.zzlr);
            }
            if (this.zzls != null && !this.zzls.equals("")) {
                zzbxm.zzq(3, this.zzls);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzlq != null && this.zzlq.length > 0) {
                for (zzi zzi : this.zzlq) {
                    if (zzi != null) {
                        zzu += zzbxm.zzc(1, (zzbxt) zzi);
                    }
                }
            }
            if (this.zzlr != null) {
                zzu += zzbxm.zzc(2, (zzbxt) this.zzlr);
            }
            return (this.zzls == null || this.zzls.equals("")) ? zzu : zzu + zzbxm.zzr(3, this.zzls);
        }

        /* renamed from: zzw */
        public zzj zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    int zzb = zzbxw.zzb(zzbxl, 10);
                    int length = this.zzlq == null ? 0 : this.zzlq.length;
                    zzi[] zziArr = new zzi[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzlq, 0, zziArr, 0, length);
                    }
                    while (length < zziArr.length - 1) {
                        zziArr[length] = new zzi();
                        zzbxl.zza(zziArr[length]);
                        zzbxl.zzaeo();
                        length++;
                    }
                    zziArr[length] = new zzi();
                    zzbxl.zza(zziArr[length]);
                    this.zzlq = zziArr;
                } else if (zzaeo == 18) {
                    if (this.zzlr == null) {
                        this.zzlr = new zzf();
                    }
                    zzbxl.zza(this.zzlr);
                } else if (zzaeo == 26) {
                    this.zzls = zzbxl.readString();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }
    }
}
