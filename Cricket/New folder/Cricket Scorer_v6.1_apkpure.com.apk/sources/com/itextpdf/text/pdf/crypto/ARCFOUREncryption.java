package com.itextpdf.text.pdf.crypto;

public class ARCFOUREncryption {
    private byte[] state = new byte[256];
    private int x;
    private int y;

    public void prepareARCFOURKey(byte[] bArr) {
        prepareARCFOURKey(bArr, 0, bArr.length);
    }

    public void prepareARCFOURKey(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < 256; i3++) {
            this.state[i3] = (byte) i3;
        }
        this.x = 0;
        this.y = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < 256; i6++) {
            i5 = (bArr[i4 + i] + this.state[i6] + i5) & 255;
            byte b = this.state[i6];
            this.state[i6] = this.state[i5];
            this.state[i5] = b;
            i4 = (i4 + 1) % i2;
        }
    }

    public void encryptARCFOUR(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i2 + i;
        for (int i5 = i; i5 < i4; i5++) {
            this.x = (this.x + 1) & 255;
            this.y = (this.state[this.x] + this.y) & 255;
            byte b = this.state[this.x];
            this.state[this.x] = this.state[this.y];
            this.state[this.y] = b;
            bArr2[(i5 - i) + i3] = (byte) (bArr[i5] ^ this.state[(this.state[this.x] + this.state[this.y]) & 255]);
        }
    }

    public void encryptARCFOUR(byte[] bArr, int i, int i2) {
        encryptARCFOUR(bArr, i, i2, bArr, i);
    }

    public void encryptARCFOUR(byte[] bArr, byte[] bArr2) {
        encryptARCFOUR(bArr, 0, bArr.length, bArr2, 0);
    }

    public void encryptARCFOUR(byte[] bArr) {
        encryptARCFOUR(bArr, 0, bArr.length, bArr, 0);
    }
}
