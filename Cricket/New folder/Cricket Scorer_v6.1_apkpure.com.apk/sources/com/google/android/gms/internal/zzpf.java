package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.ads.AdActivity;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

@zzme
public class zzpf {
    final String zzWD;
    int zzWW = -1;
    long zzWY = -1;
    long zzWZ = -1;
    int zzXa = -1;
    long zzXb = 0;
    int zzXc = 0;
    int zzXd = 0;
    private final Object zzrJ = new Object();

    public zzpf(String str) {
        this.zzWD = str;
    }

    public static boolean zzI(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        if (identifier != 0) {
            try {
                if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                    return true;
                }
                zzpk.zzbg("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
                return false;
            } catch (PackageManager.NameNotFoundException unused) {
                zzpk.zzbh("Fail to fetch AdActivity theme");
            }
        }
        zzpk.zzbg("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
        return false;
    }

    public void zzai(int i) {
        this.zzWW = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzb(com.google.android.gms.internal.zzec r11, long r12) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.zzrJ
            monitor-enter(r0)
            com.google.android.gms.internal.zzpe r1 = com.google.android.gms.ads.internal.zzw.zzcQ()     // Catch:{ all -> 0x007d }
            long r1 = r1.zzks()     // Catch:{ all -> 0x007d }
            com.google.android.gms.common.util.zze r3 = com.google.android.gms.ads.internal.zzw.zzcS()     // Catch:{ all -> 0x007d }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x007d }
            long r5 = r10.zzWZ     // Catch:{ all -> 0x007d }
            r7 = -1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0041
            long r5 = r3 - r1
            com.google.android.gms.internal.zzfz<java.lang.Long> r1 = com.google.android.gms.internal.zzgd.zzCR     // Catch:{ all -> 0x007d }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x007d }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ all -> 0x007d }
            long r1 = r1.longValue()     // Catch:{ all -> 0x007d }
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 <= 0) goto L_0x0032
            r1 = -1
            r10.zzai(r1)     // Catch:{ all -> 0x007d }
            goto L_0x003d
        L_0x0032:
            com.google.android.gms.internal.zzpe r1 = com.google.android.gms.ads.internal.zzw.zzcQ()     // Catch:{ all -> 0x007d }
            int r1 = r1.zzku()     // Catch:{ all -> 0x007d }
            r10.zzai(r1)     // Catch:{ all -> 0x007d }
        L_0x003d:
            r10.zzWZ = r12     // Catch:{ all -> 0x007d }
            long r12 = r10.zzWZ     // Catch:{ all -> 0x007d }
        L_0x0041:
            r10.zzWY = r12     // Catch:{ all -> 0x007d }
            android.os.Bundle r12 = r11.extras     // Catch:{ all -> 0x007d }
            r13 = 1
            if (r12 == 0) goto L_0x0055
            android.os.Bundle r11 = r11.extras     // Catch:{ all -> 0x007d }
            java.lang.String r12 = "gw"
            r1 = 2
            int r11 = r11.getInt(r12, r1)     // Catch:{ all -> 0x007d }
            if (r11 != r13) goto L_0x0055
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            return
        L_0x0055:
            int r11 = r10.zzXa     // Catch:{ all -> 0x007d }
            int r11 = r11 + r13
            r10.zzXa = r11     // Catch:{ all -> 0x007d }
            int r11 = r10.zzWW     // Catch:{ all -> 0x007d }
            int r11 = r11 + r13
            r10.zzWW = r11     // Catch:{ all -> 0x007d }
            int r11 = r10.zzWW     // Catch:{ all -> 0x007d }
            if (r11 != 0) goto L_0x006f
            r11 = 0
            r10.zzXb = r11     // Catch:{ all -> 0x007d }
            com.google.android.gms.internal.zzpe r11 = com.google.android.gms.ads.internal.zzw.zzcQ()     // Catch:{ all -> 0x007d }
            r11.zzp(r3)     // Catch:{ all -> 0x007d }
            goto L_0x007b
        L_0x006f:
            com.google.android.gms.internal.zzpe r11 = com.google.android.gms.ads.internal.zzw.zzcQ()     // Catch:{ all -> 0x007d }
            long r11 = r11.zzkt()     // Catch:{ all -> 0x007d }
            long r1 = r3 - r11
            r10.zzXb = r1     // Catch:{ all -> 0x007d }
        L_0x007b:
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            return
        L_0x007d:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpf.zzb(com.google.android.gms.internal.zzec, long):void");
    }

    public void zzjV() {
        synchronized (this.zzrJ) {
            this.zzXd++;
        }
    }

    public void zzjW() {
        synchronized (this.zzrJ) {
            this.zzXc++;
        }
    }

    public long zzkE() {
        return this.zzWZ;
    }

    public int zzku() {
        return this.zzWW;
    }

    public Bundle zzo(Context context, String str) {
        Bundle bundle;
        synchronized (this.zzrJ) {
            bundle = new Bundle();
            bundle.putString("session_id", this.zzWD);
            bundle.putLong("basets", this.zzWZ);
            bundle.putLong("currts", this.zzWY);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzXa);
            bundle.putInt("preqs_in_session", this.zzWW);
            bundle.putLong("time_in_session", this.zzXb);
            bundle.putInt("pclick", this.zzXc);
            bundle.putInt("pimp", this.zzXd);
            bundle.putBoolean("support_transparent_background", zzI(context));
        }
        return bundle;
    }
}
