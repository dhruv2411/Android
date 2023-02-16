package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzj extends zzdj {
    private static final String ID = zzah.ARBITRARY_PIXEL.toString();
    private static final String URL = zzai.URL.toString();
    private static final String zzbEO = zzai.ADDITIONAL_PARAMS.toString();
    private static final String zzbEP = zzai.UNREPEATABLE.toString();
    static final String zzbEQ;
    private static final Set<String> zzbER = new HashSet();
    private final Context mContext;
    private final zza zzbES;

    public interface zza {
        zzat zzQc();
    }

    static {
        String str = ID;
        StringBuilder sb = new StringBuilder(17 + String.valueOf(str).length());
        sb.append("gtm_");
        sb.append(str);
        sb.append("_unrepeatable");
        zzbEQ = sb.toString();
    }

    public zzj(final Context context) {
        this(context, new zza() {
            public zzat zzQc() {
                return zzaa.zzbT(context);
            }
        });
    }

    zzj(Context context, zza zza2) {
        super(ID, URL);
        this.zzbES = zza2;
        this.mContext = context;
    }

    private synchronized boolean zzgO(String str) {
        if (zzgQ(str)) {
            return true;
        }
        if (!zzgP(str)) {
            return false;
        }
        zzbER.add(str);
        return true;
    }

    public void zzab(Map<String, zzak.zza> map) {
        String zze = map.get(zzbEP) != null ? zzdl.zze(map.get(zzbEP)) : null;
        if (zze == null || !zzgO(zze)) {
            Uri.Builder buildUpon = Uri.parse(zzdl.zze(map.get(URL))).buildUpon();
            zzak.zza zza2 = map.get(zzbEO);
            if (zza2 != null) {
                Object zzj = zzdl.zzj(zza2);
                if (!(zzj instanceof List)) {
                    String valueOf = String.valueOf(buildUpon.build().toString());
                    zzbo.e(valueOf.length() != 0 ? "ArbitraryPixel: additional params not a list: not sending partial hit: ".concat(valueOf) : new String("ArbitraryPixel: additional params not a list: not sending partial hit: "));
                    return;
                }
                for (Object next : (List) zzj) {
                    if (!(next instanceof Map)) {
                        String valueOf2 = String.valueOf(buildUpon.build().toString());
                        zzbo.e(valueOf2.length() != 0 ? "ArbitraryPixel: additional params contains non-map: not sending partial hit: ".concat(valueOf2) : new String("ArbitraryPixel: additional params contains non-map: not sending partial hit: "));
                        return;
                    }
                    for (Map.Entry entry : ((Map) next).entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            String uri = buildUpon.build().toString();
            this.zzbES.zzQc().zzhf(uri);
            String valueOf3 = String.valueOf(uri);
            zzbo.v(valueOf3.length() != 0 ? "ArbitraryPixel: url = ".concat(valueOf3) : new String("ArbitraryPixel: url = "));
            if (zze != null) {
                synchronized (zzj.class) {
                    zzbER.add(zze);
                    zzdd.zzd(this.mContext, zzbEQ, zze, PdfBoolean.TRUE);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzgP(String str) {
        return this.mContext.getSharedPreferences(zzbEQ, 0).contains(str);
    }

    /* access modifiers changed from: package-private */
    public boolean zzgQ(String str) {
        return zzbER.contains(str);
    }
}
