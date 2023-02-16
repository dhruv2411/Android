package com.google.android.gms.measurement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.internal.zzaub;

public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzaub.zza {
    private zzaub zzbqj;

    private zzaub zzJS() {
        if (this.zzbqj == null) {
            this.zzbqj = new zzaub(this);
        }
        return this.zzbqj;
    }

    @MainThread
    public void doStartService(Context context, Intent intent) {
        startWakefulService(context, intent);
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        zzJS().onReceive(context, intent);
    }
}
