package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzf;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import java.util.HashMap;

public final class zzrs extends zzf<zzrs> {
    public String zzade;
    public boolean zzadf;

    public String getDescription() {
        return this.zzade;
    }

    public void setDescription(String str) {
        this.zzade = str;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put(DublinCoreProperties.DESCRIPTION, this.zzade);
        hashMap.put("fatal", Boolean.valueOf(this.zzadf));
        return zzj(hashMap);
    }

    public void zzR(boolean z) {
        this.zzadf = z;
    }

    /* renamed from: zza */
    public void zzb(zzrs zzrs) {
        if (!TextUtils.isEmpty(this.zzade)) {
            zzrs.setDescription(this.zzade);
        }
        if (this.zzadf) {
            zzrs.zzR(this.zzadf);
        }
    }

    public boolean zznt() {
        return this.zzadf;
    }
}
