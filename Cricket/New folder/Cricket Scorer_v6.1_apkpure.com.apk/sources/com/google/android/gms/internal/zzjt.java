package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzjv;
import com.google.android.gms.internal.zzkc;

@zzme
public final class zzjt extends zzkc.zza {
    private zzjv.zza zzKU;
    private zzjs zzKV;
    private final Object zzrJ = new Object();

    public void onAdClicked() {
        synchronized (this.zzrJ) {
            if (this.zzKV != null) {
                this.zzKV.zzbP();
            }
        }
    }

    public void onAdClosed() {
        synchronized (this.zzrJ) {
            if (this.zzKV != null) {
                this.zzKV.zzbQ();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.zzrJ) {
            if (this.zzKU != null) {
                this.zzKU.zzF(i == 3 ? 1 : 2);
                this.zzKU = null;
            }
        }
    }

    public void onAdImpression() {
        synchronized (this.zzrJ) {
            if (this.zzKV != null) {
                this.zzKV.zzbU();
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.zzrJ) {
            if (this.zzKV != null) {
                this.zzKV.zzbR();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAdLoaded() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
            com.google.android.gms.internal.zzjv$zza r1 = r3.zzKU     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x0012
            com.google.android.gms.internal.zzjv$zza r1 = r3.zzKU     // Catch:{ all -> 0x001d }
            r2 = 0
            r1.zzF(r2)     // Catch:{ all -> 0x001d }
            r1 = 0
            r3.zzKU = r1     // Catch:{ all -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x0012:
            com.google.android.gms.internal.zzjs r1 = r3.zzKV     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x001b
            com.google.android.gms.internal.zzjs r1 = r3.zzKV     // Catch:{ all -> 0x001d }
            r1.zzbT()     // Catch:{ all -> 0x001d }
        L_0x001b:
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x001d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjt.onAdLoaded():void");
    }

    public void onAdOpened() {
        synchronized (this.zzrJ) {
            if (this.zzKV != null) {
                this.zzKV.zzbS();
            }
        }
    }

    public void zza(@Nullable zzjs zzjs) {
        synchronized (this.zzrJ) {
            this.zzKV = zzjs;
        }
    }

    public void zza(zzjv.zza zza) {
        synchronized (this.zzrJ) {
            this.zzKU = zza;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(com.google.android.gms.internal.zzkd r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
            com.google.android.gms.internal.zzjv$zza r1 = r3.zzKU     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x0012
            com.google.android.gms.internal.zzjv$zza r1 = r3.zzKU     // Catch:{ all -> 0x001d }
            r2 = 0
            r1.zza(r2, r4)     // Catch:{ all -> 0x001d }
            r4 = 0
            r3.zzKU = r4     // Catch:{ all -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x0012:
            com.google.android.gms.internal.zzjs r4 = r3.zzKV     // Catch:{ all -> 0x001d }
            if (r4 == 0) goto L_0x001b
            com.google.android.gms.internal.zzjs r4 = r3.zzKV     // Catch:{ all -> 0x001d }
            r4.zzbT()     // Catch:{ all -> 0x001d }
        L_0x001b:
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x001d:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjt.zza(com.google.android.gms.internal.zzkd):void");
    }
}
