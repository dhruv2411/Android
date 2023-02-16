package com.google.android.exoplayer2.util;

public final class ParsableNalUnitBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    private byte[] data;

    public ParsableNalUnitBitArray(byte[] bArr, int i, int i2) {
        reset(bArr, i, i2);
    }

    public void reset(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.byteOffset = i;
        this.byteLimit = i2;
        this.bitOffset = 0;
        assertValidOffset();
    }

    public void skipBits(int i) {
        int i2 = this.byteOffset;
        this.byteOffset += i / 8;
        this.bitOffset += i % 8;
        if (this.bitOffset > 7) {
            this.byteOffset++;
            this.bitOffset -= 8;
        }
        while (true) {
            i2++;
            if (i2 > this.byteOffset) {
                assertValidOffset();
                return;
            } else if (shouldSkipByte(i2)) {
                this.byteOffset++;
                i2 += 2;
            }
        }
    }

    public boolean canReadBits(int i) {
        int i2 = this.byteOffset;
        int i3 = this.byteOffset + (i / 8);
        int i4 = this.bitOffset + (i % 8);
        if (i4 > 7) {
            i3++;
            i4 -= 8;
        }
        while (true) {
            i2++;
            if (i2 <= i3 && i3 < this.byteLimit) {
                if (shouldSkipByte(i2)) {
                    i3++;
                    i2 += 2;
                }
            }
        }
        if (i3 < this.byteLimit) {
            return true;
        }
        if (i3 == this.byteLimit && i4 == 0) {
            return true;
        }
        return false;
    }

    public boolean readBit() {
        return readBits(1) == 1;
    }

    public int readBits(int i) {
        byte b;
        byte b2;
        if (i == 0) {
            return 0;
        }
        int i2 = i / 8;
        byte b3 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = shouldSkipByte(this.byteOffset + 1) ? this.byteOffset + 2 : this.byteOffset + 1;
            if (this.bitOffset != 0) {
                b2 = ((this.data[i4] & 255) >>> (8 - this.bitOffset)) | ((this.data[this.byteOffset] & 255) << this.bitOffset);
            } else {
                b2 = this.data[this.byteOffset];
            }
            i -= 8;
            b3 |= (255 & b2) << i;
            this.byteOffset = i4;
        }
        if (i > 0) {
            int i5 = this.bitOffset + i;
            byte b4 = (byte) (255 >> (8 - i));
            int i6 = shouldSkipByte(this.byteOffset + 1) ? this.byteOffset + 2 : this.byteOffset + 1;
            if (i5 > 8) {
                b = (b4 & (((255 & this.data[i6]) >> (16 - i5)) | ((this.data[this.byteOffset] & 255) << (i5 - 8)))) | b3;
                this.byteOffset = i6;
            } else {
                b = (b4 & ((255 & this.data[this.byteOffset]) >> (8 - i5))) | b3;
                if (i5 == 8) {
                    this.byteOffset = i6;
                }
            }
            b3 = b;
            this.bitOffset = i5 % 8;
        }
        assertValidOffset();
        return b3;
    }

    public boolean canReadExpGolombCodedNum() {
        int i = this.byteOffset;
        int i2 = this.bitOffset;
        int i3 = 0;
        while (this.byteOffset < this.byteLimit && !readBit()) {
            i3++;
        }
        boolean z = this.byteOffset == this.byteLimit;
        this.byteOffset = i;
        this.bitOffset = i2;
        if (z || !canReadBits((i3 * 2) + 1)) {
            return false;
        }
        return true;
    }

    public int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public int readSignedExpGolombCodedInt() {
        int readExpGolombCodeNum = readExpGolombCodeNum();
        return (readExpGolombCodeNum % 2 == 0 ? -1 : 1) * ((readExpGolombCodeNum + 1) / 2);
    }

    private int readExpGolombCodeNum() {
        int i = 0;
        int i2 = 0;
        while (!readBit()) {
            i2++;
        }
        int i3 = (1 << i2) - 1;
        if (i2 > 0) {
            i = readBits(i2);
        }
        return i3 + i;
    }

    private boolean shouldSkipByte(int i) {
        return 2 <= i && i < this.byteLimit && this.data[i] == 3 && this.data[i + -2] == 0 && this.data[i - 1] == 0;
    }

    private void assertValidOffset() {
        Assertions.checkState(this.byteOffset >= 0 && this.bitOffset >= 0 && this.bitOffset < 8 && (this.byteOffset < this.byteLimit || (this.byteOffset == this.byteLimit && this.bitOffset == 0)));
    }
}
