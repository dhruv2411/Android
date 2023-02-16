package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class zzx implements DataLayer.zzc {
    /* access modifiers changed from: private */
    public static final String zzbFL = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[]{"datalayer", "ID", "key", FirebaseAnalytics.Param.VALUE, "expires"});
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Executor zzbFM;
    private zza zzbFN;
    private int zzbFO;
    private zze zzuP;

    class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x0036 A[Catch:{ all -> 0x0028 }] */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x003b A[Catch:{ all -> 0x0028 }] */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0045  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x004b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean zza(java.lang.String r11, android.database.sqlite.SQLiteDatabase r12) {
            /*
                r10 = this;
                r0 = 0
                r1 = 0
                java.lang.String r3 = "SQLITE_MASTER"
                r2 = 1
                java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002a }
                java.lang.String r5 = "name"
                r4[r0] = r5     // Catch:{ SQLiteException -> 0x002a }
                java.lang.String r5 = "name=?"
                java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002a }
                r6[r0] = r11     // Catch:{ SQLiteException -> 0x002a }
                r7 = 0
                r8 = 0
                r9 = 0
                r2 = r12
                android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x002a }
                boolean r1 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x0026, all -> 0x0023 }
                if (r12 == 0) goto L_0x0022
                r12.close()
            L_0x0022:
                return r1
            L_0x0023:
                r11 = move-exception
                r1 = r12
                goto L_0x0049
            L_0x0026:
                r1 = r12
                goto L_0x002a
            L_0x0028:
                r11 = move-exception
                goto L_0x0049
            L_0x002a:
                java.lang.String r12 = "Error querying for table "
                java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x0028 }
                int r2 = r11.length()     // Catch:{ all -> 0x0028 }
                if (r2 == 0) goto L_0x003b
                java.lang.String r11 = r12.concat(r11)     // Catch:{ all -> 0x0028 }
                goto L_0x0040
            L_0x003b:
                java.lang.String r11 = new java.lang.String     // Catch:{ all -> 0x0028 }
                r11.<init>(r12)     // Catch:{ all -> 0x0028 }
            L_0x0040:
                com.google.android.gms.tagmanager.zzbo.zzbh(r11)     // Catch:{ all -> 0x0028 }
                if (r1 == 0) goto L_0x0048
                r1.close()
            L_0x0048:
                return r0
            L_0x0049:
                if (r1 == 0) goto L_0x004e
                r1.close()
            L_0x004e:
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzx.zza.zza(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
        }

        /* JADX INFO: finally extract failed */
        private void zzc(SQLiteDatabase sQLiteDatabase) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", (String[]) null);
            HashSet hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (String add : columnNames) {
                    hashSet.add(add);
                }
                rawQuery.close();
                if (!hashSet.remove("key") || !hashSet.remove(FirebaseAnalytics.Param.VALUE) || !hashSet.remove("ID") || !hashSet.remove("expires")) {
                    throw new SQLiteException("Database column missing");
                } else if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } catch (Throwable th) {
                rawQuery.close();
                throw th;
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            SQLiteDatabase sQLiteDatabase;
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (SQLiteException unused) {
                zzx.this.mContext.getDatabasePath("google_tagmanager.db").delete();
                sQLiteDatabase = null;
            }
            return sQLiteDatabase == null ? super.getWritableDatabase() : sQLiteDatabase;
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzan.zzca(sQLiteDatabase.getPath());
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
            if (!zza("datalayer", sQLiteDatabase)) {
                sQLiteDatabase.execSQL(zzx.zzbFL);
            } else {
                zzc(sQLiteDatabase);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    private static class zzb {
        final String zzAX;
        final byte[] zzbFU;

        zzb(String str, byte[] bArr) {
            this.zzAX = str;
            this.zzbFU = bArr;
        }

        public String toString() {
            String str = this.zzAX;
            int hashCode = Arrays.hashCode(this.zzbFU);
            StringBuilder sb = new StringBuilder(54 + String.valueOf(str).length());
            sb.append("KeyAndSerialized: key = ");
            sb.append(str);
            sb.append(" serialized hash = ");
            sb.append(hashCode);
            return sb.toString();
        }
    }

    public zzx(Context context) {
        this(context, zzi.zzzc(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
    }

    zzx(Context context, zze zze, String str, int i, Executor executor) {
        this.mContext = context;
        this.zzuP = zze;
        this.zzbFO = i;
        this.zzbFM = executor;
        this.zzbFN = new zza(this.mContext, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001f A[SYNTHETIC, Splitter:B:13:0x001f] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0029 A[SYNTHETIC, Splitter:B:22:0x0029] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] zzJ(java.lang.Object r4) {
        /*
            r3 = this;
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0026, all -> 0x001c }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0026, all -> 0x001c }
            r2.writeObject(r4)     // Catch:{ IOException -> 0x0027, all -> 0x0019 }
            byte[] r4 = r0.toByteArray()     // Catch:{ IOException -> 0x0027, all -> 0x0019 }
            r2.close()     // Catch:{ IOException -> 0x0018 }
            r0.close()     // Catch:{ IOException -> 0x0018 }
        L_0x0018:
            return r4
        L_0x0019:
            r4 = move-exception
            r1 = r2
            goto L_0x001d
        L_0x001c:
            r4 = move-exception
        L_0x001d:
            if (r1 == 0) goto L_0x0022
            r1.close()     // Catch:{ IOException -> 0x0025 }
        L_0x0022:
            r0.close()     // Catch:{ IOException -> 0x0025 }
        L_0x0025:
            throw r4
        L_0x0026:
            r2 = r1
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()     // Catch:{ IOException -> 0x002f }
        L_0x002c:
            r0.close()     // Catch:{ IOException -> 0x002f }
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzx.zzJ(java.lang.Object):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001e A[SYNTHETIC, Splitter:B:13:0x001e] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0028 A[SYNTHETIC, Splitter:B:22:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0032 A[SYNTHETIC, Splitter:B:31:0x0032] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object zzL(byte[] r5) {
        /*
            r4 = this;
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r5)
            r5 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x0025, all -> 0x001b }
            r1.<init>(r0)     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x0025, all -> 0x001b }
            java.lang.Object r2 = r1.readObject()     // Catch:{ IOException -> 0x0030, ClassNotFoundException -> 0x0026, all -> 0x0016 }
            r1.close()     // Catch:{ IOException -> 0x0015 }
            r0.close()     // Catch:{ IOException -> 0x0015 }
        L_0x0015:
            return r2
        L_0x0016:
            r5 = move-exception
            r3 = r1
            r1 = r5
            r5 = r3
            goto L_0x001c
        L_0x001b:
            r1 = move-exception
        L_0x001c:
            if (r5 == 0) goto L_0x0021
            r5.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0021:
            r0.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0024:
            throw r1
        L_0x0025:
            r1 = r5
        L_0x0026:
            if (r1 == 0) goto L_0x002b
            r1.close()     // Catch:{ IOException -> 0x002e }
        L_0x002b:
            r0.close()     // Catch:{ IOException -> 0x002e }
        L_0x002e:
            return r5
        L_0x002f:
            r1 = r5
        L_0x0030:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0035:
            r0.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzx.zzL(byte[]):java.lang.Object");
    }

    private List<DataLayer.zza> zzN(List<zzb> list) {
        ArrayList arrayList = new ArrayList();
        for (zzb next : list) {
            arrayList.add(new DataLayer.zza(next.zzAX, zzL(next.zzbFU)));
        }
        return arrayList;
    }

    private List<zzb> zzO(List<DataLayer.zza> list) {
        ArrayList arrayList = new ArrayList();
        for (DataLayer.zza next : list) {
            arrayList.add(new zzb(next.zzAX, zzJ(next.mValue)));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public List<DataLayer.zza> zzQA() {
        try {
            zzaz(this.zzuP.currentTimeMillis());
            return zzN(zzQB());
        } finally {
            zzQD();
        }
    }

    private List<zzb> zzQB() {
        SQLiteDatabase zzhe = zzhe("Error opening database for loadSerialized.");
        ArrayList arrayList = new ArrayList();
        if (zzhe == null) {
            return arrayList;
        }
        Cursor query = zzhe.query("datalayer", new String[]{"key", FirebaseAnalytics.Param.VALUE}, (String) null, (String[]) null, (String) null, (String) null, "ID", (String) null);
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzb(query.getString(0), query.getBlob(1)));
            } finally {
                query.close();
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int zzQC() {
        /*
            r4 = this;
            java.lang.String r0 = "Error opening database for getNumStoredEntries."
            android.database.sqlite.SQLiteDatabase r0 = r4.zzhe(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r2 = 0
            java.lang.String r3 = "SELECT COUNT(*) from datalayer"
            android.database.Cursor r0 = r0.rawQuery(r3, r2)     // Catch:{ SQLiteException -> 0x0029 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0025, all -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            long r2 = r0.getLong(r1)     // Catch:{ SQLiteException -> 0x0025, all -> 0x0022 }
            int r1 = (int) r2
        L_0x001c:
            if (r0 == 0) goto L_0x0033
            r0.close()
            return r1
        L_0x0022:
            r1 = move-exception
            r2 = r0
            goto L_0x0034
        L_0x0025:
            r2 = r0
            goto L_0x0029
        L_0x0027:
            r1 = move-exception
            goto L_0x0034
        L_0x0029:
            java.lang.String r0 = "Error getting numStoredEntries"
            com.google.android.gms.tagmanager.zzbo.zzbh(r0)     // Catch:{ all -> 0x0027 }
            if (r2 == 0) goto L_0x0033
            r2.close()
        L_0x0033:
            return r1
        L_0x0034:
            if (r2 == 0) goto L_0x0039
            r2.close()
        L_0x0039:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzx.zzQC():int");
    }

    private void zzQD() {
        try {
            this.zzbFN.close();
        } catch (SQLiteException unused) {
        }
    }

    private void zzaz(long j) {
        SQLiteDatabase zzhe = zzhe("Error opening database for deleteOlderThan.");
        if (zzhe != null) {
            try {
                int delete = zzhe.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)});
                StringBuilder sb = new StringBuilder(33);
                sb.append("Deleted ");
                sb.append(delete);
                sb.append(" expired items");
                zzbo.v(sb.toString());
            } catch (SQLiteException unused) {
                zzbo.zzbh("Error deleting old entries.");
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void zzb(List<zzb> list, long j) {
        try {
            long currentTimeMillis = this.zzuP.currentTimeMillis();
            zzaz(currentTimeMillis);
            zznA(list.size());
            zzc(list, currentTimeMillis + j);
            zzQD();
        } catch (Throwable th) {
            zzQD();
            throw th;
        }
    }

    private void zzc(List<zzb> list, long j) {
        SQLiteDatabase zzhe = zzhe("Error opening database for writeEntryToDatabase.");
        if (zzhe != null) {
            for (zzb next : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("expires", Long.valueOf(j));
                contentValues.put("key", next.zzAX);
                contentValues.put(FirebaseAnalytics.Param.VALUE, next.zzbFU);
                zzhe.insert("datalayer", (String) null, contentValues);
            }
        }
    }

    private void zzg(String[] strArr) {
        SQLiteDatabase zzhe;
        if (strArr != null && strArr.length != 0 && (zzhe = zzhe("Error opening database for deleteEntries.")) != null) {
            try {
                zzhe.delete("datalayer", String.format("%s in (%s)", new Object[]{"ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
            } catch (SQLiteException unused) {
                String valueOf = String.valueOf(Arrays.toString(strArr));
                zzbo.zzbh(valueOf.length() != 0 ? "Error deleting entries ".concat(valueOf) : new String("Error deleting entries "));
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzhd(String str) {
        SQLiteDatabase zzhe = zzhe("Error opening database for clearKeysWithPrefix.");
        if (zzhe != null) {
            try {
                int delete = zzhe.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, String.valueOf(str).concat(".%")});
                StringBuilder sb = new StringBuilder(25);
                sb.append("Cleared ");
                sb.append(delete);
                sb.append(" items");
                zzbo.v(sb.toString());
            } catch (SQLiteException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb2 = new StringBuilder(44 + String.valueOf(str).length() + String.valueOf(valueOf).length());
                sb2.append("Error deleting entries with key prefix: ");
                sb2.append(str);
                sb2.append(" (");
                sb2.append(valueOf);
                sb2.append(").");
                zzbo.zzbh(sb2.toString());
            } catch (Throwable th) {
                zzQD();
                throw th;
            }
            zzQD();
        }
    }

    private SQLiteDatabase zzhe(String str) {
        try {
            return this.zzbFN.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzbo.zzbh(str);
            return null;
        }
    }

    private void zznA(int i) {
        int zzQC = (zzQC() - this.zzbFO) + i;
        if (zzQC > 0) {
            List<String> zznB = zznB(zzQC);
            int size = zznB.size();
            StringBuilder sb = new StringBuilder(64);
            sb.append("DataLayer store full, deleting ");
            sb.append(size);
            sb.append(" entries to make room.");
            zzbo.zzbg(sb.toString());
            zzg((String[]) zznB.toArray(new String[0]));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0078 A[Catch:{ all -> 0x0065 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007d A[Catch:{ all -> 0x0065 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.String> zznB(int r15) {
        /*
            r14 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r15 > 0) goto L_0x000d
            java.lang.String r15 = "Invalid maxEntries specified. Skipping."
            com.google.android.gms.tagmanager.zzbo.zzbh(r15)
            return r0
        L_0x000d:
            java.lang.String r1 = "Error opening database for peekEntryIds."
            android.database.sqlite.SQLiteDatabase r2 = r14.zzhe(r1)
            if (r2 != 0) goto L_0x0016
            return r0
        L_0x0016:
            r1 = 0
            java.lang.String r3 = "datalayer"
            r4 = 1
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0067 }
            java.lang.String r6 = "ID"
            r11 = 0
            r5[r11] = r6     // Catch:{ SQLiteException -> 0x0067 }
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r10 = "%s ASC"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ SQLiteException -> 0x0067 }
            java.lang.String r12 = "ID"
            r4[r11] = r12     // Catch:{ SQLiteException -> 0x0067 }
            java.lang.String r10 = java.lang.String.format(r10, r4)     // Catch:{ SQLiteException -> 0x0067 }
            java.lang.String r15 = java.lang.Integer.toString(r15)     // Catch:{ SQLiteException -> 0x0067 }
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r15
            android.database.Cursor r15 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0067 }
            boolean r1 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
            if (r1 == 0) goto L_0x0057
        L_0x0046:
            long r1 = r15.getLong(r11)     // Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
            r0.add(r1)     // Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
            boolean r1 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x0060, all -> 0x005d }
            if (r1 != 0) goto L_0x0046
        L_0x0057:
            if (r15 == 0) goto L_0x008a
            r15.close()
            return r0
        L_0x005d:
            r0 = move-exception
            r1 = r15
            goto L_0x008b
        L_0x0060:
            r1 = move-exception
            r13 = r1
            r1 = r15
            r15 = r13
            goto L_0x0068
        L_0x0065:
            r0 = move-exception
            goto L_0x008b
        L_0x0067:
            r15 = move-exception
        L_0x0068:
            java.lang.String r2 = "Error in peekEntries fetching entryIds: "
            java.lang.String r15 = r15.getMessage()     // Catch:{ all -> 0x0065 }
            java.lang.String r15 = java.lang.String.valueOf(r15)     // Catch:{ all -> 0x0065 }
            int r3 = r15.length()     // Catch:{ all -> 0x0065 }
            if (r3 == 0) goto L_0x007d
            java.lang.String r15 = r2.concat(r15)     // Catch:{ all -> 0x0065 }
            goto L_0x0082
        L_0x007d:
            java.lang.String r15 = new java.lang.String     // Catch:{ all -> 0x0065 }
            r15.<init>(r2)     // Catch:{ all -> 0x0065 }
        L_0x0082:
            com.google.android.gms.tagmanager.zzbo.zzbh(r15)     // Catch:{ all -> 0x0065 }
            if (r1 == 0) goto L_0x008a
            r1.close()
        L_0x008a:
            return r0
        L_0x008b:
            if (r1 == 0) goto L_0x0090
            r1.close()
        L_0x0090:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzx.zznB(int):java.util.List");
    }

    public void zza(final DataLayer.zzc.zza zza2) {
        this.zzbFM.execute(new Runnable() {
            public void run() {
                zza2.zzM(zzx.this.zzQA());
            }
        });
    }

    public void zza(List<DataLayer.zza> list, final long j) {
        final List<zzb> zzO = zzO(list);
        this.zzbFM.execute(new Runnable() {
            public void run() {
                zzx.this.zzb(zzO, j);
            }
        });
    }

    public void zzhc(final String str) {
        this.zzbFM.execute(new Runnable() {
            public void run() {
                zzx.this.zzhd(str);
            }
        });
    }
}
