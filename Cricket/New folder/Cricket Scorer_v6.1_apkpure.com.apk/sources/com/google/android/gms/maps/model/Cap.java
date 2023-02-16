package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.IObjectWrapper;

public class Cap extends zza {
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();
    private static final String TAG = "Cap";
    @Nullable
    private final BitmapDescriptor bitmapDescriptor;
    private final int type;
    @Nullable
    private final Float zzbpe;

    protected Cap(int i) {
        this(i, (BitmapDescriptor) null, (Float) null);
    }

    Cap(int i, @Nullable IBinder iBinder, @Nullable Float f) {
        this(i, zzeh(iBinder), f);
    }

    private Cap(int i, @Nullable BitmapDescriptor bitmapDescriptor2, @Nullable Float f) {
        boolean z = false;
        boolean z2 = f != null && f.floatValue() > 0.0f;
        if (i != 3 || (bitmapDescriptor2 != null && z2)) {
            z = true;
        }
        String valueOf = String.valueOf(bitmapDescriptor2);
        String valueOf2 = String.valueOf(f);
        StringBuilder sb = new StringBuilder(63 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb.append("Invalid Cap: type=");
        sb.append(i);
        sb.append(" bitmapDescriptor=");
        sb.append(valueOf);
        sb.append(" bitmapRefWidth=");
        sb.append(valueOf2);
        zzac.zzb(z, (Object) sb.toString());
        this.type = i;
        this.bitmapDescriptor = bitmapDescriptor2;
        this.zzbpe = f;
    }

    protected Cap(@NonNull BitmapDescriptor bitmapDescriptor2, float f) {
        this(3, bitmapDescriptor2, Float.valueOf(f));
    }

    @Nullable
    private static BitmapDescriptor zzeh(@Nullable IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        return new BitmapDescriptor(IObjectWrapper.zza.zzcd(iBinder));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.type == cap.type && zzaa.equal(this.bitmapDescriptor, cap.bitmapDescriptor) && zzaa.equal(this.zzbpe, cap.zzbpe);
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.type), this.bitmapDescriptor, this.zzbpe);
    }

    public String toString() {
        int i = this.type;
        StringBuilder sb = new StringBuilder(23);
        sb.append("[Cap: type=");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    @Nullable
    public Float zzJH() {
        return this.zzbpe;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public IBinder zzJI() {
        if (this.bitmapDescriptor == null) {
            return null;
        }
        return this.bitmapDescriptor.zzJm().asBinder();
    }

    /* access modifiers changed from: package-private */
    public Cap zzJJ() {
        switch (this.type) {
            case 0:
                return new ButtCap();
            case 1:
                return new SquareCap();
            case 2:
                return new RoundCap();
            case 3:
                return new CustomCap(this.bitmapDescriptor, this.zzbpe.floatValue());
            default:
                String str = TAG;
                int i = this.type;
                StringBuilder sb = new StringBuilder(29);
                sb.append("Unknown Cap type: ");
                sb.append(i);
                Log.w(str, sb.toString());
                return this;
        }
    }
}
