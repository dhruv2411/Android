package com.facebook.ads.internal.c;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.ads.internal.c.a;
import com.facebook.ads.internal.c.c;

public class b {
    public static String a = "";
    public static String b = "";
    public static boolean c = false;
    public static String d = "";

    public static void a(Context context) {
        c.a aVar;
        a aVar2;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SDKIDFA", 0);
            if (sharedPreferences.contains("attributionId")) {
                a = sharedPreferences.getString("attributionId", "");
            }
            if (sharedPreferences.contains("advertisingId")) {
                b = sharedPreferences.getString("advertisingId", "");
                c = sharedPreferences.getBoolean("limitAdTracking", c);
                d = a.c.SHARED_PREFS.name();
            }
            try {
                aVar = c.a(context.getContentResolver());
            } catch (Exception e) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(e, "Error retrieving attribution id from fb4a"));
                aVar = null;
            }
            if (!(aVar == null || aVar.a == null)) {
                a = aVar.a;
            }
            if (com.facebook.ads.internal.q.a.b.a() && com.facebook.ads.internal.q.a.b.b("aid_override")) {
                a = com.facebook.ads.internal.q.a.b.a("aid_override");
            }
            try {
                aVar2 = a.a(context, aVar);
            } catch (Exception e2) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(e2, "Error retrieving advertising id from Google Play Services"));
                aVar2 = null;
            }
            if (aVar2 != null) {
                String a2 = aVar2.a();
                Boolean valueOf = Boolean.valueOf(aVar2.b());
                if (a2 != null) {
                    b = a2;
                    c = valueOf.booleanValue();
                    d = aVar2.c().name();
                }
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("attributionId", a);
            edit.putString("advertisingId", b);
            edit.putBoolean("limitAdTracking", c);
            edit.apply();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
