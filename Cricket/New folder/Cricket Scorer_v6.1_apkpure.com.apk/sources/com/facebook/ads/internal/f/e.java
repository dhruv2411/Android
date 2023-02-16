package com.facebook.ads.internal.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.facebook.ads.internal.q.a.n;
import com.facebook.ads.internal.q.a.q;
import com.facebook.ads.internal.q.a.t;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

public class e {
    private static final String a = "com.facebook.ads.internal.f.e";
    private static final Object b = new Object();
    private static final Set<String> c = Collections.synchronizedSet(new HashSet());
    private static final Map<String, Integer> d = Collections.synchronizedMap(new HashMap());

    public static d a(Exception exc, Context context, Map<String, String> map) {
        try {
            d dVar = new d(n.b(), n.c(), new b(q.a((Throwable) exc), map, true).a());
            try {
                a(dVar, context);
                return dVar;
            } catch (Exception unused) {
                return dVar;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    @WorkerThread
    public static JSONArray a(Context context) {
        return a(context, -1);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v24, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v25, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v32, resolved type: java.lang.Throwable} */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r9v27 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00b6 A[SYNTHETIC, Splitter:B:57:0x00b6] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00be A[Catch:{ IOException -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00c3 A[Catch:{ IOException -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00d2 A[SYNTHETIC, Splitter:B:72:0x00d2] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00dc A[Catch:{ IOException -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00e1 A[Catch:{ IOException -> 0x00d8 }] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONArray a(android.content.Context r8, int r9) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            java.lang.Object r1 = b
            monitor-enter(r1)
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch:{ IOException | JSONException -> 0x00aa, all -> 0x00a5 }
            java.io.File r4 = r8.getFilesDir()     // Catch:{ IOException | JSONException -> 0x00aa, all -> 0x00a5 }
            java.lang.String r5 = "debuglogs"
            r3.<init>(r4, r5)     // Catch:{ IOException | JSONException -> 0x00aa, all -> 0x00a5 }
            boolean r3 = r3.exists()     // Catch:{ IOException | JSONException -> 0x00aa, all -> 0x00a5 }
            if (r3 == 0) goto L_0x0088
            java.lang.String r3 = "debuglogs"
            java.io.FileInputStream r8 = r8.openFileInput(r3)     // Catch:{ IOException | JSONException -> 0x00aa, all -> 0x00a5 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException | JSONException -> 0x0085, all -> 0x0082 }
            r3.<init>(r8)     // Catch:{ IOException | JSONException -> 0x0085, all -> 0x0082 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ IOException | JSONException -> 0x0080 }
            r4.<init>(r3)     // Catch:{ IOException | JSONException -> 0x0080 }
        L_0x002a:
            java.lang.String r2 = r4.readLine()     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            if (r2 == 0) goto L_0x0078
            if (r9 == 0) goto L_0x0078
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            r5.<init>(r2)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            java.lang.String r2 = "attempt"
            boolean r2 = r5.has(r2)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            if (r2 != 0) goto L_0x0045
            java.lang.String r2 = "attempt"
            r6 = 0
            r5.put(r2, r6)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
        L_0x0045:
            java.lang.String r2 = "id"
            java.lang.String r2 = r5.getString(r2)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            java.util.Set<java.lang.String> r6 = c     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            boolean r6 = r6.contains(r2)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            if (r6 != 0) goto L_0x002a
            java.lang.String r6 = "attempt"
            int r6 = r5.getInt(r6)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            java.util.Map<java.lang.String, java.lang.Integer> r7 = d     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            boolean r7 = r7.containsKey(r2)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            if (r7 == 0) goto L_0x006d
            java.lang.String r6 = "attempt"
            java.util.Map<java.lang.String, java.lang.Integer> r7 = d     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            java.lang.Object r2 = r7.get(r2)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            r5.put(r6, r2)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            goto L_0x0070
        L_0x006d:
            a((java.lang.String) r2, (int) r6)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
        L_0x0070:
            r0.put(r5)     // Catch:{ IOException | JSONException -> 0x007d, all -> 0x007a }
            if (r9 <= 0) goto L_0x002a
            int r9 = r9 + -1
            goto L_0x002a
        L_0x0078:
            r2 = r4
            goto L_0x008a
        L_0x007a:
            r9 = move-exception
            goto L_0x00d0
        L_0x007d:
            r9 = move-exception
            r2 = r4
            goto L_0x00ad
        L_0x0080:
            r9 = move-exception
            goto L_0x00ad
        L_0x0082:
            r9 = move-exception
            r3 = r2
            goto L_0x00a8
        L_0x0085:
            r9 = move-exception
            r3 = r2
            goto L_0x00ad
        L_0x0088:
            r8 = r2
            r3 = r8
        L_0x008a:
            if (r2 == 0) goto L_0x0092
            r2.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x0092
        L_0x0090:
            r8 = move-exception
            goto L_0x009d
        L_0x0092:
            if (r3 == 0) goto L_0x0097
            r3.close()     // Catch:{ IOException -> 0x0090 }
        L_0x0097:
            if (r8 == 0) goto L_0x00cc
            r8.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x00cc
        L_0x009d:
            java.lang.String r9 = a     // Catch:{ all -> 0x00d6 }
            java.lang.String r2 = "Failed to close buffers"
        L_0x00a1:
            android.util.Log.e(r9, r2, r8)     // Catch:{ all -> 0x00d6 }
            goto L_0x00cc
        L_0x00a5:
            r9 = move-exception
            r8 = r2
            r3 = r8
        L_0x00a8:
            r4 = r3
            goto L_0x00d0
        L_0x00aa:
            r9 = move-exception
            r8 = r2
            r3 = r8
        L_0x00ad:
            java.lang.String r4 = a     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = "Failed to read crashes"
            android.util.Log.e(r4, r5, r9)     // Catch:{ all -> 0x00ce }
            if (r2 == 0) goto L_0x00bc
            r2.close()     // Catch:{ IOException -> 0x00ba }
            goto L_0x00bc
        L_0x00ba:
            r8 = move-exception
            goto L_0x00c7
        L_0x00bc:
            if (r3 == 0) goto L_0x00c1
            r3.close()     // Catch:{ IOException -> 0x00ba }
        L_0x00c1:
            if (r8 == 0) goto L_0x00cc
            r8.close()     // Catch:{ IOException -> 0x00ba }
            goto L_0x00cc
        L_0x00c7:
            java.lang.String r9 = a     // Catch:{ all -> 0x00d6 }
            java.lang.String r2 = "Failed to close buffers"
            goto L_0x00a1
        L_0x00cc:
            monitor-exit(r1)     // Catch:{ all -> 0x00d6 }
            return r0
        L_0x00ce:
            r9 = move-exception
            r4 = r2
        L_0x00d0:
            if (r4 == 0) goto L_0x00da
            r4.close()     // Catch:{ IOException -> 0x00d8 }
            goto L_0x00da
        L_0x00d6:
            r8 = move-exception
            goto L_0x00ed
        L_0x00d8:
            r8 = move-exception
            goto L_0x00e5
        L_0x00da:
            if (r3 == 0) goto L_0x00df
            r3.close()     // Catch:{ IOException -> 0x00d8 }
        L_0x00df:
            if (r8 == 0) goto L_0x00ec
            r8.close()     // Catch:{ IOException -> 0x00d8 }
            goto L_0x00ec
        L_0x00e5:
            java.lang.String r0 = a     // Catch:{ all -> 0x00d6 }
            java.lang.String r2 = "Failed to close buffers"
            android.util.Log.e(r0, r2, r8)     // Catch:{ all -> 0x00d6 }
        L_0x00ec:
            throw r9     // Catch:{ all -> 0x00d6 }
        L_0x00ed:
            monitor-exit(r1)     // Catch:{ all -> 0x00d6 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.f.e.a(android.content.Context, int):org.json.JSONArray");
    }

    private static JSONObject a(d dVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(TtmlNode.ATTR_ID, UUID.randomUUID().toString());
        jSONObject.put(DublinCoreProperties.TYPE, dVar.a());
        jSONObject.put("time", t.a(dVar.b()));
        jSONObject.put("session_time", t.a(dVar.c()));
        jSONObject.put("session_id", dVar.d());
        jSONObject.put("data", dVar.e() != null ? new JSONObject(dVar.e()) : new JSONObject());
        jSONObject.put("attempt", 0);
        return jSONObject;
    }

    public static void a(d dVar, Context context) {
        if (dVar != null && context != null) {
            synchronized (b) {
                try {
                    JSONObject a2 = a(dVar);
                    FileOutputStream openFileOutput = context.openFileOutput("debuglogs", 32768);
                    openFileOutput.write((a2.toString() + "\n").getBytes());
                    openFileOutput.close();
                    d(context);
                } catch (Exception e) {
                    Log.e(a, "Failed to store crash", e);
                }
            }
        }
    }

    public static void a(String str) {
        Integer num = d.get(str);
        if (num == null) {
            num = 0;
        } else {
            d.remove(str);
        }
        d.put(str, Integer.valueOf(num.intValue() + 1));
    }

    private static void a(String str, int i) {
        if (c.contains(str)) {
            throw new RuntimeException("finished event should not be updated to OngoingEvent.");
        }
        if (d.containsKey(str)) {
            d.remove(str);
        }
        d.put(str, Integer.valueOf(i));
    }

    public static int b(Context context) {
        return context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).getInt("EventCount", 0) - c.size();
    }

    private static void b(Context context, int i) {
        SharedPreferences.Editor edit = context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).edit();
        if (i < 0) {
            i = 0;
        }
        edit.putInt("EventCount", i).apply();
    }

    public static void b(String str) {
        if (d.containsKey(str)) {
            d.remove(str);
        }
        c.add(str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: java.io.InputStreamReader} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v6, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r5v14 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f2 A[SYNTHETIC, Splitter:B:69:0x00f2] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00fa A[Catch:{ IOException -> 0x00f6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00ff A[Catch:{ IOException -> 0x00f6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0104 A[Catch:{ IOException -> 0x00f6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0120 A[SYNTHETIC, Splitter:B:88:0x0120] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x012a A[Catch:{ IOException -> 0x0126 }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x012f A[Catch:{ IOException -> 0x0126 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0134 A[Catch:{ IOException -> 0x0126 }] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(android.content.Context r11) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            java.lang.Object r1 = b
            monitor-enter(r1)
            r2 = 0
            r3 = 0
            java.io.File r4 = new java.io.File     // Catch:{ IOException | JSONException -> 0x00e5, all -> 0x00e0 }
            java.io.File r5 = r11.getFilesDir()     // Catch:{ IOException | JSONException -> 0x00e5, all -> 0x00e0 }
            java.lang.String r6 = "debuglogs"
            r4.<init>(r5, r6)     // Catch:{ IOException | JSONException -> 0x00e5, all -> 0x00e0 }
            boolean r4 = r4.exists()     // Catch:{ IOException | JSONException -> 0x00e5, all -> 0x00e0 }
            if (r4 == 0) goto L_0x00a8
            java.lang.String r4 = "debuglogs"
            java.io.FileInputStream r4 = r11.openFileInput(r4)     // Catch:{ IOException | JSONException -> 0x00e5, all -> 0x00e0 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ IOException | JSONException -> 0x00a4, all -> 0x00a1 }
            r5.<init>(r4)     // Catch:{ IOException | JSONException -> 0x00a4, all -> 0x00a1 }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ IOException | JSONException -> 0x009e, all -> 0x009a }
            r6.<init>(r5)     // Catch:{ IOException | JSONException -> 0x009e, all -> 0x009a }
        L_0x002b:
            java.lang.String r7 = r6.readLine()     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            if (r7 == 0) goto L_0x005b
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            r8.<init>(r7)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            java.lang.String r7 = "id"
            java.lang.String r7 = r8.getString(r7)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            java.util.Set<java.lang.String> r9 = c     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            boolean r9 = r9.contains(r7)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            if (r9 != 0) goto L_0x002b
            java.util.Map<java.lang.String, java.lang.Integer> r9 = d     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            boolean r9 = r9.containsKey(r7)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            if (r9 == 0) goto L_0x0057
            java.lang.String r9 = "attempt"
            java.util.Map<java.lang.String, java.lang.Integer> r10 = d     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            java.lang.Object r7 = r10.get(r7)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            r8.put(r9, r7)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
        L_0x0057:
            r0.put(r8)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            goto L_0x002b
        L_0x005b:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            r7.<init>()     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            int r8 = r0.length()     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            r9 = r2
        L_0x0065:
            if (r9 >= r8) goto L_0x007a
            org.json.JSONObject r10 = r0.getJSONObject(r9)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            r7.append(r10)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            r10 = 10
            r7.append(r10)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            int r9 = r9 + 1
            goto L_0x0065
        L_0x007a:
            java.lang.String r0 = "debuglogs"
            java.io.FileOutputStream r0 = r11.openFileOutput(r0, r2)     // Catch:{ IOException | JSONException -> 0x0095, all -> 0x0092 }
            java.lang.String r3 = r7.toString()     // Catch:{ IOException | JSONException -> 0x0090, all -> 0x008d }
            byte[] r3 = r3.getBytes()     // Catch:{ IOException | JSONException -> 0x0090, all -> 0x008d }
            r0.write(r3)     // Catch:{ IOException | JSONException -> 0x0090, all -> 0x008d }
            r3 = r6
            goto L_0x00ab
        L_0x008d:
            r11 = move-exception
            goto L_0x011d
        L_0x0090:
            r11 = move-exception
            goto L_0x0097
        L_0x0092:
            r11 = move-exception
            goto L_0x011e
        L_0x0095:
            r11 = move-exception
            r0 = r3
        L_0x0097:
            r3 = r6
            goto L_0x00e9
        L_0x009a:
            r11 = move-exception
            r6 = r3
            goto L_0x011e
        L_0x009e:
            r11 = move-exception
            r0 = r3
            goto L_0x00e9
        L_0x00a1:
            r11 = move-exception
            r5 = r3
            goto L_0x00e3
        L_0x00a4:
            r11 = move-exception
            r0 = r3
            r5 = r0
            goto L_0x00e9
        L_0x00a8:
            r0 = r3
            r4 = r0
            r5 = r4
        L_0x00ab:
            int r6 = b((android.content.Context) r11)     // Catch:{ IOException | JSONException -> 0x00de }
            b(r11, r6)     // Catch:{ IOException | JSONException -> 0x00de }
            if (r3 == 0) goto L_0x00ba
            r3.close()     // Catch:{ IOException -> 0x00b8 }
            goto L_0x00ba
        L_0x00b8:
            r11 = move-exception
            goto L_0x00ca
        L_0x00ba:
            if (r5 == 0) goto L_0x00bf
            r5.close()     // Catch:{ IOException -> 0x00b8 }
        L_0x00bf:
            if (r4 == 0) goto L_0x00c4
            r4.close()     // Catch:{ IOException -> 0x00b8 }
        L_0x00c4:
            if (r0 == 0) goto L_0x00d1
            r0.close()     // Catch:{ IOException -> 0x00b8 }
            goto L_0x00d1
        L_0x00ca:
            java.lang.String r0 = a     // Catch:{ all -> 0x0124 }
            java.lang.String r2 = "Failed to close buffers"
            android.util.Log.e(r0, r2, r11)     // Catch:{ all -> 0x0124 }
        L_0x00d1:
            java.util.Set<java.lang.String> r11 = c     // Catch:{ all -> 0x0124 }
            r11.clear()     // Catch:{ all -> 0x0124 }
            java.util.Map<java.lang.String, java.lang.Integer> r11 = d     // Catch:{ all -> 0x0124 }
            r11.clear()     // Catch:{ all -> 0x0124 }
            monitor-exit(r1)     // Catch:{ all -> 0x0124 }
            r11 = 1
            return r11
        L_0x00de:
            r11 = move-exception
            goto L_0x00e9
        L_0x00e0:
            r11 = move-exception
            r4 = r3
            r5 = r4
        L_0x00e3:
            r6 = r5
            goto L_0x011e
        L_0x00e5:
            r11 = move-exception
            r0 = r3
            r4 = r0
            r5 = r4
        L_0x00e9:
            java.lang.String r6 = a     // Catch:{ all -> 0x011b }
            java.lang.String r7 = "Failed to rewrite File."
            android.util.Log.e(r6, r7, r11)     // Catch:{ all -> 0x011b }
            if (r3 == 0) goto L_0x00f8
            r3.close()     // Catch:{ IOException -> 0x00f6 }
            goto L_0x00f8
        L_0x00f6:
            r11 = move-exception
            goto L_0x0108
        L_0x00f8:
            if (r5 == 0) goto L_0x00fd
            r5.close()     // Catch:{ IOException -> 0x00f6 }
        L_0x00fd:
            if (r4 == 0) goto L_0x0102
            r4.close()     // Catch:{ IOException -> 0x00f6 }
        L_0x0102:
            if (r0 == 0) goto L_0x010f
            r0.close()     // Catch:{ IOException -> 0x00f6 }
            goto L_0x010f
        L_0x0108:
            java.lang.String r0 = a     // Catch:{ all -> 0x0124 }
            java.lang.String r3 = "Failed to close buffers"
            android.util.Log.e(r0, r3, r11)     // Catch:{ all -> 0x0124 }
        L_0x010f:
            java.util.Set<java.lang.String> r11 = c     // Catch:{ all -> 0x0124 }
            r11.clear()     // Catch:{ all -> 0x0124 }
            java.util.Map<java.lang.String, java.lang.Integer> r11 = d     // Catch:{ all -> 0x0124 }
            r11.clear()     // Catch:{ all -> 0x0124 }
            monitor-exit(r1)     // Catch:{ all -> 0x0124 }
            return r2
        L_0x011b:
            r11 = move-exception
            r6 = r3
        L_0x011d:
            r3 = r0
        L_0x011e:
            if (r6 == 0) goto L_0x0128
            r6.close()     // Catch:{ IOException -> 0x0126 }
            goto L_0x0128
        L_0x0124:
            r11 = move-exception
            goto L_0x014a
        L_0x0126:
            r0 = move-exception
            goto L_0x0138
        L_0x0128:
            if (r5 == 0) goto L_0x012d
            r5.close()     // Catch:{ IOException -> 0x0126 }
        L_0x012d:
            if (r4 == 0) goto L_0x0132
            r4.close()     // Catch:{ IOException -> 0x0126 }
        L_0x0132:
            if (r3 == 0) goto L_0x013f
            r3.close()     // Catch:{ IOException -> 0x0126 }
            goto L_0x013f
        L_0x0138:
            java.lang.String r2 = a     // Catch:{ all -> 0x0124 }
            java.lang.String r3 = "Failed to close buffers"
            android.util.Log.e(r2, r3, r0)     // Catch:{ all -> 0x0124 }
        L_0x013f:
            java.util.Set<java.lang.String> r0 = c     // Catch:{ all -> 0x0124 }
            r0.clear()     // Catch:{ all -> 0x0124 }
            java.util.Map<java.lang.String, java.lang.Integer> r0 = d     // Catch:{ all -> 0x0124 }
            r0.clear()     // Catch:{ all -> 0x0124 }
            throw r11     // Catch:{ all -> 0x0124 }
        L_0x014a:
            monitor-exit(r1)     // Catch:{ all -> 0x0124 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.f.e.c(android.content.Context):boolean");
    }

    public static boolean c(String str) {
        return c.contains(str) || d.containsKey(str);
    }

    private static void d(Context context) {
        b(context, context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).getInt("EventCount", 0) + 1);
    }
}
