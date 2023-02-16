package com.google.android.gms.ads;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzel;
import com.itextpdf.text.pdf.codec.TIFFConstants;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(TIFFConstants.TIFFTAG_COLORMAP, 50, "320x50_mb");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER = new AdSize(TIFFConstants.TIFFTAG_COLORMAP, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "300x250_as");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT, "160x600_as");
    public static final AdSize zzrB = new AdSize(50, 50, "50x50_mb");
    private final int zzrC;
    private final int zzrD;
    private final String zzrE;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AdSize(int r6, int r7) {
        /*
            r5 = this;
            r0 = -1
            if (r6 != r0) goto L_0x0006
            java.lang.String r0 = "FULL"
            goto L_0x000a
        L_0x0006:
            java.lang.String r0 = java.lang.String.valueOf(r6)
        L_0x000a:
            r1 = -2
            if (r7 != r1) goto L_0x0010
            java.lang.String r1 = "AUTO"
            goto L_0x0014
        L_0x0010:
            java.lang.String r1 = java.lang.String.valueOf(r7)
        L_0x0014:
            java.lang.String r2 = "_as"
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r3 = 1
            java.lang.String r4 = java.lang.String.valueOf(r0)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.String r4 = java.lang.String.valueOf(r1)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            r4.append(r0)
            java.lang.String r0 = "x"
            r4.append(r0)
            r4.append(r1)
            r4.append(r2)
            java.lang.String r0 = r4.toString()
            r5.<init>(r6, r7, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.AdSize.<init>(int, int):void");
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Invalid width for AdSize: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.zzrC = i;
            this.zzrD = i2;
            this.zzrE = str;
        } else {
            StringBuilder sb2 = new StringBuilder(38);
            sb2.append("Invalid height for AdSize: ");
            sb2.append(i2);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.zzrC == adSize.zzrC && this.zzrD == adSize.zzrD && this.zzrE.equals(adSize.zzrE);
    }

    public int getHeight() {
        return this.zzrD;
    }

    public int getHeightInPixels(Context context) {
        switch (this.zzrD) {
            case -4:
            case -3:
                return -1;
            case -2:
                return zzeg.zzb(context.getResources().getDisplayMetrics());
            default:
                return zzel.zzeT().zzb(context, this.zzrD);
        }
    }

    public int getWidth() {
        return this.zzrC;
    }

    public int getWidthInPixels(Context context) {
        int i = this.zzrC;
        if (i == -1) {
            return zzeg.zza(context.getResources().getDisplayMetrics());
        }
        switch (i) {
            case -4:
            case -3:
                return -1;
            default:
                return zzel.zzeT().zzb(context, this.zzrC);
        }
    }

    public int hashCode() {
        return this.zzrE.hashCode();
    }

    public boolean isAutoHeight() {
        return this.zzrD == -2;
    }

    public boolean isFluid() {
        return this.zzrC == -3 && this.zzrD == -4;
    }

    public boolean isFullWidth() {
        return this.zzrC == -1;
    }

    public String toString() {
        return this.zzrE;
    }
}
