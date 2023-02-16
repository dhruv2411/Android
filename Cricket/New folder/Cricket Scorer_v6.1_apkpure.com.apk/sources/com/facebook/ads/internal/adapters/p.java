package com.facebook.ads.internal.adapters;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.j.c;
import com.facebook.ads.internal.q.a.k;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class p implements e.a {
    private final String a;
    private final String b;
    private final d c;
    private final Collection<String> d;
    private final Map<String, String> e;
    private final String f;
    private final int g;
    private final int h;
    private final int i;
    private final String j;

    private p(String str, String str2, d dVar, Collection<String> collection, Map<String, String> map, String str3, int i2, int i3, int i4, String str4) {
        this.a = str;
        this.b = str2;
        this.c = dVar;
        this.d = collection;
        this.e = map;
        this.f = str3;
        this.g = i2;
        this.h = i3;
        this.i = i4;
        this.j = str4;
    }

    public static p a(Bundle bundle) {
        return new p(c.a(bundle.getByteArray("markup")), (String) null, d.NONE, (Collection<String>) null, (Map<String, String>) null, bundle.getString("request_id"), bundle.getInt("viewability_check_initial_delay"), bundle.getInt("viewability_check_interval"), bundle.getInt("skip_after_seconds", 0), bundle.getString("ct"));
    }

    public static p a(JSONObject jSONObject) {
        JSONArray jSONArray = null;
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("markup");
        String optString2 = jSONObject.optString("activation_command");
        String optString3 = jSONObject.optString("request_id");
        String a2 = k.a(jSONObject, "ct");
        d a3 = d.a(jSONObject.optString("invalidation_behavior"));
        try {
            jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        Collection<String> a4 = e.a(jSONArray);
        JSONObject optJSONObject = jSONObject.optJSONObject(TtmlNode.TAG_METADATA);
        HashMap hashMap = new HashMap();
        if (optJSONObject != null) {
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, optJSONObject.optString(next));
            }
        }
        int i2 = 1000;
        int parseInt = hashMap.containsKey("viewability_check_initial_delay") ? Integer.parseInt((String) hashMap.get("viewability_check_initial_delay")) : 0;
        if (hashMap.containsKey("viewability_check_interval")) {
            i2 = Integer.parseInt((String) hashMap.get("viewability_check_interval"));
        }
        return new p(optString, optString2, a3, a4, hashMap, optString3, parseInt, i2, hashMap.containsKey("skip_after_seconds") ? Integer.parseInt((String) hashMap.get("skip_after_seconds")) : 0, a2);
    }

    public static p b(Intent intent) {
        return new p(c.a(intent.getByteArrayExtra("markup")), intent.getStringExtra("activation_command"), d.NONE, (Collection<String>) null, (Map<String, String>) null, intent.getStringExtra("request_id"), intent.getIntExtra("viewability_check_initial_delay", 0), intent.getIntExtra("viewability_check_interval", 1000), intent.getIntExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, 0), intent.getStringExtra("ct"));
    }

    public d a() {
        return this.c;
    }

    public void a(Intent intent) {
        intent.putExtra("markup", c.a(this.a));
        intent.putExtra("activation_command", this.b);
        intent.putExtra("request_id", this.f);
        intent.putExtra("viewability_check_initial_delay", this.g);
        intent.putExtra("viewability_check_interval", this.h);
        intent.putExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, this.i);
        intent.putExtra("ct", this.j);
    }

    public Collection<String> b() {
        return this.d;
    }

    public String c() {
        return this.j;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public Map<String, String> f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public int h() {
        return this.g;
    }

    public int i() {
        return this.h;
    }

    public Bundle j() {
        Bundle bundle = new Bundle();
        bundle.putByteArray("markup", c.a(this.a));
        bundle.putString("request_id", this.f);
        bundle.putInt("viewability_check_initial_delay", this.g);
        bundle.putInt("viewability_check_interval", this.h);
        bundle.putInt("skip_after_seconds", this.i);
        bundle.putString("ct", this.j);
        return bundle;
    }
}
