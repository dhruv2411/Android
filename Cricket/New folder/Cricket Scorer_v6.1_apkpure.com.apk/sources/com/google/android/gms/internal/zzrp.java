package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzf;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import java.util.HashMap;

public final class zzrp extends zzf<zzrp> {
    public int zzMy;
    public int zzMz;
    private String zzacX;
    public int zzacY;
    public int zzacZ;
    public int zzada;

    public String getLanguage() {
        return this.zzacX;
    }

    public void setLanguage(String str) {
        this.zzacX = str;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put(DublinCoreProperties.LANGUAGE, this.zzacX);
        hashMap.put("screenColors", Integer.valueOf(this.zzacY));
        hashMap.put("screenWidth", Integer.valueOf(this.zzMy));
        hashMap.put("screenHeight", Integer.valueOf(this.zzMz));
        hashMap.put("viewportWidth", Integer.valueOf(this.zzacZ));
        hashMap.put("viewportHeight", Integer.valueOf(this.zzada));
        return zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzrp zzrp) {
        if (this.zzacY != 0) {
            zzrp.zzaA(this.zzacY);
        }
        if (this.zzMy != 0) {
            zzrp.zzaB(this.zzMy);
        }
        if (this.zzMz != 0) {
            zzrp.zzaC(this.zzMz);
        }
        if (this.zzacZ != 0) {
            zzrp.zzaD(this.zzacZ);
        }
        if (this.zzada != 0) {
            zzrp.zzaE(this.zzada);
        }
        if (!TextUtils.isEmpty(this.zzacX)) {
            zzrp.setLanguage(this.zzacX);
        }
    }

    public void zzaA(int i) {
        this.zzacY = i;
    }

    public void zzaB(int i) {
        this.zzMy = i;
    }

    public void zzaC(int i) {
        this.zzMz = i;
    }

    public void zzaD(int i) {
        this.zzacZ = i;
    }

    public void zzaE(int i) {
        this.zzada = i;
    }

    public int zznk() {
        return this.zzacY;
    }

    public int zznl() {
        return this.zzMy;
    }

    public int zznm() {
        return this.zzMz;
    }

    public int zznn() {
        return this.zzacZ;
    }

    public int zzno() {
        return this.zzada;
    }
}
