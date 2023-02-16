package com.google.android.gms.internal;

public class zzsh extends zzsa {
    private final zzrk zzacl = new zzrk();

    zzsh(zzsc zzsc) {
        super(zzsc);
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        zznU().zzmP().zzb(this.zzacl);
        zzmw();
    }

    public void zzmw() {
        zztn zzmB = zzmB();
        String zzmY = zzmB.zzmY();
        if (zzmY != null) {
            this.zzacl.setAppName(zzmY);
        }
        String zzmZ = zzmB.zzmZ();
        if (zzmZ != null) {
            this.zzacl.setAppVersion(zzmZ);
        }
    }

    public zzrk zzoy() {
        zzob();
        return this.zzacl;
    }
}
