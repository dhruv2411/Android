package com.google.android.gms.internal;

public abstract class zzsa extends zzrz {
    private boolean zzadP;

    protected zzsa(zzsc zzsc) {
        super(zzsc);
    }

    public void initialize() {
        zzmS();
        this.zzadP = true;
    }

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
