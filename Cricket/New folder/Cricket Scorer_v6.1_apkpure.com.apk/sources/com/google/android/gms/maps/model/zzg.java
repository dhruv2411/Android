package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzg implements Parcelable.Creator<MapStyleOptions> {
    static void zza(MapStyleOptions mapStyleOptions, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, mapStyleOptions.zzJL(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhD */
    public MapStyleOptions createFromParcel(Parcel parcel) {
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
            return new MapStyleOptions(str);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzll */
    public MapStyleOptions[] newArray(int i) {
        return new MapStyleOptions[i];
    }
}
