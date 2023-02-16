package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private long endUs = C.TIME_UNSET;
    public final MediaPeriod mediaPeriod;
    private boolean pendingInitialDiscontinuity;
    private ClippingSampleStream[] sampleStreams = new ClippingSampleStream[0];
    private long startUs = C.TIME_UNSET;

    public ClippingMediaPeriod(MediaPeriod mediaPeriod2) {
        this.mediaPeriod = mediaPeriod2;
    }

    public void setClipping(long j, long j2) {
        this.startUs = j;
        this.endUs = j2;
    }

    public void prepare(MediaPeriod.Callback callback2) {
        this.callback = callback2;
        this.mediaPeriod.prepare(this);
    }

    public void maybeThrowPrepareError() throws IOException {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        SampleStream[] sampleStreamArr2;
        SampleStream[] sampleStreamArr3 = sampleStreamArr;
        this.sampleStreams = new ClippingSampleStream[sampleStreamArr3.length];
        SampleStream[] sampleStreamArr4 = new SampleStream[sampleStreamArr3.length];
        int i = 0;
        while (true) {
            SampleStream sampleStream = null;
            if (i >= sampleStreamArr3.length) {
                break;
            }
            this.sampleStreams[i] = (ClippingSampleStream) sampleStreamArr3[i];
            if (this.sampleStreams[i] != null) {
                sampleStream = this.sampleStreams[i].stream;
            }
            sampleStreamArr4[i] = sampleStream;
            i++;
        }
        long selectTracks = this.mediaPeriod.selectTracks(trackSelectionArr, zArr, sampleStreamArr4, zArr2, j + this.startUs);
        Assertions.checkState(selectTracks == j + this.startUs || (selectTracks >= this.startUs && (this.endUs == Long.MIN_VALUE || selectTracks <= this.endUs)));
        int i2 = 0;
        while (i2 < sampleStreamArr3.length) {
            if (sampleStreamArr4[i2] == null) {
                this.sampleStreams[i2] = null;
            } else if (sampleStreamArr3[i2] == null || this.sampleStreams[i2].stream != sampleStreamArr4[i2]) {
                sampleStreamArr2 = sampleStreamArr4;
                this.sampleStreams[i2] = new ClippingSampleStream(this, sampleStreamArr4[i2], this.startUs, this.endUs, this.pendingInitialDiscontinuity);
                sampleStreamArr3[i2] = this.sampleStreams[i2];
                i2++;
                sampleStreamArr4 = sampleStreamArr2;
            }
            sampleStreamArr2 = sampleStreamArr4;
            sampleStreamArr3[i2] = this.sampleStreams[i2];
            i2++;
            sampleStreamArr4 = sampleStreamArr2;
        }
        return selectTracks - this.startUs;
    }

    public void discardBuffer(long j) {
        this.mediaPeriod.discardBuffer(j + this.startUs);
    }

    public long readDiscontinuity() {
        if (this.pendingInitialDiscontinuity) {
            for (ClippingSampleStream clippingSampleStream : this.sampleStreams) {
                if (clippingSampleStream != null) {
                    clippingSampleStream.clearPendingDiscontinuity();
                }
            }
            this.pendingInitialDiscontinuity = false;
            long readDiscontinuity = readDiscontinuity();
            if (readDiscontinuity != C.TIME_UNSET) {
                return readDiscontinuity;
            }
            return 0;
        }
        long readDiscontinuity2 = this.mediaPeriod.readDiscontinuity();
        if (readDiscontinuity2 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        boolean z = true;
        Assertions.checkState(readDiscontinuity2 >= this.startUs);
        if (this.endUs != Long.MIN_VALUE && readDiscontinuity2 > this.endUs) {
            z = false;
        }
        Assertions.checkState(z);
        return readDiscontinuity2 - this.startUs;
    }

    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs == Long.MIN_VALUE || (this.endUs != Long.MIN_VALUE && bufferedPositionUs >= this.endUs)) {
            return Long.MIN_VALUE;
        }
        return Math.max(0, bufferedPositionUs - this.startUs);
    }

    public long seekToUs(long j) {
        boolean z = false;
        for (ClippingSampleStream clippingSampleStream : this.sampleStreams) {
            if (clippingSampleStream != null) {
                clippingSampleStream.clearSentEos();
            }
        }
        long seekToUs = this.mediaPeriod.seekToUs(j + this.startUs);
        if (seekToUs == j + this.startUs || (seekToUs >= this.startUs && (this.endUs == Long.MIN_VALUE || seekToUs <= this.endUs))) {
            z = true;
        }
        Assertions.checkState(z);
        return seekToUs - this.startUs;
    }

    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE || (this.endUs != Long.MIN_VALUE && nextLoadPositionUs >= this.endUs)) {
            return Long.MIN_VALUE;
        }
        return nextLoadPositionUs - this.startUs;
    }

    public boolean continueLoading(long j) {
        return this.mediaPeriod.continueLoading(j + this.startUs);
    }

    public void onPrepared(MediaPeriod mediaPeriod2) {
        boolean z = false;
        Assertions.checkState((this.startUs == C.TIME_UNSET || this.endUs == C.TIME_UNSET) ? false : true);
        if (this.startUs != 0) {
            z = true;
        }
        this.pendingInitialDiscontinuity = z;
        this.callback.onPrepared(this);
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
        this.callback.onContinueLoadingRequested(this);
    }

    private static final class ClippingSampleStream implements SampleStream {
        private final long endUs;
        private final MediaPeriod mediaPeriod;
        private boolean pendingDiscontinuity;
        private boolean sentEos;
        private final long startUs;
        /* access modifiers changed from: private */
        public final SampleStream stream;

        public ClippingSampleStream(MediaPeriod mediaPeriod2, SampleStream sampleStream, long j, long j2, boolean z) {
            this.mediaPeriod = mediaPeriod2;
            this.stream = sampleStream;
            this.startUs = j;
            this.endUs = j2;
            this.pendingDiscontinuity = z;
        }

        public void clearPendingDiscontinuity() {
            this.pendingDiscontinuity = false;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        public boolean isReady() {
            return this.stream.isReady();
        }

        public void maybeThrowError() throws IOException {
            this.stream.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            if (this.pendingDiscontinuity) {
                return -3;
            }
            if (this.sentEos) {
                decoderInputBuffer.setFlags(4);
                return -4;
            }
            int readData = this.stream.readData(formatHolder, decoderInputBuffer, z);
            if (this.endUs == Long.MIN_VALUE || ((readData != -4 || decoderInputBuffer.timeUs < this.endUs) && !(readData == -3 && this.mediaPeriod.getBufferedPositionUs() == Long.MIN_VALUE))) {
                if (readData == -4 && !decoderInputBuffer.isEndOfStream()) {
                    decoderInputBuffer.timeUs -= this.startUs;
                }
                return readData;
            }
            decoderInputBuffer.clear();
            decoderInputBuffer.setFlags(4);
            this.sentEos = true;
            return -4;
        }

        public void skipData(long j) {
            this.stream.skipData(this.startUs + j);
        }
    }
}
