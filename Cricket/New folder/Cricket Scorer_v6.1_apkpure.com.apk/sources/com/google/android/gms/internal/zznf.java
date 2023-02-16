package com.google.android.gms.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public final class zznf {
    private int mOrientation = -1;
    private String zzGr;
    private boolean zzLa = false;
    private final zzmk zzLo;
    private List<String> zzQx;
    private String zzTR;
    private String zzTS;
    private List<String> zzTT;
    private String zzTU;
    private String zzTV;
    private String zzTW;
    private List<String> zzTX;
    private long zzTY = -1;
    private boolean zzTZ = false;
    private final long zzUa = -1;
    private long zzUb = -1;
    private boolean zzUc = false;
    private boolean zzUd = false;
    private boolean zzUe = false;
    private boolean zzUf = true;
    private boolean zzUg = true;
    private String zzUh = "";
    private boolean zzUi = false;
    private zzoo zzUj;
    private List<String> zzUk;
    private List<String> zzUl;
    private boolean zzUm = false;
    private zzmp zzUn;
    private boolean zzUo = false;
    private String zzUp;
    private List<String> zzUq;
    private boolean zzUr;
    private String zzUs;
    private zzor zzUt;
    private boolean zzUu;

    public zznf(zzmk zzmk, String str) {
        this.zzTS = str;
        this.zzLo = zzmk;
    }

    private void zzA(Map<String, List<String>> map) {
        List list = map.get("X-Afma-Use-HTTPS");
        if (list != null && !list.isEmpty()) {
            this.zzUe = Boolean.valueOf((String) list.get(0)).booleanValue();
        }
    }

    private void zzB(Map<String, List<String>> map) {
        List list = map.get("X-Afma-Content-Url-Opted-Out");
        if (list != null && !list.isEmpty()) {
            this.zzUf = Boolean.valueOf((String) list.get(0)).booleanValue();
        }
    }

    private void zzC(Map<String, List<String>> map) {
        List list = map.get("X-Afma-Content-Vertical-Opted-Out");
        if (list != null && !list.isEmpty()) {
            this.zzUg = Boolean.valueOf((String) list.get(0)).booleanValue();
        }
    }

    private void zzD(Map<String, List<String>> map) {
        List list = map.get("X-Afma-Gws-Query-Id");
        if (list != null && !list.isEmpty()) {
            this.zzUh = (String) list.get(0);
        }
    }

    private void zzE(Map<String, List<String>> map) {
        String zzc = zzc(map, "X-Afma-Fluid");
        if (zzc != null && zzc.equals("height")) {
            this.zzUi = true;
        }
    }

    private void zzF(Map<String, List<String>> map) {
        this.zzLa = "native_express".equals(zzc(map, "X-Afma-Ad-Format"));
    }

    private void zzG(Map<String, List<String>> map) {
        this.zzUj = zzoo.zzaR(zzc(map, "X-Afma-Rewards"));
    }

    private void zzH(Map<String, List<String>> map) {
        if (this.zzUk == null) {
            this.zzUk = zze(map, "X-Afma-Reward-Video-Start-Urls");
        }
    }

    private void zzI(Map<String, List<String>> map) {
        if (this.zzUl == null) {
            this.zzUl = zze(map, "X-Afma-Reward-Video-Complete-Urls");
        }
    }

    private void zzJ(Map<String, List<String>> map) {
        this.zzUm = zzf(map, "X-Afma-Use-Displayed-Impression") | this.zzUm;
    }

    private void zzK(Map<String, List<String>> map) {
        this.zzUo = zzf(map, "X-Afma-Auto-Collect-Location") | this.zzUo;
    }

    private void zzL(Map<String, List<String>> map) {
        List<String> zze = zze(map, "X-Afma-Remote-Ping-Urls");
        if (zze != null) {
            this.zzUq = zze;
        }
    }

    private void zzM(Map<String, List<String>> map) {
        String zzc = zzc(map, "X-Afma-Auto-Protection-Configuration");
        if (zzc == null || TextUtils.isEmpty(zzc)) {
            Uri.Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter(TtmlNode.ATTR_ID, "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.zzTV)) {
                buildUpon.appendQueryParameter("debugDialog", this.zzTV);
            }
            boolean booleanValue = zzgd.zzBp.get().booleanValue();
            String valueOf = String.valueOf(buildUpon.toString());
            String valueOf2 = String.valueOf("navigationURL");
            StringBuilder sb = new StringBuilder(18 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append(valueOf);
            sb.append("&");
            sb.append(valueOf2);
            sb.append("={NAVIGATION_URL}");
            this.zzUn = new zzmp(booleanValue, Arrays.asList(new String[]{sb.toString()}));
            return;
        }
        try {
            this.zzUn = zzmp.zzf(new JSONObject(zzc));
        } catch (JSONException e) {
            zzpk.zzc("Error parsing configuration JSON", e);
            this.zzUn = new zzmp();
        }
    }

    private void zzN(Map<String, List<String>> map) {
        this.zzUp = zzc(map, "Set-Cookie");
    }

    private void zzO(Map<String, List<String>> map) {
        String zzc = zzc(map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty(zzc)) {
            try {
                this.zzUt = zzor.zzh(new JSONObject(zzc));
            } catch (JSONException e) {
                zzpk.zzc("Error parsing safe browsing header", e);
            }
        }
    }

    private void zzP(Map<String, List<String>> map) {
        String zzc = zzc(map, "X-Afma-Pool");
        if (!TextUtils.isEmpty(zzc)) {
            try {
                this.zzUu = new JSONObject(zzc).getBoolean("never_pool");
            } catch (JSONException e) {
                zzpk.zzc("Error parsing interstitial pool header", e);
            }
        }
    }

    static String zzc(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    static long zzd(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return -1;
        }
        String str2 = (String) list.get(0);
        try {
            return (long) (Float.parseFloat(str2) * 1000.0f);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(36 + String.valueOf(str).length() + String.valueOf(str2).length());
            sb.append("Could not parse float from ");
            sb.append(str);
            sb.append(" header: ");
            sb.append(str2);
            zzpk.zzbh(sb.toString());
            return -1;
        }
    }

    static List<String> zze(Map<String, List<String>> map, String str) {
        String str2;
        List list = map.get(str);
        if (list == null || list.isEmpty() || (str2 = (String) list.get(0)) == null) {
            return null;
        }
        return Arrays.asList(str2.trim().split("\\s+"));
    }

    private boolean zzf(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        return list != null && !list.isEmpty() && Boolean.valueOf((String) list.get(0)).booleanValue();
    }

    private void zzl(Map<String, List<String>> map) {
        this.zzTR = zzc(map, "X-Afma-Ad-Size");
    }

    private void zzm(Map<String, List<String>> map) {
        this.zzUs = zzc(map, "X-Afma-Ad-Slot-Size");
    }

    private void zzn(Map<String, List<String>> map) {
        List<String> zze = zze(map, "X-Afma-Click-Tracking-Urls");
        if (zze != null) {
            this.zzTT = zze;
        }
    }

    private void zzo(Map<String, List<String>> map) {
        this.zzTU = zzc(map, "X-Afma-Debug-Signals");
    }

    private void zzp(Map<String, List<String>> map) {
        List list = map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.zzTV = (String) list.get(0);
        }
    }

    private void zzq(Map<String, List<String>> map) {
        List<String> zze = zze(map, "X-Afma-Tracking-Urls");
        if (zze != null) {
            this.zzTX = zze;
        }
    }

    private void zzr(Map<String, List<String>> map) {
        long zzd = zzd(map, "X-Afma-Interstitial-Timeout");
        if (zzd != -1) {
            this.zzTY = zzd;
        }
    }

    private void zzs(Map<String, List<String>> map) {
        this.zzTW = zzc(map, "X-Afma-ActiveView");
    }

    private void zzt(Map<String, List<String>> map) {
        this.zzUd = "native".equals(zzc(map, "X-Afma-Ad-Format"));
    }

    private void zzu(Map<String, List<String>> map) {
        this.zzUc = zzf(map, "X-Afma-Custom-Rendering-Allowed") | this.zzUc;
    }

    private void zzv(Map<String, List<String>> map) {
        this.zzTZ = zzf(map, "X-Afma-Mediation") | this.zzTZ;
    }

    private void zzw(Map<String, List<String>> map) {
        this.zzUr = zzf(map, "X-Afma-Render-In-Browser") | this.zzUr;
    }

    private void zzx(Map<String, List<String>> map) {
        List<String> zze = zze(map, "X-Afma-Manual-Tracking-Urls");
        if (zze != null) {
            this.zzQx = zze;
        }
    }

    private void zzy(Map<String, List<String>> map) {
        long zzd = zzd(map, "X-Afma-Refresh-Rate");
        if (zzd != -1) {
            this.zzUb = zzd;
        }
    }

    private void zzz(Map<String, List<String>> map) {
        int zzkQ;
        List list = map.get("X-Afma-Orientation");
        if (list != null && !list.isEmpty()) {
            String str = (String) list.get(0);
            if ("portrait".equalsIgnoreCase(str)) {
                zzkQ = zzw.zzcO().zzkR();
            } else if ("landscape".equalsIgnoreCase(str)) {
                zzkQ = zzw.zzcO().zzkQ();
            } else {
                return;
            }
            this.mOrientation = zzkQ;
        }
    }

    public void zzb(String str, Map<String, List<String>> map, String str2) {
        this.zzGr = str2;
        zzk(map);
    }

    public zzmn zzj(long j) {
        zzmk zzmk = this.zzLo;
        String str = this.zzTS;
        String str2 = this.zzGr;
        List<String> list = this.zzTT;
        List<String> list2 = this.zzTX;
        long j2 = this.zzTY;
        boolean z = this.zzTZ;
        List<String> list3 = this.zzQx;
        long j3 = this.zzUb;
        int i = this.mOrientation;
        String str3 = this.zzTR;
        String str4 = this.zzTV;
        String str5 = this.zzTW;
        boolean z2 = this.zzUc;
        boolean z3 = this.zzUd;
        boolean z4 = this.zzUe;
        boolean z5 = this.zzUf;
        String str6 = this.zzUh;
        boolean z6 = this.zzUi;
        boolean z7 = this.zzLa;
        zzoo zzoo = this.zzUj;
        List<String> list4 = this.zzUk;
        List<String> list5 = this.zzUl;
        boolean z8 = this.zzUm;
        zzmp zzmp = this.zzUn;
        boolean z9 = this.zzUo;
        String str7 = this.zzUp;
        List<String> list6 = this.zzUq;
        boolean z10 = this.zzUr;
        String str8 = this.zzUs;
        zzor zzor = this.zzUt;
        String str9 = this.zzTU;
        return new zzmn(zzmk, str, str2, list, list2, j2, z, -1, list3, j3, i, str3, j, str4, str5, z2, z3, z4, z5, false, str6, z6, z7, zzoo, list4, list5, z8, zzmp, z9, str7, list6, z10, str8, zzor, str9, this.zzUg, this.zzUu);
    }

    public void zzk(Map<String, List<String>> map) {
        zzl(map);
        zzm(map);
        zzn(map);
        zzo(map);
        zzp(map);
        zzq(map);
        zzr(map);
        zzv(map);
        zzx(map);
        zzy(map);
        zzz(map);
        zzs(map);
        zzA(map);
        zzu(map);
        zzt(map);
        zzB(map);
        zzC(map);
        zzD(map);
        zzE(map);
        zzF(map);
        zzG(map);
        zzH(map);
        zzI(map);
        zzJ(map);
        zzK(map);
        zzN(map);
        zzM(map);
        zzL(map);
        zzO(map);
        zzw(map);
        zzP(map);
    }
}
