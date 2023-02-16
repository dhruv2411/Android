package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzsc;
import com.google.android.gms.internal.zzsw;
import com.google.android.gms.internal.zztb;
import com.google.android.gms.internal.zztc;
import com.google.android.gms.internal.zztk;
import com.google.android.gms.internal.zztl;
import com.google.android.gms.internal.zztn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics extends zza {
    private static List<Runnable> zzabG = new ArrayList();
    private Set<zza> zzabH = new HashSet();
    private boolean zzabI;
    private boolean zzabJ;
    private volatile boolean zzabK;
    private boolean zzabL;
    private boolean zztZ;

    interface zza {
        void zzo(Activity activity);

        void zzp(Activity activity);
    }

    @TargetApi(14)
    class zzb implements Application.ActivityLifecycleCallbacks {
        zzb() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.zzm(activity);
        }

        public void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.zzn(activity);
        }
    }

    public GoogleAnalytics(zzsc zzsc) {
        super(zzsc);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static GoogleAnalytics getInstance(Context context) {
        return zzsc.zzan(context).zzof();
    }

    private zzry zzmA() {
        return zzmn().zzmA();
    }

    private zztn zzmB() {
        return zzmn().zzmB();
    }

    public static void zzmx() {
        synchronized (GoogleAnalytics.class) {
            if (zzabG != null) {
                for (Runnable run : zzabG) {
                    run.run();
                }
                zzabG = null;
            }
        }
    }

    public void dispatchLocalHits() {
        zzmA().zznL();
    }

    @TargetApi(14)
    public void enableAutoActivityReports(Application application) {
        int i = Build.VERSION.SDK_INT;
        if (!this.zzabI) {
            application.registerActivityLifecycleCallbacks(new zzb());
            this.zzabI = true;
        }
    }

    public boolean getAppOptOut() {
        return this.zzabK;
    }

    @Deprecated
    public Logger getLogger() {
        return zztc.getLogger();
    }

    public void initialize() {
        zzmw();
        this.zztZ = true;
    }

    public boolean isDryRunEnabled() {
        return this.zzabJ;
    }

    public boolean isInitialized() {
        return this.zztZ;
    }

    public Tracker newTracker(int i) {
        Tracker tracker;
        zztl zztl;
        synchronized (this) {
            tracker = new Tracker(zzmn(), (String) null, (zztb) null);
            if (i > 0 && (zztl = (zztl) new zztk(zzmn()).zzaI(i)) != null) {
                tracker.zza(zztl);
            }
            tracker.initialize();
        }
        return tracker;
    }

    public Tracker newTracker(String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzmn(), str, (zztb) null);
            tracker.initialize();
        }
        return tracker;
    }

    public void reportActivityStart(Activity activity) {
        if (!this.zzabI) {
            zzm(activity);
        }
    }

    public void reportActivityStop(Activity activity) {
        if (!this.zzabI) {
            zzn(activity);
        }
    }

    public void setAppOptOut(boolean z) {
        this.zzabK = z;
        if (this.zzabK) {
            zzmA().zznK();
        }
    }

    public void setDryRun(boolean z) {
        this.zzabJ = z;
    }

    public void setLocalDispatchPeriod(int i) {
        zzmA().setLocalDispatchPeriod(i);
    }

    @Deprecated
    public void setLogger(Logger logger) {
        zztc.setLogger(logger);
        if (!this.zzabL) {
            String str = zzsw.zzafl.get();
            StringBuilder sb = new StringBuilder(112 + String.valueOf(str).length());
            sb.append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.");
            sb.append(str);
            sb.append(" DEBUG");
            Log.i(zzsw.zzafl.get(), sb.toString());
            this.zzabL = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zza zza2) {
        this.zzabH.add(zza2);
        Context context = zzmn().getContext();
        if (context instanceof Application) {
            enableAutoActivityReports((Application) context);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(zza zza2) {
        this.zzabH.remove(zza2);
    }

    /* access modifiers changed from: package-private */
    public void zzm(Activity activity) {
        for (zza zzo : this.zzabH) {
            zzo.zzo(activity);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzmw() {
        zztn zzmB = zzmB();
        zzmB.zzpI();
        if (zzmB.zzpM()) {
            setDryRun(zzmB.zzpN());
        }
        zzmB.zzpI();
    }

    public String zzmy() {
        zzac.zzdk("getClientId can not be called from the main thread");
        return zzmn().zzoi().zzoQ();
    }

    /* access modifiers changed from: package-private */
    public void zzmz() {
        zzmA().zznM();
    }

    /* access modifiers changed from: package-private */
    public void zzn(Activity activity) {
        for (zza zzp : this.zzabH) {
            zzp.zzp(activity);
        }
    }
}
