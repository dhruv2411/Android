package com.google.android.gms.tagmanager;

import android.content.Context;

public class zzaa implements zzat {
    private static final Object zzbEI = new Object();
    private static zzaa zzbFW;
    private zzau zzbFX;
    private zzcl zzbFk;

    private zzaa(Context context) {
        this(zzav.zzca(context), new zzda());
    }

    zzaa(zzau zzau, zzcl zzcl) {
        this.zzbFX = zzau;
        this.zzbFk = zzcl;
    }

    public static zzat zzbT(Context context) {
        zzaa zzaa;
        synchronized (zzbEI) {
            if (zzbFW == null) {
                zzbFW = new zzaa(context);
            }
            zzaa = zzbFW;
        }
        return zzaa;
    }

    public boolean zzhf(String str) {
        if (!this.zzbFk.zzpV()) {
            zzbo.zzbh("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        this.zzbFX.zzhj(str);
        return true;
    }
}
