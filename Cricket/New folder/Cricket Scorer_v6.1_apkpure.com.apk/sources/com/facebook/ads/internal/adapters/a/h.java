package com.facebook.ads.internal.adapters.a;

import com.facebook.ads.internal.adapters.a.b;
import com.facebook.ads.internal.adapters.a.c;
import java.io.Serializable;
import org.json.JSONObject;

public class h implements Serializable {
    private static final long serialVersionUID = 85021702336014823L;
    private final c a;
    private final e b;
    private final b c;

    private h(c cVar, e eVar, b bVar) {
        this.a = cVar;
        this.b = eVar;
        this.c = bVar;
    }

    static h a(JSONObject jSONObject) {
        c a2 = new c.a().a(jSONObject.optString("title")).b(jSONObject.optString("subtitle")).c(jSONObject.optString("body")).a();
        e eVar = new e(jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"));
        boolean optBoolean = jSONObject.optBoolean("video_autoplay_enabled");
        b.a b2 = new b.a().a(jSONObject.optString("video_url")).a(optBoolean).b(jSONObject.optBoolean("video_autoplay_with_sound"));
        int i = 0;
        if (optBoolean) {
            i = jSONObject.optInt("unskippable_seconds", 0);
        }
        b.a a3 = b2.a(i);
        JSONObject optJSONObject = jSONObject.optJSONObject("image");
        if (optJSONObject != null) {
            a3.b(optJSONObject.optString("url")).c(optJSONObject.optInt("width")).d(optJSONObject.optInt("height"));
        }
        return new h(a2, eVar, a3.a());
    }

    public c a() {
        return this.a;
    }

    public e b() {
        return this.b;
    }

    public b c() {
        return this.c;
    }
}
