package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzdd;

@zzme
public class zzdo {
    @Nullable
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();
    private final Runnable zzyG = new Runnable() {
        public void run() {
            zzdo.this.disconnect();
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public zzdr zzyH;
    /* access modifiers changed from: private */
    @Nullable
    public zzdv zzyI;

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
            android.content.Context r1 = r3.mContext     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x0023
            com.google.android.gms.internal.zzdr r1 = r3.zzyH     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x000c
            goto L_0x0023
        L_0x000c:
            com.google.android.gms.internal.zzdo$3 r1 = new com.google.android.gms.internal.zzdo$3     // Catch:{ all -> 0x0025 }
            r1.<init>()     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.zzdo$4 r2 = new com.google.android.gms.internal.zzdo$4     // Catch:{ all -> 0x0025 }
            r2.<init>()     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.zzdr r1 = r3.zza((com.google.android.gms.common.internal.zzf.zzb) r1, (com.google.android.gms.common.internal.zzf.zzc) r2)     // Catch:{ all -> 0x0025 }
            r3.zzyH = r1     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.zzdr r1 = r3.zzyH     // Catch:{ all -> 0x0025 }
            r1.zzxz()     // Catch:{ all -> 0x0025 }
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return
        L_0x0025:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdo.connect():void");
    }

    /* access modifiers changed from: private */
    public void disconnect() {
        synchronized (this.zzrJ) {
            if (this.zzyH != null) {
                if (this.zzyH.isConnected() || this.zzyH.isConnecting()) {
                    this.zzyH.disconnect();
                }
                this.zzyH = null;
                this.zzyI = null;
                Binder.flushPendingCommands();
                zzw.zzdc().zzlc();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initialize(android.content.Context r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r0 = r2.zzrJ
            monitor-enter(r0)
            android.content.Context r1 = r2.mContext     // Catch:{ all -> 0x003c }
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            return
        L_0x000c:
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x003c }
            r2.mContext = r3     // Catch:{ all -> 0x003c }
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.internal.zzgd.zzFf     // Catch:{ all -> 0x003c }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x003c }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x003c }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x003c }
            if (r3 == 0) goto L_0x0024
            r2.connect()     // Catch:{ all -> 0x003c }
            goto L_0x003a
        L_0x0024:
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.internal.zzgd.zzFe     // Catch:{ all -> 0x003c }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x003c }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x003c }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x003c }
            if (r3 == 0) goto L_0x003a
            com.google.android.gms.internal.zzdo$2 r3 = new com.google.android.gms.internal.zzdo$2     // Catch:{ all -> 0x003c }
            r3.<init>()     // Catch:{ all -> 0x003c }
            r2.zza((com.google.android.gms.internal.zzdd.zzb) r3)     // Catch:{ all -> 0x003c }
        L_0x003a:
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            return
        L_0x003c:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdo.initialize(android.content.Context):void");
    }

    public zzdp zza(zzds zzds) {
        synchronized (this.zzrJ) {
            if (this.zzyI == null) {
                zzdp zzdp = new zzdp();
                return zzdp;
            }
            try {
                zzdp zza = this.zzyI.zza(zzds);
                return zza;
            } catch (RemoteException e) {
                zzpk.zzb("Unable to call into cache service.", e);
                return new zzdp();
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzdr zza(zzf.zzb zzb, zzf.zzc zzc) {
        return new zzdr(this.mContext, zzw.zzdc().zzlb(), zzb, zzc);
    }

    /* access modifiers changed from: protected */
    public void zza(zzdd.zzb zzb) {
        zzw.zzcP().zza(zzb);
    }

    public void zzev() {
        if (zzgd.zzFg.get().booleanValue()) {
            synchronized (this.zzrJ) {
                connect();
                zzw.zzcM();
                zzpo.zzXC.removeCallbacks(this.zzyG);
                zzw.zzcM();
                zzpo.zzXC.postDelayed(this.zzyG, zzgd.zzFh.get().longValue());
            }
        }
    }
}
