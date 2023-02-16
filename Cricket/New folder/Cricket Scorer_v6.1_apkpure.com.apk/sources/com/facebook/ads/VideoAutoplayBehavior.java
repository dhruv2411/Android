package com.facebook.ads;

import com.facebook.ads.internal.n.m;

public enum VideoAutoplayBehavior {
    DEFAULT,
    ON,
    OFF;

    public static VideoAutoplayBehavior fromInternalAutoplayBehavior(m mVar) {
        if (mVar == null) {
            return DEFAULT;
        }
        switch (mVar) {
            case DEFAULT:
                return DEFAULT;
            case ON:
                return ON;
            case OFF:
                return OFF;
            default:
                return DEFAULT;
        }
    }
}
