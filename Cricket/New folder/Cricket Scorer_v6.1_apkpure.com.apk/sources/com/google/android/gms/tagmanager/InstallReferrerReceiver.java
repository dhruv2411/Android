package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;

public final class InstallReferrerReceiver extends CampaignTrackingReceiver {
    /* access modifiers changed from: protected */
    public Class<? extends CampaignTrackingService> zzms() {
        return InstallReferrerService.class;
    }

    /* access modifiers changed from: protected */
    public void zzw(Context context, String str) {
        zzbf.zzhk(str);
        zzbf.zzN(context, str);
    }
}
