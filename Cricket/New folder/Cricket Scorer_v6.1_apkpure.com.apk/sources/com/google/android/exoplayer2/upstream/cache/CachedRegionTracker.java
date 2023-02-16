package com.google.android.exoplayer2.upstream.cache;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.upstream.cache.Cache;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public final class CachedRegionTracker implements Cache.Listener {
    public static final int CACHED_TO_END = -2;
    public static final int NOT_CACHED = -1;
    private static final String TAG = "CachedRegionTracker";
    private final Cache cache;
    private final String cacheKey;
    private final ChunkIndex chunkIndex;
    private final Region lookupRegion = new Region(0, 0);
    private final TreeSet<Region> regions = new TreeSet<>();

    public void onSpanTouched(Cache cache2, CacheSpan cacheSpan, CacheSpan cacheSpan2) {
    }

    public CachedRegionTracker(Cache cache2, String str, ChunkIndex chunkIndex2) {
        this.cache = cache2;
        this.cacheKey = str;
        this.chunkIndex = chunkIndex2;
        synchronized (this) {
            NavigableSet<CacheSpan> addListener = cache2.addListener(str, this);
            if (addListener != null) {
                Iterator<CacheSpan> descendingIterator = addListener.descendingIterator();
                while (descendingIterator.hasNext()) {
                    mergeSpan(descendingIterator.next());
                }
            }
        }
    }

    public void release() {
        this.cache.removeListener(this.cacheKey, this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0067, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getRegionEndTimeMs(long r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region r0 = r9.lookupRegion     // Catch:{ all -> 0x0068 }
            r0.startOffset = r10     // Catch:{ all -> 0x0068 }
            java.util.TreeSet<com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region> r0 = r9.regions     // Catch:{ all -> 0x0068 }
            com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region r1 = r9.lookupRegion     // Catch:{ all -> 0x0068 }
            java.lang.Object r0 = r0.floor(r1)     // Catch:{ all -> 0x0068 }
            com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region r0 = (com.google.android.exoplayer2.upstream.cache.CachedRegionTracker.Region) r0     // Catch:{ all -> 0x0068 }
            r1 = -1
            if (r0 == 0) goto L_0x0066
            long r2 = r0.endOffset     // Catch:{ all -> 0x0068 }
            int r4 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x0066
            int r10 = r0.endOffsetIndex     // Catch:{ all -> 0x0068 }
            if (r10 != r1) goto L_0x001d
            goto L_0x0066
        L_0x001d:
            int r10 = r0.endOffsetIndex     // Catch:{ all -> 0x0068 }
            com.google.android.exoplayer2.extractor.ChunkIndex r11 = r9.chunkIndex     // Catch:{ all -> 0x0068 }
            int r11 = r11.length     // Catch:{ all -> 0x0068 }
            int r11 = r11 + -1
            if (r10 != r11) goto L_0x003f
            long r1 = r0.endOffset     // Catch:{ all -> 0x0068 }
            com.google.android.exoplayer2.extractor.ChunkIndex r11 = r9.chunkIndex     // Catch:{ all -> 0x0068 }
            long[] r11 = r11.offsets     // Catch:{ all -> 0x0068 }
            r3 = r11[r10]     // Catch:{ all -> 0x0068 }
            com.google.android.exoplayer2.extractor.ChunkIndex r11 = r9.chunkIndex     // Catch:{ all -> 0x0068 }
            int[] r11 = r11.sizes     // Catch:{ all -> 0x0068 }
            r11 = r11[r10]     // Catch:{ all -> 0x0068 }
            long r5 = (long) r11
            long r7 = r3 + r5
            int r11 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r11 != 0) goto L_0x003f
            r10 = -2
            monitor-exit(r9)
            return r10
        L_0x003f:
            com.google.android.exoplayer2.extractor.ChunkIndex r11 = r9.chunkIndex     // Catch:{ all -> 0x0068 }
            long[] r11 = r11.durationsUs     // Catch:{ all -> 0x0068 }
            r1 = r11[r10]     // Catch:{ all -> 0x0068 }
            long r3 = r0.endOffset     // Catch:{ all -> 0x0068 }
            com.google.android.exoplayer2.extractor.ChunkIndex r11 = r9.chunkIndex     // Catch:{ all -> 0x0068 }
            long[] r11 = r11.offsets     // Catch:{ all -> 0x0068 }
            r5 = r11[r10]     // Catch:{ all -> 0x0068 }
            long r7 = r3 - r5
            long r1 = r1 * r7
            com.google.android.exoplayer2.extractor.ChunkIndex r11 = r9.chunkIndex     // Catch:{ all -> 0x0068 }
            int[] r11 = r11.sizes     // Catch:{ all -> 0x0068 }
            r11 = r11[r10]     // Catch:{ all -> 0x0068 }
            long r3 = (long) r11     // Catch:{ all -> 0x0068 }
            long r1 = r1 / r3
            com.google.android.exoplayer2.extractor.ChunkIndex r11 = r9.chunkIndex     // Catch:{ all -> 0x0068 }
            long[] r11 = r11.timesUs     // Catch:{ all -> 0x0068 }
            r10 = r11[r10]     // Catch:{ all -> 0x0068 }
            long r3 = r10 + r1
            r10 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 / r10
            int r10 = (int) r3
            monitor-exit(r9)
            return r10
        L_0x0066:
            monitor-exit(r9)
            return r1
        L_0x0068:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CachedRegionTracker.getRegionEndTimeMs(long):int");
    }

    public synchronized void onSpanAdded(Cache cache2, CacheSpan cacheSpan) {
        mergeSpan(cacheSpan);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onSpanRemoved(com.google.android.exoplayer2.upstream.cache.Cache r9, com.google.android.exoplayer2.upstream.cache.CacheSpan r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region r9 = new com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region     // Catch:{ all -> 0x006d }
            long r0 = r10.position     // Catch:{ all -> 0x006d }
            long r2 = r10.position     // Catch:{ all -> 0x006d }
            long r4 = r10.length     // Catch:{ all -> 0x006d }
            long r6 = r2 + r4
            r9.<init>(r0, r6)     // Catch:{ all -> 0x006d }
            java.util.TreeSet<com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region> r10 = r8.regions     // Catch:{ all -> 0x006d }
            java.lang.Object r10 = r10.floor(r9)     // Catch:{ all -> 0x006d }
            com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region r10 = (com.google.android.exoplayer2.upstream.cache.CachedRegionTracker.Region) r10     // Catch:{ all -> 0x006d }
            if (r10 != 0) goto L_0x0021
            java.lang.String r9 = "CachedRegionTracker"
            java.lang.String r10 = "Removed a span we were not aware of"
            android.util.Log.e(r9, r10)     // Catch:{ all -> 0x006d }
            monitor-exit(r8)
            return
        L_0x0021:
            java.util.TreeSet<com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region> r0 = r8.regions     // Catch:{ all -> 0x006d }
            r0.remove(r10)     // Catch:{ all -> 0x006d }
            long r0 = r10.startOffset     // Catch:{ all -> 0x006d }
            long r2 = r9.startOffset     // Catch:{ all -> 0x006d }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x004d
            com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region r0 = new com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region     // Catch:{ all -> 0x006d }
            long r1 = r10.startOffset     // Catch:{ all -> 0x006d }
            long r3 = r9.startOffset     // Catch:{ all -> 0x006d }
            r0.<init>(r1, r3)     // Catch:{ all -> 0x006d }
            com.google.android.exoplayer2.extractor.ChunkIndex r1 = r8.chunkIndex     // Catch:{ all -> 0x006d }
            long[] r1 = r1.offsets     // Catch:{ all -> 0x006d }
            long r2 = r0.endOffset     // Catch:{ all -> 0x006d }
            int r1 = java.util.Arrays.binarySearch(r1, r2)     // Catch:{ all -> 0x006d }
            if (r1 >= 0) goto L_0x0046
            int r1 = -r1
            int r1 = r1 + -2
        L_0x0046:
            r0.endOffsetIndex = r1     // Catch:{ all -> 0x006d }
            java.util.TreeSet<com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region> r1 = r8.regions     // Catch:{ all -> 0x006d }
            r1.add(r0)     // Catch:{ all -> 0x006d }
        L_0x004d:
            long r0 = r10.endOffset     // Catch:{ all -> 0x006d }
            long r2 = r9.endOffset     // Catch:{ all -> 0x006d }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x006b
            com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region r0 = new com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region     // Catch:{ all -> 0x006d }
            long r1 = r9.endOffset     // Catch:{ all -> 0x006d }
            r3 = 1
            long r5 = r1 + r3
            long r1 = r10.endOffset     // Catch:{ all -> 0x006d }
            r0.<init>(r5, r1)     // Catch:{ all -> 0x006d }
            int r9 = r10.endOffsetIndex     // Catch:{ all -> 0x006d }
            r0.endOffsetIndex = r9     // Catch:{ all -> 0x006d }
            java.util.TreeSet<com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region> r9 = r8.regions     // Catch:{ all -> 0x006d }
            r9.add(r0)     // Catch:{ all -> 0x006d }
        L_0x006b:
            monitor-exit(r8)
            return
        L_0x006d:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CachedRegionTracker.onSpanRemoved(com.google.android.exoplayer2.upstream.cache.Cache, com.google.android.exoplayer2.upstream.cache.CacheSpan):void");
    }

    private void mergeSpan(CacheSpan cacheSpan) {
        Region region = new Region(cacheSpan.position, cacheSpan.position + cacheSpan.length);
        Region floor = this.regions.floor(region);
        Region ceiling = this.regions.ceiling(region);
        boolean regionsConnect = regionsConnect(floor, region);
        if (regionsConnect(region, ceiling)) {
            if (regionsConnect) {
                floor.endOffset = ceiling.endOffset;
                floor.endOffsetIndex = ceiling.endOffsetIndex;
            } else {
                region.endOffset = ceiling.endOffset;
                region.endOffsetIndex = ceiling.endOffsetIndex;
                this.regions.add(region);
            }
            this.regions.remove(ceiling);
        } else if (regionsConnect) {
            floor.endOffset = region.endOffset;
            int i = floor.endOffsetIndex;
            while (i < this.chunkIndex.length - 1) {
                int i2 = i + 1;
                if (this.chunkIndex.offsets[i2] > floor.endOffset) {
                    break;
                }
                i = i2;
            }
            floor.endOffsetIndex = i;
        } else {
            int binarySearch = Arrays.binarySearch(this.chunkIndex.offsets, region.endOffset);
            if (binarySearch < 0) {
                binarySearch = (-binarySearch) - 2;
            }
            region.endOffsetIndex = binarySearch;
            this.regions.add(region);
        }
    }

    private boolean regionsConnect(Region region, Region region2) {
        return (region == null || region2 == null || region.endOffset != region2.startOffset) ? false : true;
    }

    private static class Region implements Comparable<Region> {
        public long endOffset;
        public int endOffsetIndex;
        public long startOffset;

        public Region(long j, long j2) {
            this.startOffset = j;
            this.endOffset = j2;
        }

        public int compareTo(@NonNull Region region) {
            if (this.startOffset < region.startOffset) {
                return -1;
            }
            return this.startOffset == region.startOffset ? 0 : 1;
        }
    }
}
