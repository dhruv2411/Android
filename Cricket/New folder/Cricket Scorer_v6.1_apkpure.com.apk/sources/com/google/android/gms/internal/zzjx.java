package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@zzme
public class zzjx implements zzjp {
    private final Context mContext;
    private final zzjr zzKY;
    private final boolean zzLa;
    private final zzmk zzLo;
    /* access modifiers changed from: private */
    public final long zzLp;
    /* access modifiers changed from: private */
    public final long zzLq;
    private final int zzLr;
    /* access modifiers changed from: private */
    public boolean zzLs = false;
    /* access modifiers changed from: private */
    public final Map<zzqm<zzjv>, zzju> zzLt = new HashMap();
    private List<zzjv> zzLu = new ArrayList();
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();
    private final zzka zzsz;
    private final boolean zzwf;

    public zzjx(Context context, zzmk zzmk, zzka zzka, zzjr zzjr, boolean z, boolean z2, long j, long j2, int i) {
        this.mContext = context;
        this.zzLo = zzmk;
        this.zzsz = zzka;
        this.zzKY = zzjr;
        this.zzwf = z;
        this.zzLa = z2;
        this.zzLp = j;
        this.zzLq = j2;
        this.zzLr = i;
    }

    private void zza(final zzqm<zzjv> zzqm) {
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                for (zzqm zzqm : zzjx.this.zzLt.keySet()) {
                    if (zzqm != zzqm) {
                        ((zzju) zzjx.this.zzLt.get(zzqm)).cancel();
                    }
                }
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r4.hasNext() == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = (com.google.android.gms.internal.zzjv) r0.get();
        r3.zzLu.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r1 == null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        if (r1.zzLh != 0) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        zza((com.google.android.gms.internal.zzqm<com.google.android.gms.internal.zzjv>) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        com.google.android.gms.internal.zzpk.zzc("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        zza((com.google.android.gms.internal.zzqm<com.google.android.gms.internal.zzjv>) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0046, code lost:
        return new com.google.android.gms.internal.zzjv(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r4 = r4.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.gms.internal.zzjv zze(java.util.List<com.google.android.gms.internal.zzqm<com.google.android.gms.internal.zzjv>> r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
            boolean r1 = r3.zzLs     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x000f
            com.google.android.gms.internal.zzjv r4 = new com.google.android.gms.internal.zzjv     // Catch:{ all -> 0x0047 }
            r1 = -1
            r4.<init>(r1)     // Catch:{ all -> 0x0047 }
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            return r4
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            java.util.Iterator r4 = r4.iterator()
        L_0x0014:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x003c
            java.lang.Object r0 = r4.next()
            com.google.android.gms.internal.zzqm r0 = (com.google.android.gms.internal.zzqm) r0
            java.lang.Object r1 = r0.get()     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            com.google.android.gms.internal.zzjv r1 = (com.google.android.gms.internal.zzjv) r1     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            java.util.List<com.google.android.gms.internal.zzjv> r2 = r3.zzLu     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            r2.add(r1)     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            if (r1 == 0) goto L_0x0014
            int r2 = r1.zzLh     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            if (r2 != 0) goto L_0x0014
            r3.zza((com.google.android.gms.internal.zzqm<com.google.android.gms.internal.zzjv>) r0)     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            return r1
        L_0x0035:
            r0 = move-exception
            java.lang.String r1 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.zzpk.zzc(r1, r0)
            goto L_0x0014
        L_0x003c:
            r4 = 0
            r3.zza((com.google.android.gms.internal.zzqm<com.google.android.gms.internal.zzjv>) r4)
            com.google.android.gms.internal.zzjv r4 = new com.google.android.gms.internal.zzjv
            r0 = 1
            r4.<init>(r0)
            return r4
        L_0x0047:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjx.zze(java.util.List):com.google.android.gms.internal.zzjv");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r14.zzKY.zzKP == -1) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r0 = r14.zzKY.zzKP;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r0 = 10000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r15 = r15.iterator();
        r3 = null;
        r4 = -1;
        r1 = r0;
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        if (r15.hasNext() == false) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        r5 = r15.next();
        r6 = com.google.android.gms.ads.internal.zzw.zzcS().currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        if (r1 != 0) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r5.isDone() == false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r10 = r5.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        r10 = (com.google.android.gms.internal.zzjv) r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0052, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0054, code lost:
        r10 = r5.get(r1, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
        r14.zzLu.add(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        if (r10 == null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0064, code lost:
        if (r10.zzLh != 0) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0066, code lost:
        r11 = r10.zzLm;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0068, code lost:
        if (r11 == null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006e, code lost:
        if (r11.zzha() <= r4) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0074, code lost:
        r3 = r5;
        r0 = r10;
        r4 = r11.zzha();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        com.google.android.gms.internal.zzpk.zzc("Exception while processing an adapter; continuing with other adapters", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008e, code lost:
        java.lang.Math.max(r1 - (com.google.android.gms.ads.internal.zzw.zzcS().currentTimeMillis() - r6), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009d, code lost:
        throw r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009e, code lost:
        zza((com.google.android.gms.internal.zzqm<com.google.android.gms.internal.zzjv>) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a1, code lost:
        if (r0 != null) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a9, code lost:
        return new com.google.android.gms.internal.zzjv(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00aa, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.gms.internal.zzjv zzf(java.util.List<com.google.android.gms.internal.zzqm<com.google.android.gms.internal.zzjv>> r15) {
        /*
            r14 = this;
            java.lang.Object r0 = r14.zzrJ
            monitor-enter(r0)
            boolean r1 = r14.zzLs     // Catch:{ all -> 0x00ab }
            r2 = -1
            if (r1 == 0) goto L_0x000f
            com.google.android.gms.internal.zzjv r15 = new com.google.android.gms.internal.zzjv     // Catch:{ all -> 0x00ab }
            r15.<init>(r2)     // Catch:{ all -> 0x00ab }
            monitor-exit(r0)     // Catch:{ all -> 0x00ab }
            return r15
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x00ab }
            com.google.android.gms.internal.zzjr r0 = r14.zzKY
            long r0 = r0.zzKP
            r3 = -1
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x001f
            com.google.android.gms.internal.zzjr r0 = r14.zzKY
            long r0 = r0.zzKP
            goto L_0x0021
        L_0x001f:
            r0 = 10000(0x2710, double:4.9407E-320)
        L_0x0021:
            java.util.Iterator r15 = r15.iterator()
            r3 = 0
            r4 = r2
            r1 = r0
            r0 = r3
        L_0x0029:
            boolean r5 = r15.hasNext()
            if (r5 == 0) goto L_0x009e
            java.lang.Object r5 = r15.next()
            com.google.android.gms.internal.zzqm r5 = (com.google.android.gms.internal.zzqm) r5
            com.google.android.gms.common.util.zze r6 = com.google.android.gms.ads.internal.zzw.zzcS()
            long r6 = r6.currentTimeMillis()
            r8 = 0
            int r10 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x0054
            boolean r10 = r5.isDone()     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r10 == 0) goto L_0x0054
            java.lang.Object r10 = r5.get()     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        L_0x004d:
            com.google.android.gms.internal.zzjv r10 = (com.google.android.gms.internal.zzjv) r10     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            goto L_0x005b
        L_0x0050:
            r15 = move-exception
            goto L_0x008e
        L_0x0052:
            r5 = move-exception
            goto L_0x0078
        L_0x0054:
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            java.lang.Object r10 = r5.get(r1, r10)     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            goto L_0x004d
        L_0x005b:
            java.util.List<com.google.android.gms.internal.zzjv> r11 = r14.zzLu     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            r11.add(r10)     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r10 == 0) goto L_0x007d
            int r11 = r10.zzLh     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r11 != 0) goto L_0x007d
            com.google.android.gms.internal.zzkd r11 = r10.zzLm     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r11 == 0) goto L_0x007d
            int r12 = r11.zzha()     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r12 <= r4) goto L_0x007d
            int r11 = r11.zzha()     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            r3 = r5
            r0 = r10
            r4 = r11
            goto L_0x007d
        L_0x0078:
            java.lang.String r10 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.zzpk.zzc(r10, r5)     // Catch:{ all -> 0x0050 }
        L_0x007d:
            com.google.android.gms.common.util.zze r5 = com.google.android.gms.ads.internal.zzw.zzcS()
            long r10 = r5.currentTimeMillis()
            long r12 = r10 - r6
            long r5 = r1 - r12
            long r1 = java.lang.Math.max(r5, r8)
            goto L_0x0029
        L_0x008e:
            com.google.android.gms.common.util.zze r0 = com.google.android.gms.ads.internal.zzw.zzcS()
            long r3 = r0.currentTimeMillis()
            long r10 = r3 - r6
            long r3 = r1 - r10
            java.lang.Math.max(r3, r8)
            throw r15
        L_0x009e:
            r14.zza((com.google.android.gms.internal.zzqm<com.google.android.gms.internal.zzjv>) r3)
            if (r0 != 0) goto L_0x00aa
            com.google.android.gms.internal.zzjv r15 = new com.google.android.gms.internal.zzjv
            r0 = 1
            r15.<init>(r0)
            return r15
        L_0x00aa:
            return r0
        L_0x00ab:
            r15 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ab }
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjx.zzf(java.util.List):com.google.android.gms.internal.zzjv");
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            this.zzLs = true;
            for (zzju cancel : this.zzLt.values()) {
                cancel.cancel();
            }
        }
    }

    public zzjv zzd(List<zzjq> list) {
        zzpk.zzbf("Starting mediation.");
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        Iterator<zzjq> it = list.iterator();
        while (it.hasNext()) {
            zzjq next = it.next();
            String valueOf = String.valueOf(next.zzKo);
            zzpk.zzbg(valueOf.length() != 0 ? "Trying mediation network: ".concat(valueOf) : new String("Trying mediation network: "));
            Iterator<String> it2 = next.zzKp.iterator();
            while (it2.hasNext()) {
                Context context = this.mContext;
                zzka zzka = this.zzsz;
                zzjr zzjr = this.zzKY;
                zzec zzec = this.zzLo.zzRy;
                zzeg zzeg = this.zzLo.zzvr;
                zzqh zzqh = this.zzLo.zzvn;
                Iterator<zzjq> it3 = it;
                boolean z = this.zzwf;
                zzjq zzjq = next;
                zzjq zzjq2 = next;
                final zzju zzju = r5;
                zzju zzju2 = new zzju(context, it2.next(), zzka, zzjr, zzjq, zzec, zzeg, zzqh, z, this.zzLa, this.zzLo.zzvF, this.zzLo.zzvK);
                zzqm zza = zzpn.zza(newCachedThreadPool, new Callable<zzjv>() {
                    /* renamed from: zzhb */
                    public zzjv call() throws Exception {
                        synchronized (zzjx.this.zzrJ) {
                            if (zzjx.this.zzLs) {
                                return null;
                            }
                            return zzju.zza(zzjx.this.zzLp, zzjx.this.zzLq);
                        }
                    }
                });
                this.zzLt.put(zza, zzju);
                arrayList.add(zza);
                it = it3;
                it2 = it2;
                next = zzjq2;
            }
        }
        return this.zzLr != 2 ? zze((List<zzqm<zzjv>>) arrayList) : zzf(arrayList);
    }

    public List<zzjv> zzgU() {
        return this.zzLu;
    }
}
