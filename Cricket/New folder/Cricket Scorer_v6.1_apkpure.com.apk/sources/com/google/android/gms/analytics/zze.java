package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zze {
    private final zzg zzabU;
    private boolean zzabV;
    private long zzabW;
    private long zzabX;
    private long zzabY;
    private long zzabZ;
    private long zzaca;
    private boolean zzacb;
    private final Map<Class<? extends zzf>, zzf> zzacc;
    private final List<zzi> zzacd;
    private final com.google.android.gms.common.util.zze zzuP;

    zze(zze zze) {
        this.zzabU = zze.zzabU;
        this.zzuP = zze.zzuP;
        this.zzabW = zze.zzabW;
        this.zzabX = zze.zzabX;
        this.zzabY = zze.zzabY;
        this.zzabZ = zze.zzabZ;
        this.zzaca = zze.zzaca;
        this.zzacd = new ArrayList(zze.zzacd);
        this.zzacc = new HashMap(zze.zzacc.size());
        for (Map.Entry next : zze.zzacc.entrySet()) {
            zzf zzc = zzc((Class) next.getKey());
            ((zzf) next.getValue()).zzb(zzc);
            this.zzacc.put((Class) next.getKey(), zzc);
        }
    }

    zze(zzg zzg, com.google.android.gms.common.util.zze zze) {
        zzac.zzw(zzg);
        zzac.zzw(zze);
        this.zzabU = zzg;
        this.zzuP = zze;
        this.zzabZ = 1800000;
        this.zzaca = 3024000000L;
        this.zzacc = new HashMap();
        this.zzacd = new ArrayList();
    }

    private static <T extends zzf> T zzc(Class<T> cls) {
        try {
            return (zzf) cls.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("dataType doesn't have default constructor", e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("dataType default constructor is not accessible", e2);
        }
    }

    public <T extends zzf> T zza(Class<T> cls) {
        return (zzf) this.zzacc.get(cls);
    }

    public void zza(zzf zzf) {
        zzac.zzw(zzf);
        Class cls = zzf.getClass();
        if (cls.getSuperclass() != zzf.class) {
            throw new IllegalArgumentException();
        }
        zzf.zzb(zzb(cls));
    }

    public <T extends zzf> T zzb(Class<T> cls) {
        T t = (zzf) this.zzacc.get(cls);
        if (t != null) {
            return t;
        }
        T zzc = zzc(cls);
        this.zzacc.put(cls, zzc);
        return zzc;
    }

    public zze zzmC() {
        return new zze(this);
    }

    public Collection<zzf> zzmD() {
        return this.zzacc.values();
    }

    public List<zzi> zzmE() {
        return this.zzacd;
    }

    public long zzmF() {
        return this.zzabW;
    }

    public void zzmG() {
        zzmK().zze(this);
    }

    public boolean zzmH() {
        return this.zzabV;
    }

    /* access modifiers changed from: package-private */
    public void zzmI() {
        this.zzabY = this.zzuP.elapsedRealtime();
        this.zzabW = this.zzabX != 0 ? this.zzabX : this.zzuP.currentTimeMillis();
        this.zzabV = true;
    }

    /* access modifiers changed from: package-private */
    public zzg zzmJ() {
        return this.zzabU;
    }

    /* access modifiers changed from: package-private */
    public zzh zzmK() {
        return this.zzabU.zzmK();
    }

    /* access modifiers changed from: package-private */
    public boolean zzmL() {
        return this.zzacb;
    }

    /* access modifiers changed from: package-private */
    public void zzmM() {
        this.zzacb = true;
    }

    public void zzq(long j) {
        this.zzabX = j;
    }
}
