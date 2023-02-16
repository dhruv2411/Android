package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzbxm {
    private final ByteBuffer zzcuH;

    public static class zza extends IOException {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zza(int r3, int r4) {
            /*
                r2 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r1 = 108(0x6c, float:1.51E-43)
                r0.<init>(r1)
                java.lang.String r1 = "CodedOutputStream was writing to a flat byte array and ran out of space (pos "
                r0.append(r1)
                r0.append(r3)
                java.lang.String r3 = " limit "
                r0.append(r3)
                r0.append(r4)
                java.lang.String r3 = ")."
                r0.append(r3)
                java.lang.String r3 = r0.toString()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbxm.zza.<init>(int, int):void");
        }
    }

    private zzbxm(ByteBuffer byteBuffer) {
        this.zzcuH = byteBuffer;
        this.zzcuH.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzbxm(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzL(int i, int i2) {
        return zzrj(i) + zzrg(i2);
    }

    public static int zzM(int i, int i2) {
        return zzrj(i) + zzrh(i2);
    }

    private static int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) < 65536) {
                        StringBuilder sb = new StringBuilder(39);
                        sb.append("Unpaired surrogate at index ");
                        sb.append(i);
                        throw new IllegalArgumentException(sb.toString());
                    }
                    i++;
                }
            }
            i++;
        }
        return i2;
    }

    private static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        char charAt;
        int length = charSequence.length();
        int i5 = i2 + i;
        int i6 = 0;
        while (i6 < length && (i4 = i6 + i) < i5 && (charAt = charSequence.charAt(i6)) < 128) {
            bArr[i4] = (byte) charAt;
            i6++;
        }
        if (i6 == length) {
            return i + length;
        }
        int i7 = i + i6;
        while (i6 < length) {
            char charAt2 = charSequence.charAt(i6);
            if (charAt2 < 128 && i7 < i5) {
                i3 = i7 + 1;
                bArr[i7] = (byte) charAt2;
            } else if (charAt2 < 2048 && i7 <= i5 - 2) {
                int i8 = i7 + 1;
                bArr[i7] = (byte) (960 | (charAt2 >>> 6));
                i7 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 & '?') | 128);
                i6++;
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i7 <= i5 - 3) {
                int i9 = i7 + 1;
                bArr[i7] = (byte) (480 | (charAt2 >>> 12));
                int i10 = i9 + 1;
                bArr[i9] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i3 = i10 + 1;
                bArr[i10] = (byte) ((charAt2 & '?') | 128);
            } else if (i7 <= i5 - 4) {
                int i11 = i6 + 1;
                if (i11 != charSequence.length()) {
                    char charAt3 = charSequence.charAt(i11);
                    if (!Character.isSurrogatePair(charAt2, charAt3)) {
                        i6 = i11;
                    } else {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i12 = i7 + 1;
                        bArr[i7] = (byte) (240 | (codePoint >>> 18));
                        int i13 = i12 + 1;
                        bArr[i12] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i14 = i13 + 1;
                        bArr[i13] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i7 = i14 + 1;
                        bArr[i14] = (byte) ((codePoint & 63) | 128);
                        i6 = i11;
                        i6++;
                    }
                }
                StringBuilder sb = new StringBuilder(39);
                sb.append("Unpaired surrogate at index ");
                sb.append(i6 - 1);
                throw new IllegalArgumentException(sb.toString());
            } else {
                StringBuilder sb2 = new StringBuilder(37);
                sb2.append("Failed writing ");
                sb2.append(charAt2);
                sb2.append(" at index ");
                sb2.append(i7);
                throw new ArrayIndexOutOfBoundsException(sb2.toString());
            }
            i7 = i3;
            i6++;
        }
        return i7;
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            zzb(charSequence, byteBuffer);
        }
    }

    public static zzbxm zzag(byte[] bArr) {
        return zzc(bArr, 0, bArr.length);
    }

    public static int zzai(byte[] bArr) {
        return zzrl(bArr.length) + bArr.length;
    }

    public static int zzb(int i, double d) {
        return zzrj(i) + 8;
    }

    public static int zzb(int i, zzbxt zzbxt) {
        return (zzrj(i) * 2) + zzd(zzbxt);
    }

    private static int zzb(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt >= 2048) {
                    i2 += zza(charSequence, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(((long) i2) + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt >= 128) {
                if (charAt < 2048) {
                    i = 960 | (charAt >>> 6);
                } else if (charAt < 55296 || 57343 < charAt) {
                    byteBuffer.put((byte) (480 | (charAt >>> 12)));
                    i = ((charAt >>> 6) & 63) | 128;
                } else {
                    int i3 = i2 + 1;
                    if (i3 != charSequence.length()) {
                        char charAt2 = charSequence.charAt(i3);
                        if (!Character.isSurrogatePair(charAt, charAt2)) {
                            i2 = i3;
                        } else {
                            int codePoint = Character.toCodePoint(charAt, charAt2);
                            byteBuffer.put((byte) (240 | (codePoint >>> 18)));
                            byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                            byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                            byteBuffer.put((byte) ((codePoint & 63) | 128));
                            i2 = i3;
                            i2++;
                        }
                    }
                    StringBuilder sb = new StringBuilder(39);
                    sb.append("Unpaired surrogate at index ");
                    sb.append(i2 - 1);
                    throw new IllegalArgumentException(sb.toString());
                }
                byteBuffer.put((byte) i);
                charAt = (charAt & '?') | 128;
            }
            byteBuffer.put((byte) charAt);
            i2++;
        }
    }

    public static int zzbe(long j) {
        return zzbi(j);
    }

    public static int zzbf(long j) {
        return zzbi(j);
    }

    public static int zzbg(long j) {
        return zzbi(zzbk(j));
    }

    public static int zzbi(long j) {
        if ((j & -128) == 0) {
            return 1;
        }
        if ((j & -16384) == 0) {
            return 2;
        }
        if ((j & -2097152) == 0) {
            return 3;
        }
        if ((j & -268435456) == 0) {
            return 4;
        }
        if ((j & -34359738368L) == 0) {
            return 5;
        }
        if ((j & -4398046511104L) == 0) {
            return 6;
        }
        if ((j & -562949953421312L) == 0) {
            return 7;
        }
        if ((j & -72057594037927936L) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static long zzbk(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzc(int i, zzbxt zzbxt) {
        return zzrj(i) + zze(zzbxt);
    }

    public static int zzc(int i, byte[] bArr) {
        return zzrj(i) + zzai(bArr);
    }

    public static zzbxm zzc(byte[] bArr, int i, int i2) {
        return new zzbxm(bArr, i, i2);
    }

    public static int zzd(int i, float f) {
        return zzrj(i) + 4;
    }

    public static int zzd(zzbxt zzbxt) {
        return zzbxt.zzaeT();
    }

    public static int zze(int i, long j) {
        return zzrj(i) + zzbe(j);
    }

    public static int zze(zzbxt zzbxt) {
        int zzaeT = zzbxt.zzaeT();
        return zzrl(zzaeT) + zzaeT;
    }

    public static int zzf(int i, long j) {
        return zzrj(i) + zzbf(j);
    }

    public static int zzg(int i, long j) {
        return zzrj(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzrj(i) + zzbg(j);
    }

    public static int zzh(int i, boolean z) {
        return zzrj(i) + 1;
    }

    public static int zzkb(String str) {
        int zzb = zzb((CharSequence) str);
        return zzrl(zzb) + zzb;
    }

    public static int zzr(int i, String str) {
        return zzrj(i) + zzkb(str);
    }

    public static int zzrg(int i) {
        if (i >= 0) {
            return zzrl(i);
        }
        return 10;
    }

    public static int zzrh(int i) {
        return zzrl(zzrn(i));
    }

    public static int zzrj(int i) {
        return zzrl(zzbxw.zzO(i, 0));
    }

    public static int zzrl(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzrn(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public void zzJ(int i, int i2) throws IOException {
        zzN(i, 0);
        zzre(i2);
    }

    public void zzK(int i, int i2) throws IOException {
        zzN(i, 0);
        zzrf(i2);
    }

    public void zzN(int i, int i2) throws IOException {
        zzrk(zzbxw.zzO(i, i2));
    }

    public void zza(int i, double d) throws IOException {
        zzN(i, 1);
        zzn(d);
    }

    public void zza(int i, long j) throws IOException {
        zzN(i, 0);
        zzba(j);
    }

    public void zza(int i, zzbxt zzbxt) throws IOException {
        zzN(i, 2);
        zzc(zzbxt);
    }

    public int zzaeF() {
        return this.zzcuH.remaining();
    }

    public void zzaeG() {
        if (zzaeF() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void zzah(byte[] bArr) throws IOException {
        zzrk(bArr.length);
        zzaj(bArr);
    }

    public void zzaj(byte[] bArr) throws IOException {
        zzd(bArr, 0, bArr.length);
    }

    public void zzb(int i, long j) throws IOException {
        zzN(i, 0);
        zzbb(j);
    }

    public void zzb(int i, byte[] bArr) throws IOException {
        zzN(i, 2);
        zzah(bArr);
    }

    public void zzb(zzbxt zzbxt) throws IOException {
        zzbxt.zza(this);
    }

    public void zzba(long j) throws IOException {
        zzbh(j);
    }

    public void zzbb(long j) throws IOException {
        zzbh(j);
    }

    public void zzbc(long j) throws IOException {
        zzbj(j);
    }

    public void zzbd(long j) throws IOException {
        zzbh(zzbk(j));
    }

    public void zzbh(long j) throws IOException {
        while ((j & -128) != 0) {
            zzri((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzri((int) j);
    }

    public void zzbj(long j) throws IOException {
        if (this.zzcuH.remaining() < 8) {
            throw new zza(this.zzcuH.position(), this.zzcuH.limit());
        }
        this.zzcuH.putLong(j);
    }

    public void zzbq(boolean z) throws IOException {
        zzri(z ? 1 : 0);
    }

    public void zzc(byte b) throws IOException {
        if (!this.zzcuH.hasRemaining()) {
            throw new zza(this.zzcuH.position(), this.zzcuH.limit());
        }
        this.zzcuH.put(b);
    }

    public void zzc(int i, float f) throws IOException {
        zzN(i, 5);
        zzk(f);
    }

    public void zzc(int i, long j) throws IOException {
        zzN(i, 1);
        zzbc(j);
    }

    public void zzc(zzbxt zzbxt) throws IOException {
        zzrk(zzbxt.zzaeS());
        zzbxt.zza(this);
    }

    public void zzd(int i, long j) throws IOException {
        zzN(i, 0);
        zzbd(j);
    }

    public void zzd(byte[] bArr, int i, int i2) throws IOException {
        if (this.zzcuH.remaining() >= i2) {
            this.zzcuH.put(bArr, i, i2);
            return;
        }
        throw new zza(this.zzcuH.position(), this.zzcuH.limit());
    }

    public void zzg(int i, boolean z) throws IOException {
        zzN(i, 0);
        zzbq(z);
    }

    public void zzk(float f) throws IOException {
        zzrm(Float.floatToIntBits(f));
    }

    public void zzka(String str) throws IOException {
        try {
            int zzrl = zzrl(str.length());
            if (zzrl == zzrl(str.length() * 3)) {
                int position = this.zzcuH.position();
                if (this.zzcuH.remaining() < zzrl) {
                    throw new zza(position + zzrl, this.zzcuH.limit());
                }
                this.zzcuH.position(position + zzrl);
                zza((CharSequence) str, this.zzcuH);
                int position2 = this.zzcuH.position();
                this.zzcuH.position(position);
                zzrk((position2 - position) - zzrl);
                this.zzcuH.position(position2);
                return;
            }
            zzrk(zzb((CharSequence) str));
            zza((CharSequence) str, this.zzcuH);
        } catch (BufferOverflowException e) {
            zza zza2 = new zza(this.zzcuH.position(), this.zzcuH.limit());
            zza2.initCause(e);
            throw zza2;
        }
    }

    public void zzn(double d) throws IOException {
        zzbj(Double.doubleToLongBits(d));
    }

    public void zzq(int i, String str) throws IOException {
        zzN(i, 2);
        zzka(str);
    }

    public void zzre(int i) throws IOException {
        if (i >= 0) {
            zzrk(i);
        } else {
            zzbh((long) i);
        }
    }

    public void zzrf(int i) throws IOException {
        zzrk(zzrn(i));
    }

    public void zzri(int i) throws IOException {
        zzc((byte) i);
    }

    public void zzrk(int i) throws IOException {
        while ((i & -128) != 0) {
            zzri((i & 127) | 128);
            i >>>= 7;
        }
        zzri(i);
    }

    public void zzrm(int i) throws IOException {
        if (this.zzcuH.remaining() < 4) {
            throw new zza(this.zzcuH.position(), this.zzcuH.limit());
        }
        this.zzcuH.putInt(i);
    }
}
