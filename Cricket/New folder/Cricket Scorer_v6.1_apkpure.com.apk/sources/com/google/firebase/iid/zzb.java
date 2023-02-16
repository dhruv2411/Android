package com.google.firebase.iid;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.util.Pair;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
    @VisibleForTesting
    final ExecutorService zzbtI = Executors.newSingleThreadExecutor();
    private Binder zzckU;
    private int zzckV;
    private int zzckW = 0;
    private final Object zzrJ = new Object();

    public static class zza extends Binder {
        /* access modifiers changed from: private */
        public final zzb zzckZ;

        zza(zzb zzb) {
            this.zzckZ = zzb;
        }

        /* access modifiers changed from: private */
        public static void zza(@Nullable BroadcastReceiver.PendingResult pendingResult) {
            if (pendingResult != null) {
                pendingResult.finish();
            }
        }

        public void zza(final Intent intent, @Nullable final BroadcastReceiver.PendingResult pendingResult) {
            if (Binder.getCallingUid() != Process.myUid()) {
                throw new SecurityException("Binding only allowed within app");
            }
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "service received new intent via bind strategy");
            }
            if (this.zzckZ.zzE(intent)) {
                zza(pendingResult);
                return;
            }
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "intent being queued for bg execution");
            }
            this.zzckZ.zzbtI.execute(new Runnable() {
                public void run() {
                    if (Log.isLoggable("EnhancedIntentService", 3)) {
                        Log.d("EnhancedIntentService", "bg processing of the intent starting now");
                    }
                    zza.this.zzckZ.handleIntent(intent);
                    zza.zza(pendingResult);
                }
            });
        }
    }

    /* renamed from: com.google.firebase.iid.zzb$zzb  reason: collision with other inner class name */
    public static class C0151zzb implements ServiceConnection {
        private final Intent zzclc;
        private final Queue<Pair<Intent, BroadcastReceiver.PendingResult>> zzcld = new LinkedList();
        private zza zzcle;
        private boolean zzclf = false;
        private final Context zzqn;

        public C0151zzb(Context context, String str) {
            this.zzqn = context.getApplicationContext();
            this.zzclc = new Intent(str).setPackage(this.zzqn.getPackageName());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00be, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized void zzwH() {
            /*
                r5 = this;
                monitor-enter(r5)
                java.lang.String r0 = "EnhancedIntentService"
                r1 = 3
                boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00c1 }
                if (r0 == 0) goto L_0x0011
                java.lang.String r0 = "EnhancedIntentService"
                java.lang.String r2 = "flush queue called"
                android.util.Log.d(r0, r2)     // Catch:{ all -> 0x00c1 }
            L_0x0011:
                java.util.Queue<android.util.Pair<android.content.Intent, android.content.BroadcastReceiver$PendingResult>> r0 = r5.zzcld     // Catch:{ all -> 0x00c1 }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00c1 }
                if (r0 != 0) goto L_0x00bf
                java.lang.String r0 = "EnhancedIntentService"
                boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00c1 }
                if (r0 == 0) goto L_0x0028
                java.lang.String r0 = "EnhancedIntentService"
                java.lang.String r2 = "found intent to be delivered"
                android.util.Log.d(r0, r2)     // Catch:{ all -> 0x00c1 }
            L_0x0028:
                com.google.firebase.iid.zzb$zza r0 = r5.zzcle     // Catch:{ all -> 0x00c1 }
                if (r0 == 0) goto L_0x0059
                com.google.firebase.iid.zzb$zza r0 = r5.zzcle     // Catch:{ all -> 0x00c1 }
                boolean r0 = r0.isBinderAlive()     // Catch:{ all -> 0x00c1 }
                if (r0 == 0) goto L_0x0059
                java.lang.String r0 = "EnhancedIntentService"
                boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00c1 }
                if (r0 == 0) goto L_0x0043
                java.lang.String r0 = "EnhancedIntentService"
                java.lang.String r2 = "binder is alive, sending the intent."
                android.util.Log.d(r0, r2)     // Catch:{ all -> 0x00c1 }
            L_0x0043:
                java.util.Queue<android.util.Pair<android.content.Intent, android.content.BroadcastReceiver$PendingResult>> r0 = r5.zzcld     // Catch:{ all -> 0x00c1 }
                java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x00c1 }
                android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x00c1 }
                com.google.firebase.iid.zzb$zza r2 = r5.zzcle     // Catch:{ all -> 0x00c1 }
                java.lang.Object r3 = r0.first     // Catch:{ all -> 0x00c1 }
                android.content.Intent r3 = (android.content.Intent) r3     // Catch:{ all -> 0x00c1 }
                java.lang.Object r0 = r0.second     // Catch:{ all -> 0x00c1 }
                android.content.BroadcastReceiver$PendingResult r0 = (android.content.BroadcastReceiver.PendingResult) r0     // Catch:{ all -> 0x00c1 }
                r2.zza(r3, r0)     // Catch:{ all -> 0x00c1 }
                goto L_0x0011
            L_0x0059:
                java.lang.String r0 = "EnhancedIntentService"
                boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00c1 }
                r1 = 1
                if (r0 == 0) goto L_0x007d
                java.lang.String r0 = "EnhancedIntentService"
                boolean r2 = r5.zzclf     // Catch:{ all -> 0x00c1 }
                r2 = r2 ^ r1
                r3 = 39
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c1 }
                r4.<init>(r3)     // Catch:{ all -> 0x00c1 }
                java.lang.String r3 = "binder is dead. start connection? "
                r4.append(r3)     // Catch:{ all -> 0x00c1 }
                r4.append(r2)     // Catch:{ all -> 0x00c1 }
                java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x00c1 }
                android.util.Log.d(r0, r2)     // Catch:{ all -> 0x00c1 }
            L_0x007d:
                boolean r0 = r5.zzclf     // Catch:{ all -> 0x00c1 }
                if (r0 != 0) goto L_0x00bd
                r5.zzclf = r1     // Catch:{ all -> 0x00c1 }
                com.google.android.gms.common.stats.zza r0 = com.google.android.gms.common.stats.zza.zzyJ()     // Catch:{ SecurityException -> 0x009d }
                android.content.Context r1 = r5.zzqn     // Catch:{ SecurityException -> 0x009d }
                android.content.Intent r2 = r5.zzclc     // Catch:{ SecurityException -> 0x009d }
                r3 = 65
                boolean r0 = r0.zza((android.content.Context) r1, (android.content.Intent) r2, (android.content.ServiceConnection) r5, (int) r3)     // Catch:{ SecurityException -> 0x009d }
                if (r0 == 0) goto L_0x0095
                monitor-exit(r5)
                return
            L_0x0095:
                java.lang.String r0 = "EnhancedIntentService"
                java.lang.String r1 = "binding to the service failed"
                android.util.Log.e(r0, r1)     // Catch:{ SecurityException -> 0x009d }
                goto L_0x00a5
            L_0x009d:
                r0 = move-exception
                java.lang.String r1 = "EnhancedIntentService"
                java.lang.String r2 = "Exception while binding the service"
                android.util.Log.e(r1, r2, r0)     // Catch:{ all -> 0x00c1 }
            L_0x00a5:
                java.util.Queue<android.util.Pair<android.content.Intent, android.content.BroadcastReceiver$PendingResult>> r0 = r5.zzcld     // Catch:{ all -> 0x00c1 }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00c1 }
                if (r0 != 0) goto L_0x00bd
                java.util.Queue<android.util.Pair<android.content.Intent, android.content.BroadcastReceiver$PendingResult>> r0 = r5.zzcld     // Catch:{ all -> 0x00c1 }
                java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x00c1 }
                android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x00c1 }
                java.lang.Object r0 = r0.second     // Catch:{ all -> 0x00c1 }
                android.content.BroadcastReceiver$PendingResult r0 = (android.content.BroadcastReceiver.PendingResult) r0     // Catch:{ all -> 0x00c1 }
                r0.finish()     // Catch:{ all -> 0x00c1 }
                goto L_0x00a5
            L_0x00bd:
                monitor-exit(r5)
                return
            L_0x00bf:
                monitor-exit(r5)
                return
            L_0x00c1:
                r0 = move-exception
                monitor-exit(r5)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzb.C0151zzb.zzwH():void");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (this) {
                this.zzclf = false;
                this.zzcle = (zza) iBinder;
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    String valueOf = String.valueOf(componentName);
                    StringBuilder sb = new StringBuilder(20 + String.valueOf(valueOf).length());
                    sb.append("onServiceConnected: ");
                    sb.append(valueOf);
                    Log.d("EnhancedIntentService", sb.toString());
                }
                zzwH();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                String valueOf = String.valueOf(componentName);
                StringBuilder sb = new StringBuilder(23 + String.valueOf(valueOf).length());
                sb.append("onServiceDisconnected: ");
                sb.append(valueOf);
                Log.d("EnhancedIntentService", sb.toString());
            }
            zzwH();
        }

        public synchronized void zzb(Intent intent, BroadcastReceiver.PendingResult pendingResult) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
            }
            this.zzcld.add(new Pair(intent, pendingResult));
            zzwH();
        }
    }

    /* access modifiers changed from: private */
    public void zzC(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.zzrJ) {
            this.zzckW--;
            if (this.zzckW == 0) {
                zzqE(this.zzckV);
            }
        }
    }

    public abstract void handleIntent(Intent intent);

    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.zzckU == null) {
            this.zzckU = new zza(this);
        }
        return this.zzckU;
    }

    public final int onStartCommand(final Intent intent, int i, int i2) {
        synchronized (this.zzrJ) {
            this.zzckV = i2;
            this.zzckW++;
        }
        final Intent zzD = zzD(intent);
        if (zzD == null) {
            zzC(intent);
            return 2;
        } else if (zzE(zzD)) {
            zzC(intent);
            return 2;
        } else {
            this.zzbtI.execute(new Runnable() {
                public void run() {
                    zzb.this.handleIntent(zzD);
                    zzb.this.zzC(intent);
                }
            });
            return 3;
        }
    }

    /* access modifiers changed from: protected */
    public Intent zzD(Intent intent) {
        return intent;
    }

    public boolean zzE(Intent intent) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean zzqE(int i) {
        return stopSelfResult(i);
    }
}
