package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzjf;
import com.google.android.gms.internal.zzqp;
import java.util.Map;

@zzme
public class zzji {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final String zzJK;
    /* access modifiers changed from: private */
    public zzpt<zzjf> zzJL;
    private zzpt<zzjf> zzJM;
    /* access modifiers changed from: private */
    @Nullable
    public zzd zzJN;
    /* access modifiers changed from: private */
    public int zzJO;
    /* access modifiers changed from: private */
    public final Object zzrJ;
    /* access modifiers changed from: private */
    public final zzqh zztt;

    static class zza {
        static int zzJZ = 60000;
        static int zzKa = 10000;
    }

    public static class zzb<T> implements zzpt<T> {
        public void zzd(T t) {
        }
    }

    public static class zzc extends zzqq<zzjj> {
        /* access modifiers changed from: private */
        public final zzd zzKb;
        private boolean zzKc;
        private final Object zzrJ = new Object();

        public zzc(zzd zzd) {
            this.zzKb = zzd;
        }

        public void release() {
            synchronized (this.zzrJ) {
                if (!this.zzKc) {
                    this.zzKc = true;
                    zza(new zzqp.zzc<zzjj>(this) {
                        /* renamed from: zzb */
                        public void zzd(zzjj zzjj) {
                            zzpk.v("Ending javascript session.");
                            ((zzjk) zzjj).zzgT();
                        }
                    }, new zzqp.zzb());
                    zza(new zzqp.zzc<zzjj>() {
                        /* renamed from: zzb */
                        public void zzd(zzjj zzjj) {
                            zzpk.v("Releasing engine reference.");
                            zzc.this.zzKb.zzgQ();
                        }
                    }, new zzqp.zza() {
                        public void run() {
                            zzc.this.zzKb.zzgQ();
                        }
                    });
                }
            }
        }
    }

    public static class zzd extends zzqq<zzjf> {
        /* access modifiers changed from: private */
        public zzpt<zzjf> zzJM;
        private boolean zzKe;
        private int zzKf;
        private final Object zzrJ = new Object();

        public zzd(zzpt<zzjf> zzpt) {
            this.zzJM = zzpt;
            this.zzKe = false;
            this.zzKf = 0;
        }

        public zzc zzgP() {
            final zzc zzc = new zzc(this);
            synchronized (this.zzrJ) {
                zza(new zzqp.zzc<zzjf>(this) {
                    /* renamed from: zza */
                    public void zzd(zzjf zzjf) {
                        zzpk.v("Getting a new session for JS Engine.");
                        zzc.zzg(zzjf.zzgM());
                    }
                }, new zzqp.zza(this) {
                    public void run() {
                        zzpk.v("Rejecting reference for JS Engine.");
                        zzc.reject();
                    }
                });
                zzac.zzaw(this.zzKf >= 0);
                this.zzKf++;
            }
            return zzc;
        }

        /* access modifiers changed from: protected */
        public void zzgQ() {
            synchronized (this.zzrJ) {
                zzac.zzaw(this.zzKf >= 1);
                zzpk.v("Releasing 1 reference for JS Engine");
                this.zzKf--;
                zzgS();
            }
        }

        public void zzgR() {
            synchronized (this.zzrJ) {
                zzac.zzaw(this.zzKf >= 0);
                zzpk.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
                this.zzKe = true;
                zzgS();
            }
        }

        /* access modifiers changed from: protected */
        public void zzgS() {
            synchronized (this.zzrJ) {
                zzac.zzaw(this.zzKf >= 0);
                if (!this.zzKe || this.zzKf != 0) {
                    zzpk.v("There are still references to the engine. Not destroying.");
                } else {
                    zzpk.v("No reference is left (including root). Cleaning up engine.");
                    zza(new zzqp.zzc<zzjf>() {
                        /* renamed from: zza */
                        public void zzd(final zzjf zzjf) {
                            zzw.zzcM().runOnUiThread(new Runnable() {
                                public void run() {
                                    zzd.this.zzJM.zzd(zzjf);
                                    zzjf.destroy();
                                }
                            });
                        }
                    }, new zzqp.zzb());
                }
            }
        }
    }

    public static class zze extends zzqq<zzjj> {
        private zzc zzKk;

        public zze(zzc zzc) {
            this.zzKk = zzc;
        }

        public void finalize() {
            this.zzKk.release();
            this.zzKk = null;
        }

        public int getStatus() {
            return this.zzKk.getStatus();
        }

        public void reject() {
            this.zzKk.reject();
        }

        public void zza(zzqp.zzc<zzjj> zzc, zzqp.zza zza) {
            this.zzKk.zza(zzc, zza);
        }

        /* renamed from: zzj */
        public void zzg(zzjj zzjj) {
            this.zzKk.zzg(zzjj);
        }
    }

    public zzji(Context context, zzqh zzqh, String str) {
        this.zzrJ = new Object();
        this.zzJO = 1;
        this.zzJK = str;
        this.mContext = context.getApplicationContext();
        this.zztt = zzqh;
        this.zzJL = new zzb();
        this.zzJM = new zzb();
    }

    public zzji(Context context, zzqh zzqh, String str, zzpt<zzjf> zzpt, zzpt<zzjf> zzpt2) {
        this(context, zzqh, str);
        this.zzJL = zzpt;
        this.zzJM = zzpt2;
    }

    private zzd zza(@Nullable final zzaw zzaw) {
        final zzd zzd2 = new zzd(this.zzJM);
        zzw.zzcM().runOnUiThread(new Runnable() {
            public void run() {
                final zzjf zza = zzji.this.zza(zzji.this.mContext, zzji.this.zztt, zzaw);
                zza.zza(new zzjf.zza() {
                    public void zzgN() {
                        zzpo.zzXC.postDelayed(new Runnable() {
                            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
                                return;
                             */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void run() {
                                /*
                                    r3 = this;
                                    com.google.android.gms.internal.zzji$1$1 r0 = com.google.android.gms.internal.zzji.AnonymousClass1.AnonymousClass1.this
                                    com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.AnonymousClass1.this
                                    com.google.android.gms.internal.zzji r0 = com.google.android.gms.internal.zzji.this
                                    java.lang.Object r0 = r0.zzrJ
                                    monitor-enter(r0)
                                    com.google.android.gms.internal.zzji$1$1 r1 = com.google.android.gms.internal.zzji.AnonymousClass1.AnonymousClass1.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$1 r1 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$zzd r1 = r0     // Catch:{ all -> 0x0044 }
                                    int r1 = r1.getStatus()     // Catch:{ all -> 0x0044 }
                                    r2 = -1
                                    if (r1 == r2) goto L_0x0042
                                    com.google.android.gms.internal.zzji$1$1 r1 = com.google.android.gms.internal.zzji.AnonymousClass1.AnonymousClass1.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$1 r1 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$zzd r1 = r0     // Catch:{ all -> 0x0044 }
                                    int r1 = r1.getStatus()     // Catch:{ all -> 0x0044 }
                                    r2 = 1
                                    if (r1 != r2) goto L_0x0026
                                    goto L_0x0042
                                L_0x0026:
                                    com.google.android.gms.internal.zzji$1$1 r1 = com.google.android.gms.internal.zzji.AnonymousClass1.AnonymousClass1.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$1 r1 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$zzd r1 = r0     // Catch:{ all -> 0x0044 }
                                    r1.reject()     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzpo r1 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x0044 }
                                    com.google.android.gms.internal.zzji$1$1$1$1 r2 = new com.google.android.gms.internal.zzji$1$1$1$1     // Catch:{ all -> 0x0044 }
                                    r2.<init>()     // Catch:{ all -> 0x0044 }
                                    r1.runOnUiThread(r2)     // Catch:{ all -> 0x0044 }
                                    java.lang.String r1 = "Could not receive loaded message in a timely manner. Rejecting."
                                    com.google.android.gms.internal.zzpk.v(r1)     // Catch:{ all -> 0x0044 }
                                    monitor-exit(r0)     // Catch:{ all -> 0x0044 }
                                    return
                                L_0x0042:
                                    monitor-exit(r0)     // Catch:{ all -> 0x0044 }
                                    return
                                L_0x0044:
                                    r1 = move-exception
                                    monitor-exit(r0)     // Catch:{ all -> 0x0044 }
                                    throw r1
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzji.AnonymousClass1.AnonymousClass1.AnonymousClass1.run():void");
                            }
                        }, (long) zza.zzKa);
                    }
                });
                zza.zza("/jsLoaded", (zzid) new zzid() {
                    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0051, code lost:
                        return;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void zza(com.google.android.gms.internal.zzqw r2, java.util.Map<java.lang.String, java.lang.String> r3) {
                        /*
                            r1 = this;
                            com.google.android.gms.internal.zzji$1 r2 = com.google.android.gms.internal.zzji.AnonymousClass1.this
                            com.google.android.gms.internal.zzji r2 = com.google.android.gms.internal.zzji.this
                            java.lang.Object r2 = r2.zzrJ
                            monitor-enter(r2)
                            com.google.android.gms.internal.zzji$1 r3 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$zzd r3 = r0     // Catch:{ all -> 0x0052 }
                            int r3 = r3.getStatus()     // Catch:{ all -> 0x0052 }
                            r0 = -1
                            if (r3 == r0) goto L_0x0050
                            com.google.android.gms.internal.zzji$1 r3 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$zzd r3 = r0     // Catch:{ all -> 0x0052 }
                            int r3 = r3.getStatus()     // Catch:{ all -> 0x0052 }
                            r0 = 1
                            if (r3 != r0) goto L_0x0020
                            goto L_0x0050
                        L_0x0020:
                            com.google.android.gms.internal.zzji$1 r3 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji r3 = com.google.android.gms.internal.zzji.this     // Catch:{ all -> 0x0052 }
                            r0 = 0
                            int unused = r3.zzJO = r0     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$1 r3 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji r3 = com.google.android.gms.internal.zzji.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzpt r3 = r3.zzJL     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzjf r0 = r0     // Catch:{ all -> 0x0052 }
                            r3.zzd(r0)     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$1 r3 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$zzd r3 = r0     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzjf r0 = r0     // Catch:{ all -> 0x0052 }
                            r3.zzg(r0)     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$1 r3 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji r3 = com.google.android.gms.internal.zzji.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji$zzd r0 = r0     // Catch:{ all -> 0x0052 }
                            com.google.android.gms.internal.zzji.zzd unused = r3.zzJN = r0     // Catch:{ all -> 0x0052 }
                            java.lang.String r3 = "Successfully loaded JS Engine."
                            com.google.android.gms.internal.zzpk.v(r3)     // Catch:{ all -> 0x0052 }
                            monitor-exit(r2)     // Catch:{ all -> 0x0052 }
                            return
                        L_0x0050:
                            monitor-exit(r2)     // Catch:{ all -> 0x0052 }
                            return
                        L_0x0052:
                            r3 = move-exception
                            monitor-exit(r2)     // Catch:{ all -> 0x0052 }
                            throw r3
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzji.AnonymousClass1.AnonymousClass2.zza(com.google.android.gms.internal.zzqw, java.util.Map):void");
                    }
                });
                final zzqa zzqa = new zzqa();
                AnonymousClass3 r2 = new zzid() {
                    public void zza(zzqw zzqw, Map<String, String> map) {
                        synchronized (zzji.this.zzrJ) {
                            zzpk.zzbg("JS Engine is requesting an update");
                            if (zzji.this.zzJO == 0) {
                                zzpk.zzbg("Starting reload.");
                                int unused = zzji.this.zzJO = 2;
                                zzji.this.zzb(zzaw);
                            }
                            zza.zzb("/requestReload", (zzid) zzqa.get());
                        }
                    }
                };
                zzqa.set(r2);
                zza.zza("/requestReload", (zzid) r2);
                if (zzji.this.zzJK.endsWith(".js")) {
                    zza.zzam(zzji.this.zzJK);
                } else if (zzji.this.zzJK.startsWith("<html>")) {
                    zza.zzao(zzji.this.zzJK);
                } else {
                    zza.zzan(zzji.this.zzJK);
                }
                zzpo.zzXC.postDelayed(new Runnable() {
                    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
                        return;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r3 = this;
                            com.google.android.gms.internal.zzji$1 r0 = com.google.android.gms.internal.zzji.AnonymousClass1.this
                            com.google.android.gms.internal.zzji r0 = com.google.android.gms.internal.zzji.this
                            java.lang.Object r0 = r0.zzrJ
                            monitor-enter(r0)
                            com.google.android.gms.internal.zzji$1 r1 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzji$zzd r1 = r0     // Catch:{ all -> 0x003c }
                            int r1 = r1.getStatus()     // Catch:{ all -> 0x003c }
                            r2 = -1
                            if (r1 == r2) goto L_0x003a
                            com.google.android.gms.internal.zzji$1 r1 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzji$zzd r1 = r0     // Catch:{ all -> 0x003c }
                            int r1 = r1.getStatus()     // Catch:{ all -> 0x003c }
                            r2 = 1
                            if (r1 != r2) goto L_0x0020
                            goto L_0x003a
                        L_0x0020:
                            com.google.android.gms.internal.zzji$1 r1 = com.google.android.gms.internal.zzji.AnonymousClass1.this     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzji$zzd r1 = r0     // Catch:{ all -> 0x003c }
                            r1.reject()     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzpo r1 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x003c }
                            com.google.android.gms.internal.zzji$1$4$1 r2 = new com.google.android.gms.internal.zzji$1$4$1     // Catch:{ all -> 0x003c }
                            r2.<init>()     // Catch:{ all -> 0x003c }
                            r1.runOnUiThread(r2)     // Catch:{ all -> 0x003c }
                            java.lang.String r1 = "Could not receive loaded message in a timely manner. Rejecting."
                            com.google.android.gms.internal.zzpk.v(r1)     // Catch:{ all -> 0x003c }
                            monitor-exit(r0)     // Catch:{ all -> 0x003c }
                            return
                        L_0x003a:
                            monitor-exit(r0)     // Catch:{ all -> 0x003c }
                            return
                        L_0x003c:
                            r1 = move-exception
                            monitor-exit(r0)     // Catch:{ all -> 0x003c }
                            throw r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzji.AnonymousClass1.AnonymousClass4.run():void");
                    }
                }, (long) zza.zzJZ);
            }
        });
        return zzd2;
    }

    /* access modifiers changed from: protected */
    public zzjf zza(Context context, zzqh zzqh, @Nullable zzaw zzaw) {
        return new zzjh(context, zzqh, zzaw, (com.google.android.gms.ads.internal.zze) null);
    }

    /* access modifiers changed from: protected */
    public zzd zzb(@Nullable zzaw zzaw) {
        final zzd zza2 = zza(zzaw);
        zza2.zza(new zzqp.zzc<zzjf>() {
            /* renamed from: zza */
            public void zzd(zzjf zzjf) {
                synchronized (zzji.this.zzrJ) {
                    int unused = zzji.this.zzJO = 0;
                    if (!(zzji.this.zzJN == null || zza2 == zzji.this.zzJN)) {
                        zzpk.v("New JS engine is loaded, marking previous one as destroyable.");
                        zzji.this.zzJN.zzgR();
                    }
                    zzd unused2 = zzji.this.zzJN = zza2;
                }
            }
        }, new zzqp.zza() {
            public void run() {
                synchronized (zzji.this.zzrJ) {
                    int unused = zzji.this.zzJO = 1;
                    zzpk.v("Failed loading new engine. Marking new engine destroyable.");
                    zza2.zzgR();
                }
            }
        });
        return zza2;
    }

    public zzc zzc(@Nullable zzaw zzaw) {
        synchronized (this.zzrJ) {
            if (this.zzJN != null) {
                if (this.zzJN.getStatus() != -1) {
                    if (this.zzJO == 0) {
                        zzc zzgP = this.zzJN.zzgP();
                        return zzgP;
                    } else if (this.zzJO == 1) {
                        this.zzJO = 2;
                        zzb(zzaw);
                        zzc zzgP2 = this.zzJN.zzgP();
                        return zzgP2;
                    } else if (this.zzJO == 2) {
                        zzc zzgP3 = this.zzJN.zzgP();
                        return zzgP3;
                    } else {
                        zzc zzgP4 = this.zzJN.zzgP();
                        return zzgP4;
                    }
                }
            }
            this.zzJO = 2;
            this.zzJN = zzb(zzaw);
            zzc zzgP5 = this.zzJN.zzgP();
            return zzgP5;
        }
    }

    public zzc zzgO() {
        return zzc((zzaw) null);
    }
}
