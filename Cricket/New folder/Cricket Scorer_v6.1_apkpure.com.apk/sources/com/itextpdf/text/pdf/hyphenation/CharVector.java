package com.itextpdf.text.pdf.hyphenation;

import java.io.Serializable;

public class CharVector implements Cloneable, Serializable {
    private static final int DEFAULT_BLOCK_SIZE = 2048;
    private static final long serialVersionUID = -4875768298308363544L;
    private char[] array;
    private int blockSize;
    private int n;

    public CharVector() {
        this(2048);
    }

    public CharVector(int i) {
        if (i > 0) {
            this.blockSize = i;
        } else {
            this.blockSize = 2048;
        }
        this.array = new char[this.blockSize];
        this.n = 0;
    }

    public CharVector(char[] cArr) {
        this.blockSize = 2048;
        this.array = cArr;
        this.n = cArr.length;
    }

    public CharVector(char[] cArr, int i) {
        if (i > 0) {
            this.blockSize = i;
        } else {
            this.blockSize = 2048;
        }
        this.array = cArr;
        this.n = cArr.length;
    }

    public void clear() {
        this.n = 0;
    }

    public Object clone() {
        CharVector charVector = new CharVector((char[]) this.array.clone(), this.blockSize);
        charVector.n = this.n;
        return charVector;
    }

    public char[] getArray() {
        return this.array;
    }

    public int length() {
        return this.n;
    }

    public int capacity() {
        return this.array.length;
    }

    public void put(int i, char c) {
        this.array[i] = c;
    }

    public char get(int i) {
        return this.array[i];
    }

    public int alloc(int i) {
        int i2 = this.n;
        int length = this.array.length;
        if (this.n + i >= length) {
            char[] cArr = new char[(this.blockSize + length)];
            System.arraycopy(this.array, 0, cArr, 0, length);
            this.array = cArr;
        }
        this.n += i;
        return i2;
    }

    public void trimToSize() {
        if (this.n < this.array.length) {
            char[] cArr = new char[this.n];
            System.arraycopy(this.array, 0, cArr, 0, this.n);
            this.array = cArr;
        }
    }
}
