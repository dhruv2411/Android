package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.dash.DashSegmentIndex;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase;
import java.util.Collections;
import java.util.List;

public abstract class Representation {
    public static final long REVISION_ID_DEFAULT = -1;
    public final String baseUrl;
    public final String contentId;
    public final Format format;
    public final List<SchemeValuePair> inbandEventStreams;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;
    public final long revisionId;

    public abstract String getCacheKey();

    public abstract DashSegmentIndex getIndex();

    public abstract RangedUri getIndexUri();

    public static Representation newInstance(String str, long j, Format format2, String str2, SegmentBase segmentBase) {
        return newInstance(str, j, format2, str2, segmentBase, (List<SchemeValuePair>) null);
    }

    public static Representation newInstance(String str, long j, Format format2, String str2, SegmentBase segmentBase, List<SchemeValuePair> list) {
        return newInstance(str, j, format2, str2, segmentBase, list, (String) null);
    }

    public static Representation newInstance(String str, long j, Format format2, String str2, SegmentBase segmentBase, List<SchemeValuePair> list, String str3) {
        SegmentBase segmentBase2 = segmentBase;
        if (segmentBase2 instanceof SegmentBase.SingleSegmentBase) {
            return new SingleSegmentRepresentation(str, j, format2, str2, (SegmentBase.SingleSegmentBase) segmentBase2, list, str3, -1);
        } else if (segmentBase2 instanceof SegmentBase.MultiSegmentBase) {
            return new MultiSegmentRepresentation(str, j, format2, str2, (SegmentBase.MultiSegmentBase) segmentBase2, list);
        } else {
            throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
        }
    }

    private Representation(String str, long j, Format format2, String str2, SegmentBase segmentBase, List<SchemeValuePair> list) {
        List<SchemeValuePair> list2;
        this.contentId = str;
        this.revisionId = j;
        this.format = format2;
        this.baseUrl = str2;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.inbandEventStreams = list2;
        this.initializationUri = segmentBase.getInitialization(this);
        this.presentationTimeOffsetUs = segmentBase.getPresentationTimeOffsetUs();
    }

    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }

    public static class SingleSegmentRepresentation extends Representation {
        private final String cacheKey;
        public final long contentLength;
        private final RangedUri indexUri;
        private final SingleSegmentIndex segmentIndex;
        public final Uri uri;

        public static SingleSegmentRepresentation newInstance(String str, long j, Format format, String str2, long j2, long j3, long j4, long j5, List<SchemeValuePair> list, String str3, long j6) {
            return new SingleSegmentRepresentation(str, j, format, str2, new SegmentBase.SingleSegmentBase(new RangedUri((String) null, j2, (j3 - j2) + 1), 1, 0, j4, (j5 - j4) + 1), list, str3, j6);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SingleSegmentRepresentation(java.lang.String r12, long r13, com.google.android.exoplayer2.Format r15, java.lang.String r16, com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase r17, java.util.List<com.google.android.exoplayer2.source.dash.manifest.SchemeValuePair> r18, java.lang.String r19, long r20) {
            /*
                r11 = this;
                r9 = r11
                r10 = r12
                r8 = 0
                r0 = r9
                r1 = r10
                r2 = r13
                r4 = r15
                r5 = r16
                r6 = r17
                r7 = r18
                r0.<init>(r1, r2, r4, r5, r6, r7)
                android.net.Uri r0 = android.net.Uri.parse(r16)
                r9.uri = r0
                com.google.android.exoplayer2.source.dash.manifest.RangedUri r0 = r17.getIndex()
                r9.indexUri = r0
                r0 = 0
                if (r19 == 0) goto L_0x0022
                r1 = r19
                goto L_0x0046
            L_0x0022:
                if (r10 == 0) goto L_0x0045
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r10)
                java.lang.String r2 = "."
                r1.append(r2)
                r2 = r15
                java.lang.String r2 = r2.id
                r1.append(r2)
                java.lang.String r2 = "."
                r1.append(r2)
                r2 = r13
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                goto L_0x0046
            L_0x0045:
                r1 = r0
            L_0x0046:
                r9.cacheKey = r1
                r6 = r20
                r9.contentLength = r6
                com.google.android.exoplayer2.source.dash.manifest.RangedUri r1 = r9.indexUri
                if (r1 == 0) goto L_0x0051
                goto L_0x005f
            L_0x0051:
                com.google.android.exoplayer2.source.dash.manifest.SingleSegmentIndex r0 = new com.google.android.exoplayer2.source.dash.manifest.SingleSegmentIndex
                com.google.android.exoplayer2.source.dash.manifest.RangedUri r1 = new com.google.android.exoplayer2.source.dash.manifest.RangedUri
                r3 = 0
                r4 = 0
                r2 = r1
                r2.<init>(r3, r4, r6)
                r0.<init>(r1)
            L_0x005f:
                r9.segmentIndex = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.Representation.SingleSegmentRepresentation.<init>(java.lang.String, long, com.google.android.exoplayer2.Format, java.lang.String, com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase, java.util.List, java.lang.String, long):void");
        }

        public RangedUri getIndexUri() {
            return this.indexUri;
        }

        public DashSegmentIndex getIndex() {
            return this.segmentIndex;
        }

        public String getCacheKey() {
            return this.cacheKey;
        }
    }

    public static class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        private final SegmentBase.MultiSegmentBase segmentBase;

        public String getCacheKey() {
            return null;
        }

        public DashSegmentIndex getIndex() {
            return this;
        }

        public RangedUri getIndexUri() {
            return null;
        }

        public MultiSegmentRepresentation(String str, long j, Format format, String str2, SegmentBase.MultiSegmentBase multiSegmentBase, List<SchemeValuePair> list) {
            super(str, j, format, str2, multiSegmentBase, list);
            this.segmentBase = multiSegmentBase;
        }

        public RangedUri getSegmentUrl(int i) {
            return this.segmentBase.getSegmentUrl(this, i);
        }

        public int getSegmentNum(long j, long j2) {
            return this.segmentBase.getSegmentNum(j, j2);
        }

        public long getTimeUs(int i) {
            return this.segmentBase.getSegmentTimeUs(i);
        }

        public long getDurationUs(int i, long j) {
            return this.segmentBase.getSegmentDurationUs(i, j);
        }

        public int getFirstSegmentNum() {
            return this.segmentBase.getFirstSegmentNum();
        }

        public int getSegmentCount(long j) {
            return this.segmentBase.getSegmentCount(j);
        }

        public boolean isExplicit() {
            return this.segmentBase.isExplicit();
        }
    }
}
