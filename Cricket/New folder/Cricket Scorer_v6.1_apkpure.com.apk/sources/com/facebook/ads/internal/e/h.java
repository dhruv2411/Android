package com.facebook.ads.internal.e;

import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.WorkerThread;

public class h extends g {
    public static final b a = new b(0, "token_id", "TEXT PRIMARY KEY");
    public static final b b = new b(1, "token", "TEXT");
    public static final b[] c = {a, b};
    private static final String d = "h";
    private static final String e = a("tokens", c);
    private static final String f = a("tokens", c, b);
    private static final String g = ("DELETE FROM tokens WHERE NOT EXISTS (SELECT 1 FROM events WHERE tokens." + a.b + " = " + "events" + "." + c.b.b + ")");

    public h(d dVar) {
        super(dVar);
    }

    public String a() {
        return "tokens";
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L_0x000e
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Invalid token."
            r6.<init>(r0)
            throw r6
        L_0x000e:
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.f()     // Catch:{ all -> 0x0068 }
            java.lang.String r2 = f     // Catch:{ all -> 0x0068 }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ all -> 0x0068 }
            r4 = 0
            r3[r4] = r6     // Catch:{ all -> 0x0068 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ all -> 0x0068 }
            boolean r2 = r1.moveToNext()     // Catch:{ all -> 0x0066 }
            if (r2 == 0) goto L_0x002e
            com.facebook.ads.internal.e.b r2 = a     // Catch:{ all -> 0x0066 }
            int r2 = r2.a     // Catch:{ all -> 0x0066 }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ all -> 0x0066 }
            goto L_0x002f
        L_0x002e:
            r2 = r0
        L_0x002f:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0066 }
            if (r3 != 0) goto L_0x003b
            if (r1 == 0) goto L_0x003a
            r1.close()
        L_0x003a:
            return r2
        L_0x003b:
            java.util.UUID r2 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x0066 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0066 }
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ all -> 0x0066 }
            r4 = 2
            r3.<init>(r4)     // Catch:{ all -> 0x0066 }
            com.facebook.ads.internal.e.b r4 = a     // Catch:{ all -> 0x0066 }
            java.lang.String r4 = r4.b     // Catch:{ all -> 0x0066 }
            r3.put(r4, r2)     // Catch:{ all -> 0x0066 }
            com.facebook.ads.internal.e.b r4 = b     // Catch:{ all -> 0x0066 }
            java.lang.String r4 = r4.b     // Catch:{ all -> 0x0066 }
            r3.put(r4, r6)     // Catch:{ all -> 0x0066 }
            android.database.sqlite.SQLiteDatabase r6 = r5.f()     // Catch:{ all -> 0x0066 }
            java.lang.String r4 = "tokens"
            r6.insertOrThrow(r4, r0, r3)     // Catch:{ all -> 0x0066 }
            if (r1 == 0) goto L_0x0065
            r1.close()
        L_0x0065:
            return r2
        L_0x0066:
            r6 = move-exception
            goto L_0x006a
        L_0x0068:
            r6 = move-exception
            r1 = r0
        L_0x006a:
            if (r1 == 0) goto L_0x006f
            r1.close()
        L_0x006f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.e.h.a(java.lang.String):java.lang.String");
    }

    public b[] b() {
        return c;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public Cursor c() {
        return f().rawQuery(e, (String[]) null);
    }

    @WorkerThread
    public void d() {
        try {
            f().execSQL(g);
        } catch (SQLException unused) {
        }
    }
}
