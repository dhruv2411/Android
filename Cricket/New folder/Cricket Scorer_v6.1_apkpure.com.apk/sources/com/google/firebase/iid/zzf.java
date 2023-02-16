package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.iid.MessengerCompat;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.Random;

public class zzf {
    static String zzbhQ = null;
    static boolean zzbhR = false;
    static int zzbhS;
    static int zzbhT;
    static int zzbhU;
    static BroadcastReceiver zzbhV;
    PendingIntent zzbgG;
    Messenger zzbgK;
    Messenger zzbhX;
    MessengerCompat zzbhY;
    long zzbhZ;
    long zzbia;
    int zzbib;
    int zzbic;
    long zzbid;
    private final SimpleArrayMap<String, zzb> zzclw = new SimpleArrayMap<>();
    Context zzqn;

    private static class zza implements zzb {
        private Intent intent;
        private final ConditionVariable zzcly;
        private String zzclz;

        private zza() {
            this.zzcly = new ConditionVariable();
        }

        public void onError(String str) {
            this.zzclz = str;
            this.zzcly.open();
        }

        public void zzH(Intent intent2) {
            this.intent = intent2;
            this.zzcly.open();
        }

        public Intent zzabT() throws IOException {
            if (!this.zzcly.block(DashMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_FIXED_MS)) {
                Log.w("InstanceID/Rpc", "No response");
                throw new IOException("TIMEOUT");
            } else if (this.zzclz == null) {
                return this.intent;
            } else {
                throw new IOException(this.zzclz);
            }
        }
    }

    private interface zzb {
        void onError(String str);

        void zzH(Intent intent);
    }

    public zzf(Context context) {
        this.zzqn = context;
    }

    public static synchronized String zzHn() {
        String num;
        synchronized (zzf.class) {
            int i = zzbhU;
            zzbhU = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    static String zza(KeyPair keyPair, String... strArr) {
        String str;
        String str2;
        try {
            byte[] bytes = TextUtils.join("\n", strArr).getBytes("UTF-8");
            try {
                PrivateKey privateKey = keyPair.getPrivate();
                Signature instance = Signature.getInstance(privateKey instanceof RSAPrivateKey ? "SHA256withRSA" : "SHA256withECDSA");
                instance.initSign(privateKey);
                instance.update(bytes);
                return FirebaseInstanceId.zzv(instance.sign());
            } catch (GeneralSecurityException e) {
                e = e;
                str = "InstanceID/Rpc";
                str2 = "Unable to sign registration request";
                Log.e(str, str2, e);
                return null;
            }
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            str = "InstanceID/Rpc";
            str2 = "Unable to encode string";
            Log.e(str, str2, e);
            return null;
        }
    }

    private static boolean zza(PackageManager packageManager) {
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0)) {
            if (zza(packageManager, resolveInfo.serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                zzbhR = false;
                return true;
            }
        }
        return false;
    }

    private static boolean zza(PackageManager packageManager, String str, String str2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", str) == 0) {
            return zzb(packageManager, str);
        }
        StringBuilder sb = new StringBuilder(56 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append("Possible malicious package ");
        sb.append(str);
        sb.append(" declares ");
        sb.append(str2);
        sb.append(" without permission");
        Log.w("InstanceID/Rpc", sb.toString());
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzay(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.firebase.iid.zzf$zzb> r0 = r3.zzclw
            monitor-enter(r0)
            if (r4 != 0) goto L_0x0022
            r4 = 0
        L_0x0006:
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.firebase.iid.zzf$zzb> r1 = r3.zzclw     // Catch:{ all -> 0x004e }
            int r1 = r1.size()     // Catch:{ all -> 0x004e }
            if (r4 >= r1) goto L_0x001c
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.firebase.iid.zzf$zzb> r1 = r3.zzclw     // Catch:{ all -> 0x004e }
            java.lang.Object r1 = r1.valueAt(r4)     // Catch:{ all -> 0x004e }
            com.google.firebase.iid.zzf$zzb r1 = (com.google.firebase.iid.zzf.zzb) r1     // Catch:{ all -> 0x004e }
            r1.onError(r5)     // Catch:{ all -> 0x004e }
            int r4 = r4 + 1
            goto L_0x0006
        L_0x001c:
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.firebase.iid.zzf$zzb> r4 = r3.zzclw     // Catch:{ all -> 0x004e }
            r4.clear()     // Catch:{ all -> 0x004e }
            goto L_0x004c
        L_0x0022:
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.firebase.iid.zzf$zzb> r1 = r3.zzclw     // Catch:{ all -> 0x004e }
            java.lang.Object r1 = r1.remove(r4)     // Catch:{ all -> 0x004e }
            com.google.firebase.iid.zzf$zzb r1 = (com.google.firebase.iid.zzf.zzb) r1     // Catch:{ all -> 0x004e }
            if (r1 != 0) goto L_0x0049
            java.lang.String r5 = "InstanceID/Rpc"
            java.lang.String r1 = "Missing callback for "
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x004e }
            int r2 = r4.length()     // Catch:{ all -> 0x004e }
            if (r2 == 0) goto L_0x003f
            java.lang.String r4 = r1.concat(r4)     // Catch:{ all -> 0x004e }
            goto L_0x0044
        L_0x003f:
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x004e }
            r4.<init>(r1)     // Catch:{ all -> 0x004e }
        L_0x0044:
            android.util.Log.w(r5, r4)     // Catch:{ all -> 0x004e }
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return
        L_0x0049:
            r1.onError(r5)     // Catch:{ all -> 0x004e }
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return
        L_0x004e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzf.zzay(java.lang.String, java.lang.String):void");
    }

    private Intent zzb(Bundle bundle, KeyPair keyPair) throws IOException {
        String zzHn = zzHn();
        zza zza2 = new zza();
        synchronized (this.zzclw) {
            this.zzclw.put(zzHn, zza2);
        }
        zza(bundle, keyPair, zzHn);
        try {
            Intent zzabT = zza2.zzabT();
            synchronized (this.zzclw) {
                this.zzclw.remove(zzHn);
            }
            return zzabT;
        } catch (Throwable th) {
            synchronized (this.zzclw) {
                this.zzclw.remove(zzHn);
                throw th;
            }
        }
    }

    private void zzb(String str, Intent intent) {
        synchronized (this.zzclw) {
            zzb remove = this.zzclw.remove(str);
            if (remove == null) {
                String valueOf = String.valueOf(str);
                Log.w("InstanceID/Rpc", valueOf.length() != 0 ? "Missing callback for ".concat(valueOf) : new String("Missing callback for "));
                return;
            }
            remove.zzH(intent);
        }
    }

    private static boolean zzb(PackageManager packageManager) {
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0)) {
            if (zza(packageManager, resolveInfo.activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                zzbhR = true;
                return true;
            }
        }
        return false;
    }

    private static boolean zzb(PackageManager packageManager, String str) {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            zzbhQ = applicationInfo.packageName;
            zzbhT = applicationInfo.uid;
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static String zzbA(Context context) {
        if (zzbhQ != null) {
            return zzbhQ;
        }
        zzbhS = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        if (zzt.zzzq()) {
            if (zzb(packageManager) || zza(packageManager)) {
                return zzbhQ;
            }
        } else if (zza(packageManager) || zzb(packageManager)) {
            return zzbhQ;
        }
        Log.w("InstanceID/Rpc", "Failed to resolve IID implementation package, falling back");
        if (zzb(packageManager, "com.google.android.gms")) {
            zzbhR = zzt.zzzq();
            return zzbhQ;
        } else if (Build.VERSION.SDK_INT >= 21 || !zzb(packageManager, "com.google.android.gsf")) {
            Log.w("InstanceID/Rpc", "Google Play services is missing, unable to get tokens");
            return null;
        } else {
            zzbhR = false;
            return zzbhQ;
        }
    }

    private void zzeF(String str) {
        if ("com.google.android.gsf".equals(zzbhQ)) {
            this.zzbib++;
            if (this.zzbib >= 3) {
                if (this.zzbib == 3) {
                    this.zzbic = 1000 + new Random().nextInt(1000);
                }
                this.zzbic *= 2;
                this.zzbid = SystemClock.elapsedRealtime() + ((long) this.zzbic);
                int i = this.zzbic;
                StringBuilder sb = new StringBuilder(31 + String.valueOf(str).length());
                sb.append("Backoff due to ");
                sb.append(str);
                sb.append(" for ");
                sb.append(i);
                Log.w("InstanceID/Rpc", sb.toString());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzHl() {
        if (this.zzbgK == null) {
            zzbA(this.zzqn);
            this.zzbgK = new Messenger(new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message message) {
                    zzf.this.zze(message);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void zzHm() {
        synchronized (this) {
            if (zzbhV == null) {
                zzbhV = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        if (Log.isLoggable("InstanceID/Rpc", 3)) {
                            String valueOf = String.valueOf(intent.getExtras());
                            StringBuilder sb = new StringBuilder(44 + String.valueOf(valueOf).length());
                            sb.append("Received GSF callback via dynamic receiver: ");
                            sb.append(valueOf);
                            Log.d("InstanceID/Rpc", sb.toString());
                        }
                        zzf.this.zzs(intent);
                    }
                };
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Registered GSF callback receiver");
                }
                IntentFilter intentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
                intentFilter.addCategory(this.zzqn.getPackageName());
                this.zzqn.registerReceiver(zzbhV, intentFilter, "com.google.android.c2dm.permission.SEND", (Handler) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Intent zza(Bundle bundle, KeyPair keyPair) throws IOException {
        Intent zzb2 = zzb(bundle, keyPair);
        if (zzb2 == null || !zzb2.hasExtra("google.messenger")) {
            return zzb2;
        }
        Intent zzb3 = zzb(bundle, keyPair);
        if (zzb3 == null || !zzb3.hasExtra("google.messenger")) {
            return zzb3;
        }
        return null;
    }

    public void zza(Bundle bundle, KeyPair keyPair, String str) throws IOException {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzbid == 0 || elapsedRealtime > this.zzbid) {
            zzHl();
            if (zzbhQ == null) {
                throw new IOException("MISSING_INSTANCEID_SERVICE");
            }
            this.zzbhZ = SystemClock.elapsedRealtime();
            Intent intent = new Intent(zzbhR ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER");
            intent.setPackage(zzbhQ);
            bundle.putString("gmsv", Integer.toString(FirebaseInstanceId.zzR(this.zzqn, zzbA(this.zzqn))));
            bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
            bundle.putString("app_ver", Integer.toString(FirebaseInstanceId.zzcr(this.zzqn)));
            bundle.putString("app_ver_name", FirebaseInstanceId.zzbx(this.zzqn));
            bundle.putString("cliv", "fiid-10298000");
            bundle.putString("appid", FirebaseInstanceId.zza(keyPair));
            String zzv = FirebaseInstanceId.zzv(keyPair.getPublic().getEncoded());
            bundle.putString("pub2", zzv);
            bundle.putString("sig", zza(keyPair, this.zzqn.getPackageName(), zzv));
            intent.putExtras(bundle);
            zzp(intent);
            zzb(intent, str);
            return;
        }
        long j = this.zzbid - elapsedRealtime;
        int i = this.zzbic;
        StringBuilder sb = new StringBuilder(78);
        sb.append("Backoff mode, next request attempt: ");
        sb.append(j);
        sb.append(" interval: ");
        sb.append(i);
        Log.w("InstanceID/Rpc", sb.toString());
        throw new IOException("RETRY_LATER");
    }

    /* access modifiers changed from: protected */
    public void zzb(Intent intent, String str) {
        this.zzbhZ = SystemClock.elapsedRealtime();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 5);
        sb.append("|ID|");
        sb.append(str);
        sb.append("|");
        intent.putExtra("kid", sb.toString());
        StringBuilder sb2 = new StringBuilder(5 + String.valueOf(str).length());
        sb2.append("|ID|");
        sb2.append(str);
        sb2.append("|");
        intent.putExtra("X-kid", sb2.toString());
        boolean equals = "com.google.android.gsf".equals(zzbhQ);
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            String valueOf = String.valueOf(intent.getExtras());
            StringBuilder sb3 = new StringBuilder(8 + String.valueOf(valueOf).length());
            sb3.append("Sending ");
            sb3.append(valueOf);
            Log.d("InstanceID/Rpc", sb3.toString());
        }
        if (equals) {
            zzHm();
            this.zzqn.startService(intent);
            return;
        }
        intent.putExtra("google.messenger", this.zzbgK);
        if (!(this.zzbhX == null && this.zzbhY == null)) {
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                if (this.zzbhX != null) {
                    this.zzbhX.send(obtain);
                    return;
                } else {
                    this.zzbhY.send(obtain);
                    return;
                }
            } catch (RemoteException unused) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                }
            }
        }
        if (zzbhR) {
            this.zzqn.sendBroadcast(intent);
        } else {
            this.zzqn.startService(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void zze(Message message) {
        if (message != null) {
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        this.zzbhY = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.zzbhX = (Messenger) parcelableExtra;
                    }
                }
                zzs((Intent) message.obj);
                return;
            }
            Log.w("InstanceID/Rpc", "Dropping invalid message");
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void zzp(Intent intent) {
        if (this.zzbgG == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzbgG = PendingIntent.getBroadcast(this.zzqn, 0, intent2, 0);
        }
        intent.putExtra(SettingsJsonConstants.APP_KEY, this.zzbgG);
    }

    /* access modifiers changed from: package-private */
    public String zzq(Intent intent) throws IOException {
        if (intent == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String stringExtra = intent.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("unregistered");
        }
        if (stringExtra != null) {
            return stringExtra;
        }
        String stringExtra2 = intent.getStringExtra("error");
        if (stringExtra2 != null) {
            throw new IOException(stringExtra2);
        }
        String valueOf = String.valueOf(intent.getExtras());
        StringBuilder sb = new StringBuilder(29 + String.valueOf(valueOf).length());
        sb.append("Unexpected response from GCM ");
        sb.append(valueOf);
        Log.w("InstanceID/Rpc", sb.toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    /* access modifiers changed from: package-private */
    public void zzr(Intent intent) {
        String stringExtra = intent.getStringExtra("error");
        if (stringExtra == null) {
            String valueOf = String.valueOf(intent.getExtras());
            StringBuilder sb = new StringBuilder(49 + String.valueOf(valueOf).length());
            sb.append("Unexpected response, no error or registration id ");
            sb.append(valueOf);
            Log.w("InstanceID/Rpc", sb.toString());
            return;
        }
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            String valueOf2 = String.valueOf(stringExtra);
            Log.d("InstanceID/Rpc", valueOf2.length() != 0 ? "Received InstanceID error ".concat(valueOf2) : new String("Received InstanceID error "));
        }
        String str = null;
        if (stringExtra.startsWith("|")) {
            String[] split = stringExtra.split("\\|");
            if (!"ID".equals(split[1])) {
                String valueOf3 = String.valueOf(stringExtra);
                Log.w("InstanceID/Rpc", valueOf3.length() != 0 ? "Unexpected structured response ".concat(valueOf3) : new String("Unexpected structured response "));
            }
            if (split.length > 2) {
                String str2 = split[2];
                String str3 = split[3];
                if (str3.startsWith(":")) {
                    str3 = str3.substring(1);
                }
                String str4 = str3;
                str = str2;
                stringExtra = str4;
            } else {
                stringExtra = "UNKNOWN";
            }
            intent.putExtra("error", stringExtra);
        }
        zzay(str, stringExtra);
        long longExtra = intent.getLongExtra("Retry-After", 0);
        if (longExtra > 0) {
            this.zzbia = SystemClock.elapsedRealtime();
            this.zzbic = ((int) longExtra) * 1000;
            this.zzbid = SystemClock.elapsedRealtime() + ((long) this.zzbic);
            int i = this.zzbic;
            StringBuilder sb2 = new StringBuilder(52);
            sb2.append("Explicit request from server to backoff: ");
            sb2.append(i);
            Log.w("InstanceID/Rpc", sb2.toString());
        } else if ("SERVICE_NOT_AVAILABLE".equals(stringExtra) || "AUTHENTICATION_FAILED".equals(stringExtra)) {
            zzeF(stringExtra);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzs(Intent intent) {
        String str;
        if (intent == null) {
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
                Log.d("InstanceID/Rpc", "Unexpected response: null");
            }
        } else if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("registration_id");
            if (stringExtra == null) {
                stringExtra = intent.getStringExtra("unregistered");
            }
            if (stringExtra == null) {
                zzr(intent);
                return;
            }
            this.zzbhZ = SystemClock.elapsedRealtime();
            this.zzbid = 0;
            this.zzbib = 0;
            this.zzbic = 0;
            if (stringExtra.startsWith("|")) {
                String[] split = stringExtra.split("\\|");
                if (!"ID".equals(split[1])) {
                    String valueOf = String.valueOf(stringExtra);
                    Log.w("InstanceID/Rpc", valueOf.length() != 0 ? "Unexpected structured response ".concat(valueOf) : new String("Unexpected structured response "));
                }
                str = split[2];
                if (split.length > 4) {
                    if ("SYNC".equals(split[3])) {
                        FirebaseInstanceId.zzby(this.zzqn);
                    } else if ("RST".equals(split[3])) {
                        FirebaseInstanceId.zza(this.zzqn, zzd.zzb(this.zzqn, (Bundle) null).zzabQ());
                        intent.removeExtra("registration_id");
                        zzb(str, intent);
                        return;
                    }
                }
                String str2 = split[split.length - 1];
                if (str2.startsWith(":")) {
                    str2 = str2.substring(1);
                }
                intent.putExtra("registration_id", str2);
            } else {
                str = null;
            }
            if (str != null) {
                zzb(str, intent);
            } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
                Log.d("InstanceID/Rpc", "Ignoring response without a request ID");
            }
        } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
            String valueOf2 = String.valueOf(intent.getAction());
            Log.d("InstanceID/Rpc", valueOf2.length() != 0 ? "Unexpected response ".concat(valueOf2) : new String("Unexpected response "));
        }
    }
}
