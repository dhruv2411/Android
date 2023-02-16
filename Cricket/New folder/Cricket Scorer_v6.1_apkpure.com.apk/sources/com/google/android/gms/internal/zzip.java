package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;

@zzme
class zzip implements zzid {
    zzip() {
    }

    private int zzh(Map<String, String> map) throws NullPointerException, NumberFormatException {
        int parseInt = Integer.parseInt(map.get("playbackState"));
        if (parseInt < 0 || 3 < parseInt) {
            return 0;
        }
        return parseInt;
    }

    public void zza(zzqw zzqw, Map<String, String> map) {
        if (zzgd.zzDE.get().booleanValue()) {
            zzrb zzlG = zzqw.zzlG();
            if (zzlG == null) {
                try {
                    zzrb zzrb = new zzrb(zzqw, Float.parseFloat(map.get("duration")));
                    zzqw.zza(zzrb);
                    zzlG = zzrb;
                } catch (NullPointerException | NumberFormatException e) {
                    zzpk.zzb("Unable to parse videoMeta message.", e);
                    zzw.zzcQ().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                }
            }
            boolean equals = "1".equals(map.get("muted"));
            float parseFloat = Float.parseFloat(map.get("currentTime"));
            int zzh = zzh(map);
            String str = map.get("aspectRatio");
            float parseFloat2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (zzpk.zzak(3)) {
                StringBuilder sb = new StringBuilder(79 + String.valueOf(str).length());
                sb.append("Video Meta GMSG: isMuted : ");
                sb.append(equals);
                sb.append(" , playbackState : ");
                sb.append(zzh);
                sb.append(" , aspectRatio : ");
                sb.append(str);
                zzpk.zzbf(sb.toString());
            }
            zzlG.zza(parseFloat, zzh, equals, parseFloat2);
        }
    }
}
