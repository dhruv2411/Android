package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import java.io.File;

public class zzad {
    public static zzm zza(Context context) {
        return zza(context, (zzz) null);
    }

    public static zzm zza(Context context, zzz zzz) {
        String str;
        File file = new File(context.getCacheDir(), "volley");
        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            str = packageName + "/" + packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            str = "volley/0";
        }
        if (zzz == null) {
            zzz = Build.VERSION.SDK_INT >= 9 ? new zzaa() : new zzx(AndroidHttpClient.newInstance(str));
        }
        zzm zzm = new zzm(new zzw(file), new zzu(zzz));
        zzm.start();
        return zzm;
    }
}
