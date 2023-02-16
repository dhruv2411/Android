package com.google.android.exoplayer2.source.dash;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.CompositeSequenceableLoader;
import com.google.android.exoplayer2.source.EmptySampleStream;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.source.dash.manifest.SchemeValuePair;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

final class DashMediaPeriod implements MediaPeriod, SequenceableLoader.Callback<ChunkSampleStream<DashChunkSource>> {
    private List<AdaptationSet> adaptationSets;
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final DashChunkSource.Factory chunkSourceFactory;
    private final long elapsedRealtimeOffset;
    private final EmbeddedTrackInfo[] embeddedTrackInfos;
    private final AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher;
    final int id;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int minLoadableRetryCount;
    private int periodIndex;
    private ChunkSampleStream<DashChunkSource>[] sampleStreams = newSampleStreamArray(0);
    private CompositeSequenceableLoader sequenceableLoader = new CompositeSequenceableLoader(this.sampleStreams);
    private final TrackGroupArray trackGroups;

    public long readDiscontinuity() {
        return C.TIME_UNSET;
    }

    public DashMediaPeriod(int i, DashManifest dashManifest, int i2, DashChunkSource.Factory factory, int i3, AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher2, long j, LoaderErrorThrower loaderErrorThrower, Allocator allocator2) {
        this.id = i;
        this.manifest = dashManifest;
        this.periodIndex = i2;
        this.chunkSourceFactory = factory;
        this.minLoadableRetryCount = i3;
        this.eventDispatcher = eventDispatcher2;
        this.elapsedRealtimeOffset = j;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.allocator = allocator2;
        this.adaptationSets = dashManifest.getPeriod(i2).adaptationSets;
        Pair<TrackGroupArray, EmbeddedTrackInfo[]> buildTrackGroups = buildTrackGroups(this.adaptationSets);
        this.trackGroups = (TrackGroupArray) buildTrackGroups.first;
        this.embeddedTrackInfos = (EmbeddedTrackInfo[]) buildTrackGroups.second;
    }

    public void updateManifest(DashManifest dashManifest, int i) {
        this.manifest = dashManifest;
        this.periodIndex = i;
        this.adaptationSets = dashManifest.getPeriod(i).adaptationSets;
        if (this.sampleStreams != null) {
            for (ChunkSampleStream<DashChunkSource> chunkSource : this.sampleStreams) {
                chunkSource.getChunkSource().updateManifest(dashManifest, i);
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    public void release() {
        for (ChunkSampleStream<DashChunkSource> release : this.sampleStreams) {
            release.release();
        }
    }

    public void prepare(MediaPeriod.Callback callback2) {
        this.callback = callback2;
        callback2.onPrepared(this);
    }

    public void maybeThrowPrepareError() throws IOException {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        int indexOf;
        SampleStream sampleStream;
        int indexOf2;
        TrackSelection[] trackSelectionArr2 = trackSelectionArr;
        long j2 = j;
        int size = this.adaptationSets.size();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < trackSelectionArr2.length; i++) {
            if (sampleStreamArr[i] instanceof ChunkSampleStream) {
                ChunkSampleStream chunkSampleStream = sampleStreamArr[i];
                if (trackSelectionArr2[i] == null || !zArr[i]) {
                    chunkSampleStream.release();
                    sampleStreamArr[i] = null;
                } else {
                    hashMap.put(Integer.valueOf(this.trackGroups.indexOf(trackSelectionArr2[i].getTrackGroup())), chunkSampleStream);
                }
            }
            if (sampleStreamArr[i] == null && trackSelectionArr2[i] != null && (indexOf2 = this.trackGroups.indexOf(trackSelectionArr2[i].getTrackGroup())) < size) {
                ChunkSampleStream<DashChunkSource> buildSampleStream = buildSampleStream(indexOf2, trackSelectionArr2[i], j2);
                hashMap.put(Integer.valueOf(indexOf2), buildSampleStream);
                sampleStreamArr[i] = buildSampleStream;
                zArr2[i] = true;
            }
        }
        for (int i2 = 0; i2 < trackSelectionArr2.length; i2++) {
            if (((sampleStreamArr[i2] instanceof ChunkSampleStream.EmbeddedSampleStream) || (sampleStreamArr[i2] instanceof EmptySampleStream)) && (trackSelectionArr2[i2] == null || !zArr[i2])) {
                releaseIfEmbeddedSampleStream(sampleStreamArr[i2]);
                sampleStreamArr[i2] = null;
            }
            if (trackSelectionArr2[i2] != null && (indexOf = this.trackGroups.indexOf(trackSelectionArr2[i2].getTrackGroup())) >= size) {
                EmbeddedTrackInfo embeddedTrackInfo = this.embeddedTrackInfos[indexOf - size];
                ChunkSampleStream<T> chunkSampleStream2 = (ChunkSampleStream) hashMap.get(Integer.valueOf(embeddedTrackInfo.adaptationSetIndex));
                ChunkSampleStream.EmbeddedSampleStream embeddedSampleStream = sampleStreamArr[i2];
                if (!(chunkSampleStream2 == null ? embeddedSampleStream instanceof EmptySampleStream : (embeddedSampleStream instanceof ChunkSampleStream.EmbeddedSampleStream) && embeddedSampleStream.parent == chunkSampleStream2)) {
                    releaseIfEmbeddedSampleStream(embeddedSampleStream);
                    if (chunkSampleStream2 == null) {
                        sampleStream = new EmptySampleStream();
                    } else {
                        sampleStream = chunkSampleStream2.selectEmbeddedTrack(j2, embeddedTrackInfo.trackType);
                    }
                    sampleStreamArr[i2] = sampleStream;
                    zArr2[i2] = true;
                }
            }
        }
        this.sampleStreams = newSampleStreamArray(hashMap.size());
        hashMap.values().toArray(this.sampleStreams);
        this.sequenceableLoader = new CompositeSequenceableLoader(this.sampleStreams);
        return j2;
    }

    public void discardBuffer(long j) {
        for (ChunkSampleStream<DashChunkSource> discardUnselectedEmbeddedTracksTo : this.sampleStreams) {
            discardUnselectedEmbeddedTracksTo.discardUnselectedEmbeddedTracksTo(j);
        }
    }

    public boolean continueLoading(long j) {
        return this.sequenceableLoader.continueLoading(j);
    }

    public long getNextLoadPositionUs() {
        return this.sequenceableLoader.getNextLoadPositionUs();
    }

    public long getBufferedPositionUs() {
        long j = Long.MAX_VALUE;
        for (ChunkSampleStream<DashChunkSource> bufferedPositionUs : this.sampleStreams) {
            long bufferedPositionUs2 = bufferedPositionUs.getBufferedPositionUs();
            if (bufferedPositionUs2 != Long.MIN_VALUE) {
                j = Math.min(j, bufferedPositionUs2);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public long seekToUs(long j) {
        for (ChunkSampleStream<DashChunkSource> seekToUs : this.sampleStreams) {
            seekToUs.seekToUs(j);
        }
        return j;
    }

    public void onContinueLoadingRequested(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        this.callback.onContinueLoadingRequested(this);
    }

    private static Pair<TrackGroupArray, EmbeddedTrackInfo[]> buildTrackGroups(List<AdaptationSet> list) {
        int size = list.size();
        int embeddedTrackCount = getEmbeddedTrackCount(list);
        TrackGroup[] trackGroupArr = new TrackGroup[(size + embeddedTrackCount)];
        EmbeddedTrackInfo[] embeddedTrackInfoArr = new EmbeddedTrackInfo[embeddedTrackCount];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            AdaptationSet adaptationSet = list.get(i2);
            List<Representation> list2 = adaptationSet.representations;
            Format[] formatArr = new Format[list2.size()];
            for (int i3 = 0; i3 < formatArr.length; i3++) {
                formatArr[i3] = list2.get(i3).format;
            }
            trackGroupArr[i2] = new TrackGroup(formatArr);
            if (hasEventMessageTrack(adaptationSet)) {
                trackGroupArr[size + i] = new TrackGroup(Format.createSampleFormat(adaptationSet.id + ":emsg", MimeTypes.APPLICATION_EMSG, (String) null, -1, (DrmInitData) null));
                embeddedTrackInfoArr[i] = new EmbeddedTrackInfo(i2, 4);
                i++;
            }
            if (hasCea608Track(adaptationSet)) {
                trackGroupArr[size + i] = new TrackGroup(Format.createTextSampleFormat(adaptationSet.id + ":cea608", MimeTypes.APPLICATION_CEA608, (String) null, -1, 0, (String) null, (DrmInitData) null));
                embeddedTrackInfoArr[i] = new EmbeddedTrackInfo(i2, 3);
                i++;
            }
        }
        return Pair.create(new TrackGroupArray(trackGroupArr), embeddedTrackInfoArr);
    }

    private ChunkSampleStream<DashChunkSource> buildSampleStream(int i, TrackSelection trackSelection, long j) {
        int i2 = i;
        AdaptationSet adaptationSet = this.adaptationSets.get(i2);
        int[] iArr = new int[2];
        boolean hasEventMessageTrack = hasEventMessageTrack(adaptationSet);
        int i3 = 0;
        if (hasEventMessageTrack) {
            iArr[0] = 4;
            i3 = 1;
        }
        boolean hasCea608Track = hasCea608Track(adaptationSet);
        if (hasCea608Track) {
            iArr[i3] = 3;
            i3++;
        }
        if (i3 < iArr.length) {
            iArr = Arrays.copyOf(iArr, i3);
        }
        return new ChunkSampleStream(adaptationSet.type, iArr, this.chunkSourceFactory.createDashChunkSource(this.manifestLoaderErrorThrower, this.manifest, this.periodIndex, i2, trackSelection, this.elapsedRealtimeOffset, hasEventMessageTrack, hasCea608Track), this, this.allocator, j, this.minLoadableRetryCount, this.eventDispatcher);
    }

    private static int getEmbeddedTrackCount(List<AdaptationSet> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            AdaptationSet adaptationSet = list.get(i2);
            if (hasEventMessageTrack(adaptationSet)) {
                i++;
            }
            if (hasCea608Track(adaptationSet)) {
                i++;
            }
        }
        return i;
    }

    private static boolean hasEventMessageTrack(AdaptationSet adaptationSet) {
        List<Representation> list = adaptationSet.representations;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).inbandEventStreams.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCea608Track(AdaptationSet adaptationSet) {
        List<SchemeValuePair> list = adaptationSet.accessibilityDescriptors;
        for (int i = 0; i < list.size(); i++) {
            if ("urn:scte:dash:cc:cea-608:2015".equals(list.get(i).schemeIdUri)) {
                return true;
            }
        }
        return false;
    }

    private static ChunkSampleStream<DashChunkSource>[] newSampleStreamArray(int i) {
        return new ChunkSampleStream[i];
    }

    private static void releaseIfEmbeddedSampleStream(SampleStream sampleStream) {
        if (sampleStream instanceof ChunkSampleStream.EmbeddedSampleStream) {
            ((ChunkSampleStream.EmbeddedSampleStream) sampleStream).release();
        }
    }

    private static final class EmbeddedTrackInfo {
        public final int adaptationSetIndex;
        public final int trackType;

        public EmbeddedTrackInfo(int i, int i2) {
            this.adaptationSetIndex = i;
            this.trackType = i2;
        }
    }
}
