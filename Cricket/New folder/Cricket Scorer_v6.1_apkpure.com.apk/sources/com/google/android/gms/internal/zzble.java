package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzble {
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri zzbVN = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzbVO = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzbVP = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    /* access modifiers changed from: private */
    public static final AtomicBoolean zzbVQ = new AtomicBoolean();
    static HashMap<String, String> zzbVR;
    private static Object zzbVS;
    private static boolean zzbVT;
    static String[] zzbVU = new String[0];

    public static long getLong(ContentResolver contentResolver, String str, long j) {
        String string = getString(contentResolver, str);
        if (string == null) {
            return j;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    @Deprecated
    public static String getString(ContentResolver contentResolver, String str) {
        return zza(contentResolver, str, (String) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0053, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0055, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005a, code lost:
        r9 = r9.query(CONTENT_URI, (java.lang.String[]) null, (java.lang.String) null, new java.lang.String[]{r10}, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006a, code lost:
        if (r9 == null) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0070, code lost:
        if (r9.moveToFirst() != false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0073, code lost:
        r0 = r9.getString(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0077, code lost:
        if (r0 == null) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007d, code lost:
        if (r0.equals(r11) == false) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007f, code lost:
        r0 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0080, code lost:
        zza(r1, r10, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0083, code lost:
        if (r0 == null) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0085, code lost:
        r11 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008b, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x008c, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        zza(r1, r10, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0092, code lost:
        if (r9 == null) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0094, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0097, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0098, code lost:
        if (r9 != null) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x009a, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x009d, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(android.content.ContentResolver r9, java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.Class<com.google.android.gms.internal.zzble> r0 = com.google.android.gms.internal.zzble.class
            monitor-enter(r0)
            zza(r9)     // Catch:{ all -> 0x009e }
            java.lang.Object r1 = zzbVS     // Catch:{ all -> 0x009e }
            java.util.HashMap<java.lang.String, java.lang.String> r2 = zzbVR     // Catch:{ all -> 0x009e }
            boolean r2 = r2.containsKey(r10)     // Catch:{ all -> 0x009e }
            if (r2 == 0) goto L_0x001e
            java.util.HashMap<java.lang.String, java.lang.String> r9 = zzbVR     // Catch:{ all -> 0x009e }
            java.lang.Object r9 = r9.get(r10)     // Catch:{ all -> 0x009e }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x009e }
            if (r9 == 0) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r9 = r11
        L_0x001c:
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            return r9
        L_0x001e:
            java.lang.String[] r2 = zzbVU     // Catch:{ all -> 0x009e }
            int r3 = r2.length     // Catch:{ all -> 0x009e }
            r4 = 0
            r5 = r4
        L_0x0023:
            if (r5 >= r3) goto L_0x0059
            r6 = r2[r5]     // Catch:{ all -> 0x009e }
            boolean r6 = r10.startsWith(r6)     // Catch:{ all -> 0x009e }
            if (r6 == 0) goto L_0x0056
            boolean r1 = zzbVT     // Catch:{ all -> 0x009e }
            if (r1 == 0) goto L_0x0039
            java.util.HashMap<java.lang.String, java.lang.String> r1 = zzbVR     // Catch:{ all -> 0x009e }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x009e }
            if (r1 == 0) goto L_0x0054
        L_0x0039:
            java.lang.String[] r1 = zzbVU     // Catch:{ all -> 0x009e }
            zzc(r9, r1)     // Catch:{ all -> 0x009e }
            java.util.HashMap<java.lang.String, java.lang.String> r9 = zzbVR     // Catch:{ all -> 0x009e }
            boolean r9 = r9.containsKey(r10)     // Catch:{ all -> 0x009e }
            if (r9 == 0) goto L_0x0054
            java.util.HashMap<java.lang.String, java.lang.String> r9 = zzbVR     // Catch:{ all -> 0x009e }
            java.lang.Object r9 = r9.get(r10)     // Catch:{ all -> 0x009e }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x009e }
            if (r9 == 0) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r9 = r11
        L_0x0052:
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            return r9
        L_0x0054:
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            return r11
        L_0x0056:
            int r5 = r5 + 1
            goto L_0x0023
        L_0x0059:
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            android.net.Uri r3 = CONTENT_URI
            r0 = 0
            r5 = 0
            r8 = 1
            java.lang.String[] r6 = new java.lang.String[r8]
            r6[r4] = r10
            r7 = 0
            r2 = r9
            r4 = r0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)
            if (r9 == 0) goto L_0x008e
            boolean r0 = r9.moveToFirst()     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x0073
            goto L_0x008e
        L_0x0073:
            java.lang.String r0 = r9.getString(r8)     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x0080
            boolean r2 = r0.equals(r11)     // Catch:{ all -> 0x008c }
            if (r2 == 0) goto L_0x0080
            r0 = r11
        L_0x0080:
            zza((java.lang.Object) r1, (java.lang.String) r10, (java.lang.String) r0)     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x0086
            r11 = r0
        L_0x0086:
            if (r9 == 0) goto L_0x008b
            r9.close()
        L_0x008b:
            return r11
        L_0x008c:
            r10 = move-exception
            goto L_0x0098
        L_0x008e:
            r0 = 0
            zza((java.lang.Object) r1, (java.lang.String) r10, (java.lang.String) r0)     // Catch:{ all -> 0x008c }
            if (r9 == 0) goto L_0x0097
            r9.close()
        L_0x0097:
            return r11
        L_0x0098:
            if (r9 == 0) goto L_0x009d
            r9.close()
        L_0x009d:
            throw r10
        L_0x009e:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzble.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }

    public static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzbVN, (String[]) null, (String) null, strArr, (String) null);
        TreeMap treeMap = new TreeMap();
        if (query == null) {
            return treeMap;
        }
        while (query.moveToNext()) {
            try {
                treeMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return treeMap;
    }

    private static void zza(ContentResolver contentResolver) {
        if (zzbVR == null) {
            zzbVQ.set(false);
            zzbVR = new HashMap<>();
            zzbVS = new Object();
            zzbVT = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new ContentObserver((Handler) null) {
                public void onChange(boolean z) {
                    zzble.zzbVQ.set(true);
                }
            });
        } else if (zzbVQ.getAndSet(false)) {
            zzbVR.clear();
            zzbVS = new Object();
            zzbVT = false;
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzble.class) {
            if (obj == zzbVS) {
                zzbVR.put(str, str2);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        if (r3.length != 0) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void zzb(android.content.ContentResolver r2, java.lang.String... r3) {
        /*
            int r0 = r3.length
            if (r0 != 0) goto L_0x0004
            return
        L_0x0004:
            java.lang.Class<com.google.android.gms.internal.zzble> r0 = com.google.android.gms.internal.zzble.class
            monitor-enter(r0)
            zza(r2)     // Catch:{ all -> 0x0027 }
            java.lang.String[] r3 = zzk(r3)     // Catch:{ all -> 0x0027 }
            boolean r1 = zzbVT     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x0022
            java.util.HashMap<java.lang.String, java.lang.String> r1 = zzbVR     // Catch:{ all -> 0x0027 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x001b
            goto L_0x0022
        L_0x001b:
            int r1 = r3.length     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x0025
        L_0x001e:
            zzc(r2, r3)     // Catch:{ all -> 0x0027 }
            goto L_0x0025
        L_0x0022:
            java.lang.String[] r3 = zzbVU     // Catch:{ all -> 0x0027 }
            goto L_0x001e
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return
        L_0x0027:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzble.zzb(android.content.ContentResolver, java.lang.String[]):void");
    }

    private static void zzc(ContentResolver contentResolver, String[] strArr) {
        zzbVR.putAll(zza(contentResolver, strArr));
        zzbVT = true;
    }

    private static String[] zzk(String[] strArr) {
        HashSet hashSet = new HashSet((((zzbVU.length + strArr.length) * 4) / 3) + 1);
        hashSet.addAll(Arrays.asList(zzbVU));
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (hashSet.add(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return new String[0];
        }
        zzbVU = (String[]) hashSet.toArray(new String[hashSet.size()]);
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
