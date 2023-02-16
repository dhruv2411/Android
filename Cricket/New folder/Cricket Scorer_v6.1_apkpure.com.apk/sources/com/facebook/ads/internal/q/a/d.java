package com.facebook.ads.internal.q.a;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.concurrent.Executor;

public class d {
    public static <P, PR, R> AsyncTask<P, PR, R> a(Executor executor, AsyncTask<P, PR, R> asyncTask, P... pArr) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(executor, pArr);
            return asyncTask;
        }
        asyncTask.execute(pArr);
        return asyncTask;
    }

    public static void a() {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, String str) {
        if (AdInternalSettings.isTestMode(context)) {
            Log.d("FBAudienceNetworkLog", str + " (displayed for test ads only)");
        }
    }
}
