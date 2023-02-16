package com.facebook.ads.internal.q.a;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

public class h {
    public static boolean a(int i, int i2) {
        return i >= 640 && i2 >= 640;
    }

    public static boolean a(Context context) {
        boolean z;
        try {
            if (!((ActivityManager) context.getSystemService("activity")).getRunningTasks(2).get(0).topActivity.getClassName().equals("com.unity3d.player.UnityPlayerActivity")) {
                if (!a("com.unity3d.player.UnityPlayerActivity")) {
                    z = false;
                    Boolean valueOf = Boolean.valueOf(z);
                    Log.d("IS_UNITY", Boolean.toString(valueOf.booleanValue()));
                    return valueOf.booleanValue();
                }
            }
            z = true;
            Boolean valueOf2 = Boolean.valueOf(z);
            Log.d("IS_UNITY", Boolean.toString(valueOf2.booleanValue()));
            return valueOf2.booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
