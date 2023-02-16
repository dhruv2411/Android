package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzo;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzsg extends zzsa implements Closeable {
    /* access modifiers changed from: private */
    public static final String zzaeu = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    private static final String zzaev = String.format("SELECT MAX(%s) FROM %s WHERE 1;", new Object[]{"hit_time", "hits2"});
    private final zza zzaew;
    private final zztj zzaex = new zztj(zznR());
    /* access modifiers changed from: private */
    public final zztj zzaey = new zztj(zznR());

    class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        private void zza(SQLiteDatabase sQLiteDatabase) {
            Set<String> zzb = zzb(sQLiteDatabase, "hits2");
            String[] strArr = {"hit_id", "hit_string", "hit_time", "hit_url"};
            for (int i = 0; i < 4; i++) {
                String str = strArr[i];
                if (!zzb.remove(str)) {
                    String valueOf = String.valueOf(str);
                    throw new SQLiteException(valueOf.length() != 0 ? "Database hits2 is missing required column: ".concat(valueOf) : new String("Database hits2 is missing required column: "));
                }
            }
            boolean z = !zzb.remove("hit_app_id");
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database hits2 has extra columns");
            } else if (z) {
                sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER");
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x003d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean zza(android.database.sqlite.SQLiteDatabase r12, java.lang.String r13) {
            /*
                r11 = this;
                r0 = 0
                r1 = 0
                java.lang.String r3 = "SQLITE_MASTER"
                r2 = 1
                java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002d }
                java.lang.String r5 = "name"
                r4[r0] = r5     // Catch:{ SQLiteException -> 0x002d }
                java.lang.String r5 = "name=?"
                java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002d }
                r6[r0] = r13     // Catch:{ SQLiteException -> 0x002d }
                r7 = 0
                r8 = 0
                r9 = 0
                r2 = r12
                android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x002d }
                boolean r1 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x0026, all -> 0x0023 }
                if (r12 == 0) goto L_0x0022
                r12.close()
            L_0x0022:
                return r1
            L_0x0023:
                r13 = move-exception
                r1 = r12
                goto L_0x003b
            L_0x0026:
                r1 = move-exception
                r10 = r1
                r1 = r12
                r12 = r10
                goto L_0x002e
            L_0x002b:
                r13 = move-exception
                goto L_0x003b
            L_0x002d:
                r12 = move-exception
            L_0x002e:
                com.google.android.gms.internal.zzsg r2 = com.google.android.gms.internal.zzsg.this     // Catch:{ all -> 0x002b }
                java.lang.String r3 = "Error querying for table"
                r2.zzc(r3, r13, r12)     // Catch:{ all -> 0x002b }
                if (r1 == 0) goto L_0x003a
                r1.close()
            L_0x003a:
                return r0
            L_0x003b:
                if (r1 == 0) goto L_0x0040
                r1.close()
            L_0x0040:
                throw r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zza.zza(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
        }

        private Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
            HashSet hashSet = new HashSet();
            StringBuilder sb = new StringBuilder(22 + String.valueOf(str).length());
            sb.append("SELECT * FROM ");
            sb.append(str);
            sb.append(" LIMIT 0");
            Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), (String[]) null);
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (String add : columnNames) {
                    hashSet.add(add);
                }
                return hashSet;
            } finally {
                rawQuery.close();
            }
        }

        private void zzb(SQLiteDatabase sQLiteDatabase) {
            Set<String> zzb = zzb(sQLiteDatabase, "properties");
            String[] strArr = {"app_uid", "cid", "tid", "params", "adid", "hits_count"};
            for (int i = 0; i < 6; i++) {
                String str = strArr[i];
                if (!zzb.remove(str)) {
                    String valueOf = String.valueOf(str);
                    throw new SQLiteException(valueOf.length() != 0 ? "Database properties is missing required column: ".concat(valueOf) : new String("Database properties is missing required column: "));
                }
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database properties table has extra columns");
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            if (!zzsg.this.zzaey.zzA(3600000)) {
                throw new SQLiteException("Database open failed");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException unused) {
                zzsg.this.zzaey.start();
                zzsg.this.zzbT("Opening the database failed, dropping the table and recreating it");
                zzsg.this.getContext().getDatabasePath(zzsg.this.zzow()).delete();
                try {
                    SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    zzsg.this.zzaey.clear();
                    return writableDatabase;
                } catch (SQLiteException e) {
                    zzsg.this.zze("Failed to open freshly created database", e);
                    throw e;
                }
            }
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzsv.zzca(sQLiteDatabase.getPath());
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (Build.VERSION.SDK_INT < 15) {
                Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[]) null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            if (!zza(sQLiteDatabase, "hits2")) {
                sQLiteDatabase.execSQL(zzsg.zzaeu);
            } else {
                zza(sQLiteDatabase);
            }
            if (!zza(sQLiteDatabase, "properties")) {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
            } else {
                zzb(sQLiteDatabase);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    zzsg(zzsc zzsc) {
        super(zzsc);
        this.zzaew = new zza(zzsc.getContext(), zzow());
    }

    private static String zzT(Map<String, String> map) {
        zzac.zzw(map);
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry next : map.entrySet()) {
            builder.appendQueryParameter((String) next.getKey(), (String) next.getValue());
        }
        String encodedQuery = builder.build().getEncodedQuery();
        return encodedQuery == null ? "" : encodedQuery;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0031  */
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
            goto L_0x002f
        L_0x0023:
            r5 = move-exception
            r1 = r4
            goto L_0x0029
        L_0x0026:
            r3 = move-exception
            goto L_0x002f
        L_0x0028:
            r5 = move-exception
        L_0x0029:
            java.lang.String r4 = "Database error"
            r2.zzd(r4, r3, r5)     // Catch:{ all -> 0x0026 }
            throw r5     // Catch:{ all -> 0x0026 }
        L_0x002f:
            if (r1 == 0) goto L_0x0034
            r1.close()
        L_0x0034:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zza(java.lang.String, java.lang.String[], long):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long zzb(java.lang.String r3, java.lang.String[] r4) {
        /*
            r2 = this;
            android.database.sqlite.SQLiteDatabase r0 = r2.getWritableDatabase()
            r1 = 0
            android.database.Cursor r4 = r0.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x002a }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r0 == 0) goto L_0x001a
            r0 = 0
            long r0 = r4.getLong(r0)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r4 == 0) goto L_0x0019
            r4.close()
        L_0x0019:
            return r0
        L_0x001a:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            java.lang.String r1 = "Database returned empty set"
            r0.<init>(r1)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            throw r0     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
        L_0x0022:
            r3 = move-exception
            goto L_0x0031
        L_0x0024:
            r0 = move-exception
            r1 = r4
            goto L_0x002b
        L_0x0027:
            r3 = move-exception
            r4 = r1
            goto L_0x0031
        L_0x002a:
            r0 = move-exception
        L_0x002b:
            java.lang.String r4 = "Database error"
            r2.zzd(r4, r3, r0)     // Catch:{ all -> 0x0027 }
            throw r0     // Catch:{ all -> 0x0027 }
        L_0x0031:
            if (r4 == 0) goto L_0x0036
            r4.close()
        L_0x0036:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zzb(java.lang.String, java.lang.String[]):long");
    }

    private String zzd(zzsz zzsz) {
        return zzsz.zzpS() ? zznT().zzpj() : zznT().zzpk();
    }

    private static String zze(zzsz zzsz) {
        zzac.zzw(zzsz);
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry next : zzsz.zzfE().entrySet()) {
            String str = (String) next.getKey();
            if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str)) {
                builder.appendQueryParameter(str, (String) next.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        return encodedQuery == null ? "" : encodedQuery;
    }

    private void zzov() {
        int zzpt = zznT().zzpt();
        long zzom = zzom();
        if (zzom > ((long) (zzpt - 1))) {
            List<Long> zzt = zzt((zzom - ((long) zzpt)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzt.size()));
            zzr(zzt);
        }
    }

    /* access modifiers changed from: private */
    public String zzow() {
        return zznT().zzpv();
    }

    public void beginTransaction() {
        zzob();
        getWritableDatabase().beginTransaction();
    }

    public void close() {
        String str;
        try {
            this.zzaew.close();
        } catch (SQLiteException e) {
            e = e;
            str = "Sql error closing database";
            zze(str, e);
        } catch (IllegalStateException e2) {
            e = e2;
            str = "Error closing database";
            zze(str, e);
        }
    }

    public void endTransaction() {
        zzob();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: package-private */
    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzaew.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isEmpty() {
        return zzom() == 0;
    }

    public void setTransactionSuccessful() {
        zzob();
        getWritableDatabase().setTransactionSuccessful();
    }

    public long zza(long j, String str, String str2) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzob();
        zzmR();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    public void zza(long j, String str) {
        zzac.zzdr(str);
        zzob();
        zzmR();
        int delete = getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(j), str});
        if (delete > 0) {
            zza("Deleted property records", Integer.valueOf(delete));
        }
    }

    public void zzb(zzse zzse) {
        zzac.zzw(zzse);
        zzob();
        zzmR();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String zzT = zzT(zzse.zzfE());
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_uid", Long.valueOf(zzse.zzoj()));
        contentValues.put("cid", zzse.zzmy());
        contentValues.put("tid", zzse.zzok());
        contentValues.put("adid", Integer.valueOf(zzse.zzol() ? 1 : 0));
        contentValues.put("hits_count", Long.valueOf(zzse.zzom()));
        contentValues.put("params", zzT);
        try {
            if (writableDatabase.insertWithOnConflict("properties", (String) null, contentValues, 5) == -1) {
                zzbT("Failed to insert/update a property (got -1)");
            }
        } catch (SQLiteException e) {
            zze("Error storing a property", e);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zzbU(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String valueOf = String.valueOf(str);
                str = valueOf.length() != 0 ? "?".concat(valueOf) : new String("?");
            }
            return zzo.zza(new URI(str), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zzbV(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            String valueOf = String.valueOf(str);
            return zzo.zza(new URI(valueOf.length() != 0 ? "?".concat(valueOf) : new String("?")), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    public void zzc(zzsz zzsz) {
        zzac.zzw(zzsz);
        zzmR();
        zzob();
        String zze = zze(zzsz);
        if (zze.length() > 8192) {
            zznS().zza(zzsz, "Hit length exceeds the maximum allowed size");
            return;
        }
        zzov();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", zze);
        contentValues.put("hit_time", Long.valueOf(zzsz.zzpQ()));
        contentValues.put("hit_app_id", Integer.valueOf(zzsz.zzpO()));
        contentValues.put("hit_url", zzd(zzsz));
        try {
            long insert = writableDatabase.insert("hits2", (String) null, contentValues);
            if (insert == -1) {
                zzbT("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), zzsz);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public long zzom() {
        zzmR();
        zzob();
        return zzb("SELECT COUNT(*) FROM hits2", (String[]) null);
    }

    public void zzor() {
        zzmR();
        zzob();
        getWritableDatabase().delete("hits2", (String) null, (String[]) null);
    }

    public void zzos() {
        zzmR();
        zzob();
        getWritableDatabase().delete("properties", (String) null, (String[]) null);
    }

    public int zzot() {
        zzmR();
        zzob();
        if (!this.zzaex.zzA(86400000)) {
            return 0;
        }
        this.zzaex.start();
        zzbP("Deleting stale hits (if any)");
        int delete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zznR().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public long zzou() {
        zzmR();
        zzob();
        return zza(zzaev, (String[]) null, 0);
    }

    public void zzr(List<Long> list) {
        zzac.zzw(list);
        zzmR();
        zzob();
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder("hit_id");
            sb.append(" in (");
            for (int i = 0; i < list.size(); i++) {
                Long l = list.get(i);
                if (l == null || l.longValue() == 0) {
                    throw new SQLiteException("Invalid hit id");
                }
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(l);
            }
            sb.append(")");
            String sb2 = sb.toString();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                int delete = writableDatabase.delete("hits2", sb2, (String[]) null);
                if (delete != list.size()) {
                    zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(delete), sb2);
                }
            } catch (SQLiteException e) {
                zze("Error deleting hits", e);
                throw e;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.Long> zzt(long r14) {
        /*
            r13 = this;
            r13.zzmR()
            r13.zzob()
            r0 = 0
            int r2 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x0011
            java.util.List r14 = java.util.Collections.emptyList()
            return r14
        L_0x0011:
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r10 = 0
            java.lang.String r1 = "hits2"
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0069 }
            java.lang.String r4 = "hit_id"
            r11 = 0
            r3[r11] = r4     // Catch:{ SQLiteException -> 0x0069 }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r8 = "%s ASC"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ SQLiteException -> 0x0069 }
            java.lang.String r12 = "hit_id"
            r2[r11] = r12     // Catch:{ SQLiteException -> 0x0069 }
            java.lang.String r8 = java.lang.String.format(r8, r2)     // Catch:{ SQLiteException -> 0x0069 }
            java.lang.String r14 = java.lang.Long.toString(r14)     // Catch:{ SQLiteException -> 0x0069 }
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r14
            android.database.Cursor r14 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0069 }
            boolean r15 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            if (r15 == 0) goto L_0x005b
        L_0x004a:
            long r0 = r14.getLong(r11)     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            java.lang.Long r15 = java.lang.Long.valueOf(r0)     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            r9.add(r15)     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            boolean r15 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0064, all -> 0x0061 }
            if (r15 != 0) goto L_0x004a
        L_0x005b:
            if (r14 == 0) goto L_0x0074
            r14.close()
            return r9
        L_0x0061:
            r15 = move-exception
            r10 = r14
            goto L_0x0075
        L_0x0064:
            r15 = move-exception
            r10 = r14
            goto L_0x006a
        L_0x0067:
            r15 = move-exception
            goto L_0x0075
        L_0x0069:
            r15 = move-exception
        L_0x006a:
            java.lang.String r14 = "Error selecting hit ids"
            r13.zzd(r14, r15)     // Catch:{ all -> 0x0067 }
            if (r10 == 0) goto L_0x0074
            r10.close()
        L_0x0074:
            return r9
        L_0x0075:
            if (r10 == 0) goto L_0x007a
            r10.close()
        L_0x007a:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zzt(long):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzsz> zzu(long r24) {
        /*
            r23 = this;
            r10 = r23
            r1 = 0
            int r5 = (r24 > r1 ? 1 : (r24 == r1 ? 0 : -1))
            r11 = 0
            r12 = 1
            if (r5 < 0) goto L_0x000c
            r1 = r12
            goto L_0x000d
        L_0x000c:
            r1 = r11
        L_0x000d:
            com.google.android.gms.common.internal.zzac.zzax(r1)
            r23.zzmR()
            r23.zzob()
            android.database.sqlite.SQLiteDatabase r13 = r23.getWritableDatabase()
            r1 = 0
            java.lang.String r14 = "hits2"
            r2 = 5
            java.lang.String[] r15 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r2 = "hit_id"
            r15[r11] = r2     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r2 = "hit_time"
            r15[r12] = r2     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r2 = "hit_string"
            r9 = 2
            r15[r9] = r2     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r2 = "hit_url"
            r7 = 3
            r15[r7] = r2     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r2 = "hit_app_id"
            r8 = 4
            r15[r8] = r2     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            java.lang.String r2 = "%s ASC"
            java.lang.Object[] r5 = new java.lang.Object[r12]     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r6 = "hit_id"
            r5[r11] = r6     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r20 = java.lang.String.format(r2, r5)     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            java.lang.String r21 = java.lang.Long.toString(r24)     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            android.database.Cursor r13 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x00a7, all -> 0x00a3 }
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00a1 }
            r14.<init>()     // Catch:{ SQLiteException -> 0x00a1 }
            boolean r1 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x00a1 }
            if (r1 == 0) goto L_0x009b
        L_0x005e:
            long r15 = r13.getLong(r11)     // Catch:{ SQLiteException -> 0x00a1 }
            long r4 = r13.getLong(r12)     // Catch:{ SQLiteException -> 0x00a1 }
            java.lang.String r1 = r13.getString(r9)     // Catch:{ SQLiteException -> 0x00a1 }
            java.lang.String r2 = r13.getString(r7)     // Catch:{ SQLiteException -> 0x00a1 }
            int r17 = r13.getInt(r8)     // Catch:{ SQLiteException -> 0x00a1 }
            java.util.Map r3 = r10.zzbU(r1)     // Catch:{ SQLiteException -> 0x00a1 }
            boolean r6 = com.google.android.gms.internal.zztm.zzcj(r2)     // Catch:{ SQLiteException -> 0x00a1 }
            com.google.android.gms.internal.zzsz r2 = new com.google.android.gms.internal.zzsz     // Catch:{ SQLiteException -> 0x00a1 }
            r1 = r2
            r11 = r2
            r2 = r10
            r18 = r7
            r19 = r8
            r7 = r15
            r15 = r9
            r9 = r17
            r1.<init>(r2, r3, r4, r6, r7, r9)     // Catch:{ SQLiteException -> 0x00a1 }
            r14.add(r11)     // Catch:{ SQLiteException -> 0x00a1 }
            boolean r1 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x00a1 }
            if (r1 != 0) goto L_0x0094
            goto L_0x009b
        L_0x0094:
            r9 = r15
            r7 = r18
            r8 = r19
            r11 = 0
            goto L_0x005e
        L_0x009b:
            if (r13 == 0) goto L_0x00a0
            r13.close()
        L_0x00a0:
            return r14
        L_0x00a1:
            r0 = move-exception
            goto L_0x00a9
        L_0x00a3:
            r0 = move-exception
            r13 = r1
        L_0x00a5:
            r1 = r0
            goto L_0x00b2
        L_0x00a7:
            r0 = move-exception
            r13 = r1
        L_0x00a9:
            r1 = r0
            java.lang.String r2 = "Error loading hits from the database"
            r10.zze(r2, r1)     // Catch:{ all -> 0x00b0 }
            throw r1     // Catch:{ all -> 0x00b0 }
        L_0x00b0:
            r0 = move-exception
            goto L_0x00a5
        L_0x00b2:
            if (r13 == 0) goto L_0x00b7
            r13.close()
        L_0x00b7:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zzu(long):java.util.List");
    }

    public void zzv(long j) {
        zzmR();
        zzob();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zzr(arrayList);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzse> zzw(long r28) {
        /*
            r27 = this;
            r1 = r27
            r27.zzob()
            r27.zzmR()
            android.database.sqlite.SQLiteDatabase r2 = r27.getWritableDatabase()
            r3 = 5
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.lang.String r3 = "cid"
            r12 = 0
            r4[r12] = r3     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.lang.String r3 = "tid"
            r13 = 1
            r4[r13] = r3     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.lang.String r3 = "adid"
            r14 = 2
            r4[r14] = r3     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.lang.String r3 = "hits_count"
            r15 = 3
            r4[r15] = r3     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.lang.String r3 = "params"
            r10 = 4
            r4[r10] = r3     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            com.google.android.gms.internal.zzsp r3 = r27.zznT()     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            int r9 = r3.zzpu()     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.lang.String r16 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.lang.String r5 = "app_uid=?"
            java.lang.String[] r6 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.lang.String r3 = java.lang.String.valueOf(r28)     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            r6[r12] = r3     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.lang.String r3 = "properties"
            r7 = 0
            r8 = 0
            r17 = 0
            r11 = r9
            r9 = r17
            r10 = r16
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00c0, all -> 0x00bc }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            boolean r4 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            if (r4 == 0) goto L_0x00a4
        L_0x0058:
            java.lang.String r4 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            java.lang.String r5 = r2.getString(r13)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            int r6 = r2.getInt(r14)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            if (r6 == 0) goto L_0x0069
            r23 = r13
            goto L_0x006b
        L_0x0069:
            r23 = r12
        L_0x006b:
            int r6 = r2.getInt(r15)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            r8 = 4
            java.lang.String r9 = r2.getString(r8)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            java.util.Map r26 = r1.zzbV(r9)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            boolean r9 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            if (r9 != 0) goto L_0x0099
            boolean r9 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            if (r9 == 0) goto L_0x0086
            goto L_0x0099
        L_0x0086:
            com.google.android.gms.internal.zzse r9 = new com.google.android.gms.internal.zzse     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            r18 = r9
            r19 = r28
            r21 = r4
            r22 = r5
            r24 = r6
            r18.<init>(r19, r21, r22, r23, r24, r26)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            r3.add(r9)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            goto L_0x009e
        L_0x0099:
            java.lang.String r6 = "Read property with empty client id or tracker id"
            r1.zzc(r6, r4, r5)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
        L_0x009e:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            if (r4 != 0) goto L_0x0058
        L_0x00a4:
            int r4 = r3.size()     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
            if (r4 < r11) goto L_0x00af
            java.lang.String r4 = "Sending hits to too many properties. Campaign report might be incorrect"
            r1.zzbS(r4)     // Catch:{ SQLiteException -> 0x00b8, all -> 0x00b5 }
        L_0x00af:
            if (r2 == 0) goto L_0x00b4
            r2.close()
        L_0x00b4:
            return r3
        L_0x00b5:
            r0 = move-exception
            r11 = r2
            goto L_0x00ca
        L_0x00b8:
            r0 = move-exception
            r11 = r2
            r2 = r0
            goto L_0x00c3
        L_0x00bc:
            r0 = move-exception
            r2 = r0
            r11 = 0
            goto L_0x00cb
        L_0x00c0:
            r0 = move-exception
            r2 = r0
            r11 = 0
        L_0x00c3:
            java.lang.String r3 = "Error loading hits from the database"
            r1.zze(r3, r2)     // Catch:{ all -> 0x00c9 }
            throw r2     // Catch:{ all -> 0x00c9 }
        L_0x00c9:
            r0 = move-exception
        L_0x00ca:
            r2 = r0
        L_0x00cb:
            if (r11 == 0) goto L_0x00d0
            r11.close()
        L_0x00d0:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsg.zzw(long):java.util.List");
    }
}
