package com.itextpdf.text.io;

import java.io.IOException;

class GroupedRandomAccessSource implements RandomAccessSource {
    private SourceEntry currentSourceEntry;
    private final long size;
    private final SourceEntry[] sources;

    /* access modifiers changed from: protected */
    public void sourceInUse(RandomAccessSource randomAccessSource) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void sourceReleased(RandomAccessSource randomAccessSource) throws IOException {
    }

    public GroupedRandomAccessSource(RandomAccessSource[] randomAccessSourceArr) throws IOException {
        long j = 0;
        int i = 0;
        this.sources = new SourceEntry[randomAccessSourceArr.length];
        while (i < randomAccessSourceArr.length) {
            this.sources[i] = new SourceEntry(i, randomAccessSourceArr[i], j);
            i++;
            j += randomAccessSourceArr[i].length();
        }
        this.size = j;
        this.currentSourceEntry = this.sources[randomAccessSourceArr.length - 1];
        sourceInUse(this.currentSourceEntry.source);
    }

    /* access modifiers changed from: protected */
    public int getStartingSourceIndex(long j) {
        if (j >= this.currentSourceEntry.firstByte) {
            return this.currentSourceEntry.index;
        }
        return 0;
    }

    private SourceEntry getSourceEntryForOffset(long j) throws IOException {
        if (j >= this.size) {
            return null;
        }
        if (j >= this.currentSourceEntry.firstByte && j <= this.currentSourceEntry.lastByte) {
            return this.currentSourceEntry;
        }
        sourceReleased(this.currentSourceEntry.source);
        int startingSourceIndex = getStartingSourceIndex(j);
        while (startingSourceIndex < this.sources.length) {
            if (j < this.sources[startingSourceIndex].firstByte || j > this.sources[startingSourceIndex].lastByte) {
                startingSourceIndex++;
            } else {
                this.currentSourceEntry = this.sources[startingSourceIndex];
                sourceInUse(this.currentSourceEntry.source);
                return this.currentSourceEntry;
            }
        }
        return null;
    }

    public int get(long j) throws IOException {
        SourceEntry sourceEntryForOffset = getSourceEntryForOffset(j);
        if (sourceEntryForOffset == null) {
            return -1;
        }
        return sourceEntryForOffset.source.get(sourceEntryForOffset.offsetN(j));
    }

    public int get(long j, byte[] bArr, int i, int i2) throws IOException {
        int i3 = i2;
        SourceEntry sourceEntryForOffset = getSourceEntryForOffset(j);
        if (sourceEntryForOffset == null) {
            return -1;
        }
        long j2 = j;
        long offsetN = sourceEntryForOffset.offsetN(j2);
        long j3 = j2;
        int i4 = i;
        SourceEntry sourceEntry = sourceEntryForOffset;
        int i5 = i3;
        while (i5 > 0 && sourceEntry != null && offsetN <= sourceEntry.source.length()) {
            int i6 = sourceEntry.source.get(offsetN, bArr, i4, i5);
            if (i6 == -1) {
                break;
            }
            i4 += i6;
            long j4 = j3 + ((long) i6);
            i5 -= i6;
            sourceEntry = getSourceEntryForOffset(j4);
            j3 = j4;
            offsetN = 0;
        }
        if (i5 == i3) {
            return -1;
        }
        return i3 - i5;
    }

    public long length() {
        return this.size;
    }

    public void close() throws IOException {
        for (SourceEntry sourceEntry : this.sources) {
            sourceEntry.source.close();
        }
    }

    private static class SourceEntry {
        final long firstByte;
        final int index;
        final long lastByte;
        final RandomAccessSource source;

        public SourceEntry(int i, RandomAccessSource randomAccessSource, long j) {
            this.index = i;
            this.source = randomAccessSource;
            this.firstByte = j;
            this.lastByte = (j + randomAccessSource.length()) - 1;
        }

        public long offsetN(long j) {
            return j - this.firstByte;
        }
    }
}
