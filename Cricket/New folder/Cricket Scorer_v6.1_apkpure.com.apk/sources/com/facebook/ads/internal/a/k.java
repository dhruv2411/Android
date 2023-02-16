package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.c.g;

public class k extends b {
    private static final String d = "k";
    private final Uri e;

    public k(Context context, c cVar, String str, Uri uri) {
        super(context, cVar, str);
        this.e = uri;
    }

    public a.C0004a a() {
        return a.C0004a.OPEN_LINK;
    }

    public void b() {
        try {
            Log.w("REDIRECTACTION: ", this.e.toString());
            g.a(new g(), this.a, this.e, this.c);
        } catch (Exception e2) {
            String str = d;
            Log.d(str, "Failed to open link url: " + this.e.toString(), e2);
        }
    }
}
