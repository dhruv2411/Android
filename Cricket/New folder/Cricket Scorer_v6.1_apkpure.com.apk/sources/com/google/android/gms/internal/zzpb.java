package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzha;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@zzme
public class zzpb {
    public final int errorCode;
    public final int orientation;
    public final List<String> zzKF;
    public final List<String> zzKG;
    @Nullable
    public final List<String> zzKI;
    public final long zzKL;
    @Nullable
    public final zzjq zzLi;
    @Nullable
    public final zzkb zzLj;
    @Nullable
    public final String zzLk;
    @Nullable
    public final zzjt zzLl;
    @Nullable
    public final zzqw zzNH;
    public final String zzRB;
    public final zzec zzRy;
    @Nullable
    public final zzoo zzSC;
    @Nullable
    public final List<String> zzSE;
    public final boolean zzSF;
    public final zzmp zzSG;
    public final String zzSJ;
    public final long zzSm;
    public final boolean zzSn;
    public final long zzSo;
    public final List<String> zzSp;
    public final String zzSs;
    public final JSONObject zzWa;
    public boolean zzWb;
    public final zzjr zzWc;
    @Nullable
    public final String zzWd;
    public final zzeg zzWe;
    @Nullable
    public final List<String> zzWf;
    public final long zzWg;
    public final long zzWh;
    @Nullable
    public final zzha.zza zzWi;
    public boolean zzWj;
    public boolean zzWk;
    public boolean zzWl;

    @zzme
    public static final class zza {
        public final int errorCode;
        public final zzmk zzTi;
        @Nullable
        public final JSONObject zzWa;
        public final zzjr zzWc;
        public final long zzWg;
        public final long zzWh;
        public final zzmn zzWm;
        @Nullable
        public final zzeg zzvr;

        public zza(zzmk zzmk, zzmn zzmn, zzjr zzjr, zzeg zzeg, int i, long j, long j2, JSONObject jSONObject) {
            this.zzTi = zzmk;
            this.zzWm = zzmn;
            this.zzWc = zzjr;
            this.zzvr = zzeg;
            this.errorCode = i;
            this.zzWg = j;
            this.zzWh = j2;
            this.zzWa = jSONObject;
        }
    }

    public zzpb(zzec zzec, @Nullable zzqw zzqw, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, @Nullable zzjq zzjq, @Nullable zzkb zzkb, @Nullable String str2, zzjr zzjr, @Nullable zzjt zzjt, long j2, zzeg zzeg, long j3, long j4, long j5, String str3, JSONObject jSONObject, @Nullable zzha.zza zza2, zzoo zzoo, List<String> list4, List<String> list5, boolean z2, zzmp zzmp, @Nullable String str4, List<String> list6, String str5) {
        this.zzWj = false;
        this.zzWk = false;
        this.zzWl = false;
        this.zzRy = zzec;
        this.zzNH = zzqw;
        this.zzKF = zzm(list);
        this.errorCode = i;
        this.zzKG = zzm(list2);
        this.zzSp = zzm(list3);
        this.orientation = i2;
        this.zzKL = j;
        this.zzRB = str;
        this.zzSn = z;
        this.zzLi = zzjq;
        this.zzLj = zzkb;
        this.zzLk = str2;
        this.zzWc = zzjr;
        this.zzLl = zzjt;
        this.zzSo = j2;
        this.zzWe = zzeg;
        this.zzSm = j3;
        this.zzWg = j4;
        this.zzWh = j5;
        this.zzSs = str3;
        this.zzWa = jSONObject;
        this.zzWi = zza2;
        this.zzSC = zzoo;
        this.zzWf = zzm(list4);
        this.zzSE = zzm(list5);
        this.zzSF = z2;
        this.zzSG = zzmp;
        this.zzWd = str4;
        this.zzKI = zzm(list6);
        this.zzSJ = str5;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzpb(com.google.android.gms.internal.zzpb.zza r53, @android.support.annotation.Nullable com.google.android.gms.internal.zzqw r54, @android.support.annotation.Nullable com.google.android.gms.internal.zzjq r55, @android.support.annotation.Nullable com.google.android.gms.internal.zzkb r56, @android.support.annotation.Nullable java.lang.String r57, @android.support.annotation.Nullable com.google.android.gms.internal.zzjt r58, @android.support.annotation.Nullable com.google.android.gms.internal.zzha.zza r59, @android.support.annotation.Nullable java.lang.String r60) {
        /*
            r52 = this;
            r0 = r53
            com.google.android.gms.internal.zzmk r1 = r0.zzTi
            com.google.android.gms.internal.zzec r3 = r1.zzRy
            com.google.android.gms.internal.zzmn r1 = r0.zzWm
            java.util.List<java.lang.String> r5 = r1.zzKF
            int r6 = r0.errorCode
            com.google.android.gms.internal.zzmn r1 = r0.zzWm
            java.util.List<java.lang.String> r7 = r1.zzKG
            com.google.android.gms.internal.zzmn r1 = r0.zzWm
            java.util.List<java.lang.String> r8 = r1.zzSp
            com.google.android.gms.internal.zzmn r1 = r0.zzWm
            int r9 = r1.orientation
            com.google.android.gms.internal.zzmn r1 = r0.zzWm
            long r10 = r1.zzKL
            com.google.android.gms.internal.zzmk r1 = r0.zzTi
            java.lang.String r12 = r1.zzRB
            com.google.android.gms.internal.zzmn r1 = r0.zzWm
            boolean r13 = r1.zzSn
            com.google.android.gms.internal.zzjr r1 = r0.zzWc
            com.google.android.gms.internal.zzmn r2 = r0.zzWm
            long r14 = r2.zzSo
            com.google.android.gms.internal.zzeg r4 = r0.zzvr
            com.google.android.gms.internal.zzmn r2 = r0.zzWm
            r39 = r14
            long r14 = r2.zzSm
            r41 = r14
            long r14 = r0.zzWg
            r43 = r14
            long r14 = r0.zzWh
            com.google.android.gms.internal.zzmn r2 = r0.zzWm
            java.lang.String r2 = r2.zzSs
            r45 = r1
            org.json.JSONObject r1 = r0.zzWa
            r46 = r2
            com.google.android.gms.internal.zzmn r2 = r0.zzWm
            com.google.android.gms.internal.zzoo r2 = r2.zzSC
            r47 = r2
            com.google.android.gms.internal.zzmn r2 = r0.zzWm
            java.util.List<java.lang.String> r2 = r2.zzSD
            r48 = r2
            com.google.android.gms.internal.zzmn r2 = r0.zzWm
            java.util.List<java.lang.String> r2 = r2.zzSD
            r49 = r2
            com.google.android.gms.internal.zzmn r2 = r0.zzWm
            boolean r2 = r2.zzSF
            r50 = r2
            com.google.android.gms.internal.zzmn r2 = r0.zzWm
            com.google.android.gms.internal.zzmp r2 = r2.zzSG
            r51 = r2
            com.google.android.gms.internal.zzmn r2 = r0.zzWm
            java.util.List<java.lang.String> r2 = r2.zzKI
            com.google.android.gms.internal.zzmn r0 = r0.zzWm
            java.lang.String r0 = r0.zzSJ
            r37 = r2
            r28 = r46
            r31 = r47
            r32 = r48
            r33 = r49
            r34 = r50
            r35 = r51
            r2 = r52
            r21 = r4
            r4 = r54
            r26 = r14
            r19 = r39
            r22 = r41
            r24 = r43
            r14 = r55
            r15 = r56
            r16 = r57
            r17 = r45
            r18 = r58
            r29 = r1
            r30 = r59
            r36 = r60
            r38 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18, r19, r21, r22, r24, r26, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpb.<init>(com.google.android.gms.internal.zzpb$zza, com.google.android.gms.internal.zzqw, com.google.android.gms.internal.zzjq, com.google.android.gms.internal.zzkb, java.lang.String, com.google.android.gms.internal.zzjt, com.google.android.gms.internal.zzha$zza, java.lang.String):void");
    }

    @Nullable
    private static <T> List<T> zzm(@Nullable List<T> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public boolean zzdD() {
        if (this.zzNH == null || this.zzNH.zzlv() == null) {
            return false;
        }
        return this.zzNH.zzlv().zzdD();
    }
}
