package com.facebook.ads;

import com.facebook.ads.internal.protocol.e;
import java.io.Serializable;

public class AdSize implements Serializable {
    @Deprecated
    public static final AdSize BANNER_320_50 = new AdSize(e.BANNER_320_50);
    public static final AdSize BANNER_HEIGHT_50 = new AdSize(e.BANNER_HEIGHT_50);
    public static final AdSize BANNER_HEIGHT_90 = new AdSize(e.BANNER_HEIGHT_90);
    public static final AdSize INTERSTITIAL = new AdSize(e.INTERSTITIAL);
    public static final AdSize RECTANGLE_HEIGHT_250 = new AdSize(e.RECTANGLE_HEIGHT_250);
    private final int a;
    private final int b;

    public AdSize(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    private AdSize(e eVar) {
        this.a = eVar.a();
        this.b = eVar.b();
    }

    public static AdSize fromWidthAndHeight(int i, int i2) {
        if (INTERSTITIAL.b == i2 && INTERSTITIAL.a == i) {
            return INTERSTITIAL;
        }
        if (BANNER_320_50.b == i2 && BANNER_320_50.a == i) {
            return BANNER_320_50;
        }
        if (BANNER_HEIGHT_50.b == i2 && BANNER_HEIGHT_50.a == i) {
            return BANNER_HEIGHT_50;
        }
        if (BANNER_HEIGHT_90.b == i2 && BANNER_HEIGHT_90.a == i) {
            return BANNER_HEIGHT_90;
        }
        if (RECTANGLE_HEIGHT_250.b == i2 && RECTANGLE_HEIGHT_250.a == i) {
            return RECTANGLE_HEIGHT_250;
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        if (this.a != adSize.a) {
            return false;
        }
        return this.b == adSize.b;
    }

    public int getHeight() {
        return this.b;
    }

    public int getWidth() {
        return this.a;
    }

    public int hashCode() {
        return (31 * this.a) + this.b;
    }

    public e toInternalAdSize() {
        return e.a(this.a, this.b);
    }
}
