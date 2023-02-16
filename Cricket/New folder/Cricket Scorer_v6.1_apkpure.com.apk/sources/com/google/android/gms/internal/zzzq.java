package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class zzzq {
    private static long zzI(long j) {
        return j ^ (j >>> 47);
    }

    private static long zza(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, 8);
        long j = 0;
        int i3 = 0;
        while (i3 < min) {
            i3++;
            j |= (((long) bArr[i + i3]) & 255) << (i3 * 8);
        }
        return j;
    }

    private static long zza(byte[] bArr, long j) {
        int length = bArr.length & -8;
        int length2 = bArr.length & 7;
        long length3 = j ^ (((long) bArr.length) * -4132994306676758123L);
        for (int i = 0; i < length; i += 8) {
            length3 = (length3 ^ (zzI(zzb(bArr, i) * -4132994306676758123L) * -4132994306676758123L)) * -4132994306676758123L;
        }
        if (length2 != 0) {
            length3 = (length3 ^ zza(bArr, length, length2)) * -4132994306676758123L;
        }
        return zzI(zzI(length3) * -4132994306676758123L);
    }

    private static void zza(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long zzb = zzb(bArr, i);
        long zzb2 = zzb(bArr, i + 8);
        long zzb3 = zzb(bArr, i + 16);
        long zzb4 = zzb(bArr, i + 24);
        long j3 = j + zzb;
        long j4 = j3 + zzb2 + zzb3;
        long rotateRight = Long.rotateRight(j2 + j3 + zzb4, 51) + Long.rotateRight(j4, 23);
        jArr[0] = j4 + zzb4;
        jArr[1] = rotateRight + j3;
    }

    private static long zzb(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, 8);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap.getLong();
    }

    private static long zzc(long j, long j2) {
        long j3 = (j2 ^ j) * -4132994306676758123L;
        long j4 = (j ^ (j3 ^ (j3 >>> 47))) * -4132994306676758123L;
        return (j4 ^ (j4 >>> 47)) * -4132994306676758123L;
    }

    public static long zzn(byte[] bArr) {
        long zza = bArr.length <= 32 ? zza(bArr, -1397348546323613475L) : bArr.length <= 64 ? zzo(bArr) : zzp(bArr);
        long j = -6505348102511208375L;
        long zzb = bArr.length >= 8 ? zzb(bArr, 0) : -6505348102511208375L;
        if (bArr.length >= 9) {
            j = zzb(bArr, bArr.length - 8);
        }
        long zzc = zzc(zza + j, zzb);
        return (zzc == 0 || zzc == 1) ? zzc - 2 : zzc;
    }

    private static long zzo(byte[] bArr) {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        long zzb = zzb(bArr2, 24);
        int i = length - 16;
        long zzb2 = zzb(bArr2, 0) + ((((long) length) + zzb(bArr2, i)) * -6505348102511208375L);
        long rotateRight = Long.rotateRight(zzb2 + zzb, 52);
        long rotateRight2 = Long.rotateRight(zzb2, 37);
        long zzb3 = zzb2 + zzb(bArr2, 8);
        long rotateRight3 = rotateRight2 + Long.rotateRight(zzb3, 7);
        int i2 = i;
        int i3 = length;
        long zzb4 = zzb3 + zzb(bArr2, 16);
        long j = zzb4 + zzb;
        long zzb5 = zzb(bArr2, 16) + zzb(bArr2, i3 - 32);
        long zzb6 = zzb(bArr2, i3 - 8);
        long rotateRight4 = Long.rotateRight(zzb5 + zzb6, 52);
        long rotateRight5 = Long.rotateRight(zzb5, 37);
        long rotateRight6 = rotateRight + Long.rotateRight(zzb4, 31) + rotateRight3;
        long zzb7 = zzb5 + zzb(bArr2, i3 - 24);
        long rotateRight7 = rotateRight5 + Long.rotateRight(zzb7, 7);
        long zzb8 = zzb7 + zzb(bArr2, i2);
        return zzI((zzI(((j + rotateRight4 + Long.rotateRight(zzb8, 31) + rotateRight7) * -4288712594273399085L) + ((zzb8 + zzb6 + rotateRight6) * -6505348102511208375L)) * -6505348102511208375L) + rotateRight6) * -4288712594273399085L;
    }

    private static long zzp(byte[] bArr) {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        long zzb = zzb(bArr2, 0);
        long zzb2 = zzb(bArr2, length - 16) ^ -8261664234251669945L;
        long j = (long) length;
        byte[] bArr3 = bArr2;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        int i = length;
        zza(bArr3, length - 64, j, zzb2, jArr);
        zza(bArr3, i - 32, j * -8261664234251669945L, -6505348102511208375L, jArr2);
        long zzb3 = (zzb(bArr2, length - 56) ^ -6505348102511208375L) + (zzI(jArr[1]) * -8261664234251669945L);
        long rotateRight = Long.rotateRight(zzb3 + zzb, 39) * -8261664234251669945L;
        long rotateRight2 = Long.rotateRight(zzb2, 33) * -8261664234251669945L;
        int i2 = (i - 1) & -64;
        int i3 = 0;
        while (true) {
            long rotateRight3 = (Long.rotateRight(((rotateRight + rotateRight2) + jArr[0]) + zzb(bArr2, i3 + 16), 37) * -8261664234251669945L) ^ jArr2[1];
            rotateRight2 = (Long.rotateRight((rotateRight2 + jArr[1]) + zzb(bArr2, i3 + 48), 42) * -8261664234251669945L) ^ jArr[0];
            long rotateRight4 = Long.rotateRight(zzb3 ^ jArr2[0], 33);
            zza(bArr2, i3, jArr[1] * -8261664234251669945L, rotateRight3 + jArr2[0], jArr);
            zza(bArr2, i3 + 32, rotateRight4 + jArr2[1], rotateRight2, jArr2);
            i3 += 64;
            i2 -= 64;
            if (i2 == 0) {
                return zzc(zzc(jArr[0], jArr2[0]) + (zzI(rotateRight2) * -8261664234251669945L) + rotateRight3, zzc(jArr[1], jArr2[1]) + rotateRight4);
            }
            zzb3 = rotateRight3;
            rotateRight = rotateRight4;
        }
    }
}
