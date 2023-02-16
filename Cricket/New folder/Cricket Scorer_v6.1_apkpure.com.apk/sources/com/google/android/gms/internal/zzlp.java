package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;

@zzme
public abstract class zzlp extends zzpj {
    protected final Context mContext;
    protected final zzlq.zza zzPQ;
    protected final zzpb.zza zzPR;
    protected zzmn zzPS;
    protected final Object zzPU = new Object();
    protected final Object zzrJ = new Object();

    protected static final class zza extends Exception {
        private final int zzPY;

        public zza(String str, int i) {
            super(str);
            this.zzPY = i;
        }

        public int getErrorCode() {
            return this.zzPY;
        }
    }

    protected zzlp(Context context, zzpb.zza zza2, zzlq.zza zza3) {
        super(true);
        this.mContext = context;
        this.zzPR = zza2;
        this.zzPS = zza2.zzWm;
        this.zzPQ = zza3;
    }

    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public abstract zzpb zzR(int i);

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033 A[Catch:{ zza -> 0x0014 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003b A[Catch:{ zza -> 0x0014 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzco() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.zzrJ
            monitor-enter(r0)
            java.lang.String r1 = "AdRendererBackgroundTask started."
            com.google.android.gms.internal.zzpk.zzbf(r1)     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzpb$zza r1 = r5.zzPR     // Catch:{ all -> 0x0060 }
            int r1 = r1.errorCode     // Catch:{ all -> 0x0060 }
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ zza -> 0x0014 }
            r5.zzh(r2)     // Catch:{ zza -> 0x0014 }
            goto L_0x0050
        L_0x0014:
            r1 = move-exception
            int r2 = r1.getErrorCode()     // Catch:{ all -> 0x0060 }
            r3 = 3
            if (r2 == r3) goto L_0x0028
            r3 = -1
            if (r2 != r3) goto L_0x0020
            goto L_0x0028
        L_0x0020:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzpk.zzbh(r1)     // Catch:{ all -> 0x0060 }
            goto L_0x002f
        L_0x0028:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzpk.zzbg(r1)     // Catch:{ all -> 0x0060 }
        L_0x002f:
            com.google.android.gms.internal.zzmn r1 = r5.zzPS     // Catch:{ all -> 0x0060 }
            if (r1 != 0) goto L_0x003b
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x0060 }
            r1.<init>(r2)     // Catch:{ all -> 0x0060 }
        L_0x0038:
            r5.zzPS = r1     // Catch:{ all -> 0x0060 }
            goto L_0x0045
        L_0x003b:
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzmn r3 = r5.zzPS     // Catch:{ all -> 0x0060 }
            long r3 = r3.zzKL     // Catch:{ all -> 0x0060 }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0060 }
            goto L_0x0038
        L_0x0045:
            android.os.Handler r1 = com.google.android.gms.internal.zzpo.zzXC     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzlp$1 r3 = new com.google.android.gms.internal.zzlp$1     // Catch:{ all -> 0x0060 }
            r3.<init>()     // Catch:{ all -> 0x0060 }
            r1.post(r3)     // Catch:{ all -> 0x0060 }
            r1 = r2
        L_0x0050:
            com.google.android.gms.internal.zzpb r1 = r5.zzR(r1)     // Catch:{ all -> 0x0060 }
            android.os.Handler r2 = com.google.android.gms.internal.zzpo.zzXC     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.zzlp$2 r3 = new com.google.android.gms.internal.zzlp$2     // Catch:{ all -> 0x0060 }
            r3.<init>(r1)     // Catch:{ all -> 0x0060 }
            r2.post(r3)     // Catch:{ all -> 0x0060 }
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x0060:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlp.zzco():void");
    }

    /* access modifiers changed from: protected */
    public abstract void zzh(long j) throws zza;

    /* access modifiers changed from: protected */
    public void zzn(zzpb zzpb) {
        this.zzPQ.zzb(zzpb);
    }
}
