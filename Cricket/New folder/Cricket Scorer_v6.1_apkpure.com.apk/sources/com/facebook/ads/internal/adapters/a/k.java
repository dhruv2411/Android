package com.facebook.ads.internal.adapters.a;

import android.text.TextUtils;
import com.facebook.ads.internal.adapters.a.b;
import com.facebook.ads.internal.adapters.a.c;
import com.facebook.ads.internal.adapters.a.i;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class k implements Serializable {
    private static final long serialVersionUID = -5352540727250859603L;
    private final i a;
    private final c b;
    private final e c;
    private final a d;
    private final b e;
    private final f f;
    private final String g;
    private int h = 200;

    private k(i iVar, c cVar, e eVar, a aVar, b bVar, f fVar, String str) {
        this.a = iVar;
        this.b = cVar;
        this.c = eVar;
        this.d = aVar;
        this.e = bVar;
        this.f = fVar;
        this.g = str;
    }

    public static k a(JSONObject jSONObject) {
        i a2 = new i.a().a(jSONObject.optString("advertiser_name")).b(jSONObject.optJSONObject(SettingsJsonConstants.APP_ICON_KEY) != null ? jSONObject.optJSONObject(SettingsJsonConstants.APP_ICON_KEY).optString("url") : "").c(jSONObject.optString("ad_choices_link_url")).d(b(jSONObject)).a();
        c a3 = new c.a().a(jSONObject.optString("title")).b(jSONObject.optString("subtitle")).c(jSONObject.optString("body")).a();
        e eVar = new e(jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"));
        JSONObject optJSONObject = jSONObject.optJSONObject(TtmlNode.TAG_LAYOUT);
        j jVar = null;
        a aVar = new a(d.a(optJSONObject != null ? optJSONObject.optJSONObject("portrait") : null), d.a(optJSONObject != null ? optJSONObject.optJSONObject("landscape") : null));
        JSONObject optJSONObject2 = jSONObject.optJSONObject("playable_data");
        if (optJSONObject2 != null) {
            jVar = new j(optJSONObject2.optString("uri"), jSONObject.optInt("skippable_seconds", 0), c(optJSONObject2));
        }
        return new k(a2, a3, eVar, aVar, new b.a().a(jSONObject.optString("video_url")).b(jSONObject.optJSONObject("image") != null ? jSONObject.optJSONObject("image").optString("url") : "").a(jSONObject.optInt("skippable_seconds")).b(jSONObject.optInt("video_duration_sec")).a(jVar).a(), new f(com.facebook.ads.internal.j.c.a(jSONObject.optString("end_card_markup")), jSONObject.optString("activation_command"), a(jSONObject.optJSONArray("end_card_images"))), jSONObject.optString("ct"));
    }

    private static List<String> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            String optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    private static String b(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        return optJSONObject == null ? "Sponsored" : optJSONObject.optString("sponsored", "Sponsored");
    }

    private static String c(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        return optJSONObject == null ? "Rewarded Play" : optJSONObject.optString("rewarded_play_text", "Rewarded Play");
    }

    public i a() {
        return this.a;
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(String str) {
        this.f.a(str);
    }

    public c b() {
        return this.b;
    }

    public void b(String str) {
        this.e.a(str);
    }

    public e c() {
        return this.c;
    }

    public a d() {
        return this.d;
    }

    public b e() {
        return this.e;
    }

    public f f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }
}
