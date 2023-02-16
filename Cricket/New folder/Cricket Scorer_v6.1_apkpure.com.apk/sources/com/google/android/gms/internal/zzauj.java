package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzauk;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class zzauj extends zzauh {
    protected zza zzbuV;
    private AppMeasurement.zzb zzbuW;
    private final Set<AppMeasurement.zzc> zzbuX = new CopyOnWriteArraySet();
    private boolean zzbuY;
    private String zzbuZ = null;
    private String zzbva = null;

    @MainThread
    @TargetApi(14)
    private class zza implements Application.ActivityLifecycleCallbacks {
        private zza() {
        }

        private boolean zzfR(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            zzauj.this.zzd("auto", "_ldl", str);
            return true;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            Uri data;
            try {
                zzauj.this.zzKl().zzMe().log("onActivityCreated");
                Intent intent = activity.getIntent();
                if (!(intent == null || (data = intent.getData()) == null || !data.isHierarchical())) {
                    if (bundle == null) {
                        Bundle zzu = zzauj.this.zzKh().zzu(data);
                        String str = zzauj.this.zzKh().zzA(intent) ? "gs" : "auto";
                        if (zzu != null) {
                            zzauj.this.zze(str, "_cmp", zzu);
                        }
                    }
                    String queryParameter = data.getQueryParameter("referrer");
                    if (!TextUtils.isEmpty(queryParameter)) {
                        if (!(queryParameter.contains("gclid") && (queryParameter.contains("utm_campaign") || queryParameter.contains("utm_source") || queryParameter.contains("utm_medium") || queryParameter.contains("utm_term") || queryParameter.contains("utm_content")))) {
                            zzauj.this.zzKl().zzMd().log("Activity created with data 'referrer' param without gclid and at least one utm field");
                            return;
                        } else {
                            zzauj.this.zzKl().zzMd().zzj("Activity created with referrer", queryParameter);
                            zzfR(queryParameter);
                        }
                    } else {
                        return;
                    }
                }
            } catch (Throwable th) {
                zzauj.this.zzKl().zzLY().zzj("Throwable caught in onActivityCreated", th);
            }
            zzauj.this.zzKe().onActivityCreated(activity, bundle);
        }

        public void onActivityDestroyed(Activity activity) {
            zzauj.this.zzKe().onActivityDestroyed(activity);
        }

        @MainThread
        public void onActivityPaused(Activity activity) {
            zzauj.this.zzKe().onActivityPaused(activity);
            zzauj.this.zzKj().zzNe();
        }

        @MainThread
        public void onActivityResumed(Activity activity) {
            zzauj.this.zzKe().onActivityResumed(activity);
            zzauj.this.zzKj().zzNc();
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzauj.this.zzKe().onActivitySaveInstanceState(activity, bundle);
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

    protected zzauj(zzaue zzaue) {
        super(zzaue);
    }

    @WorkerThread
    private void zzMS() {
        try {
            zzf(Class.forName(zzMT()));
        } catch (ClassNotFoundException unused) {
            zzKl().zzMc().log("Tag Manager is not found and thus will not be used");
        }
    }

    private String zzMT() {
        return "com.google.android.gms.tagmanager.TagManagerService";
    }

    private void zza(final AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zznR().currentTimeMillis();
        zzac.zzw(conditionalUserProperty);
        zzac.zzdr(conditionalUserProperty.mName);
        zzac.zzdr(conditionalUserProperty.mOrigin);
        zzac.zzw(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzKh().zzfX(str) != 0) {
            zzKl().zzLY().zzj("Invalid conditional user property name", str);
        } else if (zzKh().zzm(str, obj) != 0) {
            zzKl().zzLY().zze("Invalid conditional user property value", str, obj);
        } else {
            Object zzn = zzKh().zzn(str, obj);
            if (zzn == null) {
                zzKl().zzLY().zze("Unable to normalize conditional user property value", str, obj);
                return;
            }
            conditionalUserProperty.mValue = zzn;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (j > zzKn().zzLa() || j < 1) {
                zzKl().zzLY().zze("Invalid conditional user property timeout", str, Long.valueOf(j));
                return;
            }
            long j2 = conditionalUserProperty.mTimeToLive;
            if (j2 > zzKn().zzLb() || j2 < 1) {
                zzKl().zzLY().zze("Invalid conditional user property time to live", str, Long.valueOf(j2));
            } else {
                zzKk().zzm(new Runnable() {
                    public void run() {
                        zzauj.this.zzb(conditionalUserProperty);
                    }
                });
            }
        }
    }

    private void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zza(str, str2, zznR().currentTimeMillis(), bundle, z, z2, z3, str3);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zza(String str, String str2, Object obj, long j) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzJW();
        zzob();
        if (!this.zzbqc.isEnabled()) {
            zzKl().zzMd().log("User property not set since app measurement is disabled");
        } else if (this.zzbqc.zzMt()) {
            zzKl().zzMd().zze("Setting user property (FE)", str2, obj);
            zzKd().zzb(new zzauq(str2, j, obj, str));
        }
    }

    private void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zznR().currentTimeMillis();
        zzac.zzdr(str2);
        final AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzc(conditionalUserProperty);
            }
        });
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzaM(boolean z) {
        zzmR();
        zzJW();
        zzob();
        zzKl().zzMd().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzKm().setMeasurementEnabled(z);
        zzKd().zzMW();
    }

    private Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            final AtomicReference atomicReference2 = atomicReference;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            final boolean z2 = z;
            this.zzbqc.zzKk().zzm(new Runnable() {
                public void run() {
                    zzauj.this.zzbqc.zzKd().zza(atomicReference2, str4, str5, str6, z2);
                }
            });
            try {
                atomicReference.wait(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            } catch (InterruptedException e) {
                zzKl().zzMa().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzauq> list = (List) atomicReference.get();
        if (list == null) {
            zzKl().zzMa().log("Timed out waiting for get user properties");
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (zzauq zzauq : list) {
            arrayMap.put(zzauq.name, zzauq.getValue());
        }
        return arrayMap;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzb(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        zzmR();
        zzob();
        zzac.zzw(conditionalUserProperty);
        zzac.zzdr(conditionalUserProperty2.mName);
        zzac.zzdr(conditionalUserProperty2.mOrigin);
        zzac.zzw(conditionalUserProperty2.mValue);
        if (!this.zzbqc.isEnabled()) {
            zzKl().zzMd().log("Conditional property not sent since Firebase Analytics is disabled");
            return;
        }
        zzauq zzauq = new zzauq(conditionalUserProperty2.mName, conditionalUserProperty2.mTriggeredTimestamp, conditionalUserProperty2.mValue, conditionalUserProperty2.mOrigin);
        try {
            zzatq zza2 = zzKh().zza(conditionalUserProperty2.mTriggeredEventName, conditionalUserProperty2.mTriggeredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzatq zza3 = zzKh().zza(conditionalUserProperty2.mTimedOutEventName, conditionalUserProperty2.mTimedOutEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzatq zza4 = zzKh().zza(conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            String str = conditionalUserProperty2.mAppId;
            String str2 = conditionalUserProperty2.mOrigin;
            long j = conditionalUserProperty2.mCreationTimestamp;
            String str3 = conditionalUserProperty2.mTriggerEventName;
            long j2 = conditionalUserProperty2.mTriggerTimeout;
            zzatg zzatg = r3;
            zzatg zzatg2 = new zzatg(str, str2, zzauq, j, false, str3, zza3, j2, zza2, conditionalUserProperty2.mTimeToLive, zza4);
            zzKd().zzf(zzatg);
        } catch (IllegalArgumentException unused) {
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        String str4 = str;
        String str5 = str2;
        Bundle bundle2 = bundle;
        zzac.zzdr(str4);
        zzac.zzdr(str5);
        zzac.zzw(bundle);
        zzmR();
        zzob();
        if (!this.zzbqc.isEnabled()) {
            zzKl().zzMd().log("Event not sent since app measurement is disabled");
            return;
        }
        if (!this.zzbuY) {
            this.zzbuY = true;
            zzMS();
        }
        boolean equals = "am".equals(str4);
        boolean zzgd = zzaut.zzgd(str5);
        if (z && this.zzbuW != null && !zzgd && !equals) {
            zzKl().zzMd().zze("Passing event to registered event handler (FE)", str5, bundle2);
            this.zzbuW.zzb(str4, str5, bundle2, j);
        } else if (this.zzbqc.zzMt()) {
            int zzfV = zzKh().zzfV(str5);
            if (zzfV != 0) {
                this.zzbqc.zzKh().zza(zzfV, "_ev", zzKh().zza(str5, zzKn().zzKM(), true), str5 != null ? str5.length() : 0);
                return;
            }
            bundle2.putString("_o", str4);
            Bundle zza2 = zzKh().zza(str5, bundle2, (List<String>) zzf.zzx("_o"), z3);
            if (!bundle2.containsKey("_sc")) {
                zzKn().zzLg();
                zzauk.zza zzMU = zzKe().zzMU();
                if (zzMU != null) {
                    zzMU.zzbvz = true;
                }
                zzauk.zza((AppMeasurement.zzf) zzMU, zza2);
            }
            if (z2) {
                zza2 = zzKh().zzM(zza2);
            }
            Bundle bundle3 = zza2;
            zzKl().zzMd().zze("Logging event (FE)", str5, bundle3);
            zzKd().zzc(new zzatq(str5, new zzato(bundle3), str4, j), str3);
            if (!equals) {
                for (AppMeasurement.zzc zzc : this.zzbuX) {
                    zzc.zzc(str4, str5, new Bundle(bundle3), j);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzc(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        zzmR();
        zzob();
        zzac.zzw(conditionalUserProperty);
        zzac.zzdr(conditionalUserProperty2.mName);
        if (!this.zzbqc.isEnabled()) {
            zzKl().zzMd().log("Conditional property not cleared since Firebase Analytics is disabled");
            return;
        }
        zzauq zzauq = new zzauq(conditionalUserProperty2.mName, 0, (Object) null, (String) null);
        try {
            zzatq zza2 = zzKh().zza(conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, conditionalUserProperty2.mCreationTimestamp, true, false);
            zzatg zzatg = r3;
            zzatg zzatg2 = new zzatg(conditionalUserProperty2.mAppId, conditionalUserProperty2.mOrigin, zzauq, conditionalUserProperty2.mCreationTimestamp, conditionalUserProperty2.mActive, conditionalUserProperty2.mTriggerEventName, (zzatq) null, conditionalUserProperty2.mTriggerTimeout, (zzatq) null, conditionalUserProperty2.mTimeToLive, zza2);
            zzKd().zzf(zzatg);
        } catch (IllegalArgumentException unused) {
        }
    }

    private List<AppMeasurement.ConditionalUserProperty> zzo(String str, String str2, String str3) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            final AtomicReference atomicReference2 = atomicReference;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            this.zzbqc.zzKk().zzm(new Runnable() {
                public void run() {
                    zzauj.this.zzbqc.zzKd().zza(atomicReference2, str4, str5, str6);
                }
            });
            try {
                atomicReference.wait(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            } catch (InterruptedException e) {
                zzKl().zzMa().zze("Interrupted waiting for get conditional user properties", str, e);
            }
        }
        List<zzatg> list = (List) atomicReference.get();
        if (list == null) {
            zzKl().zzMa().zzj("Timed out waiting for get conditional user properties", str);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (zzatg zzatg : list) {
            AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
            conditionalUserProperty.mAppId = str;
            conditionalUserProperty.mOrigin = str2;
            conditionalUserProperty.mCreationTimestamp = zzatg.zzbqX;
            conditionalUserProperty.mName = zzatg.zzbqW.name;
            conditionalUserProperty.mValue = zzatg.zzbqW.getValue();
            conditionalUserProperty.mActive = zzatg.zzbqY;
            conditionalUserProperty.mTriggerEventName = zzatg.zzbqZ;
            if (zzatg.zzbra != null) {
                conditionalUserProperty.mTimedOutEventName = zzatg.zzbra.name;
                if (zzatg.zzbra.zzbrG != null) {
                    conditionalUserProperty.mTimedOutEventParams = zzatg.zzbra.zzbrG.zzLW();
                }
            }
            conditionalUserProperty.mTriggerTimeout = zzatg.zzbrb;
            if (zzatg.zzbrc != null) {
                conditionalUserProperty.mTriggeredEventName = zzatg.zzbrc.name;
                if (zzatg.zzbrc.zzbrG != null) {
                    conditionalUserProperty.mTriggeredEventParams = zzatg.zzbrc.zzbrG.zzLW();
                }
            }
            conditionalUserProperty.mTriggeredTimestamp = zzatg.zzbqW.zzbwc;
            conditionalUserProperty.mTimeToLive = zzatg.zzbrd;
            if (zzatg.zzbre != null) {
                conditionalUserProperty.mExpiredEventName = zzatg.zzbre.name;
                if (zzatg.zzbre.zzbrG != null) {
                    conditionalUserProperty.mExpiredEventParams = zzatg.zzbre.zzbrG.zzLW();
                }
            }
            arrayList.add(conditionalUserProperty);
        }
        return arrayList;
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzJW();
        zza((String) null, str, str2, bundle);
    }

    public void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        zzac.zzdr(str);
        zzJV();
        zza(str, str2, str3, bundle);
    }

    public Task<String> getAppInstanceId() {
        try {
            String zzMl = zzKm().zzMl();
            return zzMl != null ? Tasks.forResult(zzMl) : Tasks.call(zzKk().zzMr(), new Callable<String>() {
                /* renamed from: zzbY */
                public String call() throws Exception {
                    String zzMl = zzauj.this.zzKm().zzMl();
                    if (zzMl != null) {
                        return zzMl;
                    }
                    String zzar = zzauj.this.zzKa().zzar(120000);
                    if (zzar == null) {
                        throw new TimeoutException();
                    }
                    zzauj.this.zzKm().zzfJ(zzar);
                    return zzar;
                }
            });
        } catch (Exception e) {
            zzKl().zzMa().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public List<AppMeasurement.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        zzJW();
        return zzo((String) null, str, str2);
    }

    public List<AppMeasurement.ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        zzac.zzdr(str);
        zzJV();
        return zzo(str, str2, str3);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public int getMaxUserProperties(String str) {
        zzac.zzdr(str);
        return zzKn().zzKY();
    }

    public Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzJW();
        return zzb((String) null, str, str2, z);
    }

    public Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        zzac.zzdr(str);
        zzJV();
        return zzb(str, str2, str3, z);
    }

    public void setConditionalUserProperty(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzac.zzw(conditionalUserProperty);
        zzJW();
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = new AppMeasurement.ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzKl().zzMa().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public void setConditionalUserPropertyAs(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzac.zzw(conditionalUserProperty);
        zzac.zzdr(conditionalUserProperty.mAppId);
        zzJV();
        zza(new AppMeasurement.ConditionalUserProperty(conditionalUserProperty));
    }

    public void setMeasurementEnabled(final boolean z) {
        zzob();
        zzJW();
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzaM(z);
            }
        });
    }

    public void setMinimumSessionDuration(final long j) {
        zzJW();
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzKm().zzbtl.set(j);
                zzauj.this.zzKl().zzMd().zzj("Minimum session duration set", Long.valueOf(j));
            }
        });
    }

    public void setSessionTimeoutDuration(final long j) {
        zzJW();
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzKm().zzbtm.set(j);
                zzauj.this.zzKl().zzMd().zzj("Session timeout duration set", Long.valueOf(j));
            }
        });
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

    @TargetApi(14)
    public void zzMQ() {
        if (getContext().getApplicationContext() instanceof Application) {
            Application application = (Application) getContext().getApplicationContext();
            if (this.zzbuV == null) {
                this.zzbuV = new zza();
            }
            application.unregisterActivityLifecycleCallbacks(this.zzbuV);
            application.registerActivityLifecycleCallbacks(this.zzbuV);
            zzKl().zzMe().log("Registered activity lifecycle callback");
        }
    }

    @WorkerThread
    public void zzMR() {
        zzmR();
        zzJW();
        zzob();
        if (this.zzbqc.zzMt()) {
            zzKd().zzMR();
            String zzMo = zzKm().zzMo();
            if (!TextUtils.isEmpty(zzMo) && !zzMo.equals(zzKc().zzLS())) {
                Bundle bundle = new Bundle();
                bundle.putString("_po", zzMo);
                zze("auto", "_ou", bundle);
            }
        }
    }

    @WorkerThread
    public void zza(AppMeasurement.zzb zzb) {
        zzmR();
        zzJW();
        zzob();
        if (!(zzb == null || zzb == this.zzbuW)) {
            zzac.zza(this.zzbuW == null, (Object) "EventInterceptor already set.");
        }
        this.zzbuW = zzb;
    }

    public void zza(AppMeasurement.zzc zzc) {
        zzJW();
        zzob();
        zzac.zzw(zzc);
        if (!this.zzbuX.add(zzc)) {
            zzKl().zzMa().log("OnEventListener already registered");
        }
    }

    /* access modifiers changed from: protected */
    public void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2 = bundle;
        final Bundle bundle3 = bundle2 != null ? new Bundle(bundle2) : new Bundle();
        final String str4 = str;
        final String str5 = str2;
        final long j2 = j;
        final boolean z4 = z;
        final boolean z5 = z2;
        final boolean z6 = z3;
        final String str6 = str3;
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzb(str4, str5, j2, bundle3, z4, z5, z6, str6);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void zza(String str, String str2, long j, Object obj) {
        final String str3 = str;
        final String str4 = str2;
        final Object obj2 = obj;
        final long j2 = j;
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zza(str3, str4, obj2, j2);
            }
        });
    }

    public void zza(String str, String str2, Bundle bundle, boolean z) {
        zzJW();
        zza(str, str2, bundle, true, this.zzbuW == null || zzaut.zzgd(str2), z, (String) null);
    }

    public List<zzauq> zzaN(final boolean z) {
        zzJW();
        zzob();
        zzKl().zzMd().log("Fetching user attributes (FE)");
        final AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zzbqc.zzKk().zzm(new Runnable() {
                public void run() {
                    zzauj.this.zzKd().zza((AtomicReference<List<zzauq>>) atomicReference, z);
                }
            });
            try {
                atomicReference.wait(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            } catch (InterruptedException e) {
                zzKl().zzMa().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzauq> list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzKl().zzMa().log("Timed out waiting for get user properties");
        return Collections.emptyList();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:18|19|20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        zzKl().zzMa().log("Interrupted waiting for app instance id");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0059, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x004b */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zzar(long r5) {
        /*
            r4 = this;
            com.google.android.gms.internal.zzaud r0 = r4.zzKk()
            boolean r0 = r0.zzMq()
            r1 = 0
            if (r0 == 0) goto L_0x0019
            com.google.android.gms.internal.zzatx r5 = r4.zzKl()
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()
            java.lang.String r6 = "Cannot retrieve app instance id from analytics worker thread"
        L_0x0015:
            r5.log(r6)
            return r1
        L_0x0019:
            com.google.android.gms.internal.zzaud r0 = r4.zzKk()
            boolean r0 = r0.zzbc()
            if (r0 == 0) goto L_0x002e
            com.google.android.gms.internal.zzatx r5 = r4.zzKl()
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()
            java.lang.String r6 = "Cannot retrieve app instance id from main thread"
            goto L_0x0015
        L_0x002e:
            java.util.concurrent.atomic.AtomicReference r0 = new java.util.concurrent.atomic.AtomicReference
            r0.<init>()
            monitor-enter(r0)
            com.google.android.gms.internal.zzaud r2 = r4.zzKk()     // Catch:{ all -> 0x005a }
            com.google.android.gms.internal.zzauj$12 r3 = new com.google.android.gms.internal.zzauj$12     // Catch:{ all -> 0x005a }
            r3.<init>(r0)     // Catch:{ all -> 0x005a }
            r2.zzm(r3)     // Catch:{ all -> 0x005a }
            r0.wait(r5)     // Catch:{ InterruptedException -> 0x004b }
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            java.lang.Object r5 = r0.get()
            java.lang.String r5 = (java.lang.String) r5
            return r5
        L_0x004b:
            com.google.android.gms.internal.zzatx r5 = r4.zzKl()     // Catch:{ all -> 0x005a }
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzMa()     // Catch:{ all -> 0x005a }
            java.lang.String r6 = "Interrupted waiting for app instance id"
            r5.log(r6)     // Catch:{ all -> 0x005a }
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            return r1
        L_0x005a:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzauj.zzar(long):java.lang.String");
    }

    public void zzd(String str, String str2, Bundle bundle, long j) {
        zzJW();
        zza(str, str2, j, bundle, false, true, true, (String) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0027, code lost:
        if (r9 != null) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzd(java.lang.String r8, java.lang.String r9, java.lang.Object r10) {
        /*
            r7 = this;
            com.google.android.gms.common.internal.zzac.zzdr(r8)
            com.google.android.gms.common.util.zze r0 = r7.zznR()
            long r4 = r0.currentTimeMillis()
            com.google.android.gms.internal.zzaut r0 = r7.zzKh()
            int r0 = r0.zzfX(r9)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0039
            com.google.android.gms.internal.zzaut r8 = r7.zzKh()
            com.google.android.gms.internal.zzati r10 = r7.zzKn()
            int r10 = r10.zzKN()
            java.lang.String r8 = r8.zza((java.lang.String) r9, (int) r10, (boolean) r2)
            if (r9 == 0) goto L_0x002d
        L_0x0029:
            int r1 = r9.length()
        L_0x002d:
            com.google.android.gms.internal.zzaue r9 = r7.zzbqc
            com.google.android.gms.internal.zzaut r9 = r9.zzKh()
            java.lang.String r10 = "_ev"
            r9.zza((int) r0, (java.lang.String) r10, (java.lang.String) r8, (int) r1)
            return
        L_0x0039:
            if (r10 == 0) goto L_0x006d
            com.google.android.gms.internal.zzaut r0 = r7.zzKh()
            int r0 = r0.zzm(r9, r10)
            if (r0 == 0) goto L_0x0062
            com.google.android.gms.internal.zzaut r8 = r7.zzKh()
            com.google.android.gms.internal.zzati r3 = r7.zzKn()
            int r3 = r3.zzKN()
            java.lang.String r8 = r8.zza((java.lang.String) r9, (int) r3, (boolean) r2)
            boolean r9 = r10 instanceof java.lang.String
            if (r9 != 0) goto L_0x005d
            boolean r9 = r10 instanceof java.lang.CharSequence
            if (r9 == 0) goto L_0x002d
        L_0x005d:
            java.lang.String r9 = java.lang.String.valueOf(r10)
            goto L_0x0029
        L_0x0062:
            com.google.android.gms.internal.zzaut r0 = r7.zzKh()
            java.lang.Object r6 = r0.zzn(r9, r10)
            if (r6 == 0) goto L_0x0074
            goto L_0x006e
        L_0x006d:
            r6 = 0
        L_0x006e:
            r1 = r7
            r2 = r8
            r3 = r9
            r1.zza((java.lang.String) r2, (java.lang.String) r3, (long) r4, (java.lang.Object) r6)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzauj.zzd(java.lang.String, java.lang.String, java.lang.Object):void");
    }

    public void zze(String str, String str2, Bundle bundle) {
        zzJW();
        zza(str, str2, bundle, true, this.zzbuW == null || zzaut.zzgd(str2), false, (String) null);
    }

    @WorkerThread
    public void zzf(Class<?> cls) {
        try {
            cls.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{getContext()});
        } catch (Exception e) {
            zzKl().zzMa().zzj("Failed to invoke Tag Manager's initialize() method", e);
        }
    }

    @Nullable
    @WorkerThread
    public synchronized String zzfQ(String str) {
        zzob();
        zzJW();
        if (str == null || !str.equals(this.zzbva)) {
            String zzar = zzar(DashMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_FIXED_MS);
            if (zzar == null) {
                return null;
            }
            this.zzbva = str;
            this.zzbuZ = zzar;
            return this.zzbuZ;
        }
        return this.zzbuZ;
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
}
