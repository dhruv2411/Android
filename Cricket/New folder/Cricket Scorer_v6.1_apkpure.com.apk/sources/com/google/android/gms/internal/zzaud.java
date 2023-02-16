package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import java.lang.Thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class zzaud extends zzauh {
    /* access modifiers changed from: private */
    public static final AtomicLong zzbtS = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService zzbtI;
    /* access modifiers changed from: private */
    public zzd zzbtJ;
    /* access modifiers changed from: private */
    public zzd zzbtK;
    private final PriorityBlockingQueue<FutureTask<?>> zzbtL = new PriorityBlockingQueue<>();
    private final BlockingQueue<FutureTask<?>> zzbtM = new LinkedBlockingQueue();
    private final Thread.UncaughtExceptionHandler zzbtN = new zzb("Thread death: Uncaught exception on worker thread");
    private final Thread.UncaughtExceptionHandler zzbtO = new zzb("Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzbtP = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzbtQ = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzbtR;

    static class zza extends RuntimeException {
    }

    private final class zzb implements Thread.UncaughtExceptionHandler {
        private final String zzbtT;

        public zzb(String str) {
            zzac.zzw(str);
            this.zzbtT = str;
        }

        public synchronized void uncaughtException(Thread thread, Throwable th) {
            zzaud.this.zzKl().zzLY().zzj(this.zzbtT, th);
        }
    }

    private final class zzc<V> extends FutureTask<V> implements Comparable<zzc> {
        private final String zzbtT;
        private final long zzbtV = zzaud.zzbtS.getAndIncrement();
        private final boolean zzbtW;

        zzc(Runnable runnable, boolean z, String str) {
            super(runnable, (Object) null);
            zzac.zzw(str);
            this.zzbtT = str;
            this.zzbtW = z;
            if (this.zzbtV == Long.MAX_VALUE) {
                zzaud.this.zzKl().zzLY().log("Tasks index overflow");
            }
        }

        zzc(Callable<V> callable, boolean z, String str) {
            super(callable);
            zzac.zzw(str);
            this.zzbtT = str;
            this.zzbtW = z;
            if (this.zzbtV == Long.MAX_VALUE) {
                zzaud.this.zzKl().zzLY().log("Tasks index overflow");
            }
        }

        /* access modifiers changed from: protected */
        public void setException(Throwable th) {
            zzaud.this.zzKl().zzLY().zzj(this.zzbtT, th);
            if (th instanceof zza) {
                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
            }
            super.setException(th);
        }

        /* renamed from: zzb */
        public int compareTo(@NonNull zzc zzc) {
            if (this.zzbtW != zzc.zzbtW) {
                return this.zzbtW ? -1 : 1;
            }
            if (this.zzbtV < zzc.zzbtV) {
                return -1;
            }
            if (this.zzbtV > zzc.zzbtV) {
                return 1;
            }
            zzaud.this.zzKl().zzLZ().zzj("Two tasks share the same index. index", Long.valueOf(this.zzbtV));
            return 0;
        }
    }

    private final class zzd extends Thread {
        private final Object zzbtX = new Object();
        private final BlockingQueue<FutureTask<?>> zzbtY;

        public zzd(String str, BlockingQueue<FutureTask<?>> blockingQueue) {
            zzac.zzw(str);
            zzac.zzw(blockingQueue);
            this.zzbtY = blockingQueue;
            setName(str);
        }

        private void zza(InterruptedException interruptedException) {
            zzaud.this.zzKl().zzMa().zzj(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0052, code lost:
            r2 = com.google.android.gms.internal.zzaud.zzc(r5.zzbtU);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0058, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            com.google.android.gms.internal.zzaud.zza(r5.zzbtU).release();
            com.google.android.gms.internal.zzaud.zzc(r5.zzbtU).notifyAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0071, code lost:
            if (r5 != com.google.android.gms.internal.zzaud.zzd(r5.zzbtU)) goto L_0x0079;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0073, code lost:
            com.google.android.gms.internal.zzaud.zza(r5.zzbtU, (com.google.android.gms.internal.zzaud.zzd) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x007f, code lost:
            if (r5 != com.google.android.gms.internal.zzaud.zze(r5.zzbtU)) goto L_0x0087;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0081, code lost:
            com.google.android.gms.internal.zzaud.zzb(r5.zzbtU, (com.google.android.gms.internal.zzaud.zzd) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0087, code lost:
            r5.zzbtU.zzKl().zzLY().log("Current scheduler thread is neither worker nor network");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x0096, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0097, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r5 = this;
                r0 = 0
            L_0x0001:
                if (r0 != 0) goto L_0x0013
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this     // Catch:{ InterruptedException -> 0x000e }
                java.util.concurrent.Semaphore r1 = r1.zzbtQ     // Catch:{ InterruptedException -> 0x000e }
                r1.acquire()     // Catch:{ InterruptedException -> 0x000e }
                r0 = 1
                goto L_0x0001
            L_0x000e:
                r1 = move-exception
                r5.zza(r1)
                goto L_0x0001
            L_0x0013:
                r0 = 0
                java.util.concurrent.BlockingQueue<java.util.concurrent.FutureTask<?>> r1 = r5.zzbtY     // Catch:{ all -> 0x00a4 }
                java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x00a4 }
                java.util.concurrent.FutureTask r1 = (java.util.concurrent.FutureTask) r1     // Catch:{ all -> 0x00a4 }
                if (r1 == 0) goto L_0x0022
                r1.run()     // Catch:{ all -> 0x00a4 }
                goto L_0x0013
            L_0x0022:
                java.lang.Object r1 = r5.zzbtX     // Catch:{ all -> 0x00a4 }
                monitor-enter(r1)     // Catch:{ all -> 0x00a4 }
                java.util.concurrent.BlockingQueue<java.util.concurrent.FutureTask<?>> r2 = r5.zzbtY     // Catch:{ all -> 0x00a1 }
                java.lang.Object r2 = r2.peek()     // Catch:{ all -> 0x00a1 }
                if (r2 != 0) goto L_0x0041
                com.google.android.gms.internal.zzaud r2 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00a1 }
                boolean r2 = r2.zzbtR     // Catch:{ all -> 0x00a1 }
                if (r2 != 0) goto L_0x0041
                java.lang.Object r2 = r5.zzbtX     // Catch:{ InterruptedException -> 0x003d }
                r3 = 30000(0x7530, double:1.4822E-319)
                r2.wait(r3)     // Catch:{ InterruptedException -> 0x003d }
                goto L_0x0041
            L_0x003d:
                r2 = move-exception
                r5.zza(r2)     // Catch:{ all -> 0x00a1 }
            L_0x0041:
                monitor-exit(r1)     // Catch:{ all -> 0x00a1 }
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00a4 }
                java.lang.Object r1 = r1.zzbtP     // Catch:{ all -> 0x00a4 }
                monitor-enter(r1)     // Catch:{ all -> 0x00a4 }
                java.util.concurrent.BlockingQueue<java.util.concurrent.FutureTask<?>> r2 = r5.zzbtY     // Catch:{ all -> 0x009e }
                java.lang.Object r2 = r2.peek()     // Catch:{ all -> 0x009e }
                if (r2 != 0) goto L_0x009b
                monitor-exit(r1)     // Catch:{ all -> 0x009e }
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this
                java.lang.Object r2 = r1.zzbtP
                monitor-enter(r2)
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x0098 }
                java.util.concurrent.Semaphore r1 = r1.zzbtQ     // Catch:{ all -> 0x0098 }
                r1.release()     // Catch:{ all -> 0x0098 }
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x0098 }
                java.lang.Object r1 = r1.zzbtP     // Catch:{ all -> 0x0098 }
                r1.notifyAll()     // Catch:{ all -> 0x0098 }
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x0098 }
                com.google.android.gms.internal.zzaud$zzd r1 = r1.zzbtJ     // Catch:{ all -> 0x0098 }
                if (r5 != r1) goto L_0x0079
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x0098 }
                com.google.android.gms.internal.zzaud.zzd unused = r1.zzbtJ = r0     // Catch:{ all -> 0x0098 }
                goto L_0x0096
            L_0x0079:
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x0098 }
                com.google.android.gms.internal.zzaud$zzd r1 = r1.zzbtK     // Catch:{ all -> 0x0098 }
                if (r5 != r1) goto L_0x0087
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x0098 }
                com.google.android.gms.internal.zzaud.zzd unused = r1.zzbtK = r0     // Catch:{ all -> 0x0098 }
                goto L_0x0096
            L_0x0087:
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x0098 }
                com.google.android.gms.internal.zzatx r0 = r0.zzKl()     // Catch:{ all -> 0x0098 }
                com.google.android.gms.internal.zzatx$zza r0 = r0.zzLY()     // Catch:{ all -> 0x0098 }
                java.lang.String r1 = "Current scheduler thread is neither worker nor network"
                r0.log(r1)     // Catch:{ all -> 0x0098 }
            L_0x0096:
                monitor-exit(r2)     // Catch:{ all -> 0x0098 }
                return
            L_0x0098:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0098 }
                throw r0
            L_0x009b:
                monitor-exit(r1)     // Catch:{ all -> 0x009e }
                goto L_0x0013
            L_0x009e:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x009e }
                throw r2     // Catch:{ all -> 0x00a4 }
            L_0x00a1:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x00a1 }
                throw r2     // Catch:{ all -> 0x00a4 }
            L_0x00a4:
                r1 = move-exception
                com.google.android.gms.internal.zzaud r2 = com.google.android.gms.internal.zzaud.this
                java.lang.Object r2 = r2.zzbtP
                monitor-enter(r2)
                com.google.android.gms.internal.zzaud r3 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00eb }
                java.util.concurrent.Semaphore r3 = r3.zzbtQ     // Catch:{ all -> 0x00eb }
                r3.release()     // Catch:{ all -> 0x00eb }
                com.google.android.gms.internal.zzaud r3 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00eb }
                java.lang.Object r3 = r3.zzbtP     // Catch:{ all -> 0x00eb }
                r3.notifyAll()     // Catch:{ all -> 0x00eb }
                com.google.android.gms.internal.zzaud r3 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00eb }
                com.google.android.gms.internal.zzaud$zzd r3 = r3.zzbtJ     // Catch:{ all -> 0x00eb }
                if (r5 != r3) goto L_0x00cc
                com.google.android.gms.internal.zzaud r3 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00eb }
                com.google.android.gms.internal.zzaud.zzd unused = r3.zzbtJ = r0     // Catch:{ all -> 0x00eb }
                goto L_0x00e9
            L_0x00cc:
                com.google.android.gms.internal.zzaud r3 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00eb }
                com.google.android.gms.internal.zzaud$zzd r3 = r3.zzbtK     // Catch:{ all -> 0x00eb }
                if (r5 != r3) goto L_0x00da
                com.google.android.gms.internal.zzaud r3 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00eb }
                com.google.android.gms.internal.zzaud.zzd unused = r3.zzbtK = r0     // Catch:{ all -> 0x00eb }
                goto L_0x00e9
            L_0x00da:
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00eb }
                com.google.android.gms.internal.zzatx r0 = r0.zzKl()     // Catch:{ all -> 0x00eb }
                com.google.android.gms.internal.zzatx$zza r0 = r0.zzLY()     // Catch:{ all -> 0x00eb }
                java.lang.String r3 = "Current scheduler thread is neither worker nor network"
                r0.log(r3)     // Catch:{ all -> 0x00eb }
            L_0x00e9:
                monitor-exit(r2)     // Catch:{ all -> 0x00eb }
                throw r1
            L_0x00eb:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x00eb }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaud.zzd.run():void");
        }

        public void zzhA() {
            synchronized (this.zzbtX) {
                this.zzbtX.notifyAll();
            }
        }
    }

    zzaud(zzaue zzaue) {
        super(zzaue);
    }

    private void zza(zzc<?> zzc2) {
        synchronized (this.zzbtP) {
            this.zzbtL.add(zzc2);
            if (this.zzbtJ == null) {
                this.zzbtJ = new zzd("Measurement Worker", this.zzbtL);
                this.zzbtJ.setUncaughtExceptionHandler(this.zzbtN);
                this.zzbtJ.start();
            } else {
                this.zzbtJ.zzhA();
            }
        }
    }

    private void zza(FutureTask<?> futureTask) {
        synchronized (this.zzbtP) {
            this.zzbtM.add(futureTask);
            if (this.zzbtK == null) {
                this.zzbtK = new zzd("Measurement Network", this.zzbtM);
                this.zzbtK.setUncaughtExceptionHandler(this.zzbtO);
                this.zzbtK.start();
            } else {
                this.zzbtK.zzhA();
            }
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzJV() {
        super.zzJV();
    }

    public /* bridge */ /* synthetic */ void zzJW() {
        super.zzJW();
    }

    public void zzJX() {
        if (Thread.currentThread() != this.zzbtK) {
            throw new IllegalStateException("Call expected from network thread");
        }
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

    public boolean zzMq() {
        return Thread.currentThread() == this.zzbtJ;
    }

    /* access modifiers changed from: package-private */
    public ExecutorService zzMr() {
        ExecutorService executorService;
        synchronized (this.zzbtP) {
            if (this.zzbtI == null) {
                this.zzbtI = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.zzbtI;
        }
        return executorService;
    }

    public boolean zzbc() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public <V> Future<V> zzd(Callable<V> callable) throws IllegalStateException {
        zzob();
        zzac.zzw(callable);
        zzc zzc2 = new zzc(callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzbtJ) {
            zzc2.run();
            return zzc2;
        }
        zza((zzc<?>) zzc2);
        return zzc2;
    }

    public <V> Future<V> zze(Callable<V> callable) throws IllegalStateException {
        zzob();
        zzac.zzw(callable);
        zzc zzc2 = new zzc(callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzbtJ) {
            zzc2.run();
            return zzc2;
        }
        zza((zzc<?>) zzc2);
        return zzc2;
    }

    public void zzm(Runnable runnable) throws IllegalStateException {
        zzob();
        zzac.zzw(runnable);
        zza((zzc<?>) new zzc(runnable, false, "Task exception on worker thread"));
    }

    public void zzmR() {
        if (Thread.currentThread() != this.zzbtJ) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public void zzn(Runnable runnable) throws IllegalStateException {
        zzob();
        zzac.zzw(runnable);
        zza((FutureTask<?>) new zzc(runnable, false, "Task exception on network thread"));
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }
}
