package com.google.firebase.iid;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.itextpdf.text.html.HtmlTags;

public class FirebaseInstanceIdService extends zzb {
    private static BroadcastReceiver zzcln = null;
    @VisibleForTesting
    static final Object zzclo = new Object();
    @VisibleForTesting
    static boolean zzclp = false;
    /* access modifiers changed from: private */
    public boolean zzclq = false;

    private String zzG(Intent intent) {
        String stringExtra = intent.getStringExtra("subtype");
        return stringExtra == null ? "" : stringExtra;
    }

    private int zza(Intent intent, boolean z) {
        int intExtra = intent == null ? 10 : intent.getIntExtra("next_retry_delay_in_seconds", 0);
        if (intExtra < 10 && !z) {
            return 30;
        }
        if (intExtra < 10) {
            return 10;
        }
        if (intExtra > 28800) {
            return 28800;
        }
        return intExtra;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r0.zzjB(com.google.firebase.iid.zzd.zzbhN) != false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        if (r3.zzabP().zzabS() == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        zzcs(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        r0 = r3.zzabN();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        if (r0 == null) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void zza(android.content.Context r2, com.google.firebase.iid.FirebaseInstanceId r3) {
        /*
            java.lang.Object r0 = zzclo
            monitor-enter(r0)
            boolean r1 = zzclp     // Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            return
        L_0x0009:
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            com.google.firebase.iid.zzh$zza r0 = r3.zzabN()
            if (r0 == 0) goto L_0x0022
            java.lang.String r1 = com.google.firebase.iid.zzd.zzbhN
            boolean r0 = r0.zzjB(r1)
            if (r0 != 0) goto L_0x0022
            com.google.firebase.iid.zze r3 = r3.zzabP()
            java.lang.String r3 = r3.zzabS()
            if (r3 == 0) goto L_0x0025
        L_0x0022:
            zzcs(r2)
        L_0x0025:
            return
        L_0x0026:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceIdService.zza(android.content.Context, com.google.firebase.iid.FirebaseInstanceId):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0070, code lost:
        android.util.Log.d(r1, r2);
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0060 A[Catch:{ IOException -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0061 A[Catch:{ IOException -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0074 A[Catch:{ IOException -> 0x0084 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zza(android.content.Intent r8, boolean r9, boolean r10) {
        /*
            r7 = this;
            java.lang.Object r9 = zzclo
            monitor-enter(r9)
            r0 = 0
            zzclp = r0     // Catch:{ all -> 0x00d7 }
            monitor-exit(r9)     // Catch:{ all -> 0x00d7 }
            java.lang.String r9 = com.google.firebase.iid.zzf.zzbA(r7)
            if (r9 != 0) goto L_0x000e
            return
        L_0x000e:
            com.google.firebase.iid.FirebaseInstanceId r9 = com.google.firebase.iid.FirebaseInstanceId.getInstance()
            com.google.firebase.iid.zzh$zza r1 = r9.zzabN()
            if (r1 == 0) goto L_0x0099
            java.lang.String r2 = com.google.firebase.iid.zzd.zzbhN
            boolean r2 = r1.zzjB(r2)
            if (r2 == 0) goto L_0x0022
            goto L_0x0099
        L_0x0022:
            com.google.firebase.iid.zze r9 = r9.zzabP()
        L_0x0026:
            java.lang.String r10 = r9.zzabS()
            if (r10 == 0) goto L_0x0091
            java.lang.String r1 = "!"
            java.lang.String[] r1 = r10.split(r1)
            int r2 = r1.length
            r3 = 2
            if (r2 != r3) goto L_0x008d
            r2 = r1[r0]
            r3 = 1
            r1 = r1[r3]
            r4 = -1
            int r5 = r2.hashCode()     // Catch:{ IOException -> 0x0084 }
            r6 = 83
            if (r5 == r6) goto L_0x0052
            r6 = 85
            if (r5 == r6) goto L_0x0049
            goto L_0x005c
        L_0x0049:
            java.lang.String r5 = "U"
            boolean r2 = r2.equals(r5)     // Catch:{ IOException -> 0x0084 }
            if (r2 == 0) goto L_0x005c
            goto L_0x005d
        L_0x0052:
            java.lang.String r3 = "S"
            boolean r2 = r2.equals(r3)     // Catch:{ IOException -> 0x0084 }
            if (r2 == 0) goto L_0x005c
            r3 = r0
            goto L_0x005d
        L_0x005c:
            r3 = r4
        L_0x005d:
            switch(r3) {
                case 0: goto L_0x0074;
                case 1: goto L_0x0061;
                default: goto L_0x0060;
            }     // Catch:{ IOException -> 0x0084 }
        L_0x0060:
            goto L_0x008d
        L_0x0061:
            com.google.firebase.iid.FirebaseInstanceId r2 = com.google.firebase.iid.FirebaseInstanceId.getInstance()     // Catch:{ IOException -> 0x0084 }
            r2.zzjv(r1)     // Catch:{ IOException -> 0x0084 }
            boolean r1 = r7.zzclq     // Catch:{ IOException -> 0x0084 }
            if (r1 == 0) goto L_0x008d
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "unsubscribe operation succeeded"
        L_0x0070:
            android.util.Log.d(r1, r2)     // Catch:{ IOException -> 0x0084 }
            goto L_0x008d
        L_0x0074:
            com.google.firebase.iid.FirebaseInstanceId r2 = com.google.firebase.iid.FirebaseInstanceId.getInstance()     // Catch:{ IOException -> 0x0084 }
            r2.zzju(r1)     // Catch:{ IOException -> 0x0084 }
            boolean r1 = r7.zzclq     // Catch:{ IOException -> 0x0084 }
            if (r1 == 0) goto L_0x008d
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "subscribe operation succeeded"
            goto L_0x0070
        L_0x0084:
            r9 = move-exception
            java.lang.String r9 = r9.getMessage()
            r7.zzd(r8, r9)
            return
        L_0x008d:
            r9.zzjx(r10)
            goto L_0x0026
        L_0x0091:
            java.lang.String r8 = "FirebaseInstanceId"
            java.lang.String r9 = "topic sync succeeded"
            android.util.Log.d(r8, r9)
            return
        L_0x0099:
            java.lang.String r0 = r9.zzabO()     // Catch:{ IOException -> 0x00ce, SecurityException -> 0x00c5 }
            if (r0 == 0) goto L_0x00bf
            boolean r2 = r7.zzclq     // Catch:{ IOException -> 0x00ce, SecurityException -> 0x00c5 }
            if (r2 == 0) goto L_0x00aa
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r3 = "get master token succeeded"
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x00ce, SecurityException -> 0x00c5 }
        L_0x00aa:
            zza((android.content.Context) r7, (com.google.firebase.iid.FirebaseInstanceId) r9)     // Catch:{ IOException -> 0x00ce, SecurityException -> 0x00c5 }
            if (r10 != 0) goto L_0x00bb
            if (r1 == 0) goto L_0x00bb
            if (r1 == 0) goto L_0x00be
            java.lang.String r9 = r1.zzbxT     // Catch:{ IOException -> 0x00ce, SecurityException -> 0x00c5 }
            boolean r9 = r0.equals(r9)     // Catch:{ IOException -> 0x00ce, SecurityException -> 0x00c5 }
            if (r9 != 0) goto L_0x00be
        L_0x00bb:
            r7.onTokenRefresh()     // Catch:{ IOException -> 0x00ce, SecurityException -> 0x00c5 }
        L_0x00be:
            return
        L_0x00bf:
            java.lang.String r9 = "returned token is null"
            r7.zzd(r8, r9)     // Catch:{ IOException -> 0x00ce, SecurityException -> 0x00c5 }
            return
        L_0x00c5:
            r8 = move-exception
            java.lang.String r9 = "FirebaseInstanceId"
            java.lang.String r10 = "Unable to get master token"
            android.util.Log.e(r9, r10, r8)
            return
        L_0x00ce:
            r9 = move-exception
            java.lang.String r9 = r9.getMessage()
            r7.zzd(r8, r9)
            return
        L_0x00d7:
            r8 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x00d7 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceIdService.zza(android.content.Intent, boolean, boolean):void");
    }

    private void zza(zzf zzf, Bundle bundle) {
        String zzbA = zzf.zzbA(this);
        if (zzbA == null) {
            Log.w("FirebaseInstanceId", "Unable to respond to ping due to missing target package");
            return;
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        intent.setPackage(zzbA);
        intent.putExtras(bundle);
        zzf.zzp(intent);
        intent.putExtra("google.to", "google.com/iid");
        intent.putExtra("google.message_id", zzf.zzHn());
        sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    static void zzcs(Context context) {
        if (zzf.zzbA(context) != null) {
            synchronized (zzclo) {
                if (!zzclp) {
                    zzg.zzabU().zzf(context, zzqF(0));
                    zzclp = true;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean zzct(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void zzd(Intent intent, String str) {
        boolean zzct = zzct(this);
        final int zza = zza(intent, zzct);
        StringBuilder sb = new StringBuilder(47 + String.valueOf(str).length());
        sb.append("background sync failed: ");
        sb.append(str);
        sb.append(", retry in ");
        sb.append(zza);
        sb.append(HtmlTags.S);
        Log.d("FirebaseInstanceId", sb.toString());
        synchronized (zzclo) {
            zzqG(zza);
            zzclp = true;
        }
        if (!zzct) {
            if (this.zzclq) {
                Log.d("FirebaseInstanceId", "device not connected. Connectivity change received registered");
            }
            if (zzcln == null) {
                zzcln = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        if (FirebaseInstanceIdService.zzct(context)) {
                            if (FirebaseInstanceIdService.this.zzclq) {
                                Log.d("FirebaseInstanceId", "connectivity changed. starting background sync.");
                            }
                            FirebaseInstanceIdService.this.getApplicationContext().unregisterReceiver(this);
                            zzg.zzabU().zzf(context, FirebaseInstanceIdService.zzqF(zza));
                        }
                    }
                };
            }
            getApplicationContext().registerReceiver(zzcln, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    private zzd zzjw(String str) {
        if (str == null) {
            return zzd.zzb(this, (Bundle) null);
        }
        Bundle bundle = new Bundle();
        bundle.putString("subtype", str);
        return zzd.zzb(this, bundle);
    }

    /* access modifiers changed from: private */
    public static Intent zzqF(int i) {
        Intent intent = new Intent("ACTION_TOKEN_REFRESH_RETRY");
        intent.putExtra("next_retry_delay_in_seconds", i);
        return intent;
    }

    private void zzqG(int i) {
        ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).set(3, SystemClock.elapsedRealtime() + ((long) (i * 1000)), zzg.zza(this, 0, zzqF(i * 2), 134217728));
    }

    public void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            action = "";
        }
        char c = 65535;
        if (action.hashCode() == -1737547627 && action.equals("ACTION_TOKEN_REFRESH_RETRY")) {
            c = 0;
        }
        if (c != 0) {
            zzF(intent);
        } else {
            zza(intent, false, false);
        }
    }

    @WorkerThread
    public void onTokenRefresh() {
    }

    /* access modifiers changed from: protected */
    public Intent zzD(Intent intent) {
        return zzg.zzabU().zzabV();
    }

    public boolean zzE(Intent intent) {
        this.zzclq = Log.isLoggable("FirebaseInstanceId", 3);
        if (intent.getStringExtra("error") == null && intent.getStringExtra("registration_id") == null) {
            return false;
        }
        String zzG = zzG(intent);
        if (this.zzclq) {
            String valueOf = String.valueOf(zzG);
            Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Register result in service ".concat(valueOf) : new String("Register result in service "));
        }
        zzjw(zzG).zzabR().zzs(intent);
        return true;
    }

    public void zzF(Intent intent) {
        String zzG = zzG(intent);
        zzd zzjw = zzjw(zzG);
        String stringExtra = intent.getStringExtra("CMD");
        if (this.zzclq) {
            String valueOf = String.valueOf(intent.getExtras());
            StringBuilder sb = new StringBuilder(18 + String.valueOf(zzG).length() + String.valueOf(stringExtra).length() + String.valueOf(valueOf).length());
            sb.append("Service command ");
            sb.append(zzG);
            sb.append(" ");
            sb.append(stringExtra);
            sb.append(" ");
            sb.append(valueOf);
            Log.d("FirebaseInstanceId", sb.toString());
        }
        if (intent.getStringExtra("unregistered") != null) {
            zzh zzabQ = zzjw.zzabQ();
            if (zzG == null) {
                zzG = "";
            }
            zzabQ.zzeK(zzG);
            zzjw.zzabR().zzs(intent);
        } else if ("gcm.googleapis.com/refresh".equals(intent.getStringExtra("from"))) {
            zzjw.zzabQ().zzeK(zzG);
            zza(intent, false, true);
        } else {
            if ("RST".equals(stringExtra)) {
                zzjw.zzHi();
            } else if ("RST_FULL".equals(stringExtra)) {
                if (!zzjw.zzabQ().isEmpty()) {
                    zzjw.zzHi();
                    zzjw.zzabQ().zzHo();
                } else {
                    return;
                }
            } else if ("SYNC".equals(stringExtra)) {
                zzjw.zzabQ().zzeK(zzG);
                zza(intent, false, true);
                return;
            } else if ("PING".equals(stringExtra)) {
                zza(zzjw.zzabR(), intent.getExtras());
                return;
            } else {
                return;
            }
            zza(intent, true, true);
        }
    }
}
