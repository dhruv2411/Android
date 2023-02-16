package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzate implements Parcelable.Creator<zzatd> {
    static void zza(zzatd zzatd, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzatd.packageName, false);
        zzc.zza(parcel, 3, zzatd.zzbqL, false);
        zzc.zza(parcel, 4, zzatd.zzbhN, false);
        zzc.zza(parcel, 5, zzatd.zzbqM, false);
        zzc.zza(parcel, 6, zzatd.zzbqN);
        zzc.zza(parcel, 7, zzatd.zzbqO);
        zzc.zza(parcel, 8, zzatd.zzbqP, false);
        zzc.zza(parcel, 9, zzatd.zzbqQ);
        zzc.zza(parcel, 10, zzatd.zzbqR);
        zzc.zza(parcel, 11, zzatd.zzbqS);
        zzc.zza(parcel, 12, zzatd.zzbqT, false);
        zzc.zza(parcel, 13, zzatd.zzbqU);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhQ */
    public zzatd createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzaY = zzb.zzaY(parcel);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        boolean z = true;
        boolean z2 = false;
        long j4 = -2147483648L;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel2, zzaX);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel2, zzaX);
                    break;
                case 4:
                    str3 = zzb.zzq(parcel2, zzaX);
                    break;
                case 5:
                    str4 = zzb.zzq(parcel2, zzaX);
                    break;
                case 6:
                    j = zzb.zzi(parcel2, zzaX);
                    break;
                case 7:
                    j2 = zzb.zzi(parcel2, zzaX);
                    break;
                case 8:
                    str5 = zzb.zzq(parcel2, zzaX);
                    break;
                case 9:
                    z = zzb.zzc(parcel2, zzaX);
                    break;
                case 10:
                    z2 = zzb.zzc(parcel2, zzaX);
                    break;
                case 11:
                    j4 = zzb.zzi(parcel2, zzaX);
                    break;
                case 12:
                    str6 = zzb.zzq(parcel2, zzaX);
                    break;
                case 13:
                    j3 = zzb.zzi(parcel2, zzaX);
                    break;
                default:
                    zzb.zzb(parcel2, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzatd(str, str2, str3, str4, j, j2, str5, z, z2, j4, str6, j3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel2);
    }

    /* renamed from: zzlz */
    public zzatd[] newArray(int i) {
        return new zzatd[i];
    }
}
