package com.google.android.gms.internal;

public class zzbwl {
    private final byte[] zzcsP = new byte[256];
    private int zzcsQ;
    private int zzcsR;

    public zzbwl(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.zzcsP[i] = (byte) i;
        }
        byte b = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            b = (b + this.zzcsP[i2] + bArr[i2 % bArr.length]) & 255;
            byte b2 = this.zzcsP[i2];
            this.zzcsP[i2] = this.zzcsP[b];
            this.zzcsP[b] = b2;
        }
        this.zzcsQ = 0;
        this.zzcsR = 0;
    }

    public void zzaa(byte[] bArr) {
        int i = this.zzcsQ;
        int i2 = this.zzcsR;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.zzcsP[i]) & 255;
            byte b = this.zzcsP[i];
            this.zzcsP[i] = this.zzcsP[i2];
            this.zzcsP[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.zzcsP[(this.zzcsP[i] + this.zzcsP[i2]) & 255]);
        }
        this.zzcsQ = i;
        this.zzcsR = i2;
    }
}
