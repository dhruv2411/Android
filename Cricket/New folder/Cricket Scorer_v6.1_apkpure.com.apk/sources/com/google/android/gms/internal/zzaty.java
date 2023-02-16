package com.google.android.gms.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class zzaty extends zzauh {

    @WorkerThread
    interface zza {
        void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map);
    }

    @WorkerThread
    private static class zzb implements Runnable {
        private final int zzJO;
        private final String zzRg;
        private final Throwable zzZa;
        private final zza zzbsP;
        private final byte[] zzbsQ;
        private final Map<String, List<String>> zzbsR;

        private zzb(String str, zza zza, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
            zzac.zzw(zza);
            this.zzbsP = zza;
            this.zzJO = i;
            this.zzZa = th;
            this.zzbsQ = bArr;
            this.zzRg = str;
            this.zzbsR = map;
        }

        public void run() {
            this.zzbsP.zza(this.zzRg, this.zzJO, this.zzZa, this.zzbsQ, this.zzbsR);
        }
    }

    @WorkerThread
    private class zzc implements Runnable {
        private final URL zzIe;
        private final String zzRg;
        private final byte[] zzaIQ;
        private final zza zzbsS;
        private final Map<String, String> zzbsT;

        public zzc(String str, URL url, byte[] bArr, Map<String, String> map, zza zza) {
            zzac.zzdr(str);
            zzac.zzw(url);
            zzac.zzw(zza);
            this.zzIe = url;
            this.zzaIQ = bArr;
            this.zzbsS = zza;
            this.zzRg = str;
            this.zzbsT = map;
        }

        /* JADX WARNING: Removed duplicated region for block: B:42:0x00c3 A[SYNTHETIC, Splitter:B:42:0x00c3] */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x00df  */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x0100 A[SYNTHETIC, Splitter:B:55:0x0100] */
        /* JADX WARNING: Removed duplicated region for block: B:60:0x011c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r12 = this;
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this
                r0.zzJX()
                r0 = 0
                r1 = 0
                com.google.android.gms.internal.zzaty r2 = com.google.android.gms.internal.zzaty.this     // Catch:{ IOException -> 0x00f9, all -> 0x00bd }
                java.net.URL r3 = r12.zzIe     // Catch:{ IOException -> 0x00f9, all -> 0x00bd }
                java.net.HttpURLConnection r2 = r2.zzc((java.net.URL) r3)     // Catch:{ IOException -> 0x00f9, all -> 0x00bd }
                java.util.Map<java.lang.String, java.lang.String> r3 = r12.zzbsT     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                if (r3 == 0) goto L_0x0039
                java.util.Map<java.lang.String, java.lang.String> r3 = r12.zzbsT     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.util.Set r3 = r3.entrySet()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.util.Iterator r3 = r3.iterator()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
            L_0x001d:
                boolean r4 = r3.hasNext()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                if (r4 == 0) goto L_0x0039
                java.lang.Object r4 = r3.next()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.lang.Object r5 = r4.getKey()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.lang.Object r4 = r4.getValue()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                r2.addRequestProperty(r5, r4)     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                goto L_0x001d
            L_0x0039:
                byte[] r3 = r12.zzaIQ     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                if (r3 == 0) goto L_0x0086
                com.google.android.gms.internal.zzaty r3 = com.google.android.gms.internal.zzaty.this     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                com.google.android.gms.internal.zzaut r3 = r3.zzKh()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                byte[] r4 = r12.zzaIQ     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                byte[] r3 = r3.zzk(r4)     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                com.google.android.gms.internal.zzaty r4 = com.google.android.gms.internal.zzaty.this     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                com.google.android.gms.internal.zzatx r4 = r4.zzKl()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                com.google.android.gms.internal.zzatx$zza r4 = r4.zzMe()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.lang.String r5 = "Uploading data. size"
                int r6 = r3.length     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                r4.zzj(r5, r6)     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                r4 = 1
                r2.setDoOutput(r4)     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.lang.String r4 = "Content-Encoding"
                java.lang.String r5 = "gzip"
                r2.addRequestProperty(r4, r5)     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                int r4 = r3.length     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                r2.setFixedLengthStreamingMode(r4)     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                r2.connect()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.io.OutputStream r4 = r2.getOutputStream()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                r4.write(r3)     // Catch:{ IOException -> 0x007f, all -> 0x007a }
                r4.close()     // Catch:{ IOException -> 0x007f, all -> 0x007a }
                goto L_0x0086
            L_0x007a:
                r3 = move-exception
                r10 = r0
                r7 = r1
                r0 = r4
                goto L_0x00c1
            L_0x007f:
                r3 = move-exception
                r10 = r0
                r7 = r1
                r8 = r3
                r0 = r4
                goto L_0x00fe
            L_0x0086:
                int r7 = r2.getResponseCode()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
                java.util.Map r10 = r2.getHeaderFields()     // Catch:{ IOException -> 0x00b4, all -> 0x00b1 }
                com.google.android.gms.internal.zzaty r1 = com.google.android.gms.internal.zzaty.this     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
                byte[] r9 = r1.zzc((java.net.HttpURLConnection) r2)     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
                if (r2 == 0) goto L_0x0099
                r2.disconnect()
            L_0x0099:
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzaud r0 = r0.zzKk()
                com.google.android.gms.internal.zzaty$zzb r1 = new com.google.android.gms.internal.zzaty$zzb
                java.lang.String r5 = r12.zzRg
                com.google.android.gms.internal.zzaty$zza r6 = r12.zzbsS
                r8 = 0
                r11 = 0
                r4 = r1
                r4.<init>(r5, r6, r7, r8, r9, r10)
                goto L_0x0131
            L_0x00ad:
                r3 = move-exception
                goto L_0x00c1
            L_0x00af:
                r3 = move-exception
                goto L_0x00fd
            L_0x00b1:
                r3 = move-exception
                r10 = r0
                goto L_0x00c1
            L_0x00b4:
                r3 = move-exception
                r10 = r0
                goto L_0x00fd
            L_0x00b7:
                r3 = move-exception
                r10 = r0
                goto L_0x00c0
            L_0x00ba:
                r3 = move-exception
                r10 = r0
                goto L_0x00fc
            L_0x00bd:
                r3 = move-exception
                r2 = r0
                r10 = r2
            L_0x00c0:
                r7 = r1
            L_0x00c1:
                if (r0 == 0) goto L_0x00dd
                r0.close()     // Catch:{ IOException -> 0x00c7 }
                goto L_0x00dd
            L_0x00c7:
                r0 = move-exception
                com.google.android.gms.internal.zzaty r1 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzatx r1 = r1.zzKl()
                com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()
                java.lang.String r4 = "Error closing HTTP compressed POST connection output stream. appId"
                java.lang.String r5 = r12.zzRg
                java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r5)
                r1.zze(r4, r5, r0)
            L_0x00dd:
                if (r2 == 0) goto L_0x00e2
                r2.disconnect()
            L_0x00e2:
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzaud r0 = r0.zzKk()
                com.google.android.gms.internal.zzaty$zzb r1 = new com.google.android.gms.internal.zzaty$zzb
                java.lang.String r5 = r12.zzRg
                com.google.android.gms.internal.zzaty$zza r6 = r12.zzbsS
                r8 = 0
                r9 = 0
                r11 = 0
                r4 = r1
                r4.<init>(r5, r6, r7, r8, r9, r10)
                r0.zzm(r1)
                throw r3
            L_0x00f9:
                r3 = move-exception
                r2 = r0
                r10 = r2
            L_0x00fc:
                r7 = r1
            L_0x00fd:
                r8 = r3
            L_0x00fe:
                if (r0 == 0) goto L_0x011a
                r0.close()     // Catch:{ IOException -> 0x0104 }
                goto L_0x011a
            L_0x0104:
                r0 = move-exception
                com.google.android.gms.internal.zzaty r1 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzatx r1 = r1.zzKl()
                com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()
                java.lang.String r3 = "Error closing HTTP compressed POST connection output stream. appId"
                java.lang.String r4 = r12.zzRg
                java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r4)
                r1.zze(r3, r4, r0)
            L_0x011a:
                if (r2 == 0) goto L_0x011f
                r2.disconnect()
            L_0x011f:
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzaud r0 = r0.zzKk()
                com.google.android.gms.internal.zzaty$zzb r1 = new com.google.android.gms.internal.zzaty$zzb
                java.lang.String r5 = r12.zzRg
                com.google.android.gms.internal.zzaty$zza r6 = r12.zzbsS
                r9 = 0
                r11 = 0
                r4 = r1
                r4.<init>(r5, r6, r7, r8, r9, r10)
            L_0x0131:
                r0.zzm(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaty.zzc.run():void");
        }
    }

    public zzaty(zzaue zzaue) {
        super(zzaue);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002b  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] zzc(java.net.HttpURLConnection r6) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0025 }
            r1.<init>()     // Catch:{ all -> 0x0025 }
            java.io.InputStream r6 = r6.getInputStream()     // Catch:{ all -> 0x0025 }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0023 }
        L_0x000e:
            int r2 = r6.read(r0)     // Catch:{ all -> 0x0023 }
            if (r2 <= 0) goto L_0x0019
            r3 = 0
            r1.write(r0, r3, r2)     // Catch:{ all -> 0x0023 }
            goto L_0x000e
        L_0x0019:
            byte[] r0 = r1.toByteArray()     // Catch:{ all -> 0x0023 }
            if (r6 == 0) goto L_0x0022
            r6.close()
        L_0x0022:
            return r0
        L_0x0023:
            r0 = move-exception
            goto L_0x0029
        L_0x0025:
            r6 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
        L_0x0029:
            if (r6 == 0) goto L_0x002e
            r6.close()
        L_0x002e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaty.zzc(java.net.HttpURLConnection):byte[]");
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzJV() {
        super.zzJV();
    }

    public /* bridge */ /* synthetic */ void zzJW() {
        super.zzJW();
    }

    public /* bridge */ /* synthetic */ void zzJX() {
        super.zzJX();
    }

    public /* bridge */ /* synthetic */ zzatb zzJY() {
        return super.zzJY();
    }

    public /* bridge */ /* synthetic */ zzatf zzJZ() {
        return super.zzJZ();
    }

    public /* bridge */ /* synthetic */ zzauj zzKa() {
        return super.zzKa();
    }

    public /* bridge */ /* synthetic */ zzatu zzKb() {
        return super.zzKb();
    }

    public /* bridge */ /* synthetic */ zzatl zzKc() {
        return super.zzKc();
    }

    public /* bridge */ /* synthetic */ zzaul zzKd() {
        return super.zzKd();
    }

    public /* bridge */ /* synthetic */ zzauk zzKe() {
        return super.zzKe();
    }

    public /* bridge */ /* synthetic */ zzatv zzKf() {
        return super.zzKf();
    }

    public /* bridge */ /* synthetic */ zzatj zzKg() {
        return super.zzKg();
    }

    public /* bridge */ /* synthetic */ zzaut zzKh() {
        return super.zzKh();
    }

    public /* bridge */ /* synthetic */ zzauc zzKi() {
        return super.zzKi();
    }

    public /* bridge */ /* synthetic */ zzaun zzKj() {
        return super.zzKj();
    }

    public /* bridge */ /* synthetic */ zzaud zzKk() {
        return super.zzKk();
    }

    public /* bridge */ /* synthetic */ zzatx zzKl() {
        return super.zzKl();
    }

    public /* bridge */ /* synthetic */ zzaua zzKm() {
        return super.zzKm();
    }

    public /* bridge */ /* synthetic */ zzati zzKn() {
        return super.zzKn();
    }

    @WorkerThread
    public void zza(String str, URL url, Map<String, String> map, zza zza2) {
        zzmR();
        zzob();
        zzac.zzw(url);
        zzac.zzw(zza2);
        zzKk().zzn(new zzc(str, url, (byte[]) null, map, zza2));
    }

    @WorkerThread
    public void zza(String str, URL url, byte[] bArr, Map<String, String> map, zza zza2) {
        zzmR();
        zzob();
        zzac.zzw(url);
        zzac.zzw(bArr);
        zzac.zzw(zza2);
        zzKk().zzn(new zzc(str, url, bArr, map, zza2));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public HttpURLConnection zzc(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain HTTP connection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        zzKn().zzLd();
        httpURLConnection.setConnectTimeout(60000);
        zzKn().zzLe();
        httpURLConnection.setReadTimeout(61000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }

    public boolean zzqa() {
        NetworkInfo networkInfo;
        zzob();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            networkInfo = null;
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
