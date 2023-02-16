package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.util.zzt;
import com.google.firebase.iid.zzb;

public final class FirebaseInstanceIdInternalReceiver extends WakefulBroadcastReceiver {
    private static boolean zzbgs = false;
    private zzb.C0151zzb zzcll;
    private zzb.C0151zzb zzclm;

    private zzb.C0151zzb zzK(Context context, String str) {
        if ("com.google.firebase.MESSAGING_EVENT".equals(str)) {
            if (this.zzclm == null) {
                this.zzclm = new zzb.C0151zzb(context, str);
            }
            return this.zzclm;
        }
        if (this.zzcll == null) {
            this.zzcll = new zzb.C0151zzb(context, str);
        }
        return this.zzcll;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            if (!(parcelableExtra instanceof Intent)) {
                Log.e("FirebaseInstanceId", "Missing or invalid wrapped intent");
                return;
            }
            Intent intent2 = (Intent) parcelableExtra;
            if (zzt.zzzq()) {
                zzK(context, intent.getAction()).zzb(intent2, goAsync());
            } else {
                zzg.zzabU().zzb(context, intent.getAction(), intent2);
            }
        }
    }
}
