package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaj;
import com.google.android.gms.internal.zzak;
import com.google.android.gms.tagmanager.zzbo;
import com.google.android.gms.tagmanager.zzdl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzbjf {

    public static class zza {
        private final zzak.zza zzbHT;
        private final Map<String, zzak.zza> zzbMx;

        private zza(Map<String, zzak.zza> map, zzak.zza zza) {
            this.zzbMx = map;
            this.zzbHT = zza;
        }

        public static zzb zzTw() {
            return new zzb();
        }

        public String toString() {
            String valueOf = String.valueOf(zzSW());
            String valueOf2 = String.valueOf(this.zzbHT);
            StringBuilder sb = new StringBuilder(32 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append("Properties: ");
            sb.append(valueOf);
            sb.append(" pushAfterEvaluate: ");
            sb.append(valueOf2);
            return sb.toString();
        }

        public zzak.zza zzRr() {
            return this.zzbHT;
        }

        public Map<String, zzak.zza> zzSW() {
            return Collections.unmodifiableMap(this.zzbMx);
        }

        public void zza(String str, zzak.zza zza) {
            this.zzbMx.put(str, zza);
        }
    }

    public static class zzb {
        private zzak.zza zzbHT;
        private final Map<String, zzak.zza> zzbMx;

        private zzb() {
            this.zzbMx = new HashMap();
        }

        public zza zzTx() {
            return new zza(this.zzbMx, this.zzbHT);
        }

        public zzb zzb(String str, zzak.zza zza) {
            this.zzbMx.put(str, zza);
            return this;
        }

        public zzb zzo(zzak.zza zza) {
            this.zzbHT = zza;
            return this;
        }
    }

    public static class zzc {
        private final String zzavB;
        private final List<zze> zzbMu;
        private final Map<String, List<zza>> zzbMv;
        private final int zzbMw;

        private zzc(List<zze> list, Map<String, List<zza>> map, String str, int i) {
            this.zzbMu = Collections.unmodifiableList(list);
            this.zzbMv = Collections.unmodifiableMap(map);
            this.zzavB = str;
            this.zzbMw = i;
        }

        public static zzd zzTy() {
            return new zzd();
        }

        public String getVersion() {
            return this.zzavB;
        }

        public String toString() {
            String valueOf = String.valueOf(zzSU());
            String valueOf2 = String.valueOf(this.zzbMv);
            StringBuilder sb = new StringBuilder(17 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append("Rules: ");
            sb.append(valueOf);
            sb.append("  Macros: ");
            sb.append(valueOf2);
            return sb.toString();
        }

        public List<zze> zzSU() {
            return this.zzbMu;
        }

        public Map<String, List<zza>> zzTz() {
            return this.zzbMv;
        }
    }

    public static class zzd {
        private String zzavB;
        private final List<zze> zzbMu;
        private final Map<String, List<zza>> zzbMv;
        private int zzbMw;

        private zzd() {
            this.zzbMu = new ArrayList();
            this.zzbMv = new HashMap();
            this.zzavB = "";
            this.zzbMw = 0;
        }

        public zzc zzTA() {
            return new zzc(this.zzbMu, this.zzbMv, this.zzavB, this.zzbMw);
        }

        public zzd zzb(zze zze) {
            this.zzbMu.add(zze);
            return this;
        }

        public zzd zzc(zza zza) {
            String zze = zzdl.zze(zza.zzSW().get(zzai.INSTANCE_NAME.toString()));
            List list = this.zzbMv.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbMv.put(zze, list);
            }
            list.add(zza);
            return this;
        }

        public zzd zzih(String str) {
            this.zzavB = str;
            return this;
        }

        public zzd zznO(int i) {
            this.zzbMw = i;
            return this;
        }
    }

    public static class zze {
        private final List<zza> zzbMA;
        private final List<zza> zzbMB;
        private final List<zza> zzbMC;
        private final List<zza> zzbMz;
        private final List<zza> zzbNh;
        private final List<zza> zzbNi;
        private final List<String> zzbNj;
        private final List<String> zzbNk;
        private final List<String> zzbNl;
        private final List<String> zzbNm;

        private zze(List<zza> list, List<zza> list2, List<zza> list3, List<zza> list4, List<zza> list5, List<zza> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
            this.zzbMz = Collections.unmodifiableList(list);
            this.zzbMA = Collections.unmodifiableList(list2);
            this.zzbMB = Collections.unmodifiableList(list3);
            this.zzbMC = Collections.unmodifiableList(list4);
            this.zzbNh = Collections.unmodifiableList(list5);
            this.zzbNi = Collections.unmodifiableList(list6);
            this.zzbNj = Collections.unmodifiableList(list7);
            this.zzbNk = Collections.unmodifiableList(list8);
            this.zzbNl = Collections.unmodifiableList(list9);
            this.zzbNm = Collections.unmodifiableList(list10);
        }

        public static zzf zzTB() {
            return new zzf();
        }

        public String toString() {
            String valueOf = String.valueOf(zzSY());
            String valueOf2 = String.valueOf(zzSZ());
            String valueOf3 = String.valueOf(zzTa());
            String valueOf4 = String.valueOf(zzTb());
            String valueOf5 = String.valueOf(zzTC());
            String valueOf6 = String.valueOf(zzTD());
            StringBuilder sb = new StringBuilder(102 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length());
            sb.append("Positive predicates: ");
            sb.append(valueOf);
            sb.append("  Negative predicates: ");
            sb.append(valueOf2);
            sb.append("  Add tags: ");
            sb.append(valueOf3);
            sb.append("  Remove tags: ");
            sb.append(valueOf4);
            sb.append("  Add macros: ");
            sb.append(valueOf5);
            sb.append("  Remove macros: ");
            sb.append(valueOf6);
            return sb.toString();
        }

        public List<zza> zzSY() {
            return this.zzbMz;
        }

        public List<zza> zzSZ() {
            return this.zzbMA;
        }

        public List<zza> zzTC() {
            return this.zzbNh;
        }

        public List<zza> zzTD() {
            return this.zzbNi;
        }

        public List<zza> zzTa() {
            return this.zzbMB;
        }

        public List<zza> zzTb() {
            return this.zzbMC;
        }
    }

    public static class zzf {
        private final List<zza> zzbMA;
        private final List<zza> zzbMB;
        private final List<zza> zzbMC;
        private final List<zza> zzbMz;
        private final List<zza> zzbNh;
        private final List<zza> zzbNi;
        private final List<String> zzbNj;
        private final List<String> zzbNk;
        private final List<String> zzbNl;
        private final List<String> zzbNm;

        private zzf() {
            this.zzbMz = new ArrayList();
            this.zzbMA = new ArrayList();
            this.zzbMB = new ArrayList();
            this.zzbMC = new ArrayList();
            this.zzbNh = new ArrayList();
            this.zzbNi = new ArrayList();
            this.zzbNj = new ArrayList();
            this.zzbNk = new ArrayList();
            this.zzbNl = new ArrayList();
            this.zzbNm = new ArrayList();
        }

        public zze zzTE() {
            return new zze(this.zzbMz, this.zzbMA, this.zzbMB, this.zzbMC, this.zzbNh, this.zzbNi, this.zzbNj, this.zzbNk, this.zzbNl, this.zzbNm);
        }

        public zzf zzd(zza zza) {
            this.zzbMz.add(zza);
            return this;
        }

        public zzf zze(zza zza) {
            this.zzbMA.add(zza);
            return this;
        }

        public zzf zzf(zza zza) {
            this.zzbMB.add(zza);
            return this;
        }

        public zzf zzg(zza zza) {
            this.zzbMC.add(zza);
            return this;
        }

        public zzf zzh(zza zza) {
            this.zzbNh.add(zza);
            return this;
        }

        public zzf zzi(zza zza) {
            this.zzbNi.add(zza);
            return this;
        }

        public zzf zzii(String str) {
            this.zzbNl.add(str);
            return this;
        }

        public zzf zzij(String str) {
            this.zzbNm.add(str);
            return this;
        }

        public zzf zzik(String str) {
            this.zzbNj.add(str);
            return this;
        }

        public zzf zzil(String str) {
            this.zzbNk.add(str);
            return this;
        }
    }

    public static class zzg extends Exception {
        public zzg(String str) {
            super(str);
        }
    }

    private static zzak.zza zza(int i, zzaj.zzf zzf2, zzak.zza[] zzaArr, Set<Integer> set) throws zzg {
        if (set.contains(Integer.valueOf(i))) {
            String valueOf = String.valueOf(set);
            StringBuilder sb = new StringBuilder(90 + String.valueOf(valueOf).length());
            sb.append("Value cycle detected.  Current value reference: ");
            sb.append(i);
            sb.append(".  Previous value references: ");
            sb.append(valueOf);
            sb.append(".");
            zzhS(sb.toString());
        }
        zzak.zza zza2 = (zzak.zza) zza(zzf2.zzkF, i, "values");
        if (zzaArr[i] != null) {
            return zzaArr[i];
        }
        zzak.zza zza3 = null;
        set.add(Integer.valueOf(i));
        int i2 = 0;
        switch (zza2.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zza3 = zza2;
                break;
            case 2:
                zzaj.zzh zzn = zzn(zza2);
                zzak.zza zzm = zzm(zza2);
                zzm.zzlu = new zzak.zza[zzn.zzlg.length];
                int[] iArr = zzn.zzlg;
                int length = iArr.length;
                int i3 = 0;
                while (i2 < length) {
                    zzm.zzlu[i3] = zza(iArr[i2], zzf2, zzaArr, set);
                    i2++;
                    i3++;
                }
                zza3 = zzm;
                break;
            case 3:
                zza3 = zzm(zza2);
                zzaj.zzh zzn2 = zzn(zza2);
                if (zzn2.zzlh.length != zzn2.zzli.length) {
                    int length2 = zzn2.zzlh.length;
                    int length3 = zzn2.zzli.length;
                    StringBuilder sb2 = new StringBuilder(58);
                    sb2.append("Uneven map keys (");
                    sb2.append(length2);
                    sb2.append(") and map values (");
                    sb2.append(length3);
                    sb2.append(")");
                    zzhS(sb2.toString());
                }
                zza3.zzlv = new zzak.zza[zzn2.zzlh.length];
                zza3.zzlw = new zzak.zza[zzn2.zzlh.length];
                int[] iArr2 = zzn2.zzlh;
                int length4 = iArr2.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length4) {
                    zza3.zzlv[i5] = zza(iArr2[i4], zzf2, zzaArr, set);
                    i4++;
                    i5++;
                }
                int[] iArr3 = zzn2.zzli;
                int length5 = iArr3.length;
                int i6 = 0;
                while (i2 < length5) {
                    zza3.zzlw[i6] = zza(iArr3[i2], zzf2, zzaArr, set);
                    i2++;
                    i6++;
                }
                break;
            case 4:
                zza3 = zzm(zza2);
                zza3.zzlx = zzdl.zze(zza(zzn(zza2).zzll, zzf2, zzaArr, set));
                break;
            case 7:
                zza3 = zzm(zza2);
                zzaj.zzh zzn3 = zzn(zza2);
                zza3.zzlB = new zzak.zza[zzn3.zzlk.length];
                int[] iArr4 = zzn3.zzlk;
                int length6 = iArr4.length;
                int i7 = 0;
                while (i2 < length6) {
                    zza3.zzlB[i7] = zza(iArr4[i2], zzf2, zzaArr, set);
                    i2++;
                    i7++;
                }
                break;
        }
        if (zza3 == null) {
            String valueOf2 = String.valueOf(zza2);
            StringBuilder sb3 = new StringBuilder(15 + String.valueOf(valueOf2).length());
            sb3.append("Invalid value: ");
            sb3.append(valueOf2);
            zzhS(sb3.toString());
        }
        zzaArr[i] = zza3;
        set.remove(Integer.valueOf(i));
        return zza3;
    }

    private static zza zza(zzaj.zzb zzb2, zzaj.zzf zzf2, zzak.zza[] zzaArr, int i) throws zzg {
        zzb zzTw = zza.zzTw();
        for (int valueOf : zzb2.zzkq) {
            zzaj.zze zze2 = (zzaj.zze) zza(zzf2.zzkG, Integer.valueOf(valueOf).intValue(), "properties");
            String str = (String) zza(zzf2.zzkE, zze2.key, "keys");
            zzak.zza zza2 = (zzak.zza) zza(zzaArr, zze2.value, "values");
            if (zzai.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzTw.zzo(zza2);
            } else {
                zzTw.zzb(str, zza2);
            }
        }
        return zzTw.zzTx();
    }

    private static zze zza(zzaj.zzg zzg2, List<zza> list, List<zza> list2, List<zza> list3, zzaj.zzf zzf2) {
        zzf zzTB = zze.zzTB();
        for (int valueOf : zzg2.zzkU) {
            zzTB.zzd(list3.get(Integer.valueOf(valueOf).intValue()));
        }
        for (int valueOf2 : zzg2.zzkV) {
            zzTB.zze(list3.get(Integer.valueOf(valueOf2).intValue()));
        }
        for (int valueOf3 : zzg2.zzkW) {
            zzTB.zzf(list.get(Integer.valueOf(valueOf3).intValue()));
        }
        for (int valueOf4 : zzg2.zzkY) {
            zzTB.zzii(zzf2.zzkF[Integer.valueOf(valueOf4).intValue()].string);
        }
        for (int valueOf5 : zzg2.zzkX) {
            zzTB.zzg(list.get(Integer.valueOf(valueOf5).intValue()));
        }
        for (int valueOf6 : zzg2.zzkZ) {
            zzTB.zzij(zzf2.zzkF[Integer.valueOf(valueOf6).intValue()].string);
        }
        for (int valueOf7 : zzg2.zzla) {
            zzTB.zzh(list2.get(Integer.valueOf(valueOf7).intValue()));
        }
        for (int valueOf8 : zzg2.zzlc) {
            zzTB.zzik(zzf2.zzkF[Integer.valueOf(valueOf8).intValue()].string);
        }
        for (int valueOf9 : zzg2.zzlb) {
            zzTB.zzi(list2.get(Integer.valueOf(valueOf9).intValue()));
        }
        for (int valueOf10 : zzg2.zzld) {
            zzTB.zzil(zzf2.zzkF[Integer.valueOf(valueOf10).intValue()].string);
        }
        return zzTB.zzTE();
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzg {
        if (i < 0 || i >= tArr.length) {
            StringBuilder sb = new StringBuilder(45 + String.valueOf(str).length());
            sb.append("Index out of bounds detected: ");
            sb.append(i);
            sb.append(" in ");
            sb.append(str);
            zzhS(sb.toString());
        }
        return tArr[i];
    }

    public static zzc zzb(zzaj.zzf zzf2) throws zzg {
        zzak.zza[] zzaArr = new zzak.zza[zzf2.zzkF.length];
        for (int i = 0; i < zzf2.zzkF.length; i++) {
            zza(i, zzf2, zzaArr, (Set<Integer>) new HashSet(0));
        }
        zzd zzTy = zzc.zzTy();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < zzf2.zzkI.length; i2++) {
            arrayList.add(zza(zzf2.zzkI[i2], zzf2, zzaArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < zzf2.zzkJ.length; i3++) {
            arrayList2.add(zza(zzf2.zzkJ[i3], zzf2, zzaArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < zzf2.zzkH.length; i4++) {
            zza zza2 = zza(zzf2.zzkH[i4], zzf2, zzaArr, i4);
            zzTy.zzc(zza2);
            arrayList3.add(zza2);
        }
        for (zzaj.zzg zza3 : zzf2.zzkK) {
            zzTy.zzb(zza(zza3, arrayList, arrayList3, arrayList2, zzf2));
        }
        zzTy.zzih(zzf2.version);
        zzTy.zznO(zzf2.zzkS);
        return zzTy.zzTA();
    }

    public static void zzc(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static void zzhS(String str) throws zzg {
        zzbo.e(str);
        throw new zzg(str);
    }

    public static zzak.zza zzm(zzak.zza zza2) {
        zzak.zza zza3 = new zzak.zza();
        zza3.type = zza2.type;
        zza3.zzlC = (int[]) zza2.zzlC.clone();
        if (zza2.zzlD) {
            zza3.zzlD = zza2.zzlD;
        }
        return zza3;
    }

    private static zzaj.zzh zzn(zzak.zza zza2) throws zzg {
        if (((zzaj.zzh) zza2.zza(zzaj.zzh.zzle)) == null) {
            String valueOf = String.valueOf(zza2);
            StringBuilder sb = new StringBuilder(54 + String.valueOf(valueOf).length());
            sb.append("Expected a ServingValue and didn't get one. Value is: ");
            sb.append(valueOf);
            zzhS(sb.toString());
        }
        return (zzaj.zzh) zza2.zza(zzaj.zzh.zzle);
    }
}
