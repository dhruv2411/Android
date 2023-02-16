package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import android.util.LogPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class zzd implements zzi {
    private static final Uri zzabS;
    private final LogPrinter zzabT = new LogPrinter(4, "GA/LogCatTransport");

    static {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority("local");
        zzabS = builder.build();
    }

    public void zzb(zze zze) {
        ArrayList<zzf> arrayList = new ArrayList<>(zze.zzmD());
        Collections.sort(arrayList, new Comparator<zzf>(this) {
            /* renamed from: zza */
            public int compare(zzf zzf, zzf zzf2) {
                return zzf.getClass().getCanonicalName().compareTo(zzf2.getClass().getCanonicalName());
            }
        });
        StringBuilder sb = new StringBuilder();
        for (zzf obj : arrayList) {
            String obj2 = obj.toString();
            if (!TextUtils.isEmpty(obj2)) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(obj2);
            }
        }
        this.zzabT.println(sb.toString());
    }

    public Uri zzmr() {
        return zzabS;
    }
}
