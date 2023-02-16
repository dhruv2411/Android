package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzatp implements Parcelable.Creator<zzato> {
    static void zza(zzato zzato, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzato.zzLW(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhS */
    public zzato createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        Bundle bundle = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            if (zzb.zzdc(zzaX) != 2) {
                zzb.zzb(parcel, zzaX);
            } else {
                bundle = zzb.zzs(parcel, zzaX);
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzato(bundle);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzlB */
    public zzato[] newArray(int i) {
        return new zzato[i];
    }
}
