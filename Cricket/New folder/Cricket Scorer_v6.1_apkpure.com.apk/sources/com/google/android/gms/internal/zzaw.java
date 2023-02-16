package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

public class zzaw {
    private static final String[] zzqc = {"/aclk", "/pcs/click"};
    private String zzpY = "googleads.g.doubleclick.net";
    private String zzpZ = "/pagead/ads";
    private String zzqa = "ad.doubleclick.net";
    private String[] zzqb = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private zzar zzqd;

    public zzaw(zzar zzar) {
        this.zzqd = zzar;
    }

    private Uri zza(Uri uri, Context context, String str, boolean z, View view) throws zzax {
        try {
            boolean zzb = zzb(uri);
            if (zzb) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzax("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzax("Query parameter already exists: ms");
            }
            String zza = z ? this.zzqd.zza(context, str, view) : this.zzqd.zzb(context);
            return zzb ? zzb(uri, "dc_ms", zza) : zza(uri, "ms", zza);
        } catch (UnsupportedOperationException unused) {
            throw new zzax("Provided Uri is not in a valid state");
        }
    }

    private Uri zza(Uri uri, String str, String str2) throws UnsupportedOperationException {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf("&adurl");
        if (indexOf == -1) {
            indexOf = uri2.indexOf("?adurl");
        }
        if (indexOf == -1) {
            return uri.buildUpon().appendQueryParameter(str, str2).build();
        }
        int i = indexOf + 1;
        return Uri.parse(uri2.substring(0, i) + str + "=" + str2 + "&" + uri2.substring(i));
    }

    private Uri zzb(Uri uri, String str, String str2) {
        String str3;
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf(";adurl");
        if (indexOf != -1) {
            int i = indexOf + 1;
            str3 = uri2.substring(0, i) + str + "=" + str2 + ";" + uri2.substring(i);
        } else {
            String encodedPath = uri.getEncodedPath();
            int indexOf2 = uri2.indexOf(encodedPath);
            str3 = uri2.substring(0, encodedPath.length() + indexOf2) + ";" + str + "=" + str2 + ";" + uri2.substring(indexOf2 + encodedPath.length());
        }
        return Uri.parse(str3);
    }

    public zzar zzT() {
        return this.zzqd;
    }

    public Uri zza(Uri uri, Context context) throws zzax {
        return zza(uri, context, (String) null, false, (View) null);
    }

    public Uri zza(Uri uri, Context context, View view) throws zzax {
        try {
            return zza(uri, context, uri.getQueryParameter("ai"), true, view);
        } catch (UnsupportedOperationException unused) {
            throw new zzax("Provided Uri is not in a valid state");
        }
    }

    public void zza(MotionEvent motionEvent) {
        this.zzqd.zza(motionEvent);
    }

    public boolean zza(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.zzpY) && uri.getPath().equals(this.zzpZ);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    @Deprecated
    public Uri zzb(Uri uri, Context context) throws zzax {
        return zza(uri, context, (View) null);
    }

    public void zzb(String str, String str2) {
        this.zzpY = str;
        this.zzpZ = str2;
    }

    public boolean zzb(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.zzqa);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean zzc(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            String host = uri.getHost();
            for (String endsWith : this.zzqb) {
                if (host.endsWith(endsWith)) {
                    return true;
                }
            }
        } catch (NullPointerException unused) {
        }
        return false;
    }

    public boolean zzd(Uri uri) {
        if (zzc(uri)) {
            for (String endsWith : zzqc) {
                if (uri.getPath().endsWith(endsWith)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void zzm(String str) {
        this.zzqb = str.split(",");
    }
}
