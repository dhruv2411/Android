package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zze;

public class zzatu extends zzauh {
    private String mAppId;
    private String zzVX;
    private String zzacL;
    private String zzacM;
    private long zzbqA;
    private String zzbqw;
    private int zzbsv;
    private long zzbsw;

    zzatu(zzaue zzaue) {
        super(zzaue);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    public String getGmpAppId() {
        zzob();
        return this.zzVX;
    }

    public /* bridge */ /* synthetic */ void zzJV() {
        super.zzJV();
    }

    public /* bridge */ /* synthetic */ void zzJW() {
        super.zzJW();
    }

    public /* bridge */ /* synthetic */ void zzJX() {
        super.zzJX();
    }

    public /* bridge */ /* synthetic */ zzatb zzJY() {
        return super.zzJY();
    }

    public /* bridge */ /* synthetic */ zzatf zzJZ() {
        return super.zzJZ();
    }

    public /* bridge */ /* synthetic */ zzauj zzKa() {
        return super.zzKa();
    }

    public /* bridge */ /* synthetic */ zzatu zzKb() {
        return super.zzKb();
    }

    public /* bridge */ /* synthetic */ zzatl zzKc() {
        return super.zzKc();
    }

    public /* bridge */ /* synthetic */ zzaul zzKd() {
        return super.zzKd();
    }

    public /* bridge */ /* synthetic */ zzauk zzKe() {
        return super.zzKe();
    }

    public /* bridge */ /* synthetic */ zzatv zzKf() {
        return super.zzKf();
    }

    public /* bridge */ /* synthetic */ zzatj zzKg() {
        return super.zzKg();
    }

    public /* bridge */ /* synthetic */ zzaut zzKh() {
        return super.zzKh();
    }

    public /* bridge */ /* synthetic */ zzauc zzKi() {
        return super.zzKi();
    }

    public /* bridge */ /* synthetic */ zzaun zzKj() {
        return super.zzKj();
    }

    public /* bridge */ /* synthetic */ zzaud zzKk() {
        return super.zzKk();
    }

    public /* bridge */ /* synthetic */ zzatx zzKl() {
        return super.zzKl();
    }

    public /* bridge */ /* synthetic */ zzaua zzKm() {
        return super.zzKm();
    }

    public /* bridge */ /* synthetic */ zzati zzKn() {
        return super.zzKn();
    }

    /* access modifiers changed from: package-private */
    public String zzKu() {
        zzob();
        return this.zzbqw;
    }

    /* access modifiers changed from: package-private */
    public long zzKv() {
        return zzKn().zzKv();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public long zzKw() {
        zzob();
        zzmR();
        if (this.zzbsw == 0) {
            this.zzbsw = this.zzbqc.zzKh().zzL(getContext(), getContext().getPackageName());
        }
        return this.zzbsw;
    }

    /* access modifiers changed from: package-private */
    public int zzLX() {
        zzob();
        return this.zzbsv;
    }

    /* access modifiers changed from: protected */
    public void zzbw(Status status) {
        if (status == null) {
            zzKl().zzLY().log("GoogleService failed to initialize (no status)");
        } else {
            zzKl().zzLY().zze("GoogleService failed to initialize, status", Integer.valueOf(status.getStatusCode()), status.getStatusMessage());
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public zzatd zzfD(String str) {
        zzmR();
        return new zzatd(zzke(), getGmpAppId(), zzmZ(), (long) zzLX(), zzKu(), zzKv(), zzKw(), str, this.zzbqc.isEnabled(), !zzKm().zzbtq, zzKm().zzKq(), zzuW());
    }

    /* access modifiers changed from: package-private */
    public String zzke() {
        zzob();
        return this.mAppId;
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x012b A[Catch:{ IllegalStateException -> 0x0143 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0131 A[Catch:{ IllegalStateException -> 0x0143 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzmS() {
        /*
            r10 = this;
            java.lang.String r0 = "unknown"
            java.lang.String r1 = "Unknown"
            java.lang.String r2 = "Unknown"
            android.content.Context r3 = r10.getContext()
            java.lang.String r3 = r3.getPackageName()
            android.content.Context r4 = r10.getContext()
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            r5 = 0
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r4 != 0) goto L_0x002d
            com.google.android.gms.internal.zzatx r4 = r10.zzKl()
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()
            java.lang.String r7 = "PackageManager is null, app identity information might be inaccurate. appId"
            java.lang.Object r8 = com.google.android.gms.internal.zzatx.zzfE(r3)
            r4.zzj(r7, r8)
            goto L_0x008b
        L_0x002d:
            java.lang.String r7 = r4.getInstallerPackageName(r3)     // Catch:{ IllegalArgumentException -> 0x0033 }
            r0 = r7
            goto L_0x0044
        L_0x0033:
            com.google.android.gms.internal.zzatx r7 = r10.zzKl()
            com.google.android.gms.internal.zzatx$zza r7 = r7.zzLY()
            java.lang.String r8 = "Error retrieving app installer package name. appId"
            java.lang.Object r9 = com.google.android.gms.internal.zzatx.zzfE(r3)
            r7.zzj(r8, r9)
        L_0x0044:
            if (r0 != 0) goto L_0x0049
            java.lang.String r0 = "manual_install"
            goto L_0x0053
        L_0x0049:
            java.lang.String r7 = "com.android.vending"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0053
            java.lang.String r0 = ""
        L_0x0053:
            android.content.Context r7 = r10.getContext()     // Catch:{ NameNotFoundException -> 0x007a }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x007a }
            android.content.pm.PackageInfo r7 = r4.getPackageInfo(r7, r5)     // Catch:{ NameNotFoundException -> 0x007a }
            if (r7 == 0) goto L_0x008b
            android.content.pm.ApplicationInfo r8 = r7.applicationInfo     // Catch:{ NameNotFoundException -> 0x007a }
            java.lang.CharSequence r4 = r4.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x007a }
            boolean r8 = android.text.TextUtils.isEmpty(r4)     // Catch:{ NameNotFoundException -> 0x007a }
            if (r8 != 0) goto L_0x0072
            java.lang.String r4 = r4.toString()     // Catch:{ NameNotFoundException -> 0x007a }
            r2 = r4
        L_0x0072:
            java.lang.String r4 = r7.versionName     // Catch:{ NameNotFoundException -> 0x007a }
            int r1 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x0079 }
            r6 = r1
            r1 = r4
            goto L_0x008b
        L_0x0079:
            r1 = r4
        L_0x007a:
            com.google.android.gms.internal.zzatx r4 = r10.zzKl()
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()
            java.lang.String r7 = "Error retrieving package info. appId, appName"
            java.lang.Object r8 = com.google.android.gms.internal.zzatx.zzfE(r3)
            r4.zze(r7, r8, r2)
        L_0x008b:
            r10.mAppId = r3
            r10.zzbqw = r0
            r10.zzacM = r1
            r10.zzbsv = r6
            r10.zzacL = r2
            r0 = 0
            r10.zzbsw = r0
            com.google.android.gms.internal.zzati r2 = r10.zzKn()
            r2.zzLg()
            android.content.Context r2 = r10.getContext()
            com.google.android.gms.common.api.Status r2 = com.google.android.gms.internal.zzaba.zzaQ(r2)
            r4 = 1
            if (r2 == 0) goto L_0x00b3
            boolean r6 = r2.isSuccess()
            if (r6 == 0) goto L_0x00b3
            r6 = r4
            goto L_0x00b4
        L_0x00b3:
            r6 = r5
        L_0x00b4:
            if (r6 != 0) goto L_0x00b9
            r10.zzbw(r2)
        L_0x00b9:
            if (r6 == 0) goto L_0x0113
            com.google.android.gms.internal.zzati r2 = r10.zzKn()
            java.lang.Boolean r2 = r2.zzLi()
            com.google.android.gms.internal.zzati r6 = r10.zzKn()
            boolean r6 = r6.zzLh()
            if (r6 == 0) goto L_0x00db
            com.google.android.gms.internal.zzatx r2 = r10.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMc()
            java.lang.String r4 = "Collection disabled with firebase_analytics_collection_deactivated=1"
        L_0x00d7:
            r2.log(r4)
            goto L_0x0113
        L_0x00db:
            if (r2 == 0) goto L_0x00ee
            boolean r6 = r2.booleanValue()
            if (r6 != 0) goto L_0x00ee
            com.google.android.gms.internal.zzatx r2 = r10.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMc()
            java.lang.String r4 = "Collection disabled with firebase_analytics_collection_enabled=0"
            goto L_0x00d7
        L_0x00ee:
            if (r2 != 0) goto L_0x0105
            com.google.android.gms.internal.zzati r2 = r10.zzKn()
            boolean r2 = r2.zzwR()
            if (r2 == 0) goto L_0x0105
            com.google.android.gms.internal.zzatx r2 = r10.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMc()
            java.lang.String r4 = "Collection disabled with google_app_measurement_enable=0"
            goto L_0x00d7
        L_0x0105:
            com.google.android.gms.internal.zzatx r2 = r10.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMe()
            java.lang.String r5 = "Collection enabled"
            r2.log(r5)
            goto L_0x0114
        L_0x0113:
            r4 = r5
        L_0x0114:
            java.lang.String r2 = ""
            r10.zzVX = r2
            r10.zzbqA = r0
            com.google.android.gms.internal.zzati r0 = r10.zzKn()
            r0.zzLg()
            java.lang.String r0 = com.google.android.gms.internal.zzaba.zzwQ()     // Catch:{ IllegalStateException -> 0x0143 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IllegalStateException -> 0x0143 }
            if (r1 == 0) goto L_0x012d
            java.lang.String r0 = ""
        L_0x012d:
            r10.zzVX = r0     // Catch:{ IllegalStateException -> 0x0143 }
            if (r4 == 0) goto L_0x0155
            com.google.android.gms.internal.zzatx r0 = r10.zzKl()     // Catch:{ IllegalStateException -> 0x0143 }
            com.google.android.gms.internal.zzatx$zza r0 = r0.zzMe()     // Catch:{ IllegalStateException -> 0x0143 }
            java.lang.String r1 = "App package, google app id"
            java.lang.String r2 = r10.mAppId     // Catch:{ IllegalStateException -> 0x0143 }
            java.lang.String r4 = r10.zzVX     // Catch:{ IllegalStateException -> 0x0143 }
            r0.zze(r1, r2, r4)     // Catch:{ IllegalStateException -> 0x0143 }
            return
        L_0x0143:
            r0 = move-exception
            com.google.android.gms.internal.zzatx r1 = r10.zzKl()
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()
            java.lang.String r2 = "getGoogleAppId or isMeasurementEnabled failed with exception. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r3)
            r1.zze(r2, r3, r0)
        L_0x0155:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatu.zzmS():void");
    }

    /* access modifiers changed from: package-private */
    public String zzmZ() {
        zzob();
        return this.zzacM;
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }

    /* access modifiers changed from: package-private */
    public long zzuW() {
        zzob();
        return 0;
    }
}
