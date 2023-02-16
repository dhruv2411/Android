package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

class zzde implements zzad {
    private final Context mContext;
    private final String zzIA;
    private final zzb zzbIp;
    private final zza zzbIq;

    public interface zza {
        void zza(zzas zzas);

        void zzb(zzas zzas);

        void zzc(zzas zzas);
    }

    interface zzb {
        HttpURLConnection zzd(URL url) throws IOException;
    }

    zzde(Context context, zza zza2) {
        this(new zzb() {
            public HttpURLConnection zzd(URL url) throws IOException {
                return (HttpURLConnection) url.openConnection();
            }
        }, context, zza2);
    }

    zzde(zzb zzb2, Context context, zza zza2) {
        this.zzbIp = zzb2;
        this.mContext = context.getApplicationContext();
        this.zzbIq = zza2;
        this.zzIA = zza("GoogleTagManager", "4.00", Build.VERSION.RELEASE, zzc(Locale.getDefault()), Build.MODEL, Build.ID);
    }

    static String zzc(Locale locale) {
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage().toLowerCase());
        if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
            sb.append("-");
            sb.append(locale.getCountry().toLowerCase());
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x007c A[Catch:{ IOException -> 0x0083 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzP(java.util.List<com.google.android.gms.tagmanager.zzas> r11) {
        /*
            r10 = this;
            int r0 = r11.size()
            r1 = 40
            int r0 = java.lang.Math.min(r0, r1)
            r1 = 0
            r2 = 1
            r3 = r2
            r2 = r1
        L_0x000e:
            if (r2 >= r0) goto L_0x00b6
            java.lang.Object r4 = r11.get(r2)
            com.google.android.gms.tagmanager.zzas r4 = (com.google.android.gms.tagmanager.zzas) r4
            java.net.URL r5 = r10.zzd(r4)
            if (r5 != 0) goto L_0x0028
            java.lang.String r5 = "No destination: discarding hit."
            com.google.android.gms.tagmanager.zzbo.zzbh(r5)
            com.google.android.gms.tagmanager.zzde$zza r5 = r10.zzbIq
            r5.zzb(r4)
            goto L_0x00b2
        L_0x0028:
            r6 = 0
            com.google.android.gms.tagmanager.zzde$zzb r7 = r10.zzbIp     // Catch:{ IOException -> 0x0083 }
            java.net.HttpURLConnection r5 = r7.zzd(r5)     // Catch:{ IOException -> 0x0083 }
            if (r3 == 0) goto L_0x003b
            android.content.Context r7 = r10.mContext     // Catch:{ all -> 0x0038 }
            com.google.android.gms.tagmanager.zzbt.zzcc(r7)     // Catch:{ all -> 0x0038 }
            r3 = r1
            goto L_0x003b
        L_0x0038:
            r7 = move-exception
            r8 = r6
            goto L_0x007a
        L_0x003b:
            java.lang.String r7 = "User-Agent"
            java.lang.String r8 = r10.zzIA     // Catch:{ all -> 0x0038 }
            r5.setRequestProperty(r7, r8)     // Catch:{ all -> 0x0038 }
            int r7 = r5.getResponseCode()     // Catch:{ all -> 0x0038 }
            java.io.InputStream r8 = r5.getInputStream()     // Catch:{ all -> 0x0038 }
            r6 = 200(0xc8, float:2.8E-43)
            if (r7 == r6) goto L_0x006c
            r6 = 25
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x006a }
            r9.<init>(r6)     // Catch:{ all -> 0x006a }
            java.lang.String r6 = "Bad response: "
            r9.append(r6)     // Catch:{ all -> 0x006a }
            r9.append(r7)     // Catch:{ all -> 0x006a }
            java.lang.String r6 = r9.toString()     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.zzbo.zzbh(r6)     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.zzde$zza r6 = r10.zzbIq     // Catch:{ all -> 0x006a }
            r6.zzc(r4)     // Catch:{ all -> 0x006a }
            goto L_0x0071
        L_0x006a:
            r7 = move-exception
            goto L_0x007a
        L_0x006c:
            com.google.android.gms.tagmanager.zzde$zza r6 = r10.zzbIq     // Catch:{ all -> 0x006a }
            r6.zza(r4)     // Catch:{ all -> 0x006a }
        L_0x0071:
            if (r8 == 0) goto L_0x0076
            r8.close()     // Catch:{ IOException -> 0x0083 }
        L_0x0076:
            r5.disconnect()     // Catch:{ IOException -> 0x0083 }
            goto L_0x00b2
        L_0x007a:
            if (r8 == 0) goto L_0x007f
            r8.close()     // Catch:{ IOException -> 0x0083 }
        L_0x007f:
            r5.disconnect()     // Catch:{ IOException -> 0x0083 }
            throw r7     // Catch:{ IOException -> 0x0083 }
        L_0x0083:
            r5 = move-exception
            java.lang.String r6 = "Exception sending hit: "
            java.lang.Class r7 = r5.getClass()
            java.lang.String r7 = r7.getSimpleName()
            java.lang.String r7 = java.lang.String.valueOf(r7)
            int r8 = r7.length()
            if (r8 == 0) goto L_0x009d
            java.lang.String r6 = r6.concat(r7)
            goto L_0x00a3
        L_0x009d:
            java.lang.String r7 = new java.lang.String
            r7.<init>(r6)
            r6 = r7
        L_0x00a3:
            com.google.android.gms.tagmanager.zzbo.zzbh(r6)
            java.lang.String r5 = r5.getMessage()
            com.google.android.gms.tagmanager.zzbo.zzbh(r5)
            com.google.android.gms.tagmanager.zzde$zza r5 = r10.zzbIq
            r5.zzc(r4)
        L_0x00b2:
            int r2 = r2 + 1
            goto L_0x000e
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzde.zzP(java.util.List):void");
    }

    public boolean zzQF() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbo.v("...no network connectivity");
        return false;
    }

    /* access modifiers changed from: package-private */
    public String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    /* access modifiers changed from: package-private */
    public URL zzd(zzas zzas) {
        try {
            return new URL(zzas.zzQP());
        } catch (MalformedURLException unused) {
            zzbo.e("Error trying to parse the GTM url.");
            return null;
        }
    }
}
