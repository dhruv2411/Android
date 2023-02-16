package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzzn implements Parcelable.Creator<zzzm> {
    static void zza(zzzm zzzm, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) zzzm.zzaxI, i, false);
        zzc.zza(parcel, 3, zzzm.zzaxJ, false);
        zzc.zza(parcel, 4, zzzm.zzaxK, false);
        zzc.zza(parcel, 5, zzzm.zzaxL, false);
        zzc.zza(parcel, 6, zzzm.zzaxM, false);
        zzc.zza(parcel, 7, zzzm.zzaxN, false);
        zzc.zza(parcel, 8, zzzm.zzaxO);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaH */
    public zzzm createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        zzzu zzzu = null;
        byte[] bArr = null;
        int[] iArr = null;
        String[] strArr = null;
        int[] iArr2 = null;
        byte[][] bArr2 = null;
        boolean z = true;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    zzzu = (zzzu) zzb.zza(parcel, zzaX, zzzu.CREATOR);
                    break;
                case 3:
                    bArr = zzb.zzt(parcel, zzaX);
                    break;
                case 4:
                    iArr = zzb.zzw(parcel, zzaX);
                    break;
                case 5:
                    strArr = zzb.zzC(parcel, zzaX);
                    break;
                case 6:
                    iArr2 = zzb.zzw(parcel, zzaX);
                    break;
                case 7:
                    bArr2 = zzb.zzu(parcel, zzaX);
                    break;
                case 8:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzzm(zzzu, bArr, iArr, strArr, iArr2, bArr2, z);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzcs */
    public zzzm[] newArray(int i) {
        return new zzzm[i];
    }
}
