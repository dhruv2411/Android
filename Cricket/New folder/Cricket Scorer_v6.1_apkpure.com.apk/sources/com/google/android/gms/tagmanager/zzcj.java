package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class zzcj {
    private static zzcj zzbHd;
    private volatile String zzbEU;
    private volatile zza zzbHe;
    private volatile String zzbHf;
    private volatile String zzbHg;

    enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzcj() {
        clear();
    }

    static zzcj zzRe() {
        zzcj zzcj;
        synchronized (zzcj.class) {
            if (zzbHd == null) {
                zzbHd = new zzcj();
            }
            zzcj = zzbHd;
        }
        return zzcj;
    }

    private String zzhn(String str) {
        return str.split("&")[0].split("=")[1];
    }

    private String zzw(Uri uri) {
        return uri.getQuery().replace("&gtm_debug=x", "");
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.zzbHe = zza.NONE;
        this.zzbHf = null;
        this.zzbEU = null;
        this.zzbHg = null;
    }

    /* access modifiers changed from: package-private */
    public String getContainerId() {
        return this.zzbEU;
    }

    /* access modifiers changed from: package-private */
    public zza zzRf() {
        return this.zzbHe;
    }

    /* access modifiers changed from: package-private */
    public String zzRg() {
        return this.zzbHf;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean zzv(Uri uri) {
        try {
            String decode = URLDecoder.decode(uri.toString(), "UTF-8");
            if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                String valueOf = String.valueOf(decode);
                zzbo.v(valueOf.length() != 0 ? "Container preview url: ".concat(valueOf) : new String("Container preview url: "));
                this.zzbHe = decode.matches(".*?&gtm_debug=x$") ? zza.CONTAINER_DEBUG : zza.CONTAINER;
                this.zzbHg = zzw(uri);
                if (this.zzbHe == zza.CONTAINER || this.zzbHe == zza.CONTAINER_DEBUG) {
                    String valueOf2 = String.valueOf("/r?");
                    String valueOf3 = String.valueOf(this.zzbHg);
                    this.zzbHf = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
                }
                this.zzbEU = zzhn(this.zzbHg);
                return true;
            } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                String valueOf4 = String.valueOf(decode);
                zzbo.zzbh(valueOf4.length() != 0 ? "Invalid preview uri: ".concat(valueOf4) : new String("Invalid preview uri: "));
                return false;
            } else if (!zzhn(uri.getQuery()).equals(this.zzbEU)) {
                return false;
            } else {
                String valueOf5 = String.valueOf(this.zzbEU);
                zzbo.v(valueOf5.length() != 0 ? "Exit preview mode for container: ".concat(valueOf5) : new String("Exit preview mode for container: "));
                this.zzbHe = zza.NONE;
                this.zzbHf = null;
                return true;
            }
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }
}
