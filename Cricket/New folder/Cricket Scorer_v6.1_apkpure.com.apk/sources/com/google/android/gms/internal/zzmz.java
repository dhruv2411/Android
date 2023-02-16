package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzmz implements Parcelable.Creator<zzmy> {
    static void zza(zzmy zzmy, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzmy.zzFV, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaa */
    public zzmy[] newArray(int i) {
        return new zzmy[i];
    }

    /* renamed from: zzs */
    public zzmy createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            if (zzb.zzdc(zzaX) != 2) {
                zzb.zzb(parcel, zzaX);
            } else {
                str = zzb.zzq(parcel, zzaX);
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzmy(str);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }
}
