package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.WindowManager;

@TargetApi(16)
public final class VideoFrameReleaseTimeHelper {
    private static final long CHOREOGRAPHER_SAMPLE_DELAY_MILLIS = 500;
    private static final long MAX_ALLOWED_DRIFT_NS = 20000000;
    private static final int MIN_FRAMES_FOR_ADJUSTMENT = 6;
    private static final long VSYNC_OFFSET_PERCENTAGE = 80;
    private long adjustedLastFrameTimeNs;
    private long frameCount;
    private boolean haveSync;
    private long lastFramePresentationTimeUs;
    private long pendingAdjustedFrameTimeNs;
    private long syncFramePresentationTimeNs;
    private long syncUnadjustedReleaseTimeNs;
    private final boolean useDefaultDisplayVsync;
    private final long vsyncDurationNs;
    private final long vsyncOffsetNs;
    private final VSyncSampler vsyncSampler;

    /* access modifiers changed from: protected */
    public void onSynced() {
    }

    public VideoFrameReleaseTimeHelper() {
        this(-1.0d, false);
    }

    public VideoFrameReleaseTimeHelper(Context context) {
        this((double) getDefaultDisplayRefreshRate(context), true);
    }

    private VideoFrameReleaseTimeHelper(double d, boolean z) {
        this.useDefaultDisplayVsync = z;
        if (z) {
            this.vsyncSampler = VSyncSampler.getInstance();
            this.vsyncDurationNs = (long) (1.0E9d / d);
            this.vsyncOffsetNs = (this.vsyncDurationNs * VSYNC_OFFSET_PERCENTAGE) / 100;
            return;
        }
        this.vsyncSampler = null;
        this.vsyncDurationNs = -1;
        this.vsyncOffsetNs = -1;
    }

    public void enable() {
        this.haveSync = false;
        if (this.useDefaultDisplayVsync) {
            this.vsyncSampler.addObserver();
        }
    }

    public void disable() {
        if (this.useDefaultDisplayVsync) {
            this.vsyncSampler.removeObserver();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0085 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long adjustReleaseTime(long r21, long r23) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r3 = r23
            r5 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 * r1
            boolean r7 = r0.haveSync
            if (r7 == 0) goto L_0x004d
            long r7 = r0.lastFramePresentationTimeUs
            int r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x001f
            long r7 = r0.frameCount
            r9 = 1
            long r11 = r7 + r9
            r0.frameCount = r11
            long r7 = r0.pendingAdjustedFrameTimeNs
            r0.adjustedLastFrameTimeNs = r7
        L_0x001f:
            long r7 = r0.frameCount
            r9 = 6
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r7 = 0
            if (r11 < 0) goto L_0x0045
            long r8 = r0.syncFramePresentationTimeNs
            long r10 = r5 - r8
            long r8 = r0.frameCount
            long r10 = r10 / r8
            long r8 = r0.adjustedLastFrameTimeNs
            long r12 = r8 + r10
            boolean r8 = r0.isDriftTooLarge(r12, r3)
            if (r8 == 0) goto L_0x003c
            r0.haveSync = r7
            goto L_0x004d
        L_0x003c:
            long r7 = r0.syncUnadjustedReleaseTimeNs
            long r9 = r7 + r12
            long r7 = r0.syncFramePresentationTimeNs
            long r14 = r9 - r7
            goto L_0x004f
        L_0x0045:
            boolean r8 = r0.isDriftTooLarge(r5, r3)
            if (r8 == 0) goto L_0x004d
            r0.haveSync = r7
        L_0x004d:
            r14 = r3
            r12 = r5
        L_0x004f:
            boolean r7 = r0.haveSync
            r8 = 0
            if (r7 != 0) goto L_0x0061
            r0.syncFramePresentationTimeNs = r5
            r0.syncUnadjustedReleaseTimeNs = r3
            r0.frameCount = r8
            r3 = 1
            r0.haveSync = r3
            r20.onSynced()
        L_0x0061:
            r0.lastFramePresentationTimeUs = r1
            r0.pendingAdjustedFrameTimeNs = r12
            com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper$VSyncSampler r1 = r0.vsyncSampler
            if (r1 == 0) goto L_0x0085
            com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper$VSyncSampler r1 = r0.vsyncSampler
            long r1 = r1.sampledVsyncTimeNs
            int r3 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r3 != 0) goto L_0x0072
            goto L_0x0085
        L_0x0072:
            com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper$VSyncSampler r1 = r0.vsyncSampler
            long r1 = r1.sampledVsyncTimeNs
            long r3 = r0.vsyncDurationNs
            r16 = r1
            r18 = r3
            long r1 = closestVsync(r14, r16, r18)
            long r3 = r0.vsyncOffsetNs
            long r5 = r1 - r3
            return r5
        L_0x0085:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper.adjustReleaseTime(long, long):long");
    }

    private boolean isDriftTooLarge(long j, long j2) {
        return Math.abs((j2 - this.syncUnadjustedReleaseTimeNs) - (j - this.syncFramePresentationTimeNs)) > MAX_ALLOWED_DRIFT_NS;
    }

    private static long closestVsync(long j, long j2, long j3) {
        long j4;
        long j5 = j2 + (((j - j2) / j3) * j3);
        if (j <= j5) {
            j4 = j5;
            j5 -= j3;
        } else {
            j4 = j5 + j3;
        }
        return j4 - j < j - j5 ? j4 : j5;
    }

    private static float getDefaultDisplayRefreshRate(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRefreshRate();
    }

    private static final class VSyncSampler implements Choreographer.FrameCallback, Handler.Callback {
        private static final int CREATE_CHOREOGRAPHER = 0;
        private static final VSyncSampler INSTANCE = new VSyncSampler();
        private static final int MSG_ADD_OBSERVER = 1;
        private static final int MSG_REMOVE_OBSERVER = 2;
        private Choreographer choreographer;
        private final HandlerThread choreographerOwnerThread = new HandlerThread("ChoreographerOwner:Handler");
        private final Handler handler;
        private int observerCount;
        public volatile long sampledVsyncTimeNs;

        public static VSyncSampler getInstance() {
            return INSTANCE;
        }

        private VSyncSampler() {
            this.choreographerOwnerThread.start();
            this.handler = new Handler(this.choreographerOwnerThread.getLooper(), this);
            this.handler.sendEmptyMessage(0);
        }

        public void addObserver() {
            this.handler.sendEmptyMessage(1);
        }

        public void removeObserver() {
            this.handler.sendEmptyMessage(2);
        }

        public void doFrame(long j) {
            this.sampledVsyncTimeNs = j;
            this.choreographer.postFrameCallbackDelayed(this, VideoFrameReleaseTimeHelper.CHOREOGRAPHER_SAMPLE_DELAY_MILLIS);
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    createChoreographerInstanceInternal();
                    return true;
                case 1:
                    addObserverInternal();
                    return true;
                case 2:
                    removeObserverInternal();
                    return true;
                default:
                    return false;
            }
        }

        private void createChoreographerInstanceInternal() {
            this.choreographer = Choreographer.getInstance();
        }

        private void addObserverInternal() {
            this.observerCount++;
            if (this.observerCount == 1) {
                this.choreographer.postFrameCallback(this);
            }
        }

        private void removeObserverInternal() {
            this.observerCount--;
            if (this.observerCount == 0) {
                this.choreographer.removeFrameCallback(this);
                this.sampledVsyncTimeNs = 0;
            }
        }
    }
}
