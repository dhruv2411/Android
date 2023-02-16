package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzrk;
import com.google.android.gms.internal.zzrp;
import com.google.android.gms.internal.zztm;
import java.lang.Thread;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzh {
    private static volatile zzh zzach;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final List<Object> zzaci = new CopyOnWriteArrayList();
    private final zzd zzacj = new zzd();
    private final zza zzack = new zza();
    private volatile zzrk zzacl;
    /* access modifiers changed from: private */
    public Thread.UncaughtExceptionHandler zzacm;

    private class zza extends ThreadPoolExecutor {
        public zza() {
            super(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
            setThreadFactory(new zzb());
            allowCoreThreadTimeOut(true);
        }

        /* access modifiers changed from: protected */
        public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return new FutureTask<T>(runnable, t) {
                /* access modifiers changed from: protected */
                public void setException(Throwable th) {
                    Thread.UncaughtExceptionHandler zzb = zzh.this.zzacm;
                    if (zzb != null) {
                        zzb.uncaughtException(Thread.currentThread(), th);
                    } else if (Log.isLoggable("GAv4", 6)) {
                        String valueOf = String.valueOf(th);
                        StringBuilder sb = new StringBuilder(37 + String.valueOf(valueOf).length());
                        sb.append("MeasurementExecutor: job failed with ");
                        sb.append(valueOf);
                        Log.e("GAv4", sb.toString());
                    }
                    super.setException(th);
                }
            };
        }
    }

    private static class zzb implements ThreadFactory {
        private static final AtomicInteger zzacq = new AtomicInteger();

        private zzb() {
        }

        public Thread newThread(Runnable runnable) {
            int incrementAndGet = zzacq.incrementAndGet();
            StringBuilder sb = new StringBuilder(23);
            sb.append("measurement-");
            sb.append(incrementAndGet);
            return new zzc(runnable, sb.toString());
        }
    }

    private static class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    zzh(Context context) {
        Context applicationContext = context.getApplicationContext();
        zzac.zzw(applicationContext);
        this.mContext = applicationContext;
    }

    public static zzh zzam(Context context) {
        zzac.zzw(context);
        if (zzach == null) {
            synchronized (zzh.class) {
                if (zzach == null) {
                    zzach = new zzh(context);
                }
            }
        }
        return zzach;
    }

    /* access modifiers changed from: private */
    public void zzb(zze zze) {
        zzac.zzdk("deliver should be called from worker thread");
        zzac.zzb(zze.zzmH(), (Object) "Measurement must be submitted");
        List<zzi> zzmE = zze.zzmE();
        if (!zzmE.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (zzi next : zzmE) {
                Uri zzmr = next.zzmr();
                if (!hashSet.contains(zzmr)) {
                    hashSet.add(zzmr);
                    next.zzb(zze);
                }
            }
        }
    }

    public static void zzmR() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public void zza(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzacm = uncaughtExceptionHandler;
    }

    public <V> Future<V> zzc(Callable<V> callable) {
        zzac.zzw(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzack.submit(callable);
        }
        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    /* access modifiers changed from: package-private */
    public void zze(zze zze) {
        if (zze.zzmL()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        } else if (zze.zzmH()) {
            throw new IllegalStateException("Measurement can only be submitted once");
        } else {
            final zze zzmC = zze.zzmC();
            zzmC.zzmI();
            this.zzack.execute(new Runnable() {
                public void run() {
                    zzmC.zzmJ().zza(zzmC);
                    Iterator it = zzh.this.zzaci.iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                    zzh.this.zzb(zzmC);
                }
            });
        }
    }

    public void zzg(Runnable runnable) {
        zzac.zzw(runnable);
        this.zzack.submit(runnable);
    }

    public zzrk zzmP() {
        if (this.zzacl == null) {
            synchronized (this) {
                if (this.zzacl == null) {
                    zzrk zzrk = new zzrk();
                    PackageManager packageManager = this.mContext.getPackageManager();
                    String packageName = this.mContext.getPackageName();
                    zzrk.setAppId(packageName);
                    zzrk.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        String valueOf = String.valueOf(packageName);
                        Log.e("GAv4", valueOf.length() != 0 ? "Error retrieving package info: appName set to ".concat(valueOf) : new String("Error retrieving package info: appName set to "));
                    }
                    zzrk.setAppName(packageName);
                    zzrk.setAppVersion(str);
                    this.zzacl = zzrk;
                }
            }
        }
        return this.zzacl;
    }

    public zzrp zzmQ() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        zzrp zzrp = new zzrp();
        zzrp.setLanguage(zztm.zza(Locale.getDefault()));
        zzrp.zzaB(displayMetrics.widthPixels);
        zzrp.zzaC(displayMetrics.heightPixels);
        return zzrp;
    }
}
