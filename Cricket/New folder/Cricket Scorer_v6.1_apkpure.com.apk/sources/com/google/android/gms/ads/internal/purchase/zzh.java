package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import java.util.Locale;

@zzme
public class zzh {
    /* access modifiers changed from: private */
    public static final String zzPL = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", new Object[]{"InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time"});
    private static zzh zzPN;
    private static final Object zzrJ = new Object();
    private final zza zzPM;

    public class zza extends SQLiteOpenHelper {
        public zza(zzh zzh, Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 4);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(zzh.zzPL);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("Database updated from version ");
            sb.append(i);
            sb.append(" to version ");
            sb.append(i2);
            zzpk.zzbg(sb.toString());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
            onCreate(sQLiteDatabase);
        }
    }

    zzh(Context context) {
        this.zzPM = new zza(this, context, "google_inapp_purchase.db");
    }

    public static zzh zzu(Context context) {
        zzh zzh;
        synchronized (zzrJ) {
            if (zzPN == null) {
                zzPN = new zzh(context);
            }
            zzh = zzPN;
        }
        return zzh;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0023, code lost:
        return r3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0045 A[Catch:{ all -> 0x0032 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004a A[Catch:{ all -> 0x0032 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0054 A[SYNTHETIC, Splitter:B:35:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getRecordCount() {
        /*
            r7 = this;
            java.lang.Object r0 = zzrJ
            monitor-enter(r0)
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch:{ all -> 0x005f }
            r2 = 0
            if (r1 != 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x005f }
            return r2
        L_0x000c:
            r3 = 0
            java.lang.String r4 = "select count(*) from InAppPurchase"
            android.database.Cursor r1 = r1.rawQuery(r4, r3)     // Catch:{ SQLiteException -> 0x0034 }
            boolean r3 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x002d, all -> 0x002a }
            if (r3 == 0) goto L_0x0024
            int r3 = r1.getInt(r2)     // Catch:{ SQLiteException -> 0x002d, all -> 0x002a }
            if (r1 == 0) goto L_0x0022
            r1.close()     // Catch:{ all -> 0x005f }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x005f }
            return r3
        L_0x0024:
            if (r1 == 0) goto L_0x0057
            r1.close()     // Catch:{ all -> 0x005f }
            goto L_0x0057
        L_0x002a:
            r2 = move-exception
            r3 = r1
            goto L_0x0059
        L_0x002d:
            r3 = move-exception
            r6 = r3
            r3 = r1
            r1 = r6
            goto L_0x0035
        L_0x0032:
            r2 = move-exception
            goto L_0x0059
        L_0x0034:
            r1 = move-exception
        L_0x0035:
            java.lang.String r4 = "Error getting record count"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0032 }
            int r5 = r1.length()     // Catch:{ all -> 0x0032 }
            if (r5 == 0) goto L_0x004a
            java.lang.String r1 = r4.concat(r1)     // Catch:{ all -> 0x0032 }
            goto L_0x004f
        L_0x004a:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0032 }
            r1.<init>(r4)     // Catch:{ all -> 0x0032 }
        L_0x004f:
            com.google.android.gms.internal.zzpk.zzbh(r1)     // Catch:{ all -> 0x0032 }
            if (r3 == 0) goto L_0x0057
            r3.close()     // Catch:{ all -> 0x005f }
        L_0x0057:
            monitor-exit(r0)     // Catch:{ all -> 0x005f }
            return r2
        L_0x0059:
            if (r3 == 0) goto L_0x005e
            r3.close()     // Catch:{ all -> 0x005f }
        L_0x005e:
            throw r2     // Catch:{ all -> 0x005f }
        L_0x005f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.purchase.zzh.getRecordCount():int");
    }

    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzPM.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzpk.zzbh("Error opening writable conversion tracking database");
            return null;
        }
    }

    public zzf zza(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        return new zzf(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }

    public void zza(zzf zzf) {
        if (zzf != null) {
            synchronized (zzrJ) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase != null) {
                    writableDatabase.delete("InAppPurchase", String.format(Locale.US, "%s = %d", new Object[]{"purchase_id", Long.valueOf(zzf.zzPG)}), (String[]) null);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzb(com.google.android.gms.ads.internal.purchase.zzf r7) {
        /*
            r6 = this;
            if (r7 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r0 = zzrJ
            monitor-enter(r0)
            android.database.sqlite.SQLiteDatabase r1 = r6.getWritableDatabase()     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            return
        L_0x000e:
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ all -> 0x0047 }
            r2.<init>()     // Catch:{ all -> 0x0047 }
            java.lang.String r3 = "product_id"
            java.lang.String r4 = r7.zzPI     // Catch:{ all -> 0x0047 }
            r2.put(r3, r4)     // Catch:{ all -> 0x0047 }
            java.lang.String r3 = "developer_payload"
            java.lang.String r4 = r7.zzPH     // Catch:{ all -> 0x0047 }
            r2.put(r3, r4)     // Catch:{ all -> 0x0047 }
            java.lang.String r3 = "record_time"
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0047 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0047 }
            r2.put(r3, r4)     // Catch:{ all -> 0x0047 }
            java.lang.String r3 = "InAppPurchase"
            r4 = 0
            long r1 = r1.insert(r3, r4, r2)     // Catch:{ all -> 0x0047 }
            r7.zzPG = r1     // Catch:{ all -> 0x0047 }
            int r7 = r6.getRecordCount()     // Catch:{ all -> 0x0047 }
            long r1 = (long) r7     // Catch:{ all -> 0x0047 }
            r3 = 20000(0x4e20, double:9.8813E-320)
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x0045
            r6.zziL()     // Catch:{ all -> 0x0047 }
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            return
        L_0x0047:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.purchase.zzh.zzb(com.google.android.gms.ads.internal.purchase.zzf):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x005c A[Catch:{ all -> 0x0049 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0061 A[Catch:{ all -> 0x0049 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006c A[SYNTHETIC, Splitter:B:36:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.ads.internal.purchase.zzf> zzg(long r13) {
        /*
            r12 = this;
            java.lang.Object r0 = zzrJ
            monitor-enter(r0)
            java.util.LinkedList r1 = new java.util.LinkedList     // Catch:{ all -> 0x0077 }
            r1.<init>()     // Catch:{ all -> 0x0077 }
            r2 = 0
            int r4 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x0010
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            return r1
        L_0x0010:
            android.database.sqlite.SQLiteDatabase r2 = r12.getWritableDatabase()     // Catch:{ all -> 0x0077 }
            if (r2 != 0) goto L_0x0018
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            return r1
        L_0x0018:
            r11 = 0
            java.lang.String r9 = "record_time ASC"
            java.lang.String r3 = "InAppPurchase"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r10 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x004b }
            android.database.Cursor r13 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x004b }
            boolean r14 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x0046, all -> 0x0043 }
            if (r14 == 0) goto L_0x003d
        L_0x0030:
            com.google.android.gms.ads.internal.purchase.zzf r14 = r12.zza((android.database.Cursor) r13)     // Catch:{ SQLiteException -> 0x0046, all -> 0x0043 }
            r1.add(r14)     // Catch:{ SQLiteException -> 0x0046, all -> 0x0043 }
            boolean r14 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x0046, all -> 0x0043 }
            if (r14 != 0) goto L_0x0030
        L_0x003d:
            if (r13 == 0) goto L_0x006f
            r13.close()     // Catch:{ all -> 0x0077 }
            goto L_0x006f
        L_0x0043:
            r14 = move-exception
            r11 = r13
            goto L_0x0071
        L_0x0046:
            r14 = move-exception
            r11 = r13
            goto L_0x004c
        L_0x0049:
            r14 = move-exception
            goto L_0x0071
        L_0x004b:
            r14 = move-exception
        L_0x004c:
            java.lang.String r13 = "Error extracing purchase info: "
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x0049 }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ all -> 0x0049 }
            int r2 = r14.length()     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0061
            java.lang.String r13 = r13.concat(r14)     // Catch:{ all -> 0x0049 }
            goto L_0x0067
        L_0x0061:
            java.lang.String r14 = new java.lang.String     // Catch:{ all -> 0x0049 }
            r14.<init>(r13)     // Catch:{ all -> 0x0049 }
            r13 = r14
        L_0x0067:
            com.google.android.gms.internal.zzpk.zzbh(r13)     // Catch:{ all -> 0x0049 }
            if (r11 == 0) goto L_0x006f
            r11.close()     // Catch:{ all -> 0x0077 }
        L_0x006f:
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            return r1
        L_0x0071:
            if (r11 == 0) goto L_0x0076
            r11.close()     // Catch:{ all -> 0x0077 }
        L_0x0076:
            throw r14     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r13 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.purchase.zzh.zzg(long):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x004a A[Catch:{ all -> 0x0037 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004f A[Catch:{ all -> 0x0037 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005a A[SYNTHETIC, Splitter:B:32:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zziL() {
        /*
            r11 = this;
            java.lang.Object r0 = zzrJ
            monitor-enter(r0)
            android.database.sqlite.SQLiteDatabase r1 = r11.getWritableDatabase()     // Catch:{ all -> 0x0065 }
            if (r1 != 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0065 }
            return
        L_0x000b:
            r10 = 0
            java.lang.String r8 = "record_time ASC"
            java.lang.String r2 = "InAppPurchase"
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r9 = "1"
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0039 }
            if (r1 == 0) goto L_0x0031
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x002e, all -> 0x002b }
            if (r2 == 0) goto L_0x0031
            com.google.android.gms.ads.internal.purchase.zzf r2 = r11.zza((android.database.Cursor) r1)     // Catch:{ SQLiteException -> 0x002e, all -> 0x002b }
            r11.zza((com.google.android.gms.ads.internal.purchase.zzf) r2)     // Catch:{ SQLiteException -> 0x002e, all -> 0x002b }
            goto L_0x0031
        L_0x002b:
            r2 = move-exception
            r10 = r1
            goto L_0x005f
        L_0x002e:
            r2 = move-exception
            r10 = r1
            goto L_0x003a
        L_0x0031:
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ all -> 0x0065 }
            goto L_0x005d
        L_0x0037:
            r2 = move-exception
            goto L_0x005f
        L_0x0039:
            r2 = move-exception
        L_0x003a:
            java.lang.String r1 = "Error remove oldest record"
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0037 }
            int r3 = r2.length()     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x004f
            java.lang.String r1 = r1.concat(r2)     // Catch:{ all -> 0x0037 }
            goto L_0x0055
        L_0x004f:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0037 }
            r2.<init>(r1)     // Catch:{ all -> 0x0037 }
            r1 = r2
        L_0x0055:
            com.google.android.gms.internal.zzpk.zzbh(r1)     // Catch:{ all -> 0x0037 }
            if (r10 == 0) goto L_0x005d
            r10.close()     // Catch:{ all -> 0x0065 }
        L_0x005d:
            monitor-exit(r0)     // Catch:{ all -> 0x0065 }
            return
        L_0x005f:
            if (r10 == 0) goto L_0x0064
            r10.close()     // Catch:{ all -> 0x0065 }
        L_0x0064:
            throw r2     // Catch:{ all -> 0x0065 }
        L_0x0065:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0065 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.purchase.zzh.zziL():void");
    }
}
