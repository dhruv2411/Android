package com.google.android.exoplayer2.source;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

public final class LoopingMediaSource implements MediaSource {
    public static final int MAX_EXPOSED_PERIODS = 157680000;
    private static final String TAG = "LoopingMediaSource";
    /* access modifiers changed from: private */
    public int childPeriodCount;
    private final MediaSource childSource;
    /* access modifiers changed from: private */
    public final int loopCount;

    public LoopingMediaSource(MediaSource mediaSource) {
        this(mediaSource, Integer.MAX_VALUE);
    }

    public LoopingMediaSource(MediaSource mediaSource, int i) {
        Assertions.checkArgument(i > 0);
        this.childSource = mediaSource;
        this.loopCount = i;
    }

    public void prepareSource(ExoPlayer exoPlayer, boolean z, final MediaSource.Listener listener) {
        this.childSource.prepareSource(exoPlayer, false, new MediaSource.Listener() {
            public void onSourceInfoRefreshed(Timeline timeline, Object obj) {
                int unused = LoopingMediaSource.this.childPeriodCount = timeline.getPeriodCount();
                listener.onSourceInfoRefreshed(new LoopingTimeline(timeline, LoopingMediaSource.this.loopCount), obj);
            }
        });
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.childSource.maybeThrowSourceInfoRefreshError();
    }

    public MediaPeriod createPeriod(int i, Allocator allocator, long j) {
        return this.childSource.createPeriod(i % this.childPeriodCount, allocator, j);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.childSource.releasePeriod(mediaPeriod);
    }

    public void releaseSource() {
        this.childSource.releaseSource();
    }

    private static final class LoopingTimeline extends Timeline {
        private final int childPeriodCount;
        private final Timeline childTimeline;
        private final int childWindowCount;
        private final int loopCount;

        public LoopingTimeline(Timeline timeline, int i) {
            this.childTimeline = timeline;
            this.childPeriodCount = timeline.getPeriodCount();
            this.childWindowCount = timeline.getWindowCount();
            int i2 = LoopingMediaSource.MAX_EXPOSED_PERIODS / this.childPeriodCount;
            if (i > i2) {
                if (i != Integer.MAX_VALUE) {
                    Log.w(LoopingMediaSource.TAG, "Capped loops to avoid overflow: " + i + " -> " + i2);
                }
                this.loopCount = i2;
                return;
            }
            this.loopCount = i;
        }

        public int getWindowCount() {
            return this.childWindowCount * this.loopCount;
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            this.childTimeline.getWindow(i % this.childWindowCount, window, z, j);
            int i2 = (i / this.childWindowCount) * this.childPeriodCount;
            window.firstPeriodIndex += i2;
            window.lastPeriodIndex += i2;
            return window;
        }

        public int getPeriodCount() {
            return this.childPeriodCount * this.loopCount;
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            this.childTimeline.getPeriod(i % this.childPeriodCount, period, z);
            int i2 = i / this.childPeriodCount;
            period.windowIndex += this.childWindowCount * i2;
            if (z) {
                period.uid = Pair.create(Integer.valueOf(i2), period.uid);
            }
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            if (!(obj instanceof Pair)) {
                return -1;
            }
            Pair pair = (Pair) obj;
            if (!(pair.first instanceof Integer)) {
                return -1;
            }
            return this.childTimeline.getIndexOfPeriod(pair.second) + (((Integer) pair.first).intValue() * this.childPeriodCount);
        }
    }
}
