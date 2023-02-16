package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.zzlx;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzlz implements zzlx.zza<zzgv> {
    private final boolean zzRc;
    private final boolean zzRd;

    public zzlz(boolean z, boolean z2) {
        this.zzRc = z;
        this.zzRd = z2;
    }

    /* renamed from: zzb */
    public zzgv zza(zzlx zzlx, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        zzlx zzlx2 = zzlx;
        JSONObject jSONObject2 = jSONObject;
        List<zzqm<zzgu>> zza = zzlx2.zza(jSONObject2, "images", true, this.zzRc, this.zzRd);
        zzqm<zzgu> zza2 = zzlx2.zza(jSONObject2, "app_icon", true, this.zzRc);
        zzqm<zzqw> zzc = zzlx2.zzc(jSONObject2, MimeTypes.BASE_TYPE_VIDEO);
        zzqm<zzgs> zzd = zzlx.zzd(jSONObject);
        ArrayList arrayList = new ArrayList();
        for (zzqm<zzgu> zzqm : zza) {
            arrayList.add((zzgu) zzqm.get());
        }
        zzqw zzb = zzlx.zzb(zzc);
        return new zzgv(jSONObject2.getString("headline"), arrayList, jSONObject2.getString("body"), (zzhf) zza2.get(), jSONObject2.getString("call_to_action"), jSONObject2.optDouble("rating", -1.0d), jSONObject2.optString("store"), jSONObject2.optString(FirebaseAnalytics.Param.PRICE), (zzgs) zzd.get(), new Bundle(), zzb != null ? zzb.zzlG() : null, zzb != null ? zzb.getView() : null);
    }
}
