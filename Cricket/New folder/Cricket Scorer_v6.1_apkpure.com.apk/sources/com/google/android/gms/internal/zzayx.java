package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class zzayx extends zza implements Comparable<zzayx> {
    public static final Parcelable.Creator<zzayx> CREATOR = new zzayy();
    public final String[] zzbBA;
    public final Map<String, zzayz> zzbBB = new TreeMap();
    public final int zzbBy;
    public final zzayz[] zzbBz;

    public zzayx(int i, zzayz[] zzayzArr, String[] strArr) {
        this.zzbBy = i;
        this.zzbBz = zzayzArr;
        for (zzayz zzayz : zzayzArr) {
            this.zzbBB.put(zzayz.name, zzayz);
        }
        this.zzbBA = strArr;
        if (this.zzbBA != null) {
            Arrays.sort(this.zzbBA);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzayx)) {
            return false;
        }
        zzayx zzayx = (zzayx) obj;
        return this.zzbBy == zzayx.zzbBy && zzaa.equal(this.zzbBB, zzayx.zzbBB) && Arrays.equals(this.zzbBA, zzayx.zzbBA);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.zzbBy);
        sb.append(", ");
        sb.append("(");
        for (zzayz append : this.zzbBB.values()) {
            sb.append(append);
            sb.append(", ");
        }
        sb.append(")");
        sb.append(", ");
        sb.append("(");
        if (this.zzbBA != null) {
            for (String append2 : this.zzbBA) {
                sb.append(append2);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzayy.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(zzayx zzayx) {
        return this.zzbBy - zzayx.zzbBy;
    }
}
