package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;

public class zzauq extends zza {
    public static final Parcelable.Creator<zzauq> CREATOR = new zzaur();
    public final String name;
    public final int versionCode;
    public final String zzaGV;
    public final String zzbqV;
    public final long zzbwc;
    public final Long zzbwd;
    public final Float zzbwe;
    public final Double zzbwf;

    zzauq(int i, String str, long j, Long l, Float f, String str2, String str3, Double d) {
        this.versionCode = i;
        this.name = str;
        this.zzbwc = j;
        this.zzbwd = l;
        Double d2 = null;
        this.zzbwe = null;
        if (i == 1) {
            this.zzbwf = f != null ? Double.valueOf(f.doubleValue()) : d2;
        } else {
            this.zzbwf = d;
        }
        this.zzaGV = str2;
        this.zzbqV = str3;
    }

    zzauq(zzaus zzaus) {
        this(zzaus.mName, zzaus.zzbwg, zzaus.mValue, zzaus.mOrigin);
    }

    zzauq(String str, long j, Object obj, String str2) {
        zzac.zzdr(str);
        this.versionCode = 2;
        this.name = str;
        this.zzbwc = j;
        this.zzbqV = str2;
        if (obj == null) {
            this.zzbwd = null;
        } else if (obj instanceof Long) {
            this.zzbwd = (Long) obj;
        } else if (obj instanceof String) {
            this.zzbwd = null;
            this.zzbwe = null;
            this.zzbwf = null;
            this.zzaGV = (String) obj;
            return;
        } else if (obj instanceof Double) {
            this.zzbwd = null;
            this.zzbwe = null;
            this.zzbwf = (Double) obj;
            this.zzaGV = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
        this.zzbwe = null;
        this.zzbwf = null;
        this.zzaGV = null;
    }

    public Object getValue() {
        if (this.zzbwd != null) {
            return this.zzbwd;
        }
        if (this.zzbwf != null) {
            return this.zzbwf;
        }
        if (this.zzaGV != null) {
            return this.zzaGV;
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaur.zza(this, parcel, i);
    }
}
