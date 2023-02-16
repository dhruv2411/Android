package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

public class zzdi {
    private Context mContext;
    private Tracker zzabD;
    private GoogleAnalytics zzabF;

    static class zza implements Logger {
        zza() {
        }

        private static int zznI(int i) {
            switch (i) {
                case 2:
                    return 0;
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                default:
                    return 3;
            }
        }

        public void error(Exception exc) {
            zzbo.zzb("", exc);
        }

        public void error(String str) {
            zzbo.e(str);
        }

        public int getLogLevel() {
            return zznI(zzbo.getLogLevel());
        }

        public void info(String str) {
            zzbo.zzbg(str);
        }

        public void setLogLevel(int i) {
            zzbo.zzbh("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
        }

        public void verbose(String str) {
            zzbo.v(str);
        }

        public void warn(String str) {
            zzbo.zzbh(str);
        }
    }

    public zzdi(Context context) {
        this.mContext = context;
    }

    private synchronized void zzhu(String str) {
        if (this.zzabF == null) {
            this.zzabF = GoogleAnalytics.getInstance(this.mContext);
            this.zzabF.setLogger(new zza());
            this.zzabD = this.zzabF.newTracker(str);
        }
    }

    public Tracker zzht(String str) {
        zzhu(str);
        return this.zzabD;
    }
}
