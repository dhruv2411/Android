package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaaf;
import com.google.android.gms.internal.zzaj;
import com.google.android.gms.internal.zzbjd;
import com.google.android.gms.internal.zzbje;
import com.google.android.gms.internal.zzbjf;
import com.google.android.gms.tagmanager.zzbn;
import com.google.android.gms.tagmanager.zzcj;
import com.google.android.gms.tagmanager.zzo;

public class zzp extends zzaaf<ContainerHolder> {
    private final Context mContext;
    private final String zzbEU;
    /* access modifiers changed from: private */
    public long zzbEZ;
    private final TagManager zzbFg;
    private final zzd zzbFj;
    /* access modifiers changed from: private */
    public final zzcl zzbFk;
    private final int zzbFl;
    /* access modifiers changed from: private */
    public final zzq zzbFm;
    private zzf zzbFn;
    private zzbje zzbFo;
    /* access modifiers changed from: private */
    public volatile zzo zzbFp;
    /* access modifiers changed from: private */
    public volatile boolean zzbFq;
    /* access modifiers changed from: private */
    public zzaj.zzj zzbFr;
    private String zzbFs;
    private zze zzbFt;
    private zza zzbFu;
    private final Looper zzrs;
    /* access modifiers changed from: private */
    public final com.google.android.gms.common.util.zze zzuP;

    /* renamed from: com.google.android.gms.tagmanager.zzp$1  reason: invalid class name */
    class AnonymousClass1 {
    }

    interface zza {
        boolean zzb(Container container);
    }

    private class zzb implements zzbn<zzbjd.zza> {
        private zzb() {
        }

        /* synthetic */ zzb(zzp zzp, AnonymousClass1 r2) {
            this();
        }

        /* renamed from: zza */
        public void onSuccess(zzbjd.zza zza) {
            zzaj.zzj zzj;
            if (zza.zzbNf != null) {
                zzj = zza.zzbNf;
            } else {
                zzaj.zzf zzf = zza.zzlr;
                zzaj.zzj zzj2 = new zzaj.zzj();
                zzj2.zzlr = zzf;
                zzj2.zzlq = null;
                zzj2.zzls = zzf.version;
                zzj = zzj2;
            }
            zzp.this.zza(zzj, zza.zzbNe, true);
        }

        public void zza(zzbn.zza zza) {
            if (!zzp.this.zzbFq) {
                zzp.this.zzay(0);
            }
        }
    }

    private class zzc implements zzbn<zzaj.zzj> {
        private zzc() {
        }

        /* synthetic */ zzc(zzp zzp, AnonymousClass1 r2) {
            this();
        }

        public void zza(zzbn.zza zza) {
            zzp zzp;
            Result zzbN;
            if (zza == zzbn.zza.SERVER_UNAVAILABLE_ERROR) {
                zzp.this.zzbFm.zzQs();
            }
            synchronized (zzp.this) {
                if (!zzp.this.isReady()) {
                    if (zzp.this.zzbFp != null) {
                        zzp = zzp.this;
                        zzbN = zzp.this.zzbFp;
                    } else {
                        zzp = zzp.this;
                        zzbN = zzp.this.zzc(Status.zzazA);
                    }
                    zzp.zzb(zzbN);
                }
            }
            zzp.this.zzay(zzp.this.zzbFm.zzQr());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0074, code lost:
            return;
         */
        /* renamed from: zzb */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onSuccess(com.google.android.gms.internal.zzaj.zzj r6) {
            /*
                r5 = this;
                com.google.android.gms.tagmanager.zzp r0 = com.google.android.gms.tagmanager.zzp.this
                com.google.android.gms.tagmanager.zzq r0 = r0.zzbFm
                r0.zzQt()
                com.google.android.gms.tagmanager.zzp r0 = com.google.android.gms.tagmanager.zzp.this
                monitor-enter(r0)
                com.google.android.gms.internal.zzaj$zzf r1 = r6.zzlr     // Catch:{ all -> 0x0075 }
                if (r1 != 0) goto L_0x003a
                com.google.android.gms.tagmanager.zzp r1 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0075 }
                com.google.android.gms.internal.zzaj$zzj r1 = r1.zzbFr     // Catch:{ all -> 0x0075 }
                com.google.android.gms.internal.zzaj$zzf r1 = r1.zzlr     // Catch:{ all -> 0x0075 }
                if (r1 != 0) goto L_0x0030
                java.lang.String r6 = "Current resource is null; network resource is also null"
                com.google.android.gms.tagmanager.zzbo.e(r6)     // Catch:{ all -> 0x0075 }
                com.google.android.gms.tagmanager.zzp r6 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0075 }
                com.google.android.gms.tagmanager.zzq r6 = r6.zzbFm     // Catch:{ all -> 0x0075 }
                long r1 = r6.zzQr()     // Catch:{ all -> 0x0075 }
                com.google.android.gms.tagmanager.zzp r6 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0075 }
                r6.zzay(r1)     // Catch:{ all -> 0x0075 }
                monitor-exit(r0)     // Catch:{ all -> 0x0075 }
                return
            L_0x0030:
                com.google.android.gms.tagmanager.zzp r1 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0075 }
                com.google.android.gms.internal.zzaj$zzj r1 = r1.zzbFr     // Catch:{ all -> 0x0075 }
                com.google.android.gms.internal.zzaj$zzf r1 = r1.zzlr     // Catch:{ all -> 0x0075 }
                r6.zzlr = r1     // Catch:{ all -> 0x0075 }
            L_0x003a:
                com.google.android.gms.tagmanager.zzp r1 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0075 }
                com.google.android.gms.tagmanager.zzp r2 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0075 }
                com.google.android.gms.common.util.zze r2 = r2.zzuP     // Catch:{ all -> 0x0075 }
                long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x0075 }
                r4 = 0
                r1.zza(r6, r2, r4)     // Catch:{ all -> 0x0075 }
                com.google.android.gms.tagmanager.zzp r1 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0075 }
                long r1 = r1.zzbEZ     // Catch:{ all -> 0x0075 }
                r3 = 58
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
                r4.<init>(r3)     // Catch:{ all -> 0x0075 }
                java.lang.String r3 = "setting refresh time to current time: "
                r4.append(r3)     // Catch:{ all -> 0x0075 }
                r4.append(r1)     // Catch:{ all -> 0x0075 }
                java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x0075 }
                com.google.android.gms.tagmanager.zzbo.v(r1)     // Catch:{ all -> 0x0075 }
                com.google.android.gms.tagmanager.zzp r1 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0075 }
                boolean r1 = r1.zzQn()     // Catch:{ all -> 0x0075 }
                if (r1 != 0) goto L_0x0073
                com.google.android.gms.tagmanager.zzp r1 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0075 }
                r1.zza((com.google.android.gms.internal.zzaj.zzj) r6)     // Catch:{ all -> 0x0075 }
            L_0x0073:
                monitor-exit(r0)     // Catch:{ all -> 0x0075 }
                return
            L_0x0075:
                r6 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0075 }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzp.zzc.onSuccess(com.google.android.gms.internal.zzaj$zzj):void");
        }
    }

    private class zzd implements zzo.zza {
        private zzd() {
        }

        /* synthetic */ zzd(zzp zzp, AnonymousClass1 r2) {
            this();
        }

        public String zzQh() {
            return zzp.this.zzQh();
        }

        public void zzQj() {
            if (zzp.this.zzbFk.zzpV()) {
                zzp.this.zzay(0);
            }
        }

        public void zzgW(String str) {
            zzp.this.zzgW(str);
        }
    }

    interface zze extends Releasable {
        void zza(zzbn<zzaj.zzj> zzbn);

        void zzf(long j, String str);

        void zzgZ(String str);
    }

    interface zzf extends Releasable {
        void zzQp();

        void zza(zzbn<zzbjd.zza> zzbn);

        void zzb(zzbjd.zza zza);

        zzbjf.zzc zznz(int i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzp(Context context, TagManager tagManager, Looper looper, String str, int i, zzf zzf2, zze zze2, zzbje zzbje, com.google.android.gms.common.util.zze zze3, zzcl zzcl, zzq zzq) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.zzbFg = tagManager;
        this.zzrs = looper == null ? Looper.getMainLooper() : looper;
        this.zzbEU = str;
        this.zzbFl = i;
        this.zzbFn = zzf2;
        this.zzbFt = zze2;
        this.zzbFo = zzbje;
        this.zzbFj = new zzd(this, (AnonymousClass1) null);
        this.zzbFr = new zzaj.zzj();
        this.zzuP = zze3;
        this.zzbFk = zzcl;
        this.zzbFm = zzq;
        if (zzQn()) {
            zzgW(zzcj.zzRe().zzRg());
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzp(android.content.Context r23, com.google.android.gms.tagmanager.TagManager r24, android.os.Looper r25, java.lang.String r26, int r27, com.google.android.gms.tagmanager.zzt r28) {
        /*
            r22 = this;
            r1 = r23
            r4 = r26
            com.google.android.gms.tagmanager.zzcv r6 = new com.google.android.gms.tagmanager.zzcv
            r6.<init>(r1, r4)
            com.google.android.gms.tagmanager.zzcu r7 = new com.google.android.gms.tagmanager.zzcu
            r12 = r28
            r7.<init>(r1, r4, r12)
            com.google.android.gms.internal.zzbje r8 = new com.google.android.gms.internal.zzbje
            r8.<init>(r1)
            com.google.android.gms.common.util.zze r9 = com.google.android.gms.common.util.zzi.zzzc()
            com.google.android.gms.tagmanager.zzbm r10 = new com.google.android.gms.tagmanager.zzbm
            java.lang.String r20 = "refreshing"
            com.google.android.gms.common.util.zze r21 = com.google.android.gms.common.util.zzi.zzzc()
            r14 = 1
            r15 = 5
            r16 = 900000(0xdbba0, double:4.44659E-318)
            r18 = 5000(0x1388, double:2.4703E-320)
            r13 = r10
            r13.<init>(r14, r15, r16, r18, r20, r21)
            com.google.android.gms.tagmanager.zzq r11 = new com.google.android.gms.tagmanager.zzq
            r11.<init>(r1, r4)
            r0 = r22
            r2 = r24
            r3 = r25
            r5 = r27
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            com.google.android.gms.internal.zzbje r1 = r0.zzbFo
            java.lang.String r2 = r28.zzQv()
            r1.zzig(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzp.<init>(android.content.Context, com.google.android.gms.tagmanager.TagManager, android.os.Looper, java.lang.String, int, com.google.android.gms.tagmanager.zzt):void");
    }

    /* access modifiers changed from: private */
    public boolean zzQn() {
        zzcj zzRe = zzcj.zzRe();
        return (zzRe.zzRf() == zzcj.zza.CONTAINER || zzRe.zzRf() == zzcj.zza.CONTAINER_DEBUG) && this.zzbEU.equals(zzRe.getContainerId());
    }

    /* access modifiers changed from: private */
    public synchronized void zza(zzaj.zzj zzj) {
        if (this.zzbFn != null) {
            zzbjd.zza zza2 = new zzbjd.zza();
            zza2.zzbNe = this.zzbEZ;
            zza2.zzlr = new zzaj.zzf();
            zza2.zzbNf = zzj;
            this.zzbFn.zzb(zza2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0074, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void zza(com.google.android.gms.internal.zzaj.zzj r11, long r12, boolean r14) {
        /*
            r10 = this;
            monitor-enter(r10)
            if (r14 == 0) goto L_0x0008
            boolean r14 = r10.zzbFq     // Catch:{ all -> 0x0006 }
            goto L_0x0008
        L_0x0006:
            r11 = move-exception
            goto L_0x0075
        L_0x0008:
            boolean r14 = r10.isReady()     // Catch:{ all -> 0x0006 }
            if (r14 == 0) goto L_0x0014
            com.google.android.gms.tagmanager.zzo r14 = r10.zzbFp     // Catch:{ all -> 0x0006 }
            if (r14 != 0) goto L_0x0014
            monitor-exit(r10)
            return
        L_0x0014:
            r10.zzbFr = r11     // Catch:{ all -> 0x0006 }
            r10.zzbEZ = r12     // Catch:{ all -> 0x0006 }
            com.google.android.gms.tagmanager.zzq r14 = r10.zzbFm     // Catch:{ all -> 0x0006 }
            long r0 = r14.zzQq()     // Catch:{ all -> 0x0006 }
            r2 = 0
            long r4 = r10.zzbEZ     // Catch:{ all -> 0x0006 }
            long r6 = r4 + r0
            com.google.android.gms.common.util.zze r14 = r10.zzuP     // Catch:{ all -> 0x0006 }
            long r4 = r14.currentTimeMillis()     // Catch:{ all -> 0x0006 }
            long r8 = r6 - r4
            long r0 = java.lang.Math.min(r0, r8)     // Catch:{ all -> 0x0006 }
            long r0 = java.lang.Math.max(r2, r0)     // Catch:{ all -> 0x0006 }
            r10.zzay(r0)     // Catch:{ all -> 0x0006 }
            com.google.android.gms.tagmanager.Container r14 = new com.google.android.gms.tagmanager.Container     // Catch:{ all -> 0x0006 }
            android.content.Context r3 = r10.mContext     // Catch:{ all -> 0x0006 }
            com.google.android.gms.tagmanager.TagManager r0 = r10.zzbFg     // Catch:{ all -> 0x0006 }
            com.google.android.gms.tagmanager.DataLayer r4 = r0.getDataLayer()     // Catch:{ all -> 0x0006 }
            java.lang.String r5 = r10.zzbEU     // Catch:{ all -> 0x0006 }
            r2 = r14
            r6 = r12
            r8 = r11
            r2.<init>((android.content.Context) r3, (com.google.android.gms.tagmanager.DataLayer) r4, (java.lang.String) r5, (long) r6, (com.google.android.gms.internal.zzaj.zzj) r8)     // Catch:{ all -> 0x0006 }
            com.google.android.gms.tagmanager.zzo r11 = r10.zzbFp     // Catch:{ all -> 0x0006 }
            if (r11 != 0) goto L_0x005b
            com.google.android.gms.tagmanager.zzo r11 = new com.google.android.gms.tagmanager.zzo     // Catch:{ all -> 0x0006 }
            com.google.android.gms.tagmanager.TagManager r12 = r10.zzbFg     // Catch:{ all -> 0x0006 }
            android.os.Looper r13 = r10.zzrs     // Catch:{ all -> 0x0006 }
            com.google.android.gms.tagmanager.zzp$zzd r0 = r10.zzbFj     // Catch:{ all -> 0x0006 }
            r11.<init>(r12, r13, r14, r0)     // Catch:{ all -> 0x0006 }
            r10.zzbFp = r11     // Catch:{ all -> 0x0006 }
            goto L_0x0060
        L_0x005b:
            com.google.android.gms.tagmanager.zzo r11 = r10.zzbFp     // Catch:{ all -> 0x0006 }
            r11.zza(r14)     // Catch:{ all -> 0x0006 }
        L_0x0060:
            boolean r11 = r10.isReady()     // Catch:{ all -> 0x0006 }
            if (r11 != 0) goto L_0x0073
            com.google.android.gms.tagmanager.zzp$zza r11 = r10.zzbFu     // Catch:{ all -> 0x0006 }
            boolean r11 = r11.zzb(r14)     // Catch:{ all -> 0x0006 }
            if (r11 == 0) goto L_0x0073
            com.google.android.gms.tagmanager.zzo r11 = r10.zzbFp     // Catch:{ all -> 0x0006 }
            r10.zzb(r11)     // Catch:{ all -> 0x0006 }
        L_0x0073:
            monitor-exit(r10)
            return
        L_0x0075:
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzp.zza(com.google.android.gms.internal.zzaj$zzj, long, boolean):void");
    }

    private void zzaR(final boolean z) {
        this.zzbFn.zza(new zzb(this, (AnonymousClass1) null));
        this.zzbFt.zza(new zzc(this, (AnonymousClass1) null));
        zzbjf.zzc zznz = this.zzbFn.zznz(this.zzbFl);
        if (zznz != null) {
            this.zzbFp = new zzo(this.zzbFg, this.zzrs, new Container(this.mContext, this.zzbFg.getDataLayer(), this.zzbEU, 0, zznz), this.zzbFj);
        }
        this.zzbFu = new zza() {
            private Long zzbFw;

            private long zzQo() {
                if (this.zzbFw == null) {
                    this.zzbFw = Long.valueOf(zzp.this.zzbFm.zzQq());
                }
                return this.zzbFw.longValue();
            }

            public boolean zzb(Container container) {
                return z ? container.getLastRefreshTime() + zzQo() >= zzp.this.zzuP.currentTimeMillis() : !container.isDefault();
            }
        };
        if (zzQn()) {
            this.zzbFt.zzf(0, "");
        } else {
            this.zzbFn.zzQp();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void zzay(long j) {
        if (this.zzbFt == null) {
            zzbo.zzbh("Refresh requested, but no network load scheduler.");
        } else {
            this.zzbFt.zzf(j, this.zzbFr.zzls);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized String zzQh() {
        return this.zzbFs;
    }

    public void zzQk() {
        zzbjf.zzc zznz = this.zzbFn.zznz(this.zzbFl);
        if (zznz != null) {
            zzb(new zzo(this.zzbFg, this.zzrs, new Container(this.mContext, this.zzbFg.getDataLayer(), this.zzbEU, 0, zznz), new zzo.zza() {
                public String zzQh() {
                    return zzp.this.zzQh();
                }

                public void zzQj() {
                    zzbo.zzbh("Refresh ignored: container loaded as default only.");
                }

                public void zzgW(String str) {
                    zzp.this.zzgW(str);
                }
            }));
        } else {
            zzbo.e("Default was requested, but no default container was found");
            zzb(zzc(new Status(10, "Default was requested, but no default container was found", (PendingIntent) null)));
        }
        this.zzbFt = null;
        this.zzbFn = null;
    }

    public void zzQl() {
        zzaR(false);
    }

    public void zzQm() {
        zzaR(true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzbN */
    public ContainerHolder zzc(Status status) {
        if (this.zzbFp != null) {
            return this.zzbFp;
        }
        if (status == Status.zzazA) {
            zzbo.e("timer expired: setting result to failure");
        }
        return new zzo(status);
    }

    /* access modifiers changed from: package-private */
    public synchronized void zzgW(String str) {
        this.zzbFs = str;
        if (this.zzbFt != null) {
            this.zzbFt.zzgZ(str);
        }
    }
}
