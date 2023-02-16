package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzf;
import com.google.android.gms.common.internal.zzac;
import java.util.HashMap;

public final class zzrt extends zzf<zzrt> {
    private String zzadg;
    private String zzadh;
    private String zzadi;
    private String zzadj;
    private boolean zzadk;
    private String zzadl;
    private boolean zzadm;
    private double zzadn;

    public String getUserId() {
        return this.zzadi;
    }

    public void setClientId(String str) {
        this.zzadh = str;
    }

    public void setSampleRate(double d) {
        zzac.zzb(d >= 0.0d && d <= 100.0d, (Object) "Sample rate must be between 0% and 100%");
        this.zzadn = d;
    }

    public void setUserId(String str) {
        this.zzadi = str;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("hitType", this.zzadg);
        hashMap.put("clientId", this.zzadh);
        hashMap.put("userId", this.zzadi);
        hashMap.put("androidAdId", this.zzadj);
        hashMap.put("AdTargetingEnabled", Boolean.valueOf(this.zzadk));
        hashMap.put("sessionControl", this.zzadl);
        hashMap.put("nonInteraction", Boolean.valueOf(this.zzadm));
        hashMap.put("sampleRate", Double.valueOf(this.zzadn));
        return zzj(hashMap);
    }

    public void zzS(boolean z) {
        this.zzadk = z;
    }

    public void zzT(boolean z) {
        this.zzadm = z;
    }

    /* renamed from: zza */
    public void zzb(zzrt zzrt) {
        if (!TextUtils.isEmpty(this.zzadg)) {
            zzrt.zzbE(this.zzadg);
        }
        if (!TextUtils.isEmpty(this.zzadh)) {
            zzrt.setClientId(this.zzadh);
        }
        if (!TextUtils.isEmpty(this.zzadi)) {
            zzrt.setUserId(this.zzadi);
        }
        if (!TextUtils.isEmpty(this.zzadj)) {
            zzrt.zzbF(this.zzadj);
        }
        if (this.zzadk) {
            zzrt.zzS(true);
        }
        if (!TextUtils.isEmpty(this.zzadl)) {
            zzrt.zzbG(this.zzadl);
        }
        if (this.zzadm) {
            zzrt.zzT(this.zzadm);
        }
        if (this.zzadn != 0.0d) {
            zzrt.setSampleRate(this.zzadn);
        }
    }

    public void zzbE(String str) {
        this.zzadg = str;
    }

    public void zzbF(String str) {
        this.zzadj = str;
    }

    public void zzbG(String str) {
        this.zzadl = str;
    }

    public String zzmy() {
        return this.zzadh;
    }

    public String zznu() {
        return this.zzadg;
    }

    public String zznv() {
        return this.zzadj;
    }

    public boolean zznw() {
        return this.zzadk;
    }

    public String zznx() {
        return this.zzadl;
    }

    public boolean zzny() {
        return this.zzadm;
    }

    public double zznz() {
        return this.zzadn;
    }
}
