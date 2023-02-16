package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

public class OggExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() {
        public Extractor[] createExtractors() {
            return new Extractor[]{new OggExtractor()};
        }
    };
    private static final int MAX_VERIFICATION_BYTES = 8;
    private StreamReader streamReader;

    public void release() {
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        try {
            OggPageHeader oggPageHeader = new OggPageHeader();
            if (oggPageHeader.populate(extractorInput, true)) {
                if ((oggPageHeader.type & 2) == 2) {
                    int min = Math.min(oggPageHeader.bodySize, 8);
                    ParsableByteArray parsableByteArray = new ParsableByteArray(min);
                    extractorInput.peekFully(parsableByteArray.data, 0, min);
                    if (FlacReader.verifyBitstreamType(resetPosition(parsableByteArray))) {
                        this.streamReader = new FlacReader();
                    } else if (VorbisReader.verifyBitstreamType(resetPosition(parsableByteArray))) {
                        this.streamReader = new VorbisReader();
                    } else if (!OpusReader.verifyBitstreamType(resetPosition(parsableByteArray))) {
                        return false;
                    } else {
                        this.streamReader = new OpusReader();
                    }
                    return true;
                }
            }
            return false;
        } catch (ParserException unused) {
            return false;
        }
    }

    public void init(ExtractorOutput extractorOutput) {
        TrackOutput track = extractorOutput.track(0, 1);
        extractorOutput.endTracks();
        this.streamReader.init(extractorOutput, track);
    }

    public void seek(long j, long j2) {
        this.streamReader.seek(j, j2);
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        return this.streamReader.read(extractorInput, positionHolder);
    }

    /* access modifiers changed from: package-private */
    public StreamReader getStreamReader() {
        return this.streamReader;
    }

    private static ParsableByteArray resetPosition(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(0);
        return parsableByteArray;
    }
}
