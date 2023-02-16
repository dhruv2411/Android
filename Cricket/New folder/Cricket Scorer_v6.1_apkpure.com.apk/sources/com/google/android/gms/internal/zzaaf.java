package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.internal.zzaby;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzaaf<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zzaAg = new ThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        /* renamed from: zzvJ */
        public Boolean initialValue() {
            return false;
        }
    };
    private boolean zzK;
    private final Object zzaAh;
    protected final zza<R> zzaAi;
    protected final WeakReference<GoogleApiClient> zzaAj;
    private final ArrayList<PendingResult.zza> zzaAk;
    private ResultCallback<? super R> zzaAl;
    private final AtomicReference<zzaby.zzb> zzaAm;
    private zzb zzaAn;
    private volatile boolean zzaAo;
    private boolean zzaAp;
    private zzs zzaAq;
    private volatile zzabx<R> zzaAr;
    private boolean zzaAs;
    private Status zzair;
    /* access modifiers changed from: private */
    public R zzazt;
    private final CountDownLatch zztj;

    public static class zza<R extends Result> extends Handler {
        public zza() {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    zzb((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((zzaaf) message.obj).zzC(Status.zzazA);
                    return;
                default:
                    int i = message.what;
                    StringBuilder sb = new StringBuilder(45);
                    sb.append("Don't know how to handle message: ");
                    sb.append(i);
                    Log.wtf("BasePendingResult", sb.toString(), new Exception());
                    return;
            }
        }

        public void zza(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void zza(zzaaf<R> zzaaf, long j) {
            sendMessageDelayed(obtainMessage(2, zzaaf), j);
        }

        /* access modifiers changed from: protected */
        public void zzb(ResultCallback<? super R> resultCallback, R r) {
            try {
                resultCallback.onResult(r);
            } catch (RuntimeException e) {
                zzaaf.zzd(r);
                throw e;
            }
        }

        public void zzvK() {
            removeMessages(2);
        }
    }

    private final class zzb {
        private zzb() {
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            zzaaf.zzd(zzaaf.this.zzazt);
            super.finalize();
        }
    }

    @Deprecated
    zzaaf() {
        this.zzaAh = new Object();
        this.zztj = new CountDownLatch(1);
        this.zzaAk = new ArrayList<>();
        this.zzaAm = new AtomicReference<>();
        this.zzaAs = false;
        this.zzaAi = new zza<>(Looper.getMainLooper());
        this.zzaAj = new WeakReference<>((Object) null);
    }

    @Deprecated
    protected zzaaf(Looper looper) {
        this.zzaAh = new Object();
        this.zztj = new CountDownLatch(1);
        this.zzaAk = new ArrayList<>();
        this.zzaAm = new AtomicReference<>();
        this.zzaAs = false;
        this.zzaAi = new zza<>(looper);
        this.zzaAj = new WeakReference<>((Object) null);
    }

    protected zzaaf(GoogleApiClient googleApiClient) {
        this.zzaAh = new Object();
        this.zztj = new CountDownLatch(1);
        this.zzaAk = new ArrayList<>();
        this.zzaAm = new AtomicReference<>();
        this.zzaAs = false;
        this.zzaAi = new zza<>(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zzaAj = new WeakReference<>(googleApiClient);
    }

    private R get() {
        R r;
        synchronized (this.zzaAh) {
            zzac.zza(!this.zzaAo, (Object) "Result has already been consumed.");
            zzac.zza(isReady(), (Object) "Result is not ready.");
            r = this.zzazt;
            this.zzazt = null;
            this.zzaAl = null;
            this.zzaAo = true;
        }
        zzvG();
        return r;
    }

    private void zzc(R r) {
        this.zzazt = r;
        this.zzaAq = null;
        this.zztj.countDown();
        this.zzair = this.zzazt.getStatus();
        if (this.zzK) {
            this.zzaAl = null;
        } else if (this.zzaAl != null) {
            this.zzaAi.zzvK();
            this.zzaAi.zza(this.zzaAl, get());
        } else if (this.zzazt instanceof Releasable) {
            this.zzaAn = new zzb();
        }
        Iterator<PendingResult.zza> it = this.zzaAk.iterator();
        while (it.hasNext()) {
            it.next().zzy(this.zzair);
        }
        this.zzaAk.clear();
    }

    public static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                StringBuilder sb = new StringBuilder(18 + String.valueOf(valueOf).length());
                sb.append("Unable to release ");
                sb.append(valueOf);
                Log.w("BasePendingResult", sb.toString(), e);
            }
        }
    }

    private void zzvG() {
        zzaby.zzb andSet = this.zzaAm.getAndSet((Object) null);
        if (andSet != null) {
            andSet.zzc(this);
        }
    }

    public final R await() {
        boolean z = false;
        zzac.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread");
        zzac.zza(!this.zzaAo, (Object) "Result has already been consumed");
        if (this.zzaAr == null) {
            z = true;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            this.zztj.await();
        } catch (InterruptedException unused) {
            zzC(Status.zzazy);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = false;
        zzac.zza(j <= 0 || Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread when time is greater than zero.");
        zzac.zza(!this.zzaAo, (Object) "Result has already been consumed.");
        if (this.zzaAr == null) {
            z = true;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.zztj.await(j, timeUnit)) {
                zzC(Status.zzazA);
            }
        } catch (InterruptedException unused) {
            zzC(Status.zzazy);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:8|(2:10|11)|12|13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.zzaAh
            monitor-enter(r0)
            boolean r1 = r2.zzK     // Catch:{ all -> 0x002a }
            if (r1 != 0) goto L_0x0028
            boolean r1 = r2.zzaAo     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x000c
            goto L_0x0028
        L_0x000c:
            com.google.android.gms.common.internal.zzs r1 = r2.zzaAq     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0015
            com.google.android.gms.common.internal.zzs r1 = r2.zzaAq     // Catch:{ RemoteException -> 0x0015 }
            r1.cancel()     // Catch:{ RemoteException -> 0x0015 }
        L_0x0015:
            R r1 = r2.zzazt     // Catch:{ all -> 0x002a }
            zzd(r1)     // Catch:{ all -> 0x002a }
            r1 = 1
            r2.zzK = r1     // Catch:{ all -> 0x002a }
            com.google.android.gms.common.api.Status r1 = com.google.android.gms.common.api.Status.zzazB     // Catch:{ all -> 0x002a }
            com.google.android.gms.common.api.Result r1 = r2.zzc((com.google.android.gms.common.api.Status) r1)     // Catch:{ all -> 0x002a }
            r2.zzc(r1)     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x002a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaaf.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zzaAh) {
            z = this.zzK;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zztj.getCount() == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzaAh
            monitor-enter(r0)
            if (r5 != 0) goto L_0x000c
            r5 = 0
            r4.zzaAl = r5     // Catch:{ all -> 0x000a }
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return
        L_0x000a:
            r5 = move-exception
            goto L_0x003c
        L_0x000c:
            boolean r1 = r4.zzaAo     // Catch:{ all -> 0x000a }
            r2 = 1
            r1 = r1 ^ r2
            java.lang.String r3 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzac.zza((boolean) r1, (java.lang.Object) r3)     // Catch:{ all -> 0x000a }
            com.google.android.gms.internal.zzabx<R> r1 = r4.zzaAr     // Catch:{ all -> 0x000a }
            if (r1 != 0) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            r2 = 0
        L_0x001b:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzac.zza((boolean) r2, (java.lang.Object) r1)     // Catch:{ all -> 0x000a }
            boolean r1 = r4.isCanceled()     // Catch:{ all -> 0x000a }
            if (r1 == 0) goto L_0x0028
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return
        L_0x0028:
            boolean r1 = r4.isReady()     // Catch:{ all -> 0x000a }
            if (r1 == 0) goto L_0x0038
            com.google.android.gms.internal.zzaaf$zza<R> r1 = r4.zzaAi     // Catch:{ all -> 0x000a }
            com.google.android.gms.common.api.Result r2 = r4.get()     // Catch:{ all -> 0x000a }
            r1.zza(r5, r2)     // Catch:{ all -> 0x000a }
            goto L_0x003a
        L_0x0038:
            r4.zzaAl = r5     // Catch:{ all -> 0x000a }
        L_0x003a:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return
        L_0x003c:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaaf.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r5, long r6, java.util.concurrent.TimeUnit r8) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzaAh
            monitor-enter(r0)
            if (r5 != 0) goto L_0x000c
            r5 = 0
            r4.zzaAl = r5     // Catch:{ all -> 0x000a }
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return
        L_0x000a:
            r5 = move-exception
            goto L_0x0045
        L_0x000c:
            boolean r1 = r4.zzaAo     // Catch:{ all -> 0x000a }
            r2 = 1
            r1 = r1 ^ r2
            java.lang.String r3 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzac.zza((boolean) r1, (java.lang.Object) r3)     // Catch:{ all -> 0x000a }
            com.google.android.gms.internal.zzabx<R> r1 = r4.zzaAr     // Catch:{ all -> 0x000a }
            if (r1 != 0) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            r2 = 0
        L_0x001b:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzac.zza((boolean) r2, (java.lang.Object) r1)     // Catch:{ all -> 0x000a }
            boolean r1 = r4.isCanceled()     // Catch:{ all -> 0x000a }
            if (r1 == 0) goto L_0x0028
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return
        L_0x0028:
            boolean r1 = r4.isReady()     // Catch:{ all -> 0x000a }
            if (r1 == 0) goto L_0x0038
            com.google.android.gms.internal.zzaaf$zza<R> r6 = r4.zzaAi     // Catch:{ all -> 0x000a }
            com.google.android.gms.common.api.Result r7 = r4.get()     // Catch:{ all -> 0x000a }
            r6.zza(r5, r7)     // Catch:{ all -> 0x000a }
            goto L_0x0043
        L_0x0038:
            r4.zzaAl = r5     // Catch:{ all -> 0x000a }
            com.google.android.gms.internal.zzaaf$zza<R> r5 = r4.zzaAi     // Catch:{ all -> 0x000a }
            long r6 = r8.toMillis(r6)     // Catch:{ all -> 0x000a }
            r5.zza(r4, (long) r6)     // Catch:{ all -> 0x000a }
        L_0x0043:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaaf.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        zzac.zza(!this.zzaAo, (Object) "Result has already been consumed.");
        synchronized (this.zzaAh) {
            boolean z = false;
            zzac.zza(this.zzaAr == null, (Object) "Cannot call then() twice.");
            if (this.zzaAl == null) {
                z = true;
            }
            zzac.zza(z, (Object) "Cannot call then() if callbacks are set.");
            zzac.zza(!this.zzK, (Object) "Cannot call then() if result was canceled.");
            this.zzaAs = true;
            this.zzaAr = new zzabx<>(this.zzaAj);
            then = this.zzaAr.then(resultTransform);
            if (isReady()) {
                this.zzaAi.zza(this.zzaAr, get());
            } else {
                this.zzaAl = this.zzaAr;
            }
        }
        return then;
    }

    public final void zzC(Status status) {
        synchronized (this.zzaAh) {
            if (!isReady()) {
                zzb(zzc(status));
                this.zzaAp = true;
            }
        }
    }

    public final void zza(PendingResult.zza zza2) {
        zzac.zzb(zza2 != null, (Object) "Callback cannot be null.");
        synchronized (this.zzaAh) {
            if (isReady()) {
                zza2.zzy(this.zzair);
            } else {
                this.zzaAk.add(zza2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzs zzs) {
        synchronized (this.zzaAh) {
            this.zzaAq = zzs;
        }
    }

    public void zza(zzaby.zzb zzb2) {
        this.zzaAm.set(zzb2);
    }

    public final void zzb(R r) {
        synchronized (this.zzaAh) {
            if (this.zzaAp || this.zzK) {
                zzd(r);
                return;
            }
            isReady();
            zzac.zza(!isReady(), (Object) "Results have already been set");
            zzac.zza(!this.zzaAo, (Object) "Result has already been consumed");
            zzc(r);
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public abstract R zzc(Status status);

    public boolean zzvF() {
        boolean isCanceled;
        synchronized (this.zzaAh) {
            if (((GoogleApiClient) this.zzaAj.get()) == null || !this.zzaAs) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public void zzvH() {
        setResultCallback((ResultCallback) null);
    }

    public void zzvI() {
        this.zzaAs = this.zzaAs || zzaAg.get().booleanValue();
    }

    public Integer zzvr() {
        return null;
    }
}
