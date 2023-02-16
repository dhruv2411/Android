package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzaa;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

public class zzayz extends com.google.android.gms.common.internal.safeparcel.zza implements Comparable<zzayz> {
    public static final Parcelable.Creator<zzayz> CREATOR = new zzaza();
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final zza zzbBG = new zza();
    public final String name;
    final String zzaGV;
    final long zzbBC;
    final byte[] zzbBD;
    public final int zzbBE;
    public final int zzbBF;
    final boolean zzbhn;
    final double zzbhp;

    public static class zza implements Comparator<zzayz> {
        /* renamed from: zza */
        public int compare(zzayz zzayz, zzayz zzayz2) {
            return zzayz.zzbBF == zzayz2.zzbBF ? zzayz.name.compareTo(zzayz2.name) : zzayz.zzbBF - zzayz2.zzbBF;
        }
    }

    public zzayz(String str, long j, boolean z, double d, String str2, byte[] bArr, int i, int i2) {
        this.name = str;
        this.zzbBC = j;
        this.zzbhn = z;
        this.zzbhp = d;
        this.zzaGV = str2;
        this.zzbBD = bArr;
        this.zzbBE = i;
        this.zzbBF = i2;
    }

    private static int compare(byte b, byte b2) {
        return b - b2;
    }

    private static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    private static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    private static int compare(String str, String str2) {
        if (str == str2) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    private static int compare(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzayz)) {
            return false;
        }
        zzayz zzayz = (zzayz) obj;
        if (!zzaa.equal(this.name, zzayz.name) || this.zzbBE != zzayz.zzbBE || this.zzbBF != zzayz.zzbBF) {
            return false;
        }
        switch (this.zzbBE) {
            case 1:
                return this.zzbBC == zzayz.zzbBC;
            case 2:
                return this.zzbhn == zzayz.zzbhn;
            case 3:
                return this.zzbhp == zzayz.zzbhp;
            case 4:
                return zzaa.equal(this.zzaGV, zzayz.zzaGV);
            case 5:
                return Arrays.equals(this.zzbBD, zzayz.zzbBD);
            default:
                int i = this.zzbBE;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Invalid enum value: ");
                sb.append(i);
                throw new AssertionError(sb.toString());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        zza(sb);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaza.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(zzayz zzayz) {
        int compareTo = this.name.compareTo(zzayz.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int compare = compare(this.zzbBE, zzayz.zzbBE);
        if (compare != 0) {
            return compare;
        }
        switch (this.zzbBE) {
            case 1:
                return compare(this.zzbBC, zzayz.zzbBC);
            case 2:
                return compare(this.zzbhn, zzayz.zzbhn);
            case 3:
                return Double.compare(this.zzbhp, zzayz.zzbhp);
            case 4:
                return compare(this.zzaGV, zzayz.zzaGV);
            case 5:
                if (this.zzbBD == zzayz.zzbBD) {
                    return 0;
                }
                if (this.zzbBD == null) {
                    return -1;
                }
                if (zzayz.zzbBD == null) {
                    return 1;
                }
                for (int i = 0; i < Math.min(this.zzbBD.length, zzayz.zzbBD.length); i++) {
                    int compare2 = compare(this.zzbBD[i], zzayz.zzbBD[i]);
                    if (compare2 != 0) {
                        return compare2;
                    }
                }
                return compare(this.zzbBD.length, zzayz.zzbBD.length);
            default:
                int i2 = this.zzbBE;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Invalid enum value: ");
                sb.append(i2);
                throw new AssertionError(sb.toString());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0064, code lost:
        r5.append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0079, code lost:
        r5.append(", ");
        r5.append(r4.zzbBE);
        r5.append(", ");
        r5.append(r4.zzbBF);
        r5.append(")");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0096, code lost:
        return r5.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x005f, code lost:
        r5.append(r0);
        r0 = "'";
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zza(java.lang.StringBuilder r5) {
        /*
            r4 = this;
            java.lang.String r0 = "Flag("
            r5.append(r0)
            java.lang.String r0 = r4.name
            r5.append(r0)
            java.lang.String r0 = ", "
            r5.append(r0)
            int r0 = r4.zzbBE
            switch(r0) {
                case 1: goto L_0x0074;
                case 2: goto L_0x006e;
                case 3: goto L_0x0068;
                case 4: goto L_0x0058;
                case 5: goto L_0x0042;
                default: goto L_0x0014;
            }
        L_0x0014:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            java.lang.String r0 = r4.name
            int r1 = r4.zzbBE
            r2 = 27
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Invalid type: "
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = ", "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            r5.<init>(r0)
            throw r5
        L_0x0042:
            byte[] r0 = r4.zzbBD
            if (r0 != 0) goto L_0x0049
            java.lang.String r0 = "null"
            goto L_0x0064
        L_0x0049:
            java.lang.String r0 = "'"
            r5.append(r0)
            java.lang.String r0 = new java.lang.String
            byte[] r1 = r4.zzbBD
            java.nio.charset.Charset r2 = UTF_8
            r0.<init>(r1, r2)
            goto L_0x005f
        L_0x0058:
            java.lang.String r0 = "'"
            r5.append(r0)
            java.lang.String r0 = r4.zzaGV
        L_0x005f:
            r5.append(r0)
            java.lang.String r0 = "'"
        L_0x0064:
            r5.append(r0)
            goto L_0x0079
        L_0x0068:
            double r0 = r4.zzbhp
            r5.append(r0)
            goto L_0x0079
        L_0x006e:
            boolean r0 = r4.zzbhn
            r5.append(r0)
            goto L_0x0079
        L_0x0074:
            long r0 = r4.zzbBC
            r5.append(r0)
        L_0x0079:
            java.lang.String r0 = ", "
            r5.append(r0)
            int r0 = r4.zzbBE
            r5.append(r0)
            java.lang.String r0 = ", "
            r5.append(r0)
            int r0 = r4.zzbBF
            r5.append(r0)
            java.lang.String r0 = ")"
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzayz.zza(java.lang.StringBuilder):java.lang.String");
    }
}
