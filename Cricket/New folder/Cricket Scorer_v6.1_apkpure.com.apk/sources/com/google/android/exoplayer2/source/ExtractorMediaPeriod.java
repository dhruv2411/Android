package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.DefaultTrackOutput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;

final class ExtractorMediaPeriod implements MediaPeriod, ExtractorOutput, Loader.Callback<ExtractingLoadable>, DefaultTrackOutput.UpstreamFormatChangedListener {
    private static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    private final Allocator allocator;
    /* access modifiers changed from: private */
    public MediaPeriod.Callback callback;
    /* access modifiers changed from: private */
    public final String customCacheKey;
    private final DataSource dataSource;
    private long durationUs;
    private int enabledTrackCount;
    private final Handler eventHandler;
    /* access modifiers changed from: private */
    public final ExtractorMediaSource.EventListener eventListener;
    private int extractedSamplesCountAtStartOfLoad;
    private final ExtractorHolder extractorHolder;
    /* access modifiers changed from: private */
    public final Handler handler;
    private boolean haveAudioVideoTracks;
    private long lastSeekPositionUs;
    private long length;
    private final ConditionVariable loadCondition;
    private final Loader loader = new Loader("Loader:ExtractorMediaPeriod");
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final int minLoadableRetryCount;
    private boolean notifyReset;
    /* access modifiers changed from: private */
    public final Runnable onContinueLoadingRequestedRunnable;
    private long pendingResetPositionUs;
    private boolean prepared;
    /* access modifiers changed from: private */
    public boolean released;
    /* access modifiers changed from: private */
    public final SparseArray<DefaultTrackOutput> sampleQueues;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private final MediaSource.Listener sourceListener;
    private boolean[] trackEnabledStates;
    private boolean[] trackIsAudioVideoFlags;
    private TrackGroupArray tracks;
    private boolean tracksBuilt;
    private final Uri uri;

    public void discardBuffer(long j) {
    }

    public ExtractorMediaPeriod(Uri uri2, DataSource dataSource2, Extractor[] extractorArr, int i, Handler handler2, ExtractorMediaSource.EventListener eventListener2, MediaSource.Listener listener, Allocator allocator2, String str) {
        this.uri = uri2;
        this.dataSource = dataSource2;
        this.minLoadableRetryCount = i;
        this.eventHandler = handler2;
        this.eventListener = eventListener2;
        this.sourceListener = listener;
        this.allocator = allocator2;
        this.customCacheKey = str;
        this.extractorHolder = new ExtractorHolder(extractorArr, this);
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new Runnable() {
            public void run() {
                ExtractorMediaPeriod.this.maybeFinishPrepare();
            }
        };
        this.onContinueLoadingRequestedRunnable = new Runnable() {
            public void run() {
                if (!ExtractorMediaPeriod.this.released) {
                    ExtractorMediaPeriod.this.callback.onContinueLoadingRequested(ExtractorMediaPeriod.this);
                }
            }
        };
        this.handler = new Handler();
        this.pendingResetPositionUs = C.TIME_UNSET;
        this.sampleQueues = new SparseArray<>();
        this.length = -1;
    }

    public void release() {
        final ExtractorHolder extractorHolder2 = this.extractorHolder;
        this.loader.release(new Runnable() {
            public void run() {
                extractorHolder2.release();
                int size = ExtractorMediaPeriod.this.sampleQueues.size();
                for (int i = 0; i < size; i++) {
                    ((DefaultTrackOutput) ExtractorMediaPeriod.this.sampleQueues.valueAt(i)).disable();
                }
            }
        });
        this.handler.removeCallbacksAndMessages((Object) null);
        this.released = true;
    }

    public void prepare(MediaPeriod.Callback callback2) {
        this.callback = callback2;
        this.loadCondition.open();
        startLoading();
    }

    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        Assertions.checkState(this.prepared);
        for (int i = 0; i < trackSelectionArr.length; i++) {
            if (sampleStreamArr[i] != null && (trackSelectionArr[i] == null || !zArr[i])) {
                int access$400 = sampleStreamArr[i].track;
                Assertions.checkState(this.trackEnabledStates[access$400]);
                this.enabledTrackCount--;
                this.trackEnabledStates[access$400] = false;
                this.sampleQueues.valueAt(access$400).disable();
                sampleStreamArr[i] = null;
            }
        }
        boolean z = false;
        for (int i2 = 0; i2 < trackSelectionArr.length; i2++) {
            if (sampleStreamArr[i2] == null && trackSelectionArr[i2] != null) {
                TrackSelection trackSelection = trackSelectionArr[i2];
                Assertions.checkState(trackSelection.length() == 1);
                Assertions.checkState(trackSelection.getIndexInTrackGroup(0) == 0);
                int indexOf = this.tracks.indexOf(trackSelection.getTrackGroup());
                Assertions.checkState(!this.trackEnabledStates[indexOf]);
                this.enabledTrackCount++;
                this.trackEnabledStates[indexOf] = true;
                sampleStreamArr[i2] = new SampleStreamImpl(indexOf);
                zArr2[i2] = true;
                z = true;
            }
        }
        if (!this.seenFirstTrackSelection) {
            int size = this.sampleQueues.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (!this.trackEnabledStates[i3]) {
                    this.sampleQueues.valueAt(i3).disable();
                }
            }
        }
        if (this.enabledTrackCount == 0) {
            this.notifyReset = false;
            if (this.loader.isLoading()) {
                this.loader.cancelLoading();
            }
        } else if (!this.seenFirstTrackSelection ? j != 0 : z) {
            j = seekToUs(j);
            for (int i4 = 0; i4 < sampleStreamArr.length; i4++) {
                if (sampleStreamArr[i4] != null) {
                    zArr2[i4] = true;
                }
            }
        }
        this.seenFirstTrackSelection = true;
        return j;
    }

    public boolean continueLoading(long j) {
        if (this.loadingFinished) {
            return false;
        }
        if (this.prepared && this.enabledTrackCount == 0) {
            return false;
        }
        boolean open = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return open;
        }
        startLoading();
        return true;
    }

    public long getNextLoadPositionUs() {
        if (this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        return getBufferedPositionUs();
    }

    public long readDiscontinuity() {
        if (!this.notifyReset) {
            return C.TIME_UNSET;
        }
        this.notifyReset = false;
        return this.lastSeekPositionUs;
    }

    public long getBufferedPositionUs() {
        long j;
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            j = Long.MAX_VALUE;
            int size = this.sampleQueues.size();
            for (int i = 0; i < size; i++) {
                if (this.trackIsAudioVideoFlags[i]) {
                    j = Math.min(j, this.sampleQueues.valueAt(i).getLargestQueuedTimestampUs());
                }
            }
        } else {
            j = getLargestQueuedTimestampUs();
        }
        return j == Long.MIN_VALUE ? this.lastSeekPositionUs : j;
    }

    public long seekToUs(long j) {
        if (!this.seekMap.isSeekable()) {
            j = 0;
        }
        this.lastSeekPositionUs = j;
        int size = this.sampleQueues.size();
        boolean z = !isPendingReset();
        int i = 0;
        while (z && i < size) {
            if (this.trackEnabledStates[i]) {
                z = this.sampleQueues.valueAt(i).skipToKeyframeBefore(j, false);
            }
            i++;
        }
        if (!z) {
            this.pendingResetPositionUs = j;
            this.loadingFinished = false;
            if (this.loader.isLoading()) {
                this.loader.cancelLoading();
            } else {
                for (int i2 = 0; i2 < size; i2++) {
                    this.sampleQueues.valueAt(i2).reset(this.trackEnabledStates[i2]);
                }
            }
        }
        this.notifyReset = false;
        return j;
    }

    /* access modifiers changed from: package-private */
    public boolean isReady(int i) {
        return this.loadingFinished || (!isPendingReset() && !this.sampleQueues.valueAt(i).isEmpty());
    }

    /* access modifiers changed from: package-private */
    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
    }

    /* access modifiers changed from: package-private */
    public int readData(int i, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (this.notifyReset || isPendingReset()) {
            return -3;
        }
        return this.sampleQueues.valueAt(i).readData(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.lastSeekPositionUs);
    }

    /* access modifiers changed from: package-private */
    public void skipData(int i, long j) {
        DefaultTrackOutput valueAt = this.sampleQueues.valueAt(i);
        if (!this.loadingFinished || j <= valueAt.getLargestQueuedTimestampUs()) {
            valueAt.skipToKeyframeBefore(j, true);
        } else {
            valueAt.skipAll();
        }
    }

    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j, long j2) {
        copyLengthFromLoader(extractingLoadable);
        this.loadingFinished = true;
        if (this.durationUs == C.TIME_UNSET) {
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs();
            this.durationUs = largestQueuedTimestampUs == Long.MIN_VALUE ? 0 : largestQueuedTimestampUs + DEFAULT_LAST_SAMPLE_DURATION_US;
            this.sourceListener.onSourceInfoRefreshed(new SinglePeriodTimeline(this.durationUs, this.seekMap.isSeekable()), (Object) null);
        }
        this.callback.onContinueLoadingRequested(this);
    }

    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j, long j2, boolean z) {
        copyLengthFromLoader(extractingLoadable);
        if (!z && this.enabledTrackCount > 0) {
            int size = this.sampleQueues.size();
            for (int i = 0; i < size; i++) {
                this.sampleQueues.valueAt(i).reset(this.trackEnabledStates[i]);
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    public int onLoadError(ExtractingLoadable extractingLoadable, long j, long j2, IOException iOException) {
        copyLengthFromLoader(extractingLoadable);
        notifyLoadError(iOException);
        if (isLoadableExceptionFatal(iOException)) {
            return 3;
        }
        int i = getExtractedSamplesCount() > this.extractedSamplesCountAtStartOfLoad ? 1 : 0;
        configureRetry(extractingLoadable);
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        return i;
    }

    public TrackOutput track(int i, int i2) {
        DefaultTrackOutput defaultTrackOutput = this.sampleQueues.get(i);
        if (defaultTrackOutput != null) {
            return defaultTrackOutput;
        }
        DefaultTrackOutput defaultTrackOutput2 = new DefaultTrackOutput(this.allocator);
        defaultTrackOutput2.setUpstreamFormatChangeListener(this);
        this.sampleQueues.put(i, defaultTrackOutput2);
        return defaultTrackOutput2;
    }

    public void endTracks() {
        this.tracksBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void seekMap(SeekMap seekMap2) {
        this.seekMap = seekMap2;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, boolean], vars: [r4v0 ?, r4v3 ?, r4v5 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    public void maybeFinishPrepare() {
        /*
            r8 = this;
            boolean r0 = r8.released
            if (r0 != 0) goto L_0x009e
            boolean r0 = r8.prepared
            if (r0 != 0) goto L_0x009e
            com.google.android.exoplayer2.extractor.SeekMap r0 = r8.seekMap
            if (r0 == 0) goto L_0x009e
            boolean r0 = r8.tracksBuilt
            if (r0 != 0) goto L_0x0012
            goto L_0x009e
        L_0x0012:
            android.util.SparseArray<com.google.android.exoplayer2.extractor.DefaultTrackOutput> r0 = r8.sampleQueues
            int r0 = r0.size()
            r1 = 0
            r2 = r1
        L_0x001a:
            if (r2 >= r0) goto L_0x002e
            android.util.SparseArray<com.google.android.exoplayer2.extractor.DefaultTrackOutput> r3 = r8.sampleQueues
            java.lang.Object r3 = r3.valueAt(r2)
            com.google.android.exoplayer2.extractor.DefaultTrackOutput r3 = (com.google.android.exoplayer2.extractor.DefaultTrackOutput) r3
            com.google.android.exoplayer2.Format r3 = r3.getUpstreamFormat()
            if (r3 != 0) goto L_0x002b
            return
        L_0x002b:
            int r2 = r2 + 1
            goto L_0x001a
        L_0x002e:
            com.google.android.exoplayer2.util.ConditionVariable r2 = r8.loadCondition
            r2.close()
            com.google.android.exoplayer2.source.TrackGroup[] r2 = new com.google.android.exoplayer2.source.TrackGroup[r0]
            boolean[] r3 = new boolean[r0]
            r8.trackIsAudioVideoFlags = r3
            boolean[] r3 = new boolean[r0]
            r8.trackEnabledStates = r3
            com.google.android.exoplayer2.extractor.SeekMap r3 = r8.seekMap
            long r3 = r3.getDurationUs()
            r8.durationUs = r3
            r3 = r1
        L_0x0046:
            r4 = 1
            if (r3 >= r0) goto L_0x007c
            android.util.SparseArray<com.google.android.exoplayer2.extractor.DefaultTrackOutput> r5 = r8.sampleQueues
            java.lang.Object r5 = r5.valueAt(r3)
            com.google.android.exoplayer2.extractor.DefaultTrackOutput r5 = (com.google.android.exoplayer2.extractor.DefaultTrackOutput) r5
            com.google.android.exoplayer2.Format r5 = r5.getUpstreamFormat()
            com.google.android.exoplayer2.source.TrackGroup r6 = new com.google.android.exoplayer2.source.TrackGroup
            com.google.android.exoplayer2.Format[] r7 = new com.google.android.exoplayer2.Format[r4]
            r7[r1] = r5
            r6.<init>(r7)
            r2[r3] = r6
            java.lang.String r5 = r5.sampleMimeType
            boolean r6 = com.google.android.exoplayer2.util.MimeTypes.isVideo(r5)
            if (r6 != 0) goto L_0x0070
            boolean r5 = com.google.android.exoplayer2.util.MimeTypes.isAudio(r5)
            if (r5 == 0) goto L_0x006f
            goto L_0x0070
        L_0x006f:
            r4 = r1
        L_0x0070:
            boolean[] r5 = r8.trackIsAudioVideoFlags
            r5[r3] = r4
            boolean r5 = r8.haveAudioVideoTracks
            r4 = r4 | r5
            r8.haveAudioVideoTracks = r4
            int r3 = r3 + 1
            goto L_0x0046
        L_0x007c:
            com.google.android.exoplayer2.source.TrackGroupArray r0 = new com.google.android.exoplayer2.source.TrackGroupArray
            r0.<init>(r2)
            r8.tracks = r0
            r8.prepared = r4
            com.google.android.exoplayer2.source.MediaSource$Listener r0 = r8.sourceListener
            com.google.android.exoplayer2.source.SinglePeriodTimeline r1 = new com.google.android.exoplayer2.source.SinglePeriodTimeline
            long r2 = r8.durationUs
            com.google.android.exoplayer2.extractor.SeekMap r4 = r8.seekMap
            boolean r4 = r4.isSeekable()
            r1.<init>(r2, r4)
            r2 = 0
            r0.onSourceInfoRefreshed(r1, r2)
            com.google.android.exoplayer2.source.MediaPeriod$Callback r0 = r8.callback
            r0.onPrepared(r8)
            return
        L_0x009e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ExtractorMediaPeriod.maybeFinishPrepare():void");
    }

    private void copyLengthFromLoader(ExtractingLoadable extractingLoadable) {
        if (this.length == -1) {
            this.length = extractingLoadable.length;
        }
    }

    private void startLoading() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.extractorHolder, this.loadCondition);
        if (this.prepared) {
            Assertions.checkState(isPendingReset());
            if (this.durationUs == C.TIME_UNSET || this.pendingResetPositionUs < this.durationUs) {
                extractingLoadable.setLoadPosition(this.seekMap.getPosition(this.pendingResetPositionUs), this.pendingResetPositionUs);
                this.pendingResetPositionUs = C.TIME_UNSET;
            } else {
                this.loadingFinished = true;
                this.pendingResetPositionUs = C.TIME_UNSET;
                return;
            }
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        int i = this.minLoadableRetryCount;
        if (i == -1) {
            i = (this.prepared && this.length == -1 && (this.seekMap == null || this.seekMap.getDurationUs() == C.TIME_UNSET)) ? 6 : 3;
        }
        this.loader.startLoading(extractingLoadable, this, i);
    }

    private void configureRetry(ExtractingLoadable extractingLoadable) {
        if (this.length != -1) {
            return;
        }
        if (this.seekMap == null || this.seekMap.getDurationUs() == C.TIME_UNSET) {
            this.lastSeekPositionUs = 0;
            this.notifyReset = this.prepared;
            int size = this.sampleQueues.size();
            for (int i = 0; i < size; i++) {
                this.sampleQueues.valueAt(i).reset(!this.prepared || this.trackEnabledStates[i]);
            }
            extractingLoadable.setLoadPosition(0, 0);
        }
    }

    private int getExtractedSamplesCount() {
        int size = this.sampleQueues.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.sampleQueues.valueAt(i2).getWriteIndex();
        }
        return i;
    }

    private long getLargestQueuedTimestampUs() {
        int size = this.sampleQueues.size();
        long j = Long.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            j = Math.max(j, this.sampleQueues.valueAt(i).getLargestQueuedTimestampUs());
        }
        return j;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    private boolean isLoadableExceptionFatal(IOException iOException) {
        return iOException instanceof UnrecognizedInputFormatException;
    }

    private void notifyLoadError(final IOException iOException) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post(new Runnable() {
                public void run() {
                    ExtractorMediaPeriod.this.eventListener.onLoadError(iOException);
                }
            });
        }
    }

    private final class SampleStreamImpl implements SampleStream {
        /* access modifiers changed from: private */
        public final int track;

        public SampleStreamImpl(int i) {
            this.track = i;
        }

        public boolean isReady() {
            return ExtractorMediaPeriod.this.isReady(this.track);
        }

        public void maybeThrowError() throws IOException {
            ExtractorMediaPeriod.this.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            return ExtractorMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, z);
        }

        public void skipData(long j) {
            ExtractorMediaPeriod.this.skipData(this.track, j);
        }
    }

    final class ExtractingLoadable implements Loader.Loadable {
        private static final int CONTINUE_LOADING_CHECK_INTERVAL_BYTES = 1048576;
        private final DataSource dataSource;
        private final ExtractorHolder extractorHolder;
        /* access modifiers changed from: private */
        public long length = -1;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private boolean pendingExtractorSeek = true;
        private final PositionHolder positionHolder = new PositionHolder();
        private long seekTimeUs;
        private final Uri uri;

        public ExtractingLoadable(Uri uri2, DataSource dataSource2, ExtractorHolder extractorHolder2, ConditionVariable conditionVariable) {
            this.uri = (Uri) Assertions.checkNotNull(uri2);
            this.dataSource = (DataSource) Assertions.checkNotNull(dataSource2);
            this.extractorHolder = (ExtractorHolder) Assertions.checkNotNull(extractorHolder2);
            this.loadCondition = conditionVariable;
        }

        public void setLoadPosition(long j, long j2) {
            this.positionHolder.position = j;
            this.seekTimeUs = j2;
            this.pendingExtractorSeek = true;
        }

        public void cancelLoad() {
            this.loadCanceled = true;
        }

        public boolean isLoadCanceled() {
            return this.loadCanceled;
        }

        public void load() throws IOException, InterruptedException {
            DefaultExtractorInput defaultExtractorInput;
            int i = 0;
            while (i == 0 && !this.loadCanceled) {
                try {
                    long j = this.positionHolder.position;
                    this.length = this.dataSource.open(new DataSpec(this.uri, j, -1, ExtractorMediaPeriod.this.customCacheKey));
                    if (this.length != -1) {
                        this.length += j;
                    }
                    defaultExtractorInput = new DefaultExtractorInput(this.dataSource, j, this.length);
                    try {
                        Extractor selectExtractor = this.extractorHolder.selectExtractor(defaultExtractorInput, this.dataSource.getUri());
                        if (this.pendingExtractorSeek) {
                            selectExtractor.seek(j, this.seekTimeUs);
                            this.pendingExtractorSeek = false;
                        }
                        while (i == 0 && !this.loadCanceled) {
                            this.loadCondition.block();
                            int read = selectExtractor.read(defaultExtractorInput, this.positionHolder);
                            try {
                                if (defaultExtractorInput.getPosition() > j + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                                    j = defaultExtractorInput.getPosition();
                                    this.loadCondition.close();
                                    ExtractorMediaPeriod.this.handler.post(ExtractorMediaPeriod.this.onContinueLoadingRequestedRunnable);
                                }
                                i = read;
                            } catch (Throwable th) {
                                th = th;
                                i = read;
                                this.positionHolder.position = defaultExtractorInput.getPosition();
                                Util.closeQuietly(this.dataSource);
                                throw th;
                            }
                        }
                        if (i == 1) {
                            i = 0;
                        } else if (defaultExtractorInput != null) {
                            this.positionHolder.position = defaultExtractorInput.getPosition();
                        }
                        Util.closeQuietly(this.dataSource);
                    } catch (Throwable th2) {
                        th = th2;
                        if (!(i == 1 || defaultExtractorInput == null)) {
                            this.positionHolder.position = defaultExtractorInput.getPosition();
                        }
                        Util.closeQuietly(this.dataSource);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    defaultExtractorInput = null;
                    this.positionHolder.position = defaultExtractorInput.getPosition();
                    Util.closeQuietly(this.dataSource);
                    throw th;
                }
            }
        }
    }

    private static final class ExtractorHolder {
        private Extractor extractor;
        private final ExtractorOutput extractorOutput;
        private final Extractor[] extractors;

        public ExtractorHolder(Extractor[] extractorArr, ExtractorOutput extractorOutput2) {
            this.extractors = extractorArr;
            this.extractorOutput = extractorOutput2;
        }

        public Extractor selectExtractor(ExtractorInput extractorInput, Uri uri) throws IOException, InterruptedException {
            if (this.extractor != null) {
                return this.extractor;
            }
            Extractor[] extractorArr = this.extractors;
            int length = extractorArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Extractor extractor2 = extractorArr[i];
                try {
                    if (extractor2.sniff(extractorInput)) {
                        this.extractor = extractor2;
                        extractorInput.resetPeekPosition();
                        break;
                    }
                    extractorInput.resetPeekPosition();
                    i++;
                } catch (EOFException unused) {
                } catch (Throwable th) {
                    extractorInput.resetPeekPosition();
                    throw th;
                }
            }
            if (this.extractor == null) {
                throw new UnrecognizedInputFormatException("None of the available extractors (" + Util.getCommaDelimitedSimpleClassNames(this.extractors) + ") could read the stream.", uri);
            }
            this.extractor.init(this.extractorOutput);
            return this.extractor;
        }

        public void release() {
            if (this.extractor != null) {
                this.extractor.release();
                this.extractor = null;
            }
        }
    }
}
