package com.google.android.gms.internal;

import android.content.Context;

public class zzade {
    private static Context zzaIy;
    private static Boolean zzaIz;

    public static synchronized boolean zzbg(Context context) {
        synchronized (zzade.class) {
            Context applicationContext = context.getApplicationContext();
            if (zzaIy == null || zzaIz == null || zzaIy != applicationContext) {
                zzaIz = null;
                try {
                    context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                    zzaIz = true;
                } catch (ClassNotFoundException unused) {
                    zzaIz = false;
                }
                zzaIy = applicationContext;
                boolean booleanValue = zzaIz.booleanValue();
                return booleanValue;
            }
            boolean booleanValue2 = zzaIz.booleanValue();
            return booleanValue2;
        }
    }
}
