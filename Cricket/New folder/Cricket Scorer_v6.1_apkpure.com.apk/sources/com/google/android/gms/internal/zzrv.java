package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzf;
import java.util.HashMap;

public final class zzrv extends zzf<zzrv> {
    public String zzadb;
    public String zzadv;
    public String zzadw;

    public String getAction() {
        return this.zzadb;
    }

    public String getTarget() {
        return this.zzadw;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("network", this.zzadv);
        hashMap.put("action", this.zzadb);
        hashMap.put("target", this.zzadw);
        return zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzrv zzrv) {
        if (!TextUtils.isEmpty(this.zzadv)) {
            zzrv.zzbJ(this.zzadv);
        }
        if (!TextUtils.isEmpty(this.zzadb)) {
            zzrv.zzbC(this.zzadb);
        }
        if (!TextUtils.isEmpty(this.zzadw)) {
            zzrv.zzbK(this.zzadw);
        }
    }

    public void zzbC(String str) {
        this.zzadb = str;
    }

    public void zzbJ(String str) {
        this.zzadv = str;
    }

    public void zzbK(String str) {
        this.zzadw = str;
    }

    public String zznE() {
        return this.zzadv;
    }
}
