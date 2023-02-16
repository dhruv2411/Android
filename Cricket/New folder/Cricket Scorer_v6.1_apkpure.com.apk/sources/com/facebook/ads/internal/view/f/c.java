package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.internal.b.c;
import com.facebook.ads.internal.q.a.p;
import com.facebook.ads.internal.q.a.w;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c implements p<Bundle> {
    /* access modifiers changed from: private */
    public final String a;
    private boolean b;
    private final Context c;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.m.c d;
    private final a e;
    private final com.facebook.ads.internal.b.a f;
    private int g;
    private int h;
    private final e i;
    @Nullable
    private final Map<String, String> j;

    public interface a {
        boolean g();

        int getCurrentPositionInMillis();

        boolean getGlobalVisibleRect(Rect rect);

        long getInitialBufferTime();

        int getMeasuredHeight();

        int getMeasuredWidth();

        com.facebook.ads.internal.view.f.a.a getVideoStartReason();

        float getVolume();

        boolean h();
    }

    protected enum b {
        PLAY(0),
        SKIP(1),
        TIME(2),
        MRC(3),
        PAUSE(4),
        RESUME(5),
        MUTE(6),
        UNMUTE(7),
        VIEWABLE_IMPRESSION(10);
        
        public final int j;

        private b(int i) {
            this.j = i;
        }
    }

    public c(Context context, com.facebook.ads.internal.m.c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str) {
        this(context, cVar, aVar, list, str, (Bundle) null);
    }

    public c(Context context, com.facebook.ads.internal.m.c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str, @Nullable Bundle bundle) {
        this(context, cVar, aVar, list, str, bundle, (Map<String, String>) null);
    }

    public c(Context context, com.facebook.ads.internal.m.c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        a aVar2 = aVar;
        List<com.facebook.ads.internal.b.b> list2 = list;
        Bundle bundle2 = bundle;
        this.b = true;
        this.g = 0;
        this.h = 0;
        this.c = context;
        this.d = cVar;
        this.e = aVar2;
        this.a = str;
        this.j = map;
        list2.add(new com.facebook.ads.internal.b.b(this, 0.5d, -1.0d, 2.0d, true) {
            final /* synthetic */ c a;

            {
                this.a = r10;
            }

            /* access modifiers changed from: protected */
            public void a(boolean z, boolean z2, com.facebook.ads.internal.b.c cVar) {
                if (z2) {
                    this.a.d.e(this.a.a, this.a.a(b.MRC));
                }
            }
        });
        list2.add(new com.facebook.ads.internal.b.b(this, 1.0E-7d, -1.0d, 0.001d, false) {
            final /* synthetic */ c a;

            {
                this.a = r10;
            }

            /* access modifiers changed from: protected */
            public void a(boolean z, boolean z2, com.facebook.ads.internal.b.c cVar) {
                if (z2) {
                    this.a.d.e(this.a.a, this.a.a(b.VIEWABLE_IMPRESSION));
                }
            }
        });
        if (bundle2 != null) {
            this.f = new com.facebook.ads.internal.b.a((View) aVar2, list2, bundle2.getBundle("adQualityManager"));
            this.g = bundle2.getInt("lastProgressTimeMS");
            this.h = bundle2.getInt("lastBoundaryTimeMS");
        } else {
            this.f = new com.facebook.ads.internal.b.a((View) aVar2, list2);
        }
        this.i = new e(new Handler(), this);
    }

    /* access modifiers changed from: private */
    public Map<String, String> a(b bVar) {
        return a(bVar, this.e.getCurrentPositionInMillis());
    }

    private Map<String, String> a(b bVar, int i2) {
        Map<String, String> c2 = c(i2);
        c2.put("action", String.valueOf(bVar.j));
        return c2;
    }

    private void a() {
        this.d.e(this.a, a(b.MUTE));
    }

    private void a(int i2, boolean z) {
        if (((double) i2) > 0.0d && i2 >= this.g) {
            if (i2 > this.g) {
                this.f.a((double) (((float) (i2 - this.g)) / 1000.0f), (double) d());
                this.g = i2;
                if (i2 - this.h >= 5000) {
                    this.d.e(this.a, a(b.TIME, i2));
                    this.h = this.g;
                    this.f.a();
                    return;
                }
            }
            if (z) {
                this.d.e(this.a, a(b.TIME, i2));
            }
        }
    }

    private void a(HashMap<String, String> hashMap) {
        if (this.j != null) {
            hashMap.putAll(this.j);
        }
    }

    private void a(Map<String, String> map) {
        map.put("exoplayer", String.valueOf(this.e.g()));
        map.put("prep", Long.toString(this.e.getInitialBufferTime()));
    }

    private void a(Map<String, String> map, int i2) {
        map.put("ptime", String.valueOf(((float) this.h) / 1000.0f));
        map.put("time", String.valueOf(((float) i2) / 1000.0f));
    }

    private void b(Map<String, String> map) {
        com.facebook.ads.internal.b.c c2 = this.f.c();
        c.a c3 = c2.c();
        map.put("vwa", String.valueOf(c3.d()));
        map.put("vwm", String.valueOf(c3.c()));
        map.put("vwmax", String.valueOf(c3.e()));
        map.put("vtime_ms", String.valueOf(c3.g() * 1000.0d));
        map.put("mcvt_ms", String.valueOf(c3.h() * 1000.0d));
        c.a d2 = c2.d();
        map.put("vla", String.valueOf(d2.d()));
        map.put("vlm", String.valueOf(d2.c()));
        map.put("vlmax", String.valueOf(d2.e()));
        map.put("atime_ms", String.valueOf(d2.g() * 1000.0d));
        map.put("mcat_ms", String.valueOf(d2.h() * 1000.0d));
    }

    private Map<String, String> c(int i2) {
        HashMap hashMap = new HashMap();
        w.a(hashMap, this.e.getVideoStartReason() == com.facebook.ads.internal.view.f.a.a.AUTO_STARTED, !this.e.h());
        a((Map<String, String>) hashMap);
        b((Map<String, String>) hashMap);
        a((Map<String, String>) hashMap, i2);
        c((Map<String, String>) hashMap);
        a((HashMap<String, String>) hashMap);
        return hashMap;
    }

    private void c(Map<String, String> map) {
        Rect rect = new Rect();
        this.e.getGlobalVisibleRect(rect);
        map.put("pt", String.valueOf(rect.top));
        map.put("pl", String.valueOf(rect.left));
        map.put("ph", String.valueOf(this.e.getMeasuredHeight()));
        map.put("pw", String.valueOf(this.e.getMeasuredWidth()));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        map.put("vph", String.valueOf(displayMetrics.heightPixels));
        map.put("vpw", String.valueOf(displayMetrics.widthPixels));
    }

    private void k() {
        this.d.e(this.a, a(b.UNMUTE));
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        a(i2, false);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3) {
        a(i2, true);
        this.h = i3;
        this.g = i3;
        this.f.a();
        this.f.b();
    }

    public void b() {
        this.c.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.i);
    }

    public void b(int i2) {
        a(i2, true);
        this.h = 0;
        this.g = 0;
        this.f.a();
        this.f.b();
    }

    public void c() {
        this.c.getContentResolver().unregisterContentObserver(this.i);
    }

    /* access modifiers changed from: protected */
    public float d() {
        return w.a(this.c) * this.e.getVolume();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        boolean z;
        if (((double) d()) < 0.05d) {
            if (this.b) {
                a();
                z = false;
            } else {
                return;
            }
        } else if (!this.b) {
            k();
            z = true;
        } else {
            return;
        }
        this.b = z;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.d.e(this.a, a(b.SKIP));
    }

    public Bundle g() {
        a(j(), j());
        Bundle bundle = new Bundle();
        bundle.putInt("lastProgressTimeMS", this.g);
        bundle.putInt("lastBoundaryTimeMS", this.h);
        bundle.putBundle("adQualityManager", this.f.g());
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        this.d.e(this.a, a(b.PAUSE));
    }

    /* access modifiers changed from: package-private */
    public void i() {
        this.d.e(this.a, a(b.RESUME));
    }

    public int j() {
        return this.g;
    }
}
