package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbxw {
    static final int zzcuS = zzO(1, 3);
    static final int zzcuT = zzO(1, 4);
    static final int zzcuU = zzO(2, 0);
    static final int zzcuV = zzO(3, 2);
    public static final int[] zzcuW = new int[0];
    public static final long[] zzcuX = new long[0];
    public static final float[] zzcuY = new float[0];
    public static final double[] zzcuZ = new double[0];
    public static final boolean[] zzcva = new boolean[0];
    public static final String[] zzcvb = new String[0];
    public static final byte[][] zzcvc = new byte[0][];
    public static final byte[] zzcvd = new byte[0];

    public static int zzO(int i, int i2) {
        return (i << 3) | i2;
    }

    public static final int zzb(zzbxl zzbxl, int i) throws IOException {
        int position = zzbxl.getPosition();
        zzbxl.zzqY(i);
        int i2 = 1;
        while (zzbxl.zzaeo() == i) {
            zzbxl.zzqY(i);
            i2++;
        }
        zzbxl.zzrc(position);
        return i2;
    }

    static int zzrr(int i) {
        return i & 7;
    }

    public static int zzrs(int i) {
        return i >>> 3;
    }
}
