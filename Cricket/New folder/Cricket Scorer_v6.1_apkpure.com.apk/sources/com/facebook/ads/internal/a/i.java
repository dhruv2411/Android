package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.c.g;
import java.util.Map;

class i extends h {
    private static final String e = "i";
    private final Uri f;
    private final Map<String, String> g;

    i(Context context, c cVar, String str, Uri uri, Map<String, String> map, l lVar) {
        super(context, cVar, str, lVar);
        this.f = uri;
        this.g = map;
    }

    public a.C0004a a() {
        return a.C0004a.OPEN_LINK;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        a aVar;
        try {
            g.a(new g(), this.a, Uri.parse(this.f.getQueryParameter("link")), this.c);
            aVar = null;
        } catch (Exception e2) {
            String str = e;
            Log.d(str, "Failed to open link url: " + this.f.toString(), e2);
            aVar = a.CANNOT_OPEN;
        }
        a(this.g, aVar);
    }
}
