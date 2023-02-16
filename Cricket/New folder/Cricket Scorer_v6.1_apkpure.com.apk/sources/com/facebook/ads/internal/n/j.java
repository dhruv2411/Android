package com.facebook.ads.internal.n;

import android.support.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.json.JSONObject;

public class j {
    private final double a;
    private final double b;

    public j(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    @Nullable
    public static j a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        double optDouble = jSONObject.optDouble(FirebaseAnalytics.Param.VALUE, 0.0d);
        double optDouble2 = jSONObject.optDouble("scale", 0.0d);
        if (optDouble == 0.0d || optDouble2 == 0.0d) {
            return null;
        }
        return new j(optDouble, optDouble2);
    }

    public double a() {
        return this.a;
    }

    public double b() {
        return this.b;
    }
}
