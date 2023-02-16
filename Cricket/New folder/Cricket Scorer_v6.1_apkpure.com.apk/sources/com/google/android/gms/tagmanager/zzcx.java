package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzaj;
import com.google.android.gms.internal.zzak;
import com.google.android.gms.internal.zzbjf;
import com.google.android.gms.tagmanager.zzm;
import com.google.android.gms.tagmanager.zzu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzcx {
    private static final zzce<zzak.zza> zzbHC = new zzce<>(zzdl.zzRR(), true);
    private final DataLayer zzbEV;
    private final zzbjf.zzc zzbHD;
    private final zzaj zzbHE;
    private final Map<String, zzam> zzbHF;
    private final Map<String, zzam> zzbHG;
    private final Map<String, zzam> zzbHH;
    private final zzl<zzbjf.zza, zzce<zzak.zza>> zzbHI;
    private final zzl<String, zzb> zzbHJ;
    private final Set<zzbjf.zze> zzbHK;
    private final Map<String, zzc> zzbHL;
    private volatile String zzbHM;
    private int zzbHN;

    interface zza {
        void zza(zzbjf.zze zze, Set<zzbjf.zza> set, Set<zzbjf.zza> set2, zzcs zzcs);
    }

    private static class zzb {
        private zzce<zzak.zza> zzbHS;
        private zzak.zza zzbHT;

        public zzb(zzce<zzak.zza> zzce, zzak.zza zza) {
            this.zzbHS = zzce;
            this.zzbHT = zza;
        }

        public int getSize() {
            return this.zzbHS.getObject().zzaeS() + (this.zzbHT == null ? 0 : this.zzbHT.zzaeS());
        }

        public zzce<zzak.zza> zzRq() {
            return this.zzbHS;
        }

        public zzak.zza zzRr() {
            return this.zzbHT;
        }
    }

    private static class zzc {
        private final Set<zzbjf.zze> zzbHK = new HashSet();
        private final Map<zzbjf.zze, List<zzbjf.zza>> zzbHU = new HashMap();
        private final Map<zzbjf.zze, List<zzbjf.zza>> zzbHV = new HashMap();
        private final Map<zzbjf.zze, List<String>> zzbHW = new HashMap();
        private final Map<zzbjf.zze, List<String>> zzbHX = new HashMap();
        private zzbjf.zza zzbHY;

        public Set<zzbjf.zze> zzRs() {
            return this.zzbHK;
        }

        public Map<zzbjf.zze, List<zzbjf.zza>> zzRt() {
            return this.zzbHU;
        }

        public Map<zzbjf.zze, List<String>> zzRu() {
            return this.zzbHW;
        }

        public Map<zzbjf.zze, List<String>> zzRv() {
            return this.zzbHX;
        }

        public Map<zzbjf.zze, List<zzbjf.zza>> zzRw() {
            return this.zzbHV;
        }

        public zzbjf.zza zzRx() {
            return this.zzbHY;
        }

        public void zza(zzbjf.zze zze) {
            this.zzbHK.add(zze);
        }

        public void zza(zzbjf.zze zze, zzbjf.zza zza) {
            List list = this.zzbHU.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbHU.put(zze, list);
            }
            list.add(zza);
        }

        public void zza(zzbjf.zze zze, String str) {
            List list = this.zzbHW.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbHW.put(zze, list);
            }
            list.add(str);
        }

        public void zzb(zzbjf.zza zza) {
            this.zzbHY = zza;
        }

        public void zzb(zzbjf.zze zze, zzbjf.zza zza) {
            List list = this.zzbHV.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbHV.put(zze, list);
            }
            list.add(zza);
        }

        public void zzb(zzbjf.zze zze, String str) {
            List list = this.zzbHX.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbHX.put(zze, list);
            }
            list.add(str);
        }
    }

    public zzcx(Context context, zzbjf.zzc zzc2, DataLayer dataLayer, zzu.zza zza2, zzu.zza zza3, zzaj zzaj) {
        if (zzc2 == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.zzbHD = zzc2;
        this.zzbHK = new HashSet(zzc2.zzSU());
        this.zzbEV = dataLayer;
        this.zzbHE = zzaj;
        this.zzbHI = new zzm().zza(1048576, new zzm.zza<zzbjf.zza, zzce<zzak.zza>>(this) {
            /* renamed from: zza */
            public int sizeOf(zzbjf.zza zza, zzce<zzak.zza> zzce) {
                return zzce.getObject().zzaeS();
            }
        });
        this.zzbHJ = new zzm().zza(1048576, new zzm.zza<String, zzb>(this) {
            /* renamed from: zza */
            public int sizeOf(String str, zzb zzb) {
                return str.length() + zzb.getSize();
            }
        });
        this.zzbHF = new HashMap();
        zzb(new zzj(context));
        zzb(new zzu(zza3));
        zzb(new zzy(dataLayer));
        zzb(new zzdm(context, dataLayer));
        this.zzbHG = new HashMap();
        zzc(new zzs());
        zzc(new zzag());
        zzc(new zzah());
        zzc(new zzao());
        zzc(new zzap());
        zzc(new zzbk());
        zzc(new zzbl());
        zzc(new zzcn());
        zzc(new zzdf());
        this.zzbHH = new HashMap();
        zza((zzam) new zzb(context));
        zza((zzam) new zzc(context));
        zza((zzam) new zze(context));
        zza((zzam) new zzf(context));
        zza((zzam) new zzg(context));
        zza((zzam) new zzh(context));
        zza((zzam) new zzi(context));
        zza((zzam) new zzn());
        zza((zzam) new zzr(this.zzbHD.getVersion()));
        zza((zzam) new zzu(zza2));
        zza((zzam) new zzw(dataLayer));
        zza((zzam) new zzab(context));
        zza((zzam) new zzac());
        zza((zzam) new zzaf());
        zza((zzam) new zzak(this));
        zza((zzam) new zzaq());
        zza((zzam) new zzar());
        zza((zzam) new zzbe(context));
        zza((zzam) new zzbg());
        zza((zzam) new zzbj());
        zza((zzam) new zzbq());
        zza((zzam) new zzbs(context));
        zza((zzam) new zzcf());
        zza((zzam) new zzch());
        zza((zzam) new zzck());
        zza((zzam) new zzcm());
        zza((zzam) new zzco(context));
        zza((zzam) new zzcy());
        zza((zzam) new zzcz());
        zza((zzam) new zzdh());
        zza((zzam) new zzdn());
        this.zzbHL = new HashMap();
        for (zzbjf.zze next : this.zzbHK) {
            for (int i = 0; i < next.zzTC().size(); i++) {
                zzbjf.zza zza4 = next.zzTC().get(i);
                zzc zzh = zzh(this.zzbHL, zza(zza4));
                zzh.zza(next);
                zzh.zza(next, zza4);
                zzh.zza(next, "Unknown");
            }
            for (int i2 = 0; i2 < next.zzTD().size(); i2++) {
                zzbjf.zza zza5 = next.zzTD().get(i2);
                zzc zzh2 = zzh(this.zzbHL, zza(zza5));
                zzh2.zza(next);
                zzh2.zzb(next, zza5);
                zzh2.zzb(next, "Unknown");
            }
        }
        for (Map.Entry next2 : this.zzbHD.zzTz().entrySet()) {
            for (zzbjf.zza zza6 : (List) next2.getValue()) {
                if (!zzdl.zzi(zza6.zzSW().get(zzai.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzh(this.zzbHL, (String) next2.getKey()).zzb(zza6);
                }
            }
        }
    }

    private String zzRp() {
        if (this.zzbHN <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzbHN));
        for (int i = 2; i < this.zzbHN; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    private zzce<zzak.zza> zza(zzak.zza zza2, Set<String> set, zzdo zzdo) {
        if (!zza2.zzlD) {
            return new zzce<>(zza2, true);
        }
        int i = zza2.type;
        if (i != 7) {
            switch (i) {
                case 2:
                    zzak.zza zzm = zzbjf.zzm(zza2);
                    zzm.zzlu = new zzak.zza[zza2.zzlu.length];
                    for (int i2 = 0; i2 < zza2.zzlu.length; i2++) {
                        zzce<zzak.zza> zza3 = zza(zza2.zzlu[i2], set, zzdo.zznC(i2));
                        if (zza3 == zzbHC) {
                            return zzbHC;
                        }
                        zzm.zzlu[i2] = zza3.getObject();
                    }
                    return new zzce<>(zzm, false);
                case 3:
                    zzak.zza zzm2 = zzbjf.zzm(zza2);
                    if (zza2.zzlv.length != zza2.zzlw.length) {
                        String valueOf = String.valueOf(zza2.toString());
                        zzbo.e(valueOf.length() != 0 ? "Invalid serving value: ".concat(valueOf) : new String("Invalid serving value: "));
                        return zzbHC;
                    }
                    zzm2.zzlv = new zzak.zza[zza2.zzlv.length];
                    zzm2.zzlw = new zzak.zza[zza2.zzlv.length];
                    for (int i3 = 0; i3 < zza2.zzlv.length; i3++) {
                        zzce<zzak.zza> zza4 = zza(zza2.zzlv[i3], set, zzdo.zznD(i3));
                        zzce<zzak.zza> zza5 = zza(zza2.zzlw[i3], set, zzdo.zznE(i3));
                        if (zza4 == zzbHC || zza5 == zzbHC) {
                            return zzbHC;
                        }
                        zzm2.zzlv[i3] = zza4.getObject();
                        zzm2.zzlw[i3] = zza5.getObject();
                    }
                    return new zzce<>(zzm2, false);
                case 4:
                    if (set.contains(zza2.zzlx)) {
                        String valueOf2 = String.valueOf(zza2.zzlx);
                        String valueOf3 = String.valueOf(set.toString());
                        StringBuilder sb = new StringBuilder(79 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
                        sb.append("Macro cycle detected.  Current macro reference: ");
                        sb.append(valueOf2);
                        sb.append(".  Previous macro references: ");
                        sb.append(valueOf3);
                        sb.append(".");
                        zzbo.e(sb.toString());
                        return zzbHC;
                    }
                    set.add(zza2.zzlx);
                    zzce<zzak.zza> zza6 = zzdp.zza(zza(zza2.zzlx, set, zzdo.zzQY()), zza2.zzlC);
                    set.remove(zza2.zzlx);
                    return zza6;
                default:
                    int i4 = zza2.type;
                    StringBuilder sb2 = new StringBuilder(25);
                    sb2.append("Unknown type: ");
                    sb2.append(i4);
                    zzbo.e(sb2.toString());
                    return zzbHC;
            }
        } else {
            zzak.zza zzm3 = zzbjf.zzm(zza2);
            zzm3.zzlB = new zzak.zza[zza2.zzlB.length];
            for (int i5 = 0; i5 < zza2.zzlB.length; i5++) {
                zzce<zzak.zza> zza7 = zza(zza2.zzlB[i5], set, zzdo.zznF(i5));
                if (zza7 == zzbHC) {
                    return zzbHC;
                }
                zzm3.zzlB[i5] = zza7.getObject();
            }
            return new zzce<>(zzm3, false);
        }
    }

    private zzce<zzak.zza> zza(String str, Set<String> set, zzbr zzbr) {
        zzbjf.zza zza2;
        this.zzbHN++;
        zzb zzb2 = this.zzbHJ.get(str);
        if (zzb2 != null) {
            zza(zzb2.zzRr(), set);
            this.zzbHN--;
            return zzb2.zzRq();
        }
        zzc zzc2 = this.zzbHL.get(str);
        if (zzc2 == null) {
            String valueOf = String.valueOf(zzRp());
            StringBuilder sb = new StringBuilder(15 + String.valueOf(valueOf).length() + String.valueOf(str).length());
            sb.append(valueOf);
            sb.append("Invalid macro: ");
            sb.append(str);
            zzbo.e(sb.toString());
            this.zzbHN--;
            return zzbHC;
        }
        zzce<Set<zzbjf.zza>> zza3 = zza(str, zzc2.zzRs(), zzc2.zzRt(), zzc2.zzRu(), zzc2.zzRw(), zzc2.zzRv(), set, zzbr.zzQz());
        if (zza3.getObject().isEmpty()) {
            zza2 = zzc2.zzRx();
        } else {
            if (zza3.getObject().size() > 1) {
                String valueOf2 = String.valueOf(zzRp());
                StringBuilder sb2 = new StringBuilder(37 + String.valueOf(valueOf2).length() + String.valueOf(str).length());
                sb2.append(valueOf2);
                sb2.append("Multiple macros active for macroName ");
                sb2.append(str);
                zzbo.zzbh(sb2.toString());
            }
            zza2 = (zzbjf.zza) zza3.getObject().iterator().next();
        }
        if (zza2 == null) {
            this.zzbHN--;
            return zzbHC;
        }
        zzce<zzak.zza> zza4 = zza(this.zzbHH, zza2, set, zzbr.zzQQ());
        zzce<zzak.zza> zzce = zza4 == zzbHC ? zzbHC : new zzce<>(zza4.getObject(), zza3.zzQZ() && zza4.zzQZ());
        zzak.zza zzRr = zza2.zzRr();
        if (zzce.zzQZ()) {
            this.zzbHJ.zzh(str, new zzb(zzce, zzRr));
        }
        zza(zzRr, set);
        this.zzbHN--;
        return zzce;
    }

    private zzce<zzak.zza> zza(Map<String, zzam> map, zzbjf.zza zza2, Set<String> set, zzcp zzcp) {
        String sb;
        zzak.zza zza3 = zza2.zzSW().get(zzai.FUNCTION.toString());
        if (zza3 == null) {
            sb = "No function id in properties";
        } else {
            String str = zza3.zzly;
            zzam zzam = map.get(str);
            if (zzam == null) {
                sb = String.valueOf(str).concat(" has no backing implementation.");
            } else {
                zzce<zzak.zza> zzce = this.zzbHI.get(zza2);
                if (zzce != null) {
                    return zzce;
                }
                HashMap hashMap = new HashMap();
                boolean z = true;
                boolean z2 = true;
                for (Map.Entry next : zza2.zzSW().entrySet()) {
                    zzce<zzak.zza> zza4 = zza((zzak.zza) next.getValue(), set, zzcp.zzhm((String) next.getKey()).zzd((zzak.zza) next.getValue()));
                    if (zza4 == zzbHC) {
                        return zzbHC;
                    }
                    if (zza4.zzQZ()) {
                        zza2.zza((String) next.getKey(), zza4.getObject());
                    } else {
                        z2 = false;
                    }
                    hashMap.put((String) next.getKey(), zza4.getObject());
                }
                if (!zzam.zzf(hashMap.keySet())) {
                    String valueOf = String.valueOf(zzam.zzQM());
                    String valueOf2 = String.valueOf(hashMap.keySet());
                    StringBuilder sb2 = new StringBuilder(43 + String.valueOf(str).length() + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
                    sb2.append("Incorrect keys for function ");
                    sb2.append(str);
                    sb2.append(" required ");
                    sb2.append(valueOf);
                    sb2.append(" had ");
                    sb2.append(valueOf2);
                    sb = sb2.toString();
                } else {
                    if (!z2 || !zzam.zzQb()) {
                        z = false;
                    }
                    zzce<zzak.zza> zzce2 = new zzce<>(zzam.zzZ(hashMap), z);
                    if (z) {
                        this.zzbHI.zzh(zza2, zzce2);
                    }
                    return zzce2;
                }
            }
        }
        zzbo.e(sb);
        return zzbHC;
    }

    private zzce<Set<zzbjf.zza>> zza(Set<zzbjf.zze> set, Set<String> set2, zza zza2, zzcw zzcw) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Iterator<zzbjf.zze> it = set.iterator();
        while (true) {
            boolean z = true;
            while (true) {
                if (it.hasNext()) {
                    zzbjf.zze next = it.next();
                    zzcs zzQX = zzcw.zzQX();
                    zzce<Boolean> zza3 = zza(next, set2, zzQX);
                    if (zza3.getObject().booleanValue()) {
                        zza2.zza(next, hashSet, hashSet2, zzQX);
                    }
                    if (!z || !zza3.zzQZ()) {
                        z = false;
                    }
                } else {
                    hashSet.removeAll(hashSet2);
                    return new zzce<>(hashSet, z);
                }
            }
        }
    }

    private static String zza(zzbjf.zza zza2) {
        return zzdl.zze(zza2.zzSW().get(zzai.INSTANCE_NAME.toString()));
    }

    private void zza(zzak.zza zza2, Set<String> set) {
        zzce<zzak.zza> zza3;
        if (zza2 != null && (zza3 = zza(zza2, set, (zzdo) new zzcc())) != zzbHC) {
            Object zzj = zzdl.zzj(zza3.getObject());
            if (zzj instanceof Map) {
                this.zzbEV.push((Map) zzj);
            } else if (zzj instanceof List) {
                for (Object next : (List) zzj) {
                    if (next instanceof Map) {
                        this.zzbEV.push((Map) next);
                    } else {
                        zzbo.zzbh("pushAfterEvaluate: value not a Map");
                    }
                }
            } else {
                zzbo.zzbh("pushAfterEvaluate: value not a Map or List");
            }
        }
    }

    private static void zza(Map<String, zzam> map, zzam zzam) {
        if (map.containsKey(zzam.zzQL())) {
            String valueOf = String.valueOf(zzam.zzQL());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Duplicate function type name: ".concat(valueOf) : new String("Duplicate function type name: "));
        } else {
            map.put(zzam.zzQL(), zzam);
        }
    }

    private static zzc zzh(Map<String, zzc> map, String str) {
        zzc zzc2 = map.get(str);
        if (zzc2 != null) {
            return zzc2;
        }
        zzc zzc3 = new zzc();
        map.put(str, zzc3);
        return zzc3;
    }

    public synchronized void zzQ(List<zzaj.zzi> list) {
        for (zzaj.zzi next : list) {
            if (next.name != null) {
                if (next.name.startsWith("gaExperiment:")) {
                    zzal.zza(this.zzbEV, next);
                }
            }
            String valueOf = String.valueOf(next);
            StringBuilder sb = new StringBuilder(22 + String.valueOf(valueOf).length());
            sb.append("Ignored supplemental: ");
            sb.append(valueOf);
            zzbo.v(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized String zzRo() {
        return this.zzbHM;
    }

    /* access modifiers changed from: package-private */
    public zzce<Boolean> zza(zzbjf.zza zza2, Set<String> set, zzcp zzcp) {
        zzce<zzak.zza> zza3 = zza(this.zzbHG, zza2, set, zzcp);
        Boolean zzi = zzdl.zzi(zza3.getObject());
        zzdl.zzR(zzi);
        return new zzce<>(zzi, zza3.zzQZ());
    }

    /* access modifiers changed from: package-private */
    public zzce<Boolean> zza(zzbjf.zze zze, Set<String> set, zzcs zzcs) {
        boolean z;
        Iterator<zzbjf.zza> it = zze.zzSZ().iterator();
        while (true) {
            boolean z2 = true;
            while (true) {
                if (it.hasNext()) {
                    zzce<Boolean> zza2 = zza(it.next(), set, zzcs.zzQR());
                    if (zza2.getObject().booleanValue()) {
                        zzdl.zzR(false);
                        return new zzce<>(false, zza2.zzQZ());
                    } else if (!z || !zza2.zzQZ()) {
                        z2 = false;
                    }
                } else {
                    for (zzbjf.zza zza3 : zze.zzSY()) {
                        zzce<Boolean> zza4 = zza(zza3, set, zzcs.zzQS());
                        if (!zza4.getObject().booleanValue()) {
                            zzdl.zzR(false);
                            return new zzce<>(false, zza4.zzQZ());
                        }
                        z = z && zza4.zzQZ();
                    }
                    zzdl.zzR(true);
                    return new zzce<>(true, z);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public zzce<Set<zzbjf.zza>> zza(String str, Set<zzbjf.zze> set, Map<zzbjf.zze, List<zzbjf.zza>> map, Map<zzbjf.zze, List<String>> map2, Map<zzbjf.zze, List<zzbjf.zza>> map3, Map<zzbjf.zze, List<String>> map4, Set<String> set2, zzcw zzcw) {
        final Map<zzbjf.zze, List<zzbjf.zza>> map5 = map;
        final Map<zzbjf.zze, List<String>> map6 = map2;
        final Map<zzbjf.zze, List<zzbjf.zza>> map7 = map3;
        final Map<zzbjf.zze, List<String>> map8 = map4;
        return zza(set, set2, (zza) new zza(this) {
            public void zza(zzbjf.zze zze, Set<zzbjf.zza> set, Set<zzbjf.zza> set2, zzcs zzcs) {
                List list = (List) map5.get(zze);
                map6.get(zze);
                if (list != null) {
                    set.addAll(list);
                    zzcs.zzQT();
                }
                List list2 = (List) map7.get(zze);
                map8.get(zze);
                if (list2 != null) {
                    set2.addAll(list2);
                    zzcs.zzQU();
                }
            }
        }, zzcw);
    }

    /* access modifiers changed from: package-private */
    public zzce<Set<zzbjf.zza>> zza(Set<zzbjf.zze> set, zzcw zzcw) {
        return zza(set, (Set<String>) new HashSet(), (zza) new zza(this) {
            public void zza(zzbjf.zze zze, Set<zzbjf.zza> set, Set<zzbjf.zza> set2, zzcs zzcs) {
                set.addAll(zze.zzTa());
                set2.addAll(zze.zzTb());
                zzcs.zzQV();
                zzcs.zzQW();
            }
        }, zzcw);
    }

    /* access modifiers changed from: package-private */
    public void zza(zzam zzam) {
        zza(this.zzbHH, zzam);
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzam zzam) {
        zza(this.zzbHF, zzam);
    }

    /* access modifiers changed from: package-private */
    public void zzc(zzam zzam) {
        zza(this.zzbHG, zzam);
    }

    public synchronized void zzgU(String str) {
        zzhr(str);
        zzv zzQK = this.zzbHE.zzhh(str).zzQK();
        for (zzbjf.zza zza2 : zza(this.zzbHK, zzQK.zzQz()).getObject()) {
            zza(this.zzbHF, zza2, (Set<String>) new HashSet(), zzQK.zzQy());
        }
        zzhr((String) null);
    }

    public zzce<zzak.zza> zzhq(String str) {
        this.zzbHN = 0;
        return zza(str, (Set<String>) new HashSet(), this.zzbHE.zzhg(str).zzQJ());
    }

    /* access modifiers changed from: package-private */
    public synchronized void zzhr(String str) {
        this.zzbHM = str;
    }
}
