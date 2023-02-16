package com.facebook.ads.internal.adapters.a;

import android.content.Context;
import com.facebook.ads.internal.adapters.a.i;
import com.facebook.ads.internal.q.d.a;
import com.facebook.ads.internal.q.d.b;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g implements Serializable {
    private static final long serialVersionUID = 8751287062553772011L;
    private final i a;
    private final a b;
    private final List<h> c;
    private final int d;
    private final int e;
    private int f = 200;
    private final String g;
    private final String h;

    private g(i iVar, a aVar, List<h> list, String str, String str2, int i, int i2) {
        this.a = iVar;
        this.b = aVar;
        this.c = list;
        this.g = str;
        this.h = str2;
        this.d = i;
        this.e = i2;
    }

    public static g a(JSONObject jSONObject, Context context) {
        i a2 = new i.a().a(jSONObject.optString("title")).b(jSONObject.optJSONObject(SettingsJsonConstants.APP_ICON_KEY) != null ? jSONObject.optJSONObject(SettingsJsonConstants.APP_ICON_KEY).optString("url") : "").c(jSONObject.optString("ad_choices_link_url")).d(a(jSONObject)).a();
        JSONObject optJSONObject = jSONObject.optJSONObject(TtmlNode.TAG_LAYOUT);
        JSONObject jSONObject2 = null;
        d a3 = d.a(optJSONObject != null ? optJSONObject.optJSONObject("portrait") : null);
        if (optJSONObject != null) {
            jSONObject2 = optJSONObject.optJSONObject("landscape");
        }
        a aVar = new a(a3, d.a(jSONObject2));
        int optInt = jSONObject.optInt("viewability_check_initial_delay", 0);
        int optInt2 = jSONObject.optInt("viewability_check_interval", 1000);
        String optString = jSONObject.optString("ct");
        String optString2 = jSONObject.optString("request_id", "");
        JSONArray optJSONArray = jSONObject.optJSONArray("carousel");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            arrayList.add(h.a(jSONObject));
        } else {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    arrayList.add(h.a(optJSONArray.getJSONObject(i)));
                } catch (JSONException e2) {
                    a.a(context, "parsing", b.u, (Exception) e2);
                    e2.printStackTrace();
                }
            }
        }
        return new g(a2, aVar, arrayList, optString, optString2, optInt, optInt2);
    }

    private static String a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        return optJSONObject == null ? "Sponsored" : optJSONObject.optString("sponsored", "Sponsored");
    }

    public i a() {
        return this.a;
    }

    public void a(int i) {
        this.f = i;
    }

    public a b() {
        return this.b;
    }

    public String c() {
        return this.g;
    }

    public List<h> d() {
        return Collections.unmodifiableList(this.c);
    }

    public String e() {
        return this.h;
    }

    public int f() {
        return this.d;
    }

    public int g() {
        return this.e;
    }

    public int h() {
        return this.f;
    }
}
