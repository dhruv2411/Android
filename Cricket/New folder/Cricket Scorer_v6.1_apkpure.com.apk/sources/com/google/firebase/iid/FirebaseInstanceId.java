package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.zzh;
import com.itextpdf.text.pdf.BidiOrder;
import com.itextpdf.text.pdf.security.SecurityConstants;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class FirebaseInstanceId {
    private static Map<String, FirebaseInstanceId> zzbhH = new ArrayMap();
    private static zze zzclh;
    private final FirebaseApp zzcli;
    private final zzd zzclj;
    private final String zzclk = zzabM();

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzd zzd) {
        this.zzcli = firebaseApp;
        this.zzclj = zzd;
        if (this.zzclk == null) {
            throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
        }
        FirebaseInstanceIdService.zza(this.zzcli.getApplicationContext(), this);
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = zzbhH.get(firebaseApp.getOptions().getApplicationId());
            if (firebaseInstanceId == null) {
                zzd zzb = zzd.zzb(firebaseApp.getApplicationContext(), (Bundle) null);
                if (zzclh == null) {
                    zzclh = new zze(zzb.zzabQ());
                }
                FirebaseInstanceId firebaseInstanceId2 = new FirebaseInstanceId(firebaseApp, zzb);
                zzbhH.put(firebaseApp.getOptions().getApplicationId(), firebaseInstanceId2);
                firebaseInstanceId = firebaseInstanceId2;
            }
        }
        return firebaseInstanceId;
    }

    static int zzR(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(23 + String.valueOf(valueOf).length());
            sb.append("Failed to find package ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return 0;
        }
    }

    private void zzS(Bundle bundle) {
        bundle.putString("gmp_app_id", this.zzcli.getOptions().getApplicationId());
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance(SecurityConstants.SHA1).digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((112 + (digest[0] & BidiOrder.B)) & 255);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }

    static void zza(Context context, zzh zzh) {
        zzh.zzHo();
        Intent intent = new Intent();
        intent.putExtra("CMD", "RST");
        zzg.zzabU().zzf(context, intent);
    }

    static String zzbx(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(38 + String.valueOf(valueOf).length());
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    static void zzby(Context context) {
        Intent intent = new Intent();
        intent.putExtra("CMD", "SYNC");
        zzg.zzabU().zzf(context, intent);
    }

    static int zzcr(Context context) {
        return zzR(context, context.getPackageName());
    }

    static String zzv(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public void deleteInstanceId() throws IOException {
        this.zzclj.zzb("*", "*", (Bundle) null);
        this.zzclj.zzHi();
    }

    @WorkerThread
    public void deleteToken(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        zzS(bundle);
        this.zzclj.zzb(str, str2, bundle);
    }

    public long getCreationTime() {
        return this.zzclj.getCreationTime();
    }

    public String getId() {
        return zza(this.zzclj.zzHh());
    }

    @Nullable
    public String getToken() {
        zzh.zza zzabN = zzabN();
        if (zzabN == null || zzabN.zzjB(zzd.zzbhN)) {
            FirebaseInstanceIdService.zzcs(this.zzcli.getApplicationContext());
        }
        if (zzabN != null) {
            return zzabN.zzbxT;
        }
        return null;
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        zzS(bundle);
        return this.zzclj.getToken(str, str2, bundle);
    }

    /* access modifiers changed from: package-private */
    public String zzabM() {
        String gcmSenderId = this.zzcli.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = this.zzcli.getOptions().getApplicationId();
        if (applicationId.startsWith("1:")) {
            String[] split = applicationId.split(":");
            if (split.length < 2) {
                return null;
            }
            applicationId = split[1];
            if (applicationId.isEmpty()) {
                return null;
            }
        }
        return applicationId;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public zzh.zza zzabN() {
        return this.zzclj.zzabQ().zzu("", this.zzclk, "*");
    }

    /* access modifiers changed from: package-private */
    public String zzabO() throws IOException {
        return getToken(this.zzclk, "*");
    }

    /* access modifiers changed from: package-private */
    public zze zzabP() {
        return zzclh;
    }

    public String zzc(String str, String str2, Bundle bundle) throws IOException {
        zzS(bundle);
        return this.zzclj.zzc(str, str2, bundle);
    }

    public void zzjt(String str) {
        zzclh.zzjt(str);
        FirebaseInstanceIdService.zzcs(this.zzcli.getApplicationContext());
    }

    /* access modifiers changed from: package-private */
    public void zzju(String str) throws IOException {
        zzh.zza zzabN = zzabN();
        if (zzabN == null || zzabN.zzjB(zzd.zzbhN)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString("gcm.topic", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        String str2 = zzabN.zzbxT;
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str);
        zzc(str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle);
    }

    /* access modifiers changed from: package-private */
    public void zzjv(String str) throws IOException {
        zzh.zza zzabN = zzabN();
        if (zzabN == null || zzabN.zzjB(zzd.zzbhN)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString("gcm.topic", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zzd zzd = this.zzclj;
        String str2 = zzabN.zzbxT;
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str);
        zzd.zzb(str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle);
    }
}
