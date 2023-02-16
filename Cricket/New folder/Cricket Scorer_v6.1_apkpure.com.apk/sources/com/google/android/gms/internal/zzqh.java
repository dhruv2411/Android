package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzme
public final class zzqh extends zza {
    public static final Parcelable.Creator<zzqh> CREATOR = new zzqi();
    public int zzYW;
    public int zzYX;
    public boolean zzYY;
    public boolean zzYZ;
    public String zzba;

    public zzqh(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzqh(int i, int i2, boolean z, boolean z2) {
        this(i, i2, z, false, z2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzqh(int r10, int r11, boolean r12, boolean r13, boolean r14) {
        /*
            r9 = this;
            java.lang.String r0 = "afma-sdk-a-v"
            java.lang.String r0 = java.lang.String.valueOf(r0)
            if (r12 == 0) goto L_0x000b
            java.lang.String r13 = "0"
            goto L_0x0012
        L_0x000b:
            if (r13 == 0) goto L_0x0010
            java.lang.String r13 = "2"
            goto L_0x0012
        L_0x0010:
            java.lang.String r13 = "1"
        L_0x0012:
            r1 = 24
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r1 = r1 + r2
            java.lang.String r2 = java.lang.String.valueOf(r13)
            int r2 = r2.length()
            int r1 = r1 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            r2.append(r0)
            r2.append(r10)
            java.lang.String r0 = "."
            r2.append(r0)
            r2.append(r11)
            java.lang.String r0 = "."
            r2.append(r0)
            r2.append(r13)
            java.lang.String r4 = r2.toString()
            r3 = r9
            r5 = r10
            r6 = r11
            r7 = r12
            r8 = r14
            r3.<init>((java.lang.String) r4, (int) r5, (int) r6, (boolean) r7, (boolean) r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqh.<init>(int, int, boolean, boolean, boolean):void");
    }

    zzqh(String str, int i, int i2, boolean z, boolean z2) {
        this.zzba = str;
        this.zzYW = i;
        this.zzYX = i2;
        this.zzYY = z;
        this.zzYZ = z2;
    }

    public static zzqh zzlk() {
        return new zzqh(10298208, 10298208, true);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzqi.zza(this, parcel, i);
    }
}
