package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public class zzsl implements Parcelable {
    @Deprecated
    public static final Parcelable.Creator<zzsl> CREATOR = new Parcelable.Creator<zzsl>() {
        @Deprecated
        /* renamed from: zzaH */
        public zzsl[] newArray(int i) {
            return new zzsl[i];
        }

        @Deprecated
        /* renamed from: zzx */
        public zzsl createFromParcel(Parcel parcel) {
            return new zzsl(parcel);
        }
    };
    private String mValue;
    private String zzGV;
    private String zzaeU;

    @Deprecated
    public zzsl() {
    }

    @Deprecated
    zzsl(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Deprecated
    private void readFromParcel(Parcel parcel) {
        this.zzGV = parcel.readString();
        this.zzaeU = parcel.readString();
        this.mValue = parcel.readString();
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.zzGV;
    }

    public String getValue() {
        return this.mValue;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzGV);
        parcel.writeString(this.zzaeU);
        parcel.writeString(this.mValue);
    }
}
