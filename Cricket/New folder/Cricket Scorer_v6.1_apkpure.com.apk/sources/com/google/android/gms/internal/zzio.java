package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.itextpdf.text.html.HtmlTags;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.util.Map;
import org.json.JSONObject;

@zzme
public final class zzio implements zzid {
    private boolean zzIv;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 != null) {
            try {
                return zzel.zzeT().zzb(context, Integer.parseInt(str2));
            } catch (NumberFormatException unused) {
                StringBuilder sb = new StringBuilder(34 + String.valueOf(str).length() + String.valueOf(str2).length());
                sb.append("Could not parse ");
                sb.append(str);
                sb.append(" in a video GMSG: ");
                sb.append(str2);
                zzpk.zzbh(sb.toString());
            }
        }
        return i;
    }

    public void zza(zzqw zzqw, Map<String, String> map) {
        int i;
        int i2;
        String str;
        String str2;
        String valueOf;
        String str3;
        String str4 = map.get("action");
        if (str4 == null) {
            str3 = "Action missing from video GMSG.";
        } else {
            if (zzpk.zzak(3)) {
                JSONObject jSONObject = new JSONObject(map);
                jSONObject.remove("google.afma.Notify_dt");
                String valueOf2 = String.valueOf(jSONObject.toString());
                StringBuilder sb = new StringBuilder(13 + String.valueOf(str4).length() + String.valueOf(valueOf2).length());
                sb.append("Video GMSG: ");
                sb.append(str4);
                sb.append(" ");
                sb.append(valueOf2);
                zzpk.zzbf(sb.toString());
            }
            if ("background".equals(str4)) {
                String str5 = map.get("color");
                if (TextUtils.isEmpty(str5)) {
                    str3 = "Color parameter missing from color video GMSG.";
                } else {
                    try {
                        zzqw.setBackgroundColor(Color.parseColor(str5));
                        return;
                    } catch (IllegalArgumentException unused) {
                        zzpk.zzbh("Invalid color parameter in video GMSG.");
                        return;
                    }
                }
            } else {
                zzqv zzlD = zzqw.zzlD();
                if (zzlD == null) {
                    str3 = "Could not get underlay container for a video GMSG.";
                } else {
                    boolean equals = AppSettingsData.STATUS_NEW.equals(str4);
                    boolean equals2 = "position".equals(str4);
                    if (equals || equals2) {
                        Context context = zzqw.getContext();
                        int zza = zza(context, map, "x", 0);
                        int zza2 = zza(context, map, "y", 0);
                        int zza3 = zza(context, map, "w", -1);
                        int zza4 = zza(context, map, "h", -1);
                        if (zzgd.zzEv.get().booleanValue()) {
                            zza3 = Math.min(zza3, zzqw.getMeasuredWidth() - zza);
                            i = Math.min(zza4, zzqw.getMeasuredHeight() - zza2);
                        } else {
                            i = zza4;
                        }
                        try {
                            i2 = Integer.parseInt(map.get(DBHelper.TABLE_PLAYERS));
                        } catch (NumberFormatException unused2) {
                            i2 = 0;
                        }
                        boolean parseBoolean = Boolean.parseBoolean(map.get("spherical"));
                        if (!equals || zzlD.zzlo() != null) {
                            zzlD.zze(zza, zza2, zza3, i);
                            return;
                        } else {
                            zzlD.zza(zza, zza2, zza3, i, i2, parseBoolean);
                            return;
                        }
                    } else {
                        zzl zzlo = zzlD.zzlo();
                        if (zzlo == null) {
                            zzl.zzi(zzqw);
                            return;
                        } else if ("click".equals(str4)) {
                            Context context2 = zzqw.getContext();
                            int zza5 = zza(context2, map, "x", 0);
                            int zza6 = zza(context2, map, "y", 0);
                            long uptimeMillis = SystemClock.uptimeMillis();
                            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) zza5, (float) zza6, 0);
                            zzlo.zzf(obtain);
                            obtain.recycle();
                            return;
                        } else if ("currentTime".equals(str4)) {
                            String str6 = map.get("time");
                            if (str6 == null) {
                                str3 = "Time parameter missing from currentTime video GMSG.";
                            } else {
                                try {
                                    zzlo.seekTo((int) (Float.parseFloat(str6) * 1000.0f));
                                    return;
                                } catch (NumberFormatException unused3) {
                                    str2 = "Could not parse time parameter from currentTime video GMSG: ";
                                    valueOf = String.valueOf(str6);
                                    if (valueOf.length() == 0) {
                                        str = new String(str2);
                                    }
                                    str = str2.concat(valueOf);
                                }
                            }
                        } else if ("hide".equals(str4)) {
                            zzlo.setVisibility(4);
                            return;
                        } else if ("load".equals(str4)) {
                            zzlo.zzib();
                            return;
                        } else if ("muted".equals(str4)) {
                            if (Boolean.parseBoolean(map.get("muted"))) {
                                zzlo.zzhZ();
                                return;
                            } else {
                                zzlo.zzia();
                                return;
                            }
                        } else if ("pause".equals(str4)) {
                            zzlo.pause();
                            return;
                        } else if ("play".equals(str4)) {
                            zzlo.play();
                            return;
                        } else if ("show".equals(str4)) {
                            zzlo.setVisibility(0);
                            return;
                        } else if (HtmlTags.SRC.equals(str4)) {
                            zzlo.zzaC(map.get(HtmlTags.SRC));
                            return;
                        } else if ("touchMove".equals(str4)) {
                            Context context3 = zzqw.getContext();
                            zzlo.zza((float) zza(context3, map, "dx", 0), (float) zza(context3, map, "dy", 0));
                            if (!this.zzIv) {
                                zzqw.zzlt().zzhL();
                                this.zzIv = true;
                                return;
                            }
                            return;
                        } else if ("volume".equals(str4)) {
                            String str7 = map.get("volume");
                            if (str7 == null) {
                                str3 = "Level parameter missing from volume video GMSG.";
                            } else {
                                try {
                                    zzlo.zzb(Float.parseFloat(str7));
                                    return;
                                } catch (NumberFormatException unused4) {
                                    str2 = "Could not parse volume parameter from volume video GMSG: ";
                                    valueOf = String.valueOf(str7);
                                    if (valueOf.length() == 0) {
                                        str = new String(str2);
                                    }
                                    str = str2.concat(valueOf);
                                }
                            }
                        } else if ("watermark".equals(str4)) {
                            zzlo.zzic();
                            return;
                        } else {
                            String valueOf3 = String.valueOf(str4);
                            str = valueOf3.length() != 0 ? "Unknown video action: ".concat(valueOf3) : new String("Unknown video action: ");
                            zzpk.zzbh(str);
                            return;
                        }
                    }
                }
            }
        }
        zzpk.zzbh(str3);
    }
}
