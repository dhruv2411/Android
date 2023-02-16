package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;

public class zzatm {
    final String mAppId;
    final String mName;
    final String mOrigin;
    final long zzaxb;
    final long zzbry;
    final zzato zzbrz;

    zzatm(zzaue zzaue, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzac.zzdr(str2);
        zzac.zzdr(str3);
        this.mAppId = str2;
        this.mName = str3;
        this.mOrigin = TextUtils.isEmpty(str) ? null : str;
        this.zzaxb = j;
        this.zzbry = j2;
        if (this.zzbry != 0 && this.zzbry > this.zzaxb) {
            zzaue.zzKl().zzMa().zzj("Event created with reverse previous/current timestamps. appId", zzatx.zzfE(str2));
        }
        this.zzbrz = zza(zzaue, bundle);
    }

    private zzatm(zzaue zzaue, String str, String str2, String str3, long j, long j2, zzato zzato) {
        zzac.zzdr(str2);
        zzac.zzdr(str3);
        zzac.zzw(zzato);
        this.mAppId = str2;
        this.mName = str3;
        this.mOrigin = TextUtils.isEmpty(str) ? null : str;
        this.zzaxb = j;
        this.zzbry = j2;
        if (this.zzbry != 0 && this.zzbry > this.zzaxb) {
            zzaue.zzKl().zzMa().zzj("Event created with reverse previous/current timestamps. appId", zzatx.zzfE(str2));
        }
        this.zzbrz = zzato;
    }

    static zzato zza(zzaue zzaue, Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return new zzato(new Bundle());
        }
        Bundle bundle2 = new Bundle(bundle);
        Iterator it = bundle2.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                zzaue.zzKl().zzLY().log("Param name can't be null");
            } else {
                Object zzl = zzaue.zzKh().zzl(str, bundle2.get(str));
                if (zzl == null) {
                    zzaue.zzKl().zzMa().zzj("Param value can't be null", str);
                } else {
                    zzaue.zzKh().zza(bundle2, str, zzl);
                }
            }
            it.remove();
        }
        return new zzato(bundle2);
    }

    public String toString() {
        String str = this.mAppId;
        String str2 = this.mName;
        String valueOf = String.valueOf(this.zzbrz);
        StringBuilder sb = new StringBuilder(33 + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("Event{appId='");
        sb.append(str);
        sb.append("'");
        sb.append(", name='");
        sb.append(str2);
        sb.append("'");
        sb.append(", params=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public zzatm zza(zzaue zzaue, long j) {
        return new zzatm(zzaue, this.mOrigin, this.mAppId, this.mName, this.zzaxb, j, this.zzbrz);
    }
}
