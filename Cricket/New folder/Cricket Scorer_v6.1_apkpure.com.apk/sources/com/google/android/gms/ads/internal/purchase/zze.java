package com.google.android.gms.ads.internal.purchase;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.internal.zzld;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import util.IabHelper;

@zzme
public class zze extends zzlf.zza implements ServiceConnection {
    private final Activity mActivity;
    private Context zzPB;
    private zzld zzPC;
    private zzf zzPD;
    private zzj zzPE;
    private String zzPF = null;
    private zzb zzPt;
    zzh zzPu;
    private zzk zzPw;

    public zze(Activity activity) {
        this.mActivity = activity;
        this.zzPu = zzh.zzu(this.mActivity.getApplicationContext());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001) {
            boolean z = false;
            try {
                int zzd = zzw.zzda().zzd(intent);
                if (i2 == -1) {
                    zzw.zzda();
                    if (zzd == 0) {
                        if (this.zzPw.zza(this.zzPF, i2, intent)) {
                            z = true;
                        }
                        this.zzPC.recordPlayBillingResolution(zzd);
                        this.mActivity.finish();
                        zza(this.zzPC.getProductId(), z, i2, intent);
                        this.zzPF = null;
                    }
                }
                this.zzPu.zza(this.zzPD);
                this.zzPC.recordPlayBillingResolution(zzd);
                this.mActivity.finish();
                zza(this.zzPC.getProductId(), z, i2, intent);
            } catch (RemoteException unused) {
                zzpk.zzbh("Fail to process purchase result.");
                this.mActivity.finish();
            } catch (Throwable th) {
                this.zzPF = null;
                throw th;
            }
            this.zzPF = null;
        }
    }

    public void onCreate() {
        Activity activity;
        int zzkR;
        GInAppPurchaseManagerInfoParcel zzc = GInAppPurchaseManagerInfoParcel.zzc(this.mActivity.getIntent());
        this.zzPE = zzc.zzPp;
        this.zzPw = zzc.zzvL;
        this.zzPC = zzc.zzPn;
        this.zzPt = new zzb(this.mActivity.getApplicationContext());
        this.zzPB = zzc.zzPo;
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            activity = this.mActivity;
            zzkR = zzw.zzcO().zzkQ();
        } else {
            activity = this.mActivity;
            zzkR = zzw.zzcO().zzkR();
        }
        activity.setRequestedOrientation(zzkR);
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        zza.zzyJ().zza((Context) this.mActivity, intent, (ServiceConnection) this, 1);
    }

    public void onDestroy() {
        zza.zzyJ().zza(this.mActivity, this);
        this.zzPt.destroy();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zzPt.zzV(iBinder);
        try {
            this.zzPF = this.zzPw.zziM();
            Bundle zza = this.zzPt.zza(this.mActivity.getPackageName(), this.zzPC.getProductId(), this.zzPF);
            PendingIntent pendingIntent = (PendingIntent) zza.getParcelable(IabHelper.RESPONSE_BUY_INTENT);
            if (pendingIntent == null) {
                int zzd = zzw.zzda().zzd(zza);
                this.zzPC.recordPlayBillingResolution(zzd);
                zza(this.zzPC.getProductId(), false, zzd, (Intent) null);
                this.mActivity.finish();
                return;
            }
            this.zzPD = new zzf(this.zzPC.getProductId(), this.zzPF);
            this.zzPu.zzb(this.zzPD);
            Activity activity = this.mActivity;
            IntentSender intentSender = pendingIntent.getIntentSender();
            Intent intent = new Intent();
            Integer num = 0;
            int intValue = num.intValue();
            Integer num2 = 0;
            int intValue2 = num2.intValue();
            Integer num3 = 0;
            activity.startIntentSenderForResult(intentSender, 1001, intent, intValue, intValue2, num3.intValue());
        } catch (IntentSender.SendIntentException | RemoteException e) {
            zzpk.zzc("Error when connecting in-app billing service", e);
            this.mActivity.finish();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzpk.zzbg("In-app billing service disconnected.");
        this.zzPt.destroy();
    }

    /* access modifiers changed from: protected */
    public void zza(String str, boolean z, int i, Intent intent) {
        if (this.zzPE != null) {
            this.zzPE.zza(str, z, i, intent, this.zzPD);
        }
    }
}
