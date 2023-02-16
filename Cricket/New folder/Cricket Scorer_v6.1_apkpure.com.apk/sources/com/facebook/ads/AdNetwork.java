package com.facebook.ads;

import com.facebook.ads.internal.n.c;

public enum AdNetwork {
    AN(c.AN),
    ADMOB(c.ADMOB),
    FLURRY(c.FLURRY),
    INMOBI(c.INMOBI);
    
    private final c a;

    private AdNetwork(c cVar) {
        this.a = cVar;
    }

    public static AdNetwork fromInternalAdNetwork(c cVar) {
        if (cVar == null) {
            return AN;
        }
        switch (cVar) {
            case AN:
                return AN;
            case ADMOB:
                return ADMOB;
            case FLURRY:
                return FLURRY;
            case INMOBI:
                return INMOBI;
            default:
                return AN;
        }
    }
}
