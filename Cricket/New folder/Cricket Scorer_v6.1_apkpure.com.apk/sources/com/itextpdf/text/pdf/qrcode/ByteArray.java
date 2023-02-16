package com.itextpdf.text.pdf.qrcode;

public final class ByteArray {
    private static final int INITIAL_SIZE = 32;
    private byte[] bytes;
    private int size;

    public ByteArray() {
        this.bytes = null;
        this.size = 0;
    }

    public ByteArray(int i) {
        this.bytes = new byte[i];
        this.size = i;
    }

    public ByteArray(byte[] bArr) {
        this.bytes = bArr;
        this.size = this.bytes.length;
    }

    public int at(int i) {
        return this.bytes[i] & 255;
    }

    public void set(int i, int i2) {
        this.bytes[i] = (byte) i2;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void appendByte(int i) {
        if (this.size == 0 || this.size >= this.bytes.length) {
            reserve(Math.max(32, this.size << 1));
        }
        this.bytes[this.size] = (byte) i;
        this.size++;
    }

    public void reserve(int i) {
        if (this.bytes == null || this.bytes.length < i) {
            byte[] bArr = new byte[i];
            if (this.bytes != null) {
                System.arraycopy(this.bytes, 0, bArr, 0, this.bytes.length);
            }
            this.bytes = bArr;
        }
    }

    public void set(byte[] bArr, int i, int i2) {
        this.bytes = new byte[i2];
        this.size = i2;
        for (int i3 = 0; i3 < i2; i3++) {
            this.bytes[i3] = bArr[i + i3];
        }
    }
}
