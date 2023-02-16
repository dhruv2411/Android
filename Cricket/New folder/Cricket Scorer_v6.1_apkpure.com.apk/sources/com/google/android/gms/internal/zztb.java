package com.google.android.gms.internal;

import com.google.android.gms.common.util.zze;

public class zztb {
    private final String zzadb;
    private final long zzagm;
    private final int zzagn;
    private double zzago;
    private long zzagp;
    private final Object zzagq;
    private final zze zzuP;

    public zztb(int i, long j, String str, zze zze) {
        this.zzagq = new Object();
        this.zzagn = i;
        this.zzago = (double) this.zzagn;
        this.zzagm = j;
        this.zzadb = str;
        this.zzuP = zze;
    }

    public zztb(String str, zze zze) {
        this(60, 2000, str, zze);
    }

    public boolean zzpV() {
        synchronized (this.zzagq) {
            long currentTimeMillis = this.zzuP.currentTimeMillis();
            if (this.zzago < ((double) this.zzagn)) {
                double d = ((double) (currentTimeMillis - this.zzagp)) / ((double) this.zzagm);
                if (d > 0.0d) {
                    this.zzago = Math.min((double) this.zzagn, this.zzago + d);
                }
            }
            this.zzagp = currentTimeMillis;
            if (this.zzago >= 1.0d) {
                this.zzago -= 1.0d;
                return true;
            }
            String str = this.zzadb;
            StringBuilder sb = new StringBuilder(34 + String.valueOf(str).length());
            sb.append("Excessive ");
            sb.append(str);
            sb.append(" detected; call ignored.");
            zztc.zzbh(sb.toString());
            return false;
        }
    }
}
