package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.common.internal.zzn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class zzo extends zzn implements Handler.Callback {
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    public final HashMap<zzn.zza, zza> zzaGf = new HashMap<>();
    /* access modifiers changed from: private */
    public final com.google.android.gms.common.stats.zza zzaGg;
    private final long zzaGh;
    /* access modifiers changed from: private */
    public final long zzaGi;
    /* access modifiers changed from: private */
    public final Context zzwi;

    private final class zza implements ServiceConnection {
        private int mState = 2;
        private IBinder zzaFz;
        private ComponentName zzaGe;
        private final Set<ServiceConnection> zzaGj = new HashSet();
        private boolean zzaGk;
        private final zzn.zza zzaGl;

        public zza(zzn.zza zza) {
            this.zzaGl = zza;
        }

        public IBinder getBinder() {
            return this.zzaFz;
        }

        public ComponentName getComponentName() {
            return this.zzaGe;
        }

        public int getState() {
            return this.mState;
        }

        public boolean isBound() {
            return this.zzaGk;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (zzo.this.zzaGf) {
                zzo.this.mHandler.removeMessages(1, this.zzaGl);
                this.zzaFz = iBinder;
                this.zzaGe = componentName;
                for (ServiceConnection onServiceConnected : this.zzaGj) {
                    onServiceConnected.onServiceConnected(componentName, iBinder);
                }
                this.mState = 1;
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (zzo.this.zzaGf) {
                zzo.this.mHandler.removeMessages(1, this.zzaGl);
                this.zzaFz = null;
                this.zzaGe = componentName;
                for (ServiceConnection onServiceDisconnected : this.zzaGj) {
                    onServiceDisconnected.onServiceDisconnected(componentName);
                }
                this.mState = 2;
            }
        }

        public void zza(ServiceConnection serviceConnection, String str) {
            zzo.this.zzaGg.zza(zzo.this.zzwi, serviceConnection, str, this.zzaGl.zzxZ());
            this.zzaGj.add(serviceConnection);
        }

        public boolean zza(ServiceConnection serviceConnection) {
            return this.zzaGj.contains(serviceConnection);
        }

        public void zzb(ServiceConnection serviceConnection, String str) {
            zzo.this.zzaGg.zzb(zzo.this.zzwi, serviceConnection);
            this.zzaGj.remove(serviceConnection);
        }

        public void zzdn(String str) {
            this.mState = 3;
            this.zzaGk = zzo.this.zzaGg.zza(zzo.this.zzwi, str, this.zzaGl.zzxZ(), this, TsExtractor.TS_STREAM_TYPE_AC3);
            if (this.zzaGk) {
                zzo.this.mHandler.sendMessageDelayed(zzo.this.mHandler.obtainMessage(1, this.zzaGl), zzo.this.zzaGi);
                return;
            }
            this.mState = 2;
            try {
                zzo.this.zzaGg.zza(zzo.this.zzwi, this);
            } catch (IllegalArgumentException unused) {
            }
        }

        public void zzdo(String str) {
            zzo.this.mHandler.removeMessages(1, this.zzaGl);
            zzo.this.zzaGg.zza(zzo.this.zzwi, this);
            this.zzaGk = false;
            this.mState = 2;
        }

        public boolean zzya() {
            return this.zzaGj.isEmpty();
        }
    }

    zzo(Context context) {
        this.zzwi = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.zzaGg = com.google.android.gms.common.stats.zza.zzyJ();
        this.zzaGh = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
        this.zzaGi = 300000;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                synchronized (this.zzaGf) {
                    zzn.zza zza2 = (zzn.zza) message.obj;
                    zza zza3 = this.zzaGf.get(zza2);
                    if (zza3 != null && zza3.zzya()) {
                        if (zza3.isBound()) {
                            zza3.zzdo("GmsClientSupervisor");
                        }
                        this.zzaGf.remove(zza2);
                    }
                }
                return true;
            case 1:
                synchronized (this.zzaGf) {
                    zzn.zza zza4 = (zzn.zza) message.obj;
                    zza zza5 = this.zzaGf.get(zza4);
                    if (zza5 != null && zza5.getState() == 3) {
                        String valueOf = String.valueOf(zza4);
                        StringBuilder sb = new StringBuilder(47 + String.valueOf(valueOf).length());
                        sb.append("Timeout waiting for ServiceConnection callback ");
                        sb.append(valueOf);
                        Log.wtf("GmsClientSupervisor", sb.toString(), new Exception());
                        ComponentName componentName = zza5.getComponentName();
                        if (componentName == null) {
                            componentName = zza4.getComponentName();
                        }
                        if (componentName == null) {
                            componentName = new ComponentName(zza4.getPackage(), "unknown");
                        }
                        zza5.onServiceDisconnected(componentName);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzn.zza zza2, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzac.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzaGf) {
            zza zza3 = this.zzaGf.get(zza2);
            if (zza3 != null) {
                this.mHandler.removeMessages(0, zza2);
                if (!zza3.zza(serviceConnection)) {
                    zza3.zza(serviceConnection, str);
                    switch (zza3.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zza3.getComponentName(), zza3.getBinder());
                            break;
                        case 2:
                            zza3.zzdn(str);
                            break;
                    }
                } else {
                    String valueOf = String.valueOf(zza2);
                    StringBuilder sb = new StringBuilder(81 + String.valueOf(valueOf).length());
                    sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
                }
            } else {
                zza3 = new zza(zza2);
                zza3.zza(serviceConnection, str);
                zza3.zzdn(str);
                this.zzaGf.put(zza2, zza3);
            }
            isBound = zza3.isBound();
        }
        return isBound;
    }

    /* access modifiers changed from: protected */
    public void zzb(zzn.zza zza2, ServiceConnection serviceConnection, String str) {
        zzac.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzaGf) {
            zza zza3 = this.zzaGf.get(zza2);
            if (zza3 == null) {
                String valueOf = String.valueOf(zza2);
                StringBuilder sb = new StringBuilder(50 + String.valueOf(valueOf).length());
                sb.append("Nonexistent connection status for service config: ");
                sb.append(valueOf);
                throw new IllegalStateException(sb.toString());
            } else if (!zza3.zza(serviceConnection)) {
                String valueOf2 = String.valueOf(zza2);
                StringBuilder sb2 = new StringBuilder(76 + String.valueOf(valueOf2).length());
                sb2.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                sb2.append(valueOf2);
                throw new IllegalStateException(sb2.toString());
            } else {
                zza3.zzb(serviceConnection, str);
                if (zza3.zzya()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zza2), this.zzaGh);
                }
            }
        }
    }
}
