package com.facebook.ads.internal.g;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class a {
    private static SensorManager a;
    private static Sensor b;
    private static Sensor c;
    /* access modifiers changed from: private */
    public static volatile float[] d;
    /* access modifiers changed from: private */
    public static volatile float[] e;
    private static Map<String, String> f = new ConcurrentHashMap();
    private static String[] g = {"x", "y", "z"};
    private static SensorEventListener h;
    private static SensorEventListener i;

    /* renamed from: com.facebook.ads.internal.g.a$a  reason: collision with other inner class name */
    private static class C0003a implements SensorEventListener {
        private C0003a() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] unused = a.d = sensorEvent.values;
            a.d();
        }
    }

    private static class b implements SensorEventListener {
        private b() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] unused = a.e = sensorEvent.values;
            a.e();
        }
    }

    public static Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(f);
        a((Map<String, String>) hashMap);
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(android.content.Context r5) {
        /*
            java.lang.Class<com.facebook.ads.internal.g.a> r0 = com.facebook.ads.internal.g.a.class
            monitor-enter(r0)
            b((android.content.Context) r5)     // Catch:{ all -> 0x006e }
            c(r5)     // Catch:{ all -> 0x006e }
            d(r5)     // Catch:{ all -> 0x006e }
            android.hardware.SensorManager r1 = a     // Catch:{ all -> 0x006e }
            if (r1 != 0) goto L_0x0020
            java.lang.String r1 = "sensor"
            java.lang.Object r5 = r5.getSystemService(r1)     // Catch:{ all -> 0x006e }
            android.hardware.SensorManager r5 = (android.hardware.SensorManager) r5     // Catch:{ all -> 0x006e }
            a = r5     // Catch:{ all -> 0x006e }
            android.hardware.SensorManager r5 = a     // Catch:{ all -> 0x006e }
            if (r5 != 0) goto L_0x0020
            monitor-exit(r0)
            return
        L_0x0020:
            android.hardware.Sensor r5 = b     // Catch:{ all -> 0x006e }
            if (r5 != 0) goto L_0x002d
            android.hardware.SensorManager r5 = a     // Catch:{ all -> 0x006e }
            r1 = 1
            android.hardware.Sensor r5 = r5.getDefaultSensor(r1)     // Catch:{ all -> 0x006e }
            b = r5     // Catch:{ all -> 0x006e }
        L_0x002d:
            android.hardware.Sensor r5 = c     // Catch:{ all -> 0x006e }
            if (r5 != 0) goto L_0x003a
            android.hardware.SensorManager r5 = a     // Catch:{ all -> 0x006e }
            r1 = 4
            android.hardware.Sensor r5 = r5.getDefaultSensor(r1)     // Catch:{ all -> 0x006e }
            c = r5     // Catch:{ all -> 0x006e }
        L_0x003a:
            android.hardware.SensorEventListener r5 = h     // Catch:{ all -> 0x006e }
            r1 = 3
            r2 = 0
            if (r5 != 0) goto L_0x0054
            com.facebook.ads.internal.g.a$a r5 = new com.facebook.ads.internal.g.a$a     // Catch:{ all -> 0x006e }
            r5.<init>()     // Catch:{ all -> 0x006e }
            h = r5     // Catch:{ all -> 0x006e }
            android.hardware.Sensor r5 = b     // Catch:{ all -> 0x006e }
            if (r5 == 0) goto L_0x0054
            android.hardware.SensorManager r5 = a     // Catch:{ all -> 0x006e }
            android.hardware.SensorEventListener r3 = h     // Catch:{ all -> 0x006e }
            android.hardware.Sensor r4 = b     // Catch:{ all -> 0x006e }
            r5.registerListener(r3, r4, r1)     // Catch:{ all -> 0x006e }
        L_0x0054:
            android.hardware.SensorEventListener r5 = i     // Catch:{ all -> 0x006e }
            if (r5 != 0) goto L_0x006c
            com.facebook.ads.internal.g.a$b r5 = new com.facebook.ads.internal.g.a$b     // Catch:{ all -> 0x006e }
            r5.<init>()     // Catch:{ all -> 0x006e }
            i = r5     // Catch:{ all -> 0x006e }
            android.hardware.Sensor r5 = c     // Catch:{ all -> 0x006e }
            if (r5 == 0) goto L_0x006c
            android.hardware.SensorManager r5 = a     // Catch:{ all -> 0x006e }
            android.hardware.SensorEventListener r2 = i     // Catch:{ all -> 0x006e }
            android.hardware.Sensor r3 = c     // Catch:{ all -> 0x006e }
            r5.registerListener(r2, r3, r1)     // Catch:{ all -> 0x006e }
        L_0x006c:
            monitor-exit(r0)
            return
        L_0x006e:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.g.a.a(android.content.Context):void");
    }

    private static void a(Map<String, String> map) {
        float[] fArr = d;
        float[] fArr2 = e;
        if (fArr != null) {
            int min = Math.min(g.length, fArr.length);
            for (int i2 = 0; i2 < min; i2++) {
                map.put("accelerometer_" + g[i2], String.valueOf(fArr[i2]));
            }
        }
        if (fArr2 != null) {
            int min2 = Math.min(g.length, fArr2.length);
            for (int i3 = 0; i3 < min2; i3++) {
                map.put("rotation_" + g[i3], String.valueOf(fArr2[i3]));
            }
        }
    }

    private static void b(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        f.put("available_memory", String.valueOf(memoryInfo.availMem));
        if (Build.VERSION.SDK_INT >= 16) {
            f.put("total_memory", String.valueOf(memoryInfo.totalMem));
        }
    }

    private static void c(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = (long) statFs.getBlockSize();
        f.put("free_space", String.valueOf(((long) statFs.getAvailableBlocks()) * blockSize));
    }

    /* access modifiers changed from: private */
    public static synchronized void d() {
        synchronized (a.class) {
            if (a != null) {
                a.unregisterListener(h);
            }
            h = null;
        }
    }

    private static void d(Context context) {
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            int intExtra3 = registerReceiver.getIntExtra("status", -1);
            boolean z = intExtra3 == 2 || intExtra3 == 5;
            float f2 = 0.0f;
            if (intExtra2 > 0) {
                f2 = 100.0f * (((float) intExtra) / ((float) intExtra2));
            }
            f.put("battery", String.valueOf(f2));
            f.put("charging", z ? "1" : "0");
        }
    }

    /* access modifiers changed from: private */
    public static synchronized void e() {
        synchronized (a.class) {
            if (a != null) {
                a.unregisterListener(i);
            }
            i = null;
        }
    }
}
