package com.google.android.gms.internal;

import com.google.android.gms.internal.zzso;

public class zztk extends zzso<zztl> {

    private static class zza extends zzrz implements zzso.zza<zztl> {
        private final zztl zzagN = new zztl();

        public zza(zzsc zzsc) {
            super(zzsc);
        }

        public void zzd(String str, int i) {
            if ("ga_sessionTimeout".equals(str)) {
                this.zzagN.zzagP = i;
            } else {
                zzd("int configuration name not recognized", str);
            }
        }

        public void zze(String str, boolean z) {
            if ("ga_autoActivityTracking".equals(str)) {
                this.zzagN.zzagQ = z;
            } else if ("ga_anonymizeIp".equals(str)) {
                this.zzagN.zzagR = z;
            } else if ("ga_reportUncaughtExceptions".equals(str)) {
                this.zzagN.zzagS = z ? 1 : 0;
            } else {
                zzd("bool configuration name not recognized", str);
            }
        }

        public void zzp(String str, String str2) {
            this.zzagN.zzagT.put(str, str2);
        }

        public void zzq(String str, String str2) {
            if ("ga_trackingId".equals(str)) {
                this.zzagN.zzabs = str2;
            } else if ("ga_sampleFrequency".equals(str)) {
                try {
                    this.zzagN.zzagO = Double.parseDouble(str2);
                } catch (NumberFormatException e) {
                    zzc("Error parsing ga_sampleFrequency value", str2, e);
                }
            } else {
                zzd("string configuration name not recognized", str);
            }
        }

        /* renamed from: zzqr */
        public zztl zzoV() {
            return this.zzagN;
        }
    }

    public zztk(zzsc zzsc) {
        super(zzsc, new zza(zzsc));
    }
}
