package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzayy implements Parcelable.Creator<zzayx> {
    static void zza(zzayx zzayx, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 2, zzayx.zzbBy);
        zzc.zza(parcel, 3, (T[]) zzayx.zzbBz, i, false);
        zzc.zza(parcel, 4, zzayx.zzbBA, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzja */
    public zzayx createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        zzayz[] zzayzArr = null;
        int i = 0;
        String[] strArr = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 3:
                    zzayzArr = (zzayz[]) zzb.zzb(parcel, zzaX, zzayz.CREATOR);
                    break;
                case 4:
                    strArr = zzb.zzC(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzayx(i, zzayzArr, strArr);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzmX */
    public zzayx[] newArray(int i) {
        return new zzayx[i];
    }
}
