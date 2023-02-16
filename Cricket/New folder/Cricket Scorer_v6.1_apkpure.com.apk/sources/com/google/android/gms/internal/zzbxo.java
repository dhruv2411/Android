package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbxn;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zzbxo<M extends zzbxn<M>, T> {
    public final int tag;
    protected final int type;
    protected final Class<T> zzckM;
    protected final boolean zzcuJ;

    private zzbxo(int i, Class<T> cls, int i2, boolean z) {
        this.type = i;
        this.zzckM = cls;
        this.tag = i2;
        this.zzcuJ = z;
    }

    public static <M extends zzbxn<M>, T extends zzbxt> zzbxo<M, T> zza(int i, Class<T> cls, long j) {
        return new zzbxo<>(i, cls, (int) j, false);
    }

    private T zzad(List<zzbxv> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            zzbxv zzbxv = list.get(i);
            if (zzbxv.zzbxZ.length != 0) {
                zza(zzbxv, (List<Object>) arrayList);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        T cast = this.zzckM.cast(Array.newInstance(this.zzckM.getComponentType(), size));
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(cast, i2, arrayList.get(i2));
        }
        return cast;
    }

    private T zzae(List<zzbxv> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.zzckM.cast(zzaN(zzbxl.zzaf(list.get(list.size() - 1).zzbxZ)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbxo)) {
            return false;
        }
        zzbxo zzbxo = (zzbxo) obj;
        return this.type == zzbxo.type && this.zzckM == zzbxo.zzckM && this.tag == zzbxo.tag && this.zzcuJ == zzbxo.zzcuJ;
    }

    public int hashCode() {
        return (31 * (((((1147 + this.type) * 31) + this.zzckM.hashCode()) * 31) + this.tag)) + (this.zzcuJ ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public void zza(zzbxv zzbxv, List<Object> list) {
        list.add(zzaN(zzbxl.zzaf(zzbxv.zzbxZ)));
    }

    /* access modifiers changed from: package-private */
    public void zza(Object obj, zzbxm zzbxm) throws IOException {
        if (this.zzcuJ) {
            zzc(obj, zzbxm);
        } else {
            zzb(obj, zzbxm);
        }
    }

    /* access modifiers changed from: protected */
    public Object zzaN(zzbxl zzbxl) {
        Class componentType = this.zzcuJ ? this.zzckM.getComponentType() : this.zzckM;
        try {
            switch (this.type) {
                case 10:
                    zzbxt zzbxt = (zzbxt) componentType.newInstance();
                    zzbxl.zza(zzbxt, zzbxw.zzrs(this.tag));
                    return zzbxt;
                case 11:
                    zzbxt zzbxt2 = (zzbxt) componentType.newInstance();
                    zzbxl.zza(zzbxt2);
                    return zzbxt2;
                default:
                    int i = this.type;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (InstantiationException e) {
            String valueOf = String.valueOf(componentType);
            StringBuilder sb2 = new StringBuilder(33 + String.valueOf(valueOf).length());
            sb2.append("Error creating instance of class ");
            sb2.append(valueOf);
            throw new IllegalArgumentException(sb2.toString(), e);
        } catch (IllegalAccessException e2) {
            String valueOf2 = String.valueOf(componentType);
            StringBuilder sb3 = new StringBuilder(33 + String.valueOf(valueOf2).length());
            sb3.append("Error creating instance of class ");
            sb3.append(valueOf2);
            throw new IllegalArgumentException(sb3.toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* access modifiers changed from: package-private */
    public int zzaU(Object obj) {
        return this.zzcuJ ? zzaV(obj) : zzaW(obj);
    }

    /* access modifiers changed from: protected */
    public int zzaV(Object obj) {
        int length = Array.getLength(obj);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += zzaW(Array.get(obj, i2));
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int zzaW(Object obj) {
        int zzrs = zzbxw.zzrs(this.tag);
        switch (this.type) {
            case 10:
                return zzbxm.zzb(zzrs, (zzbxt) obj);
            case 11:
                return zzbxm.zzc(zzrs, (zzbxt) obj);
            default:
                int i = this.type;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Unknown type ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final T zzac(List<zzbxv> list) {
        if (list == null) {
            return null;
        }
        return this.zzcuJ ? zzad(list) : zzae(list);
    }

    /* access modifiers changed from: protected */
    public void zzb(Object obj, zzbxm zzbxm) {
        try {
            zzbxm.zzrk(this.tag);
            switch (this.type) {
                case 10:
                    int zzrs = zzbxw.zzrs(this.tag);
                    zzbxm.zzb((zzbxt) obj);
                    zzbxm.zzN(zzrs, 4);
                    return;
                case 11:
                    zzbxm.zzc((zzbxt) obj);
                    return;
                default:
                    int i = this.type;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void zzc(Object obj, zzbxm zzbxm) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                zzb(obj2, zzbxm);
            }
        }
    }
}
