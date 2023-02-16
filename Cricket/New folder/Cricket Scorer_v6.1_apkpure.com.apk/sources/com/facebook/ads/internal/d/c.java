package com.facebook.ads.internal.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.l.a;
import com.facebook.ads.internal.p.a.p;
import com.facebook.ads.internal.q.c.d;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class c {
    private static final String a = "c";
    private static c b;
    private final Context c;

    private c(Context context) {
        this.c = context;
    }

    private Bitmap a(String str) {
        byte[] d = d.a(this.c).a(str, (p) null).d();
        return BitmapFactory.decodeByteArray(d, 0, d.length);
    }

    public static c a(Context context) {
        if (b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (c.class) {
                if (b == null) {
                    b = new c(applicationContext);
                }
            }
        }
        return b;
    }

    private static void a(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:34:0x0074=Splitter:B:34:0x0074, B:49:0x00ab=Splitter:B:49:0x00ab} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r7, android.graphics.Bitmap r8) {
        /*
            r6 = this;
            java.io.File r0 = new java.io.File
            android.content.Context r1 = r6.c
            java.io.File r1 = r1.getCacheDir()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = r7.hashCode()
            r2.append(r3)
            java.lang.String r3 = ".png"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r1, r2)
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ FileNotFoundException -> 0x00a9, IOException -> 0x0082, OutOfMemoryError -> 0x0072, all -> 0x006f }
            r2.<init>()     // Catch:{ FileNotFoundException -> 0x00a9, IOException -> 0x0082, OutOfMemoryError -> 0x0072, all -> 0x006f }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0067, OutOfMemoryError -> 0x0063, all -> 0x005e }
            r4 = 100
            r8.compress(r3, r4, r2)     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0067, OutOfMemoryError -> 0x0063, all -> 0x005e }
            int r8 = r2.size()     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0067, OutOfMemoryError -> 0x0063, all -> 0x005e }
            r3 = 3145728(0x300000, float:4.408104E-39)
            if (r8 < r3) goto L_0x0043
            java.lang.String r8 = a     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0067, OutOfMemoryError -> 0x0063, all -> 0x005e }
            java.lang.String r3 = "Bitmap size exceeds max size for storage"
            android.util.Log.d(r8, r3)     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0067, OutOfMemoryError -> 0x0063, all -> 0x005e }
            a((java.io.Closeable) r2)
            a((java.io.Closeable) r1)
            return
        L_0x0043:
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0067, OutOfMemoryError -> 0x0063, all -> 0x005e }
            r8.<init>(r0)     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0067, OutOfMemoryError -> 0x0063, all -> 0x005e }
            r2.writeTo(r8)     // Catch:{ FileNotFoundException -> 0x005c, IOException -> 0x0056, OutOfMemoryError -> 0x0054, all -> 0x0052 }
            r8.flush()     // Catch:{ FileNotFoundException -> 0x005c, IOException -> 0x0056, OutOfMemoryError -> 0x0054, all -> 0x0052 }
            a((java.io.Closeable) r2)
            goto L_0x007e
        L_0x0052:
            r7 = move-exception
            goto L_0x0060
        L_0x0054:
            r7 = move-exception
            goto L_0x0065
        L_0x0056:
            r0 = move-exception
            r1 = r2
            r5 = r0
            r0 = r8
            r8 = r5
            goto L_0x0084
        L_0x005c:
            r7 = move-exception
            goto L_0x006d
        L_0x005e:
            r7 = move-exception
            r8 = r1
        L_0x0060:
            r1 = r2
            goto L_0x00cd
        L_0x0063:
            r7 = move-exception
            r8 = r1
        L_0x0065:
            r1 = r2
            goto L_0x0074
        L_0x0067:
            r8 = move-exception
            r0 = r1
            r1 = r2
            goto L_0x0084
        L_0x006b:
            r7 = move-exception
            r8 = r1
        L_0x006d:
            r1 = r2
            goto L_0x00ab
        L_0x006f:
            r7 = move-exception
            r8 = r1
            goto L_0x00cd
        L_0x0072:
            r7 = move-exception
            r8 = r1
        L_0x0074:
            java.lang.String r0 = a     // Catch:{ all -> 0x00cc }
            java.lang.String r2 = "Unable to write bitmap to output stream"
            android.util.Log.e(r0, r2, r7)     // Catch:{ all -> 0x00cc }
        L_0x007b:
            a((java.io.Closeable) r1)
        L_0x007e:
            a((java.io.Closeable) r8)
            return
        L_0x0082:
            r8 = move-exception
            r0 = r1
        L_0x0084:
            java.lang.String r2 = a     // Catch:{ all -> 0x00a6 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a6 }
            r3.<init>()     // Catch:{ all -> 0x00a6 }
            java.lang.String r4 = "Unable to write bitmap to file (url="
            r3.append(r4)     // Catch:{ all -> 0x00a6 }
            r3.append(r7)     // Catch:{ all -> 0x00a6 }
            java.lang.String r7 = ")."
            r3.append(r7)     // Catch:{ all -> 0x00a6 }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x00a6 }
            android.util.Log.e(r2, r7, r8)     // Catch:{ all -> 0x00a6 }
            a((java.io.Closeable) r1)
            a((java.io.Closeable) r0)
            return
        L_0x00a6:
            r7 = move-exception
            r8 = r0
            goto L_0x00cd
        L_0x00a9:
            r7 = move-exception
            r8 = r1
        L_0x00ab:
            java.lang.String r2 = a     // Catch:{ all -> 0x00cc }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cc }
            r3.<init>()     // Catch:{ all -> 0x00cc }
            java.lang.String r4 = "Bad output destination (file="
            r3.append(r4)     // Catch:{ all -> 0x00cc }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x00cc }
            r3.append(r0)     // Catch:{ all -> 0x00cc }
            java.lang.String r0 = ")."
            r3.append(r0)     // Catch:{ all -> 0x00cc }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x00cc }
            android.util.Log.e(r2, r0, r7)     // Catch:{ all -> 0x00cc }
            goto L_0x007b
            return
        L_0x00cc:
            r7 = move-exception
        L_0x00cd:
            a((java.io.Closeable) r1)
            a((java.io.Closeable) r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.d.c.a(java.lang.String, android.graphics.Bitmap):void");
    }

    private boolean a(int i, int i2) {
        return i > 0 && i2 > 0 && a.d(this.c);
    }

    @Nullable
    private Bitmap b(String str, int i, int i2) {
        try {
            Bitmap a2 = a(i, i2) ? com.facebook.ads.internal.q.b.c.a(str.substring("file://".length()), i, i2) : BitmapFactory.decodeStream(new FileInputStream(str.substring("file://".length())), (Rect) null, (BitmapFactory.Options) null);
            a(str, a2);
            return a2;
        } catch (IOException e) {
            String str2 = a;
            Log.e(str2, "Failed to copy local image into cache (url=" + str + ").", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003f  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap c(java.lang.String r5, int r6, int r7) {
        /*
            r4 = this;
            java.lang.String r0 = "asset:///"
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L_0x0043
            r0 = 0
            android.content.Context r1 = r4.c     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            r2 = 9
            int r3 = r5.length()     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            java.lang.String r2 = r5.substring(r2, r3)     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            boolean r2 = r4.a((int) r6, (int) r7)     // Catch:{ IOException -> 0x003d, all -> 0x0032 }
            if (r2 == 0) goto L_0x0028
            android.graphics.Bitmap r6 = com.facebook.ads.internal.q.b.c.a((java.io.InputStream) r1, (int) r6, (int) r7)     // Catch:{ IOException -> 0x003d, all -> 0x0032 }
            goto L_0x002c
        L_0x0028:
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ IOException -> 0x003d, all -> 0x0032 }
        L_0x002c:
            if (r1 == 0) goto L_0x0052
            a((java.io.Closeable) r1)
            goto L_0x0052
        L_0x0032:
            r5 = move-exception
            r0 = r1
            goto L_0x0036
        L_0x0035:
            r5 = move-exception
        L_0x0036:
            if (r0 == 0) goto L_0x003b
            a((java.io.Closeable) r0)
        L_0x003b:
            throw r5
        L_0x003c:
            r1 = r0
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            a((java.io.Closeable) r1)
        L_0x0042:
            return r0
        L_0x0043:
            boolean r0 = r4.a((int) r6, (int) r7)
            if (r0 == 0) goto L_0x004e
            android.graphics.Bitmap r6 = r4.d(r5, r6, r7)     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            android.graphics.Bitmap r6 = r4.a((java.lang.String) r5)
        L_0x0052:
            r4.a((java.lang.String) r5, (android.graphics.Bitmap) r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.d.c.c(java.lang.String, int, int):android.graphics.Bitmap");
    }

    private Bitmap d(String str, int i, int i2) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        Bitmap a2 = com.facebook.ads.internal.q.b.c.a(inputStream, i, i2);
        a((Closeable) inputStream);
        return a2;
    }

    @Nullable
    public Bitmap a(String str, int i, int i2) {
        File cacheDir = this.c.getCacheDir();
        File file = new File(cacheDir, str.hashCode() + ".png");
        return !file.exists() ? str.startsWith("file://") ? b(str, i, i2) : c(str, i, i2) : a(i, i2) ? com.facebook.ads.internal.q.b.c.a(file.getAbsolutePath(), i, i2) : BitmapFactory.decodeFile(file.getAbsolutePath());
    }
}
