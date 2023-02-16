package com.google.android.gms.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.common.internal.zzac;

public class zzst extends zzsa {
    private boolean zzafg;
    private boolean zzafh;
    private AlarmManager zzafi = ((AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM));

    protected zzst(zzsc zzsc) {
        super(zzsc);
    }

    private PendingIntent zzpE() {
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"));
        return PendingIntent.getBroadcast(getContext(), 0, intent, 0);
    }

    public void cancel() {
        zzob();
        this.zzafh = false;
        this.zzafi.cancel(zzpE());
    }

    public void schedule() {
        zzob();
        zzac.zza(zzpD(), (Object) "Receiver not registered");
        long zzpe = zznT().zzpe();
        if (zzpe > 0) {
            cancel();
            this.zzafh = true;
            this.zzafi.setInexactRepeating(2, zznR().elapsedRealtime() + zzpe, 0, zzpE());
        }
    }

    public boolean zzcy() {
        return this.zzafh;
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        ActivityInfo receiverInfo;
        try {
            this.zzafi.cancel(zzpE());
            if (zznT().zzpe() > 0 && (receiverInfo = getContext().getPackageManager().getReceiverInfo(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"), 2)) != null && receiverInfo.enabled) {
                zzbP("Receiver registered. Using alarm for local dispatch.");
                this.zzafg = true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public boolean zzpD() {
        return this.zzafg;
    }
}
