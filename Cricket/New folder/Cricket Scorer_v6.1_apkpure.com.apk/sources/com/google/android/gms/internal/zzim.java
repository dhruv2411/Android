package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzim implements zzid {
    private final Map<String, zza> zzIt = new HashMap();
    private final Object zzrJ = new Object();

    public interface zza {
    }

    public void zza(zzqw zzqw, Map<String, String> map) {
        String str = map.get(TtmlNode.ATTR_ID);
        String str2 = map.get("fail");
        map.get("fail_reason");
        String str3 = map.get("result");
        synchronized (this.zzrJ) {
            if (this.zzIt.remove(str) == null) {
                String valueOf = String.valueOf(str);
                zzpk.zzbh(valueOf.length() != 0 ? "Received result for unexpected method invocation: ".concat(valueOf) : new String("Received result for unexpected method invocation: "));
            } else if (TextUtils.isEmpty(str2)) {
                if (str3 != null) {
                    try {
                        new JSONObject(str3);
                    } catch (JSONException e) {
                        e.getMessage();
                    }
                }
            }
        }
    }
}
