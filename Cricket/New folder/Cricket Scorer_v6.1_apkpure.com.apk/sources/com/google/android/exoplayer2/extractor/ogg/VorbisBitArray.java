package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.util.Assertions;

final class VorbisBitArray {
    private int bitOffset;
    private int byteOffset;
    public final byte[] data;
    private final int limit;

    public VorbisBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public VorbisBitArray(byte[] bArr, int i) {
        this.data = bArr;
        this.limit = i * 8;
    }

    public void reset() {
        this.byteOffset = 0;
        this.bitOffset = 0;
    }

    public boolean readBit() {
        return readBits(1) == 1;
    }

    public int readBits(int i) {
        int i2;
        int i3;
        Assertions.checkState(getPosition() + i <= this.limit);
        if (i == 0) {
            return 0;
        }
        if (this.bitOffset != 0) {
            i3 = Math.min(i, 8 - this.bitOffset);
            i2 = (255 >>> (8 - i3)) & (this.data[this.byteOffset] >>> this.bitOffset);
            this.bitOffset += i3;
            if (this.bitOffset == 8) {
                this.byteOffset++;
                this.bitOffset = 0;
            }
        } else {
            i3 = 0;
            i2 = 0;
        }
        int i4 = i - i3;
        if (i4 > 7) {
            int i5 = i4 / 8;
            for (int i6 = 0; i6 < i5; i6++) {
                byte[] bArr = this.data;
                int i7 = this.byteOffset;
                this.byteOffset = i7 + 1;
                i2 = (int) (((long) i2) | ((((long) bArr[i7]) & 255) << i3));
                i3 += 8;
            }
        }
        if (i <= i3) {
            return i2;
        }
        int i8 = i - i3;
        int i9 = i2 | (((255 >>> (8 - i8)) & this.data[this.byteOffset]) << i3);
        this.bitOffset += i8;
        return i9;
    }

    public void skipBits(int i) {
        Assertions.checkState(getPosition() + i <= this.limit);
        this.byteOffset += i / 8;
        this.bitOffset += i % 8;
        if (this.bitOffset > 7) {
            this.byteOffset++;
            this.bitOffset -= 8;
        }
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public void setPosition(int i) {
        Assertions.checkArgument(i < this.limit && i >= 0);
        this.byteOffset = i / 8;
        this.bitOffset = i - (this.byteOffset * 8);
    }

    public int bitsLeft() {
        return this.limit - getPosition();
    }

    public int limit() {
        return this.limit;
    }
}
