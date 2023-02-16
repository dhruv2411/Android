package com.facebook.ads.internal.p.b.a;

import com.facebook.ads.internal.p.b.a;
import com.facebook.ads.internal.p.b.l;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class b implements a {
    public File a;
    private final a b;
    private RandomAccessFile c;

    public b(File file, a aVar) {
        File file2;
        if (aVar == null) {
            try {
                throw new NullPointerException();
            } catch (IOException e) {
                throw new l("Error using file " + file + " as disc cache", e);
            }
        } else {
            this.b = aVar;
            d.a(file.getParentFile());
            boolean exists = file.exists();
            if (exists) {
                file2 = file;
            } else {
                File parentFile = file.getParentFile();
                file2 = new File(parentFile, file.getName() + ".download");
            }
            this.a = file2;
            this.c = new RandomAccessFile(this.a, exists ? "r" : "rw");
        }
    }

    private boolean a(File file) {
        return file.getName().endsWith(".download");
    }

    public synchronized int a() {
        try {
        } catch (IOException e) {
            throw new l("Error reading length of file " + this.a, e);
        }
        return (int) this.c.length();
    }

    public synchronized int a(byte[] bArr, long j, int i) {
        try {
            this.c.seek(j);
        } catch (IOException e) {
            throw new l(String.format("Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", new Object[]{Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(a()), Integer.valueOf(bArr.length)}), e);
        }
        return this.c.read(bArr, 0, i);
    }

    public synchronized void a(byte[] bArr, int i) {
        try {
            if (d()) {
                throw new l("Error append cache: cache file " + this.a + " is completed!");
            }
            this.c.seek((long) a());
            this.c.write(bArr, 0, i);
        } catch (IOException e) {
            throw new l(String.format("Error writing %d bytes to %s from buffer with size %d", new Object[]{Integer.valueOf(i), this.c, Integer.valueOf(bArr.length)}), e);
        }
    }

    public synchronized void b() {
        try {
            this.c.close();
            this.b.a(this.a);
        } catch (IOException e) {
            throw new l("Error closing file " + this.a, e);
        }
    }

    public synchronized void c() {
        if (!d()) {
            b();
            File file = new File(this.a.getParentFile(), this.a.getName().substring(0, this.a.getName().length() - ".download".length()));
            if (!this.a.renameTo(file)) {
                throw new l("Error renaming file " + this.a + " to " + file + " for completion!");
            }
            this.a = file;
            try {
                this.c = new RandomAccessFile(this.a, "r");
            } catch (IOException e) {
                throw new l("Error opening " + this.a + " as disc cache", e);
            }
        }
    }

    public synchronized boolean d() {
        return !a(this.a);
    }
}
