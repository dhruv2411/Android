package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.ArrayList;
import java.util.List;

public enum g {
    ANBANNER(i.class, f.AN, AdPlacementType.BANNER),
    ANINTERSTITIAL(k.class, f.AN, AdPlacementType.INTERSTITIAL),
    ADMOBNATIVE(d.class, f.ADMOB, AdPlacementType.NATIVE),
    ANNATIVE(m.class, f.AN, AdPlacementType.NATIVE),
    ANNATIVEBANNER(m.class, f.AN, AdPlacementType.NATIVE_BANNER),
    ANINSTREAMVIDEO(j.class, f.AN, AdPlacementType.INSTREAM),
    ANREWARDEDVIDEO(n.class, f.AN, AdPlacementType.REWARDED_VIDEO),
    INMOBINATIVE(r.class, f.INMOBI, AdPlacementType.NATIVE),
    YAHOONATIVE(o.class, f.YAHOO, AdPlacementType.NATIVE);
    
    private static List<g> n;
    public Class<?> j;
    public String k;
    public f l;
    public AdPlacementType m;

    private g(Class<?> cls, f fVar, AdPlacementType adPlacementType) {
        this.j = cls;
        this.l = fVar;
        this.m = adPlacementType;
    }

    public static List<g> a() {
        if (n == null) {
            synchronized (g.class) {
                n = new ArrayList();
                n.add(ANBANNER);
                n.add(ANINTERSTITIAL);
                n.add(ANNATIVE);
                n.add(ANNATIVEBANNER);
                n.add(ANINSTREAMVIDEO);
                n.add(ANREWARDEDVIDEO);
                if (w.a(f.YAHOO)) {
                    n.add(YAHOONATIVE);
                }
                if (w.a(f.INMOBI)) {
                    n.add(INMOBINATIVE);
                }
                if (w.a(f.ADMOB)) {
                    n.add(ADMOBNATIVE);
                }
            }
        }
        return n;
    }
}
