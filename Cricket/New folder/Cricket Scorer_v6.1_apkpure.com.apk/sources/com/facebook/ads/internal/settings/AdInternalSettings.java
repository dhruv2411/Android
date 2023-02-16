package com.facebook.ads.internal.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class AdInternalSettings {
    static volatile boolean a = false;
    private static final String b = "AdInternalSettings";
    private static final Collection<String> c = new HashSet();
    private static final Collection<String> d = new HashSet();
    private static boolean e = false;
    private static boolean f = false;
    private static String g = null;
    private static String h = null;
    private static String i = null;
    private static boolean j = false;
    private static boolean k;
    private static boolean l;

    static {
        d.add(CommonUtils.SDK);
        d.add(CommonUtils.GOOGLE_SDK);
        d.add("vbox86p");
        d.add("vbox86tp");
    }

    private static void a(String str) {
        if (!a) {
            a = true;
            String str2 = b;
            Log.d(str2, "Test mode device hash: " + str);
            String str3 = b;
            Log.d(str3, "When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"" + str + "\");");
        }
    }

    public static void addTestDevice(String str) {
        c.add(str);
    }

    public static void addTestDevices(Collection<String> collection) {
        c.addAll(collection);
    }

    public static void clearTestDevices() {
        c.clear();
    }

    public static String getMediationService() {
        return h;
    }

    public static String getUrlPrefix() {
        return g;
    }

    public static boolean isDebugBuild() {
        return j;
    }

    public static boolean isExplicitTestMode() {
        return e;
    }

    public static boolean isTestMode(Context context) {
        if (j || isExplicitTestMode() || d.contains(Build.PRODUCT)) {
            return true;
        }
        if (i == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
            i = sharedPreferences.getString("deviceIdHash", (String) null);
            if (TextUtils.isEmpty(i)) {
                i = UUID.randomUUID().toString();
                sharedPreferences.edit().putString("deviceIdHash", i).apply();
            }
        }
        if (c.contains(i)) {
            return true;
        }
        a(i);
        return false;
    }

    public static boolean isVideoAutoplay() {
        return k;
    }

    public static boolean isVideoAutoplayOnMobile() {
        return l;
    }

    public static boolean isVisibleAnimation() {
        return f;
    }

    public static void setDebugBuild(boolean z) {
        j = z;
    }

    public static void setMediationService(String str) {
        h = str;
    }

    public static void setTestMode(boolean z) {
        e = z;
    }

    public static void setUrlPrefix(String str) {
        g = str;
    }

    public static void setVideoAutoplay(boolean z) {
        k = z;
    }

    public static void setVideoAutoplayOnMobile(boolean z) {
        l = z;
    }

    public static void setVisibleAnimation(boolean z) {
        f = z;
    }
}
