package com.itextpdf.text.pdf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class MappedRandomAccessFile {
    private static final int BUFSIZE = 1073741824;
    private FileChannel channel = null;
    private MappedByteBuffer[] mappedBuffers;
    private long pos;
    private long size;

    public MappedRandomAccessFile(String str, String str2) throws FileNotFoundException, IOException {
        if (str2.equals("rw")) {
            init(new RandomAccessFile(str, str2).getChannel(), FileChannel.MapMode.READ_WRITE);
        } else {
            init(new FileInputStream(str).getChannel(), FileChannel.MapMode.READ_ONLY);
        }
    }

    private void init(FileChannel fileChannel, FileChannel.MapMode mapMode) throws IOException {
        this.channel = fileChannel;
        this.size = fileChannel.size();
        long j = 0;
        this.pos = 0;
        int i = 0;
        int i2 = ((int) (this.size / 1073741824)) + (this.size % 1073741824 == 0 ? 0 : 1);
        this.mappedBuffers = new MappedByteBuffer[i2];
        while (j < this.size) {
            try {
                this.mappedBuffers[i] = fileChannel.map(mapMode, j, Math.min(this.size - j, 1073741824));
                this.mappedBuffers[i].load();
                i++;
                j += 1073741824;
            } catch (IOException e) {
                close();
                throw e;
            } catch (RuntimeException e2) {
                close();
                throw e2;
            }
        }
        if (i != i2) {
            throw new Error("Should never happen - " + i + " != " + i2);
        }
    }

    public FileChannel getChannel() {
        return this.channel;
    }

    public int read() {
        try {
            int i = (int) (this.pos / 1073741824);
            int i2 = (int) (this.pos % 1073741824);
            if (i >= this.mappedBuffers.length || i2 >= this.mappedBuffers[i].limit()) {
                return -1;
            }
            byte b = this.mappedBuffers[i].get(i2);
            this.pos++;
            return b & 255;
        } catch (BufferUnderflowException unused) {
            return -1;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        int i3 = (int) (this.pos / 1073741824);
        int i4 = (int) (this.pos % 1073741824);
        int i5 = i;
        int i6 = 0;
        while (i6 < i2 && i3 < this.mappedBuffers.length) {
            MappedByteBuffer mappedByteBuffer = this.mappedBuffers[i3];
            if (i4 > mappedByteBuffer.limit()) {
                break;
            }
            mappedByteBuffer.position(i4);
            int min = Math.min(i2 - i6, mappedByteBuffer.remaining());
            mappedByteBuffer.get(bArr, i5, min);
            i5 += min;
            this.pos += (long) min;
            i6 += min;
            i3++;
            i4 = 0;
        }
        if (i6 == 0) {
            return -1;
        }
        return i6;
    }

    public long getFilePointer() {
        return this.pos;
    }

    public void seek(long j) {
        this.pos = j;
    }

    public long length() {
        return this.size;
    }

    public void close() throws IOException {
        for (int i = 0; i < this.mappedBuffers.length; i++) {
            if (this.mappedBuffers[i] != null) {
                clean(this.mappedBuffers[i]);
                this.mappedBuffers[i] = null;
            }
        }
        if (this.channel != null) {
            this.channel.close();
        }
        this.channel = null;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        close();
        super.finalize();
    }

    public static boolean clean(final ByteBuffer byteBuffer) {
        if (byteBuffer == null || !byteBuffer.isDirect()) {
            return false;
        }
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
            public Boolean run() {
                Boolean bool = Boolean.FALSE;
                try {
                    Method method = byteBuffer.getClass().getMethod("cleaner", (Class[]) null);
                    method.setAccessible(true);
                    Object invoke = method.invoke(byteBuffer, (Object[]) null);
                    invoke.getClass().getMethod("clean", (Class[]) null).invoke(invoke, (Object[]) null);
                    return Boolean.TRUE;
                } catch (Exception unused) {
                    return bool;
                }
            }
        })).booleanValue();
    }
}
