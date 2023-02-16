package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public class zzsd {
    private final Context zzaeg;
    private final Context zzwi;

    public zzsd(Context context) {
        zzac.zzw(context);
        Context applicationContext = context.getApplicationContext();
        zzac.zzb(applicationContext, (Object) "Application context can't be null");
        this.zzwi = applicationContext;
        this.zzaeg = applicationContext;
    }

    public Context getApplicationContext() {
        return this.zzwi;
    }

    /* access modifiers changed from: protected */
    public zzss zza(zzsc zzsc) {
        return new zzss(zzsc);
    }

    /* access modifiers changed from: protected */
    public zzh zzao(Context context) {
        return zzh.zzam(context);
    }

    /* access modifiers changed from: protected */
    public zzsh zzb(zzsc zzsc) {
        return new zzsh(zzsc);
    }

    /* access modifiers changed from: protected */
    public zzrx zzc(zzsc zzsc) {
        return new zzrx(zzsc);
    }

    /* access modifiers changed from: protected */
    public zzsk zzd(zzsc zzsc) {
        return new zzsk(zzsc);
    }

    /* access modifiers changed from: protected */
    public zztn zze(zzsc zzsc) {
        return new zztn(zzsc);
    }

    /* access modifiers changed from: protected */
    public zztd zzf(zzsc zzsc) {
        return new zztd(zzsc);
    }

    /* access modifiers changed from: protected */
    public zzsp zzg(zzsc zzsc) {
        return new zzsp(zzsc);
    }

    /* access modifiers changed from: protected */
    public zze zzh(zzsc zzsc) {
        return zzi.zzzc();
    }

    /* access modifiers changed from: protected */
    public GoogleAnalytics zzi(zzsc zzsc) {
        return new GoogleAnalytics(zzsc);
    }

    /* access modifiers changed from: package-private */
    public zzsi zzj(zzsc zzsc) {
        return new zzsi(zzsc, this);
    }

    /* access modifiers changed from: package-private */
    public zzte zzk(zzsc zzsc) {
        return new zzte(zzsc);
    }

    /* access modifiers changed from: protected */
    public zzry zzl(zzsc zzsc) {
        return new zzry(zzsc, this);
    }

    public zzsg zzm(zzsc zzsc) {
        return new zzsg(zzsc);
    }

    public zztf zzn(zzsc zzsc) {
        return new zztf(zzsc);
    }

    public zzsf zzo(zzsc zzsc) {
        return new zzsf(zzsc);
    }

    public Context zzod() {
        return this.zzaeg;
    }

    public zzst zzp(zzsc zzsc) {
        return new zzst(zzsc);
    }

    public zztg zzq(zzsc zzsc) {
        return new zztg(zzsc);
    }
}
