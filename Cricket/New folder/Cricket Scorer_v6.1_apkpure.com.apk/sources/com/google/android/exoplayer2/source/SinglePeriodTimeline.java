package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.util.Assertions;

public final class SinglePeriodTimeline extends Timeline {
    private static final Object ID = new Object();
    private final boolean isDynamic;
    private final boolean isSeekable;
    private final long periodDurationUs;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;

    public int getPeriodCount() {
        return 1;
    }

    public int getWindowCount() {
        return 1;
    }

    public SinglePeriodTimeline(long j, boolean z) {
        this(j, j, 0, 0, z, false);
    }

    public SinglePeriodTimeline(long j, long j2, long j3, long j4, boolean z, boolean z2) {
        this.periodDurationUs = j;
        this.windowDurationUs = j2;
        this.windowPositionInPeriodUs = j3;
        this.windowDefaultStartPositionUs = j4;
        this.isSeekable = z;
        this.isDynamic = z2;
    }

    public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
        long j2;
        Assertions.checkIndex(i, 0, 1);
        Object obj = z ? ID : null;
        long j3 = this.windowDefaultStartPositionUs;
        if (this.isDynamic) {
            long j4 = j3 + j;
            if (j4 > this.windowDurationUs) {
                j3 = C.TIME_UNSET;
            } else {
                j2 = j4;
                return window.set(obj, C.TIME_UNSET, C.TIME_UNSET, this.isSeekable, this.isDynamic, j2, this.windowDurationUs, 0, 0, this.windowPositionInPeriodUs);
            }
        }
        j2 = j3;
        return window.set(obj, C.TIME_UNSET, C.TIME_UNSET, this.isSeekable, this.isDynamic, j2, this.windowDurationUs, 0, 0, this.windowPositionInPeriodUs);
    }

    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        Assertions.checkIndex(i, 0, 1);
        Object obj = z ? ID : null;
        return period.set(obj, obj, 0, this.periodDurationUs, -this.windowPositionInPeriodUs, false);
    }

    public int getIndexOfPeriod(Object obj) {
        return ID.equals(obj) ? 0 : -1;
    }
}
