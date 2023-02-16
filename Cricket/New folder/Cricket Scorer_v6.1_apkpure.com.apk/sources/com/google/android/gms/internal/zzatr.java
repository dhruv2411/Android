package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzatr implements Parcelable.Creator<zzatq> {
    static void zza(zzatq zzatq, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzatq.name, false);
        zzc.zza(parcel, 3, (Parcelable) zzatq.zzbrG, i, false);
        zzc.zza(parcel, 4, zzatq.zzbqV, false);
        zzc.zza(parcel, 5, zzatq.zzbrH);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhT */
    public zzatq createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        zzato zzato = null;
        String str2 = null;
        long j = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    zzato = (zzato) zzb.zza(parcel, zzaX, zzato.CREATOR);
                    break;
                case 4:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 5:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzatq(str, zzato, str2, j);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzlC */
    public zzatq[] newArray(int i) {
        return new zzatq[i];
    }
}
