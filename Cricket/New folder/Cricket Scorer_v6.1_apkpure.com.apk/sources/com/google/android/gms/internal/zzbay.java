package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.common.util.zzz;

public class zzbay {
    private static boolean DEBUG = false;
    private static String TAG = "WakeLock";
    private static String zzbEv = "*gcore*:";
    private final Context mContext;
    private final String zzaHF;
    private final String zzaHH;
    private int zzbEA;
    private int zzbEB;
    private final PowerManager.WakeLock zzbEw;
    private final int zzbEx;
    private final String zzbEy;
    private boolean zzbEz;
    private WorkSource zzbjm;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzbay(Context context, int i, String str) {
        this(context, i, str, (String) null, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzbay(Context context, int i, String str, String str2, String str3) {
        this(context, i, str, str2, str3, (String) null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzbay(Context context, int i, String str, String str2, String str3, String str4) {
        this.zzbEz = true;
        zzac.zzh(str, "Wake lock name can NOT be empty");
        this.zzbEx = i;
        this.zzbEy = str2;
        this.zzaHH = str4;
        this.mContext = context.getApplicationContext();
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            String valueOf = String.valueOf(zzbEv);
            String valueOf2 = String.valueOf(str);
            this.zzaHF = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            this.zzaHF = str;
        }
        this.zzbEw = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (zzz.zzbf(this.mContext)) {
            this.zzbjm = zzz.zzF(context, zzw.zzdz(str3) ? context.getPackageName() : str3);
            zzc(this.zzbjm);
        }
    }

    private void zzd(WorkSource workSource) {
        try {
            this.zzbEw.setWorkSource(workSource);
        } catch (IllegalArgumentException e) {
            Log.wtf(TAG, e.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        if (r11.zzbEB == 1) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r0 == false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzgM(java.lang.String r12) {
        /*
            r11 = this;
            boolean r0 = r11.zzgN(r12)
            java.lang.String r6 = r11.zzo((java.lang.String) r12, (boolean) r0)
            monitor-enter(r11)
            boolean r12 = r11.zzbEz     // Catch:{ all -> 0x0043 }
            r10 = 1
            if (r12 == 0) goto L_0x0017
            int r12 = r11.zzbEA     // Catch:{ all -> 0x0043 }
            int r12 = r12 - r10
            r11.zzbEA = r12     // Catch:{ all -> 0x0043 }
            if (r12 == 0) goto L_0x001f
            if (r0 != 0) goto L_0x001f
        L_0x0017:
            boolean r12 = r11.zzbEz     // Catch:{ all -> 0x0043 }
            if (r12 != 0) goto L_0x0041
            int r12 = r11.zzbEB     // Catch:{ all -> 0x0043 }
            if (r12 != r10) goto L_0x0041
        L_0x001f:
            com.google.android.gms.common.stats.zze r1 = com.google.android.gms.common.stats.zze.zzyX()     // Catch:{ all -> 0x0043 }
            android.content.Context r2 = r11.mContext     // Catch:{ all -> 0x0043 }
            android.os.PowerManager$WakeLock r12 = r11.zzbEw     // Catch:{ all -> 0x0043 }
            java.lang.String r3 = com.google.android.gms.common.stats.zzc.zza(r12, r6)     // Catch:{ all -> 0x0043 }
            r4 = 8
            java.lang.String r5 = r11.zzaHF     // Catch:{ all -> 0x0043 }
            java.lang.String r7 = r11.zzaHH     // Catch:{ all -> 0x0043 }
            int r8 = r11.zzbEx     // Catch:{ all -> 0x0043 }
            android.os.WorkSource r12 = r11.zzbjm     // Catch:{ all -> 0x0043 }
            java.util.List r9 = com.google.android.gms.common.util.zzz.zzb(r12)     // Catch:{ all -> 0x0043 }
            r1.zza(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0043 }
            int r12 = r11.zzbEB     // Catch:{ all -> 0x0043 }
            int r12 = r12 - r10
            r11.zzbEB = r12     // Catch:{ all -> 0x0043 }
        L_0x0041:
            monitor-exit(r11)     // Catch:{ all -> 0x0043 }
            return
        L_0x0043:
            r12 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0043 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbay.zzgM(java.lang.String):void");
    }

    private boolean zzgN(String str) {
        return !TextUtils.isEmpty(str) && !str.equals(this.zzbEy);
    }

    private String zzo(String str, boolean z) {
        return (!this.zzbEz || !z) ? this.zzbEy : str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        if (r12.zzbEB == 0) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r0 == false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzo(java.lang.String r13, long r14) {
        /*
            r12 = this;
            boolean r0 = r12.zzgN(r13)
            java.lang.String r6 = r12.zzo((java.lang.String) r13, (boolean) r0)
            monitor-enter(r12)
            boolean r13 = r12.zzbEz     // Catch:{ all -> 0x0044 }
            if (r13 == 0) goto L_0x0017
            int r13 = r12.zzbEA     // Catch:{ all -> 0x0044 }
            int r1 = r13 + 1
            r12.zzbEA = r1     // Catch:{ all -> 0x0044 }
            if (r13 == 0) goto L_0x001f
            if (r0 != 0) goto L_0x001f
        L_0x0017:
            boolean r13 = r12.zzbEz     // Catch:{ all -> 0x0044 }
            if (r13 != 0) goto L_0x0042
            int r13 = r12.zzbEB     // Catch:{ all -> 0x0044 }
            if (r13 != 0) goto L_0x0042
        L_0x001f:
            com.google.android.gms.common.stats.zze r1 = com.google.android.gms.common.stats.zze.zzyX()     // Catch:{ all -> 0x0044 }
            android.content.Context r2 = r12.mContext     // Catch:{ all -> 0x0044 }
            android.os.PowerManager$WakeLock r13 = r12.zzbEw     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = com.google.android.gms.common.stats.zzc.zza(r13, r6)     // Catch:{ all -> 0x0044 }
            r4 = 7
            java.lang.String r5 = r12.zzaHF     // Catch:{ all -> 0x0044 }
            java.lang.String r7 = r12.zzaHH     // Catch:{ all -> 0x0044 }
            int r8 = r12.zzbEx     // Catch:{ all -> 0x0044 }
            android.os.WorkSource r13 = r12.zzbjm     // Catch:{ all -> 0x0044 }
            java.util.List r9 = com.google.android.gms.common.util.zzz.zzb(r13)     // Catch:{ all -> 0x0044 }
            r10 = r14
            r1.zza(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0044 }
            int r13 = r12.zzbEB     // Catch:{ all -> 0x0044 }
            int r13 = r13 + 1
            r12.zzbEB = r13     // Catch:{ all -> 0x0044 }
        L_0x0042:
            monitor-exit(r12)     // Catch:{ all -> 0x0044 }
            return
        L_0x0044:
            r13 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0044 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbay.zzo(java.lang.String, long):void");
    }

    public void acquire(long j) {
        zzt.zzzg();
        zzo((String) null, j);
        this.zzbEw.acquire(j);
    }

    public boolean isHeld() {
        return this.zzbEw.isHeld();
    }

    public void release() {
        zzgM((String) null);
        this.zzbEw.release();
    }

    public void setReferenceCounted(boolean z) {
        this.zzbEw.setReferenceCounted(z);
        this.zzbEz = z;
    }

    public void zzc(WorkSource workSource) {
        if (workSource != null && zzz.zzbf(this.mContext)) {
            if (this.zzbjm != null) {
                this.zzbjm.add(workSource);
            } else {
                this.zzbjm = workSource;
            }
            zzd(this.zzbjm);
        }
    }
}
