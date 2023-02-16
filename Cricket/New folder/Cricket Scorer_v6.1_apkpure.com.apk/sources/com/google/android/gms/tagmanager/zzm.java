package com.google.android.gms.tagmanager;

import android.os.Build;

class zzm<K, V> {
    final zza<K, V> zzbET = new zza<K, V>(this) {
        public int sizeOf(K k, V v) {
            return 1;
        }
    };

    public interface zza<K, V> {
        int sizeOf(K k, V v);
    }

    public zzl<K, V> zza(int i, zza<K, V> zza2) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        zzzA();
        return new zzbi(i, zza2);
    }

    /* access modifiers changed from: package-private */
    public int zzzA() {
        return Build.VERSION.SDK_INT;
    }
}
