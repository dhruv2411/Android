package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class zzau extends zzas {
    private static final String TAG = "zzau";
    private static long startTime = 0;
    protected static final Object zzpS = new Object();
    static boolean zzpT = false;
    protected static volatile zzbd zzpz;
    protected boolean zzpU = false;
    protected String zzpV;
    protected boolean zzpW = false;
    protected boolean zzpX = false;

    protected zzau(Context context, String str) {
        super(context);
        this.zzpV = str;
        this.zzpU = false;
    }

    protected zzau(Context context, String str, boolean z) {
        super(context);
        this.zzpV = str;
        this.zzpU = z;
    }

    static zzbe zza(zzbd zzbd, MotionEvent motionEvent, DisplayMetrics displayMetrics) throws zzba {
        Method zzc = zzbd.zzc(zzaz.zzaC(), zzaz.zzaD());
        if (zzc == null || motionEvent == null) {
            throw new zzba();
        }
        try {
            return new zzbe((String) zzc.invoke((Object) null, new Object[]{motionEvent, displayMetrics}));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzba(e);
        }
    }

    protected static synchronized void zza(Context context, boolean z) {
        synchronized (zzau.class) {
            if (!zzpT) {
                startTime = zzbf.zzbb().longValue() / 1000;
                zzpz = zzb(context, z);
                zzpT = true;
            }
        }
    }

    private static void zza(zzbd zzbd) {
        List<Context> singletonList = Collections.singletonList(Context.class);
        zzbd.zza(zzaz.zzai(), zzaz.zzaj(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzas(), zzaz.zzat(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzaq(), zzaz.zzar(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzac(), zzaz.zzad(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzam(), zzaz.zzan(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzW(), zzaz.zzX(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzaE(), zzaz.zzaF(), (List<Class>) singletonList);
        zzbd.zza(zzaz.zzY(), zzaz.zzZ(), (List<Class>) singletonList);
        List asList = Arrays.asList(new Class[]{MotionEvent.class, DisplayMetrics.class});
        zzbd.zza(zzaz.zzaC(), zzaz.zzaD(), (List<Class>) asList);
        zzbd.zza(zzaz.zzaA(), zzaz.zzaB(), (List<Class>) asList);
        zzbd.zza(zzaz.zzag(), zzaz.zzah(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzay(), zzaz.zzaz(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzao(), zzaz.zzap(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzae(), zzaz.zzaf(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzak(), zzaz.zzal(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzaw(), zzaz.zzax(), (List<Class>) Collections.emptyList());
        zzbd.zza(zzaz.zzaa(), zzaz.zzab(), (List<Class>) Arrays.asList(new Class[]{Context.class, Boolean.TYPE}));
        zzbd.zza(zzaz.zzau(), zzaz.zzav(), (List<Class>) Arrays.asList(new Class[]{StackTraceElement[].class}));
        zzbd.zza(zzaz.zzaG(), zzaz.zzaH(), (List<Class>) Arrays.asList(new Class[]{View.class}));
    }

    private void zza(zzbd zzbd, zzag.zza zza) {
        try {
            zzbe zza2 = zza(zzbd, this.zzpF, this.zzpQ);
            zza.zzbn = zza2.zzqI;
            zza.zzbo = zza2.zzqJ;
            zza.zzbp = zza2.zzqK;
            if (this.zzpP) {
                zza.zzbB = zza2.zzcf;
                zza.zzbC = zza2.zzcd;
            }
            zzag.zza.C0036zza zza3 = new zzag.zza.C0036zza();
            zzbe zzb = zzb(this.zzpF);
            zza3.zzbn = zzb.zzqI;
            zza3.zzbo = zzb.zzqJ;
            zza3.zzci = zzb.zzqK;
            if (this.zzpP) {
                zza3.zzcd = zzb.zzcd;
                zza3.zzcf = zzb.zzcf;
                zza3.zzch = Integer.valueOf(zzb.zzqL.longValue() != 0 ? 1 : 0);
                if (this.zzpI > 0) {
                    zza3.zzce = this.zzpQ != null ? Long.valueOf(Math.round(((double) this.zzpN) / ((double) this.zzpI))) : null;
                    zza3.zzcg = Long.valueOf(Math.round(((double) this.zzpM) / ((double) this.zzpI)));
                }
                zza3.zzck = zzb.zzck;
                zza3.zzcj = zzb.zzcj;
                zza3.zzcl = Integer.valueOf(zzb.zzqO.longValue() != 0 ? 1 : 0);
                if (this.zzpL > 0) {
                    zza3.zzcm = Long.valueOf(this.zzpL);
                }
            }
            zza.zzbS = zza3;
        } catch (zzba unused) {
        }
        if (this.zzpH > 0) {
            zza.zzbG = Long.valueOf(this.zzpH);
        }
        if (this.zzpI > 0) {
            zza.zzbF = Long.valueOf(this.zzpI);
        }
        if (this.zzpJ > 0) {
            zza.zzbE = Long.valueOf(this.zzpJ);
        }
        if (this.zzpK > 0) {
            zza.zzbH = Long.valueOf(this.zzpK);
        }
        try {
            int size = this.zzpG.size() - 1;
            if (size > 0) {
                zza.zzbT = new zzag.zza.C0036zza[size];
                for (int i = 0; i < size; i++) {
                    zzbe zza4 = zza(zzbd, (MotionEvent) this.zzpG.get(i), this.zzpQ);
                    zzag.zza.C0036zza zza5 = new zzag.zza.C0036zza();
                    zza5.zzbn = zza4.zzqI;
                    zza5.zzbo = zza4.zzqJ;
                    zza.zzbT[i] = zza5;
                }
            }
        } catch (zzba unused2) {
            zza.zzbT = null;
        }
    }

    protected static zzbd zzb(Context context, boolean z) {
        if (zzpz == null) {
            synchronized (zzpS) {
                if (zzpz == null) {
                    zzbd zza = zzbd.zza(context, zzaz.getKey(), zzaz.zzV(), z);
                    zza(zza);
                    zzpz = zza;
                }
            }
        }
        return zzpz;
    }

    /* access modifiers changed from: protected */
    public long zza(StackTraceElement[] stackTraceElementArr) throws zzba {
        Method zzc = zzpz.zzc(zzaz.zzau(), zzaz.zzav());
        if (zzc == null || stackTraceElementArr == null) {
            throw new zzba();
        }
        try {
            return new zzbb((String) zzc.invoke((Object) null, new Object[]{stackTraceElementArr})).zzqi.longValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzba(e);
        }
    }

    /* access modifiers changed from: protected */
    public zzag.zza zza(Context context, View view) {
        zzag.zza zza = new zzag.zza();
        if (!TextUtils.isEmpty(this.zzpV)) {
            zza.zzba = this.zzpV;
        }
        zzbd zzb = zzb(context, this.zzpU);
        zzb.zzaZ();
        zzb(zzb, zza, view);
        zzb.zzba();
        return zza;
    }

    /* access modifiers changed from: protected */
    public zzag.zza zza(Context context, zzae.zza zza) {
        zzag.zza zza2 = new zzag.zza();
        if (!TextUtils.isEmpty(this.zzpV)) {
            zza2.zzba = this.zzpV;
        }
        zzbd zzb = zzb(context, this.zzpU);
        zzb.zzaZ();
        zza(zzb, zza2, zza);
        zzb.zzba();
        return zza2;
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zza(zzbd zzbd, zzag.zza zza, View view) {
        ArrayList arrayList = new ArrayList();
        if (zzbd.zzaI() == null) {
            return arrayList;
        }
        int zzQ = zzbd.zzQ();
        arrayList.add(new zzbp(zzbd, zza));
        zzbd zzbd2 = zzbd;
        zzag.zza zza2 = zza;
        arrayList.add(new zzbs(zzbd2, zzaz.zzao(), zzaz.zzap(), zza2, zzQ, 1));
        arrayList.add(new zzbn(zzbd2, zzaz.zzag(), zzaz.zzah(), zza2, startTime, zzQ, 25));
        int i = zzQ;
        arrayList.add(new zzbm(zzbd2, zzaz.zzae(), zzaz.zzaf(), zza2, i, 44));
        arrayList.add(new zzbh(zzbd2, zzaz.zzW(), zzaz.zzX(), zza2, i, 3));
        arrayList.add(new zzbq(zzbd2, zzaz.zzak(), zzaz.zzal(), zza2, i, 22));
        arrayList.add(new zzbl(zzbd2, zzaz.zzac(), zzaz.zzad(), zza2, i, 5));
        arrayList.add(new zzbx(zzbd2, zzaz.zzaE(), zzaz.zzaF(), zza2, i, 48));
        if (zzgd.zzDR.get().booleanValue()) {
            arrayList.add(new zzbi(zzbd, zzaz.zzY(), zzaz.zzZ(), zza, zzQ, 49));
        }
        zzbd zzbd3 = zzbd;
        zzag.zza zza3 = zza;
        int i2 = zzQ;
        arrayList.add(new zzbv(zzbd3, zzaz.zzaw(), zzaz.zzax(), zza3, i2, 51));
        arrayList.add(new zzbu(zzbd3, zzaz.zzau(), zzaz.zzav(), zza3, i2, 45, new Throwable().getStackTrace()));
        if (zzgd.zzDS.get().booleanValue()) {
            arrayList.add(new zzby(zzbd, zzaz.zzaG(), zzaz.zzaH(), zza, zzQ, 57, view));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void zza(zzbd zzbd, zzag.zza zza, zzae.zza zza2) {
        if (zzbd.zzaI() != null) {
            zza(zzb(zzbd, zza, zza2));
        }
    }

    /* access modifiers changed from: protected */
    public void zza(List<Callable<Void>> list) {
        ExecutorService zzaI;
        if (zzpz != null && (zzaI = zzpz.zzaI()) != null && !list.isEmpty()) {
            try {
                zzaI.invokeAll(list, zzgd.zzDO.get().longValue(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.d(TAG, String.format("class methods got exception: %s", new Object[]{zzbf.zza(e)}));
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzbe zzb(MotionEvent motionEvent) throws zzba {
        Method zzc = zzpz.zzc(zzaz.zzaA(), zzaz.zzaB());
        if (zzc == null || motionEvent == null) {
            throw new zzba();
        }
        try {
            return new zzbe((String) zzc.invoke((Object) null, new Object[]{motionEvent, this.zzpQ}));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzba(e);
        }
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zzb(zzbd zzbd, zzag.zza zza, zzae.zza zza2) {
        int zzQ = zzbd.zzQ();
        ArrayList arrayList = new ArrayList();
        zzbd zzbd2 = zzbd;
        zzag.zza zza3 = zza;
        arrayList.add(new zzbk(zzbd2, zzaz.zzaa(), zzaz.zzab(), zza3, zzQ, 27, zza2));
        arrayList.add(new zzbn(zzbd2, zzaz.zzag(), zzaz.zzah(), zza3, startTime, zzQ, 25));
        int i = zzQ;
        arrayList.add(new zzbs(zzbd2, zzaz.zzao(), zzaz.zzap(), zza3, i, 1));
        arrayList.add(new zzbt(zzbd2, zzaz.zzaq(), zzaz.zzar(), zza3, i, 31));
        arrayList.add(new zzbw(zzbd2, zzaz.zzay(), zzaz.zzaz(), zza3, i, 33));
        arrayList.add(new zzbj(zzbd2, zzaz.zzas(), zzaz.zzat(), zza3, i, 29));
        arrayList.add(new zzbl(zzbd2, zzaz.zzac(), zzaz.zzad(), zza3, i, 5));
        arrayList.add(new zzbr(zzbd2, zzaz.zzam(), zzaz.zzan(), zza3, i, 12));
        arrayList.add(new zzbh(zzbd2, zzaz.zzW(), zzaz.zzX(), zza3, i, 3));
        arrayList.add(new zzbm(zzbd2, zzaz.zzae(), zzaz.zzaf(), zza3, i, 44));
        arrayList.add(new zzbq(zzbd2, zzaz.zzak(), zzaz.zzal(), zza3, i, 22));
        arrayList.add(new zzbx(zzbd2, zzaz.zzaE(), zzaz.zzaF(), zza3, i, 48));
        if (zzgd.zzDQ.get().booleanValue()) {
            arrayList.add(new zzbi(zzbd, zzaz.zzY(), zzaz.zzZ(), zza, zzQ, 49));
        }
        arrayList.add(new zzbv(zzbd, zzaz.zzaw(), zzaz.zzax(), zza, zzQ, 51));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void zzb(zzbd zzbd, zzag.zza zza, View view) {
        zza(zzbd, zza);
        zza(zza(zzbd, zza, view));
    }
}
