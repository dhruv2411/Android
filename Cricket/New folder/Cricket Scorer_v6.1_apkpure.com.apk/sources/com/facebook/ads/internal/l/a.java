package com.facebook.ads.internal.l;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.Nullable;
import com.itextpdf.xmp.XMPConst;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static a a;
    private final SharedPreferences b;

    private a(Context context) {
        this.b = context.getApplicationContext().getSharedPreferences("com.facebook.ads.FEATURE_CONFIG", 0);
    }

    private static int a(Context context, String str, int i) {
        int a2 = w(context).a(str, i);
        return (a2 < 0 || a2 >= 101) ? i : a2;
    }

    public static boolean a(Context context) {
        return Build.VERSION.SDK_INT >= 14 && b("com.google.android.exoplayer2", "ExoPlayer") && w(context).a("adnw_enable_exoplayer", false);
    }

    public static boolean b(Context context) {
        return Build.VERSION.SDK_INT >= 18 && w(context).a("adnw_enable_debug_overlay", false);
    }

    private static boolean b(String str, String str2) {
        try {
            Class.forName(str + "." + str2);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean c(Context context) {
        return w(context).a("adnw_block_lockscreen", false);
    }

    public static boolean d(Context context) {
        return w(context).a("adnw_android_memory_opt", false);
    }

    public static boolean e(Context context) {
        return w(context).a("adnw_android_disable_blur", false);
    }

    public static boolean f(Context context) {
        return w(context).a("adnw_android_disable_playable_precache", false);
    }

    public static boolean g(Context context) {
        return Build.VERSION.SDK_INT >= 19 && w(context).a("adnw_enable_iab", false);
    }

    public static boolean h(Context context) {
        return w(context).a("adnw_debug_logging", false);
    }

    public static Set<String> i(Context context) {
        String a2 = w(context).a("additional_debug_logging_black_list", "");
        HashSet hashSet = new HashSet();
        try {
            JSONArray jSONArray = new JSONArray(a2);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
        } catch (JSONException unused) {
        }
        return hashSet;
    }

    public static int j(Context context) {
        return a(context, "additional_debug_logging_black_list_percentage", 0);
    }

    public static int k(Context context) {
        return a(context, "additional_debug_logging_sampling_percentage", 100);
    }

    public static long l(Context context) {
        return w(context).a("unified_logging_immediate_delay_ms", 500);
    }

    public static long m(Context context) {
        return ((long) w(context).a("unified_logging_dispatch_interval_seconds", 300)) * 1000;
    }

    public static int n(Context context) {
        return w(context).a("unified_logging_event_limit", -1);
    }

    public static boolean o(Context context) {
        return w(context).a("video_and_endcard_autorotate", "autorotate_disabled").equals("autorotate_enabled");
    }

    public static int p(Context context) {
        return w(context).a("minimum_elapsed_time_after_impression", -1);
    }

    public static int q(Context context) {
        return w(context).a("stack_trace_sample_rate", 0);
    }

    public static boolean r(Context context) {
        return w(context).a("adnw_top_activity_viewability", false);
    }

    public static boolean s(Context context) {
        return w(context).a("adnw_enhanced_viewability_area_check", false);
    }

    public static boolean t(Context context) {
        return w(context).a("adnw_viewability_check_area_based", false);
    }

    @Nullable
    public static String u(Context context) {
        return w(context).a("adnw_logging_endpoint_prefix", "www");
    }

    public static boolean v(Context context) {
        return w(context).a("adnw_mapp_markup_impression_after_image_load", false);
    }

    public static a w(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    public int a(String str, int i) {
        String string = this.b.getString(str, String.valueOf(i));
        try {
            return string.equals("null") ? i : Integer.valueOf(string).intValue();
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public long a(String str, long j) {
        String string = this.b.getString(str, String.valueOf(j));
        try {
            return string.equals("null") ? j : Long.valueOf(string).longValue();
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    @Nullable
    public String a(String str, String str2) {
        String string = this.b.getString(str, str2);
        return (string == null || string.equals("null")) ? str2 : string;
    }

    public void a(@Nullable String str) {
        if (str != null && !str.isEmpty() && !str.equals(XMPConst.ARRAY_ITEM_NAME)) {
            SharedPreferences.Editor edit = this.b.edit();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                edit.putString(next, jSONObject.getString(next));
            }
            edit.apply();
        }
    }

    public boolean a(String str, boolean z) {
        String string = this.b.getString(str, String.valueOf(z));
        return string.equals("null") ? z : Boolean.valueOf(string).booleanValue();
    }
}
