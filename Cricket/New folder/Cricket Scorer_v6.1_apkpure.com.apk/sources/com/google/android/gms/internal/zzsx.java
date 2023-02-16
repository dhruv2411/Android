package com.google.android.gms.internal;

import com.google.android.gms.internal.zzso;

public class zzsx extends zzso<zzsy> {

    private static class zza implements zzso.zza<zzsy> {
        private final zzsc zzadO;
        private final zzsy zzagc = new zzsy();

        public zza(zzsc zzsc) {
            this.zzadO = zzsc;
        }

        public void zzd(String str, int i) {
            if ("ga_dispatchPeriod".equals(str)) {
                this.zzagc.zzage = i;
            } else {
                this.zzadO.zznS().zzd("Int xml configuration name not recognized", str);
            }
        }

        public void zze(String str, boolean z) {
            if ("ga_dryRun".equals(str)) {
                this.zzagc.zzagf = z ? 1 : 0;
                return;
            }
            this.zzadO.zznS().zzd("Bool xml configuration name not recognized", str);
        }

        public void zzp(String str, String str2) {
        }

        /* renamed from: zzpF */
        public zzsy zzoV() {
            return this.zzagc;
        }

        public void zzq(String str, String str2) {
            if ("ga_appName".equals(str)) {
                this.zzagc.zzacL = str2;
            } else if ("ga_appVersion".equals(str)) {
                this.zzagc.zzacM = str2;
            } else if ("ga_logLevel".equals(str)) {
                this.zzagc.zzagd = str2;
            } else {
                this.zzadO.zznS().zzd("String xml configuration name not recognized", str);
            }
        }
    }

    public zzsx(zzsc zzsc) {
        super(zzsc, new zza(zzsc));
    }
}
