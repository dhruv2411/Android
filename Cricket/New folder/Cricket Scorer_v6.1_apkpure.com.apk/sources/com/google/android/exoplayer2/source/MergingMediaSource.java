package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;

public final class MergingMediaSource implements MediaSource {
    private static final int PERIOD_COUNT_UNSET = -1;
    private MediaSource.Listener listener;
    private final MediaSource[] mediaSources;
    private IllegalMergeException mergeError;
    private final ArrayList<MediaSource> pendingTimelineSources;
    private int periodCount = -1;
    private Object primaryManifest;
    private Timeline primaryTimeline;
    private final Timeline.Window window = new Timeline.Window();

    public static final class IllegalMergeException extends IOException {
        public static final int REASON_PERIOD_COUNT_MISMATCH = 1;
        public static final int REASON_WINDOWS_ARE_DYNAMIC = 0;
        public final int reason;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalMergeException(int i) {
            this.reason = i;
        }
    }

    public MergingMediaSource(MediaSource... mediaSourceArr) {
        this.mediaSources = mediaSourceArr;
        this.pendingTimelineSources = new ArrayList<>(Arrays.asList(mediaSourceArr));
    }

    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener2) {
        this.listener = listener2;
        for (final int i = 0; i < this.mediaSources.length; i++) {
            this.mediaSources[i].prepareSource(exoPlayer, false, new MediaSource.Listener() {
                public void onSourceInfoRefreshed(Timeline timeline, Object obj) {
                    MergingMediaSource.this.handleSourceInfoRefreshed(i, timeline, obj);
                }
            });
        }
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        if (this.mergeError != null) {
            throw this.mergeError;
        }
        for (MediaSource maybeThrowSourceInfoRefreshError : this.mediaSources) {
            maybeThrowSourceInfoRefreshError.maybeThrowSourceInfoRefreshError();
        }
    }

    public MediaPeriod createPeriod(int i, Allocator allocator, long j) {
        MediaPeriod[] mediaPeriodArr = new MediaPeriod[this.mediaSources.length];
        for (int i2 = 0; i2 < mediaPeriodArr.length; i2++) {
            mediaPeriodArr[i2] = this.mediaSources[i2].createPeriod(i, allocator, j);
        }
        return new MergingMediaPeriod(mediaPeriodArr);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        MergingMediaPeriod mergingMediaPeriod = (MergingMediaPeriod) mediaPeriod;
        for (int i = 0; i < this.mediaSources.length; i++) {
            this.mediaSources[i].releasePeriod(mergingMediaPeriod.periods[i]);
        }
    }

    public void releaseSource() {
        for (MediaSource releaseSource : this.mediaSources) {
            releaseSource.releaseSource();
        }
    }

    /* access modifiers changed from: private */
    public void handleSourceInfoRefreshed(int i, Timeline timeline, Object obj) {
        if (this.mergeError == null) {
            this.mergeError = checkTimelineMerges(timeline);
        }
        if (this.mergeError == null) {
            this.pendingTimelineSources.remove(this.mediaSources[i]);
            if (i == 0) {
                this.primaryTimeline = timeline;
                this.primaryManifest = obj;
            }
            if (this.pendingTimelineSources.isEmpty()) {
                this.listener.onSourceInfoRefreshed(this.primaryTimeline, this.primaryManifest);
            }
        }
    }

    private IllegalMergeException checkTimelineMerges(Timeline timeline) {
        int windowCount = timeline.getWindowCount();
        for (int i = 0; i < windowCount; i++) {
            if (timeline.getWindow(i, this.window, false).isDynamic) {
                return new IllegalMergeException(0);
            }
        }
        if (this.periodCount == -1) {
            this.periodCount = timeline.getPeriodCount();
            return null;
        } else if (timeline.getPeriodCount() != this.periodCount) {
            return new IllegalMergeException(1);
        } else {
            return null;
        }
    }
}
