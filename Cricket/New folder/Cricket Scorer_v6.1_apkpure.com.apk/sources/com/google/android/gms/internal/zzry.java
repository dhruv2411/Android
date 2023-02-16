package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzry extends zzsa {
    /* access modifiers changed from: private */
    public final zzsi zzadG;

    public zzry(zzsc zzsc, zzsd zzsd) {
        super(zzsc);
        zzac.zzw(zzsd);
        this.zzadG = zzsd.zzj(zzsc);
    }

    /* access modifiers changed from: package-private */
    public void onServiceConnected() {
        zzmR();
        this.zzadG.onServiceConnected();
    }

    public void setLocalDispatchPeriod(final int i) {
        zzob();
        zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(i));
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zzx(((long) i) * 1000);
            }
        });
    }

    public void start() {
        this.zzadG.start();
    }

    public void zzW(final boolean z) {
        zza("Network connectivity status changed", Boolean.valueOf(z));
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zzW(z);
            }
        });
    }

    public long zza(zzse zzse) {
        zzob();
        zzac.zzw(zzse);
        zzmR();
        long zza = this.zzadG.zza(zzse, true);
        if (zza == 0) {
            this.zzadG.zzc(zzse);
        }
        return zza;
    }

    public void zza(final zzsu zzsu) {
        zzob();
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zzb(zzsu);
            }
        });
    }

    public void zza(final zzsz zzsz) {
        zzac.zzw(zzsz);
        zzob();
        zzb("Hit delivery requested", zzsz);
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zza(zzsz);
            }
        });
    }

    public void zza(final String str, final Runnable runnable) {
        zzac.zzh(str, "campaign param can't be empty");
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zzbX(str);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        this.zzadG.initialize();
    }

    public void zznK() {
        zzob();
        zznU().zzg(new Runnable() {
            public void run() {
                zzry.this.zzadG.zznK();
            }
        });
    }

    public void zznL() {
        zzob();
        Context context = getContext();
        if (!zzth.zzak(context) || !zzti.zzal(context)) {
            zza((zzsu) null);
            return;
        }
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
        context.startService(intent);
    }

    public boolean zznM() {
        zzob();
        try {
            zznU().zzc(new Callable<Void>() {
                /* renamed from: zzbk */
                public Void call() throws Exception {
                    zzry.this.zzadG.zzoG();
                    return null;
                }
            }).get(4, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            zzd("syncDispatchLocalHits interrupted", e);
            return false;
        } catch (ExecutionException e2) {
            zze("syncDispatchLocalHits failed", e2);
            return false;
        } catch (TimeoutException e3) {
            zzd("syncDispatchLocalHits timed out", e3);
            return false;
        }
    }

    public void zznN() {
        zzob();
        zzh.zzmR();
        this.zzadG.zznN();
    }

    public void zznO() {
        zzbP("Radio powered up");
        zznL();
    }

    /* access modifiers changed from: package-private */
    public void zznP() {
        zzmR();
        this.zzadG.zznP();
    }
}
