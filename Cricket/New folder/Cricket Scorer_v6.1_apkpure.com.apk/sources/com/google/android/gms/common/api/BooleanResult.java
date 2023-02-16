package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzac;
import com.itextpdf.text.pdf.codec.wmf.MetaDo;

public class BooleanResult implements Result {
    private final Status zzair;
    private final boolean zzayS;

    public BooleanResult(Status status, boolean z) {
        this.zzair = (Status) zzac.zzb(status, (Object) "Status must not be null");
        this.zzayS = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult booleanResult = (BooleanResult) obj;
        return this.zzair.equals(booleanResult.zzair) && this.zzayS == booleanResult.zzayS;
    }

    public Status getStatus() {
        return this.zzair;
    }

    public boolean getValue() {
        return this.zzayS;
    }

    public final int hashCode() {
        return (31 * (MetaDo.META_OFFSETWINDOWORG + this.zzair.hashCode())) + (this.zzayS ? 1 : 0);
    }
}
