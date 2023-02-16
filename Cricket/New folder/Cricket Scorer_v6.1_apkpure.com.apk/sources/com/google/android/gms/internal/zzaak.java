package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzaad;
import com.google.android.gms.internal.zzaby;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzaak implements zzabc {
    /* access modifiers changed from: private */
    public final Lock zzaAG;
    private final zzg zzaAL;
    /* access modifiers changed from: private */
    public final Map<Api.zzc<?>, zzaaj<?>> zzaAM = new HashMap();
    /* access modifiers changed from: private */
    public final Map<Api.zzc<?>, zzaaj<?>> zzaAN = new HashMap();
    private final Map<Api<?>, Boolean> zzaAO;
    /* access modifiers changed from: private */
    public final zzaat zzaAP;
    private final zze zzaAQ;
    /* access modifiers changed from: private */
    public final Condition zzaAR;
    private final boolean zzaAS;
    /* access modifiers changed from: private */
    public final boolean zzaAT;
    private final Queue<zzaad.zza<?, ?>> zzaAU = new LinkedList();
    /* access modifiers changed from: private */
    public boolean zzaAV;
    /* access modifiers changed from: private */
    public Map<zzzz<?>, ConnectionResult> zzaAW;
    /* access modifiers changed from: private */
    public Map<zzzz<?>, ConnectionResult> zzaAX;
    private zzb zzaAY;
    /* access modifiers changed from: private */
    public ConnectionResult zzaAZ;
    private final zzaax zzayX;
    private final Looper zzrs;

    private class zza implements OnCompleteListener<Void> {
        private zza() {
        }

        public void onComplete(@NonNull Task<Void> task) {
            zzaak zzaak;
            ConnectionResult connectionResult;
            Map zzd;
            zzaak.this.zzaAG.lock();
            try {
                if (zzaak.this.zzaAV) {
                    if (task.isSuccessful()) {
                        Map unused = zzaak.this.zzaAW = new ArrayMap(zzaak.this.zzaAM.size());
                        for (zzaaj apiKey : zzaak.this.zzaAM.values()) {
                            zzaak.this.zzaAW.put(apiKey.getApiKey(), ConnectionResult.zzayj);
                        }
                    } else {
                        if (task.getException() instanceof com.google.android.gms.common.api.zzb) {
                            com.google.android.gms.common.api.zzb zzb = (com.google.android.gms.common.api.zzb) task.getException();
                            if (zzaak.this.zzaAT) {
                                Map unused2 = zzaak.this.zzaAW = new ArrayMap(zzaak.this.zzaAM.size());
                                for (zzaaj zzaaj : zzaak.this.zzaAM.values()) {
                                    zzzz apiKey2 = zzaaj.getApiKey();
                                    ConnectionResult zza = zzb.zza(zzaaj);
                                    if (zzaak.this.zza((zzaaj<?>) zzaaj, zza)) {
                                        zzd = zzaak.this.zzaAW;
                                        zza = new ConnectionResult(16);
                                    } else {
                                        zzd = zzaak.this.zzaAW;
                                    }
                                    zzd.put(apiKey2, zza);
                                }
                            } else {
                                Map unused3 = zzaak.this.zzaAW = zzb.zzvj();
                            }
                            zzaak = zzaak.this;
                            connectionResult = zzaak.this.zzvX();
                        } else {
                            Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                            Map unused4 = zzaak.this.zzaAW = Collections.emptyMap();
                            zzaak = zzaak.this;
                            connectionResult = new ConnectionResult(8);
                        }
                        ConnectionResult unused5 = zzaak.zzaAZ = connectionResult;
                    }
                    if (zzaak.this.zzaAX != null) {
                        zzaak.this.zzaAW.putAll(zzaak.this.zzaAX);
                        ConnectionResult unused6 = zzaak.this.zzaAZ = zzaak.this.zzvX();
                    }
                    if (zzaak.this.zzaAZ == null) {
                        zzaak.this.zzvV();
                        zzaak.this.zzvW();
                    } else {
                        boolean unused7 = zzaak.this.zzaAV = false;
                        zzaak.this.zzaAP.zzc(zzaak.this.zzaAZ);
                    }
                    zzaak.this.zzaAR.signalAll();
                    zzaak.this.zzaAG.unlock();
                }
            } finally {
                zzaak.this.zzaAG.unlock();
            }
        }
    }

    private class zzb implements OnCompleteListener<Void> {
        private zzabq zzaBb;

        zzb(zzabq zzabq) {
            this.zzaBb = zzabq;
        }

        /* access modifiers changed from: package-private */
        public void cancel() {
            this.zzaBb.zzrq();
        }

        public void onComplete(@NonNull Task<Void> task) {
            Map zzg;
            zzaak.this.zzaAG.lock();
            try {
                if (!zzaak.this.zzaAV) {
                    this.zzaBb.zzrq();
                    return;
                }
                if (task.isSuccessful()) {
                    Map unused = zzaak.this.zzaAX = new ArrayMap(zzaak.this.zzaAN.size());
                    for (zzaaj apiKey : zzaak.this.zzaAN.values()) {
                        zzaak.this.zzaAX.put(apiKey.getApiKey(), ConnectionResult.zzayj);
                    }
                } else if (task.getException() instanceof com.google.android.gms.common.api.zzb) {
                    com.google.android.gms.common.api.zzb zzb = (com.google.android.gms.common.api.zzb) task.getException();
                    if (zzaak.this.zzaAT) {
                        Map unused2 = zzaak.this.zzaAX = new ArrayMap(zzaak.this.zzaAN.size());
                        for (zzaaj zzaaj : zzaak.this.zzaAN.values()) {
                            zzzz apiKey2 = zzaaj.getApiKey();
                            ConnectionResult zza = zzb.zza(zzaaj);
                            if (zzaak.this.zza((zzaaj<?>) zzaaj, zza)) {
                                zzg = zzaak.this.zzaAX;
                                zza = new ConnectionResult(16);
                            } else {
                                zzg = zzaak.this.zzaAX;
                            }
                            zzg.put(apiKey2, zza);
                        }
                    } else {
                        Map unused3 = zzaak.this.zzaAX = zzb.zzvj();
                    }
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    Map unused4 = zzaak.this.zzaAX = Collections.emptyMap();
                }
                if (zzaak.this.isConnected()) {
                    zzaak.this.zzaAW.putAll(zzaak.this.zzaAX);
                    if (zzaak.this.zzvX() == null) {
                        zzaak.this.zzvV();
                        zzaak.this.zzvW();
                        zzaak.this.zzaAR.signalAll();
                    }
                }
                this.zzaBb.zzrq();
                zzaak.this.zzaAG.unlock();
            } finally {
                zzaak.this.zzaAG.unlock();
            }
        }
    }

    public zzaak(Context context, Lock lock, Looper looper, zze zze, Map<Api.zzc<?>, Api.zze> map, zzg zzg, Map<Api<?>, Boolean> map2, Api.zza<? extends zzbai, zzbaj> zza2, ArrayList<zzaag> arrayList, zzaat zzaat, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zzaAG = lock;
        Looper looper2 = looper;
        this.zzrs = looper2;
        this.zzaAR = lock.newCondition();
        this.zzaAQ = zze;
        this.zzaAP = zzaat;
        this.zzaAO = map2;
        zzg zzg2 = zzg;
        this.zzaAL = zzg2;
        this.zzaAS = z;
        HashMap hashMap = new HashMap();
        for (Api next : map2.keySet()) {
            hashMap.put(next.zzvg(), next);
        }
        HashMap hashMap2 = new HashMap();
        Iterator<zzaag> it = arrayList.iterator();
        while (it.hasNext()) {
            zzaag next2 = it.next();
            hashMap2.put(next2.zzaxf, next2);
        }
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        for (Map.Entry next3 : map.entrySet()) {
            Api api = (Api) hashMap.get(next3.getKey());
            Api.zze zze2 = (Api.zze) next3.getValue();
            if (zze2.zzvh()) {
                if (!this.zzaAO.get(api).booleanValue()) {
                    z3 = z6;
                    z4 = true;
                } else {
                    z3 = z6;
                    z4 = z7;
                }
                z2 = true;
            } else {
                z2 = z5;
                z4 = z7;
                z3 = false;
            }
            zzaaj zzaaj = r1;
            Api.zze zze3 = zze2;
            Map.Entry entry = next3;
            zzaaj zzaaj2 = new zzaaj(context, api, looper2, zze2, (zzaag) hashMap2.get(api), zzg2, zza2);
            this.zzaAM.put((Api.zzc) entry.getKey(), zzaaj);
            if (zze3.zzrd()) {
                this.zzaAN.put((Api.zzc) entry.getKey(), zzaaj);
            }
            z7 = z4;
            z6 = z3;
            z5 = z2;
            looper2 = looper;
        }
        this.zzaAT = z5 && !z6 && !z7;
        this.zzayX = zzaax.zzww();
    }

    /* access modifiers changed from: private */
    public boolean zza(zzaaj<?> zzaaj, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zzaAO.get(zzaaj.getApi()).booleanValue() && zzaaj.zzvU().zzvh() && this.zzaAQ.isUserResolvableError(connectionResult.getErrorCode());
    }

    @Nullable
    private ConnectionResult zzb(@NonNull Api.zzc<?> zzc) {
        this.zzaAG.lock();
        try {
            zzaaj zzaaj = this.zzaAM.get(zzc);
            if (this.zzaAW != null && zzaaj != null) {
                return this.zzaAW.get(zzaaj.getApiKey());
            }
            this.zzaAG.unlock();
            return null;
        } finally {
            this.zzaAG.unlock();
        }
    }

    private <T extends zzaad.zza<? extends Result, ? extends Api.zzb>> boolean zzd(@NonNull T t) {
        Api.zzc zzvg = t.zzvg();
        ConnectionResult zzb2 = zzb((Api.zzc<?>) zzvg);
        if (zzb2 == null || zzb2.getErrorCode() != 4) {
            return false;
        }
        t.zzB(new Status(4, (String) null, this.zzayX.zza((zzzz<?>) this.zzaAM.get(zzvg).getApiKey(), this.zzaAP.getSessionId())));
        return true;
    }

    /* access modifiers changed from: private */
    public void zzvV() {
        if (this.zzaAL == null) {
            this.zzaAP.zzaBR = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zzaAL.zzxL());
        Map<Api<?>, zzg.zza> zzxN = this.zzaAL.zzxN();
        for (Api next : zzxN.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(next);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(zzxN.get(next).zzakq);
            }
        }
        this.zzaAP.zzaBR = hashSet;
    }

    /* access modifiers changed from: private */
    public void zzvW() {
        while (!this.zzaAU.isEmpty()) {
            zzb(this.zzaAU.remove());
        }
        this.zzaAP.zzo((Bundle) null);
    }

    /* access modifiers changed from: private */
    @Nullable
    public ConnectionResult zzvX() {
        ConnectionResult connectionResult = null;
        int i = 0;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zzaaj next : this.zzaAM.values()) {
            Api api = next.getApi();
            ConnectionResult connectionResult3 = this.zzaAW.get(next.getApiKey());
            if (!connectionResult3.isSuccess() && (!this.zzaAO.get(api).booleanValue() || connectionResult3.hasResolution() || this.zzaAQ.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() != 4 || !this.zzaAS) {
                    int priority = api.zzve().getPriority();
                    if (connectionResult == null || i > priority) {
                        connectionResult = connectionResult3;
                        i = priority;
                    }
                } else {
                    int priority2 = api.zzve().getPriority();
                    if (connectionResult2 == null || i2 > priority2) {
                        connectionResult2 = connectionResult3;
                        i2 = priority2;
                    }
                }
            }
        }
        return (connectionResult == null || connectionResult2 == null || i <= i2) ? connectionResult : connectionResult2;
    }

    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzaAR.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, (PendingIntent) null);
            }
        }
        return isConnected() ? ConnectionResult.zzayj : this.zzaAZ != null ? this.zzaAZ : new ConnectionResult(13, (PendingIntent) null);
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, (PendingIntent) null);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, (PendingIntent) null);
                }
            } else {
                nanos = this.zzaAR.awaitNanos(nanos);
            }
        }
        return isConnected() ? ConnectionResult.zzayj : this.zzaAZ != null ? this.zzaAZ : new ConnectionResult(13, (PendingIntent) null);
    }

    public void connect() {
        this.zzaAG.lock();
        try {
            if (!this.zzaAV) {
                this.zzaAV = true;
                this.zzaAW = null;
                this.zzaAX = null;
                this.zzaAY = null;
                this.zzaAZ = null;
                this.zzayX.zzvx();
                this.zzayX.zza((Iterable<? extends zzc<?>>) this.zzaAM.values()).addOnCompleteListener((Executor) new zzadb(this.zzrs), new zza());
                this.zzaAG.unlock();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void disconnect() {
        this.zzaAG.lock();
        try {
            this.zzaAV = false;
            this.zzaAW = null;
            this.zzaAX = null;
            if (this.zzaAY != null) {
                this.zzaAY.cancel();
                this.zzaAY = null;
            }
            this.zzaAZ = null;
            while (!this.zzaAU.isEmpty()) {
                zzaad.zza remove = this.zzaAU.remove();
                remove.zza((zzaby.zzb) null);
                remove.cancel();
            }
            this.zzaAR.signalAll();
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zzb(api.zzvg());
    }

    public boolean isConnected() {
        this.zzaAG.lock();
        try {
            return this.zzaAW != null && this.zzaAZ == null;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public boolean isConnecting() {
        this.zzaAG.lock();
        try {
            return this.zzaAW == null && this.zzaAV;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public <A extends Api.zzb, R extends Result, T extends zzaad.zza<R, A>> T zza(@NonNull T t) {
        if (this.zzaAS && zzd(t)) {
            return t;
        }
        if (!isConnected()) {
            this.zzaAU.add(t);
            return t;
        }
        this.zzaAP.zzaBW.zzb(t);
        return this.zzaAM.get(t.zzvg()).doRead(t);
    }

    /* JADX INFO: finally extract failed */
    public boolean zza(zzabq zzabq) {
        this.zzaAG.lock();
        try {
            if (!this.zzaAV || zzvN()) {
                this.zzaAG.unlock();
                return false;
            }
            this.zzayX.zzvx();
            this.zzaAY = new zzb(zzabq);
            this.zzayX.zza((Iterable<? extends zzc<?>>) this.zzaAN.values()).addOnCompleteListener((Executor) new zzadb(this.zzrs), this.zzaAY);
            this.zzaAG.unlock();
            return true;
        } catch (Throwable th) {
            this.zzaAG.unlock();
            throw th;
        }
    }

    public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T zzb(@NonNull T t) {
        Api.zzc zzvg = t.zzvg();
        if (this.zzaAS && zzd(t)) {
            return t;
        }
        this.zzaAP.zzaBW.zzb(t);
        return this.zzaAM.get(zzvg).doWrite(t);
    }

    public void zzvM() {
    }

    public boolean zzvN() {
        this.zzaAG.lock();
        try {
            if (this.zzaAV) {
                if (this.zzaAS) {
                    for (Api.zzc<?> zzb2 : this.zzaAN.keySet()) {
                        ConnectionResult zzb3 = zzb(zzb2);
                        if (zzb3 != null) {
                            if (!zzb3.isSuccess()) {
                            }
                        }
                    }
                    this.zzaAG.unlock();
                    return true;
                }
            }
            return false;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void zzvn() {
        this.zzaAG.lock();
        try {
            this.zzayX.zzvn();
            if (this.zzaAY != null) {
                this.zzaAY.cancel();
                this.zzaAY = null;
            }
            if (this.zzaAX == null) {
                this.zzaAX = new ArrayMap(this.zzaAN.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zzaaj<?> apiKey : this.zzaAN.values()) {
                this.zzaAX.put(apiKey.getApiKey(), connectionResult);
            }
            if (this.zzaAW != null) {
                this.zzaAW.putAll(this.zzaAX);
            }
        } finally {
            this.zzaAG.unlock();
        }
    }
}
