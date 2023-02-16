package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.b.n;
import com.facebook.ads.internal.view.f.b.p;
import com.facebook.ads.internal.view.f.b.r;
import com.facebook.ads.internal.view.f.b.s;
import com.facebook.ads.internal.view.f.b.v;
import com.facebook.ads.internal.view.f.b.w;
import com.facebook.ads.internal.view.f.b.x;
import com.facebook.ads.internal.view.f.b.y;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class b extends c {
    public int a;
    /* access modifiers changed from: private */
    public final w b;
    /* access modifiers changed from: private */
    public final f<r> c;
    /* access modifiers changed from: private */
    public final f<h> d;
    /* access modifiers changed from: private */
    public final f<j> e;
    /* access modifiers changed from: private */
    public final f<n> f;
    /* access modifiers changed from: private */
    public final f<com.facebook.ads.internal.view.f.b.b> g;
    /* access modifiers changed from: private */
    public final f<p> h;
    /* access modifiers changed from: private */
    public final f<x> i;
    /* access modifiers changed from: private */
    public final f<y> j;
    /* access modifiers changed from: private */
    public final f<s> k;
    /* access modifiers changed from: private */
    public final m l;
    /* access modifiers changed from: private */
    public final a m;
    /* access modifiers changed from: private */
    public boolean n;

    public b(Context context, c cVar, a aVar, String str) {
        this(context, cVar, aVar, (List<com.facebook.ads.internal.b.b>) new ArrayList(), str);
    }

    public b(Context context, c cVar, a aVar, String str, @Nullable Bundle bundle) {
        this(context, cVar, aVar, new ArrayList(), str, bundle, (Map<String, String>) null);
    }

    public b(Context context, c cVar, a aVar, String str, @Nullable Map<String, String> map) {
        this(context, cVar, aVar, new ArrayList(), str, (Bundle) null, map);
    }

    public b(Context context, c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str) {
        super(context, cVar, aVar, list, str);
        this.b = new w() {
            static final /* synthetic */ boolean a = true;

            static {
                Class<b> cls = b.class;
            }

            public void a(v vVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    b.this.e();
                }
            }
        };
        this.c = new f<r>() {
            static final /* synthetic */ boolean a = true;

            static {
                Class<b> cls = b.class;
            }

            public Class<r> a() {
                return r.class;
            }

            public void a(r rVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    b.this.f();
                }
            }
        };
        this.d = new f<h>() {
            static final /* synthetic */ boolean a = true;

            static {
                Class<b> cls = b.class;
            }

            public Class<h> a() {
                return h.class;
            }

            public void a(h hVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    b.this.h();
                }
            }
        };
        this.e = new f<j>() {
            static final /* synthetic */ boolean a = true;

            static {
                Class<b> cls = b.class;
            }

            public Class<j> a() {
                return j.class;
            }

            public void a(j jVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    if (!b.this.n) {
                        boolean unused = b.this.n = true;
                    } else {
                        b.this.i();
                    }
                }
            }
        };
        this.f = new f<n>() {
            public Class<n> a() {
                return n.class;
            }

            public void a(n nVar) {
                int a2 = nVar.a();
                if (b.this.a <= 0 || a2 != b.this.m.getDuration() || b.this.m.getDuration() <= b.this.a) {
                    b.this.a(a2);
                }
            }
        };
        this.g = new f<com.facebook.ads.internal.view.f.b.b>() {
            public Class<com.facebook.ads.internal.view.f.b.b> a() {
                return com.facebook.ads.internal.view.f.b.b.class;
            }

            public void a(com.facebook.ads.internal.view.f.b.b bVar) {
                b bVar2;
                int a2 = bVar.a();
                int b = bVar.b();
                if (b.this.a <= 0 || a2 != b || b <= b.this.a) {
                    if (b >= a2 + 500) {
                        bVar2 = b.this;
                    } else if (b == 0) {
                        bVar2 = b.this;
                        a2 = b.this.a;
                    } else {
                        b.this.b(b);
                        return;
                    }
                    bVar2.b(a2);
                }
            }
        };
        this.h = new f<p>() {
            public Class<p> a() {
                return p.class;
            }

            public void a(p pVar) {
                b.this.a(pVar.a(), pVar.b());
            }
        };
        this.i = new f<x>() {
            public Class<x> a() {
                return x.class;
            }

            public void a(x xVar) {
                b.this.b();
            }
        };
        this.j = new f<y>() {
            public Class<y> a() {
                return y.class;
            }

            public void a(y yVar) {
                b.this.c();
            }
        };
        this.k = new f<s>() {
            public Class<s> a() {
                return s.class;
            }

            public void a(s sVar) {
                b.this.a(b.this.j(), b.this.j());
            }
        };
        this.l = new m() {
            public void a(l lVar) {
                b.this.a = b.this.m.getDuration();
            }
        };
        this.n = false;
        this.m = aVar;
        this.m.getEventBus().a((T[]) new f[]{this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.l, this.k});
    }

    public b(Context context, c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        super(context, cVar, aVar, list, str, bundle, map);
        this.b = new w() {
            static final /* synthetic */ boolean a = true;

            static {
                Class<b> cls = b.class;
            }

            public void a(v vVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    b.this.e();
                }
            }
        };
        this.c = new f<r>() {
            static final /* synthetic */ boolean a = true;

            static {
                Class<b> cls = b.class;
            }

            public Class<r> a() {
                return r.class;
            }

            public void a(r rVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    b.this.f();
                }
            }
        };
        this.d = new f<h>() {
            static final /* synthetic */ boolean a = true;

            static {
                Class<b> cls = b.class;
            }

            public Class<h> a() {
                return h.class;
            }

            public void a(h hVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    b.this.h();
                }
            }
        };
        this.e = new f<j>() {
            static final /* synthetic */ boolean a = true;

            static {
                Class<b> cls = b.class;
            }

            public Class<j> a() {
                return j.class;
            }

            public void a(j jVar) {
                if (!a && b.this == null) {
                    throw new AssertionError();
                } else if (b.this != null) {
                    if (!b.this.n) {
                        boolean unused = b.this.n = true;
                    } else {
                        b.this.i();
                    }
                }
            }
        };
        this.f = new f<n>() {
            public Class<n> a() {
                return n.class;
            }

            public void a(n nVar) {
                int a2 = nVar.a();
                if (b.this.a <= 0 || a2 != b.this.m.getDuration() || b.this.m.getDuration() <= b.this.a) {
                    b.this.a(a2);
                }
            }
        };
        this.g = new f<com.facebook.ads.internal.view.f.b.b>() {
            public Class<com.facebook.ads.internal.view.f.b.b> a() {
                return com.facebook.ads.internal.view.f.b.b.class;
            }

            public void a(com.facebook.ads.internal.view.f.b.b bVar) {
                b bVar2;
                int a2 = bVar.a();
                int b = bVar.b();
                if (b.this.a <= 0 || a2 != b || b <= b.this.a) {
                    if (b >= a2 + 500) {
                        bVar2 = b.this;
                    } else if (b == 0) {
                        bVar2 = b.this;
                        a2 = b.this.a;
                    } else {
                        b.this.b(b);
                        return;
                    }
                    bVar2.b(a2);
                }
            }
        };
        this.h = new f<p>() {
            public Class<p> a() {
                return p.class;
            }

            public void a(p pVar) {
                b.this.a(pVar.a(), pVar.b());
            }
        };
        this.i = new f<x>() {
            public Class<x> a() {
                return x.class;
            }

            public void a(x xVar) {
                b.this.b();
            }
        };
        this.j = new f<y>() {
            public Class<y> a() {
                return y.class;
            }

            public void a(y yVar) {
                b.this.c();
            }
        };
        this.k = new f<s>() {
            public Class<s> a() {
                return s.class;
            }

            public void a(s sVar) {
                b.this.a(b.this.j(), b.this.j());
            }
        };
        this.l = new m() {
            public void a(l lVar) {
                b.this.a = b.this.m.getDuration();
            }
        };
        this.n = false;
        this.m = aVar;
        this.m.getEventBus().a((T[]) new f[]{this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.k});
    }

    public void a() {
        this.m.getStateHandler().post(new Runnable() {
            public void run() {
                b.this.m.getEventBus().b((T[]) new f[]{b.this.b, b.this.f, b.this.c, b.this.e, b.this.d, b.this.g, b.this.h, b.this.i, b.this.j, b.this.l, b.this.k});
            }
        });
    }
}
