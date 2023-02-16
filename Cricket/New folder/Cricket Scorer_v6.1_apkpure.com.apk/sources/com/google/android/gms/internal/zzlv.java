package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

@zzme
public class zzlv extends zzpj {
    /* access modifiers changed from: private */
    public final zzlq.zza zzPQ;
    private final zzpb.zza zzPR;
    private final zzmn zzPS;
    private final zzlx zzQi;
    private Future<zzpb> zzQj;
    private final Object zzrJ;

    public zzlv(Context context, zzs zzs, zzpb.zza zza, zzaw zzaw, zzlq.zza zza2, zzgl zzgl) {
        this(zza, zza2, new zzlx(context, zzs, new zzpv(context), zzaw, zza, zzgl));
    }

    zzlv(zzpb.zza zza, zzlq.zza zza2, zzlx zzlx) {
        this.zzrJ = new Object();
        this.zzPR = zza;
        this.zzPS = zza.zzWm;
        this.zzPQ = zza2;
        this.zzQi = zzlx;
    }

    private zzpb zzS(int i) {
        zzec zzec = this.zzPR.zzTi.zzRy;
        int i2 = this.zzPS.orientation;
        long j = this.zzPS.zzKL;
        String str = this.zzPR.zzTi.zzRB;
        long j2 = this.zzPS.zzSo;
        zzeg zzeg = this.zzPR.zzvr;
        long j3 = this.zzPS.zzSm;
        long j4 = this.zzPR.zzWg;
        long j5 = j2;
        long j6 = this.zzPS.zzSr;
        String str2 = this.zzPS.zzSs;
        JSONObject jSONObject = this.zzPR.zzWa;
        boolean z = this.zzPR.zzWm.zzSF;
        return new zzpb(zzec, (zzqw) null, (List<String>) null, i, (List<String>) null, (List<String>) null, i2, j, str, false, (zzjq) null, (zzkb) null, (String) null, (zzjr) null, (zzjt) null, j5, zzeg, j3, j4, j6, str2, jSONObject, (zzha.zza) null, (zzoo) null, (List<String>) null, (List<String>) null, z, this.zzPR.zzWm.zzSG, (String) null, (List<String>) null, this.zzPS.zzSJ);
    }

    public void onStop() {
        synchronized (this.zzrJ) {
            if (this.zzQj != null) {
                this.zzQj.cancel(true);
            }
        }
    }

    public void zzco() {
        int i = 0;
        final zzpb zzpb = null;
        try {
            synchronized (this.zzrJ) {
                this.zzQj = zzpn.zza(this.zzQi);
            }
            i = -2;
            zzpb = this.zzQj.get(ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS, TimeUnit.MILLISECONDS);
        } catch (TimeoutException unused) {
            zzpk.zzbh("Timed out waiting for native ad.");
            i = 2;
            this.zzQj.cancel(true);
        } catch (InterruptedException | CancellationException | ExecutionException unused2) {
        }
        if (zzpb == null) {
            zzpb = zzS(i);
        }
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzlv.this.zzPQ.zzb(zzpb);
            }
        });
    }
}
