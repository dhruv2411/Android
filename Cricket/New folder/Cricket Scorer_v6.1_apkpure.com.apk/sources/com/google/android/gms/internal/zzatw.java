package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzatt;

public class zzatw extends zzf<zzatt> {
    public zzatw(Context context, Looper looper, zzf.zzb zzb, zzf.zzc zzc) {
        super(context, looper, 93, zzb, zzc, (String) null);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String zzeA() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }

    /* renamed from: zzet */
    public zzatt zzh(IBinder iBinder) {
        return zzatt.zza.zzes(iBinder);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String zzez() {
        return "com.google.android.gms.measurement.START";
    }
}
