package com.google.android.exoplayer2.source.chunk;

import android.util.Log;
import com.google.android.exoplayer2.extractor.DefaultTrackOutput;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;

final class BaseMediaChunkOutput implements ChunkExtractorWrapper.TrackOutputProvider {
    private static final String TAG = "BaseMediaChunkOutput";
    private final DefaultTrackOutput[] trackOutputs;
    private final int[] trackTypes;

    public BaseMediaChunkOutput(int[] iArr, DefaultTrackOutput[] defaultTrackOutputArr) {
        this.trackTypes = iArr;
        this.trackOutputs = defaultTrackOutputArr;
    }

    public TrackOutput track(int i, int i2) {
        for (int i3 = 0; i3 < this.trackTypes.length; i3++) {
            if (i2 == this.trackTypes[i3]) {
                return this.trackOutputs[i3];
            }
        }
        Log.e(TAG, "Unmatched track of type: " + i2);
        return new DummyTrackOutput();
    }

    public int[] getWriteIndices() {
        int[] iArr = new int[this.trackOutputs.length];
        for (int i = 0; i < this.trackOutputs.length; i++) {
            if (this.trackOutputs[i] != null) {
                iArr[i] = this.trackOutputs[i].getWriteIndex();
            }
        }
        return iArr;
    }

    public void setSampleOffsetUs(long j) {
        for (DefaultTrackOutput defaultTrackOutput : this.trackOutputs) {
            if (defaultTrackOutput != null) {
                defaultTrackOutput.setSampleOffsetUs(j);
            }
        }
    }
}
