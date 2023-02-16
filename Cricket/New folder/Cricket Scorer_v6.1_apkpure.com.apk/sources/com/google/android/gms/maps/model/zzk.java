package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.List;

public class zzk implements Parcelable.Creator<PolygonOptions> {
    static void zza(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 2, polygonOptions.getPoints(), false);
        zzc.zzd(parcel, 3, polygonOptions.zzJP(), false);
        zzc.zza(parcel, 4, polygonOptions.getStrokeWidth());
        zzc.zzc(parcel, 5, polygonOptions.getStrokeColor());
        zzc.zzc(parcel, 6, polygonOptions.getFillColor());
        zzc.zza(parcel, 7, polygonOptions.getZIndex());
        zzc.zza(parcel, 8, polygonOptions.isVisible());
        zzc.zza(parcel, 9, polygonOptions.isGeodesic());
        zzc.zza(parcel, 10, polygonOptions.isClickable());
        zzc.zzc(parcel, 11, polygonOptions.getStrokeJointType());
        zzc.zzc(parcel, 12, polygonOptions.getStrokePattern(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhH */
    public PolygonOptions createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList<LatLng> arrayList2 = null;
        float f = 0.0f;
        ArrayList<PatternItem> arrayList3 = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = 0;
        float f2 = 0.0f;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    arrayList2 = zzb.zzc(parcel, zzaX, LatLng.CREATOR);
                    break;
                case 3:
                    zzb.zza(parcel, zzaX, (List) arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    f2 = zzb.zzl(parcel, zzaX);
                    break;
                case 5:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 6:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 7:
                    f = zzb.zzl(parcel, zzaX);
                    break;
                case 8:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 9:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 10:
                    z3 = zzb.zzc(parcel, zzaX);
                    break;
                case 11:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 12:
                    arrayList3 = zzb.zzc(parcel, zzaX, PatternItem.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new PolygonOptions(arrayList2, arrayList, f2, i, i2, f, z, z2, z3, i3, arrayList3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzlp */
    public PolygonOptions[] newArray(int i) {
        return new PolygonOptions[i];
    }
}
