package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzsc;
import com.google.android.gms.internal.zztd;
import com.google.android.gms.internal.zztm;

public class CampaignTrackingService extends Service {
    private static Boolean zzaby;
    private Handler mHandler;

    private Handler getHandler() {
        Handler handler = this.mHandler;
        if (handler != null) {
            return handler;
        }
        Handler handler2 = new Handler(getMainLooper());
        this.mHandler = handler2;
        return handler2;
    }

    public static boolean zzal(Context context) {
        zzac.zzw(context);
        if (zzaby != null) {
            return zzaby.booleanValue();
        }
        boolean zzy = zztm.zzy(context, "com.google.android.gms.analytics.CampaignTrackingService");
        zzaby = Boolean.valueOf(zzy);
        return zzy;
    }

    private void zzmt() {
        try {
            synchronized (CampaignTrackingReceiver.zztX) {
                zzbay zzbay = CampaignTrackingReceiver.zzabw;
                if (zzbay != null && zzbay.isHeld()) {
                    zzbay.release();
                }
            }
        } catch (SecurityException unused) {
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onCreate() {
        super.onCreate();
        zzsc.zzan(this).zznS().zzbP("CampaignTrackingService is starting up");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onDestroy() {
        zzsc.zzan(this).zznS().zzbP("CampaignTrackingService is shutting down");
        super.onDestroy();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public int onStartCommand(Intent intent, int i, final int i2) {
        zzmt();
        zzsc zzan = zzsc.zzan(this);
        final zztd zznS = zzan.zznS();
        String stringExtra = intent.getStringExtra("referrer");
        final Handler handler = getHandler();
        if (TextUtils.isEmpty(stringExtra)) {
            zznS.zzbS("No campaign found on com.android.vending.INSTALL_REFERRER \"referrer\" extra");
            zzan.zznU().zzg(new Runnable() {
                public void run() {
                    CampaignTrackingService.this.zza(zznS, handler, i2);
                }
            });
            return 2;
        }
        int zzoZ = zzan.zznT().zzoZ();
        if (stringExtra.length() > zzoZ) {
            zznS.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", Integer.valueOf(stringExtra.length()), Integer.valueOf(zzoZ));
            stringExtra = stringExtra.substring(0, zzoZ);
        }
        zznS.zza("CampaignTrackingService called. startId, campaign", Integer.valueOf(i2), stringExtra);
        zzan.zzmA().zza(stringExtra, new Runnable() {
            public void run() {
                CampaignTrackingService.this.zza(zznS, handler, i2);
            }
        });
        return 2;
    }

    /* access modifiers changed from: protected */
    public void zza(final zztd zztd, Handler handler, final int i) {
        handler.post(new Runnable() {
            public void run() {
                boolean stopSelfResult = CampaignTrackingService.this.stopSelfResult(i);
                if (stopSelfResult) {
                    zztd.zza("Install campaign broadcast processed", Boolean.valueOf(stopSelfResult));
                }
            }
        });
    }
}
