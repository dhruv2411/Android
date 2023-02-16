package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzme
public class zzjy implements zzjp {
    private final Context mContext;
    private final zzjr zzKY;
    private final boolean zzLa;
    private final zzmk zzLo;
    private final long zzLp;
    private final long zzLq;
    private boolean zzLs = false;
    private List<zzjv> zzLu = new ArrayList();
    private zzju zzLy;
    private final Object zzrJ = new Object();
    private final zzgl zzsn;
    private final zzka zzsz;
    private final boolean zzwf;

    public zzjy(Context context, zzmk zzmk, zzka zzka, zzjr zzjr, boolean z, boolean z2, long j, long j2, zzgl zzgl) {
        this.mContext = context;
        this.zzLo = zzmk;
        this.zzsz = zzka;
        this.zzKY = zzjr;
        this.zzwf = z;
        this.zzLa = z2;
        this.zzLp = j;
        this.zzLq = j2;
        this.zzsn = zzgl;
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            this.zzLs = true;
            if (this.zzLy != null) {
                this.zzLy.cancel();
            }
        }
    }

    public zzjv zzd(List<zzjq> list) {
        Object obj;
        zzjv zzjv;
        zzjy zzjy = this;
        zzpk.zzbf("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzgj zzfB = zzjy.zzsn.zzfB();
        Iterator<zzjq> it = list.iterator();
        while (it.hasNext()) {
            zzjq next = it.next();
            String valueOf = String.valueOf(next.zzKo);
            zzpk.zzbg(valueOf.length() != 0 ? "Trying mediation network: ".concat(valueOf) : new String("Trying mediation network: "));
            Iterator<String> it2 = next.zzKp.iterator();
            while (true) {
                if (it2.hasNext()) {
                    String next2 = it2.next();
                    zzgj zzfB2 = zzjy.zzsn.zzfB();
                    Object obj2 = zzjy.zzrJ;
                    synchronized (obj2) {
                        try {
                            if (zzjy.zzLs) {
                                zzjv = new zzjv(-1);
                            } else {
                                Context context = zzjy.mContext;
                                zzka zzka = zzjy.zzsz;
                                zzjr zzjr = zzjy.zzKY;
                                zzec zzec = zzjy.zzLo.zzRy;
                                zzeg zzeg = zzjy.zzLo.zzvr;
                                Iterator<zzjq> it3 = it;
                                zzqh zzqh = zzjy.zzLo.zzvn;
                                zzgj zzgj = zzfB;
                                boolean z = zzjy.zzwf;
                                ArrayList arrayList2 = arrayList;
                                boolean z2 = zzjy.zzLa;
                                zzec zzec2 = zzec;
                                zzhc zzhc = zzjy.zzLo.zzvF;
                                List<String> list2 = zzjy.zzLo.zzvK;
                                zzec zzec3 = zzec2;
                                zzjr zzjr2 = zzjr;
                                String str = next2;
                                obj = obj2;
                                zzjq zzjq = next;
                                zzju zzju = r7;
                                zzgj zzgj2 = zzfB2;
                                zzjq zzjq2 = next;
                                String str2 = next2;
                                zzeg zzeg2 = zzeg;
                                Iterator<String> it4 = it2;
                                try {
                                    zzju zzju2 = new zzju(context, str, zzka, zzjr2, zzjq, zzec3, zzeg2, zzqh, z, z2, zzhc, list2);
                                } catch (Throwable th) {
                                    th = th;
                                    Throwable th2 = th;
                                    throw th2;
                                }
                                try {
                                    this.zzLy = zzju;
                                    final zzjv zza = this.zzLy.zza(this.zzLp, this.zzLq);
                                    this.zzLu.add(zza);
                                    if (zza.zzLh == 0) {
                                        zzpk.zzbf("Adapter succeeded.");
                                        this.zzsn.zzh("mediation_network_succeed", str2);
                                        ArrayList arrayList3 = arrayList2;
                                        if (!arrayList3.isEmpty()) {
                                            this.zzsn.zzh("mediation_networks_fail", TextUtils.join(",", arrayList3));
                                        }
                                        this.zzsn.zza(zzgj2, "mls");
                                        this.zzsn.zza(zzgj, "ttm");
                                        return zza;
                                    }
                                    zzgj zzgj3 = zzgj;
                                    ArrayList arrayList4 = arrayList2;
                                    arrayList4.add(str2);
                                    this.zzsn.zza(zzgj2, "mlf");
                                    if (zza.zzLj != null) {
                                        zzpo.zzXC.post(new Runnable(this) {
                                            public void run() {
                                                try {
                                                    zza.zzLj.destroy();
                                                } catch (RemoteException e) {
                                                    zzpk.zzc("Could not destroy mediation adapter.", e);
                                                }
                                            }
                                        });
                                    }
                                    zzjy = this;
                                    arrayList = arrayList4;
                                    it2 = it4;
                                    zzfB = zzgj3;
                                    it = it3;
                                    next = zzjq2;
                                } catch (Throwable th3) {
                                    th = th3;
                                    Throwable th22 = th;
                                    throw th22;
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            zzjy zzjy2 = zzjy;
                            obj = obj2;
                            Throwable th222 = th;
                            throw th222;
                        }
                    }
                    return zzjv;
                }
            }
        }
        ArrayList arrayList5 = arrayList;
        zzjy zzjy3 = zzjy;
        if (!arrayList5.isEmpty()) {
            zzjy3.zzsn.zzh("mediation_networks_fail", TextUtils.join(",", arrayList5));
        }
        return new zzjv(1);
    }

    public List<zzjv> zzgU() {
        return this.zzLu;
    }
}
