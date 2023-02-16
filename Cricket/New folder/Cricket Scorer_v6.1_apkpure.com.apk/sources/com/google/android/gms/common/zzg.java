package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzy;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzadg;
import com.itextpdf.text.pdf.codec.TIFFConstants;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class zzg {
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 10298000;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    private static boolean zzayA = false;
    static final AtomicBoolean zzayB = new AtomicBoolean();
    private static final AtomicBoolean zzayC = new AtomicBoolean();
    public static boolean zzayx = false;
    public static boolean zzayy = false;
    static boolean zzayz = false;

    zzg() {
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return zze.zzuY().getErrorResolutionPendingIntent(context, i, i2);
    }

    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.getStatusString(i);
    }

    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(Context context) {
        InputStream openInputStream;
        try {
            openInputStream = context.getContentResolver().openInputStream(new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build());
            String next = new Scanner(openInputStream).useDelimiter("\\A").next();
            if (openInputStream != null) {
                openInputStream.close();
            }
            return next;
        } catch (NoSuchElementException unused) {
            if (openInputStream != null) {
                openInputStream.close();
            }
            return null;
        } catch (Exception unused2) {
            return null;
        } catch (Throwable th) {
            if (openInputStream != null) {
                openInputStream.close();
            }
            throw th;
        }
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006e, code lost:
        if (r8.zza(r6, r1) == null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007b, code lost:
        if (r8.zza(r6, com.google.android.gms.common.zzf.zzd.zzayw) == null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008a, code lost:
        if (com.google.android.gms.common.util.zzm.zzdp(r6.versionCode) >= com.google.android.gms.common.util.zzm.zzdp(GOOGLE_PLAY_SERVICES_VERSION_CODE)) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008c, code lost:
        r0 = GOOGLE_PLAY_SERVICES_VERSION_CODE;
        r1 = r6.versionCode;
        r3 = new java.lang.StringBuilder(77);
        r3.append("Google Play services out of date.  Requires ");
        r3.append(r0);
        r3.append(" but found ");
        r3.append(r1);
        android.util.Log.w("GooglePlayServicesUtil", r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b1, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b2, code lost:
        r8 = r6.applicationInfo;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b4, code lost:
        if (r8 != null) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r8 = r0.getApplicationInfo("com.google.android.gms", 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bd, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00be, code lost:
        android.util.Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c5, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c8, code lost:
        if (r8.enabled != false) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ca, code lost:
        return 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cc, code lost:
        return 0;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int isGooglePlayServicesAvailable(android.content.Context r8) {
        /*
            android.content.pm.PackageManager r0 = r8.getPackageManager()
            android.content.res.Resources r1 = r8.getResources()     // Catch:{ Throwable -> 0x000e }
            int r2 = com.google.android.gms.R.string.common_google_play_services_unknown_issue     // Catch:{ Throwable -> 0x000e }
            r1.getString(r2)     // Catch:{ Throwable -> 0x000e }
            goto L_0x0015
        L_0x000e:
            java.lang.String r1 = "GooglePlayServicesUtil"
            java.lang.String r2 = "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included."
            android.util.Log.e(r1, r2)
        L_0x0015:
            java.lang.String r1 = "com.google.android.gms"
            java.lang.String r2 = r8.getPackageName()
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0024
            zzaH(r8)
        L_0x0024:
            boolean r1 = com.google.android.gms.common.util.zzj.zzba(r8)
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0034
            boolean r1 = com.google.android.gms.common.util.zzj.zzbc(r8)
            if (r1 != 0) goto L_0x0034
            r1 = r3
            goto L_0x0035
        L_0x0034:
            r1 = r2
        L_0x0035:
            r4 = 0
            r5 = 9
            if (r1 == 0) goto L_0x004b
            java.lang.String r4 = "com.android.vending"
            r6 = 8256(0x2040, float:1.1569E-41)
            android.content.pm.PackageInfo r4 = r0.getPackageInfo(r4, r6)     // Catch:{ NameNotFoundException -> 0x0043 }
            goto L_0x004b
        L_0x0043:
            java.lang.String r8 = "GooglePlayServicesUtil"
            java.lang.String r0 = "Google Play Store is missing."
        L_0x0047:
            android.util.Log.w(r8, r0)
            return r5
        L_0x004b:
            java.lang.String r6 = "com.google.android.gms"
            r7 = 64
            android.content.pm.PackageInfo r6 = r0.getPackageInfo(r6, r7)     // Catch:{ NameNotFoundException -> 0x00cd }
            com.google.android.gms.common.zzh r8 = com.google.android.gms.common.zzh.zzaN(r8)
            if (r1 == 0) goto L_0x0075
            com.google.android.gms.common.zzf$zza[] r1 = com.google.android.gms.common.zzf.zzd.zzayw
            com.google.android.gms.common.zzf$zza r1 = r8.zza((android.content.pm.PackageInfo) r4, (com.google.android.gms.common.zzf.zza[]) r1)
            if (r1 != 0) goto L_0x0066
            java.lang.String r8 = "GooglePlayServicesUtil"
            java.lang.String r0 = "Google Play Store signature invalid."
            goto L_0x0047
        L_0x0066:
            com.google.android.gms.common.zzf$zza[] r4 = new com.google.android.gms.common.zzf.zza[r3]
            r4[r2] = r1
            com.google.android.gms.common.zzf$zza r8 = r8.zza((android.content.pm.PackageInfo) r6, (com.google.android.gms.common.zzf.zza[]) r4)
            if (r8 != 0) goto L_0x007e
        L_0x0070:
            java.lang.String r8 = "GooglePlayServicesUtil"
            java.lang.String r0 = "Google Play services signature invalid."
            goto L_0x0047
        L_0x0075:
            com.google.android.gms.common.zzf$zza[] r1 = com.google.android.gms.common.zzf.zzd.zzayw
            com.google.android.gms.common.zzf$zza r8 = r8.zza((android.content.pm.PackageInfo) r6, (com.google.android.gms.common.zzf.zza[]) r1)
            if (r8 != 0) goto L_0x007e
            goto L_0x0070
        L_0x007e:
            int r8 = GOOGLE_PLAY_SERVICES_VERSION_CODE
            int r8 = com.google.android.gms.common.util.zzm.zzdp(r8)
            int r1 = r6.versionCode
            int r1 = com.google.android.gms.common.util.zzm.zzdp(r1)
            if (r1 >= r8) goto L_0x00b2
            java.lang.String r8 = "GooglePlayServicesUtil"
            int r0 = GOOGLE_PLAY_SERVICES_VERSION_CODE
            int r1 = r6.versionCode
            r2 = 77
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Google Play services out of date.  Requires "
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = " but found "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            android.util.Log.w(r8, r0)
            r8 = 2
            return r8
        L_0x00b2:
            android.content.pm.ApplicationInfo r8 = r6.applicationInfo
            if (r8 != 0) goto L_0x00c6
            java.lang.String r8 = "com.google.android.gms"
            android.content.pm.ApplicationInfo r8 = r0.getApplicationInfo(r8, r2)     // Catch:{ NameNotFoundException -> 0x00bd }
            goto L_0x00c6
        L_0x00bd:
            r8 = move-exception
            java.lang.String r0 = "GooglePlayServicesUtil"
            java.lang.String r1 = "Google Play services missing when getting application info."
            android.util.Log.wtf(r0, r1, r8)
            return r3
        L_0x00c6:
            boolean r8 = r8.enabled
            if (r8 != 0) goto L_0x00cc
            r8 = 3
            return r8
        L_0x00cc:
            return r2
        L_0x00cd:
            java.lang.String r8 = "GooglePlayServicesUtil"
            java.lang.String r0 = "Google Play services is missing."
            android.util.Log.w(r8, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzg.isGooglePlayServicesAvailable(android.content.Context):int");
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        if (i == 9) {
            return true;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static int zzaC(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    @Deprecated
    public static void zzaF(Context context) {
        if (!zzayB.getAndSet(true)) {
            try {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (notificationManager != null) {
                    notificationManager.cancel(10436);
                }
            } catch (SecurityException unused) {
            }
        }
    }

    private static void zzaH(Context context) {
        if (!zzayC.get()) {
            int zzaW = zzz.zzaW(context);
            if (zzaW == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            } else if (zzaW != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                int i = GOOGLE_PLAY_SERVICES_VERSION_CODE;
                String valueOf = String.valueOf("com.google.android.gms.version");
                StringBuilder sb = new StringBuilder(TIFFConstants.TIFFTAG_GRAYRESPONSEUNIT + String.valueOf(valueOf).length());
                sb.append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ");
                sb.append(i);
                sb.append(" but found ");
                sb.append(zzaW);
                sb.append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"");
                sb.append(valueOf);
                sb.append("\" android:value=\"@integer/google_play_services_version\" />");
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    public static boolean zzaI(Context context) {
        zzaL(context);
        return zzayz;
    }

    public static boolean zzaJ(Context context) {
        return zzaI(context) || !zzvd();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r2 = ((android.os.UserManager) r2.getSystemService("user")).getApplicationRestrictions(r2.getPackageName());
     */
    @android.annotation.TargetApi(18)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzaK(android.content.Context r2) {
        /*
            boolean r0 = com.google.android.gms.common.util.zzt.zzzk()
            if (r0 == 0) goto L_0x0028
            java.lang.String r0 = "user"
            java.lang.Object r0 = r2.getSystemService(r0)
            android.os.UserManager r0 = (android.os.UserManager) r0
            java.lang.String r2 = r2.getPackageName()
            android.os.Bundle r2 = r0.getApplicationRestrictions(r2)
            if (r2 == 0) goto L_0x0028
            java.lang.String r0 = "true"
            java.lang.String r1 = "restricted_profile"
            java.lang.String r2 = r2.getString(r1)
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0028
            r2 = 1
            return r2
        L_0x0028:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzg.zzaK(android.content.Context):boolean");
    }

    private static void zzaL(Context context) {
        if (!zzayA) {
            zzaM(context);
        }
    }

    private static void zzaM(Context context) {
        try {
            PackageInfo packageInfo = zzadg.zzbi(context).getPackageInfo("com.google.android.gms", 64);
            if (packageInfo != null) {
                if (zzh.zzaN(context).zza(packageInfo, zzf.zzd.zzayw[1]) != null) {
                    zzayz = true;
                    zzayA = true;
                }
            }
            zzayz = false;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);
        } catch (Throwable th) {
            zzayA = true;
            throw th;
        }
        zzayA = true;
    }

    @Deprecated
    public static void zzaq(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = zze.zzuY().isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable != 0) {
            Intent zzb = zze.zzuY().zzb(context, isGooglePlayServicesAvailable, "e");
            StringBuilder sb = new StringBuilder(57);
            sb.append("GooglePlayServices not available due to error ");
            sb.append(isGooglePlayServicesAvailable);
            Log.e("GooglePlayServicesUtil", sb.toString());
            if (zzb == null) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", zzb);
        }
    }

    @TargetApi(19)
    @Deprecated
    public static boolean zzc(Context context, int i, String str) {
        return zzy.zzc(context, i, str);
    }

    @Deprecated
    public static boolean zzd(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return zzz(context, "com.google.android.gms");
        }
        return false;
    }

    @Deprecated
    public static boolean zze(Context context, int i) {
        if (i == 9) {
            return zzz(context, "com.android.vending");
        }
        return false;
    }

    @Deprecated
    public static boolean zzf(Context context, int i) {
        return zzy.zzf(context, i);
    }

    @Deprecated
    public static boolean zzvd() {
        return zzj.zzzd();
    }

    @TargetApi(21)
    static boolean zzz(Context context, String str) {
        boolean equals = str.equals("com.google.android.gms");
        if (zzt.zzzo()) {
            for (PackageInstaller.SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                if (str.equals(appPackageName.getAppPackageName())) {
                    return true;
                }
            }
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            return equals ? applicationInfo.enabled : applicationInfo.enabled && !zzaK(context);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
