package com.google.android.gms.internal;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public class zzbyb extends CustomTabsServiceConnection {
    private WeakReference<zzbyc> zzcwd;

    public zzbyb(zzbyc zzbyc) {
        this.zzcwd = new WeakReference<>(zzbyc);
    }

    public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzbyc zzbyc = (zzbyc) this.zzcwd.get();
        if (zzbyc != null) {
            zzbyc.zza(customTabsClient);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzbyc zzbyc = (zzbyc) this.zzcwd.get();
        if (zzbyc != null) {
            zzbyc.zzfI();
        }
    }
}
