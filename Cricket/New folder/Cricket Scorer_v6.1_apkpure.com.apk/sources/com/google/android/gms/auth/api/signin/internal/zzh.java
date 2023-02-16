package com.google.android.gms.auth.api.signin.internal;

public class zzh {
    static int zzakE = 31;
    private int zzakF = 1;

    public zzh zzae(boolean z) {
        this.zzakF = (zzakE * this.zzakF) + (z ? 1 : 0);
        return this;
    }

    public zzh zzq(Object obj) {
        this.zzakF = (zzakE * this.zzakF) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    public int zzru() {
        return this.zzakF;
    }
}
