package com.facebook.ads.internal.view.c.a;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.facebook.ads.internal.view.c.a.d;
import com.facebook.ads.internal.view.component.a.a.b;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class a extends RecyclerView.OnScrollListener {
    private final LinearLayoutManager a;
    private final int b;
    private final RecyclerView.SmoothScroller c;
    private final Set<Integer> d = new HashSet();
    private List<b> e;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.r.a f;
    private boolean g = true;
    @Nullable
    private d.a h;
    private boolean i = true;
    /* access modifiers changed from: private */
    public boolean j = true;
    /* access modifiers changed from: private */
    public boolean k;
    private final b.e l = new b.e() {
        private float b = 0.0f;

        public float a() {
            return this.b;
        }

        public void a(float f) {
            this.b = f;
        }
    };
    private final b.c m = new b.c() {
        public void a(int i) {
            a.this.a(i, true);
            if (a.this.f()) {
                a.this.d();
            } else {
                a.this.a(i);
            }
        }
    };
    private final b.d n = new b.d() {
        public void a(View view) {
            b bVar = (b) view;
            bVar.j();
            if (a.this.k) {
                boolean unused = a.this.j = true;
            }
            if (a.this.f.b() && ((Integer) bVar.getTag(-1593835536)).intValue() == 0) {
                a.this.f.a();
            }
        }

        public void b(View view) {
            if (a.this.k) {
                boolean unused = a.this.j = false;
            }
        }
    };

    a(c cVar, int i2, List<b> list, com.facebook.ads.internal.r.a aVar) {
        this.a = cVar.getLayoutManager();
        this.b = i2;
        this.e = list;
        this.f = aVar;
        this.c = new LinearSmoothScroller(cVar.getContext());
        cVar.addOnScrollListener(this);
    }

    @Nullable
    private b a(int i2, int i3) {
        return a(i2, i3, true);
    }

    @Nullable
    private b a(int i2, int i3, boolean z) {
        b bVar = null;
        while (i2 <= i3) {
            b bVar2 = (b) this.a.findViewByPosition(i2);
            if (bVar2.g()) {
                return null;
            }
            boolean a2 = a((View) bVar2);
            if (bVar == null && bVar2.f() && a2 && !this.d.contains(Integer.valueOf(i2)) && (!z || b(bVar2))) {
                bVar = bVar2;
            }
            if (bVar2.f() && !a2) {
                a(i2, false);
            }
            i2++;
        }
        return bVar;
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        b a2 = a(i2 + 1, this.a.findLastVisibleItemPosition(), false);
        if (a2 != null) {
            a2.h();
            b(((Integer) a2.getTag(-1593835536)).intValue());
        }
    }

    private void a(int i2, int i3, int i4) {
        if (f() && this.h != null) {
            int findFirstCompletelyVisibleItemPosition = this.a.findFirstCompletelyVisibleItemPosition();
            if (findFirstCompletelyVisibleItemPosition != -1) {
                i2 = findFirstCompletelyVisibleItemPosition;
            } else if (i4 >= 0) {
                i2 = i3;
            }
            this.h.a(i2);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, boolean z) {
        if (z) {
            this.d.add(Integer.valueOf(i2));
        } else {
            this.d.remove(Integer.valueOf(i2));
        }
    }

    private static void a(View view, boolean z) {
        view.setAlpha(z ? 1.0f : 0.5f);
    }

    private void a(b bVar, boolean z) {
        if (f()) {
            a((View) bVar, z);
        }
        if (!z && bVar.g()) {
            bVar.i();
        }
    }

    private static boolean a(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return ((float) rect.width()) / ((float) view.getWidth()) >= 0.15f;
    }

    private boolean a(b bVar) {
        if (!this.g || !bVar.f()) {
            return false;
        }
        this.g = false;
        return true;
    }

    private void b(int i2) {
        this.c.setTargetPosition(i2);
        this.a.startSmoothScroll(this.c);
    }

    private void b(int i2, int i3) {
        while (i2 <= i3) {
            c(i2);
            i2++;
        }
    }

    private static boolean b(b bVar) {
        return ((int) (bVar.getX() + ((float) bVar.getWidth()))) <= ((int) (((float) bVar.getWidth()) * 1.3f));
    }

    private void c(int i2) {
        b bVar = (b) this.a.findViewByPosition(i2);
        if (a((View) bVar)) {
            a(bVar, true);
        }
        if (a(bVar)) {
            this.l.a(this.e.get(((Integer) bVar.getTag(-1593835536)).intValue()).c().c().f() ? 1.0f : 0.0f);
        }
    }

    private void c(int i2, int i3) {
        d(i2);
        d(i3);
    }

    /* access modifiers changed from: private */
    public void d() {
        int findFirstCompletelyVisibleItemPosition = this.a.findFirstCompletelyVisibleItemPosition();
        if (findFirstCompletelyVisibleItemPosition != -1 && findFirstCompletelyVisibleItemPosition < this.e.size() - 1) {
            b(findFirstCompletelyVisibleItemPosition + 1);
        }
    }

    private void d(int i2) {
        b bVar = (b) this.a.findViewByPosition(i2);
        if (!a((View) bVar)) {
            a(bVar, false);
        }
    }

    private void e() {
        b a2;
        if (this.j && (a2 = a(this.a.findFirstVisibleItemPosition(), this.a.findLastVisibleItemPosition())) != null) {
            a2.h();
        }
    }

    /* access modifiers changed from: private */
    public boolean f() {
        return this.b == 1;
    }

    public b.e a() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public void a(d.a aVar) {
        this.h = aVar;
    }

    public b.c b() {
        return this.m;
    }

    public b.d c() {
        return this.n;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        super.onScrollStateChanged(recyclerView, i2);
        if (i2 == 0) {
            this.k = true;
            e();
        }
    }

    public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
        super.onScrolled(recyclerView, i2, i3);
        this.k = false;
        if (this.i) {
            this.k = true;
            e();
            this.i = false;
        }
        int findFirstVisibleItemPosition = this.a.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.a.findLastVisibleItemPosition();
        c(findFirstVisibleItemPosition, findLastVisibleItemPosition);
        b(findFirstVisibleItemPosition, findLastVisibleItemPosition);
        a(findFirstVisibleItemPosition, findLastVisibleItemPosition, i2);
    }
}
