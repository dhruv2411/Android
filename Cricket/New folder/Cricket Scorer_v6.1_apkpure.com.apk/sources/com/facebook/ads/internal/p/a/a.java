package com.facebook.ads.internal.p.a;

import android.os.Build;
import com.itextpdf.text.pdf.PdfBoolean;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class a {
    private static int[] f = new int[20];
    private static final String g = "a";
    protected final q a = new f() {
    };
    protected final d b = new e();
    protected r c = new g();
    protected int d = 2000;
    protected int e = 8000;
    private int h = 3;
    private Map<String, String> i = new TreeMap();
    private boolean j;
    private Set<String> k;
    private Set<String> l;

    static {
        c();
        if (Build.VERSION.SDK_INT > 8) {
            a();
        }
    }

    public static void a() {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    private static void c() {
        if (Build.VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", PdfBoolean.FALSE);
        }
    }

    private void c(HttpURLConnection httpURLConnection) {
        for (String next : this.i.keySet()) {
            httpURLConnection.setRequestProperty(next, this.i.get(next));
        }
    }

    /* access modifiers changed from: protected */
    public int a(int i2) {
        return 1000 * f[i2 + 2];
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x001f A[SYNTHETIC, Splitter:B:17:0x001f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.net.HttpURLConnection r3, byte[] r4) {
        /*
            r2 = this;
            r0 = 0
            com.facebook.ads.internal.p.a.q r1 = r2.a     // Catch:{ all -> 0x001b }
            java.io.OutputStream r1 = r1.a((java.net.HttpURLConnection) r3)     // Catch:{ all -> 0x001b }
            if (r1 == 0) goto L_0x0011
            com.facebook.ads.internal.p.a.q r0 = r2.a     // Catch:{ all -> 0x000f }
            r0.a(r1, r4)     // Catch:{ all -> 0x000f }
            goto L_0x0011
        L_0x000f:
            r3 = move-exception
            goto L_0x001d
        L_0x0011:
            int r3 = r3.getResponseCode()     // Catch:{ all -> 0x000f }
            if (r1 == 0) goto L_0x001a
            r1.close()     // Catch:{ Exception -> 0x001a }
        L_0x001a:
            return r3
        L_0x001b:
            r3 = move-exception
            r1 = r0
        L_0x001d:
            if (r1 == 0) goto L_0x0022
            r1.close()     // Catch:{ Exception -> 0x0022 }
        L_0x0022:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.a.a.a(java.net.HttpURLConnection, byte[]):int");
    }

    public a a(String str, String str2) {
        this.i.put(str, str2);
        return this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0085 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.ads.internal.p.a.n a(com.facebook.ads.internal.p.a.l r10) {
        /*
            r9 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
        L_0x0005:
            int r3 = r9.h
            if (r2 >= r3) goto L_0x008b
            int r3 = r9.a((int) r2)     // Catch:{ m -> 0x0063 }
            r9.c((int) r3)     // Catch:{ m -> 0x0063 }
            com.facebook.ads.internal.p.a.r r3 = r9.c     // Catch:{ m -> 0x0063 }
            boolean r3 = r3.a()     // Catch:{ m -> 0x0063 }
            if (r3 == 0) goto L_0x0041
            com.facebook.ads.internal.p.a.r r3 = r9.c     // Catch:{ m -> 0x0063 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ m -> 0x0063 }
            r4.<init>()     // Catch:{ m -> 0x0063 }
            int r5 = r2 + 1
            r4.append(r5)     // Catch:{ m -> 0x0063 }
            java.lang.String r5 = "of"
            r4.append(r5)     // Catch:{ m -> 0x0063 }
            int r5 = r9.h     // Catch:{ m -> 0x0063 }
            r4.append(r5)     // Catch:{ m -> 0x0063 }
            java.lang.String r5 = ", trying "
            r4.append(r5)     // Catch:{ m -> 0x0063 }
            java.lang.String r5 = r10.a()     // Catch:{ m -> 0x0063 }
            r4.append(r5)     // Catch:{ m -> 0x0063 }
            java.lang.String r4 = r4.toString()     // Catch:{ m -> 0x0063 }
            r3.a((java.lang.String) r4)     // Catch:{ m -> 0x0063 }
        L_0x0041:
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ m -> 0x0063 }
            java.lang.String r0 = r10.a()     // Catch:{ m -> 0x005e }
            com.facebook.ads.internal.p.a.j r1 = r10.b()     // Catch:{ m -> 0x005e }
            java.lang.String r5 = r10.c()     // Catch:{ m -> 0x005e }
            byte[] r6 = r10.d()     // Catch:{ m -> 0x005e }
            com.facebook.ads.internal.p.a.n r0 = r9.a(r0, r1, r5, r6)     // Catch:{ m -> 0x005e }
            if (r0 == 0) goto L_0x005c
            return r0
        L_0x005c:
            r0 = r3
            goto L_0x0085
        L_0x005e:
            r0 = move-exception
            r7 = r3
            r3 = r0
            r0 = r7
            goto L_0x0064
        L_0x0063:
            r3 = move-exception
        L_0x0064:
            boolean r4 = r9.a((java.lang.Throwable) r3, (long) r0)
            if (r4 == 0) goto L_0x0071
            int r4 = r9.h
            int r4 = r4 + -1
            if (r2 >= r4) goto L_0x0071
            goto L_0x0085
        L_0x0071:
            com.facebook.ads.internal.p.a.q r4 = r9.a
            boolean r4 = r4.a((com.facebook.ads.internal.p.a.m) r3)
            if (r4 == 0) goto L_0x008a
            int r4 = r9.h
            int r4 = r4 + -1
            if (r2 >= r4) goto L_0x008a
            int r4 = r9.d     // Catch:{ InterruptedException -> 0x0089 }
            long r4 = (long) r4     // Catch:{ InterruptedException -> 0x0089 }
            java.lang.Thread.sleep(r4)     // Catch:{ InterruptedException -> 0x0089 }
        L_0x0085:
            int r2 = r2 + 1
            goto L_0x0005
        L_0x0089:
            throw r3
        L_0x008a:
            throw r3
        L_0x008b:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.a.a.a(com.facebook.ads.internal.p.a.l):com.facebook.ads.internal.p.a.n");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:66|67|68|69|70) */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0087, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00c0, code lost:
        throw new com.facebook.ads.internal.p.a.m(r5, (com.facebook.ads.internal.p.a.n) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00c6, code lost:
        throw new com.facebook.ads.internal.p.a.m(r5, (com.facebook.ads.internal.p.a.n) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00cf, code lost:
        r3.c.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00d6, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:66:0x00b8 */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0096 A[SYNTHETIC, Splitter:B:52:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.ads.internal.p.a.n a(java.lang.String r4, com.facebook.ads.internal.p.a.j r5, java.lang.String r6, byte[] r7) {
        /*
            r3 = this;
            r0 = 0
            r1 = 0
            r3.j = r0     // Catch:{ Exception -> 0x008e, all -> 0x008b }
            java.net.HttpURLConnection r4 = r3.a((java.lang.String) r4)     // Catch:{ Exception -> 0x008e, all -> 0x008b }
            r3.a((java.net.HttpURLConnection) r4, (com.facebook.ads.internal.p.a.j) r5, (java.lang.String) r6)     // Catch:{ Exception -> 0x0089 }
            r3.c((java.net.HttpURLConnection) r4)     // Catch:{ Exception -> 0x0089 }
            com.facebook.ads.internal.p.a.r r5 = r3.c     // Catch:{ Exception -> 0x0089 }
            boolean r5 = r5.a()     // Catch:{ Exception -> 0x0089 }
            if (r5 == 0) goto L_0x001b
            com.facebook.ads.internal.p.a.r r5 = r3.c     // Catch:{ Exception -> 0x0089 }
            r5.a(r4, r7)     // Catch:{ Exception -> 0x0089 }
        L_0x001b:
            r4.connect()     // Catch:{ Exception -> 0x0089 }
            r5 = 1
            r3.j = r5     // Catch:{ Exception -> 0x0089 }
            java.util.Set<java.lang.String> r6 = r3.l     // Catch:{ Exception -> 0x0089 }
            if (r6 == 0) goto L_0x002f
            java.util.Set<java.lang.String> r6 = r3.l     // Catch:{ Exception -> 0x0089 }
            boolean r6 = r6.isEmpty()     // Catch:{ Exception -> 0x0089 }
            if (r6 != 0) goto L_0x002f
            r6 = r5
            goto L_0x0030
        L_0x002f:
            r6 = r0
        L_0x0030:
            java.util.Set<java.lang.String> r2 = r3.k     // Catch:{ Exception -> 0x0089 }
            if (r2 == 0) goto L_0x003d
            java.util.Set<java.lang.String> r2 = r3.k     // Catch:{ Exception -> 0x0089 }
            boolean r2 = r2.isEmpty()     // Catch:{ Exception -> 0x0089 }
            if (r2 != 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r5 = r0
        L_0x003e:
            boolean r0 = r4 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ Exception -> 0x0089 }
            if (r0 == 0) goto L_0x0059
            if (r6 != 0) goto L_0x0046
            if (r5 == 0) goto L_0x0059
        L_0x0046:
            r5 = r4
            javax.net.ssl.HttpsURLConnection r5 = (javax.net.ssl.HttpsURLConnection) r5     // Catch:{ Exception -> 0x0051 }
            java.util.Set<java.lang.String> r6 = r3.l     // Catch:{ Exception -> 0x0051 }
            java.util.Set<java.lang.String> r0 = r3.k     // Catch:{ Exception -> 0x0051 }
            com.facebook.ads.internal.p.a.o.a(r5, r6, r0)     // Catch:{ Exception -> 0x0051 }
            goto L_0x0059
        L_0x0051:
            r5 = move-exception
            java.lang.String r6 = g     // Catch:{ Exception -> 0x0089 }
            java.lang.String r0 = "Unable to validate SSL certificates."
            android.util.Log.e(r6, r0, r5)     // Catch:{ Exception -> 0x0089 }
        L_0x0059:
            boolean r5 = r4.getDoOutput()     // Catch:{ Exception -> 0x0089 }
            if (r5 == 0) goto L_0x0064
            if (r7 == 0) goto L_0x0064
            r3.a((java.net.HttpURLConnection) r4, (byte[]) r7)     // Catch:{ Exception -> 0x0089 }
        L_0x0064:
            boolean r5 = r4.getDoInput()     // Catch:{ Exception -> 0x0089 }
            if (r5 == 0) goto L_0x006f
            com.facebook.ads.internal.p.a.n r5 = r3.a((java.net.HttpURLConnection) r4)     // Catch:{ Exception -> 0x0089 }
            goto L_0x0074
        L_0x006f:
            com.facebook.ads.internal.p.a.n r5 = new com.facebook.ads.internal.p.a.n     // Catch:{ Exception -> 0x0089 }
            r5.<init>(r4, r1)     // Catch:{ Exception -> 0x0089 }
        L_0x0074:
            com.facebook.ads.internal.p.a.r r6 = r3.c
            boolean r6 = r6.a()
            if (r6 == 0) goto L_0x0081
            com.facebook.ads.internal.p.a.r r6 = r3.c
            r6.a((com.facebook.ads.internal.p.a.n) r5)
        L_0x0081:
            if (r4 == 0) goto L_0x0086
            r4.disconnect()
        L_0x0086:
            return r5
        L_0x0087:
            r5 = move-exception
            goto L_0x00c7
        L_0x0089:
            r5 = move-exception
            goto L_0x0090
        L_0x008b:
            r5 = move-exception
            r4 = r1
            goto L_0x00c7
        L_0x008e:
            r5 = move-exception
            r4 = r1
        L_0x0090:
            com.facebook.ads.internal.p.a.n r6 = r3.b((java.net.HttpURLConnection) r4)     // Catch:{ Exception -> 0x00b8 }
            if (r6 == 0) goto L_0x00b2
            int r7 = r6.a()     // Catch:{ all -> 0x00af }
            if (r7 <= 0) goto L_0x00b2
            com.facebook.ads.internal.p.a.r r5 = r3.c
            boolean r5 = r5.a()
            if (r5 == 0) goto L_0x00a9
            com.facebook.ads.internal.p.a.r r5 = r3.c
            r5.a((com.facebook.ads.internal.p.a.n) r6)
        L_0x00a9:
            if (r4 == 0) goto L_0x00ae
            r4.disconnect()
        L_0x00ae:
            return r6
        L_0x00af:
            r5 = move-exception
            r1 = r6
            goto L_0x00c7
        L_0x00b2:
            com.facebook.ads.internal.p.a.m r7 = new com.facebook.ads.internal.p.a.m     // Catch:{ all -> 0x00af }
            r7.<init>(r5, r6)     // Catch:{ all -> 0x00af }
            throw r7     // Catch:{ all -> 0x00af }
        L_0x00b8:
            r5.printStackTrace()     // Catch:{ all -> 0x00c1 }
            com.facebook.ads.internal.p.a.m r6 = new com.facebook.ads.internal.p.a.m     // Catch:{ all -> 0x0087 }
            r6.<init>(r5, r1)     // Catch:{ all -> 0x0087 }
            throw r6     // Catch:{ all -> 0x0087 }
        L_0x00c1:
            com.facebook.ads.internal.p.a.m r6 = new com.facebook.ads.internal.p.a.m     // Catch:{ all -> 0x0087 }
            r6.<init>(r5, r1)     // Catch:{ all -> 0x0087 }
            throw r6     // Catch:{ all -> 0x0087 }
        L_0x00c7:
            com.facebook.ads.internal.p.a.r r6 = r3.c
            boolean r6 = r6.a()
            if (r6 == 0) goto L_0x00d4
            com.facebook.ads.internal.p.a.r r6 = r3.c
            r6.a((com.facebook.ads.internal.p.a.n) r1)
        L_0x00d4:
            if (r4 == 0) goto L_0x00d9
            r4.disconnect()
        L_0x00d9:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.a.a.a(java.lang.String, com.facebook.ads.internal.p.a.j, java.lang.String, byte[]):com.facebook.ads.internal.p.a.n");
    }

    public n a(String str, p pVar) {
        return b((l) new i(str, pVar));
    }

    public n a(String str, String str2, byte[] bArr) {
        return b((l) new k(str, (p) null, str2, bArr));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0021 A[SYNTHETIC, Splitter:B:17:0x0021] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.ads.internal.p.a.n a(java.net.HttpURLConnection r4) {
        /*
            r3 = this;
            r0 = 0
            com.facebook.ads.internal.p.a.q r1 = r3.a     // Catch:{ all -> 0x001d }
            java.io.InputStream r1 = r1.b(r4)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x0012
            com.facebook.ads.internal.p.a.q r0 = r3.a     // Catch:{ all -> 0x0010 }
            byte[] r0 = r0.a((java.io.InputStream) r1)     // Catch:{ all -> 0x0010 }
            goto L_0x0012
        L_0x0010:
            r4 = move-exception
            goto L_0x001f
        L_0x0012:
            com.facebook.ads.internal.p.a.n r2 = new com.facebook.ads.internal.p.a.n     // Catch:{ all -> 0x0010 }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x0010 }
            if (r1 == 0) goto L_0x001c
            r1.close()     // Catch:{ Exception -> 0x001c }
        L_0x001c:
            return r2
        L_0x001d:
            r4 = move-exception
            r1 = r0
        L_0x001f:
            if (r1 == 0) goto L_0x0024
            r1.close()     // Catch:{ Exception -> 0x0024 }
        L_0x0024:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.a.a.a(java.net.HttpURLConnection):com.facebook.ads.internal.p.a.n");
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection a(String str) {
        try {
            new URL(str);
            return this.a.a(str);
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException(str + " is not a valid URL", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(l lVar, b bVar) {
        this.b.a(this, bVar).a(lVar);
    }

    public void a(String str, p pVar, b bVar) {
        a((l) new k(str, pVar), bVar);
    }

    /* access modifiers changed from: protected */
    public void a(HttpURLConnection httpURLConnection, j jVar, String str) {
        httpURLConnection.setConnectTimeout(this.d);
        httpURLConnection.setReadTimeout(this.e);
        this.a.a(httpURLConnection, jVar, str);
    }

    public void a(Set<String> set) {
        this.l = set;
    }

    /* access modifiers changed from: protected */
    public boolean a(Throwable th, long j2) {
        long currentTimeMillis = (System.currentTimeMillis() - j2) + 10;
        if (this.c.a()) {
            r rVar = this.c;
            rVar.a("ELAPSED TIME = " + currentTimeMillis + ", CT = " + this.d + ", RT = " + this.e);
        }
        return this.j ? currentTimeMillis >= ((long) this.e) : currentTimeMillis >= ((long) this.d);
    }

    public n b(l lVar) {
        try {
            return a(lVar.a(), lVar.b(), lVar.c(), lVar.d());
        } catch (m e2) {
            this.a.a(e2);
            return null;
        } catch (Exception e3) {
            this.a.a(new m(e3, (n) null));
            return null;
        }
    }

    public n b(String str, p pVar) {
        return b((l) new k(str, pVar));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x001f A[SYNTHETIC, Splitter:B:17:0x001f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.ads.internal.p.a.n b(java.net.HttpURLConnection r4) {
        /*
            r3 = this;
            r0 = 0
            java.io.InputStream r1 = r4.getErrorStream()     // Catch:{ all -> 0x001b }
            if (r1 == 0) goto L_0x0010
            com.facebook.ads.internal.p.a.q r0 = r3.a     // Catch:{ all -> 0x000e }
            byte[] r0 = r0.a((java.io.InputStream) r1)     // Catch:{ all -> 0x000e }
            goto L_0x0010
        L_0x000e:
            r4 = move-exception
            goto L_0x001d
        L_0x0010:
            com.facebook.ads.internal.p.a.n r2 = new com.facebook.ads.internal.p.a.n     // Catch:{ all -> 0x000e }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x000e }
            if (r1 == 0) goto L_0x001a
            r1.close()     // Catch:{ Exception -> 0x001a }
        L_0x001a:
            return r2
        L_0x001b:
            r4 = move-exception
            r1 = r0
        L_0x001d:
            if (r1 == 0) goto L_0x0022
            r1.close()     // Catch:{ Exception -> 0x0022 }
        L_0x0022:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.a.a.b(java.net.HttpURLConnection):com.facebook.ads.internal.p.a.n");
    }

    public p b() {
        return new p();
    }

    public void b(int i2) {
        if (i2 < 1 || i2 > 18) {
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.h = i2;
    }

    public void b(Set<String> set) {
        this.k = set;
    }

    public void c(int i2) {
        this.d = i2;
    }
}
