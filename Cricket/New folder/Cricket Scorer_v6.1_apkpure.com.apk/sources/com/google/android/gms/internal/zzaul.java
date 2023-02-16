package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzatx;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class zzaul extends zzauh {
    /* access modifiers changed from: private */
    public final zza zzbvA;
    /* access modifiers changed from: private */
    public zzatt zzbvB;
    private Boolean zzbvC;
    private final zzatk zzbvD;
    private final zzauo zzbvE;
    private final List<Runnable> zzbvF = new ArrayList();
    private final zzatk zzbvG;

    protected class zza implements ServiceConnection, zzf.zzb, zzf.zzc {
        /* access modifiers changed from: private */
        public volatile boolean zzbvO;
        private volatile zzatw zzbvP;

        protected zza() {
        }

        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0022 */
        @android.support.annotation.MainThread
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onConnected(@android.support.annotation.Nullable android.os.Bundle r4) {
            /*
                r3 = this;
                java.lang.String r4 = "MeasurementServiceConnection.onConnected"
                com.google.android.gms.common.internal.zzac.zzdj(r4)
                monitor-enter(r3)
                r4 = 0
                com.google.android.gms.internal.zzatw r0 = r3.zzbvP     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
                android.os.IInterface r0 = r0.zzxD()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
                com.google.android.gms.internal.zzatt r0 = (com.google.android.gms.internal.zzatt) r0     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
                r3.zzbvP = r4     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
                com.google.android.gms.internal.zzaul r1 = com.google.android.gms.internal.zzaul.this     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
                com.google.android.gms.internal.zzaud r1 = r1.zzKk()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
                com.google.android.gms.internal.zzaul$zza$3 r2 = new com.google.android.gms.internal.zzaul$zza$3     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
                r2.<init>(r0)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
                r1.zzm(r2)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
                goto L_0x0027
            L_0x0020:
                r4 = move-exception
                goto L_0x0029
            L_0x0022:
                r3.zzbvP = r4     // Catch:{ all -> 0x0020 }
                r4 = 0
                r3.zzbvO = r4     // Catch:{ all -> 0x0020 }
            L_0x0027:
                monitor-exit(r3)     // Catch:{ all -> 0x0020 }
                return
            L_0x0029:
                monitor-exit(r3)     // Catch:{ all -> 0x0020 }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaul.zza.onConnected(android.os.Bundle):void");
        }

        @MainThread
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzac.zzdj("MeasurementServiceConnection.onConnectionFailed");
            zzatx zzMu = zzaul.this.zzbqc.zzMu();
            if (zzMu != null) {
                zzMu.zzMa().zzj("Service connection failed", connectionResult);
            }
            synchronized (this) {
                this.zzbvO = false;
                this.zzbvP = null;
            }
        }

        @MainThread
        public void onConnectionSuspended(int i) {
            zzac.zzdj("MeasurementServiceConnection.onConnectionSuspended");
            zzaul.this.zzKl().zzMd().log("Service connection suspended");
            zzaul.this.zzKk().zzm(new Runnable() {
                public void run() {
                    zzaul zzaul = zzaul.this;
                    Context context = zzaul.this.getContext();
                    zzaul.this.zzKn().zzLg();
                    zzaul.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
                }
            });
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|15|21|22) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0051 */
        @android.support.annotation.MainThread
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onServiceConnected(android.content.ComponentName r4, android.os.IBinder r5) {
            /*
                r3 = this;
                java.lang.String r4 = "MeasurementServiceConnection.onServiceConnected"
                com.google.android.gms.common.internal.zzac.zzdj(r4)
                monitor-enter(r3)
                r4 = 0
                if (r5 != 0) goto L_0x001e
                r3.zzbvO = r4     // Catch:{ all -> 0x001c }
                com.google.android.gms.internal.zzaul r4 = com.google.android.gms.internal.zzaul.this     // Catch:{ all -> 0x001c }
                com.google.android.gms.internal.zzatx r4 = r4.zzKl()     // Catch:{ all -> 0x001c }
                com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x001c }
                java.lang.String r5 = "Service connected with null binder"
                r4.log(r5)     // Catch:{ all -> 0x001c }
                monitor-exit(r3)     // Catch:{ all -> 0x001c }
                return
            L_0x001c:
                r4 = move-exception
                goto L_0x0088
            L_0x001e:
                r0 = 0
                java.lang.String r1 = r5.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x0050 }
                java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
                boolean r2 = r2.equals(r1)     // Catch:{ RemoteException -> 0x0050 }
                if (r2 == 0) goto L_0x003f
                com.google.android.gms.internal.zzatt r5 = com.google.android.gms.internal.zzatt.zza.zzes(r5)     // Catch:{ RemoteException -> 0x0050 }
                com.google.android.gms.internal.zzaul r0 = com.google.android.gms.internal.zzaul.this     // Catch:{ RemoteException -> 0x0051 }
                com.google.android.gms.internal.zzatx r0 = r0.zzKl()     // Catch:{ RemoteException -> 0x0051 }
                com.google.android.gms.internal.zzatx$zza r0 = r0.zzMe()     // Catch:{ RemoteException -> 0x0051 }
                java.lang.String r1 = "Bound to IMeasurementService interface"
                r0.log(r1)     // Catch:{ RemoteException -> 0x0051 }
                goto L_0x0060
            L_0x003f:
                com.google.android.gms.internal.zzaul r5 = com.google.android.gms.internal.zzaul.this     // Catch:{ RemoteException -> 0x0050 }
                com.google.android.gms.internal.zzatx r5 = r5.zzKl()     // Catch:{ RemoteException -> 0x0050 }
                com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()     // Catch:{ RemoteException -> 0x0050 }
                java.lang.String r2 = "Got binder with a wrong descriptor"
                r5.zzj(r2, r1)     // Catch:{ RemoteException -> 0x0050 }
                r5 = r0
                goto L_0x0060
            L_0x0050:
                r5 = r0
            L_0x0051:
                com.google.android.gms.internal.zzaul r0 = com.google.android.gms.internal.zzaul.this     // Catch:{ all -> 0x001c }
                com.google.android.gms.internal.zzatx r0 = r0.zzKl()     // Catch:{ all -> 0x001c }
                com.google.android.gms.internal.zzatx$zza r0 = r0.zzLY()     // Catch:{ all -> 0x001c }
                java.lang.String r1 = "Service connect failed to get IMeasurementService"
                r0.log(r1)     // Catch:{ all -> 0x001c }
            L_0x0060:
                if (r5 != 0) goto L_0x0078
                r3.zzbvO = r4     // Catch:{ all -> 0x001c }
                com.google.android.gms.common.stats.zza r4 = com.google.android.gms.common.stats.zza.zzyJ()     // Catch:{ IllegalArgumentException -> 0x0086 }
                com.google.android.gms.internal.zzaul r5 = com.google.android.gms.internal.zzaul.this     // Catch:{ IllegalArgumentException -> 0x0086 }
                android.content.Context r5 = r5.getContext()     // Catch:{ IllegalArgumentException -> 0x0086 }
                com.google.android.gms.internal.zzaul r0 = com.google.android.gms.internal.zzaul.this     // Catch:{ IllegalArgumentException -> 0x0086 }
                com.google.android.gms.internal.zzaul$zza r0 = r0.zzbvA     // Catch:{ IllegalArgumentException -> 0x0086 }
                r4.zza(r5, r0)     // Catch:{ IllegalArgumentException -> 0x0086 }
                goto L_0x0086
            L_0x0078:
                com.google.android.gms.internal.zzaul r4 = com.google.android.gms.internal.zzaul.this     // Catch:{ all -> 0x001c }
                com.google.android.gms.internal.zzaud r4 = r4.zzKk()     // Catch:{ all -> 0x001c }
                com.google.android.gms.internal.zzaul$zza$1 r0 = new com.google.android.gms.internal.zzaul$zza$1     // Catch:{ all -> 0x001c }
                r0.<init>(r5)     // Catch:{ all -> 0x001c }
                r4.zzm(r0)     // Catch:{ all -> 0x001c }
            L_0x0086:
                monitor-exit(r3)     // Catch:{ all -> 0x001c }
                return
            L_0x0088:
                monitor-exit(r3)     // Catch:{ all -> 0x001c }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaul.zza.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        @MainThread
        public void onServiceDisconnected(final ComponentName componentName) {
            zzac.zzdj("MeasurementServiceConnection.onServiceDisconnected");
            zzaul.this.zzKl().zzMd().log("Service disconnected");
            zzaul.this.zzKk().zzm(new Runnable() {
                public void run() {
                    zzaul.this.onServiceDisconnected(componentName);
                }
            });
        }

        @WorkerThread
        public void zzNb() {
            zzaul.this.zzmR();
            Context context = zzaul.this.getContext();
            synchronized (this) {
                if (this.zzbvO) {
                    zzaul.this.zzKl().zzMe().log("Connection attempt already in progress");
                } else if (this.zzbvP != null) {
                    zzaul.this.zzKl().zzMe().log("Already awaiting connection attempt");
                } else {
                    this.zzbvP = new zzatw(context, Looper.getMainLooper(), this, this);
                    zzaul.this.zzKl().zzMe().log("Connecting to remote service");
                    this.zzbvO = true;
                    this.zzbvP.zzxz();
                }
            }
        }

        @WorkerThread
        public void zzz(Intent intent) {
            zzaul.this.zzmR();
            Context context = zzaul.this.getContext();
            com.google.android.gms.common.stats.zza zzyJ = com.google.android.gms.common.stats.zza.zzyJ();
            synchronized (this) {
                if (this.zzbvO) {
                    zzaul.this.zzKl().zzMe().log("Connection attempt already in progress");
                    return;
                }
                this.zzbvO = true;
                zzyJ.zza(context, intent, (ServiceConnection) zzaul.this.zzbvA, (int) TsExtractor.TS_STREAM_TYPE_AC3);
            }
        }
    }

    protected zzaul(zzaue zzaue) {
        super(zzaue);
        this.zzbvE = new zzauo(zzaue.zznR());
        this.zzbvA = new zza();
        this.zzbvD = new zzatk(zzaue) {
            public void run() {
                zzaul.this.zzop();
            }
        };
        this.zzbvG = new zzatk(zzaue) {
            public void run() {
                zzaul.this.zzKl().zzMa().log("Tasks have been queued for a long time");
            }
        };
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void onServiceDisconnected(ComponentName componentName) {
        zzmR();
        if (this.zzbvB != null) {
            this.zzbvB = null;
            zzKl().zzMe().zzj("Disconnected from device MeasurementService", componentName);
            zzMZ();
        }
    }

    private boolean zzMX() {
        zzKn().zzLg();
        List<ResolveInfo> queryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        return queryIntentServices != null && queryIntentServices.size() > 0;
    }

    @WorkerThread
    private void zzMZ() {
        zzmR();
        zzoD();
    }

    @WorkerThread
    private void zzNa() {
        zzmR();
        zzKl().zzMe().zzj("Processing queued up service tasks", Integer.valueOf(this.zzbvF.size()));
        for (Runnable zzm : this.zzbvF) {
            zzKk().zzm(zzm);
        }
        this.zzbvF.clear();
        this.zzbvG.cancel();
    }

    @WorkerThread
    private void zzo(Runnable runnable) throws IllegalStateException {
        zzmR();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzbvF.size()) >= zzKn().zzLm()) {
            zzKl().zzLY().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzbvF.add(runnable);
            this.zzbvG.zzy(ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
            zzoD();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzoo() {
        zzmR();
        this.zzbvE.start();
        this.zzbvD.zzy(zzKn().zzpq());
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzop() {
        zzmR();
        if (isConnected()) {
            zzKl().zzMe().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    public void disconnect() {
        zzmR();
        zzob();
        try {
            com.google.android.gms.common.stats.zza.zzyJ().zza(getContext(), this.zzbvA);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzbvB = null;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public boolean isConnected() {
        zzmR();
        zzob();
        return this.zzbvB != null;
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

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzMR() {
        zzmR();
        zzob();
        zzo(new Runnable() {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Discarding data. Failed to send app launch");
                    return;
                }
                try {
                    zzc.zza(zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                    zzaul.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) null);
                    zzaul.this.zzoo();
                } catch (RemoteException e) {
                    zzaul.this.zzKl().zzLY().zzj("Failed to send app launch to the service", e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzMW() {
        zzmR();
        zzob();
        zzo(new Runnable() {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Failed to send measurementEnabled to service");
                    return;
                }
                try {
                    zzc.zzb(zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                    zzaul.this.zzoo();
                } catch (RemoteException e) {
                    zzaul.this.zzKl().zzLY().zzj("Failed to send measurementEnabled to the service", e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzMY() {
        zzatx.zza zzMa;
        String str;
        zzatx.zza zzMa2;
        String str2;
        zzmR();
        zzob();
        zzKn().zzLg();
        zzKl().zzMe().log("Checking service availability");
        int isGooglePlayServicesAvailable = zze.zzuY().isGooglePlayServicesAvailable(getContext());
        if (isGooglePlayServicesAvailable != 9) {
            if (isGooglePlayServicesAvailable != 18) {
                switch (isGooglePlayServicesAvailable) {
                    case 0:
                        zzMa2 = zzKl().zzMe();
                        str2 = "Service available";
                        break;
                    case 1:
                        zzMa = zzKl().zzMe();
                        str = "Service missing";
                        break;
                    case 2:
                        zzMa2 = zzKl().zzMd();
                        str2 = "Service container out of date";
                        break;
                    case 3:
                        zzMa = zzKl().zzMa();
                        str = "Service disabled";
                        break;
                    default:
                        return false;
                }
            } else {
                zzMa2 = zzKl().zzMa();
                str2 = "Service updating";
            }
            zzMa2.log(str2);
            return true;
        }
        zzMa = zzKl().zzMa();
        str = "Service invalid";
        zzMa.log(str);
        return false;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(zzatt zzatt) {
        zzmR();
        zzac.zzw(zzatt);
        this.zzbvB = zzatt;
        zzoo();
        zzNa();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zza(zzatt zzatt, com.google.android.gms.common.internal.safeparcel.zza zza2) {
        zzatx.zza zzLY;
        String str;
        zzmR();
        zzJW();
        zzob();
        int i = Build.VERSION.SDK_INT;
        zzKn().zzLg();
        ArrayList<com.google.android.gms.common.internal.safeparcel.zza> arrayList = new ArrayList<>();
        zzKn().zzLp();
        int i2 = 100;
        for (int i3 = 0; i3 < 1001 && i2 == 100; i3++) {
            List<com.google.android.gms.common.internal.safeparcel.zza> zzlD = zzKf().zzlD(100);
            if (zzlD != null) {
                arrayList.addAll(zzlD);
                i2 = zzlD.size();
            } else {
                i2 = 0;
            }
            if (zza2 != null && i2 < 100) {
                arrayList.add(zza2);
            }
            for (com.google.android.gms.common.internal.safeparcel.zza zza3 : arrayList) {
                if (zza3 instanceof zzatq) {
                    try {
                        zzatt.zza((zzatq) zza3, zzKb().zzfD(zzKl().zzMf()));
                    } catch (RemoteException e) {
                        e = e;
                        zzLY = zzKl().zzLY();
                        str = "Failed to send event to the service";
                    }
                } else if (zza3 instanceof zzauq) {
                    try {
                        zzatt.zza((zzauq) zza3, zzKb().zzfD(zzKl().zzMf()));
                    } catch (RemoteException e2) {
                        e = e2;
                        zzLY = zzKl().zzLY();
                        str = "Failed to send attribute to the service";
                    }
                } else if (zza3 instanceof zzatg) {
                    try {
                        zzatt.zza((zzatg) zza3, zzKb().zzfD(zzKl().zzMf()));
                    } catch (RemoteException e3) {
                        e = e3;
                        zzLY = zzKl().zzLY();
                        str = "Failed to send conditional property to the service";
                    }
                } else {
                    zzKl().zzLY().log("Discarding data. Unrecognized parcel type.");
                }
            }
        }
        return;
        zzLY.zzj(str, e);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(final AppMeasurement.zzf zzf) {
        zzmR();
        zzob();
        zzo(new Runnable() {
            public void run() {
                long j;
                String str;
                String str2;
                String packageName;
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Failed to send current screen to service");
                    return;
                }
                try {
                    if (zzf == null) {
                        j = 0;
                        str = null;
                        str2 = null;
                        packageName = zzaul.this.getContext().getPackageName();
                    } else {
                        j = zzf.zzbqh;
                        str = zzf.zzbqf;
                        str2 = zzf.zzbqg;
                        packageName = zzaul.this.getContext().getPackageName();
                    }
                    zzc.zza(j, str, str2, packageName);
                    zzaul.this.zzoo();
                } catch (RemoteException e) {
                    zzaul.this.zzKl().zzLY().zzj("Failed to send current screen to the service", e);
                }
            }
        });
    }

    @WorkerThread
    public void zza(final AtomicReference<String> atomicReference) {
        zzmR();
        zzob();
        zzo(new Runnable() {
            public void run() {
                AtomicReference atomicReference;
                synchronized (atomicReference) {
                    try {
                        zzatt zzc = zzaul.this.zzbvB;
                        if (zzc == null) {
                            zzaul.this.zzKl().zzLY().log("Failed to get app instance id");
                            atomicReference.notify();
                            return;
                        }
                        atomicReference.set(zzc.zzc(zzaul.this.zzKb().zzfD((String) null)));
                        zzaul.this.zzoo();
                        atomicReference = atomicReference;
                        atomicReference.notify();
                    } catch (RemoteException e) {
                        try {
                            zzaul.this.zzKl().zzLY().zzj("Failed to get app instance id", e);
                            atomicReference = atomicReference;
                        } catch (Throwable th) {
                            atomicReference.notify();
                            throw th;
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(AtomicReference<List<zzatg>> atomicReference, String str, String str2, String str3) {
        zzmR();
        zzob();
        final AtomicReference<List<zzatg>> atomicReference2 = atomicReference;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        zzo(new Runnable() {
            public void run() {
                AtomicReference atomicReference;
                AtomicReference atomicReference2;
                List<zzatg> zzn;
                synchronized (atomicReference2) {
                    try {
                        zzatt zzc = zzaul.this.zzbvB;
                        if (zzc == null) {
                            zzaul.this.zzKl().zzLY().zzd("Failed to get conditional properties", zzatx.zzfE(str4), str5, str6);
                            atomicReference2.set(Collections.emptyList());
                            atomicReference2.notify();
                            return;
                        }
                        if (TextUtils.isEmpty(str4)) {
                            atomicReference2 = atomicReference2;
                            zzn = zzc.zza(str5, str6, zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                        } else {
                            atomicReference2 = atomicReference2;
                            zzn = zzc.zzn(str4, str5, str6);
                        }
                        atomicReference2.set(zzn);
                        zzaul.this.zzoo();
                        atomicReference = atomicReference2;
                        atomicReference.notify();
                    } catch (RemoteException e) {
                        try {
                            zzaul.this.zzKl().zzLY().zzd("Failed to get conditional properties", zzatx.zzfE(str4), str5, e);
                            atomicReference2.set(Collections.emptyList());
                            atomicReference = atomicReference2;
                        } catch (Throwable th) {
                            atomicReference2.notify();
                            throw th;
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(AtomicReference<List<zzauq>> atomicReference, String str, String str2, String str3, boolean z) {
        zzmR();
        zzob();
        final AtomicReference<List<zzauq>> atomicReference2 = atomicReference;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final boolean z2 = z;
        zzo(new Runnable() {
            public void run() {
                AtomicReference atomicReference;
                AtomicReference atomicReference2;
                List<zzauq> zza;
                synchronized (atomicReference2) {
                    try {
                        zzatt zzc = zzaul.this.zzbvB;
                        if (zzc == null) {
                            zzaul.this.zzKl().zzLY().zzd("Failed to get user properties", zzatx.zzfE(str4), str5, str6);
                            atomicReference2.set(Collections.emptyList());
                            atomicReference2.notify();
                            return;
                        }
                        if (TextUtils.isEmpty(str4)) {
                            atomicReference2 = atomicReference2;
                            zza = zzc.zza(str5, str6, z2, zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                        } else {
                            atomicReference2 = atomicReference2;
                            zza = zzc.zza(str4, str5, str6, z2);
                        }
                        atomicReference2.set(zza);
                        zzaul.this.zzoo();
                        atomicReference = atomicReference2;
                        atomicReference.notify();
                    } catch (RemoteException e) {
                        try {
                            zzaul.this.zzKl().zzLY().zzd("Failed to get user properties", zzatx.zzfE(str4), str5, e);
                            atomicReference2.set(Collections.emptyList());
                            atomicReference = atomicReference2;
                        } catch (Throwable th) {
                            atomicReference2.notify();
                            throw th;
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(final AtomicReference<List<zzauq>> atomicReference, final boolean z) {
        zzmR();
        zzob();
        zzo(new Runnable() {
            public void run() {
                AtomicReference atomicReference;
                synchronized (atomicReference) {
                    try {
                        zzatt zzc = zzaul.this.zzbvB;
                        if (zzc == null) {
                            zzaul.this.zzKl().zzLY().log("Failed to get user properties");
                            atomicReference.notify();
                            return;
                        }
                        atomicReference.set(zzc.zza(zzaul.this.zzKb().zzfD((String) null), z));
                        zzaul.this.zzoo();
                        atomicReference = atomicReference;
                        atomicReference.notify();
                    } catch (RemoteException e) {
                        try {
                            zzaul.this.zzKl().zzLY().zzj("Failed to get user properties", e);
                            atomicReference = atomicReference;
                        } catch (Throwable th) {
                            atomicReference.notify();
                            throw th;
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzb(final zzauq zzauq) {
        zzmR();
        zzob();
        int i = Build.VERSION.SDK_INT;
        zzKn().zzLg();
        final boolean zza2 = zzKf().zza(zzauq);
        zzo(new Runnable() {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Discarding data. Failed to set user attribute");
                    return;
                }
                zzaul.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) zza2 ? null : zzauq);
                zzaul.this.zzoo();
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzc(zzatq zzatq, String str) {
        zzac.zzw(zzatq);
        zzmR();
        zzob();
        int i = Build.VERSION.SDK_INT;
        zzKn().zzLg();
        final boolean zza2 = zzKf().zza(zzatq);
        final zzatq zzatq2 = zzatq;
        final String str2 = str;
        zzo(new Runnable(true) {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Discarding data. Failed to send event to service");
                    return;
                }
                if (true) {
                    zzaul.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) zza2 ? null : zzatq2);
                } else {
                    try {
                        if (TextUtils.isEmpty(str2)) {
                            zzc.zza(zzatq2, zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                        } else {
                            zzc.zza(zzatq2, str2, zzaul.this.zzKl().zzMf());
                        }
                    } catch (RemoteException e) {
                        zzaul.this.zzKl().zzLY().zzj("Failed to send event to the service", e);
                    }
                }
                zzaul.this.zzoo();
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzf(zzatg zzatg) {
        zzac.zzw(zzatg);
        zzmR();
        zzob();
        zzKn().zzLg();
        final boolean zzc = zzKf().zzc(zzatg);
        final zzatg zzatg2 = new zzatg(zzatg);
        final zzatg zzatg3 = zzatg;
        zzo(new Runnable(true) {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Discarding data. Failed to send conditional user property to service");
                    return;
                }
                if (true) {
                    zzaul.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) zzc ? null : zzatg2);
                } else {
                    try {
                        if (TextUtils.isEmpty(zzatg3.packageName)) {
                            zzc.zza(zzatg2, zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                        } else {
                            zzc.zzb(zzatg2);
                        }
                    } catch (RemoteException e) {
                        zzaul.this.zzKl().zzLY().zzj("Failed to send conditional user property to the service", e);
                    }
                }
                zzaul.this.zzoo();
            }
        });
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public /* bridge */ /* synthetic */ com.google.android.gms.common.util.zze zznR() {
        return super.zznR();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzoD() {
        zzmR();
        zzob();
        if (!isConnected()) {
            if (this.zzbvC == null) {
                this.zzbvC = zzKm().zzMm();
                if (this.zzbvC == null) {
                    zzKl().zzMe().log("State of service unknown");
                    this.zzbvC = Boolean.valueOf(zzMY());
                    zzKm().zzaK(this.zzbvC.booleanValue());
                }
            }
            if (this.zzbvC.booleanValue()) {
                zzKl().zzMe().log("Using measurement service");
                this.zzbvA.zzNb();
            } else if (zzMX()) {
                zzKl().zzMe().log("Using local app measurement service");
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                Context context = getContext();
                zzKn().zzLg();
                intent.setComponent(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
                this.zzbvA.zzz(intent);
            } else {
                zzKl().zzLY().log("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }
}
