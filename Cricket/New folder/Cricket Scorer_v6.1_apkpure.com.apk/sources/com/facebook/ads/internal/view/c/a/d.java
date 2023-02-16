package com.facebook.ads.internal.view.c.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.component.a.d;
import java.util.List;

public class d extends RecyclerView.Adapter<g> {
    private final c a;
    @Nullable
    private final b b;
    private final com.facebook.ads.internal.r.a c;
    private final u d;
    private final com.facebook.ads.internal.adapters.a.d e;
    @Nullable
    private a.C0008a f;
    private int g;
    private int h;
    private String i;
    private int j;
    private int k;
    private List<b> l;
    private final a m;

    public interface a {
        void a(int i);
    }

    d(List<b> list, c cVar, b bVar, com.facebook.ads.internal.r.a aVar, u uVar, a.C0008a aVar2, com.facebook.ads.internal.adapters.a.d dVar, String str, int i2, int i3, int i4, int i5, a aVar3) {
        this.a = cVar;
        this.b = bVar;
        this.c = aVar;
        this.d = uVar;
        this.f = aVar2;
        this.l = list;
        this.h = i2;
        this.e = dVar;
        this.j = i5;
        this.i = str;
        this.g = i4;
        this.k = i3;
        this.m = aVar3;
    }

    /* renamed from: a */
    public g onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new g(com.facebook.ads.internal.view.component.a.a.c.a(new d.a(viewGroup.getContext(), this.a, this.f, (g) null, (View) null, this.c, this.d).a(), this.j, this.e, this.i, this.m), this.c, this.h, this.g, this.k, this.l.size());
    }

    /* renamed from: a */
    public void onBindViewHolder(g gVar, int i2) {
        gVar.a(this.l.get(i2), this.a, this.b, this.d, this.i, false);
    }

    public int getItemCount() {
        return this.l.size();
    }
}
