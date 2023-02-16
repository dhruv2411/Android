package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzaf;

public class zzbax implements Parcelable.Creator<zzbaw> {
    static void zza(zzbaw zzbaw, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzbaw.zzaiI);
        zzc.zza(parcel, 2, (Parcelable) zzbaw.zzyh(), i, false);
        zzc.zza(parcel, 3, (Parcelable) zzbaw.zzPU(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzjy */
    public zzbaw createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        ConnectionResult connectionResult = null;
        int i = 0;
        zzaf zzaf = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    connectionResult = (ConnectionResult) zzb.zza(parcel, zzaX, ConnectionResult.CREATOR);
                    break;
                case 3:
                    zzaf = (zzaf) zzb.zza(parcel, zzaX, zzaf.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzbaw(i, connectionResult, zzaf);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzny */
    public zzbaw[] newArray(int i) {
        return new zzbaw[i];
    }
}
