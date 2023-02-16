package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzob implements Parcelable.Creator<zzoa> {
    static void zza(zzoa zzoa, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) zzoa.zzRy, i, false);
        zzc.zza(parcel, 3, zzoa.zzvl, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzac */
    public zzoa[] newArray(int i) {
        return new zzoa[i];
    }

    /* renamed from: zzt */
    public zzoa createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        zzec zzec = null;
        String str = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    zzec = (zzec) zzb.zza(parcel, zzaX, zzec.CREATOR);
                    break;
                case 3:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzoa(zzec, str);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }
}
