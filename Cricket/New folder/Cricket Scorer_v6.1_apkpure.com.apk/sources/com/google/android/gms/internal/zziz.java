package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzni;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@zzme
public class zziz {
    private final LinkedList<zzja> zzJb = new LinkedList<>();
    @Nullable
    private zziw zzJc;
    private final Map<zzja, zzjb> zzyE = new HashMap();

    private static void zza(String str, zzja zzja) {
        if (zzpk.zzak(2)) {
            zzpk.v(String.format(str, new Object[]{zzja}));
        }
    }

    private String[] zzai(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
            return split;
        } catch (UnsupportedEncodingException unused) {
            return new String[0];
        }
    }

    private boolean zzaj(String str) {
        try {
            return Pattern.matches(zzgd.zzDj.get(), str);
        } catch (RuntimeException e) {
            zzw.zzcQ().zza((Throwable) e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    static String zzak(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        } catch (RuntimeException unused) {
        }
        return str;
    }

    private static void zzc(Bundle bundle, String str) {
        String[] split = str.split("/", 2);
        if (split.length != 0) {
            String str2 = split[0];
            if (split.length == 1) {
                bundle.remove(str2);
                return;
            }
            Bundle bundle2 = bundle.getBundle(str2);
            if (bundle2 != null) {
                zzc(bundle2, split[1]);
            }
        }
    }

    private static void zzc(zzec zzec, String str) {
        Bundle bundle = zzec.zzzd.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean(str, true);
        }
        zzec.extras.putBoolean(str, true);
    }

    private String zzgw() {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = this.zzJb.iterator();
            while (it.hasNext()) {
                sb.append(Base64.encodeToString(((zzja) it.next()).toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    sb.append("\u0000");
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    static Set<String> zzj(zzec zzec) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(zzec.extras.keySet());
        Bundle bundle = zzec.zzzd.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    static zzec zzk(zzec zzec) {
        zzec zzn = zzn(zzec);
        zzc(zzn, "_skipMediation");
        return zzn;
    }

    static boolean zzl(zzec zzec) {
        return zzj(zzec).contains("_skipMediation");
    }

    static zzec zzm(zzec zzec) {
        zzec zzn = zzn(zzec);
        for (String str : zzgd.zzDf.get().split(",")) {
            zzc(zzn.zzzd, str);
            if (str.startsWith("com.google.ads.mediation.admob.AdMobAdapter/")) {
                zzc(zzn.extras, str.replaceFirst("com.google.ads.mediation.admob.AdMobAdapter/", ""));
            }
        }
        return zzn;
    }

    static zzec zzn(zzec zzec) {
        Parcel obtain = Parcel.obtain();
        zzec.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzec createFromParcel = zzec.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        if (zzgd.zzCX.get().booleanValue()) {
            zzec.zzi(createFromParcel);
        }
        return createFromParcel;
    }

    /* access modifiers changed from: package-private */
    public void flush() {
        while (this.zzJb.size() > 0) {
            zzja remove = this.zzJb.remove();
            zzjb zzjb = this.zzyE.get(remove);
            zza("Flushing interstitial queue for %s.", remove);
            while (zzjb.size() > 0) {
                zzjb.zzo((zzec) null).zzJh.zzcm();
            }
            this.zzyE.remove(remove);
        }
    }

    /* access modifiers changed from: package-private */
    public void restore() {
        if (this.zzJc != null) {
            SharedPreferences sharedPreferences = this.zzJc.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
            flush();
            try {
                HashMap hashMap = new HashMap();
                for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
                    if (!((String) next.getKey()).equals("PoolKeys")) {
                        zzje zzal = zzje.zzal((String) next.getValue());
                        zzja zzja = new zzja(zzal.zzum, zzal.zzts, zzal.zzJf);
                        if (!this.zzyE.containsKey(zzja)) {
                            this.zzyE.put(zzja, new zzjb(zzal.zzum, zzal.zzts, zzal.zzJf));
                            hashMap.put(zzja.toString(), zzja);
                            zza("Restored interstitial queue for %s.", zzja);
                        }
                    }
                }
                for (String str : zzai(sharedPreferences.getString("PoolKeys", ""))) {
                    zzja zzja2 = (zzja) hashMap.get(str);
                    if (this.zzyE.containsKey(zzja2)) {
                        this.zzJb.add(zzja2);
                    }
                }
            } catch (IOException | RuntimeException e) {
                zzw.zzcQ().zza(e, "InterstitialAdPool.restore");
                zzpk.zzc("Malformed preferences value for InterstitialAdPool.", e);
                this.zzyE.clear();
                this.zzJb.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void save() {
        if (this.zzJc != null) {
            SharedPreferences.Editor edit = this.zzJc.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
            edit.clear();
            for (Map.Entry next : this.zzyE.entrySet()) {
                zzja zzja = (zzja) next.getKey();
                zzjb zzjb = (zzjb) next.getValue();
                if (zzjb.zzgB()) {
                    edit.putString(zzja.toString(), new zzje(zzjb).zzgL());
                    zza("Saved interstitial queue for %s.", zzja);
                }
            }
            edit.putString("PoolKeys", zzgw());
            edit.apply();
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public zzjb.zza zza(zzec zzec, String str) {
        if (zzaj(str)) {
            return null;
        }
        int i = new zzni.zza(this.zzJc.getApplicationContext()).zzjC().zzUQ;
        zzec zzm = zzm(zzec);
        String zzak = zzak(str);
        zzja zzja = new zzja(zzm, zzak, i);
        zzjb zzjb = this.zzyE.get(zzja);
        if (zzjb == null) {
            zza("Interstitial pool created at %s.", zzja);
            zzjb = new zzjb(zzm, zzak, i);
            this.zzyE.put(zzja, zzjb);
        }
        this.zzJb.remove(zzja);
        this.zzJb.add(zzja);
        zzjb.zzgA();
        while (this.zzJb.size() > zzgd.zzDg.get().intValue()) {
            zzja remove = this.zzJb.remove();
            zzjb zzjb2 = this.zzyE.get(remove);
            zza("Evicting interstitial queue for %s.", remove);
            while (zzjb2.size() > 0) {
                zzjb.zza zzo = zzjb2.zzo((zzec) null);
                if (zzo.zzJl) {
                    zzjc.zzgC().zzgE();
                }
                zzo.zzJh.zzcm();
            }
            this.zzyE.remove(remove);
        }
        while (zzjb.size() > 0) {
            zzjb.zza zzo2 = zzjb.zzo(zzm);
            if (!zzo2.zzJl || zzw.zzcS().currentTimeMillis() - zzo2.zzJk <= 1000 * ((long) zzgd.zzDi.get().intValue())) {
                String str2 = zzo2.zzJi != null ? " (inline) " : " ";
                StringBuilder sb = new StringBuilder(34 + String.valueOf(str2).length());
                sb.append("Pooled interstitial");
                sb.append(str2);
                sb.append("returned at %s.");
                zza(sb.toString(), zzja);
                return zzo2;
            }
            zza("Expired interstitial at %s.", zzja);
            zzjc.zzgC().zzgD();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void zza(zziw zziw) {
        if (this.zzJc == null) {
            this.zzJc = zziw.zzgu();
            restore();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzec zzec, String str) {
        if (this.zzJc != null) {
            int i = new zzni.zza(this.zzJc.getApplicationContext()).zzjC().zzUQ;
            zzec zzm = zzm(zzec);
            String zzak = zzak(str);
            zzja zzja = new zzja(zzm, zzak, i);
            zzjb zzjb = this.zzyE.get(zzja);
            if (zzjb == null) {
                zza("Interstitial pool created at %s.", zzja);
                zzjb = new zzjb(zzm, zzak, i);
                this.zzyE.put(zzja, zzjb);
            }
            zzjb.zza(this.zzJc, zzec);
            zzjb.zzgA();
            zza("Inline entry added to the queue at %s.", zzja);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002f, code lost:
        r4 = r1.size();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzgv() {
        /*
            r9 = this;
            com.google.android.gms.internal.zziw r0 = r9.zzJc
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.util.Map<com.google.android.gms.internal.zzja, com.google.android.gms.internal.zzjb> r0 = r9.zzyE
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0085
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            com.google.android.gms.internal.zzja r2 = (com.google.android.gms.internal.zzja) r2
            java.lang.Object r1 = r1.getValue()
            com.google.android.gms.internal.zzjb r1 = (com.google.android.gms.internal.zzjb) r1
            r3 = 2
            boolean r4 = com.google.android.gms.internal.zzpk.zzak(r3)
            r5 = 0
            if (r4 == 0) goto L_0x0056
            int r4 = r1.size()
            int r6 = r1.zzgy()
            if (r6 >= r4) goto L_0x0056
            java.lang.String r7 = "Loading %s/%s pooled interstitials for %s."
            r8 = 3
            java.lang.Object[] r8 = new java.lang.Object[r8]
            int r6 = r4 - r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8[r5] = r6
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r6 = 1
            r8[r6] = r4
            r8[r3] = r2
            java.lang.String r3 = java.lang.String.format(r7, r8)
            com.google.android.gms.internal.zzpk.v(r3)
        L_0x0056:
            int r3 = r1.zzgz()
            int r5 = r5 + r3
        L_0x005b:
            int r3 = r1.size()
            com.google.android.gms.internal.zzfz<java.lang.Integer> r4 = com.google.android.gms.internal.zzgd.zzDh
            java.lang.Object r4 = r4.get()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            if (r3 >= r4) goto L_0x007d
            java.lang.String r3 = "Pooling and loading one new interstitial for %s."
            zza((java.lang.String) r3, (com.google.android.gms.internal.zzja) r2)
            com.google.android.gms.internal.zziw r3 = r9.zzJc
            boolean r3 = r1.zzb((com.google.android.gms.internal.zziw) r3)
            if (r3 == 0) goto L_0x005b
            int r5 = r5 + 1
            goto L_0x005b
        L_0x007d:
            com.google.android.gms.internal.zzjc r1 = com.google.android.gms.internal.zzjc.zzgC()
            r1.zzE(r5)
            goto L_0x000f
        L_0x0085:
            r9.save()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zziz.zzgv():void");
    }
}
