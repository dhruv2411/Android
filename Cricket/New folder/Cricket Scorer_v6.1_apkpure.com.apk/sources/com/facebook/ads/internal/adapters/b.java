package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.internal.q.a.d;
import com.facebook.ads.internal.r.a;
import java.util.HashMap;
import java.util.Map;

public abstract class b {
    protected final c a;
    protected final a b;
    private final Context c;
    private boolean d;

    public b(Context context, c cVar, a aVar) {
        this.c = context;
        this.a = cVar;
        this.b = aVar;
    }

    public final void a() {
        if (!this.d) {
            if (this.a != null) {
                this.a.a();
            }
            HashMap hashMap = new HashMap();
            if (this.b != null) {
                this.b.a((Map<String, String>) hashMap);
            }
            a(hashMap);
            this.d = true;
            d.a(this.c, "Impression logged");
            if (this.a != null) {
                this.a.b();
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(Map<String, String> map);
}
