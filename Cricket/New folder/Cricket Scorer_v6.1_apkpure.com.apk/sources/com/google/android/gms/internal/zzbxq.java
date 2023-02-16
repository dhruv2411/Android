package com.google.android.gms.internal;

import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class zzbxq implements Cloneable {
    private Object value;
    private zzbxo<?, ?> zzcuO;
    private List<zzbxv> zzcuP = new ArrayList();

    zzbxq() {
    }

    private byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzu()];
        zza(zzbxm.zzag(bArr));
        return bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbxq)) {
            return false;
        }
        zzbxq zzbxq = (zzbxq) obj;
        if (this.value == null || zzbxq.value == null) {
            if (this.zzcuP != null && zzbxq.zzcuP != null) {
                return this.zzcuP.equals(zzbxq.zzcuP);
            }
            try {
                return Arrays.equals(toByteArray(), zzbxq.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzcuO != zzbxq.zzcuO) {
            return false;
        } else {
            return !this.zzcuO.zzckM.isArray() ? this.value.equals(zzbxq.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzbxq.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzbxq.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzbxq.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzbxq.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzbxq.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzbxq.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzbxq.value);
        }
    }

    public int hashCode() {
        try {
            return MetaDo.META_OFFSETWINDOWORG + Arrays.hashCode(toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbxm zzbxm) throws IOException {
        if (this.value != null) {
            this.zzcuO.zza(this.value, zzbxm);
            return;
        }
        for (zzbxv zza : this.zzcuP) {
            zza.zza(zzbxm);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbxv zzbxv) {
        this.zzcuP.add(zzbxv);
    }

    /* renamed from: zzaeK */
    public final zzbxq clone() {
        Object clone;
        zzbxq zzbxq = new zzbxq();
        try {
            zzbxq.zzcuO = this.zzcuO;
            if (this.zzcuP == null) {
                zzbxq.zzcuP = null;
            } else {
                zzbxq.zzcuP.addAll(this.zzcuP);
            }
            if (this.value == null) {
                return zzbxq;
            }
            if (this.value instanceof zzbxt) {
                clone = (zzbxt) ((zzbxt) this.value).clone();
            } else if (this.value instanceof byte[]) {
                clone = ((byte[]) this.value).clone();
            } else {
                int i = 0;
                if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zzbxq.value = bArr2;
                    while (i < bArr.length) {
                        bArr2[i] = (byte[]) bArr[i].clone();
                        i++;
                    }
                } else if (this.value instanceof boolean[]) {
                    clone = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    clone = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    clone = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    clone = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    clone = ((double[]) this.value).clone();
                } else if (this.value instanceof zzbxt[]) {
                    zzbxt[] zzbxtArr = (zzbxt[]) this.value;
                    zzbxt[] zzbxtArr2 = new zzbxt[zzbxtArr.length];
                    zzbxq.value = zzbxtArr2;
                    while (i < zzbxtArr.length) {
                        zzbxtArr2[i] = (zzbxt) zzbxtArr[i].clone();
                        i++;
                    }
                }
                return zzbxq;
            }
            zzbxq.value = clone;
            return zzbxq;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public <T> T zzb(zzbxo<?, T> zzbxo) {
        if (this.value == null) {
            this.zzcuO = zzbxo;
            this.value = zzbxo.zzac(this.zzcuP);
            this.zzcuP = null;
        } else if (!this.zzcuO.equals(zzbxo)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public int zzu() {
        if (this.value != null) {
            return this.zzcuO.zzaU(this.value);
        }
        int i = 0;
        for (zzbxv zzu : this.zzcuP) {
            i += zzu.zzu();
        }
        return i;
    }
}
