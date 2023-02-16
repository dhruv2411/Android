package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class DataSpec {
    public static final int FLAG_ALLOW_CACHING_UNKNOWN_LENGTH = 2;
    public static final int FLAG_ALLOW_GZIP = 1;
    public final long absoluteStreamPosition;
    public final int flags;
    public final String key;
    public final long length;
    public final long position;
    public final byte[] postBody;
    public final Uri uri;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DataSpec(Uri uri2) {
        this(uri2, 0);
    }

    public DataSpec(Uri uri2, int i) {
        this(uri2, 0, -1, (String) null, i);
    }

    public DataSpec(Uri uri2, long j, long j2, String str) {
        this(uri2, j, j, j2, str, 0);
    }

    public DataSpec(Uri uri2, long j, long j2, String str, int i) {
        this(uri2, j, j, j2, str, i);
    }

    public DataSpec(Uri uri2, long j, long j2, long j3, String str, int i) {
        this(uri2, (byte[]) null, j, j2, j3, str, i);
    }

    public DataSpec(Uri uri2, byte[] bArr, long j, long j2, long j3, String str, int i) {
        boolean z = false;
        Assertions.checkArgument(j >= 0);
        Assertions.checkArgument(j2 >= 0);
        Assertions.checkArgument((j3 > 0 || j3 == -1) ? true : z);
        this.uri = uri2;
        this.postBody = bArr;
        this.absoluteStreamPosition = j;
        this.position = j2;
        this.length = j3;
        this.key = str;
        this.flags = i;
    }

    public boolean isFlagSet(int i) {
        return (this.flags & i) == i;
    }

    public String toString() {
        return "DataSpec[" + this.uri + ", " + Arrays.toString(this.postBody) + ", " + this.absoluteStreamPosition + ", " + this.position + ", " + this.length + ", " + this.key + ", " + this.flags + "]";
    }
}
