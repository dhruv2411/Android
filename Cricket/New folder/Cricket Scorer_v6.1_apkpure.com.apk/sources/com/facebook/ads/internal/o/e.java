package com.facebook.ads.internal.o;

import android.text.TextUtils;
import com.facebook.ads.internal.h.a;
import com.facebook.ads.internal.h.c;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.o.f;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    private static e a = new e();

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            eVar = a;
        }
        return eVar;
    }

    private g a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
        c cVar = new c(d.a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config"));
        if (jSONObject2.has("ads")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("ads");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                cVar.a(new a(jSONObject3.optString("adapter"), jSONObject3.optJSONObject("data"), jSONObject3.optJSONArray("trackers")));
            }
        }
        return new g(cVar, jSONObject.optString("server_request_id"), jSONObject.optString("server_response"), jSONObject.optString("an_validation_uuid"));
    }

    private h b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
            return new h(jSONObject.optString(SettingsJsonConstants.PROMPT_MESSAGE_KEY, ""), jSONObject.optInt("code", 0), new c(d.a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config")));
        } catch (JSONException unused) {
            return c(jSONObject);
        }
    }

    private h c(JSONObject jSONObject) {
        return new h(jSONObject.optString(SettingsJsonConstants.PROMPT_MESSAGE_KEY, ""), jSONObject.optInt("code", 0), (c) null);
    }

    public f a(String str) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(DublinCoreProperties.TYPE);
            char c = 65535;
            int hashCode = optString.hashCode();
            if (hashCode != 96432) {
                if (hashCode == 96784904 && optString.equals("error")) {
                    c = 1;
                }
            } else if (optString.equals("ads")) {
                c = 0;
            }
            switch (c) {
                case 0:
                    return a(jSONObject);
                case 1:
                    return b(jSONObject);
                default:
                    JSONObject optJSONObject = jSONObject.optJSONObject("error");
                    if (optJSONObject != null) {
                        return c(optJSONObject);
                    }
                    break;
            }
        }
        return new f(f.a.UNKNOWN);
    }
}
