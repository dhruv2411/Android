package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzq implements Parcelable.Creator<Tile> {
    static void zza(Tile tile, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 2, tile.width);
        zzc.zzc(parcel, 3, tile.height);
        zzc.zza(parcel, 4, tile.data, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhN */
    public Tile createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        byte[] bArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 3:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    bArr = zzb.zzt(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new Tile(i, i2, bArr);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzlv */
    public Tile[] newArray(int i) {
        return new Tile[i];
    }
}
