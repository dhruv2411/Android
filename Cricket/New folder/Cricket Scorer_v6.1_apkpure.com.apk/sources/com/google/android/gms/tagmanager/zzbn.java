package com.google.android.gms.tagmanager;

interface zzbn<T> {

    public enum zza {
        NOT_AVAILABLE,
        IO_ERROR,
        SERVER_ERROR,
        SERVER_UNAVAILABLE_ERROR
    }

    void onSuccess(T t);

    void zza(zza zza2);
}
