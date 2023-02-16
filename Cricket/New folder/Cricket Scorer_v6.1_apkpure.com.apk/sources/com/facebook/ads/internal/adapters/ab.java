package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.Map;

public abstract class ab implements AdAdapter {
    RewardData a;
    int b;

    public abstract int a();

    public void a(int i) {
        this.b = i;
    }

    public abstract void a(Context context, ac acVar, Map<String, Object> map, boolean z);

    public void a(RewardData rewardData) {
        this.a = rewardData;
    }

    public abstract boolean b();

    public AdPlacementType getPlacementType() {
        return AdPlacementType.REWARDED_VIDEO;
    }
}
