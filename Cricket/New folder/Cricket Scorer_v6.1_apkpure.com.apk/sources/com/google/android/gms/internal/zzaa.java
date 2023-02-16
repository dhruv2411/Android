package com.google.android.gms.internal;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class zzaa implements zzz {
    private final zza zzaE;
    private final SSLSocketFactory zzaF;

    public interface zza {
        String zzh(String str);
    }

    public zzaa() {
        this((zza) null);
    }

    public zzaa(zza zza2) {
        this(zza2, (SSLSocketFactory) null);
    }

    public zzaa(zza zza2, SSLSocketFactory sSLSocketFactory) {
        this.zzaE = zza2;
        this.zzaF = sSLSocketFactory;
    }

    private HttpURLConnection zza(URL url, zzl<?> zzl) throws IOException {
        HttpURLConnection zza2 = zza(url);
        int zzp = zzl.zzp();
        zza2.setConnectTimeout(zzp);
        zza2.setReadTimeout(zzp);
        zza2.setUseCaches(false);
        zza2.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.zzaF != null) {
            ((HttpsURLConnection) zza2).setSSLSocketFactory(this.zzaF);
        }
        return zza2;
    }

    private static HttpEntity zza(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException unused) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        r2.setRequestMethod(r0);
        zzb(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r2.setRequestMethod(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void zza(java.net.HttpURLConnection r2, com.google.android.gms.internal.zzl<?> r3) throws java.io.IOException, com.google.android.gms.internal.zza {
        /*
            int r0 = r3.getMethod()
            switch(r0) {
                case -1: goto L_0x0030;
                case 0: goto L_0x002a;
                case 1: goto L_0x0021;
                case 2: goto L_0x001e;
                case 3: goto L_0x001b;
                case 4: goto L_0x0018;
                case 5: goto L_0x0015;
                case 6: goto L_0x0012;
                case 7: goto L_0x000f;
                default: goto L_0x0007;
            }
        L_0x0007:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "Unknown method type."
            r2.<init>(r3)
            throw r2
        L_0x000f:
            java.lang.String r0 = "PATCH"
            goto L_0x0023
        L_0x0012:
            java.lang.String r3 = "TRACE"
            goto L_0x002c
        L_0x0015:
            java.lang.String r3 = "OPTIONS"
            goto L_0x002c
        L_0x0018:
            java.lang.String r3 = "HEAD"
            goto L_0x002c
        L_0x001b:
            java.lang.String r3 = "DELETE"
            goto L_0x002c
        L_0x001e:
            java.lang.String r0 = "PUT"
            goto L_0x0023
        L_0x0021:
            java.lang.String r0 = "POST"
        L_0x0023:
            r2.setRequestMethod(r0)
            zzb(r2, r3)
            return
        L_0x002a:
            java.lang.String r3 = "GET"
        L_0x002c:
            r2.setRequestMethod(r3)
            return
        L_0x0030:
            byte[] r0 = r3.zzj()
            if (r0 == 0) goto L_0x0057
            r1 = 1
            r2.setDoOutput(r1)
            java.lang.String r1 = "POST"
            r2.setRequestMethod(r1)
            java.lang.String r1 = "Content-Type"
            java.lang.String r3 = r3.zzi()
            r2.addRequestProperty(r1, r3)
            java.io.DataOutputStream r3 = new java.io.DataOutputStream
            java.io.OutputStream r2 = r2.getOutputStream()
            r3.<init>(r2)
            r3.write(r0)
            r3.close()
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaa.zza(java.net.HttpURLConnection, com.google.android.gms.internal.zzl):void");
    }

    private static boolean zza(int i, int i2) {
        if (i != 4) {
            return ((100 <= i2 && i2 < 200) || i2 == 204 || i2 == 304) ? false : true;
        }
        return false;
    }

    private static void zzb(HttpURLConnection httpURLConnection, zzl<?> zzl) throws IOException, zza {
        byte[] zzm = zzl.zzm();
        if (zzm != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, zzl.zzl());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzm);
            dataOutputStream.close();
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection zza(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    public HttpResponse zza(zzl<?> zzl, Map<String, String> map) throws IOException, zza {
        String str;
        String url = zzl.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(zzl.getHeaders());
        hashMap.putAll(map);
        if (this.zzaE != null) {
            str = this.zzaE.zzh(url);
            if (str == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }
        } else {
            str = url;
        }
        HttpURLConnection zza2 = zza(new URL(str), zzl);
        for (String str2 : hashMap.keySet()) {
            zza2.addRequestProperty(str2, (String) hashMap.get(str2));
        }
        zza(zza2, zzl);
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (zza2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        BasicStatusLine basicStatusLine = new BasicStatusLine(protocolVersion, zza2.getResponseCode(), zza2.getResponseMessage());
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(basicStatusLine);
        if (zza(zzl.getMethod(), basicStatusLine.getStatusCode())) {
            basicHttpResponse.setEntity(zza(zza2));
        }
        for (Map.Entry entry : zza2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }
}
