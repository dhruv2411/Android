package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@zzme
public final class zzmk extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator<zzmk> CREATOR = new zzml();
    public final ApplicationInfo applicationInfo;
    public final int versionCode;
    public final boolean zzKJ;
    public final String zzRA;
    public final String zzRB;
    public final String zzRC;
    public final Bundle zzRD;
    public final int zzRE;
    public final Bundle zzRF;
    public final boolean zzRG;
    public final int zzRH;
    public final int zzRI;
    public final String zzRJ;
    public final long zzRK;
    public final String zzRL;
    @Nullable
    public final List<String> zzRM;
    public final List<String> zzRN;
    public final long zzRO;
    public final zzmr zzRP;
    public final String zzRQ;
    public final float zzRR;
    public final int zzRS;
    public final int zzRT;
    public final boolean zzRU;
    public final boolean zzRV;
    public final String zzRW;
    public final boolean zzRX;
    public final String zzRY;
    public final int zzRZ;
    @Nullable
    public final Bundle zzRx;
    public final zzec zzRy;
    @Nullable
    public final PackageInfo zzRz;
    public final Bundle zzSa;
    public final String zzSb;
    public final boolean zzSc;
    public final Bundle zzSd;
    @Nullable
    public final String zzSe;
    @Nullable
    public final String zzSf;
    @Nullable
    public final String zzSg;
    @Nullable
    public final boolean zzSh;
    public final zzhc zzvF;
    @Nullable
    public final zzfc zzvH;
    public final List<String> zzvK;
    public final String zzvk;
    public final String zzvl;
    public final zzqh zzvn;
    public final zzeg zzvr;
    public final float zzxk;

    @zzme
    public static final class zza {
        public final ApplicationInfo applicationInfo;
        public final boolean zzKJ;
        public final String zzRB;
        public final String zzRC;
        public final Bundle zzRD;
        public final int zzRE;
        public final Bundle zzRF;
        public final boolean zzRG;
        public final int zzRH;
        public final int zzRI;
        public final String zzRJ;
        public final long zzRK;
        public final String zzRL;
        @Nullable
        public final List<String> zzRM;
        public final List<String> zzRN;
        public final String zzRQ;
        public final float zzRR;
        public final int zzRS;
        public final int zzRT;
        public final boolean zzRU;
        public final boolean zzRV;
        public final boolean zzRX;
        public final String zzRY;
        public final int zzRZ;
        @Nullable
        public final Bundle zzRx;
        public final zzec zzRy;
        @Nullable
        public final PackageInfo zzRz;
        public final Bundle zzSa;
        public final String zzSb;
        public final boolean zzSc;
        @Nullable
        public final Bundle zzSd;
        public final boolean zzSh;
        public final Future<zzmr> zzSi;
        public final Future<String> zzSj;
        public final Future<String> zzSk;
        public final zzhc zzvF;
        @Nullable
        public final zzfc zzvH;
        public final List<String> zzvK;
        public final String zzvk;
        public final String zzvl;
        public final zzqh zzvn;
        public final zzeg zzvr;
        public final float zzxk;

        public zza(@Nullable Bundle bundle, zzec zzec, zzeg zzeg, String str, ApplicationInfo applicationInfo2, @Nullable PackageInfo packageInfo, String str2, String str3, zzqh zzqh, Bundle bundle2, List<String> list, List<String> list2, Bundle bundle3, boolean z, int i, int i2, float f, String str4, long j, String str5, @Nullable List<String> list3, String str6, zzhc zzhc, Future<zzmr> future, String str7, float f2, boolean z2, int i3, int i4, boolean z3, boolean z4, Future<String> future2, String str8, boolean z5, int i5, Bundle bundle4, String str9, @Nullable zzfc zzfc, boolean z6, Bundle bundle5, boolean z7, Future<String> future3) {
            List<String> list4;
            List<String> list5 = list;
            this.zzRx = bundle;
            this.zzRy = zzec;
            this.zzvr = zzeg;
            this.zzvl = str;
            this.applicationInfo = applicationInfo2;
            this.zzRz = packageInfo;
            this.zzRB = str2;
            this.zzRC = str3;
            this.zzvn = zzqh;
            this.zzRD = bundle2;
            this.zzRG = z;
            this.zzRH = i;
            this.zzRI = i2;
            this.zzxk = f;
            if (list5 == null || list5.size() <= 0) {
                this.zzRE = 0;
                list4 = null;
                this.zzvK = null;
            } else {
                this.zzRE = 3;
                this.zzvK = list5;
                list4 = list2;
            }
            this.zzRN = list4;
            this.zzRF = bundle3;
            this.zzRJ = str4;
            this.zzRK = j;
            this.zzRL = str5;
            this.zzRM = list3;
            this.zzvk = str6;
            this.zzvF = zzhc;
            this.zzSi = future;
            this.zzRQ = str7;
            this.zzRR = f2;
            this.zzRX = z2;
            this.zzRS = i3;
            this.zzRT = i4;
            this.zzRU = z3;
            this.zzRV = z4;
            this.zzSj = future2;
            this.zzRY = str8;
            this.zzKJ = z5;
            this.zzRZ = i5;
            this.zzSa = bundle4;
            this.zzSb = str9;
            this.zzvH = zzfc;
            this.zzSc = z6;
            this.zzSd = bundle5;
            this.zzSh = z7;
            this.zzSk = future3;
        }
    }

    zzmk(int i, Bundle bundle, zzec zzec, zzeg zzeg, String str, ApplicationInfo applicationInfo2, PackageInfo packageInfo, String str2, String str3, String str4, zzqh zzqh, Bundle bundle2, int i2, List<String> list, Bundle bundle3, boolean z, int i3, int i4, float f, String str5, long j, String str6, List<String> list2, String str7, zzhc zzhc, List<String> list3, long j2, zzmr zzmr, String str8, float f2, boolean z2, int i5, int i6, boolean z3, boolean z4, String str9, String str10, boolean z5, int i7, Bundle bundle4, String str11, zzfc zzfc, boolean z6, Bundle bundle5, String str12, String str13, String str14, boolean z7) {
        this.versionCode = i;
        this.zzRx = bundle;
        this.zzRy = zzec;
        this.zzvr = zzeg;
        this.zzvl = str;
        this.applicationInfo = applicationInfo2;
        this.zzRz = packageInfo;
        this.zzRA = str2;
        this.zzRB = str3;
        this.zzRC = str4;
        this.zzvn = zzqh;
        this.zzRD = bundle2;
        this.zzRE = i2;
        this.zzvK = list;
        this.zzRN = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzRF = bundle3;
        this.zzRG = z;
        this.zzRH = i3;
        this.zzRI = i4;
        this.zzxk = f;
        this.zzRJ = str5;
        this.zzRK = j;
        this.zzRL = str6;
        this.zzRM = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzvk = str7;
        this.zzvF = zzhc;
        this.zzRO = j2;
        this.zzRP = zzmr;
        this.zzRQ = str8;
        this.zzRR = f2;
        this.zzRX = z2;
        this.zzRS = i5;
        this.zzRT = i6;
        this.zzRU = z3;
        this.zzRV = z4;
        this.zzRW = str9;
        this.zzRY = str10;
        this.zzKJ = z5;
        this.zzRZ = i7;
        this.zzSa = bundle4;
        this.zzSb = str11;
        this.zzvH = zzfc;
        this.zzSc = z6;
        this.zzSd = bundle5;
        this.zzSe = str12;
        this.zzSf = str13;
        this.zzSg = str14;
        this.zzSh = z7;
    }

    public zzmk(@Nullable Bundle bundle, zzec zzec, zzeg zzeg, String str, ApplicationInfo applicationInfo2, @Nullable PackageInfo packageInfo, String str2, String str3, String str4, zzqh zzqh, Bundle bundle2, int i, List<String> list, List<String> list2, Bundle bundle3, boolean z, int i2, int i3, float f, String str5, long j, String str6, @Nullable List<String> list3, String str7, zzhc zzhc, long j2, zzmr zzmr, String str8, float f2, boolean z2, int i4, int i5, boolean z3, boolean z4, String str9, String str10, boolean z5, int i6, Bundle bundle4, String str11, @Nullable zzfc zzfc, boolean z6, Bundle bundle5, String str12, String str13, String str14, boolean z7) {
        this(22, bundle, zzec, zzeg, str, applicationInfo2, packageInfo, str2, str3, str4, zzqh, bundle2, i, list, bundle3, z, i2, i3, f, str5, j, str6, list3, str7, zzhc, list2, j2, zzmr, str8, f2, z2, i4, i5, z3, z4, str9, str10, z5, i6, bundle4, str11, zzfc, z6, bundle5, str12, str13, str14, z7);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzmk(com.google.android.gms.internal.zzmk.zza r77, long r78, java.lang.String r80, java.lang.String r81, java.lang.String r82) {
        /*
            r76 = this;
            r0 = r77
            android.os.Bundle r1 = r0.zzRx
            com.google.android.gms.internal.zzec r2 = r0.zzRy
            com.google.android.gms.internal.zzeg r3 = r0.zzvr
            java.lang.String r4 = r0.zzvl
            android.content.pm.ApplicationInfo r5 = r0.applicationInfo
            android.content.pm.PackageInfo r6 = r0.zzRz
            java.util.concurrent.Future<java.lang.String> r7 = r0.zzSk
            java.lang.String r8 = ""
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.SECONDS
            r10 = 2
            java.lang.Object r7 = com.google.android.gms.internal.zzql.zza(r7, r8, r10, r9)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r8 = r0.zzRB
            java.lang.String r9 = r0.zzRC
            com.google.android.gms.internal.zzqh r10 = r0.zzvn
            android.os.Bundle r11 = r0.zzRD
            int r12 = r0.zzRE
            java.util.List<java.lang.String> r13 = r0.zzvK
            java.util.List<java.lang.String> r14 = r0.zzRN
            android.os.Bundle r15 = r0.zzRF
            r50 = r15
            boolean r15 = r0.zzRG
            r51 = r15
            int r15 = r0.zzRH
            r52 = r15
            int r15 = r0.zzRI
            r53 = r15
            float r15 = r0.zzxk
            r54 = r15
            java.lang.String r15 = r0.zzRJ
            r55 = r14
            r56 = r15
            long r14 = r0.zzRK
            r57 = r14
            java.lang.String r15 = r0.zzRL
            java.util.List<java.lang.String> r14 = r0.zzRM
            r59 = r15
            java.lang.String r15 = r0.zzvk
            r60 = r15
            com.google.android.gms.internal.zzhc r15 = r0.zzvF
            r61 = r14
            java.util.concurrent.Future<com.google.android.gms.internal.zzmr> r14 = r0.zzSi
            r62 = r15
            r15 = 0
            if (r14 == 0) goto L_0x006e
            java.util.concurrent.Future<com.google.android.gms.internal.zzmr> r14 = r0.zzSi
            r63 = r13
            r13 = 6
            r64 = r12
            java.util.concurrent.TimeUnit r12 = java.util.concurrent.TimeUnit.SECONDS
            java.lang.Object r12 = com.google.android.gms.internal.zzql.zza(r14, r15, r13, r12)
            com.google.android.gms.internal.zzmr r12 = (com.google.android.gms.internal.zzmr) r12
            r29 = r12
            goto L_0x0074
        L_0x006e:
            r64 = r12
            r63 = r13
            r29 = r15
        L_0x0074:
            java.lang.String r15 = r0.zzRQ
            float r14 = r0.zzRR
            boolean r13 = r0.zzRX
            int r12 = r0.zzRS
            r65 = r15
            int r15 = r0.zzRT
            r66 = r15
            boolean r15 = r0.zzRU
            r67 = r15
            boolean r15 = r0.zzRV
            r68 = r12
            java.util.concurrent.Future<java.lang.String> r12 = r0.zzSj
            r69 = r13
            java.lang.String r13 = ""
            r70 = r14
            r14 = 1
            r71 = r15
            java.util.concurrent.TimeUnit r15 = java.util.concurrent.TimeUnit.SECONDS
            java.lang.Object r12 = com.google.android.gms.internal.zzql.zza(r12, r13, r14, r15)
            r37 = r12
            java.lang.String r37 = (java.lang.String) r37
            java.lang.String r15 = r0.zzRY
            boolean r14 = r0.zzKJ
            int r13 = r0.zzRZ
            android.os.Bundle r12 = r0.zzSa
            r72 = r15
            java.lang.String r15 = r0.zzSb
            r73 = r15
            com.google.android.gms.internal.zzfc r15 = r0.zzvH
            r74 = r15
            boolean r15 = r0.zzSc
            r75 = r15
            android.os.Bundle r15 = r0.zzSd
            boolean r0 = r0.zzSh
            r49 = r0
            r0 = r76
            r41 = r12
            r33 = r68
            r12 = r64
            r40 = r13
            r32 = r69
            r13 = r63
            r39 = r14
            r21 = r57
            r24 = r61
            r31 = r70
            r14 = r55
            r45 = r15
            r16 = r51
            r17 = r52
            r18 = r53
            r19 = r54
            r20 = r56
            r23 = r59
            r25 = r60
            r26 = r62
            r30 = r65
            r34 = r66
            r35 = r67
            r36 = r71
            r38 = r72
            r42 = r73
            r43 = r74
            r44 = r75
            r15 = r50
            r27 = r78
            r46 = r80
            r47 = r81
            r48 = r82
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r23, r24, r25, r26, r27, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmk.<init>(com.google.android.gms.internal.zzmk$zza, long, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzml.zza(this, parcel, i);
    }
}
