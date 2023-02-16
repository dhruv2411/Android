package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.StandaloneMediaClock;
import com.google.android.exoplayer2.util.TraceUtil;
import java.io.IOException;

final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSource.Listener {
    private static final int IDLE_INTERVAL_MS = 1000;
    private static final int MAXIMUM_BUFFER_AHEAD_PERIODS = 100;
    private static final int MSG_CUSTOM = 11;
    private static final int MSG_DO_SOME_WORK = 2;
    public static final int MSG_ERROR = 8;
    public static final int MSG_LOADING_CHANGED = 2;
    private static final int MSG_PERIOD_PREPARED = 8;
    public static final int MSG_PLAYBACK_PARAMETERS_CHANGED = 7;
    public static final int MSG_POSITION_DISCONTINUITY = 5;
    private static final int MSG_PREPARE = 0;
    public static final int MSG_PREPARE_ACK = 0;
    private static final int MSG_REFRESH_SOURCE_INFO = 7;
    private static final int MSG_RELEASE = 6;
    public static final int MSG_SEEK_ACK = 4;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 9;
    public static final int MSG_SOURCE_INFO_REFRESHED = 6;
    public static final int MSG_STATE_CHANGED = 1;
    private static final int MSG_STOP = 5;
    public static final int MSG_TRACKS_CHANGED = 3;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 10;
    private static final int PREPARING_SOURCE_INTERVAL_MS = 10;
    private static final int RENDERER_TIMESTAMP_OFFSET_US = 60000000;
    private static final int RENDERING_INTERVAL_MS = 10;
    private static final String TAG = "ExoPlayerImplInternal";
    private int customMessagesProcessed;
    private int customMessagesSent;
    private long elapsedRealtimeUs;
    private Renderer[] enabledRenderers;
    private final Handler eventHandler;
    private final Handler handler;
    private final HandlerThread internalPlaybackThread;
    private boolean isLoading;
    private final LoadControl loadControl;
    private MediaPeriodHolder loadingPeriodHolder;
    private MediaSource mediaSource;
    private int pendingInitialSeekCount;
    private SeekPosition pendingSeekPosition;
    private final Timeline.Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private PlaybackParameters playbackParameters;
    private final ExoPlayer player;
    private MediaPeriodHolder playingPeriodHolder;
    private MediaPeriodHolder readingPeriodHolder;
    private boolean rebuffering;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private MediaClock rendererMediaClock;
    private Renderer rendererMediaClockSource;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private final StandaloneMediaClock standaloneMediaClock;
    private int state = 1;
    private Timeline timeline;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;

    public static final class PlaybackInfo {
        public volatile long bufferedPositionUs;
        public final int periodIndex;
        public volatile long positionUs;
        public final long startPositionUs;

        public PlaybackInfo(int i, long j) {
            this.periodIndex = i;
            this.startPositionUs = j;
            this.positionUs = j;
            this.bufferedPositionUs = j;
        }

        public PlaybackInfo copyWithPeriodIndex(int i) {
            PlaybackInfo playbackInfo = new PlaybackInfo(i, this.startPositionUs);
            playbackInfo.positionUs = this.positionUs;
            playbackInfo.bufferedPositionUs = this.bufferedPositionUs;
            return playbackInfo;
        }
    }

    public static final class SourceInfo {
        public final Object manifest;
        public final PlaybackInfo playbackInfo;
        public final int seekAcks;
        public final Timeline timeline;

        public SourceInfo(Timeline timeline2, Object obj, PlaybackInfo playbackInfo2, int i) {
            this.timeline = timeline2;
            this.manifest = obj;
            this.playbackInfo = playbackInfo2;
            this.seekAcks = i;
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector2, LoadControl loadControl2, boolean z, Handler handler2, PlaybackInfo playbackInfo2, ExoPlayer exoPlayer) {
        this.renderers = rendererArr;
        this.trackSelector = trackSelector2;
        this.loadControl = loadControl2;
        this.playWhenReady = z;
        this.eventHandler = handler2;
        this.playbackInfo = playbackInfo2;
        this.player = exoPlayer;
        this.rendererCapabilities = new RendererCapabilities[rendererArr.length];
        for (int i = 0; i < rendererArr.length; i++) {
            rendererArr[i].setIndex(i);
            this.rendererCapabilities[i] = rendererArr[i].getCapabilities();
        }
        this.standaloneMediaClock = new StandaloneMediaClock();
        this.enabledRenderers = new Renderer[0];
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector2.init(this);
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.internalPlaybackThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread.start();
        this.handler = new Handler(this.internalPlaybackThread.getLooper(), this);
    }

    public void prepare(MediaSource mediaSource2, boolean z) {
        this.handler.obtainMessage(0, z ? 1 : 0, 0, mediaSource2).sendToTarget();
    }

    public void setPlayWhenReady(boolean z) {
        this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
    }

    public void seekTo(Timeline timeline2, int i, long j) {
        this.handler.obtainMessage(3, new SeekPosition(timeline2, i, j)).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters2) {
        this.handler.obtainMessage(4, playbackParameters2).sendToTarget();
    }

    public void stop() {
        this.handler.sendEmptyMessage(5);
    }

    public void sendMessages(ExoPlayer.ExoPlayerMessage... exoPlayerMessageArr) {
        if (this.released) {
            Log.w(TAG, "Ignoring messages sent after release.");
            return;
        }
        this.customMessagesSent++;
        this.handler.obtainMessage(11, exoPlayerMessageArr).sendToTarget();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:11|12|13|14|23|20|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x001f, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0027 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void blockingSendMessages(com.google.android.exoplayer2.ExoPlayer.ExoPlayerMessage... r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.released     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x000e
            java.lang.String r4 = "ExoPlayerImplInternal"
            java.lang.String r0 = "Ignoring messages sent after release."
            android.util.Log.w(r4, r0)     // Catch:{ all -> 0x0031 }
            monitor-exit(r3)
            return
        L_0x000e:
            int r0 = r3.customMessagesSent     // Catch:{ all -> 0x0031 }
            int r1 = r0 + 1
            r3.customMessagesSent = r1     // Catch:{ all -> 0x0031 }
            android.os.Handler r1 = r3.handler     // Catch:{ all -> 0x0031 }
            r2 = 11
            android.os.Message r4 = r1.obtainMessage(r2, r4)     // Catch:{ all -> 0x0031 }
            r4.sendToTarget()     // Catch:{ all -> 0x0031 }
        L_0x001f:
            int r4 = r3.customMessagesProcessed     // Catch:{ all -> 0x0031 }
            if (r4 > r0) goto L_0x002f
            r3.wait()     // Catch:{ InterruptedException -> 0x0027 }
            goto L_0x001f
        L_0x0027:
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0031 }
            r4.interrupt()     // Catch:{ all -> 0x0031 }
            goto L_0x001f
        L_0x002f:
            monitor-exit(r3)
            return
        L_0x0031:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.blockingSendMessages(com.google.android.exoplayer2.ExoPlayer$ExoPlayerMessage[]):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:10|11|12|13|23|20|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x000d, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void release() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.released     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            android.os.Handler r0 = r2.handler     // Catch:{ all -> 0x0024 }
            r1 = 6
            r0.sendEmptyMessage(r1)     // Catch:{ all -> 0x0024 }
        L_0x000d:
            boolean r0 = r2.released     // Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x001d
            r2.wait()     // Catch:{ InterruptedException -> 0x0015 }
            goto L_0x000d
        L_0x0015:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0024 }
            r0.interrupt()     // Catch:{ all -> 0x0024 }
            goto L_0x000d
        L_0x001d:
            android.os.HandlerThread r0 = r2.internalPlaybackThread     // Catch:{ all -> 0x0024 }
            r0.quit()     // Catch:{ all -> 0x0024 }
            monitor-exit(r2)
            return
        L_0x0024:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.release():void");
    }

    public void onSourceInfoRefreshed(Timeline timeline2, Object obj) {
        this.handler.obtainMessage(7, Pair.create(timeline2, obj)).sendToTarget();
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(8, mediaPeriod).sendToTarget();
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(10);
    }

    public boolean handleMessage(Message message) {
        try {
            boolean z = false;
            switch (message.what) {
                case 0:
                    MediaSource mediaSource2 = (MediaSource) message.obj;
                    if (message.arg1 != 0) {
                        z = true;
                    }
                    prepareInternal(mediaSource2, z);
                    return true;
                case 1:
                    if (message.arg1 != 0) {
                        z = true;
                    }
                    setPlayWhenReadyInternal(z);
                    return true;
                case 2:
                    doSomeWork();
                    return true;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    return true;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    return true;
                case 5:
                    stopInternal();
                    return true;
                case 6:
                    releaseInternal();
                    return true;
                case 7:
                    handleSourceInfoRefreshed((Pair) message.obj);
                    return true;
                case 8:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    return true;
                case 9:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    return true;
                case 10:
                    reselectTracksInternal();
                    return true;
                case 11:
                    sendMessagesInternal((ExoPlayer.ExoPlayerMessage[]) message.obj);
                    return true;
                default:
                    return false;
            }
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Renderer error.", e);
            this.eventHandler.obtainMessage(8, e).sendToTarget();
            stopInternal();
            return true;
        } catch (IOException e2) {
            Log.e(TAG, "Source error.", e2);
            this.eventHandler.obtainMessage(8, ExoPlaybackException.createForSource(e2)).sendToTarget();
            stopInternal();
            return true;
        } catch (RuntimeException e3) {
            Log.e(TAG, "Internal runtime error.", e3);
            this.eventHandler.obtainMessage(8, ExoPlaybackException.createForUnexpected(e3)).sendToTarget();
            stopInternal();
            return true;
        }
    }

    private void setState(int i) {
        if (this.state != i) {
            this.state = i;
            this.eventHandler.obtainMessage(1, i, 0).sendToTarget();
        }
    }

    private void setIsLoading(boolean z) {
        if (this.isLoading != z) {
            this.isLoading = z;
            this.eventHandler.obtainMessage(2, z ? 1 : 0, 0).sendToTarget();
        }
    }

    private void prepareInternal(MediaSource mediaSource2, boolean z) {
        this.eventHandler.sendEmptyMessage(0);
        resetInternal(true);
        this.loadControl.onPrepared();
        if (z) {
            this.playbackInfo = new PlaybackInfo(0, C.TIME_UNSET);
        }
        this.mediaSource = mediaSource2;
        mediaSource2.prepareSource(this.player, true, this);
        setState(2);
        this.handler.sendEmptyMessage(2);
    }

    private void setPlayWhenReadyInternal(boolean z) throws ExoPlaybackException {
        this.rebuffering = false;
        this.playWhenReady = z;
        if (!z) {
            stopRenderers();
            updatePlaybackPositions();
        } else if (this.state == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (this.state == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        this.rebuffering = false;
        this.standaloneMediaClock.start();
        for (Renderer start : this.enabledRenderers) {
            start.start();
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.standaloneMediaClock.stop();
        for (Renderer ensureStopped : this.enabledRenderers) {
            ensureStopped(ensureStopped);
        }
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        long j;
        if (this.playingPeriodHolder != null) {
            long readDiscontinuity = this.playingPeriodHolder.mediaPeriod.readDiscontinuity();
            if (readDiscontinuity != C.TIME_UNSET) {
                resetRendererPosition(readDiscontinuity);
            } else {
                if (this.rendererMediaClockSource == null || this.rendererMediaClockSource.isEnded()) {
                    this.rendererPositionUs = this.standaloneMediaClock.getPositionUs();
                } else {
                    this.rendererPositionUs = this.rendererMediaClock.getPositionUs();
                    this.standaloneMediaClock.setPositionUs(this.rendererPositionUs);
                }
                readDiscontinuity = this.playingPeriodHolder.toPeriodTime(this.rendererPositionUs);
            }
            this.playbackInfo.positionUs = readDiscontinuity;
            this.elapsedRealtimeUs = SystemClock.elapsedRealtime() * 1000;
            if (this.enabledRenderers.length == 0) {
                j = Long.MIN_VALUE;
            } else {
                j = this.playingPeriodHolder.mediaPeriod.getBufferedPositionUs();
            }
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            if (j == Long.MIN_VALUE) {
                j = this.timeline.getPeriod(this.playingPeriodHolder.index, this.period).getDurationUs();
            }
            playbackInfo2.bufferedPositionUs = j;
        }
    }

    private void doSomeWork() throws ExoPlaybackException, IOException {
        boolean z;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        updatePeriods();
        if (this.playingPeriodHolder == null) {
            maybeThrowPeriodPrepareError();
            scheduleNextWork(elapsedRealtime, 10);
            return;
        }
        TraceUtil.beginSection("doSomeWork");
        updatePlaybackPositions();
        this.playingPeriodHolder.mediaPeriod.discardBuffer(this.playbackInfo.positionUs);
        boolean z2 = true;
        boolean z3 = true;
        for (Renderer renderer : this.enabledRenderers) {
            renderer.render(this.rendererPositionUs, this.elapsedRealtimeUs);
            z2 = z2 && renderer.isEnded();
            boolean z4 = renderer.isReady() || renderer.isEnded();
            if (!z4) {
                renderer.maybeThrowStreamError();
            }
            z3 = z3 && z4;
        }
        if (!z3) {
            maybeThrowPeriodPrepareError();
        }
        if (this.rendererMediaClock != null) {
            PlaybackParameters playbackParameters2 = this.rendererMediaClock.getPlaybackParameters();
            if (!playbackParameters2.equals(this.playbackParameters)) {
                this.playbackParameters = playbackParameters2;
                this.standaloneMediaClock.synchronize(this.rendererMediaClock);
                this.eventHandler.obtainMessage(7, playbackParameters2).sendToTarget();
            }
        }
        long durationUs = this.timeline.getPeriod(this.playingPeriodHolder.index, this.period).getDurationUs();
        if (z2 && ((durationUs == C.TIME_UNSET || durationUs <= this.playbackInfo.positionUs) && this.playingPeriodHolder.isLast)) {
            setState(4);
            stopRenderers();
        } else if (this.state == 2) {
            if (this.enabledRenderers.length > 0) {
                z = z3 && haveSufficientBuffer(this.rebuffering);
            } else {
                z = isTimelineReady(durationUs);
            }
            if (z) {
                setState(3);
                if (this.playWhenReady) {
                    startRenderers();
                }
            }
        } else if (this.state == 3) {
            if (this.enabledRenderers.length <= 0) {
                z3 = isTimelineReady(durationUs);
            }
            if (!z3) {
                this.rebuffering = this.playWhenReady;
                setState(2);
                stopRenderers();
            }
        }
        if (this.state == 2) {
            for (Renderer maybeThrowStreamError : this.enabledRenderers) {
                maybeThrowStreamError.maybeThrowStreamError();
            }
        }
        if ((this.playWhenReady && this.state == 3) || this.state == 2) {
            scheduleNextWork(elapsedRealtime, 10);
        } else if (this.enabledRenderers.length != 0) {
            scheduleNextWork(elapsedRealtime, 1000);
        } else {
            this.handler.removeMessages(2);
        }
        TraceUtil.endSection();
    }

    private void scheduleNextWork(long j, long j2) {
        this.handler.removeMessages(2);
        long elapsedRealtime = (j + j2) - SystemClock.elapsedRealtime();
        if (elapsedRealtime <= 0) {
            this.handler.sendEmptyMessage(2);
        } else {
            this.handler.sendEmptyMessageDelayed(2, elapsedRealtime);
        }
    }

    private void seekToInternal(SeekPosition seekPosition) throws ExoPlaybackException {
        int i = 1;
        if (this.timeline == null) {
            this.pendingInitialSeekCount++;
            this.pendingSeekPosition = seekPosition;
            return;
        }
        Pair<Integer, Long> resolveSeekPosition = resolveSeekPosition(seekPosition);
        if (resolveSeekPosition == null) {
            this.playbackInfo = new PlaybackInfo(0, 0);
            this.eventHandler.obtainMessage(4, 1, 0, this.playbackInfo).sendToTarget();
            this.playbackInfo = new PlaybackInfo(0, C.TIME_UNSET);
            setState(4);
            resetInternal(false);
            return;
        }
        int i2 = seekPosition.windowPositionUs == C.TIME_UNSET ? 1 : 0;
        int intValue = ((Integer) resolveSeekPosition.first).intValue();
        long longValue = ((Long) resolveSeekPosition.second).longValue();
        try {
            if (intValue != this.playbackInfo.periodIndex || longValue / 1000 != this.playbackInfo.positionUs / 1000) {
                long seekToPeriodPosition = seekToPeriodPosition(intValue, longValue);
                int i3 = i2 | (longValue != seekToPeriodPosition ? 1 : 0);
                this.playbackInfo = new PlaybackInfo(intValue, seekToPeriodPosition);
                Handler handler2 = this.eventHandler;
                if (i3 == 0) {
                    i = 0;
                }
                handler2.obtainMessage(4, i, 0, this.playbackInfo).sendToTarget();
            }
        } finally {
            this.playbackInfo = new PlaybackInfo(intValue, longValue);
            this.eventHandler.obtainMessage(4, i2, 0, this.playbackInfo).sendToTarget();
        }
    }

    private long seekToPeriodPosition(int i, long j) throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder;
        stopRenderers();
        this.rebuffering = false;
        setState(2);
        if (this.playingPeriodHolder == null) {
            if (this.loadingPeriodHolder != null) {
                this.loadingPeriodHolder.release();
            }
            mediaPeriodHolder = null;
        } else {
            mediaPeriodHolder = null;
            for (MediaPeriodHolder mediaPeriodHolder2 = this.playingPeriodHolder; mediaPeriodHolder2 != null; mediaPeriodHolder2 = mediaPeriodHolder2.next) {
                if (mediaPeriodHolder2.index != i || !mediaPeriodHolder2.prepared) {
                    mediaPeriodHolder2.release();
                } else {
                    mediaPeriodHolder = mediaPeriodHolder2;
                }
            }
        }
        if (!(this.playingPeriodHolder == mediaPeriodHolder && this.playingPeriodHolder == this.readingPeriodHolder)) {
            for (Renderer disable : this.enabledRenderers) {
                disable.disable();
            }
            this.enabledRenderers = new Renderer[0];
            this.rendererMediaClock = null;
            this.rendererMediaClockSource = null;
            this.playingPeriodHolder = null;
        }
        if (mediaPeriodHolder != null) {
            mediaPeriodHolder.next = null;
            this.loadingPeriodHolder = mediaPeriodHolder;
            this.readingPeriodHolder = mediaPeriodHolder;
            setPlayingPeriodHolder(mediaPeriodHolder);
            if (this.playingPeriodHolder.hasEnabledTracks) {
                j = this.playingPeriodHolder.mediaPeriod.seekToUs(j);
            }
            resetRendererPosition(j);
            maybeContinueLoading();
        } else {
            this.loadingPeriodHolder = null;
            this.readingPeriodHolder = null;
            this.playingPeriodHolder = null;
            resetRendererPosition(j);
        }
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private void resetRendererPosition(long j) throws ExoPlaybackException {
        long j2;
        if (this.playingPeriodHolder == null) {
            j2 = j + 60000000;
        } else {
            j2 = this.playingPeriodHolder.toRendererTime(j);
        }
        this.rendererPositionUs = j2;
        this.standaloneMediaClock.setPositionUs(this.rendererPositionUs);
        for (Renderer resetPosition : this.enabledRenderers) {
            resetPosition.resetPosition(this.rendererPositionUs);
        }
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters2) {
        PlaybackParameters playbackParameters3;
        if (this.rendererMediaClock != null) {
            playbackParameters3 = this.rendererMediaClock.setPlaybackParameters(playbackParameters2);
        } else {
            playbackParameters3 = this.standaloneMediaClock.setPlaybackParameters(playbackParameters2);
        }
        this.playbackParameters = playbackParameters3;
        this.eventHandler.obtainMessage(7, playbackParameters3).sendToTarget();
    }

    private void stopInternal() {
        resetInternal(true);
        this.loadControl.onStopped();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal(true);
        this.loadControl.onReleased();
        setState(1);
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private void resetInternal(boolean z) {
        this.handler.removeMessages(2);
        this.rebuffering = false;
        this.standaloneMediaClock.stop();
        this.rendererMediaClock = null;
        this.rendererMediaClockSource = null;
        this.rendererPositionUs = 60000000;
        for (Renderer renderer : this.enabledRenderers) {
            try {
                ensureStopped(renderer);
                renderer.disable();
            } catch (ExoPlaybackException | RuntimeException e) {
                Log.e(TAG, "Stop failed.", e);
            }
        }
        this.enabledRenderers = new Renderer[0];
        releasePeriodHoldersFrom(this.playingPeriodHolder != null ? this.playingPeriodHolder : this.loadingPeriodHolder);
        this.loadingPeriodHolder = null;
        this.readingPeriodHolder = null;
        this.playingPeriodHolder = null;
        setIsLoading(false);
        if (z) {
            if (this.mediaSource != null) {
                this.mediaSource.releaseSource();
                this.mediaSource = null;
            }
            this.timeline = null;
        }
    }

    private void sendMessagesInternal(ExoPlayer.ExoPlayerMessage[] exoPlayerMessageArr) throws ExoPlaybackException {
        try {
            for (ExoPlayer.ExoPlayerMessage exoPlayerMessage : exoPlayerMessageArr) {
                exoPlayerMessage.target.handleMessage(exoPlayerMessage.messageType, exoPlayerMessage.message);
            }
            if (this.mediaSource != null) {
                this.handler.sendEmptyMessage(2);
            }
            synchronized (this) {
                this.customMessagesProcessed++;
                notifyAll();
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.customMessagesProcessed++;
                notifyAll();
                throw th;
            }
        }
    }

    private void ensureStopped(Renderer renderer) throws ExoPlaybackException {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        if (this.playingPeriodHolder != null) {
            MediaPeriodHolder mediaPeriodHolder = this.playingPeriodHolder;
            boolean z = true;
            while (mediaPeriodHolder != null && mediaPeriodHolder.prepared) {
                if (mediaPeriodHolder.selectTracks()) {
                    if (z) {
                        boolean z2 = this.readingPeriodHolder != this.playingPeriodHolder;
                        releasePeriodHoldersFrom(this.playingPeriodHolder.next);
                        this.playingPeriodHolder.next = null;
                        this.loadingPeriodHolder = this.playingPeriodHolder;
                        this.readingPeriodHolder = this.playingPeriodHolder;
                        boolean[] zArr = new boolean[this.renderers.length];
                        long updatePeriodTrackSelection = this.playingPeriodHolder.updatePeriodTrackSelection(this.playbackInfo.positionUs, z2, zArr);
                        if (updatePeriodTrackSelection != this.playbackInfo.positionUs) {
                            this.playbackInfo.positionUs = updatePeriodTrackSelection;
                            resetRendererPosition(updatePeriodTrackSelection);
                        }
                        boolean[] zArr2 = new boolean[this.renderers.length];
                        int i = 0;
                        for (int i2 = 0; i2 < this.renderers.length; i2++) {
                            Renderer renderer = this.renderers[i2];
                            zArr2[i2] = renderer.getState() != 0;
                            SampleStream sampleStream = this.playingPeriodHolder.sampleStreams[i2];
                            if (sampleStream != null) {
                                i++;
                            }
                            if (zArr2[i2]) {
                                if (sampleStream != renderer.getStream()) {
                                    if (renderer == this.rendererMediaClockSource) {
                                        if (sampleStream == null) {
                                            this.standaloneMediaClock.synchronize(this.rendererMediaClock);
                                        }
                                        this.rendererMediaClock = null;
                                        this.rendererMediaClockSource = null;
                                    }
                                    ensureStopped(renderer);
                                    renderer.disable();
                                } else if (zArr[i2]) {
                                    renderer.resetPosition(this.rendererPositionUs);
                                }
                            }
                        }
                        this.eventHandler.obtainMessage(3, mediaPeriodHolder.trackSelectorResult).sendToTarget();
                        enableRenderers(zArr2, i);
                    } else {
                        this.loadingPeriodHolder = mediaPeriodHolder;
                        for (MediaPeriodHolder mediaPeriodHolder2 = this.loadingPeriodHolder.next; mediaPeriodHolder2 != null; mediaPeriodHolder2 = mediaPeriodHolder2.next) {
                            mediaPeriodHolder2.release();
                        }
                        this.loadingPeriodHolder.next = null;
                        if (this.loadingPeriodHolder.prepared) {
                            this.loadingPeriodHolder.updatePeriodTrackSelection(Math.max(this.loadingPeriodHolder.startPositionUs, this.loadingPeriodHolder.toPeriodTime(this.rendererPositionUs)), false);
                        }
                    }
                    maybeContinueLoading();
                    updatePlaybackPositions();
                    this.handler.sendEmptyMessage(2);
                    return;
                }
                if (mediaPeriodHolder == this.readingPeriodHolder) {
                    z = false;
                }
                mediaPeriodHolder = mediaPeriodHolder.next;
            }
        }
    }

    private boolean isTimelineReady(long j) {
        return j == C.TIME_UNSET || this.playbackInfo.positionUs < j || (this.playingPeriodHolder.next != null && this.playingPeriodHolder.next.prepared);
    }

    private boolean haveSufficientBuffer(boolean z) {
        long j;
        if (!this.loadingPeriodHolder.prepared) {
            j = this.loadingPeriodHolder.startPositionUs;
        } else {
            j = this.loadingPeriodHolder.mediaPeriod.getBufferedPositionUs();
        }
        if (j == Long.MIN_VALUE) {
            if (this.loadingPeriodHolder.isLast) {
                return true;
            }
            j = this.timeline.getPeriod(this.loadingPeriodHolder.index, this.period).getDurationUs();
        }
        return this.loadControl.shouldStartPlayback(j - this.loadingPeriodHolder.toPeriodTime(this.rendererPositionUs), z);
    }

    private void maybeThrowPeriodPrepareError() throws IOException {
        if (this.loadingPeriodHolder != null && !this.loadingPeriodHolder.prepared) {
            if (this.readingPeriodHolder == null || this.readingPeriodHolder.next == this.loadingPeriodHolder) {
                Renderer[] rendererArr = this.enabledRenderers;
                int length = rendererArr.length;
                int i = 0;
                while (i < length) {
                    if (rendererArr[i].hasReadStreamToEnd()) {
                        i++;
                    } else {
                        return;
                    }
                }
                this.loadingPeriodHolder.mediaPeriod.maybeThrowPrepareError();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleSourceInfoRefreshed(android.util.Pair<com.google.android.exoplayer2.Timeline, java.lang.Object> r12) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r11 = this;
            com.google.android.exoplayer2.Timeline r0 = r11.timeline
            java.lang.Object r1 = r12.first
            com.google.android.exoplayer2.Timeline r1 = (com.google.android.exoplayer2.Timeline) r1
            r11.timeline = r1
            java.lang.Object r12 = r12.second
            r1 = 0
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r4 = 0
            if (r0 != 0) goto L_0x0070
            int r5 = r11.pendingInitialSeekCount
            if (r5 <= 0) goto L_0x0041
            com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition r5 = r11.pendingSeekPosition
            android.util.Pair r5 = r11.resolveSeekPosition(r5)
            int r6 = r11.pendingInitialSeekCount
            r11.pendingInitialSeekCount = r4
            r11.pendingSeekPosition = r1
            if (r5 != 0) goto L_0x0029
            r11.handleSourceInfoRefreshEndedPlayback(r12, r6)
            return
        L_0x0029:
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo r7 = new com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo
            java.lang.Object r8 = r5.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            java.lang.Object r5 = r5.second
            java.lang.Long r5 = (java.lang.Long) r5
            long r9 = r5.longValue()
            r7.<init>(r8, r9)
            r11.playbackInfo = r7
            goto L_0x0071
        L_0x0041:
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo r5 = r11.playbackInfo
            long r5 = r5.startPositionUs
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 != 0) goto L_0x0070
            com.google.android.exoplayer2.Timeline r5 = r11.timeline
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0055
            r11.handleSourceInfoRefreshEndedPlayback(r12, r4)
            return
        L_0x0055:
            android.util.Pair r5 = r11.getPeriodPosition(r4, r2)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo r6 = new com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo
            java.lang.Object r7 = r5.first
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            java.lang.Object r5 = r5.second
            java.lang.Long r5 = (java.lang.Long) r5
            long r8 = r5.longValue()
            r6.<init>(r7, r8)
            r11.playbackInfo = r6
        L_0x0070:
            r6 = r4
        L_0x0071:
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r5 = r11.playingPeriodHolder
            if (r5 == 0) goto L_0x0078
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r5 = r11.playingPeriodHolder
            goto L_0x007a
        L_0x0078:
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r5 = r11.loadingPeriodHolder
        L_0x007a:
            if (r5 != 0) goto L_0x0080
            r11.notifySourceInfoRefresh(r12, r6)
            return
        L_0x0080:
            com.google.android.exoplayer2.Timeline r7 = r11.timeline
            java.lang.Object r8 = r5.uid
            int r7 = r7.getIndexOfPeriod(r8)
            r8 = -1
            r9 = 1
            if (r7 != r8) goto L_0x00e8
            int r1 = r5.index
            com.google.android.exoplayer2.Timeline r4 = r11.timeline
            int r0 = r11.resolveSubsequentPeriod(r1, r0, r4)
            if (r0 != r8) goto L_0x009a
            r11.handleSourceInfoRefreshEndedPlayback(r12, r6)
            return
        L_0x009a:
            com.google.android.exoplayer2.Timeline r1 = r11.timeline
            com.google.android.exoplayer2.Timeline$Period r4 = r11.period
            com.google.android.exoplayer2.Timeline$Period r0 = r1.getPeriod(r0, r4)
            int r0 = r0.windowIndex
            android.util.Pair r0 = r11.getPeriodPosition(r0, r2)
            java.lang.Object r1 = r0.first
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r2 = r0.longValue()
            com.google.android.exoplayer2.Timeline r0 = r11.timeline
            com.google.android.exoplayer2.Timeline$Period r4 = r11.period
            r0.getPeriod(r1, r4, r9)
            com.google.android.exoplayer2.Timeline$Period r0 = r11.period
            java.lang.Object r0 = r0.uid
            r5.index = r8
        L_0x00c5:
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r4 = r5.next
            if (r4 == 0) goto L_0x00d9
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r5 = r5.next
            java.lang.Object r4 = r5.uid
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00d5
            r4 = r1
            goto L_0x00d6
        L_0x00d5:
            r4 = r8
        L_0x00d6:
            r5.index = r4
            goto L_0x00c5
        L_0x00d9:
            long r2 = r11.seekToPeriodPosition(r1, r2)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo r0 = new com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo
            r0.<init>(r1, r2)
            r11.playbackInfo = r0
            r11.notifySourceInfoRefresh(r12, r6)
            return
        L_0x00e8:
            com.google.android.exoplayer2.Timeline r0 = r11.timeline
            com.google.android.exoplayer2.Timeline$Period r2 = r11.period
            r0.getPeriod(r7, r2)
            com.google.android.exoplayer2.Timeline r0 = r11.timeline
            int r0 = r0.getPeriodCount()
            int r0 = r0 - r9
            if (r7 != r0) goto L_0x010a
            com.google.android.exoplayer2.Timeline r0 = r11.timeline
            com.google.android.exoplayer2.Timeline$Period r2 = r11.period
            int r2 = r2.windowIndex
            com.google.android.exoplayer2.Timeline$Window r3 = r11.window
            com.google.android.exoplayer2.Timeline$Window r0 = r0.getWindow(r2, r3)
            boolean r0 = r0.isDynamic
            if (r0 != 0) goto L_0x010a
            r0 = r9
            goto L_0x010b
        L_0x010a:
            r0 = r4
        L_0x010b:
            r5.setIndex(r7, r0)
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r0 = r11.readingPeriodHolder
            if (r5 != r0) goto L_0x0114
            r0 = r9
            goto L_0x0115
        L_0x0114:
            r0 = r4
        L_0x0115:
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo r2 = r11.playbackInfo
            int r2 = r2.periodIndex
            if (r7 == r2) goto L_0x0123
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo r2 = r11.playbackInfo
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo r2 = r2.copyWithPeriodIndex(r7)
            r11.playbackInfo = r2
        L_0x0123:
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r2 = r5.next
            if (r2 == 0) goto L_0x0185
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r2 = r5.next
            int r7 = r7 + r9
            com.google.android.exoplayer2.Timeline r3 = r11.timeline
            com.google.android.exoplayer2.Timeline$Period r8 = r11.period
            r3.getPeriod(r7, r8, r9)
            com.google.android.exoplayer2.Timeline r3 = r11.timeline
            int r3 = r3.getPeriodCount()
            int r3 = r3 - r9
            if (r7 != r3) goto L_0x014c
            com.google.android.exoplayer2.Timeline r3 = r11.timeline
            com.google.android.exoplayer2.Timeline$Period r8 = r11.period
            int r8 = r8.windowIndex
            com.google.android.exoplayer2.Timeline$Window r10 = r11.window
            com.google.android.exoplayer2.Timeline$Window r3 = r3.getWindow(r8, r10)
            boolean r3 = r3.isDynamic
            if (r3 != 0) goto L_0x014c
            r3 = r9
            goto L_0x014d
        L_0x014c:
            r3 = r4
        L_0x014d:
            java.lang.Object r8 = r2.uid
            com.google.android.exoplayer2.Timeline$Period r10 = r11.period
            java.lang.Object r10 = r10.uid
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0166
            r2.setIndex(r7, r3)
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r3 = r11.readingPeriodHolder
            if (r2 != r3) goto L_0x0162
            r3 = r9
            goto L_0x0163
        L_0x0162:
            r3 = r4
        L_0x0163:
            r0 = r0 | r3
            r5 = r2
            goto L_0x0123
        L_0x0166:
            if (r0 != 0) goto L_0x017c
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r0 = r11.playingPeriodHolder
            int r0 = r0.index
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo r1 = r11.playbackInfo
            long r1 = r1.positionUs
            long r1 = r11.seekToPeriodPosition(r0, r1)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo r3 = new com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfo
            r3.<init>(r0, r1)
            r11.playbackInfo = r3
            goto L_0x0185
        L_0x017c:
            r11.loadingPeriodHolder = r5
            com.google.android.exoplayer2.ExoPlayerImplInternal$MediaPeriodHolder r0 = r11.loadingPeriodHolder
            r0.next = r1
            r11.releasePeriodHoldersFrom(r2)
        L_0x0185:
            r11.notifySourceInfoRefresh(r12, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.handleSourceInfoRefreshed(android.util.Pair):void");
    }

    private void handleSourceInfoRefreshEndedPlayback(Object obj, int i) {
        this.playbackInfo = new PlaybackInfo(0, 0);
        notifySourceInfoRefresh(obj, i);
        this.playbackInfo = new PlaybackInfo(0, C.TIME_UNSET);
        setState(4);
        resetInternal(false);
    }

    private void notifySourceInfoRefresh(Object obj, int i) {
        this.eventHandler.obtainMessage(6, new SourceInfo(this.timeline, obj, this.playbackInfo, i)).sendToTarget();
    }

    private int resolveSubsequentPeriod(int i, Timeline timeline2, Timeline timeline3) {
        int i2 = i;
        int i3 = -1;
        while (i3 == -1 && i2 < timeline2.getPeriodCount() - 1) {
            i2++;
            i3 = timeline3.getIndexOfPeriod(timeline2.getPeriod(i2, this.period, true).uid);
        }
        return i3;
    }

    private Pair<Integer, Long> resolveSeekPosition(SeekPosition seekPosition) {
        Timeline timeline2 = seekPosition.timeline;
        if (timeline2.isEmpty()) {
            timeline2 = this.timeline;
        }
        try {
            Pair<Integer, Long> periodPosition = getPeriodPosition(timeline2, seekPosition.windowIndex, seekPosition.windowPositionUs);
            if (this.timeline == timeline2) {
                return periodPosition;
            }
            int indexOfPeriod = this.timeline.getIndexOfPeriod(timeline2.getPeriod(((Integer) periodPosition.first).intValue(), this.period, true).uid);
            if (indexOfPeriod != -1) {
                return Pair.create(Integer.valueOf(indexOfPeriod), periodPosition.second);
            }
            int resolveSubsequentPeriod = resolveSubsequentPeriod(((Integer) periodPosition.first).intValue(), timeline2, this.timeline);
            if (resolveSubsequentPeriod != -1) {
                return getPeriodPosition(this.timeline.getPeriod(resolveSubsequentPeriod, this.period).windowIndex, C.TIME_UNSET);
            }
            return null;
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalSeekPositionException(this.timeline, seekPosition.windowIndex, seekPosition.windowPositionUs);
        }
    }

    private Pair<Integer, Long> getPeriodPosition(int i, long j) {
        return getPeriodPosition(this.timeline, i, j);
    }

    private Pair<Integer, Long> getPeriodPosition(Timeline timeline2, int i, long j) {
        return getPeriodPosition(timeline2, i, j, 0);
    }

    private Pair<Integer, Long> getPeriodPosition(Timeline timeline2, int i, long j, long j2) {
        Assertions.checkIndex(i, 0, timeline2.getWindowCount());
        timeline2.getWindow(i, this.window, false, j2);
        if (j == C.TIME_UNSET) {
            j = this.window.getDefaultPositionUs();
            if (j == C.TIME_UNSET) {
                return null;
            }
        }
        int i2 = this.window.firstPeriodIndex;
        long positionInFirstPeriodUs = this.window.getPositionInFirstPeriodUs() + j;
        long durationUs = timeline2.getPeriod(i2, this.period).getDurationUs();
        while (durationUs != C.TIME_UNSET && positionInFirstPeriodUs >= durationUs && i2 < this.window.lastPeriodIndex) {
            long j3 = positionInFirstPeriodUs - durationUs;
            i2++;
            durationUs = timeline2.getPeriod(i2, this.period).getDurationUs();
            positionInFirstPeriodUs = j3;
        }
        return Pair.create(Integer.valueOf(i2), Long.valueOf(positionInFirstPeriodUs));
    }

    private void updatePeriods() throws ExoPlaybackException, IOException {
        if (this.timeline == null) {
            this.mediaSource.maybeThrowSourceInfoRefreshError();
            return;
        }
        maybeUpdateLoadingPeriod();
        if (this.loadingPeriodHolder == null || this.loadingPeriodHolder.isFullyBuffered()) {
            setIsLoading(false);
        } else if (this.loadingPeriodHolder != null && this.loadingPeriodHolder.needsContinueLoading) {
            maybeContinueLoading();
        }
        if (this.playingPeriodHolder != null) {
            while (this.playingPeriodHolder != this.readingPeriodHolder && this.rendererPositionUs >= this.playingPeriodHolder.next.rendererPositionOffsetUs) {
                this.playingPeriodHolder.release();
                setPlayingPeriodHolder(this.playingPeriodHolder.next);
                this.playbackInfo = new PlaybackInfo(this.playingPeriodHolder.index, this.playingPeriodHolder.startPositionUs);
                updatePlaybackPositions();
                this.eventHandler.obtainMessage(5, this.playbackInfo).sendToTarget();
            }
            if (this.readingPeriodHolder.isLast) {
                for (int i = 0; i < this.renderers.length; i++) {
                    Renderer renderer = this.renderers[i];
                    SampleStream sampleStream = this.readingPeriodHolder.sampleStreams[i];
                    if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                        renderer.setCurrentStreamFinal();
                    }
                }
                return;
            }
            int i2 = 0;
            while (i2 < this.renderers.length) {
                Renderer renderer2 = this.renderers[i2];
                SampleStream sampleStream2 = this.readingPeriodHolder.sampleStreams[i2];
                if (renderer2.getStream() != sampleStream2) {
                    return;
                }
                if (sampleStream2 == null || renderer2.hasReadStreamToEnd()) {
                    i2++;
                } else {
                    return;
                }
            }
            if (this.readingPeriodHolder.next != null && this.readingPeriodHolder.next.prepared) {
                TrackSelectorResult trackSelectorResult = this.readingPeriodHolder.trackSelectorResult;
                this.readingPeriodHolder = this.readingPeriodHolder.next;
                TrackSelectorResult trackSelectorResult2 = this.readingPeriodHolder.trackSelectorResult;
                boolean z = this.readingPeriodHolder.mediaPeriod.readDiscontinuity() != C.TIME_UNSET;
                for (int i3 = 0; i3 < this.renderers.length; i3++) {
                    Renderer renderer3 = this.renderers[i3];
                    if (trackSelectorResult.selections.get(i3) != null) {
                        if (z) {
                            renderer3.setCurrentStreamFinal();
                        } else if (!renderer3.isCurrentStreamFinal()) {
                            TrackSelection trackSelection = trackSelectorResult2.selections.get(i3);
                            RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i3];
                            RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i3];
                            if (trackSelection == null || !rendererConfiguration2.equals(rendererConfiguration)) {
                                renderer3.setCurrentStreamFinal();
                            } else {
                                Format[] formatArr = new Format[trackSelection.length()];
                                for (int i4 = 0; i4 < formatArr.length; i4++) {
                                    formatArr[i4] = trackSelection.getFormat(i4);
                                }
                                renderer3.replaceStream(formatArr, this.readingPeriodHolder.sampleStreams[i3], this.readingPeriodHolder.getRendererOffset());
                            }
                        }
                    }
                }
            }
        }
    }

    private void maybeUpdateLoadingPeriod() throws IOException {
        int i;
        long j;
        if (this.loadingPeriodHolder == null) {
            i = this.playbackInfo.periodIndex;
        } else {
            int i2 = this.loadingPeriodHolder.index;
            if (!this.loadingPeriodHolder.isLast && this.loadingPeriodHolder.isFullyBuffered() && this.timeline.getPeriod(i2, this.period).getDurationUs() != C.TIME_UNSET) {
                if (this.playingPeriodHolder == null || i2 - this.playingPeriodHolder.index != 100) {
                    i = this.loadingPeriodHolder.index + 1;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        if (i >= this.timeline.getPeriodCount()) {
            this.mediaSource.maybeThrowSourceInfoRefreshError();
            return;
        }
        long j2 = 0;
        if (this.loadingPeriodHolder == null) {
            j2 = this.playbackInfo.positionUs;
        } else {
            int i3 = this.timeline.getPeriod(i, this.period).windowIndex;
            if (i == this.timeline.getWindow(i3, this.window).firstPeriodIndex) {
                Pair<Integer, Long> periodPosition = getPeriodPosition(this.timeline, i3, C.TIME_UNSET, Math.max(0, (this.loadingPeriodHolder.getRendererOffset() + this.timeline.getPeriod(this.loadingPeriodHolder.index, this.period).getDurationUs()) - this.rendererPositionUs));
                if (periodPosition != null) {
                    int intValue = ((Integer) periodPosition.first).intValue();
                    j2 = ((Long) periodPosition.second).longValue();
                    i = intValue;
                } else {
                    return;
                }
            }
        }
        long j3 = j2;
        if (this.loadingPeriodHolder == null) {
            j = j3 + 60000000;
        } else {
            j = this.loadingPeriodHolder.getRendererOffset() + this.timeline.getPeriod(this.loadingPeriodHolder.index, this.period).getDurationUs();
        }
        this.timeline.getPeriod(i, this.period, true);
        MediaPeriodHolder mediaPeriodHolder = new MediaPeriodHolder(this.renderers, this.rendererCapabilities, j, this.trackSelector, this.loadControl, this.mediaSource, this.period.uid, i, i == this.timeline.getPeriodCount() - 1 && !this.timeline.getWindow(this.period.windowIndex, this.window).isDynamic, j3);
        if (this.loadingPeriodHolder != null) {
            this.loadingPeriodHolder.next = mediaPeriodHolder;
        }
        this.loadingPeriodHolder = mediaPeriodHolder;
        this.loadingPeriodHolder.mediaPeriod.prepare(this);
        setIsLoading(true);
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.loadingPeriodHolder != null && this.loadingPeriodHolder.mediaPeriod == mediaPeriod) {
            this.loadingPeriodHolder.handlePrepared();
            if (this.playingPeriodHolder == null) {
                this.readingPeriodHolder = this.loadingPeriodHolder;
                resetRendererPosition(this.readingPeriodHolder.startPositionUs);
                setPlayingPeriodHolder(this.readingPeriodHolder);
            }
            maybeContinueLoading();
        }
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.loadingPeriodHolder != null && this.loadingPeriodHolder.mediaPeriod == mediaPeriod) {
            maybeContinueLoading();
        }
    }

    private void maybeContinueLoading() {
        long j;
        if (!this.loadingPeriodHolder.prepared) {
            j = 0;
        } else {
            j = this.loadingPeriodHolder.mediaPeriod.getNextLoadPositionUs();
        }
        if (j == Long.MIN_VALUE) {
            setIsLoading(false);
            return;
        }
        long periodTime = this.loadingPeriodHolder.toPeriodTime(this.rendererPositionUs);
        boolean shouldContinueLoading = this.loadControl.shouldContinueLoading(j - periodTime);
        setIsLoading(shouldContinueLoading);
        if (shouldContinueLoading) {
            this.loadingPeriodHolder.needsContinueLoading = false;
            this.loadingPeriodHolder.mediaPeriod.continueLoading(periodTime);
            return;
        }
        this.loadingPeriodHolder.needsContinueLoading = true;
    }

    private void releasePeriodHoldersFrom(MediaPeriodHolder mediaPeriodHolder) {
        while (mediaPeriodHolder != null) {
            mediaPeriodHolder.release();
            mediaPeriodHolder = mediaPeriodHolder.next;
        }
    }

    private void setPlayingPeriodHolder(MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        if (this.playingPeriodHolder != mediaPeriodHolder) {
            boolean[] zArr = new boolean[this.renderers.length];
            int i = 0;
            for (int i2 = 0; i2 < this.renderers.length; i2++) {
                Renderer renderer = this.renderers[i2];
                zArr[i2] = renderer.getState() != 0;
                TrackSelection trackSelection = mediaPeriodHolder.trackSelectorResult.selections.get(i2);
                if (trackSelection != null) {
                    i++;
                }
                if (zArr[i2] && (trackSelection == null || (renderer.isCurrentStreamFinal() && renderer.getStream() == this.playingPeriodHolder.sampleStreams[i2]))) {
                    if (renderer == this.rendererMediaClockSource) {
                        this.standaloneMediaClock.synchronize(this.rendererMediaClock);
                        this.rendererMediaClock = null;
                        this.rendererMediaClockSource = null;
                    }
                    ensureStopped(renderer);
                    renderer.disable();
                }
            }
            this.playingPeriodHolder = mediaPeriodHolder;
            this.eventHandler.obtainMessage(3, mediaPeriodHolder.trackSelectorResult).sendToTarget();
            enableRenderers(zArr, i);
        }
    }

    private void enableRenderers(boolean[] zArr, int i) throws ExoPlaybackException {
        this.enabledRenderers = new Renderer[i];
        int i2 = 0;
        for (int i3 = 0; i3 < this.renderers.length; i3++) {
            Renderer renderer = this.renderers[i3];
            TrackSelection trackSelection = this.playingPeriodHolder.trackSelectorResult.selections.get(i3);
            if (trackSelection != null) {
                int i4 = i2 + 1;
                this.enabledRenderers[i2] = renderer;
                if (renderer.getState() == 0) {
                    RendererConfiguration rendererConfiguration = this.playingPeriodHolder.trackSelectorResult.rendererConfigurations[i3];
                    boolean z = this.playWhenReady && this.state == 3;
                    boolean z2 = !zArr[i3] && z;
                    Format[] formatArr = new Format[trackSelection.length()];
                    for (int i5 = 0; i5 < formatArr.length; i5++) {
                        formatArr[i5] = trackSelection.getFormat(i5);
                    }
                    renderer.enable(rendererConfiguration, formatArr, this.playingPeriodHolder.sampleStreams[i3], this.rendererPositionUs, z2, this.playingPeriodHolder.getRendererOffset());
                    MediaClock mediaClock = renderer.getMediaClock();
                    if (mediaClock != null) {
                        if (this.rendererMediaClock != null) {
                            throw ExoPlaybackException.createForUnexpected(new IllegalStateException("Multiple renderer media clocks enabled."));
                        }
                        this.rendererMediaClock = mediaClock;
                        this.rendererMediaClockSource = renderer;
                        this.rendererMediaClock.setPlaybackParameters(this.playbackParameters);
                    }
                    if (z) {
                        renderer.start();
                    }
                }
                i2 = i4;
            }
        }
    }

    private static final class MediaPeriodHolder {
        public boolean hasEnabledTracks;
        public int index;
        public boolean isLast;
        private final LoadControl loadControl;
        public final boolean[] mayRetainStreamFlags;
        public final MediaPeriod mediaPeriod;
        private final MediaSource mediaSource;
        public boolean needsContinueLoading;
        public MediaPeriodHolder next;
        private TrackSelectorResult periodTrackSelectorResult;
        public boolean prepared;
        private final RendererCapabilities[] rendererCapabilities;
        public final long rendererPositionOffsetUs;
        private final Renderer[] renderers;
        public final SampleStream[] sampleStreams;
        public long startPositionUs;
        private final TrackSelector trackSelector;
        public TrackSelectorResult trackSelectorResult;
        public final Object uid;

        public MediaPeriodHolder(Renderer[] rendererArr, RendererCapabilities[] rendererCapabilitiesArr, long j, TrackSelector trackSelector2, LoadControl loadControl2, MediaSource mediaSource2, Object obj, int i, boolean z, long j2) {
            this.renderers = rendererArr;
            this.rendererCapabilities = rendererCapabilitiesArr;
            this.rendererPositionOffsetUs = j;
            this.trackSelector = trackSelector2;
            this.loadControl = loadControl2;
            this.mediaSource = mediaSource2;
            this.uid = Assertions.checkNotNull(obj);
            this.index = i;
            this.isLast = z;
            this.startPositionUs = j2;
            this.sampleStreams = new SampleStream[rendererArr.length];
            this.mayRetainStreamFlags = new boolean[rendererArr.length];
            this.mediaPeriod = mediaSource2.createPeriod(i, loadControl2.getAllocator(), j2);
        }

        public long toRendererTime(long j) {
            return j + getRendererOffset();
        }

        public long toPeriodTime(long j) {
            return j - getRendererOffset();
        }

        public long getRendererOffset() {
            return this.rendererPositionOffsetUs - this.startPositionUs;
        }

        public void setIndex(int i, boolean z) {
            this.index = i;
            this.isLast = z;
        }

        public boolean isFullyBuffered() {
            return this.prepared && (!this.hasEnabledTracks || this.mediaPeriod.getBufferedPositionUs() == Long.MIN_VALUE);
        }

        public void handlePrepared() throws ExoPlaybackException {
            this.prepared = true;
            selectTracks();
            this.startPositionUs = updatePeriodTrackSelection(this.startPositionUs, false);
        }

        public boolean selectTracks() throws ExoPlaybackException {
            TrackSelectorResult selectTracks = this.trackSelector.selectTracks(this.rendererCapabilities, this.mediaPeriod.getTrackGroups());
            if (selectTracks.isEquivalent(this.periodTrackSelectorResult)) {
                return false;
            }
            this.trackSelectorResult = selectTracks;
            return true;
        }

        public long updatePeriodTrackSelection(long j, boolean z) {
            return updatePeriodTrackSelection(j, z, new boolean[this.renderers.length]);
        }

        public long updatePeriodTrackSelection(long j, boolean z, boolean[] zArr) {
            TrackSelectionArray trackSelectionArray = this.trackSelectorResult.selections;
            int i = 0;
            while (true) {
                boolean z2 = true;
                if (i >= trackSelectionArray.length) {
                    break;
                }
                boolean[] zArr2 = this.mayRetainStreamFlags;
                if (z || !this.trackSelectorResult.isEquivalent(this.periodTrackSelectorResult, i)) {
                    z2 = false;
                }
                zArr2[i] = z2;
                i++;
            }
            long selectTracks = this.mediaPeriod.selectTracks(trackSelectionArray.getAll(), this.mayRetainStreamFlags, this.sampleStreams, zArr, j);
            this.periodTrackSelectorResult = this.trackSelectorResult;
            this.hasEnabledTracks = false;
            for (int i2 = 0; i2 < this.sampleStreams.length; i2++) {
                if (this.sampleStreams[i2] != null) {
                    Assertions.checkState(trackSelectionArray.get(i2) != null);
                    this.hasEnabledTracks = true;
                } else {
                    Assertions.checkState(trackSelectionArray.get(i2) == null);
                }
            }
            this.loadControl.onTracksSelected(this.renderers, this.trackSelectorResult.groups, trackSelectionArray);
            return selectTracks;
        }

        public void release() {
            try {
                this.mediaSource.releasePeriod(this.mediaPeriod);
            } catch (RuntimeException e) {
                Log.e(ExoPlayerImplInternal.TAG, "Period release failed.", e);
            }
        }
    }

    private static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline2, int i, long j) {
            this.timeline = timeline2;
            this.windowIndex = i;
            this.windowPositionUs = j;
        }
    }
}
