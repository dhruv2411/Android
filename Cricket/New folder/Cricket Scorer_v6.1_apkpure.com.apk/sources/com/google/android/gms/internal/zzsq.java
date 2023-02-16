package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

class zzsq implements Logger {
    private boolean zzabL;
    private int zzafc = 2;

    zzsq() {
    }

    public void error(Exception exc) {
    }

    public void error(String str) {
    }

    public int getLogLevel() {
        return this.zzafc;
    }

    public void info(String str) {
    }

    public void setLogLevel(int i) {
        this.zzafc = i;
        if (!this.zzabL) {
            String str = zzsw.zzafl.get();
            StringBuilder sb = new StringBuilder(91 + String.valueOf(str).length());
            sb.append("Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.");
            sb.append(str);
            sb.append(" DEBUG");
            Log.i(zzsw.zzafl.get(), sb.toString());
            this.zzabL = true;
        }
    }

    public void verbose(String str) {
    }

    public void warn(String str) {
    }
}
