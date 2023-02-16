package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzjv;
import com.google.android.gms.internal.zzkd;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzju implements zzjv.zza {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final String zzKW;
    private final long zzKX;
    private final zzjr zzKY;
    private final zzjq zzKZ;
    private final boolean zzLa;
    /* access modifiers changed from: private */
    public zzkb zzLb;
    /* access modifiers changed from: private */
    public int zzLc = -2;
    private zzkd zzLd;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();
    private final zzka zzsz;
    private final zzhc zztp;
    private final List<String> zztq;
    private final zzqh zztt;
    private zzec zzum;
    private final zzeg zzus;
    private final boolean zzwf;

    public zzju(Context context, String str, zzka zzka, zzjr zzjr, zzjq zzjq, zzec zzec, zzeg zzeg, zzqh zzqh, boolean z, boolean z2, zzhc zzhc, List<String> list) {
        this.mContext = context;
        this.zzsz = zzka;
        this.zzKZ = zzjq;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.zzKW = zzgV();
        } else {
            this.zzKW = str;
        }
        this.zzKY = zzjr;
        this.zzKX = zzjr.zzKE != -1 ? zzjr.zzKE : 10000;
        this.zzum = zzec;
        this.zzus = zzeg;
        this.zztt = zzqh;
        this.zzwf = z;
        this.zzLa = z2;
        this.zztp = zzhc;
        this.zztq = list;
    }

    /* access modifiers changed from: private */
    public boolean zzG(int i) {
        try {
            Bundle zzhf = this.zzwf ? this.zzLb.zzhf() : this.zzus.zzzz ? this.zzLb.getInterstitialAdapterInfo() : this.zzLb.zzhe();
            return zzhf != null && (zzhf.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException unused) {
            zzpk.zzbh("Could not get adapter info. Returning false");
            return false;
        }
    }

    private static zzkd zzH(final int i) {
        return new zzkd.zza() {
            public int zzha() throws RemoteException {
                return i;
            }
        };
    }

    private long zza(long j, long j2, long j3, long j4) {
        while (this.zzLc == -2) {
            zzb(j, j2, j3, j4);
        }
        return zzw.zzcS().elapsedRealtime() - j;
    }

    /* access modifiers changed from: private */
    public void zza(zzjt zzjt) {
        zzkb zzkb;
        IObjectWrapper zzA;
        zzeg zzeg;
        zzec zzec;
        String str;
        zzkb zzkb2;
        IObjectWrapper zzA2;
        zzec zzec2;
        String str2;
        zzhc zzhc;
        List<String> list;
        String zzap = zzap(this.zzKZ.zzKv);
        try {
            if (this.zztt.zzYX >= 4100000) {
                if (this.zzwf) {
                    zzkb2 = this.zzLb;
                    zzA2 = zzd.zzA(this.mContext);
                    zzec2 = this.zzum;
                    str2 = this.zzKZ.zzKn;
                    zzhc = this.zztp;
                    list = this.zztq;
                } else if (this.zzus.zzzz) {
                    this.zzLb.zza(zzd.zzA(this.mContext), this.zzum, zzap, this.zzKZ.zzKn, (zzkc) zzjt);
                    return;
                } else {
                    if (!this.zzLa) {
                        zzkb = this.zzLb;
                        zzA = zzd.zzA(this.mContext);
                        zzeg = this.zzus;
                        zzec = this.zzum;
                        str = this.zzKZ.zzKn;
                    } else if (this.zzKZ.zzKy != null) {
                        zzkb2 = this.zzLb;
                        zzA2 = zzd.zzA(this.mContext);
                        zzec2 = this.zzum;
                        str2 = this.zzKZ.zzKn;
                        zzhc = new zzhc(zzaq(this.zzKZ.zzKC));
                        list = this.zzKZ.zzKB;
                    } else {
                        zzkb = this.zzLb;
                        zzA = zzd.zzA(this.mContext);
                        zzeg = this.zzus;
                        zzec = this.zzum;
                        str = this.zzKZ.zzKn;
                    }
                    zzkb.zza(zzA, zzeg, zzec, zzap, str, zzjt);
                    return;
                }
                zzkb2.zza(zzA2, zzec2, zzap, str2, zzjt, zzhc, list);
            } else if (this.zzus.zzzz) {
                this.zzLb.zza(zzd.zzA(this.mContext), this.zzum, zzap, zzjt);
            } else {
                this.zzLb.zza(zzd.zzA(this.mContext), this.zzus, this.zzum, zzap, (zzkc) zzjt);
            }
        } catch (RemoteException e) {
            zzpk.zzc("Could not request ad from mediation adapter.", e);
            zzF(5);
        }
    }

    private String zzap(String str) {
        if (str == null || !zzgY() || zzG(2)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.remove("cpm_floor_cents");
            return jSONObject.toString();
        } catch (JSONException unused) {
            zzpk.zzbh("Could not remove field. Returning the original value");
            return str;
        }
    }

    private static NativeAdOptions zzaq(String str) {
        NativeAdOptions.Builder builder = new NativeAdOptions.Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            builder.setImageOrientation(zzar(jSONObject.optString("native_image_orientation", "any")));
        } catch (JSONException e) {
            zzpk.zzc("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }

    private static int zzar(String str) {
        if ("landscape".equals(str)) {
            return 2;
        }
        return "portrait".equals(str) ? 1 : 0;
    }

    private void zzb(long j, long j2, long j3, long j4) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j5 = j2 - (elapsedRealtime - j);
        long j6 = j4 - (elapsedRealtime - j3);
        if (j5 <= 0 || j6 <= 0) {
            zzpk.zzbg("Timed out waiting for adapter.");
            this.zzLc = 3;
            return;
        }
        try {
            this.zzrJ.wait(Math.min(j5, j6));
        } catch (InterruptedException unused) {
            this.zzLc = -1;
        }
    }

    private String zzgV() {
        try {
            return (TextUtils.isEmpty(this.zzKZ.zzKr) || !this.zzsz.zzat(this.zzKZ.zzKr)) ? "com.google.ads.mediation.customevent.CustomEventAdapter" : "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        } catch (RemoteException unused) {
            zzpk.zzbh("Fail to determine the custom event's version, assuming the old one.");
            return "com.google.ads.mediation.customevent.CustomEventAdapter";
        }
    }

    private zzkd zzgW() {
        if (this.zzLc != 0 || !zzgY()) {
            return null;
        }
        try {
            if (!(!zzG(4) || this.zzLd == null || this.zzLd.zzha() == 0)) {
                return this.zzLd;
            }
        } catch (RemoteException unused) {
            zzpk.zzbh("Could not get cpm value from MediationResponseMetadata");
        }
        return zzH(zzgZ());
    }

    /* access modifiers changed from: private */
    public zzkb zzgX() {
        String valueOf = String.valueOf(this.zzKW);
        zzpk.zzbg(valueOf.length() != 0 ? "Instantiating mediation adapter: ".concat(valueOf) : new String("Instantiating mediation adapter: "));
        if (!this.zzwf) {
            if (zzgd.zzDI.get().booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzKW)) {
                return zza((MediationAdapter) new AdMobAdapter());
            }
            if (zzgd.zzDJ.get().booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzKW)) {
                return zza((MediationAdapter) new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzKW)) {
                return new zzkh(new zzkp());
            }
        }
        try {
            return this.zzsz.zzas(this.zzKW);
        } catch (RemoteException e) {
            String valueOf2 = String.valueOf(this.zzKW);
            zzpk.zza(valueOf2.length() != 0 ? "Could not instantiate mediation adapter: ".concat(valueOf2) : new String("Could not instantiate mediation adapter: "), e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean zzgY() {
        return this.zzKY.zzKO != -1;
    }

    private int zzgZ() {
        if (this.zzKZ.zzKv == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzKZ.zzKv);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzKW)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzG(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return optInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : optInt;
        } catch (JSONException unused) {
            zzpk.zzbh("Could not convert to json. Returning 0");
            return 0;
        }
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            try {
                if (this.zzLb != null) {
                    this.zzLb.destroy();
                }
            } catch (RemoteException e) {
                zzpk.zzc("Could not destroy mediation adapter.", e);
            }
            this.zzLc = -1;
            this.zzrJ.notify();
        }
    }

    public void zzF(int i) {
        synchronized (this.zzrJ) {
            this.zzLc = i;
            this.zzrJ.notify();
        }
    }

    /* JADX INFO: finally extract failed */
    public zzjv zza(long j, long j2) {
        zzjv zzjv;
        synchronized (this.zzrJ) {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                final zzjt zzjt = new zzjt();
                zzpo.zzXC.post(new Runnable() {
                    public void run() {
                        synchronized (zzju.this.zzrJ) {
                            if (zzju.this.zzLc == -2) {
                                zzkb unused = zzju.this.zzLb = zzju.this.zzgX();
                                if (zzju.this.zzLb == null) {
                                    zzju.this.zzF(4);
                                } else if (!zzju.this.zzgY() || zzju.this.zzG(1)) {
                                    zzjt.zza((zzjv.zza) zzju.this);
                                    zzju.this.zza(zzjt);
                                } else {
                                    String zzf = zzju.this.zzKW;
                                    StringBuilder sb = new StringBuilder(56 + String.valueOf(zzf).length());
                                    sb.append("Ignoring adapter ");
                                    sb.append(zzf);
                                    sb.append(" as delayed impression is not supported");
                                    zzpk.zzbh(sb.toString());
                                    zzju.this.zzF(2);
                                }
                            }
                        }
                    }
                });
                long zza = zza(elapsedRealtime, this.zzKX, j, j2);
                zzjq zzjq = this.zzKZ;
                zzkb zzkb = this.zzLb;
                String str = this.zzKW;
                zzjt zzjt2 = zzjt;
                zzjv = new zzjv(zzjq, zzkb, str, zzjt2, this.zzLc, zzgW(), zza);
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzjv;
    }

    /* access modifiers changed from: protected */
    public zzkb zza(MediationAdapter mediationAdapter) {
        return new zzkh(mediationAdapter);
    }

    public void zza(int i, zzkd zzkd) {
        synchronized (this.zzrJ) {
            this.zzLc = i;
            this.zzLd = zzkd;
            this.zzrJ.notify();
        }
    }
}
