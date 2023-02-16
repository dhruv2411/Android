package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

class zzdc extends zzdb {
    /* access modifiers changed from: private */
    public static final Object zzbIa = new Object();
    private static zzdc zzbIm;
    /* access modifiers changed from: private */
    public boolean connected = true;
    /* access modifiers changed from: private */
    public Context zzbIb;
    /* access modifiers changed from: private */
    public zzaw zzbIc;
    private volatile zzau zzbId;
    /* access modifiers changed from: private */
    public int zzbIe = 1800000;
    private boolean zzbIf = true;
    private boolean zzbIg = false;
    private boolean zzbIh = true;
    private zzax zzbIi = new zzax() {
        public void zzaS(boolean z) {
            zzdc.this.zze(z, zzdc.this.connected);
        }
    };
    private zza zzbIj;
    private zzbt zzbIk;
    private boolean zzbIl = false;

    public interface zza {
        void cancel();

        void zzRD();

        void zzy(long j);
    }

    private class zzb implements zza {
        private Handler handler;

        private zzb() {
            this.handler = new Handler(zzdc.this.zzbIb.getMainLooper(), new Handler.Callback() {
                public boolean handleMessage(Message message) {
                    if (1 == message.what && zzdc.zzbIa.equals(message.obj)) {
                        zzdc.this.dispatch();
                        if (!zzdc.this.isPowerSaveMode()) {
                            zzb.this.zzy((long) zzdc.this.zzbIe);
                        }
                    }
                    return true;
                }
            });
        }

        private Message obtainMessage() {
            return this.handler.obtainMessage(1, zzdc.zzbIa);
        }

        public void cancel() {
            this.handler.removeMessages(1, zzdc.zzbIa);
        }

        public void zzRD() {
            this.handler.removeMessages(1, zzdc.zzbIa);
            this.handler.sendMessage(obtainMessage());
        }

        public void zzy(long j) {
            this.handler.removeMessages(1, zzdc.zzbIa);
            this.handler.sendMessageDelayed(obtainMessage(), j);
        }
    }

    private zzdc() {
    }

    /* access modifiers changed from: private */
    public boolean isPowerSaveMode() {
        return this.zzbIl || !this.connected || this.zzbIe <= 0;
    }

    private void zzRA() {
        this.zzbIj = new zzb();
        if (this.zzbIe > 0) {
            this.zzbIj.zzy((long) this.zzbIe);
        }
    }

    public static zzdc zzRy() {
        if (zzbIm == null) {
            zzbIm = new zzdc();
        }
        return zzbIm;
    }

    private void zzRz() {
        this.zzbIk = new zzbt(this);
        this.zzbIk.zzcb(this.zzbIb);
    }

    private void zzoH() {
        String str;
        if (isPowerSaveMode()) {
            this.zzbIj.cancel();
            str = "PowerSaveMode initiated.";
        } else {
            this.zzbIj.zzy((long) this.zzbIe);
            str = "PowerSaveMode terminated.";
        }
        zzbo.v(str);
    }

    public synchronized void dispatch() {
        if (!this.zzbIg) {
            zzbo.v("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzbIf = true;
            return;
        }
        this.zzbId.zzp(new Runnable() {
            public void run() {
                zzdc.this.zzbIc.dispatch();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public synchronized zzaw zzRB() {
        if (this.zzbIc == null) {
            if (this.zzbIb == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.zzbIc = new zzcg(this.zzbIi, this.zzbIb);
        }
        if (this.zzbIj == null) {
            zzRA();
        }
        this.zzbIg = true;
        if (this.zzbIf) {
            dispatch();
            this.zzbIf = false;
        }
        if (this.zzbIk == null && this.zzbIh) {
            zzRz();
        }
        return this.zzbIc;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void zza(android.content.Context r2, com.google.android.gms.tagmanager.zzau r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            android.content.Context r0 = r1.zzbIb     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x0015 }
            r1.zzbIb = r2     // Catch:{ all -> 0x0015 }
            com.google.android.gms.tagmanager.zzau r2 = r1.zzbId     // Catch:{ all -> 0x0015 }
            if (r2 != 0) goto L_0x0013
            r1.zzbId = r3     // Catch:{ all -> 0x0015 }
        L_0x0013:
            monitor-exit(r1)
            return
        L_0x0015:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdc.zza(android.content.Context, com.google.android.gms.tagmanager.zzau):void");
    }

    public synchronized void zzaT(boolean z) {
        zze(this.zzbIl, z);
    }

    /* access modifiers changed from: package-private */
    public synchronized void zze(boolean z, boolean z2) {
        boolean isPowerSaveMode = isPowerSaveMode();
        this.zzbIl = z;
        this.connected = z2;
        if (isPowerSaveMode() != isPowerSaveMode) {
            zzoH();
        }
    }

    public synchronized void zznO() {
        if (!isPowerSaveMode()) {
            this.zzbIj.zzRD();
        }
    }
}
