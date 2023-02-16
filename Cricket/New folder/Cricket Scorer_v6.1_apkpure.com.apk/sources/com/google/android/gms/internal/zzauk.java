package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class zzauk extends zzauh {
    protected zza zzbvm;
    private volatile AppMeasurement.zzf zzbvn;
    private AppMeasurement.zzf zzbvo;
    private long zzbvp;
    private final Map<Activity, zza> zzbvq = new ArrayMap();
    private final CopyOnWriteArrayList<AppMeasurement.zzd> zzbvr = new CopyOnWriteArrayList<>();
    private boolean zzbvs;
    private AppMeasurement.zzf zzbvt;
    private String zzbvu;

    static class zza extends AppMeasurement.zzf {
        public boolean zzbvz;

        public zza(zza zza) {
            this.zzbqf = zza.zzbqf;
            this.zzbqg = zza.zzbqg;
            this.zzbqh = zza.zzbqh;
            this.zzbvz = zza.zzbvz;
        }

        public zza(String str, String str2, long j) {
            this.zzbqf = str;
            this.zzbqg = str2;
            this.zzbqh = j;
            this.zzbvz = false;
        }
    }

    public zzauk(zzaue zzaue) {
        super(zzaue);
    }

    @MainThread
    private void zza(Activity activity, zza zza2, final boolean z) {
        AppMeasurement.zzf zzf = null;
        AppMeasurement.zzf zzf2 = this.zzbvn != null ? this.zzbvn : (this.zzbvo == null || Math.abs(zznR().elapsedRealtime() - this.zzbvp) >= 1000) ? null : this.zzbvo;
        if (zzf2 != null) {
            zzf = new AppMeasurement.zzf(zzf2);
        }
        boolean z2 = true;
        this.zzbvs = true;
        try {
            Iterator<AppMeasurement.zzd> it = this.zzbvr.iterator();
            while (it.hasNext()) {
                try {
                    z2 &= it.next().zza(zzf, zza2);
                } catch (Exception e) {
                    zzKl().zzLY().zzj("onScreenChangeCallback threw exception", e);
                }
            }
        } catch (Exception e2) {
            zzKl().zzLY().zzj("onScreenChangeCallback loop threw exception", e2);
        } catch (Throwable th) {
            this.zzbvs = false;
            throw th;
        }
        this.zzbvs = false;
        if (z2) {
            if (zza2.zzbqg == null) {
                zza2.zzbqg = zzfS(activity.getClass().getCanonicalName());
            }
            final zza zza3 = new zza(zza2);
            this.zzbvo = this.zzbvn;
            this.zzbvp = zznR().elapsedRealtime();
            this.zzbvn = zza3;
            zzKk().zzm(new Runnable() {
                public void run() {
                    if (z && zzauk.this.zzbvm != null) {
                        zzauk.this.zza(zzauk.this.zzbvm);
                    }
                    zzauk.this.zzbvm = zza3;
                    zzauk.this.zzKd().zza((AppMeasurement.zzf) zza3);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zza(@NonNull zza zza2) {
        zzJY().zzW(zznR().elapsedRealtime());
        if (zzKj().zzaO(zza2.zzbvz)) {
            zza2.zzbvz = false;
        }
    }

    public static void zza(AppMeasurement.zzf zzf, Bundle bundle) {
        if (bundle != null && zzf != null && !bundle.containsKey("_sc")) {
            if (zzf.zzbqf != null) {
                bundle.putString("_sn", zzf.zzbqf);
            }
            bundle.putString("_sc", zzf.zzbqg);
            bundle.putLong("_si", zzf.zzbqh);
        }
    }

    static String zzfS(String str) {
        String[] split = str.split("\\.");
        if (split.length == 0) {
            return str.substring(0, 36);
        }
        String str2 = split[split.length - 1];
        return str2.length() > 36 ? str2.substring(0, 36) : str2;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @MainThread
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (bundle != null && (bundle2 = bundle.getBundle("com.google.firebase.analytics.screen_service")) != null) {
            zza zzv = zzv(activity);
            zzv.zzbqh = bundle2.getLong(TtmlNode.ATTR_ID);
            zzv.zzbqf = bundle2.getString("name");
            zzv.zzbqg = bundle2.getString("referrer_name");
        }
    }

    @MainThread
    public void onActivityDestroyed(Activity activity) {
        this.zzbvq.remove(activity);
    }

    @MainThread
    public void onActivityPaused(Activity activity) {
        final zza zzv = zzv(activity);
        this.zzbvo = this.zzbvn;
        this.zzbvp = zznR().elapsedRealtime();
        this.zzbvn = null;
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauk.this.zza(zzv);
                zzauk.this.zzbvm = null;
                zzauk.this.zzKd().zza((AppMeasurement.zzf) null);
            }
        });
    }

    @MainThread
    public void onActivityResumed(Activity activity) {
        zza(activity, zzv(activity), false);
        zzJY().zzJU();
    }

    @MainThread
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza zza2;
        if (bundle != null && (zza2 = this.zzbvq.get(activity)) != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong(TtmlNode.ATTR_ID, zza2.zzbqh);
            bundle2.putString("name", zza2.zzbqf);
            bundle2.putString("referrer_name", zza2.zzbqg);
            bundle.putBundle("com.google.firebase.analytics.screen_service", bundle2);
        }
    }

    @MainThread
    public void registerOnScreenChangeCallback(@NonNull AppMeasurement.zzd zzd) {
        zzJW();
        if (zzd == null) {
            zzKl().zzMa().log("Attempting to register null OnScreenChangeCallback");
            return;
        }
        this.zzbvr.remove(zzd);
        this.zzbvr.add(zzd);
    }

    @MainThread
    public void setCurrentScreen(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        int i = Build.VERSION.SDK_INT;
        if (activity == null) {
            zzKl().zzMa().log("setCurrentScreen must be called with a non-null activity");
        } else if (!zzKk().zzbc()) {
            zzKl().zzMa().log("setCurrentScreen must be called from the main thread");
        } else if (this.zzbvs) {
            zzKl().zzMa().log("Cannot call setCurrentScreen from onScreenChangeCallback");
        } else if (this.zzbvn == null) {
            zzKl().zzMa().log("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzbvq.get(activity) == null) {
            zzKl().zzMa().log("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zzfS(activity.getClass().getCanonicalName());
            }
            boolean equals = this.zzbvn.zzbqg.equals(str2);
            boolean z = (this.zzbvn.zzbqf == null && str == null) || (this.zzbvn.zzbqf != null && this.zzbvn.zzbqf.equals(str));
            if (equals && z) {
                zzKl().zzMb().log("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() < 1 || str.length() > zzKn().zzKP())) {
                zzKl().zzMa().zzj("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() >= 1 && str2.length() <= zzKn().zzKP())) {
                zzKl().zzMe().zze("Setting current screen to name, class", str == null ? "null" : str, str2);
                zza zza2 = new zza(str, str2, zzKh().zzNi());
                this.zzbvq.put(activity, zza2);
                zza(activity, zza2, true);
            } else {
                zzKl().zzMa().zzj("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    @MainThread
    public void unregisterOnScreenChangeCallback(@NonNull AppMeasurement.zzd zzd) {
        zzJW();
        this.zzbvr.remove(zzd);
    }

    public /* bridge */ /* synthetic */ void zzJV() {
        super.zzJV();
    }

    public /* bridge */ /* synthetic */ void zzJW() {
        super.zzJW();
    }

    public /* bridge */ /* synthetic */ void zzJX() {
        super.zzJX();
    }

    public /* bridge */ /* synthetic */ zzatb zzJY() {
        return super.zzJY();
    }

    public /* bridge */ /* synthetic */ zzatf zzJZ() {
        return super.zzJZ();
    }

    public /* bridge */ /* synthetic */ zzauj zzKa() {
        return super.zzKa();
    }

    public /* bridge */ /* synthetic */ zzatu zzKb() {
        return super.zzKb();
    }

    public /* bridge */ /* synthetic */ zzatl zzKc() {
        return super.zzKc();
    }

    public /* bridge */ /* synthetic */ zzaul zzKd() {
        return super.zzKd();
    }

    public /* bridge */ /* synthetic */ zzauk zzKe() {
        return super.zzKe();
    }

    public /* bridge */ /* synthetic */ zzatv zzKf() {
        return super.zzKf();
    }

    public /* bridge */ /* synthetic */ zzatj zzKg() {
        return super.zzKg();
    }

    public /* bridge */ /* synthetic */ zzaut zzKh() {
        return super.zzKh();
    }

    public /* bridge */ /* synthetic */ zzauc zzKi() {
        return super.zzKi();
    }

    public /* bridge */ /* synthetic */ zzaun zzKj() {
        return super.zzKj();
    }

    public /* bridge */ /* synthetic */ zzaud zzKk() {
        return super.zzKk();
    }

    public /* bridge */ /* synthetic */ zzatx zzKl() {
        return super.zzKl();
    }

    public /* bridge */ /* synthetic */ zzaua zzKm() {
        return super.zzKm();
    }

    public /* bridge */ /* synthetic */ zzati zzKn() {
        return super.zzKn();
    }

    @WorkerThread
    public zza zzMU() {
        zzob();
        zzmR();
        return this.zzbvm;
    }

    public AppMeasurement.zzf zzMV() {
        zzJW();
        AppMeasurement.zzf zzf = this.zzbvn;
        if (zzf == null) {
            return null;
        }
        return new AppMeasurement.zzf(zzf);
    }

    @WorkerThread
    public void zza(String str, AppMeasurement.zzf zzf) {
        zzmR();
        synchronized (this) {
            if (this.zzbvu == null || this.zzbvu.equals(str) || zzf != null) {
                this.zzbvu = str;
                this.zzbvt = zzf;
            }
        }
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }

    /* access modifiers changed from: package-private */
    @MainThread
    public zza zzv(@NonNull Activity activity) {
        zzac.zzw(activity);
        zza zza2 = this.zzbvq.get(activity);
        if (zza2 != null) {
            return zza2;
        }
        zza zza3 = new zza((String) null, zzfS(activity.getClass().getCanonicalName()), zzKh().zzNi());
        this.zzbvq.put(activity, zza3);
        return zza3;
    }
}
