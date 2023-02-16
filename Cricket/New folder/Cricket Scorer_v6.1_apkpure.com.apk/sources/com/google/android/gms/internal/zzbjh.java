package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzbo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class zzbjh implements zzbji {
    private HttpURLConnection zzbMp;
    private InputStream zzbMq = null;

    zzbjh() {
    }

    private InputStream zzd(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            return httpURLConnection.getInputStream();
        }
        StringBuilder sb = new StringBuilder(25);
        sb.append("Bad response: ");
        sb.append(responseCode);
        String sb2 = sb.toString();
        if (responseCode == 404) {
            throw new FileNotFoundException(sb2);
        } else if (responseCode == 503) {
            throw new zzbjk(sb2);
        } else {
            throw new IOException(sb2);
        }
    }

    private void zze(HttpURLConnection httpURLConnection) {
        try {
            if (this.zzbMq != null) {
                this.zzbMq.close();
            }
        } catch (IOException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbo.zzb(valueOf.length() != 0 ? "HttpUrlConnectionNetworkClient: Error when closing http input stream: ".concat(valueOf) : new String("HttpUrlConnectionNetworkClient: Error when closing http input stream: "), e);
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public void close() {
        zze(this.zzbMp);
    }

    public InputStream zzhX(String str) throws IOException {
        this.zzbMp = zzhY(str);
        this.zzbMq = zzd(this.zzbMp);
        return this.zzbMq;
    }

    /* access modifiers changed from: package-private */
    public HttpURLConnection zzhY(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        return httpURLConnection;
    }
}
