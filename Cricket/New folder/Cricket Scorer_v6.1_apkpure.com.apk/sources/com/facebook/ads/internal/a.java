package com.facebook.ads.internal;

import android.content.Context;
import android.os.Handler;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.e;
import com.facebook.ads.internal.adapters.y;
import com.facebook.ads.internal.adapters.z;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.o.g;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.f;
import com.facebook.ads.internal.protocol.h;
import com.facebook.ads.internal.q.a.l;
import com.facebook.ads.internal.q.a.o;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a implements c.a {
    /* access modifiers changed from: private */
    public final Context a;
    private final String b;
    private final c c;
    private final f d;
    private final d e;
    private final AdSize f;
    private final int g;
    private boolean h = true;
    /* access modifiers changed from: private */
    public final Handler i = new Handler();
    /* access modifiers changed from: private */
    public final Runnable j = new b(this);
    private final com.facebook.ads.internal.m.c k;
    private C0000a l;
    private com.facebook.ads.internal.h.c m;

    /* renamed from: com.facebook.ads.internal.a$a  reason: collision with other inner class name */
    public interface C0000a {
        void a(com.facebook.ads.internal.protocol.a aVar);

        void a(List<y> list);
    }

    private static final class b extends com.facebook.ads.internal.q.a.y<a> {
        public b(a aVar) {
            super(aVar);
        }

        public void run() {
            a aVar = (a) a();
            if (aVar != null) {
                if (com.facebook.ads.internal.q.e.a.a(aVar.a)) {
                    aVar.a();
                } else {
                    aVar.i.postDelayed(aVar.j, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                }
            }
        }
    }

    static {
        com.facebook.ads.internal.q.a.d.a();
    }

    public a(Context context, String str, f fVar, AdSize adSize, d dVar, int i2) {
        this.a = context;
        this.b = str;
        this.d = fVar;
        this.f = adSize;
        this.e = dVar;
        this.g = i2;
        this.c = new c(context);
        this.c.a((c.a) this);
        this.k = com.facebook.ads.internal.m.d.a(context);
        com.facebook.ads.internal.i.a.a(context).a();
    }

    private List<y> d() {
        com.facebook.ads.internal.h.c cVar = this.m;
        final ArrayList arrayList = new ArrayList(cVar.c());
        for (com.facebook.ads.internal.h.a d2 = cVar.d(); d2 != null; d2 = cVar.d()) {
            AdAdapter a2 = e.a(d2.a(), AdPlacementType.NATIVE);
            if (a2 != null && a2.getPlacementType() == AdPlacementType.NATIVE) {
                HashMap hashMap = new HashMap();
                hashMap.put("data", d2.b());
                hashMap.put("definition", cVar.a());
                ((y) a2).a(this.a, new z() {
                    public void a(y yVar) {
                        arrayList.add(yVar);
                    }

                    public void a(y yVar, com.facebook.ads.internal.protocol.a aVar) {
                    }

                    public void b(y yVar) {
                    }

                    public void c(y yVar) {
                    }
                }, this.k, hashMap, NativeAd.getViewTraversalPredicate());
            }
        }
        return arrayList;
    }

    public void a() {
        try {
            h hVar = new h(this.a, (String) null, (String) null, (f) null);
            com.facebook.ads.internal.o.b bVar = r2;
            com.facebook.ads.internal.o.b bVar2 = new com.facebook.ads.internal.o.b(this.a, new com.facebook.ads.internal.i.c(this.a, false), this.b, this.f != null ? new l(this.f.getHeight(), this.f.getWidth()) : null, this.d, this.e, (String) null, e.a(com.facebook.ads.internal.protocol.c.a(this.d).a()), this.g, AdSettings.isTestMode(this.a), AdSettings.isChildDirected(), hVar, o.a(com.facebook.ads.internal.l.a.q(this.a)));
            this.c.a(bVar);
        } catch (com.facebook.ads.internal.protocol.b e2) {
            a(com.facebook.ads.internal.protocol.a.a(e2));
        }
    }

    public void a(C0000a aVar) {
        this.l = aVar;
    }

    public void a(g gVar) {
        com.facebook.ads.internal.h.c a2 = gVar.a();
        if (a2 == null) {
            throw new IllegalStateException("no placement in response");
        }
        if (this.h) {
            long c2 = a2.a().c();
            if (c2 == 0) {
                c2 = 1800000;
            }
            this.i.postDelayed(this.j, c2);
        }
        this.m = a2;
        List<y> d2 = d();
        if (this.l == null) {
            return;
        }
        if (d2.isEmpty()) {
            this.l.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NO_FILL, ""));
        } else {
            this.l.a(d2);
        }
    }

    public void a(com.facebook.ads.internal.protocol.a aVar) {
        if (this.h) {
            this.i.postDelayed(this.j, 1800000);
        }
        if (this.l != null) {
            this.l.a(aVar);
        }
    }

    public void b() {
    }

    public void c() {
        this.h = false;
        this.i.removeCallbacks(this.j);
    }
}
