package com.google.android.gms.internal;

import com.itextpdf.text.pdf.codec.TIFFConstants;
import com.itextpdf.xmp.XMPError;
import java.io.IOException;

public interface zzag {

    public static final class zza extends zzbxn<zza> {
        public String zzaN = null;
        public String zzaP = null;
        public String zzaQ = null;
        public String zzaR = null;
        public Long zzbA = null;
        public Long zzbB = null;
        public Long zzbC = null;
        public zzb zzbD;
        public Long zzbE = null;
        public Long zzbF = null;
        public Long zzbG = null;
        public Long zzbH = null;
        public Long zzbI = null;
        public Long zzbJ = null;
        public Integer zzbK = null;
        public Integer zzbL = null;
        public Long zzbM = null;
        public Long zzbN = null;
        public Long zzbO = null;
        public Long zzbP = null;
        public Long zzbQ = null;
        public Integer zzbR = null;
        public C0036zza zzbS;
        public C0036zza[] zzbT = C0036zza.zzv();
        public zzb zzbU;
        public Long zzbV = null;
        public String zzbW = null;
        public Integer zzbX = null;
        public Boolean zzbY = null;
        public String zzbZ = null;
        public String zzba = null;
        public String zzbb = null;
        public Long zzbc = null;
        public Long zzbd = null;
        public Long zzbe = null;
        public Long zzbf = null;
        public Long zzbg = null;
        public Long zzbh = null;
        public Long zzbi = null;
        public Long zzbj = null;
        public Long zzbk = null;
        public Long zzbl = null;
        public String zzbm = null;
        public Long zzbn = null;
        public Long zzbo = null;
        public Long zzbp = null;
        public Long zzbq = null;
        public Long zzbr = null;
        public Long zzbs = null;
        public Long zzbt = null;
        public Long zzbu = null;
        public Long zzbv = null;
        public String zzbw = null;
        public Long zzbx = null;
        public Long zzby = null;
        public Long zzbz = null;
        public Long zzca = null;
        public zze zzcb;

        /* renamed from: com.google.android.gms.internal.zzag$zza$zza  reason: collision with other inner class name */
        public static final class C0036zza extends zzbxn<C0036zza> {
            private static volatile C0036zza[] zzcc;
            public Long zzbn = null;
            public Long zzbo = null;
            public Long zzcd = null;
            public Long zzce = null;
            public Long zzcf = null;
            public Long zzcg = null;
            public Integer zzch = null;
            public Long zzci = null;
            public Long zzcj = null;
            public Long zzck = null;
            public Integer zzcl = null;
            public Long zzcm = null;

            public C0036zza() {
                this.zzcuR = -1;
            }

            public static C0036zza[] zzv() {
                if (zzcc == null) {
                    synchronized (zzbxr.zzcuQ) {
                        if (zzcc == null) {
                            zzcc = new C0036zza[0];
                        }
                    }
                }
                return zzcc;
            }

            public void zza(zzbxm zzbxm) throws IOException {
                if (this.zzbn != null) {
                    zzbxm.zzb(1, this.zzbn.longValue());
                }
                if (this.zzbo != null) {
                    zzbxm.zzb(2, this.zzbo.longValue());
                }
                if (this.zzcd != null) {
                    zzbxm.zzb(3, this.zzcd.longValue());
                }
                if (this.zzce != null) {
                    zzbxm.zzb(4, this.zzce.longValue());
                }
                if (this.zzcf != null) {
                    zzbxm.zzb(5, this.zzcf.longValue());
                }
                if (this.zzcg != null) {
                    zzbxm.zzb(6, this.zzcg.longValue());
                }
                if (this.zzch != null) {
                    zzbxm.zzJ(7, this.zzch.intValue());
                }
                if (this.zzci != null) {
                    zzbxm.zzb(8, this.zzci.longValue());
                }
                if (this.zzcj != null) {
                    zzbxm.zzb(9, this.zzcj.longValue());
                }
                if (this.zzck != null) {
                    zzbxm.zzb(10, this.zzck.longValue());
                }
                if (this.zzcl != null) {
                    zzbxm.zzJ(11, this.zzcl.intValue());
                }
                if (this.zzcm != null) {
                    zzbxm.zzb(12, this.zzcm.longValue());
                }
                super.zza(zzbxm);
            }

            /* renamed from: zzg */
            public C0036zza zzb(zzbxl zzbxl) throws IOException {
                while (true) {
                    int zzaeo = zzbxl.zzaeo();
                    switch (zzaeo) {
                        case 0:
                            return this;
                        case 8:
                            this.zzbn = Long.valueOf(zzbxl.zzaer());
                            break;
                        case 16:
                            this.zzbo = Long.valueOf(zzbxl.zzaer());
                            break;
                        case 24:
                            this.zzcd = Long.valueOf(zzbxl.zzaer());
                            break;
                        case 32:
                            this.zzce = Long.valueOf(zzbxl.zzaer());
                            break;
                        case 40:
                            this.zzcf = Long.valueOf(zzbxl.zzaer());
                            break;
                        case 48:
                            this.zzcg = Long.valueOf(zzbxl.zzaer());
                            break;
                        case 56:
                            int zzaes = zzbxl.zzaes();
                            if (zzaes != 1000) {
                                switch (zzaes) {
                                    case 0:
                                    case 1:
                                    case 2:
                                        break;
                                }
                            }
                            this.zzch = Integer.valueOf(zzaes);
                            break;
                        case 64:
                            this.zzci = Long.valueOf(zzbxl.zzaer());
                            break;
                        case 72:
                            this.zzcj = Long.valueOf(zzbxl.zzaer());
                            break;
                        case 80:
                            this.zzck = Long.valueOf(zzbxl.zzaer());
                            break;
                        case 88:
                            int zzaes2 = zzbxl.zzaes();
                            if (zzaes2 != 1000) {
                                switch (zzaes2) {
                                    case 0:
                                    case 1:
                                    case 2:
                                        break;
                                }
                            }
                            this.zzcl = Integer.valueOf(zzaes2);
                            break;
                        case 96:
                            this.zzcm = Long.valueOf(zzbxl.zzaer());
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
                if (this.zzbn != null) {
                    zzu += zzbxm.zzf(1, this.zzbn.longValue());
                }
                if (this.zzbo != null) {
                    zzu += zzbxm.zzf(2, this.zzbo.longValue());
                }
                if (this.zzcd != null) {
                    zzu += zzbxm.zzf(3, this.zzcd.longValue());
                }
                if (this.zzce != null) {
                    zzu += zzbxm.zzf(4, this.zzce.longValue());
                }
                if (this.zzcf != null) {
                    zzu += zzbxm.zzf(5, this.zzcf.longValue());
                }
                if (this.zzcg != null) {
                    zzu += zzbxm.zzf(6, this.zzcg.longValue());
                }
                if (this.zzch != null) {
                    zzu += zzbxm.zzL(7, this.zzch.intValue());
                }
                if (this.zzci != null) {
                    zzu += zzbxm.zzf(8, this.zzci.longValue());
                }
                if (this.zzcj != null) {
                    zzu += zzbxm.zzf(9, this.zzcj.longValue());
                }
                if (this.zzck != null) {
                    zzu += zzbxm.zzf(10, this.zzck.longValue());
                }
                if (this.zzcl != null) {
                    zzu += zzbxm.zzL(11, this.zzcl.intValue());
                }
                return this.zzcm != null ? zzu + zzbxm.zzf(12, this.zzcm.longValue()) : zzu;
            }
        }

        public static final class zzb extends zzbxn<zzb> {
            public Long zzbP = null;
            public Long zzbQ = null;
            public Long zzcn = null;

            public zzb() {
                this.zzcuR = -1;
            }

            public void zza(zzbxm zzbxm) throws IOException {
                if (this.zzbP != null) {
                    zzbxm.zzb(1, this.zzbP.longValue());
                }
                if (this.zzbQ != null) {
                    zzbxm.zzb(2, this.zzbQ.longValue());
                }
                if (this.zzcn != null) {
                    zzbxm.zzb(3, this.zzcn.longValue());
                }
                super.zza(zzbxm);
            }

            /* renamed from: zzh */
            public zzb zzb(zzbxl zzbxl) throws IOException {
                while (true) {
                    int zzaeo = zzbxl.zzaeo();
                    if (zzaeo == 0) {
                        return this;
                    }
                    if (zzaeo == 8) {
                        this.zzbP = Long.valueOf(zzbxl.zzaer());
                    } else if (zzaeo == 16) {
                        this.zzbQ = Long.valueOf(zzbxl.zzaer());
                    } else if (zzaeo == 24) {
                        this.zzcn = Long.valueOf(zzbxl.zzaer());
                    } else if (!super.zza(zzbxl, zzaeo)) {
                        return this;
                    }
                }
            }

            /* access modifiers changed from: protected */
            public int zzu() {
                int zzu = super.zzu();
                if (this.zzbP != null) {
                    zzu += zzbxm.zzf(1, this.zzbP.longValue());
                }
                if (this.zzbQ != null) {
                    zzu += zzbxm.zzf(2, this.zzbQ.longValue());
                }
                return this.zzcn != null ? zzu + zzbxm.zzf(3, this.zzcn.longValue()) : zzu;
            }
        }

        public zza() {
            this.zzcuR = -1;
        }

        public static zza zzd(byte[] bArr) throws zzbxs {
            return (zza) zzbxt.zza(new zza(), bArr);
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzbb != null) {
                zzbxm.zzq(1, this.zzbb);
            }
            if (this.zzba != null) {
                zzbxm.zzq(2, this.zzba);
            }
            if (this.zzbc != null) {
                zzbxm.zzb(3, this.zzbc.longValue());
            }
            if (this.zzbd != null) {
                zzbxm.zzb(4, this.zzbd.longValue());
            }
            if (this.zzbe != null) {
                zzbxm.zzb(5, this.zzbe.longValue());
            }
            if (this.zzbf != null) {
                zzbxm.zzb(6, this.zzbf.longValue());
            }
            if (this.zzbg != null) {
                zzbxm.zzb(7, this.zzbg.longValue());
            }
            if (this.zzbh != null) {
                zzbxm.zzb(8, this.zzbh.longValue());
            }
            if (this.zzbi != null) {
                zzbxm.zzb(9, this.zzbi.longValue());
            }
            if (this.zzbj != null) {
                zzbxm.zzb(10, this.zzbj.longValue());
            }
            if (this.zzbk != null) {
                zzbxm.zzb(11, this.zzbk.longValue());
            }
            if (this.zzbl != null) {
                zzbxm.zzb(12, this.zzbl.longValue());
            }
            if (this.zzbm != null) {
                zzbxm.zzq(13, this.zzbm);
            }
            if (this.zzbn != null) {
                zzbxm.zzb(14, this.zzbn.longValue());
            }
            if (this.zzbo != null) {
                zzbxm.zzb(15, this.zzbo.longValue());
            }
            if (this.zzbp != null) {
                zzbxm.zzb(16, this.zzbp.longValue());
            }
            if (this.zzbq != null) {
                zzbxm.zzb(17, this.zzbq.longValue());
            }
            if (this.zzbr != null) {
                zzbxm.zzb(18, this.zzbr.longValue());
            }
            if (this.zzbs != null) {
                zzbxm.zzb(19, this.zzbs.longValue());
            }
            if (this.zzbt != null) {
                zzbxm.zzb(20, this.zzbt.longValue());
            }
            if (this.zzbV != null) {
                zzbxm.zzb(21, this.zzbV.longValue());
            }
            if (this.zzbu != null) {
                zzbxm.zzb(22, this.zzbu.longValue());
            }
            if (this.zzbv != null) {
                zzbxm.zzb(23, this.zzbv.longValue());
            }
            if (this.zzbW != null) {
                zzbxm.zzq(24, this.zzbW);
            }
            if (this.zzca != null) {
                zzbxm.zzb(25, this.zzca.longValue());
            }
            if (this.zzbX != null) {
                zzbxm.zzJ(26, this.zzbX.intValue());
            }
            if (this.zzaN != null) {
                zzbxm.zzq(27, this.zzaN);
            }
            if (this.zzbY != null) {
                zzbxm.zzg(28, this.zzbY.booleanValue());
            }
            if (this.zzbw != null) {
                zzbxm.zzq(29, this.zzbw);
            }
            if (this.zzbZ != null) {
                zzbxm.zzq(30, this.zzbZ);
            }
            if (this.zzbx != null) {
                zzbxm.zzb(31, this.zzbx.longValue());
            }
            if (this.zzby != null) {
                zzbxm.zzb(32, this.zzby.longValue());
            }
            if (this.zzbz != null) {
                zzbxm.zzb(33, this.zzbz.longValue());
            }
            if (this.zzaP != null) {
                zzbxm.zzq(34, this.zzaP);
            }
            if (this.zzbA != null) {
                zzbxm.zzb(35, this.zzbA.longValue());
            }
            if (this.zzbB != null) {
                zzbxm.zzb(36, this.zzbB.longValue());
            }
            if (this.zzbC != null) {
                zzbxm.zzb(37, this.zzbC.longValue());
            }
            if (this.zzbD != null) {
                zzbxm.zza(38, (zzbxt) this.zzbD);
            }
            if (this.zzbE != null) {
                zzbxm.zzb(39, this.zzbE.longValue());
            }
            if (this.zzbF != null) {
                zzbxm.zzb(40, this.zzbF.longValue());
            }
            if (this.zzbG != null) {
                zzbxm.zzb(41, this.zzbG.longValue());
            }
            if (this.zzbH != null) {
                zzbxm.zzb(42, this.zzbH.longValue());
            }
            if (this.zzbT != null && this.zzbT.length > 0) {
                for (C0036zza zza : this.zzbT) {
                    if (zza != null) {
                        zzbxm.zza(43, (zzbxt) zza);
                    }
                }
            }
            if (this.zzbI != null) {
                zzbxm.zzb(44, this.zzbI.longValue());
            }
            if (this.zzbJ != null) {
                zzbxm.zzb(45, this.zzbJ.longValue());
            }
            if (this.zzaQ != null) {
                zzbxm.zzq(46, this.zzaQ);
            }
            if (this.zzaR != null) {
                zzbxm.zzq(47, this.zzaR);
            }
            if (this.zzbK != null) {
                zzbxm.zzJ(48, this.zzbK.intValue());
            }
            if (this.zzbL != null) {
                zzbxm.zzJ(49, this.zzbL.intValue());
            }
            if (this.zzbS != null) {
                zzbxm.zza(50, (zzbxt) this.zzbS);
            }
            if (this.zzbM != null) {
                zzbxm.zzb(51, this.zzbM.longValue());
            }
            if (this.zzbN != null) {
                zzbxm.zzb(52, this.zzbN.longValue());
            }
            if (this.zzbO != null) {
                zzbxm.zzb(53, this.zzbO.longValue());
            }
            if (this.zzbP != null) {
                zzbxm.zzb(54, this.zzbP.longValue());
            }
            if (this.zzbQ != null) {
                zzbxm.zzb(55, this.zzbQ.longValue());
            }
            if (this.zzbR != null) {
                zzbxm.zzJ(56, this.zzbR.intValue());
            }
            if (this.zzbU != null) {
                zzbxm.zza(57, (zzbxt) this.zzbU);
            }
            if (this.zzcb != null) {
                zzbxm.zza((int) XMPError.BADXML, (zzbxt) this.zzcb);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzf */
        public zza zzb(zzbxl zzbxl) throws IOException {
            zzbxt zzbxt;
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        return this;
                    case 10:
                        this.zzbb = zzbxl.readString();
                        continue;
                    case 18:
                        this.zzba = zzbxl.readString();
                        continue;
                    case 24:
                        this.zzbc = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 32:
                        this.zzbd = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 40:
                        this.zzbe = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 48:
                        this.zzbf = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 56:
                        this.zzbg = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 64:
                        this.zzbh = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 72:
                        this.zzbi = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 80:
                        this.zzbj = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 88:
                        this.zzbk = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 96:
                        this.zzbl = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 106:
                        this.zzbm = zzbxl.readString();
                        continue;
                    case 112:
                        this.zzbn = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 120:
                        this.zzbo = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 128:
                        this.zzbp = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 136:
                        this.zzbq = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 144:
                        this.zzbr = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 152:
                        this.zzbs = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 160:
                        this.zzbt = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 168:
                        this.zzbV = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 176:
                        this.zzbu = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 184:
                        this.zzbv = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 194:
                        this.zzbW = zzbxl.readString();
                        continue;
                    case 200:
                        this.zzca = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 208:
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                this.zzbX = Integer.valueOf(zzaes);
                                break;
                            default:
                                continue;
                        }
                    case 218:
                        this.zzaN = zzbxl.readString();
                        continue;
                    case 224:
                        this.zzbY = Boolean.valueOf(zzbxl.zzaeu());
                        continue;
                    case 234:
                        this.zzbw = zzbxl.readString();
                        continue;
                    case 242:
                        this.zzbZ = zzbxl.readString();
                        continue;
                    case 248:
                        this.zzbx = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 256:
                        this.zzby = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 264:
                        this.zzbz = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case TIFFConstants.TIFFTAG_ORIENTATION /*274*/:
                        this.zzaP = zzbxl.readString();
                        continue;
                    case TIFFConstants.TIFFTAG_MINSAMPLEVALUE /*280*/:
                        this.zzbA = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case TIFFConstants.TIFFTAG_FREEOFFSETS /*288*/:
                        this.zzbB = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case TIFFConstants.TIFFTAG_RESOLUTIONUNIT /*296*/:
                        this.zzbC = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case TIFFConstants.TIFFTAG_DATETIME /*306*/:
                        if (this.zzbD == null) {
                            this.zzbD = new zzb();
                        }
                        zzbxt = this.zzbD;
                        break;
                    case 312:
                        this.zzbE = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case TIFFConstants.TIFFTAG_COLORMAP /*320*/:
                        this.zzbF = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case TIFFConstants.TIFFTAG_CONSECUTIVEBADFAXLINES /*328*/:
                        this.zzbG = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case TIFFConstants.TIFFTAG_DOTRANGE /*336*/:
                        this.zzbH = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 346:
                        int zzb2 = zzbxw.zzb(zzbxl, 346);
                        int length = this.zzbT == null ? 0 : this.zzbT.length;
                        C0036zza[] zzaArr = new C0036zza[(zzb2 + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbT, 0, zzaArr, 0, length);
                        }
                        while (length < zzaArr.length - 1) {
                            zzaArr[length] = new C0036zza();
                            zzbxl.zza(zzaArr[length]);
                            zzbxl.zzaeo();
                            length++;
                        }
                        zzaArr[length] = new C0036zza();
                        zzbxl.zza(zzaArr[length]);
                        this.zzbT = zzaArr;
                        continue;
                    case 352:
                        this.zzbI = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 360:
                        this.zzbJ = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 370:
                        this.zzaQ = zzbxl.readString();
                        continue;
                    case 378:
                        this.zzaR = zzbxl.readString();
                        continue;
                    case 384:
                        int zzaes2 = zzbxl.zzaes();
                        if (zzaes2 != 1000) {
                            switch (zzaes2) {
                                case 0:
                                case 1:
                                case 2:
                                    break;
                                default:
                                    continue;
                            }
                        }
                        this.zzbK = Integer.valueOf(zzaes2);
                        break;
                    case 392:
                        int zzaes3 = zzbxl.zzaes();
                        if (zzaes3 != 1000) {
                            switch (zzaes3) {
                                case 0:
                                case 1:
                                case 2:
                                    break;
                                default:
                                    continue;
                            }
                        }
                        this.zzbL = Integer.valueOf(zzaes3);
                        break;
                    case 402:
                        if (this.zzbS == null) {
                            this.zzbS = new C0036zza();
                        }
                        zzbxt = this.zzbS;
                        break;
                    case 408:
                        this.zzbM = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 416:
                        this.zzbN = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 424:
                        this.zzbO = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 432:
                        this.zzbP = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 440:
                        this.zzbQ = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 448:
                        int zzaes4 = zzbxl.zzaes();
                        if (zzaes4 != 1000) {
                            switch (zzaes4) {
                                case 0:
                                case 1:
                                case 2:
                                    break;
                                default:
                                    continue;
                            }
                        }
                        this.zzbR = Integer.valueOf(zzaes4);
                        break;
                    case 458:
                        if (this.zzbU == null) {
                            this.zzbU = new zzb();
                        }
                        zzbxt = this.zzbU;
                        break;
                    case 1610:
                        if (this.zzcb == null) {
                            this.zzcb = new zze();
                        }
                        zzbxt = this.zzcb;
                        break;
                    default:
                        if (!super.zza(zzbxl, zzaeo)) {
                            return this;
                        }
                        continue;
                }
                zzbxl.zza(zzbxt);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbb != null) {
                zzu += zzbxm.zzr(1, this.zzbb);
            }
            if (this.zzba != null) {
                zzu += zzbxm.zzr(2, this.zzba);
            }
            if (this.zzbc != null) {
                zzu += zzbxm.zzf(3, this.zzbc.longValue());
            }
            if (this.zzbd != null) {
                zzu += zzbxm.zzf(4, this.zzbd.longValue());
            }
            if (this.zzbe != null) {
                zzu += zzbxm.zzf(5, this.zzbe.longValue());
            }
            if (this.zzbf != null) {
                zzu += zzbxm.zzf(6, this.zzbf.longValue());
            }
            if (this.zzbg != null) {
                zzu += zzbxm.zzf(7, this.zzbg.longValue());
            }
            if (this.zzbh != null) {
                zzu += zzbxm.zzf(8, this.zzbh.longValue());
            }
            if (this.zzbi != null) {
                zzu += zzbxm.zzf(9, this.zzbi.longValue());
            }
            if (this.zzbj != null) {
                zzu += zzbxm.zzf(10, this.zzbj.longValue());
            }
            if (this.zzbk != null) {
                zzu += zzbxm.zzf(11, this.zzbk.longValue());
            }
            if (this.zzbl != null) {
                zzu += zzbxm.zzf(12, this.zzbl.longValue());
            }
            if (this.zzbm != null) {
                zzu += zzbxm.zzr(13, this.zzbm);
            }
            if (this.zzbn != null) {
                zzu += zzbxm.zzf(14, this.zzbn.longValue());
            }
            if (this.zzbo != null) {
                zzu += zzbxm.zzf(15, this.zzbo.longValue());
            }
            if (this.zzbp != null) {
                zzu += zzbxm.zzf(16, this.zzbp.longValue());
            }
            if (this.zzbq != null) {
                zzu += zzbxm.zzf(17, this.zzbq.longValue());
            }
            if (this.zzbr != null) {
                zzu += zzbxm.zzf(18, this.zzbr.longValue());
            }
            if (this.zzbs != null) {
                zzu += zzbxm.zzf(19, this.zzbs.longValue());
            }
            if (this.zzbt != null) {
                zzu += zzbxm.zzf(20, this.zzbt.longValue());
            }
            if (this.zzbV != null) {
                zzu += zzbxm.zzf(21, this.zzbV.longValue());
            }
            if (this.zzbu != null) {
                zzu += zzbxm.zzf(22, this.zzbu.longValue());
            }
            if (this.zzbv != null) {
                zzu += zzbxm.zzf(23, this.zzbv.longValue());
            }
            if (this.zzbW != null) {
                zzu += zzbxm.zzr(24, this.zzbW);
            }
            if (this.zzca != null) {
                zzu += zzbxm.zzf(25, this.zzca.longValue());
            }
            if (this.zzbX != null) {
                zzu += zzbxm.zzL(26, this.zzbX.intValue());
            }
            if (this.zzaN != null) {
                zzu += zzbxm.zzr(27, this.zzaN);
            }
            if (this.zzbY != null) {
                zzu += zzbxm.zzh(28, this.zzbY.booleanValue());
            }
            if (this.zzbw != null) {
                zzu += zzbxm.zzr(29, this.zzbw);
            }
            if (this.zzbZ != null) {
                zzu += zzbxm.zzr(30, this.zzbZ);
            }
            if (this.zzbx != null) {
                zzu += zzbxm.zzf(31, this.zzbx.longValue());
            }
            if (this.zzby != null) {
                zzu += zzbxm.zzf(32, this.zzby.longValue());
            }
            if (this.zzbz != null) {
                zzu += zzbxm.zzf(33, this.zzbz.longValue());
            }
            if (this.zzaP != null) {
                zzu += zzbxm.zzr(34, this.zzaP);
            }
            if (this.zzbA != null) {
                zzu += zzbxm.zzf(35, this.zzbA.longValue());
            }
            if (this.zzbB != null) {
                zzu += zzbxm.zzf(36, this.zzbB.longValue());
            }
            if (this.zzbC != null) {
                zzu += zzbxm.zzf(37, this.zzbC.longValue());
            }
            if (this.zzbD != null) {
                zzu += zzbxm.zzc(38, (zzbxt) this.zzbD);
            }
            if (this.zzbE != null) {
                zzu += zzbxm.zzf(39, this.zzbE.longValue());
            }
            if (this.zzbF != null) {
                zzu += zzbxm.zzf(40, this.zzbF.longValue());
            }
            if (this.zzbG != null) {
                zzu += zzbxm.zzf(41, this.zzbG.longValue());
            }
            if (this.zzbH != null) {
                zzu += zzbxm.zzf(42, this.zzbH.longValue());
            }
            if (this.zzbT != null && this.zzbT.length > 0) {
                for (C0036zza zza : this.zzbT) {
                    if (zza != null) {
                        zzu += zzbxm.zzc(43, (zzbxt) zza);
                    }
                }
            }
            if (this.zzbI != null) {
                zzu += zzbxm.zzf(44, this.zzbI.longValue());
            }
            if (this.zzbJ != null) {
                zzu += zzbxm.zzf(45, this.zzbJ.longValue());
            }
            if (this.zzaQ != null) {
                zzu += zzbxm.zzr(46, this.zzaQ);
            }
            if (this.zzaR != null) {
                zzu += zzbxm.zzr(47, this.zzaR);
            }
            if (this.zzbK != null) {
                zzu += zzbxm.zzL(48, this.zzbK.intValue());
            }
            if (this.zzbL != null) {
                zzu += zzbxm.zzL(49, this.zzbL.intValue());
            }
            if (this.zzbS != null) {
                zzu += zzbxm.zzc(50, (zzbxt) this.zzbS);
            }
            if (this.zzbM != null) {
                zzu += zzbxm.zzf(51, this.zzbM.longValue());
            }
            if (this.zzbN != null) {
                zzu += zzbxm.zzf(52, this.zzbN.longValue());
            }
            if (this.zzbO != null) {
                zzu += zzbxm.zzf(53, this.zzbO.longValue());
            }
            if (this.zzbP != null) {
                zzu += zzbxm.zzf(54, this.zzbP.longValue());
            }
            if (this.zzbQ != null) {
                zzu += zzbxm.zzf(55, this.zzbQ.longValue());
            }
            if (this.zzbR != null) {
                zzu += zzbxm.zzL(56, this.zzbR.intValue());
            }
            if (this.zzbU != null) {
                zzu += zzbxm.zzc(57, (zzbxt) this.zzbU);
            }
            return this.zzcb != null ? zzu + zzbxm.zzc((int) XMPError.BADXML, (zzbxt) this.zzcb) : zzu;
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        public Long zzco = null;
        public Integer zzcp = null;
        public Boolean zzcq = null;
        public int[] zzcr = zzbxw.zzcuW;
        public Long zzcs = null;

        public zzb() {
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzco != null) {
                zzbxm.zzb(1, this.zzco.longValue());
            }
            if (this.zzcp != null) {
                zzbxm.zzJ(2, this.zzcp.intValue());
            }
            if (this.zzcq != null) {
                zzbxm.zzg(3, this.zzcq.booleanValue());
            }
            if (this.zzcr != null && this.zzcr.length > 0) {
                for (int zzJ : this.zzcr) {
                    zzbxm.zzJ(4, zzJ);
                }
            }
            if (this.zzcs != null) {
                zzbxm.zza(5, this.zzcs.longValue());
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzi */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 8) {
                    this.zzco = Long.valueOf(zzbxl.zzaer());
                } else if (zzaeo == 16) {
                    this.zzcp = Integer.valueOf(zzbxl.zzaes());
                } else if (zzaeo == 24) {
                    this.zzcq = Boolean.valueOf(zzbxl.zzaeu());
                } else if (zzaeo == 32) {
                    int zzb = zzbxw.zzb(zzbxl, 32);
                    int length = this.zzcr == null ? 0 : this.zzcr.length;
                    int[] iArr = new int[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzcr, 0, iArr, 0, length);
                    }
                    while (length < iArr.length - 1) {
                        iArr[length] = zzbxl.zzaes();
                        zzbxl.zzaeo();
                        length++;
                    }
                    iArr[length] = zzbxl.zzaes();
                    this.zzcr = iArr;
                } else if (zzaeo == 34) {
                    int zzra = zzbxl.zzra(zzbxl.zzaex());
                    int position = zzbxl.getPosition();
                    int i = 0;
                    while (zzbxl.zzaeC() > 0) {
                        zzbxl.zzaes();
                        i++;
                    }
                    zzbxl.zzrc(position);
                    int length2 = this.zzcr == null ? 0 : this.zzcr.length;
                    int[] iArr2 = new int[(i + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzcr, 0, iArr2, 0, length2);
                    }
                    while (length2 < iArr2.length) {
                        iArr2[length2] = zzbxl.zzaes();
                        length2++;
                    }
                    this.zzcr = iArr2;
                    zzbxl.zzrb(zzra);
                } else if (zzaeo == 40) {
                    this.zzcs = Long.valueOf(zzbxl.zzaeq());
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzco != null) {
                zzu += zzbxm.zzf(1, this.zzco.longValue());
            }
            if (this.zzcp != null) {
                zzu += zzbxm.zzL(2, this.zzcp.intValue());
            }
            if (this.zzcq != null) {
                zzu += zzbxm.zzh(3, this.zzcq.booleanValue());
            }
            if (this.zzcr != null && this.zzcr.length > 0) {
                int i = 0;
                for (int zzrg : this.zzcr) {
                    i += zzbxm.zzrg(zzrg);
                }
                zzu = zzu + i + (1 * this.zzcr.length);
            }
            return this.zzcs != null ? zzu + zzbxm.zze(5, this.zzcs.longValue()) : zzu;
        }
    }

    public static final class zzc extends zzbxn<zzc> {
        public byte[] zzct = null;
        public byte[] zzcu = null;

        public zzc() {
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzct != null) {
                zzbxm.zzb(1, this.zzct);
            }
            if (this.zzcu != null) {
                zzbxm.zzb(2, this.zzcu);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzj */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    this.zzct = zzbxl.readBytes();
                } else if (zzaeo == 18) {
                    this.zzcu = zzbxl.readBytes();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzct != null) {
                zzu += zzbxm.zzc(1, this.zzct);
            }
            return this.zzcu != null ? zzu + zzbxm.zzc(2, this.zzcu) : zzu;
        }
    }

    public static final class zzd extends zzbxn<zzd> {
        public byte[] data = null;
        public byte[] zzcv = null;
        public byte[] zzcw = null;
        public byte[] zzcx = null;

        public zzd() {
            this.zzcuR = -1;
        }

        public static zzd zze(byte[] bArr) throws zzbxs {
            return (zzd) zzbxt.zza(new zzd(), bArr);
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.data != null) {
                zzbxm.zzb(1, this.data);
            }
            if (this.zzcv != null) {
                zzbxm.zzb(2, this.zzcv);
            }
            if (this.zzcw != null) {
                zzbxm.zzb(3, this.zzcw);
            }
            if (this.zzcx != null) {
                zzbxm.zzb(4, this.zzcx);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzk */
        public zzd zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    this.data = zzbxl.readBytes();
                } else if (zzaeo == 18) {
                    this.zzcv = zzbxl.readBytes();
                } else if (zzaeo == 26) {
                    this.zzcw = zzbxl.readBytes();
                } else if (zzaeo == 34) {
                    this.zzcx = zzbxl.readBytes();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.data != null) {
                zzu += zzbxm.zzc(1, this.data);
            }
            if (this.zzcv != null) {
                zzu += zzbxm.zzc(2, this.zzcv);
            }
            if (this.zzcw != null) {
                zzu += zzbxm.zzc(3, this.zzcw);
            }
            return this.zzcx != null ? zzu + zzbxm.zzc(4, this.zzcx) : zzu;
        }
    }

    public static final class zze extends zzbxn<zze> {
        public Long zzco = null;
        public String zzcy = null;
        public byte[] zzcz = null;

        public zze() {
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzco != null) {
                zzbxm.zzb(1, this.zzco.longValue());
            }
            if (this.zzcy != null) {
                zzbxm.zzq(3, this.zzcy);
            }
            if (this.zzcz != null) {
                zzbxm.zzb(4, this.zzcz);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzl */
        public zze zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 8) {
                    this.zzco = Long.valueOf(zzbxl.zzaer());
                } else if (zzaeo == 26) {
                    this.zzcy = zzbxl.readString();
                } else if (zzaeo == 34) {
                    this.zzcz = zzbxl.readBytes();
                } else if (!super.zza(zzbxl, zzaeo)) {
                    return this;
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzco != null) {
                zzu += zzbxm.zzf(1, this.zzco.longValue());
            }
            if (this.zzcy != null) {
                zzu += zzbxm.zzr(3, this.zzcy);
            }
            return this.zzcz != null ? zzu + zzbxm.zzc(4, this.zzcz) : zzu;
        }
    }

    public static final class zzf extends zzbxn<zzf> {
        public byte[][] zzcA = zzbxw.zzcvc;
        public Integer zzcB = null;
        public Integer zzcC = null;
        public byte[] zzcv = null;

        public zzf() {
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcA != null && this.zzcA.length > 0) {
                for (byte[] bArr : this.zzcA) {
                    if (bArr != null) {
                        zzbxm.zzb(1, bArr);
                    }
                }
            }
            if (this.zzcv != null) {
                zzbxm.zzb(2, this.zzcv);
            }
            if (this.zzcB != null) {
                zzbxm.zzJ(3, this.zzcB.intValue());
            }
            if (this.zzcC != null) {
                zzbxm.zzJ(4, this.zzcC.intValue());
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzm */
        public zzf zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                if (zzaeo == 0) {
                    return this;
                }
                if (zzaeo == 10) {
                    int zzb = zzbxw.zzb(zzbxl, 10);
                    int length = this.zzcA == null ? 0 : this.zzcA.length;
                    byte[][] bArr = new byte[(zzb + length)][];
                    if (length != 0) {
                        System.arraycopy(this.zzcA, 0, bArr, 0, length);
                    }
                    while (length < bArr.length - 1) {
                        bArr[length] = zzbxl.readBytes();
                        zzbxl.zzaeo();
                        length++;
                    }
                    bArr[length] = zzbxl.readBytes();
                    this.zzcA = bArr;
                } else if (zzaeo != 18) {
                    if (zzaeo != 24) {
                        if (zzaeo == 32) {
                            int zzaes = zzbxl.zzaes();
                            switch (zzaes) {
                                case 0:
                                case 1:
                                    this.zzcC = Integer.valueOf(zzaes);
                                    break;
                            }
                        } else if (!super.zza(zzbxl, zzaeo)) {
                            return this;
                        }
                    } else {
                        int zzaes2 = zzbxl.zzaes();
                        switch (zzaes2) {
                            case 0:
                            case 1:
                                this.zzcB = Integer.valueOf(zzaes2);
                                break;
                        }
                    }
                } else {
                    this.zzcv = zzbxl.readBytes();
                }
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcA != null && this.zzcA.length > 0) {
                int i = 0;
                int i2 = 0;
                for (byte[] bArr : this.zzcA) {
                    if (bArr != null) {
                        i2++;
                        i += zzbxm.zzai(bArr);
                    }
                }
                zzu = zzu + i + (1 * i2);
            }
            if (this.zzcv != null) {
                zzu += zzbxm.zzc(2, this.zzcv);
            }
            if (this.zzcB != null) {
                zzu += zzbxm.zzL(3, this.zzcB.intValue());
            }
            return this.zzcC != null ? zzu + zzbxm.zzL(4, this.zzcC.intValue()) : zzu;
        }
    }
}
