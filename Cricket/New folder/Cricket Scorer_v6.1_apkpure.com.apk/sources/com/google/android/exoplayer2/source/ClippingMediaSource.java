package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;

public final class ClippingMediaSource implements MediaSource, MediaSource.Listener {
    private ClippingTimeline clippingTimeline;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private MediaSource.Listener sourceListener;
    private final long startUs;

    public ClippingMediaSource(MediaSource mediaSource2, long j, long j2) {
        Assertions.checkArgument(j >= 0);
        this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource2);
        this.startUs = j;
        this.endUs = j2;
        this.mediaPeriods = new ArrayList<>();
    }

    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        this.sourceListener = listener;
        this.mediaSource.prepareSource(exoPlayer, false, this);
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.mediaSource.maybeThrowSourceInfoRefreshError();
    }

    public MediaPeriod createPeriod(int i, Allocator allocator, long j) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(i, allocator, this.startUs + j));
        this.mediaPeriods.add(clippingMediaPeriod);
        clippingMediaPeriod.setClipping(this.clippingTimeline.startUs, this.clippingTimeline.endUs);
        return clippingMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
    }

    public void releaseSource() {
        this.mediaSource.releaseSource();
    }

    public void onSourceInfoRefreshed(Timeline timeline, Object obj) {
        long j;
        this.clippingTimeline = new ClippingTimeline(timeline, this.startUs, this.endUs);
        this.sourceListener.onSourceInfoRefreshed(this.clippingTimeline, obj);
        long access$000 = this.clippingTimeline.startUs;
        if (this.clippingTimeline.endUs == C.TIME_UNSET) {
            j = Long.MIN_VALUE;
        } else {
            j = this.clippingTimeline.endUs;
        }
        int size = this.mediaPeriods.size();
        for (int i = 0; i < size; i++) {
            this.mediaPeriods.get(i).setClipping(access$000, j);
        }
    }

    private static final class ClippingTimeline extends Timeline {
        /* access modifiers changed from: private */
        public final long endUs;
        /* access modifiers changed from: private */
        public final long startUs;
        private final Timeline timeline;

        public int getPeriodCount() {
            return 1;
        }

        public int getWindowCount() {
            return 1;
        }

        public ClippingTimeline(Timeline timeline2, long j, long j2) {
            boolean z = false;
            Assertions.checkArgument(timeline2.getWindowCount() == 1);
            Assertions.checkArgument(timeline2.getPeriodCount() == 1);
            Timeline.Window window = timeline2.getWindow(0, new Timeline.Window(), false);
            Assertions.checkArgument(!window.isDynamic);
            j2 = j2 == Long.MIN_VALUE ? window.durationUs : j2;
            if (window.durationUs != C.TIME_UNSET) {
                Assertions.checkArgument(j == 0 || window.isSeekable);
                Assertions.checkArgument(j2 <= window.durationUs);
                Assertions.checkArgument(j <= j2);
            }
            Assertions.checkArgument(timeline2.getPeriod(0, new Timeline.Period()).getPositionInWindowUs() == 0 ? true : z);
            this.timeline = timeline2;
            this.startUs = j;
            this.endUs = j2;
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            long j2;
            Timeline.Window window2 = this.timeline.getWindow(0, window, z, j);
            window2.durationUs = this.endUs != C.TIME_UNSET ? this.endUs - this.startUs : -9223372036854775807L;
            if (window2.defaultPositionUs != C.TIME_UNSET) {
                window2.defaultPositionUs = Math.max(window2.defaultPositionUs, this.startUs);
                if (this.endUs == C.TIME_UNSET) {
                    j2 = window2.defaultPositionUs;
                } else {
                    j2 = Math.min(window2.defaultPositionUs, this.endUs);
                }
                window2.defaultPositionUs = j2;
                window2.defaultPositionUs -= this.startUs;
            }
            long usToMs = C.usToMs(this.startUs);
            if (window2.presentationStartTimeMs != C.TIME_UNSET) {
                window2.presentationStartTimeMs += usToMs;
            }
            if (window2.windowStartTimeMs != C.TIME_UNSET) {
                window2.windowStartTimeMs += usToMs;
            }
            return window2;
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            Timeline.Period period2 = this.timeline.getPeriod(0, period, z);
            long j = this.endUs;
            long j2 = C.TIME_UNSET;
            if (j != C.TIME_UNSET) {
                j2 = this.endUs - this.startUs;
            }
            period2.durationUs = j2;
            return period2;
        }

        public int getIndexOfPeriod(Object obj) {
            return this.timeline.getIndexOfPeriod(obj);
        }
    }
}
