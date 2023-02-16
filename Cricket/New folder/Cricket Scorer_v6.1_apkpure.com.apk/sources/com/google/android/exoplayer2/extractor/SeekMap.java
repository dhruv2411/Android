package com.google.android.exoplayer2.extractor;

public interface SeekMap {
    long getDurationUs();

    long getPosition(long j);

    boolean isSeekable();

    public static final class Unseekable implements SeekMap {
        private final long durationUs;

        public long getPosition(long j) {
            return 0;
        }

        public boolean isSeekable() {
            return false;
        }

        public Unseekable(long j) {
            this.durationUs = j;
        }

        public long getDurationUs() {
            return this.durationUs;
        }
    }
}
