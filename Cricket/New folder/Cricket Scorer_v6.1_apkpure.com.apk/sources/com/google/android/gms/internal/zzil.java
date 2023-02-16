package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.zzf;
import com.google.android.gms.ads.internal.zzw;
import com.itextpdf.text.html.HtmlTags;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@zzme
public final class zzil implements zzid {
    private final zzf zzIo;
    private final zzkr zzIp;
    private final zzif zzIr;

    public static class zza {
        private final zzqw zzIs;

        public zza(zzqw zzqw) {
            this.zzIs = zzqw;
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0083  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0088 A[ADDED_TO_REGION] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.content.Intent zza(android.content.Context r8, java.util.Map<java.lang.String, java.lang.String> r9) {
            /*
                r7 = this;
                java.lang.String r0 = "activity"
                java.lang.Object r0 = r8.getSystemService(r0)
                android.app.ActivityManager r0 = (android.app.ActivityManager) r0
                java.lang.String r1 = "u"
                java.lang.Object r1 = r9.get(r1)
                java.lang.String r1 = (java.lang.String) r1
                boolean r2 = android.text.TextUtils.isEmpty(r1)
                r3 = 0
                if (r2 == 0) goto L_0x0018
                return r3
            L_0x0018:
                com.google.android.gms.internal.zzqw r2 = r7.zzIs
                if (r2 == 0) goto L_0x0026
                com.google.android.gms.internal.zzpo r2 = com.google.android.gms.ads.internal.zzw.zzcM()
                com.google.android.gms.internal.zzqw r4 = r7.zzIs
                java.lang.String r1 = r2.zza((com.google.android.gms.internal.zzqw) r4, (java.lang.String) r1)
            L_0x0026:
                android.net.Uri r1 = android.net.Uri.parse(r1)
                java.lang.String r2 = "use_first_package"
                java.lang.Object r2 = r9.get(r2)
                java.lang.String r2 = (java.lang.String) r2
                boolean r2 = java.lang.Boolean.parseBoolean(r2)
                java.lang.String r4 = "use_running_process"
                java.lang.Object r9 = r9.get(r4)
                java.lang.String r9 = (java.lang.String) r9
                boolean r9 = java.lang.Boolean.parseBoolean(r9)
                java.lang.String r4 = "http"
                java.lang.String r5 = r1.getScheme()
                boolean r4 = r4.equalsIgnoreCase(r5)
                if (r4 == 0) goto L_0x005d
                android.net.Uri$Builder r3 = r1.buildUpon()
                java.lang.String r4 = "https"
            L_0x0054:
                android.net.Uri$Builder r3 = r3.scheme(r4)
                android.net.Uri r3 = r3.build()
                goto L_0x0070
            L_0x005d:
                java.lang.String r4 = "https"
                java.lang.String r5 = r1.getScheme()
                boolean r4 = r4.equalsIgnoreCase(r5)
                if (r4 == 0) goto L_0x0070
                android.net.Uri$Builder r3 = r1.buildUpon()
                java.lang.String r4 = "http"
                goto L_0x0054
            L_0x0070:
                java.util.ArrayList r4 = new java.util.ArrayList
                r4.<init>()
                android.content.Intent r1 = r7.zzf(r1)
                android.content.Intent r3 = r7.zzf(r3)
                android.content.pm.ResolveInfo r5 = r7.zza(r8, r1, r4)
                if (r5 == 0) goto L_0x0088
                android.content.Intent r8 = r7.zza((android.content.Intent) r1, (android.content.pm.ResolveInfo) r5)
                return r8
            L_0x0088:
                if (r3 == 0) goto L_0x009b
                android.content.pm.ResolveInfo r3 = r7.zza((android.content.Context) r8, (android.content.Intent) r3)
                if (r3 == 0) goto L_0x009b
                android.content.Intent r3 = r7.zza((android.content.Intent) r1, (android.content.pm.ResolveInfo) r3)
                android.content.pm.ResolveInfo r8 = r7.zza((android.content.Context) r8, (android.content.Intent) r3)
                if (r8 == 0) goto L_0x009b
                return r3
            L_0x009b:
                int r8 = r4.size()
                if (r8 != 0) goto L_0x00a2
                return r1
            L_0x00a2:
                if (r9 == 0) goto L_0x00dd
                if (r0 == 0) goto L_0x00dd
                java.util.List r8 = r0.getRunningAppProcesses()
                if (r8 == 0) goto L_0x00dd
                java.util.Iterator r9 = r4.iterator()
            L_0x00b0:
                boolean r0 = r9.hasNext()
                if (r0 == 0) goto L_0x00dd
                java.lang.Object r0 = r9.next()
                android.content.pm.ResolveInfo r0 = (android.content.pm.ResolveInfo) r0
                java.util.Iterator r3 = r8.iterator()
            L_0x00c0:
                boolean r5 = r3.hasNext()
                if (r5 == 0) goto L_0x00b0
                java.lang.Object r5 = r3.next()
                android.app.ActivityManager$RunningAppProcessInfo r5 = (android.app.ActivityManager.RunningAppProcessInfo) r5
                java.lang.String r5 = r5.processName
                android.content.pm.ActivityInfo r6 = r0.activityInfo
                java.lang.String r6 = r6.packageName
                boolean r5 = r5.equals(r6)
                if (r5 == 0) goto L_0x00c0
                android.content.Intent r8 = r7.zza((android.content.Intent) r1, (android.content.pm.ResolveInfo) r0)
                return r8
            L_0x00dd:
                if (r2 == 0) goto L_0x00eb
                r8 = 0
                java.lang.Object r8 = r4.get(r8)
                android.content.pm.ResolveInfo r8 = (android.content.pm.ResolveInfo) r8
                android.content.Intent r8 = r7.zza((android.content.Intent) r1, (android.content.pm.ResolveInfo) r8)
                return r8
            L_0x00eb:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzil.zza.zza(android.content.Context, java.util.Map):android.content.Intent");
        }

        public Intent zza(Intent intent, ResolveInfo resolveInfo) {
            Intent intent2 = new Intent(intent);
            intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            return intent2;
        }

        public ResolveInfo zza(Context context, Intent intent) {
            return zza(context, intent, new ArrayList());
        }

        public ResolveInfo zza(Context context, Intent intent, ArrayList<ResolveInfo> arrayList) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (queryIntentActivities != null && resolveActivity != null) {
                int i = 0;
                while (true) {
                    if (i >= queryIntentActivities.size()) {
                        break;
                    }
                    ResolveInfo resolveInfo = queryIntentActivities.get(i);
                    if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                        break;
                    }
                    i++;
                }
            }
            resolveActivity = null;
            arrayList.addAll(queryIntentActivities);
            return resolveActivity;
        }

        public Intent zzf(Uri uri) {
            if (uri == null) {
                return null;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(uri);
            intent.setAction("android.intent.action.VIEW");
            return intent;
        }
    }

    public zzil(zzif zzif, zzf zzf, zzkr zzkr) {
        this.zzIr = zzif;
        this.zzIo = zzf;
        this.zzIp = zzkr;
    }

    private static boolean zzd(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int zze(Map<String, String> map) {
        String str = map.get("o");
        if (str == null) {
            return -1;
        }
        if ("p".equalsIgnoreCase(str)) {
            return zzw.zzcO().zzkR();
        }
        if ("l".equalsIgnoreCase(str)) {
            return zzw.zzcO().zzkQ();
        }
        if ("c".equalsIgnoreCase(str)) {
            return zzw.zzcO().zzkS();
        }
        return -1;
    }

    private static void zzf(zzqw zzqw, Map<String, String> map) {
        Context context = zzqw.getContext();
        if (TextUtils.isEmpty(map.get(HtmlTags.U))) {
            zzpk.zzbh("Destination url cannot be empty.");
            return;
        }
        try {
            zzqw.zzlv().zza(new zzc(new zza(zzqw).zza(context, map)));
        } catch (ActivityNotFoundException e) {
            zzpk.zzbh(e.getMessage());
        }
    }

    private void zzr(boolean z) {
        if (this.zzIp != null) {
            this.zzIp.zzs(z);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x017a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(com.google.android.gms.internal.zzqw r12, java.util.Map<java.lang.String, java.lang.String> r13) {
        /*
            r11 = this;
            java.lang.String r0 = "a"
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x0010
            java.lang.String r12 = "Action missing from an open GMSG."
            com.google.android.gms.internal.zzpk.zzbh(r12)
            return
        L_0x0010:
            com.google.android.gms.ads.internal.zzf r1 = r11.zzIo
            if (r1 == 0) goto L_0x002a
            com.google.android.gms.ads.internal.zzf r1 = r11.zzIo
            boolean r1 = r1.zzcd()
            if (r1 != 0) goto L_0x002a
            com.google.android.gms.ads.internal.zzf r12 = r11.zzIo
            java.lang.String r0 = "u"
            java.lang.Object r13 = r13.get(r0)
            java.lang.String r13 = (java.lang.String) r13
            r12.zzx(r13)
            return
        L_0x002a:
            com.google.android.gms.internal.zzqx r1 = r12.zzlv()
            java.lang.String r2 = "expand"
            boolean r2 = r2.equalsIgnoreCase(r0)
            r3 = 0
            if (r2 == 0) goto L_0x0052
            boolean r12 = r12.zzlz()
            if (r12 == 0) goto L_0x0043
            java.lang.String r12 = "Cannot expand WebView that is already expanded."
            com.google.android.gms.internal.zzpk.zzbh(r12)
            return
        L_0x0043:
            r11.zzr(r3)
            boolean r12 = zzd(r13)
            int r13 = zze(r13)
            r1.zza((boolean) r12, (int) r13)
            return
        L_0x0052:
            java.lang.String r2 = "webapp"
            boolean r2 = r2.equalsIgnoreCase(r0)
            if (r2 == 0) goto L_0x008f
            java.lang.String r12 = "u"
            java.lang.Object r12 = r13.get(r12)
            java.lang.String r12 = (java.lang.String) r12
            r11.zzr(r3)
            if (r12 == 0) goto L_0x0073
            boolean r0 = zzd(r13)
            int r13 = zze(r13)
            r1.zza((boolean) r0, (int) r13, (java.lang.String) r12)
            return
        L_0x0073:
            boolean r12 = zzd(r13)
            int r0 = zze(r13)
            java.lang.String r2 = "html"
            java.lang.Object r2 = r13.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "baseurl"
            java.lang.Object r13 = r13.get(r3)
            java.lang.String r13 = (java.lang.String) r13
            r1.zza(r12, r0, r2, r13)
            return
        L_0x008f:
            java.lang.String r2 = "in_app_purchase"
            boolean r2 = r2.equalsIgnoreCase(r0)
            if (r2 == 0) goto L_0x00d4
            java.lang.String r12 = "product_id"
            java.lang.Object r12 = r13.get(r12)
            java.lang.String r12 = (java.lang.String) r12
            java.lang.String r0 = "report_urls"
            java.lang.Object r13 = r13.get(r0)
            java.lang.String r13 = (java.lang.String) r13
            com.google.android.gms.internal.zzif r0 = r11.zzIr
            if (r0 != 0) goto L_0x00ac
            return
        L_0x00ac:
            if (r13 == 0) goto L_0x00c9
            boolean r0 = r13.isEmpty()
            if (r0 != 0) goto L_0x00c9
            java.lang.String r0 = " "
            java.lang.String[] r13 = r13.split(r0)
            com.google.android.gms.internal.zzif r0 = r11.zzIr
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.List r13 = java.util.Arrays.asList(r13)
            r1.<init>(r13)
            r0.zza(r12, r1)
            return
        L_0x00c9:
            com.google.android.gms.internal.zzif r13 = r11.zzIr
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r13.zza(r12, r0)
            return
        L_0x00d4:
            java.lang.String r2 = "app"
            boolean r0 = r2.equalsIgnoreCase(r0)
            r2 = 1
            if (r0 == 0) goto L_0x00f4
            java.lang.String r0 = "true"
            java.lang.String r4 = "system_browser"
            java.lang.Object r4 = r13.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            boolean r0 = r0.equalsIgnoreCase(r4)
            if (r0 == 0) goto L_0x00f4
            r11.zzr(r2)
            zzf(r12, r13)
            return
        L_0x00f4:
            r11.zzr(r2)
            java.lang.String r0 = "intent_url"
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = "u"
            java.lang.Object r2 = r13.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            r4 = 0
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 != 0) goto L_0x012d
            android.content.Intent r3 = android.content.Intent.parseUri(r0, r3)     // Catch:{ URISyntaxException -> 0x0113 }
            goto L_0x012e
        L_0x0113:
            r3 = move-exception
            java.lang.String r5 = "Error parsing the url: "
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r6 = r0.length()
            if (r6 == 0) goto L_0x0125
            java.lang.String r0 = r5.concat(r0)
            goto L_0x012a
        L_0x0125:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r5)
        L_0x012a:
            com.google.android.gms.internal.zzpk.zzb(r0, r3)
        L_0x012d:
            r3 = r4
        L_0x012e:
            if (r3 == 0) goto L_0x016f
            android.net.Uri r0 = r3.getData()
            if (r0 == 0) goto L_0x016f
            android.net.Uri r0 = r3.getData()
            java.lang.String r4 = r0.toString()
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x016c
            com.google.android.gms.internal.zzpo r5 = com.google.android.gms.ads.internal.zzw.zzcM()
            java.lang.String r4 = r5.zza((com.google.android.gms.internal.zzqw) r12, (java.lang.String) r4)
            android.net.Uri r5 = android.net.Uri.parse(r4)     // Catch:{ Exception -> 0x0152 }
            r0 = r5
            goto L_0x016c
        L_0x0152:
            r5 = move-exception
            java.lang.String r6 = "Error parsing the uri: "
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r7 = r4.length()
            if (r7 == 0) goto L_0x0164
            java.lang.String r4 = r6.concat(r4)
            goto L_0x0169
        L_0x0164:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r6)
        L_0x0169:
            com.google.android.gms.internal.zzpk.zzb(r4, r5)
        L_0x016c:
            r3.setData(r0)
        L_0x016f:
            if (r3 == 0) goto L_0x017a
            com.google.android.gms.ads.internal.overlay.zzc r12 = new com.google.android.gms.ads.internal.overlay.zzc
            r12.<init>(r3)
        L_0x0176:
            r1.zza((com.google.android.gms.ads.internal.overlay.zzc) r12)
            return
        L_0x017a:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x0188
            com.google.android.gms.internal.zzpo r0 = com.google.android.gms.ads.internal.zzw.zzcM()
            java.lang.String r2 = r0.zza((com.google.android.gms.internal.zzqw) r12, (java.lang.String) r2)
        L_0x0188:
            r5 = r2
            com.google.android.gms.ads.internal.overlay.zzc r12 = new com.google.android.gms.ads.internal.overlay.zzc
            java.lang.String r0 = "i"
            java.lang.Object r0 = r13.get(r0)
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r0 = "m"
            java.lang.Object r0 = r13.get(r0)
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r0 = "p"
            java.lang.Object r0 = r13.get(r0)
            r7 = r0
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r0 = "c"
            java.lang.Object r0 = r13.get(r0)
            r8 = r0
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r0 = "f"
            java.lang.Object r0 = r13.get(r0)
            r9 = r0
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r0 = "e"
            java.lang.Object r13 = r13.get(r0)
            r10 = r13
            java.lang.String r10 = (java.lang.String) r10
            r3 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            goto L_0x0176
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzil.zza(com.google.android.gms.internal.zzqw, java.util.Map):void");
    }
}
