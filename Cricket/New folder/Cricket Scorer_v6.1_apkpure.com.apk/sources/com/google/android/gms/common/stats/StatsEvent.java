package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

public abstract class StatsEvent extends zza implements ReflectedParcelable {
    public abstract int getEventType();

    public abstract long getTimeMillis();

    public String toString() {
        long timeMillis = getTimeMillis();
        String valueOf = String.valueOf("\t");
        int eventType = getEventType();
        String valueOf2 = String.valueOf("\t");
        long zzyK = zzyK();
        String valueOf3 = String.valueOf(zzyL());
        StringBuilder sb = new StringBuilder(51 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append(timeMillis);
        sb.append(valueOf);
        sb.append(eventType);
        sb.append(valueOf2);
        sb.append(zzyK);
        sb.append(valueOf3);
        return sb.toString();
    }

    public abstract long zzyK();

    public abstract String zzyL();
}
