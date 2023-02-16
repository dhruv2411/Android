package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.internal.zzv;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzf<T extends IInterface> {
    public static final String[] zzaFs = {"service_esmobile", "service_googleme"};
    private final Context mContext;
    final Handler mHandler;
    private final com.google.android.gms.common.zze zzaAQ;
    private int zzaFa;
    private long zzaFb;
    private long zzaFc;
    private int zzaFd;
    private long zzaFe;
    private final zzn zzaFf;
    /* access modifiers changed from: private */
    public final Object zzaFg;
    /* access modifiers changed from: private */
    public zzv zzaFh;
    protected C0015zzf zzaFi;
    private T zzaFj;
    /* access modifiers changed from: private */
    public final ArrayList<zze<?>> zzaFk;
    private zzh zzaFl;
    private int zzaFm;
    /* access modifiers changed from: private */
    public final zzb zzaFn;
    /* access modifiers changed from: private */
    public final zzc zzaFo;
    private final int zzaFp;
    private final String zzaFq;
    protected AtomicInteger zzaFr;
    private final Object zzrJ;
    private final Looper zzrs;

    private abstract class zza extends zze<Boolean> {
        public final int statusCode;
        public final Bundle zzaFt;

        @BinderThread
        protected zza(int i, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.zzaFt = bundle;
        }

        /* JADX WARNING: type inference failed for: r4v12, types: [android.os.Parcelable] */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed */
        /* renamed from: zzb */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void zzu(java.lang.Boolean r4) {
            /*
                r3 = this;
                r0 = 1
                r1 = 0
                if (r4 != 0) goto L_0x000a
                com.google.android.gms.common.internal.zzf r4 = com.google.android.gms.common.internal.zzf.this
                r4.zza((int) r0, r1)
                return
            L_0x000a:
                int r4 = r3.statusCode
                if (r4 == 0) goto L_0x003e
                r2 = 10
                if (r4 == r2) goto L_0x0031
                com.google.android.gms.common.internal.zzf r4 = com.google.android.gms.common.internal.zzf.this
                r4.zza((int) r0, r1)
                android.os.Bundle r4 = r3.zzaFt
                if (r4 == 0) goto L_0x0026
                android.os.Bundle r4 = r3.zzaFt
                java.lang.String r0 = "pendingIntent"
                android.os.Parcelable r4 = r4.getParcelable(r0)
                r1 = r4
                android.app.PendingIntent r1 = (android.app.PendingIntent) r1
            L_0x0026:
                com.google.android.gms.common.ConnectionResult r4 = new com.google.android.gms.common.ConnectionResult
                int r0 = r3.statusCode
                r4.<init>(r0, r1)
            L_0x002d:
                r3.zzm(r4)
                return
            L_0x0031:
                com.google.android.gms.common.internal.zzf r4 = com.google.android.gms.common.internal.zzf.this
                r4.zza((int) r0, r1)
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = "A fatal developer error has occurred. Check the logs for further information."
                r4.<init>(r0)
                throw r4
            L_0x003e:
                boolean r4 = r3.zzxG()
                if (r4 != 0) goto L_0x0051
                com.google.android.gms.common.internal.zzf r4 = com.google.android.gms.common.internal.zzf.this
                r4.zza((int) r0, r1)
                com.google.android.gms.common.ConnectionResult r4 = new com.google.android.gms.common.ConnectionResult
                r0 = 8
                r4.<init>(r0, r1)
                goto L_0x002d
            L_0x0051:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzf.zza.zzu(java.lang.Boolean):void");
        }

        /* access modifiers changed from: protected */
        public abstract void zzm(ConnectionResult connectionResult);

        /* access modifiers changed from: protected */
        public abstract boolean zzxG();
    }

    public interface zzb {
        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface zzc {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    final class zzd extends Handler {
        public zzd(Looper looper) {
            super(looper);
        }

        private void zza(Message message) {
            ((zze) message.obj).unregister();
        }

        private boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 5;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.app.PendingIntent} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r6) {
            /*
                r5 = this;
                com.google.android.gms.common.internal.zzf r0 = com.google.android.gms.common.internal.zzf.this
                java.util.concurrent.atomic.AtomicInteger r0 = r0.zzaFr
                int r0 = r0.get()
                int r1 = r6.arg1
                if (r0 == r1) goto L_0x0016
                boolean r0 = r5.zzb(r6)
                if (r0 == 0) goto L_0x0015
                r5.zza(r6)
            L_0x0015:
                return
            L_0x0016:
                int r0 = r6.what
                r1 = 1
                if (r0 == r1) goto L_0x0020
                int r0 = r6.what
                r2 = 5
                if (r0 != r2) goto L_0x002c
            L_0x0020:
                com.google.android.gms.common.internal.zzf r0 = com.google.android.gms.common.internal.zzf.this
                boolean r0 = r0.isConnecting()
                if (r0 != 0) goto L_0x002c
                r5.zza(r6)
                return
            L_0x002c:
                int r0 = r6.what
                r2 = 3
                r3 = 0
                if (r0 != r2) goto L_0x0051
                java.lang.Object r0 = r6.obj
                boolean r0 = r0 instanceof android.app.PendingIntent
                if (r0 == 0) goto L_0x003d
                java.lang.Object r0 = r6.obj
                r3 = r0
                android.app.PendingIntent r3 = (android.app.PendingIntent) r3
            L_0x003d:
                com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult
                int r6 = r6.arg2
                r0.<init>(r6, r3)
                com.google.android.gms.common.internal.zzf r6 = com.google.android.gms.common.internal.zzf.this
                com.google.android.gms.common.internal.zzf$zzf r6 = r6.zzaFi
                r6.zzg(r0)
                com.google.android.gms.common.internal.zzf r6 = com.google.android.gms.common.internal.zzf.this
                r6.onConnectionFailed(r0)
                return
            L_0x0051:
                int r0 = r6.what
                r2 = 4
                if (r0 != r2) goto L_0x007b
                com.google.android.gms.common.internal.zzf r0 = com.google.android.gms.common.internal.zzf.this
                r0.zza((int) r2, r3)
                com.google.android.gms.common.internal.zzf r0 = com.google.android.gms.common.internal.zzf.this
                com.google.android.gms.common.internal.zzf$zzb r0 = r0.zzaFn
                if (r0 == 0) goto L_0x006e
                com.google.android.gms.common.internal.zzf r0 = com.google.android.gms.common.internal.zzf.this
                com.google.android.gms.common.internal.zzf$zzb r0 = r0.zzaFn
                int r4 = r6.arg2
                r0.onConnectionSuspended(r4)
            L_0x006e:
                com.google.android.gms.common.internal.zzf r0 = com.google.android.gms.common.internal.zzf.this
                int r6 = r6.arg2
                r0.onConnectionSuspended(r6)
                com.google.android.gms.common.internal.zzf r6 = com.google.android.gms.common.internal.zzf.this
                boolean unused = r6.zza((int) r2, (int) r1, r3)
                return
            L_0x007b:
                int r0 = r6.what
                r1 = 2
                if (r0 != r1) goto L_0x008c
                com.google.android.gms.common.internal.zzf r0 = com.google.android.gms.common.internal.zzf.this
                boolean r0 = r0.isConnected()
                if (r0 != 0) goto L_0x008c
                r5.zza(r6)
                return
            L_0x008c:
                boolean r0 = r5.zzb(r6)
                if (r0 == 0) goto L_0x009a
                java.lang.Object r6 = r6.obj
                com.google.android.gms.common.internal.zzf$zze r6 = (com.google.android.gms.common.internal.zzf.zze) r6
                r6.zzxH()
                return
            L_0x009a:
                java.lang.String r0 = "GmsClient"
                int r6 = r6.what
                r1 = 45
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>(r1)
                java.lang.String r1 = "Don't know how to handle message: "
                r2.append(r1)
                r2.append(r6)
                java.lang.String r6 = r2.toString()
                java.lang.Exception r1 = new java.lang.Exception
                r1.<init>()
                android.util.Log.wtf(r0, r6, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzf.zzd.handleMessage(android.os.Message):void");
        }
    }

    protected abstract class zze<TListener> {
        private TListener mListener;
        private boolean zzaFv = false;

        public zze(TListener tlistener) {
            this.mListener = tlistener;
        }

        public void unregister() {
            zzxI();
            synchronized (zzf.this.zzaFk) {
                zzf.this.zzaFk.remove(this);
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzu(TListener tlistener);

        public void zzxH() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.mListener;
                if (this.zzaFv) {
                    String valueOf = String.valueOf(this);
                    StringBuilder sb = new StringBuilder(47 + String.valueOf(valueOf).length());
                    sb.append("Callback proxy ");
                    sb.append(valueOf);
                    sb.append(" being reused. This is not safe.");
                    Log.w("GmsClient", sb.toString());
                }
            }
            if (tlistener != null) {
                try {
                    zzu(tlistener);
                } catch (RuntimeException e) {
                    throw e;
                }
            }
            synchronized (this) {
                this.zzaFv = true;
            }
            unregister();
        }

        public void zzxI() {
            synchronized (this) {
                this.mListener = null;
            }
        }
    }

    /* renamed from: com.google.android.gms.common.internal.zzf$zzf  reason: collision with other inner class name */
    public interface C0015zzf {
        void zzg(@NonNull ConnectionResult connectionResult);
    }

    public static final class zzg extends zzu.zza {
        private zzf zzaFw;
        private final int zzaFx;

        public zzg(@NonNull zzf zzf, int i) {
            this.zzaFw = zzf;
            this.zzaFx = i;
        }

        private void zzxJ() {
            this.zzaFw = null;
        }

        @BinderThread
        public void zza(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            zzac.zzb(this.zzaFw, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzaFw.zza(i, iBinder, bundle, this.zzaFx);
            zzxJ();
        }

        @BinderThread
        public void zzb(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }
    }

    public final class zzh implements ServiceConnection {
        private final int zzaFx;

        public zzh(int i) {
            this.zzaFx = i;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            zzf zzf;
            int i;
            if (iBinder == null) {
                zzf = zzf.this;
                i = 8;
            } else {
                synchronized (zzf.this.zzaFg) {
                    zzv unused = zzf.this.zzaFh = zzv.zza.zzbu(iBinder);
                }
                zzf = zzf.this;
                i = 0;
            }
            zzf.zza(i, (Bundle) null, this.zzaFx);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (zzf.this.zzaFg) {
                zzv unused = zzf.this.zzaFh = null;
            }
            zzf.this.mHandler.sendMessage(zzf.this.mHandler.obtainMessage(4, this.zzaFx, 1));
        }
    }

    protected class zzi implements C0015zzf {
        public zzi() {
        }

        public void zzg(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                zzf.this.zza((zzr) null, zzf.this.zzxF());
            } else if (zzf.this.zzaFo != null) {
                zzf.this.zzaFo.onConnectionFailed(connectionResult);
            }
        }
    }

    protected final class zzj extends zza {
        public final IBinder zzaFy;

        @BinderThread
        public zzj(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzaFy = iBinder;
        }

        /* access modifiers changed from: protected */
        public void zzm(ConnectionResult connectionResult) {
            if (zzf.this.zzaFo != null) {
                zzf.this.zzaFo.onConnectionFailed(connectionResult);
            }
            zzf.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public boolean zzxG() {
            try {
                String interfaceDescriptor = this.zzaFy.getInterfaceDescriptor();
                if (!zzf.this.zzeA().equals(interfaceDescriptor)) {
                    String valueOf = String.valueOf(zzf.this.zzeA());
                    StringBuilder sb = new StringBuilder(34 + String.valueOf(valueOf).length() + String.valueOf(interfaceDescriptor).length());
                    sb.append("service descriptor mismatch: ");
                    sb.append(valueOf);
                    sb.append(" vs. ");
                    sb.append(interfaceDescriptor);
                    Log.e("GmsClient", sb.toString());
                    return false;
                }
                IInterface zzh = zzf.this.zzh(this.zzaFy);
                if (zzh == null || !zzf.this.zza(2, 3, zzh)) {
                    return false;
                }
                Bundle zzuC = zzf.this.zzuC();
                if (zzf.this.zzaFn != null) {
                    zzf.this.zzaFn.onConnected(zzuC);
                }
                return true;
            } catch (RemoteException unused) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    protected final class zzk extends zza {
        @BinderThread
        public zzk(int i, Bundle bundle) {
            super(i, bundle);
        }

        /* access modifiers changed from: protected */
        public void zzm(ConnectionResult connectionResult) {
            zzf.this.zzaFi.zzg(connectionResult);
            zzf.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public boolean zzxG() {
            zzf.this.zzaFi.zzg(ConnectionResult.zzayj);
            return true;
        }
    }

    protected zzf(Context context, Looper looper, int i, zzb zzb2, zzc zzc2, String str) {
        this(context, looper, zzn.zzaU(context), com.google.android.gms.common.zze.zzuY(), i, (zzb) zzac.zzw(zzb2), (zzc) zzac.zzw(zzc2), str);
    }

    protected zzf(Context context, Looper looper, zzn zzn, com.google.android.gms.common.zze zze2, int i, zzb zzb2, zzc zzc2, String str) {
        this.zzrJ = new Object();
        this.zzaFg = new Object();
        this.zzaFk = new ArrayList<>();
        this.zzaFm = 1;
        this.zzaFr = new AtomicInteger(0);
        this.mContext = (Context) zzac.zzb(context, (Object) "Context must not be null");
        this.zzrs = (Looper) zzac.zzb(looper, (Object) "Looper must not be null");
        this.zzaFf = (zzn) zzac.zzb(zzn, (Object) "Supervisor must not be null");
        this.zzaAQ = (com.google.android.gms.common.zze) zzac.zzb(zze2, (Object) "API availability must not be null");
        this.mHandler = new zzd(looper);
        this.zzaFp = i;
        this.zzaFn = zzb2;
        this.zzaFo = zzc2;
        this.zzaFq = str;
    }

    /* access modifiers changed from: private */
    public void zza(int i, T t) {
        boolean z = false;
        if ((i == 3) == (t != null)) {
            z = true;
        }
        zzac.zzax(z);
        synchronized (this.zzrJ) {
            this.zzaFm = i;
            this.zzaFj = t;
            switch (i) {
                case 1:
                    zzxy();
                    break;
                case 2:
                    zzxx();
                    break;
                case 3:
                    zza(t);
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean zza(int i, int i2, T t) {
        synchronized (this.zzrJ) {
            if (this.zzaFm != i) {
                return false;
            }
            zza(i2, t);
            return true;
        }
    }

    private void zzxx() {
        if (this.zzaFl != null) {
            String valueOf = String.valueOf(zzez());
            String valueOf2 = String.valueOf(zzxv());
            StringBuilder sb = new StringBuilder(70 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append("Calling connect() while still connected, missing disconnect() for ");
            sb.append(valueOf);
            sb.append(" on ");
            sb.append(valueOf2);
            Log.e("GmsClient", sb.toString());
            this.zzaFf.zzb(zzez(), zzxv(), this.zzaFl, zzxw());
            this.zzaFr.incrementAndGet();
        }
        this.zzaFl = new zzh(this.zzaFr.get());
        if (!this.zzaFf.zza(zzez(), zzxv(), this.zzaFl, zzxw())) {
            String valueOf3 = String.valueOf(zzez());
            String valueOf4 = String.valueOf(zzxv());
            StringBuilder sb2 = new StringBuilder(34 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
            sb2.append("unable to connect to service: ");
            sb2.append(valueOf3);
            sb2.append(" on ");
            sb2.append(valueOf4);
            Log.e("GmsClient", sb2.toString());
            zza(16, (Bundle) null, this.zzaFr.get());
        }
    }

    private void zzxy() {
        if (this.zzaFl != null) {
            this.zzaFf.zzb(zzez(), zzxv(), this.zzaFl, zzxw());
            this.zzaFl = null;
        }
    }

    public void disconnect() {
        this.zzaFr.incrementAndGet();
        synchronized (this.zzaFk) {
            int size = this.zzaFk.size();
            for (int i = 0; i < size; i++) {
                this.zzaFk.get(i).zzxI();
            }
            this.zzaFk.clear();
        }
        synchronized (this.zzaFg) {
            this.zzaFh = null;
        }
        zza(1, (IInterface) null);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        zzv zzv;
        String str2;
        String str3;
        synchronized (this.zzrJ) {
            i = this.zzaFm;
            t = this.zzaFj;
        }
        synchronized (this.zzaFg) {
            zzv = this.zzaFh;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                str2 = "DISCONNECTED";
                break;
            case 2:
                str2 = "CONNECTING";
                break;
            case 3:
                str2 = "CONNECTED";
                break;
            case 4:
                str2 = "DISCONNECTING";
                break;
            default:
                str2 = "UNKNOWN";
                break;
        }
        printWriter.print(str2);
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.append("null");
        } else {
            printWriter.append(zzeA()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (zzv == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(zzv.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzaFc > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzaFc;
            String valueOf = String.valueOf(simpleDateFormat.format(new Date(this.zzaFc)));
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
            sb.append(j);
            sb.append(" ");
            sb.append(valueOf);
            append.println(sb.toString());
        }
        if (this.zzaFb > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzaFa) {
                case 1:
                    str3 = "CAUSE_SERVICE_DISCONNECTED";
                    break;
                case 2:
                    str3 = "CAUSE_NETWORK_LOST";
                    break;
                default:
                    str3 = String.valueOf(this.zzaFa);
                    break;
            }
            printWriter.append(str3);
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzaFb;
            String valueOf2 = String.valueOf(simpleDateFormat.format(new Date(this.zzaFb)));
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 21);
            sb2.append(j2);
            sb2.append(" ");
            sb2.append(valueOf2);
            append2.println(sb2.toString());
        }
        if (this.zzaFe > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzaFd));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzaFe;
            String valueOf3 = String.valueOf(simpleDateFormat.format(new Date(this.zzaFe)));
            StringBuilder sb3 = new StringBuilder(21 + String.valueOf(valueOf3).length());
            sb3.append(j3);
            sb3.append(" ");
            sb3.append(valueOf3);
            append3.println(sb3.toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzrs;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaFm == 3;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaFm == 2;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzaFd = connectionResult.getErrorCode();
        this.zzaFe = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onConnectionSuspended(int i) {
        this.zzaFa = i;
        this.zzaFb = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i2, -1, new zzk(i, bundle)));
    }

    /* access modifiers changed from: protected */
    public void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzj(i, iBinder, bundle)));
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void zza(@NonNull T t) {
        this.zzaFc = System.currentTimeMillis();
    }

    public void zza(@NonNull C0015zzf zzf) {
        this.zzaFi = (C0015zzf) zzac.zzb(zzf, (Object) "Connection progress callbacks cannot be null.");
        zza(2, (IInterface) null);
    }

    public void zza(@NonNull C0015zzf zzf, int i, @Nullable PendingIntent pendingIntent) {
        this.zzaFi = (C0015zzf) zzac.zzb(zzf, (Object) "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaFr.get(), i, pendingIntent));
    }

    @WorkerThread
    public void zza(zzr zzr, Set<Scope> set) {
        zzj zzp = new zzj(this.zzaFp).zzdm(this.mContext.getPackageName()).zzp(zzqL());
        if (set != null) {
            zzp.zzf((Collection<Scope>) set);
        }
        if (zzrd()) {
            zzp.zzf(zzxB()).zzb(zzr);
        } else if (zzxE()) {
            zzp.zzf(getAccount());
        }
        zzp.zza(zzxA());
        try {
            synchronized (this.zzaFg) {
                if (this.zzaFh != null) {
                    this.zzaFh.zza(new zzg(this, this.zzaFr.get()), zzp);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            zzcS(1);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            zza(8, (IBinder) null, (Bundle) null, this.zzaFr.get());
        }
    }

    public void zzcS(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzaFr.get(), i));
    }

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String zzeA();

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String zzez();

    /* access modifiers changed from: protected */
    @Nullable
    public abstract T zzh(IBinder iBinder);

    /* access modifiers changed from: protected */
    public Bundle zzqL() {
        return new Bundle();
    }

    public boolean zzrd() {
        return false;
    }

    public boolean zzrr() {
        return false;
    }

    public Intent zzrs() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public Bundle zzuC() {
        return null;
    }

    public boolean zzvh() {
        return true;
    }

    @Nullable
    public IBinder zzvi() {
        synchronized (this.zzaFg) {
            if (this.zzaFh == null) {
                return null;
            }
            IBinder asBinder = this.zzaFh.asBinder();
            return asBinder;
        }
    }

    public com.google.android.gms.common.zzc[] zzxA() {
        return new com.google.android.gms.common.zzc[0];
    }

    public final Account zzxB() {
        return getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
    }

    /* access modifiers changed from: protected */
    public final void zzxC() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zzxD() throws DeadObjectException {
        T t;
        synchronized (this.zzrJ) {
            if (this.zzaFm == 4) {
                throw new DeadObjectException();
            }
            zzxC();
            zzac.zza(this.zzaFj != null, (Object) "Client is connected but service is null");
            t = this.zzaFj;
        }
        return t;
    }

    public boolean zzxE() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Set<Scope> zzxF() {
        return Collections.EMPTY_SET;
    }

    /* access modifiers changed from: protected */
    public String zzxv() {
        return "com.google.android.gms";
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzxw() {
        return this.zzaFq == null ? this.mContext.getClass().getName() : this.zzaFq;
    }

    public void zzxz() {
        int isGooglePlayServicesAvailable = this.zzaAQ.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (IInterface) null);
            zza((C0015zzf) new zzi(), isGooglePlayServicesAvailable, (PendingIntent) null);
            return;
        }
        zza((C0015zzf) new zzi());
    }
}
