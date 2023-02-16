package com.facebook.ads.internal.view.c.a;

import com.facebook.ads.internal.adapters.a.h;
import java.util.HashMap;
import java.util.Map;

public class b {
    private final int a;
    private final int b;
    private final h c;

    b(int i, int i2, h hVar) {
        this.a = i;
        this.b = i2;
        this.c = hVar;
    }

    public Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("cardind", this.a + "");
        hashMap.put("cardcnt", this.b + "");
        return hashMap;
    }

    public int b() {
        return this.a;
    }

    public h c() {
        return this.c;
    }
}
