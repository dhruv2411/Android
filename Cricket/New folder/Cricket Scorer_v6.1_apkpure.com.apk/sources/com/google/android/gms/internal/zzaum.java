package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzac;

public final class zzaum {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public final zza zzbvT;

    public interface zza {
        boolean callServiceStopSelfResult(int i);

        Context getContext();
    }

    public zzaum(zza zza2) {
        this.mContext = zza2.getContext();
        zzac.zzw(this.mContext);
        this.zzbvT = zza2;
    }

    private zzatx zzKl() {
        return zzaue.zzbM(this.mContext).zzKl();
    }

    public static boolean zzj(Context context, boolean z) {
        zzac.zzw(context);
        return zzaut.zzy(context, z ? "com.google.android.gms.measurement.PackageMeasurementService" : "com.google.android.gms.measurement.AppMeasurementService");
    }

    @MainThread
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            zzKl().zzLY().log("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzauf(zzaue.zzbM(this.mContext));
        }
        zzKl().zzMa().zzj("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public void onCreate() {
        zzaue zzbM = zzaue.zzbM(this.mContext);
        zzatx zzKl = zzbM.zzKl();
        zzbM.zzKn().zzLg();
        zzKl.zzMe().log("Local AppMeasurementService is starting up");
    }

    @MainThread
    public void onDestroy() {
        zzaue zzbM = zzaue.zzbM(this.mContext);
        zzatx zzKl = zzbM.zzKl();
        zzbM.zzKn().zzLg();
        zzKl.zzMe().log("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public void onRebind(Intent intent) {
        if (intent == null) {
            zzKl().zzLY().log("onRebind called with null intent");
            return;
        }
        zzKl().zzMe().zzj("onRebind called. action", intent.getAction());
    }

    @MainThread
    public int onStartCommand(Intent intent, int i, final int i2) {
        final zzaue zzbM = zzaue.zzbM(this.mContext);
        final zzatx zzKl = zzbM.zzKl();
        if (intent == null) {
            zzKl.zzMa().log("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzbM.zzKn().zzLg();
        zzKl.zzMe().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzbM.zzKk().zzm(new Runnable() {
                public void run() {
                    zzbM.zzML();
                    zzbM.zzMG();
                    zzaum.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (zzaum.this.zzbvT.callServiceStopSelfResult(i2)) {
                                zzbM.zzKn().zzLg();
                                zzKl.zzMe().log("Local AppMeasurementService processed last upload request");
                            }
                        }
                    });
                }
            });
        }
        return 2;
    }

    @MainThread
    public boolean onUnbind(Intent intent) {
        if (intent == null) {
            zzKl().zzLY().log("onUnbind called with null intent");
            return true;
        }
        zzKl().zzMe().zzj("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
