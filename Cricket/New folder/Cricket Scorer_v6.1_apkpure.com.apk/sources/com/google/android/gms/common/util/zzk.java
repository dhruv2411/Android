package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.firebase.analytics.FirebaseAnalytics;

public final class zzk {
    private static IntentFilter zzaIe = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzaIf = 0;
    private static float zzaIg = Float.NaN;

    @TargetApi(20)
    public static boolean zzb(PowerManager powerManager) {
        return zzt.zzzm() ? powerManager.isInteractive() : powerManager.isScreenOn();
    }

    @TargetApi(20)
    public static int zzbd(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, zzaIe);
        int i = 0;
        if (((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0) {
            i = 1;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        return ((zzb(powerManager) ? 1 : 0) << true) | i;
    }

    public static synchronized float zzbe(Context context) {
        synchronized (zzk.class) {
            if (SystemClock.elapsedRealtime() - zzaIf >= ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS || Float.isNaN(zzaIg)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, zzaIe);
                if (registerReceiver != null) {
                    zzaIg = ((float) registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzaIf = SystemClock.elapsedRealtime();
                float f = zzaIg;
                return f;
            }
            float f2 = zzaIg;
            return f2;
        }
    }
}
