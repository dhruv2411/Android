package com.facebook.ads.internal.adapters;

import android.text.TextUtils;
import java.util.Locale;

public enum f {
    UNKNOWN,
    AN,
    ADMOB,
    INMOBI,
    YAHOO;

    public static f a(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        try {
            return (f) Enum.valueOf(f.class, str.toUpperCase(Locale.getDefault()));
        } catch (Exception unused) {
            return UNKNOWN;
        }
    }
}
