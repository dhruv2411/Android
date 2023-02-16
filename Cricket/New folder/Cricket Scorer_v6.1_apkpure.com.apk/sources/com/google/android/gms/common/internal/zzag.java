package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzag implements Parcelable.Creator<zzaf> {
    static void zza(zzaf zzaf, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzaf.zzaiI);
        zzc.zza(parcel, 2, zzaf.zzaEW, false);
        zzc.zza(parcel, 3, (Parcelable) zzaf.zzyh(), i, false);
        zzc.zza(parcel, 4, zzaf.zzyi());
        zzc.zza(parcel, 5, zzaf.zzyj());
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaU */
    public zzaf createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        IBinder iBinder = null;
        ConnectionResult connectionResult = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    iBinder = zzb.zzr(parcel, zzaX);
                    break;
                case 3:
                    connectionResult = (ConnectionResult) zzb.zza(parcel, zzaX, ConnectionResult.CREATOR);
                    break;
                case 4:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 5:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzaf(i, iBinder, connectionResult, z, z2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzcZ */
    public zzaf[] newArray(int i) {
        return new zzaf[i];
    }
}
