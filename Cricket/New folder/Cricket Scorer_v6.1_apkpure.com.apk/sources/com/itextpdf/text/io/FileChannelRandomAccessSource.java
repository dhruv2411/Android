package com.itextpdf.text.io;

import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileChannelRandomAccessSource implements RandomAccessSource {
    private final FileChannel channel;
    private final MappedChannelRandomAccessSource source;

    public FileChannelRandomAccessSource(FileChannel fileChannel) throws IOException {
        this.channel = fileChannel;
        if (fileChannel.size() == 0) {
            throw new IOException("File size is 0 bytes");
        }
        this.source = new MappedChannelRandomAccessSource(fileChannel, 0, fileChannel.size());
        this.source.open();
    }

    public void close() throws IOException {
        this.source.close();
        this.channel.close();
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
