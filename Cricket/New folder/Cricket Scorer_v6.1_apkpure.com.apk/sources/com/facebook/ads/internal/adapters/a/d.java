package com.facebook.ads.internal.adapters.a;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONObject;

public class d implements Serializable {
    public static final int a = Color.parseColor("#90949c");
    public static final int b = Color.parseColor("#4b4f56");
    public static final int c = Color.parseColor("#f6f7f9");
    public static final int d = Color.parseColor("#ff4080ff");
    public static final int e = Color.parseColor("#23272F");
    public static final int f = Color.parseColor("#ff4080ff");
    private static final long serialVersionUID = 8946536326456653736L;
    private int g = a;
    private int h = b;
    private int i = ViewCompat.MEASURED_STATE_MASK;
    private int j = c;
    private int k = d;
    private int l = -1;
    private int m = ViewCompat.MEASURED_STATE_MASK;

    public static d a(JSONObject jSONObject) {
        d dVar = new d();
        if (jSONObject != null) {
            String optString = jSONObject.optString("accent_color");
            String optString2 = jSONObject.optString("body_color");
            String optString3 = jSONObject.optString("subtitle_color");
            String optString4 = jSONObject.optString("bg_color");
            String optString5 = jSONObject.optString("cta_color");
            String optString6 = jSONObject.optString("cta_text_color");
            String optString7 = jSONObject.optString("title_color");
            if (!TextUtils.isEmpty(optString)) {
                dVar.g = Color.parseColor(optString);
            }
            if (!TextUtils.isEmpty(optString2)) {
                dVar.h = Color.parseColor(optString2);
            }
            if (!TextUtils.isEmpty(optString3)) {
                dVar.i = Color.parseColor(optString3);
            }
            if (!TextUtils.isEmpty(optString4)) {
                dVar.j = Color.parseColor(optString4);
            }
            if (!TextUtils.isEmpty(optString5)) {
                dVar.k = Color.parseColor(optString5);
            }
            if (!TextUtils.isEmpty(optString6)) {
                dVar.l = Color.parseColor(optString6);
            }
            if (!TextUtils.isEmpty(optString7)) {
                dVar.m = Color.parseColor(optString7);
            }
        }
        return dVar;
    }

    public int a(boolean z) {
        if (z) {
            return -1;
        }
        return this.g;
    }

    public int b(boolean z) {
        if (z) {
            return -1;
        }
        return this.h;
    }

    public int c(boolean z) {
        if (z) {
            return -1;
        }
        return this.i;
    }

    public int d(boolean z) {
        return z ? e : this.j;
    }

    public int e(boolean z) {
        if (z) {
            return -1;
        }
        return this.k;
    }

    public int f(boolean z) {
        return z ? f : this.l;
    }

    public int g(boolean z) {
        if (z) {
            return -1;
        }
        return this.m;
    }
}
