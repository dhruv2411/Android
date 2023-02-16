package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.internal.zzqe;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zzme
public class zzqg implements zzqe.zza {
    @Nullable
    private final String zzIA;

    public zzqg() {
        this((String) null);
    }

    public zzqg(@Nullable String str) {
        this.zzIA = str;
    }

    @WorkerThread
    public void zzu(String str) {
        StringBuilder sb;
        String str2;
        HttpURLConnection httpURLConnection;
        try {
            String valueOf = String.valueOf(str);
            zzqf.zzbf(valueOf.length() != 0 ? "Pinging URL: ".concat(valueOf) : new String("Pinging URL: "));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            zzel.zzeT().zza(true, httpURLConnection, this.zzIA);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                StringBuilder sb2 = new StringBuilder(65 + String.valueOf(str).length());
                sb2.append("Received non-success response code ");
                sb2.append(responseCode);
                sb2.append(" from pinging URL: ");
                sb2.append(str);
                zzqf.zzbh(sb2.toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            String valueOf2 = String.valueOf(e.getMessage());
            sb = new StringBuilder(32 + String.valueOf(str).length() + String.valueOf(valueOf2).length());
            sb.append("Error while parsing ping URL: ");
            sb.append(str);
            sb.append(". ");
            sb.append(valueOf2);
            zzqf.zzbh(sb.toString());
        } catch (IOException e2) {
            str2 = String.valueOf(e2.getMessage());
            sb = new StringBuilder(27 + String.valueOf(str).length() + String.valueOf(str2).length());
            sb.append("Error while pinging URL: ");
            sb.append(str);
            sb.append(". ");
            sb.append(str2);
            zzqf.zzbh(sb.toString());
        } catch (RuntimeException e3) {
            str2 = String.valueOf(e3.getMessage());
            sb = new StringBuilder(27 + String.valueOf(str).length() + String.valueOf(str2).length());
            sb.append("Error while pinging URL: ");
            sb.append(str);
            sb.append(". ");
            sb.append(str2);
            zzqf.zzbh(sb.toString());
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
    }
}
