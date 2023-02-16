package com.facebook.ads.internal.o;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.f.d;
import com.facebook.ads.internal.f.e;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.p.a.n;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.q.a.i;
import com.facebook.ads.internal.q.a.m;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.q.d.b;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONException;

public class c {
    private static final m i = new m();
    private static final ThreadPoolExecutor j = ((ThreadPoolExecutor) Executors.newCachedThreadPool(i));
    /* access modifiers changed from: private */
    public final Context a;
    /* access modifiers changed from: private */
    public final e b = e.a();
    private final com.facebook.ads.internal.l.a c = com.facebook.ads.internal.l.a.w(this.a);
    /* access modifiers changed from: private */
    public Map<String, String> d;
    private a e;
    /* access modifiers changed from: private */
    public b f;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.p.a.a g;
    /* access modifiers changed from: private */
    public final String h = d.a();

    public interface a {
        void a(g gVar);

        void a(com.facebook.ads.internal.protocol.a aVar);
    }

    public c(Context context) {
        this.a = context.getApplicationContext();
    }

    private void a(g gVar) {
        if (this.e != null) {
            this.e.a(gVar);
        }
        a();
    }

    /* access modifiers changed from: private */
    public void a(com.facebook.ads.internal.protocol.a aVar) {
        if (this.e != null) {
            this.e.a(aVar);
        }
        a();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        int i2;
        com.facebook.ads.internal.protocol.a aVar;
        try {
            f a2 = this.b.a(str);
            com.facebook.ads.internal.h.c a3 = a2.a();
            if (a3 != null) {
                this.c.a(a3.b());
                a.a(a3.a().d(), this.f);
            }
            switch (a2.b()) {
                case ADS:
                    g gVar = (g) a2;
                    if (a3 != null) {
                        if (a3.a().e()) {
                            a.a(str, this.f);
                        }
                        String str2 = this.d != null ? this.d.get("CLIENT_REQUEST_ID") : null;
                        String c2 = a2.c();
                        if (!TextUtils.isEmpty(c2) && !TextUtils.isEmpty(str2)) {
                            StringBuilder sb = new StringBuilder();
                            for (int i3 = 0; i3 < "82042s3304s547sso6r044qoq3sn5199".length(); i3++) {
                                char charAt = "82042s3304s547sso6r044qoq3sn5199".charAt(i3);
                                if ((charAt < 'a' || charAt > 'm') && (charAt < 'A' || charAt > 'M')) {
                                    if ((charAt >= 'n' && charAt <= 'z') || (charAt >= 'N' && charAt <= 'Z')) {
                                        i2 = charAt - 13;
                                    }
                                    sb.append(charAt);
                                } else {
                                    i2 = charAt + 13;
                                }
                                charAt = (char) i2;
                                sb.append(charAt);
                            }
                            byte[] bytes = (str2 + c2 + sb.toString()).getBytes("iso-8859-1");
                            MessageDigest instance = MessageDigest.getInstance("SHA-1");
                            instance.update(bytes, 0, bytes.length);
                            if (!a2.d().equals(i.a(instance.digest()))) {
                                com.facebook.ads.internal.q.d.a.a(this.a, "network", b.h, (Exception) new com.facebook.ads.internal.protocol.i());
                            }
                            byte[] bytes2 = (c2 + str2 + sb.toString()).getBytes("iso-8859-1");
                            MessageDigest instance2 = MessageDigest.getInstance("SHA-1");
                            instance2.update(bytes2, 0, bytes2.length);
                            e.a((d) new com.facebook.ads.internal.f.a(c2, i.a(instance2.digest())), this.a);
                        }
                        if (!TextUtils.isEmpty(a2.e()) && !TextUtils.isEmpty(str2)) {
                            new com.facebook.ads.internal.k.a(this.a, str2, a2.e()).a();
                        }
                    }
                    a(gVar);
                    return;
                case ERROR:
                    h hVar = (h) a2;
                    String f2 = hVar.f();
                    AdErrorType adErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(hVar.g(), AdErrorType.ERROR_MESSAGE);
                    if (f2 != null) {
                        str = f2;
                    }
                    aVar = com.facebook.ads.internal.protocol.a.a(adErrorTypeFromCode, str);
                    break;
                default:
                    aVar = com.facebook.ads.internal.protocol.a.a(AdErrorType.UNKNOWN_RESPONSE, str);
                    break;
            }
            a(aVar);
        } catch (Exception e2) {
            a(com.facebook.ads.internal.protocol.a.a(AdErrorType.PARSER_FAILURE, e2.getMessage()));
        }
    }

    /* access modifiers changed from: private */
    public com.facebook.ads.internal.p.a.b b() {
        return new com.facebook.ads.internal.p.a.b() {
            /* access modifiers changed from: package-private */
            public void a(com.facebook.ads.internal.p.a.m mVar) {
                a.b(c.this.f);
                com.facebook.ads.internal.p.a.a unused = c.this.g = null;
                try {
                    n a2 = mVar.a();
                    if (a2 != null) {
                        String e = a2.e();
                        f a3 = c.this.b.a(e);
                        if (a3.b() == f.a.ERROR) {
                            h hVar = (h) a3;
                            String f = hVar.f();
                            AdErrorType adErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(hVar.g(), AdErrorType.ERROR_MESSAGE);
                            c cVar = c.this;
                            if (f != null) {
                                e = f;
                            }
                            cVar.a(com.facebook.ads.internal.protocol.a.a(adErrorTypeFromCode, e));
                            return;
                        }
                    }
                } catch (JSONException unused2) {
                }
                c.this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NETWORK_ERROR, mVar.getMessage()));
            }

            public void a(n nVar) {
                if (nVar != null) {
                    String e = nVar.e();
                    a.b(c.this.f);
                    com.facebook.ads.internal.p.a.a unused = c.this.g = null;
                    c.this.a(e);
                }
            }

            public void a(Exception exc) {
                if (com.facebook.ads.internal.p.a.m.class.equals(exc.getClass())) {
                    a((com.facebook.ads.internal.p.a.m) exc);
                } else {
                    c.this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NETWORK_ERROR, exc.getMessage()));
                }
            }
        };
    }

    public void a() {
        if (this.g != null) {
            this.g.c(1);
            this.g.b(1);
            this.g = null;
        }
    }

    public void a(final b bVar) {
        a();
        if (s.a(this.a) == s.a.NONE) {
            a(new com.facebook.ads.internal.protocol.a(AdErrorType.NETWORK_ERROR, "No network connection"));
            return;
        }
        this.f = bVar;
        com.facebook.ads.internal.g.a.a(this.a);
        if (a.a(bVar)) {
            String c2 = a.c(bVar);
            if (c2 != null) {
                a(c2);
            } else {
                a(com.facebook.ads.internal.protocol.a.a(AdErrorType.LOAD_TOO_FREQUENTLY, (String) null));
            }
        } else {
            j.submit(new Runnable() {
                /* JADX WARNING: Can't wrap try/catch for region: R(9:8|9|10|11|12|(2:18|(1:20)(3:21|23|24))|22|23|24) */
                /* JADX WARNING: Code restructure failed: missing block: B:25:0x00f0, code lost:
                    r0 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:26:0x00f1, code lost:
                    com.facebook.ads.internal.o.c.a(r6.b, com.facebook.ads.internal.protocol.a.a(com.facebook.ads.internal.protocol.AdErrorType.AD_REQUEST_FAILED, r0.getMessage()));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:27:0x0100, code lost:
                    return;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0095 */
                /* JADX WARNING: Removed duplicated region for block: B:20:0x00b3 A[Catch:{ Exception -> 0x00f0 }] */
                /* JADX WARNING: Removed duplicated region for block: B:21:0x00b4 A[Catch:{ Exception -> 0x00f0 }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r6 = this;
                        com.facebook.ads.internal.o.c r0 = com.facebook.ads.internal.o.c.this
                        android.content.Context r0 = r0.a
                        com.facebook.ads.internal.c.b.a(r0)
                        com.facebook.ads.internal.o.b r0 = r3
                        com.facebook.ads.internal.protocol.h r0 = r0.e()
                        boolean r0 = r0.a()
                        if (r0 == 0) goto L_0x003b
                        com.facebook.ads.internal.o.b r0 = r3     // Catch:{ b -> 0x0021 }
                        com.facebook.ads.internal.protocol.h r0 = r0.e()     // Catch:{ b -> 0x0021 }
                        java.lang.String r1 = com.facebook.ads.internal.c.b.b     // Catch:{ b -> 0x0021 }
                        r0.a(r1)     // Catch:{ b -> 0x0021 }
                        goto L_0x002b
                    L_0x0021:
                        r0 = move-exception
                        com.facebook.ads.internal.o.c r1 = com.facebook.ads.internal.o.c.this
                        com.facebook.ads.internal.protocol.a r0 = com.facebook.ads.internal.protocol.a.a(r0)
                        r1.a((com.facebook.ads.internal.protocol.a) r0)
                    L_0x002b:
                        com.facebook.ads.internal.o.c r0 = com.facebook.ads.internal.o.c.this
                        com.facebook.ads.internal.o.b r1 = r3
                        com.facebook.ads.internal.protocol.h r1 = r1.e()
                        java.lang.String r1 = r1.b()
                        r0.a((java.lang.String) r1)
                        return
                    L_0x003b:
                        com.facebook.ads.internal.o.c r0 = com.facebook.ads.internal.o.c.this
                        com.facebook.ads.internal.o.b r1 = r3
                        java.util.Map r1 = r1.f()
                        java.util.Map unused = r0.d = r1
                        com.facebook.ads.internal.o.c r0 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x0095 }
                        java.util.Map r0 = r0.d     // Catch:{ Exception -> 0x0095 }
                        java.lang.String r1 = "M_BANNER_KEY"
                        java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x0095 }
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0095 }
                        r3.<init>()     // Catch:{ Exception -> 0x0095 }
                        com.facebook.ads.internal.o.c r4 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x0095 }
                        android.content.Context r4 = r4.a     // Catch:{ Exception -> 0x0095 }
                        java.lang.String r4 = r4.getPackageName()     // Catch:{ Exception -> 0x0095 }
                        r3.append(r4)     // Catch:{ Exception -> 0x0095 }
                        java.lang.String r4 = " "
                        r3.append(r4)     // Catch:{ Exception -> 0x0095 }
                        com.facebook.ads.internal.o.c r4 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x0095 }
                        android.content.Context r4 = r4.a     // Catch:{ Exception -> 0x0095 }
                        android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ Exception -> 0x0095 }
                        com.facebook.ads.internal.o.c r5 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x0095 }
                        android.content.Context r5 = r5.a     // Catch:{ Exception -> 0x0095 }
                        java.lang.String r5 = r5.getPackageName()     // Catch:{ Exception -> 0x0095 }
                        java.lang.String r4 = r4.getInstallerPackageName(r5)     // Catch:{ Exception -> 0x0095 }
                        r3.append(r4)     // Catch:{ Exception -> 0x0095 }
                        java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0095 }
                        byte[] r3 = r3.getBytes()     // Catch:{ Exception -> 0x0095 }
                        r4 = 2
                        byte[] r3 = android.util.Base64.encode(r3, r4)     // Catch:{ Exception -> 0x0095 }
                        r2.<init>(r3)     // Catch:{ Exception -> 0x0095 }
                        r0.put(r1, r2)     // Catch:{ Exception -> 0x0095 }
                    L_0x0095:
                        com.facebook.ads.internal.o.b r0 = r3     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.protocol.f r0 = r0.c     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.protocol.f r1 = com.facebook.ads.internal.protocol.f.NATIVE_250     // Catch:{ Exception -> 0x00f0 }
                        if (r0 == r1) goto L_0x00b6
                        com.facebook.ads.internal.o.b r0 = r3     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.protocol.f r0 = r0.c     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.protocol.f r1 = com.facebook.ads.internal.protocol.f.NATIVE_UNKNOWN     // Catch:{ Exception -> 0x00f0 }
                        if (r0 == r1) goto L_0x00b6
                        com.facebook.ads.internal.o.b r0 = r3     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.protocol.f r0 = r0.c     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.protocol.f r1 = com.facebook.ads.internal.protocol.f.NATIVE_BANNER     // Catch:{ Exception -> 0x00f0 }
                        if (r0 == r1) goto L_0x00b6
                        com.facebook.ads.internal.o.b r0 = r3     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.protocol.f r0 = r0.c     // Catch:{ Exception -> 0x00f0 }
                        if (r0 != 0) goto L_0x00b4
                        goto L_0x00b6
                    L_0x00b4:
                        r0 = 0
                        goto L_0x00b7
                    L_0x00b6:
                        r0 = 1
                    L_0x00b7:
                        com.facebook.ads.internal.o.c r1 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.o.c r2 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x00f0 }
                        android.content.Context r2 = r2.a     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.p.a.a r0 = com.facebook.ads.internal.q.c.d.b(r2, r0)     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.p.a.a unused = r1.g = r0     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.o.c r0 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.p.a.a r0 = r0.g     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.o.c r1 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x00f0 }
                        java.lang.String r1 = r1.h     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.o.c r2 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.p.a.a r2 = r2.g     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.p.a.p r2 = r2.b()     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.o.c r3 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x00f0 }
                        java.util.Map r3 = r3.d     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.p.a.p r2 = r2.a((java.util.Map<? extends java.lang.String, ? extends java.lang.String>) r3)     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.o.c r3 = com.facebook.ads.internal.o.c.this     // Catch:{ Exception -> 0x00f0 }
                        com.facebook.ads.internal.p.a.b r3 = r3.b()     // Catch:{ Exception -> 0x00f0 }
                        r0.a((java.lang.String) r1, (com.facebook.ads.internal.p.a.p) r2, (com.facebook.ads.internal.p.a.b) r3)     // Catch:{ Exception -> 0x00f0 }
                        return
                    L_0x00f0:
                        r0 = move-exception
                        com.facebook.ads.internal.o.c r1 = com.facebook.ads.internal.o.c.this
                        com.facebook.ads.internal.protocol.AdErrorType r2 = com.facebook.ads.internal.protocol.AdErrorType.AD_REQUEST_FAILED
                        java.lang.String r0 = r0.getMessage()
                        com.facebook.ads.internal.protocol.a r0 = com.facebook.ads.internal.protocol.a.a(r2, r0)
                        r1.a((com.facebook.ads.internal.protocol.a) r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.o.c.AnonymousClass1.run():void");
                }
            });
        }
    }

    public void a(a aVar) {
        this.e = aVar;
    }
}
