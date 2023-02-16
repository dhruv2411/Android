package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzlp;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@zzme
public class zzlu extends zzlp {
    /* access modifiers changed from: private */
    public final zzqw zzIs;
    private zzjr zzKY;
    zzjp zzQe;
    protected zzjv zzQf;
    /* access modifiers changed from: private */
    public boolean zzQg;
    private final zzgl zzsn;
    private zzka zzsz;

    zzlu(Context context, zzpb.zza zza, zzka zzka, zzlq.zza zza2, zzgl zzgl, zzqw zzqw) {
        super(context, zza, zza2);
        this.zzsz = zzka;
        this.zzKY = zza.zzWc;
        this.zzsn = zzgl;
        this.zzIs = zzqw;
    }

    private static int zzT(int i) {
        switch (i) {
            case -1:
                return 4;
            case 0:
                return 0;
            case 1:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 5;
            default:
                return 6;
        }
    }

    private static String zza(zzjv zzjv) {
        String str = zzjv.zzLi.zzKq;
        int zzT = zzT(zzjv.zzLh);
        long j = zzjv.zzLn;
        StringBuilder sb = new StringBuilder(33 + String.valueOf(str).length());
        sb.append(str);
        sb.append(".");
        sb.append(zzT);
        sb.append(".");
        sb.append(j);
        return sb.toString();
    }

    private static String zzg(List<zzjv> list) {
        String str = "";
        if (list == null) {
            return str.toString();
        }
        for (zzjv next : list) {
            if (!(next == null || next.zzLi == null || TextUtils.isEmpty(next.zzLi.zzKq))) {
                String valueOf = String.valueOf(str);
                String valueOf2 = String.valueOf(zza(next));
                StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
                sb.append(valueOf);
                sb.append(valueOf2);
                sb.append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
                str = sb.toString();
            }
        }
        return str.substring(0, Math.max(0, str.length() - 1));
    }

    private void zziS() throws zzlp.zza {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                synchronized (zzlu.this.zzPU) {
                    boolean unused = zzlu.this.zzQg = zzp.zza(zzlu.this.zzIs, zzlu.this.zzQf, countDownLatch);
                }
            }
        });
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
            synchronized (this.zzPU) {
                if (!this.zzQg) {
                    throw new zzlp.zza("View could not be prepared", 0);
                } else if (this.zzIs.isDestroyed()) {
                    throw new zzlp.zza("Assets not loaded, web view is destroyed", 0);
                }
            }
        } catch (InterruptedException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(38 + String.valueOf(valueOf).length());
            sb.append("Interrupted while waiting for latch : ");
            sb.append(valueOf);
            throw new zzlp.zza(sb.toString(), 0);
        }
    }

    public void onStop() {
        synchronized (this.zzPU) {
            super.onStop();
            if (this.zzQe != null) {
                this.zzQe.cancel();
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzpb zzR(int i) {
        zzmk zzmk = this.zzPR.zzTi;
        zzec zzec = zzmk.zzRy;
        zzqw zzqw = this.zzIs;
        List<String> list = this.zzPS.zzKF;
        List<String> list2 = this.zzPS.zzKG;
        List<String> list3 = this.zzPS.zzSp;
        int i2 = this.zzPS.orientation;
        long j = this.zzPS.zzKL;
        String str = zzmk.zzRB;
        boolean z = this.zzPS.zzSn;
        zzjq zzjq = this.zzQf != null ? this.zzQf.zzLi : null;
        zzkb zzkb = this.zzQf != null ? this.zzQf.zzLj : null;
        return new zzpb(zzec, zzqw, list, i, list2, list3, i2, j, str, z, zzjq, zzkb, this.zzQf != null ? this.zzQf.zzLk : AdMobAdapter.class.getName(), this.zzKY, this.zzQf != null ? this.zzQf.zzLl : null, this.zzPS.zzSo, this.zzPR.zzvr, this.zzPS.zzSm, this.zzPR.zzWg, this.zzPS.zzSr, this.zzPS.zzSs, this.zzPR.zzWa, (zzha.zza) null, this.zzPS.zzSC, this.zzPS.zzSD, this.zzPS.zzSE, this.zzKY != null ? this.zzKY.zzKQ : false, this.zzPS.zzSG, this.zzQe != null ? zzg(this.zzQe.zzgU()) : null, this.zzPS.zzKI, this.zzPS.zzSJ);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        r5 = r5.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzh(long r4) throws com.google.android.gms.internal.zzlp.zza {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzPU
            monitor-enter(r0)
            com.google.android.gms.internal.zzjp r4 = r3.zzi(r4)     // Catch:{ all -> 0x0093 }
            r3.zzQe = r4     // Catch:{ all -> 0x0093 }
            monitor-exit(r0)     // Catch:{ all -> 0x0093 }
            java.util.ArrayList r4 = new java.util.ArrayList
            com.google.android.gms.internal.zzjr r5 = r3.zzKY
            java.util.List<com.google.android.gms.internal.zzjq> r5 = r5.zzKD
            r4.<init>(r5)
            com.google.android.gms.internal.zzpb$zza r5 = r3.zzPR
            com.google.android.gms.internal.zzmk r5 = r5.zzTi
            com.google.android.gms.internal.zzec r5 = r5.zzRy
            android.os.Bundle r5 = r5.zzzd
            java.lang.String r0 = "com.google.ads.mediation.admob.AdMobAdapter"
            r1 = 0
            if (r5 == 0) goto L_0x002d
            android.os.Bundle r5 = r5.getBundle(r0)
            if (r5 == 0) goto L_0x002d
            java.lang.String r2 = "_skipMediation"
            boolean r5 = r5.getBoolean(r2)
            goto L_0x002e
        L_0x002d:
            r5 = r1
        L_0x002e:
            if (r5 == 0) goto L_0x004c
            java.util.ListIterator r5 = r4.listIterator()
        L_0x0034:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L_0x004c
            java.lang.Object r2 = r5.next()
            com.google.android.gms.internal.zzjq r2 = (com.google.android.gms.internal.zzjq) r2
            java.util.List<java.lang.String> r2 = r2.zzKp
            boolean r2 = r2.contains(r0)
            if (r2 != 0) goto L_0x0034
            r5.remove()
            goto L_0x0034
        L_0x004c:
            com.google.android.gms.internal.zzjp r5 = r3.zzQe
            com.google.android.gms.internal.zzjv r4 = r5.zzd(r4)
            r3.zzQf = r4
            com.google.android.gms.internal.zzjv r4 = r3.zzQf
            int r4 = r4.zzLh
            switch(r4) {
                case 0: goto L_0x0081;
                case 1: goto L_0x0078;
                default: goto L_0x005b;
            }
        L_0x005b:
            com.google.android.gms.internal.zzlp$zza r4 = new com.google.android.gms.internal.zzlp$zza
            com.google.android.gms.internal.zzjv r5 = r3.zzQf
            int r5 = r5.zzLh
            r0 = 40
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            java.lang.String r0 = "Unexpected mediation result: "
            r2.append(r0)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            r4.<init>(r5, r1)
            throw r4
        L_0x0078:
            com.google.android.gms.internal.zzlp$zza r4 = new com.google.android.gms.internal.zzlp$zza
            java.lang.String r5 = "No fill from any mediation ad networks."
            r0 = 3
            r4.<init>(r5, r0)
            throw r4
        L_0x0081:
            com.google.android.gms.internal.zzjv r4 = r3.zzQf
            com.google.android.gms.internal.zzjq r4 = r4.zzLi
            if (r4 == 0) goto L_0x0092
            com.google.android.gms.internal.zzjv r4 = r3.zzQf
            com.google.android.gms.internal.zzjq r4 = r4.zzLi
            java.lang.String r4 = r4.zzKy
            if (r4 == 0) goto L_0x0092
            r3.zziS()
        L_0x0092:
            return
        L_0x0093:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0093 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlu.zzh(long):void");
    }

    /* access modifiers changed from: package-private */
    public zzjp zzi(long j) {
        if (this.zzKY.zzKO != -1) {
            return new zzjx(this.mContext, this.zzPR.zzTi, this.zzsz, this.zzKY, this.zzPS.zzzB, this.zzPS.zzzD, j, zzgd.zzDM.get().longValue(), 2);
        }
        Context context = this.mContext;
        zzmk zzmk = this.zzPR.zzTi;
        zzka zzka = this.zzsz;
        zzjr zzjr = this.zzKY;
        boolean z = this.zzPS.zzzB;
        return new zzjy(context, zzmk, zzka, zzjr, z, this.zzPS.zzzD, j, zzgd.zzDM.get().longValue(), this.zzsn);
    }
}
