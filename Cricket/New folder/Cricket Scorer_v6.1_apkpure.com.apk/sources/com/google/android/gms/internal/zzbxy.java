package com.google.android.gms.internal;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import java.io.IOException;
import java.util.Arrays;

public interface zzbxy {

    public static final class zza extends zzbxn<zza> implements Cloneable {
        public String[] zzcvg;
        public String[] zzcvh;
        public int[] zzcvi;
        public long[] zzcvj;
        public long[] zzcvk;

        public zza() {
            zzaeV();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (zzbxr.equals((Object[]) this.zzcvg, (Object[]) zza.zzcvg) && zzbxr.equals((Object[]) this.zzcvh, (Object[]) zza.zzcvh) && zzbxr.equals(this.zzcvi, zza.zzcvi) && zzbxr.equals(this.zzcvj, zza.zzcvj) && zzbxr.equals(this.zzcvk, zza.zzcvk)) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zza.zzcuI == null || zza.zzcuI.isEmpty() : this.zzcuI.equals(zza.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            return (31 * (((((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + zzbxr.hashCode((Object[]) this.zzcvg)) * 31) + zzbxr.hashCode((Object[]) this.zzcvh)) * 31) + zzbxr.hashCode(this.zzcvi)) * 31) + zzbxr.hashCode(this.zzcvj)) * 31) + zzbxr.hashCode(this.zzcvk))) + ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode());
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvg != null && this.zzcvg.length > 0) {
                for (String str : this.zzcvg) {
                    if (str != null) {
                        zzbxm.zzq(1, str);
                    }
                }
            }
            if (this.zzcvh != null && this.zzcvh.length > 0) {
                for (String str2 : this.zzcvh) {
                    if (str2 != null) {
                        zzbxm.zzq(2, str2);
                    }
                }
            }
            if (this.zzcvi != null && this.zzcvi.length > 0) {
                for (int zzJ : this.zzcvi) {
                    zzbxm.zzJ(3, zzJ);
                }
            }
            if (this.zzcvj != null && this.zzcvj.length > 0) {
                for (long zzb : this.zzcvj) {
                    zzbxm.zzb(4, zzb);
                }
            }
            if (this.zzcvk != null && this.zzcvk.length > 0) {
                for (long zzb2 : this.zzcvk) {
                    zzbxm.zzb(5, zzb2);
                }
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaP */
        public zza zzb(zzbxl zzbxl) throws IOException {
            int i;
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    int zzb = zzbxw.zzb(zzbxl, 10);
                    int length = this.zzcvg == null ? 0 : this.zzcvg.length;
                    String[] strArr = new String[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzcvg, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzbxl.readString();
                        zzbxl.zzaeo();
                        length++;
                    }
                    strArr[length] = zzbxl.readString();
                    this.zzcvg = strArr;
                } else if (zzaeo == 18) {
                    int zzb2 = zzbxw.zzb(zzbxl, 18);
                    int length2 = this.zzcvh == null ? 0 : this.zzcvh.length;
                    String[] strArr2 = new String[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzcvh, 0, strArr2, 0, length2);
                    }
                    while (length2 < strArr2.length - 1) {
                        strArr2[length2] = zzbxl.readString();
                        zzbxl.zzaeo();
                        length2++;
                    }
                    strArr2[length2] = zzbxl.readString();
                    this.zzcvh = strArr2;
                } else if (zzaeo != 24) {
                    if (zzaeo == 26) {
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position = zzbxl.getPosition();
                        int i2 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i2++;
                        }
                        zzbxl.zzrc(position);
                        int length3 = this.zzcvi == null ? 0 : this.zzcvi.length;
                        int[] iArr = new int[(i2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzcvi, 0, iArr, 0, length3);
                        }
                        while (length3 < iArr.length) {
                            iArr[length3] = zzbxl.zzaes();
                            length3++;
                        }
                        this.zzcvi = iArr;
                    } else if (zzaeo == 32) {
                        int zzb3 = zzbxw.zzb(zzbxl, 32);
                        int length4 = this.zzcvj == null ? 0 : this.zzcvj.length;
                        long[] jArr = new long[(zzb3 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzcvj, 0, jArr, 0, length4);
                        }
                        while (length4 < jArr.length - 1) {
                            jArr[length4] = zzbxl.zzaer();
                            zzbxl.zzaeo();
                            length4++;
                        }
                        jArr[length4] = zzbxl.zzaer();
                        this.zzcvj = jArr;
                    } else if (zzaeo == 34) {
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position2 = zzbxl.getPosition();
                        int i3 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaer();
                            i3++;
                        }
                        zzbxl.zzrc(position2);
                        int length5 = this.zzcvj == null ? 0 : this.zzcvj.length;
                        long[] jArr2 = new long[(i3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzcvj, 0, jArr2, 0, length5);
                        }
                        while (length5 < jArr2.length) {
                            jArr2[length5] = zzbxl.zzaer();
                            length5++;
                        }
                        this.zzcvj = jArr2;
                    } else if (zzaeo == 40) {
                        int zzb4 = zzbxw.zzb(zzbxl, 40);
                        int length6 = this.zzcvk == null ? 0 : this.zzcvk.length;
                        long[] jArr3 = new long[(zzb4 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzcvk, 0, jArr3, 0, length6);
                        }
                        while (length6 < jArr3.length - 1) {
                            jArr3[length6] = zzbxl.zzaer();
                            zzbxl.zzaeo();
                            length6++;
                        }
                        jArr3[length6] = zzbxl.zzaer();
                        this.zzcvk = jArr3;
                    } else if (zzaeo == 42) {
                        i = zzbxl.zzra(zzbxl.zzaex());
                        int position3 = zzbxl.getPosition();
                        int i4 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaer();
                            i4++;
                        }
                        zzbxl.zzrc(position3);
                        int length7 = this.zzcvk == null ? 0 : this.zzcvk.length;
                        long[] jArr4 = new long[(i4 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzcvk, 0, jArr4, 0, length7);
                        }
                        while (length7 < jArr4.length) {
                            jArr4[length7] = zzbxl.zzaer();
                            length7++;
                        }
                        this.zzcvk = jArr4;
                    } else if (!super.zza(zzbxl, zzaeo)) {
                        return this;
                    }
                    zzbxl.zzrb(i);
                } else {
                    int zzb5 = zzbxw.zzb(zzbxl, 24);
                    int length8 = this.zzcvi == null ? 0 : this.zzcvi.length;
                    int[] iArr2 = new int[(zzb5 + length8)];
                    if (length8 != 0) {
                        System.arraycopy(this.zzcvi, 0, iArr2, 0, length8);
                    }
                    while (length8 < iArr2.length - 1) {
                        iArr2[length8] = zzbxl.zzaes();
                        zzbxl.zzaeo();
                        length8++;
                    }
                    iArr2[length8] = zzbxl.zzaes();
                    this.zzcvi = iArr2;
                }
            }
        }

        public /* synthetic */ zzbxn zzaeH() throws CloneNotSupportedException {
            return (zza) clone();
        }

        public /* synthetic */ zzbxt zzaeI() throws CloneNotSupportedException {
            return (zza) clone();
        }

        public zza zzaeV() {
            this.zzcvg = zzbxw.zzcvb;
            this.zzcvh = zzbxw.zzcvb;
            this.zzcvi = zzbxw.zzcuW;
            this.zzcvj = zzbxw.zzcuX;
            this.zzcvk = zzbxw.zzcuX;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzaeW */
        public zza clone() {
            try {
                zza zza = (zza) super.clone();
                if (this.zzcvg != null && this.zzcvg.length > 0) {
                    zza.zzcvg = (String[]) this.zzcvg.clone();
                }
                if (this.zzcvh != null && this.zzcvh.length > 0) {
                    zza.zzcvh = (String[]) this.zzcvh.clone();
                }
                if (this.zzcvi != null && this.zzcvi.length > 0) {
                    zza.zzcvi = (int[]) this.zzcvi.clone();
                }
                if (this.zzcvj != null && this.zzcvj.length > 0) {
                    zza.zzcvj = (long[]) this.zzcvj.clone();
                }
                if (this.zzcvk != null && this.zzcvk.length > 0) {
                    zza.zzcvk = (long[]) this.zzcvk.clone();
                }
                return zza;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcvg != null && this.zzcvg.length > 0) {
                int i = 0;
                int i2 = 0;
                for (String str : this.zzcvg) {
                    if (str != null) {
                        i2++;
                        i += zzbxm.zzkb(str);
                    }
                }
                zzu = zzu + i + (i2 * 1);
            }
            if (this.zzcvh != null && this.zzcvh.length > 0) {
                int i3 = 0;
                int i4 = 0;
                for (String str2 : this.zzcvh) {
                    if (str2 != null) {
                        i4++;
                        i3 += zzbxm.zzkb(str2);
                    }
                }
                zzu = zzu + i3 + (i4 * 1);
            }
            if (this.zzcvi != null && this.zzcvi.length > 0) {
                int i5 = 0;
                for (int zzrg : this.zzcvi) {
                    i5 += zzbxm.zzrg(zzrg);
                }
                zzu = zzu + i5 + (this.zzcvi.length * 1);
            }
            if (this.zzcvj != null && this.zzcvj.length > 0) {
                int i6 = 0;
                for (long zzbf : this.zzcvj) {
                    i6 += zzbxm.zzbf(zzbf);
                }
                zzu = zzu + i6 + (this.zzcvj.length * 1);
            }
            if (this.zzcvk == null || this.zzcvk.length <= 0) {
                return zzu;
            }
            int i7 = 0;
            for (long zzbf2 : this.zzcvk) {
                i7 += zzbxm.zzbf(zzbf2);
            }
            return zzu + i7 + (1 * this.zzcvk.length);
        }
    }

    public static final class zzb extends zzbxn<zzb> implements Cloneable {
        public String version;
        public int zzcvl;
        public String zzcvm;

        public zzb() {
            zzaeX();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzcvl != zzb.zzcvl) {
                return false;
            }
            if (this.zzcvm == null) {
                if (zzb.zzcvm != null) {
                    return false;
                }
            } else if (!this.zzcvm.equals(zzb.zzcvm)) {
                return false;
            }
            if (this.version == null) {
                if (zzb.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzb.version)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzb.zzcuI == null || zzb.zzcuI.isEmpty() : this.zzcuI.equals(zzb.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + this.zzcvl) * 31) + (this.zzcvm == null ? 0 : this.zzcvm.hashCode())) * 31) + (this.version == null ? 0 : this.version.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvl != 0) {
                zzbxm.zzJ(1, this.zzcvl);
            }
            if (this.zzcvm != null && !this.zzcvm.equals("")) {
                zzbxm.zzq(2, this.zzcvm);
            }
            if (this.version != null && !this.version.equals("")) {
                zzbxm.zzq(3, this.version);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaQ */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 8) {
                    this.zzcvl = zzbxl.zzaes();
                } else if (zzaeo == 18) {
                    this.zzcvm = zzbxl.readString();
                } else if (zzaeo == 26) {
                    this.version = zzbxl.readString();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        public /* synthetic */ zzbxn zzaeH() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        public /* synthetic */ zzbxt zzaeI() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        public zzb zzaeX() {
            this.zzcvl = 0;
            this.zzcvm = "";
            this.version = "";
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzaeY */
        public zzb clone() {
            try {
                return (zzb) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcvl != 0) {
                zzu += zzbxm.zzL(1, this.zzcvl);
            }
            if (this.zzcvm != null && !this.zzcvm.equals("")) {
                zzu += zzbxm.zzr(2, this.zzcvm);
            }
            return (this.version == null || this.version.equals("")) ? zzu : zzu + zzbxm.zzr(3, this.version);
        }
    }

    public static final class zzc extends zzbxn<zzc> implements Cloneable {
        public byte[] zzcvn;
        public String zzcvo;
        public byte[][] zzcvp;
        public boolean zzcvq;

        public zzc() {
            zzaeZ();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (!Arrays.equals(this.zzcvn, zzc.zzcvn)) {
                return false;
            }
            if (this.zzcvo == null) {
                if (zzc.zzcvo != null) {
                    return false;
                }
            } else if (!this.zzcvo.equals(zzc.zzcvo)) {
                return false;
            }
            if (zzbxr.zza(this.zzcvp, zzc.zzcvp) && this.zzcvq == zzc.zzcvq) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzc.zzcuI == null || zzc.zzcuI.isEmpty() : this.zzcuI.equals(zzc.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + Arrays.hashCode(this.zzcvn)) * 31) + (this.zzcvo == null ? 0 : this.zzcvo.hashCode())) * 31) + zzbxr.zzb(this.zzcvp)) * 31) + (this.zzcvq ? 1231 : 1237));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (!Arrays.equals(this.zzcvn, zzbxw.zzcvd)) {
                zzbxm.zzb(1, this.zzcvn);
            }
            if (this.zzcvp != null && this.zzcvp.length > 0) {
                for (byte[] bArr : this.zzcvp) {
                    if (bArr != null) {
                        zzbxm.zzb(2, bArr);
                    }
                }
            }
            if (this.zzcvq) {
                zzbxm.zzg(3, this.zzcvq);
            }
            if (this.zzcvo != null && !this.zzcvo.equals("")) {
                zzbxm.zzq(4, this.zzcvo);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaR */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    this.zzcvn = zzbxl.readBytes();
                } else if (zzaeo == 18) {
                    int zzb = zzbxw.zzb(zzbxl, 18);
                    int length = this.zzcvp == null ? 0 : this.zzcvp.length;
                    byte[][] bArr = new byte[(zzb + length)][];
                    if (length != 0) {
                        System.arraycopy(this.zzcvp, 0, bArr, 0, length);
                    }
                    while (length < bArr.length - 1) {
                        bArr[length] = zzbxl.readBytes();
                        zzbxl.zzaeo();
                        length++;
                    }
                    bArr[length] = zzbxl.readBytes();
                    this.zzcvp = bArr;
                } else if (zzaeo == 24) {
                    this.zzcvq = zzbxl.zzaeu();
                } else if (zzaeo == 34) {
                    this.zzcvo = zzbxl.readString();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        public /* synthetic */ zzbxn zzaeH() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        public /* synthetic */ zzbxt zzaeI() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        public zzc zzaeZ() {
            this.zzcvn = zzbxw.zzcvd;
            this.zzcvo = "";
            this.zzcvp = zzbxw.zzcvc;
            this.zzcvq = false;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzafa */
        public zzc clone() {
            try {
                zzc zzc = (zzc) super.clone();
                if (this.zzcvp != null && this.zzcvp.length > 0) {
                    zzc.zzcvp = (byte[][]) this.zzcvp.clone();
                }
                return zzc;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (!Arrays.equals(this.zzcvn, zzbxw.zzcvd)) {
                zzu += zzbxm.zzc(1, this.zzcvn);
            }
            if (this.zzcvp != null && this.zzcvp.length > 0) {
                int i = 0;
                int i2 = 0;
                for (byte[] bArr : this.zzcvp) {
                    if (bArr != null) {
                        i2++;
                        i += zzbxm.zzai(bArr);
                    }
                }
                zzu = zzu + i + (1 * i2);
            }
            if (this.zzcvq) {
                zzu += zzbxm.zzh(3, this.zzcvq);
            }
            return (this.zzcvo == null || this.zzcvo.equals("")) ? zzu : zzu + zzbxm.zzr(4, this.zzcvo);
        }
    }

    public static final class zzd extends zzbxn<zzd> implements Cloneable {
        public String tag;
        public boolean zzced;
        public String zzcvA;
        public zza zzcvB;
        public String zzcvC;
        public long zzcvD;
        public zzc zzcvE;
        public byte[] zzcvF;
        public String zzcvG;
        public int zzcvH;
        public int[] zzcvI;
        public long zzcvJ;
        public zzf zzcvK;
        public long zzcvr;
        public long zzcvs;
        public long zzcvt;
        public int zzcvu;
        public zze[] zzcvv;
        public byte[] zzcvw;
        public zzb zzcvx;
        public byte[] zzcvy;
        public String zzcvz;
        public int zzri;

        public zzd() {
            zzafb();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (this.zzcvr != zzd.zzcvr || this.zzcvs != zzd.zzcvs || this.zzcvt != zzd.zzcvt) {
                return false;
            }
            if (this.tag == null) {
                if (zzd.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(zzd.tag)) {
                return false;
            }
            if (this.zzcvu != zzd.zzcvu || this.zzri != zzd.zzri || this.zzced != zzd.zzced || !zzbxr.equals((Object[]) this.zzcvv, (Object[]) zzd.zzcvv) || !Arrays.equals(this.zzcvw, zzd.zzcvw)) {
                return false;
            }
            if (this.zzcvx == null) {
                if (zzd.zzcvx != null) {
                    return false;
                }
            } else if (!this.zzcvx.equals(zzd.zzcvx)) {
                return false;
            }
            if (!Arrays.equals(this.zzcvy, zzd.zzcvy)) {
                return false;
            }
            if (this.zzcvz == null) {
                if (zzd.zzcvz != null) {
                    return false;
                }
            } else if (!this.zzcvz.equals(zzd.zzcvz)) {
                return false;
            }
            if (this.zzcvA == null) {
                if (zzd.zzcvA != null) {
                    return false;
                }
            } else if (!this.zzcvA.equals(zzd.zzcvA)) {
                return false;
            }
            if (this.zzcvB == null) {
                if (zzd.zzcvB != null) {
                    return false;
                }
            } else if (!this.zzcvB.equals(zzd.zzcvB)) {
                return false;
            }
            if (this.zzcvC == null) {
                if (zzd.zzcvC != null) {
                    return false;
                }
            } else if (!this.zzcvC.equals(zzd.zzcvC)) {
                return false;
            }
            if (this.zzcvD != zzd.zzcvD) {
                return false;
            }
            if (this.zzcvE == null) {
                if (zzd.zzcvE != null) {
                    return false;
                }
            } else if (!this.zzcvE.equals(zzd.zzcvE)) {
                return false;
            }
            if (!Arrays.equals(this.zzcvF, zzd.zzcvF)) {
                return false;
            }
            if (this.zzcvG == null) {
                if (zzd.zzcvG != null) {
                    return false;
                }
            } else if (!this.zzcvG.equals(zzd.zzcvG)) {
                return false;
            }
            if (this.zzcvH != zzd.zzcvH || !zzbxr.equals(this.zzcvI, zzd.zzcvI) || this.zzcvJ != zzd.zzcvJ) {
                return false;
            }
            if (this.zzcvK == null) {
                if (zzd.zzcvK != null) {
                    return false;
                }
            } else if (!this.zzcvK.equals(zzd.zzcvK)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzd.zzcuI == null || zzd.zzcuI.isEmpty() : this.zzcuI.equals(zzd.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((((((((((((((((((((((((((((((((((((((((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + ((int) (this.zzcvr ^ (this.zzcvr >>> 32)))) * 31) + ((int) (this.zzcvs ^ (this.zzcvs >>> 32)))) * 31) + ((int) (this.zzcvt ^ (this.zzcvt >>> 32)))) * 31) + (this.tag == null ? 0 : this.tag.hashCode())) * 31) + this.zzcvu) * 31) + this.zzri) * 31) + (this.zzced ? 1231 : 1237)) * 31) + zzbxr.hashCode((Object[]) this.zzcvv)) * 31) + Arrays.hashCode(this.zzcvw)) * 31) + (this.zzcvx == null ? 0 : this.zzcvx.hashCode())) * 31) + Arrays.hashCode(this.zzcvy)) * 31) + (this.zzcvz == null ? 0 : this.zzcvz.hashCode())) * 31) + (this.zzcvA == null ? 0 : this.zzcvA.hashCode())) * 31) + (this.zzcvB == null ? 0 : this.zzcvB.hashCode())) * 31) + (this.zzcvC == null ? 0 : this.zzcvC.hashCode())) * 31) + ((int) (this.zzcvD ^ (this.zzcvD >>> 32)))) * 31) + (this.zzcvE == null ? 0 : this.zzcvE.hashCode())) * 31) + Arrays.hashCode(this.zzcvF)) * 31) + (this.zzcvG == null ? 0 : this.zzcvG.hashCode())) * 31) + this.zzcvH) * 31) + zzbxr.hashCode(this.zzcvI)) * 31) + ((int) (this.zzcvJ ^ (this.zzcvJ >>> 32)))) * 31) + (this.zzcvK == null ? 0 : this.zzcvK.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvr != 0) {
                zzbxm.zzb(1, this.zzcvr);
            }
            if (this.tag != null && !this.tag.equals("")) {
                zzbxm.zzq(2, this.tag);
            }
            if (this.zzcvv != null && this.zzcvv.length > 0) {
                for (zze zze : this.zzcvv) {
                    if (zze != null) {
                        zzbxm.zza(3, (zzbxt) zze);
                    }
                }
            }
            if (!Arrays.equals(this.zzcvw, zzbxw.zzcvd)) {
                zzbxm.zzb(4, this.zzcvw);
            }
            if (!Arrays.equals(this.zzcvy, zzbxw.zzcvd)) {
                zzbxm.zzb(6, this.zzcvy);
            }
            if (this.zzcvB != null) {
                zzbxm.zza(7, (zzbxt) this.zzcvB);
            }
            if (this.zzcvz != null && !this.zzcvz.equals("")) {
                zzbxm.zzq(8, this.zzcvz);
            }
            if (this.zzcvx != null) {
                zzbxm.zza(9, (zzbxt) this.zzcvx);
            }
            if (this.zzced) {
                zzbxm.zzg(10, this.zzced);
            }
            if (this.zzcvu != 0) {
                zzbxm.zzJ(11, this.zzcvu);
            }
            if (this.zzri != 0) {
                zzbxm.zzJ(12, this.zzri);
            }
            if (this.zzcvA != null && !this.zzcvA.equals("")) {
                zzbxm.zzq(13, this.zzcvA);
            }
            if (this.zzcvC != null && !this.zzcvC.equals("")) {
                zzbxm.zzq(14, this.zzcvC);
            }
            if (this.zzcvD != 180000) {
                zzbxm.zzd(15, this.zzcvD);
            }
            if (this.zzcvE != null) {
                zzbxm.zza(16, (zzbxt) this.zzcvE);
            }
            if (this.zzcvs != 0) {
                zzbxm.zzb(17, this.zzcvs);
            }
            if (!Arrays.equals(this.zzcvF, zzbxw.zzcvd)) {
                zzbxm.zzb(18, this.zzcvF);
            }
            if (this.zzcvH != 0) {
                zzbxm.zzJ(19, this.zzcvH);
            }
            if (this.zzcvI != null && this.zzcvI.length > 0) {
                for (int zzJ : this.zzcvI) {
                    zzbxm.zzJ(20, zzJ);
                }
            }
            if (this.zzcvt != 0) {
                zzbxm.zzb(21, this.zzcvt);
            }
            if (this.zzcvJ != 0) {
                zzbxm.zzb(22, this.zzcvJ);
            }
            if (this.zzcvK != null) {
                zzbxm.zza(23, (zzbxt) this.zzcvK);
            }
            if (this.zzcvG != null && !this.zzcvG.equals("")) {
                zzbxm.zzq(24, this.zzcvG);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaS */
        public zzd zzb(zzbxl zzbxl) throws IOException {
            zzbxt zzbxt;
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        return this;
                    case 8:
                        this.zzcvr = zzbxl.zzaer();
                        continue;
                    case 18:
                        this.tag = zzbxl.readString();
                        continue;
                    case 26:
                        int zzb = zzbxw.zzb(zzbxl, 26);
                        int length = this.zzcvv == null ? 0 : this.zzcvv.length;
                        zze[] zzeArr = new zze[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzcvv, 0, zzeArr, 0, length);
                        }
                        while (length < zzeArr.length - 1) {
                            zzeArr[length] = new zze();
                            zzbxl.zza(zzeArr[length]);
                            zzbxl.zzaeo();
                            length++;
                        }
                        zzeArr[length] = new zze();
                        zzbxl.zza(zzeArr[length]);
                        this.zzcvv = zzeArr;
                        continue;
                    case 34:
                        this.zzcvw = zzbxl.readBytes();
                        continue;
                    case 50:
                        this.zzcvy = zzbxl.readBytes();
                        continue;
                    case 58:
                        if (this.zzcvB == null) {
                            this.zzcvB = new zza();
                        }
                        zzbxt = this.zzcvB;
                        break;
                    case 66:
                        this.zzcvz = zzbxl.readString();
                        continue;
                    case 74:
                        if (this.zzcvx == null) {
                            this.zzcvx = new zzb();
                        }
                        zzbxt = this.zzcvx;
                        break;
                    case 80:
                        this.zzced = zzbxl.zzaeu();
                        continue;
                    case 88:
                        this.zzcvu = zzbxl.zzaes();
                        continue;
                    case 96:
                        this.zzri = zzbxl.zzaes();
                        continue;
                    case 106:
                        this.zzcvA = zzbxl.readString();
                        continue;
                    case 114:
                        this.zzcvC = zzbxl.readString();
                        continue;
                    case 120:
                        this.zzcvD = zzbxl.zzaew();
                        continue;
                    case TsExtractor.TS_STREAM_TYPE_HDMV_DTS:
                        if (this.zzcvE == null) {
                            this.zzcvE = new zzc();
                        }
                        zzbxt = this.zzcvE;
                        break;
                    case 136:
                        this.zzcvs = zzbxl.zzaer();
                        continue;
                    case 146:
                        this.zzcvF = zzbxl.readBytes();
                        continue;
                    case 152:
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 0:
                            case 1:
                            case 2:
                                this.zzcvH = zzaes;
                                break;
                            default:
                                continue;
                        }
                    case 160:
                        int zzb2 = zzbxw.zzb(zzbxl, 160);
                        int length2 = this.zzcvI == null ? 0 : this.zzcvI.length;
                        int[] iArr = new int[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzcvI, 0, iArr, 0, length2);
                        }
                        while (length2 < iArr.length - 1) {
                            iArr[length2] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length2++;
                        }
                        iArr[length2] = zzbxl.zzaes();
                        this.zzcvI = iArr;
                        continue;
                    case 162:
                        int zzra = zzbxl.zzra(zzbxl.zzaex());
                        int position = zzbxl.getPosition();
                        int i = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i++;
                        }
                        zzbxl.zzrc(position);
                        int length3 = this.zzcvI == null ? 0 : this.zzcvI.length;
                        int[] iArr2 = new int[(i + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzcvI, 0, iArr2, 0, length3);
                        }
                        while (length3 < iArr2.length) {
                            iArr2[length3] = zzbxl.zzaes();
                            length3++;
                        }
                        this.zzcvI = iArr2;
                        zzbxl.zzrb(zzra);
                        continue;
                    case 168:
                        this.zzcvt = zzbxl.zzaer();
                        continue;
                    case 176:
                        this.zzcvJ = zzbxl.zzaer();
                        continue;
                    case 186:
                        if (this.zzcvK == null) {
                            this.zzcvK = new zzf();
                        }
                        zzbxt = this.zzcvK;
                        break;
                    case 194:
                        this.zzcvG = zzbxl.readString();
                        continue;
                    default:
                        if (!super.zza(zzbxl, zzaeo)) {
                            return this;
                        }
                        continue;
                }
                zzbxl.zza(zzbxt);
            }
        }

        public /* synthetic */ zzbxn zzaeH() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        public /* synthetic */ zzbxt zzaeI() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        public zzd zzafb() {
            this.zzcvr = 0;
            this.zzcvs = 0;
            this.zzcvt = 0;
            this.tag = "";
            this.zzcvu = 0;
            this.zzri = 0;
            this.zzced = false;
            this.zzcvv = zze.zzafd();
            this.zzcvw = zzbxw.zzcvd;
            this.zzcvx = null;
            this.zzcvy = zzbxw.zzcvd;
            this.zzcvz = "";
            this.zzcvA = "";
            this.zzcvB = null;
            this.zzcvC = "";
            this.zzcvD = 180000;
            this.zzcvE = null;
            this.zzcvF = zzbxw.zzcvd;
            this.zzcvG = "";
            this.zzcvH = 0;
            this.zzcvI = zzbxw.zzcuW;
            this.zzcvJ = 0;
            this.zzcvK = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzafc */
        public zzd clone() {
            try {
                zzd zzd = (zzd) super.clone();
                if (this.zzcvv != null && this.zzcvv.length > 0) {
                    zzd.zzcvv = new zze[this.zzcvv.length];
                    for (int i = 0; i < this.zzcvv.length; i++) {
                        if (this.zzcvv[i] != null) {
                            zzd.zzcvv[i] = (zze) this.zzcvv[i].clone();
                        }
                    }
                }
                if (this.zzcvx != null) {
                    zzd.zzcvx = (zzb) this.zzcvx.clone();
                }
                if (this.zzcvB != null) {
                    zzd.zzcvB = (zza) this.zzcvB.clone();
                }
                if (this.zzcvE != null) {
                    zzd.zzcvE = (zzc) this.zzcvE.clone();
                }
                if (this.zzcvI != null && this.zzcvI.length > 0) {
                    zzd.zzcvI = (int[]) this.zzcvI.clone();
                }
                if (this.zzcvK != null) {
                    zzd.zzcvK = (zzf) this.zzcvK.clone();
                }
                return zzd;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcvr != 0) {
                zzu += zzbxm.zzf(1, this.zzcvr);
            }
            if (this.tag != null && !this.tag.equals("")) {
                zzu += zzbxm.zzr(2, this.tag);
            }
            if (this.zzcvv != null && this.zzcvv.length > 0) {
                int i = zzu;
                for (zze zze : this.zzcvv) {
                    if (zze != null) {
                        i += zzbxm.zzc(3, (zzbxt) zze);
                    }
                }
                zzu = i;
            }
            if (!Arrays.equals(this.zzcvw, zzbxw.zzcvd)) {
                zzu += zzbxm.zzc(4, this.zzcvw);
            }
            if (!Arrays.equals(this.zzcvy, zzbxw.zzcvd)) {
                zzu += zzbxm.zzc(6, this.zzcvy);
            }
            if (this.zzcvB != null) {
                zzu += zzbxm.zzc(7, (zzbxt) this.zzcvB);
            }
            if (this.zzcvz != null && !this.zzcvz.equals("")) {
                zzu += zzbxm.zzr(8, this.zzcvz);
            }
            if (this.zzcvx != null) {
                zzu += zzbxm.zzc(9, (zzbxt) this.zzcvx);
            }
            if (this.zzced) {
                zzu += zzbxm.zzh(10, this.zzced);
            }
            if (this.zzcvu != 0) {
                zzu += zzbxm.zzL(11, this.zzcvu);
            }
            if (this.zzri != 0) {
                zzu += zzbxm.zzL(12, this.zzri);
            }
            if (this.zzcvA != null && !this.zzcvA.equals("")) {
                zzu += zzbxm.zzr(13, this.zzcvA);
            }
            if (this.zzcvC != null && !this.zzcvC.equals("")) {
                zzu += zzbxm.zzr(14, this.zzcvC);
            }
            if (this.zzcvD != 180000) {
                zzu += zzbxm.zzh(15, this.zzcvD);
            }
            if (this.zzcvE != null) {
                zzu += zzbxm.zzc(16, (zzbxt) this.zzcvE);
            }
            if (this.zzcvs != 0) {
                zzu += zzbxm.zzf(17, this.zzcvs);
            }
            if (!Arrays.equals(this.zzcvF, zzbxw.zzcvd)) {
                zzu += zzbxm.zzc(18, this.zzcvF);
            }
            if (this.zzcvH != 0) {
                zzu += zzbxm.zzL(19, this.zzcvH);
            }
            if (this.zzcvI != null && this.zzcvI.length > 0) {
                int i2 = 0;
                for (int zzrg : this.zzcvI) {
                    i2 += zzbxm.zzrg(zzrg);
                }
                zzu = zzu + i2 + (2 * this.zzcvI.length);
            }
            if (this.zzcvt != 0) {
                zzu += zzbxm.zzf(21, this.zzcvt);
            }
            if (this.zzcvJ != 0) {
                zzu += zzbxm.zzf(22, this.zzcvJ);
            }
            if (this.zzcvK != null) {
                zzu += zzbxm.zzc(23, (zzbxt) this.zzcvK);
            }
            return (this.zzcvG == null || this.zzcvG.equals("")) ? zzu : zzu + zzbxm.zzr(24, this.zzcvG);
        }
    }

    public static final class zze extends zzbxn<zze> implements Cloneable {
        private static volatile zze[] zzcvL;
        public String value;
        public String zzaB;

        public zze() {
            zzafe();
        }

        public static zze[] zzafd() {
            if (zzcvL == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzcvL == null) {
                        zzcvL = new zze[0];
                    }
                }
            }
            return zzcvL;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.zzaB == null) {
                if (zze.zzaB != null) {
                    return false;
                }
            } else if (!this.zzaB.equals(zze.zzaB)) {
                return false;
            }
            if (this.value == null) {
                if (zze.value != null) {
                    return false;
                }
            } else if (!this.value.equals(zze.value)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zze.zzcuI == null || zze.zzcuI.isEmpty() : this.zzcuI.equals(zze.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + (this.zzaB == null ? 0 : this.zzaB.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode()));
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaB != null && !this.zzaB.equals("")) {
                zzbxm.zzq(1, this.zzaB);
            }
            if (this.value != null && !this.value.equals("")) {
                zzbxm.zzq(2, this.value);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaT */
        public zze zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    this.zzaB = zzbxl.readString();
                } else if (zzaeo == 18) {
                    this.value = zzbxl.readString();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        public /* synthetic */ zzbxn zzaeH() throws CloneNotSupportedException {
            return (zze) clone();
        }

        public /* synthetic */ zzbxt zzaeI() throws CloneNotSupportedException {
            return (zze) clone();
        }

        public zze zzafe() {
            this.zzaB = "";
            this.value = "";
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzaff */
        public zze clone() {
            try {
                return (zze) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaB != null && !this.zzaB.equals("")) {
                zzu += zzbxm.zzr(1, this.zzaB);
            }
            return (this.value == null || this.value.equals("")) ? zzu : zzu + zzbxm.zzr(2, this.value);
        }
    }

    public static final class zzf extends zzbxn<zzf> implements Cloneable {
        public int zzcvM;
        public int zzcvN;

        public zzf() {
            zzafg();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (this.zzcvM == zzf.zzcvM && this.zzcvN == zzf.zzcvN) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzf.zzcuI == null || zzf.zzcuI.isEmpty() : this.zzcuI.equals(zzf.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            return (31 * (((((MetaDo.META_OFFSETWINDOWORG + getClass().getName().hashCode()) * 31) + this.zzcvM) * 31) + this.zzcvN)) + ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode());
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvM != -1) {
                zzbxm.zzJ(1, this.zzcvM);
            }
            if (this.zzcvN != 0) {
                zzbxm.zzJ(2, this.zzcvN);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaU */
        public zzf zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo != 0) {
                    if (zzaeo == 8) {
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case -1:
                            case 0:
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
                                this.zzcvM = zzaes;
                                break;
                        }
                    } else if (zzaeo == 16) {
                        int zzaes2 = zzbxl.zzaes();
                        if (zzaes2 != 100) {
                            switch (zzaes2) {
                                case 0:
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
                                    break;
                            }
                        }
                        this.zzcvN = zzaes2;
                    } else if (!super.zza(zzbxl, zzaeo)) {
                        return this;
                    }
                } else {
                    return this;
                }
            }
        }

        public /* synthetic */ zzbxn zzaeH() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        public /* synthetic */ zzbxt zzaeI() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        public zzf zzafg() {
            this.zzcvM = -1;
            this.zzcvN = 0;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzafh */
        public zzf clone() {
            try {
                return (zzf) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcvM != -1) {
                zzu += zzbxm.zzL(1, this.zzcvM);
            }
            return this.zzcvN != 0 ? zzu + zzbxm.zzL(2, this.zzcvN) : zzu;
        }
    }
}
