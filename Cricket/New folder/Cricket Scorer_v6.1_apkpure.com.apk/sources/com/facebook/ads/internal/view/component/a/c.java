package com.facebook.ads.internal.view.component.a;

import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.component.a;

public final class c {
    private static final int a = x.a.heightPixels;
    private static final int b = x.a.widthPixels;

    private static float a(h hVar) {
        int h = hVar.c().h();
        int i = hVar.c().i();
        if (i > 0) {
            return ((float) h) / ((float) i);
        }
        return -1.0f;
    }

    private static int a(int i) {
        int a2 = x.a(16) + (a.a * 2);
        return (a - i) - (a2 + (2 * e.a));
    }

    public static b a(d dVar) {
        b bVar;
        boolean z = true;
        d a2 = dVar.k() == 1 ? dVar.g().b().a() : dVar.g().b().b();
        h hVar = dVar.g().d().get(0);
        double a3 = (double) a(hVar);
        if (a(dVar.k(), dVar.j(), a3)) {
            if (dVar.k() != 2) {
                z = false;
            }
            bVar = new a(dVar, a2, z);
        } else {
            bVar = new e(dVar, a(a3), a2);
        }
        bVar.a(hVar, dVar.g().c(), a3);
        return bVar;
    }

    private static boolean a(double d) {
        return d < 0.9d;
    }

    private static boolean a(double d, int i) {
        return a(i) < b(d);
    }

    private static boolean a(int i, int i2, double d) {
        return i == 2 || a(d, i2);
    }

    private static int b(double d) {
        return (int) (((double) (b - (2 * e.a))) / d);
    }
}
