package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzeq;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzhc;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhq;
import com.google.android.gms.internal.zzhr;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqh;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@zzme
public class zzk extends zzeq.zza {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();
    private final zze zzsv;
    private final zzka zzsz;
    /* access modifiers changed from: private */
    public final zzep zztk;
    /* access modifiers changed from: private */
    @Nullable
    public final zzhp zztl;
    /* access modifiers changed from: private */
    @Nullable
    public final zzhq zztm;
    /* access modifiers changed from: private */
    public final SimpleArrayMap<String, zzhs> zztn;
    /* access modifiers changed from: private */
    public final SimpleArrayMap<String, zzhr> zzto;
    /* access modifiers changed from: private */
    public final zzhc zztp;
    private final List<String> zztq;
    /* access modifiers changed from: private */
    public final zzex zztr;
    private final String zzts;
    private final zzqh zztt;
    /* access modifiers changed from: private */
    @Nullable
    public WeakReference<zzs> zztu;

    zzk(Context context, String str, zzka zzka, zzqh zzqh, zzep zzep, zzhp zzhp, zzhq zzhq, SimpleArrayMap<String, zzhs> simpleArrayMap, SimpleArrayMap<String, zzhr> simpleArrayMap2, zzhc zzhc, zzex zzex, zze zze) {
        this.mContext = context;
        this.zzts = str;
        this.zzsz = zzka;
        this.zztt = zzqh;
        this.zztk = zzep;
        this.zztm = zzhq;
        this.zztl = zzhp;
        this.zztn = simpleArrayMap;
        this.zzto = simpleArrayMap2;
        this.zztp = zzhc;
        this.zztq = zzci();
        this.zztr = zzex;
        this.zzsv = zze;
    }

    /* access modifiers changed from: private */
    public List<String> zzci() {
        ArrayList arrayList = new ArrayList();
        if (this.zztm != null) {
            arrayList.add("1");
        }
        if (this.zztl != null) {
            arrayList.add("2");
        }
        if (this.zztn.size() > 0) {
            arrayList.add("3");
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        return r2;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getMediationAdapterClassName() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzs> r1 = r3.zztu     // Catch:{ all -> 0x001a }
            r2 = 0
            if (r1 == 0) goto L_0x0018
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzs> r1 = r3.zztu     // Catch:{ all -> 0x001a }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x001a }
            com.google.android.gms.ads.internal.zzs r1 = (com.google.android.gms.ads.internal.zzs) r1     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x0016
            java.lang.String r2 = r1.getMediationAdapterClassName()     // Catch:{ all -> 0x001a }
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x001a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzk.getMediationAdapterClassName():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isLoading() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzs> r1 = r3.zztu     // Catch:{ all -> 0x001a }
            r2 = 0
            if (r1 == 0) goto L_0x0018
            java.lang.ref.WeakReference<com.google.android.gms.ads.internal.zzs> r1 = r3.zztu     // Catch:{ all -> 0x001a }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x001a }
            com.google.android.gms.ads.internal.zzs r1 = (com.google.android.gms.ads.internal.zzs) r1     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x0016
            boolean r2 = r1.isLoading()     // Catch:{ all -> 0x001a }
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x001a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzk.isLoading():boolean");
    }

    /* access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        zzpo.zzXC.post(runnable);
    }

    /* access modifiers changed from: protected */
    public zzs zzcj() {
        return new zzs(this.mContext, this.zzsv, zzeg.zzk(this.mContext), this.zzts, this.zzsz, this.zztt);
    }

    public void zzf(final zzec zzec) {
        runOnUiThread(new Runnable() {
            public void run() {
                synchronized (zzk.this.zzrJ) {
                    zzs zzcj = zzk.this.zzcj();
                    WeakReference unused = zzk.this.zztu = new WeakReference(zzcj);
                    zzcj.zzb(zzk.this.zztl);
                    zzcj.zzb(zzk.this.zztm);
                    zzcj.zza((SimpleArrayMap<String, zzhs>) zzk.this.zztn);
                    zzcj.zza(zzk.this.zztk);
                    zzcj.zzb((SimpleArrayMap<String, zzhr>) zzk.this.zzto);
                    zzcj.zzb((List<String>) zzk.this.zzci());
                    zzcj.zzb(zzk.this.zztp);
                    zzcj.zza(zzk.this.zztr);
                    zzcj.zzb(zzec);
                }
            }
        });
    }
}
