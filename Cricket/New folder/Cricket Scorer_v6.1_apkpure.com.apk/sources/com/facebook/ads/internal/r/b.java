package com.facebook.ads.internal.r;

import java.util.HashMap;
import java.util.Map;

public class b {
    private c a;
    private float b;
    private Map<String, String> c;

    public b(c cVar) {
        this(cVar, 0.0f);
    }

    public b(c cVar, float f) {
        this(cVar, f, (Map<String, String>) null);
    }

    public b(c cVar, float f, Map<String, String> map) {
        this.a = cVar;
        this.b = f;
        if (map != null) {
            this.c = map;
        } else {
            this.c = new HashMap();
        }
    }

    public boolean a() {
        return this.a == c.IS_VIEWABLE;
    }

    public int b() {
        return this.a.a();
    }

    public float c() {
        return this.b;
    }

    public Map<String, String> d() {
        return this.c;
    }
}
