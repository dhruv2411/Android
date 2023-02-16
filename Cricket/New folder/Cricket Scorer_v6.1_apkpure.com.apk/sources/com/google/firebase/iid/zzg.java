package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;

public class zzg {
    private static zzg zzclA;
    private final SimpleArrayMap<String, String> zzclB = new SimpleArrayMap<>();
    private Boolean zzclC = null;
    @VisibleForTesting
    final Queue<Intent> zzclD = new LinkedList();
    @VisibleForTesting
    final Queue<Intent> zzclE = new LinkedList();

    private zzg() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return zza(context, i, "com.google.firebase.INSTANCE_ID_EVENT", intent, i2);
    }

    private static PendingIntent zza(Context context, int i, String str, Intent intent, int i2) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdInternalReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return PendingIntent.getBroadcast(context, i, intent2, i2);
    }

    public static synchronized zzg zzabU() {
        zzg zzg;
        synchronized (zzg.class) {
            if (zzclA == null) {
                zzclA = new zzg();
            }
            zzg = zzclA;
        }
        return zzg;
    }

    public static PendingIntent zzb(Context context, int i, Intent intent, int i2) {
        return zza(context, i, "com.google.firebase.MESSAGING_EVENT", intent, i2);
    }

    private boolean zzcv(Context context) {
        if (this.zzclC == null) {
            this.zzclC = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        return this.zzclC.booleanValue();
    }

    private void zze(Context context, Intent intent) {
        String str;
        synchronized (this.zzclB) {
            str = this.zzclB.get(intent.getAction());
        }
        if (str == null) {
            ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (resolveService == null || resolveService.serviceInfo == null) {
                Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
                return;
            }
            ServiceInfo serviceInfo = resolveService.serviceInfo;
            if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
                String valueOf = String.valueOf(serviceInfo.packageName);
                String valueOf2 = String.valueOf(serviceInfo.name);
                StringBuilder sb = new StringBuilder(94 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
                sb.append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ");
                sb.append(valueOf);
                sb.append("/");
                sb.append(valueOf2);
                Log.e("FirebaseInstanceId", sb.toString());
                return;
            }
            String str2 = serviceInfo.name;
            if (str2.startsWith(".")) {
                String valueOf3 = String.valueOf(context.getPackageName());
                String valueOf4 = String.valueOf(str2);
                str2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
            }
            str = str2;
            synchronized (this.zzclB) {
                this.zzclB.put(intent.getAction(), str);
            }
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf5 = String.valueOf(str);
            Log.d("FirebaseInstanceId", valueOf5.length() != 0 ? "Restricting intent to a specific service: ".concat(valueOf5) : new String("Restricting intent to a specific service: "));
        }
        intent.setClassName(context.getPackageName(), str);
    }

    private int zzg(Context context, Intent intent) {
        ComponentName componentName;
        zze(context, intent);
        try {
            if (zzcv(context)) {
                componentName = WakefulBroadcastReceiver.startWakefulService(context, intent);
            } else {
                componentName = context.startService(intent);
                Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
            }
            if (componentName != null) {
                return -1;
            }
            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        } catch (SecurityException e) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e);
            return 401;
        }
    }

    public Intent zzabV() {
        return this.zzclD.poll();
    }

    public Intent zzabW() {
        return this.zzclE.poll();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int zzb(android.content.Context r3, java.lang.String r4, android.content.Intent r5) {
        /*
            r2 = this;
            int r0 = r4.hashCode()
            r1 = -842411455(0xffffffffcdc9d241, float:-4.23249952E8)
            if (r0 == r1) goto L_0x0019
            r1 = 41532704(0x279bd20, float:1.8347907E-37)
            if (r0 == r1) goto L_0x000f
            goto L_0x0023
        L_0x000f:
            java.lang.String r0 = "com.google.firebase.MESSAGING_EVENT"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0023
            r0 = 1
            goto L_0x0024
        L_0x0019:
            java.lang.String r0 = "com.google.firebase.INSTANCE_ID_EVENT"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0023
            r0 = 0
            goto L_0x0024
        L_0x0023:
            r0 = -1
        L_0x0024:
            switch(r0) {
                case 0: goto L_0x003d;
                case 1: goto L_0x003a;
                default: goto L_0x0027;
            }
        L_0x0027:
            java.lang.String r3 = "FirebaseInstanceId"
            java.lang.String r5 = "Unknown service action: "
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r0 = r4.length()
            if (r0 == 0) goto L_0x0053
            java.lang.String r4 = r5.concat(r4)
            goto L_0x0058
        L_0x003a:
            java.util.Queue<android.content.Intent> r0 = r2.zzclE
            goto L_0x003f
        L_0x003d:
            java.util.Queue<android.content.Intent> r0 = r2.zzclD
        L_0x003f:
            r0.offer(r5)
            android.content.Intent r5 = new android.content.Intent
            r5.<init>(r4)
            java.lang.String r4 = r3.getPackageName()
            r5.setPackage(r4)
            int r3 = r2.zzg(r3, r5)
            return r3
        L_0x0053:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r5)
        L_0x0058:
            android.util.Log.w(r3, r4)
            r3 = 500(0x1f4, float:7.0E-43)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzg.zzb(android.content.Context, java.lang.String, android.content.Intent):int");
    }

    public void zzf(Context context, Intent intent) {
        zzb(context, "com.google.firebase.INSTANCE_ID_EVENT", intent);
    }
}
