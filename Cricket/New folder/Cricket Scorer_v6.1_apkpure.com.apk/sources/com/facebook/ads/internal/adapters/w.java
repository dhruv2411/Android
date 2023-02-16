package com.facebook.ads.internal.adapters;

public class w {
    private static final String[] a = {"com.flurry.android.FlurryAgent", "com.flurry.android.ads.FlurryAdErrorType", "com.flurry.android.ads.FlurryAdNative", "com.flurry.android.ads.FlurryAdNativeAsset", "com.flurry.android.ads.FlurryAdNativeListener"};
    private static final String[] b = {"com.inmobi.ads.InMobiNative", "com.inmobi.sdk.InMobiSdk"};
    private static final String[] c = {"com.google.android.gms.ads.formats.NativeAdView"};

    public static boolean a(f fVar) {
        String[] strArr;
        switch (fVar) {
            case AN:
                return true;
            case YAHOO:
                strArr = a;
                break;
            case INMOBI:
                strArr = b;
                break;
            case ADMOB:
                strArr = c;
                break;
            default:
                return false;
        }
        return a(strArr);
    }

    private static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean a(String[] strArr) {
        if (strArr == null) {
            return false;
        }
        for (String a2 : strArr) {
            if (!a(a2)) {
                return false;
            }
        }
        return true;
    }
}
