package com.itextpdf.text.io;

import java.io.IOException;

public class WindowRandomAccessSource implements RandomAccessSource {
    private final long length;
    private final long offset;
    private final RandomAccessSource source;

    public WindowRandomAccessSource(RandomAccessSource randomAccessSource, long j) {
        this(randomAccessSource, j, randomAccessSource.length() - j);
    }

    public WindowRandomAccessSource(RandomAccessSource randomAccessSource, long j, long j2) {
        this.source = randomAccessSource;
        this.offset = j;
        this.length = j2;
    }

    public int get(long j) throws IOException {
        if (j >= this.length) {
            return -1;
        }
        return this.source.get(this.offset + j);
    }

    public int get(long j, byte[] bArr, int i, int i2) throws IOException {
        if (j >= this.length) {
            return -1;
        }
        long min = Math.min((long) i2, this.length - j);
        return this.source.get(this.offset + j, bArr, i, (int) min);
    }

    public long length() {
        return this.length;
    }

    public void close() throws IOException {
        this.source.close();
    }
}
