package com.google.android.gms.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzatj;
import com.google.android.gms.internal.zzatx;
import com.google.android.gms.internal.zzaty;
import com.google.android.gms.internal.zzauv;
import com.google.android.gms.internal.zzauw;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.itextpdf.xmp.XMPError;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzaue {
    private static volatile zzaue zzbtZ;
    private final Context mContext;
    private final boolean zzadP;
    private List<Long> zzbuA;
    private int zzbuB;
    private int zzbuC;
    private long zzbuD = -1;
    protected long zzbuE;
    private final zzati zzbua;
    private final zzaua zzbub;
    private final zzatx zzbuc;
    private final zzaud zzbud;
    private final zzaun zzbue;
    private final zzauc zzbuf;
    private final AppMeasurement zzbug;
    private final FirebaseAnalytics zzbuh;
    private final zzaut zzbui;
    private final zzatj zzbuj;
    private final zzatv zzbuk;
    private final zzaty zzbul;
    private final zzauk zzbum;
    private final zzaul zzbun;
    private final zzatl zzbuo;
    private final zzauj zzbup;
    private final zzatu zzbuq;
    private final zzatz zzbur;
    private final zzaup zzbus;
    private final zzatf zzbut;
    private final zzatb zzbuu;
    private boolean zzbuv;
    private Boolean zzbuw;
    private long zzbux;
    private FileLock zzbuy;
    private FileChannel zzbuz;
    private final zze zzuP;

    private class zza implements zzatj.zzb {
        zzauw.zze zzbuG;
        List<Long> zzbuH;
        long zzbuI;
        List<zzauw.zzb> zzth;

        private zza() {
        }

        private long zza(zzauw.zzb zzb) {
            return ((zzb.zzbwZ.longValue() / 1000) / 60) / 60;
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return this.zzth == null || this.zzth.isEmpty();
        }

        public boolean zza(long j, zzauw.zzb zzb) {
            zzac.zzw(zzb);
            if (this.zzth == null) {
                this.zzth = new ArrayList();
            }
            if (this.zzbuH == null) {
                this.zzbuH = new ArrayList();
            }
            if (this.zzth.size() > 0 && zza(this.zzth.get(0)) != zza(zzb)) {
                return false;
            }
            long zzaeT = this.zzbuI + ((long) zzb.zzaeT());
            if (zzaeT >= ((long) zzaue.this.zzKn().zzLn())) {
                return false;
            }
            this.zzbuI = zzaeT;
            this.zzth.add(zzb);
            this.zzbuH.add(Long.valueOf(j));
            return this.zzth.size() < zzaue.this.zzKn().zzLo();
        }

        public void zzb(zzauw.zze zze) {
            zzac.zzw(zze);
            this.zzbuG = zze;
        }
    }

    zzaue(zzaui zzaui) {
        zzac.zzw(zzaui);
        this.mContext = zzaui.mContext;
        this.zzuP = zzaui.zzn(this);
        this.zzbua = zzaui.zza(this);
        zzaua zzb = zzaui.zzb(this);
        zzb.initialize();
        this.zzbub = zzb;
        zzatx zzc = zzaui.zzc(this);
        zzc.initialize();
        this.zzbuc = zzc;
        zzKl().zzMc().zzj("App measurement is starting up, version", Long.valueOf(zzKn().zzKv()));
        zzKn().zzLg();
        zzKl().zzMc().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzaut zzj = zzaui.zzj(this);
        zzj.initialize();
        this.zzbui = zzj;
        zzatl zzq = zzaui.zzq(this);
        zzq.initialize();
        this.zzbuo = zzq;
        zzatu zzr = zzaui.zzr(this);
        zzr.initialize();
        this.zzbuq = zzr;
        zzKn().zzLg();
        String zzke = zzr.zzke();
        if (zzKh().zzge(zzke)) {
            zzKl().zzMc().log("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
        } else {
            zzatx.zza zzMc = zzKl().zzMc();
            String valueOf = String.valueOf(zzke);
            zzMc.log(valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app "));
        }
        zzKl().zzMd().log("Debug-level message logging enabled");
        zzatj zzk = zzaui.zzk(this);
        zzk.initialize();
        this.zzbuj = zzk;
        zzatv zzl = zzaui.zzl(this);
        zzl.initialize();
        this.zzbuk = zzl;
        zzatf zzu = zzaui.zzu(this);
        zzu.initialize();
        this.zzbut = zzu;
        this.zzbuu = zzaui.zzv(this);
        zzaty zzm = zzaui.zzm(this);
        zzm.initialize();
        this.zzbul = zzm;
        zzauk zzo = zzaui.zzo(this);
        zzo.initialize();
        this.zzbum = zzo;
        zzaul zzp = zzaui.zzp(this);
        zzp.initialize();
        this.zzbun = zzp;
        zzauj zzi = zzaui.zzi(this);
        zzi.initialize();
        this.zzbup = zzi;
        zzaup zzt = zzaui.zzt(this);
        zzt.initialize();
        this.zzbus = zzt;
        this.zzbur = zzaui.zzs(this);
        this.zzbug = zzaui.zzh(this);
        this.zzbuh = zzaui.zzg(this);
        zzaun zze = zzaui.zze(this);
        zze.initialize();
        this.zzbue = zze;
        zzauc zzf = zzaui.zzf(this);
        zzf.initialize();
        this.zzbuf = zzf;
        zzaud zzd = zzaui.zzd(this);
        zzd.initialize();
        this.zzbud = zzd;
        if (this.zzbuB != this.zzbuC) {
            zzKl().zzLY().zze("Not all components initialized", Integer.valueOf(this.zzbuB), Integer.valueOf(this.zzbuC));
        }
        this.zzadP = true;
        this.zzbua.zzLg();
        if (this.mContext.getApplicationContext() instanceof Application) {
            int i = Build.VERSION.SDK_INT;
            zzKa().zzMQ();
        } else {
            zzKl().zzMa().log("Application context is not an Application");
        }
        this.zzbud.zzm(new Runnable() {
            public void run() {
                zzaue.this.start();
            }
        });
    }

    private boolean zzMH() {
        zzmR();
        zzob();
        return zzKg().zzLJ() || !TextUtils.isEmpty(zzKg().zzLD());
    }

    @WorkerThread
    private void zzMI() {
        zzmR();
        zzob();
        if (zzMM()) {
            if (this.zzbuE > 0) {
                long abs = 3600000 - Math.abs(zznR().elapsedRealtime() - this.zzbuE);
                if (abs > 0) {
                    zzKl().zzMe().zzj("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                    zzMz().unregister();
                    zzMA().cancel();
                    return;
                }
                this.zzbuE = 0;
            }
            if (!zzMt() || !zzMH()) {
                zzMz().unregister();
                zzMA().cancel();
                return;
            }
            long zzMJ = zzMJ();
            if (zzMJ == 0) {
                zzMz().unregister();
                zzMA().cancel();
            } else if (!zzMy().zzqa()) {
                zzMz().zzpX();
                zzMA().cancel();
            } else {
                long j = zzKm().zzbtb.get();
                long zzLs = zzKn().zzLs();
                if (!zzKh().zzh(j, zzLs)) {
                    zzMJ = Math.max(zzMJ, j + zzLs);
                }
                zzMz().unregister();
                long currentTimeMillis = zzMJ - zznR().currentTimeMillis();
                if (currentTimeMillis <= 0) {
                    currentTimeMillis = zzKn().zzLw();
                    zzKm().zzbsZ.set(zznR().currentTimeMillis());
                }
                zzKl().zzMe().zzj("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis));
                zzMA().zzy(currentTimeMillis);
            }
        }
    }

    private long zzMJ() {
        long j;
        long currentTimeMillis = zznR().currentTimeMillis();
        long zzLz = zzKn().zzLz();
        boolean z = zzKg().zzLK() || zzKg().zzLE();
        if (z) {
            String zzLC = zzKn().zzLC();
            j = (TextUtils.isEmpty(zzLC) || ".none.".equals(zzLC)) ? zzKn().zzLu() : zzKn().zzLv();
        } else {
            j = zzKn().zzLt();
        }
        long j2 = zzKm().zzbsZ.get();
        long j3 = zzKm().zzbta.get();
        long max = Math.max(zzKg().zzLH(), zzKg().zzLI());
        if (max == 0) {
            return 0;
        }
        long abs = currentTimeMillis - Math.abs(max - currentTimeMillis);
        long abs2 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
        long abs3 = currentTimeMillis - Math.abs(j3 - currentTimeMillis);
        long max2 = Math.max(abs2, abs3);
        long j4 = abs + zzLz;
        if (z && max2 > 0) {
            j4 = Math.min(abs, max2) + j;
        }
        if (!zzKh().zzh(max2, j)) {
            j4 = max2 + j;
        }
        if (abs3 == 0 || abs3 < abs) {
            return j4;
        }
        int i = 0;
        while (i < zzKn().zzLB()) {
            long zzLA = j4 + (zzKn().zzLA() * ((long) (1 << i)));
            if (zzLA > abs3) {
                return zzLA;
            }
            i++;
            j4 = zzLA;
        }
        return 0;
    }

    private void zza(zzaug zzaug) {
        if (zzaug == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private void zza(zzauh zzauh) {
        if (zzauh == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzauh.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }

    private boolean zza(zzatm zzatm) {
        if (zzatm.zzbrz == null) {
            return false;
        }
        Iterator<String> it = zzatm.zzbrz.iterator();
        while (it.hasNext()) {
            if ("_r".equals(it.next())) {
                return true;
            }
        }
        return zzKi().zzab(zzatm.mAppId, zzatm.mName) && zzKg().zza(zzME(), zzatm.mAppId, false, false, false, false, false).zzbrr < ((long) zzKn().zzfl(zzatm.mAppId));
    }

    private zzauw.zza[] zza(String str, zzauw.zzg[] zzgArr, zzauw.zzb[] zzbArr) {
        zzac.zzdr(str);
        return zzJZ().zza(str, zzbArr, zzgArr);
    }

    public static zzaue zzbM(Context context) {
        zzac.zzw(context);
        zzac.zzw(context.getApplicationContext());
        if (zzbtZ == null) {
            synchronized (zzaue.class) {
                if (zzbtZ == null) {
                    zzbtZ = new zzaui(context).zzMP();
                }
            }
        }
        return zzbtZ;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzf(com.google.android.gms.internal.zzatd r9) {
        /*
            r8 = this;
            r8.zzmR()
            r8.zzob()
            com.google.android.gms.common.internal.zzac.zzw(r9)
            java.lang.String r0 = r9.packageName
            com.google.android.gms.common.internal.zzac.zzdr(r0)
            com.google.android.gms.internal.zzatj r0 = r8.zzKg()
            java.lang.String r1 = r9.packageName
            com.google.android.gms.internal.zzatc r0 = r0.zzfu(r1)
            com.google.android.gms.internal.zzaua r1 = r8.zzKm()
            java.lang.String r2 = r9.packageName
            java.lang.String r1 = r1.zzfH(r2)
            r2 = 1
            if (r0 != 0) goto L_0x003c
            com.google.android.gms.internal.zzatc r0 = new com.google.android.gms.internal.zzatc
            java.lang.String r3 = r9.packageName
            r0.<init>(r8, r3)
            com.google.android.gms.internal.zzaua r3 = r8.zzKm()
            java.lang.String r3 = r3.zzMh()
            r0.zzfd(r3)
            r0.zzff(r1)
        L_0x003a:
            r1 = r2
            goto L_0x0056
        L_0x003c:
            java.lang.String r3 = r0.zzKp()
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0055
            r0.zzff(r1)
            com.google.android.gms.internal.zzaua r1 = r8.zzKm()
            java.lang.String r1 = r1.zzMh()
            r0.zzfd(r1)
            goto L_0x003a
        L_0x0055:
            r1 = 0
        L_0x0056:
            java.lang.String r3 = r9.zzbqL
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0070
            java.lang.String r3 = r9.zzbqL
            java.lang.String r4 = r0.getGmpAppId()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0070
            java.lang.String r1 = r9.zzbqL
            r0.zzfe(r1)
            r1 = r2
        L_0x0070:
            java.lang.String r3 = r9.zzbqT
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x008a
            java.lang.String r3 = r9.zzbqT
            java.lang.String r4 = r0.zzKq()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x008a
            java.lang.String r1 = r9.zzbqT
            r0.zzfg(r1)
            r1 = r2
        L_0x008a:
            long r3 = r9.zzbqN
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00a2
            long r3 = r9.zzbqN
            long r5 = r0.zzKv()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00a2
            long r3 = r9.zzbqN
            r0.zzab(r3)
            r1 = r2
        L_0x00a2:
            java.lang.String r3 = r9.zzbhN
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00bc
            java.lang.String r3 = r9.zzbhN
            java.lang.String r4 = r0.zzmZ()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00bc
            java.lang.String r1 = r9.zzbhN
            r0.setAppVersion(r1)
            r1 = r2
        L_0x00bc:
            long r3 = r9.zzbqS
            long r5 = r0.zzKt()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00cc
            long r3 = r9.zzbqS
            r0.zzaa(r3)
            r1 = r2
        L_0x00cc:
            java.lang.String r3 = r9.zzbqM
            if (r3 == 0) goto L_0x00e2
            java.lang.String r3 = r9.zzbqM
            java.lang.String r4 = r0.zzKu()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00e2
            java.lang.String r1 = r9.zzbqM
            r0.zzfh(r1)
            r1 = r2
        L_0x00e2:
            long r3 = r9.zzbqO
            long r5 = r0.zzKw()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00f2
            long r3 = r9.zzbqO
            r0.zzac(r3)
            r1 = r2
        L_0x00f2:
            boolean r3 = r9.zzbqQ
            boolean r4 = r0.zzKx()
            if (r3 == r4) goto L_0x0100
            boolean r1 = r9.zzbqQ
            r0.setMeasurementEnabled(r1)
            r1 = r2
        L_0x0100:
            java.lang.String r3 = r9.zzbqP
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x011a
            java.lang.String r3 = r9.zzbqP
            java.lang.String r4 = r0.zzKI()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x011a
            java.lang.String r1 = r9.zzbqP
            r0.zzfi(r1)
            r1 = r2
        L_0x011a:
            long r3 = r9.zzbqU
            long r5 = r0.zzuW()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x012a
            long r3 = r9.zzbqU
            r0.zzam(r3)
            r1 = r2
        L_0x012a:
            if (r1 == 0) goto L_0x0133
            com.google.android.gms.internal.zzatj r9 = r8.zzKg()
            r9.zza((com.google.android.gms.internal.zzatc) r0)
        L_0x0133:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaue.zzf(com.google.android.gms.internal.zzatd):void");
    }

    private boolean zzl(String str, long j) {
        long j2;
        int i;
        zzKg().beginTransaction();
        try {
            zza zza2 = new zza();
            zzKg().zza(str, j, this.zzbuD, zza2);
            int i2 = 0;
            if (!zza2.isEmpty()) {
                zzauw.zze zze = zza2.zzbuG;
                zze.zzbxg = new zzauw.zzb[zza2.zzth.size()];
                int i3 = 0;
                boolean z = false;
                int i4 = 0;
                while (i3 < zza2.zzth.size()) {
                    if (zzKi().zzaa(zza2.zzbuG.zzaS, zza2.zzth.get(i3).name)) {
                        zzKl().zzMa().zze("Dropping blacklisted raw event. appId", zzatx.zzfE(zza2.zzbuG.zzaS), zza2.zzth.get(i3).name);
                        if (!zzKh().zzgg(zza2.zzbuG.zzaS)) {
                            if (!zzKh().zzgh(zza2.zzbuG.zzaS)) {
                                i = i2;
                                if (i == 0 && !"_err".equals(zza2.zzth.get(i3).name)) {
                                    zzKh().zza(11, "_ev", zza2.zzth.get(i3).name, i2);
                                }
                            }
                        }
                        i = 1;
                        zzKh().zza(11, "_ev", zza2.zzth.get(i3).name, i2);
                    } else {
                        boolean zzab = zzKi().zzab(zza2.zzbuG.zzaS, zza2.zzth.get(i3).name);
                        if (zzab || zzKh().zzgi(zza2.zzth.get(i3).name)) {
                            if (zza2.zzth.get(i3).zzbwY == null) {
                                zza2.zzth.get(i3).zzbwY = new zzauw.zzc[i2];
                            }
                            zzauw.zzc[] zzcArr = zza2.zzth.get(i3).zzbwY;
                            int length = zzcArr.length;
                            int i5 = i2;
                            int i6 = i5;
                            int i7 = i6;
                            while (i5 < length) {
                                zzauw.zzc zzc = zzcArr[i5];
                                if ("_c".equals(zzc.name)) {
                                    zzc.zzbxc = 1L;
                                    i6 = 1;
                                } else if ("_r".equals(zzc.name)) {
                                    zzc.zzbxc = 1L;
                                    i7 = 1;
                                }
                                i5++;
                            }
                            if (i6 == 0 && zzab) {
                                zzKl().zzMe().zzj("Marking event as conversion", zza2.zzth.get(i3).name);
                                zzauw.zzc[] zzcArr2 = (zzauw.zzc[]) Arrays.copyOf(zza2.zzth.get(i3).zzbwY, zza2.zzth.get(i3).zzbwY.length + 1);
                                zzauw.zzc zzc2 = new zzauw.zzc();
                                zzc2.name = "_c";
                                zzc2.zzbxc = 1L;
                                zzcArr2[zzcArr2.length - 1] = zzc2;
                                zza2.zzth.get(i3).zzbwY = zzcArr2;
                            }
                            if (i7 == 0) {
                                zzKl().zzMe().zzj("Marking event as real-time", zza2.zzth.get(i3).name);
                                zzauw.zzc[] zzcArr3 = (zzauw.zzc[]) Arrays.copyOf(zza2.zzth.get(i3).zzbwY, zza2.zzth.get(i3).zzbwY.length + 1);
                                zzauw.zzc zzc3 = new zzauw.zzc();
                                zzc3.name = "_r";
                                zzc3.zzbxc = 1L;
                                zzcArr3[zzcArr3.length - 1] = zzc3;
                                zza2.zzth.get(i3).zzbwY = zzcArr3;
                            }
                            if (zzKg().zza(zzME(), zza2.zzbuG.zzaS, false, false, false, false, true).zzbrr > ((long) zzKn().zzfl(zza2.zzbuG.zzaS))) {
                                zzauw.zzb zzb = zza2.zzth.get(i3);
                                int i8 = 0;
                                while (true) {
                                    if (i8 >= zzb.zzbwY.length) {
                                        break;
                                    } else if ("_r".equals(zzb.zzbwY[i8].name)) {
                                        zzauw.zzc[] zzcArr4 = new zzauw.zzc[(zzb.zzbwY.length - 1)];
                                        if (i8 > 0) {
                                            System.arraycopy(zzb.zzbwY, 0, zzcArr4, 0, i8);
                                        }
                                        if (i8 < zzcArr4.length) {
                                            System.arraycopy(zzb.zzbwY, i8 + 1, zzcArr4, i8, zzcArr4.length - i8);
                                        }
                                        zzb.zzbwY = zzcArr4;
                                    } else {
                                        i8++;
                                    }
                                }
                            } else {
                                z = true;
                            }
                            if (zzaut.zzfT(zza2.zzth.get(i3).name) && zzab && zzKg().zza(zzME(), zza2.zzbuG.zzaS, false, false, true, false, false).zzbrp > ((long) zzKn().zzfk(zza2.zzbuG.zzaS))) {
                                zzKl().zzMa().zzj("Too many conversions. Not logging as conversion. appId", zzatx.zzfE(zza2.zzbuG.zzaS));
                                zzauw.zzb zzb2 = zza2.zzth.get(i3);
                                boolean z2 = false;
                                zzauw.zzc zzc4 = null;
                                for (zzauw.zzc zzc5 : zzb2.zzbwY) {
                                    if ("_c".equals(zzc5.name)) {
                                        zzc4 = zzc5;
                                    } else if ("_err".equals(zzc5.name)) {
                                        z2 = true;
                                    }
                                }
                                if (z2 && zzc4 != null) {
                                    zzauw.zzc[] zzcArr5 = new zzauw.zzc[(zzb2.zzbwY.length - 1)];
                                    int i9 = 0;
                                    for (zzauw.zzc zzc6 : zzb2.zzbwY) {
                                        if (zzc6 != zzc4) {
                                            zzcArr5[i9] = zzc6;
                                            i9++;
                                        }
                                    }
                                    zzb2.zzbwY = zzcArr5;
                                } else if (zzc4 != null) {
                                    zzc4.name = "_err";
                                    zzc4.zzbxc = 10L;
                                } else {
                                    zzKl().zzLY().zzj("Did not find conversion parameter. appId", zzatx.zzfE(zza2.zzbuG.zzaS));
                                }
                            }
                        }
                        zze.zzbxg[i4] = zza2.zzth.get(i3);
                        i4++;
                    }
                    i3++;
                    i2 = 0;
                }
                if (i4 < zza2.zzth.size()) {
                    zze.zzbxg = (zzauw.zzb[]) Arrays.copyOf(zze.zzbxg, i4);
                }
                zze.zzbxz = zza(zza2.zzbuG.zzaS, zza2.zzbuG.zzbxh, zze.zzbxg);
                zze.zzbxj = Long.MAX_VALUE;
                zze.zzbxk = Long.MIN_VALUE;
                for (zzauw.zzb zzb3 : zze.zzbxg) {
                    if (zzb3.zzbwZ.longValue() < zze.zzbxj.longValue()) {
                        zze.zzbxj = zzb3.zzbwZ;
                    }
                    if (zzb3.zzbwZ.longValue() > zze.zzbxk.longValue()) {
                        zze.zzbxk = zzb3.zzbwZ;
                    }
                }
                String str2 = zza2.zzbuG.zzaS;
                zzatc zzfu = zzKg().zzfu(str2);
                if (zzfu == null) {
                    zzKl().zzLY().zzj("Bundling raw events w/o app info. appId", zzatx.zzfE(zza2.zzbuG.zzaS));
                } else if (zze.zzbxg.length > 0) {
                    long zzKs = zzfu.zzKs();
                    zze.zzbxm = zzKs != 0 ? Long.valueOf(zzKs) : null;
                    long zzKr = zzfu.zzKr();
                    if (zzKr == 0) {
                        zzKr = zzKs;
                    }
                    zze.zzbxl = zzKr != 0 ? Long.valueOf(zzKr) : null;
                    zzfu.zzKB();
                    zze.zzbxx = Integer.valueOf((int) zzfu.zzKy());
                    zzfu.zzY(zze.zzbxj.longValue());
                    zzfu.zzZ(zze.zzbxk.longValue());
                    zze.zzbqP = zzfu.zzKJ();
                    zzKg().zza(zzfu);
                }
                if (zze.zzbxg.length > 0) {
                    zzKn().zzLg();
                    zzauv.zzb zzfL = zzKi().zzfL(zza2.zzbuG.zzaS);
                    if (zzfL != null) {
                        if (zzfL.zzbwN != null) {
                            j2 = zzfL.zzbwN;
                            zze.zzbxE = j2;
                            zzKg().zza(zze, z);
                        }
                    }
                    if (TextUtils.isEmpty(zza2.zzbuG.zzbqL)) {
                        j2 = -1L;
                        zze.zzbxE = j2;
                        zzKg().zza(zze, z);
                    } else {
                        zzKl().zzMa().zzj("Did not find measurement config or missing version info. appId", zzatx.zzfE(zza2.zzbuG.zzaS));
                        zzKg().zza(zze, z);
                    }
                }
                zzKg().zzJ(zza2.zzbuH);
                zzKg().zzfB(str2);
                zzKg().setTransactionSuccessful();
                boolean z3 = zze.zzbxg.length > 0;
                zzKg().endTransaction();
                return z3;
            }
            zzKg().setTransactionSuccessful();
            zzKg().endTransaction();
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            zzKg().endTransaction();
            throw th2;
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    @WorkerThread
    public boolean isEnabled() {
        zzmR();
        zzob();
        if (zzKn().zzLh()) {
            return false;
        }
        Boolean zzLi = zzKn().zzLi();
        return zzKm().zzaL(zzLi != null ? zzLi.booleanValue() : !zzKn().zzwR());
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void start() {
        zzmR();
        zzKg().zzLF();
        if (zzKm().zzbsZ.get() == 0) {
            zzKm().zzbsZ.set(zznR().currentTimeMillis());
        }
        if (zzMt()) {
            zzKn().zzLg();
            if (!TextUtils.isEmpty(zzKb().getGmpAppId())) {
                String zzMk = zzKm().zzMk();
                if (zzMk != null) {
                    if (!zzMk.equals(zzKb().getGmpAppId())) {
                        zzKl().zzMc().log("Rechecking which service to use due to a GMP App Id change");
                        zzKm().zzMn();
                        this.zzbun.disconnect();
                        this.zzbun.zzoD();
                    }
                }
                zzKm().zzfI(zzKb().getGmpAppId());
            }
            zzKn().zzLg();
            if (!TextUtils.isEmpty(zzKb().getGmpAppId())) {
                zzKa().zzMR();
            }
        } else if (isEnabled()) {
            if (!zzKh().zzbW("android.permission.INTERNET")) {
                zzKl().zzLY().log("App is missing INTERNET permission");
            }
            if (!zzKh().zzbW("android.permission.ACCESS_NETWORK_STATE")) {
                zzKl().zzLY().log("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzKn().zzLg();
            if (!zzadg.zzbi(getContext()).zzzx()) {
                if (!zzaub.zzi(getContext(), false)) {
                    zzKl().zzLY().log("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzaum.zzj(getContext(), false)) {
                    zzKl().zzLY().log("AppMeasurementService not registered/enabled");
                }
            }
            zzKl().zzLY().log("Uploading is not possible. App measurement disabled");
        }
        zzMI();
    }

    /* access modifiers changed from: package-private */
    public void zzJV() {
        zzKn().zzLg();
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: package-private */
    public void zzJW() {
        zzKn().zzLg();
    }

    public zzatb zzJY() {
        zza((zzaug) this.zzbuu);
        return this.zzbuu;
    }

    public zzatf zzJZ() {
        zza((zzauh) this.zzbut);
        return this.zzbut;
    }

    /* access modifiers changed from: protected */
    public void zzK(List<Long> list) {
        zzac.zzax(!list.isEmpty());
        if (this.zzbuA != null) {
            zzKl().zzLY().log("Set uploading progress before finishing the previous upload");
        } else {
            this.zzbuA = new ArrayList(list);
        }
    }

    public zzauj zzKa() {
        zza((zzauh) this.zzbup);
        return this.zzbup;
    }

    public zzatu zzKb() {
        zza((zzauh) this.zzbuq);
        return this.zzbuq;
    }

    public zzatl zzKc() {
        zza((zzauh) this.zzbuo);
        return this.zzbuo;
    }

    public zzaul zzKd() {
        zza((zzauh) this.zzbun);
        return this.zzbun;
    }

    public zzauk zzKe() {
        zza((zzauh) this.zzbum);
        return this.zzbum;
    }

    public zzatv zzKf() {
        zza((zzauh) this.zzbuk);
        return this.zzbuk;
    }

    public zzatj zzKg() {
        zza((zzauh) this.zzbuj);
        return this.zzbuj;
    }

    public zzaut zzKh() {
        zza((zzaug) this.zzbui);
        return this.zzbui;
    }

    public zzauc zzKi() {
        zza((zzauh) this.zzbuf);
        return this.zzbuf;
    }

    public zzaun zzKj() {
        zza((zzauh) this.zzbue);
        return this.zzbue;
    }

    public zzaud zzKk() {
        zza((zzauh) this.zzbud);
        return this.zzbud;
    }

    public zzatx zzKl() {
        zza((zzauh) this.zzbuc);
        return this.zzbuc;
    }

    public zzaua zzKm() {
        zza((zzaug) this.zzbub);
        return this.zzbub;
    }

    public zzati zzKn() {
        return this.zzbua;
    }

    public zzaup zzMA() {
        zza((zzauh) this.zzbus);
        return this.zzbus;
    }

    /* access modifiers changed from: package-private */
    public FileChannel zzMB() {
        return this.zzbuz;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzMC() {
        zzmR();
        zzob();
        if (zzMM() && zzMD()) {
            zzy(zza(zzMB()), zzKb().zzLX());
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzMD() {
        String str;
        zzatx.zza zza2;
        zzmR();
        try {
            this.zzbuz = new RandomAccessFile(new File(getContext().getFilesDir(), this.zzbuj.zzow()), "rw").getChannel();
            this.zzbuy = this.zzbuz.tryLock();
            if (this.zzbuy != null) {
                zzKl().zzMe().log("Storage concurrent access okay");
                return true;
            }
            zzKl().zzLY().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            e = e;
            zza2 = zzKl().zzLY();
            str = "Failed to acquire storage lock";
            zza2.zzj(str, e);
            return false;
        } catch (IOException e2) {
            e = e2;
            zza2 = zzKl().zzLY();
            str = "Failed to access storage lock file";
            zza2.zzj(str, e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public long zzME() {
        return ((((zznR().currentTimeMillis() + zzKm().zzMi()) / 1000) / 60) / 60) / 24;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzMF() {
        zzmR();
        return this.zzbuA != null;
    }

    @WorkerThread
    public void zzMG() {
        zzatc zzfu;
        String str;
        String str2;
        zzmR();
        zzob();
        zzKn().zzLg();
        Boolean zzMm = zzKm().zzMm();
        if (zzMm == null) {
            zzKl().zzMa().log("Upload data called on the client side before use of service was decided");
        } else if (zzMm.booleanValue()) {
            zzKl().zzLY().log("Upload called in the client side when service should be used");
        } else if (this.zzbuE > 0) {
            zzMI();
        } else if (zzMF()) {
            zzKl().zzMa().log("Uploading requested multiple times");
        } else if (!zzMy().zzqa()) {
            zzKl().zzMa().log("Network not connected, ignoring upload request");
            zzMI();
        } else {
            long currentTimeMillis = zznR().currentTimeMillis();
            zzaq(currentTimeMillis - zzKn().zzLr());
            long j = zzKm().zzbsZ.get();
            if (j != 0) {
                zzKl().zzMd().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
            }
            String zzLD = zzKg().zzLD();
            if (!TextUtils.isEmpty(zzLD)) {
                if (this.zzbuD == -1) {
                    this.zzbuD = zzKg().zzLL();
                }
                List<Pair<zzauw.zze, Long>> zzn = zzKg().zzn(zzLD, zzKn().zzfq(zzLD), zzKn().zzfr(zzLD));
                if (!zzn.isEmpty()) {
                    Iterator<Pair<zzauw.zze, Long>> it = zzn.iterator();
                    while (true) {
                        str = null;
                        if (!it.hasNext()) {
                            str2 = null;
                            break;
                        }
                        zzauw.zze zze = (zzauw.zze) it.next().first;
                        if (!TextUtils.isEmpty(zze.zzbxt)) {
                            str2 = zze.zzbxt;
                            break;
                        }
                    }
                    if (str2 != null) {
                        int i = 0;
                        while (true) {
                            if (i >= zzn.size()) {
                                break;
                            }
                            zzauw.zze zze2 = (zzauw.zze) zzn.get(i).first;
                            if (!TextUtils.isEmpty(zze2.zzbxt) && !zze2.zzbxt.equals(str2)) {
                                zzn = zzn.subList(0, i);
                                break;
                            }
                            i++;
                        }
                    }
                    zzauw.zzd zzd = new zzauw.zzd();
                    zzd.zzbxd = new zzauw.zze[zzn.size()];
                    ArrayList arrayList = new ArrayList(zzn.size());
                    for (int i2 = 0; i2 < zzd.zzbxd.length; i2++) {
                        zzd.zzbxd[i2] = (zzauw.zze) zzn.get(i2).first;
                        arrayList.add((Long) zzn.get(i2).second);
                        zzd.zzbxd[i2].zzbxs = Long.valueOf(zzKn().zzKv());
                        zzd.zzbxd[i2].zzbxi = Long.valueOf(currentTimeMillis);
                        zzd.zzbxd[i2].zzbxy = Boolean.valueOf(zzKn().zzLg());
                    }
                    if (zzKl().zzak(2)) {
                        str = zzaut.zzb(zzd);
                    }
                    byte[] zza2 = zzKh().zza(zzd);
                    String zzLq = zzKn().zzLq();
                    try {
                        URL url = new URL(zzLq);
                        zzK(arrayList);
                        zzKm().zzbta.set(currentTimeMillis);
                        String str3 = "?";
                        if (zzd.zzbxd.length > 0) {
                            str3 = zzd.zzbxd[0].zzaS;
                        }
                        zzKl().zzMe().zzd("Uploading data. app, uncompressed size, data", str3, Integer.valueOf(zza2.length), str);
                        zzMy().zza(zzLD, url, zza2, (Map<String, String>) null, new zzaty.zza() {
                            public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
                                zzaue.this.zza(i, th, bArr);
                            }
                        });
                    } catch (MalformedURLException unused) {
                        zzKl().zzLY().zze("Failed to parse upload URL. Not uploading. appId", zzatx.zzfE(zzLD), zzLq);
                    }
                }
            } else {
                this.zzbuD = -1;
                String zzao = zzKg().zzao(currentTimeMillis - zzKn().zzLr());
                if (!TextUtils.isEmpty(zzao) && (zzfu = zzKg().zzfu(zzao)) != null) {
                    zzb(zzfu);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzMK() {
        this.zzbuC++;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzML() {
        zzmR();
        zzob();
        if (!this.zzbuv) {
            zzKl().zzMc().log("This instance being marked as an uploader");
            zzMC();
        }
        this.zzbuv = true;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzMM() {
        zzmR();
        zzob();
        return this.zzbuv;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzMt() {
        zzob();
        zzmR();
        if (this.zzbuw == null || this.zzbux == 0 || (this.zzbuw != null && !this.zzbuw.booleanValue() && Math.abs(zznR().elapsedRealtime() - this.zzbux) > 1000)) {
            this.zzbux = zznR().elapsedRealtime();
            zzKn().zzLg();
            boolean z = false;
            if (zzKh().zzbW("android.permission.INTERNET") && zzKh().zzbW("android.permission.ACCESS_NETWORK_STATE") && (zzadg.zzbi(getContext()).zzzx() || (zzaub.zzi(getContext(), false) && zzaum.zzj(getContext(), false)))) {
                z = true;
            }
            this.zzbuw = Boolean.valueOf(z);
            if (this.zzbuw.booleanValue()) {
                this.zzbuw = Boolean.valueOf(zzKh().zzga(zzKb().getGmpAppId()));
            }
        }
        return this.zzbuw.booleanValue();
    }

    public zzatx zzMu() {
        if (this.zzbuc == null || !this.zzbuc.isInitialized()) {
            return null;
        }
        return this.zzbuc;
    }

    /* access modifiers changed from: package-private */
    public zzaud zzMv() {
        return this.zzbud;
    }

    public AppMeasurement zzMw() {
        return this.zzbug;
    }

    public FirebaseAnalytics zzMx() {
        return this.zzbuh;
    }

    public zzaty zzMy() {
        zza((zzauh) this.zzbul);
        return this.zzbul;
    }

    public zzatz zzMz() {
        if (this.zzbur != null) {
            return this.zzbur;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public void zzW(boolean z) {
        zzMI();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public int zza(FileChannel fileChannel) {
        zzmR();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzKl().zzLY().log("Bad chanel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    zzKl().zzMa().zzj("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            zzKl().zzLY().zzj("Failed to read from channel", e);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(int i, Throwable th, byte[] bArr) {
        zzmR();
        zzob();
        boolean z = false;
        if (bArr == null) {
            bArr = new byte[0];
        }
        List<Long> list = this.zzbuA;
        this.zzbuA = null;
        if ((i == 200 || i == 204) && th == null) {
            try {
                zzKm().zzbsZ.set(zznR().currentTimeMillis());
                zzKm().zzbta.set(0);
                zzMI();
                zzKl().zzMe().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzKg().beginTransaction();
                for (Long longValue : list) {
                    zzKg().zzan(longValue.longValue());
                }
                zzKg().setTransactionSuccessful();
                zzKg().endTransaction();
                if (!zzMy().zzqa() || !zzMH()) {
                    this.zzbuD = -1;
                    zzMI();
                } else {
                    zzMG();
                }
                this.zzbuE = 0;
            } catch (SQLiteException e) {
                zzKl().zzLY().zzj("Database error while trying to delete uploaded bundles", e);
                this.zzbuE = zznR().elapsedRealtime();
                zzKl().zzMe().zzj("Disable upload, time", Long.valueOf(this.zzbuE));
            } catch (Throwable th2) {
                zzKg().endTransaction();
                throw th2;
            }
        } else {
            zzKl().zzMe().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzKm().zzbta.set(zznR().currentTimeMillis());
            if (i == 503 || i == 429) {
                z = true;
            }
            if (z) {
                zzKm().zzbtb.set(zznR().currentTimeMillis());
            }
            zzMI();
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zza(zzatd zzatd, long j) {
        zzatc zzfu = zzKg().zzfu(zzatd.packageName);
        if (!(zzfu == null || zzfu.getGmpAppId() == null || zzfu.getGmpAppId().equals(zzatd.zzbqL))) {
            zzKl().zzMa().zzj("New GMP App Id passed in. Removing cached database data. appId", zzatx.zzfE(zzfu.zzke()));
            zzKg().zzfz(zzfu.zzke());
            zzfu = null;
        }
        if (zzfu != null && zzfu.zzmZ() != null && !zzfu.zzmZ().equals(zzatd.zzbhN)) {
            Bundle bundle = new Bundle();
            bundle.putString("_pv", zzfu.zzmZ());
            zzb(new zzatq("_au", new zzato(bundle), "auto", j), zzatd);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzatm zzatm, zzatd zzatd) {
        zzmR();
        zzob();
        zzac.zzw(zzatm);
        zzac.zzw(zzatd);
        zzac.zzdr(zzatm.mAppId);
        zzac.zzax(zzatm.mAppId.equals(zzatd.packageName));
        zzauw.zze zze = new zzauw.zze();
        zze.zzbxf = 1;
        zze.zzbxn = AbstractSpiCall.ANDROID_CLIENT_TYPE;
        zze.zzaS = zzatd.packageName;
        zze.zzbqM = zzatd.zzbqM;
        zze.zzbhN = zzatd.zzbhN;
        zze.zzbxA = Integer.valueOf((int) zzatd.zzbqS);
        zze.zzbxr = Long.valueOf(zzatd.zzbqN);
        zze.zzbqL = zzatd.zzbqL;
        zze.zzbxw = zzatd.zzbqO == 0 ? null : Long.valueOf(zzatd.zzbqO);
        Pair<String, Boolean> zzfG = zzKm().zzfG(zzatd.packageName);
        if (!TextUtils.isEmpty((CharSequence) zzfG.first)) {
            zze.zzbxt = (String) zzfG.first;
            zze.zzbxu = (Boolean) zzfG.second;
        } else if (!zzKc().zzbL(this.mContext)) {
            String string = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (string == null) {
                zzKl().zzMa().zzj("null secure ID. appId", zzatx.zzfE(zze.zzaS));
                string = "null";
            } else if (string.isEmpty()) {
                zzKl().zzMa().zzj("empty secure ID. appId", zzatx.zzfE(zze.zzaS));
            }
            zze.zzbxD = string;
        }
        zze.zzbxo = zzKc().zzkN();
        zze.zzbb = zzKc().zzLS();
        zze.zzbxq = Integer.valueOf((int) zzKc().zzLT());
        zze.zzbxp = zzKc().zzLU();
        zze.zzbxs = null;
        zze.zzbxi = null;
        zze.zzbxj = null;
        zze.zzbxk = null;
        zze.zzbxF = Long.valueOf(zzatd.zzbqU);
        zzatc zzfu = zzKg().zzfu(zzatd.packageName);
        if (zzfu == null) {
            zzfu = new zzatc(this, zzatd.packageName);
            zzfu.zzfd(zzKm().zzMh());
            zzfu.zzfg(zzatd.zzbqT);
            zzfu.zzfe(zzatd.zzbqL);
            zzfu.zzff(zzKm().zzfH(zzatd.packageName));
            zzfu.zzad(0);
            zzfu.zzY(0);
            zzfu.zzZ(0);
            zzfu.setAppVersion(zzatd.zzbhN);
            zzfu.zzaa(zzatd.zzbqS);
            zzfu.zzfh(zzatd.zzbqM);
            zzfu.zzab(zzatd.zzbqN);
            zzfu.zzac(zzatd.zzbqO);
            zzfu.setMeasurementEnabled(zzatd.zzbqQ);
            zzfu.zzam(zzatd.zzbqU);
            zzKg().zza(zzfu);
        }
        zze.zzbxv = zzfu.getAppInstanceId();
        zze.zzbqT = zzfu.zzKq();
        List<zzaus> zzft = zzKg().zzft(zzatd.packageName);
        zze.zzbxh = new zzauw.zzg[zzft.size()];
        for (int i = 0; i < zzft.size(); i++) {
            zzauw.zzg zzg = new zzauw.zzg();
            zze.zzbxh[i] = zzg;
            zzg.name = zzft.get(i).mName;
            zzg.zzbxJ = Long.valueOf(zzft.get(i).zzbwg);
            zzKh().zza(zzg, zzft.get(i).mValue);
        }
        try {
            if (zzKg().zza(zzatm, zzKg().zza(zze), zza(zzatm))) {
                this.zzbuE = 0;
            }
        } catch (IOException e) {
            zzKl().zzLY().zze("Data loss. Failed to insert raw event metadata. appId", zzatx.zzfE(zze.zzaS), e);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zza(int i, FileChannel fileChannel) {
        zzmR();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzKl().zzLY().log("Bad chanel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                zzKl().zzLY().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            zzKl().zzLY().zzj("Failed to write to channel", e);
            return false;
        }
    }

    @WorkerThread
    public byte[] zza(@NonNull zzatq zzatq, @Size(min = 1) String str) {
        zzauw.zzd zzd;
        Bundle bundle;
        int i;
        long j;
        zzatq zzatq2 = zzatq;
        String str2 = str;
        zzob();
        zzmR();
        zzJV();
        zzac.zzw(zzatq);
        zzac.zzdr(str);
        zzauw.zzd zzd2 = new zzauw.zzd();
        zzKg().beginTransaction();
        try {
            zzatc zzfu = zzKg().zzfu(str2);
            if (zzfu == null) {
                zzKl().zzMd().zzj("Log and bundle not available. package_name", str2);
            } else if (!zzfu.zzKx()) {
                zzKl().zzMd().zzj("Log and bundle disabled. package_name", str2);
            } else {
                zzauw.zze zze = new zzauw.zze();
                zzd2.zzbxd = new zzauw.zze[]{zze};
                zze.zzbxf = 1;
                zze.zzbxn = AbstractSpiCall.ANDROID_CLIENT_TYPE;
                zze.zzaS = zzfu.zzke();
                zze.zzbqM = zzfu.zzKu();
                zze.zzbhN = zzfu.zzmZ();
                zze.zzbxA = Integer.valueOf((int) zzfu.zzKt());
                zze.zzbxr = Long.valueOf(zzfu.zzKv());
                zze.zzbqL = zzfu.getGmpAppId();
                zze.zzbxw = Long.valueOf(zzfu.zzKw());
                Pair<String, Boolean> zzfG = zzKm().zzfG(zzfu.zzke());
                if (!TextUtils.isEmpty((CharSequence) zzfG.first)) {
                    zze.zzbxt = (String) zzfG.first;
                    zze.zzbxu = (Boolean) zzfG.second;
                }
                zze.zzbxo = zzKc().zzkN();
                zze.zzbb = zzKc().zzLS();
                zze.zzbxq = Integer.valueOf((int) zzKc().zzLT());
                zze.zzbxp = zzKc().zzLU();
                zze.zzbxv = zzfu.getAppInstanceId();
                zze.zzbqT = zzfu.zzKq();
                List<zzaus> zzft = zzKg().zzft(zzfu.zzke());
                zze.zzbxh = new zzauw.zzg[zzft.size()];
                for (int i2 = 0; i2 < zzft.size(); i2++) {
                    zzauw.zzg zzg = new zzauw.zzg();
                    zze.zzbxh[i2] = zzg;
                    zzg.name = zzft.get(i2).mName;
                    zzg.zzbxJ = Long.valueOf(zzft.get(i2).zzbwg);
                    zzKh().zza(zzg, zzft.get(i2).mValue);
                }
                Bundle zzLW = zzatq2.zzbrG.zzLW();
                if ("_iap".equals(zzatq2.name)) {
                    zzLW.putLong("_c", 1);
                    zzKl().zzMd().log("Marking in-app purchase as real-time");
                    zzLW.putLong("_r", 1);
                }
                zzLW.putString("_o", zzatq2.zzbqV);
                if (zzKh().zzge(zze.zzaS)) {
                    zzKh().zza(zzLW, "_dbg", (Object) 1L);
                    zzKh().zza(zzLW, "_r", (Object) 1L);
                }
                zzatn zzQ = zzKg().zzQ(str2, zzatq2.name);
                if (zzQ == null) {
                    zzatn zzatn = r2;
                    bundle = zzLW;
                    zzd = zzd2;
                    i = 1;
                    zzatn zzatn2 = new zzatn(str2, zzatq2.name, 1, 0, zzatq2.zzbrH);
                    zzKg().zza(zzatn);
                    j = 0;
                } else {
                    bundle = zzLW;
                    zzd = zzd2;
                    i = 1;
                    long j2 = zzQ.zzbrC;
                    zzKg().zza(zzQ.zzap(zzatq2.zzbrH).zzLV());
                    j = j2;
                }
                zzatm zzatm = new zzatm(this, zzatq2.zzbqV, str2, zzatq2.name, zzatq2.zzbrH, j, bundle);
                zzauw.zzb zzb = new zzauw.zzb();
                zzauw.zzb[] zzbArr = new zzauw.zzb[i];
                int i3 = 0;
                zzbArr[0] = zzb;
                zze.zzbxg = zzbArr;
                zzb.zzbwZ = Long.valueOf(zzatm.zzaxb);
                zzb.name = zzatm.mName;
                zzb.zzbxa = Long.valueOf(zzatm.zzbry);
                zzb.zzbwY = new zzauw.zzc[zzatm.zzbrz.size()];
                Iterator<String> it = zzatm.zzbrz.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    zzauw.zzc zzc = new zzauw.zzc();
                    zzb.zzbwY[i3] = zzc;
                    zzc.name = next;
                    zzKh().zza(zzc, zzatm.zzbrz.get(next));
                    i3++;
                }
                try {
                    zze.zzbxz = zza(zzfu.zzke(), zze.zzbxh, zze.zzbxg);
                    zze.zzbxj = zzb.zzbwZ;
                    zze.zzbxk = zzb.zzbwZ;
                    long zzKs = zzfu.zzKs();
                    zze.zzbxm = zzKs != 0 ? Long.valueOf(zzKs) : null;
                    long zzKr = zzfu.zzKr();
                    if (zzKr != 0) {
                        zzKs = zzKr;
                    }
                    zze.zzbxl = zzKs != 0 ? Long.valueOf(zzKs) : null;
                    zzfu.zzKB();
                    zze.zzbxx = Integer.valueOf((int) zzfu.zzKy());
                    zze.zzbxs = Long.valueOf(zzKn().zzKv());
                    zze.zzbxi = Long.valueOf(zznR().currentTimeMillis());
                    zze.zzbxy = Boolean.TRUE;
                    zzfu.zzY(zze.zzbxj.longValue());
                    zzfu.zzZ(zze.zzbxk.longValue());
                    zzKg().zza(zzfu);
                    zzKg().setTransactionSuccessful();
                    zzKg().endTransaction();
                    zzauw.zzd zzd3 = zzd;
                    try {
                        byte[] bArr = new byte[zzd3.zzaeT()];
                        zzbxm zzag = zzbxm.zzag(bArr);
                        zzd3.zza(zzag);
                        zzag.zzaeG();
                        return zzKh().zzk(bArr);
                    } catch (IOException e) {
                        zzKl().zzLY().zze("Data loss. Failed to bundle and serialize. appId", zzatx.zzfE(str), e);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    zzKg().endTransaction();
                    throw th2;
                }
            }
            byte[] bArr2 = new byte[0];
            zzKg().endTransaction();
            return bArr2;
        } catch (Throwable th3) {
            th = th3;
            Throwable th22 = th;
            zzKg().endTransaction();
            throw th22;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzaq(long j) {
        return zzl((String) null, j);
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzatc zzatc) {
        if (TextUtils.isEmpty(zzatc.getGmpAppId())) {
            zzb(zzatc.zzke(), XMPError.BADSTREAM, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
            return;
        }
        String zzP = zzKn().zzP(zzatc.getGmpAppId(), zzatc.getAppInstanceId());
        try {
            URL url = new URL(zzP);
            zzKl().zzMe().zzj("Fetching remote configuration", zzatc.zzke());
            zzauv.zzb zzfL = zzKi().zzfL(zzatc.zzke());
            ArrayMap arrayMap = null;
            String zzfM = zzKi().zzfM(zzatc.zzke());
            if (zzfL != null && !TextUtils.isEmpty(zzfM)) {
                arrayMap = new ArrayMap();
                arrayMap.put("If-Modified-Since", zzfM);
            }
            zzMy().zza(zzatc.zzke(), url, arrayMap, new zzaty.zza() {
                public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
                    zzaue.this.zzb(str, i, th, bArr, map);
                }
            });
        } catch (MalformedURLException unused) {
            zzKl().zzLY().zze("Failed to parse config URL. Not fetching. appId", zzatx.zzfE(zzatc.zzke()), zzP);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(zzatd zzatd, long j) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        zzmR();
        zzob();
        zzatc zzfu = zzKg().zzfu(zzatd.packageName);
        if (zzfu != null && TextUtils.isEmpty(zzfu.getGmpAppId()) && zzatd != null && !TextUtils.isEmpty(zzatd.zzbqL)) {
            zzfu.zzae(0);
            zzKg().zza(zzfu);
        }
        Bundle bundle = new Bundle();
        bundle.putLong("_c", 1);
        bundle.putLong("_r", 1);
        bundle.putLong("_uwa", 0);
        bundle.putLong("_pfo", 0);
        bundle.putLong("_sys", 0);
        bundle.putLong("_sysu", 0);
        if (getContext().getPackageManager() == null) {
            zzKl().zzLY().zzj("PackageManager is null, first open report might be inaccurate. appId", zzatx.zzfE(zzatd.packageName));
        } else {
            try {
                packageInfo = zzadg.zzbi(getContext()).getPackageInfo(zzatd.packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                zzKl().zzLY().zze("Package info is null, first open report might be inaccurate. appId", zzatx.zzfE(zzatd.packageName), e);
                packageInfo = null;
            }
            if (!(packageInfo == null || packageInfo.firstInstallTime == 0 || packageInfo.firstInstallTime == packageInfo.lastUpdateTime)) {
                bundle.putLong("_uwa", 1);
            }
            try {
                applicationInfo = zzadg.zzbi(getContext()).getApplicationInfo(zzatd.packageName, 0);
            } catch (PackageManager.NameNotFoundException e2) {
                zzKl().zzLY().zze("Application info is null, first open report might be inaccurate. appId", zzatx.zzfE(zzatd.packageName), e2);
                applicationInfo = null;
            }
            if (applicationInfo != null) {
                if ((applicationInfo.flags & 1) != 0) {
                    bundle.putLong("_sys", 1);
                }
                if ((applicationInfo.flags & 128) != 0) {
                    bundle.putLong("_sysu", 1);
                }
            }
        }
        long zzfA = zzKg().zzfA(zzatd.packageName);
        if (zzfA >= 0) {
            bundle.putLong("_pfo", zzfA);
        }
        zzb(new zzatq("_f", new zzato(bundle), "auto", j), zzatd);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(zzatg zzatg, zzatd zzatd) {
        zzatx.zza zzLY;
        String str;
        Object zzfE;
        String str2;
        Object value;
        zzatx.zza zzLY2;
        String str3;
        Object zzfE2;
        String str4;
        Object obj;
        zzac.zzw(zzatg);
        zzac.zzdr(zzatg.packageName);
        zzac.zzw(zzatg.zzbqV);
        zzac.zzw(zzatg.zzbqW);
        zzac.zzdr(zzatg.zzbqW.name);
        zzmR();
        zzob();
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            zzatg zzatg2 = new zzatg(zzatg);
            zzKg().beginTransaction();
            boolean z = false;
            try {
                zzatg zzT = zzKg().zzT(zzatg2.packageName, zzatg2.zzbqW.name);
                if (zzT != null && zzT.zzbqY) {
                    zzatg2.zzbqV = zzT.zzbqV;
                    zzatg2.zzbqX = zzT.zzbqX;
                    zzatg2.zzbqZ = zzT.zzbqZ;
                    zzatg2.zzbrc = zzT.zzbrc;
                } else if (TextUtils.isEmpty(zzatg2.zzbqZ)) {
                    zzauq zzauq = zzatg2.zzbqW;
                    zzatg2.zzbqW = new zzauq(zzauq.name, zzatg2.zzbqX, zzauq.getValue(), zzauq.zzbqV);
                    zzatg2.zzbqY = true;
                    z = true;
                }
                if (zzatg2.zzbqY) {
                    zzauq zzauq2 = zzatg2.zzbqW;
                    zzaus zzaus = new zzaus(zzatg2.packageName, zzatg2.zzbqV, zzauq2.name, zzauq2.zzbwc, zzauq2.getValue());
                    if (zzKg().zza(zzaus)) {
                        zzLY2 = zzKl().zzMd();
                        str3 = "User property updated immediately";
                        zzfE2 = zzatg2.packageName;
                        str4 = zzaus.mName;
                        obj = zzaus.mValue;
                    } else {
                        zzLY2 = zzKl().zzLY();
                        str3 = "(2)Too many active user properties, ignoring";
                        zzfE2 = zzatx.zzfE(zzatg2.packageName);
                        str4 = zzaus.mName;
                        obj = zzaus.mValue;
                    }
                    zzLY2.zzd(str3, zzfE2, str4, obj);
                    if (z && zzatg2.zzbrc != null) {
                        zzc(new zzatq(zzatg2.zzbrc, zzatg2.zzbqX), zzatd);
                    }
                }
                if (zzKg().zza(zzatg2)) {
                    zzLY = zzKl().zzMd();
                    str = "Conditional property added";
                    zzfE = zzatg2.packageName;
                    str2 = zzatg2.zzbqW.name;
                    value = zzatg2.zzbqW.getValue();
                } else {
                    zzLY = zzKl().zzLY();
                    str = "Too many conditional properties, ignoring";
                    zzfE = zzatx.zzfE(zzatg2.packageName);
                    str2 = zzatg2.zzbqW.name;
                    value = zzatg2.zzbqW.getValue();
                }
                zzLY.zzd(str, zzfE, str2, value);
                zzKg().setTransactionSuccessful();
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(zzatq zzatq, zzatd zzatd) {
        zzatx.zza zzLY;
        String str;
        Object zzfE;
        String str2;
        Object obj;
        zzac.zzw(zzatd);
        zzac.zzdr(zzatd.packageName);
        zzmR();
        zzob();
        String str3 = zzatd.packageName;
        long j = zzatq.zzbrH;
        if (zzKh().zzd(zzatq, zzatd)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            zzKg().beginTransaction();
            try {
                for (zzatg next : zzKg().zzh(str3, j)) {
                    if (next != null) {
                        zzKl().zzMd().zzd("User property timed out", next.packageName, next.zzbqW.name, next.zzbqW.getValue());
                        if (next.zzbra != null) {
                            zzc(new zzatq(next.zzbra, j), zzatd);
                        }
                        zzKg().zzU(str3, next.zzbqW.name);
                    }
                }
                List<zzatg> zzi = zzKg().zzi(str3, j);
                ArrayList<zzatq> arrayList = new ArrayList<>(zzi.size());
                for (zzatg next2 : zzi) {
                    if (next2 != null) {
                        zzKl().zzMd().zzd("User property expired", next2.packageName, next2.zzbqW.name, next2.zzbqW.getValue());
                        zzKg().zzR(str3, next2.zzbqW.name);
                        if (next2.zzbre != null) {
                            arrayList.add(next2.zzbre);
                        }
                        zzKg().zzU(str3, next2.zzbqW.name);
                    }
                }
                for (zzatq zzatq2 : arrayList) {
                    zzc(new zzatq(zzatq2, j), zzatd);
                }
                List<zzatg> zzc = zzKg().zzc(str3, zzatq.name, j);
                ArrayList<zzatq> arrayList2 = new ArrayList<>(zzc.size());
                for (zzatg next3 : zzc) {
                    if (next3 != null) {
                        zzauq zzauq = next3.zzbqW;
                        zzaus zzaus = new zzaus(next3.packageName, next3.zzbqV, zzauq.name, j, zzauq.getValue());
                        if (zzKg().zza(zzaus)) {
                            zzLY = zzKl().zzMd();
                            str = "User property triggered";
                            zzfE = next3.packageName;
                            str2 = zzaus.mName;
                            obj = zzaus.mValue;
                        } else {
                            zzLY = zzKl().zzLY();
                            str = "Too many active user properties, ignoring";
                            zzfE = zzatx.zzfE(next3.packageName);
                            str2 = zzaus.mName;
                            obj = zzaus.mValue;
                        }
                        zzLY.zzd(str, zzfE, str2, obj);
                        if (next3.zzbrc != null) {
                            arrayList2.add(next3.zzbrc);
                        }
                        next3.zzbqW = new zzauq(zzaus);
                        next3.zzbqY = true;
                        zzKg().zza(next3);
                    }
                }
                zzc(zzatq, zzatd);
                for (zzatq zzatq3 : arrayList2) {
                    zzc(new zzatq(zzatq3, j), zzatd);
                }
                zzKg().setTransactionSuccessful();
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(zzatq zzatq, String str) {
        zzatq zzatq2 = zzatq;
        String str2 = str;
        zzatc zzfu = zzKg().zzfu(str2);
        if (zzfu == null || TextUtils.isEmpty(zzfu.zzmZ())) {
            zzKl().zzMd().zzj("No app data available; dropping event", str2);
            return;
        }
        try {
            String str3 = zzadg.zzbi(getContext()).getPackageInfo(str2, 0).versionName;
            if (zzfu.zzmZ() != null && !zzfu.zzmZ().equals(str3)) {
                zzKl().zzMa().zzj("App version does not match; dropping event. appId", zzatx.zzfE(str));
                return;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            if (!"_ui".equals(zzatq2.name)) {
                zzKl().zzMa().zzj("Could not find package. appId", zzatx.zzfE(str));
            }
        }
        zzatd zzatd = r1;
        zzatd zzatd2 = new zzatd(str2, zzfu.getGmpAppId(), zzfu.zzmZ(), zzfu.zzKt(), zzfu.zzKu(), zzfu.zzKv(), zzfu.zzKw(), (String) null, zzfu.zzKx(), false, zzfu.zzKq(), zzfu.zzuW());
        zzb(zzatq2, zzatd);
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzauh zzauh) {
        this.zzbuB++;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(zzauq zzauq, zzatd zzatd) {
        String zza2;
        String valueOf;
        zzmR();
        zzob();
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            int zzfX = zzKh().zzfX(zzauq.name);
            int i = 0;
            if (zzfX != 0) {
                zza2 = zzKh().zza(zzauq.name, zzKn().zzKN(), true);
                if (zzauq.name != null) {
                    valueOf = zzauq.name;
                }
                zzKh().zza(zzfX, "_ev", zza2, i);
            }
            zzfX = zzKh().zzm(zzauq.name, zzauq.getValue());
            if (zzfX != 0) {
                zza2 = zzKh().zza(zzauq.name, zzKn().zzKN(), true);
                Object value = zzauq.getValue();
                if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                    valueOf = String.valueOf(value);
                }
                zzKh().zza(zzfX, "_ev", zza2, i);
            }
            Object zzn = zzKh().zzn(zzauq.name, zzauq.getValue());
            if (zzn != null) {
                zzaus zzaus = new zzaus(zzatd.packageName, zzauq.zzbqV, zzauq.name, zzauq.zzbwc, zzn);
                zzKl().zzMd().zze("Setting user property", zzaus.mName, zzn);
                zzKg().beginTransaction();
                try {
                    zzf(zzatd);
                    boolean zza3 = zzKg().zza(zzaus);
                    zzKg().setTransactionSuccessful();
                    if (zza3) {
                        zzKl().zzMd().zze("User property set", zzaus.mName, zzaus.mValue);
                    } else {
                        zzKl().zzLY().zze("Too many unique user properties are set. Ignoring user property", zzaus.mName, zzaus.mValue);
                        zzKh().zza(9, (String) null, (String) null, 0);
                    }
                    return;
                } finally {
                    zzKg().endTransaction();
                }
            } else {
                return;
            }
            i = valueOf.length();
            zzKh().zza(zzfX, "_ev", zza2, i);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d1, code lost:
        if (zzKi().zzb(r7, r10, r11) == false) goto L_0x00d3;
     */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzb(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            r6.zzmR()
            r6.zzob()
            com.google.android.gms.common.internal.zzac.zzdr(r7)
            r0 = 0
            if (r10 != 0) goto L_0x000e
            byte[] r10 = new byte[r0]
        L_0x000e:
            com.google.android.gms.internal.zzatj r1 = r6.zzKg()
            r1.beginTransaction()
            com.google.android.gms.internal.zzatj r1 = r6.zzKg()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzatc r1 = r1.zzfu(r7)     // Catch:{ all -> 0x014a }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 1
            r4 = 304(0x130, float:4.26E-43)
            if (r8 == r2) goto L_0x002a
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x002a
            if (r8 != r4) goto L_0x002e
        L_0x002a:
            if (r9 != 0) goto L_0x002e
            r2 = r3
            goto L_0x002f
        L_0x002e:
            r2 = r0
        L_0x002f:
            if (r1 != 0) goto L_0x0044
            com.google.android.gms.internal.zzatx r8 = r6.zzKl()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzatx$zza r8 = r8.zzMa()     // Catch:{ all -> 0x014a }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzatx.zzfE(r7)     // Catch:{ all -> 0x014a }
            r8.zzj(r9, r7)     // Catch:{ all -> 0x014a }
            goto L_0x013b
        L_0x0044:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00a7
            if (r8 != r5) goto L_0x004b
            goto L_0x00a7
        L_0x004b:
            com.google.android.gms.common.util.zze r10 = r6.zznR()     // Catch:{ all -> 0x014a }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x014a }
            r1.zzaf(r10)     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzatj r10 = r6.zzKg()     // Catch:{ all -> 0x014a }
            r10.zza((com.google.android.gms.internal.zzatc) r1)     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzatx r10 = r6.zzKl()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzatx$zza r10 = r10.zzMe()     // Catch:{ all -> 0x014a }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x014a }
            r10.zze(r11, r1, r9)     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzauc r9 = r6.zzKi()     // Catch:{ all -> 0x014a }
            r9.zzfN(r7)     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzaua r7 = r6.zzKm()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzaua$zzb r7 = r7.zzbta     // Catch:{ all -> 0x014a }
            com.google.android.gms.common.util.zze r9 = r6.zznR()     // Catch:{ all -> 0x014a }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x014a }
            r7.set(r9)     // Catch:{ all -> 0x014a }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x008e
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x008f
        L_0x008e:
            r0 = r3
        L_0x008f:
            if (r0 == 0) goto L_0x00a2
            com.google.android.gms.internal.zzaua r7 = r6.zzKm()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzaua$zzb r7 = r7.zzbtb     // Catch:{ all -> 0x014a }
            com.google.android.gms.common.util.zze r8 = r6.zznR()     // Catch:{ all -> 0x014a }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x014a }
            r7.set(r8)     // Catch:{ all -> 0x014a }
        L_0x00a2:
            r6.zzMI()     // Catch:{ all -> 0x014a }
            goto L_0x013b
        L_0x00a7:
            r9 = 0
            if (r11 == 0) goto L_0x00b3
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x014a }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x014a }
            goto L_0x00b4
        L_0x00b3:
            r11 = r9
        L_0x00b4:
            if (r11 == 0) goto L_0x00c3
            int r2 = r11.size()     // Catch:{ all -> 0x014a }
            if (r2 <= 0) goto L_0x00c3
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x014a }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x014a }
            goto L_0x00c4
        L_0x00c3:
            r11 = r9
        L_0x00c4:
            if (r8 == r5) goto L_0x00db
            if (r8 != r4) goto L_0x00c9
            goto L_0x00db
        L_0x00c9:
            com.google.android.gms.internal.zzauc r9 = r6.zzKi()     // Catch:{ all -> 0x014a }
            boolean r9 = r9.zzb(r7, r10, r11)     // Catch:{ all -> 0x014a }
            if (r9 != 0) goto L_0x00f0
        L_0x00d3:
            com.google.android.gms.internal.zzatj r7 = r6.zzKg()
            r7.endTransaction()
            return
        L_0x00db:
            com.google.android.gms.internal.zzauc r11 = r6.zzKi()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzauv$zzb r11 = r11.zzfL(r7)     // Catch:{ all -> 0x014a }
            if (r11 != 0) goto L_0x00f0
            com.google.android.gms.internal.zzauc r11 = r6.zzKi()     // Catch:{ all -> 0x014a }
            boolean r9 = r11.zzb(r7, r9, r9)     // Catch:{ all -> 0x014a }
            if (r9 != 0) goto L_0x00f0
            goto L_0x00d3
        L_0x00f0:
            com.google.android.gms.common.util.zze r9 = r6.zznR()     // Catch:{ all -> 0x014a }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x014a }
            r1.zzae(r2)     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzatj r9 = r6.zzKg()     // Catch:{ all -> 0x014a }
            r9.zza((com.google.android.gms.internal.zzatc) r1)     // Catch:{ all -> 0x014a }
            if (r8 != r5) goto L_0x0112
            com.google.android.gms.internal.zzatx r8 = r6.zzKl()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzatx$zza r8 = r8.zzMb()     // Catch:{ all -> 0x014a }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zzj(r9, r7)     // Catch:{ all -> 0x014a }
            goto L_0x0128
        L_0x0112:
            com.google.android.gms.internal.zzatx r7 = r6.zzKl()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzatx$zza r7 = r7.zzMe()     // Catch:{ all -> 0x014a }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x014a }
            int r10 = r10.length     // Catch:{ all -> 0x014a }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x014a }
            r7.zze(r9, r8, r10)     // Catch:{ all -> 0x014a }
        L_0x0128:
            com.google.android.gms.internal.zzaty r7 = r6.zzMy()     // Catch:{ all -> 0x014a }
            boolean r7 = r7.zzqa()     // Catch:{ all -> 0x014a }
            if (r7 == 0) goto L_0x00a2
            boolean r7 = r6.zzMH()     // Catch:{ all -> 0x014a }
            if (r7 == 0) goto L_0x00a2
            r6.zzMG()     // Catch:{ all -> 0x014a }
        L_0x013b:
            com.google.android.gms.internal.zzatj r7 = r6.zzKg()     // Catch:{ all -> 0x014a }
            r7.setTransactionSuccessful()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzatj r7 = r6.zzKg()
            r7.endTransaction()
            return
        L_0x014a:
            r7 = move-exception
            com.google.android.gms.internal.zzatj r8 = r6.zzKg()
            r8.endTransaction()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaue.zzb(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzc(zzatd zzatd, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("_et", 1);
        zzb(new zzatq("_e", new zzato(bundle), "auto", j), zzatd);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzc(zzatg zzatg, zzatd zzatd) {
        zzac.zzw(zzatg);
        zzac.zzdr(zzatg.packageName);
        zzac.zzw(zzatg.zzbqW);
        zzac.zzdr(zzatg.zzbqW.name);
        zzmR();
        zzob();
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            zzKg().beginTransaction();
            try {
                zzf(zzatd);
                zzatg zzT = zzKg().zzT(zzatg.packageName, zzatg.zzbqW.name);
                if (zzT != null) {
                    zzKl().zzMd().zze("Removing conditional user property", zzatg.packageName, zzatg.zzbqW.name);
                    zzKg().zzU(zzatg.packageName, zzatg.zzbqW.name);
                    if (zzT.zzbqY) {
                        zzKg().zzR(zzatg.packageName, zzatg.zzbqW.name);
                    }
                    if (zzatg.zzbre != null) {
                        Bundle bundle = null;
                        if (zzatg.zzbre.zzbrG != null) {
                            bundle = zzatg.zzbre.zzbrG.zzLW();
                        }
                        Bundle bundle2 = bundle;
                        zzc(zzKh().zza(zzatg.zzbre.name, bundle2, zzT.zzbqV, zzatg.zzbre.zzbrH, true, false), zzatd);
                    }
                } else {
                    zzKl().zzMa().zze("Conditional user property doesn't exist", zzatx.zzfE(zzatg.packageName), zzatg.zzbqW.name);
                }
                zzKg().setTransactionSuccessful();
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00fa, code lost:
        if (com.google.firebase.analytics.FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(r1.name) != false) goto L_0x010b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0222 A[Catch:{ all -> 0x0244 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0242  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0290 A[Catch:{ all -> 0x047e }] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzc(com.google.android.gms.internal.zzatq r26, com.google.android.gms.internal.zzatd r27) {
        /*
            r25 = this;
            r11 = r25
            r1 = r26
            r12 = r27
            com.google.android.gms.common.internal.zzac.zzw(r27)
            java.lang.String r2 = r12.packageName
            com.google.android.gms.common.internal.zzac.zzdr(r2)
            long r13 = java.lang.System.nanoTime()
            r25.zzmR()
            r25.zzob()
            java.lang.String r15 = r12.packageName
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()
            boolean r2 = r2.zzd((com.google.android.gms.internal.zzatq) r1, (com.google.android.gms.internal.zzatd) r12)
            if (r2 != 0) goto L_0x0025
            return
        L_0x0025:
            boolean r2 = r12.zzbqQ
            if (r2 != 0) goto L_0x002d
            r11.zzf(r12)
            return
        L_0x002d:
            com.google.android.gms.internal.zzauc r2 = r25.zzKi()
            java.lang.String r3 = r1.name
            boolean r2 = r2.zzaa(r15, r3)
            r3 = 1
            r10 = 0
            if (r2 == 0) goto L_0x00c0
            com.google.android.gms.internal.zzatx r2 = r25.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMa()
            java.lang.String r4 = "Dropping blacklisted event. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r15)
            java.lang.String r6 = r1.name
            r2.zze(r4, r5, r6)
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()
            boolean r2 = r2.zzgg(r15)
            if (r2 != 0) goto L_0x0064
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()
            boolean r2 = r2.zzgh(r15)
            if (r2 == 0) goto L_0x0063
            goto L_0x0064
        L_0x0063:
            r3 = r10
        L_0x0064:
            if (r3 != 0) goto L_0x007d
            java.lang.String r2 = "_err"
            java.lang.String r4 = r1.name
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x007d
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()
            r4 = 11
            java.lang.String r5 = "_ev"
            java.lang.String r1 = r1.name
            r2.zza((int) r4, (java.lang.String) r5, (java.lang.String) r1, (int) r10)
        L_0x007d:
            if (r3 == 0) goto L_0x00bf
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()
            com.google.android.gms.internal.zzatc r1 = r1.zzfu(r15)
            if (r1 == 0) goto L_0x00bf
            long r2 = r1.zzKA()
            long r4 = r1.zzKz()
            long r2 = java.lang.Math.max(r2, r4)
            com.google.android.gms.common.util.zze r4 = r25.zznR()
            long r4 = r4.currentTimeMillis()
            long r6 = r4 - r2
            long r2 = java.lang.Math.abs(r6)
            com.google.android.gms.internal.zzati r4 = r25.zzKn()
            long r4 = r4.zzLl()
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x00bf
            com.google.android.gms.internal.zzatx r2 = r25.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMd()
            java.lang.String r3 = "Fetching config for blacklisted app"
            r2.log(r3)
            r11.zzb((com.google.android.gms.internal.zzatc) r1)
        L_0x00bf:
            return
        L_0x00c0:
            com.google.android.gms.internal.zzatx r2 = r25.zzKl()
            r9 = 2
            boolean r2 = r2.zzak(r9)
            if (r2 == 0) goto L_0x00d8
            com.google.android.gms.internal.zzatx r2 = r25.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMe()
            java.lang.String r4 = "Logging event"
            r2.zzj(r4, r1)
        L_0x00d8:
            com.google.android.gms.internal.zzatj r2 = r25.zzKg()
            r2.beginTransaction()
            com.google.android.gms.internal.zzato r2 = r1.zzbrG     // Catch:{ all -> 0x0485 }
            android.os.Bundle r8 = r2.zzLW()     // Catch:{ all -> 0x0485 }
            r11.zzf(r12)     // Catch:{ all -> 0x0485 }
            java.lang.String r2 = "_iap"
            java.lang.String r4 = r1.name     // Catch:{ all -> 0x0485 }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x0485 }
            if (r2 != 0) goto L_0x010b
            java.lang.String r2 = "ecommerce_purchase"
            java.lang.String r4 = r1.name     // Catch:{ all -> 0x0106 }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x0106 }
            if (r2 == 0) goto L_0x00fd
            goto L_0x010b
        L_0x00fd:
            r9 = r11
            r11 = 0
            r24 = r10
            r10 = r8
            r8 = r24
            goto L_0x0249
        L_0x0106:
            r0 = move-exception
            r1 = r0
            r4 = r11
            goto L_0x0488
        L_0x010b:
            java.lang.String r2 = "currency"
            java.lang.String r2 = r8.getString(r2)     // Catch:{ all -> 0x0485 }
            java.lang.String r4 = "ecommerce_purchase"
            java.lang.String r5 = r1.name     // Catch:{ all -> 0x0485 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0485 }
            if (r4 == 0) goto L_0x016c
            java.lang.String r4 = "value"
            double r4 = r8.getDouble(r4)     // Catch:{ all -> 0x0106 }
            r16 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r4 = r4 * r16
            r18 = 0
            int r7 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r7 != 0) goto L_0x0137
            java.lang.String r4 = "value"
            long r4 = r8.getLong(r4)     // Catch:{ all -> 0x0106 }
            double r4 = (double) r4     // Catch:{ all -> 0x0106 }
            double r4 = r4 * r16
        L_0x0137:
            r16 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r7 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r7 > 0) goto L_0x0148
            r16 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r7 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r7 < 0) goto L_0x0148
            long r4 = java.lang.Math.round(r4)     // Catch:{ all -> 0x0106 }
            goto L_0x0172
        L_0x0148:
            com.google.android.gms.internal.zzatx r1 = r25.zzKl()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzMa()     // Catch:{ all -> 0x0106 }
            java.lang.String r2 = "Data lost. Currency value is too big. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ all -> 0x0106 }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ all -> 0x0106 }
            r1.zze(r2, r3, r4)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()     // Catch:{ all -> 0x0106 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()
            r1.endTransaction()
            return
        L_0x016c:
            java.lang.String r4 = "value"
            long r4 = r8.getLong(r4)     // Catch:{ all -> 0x0485 }
        L_0x0172:
            boolean r7 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0485 }
            if (r7 != 0) goto L_0x00fd
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ all -> 0x0106 }
            java.lang.String r2 = r2.toUpperCase(r7)     // Catch:{ all -> 0x0106 }
            java.lang.String r7 = "[A-Z]{3}"
            boolean r7 = r2.matches(r7)     // Catch:{ all -> 0x0106 }
            if (r7 == 0) goto L_0x00fd
            java.lang.String r7 = "_ltv_"
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x0106 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0106 }
            int r16 = r2.length()     // Catch:{ all -> 0x0106 }
            if (r16 == 0) goto L_0x019c
            java.lang.String r2 = r7.concat(r2)     // Catch:{ all -> 0x0106 }
        L_0x019a:
            r7 = r2
            goto L_0x01a2
        L_0x019c:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0106 }
            r2.<init>(r7)     // Catch:{ all -> 0x0106 }
            goto L_0x019a
        L_0x01a2:
            com.google.android.gms.internal.zzatj r2 = r25.zzKg()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.internal.zzaus r2 = r2.zzS(r15, r7)     // Catch:{ all -> 0x0106 }
            if (r2 == 0) goto L_0x01e8
            java.lang.Object r6 = r2.mValue     // Catch:{ all -> 0x0106 }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ all -> 0x0106 }
            if (r6 != 0) goto L_0x01b6
            r10 = r8
            r9 = r11
            r11 = 0
            goto L_0x01ec
        L_0x01b6:
            java.lang.Object r2 = r2.mValue     // Catch:{ all -> 0x0106 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x0106 }
            long r2 = r2.longValue()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.internal.zzaus r16 = new com.google.android.gms.internal.zzaus     // Catch:{ all -> 0x0106 }
            java.lang.String r6 = r1.zzbqV     // Catch:{ all -> 0x0106 }
            com.google.android.gms.common.util.zze r9 = r25.zznR()     // Catch:{ all -> 0x0106 }
            long r17 = r9.currentTimeMillis()     // Catch:{ all -> 0x0106 }
            long r10 = r2 + r4
            java.lang.Long r9 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x01e2 }
            r2 = r16
            r3 = r15
            r4 = r6
            r5 = r7
            r11 = 0
            r6 = r17
            r10 = r8
            r8 = r9
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x01e2 }
            r2 = r16
            r9 = r25
            goto L_0x0218
        L_0x01e2:
            r0 = move-exception
            r1 = r0
            r4 = r25
            goto L_0x0488
        L_0x01e8:
            r10 = r8
            r11 = 0
            r9 = r25
        L_0x01ec:
            com.google.android.gms.internal.zzatj r2 = r25.zzKg()     // Catch:{ all -> 0x0244 }
            com.google.android.gms.internal.zzati r6 = r25.zzKn()     // Catch:{ all -> 0x0244 }
            int r6 = r6.zzfn(r15)     // Catch:{ all -> 0x0244 }
            int r6 = r6 - r3
            r2.zzz(r15, r6)     // Catch:{ all -> 0x0244 }
            com.google.android.gms.internal.zzaus r16 = new com.google.android.gms.internal.zzaus     // Catch:{ all -> 0x0244 }
            java.lang.String r6 = r1.zzbqV     // Catch:{ all -> 0x0244 }
            com.google.android.gms.common.util.zze r2 = r25.zznR()     // Catch:{ all -> 0x0244 }
            long r17 = r2.currentTimeMillis()     // Catch:{ all -> 0x0244 }
            java.lang.Long r8 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0244 }
            r2 = r16
            r3 = r15
            r4 = r6
            r5 = r7
            r6 = r17
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x0244 }
            r2 = r16
        L_0x0218:
            com.google.android.gms.internal.zzatj r3 = r25.zzKg()     // Catch:{ all -> 0x0244 }
            boolean r3 = r3.zza((com.google.android.gms.internal.zzaus) r2)     // Catch:{ all -> 0x0244 }
            if (r3 != 0) goto L_0x0242
            com.google.android.gms.internal.zzatx r3 = r25.zzKl()     // Catch:{ all -> 0x0244 }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ all -> 0x0244 }
            java.lang.String r4 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ all -> 0x0244 }
            java.lang.String r6 = r2.mName     // Catch:{ all -> 0x0244 }
            java.lang.Object r2 = r2.mValue     // Catch:{ all -> 0x0244 }
            r3.zzd(r4, r5, r6, r2)     // Catch:{ all -> 0x0244 }
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()     // Catch:{ all -> 0x0244 }
            r3 = 9
            r8 = 0
            r2.zza((int) r3, (java.lang.String) r11, (java.lang.String) r11, (int) r8)     // Catch:{ all -> 0x0244 }
            goto L_0x0249
        L_0x0242:
            r8 = 0
            goto L_0x0249
        L_0x0244:
            r0 = move-exception
            r1 = r0
            r4 = r9
            goto L_0x0488
        L_0x0249:
            java.lang.String r2 = r1.name     // Catch:{ all -> 0x0482 }
            boolean r16 = com.google.android.gms.internal.zzaut.zzfT(r2)     // Catch:{ all -> 0x0482 }
            java.lang.String r2 = "_err"
            java.lang.String r3 = r1.name     // Catch:{ all -> 0x0482 }
            boolean r17 = r2.equals(r3)     // Catch:{ all -> 0x0482 }
            com.google.android.gms.internal.zzatj r2 = r25.zzKg()     // Catch:{ all -> 0x0482 }
            long r3 = r25.zzME()     // Catch:{ all -> 0x0482 }
            r6 = 1
            r18 = 0
            r19 = 0
            r5 = r15
            r7 = r16
            r20 = r8
            r8 = r18
            r9 = r17
            r11 = r10
            r22 = r13
            r13 = r20
            r10 = r19
            com.google.android.gms.internal.zzatj$zza r2 = r2.zza(r3, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x047e }
            long r3 = r2.zzbro     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzati r5 = r25.zzKn()     // Catch:{ all -> 0x047e }
            long r5 = r5.zzKU()     // Catch:{ all -> 0x047e }
            long r7 = r3 - r5
            r3 = 0
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            r6 = 16
            r9 = 1000(0x3e8, double:4.94E-321)
            r3 = 1
            if (r5 <= 0) goto L_0x02c6
            long r7 = r7 % r9
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x02ac
            com.google.android.gms.internal.zzatx r3 = r25.zzKl()     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ all -> 0x047e }
            java.lang.String r4 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ all -> 0x047e }
            long r7 = r2.zzbro     // Catch:{ all -> 0x047e }
            java.lang.Long r2 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x047e }
            r3.zze(r4, r5, r2)     // Catch:{ all -> 0x047e }
        L_0x02ac:
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()     // Catch:{ all -> 0x047e }
            java.lang.String r3 = "_ev"
            java.lang.String r1 = r1.name     // Catch:{ all -> 0x047e }
            r2.zza((int) r6, (java.lang.String) r3, (java.lang.String) r1, (int) r13)     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()     // Catch:{ all -> 0x047e }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()
            r1.endTransaction()
            return
        L_0x02c6:
            if (r16 == 0) goto L_0x0311
            long r7 = r2.zzbrn     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzati r5 = r25.zzKn()     // Catch:{ all -> 0x047e }
            long r18 = r5.zzKV()     // Catch:{ all -> 0x047e }
            long r20 = r7 - r18
            r7 = 0
            int r5 = (r20 > r7 ? 1 : (r20 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x0311
            long r20 = r20 % r9
            int r5 = (r20 > r3 ? 1 : (r20 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x02f7
            com.google.android.gms.internal.zzatx r3 = r25.zzKl()     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ all -> 0x047e }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ all -> 0x047e }
            long r7 = r2.zzbrn     // Catch:{ all -> 0x047e }
            java.lang.Long r2 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x047e }
            r3.zze(r4, r5, r2)     // Catch:{ all -> 0x047e }
        L_0x02f7:
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()     // Catch:{ all -> 0x047e }
            java.lang.String r3 = "_ev"
            java.lang.String r1 = r1.name     // Catch:{ all -> 0x047e }
            r2.zza((int) r6, (java.lang.String) r3, (java.lang.String) r1, (int) r13)     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()     // Catch:{ all -> 0x047e }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()
            r1.endTransaction()
            return
        L_0x0311:
            if (r17 == 0) goto L_0x0352
            long r5 = r2.zzbrq     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzati r7 = r25.zzKn()     // Catch:{ all -> 0x047e }
            java.lang.String r8 = r12.packageName     // Catch:{ all -> 0x047e }
            int r7 = r7.zzfj(r8)     // Catch:{ all -> 0x047e }
            long r7 = (long) r7     // Catch:{ all -> 0x047e }
            long r9 = r5 - r7
            r5 = 0
            int r7 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0352
            int r1 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0343
            com.google.android.gms.internal.zzatx r1 = r25.zzKl()     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ all -> 0x047e }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ all -> 0x047e }
            long r5 = r2.zzbrq     // Catch:{ all -> 0x047e }
            java.lang.Long r2 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x047e }
            r1.zze(r3, r4, r2)     // Catch:{ all -> 0x047e }
        L_0x0343:
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()     // Catch:{ all -> 0x047e }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()
            r1.endTransaction()
            return
        L_0x0352:
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()     // Catch:{ all -> 0x047e }
            java.lang.String r5 = "_o"
            java.lang.String r6 = r1.zzbqV     // Catch:{ all -> 0x047e }
            r2.zza((android.os.Bundle) r11, (java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()     // Catch:{ all -> 0x047e }
            boolean r2 = r2.zzge(r15)     // Catch:{ all -> 0x047e }
            if (r2 == 0) goto L_0x0381
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()     // Catch:{ all -> 0x047e }
            java.lang.String r5 = "_dbg"
            java.lang.Long r6 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x047e }
            r2.zza((android.os.Bundle) r11, (java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzaut r2 = r25.zzKh()     // Catch:{ all -> 0x047e }
            java.lang.String r5 = "_r"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x047e }
            r2.zza((android.os.Bundle) r11, (java.lang.String) r5, (java.lang.Object) r3)     // Catch:{ all -> 0x047e }
        L_0x0381:
            com.google.android.gms.internal.zzatj r2 = r25.zzKg()     // Catch:{ all -> 0x047e }
            long r2 = r2.zzfv(r15)     // Catch:{ all -> 0x047e }
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x03a4
            com.google.android.gms.internal.zzatx r4 = r25.zzKl()     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzMa()     // Catch:{ all -> 0x047e }
            java.lang.String r5 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ all -> 0x047e }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x047e }
            r4.zze(r5, r6, r2)     // Catch:{ all -> 0x047e }
        L_0x03a4:
            com.google.android.gms.internal.zzatm r14 = new com.google.android.gms.internal.zzatm     // Catch:{ all -> 0x047e }
            java.lang.String r3 = r1.zzbqV     // Catch:{ all -> 0x047e }
            java.lang.String r5 = r1.name     // Catch:{ all -> 0x047e }
            long r6 = r1.zzbrH     // Catch:{ all -> 0x047e }
            r8 = 0
            r1 = r14
            r2 = r25
            r4 = r15
            r10 = r11
            r1.<init>((com.google.android.gms.internal.zzaue) r2, (java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (long) r6, (long) r8, (android.os.Bundle) r10)     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()     // Catch:{ all -> 0x047e }
            java.lang.String r2 = r14.mName     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatn r1 = r1.zzQ(r15, r2)     // Catch:{ all -> 0x047e }
            if (r1 != 0) goto L_0x041a
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()     // Catch:{ all -> 0x047e }
            long r1 = r1.zzfC(r15)     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzati r3 = r25.zzKn()     // Catch:{ all -> 0x047e }
            r3.zzKT()     // Catch:{ all -> 0x047e }
            r3 = 500(0x1f4, double:2.47E-321)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0408
            com.google.android.gms.internal.zzatx r1 = r25.zzKl()     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ all -> 0x047e }
            java.lang.String r2 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ all -> 0x047e }
            java.lang.String r4 = r14.mName     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzati r5 = r25.zzKn()     // Catch:{ all -> 0x047e }
            int r5 = r5.zzKT()     // Catch:{ all -> 0x047e }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x047e }
            r1.zzd(r2, r3, r4, r5)     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzaut r1 = r25.zzKh()     // Catch:{ all -> 0x047e }
            r2 = 8
            r3 = 0
            r1.zza((int) r2, (java.lang.String) r3, (java.lang.String) r3, (int) r13)     // Catch:{ all -> 0x047e }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()
            r1.endTransaction()
            return
        L_0x0408:
            com.google.android.gms.internal.zzatn r10 = new com.google.android.gms.internal.zzatn     // Catch:{ all -> 0x047e }
            java.lang.String r3 = r14.mName     // Catch:{ all -> 0x047e }
            r4 = 0
            r6 = 0
            long r8 = r14.zzaxb     // Catch:{ all -> 0x047e }
            r1 = r10
            r2 = r15
            r1.<init>(r2, r3, r4, r6, r8)     // Catch:{ all -> 0x047e }
            r4 = r25
            goto L_0x0428
        L_0x041a:
            long r2 = r1.zzbrC     // Catch:{ all -> 0x047e }
            r4 = r25
            com.google.android.gms.internal.zzatm r14 = r14.zza((com.google.android.gms.internal.zzaue) r4, (long) r2)     // Catch:{ all -> 0x047c }
            long r2 = r14.zzaxb     // Catch:{ all -> 0x047c }
            com.google.android.gms.internal.zzatn r10 = r1.zzap(r2)     // Catch:{ all -> 0x047c }
        L_0x0428:
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()     // Catch:{ all -> 0x047c }
            r1.zza((com.google.android.gms.internal.zzatn) r10)     // Catch:{ all -> 0x047c }
            r4.zza((com.google.android.gms.internal.zzatm) r14, (com.google.android.gms.internal.zzatd) r12)     // Catch:{ all -> 0x047c }
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()     // Catch:{ all -> 0x047c }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x047c }
            com.google.android.gms.internal.zzatx r1 = r25.zzKl()     // Catch:{ all -> 0x047c }
            r2 = 2
            boolean r1 = r1.zzak(r2)     // Catch:{ all -> 0x047c }
            if (r1 == 0) goto L_0x0451
            com.google.android.gms.internal.zzatx r1 = r25.zzKl()     // Catch:{ all -> 0x047c }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzMe()     // Catch:{ all -> 0x047c }
            java.lang.String r2 = "Event recorded"
            r1.zzj(r2, r14)     // Catch:{ all -> 0x047c }
        L_0x0451:
            com.google.android.gms.internal.zzatj r1 = r25.zzKg()
            r1.endTransaction()
            r25.zzMI()
            com.google.android.gms.internal.zzatx r1 = r25.zzKl()
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzMe()
            java.lang.String r2 = "Background event processing time, ms"
            long r5 = java.lang.System.nanoTime()
            long r7 = r5 - r22
            r5 = 500000(0x7a120, double:2.47033E-318)
            long r9 = r7 + r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r9 / r5
            java.lang.Long r3 = java.lang.Long.valueOf(r9)
            r1.zzj(r2, r3)
            return
        L_0x047c:
            r0 = move-exception
            goto L_0x0487
        L_0x047e:
            r0 = move-exception
            r4 = r25
            goto L_0x0487
        L_0x0482:
            r0 = move-exception
            r4 = r9
            goto L_0x0487
        L_0x0485:
            r0 = move-exception
            r4 = r11
        L_0x0487:
            r1 = r0
        L_0x0488:
            com.google.android.gms.internal.zzatj r2 = r25.zzKg()
            r2.endTransaction()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaue.zzc(com.google.android.gms.internal.zzatq, com.google.android.gms.internal.zzatd):void");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzc(zzauq zzauq, zzatd zzatd) {
        zzmR();
        zzob();
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            zzKl().zzMd().zzj("Removing user property", zzauq.name);
            zzKg().beginTransaction();
            try {
                zzf(zzatd);
                zzKg().zzR(zzatd.packageName, zzauq.name);
                zzKg().setTransactionSuccessful();
                zzKl().zzMd().zzj("User property removed", zzauq.name);
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzd(zzatd zzatd) {
        zzmR();
        zzob();
        zzac.zzdr(zzatd.packageName);
        zzf(zzatd);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzd(zzatd zzatd, long j) {
        zzb(new zzatq("_cd", new zzato(new Bundle()), "auto", j), zzatd);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzd(zzatg zzatg) {
        zzatd zzfO = zzfO(zzatg.packageName);
        if (zzfO != null) {
            zzb(zzatg, zzfO);
        }
    }

    @WorkerThread
    public void zze(zzatd zzatd) {
        zzmR();
        zzob();
        zzac.zzw(zzatd);
        zzac.zzdr(zzatd.packageName);
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            long currentTimeMillis = zznR().currentTimeMillis();
            zzKg().beginTransaction();
            try {
                zza(zzatd, currentTimeMillis);
                zzf(zzatd);
                if (zzKg().zzQ(zzatd.packageName, "_f") == null) {
                    zzb(new zzauq("_fot", currentTimeMillis, Long.valueOf((1 + (currentTimeMillis / 3600000)) * 3600000), "auto"), zzatd);
                    zzb(zzatd, currentTimeMillis);
                    zzc(zzatd, currentTimeMillis);
                } else if (zzatd.zzbqR) {
                    zzd(zzatd, currentTimeMillis);
                }
                zzKg().setTransactionSuccessful();
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zze(zzatg zzatg) {
        zzatd zzfO = zzfO(zzatg.packageName);
        if (zzfO != null) {
            zzc(zzatg, zzfO);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public zzatd zzfO(String str) {
        String str2 = str;
        zzatc zzfu = zzKg().zzfu(str2);
        if (zzfu == null || TextUtils.isEmpty(zzfu.zzmZ())) {
            zzKl().zzMd().zzj("No app data available; dropping", str2);
            return null;
        }
        try {
            String str3 = zzadg.zzbi(getContext()).getPackageInfo(str2, 0).versionName;
            if (zzfu.zzmZ() != null && !zzfu.zzmZ().equals(str3)) {
                zzKl().zzMa().zzj("App version does not match; dropping. appId", zzatx.zzfE(str));
                return null;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return new zzatd(str2, zzfu.getGmpAppId(), zzfu.zzmZ(), zzfu.zzKt(), zzfu.zzKu(), zzfu.zzKv(), zzfu.zzKw(), (String) null, zzfu.zzKx(), false, zzfu.zzKq(), zzfu.zzuW());
    }

    public String zzfP(final String str) {
        try {
            return (String) zzKk().zzd(new Callable<String>() {
                /* renamed from: zzbY */
                public String call() throws Exception {
                    zzatc zzfu = zzaue.this.zzKg().zzfu(str);
                    if (zzfu == null) {
                        return null;
                    }
                    return zzfu.getAppInstanceId();
                }
            }).get(DashMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_FIXED_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzKl().zzLY().zze("Failed to get app instance id. appId", zzatx.zzfE(str), e);
            return null;
        }
    }

    @WorkerThread
    public void zzmR() {
        zzKk().zzmR();
    }

    public zze zznR() {
        return this.zzuP;
    }

    /* access modifiers changed from: package-private */
    public void zzob() {
        if (!this.zzadP) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzy(int i, int i2) {
        zzatx.zza zzLY;
        String str;
        zzmR();
        if (i > i2) {
            zzLY = zzKl().zzLY();
            str = "Panic: can't downgrade version. Previous, current version";
        } else if (i >= i2) {
            return true;
        } else {
            if (zza(i2, zzMB())) {
                zzKl().zzMe().zze("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
                return true;
            }
            zzLY = zzKl().zzLY();
            str = "Storage version upgrade failed. Previous, current version";
        }
        zzLY.zze(str, Integer.valueOf(i), Integer.valueOf(i2));
        return false;
    }
}
