package com.facebook.ads.internal.j;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.j.f;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class e<T extends f, E extends d> {
    private final Map<Class<E>, List<WeakReference<T>>> a = new HashMap();
    private final Queue<E> b = new ArrayDeque();

    private void a(List<WeakReference<T>> list) {
        if (list != null) {
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                WeakReference weakReference = list.get(i2);
                if (weakReference.get() != null) {
                    list.set(i, weakReference);
                    i++;
                }
            }
            for (int size = list.size() - 1; size >= i; size--) {
                list.remove(size);
            }
        }
    }

    private void b(E e) {
        List list;
        if (this.a != null && (list = this.a.get(e.getClass())) != null) {
            a(list);
            if (!list.isEmpty()) {
                for (WeakReference weakReference : new ArrayList(list)) {
                    f fVar = (f) weakReference.get();
                    if (fVar != null && fVar.b(e)) {
                        fVar.a(e);
                    }
                }
            }
        }
    }

    public synchronized void a(E e) {
        if (this.b.isEmpty()) {
            this.b.add(e);
            while (!this.b.isEmpty()) {
                b((d) this.b.peek());
                this.b.remove();
            }
        } else {
            this.b.add(e);
        }
    }

    public synchronized void a(T... tArr) {
        if (tArr != null) {
            for (T a2 : tArr) {
                a(a2);
            }
        }
    }

    public synchronized boolean a(T t) {
        if (t == null) {
            return false;
        }
        Class a2 = t.a();
        if (this.a.get(a2) == null) {
            this.a.put(a2, new ArrayList());
        }
        List list = this.a.get(a2);
        a(list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (((WeakReference) list.get(i)).get() == t) {
                return false;
            }
        }
        return list.add(new WeakReference(t));
    }

    public synchronized void b(T... tArr) {
        if (tArr != null) {
            for (T b2 : tArr) {
                b(b2);
            }
        }
    }

    public synchronized boolean b(@Nullable T t) {
        if (t == null) {
            return false;
        }
        List list = this.a.get(t.a());
        if (list == null) {
            return false;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (((WeakReference) list.get(i)).get() == t) {
                ((WeakReference) list.get(i)).clear();
                return true;
            }
        }
        return false;
    }
}
