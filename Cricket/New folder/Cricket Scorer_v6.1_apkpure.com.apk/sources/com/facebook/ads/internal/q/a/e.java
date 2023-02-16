package com.facebook.ads.internal.q.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import java.io.IOException;
import java.util.concurrent.Executors;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class e {
    public static volatile a a = a.NOT_INITIALIZED;
    /* access modifiers changed from: private */
    public static int b = -1;

    enum a {
        NOT_INITIALIZED,
        INITIALIZING,
        INITIALIZED
    }

    @VisibleForTesting(otherwise = 2)
    public static int a(XmlPullParser xmlPullParser) {
        while (true) {
            if (xmlPullParser.next() == 1) {
                return 0;
            }
            if (xmlPullParser.getEventType() == 2 && xmlPullParser.getName().equals("uses-sdk")) {
                for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                    if (xmlPullParser.getAttributeName(i).equals("minSdkVersion")) {
                        return Integer.parseInt(xmlPullParser.getAttributeValue(i));
                    }
                }
                continue;
            }
        }
    }

    public static void a(Context context) {
        if (!a()) {
            e(context);
        }
    }

    public static boolean a() {
        return a == a.INITIALIZED;
    }

    public static int b(Context context) {
        if (a == a.NOT_INITIALIZED) {
            a(context);
        }
        return b;
    }

    public static int c(Context context) {
        try {
            return a((XmlPullParser) context.getAssets().openXmlResourceParser("AndroidManifest.xml"));
        } catch (IOException | XmlPullParserException unused) {
            return 0;
        }
    }

    private static void e(final Context context) {
        if (a == a.NOT_INITIALIZED) {
            a = a.INITIALIZING;
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                public void run() {
                    if (e.a != a.INITIALIZED) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
                        int i = sharedPreferences.getInt("AppMinSdkVersion", -1);
                        if (i != -1) {
                            int unused = e.b = i;
                        } else {
                            int d = Build.VERSION.SDK_INT >= 24 ? e.f(context) : e.c(context);
                            int unused2 = e.b = d;
                            sharedPreferences.edit().putInt("AppMinSdkVersion", d).commit();
                        }
                        e.a = a.INITIALIZED;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static int f(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).minSdkVersion;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }
}
