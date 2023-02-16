package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.PriorityQueue;

@zzme
public class zzdk {

    public static class zza {
        final long value;
        final String zzyu;
        final int zzyv;

        zza(long j, String str, int i) {
            this.value = j;
            this.zzyu = str;
            this.zzyv = i;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == null || !(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zza.value == this.value && zza.zzyv == this.zzyv;
        }

        public int hashCode() {
            return (int) this.value;
        }
    }

    static long zza(int i, int i2, long j, long j2, long j3) {
        return ((((((j + 1073807359) - ((j2 * ((((long) i) + 2147483647L) % 1073807359)) % 1073807359)) % 1073807359) * j3) % 1073807359) + ((((long) i2) + 2147483647L) % 1073807359)) % 1073807359;
    }

    static long zza(long j, int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return j;
        }
        return (i % 2 == 0 ? zza((j * j) % 1073807359, i / 2) : j * (zza((j * j) % 1073807359, i / 2) % 1073807359)) % 1073807359;
    }

    static String zza(String[] strArr, int i, int i2) {
        int i3 = i2 + i;
        if (strArr.length < i3) {
            zzpk.e("Unable to construct shingle");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int i4 = i3 - 1;
            if (i < i4) {
                stringBuffer.append(strArr[i]);
                stringBuffer.append(' ');
                i++;
            } else {
                stringBuffer.append(strArr[i4]);
                return stringBuffer.toString();
            }
        }
    }

    static void zza(int i, long j, String str, int i2, PriorityQueue<zza> priorityQueue) {
        zza zza2 = new zza(j, str, i2);
        if ((priorityQueue.size() != i || (priorityQueue.peek().zzyv <= zza2.zzyv && priorityQueue.peek().value <= zza2.value)) && !priorityQueue.contains(zza2)) {
            priorityQueue.add(zza2);
            if (priorityQueue.size() > i) {
                priorityQueue.poll();
            }
        }
    }

    public static void zza(String[] strArr, int i, int i2, PriorityQueue<zza> priorityQueue) {
        String[] strArr2 = strArr;
        int i3 = i2;
        if (strArr2.length < i3) {
            zza(i, zzb(strArr2, 0, strArr2.length), zza(strArr2, 0, strArr2.length), strArr2.length, priorityQueue);
            return;
        }
        long zzb = zzb(strArr2, 0, i3);
        zza(i, zzb, zza(strArr2, 0, i3), i3, priorityQueue);
        long zza2 = zza(16785407, i3 - 1);
        int i4 = 1;
        long j = zzb;
        while (i4 < (strArr2.length - i3) + 1) {
            long zza3 = zza(zzdi.zzH(strArr2[i4 - 1]), zzdi.zzH(strArr2[(i4 + i3) - 1]), j, zza2, 16785407);
            zza(i, zza3, zza(strArr2, i4, i3), strArr2.length, priorityQueue);
            i4++;
            j = zza3;
        }
    }

    private static long zzb(String[] strArr, int i, int i2) {
        long zzH = (((long) zzdi.zzH(strArr[i])) + 2147483647L) % 1073807359;
        for (int i3 = i + 1; i3 < i + i2; i3++) {
            zzH = (((zzH * 16785407) % 1073807359) + ((((long) zzdi.zzH(strArr[i3])) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return zzH;
    }
}
