package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzy;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;

public final class zzaj extends zzf<zzy> {
    private static final zzaj zzaGI = new zzaj();

    private zzaj() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zzd(Context context, int i, int i2) throws zzf.zza {
        return zzaGI.zze(context, i, i2);
    }

    private View zze(Context context, int i, int i2) throws zzf.zza {
        try {
            zzah zzah = new zzah(i, i2, (Scope[]) null);
            return (View) zzd.zzF(((zzy) zzbl(context)).zza(zzd.zzA(context), zzah));
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("Could not get button with size ");
            sb.append(i);
            sb.append(" and color ");
            sb.append(i2);
            throw new zzf.zza(sb.toString(), e);
        }
    }

    /* renamed from: zzby */
    public zzy zzc(IBinder iBinder) {
        return zzy.zza.zzbx(iBinder);
    }
}
