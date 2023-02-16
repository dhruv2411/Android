package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzf implements Parcelable.Creator<LatLng> {
    static void zza(LatLng latLng, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, latLng.latitude);
        zzc.zza(parcel, 3, latLng.longitude);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhC */
    public LatLng createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        double d = 0.0d;
        double d2 = 0.0d;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    d = zzb.zzn(parcel, zzaX);
                    break;
                case 3:
                    d2 = zzb.zzn(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new LatLng(d, d2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzlk */
    public LatLng[] newArray(int i) {
        return new LatLng[i];
    }
}
