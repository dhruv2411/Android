package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

class zzdd {
    static void zza(SharedPreferences.Editor editor) {
        int i = Build.VERSION.SDK_INT;
        editor.apply();
    }

    @SuppressLint({"CommitPrefEdits"})
    static void zzd(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        zza(edit);
    }
}
