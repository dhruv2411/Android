package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class zzu {
    private static String zzaIn;
    private static final int zzaIo = Process.myPid();

    static String zzdq(int i) {
        BufferedReader bufferedReader;
        StrictMode.ThreadPolicy allowThreadDiskReads;
        BufferedReader bufferedReader2 = null;
        if (i <= 0) {
            return null;
        }
        try {
            allowThreadDiskReads = StrictMode.allowThreadDiskReads();
            StringBuilder sb = new StringBuilder(25);
            sb.append("/proc/");
            sb.append(i);
            sb.append("/cmdline");
            bufferedReader = new BufferedReader(new FileReader(sb.toString()));
            try {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                String trim = bufferedReader.readLine().trim();
                zzp.zzb(bufferedReader);
                return trim;
            } catch (IOException unused) {
                zzp.zzb(bufferedReader);
                return null;
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                zzp.zzb(bufferedReader2);
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
            zzp.zzb(bufferedReader);
            return null;
        } catch (Throwable th2) {
            th = th2;
            zzp.zzb(bufferedReader2);
            throw th;
        }
    }

    public static String zzzr() {
        if (zzaIn == null) {
            zzaIn = zzdq(zzaIo);
        }
        return zzaIn;
    }
}
