package com.itextpdf.text.io;

import java.io.IOException;
import java.nio.channels.FileChannel;

class MappedChannelRandomAccessSource implements RandomAccessSource {
    private final FileChannel channel;
    private final long length;
    private final long offset;
    private ByteBufferRandomAccessSource source;

    public MappedChannelRandomAccessSource(FileChannel fileChannel, long j, long j2) {
        if (j < 0) {
            throw new IllegalArgumentException(j + " is negative");
        } else if (j2 <= 0) {
            throw new IllegalArgumentException(j2 + " is zero or negative");
        } else {
            this.channel = fileChannel;
            this.offset = j;
            this.length = j2;
            this.source = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void open() throws IOException {
        if (this.source == null) {
            if (!this.channel.isOpen()) {
                throw new IllegalStateException("Channel is closed");
            }
            try {
                this.source = new ByteBufferRandomAccessSource(this.channel.map(FileChannel.MapMode.READ_ONLY, this.offset, this.length));
            } catch (IOException e) {
                if (exceptionIsMapFailureException(e)) {
                    throw new MapFailedException(e);
                }
                throw e;
            }
        }
    }

    private static boolean exceptionIsMapFailureException(IOException iOException) {
        return iOException.getMessage() != null && iOException.getMessage().indexOf("Map failed") >= 0;
    }

    public int get(long j) throws IOException {
        if (this.source != null) {
            return this.source.get(j);
        }
        throw new IOException("RandomAccessSource not opened");
    }

    public int get(long j, byte[] bArr, int i, int i2) throws IOException {
        if (this.source != null) {
            return this.source.get(j, bArr, i, i2);
        }
        throw new IOException("RandomAccessSource not opened");
    }

    public long length() {
        return this.length;
    }

    public void close() throws IOException {
        if (this.source != null) {
            this.source.close();
            this.source = null;
        }
    }

    public String toString() {
        return getClass().getName() + " (" + this.offset + ", " + this.length + ")";
    }
}
