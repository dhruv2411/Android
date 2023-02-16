package com.facebook.ads.internal.q.a;

import java.lang.ref.WeakReference;

public abstract class y<T> implements Runnable {
    private final WeakReference<T> a;

    public y(T t) {
        this.a = new WeakReference<>(t);
    }

    public T a() {
        return this.a.get();
    }
}
