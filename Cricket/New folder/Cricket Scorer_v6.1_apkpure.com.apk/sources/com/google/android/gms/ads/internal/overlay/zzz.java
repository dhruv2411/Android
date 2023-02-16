package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpu;
import com.google.android.gms.internal.zzqh;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import java.util.concurrent.TimeUnit;

@zzme
public class zzz {
    private final Context mContext;
    @Nullable
    private final zzgl zzNU;
    private boolean zzNY;
    private final String zzOV;
    @Nullable
    private final zzgj zzOW;
    private final zzpu zzOX = new zzpu.zzb().zza("min_1", Double.MIN_VALUE, 1.0d).zza("1_5", 1.0d, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zzla();
    private final long[] zzOY;
    private final String[] zzOZ;
    private boolean zzPa;
    private boolean zzPb;
    private boolean zzPc;
    private boolean zzPd;
    private zzj zzPe;
    private boolean zzPf;
    private boolean zzPg;
    private long zzPh;
    private final zzqh zzuc;

    public zzz(Context context, zzqh zzqh, String str, @Nullable zzgl zzgl, @Nullable zzgj zzgj) {
        this.zzPa = false;
        this.zzPb = false;
        this.zzPc = false;
        this.zzPd = false;
        this.zzPh = -1;
        this.mContext = context;
        this.zzuc = zzqh;
        this.zzOV = str;
        this.zzNU = zzgl;
        this.zzOW = zzgj;
        String str2 = zzgd.zzBE.get();
        if (str2 == null) {
            this.zzOZ = new String[0];
            this.zzOY = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.zzOZ = new String[split.length];
        this.zzOY = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                this.zzOY[i] = Long.parseLong(split[i]);
            } catch (NumberFormatException e) {
                zzpk.zzc("Unable to parse frame hash target time number.", e);
                this.zzOY[i] = -1;
            }
        }
    }

    private void zzc(zzj zzj) {
        long longValue = zzgd.zzBF.get().longValue();
        long currentPosition = (long) zzj.getCurrentPosition();
        for (int i = 0; i < this.zzOZ.length; i++) {
            if (this.zzOZ[i] == null && longValue > Math.abs(currentPosition - this.zzOY[i])) {
                this.zzOZ[i] = zza((TextureView) zzj);
                return;
            }
        }
    }

    private void zziw() {
        if (this.zzPc && !this.zzPd) {
            zzgh.zza(this.zzNU, this.zzOW, "vff2");
            this.zzPd = true;
        }
        long nanoTime = zzw.zzcS().nanoTime();
        if (this.zzNY && this.zzPg && this.zzPh != -1) {
            this.zzOX.zza(((double) TimeUnit.SECONDS.toNanos(1)) / ((double) (nanoTime - this.zzPh)));
        }
        this.zzPg = this.zzNY;
        this.zzPh = nanoTime;
    }

    public void onStop() {
        if (zzgd.zzBD.get().booleanValue() && !this.zzPf) {
            Bundle bundle = new Bundle();
            bundle.putString(DublinCoreProperties.TYPE, "native-player-metrics");
            bundle.putString("request", this.zzOV);
            bundle.putString(DBHelper.TABLE_PLAYERS, this.zzPe.zzhy());
            for (zzpu.zza next : this.zzOX.getBuckets()) {
                String valueOf = String.valueOf("fps_c_");
                String valueOf2 = String.valueOf(next.name);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Integer.toString(next.count));
                String valueOf3 = String.valueOf("fps_p_");
                String valueOf4 = String.valueOf(next.name);
                bundle.putString(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), Double.toString(next.zzYi));
            }
            for (int i = 0; i < this.zzOY.length; i++) {
                String str = this.zzOZ[i];
                if (str != null) {
                    String valueOf5 = String.valueOf("fh_");
                    String valueOf6 = String.valueOf(Long.valueOf(this.zzOY[i]));
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length());
                    sb.append(valueOf5);
                    sb.append(valueOf6);
                    bundle.putString(sb.toString(), str);
                }
            }
            zzw.zzcM().zza(this.mContext, this.zzuc.zzba, "gmob-apps", bundle, true);
            this.zzPf = true;
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(14)
    public String zza(TextureView textureView) {
        Bitmap bitmap = textureView.getBitmap(8, 8);
        long j = 63;
        long j2 = 0;
        int i = 0;
        while (i < 8) {
            long j3 = j;
            long j4 = j2;
            int i2 = 0;
            while (i2 < 8) {
                int pixel = bitmap.getPixel(i2, i);
                long j5 = j4 | (((Color.blue(pixel) + Color.red(pixel)) + Color.green(pixel) > 128 ? 1 : 0) << ((int) j3));
                i2++;
                j3--;
                j4 = j5;
            }
            i++;
            j2 = j4;
            j = j3;
        }
        return String.format("%016X", new Object[]{Long.valueOf(j2)});
    }

    public void zza(zzj zzj) {
        zzgh.zza(this.zzNU, this.zzOW, "vpc2");
        this.zzPa = true;
        if (this.zzNU != null) {
            this.zzNU.zzh("vpn", zzj.zzhy());
        }
        this.zzPe = zzj;
    }

    public void zzb(zzj zzj) {
        zziw();
        zzc(zzj);
    }

    public void zzhU() {
        if (this.zzPa && !this.zzPb) {
            zzgh.zza(this.zzNU, this.zzOW, "vfr2");
            this.zzPb = true;
        }
    }

    public void zzix() {
        this.zzNY = true;
        if (this.zzPb && !this.zzPc) {
            zzgh.zza(this.zzNU, this.zzOW, "vfp2");
            this.zzPc = true;
        }
    }

    public void zziy() {
        this.zzNY = false;
    }
}
