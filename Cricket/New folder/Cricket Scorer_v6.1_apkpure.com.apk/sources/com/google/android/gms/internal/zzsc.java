package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.lang.Thread;

public class zzsc {
    private static volatile zzsc zzadR;
    private final Context mContext;
    private final Context zzadS;
    private final zzsp zzadT;
    private final zztd zzadU;
    private final zzh zzadV;
    private final zzry zzadW;
    private final zzst zzadX;
    private final zztn zzadY;
    private final zztg zzadZ;
    private final GoogleAnalytics zzaea;
    private final zzsk zzaeb;
    private final zzrx zzaec;
    private final zzsh zzaed;
    private final zzss zzaee;
    private final zze zzuP;

    protected zzsc(zzsd zzsd) {
        Context applicationContext = zzsd.getApplicationContext();
        zzac.zzb(applicationContext, (Object) "Application context can't be null");
        Context zzod = zzsd.zzod();
        zzac.zzw(zzod);
        this.mContext = applicationContext;
        this.zzadS = zzod;
        this.zzuP = zzsd.zzh(this);
        this.zzadT = zzsd.zzg(this);
        zztd zzf = zzsd.zzf(this);
        zzf.initialize();
        this.zzadU = zzf;
        zztd zznS = zznS();
        String str = zzsb.VERSION;
        StringBuilder sb = new StringBuilder(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO + String.valueOf(str).length());
        sb.append("Google Analytics ");
        sb.append(str);
        sb.append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4");
        zznS.zzbR(sb.toString());
        zztg zzq = zzsd.zzq(this);
        zzq.initialize();
        this.zzadZ = zzq;
        zztn zze = zzsd.zze(this);
        zze.initialize();
        this.zzadY = zze;
        zzry zzl = zzsd.zzl(this);
        zzsk zzd = zzsd.zzd(this);
        zzrx zzc = zzsd.zzc(this);
        zzsh zzb = zzsd.zzb(this);
        zzss zza = zzsd.zza(this);
        zzh zzao = zzsd.zzao(applicationContext);
        zzao.zza(zzoc());
        this.zzadV = zzao;
        GoogleAnalytics zzi = zzsd.zzi(this);
        zzd.initialize();
        this.zzaeb = zzd;
        zzc.initialize();
        this.zzaec = zzc;
        zzb.initialize();
        this.zzaed = zzb;
        zza.initialize();
        this.zzaee = zza;
        zzst zzp = zzsd.zzp(this);
        zzp.initialize();
        this.zzadX = zzp;
        zzl.initialize();
        this.zzadW = zzl;
        zzi.initialize();
        this.zzaea = zzi;
        zzl.start();
    }

    private void zza(zzsa zzsa) {
        zzac.zzb(zzsa, (Object) "Analytics service not created/initialized");
        zzac.zzb(zzsa.isInitialized(), (Object) "Analytics service not initialized");
    }

    public static zzsc zzan(Context context) {
        zzac.zzw(context);
        if (zzadR == null) {
            synchronized (zzsc.class) {
                if (zzadR == null) {
                    zze zzzc = zzi.zzzc();
                    long elapsedRealtime = zzzc.elapsedRealtime();
                    zzsc zzsc = new zzsc(new zzsd(context));
                    zzadR = zzsc;
                    GoogleAnalytics.zzmx();
                    long elapsedRealtime2 = zzzc.elapsedRealtime() - elapsedRealtime;
                    long longValue = zzsw.zzafZ.get().longValue();
                    if (elapsedRealtime2 > longValue) {
                        zzsc.zznS().zzc("Slow initialization (ms)", Long.valueOf(elapsedRealtime2), Long.valueOf(longValue));
                    }
                }
            }
        }
        return zzadR;
    }

    public Context getContext() {
        return this.mContext;
    }

    public zzry zzmA() {
        zza(this.zzadW);
        return this.zzadW;
    }

    public zztn zzmB() {
        zza(this.zzadY);
        return this.zzadY;
    }

    public void zzmR() {
        zzh.zzmR();
    }

    public zze zznR() {
        return this.zzuP;
    }

    public zztd zznS() {
        zza(this.zzadU);
        return this.zzadU;
    }

    public zzsp zznT() {
        return this.zzadT;
    }

    public zzh zznU() {
        zzac.zzw(this.zzadV);
        return this.zzadV;
    }

    public zzst zznV() {
        zza(this.zzadX);
        return this.zzadX;
    }

    public zztg zznW() {
        zza(this.zzadZ);
        return this.zzadZ;
    }

    public zzsh zznZ() {
        zza(this.zzaed);
        return this.zzaed;
    }

    public zzss zzoa() {
        return this.zzaee;
    }

    /* access modifiers changed from: protected */
    public Thread.UncaughtExceptionHandler zzoc() {
        return new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                zztd zzoe = zzsc.this.zzoe();
                if (zzoe != null) {
                    zzoe.zze("Job execution failed", th);
                }
            }
        };
    }

    public Context zzod() {
        return this.zzadS;
    }

    public zztd zzoe() {
        return this.zzadU;
    }

    public GoogleAnalytics zzof() {
        zzac.zzw(this.zzaea);
        zzac.zzb(this.zzaea.isInitialized(), (Object) "Analytics instance not initialized");
        return this.zzaea;
    }

    public zztg zzog() {
        if (this.zzadZ == null || !this.zzadZ.isInitialized()) {
            return null;
        }
        return this.zzadZ;
    }

    public zzrx zzoh() {
        zza(this.zzaec);
        return this.zzaec;
    }

    public zzsk zzoi() {
        zza(this.zzaeb);
        return this.zzaeb;
    }
}
