package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.internal.zzaad;
import com.google.android.gms.internal.zzaaz;
import com.google.android.gms.internal.zzabc;
import com.google.android.gms.internal.zzaby;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzaat extends GoogleApiClient implements zzabc.zza {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Lock zzaAG;
    final zzg zzaAL;
    final Map<Api<?>, Boolean> zzaAO;
    final Queue<zzaad.zza<?, ?>> zzaAU = new LinkedList();
    private final zzm zzaBJ;
    private zzabc zzaBK = null;
    private volatile boolean zzaBL;
    private long zzaBM = 120000;
    private long zzaBN = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
    private final zza zzaBO;
    zzaaz zzaBP;
    final Map<Api.zzc<?>, Api.zze> zzaBQ;
    Set<Scope> zzaBR = new HashSet();
    private final zzabi zzaBS = new zzabi();
    private final ArrayList<zzaag> zzaBT;
    private Integer zzaBU = null;
    Set<zzabx> zzaBV = null;
    final zzaby zzaBW;
    private final zzm.zza zzaBX = new zzm.zza() {
        public boolean isConnected() {
            return zzaat.this.isConnected();
        }

        public Bundle zzuC() {
            return null;
        }
    };
    private final int zzazl;
    private final GoogleApiAvailability zzazn;
    final Api.zza<? extends zzbai, zzbaj> zzazo;
    private boolean zzazr;
    private final Looper zzrs;

    final class zza extends Handler {
        zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    zzaat.this.zzwn();
                    return;
                case 2:
                    zzaat.this.resume();
                    return;
                default:
                    int i = message.what;
                    StringBuilder sb = new StringBuilder(31);
                    sb.append("Unknown message id: ");
                    sb.append(i);
                    Log.w("GoogleApiClientImpl", sb.toString());
                    return;
            }
        }
    }

    static class zzb extends zzaaz.zza {
        private WeakReference<zzaat> zzaCc;

        zzb(zzaat zzaat) {
            this.zzaCc = new WeakReference<>(zzaat);
        }

        public void zzvE() {
            zzaat zzaat = (zzaat) this.zzaCc.get();
            if (zzaat != null) {
                zzaat.resume();
            }
        }
    }

    public zzaat(Context context, Lock lock, Looper looper, zzg zzg, GoogleApiAvailability googleApiAvailability, Api.zza<? extends zzbai, zzbaj> zza2, Map<Api<?>, Boolean> map, List<GoogleApiClient.ConnectionCallbacks> list, List<GoogleApiClient.OnConnectionFailedListener> list2, Map<Api.zzc<?>, Api.zze> map2, int i, int i2, ArrayList<zzaag> arrayList, boolean z) {
        Looper looper2 = looper;
        this.mContext = context;
        this.zzaAG = lock;
        this.zzazr = z;
        this.zzaBJ = new zzm(looper2, this.zzaBX);
        this.zzrs = looper2;
        this.zzaBO = new zza(looper2);
        this.zzazn = googleApiAvailability;
        this.zzazl = i;
        if (this.zzazl >= 0) {
            this.zzaBU = Integer.valueOf(i2);
        }
        this.zzaAO = map;
        this.zzaBQ = map2;
        this.zzaBT = arrayList;
        this.zzaBW = new zzaby(this.zzaBQ);
        for (GoogleApiClient.ConnectionCallbacks registerConnectionCallbacks : list) {
            this.zzaBJ.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (GoogleApiClient.OnConnectionFailedListener registerConnectionFailedListener : list2) {
            this.zzaBJ.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.zzaAL = zzg;
        this.zzazo = zza2;
    }

    /* access modifiers changed from: private */
    public void resume() {
        this.zzaAG.lock();
        try {
            if (isResuming()) {
                zzwm();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public static int zza(Iterable<Api.zze> iterable, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        for (Api.zze next : iterable) {
            if (next.zzrd()) {
                z2 = true;
            }
            if (next.zzrr()) {
                z3 = true;
            }
        }
        if (z2) {
            return (!z3 || !z) ? 1 : 2;
        }
        return 3;
    }

    /* access modifiers changed from: private */
    public void zza(final GoogleApiClient googleApiClient, final zzabt zzabt, final boolean z) {
        zzacf.zzaGM.zzg(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            /* renamed from: zzp */
            public void onResult(@NonNull Status status) {
                zzn.zzas(zzaat.this.mContext).zzrD();
                if (status.isSuccess() && zzaat.this.isConnected()) {
                    zzaat.this.reconnect();
                }
                zzabt.zzb(status);
                if (z) {
                    googleApiClient.disconnect();
                }
            }
        });
    }

    private void zzb(@NonNull zzabd zzabd) {
        if (this.zzazl >= 0) {
            zzaaa.zza(zzabd).zzcA(this.zzazl);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    /* JADX WARNING: type inference failed for: r12v0, types: [com.google.android.gms.internal.zzabc] */
    /* JADX WARNING: type inference failed for: r0v20, types: [com.google.android.gms.internal.zzaav] */
    /* JADX WARNING: type inference failed for: r0v21, types: [com.google.android.gms.internal.zzaak] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzcD(int r14) {
        /*
            r13 = this;
            java.lang.Integer r0 = r13.zzaBU
            if (r0 != 0) goto L_0x000b
            java.lang.Integer r0 = java.lang.Integer.valueOf(r14)
            r13.zzaBU = r0
            goto L_0x005c
        L_0x000b:
            java.lang.Integer r0 = r13.zzaBU
            int r0 = r0.intValue()
            if (r0 == r14) goto L_0x005c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = zzcE(r14)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.Integer r2 = r13.zzaBU
            int r2 = r2.intValue()
            java.lang.String r2 = zzcE(r2)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r3 = 51
            java.lang.String r4 = java.lang.String.valueOf(r1)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Cannot use sign-in mode: "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = ". Mode was already set to "
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = r4.toString()
            r0.<init>(r1)
            throw r0
        L_0x005c:
            com.google.android.gms.internal.zzabc r0 = r13.zzaBK
            if (r0 == 0) goto L_0x0061
            return
        L_0x0061:
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.api.Api$zze> r0 = r13.zzaBQ
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
            r2 = r1
        L_0x006d:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0089
            java.lang.Object r3 = r0.next()
            com.google.android.gms.common.api.Api$zze r3 = (com.google.android.gms.common.api.Api.zze) r3
            boolean r4 = r3.zzrd()
            r5 = 1
            if (r4 == 0) goto L_0x0081
            r1 = r5
        L_0x0081:
            boolean r3 = r3.zzrr()
            if (r3 == 0) goto L_0x006d
            r2 = r5
            goto L_0x006d
        L_0x0089:
            java.lang.Integer r0 = r13.zzaBU
            int r0 = r0.intValue()
            switch(r0) {
                case 1: goto L_0x00d0;
                case 2: goto L_0x0093;
                case 3: goto L_0x00e4;
                default: goto L_0x0092;
            }
        L_0x0092:
            goto L_0x00e4
        L_0x0093:
            if (r1 == 0) goto L_0x00e4
            boolean r0 = r13.zzazr
            if (r0 == 0) goto L_0x00b6
            com.google.android.gms.internal.zzaak r12 = new com.google.android.gms.internal.zzaak
            android.content.Context r1 = r13.mContext
            java.util.concurrent.locks.Lock r2 = r13.zzaAG
            android.os.Looper r3 = r13.zzrs
            com.google.android.gms.common.GoogleApiAvailability r4 = r13.zzazn
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.api.Api$zze> r5 = r13.zzaBQ
            com.google.android.gms.common.internal.zzg r6 = r13.zzaAL
            java.util.Map<com.google.android.gms.common.api.Api<?>, java.lang.Boolean> r7 = r13.zzaAO
            com.google.android.gms.common.api.Api$zza<? extends com.google.android.gms.internal.zzbai, com.google.android.gms.internal.zzbaj> r8 = r13.zzazo
            java.util.ArrayList<com.google.android.gms.internal.zzaag> r9 = r13.zzaBT
            r11 = 1
            r0 = r12
            r10 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r13.zzaBK = r12
            return
        L_0x00b6:
            android.content.Context r0 = r13.mContext
            java.util.concurrent.locks.Lock r2 = r13.zzaAG
            android.os.Looper r3 = r13.zzrs
            com.google.android.gms.common.GoogleApiAvailability r4 = r13.zzazn
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.api.Api$zze> r5 = r13.zzaBQ
            com.google.android.gms.common.internal.zzg r6 = r13.zzaAL
            java.util.Map<com.google.android.gms.common.api.Api<?>, java.lang.Boolean> r7 = r13.zzaAO
            com.google.android.gms.common.api.Api$zza<? extends com.google.android.gms.internal.zzbai, com.google.android.gms.internal.zzbaj> r8 = r13.zzazo
            java.util.ArrayList<com.google.android.gms.internal.zzaag> r9 = r13.zzaBT
            r1 = r13
            com.google.android.gms.internal.zzaai r0 = com.google.android.gms.internal.zzaai.zza(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r13.zzaBK = r0
            return
        L_0x00d0:
            if (r1 != 0) goto L_0x00da
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead."
            r0.<init>(r1)
            throw r0
        L_0x00da:
            if (r2 == 0) goto L_0x00e4
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead."
            r0.<init>(r1)
            throw r0
        L_0x00e4:
            boolean r0 = r13.zzazr
            if (r0 == 0) goto L_0x0105
            if (r2 != 0) goto L_0x0105
            com.google.android.gms.internal.zzaak r12 = new com.google.android.gms.internal.zzaak
            android.content.Context r1 = r13.mContext
            java.util.concurrent.locks.Lock r2 = r13.zzaAG
            android.os.Looper r3 = r13.zzrs
            com.google.android.gms.common.GoogleApiAvailability r4 = r13.zzazn
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.api.Api$zze> r5 = r13.zzaBQ
            com.google.android.gms.common.internal.zzg r6 = r13.zzaAL
            java.util.Map<com.google.android.gms.common.api.Api<?>, java.lang.Boolean> r7 = r13.zzaAO
            com.google.android.gms.common.api.Api$zza<? extends com.google.android.gms.internal.zzbai, com.google.android.gms.internal.zzbaj> r8 = r13.zzazo
            java.util.ArrayList<com.google.android.gms.internal.zzaag> r9 = r13.zzaBT
            r11 = 0
            r0 = r12
            r10 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            goto L_0x011f
        L_0x0105:
            com.google.android.gms.internal.zzaav r12 = new com.google.android.gms.internal.zzaav
            android.content.Context r1 = r13.mContext
            java.util.concurrent.locks.Lock r3 = r13.zzaAG
            android.os.Looper r4 = r13.zzrs
            com.google.android.gms.common.GoogleApiAvailability r5 = r13.zzazn
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.api.Api$zze> r6 = r13.zzaBQ
            com.google.android.gms.common.internal.zzg r7 = r13.zzaAL
            java.util.Map<com.google.android.gms.common.api.Api<?>, java.lang.Boolean> r8 = r13.zzaAO
            com.google.android.gms.common.api.Api$zza<? extends com.google.android.gms.internal.zzbai, com.google.android.gms.internal.zzbaj> r9 = r13.zzazo
            java.util.ArrayList<com.google.android.gms.internal.zzaag> r10 = r13.zzaBT
            r0 = r12
            r2 = r13
            r11 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
        L_0x011f:
            r13.zzaBK = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaat.zzcD(int):void");
    }

    static String zzcE(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    private void zzwm() {
        this.zzaBJ.zzxY();
        this.zzaBK.connect();
    }

    /* access modifiers changed from: private */
    public void zzwn() {
        this.zzaAG.lock();
        try {
            if (zzwp()) {
                zzwm();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public ConnectionResult blockingConnect() {
        boolean z = true;
        zzac.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        this.zzaAG.lock();
        try {
            if (this.zzazl >= 0) {
                if (this.zzaBU == null) {
                    z = false;
                }
                zzac.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzaBU == null) {
                this.zzaBU = Integer.valueOf(zza(this.zzaBQ.values(), false));
            } else if (this.zzaBU.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzcD(this.zzaBU.intValue());
            this.zzaBJ.zzxY();
            return this.zzaBK.blockingConnect();
        } finally {
            this.zzaAG.unlock();
        }
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        zzac.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        zzac.zzb(timeUnit, (Object) "TimeUnit must not be null");
        this.zzaAG.lock();
        try {
            if (this.zzaBU == null) {
                this.zzaBU = Integer.valueOf(zza(this.zzaBQ.values(), false));
            } else if (this.zzaBU.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzcD(this.zzaBU.intValue());
            this.zzaBJ.zzxY();
            return this.zzaBK.blockingConnect(j, timeUnit);
        } finally {
            this.zzaAG.unlock();
        }
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzac.zza(isConnected(), (Object) "GoogleApiClient is not connected yet.");
        zzac.zza(this.zzaBU.intValue() != 2, (Object) "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        final zzabt zzabt = new zzabt((GoogleApiClient) this);
        if (this.zzaBQ.containsKey(zzacf.zzaid)) {
            zza(this, zzabt, false);
            return zzabt;
        }
        final AtomicReference atomicReference = new AtomicReference();
        GoogleApiClient build = new GoogleApiClient.Builder(this.mContext).addApi(zzacf.API).addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            public void onConnected(Bundle bundle) {
                zzaat.this.zza((GoogleApiClient) atomicReference.get(), zzabt, true);
            }

            public void onConnectionSuspended(int i) {
            }
        }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener(this) {
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                zzabt.zzb(new Status(8));
            }
        }).setHandler(this.zzaBO).build();
        atomicReference.set(build);
        build.connect();
        return zzabt;
    }

    public void connect() {
        this.zzaAG.lock();
        try {
            boolean z = false;
            if (this.zzazl >= 0) {
                if (this.zzaBU != null) {
                    z = true;
                }
                zzac.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzaBU == null) {
                this.zzaBU = Integer.valueOf(zza(this.zzaBQ.values(), false));
            } else if (this.zzaBU.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.zzaBU.intValue());
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void connect(int i) {
        this.zzaAG.lock();
        boolean z = true;
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Illegal sign-in mode: ");
            sb.append(i);
            zzac.zzb(z, (Object) sb.toString());
            zzcD(i);
            zzwm();
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void disconnect() {
        this.zzaAG.lock();
        try {
            this.zzaBW.release();
            if (this.zzaBK != null) {
                this.zzaBK.disconnect();
            }
            this.zzaBS.release();
            for (zzaad.zza zza2 : this.zzaAU) {
                zza2.zza((zzaby.zzb) null);
                zza2.cancel();
            }
            this.zzaAU.clear();
            if (this.zzaBK != null) {
                zzwp();
                this.zzaBJ.zzxX();
                this.zzaAG.unlock();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.mContext);
        printWriter.append(str).append("mResuming=").print(this.zzaBL);
        printWriter.append(" mWorkQueue.size()=").print(this.zzaAU.size());
        this.zzaBW.dump(printWriter);
        if (this.zzaBK != null) {
            this.zzaBK.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        ConnectionResult connectionResult;
        this.zzaAG.lock();
        try {
            if (!isConnected() && !isResuming()) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            } else if (this.zzaBQ.containsKey(api.zzvg())) {
                ConnectionResult connectionResult2 = this.zzaBK.getConnectionResult(api);
                if (connectionResult2 == null) {
                    if (isResuming()) {
                        connectionResult = ConnectionResult.zzayj;
                    } else {
                        Log.w("GoogleApiClientImpl", zzwr());
                        Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                        connectionResult = new ConnectionResult(8, (PendingIntent) null);
                    }
                    return connectionResult;
                }
                this.zzaAG.unlock();
                return connectionResult2;
            } else {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public Looper getLooper() {
        return this.zzrs;
    }

    public int getSessionId() {
        return System.identityHashCode(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r3 = r2.zzaBQ.get(r3.zzvg());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasConnectedApi(@android.support.annotation.NonNull com.google.android.gms.common.api.Api<?> r3) {
        /*
            r2 = this;
            boolean r0 = r2.isConnected()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.api.Api$zze> r0 = r2.zzaBQ
            com.google.android.gms.common.api.Api$zzc r3 = r3.zzvg()
            java.lang.Object r3 = r0.get(r3)
            com.google.android.gms.common.api.Api$zze r3 = (com.google.android.gms.common.api.Api.zze) r3
            if (r3 == 0) goto L_0x001d
            boolean r3 = r3.isConnected()
            if (r3 == 0) goto L_0x001d
            r1 = 1
        L_0x001d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaat.hasConnectedApi(com.google.android.gms.common.api.Api):boolean");
    }

    public boolean isConnected() {
        return this.zzaBK != null && this.zzaBK.isConnected();
    }

    public boolean isConnecting() {
        return this.zzaBK != null && this.zzaBK.isConnecting();
    }

    public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zzaBJ.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zzaBJ.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    /* access modifiers changed from: package-private */
    public boolean isResuming() {
        return this.zzaBL;
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zzaBJ.registerConnectionCallbacks(connectionCallbacks);
    }

    public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zzaBJ.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        zzb(new zzabd(fragmentActivity));
    }

    public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zzaBJ.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zzaBJ.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    @NonNull
    public <C extends Api.zze> C zza(@NonNull Api.zzc<C> zzc) {
        C c = (Api.zze) this.zzaBQ.get(zzc);
        zzac.zzb(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public <A extends Api.zzb, R extends Result, T extends zzaad.zza<R, A>> T zza(@NonNull T t) {
        zzac.zzb(t.zzvg() != null, (Object) "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.zzaBQ.containsKey(t.zzvg());
        String name = t.getApi() != null ? t.getApi().getName() : "the API";
        StringBuilder sb = new StringBuilder(65 + String.valueOf(name).length());
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        zzac.zzb(containsKey, (Object) sb.toString());
        this.zzaAG.lock();
        try {
            if (this.zzaBK == null) {
                this.zzaAU.add(t);
            } else {
                t = this.zzaBK.zza(t);
            }
            return t;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void zza(zzabx zzabx) {
        this.zzaAG.lock();
        try {
            if (this.zzaBV == null) {
                this.zzaBV = new HashSet();
            }
            this.zzaBV.add(zzabx);
        } finally {
            this.zzaAG.unlock();
        }
    }

    public boolean zza(@NonNull Api<?> api) {
        return this.zzaBQ.containsKey(api.zzvg());
    }

    public boolean zza(zzabq zzabq) {
        return this.zzaBK != null && this.zzaBK.zza(zzabq);
    }

    public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T zzb(@NonNull T t) {
        zzac.zzb(t.zzvg() != null, (Object) "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.zzaBQ.containsKey(t.zzvg());
        String name = t.getApi() != null ? t.getApi().getName() : "the API";
        StringBuilder sb = new StringBuilder(65 + String.valueOf(name).length());
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        zzac.zzb(containsKey, (Object) sb.toString());
        this.zzaAG.lock();
        try {
            if (this.zzaBK == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (isResuming()) {
                this.zzaAU.add(t);
                while (!this.zzaAU.isEmpty()) {
                    zzaad.zza remove = this.zzaAU.remove();
                    this.zzaBW.zzb(remove);
                    remove.zzB(Status.zzazz);
                }
            } else {
                t = this.zzaBK.zzb(t);
            }
            return t;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void zzb(zzabx zzabx) {
        String str;
        String str2;
        Exception exc;
        this.zzaAG.lock();
        try {
            if (this.zzaBV == null) {
                str = "GoogleApiClientImpl";
                str2 = "Attempted to remove pending transform when no transforms are registered.";
                exc = new Exception();
            } else if (!this.zzaBV.remove(zzabx)) {
                str = "GoogleApiClientImpl";
                str2 = "Failed to remove pending transform - this may lead to memory leaks!";
                exc = new Exception();
            } else {
                if (!zzwq()) {
                    this.zzaBK.zzvM();
                }
            }
            Log.wtf(str, str2, exc);
        } finally {
            this.zzaAG.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public <C extends Api.zze> C zzc(Api.zzc<?> zzc) {
        C c = (Api.zze) this.zzaBQ.get(zzc);
        zzac.zzb(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public void zzc(int i, boolean z) {
        if (i == 1 && !z) {
            zzwo();
        }
        this.zzaBW.zzxd();
        this.zzaBJ.zzcV(i);
        this.zzaBJ.zzxX();
        if (i == 2) {
            zzwm();
        }
    }

    public void zzc(ConnectionResult connectionResult) {
        if (!this.zzazn.zzd(this.mContext, connectionResult.getErrorCode())) {
            zzwp();
        }
        if (!isResuming()) {
            this.zzaBJ.zzn(connectionResult);
            this.zzaBJ.zzxX();
        }
    }

    public void zzo(Bundle bundle) {
        while (!this.zzaAU.isEmpty()) {
            zzb(this.zzaAU.remove());
        }
        this.zzaBJ.zzq(bundle);
    }

    public <L> zzabh<L> zzr(@NonNull L l) {
        this.zzaAG.lock();
        try {
            return this.zzaBS.zzb(l, this.zzrs);
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void zzvn() {
        if (this.zzaBK != null) {
            this.zzaBK.zzvn();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzwo() {
        if (!isResuming()) {
            this.zzaBL = true;
            if (this.zzaBP == null) {
                this.zzaBP = this.zzazn.zza(this.mContext.getApplicationContext(), (zzaaz.zza) new zzb(this));
            }
            this.zzaBO.sendMessageDelayed(this.zzaBO.obtainMessage(1), this.zzaBM);
            this.zzaBO.sendMessageDelayed(this.zzaBO.obtainMessage(2), this.zzaBN);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzwp() {
        if (!isResuming()) {
            return false;
        }
        this.zzaBL = false;
        this.zzaBO.removeMessages(2);
        this.zzaBO.removeMessages(1);
        if (this.zzaBP != null) {
            this.zzaBP.unregister();
            this.zzaBP = null;
        }
        return true;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public boolean zzwq() {
        this.zzaAG.lock();
        try {
            if (this.zzaBV == null) {
                this.zzaAG.unlock();
                return false;
            }
            boolean z = !this.zzaBV.isEmpty();
            this.zzaAG.unlock();
            return z;
        } catch (Throwable th) {
            this.zzaAG.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public String zzwr() {
        StringWriter stringWriter = new StringWriter();
        dump("", (FileDescriptor) null, new PrintWriter(stringWriter), (String[]) null);
        return stringWriter.toString();
    }
}
