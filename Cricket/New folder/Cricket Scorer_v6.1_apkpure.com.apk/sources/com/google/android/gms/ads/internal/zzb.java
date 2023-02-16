package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzh;
import com.google.android.gms.ads.internal.purchase.GInAppPurchaseManagerInfoParcel;
import com.google.android.gms.ads.internal.purchase.zzc;
import com.google.android.gms.ads.internal.purchase.zzd;
import com.google.android.gms.ads.internal.purchase.zzf;
import com.google.android.gms.ads.internal.purchase.zzg;
import com.google.android.gms.ads.internal.purchase.zzj;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzld;
import com.google.android.gms.internal.zzle;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmk;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpd;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqw;
import java.util.ArrayList;

@zzme
public abstract class zzb extends zza implements zzh, zzj, zzu, zzif, zzjs {
    protected transient boolean zzsA;
    protected final zzka zzsz;

    public zzb(Context context, zzeg zzeg, String str, zzka zzka, zzqh zzqh, zze zze) {
        this(new zzx(context, zzeg, str, zzqh), zzka, (zzt) null, zze);
    }

    protected zzb(zzx zzx, zzka zzka, @Nullable zzt zzt, zze zze) {
        super(zzx, zzt, zze);
        this.zzsz = zzka;
        this.zzsA = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0107 A[LOOP:0: B:33:0x00fd->B:35:0x0107, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0150  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.gms.internal.zzmk.zza zza(com.google.android.gms.internal.zzec r60, android.os.Bundle r61, com.google.android.gms.internal.zzpd r62) {
        /*
            r59 = this;
            r6 = r59
            com.google.android.gms.ads.internal.zzx r0 = r6.zzss
            android.content.Context r0 = r0.zzqn
            android.content.pm.ApplicationInfo r12 = r0.getApplicationInfo()
            r0 = 0
            r1 = 0
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss     // Catch:{ NameNotFoundException -> 0x001c }
            android.content.Context r2 = r2.zzqn     // Catch:{ NameNotFoundException -> 0x001c }
            com.google.android.gms.internal.zzadf r2 = com.google.android.gms.internal.zzadg.zzbi(r2)     // Catch:{ NameNotFoundException -> 0x001c }
            java.lang.String r3 = r12.packageName     // Catch:{ NameNotFoundException -> 0x001c }
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r1)     // Catch:{ NameNotFoundException -> 0x001c }
            r13 = r2
            goto L_0x001d
        L_0x001c:
            r13 = r0
        L_0x001d:
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss
            android.content.Context r2 = r2.zzqn
            android.content.res.Resources r2 = r2.getResources()
            android.util.DisplayMetrics r7 = r2.getDisplayMetrics()
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss
            com.google.android.gms.ads.internal.zzx$zza r2 = r2.zzvo
            r3 = 1
            if (r2 == 0) goto L_0x0096
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss
            com.google.android.gms.ads.internal.zzx$zza r2 = r2.zzvo
            android.view.ViewParent r2 = r2.getParent()
            if (r2 == 0) goto L_0x0096
            r2 = 2
            int[] r2 = new int[r2]
            com.google.android.gms.ads.internal.zzx r4 = r6.zzss
            com.google.android.gms.ads.internal.zzx$zza r4 = r4.zzvo
            r4.getLocationOnScreen(r2)
            r4 = r2[r1]
            r2 = r2[r3]
            com.google.android.gms.ads.internal.zzx r5 = r6.zzss
            com.google.android.gms.ads.internal.zzx$zza r5 = r5.zzvo
            int r5 = r5.getWidth()
            com.google.android.gms.ads.internal.zzx r8 = r6.zzss
            com.google.android.gms.ads.internal.zzx$zza r8 = r8.zzvo
            int r8 = r8.getHeight()
            com.google.android.gms.ads.internal.zzx r9 = r6.zzss
            com.google.android.gms.ads.internal.zzx$zza r9 = r9.zzvo
            boolean r9 = r9.isShown()
            if (r9 == 0) goto L_0x0074
            int r9 = r4 + r5
            if (r9 <= 0) goto L_0x0074
            int r9 = r2 + r8
            if (r9 <= 0) goto L_0x0074
            int r9 = r7.widthPixels
            if (r4 > r9) goto L_0x0074
            int r9 = r7.heightPixels
            if (r2 > r9) goto L_0x0074
            r9 = r3
            goto L_0x0075
        L_0x0074:
            r9 = r1
        L_0x0075:
            android.os.Bundle r10 = new android.os.Bundle
            r11 = 5
            r10.<init>(r11)
            java.lang.String r11 = "x"
            r10.putInt(r11, r4)
            java.lang.String r4 = "y"
            r10.putInt(r4, r2)
            java.lang.String r2 = "width"
            r10.putInt(r2, r5)
            java.lang.String r2 = "height"
            r10.putInt(r2, r8)
            java.lang.String r2 = "visible"
            r10.putInt(r2, r9)
            r8 = r10
            goto L_0x0097
        L_0x0096:
            r8 = r0
        L_0x0097:
            com.google.android.gms.internal.zzpe r2 = com.google.android.gms.ads.internal.zzw.zzcQ()
            java.lang.String r14 = r2.zzki()
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss
            com.google.android.gms.internal.zzpc r4 = new com.google.android.gms.internal.zzpc
            com.google.android.gms.ads.internal.zzx r5 = r6.zzss
            java.lang.String r5 = r5.zzvl
            r4.<init>(r14, r5)
            r2.zzvu = r4
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss
            com.google.android.gms.internal.zzpc r2 = r2.zzvu
            r9 = r60
            r2.zzs(r9)
            com.google.android.gms.internal.zzpo r2 = com.google.android.gms.ads.internal.zzw.zzcM()
            com.google.android.gms.ads.internal.zzx r4 = r6.zzss
            android.content.Context r4 = r4.zzqn
            com.google.android.gms.ads.internal.zzx r5 = r6.zzss
            com.google.android.gms.ads.internal.zzx$zza r5 = r5.zzvo
            com.google.android.gms.ads.internal.zzx r10 = r6.zzss
            com.google.android.gms.internal.zzeg r10 = r10.zzvr
            java.lang.String r25 = r2.zza((android.content.Context) r4, (android.view.View) r5, (com.google.android.gms.internal.zzeg) r10)
            r4 = 0
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss
            com.google.android.gms.internal.zzex r2 = r2.zzvy
            if (r2 == 0) goto L_0x00e1
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss     // Catch:{ RemoteException -> 0x00dc }
            com.google.android.gms.internal.zzex r2 = r2.zzvy     // Catch:{ RemoteException -> 0x00dc }
            long r10 = r2.getValue()     // Catch:{ RemoteException -> 0x00dc }
            r26 = r10
            goto L_0x00e3
        L_0x00dc:
            java.lang.String r2 = "Cannot get correlation id, default to 0."
            com.google.android.gms.internal.zzpk.zzbh(r2)
        L_0x00e1:
            r26 = r4
        L_0x00e3:
            java.util.UUID r2 = java.util.UUID.randomUUID()
            java.lang.String r28 = r2.toString()
            com.google.android.gms.internal.zzpe r2 = com.google.android.gms.ads.internal.zzw.zzcQ()
            com.google.android.gms.ads.internal.zzx r4 = r6.zzss
            android.content.Context r4 = r4.zzqn
            android.os.Bundle r17 = r2.zza(r4, r6, r14)
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            r2 = r1
        L_0x00fd:
            com.google.android.gms.ads.internal.zzx r4 = r6.zzss
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.zzhs> r4 = r4.zzvE
            int r4 = r4.size()
            if (r2 >= r4) goto L_0x0117
            com.google.android.gms.ads.internal.zzx r4 = r6.zzss
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.zzhs> r4 = r4.zzvE
            java.lang.Object r4 = r4.keyAt(r2)
            java.lang.String r4 = (java.lang.String) r4
            r15.add(r4)
            int r2 = r2 + 1
            goto L_0x00fd
        L_0x0117:
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss
            com.google.android.gms.internal.zzle r2 = r2.zzvz
            if (r2 == 0) goto L_0x011f
            r4 = r3
            goto L_0x0120
        L_0x011f:
            r4 = r1
        L_0x0120:
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss
            com.google.android.gms.internal.zzli r2 = r2.zzvA
            if (r2 == 0) goto L_0x0132
            com.google.android.gms.internal.zzpe r2 = com.google.android.gms.ads.internal.zzw.zzcQ()
            boolean r2 = r2.zzkz()
            if (r2 == 0) goto L_0x0132
            r5 = r3
            goto L_0x0133
        L_0x0132:
            r5 = r1
        L_0x0133:
            com.google.android.gms.ads.internal.zzb$1 r1 = new com.google.android.gms.ads.internal.zzb$1
            r1.<init>()
            com.google.android.gms.internal.zzqm r10 = com.google.android.gms.internal.zzpn.zza(r1)
            com.google.android.gms.ads.internal.zzb$2 r1 = new com.google.android.gms.ads.internal.zzb$2
            r1.<init>()
            com.google.android.gms.internal.zzqm r40 = com.google.android.gms.internal.zzpn.zza(r1)
            com.google.android.gms.ads.internal.zzb$3 r1 = new com.google.android.gms.ads.internal.zzb$3
            r1.<init>()
            com.google.android.gms.internal.zzqm r50 = com.google.android.gms.internal.zzpn.zza(r1)
            if (r62 == 0) goto L_0x0154
            java.lang.String r0 = r62.zzkd()
        L_0x0154:
            r41 = r0
            com.google.android.gms.internal.zzqj r11 = new com.google.android.gms.internal.zzqj
            r11.<init>()
            com.google.android.gms.ads.internal.zzb$4 r3 = new com.google.android.gms.ads.internal.zzb$4
            r0 = r3
            r1 = r6
            r2 = r10
            r51 = r15
            r15 = r3
            r3 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            r10.zzc(r15)
            com.google.android.gms.internal.zzmk$zza r0 = new com.google.android.gms.internal.zzmk$zza
            com.google.android.gms.ads.internal.zzx r1 = r6.zzss
            com.google.android.gms.internal.zzeg r10 = r1.zzvr
            com.google.android.gms.ads.internal.zzx r1 = r6.zzss
            java.lang.String r1 = r1.zzvl
            com.google.android.gms.internal.zzpe r2 = com.google.android.gms.ads.internal.zzw.zzcQ()
            java.lang.String r15 = r2.getSessionId()
            com.google.android.gms.ads.internal.zzx r2 = r6.zzss
            com.google.android.gms.internal.zzqh r2 = r2.zzvn
            com.google.android.gms.ads.internal.zzx r3 = r6.zzss
            java.util.List<java.lang.String> r3 = r3.zzvK
            com.google.android.gms.internal.zzpe r4 = com.google.android.gms.ads.internal.zzw.zzcQ()
            boolean r21 = r4.zzkm()
            int r4 = r7.widthPixels
            int r5 = r7.heightPixels
            float r7 = r7.density
            java.util.List r29 = com.google.android.gms.internal.zzgd.zzfs()
            r52 = r7
            com.google.android.gms.ads.internal.zzx r7 = r6.zzss
            java.lang.String r7 = r7.zzvk
            r53 = r7
            com.google.android.gms.ads.internal.zzx r7 = r6.zzss
            com.google.android.gms.internal.zzhc r7 = r7.zzvF
            r54 = r7
            com.google.android.gms.ads.internal.zzx r7 = r6.zzss
            java.lang.String r33 = r7.zzdu()
            com.google.android.gms.internal.zzpo r7 = com.google.android.gms.ads.internal.zzw.zzcM()
            float r34 = r7.zzcq()
            com.google.android.gms.internal.zzpo r7 = com.google.android.gms.ads.internal.zzw.zzcM()
            boolean r35 = r7.zzcs()
            com.google.android.gms.internal.zzpo r7 = com.google.android.gms.ads.internal.zzw.zzcM()
            r55 = r11
            com.google.android.gms.ads.internal.zzx r11 = r6.zzss
            android.content.Context r11 = r11.zzqn
            int r36 = r7.zzT(r11)
            com.google.android.gms.internal.zzpo r7 = com.google.android.gms.ads.internal.zzw.zzcM()
            com.google.android.gms.ads.internal.zzx r11 = r6.zzss
            com.google.android.gms.ads.internal.zzx$zza r11 = r11.zzvo
            int r37 = r7.zzs(r11)
            com.google.android.gms.ads.internal.zzx r7 = r6.zzss
            android.content.Context r7 = r7.zzqn
            boolean r11 = r7 instanceof android.app.Activity
            com.google.android.gms.internal.zzpe r7 = com.google.android.gms.ads.internal.zzw.zzcQ()
            boolean r39 = r7.zzkr()
            com.google.android.gms.internal.zzpe r7 = com.google.android.gms.ads.internal.zzw.zzcQ()
            boolean r42 = r7.zzkv()
            com.google.android.gms.internal.zzir r7 = com.google.android.gms.ads.internal.zzw.zzdj()
            int r43 = r7.zzgr()
            com.google.android.gms.internal.zzpo r7 = com.google.android.gms.ads.internal.zzw.zzcM()
            android.os.Bundle r44 = r7.zzkP()
            com.google.android.gms.internal.zzps r7 = com.google.android.gms.ads.internal.zzw.zzcU()
            java.lang.String r45 = r7.zzkY()
            com.google.android.gms.ads.internal.zzx r7 = r6.zzss
            com.google.android.gms.internal.zzfc r7 = r7.zzvH
            r56 = r7
            com.google.android.gms.internal.zzps r7 = com.google.android.gms.ads.internal.zzw.zzcU()
            boolean r47 = r7.zzkZ()
            com.google.android.gms.internal.zzjc r7 = com.google.android.gms.internal.zzjc.zzgC()
            android.os.Bundle r48 = r7.asBundle()
            com.google.android.gms.internal.zzpe r7 = com.google.android.gms.ads.internal.zzw.zzcQ()
            r57 = r11
            com.google.android.gms.ads.internal.zzx r11 = r6.zzss
            android.content.Context r11 = r11.zzqn
            r58 = r5
            com.google.android.gms.ads.internal.zzx r5 = r6.zzss
            java.lang.String r5 = r5.zzvl
            boolean r49 = r7.zzm(r11, r5)
            r5 = r52
            r30 = r53
            r31 = r54
            r46 = r56
            r7 = r0
            r32 = r55
            r38 = r57
            r11 = r1
            r1 = r51
            r16 = r2
            r18 = r3
            r19 = r1
            r20 = r61
            r22 = r4
            r23 = r58
            r24 = r5
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzb.zza(com.google.android.gms.internal.zzec, android.os.Bundle, com.google.android.gms.internal.zzpd):com.google.android.gms.internal.zzmk$zza");
    }

    public String getMediationAdapterClassName() {
        if (this.zzss.zzvs == null) {
            return null;
        }
        return this.zzss.zzvs.zzLk;
    }

    public void onAdClicked() {
        if (this.zzss.zzvs == null) {
            zzpk.zzbh("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzss.zzvs.zzWc == null || this.zzss.zzvs.zzWc.zzKF == null)) {
            String zzF = zzw.zzdl().zzF(this.zzss.zzqn);
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, this.zzss.zzvs, this.zzss.zzvl, false, zza(zzF, this.zzss.zzvs.zzWc.zzKF));
            if (this.zzss.zzvs.zzWc.zzKF.size() > 0) {
                zzw.zzdl().zzf(this.zzss.zzqn, zzF);
            }
        }
        if (!(this.zzss.zzvs.zzLi == null || this.zzss.zzvs.zzLi.zzKs == null)) {
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, this.zzss.zzvs, this.zzss.zzvl, false, this.zzss.zzvs.zzLi.zzKs);
        }
        super.onAdClicked();
    }

    public void onPause() {
        this.zzsu.zzl(this.zzss.zzvs);
    }

    public void onResume() {
        this.zzsu.zzm(this.zzss.zzvs);
    }

    public void pause() {
        zzac.zzdj("pause must be called on the main UI thread.");
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzNH == null || !this.zzss.zzdq())) {
            zzw.zzcO().zzl(this.zzss.zzvs.zzNH);
        }
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzLj == null)) {
            try {
                this.zzss.zzvs.zzLj.pause();
            } catch (RemoteException unused) {
                zzpk.zzbh("Could not pause mediation adapter.");
            }
        }
        this.zzsu.zzl(this.zzss.zzvs);
        this.zzsr.pause();
    }

    public void recordImpression() {
        zza(this.zzss.zzvs, false);
    }

    public void resume() {
        zzac.zzdj("resume must be called on the main UI thread.");
        zzqw zzqw = (this.zzss.zzvs == null || this.zzss.zzvs.zzNH == null) ? null : this.zzss.zzvs.zzNH;
        if (zzqw != null && this.zzss.zzdq()) {
            zzw.zzcO().zzm(this.zzss.zzvs.zzNH);
        }
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzLj == null)) {
            try {
                this.zzss.zzvs.zzLj.resume();
            } catch (RemoteException unused) {
                zzpk.zzbh("Could not resume mediation adapter.");
            }
        }
        if (zzqw == null || !zzqw.zzlC()) {
            this.zzsr.resume();
        }
        this.zzsu.zzm(this.zzss.zzvs);
    }

    public void showInterstitial() {
        throw new IllegalStateException("showInterstitial is not supported for current ad type");
    }

    public void zza(zzle zzle) {
        zzac.zzdj("setInAppPurchaseListener must be called on the main UI thread.");
        this.zzss.zzvz = zzle;
    }

    public void zza(zzli zzli, @Nullable String str) {
        zzac.zzdj("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.zzss.zzvL = new zzk(str);
        this.zzss.zzvA = zzli;
        if (!zzw.zzcQ().zzkl() && zzli != null) {
            new zzc(this.zzss.zzqn, this.zzss.zzvA, this.zzss.zzvL).zziP();
        }
    }

    /* access modifiers changed from: protected */
    public void zza(@Nullable zzpb zzpb, boolean z) {
        if (zzpb == null) {
            zzpk.zzbh("Ad state was null when trying to ping impression URLs.");
            return;
        }
        super.zzc(zzpb);
        if (!(zzpb.zzWc == null || zzpb.zzWc.zzKG == null)) {
            String zzF = zzw.zzdl().zzF(this.zzss.zzqn);
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, zzpb, this.zzss.zzvl, z, zza(zzF, zzpb.zzWc.zzKG));
            if (zzpb.zzWc.zzKG.size() > 0) {
                zzw.zzdl().zzg(this.zzss.zzqn, zzF);
            }
        }
        if (zzpb.zzLi != null && zzpb.zzLi.zzKt != null) {
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, zzpb, this.zzss.zzvl, z, zzpb.zzLi.zzKt);
        }
    }

    public void zza(String str, ArrayList<String> arrayList) {
        zzd zzd = new zzd(str, arrayList, this.zzss.zzqn, this.zzss.zzvn.zzba);
        if (this.zzss.zzvz == null) {
            zzpk.zzbh("InAppPurchaseListener is not set. Try to launch default purchase flow.");
            if (!zzel.zzeT().zzaf(this.zzss.zzqn)) {
                zzpk.zzbh("Google Play Service unavailable, cannot launch default purchase flow.");
            } else if (this.zzss.zzvA == null) {
                zzpk.zzbh("PlayStorePurchaseListener is not set.");
            } else if (this.zzss.zzvL == null) {
                zzpk.zzbh("PlayStorePurchaseVerifier is not initialized.");
            } else if (this.zzss.zzvP) {
                zzpk.zzbh("An in-app purchase request is already in progress, abort");
            } else {
                this.zzss.zzvP = true;
                try {
                    if (!this.zzss.zzvA.isValidPurchase(str)) {
                        this.zzss.zzvP = false;
                    } else {
                        zzw.zzda().zza(this.zzss.zzqn, this.zzss.zzvn.zzYY, new GInAppPurchaseManagerInfoParcel(this.zzss.zzqn, this.zzss.zzvL, (zzld) zzd, (zzj) this));
                    }
                } catch (RemoteException unused) {
                    zzpk.zzbh("Could not start In-App purchase.");
                    this.zzss.zzvP = false;
                }
            }
        } else {
            try {
                this.zzss.zzvz.zza(zzd);
            } catch (RemoteException unused2) {
                zzpk.zzbh("Could not start In-App purchase.");
            }
        }
    }

    public void zza(String str, boolean z, int i, final Intent intent, zzf zzf) {
        try {
            if (this.zzss.zzvA != null) {
                this.zzss.zzvA.zza(new zzg(this.zzss.zzqn, str, z, i, intent, zzf));
            }
        } catch (RemoteException unused) {
            zzpk.zzbh("Fail to invoke PlayStorePurchaseListener.");
        }
        zzpo.zzXC.postDelayed(new Runnable() {
            public void run() {
                int zzd = zzw.zzda().zzd(intent);
                zzw.zzda();
                if (!(zzd != 0 || zzb.this.zzss.zzvs == null || zzb.this.zzss.zzvs.zzNH == null || zzb.this.zzss.zzvs.zzNH.zzlt() == null)) {
                    zzb.this.zzss.zzvs.zzNH.zzlt().close();
                }
                zzb.this.zzss.zzvP = false;
            }
        }, 500);
    }

    public boolean zza(zzec zzec, zzgl zzgl) {
        if (!zzbM()) {
            return false;
        }
        Bundle zzV = zzw.zzcM().zzV(this.zzss.zzqn);
        this.zzsr.cancel();
        this.zzss.zzvO = 0;
        zzpd zzpd = null;
        if (zzgd.zzEJ.get().booleanValue()) {
            zzpd = zzw.zzcQ().zzkw();
            zzw.zzdi().zza(this.zzss.zzqn, this.zzss.zzvn, this.zzss.zzvl, zzpd);
        }
        zzmk.zza zza = zza(zzec, zzV, zzpd);
        zzgl.zzh("seq_num", zza.zzRB);
        zzgl.zzh("request_id", zza.zzRL);
        zzgl.zzh("session_id", zza.zzRC);
        if (zza.zzRz != null) {
            zzgl.zzh("app_version", String.valueOf(zza.zzRz.versionCode));
        }
        this.zzss.zzvp = zzw.zzcI().zza(this.zzss.zzqn, zza, this);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzec zzec, zzpb zzpb, boolean z) {
        zzt zzt;
        long j;
        if (!z && this.zzss.zzdq()) {
            if (zzpb.zzKL > 0) {
                zzt = this.zzsr;
                j = zzpb.zzKL;
            } else if (zzpb.zzWc != null && zzpb.zzWc.zzKL > 0) {
                zzt = this.zzsr;
                j = zzpb.zzWc.zzKL;
            } else if (!zzpb.zzSn && zzpb.errorCode == 2) {
                this.zzsr.zzh(zzec);
            }
            zzt.zza(zzec, j);
        }
        return this.zzsr.zzcy();
    }

    /* access modifiers changed from: package-private */
    public boolean zza(zzpb zzpb) {
        zzec zzec;
        boolean z = false;
        if (this.zzst != null) {
            zzec = this.zzst;
            this.zzst = null;
        } else {
            zzec = zzpb.zzRy;
            if (zzec.extras != null) {
                z = zzec.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(zzec, zzpb, z);
    }

    /* access modifiers changed from: protected */
    public boolean zza(@Nullable zzpb zzpb, zzpb zzpb2) {
        int i;
        if (!(zzpb == null || zzpb.zzLl == null)) {
            zzpb.zzLl.zza((zzjs) null);
        }
        if (zzpb2.zzLl != null) {
            zzpb2.zzLl.zza((zzjs) this);
        }
        int i2 = 0;
        if (zzpb2.zzWc != null) {
            i2 = zzpb2.zzWc.zzKS;
            i = zzpb2.zzWc.zzKT;
        } else {
            i = 0;
        }
        this.zzss.zzvM.zzk(i2, i);
        return true;
    }

    public void zzb(zzpb zzpb) {
        super.zzb(zzpb);
        if (zzpb.zzLi != null) {
            zzpk.zzbf("Disable the debug gesture detector on the mediation ad frame.");
            if (this.zzss.zzvo != null) {
                this.zzss.zzvo.zzdy();
            }
            zzpk.zzbf("Pinging network fill URLs.");
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, zzpb, this.zzss.zzvl, false, zzpb.zzLi.zzKu);
            if (!(zzpb.zzWc == null || zzpb.zzWc.zzKI == null || zzpb.zzWc.zzKI.size() <= 0)) {
                zzpk.zzbf("Pinging urls remotely");
                zzw.zzcM().zza(this.zzss.zzqn, zzpb.zzWc.zzKI);
            }
        } else {
            zzpk.zzbf("Enable the debug gesture detector on the admob ad frame.");
            if (this.zzss.zzvo != null) {
                this.zzss.zzvo.zzdx();
            }
        }
        if (zzpb.errorCode == 3 && zzpb.zzWc != null && zzpb.zzWc.zzKH != null) {
            zzpk.zzbf("Pinging no fill URLs.");
            zzw.zzdf().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, zzpb, this.zzss.zzvl, false, zzpb.zzWc.zzKH);
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzbM() {
        return zzw.zzcM().zze(this.zzss.zzqn, this.zzss.zzqn.getPackageName(), "android.permission.INTERNET") && zzw.zzcM().zzJ(this.zzss.zzqn);
    }

    public void zzbN() {
        this.zzsu.zzj(this.zzss.zzvs);
        this.zzsA = false;
        zzbG();
        this.zzss.zzvu.zzjX();
    }

    public void zzbO() {
        this.zzsA = true;
        zzbI();
    }

    public void zzbP() {
        onAdClicked();
    }

    public void zzbQ() {
        zzbN();
    }

    public void zzbR() {
        zzbD();
    }

    public void zzbS() {
        zzbO();
    }

    public void zzbT() {
        if (this.zzss.zzvs != null) {
            String str = this.zzss.zzvs.zzLk;
            StringBuilder sb = new StringBuilder(74 + String.valueOf(str).length());
            sb.append("Mediation adapter ");
            sb.append(str);
            sb.append(" refreshed, but mediation adapters should never refresh.");
            zzpk.zzbh(sb.toString());
        }
        zza(this.zzss.zzvs, true);
        zzbJ();
    }

    public void zzbU() {
        recordImpression();
    }

    public void zzbV() {
        zzw.zzcM().runOnUiThread(new Runnable() {
            public void run() {
                zzb.this.zzsr.pause();
            }
        });
    }

    public void zzbW() {
        zzw.zzcM().runOnUiThread(new Runnable() {
            public void run() {
                zzb.this.zzsr.resume();
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean zzc(zzec zzec) {
        return super.zzc(zzec) && !this.zzsA;
    }
}
