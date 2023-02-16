package com.facebook.ads.internal.c;

public class c {

    public static class a {
        public String a;
        public String b;
        public boolean c;

        public a(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:21|22|(1:24)|25) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r0 = new com.facebook.ads.internal.c.c.a((java.lang.String) null, (java.lang.String) null, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006f, code lost:
        if (r9 != null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0071, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0074, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0075, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0078, code lost:
        r9.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x006a */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.ads.internal.c.c.a a(android.content.ContentResolver r9) {
        /*
            r0 = 3
            r1 = 0
            r2 = 0
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            java.lang.String r0 = "aid"
            r5[r1] = r0     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r0 = 1
            java.lang.String r3 = "androidid"
            r5[r0] = r3     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r0 = 2
            java.lang.String r3 = "limit_tracking"
            r5[r0] = r3     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            java.lang.String r0 = "content://com.facebook.katana.provider.AttributionIdProvider"
            android.net.Uri r4 = android.net.Uri.parse(r0)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r9
            android.database.Cursor r9 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0069, all -> 0x0066 }
            if (r9 == 0) goto L_0x005b
            boolean r0 = r9.moveToFirst()     // Catch:{ Exception -> 0x006a }
            if (r0 != 0) goto L_0x002a
            goto L_0x005b
        L_0x002a:
            java.lang.String r0 = "aid"
            int r0 = r9.getColumnIndex(r0)     // Catch:{ Exception -> 0x006a }
            java.lang.String r0 = r9.getString(r0)     // Catch:{ Exception -> 0x006a }
            java.lang.String r3 = "androidid"
            int r3 = r9.getColumnIndex(r3)     // Catch:{ Exception -> 0x006a }
            java.lang.String r3 = r9.getString(r3)     // Catch:{ Exception -> 0x006a }
            java.lang.String r4 = "limit_tracking"
            int r4 = r9.getColumnIndex(r4)     // Catch:{ Exception -> 0x006a }
            java.lang.String r4 = r9.getString(r4)     // Catch:{ Exception -> 0x006a }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x006a }
            com.facebook.ads.internal.c.c$a r5 = new com.facebook.ads.internal.c.c$a     // Catch:{ Exception -> 0x006a }
            boolean r4 = r4.booleanValue()     // Catch:{ Exception -> 0x006a }
            r5.<init>(r0, r3, r4)     // Catch:{ Exception -> 0x006a }
            if (r9 == 0) goto L_0x005a
            r9.close()
        L_0x005a:
            return r5
        L_0x005b:
            com.facebook.ads.internal.c.c$a r0 = new com.facebook.ads.internal.c.c$a     // Catch:{ Exception -> 0x006a }
            r0.<init>(r2, r2, r1)     // Catch:{ Exception -> 0x006a }
            if (r9 == 0) goto L_0x0065
            r9.close()
        L_0x0065:
            return r0
        L_0x0066:
            r0 = move-exception
            r9 = r2
            goto L_0x0076
        L_0x0069:
            r9 = r2
        L_0x006a:
            com.facebook.ads.internal.c.c$a r0 = new com.facebook.ads.internal.c.c$a     // Catch:{ all -> 0x0075 }
            r0.<init>(r2, r2, r1)     // Catch:{ all -> 0x0075 }
            if (r9 == 0) goto L_0x0074
            r9.close()
        L_0x0074:
            return r0
        L_0x0075:
            r0 = move-exception
        L_0x0076:
            if (r9 == 0) goto L_0x007b
            r9.close()
        L_0x007b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.c.c.a(android.content.ContentResolver):com.facebook.ads.internal.c.c$a");
    }
}
