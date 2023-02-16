package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.itextpdf.text.pdf.Barcode128;
import java.util.Map;

public class zztd extends zzsa {
    private static String zzags = "3";
    private static String zzagt = "01VDIWEA?";
    private static zztd zzagu;

    public zztd(zzsc zzsc) {
        super(zzsc);
    }

    public static zztd zzpW() {
        return zzagu;
    }

    public void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        String str2 = zzsw.zzafl.get();
        if (Log.isLoggable(str2, i)) {
            Log.println(i, str2, zzc(str, obj, obj2, obj3));
        }
        if (i >= 5) {
            zzb(i, str, obj, obj2, obj3);
        }
    }

    public void zza(zzsz zzsz, String str) {
        if (str == null) {
            str = "no reason provided";
        }
        String zzsz2 = zzsz != null ? zzsz.toString() : "no hit data";
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? "Discarding hit. ".concat(valueOf) : new String("Discarding hit. "), zzsz2);
    }

    public synchronized void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        zzac.zzw(str);
        if (i < 0) {
            i = 0;
        }
        if (i >= zzagt.length()) {
            i = zzagt.length() - 1;
        }
        char c = zznT().zzoW() ? 'C' : Barcode128.CODE_AB_TO_C;
        String str2 = zzags;
        char charAt = zzagt.charAt(i);
        String str3 = zzsb.VERSION;
        String valueOf = String.valueOf(zzc(str, zzm(obj), zzm(obj2), zzm(obj3)));
        StringBuilder sb = new StringBuilder(3 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(valueOf).length());
        sb.append(str2);
        sb.append(charAt);
        sb.append(c);
        sb.append(str3);
        sb.append(":");
        sb.append(valueOf);
        String sb2 = sb.toString();
        if (sb2.length() > 1024) {
            sb2 = sb2.substring(0, 1024);
        }
        zztg zzog = zznQ().zzog();
        if (zzog != null) {
            zzog.zzqj().zzcc(sb2);
        }
    }

    public void zzg(Map<String, String> map, String str) {
        String str2;
        if (str == null) {
            str = "no reason provided";
        }
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry next : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append((String) next.getKey());
                sb.append('=');
                sb.append((String) next.getValue());
            }
            str2 = sb.toString();
        } else {
            str2 = "no hit data";
        }
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? "Discarding hit. ".concat(valueOf) : new String("Discarding hit. "), str2);
    }

    /* access modifiers changed from: protected */
    public String zzm(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            obj = new Long((long) ((Integer) obj).intValue());
        }
        if (!(obj instanceof Long)) {
            return obj instanceof Boolean ? String.valueOf(obj) : obj instanceof Throwable ? obj.getClass().getCanonicalName() : "-";
        }
        Long l = (Long) obj;
        if (Math.abs(l.longValue()) < 100) {
            return String.valueOf(obj);
        }
        String str = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
        String valueOf = String.valueOf(Math.abs(l.longValue()));
        return str + Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1))) + "..." + str + Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        synchronized (zztd.class) {
            zzagu = this;
        }
    }
}
