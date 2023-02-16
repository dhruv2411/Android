package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.zzaj;
import com.google.android.gms.internal.zzbjj;
import com.google.android.gms.tagmanager.zzcj;

class zzct implements Runnable {
    private final Context mContext;
    private final String zzbEU;
    private volatile String zzbFs;
    private final zzbjj zzbHr;
    private final String zzbHs;
    private zzbn<zzaj.zzj> zzbHt;
    private volatile zzt zzbHu;
    private volatile String zzbHv;

    zzct(Context context, String str, zzbjj zzbjj, zzt zzt) {
        this.mContext = context;
        this.zzbHr = zzbjj;
        this.zzbEU = str;
        this.zzbHu = zzt;
        String valueOf = String.valueOf("/r?id=");
        String valueOf2 = String.valueOf(str);
        this.zzbHs = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        this.zzbFs = this.zzbHs;
        this.zzbHv = null;
    }

    public zzct(Context context, String str, zzt zzt) {
        this(context, str, new zzbjj(), zzt);
    }

    private boolean zzRh() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbo.v("...no network connectivity");
        return false;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x0136 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzRi() {
        /*
            r6 = this;
            boolean r0 = r6.zzRh()
            if (r0 != 0) goto L_0x000e
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzaj$zzj> r0 = r6.zzbHt
            com.google.android.gms.tagmanager.zzbn$zza r1 = com.google.android.gms.tagmanager.zzbn.zza.NOT_AVAILABLE
            r0.zza(r1)
            return
        L_0x000e:
            java.lang.String r0 = "Start loading resource from network ..."
            com.google.android.gms.tagmanager.zzbo.v(r0)
            java.lang.String r0 = r6.zzRj()
            com.google.android.gms.internal.zzbjj r1 = r6.zzbHr
            com.google.android.gms.internal.zzbji r1 = r1.zzTF()
            r2 = 0
            java.io.InputStream r3 = r1.zzhX(r0)     // Catch:{ FileNotFoundException -> 0x0136, zzbjk -> 0x006b, IOException -> 0x0027 }
            r2 = r3
            goto L_0x008c
        L_0x0024:
            r0 = move-exception
            goto L_0x0178
        L_0x0027:
            r2 = move-exception
            java.lang.String r3 = r2.getMessage()     // Catch:{ all -> 0x0024 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0024 }
            r4 = 40
            java.lang.String r5 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0024 }
            int r5 = r5.length()     // Catch:{ all -> 0x0024 }
            int r4 = r4 + r5
            java.lang.String r5 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0024 }
            int r5 = r5.length()     // Catch:{ all -> 0x0024 }
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0024 }
            r5.<init>(r4)     // Catch:{ all -> 0x0024 }
            java.lang.String r4 = "Error when loading resources from url: "
            r5.append(r4)     // Catch:{ all -> 0x0024 }
            r5.append(r0)     // Catch:{ all -> 0x0024 }
            java.lang.String r0 = " "
            r5.append(r0)     // Catch:{ all -> 0x0024 }
            r5.append(r3)     // Catch:{ all -> 0x0024 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbo.zzc(r0, r2)     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzaj$zzj> r0 = r6.zzbHt     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbn$zza r2 = com.google.android.gms.tagmanager.zzbn.zza.IO_ERROR     // Catch:{ all -> 0x0024 }
            r0.zza(r2)     // Catch:{ all -> 0x0024 }
            r1.close()
            return
        L_0x006b:
            java.lang.String r3 = "Error when loading resource for url: "
            java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0024 }
            int r5 = r4.length()     // Catch:{ all -> 0x0024 }
            if (r5 == 0) goto L_0x007c
            java.lang.String r3 = r3.concat(r4)     // Catch:{ all -> 0x0024 }
            goto L_0x0082
        L_0x007c:
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x0024 }
            r4.<init>(r3)     // Catch:{ all -> 0x0024 }
            r3 = r4
        L_0x0082:
            com.google.android.gms.tagmanager.zzbo.zzbh(r3)     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzaj$zzj> r3 = r6.zzbHt     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbn$zza r4 = com.google.android.gms.tagmanager.zzbn.zza.SERVER_UNAVAILABLE_ERROR     // Catch:{ all -> 0x0024 }
            r3.zza(r4)     // Catch:{ all -> 0x0024 }
        L_0x008c:
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00f2 }
            r3.<init>()     // Catch:{ IOException -> 0x00f2 }
            com.google.android.gms.internal.zzbjf.zzc(r2, r3)     // Catch:{ IOException -> 0x00f2 }
            byte[] r2 = r3.toByteArray()     // Catch:{ IOException -> 0x00f2 }
            com.google.android.gms.internal.zzaj$zzj r2 = com.google.android.gms.internal.zzaj.zzj.zzg(r2)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x00f2 }
            r4 = 43
            java.lang.String r5 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x00f2 }
            int r5 = r5.length()     // Catch:{ IOException -> 0x00f2 }
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00f2 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r4 = "Successfully loaded supplemented resource: "
            r5.append(r4)     // Catch:{ IOException -> 0x00f2 }
            r5.append(r3)     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r3 = r5.toString()     // Catch:{ IOException -> 0x00f2 }
            com.google.android.gms.tagmanager.zzbo.v(r3)     // Catch:{ IOException -> 0x00f2 }
            com.google.android.gms.internal.zzaj$zzf r3 = r2.zzlr     // Catch:{ IOException -> 0x00f2 }
            if (r3 != 0) goto L_0x00e4
            com.google.android.gms.internal.zzaj$zzi[] r3 = r2.zzlq     // Catch:{ IOException -> 0x00f2 }
            int r3 = r3.length     // Catch:{ IOException -> 0x00f2 }
            if (r3 != 0) goto L_0x00e4
            java.lang.String r3 = "No change for container: "
            java.lang.String r4 = r6.zzbEU     // Catch:{ IOException -> 0x00f2 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x00f2 }
            int r5 = r4.length()     // Catch:{ IOException -> 0x00f2 }
            if (r5 == 0) goto L_0x00db
            java.lang.String r3 = r3.concat(r4)     // Catch:{ IOException -> 0x00f2 }
            goto L_0x00e1
        L_0x00db:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x00f2 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x00f2 }
            r3 = r4
        L_0x00e1:
            com.google.android.gms.tagmanager.zzbo.v(r3)     // Catch:{ IOException -> 0x00f2 }
        L_0x00e4:
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzaj$zzj> r3 = r6.zzbHt     // Catch:{ IOException -> 0x00f2 }
            r3.onSuccess(r2)     // Catch:{ IOException -> 0x00f2 }
            r1.close()
            java.lang.String r0 = "Load resource from network finished."
            com.google.android.gms.tagmanager.zzbo.v(r0)
            return
        L_0x00f2:
            r2 = move-exception
            java.lang.String r3 = r2.getMessage()     // Catch:{ all -> 0x0024 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0024 }
            r4 = 51
            java.lang.String r5 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0024 }
            int r5 = r5.length()     // Catch:{ all -> 0x0024 }
            int r4 = r4 + r5
            java.lang.String r5 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0024 }
            int r5 = r5.length()     // Catch:{ all -> 0x0024 }
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0024 }
            r5.<init>(r4)     // Catch:{ all -> 0x0024 }
            java.lang.String r4 = "Error when parsing downloaded resources from url: "
            r5.append(r4)     // Catch:{ all -> 0x0024 }
            r5.append(r0)     // Catch:{ all -> 0x0024 }
            java.lang.String r0 = " "
            r5.append(r0)     // Catch:{ all -> 0x0024 }
            r5.append(r3)     // Catch:{ all -> 0x0024 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbo.zzc(r0, r2)     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzaj$zzj> r0 = r6.zzbHt     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbn$zza r2 = com.google.android.gms.tagmanager.zzbn.zza.SERVER_ERROR     // Catch:{ all -> 0x0024 }
            r0.zza(r2)     // Catch:{ all -> 0x0024 }
            r1.close()
            return
        L_0x0136:
            java.lang.String r2 = r6.zzbEU     // Catch:{ all -> 0x0024 }
            r3 = 79
            java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0024 }
            int r4 = r4.length()     // Catch:{ all -> 0x0024 }
            int r3 = r3 + r4
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0024 }
            int r4 = r4.length()     // Catch:{ all -> 0x0024 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0024 }
            r4.<init>(r3)     // Catch:{ all -> 0x0024 }
            java.lang.String r3 = "No data is retrieved from the given url: "
            r4.append(r3)     // Catch:{ all -> 0x0024 }
            r4.append(r0)     // Catch:{ all -> 0x0024 }
            java.lang.String r0 = ". Make sure container_id: "
            r4.append(r0)     // Catch:{ all -> 0x0024 }
            r4.append(r2)     // Catch:{ all -> 0x0024 }
            java.lang.String r0 = " is correct."
            r4.append(r0)     // Catch:{ all -> 0x0024 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbo.zzbh(r0)     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzaj$zzj> r0 = r6.zzbHt     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.zzbn$zza r2 = com.google.android.gms.tagmanager.zzbn.zza.SERVER_ERROR     // Catch:{ all -> 0x0024 }
            r0.zza(r2)     // Catch:{ all -> 0x0024 }
            r1.close()
            return
        L_0x0178:
            r1.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzct.zzRi():void");
    }

    public void run() {
        if (this.zzbHt == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        zzRi();
    }

    /* access modifiers changed from: package-private */
    public String zzRj() {
        String valueOf = String.valueOf(this.zzbHu.zzQv());
        String str = this.zzbFs;
        String valueOf2 = String.valueOf("&v=a65833898");
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + String.valueOf(str).length() + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append(str);
        sb.append(valueOf2);
        String sb2 = sb.toString();
        if (this.zzbHv != null && !this.zzbHv.trim().equals("")) {
            String valueOf3 = String.valueOf(sb2);
            String valueOf4 = String.valueOf("&pv=");
            String str2 = this.zzbHv;
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length() + String.valueOf(str2).length());
            sb3.append(valueOf3);
            sb3.append(valueOf4);
            sb3.append(str2);
            sb2 = sb3.toString();
        }
        if (!zzcj.zzRe().zzRf().equals(zzcj.zza.CONTAINER_DEBUG)) {
            return sb2;
        }
        String valueOf5 = String.valueOf(sb2);
        String valueOf6 = String.valueOf("&gtm_debug=x");
        return valueOf6.length() != 0 ? valueOf5.concat(valueOf6) : new String(valueOf5);
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbn<zzaj.zzj> zzbn) {
        this.zzbHt = zzbn;
    }

    /* access modifiers changed from: package-private */
    public void zzgZ(String str) {
        if (str == null) {
            str = this.zzbHs;
        } else {
            String valueOf = String.valueOf(str);
            zzbo.zzbf(valueOf.length() != 0 ? "Setting CTFE URL path: ".concat(valueOf) : new String("Setting CTFE URL path: "));
        }
        this.zzbFs = str;
    }

    /* access modifiers changed from: package-private */
    public void zzho(String str) {
        String valueOf = String.valueOf(str);
        zzbo.zzbf(valueOf.length() != 0 ? "Setting previous container version: ".concat(valueOf) : new String("Setting previous container version: "));
        this.zzbHv = str;
    }
}
