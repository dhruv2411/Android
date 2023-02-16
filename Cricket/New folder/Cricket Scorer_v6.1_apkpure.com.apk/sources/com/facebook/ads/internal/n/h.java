package com.facebook.ads.internal.n;

import android.support.annotation.Nullable;
import org.json.JSONObject;

public class h {
    private final String a;
    private final int b;
    private final int c;

    public h(String str, int i, int i2) {
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    @Nullable
    public static h a(JSONObject jSONObject) {
        String optString;
        if (jSONObject == null || (optString = jSONObject.optString("url")) == null) {
            return null;
        }
        return new h(optString, jSONObject.optInt("width", 0), jSONObject.optInt("height", 0));
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }
}
