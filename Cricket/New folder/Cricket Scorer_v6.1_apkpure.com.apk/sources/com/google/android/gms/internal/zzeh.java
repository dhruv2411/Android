package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzeh implements Parcelable.Creator<zzeg> {
    static void zza(zzeg zzeg, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzeg.zzzy, false);
        zzc.zzc(parcel, 3, zzeg.height);
        zzc.zzc(parcel, 4, zzeg.heightPixels);
        zzc.zza(parcel, 5, zzeg.zzzz);
        zzc.zzc(parcel, 6, zzeg.width);
        zzc.zzc(parcel, 7, zzeg.widthPixels);
        zzc.zza(parcel, 8, (T[]) zzeg.zzzA, i, false);
        zzc.zza(parcel, 9, zzeg.zzzB);
        zzc.zza(parcel, 10, zzeg.zzzC);
        zzc.zza(parcel, 11, zzeg.zzzD);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzf */
    public zzeg createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        zzeg[] zzegArr = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 5:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 6:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 7:
                    i4 = zzb.zzg(parcel, zzaX);
                    break;
                case 8:
                    zzegArr = (zzeg[]) zzb.zzb(parcel, zzaX, zzeg.CREATOR);
                    break;
                case 9:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 10:
                    z3 = zzb.zzc(parcel, zzaX);
                    break;
                case 11:
                    z4 = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzeg(str, i, i2, z, i3, i4, zzegArr, z2, z3, z4);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzw */
    public zzeg[] newArray(int i) {
        return new zzeg[i];
    }
}
