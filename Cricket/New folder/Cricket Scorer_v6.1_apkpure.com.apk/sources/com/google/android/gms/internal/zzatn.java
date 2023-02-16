package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;

class zzatn {
    final String mAppId;
    final String mName;
    final long zzbrA;
    final long zzbrB;
    final long zzbrC;

    zzatn(String str, String str2, long j, long j2, long j3) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        boolean z = false;
        zzac.zzax(j >= 0);
        zzac.zzax(j2 >= 0 ? true : z);
        this.mAppId = str;
        this.mName = str2;
        this.zzbrA = j;
        this.zzbrB = j2;
        this.zzbrC = j3;
    }

    /* access modifiers changed from: package-private */
    public zzatn zzLV() {
        return new zzatn(this.mAppId, this.mName, this.zzbrA + 1, this.zzbrB + 1, this.zzbrC);
    }

    /* access modifiers changed from: package-private */
    public zzatn zzap(long j) {
        return new zzatn(this.mAppId, this.mName, this.zzbrA, this.zzbrB, j);
    }
}
