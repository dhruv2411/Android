package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import util.IabHelper;

@zzme
public class zzc extends zzpj implements ServiceConnection {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public zzli zzJv;
    private boolean zzPs;
    private zzb zzPt;
    private zzh zzPu;
    private List<zzf> zzPv;
    /* access modifiers changed from: private */
    public zzk zzPw;
    private final Object zzrJ;

    public zzc(Context context, zzli zzli, zzk zzk) {
        this(context, zzli, zzk, new zzb(context), zzh.zzu(context.getApplicationContext()));
    }

    zzc(Context context, zzli zzli, zzk zzk, zzb zzb, zzh zzh) {
        this.zzrJ = new Object();
        this.zzPs = false;
        this.zzPv = null;
        this.mContext = context;
        this.zzJv = zzli;
        this.zzPw = zzk;
        this.zzPt = zzb;
        this.zzPu = zzh;
        this.zzPv = this.zzPu.zzg(10);
    }

    private void zze(long j) {
        do {
            if (!zzf(j)) {
                zzpk.v("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.zzPs);
    }

    private boolean zzf(long j) {
        long elapsedRealtime = ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.zzrJ.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException unused) {
            zzpk.zzbh("waitWithTimeout_lock interrupted");
            return true;
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzrJ) {
            this.zzPt.zzV(iBinder);
            zziI();
            this.zzPs = true;
            this.zzrJ.notify();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzpk.zzbg("In-app billing service disconnected.");
        this.zzPt.destroy();
    }

    public void onStop() {
        synchronized (this.zzrJ) {
            zza.zzyJ().zza(this.mContext, this);
            this.zzPt.destroy();
        }
    }

    /* access modifiers changed from: protected */
    public void zza(final zzf zzf, String str, String str2) {
        final Intent intent = new Intent();
        zzw.zzda();
        intent.putExtra(IabHelper.RESPONSE_CODE, 0);
        zzw.zzda();
        intent.putExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA, str);
        zzw.zzda();
        intent.putExtra(IabHelper.RESPONSE_INAPP_SIGNATURE, str2);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzli zzc;
                zzg zzg;
                try {
                    if (zzc.this.zzPw.zza(zzf.zzPH, -1, intent)) {
                        zzc = zzc.this.zzJv;
                        zzg = new zzg(zzc.this.mContext, zzf.zzPI, true, -1, intent, zzf);
                    } else {
                        zzc = zzc.this.zzJv;
                        zzg = new zzg(zzc.this.mContext, zzf.zzPI, false, -1, intent, zzf);
                    }
                    zzc.zza(zzg);
                } catch (RemoteException unused) {
                    zzpk.zzbh("Fail to verify and dispatch pending transaction");
                }
            }
        });
    }

    public void zzco() {
        synchronized (this.zzrJ) {
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            zza.zzyJ().zza(this.mContext, intent, (ServiceConnection) this, 1);
            zze(SystemClock.elapsedRealtime());
            zza.zzyJ().zza(this.mContext, this);
            this.zzPt.destroy();
        }
    }

    /* access modifiers changed from: protected */
    public void zziI() {
        if (!this.zzPv.isEmpty()) {
            HashMap hashMap = new HashMap();
            for (zzf next : this.zzPv) {
                hashMap.put(next.zzPI, next);
            }
            String str = null;
            do {
                Bundle zzn = this.zzPt.zzn(this.mContext.getPackageName(), str);
                if (zzn == null || zzw.zzda().zzd(zzn) != 0) {
                    break;
                }
                ArrayList<String> stringArrayList = zzn.getStringArrayList(IabHelper.RESPONSE_INAPP_ITEM_LIST);
                ArrayList<String> stringArrayList2 = zzn.getStringArrayList(IabHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList<String> stringArrayList3 = zzn.getStringArrayList(IabHelper.RESPONSE_INAPP_SIGNATURE_LIST);
                str = zzn.getString(IabHelper.INAPP_CONTINUATION_TOKEN);
                for (int i = 0; i < stringArrayList.size(); i++) {
                    if (hashMap.containsKey(stringArrayList.get(i))) {
                        String str2 = stringArrayList.get(i);
                        String str3 = stringArrayList2.get(i);
                        String str4 = stringArrayList3.get(i);
                        zzf zzf = (zzf) hashMap.get(str2);
                        if (zzf.zzPH.equals(zzw.zzda().zzaE(str3))) {
                            zza(zzf, str3, str4);
                            hashMap.remove(str2);
                        }
                    }
                }
                if (str == null) {
                    break;
                }
            } while (hashMap.isEmpty());
            for (String str5 : hashMap.keySet()) {
                this.zzPu.zza((zzf) hashMap.get(str5));
            }
        }
    }
}
