package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzac;

public final class zzti {
    private static Boolean zzaby;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public final zza zzagJ;

    public interface zza {
        boolean callServiceStopSelfResult(int i);

        Context getContext();
    }

    public zzti(zza zza2) {
        this.mContext = zza2.getContext();
        zzac.zzw(this.mContext);
        this.zzagJ = zza2;
    }

    public static boolean zzal(Context context) {
        zzac.zzw(context);
        if (zzaby != null) {
            return zzaby.booleanValue();
        }
        boolean zzy = zztm.zzy(context, "com.google.android.gms.analytics.AnalyticsService");
        zzaby = Boolean.valueOf(zzy);
        return zzy;
    }

    private void zzmt() {
        try {
            synchronized (zzth.zztX) {
                zzbay zzbay = zzth.zzabw;
                if (zzbay != null && zzbay.isHeld()) {
                    zzbay.release();
                }
            }
        } catch (SecurityException unused) {
        }
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onCreate() {
        zzsc.zzan(this.mContext).zznS().zzbP("Local AnalyticsService is starting up");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onDestroy() {
        zzsc.zzan(this.mContext).zznS().zzbP("Local AnalyticsService is shutting down");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public int onStartCommand(Intent intent, int i, final int i2) {
        zzmt();
        final zzsc zzan = zzsc.zzan(this.mContext);
        final zztd zznS = zzan.zznS();
        if (intent == null) {
            zznS.zzbS("AnalyticsService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zznS.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            zzan.zzmA().zza((zzsu) new zzsu() {
                public void zzf(Throwable th) {
                    zzti.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (zzti.this.zzagJ.callServiceStopSelfResult(i2)) {
                                zznS.zzbP("Local AnalyticsService processed last dispatch request");
                            }
                        }
                    });
                }
            });
        }
        return 2;
    }
}
