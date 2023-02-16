package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzatx;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzaub {
    private final zza zzbty;

    public interface zza {
        void doStartService(Context context, Intent intent);
    }

    public zzaub(zza zza2) {
        zzac.zzw(zza2);
        this.zzbty = zza2;
    }

    public static boolean zzi(Context context, boolean z) {
        zzac.zzw(context);
        return zzaut.zza(context, z ? "com.google.android.gms.measurement.PackageMeasurementReceiver" : "com.google.android.gms.measurement.AppMeasurementReceiver", false);
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        zzatx.zza zzMe;
        String str;
        final zzaue zzbM = zzaue.zzbM(context);
        final zzatx zzKl = zzbM.zzKl();
        if (intent == null) {
            zzMe = zzKl.zzMa();
            str = "Receiver called with null intent";
        } else {
            zzbM.zzKn().zzLg();
            String action = intent.getAction();
            zzKl.zzMe().zzj("Local receiver got", action);
            if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
                zzaum.zzj(context, false);
                Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
                className.setAction("com.google.android.gms.measurement.UPLOAD");
                this.zzbty.doStartService(context, className);
                return;
            } else if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
                String stringExtra = intent.getStringExtra("referrer");
                if (stringExtra == null) {
                    zzMe = zzKl.zzMe();
                    str = "Install referrer extras are null";
                } else {
                    final Bundle zzu = zzbM.zzKh().zzu(Uri.parse(stringExtra));
                    if (zzu == null) {
                        zzKl.zzMe().log("No campaign defined in install referrer broadcast");
                        return;
                    }
                    long longExtra = 1000 * intent.getLongExtra("referrer_timestamp_seconds", 0);
                    if (longExtra == 0) {
                        zzKl.zzMa().log("Install referrer is missing timestamp");
                    }
                    final long j = longExtra;
                    final Context context2 = context;
                    zzbM.zzKk().zzm(new Runnable(this) {
                        public void run() {
                            zzaus zzS = zzbM.zzKg().zzS(zzbM.zzKb().zzke(), "_fot");
                            long longValue = (zzS == null || !(zzS.mValue instanceof Long)) ? 0 : ((Long) zzS.mValue).longValue();
                            long j = j;
                            if (longValue > 0 && (j >= longValue || j <= 0)) {
                                j = longValue - 1;
                            }
                            if (j > 0) {
                                zzu.putLong("click_timestamp", j);
                            }
                            AppMeasurement.getInstance(context2).logEventInternal("auto", "_cmp", zzu);
                            zzKl.zzMe().log("Install campaign recorded");
                        }
                    });
                    return;
                }
            } else {
                return;
            }
        }
        zzMe.log(str);
    }
}
