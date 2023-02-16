package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.r.a;
import java.util.Map;

public class aa extends b {
    private final c c;
    private final u d;
    private k e;

    public aa(Context context, c cVar, a aVar, u uVar, c cVar2) {
        super(context, cVar2, aVar);
        this.c = cVar;
        this.d = uVar;
    }

    public void a(k kVar) {
        this.e = kVar;
    }

    /* access modifiers changed from: protected */
    public void a(Map<String, String> map) {
        if (this.e != null && !TextUtils.isEmpty(this.e.g())) {
            map.put("touch", com.facebook.ads.internal.q.a.k.a(this.d.e()));
            this.c.a(this.e.g(), map);
        }
    }
}
