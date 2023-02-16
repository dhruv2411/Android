package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzjj;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpd;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqp;
import com.google.android.gms.internal.zzqw;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.Map;
import org.json.JSONObject;

@zzme
public class zzh {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    private static boolean zza(@Nullable zzpd zzpd) {
        if (zzpd == null) {
            return true;
        }
        return (((zzw.zzcS().currentTimeMillis() - zzpd.zzkb()) > zzgd.zzEL.get().longValue() ? 1 : ((zzw.zzcS().currentTimeMillis() - zzpd.zzkb()) == zzgd.zzEL.get().longValue() ? 0 : -1)) > 0) || !zzpd.zzkc();
    }

    public void zza(Context context, zzqh zzqh, String str, zzpd zzpd) {
        zza(context, zzqh, false, zzpd, zzpd != null ? null : zzpd.zzke(), str, (Runnable) null);
    }

    public void zza(Context context, zzqh zzqh, String str, @Nullable Runnable runnable) {
        zza(context, zzqh, true, (zzpd) null, str, (String) null, runnable);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzqh zzqh, boolean z, @Nullable zzpd zzpd, String str, @Nullable String str2, @Nullable final Runnable runnable) {
        if (zza(zzpd)) {
            if (context == null) {
                zzpk.zzbh("Context not provided to fetch application settings");
            } else if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                this.mContext = context;
                final zzji zzd = zzw.zzcM().zzd(context, zzqh);
                final AnonymousClass1 r3 = new zzid() {
                    public void zza(zzqw zzqw, Map<String, String> map) {
                        zzqw.zzb("/appSettingsFetched", (zzid) this);
                        synchronized (zzh.this.zzrJ) {
                            if (map != null) {
                                try {
                                    if (PdfBoolean.TRUE.equalsIgnoreCase(map.get("isSuccessful"))) {
                                        zzw.zzcQ().zzn(zzh.this.mContext, map.get("appSettingsJson"));
                                        if (runnable != null) {
                                            runnable.run();
                                        }
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        }
                    }
                };
                final String str3 = str;
                final String str4 = str2;
                final boolean z2 = z;
                final Context context2 = context;
                zzpo.zzXC.post(new Runnable(this) {
                    public void run() {
                        zzd.zzgO().zza(new zzqp.zzc<zzjj>() {
                            /* renamed from: zzb */
                            public void zzd(zzjj zzjj) {
                                String str;
                                String str2;
                                zzjj.zza("/appSettingsFetched", r3);
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    if (!TextUtils.isEmpty(str3)) {
                                        str = "app_id";
                                        str2 = str3;
                                    } else {
                                        if (!TextUtils.isEmpty(str4)) {
                                            str = "ad_unit_id";
                                            str2 = str4;
                                        }
                                        jSONObject.put("is_init", z2);
                                        jSONObject.put("pn", context2.getPackageName());
                                        zzjj.zza("AFMA_fetchAppSettings", jSONObject);
                                    }
                                    jSONObject.put(str, str2);
                                    jSONObject.put("is_init", z2);
                                    jSONObject.put("pn", context2.getPackageName());
                                    zzjj.zza("AFMA_fetchAppSettings", jSONObject);
                                } catch (Exception e) {
                                    zzjj.zzb("/appSettingsFetched", r3);
                                    zzpk.zzb("Error requesting application settings", e);
                                }
                            }
                        }, new zzqp.zzb());
                    }
                });
            } else {
                zzpk.zzbh("App settings could not be fetched. Required parameters missing");
            }
        }
    }
}
