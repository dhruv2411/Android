package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzatx;
import com.google.android.gms.internal.zzauu;
import com.google.android.gms.internal.zzauw;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzatj extends zzauh {
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbrg = new ArrayMap(1);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbrh = new ArrayMap(18);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbri = new ArrayMap(1);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbrj = new ArrayMap(1);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbrk = new ArrayMap(1);
    private final zzc zzbrl = new zzc(getContext(), zzow());
    /* access modifiers changed from: private */
    public final zzauo zzbrm = new zzauo(zznR());

    public static class zza {
        long zzbrn;
        long zzbro;
        long zzbrp;
        long zzbrq;
        long zzbrr;
    }

    interface zzb {
        boolean zza(long j, zzauw.zzb zzb);

        void zzb(zzauw.zze zze);
    }

    private class zzc extends SQLiteOpenHelper {
        zzc(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @WorkerThread
        public SQLiteDatabase getWritableDatabase() {
            if (!zzatj.this.zzbrm.zzA(zzatj.this.zzKn().zzLc())) {
                throw new SQLiteException("Database open failed");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException unused) {
                zzatj.this.zzbrm.start();
                zzatj.this.zzKl().zzLY().log("Opening the database failed, dropping and recreating it");
                String zzow = zzatj.this.zzow();
                if (!zzatj.this.getContext().getDatabasePath(zzow).delete()) {
                    zzatj.this.zzKl().zzLY().zzj("Failed to delete corrupted db file", zzow);
                }
                try {
                    SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    zzatj.this.zzbrm.clear();
                    return writableDatabase;
                } catch (SQLiteException e) {
                    zzatj.this.zzKl().zzLY().zzj("Failed to open freshly created database", e);
                    throw e;
                }
            }
        }

        @WorkerThread
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase);
        }

        @WorkerThread
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (Build.VERSION.SDK_INT < 15) {
                Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[]) null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "events", "CREATE TABLE IF NOT EXISTS events ( app_id TEXT NOT NULL, name TEXT NOT NULL, lifetime_count INTEGER NOT NULL, current_bundle_count INTEGER NOT NULL, last_fire_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,lifetime_count,current_bundle_count,last_fire_timestamp", (Map<String, String>) null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "conditional_properties", "CREATE TABLE IF NOT EXISTS conditional_properties ( app_id TEXT NOT NULL, origin TEXT NOT NULL, name TEXT NOT NULL, value BLOB NOT NULL, creation_timestamp INTEGER NOT NULL, active INTEGER NOT NULL, trigger_event_name TEXT, trigger_timeout INTEGER NOT NULL, timed_out_event BLOB,triggered_event BLOB, triggered_timestamp INTEGER NOT NULL, time_to_live INTEGER NOT NULL, expired_event BLOB, PRIMARY KEY (app_id, name)) ;", "app_id,origin,name,value,active,trigger_event_name,trigger_timeout,creation_timestamp,timed_out_event,triggered_event,triggered_timestamp,time_to_live,expired_event", (Map<String, String>) null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "user_attributes", "CREATE TABLE IF NOT EXISTS user_attributes ( app_id TEXT NOT NULL, name TEXT NOT NULL, set_timestamp INTEGER NOT NULL, value BLOB NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,set_timestamp,value", zzatj.zzbrg);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "apps", "CREATE TABLE IF NOT EXISTS apps ( app_id TEXT NOT NULL, app_instance_id TEXT, gmp_app_id TEXT, resettable_device_id_hash TEXT, last_bundle_index INTEGER NOT NULL, last_bundle_end_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id)) ;", "app_id,app_instance_id,gmp_app_id,resettable_device_id_hash,last_bundle_index,last_bundle_end_timestamp", zzatj.zzbrh);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "queue", "CREATE TABLE IF NOT EXISTS queue ( app_id TEXT NOT NULL, bundle_end_timestamp INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,bundle_end_timestamp,data", zzatj.zzbrj);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "raw_events_metadata", "CREATE TABLE IF NOT EXISTS raw_events_metadata ( app_id TEXT NOT NULL, metadata_fingerprint INTEGER NOT NULL, metadata BLOB NOT NULL, PRIMARY KEY (app_id, metadata_fingerprint));", "app_id,metadata_fingerprint,metadata", (Map<String, String>) null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "raw_events", "CREATE TABLE IF NOT EXISTS raw_events ( app_id TEXT NOT NULL, name TEXT NOT NULL, timestamp INTEGER NOT NULL, metadata_fingerprint INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,name,timestamp,metadata_fingerprint,data", zzatj.zzbri);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "event_filters", "CREATE TABLE IF NOT EXISTS event_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, event_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, event_name, audience_id, filter_id));", "app_id,audience_id,filter_id,event_name,data", (Map<String, String>) null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "property_filters", "CREATE TABLE IF NOT EXISTS property_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, property_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, property_name, audience_id, filter_id));", "app_id,audience_id,filter_id,property_name,data", (Map<String, String>) null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "audience_filter_values", "CREATE TABLE IF NOT EXISTS audience_filter_values ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, current_results BLOB, PRIMARY KEY (app_id, audience_id));", "app_id,audience_id,current_results", (Map<String, String>) null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase2, "app2", "CREATE TABLE IF NOT EXISTS app2 ( app_id TEXT NOT NULL, first_open_count INTEGER NOT NULL, PRIMARY KEY (app_id));", "app_id,first_open_count", zzatj.zzbrk);
        }

        @WorkerThread
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    static {
        zzbrg.put("origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;");
        zzbrh.put("app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;");
        zzbrh.put("app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;");
        zzbrh.put("gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;");
        zzbrh.put("dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;");
        zzbrh.put("measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;");
        zzbrh.put("last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;");
        zzbrh.put("day", "ALTER TABLE apps ADD COLUMN day INTEGER;");
        zzbrh.put("daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;");
        zzbrh.put("daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;");
        zzbrh.put("daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;");
        zzbrh.put("remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;");
        zzbrh.put("config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;");
        zzbrh.put("failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;");
        zzbrh.put("app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;");
        zzbrh.put("firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;");
        zzbrh.put("daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;");
        zzbrh.put("daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;");
        zzbrh.put("health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;");
        zzbrh.put("android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;");
        zzbri.put("realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;");
        zzbrj.put("has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;");
        zzbrk.put("previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;");
    }

    zzatj(zzaue zzaue) {
        super(zzaue);
    }

    private boolean zzLM() {
        return getContext().getDatabasePath(zzow()).exists();
    }

    @WorkerThread
    @TargetApi(11)
    static int zza(Cursor cursor, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return cursor.getType(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0039  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long zza(java.lang.String r3, java.lang.String[] r4, long r5) {
        /*
            r2 = this;
            android.database.sqlite.SQLiteDatabase r0 = r2.getWritableDatabase()
            r1 = 0
            android.database.Cursor r4 = r0.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x0028 }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0023, all -> 0x0020 }
            if (r0 == 0) goto L_0x001a
            r5 = 0
            long r5 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x0023, all -> 0x0020 }
            if (r4 == 0) goto L_0x0019
            r4.close()
        L_0x0019:
            return r5
        L_0x001a:
            if (r4 == 0) goto L_0x001f
            r4.close()
        L_0x001f:
            return r5
        L_0x0020:
            r3 = move-exception
            r1 = r4
            goto L_0x0037
        L_0x0023:
            r5 = move-exception
            r1 = r4
            goto L_0x0029
        L_0x0026:
            r3 = move-exception
            goto L_0x0037
        L_0x0028:
            r5 = move-exception
        L_0x0029:
            com.google.android.gms.internal.zzatx r4 = r2.zzKl()     // Catch:{ all -> 0x0026 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x0026 }
            java.lang.String r6 = "Database error"
            r4.zze(r6, r3, r5)     // Catch:{ all -> 0x0026 }
            throw r5     // Catch:{ all -> 0x0026 }
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zza(java.lang.String, java.lang.String[], long):long");
    }

    static void zza(zzatx zzatx, SQLiteDatabase sQLiteDatabase) {
        if (zzatx == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        int i = Build.VERSION.SDK_INT;
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzatx.zzMa().log("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzatx.zzMa().log("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzatx.zzMa().log("Failed to turn on database read permission for owner");
        }
        if (!file.setWritable(true, true)) {
            zzatx.zzMa().log("Failed to turn on database write permission for owner");
        }
    }

    @WorkerThread
    static void zza(zzatx zzatx, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Map<String, String> map) throws SQLiteException {
        if (zzatx == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(zzatx, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        try {
            zza(zzatx, sQLiteDatabase, str, str3, map);
        } catch (SQLiteException e) {
            zzatx.zzLY().zzj("Failed to verify columns on table that was just created", str);
            throw e;
        }
    }

    @WorkerThread
    static void zza(zzatx zzatx, SQLiteDatabase sQLiteDatabase, String str, String str2, Map<String, String> map) throws SQLiteException {
        if (zzatx == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Set<String> zzb2 = zzb(sQLiteDatabase, str);
        for (String str3 : str2.split(",")) {
            if (!zzb2.remove(str3)) {
                StringBuilder sb = new StringBuilder(35 + String.valueOf(str).length() + String.valueOf(str3).length());
                sb.append("Table ");
                sb.append(str);
                sb.append(" is missing required column: ");
                sb.append(str3);
                throw new SQLiteException(sb.toString());
            }
        }
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                if (!zzb2.remove(next.getKey())) {
                    sQLiteDatabase.execSQL((String) next.getValue());
                }
            }
        }
        if (!zzb2.isEmpty()) {
            zzatx.zzMa().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb2));
        }
    }

    @WorkerThread
    private void zza(String str, zzauu.zza zza2) {
        boolean z;
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zza2);
        zzac.zzw(zza2.zzbwm);
        zzac.zzw(zza2.zzbwl);
        if (zza2.zzbwk == null) {
            zzKl().zzMa().zzj("Audience with no ID. appId", zzatx.zzfE(str));
            return;
        }
        int intValue = zza2.zzbwk.intValue();
        for (zzauu.zzb zzb2 : zza2.zzbwm) {
            if (zzb2.zzbwo == null) {
                zzKl().zzMa().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzatx.zzfE(str), zza2.zzbwk);
                return;
            }
        }
        for (zzauu.zze zze : zza2.zzbwl) {
            if (zze.zzbwo == null) {
                zzKl().zzMa().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzatx.zzfE(str), zza2.zzbwk);
                return;
            }
        }
        zzauu.zzb[] zzbArr = zza2.zzbwm;
        int length = zzbArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else if (!zza(str, intValue, zzbArr[i])) {
                z = false;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            zzauu.zze[] zzeArr = zza2.zzbwl;
            int length2 = zzeArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    break;
                } else if (!zza(str, intValue, zzeArr[i2])) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (!z) {
            zzA(str, intValue);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0049  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean zza(com.google.android.gms.internal.zzatx r11, android.database.sqlite.SQLiteDatabase r12, java.lang.String r13) {
        /*
            if (r11 != 0) goto L_0x000a
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "Monitor must not be null"
            r11.<init>(r12)
            throw r11
        L_0x000a:
            r0 = 0
            r1 = 0
            java.lang.String r3 = "SQLITE_MASTER"
            r2 = 1
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r5 = "name"
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r5 = "name=?"
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0037 }
            r6[r1] = r13     // Catch:{ SQLiteException -> 0x0037 }
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r12
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0037 }
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x0030, all -> 0x002d }
            if (r12 == 0) goto L_0x002c
            r12.close()
        L_0x002c:
            return r0
        L_0x002d:
            r11 = move-exception
            r0 = r12
            goto L_0x0047
        L_0x0030:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
            goto L_0x0038
        L_0x0035:
            r11 = move-exception
            goto L_0x0047
        L_0x0037:
            r12 = move-exception
        L_0x0038:
            com.google.android.gms.internal.zzatx$zza r11 = r11.zzMa()     // Catch:{ all -> 0x0035 }
            java.lang.String r2 = "Error querying for table"
            r11.zze(r2, r13, r12)     // Catch:{ all -> 0x0035 }
            if (r0 == 0) goto L_0x0046
            r0.close()
        L_0x0046:
            return r1
        L_0x0047:
            if (r0 == 0) goto L_0x004c
            r0.close()
        L_0x004c:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zza(com.google.android.gms.internal.zzatx, android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    @WorkerThread
    private boolean zza(String str, int i, zzauu.zzb zzb2) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zzb2);
        if (TextUtils.isEmpty(zzb2.zzbwp)) {
            zzKl().zzMa().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzatx.zzfE(str), Integer.valueOf(i), String.valueOf(zzb2.zzbwo));
            return false;
        }
        try {
            byte[] bArr = new byte[zzb2.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zzb2.zza(zzag);
            zzag.zzaeG();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzb2.zzbwo);
            contentValues.put("event_name", zzb2.zzbwp);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", (String) null, contentValues, 5) != -1) {
                    return true;
                }
                zzKl().zzLY().zzj("Failed to insert event filter (got -1). appId", zzatx.zzfE(str));
                return true;
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing event filter. appId", zzatx.zzfE(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Configuration loss. Failed to serialize event filter. appId", zzatx.zzfE(str), e2);
            return false;
        }
    }

    @WorkerThread
    private boolean zza(String str, int i, zzauu.zze zze) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zze);
        if (TextUtils.isEmpty(zze.zzbwE)) {
            zzKl().zzMa().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzatx.zzfE(str), Integer.valueOf(i), String.valueOf(zze.zzbwo));
            return false;
        }
        try {
            byte[] bArr = new byte[zze.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zze.zza(zzag);
            zzag.zzaeG();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zze.zzbwo);
            contentValues.put("property_name", zze.zzbwE);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", (String) null, contentValues, 5) != -1) {
                    return true;
                }
                zzKl().zzLY().zzj("Failed to insert property filter (got -1). appId", zzatx.zzfE(str));
                return false;
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing property filter. appId", zzatx.zzfE(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Configuration loss. Failed to serialize property filter. appId", zzatx.zzfE(str), e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003b  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long zzb(java.lang.String r4, java.lang.String[] r5) {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.getWritableDatabase()
            r1 = 0
            android.database.Cursor r5 = r0.rawQuery(r4, r5)     // Catch:{ SQLiteException -> 0x002a }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r0 == 0) goto L_0x001a
            r0 = 0
            long r0 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r5 == 0) goto L_0x0019
            r5.close()
        L_0x0019:
            return r0
        L_0x001a:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            java.lang.String r1 = "Database returned empty set"
            r0.<init>(r1)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            throw r0     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
        L_0x0022:
            r4 = move-exception
            goto L_0x0039
        L_0x0024:
            r0 = move-exception
            r1 = r5
            goto L_0x002b
        L_0x0027:
            r4 = move-exception
            r5 = r1
            goto L_0x0039
        L_0x002a:
            r0 = move-exception
        L_0x002b:
            com.google.android.gms.internal.zzatx r5 = r3.zzKl()     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = "Database error"
            r5.zze(r2, r4, r0)     // Catch:{ all -> 0x0027 }
            throw r0     // Catch:{ all -> 0x0027 }
        L_0x0039:
            if (r5 == 0) goto L_0x003e
            r5.close()
        L_0x003e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzb(java.lang.String, java.lang.String[]):long");
    }

    @WorkerThread
    static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder(22 + String.valueOf(str).length());
        sb.append("SELECT * FROM ");
        sb.append(str);
        sb.append(" LIMIT 0");
        Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), (String[]) null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    @WorkerThread
    public void beginTransaction() {
        zzob();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public void endTransaction() {
        zzob();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public SQLiteDatabase getWritableDatabase() {
        zzmR();
        try {
            return this.zzbrl.getWritableDatabase();
        } catch (SQLiteException e) {
            zzKl().zzMa().zzj("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public void setTransactionSuccessful() {
        zzob();
        getWritableDatabase().setTransactionSuccessful();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzA(String str, int i) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(i)});
        writableDatabase.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(i)});
    }

    public void zzJ(List<Long> list) {
        zzac.zzw(list);
        zzmR();
        zzob();
        StringBuilder sb = new StringBuilder("rowid in (");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(list.get(i).longValue());
        }
        sb.append(")");
        int delete = getWritableDatabase().delete("raw_events", sb.toString(), (String[]) null);
        if (delete != list.size()) {
            zzKl().zzLY().zze("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zzLD() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r2 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r2
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x002b
        L_0x0024:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003f
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            com.google.android.gms.internal.zzatx r3 = r6.zzKl()     // Catch:{ all -> 0x003e }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzj(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzLD():java.lang.String");
    }

    public boolean zzLE() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzLF() {
        zzmR();
        zzob();
        if (zzLM()) {
            long j = zzKm().zzbtc.get();
            long elapsedRealtime = zznR().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzKn().zzLk()) {
                zzKm().zzbtc.set(elapsedRealtime);
                zzLG();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzLG() {
        int delete;
        zzmR();
        zzob();
        if (zzLM() && (delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zznR().currentTimeMillis()), String.valueOf(zzKn().zzLj())})) > 0) {
            zzKl().zzMe().zzj("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
        }
    }

    @WorkerThread
    public long zzLH() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    @WorkerThread
    public long zzLI() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public boolean zzLJ() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public boolean zzLK() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long zzLL() {
        /*
            r7 = this;
            r0 = -1
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x002e }
            java.lang.String r4 = "select rowid from raw_events order by rowid desc limit 1;"
            android.database.Cursor r3 = r3.rawQuery(r4, r2)     // Catch:{ SQLiteException -> 0x002e }
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0027, all -> 0x0024 }
            if (r2 != 0) goto L_0x0019
            if (r3 == 0) goto L_0x0018
            r3.close()
        L_0x0018:
            return r0
        L_0x0019:
            r2 = 0
            long r4 = r3.getLong(r2)     // Catch:{ SQLiteException -> 0x0027, all -> 0x0024 }
            if (r3 == 0) goto L_0x0023
            r3.close()
        L_0x0023:
            return r4
        L_0x0024:
            r0 = move-exception
            r2 = r3
            goto L_0x0042
        L_0x0027:
            r2 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x002f
        L_0x002c:
            r0 = move-exception
            goto L_0x0042
        L_0x002e:
            r3 = move-exception
        L_0x002f:
            com.google.android.gms.internal.zzatx r4 = r7.zzKl()     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x002c }
            java.lang.String r5 = "Error querying raw events"
            r4.zzj(r5, r3)     // Catch:{ all -> 0x002c }
            if (r2 == 0) goto L_0x0041
            r2.close()
        L_0x0041:
            return r0
        L_0x0042:
            if (r2 == 0) goto L_0x0047
            r2.close()
        L_0x0047:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzLL():long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzatn zzQ(java.lang.String r16, java.lang.String r17) {
        /*
            r15 = this;
            r10 = r17
            com.google.android.gms.common.internal.zzac.zzdr(r16)
            com.google.android.gms.common.internal.zzac.zzdr(r17)
            r15.zzmR()
            r15.zzob()
            r11 = 0
            android.database.sqlite.SQLiteDatabase r1 = r15.getWritableDatabase()     // Catch:{ SQLiteException -> 0x007b, all -> 0x0077 }
            java.lang.String r2 = "events"
            r3 = 3
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x007b, all -> 0x0077 }
            java.lang.String r4 = "lifetime_count"
            r9 = 0
            r3[r9] = r4     // Catch:{ SQLiteException -> 0x007b, all -> 0x0077 }
            java.lang.String r4 = "current_bundle_count"
            r12 = 1
            r3[r12] = r4     // Catch:{ SQLiteException -> 0x007b, all -> 0x0077 }
            java.lang.String r4 = "last_fire_timestamp"
            r13 = 2
            r3[r13] = r4     // Catch:{ SQLiteException -> 0x007b, all -> 0x0077 }
            java.lang.String r4 = "app_id=? and name=?"
            java.lang.String[] r5 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x007b, all -> 0x0077 }
            r5[r9] = r16     // Catch:{ SQLiteException -> 0x007b, all -> 0x0077 }
            r5[r12] = r10     // Catch:{ SQLiteException -> 0x007b, all -> 0x0077 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x007b, all -> 0x0077 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 != 0) goto L_0x0042
            if (r14 == 0) goto L_0x0041
            r14.close()
        L_0x0041:
            return r11
        L_0x0042:
            long r4 = r14.getLong(r9)     // Catch:{ SQLiteException -> 0x0074 }
            long r6 = r14.getLong(r12)     // Catch:{ SQLiteException -> 0x0074 }
            long r8 = r14.getLong(r13)     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.internal.zzatn r12 = new com.google.android.gms.internal.zzatn     // Catch:{ SQLiteException -> 0x0074 }
            r1 = r12
            r2 = r16
            r3 = r10
            r1.<init>(r2, r3, r4, r6, r8)     // Catch:{ SQLiteException -> 0x0074 }
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x006e
            com.google.android.gms.internal.zzatx r1 = r15.zzKl()     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.String r2 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r16)     // Catch:{ SQLiteException -> 0x0074 }
            r1.zzj(r2, r3)     // Catch:{ SQLiteException -> 0x0074 }
        L_0x006e:
            if (r14 == 0) goto L_0x0073
            r14.close()
        L_0x0073:
            return r12
        L_0x0074:
            r0 = move-exception
            r1 = r0
            goto L_0x007e
        L_0x0077:
            r0 = move-exception
            r1 = r0
            r14 = r11
            goto L_0x0097
        L_0x007b:
            r0 = move-exception
            r1 = r0
            r14 = r11
        L_0x007e:
            com.google.android.gms.internal.zzatx r2 = r15.zzKl()     // Catch:{ all -> 0x0095 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x0095 }
            java.lang.String r3 = "Error querying events. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r16)     // Catch:{ all -> 0x0095 }
            r2.zzd(r3, r4, r10, r1)     // Catch:{ all -> 0x0095 }
            if (r14 == 0) goto L_0x0094
            r14.close()
        L_0x0094:
            return r11
        L_0x0095:
            r0 = move-exception
            r1 = r0
        L_0x0097:
            if (r14 == 0) goto L_0x009c
            r14.close()
        L_0x009c:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzQ(java.lang.String, java.lang.String):com.google.android.gms.internal.zzatn");
    }

    @WorkerThread
    public void zzR(String str, String str2) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzob();
        try {
            zzKl().zzMe().zzj("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzKl().zzLY().zzd("Error deleting user attribute. appId", zzatx.zzfE(str), str2, e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a9  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzaus zzS(java.lang.String r20, java.lang.String r21) {
        /*
            r19 = this;
            r8 = r21
            com.google.android.gms.common.internal.zzac.zzdr(r20)
            com.google.android.gms.common.internal.zzac.zzdr(r21)
            r19.zzmR()
            r19.zzob()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r19.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r11 = "user_attributes"
            r1 = 3
            java.lang.String[] r12 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r1 = "set_timestamp"
            r2 = 0
            r12[r2] = r1     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r1 = "value"
            r3 = 1
            r12[r3] = r1     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r1 = "origin"
            r4 = 2
            r12[r4] = r1     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            java.lang.String r13 = "app_id=? and name=?"
            java.lang.String[] r14 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            r14[r2] = r20     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            r14[r3] = r8     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x0089, all -> 0x0083 }
            boolean r1 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x007e, all -> 0x007a }
            if (r1 != 0) goto L_0x0044
            if (r10 == 0) goto L_0x0043
            r10.close()
        L_0x0043:
            return r9
        L_0x0044:
            long r5 = r10.getLong(r2)     // Catch:{ SQLiteException -> 0x007e, all -> 0x007a }
            r11 = r19
            java.lang.Object r7 = r11.zzb((android.database.Cursor) r10, (int) r3)     // Catch:{ SQLiteException -> 0x0078 }
            java.lang.String r3 = r10.getString(r4)     // Catch:{ SQLiteException -> 0x0078 }
            com.google.android.gms.internal.zzaus r12 = new com.google.android.gms.internal.zzaus     // Catch:{ SQLiteException -> 0x0078 }
            r1 = r12
            r2 = r20
            r4 = r8
            r1.<init>(r2, r3, r4, r5, r7)     // Catch:{ SQLiteException -> 0x0078 }
            boolean r1 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0078 }
            if (r1 == 0) goto L_0x0072
            com.google.android.gms.internal.zzatx r1 = r19.zzKl()     // Catch:{ SQLiteException -> 0x0078 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ SQLiteException -> 0x0078 }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r20)     // Catch:{ SQLiteException -> 0x0078 }
            r1.zzj(r2, r3)     // Catch:{ SQLiteException -> 0x0078 }
        L_0x0072:
            if (r10 == 0) goto L_0x0077
            r10.close()
        L_0x0077:
            return r12
        L_0x0078:
            r0 = move-exception
            goto L_0x0081
        L_0x007a:
            r0 = move-exception
            r11 = r19
            goto L_0x00a6
        L_0x007e:
            r0 = move-exception
            r11 = r19
        L_0x0081:
            r1 = r0
            goto L_0x008e
        L_0x0083:
            r0 = move-exception
            r11 = r19
            r1 = r0
            r10 = r9
            goto L_0x00a7
        L_0x0089:
            r0 = move-exception
            r11 = r19
            r1 = r0
            r10 = r9
        L_0x008e:
            com.google.android.gms.internal.zzatx r2 = r19.zzKl()     // Catch:{ all -> 0x00a5 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x00a5 }
            java.lang.String r3 = "Error querying user property. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r20)     // Catch:{ all -> 0x00a5 }
            r2.zzd(r3, r4, r8, r1)     // Catch:{ all -> 0x00a5 }
            if (r10 == 0) goto L_0x00a4
            r10.close()
        L_0x00a4:
            return r9
        L_0x00a5:
            r0 = move-exception
        L_0x00a6:
            r1 = r0
        L_0x00a7:
            if (r10 == 0) goto L_0x00ac
            r10.close()
        L_0x00ac:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzS(java.lang.String, java.lang.String):com.google.android.gms.internal.zzaus");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0145  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzatg zzT(java.lang.String r34, java.lang.String r35) {
        /*
            r33 = this;
            r7 = r35
            com.google.android.gms.common.internal.zzac.zzdr(r34)
            com.google.android.gms.common.internal.zzac.zzdr(r35)
            r33.zzmR()
            r33.zzob()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r33.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r10 = "conditional_properties"
            r1 = 11
            java.lang.String[] r11 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "origin"
            r2 = 0
            r11[r2] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "value"
            r3 = 1
            r11[r3] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "active"
            r4 = 2
            r11[r4] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "trigger_event_name"
            r5 = 3
            r11[r5] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "trigger_timeout"
            r6 = 4
            r11[r6] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "timed_out_event"
            r15 = 5
            r11[r15] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "creation_timestamp"
            r14 = 6
            r11[r14] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "triggered_event"
            r13 = 7
            r11[r13] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "triggered_timestamp"
            r12 = 8
            r11[r12] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "time_to_live"
            r6 = 9
            r11[r6] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "expired_event"
            r6 = 10
            r11[r6] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            java.lang.String r1 = "app_id=? and name=?"
            java.lang.String[] r13 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            r13[r2] = r34     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            r13[r3] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            r16 = 0
            r17 = 0
            r18 = 0
            r6 = r12
            r12 = r1
            r1 = 7
            r6 = r14
            r14 = r16
            r1 = r15
            r15 = r17
            r16 = r18
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x0125, all -> 0x011f }
            boolean r10 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x011a, all -> 0x0116 }
            if (r10 != 0) goto L_0x007c
            if (r9 == 0) goto L_0x007b
            r9.close()
        L_0x007b:
            return r8
        L_0x007c:
            java.lang.String r19 = r9.getString(r2)     // Catch:{ SQLiteException -> 0x011a, all -> 0x0116 }
            r10 = r33
            java.lang.Object r11 = r10.zzb((android.database.Cursor) r9, (int) r3)     // Catch:{ SQLiteException -> 0x0114 }
            int r4 = r9.getInt(r4)     // Catch:{ SQLiteException -> 0x0114 }
            if (r4 == 0) goto L_0x008f
            r23 = r3
            goto L_0x0091
        L_0x008f:
            r23 = r2
        L_0x0091:
            java.lang.String r24 = r9.getString(r5)     // Catch:{ SQLiteException -> 0x0114 }
            r2 = 4
            long r26 = r9.getLong(r2)     // Catch:{ SQLiteException -> 0x0114 }
            com.google.android.gms.internal.zzaut r2 = r33.zzKh()     // Catch:{ SQLiteException -> 0x0114 }
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x0114 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r3 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0114 }
            android.os.Parcelable r1 = r2.zzb((byte[]) r1, r3)     // Catch:{ SQLiteException -> 0x0114 }
            r25 = r1
            com.google.android.gms.internal.zzatq r25 = (com.google.android.gms.internal.zzatq) r25     // Catch:{ SQLiteException -> 0x0114 }
            long r21 = r9.getLong(r6)     // Catch:{ SQLiteException -> 0x0114 }
            com.google.android.gms.internal.zzaut r1 = r33.zzKh()     // Catch:{ SQLiteException -> 0x0114 }
            r2 = 7
            byte[] r2 = r9.getBlob(r2)     // Catch:{ SQLiteException -> 0x0114 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r3 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0114 }
            android.os.Parcelable r1 = r1.zzb((byte[]) r2, r3)     // Catch:{ SQLiteException -> 0x0114 }
            r28 = r1
            com.google.android.gms.internal.zzatq r28 = (com.google.android.gms.internal.zzatq) r28     // Catch:{ SQLiteException -> 0x0114 }
            r1 = 8
            long r3 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x0114 }
            r1 = 9
            long r29 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x0114 }
            com.google.android.gms.internal.zzaut r1 = r33.zzKh()     // Catch:{ SQLiteException -> 0x0114 }
            r2 = 10
            byte[] r2 = r9.getBlob(r2)     // Catch:{ SQLiteException -> 0x0114 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r5 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0114 }
            android.os.Parcelable r1 = r1.zzb((byte[]) r2, r5)     // Catch:{ SQLiteException -> 0x0114 }
            r31 = r1
            com.google.android.gms.internal.zzatq r31 = (com.google.android.gms.internal.zzatq) r31     // Catch:{ SQLiteException -> 0x0114 }
            com.google.android.gms.internal.zzauq r20 = new com.google.android.gms.internal.zzauq     // Catch:{ SQLiteException -> 0x0114 }
            r1 = r20
            r2 = r7
            r5 = r11
            r6 = r19
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x0114 }
            com.google.android.gms.internal.zzatg r1 = new com.google.android.gms.internal.zzatg     // Catch:{ SQLiteException -> 0x0114 }
            r17 = r1
            r18 = r34
            r17.<init>(r18, r19, r20, r21, r23, r24, r25, r26, r28, r29, r31)     // Catch:{ SQLiteException -> 0x0114 }
            boolean r2 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x0114 }
            if (r2 == 0) goto L_0x010e
            com.google.android.gms.internal.zzatx r2 = r33.zzKl()     // Catch:{ SQLiteException -> 0x0114 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0114 }
            java.lang.String r3 = "Got multiple records for conditional property, expected one"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r34)     // Catch:{ SQLiteException -> 0x0114 }
            r2.zze(r3, r4, r7)     // Catch:{ SQLiteException -> 0x0114 }
        L_0x010e:
            if (r9 == 0) goto L_0x0113
            r9.close()
        L_0x0113:
            return r1
        L_0x0114:
            r0 = move-exception
            goto L_0x011d
        L_0x0116:
            r0 = move-exception
            r10 = r33
            goto L_0x0142
        L_0x011a:
            r0 = move-exception
            r10 = r33
        L_0x011d:
            r1 = r0
            goto L_0x012a
        L_0x011f:
            r0 = move-exception
            r10 = r33
            r1 = r0
            r9 = r8
            goto L_0x0143
        L_0x0125:
            r0 = move-exception
            r10 = r33
            r1 = r0
            r9 = r8
        L_0x012a:
            com.google.android.gms.internal.zzatx r2 = r33.zzKl()     // Catch:{ all -> 0x0141 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x0141 }
            java.lang.String r3 = "Error querying conditional property"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r34)     // Catch:{ all -> 0x0141 }
            r2.zzd(r3, r4, r7, r1)     // Catch:{ all -> 0x0141 }
            if (r9 == 0) goto L_0x0140
            r9.close()
        L_0x0140:
            return r8
        L_0x0141:
            r0 = move-exception
        L_0x0142:
            r1 = r0
        L_0x0143:
            if (r9 == 0) goto L_0x0148
            r9.close()
        L_0x0148:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzT(java.lang.String, java.lang.String):com.google.android.gms.internal.zzatg");
    }

    @WorkerThread
    public int zzU(String str, String str2) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzob();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzKl().zzLY().zzd("Error deleting conditional property", zzatx.zzfE(str), str2, e);
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzauu.zzb>> zzV(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzob()
            r12.zzmR()
            com.google.android.gms.common.internal.zzac.zzdr(r13)
            com.google.android.gms.common.internal.zzac.zzdr(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "event_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            java.lang.String r5 = "app_id=? AND event_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            r6[r10] = r13     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            r6[r11] = r14     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0096 }
            if (r1 != 0) goto L_0x0048
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0096 }
            if (r14 == 0) goto L_0x0047
            r14.close()
        L_0x0047:
            return r0
        L_0x0048:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0096 }
            com.google.android.gms.internal.zzbxl r1 = com.google.android.gms.internal.zzbxl.zzaf(r1)     // Catch:{ SQLiteException -> 0x0096 }
            com.google.android.gms.internal.zzauu$zzb r2 = new com.google.android.gms.internal.zzauu$zzb     // Catch:{ SQLiteException -> 0x0096 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0096 }
            r2.zzb(r1)     // Catch:{ IOException -> 0x0078 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0096 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0096 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0096 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0096 }
            if (r3 != 0) goto L_0x0074
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0096 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0096 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0096 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0096 }
        L_0x0074:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0096 }
            goto L_0x008a
        L_0x0078:
            r1 = move-exception
            com.google.android.gms.internal.zzatx r2 = r12.zzKl()     // Catch:{ SQLiteException -> 0x0096 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0096 }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r13)     // Catch:{ SQLiteException -> 0x0096 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0096 }
        L_0x008a:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0096 }
            if (r1 != 0) goto L_0x0048
            if (r14 == 0) goto L_0x0095
            r14.close()
        L_0x0095:
            return r0
        L_0x0096:
            r0 = move-exception
            goto L_0x009d
        L_0x0098:
            r13 = move-exception
            r14 = r9
            goto L_0x00b5
        L_0x009b:
            r0 = move-exception
            r14 = r9
        L_0x009d:
            com.google.android.gms.internal.zzatx r1 = r12.zzKl()     // Catch:{ all -> 0x00b4 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ all -> 0x00b4 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.zzatx.zzfE(r13)     // Catch:{ all -> 0x00b4 }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00b4 }
            if (r14 == 0) goto L_0x00b3
            r14.close()
        L_0x00b3:
            return r9
        L_0x00b4:
            r13 = move-exception
        L_0x00b5:
            if (r14 == 0) goto L_0x00ba
            r14.close()
        L_0x00ba:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzV(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzauu.zze>> zzW(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzob()
            r12.zzmR()
            com.google.android.gms.common.internal.zzac.zzdr(r13)
            com.google.android.gms.common.internal.zzac.zzdr(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "property_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            java.lang.String r5 = "app_id=? AND property_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            r6[r10] = r13     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            r6[r11] = r14     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x009b, all -> 0x0098 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0096 }
            if (r1 != 0) goto L_0x0048
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0096 }
            if (r14 == 0) goto L_0x0047
            r14.close()
        L_0x0047:
            return r0
        L_0x0048:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0096 }
            com.google.android.gms.internal.zzbxl r1 = com.google.android.gms.internal.zzbxl.zzaf(r1)     // Catch:{ SQLiteException -> 0x0096 }
            com.google.android.gms.internal.zzauu$zze r2 = new com.google.android.gms.internal.zzauu$zze     // Catch:{ SQLiteException -> 0x0096 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0096 }
            r2.zzb(r1)     // Catch:{ IOException -> 0x0078 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0096 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0096 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0096 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0096 }
            if (r3 != 0) goto L_0x0074
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0096 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0096 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0096 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0096 }
        L_0x0074:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0096 }
            goto L_0x008a
        L_0x0078:
            r1 = move-exception
            com.google.android.gms.internal.zzatx r2 = r12.zzKl()     // Catch:{ SQLiteException -> 0x0096 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0096 }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r13)     // Catch:{ SQLiteException -> 0x0096 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0096 }
        L_0x008a:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0096 }
            if (r1 != 0) goto L_0x0048
            if (r14 == 0) goto L_0x0095
            r14.close()
        L_0x0095:
            return r0
        L_0x0096:
            r0 = move-exception
            goto L_0x009d
        L_0x0098:
            r13 = move-exception
            r14 = r9
            goto L_0x00b5
        L_0x009b:
            r0 = move-exception
            r14 = r9
        L_0x009d:
            com.google.android.gms.internal.zzatx r1 = r12.zzKl()     // Catch:{ all -> 0x00b4 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ all -> 0x00b4 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.zzatx.zzfE(r13)     // Catch:{ all -> 0x00b4 }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00b4 }
            if (r14 == 0) goto L_0x00b3
            r14.close()
        L_0x00b3:
            return r9
        L_0x00b4:
            r13 = move-exception
        L_0x00b5:
            if (r14 == 0) goto L_0x00ba
            r14.close()
        L_0x00ba:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzW(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public long zzX(String str, String str2) {
        long j;
        SQLiteException sQLiteException;
        String str3 = str;
        String str4 = str2;
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzob();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(32 + String.valueOf(str2).length());
            sb.append("select ");
            sb.append(str4);
            sb.append(" from app2 where app_id=?");
            try {
                j = zza(sb.toString(), new String[]{str3}, -1);
                if (j == -1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str3);
                    contentValues.put("first_open_count", 0);
                    contentValues.put("previous_install_count", 0);
                    if (writableDatabase.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                        zzKl().zzLY().zze("Failed to insert column (got -1). appId", zzatx.zzfE(str), str4);
                        writableDatabase.endTransaction();
                        return -1;
                    }
                    j = 0;
                }
                try {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str3);
                    contentValues2.put(str4, Long.valueOf(j + 1));
                    if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str3})) == 0) {
                        zzKl().zzLY().zze("Failed to update column (got 0). appId", zzatx.zzfE(str), str4);
                        writableDatabase.endTransaction();
                        return -1;
                    }
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    return j;
                } catch (SQLiteException e) {
                    sQLiteException = e;
                }
            } catch (SQLiteException e2) {
                e = e2;
                sQLiteException = e;
                j = 0;
                try {
                    zzKl().zzLY().zzd("Error inserting column. appId", zzatx.zzfE(str), str4, sQLiteException);
                    writableDatabase.endTransaction();
                    return j;
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    writableDatabase.endTransaction();
                    throw th2;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            sQLiteException = e;
            j = 0;
            zzKl().zzLY().zzd("Error inserting column. appId", zzatx.zzfE(str), str4, sQLiteException);
            writableDatabase.endTransaction();
            return j;
        } catch (Throwable th3) {
            th = th3;
            Throwable th22 = th;
            writableDatabase.endTransaction();
            throw th22;
        }
    }

    public long zza(zzauw.zze zze) throws IOException {
        zzmR();
        zzob();
        zzac.zzw(zze);
        zzac.zzdr(zze.zzaS);
        try {
            byte[] bArr = new byte[zze.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zze.zza(zzag);
            zzag.zzaeG();
            long zzz = zzKh().zzz(bArr);
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zze.zzaS);
            contentValues.put("metadata_fingerprint", Long.valueOf(zzz));
            contentValues.put(TtmlNode.TAG_METADATA, bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", (String) null, contentValues, 4);
                return zzz;
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing raw event metadata. appId", zzatx.zzfE(zze.zzaS), e);
                throw e;
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Data loss. Failed to serialize event metadata. appId", zzatx.zzfE(zze.zzaS), e2);
            throw e2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0144  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzatj.zza zza(long r22, java.lang.String r24, boolean r25, boolean r26, boolean r27, boolean r28, boolean r29) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.zzac.zzdr(r24)
            r21.zzmR()
            r21.zzob()
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]
            r4 = 0
            r3[r4] = r24
            com.google.android.gms.internal.zzatj$zza r5 = new com.google.android.gms.internal.zzatj$zza
            r5.<init>()
            android.database.sqlite.SQLiteDatabase r15 = r21.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r8 = "apps"
            r7 = 6
            java.lang.String[] r9 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "day"
            r9[r4] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_events_count"
            r9[r2] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_public_events_count"
            r14 = 2
            r9[r14] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_conversions_count"
            r13 = 3
            r9[r13] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_error_events_count"
            r12 = 4
            r9[r12] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r7 = "daily_realtime_events_count"
            r11 = 5
            r9[r11] = r7     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            java.lang.String r10 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            r7[r4] = r24     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = r7
            r7 = r15
            r6 = r11
            r11 = r19
            r6 = r12
            r12 = r16
            r6 = r13
            r13 = r17
            r6 = r14
            r14 = r18
            android.database.Cursor r7 = r7.query(r8, r9, r10, r11, r12, r13, r14)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0121 }
            boolean r8 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            if (r8 != 0) goto L_0x0075
            com.google.android.gms.internal.zzatx r2 = r21.zzKl()     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMa()     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r3 = "Not updating daily counts, app is not known. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r24)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r2.zzj(r3, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            if (r7 == 0) goto L_0x0074
            r7.close()
        L_0x0074:
            return r5
        L_0x0075:
            long r8 = r7.getLong(r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            int r4 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r4 != 0) goto L_0x009e
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.zzbro = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r8 = r7.getLong(r6)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.zzbrn = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r2 = 3
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.zzbrp = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r2 = 4
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.zzbrq = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r2 = 5
            long r8 = r7.getLong(r2)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r5.zzbrr = r8     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x009e:
            r8 = 1
            if (r25 == 0) goto L_0x00a8
            long r12 = r5.zzbro     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r1 = r12 + r8
            r5.zzbro = r1     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00a8:
            if (r26 == 0) goto L_0x00b0
            long r1 = r5.zzbrn     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r12 = r1 + r8
            r5.zzbrn = r12     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00b0:
            if (r27 == 0) goto L_0x00b8
            long r1 = r5.zzbrp     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r12 = r1 + r8
            r5.zzbrp = r12     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00b8:
            if (r28 == 0) goto L_0x00c0
            long r1 = r5.zzbrq     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r12 = r1 + r8
            r5.zzbrq = r12     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00c0:
            if (r29 == 0) goto L_0x00c8
            long r1 = r5.zzbrr     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            long r12 = r1 + r8
            r5.zzbrr = r12     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
        L_0x00c8:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.<init>()     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "day"
            java.lang.Long r4 = java.lang.Long.valueOf(r22)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_public_events_count"
            long r8 = r5.zzbrn     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_events_count"
            long r8 = r5.zzbro     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_conversions_count"
            long r8 = r5.zzbrp     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_error_events_count"
            long r8 = r5.zzbrq     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "daily_realtime_events_count"
            long r8 = r5.zzbrr     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            java.lang.String r2 = "apps"
            java.lang.String r4 = "app_id=?"
            r15.update(r2, r1, r4, r3)     // Catch:{ SQLiteException -> 0x011d, all -> 0x011a }
            if (r7 == 0) goto L_0x0119
            r7.close()
        L_0x0119:
            return r5
        L_0x011a:
            r0 = move-exception
            r1 = r0
            goto L_0x0142
        L_0x011d:
            r0 = move-exception
            r1 = r0
            r6 = r7
            goto L_0x0128
        L_0x0121:
            r0 = move-exception
            r1 = r0
            r7 = 0
            goto L_0x0142
        L_0x0125:
            r0 = move-exception
            r1 = r0
            r6 = 0
        L_0x0128:
            com.google.android.gms.internal.zzatx r2 = r21.zzKl()     // Catch:{ all -> 0x013f }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x013f }
            java.lang.String r3 = "Error updating daily counts. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r24)     // Catch:{ all -> 0x013f }
            r2.zze(r3, r4, r1)     // Catch:{ all -> 0x013f }
            if (r6 == 0) goto L_0x013e
            r6.close()
        L_0x013e:
            return r5
        L_0x013f:
            r0 = move-exception
            r1 = r0
            r7 = r6
        L_0x0142:
            if (r7 == 0) goto L_0x0147
            r7.close()
        L_0x0147:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zza(long, java.lang.String, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.internal.zzatj$zza");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zza(ContentValues contentValues, String str, Object obj) {
        zzac.zzdr(str);
        zzac.zzw(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    public void zza(zzatc zzatc) {
        zzac.zzw(zzatc);
        zzmR();
        zzob();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzatc.zzke());
        contentValues.put("app_instance_id", zzatc.getAppInstanceId());
        contentValues.put("gmp_app_id", zzatc.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzatc.zzKp());
        contentValues.put("last_bundle_index", Long.valueOf(zzatc.zzKy()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzatc.zzKr()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzatc.zzKs()));
        contentValues.put("app_version", zzatc.zzmZ());
        contentValues.put("app_store", zzatc.zzKu());
        contentValues.put("gmp_version", Long.valueOf(zzatc.zzKv()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzatc.zzKw()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzatc.zzKx()));
        contentValues.put("day", Long.valueOf(zzatc.zzKC()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzatc.zzKD()));
        contentValues.put("daily_events_count", Long.valueOf(zzatc.zzKE()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzatc.zzKF()));
        contentValues.put("config_fetched_time", Long.valueOf(zzatc.zzKz()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzatc.zzKA()));
        contentValues.put("app_version_int", Long.valueOf(zzatc.zzKt()));
        contentValues.put("firebase_instance_id", zzatc.zzKq());
        contentValues.put("daily_error_events_count", Long.valueOf(zzatc.zzKH()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzatc.zzKG()));
        contentValues.put("health_monitor_sample", zzatc.zzKI());
        contentValues.put("android_id", Long.valueOf(zzatc.zzuW()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzatc.zzke()})) == 0 && writableDatabase.insertWithOnConflict("apps", (String) null, contentValues, 5) == -1) {
                zzKl().zzLY().zzj("Failed to insert/update app (got -1). appId", zzatx.zzfE(zzatc.zzke()));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing app. appId", zzatx.zzfE(zzatc.zzke()), e);
        }
    }

    @WorkerThread
    public void zza(zzatn zzatn) {
        zzac.zzw(zzatn);
        zzmR();
        zzob();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzatn.mAppId);
        contentValues.put("name", zzatn.mName);
        contentValues.put("lifetime_count", Long.valueOf(zzatn.zzbrA));
        contentValues.put("current_bundle_count", Long.valueOf(zzatn.zzbrB));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzatn.zzbrC));
        try {
            if (getWritableDatabase().insertWithOnConflict("events", (String) null, contentValues, 5) == -1) {
                zzKl().zzLY().zzj("Failed to insert/update event aggregates (got -1). appId", zzatx.zzfE(zzatn.mAppId));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing event aggregates. appId", zzatx.zzfE(zzatn.mAppId), e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(String str, int i, zzauw.zzf zzf) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zzf);
        try {
            byte[] bArr = new byte[zzf.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zzf.zza(zzag);
            zzag.zzaeG();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("current_results", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("audience_filter_values", (String) null, contentValues, 5) == -1) {
                    zzKl().zzLY().zzj("Failed to insert filter results (got -1). appId", zzatx.zzfE(str));
                }
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing filter results. appId", zzatx.zzfE(str), e);
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Configuration loss. Failed to serialize filter results. appId", zzatx.zzfE(str), e2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0242, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0245, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0246, code lost:
        r1 = r0;
        r6 = null;
        r5 = r25;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0242 A[ExcHandler: all (th java.lang.Throwable), PHI: r5 
      PHI: (r5v6 android.database.Cursor) = (r5v0 android.database.Cursor), (r5v17 android.database.Cursor), (r5v17 android.database.Cursor), (r5v19 android.database.Cursor), (r5v0 android.database.Cursor) binds: [B:1:0x000c, B:106:0x0212, B:107:?, B:79:0x01a0, B:26:0x008a] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x000c] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:153:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(java.lang.String r25, long r26, long r28, com.google.android.gms.internal.zzatj.zzb r30) {
        /*
            r24 = this;
            r4 = r30
            com.google.android.gms.common.internal.zzac.zzw(r30)
            r24.zzmR()
            r24.zzob()
            r5 = 0
            android.database.sqlite.SQLiteDatabase r15 = r24.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            boolean r6 = android.text.TextUtils.isEmpty(r25)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r16 = -1
            r14 = 2
            r13 = 1
            r12 = 0
            if (r6 == 0) goto L_0x0086
            int r6 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x002e
            java.lang.String[] r6 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r7 = java.lang.String.valueOf(r28)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r6[r12] = r7     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r7 = java.lang.String.valueOf(r26)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r6[r13] = r7     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            goto L_0x0036
        L_0x002e:
            java.lang.String[] r6 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r7 = java.lang.String.valueOf(r26)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r6[r12] = r7     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
        L_0x0036:
            int r7 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r7 == 0) goto L_0x003d
            java.lang.String r7 = "rowid <= ? and "
            goto L_0x003f
        L_0x003d:
            java.lang.String r7 = ""
        L_0x003f:
            r8 = 148(0x94, float:2.07E-43)
            java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            int r9 = r9.length()     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            int r8 = r8 + r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r9.<init>(r8)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r8 = "select app_id, metadata_fingerprint from raw_events where "
            r9.append(r8)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r9.append(r7)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r7 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r9.append(r7)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r7 = r9.toString()     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            android.database.Cursor r6 = r15.rawQuery(r7, r6)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            boolean r5 = r6.moveToFirst()     // Catch:{ SQLiteException -> 0x023d }
            if (r5 != 0) goto L_0x0070
            if (r6 == 0) goto L_0x006f
            r6.close()
        L_0x006f:
            return
        L_0x0070:
            java.lang.String r5 = r6.getString(r12)     // Catch:{ SQLiteException -> 0x023d }
            java.lang.String r1 = r6.getString(r13)     // Catch:{ SQLiteException -> 0x0083 }
            r6.close()     // Catch:{ SQLiteException -> 0x0083 }
            r18 = r6
            r23 = r5
            r5 = r1
            r1 = r23
            goto L_0x00de
        L_0x0083:
            r0 = move-exception
            goto L_0x0240
        L_0x0086:
            int r6 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x0095
            java.lang.String[] r6 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r6[r12] = r25     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r7 = java.lang.String.valueOf(r28)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r6[r13] = r7     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            goto L_0x0099
        L_0x0095:
            java.lang.String[] r6 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r6[r12] = r25     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
        L_0x0099:
            int r7 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r7 == 0) goto L_0x00a0
            java.lang.String r7 = " and rowid <= ?"
            goto L_0x00a2
        L_0x00a0:
            java.lang.String r7 = ""
        L_0x00a2:
            r8 = 84
            java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            int r9 = r9.length()     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            int r8 = r8 + r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r9.<init>(r8)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r8 = "select metadata_fingerprint from raw_events where app_id = ?"
            r9.append(r8)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            r9.append(r7)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r7 = " order by rowid limit 1;"
            r9.append(r7)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            java.lang.String r7 = r9.toString()     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            android.database.Cursor r6 = r15.rawQuery(r7, r6)     // Catch:{ SQLiteException -> 0x0245, all -> 0x0242 }
            boolean r5 = r6.moveToFirst()     // Catch:{ SQLiteException -> 0x023d }
            if (r5 != 0) goto L_0x00d3
            if (r6 == 0) goto L_0x00d2
            r6.close()
        L_0x00d2:
            return
        L_0x00d3:
            java.lang.String r5 = r6.getString(r12)     // Catch:{ SQLiteException -> 0x023d }
            r6.close()     // Catch:{ SQLiteException -> 0x023d }
            r1 = r25
            r18 = r6
        L_0x00de:
            java.lang.String r7 = "raw_events_metadata"
            java.lang.String[] r8 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x0238, all -> 0x0233 }
            java.lang.String r6 = "metadata"
            r8[r12] = r6     // Catch:{ SQLiteException -> 0x0238, all -> 0x0233 }
            java.lang.String r9 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r10 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0238, all -> 0x0233 }
            r10[r12] = r1     // Catch:{ SQLiteException -> 0x0238, all -> 0x0233 }
            r10[r13] = r5     // Catch:{ SQLiteException -> 0x0238, all -> 0x0233 }
            r11 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            java.lang.String r21 = "2"
            r6 = r15
            r12 = r19
            r13 = r20
            r22 = r15
            r15 = r14
            r14 = r21
            android.database.Cursor r14 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ SQLiteException -> 0x0238, all -> 0x0233 }
            boolean r6 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            if (r6 != 0) goto L_0x012a
            com.google.android.gms.internal.zzatx r2 = r24.zzKl()     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            java.lang.String r3 = "Raw event metadata record is missing. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r1)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            r2.zzj(r3, r4)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            if (r14 == 0) goto L_0x011f
            r14.close()
        L_0x011f:
            return
        L_0x0120:
            r0 = move-exception
            r1 = r0
            r5 = r14
            goto L_0x0264
        L_0x0125:
            r0 = move-exception
            r5 = r1
            r6 = r14
            goto L_0x0240
        L_0x012a:
            r13 = 0
            byte[] r6 = r14.getBlob(r13)     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            com.google.android.gms.internal.zzbxl r6 = com.google.android.gms.internal.zzbxl.zzaf(r6)     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            com.google.android.gms.internal.zzauw$zze r7 = new com.google.android.gms.internal.zzauw$zze     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            r7.<init>()     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            r7.zzb(r6)     // Catch:{ IOException -> 0x0210 }
            boolean r6 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            if (r6 == 0) goto L_0x0152
            com.google.android.gms.internal.zzatx r6 = r24.zzKl()     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            com.google.android.gms.internal.zzatx$zza r6 = r6.zzMa()     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            java.lang.String r8 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r9 = com.google.android.gms.internal.zzatx.zzfE(r1)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            r6.zzj(r8, r9)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
        L_0x0152:
            r14.close()     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            r4.zzb(r7)     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            int r6 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            r12 = 3
            if (r6 == 0) goto L_0x016f
            java.lang.String r6 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            java.lang.String[] r7 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            r7[r13] = r1     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            r11 = 1
            r7[r11] = r5     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            java.lang.String r2 = java.lang.String.valueOf(r28)     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            r7[r15] = r2     // Catch:{ SQLiteException -> 0x0125, all -> 0x0120 }
            r9 = r6
            r10 = r7
            goto L_0x017a
        L_0x016f:
            r11 = 1
            java.lang.String r2 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r3 = new java.lang.String[r15]     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            r3[r13] = r1     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            r3[r11] = r5     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            r9 = r2
            r10 = r3
        L_0x017a:
            java.lang.String r7 = "raw_events"
            r2 = 4
            java.lang.String[] r8 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            java.lang.String r2 = "rowid"
            r8[r13] = r2     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            java.lang.String r2 = "name"
            r8[r11] = r2     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            java.lang.String r2 = "timestamp"
            r8[r15] = r2     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            java.lang.String r2 = "data"
            r8[r12] = r2     // Catch:{ SQLiteException -> 0x022e, all -> 0x022b }
            r2 = 0
            r3 = 0
            java.lang.String r5 = "rowid"
            r16 = 0
            r6 = r22
            r15 = r11
            r11 = r2
            r2 = r12
            r12 = r3
            r3 = r13
            r13 = r5
            r5 = r14
            r14 = r16
            android.database.Cursor r6 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ SQLiteException -> 0x0229, all -> 0x0242 }
            boolean r5 = r6.moveToFirst()     // Catch:{ SQLiteException -> 0x020e }
            if (r5 != 0) goto L_0x01c1
            com.google.android.gms.internal.zzatx r2 = r24.zzKl()     // Catch:{ SQLiteException -> 0x020e }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMa()     // Catch:{ SQLiteException -> 0x020e }
            java.lang.String r3 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r1)     // Catch:{ SQLiteException -> 0x020e }
            r2.zzj(r3, r4)     // Catch:{ SQLiteException -> 0x020e }
            if (r6 == 0) goto L_0x01c0
            r6.close()
        L_0x01c0:
            return
        L_0x01c1:
            long r7 = r6.getLong(r3)     // Catch:{ SQLiteException -> 0x020e }
            byte[] r5 = r6.getBlob(r2)     // Catch:{ SQLiteException -> 0x020e }
            com.google.android.gms.internal.zzbxl r5 = com.google.android.gms.internal.zzbxl.zzaf(r5)     // Catch:{ SQLiteException -> 0x020e }
            com.google.android.gms.internal.zzauw$zzb r9 = new com.google.android.gms.internal.zzauw$zzb     // Catch:{ SQLiteException -> 0x020e }
            r9.<init>()     // Catch:{ SQLiteException -> 0x020e }
            r9.zzb(r5)     // Catch:{ IOException -> 0x01f2 }
            java.lang.String r5 = r6.getString(r15)     // Catch:{ SQLiteException -> 0x020e }
            r9.name = r5     // Catch:{ SQLiteException -> 0x020e }
            r5 = 2
            long r10 = r6.getLong(r5)     // Catch:{ SQLiteException -> 0x020e }
            java.lang.Long r10 = java.lang.Long.valueOf(r10)     // Catch:{ SQLiteException -> 0x020e }
            r9.zzbwZ = r10     // Catch:{ SQLiteException -> 0x020e }
            boolean r7 = r4.zza(r7, r9)     // Catch:{ SQLiteException -> 0x020e }
            if (r7 != 0) goto L_0x0205
            if (r6 == 0) goto L_0x01f1
            r6.close()
        L_0x01f1:
            return
        L_0x01f2:
            r0 = move-exception
            r5 = 2
            com.google.android.gms.internal.zzatx r7 = r24.zzKl()     // Catch:{ SQLiteException -> 0x020e }
            com.google.android.gms.internal.zzatx$zza r7 = r7.zzLY()     // Catch:{ SQLiteException -> 0x020e }
            java.lang.String r8 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r9 = com.google.android.gms.internal.zzatx.zzfE(r1)     // Catch:{ SQLiteException -> 0x020e }
            r7.zze(r8, r9, r0)     // Catch:{ SQLiteException -> 0x020e }
        L_0x0205:
            boolean r7 = r6.moveToNext()     // Catch:{ SQLiteException -> 0x020e }
            if (r7 != 0) goto L_0x01c1
            if (r6 == 0) goto L_0x0260
            goto L_0x025d
        L_0x020e:
            r0 = move-exception
            goto L_0x0231
        L_0x0210:
            r0 = move-exception
            r5 = r14
            com.google.android.gms.internal.zzatx r2 = r24.zzKl()     // Catch:{ SQLiteException -> 0x0229, all -> 0x0242 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0229, all -> 0x0242 }
            java.lang.String r3 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r1)     // Catch:{ SQLiteException -> 0x0229, all -> 0x0242 }
            r2.zze(r3, r4, r0)     // Catch:{ SQLiteException -> 0x0229, all -> 0x0242 }
            if (r5 == 0) goto L_0x0228
            r5.close()
        L_0x0228:
            return
        L_0x0229:
            r0 = move-exception
            goto L_0x0230
        L_0x022b:
            r0 = move-exception
            r5 = r14
            goto L_0x0243
        L_0x022e:
            r0 = move-exception
            r5 = r14
        L_0x0230:
            r6 = r5
        L_0x0231:
            r5 = r1
            goto L_0x0240
        L_0x0233:
            r0 = move-exception
            r1 = r0
            r5 = r18
            goto L_0x0264
        L_0x0238:
            r0 = move-exception
            r5 = r1
            r6 = r18
            goto L_0x0240
        L_0x023d:
            r0 = move-exception
            r5 = r25
        L_0x0240:
            r1 = r0
            goto L_0x024a
        L_0x0242:
            r0 = move-exception
        L_0x0243:
            r1 = r0
            goto L_0x0264
        L_0x0245:
            r0 = move-exception
            r1 = r0
            r6 = r5
            r5 = r25
        L_0x024a:
            com.google.android.gms.internal.zzatx r2 = r24.zzKl()     // Catch:{ all -> 0x0261 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x0261 }
            java.lang.String r3 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r5)     // Catch:{ all -> 0x0261 }
            r2.zze(r3, r4, r1)     // Catch:{ all -> 0x0261 }
            if (r6 == 0) goto L_0x0260
        L_0x025d:
            r6.close()
        L_0x0260:
            return
        L_0x0261:
            r0 = move-exception
            r1 = r0
            r5 = r6
        L_0x0264:
            if (r5 == 0) goto L_0x0269
            r5.close()
        L_0x0269:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zza(java.lang.String, long, long, com.google.android.gms.internal.zzatj$zzb):void");
    }

    @WorkerThread
    public boolean zza(zzatg zzatg) {
        zzac.zzw(zzatg);
        zzmR();
        zzob();
        if (zzS(zzatg.packageName, zzatg.zzbqW.name) == null) {
            long zzb2 = zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzatg.packageName});
            zzKn().zzKZ();
            if (zzb2 >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzatg.packageName);
        contentValues.put("origin", zzatg.zzbqV);
        contentValues.put("name", zzatg.zzbqW.name);
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzatg.zzbqW.getValue());
        contentValues.put("active", Boolean.valueOf(zzatg.zzbqY));
        contentValues.put("trigger_event_name", zzatg.zzbqZ);
        contentValues.put("trigger_timeout", Long.valueOf(zzatg.zzbrb));
        contentValues.put("timed_out_event", zzKh().zza((Parcelable) zzatg.zzbra));
        contentValues.put("creation_timestamp", Long.valueOf(zzatg.zzbqX));
        contentValues.put("triggered_event", zzKh().zza((Parcelable) zzatg.zzbrc));
        contentValues.put("triggered_timestamp", Long.valueOf(zzatg.zzbqW.zzbwc));
        contentValues.put("time_to_live", Long.valueOf(zzatg.zzbrd));
        contentValues.put("expired_event", zzKh().zza((Parcelable) zzatg.zzbre));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) == -1) {
                zzKl().zzLY().zzj("Failed to insert/update conditional user property (got -1)", zzatx.zzfE(zzatg.packageName));
                return true;
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing conditional user property", zzatx.zzfE(zzatg.packageName), e);
        }
        return true;
    }

    public boolean zza(zzatm zzatm, long j, boolean z) {
        zzatx.zza zzLY;
        String str;
        zzmR();
        zzob();
        zzac.zzw(zzatm);
        zzac.zzdr(zzatm.mAppId);
        zzauw.zzb zzb2 = new zzauw.zzb();
        zzb2.zzbxa = Long.valueOf(zzatm.zzbry);
        zzb2.zzbwY = new zzauw.zzc[zzatm.zzbrz.size()];
        Iterator<String> it = zzatm.zzbrz.iterator();
        int i = 0;
        while (it.hasNext()) {
            String next = it.next();
            zzauw.zzc zzc2 = new zzauw.zzc();
            zzb2.zzbwY[i] = zzc2;
            zzc2.name = next;
            zzKh().zza(zzc2, zzatm.zzbrz.get(next));
            i++;
        }
        try {
            byte[] bArr = new byte[zzb2.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zzb2.zza(zzag);
            zzag.zzaeG();
            zzKl().zzMe().zze("Saving event, name, data size", zzatm.mName, Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzatm.mAppId);
            contentValues.put("name", zzatm.mName);
            contentValues.put("timestamp", Long.valueOf(zzatm.zzaxb));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", (String) null, contentValues) != -1) {
                    return true;
                }
                zzKl().zzLY().zzj("Failed to insert raw event (got -1). appId", zzatx.zzfE(zzatm.mAppId));
                return false;
            } catch (SQLiteException e) {
                e = e;
                zzLY = zzKl().zzLY();
                str = "Error storing raw event. appId";
                zzLY.zze(str, zzatx.zzfE(zzatm.mAppId), e);
                return false;
            }
        } catch (IOException e2) {
            e = e2;
            zzLY = zzKl().zzLY();
            str = "Data loss. Failed to serialize event params/data. appId";
            zzLY.zze(str, zzatx.zzfE(zzatm.mAppId), e);
            return false;
        }
    }

    @WorkerThread
    public boolean zza(zzaus zzaus) {
        zzac.zzw(zzaus);
        zzmR();
        zzob();
        if (zzS(zzaus.mAppId, zzaus.mName) == null) {
            if (zzaut.zzfT(zzaus.mName)) {
                long zzb2 = zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzaus.mAppId});
                zzKn().zzKW();
                if (zzb2 >= 25) {
                    return false;
                }
            } else {
                long zzb3 = zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzaus.mAppId, zzaus.mOrigin});
                zzKn().zzKY();
                if (zzb3 >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzaus.mAppId);
        contentValues.put("origin", zzaus.mOrigin);
        contentValues.put("name", zzaus.mName);
        contentValues.put("set_timestamp", Long.valueOf(zzaus.zzbwg));
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzaus.mValue);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) == -1) {
                zzKl().zzLY().zzj("Failed to insert/update user property (got -1). appId", zzatx.zzfE(zzaus.mAppId));
                return true;
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing user property. appId", zzatx.zzfE(zzaus.mAppId), e);
        }
        return true;
    }

    @WorkerThread
    public boolean zza(zzauw.zze zze, boolean z) {
        zzatx.zza zzLY;
        String str;
        zzmR();
        zzob();
        zzac.zzw(zze);
        zzac.zzdr(zze.zzaS);
        zzac.zzw(zze.zzbxk);
        zzLF();
        long currentTimeMillis = zznR().currentTimeMillis();
        if (zze.zzbxk.longValue() < currentTimeMillis - zzKn().zzLj() || zze.zzbxk.longValue() > currentTimeMillis + zzKn().zzLj()) {
            zzKl().zzMa().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzatx.zzfE(zze.zzaS), Long.valueOf(currentTimeMillis), zze.zzbxk);
        }
        try {
            byte[] bArr = new byte[zze.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zze.zza(zzag);
            zzag.zzaeG();
            byte[] zzk = zzKh().zzk(bArr);
            zzKl().zzMe().zzj("Saving bundle, size", Integer.valueOf(zzk.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zze.zzaS);
            contentValues.put("bundle_end_timestamp", zze.zzbxk);
            contentValues.put("data", zzk);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("queue", (String) null, contentValues) != -1) {
                    return true;
                }
                zzKl().zzLY().zzj("Failed to insert bundle (got -1). appId", zzatx.zzfE(zze.zzaS));
                return false;
            } catch (SQLiteException e) {
                e = e;
                zzLY = zzKl().zzLY();
                str = "Error storing bundle. appId";
                zzLY.zze(str, zzatx.zzfE(zze.zzaS), e);
                return false;
            }
        } catch (IOException e2) {
            e = e2;
            zzLY = zzKl().zzLY();
            str = "Data loss. Failed to serialize bundle. appId";
            zzLY.zze(str, zzatx.zzfE(zze.zzaS), e);
            return false;
        }
    }

    @WorkerThread
    public void zzan(long j) {
        zzmR();
        zzob();
        try {
            if (getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(j)}) != 1) {
                throw new SQLiteException("Deleted fewer rows from queue than expected");
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zzj("Failed to delete a bundle in a queue table", e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zzao(long r5) {
        /*
            r4 = this;
            r4.zzmR()
            r4.zzob()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003e }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.internal.zzatx r6 = r4.zzKl()     // Catch:{ SQLiteException -> 0x003e }
            com.google.android.gms.internal.zzatx$zza r6 = r6.zzMe()     // Catch:{ SQLiteException -> 0x003e }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.log(r1)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x0033
            r5.close()
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.String r6 = r5.getString(r6)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x003d
            r5.close()
        L_0x003d:
            return r6
        L_0x003e:
            r6 = move-exception
            goto L_0x0045
        L_0x0040:
            r6 = move-exception
            r5 = r0
            goto L_0x0059
        L_0x0043:
            r6 = move-exception
            r5 = r0
        L_0x0045:
            com.google.android.gms.internal.zzatx r1 = r4.zzKl()     // Catch:{ all -> 0x0058 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ all -> 0x0058 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zzj(r2, r6)     // Catch:{ all -> 0x0058 }
            if (r5 == 0) goto L_0x0057
            r5.close()
        L_0x0057:
            return r0
        L_0x0058:
            r6 = move-exception
        L_0x0059:
            if (r5 == 0) goto L_0x005e
            r5.close()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzao(long):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public Object zzb(Cursor cursor, int i) {
        int zza2 = zza(cursor, i);
        switch (zza2) {
            case 0:
                zzKl().zzLY().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzKl().zzLY().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzKl().zzLY().zzj("Loaded invalid unknown value type, ignoring it", Integer.valueOf(zza2));
                return null;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(String str, zzauu.zza[] zzaArr) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zzaArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzfx(str);
            for (zzauu.zza zza2 : zzaArr) {
                zza(str, zza2);
            }
            ArrayList arrayList = new ArrayList();
            for (zzauu.zza zza3 : zzaArr) {
                arrayList.add(zza3.zzbwk);
            }
            zzd(str, (List<Integer>) arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    public List<zzatg> zzc(String str, String str2, long j) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzob();
        if (j < 0) {
            zzKl().zzMa().zzd("Invalid time querying triggered conditional properties", zzatx.zzfE(str), str2, Long.valueOf(j));
            return Collections.emptyList();
        }
        return zzc("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x018f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzatg> zzc(java.lang.String r39, java.lang.String[] r40) {
        /*
            r38 = this;
            r38.zzmR()
            r38.zzob()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.database.sqlite.SQLiteDatabase r3 = r38.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r4 = "conditional_properties"
            r5 = 13
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "app_id"
            r12 = 0
            r5[r12] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "origin"
            r13 = 1
            r5[r13] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "name"
            r14 = 2
            r5[r14] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "value"
            r15 = 3
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "active"
            r11 = 4
            r5[r11] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "trigger_event_name"
            r10 = 5
            r5[r10] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "trigger_timeout"
            r9 = 6
            r5[r9] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "timed_out_event"
            r8 = 7
            r5[r8] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "creation_timestamp"
            r7 = 8
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "triggered_event"
            r2 = 9
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "triggered_timestamp"
            r2 = 10
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "time_to_live"
            r2 = 11
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            java.lang.String r6 = "expired_event"
            r2 = 12
            r5[r2] = r6     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            r20 = 0
            r21 = 0
            java.lang.String r22 = "rowid"
            com.google.android.gms.internal.zzati r6 = r38.zzKn()     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            r6.zzKZ()     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            r6 = 1001(0x3e9, float:1.403E-42)
            java.lang.String r23 = java.lang.String.valueOf(r6)     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            r6 = r39
            r2 = r7
            r7 = r40
            r2 = r8
            r8 = r20
            r2 = r9
            r9 = r21
            r2 = r10
            r10 = r22
            r2 = r11
            r11 = r23
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0170, all -> 0x016c }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            if (r4 != 0) goto L_0x008f
            if (r3 == 0) goto L_0x008e
            r3.close()
        L_0x008e:
            return r1
        L_0x008f:
            int r4 = r1.size()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            com.google.android.gms.internal.zzati r5 = r38.zzKn()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            int r5 = r5.zzKZ()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            if (r4 < r5) goto L_0x00b8
            com.google.android.gms.internal.zzatx r2 = r38.zzKl()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            java.lang.String r4 = "Read more than the max allowed conditional properties, ignoring extra"
            com.google.android.gms.internal.zzati r5 = r38.zzKn()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            int r5 = r5.zzKZ()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r2.zzj(r4, r5)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            goto L_0x015b
        L_0x00b8:
            java.lang.String r4 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            java.lang.String r11 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            java.lang.String r6 = r3.getString(r14)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r10 = r38
            java.lang.Object r9 = r10.zzb((android.database.Cursor) r3, (int) r15)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            int r5 = r3.getInt(r2)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            if (r5 == 0) goto L_0x00d4
            r22 = r13
        L_0x00d2:
            r7 = 5
            goto L_0x00d7
        L_0x00d4:
            r22 = r12
            goto L_0x00d2
        L_0x00d7:
            java.lang.String r23 = r3.getString(r7)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r8 = 6
            long r27 = r3.getLong(r8)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            com.google.android.gms.internal.zzaut r5 = r38.zzKh()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r2 = 7
            byte[] r7 = r3.getBlob(r2)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r2 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            android.os.Parcelable r2 = r5.zzb((byte[]) r7, r2)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            com.google.android.gms.internal.zzatq r2 = (com.google.android.gms.internal.zzatq) r2     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r7 = 8
            long r20 = r3.getLong(r7)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            com.google.android.gms.internal.zzaut r5 = r38.zzKh()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r12 = 9
            byte[] r7 = r3.getBlob(r12)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r8 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            android.os.Parcelable r5 = r5.zzb((byte[]) r7, r8)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r29 = r5
            com.google.android.gms.internal.zzatq r29 = (com.google.android.gms.internal.zzatq) r29     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r7 = 10
            long r16 = r3.getLong(r7)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r8 = 11
            long r34 = r3.getLong(r8)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            com.google.android.gms.internal.zzaut r5 = r38.zzKh()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r12 = 12
            byte[] r7 = r3.getBlob(r12)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r8 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            android.os.Parcelable r5 = r5.zzb((byte[]) r7, r8)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r30 = r5
            com.google.android.gms.internal.zzatq r30 = (com.google.android.gms.internal.zzatq) r30     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            com.google.android.gms.internal.zzauq r19 = new com.google.android.gms.internal.zzauq     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r5 = r19
            r31 = 5
            r32 = 6
            r33 = 8
            r36 = 10
            r37 = 11
            r7 = r16
            r10 = r11
            r5.<init>(r6, r7, r9, r10)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            com.google.android.gms.internal.zzatg r5 = new com.google.android.gms.internal.zzatg     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r16 = r5
            r17 = r4
            r18 = r11
            r24 = r2
            r25 = r27
            r27 = r29
            r28 = r34
            r16.<init>(r17, r18, r19, r20, r22, r23, r24, r25, r27, r28, r30)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            r1.add(r5)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            boolean r2 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0168, all -> 0x0165 }
            if (r2 != 0) goto L_0x0161
        L_0x015b:
            if (r3 == 0) goto L_0x0160
            r3.close()
        L_0x0160:
            return r1
        L_0x0161:
            r2 = 4
            r12 = 0
            goto L_0x008f
        L_0x0165:
            r0 = move-exception
            r1 = r0
            goto L_0x018d
        L_0x0168:
            r0 = move-exception
            r1 = r0
            r2 = r3
            goto L_0x0173
        L_0x016c:
            r0 = move-exception
            r1 = r0
            r3 = 0
            goto L_0x018d
        L_0x0170:
            r0 = move-exception
            r1 = r0
            r2 = 0
        L_0x0173:
            com.google.android.gms.internal.zzatx r3 = r38.zzKl()     // Catch:{ all -> 0x018a }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ all -> 0x018a }
            java.lang.String r4 = "Error querying conditional user property value"
            r3.zzj(r4, r1)     // Catch:{ all -> 0x018a }
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ all -> 0x018a }
            if (r2 == 0) goto L_0x0189
            r2.close()
        L_0x0189:
            return r1
        L_0x018a:
            r0 = move-exception
            r1 = r0
            r3 = r2
        L_0x018d:
            if (r3 == 0) goto L_0x0192
            r3.close()
        L_0x0192:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzc(java.lang.String, java.lang.String[]):java.util.List");
    }

    @WorkerThread
    public void zzd(String str, byte[] bArr) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) getWritableDatabase().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzKl().zzLY().zzj("Failed to update remote config (got 0). appId", zzatx.zzfE(str));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing remote config. appId", zzatx.zzfE(str), e);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzd(String str, List<Integer> list) {
        zzac.zzdr(str);
        zzob();
        zzmR();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zzb2 = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int zzfo = zzKn().zzfo(str);
            if (zzb2 <= ((long) zzfo)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Integer num = list.get(i);
                    if (num == null || !(num instanceof Integer)) {
                        return false;
                    }
                    arrayList.add(Integer.toString(num.intValue()));
                }
            }
            String valueOf = String.valueOf(TextUtils.join(",", arrayList));
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 2);
            sb.append("(");
            sb.append(valueOf);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(140 + String.valueOf(sb2).length());
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(zzfo)}) > 0;
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Database error querying filters. appId", zzatx.zzfE(str), e);
            return false;
        }
    }

    @WorkerThread
    public long zzfA(String str) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        return zzX(str, "first_open_count");
    }

    public void zzfB(String str) {
        try {
            getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str, str});
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Failed to remove unused event metadata. appId", zzatx.zzfE(str), e);
        }
    }

    public long zzfC(String str) {
        zzac.zzdr(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c7  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzaus> zzft(java.lang.String r25) {
        /*
            r24 = this;
            com.google.android.gms.common.internal.zzac.zzdr(r25)
            r24.zzmR()
            r24.zzob()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r24.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            java.lang.String r4 = "user_attributes"
            r5 = 4
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            java.lang.String r6 = "name"
            r12 = 0
            r5[r12] = r6     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            java.lang.String r6 = "origin"
            r13 = 1
            r5[r13] = r6     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            java.lang.String r6 = "set_timestamp"
            r14 = 2
            r5[r14] = r6     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            java.lang.String r6 = "value"
            r15 = 3
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            java.lang.String r6 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            r7[r12] = r25     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            r8 = 0
            r9 = 0
            java.lang.String r10 = "rowid"
            com.google.android.gms.internal.zzati r11 = r24.zzKn()     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            int r11 = r11.zzKX()     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a1 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x009c, all -> 0x0098 }
            if (r4 != 0) goto L_0x0052
            if (r3 == 0) goto L_0x0051
            r3.close()
        L_0x0051:
            return r1
        L_0x0052:
            java.lang.String r19 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x009c, all -> 0x0098 }
            java.lang.String r4 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x009c, all -> 0x0098 }
            if (r4 != 0) goto L_0x005e
            java.lang.String r4 = ""
        L_0x005e:
            r18 = r4
            long r20 = r3.getLong(r14)     // Catch:{ SQLiteException -> 0x009c, all -> 0x0098 }
            r4 = r24
            java.lang.Object r22 = r4.zzb((android.database.Cursor) r3, (int) r15)     // Catch:{ SQLiteException -> 0x0096 }
            if (r22 != 0) goto L_0x007e
            com.google.android.gms.internal.zzatx r5 = r24.zzKl()     // Catch:{ SQLiteException -> 0x0096 }
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()     // Catch:{ SQLiteException -> 0x0096 }
            java.lang.String r6 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzatx.zzfE(r25)     // Catch:{ SQLiteException -> 0x0096 }
            r5.zzj(r6, r7)     // Catch:{ SQLiteException -> 0x0096 }
            goto L_0x008a
        L_0x007e:
            com.google.android.gms.internal.zzaus r5 = new com.google.android.gms.internal.zzaus     // Catch:{ SQLiteException -> 0x0096 }
            r16 = r5
            r17 = r25
            r16.<init>(r17, r18, r19, r20, r22)     // Catch:{ SQLiteException -> 0x0096 }
            r1.add(r5)     // Catch:{ SQLiteException -> 0x0096 }
        L_0x008a:
            boolean r5 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0096 }
            if (r5 != 0) goto L_0x0052
            if (r3 == 0) goto L_0x0095
            r3.close()
        L_0x0095:
            return r1
        L_0x0096:
            r0 = move-exception
            goto L_0x009f
        L_0x0098:
            r0 = move-exception
            r4 = r24
            goto L_0x00c4
        L_0x009c:
            r0 = move-exception
            r4 = r24
        L_0x009f:
            r1 = r0
            goto L_0x00ac
        L_0x00a1:
            r0 = move-exception
            r4 = r24
            r1 = r0
            r3 = r2
            goto L_0x00c5
        L_0x00a7:
            r0 = move-exception
            r4 = r24
            r1 = r0
            r3 = r2
        L_0x00ac:
            com.google.android.gms.internal.zzatx r5 = r24.zzKl()     // Catch:{ all -> 0x00c3 }
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()     // Catch:{ all -> 0x00c3 }
            java.lang.String r6 = "Error querying user properties. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzatx.zzfE(r25)     // Catch:{ all -> 0x00c3 }
            r5.zze(r6, r7, r1)     // Catch:{ all -> 0x00c3 }
            if (r3 == 0) goto L_0x00c2
            r3.close()
        L_0x00c2:
            return r2
        L_0x00c3:
            r0 = move-exception
        L_0x00c4:
            r1 = r0
        L_0x00c5:
            if (r3 == 0) goto L_0x00ca
            r3.close()
        L_0x00ca:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzft(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0200  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzatc zzfu(java.lang.String r21) {
        /*
            r20 = this;
            r1 = r21
            com.google.android.gms.common.internal.zzac.zzdr(r21)
            r20.zzmR()
            r20.zzob()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r20.getWritableDatabase()     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r4 = "apps"
            r5 = 23
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "app_instance_id"
            r11 = 0
            r5[r11] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "gmp_app_id"
            r12 = 1
            r5[r12] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "resettable_device_id_hash"
            r13 = 2
            r5[r13] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "last_bundle_index"
            r14 = 3
            r5[r14] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "last_bundle_start_timestamp"
            r15 = 4
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "last_bundle_end_timestamp"
            r10 = 5
            r5[r10] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "app_version"
            r9 = 6
            r5[r9] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "app_store"
            r8 = 7
            r5[r8] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "gmp_version"
            r7 = 8
            r5[r7] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "dev_cert_hash"
            r15 = 9
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "measurement_enabled"
            r15 = 10
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "day"
            r15 = 11
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r6 = 12
            java.lang.String r16 = "daily_public_events_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r6 = 13
            java.lang.String r16 = "daily_events_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r6 = 14
            java.lang.String r16 = "daily_conversions_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r6 = 15
            java.lang.String r16 = "config_fetched_time"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r6 = 16
            java.lang.String r16 = "failed_config_fetch_time"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "app_version_int"
            r15 = 17
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r6 = 18
            java.lang.String r16 = "firebase_instance_id"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r6 = 19
            java.lang.String r16 = "daily_error_events_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r6 = 20
            java.lang.String r16 = "daily_realtime_events_count"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r6 = 21
            java.lang.String r16 = "health_monitor_sample"
            r5[r6] = r16     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "android_id"
            r15 = 22
            r5[r15] = r6     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            java.lang.String r6 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r7[r11] = r1     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            r16 = 0
            r17 = 0
            r18 = 0
            r15 = 8
            r15 = r8
            r8 = r16
            r15 = r9
            r9 = r17
            r15 = r10
            r10 = r18
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x01df, all -> 0x01d9 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x01d3, all -> 0x01ce }
            if (r4 != 0) goto L_0x00bf
            if (r3 == 0) goto L_0x00be
            r3.close()
        L_0x00be:
            return r2
        L_0x00bf:
            com.google.android.gms.internal.zzatc r4 = new com.google.android.gms.internal.zzatc     // Catch:{ SQLiteException -> 0x01d3, all -> 0x01ce }
            r5 = r20
            com.google.android.gms.internal.zzaue r6 = r5.zzbqc     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.<init>(r6, r1)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            java.lang.String r6 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzfd(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            java.lang.String r6 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzfe(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            java.lang.String r6 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzff(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            long r6 = r3.getLong(r14)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzad(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 4
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzY(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            long r6 = r3.getLong(r15)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzZ(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 6
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.setAppVersion(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 7
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzfh(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 8
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzab(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 9
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzac(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 10
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            if (r7 == 0) goto L_0x011f
            r6 = r12
            goto L_0x0123
        L_0x011f:
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
        L_0x0123:
            if (r6 == 0) goto L_0x0126
            goto L_0x0127
        L_0x0126:
            r12 = r11
        L_0x0127:
            r4.setMeasurementEnabled(r12)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 11
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzag(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 12
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzah(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 13
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzai(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 14
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzaj(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 15
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzae(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 16
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzaf(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 17
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            if (r7 == 0) goto L_0x016c
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0171
        L_0x016c:
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
        L_0x0171:
            r4.zzaa(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 18
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzfg(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 19
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzal(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 20
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzak(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 21
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzfi(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6 = 22
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            if (r7 == 0) goto L_0x01a3
            r6 = 0
            goto L_0x01a7
        L_0x01a3:
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
        L_0x01a7:
            r4.zzam(r6)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r4.zzKo()     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            boolean r6 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            if (r6 == 0) goto L_0x01c4
            com.google.android.gms.internal.zzatx r6 = r20.zzKl()     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            com.google.android.gms.internal.zzatx$zza r6 = r6.zzLY()     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            java.lang.String r7 = "Got multiple records for app, expected one. appId"
            java.lang.Object r8 = com.google.android.gms.internal.zzatx.zzfE(r21)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
            r6.zzj(r7, r8)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01ca }
        L_0x01c4:
            if (r3 == 0) goto L_0x01c9
            r3.close()
        L_0x01c9:
            return r4
        L_0x01ca:
            r0 = move-exception
            goto L_0x01d1
        L_0x01cc:
            r0 = move-exception
            goto L_0x01d6
        L_0x01ce:
            r0 = move-exception
            r5 = r20
        L_0x01d1:
            r1 = r0
            goto L_0x01fe
        L_0x01d3:
            r0 = move-exception
            r5 = r20
        L_0x01d6:
            r4 = r3
            r3 = r0
            goto L_0x01e4
        L_0x01d9:
            r0 = move-exception
            r5 = r20
            r1 = r0
            r3 = r2
            goto L_0x01fe
        L_0x01df:
            r0 = move-exception
            r5 = r20
            r3 = r0
            r4 = r2
        L_0x01e4:
            com.google.android.gms.internal.zzatx r6 = r20.zzKl()     // Catch:{ all -> 0x01fb }
            com.google.android.gms.internal.zzatx$zza r6 = r6.zzLY()     // Catch:{ all -> 0x01fb }
            java.lang.String r7 = "Error querying app. appId"
            java.lang.Object r1 = com.google.android.gms.internal.zzatx.zzfE(r21)     // Catch:{ all -> 0x01fb }
            r6.zze(r7, r1, r3)     // Catch:{ all -> 0x01fb }
            if (r4 == 0) goto L_0x01fa
            r4.close()
        L_0x01fa:
            return r2
        L_0x01fb:
            r0 = move-exception
            r1 = r0
            r3 = r4
        L_0x01fe:
            if (r3 == 0) goto L_0x0203
            r3.close()
        L_0x0203:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzfu(java.lang.String):com.google.android.gms.internal.zzatc");
    }

    public long zzfv(String str) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        try {
            return (long) getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(zzKn().zzfs(str))});
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error deleting over the limit events. appId", zzatx.zzfE(str), e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0079  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] zzfw(java.lang.String r12) {
        /*
            r11 = this;
            com.google.android.gms.common.internal.zzac.zzdr(r12)
            r11.zzmR()
            r11.zzob()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r11.getWritableDatabase()     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            java.lang.String r2 = "apps"
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            java.lang.String r5 = "remote_config"
            r9 = 0
            r4[r9] = r5     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            java.lang.String r5 = "app_id=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            r6[r9] = r12     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            r7 = 0
            r8 = 0
            r10 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r10
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0058 }
            if (r2 != 0) goto L_0x0037
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r9)     // Catch:{ SQLiteException -> 0x0058 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0058 }
            if (r3 == 0) goto L_0x0052
            com.google.android.gms.internal.zzatx r3 = r11.zzKl()     // Catch:{ SQLiteException -> 0x0058 }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ SQLiteException -> 0x0058 }
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ SQLiteException -> 0x0058 }
            r3.zzj(r4, r5)     // Catch:{ SQLiteException -> 0x0058 }
        L_0x0052:
            if (r1 == 0) goto L_0x0057
            r1.close()
        L_0x0057:
            return r2
        L_0x0058:
            r2 = move-exception
            goto L_0x005f
        L_0x005a:
            r12 = move-exception
            r1 = r0
            goto L_0x0077
        L_0x005d:
            r2 = move-exception
            r1 = r0
        L_0x005f:
            com.google.android.gms.internal.zzatx r3 = r11.zzKl()     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ all -> 0x0076 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r12 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ all -> 0x0076 }
            r3.zze(r4, r12, r2)     // Catch:{ all -> 0x0076 }
            if (r1 == 0) goto L_0x0075
            r1.close()
        L_0x0075:
            return r0
        L_0x0076:
            r12 = move-exception
        L_0x0077:
            if (r1 == 0) goto L_0x007c
            r1.close()
        L_0x007c:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzfw(java.lang.String):byte[]");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzfx(String str) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("property_filters", "app_id=?", new String[]{str});
        writableDatabase.delete("event_filters", "app_id=?", new String[]{str});
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.Integer, com.google.android.gms.internal.zzauw.zzf> zzfy(java.lang.String r12) {
        /*
            r11 = this;
            r11.zzob()
            r11.zzmR()
            com.google.android.gms.common.internal.zzac.zzdr(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            java.lang.String r3 = "audience_id"
            r9 = 0
            r2[r9] = r3     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            java.lang.String r3 = "current_results"
            r10 = 1
            r2[r10] = r3     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            java.lang.String r3 = "app_id=?"
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            r4[r9] = r12     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x007e, all -> 0x007b }
            boolean r1 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0079 }
            if (r1 != 0) goto L_0x0036
            if (r0 == 0) goto L_0x0035
            r0.close()
        L_0x0035:
            return r8
        L_0x0036:
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap     // Catch:{ SQLiteException -> 0x0079 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x0079 }
        L_0x003b:
            int r2 = r0.getInt(r9)     // Catch:{ SQLiteException -> 0x0079 }
            byte[] r3 = r0.getBlob(r10)     // Catch:{ SQLiteException -> 0x0079 }
            com.google.android.gms.internal.zzbxl r3 = com.google.android.gms.internal.zzbxl.zzaf(r3)     // Catch:{ SQLiteException -> 0x0079 }
            com.google.android.gms.internal.zzauw$zzf r4 = new com.google.android.gms.internal.zzauw$zzf     // Catch:{ SQLiteException -> 0x0079 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0079 }
            r4.zzb(r3)     // Catch:{ IOException -> 0x0057 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0079 }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x0079 }
            goto L_0x006d
        L_0x0057:
            r3 = move-exception
            com.google.android.gms.internal.zzatx r4 = r11.zzKl()     // Catch:{ SQLiteException -> 0x0079 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ SQLiteException -> 0x0079 }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ SQLiteException -> 0x0079 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0079 }
            r4.zzd(r5, r6, r2, r3)     // Catch:{ SQLiteException -> 0x0079 }
        L_0x006d:
            boolean r2 = r0.moveToNext()     // Catch:{ SQLiteException -> 0x0079 }
            if (r2 != 0) goto L_0x003b
            if (r0 == 0) goto L_0x0078
            r0.close()
        L_0x0078:
            return r1
        L_0x0079:
            r1 = move-exception
            goto L_0x0080
        L_0x007b:
            r12 = move-exception
            r0 = r8
            goto L_0x0098
        L_0x007e:
            r1 = move-exception
            r0 = r8
        L_0x0080:
            com.google.android.gms.internal.zzatx r2 = r11.zzKl()     // Catch:{ all -> 0x0097 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x0097 }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ all -> 0x0097 }
            r2.zze(r3, r12, r1)     // Catch:{ all -> 0x0097 }
            if (r0 == 0) goto L_0x0096
            r0.close()
        L_0x0096:
            return r8
        L_0x0097:
            r12 = move-exception
        L_0x0098:
            if (r0 == 0) goto L_0x009d
            r0.close()
        L_0x009d:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzfy(java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzfz(String str) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String[] strArr = {str};
            int delete = 0 + writableDatabase.delete("events", "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("event_filters", "app_id=?", strArr) + writableDatabase.delete("property_filters", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr);
            if (delete > 0) {
                zzKl().zzMe().zze("Deleted application data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error deleting application data. appId, error", zzatx.zzfE(str), e);
        }
    }

    @WorkerThread
    public List<zzatg> zzh(String str, long j) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        if (j < 0) {
            zzKl().zzMa().zze("Invalid time querying timed out conditional properties", zzatx.zzfE(str), Long.valueOf(j));
            return Collections.emptyList();
        }
        return zzc("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
    }

    @WorkerThread
    public List<zzatg> zzi(String str, long j) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        if (j < 0) {
            zzKl().zzMa().zze("Invalid time querying expired conditional properties", zzatx.zzfE(str), Long.valueOf(j));
            return Collections.emptyList();
        }
        return zzc("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r15 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0143, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0144, code lost:
        r15 = r23;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0148, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0149, code lost:
        r15 = r23;
        r12 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0164, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x016d, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0143 A[ExcHandler: all (r0v5 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x016d  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzaus> zzk(java.lang.String r24, java.lang.String r25, java.lang.String r26) {
        /*
            r23 = this;
            com.google.android.gms.common.internal.zzac.zzdr(r24)
            r23.zzmR()
            r23.zzob()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0148, all -> 0x0143 }
            r4 = 3
            r3.<init>(r4)     // Catch:{ SQLiteException -> 0x0148, all -> 0x0143 }
            r12 = r24
            r3.add(r12)     // Catch:{ SQLiteException -> 0x013f, all -> 0x0143 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x013f, all -> 0x0143 }
            java.lang.String r6 = "app_id=?"
            r5.<init>(r6)     // Catch:{ SQLiteException -> 0x013f, all -> 0x0143 }
            boolean r6 = android.text.TextUtils.isEmpty(r25)     // Catch:{ SQLiteException -> 0x013f, all -> 0x0143 }
            if (r6 != 0) goto L_0x0037
            r6 = r25
            r3.add(r6)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String r7 = " and origin=?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            goto L_0x0039
        L_0x0032:
            r0 = move-exception
            r15 = r23
            goto L_0x014f
        L_0x0037:
            r6 = r25
        L_0x0039:
            boolean r7 = android.text.TextUtils.isEmpty(r26)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            if (r7 != 0) goto L_0x0051
            java.lang.String r7 = java.lang.String.valueOf(r26)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String r8 = "*"
            java.lang.String r7 = r7.concat(r8)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            r3.add(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String r7 = " and name glob ?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
        L_0x0051:
            int r7 = r3.size()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.Object[] r3 = r3.toArray(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            r17 = r3
            java.lang.String[] r17 = (java.lang.String[]) r17     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            android.database.sqlite.SQLiteDatabase r13 = r23.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String r14 = "user_attributes"
            r3 = 4
            java.lang.String[] r15 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String r3 = "name"
            r11 = 0
            r15[r11] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String r3 = "set_timestamp"
            r9 = 1
            r15[r9] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String r3 = "value"
            r10 = 2
            r15[r10] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String r3 = "origin"
            r15[r4] = r3     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            java.lang.String r16 = r5.toString()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            com.google.android.gms.internal.zzati r3 = r23.zzKn()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            r3.zzKX()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            r3 = 1001(0x3e9, float:1.403E-42)
            java.lang.String r21 = java.lang.String.valueOf(r3)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            android.database.Cursor r3 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0143 }
            boolean r5 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            if (r5 != 0) goto L_0x00a2
            if (r3 == 0) goto L_0x00a1
            r3.close()
        L_0x00a1:
            return r1
        L_0x00a2:
            int r5 = r1.size()     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            com.google.android.gms.internal.zzati r7 = r23.zzKn()     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            int r7 = r7.zzKX()     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            if (r5 < r7) goto L_0x00cc
            com.google.android.gms.internal.zzatx r4 = r23.zzKl()     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            java.lang.String r5 = "Read more than the max allowed user properties, ignoring excess"
            com.google.android.gms.internal.zzati r7 = r23.zzKn()     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            int r7 = r7.zzKX()     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            r4.zzj(r5, r7)     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            r15 = r23
            goto L_0x011b
        L_0x00cc:
            java.lang.String r8 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            long r13 = r3.getLong(r9)     // Catch:{ SQLiteException -> 0x013a, all -> 0x0136 }
            r15 = r23
            java.lang.Object r16 = r15.zzb((android.database.Cursor) r3, (int) r10)     // Catch:{ SQLiteException -> 0x0134 }
            java.lang.String r7 = r3.getString(r4)     // Catch:{ SQLiteException -> 0x0134 }
            if (r16 != 0) goto L_0x0100
            com.google.android.gms.internal.zzatx r5 = r23.zzKl()     // Catch:{ SQLiteException -> 0x00fb }
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()     // Catch:{ SQLiteException -> 0x00fb }
            java.lang.String r6 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r8 = com.google.android.gms.internal.zzatx.zzfE(r24)     // Catch:{ SQLiteException -> 0x00fb }
            r13 = r26
            r5.zzd(r6, r8, r7, r13)     // Catch:{ SQLiteException -> 0x00fb }
            r17 = r7
            r18 = r9
            r19 = r10
            r13 = r11
            goto L_0x0115
        L_0x00fb:
            r0 = move-exception
            r1 = r0
            r6 = r7
            goto L_0x0151
        L_0x0100:
            com.google.android.gms.internal.zzaus r6 = new com.google.android.gms.internal.zzaus     // Catch:{ SQLiteException -> 0x012d }
            r5 = r6
            r4 = r6
            r6 = r12
            r17 = r7
            r18 = r9
            r19 = r10
            r9 = r13
            r13 = r11
            r11 = r16
            r5.<init>(r6, r7, r8, r9, r11)     // Catch:{ SQLiteException -> 0x012b }
            r1.add(r4)     // Catch:{ SQLiteException -> 0x012b }
        L_0x0115:
            boolean r4 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x012b }
            if (r4 != 0) goto L_0x0121
        L_0x011b:
            if (r3 == 0) goto L_0x0120
            r3.close()
        L_0x0120:
            return r1
        L_0x0121:
            r11 = r13
            r6 = r17
            r9 = r18
            r10 = r19
            r4 = 3
            goto L_0x00a2
        L_0x012b:
            r0 = move-exception
            goto L_0x0130
        L_0x012d:
            r0 = move-exception
            r17 = r7
        L_0x0130:
            r1 = r0
            r6 = r17
            goto L_0x0151
        L_0x0134:
            r0 = move-exception
            goto L_0x013d
        L_0x0136:
            r0 = move-exception
            r15 = r23
            goto L_0x0169
        L_0x013a:
            r0 = move-exception
            r15 = r23
        L_0x013d:
            r1 = r0
            goto L_0x0151
        L_0x013f:
            r0 = move-exception
            r15 = r23
            goto L_0x014d
        L_0x0143:
            r0 = move-exception
            r15 = r23
            r1 = r0
            goto L_0x016b
        L_0x0148:
            r0 = move-exception
            r15 = r23
            r12 = r24
        L_0x014d:
            r6 = r25
        L_0x014f:
            r1 = r0
            r3 = r2
        L_0x0151:
            com.google.android.gms.internal.zzatx r4 = r23.zzKl()     // Catch:{ all -> 0x0168 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x0168 }
            java.lang.String r5 = "(2)Error querying user properties"
            java.lang.Object r7 = com.google.android.gms.internal.zzatx.zzfE(r24)     // Catch:{ all -> 0x0168 }
            r4.zzd(r5, r7, r6, r1)     // Catch:{ all -> 0x0168 }
            if (r3 == 0) goto L_0x0167
            r3.close()
        L_0x0167:
            return r2
        L_0x0168:
            r0 = move-exception
        L_0x0169:
            r1 = r0
            r2 = r3
        L_0x016b:
            if (r2 == 0) goto L_0x0170
            r2.close()
        L_0x0170:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzk(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public List<zzatg> zzl(String str, String str2, String str3) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzc(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b3 A[LOOP:0: B:20:0x0055->B:41:0x00b3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00b5 A[EDGE_INSN: B:60:0x00b5->B:42:0x00b5 ?: BREAK  , SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<android.util.Pair<com.google.android.gms.internal.zzauw.zze, java.lang.Long>> zzn(java.lang.String r13, int r14, int r15) {
        /*
            r12 = this;
            r12.zzmR()
            r12.zzob()
            r0 = 1
            r1 = 0
            if (r14 <= 0) goto L_0x000c
            r2 = r0
            goto L_0x000d
        L_0x000c:
            r2 = r1
        L_0x000d:
            com.google.android.gms.common.internal.zzac.zzax(r2)
            if (r15 <= 0) goto L_0x0014
            r2 = r0
            goto L_0x0015
        L_0x0014:
            r2 = r1
        L_0x0015:
            com.google.android.gms.common.internal.zzac.zzax(r2)
            com.google.android.gms.common.internal.zzac.zzdr(r13)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r12.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00c3 }
            java.lang.String r4 = "queue"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x00c3 }
            java.lang.String r6 = "rowid"
            r5[r1] = r6     // Catch:{ SQLiteException -> 0x00c3 }
            java.lang.String r6 = "data"
            r5[r0] = r6     // Catch:{ SQLiteException -> 0x00c3 }
            java.lang.String r6 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00c3 }
            r7[r1] = r13     // Catch:{ SQLiteException -> 0x00c3 }
            r8 = 0
            r9 = 0
            java.lang.String r10 = "rowid"
            java.lang.String r11 = java.lang.String.valueOf(r14)     // Catch:{ SQLiteException -> 0x00c3 }
            android.database.Cursor r14 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x00c3 }
            boolean r2 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            if (r2 != 0) goto L_0x004f
            java.util.List r15 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            if (r14 == 0) goto L_0x004e
            r14.close()
        L_0x004e:
            return r15
        L_0x004f:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            r2.<init>()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            r3 = r1
        L_0x0055:
            long r4 = r14.getLong(r1)     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            byte[] r6 = r14.getBlob(r0)     // Catch:{ IOException -> 0x009d }
            com.google.android.gms.internal.zzaut r7 = r12.zzKh()     // Catch:{ IOException -> 0x009d }
            byte[] r6 = r7.zzx(r6)     // Catch:{ IOException -> 0x009d }
            boolean r7 = r2.isEmpty()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            if (r7 != 0) goto L_0x0070
            int r7 = r6.length     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            int r7 = r7 + r3
            if (r7 <= r15) goto L_0x0070
            goto L_0x00b5
        L_0x0070:
            com.google.android.gms.internal.zzbxl r7 = com.google.android.gms.internal.zzbxl.zzaf(r6)     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            com.google.android.gms.internal.zzauw$zze r8 = new com.google.android.gms.internal.zzauw$zze     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            r8.<init>()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            r8.zzb(r7)     // Catch:{ IOException -> 0x008a }
            int r6 = r6.length     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            int r3 = r3 + r6
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            android.util.Pair r4 = android.util.Pair.create(r8, r4)     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            r2.add(r4)     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            goto L_0x00ad
        L_0x008a:
            r4 = move-exception
            com.google.android.gms.internal.zzatx r5 = r12.zzKl()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            java.lang.String r6 = "Failed to merge queued bundle. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzatx.zzfE(r13)     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
        L_0x0099:
            r5.zze(r6, r7, r4)     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            goto L_0x00ad
        L_0x009d:
            r4 = move-exception
            com.google.android.gms.internal.zzatx r5 = r12.zzKl()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            java.lang.String r6 = "Failed to unzip queued bundle. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzatx.zzfE(r13)     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            goto L_0x0099
        L_0x00ad:
            boolean r4 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x00bd, all -> 0x00bb }
            if (r4 == 0) goto L_0x00b5
            if (r3 <= r15) goto L_0x0055
        L_0x00b5:
            if (r14 == 0) goto L_0x00ba
            r14.close()
        L_0x00ba:
            return r2
        L_0x00bb:
            r13 = move-exception
            goto L_0x00df
        L_0x00bd:
            r15 = move-exception
            r2 = r14
            goto L_0x00c4
        L_0x00c0:
            r13 = move-exception
            r14 = r2
            goto L_0x00df
        L_0x00c3:
            r15 = move-exception
        L_0x00c4:
            com.google.android.gms.internal.zzatx r14 = r12.zzKl()     // Catch:{ all -> 0x00c0 }
            com.google.android.gms.internal.zzatx$zza r14 = r14.zzLY()     // Catch:{ all -> 0x00c0 }
            java.lang.String r0 = "Error querying bundles. appId"
            java.lang.Object r13 = com.google.android.gms.internal.zzatx.zzfE(r13)     // Catch:{ all -> 0x00c0 }
            r14.zze(r0, r13, r15)     // Catch:{ all -> 0x00c0 }
            java.util.List r13 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00c0 }
            if (r2 == 0) goto L_0x00de
            r2.close()
        L_0x00de:
            return r13
        L_0x00df:
            if (r14 == 0) goto L_0x00e4
            r14.close()
        L_0x00e4:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzn(java.lang.String, int, int):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public String zzow() {
        return zzKn().zzpv();
    }

    @WorkerThread
    public void zzz(String str, int i) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        try {
            getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(i)});
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error pruning currencies. appId", zzatx.zzfE(str), e);
        }
    }
}
