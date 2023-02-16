package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzos implements Parcelable.Creator<zzor> {
    static void zza(zzor zzor, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzor.zzVQ, false);
        zzc.zza(parcel, 3, zzor.zzVR, false);
        zzc.zza(parcel, 4, zzor.zzVS);
        zzc.zza(parcel, 5, zzor.zzVT);
        zzc.zzb(parcel, 6, zzor.zzVU, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzag */
    public zzor[] newArray(int i) {
        return new zzor[i];
    }

    /* renamed from: zzv */
    public zzor createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        boolean z = false;
        boolean z2 = false;
        String str = null;
        String str2 = null;
        ArrayList<String> arrayList = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 5:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 6:
                    arrayList = zzb.zzE(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzor(str, str2, z, z2, arrayList);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }
}
