package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzak;
import java.util.Map;

class zzh extends zzam {
    private static final String ID = zzah.APP_VERSION.toString();
    private final Context mContext;

    public zzh(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzQb() {
        return true;
    }

    public zzak.zza zzZ(Map<String, zzak.zza> map) {
        try {
            return zzdl.zzR(Integer.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(this.mContext.getPackageName());
            String valueOf2 = String.valueOf(e.getMessage());
            StringBuilder sb = new StringBuilder(25 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append("Package name ");
            sb.append(valueOf);
            sb.append(" not found. ");
            sb.append(valueOf2);
            zzbo.e(sb.toString());
            return zzdl.zzRR();
        }
    }
}
