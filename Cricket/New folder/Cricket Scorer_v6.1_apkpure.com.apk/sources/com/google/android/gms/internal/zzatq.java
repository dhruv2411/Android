package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;

public final class zzatq extends zza {
    public static final Parcelable.Creator<zzatq> CREATOR = new zzatr();
    public final String name;
    public final String zzbqV;
    public final zzato zzbrG;
    public final long zzbrH;

    zzatq(zzatq zzatq, long j) {
        zzac.zzw(zzatq);
        this.name = zzatq.name;
        this.zzbrG = zzatq.zzbrG;
        this.zzbqV = zzatq.zzbqV;
        this.zzbrH = j;
    }

    public zzatq(String str, zzato zzato, String str2, long j) {
        this.name = str;
        this.zzbrG = zzato;
        this.zzbqV = str2;
        this.zzbrH = j;
    }

    public String toString() {
        String str = this.zzbqV;
        String str2 = this.name;
        String valueOf = String.valueOf(this.zzbrG);
        StringBuilder sb = new StringBuilder(21 + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("origin=");
        sb.append(str);
        sb.append(",name=");
        sb.append(str2);
        sb.append(",params=");
        sb.append(valueOf);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzatr.zza(this, parcel, i);
    }
}
