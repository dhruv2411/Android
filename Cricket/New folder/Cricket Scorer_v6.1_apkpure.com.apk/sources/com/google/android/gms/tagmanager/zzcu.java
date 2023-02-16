package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaj;
import com.google.android.gms.tagmanager.zzp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class zzcu implements zzp.zze {
    private boolean mClosed;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final String zzbEU;
    private String zzbFs;
    private zzbn<zzaj.zzj> zzbHt;
    private zzt zzbHu;
    private final ScheduledExecutorService zzbHw;
    private final zza zzbHx;
    private ScheduledFuture<?> zzbHy;

    interface zza {
        zzct zza(zzt zzt);
    }

    interface zzb {
        ScheduledExecutorService zzRl();
    }

    public zzcu(Context context, String str, zzt zzt) {
        this(context, str, zzt, (zzb) null, (zza) null);
    }

    zzcu(Context context, String str, zzt zzt, zzb zzb2, zza zza2) {
        this.zzbHu = zzt;
        this.mContext = context;
        this.zzbEU = str;
        this.zzbHw = (zzb2 == null ? new zzb(this) {
            public ScheduledExecutorService zzRl() {
                return Executors.newSingleThreadScheduledExecutor();
            }
        } : zzb2).zzRl();
        if (zza2 == null) {
            this.zzbHx = new zza() {
                public zzct zza(zzt zzt) {
                    return new zzct(zzcu.this.mContext, zzcu.this.zzbEU, zzt);
                }
            };
        } else {
            this.zzbHx = zza2;
        }
    }

    private synchronized void zzRk() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    private zzct zzhp(String str) {
        zzct zza2 = this.zzbHx.zza(this.zzbHu);
        zza2.zza(this.zzbHt);
        zza2.zzgZ(this.zzbFs);
        zza2.zzho(str);
        return zza2;
    }

    public synchronized void release() {
        zzRk();
        if (this.zzbHy != null) {
            this.zzbHy.cancel(false);
        }
        this.zzbHw.shutdown();
        this.mClosed = true;
    }

    public synchronized void zza(zzbn<zzaj.zzj> zzbn) {
        zzRk();
        this.zzbHt = zzbn;
    }

    public synchronized void zzf(long j, String str) {
        String str2 = this.zzbEU;
        StringBuilder sb = new StringBuilder(55 + String.valueOf(str2).length());
        sb.append("loadAfterDelay: containerId=");
        sb.append(str2);
        sb.append(" delay=");
        sb.append(j);
        zzbo.v(sb.toString());
        zzRk();
        if (this.zzbHt == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.zzbHy != null) {
            this.zzbHy.cancel(false);
        }
        this.zzbHy = this.zzbHw.schedule(zzhp(str), j, TimeUnit.MILLISECONDS);
    }

    public synchronized void zzgZ(String str) {
        zzRk();
        this.zzbFs = str;
    }
}
