package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzms implements Parcelable.Creator<zzmr> {
    static void zza(zzmr zzmr, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzmr.zzSN);
        zzc.zza(parcel, 3, zzmr.zzSO);
        zzc.zza(parcel, 4, zzmr.zzSP);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzY */
    public zzmr[] newArray(int i) {
        return new zzmr[i];
    }

    /* renamed from: zzq */
    public zzmr createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 3:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 4:
                    z3 = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzmr(z, z2, z3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }
}
