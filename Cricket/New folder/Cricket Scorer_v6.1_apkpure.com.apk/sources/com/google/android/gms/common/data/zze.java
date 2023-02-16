package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zze implements Parcelable.Creator<DataHolder> {
    static void zza(DataHolder dataHolder, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 1, dataHolder.zzxl(), false);
        zzc.zza(parcel, 2, (T[]) dataHolder.zzxm(), i, false);
        zzc.zzc(parcel, 3, dataHolder.getStatusCode());
        zzc.zza(parcel, 4, dataHolder.zzxf(), false);
        zzc.zzc(parcel, 1000, dataHolder.zzaiI);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaO */
    public DataHolder createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        int i2 = 0;
        String[] strArr = null;
        CursorWindow[] cursorWindowArr = null;
        Bundle bundle = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            int zzdc = zzb.zzdc(zzaX);
            if (zzdc != 1000) {
                switch (zzdc) {
                    case 1:
                        strArr = zzb.zzC(parcel, zzaX);
                        break;
                    case 2:
                        cursorWindowArr = (CursorWindow[]) zzb.zzb(parcel, zzaX, CursorWindow.CREATOR);
                        break;
                    case 3:
                        i2 = zzb.zzg(parcel, zzaX);
                        break;
                    case 4:
                        bundle = zzb.zzs(parcel, zzaX);
                        break;
                    default:
                        zzb.zzb(parcel, zzaX);
                        break;
                }
            } else {
                i = zzb.zzg(parcel, zzaX);
            }
        }
        if (parcel.dataPosition() != zzaY) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Overread allowed size end=");
            sb.append(zzaY);
            throw new zzb.zza(sb.toString(), parcel);
        }
        DataHolder dataHolder = new DataHolder(i, strArr, cursorWindowArr, i2, bundle);
        dataHolder.zzxk();
        return dataHolder;
    }

    /* renamed from: zzcL */
    public DataHolder[] newArray(int i) {
        return new DataHolder[i];
    }
}
