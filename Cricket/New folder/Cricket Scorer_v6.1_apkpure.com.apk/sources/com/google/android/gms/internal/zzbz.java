package com.google.android.gms.internal;

import com.google.android.gms.internal.zzay;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzbz {
    protected static final String TAG = "zzbz";
    private final String className;
    private final zzbd zzpz;
    private final String zzrc;
    private final int zzrd = 2;
    private volatile Method zzre = null;
    private List<Class> zzrf;
    private CountDownLatch zzrg = new CountDownLatch(1);

    public zzbz(zzbd zzbd, String str, String str2, List<Class> list) {
        this.zzpz = zzbd;
        this.className = str;
        this.zzrc = str2;
        this.zzrf = new ArrayList(list);
        this.zzpz.zzaI().submit(new Runnable() {
            public void run() {
                zzbz.this.zzbl();
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0042, code lost:
        if (r4.zzre == null) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzbl() {
        /*
            r4 = this;
            com.google.android.gms.internal.zzbd r0 = r4.zzpz     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            dalvik.system.DexClassLoader r0 = r0.zzaJ()     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            com.google.android.gms.internal.zzbd r1 = r4.zzpz     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            byte[] r1 = r1.zzaL()     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.String r2 = r4.className     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.String r1 = r4.zzd(r1, r2)     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.Class r0 = r0.loadClass(r1)     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            if (r0 != 0) goto L_0x001e
        L_0x0018:
            java.util.concurrent.CountDownLatch r0 = r4.zzrg
            r0.countDown()
            return
        L_0x001e:
            com.google.android.gms.internal.zzbd r1 = r4.zzpz     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            byte[] r1 = r1.zzaL()     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.String r2 = r4.zzrc     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.String r1 = r4.zzd(r1, r2)     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.util.List<java.lang.Class> r2 = r4.zzrf     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.util.List<java.lang.Class> r3 = r4.zzrf     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            int r3 = r3.size()     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.Object[] r2 = r2.toArray(r3)     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.Class[] r2 = (java.lang.Class[]) r2     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.reflect.Method r0 = r0.getMethod(r1, r2)     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            r4.zzre = r0     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            java.lang.reflect.Method r0 = r4.zzre     // Catch:{ zza | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException -> 0x004c, all -> 0x0045 }
            if (r0 != 0) goto L_0x004c
            goto L_0x0018
        L_0x0045:
            r0 = move-exception
            java.util.concurrent.CountDownLatch r1 = r4.zzrg
            r1.countDown()
            throw r0
        L_0x004c:
            java.util.concurrent.CountDownLatch r0 = r4.zzrg
            r0.countDown()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbz.zzbl():void");
    }

    private String zzd(byte[] bArr, String str) throws zzay.zza, UnsupportedEncodingException {
        return new String(this.zzpz.zzaK().zzc(bArr, str), "UTF-8");
    }

    public Method zzbm() {
        if (this.zzre != null) {
            return this.zzre;
        }
        try {
            if (!this.zzrg.await(2, TimeUnit.SECONDS)) {
                return null;
            }
            return this.zzre;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
