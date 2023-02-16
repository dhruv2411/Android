package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.os.Bundle;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.a.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.q.a.p;
import java.util.EnumSet;
import java.util.Map;

public abstract class s implements AdAdapter, p<Bundle> {
    public abstract void a(Context context, a aVar, Map<String, Object> map, c cVar, EnumSet<CacheFlag> enumSet);

    public abstract boolean e();

    public AdPlacementType getPlacementType() {
        return AdPlacementType.INSTREAM;
    }
}
