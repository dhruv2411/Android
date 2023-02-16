package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.text.TextUtils;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzu;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzats;
import com.google.android.gms.internal.zzatx;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.lang.reflect.InvocationTargetException;

public class zzati extends zzaug {
    static final String zzbrf = String.valueOf(zze.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    private Boolean zzaeZ;

    zzati(zzaue zzaue) {
        super(zzaue);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzJV() {
        super.zzJV();
    }

    public /* bridge */ /* synthetic */ void zzJW() {
        super.zzJW();
    }

    public /* bridge */ /* synthetic */ void zzJX() {
        super.zzJX();
    }

    public /* bridge */ /* synthetic */ zzatb zzJY() {
        return super.zzJY();
    }

    public /* bridge */ /* synthetic */ zzatf zzJZ() {
        return super.zzJZ();
    }

    /* access modifiers changed from: package-private */
    public String zzKK() {
        return zzats.zzbrO.get();
    }

    public int zzKL() {
        return 25;
    }

    public int zzKM() {
        return 40;
    }

    public int zzKN() {
        return 24;
    }

    /* access modifiers changed from: package-private */
    public int zzKO() {
        return 40;
    }

    /* access modifiers changed from: package-private */
    public int zzKP() {
        return 100;
    }

    /* access modifiers changed from: package-private */
    public int zzKQ() {
        return 256;
    }

    public int zzKR() {
        return 36;
    }

    public int zzKS() {
        return 2048;
    }

    /* access modifiers changed from: package-private */
    public int zzKT() {
        return 500;
    }

    public long zzKU() {
        return (long) zzats.zzbrY.get().intValue();
    }

    public long zzKV() {
        return (long) zzats.zzbsa.get().intValue();
    }

    /* access modifiers changed from: package-private */
    public int zzKW() {
        return 25;
    }

    /* access modifiers changed from: package-private */
    public int zzKX() {
        return 1000;
    }

    /* access modifiers changed from: package-private */
    public int zzKY() {
        return 25;
    }

    /* access modifiers changed from: package-private */
    public int zzKZ() {
        return 1000;
    }

    public /* bridge */ /* synthetic */ zzauj zzKa() {
        return super.zzKa();
    }

    public /* bridge */ /* synthetic */ zzatu zzKb() {
        return super.zzKb();
    }

    public /* bridge */ /* synthetic */ zzatl zzKc() {
        return super.zzKc();
    }

    public /* bridge */ /* synthetic */ zzaul zzKd() {
        return super.zzKd();
    }

    public /* bridge */ /* synthetic */ zzauk zzKe() {
        return super.zzKe();
    }

    public /* bridge */ /* synthetic */ zzatv zzKf() {
        return super.zzKf();
    }

    public /* bridge */ /* synthetic */ zzatj zzKg() {
        return super.zzKg();
    }

    public /* bridge */ /* synthetic */ zzaut zzKh() {
        return super.zzKh();
    }

    public /* bridge */ /* synthetic */ zzauc zzKi() {
        return super.zzKi();
    }

    public /* bridge */ /* synthetic */ zzaun zzKj() {
        return super.zzKj();
    }

    public /* bridge */ /* synthetic */ zzaud zzKk() {
        return super.zzKk();
    }

    public /* bridge */ /* synthetic */ zzatx zzKl() {
        return super.zzKl();
    }

    public /* bridge */ /* synthetic */ zzaua zzKm() {
        return super.zzKm();
    }

    public /* bridge */ /* synthetic */ zzati zzKn() {
        return super.zzKn();
    }

    public long zzKv() {
        return 10298;
    }

    public long zzLA() {
        return Math.max(0, zzats.zzbsp.get().longValue());
    }

    public int zzLB() {
        return Math.min(20, Math.max(0, zzats.zzbsq.get().intValue()));
    }

    public String zzLC() {
        String str;
        zzatx.zza zza;
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            e = e;
            zza = zzKl().zzLY();
            str = "Could not find SystemProperties class";
            zza.zzj(str, e);
            return "";
        } catch (NoSuchMethodException e2) {
            e = e2;
            zza = zzKl().zzLY();
            str = "Could not find SystemProperties.get() method";
            zza.zzj(str, e);
            return "";
        } catch (IllegalAccessException e3) {
            e = e3;
            zza = zzKl().zzLY();
            str = "Could not access SystemProperties.get()";
            zza.zzj(str, e);
            return "";
        } catch (InvocationTargetException e4) {
            e = e4;
            zza = zzKl().zzLY();
            str = "SystemProperties.get() threw an exception";
            zza.zzj(str, e);
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public long zzLa() {
        return 15552000000L;
    }

    /* access modifiers changed from: package-private */
    public long zzLb() {
        return 15552000000L;
    }

    /* access modifiers changed from: package-private */
    public long zzLc() {
        return 3600000;
    }

    /* access modifiers changed from: package-private */
    public long zzLd() {
        return ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS;
    }

    /* access modifiers changed from: package-private */
    public long zzLe() {
        return 61000;
    }

    /* access modifiers changed from: package-private */
    public String zzLf() {
        return "google_app_measurement_local.db";
    }

    public boolean zzLg() {
        return false;
    }

    public boolean zzLh() {
        Boolean zzfp = zzfp("firebase_analytics_collection_deactivated");
        return zzfp != null && zzfp.booleanValue();
    }

    public Boolean zzLi() {
        return zzfp("firebase_analytics_collection_enabled");
    }

    public long zzLj() {
        return zzats.zzbsr.get().longValue();
    }

    public long zzLk() {
        return zzats.zzbsm.get().longValue();
    }

    public long zzLl() {
        return zzats.zzbsn.get().longValue();
    }

    public long zzLm() {
        return 1000;
    }

    public int zzLn() {
        return Math.max(0, zzats.zzbrW.get().intValue());
    }

    public int zzLo() {
        return Math.max(1, zzats.zzbrX.get().intValue());
    }

    public int zzLp() {
        return DefaultOggSeeker.MATCH_BYTE_RANGE;
    }

    public String zzLq() {
        return zzats.zzbse.get();
    }

    public long zzLr() {
        return zzats.zzbrR.get().longValue();
    }

    public long zzLs() {
        return Math.max(0, zzats.zzbsf.get().longValue());
    }

    public long zzLt() {
        return Math.max(0, zzats.zzbsh.get().longValue());
    }

    public long zzLu() {
        return Math.max(0, zzats.zzbsi.get().longValue());
    }

    public long zzLv() {
        return Math.max(0, zzats.zzbsj.get().longValue());
    }

    public long zzLw() {
        return Math.max(0, zzats.zzbsk.get().longValue());
    }

    public long zzLx() {
        return Math.max(0, zzats.zzbsl.get().longValue());
    }

    public long zzLy() {
        return zzats.zzbsg.get().longValue();
    }

    public long zzLz() {
        return Math.max(0, zzats.zzbso.get().longValue());
    }

    public String zzP(String str, String str2) {
        Uri.Builder builder = new Uri.Builder();
        Uri.Builder encodedAuthority = builder.scheme(zzats.zzbrS.get()).encodedAuthority(zzats.zzbrT.get());
        String valueOf = String.valueOf(str);
        encodedAuthority.path(valueOf.length() != 0 ? "config/app/".concat(valueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", str2).appendQueryParameter("platform", AbstractSpiCall.ANDROID_CLIENT_TYPE).appendQueryParameter("gmp_version", String.valueOf(10298));
        return builder.build().toString();
    }

    public long zza(String str, zzats.zza<Long> zza) {
        if (str != null) {
            String zzZ = zzKi().zzZ(str, zza.getKey());
            if (!TextUtils.isEmpty(zzZ)) {
                try {
                    return zza.get(Long.valueOf(Long.valueOf(zzZ).longValue())).longValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zza.get().longValue();
    }

    public int zzb(String str, zzats.zza<Integer> zza) {
        if (str != null) {
            String zzZ = zzKi().zzZ(str, zza.getKey());
            if (!TextUtils.isEmpty(zzZ)) {
                try {
                    return zza.get(Integer.valueOf(Integer.valueOf(zzZ).intValue())).intValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zza.get().intValue();
    }

    public int zzfj(@Size(min = 1) String str) {
        return Math.max(0, Math.min(1000000, zzb(str, zzats.zzbrZ)));
    }

    public int zzfk(@Size(min = 1) String str) {
        return zzb(str, zzats.zzbsb);
    }

    public int zzfl(@Size(min = 1) String str) {
        return zzb(str, zzats.zzbsc);
    }

    /* access modifiers changed from: package-private */
    public long zzfm(String str) {
        return zza(str, zzats.zzbrP);
    }

    /* access modifiers changed from: package-private */
    public int zzfn(String str) {
        return zzb(str, zzats.zzbss);
    }

    /* access modifiers changed from: package-private */
    public int zzfo(String str) {
        return Math.max(0, Math.min(2000, zzb(str, zzats.zzbst)));
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Boolean zzfp(@Size(min = 1) String str) {
        zzac.zzdr(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzKl().zzLY().log("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = zzadg.zzbi(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo == null) {
                zzKl().zzLY().log("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (applicationInfo.metaData == null) {
                zzKl().zzLY().log("Failed to load metadata: Metadata bundle is null");
                return null;
            } else if (!applicationInfo.metaData.containsKey(str)) {
                return null;
            } else {
                return Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
            }
        } catch (PackageManager.NameNotFoundException e) {
            zzKl().zzLY().zzj("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public int zzfq(String str) {
        return zzb(str, zzats.zzbrU);
    }

    public int zzfr(String str) {
        return Math.max(0, zzb(str, zzats.zzbrV));
    }

    public int zzfs(String str) {
        return Math.max(0, Math.min(1000000, zzb(str, zzats.zzbsd)));
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    public /* bridge */ /* synthetic */ com.google.android.gms.common.util.zze zznR() {
        return super.zznR();
    }

    public boolean zzoW() {
        if (this.zzaeZ == null) {
            synchronized (this) {
                if (this.zzaeZ == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String zzzr = zzu.zzzr();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzaeZ = Boolean.valueOf(str != null && str.equals(zzzr));
                    }
                    if (this.zzaeZ == null) {
                        this.zzaeZ = Boolean.TRUE;
                        zzKl().zzLY().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzaeZ.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public long zzpq() {
        return zzats.zzbsu.get().longValue();
    }

    public String zzpv() {
        return "google_app_measurement.db";
    }

    public long zzpz() {
        return Math.max(0, zzats.zzbrQ.get().longValue());
    }

    public boolean zzwR() {
        return zzaba.zzwR();
    }
}
