package com.itextpdf.text.io;

import java.io.IOException;

class ArrayRandomAccessSource implements RandomAccessSource {
    private byte[] array;

    public ArrayRandomAccessSource(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.array = bArr;
    }

    public int get(long j) {
        if (j >= ((long) this.array.length)) {
            return -1;
        }
        return this.array[(int) j] & 255;
    }

    public int get(long j, byte[] bArr, int i, int i2) {
        if (this.array == null) {
            throw new IllegalStateException("Already closed");
        } else if (j >= ((long) this.array.length)) {
            return -1;
        } else {
            if (j + ((long) i2) > ((long) this.array.length)) {
                i2 = (int) (((long) this.array.length) - j);
            }
            System.arraycopy(this.array, (int) j, bArr, i, i2);
            return i2;
        }
    }

    public long length() {
        return (long) this.array.length;
    }

    public void close() throws IOException {
        this.array = null;
    }
}
