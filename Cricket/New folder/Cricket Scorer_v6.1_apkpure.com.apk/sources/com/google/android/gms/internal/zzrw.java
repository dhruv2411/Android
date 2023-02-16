package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzf;
import java.util.HashMap;

public final class zzrw extends zzf<zzrw> {
    public String mCategory;
    public String zzadc;
    public String zzadx;
    public long zzady;

    public String getCategory() {
        return this.mCategory;
    }

    public String getLabel() {
        return this.zzadc;
    }

    public long getTimeInMillis() {
        return this.zzady;
    }

    public void setTimeInMillis(long j) {
        this.zzady = j;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("variableName", this.zzadx);
        hashMap.put("timeInMillis", Long.valueOf(this.zzady));
        hashMap.put("category", this.mCategory);
        hashMap.put("label", this.zzadc);
        return zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzrw zzrw) {
        if (!TextUtils.isEmpty(this.zzadx)) {
            zzrw.zzbL(this.zzadx);
        }
        if (this.zzady != 0) {
            zzrw.setTimeInMillis(this.zzady);
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzrw.zzbB(this.mCategory);
        }
        if (!TextUtils.isEmpty(this.zzadc)) {
            zzrw.zzbD(this.zzadc);
        }
    }

    public void zzbB(String str) {
        this.mCategory = str;
    }

    public void zzbD(String str) {
        this.zzadc = str;
    }

    public void zzbL(String str) {
        this.zzadx = str;
    }

    public String zznF() {
        return this.zzadx;
    }
}
