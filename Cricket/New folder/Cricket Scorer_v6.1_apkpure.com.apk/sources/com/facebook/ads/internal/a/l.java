package com.facebook.ads.internal.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.m.c;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class l {
    private final c a;
    @Nullable
    private Application b;
    @Nullable
    private a c;
    private long d = 0;
    @Nullable
    private String e = null;
    @Nullable
    private a f = null;

    @TargetApi(14)
    private static class a implements Application.ActivityLifecycleCallbacks {
        private final WeakReference<Activity> a;
        @Nullable
        private l b;

        public a(Activity activity, l lVar) {
            this.a = new WeakReference<>(activity);
            this.b = lVar;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            if (this.b != null) {
                Activity activity2 = (Activity) this.a.get();
                if (activity2 == null || (activity2 != null && activity.equals(activity2))) {
                    this.b.a();
                    this.b = null;
                }
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

    private l(c cVar, Activity activity, int i) {
        this.a = cVar;
        this.b = activity.getApplication();
        this.c = new a(activity, this);
    }

    public static l a(c cVar, Activity activity) {
        return a(cVar, activity, Build.VERSION.SDK_INT);
    }

    @Nullable
    @VisibleForTesting
    protected static l a(c cVar, Activity activity, int i) {
        if (activity == null || i < 14) {
            return null;
        }
        return new l(cVar, activity, i);
    }

    private void a(String str, long j, long j2, @Nullable a aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("leave_time", Long.toString(j));
        hashMap.put("back_time", Long.toString(j2));
        if (aVar != null) {
            hashMap.put("outcome", aVar.name());
        }
        this.a.j(str, hashMap);
    }

    @TargetApi(14)
    public void a() {
        a(this.e, this.d, System.currentTimeMillis(), this.f);
        if (this.b != null && this.c != null) {
            this.b.unregisterActivityLifecycleCallbacks(this.c);
            this.c = null;
            this.b = null;
        }
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    @TargetApi(14)
    public void a(String str) {
        this.e = str;
        if (this.c == null || this.b == null) {
            a(str, -1, -1, a.CANNOT_TRACK);
            return;
        }
        this.d = System.currentTimeMillis();
        this.b.registerActivityLifecycleCallbacks(this.c);
    }
}
