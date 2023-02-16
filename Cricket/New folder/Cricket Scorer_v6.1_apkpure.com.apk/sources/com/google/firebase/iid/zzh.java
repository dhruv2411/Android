package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzx;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

class zzh {
    SharedPreferences zzbif;
    Context zzqn;

    static class zza {
        private static final long zzclF = TimeUnit.DAYS.toMillis(7);
        final long timestamp;
        final String zzbhN;
        final String zzbxT;

        private zza(String str, String str2, long j) {
            this.zzbxT = str;
            this.zzbhN = str2;
            this.timestamp = j;
        }

        static String zzd(String str, String str2, long j) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("token", str);
                jSONObject.put("appVersion", str2);
                jSONObject.put("timestamp", j);
                return jSONObject.toString();
            } catch (JSONException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(24 + String.valueOf(valueOf).length());
                sb.append("Failed to encode token: ");
                sb.append(valueOf);
                Log.w("InstanceID/Store", sb.toString());
                return null;
            }
        }

        static zza zzjA(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (!str.startsWith("{")) {
                return new zza(str, (String) null, 0);
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new zza(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong("timestamp"));
            } catch (JSONException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(23 + String.valueOf(valueOf).length());
                sb.append("Failed to parse token: ");
                sb.append(valueOf);
                Log.w("InstanceID/Store", sb.toString());
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean zzjB(String str) {
            return System.currentTimeMillis() > this.timestamp + zzclF || !str.equals(this.zzbhN);
        }
    }

    public zzh(Context context) {
        this(context, "com.google.android.gms.appid");
    }

    public zzh(Context context, String str) {
        this.zzqn = context;
        this.zzbif = context.getSharedPreferences(str, 0);
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("-no-backup");
        zzeG(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    private String zzaz(String str, String str2) {
        String valueOf = String.valueOf("|S|");
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + String.valueOf(valueOf).length() + String.valueOf(str2).length());
        sb.append(str);
        sb.append(valueOf);
        sb.append(str2);
        return sb.toString();
    }

    private void zzeG(String str) {
        File file = new File(zzx.getNoBackupFilesDir(this.zzqn), str);
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i("InstanceID/Store", "App restored, clearing state");
                    FirebaseInstanceId.zza(this.zzqn, this);
                }
            } catch (IOException e) {
                if (Log.isLoggable("InstanceID/Store", 3)) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d("InstanceID/Store", valueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(valueOf) : new String("Error creating file in no backup dir: "));
                }
            }
        }
    }

    private void zzeH(String str) {
        SharedPreferences.Editor edit = this.zzbif.edit();
        for (String next : this.zzbif.getAll().keySet()) {
            if (next.startsWith(str)) {
                edit.remove(next);
            }
        }
        edit.commit();
    }

    private String zzt(String str, String str2, String str3) {
        String valueOf = String.valueOf("|T|");
        StringBuilder sb = new StringBuilder(1 + String.valueOf(str).length() + String.valueOf(valueOf).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append(valueOf);
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    public synchronized boolean isEmpty() {
        return this.zzbif.getAll().isEmpty();
    }

    public synchronized void zzHo() {
        this.zzbif.edit().clear().commit();
    }

    public synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String zzd = zza.zzd(str4, str5, System.currentTimeMillis());
        if (zzd != null) {
            SharedPreferences.Editor edit = this.zzbif.edit();
            edit.putString(zzt(str, str2, str3), zzd);
            edit.commit();
        }
    }

    public SharedPreferences zzabX() {
        return this.zzbif;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0076, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.security.KeyPair zzeI(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            android.content.SharedPreferences r0 = r4.zzbif     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "|P|"
            java.lang.String r1 = r4.zzaz(r5, r1)     // Catch:{ all -> 0x0077 }
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)     // Catch:{ all -> 0x0077 }
            android.content.SharedPreferences r1 = r4.zzbif     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = "|K|"
            java.lang.String r5 = r4.zzaz(r5, r3)     // Catch:{ all -> 0x0077 }
            java.lang.String r5 = r1.getString(r5, r2)     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0075
            if (r5 != 0) goto L_0x001f
            goto L_0x0075
        L_0x001f:
            r1 = 8
            byte[] r0 = android.util.Base64.decode(r0, r1)     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            byte[] r5 = android.util.Base64.decode(r5, r1)     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            java.lang.String r1 = "RSA"
            java.security.KeyFactory r1 = java.security.KeyFactory.getInstance(r1)     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            java.security.spec.X509EncodedKeySpec r3 = new java.security.spec.X509EncodedKeySpec     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            r3.<init>(r0)     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            java.security.PublicKey r0 = r1.generatePublic(r3)     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            java.security.spec.PKCS8EncodedKeySpec r3 = new java.security.spec.PKCS8EncodedKeySpec     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            r3.<init>(r5)     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            java.security.PrivateKey r5 = r1.generatePrivate(r3)     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            java.security.KeyPair r1 = new java.security.KeyPair     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            r1.<init>(r0, r5)     // Catch:{ NoSuchAlgorithmException | InvalidKeySpecException -> 0x0048 }
            monitor-exit(r4)
            return r1
        L_0x0048:
            r5 = move-exception
            java.lang.String r0 = "InstanceID/Store"
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0077 }
            r1 = 19
            java.lang.String r3 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0077 }
            int r3 = r3.length()     // Catch:{ all -> 0x0077 }
            int r1 = r1 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0077 }
            r3.<init>(r1)     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "Invalid key stored "
            r3.append(r1)     // Catch:{ all -> 0x0077 }
            r3.append(r5)     // Catch:{ all -> 0x0077 }
            java.lang.String r5 = r3.toString()     // Catch:{ all -> 0x0077 }
            android.util.Log.w(r0, r5)     // Catch:{ all -> 0x0077 }
            android.content.Context r5 = r4.zzqn     // Catch:{ all -> 0x0077 }
            com.google.firebase.iid.FirebaseInstanceId.zza(r5, r4)     // Catch:{ all -> 0x0077 }
            monitor-exit(r4)
            return r2
        L_0x0075:
            monitor-exit(r4)
            return r2
        L_0x0077:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzh.zzeI(java.lang.String):java.security.KeyPair");
    }

    /* access modifiers changed from: package-private */
    public synchronized void zzeJ(String str) {
        zzeH(String.valueOf(str).concat("|"));
    }

    public synchronized void zzeK(String str) {
        zzeH(String.valueOf(str).concat("|T|"));
    }

    public synchronized void zzi(String str, String str2, String str3) {
        String zzt = zzt(str, str2, str3);
        SharedPreferences.Editor edit = this.zzbif.edit();
        edit.remove(zzt);
        edit.commit();
    }

    public synchronized long zzjy(String str) {
        String string = this.zzbif.getString(zzaz(str, "cre"), (String) null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public synchronized KeyPair zzjz(String str) {
        KeyPair zzHg;
        zzHg = zza.zzHg();
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor edit = this.zzbif.edit();
        edit.putString(zzaz(str, "|P|"), FirebaseInstanceId.zzv(zzHg.getPublic().getEncoded()));
        edit.putString(zzaz(str, "|K|"), FirebaseInstanceId.zzv(zzHg.getPrivate().getEncoded()));
        edit.putString(zzaz(str, "cre"), Long.toString(currentTimeMillis));
        edit.commit();
        return zzHg;
    }

    public synchronized zza zzu(String str, String str2, String str3) {
        return zza.zzjA(this.zzbif.getString(zzt(str, str2, str3), (String) null));
    }
}
