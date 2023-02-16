package com.google.android.gms.internal;

import com.google.android.gms.internal.zzb;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class zzu implements zzg {
    protected static final boolean DEBUG = zzt.DEBUG;
    private static int zzan = 3000;
    private static int zzao = 4096;
    protected final zzz zzap;
    protected final zzv zzaq;

    public zzu(zzz zzz) {
        this(zzz, new zzv(zzao));
    }

    public zzu(zzz zzz, zzv zzv) {
        this.zzap = zzz;
        this.zzaq = zzv;
    }

    protected static Map<String, String> zza(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }

    private void zza(long j, zzl<?> zzl, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > ((long) zzan)) {
            Object[] objArr = new Object[5];
            objArr[0] = zzl;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(zzl.zzq().zzd());
            zzt.zzb("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void zza(String str, zzl<?> zzl, zzs zzs) throws zzs {
        zzp zzq = zzl.zzq();
        int zzp = zzl.zzp();
        try {
            zzq.zza(zzs);
            zzl.zzc(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(zzp)}));
        } catch (zzs e) {
            zzl.zzc(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(zzp)}));
            throw e;
        }
    }

    private void zza(Map<String, String> map, zzb.zza zza) {
        if (zza != null) {
            if (zza.zza != null) {
                map.put(HttpRequest.HEADER_IF_NONE_MATCH, zza.zza);
            }
            if (zza.zzc > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(zza.zzc)));
            }
        }
    }

    private byte[] zza(HttpEntity httpEntity) throws IOException, zzq {
        zzab zzab = new zzab(this.zzaq, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new zzq();
            }
            byte[] zzb = this.zzaq.zzb(1024);
            while (true) {
                try {
                    int read = content.read(zzb);
                    if (read == -1) {
                        break;
                    }
                    zzab.write(zzb, 0, read);
                } catch (Throwable th) {
                    th = th;
                    bArr = zzb;
                    try {
                        httpEntity.consumeContent();
                    } catch (IOException unused) {
                        zzt.zza("Error occured when calling consumingContent", new Object[0]);
                    }
                    this.zzaq.zza(bArr);
                    zzab.close();
                    throw th;
                }
            }
            byte[] byteArray = zzab.toByteArray();
            try {
                httpEntity.consumeContent();
            } catch (IOException unused2) {
                zzt.zza("Error occured when calling consumingContent", new Object[0]);
            }
            this.zzaq.zza(zzb);
            zzab.close();
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            httpEntity.consumeContent();
            this.zzaq.zza(bArr);
            zzab.close();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006e, code lost:
        r1 = r0;
        r15 = null;
        r16 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00bb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00bc, code lost:
        r3 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00bd, code lost:
        r1 = r0;
        r16 = r3;
        r15 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ca, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00cb, code lost:
        r16 = r1;
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cf, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d0, code lost:
        r16 = r1;
        r12 = null;
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d4, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d7, code lost:
        r1 = r12.getStatusLine().getStatusCode();
        com.google.android.gms.internal.zzt.zzc("Unexpected response code %d for %s", java.lang.Integer.valueOf(r1), r23.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f4, code lost:
        if (r15 != null) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f6, code lost:
        r13 = new com.google.android.gms.internal.zzj(r1, r15, r16, false, android.os.SystemClock.elapsedRealtime() - r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0107, code lost:
        if (r1 == 401) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0110, code lost:
        if (r1 < 400) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x011b, code lost:
        throw new com.google.android.gms.internal.zzd(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x011e, code lost:
        if (r1 < 500) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0129, code lost:
        throw new com.google.android.gms.internal.zzq(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x012f, code lost:
        throw new com.google.android.gms.internal.zzq(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0130, code lost:
        zza("auth", r8, new com.google.android.gms.internal.zza(r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x013c, code lost:
        r1 = "network";
        r2 = new com.google.android.gms.internal.zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0149, code lost:
        throw new com.google.android.gms.internal.zzk(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x014a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0166, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r23.getUrl(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0167, code lost:
        r1 = "connection";
        r2 = new com.google.android.gms.internal.zzr();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x016f, code lost:
        r1 = "socket";
        r2 = new com.google.android.gms.internal.zzr();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0176, code lost:
        zza(r1, r8, r2);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x014a A[ExcHandler: MalformedURLException (r0v0 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:78:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0144 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzj zza(com.google.android.gms.internal.zzl<?> r23) throws com.google.android.gms.internal.zzs {
        /*
            r22 = this;
            r7 = r22
            r8 = r23
            long r9 = android.os.SystemClock.elapsedRealtime()
        L_0x0008:
            java.util.Map r1 = java.util.Collections.emptyMap()
            r11 = 0
            r2 = 0
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00cf }
            r3.<init>()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00cf }
            com.google.android.gms.internal.zzb$zza r4 = r23.zzh()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00cf }
            r7.zza(r3, r4)     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00cf }
            com.google.android.gms.internal.zzz r4 = r7.zzap     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00cf }
            org.apache.http.HttpResponse r12 = r4.zza(r8, r3)     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00cf }
            org.apache.http.StatusLine r6 = r12.getStatusLine()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00ca }
            int r14 = r6.getStatusCode()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00ca }
            org.apache.http.Header[] r3 = r12.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00ca }
            java.util.Map r13 = zza((org.apache.http.Header[]) r3)     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00ca }
            r1 = 304(0x130, float:4.26E-43)
            if (r14 != r1) goto L_0x0074
            com.google.android.gms.internal.zzb$zza r1 = r23.zzh()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            if (r1 != 0) goto L_0x004f
            com.google.android.gms.internal.zzj r1 = new com.google.android.gms.internal.zzj     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            r16 = 304(0x130, float:4.26E-43)
            r17 = 0
            r19 = 1
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            long r20 = r3 - r9
            r15 = r1
            r18 = r13
            r15.<init>(r16, r17, r18, r19, r20)     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            return r1
        L_0x004f:
            java.util.Map<java.lang.String, java.lang.String> r3 = r1.zzf     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            r3.putAll(r13)     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            com.google.android.gms.internal.zzj r3 = new com.google.android.gms.internal.zzj     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            r15 = 304(0x130, float:4.26E-43)
            byte[] r4 = r1.data     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            java.util.Map<java.lang.String, java.lang.String> r1 = r1.zzf     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            r18 = 1
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            long r19 = r5 - r9
            r14 = r3
            r16 = r4
            r17 = r1
            r14.<init>(r15, r16, r17, r18, r19)     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            return r3
        L_0x006d:
            r0 = move-exception
            r1 = r0
            r15 = r2
            r16 = r13
            goto L_0x00d5
        L_0x0074:
            org.apache.http.HttpEntity r1 = r12.getEntity()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00c3 }
            if (r1 == 0) goto L_0x0083
            org.apache.http.HttpEntity r1 = r12.getEntity()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            byte[] r1 = r7.zza((org.apache.http.HttpEntity) r1)     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x006d }
            goto L_0x0085
        L_0x0083:
            byte[] r1 = new byte[r11]     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00c3 }
        L_0x0085:
            r20 = r1
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00bb }
            long r3 = r1 - r9
            r1 = r7
            r2 = r3
            r4 = r8
            r5 = r20
            r1.zza(r2, r4, r5, r6)     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00bb }
            r1 = 200(0xc8, float:2.8E-43)
            if (r14 < r1) goto L_0x00b2
            r1 = 299(0x12b, float:4.19E-43)
            if (r14 <= r1) goto L_0x009e
            goto L_0x00b2
        L_0x009e:
            com.google.android.gms.internal.zzj r1 = new com.google.android.gms.internal.zzj     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00bb }
            r17 = 0
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00bb }
            long r18 = r2 - r9
            r3 = r13
            r13 = r1
            r15 = r20
            r16 = r3
            r13.<init>(r14, r15, r16, r17, r18)     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00b9 }
            return r1
        L_0x00b2:
            r3 = r13
            java.io.IOException r1 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00b9 }
            r1.<init>()     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00b9 }
            throw r1     // Catch:{ SocketTimeoutException -> 0x016f, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014a, IOException -> 0x00b9 }
        L_0x00b9:
            r0 = move-exception
            goto L_0x00bd
        L_0x00bb:
            r0 = move-exception
            r3 = r13
        L_0x00bd:
            r1 = r0
            r16 = r3
            r15 = r20
            goto L_0x00d5
        L_0x00c3:
            r0 = move-exception
            r3 = r13
            r1 = r0
            r15 = r2
            r16 = r3
            goto L_0x00d5
        L_0x00ca:
            r0 = move-exception
            r16 = r1
            r15 = r2
            goto L_0x00d4
        L_0x00cf:
            r0 = move-exception
            r16 = r1
            r12 = r2
            r15 = r12
        L_0x00d4:
            r1 = r0
        L_0x00d5:
            if (r12 == 0) goto L_0x0144
            org.apache.http.StatusLine r1 = r12.getStatusLine()
            int r1 = r1.getStatusCode()
            java.lang.String r2 = "Unexpected response code %d for %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            r3[r11] = r4
            r4 = 1
            java.lang.String r5 = r23.getUrl()
            r3[r4] = r5
            com.google.android.gms.internal.zzt.zzc(r2, r3)
            if (r15 == 0) goto L_0x013c
            com.google.android.gms.internal.zzj r2 = new com.google.android.gms.internal.zzj
            r17 = 0
            long r3 = android.os.SystemClock.elapsedRealtime()
            long r18 = r3 - r9
            r13 = r2
            r14 = r1
            r13.<init>(r14, r15, r16, r17, r18)
            r3 = 401(0x191, float:5.62E-43)
            if (r1 == r3) goto L_0x0130
            r3 = 403(0x193, float:5.65E-43)
            if (r1 != r3) goto L_0x010e
            goto L_0x0130
        L_0x010e:
            r3 = 400(0x190, float:5.6E-43)
            if (r1 < r3) goto L_0x011c
            r3 = 499(0x1f3, float:6.99E-43)
            if (r1 > r3) goto L_0x011c
            com.google.android.gms.internal.zzd r1 = new com.google.android.gms.internal.zzd
            r1.<init>(r2)
            throw r1
        L_0x011c:
            r3 = 500(0x1f4, float:7.0E-43)
            if (r1 < r3) goto L_0x012a
            r3 = 599(0x257, float:8.4E-43)
            if (r1 > r3) goto L_0x012a
            com.google.android.gms.internal.zzq r1 = new com.google.android.gms.internal.zzq
            r1.<init>(r2)
            throw r1
        L_0x012a:
            com.google.android.gms.internal.zzq r1 = new com.google.android.gms.internal.zzq
            r1.<init>(r2)
            throw r1
        L_0x0130:
            java.lang.String r1 = "auth"
            com.google.android.gms.internal.zza r3 = new com.google.android.gms.internal.zza
            r3.<init>(r2)
            zza(r1, r8, r3)
            goto L_0x0008
        L_0x013c:
            java.lang.String r1 = "network"
            com.google.android.gms.internal.zzi r2 = new com.google.android.gms.internal.zzi
            r2.<init>()
            goto L_0x0176
        L_0x0144:
            com.google.android.gms.internal.zzk r2 = new com.google.android.gms.internal.zzk
            r2.<init>(r1)
            throw r2
        L_0x014a:
            r0 = move-exception
            r1 = r0
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Bad URL "
            r3.append(r4)
            java.lang.String r4 = r23.getUrl()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3, r1)
            throw r2
        L_0x0167:
            java.lang.String r1 = "connection"
            com.google.android.gms.internal.zzr r2 = new com.google.android.gms.internal.zzr
            r2.<init>()
            goto L_0x0176
        L_0x016f:
            java.lang.String r1 = "socket"
            com.google.android.gms.internal.zzr r2 = new com.google.android.gms.internal.zzr
            r2.<init>()
        L_0x0176:
            zza(r1, r8, r2)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzu.zza(com.google.android.gms.internal.zzl):com.google.android.gms.internal.zzj");
    }
}
