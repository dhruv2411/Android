package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;

public final class zzatg extends zza {
    public static final Parcelable.Creator<zzatg> CREATOR = new zzath();
    public String packageName;
    public final int versionCode;
    public String zzbqV;
    public zzauq zzbqW;
    public long zzbqX;
    public boolean zzbqY;
    public String zzbqZ;
    public zzatq zzbra;
    public long zzbrb;
    public zzatq zzbrc;
    public long zzbrd;
    public zzatq zzbre;

    zzatg(int i, String str, String str2, zzauq zzauq, long j, boolean z, String str3, zzatq zzatq, long j2, zzatq zzatq2, long j3, zzatq zzatq3) {
        this.versionCode = i;
        this.packageName = str;
        this.zzbqV = str2;
        this.zzbqW = zzauq;
        this.zzbqX = j;
        this.zzbqY = z;
        this.zzbqZ = str3;
        this.zzbra = zzatq;
        this.zzbrb = j2;
        this.zzbrc = zzatq2;
        this.zzbrd = j3;
        this.zzbre = zzatq3;
    }

    zzatg(zzatg zzatg) {
        this.versionCode = 1;
        zzac.zzw(zzatg);
        this.packageName = zzatg.packageName;
        this.zzbqV = zzatg.zzbqV;
        this.zzbqW = zzatg.zzbqW;
        this.zzbqX = zzatg.zzbqX;
        this.zzbqY = zzatg.zzbqY;
        this.zzbqZ = zzatg.zzbqZ;
        this.zzbra = zzatg.zzbra;
        this.zzbrb = zzatg.zzbrb;
        this.zzbrc = zzatg.zzbrc;
        this.zzbrd = zzatg.zzbrd;
        this.zzbre = zzatg.zzbre;
    }

    zzatg(String str, String str2, zzauq zzauq, long j, boolean z, String str3, zzatq zzatq, long j2, zzatq zzatq2, long j3, zzatq zzatq3) {
        this.versionCode = 1;
        this.packageName = str;
        this.zzbqV = str2;
        this.zzbqW = zzauq;
        this.zzbqX = j;
        this.zzbqY = z;
        this.zzbqZ = str3;
        this.zzbra = zzatq;
        this.zzbrb = j2;
        this.zzbrc = zzatq2;
        this.zzbrd = j3;
        this.zzbre = zzatq3;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzath.zza(this, parcel, i);
    }
}
