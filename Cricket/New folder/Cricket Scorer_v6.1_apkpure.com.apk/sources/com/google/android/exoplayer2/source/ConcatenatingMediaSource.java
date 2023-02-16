package com.google.android.exoplayer2.source;

import android.util.Pair;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public final class ConcatenatingMediaSource implements MediaSource {
    private final boolean[] duplicateFlags;
    private MediaSource.Listener listener;
    private final Object[] manifests;
    private final MediaSource[] mediaSources;
    private final Map<MediaPeriod, Integer> sourceIndexByMediaPeriod = new HashMap();
    private ConcatenatedTimeline timeline;
    private final Timeline[] timelines;

    public ConcatenatingMediaSource(MediaSource... mediaSourceArr) {
        this.mediaSources = mediaSourceArr;
        this.timelines = new Timeline[mediaSourceArr.length];
        this.manifests = new Object[mediaSourceArr.length];
        this.duplicateFlags = buildDuplicateFlags(mediaSourceArr);
    }

    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener2) {
        this.listener = listener2;
        for (final int i = 0; i < this.mediaSources.length; i++) {
            if (!this.duplicateFlags[i]) {
                this.mediaSources[i].prepareSource(exoPlayer, false, new MediaSource.Listener() {
                    public void onSourceInfoRefreshed(Timeline timeline, Object obj) {
                        ConcatenatingMediaSource.this.handleSourceInfoRefreshed(i, timeline, obj);
                    }
                });
            }
        }
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        for (int i = 0; i < this.mediaSources.length; i++) {
            if (!this.duplicateFlags[i]) {
                this.mediaSources[i].maybeThrowSourceInfoRefreshError();
            }
        }
    }

    public MediaPeriod createPeriod(int i, Allocator allocator, long j) {
        int access$100 = this.timeline.getSourceIndexForPeriod(i);
        MediaPeriod createPeriod = this.mediaSources[access$100].createPeriod(i - this.timeline.getFirstPeriodIndexInSource(access$100), allocator, j);
        this.sourceIndexByMediaPeriod.put(createPeriod, Integer.valueOf(access$100));
        return createPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        int intValue = this.sourceIndexByMediaPeriod.get(mediaPeriod).intValue();
        this.sourceIndexByMediaPeriod.remove(mediaPeriod);
        this.mediaSources[intValue].releasePeriod(mediaPeriod);
    }

    public void releaseSource() {
        for (int i = 0; i < this.mediaSources.length; i++) {
            if (!this.duplicateFlags[i]) {
                this.mediaSources[i].releaseSource();
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleSourceInfoRefreshed(int i, Timeline timeline2, Object obj) {
        this.timelines[i] = timeline2;
        this.manifests[i] = obj;
        for (int i2 = i + 1; i2 < this.mediaSources.length; i2++) {
            if (this.mediaSources[i2] == this.mediaSources[i]) {
                this.timelines[i2] = timeline2;
                this.manifests[i2] = obj;
            }
        }
        Timeline[] timelineArr = this.timelines;
        int length = timelineArr.length;
        int i3 = 0;
        while (i3 < length) {
            if (timelineArr[i3] != null) {
                i3++;
            } else {
                return;
            }
        }
        this.timeline = new ConcatenatedTimeline((Timeline[]) this.timelines.clone());
        this.listener.onSourceInfoRefreshed(this.timeline, this.manifests.clone());
    }

    private static boolean[] buildDuplicateFlags(MediaSource[] mediaSourceArr) {
        boolean[] zArr = new boolean[mediaSourceArr.length];
        IdentityHashMap identityHashMap = new IdentityHashMap(mediaSourceArr.length);
        for (int i = 0; i < mediaSourceArr.length; i++) {
            MediaSource mediaSource = mediaSourceArr[i];
            if (!identityHashMap.containsKey(mediaSource)) {
                identityHashMap.put(mediaSource, (Object) null);
            } else {
                zArr[i] = true;
            }
        }
        return zArr;
    }

    private static final class ConcatenatedTimeline extends Timeline {
        private final int[] sourcePeriodOffsets;
        private final int[] sourceWindowOffsets;
        private final Timeline[] timelines;

        public ConcatenatedTimeline(Timeline[] timelineArr) {
            int[] iArr = new int[timelineArr.length];
            int[] iArr2 = new int[timelineArr.length];
            long j = 0;
            int i = 0;
            int i2 = 0;
            while (i < timelineArr.length) {
                Timeline timeline = timelineArr[i];
                long periodCount = j + ((long) timeline.getPeriodCount());
                Assertions.checkState(periodCount <= 2147483647L, "ConcatenatingMediaSource children contain too many periods");
                iArr[i] = (int) periodCount;
                i2 += timeline.getWindowCount();
                iArr2[i] = i2;
                i++;
                j = periodCount;
            }
            this.timelines = timelineArr;
            this.sourcePeriodOffsets = iArr;
            this.sourceWindowOffsets = iArr2;
        }

        public int getWindowCount() {
            return this.sourceWindowOffsets[this.sourceWindowOffsets.length - 1];
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            int sourceIndexForWindow = getSourceIndexForWindow(i);
            int firstWindowIndexInSource = getFirstWindowIndexInSource(sourceIndexForWindow);
            int firstPeriodIndexInSource = getFirstPeriodIndexInSource(sourceIndexForWindow);
            this.timelines[sourceIndexForWindow].getWindow(i - firstWindowIndexInSource, window, z, j);
            window.firstPeriodIndex += firstPeriodIndexInSource;
            window.lastPeriodIndex += firstPeriodIndexInSource;
            return window;
        }

        public int getPeriodCount() {
            return this.sourcePeriodOffsets[this.sourcePeriodOffsets.length - 1];
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            int sourceIndexForPeriod = getSourceIndexForPeriod(i);
            int firstWindowIndexInSource = getFirstWindowIndexInSource(sourceIndexForPeriod);
            this.timelines[sourceIndexForPeriod].getPeriod(i - getFirstPeriodIndexInSource(sourceIndexForPeriod), period, z);
            period.windowIndex += firstWindowIndexInSource;
            if (z) {
                period.uid = Pair.create(Integer.valueOf(sourceIndexForPeriod), period.uid);
            }
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            int indexOfPeriod;
            if (!(obj instanceof Pair)) {
                return -1;
            }
            Pair pair = (Pair) obj;
            if (!(pair.first instanceof Integer)) {
                return -1;
            }
            int intValue = ((Integer) pair.first).intValue();
            Object obj2 = pair.second;
            if (intValue < 0 || intValue >= this.timelines.length || (indexOfPeriod = this.timelines[intValue].getIndexOfPeriod(obj2)) == -1) {
                return -1;
            }
            return getFirstPeriodIndexInSource(intValue) + indexOfPeriod;
        }

        /* access modifiers changed from: private */
        public int getSourceIndexForPeriod(int i) {
            return Util.binarySearchFloor(this.sourcePeriodOffsets, i, true, false) + 1;
        }

        /* access modifiers changed from: private */
        public int getFirstPeriodIndexInSource(int i) {
            if (i == 0) {
                return 0;
            }
            return this.sourcePeriodOffsets[i - 1];
        }

        private int getSourceIndexForWindow(int i) {
            return Util.binarySearchFloor(this.sourceWindowOffsets, i, true, false) + 1;
        }

        private int getFirstWindowIndexInSource(int i) {
            if (i == 0) {
                return 0;
            }
            return this.sourceWindowOffsets[i - 1];
        }
    }
}
