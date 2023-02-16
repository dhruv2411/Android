package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.NavigableSet;

public final class CacheUtil {

    public static class CachingCounters {
        public long alreadyCachedBytes;
        public long downloadedBytes;
    }

    public static String generateKey(Uri uri) {
        return uri.toString();
    }

    public static String getKey(DataSpec dataSpec) {
        return dataSpec.key != null ? dataSpec.key : generateKey(dataSpec.uri);
    }

    public static CachingCounters getCached(DataSpec dataSpec, Cache cache, CachingCounters cachingCounters) {
        try {
            return internalCache(dataSpec, cache, (CacheDataSource) null, (byte[]) null, (PriorityTaskManager) null, 0, cachingCounters);
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public static CachingCounters cache(DataSpec dataSpec, Cache cache, CacheDataSource cacheDataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, CachingCounters cachingCounters) throws IOException, InterruptedException {
        Assertions.checkNotNull(cacheDataSource);
        Assertions.checkNotNull(bArr);
        return internalCache(dataSpec, cache, cacheDataSource, bArr, priorityTaskManager, i, cachingCounters);
    }

    private static CachingCounters internalCache(DataSpec dataSpec, Cache cache, CacheDataSource cacheDataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, CachingCounters cachingCounters) throws IOException, InterruptedException {
        Cache cache2;
        long j;
        long j2;
        DataSpec dataSpec2 = dataSpec;
        CacheDataSource cacheDataSource2 = cacheDataSource;
        byte[] bArr2 = bArr;
        CachingCounters cachingCounters2 = cachingCounters;
        long j3 = dataSpec2.position;
        long j4 = dataSpec2.length;
        String key = getKey(dataSpec);
        if (j4 == -1) {
            cache2 = cache;
            j4 = cache2.getContentLength(key);
            if (j4 == -1) {
                j4 = Long.MAX_VALUE;
            }
        } else {
            cache2 = cache;
        }
        long j5 = 0;
        if (cachingCounters2 == null) {
            cachingCounters2 = new CachingCounters();
        } else {
            cachingCounters2.alreadyCachedBytes = 0;
            cachingCounters2.downloadedBytes = 0;
        }
        while (true) {
            if (j4 <= j5) {
                break;
            }
            long j6 = j5;
            long cachedBytes = cache2.getCachedBytes(key, j3, j4);
            if (cachedBytes > j6) {
                cachingCounters2.alreadyCachedBytes += cachedBytes;
                j = cachedBytes;
                j2 = -1;
                PriorityTaskManager priorityTaskManager2 = priorityTaskManager;
                int i2 = i;
            } else {
                long j7 = -cachedBytes;
                if (cacheDataSource2 == null || bArr2 == null) {
                    PriorityTaskManager priorityTaskManager3 = priorityTaskManager;
                    int i3 = i;
                    j = j7;
                    if (j == Long.MAX_VALUE) {
                        cachingCounters2.downloadedBytes = -1;
                        break;
                    }
                    j2 = -1;
                    cachingCounters2.downloadedBytes += j;
                } else {
                    Uri uri = dataSpec2.uri;
                    DataSpec dataSpec3 = r8;
                    j = j7;
                    DataSpec dataSpec4 = new DataSpec(uri, j3, j7 == Long.MAX_VALUE ? -1 : j7, key);
                    long readAndDiscard = readAndDiscard(dataSpec3, cacheDataSource2, bArr2, priorityTaskManager, i);
                    cachingCounters2.downloadedBytes += readAndDiscard;
                    if (readAndDiscard < j) {
                        break;
                    }
                    j2 = -1;
                }
            }
            long j8 = j3 + j;
            if (j4 != Long.MAX_VALUE) {
                j4 -= j;
            }
            cache2 = cache;
            long j9 = j2;
            j3 = j8;
            j5 = j6;
            dataSpec2 = dataSpec;
            cacheDataSource2 = cacheDataSource;
        }
        return cachingCounters2;
    }

    private static long readAndDiscard(DataSpec dataSpec, DataSource dataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i) throws IOException, InterruptedException {
        long j;
        while (true) {
            if (priorityTaskManager != null) {
                priorityTaskManager.proceed(i);
            }
            try {
                dataSource.open(dataSpec);
                j = 0;
                break;
            } catch (PriorityTaskManager.PriorityTooLowException unused) {
            } finally {
                Util.closeQuietly(dataSource);
            }
        }
        while (!Thread.interrupted()) {
            int read = dataSource.read(bArr, 0, bArr.length);
            if (read == -1) {
                return j;
            }
            j += (long) read;
        }
        throw new InterruptedException();
    }

    public static void remove(Cache cache, String str) {
        NavigableSet<CacheSpan> cachedSpans = cache.getCachedSpans(str);
        if (cachedSpans != null) {
            for (CacheSpan removeSpan : cachedSpans) {
                try {
                    cache.removeSpan(removeSpan);
                } catch (Cache.CacheException unused) {
                }
            }
        }
    }

    private CacheUtil() {
    }
}
