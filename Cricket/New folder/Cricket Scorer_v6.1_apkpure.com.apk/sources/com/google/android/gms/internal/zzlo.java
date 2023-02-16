package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzqx;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

@zzme
public abstract class zzlo implements zzpq<Void>, zzqx.zza {
    protected final Context mContext;
    protected final zzqw zzIs;
    protected final zzlq.zza zzPQ;
    protected final zzpb.zza zzPR;
    protected zzmn zzPS;
    private Runnable zzPT;
    protected final Object zzPU = new Object();
    /* access modifiers changed from: private */
    public AtomicBoolean zzPV = new AtomicBoolean(true);

    protected zzlo(Context context, zzpb.zza zza, zzqw zzqw, zzlq.zza zza2) {
        this.mContext = context;
        this.zzPR = zza;
        this.zzPS = this.zzPR.zzWm;
        this.zzIs = zzqw;
        this.zzPQ = zza2;
    }

    private zzpb zzR(int i) {
        zzmk zzmk = this.zzPR.zzTi;
        zzec zzec = zzmk.zzRy;
        zzqw zzqw = this.zzIs;
        List<String> list = this.zzPS.zzKF;
        List<String> list2 = this.zzPS.zzKG;
        List<String> list3 = this.zzPS.zzSp;
        int i2 = this.zzPS.orientation;
        long j = this.zzPS.zzKL;
        String str = zzmk.zzRB;
        boolean z = this.zzPS.zzSn;
        long j2 = this.zzPS.zzSo;
        zzeg zzeg = this.zzPR.zzvr;
        long j3 = j2;
        long j4 = this.zzPS.zzSm;
        long j5 = this.zzPR.zzWg;
        long j6 = this.zzPS.zzSr;
        String str2 = this.zzPS.zzSs;
        JSONObject jSONObject = this.zzPR.zzWa;
        zzoo zzoo = this.zzPS.zzSC;
        List<String> list4 = this.zzPS.zzSD;
        List<String> list5 = this.zzPS.zzSE;
        boolean z2 = this.zzPS.zzSF;
        zzmp zzmp = this.zzPS.zzSG;
        String str3 = str2;
        return new zzpb(zzec, zzqw, list, i, list2, list3, i2, j, str, z, (zzjq) null, (zzkb) null, (String) null, (zzjr) null, (zzjt) null, j3, zzeg, j4, j5, j6, str3, jSONObject, (zzha.zza) null, zzoo, list4, list5, z2, zzmp, (String) null, this.zzPS.zzKI, this.zzPS.zzSJ);
    }

    public void cancel() {
        if (this.zzPV.getAndSet(false)) {
            this.zzIs.stopLoading();
            zzw.zzcO().zzl(this.zzIs);
            zzQ(-1);
            zzpo.zzXC.removeCallbacks(this.zzPT);
        }
    }

    /* access modifiers changed from: protected */
    public void zzQ(int i) {
        if (i != -2) {
            this.zzPS = new zzmn(i, this.zzPS.zzKL);
        }
        this.zzIs.zzlq();
        this.zzPQ.zzb(zzR(i));
    }

    public void zza(zzqw zzqw, boolean z) {
        zzpk.zzbf("WebView finished loading.");
        int i = 0;
        if (this.zzPV.getAndSet(false)) {
            if (z) {
                i = -2;
            }
            zzQ(i);
            zzpo.zzXC.removeCallbacks(this.zzPT);
        }
    }

    /* renamed from: zziN */
    public final Void zziP() {
        zzac.zzdj("Webview render task needs to be called on UI thread.");
        this.zzPT = new Runnable() {
            public void run() {
                if (zzlo.this.zzPV.get()) {
                    zzpk.e("Timed out waiting for WebView to finish loading.");
                    zzlo.this.cancel();
                }
            }
        };
        zzpo.zzXC.postDelayed(this.zzPT, zzgd.zzDM.get().longValue());
        zziO();
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void zziO();
}
