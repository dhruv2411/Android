package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzsc;
import com.google.android.gms.internal.zztd;
import com.google.android.gms.internal.zztm;

public class CampaignTrackingReceiver extends BroadcastReceiver {
    static zzbay zzabw;
    static Boolean zzabx;
    static Object zztX = new Object();

    public static boolean zzak(Context context) {
        zzac.zzw(context);
        if (zzabx != null) {
            return zzabx.booleanValue();
        }
        boolean zza = zztm.zza(context, "com.google.android.gms.analytics.CampaignTrackingReceiver", true);
        zzabx = Boolean.valueOf(zza);
        return zza;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onReceive(Context context, Intent intent) {
        String str;
        zztd zznS = zzsc.zzan(context).zznS();
        if (intent == null) {
            str = "CampaignTrackingReceiver received null intent";
        } else {
            String stringExtra = intent.getStringExtra("referrer");
            String action = intent.getAction();
            zznS.zza("CampaignTrackingReceiver received", action);
            if (!"com.android.vending.INSTALL_REFERRER".equals(action) || TextUtils.isEmpty(stringExtra)) {
                str = "CampaignTrackingReceiver received unexpected intent without referrer extra";
            } else {
                boolean zzal = CampaignTrackingService.zzal(context);
                if (!zzal) {
                    zznS.zzbS("CampaignTrackingService not registered or disabled. Installation tracking not possible. See http://goo.gl/8Rd3yj for instructions.");
                }
                zzw(context, stringExtra);
                Class<? extends CampaignTrackingService> zzms = zzms();
                zzac.zzw(zzms);
                Intent intent2 = new Intent(context, zzms);
                intent2.putExtra("referrer", stringExtra);
                synchronized (zztX) {
                    context.startService(intent2);
                    if (zzal) {
                        try {
                            if (zzabw == null) {
                                zzabw = new zzbay(context, 1, "Analytics campaign WakeLock");
                                zzabw.setReferenceCounted(false);
                            }
                            zzabw.acquire(1000);
                        } catch (SecurityException unused) {
                            zznS.zzbS("CampaignTrackingService service at risk of not starting. For more reliable installation campaign reports, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                        }
                    } else {
                        return;
                    }
                }
            }
        }
        zznS.zzbS(str);
    }

    /* access modifiers changed from: protected */
    public Class<? extends CampaignTrackingService> zzms() {
        return CampaignTrackingService.class;
    }

    /* access modifiers changed from: protected */
    public void zzw(Context context, String str) {
    }
}
