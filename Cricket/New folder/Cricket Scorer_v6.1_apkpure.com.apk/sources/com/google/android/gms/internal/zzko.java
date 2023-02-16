package com.google.android.gms.internal;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.zza;
import java.util.Date;
import java.util.HashSet;

@zzme
public final class zzko {

    /* renamed from: com.google.android.gms.internal.zzko$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] zzLM = new int[AdRequest.Gender.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|5|6|7|9|10|(2:11|12)|13|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|12|13|15|16|17|18|19|20|22) */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0052 */
        static {
            /*
                com.google.ads.AdRequest$ErrorCode[] r0 = com.google.ads.AdRequest.ErrorCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                zzLN = r0
                r0 = 1
                int[] r1 = zzLN     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.ads.AdRequest$ErrorCode r2 = com.google.ads.AdRequest.ErrorCode.INTERNAL_ERROR     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = zzLN     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.ads.AdRequest$ErrorCode r3 = com.google.ads.AdRequest.ErrorCode.INVALID_REQUEST     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = zzLN     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.ads.AdRequest$ErrorCode r4 = com.google.ads.AdRequest.ErrorCode.NETWORK_ERROR     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = zzLN     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.ads.AdRequest$ErrorCode r4 = com.google.ads.AdRequest.ErrorCode.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                com.google.ads.AdRequest$Gender[] r3 = com.google.ads.AdRequest.Gender.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                zzLM = r3
                int[] r3 = zzLM     // Catch:{ NoSuchFieldError -> 0x0048 }
                com.google.ads.AdRequest$Gender r4 = com.google.ads.AdRequest.Gender.FEMALE     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                int[] r0 = zzLM     // Catch:{ NoSuchFieldError -> 0x0052 }
                com.google.ads.AdRequest$Gender r3 = com.google.ads.AdRequest.Gender.MALE     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                int[] r0 = zzLM     // Catch:{ NoSuchFieldError -> 0x005c }
                com.google.ads.AdRequest$Gender r1 = com.google.ads.AdRequest.Gender.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzko.AnonymousClass1.<clinit>():void");
        }
    }

    public static AdRequest.Gender zzI(int i) {
        switch (i) {
            case 1:
                return AdRequest.Gender.MALE;
            case 2:
                return AdRequest.Gender.FEMALE;
            default:
                return AdRequest.Gender.UNKNOWN;
        }
    }

    public static int zza(AdRequest.ErrorCode errorCode) {
        switch (errorCode) {
            case INVALID_REQUEST:
                return 1;
            case NETWORK_ERROR:
                return 2;
            case NO_FILL:
                return 3;
            default:
                return 0;
        }
    }

    public static AdSize zzc(zzeg zzeg) {
        AdSize[] adSizeArr = {AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
        for (int i = 0; i < 6; i++) {
            if (adSizeArr[i].getWidth() == zzeg.width && adSizeArr[i].getHeight() == zzeg.height) {
                return adSizeArr[i];
            }
        }
        return new AdSize(zza.zza(zzeg.width, zzeg.height, zzeg.zzzy));
    }

    public static MediationAdRequest zzr(zzec zzec) {
        return new MediationAdRequest(new Date(zzec.zzyT), zzI(zzec.zzyU), zzec.zzyV != null ? new HashSet(zzec.zzyV) : null, zzec.zzyW, zzec.zzzb);
    }
}
