package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzrk;
import com.google.android.gms.internal.zzrl;
import com.google.android.gms.internal.zzrm;
import com.google.android.gms.internal.zzrn;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.internal.zzrp;
import com.google.android.gms.internal.zzrq;
import com.google.android.gms.internal.zzrr;
import com.google.android.gms.internal.zzrs;
import com.google.android.gms.internal.zzrt;
import com.google.android.gms.internal.zzru;
import com.google.android.gms.internal.zzrv;
import com.google.android.gms.internal.zzrw;
import com.google.android.gms.internal.zzrz;
import com.google.android.gms.internal.zzsb;
import com.google.android.gms.internal.zzsc;
import com.google.android.gms.internal.zzse;
import com.google.android.gms.internal.zzsz;
import com.google.android.gms.internal.zztm;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.itextpdf.text.html.HtmlTags;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class zzb extends zzrz implements zzi {
    private static DecimalFormat zzabr;
    private final zzsc zzabn;
    private final String zzabs;
    private final Uri zzabt;
    private final boolean zzabu;
    private final boolean zzabv;

    public zzb(zzsc zzsc, String str) {
        this(zzsc, str, true, false);
    }

    public zzb(zzsc zzsc, String str, boolean z, boolean z2) {
        super(zzsc);
        zzac.zzdr(str);
        this.zzabn = zzsc;
        this.zzabs = str;
        this.zzabu = z;
        this.zzabv = z2;
        this.zzabt = zzbq(this.zzabs);
    }

    private static String zzR(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append((String) next.getKey());
            sb.append("=");
            sb.append((String) next.getValue());
        }
        return sb.toString();
    }

    private static void zza(Map<String, String> map, String str, double d) {
        if (d != 0.0d) {
            map.put(str, zzb(d));
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i > 0 && i2 > 0) {
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append("x");
            sb.append(i2);
            map.put(str, sb.toString());
        }
    }

    private static void zza(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    static String zzb(double d) {
        if (zzabr == null) {
            zzabr = new DecimalFormat("0.######");
        }
        return zzabr.format(d);
    }

    private static void zzb(Map<String, String> map, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    static Uri zzbq(String str) {
        zzac.zzdr(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    public static Map<String, String> zzc(zze zze) {
        HashMap hashMap = new HashMap();
        zzro zzro = (zzro) zze.zza(zzro.class);
        if (zzro != null) {
            for (Map.Entry next : zzro.zznj().entrySet()) {
                String zzi = zzi(next.getValue());
                if (zzi != null) {
                    hashMap.put((String) next.getKey(), zzi);
                }
            }
        }
        zzrt zzrt = (zzrt) zze.zza(zzrt.class);
        if (zzrt != null) {
            zzb(hashMap, "t", zzrt.zznu());
            zzb(hashMap, "cid", zzrt.zzmy());
            zzb(hashMap, "uid", zzrt.getUserId());
            zzb(hashMap, "sc", zzrt.zznx());
            zza((Map<String, String>) hashMap, "sf", zzrt.zznz());
            zza((Map<String, String>) hashMap, "ni", zzrt.zzny());
            zzb(hashMap, "adid", zzrt.zznv());
            zza((Map<String, String>) hashMap, "ate", zzrt.zznw());
        }
        zzru zzru = (zzru) zze.zza(zzru.class);
        if (zzru != null) {
            zzb(hashMap, "cd", zzru.zznB());
            zza((Map<String, String>) hashMap, "a", (double) zzru.zznC());
            zzb(hashMap, "dr", zzru.zznD());
        }
        zzrr zzrr = (zzrr) zze.zza(zzrr.class);
        if (zzrr != null) {
            zzb(hashMap, "ec", zzrr.getCategory());
            zzb(hashMap, "ea", zzrr.getAction());
            zzb(hashMap, "el", zzrr.getLabel());
            zza((Map<String, String>) hashMap, "ev", (double) zzrr.getValue());
        }
        zzrl zzrl = (zzrl) zze.zza(zzrl.class);
        if (zzrl != null) {
            zzb(hashMap, "cn", zzrl.getName());
            zzb(hashMap, "cs", zzrl.getSource());
            zzb(hashMap, "cm", zzrl.zznb());
            zzb(hashMap, "ck", zzrl.zznc());
            zzb(hashMap, "cc", zzrl.getContent());
            zzb(hashMap, "ci", zzrl.getId());
            zzb(hashMap, "anid", zzrl.zznd());
            zzb(hashMap, "gclid", zzrl.zzne());
            zzb(hashMap, "dclid", zzrl.zznf());
            zzb(hashMap, FirebaseAnalytics.Param.ACLID, zzrl.zzng());
        }
        zzrs zzrs = (zzrs) zze.zza(zzrs.class);
        if (zzrs != null) {
            zzb(hashMap, "exd", zzrs.getDescription());
            zza((Map<String, String>) hashMap, "exf", zzrs.zznt());
        }
        zzrv zzrv = (zzrv) zze.zza(zzrv.class);
        if (zzrv != null) {
            zzb(hashMap, "sn", zzrv.zznE());
            zzb(hashMap, "sa", zzrv.getAction());
            zzb(hashMap, "st", zzrv.getTarget());
        }
        zzrw zzrw = (zzrw) zze.zza(zzrw.class);
        if (zzrw != null) {
            zzb(hashMap, "utv", zzrw.zznF());
            zza((Map<String, String>) hashMap, "utt", (double) zzrw.getTimeInMillis());
            zzb(hashMap, "utc", zzrw.getCategory());
            zzb(hashMap, "utl", zzrw.getLabel());
        }
        zzrm zzrm = (zzrm) zze.zza(zzrm.class);
        if (zzrm != null) {
            for (Map.Entry next2 : zzrm.zznh().entrySet()) {
                String zzao = zzc.zzao(((Integer) next2.getKey()).intValue());
                if (!TextUtils.isEmpty(zzao)) {
                    hashMap.put(zzao, (String) next2.getValue());
                }
            }
        }
        zzrn zzrn = (zzrn) zze.zza(zzrn.class);
        if (zzrn != null) {
            for (Map.Entry next3 : zzrn.zzni().entrySet()) {
                String zzaq = zzc.zzaq(((Integer) next3.getKey()).intValue());
                if (!TextUtils.isEmpty(zzaq)) {
                    hashMap.put(zzaq, zzb(((Double) next3.getValue()).doubleValue()));
                }
            }
        }
        zzrq zzrq = (zzrq) zze.zza(zzrq.class);
        if (zzrq != null) {
            ProductAction zznp = zzrq.zznp();
            if (zznp != null) {
                for (Map.Entry next4 : zznp.build().entrySet()) {
                    hashMap.put(((String) next4.getKey()).startsWith("&") ? ((String) next4.getKey()).substring(1) : (String) next4.getKey(), (String) next4.getValue());
                }
            }
            int i = 1;
            for (Promotion zzbM : zzrq.zzns()) {
                hashMap.putAll(zzbM.zzbM(zzc.zzau(i)));
                i++;
            }
            int i2 = 1;
            for (Product zzbM2 : zzrq.zznq()) {
                hashMap.putAll(zzbM2.zzbM(zzc.zzas(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry next5 : zzrq.zznr().entrySet()) {
                String zzax = zzc.zzax(i3);
                int i4 = 1;
                for (Product product : (List) next5.getValue()) {
                    String valueOf = String.valueOf(zzax);
                    String valueOf2 = String.valueOf(zzc.zzav(i4));
                    hashMap.putAll(product.zzbM(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty((CharSequence) next5.getKey())) {
                    String valueOf3 = String.valueOf(zzax);
                    String valueOf4 = String.valueOf("nm");
                    hashMap.put(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), (String) next5.getKey());
                }
                i3++;
            }
        }
        zzrp zzrp = (zzrp) zze.zza(zzrp.class);
        if (zzrp != null) {
            zzb(hashMap, HtmlTags.UL, zzrp.getLanguage());
            zza((Map<String, String>) hashMap, "sd", (double) zzrp.zznk());
            zza(hashMap, "sr", zzrp.zznl(), zzrp.zznm());
            zza(hashMap, "vp", zzrp.zznn(), zzrp.zzno());
        }
        zzrk zzrk = (zzrk) zze.zza(zzrk.class);
        if (zzrk != null) {
            zzb(hashMap, "an", zzrk.zzmY());
            zzb(hashMap, "aid", zzrk.zzke());
            zzb(hashMap, "aiid", zzrk.zzna());
            zzb(hashMap, "av", zzrk.zzmZ());
        }
        return hashMap;
    }

    private static String zzi(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            return null;
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.doubleValue() != 0.0d) {
                return zzb(d.doubleValue());
            }
            return null;
        } else if (!(obj instanceof Boolean)) {
            return String.valueOf(obj);
        } else {
            if (obj != Boolean.FALSE) {
                return "1";
            }
            return null;
        }
    }

    public void zzb(zze zze) {
        zzac.zzw(zze);
        zzac.zzb(zze.zzmH(), (Object) "Can't deliver not submitted measurement");
        zzac.zzdk("deliver should be called on worker thread");
        zze zzmC = zze.zzmC();
        zzrt zzrt = (zzrt) zzmC.zzb(zzrt.class);
        if (TextUtils.isEmpty(zzrt.zznu())) {
            zznS().zzg(zzc(zzmC), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(zzrt.zzmy())) {
            zznS().zzg(zzc(zzmC), "Ignoring measurement without client id");
        } else if (!this.zzabn.zzof().getAppOptOut()) {
            double zznz = zzrt.zznz();
            if (zztm.zza(zznz, zzrt.zzmy())) {
                zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(zznz));
                return;
            }
            Map<String, String> zzc = zzc(zzmC);
            zzc.put("v", "1");
            zzc.put("_v", zzsb.zzadQ);
            zzc.put("tid", this.zzabs);
            if (this.zzabn.zzof().isDryRunEnabled()) {
                zzc("Dry run is enabled. GoogleAnalytics would have sent", zzR(zzc));
                return;
            }
            HashMap hashMap = new HashMap();
            zztm.zzc(hashMap, "uid", zzrt.getUserId());
            zzrk zzrk = (zzrk) zze.zza(zzrk.class);
            if (zzrk != null) {
                zztm.zzc(hashMap, "an", zzrk.zzmY());
                zztm.zzc(hashMap, "aid", zzrk.zzke());
                zztm.zzc(hashMap, "av", zzrk.zzmZ());
                zztm.zzc(hashMap, "aiid", zzrk.zzna());
            }
            zzc.put("_s", String.valueOf(zzmA().zza(new zzse(0, zzrt.zzmy(), this.zzabs, !TextUtils.isEmpty(zzrt.zznv()), 0, hashMap))));
            zzmA().zza(new zzsz(zznS(), zzc, zze.zzmF(), true));
        }
    }

    public Uri zzmr() {
        return this.zzabt;
    }
}
