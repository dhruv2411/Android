package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzme
public class zzeg extends zza {
    public static final Parcelable.Creator<zzeg> CREATOR = new zzeh();
    public final int height;
    public final int heightPixels;
    public final int width;
    public final int widthPixels;
    public final zzeg[] zzzA;
    public final boolean zzzB;
    public final boolean zzzC;
    public boolean zzzD;
    public final String zzzy;
    public final boolean zzzz;

    public zzeg() {
        this("interstitial_mb", 0, 0, true, 0, 0, (zzeg[]) null, false, false, false);
    }

    public zzeg(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    public zzeg(Context context, AdSize[] adSizeArr) {
        int height2;
        int i;
        String str;
        AdSize adSize = adSizeArr[0];
        this.zzzz = false;
        this.zzzC = adSize.isFluid();
        if (this.zzzC) {
            this.width = AdSize.BANNER.getWidth();
            height2 = AdSize.BANNER.getHeight();
        } else {
            this.width = adSize.getWidth();
            height2 = adSize.getHeight();
        }
        this.height = height2;
        boolean z = this.width == -1;
        boolean z2 = this.height == -2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            this.widthPixels = (!zzel.zzeT().zzah(context) || !zzel.zzeT().zzai(context)) ? zza(displayMetrics) : zza(displayMetrics) - zzel.zzeT().zzaj(context);
            double d = (double) (((float) this.widthPixels) / displayMetrics.density);
            i = (int) d;
            if (d - ((double) i) >= 0.01d) {
                i++;
            }
        } else {
            i = this.width;
            this.widthPixels = zzel.zzeT().zza(displayMetrics, this.width);
        }
        int zzc = z2 ? zzc(displayMetrics) : this.height;
        this.heightPixels = zzel.zzeT().zza(displayMetrics, zzc);
        if (z || z2) {
            StringBuilder sb = new StringBuilder(26);
            sb.append(i);
            sb.append("x");
            sb.append(zzc);
            sb.append("_as");
            str = sb.toString();
        } else {
            str = this.zzzC ? "320x50_mb" : adSize.toString();
        }
        this.zzzy = str;
        if (adSizeArr.length > 1) {
            this.zzzA = new zzeg[adSizeArr.length];
            for (int i2 = 0; i2 < adSizeArr.length; i2++) {
                this.zzzA[i2] = new zzeg(context, adSizeArr[i2]);
            }
        } else {
            this.zzzA = null;
        }
        this.zzzB = false;
        this.zzzD = false;
    }

    public zzeg(zzeg zzeg, zzeg[] zzegArr) {
        this(zzeg.zzzy, zzeg.height, zzeg.heightPixels, zzeg.zzzz, zzeg.width, zzeg.widthPixels, zzegArr, zzeg.zzzB, zzeg.zzzC, zzeg.zzzD);
    }

    zzeg(String str, int i, int i2, boolean z, int i3, int i4, zzeg[] zzegArr, boolean z2, boolean z3, boolean z4) {
        this.zzzy = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzzz = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzzA = zzegArr;
        this.zzzB = z2;
        this.zzzC = z3;
        this.zzzD = z4;
    }

    public static int zza(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzb(DisplayMetrics displayMetrics) {
        return (int) (((float) zzc(displayMetrics)) * displayMetrics.density);
    }

    private static int zzc(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public static zzeg zzeE() {
        return new zzeg("reward_mb", 0, 0, true, 0, 0, (zzeg[]) null, false, false, false);
    }

    public static zzeg zzk(Context context) {
        return new zzeg("320x50_mb", 0, 0, false, 0, 0, (zzeg[]) null, true, false, false);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzeh.zza(this, parcel, i);
    }

    public AdSize zzeF() {
        return com.google.android.gms.ads.zza.zza(this.width, this.height, this.zzzy);
    }

    public void zzl(boolean z) {
        this.zzzD = z;
    }
}
