package com.google.android.gms.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.common.internal.zzac;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

class zztf extends zzsa {
    /* access modifiers changed from: private */
    public static final byte[] zzagz = "\n".getBytes();
    private final String zzIA = zza("GoogleAnalytics", zzsb.VERSION, Build.VERSION.RELEASE, zztm.zza(Locale.getDefault()), Build.MODEL, Build.ID);
    private final zztj zzagy;

    private class zza {
        private int zzagA;
        private ByteArrayOutputStream zzagB = new ByteArrayOutputStream();

        public zza() {
        }

        public byte[] getPayload() {
            return this.zzagB.toByteArray();
        }

        public boolean zzj(zzsz zzsz) {
            zzac.zzw(zzsz);
            if (this.zzagA + 1 > zztf.this.zznT().zzph()) {
                return false;
            }
            String zza = zztf.this.zza(zzsz, false);
            if (zza == null) {
                zztf.this.zznS().zza(zzsz, "Error formatting hit");
                return true;
            }
            byte[] bytes = zza.getBytes();
            int length = bytes.length;
            if (length > zztf.this.zznT().zzoZ()) {
                zztf.this.zznS().zza(zzsz, "Hit size exceeds the maximum size limit");
                return true;
            }
            if (this.zzagB.size() > 0) {
                length++;
            }
            if (this.zzagB.size() + length > zztf.this.zznT().zzpb()) {
                return false;
            }
            try {
                if (this.zzagB.size() > 0) {
                    this.zzagB.write(zztf.zzagz);
                }
                this.zzagB.write(bytes);
                this.zzagA++;
                return true;
            } catch (IOException e) {
                zztf.this.zze("Failed to write payload when batching hits", e);
                return true;
            }
        }

        public int zzqd() {
            return this.zzagA;
        }
    }

    zztf(zzsc zzsc) {
        super(zzsc);
        this.zzagy = new zztj(zzsc.zznR());
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x007f A[SYNTHETIC, Splitter:B:35:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0093 A[SYNTHETIC, Splitter:B:43:0x0093] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int zza(java.net.URL r4, byte[] r5) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.zzac.zzw(r4)
            com.google.android.gms.common.internal.zzac.zzw(r5)
            java.lang.String r0 = "POST bytes, url"
            int r1 = r5.length
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.zzb(r0, r1, r4)
            boolean r0 = r3.zzkI()
            if (r0 == 0) goto L_0x0020
            java.lang.String r0 = "Post payload\n"
            java.lang.String r1 = new java.lang.String
            r1.<init>(r5)
            r3.zza(r0, r1)
        L_0x0020:
            r0 = 0
            android.content.Context r1 = r3.getContext()     // Catch:{ IOException -> 0x0076, all -> 0x0073 }
            r1.getPackageName()     // Catch:{ IOException -> 0x0076, all -> 0x0073 }
            java.net.HttpURLConnection r4 = r3.zzc(r4)     // Catch:{ IOException -> 0x0076, all -> 0x0073 }
            r1 = 1
            r4.setDoOutput(r1)     // Catch:{ IOException -> 0x0071 }
            int r1 = r5.length     // Catch:{ IOException -> 0x0071 }
            r4.setFixedLengthStreamingMode(r1)     // Catch:{ IOException -> 0x0071 }
            r4.connect()     // Catch:{ IOException -> 0x0071 }
            java.io.OutputStream r1 = r4.getOutputStream()     // Catch:{ IOException -> 0x0071 }
            r1.write(r5)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r3.zzb((java.net.HttpURLConnection) r4)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            int r5 = r4.getResponseCode()     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r0 = 200(0xc8, float:2.8E-43)
            if (r5 != r0) goto L_0x0050
            com.google.android.gms.internal.zzry r0 = r3.zzmA()     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r0.zznP()     // Catch:{ IOException -> 0x006e, all -> 0x006b }
        L_0x0050:
            java.lang.String r0 = "POST status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r3.zzb(r0, r2)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            if (r1 == 0) goto L_0x0065
            r1.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0065
        L_0x005f:
            r0 = move-exception
            java.lang.String r1 = "Error closing http post connection output stream"
            r3.zze(r1, r0)
        L_0x0065:
            if (r4 == 0) goto L_0x006a
            r4.disconnect()
        L_0x006a:
            return r5
        L_0x006b:
            r5 = move-exception
            r0 = r1
            goto L_0x0091
        L_0x006e:
            r5 = move-exception
            r0 = r1
            goto L_0x0078
        L_0x0071:
            r5 = move-exception
            goto L_0x0078
        L_0x0073:
            r5 = move-exception
            r4 = r0
            goto L_0x0091
        L_0x0076:
            r5 = move-exception
            r4 = r0
        L_0x0078:
            java.lang.String r1 = "Network POST connection error"
            r3.zzd(r1, r5)     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x0089
            r0.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0089
        L_0x0083:
            r5 = move-exception
            java.lang.String r0 = "Error closing http post connection output stream"
            r3.zze(r0, r5)
        L_0x0089:
            if (r4 == 0) goto L_0x008e
            r4.disconnect()
        L_0x008e:
            r4 = 0
            return r4
        L_0x0090:
            r5 = move-exception
        L_0x0091:
            if (r0 == 0) goto L_0x009d
            r0.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x009d
        L_0x0097:
            r0 = move-exception
            java.lang.String r1 = "Error closing http post connection output stream"
            r3.zze(r1, r0)
        L_0x009d:
            if (r4 == 0) goto L_0x00a2
            r4.disconnect()
        L_0x00a2:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztf.zza(java.net.URL, byte[]):int");
    }

    private static String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    private void zza(StringBuilder sb, String str, String str2) throws UnsupportedEncodingException {
        if (sb.length() != 0) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(str, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, "UTF-8"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int zzb(java.net.URL r5) {
        /*
            r4 = this;
            com.google.android.gms.common.internal.zzac.zzw(r5)
            java.lang.String r0 = "GET request"
            r4.zzb(r0, r5)
            r0 = 0
            java.net.HttpURLConnection r5 = r4.zzc(r5)     // Catch:{ IOException -> 0x003a, all -> 0x0035 }
            r5.connect()     // Catch:{ IOException -> 0x0033 }
            r4.zzb((java.net.HttpURLConnection) r5)     // Catch:{ IOException -> 0x0033 }
            int r0 = r5.getResponseCode()     // Catch:{ IOException -> 0x0033 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 != r1) goto L_0x0022
            com.google.android.gms.internal.zzry r1 = r4.zzmA()     // Catch:{ IOException -> 0x0033 }
            r1.zznP()     // Catch:{ IOException -> 0x0033 }
        L_0x0022:
            java.lang.String r1 = "GET status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x0033 }
            r4.zzb(r1, r2)     // Catch:{ IOException -> 0x0033 }
            if (r5 == 0) goto L_0x0030
            r5.disconnect()
        L_0x0030:
            return r0
        L_0x0031:
            r0 = move-exception
            goto L_0x004a
        L_0x0033:
            r0 = move-exception
            goto L_0x003e
        L_0x0035:
            r5 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L_0x004a
        L_0x003a:
            r5 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
        L_0x003e:
            java.lang.String r1 = "Network GET connection error"
            r4.zzd(r1, r0)     // Catch:{ all -> 0x0031 }
            if (r5 == 0) goto L_0x0048
            r5.disconnect()
        L_0x0048:
            r5 = 0
            return r5
        L_0x004a:
            if (r5 == 0) goto L_0x004f
            r5.disconnect()
        L_0x004f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztf.zzb(java.net.URL):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c4 A[SYNTHETIC, Splitter:B:42:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d8 A[SYNTHETIC, Splitter:B:50:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int zzb(java.net.URL r10, byte[] r11) {
        /*
            r9 = this;
            com.google.android.gms.common.internal.zzac.zzw(r10)
            com.google.android.gms.common.internal.zzac.zzw(r11)
            r0 = 0
            android.content.Context r1 = r9.getContext()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r1.getPackageName()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            byte[] r1 = zzk(r11)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.String r2 = "POST compressed size, ratio %, url"
            int r3 = r1.length     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r4 = 100
            int r6 = r1.length     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            long r6 = (long) r6     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            long r4 = r4 * r6
            int r6 = r11.length     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            long r6 = (long) r6     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            long r4 = r4 / r6
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r9.zza(r2, r3, r4, r10)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            int r2 = r1.length     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            int r3 = r11.length     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            if (r2 <= r3) goto L_0x003b
            java.lang.String r2 = "Compressed payload is larger then uncompressed. compressed, uncompressed"
            int r3 = r1.length     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            int r4 = r11.length     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r9.zzc(r2, r3, r4)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        L_0x003b:
            boolean r2 = r9.zzkI()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            if (r2 == 0) goto L_0x0061
            java.lang.String r2 = "Post payload"
            java.lang.String r3 = "\n"
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r4.<init>(r11)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            java.lang.String r11 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            int r4 = r11.length()     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            if (r4 == 0) goto L_0x0059
            java.lang.String r11 = r3.concat(r11)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            goto L_0x005e
        L_0x0059:
            java.lang.String r11 = new java.lang.String     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r11.<init>(r3)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        L_0x005e:
            r9.zza(r2, r11)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
        L_0x0061:
            java.net.HttpURLConnection r10 = r9.zzc(r10)     // Catch:{ IOException -> 0x00bb, all -> 0x00b8 }
            r11 = 1
            r10.setDoOutput(r11)     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            java.lang.String r11 = "Content-Encoding"
            java.lang.String r2 = "gzip"
            r10.addRequestProperty(r11, r2)     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            int r11 = r1.length     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            r10.setFixedLengthStreamingMode(r11)     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            r10.connect()     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            java.io.OutputStream r11 = r10.getOutputStream()     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            r11.write(r1)     // Catch:{ IOException -> 0x00a8, all -> 0x00a2 }
            r11.close()     // Catch:{ IOException -> 0x00a8, all -> 0x00a2 }
            r9.zzb((java.net.HttpURLConnection) r10)     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            int r11 = r10.getResponseCode()     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            r1 = 200(0xc8, float:2.8E-43)
            if (r11 != r1) goto L_0x0093
            com.google.android.gms.internal.zzry r1 = r9.zzmA()     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            r1.zznP()     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
        L_0x0093:
            java.lang.String r1 = "POST status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            r9.zzb(r1, r2)     // Catch:{ IOException -> 0x00b3, all -> 0x00ae }
            if (r10 == 0) goto L_0x00a1
            r10.disconnect()
        L_0x00a1:
            return r11
        L_0x00a2:
            r0 = move-exception
            r8 = r11
            r11 = r10
            r10 = r0
            r0 = r8
            goto L_0x00d6
        L_0x00a8:
            r0 = move-exception
            r8 = r11
            r11 = r10
            r10 = r0
            r0 = r8
            goto L_0x00bd
        L_0x00ae:
            r11 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto L_0x00d6
        L_0x00b3:
            r11 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto L_0x00bd
        L_0x00b8:
            r10 = move-exception
            r11 = r0
            goto L_0x00d6
        L_0x00bb:
            r10 = move-exception
            r11 = r0
        L_0x00bd:
            java.lang.String r1 = "Network compressed POST connection error"
            r9.zzd(r1, r10)     // Catch:{ all -> 0x00d5 }
            if (r0 == 0) goto L_0x00ce
            r0.close()     // Catch:{ IOException -> 0x00c8 }
            goto L_0x00ce
        L_0x00c8:
            r10 = move-exception
            java.lang.String r0 = "Error closing http compressed post connection output stream"
            r9.zze(r0, r10)
        L_0x00ce:
            if (r11 == 0) goto L_0x00d3
            r11.disconnect()
        L_0x00d3:
            r10 = 0
            return r10
        L_0x00d5:
            r10 = move-exception
        L_0x00d6:
            if (r0 == 0) goto L_0x00e2
            r0.close()     // Catch:{ IOException -> 0x00dc }
            goto L_0x00e2
        L_0x00dc:
            r0 = move-exception
            java.lang.String r1 = "Error closing http compressed post connection output stream"
            r9.zze(r1, r0)
        L_0x00e2:
            if (r11 == 0) goto L_0x00e7
            r11.disconnect()
        L_0x00e7:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztf.zzb(java.net.URL, byte[]):int");
    }

    private URL zzb(zzsz zzsz, String str) {
        String valueOf;
        String valueOf2;
        StringBuilder sb;
        if (zzsz.zzpS()) {
            valueOf = String.valueOf(zznT().zzpj());
            valueOf2 = String.valueOf(zznT().zzpl());
            sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length() + String.valueOf(str).length());
        } else {
            valueOf = String.valueOf(zznT().zzpk());
            valueOf2 = String.valueOf(zznT().zzpl());
            sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length() + String.valueOf(str).length());
        }
        sb.append(valueOf);
        sb.append(valueOf2);
        sb.append("?");
        sb.append(str);
        try {
            return new URL(sb.toString());
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0022 A[SYNTHETIC, Splitter:B:17:0x0022] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzb(java.net.HttpURLConnection r3) throws java.io.IOException {
        /*
            r2 = this;
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ all -> 0x001e }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x001c }
        L_0x0008:
            int r1 = r3.read(r0)     // Catch:{ all -> 0x001c }
            if (r1 <= 0) goto L_0x000f
            goto L_0x0008
        L_0x000f:
            if (r3 == 0) goto L_0x001b
            r3.close()     // Catch:{ IOException -> 0x0015 }
            return
        L_0x0015:
            r3 = move-exception
            java.lang.String r0 = "Error closing http connection input stream"
            r2.zze(r0, r3)
        L_0x001b:
            return
        L_0x001c:
            r0 = move-exception
            goto L_0x0020
        L_0x001e:
            r0 = move-exception
            r3 = 0
        L_0x0020:
            if (r3 == 0) goto L_0x002c
            r3.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x002c
        L_0x0026:
            r3 = move-exception
            java.lang.String r1 = "Error closing http connection input stream"
            r2.zze(r1, r3)
        L_0x002c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztf.zzb(java.net.HttpURLConnection):void");
    }

    private boolean zzg(zzsz zzsz) {
        String str;
        zztd zznS;
        String str2;
        zzac.zzw(zzsz);
        String zza2 = zza(zzsz, !zzsz.zzpS());
        if (zza2 == null) {
            zznS = zznS();
            str2 = "Error formatting hit for upload";
        } else {
            if (zza2.length() <= zznT().zzoY()) {
                URL zzb = zzb(zzsz, zza2);
                if (zzb != null) {
                    return zzb(zzb) == 200;
                }
                str = "Failed to build collect GET endpoint url";
            } else {
                String zza3 = zza(zzsz, false);
                if (zza3 == null) {
                    zznS = zznS();
                    str2 = "Error formatting hit for POST upload";
                } else {
                    byte[] bytes = zza3.getBytes();
                    if (bytes.length > zznT().zzpa()) {
                        zznS = zznS();
                        str2 = "Hit payload exceeds size limit";
                    } else {
                        URL zzh = zzh(zzsz);
                        if (zzh != null) {
                            return zza(zzh, bytes) == 200;
                        }
                        str = "Failed to build collect POST endpoint url";
                    }
                }
            }
            zzbT(str);
            return false;
        }
        zznS.zza(zzsz, str2);
        return true;
    }

    private URL zzh(zzsz zzsz) {
        String str;
        String valueOf;
        String valueOf2;
        String str2;
        if (zzsz.zzpS()) {
            valueOf = String.valueOf(zznT().zzpj());
            valueOf2 = String.valueOf(zznT().zzpl());
            if (valueOf2.length() == 0) {
                str2 = new String(valueOf);
                str = str2;
                return new URL(str);
            }
        } else {
            valueOf = String.valueOf(zznT().zzpk());
            valueOf2 = String.valueOf(zznT().zzpl());
            if (valueOf2.length() == 0) {
                str2 = new String(valueOf);
                str = str2;
                return new URL(str);
            }
        }
        str = valueOf.concat(valueOf2);
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private String zzi(zzsz zzsz) {
        return String.valueOf(zzsz.zzpP());
    }

    private static byte[] zzk(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private URL zzqb() {
        String valueOf = String.valueOf(zznT().zzpj());
        String valueOf2 = String.valueOf(zznT().zzpm());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String zza(zzsz zzsz, boolean z) {
        zzac.zzw(zzsz);
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : zzsz.zzfE().entrySet()) {
                String str = (String) next.getKey();
                if (!"ht".equals(str)) {
                    if (!"qt".equals(str)) {
                        if (!"AppUID".equals(str)) {
                            if (!"z".equals(str)) {
                                if (!"_gmsv".equals(str)) {
                                    zza(sb, str, (String) next.getValue());
                                }
                            }
                        }
                    }
                }
            }
            zza(sb, "ht", String.valueOf(zzsz.zzpQ()));
            zza(sb, "qt", String.valueOf(zznR().currentTimeMillis() - zzsz.zzpQ()));
            if (z) {
                long zzpT = zzsz.zzpT();
                zza(sb, "z", zzpT != 0 ? String.valueOf(zzpT) : zzi(zzsz));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public List<Long> zza(List<zzsz> list, boolean z) {
        zzac.zzax(!list.isEmpty());
        zza("Uploading batched hits. compression, count", Boolean.valueOf(z), Integer.valueOf(list.size()));
        zza zza2 = new zza();
        ArrayList arrayList = new ArrayList();
        for (zzsz next : list) {
            if (!zza2.zzj(next)) {
                break;
            }
            arrayList.add(Long.valueOf(next.zzpP()));
        }
        if (zza2.zzqd() == 0) {
            return arrayList;
        }
        URL zzqb = zzqb();
        if (zzqb == null) {
            zzbT("Failed to build batching endpoint url");
            return Collections.emptyList();
        }
        int zzb = z ? zzb(zzqb, zza2.getPayload()) : zza(zzqb, zza2.getPayload());
        if (200 == zzb) {
            zza("Batched upload completed. Hits batched", Integer.valueOf(zza2.zzqd()));
            return arrayList;
        }
        zza("Network error uploading hits. status code", Integer.valueOf(zzb));
        if (zznT().zzpp().contains(Integer.valueOf(zzb))) {
            zzbS("Server instructed the client to stop batching");
            this.zzagy.start();
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public HttpURLConnection zzc(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain http connection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setConnectTimeout(zznT().zzpw());
        httpURLConnection.setReadTimeout(zznT().zzpx());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestProperty("User-Agent", this.zzIA);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        zza("Network initialized. User agent", this.zzIA);
    }

    public boolean zzqa() {
        NetworkInfo networkInfo;
        zzmR();
        zzob();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        zzbP("No network connectivity");
        return false;
    }

    public List<Long> zzt(List<zzsz> list) {
        boolean z;
        zzmR();
        zzob();
        zzac.zzw(list);
        boolean z2 = false;
        if (zznT().zzpp().isEmpty() || !this.zzagy.zzA(zznT().zzpi() * 1000)) {
            z = false;
        } else {
            z = zznT().zzpn() != zzsj.NONE;
            if (zznT().zzpo() == zzsm.GZIP) {
                z2 = true;
            }
        }
        return z ? zza(list, z2) : zzu(list);
    }

    /* access modifiers changed from: package-private */
    public List<Long> zzu(List<zzsz> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (zzsz next : list) {
            if (zzg(next)) {
                arrayList.add(Long.valueOf(next.zzpP()));
                if (arrayList.size() >= zznT().zzpg()) {
                    break;
                }
            } else {
                return arrayList;
            }
        }
        return arrayList;
    }
}
