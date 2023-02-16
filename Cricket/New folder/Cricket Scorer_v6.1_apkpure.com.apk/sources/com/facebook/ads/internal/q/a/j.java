package com.facebook.ads.internal.q.a;

import android.os.Build;
import android.view.View;

public enum j {
    INTERNAL_NO_TAG(0),
    INTERNAL_NO_CLICK(1),
    INTERNAL_API_TOO_LOW(2),
    INTERNAL_WRONG_TAG_CLASS(3),
    INTERNAL_NULL_VIEW(4),
    INTERNAL_AD_ICON(5),
    INTERNAL_AD_TITLE(6),
    INTERNAL_AD_COVER_IMAGE(7),
    INTERNAL_AD_SUBTITLE(8),
    INTERNAL_AD_BODY(9),
    INTERNAL_AD_CALL_TO_ACTION(10),
    INTERNAL_AD_SOCIAL_CONTEXT(11),
    INTERNAL_AD_CHOICES_ICON(12),
    INTERNAL_AD_MEDIA(13);
    
    public static int o = -1593835521;
    private final int p;

    private j(int i) {
        this.p = i;
    }

    public static void a(View view, j jVar) {
        if (view != null && jVar != null && Build.VERSION.SDK_INT > 4) {
            view.setTag(o, jVar);
        }
    }

    public int a() {
        return this.p;
    }
}
