package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.e;
import java.util.Map;

public abstract class BannerAdapter implements AdAdapter {
    public final AdPlacementType getPlacementType() {
        return AdPlacementType.BANNER;
    }

    public abstract void loadBannerAd(Context context, c cVar, e eVar, BannerAdapterListener bannerAdapterListener, Map<String, Object> map);
}
