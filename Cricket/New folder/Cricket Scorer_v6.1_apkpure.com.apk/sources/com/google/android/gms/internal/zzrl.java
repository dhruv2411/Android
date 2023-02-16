package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzf;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

public final class zzrl extends zzf<zzrl> {
    private String mName;
    private String zzFV;
    private String zzGV;
    private String zzacO;
    private String zzacP;
    private String zzacQ;
    private String zzacR;
    private String zzacS;
    private String zzacT;
    private String zzacU;

    public String getContent() {
        return this.zzFV;
    }

    public String getId() {
        return this.zzGV;
    }

    public String getName() {
        return this.mName;
    }

    public String getSource() {
        return this.zzacO;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.mName);
        hashMap.put("source", this.zzacO);
        hashMap.put(FirebaseAnalytics.Param.MEDIUM, this.zzacP);
        hashMap.put("keyword", this.zzacQ);
        hashMap.put("content", this.zzFV);
        hashMap.put(TtmlNode.ATTR_ID, this.zzGV);
        hashMap.put("adNetworkId", this.zzacR);
        hashMap.put("gclid", this.zzacS);
        hashMap.put("dclid", this.zzacT);
        hashMap.put(FirebaseAnalytics.Param.ACLID, this.zzacU);
        return zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzrl zzrl) {
        if (!TextUtils.isEmpty(this.mName)) {
            zzrl.setName(this.mName);
        }
        if (!TextUtils.isEmpty(this.zzacO)) {
            zzrl.zzbr(this.zzacO);
        }
        if (!TextUtils.isEmpty(this.zzacP)) {
            zzrl.zzbs(this.zzacP);
        }
        if (!TextUtils.isEmpty(this.zzacQ)) {
            zzrl.zzbt(this.zzacQ);
        }
        if (!TextUtils.isEmpty(this.zzFV)) {
            zzrl.zzbu(this.zzFV);
        }
        if (!TextUtils.isEmpty(this.zzGV)) {
            zzrl.zzbv(this.zzGV);
        }
        if (!TextUtils.isEmpty(this.zzacR)) {
            zzrl.zzbw(this.zzacR);
        }
        if (!TextUtils.isEmpty(this.zzacS)) {
            zzrl.zzbx(this.zzacS);
        }
        if (!TextUtils.isEmpty(this.zzacT)) {
            zzrl.zzby(this.zzacT);
        }
        if (!TextUtils.isEmpty(this.zzacU)) {
            zzrl.zzbz(this.zzacU);
        }
    }

    public void zzbr(String str) {
        this.zzacO = str;
    }

    public void zzbs(String str) {
        this.zzacP = str;
    }

    public void zzbt(String str) {
        this.zzacQ = str;
    }

    public void zzbu(String str) {
        this.zzFV = str;
    }

    public void zzbv(String str) {
        this.zzGV = str;
    }

    public void zzbw(String str) {
        this.zzacR = str;
    }

    public void zzbx(String str) {
        this.zzacS = str;
    }

    public void zzby(String str) {
        this.zzacT = str;
    }

    public void zzbz(String str) {
        this.zzacU = str;
    }

    public String zznb() {
        return this.zzacP;
    }

    public String zznc() {
        return this.zzacQ;
    }

    public String zznd() {
        return this.zzacR;
    }

    public String zzne() {
        return this.zzacS;
    }

    public String zznf() {
        return this.zzacT;
    }

    public String zzng() {
        return this.zzacU;
    }
}
