package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzr implements Parcelable.Creator<TileOverlayOptions> {
    static void zza(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, tileOverlayOptions.zzJQ(), false);
        zzc.zza(parcel, 3, tileOverlayOptions.isVisible());
        zzc.zza(parcel, 4, tileOverlayOptions.getZIndex());
        zzc.zza(parcel, 5, tileOverlayOptions.getFadeIn());
        zzc.zza(parcel, 6, tileOverlayOptions.getTransparency());
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhO */
    public TileOverlayOptions createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        float f = 0.0f;
        float f2 = 0.0f;
        IBinder iBinder = null;
        boolean z = false;
        boolean z2 = true;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    iBinder = zzb.zzr(parcel, zzaX);
                    break;
                case 3:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 4:
                    f = zzb.zzl(parcel, zzaX);
                    break;
                case 5:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 6:
                    f2 = zzb.zzl(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new TileOverlayOptions(iBinder, z, f, z2, f2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzlw */
    public TileOverlayOptions[] newArray(int i) {
        return new TileOverlayOptions[i];
    }
}
