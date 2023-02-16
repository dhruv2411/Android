package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public class zza implements Parcelable.Creator<GoogleMapOptions> {
    static void zza(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, googleMapOptions.zzJo());
        zzc.zza(parcel, 3, googleMapOptions.zzJp());
        zzc.zzc(parcel, 4, googleMapOptions.getMapType());
        zzc.zza(parcel, 5, (Parcelable) googleMapOptions.getCamera(), i, false);
        zzc.zza(parcel, 6, googleMapOptions.zzJq());
        zzc.zza(parcel, 7, googleMapOptions.zzJr());
        zzc.zza(parcel, 8, googleMapOptions.zzJs());
        zzc.zza(parcel, 9, googleMapOptions.zzJt());
        zzc.zza(parcel, 10, googleMapOptions.zzJu());
        zzc.zza(parcel, 11, googleMapOptions.zzJv());
        zzc.zza(parcel, 12, googleMapOptions.zzJw());
        zzc.zza(parcel, 14, googleMapOptions.zzJx());
        zzc.zza(parcel, 15, googleMapOptions.zzJy());
        zzc.zza(parcel, 16, googleMapOptions.getMinZoomPreference(), false);
        zzc.zza(parcel, 17, googleMapOptions.getMaxZoomPreference(), false);
        zzc.zza(parcel, 18, (Parcelable) googleMapOptions.getLatLngBoundsForCameraTarget(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhv */
    public GoogleMapOptions createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzaY = zzb.zzaY(parcel);
        CameraPosition cameraPosition = null;
        Float f = null;
        Float f2 = null;
        LatLngBounds latLngBounds = null;
        byte b = -1;
        byte b2 = -1;
        byte b3 = -1;
        byte b4 = -1;
        byte b5 = -1;
        byte b6 = -1;
        byte b7 = -1;
        byte b8 = -1;
        byte b9 = -1;
        byte b10 = -1;
        byte b11 = -1;
        int i = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    b = zzb.zze(parcel2, zzaX);
                    break;
                case 3:
                    b2 = zzb.zze(parcel2, zzaX);
                    break;
                case 4:
                    i = zzb.zzg(parcel2, zzaX);
                    break;
                case 5:
                    cameraPosition = (CameraPosition) zzb.zza(parcel2, zzaX, CameraPosition.CREATOR);
                    break;
                case 6:
                    b3 = zzb.zze(parcel2, zzaX);
                    break;
                case 7:
                    b4 = zzb.zze(parcel2, zzaX);
                    break;
                case 8:
                    b5 = zzb.zze(parcel2, zzaX);
                    break;
                case 9:
                    b6 = zzb.zze(parcel2, zzaX);
                    break;
                case 10:
                    b7 = zzb.zze(parcel2, zzaX);
                    break;
                case 11:
                    b8 = zzb.zze(parcel2, zzaX);
                    break;
                case 12:
                    b9 = zzb.zze(parcel2, zzaX);
                    break;
                case 14:
                    b10 = zzb.zze(parcel2, zzaX);
                    break;
                case 15:
                    b11 = zzb.zze(parcel2, zzaX);
                    break;
                case 16:
                    f = zzb.zzm(parcel2, zzaX);
                    break;
                case 17:
                    f2 = zzb.zzm(parcel2, zzaX);
                    break;
                case 18:
                    latLngBounds = (LatLngBounds) zzb.zza(parcel2, zzaX, LatLngBounds.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel2, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new GoogleMapOptions(b, b2, i, cameraPosition, b3, b4, b5, b6, b7, b8, b9, b10, b11, f, f2, latLngBounds);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel2);
    }

    /* renamed from: zzld */
    public GoogleMapOptions[] newArray(int i) {
        return new GoogleMapOptions[i];
    }
}
