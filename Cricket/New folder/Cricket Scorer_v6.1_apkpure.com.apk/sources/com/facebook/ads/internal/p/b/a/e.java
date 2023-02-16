package com.facebook.ads.internal.p.b.a;

import android.util.Log;
import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class e implements a {
    private final ExecutorService a = Executors.newSingleThreadExecutor();

    private class a implements Callable<Void> {
        private final File b;

        public a(File file) {
            this.b = file;
        }

        /* renamed from: a */
        public Void call() {
            e.this.b(this.b);
            return null;
        }
    }

    e() {
    }

    private void a(List<File> list) {
        long b = b(list);
        int size = list.size();
        for (File next : list) {
            if (!a(next, b, size)) {
                long length = next.length();
                if (next.delete()) {
                    size--;
                    Log.i("ProxyCache", "Cache file " + next + " is deleted because it exceeds cache limit");
                    b -= length;
                } else {
                    Log.e("ProxyCache", "Error deleting file " + next + " for trimming cache");
                }
            }
        }
    }

    private long b(List<File> list) {
        long j = 0;
        for (File length : list) {
            j += length.length();
        }
        return j;
    }

    /* access modifiers changed from: private */
    public void b(File file) {
        d.c(file);
        a(d.b(file.getParentFile()));
    }

    public void a(File file) {
        this.a.submit(new a(file));
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(File file, long j, int i);
}
