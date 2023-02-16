package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzfu implements Parcelable.Creator<zzft> {
    static void zza(zzft zzft, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzft.zzAU);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzB */
    public zzft[] newArray(int i) {
        return new zzft[i];
    }

    /* renamed from: zzi */
    public zzft createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            if (zzb.zzdc(zzaX) != 2) {
                zzb.zzb(parcel, zzaX);
            } else {
                z = zzb.zzc(parcel, zzaX);
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzft(z);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }
}
