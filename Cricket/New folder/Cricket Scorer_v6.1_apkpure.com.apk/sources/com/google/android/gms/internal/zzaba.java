package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzz;

@Deprecated
public final class zzaba {
    private static zzaba zzaCM;
    private static final Object zztX = new Object();
    private final String mAppId;
    private final Status zzaCN;
    private final boolean zzaCO;
    private final boolean zzaCP;

    zzaba(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        boolean z = true;
        if (identifier != 0) {
            z = resources.getInteger(identifier) == 0 ? false : z;
            this.zzaCP = !z;
        } else {
            this.zzaCP = false;
        }
        this.zzaCO = z;
        String zzaV = zzz.zzaV(context);
        zzaV = zzaV == null ? new zzam(context).getString("google_app_id") : zzaV;
        if (TextUtils.isEmpty(zzaV)) {
            this.zzaCN = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.mAppId = null;
            return;
        }
        this.mAppId = zzaV;
        this.zzaCN = Status.zzazx;
    }

    public static Status zzaQ(Context context) {
        Status status;
        zzac.zzb(context, (Object) "Context must not be null.");
        synchronized (zztX) {
            if (zzaCM == null) {
                zzaCM = new zzaba(context);
            }
            status = zzaCM.zzaCN;
        }
        return status;
    }

    private static zzaba zzde(String str) {
        zzaba zzaba;
        synchronized (zztX) {
            if (zzaCM == null) {
                StringBuilder sb = new StringBuilder(34 + String.valueOf(str).length());
                sb.append("Initialize must be called before ");
                sb.append(str);
                sb.append(".");
                throw new IllegalStateException(sb.toString());
            }
            zzaba = zzaCM;
        }
        return zzaba;
    }

    public static String zzwQ() {
        return zzde("getGoogleAppId").mAppId;
    }

    public static boolean zzwR() {
        return zzde("isMeasurementExplicitlyDisabled").zzaCP;
    }
}
