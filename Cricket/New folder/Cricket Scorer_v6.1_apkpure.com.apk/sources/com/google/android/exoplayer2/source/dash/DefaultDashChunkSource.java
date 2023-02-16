package com.google.android.exoplayer2.source.dash;

import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer2.source.chunk.ChunkHolder;
import com.google.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.google.android.exoplayer2.source.chunk.InitializationChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.SingleSampleMediaChunk;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;

public class DefaultDashChunkSource implements DashChunkSource {
    private final int adaptationSetIndex;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    private final RepresentationHolder[] representationHolders;
    private final TrackSelection trackSelection;

    public static final class Factory implements DashChunkSource.Factory {
        private final DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(DataSource.Factory factory) {
            this(factory, 1);
        }

        public Factory(DataSource.Factory factory, int i) {
            this.dataSourceFactory = factory;
            this.maxSegmentsPerLoad = i;
        }

        public DashChunkSource createDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int i2, TrackSelection trackSelection, long j, boolean z, boolean z2) {
            return new DefaultDashChunkSource(loaderErrorThrower, dashManifest, i, i2, trackSelection, this.dataSourceFactory.createDataSource(), j, this.maxSegmentsPerLoad, z, z2);
        }
    }

    public DefaultDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int i2, TrackSelection trackSelection2, DataSource dataSource2, long j, int i3, boolean z, boolean z2) {
        TrackSelection trackSelection3 = trackSelection2;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.manifest = dashManifest;
        this.adaptationSetIndex = i2;
        this.trackSelection = trackSelection3;
        this.dataSource = dataSource2;
        this.periodIndex = i;
        this.elapsedRealtimeOffsetMs = j;
        this.maxSegmentsPerLoad = i3;
        long periodDurationUs = dashManifest.getPeriodDurationUs(i);
        AdaptationSet adaptationSet = getAdaptationSet();
        List<Representation> list = adaptationSet.representations;
        this.representationHolders = new RepresentationHolder[trackSelection2.length()];
        for (int i4 = 0; i4 < this.representationHolders.length; i4++) {
            this.representationHolders[i4] = new RepresentationHolder(periodDurationUs, list.get(trackSelection3.getIndexInTrackGroup(i4)), z, z2, adaptationSet.type);
        }
    }

    public void updateManifest(DashManifest dashManifest, int i) {
        try {
            this.manifest = dashManifest;
            this.periodIndex = i;
            long periodDurationUs = this.manifest.getPeriodDurationUs(this.periodIndex);
            List<Representation> list = getAdaptationSet().representations;
            for (int i2 = 0; i2 < this.representationHolders.length; i2++) {
                this.representationHolders[i2].updateRepresentation(periodDurationUs, list.get(this.trackSelection.getIndexInTrackGroup(i2)));
            }
        } catch (BehindLiveWindowException e) {
            this.fatalError = e;
        }
    }

    public void maybeThrowError() throws IOException {
        if (this.fatalError != null) {
            throw this.fatalError;
        }
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    public int getPreferredQueueSize(long j, List<? extends MediaChunk> list) {
        if (this.fatalError != null || this.trackSelection.length() < 2) {
            return list.size();
        }
        return this.trackSelection.evaluateQueueSize(j, list);
    }

    public final void getNextChunk(MediaChunk mediaChunk, long j, ChunkHolder chunkHolder) {
        int i;
        int nextChunkIndex;
        MediaChunk mediaChunk2 = mediaChunk;
        long j2 = j;
        ChunkHolder chunkHolder2 = chunkHolder;
        if (this.fatalError == null) {
            this.trackSelection.updateSelectedTrack(mediaChunk2 != null ? mediaChunk2.endTimeUs - j2 : 0);
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.getSelectedIndex()];
            if (representationHolder.extractorWrapper != null) {
                Representation representation = representationHolder.representation;
                RangedUri initializationUri = representationHolder.extractorWrapper.getSampleFormats() == null ? representation.getInitializationUri() : null;
                RangedUri indexUri = representationHolder.segmentIndex == null ? representation.getIndexUri() : null;
                if (!(initializationUri == null && indexUri == null)) {
                    chunkHolder2.chunk = newInitializationChunk(representationHolder, this.dataSource, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), initializationUri, indexUri);
                    return;
                }
            }
            long nowUnixTimeUs = getNowUnixTimeUs();
            int segmentCount = representationHolder.getSegmentCount();
            boolean z = false;
            if (segmentCount == 0) {
                if (!this.manifest.dynamic || this.periodIndex < this.manifest.getPeriodCount() - 1) {
                    z = true;
                }
                chunkHolder2.endOfStream = z;
                return;
            }
            int firstSegmentNum = representationHolder.getFirstSegmentNum();
            if (segmentCount == -1) {
                long j3 = (nowUnixTimeUs - (this.manifest.availabilityStartTime * 1000)) - (this.manifest.getPeriod(this.periodIndex).startMs * 1000);
                if (this.manifest.timeShiftBufferDepth != C.TIME_UNSET) {
                    firstSegmentNum = Math.max(firstSegmentNum, representationHolder.getSegmentNum(j3 - (this.manifest.timeShiftBufferDepth * 1000)));
                }
                i = representationHolder.getSegmentNum(j3) - 1;
            } else {
                i = (segmentCount + firstSegmentNum) - 1;
            }
            if (mediaChunk2 == null) {
                nextChunkIndex = Util.constrainValue(representationHolder.getSegmentNum(j2), firstSegmentNum, i);
            } else {
                nextChunkIndex = mediaChunk.getNextChunkIndex();
                if (nextChunkIndex < firstSegmentNum) {
                    this.fatalError = new BehindLiveWindowException();
                    return;
                }
            }
            int i2 = nextChunkIndex;
            if (i2 > i || (this.missingLastSegment && i2 >= i)) {
                if (!this.manifest.dynamic || this.periodIndex < this.manifest.getPeriodCount() - 1) {
                    z = true;
                }
                chunkHolder2.endOfStream = z;
                return;
            }
            chunkHolder2.chunk = newMediaChunk(representationHolder, this.dataSource, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), i2, Math.min(this.maxSegmentsPerLoad, (i - i2) + 1));
        }
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        SeekMap seekMap;
        if (chunk instanceof InitializationChunk) {
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.indexOf(((InitializationChunk) chunk).trackFormat)];
            if (representationHolder.segmentIndex == null && (seekMap = representationHolder.extractorWrapper.getSeekMap()) != null) {
                representationHolder.segmentIndex = new DashWrappingSegmentIndex((ChunkIndex) seekMap);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        r4 = r2.representationHolders[r2.trackSelection.indexOf(r3.trackFormat)];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onChunkLoadError(com.google.android.exoplayer2.source.chunk.Chunk r3, boolean r4, java.lang.Exception r5) {
        /*
            r2 = this;
            if (r4 != 0) goto L_0x0004
            r3 = 0
            return r3
        L_0x0004:
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r4 = r2.manifest
            boolean r4 = r4.dynamic
            if (r4 != 0) goto L_0x0043
            boolean r4 = r3 instanceof com.google.android.exoplayer2.source.chunk.MediaChunk
            if (r4 == 0) goto L_0x0043
            boolean r4 = r5 instanceof com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException
            if (r4 == 0) goto L_0x0043
            r4 = r5
            com.google.android.exoplayer2.upstream.HttpDataSource$InvalidResponseCodeException r4 = (com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException) r4
            int r4 = r4.responseCode
            r0 = 404(0x194, float:5.66E-43)
            if (r4 != r0) goto L_0x0043
            com.google.android.exoplayer2.source.dash.DefaultDashChunkSource$RepresentationHolder[] r4 = r2.representationHolders
            com.google.android.exoplayer2.trackselection.TrackSelection r0 = r2.trackSelection
            com.google.android.exoplayer2.Format r1 = r3.trackFormat
            int r0 = r0.indexOf((com.google.android.exoplayer2.Format) r1)
            r4 = r4[r0]
            int r0 = r4.getSegmentCount()
            r1 = -1
            if (r0 == r1) goto L_0x0043
            if (r0 == 0) goto L_0x0043
            int r4 = r4.getFirstSegmentNum()
            int r4 = r4 + r0
            r0 = 1
            int r4 = r4 - r0
            r1 = r3
            com.google.android.exoplayer2.source.chunk.MediaChunk r1 = (com.google.android.exoplayer2.source.chunk.MediaChunk) r1
            int r1 = r1.getNextChunkIndex()
            if (r1 <= r4) goto L_0x0043
            r2.missingLastSegment = r0
            return r0
        L_0x0043:
            com.google.android.exoplayer2.trackselection.TrackSelection r4 = r2.trackSelection
            com.google.android.exoplayer2.trackselection.TrackSelection r0 = r2.trackSelection
            com.google.android.exoplayer2.Format r3 = r3.trackFormat
            int r3 = r0.indexOf((com.google.android.exoplayer2.Format) r3)
            boolean r3 = com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil.maybeBlacklistTrack(r4, r3, r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.DefaultDashChunkSource.onChunkLoadError(com.google.android.exoplayer2.source.chunk.Chunk, boolean, java.lang.Exception):boolean");
    }

    private AdaptationSet getAdaptationSet() {
        return this.manifest.getPeriod(this.periodIndex).adaptationSets.get(this.adaptationSetIndex);
    }

    private long getNowUnixTimeUs() {
        if (this.elapsedRealtimeOffsetMs != 0) {
            return (SystemClock.elapsedRealtime() + this.elapsedRealtimeOffsetMs) * 1000;
        }
        return System.currentTimeMillis() * 1000;
    }

    private static Chunk newInitializationChunk(RepresentationHolder representationHolder, DataSource dataSource2, Format format, int i, Object obj, RangedUri rangedUri, RangedUri rangedUri2) {
        String str = representationHolder.representation.baseUrl;
        if (rangedUri != null && (rangedUri2 = rangedUri.attemptMerge(rangedUri2, str)) == null) {
            rangedUri2 = rangedUri;
        }
        return new InitializationChunk(dataSource2, new DataSpec(rangedUri2.resolveUri(str), rangedUri2.start, rangedUri2.length, representationHolder.representation.getCacheKey()), format, i, obj, representationHolder.extractorWrapper);
    }

    private static Chunk newMediaChunk(RepresentationHolder representationHolder, DataSource dataSource2, Format format, int i, Object obj, int i2, int i3) {
        RepresentationHolder representationHolder2 = representationHolder;
        int i4 = i2;
        Representation representation = representationHolder2.representation;
        long segmentStartTimeUs = representationHolder2.getSegmentStartTimeUs(i4);
        RangedUri segmentUrl = representationHolder2.getSegmentUrl(i4);
        String str = representation.baseUrl;
        if (representationHolder2.extractorWrapper == null) {
            long segmentEndTimeUs = representationHolder2.getSegmentEndTimeUs(i4);
            return new SingleSampleMediaChunk(dataSource2, new DataSpec(segmentUrl.resolveUri(str), segmentUrl.start, segmentUrl.length, representation.getCacheKey()), format, i, obj, segmentStartTimeUs, segmentEndTimeUs, i4, representationHolder2.trackType, format);
        }
        RangedUri rangedUri = segmentUrl;
        int i5 = 1;
        int i6 = 1;
        int i7 = i3;
        while (i5 < i7) {
            RangedUri attemptMerge = rangedUri.attemptMerge(representationHolder2.getSegmentUrl(i4 + i5), str);
            if (attemptMerge == null) {
                break;
            }
            i6++;
            i5++;
            rangedUri = attemptMerge;
        }
        return new ContainerMediaChunk(dataSource2, new DataSpec(rangedUri.resolveUri(str), rangedUri.start, rangedUri.length, representation.getCacheKey()), format, i, obj, segmentStartTimeUs, representationHolder2.getSegmentEndTimeUs((i4 + i6) - 1), i4, i6, -representation.presentationTimeOffsetUs, representationHolder2.extractorWrapper);
    }

    protected static final class RepresentationHolder {
        public final ChunkExtractorWrapper extractorWrapper;
        private long periodDurationUs;
        public Representation representation;
        public DashSegmentIndex segmentIndex;
        private int segmentNumShift;
        public final int trackType;

        public RepresentationHolder(long j, Representation representation2, boolean z, boolean z2, int i) {
            Extractor extractor;
            this.periodDurationUs = j;
            this.representation = representation2;
            this.trackType = i;
            String str = representation2.format.containerMimeType;
            if (mimeTypeIsRawText(str)) {
                this.extractorWrapper = null;
            } else {
                if (MimeTypes.APPLICATION_RAWCC.equals(str)) {
                    extractor = new RawCcExtractor(representation2.format);
                } else if (mimeTypeIsWebm(str)) {
                    extractor = new MatroskaExtractor(1);
                } else {
                    int i2 = z ? 4 : 0;
                    extractor = new FragmentedMp4Extractor(z2 ? i2 | 8 : i2);
                }
                this.extractorWrapper = new ChunkExtractorWrapper(extractor, representation2.format);
            }
            this.segmentIndex = representation2.getIndex();
        }

        public void updateRepresentation(long j, Representation representation2) throws BehindLiveWindowException {
            int segmentCount;
            DashSegmentIndex index = this.representation.getIndex();
            DashSegmentIndex index2 = representation2.getIndex();
            this.periodDurationUs = j;
            this.representation = representation2;
            if (index != null) {
                this.segmentIndex = index2;
                if (index.isExplicit() && (segmentCount = index.getSegmentCount(this.periodDurationUs)) != 0) {
                    int firstSegmentNum = (index.getFirstSegmentNum() + segmentCount) - 1;
                    long timeUs = index.getTimeUs(firstSegmentNum) + index.getDurationUs(firstSegmentNum, this.periodDurationUs);
                    int firstSegmentNum2 = index2.getFirstSegmentNum();
                    long timeUs2 = index2.getTimeUs(firstSegmentNum2);
                    if (timeUs == timeUs2) {
                        this.segmentNumShift += (firstSegmentNum + 1) - firstSegmentNum2;
                    } else if (timeUs < timeUs2) {
                        throw new BehindLiveWindowException();
                    } else {
                        this.segmentNumShift += index.getSegmentNum(timeUs2, this.periodDurationUs) - firstSegmentNum2;
                    }
                }
            }
        }

        public int getFirstSegmentNum() {
            return this.segmentIndex.getFirstSegmentNum() + this.segmentNumShift;
        }

        public int getSegmentCount() {
            return this.segmentIndex.getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentStartTimeUs(int i) {
            return this.segmentIndex.getTimeUs(i - this.segmentNumShift);
        }

        public long getSegmentEndTimeUs(int i) {
            return getSegmentStartTimeUs(i) + this.segmentIndex.getDurationUs(i - this.segmentNumShift, this.periodDurationUs);
        }

        public int getSegmentNum(long j) {
            return this.segmentIndex.getSegmentNum(j, this.periodDurationUs) + this.segmentNumShift;
        }

        public RangedUri getSegmentUrl(int i) {
            return this.segmentIndex.getSegmentUrl(i - this.segmentNumShift);
        }

        private static boolean mimeTypeIsWebm(String str) {
            return str.startsWith(MimeTypes.VIDEO_WEBM) || str.startsWith(MimeTypes.AUDIO_WEBM) || str.startsWith(MimeTypes.APPLICATION_WEBM);
        }

        private static boolean mimeTypeIsRawText(String str) {
            return MimeTypes.isText(str) || MimeTypes.APPLICATION_TTML.equals(str);
        }
    }
}
