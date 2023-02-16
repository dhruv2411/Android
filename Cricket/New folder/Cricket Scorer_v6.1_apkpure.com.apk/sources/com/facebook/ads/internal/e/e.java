package com.facebook.ads.internal.e;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class e extends SQLiteOpenHelper {
    private final d a;

    public e(Context context, d dVar) {
        super(context, "ads.db", (SQLiteDatabase.CursorFactory) null, 4);
        if (dVar == null) {
            throw new IllegalArgumentException("AdDatabaseHelper can not be null");
        }
        this.a = dVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        for (g a2 : this.a.c()) {
            a2.a(sQLiteDatabase);
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (g gVar : this.a.c()) {
            gVar.b(sQLiteDatabase);
            gVar.a(sQLiteDatabase);
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        if (!sQLiteDatabase.isReadOnly()) {
            sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 2 && i2 >= 3) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS crashes");
        }
        if (i <= 3 && i2 >= 4) {
            b bVar = c.i;
            sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN " + bVar.b + " " + bVar.c + " DEFAULT 0");
        }
    }
}
