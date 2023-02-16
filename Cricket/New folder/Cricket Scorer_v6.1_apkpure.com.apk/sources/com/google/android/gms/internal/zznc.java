package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzmt;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzme
public final class zznc extends zzmt.zza {
    private static zznc zzTv;
    private static final Object zztX = new Object();
    private final Context mContext;
    private final zznb zzTw;
    private final zzfw zzTx;
    private final zzji zzTy;

    zznc(Context context, zzfw zzfw, zznb zznb) {
        this.mContext = context;
        this.zzTw = zznb;
        this.zzTx = zzfw;
        this.zzTy = new zzji(context.getApplicationContext() != null ? context.getApplicationContext() : context, zzqh.zzlk(), zzfw.zzfq(), new zzpt<zzjf>(this) {
            /* renamed from: zza */
            public void zzd(zzjf zzjf) {
                zzjf.zza("/log", zzic.zzHL);
            }
        }, new zzji.zzb());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:116|117|118|119) */
    /* JADX WARNING: Code restructure failed: missing block: B:117:?, code lost:
        r1 = new com.google.android.gms.internal.zzmn(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x031c, code lost:
        com.google.android.gms.internal.zzpo.zzXC.post(new com.google.android.gms.internal.zznc.AnonymousClass3());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0326, code lost:
        return r1;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:116:0x0316 */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0233  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.zzmn zza(android.content.Context r21, com.google.android.gms.internal.zzji r22, com.google.android.gms.internal.zzfw r23, com.google.android.gms.internal.zznb r24, com.google.android.gms.internal.zzmk r25) {
        /*
            r9 = r21
            r10 = r24
            r11 = r25
            java.lang.String r1 = "Starting ad request from service using: AFMA_getAd"
            com.google.android.gms.internal.zzpk.zzbf(r1)
            com.google.android.gms.internal.zzgd.initialize(r21)
            com.google.android.gms.internal.zzgl r12 = new com.google.android.gms.internal.zzgl
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.internal.zzgd.zzBZ
            java.lang.Object r1 = r1.get()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            java.lang.String r2 = "load_ad"
            com.google.android.gms.internal.zzeg r3 = r11.zzvr
            java.lang.String r3 = r3.zzzy
            r12.<init>(r1, r2, r3)
            int r1 = r11.versionCode
            r13 = 1
            r14 = 0
            r2 = 10
            if (r1 <= r2) goto L_0x0044
            long r1 = r11.zzRO
            r3 = -1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0044
            long r1 = r11.zzRO
            com.google.android.gms.internal.zzgj r1 = r12.zzc((long) r1)
            java.lang.String[] r2 = new java.lang.String[r13]
            java.lang.String r3 = "cts"
            r2[r14] = r3
            r12.zza(r1, r2)
        L_0x0044:
            com.google.android.gms.internal.zzgj r15 = r12.zzfB()
            com.google.android.gms.internal.zzlc r1 = r10.zzTt
            com.google.android.gms.internal.zzqm r1 = r1.zzt(r9)
            com.google.android.gms.internal.zznm r2 = r10.zzTs
            java.util.concurrent.Future r2 = r2.zzB(r9)
            com.google.android.gms.internal.zzox r3 = r10.zzTn
            android.content.pm.PackageInfo r4 = r11.zzRz
            java.lang.String r4 = r4.packageName
            java.util.concurrent.Future r3 = r3.zzaS(r4)
            com.google.android.gms.internal.zzpa r4 = r10.zzTu
            com.google.android.gms.internal.zzqm r4 = r4.zzg(r11)
            com.google.android.gms.internal.zznj r5 = com.google.android.gms.ads.internal.zzw.zzcV()
            java.util.concurrent.Future r5 = r5.zzA(r9)
            com.google.android.gms.internal.zzqk r6 = new com.google.android.gms.internal.zzqk
            r8 = 0
            r6.<init>(r8)
            com.google.android.gms.internal.zzec r7 = r11.zzRy
            android.os.Bundle r7 = r7.extras
            if (r7 == 0) goto L_0x0082
            java.lang.String r13 = "_ad"
            java.lang.String r7 = r7.getString(r13)
            if (r7 == 0) goto L_0x0082
            r7 = 1
            goto L_0x0083
        L_0x0082:
            r7 = r14
        L_0x0083:
            boolean r13 = r11.zzRV
            if (r13 == 0) goto L_0x0091
            if (r7 != 0) goto L_0x0091
            com.google.android.gms.internal.zzjn r6 = r10.zzTq
            android.content.pm.ApplicationInfo r7 = r11.applicationInfo
            com.google.android.gms.internal.zzqm r6 = r6.zza(r7)
        L_0x0091:
            com.google.android.gms.internal.zzqk r7 = new com.google.android.gms.internal.zzqk
            r7.<init>(r8)
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r13 = com.google.android.gms.internal.zzgd.zzCS
            java.lang.Object r13 = r13.get()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00aa
            com.google.android.gms.internal.zzpa r7 = r10.zzTu
            com.google.android.gms.internal.zzqm r7 = r7.zzG(r9)
        L_0x00aa:
            int r13 = r11.versionCode
            r8 = 4
            if (r13 < r8) goto L_0x00b6
            android.os.Bundle r8 = r11.zzRF
            if (r8 == 0) goto L_0x00b6
            android.os.Bundle r8 = r11.zzRF
            goto L_0x00b7
        L_0x00b6:
            r8 = 0
        L_0x00b7:
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r13 = com.google.android.gms.internal.zzgd.zzCp
            java.lang.Object r13 = r13.get()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00ef
            com.google.android.gms.internal.zzng r13 = r10.zzTl
            if (r13 == 0) goto L_0x00ef
            if (r8 != 0) goto L_0x00e3
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r13 = com.google.android.gms.internal.zzgd.zzCq
            java.lang.Object r13 = r13.get()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00e3
            java.lang.String r8 = "contentInfo is not present, but we'll still launch the app index task"
            com.google.android.gms.internal.zzpk.v(r8)
            android.os.Bundle r8 = new android.os.Bundle
            r8.<init>()
        L_0x00e3:
            if (r8 == 0) goto L_0x00ef
            com.google.android.gms.internal.zznc$1 r13 = new com.google.android.gms.internal.zznc$1
            r13.<init>(r9, r11, r8)
            com.google.android.gms.internal.zzqm r13 = com.google.android.gms.internal.zzpn.zza(r13)
            goto L_0x00f0
        L_0x00ef:
            r13 = 0
        L_0x00f0:
            com.google.android.gms.internal.zzpo r14 = com.google.android.gms.ads.internal.zzw.zzcM()
            r17 = r12
            java.lang.String r12 = r21.getPackageName()
            r18 = r15
            java.lang.String r15 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r12 = r14.zze(r9, r12, r15)
            if (r12 == 0) goto L_0x0117
            java.lang.String r12 = "connectivity"
            java.lang.Object r12 = r9.getSystemService(r12)
            android.net.ConnectivityManager r12 = (android.net.ConnectivityManager) r12
            android.net.NetworkInfo r12 = r12.getActiveNetworkInfo()
            if (r12 != 0) goto L_0x0117
            java.lang.String r12 = "Device is offline."
            com.google.android.gms.internal.zzpk.zzbf(r12)
        L_0x0117:
            int r12 = r11.versionCode
            r14 = 7
            if (r12 < r14) goto L_0x011f
            java.lang.String r12 = r11.zzRL
            goto L_0x0127
        L_0x011f:
            java.util.UUID r12 = java.util.UUID.randomUUID()
            java.lang.String r12 = r12.toString()
        L_0x0127:
            com.google.android.gms.internal.zzne r15 = new com.google.android.gms.internal.zzne
            android.content.pm.ApplicationInfo r14 = r11.applicationInfo
            java.lang.String r14 = r14.packageName
            r15.<init>(r12, r14)
            com.google.android.gms.internal.zzec r14 = r11.zzRy
            android.os.Bundle r14 = r14.extras
            if (r14 == 0) goto L_0x0149
            com.google.android.gms.internal.zzec r14 = r11.zzRy
            android.os.Bundle r14 = r14.extras
            r19 = r15
            java.lang.String r15 = "_ad"
            java.lang.String r14 = r14.getString(r15)
            if (r14 == 0) goto L_0x014b
            com.google.android.gms.internal.zzmn r1 = com.google.android.gms.internal.zznd.zza((android.content.Context) r9, (com.google.android.gms.internal.zzmk) r11, (java.lang.String) r14)
            return r1
        L_0x0149:
            r19 = r15
        L_0x014b:
            com.google.android.gms.internal.zzfy r14 = r10.zzTo
            java.util.List r14 = r14.zza(r11)
            if (r13 == 0) goto L_0x017c
            java.lang.String r15 = "Waiting for app index fetching task."
            com.google.android.gms.internal.zzpk.v(r15)     // Catch:{ InterruptedException | ExecutionException -> 0x0175, TimeoutException -> 0x016f }
            com.google.android.gms.internal.zzfz<java.lang.Long> r15 = com.google.android.gms.internal.zzgd.zzCr     // Catch:{ InterruptedException | ExecutionException -> 0x0175, TimeoutException -> 0x016f }
            java.lang.Object r15 = r15.get()     // Catch:{ InterruptedException | ExecutionException -> 0x0175, TimeoutException -> 0x016f }
            java.lang.Long r15 = (java.lang.Long) r15     // Catch:{ InterruptedException | ExecutionException -> 0x0175, TimeoutException -> 0x016f }
            long r9 = r15.longValue()     // Catch:{ InterruptedException | ExecutionException -> 0x0175, TimeoutException -> 0x016f }
            java.util.concurrent.TimeUnit r15 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException | ExecutionException -> 0x0175, TimeoutException -> 0x016f }
            r13.get(r9, r15)     // Catch:{ InterruptedException | ExecutionException -> 0x0175, TimeoutException -> 0x016f }
            java.lang.String r9 = "App index fetching task completed."
            com.google.android.gms.internal.zzpk.v(r9)     // Catch:{ InterruptedException | ExecutionException -> 0x0175, TimeoutException -> 0x016f }
            goto L_0x017c
        L_0x016f:
            java.lang.String r9 = "Timed out waiting for app index fetching task"
            com.google.android.gms.internal.zzpk.zzbf(r9)
            goto L_0x017c
        L_0x0175:
            r0 = move-exception
            r9 = r0
            java.lang.String r10 = "Failed to fetch app index signal"
            com.google.android.gms.internal.zzpk.zzc(r10, r9)
        L_0x017c:
            com.google.android.gms.internal.zzfz<java.lang.Long> r9 = com.google.android.gms.internal.zzgd.zzEX
            java.lang.Object r9 = r9.get()
            java.lang.Long r9 = (java.lang.Long) r9
            java.lang.Object r1 = zza(r1, (java.lang.Long) r9)
            android.os.Bundle r1 = (android.os.Bundle) r1
            com.google.android.gms.internal.zzfz<java.lang.Long> r9 = com.google.android.gms.internal.zzgd.zzDH
            java.lang.Object r9 = r9.get()
            java.lang.Long r9 = (java.lang.Long) r9
            java.lang.Object r2 = zza(r2, (java.lang.Long) r9)
            com.google.android.gms.internal.zznm$zza r2 = (com.google.android.gms.internal.zznm.zza) r2
            com.google.android.gms.internal.zzfz<java.lang.Long> r9 = com.google.android.gms.internal.zzgd.zzEF
            java.lang.Object r9 = r9.get()
            java.lang.Long r9 = (java.lang.Long) r9
            java.lang.Object r6 = zza(r6, (java.lang.Long) r9)
            android.location.Location r6 = (android.location.Location) r6
            com.google.android.gms.internal.zzfz<java.lang.Long> r9 = com.google.android.gms.internal.zzgd.zzCT
            java.lang.Object r9 = r9.get()
            java.lang.Long r9 = (java.lang.Long) r9
            java.lang.Object r7 = zza(r7, (java.lang.Long) r9)
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r7 = (com.google.android.gms.ads.identifier.AdvertisingIdClient.Info) r7
            java.lang.Object r4 = r4.get()     // Catch:{ Exception -> 0x01bb }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x01bb }
            goto L_0x01cc
        L_0x01bb:
            r0 = move-exception
            r4 = r0
            com.google.android.gms.internal.zzpe r9 = com.google.android.gms.ads.internal.zzw.zzcQ()
            java.lang.String r10 = "AdRequestServiceImpl.loadAdAsync.qs"
            r9.zza((java.lang.Throwable) r4, (java.lang.String) r10)
            java.lang.String r9 = "Error fetching qs signals. Continuing."
            com.google.android.gms.internal.zzpk.zzc(r9, r4)
            r4 = 0
        L_0x01cc:
            java.lang.Object r3 = r3.get()     // Catch:{ Exception -> 0x01d4 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x01d4 }
            r9 = r3
            goto L_0x01e5
        L_0x01d4:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.internal.zzpe r9 = com.google.android.gms.ads.internal.zzw.zzcQ()
            java.lang.String r10 = "AdRequestServiceImpl.loadAdAsync.ds"
            r9.zza((java.lang.Throwable) r3, (java.lang.String) r10)
            java.lang.String r9 = "Error fetching drt signals. Continuing."
            com.google.android.gms.internal.zzpk.zzc(r9, r3)
            r9 = 0
        L_0x01e5:
            java.lang.Object r3 = r5.get()     // Catch:{ Throwable -> 0x0332 }
            com.google.android.gms.internal.zzni r3 = (com.google.android.gms.internal.zzni) r3     // Catch:{ Throwable -> 0x0332 }
            com.google.android.gms.internal.zzna r5 = new com.google.android.gms.internal.zzna
            r5.<init>()
            com.google.android.gms.internal.zzna r5 = r5.zzf((com.google.android.gms.internal.zzmk) r11)
            com.google.android.gms.internal.zzna r3 = r5.zza((com.google.android.gms.internal.zzni) r3)
            com.google.android.gms.internal.zzna r2 = r3.zza((com.google.android.gms.internal.zznm.zza) r2)
            com.google.android.gms.internal.zzna r2 = r2.zzc(r6)
            com.google.android.gms.internal.zzna r1 = r2.zze(r1)
            com.google.android.gms.internal.zzna r1 = r1.zzaK(r4)
            com.google.android.gms.internal.zzna r1 = r1.zzb(r7)
            com.google.android.gms.internal.zzna r1 = r1.zzk(r14)
            com.google.android.gms.internal.zzna r1 = r1.zzf((android.os.Bundle) r8)
            com.google.android.gms.internal.zzna r1 = r1.zzaL(r9)
            r10 = r24
            com.google.android.gms.internal.zzdu r2 = r10.zzTm
            r13 = r21
            org.json.JSONObject r2 = r2.zzj(r13)
            com.google.android.gms.internal.zzna r1 = r1.zzg(r2)
            org.json.JSONObject r1 = com.google.android.gms.internal.zznd.zza((android.content.Context) r13, (com.google.android.gms.internal.zzna) r1)
            if (r1 != 0) goto L_0x0233
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn
            r2 = 0
            r1.<init>(r2)
            return r1
        L_0x0233:
            int r2 = r11.versionCode
            r3 = 7
            if (r2 >= r3) goto L_0x023d
            java.lang.String r2 = "request_id"
            r1.put(r2, r12)     // Catch:{ JSONException -> 0x023d }
        L_0x023d:
            java.lang.String r7 = r1.toString()
            r1 = 1
            java.lang.String[] r2 = new java.lang.String[r1]
            java.lang.String r1 = "arc"
            r3 = 0
            r2[r3] = r1
            r12 = r17
            r14 = r18
            r12.zza(r14, r2)
            com.google.android.gms.internal.zzgj r6 = r12.zzfB()
            android.os.Handler r1 = com.google.android.gms.internal.zzpo.zzXC
            com.google.android.gms.internal.zznc$2 r8 = new com.google.android.gms.internal.zznc$2
            r2 = r8
            r3 = r22
            r4 = r19
            r5 = r12
            r2.<init>(r4, r5, r6, r7)
            r1.post(r8)
            r15 = r19
            java.util.concurrent.Future r1 = r15.zzjw()     // Catch:{ Exception -> 0x0316 }
            r2 = 10
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x0316 }
            java.lang.Object r1 = r1.get(r2, r4)     // Catch:{ Exception -> 0x0316 }
            r6 = r1
            com.google.android.gms.internal.zznh r6 = (com.google.android.gms.internal.zznh) r6     // Catch:{ Exception -> 0x0316 }
            if (r6 != 0) goto L_0x0288
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x0313 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0313 }
            android.os.Handler r2 = com.google.android.gms.internal.zzpo.zzXC
            com.google.android.gms.internal.zznc$3 r3 = new com.google.android.gms.internal.zznc$3
            r3.<init>(r13, r15, r11)
            r2.post(r3)
            return r1
        L_0x0288:
            int r1 = r6.getErrorCode()     // Catch:{ all -> 0x0313 }
            r2 = -2
            if (r1 == r2) goto L_0x02a3
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x0313 }
            int r2 = r6.getErrorCode()     // Catch:{ all -> 0x0313 }
            r1.<init>(r2)     // Catch:{ all -> 0x0313 }
            android.os.Handler r2 = com.google.android.gms.internal.zzpo.zzXC
            com.google.android.gms.internal.zznc$3 r3 = new com.google.android.gms.internal.zznc$3
            r3.<init>(r13, r15, r11)
            r2.post(r3)
            return r1
        L_0x02a3:
            com.google.android.gms.internal.zzgj r1 = r12.zzfF()     // Catch:{ all -> 0x0313 }
            if (r1 == 0) goto L_0x02b8
            com.google.android.gms.internal.zzgj r1 = r12.zzfF()     // Catch:{ all -> 0x0313 }
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]     // Catch:{ all -> 0x0313 }
            java.lang.String r2 = "rur"
            r4 = 0
            r3[r4] = r2     // Catch:{ all -> 0x0313 }
            r12.zza(r1, r3)     // Catch:{ all -> 0x0313 }
        L_0x02b8:
            java.lang.String r1 = r6.zzjB()     // Catch:{ all -> 0x0313 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0313 }
            if (r1 != 0) goto L_0x02cd
            java.lang.String r1 = r6.zzjB()     // Catch:{ all -> 0x0313 }
            com.google.android.gms.internal.zzmn r8 = com.google.android.gms.internal.zznd.zza((android.content.Context) r13, (com.google.android.gms.internal.zzmk) r11, (java.lang.String) r1)     // Catch:{ all -> 0x0313 }
            r16 = r8
            goto L_0x02cf
        L_0x02cd:
            r16 = 0
        L_0x02cf:
            if (r16 != 0) goto L_0x02ec
            java.lang.String r1 = r6.getUrl()     // Catch:{ all -> 0x0313 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0313 }
            if (r1 != 0) goto L_0x02ec
            com.google.android.gms.internal.zzqh r1 = r11.zzvn     // Catch:{ all -> 0x0313 }
            java.lang.String r3 = r1.zzba     // Catch:{ all -> 0x0313 }
            java.lang.String r4 = r6.getUrl()     // Catch:{ all -> 0x0313 }
            r1 = r11
            r2 = r13
            r5 = r9
            r7 = r12
            r8 = r10
            com.google.android.gms.internal.zzmn r16 = zza(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0313 }
        L_0x02ec:
            if (r16 != 0) goto L_0x02f5
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x0313 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0313 }
            goto L_0x02f7
        L_0x02f5:
            r1 = r16
        L_0x02f7:
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ all -> 0x0313 }
            java.lang.String r3 = "tts"
            r4 = 0
            r2[r4] = r3     // Catch:{ all -> 0x0313 }
            r12.zza(r14, r2)     // Catch:{ all -> 0x0313 }
            java.lang.String r2 = r12.zzfD()     // Catch:{ all -> 0x0313 }
            r1.zzSA = r2     // Catch:{ all -> 0x0313 }
            android.os.Handler r2 = com.google.android.gms.internal.zzpo.zzXC
            com.google.android.gms.internal.zznc$3 r3 = new com.google.android.gms.internal.zznc$3
            r3.<init>(r13, r15, r11)
            r2.post(r3)
            return r1
        L_0x0313:
            r0 = move-exception
            r1 = r0
            goto L_0x0327
        L_0x0316:
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x0313 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0313 }
            android.os.Handler r2 = com.google.android.gms.internal.zzpo.zzXC
            com.google.android.gms.internal.zznc$3 r3 = new com.google.android.gms.internal.zznc$3
            r3.<init>(r13, r15, r11)
            r2.post(r3)
            return r1
        L_0x0327:
            android.os.Handler r2 = com.google.android.gms.internal.zzpo.zzXC
            com.google.android.gms.internal.zznc$3 r3 = new com.google.android.gms.internal.zznc$3
            r3.<init>(r13, r15, r11)
            r2.post(r3)
            throw r1
        L_0x0332:
            r0 = move-exception
            r1 = r0
            com.google.android.gms.internal.zzpe r2 = com.google.android.gms.ads.internal.zzw.zzcQ()
            java.lang.String r3 = "AdRequestServiceImpl.loadAdAsync.di"
            r2.zza((java.lang.Throwable) r1, (java.lang.String) r3)
            java.lang.String r2 = "Error fetching device info. This is not recoverable."
            com.google.android.gms.internal.zzpk.zzc(r2, r1)
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn
            r2 = 0
            r1.<init>(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zznc.zza(android.content.Context, com.google.android.gms.internal.zzji, com.google.android.gms.internal.zzfw, com.google.android.gms.internal.zznb, com.google.android.gms.internal.zzmk):com.google.android.gms.internal.zzmn");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00bf, code lost:
        r6 = r6.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r9 = new java.io.InputStreamReader(r11.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r10 = com.google.android.gms.ads.internal.zzw.zzcM().zza(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        com.google.android.gms.common.util.zzp.zzb(r9);
        zza(r6, r3, r10, r1);
        r5.zzb(r6, r3, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00dd, code lost:
        if (r2 == null) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00df, code lost:
        r2.zza(r4, "ufe");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ea, code lost:
        r1 = r5.zzj(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f1, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f3, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f6, code lost:
        r1 = r0;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        com.google.android.gms.common.util.zzp.zzb(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fb, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzmn zza(com.google.android.gms.internal.zzmk r18, android.content.Context r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, com.google.android.gms.internal.zznh r23, com.google.android.gms.internal.zzgl r24, com.google.android.gms.internal.zznb r25) {
        /*
            r1 = r18
            r2 = r24
            if (r2 == 0) goto L_0x000b
            com.google.android.gms.internal.zzgj r4 = r24.zzfB()
            goto L_0x000c
        L_0x000b:
            r4 = 0
        L_0x000c:
            com.google.android.gms.internal.zznf r5 = new com.google.android.gms.internal.zznf     // Catch:{ IOException -> 0x016f }
            java.lang.String r6 = r23.zzjy()     // Catch:{ IOException -> 0x016f }
            r5.<init>(r1, r6)     // Catch:{ IOException -> 0x016f }
            java.lang.String r6 = "AdRequestServiceImpl: Sending request: "
            java.lang.String r7 = java.lang.String.valueOf(r21)     // Catch:{ IOException -> 0x016f }
            int r8 = r7.length()     // Catch:{ IOException -> 0x016f }
            if (r8 == 0) goto L_0x0026
            java.lang.String r6 = r6.concat(r7)     // Catch:{ IOException -> 0x016f }
            goto L_0x002c
        L_0x0026:
            java.lang.String r7 = new java.lang.String     // Catch:{ IOException -> 0x016f }
            r7.<init>(r6)     // Catch:{ IOException -> 0x016f }
            r6 = r7
        L_0x002c:
            com.google.android.gms.internal.zzpk.zzbf(r6)     // Catch:{ IOException -> 0x016f }
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException -> 0x016f }
            r7 = r21
            r6.<init>(r7)     // Catch:{ IOException -> 0x016f }
            com.google.android.gms.common.util.zze r7 = com.google.android.gms.ads.internal.zzw.zzcS()     // Catch:{ IOException -> 0x016f }
            long r7 = r7.elapsedRealtime()     // Catch:{ IOException -> 0x016f }
            r9 = 0
            r10 = r9
        L_0x0040:
            java.net.URLConnection r11 = r6.openConnection()     // Catch:{ IOException -> 0x016f }
            java.net.HttpURLConnection r11 = (java.net.HttpURLConnection) r11     // Catch:{ IOException -> 0x016f }
            com.google.android.gms.internal.zzpo r12 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x0169 }
            r13 = r19
            r14 = r20
            r12.zza((android.content.Context) r13, (java.lang.String) r14, (boolean) r9, (java.net.HttpURLConnection) r11)     // Catch:{ all -> 0x0169 }
            boolean r12 = android.text.TextUtils.isEmpty(r22)     // Catch:{ all -> 0x0169 }
            if (r12 != 0) goto L_0x0065
            boolean r12 = r23.zzjA()     // Catch:{ all -> 0x0169 }
            if (r12 == 0) goto L_0x0065
            java.lang.String r12 = "x-afma-drt-cookie"
            r15 = r22
            r11.addRequestProperty(r12, r15)     // Catch:{ all -> 0x0169 }
            goto L_0x0067
        L_0x0065:
            r15 = r22
        L_0x0067:
            java.lang.String r12 = r1.zzRW     // Catch:{ all -> 0x0169 }
            boolean r16 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x0169 }
            if (r16 != 0) goto L_0x0079
            java.lang.String r3 = "Sending webview cookie in ad request header."
            com.google.android.gms.internal.zzpk.zzbf(r3)     // Catch:{ all -> 0x0169 }
            java.lang.String r3 = "Cookie"
            r11.addRequestProperty(r3, r12)     // Catch:{ all -> 0x0169 }
        L_0x0079:
            r3 = 1
            if (r23 == 0) goto L_0x00af
            java.lang.String r9 = r23.zzjz()     // Catch:{ all -> 0x0169 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x0169 }
            if (r9 != 0) goto L_0x00af
            r11.setDoOutput(r3)     // Catch:{ all -> 0x0169 }
            java.lang.String r9 = r23.zzjz()     // Catch:{ all -> 0x0169 }
            byte[] r9 = r9.getBytes()     // Catch:{ all -> 0x0169 }
            int r3 = r9.length     // Catch:{ all -> 0x0169 }
            r11.setFixedLengthStreamingMode(r3)     // Catch:{ all -> 0x0169 }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00a8 }
            java.io.OutputStream r1 = r11.getOutputStream()     // Catch:{ all -> 0x00a8 }
            r3.<init>(r1)     // Catch:{ all -> 0x00a8 }
            r3.write(r9)     // Catch:{ all -> 0x00a5 }
            com.google.android.gms.common.util.zzp.zzb(r3)     // Catch:{ all -> 0x0169 }
            goto L_0x00af
        L_0x00a5:
            r0 = move-exception
            r1 = r0
            goto L_0x00ab
        L_0x00a8:
            r0 = move-exception
            r1 = r0
            r3 = 0
        L_0x00ab:
            com.google.android.gms.common.util.zzp.zzb(r3)     // Catch:{ all -> 0x0169 }
            throw r1     // Catch:{ all -> 0x0169 }
        L_0x00af:
            int r1 = r11.getResponseCode()     // Catch:{ all -> 0x0169 }
            java.util.Map r3 = r11.getHeaderFields()     // Catch:{ all -> 0x0169 }
            r9 = 200(0xc8, float:2.8E-43)
            r12 = 300(0x12c, float:4.2E-43)
            if (r1 < r9) goto L_0x00fc
            if (r1 >= r12) goto L_0x00fc
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0169 }
            java.io.InputStreamReader r9 = new java.io.InputStreamReader     // Catch:{ all -> 0x00f5 }
            java.io.InputStream r10 = r11.getInputStream()     // Catch:{ all -> 0x00f5 }
            r9.<init>(r10)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.zzpo r10 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x00f2 }
            java.lang.String r10 = r10.zza((java.io.InputStreamReader) r9)     // Catch:{ all -> 0x00f2 }
            com.google.android.gms.common.util.zzp.zzb(r9)     // Catch:{ all -> 0x0169 }
            zza(r6, r3, r10, r1)     // Catch:{ all -> 0x0169 }
            r5.zzb(r6, r3, r10)     // Catch:{ all -> 0x0169 }
            if (r2 == 0) goto L_0x00ea
            r1 = 1
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ all -> 0x0169 }
            java.lang.String r3 = "ufe"
            r6 = 0
            r1[r6] = r3     // Catch:{ all -> 0x0169 }
            r2.zza(r4, r1)     // Catch:{ all -> 0x0169 }
        L_0x00ea:
            com.google.android.gms.internal.zzmn r1 = r5.zzj(r7)     // Catch:{ all -> 0x0169 }
            r11.disconnect()     // Catch:{ IOException -> 0x016f }
            return r1
        L_0x00f2:
            r0 = move-exception
            r1 = r0
            goto L_0x00f8
        L_0x00f5:
            r0 = move-exception
            r1 = r0
            r9 = 0
        L_0x00f8:
            com.google.android.gms.common.util.zzp.zzb(r9)     // Catch:{ all -> 0x0169 }
            throw r1     // Catch:{ all -> 0x0169 }
        L_0x00fc:
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0169 }
            r9 = 0
            zza(r6, r3, r9, r1)     // Catch:{ all -> 0x0169 }
            if (r1 < r12) goto L_0x0149
            r6 = 400(0x190, float:5.6E-43)
            if (r1 >= r6) goto L_0x0149
            java.lang.String r1 = "Location"
            java.lang.String r1 = r11.getHeaderField(r1)     // Catch:{ all -> 0x0169 }
            boolean r6 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0169 }
            if (r6 == 0) goto L_0x0125
            java.lang.String r1 = "No location header to follow redirect."
            com.google.android.gms.internal.zzpk.zzbh(r1)     // Catch:{ all -> 0x0169 }
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x0169 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0169 }
            r11.disconnect()     // Catch:{ IOException -> 0x016f }
            return r1
        L_0x0125:
            java.net.URL r6 = new java.net.URL     // Catch:{ all -> 0x0169 }
            r6.<init>(r1)     // Catch:{ all -> 0x0169 }
            r1 = 1
            int r10 = r10 + r1
            r1 = 5
            if (r10 <= r1) goto L_0x013e
            java.lang.String r1 = "Too many redirects."
            com.google.android.gms.internal.zzpk.zzbh(r1)     // Catch:{ all -> 0x0169 }
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x0169 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0169 }
            r11.disconnect()     // Catch:{ IOException -> 0x016f }
            return r1
        L_0x013e:
            r5.zzk(r3)     // Catch:{ all -> 0x0169 }
            r11.disconnect()     // Catch:{ IOException -> 0x016f }
            r1 = r18
            r9 = 0
            goto L_0x0040
        L_0x0149:
            r2 = 46
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0169 }
            r3.<init>(r2)     // Catch:{ all -> 0x0169 }
            java.lang.String r2 = "Received error HTTP response code: "
            r3.append(r2)     // Catch:{ all -> 0x0169 }
            r3.append(r1)     // Catch:{ all -> 0x0169 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0169 }
            com.google.android.gms.internal.zzpk.zzbh(r1)     // Catch:{ all -> 0x0169 }
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x0169 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0169 }
            r11.disconnect()     // Catch:{ IOException -> 0x016f }
            return r1
        L_0x0169:
            r0 = move-exception
            r1 = r0
            r11.disconnect()     // Catch:{ IOException -> 0x016f }
            throw r1     // Catch:{ IOException -> 0x016f }
        L_0x016f:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "Error while connecting to ad server: "
            java.lang.String r1 = r1.getMessage()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r3 = r1.length()
            if (r3 == 0) goto L_0x0186
            java.lang.String r1 = r2.concat(r1)
            goto L_0x018b
        L_0x0186:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
        L_0x018b:
            com.google.android.gms.internal.zzpk.zzbh(r1)
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn
            r2 = 2
            r1.<init>(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zznc.zza(com.google.android.gms.internal.zzmk, android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.zznh, com.google.android.gms.internal.zzgl, com.google.android.gms.internal.zznb):com.google.android.gms.internal.zzmn");
    }

    public static zznc zza(Context context, zzfw zzfw, zznb zznb) {
        zznc zznc;
        synchronized (zztX) {
            if (zzTv == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzTv = new zznc(context, zzfw, zznb);
            }
            zznc = zzTv;
        }
        return zznc;
    }

    @Nullable
    private static <T> T zza(Future<T> future, Long l) {
        try {
            return future.get(l.longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzpk.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (RuntimeException | ExecutionException | TimeoutException e2) {
            zzpk.zzc("Exception caught while resolving future", e2);
            return null;
        }
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzpk.zzak(2)) {
            StringBuilder sb = new StringBuilder(39 + String.valueOf(str).length());
            sb.append("Http Response: {\n  URL:\n    ");
            sb.append(str);
            sb.append("\n  Headers:");
            zzpk.v(sb.toString());
            if (map != null) {
                for (String next : map.keySet()) {
                    StringBuilder sb2 = new StringBuilder(5 + String.valueOf(next).length());
                    sb2.append("    ");
                    sb2.append(next);
                    sb2.append(":");
                    zzpk.v(sb2.toString());
                    for (String valueOf : map.get(next)) {
                        String valueOf2 = String.valueOf(valueOf);
                        zzpk.v(valueOf2.length() != 0 ? "      ".concat(valueOf2) : new String("      "));
                    }
                }
            }
            zzpk.v("  Body:");
            if (str2 != null) {
                int i2 = 0;
                while (i2 < Math.min(str2.length(), DefaultOggSeeker.MATCH_BYTE_RANGE)) {
                    int i3 = i2 + 1000;
                    zzpk.v(str2.substring(i2, Math.min(str2.length(), i3)));
                    i2 = i3;
                }
            } else {
                zzpk.v("    null");
            }
            StringBuilder sb3 = new StringBuilder(34);
            sb3.append("  Response Code:\n    ");
            sb3.append(i);
            sb3.append("\n}");
            zzpk.v(sb3.toString());
        }
    }

    public void zza(final zzmk zzmk, final zzmu zzmu) {
        zzw.zzcQ().zzc(this.mContext, zzmk.zzvn);
        zzpn.zza((Runnable) new Runnable() {
            public void run() {
                zzmn zzmn;
                try {
                    zzmn = zznc.this.zzd(zzmk);
                } catch (Exception e) {
                    zzw.zzcQ().zza((Throwable) e, "AdRequestServiceImpl.loadAdAsync");
                    zzpk.zzc("Could not fetch ad response due to an Exception.", e);
                    zzmn = null;
                }
                if (zzmn == null) {
                    zzmn = new zzmn(0);
                }
                try {
                    zzmu.zza(zzmn);
                } catch (RemoteException e2) {
                    zzpk.zzc("Fail to forward ad response.", e2);
                }
            }
        });
    }

    public zzmn zzd(zzmk zzmk) {
        return zza(this.mContext, this.zzTy, this.zzTx, this.zzTw, zzmk);
    }
}
