package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Collections;
import java.util.List;

public class AdaptationSet {
    public static final int ID_UNSET = -1;
    public final List<SchemeValuePair> accessibilityDescriptors;
    public final int id;
    public final List<Representation> representations;
    public final int type;

    public AdaptationSet(int i, int i2, List<Representation> list, List<SchemeValuePair> list2) {
        List<SchemeValuePair> list3;
        this.id = i;
        this.type = i2;
        this.representations = Collections.unmodifiableList(list);
        if (list2 == null) {
            list3 = Collections.emptyList();
        } else {
            list3 = Collections.unmodifiableList(list2);
        }
        this.accessibilityDescriptors = list3;
    }
}
