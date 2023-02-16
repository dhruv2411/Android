package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzacw;
import java.util.ArrayList;

public class zzacx implements Parcelable.Creator<zzacw> {
    static void zza(zzacw zzacw, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzacw.zzaiI);
        zzc.zzc(parcel, 2, zzacw.zzyE(), false);
        zzc.zza(parcel, 3, zzacw.zzyF(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzbg */
    public zzacw createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        ArrayList<zzacw.zza> arrayList = null;
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    arrayList = zzb.zzc(parcel, zzaX, zzacw.zza.CREATOR);
                    break;
                case 3:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzacw(i, arrayList, str);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzdk */
    public zzacw[] newArray(int i) {
        return new zzacw[i];
    }
}
