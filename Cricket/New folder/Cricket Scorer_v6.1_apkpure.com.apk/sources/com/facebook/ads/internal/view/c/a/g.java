package com.facebook.ads.internal.view.c.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.view.component.a.a.b;
import java.util.Map;

class g extends RecyclerView.ViewHolder {
    private final b a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    @Nullable
    public a g;
    private a.C0006a h;
    /* access modifiers changed from: private */
    public a i;

    g(b bVar, a aVar, int i2, int i3, int i4, int i5) {
        super(bVar);
        this.a = bVar;
        this.i = aVar;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
    }

    private String a(com.facebook.ads.internal.d.b bVar, String str) {
        String b2 = (bVar == null || str == null) ? "" : bVar.b(str);
        return !TextUtils.isEmpty(b2) ? b2 : str;
    }

    private void a(c cVar, u uVar, String str, final b bVar) {
        if (!this.f) {
            if (this.g != null) {
                this.g.c();
                this.g = null;
            }
            final Map<String, String> a2 = bVar.a();
            final String str2 = str;
            final u uVar2 = uVar;
            final c cVar2 = cVar;
            this.h = new a.C0006a() {
                public void a() {
                    if (!g.this.i.b() && !TextUtils.isEmpty(str2)) {
                        if (g.this.g != null) {
                            g.this.g.a((Map<String, String>) a2);
                        }
                        a2.put("touch", k.a(uVar2.e()));
                        cVar2.a(str2, a2);
                    }
                    boolean unused = g.this.f = true;
                }
            };
            this.g = new a(this.a, 10, this.h);
            this.g.a(100);
            this.g.b(100);
            this.a.setOnAssetsLoadedListener(new b.a() {
                public void a() {
                    if (bVar.b() == 0) {
                        g.this.i.a();
                    }
                    g.this.g.a();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar, c cVar, com.facebook.ads.internal.d.b bVar2, u uVar, String str, boolean z) {
        int b2 = bVar.b();
        this.a.setTag(-1593835536, Integer.valueOf(b2));
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(this.b, -2);
        marginLayoutParams.setMargins(b2 == 0 ? this.c : this.d, 0, b2 >= this.e + -1 ? this.c : this.d, 0);
        String g2 = bVar.c().c().g();
        String a2 = bVar.c().c().a();
        this.a.setIsVideo(!TextUtils.isEmpty(a2));
        if (this.a.f()) {
            this.a.setVideoPlaceholderUrl(g2);
            this.a.setVideoUrl(a(bVar2, a2));
            if (z) {
                this.a.h();
            }
        } else {
            this.a.setImageUrl(g2);
        }
        this.a.setLayoutParams(marginLayoutParams);
        this.a.a(bVar.c().a().a(), bVar.c().a().c());
        this.a.a(bVar.c().b().b(), bVar.c().b().a(), bVar.a());
        this.a.a(bVar.a());
        a(cVar, uVar, str, bVar);
    }
}
