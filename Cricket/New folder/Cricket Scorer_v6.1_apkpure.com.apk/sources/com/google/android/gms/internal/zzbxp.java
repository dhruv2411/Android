package com.google.android.gms.internal;

public final class zzbxp implements Cloneable {
    private static final zzbxq zzcuK = new zzbxq();
    private int mSize;
    private boolean zzcuL;
    private int[] zzcuM;
    private zzbxq[] zzcuN;

    zzbxp() {
        this(10);
    }

    zzbxp(int i) {
        this.zzcuL = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzcuM = new int[idealIntArraySize];
        this.zzcuN = new zzbxq[idealIntArraySize];
        this.mSize = 0;
    }

    private int idealByteArraySize(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            int i3 = (1 << i2) - 12;
            if (i <= i3) {
                return i3;
            }
        }
        return i;
    }

    private int idealIntArraySize(int i) {
        return idealByteArraySize(i * 4) / 4;
    }

    private boolean zza(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean zza(zzbxq[] zzbxqArr, zzbxq[] zzbxqArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!zzbxqArr[i2].equals(zzbxqArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private int zzrq(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzcuM[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i2 = i4 - 1;
            }
        }
        return i3 ^ -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbxp)) {
            return false;
        }
        zzbxp zzbxp = (zzbxp) obj;
        if (size() != zzbxp.size()) {
            return false;
        }
        return zza(this.zzcuM, zzbxp.zzcuM, this.mSize) && zza(this.zzcuN, zzbxp.zzcuN, this.mSize);
    }

    public int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = this.zzcuN[i2].hashCode() + (31 * ((i * 31) + this.zzcuM[i2]));
        }
        return i;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.mSize;
    }

    /* access modifiers changed from: package-private */
    public void zza(int i, zzbxq zzbxq) {
        int zzrq = zzrq(i);
        if (zzrq >= 0) {
            this.zzcuN[zzrq] = zzbxq;
            return;
        }
        int i2 = zzrq ^ -1;
        if (i2 >= this.mSize || this.zzcuN[i2] != zzcuK) {
            if (this.mSize >= this.zzcuM.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzbxq[] zzbxqArr = new zzbxq[idealIntArraySize];
                System.arraycopy(this.zzcuM, 0, iArr, 0, this.zzcuM.length);
                System.arraycopy(this.zzcuN, 0, zzbxqArr, 0, this.zzcuN.length);
                this.zzcuM = iArr;
                this.zzcuN = zzbxqArr;
            }
            if (this.mSize - i2 != 0) {
                int i3 = i2 + 1;
                System.arraycopy(this.zzcuM, i2, this.zzcuM, i3, this.mSize - i2);
                System.arraycopy(this.zzcuN, i2, this.zzcuN, i3, this.mSize - i2);
            }
            this.zzcuM[i2] = i;
            this.zzcuN[i2] = zzbxq;
            this.mSize++;
            return;
        }
        this.zzcuM[i2] = i;
        this.zzcuN[i2] = zzbxq;
    }

    /* renamed from: zzaeJ */
    public final zzbxp clone() {
        int size = size();
        zzbxp zzbxp = new zzbxp(size);
        System.arraycopy(this.zzcuM, 0, zzbxp.zzcuM, 0, size);
        for (int i = 0; i < size; i++) {
            if (this.zzcuN[i] != null) {
                zzbxp.zzcuN[i] = (zzbxq) this.zzcuN[i].clone();
            }
        }
        zzbxp.mSize = size;
        return zzbxp;
    }

    /* access modifiers changed from: package-private */
    public zzbxq zzro(int i) {
        int zzrq = zzrq(i);
        if (zzrq < 0 || this.zzcuN[zzrq] == zzcuK) {
            return null;
        }
        return this.zzcuN[zzrq];
    }

    /* access modifiers changed from: package-private */
    public zzbxq zzrp(int i) {
        return this.zzcuN[i];
    }
}
