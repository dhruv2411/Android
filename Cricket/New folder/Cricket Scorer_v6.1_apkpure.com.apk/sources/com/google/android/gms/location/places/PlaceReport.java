package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;

public class PlaceReport extends zza implements ReflectedParcelable {
    public static final Parcelable.Creator<PlaceReport> CREATOR = new zzl();
    private final String mTag;
    private final String zzacO;
    final int zzaiI;
    private final String zzblE;

    PlaceReport(int i, String str, String str2, String str3) {
        this.zzaiI = i;
        this.zzblE = str;
        this.mTag = str2;
        this.zzacO = str3;
    }

    public static PlaceReport create(String str, String str2) {
        return zzj(str, str2, "unknown");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zzeU(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = 1
            r2 = 0
            switch(r0) {
                case -1436706272: goto L_0x003c;
                case -1194968642: goto L_0x0032;
                case -284840886: goto L_0x0028;
                case -262743844: goto L_0x001e;
                case 1164924125: goto L_0x0014;
                case 1287171955: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0046
        L_0x000a:
            java.lang.String r0 = "inferredRadioSignals"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 3
            goto L_0x0047
        L_0x0014:
            java.lang.String r0 = "inferredSnappedToRoad"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 5
            goto L_0x0047
        L_0x001e:
            java.lang.String r0 = "inferredReverseGeocoding"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 4
            goto L_0x0047
        L_0x0028:
            java.lang.String r0 = "unknown"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = r2
            goto L_0x0047
        L_0x0032:
            java.lang.String r0 = "userReported"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = r1
            goto L_0x0047
        L_0x003c:
            java.lang.String r0 = "inferredGeofencing"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 2
            goto L_0x0047
        L_0x0046:
            r3 = -1
        L_0x0047:
            switch(r3) {
                case 0: goto L_0x004b;
                case 1: goto L_0x004b;
                case 2: goto L_0x004b;
                case 3: goto L_0x004b;
                case 4: goto L_0x004b;
                case 5: goto L_0x004b;
                default: goto L_0x004a;
            }
        L_0x004a:
            return r2
        L_0x004b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.places.PlaceReport.zzeU(java.lang.String):boolean");
    }

    public static PlaceReport zzj(String str, String str2, String str3) {
        zzac.zzw(str);
        zzac.zzdr(str2);
        zzac.zzdr(str3);
        zzac.zzb(zzeU(str3), (Object) "Invalid source");
        return new PlaceReport(1, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzaa.equal(this.zzblE, placeReport.zzblE) && zzaa.equal(this.mTag, placeReport.mTag) && zzaa.equal(this.zzacO, placeReport.zzacO);
    }

    public String getPlaceId() {
        return this.zzblE;
    }

    public String getSource() {
        return this.zzacO;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return zzaa.hashCode(this.zzblE, this.mTag, this.zzacO);
    }

    public String toString() {
        zzaa.zza zzv = zzaa.zzv(this);
        zzv.zzg("placeId", this.zzblE);
        zzv.zzg("tag", this.mTag);
        if (!"unknown".equals(this.zzacO)) {
            zzv.zzg("source", this.zzacO);
        }
        return zzv.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzl.zza(this, parcel, i);
    }
}
