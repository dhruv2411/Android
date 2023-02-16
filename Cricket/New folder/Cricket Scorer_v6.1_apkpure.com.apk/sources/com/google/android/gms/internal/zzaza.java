package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzaza implements Parcelable.Creator<zzayz> {
    static void zza(zzayz zzayz, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzayz.name, false);
        zzc.zza(parcel, 3, zzayz.zzbBC);
        zzc.zza(parcel, 4, zzayz.zzbhn);
        zzc.zza(parcel, 5, zzayz.zzbhp);
        zzc.zza(parcel, 6, zzayz.zzaGV, false);
        zzc.zza(parcel, 7, zzayz.zzbBD, false);
        zzc.zzc(parcel, 8, zzayz.zzbBE);
        zzc.zzc(parcel, 9, zzayz.zzbBF);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzjb */
    public zzayz createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzaY = zzb.zzaY(parcel);
        boolean z = false;
        int i = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        long j = 0;
        double d = 0.0d;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel2, zzaX);
                    break;
                case 3:
                    j = zzb.zzi(parcel2, zzaX);
                    break;
                case 4:
                    z = zzb.zzc(parcel2, zzaX);
                    break;
                case 5:
                    d = zzb.zzn(parcel2, zzaX);
                    break;
                case 6:
                    str2 = zzb.zzq(parcel2, zzaX);
                    break;
                case 7:
                    bArr = zzb.zzt(parcel2, zzaX);
                    break;
                case 8:
                    i = zzb.zzg(parcel2, zzaX);
                    break;
                case 9:
                    i2 = zzb.zzg(parcel2, zzaX);
                    break;
                default:
                    zzb.zzb(parcel2, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzayz(str, j, z, d, str2, bArr, i, i2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel2);
    }

    /* renamed from: zzmY */
    public zzayz[] newArray(int i) {
        return new zzayz[i];
    }
}
