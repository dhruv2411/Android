package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import java.util.Collections;

public class zzsf extends zzsa {
    /* access modifiers changed from: private */
    public final zza zzael = new zza();
    private zzta zzaem;
    private final zzsr zzaen;
    private zztj zzaeo;

    protected class zza implements ServiceConnection {
        private volatile zzta zzaeq;
        private volatile boolean zzaer;

        protected zza() {
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(9:14|15|16|17|(2:25|26)(3:27|28|(1:30)(1:31))|32|33|34|35) */
        /* JADX WARNING: Can't wrap try/catch for region: R(9:20|21|22|23|(0)(0)|32|33|34|35) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x003a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0073 */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x0043 A[SYNTHETIC, Splitter:B:25:0x0043] */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0057 A[SYNTHETIC, Splitter:B:27:0x0057] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onServiceConnected(android.content.ComponentName r3, android.os.IBinder r4) {
            /*
                r2 = this;
                java.lang.String r3 = "AnalyticsServiceConnection.onServiceConnected"
                com.google.android.gms.common.internal.zzac.zzdj(r3)
                monitor-enter(r2)
                if (r4 != 0) goto L_0x0016
                com.google.android.gms.internal.zzsf r3 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0014 }
                java.lang.String r4 = "Service connected with null binder"
                r3.zzbT(r4)     // Catch:{ all -> 0x0014 }
                r2.notifyAll()     // Catch:{ all -> 0x007c }
                monitor-exit(r2)     // Catch:{ all -> 0x007c }
                return
            L_0x0014:
                r3 = move-exception
                goto L_0x0078
            L_0x0016:
                r3 = 0
                java.lang.String r0 = r4.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x003a }
                java.lang.String r1 = "com.google.android.gms.analytics.internal.IAnalyticsService"
                boolean r1 = r1.equals(r0)     // Catch:{ RemoteException -> 0x003a }
                if (r1 == 0) goto L_0x0032
                com.google.android.gms.internal.zzta r4 = com.google.android.gms.internal.zzta.zza.zzam(r4)     // Catch:{ RemoteException -> 0x003a }
                com.google.android.gms.internal.zzsf r3 = com.google.android.gms.internal.zzsf.this     // Catch:{ RemoteException -> 0x0030 }
                java.lang.String r0 = "Bound to IAnalyticsService interface"
                r3.zzbP(r0)     // Catch:{ RemoteException -> 0x0030 }
                r3 = r4
                goto L_0x0041
            L_0x0030:
                r3 = r4
                goto L_0x003a
            L_0x0032:
                com.google.android.gms.internal.zzsf r4 = com.google.android.gms.internal.zzsf.this     // Catch:{ RemoteException -> 0x003a }
                java.lang.String r1 = "Got binder with a wrong descriptor"
                r4.zze(r1, r0)     // Catch:{ RemoteException -> 0x003a }
                goto L_0x0041
            L_0x003a:
                com.google.android.gms.internal.zzsf r4 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0014 }
                java.lang.String r0 = "Service connect failed to get IAnalyticsService"
                r4.zzbT(r0)     // Catch:{ all -> 0x0014 }
            L_0x0041:
                if (r3 != 0) goto L_0x0057
                com.google.android.gms.common.stats.zza r3 = com.google.android.gms.common.stats.zza.zzyJ()     // Catch:{ IllegalArgumentException -> 0x0073 }
                com.google.android.gms.internal.zzsf r4 = com.google.android.gms.internal.zzsf.this     // Catch:{ IllegalArgumentException -> 0x0073 }
                android.content.Context r4 = r4.getContext()     // Catch:{ IllegalArgumentException -> 0x0073 }
                com.google.android.gms.internal.zzsf r0 = com.google.android.gms.internal.zzsf.this     // Catch:{ IllegalArgumentException -> 0x0073 }
                com.google.android.gms.internal.zzsf$zza r0 = r0.zzael     // Catch:{ IllegalArgumentException -> 0x0073 }
                r3.zza(r4, r0)     // Catch:{ IllegalArgumentException -> 0x0073 }
                goto L_0x0073
            L_0x0057:
                boolean r4 = r2.zzaer     // Catch:{ all -> 0x0014 }
                if (r4 != 0) goto L_0x0071
                com.google.android.gms.internal.zzsf r4 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0014 }
                java.lang.String r0 = "onServiceConnected received after the timeout limit"
                r4.zzbS(r0)     // Catch:{ all -> 0x0014 }
                com.google.android.gms.internal.zzsf r4 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0014 }
                com.google.android.gms.analytics.zzh r4 = r4.zznU()     // Catch:{ all -> 0x0014 }
                com.google.android.gms.internal.zzsf$zza$1 r0 = new com.google.android.gms.internal.zzsf$zza$1     // Catch:{ all -> 0x0014 }
                r0.<init>(r3)     // Catch:{ all -> 0x0014 }
                r4.zzg(r0)     // Catch:{ all -> 0x0014 }
                goto L_0x0073
            L_0x0071:
                r2.zzaeq = r3     // Catch:{ all -> 0x0014 }
            L_0x0073:
                r2.notifyAll()     // Catch:{ all -> 0x007c }
                monitor-exit(r2)     // Catch:{ all -> 0x007c }
                return
            L_0x0078:
                r2.notifyAll()     // Catch:{ all -> 0x007c }
                throw r3     // Catch:{ all -> 0x007c }
            L_0x007c:
                r3 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x007c }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsf.zza.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        public void onServiceDisconnected(final ComponentName componentName) {
            zzac.zzdj("AnalyticsServiceConnection.onServiceDisconnected");
            zzsf.this.zznU().zzg(new Runnable() {
                public void run() {
                    zzsf.this.onServiceDisconnected(componentName);
                }
            });
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:9|10|11|12|13|(1:15)) */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0075, code lost:
            return r0;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x005e */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.android.gms.internal.zzta zzoq() {
            /*
                r6 = this;
                com.google.android.gms.internal.zzsf r0 = com.google.android.gms.internal.zzsf.this
                r0.zzmR()
                android.content.Intent r0 = new android.content.Intent
                java.lang.String r1 = "com.google.android.gms.analytics.service.START"
                r0.<init>(r1)
                android.content.ComponentName r1 = new android.content.ComponentName
                java.lang.String r2 = "com.google.android.gms"
                java.lang.String r3 = "com.google.android.gms.analytics.service.AnalyticsService"
                r1.<init>(r2, r3)
                r0.setComponent(r1)
                com.google.android.gms.internal.zzsf r1 = com.google.android.gms.internal.zzsf.this
                android.content.Context r1 = r1.getContext()
                java.lang.String r2 = "app_package_name"
                java.lang.String r3 = r1.getPackageName()
                r0.putExtra(r2, r3)
                com.google.android.gms.common.stats.zza r2 = com.google.android.gms.common.stats.zza.zzyJ()
                monitor-enter(r6)
                r3 = 0
                r6.zzaeq = r3     // Catch:{ all -> 0x0076 }
                r4 = 1
                r6.zzaer = r4     // Catch:{ all -> 0x0076 }
                com.google.android.gms.internal.zzsf r4 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0076 }
                com.google.android.gms.internal.zzsf$zza r4 = r4.zzael     // Catch:{ all -> 0x0076 }
                r5 = 129(0x81, float:1.81E-43)
                boolean r0 = r2.zza((android.content.Context) r1, (android.content.Intent) r0, (android.content.ServiceConnection) r4, (int) r5)     // Catch:{ all -> 0x0076 }
                com.google.android.gms.internal.zzsf r1 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0076 }
                java.lang.String r2 = "Bind to service requested"
                java.lang.Boolean r4 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0076 }
                r1.zza(r2, r4)     // Catch:{ all -> 0x0076 }
                r1 = 0
                if (r0 != 0) goto L_0x0050
                r6.zzaer = r1     // Catch:{ all -> 0x0076 }
                monitor-exit(r6)     // Catch:{ all -> 0x0076 }
                return r3
            L_0x0050:
                com.google.android.gms.internal.zzsf r0 = com.google.android.gms.internal.zzsf.this     // Catch:{ InterruptedException -> 0x005e }
                com.google.android.gms.internal.zzsp r0 = r0.zznT()     // Catch:{ InterruptedException -> 0x005e }
                long r4 = r0.zzpr()     // Catch:{ InterruptedException -> 0x005e }
                r6.wait(r4)     // Catch:{ InterruptedException -> 0x005e }
                goto L_0x0065
            L_0x005e:
                com.google.android.gms.internal.zzsf r0 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0076 }
                java.lang.String r2 = "Wait for service connect was interrupted"
                r0.zzbS(r2)     // Catch:{ all -> 0x0076 }
            L_0x0065:
                r6.zzaer = r1     // Catch:{ all -> 0x0076 }
                com.google.android.gms.internal.zzta r0 = r6.zzaeq     // Catch:{ all -> 0x0076 }
                r6.zzaeq = r3     // Catch:{ all -> 0x0076 }
                if (r0 != 0) goto L_0x0074
                com.google.android.gms.internal.zzsf r1 = com.google.android.gms.internal.zzsf.this     // Catch:{ all -> 0x0076 }
                java.lang.String r2 = "Successfully bound to service but never got onServiceConnected callback"
                r1.zzbT(r2)     // Catch:{ all -> 0x0076 }
            L_0x0074:
                monitor-exit(r6)     // Catch:{ all -> 0x0076 }
                return r0
            L_0x0076:
                r0 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x0076 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsf.zza.zzoq():com.google.android.gms.internal.zzta");
        }
    }

    protected zzsf(zzsc zzsc) {
        super(zzsc);
        this.zzaeo = new zztj(zzsc.zznR());
        this.zzaen = new zzsr(zzsc) {
            public void run() {
                zzsf.this.zzop();
            }
        };
    }

    private void onDisconnect() {
        zzmA().zznN();
    }

    /* access modifiers changed from: private */
    public void onServiceDisconnected(ComponentName componentName) {
        zzmR();
        if (this.zzaem != null) {
            this.zzaem = null;
            zza("Disconnected from device AnalyticsService", componentName);
            onDisconnect();
        }
    }

    /* access modifiers changed from: private */
    public void zza(zzta zzta) {
        zzmR();
        this.zzaem = zzta;
        zzoo();
        zzmA().onServiceConnected();
    }

    private void zzoo() {
        this.zzaeo.start();
        this.zzaen.zzy(zznT().zzpq());
    }

    /* access modifiers changed from: private */
    public void zzop() {
        zzmR();
        if (isConnected()) {
            zzbP("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public boolean connect() {
        zzmR();
        zzob();
        if (this.zzaem != null) {
            return true;
        }
        zzta zzoq = this.zzael.zzoq();
        if (zzoq == null) {
            return false;
        }
        this.zzaem = zzoq;
        zzoo();
        return true;
    }

    public void disconnect() {
        zzmR();
        zzob();
        try {
            com.google.android.gms.common.stats.zza.zzyJ().zza(getContext(), this.zzael);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        if (this.zzaem != null) {
            this.zzaem = null;
            onDisconnect();
        }
    }

    public boolean isConnected() {
        zzmR();
        zzob();
        return this.zzaem != null;
    }

    public boolean zzb(zzsz zzsz) {
        zzac.zzw(zzsz);
        zzmR();
        zzob();
        zzta zzta = this.zzaem;
        if (zzta == null) {
            return false;
        }
        try {
            zzta.zza(zzsz.zzfE(), zzsz.zzpQ(), zzsz.zzpS() ? zznT().zzpj() : zznT().zzpk(), Collections.emptyList());
            zzoo();
            return true;
        } catch (RemoteException unused) {
            zzbP("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public boolean zzon() {
        zzmR();
        zzob();
        zzta zzta = this.zzaem;
        if (zzta == null) {
            return false;
        }
        try {
            zzta.zznK();
            zzoo();
            return true;
        } catch (RemoteException unused) {
            zzbP("Failed to clear hits from AnalyticsService");
            return false;
        }
    }
}
