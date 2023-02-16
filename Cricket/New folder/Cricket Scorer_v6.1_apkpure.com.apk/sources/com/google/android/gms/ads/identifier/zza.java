package com.google.android.gms.ads.identifier;

import android.support.annotation.WorkerThread;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class zza {
    @WorkerThread
    public void zzu(String str) {
        StringBuilder sb;
        String str2;
        String str3;
        String str4;
        Exception exc;
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                StringBuilder sb2 = new StringBuilder(65 + String.valueOf(str).length());
                sb2.append("Received non-success response code ");
                sb2.append(responseCode);
                sb2.append(" from pinging URL: ");
                sb2.append(str);
                Log.w("HttpUrlPinger", sb2.toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            str4 = "HttpUrlPinger";
            str3 = String.valueOf(e.getMessage());
            sb = new StringBuilder(32 + String.valueOf(str).length() + String.valueOf(str3).length());
            str2 = "Error while parsing ping URL: ";
            exc = e;
            sb.append(str2);
            sb.append(str);
            sb.append(". ");
            sb.append(str3);
            Log.w(str4, sb.toString(), exc);
        } catch (IOException | RuntimeException e2) {
            str4 = "HttpUrlPinger";
            str3 = String.valueOf(e2.getMessage());
            sb = new StringBuilder(27 + String.valueOf(str).length() + String.valueOf(str3).length());
            str2 = "Error while pinging URL: ";
            exc = e2;
            sb.append(str2);
            sb.append(str);
            sb.append(". ");
            sb.append(str3);
            Log.w(str4, sb.toString(), exc);
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
    }
}
