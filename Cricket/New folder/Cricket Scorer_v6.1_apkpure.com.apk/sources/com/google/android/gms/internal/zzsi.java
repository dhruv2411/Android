package com.google.android.gms.internal;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zze;
import com.google.android.gms.analytics.zzf;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class zzsi extends zzsa {
    private boolean mStarted;
    private final zzsg zzaeA;
    private final zztf zzaeB;
    private final zzte zzaeC;
    private final zzsf zzaeD;
    private long zzaeE = Long.MIN_VALUE;
    private final zzsr zzaeF;
    private final zzsr zzaeG;
    private final zztj zzaeH;
    private long zzaeI;
    private boolean zzaeJ;

    protected zzsi(zzsc zzsc, zzsd zzsd) {
        super(zzsc);
        zzac.zzw(zzsd);
        this.zzaeC = zzsd.zzk(zzsc);
        this.zzaeA = zzsd.zzm(zzsc);
        this.zzaeB = zzsd.zzn(zzsc);
        this.zzaeD = zzsd.zzo(zzsc);
        this.zzaeH = new zztj(zznR());
        this.zzaeF = new zzsr(zzsc) {
            public void run() {
                zzsi.this.zzoB();
            }
        };
        this.zzaeG = new zzsr(zzsc) {
            public void run() {
                zzsi.this.zzoC();
            }
        };
    }

    private void zza(zzse zzse, zzrl zzrl) {
        zzac.zzw(zzse);
        zzac.zzw(zzrl);
        zza zza = new zza(zznQ());
        zza.zzbo(zzse.zzok());
        zza.enableAdvertisingIdCollection(zzse.zzol());
        zze zzmo = zza.zzmo();
        zzrt zzrt = (zzrt) zzmo.zzb(zzrt.class);
        zzrt.zzbE("data");
        zzrt.zzT(true);
        zzmo.zza((zzf) zzrl);
        zzro zzro = (zzro) zzmo.zzb(zzro.class);
        zzrk zzrk = (zzrk) zzmo.zzb(zzrk.class);
        for (Map.Entry next : zzse.zzfE().entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if ("an".equals(str)) {
                zzrk.setAppName(str2);
            } else if ("av".equals(str)) {
                zzrk.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                zzrk.setAppId(str2);
            } else if ("aiid".equals(str)) {
                zzrk.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                zzrt.setUserId(str2);
            } else {
                zzro.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", zzse.zzok(), zzrl);
        zzmo.zzq(zznW().zzqe());
        zzmo.zzmG();
    }

    private boolean zzbW(String str) {
        return zzadg.zzbi(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    /* access modifiers changed from: private */
    public void zzoB() {
        zzb((zzsu) new zzsu() {
            public void zzf(Throwable th) {
                zzsi.this.zzoH();
            }
        });
    }

    /* access modifiers changed from: private */
    public void zzoC() {
        try {
            this.zzaeA.zzot();
            zzoH();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzaeG.zzy(86400000);
    }

    private boolean zzoI() {
        return !this.zzaeJ && zzoO() > 0;
    }

    private void zzoJ() {
        zzst zznV = zznV();
        if (zznV.zzpD() && !zznV.zzcy()) {
            long zzou = zzou();
            if (zzou != 0 && Math.abs(zznR().currentTimeMillis() - zzou) <= zznT().zzpf()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zznT().zzpe()));
                zznV.schedule();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0027, code lost:
        if (r6 > 0) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzoK() {
        /*
            r10 = this;
            r10.zzoJ()
            long r0 = r10.zzoO()
            com.google.android.gms.internal.zztg r2 = r10.zznW()
            long r2 = r2.zzqg()
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x002a
            com.google.android.gms.common.util.zze r6 = r10.zznR()
            long r6 = r6.currentTimeMillis()
            long r8 = r6 - r2
            long r2 = java.lang.Math.abs(r8)
            long r6 = r0 - r2
            int r2 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x002a
            goto L_0x0036
        L_0x002a:
            com.google.android.gms.internal.zzsp r2 = r10.zznT()
            long r2 = r2.zzpc()
            long r6 = java.lang.Math.min(r2, r0)
        L_0x0036:
            java.lang.String r0 = "Dispatch scheduled (ms)"
            java.lang.Long r1 = java.lang.Long.valueOf(r6)
            r10.zza(r0, r1)
            com.google.android.gms.internal.zzsr r0 = r10.zzaeF
            boolean r0 = r0.zzcy()
            if (r0 == 0) goto L_0x005b
            r0 = 1
            com.google.android.gms.internal.zzsr r2 = r10.zzaeF
            long r2 = r2.zzpA()
            long r4 = r6 + r2
            long r0 = java.lang.Math.max(r0, r4)
            com.google.android.gms.internal.zzsr r2 = r10.zzaeF
            r2.zzz(r0)
            return
        L_0x005b:
            com.google.android.gms.internal.zzsr r0 = r10.zzaeF
            r0.zzy(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsi.zzoK():void");
    }

    private void zzoL() {
        zzoM();
        zzoN();
    }

    private void zzoM() {
        if (this.zzaeF.zzcy()) {
            zzbP("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzaeF.cancel();
    }

    private void zzoN() {
        zzst zznV = zznV();
        if (zznV.zzcy()) {
            zznV.cancel();
        }
    }

    private void zzoz() {
        String str;
        zzmR();
        Context context = zznQ().getContext();
        if (!zzth.zzak(context)) {
            zzbS("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzti.zzal(context)) {
            zzbT("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzak(context)) {
            str = "CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.";
        } else if (!CampaignTrackingService.zzal(context)) {
            str = "CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.";
        } else {
            return;
        }
        zzbS(str);
    }

    /* access modifiers changed from: protected */
    public void onServiceConnected() {
        zzmR();
        zzoE();
    }

    /* access modifiers changed from: package-private */
    public void start() {
        zzob();
        zzac.zza(!this.mStarted, (Object) "Analytics backend already started");
        this.mStarted = true;
        zznU().zzg(new Runnable() {
            public void run() {
                zzsi.this.zzoA();
            }
        });
    }

    public void zzW(boolean z) {
        zzoH();
    }

    public long zza(zzse zzse, boolean z) {
        zzac.zzw(zzse);
        zzob();
        zzmR();
        try {
            this.zzaeA.beginTransaction();
            this.zzaeA.zza(zzse.zzoj(), zzse.zzmy());
            long zza = this.zzaeA.zza(zzse.zzoj(), zzse.zzmy(), zzse.zzok());
            if (!z) {
                zzse.zzs(zza);
            } else {
                zzse.zzs(zza + 1);
            }
            this.zzaeA.zzb(zzse);
            this.zzaeA.setTransactionSuccessful();
            try {
                this.zzaeA.endTransaction();
                return zza;
            } catch (SQLiteException e) {
                zze("Failed to end transaction", e);
                return zza;
            }
        } catch (SQLiteException e2) {
            zze("Failed to update Analytics property", e2);
            try {
                this.zzaeA.endTransaction();
                return -1;
            } catch (SQLiteException e3) {
                zze("Failed to end transaction", e3);
                return -1;
            }
        } catch (Throwable th) {
            try {
                this.zzaeA.endTransaction();
            } catch (SQLiteException e4) {
                zze("Failed to end transaction", e4);
            }
            throw th;
        }
    }

    public void zza(zzsu zzsu, long j) {
        zzh.zzmR();
        zzob();
        long zzqg = zznW().zzqg();
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(zzqg != 0 ? Math.abs(zznR().currentTimeMillis() - zzqg) : -1));
        zzoD();
        try {
            zzoF();
            zznW().zzqh();
            zzoH();
            if (zzsu != null) {
                zzsu.zzf((Throwable) null);
            }
            if (this.zzaeI != j) {
                this.zzaeC.zzpZ();
            }
        } catch (Throwable th) {
            zze("Local dispatch failed", th);
            zznW().zzqh();
            zzoH();
            if (zzsu != null) {
                zzsu.zzf(th);
            }
        }
    }

    public void zza(zzsz zzsz) {
        zzac.zzw(zzsz);
        zzh.zzmR();
        zzob();
        if (this.zzaeJ) {
            zzbQ("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzsz);
        }
        zzsz zzf = zzf(zzsz);
        zzoD();
        if (this.zzaeD.zzb(zzf)) {
            zzbQ("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzaeA.zzc(zzf);
            zzoH();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zznS().zza(zzf, "deliver: failed to insert hit to database");
        }
    }

    public void zzb(zzsu zzsu) {
        zza(zzsu, this.zzaeI);
    }

    public void zzbX(String str) {
        zzac.zzdr(str);
        zzmR();
        zzrl zza = zztm.zza(zznS(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzqi = zznW().zzqi();
        if (str.equals(zzqi)) {
            zzbS("Ignoring duplicate install campaign");
        } else if (!TextUtils.isEmpty(zzqi)) {
            zzd("Ignoring multiple install campaigns. original, new", zzqi, str);
        } else {
            zznW().zzcb(str);
            if (zznW().zzqf().zzA(zznT().zzpy())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzse zza2 : this.zzaeA.zzw(0)) {
                zza(zza2, zza);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzc(zzse zzse) {
        zzmR();
        zzb("Sending first hit to property", zzse.zzok());
        if (!zznW().zzqf().zzA(zznT().zzpy())) {
            String zzqi = zznW().zzqi();
            if (!TextUtils.isEmpty(zzqi)) {
                zzrl zza = zztm.zza(zznS(), zzqi);
                zzb("Found relevant installation campaign", zza);
                zza(zzse, zza);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public zzsz zzf(zzsz zzsz) {
        Pair<String, Long> zzqm;
        if (!TextUtils.isEmpty(zzsz.zzpU()) || (zzqm = zznW().zzqj().zzqm()) == null) {
            return zzsz;
        }
        String str = (String) zzqm.first;
        String valueOf = String.valueOf((Long) zzqm.second);
        StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(":");
        sb.append(str);
        String sb2 = sb.toString();
        HashMap hashMap = new HashMap(zzsz.zzfE());
        hashMap.put("_m", sb2);
        return zzsz.zza(this, zzsz, hashMap);
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        this.zzaeA.initialize();
        this.zzaeB.initialize();
        this.zzaeD.initialize();
    }

    public void zznK() {
        zzh.zzmR();
        zzob();
        zzbP("Delete all hits from local store");
        try {
            this.zzaeA.zzor();
            this.zzaeA.zzos();
            zzoH();
        } catch (SQLiteException e) {
            zzd("Failed to delete hits from store", e);
        }
        zzoD();
        if (this.zzaeD.zzon()) {
            zzbP("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    public void zznN() {
        zzh.zzmR();
        zzob();
        zzbP("Service disconnected");
    }

    /* access modifiers changed from: package-private */
    public void zznP() {
        zzmR();
        this.zzaeI = zznR().currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void zzoA() {
        zzob();
        zzoz();
        zznW().zzqe();
        if (!zzbW("android.permission.ACCESS_NETWORK_STATE")) {
            zzbT("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzoP();
        }
        if (!zzbW("android.permission.INTERNET")) {
            zzbT("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzoP();
        }
        if (zzti.zzal(getContext())) {
            zzbP("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzbS("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzaeJ && !this.zzaeA.isEmpty()) {
            zzoD();
        }
        zzoH();
    }

    /* access modifiers changed from: protected */
    public void zzoD() {
        if (!this.zzaeJ && zznT().zzoX() && !this.zzaeD.isConnected()) {
            if (this.zzaeH.zzA(zznT().zzps())) {
                this.zzaeH.start();
                zzbP("Connecting to service");
                if (this.zzaeD.connect()) {
                    zzbP("Connected to service");
                    this.zzaeH.clear();
                    onServiceConnected();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004a A[LOOP:1: B:16:0x004a->B:24:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0046 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzoE() {
        /*
            r5 = this;
            com.google.android.gms.analytics.zzh.zzmR()
            r5.zzob()
            com.google.android.gms.internal.zzsp r0 = r5.zznT()
            boolean r0 = r0.zzoX()
            if (r0 != 0) goto L_0x0015
            java.lang.String r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService"
            r5.zzbS(r0)
        L_0x0015:
            com.google.android.gms.internal.zzsf r0 = r5.zzaeD
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0023
            java.lang.String r0 = "Service not connected"
            r5.zzbP(r0)
            return
        L_0x0023:
            com.google.android.gms.internal.zzsg r0 = r5.zzaeA
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x002c
            return
        L_0x002c:
            java.lang.String r0 = "Dispatching local hits to device AnalyticsService"
            r5.zzbP(r0)
        L_0x0031:
            com.google.android.gms.internal.zzsg r0 = r5.zzaeA     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.zzsp r1 = r5.zznT()     // Catch:{ SQLiteException -> 0x007a }
            int r1 = r1.zzpg()     // Catch:{ SQLiteException -> 0x007a }
            long r1 = (long) r1     // Catch:{ SQLiteException -> 0x007a }
            java.util.List r0 = r0.zzu(r1)     // Catch:{ SQLiteException -> 0x007a }
            boolean r1 = r0.isEmpty()     // Catch:{ SQLiteException -> 0x007a }
            if (r1 == 0) goto L_0x004a
            r5.zzoH()     // Catch:{ SQLiteException -> 0x007a }
            return
        L_0x004a:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0031
            r1 = 0
            java.lang.Object r1 = r0.get(r1)
            com.google.android.gms.internal.zzsz r1 = (com.google.android.gms.internal.zzsz) r1
            com.google.android.gms.internal.zzsf r2 = r5.zzaeD
            boolean r2 = r2.zzb((com.google.android.gms.internal.zzsz) r1)
            if (r2 != 0) goto L_0x0063
            r5.zzoH()
            return
        L_0x0063:
            r0.remove(r1)
            com.google.android.gms.internal.zzsg r2 = r5.zzaeA     // Catch:{ SQLiteException -> 0x0070 }
            long r3 = r1.zzpP()     // Catch:{ SQLiteException -> 0x0070 }
            r2.zzv(r3)     // Catch:{ SQLiteException -> 0x0070 }
            goto L_0x004a
        L_0x0070:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r5.zze(r1, r0)
            r5.zzoL()
            return
        L_0x007a:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from store"
            r5.zze(r1, r0)
            r5.zzoL()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsi.zzoE():void");
    }

    /* access modifiers changed from: protected */
    public boolean zzoF() {
        zzh.zzmR();
        zzob();
        zzbP("Dispatching a batch of local hits");
        boolean z = !this.zzaeD.isConnected();
        boolean z2 = !this.zzaeB.zzqa();
        if (!z || !z2) {
            long max = (long) Math.max(zznT().zzpg(), zznT().zzph());
            ArrayList arrayList = new ArrayList();
            long j = 0;
            while (true) {
                try {
                    this.zzaeA.beginTransaction();
                    arrayList.clear();
                    try {
                        List<zzsz> zzu = this.zzaeA.zzu(max);
                        if (zzu.isEmpty()) {
                            zzbP("Store is empty, nothing to dispatch");
                            zzoL();
                            try {
                                this.zzaeA.setTransactionSuccessful();
                                this.zzaeA.endTransaction();
                                return false;
                            } catch (SQLiteException e) {
                                zze("Failed to commit local dispatch transaction", e);
                                zzoL();
                                return false;
                            }
                        } else {
                            zza("Hits loaded from store. count", Integer.valueOf(zzu.size()));
                            for (zzsz zzpP : zzu) {
                                if (zzpP.zzpP() == j) {
                                    zzd("Database contains successfully uploaded hit", Long.valueOf(j), Integer.valueOf(zzu.size()));
                                    zzoL();
                                    try {
                                        this.zzaeA.setTransactionSuccessful();
                                        this.zzaeA.endTransaction();
                                        return false;
                                    } catch (SQLiteException e2) {
                                        zze("Failed to commit local dispatch transaction", e2);
                                        zzoL();
                                        return false;
                                    }
                                }
                            }
                            if (this.zzaeD.isConnected()) {
                                zzbP("Service connected, sending hits to the service");
                                while (true) {
                                    if (!zzu.isEmpty()) {
                                        zzsz zzsz = zzu.get(0);
                                        if (!this.zzaeD.zzb(zzsz)) {
                                            break;
                                        }
                                        j = Math.max(j, zzsz.zzpP());
                                        zzu.remove(zzsz);
                                        zzb("Hit sent do device AnalyticsService for delivery", zzsz);
                                        this.zzaeA.zzv(zzsz.zzpP());
                                        arrayList.add(Long.valueOf(zzsz.zzpP()));
                                    }
                                }
                            }
                            if (this.zzaeB.zzqa()) {
                                List<Long> zzt = this.zzaeB.zzt(zzu);
                                for (Long longValue : zzt) {
                                    j = Math.max(j, longValue.longValue());
                                }
                                try {
                                    this.zzaeA.zzr(zzt);
                                    arrayList.addAll(zzt);
                                } catch (SQLiteException e3) {
                                    zze("Failed to remove successfully uploaded hits", e3);
                                    zzoL();
                                    try {
                                        this.zzaeA.setTransactionSuccessful();
                                        this.zzaeA.endTransaction();
                                        return false;
                                    } catch (SQLiteException e4) {
                                        zze("Failed to commit local dispatch transaction", e4);
                                        zzoL();
                                        return false;
                                    }
                                }
                            }
                            if (arrayList.isEmpty()) {
                                try {
                                    this.zzaeA.setTransactionSuccessful();
                                    this.zzaeA.endTransaction();
                                    return false;
                                } catch (SQLiteException e5) {
                                    zze("Failed to commit local dispatch transaction", e5);
                                    zzoL();
                                    return false;
                                }
                            } else {
                                try {
                                    this.zzaeA.setTransactionSuccessful();
                                    this.zzaeA.endTransaction();
                                } catch (SQLiteException e6) {
                                    zze("Failed to commit local dispatch transaction", e6);
                                    zzoL();
                                    return false;
                                }
                            }
                        }
                    } catch (SQLiteException e7) {
                        zzd("Failed to read hits from persisted store", e7);
                        zzoL();
                        try {
                            this.zzaeA.setTransactionSuccessful();
                            this.zzaeA.endTransaction();
                            return false;
                        } catch (SQLiteException e8) {
                            zze("Failed to commit local dispatch transaction", e8);
                            zzoL();
                            return false;
                        }
                    }
                } catch (SQLiteException e9) {
                    zze("Failed to remove hit that was send for delivery", e9);
                    zzoL();
                    try {
                        this.zzaeA.setTransactionSuccessful();
                        this.zzaeA.endTransaction();
                        return false;
                    } catch (SQLiteException e10) {
                        zze("Failed to commit local dispatch transaction", e10);
                        zzoL();
                        return false;
                    }
                } catch (Throwable th) {
                    try {
                        this.zzaeA.setTransactionSuccessful();
                        this.zzaeA.endTransaction();
                        throw th;
                    } catch (SQLiteException e11) {
                        zze("Failed to commit local dispatch transaction", e11);
                        zzoL();
                        return false;
                    }
                }
            }
        } else {
            zzbP("No network or service available. Will retry later");
            return false;
        }
    }

    public void zzoG() {
        zzh.zzmR();
        zzob();
        zzbQ("Sync dispatching local hits");
        long j = this.zzaeI;
        zzoD();
        try {
            zzoF();
            zznW().zzqh();
            zzoH();
            if (this.zzaeI != j) {
                this.zzaeC.zzpZ();
            }
        } catch (Throwable th) {
            zze("Sync local dispatch failed", th);
            zzoH();
        }
    }

    public void zzoH() {
        boolean z;
        zznQ().zzmR();
        zzob();
        if (zzoI() && !this.zzaeA.isEmpty()) {
            if (!zzsw.zzafS.get().booleanValue()) {
                this.zzaeC.zzpX();
                z = this.zzaeC.isConnected();
            } else {
                z = true;
            }
            if (z) {
                zzoK();
                return;
            }
            zzoL();
            zzoJ();
            return;
        }
        this.zzaeC.unregister();
        zzoL();
    }

    public long zzoO() {
        if (this.zzaeE != Long.MIN_VALUE) {
            return this.zzaeE;
        }
        return zzmB().zzpK() ? ((long) zzmB().zzqB()) * 1000 : zznT().zzpd();
    }

    public void zzoP() {
        zzob();
        zzmR();
        this.zzaeJ = true;
        this.zzaeD.disconnect();
        zzoH();
    }

    public long zzou() {
        zzh.zzmR();
        zzob();
        try {
            return this.zzaeA.zzou();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    public void zzx(long j) {
        zzh.zzmR();
        zzob();
        if (j < 0) {
            j = 0;
        }
        this.zzaeE = j;
        zzoH();
    }
}
