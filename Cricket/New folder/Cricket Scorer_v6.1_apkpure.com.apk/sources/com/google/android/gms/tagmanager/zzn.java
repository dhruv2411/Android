package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzn extends zzam {
    private static final String ID = zzah.CONSTANT.toString();
    private static final String VALUE = zzai.VALUE.toString();

    public zzn() {
        super(ID, VALUE);
    }

    public static String zzQd() {
        return ID;
    }

    public static String zzQe() {
        return VALUE;
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        return map.get(VALUE);
    }
}
