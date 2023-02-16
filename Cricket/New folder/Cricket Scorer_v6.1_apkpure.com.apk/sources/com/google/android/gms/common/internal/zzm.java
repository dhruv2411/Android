package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzm implements Handler.Callback {
    private final Handler mHandler;
    private final zza zzaFU;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzaFV = new ArrayList<>();
    final ArrayList<GoogleApiClient.ConnectionCallbacks> zzaFW = new ArrayList<>();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzaFX = new ArrayList<>();
    private volatile boolean zzaFY = false;
    private final AtomicInteger zzaFZ = new AtomicInteger(0);
    private boolean zzaGa = false;
    private final Object zzrJ = new Object();

    public interface zza {
        boolean isConnected();

        Bundle zzuC();
    }

    public zzm(Looper looper, zza zza2) {
        this.zzaFU = zza2;
        this.mHandler = new Handler(looper, this);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
            synchronized (this.zzrJ) {
                if (this.zzaFY && this.zzaFU.isConnected() && this.zzaFV.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zzaFU.zzuC());
                }
            }
            return true;
        }
        int i = message.what;
        StringBuilder sb = new StringBuilder(45);
        sb.append("Don't know how to handle message: ");
        sb.append(i);
        Log.wtf("GmsClientEvents", sb.toString(), new Exception());
        return false;
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        zzac.zzw(connectionCallbacks);
        synchronized (this.zzrJ) {
            contains = this.zzaFV.contains(connectionCallbacks);
        }
        return contains;
    }

    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        zzac.zzw(onConnectionFailedListener);
        synchronized (this.zzrJ) {
            contains = this.zzaFX.contains(onConnectionFailedListener);
        }
        return contains;
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzac.zzw(connectionCallbacks);
        synchronized (this.zzrJ) {
            if (this.zzaFV.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                StringBuilder sb = new StringBuilder(62 + String.valueOf(valueOf).length());
                sb.append("registerConnectionCallbacks(): listener ");
                sb.append(valueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.zzaFV.add(connectionCallbacks);
            }
        }
        if (this.zzaFU.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzw(onConnectionFailedListener);
        synchronized (this.zzrJ) {
            if (this.zzaFX.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder sb = new StringBuilder(67 + String.valueOf(valueOf).length());
                sb.append("registerConnectionFailedListener(): listener ");
                sb.append(valueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.zzaFX.add(onConnectionFailedListener);
            }
        }
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzac.zzw(connectionCallbacks);
        synchronized (this.zzrJ) {
            if (!this.zzaFV.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                StringBuilder sb = new StringBuilder(52 + String.valueOf(valueOf).length());
                sb.append("unregisterConnectionCallbacks(): listener ");
                sb.append(valueOf);
                sb.append(" not found");
                Log.w("GmsClientEvents", sb.toString());
            } else if (this.zzaGa) {
                this.zzaFW.add(connectionCallbacks);
            }
        }
    }

    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzw(onConnectionFailedListener);
        synchronized (this.zzrJ) {
            if (!this.zzaFX.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder sb = new StringBuilder(57 + String.valueOf(valueOf).length());
                sb.append("unregisterConnectionFailedListener(): listener ");
                sb.append(valueOf);
                sb.append(" not found");
                Log.w("GmsClientEvents", sb.toString());
            }
        }
    }

    public void zzcV(int i) {
        zzac.zza(Looper.myLooper() == this.mHandler.getLooper(), (Object) "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzrJ) {
            this.zzaGa = true;
            ArrayList arrayList = new ArrayList(this.zzaFV);
            int i2 = this.zzaFZ.get();
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.zzaFY) {
                    break;
                } else if (this.zzaFZ.get() != i2) {
                    break;
                } else if (this.zzaFV.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.zzaFW.clear();
            this.zzaGa = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzn(com.google.android.gms.common.ConnectionResult r6) {
        /*
            r5 = this;
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Handler r1 = r5.mHandler
            android.os.Looper r1 = r1.getLooper()
            r2 = 1
            if (r0 != r1) goto L_0x000f
            r0 = r2
            goto L_0x0010
        L_0x000f:
            r0 = 0
        L_0x0010:
            java.lang.String r1 = "onConnectionFailure must only be called on the Handler thread"
            com.google.android.gms.common.internal.zzac.zza((boolean) r0, (java.lang.Object) r1)
            android.os.Handler r0 = r5.mHandler
            r0.removeMessages(r2)
            java.lang.Object r0 = r5.zzrJ
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0057 }
            java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener> r2 = r5.zzaFX     // Catch:{ all -> 0x0057 }
            r1.<init>(r2)     // Catch:{ all -> 0x0057 }
            java.util.concurrent.atomic.AtomicInteger r2 = r5.zzaFZ     // Catch:{ all -> 0x0057 }
            int r2 = r2.get()     // Catch:{ all -> 0x0057 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0057 }
        L_0x002e:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0057 }
            if (r3 == 0) goto L_0x0055
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0057 }
            com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener r3 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r3     // Catch:{ all -> 0x0057 }
            boolean r4 = r5.zzaFY     // Catch:{ all -> 0x0057 }
            if (r4 == 0) goto L_0x0053
            java.util.concurrent.atomic.AtomicInteger r4 = r5.zzaFZ     // Catch:{ all -> 0x0057 }
            int r4 = r4.get()     // Catch:{ all -> 0x0057 }
            if (r4 == r2) goto L_0x0047
            goto L_0x0053
        L_0x0047:
            java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener> r4 = r5.zzaFX     // Catch:{ all -> 0x0057 }
            boolean r4 = r4.contains(r3)     // Catch:{ all -> 0x0057 }
            if (r4 == 0) goto L_0x002e
            r3.onConnectionFailed(r6)     // Catch:{ all -> 0x0057 }
            goto L_0x002e
        L_0x0053:
            monitor-exit(r0)     // Catch:{ all -> 0x0057 }
            return
        L_0x0055:
            monitor-exit(r0)     // Catch:{ all -> 0x0057 }
            return
        L_0x0057:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0057 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzm.zzn(com.google.android.gms.common.ConnectionResult):void");
    }

    public void zzq(Bundle bundle) {
        boolean z = true;
        zzac.zza(Looper.myLooper() == this.mHandler.getLooper(), (Object) "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zzrJ) {
            zzac.zzaw(!this.zzaGa);
            this.mHandler.removeMessages(1);
            this.zzaGa = true;
            if (this.zzaFW.size() != 0) {
                z = false;
            }
            zzac.zzaw(z);
            ArrayList arrayList = new ArrayList(this.zzaFV);
            int i = this.zzaFZ.get();
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.zzaFY || !this.zzaFU.isConnected()) {
                    break;
                } else if (this.zzaFZ.get() != i) {
                    break;
                } else if (!this.zzaFW.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.zzaFW.clear();
            this.zzaGa = false;
        }
    }

    public void zzxX() {
        this.zzaFY = false;
        this.zzaFZ.incrementAndGet();
    }

    public void zzxY() {
        this.zzaFY = true;
    }
}
