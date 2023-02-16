package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;

class zzbm implements zzcl {
    private final String zzadb;
    private final long zzagm;
    private final int zzagn;
    private double zzago;
    private long zzagp;
    private final Object zzagq = new Object();
    private final long zzbGH;
    private final zze zzuP;

    public zzbm(int i, int i2, long j, long j2, String str, zze zze) {
        this.zzagn = i2;
        this.zzago = (double) Math.min(i, i2);
        this.zzagm = j;
        this.zzbGH = j2;
        this.zzadb = str;
        this.zzuP = zze;
    }

    public boolean zzpV() {
        synchronized (this.zzagq) {
            long currentTimeMillis = this.zzuP.currentTimeMillis();
            if (currentTimeMillis - this.zzagp < this.zzbGH) {
                String str = this.zzadb;
                StringBuilder sb = new StringBuilder(34 + String.valueOf(str).length());
                sb.append("Excessive ");
                sb.append(str);
                sb.append(" detected; call ignored.");
                zzbo.zzbh(sb.toString());
                return false;
            }
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
            String str2 = this.zzadb;
            StringBuilder sb2 = new StringBuilder(34 + String.valueOf(str2).length());
            sb2.append("Excessive ");
            sb2.append(str2);
            sb2.append(" detected; call ignored.");
            zzbo.zzbh(sb2.toString());
            return false;
        }
    }
}
