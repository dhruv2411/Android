package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzee implements Parcelable.Creator<zzec> {
    static void zza(zzec zzec, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzec.versionCode);
        zzc.zza(parcel, 2, zzec.zzyT);
        zzc.zza(parcel, 3, zzec.extras, false);
        zzc.zzc(parcel, 4, zzec.zzyU);
        zzc.zzb(parcel, 5, zzec.zzyV, false);
        zzc.zza(parcel, 6, zzec.zzyW);
        zzc.zzc(parcel, 7, zzec.zzyX);
        zzc.zza(parcel, 8, zzec.zzyY);
        zzc.zza(parcel, 9, zzec.zzyZ, false);
        zzc.zza(parcel, 10, (Parcelable) zzec.zzza, i, false);
        zzc.zza(parcel, 11, (Parcelable) zzec.zzzb, i, false);
        zzc.zza(parcel, 12, zzec.zzzc, false);
        zzc.zza(parcel, 13, zzec.zzzd, false);
        zzc.zza(parcel, 14, zzec.zzze, false);
        zzc.zzb(parcel, 15, zzec.zzzf, false);
        zzc.zza(parcel, 16, zzec.zzzg, false);
        zzc.zza(parcel, 17, zzec.zzzh, false);
        zzc.zza(parcel, 18, zzec.zzzi);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zze */
    public zzec createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        Bundle bundle = null;
        ArrayList<String> arrayList = null;
        String str = null;
        zzfp zzfp = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        Bundle bundle3 = null;
        ArrayList<String> arrayList2 = null;
        String str3 = null;
        String str4 = null;
        long j = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel2, zzaX);
                    break;
                case 2:
                    j = zzb.zzi(parcel2, zzaX);
                    break;
                case 3:
                    bundle = zzb.zzs(parcel2, zzaX);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel2, zzaX);
                    break;
                case 5:
                    arrayList = zzb.zzE(parcel2, zzaX);
                    break;
                case 6:
                    z = zzb.zzc(parcel2, zzaX);
                    break;
                case 7:
                    i3 = zzb.zzg(parcel2, zzaX);
                    break;
                case 8:
                    z2 = zzb.zzc(parcel2, zzaX);
                    break;
                case 9:
                    str = zzb.zzq(parcel2, zzaX);
                    break;
                case 10:
                    zzfp = (zzfp) zzb.zza(parcel2, zzaX, zzfp.CREATOR);
                    break;
                case 11:
                    location = (Location) zzb.zza(parcel2, zzaX, Location.CREATOR);
                    break;
                case 12:
                    str2 = zzb.zzq(parcel2, zzaX);
                    break;
                case 13:
                    bundle2 = zzb.zzs(parcel2, zzaX);
                    break;
                case 14:
                    bundle3 = zzb.zzs(parcel2, zzaX);
                    break;
                case 15:
                    arrayList2 = zzb.zzE(parcel2, zzaX);
                    break;
                case 16:
                    str3 = zzb.zzq(parcel2, zzaX);
                    break;
                case 17:
                    str4 = zzb.zzq(parcel2, zzaX);
                    break;
                case 18:
                    z3 = zzb.zzc(parcel2, zzaX);
                    break;
                default:
                    zzb.zzb(parcel2, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzec(i, j, bundle, i2, arrayList, z, i3, z2, str, zzfp, location, str2, bundle2, bundle3, arrayList2, str3, str4, z3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel2);
    }

    /* renamed from: zzv */
    public zzec[] newArray(int i) {
        return new zzec[i];
    }
}
