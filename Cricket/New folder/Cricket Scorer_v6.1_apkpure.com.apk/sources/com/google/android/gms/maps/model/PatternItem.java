package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PatternItem extends zza {
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzi();
    private static final String TAG = "PatternItem";
    private final int type;
    @Nullable
    private final Float zzbpJ;

    public PatternItem(int i, @Nullable Float f) {
        boolean z = true;
        if (i != 1 && (f == null || f.floatValue() < 0.0f)) {
            z = false;
        }
        String valueOf = String.valueOf(f);
        StringBuilder sb = new StringBuilder(45 + String.valueOf(valueOf).length());
        sb.append("Invalid PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        zzac.zzb(z, (Object) sb.toString());
        this.type = i;
        this.zzbpJ = f;
    }

    @Nullable
    static List<PatternItem> zzI(@Nullable List<PatternItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<PatternItem> it = list.iterator();
        while (it.hasNext()) {
            PatternItem next = it.next();
            arrayList.add(next == null ? null : next.zzJO());
        }
        return arrayList;
    }

    private PatternItem zzJO() {
        switch (this.type) {
            case 0:
                return new Dash(this.zzbpJ.floatValue());
            case 1:
                return new Dot();
            case 2:
                return new Gap(this.zzbpJ.floatValue());
            default:
                String str = TAG;
                int i = this.type;
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unknown PatternItem type: ");
                sb.append(i);
                Log.w(str, sb.toString());
                return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.type == patternItem.type && zzaa.equal(this.zzbpJ, patternItem.zzbpJ);
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.type), this.zzbpJ);
    }

    public String toString() {
        int i = this.type;
        String valueOf = String.valueOf(this.zzbpJ);
        StringBuilder sb = new StringBuilder(39 + String.valueOf(valueOf).length());
        sb.append("[PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }

    @Nullable
    public Float zzJN() {
        return this.zzbpJ;
    }
}
