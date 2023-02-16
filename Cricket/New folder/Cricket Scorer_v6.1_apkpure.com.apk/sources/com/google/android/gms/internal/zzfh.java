package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zzd;

@zzme
public class zzfh {
    private static zzfh zzAy;
    private static final Object zztX = new Object();
    private RewardedVideoAd zzAA;
    private zzey zzAz;

    private zzfh() {
    }

    public static zzfh zzfk() {
        zzfh zzfh;
        synchronized (zztX) {
            if (zzAy == null) {
                zzAy = new zzfh();
            }
            zzfh = zzAy;
        }
        return zzfh;
    }

    public RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        synchronized (zztX) {
            if (this.zzAA != null) {
                RewardedVideoAd rewardedVideoAd = this.zzAA;
                return rewardedVideoAd;
            }
            this.zzAA = new zzoc(context, zzel.zzeU().zza(context, (zzka) new zzjz()));
            RewardedVideoAd rewardedVideoAd2 = this.zzAA;
            return rewardedVideoAd2;
        }
    }

    public void openDebugMenu(Context context, String str) {
        zzac.zza(this.zzAz != null, (Object) "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzAz.zzb(zzd.zzA(context), str);
        } catch (RemoteException e) {
            zzqf.zzb("Unable to open debug menu.", e);
        }
    }

    public void setAppMuted(boolean z) {
        zzac.zza(this.zzAz != null, (Object) "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzAz.setAppMuted(z);
        } catch (RemoteException e) {
            zzqf.zzb("Unable to set app mute state.", e);
        }
    }

    public void setAppVolume(float f) {
        boolean z = false;
        zzac.zzb(0.0f <= f && f <= 1.0f, (Object) "The app volume must be a value between 0 and 1 inclusive.");
        if (this.zzAz != null) {
            z = true;
        }
        zzac.zza(z, (Object) "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzAz.setAppVolume(f);
        } catch (RemoteException e) {
            zzqf.zzb("Unable to set app volume.", e);
        }
    }

    public void zza(final Context context, String str, zzfi zzfi) {
        synchronized (zztX) {
            if (this.zzAz == null) {
                if (context == null) {
                    throw new IllegalArgumentException("Context cannot be null.");
                }
                try {
                    this.zzAz = zzel.zzeU().zzl(context);
                    this.zzAz.initialize();
                    if (str != null) {
                        this.zzAz.zzc(str, zzd.zzA(new Runnable() {
                            public void run() {
                                zzfh.this.getRewardedVideoAdInstance(context);
                            }
                        }));
                    }
                } catch (RemoteException e) {
                    zzqf.zzc("MobileAdsSettingManager initialization failed", e);
                }
            }
        }
    }
}
