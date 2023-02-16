package com.itextpdf.text.pdf.hyphenation;

import java.io.Serializable;

public class ByteVector implements Serializable {
    private static final int DEFAULT_BLOCK_SIZE = 2048;
    private static final long serialVersionUID = -1096301185375029343L;
    private byte[] array;
    private int blockSize;
    private int n;

    public ByteVector() {
        this(2048);
    }

    public ByteVector(int i) {
        if (i > 0) {
            this.blockSize = i;
        } else {
            this.blockSize = 2048;
        }
        this.array = new byte[this.blockSize];
        this.n = 0;
    }

    public ByteVector(byte[] bArr) {
        this.blockSize = 2048;
        this.array = bArr;
        this.n = 0;
    }

    public ByteVector(byte[] bArr, int i) {
        if (i > 0) {
            this.blockSize = i;
        } else {
            this.blockSize = 2048;
        }
        this.array = bArr;
        this.n = 0;
    }

    public byte[] getArray() {
        return this.array;
    }

    public int length() {
        return this.n;
    }

    public int capacity() {
        return this.array.length;
    }

    public void put(int i, byte b) {
        this.array[i] = b;
    }

    public byte get(int i) {
        return this.array[i];
    }

    public int alloc(int i) {
        int i2 = this.n;
        int length = this.array.length;
        if (this.n + i >= length) {
            byte[] bArr = new byte[(this.blockSize + length)];
            System.arraycopy(this.array, 0, bArr, 0, length);
            this.array = bArr;
        }
        this.n += i;
        return i2;
    }

    public void trimToSize() {
        if (this.n < this.array.length) {
            byte[] bArr = new byte[this.n];
            System.arraycopy(this.array, 0, bArr, 0, this.n);
            this.array = bArr;
        }
    }
}
