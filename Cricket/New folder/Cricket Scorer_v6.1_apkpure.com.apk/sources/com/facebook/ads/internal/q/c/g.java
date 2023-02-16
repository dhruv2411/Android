package com.facebook.ads.internal.q.c;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.settings.a;

public class g {
    private Intent a(Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setComponent((ComponentName) null);
        if (Build.VERSION.SDK_INT >= 15) {
            intent.setSelector((Intent) null);
        }
        return intent;
    }

    public static void a(g gVar, Context context, Uri uri, String str) {
        boolean z = a(uri.getScheme()) && uri.getHost().equals("play.google.com");
        if (uri.getScheme().equals("market") || z) {
            try {
                gVar.a(context, uri);
                return;
            } catch (c unused) {
            }
        }
        gVar.a(context, uri, str);
    }

    private boolean a(Context context) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/")), 0)) {
            if (resolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(String str) {
        return "http".equalsIgnoreCase(str) || "https".equalsIgnoreCase(str);
    }

    private void b(Context context, Uri uri) {
        context.startActivity(c(context, uri));
    }

    private void b(Context context, Uri uri, String str) {
        Intent intent = new Intent();
        intent.setClassName(context.getPackageName(), "com.facebook.ads.AudienceNetworkActivity");
        intent.addFlags(268435456);
        intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, a.C0007a.BROWSER);
        intent.putExtra(AudienceNetworkActivity.BROWSER_URL, uri.toString());
        intent.putExtra(AudienceNetworkActivity.CLIENT_TOKEN, str);
        intent.putExtra(AudienceNetworkActivity.HANDLER_TIME, System.currentTimeMillis());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            intent.setClassName(context.getPackageName(), "com.facebook.ads.InterstitialAdActivity");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused2) {
                b(context, uri);
            }
        }
    }

    private Intent c(Context context, Uri uri) {
        Intent a = a(uri);
        a.addCategory("android.intent.category.BROWSABLE");
        a.addFlags(268435456);
        a.putExtra("com.android.browser.application_id", context.getPackageName());
        a.putExtra("create_new_tab", false);
        return a;
    }

    public void a(Context context, Uri uri) {
        if (!a(context)) {
            throw new c();
        }
        Intent c = c(context, uri);
        c.setPackage("com.android.vending");
        context.startActivity(c);
    }

    public void a(Context context, Uri uri, String str) {
        if (!a(uri.getScheme()) || !com.facebook.ads.internal.l.a.g(context)) {
            b(context, uri);
        } else {
            b(context, uri, str);
        }
    }
}
