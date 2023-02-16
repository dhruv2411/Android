package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.zze;
import java.util.Map;

public class zzatv extends zzauh {
    private final zza zzbsx = new zza(getContext(), zzow());
    private boolean zzbsy;

    @TargetApi(11)
    private class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @WorkerThread
        public SQLiteDatabase getWritableDatabase() {
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e) {
                if (Build.VERSION.SDK_INT < 11 || !(e instanceof SQLiteDatabaseLockedException)) {
                    zzatv.this.zzKl().zzLY().log("Opening the local database failed, dropping and recreating it");
                    String zzow = zzatv.this.zzow();
                    if (!zzatv.this.getContext().getDatabasePath(zzow).delete()) {
                        zzatv.this.zzKl().zzLY().zzj("Failed to delete corrupted local db file", zzow);
                    }
                    try {
                        return super.getWritableDatabase();
                    } catch (SQLiteException e2) {
                        zzatv.this.zzKl().zzLY().zzj("Failed to open local database. Events will bypass local storage", e2);
                        return null;
                    }
                } else {
                    throw e;
                }
            }
        }

        @WorkerThread
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzatj.zza(zzatv.this.zzKl(), sQLiteDatabase);
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
            zzatj.zza(zzatv.this.zzKl(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", (Map<String, String>) null);
        }

        @WorkerThread
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    zzatv(zzaue zzaue) {
        super(zzaue);
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [int, boolean] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d3 A[Catch:{ all -> 0x0117 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00da A[ADDED_TO_REGION, Catch:{ all -> 0x0117 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0111 A[SYNTHETIC] */
    @android.support.annotation.WorkerThread
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean zza(int r18, byte[] r19) {
        /*
            r17 = this;
            r1 = r17
            r17.zzJW()
            r17.zzmR()
            boolean r2 = r1.zzbsy
            r3 = 0
            if (r2 == 0) goto L_0x000e
            return r3
        L_0x000e:
            android.content.ContentValues r2 = new android.content.ContentValues
            r2.<init>()
            java.lang.String r4 = "type"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r18)
            r2.put(r4, r5)
            java.lang.String r4 = "entry"
            r5 = r19
            r2.put(r4, r5)
            com.google.android.gms.internal.zzati r4 = r17.zzKn()
            r4.zzLp()
            r4 = 5
            r5 = r3
            r6 = r4
        L_0x002d:
            if (r5 >= r4) goto L_0x0120
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r17.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x00fb, SQLiteException -> 0x00c6, all -> 0x00c1 }
            if (r9 != 0) goto L_0x004d
            r1.zzbsy = r8     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            if (r9 == 0) goto L_0x003e
            r9.close()
        L_0x003e:
            return r3
        L_0x003f:
            r0 = move-exception
            r2 = r0
            goto L_0x011a
        L_0x0043:
            r0 = move-exception
            r3 = r0
            r7 = r9
            goto L_0x00c9
        L_0x0048:
            r0 = move-exception
            r3 = r0
            r7 = r9
            goto L_0x00fe
        L_0x004d:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            r10 = 0
            java.lang.String r12 = "select count(1) from messages"
            android.database.Cursor r12 = r9.rawQuery(r12, r7)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            if (r12 == 0) goto L_0x0064
            boolean r13 = r12.moveToFirst()     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            if (r13 == 0) goto L_0x0064
            long r10 = r12.getLong(r3)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
        L_0x0064:
            r12 = 100000(0x186a0, double:4.94066E-319)
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 < 0) goto L_0x00ae
            com.google.android.gms.internal.zzatx r14 = r17.zzKl()     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            com.google.android.gms.internal.zzatx$zza r14 = r14.zzLY()     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            java.lang.String r15 = "Data loss, local db full"
            r14.log(r15)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            long r14 = r12 - r10
            r10 = 1
            long r12 = r14 + r10
            java.lang.String r10 = "messages"
            java.lang.String r11 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String[] r14 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            java.lang.String r15 = java.lang.Long.toString(r12)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            r14[r3] = r15     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            int r10 = r9.delete(r10, r11, r14)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            long r10 = (long) r10     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 == 0) goto L_0x00ae
            com.google.android.gms.internal.zzatx r14 = r17.zzKl()     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            com.google.android.gms.internal.zzatx$zza r14 = r14.zzLY()     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            java.lang.String r15 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r4 = java.lang.Long.valueOf(r12)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            java.lang.Long r3 = java.lang.Long.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            long r7 = r12 - r10
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            r14.zzd(r15, r4, r3, r7)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
        L_0x00ae:
            java.lang.String r3 = "messages"
            r4 = 0
            r9.insertOrThrow(r3, r4, r2)     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x0048, SQLiteException -> 0x0043, all -> 0x003f }
            if (r9 == 0) goto L_0x00bf
            r9.close()
        L_0x00bf:
            r2 = 1
            return r2
        L_0x00c1:
            r0 = move-exception
            r4 = r7
            r2 = r0
            r9 = r4
            goto L_0x011a
        L_0x00c6:
            r0 = move-exception
            r4 = r7
            r3 = r0
        L_0x00c9:
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0117 }
            r8 = 11
            if (r4 < r8) goto L_0x00da
            boolean r4 = r3 instanceof android.database.sqlite.SQLiteDatabaseLockedException     // Catch:{ all -> 0x0117 }
            if (r4 == 0) goto L_0x00da
            long r3 = (long) r6     // Catch:{ all -> 0x0117 }
            android.os.SystemClock.sleep(r3)     // Catch:{ all -> 0x0117 }
            int r6 = r6 + 20
            goto L_0x00f5
        L_0x00da:
            if (r7 == 0) goto L_0x00e5
            boolean r4 = r7.inTransaction()     // Catch:{ all -> 0x0117 }
            if (r4 == 0) goto L_0x00e5
            r7.endTransaction()     // Catch:{ all -> 0x0117 }
        L_0x00e5:
            com.google.android.gms.internal.zzatx r4 = r17.zzKl()     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x0117 }
            java.lang.String r8 = "Error writing entry to local database"
            r4.zzj(r8, r3)     // Catch:{ all -> 0x0117 }
            r3 = 1
            r1.zzbsy = r3     // Catch:{ all -> 0x0117 }
        L_0x00f5:
            if (r7 == 0) goto L_0x0111
        L_0x00f7:
            r7.close()
            goto L_0x0111
        L_0x00fb:
            r0 = move-exception
            r4 = r7
            r3 = r0
        L_0x00fe:
            com.google.android.gms.internal.zzatx r4 = r17.zzKl()     // Catch:{ all -> 0x0117 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x0117 }
            java.lang.String r8 = "Error writing entry to local database"
            r4.zzj(r8, r3)     // Catch:{ all -> 0x0117 }
            r3 = 1
            r1.zzbsy = r3     // Catch:{ all -> 0x0117 }
            if (r7 == 0) goto L_0x0111
            goto L_0x00f7
        L_0x0111:
            int r5 = r5 + 1
            r3 = 0
            r4 = 5
            goto L_0x002d
        L_0x0117:
            r0 = move-exception
            r2 = r0
            r9 = r7
        L_0x011a:
            if (r9 == 0) goto L_0x011f
            r9.close()
        L_0x011f:
            throw r2
        L_0x0120:
            com.google.android.gms.internal.zzatx r2 = r17.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMa()
            java.lang.String r3 = "Failed to write entry to local database"
            r2.log(r3)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatv.zza(int, byte[]):boolean");
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public SQLiteDatabase getWritableDatabase() {
        int i = Build.VERSION.SDK_INT;
        if (this.zzbsy) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzbsx.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzbsy = true;
        return null;
    }

    public /* bridge */ /* synthetic */ void zzJV() {
        super.zzJV();
    }

    public /* bridge */ /* synthetic */ void zzJW() {
        super.zzJW();
    }

    public /* bridge */ /* synthetic */ void zzJX() {
        super.zzJX();
    }

    public /* bridge */ /* synthetic */ zzatb zzJY() {
        return super.zzJY();
    }

    public /* bridge */ /* synthetic */ zzatf zzJZ() {
        return super.zzJZ();
    }

    public /* bridge */ /* synthetic */ zzauj zzKa() {
        return super.zzKa();
    }

    public /* bridge */ /* synthetic */ zzatu zzKb() {
        return super.zzKb();
    }

    public /* bridge */ /* synthetic */ zzatl zzKc() {
        return super.zzKc();
    }

    public /* bridge */ /* synthetic */ zzaul zzKd() {
        return super.zzKd();
    }

    public /* bridge */ /* synthetic */ zzauk zzKe() {
        return super.zzKe();
    }

    public /* bridge */ /* synthetic */ zzatv zzKf() {
        return super.zzKf();
    }

    public /* bridge */ /* synthetic */ zzatj zzKg() {
        return super.zzKg();
    }

    public /* bridge */ /* synthetic */ zzaut zzKh() {
        return super.zzKh();
    }

    public /* bridge */ /* synthetic */ zzauc zzKi() {
        return super.zzKi();
    }

    public /* bridge */ /* synthetic */ zzaun zzKj() {
        return super.zzKj();
    }

    public /* bridge */ /* synthetic */ zzaud zzKk() {
        return super.zzKk();
    }

    public /* bridge */ /* synthetic */ zzatx zzKl() {
        return super.zzKl();
    }

    public /* bridge */ /* synthetic */ zzaua zzKm() {
        return super.zzKm();
    }

    public /* bridge */ /* synthetic */ zzati zzKn() {
        return super.zzKn();
    }

    /* access modifiers changed from: package-private */
    public boolean zzLM() {
        return getContext().getDatabasePath(zzow()).exists();
    }

    public boolean zza(zzatq zzatq) {
        int i = Build.VERSION.SDK_INT;
        Parcel obtain = Parcel.obtain();
        zzatq.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzKl().zzMa().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public boolean zza(zzauq zzauq) {
        int i = Build.VERSION.SDK_INT;
        Parcel obtain = Parcel.obtain();
        zzauq.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzKl().zzMa().log("User property too long for local database. Sending directly to service");
        return false;
    }

    public boolean zzc(zzatg zzatg) {
        int i = Build.VERSION.SDK_INT;
        byte[] zza2 = zzKh().zza((Parcelable) zzatg);
        if (zza2.length <= 131072) {
            return zza(2, zza2);
        }
        zzKl().zzMa().log("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:53|54|55|56) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:69|70|71|72) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:39|40|41|42|136) */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        zzKl().zzLY().log("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r12.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        zzKl().zzLY().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r12.recycle();
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        zzKl().zzLY().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r12.recycle();
        r13 = null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x009e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00cf */
    /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0103 */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0184 A[Catch:{ all -> 0x01c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x018b A[ADDED_TO_REGION, Catch:{ all -> 0x01c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01c0 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01c0 A[SYNTHETIC] */
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.common.internal.safeparcel.zza> zzlD(int r22) {
        /*
            r21 = this;
            r1 = r21
            r21.zzmR()
            r21.zzJW()
            int r2 = android.os.Build.VERSION.SDK_INT
            boolean r2 = r1.zzbsy
            r3 = 0
            if (r2 == 0) goto L_0x0010
            return r3
        L_0x0010:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            boolean r4 = r21.zzLM()
            if (r4 != 0) goto L_0x001c
            return r2
        L_0x001c:
            r4 = 5
            r5 = 0
            r7 = r4
            r6 = r5
        L_0x0020:
            if (r6 >= r4) goto L_0x01cf
            r8 = 1
            android.database.sqlite.SQLiteDatabase r15 = r21.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x01ab, SQLiteException -> 0x0177, all -> 0x0173 }
            if (r15 != 0) goto L_0x0039
            r1.zzbsy = r8     // Catch:{ SQLiteFullException -> 0x0035, SQLiteException -> 0x0031 }
            if (r15 == 0) goto L_0x0030
            r15.close()
        L_0x0030:
            return r3
        L_0x0031:
            r0 = move-exception
        L_0x0032:
            r3 = r0
            goto L_0x017a
        L_0x0035:
            r0 = move-exception
        L_0x0036:
            r3 = r0
            goto L_0x01ae
        L_0x0039:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x016f, SQLiteException -> 0x016b, all -> 0x0166 }
            java.lang.String r10 = "messages"
            r9 = 3
            java.lang.String[] r11 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x016f, SQLiteException -> 0x016b, all -> 0x0166 }
            java.lang.String r9 = "rowid"
            r11[r5] = r9     // Catch:{ SQLiteFullException -> 0x016f, SQLiteException -> 0x016b, all -> 0x0166 }
            java.lang.String r9 = "type"
            r11[r8] = r9     // Catch:{ SQLiteFullException -> 0x016f, SQLiteException -> 0x016b, all -> 0x0166 }
            java.lang.String r9 = "entry"
            r14 = 2
            r11[r14] = r9     // Catch:{ SQLiteFullException -> 0x016f, SQLiteException -> 0x016b, all -> 0x0166 }
            r12 = 0
            r13 = 0
            r16 = 0
            r17 = 0
            java.lang.String r18 = "rowid asc"
            java.lang.String r19 = java.lang.Integer.toString(r22)     // Catch:{ SQLiteFullException -> 0x016f, SQLiteException -> 0x016b, all -> 0x0166 }
            r9 = r15
            r4 = r14
            r14 = r16
            r3 = r15
            r15 = r17
            r16 = r18
            r17 = r19
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            r10 = -1
        L_0x006b:
            boolean r12 = r9.moveToNext()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            if (r12 == 0) goto L_0x012a
            long r10 = r9.getLong(r5)     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            int r12 = r9.getInt(r8)     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            byte[] r13 = r9.getBlob(r4)     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            if (r12 != 0) goto L_0x00b3
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            int r14 = r13.length     // Catch:{ zza -> 0x009e }
            r12.unmarshall(r13, r5, r14)     // Catch:{ zza -> 0x009e }
            r12.setDataPosition(r5)     // Catch:{ zza -> 0x009e }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r13 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ zza -> 0x009e }
            java.lang.Object r13 = r13.createFromParcel(r12)     // Catch:{ zza -> 0x009e }
            com.google.android.gms.internal.zzatq r13 = (com.google.android.gms.internal.zzatq) r13     // Catch:{ zza -> 0x009e }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            if (r13 == 0) goto L_0x006b
        L_0x0097:
            r2.add(r13)     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            goto L_0x006b
        L_0x009b:
            r0 = move-exception
            r4 = r0
            goto L_0x00af
        L_0x009e:
            com.google.android.gms.internal.zzatx r13 = r21.zzKl()     // Catch:{ all -> 0x009b }
            com.google.android.gms.internal.zzatx$zza r13 = r13.zzLY()     // Catch:{ all -> 0x009b }
            java.lang.String r14 = "Failed to load event from local database"
            r13.log(r14)     // Catch:{ all -> 0x009b }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            goto L_0x006b
        L_0x00af:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            throw r4     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
        L_0x00b3:
            if (r12 != r8) goto L_0x00e7
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            int r14 = r13.length     // Catch:{ zza -> 0x00cf }
            r12.unmarshall(r13, r5, r14)     // Catch:{ zza -> 0x00cf }
            r12.setDataPosition(r5)     // Catch:{ zza -> 0x00cf }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzauq> r13 = com.google.android.gms.internal.zzauq.CREATOR     // Catch:{ zza -> 0x00cf }
            java.lang.Object r13 = r13.createFromParcel(r12)     // Catch:{ zza -> 0x00cf }
            com.google.android.gms.internal.zzauq r13 = (com.google.android.gms.internal.zzauq) r13     // Catch:{ zza -> 0x00cf }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            goto L_0x00e0
        L_0x00cc:
            r0 = move-exception
            r4 = r0
            goto L_0x00e3
        L_0x00cf:
            com.google.android.gms.internal.zzatx r13 = r21.zzKl()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.internal.zzatx$zza r13 = r13.zzLY()     // Catch:{ all -> 0x00cc }
            java.lang.String r14 = "Failed to load user property from local database"
            r13.log(r14)     // Catch:{ all -> 0x00cc }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            r13 = 0
        L_0x00e0:
            if (r13 == 0) goto L_0x006b
            goto L_0x0097
        L_0x00e3:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            throw r4     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
        L_0x00e7:
            if (r12 != r4) goto L_0x011b
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            int r14 = r13.length     // Catch:{ zza -> 0x0103 }
            r12.unmarshall(r13, r5, r14)     // Catch:{ zza -> 0x0103 }
            r12.setDataPosition(r5)     // Catch:{ zza -> 0x0103 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatg> r13 = com.google.android.gms.internal.zzatg.CREATOR     // Catch:{ zza -> 0x0103 }
            java.lang.Object r13 = r13.createFromParcel(r12)     // Catch:{ zza -> 0x0103 }
            com.google.android.gms.internal.zzatg r13 = (com.google.android.gms.internal.zzatg) r13     // Catch:{ zza -> 0x0103 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            goto L_0x0114
        L_0x0100:
            r0 = move-exception
            r4 = r0
            goto L_0x0117
        L_0x0103:
            com.google.android.gms.internal.zzatx r13 = r21.zzKl()     // Catch:{ all -> 0x0100 }
            com.google.android.gms.internal.zzatx$zza r13 = r13.zzLY()     // Catch:{ all -> 0x0100 }
            java.lang.String r14 = "Failed to load user property from local database"
            r13.log(r14)     // Catch:{ all -> 0x0100 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            r13 = 0
        L_0x0114:
            if (r13 == 0) goto L_0x006b
            goto L_0x0097
        L_0x0117:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            throw r4     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
        L_0x011b:
            com.google.android.gms.internal.zzatx r12 = r21.zzKl()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            com.google.android.gms.internal.zzatx$zza r12 = r12.zzLY()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            java.lang.String r13 = "Unknown record type in local database"
            r12.log(r13)     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            goto L_0x006b
        L_0x012a:
            r9.close()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            java.lang.String r4 = "messages"
            java.lang.String r9 = "rowid <= ?"
            java.lang.String[] r12 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            java.lang.String r10 = java.lang.Long.toString(r10)     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            r12[r5] = r10     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            int r4 = r3.delete(r4, r9, r12)     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            int r9 = r2.size()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            if (r4 >= r9) goto L_0x0150
            com.google.android.gms.internal.zzatx r4 = r21.zzKl()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            java.lang.String r9 = "Fewer entries removed from local database than expected"
            r4.log(r9)     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
        L_0x0150:
            r3.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            r3.endTransaction()     // Catch:{ SQLiteFullException -> 0x0162, SQLiteException -> 0x015e, all -> 0x015c }
            if (r3 == 0) goto L_0x015b
            r3.close()
        L_0x015b:
            return r2
        L_0x015c:
            r0 = move-exception
            goto L_0x0168
        L_0x015e:
            r0 = move-exception
            r15 = r3
            goto L_0x0032
        L_0x0162:
            r0 = move-exception
            r15 = r3
            goto L_0x0036
        L_0x0166:
            r0 = move-exception
            r3 = r15
        L_0x0168:
            r2 = r0
            goto L_0x01c9
        L_0x016b:
            r0 = move-exception
            r3 = r15
            goto L_0x0032
        L_0x016f:
            r0 = move-exception
            r3 = r15
            goto L_0x0036
        L_0x0173:
            r0 = move-exception
            r2 = r0
            r3 = 0
            goto L_0x01c9
        L_0x0177:
            r0 = move-exception
            r3 = r0
            r15 = 0
        L_0x017a:
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01c6 }
            r9 = 11
            if (r4 < r9) goto L_0x018b
            boolean r4 = r3 instanceof android.database.sqlite.SQLiteDatabaseLockedException     // Catch:{ all -> 0x01c6 }
            if (r4 == 0) goto L_0x018b
            long r3 = (long) r7     // Catch:{ all -> 0x01c6 }
            android.os.SystemClock.sleep(r3)     // Catch:{ all -> 0x01c6 }
            int r7 = r7 + 20
            goto L_0x01a5
        L_0x018b:
            if (r15 == 0) goto L_0x0196
            boolean r4 = r15.inTransaction()     // Catch:{ all -> 0x01c6 }
            if (r4 == 0) goto L_0x0196
            r15.endTransaction()     // Catch:{ all -> 0x01c6 }
        L_0x0196:
            com.google.android.gms.internal.zzatx r4 = r21.zzKl()     // Catch:{ all -> 0x01c6 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x01c6 }
            java.lang.String r9 = "Error reading entries from local database"
            r4.zzj(r9, r3)     // Catch:{ all -> 0x01c6 }
            r1.zzbsy = r8     // Catch:{ all -> 0x01c6 }
        L_0x01a5:
            if (r15 == 0) goto L_0x01c0
        L_0x01a7:
            r15.close()
            goto L_0x01c0
        L_0x01ab:
            r0 = move-exception
            r3 = r0
            r15 = 0
        L_0x01ae:
            com.google.android.gms.internal.zzatx r4 = r21.zzKl()     // Catch:{ all -> 0x01c6 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x01c6 }
            java.lang.String r9 = "Error reading entries from local database"
            r4.zzj(r9, r3)     // Catch:{ all -> 0x01c6 }
            r1.zzbsy = r8     // Catch:{ all -> 0x01c6 }
            if (r15 == 0) goto L_0x01c0
            goto L_0x01a7
        L_0x01c0:
            int r6 = r6 + 1
            r3 = 0
            r4 = 5
            goto L_0x0020
        L_0x01c6:
            r0 = move-exception
            r2 = r0
            r3 = r15
        L_0x01c9:
            if (r3 == 0) goto L_0x01ce
            r3.close()
        L_0x01ce:
            throw r2
        L_0x01cf:
            com.google.android.gms.internal.zzatx r2 = r21.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMa()
            java.lang.String r3 = "Failed to read events from database in reasonable time"
            r2.log(r3)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatv.zzlD(int):java.util.List");
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }

    /* access modifiers changed from: package-private */
    public String zzow() {
        return zzKn().zzLf();
    }
}
