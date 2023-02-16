package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.util.HashMap;
import java.util.Map;

public class zzbje {
    private final Context mContext;
    private String zzbFy;
    Map<String, Object> zzbLU;
    private final Map<String, Object> zzbLV;
    private final zzbjg zzbNg;
    private final zze zzuP;

    public zzbje(Context context) {
        this(context, new HashMap(), new zzbjg(context), zzi.zzzc());
    }

    zzbje(Context context, Map<String, Object> map, zzbjg zzbjg, zze zze) {
        this.zzbFy = null;
        this.zzbLU = new HashMap();
        this.mContext = context;
        this.zzuP = zze;
        this.zzbNg = zzbjg;
        this.zzbLV = map;
    }

    public void zzig(String str) {
        this.zzbFy = str;
    }
}
