package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzf;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

public final class zzrr extends zzf<zzrr> {
    private String mCategory;
    private String zzadb;
    private String zzadc;
    private long zzadd;

    public String getAction() {
        return this.zzadb;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public String getLabel() {
        return this.zzadc;
    }

    public long getValue() {
        return this.zzadd;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("category", this.mCategory);
        hashMap.put("action", this.zzadb);
        hashMap.put("label", this.zzadc);
        hashMap.put(FirebaseAnalytics.Param.VALUE, Long.valueOf(this.zzadd));
        return zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzrr zzrr) {
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzrr.zzbB(this.mCategory);
        }
        if (!TextUtils.isEmpty(this.zzadb)) {
            zzrr.zzbC(this.zzadb);
        }
        if (!TextUtils.isEmpty(this.zzadc)) {
            zzrr.zzbD(this.zzadc);
        }
        if (this.zzadd != 0) {
            zzrr.zzr(this.zzadd);
        }
    }

    public void zzbB(String str) {
        this.mCategory = str;
    }

    public void zzbC(String str) {
        this.zzadb = str;
    }

    public void zzbD(String str) {
        this.zzadc = str;
    }

    public void zzr(long j) {
        this.zzadd = j;
    }
}
