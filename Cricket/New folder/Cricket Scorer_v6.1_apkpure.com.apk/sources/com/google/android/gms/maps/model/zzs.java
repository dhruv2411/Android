package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzs implements Parcelable.Creator<VisibleRegion> {
    static void zza(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) visibleRegion.nearLeft, i, false);
        zzc.zza(parcel, 3, (Parcelable) visibleRegion.nearRight, i, false);
        zzc.zza(parcel, 4, (Parcelable) visibleRegion.farLeft, i, false);
        zzc.zza(parcel, 5, (Parcelable) visibleRegion.farRight, i, false);
        zzc.zza(parcel, 6, (Parcelable) visibleRegion.latLngBounds, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhP */
    public VisibleRegion createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        LatLngBounds latLngBounds = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    latLng = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 3:
                    latLng2 = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 4:
                    latLng3 = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 5:
                    latLng4 = (LatLng) zzb.zza(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) zzb.zza(parcel, zzaX, LatLngBounds.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new VisibleRegion(latLng, latLng2, latLng3, latLng4, latLngBounds);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzlx */
    public VisibleRegion[] newArray(int i) {
        return new VisibleRegion[i];
    }
}
