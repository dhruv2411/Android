package com.google.android.gms.internal;

import android.support.v4.util.SimpleArrayMap;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.zzlx;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzmb implements zzlx.zza<zzgx> {
    private final boolean zzRc;

    public zzmb(boolean z) {
        this.zzRc = z;
    }

    private void zza(zzlx zzlx, JSONObject jSONObject, SimpleArrayMap<String, Future<zzgu>> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jSONObject.getString("name"), zzlx.zza(jSONObject, "image_value", this.zzRc));
    }

    private void zza(JSONObject jSONObject, SimpleArrayMap<String, String> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jSONObject.getString("name"), jSONObject.getString("string_value"));
    }

    private <K, V> SimpleArrayMap<K, V> zzc(SimpleArrayMap<K, Future<V>> simpleArrayMap) throws InterruptedException, ExecutionException {
        SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap<>();
        for (int i = 0; i < simpleArrayMap.size(); i++) {
            simpleArrayMap2.put(simpleArrayMap.keyAt(i), simpleArrayMap.valueAt(i).get());
        }
        return simpleArrayMap2;
    }

    /* renamed from: zzd */
    public zzgx zza(zzlx zzlx, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        zzqm<zzgs> zzd = zzlx.zzd(jSONObject);
        zzqm<zzqw> zzc = zzlx.zzc(jSONObject, MimeTypes.BASE_TYPE_VIDEO);
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString(DublinCoreProperties.TYPE);
            if ("string".equals(string)) {
                zza(jSONObject2, (SimpleArrayMap<String, String>) simpleArrayMap2);
            } else if ("image".equals(string)) {
                zza(zzlx, jSONObject2, simpleArrayMap);
            } else {
                String valueOf = String.valueOf(string);
                zzpk.zzbh(valueOf.length() != 0 ? "Unknown custom asset type: ".concat(valueOf) : new String("Unknown custom asset type: "));
            }
        }
        zzqw zzb = zzlx.zzb(zzc);
        return new zzgx(jSONObject.getString("custom_template_id"), zzc(simpleArrayMap), simpleArrayMap2, (zzgs) zzd.get(), zzb != null ? zzb.zzlG() : null, zzb != null ? zzb.getView() : null);
    }
}
