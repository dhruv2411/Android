package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;
import com.google.android.gms.tagmanager.zzm;

@TargetApi(12)
class zzbi<K, V> implements zzl<K, V> {
    private LruCache<K, V> zzbGF;

    zzbi(int i, final zzm.zza<K, V> zza) {
        this.zzbGF = new LruCache<K, V>(this, i) {
            /* access modifiers changed from: protected */
            public int sizeOf(K k, V v) {
                return zza.sizeOf(k, v);
            }
        };
    }

    public V get(K k) {
        return this.zzbGF.get(k);
    }

    public void zzh(K k, V v) {
        this.zzbGF.put(k, v);
    }
}
