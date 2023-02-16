package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.zzac;
import java.util.Random;

public class zzq {
    private final Context mContext;
    private final String zzbEU;
    private final Random zzzW;

    public zzq(Context context, String str) {
        this(context, str, new Random());
    }

    @VisibleForTesting
    zzq(Context context, String str, Random random) {
        this.mContext = (Context) zzac.zzw(context);
        this.zzbEU = (String) zzac.zzw(str);
        this.zzzW = random;
    }

    private SharedPreferences zzQu() {
        Context context = this.mContext;
        String valueOf = String.valueOf("_gtmContainerRefreshPolicy_");
        String valueOf2 = String.valueOf(this.zzbEU);
        return context.getSharedPreferences(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), 0);
    }

    private long zzi(long j, long j2) {
        SharedPreferences zzQu = zzQu();
        long max = Math.max(0, zzQu.getLong("FORBIDDEN_COUNT", 0));
        return (long) (this.zzzW.nextFloat() * ((float) (j + ((long) ((((float) max) / ((float) ((max + Math.max(0, zzQu.getLong("SUCCESSFUL_COUNT", 0))) + 1))) * ((float) (j2 - j)))))));
    }

    public long zzQq() {
        return 43200000 + zzi(7200000, 259200000);
    }

    public long zzQr() {
        return 3600000 + zzi(600000, 86400000);
    }

    @SuppressLint({"CommitPrefEdits"})
    public void zzQs() {
        SharedPreferences zzQu = zzQu();
        long j = zzQu.getLong("FORBIDDEN_COUNT", 0);
        long j2 = zzQu.getLong("SUCCESSFUL_COUNT", 0);
        SharedPreferences.Editor edit = zzQu.edit();
        long min = j == 0 ? 3 : Math.min(10, j + 1);
        long max = Math.max(0, Math.min(j2, 10 - min));
        edit.putLong("FORBIDDEN_COUNT", min);
        edit.putLong("SUCCESSFUL_COUNT", max);
        zzdd.zza(edit);
    }

    @SuppressLint({"CommitPrefEdits"})
    public void zzQt() {
        SharedPreferences zzQu = zzQu();
        long j = zzQu.getLong("SUCCESSFUL_COUNT", 0);
        long j2 = zzQu.getLong("FORBIDDEN_COUNT", 0);
        long min = Math.min(10, j + 1);
        long max = Math.max(0, Math.min(j2, 10 - min));
        SharedPreferences.Editor edit = zzQu.edit();
        edit.putLong("SUCCESSFUL_COUNT", min);
        edit.putLong("FORBIDDEN_COUNT", max);
        zzdd.zza(edit);
    }
}
