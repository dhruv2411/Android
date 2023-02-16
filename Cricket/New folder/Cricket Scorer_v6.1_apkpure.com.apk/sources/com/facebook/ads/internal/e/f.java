package com.facebook.ads.internal.e;

import android.support.annotation.Nullable;
import com.facebook.ads.AdError;

abstract class f<T> {
    private a a;

    public enum a {
        UNKNOWN(9000, "An unknown error has occurred."),
        DATABASE_SELECT(AdError.MEDIATION_ERROR_CODE, "Failed to read from database."),
        DATABASE_INSERT(3002, "Failed to insert row into database."),
        DATABASE_UPDATE(3003, "Failed to update row in database."),
        DATABASE_DELETE(3004, "Failed to delete row from database.");
        
        private final int f;
        private final String g;

        private a(int i, String str) {
            this.f = i;
            this.g = str;
        }

        public int a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }
    }

    f() {
    }

    /* access modifiers changed from: protected */
    public void a(a aVar) {
        this.a = aVar;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public abstract T b();

    public a c() {
        return this.a;
    }
}
