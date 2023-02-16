package com.itextpdf.text.pdf.qrcode;

public final class BitVector {
    private static final int DEFAULT_SIZE_IN_BYTES = 32;
    private byte[] array = new byte[32];
    private int sizeInBits = 0;

    public int at(int i) {
        if (i >= 0 && i < this.sizeInBits) {
            return ((this.array[i >> 3] & 255) >> (7 - (i & 7))) & 1;
        }
        throw new IllegalArgumentException("Bad index: " + i);
    }

    public int size() {
        return this.sizeInBits;
    }

    public int sizeInBytes() {
        return (this.sizeInBits + 7) >> 3;
    }

    public void appendBit(int i) {
        if (i == 0 || i == 1) {
            int i2 = this.sizeInBits & 7;
            if (i2 == 0) {
                appendByte(0);
                this.sizeInBits -= 8;
            }
            byte[] bArr = this.array;
            int i3 = this.sizeInBits >> 3;
            bArr[i3] = (byte) ((i << (7 - i2)) | bArr[i3]);
            this.sizeInBits++;
            return;
        }
        throw new IllegalArgumentException("Bad bit");
    }

    public void appendBits(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        while (i2 > 0) {
            if ((this.sizeInBits & 7) != 0 || i2 < 8) {
                appendBit((i >> (i2 - 1)) & 1);
                i2--;
            } else {
                appendByte((i >> (i2 - 8)) & 255);
                i2 -= 8;
            }
        }
    }

    public void appendBitVector(BitVector bitVector) {
        int size = bitVector.size();
        for (int i = 0; i < size; i++) {
            appendBit(bitVector.at(i));
        }
    }

    public void xor(BitVector bitVector) {
        if (this.sizeInBits != bitVector.size()) {
            throw new IllegalArgumentException("BitVector sizes don't match");
        }
        int i = (this.sizeInBits + 7) >> 3;
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArr = this.array;
            bArr[i2] = (byte) (bArr[i2] ^ bitVector.array[i2]);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(this.sizeInBits);
        for (int i = 0; i < this.sizeInBits; i++) {
            if (at(i) == 0) {
                stringBuffer.append('0');
            } else if (at(i) == 1) {
                stringBuffer.append('1');
            } else {
                throw new IllegalArgumentException("Byte isn't 0 or 1");
            }
        }
        return stringBuffer.toString();
    }

    public byte[] getArray() {
        return this.array;
    }

    private void appendByte(int i) {
        if ((this.sizeInBits >> 3) == this.array.length) {
            byte[] bArr = new byte[(this.array.length << 1)];
            System.arraycopy(this.array, 0, bArr, 0, this.array.length);
            this.array = bArr;
        }
        this.array[this.sizeInBits >> 3] = (byte) i;
        this.sizeInBits += 8;
    }
}
