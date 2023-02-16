package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.q.a.v;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class e {
    private static final Set<g> a = new HashSet();
    private static final Map<AdPlacementType, String> b = new ConcurrentHashMap();

    static {
        for (g next : g.a()) {
            Class cls = null;
            switch (next.m) {
                case BANNER:
                    cls = BannerAdapter.class;
                    break;
                case INTERSTITIAL:
                    cls = InterstitialAdapter.class;
                    break;
                case NATIVE:
                case NATIVE_BANNER:
                    cls = y.class;
                    break;
                case INSTREAM:
                    cls = s.class;
                    break;
                case REWARDED_VIDEO:
                    cls = ab.class;
                    break;
            }
            if (cls != null) {
                Class<?> cls2 = next.j;
                if (cls2 == null) {
                    try {
                        cls2 = Class.forName(next.k);
                    } catch (ClassNotFoundException unused) {
                    }
                }
                if (cls2 != null && cls.isAssignableFrom(cls2)) {
                    a.add(next);
                }
            }
        }
    }

    public static AdAdapter a(f fVar, AdPlacementType adPlacementType) {
        AdAdapter adAdapter = null;
        try {
            g b2 = b(fVar, adPlacementType);
            if (b2 != null && a.contains(b2)) {
                Class<?> cls = b2.j;
                if (cls == null) {
                    cls = Class.forName(b2.k);
                }
                AdAdapter adAdapter2 = (AdAdapter) cls.newInstance();
                try {
                    if (!(adAdapter2 instanceof m)) {
                        return adAdapter2;
                    }
                    ((m) adAdapter2).a(adPlacementType);
                    return adAdapter2;
                } catch (Exception e) {
                    adAdapter = adAdapter2;
                    e = e;
                    e.printStackTrace();
                    return adAdapter;
                }
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return adAdapter;
        }
        return adAdapter;
    }

    public static AdAdapter a(String str, AdPlacementType adPlacementType) {
        return a(f.a(str), adPlacementType);
    }

    public static String a(AdPlacementType adPlacementType) {
        if (b.containsKey(adPlacementType)) {
            return b.get(adPlacementType);
        }
        HashSet hashSet = new HashSet();
        for (g next : a) {
            if (next.m == adPlacementType) {
                hashSet.add(next.l.toString());
            }
        }
        String a2 = v.a(hashSet, ",");
        b.put(adPlacementType, a2);
        return a2;
    }

    private static g b(f fVar, AdPlacementType adPlacementType) {
        for (g next : a) {
            if (next.l == fVar && next.m == adPlacementType) {
                return next;
            }
        }
        return null;
    }
}
