package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@zzme
public class zzgf {
    final Context mContext;
    BlockingQueue<zzgl> zzFA;
    ExecutorService zzFB;
    LinkedHashMap<String, String> zzFC = new LinkedHashMap<>();
    Map<String, zzgi> zzFD = new HashMap();
    private AtomicBoolean zzFE;
    private File zzFF;
    String zzFy;
    final String zzwd;

    public zzgf(Context context, String str, String str2, Map<String, String> map) {
        File externalStorageDirectory;
        this.mContext = context;
        this.zzwd = str;
        this.zzFy = str2;
        this.zzFE = new AtomicBoolean(false);
        this.zzFE.set(zzgd.zzCb.get().booleanValue());
        if (this.zzFE.get() && (externalStorageDirectory = Environment.getExternalStorageDirectory()) != null) {
            this.zzFF = new File(externalStorageDirectory, "sdk_csi_data.txt");
        }
        for (Map.Entry next : map.entrySet()) {
            this.zzFC.put((String) next.getKey(), (String) next.getValue());
        }
        this.zzFA = new ArrayBlockingQueue(30);
        this.zzFB = Executors.newSingleThreadExecutor();
        this.zzFB.execute(new Runnable() {
            public void run() {
                zzgf.this.zzfx();
            }
        });
        this.zzFD.put("action", zzgi.zzFI);
        this.zzFD.put("ad_format", zzgi.zzFI);
        this.zzFD.put("e", zzgi.zzFJ);
    }

    private void zzb(Map<String, String> map, String str) {
        String zza = zza(this.zzFy, map, str);
        if (this.zzFE.get()) {
            zzc(this.zzFF, zza);
        } else {
            zzw.zzcM().zzf(this.mContext, this.zzwd, zza);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0029 A[SYNTHETIC, Splitter:B:19:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0036 A[SYNTHETIC, Splitter:B:26:0x0036] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzc(@android.support.annotation.Nullable java.io.File r4, java.lang.String r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0041
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0021 }
            r2 = 1
            r1.<init>(r4, r2)     // Catch:{ IOException -> 0x0021 }
            byte[] r4 = r5.getBytes()     // Catch:{ IOException -> 0x001c, all -> 0x0019 }
            r1.write(r4)     // Catch:{ IOException -> 0x001c, all -> 0x0019 }
            r4 = 10
            r1.write(r4)     // Catch:{ IOException -> 0x001c, all -> 0x0019 }
            r1.close()     // Catch:{ IOException -> 0x002d }
            return
        L_0x0019:
            r4 = move-exception
            r0 = r1
            goto L_0x0034
        L_0x001c:
            r4 = move-exception
            r0 = r1
            goto L_0x0022
        L_0x001f:
            r4 = move-exception
            goto L_0x0034
        L_0x0021:
            r4 = move-exception
        L_0x0022:
            java.lang.String r5 = "CsiReporter: Cannot write to file: sdk_csi_data.txt."
            com.google.android.gms.internal.zzpk.zzc(r5, r4)     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x0046
            r0.close()     // Catch:{ IOException -> 0x002d }
            return
        L_0x002d:
            r4 = move-exception
            java.lang.String r5 = "CsiReporter: Cannot close file: sdk_csi_data.txt."
            com.google.android.gms.internal.zzpk.zzc(r5, r4)
            return
        L_0x0034:
            if (r0 == 0) goto L_0x0040
            r0.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x0040
        L_0x003a:
            r5 = move-exception
            java.lang.String r0 = "CsiReporter: Cannot close file: sdk_csi_data.txt."
            com.google.android.gms.internal.zzpk.zzc(r0, r5)
        L_0x0040:
            throw r4
        L_0x0041:
            java.lang.String r4 = "CsiReporter: File doesn't exists. Cannot write CSI data to file."
            com.google.android.gms.internal.zzpk.zzbh(r4)
        L_0x0046:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgf.zzc(java.io.File, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void zzfx() {
        while (true) {
            try {
                zzgl take = this.zzFA.take();
                String zzfD = take.zzfD();
                if (!TextUtils.isEmpty(zzfD)) {
                    zzb(zza(this.zzFC, take.zzfE()), zzfD);
                }
            } catch (InterruptedException e) {
                zzpk.zzc("CsiReporter:reporter interrupted", e);
                return;
            }
        }
    }

    public zzgi zzV(String str) {
        zzgi zzgi = this.zzFD.get(str);
        return zzgi != null ? zzgi : zzgi.zzFH;
    }

    /* access modifiers changed from: package-private */
    public String zza(String str, Map<String, String> map, @NonNull String str2) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        for (Map.Entry next : map.entrySet()) {
            buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
        }
        return buildUpon.build().toString() + "&" + "it" + "=" + str2;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zza(Map<String, String> map, @Nullable Map<String, String> map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Map.Entry next : map2.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, zzV(str).zzg(str2, (String) next.getValue()));
        }
        return linkedHashMap;
    }

    public boolean zza(zzgl zzgl) {
        return this.zzFA.offer(zzgl);
    }

    public void zzc(@Nullable List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzFC.put("e", TextUtils.join(",", list));
        }
    }
}
