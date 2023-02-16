package com.google.android.gms.internal;

import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;
import java.util.List;
import org.json.JSONObject;

@zzme
public class zzls extends zzpj {
    /* access modifiers changed from: private */
    public final zzlq.zza zzPQ;
    private final zzpb.zza zzPR;
    private final zzmn zzPS = this.zzPR.zzWm;

    public zzls(zzpb.zza zza, zzlq.zza zza2) {
        this.zzPR = zza;
        this.zzPQ = zza2;
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
        return new zzpb(zzec, (zzqw) null, (List<String>) null, i, (List<String>) null, (List<String>) null, i2, j, str, false, (zzjq) null, (zzkb) null, (String) null, (zzjr) null, (zzjt) null, j5, zzeg, j3, j4, j6, str2, jSONObject, (zzha.zza) null, (zzoo) null, (List<String>) null, (List<String>) null, this.zzPR.zzWm.zzSF, this.zzPR.zzWm.zzSG, (String) null, (List<String>) null, (String) null);
    }

    public void onStop() {
    }

    public void zzco() {
        final zzpb zzS = zzS(0);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzls.this.zzPQ.zzb(zzS);
            }
        });
    }
}
