package com.facebook.ads.internal.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.b.e;
import com.facebook.ads.internal.view.c;
import com.facebook.ads.internal.view.hscroll.b;
import com.facebook.ads.internal.view.l;
import com.facebook.ads.internal.view.s;
import java.util.List;

public class h extends RecyclerView.Adapter<c> {
    /* access modifiers changed from: private */
    public static final int a = Color.argb(51, 0, 0, 0);
    /* access modifiers changed from: private */
    public final a.C0006a b = new a.C0006a() {
        public void a() {
            if (h.this.f != null) {
                h.this.f.a();
            }
        }
    };
    private final List<f> c;
    private final int d;
    private final int e;
    /* access modifiers changed from: private */
    @Nullable
    public a f;

    public interface a {
        void a();
    }

    public h(b bVar, List<f> list) {
        float f2 = bVar.getContext().getResources().getDisplayMetrics().density;
        this.c = list;
        this.d = Math.round(f2 * 1.0f);
        this.e = bVar.getChildSpacing();
    }

    /* renamed from: a */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(new l(viewGroup.getContext()));
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    /* renamed from: a */
    public void onBindViewHolder(c cVar, final int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.e * 2 : this.e, 0, i >= this.c.size() + -1 ? this.e * 2 : this.e, 0);
        l lVar = (l) cVar.a;
        lVar.setLayoutParams(marginLayoutParams);
        lVar.setPadding(this.d, this.d, this.d, this.d);
        final s sVar = (s) lVar.getAdContentsView();
        x.a((View) sVar, 0);
        sVar.setImageDrawable((Drawable) null);
        final f fVar = this.c.get(i);
        fVar.a((View) lVar, (g) lVar);
        com.facebook.ads.internal.n.h k = fVar.k();
        if (k != null) {
            d a2 = new d((ImageView) sVar).a();
            a2.a((e) new e() {
                public void a(boolean z) {
                    if (i == 0) {
                        fVar.a(h.this.b);
                    }
                    fVar.a(z, true);
                    x.a((View) sVar, h.a);
                }
            });
            a2.a(k.a());
        }
    }

    public int getItemCount() {
        return this.c.size();
    }
}
