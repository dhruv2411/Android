package com.google.android.gms.internal;

abstract class zzauh extends zzaug {
    private boolean zzadP;

    zzauh(zzaue zzaue) {
        super(zzaue);
        this.zzbqc.zzb(this);
    }

    public final void initialize() {
        if (this.zzadP) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzmS();
        this.zzbqc.zzMK();
        this.zzadP = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isInitialized() {
        return this.zzadP;
    }

    /* access modifiers changed from: protected */
    public abstract void zzmS();

    /* access modifiers changed from: protected */
    public void zzob() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
