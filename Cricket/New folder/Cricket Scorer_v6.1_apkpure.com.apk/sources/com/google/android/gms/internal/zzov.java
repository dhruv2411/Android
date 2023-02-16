package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzcy;

@zzme
public class zzov implements zzcy.zzb {
    private final Context mContext;
    boolean zzVV = false;
    private final Object zzrJ = new Object();
    private final String zzts;

    public zzov(Context context, String str) {
        this.mContext = context;
        this.zzts = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzC(boolean r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.zzow r0 = com.google.android.gms.ads.internal.zzw.zzdl()
            boolean r0 = r0.zzjQ()
            if (r0 != 0) goto L_0x000b
            return
        L_0x000b:
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
            boolean r1 = r3.zzVV     // Catch:{ all -> 0x0033 }
            if (r1 != r4) goto L_0x0014
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x0014:
            r3.zzVV = r4     // Catch:{ all -> 0x0033 }
            boolean r4 = r3.zzVV     // Catch:{ all -> 0x0033 }
            if (r4 == 0) goto L_0x0026
            com.google.android.gms.internal.zzow r4 = com.google.android.gms.ads.internal.zzw.zzdl()     // Catch:{ all -> 0x0033 }
            android.content.Context r1 = r3.mContext     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = r3.zzts     // Catch:{ all -> 0x0033 }
            r4.zzd(r1, r2)     // Catch:{ all -> 0x0033 }
            goto L_0x0031
        L_0x0026:
            com.google.android.gms.internal.zzow r4 = com.google.android.gms.ads.internal.zzw.zzdl()     // Catch:{ all -> 0x0033 }
            android.content.Context r1 = r3.mContext     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = r3.zzts     // Catch:{ all -> 0x0033 }
            r4.zze(r1, r2)     // Catch:{ all -> 0x0033 }
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x0033:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzov.zzC(boolean):void");
    }

    public void zza(zzcy.zza zza) {
        zzC(zza.zzxl);
    }
}
