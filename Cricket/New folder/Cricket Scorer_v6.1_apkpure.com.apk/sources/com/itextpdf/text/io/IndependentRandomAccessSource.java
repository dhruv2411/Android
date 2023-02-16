package com.itextpdf.text.io;

import java.io.IOException;

public class IndependentRandomAccessSource implements RandomAccessSource {
    private final RandomAccessSource source;

    public void close() throws IOException {
    }

    public IndependentRandomAccessSource(RandomAccessSource randomAccessSource) {
        this.source = randomAccessSource;
    }

    public int get(long j) throws IOException {
        return this.source.get(j);
    }

    public int get(long j, byte[] bArr, int i, int i2) throws IOException {
        return this.source.get(j, bArr, i, i2);
    }

    public long length() {
        return this.source.length();
    }
}
