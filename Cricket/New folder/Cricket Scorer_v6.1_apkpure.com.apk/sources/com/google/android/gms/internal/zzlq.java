package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzpb;

@zzme
public class zzlq {

    public interface zza {
        void zzb(zzpb zzpb);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.android.gms.internal.zzpq, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r0v15, types: [com.google.android.gms.internal.zzlv] */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r0v16, types: [com.google.android.gms.internal.zzlu] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzpq zza(android.content.Context r9, com.google.android.gms.ads.internal.zza r10, com.google.android.gms.internal.zzpb.zza r11, com.google.android.gms.internal.zzaw r12, @android.support.annotation.Nullable com.google.android.gms.internal.zzqw r13, com.google.android.gms.internal.zzka r14, com.google.android.gms.internal.zzlq.zza r15, com.google.android.gms.internal.zzgl r16) {
        /*
            r8 = this;
            r1 = r9
            r0 = r10
            r3 = r11
            r6 = r13
            r5 = r15
            com.google.android.gms.internal.zzmn r2 = r3.zzWm
            boolean r4 = r2.zzSn
            if (r4 == 0) goto L_0x0017
            com.google.android.gms.internal.zzlu r7 = new com.google.android.gms.internal.zzlu
            r0 = r7
            r2 = r3
            r3 = r14
            r4 = r5
            r5 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6)
            goto L_0x006a
        L_0x0017:
            boolean r4 = r2.zzzB
            if (r4 != 0) goto L_0x0050
            boolean r4 = r0 instanceof com.google.android.gms.ads.internal.zzs
            if (r4 == 0) goto L_0x0020
            goto L_0x0050
        L_0x0020:
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.internal.zzgd.zzCu
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x004a
            boolean r0 = com.google.android.gms.common.util.zzt.zzzl()
            if (r0 == 0) goto L_0x004a
            boolean r0 = com.google.android.gms.common.util.zzt.zzzn()
            if (r0 != 0) goto L_0x004a
            if (r6 == 0) goto L_0x004a
            com.google.android.gms.internal.zzeg r0 = r6.zzbC()
            boolean r0 = r0.zzzz
            if (r0 == 0) goto L_0x004a
            com.google.android.gms.internal.zzlt r7 = new com.google.android.gms.internal.zzlt
            r7.<init>(r1, r3, r6, r5)
            goto L_0x006a
        L_0x004a:
            com.google.android.gms.internal.zzlr r7 = new com.google.android.gms.internal.zzlr
            r7.<init>(r1, r3, r6, r5)
            goto L_0x006a
        L_0x0050:
            boolean r2 = r2.zzzB
            if (r2 == 0) goto L_0x0065
            boolean r2 = r0 instanceof com.google.android.gms.ads.internal.zzs
            if (r2 == 0) goto L_0x0065
            com.google.android.gms.internal.zzlv r7 = new com.google.android.gms.internal.zzlv
            r2 = r0
            com.google.android.gms.ads.internal.zzs r2 = (com.google.android.gms.ads.internal.zzs) r2
            r0 = r7
            r4 = r12
            r6 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6)
            goto L_0x006a
        L_0x0065:
            com.google.android.gms.internal.zzls r7 = new com.google.android.gms.internal.zzls
            r7.<init>(r3, r5)
        L_0x006a:
            java.lang.String r0 = "AdRenderer: "
            java.lang.Class r1 = r7.getClass()
            java.lang.String r1 = r1.getName()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0083
            java.lang.String r0 = r0.concat(r1)
            goto L_0x0089
        L_0x0083:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            r0 = r1
        L_0x0089:
            com.google.android.gms.internal.zzpk.zzbf(r0)
            r7.zziP()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlq.zza(android.content.Context, com.google.android.gms.ads.internal.zza, com.google.android.gms.internal.zzpb$zza, com.google.android.gms.internal.zzaw, com.google.android.gms.internal.zzqw, com.google.android.gms.internal.zzka, com.google.android.gms.internal.zzlq$zza, com.google.android.gms.internal.zzgl):com.google.android.gms.internal.zzpq");
    }

    public zzpq zza(Context context, zzpb.zza zza2, zzns zzns) {
        zzok zzok = new zzok(context, zza2, zzns);
        String valueOf = String.valueOf(zzok.getClass().getName());
        zzpk.zzbf(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
        zzok.zziP();
        return zzok;
    }
}
