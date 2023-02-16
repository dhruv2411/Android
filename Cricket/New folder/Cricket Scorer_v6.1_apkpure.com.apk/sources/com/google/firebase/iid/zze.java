package com.google.firebase.iid;

public class zze {
    private static final Object zztX = new Object();
    private final zzh zzclv;

    zze(zzh zzh) {
        this.zzclv = zzh;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        return null;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zzabS() {
        /*
            r5 = this;
            java.lang.Object r0 = zztX
            monitor-enter(r0)
            com.google.firebase.iid.zzh r1 = r5.zzclv     // Catch:{ all -> 0x002a }
            android.content.SharedPreferences r1 = r1.zzabX()     // Catch:{ all -> 0x002a }
            java.lang.String r2 = "topic_operaion_queue"
            r3 = 0
            java.lang.String r1 = r1.getString(r2, r3)     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0028
            java.lang.String r2 = ","
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ all -> 0x002a }
            int r2 = r1.length     // Catch:{ all -> 0x002a }
            r4 = 1
            if (r2 <= r4) goto L_0x0028
            r2 = r1[r4]     // Catch:{ all -> 0x002a }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x002a }
            if (r2 != 0) goto L_0x0028
            r1 = r1[r4]     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return r1
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return r3
        L_0x002a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zze.zzabS():java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public void zzjt(String str) {
        synchronized (zztX) {
            String string = this.zzclv.zzabX().getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            StringBuilder sb = new StringBuilder(String.valueOf(string).length() + String.valueOf(valueOf).length() + String.valueOf(str).length());
            sb.append(string);
            sb.append(valueOf);
            sb.append(str);
            this.zzclv.zzabX().edit().putString("topic_operaion_queue", sb.toString()).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzjx(String str) {
        synchronized (zztX) {
            String string = this.zzclv.zzabX().getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (!string.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                return false;
            }
            String valueOf3 = String.valueOf(",");
            String valueOf4 = String.valueOf(str);
            this.zzclv.zzabX().edit().putString("topic_operaion_queue", string.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length())).apply();
            return true;
        }
    }
}
