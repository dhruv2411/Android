package com.facebook.ads.internal.p.b;

import android.text.TextUtils;
import android.util.Log;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class h implements n {
    public final String a;
    private HttpURLConnection b;
    private InputStream c;
    private volatile int d;
    private volatile String e;

    public h(h hVar) {
        this.d = Integer.MIN_VALUE;
        this.a = hVar.a;
        this.e = hVar.e;
        this.d = hVar.d;
    }

    public h(String str) {
        this(str, m.a(str));
    }

    public h(String str, String str2) {
        this.d = Integer.MIN_VALUE;
        this.a = (String) j.a(str);
        this.e = str2;
    }

    private int a(HttpURLConnection httpURLConnection, int i, int i2) {
        int contentLength = httpURLConnection.getContentLength();
        return i2 == 200 ? contentLength : i2 == 206 ? contentLength + i : this.d;
    }

    private HttpURLConnection a(int i, int i2) {
        String str;
        HttpURLConnection httpURLConnection;
        boolean z;
        String str2 = this.a;
        int i3 = 0;
        do {
            StringBuilder sb = new StringBuilder();
            sb.append("Open connection ");
            if (i > 0) {
                str = " with offset " + i;
            } else {
                str = "";
            }
            sb.append(str);
            sb.append(" to ");
            sb.append(str2);
            Log.d("ProxyCache", sb.toString());
            httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            if (i > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + i + "-");
            }
            if (i2 > 0) {
                httpURLConnection.setConnectTimeout(i2);
                httpURLConnection.setReadTimeout(i2);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                str2 = httpURLConnection.getHeaderField(HttpRequest.HEADER_LOCATION);
                i3++;
                httpURLConnection.disconnect();
            }
            if (i3 > 5) {
                throw new l("Too many redirects: " + i3);
            }
        } while (z);
        return httpURLConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
            r7 = this;
            java.lang.String r0 = "ProxyCache"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Read content info from "
            r1.append(r2)
            java.lang.String r2 = r7.a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
            r0 = 0
            r1 = 10000(0x2710, float:1.4013E-41)
            r2 = 0
            java.net.HttpURLConnection r0 = r7.a(r0, r1)     // Catch:{ IOException -> 0x0073, all -> 0x006e }
            int r1 = r0.getContentLength()     // Catch:{ IOException -> 0x0069, all -> 0x0064 }
            r7.d = r1     // Catch:{ IOException -> 0x0069, all -> 0x0064 }
            java.lang.String r1 = r0.getContentType()     // Catch:{ IOException -> 0x0069, all -> 0x0064 }
            r7.e = r1     // Catch:{ IOException -> 0x0069, all -> 0x0064 }
            java.io.InputStream r1 = r0.getInputStream()     // Catch:{ IOException -> 0x0069, all -> 0x0064 }
            java.lang.String r2 = "ProxyCache"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0062 }
            r3.<init>()     // Catch:{ IOException -> 0x0062 }
            java.lang.String r4 = "Content info for `"
            r3.append(r4)     // Catch:{ IOException -> 0x0062 }
            java.lang.String r4 = r7.a     // Catch:{ IOException -> 0x0062 }
            r3.append(r4)     // Catch:{ IOException -> 0x0062 }
            java.lang.String r4 = "`: mime: "
            r3.append(r4)     // Catch:{ IOException -> 0x0062 }
            java.lang.String r4 = r7.e     // Catch:{ IOException -> 0x0062 }
            r3.append(r4)     // Catch:{ IOException -> 0x0062 }
            java.lang.String r4 = ", content-length: "
            r3.append(r4)     // Catch:{ IOException -> 0x0062 }
            int r4 = r7.d     // Catch:{ IOException -> 0x0062 }
            r3.append(r4)     // Catch:{ IOException -> 0x0062 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0062 }
            android.util.Log.i(r2, r3)     // Catch:{ IOException -> 0x0062 }
            com.facebook.ads.internal.p.b.m.a((java.io.Closeable) r1)
            if (r0 == 0) goto L_0x0097
            goto L_0x0094
        L_0x0062:
            r2 = move-exception
            goto L_0x0077
        L_0x0064:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L_0x0099
        L_0x0069:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L_0x0077
        L_0x006e:
            r0 = move-exception
            r1 = r2
            r2 = r0
            r0 = r1
            goto L_0x0099
        L_0x0073:
            r0 = move-exception
            r1 = r2
            r2 = r0
            r0 = r1
        L_0x0077:
            java.lang.String r3 = "ProxyCache"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0098 }
            r4.<init>()     // Catch:{ all -> 0x0098 }
            java.lang.String r5 = "Error fetching info from "
            r4.append(r5)     // Catch:{ all -> 0x0098 }
            java.lang.String r5 = r7.a     // Catch:{ all -> 0x0098 }
            r4.append(r5)     // Catch:{ all -> 0x0098 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0098 }
            android.util.Log.e(r3, r4, r2)     // Catch:{ all -> 0x0098 }
            com.facebook.ads.internal.p.b.m.a((java.io.Closeable) r1)
            if (r0 == 0) goto L_0x0097
        L_0x0094:
            r0.disconnect()
        L_0x0097:
            return
        L_0x0098:
            r2 = move-exception
        L_0x0099:
            com.facebook.ads.internal.p.b.m.a((java.io.Closeable) r1)
            if (r0 == 0) goto L_0x00a1
            r0.disconnect()
        L_0x00a1:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.b.h.d():void");
    }

    public synchronized int a() {
        if (this.d == Integer.MIN_VALUE) {
            d();
        }
        return this.d;
    }

    public int a(byte[] bArr) {
        if (this.c == null) {
            throw new l("Error reading data from " + this.a + ": connection is absent!");
        }
        try {
            return this.c.read(bArr, 0, bArr.length);
        } catch (InterruptedIOException e2) {
            throw new i("Reading source " + this.a + " is interrupted", e2);
        } catch (IOException e3) {
            throw new l("Error reading data from " + this.a, e3);
        }
    }

    public void a(int i) {
        try {
            this.b = a(i, -1);
            this.e = this.b.getContentType();
            this.c = new BufferedInputStream(this.b.getInputStream(), 8192);
            this.d = a(this.b, i, this.b.getResponseCode());
        } catch (IOException e2) {
            throw new l("Error opening connection for " + this.a + " with offset " + i, e2);
        }
    }

    public void b() {
        if (this.b != null) {
            try {
                this.b.disconnect();
            } catch (NullPointerException e2) {
                throw new l("Error disconnecting HttpUrlConnection", e2);
            }
        }
    }

    public synchronized String c() {
        if (TextUtils.isEmpty(this.e)) {
            d();
        }
        return this.e;
    }

    public String toString() {
        return "HttpUrlSource{url='" + this.a + "}";
    }
}
