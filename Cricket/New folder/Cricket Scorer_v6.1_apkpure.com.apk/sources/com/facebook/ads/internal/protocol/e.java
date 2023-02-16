package com.facebook.ads.internal.protocol;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.itextpdf.text.pdf.codec.TIFFConstants;
import java.io.Serializable;

public enum e implements Serializable {
    BANNER_320_50(TIFFConstants.TIFFTAG_COLORMAP, 50),
    INTERSTITIAL(0, 0),
    BANNER_HEIGHT_50(-1, 50),
    BANNER_HEIGHT_90(-1, 90),
    RECTANGLE_HEIGHT_250(-1, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    
    private final int f;
    private final int g;

    private e(int i, int i2) {
        this.f = i;
        this.g = i2;
    }

    public static e a(int i, int i2) {
        if (INTERSTITIAL.g == i2 && INTERSTITIAL.f == i) {
            return INTERSTITIAL;
        }
        if (BANNER_320_50.g == i2 && BANNER_320_50.f == i) {
            return BANNER_320_50;
        }
        if (BANNER_HEIGHT_50.g == i2 && BANNER_HEIGHT_50.f == i) {
            return BANNER_HEIGHT_50;
        }
        if (BANNER_HEIGHT_90.g == i2 && BANNER_HEIGHT_90.f == i) {
            return BANNER_HEIGHT_90;
        }
        if (RECTANGLE_HEIGHT_250.g == i2 && RECTANGLE_HEIGHT_250.f == i) {
            return RECTANGLE_HEIGHT_250;
        }
        return null;
    }

    public int a() {
        return this.f;
    }

    public int b() {
        return this.g;
    }
}
