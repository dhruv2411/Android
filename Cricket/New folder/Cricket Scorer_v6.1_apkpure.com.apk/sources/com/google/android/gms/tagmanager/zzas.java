package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class zzas {
    private final long zzagi;
    private final long zzbGm;
    private final long zzbGn;
    private String zzbGo;

    zzas(long j, long j2, long j3) {
        this.zzbGm = j;
        this.zzagi = j2;
        this.zzbGn = j3;
    }

    /* access modifiers changed from: package-private */
    public long zzQN() {
        return this.zzbGm;
    }

    /* access modifiers changed from: package-private */
    public long zzQO() {
        return this.zzbGn;
    }

    /* access modifiers changed from: package-private */
    public String zzQP() {
        return this.zzbGo;
    }

    /* access modifiers changed from: package-private */
    public void zzhi(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzbGo = str;
        }
    }
}
