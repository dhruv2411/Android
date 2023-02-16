package com.google.firebase.messaging;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzbxs;
import com.google.android.gms.internal.zzbxz;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class zzc {
    @Nullable
    static zzbxz.zzb zzU(@NonNull byte[] bArr) {
        try {
            return zzbxz.zzb.zzak(bArr);
        } catch (zzbxs unused) {
            return null;
        }
    }

    static int zza(@NonNull zzbxz.zzb zzb, int i) {
        if (zzb.zzcwa != 0) {
            return zzb.zzcwa;
        }
        if (i != 0) {
            return i;
        }
        return 1;
    }

    static Bundle zza(@NonNull zzbxz.zzb zzb) {
        return zzam(zzb.zzcvP, zzb.zzcvQ);
    }

    @Nullable
    static Object zza(@NonNull zzbxz.zzb zzb, @NonNull String str, @NonNull zzb zzb2) {
        Object obj;
        String str2 = null;
        try {
            Class<?> cls = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            Bundle zza = zza(zzb);
            obj = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            try {
                cls.getField("mOrigin").set(obj, str);
                cls.getField("mCreationTimestamp").set(obj, Long.valueOf(zzb.zzcvR));
                cls.getField("mName").set(obj, zzb.zzcvP);
                cls.getField("mValue").set(obj, zzb.zzcvQ);
                if (!TextUtils.isEmpty(zzb.zzcvS)) {
                    str2 = zzb.zzcvS;
                }
                cls.getField("mTriggerEventName").set(obj, str2);
                cls.getField("mTimedOutEventName").set(obj, zzd(zzb, zzb2));
                cls.getField("mTimedOutEventParams").set(obj, zza);
                cls.getField("mTriggerTimeout").set(obj, Integer.valueOf(zzb.zzcvT));
                cls.getField("mTriggeredEventName").set(obj, zzb(zzb, zzb2));
                cls.getField("mTriggeredEventParams").set(obj, zza);
                cls.getField("mTimeToLive").set(obj, Integer.valueOf(zzb.zzcvU));
                cls.getField("mExpiredEventName").set(obj, zze(zzb, zzb2));
                cls.getField("mExpiredEventParams").set(obj, zza);
                return obj;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
                e = e;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return obj;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e2) {
            e = e2;
            obj = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return obj;
        }
    }

    static String zza(@NonNull zzbxz.zzb zzb, @NonNull zzb zzb2) {
        return !TextUtils.isEmpty(zzb.zzcvV) ? zzb.zzcvV : zzb2.zzUZ();
    }

    public static void zza(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String valueOf = String.valueOf(str);
            Log.v("FirebaseAbtUtil", valueOf.length() != 0 ? "_CE(experimentId) called by ".concat(valueOf) : new String("_CE(experimentId) called by "));
        }
        if (zzco(context)) {
            AppMeasurement zzbj = zzbj(context);
            try {
                Method declaredMethod = AppMeasurement.class.getDeclaredMethod("clearConditionalUserProperty", new Class[]{String.class, String.class, Bundle.class});
                declaredMethod.setAccessible(true);
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    StringBuilder sb = new StringBuilder(17 + String.valueOf(str2).length() + String.valueOf(str3).length());
                    sb.append("Clearing _E: [");
                    sb.append(str2);
                    sb.append(", ");
                    sb.append(str3);
                    sb.append("]");
                    Log.v("FirebaseAbtUtil", sb.toString());
                }
                declaredMethod.invoke(zzbj, new Object[]{str2, str4, zzam(str2, str3)});
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
    }

    public static void zza(@NonNull Context context, @NonNull String str, @NonNull byte[] bArr, @NonNull zzb zzb, int i) {
        boolean z;
        String str2 = str;
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String valueOf = String.valueOf(str);
            Log.v("FirebaseAbtUtil", valueOf.length() != 0 ? "_SE called by ".concat(valueOf) : new String("_SE called by "));
        }
        if (zzco(context)) {
            AppMeasurement zzbj = zzbj(context);
            zzbxz.zzb zzU = zzU(bArr);
            if (zzU != null) {
                try {
                    Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                    boolean z2 = false;
                    for (Object next : zzb(zzbj, str2)) {
                        String zzab = zzab(next);
                        String zzac = zzac(next);
                        long zzaI = zzaI(next);
                        if (!zzU.zzcvP.equals(zzab) || !zzU.zzcvQ.equals(zzac)) {
                            zzbxz.zza[] zzaArr = zzU.zzcwb;
                            int length = zzaArr.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= length) {
                                    z = false;
                                    break;
                                } else if (zzaArr[i2].zzcvP.equals(zzab)) {
                                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                        StringBuilder sb = new StringBuilder(33 + String.valueOf(zzab).length() + String.valueOf(zzac).length());
                                        sb.append("_E is found in the _OE list. [");
                                        sb.append(zzab);
                                        sb.append(", ");
                                        sb.append(zzac);
                                        sb.append("]");
                                        Log.v("FirebaseAbtUtil", sb.toString());
                                    }
                                    z = true;
                                } else {
                                    i2++;
                                }
                            }
                            if (z) {
                                Context context2 = context;
                                zzb zzb2 = zzb;
                            } else if (zzU.zzcvR > zzaI) {
                                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                    StringBuilder sb2 = new StringBuilder(115 + String.valueOf(zzab).length() + String.valueOf(zzac).length());
                                    sb2.append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [");
                                    sb2.append(zzab);
                                    sb2.append(", ");
                                    sb2.append(zzac);
                                    sb2.append("]");
                                    Log.v("FirebaseAbtUtil", sb2.toString());
                                }
                                zza(context, str2, zzab, zzac, zzc(zzU, zzb));
                            } else {
                                Context context3 = context;
                                zzb zzb3 = zzb;
                                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                    StringBuilder sb3 = new StringBuilder(109 + String.valueOf(zzab).length() + String.valueOf(zzac).length());
                                    sb3.append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [");
                                    sb3.append(zzab);
                                    sb3.append(", ");
                                    sb3.append(zzac);
                                    sb3.append("]");
                                    Log.v("FirebaseAbtUtil", sb3.toString());
                                }
                            }
                        } else {
                            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                StringBuilder sb4 = new StringBuilder(23 + String.valueOf(zzab).length() + String.valueOf(zzac).length());
                                sb4.append("_E is already set. [");
                                sb4.append(zzab);
                                sb4.append(", ");
                                sb4.append(zzac);
                                sb4.append("]");
                                Log.v("FirebaseAbtUtil", sb4.toString());
                            }
                            z2 = true;
                        }
                    }
                    Context context4 = context;
                    zzb zzb4 = zzb;
                    if (!z2) {
                        zza(zzbj, context4, str2, zzU, zzb4, i);
                    } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        String valueOf2 = String.valueOf(zzU.zzcvP);
                        String valueOf3 = String.valueOf(zzU.zzcvQ);
                        StringBuilder sb5 = new StringBuilder(44 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
                        sb5.append("_E is already set. Not setting it again [");
                        sb5.append(valueOf2);
                        sb5.append(", ");
                        sb5.append(valueOf3);
                        sb5.append("]");
                        Log.v("FirebaseAbtUtil", sb5.toString());
                    }
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                }
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "_SE failed; either _P was not set, or we couldn't deserialize the _P.");
            }
        }
    }

    static void zza(@NonNull AppMeasurement appMeasurement, @NonNull Context context, @NonNull String str, @NonNull zzbxz.zzb zzb, @NonNull zzb zzb2, int i) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String valueOf = String.valueOf(zzb.zzcvP);
            String valueOf2 = String.valueOf(zzb.zzcvQ);
            StringBuilder sb = new StringBuilder(7 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append("_SEI: ");
            sb.append(valueOf);
            sb.append(" ");
            sb.append(valueOf2);
            Log.v("FirebaseAbtUtil", sb.toString());
        }
        try {
            Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            List<Object> zzb3 = zzb(appMeasurement, str);
            if (zza(appMeasurement, str)) {
                if (zza(zzb, i) == 1) {
                    Object obj = zzb3.get(0);
                    String zzab = zzab(obj);
                    String zzac = zzac(obj);
                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        StringBuilder sb2 = new StringBuilder(38 + String.valueOf(zzab).length());
                        sb2.append("Clearing _E due to overflow policy: [");
                        sb2.append(zzab);
                        sb2.append("]");
                        Log.v("FirebaseAbtUtil", sb2.toString());
                    }
                    zza(context, str, zzab, zzac, zzc(zzb, zzb2));
                } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    String valueOf3 = String.valueOf(zzb.zzcvP);
                    String valueOf4 = String.valueOf(zzb.zzcvQ);
                    StringBuilder sb3 = new StringBuilder(44 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
                    sb3.append("_E won't be set due to overflow policy. [");
                    sb3.append(valueOf3);
                    sb3.append(", ");
                    sb3.append(valueOf4);
                    sb3.append("]");
                    Log.v("FirebaseAbtUtil", sb3.toString());
                    return;
                } else {
                    return;
                }
            }
            for (Object next : zzb3) {
                String zzab2 = zzab(next);
                String zzac2 = zzac(next);
                if (zzab2.equals(zzb.zzcvP) && !zzac2.equals(zzb.zzcvQ) && Log.isLoggable("FirebaseAbtUtil", 2)) {
                    StringBuilder sb4 = new StringBuilder(77 + String.valueOf(zzab2).length() + String.valueOf(zzac2).length());
                    sb4.append("Clearing _E, as only one _V of the same _E can be set atany given time: [");
                    sb4.append(zzab2);
                    sb4.append(", ");
                    sb4.append(zzac2);
                    sb4.append("].");
                    Log.v("FirebaseAbtUtil", sb4.toString());
                    zza(context, str, zzab2, zzac2, zzc(zzb, zzb2));
                }
            }
            Object zza = zza(zzb, str, zzb2);
            if (zza != null) {
                zza(appMeasurement, zzb, zzb2, zza, str);
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                String valueOf5 = String.valueOf(zzb.zzcvP);
                String valueOf6 = String.valueOf(zzb.zzcvQ);
                StringBuilder sb5 = new StringBuilder(42 + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length());
                sb5.append("Could not create _CUP for: [");
                sb5.append(valueOf5);
                sb5.append(", ");
                sb5.append(valueOf6);
                sb5.append("]. Skipping.");
                Log.v("FirebaseAbtUtil", sb5.toString());
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        }
    }

    static void zza(@NonNull AppMeasurement appMeasurement, @NonNull zzbxz.zzb zzb, @NonNull zzb zzb2, @NonNull Object obj, @NonNull String str) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String valueOf = String.valueOf(zzb.zzcvP);
            String valueOf2 = String.valueOf(zzb.zzcvQ);
            String valueOf3 = String.valueOf(zzb.zzcvS);
            StringBuilder sb = new StringBuilder(27 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
            sb.append("Setting _CUP for _E: [");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(valueOf2);
            sb.append(", ");
            sb.append(valueOf3);
            sb.append("]");
            Log.v("FirebaseAbtUtil", sb.toString());
        }
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("setConditionalUserProperty", new Class[]{Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty")});
            declaredMethod.setAccessible(true);
            appMeasurement.logEventInternal(str, zza(zzb, zzb2), zza(zzb));
            declaredMethod.invoke(appMeasurement, new Object[]{obj});
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        }
    }

    static boolean zza(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        return zzb(appMeasurement, str).size() >= zzc(appMeasurement, str);
    }

    static long zzaI(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return ((Long) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mCreationTimestamp").get(obj)).longValue();
    }

    static String zzab(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mName").get(obj);
    }

    static String zzac(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mValue").get(obj);
    }

    static Bundle zzam(@NonNull String str, @NonNull String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        return bundle;
    }

    static String zzb(@NonNull zzbxz.zzb zzb, @NonNull zzb zzb2) {
        return !TextUtils.isEmpty(zzb.zzcvW) ? zzb.zzcvW : zzb2.zzVa();
    }

    static List<Object> zzb(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        List<Object> list;
        ArrayList arrayList = new ArrayList();
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getConditionalUserProperties", new Class[]{String.class, String.class});
            declaredMethod.setAccessible(true);
            list = (List) declaredMethod.invoke(appMeasurement, new Object[]{str, ""});
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            list = arrayList;
        }
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            int size = list.size();
            StringBuilder sb = new StringBuilder(55 + String.valueOf(str).length());
            sb.append("Number of currently set _Es for origin: ");
            sb.append(str);
            sb.append(" is ");
            sb.append(size);
            Log.v("FirebaseAbtUtil", sb.toString());
        }
        return list;
    }

    @Nullable
    static AppMeasurement zzbj(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError unused) {
            return null;
        }
    }

    static int zzc(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getMaxUserProperties", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(appMeasurement, new Object[]{str})).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return 20;
        }
    }

    static String zzc(@Nullable zzbxz.zzb zzb, @NonNull zzb zzb2) {
        return (zzb == null || TextUtils.isEmpty(zzb.zzcvX)) ? zzb2.zzVd() : zzb.zzcvX;
    }

    private static boolean zzco(Context context) {
        if (zzbj(context) == null) {
            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "Firebase Analytics not available");
            }
            return false;
        }
        try {
            Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            return true;
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "Firebase Analytics library is missing support for abt. Please update to a more recent version.");
            }
            return false;
        }
    }

    static String zzd(@NonNull zzbxz.zzb zzb, @NonNull zzb zzb2) {
        return !TextUtils.isEmpty(zzb.zzcvY) ? zzb.zzcvY : zzb2.zzVb();
    }

    static String zze(@NonNull zzbxz.zzb zzb, @NonNull zzb zzb2) {
        return !TextUtils.isEmpty(zzb.zzcvZ) ? zzb.zzcvZ : zzb2.zzVc();
    }
}
