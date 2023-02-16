package com.google.android.gms.internal;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzme
public class zziv extends zzis {
    private static final Set<String> zzIJ = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzIK = new DecimalFormat("#,###");
    private File zzIL;
    private boolean zzIM;

    public zziv(zzqw zzqw) {
        super(zzqw);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzpk.zzbh("Context.getCacheDir() returned null");
            return;
        }
        this.zzIL = new File(cacheDir, "admobVideoStreams");
        if (!this.zzIL.isDirectory() && !this.zzIL.mkdirs()) {
            String valueOf = String.valueOf(this.zzIL.getAbsolutePath());
            zzpk.zzbh(valueOf.length() != 0 ? "Could not create preload cache directory at ".concat(valueOf) : new String("Could not create preload cache directory at "));
            this.zzIL = null;
        } else if (!this.zzIL.setReadable(true, false) || !this.zzIL.setExecutable(true, false)) {
            String valueOf2 = String.valueOf(this.zzIL.getAbsolutePath());
            zzpk.zzbh(valueOf2.length() != 0 ? "Could not set cache file permissions at ".concat(valueOf2) : new String("Could not set cache file permissions at "));
            this.zzIL = null;
        }
    }

    private File zzb(File file) {
        return new File(this.zzIL, String.valueOf(file.getName()).concat(".done"));
    }

    private static void zzc(File file) {
        if (file.isFile()) {
            file.setLastModified(System.currentTimeMillis());
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
    }

    public void abort() {
        this.zzIM = true;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0258, code lost:
        if (r3 <= r5) goto L_0x029a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x025a, code lost:
        r1 = "sizeExceeded";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        r3 = java.lang.String.valueOf(java.lang.Integer.toString(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x026a, code lost:
        if (r3.length() == 0) goto L_0x0272;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x026c, code lost:
        r10 = "File too big for full file cache. Size: ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0277, code lost:
        r10 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x027f, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0280, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0281, code lost:
        r15 = r1;
        r3 = r10;
        r2 = r18;
        r10 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0289, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x028a, code lost:
        r15 = r1;
        r2 = r18;
        r10 = r19;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:?, code lost:
        r1.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02a1, code lost:
        if (r2.write(r1) <= 0) goto L_0x02a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02a4, code lost:
        r1.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02b3, code lost:
        if ((r10.currentTimeMillis() - r16) <= (1000 * r13)) goto L_0x02e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02b5, code lost:
        r1 = "downloadTimeout";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
        r2 = java.lang.String.valueOf(java.lang.Long.toString(r13));
        r4 = new java.lang.StringBuilder(29 + java.lang.String.valueOf(r2).length());
        r4.append("Timeout exceeded. Limit: ");
        r4.append(r2);
        r4.append(" sec");
        r10 = r4.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02e7, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02e8, code lost:
        r25 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02ec, code lost:
        if (r7.zzIM == false) goto L_0x02f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02ee, code lost:
        r1 = "externalAbort";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02f7, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02fc, code lost:
        if (r9.tryAcquire() == false) goto L_0x0321;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0302, code lost:
        r22 = r25;
        r23 = r2;
        r26 = r9;
        r9 = r19;
        r19 = r3;
        r20 = r4;
        r24 = r5;
        r25 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:?, code lost:
        zza(r8, r11.getAbsolutePath(), r19, r6, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0321, code lost:
        r23 = r2;
        r20 = r4;
        r24 = r5;
        r26 = r9;
        r9 = r19;
        r22 = r25;
        r19 = r3;
        r25 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0331, code lost:
        r3 = r19;
        r4 = r20;
        r1 = r22;
        r2 = r23;
        r5 = r24;
        r6 = r25;
        r19 = r9;
        r9 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0343, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0344, code lost:
        r9 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0346, code lost:
        r1 = r0;
        r10 = r9;
        r2 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x034c, code lost:
        r9 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0356, code lost:
        if (com.google.android.gms.internal.zzpk.zzak(3) == false) goto L_0x0392;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:?, code lost:
        r1 = zzIK.format((long) r3);
        r4 = new java.lang.StringBuilder((22 + java.lang.String.valueOf(r1).length()) + java.lang.String.valueOf(r28).length());
        r4.append("Preloaded ");
        r4.append(r1);
        r4.append(" bytes from ");
        r4.append(r8);
        com.google.android.gms.internal.zzpk.zzbf(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0390, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:?, code lost:
        r11.setReadable(true, false);
        zzc(r12);
        zza(r8, r11.getAbsolutePath(), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03a3, code lost:
        r2 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:?, code lost:
        zzIJ.remove(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03a8, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03aa, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03ac, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03ae, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03af, code lost:
        r2 = r18;
        r9 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03b4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03b5, code lost:
        r9 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03b6, code lost:
        r2 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03b9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03ba, code lost:
        r9 = r3;
        r2 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03bc, code lost:
        r1 = r0;
        r10 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x03be, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x03c0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x03c1, code lost:
        r2 = r14;
        r1 = r0;
        r3 = null;
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x03c7, code lost:
        if ((r1 instanceof java.lang.RuntimeException) != false) goto L_0x03c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03c9, code lost:
        com.google.android.gms.ads.internal.zzw.zzcQ().zza(r1, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x03d7, code lost:
        if (r7.zzIM == false) goto L_0x03fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x03d9, code lost:
        r4 = new java.lang.StringBuilder(26 + java.lang.String.valueOf(r28).length());
        r4.append("Preload aborted for URL \"");
        r4.append(r8);
        r4.append("\"");
        com.google.android.gms.internal.zzpk.zzbg(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x03fe, code lost:
        r5 = new java.lang.StringBuilder(25 + java.lang.String.valueOf(r28).length());
        r5.append("Preload failed for URL \"");
        r5.append(r8);
        r5.append("\"");
        com.google.android.gms.internal.zzpk.zzc(r5.toString(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x042e, code lost:
        r4 = java.lang.String.valueOf(r11.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x043c, code lost:
        if (r4.length() == 0) goto L_0x0443;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x043e, code lost:
        r1 = "Could not delete partial cache file at ".concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0443, code lost:
        r1 = new java.lang.String("Could not delete partial cache file at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0449, code lost:
        com.google.android.gms.internal.zzpk.zzbh(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x044c, code lost:
        zza(r8, r11.getAbsolutePath(), r15, r3);
        zzIJ.remove(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0459, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c9, code lost:
        r15 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r1 = com.google.android.gms.ads.internal.zzw.zzcZ().zzb(r8, com.google.android.gms.internal.zzgd.zzBC.get().intValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e1, code lost:
        if ((r1 instanceof java.net.HttpURLConnection) == false) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2 = r1.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ec, code lost:
        if (r2 < 400) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r4 = java.lang.String.valueOf(java.lang.Integer.toString(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00fe, code lost:
        if (r4.length() == 0) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0100, code lost:
        r3 = "HTTP request failed. Code: ".concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x010a, code lost:
        r3 = new java.lang.String("HTTP request failed. Code: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r6 = new java.lang.StringBuilder(32 + java.lang.String.valueOf(r28).length());
        r6.append("HTTP status code ");
        r6.append(r2);
        r6.append(" at ");
        r6.append(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0134, code lost:
        throw new java.io.IOException(r6.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0135, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0136, code lost:
        r15 = "badUrl";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0138, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0139, code lost:
        r15 = "badUrl";
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x013b, code lost:
        r2 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x013c, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x013f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0140, code lost:
        r1 = r0;
        r3 = null;
        r2 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        r6 = r1.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0149, code lost:
        if (r6 >= 0) goto L_0x0174;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r2 = java.lang.String.valueOf(r28);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0155, code lost:
        if (r2.length() == 0) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0157, code lost:
        r1 = "Stream cache aborted, missing content-length header at ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x015c, code lost:
        r1 = new java.lang.String("Stream cache aborted, missing content-length header at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0162, code lost:
        com.google.android.gms.internal.zzpk.zzbh(r1);
        zza(r8, r11.getAbsolutePath(), "contentLengthMissing", (java.lang.String) null);
        zzIJ.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0173, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r2 = zzIK.format((long) r6);
        r5 = com.google.android.gms.internal.zzgd.zzBx.get().intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0187, code lost:
        if (r6 <= r5) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        r3 = new java.lang.StringBuilder((33 + java.lang.String.valueOf(r2).length()) + java.lang.String.valueOf(r28).length());
        r3.append("Content length ");
        r3.append(r2);
        r3.append(" exceeds limit at ");
        r3.append(r8);
        com.google.android.gms.internal.zzpk.zzbh(r3.toString());
        r2 = java.lang.String.valueOf(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01c3, code lost:
        if (r2.length() == 0) goto L_0x01ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01c5, code lost:
        r1 = "File too big for full file cache. Size: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01ca, code lost:
        r1 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01d0, code lost:
        zza(r8, r11.getAbsolutePath(), "sizeExceeded", r1);
        zzIJ.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01de, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        r4 = new java.lang.StringBuilder((20 + java.lang.String.valueOf(r2).length()) + java.lang.String.valueOf(r28).length());
        r4.append("Caching ");
        r4.append(r2);
        r4.append(" bytes from ");
        r4.append(r8);
        com.google.android.gms.internal.zzpk.zzbf(r4.toString());
        r4 = java.nio.channels.Channels.newChannel(r1.getInputStream());
        r3 = new java.io.FileOutputStream(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
        r2 = r3.getChannel();
        r1 = java.nio.ByteBuffer.allocate(1048576);
        r10 = com.google.android.gms.ads.internal.zzw.zzcS();
        r16 = r10.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0236, code lost:
        r18 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        r9 = new com.google.android.gms.internal.zzpz(com.google.android.gms.internal.zzgd.zzBB.get().longValue());
        r13 = com.google.android.gms.internal.zzgd.zzBA.get().longValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x024d, code lost:
        r19 = r3;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r20 = r4.read(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0254, code lost:
        if (r20 < 0) goto L_0x034c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0256, code lost:
        r3 = r3 + r20;
     */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x03c9  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x03d9  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x03fe  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x043e  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0443  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zzad(java.lang.String r28) {
        /*
            r27 = this;
            r7 = r27
            r8 = r28
            java.io.File r1 = r7.zzIL
            r9 = 0
            r10 = 0
            if (r1 != 0) goto L_0x0010
            java.lang.String r1 = "noCacheDir"
        L_0x000c:
            r7.zza(r8, r10, r1, r10)
            return r9
        L_0x0010:
            int r1 = r27.zzgs()
            com.google.android.gms.internal.zzfz<java.lang.Integer> r2 = com.google.android.gms.internal.zzgd.zzBw
            java.lang.Object r2 = r2.get()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            if (r1 <= r2) goto L_0x0030
            boolean r1 = r27.zzgt()
            if (r1 != 0) goto L_0x0010
            java.lang.String r1 = "Unable to expire stream cache"
            com.google.android.gms.internal.zzpk.zzbh(r1)
            java.lang.String r1 = "expireFailed"
            goto L_0x000c
        L_0x0030:
            java.lang.String r1 = r27.zzae(r28)
            java.io.File r11 = new java.io.File
            java.io.File r2 = r7.zzIL
            r11.<init>(r2, r1)
            java.io.File r12 = r7.zzb(r11)
            boolean r1 = r11.isFile()
            r13 = 1
            if (r1 == 0) goto L_0x0073
            boolean r1 = r12.isFile()
            if (r1 == 0) goto L_0x0073
            long r1 = r11.length()
            int r1 = (int) r1
            java.lang.String r2 = "Stream cache hit at "
            java.lang.String r3 = java.lang.String.valueOf(r28)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x0062
            java.lang.String r2 = r2.concat(r3)
            goto L_0x0068
        L_0x0062:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            r2 = r3
        L_0x0068:
            com.google.android.gms.internal.zzpk.zzbf(r2)
            java.lang.String r2 = r11.getAbsolutePath()
            r7.zza((java.lang.String) r8, (java.lang.String) r2, (int) r1)
            return r13
        L_0x0073:
            java.io.File r1 = r7.zzIL
            java.lang.String r1 = r1.getAbsolutePath()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = java.lang.String.valueOf(r28)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x008d
            java.lang.String r1 = r1.concat(r2)
            r14 = r1
            goto L_0x0093
        L_0x008d:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            r14 = r2
        L_0x0093:
            java.util.Set<java.lang.String> r1 = zzIJ
            monitor-enter(r1)
            java.util.Set<java.lang.String> r2 = zzIJ     // Catch:{ all -> 0x045a }
            boolean r2 = r2.contains(r14)     // Catch:{ all -> 0x045a }
            if (r2 == 0) goto L_0x00c3
            java.lang.String r2 = "Stream cache already in progress at "
            java.lang.String r3 = java.lang.String.valueOf(r28)     // Catch:{ all -> 0x045a }
            int r4 = r3.length()     // Catch:{ all -> 0x045a }
            if (r4 == 0) goto L_0x00af
            java.lang.String r2 = r2.concat(r3)     // Catch:{ all -> 0x045a }
            goto L_0x00b5
        L_0x00af:
            java.lang.String r3 = new java.lang.String     // Catch:{ all -> 0x045a }
            r3.<init>(r2)     // Catch:{ all -> 0x045a }
            r2 = r3
        L_0x00b5:
            com.google.android.gms.internal.zzpk.zzbh(r2)     // Catch:{ all -> 0x045a }
            java.lang.String r2 = r11.getAbsolutePath()     // Catch:{ all -> 0x045a }
            java.lang.String r3 = "inProgress"
            r7.zza(r8, r2, r3, r10)     // Catch:{ all -> 0x045a }
            monitor-exit(r1)     // Catch:{ all -> 0x045a }
            return r9
        L_0x00c3:
            java.util.Set<java.lang.String> r2 = zzIJ     // Catch:{ all -> 0x045a }
            r2.add(r14)     // Catch:{ all -> 0x045a }
            monitor-exit(r1)     // Catch:{ all -> 0x045a }
            java.lang.String r15 = "error"
            com.google.android.gms.internal.zzqo r1 = com.google.android.gms.ads.internal.zzw.zzcZ()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            com.google.android.gms.internal.zzfz<java.lang.Integer> r2 = com.google.android.gms.internal.zzgd.zzBC     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.lang.Object r2 = r2.get()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            int r2 = r2.intValue()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.net.HttpURLConnection r1 = r1.zzb(r8, r2)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            boolean r2 = r1 instanceof java.net.HttpURLConnection     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            if (r2 == 0) goto L_0x0145
            r2 = r1
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ IOException | RuntimeException -> 0x013f }
            int r2 = r2.getResponseCode()     // Catch:{ IOException | RuntimeException -> 0x013f }
            r3 = 400(0x190, float:5.6E-43)
            if (r2 < r3) goto L_0x0145
            java.lang.String r1 = "badUrl"
            java.lang.String r3 = "HTTP request failed. Code: "
            java.lang.String r4 = java.lang.Integer.toString(r2)     // Catch:{ IOException | RuntimeException -> 0x0138 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException | RuntimeException -> 0x0138 }
            int r5 = r4.length()     // Catch:{ IOException | RuntimeException -> 0x0138 }
            if (r5 == 0) goto L_0x0105
            java.lang.String r3 = r3.concat(r4)     // Catch:{ IOException | RuntimeException -> 0x0138 }
            goto L_0x010b
        L_0x0105:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x0138 }
            r4.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x0138 }
            r3 = r4
        L_0x010b:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x0135 }
            r5 = 32
            java.lang.String r6 = java.lang.String.valueOf(r28)     // Catch:{ IOException | RuntimeException -> 0x0135 }
            int r6 = r6.length()     // Catch:{ IOException | RuntimeException -> 0x0135 }
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x0135 }
            r6.<init>(r5)     // Catch:{ IOException | RuntimeException -> 0x0135 }
            java.lang.String r5 = "HTTP status code "
            r6.append(r5)     // Catch:{ IOException | RuntimeException -> 0x0135 }
            r6.append(r2)     // Catch:{ IOException | RuntimeException -> 0x0135 }
            java.lang.String r2 = " at "
            r6.append(r2)     // Catch:{ IOException | RuntimeException -> 0x0135 }
            r6.append(r8)     // Catch:{ IOException | RuntimeException -> 0x0135 }
            java.lang.String r2 = r6.toString()     // Catch:{ IOException | RuntimeException -> 0x0135 }
            r4.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x0135 }
            throw r4     // Catch:{ IOException | RuntimeException -> 0x0135 }
        L_0x0135:
            r0 = move-exception
            r15 = r1
            goto L_0x013b
        L_0x0138:
            r0 = move-exception
            r15 = r1
            r3 = r10
        L_0x013b:
            r2 = r14
        L_0x013c:
            r1 = r0
            goto L_0x03c5
        L_0x013f:
            r0 = move-exception
            r1 = r0
            r3 = r10
            r2 = r14
            goto L_0x03c5
        L_0x0145:
            int r6 = r1.getContentLength()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            if (r6 >= 0) goto L_0x0174
            java.lang.String r1 = "Stream cache aborted, missing content-length header at "
            java.lang.String r2 = java.lang.String.valueOf(r28)     // Catch:{ IOException | RuntimeException -> 0x013f }
            int r3 = r2.length()     // Catch:{ IOException | RuntimeException -> 0x013f }
            if (r3 == 0) goto L_0x015c
            java.lang.String r1 = r1.concat(r2)     // Catch:{ IOException | RuntimeException -> 0x013f }
            goto L_0x0162
        L_0x015c:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x013f }
            r2.<init>(r1)     // Catch:{ IOException | RuntimeException -> 0x013f }
            r1 = r2
        L_0x0162:
            com.google.android.gms.internal.zzpk.zzbh(r1)     // Catch:{ IOException | RuntimeException -> 0x013f }
            java.lang.String r1 = r11.getAbsolutePath()     // Catch:{ IOException | RuntimeException -> 0x013f }
            java.lang.String r2 = "contentLengthMissing"
            r7.zza(r8, r1, r2, r10)     // Catch:{ IOException | RuntimeException -> 0x013f }
            java.util.Set<java.lang.String> r1 = zzIJ     // Catch:{ IOException | RuntimeException -> 0x013f }
            r1.remove(r14)     // Catch:{ IOException | RuntimeException -> 0x013f }
            return r9
        L_0x0174:
            java.text.DecimalFormat r2 = zzIK     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            long r3 = (long) r6     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.lang.String r2 = r2.format(r3)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            com.google.android.gms.internal.zzfz<java.lang.Integer> r3 = com.google.android.gms.internal.zzgd.zzBx     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.lang.Object r3 = r3.get()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            int r5 = r3.intValue()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            if (r6 <= r5) goto L_0x01df
            r1 = 33
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ IOException | RuntimeException -> 0x013f }
            int r3 = r3.length()     // Catch:{ IOException | RuntimeException -> 0x013f }
            int r1 = r1 + r3
            java.lang.String r3 = java.lang.String.valueOf(r28)     // Catch:{ IOException | RuntimeException -> 0x013f }
            int r3 = r3.length()     // Catch:{ IOException | RuntimeException -> 0x013f }
            int r1 = r1 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x013f }
            r3.<init>(r1)     // Catch:{ IOException | RuntimeException -> 0x013f }
            java.lang.String r1 = "Content length "
            r3.append(r1)     // Catch:{ IOException | RuntimeException -> 0x013f }
            r3.append(r2)     // Catch:{ IOException | RuntimeException -> 0x013f }
            java.lang.String r1 = " exceeds limit at "
            r3.append(r1)     // Catch:{ IOException | RuntimeException -> 0x013f }
            r3.append(r8)     // Catch:{ IOException | RuntimeException -> 0x013f }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException | RuntimeException -> 0x013f }
            com.google.android.gms.internal.zzpk.zzbh(r1)     // Catch:{ IOException | RuntimeException -> 0x013f }
            java.lang.String r1 = "File too big for full file cache. Size: "
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException | RuntimeException -> 0x013f }
            int r3 = r2.length()     // Catch:{ IOException | RuntimeException -> 0x013f }
            if (r3 == 0) goto L_0x01ca
            java.lang.String r1 = r1.concat(r2)     // Catch:{ IOException | RuntimeException -> 0x013f }
            goto L_0x01d0
        L_0x01ca:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x013f }
            r2.<init>(r1)     // Catch:{ IOException | RuntimeException -> 0x013f }
            r1 = r2
        L_0x01d0:
            java.lang.String r2 = r11.getAbsolutePath()     // Catch:{ IOException | RuntimeException -> 0x013f }
            java.lang.String r3 = "sizeExceeded"
            r7.zza(r8, r2, r3, r1)     // Catch:{ IOException | RuntimeException -> 0x013f }
            java.util.Set<java.lang.String> r1 = zzIJ     // Catch:{ IOException | RuntimeException -> 0x013f }
            r1.remove(r14)     // Catch:{ IOException | RuntimeException -> 0x013f }
            return r9
        L_0x01df:
            r3 = 20
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            int r4 = r4.length()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            int r3 = r3 + r4
            java.lang.String r4 = java.lang.String.valueOf(r28)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            int r4 = r4.length()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            r4.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.lang.String r3 = "Caching "
            r4.append(r3)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            r4.append(r2)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.lang.String r2 = " bytes from "
            r4.append(r2)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            r4.append(r8)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.lang.String r2 = r4.toString()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            com.google.android.gms.internal.zzpk.zzbf(r2)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.nio.channels.ReadableByteChannel r4 = java.nio.channels.Channels.newChannel(r1)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            r3.<init>(r11)     // Catch:{ IOException | RuntimeException -> 0x03c0 }
            java.nio.channels.FileChannel r2 = r3.getChannel()     // Catch:{ IOException | RuntimeException -> 0x03b9 }
            r1 = 1048576(0x100000, float:1.469368E-39)
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r1)     // Catch:{ IOException | RuntimeException -> 0x03b9 }
            com.google.android.gms.common.util.zze r10 = com.google.android.gms.ads.internal.zzw.zzcS()     // Catch:{ IOException | RuntimeException -> 0x03b9 }
            long r16 = r10.currentTimeMillis()     // Catch:{ IOException | RuntimeException -> 0x03b9 }
            com.google.android.gms.internal.zzfz<java.lang.Long> r9 = com.google.android.gms.internal.zzgd.zzBB     // Catch:{ IOException | RuntimeException -> 0x03b9 }
            java.lang.Object r9 = r9.get()     // Catch:{ IOException | RuntimeException -> 0x03b9 }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ IOException | RuntimeException -> 0x03b9 }
            r18 = r14
            long r13 = r9.longValue()     // Catch:{ IOException | RuntimeException -> 0x03b4 }
            com.google.android.gms.internal.zzpz r9 = new com.google.android.gms.internal.zzpz     // Catch:{ IOException | RuntimeException -> 0x03b4 }
            r9.<init>(r13)     // Catch:{ IOException | RuntimeException -> 0x03b4 }
            com.google.android.gms.internal.zzfz<java.lang.Long> r13 = com.google.android.gms.internal.zzgd.zzBA     // Catch:{ IOException | RuntimeException -> 0x03b4 }
            java.lang.Object r13 = r13.get()     // Catch:{ IOException | RuntimeException -> 0x03b4 }
            java.lang.Long r13 = (java.lang.Long) r13     // Catch:{ IOException | RuntimeException -> 0x03b4 }
            long r13 = r13.longValue()     // Catch:{ IOException | RuntimeException -> 0x03b4 }
            r19 = r3
            r3 = 0
        L_0x0250:
            int r20 = r4.read(r1)     // Catch:{ IOException | RuntimeException -> 0x03ae }
            if (r20 < 0) goto L_0x034c
            int r3 = r3 + r20
            if (r3 <= r5) goto L_0x029a
            java.lang.String r1 = "sizeExceeded"
            java.lang.String r2 = "File too big for full file cache. Size: "
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            int r4 = r3.length()     // Catch:{ IOException | RuntimeException -> 0x0289 }
            if (r4 == 0) goto L_0x0272
            java.lang.String r2 = r2.concat(r3)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            r10 = r2
            goto L_0x0278
        L_0x0272:
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x0289 }
            r3.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            r10 = r3
        L_0x0278:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x0280 }
            java.lang.String r3 = "stream cache file size limit exceeded"
            r2.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x0280 }
            throw r2     // Catch:{ IOException | RuntimeException -> 0x0280 }
        L_0x0280:
            r0 = move-exception
            r15 = r1
            r3 = r10
            r2 = r18
            r10 = r19
            goto L_0x013c
        L_0x0289:
            r0 = move-exception
            r15 = r1
            r2 = r18
            r10 = r19
            r3 = 0
            goto L_0x013c
        L_0x0292:
            r0 = move-exception
            r1 = r0
            r2 = r18
            r10 = r19
            goto L_0x03be
        L_0x029a:
            r1.flip()     // Catch:{ IOException | RuntimeException -> 0x0343 }
        L_0x029d:
            int r20 = r2.write(r1)     // Catch:{ IOException | RuntimeException -> 0x0343 }
            if (r20 <= 0) goto L_0x02a4
            goto L_0x029d
        L_0x02a4:
            r1.clear()     // Catch:{ IOException | RuntimeException -> 0x0343 }
            long r20 = r10.currentTimeMillis()     // Catch:{ IOException | RuntimeException -> 0x0343 }
            long r22 = r20 - r16
            r20 = 1000(0x3e8, double:4.94E-321)
            long r20 = r20 * r13
            int r24 = (r22 > r20 ? 1 : (r22 == r20 ? 0 : -1))
            if (r24 <= 0) goto L_0x02e8
            java.lang.String r1 = "downloadTimeout"
            java.lang.String r2 = java.lang.Long.toString(r13)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            r3 = 29
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            int r4 = r4.length()     // Catch:{ IOException | RuntimeException -> 0x0289 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x0289 }
            r4.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            java.lang.String r3 = "Timeout exceeded. Limit: "
            r4.append(r3)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            r4.append(r2)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            java.lang.String r2 = " sec"
            r4.append(r2)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            java.lang.String r10 = r4.toString()     // Catch:{ IOException | RuntimeException -> 0x0289 }
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x0280 }
            java.lang.String r3 = "stream cache time limit exceeded"
            r2.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x0280 }
            throw r2     // Catch:{ IOException | RuntimeException -> 0x0280 }
        L_0x02e8:
            r25 = r1
            boolean r1 = r7.zzIM     // Catch:{ IOException | RuntimeException -> 0x0343 }
            if (r1 == 0) goto L_0x02f8
            java.lang.String r1 = "externalAbort"
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x0289 }
            java.lang.String r3 = "abort requested"
            r2.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x0289 }
            throw r2     // Catch:{ IOException | RuntimeException -> 0x0289 }
        L_0x02f8:
            boolean r1 = r9.tryAcquire()     // Catch:{ IOException | RuntimeException -> 0x0343 }
            if (r1 == 0) goto L_0x0321
            java.lang.String r20 = r11.getAbsolutePath()     // Catch:{ IOException | RuntimeException -> 0x0343 }
            r21 = 0
            r22 = r25
            r1 = r7
            r23 = r2
            r2 = r8
            r26 = r9
            r9 = r19
            r19 = r3
            r3 = r20
            r20 = r4
            r4 = r19
            r24 = r5
            r5 = r6
            r25 = r6
            r6 = r21
            r1.zza(r2, r3, r4, r5, r6)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            goto L_0x0331
        L_0x0321:
            r23 = r2
            r20 = r4
            r24 = r5
            r26 = r9
            r9 = r19
            r22 = r25
            r19 = r3
            r25 = r6
        L_0x0331:
            r3 = r19
            r4 = r20
            r1 = r22
            r2 = r23
            r5 = r24
            r6 = r25
            r19 = r9
            r9 = r26
            goto L_0x0250
        L_0x0343:
            r0 = move-exception
            r9 = r19
        L_0x0346:
            r1 = r0
            r10 = r9
            r2 = r18
            goto L_0x03be
        L_0x034c:
            r9 = r19
            r9.close()     // Catch:{ IOException | RuntimeException -> 0x03ac }
            r1 = 3
            boolean r1 = com.google.android.gms.internal.zzpk.zzak(r1)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            if (r1 == 0) goto L_0x0392
            java.text.DecimalFormat r1 = zzIK     // Catch:{ IOException | RuntimeException -> 0x0390 }
            long r4 = (long) r3     // Catch:{ IOException | RuntimeException -> 0x0390 }
            java.lang.String r1 = r1.format(r4)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            r2 = 22
            java.lang.String r4 = java.lang.String.valueOf(r1)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            int r4 = r4.length()     // Catch:{ IOException | RuntimeException -> 0x0390 }
            int r2 = r2 + r4
            java.lang.String r4 = java.lang.String.valueOf(r28)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            int r4 = r4.length()     // Catch:{ IOException | RuntimeException -> 0x0390 }
            int r2 = r2 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x0390 }
            r4.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            java.lang.String r2 = "Preloaded "
            r4.append(r2)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            r4.append(r1)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            java.lang.String r1 = " bytes from "
            r4.append(r1)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            r4.append(r8)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException | RuntimeException -> 0x0390 }
            com.google.android.gms.internal.zzpk.zzbf(r1)     // Catch:{ IOException | RuntimeException -> 0x0390 }
            goto L_0x0392
        L_0x0390:
            r0 = move-exception
            goto L_0x0346
        L_0x0392:
            r1 = 0
            r2 = 1
            r11.setReadable(r2, r1)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            zzc(r12)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            java.lang.String r1 = r11.getAbsolutePath()     // Catch:{ IOException | RuntimeException -> 0x03ac }
            r7.zza((java.lang.String) r8, (java.lang.String) r1, (int) r3)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            java.util.Set<java.lang.String> r1 = zzIJ     // Catch:{ IOException | RuntimeException -> 0x03ac }
            r2 = r18
            r1.remove(r2)     // Catch:{ IOException | RuntimeException -> 0x03aa }
            r1 = 1
            return r1
        L_0x03aa:
            r0 = move-exception
            goto L_0x03bc
        L_0x03ac:
            r0 = move-exception
            goto L_0x03b6
        L_0x03ae:
            r0 = move-exception
            r2 = r18
            r9 = r19
            goto L_0x03bc
        L_0x03b4:
            r0 = move-exception
            r9 = r3
        L_0x03b6:
            r2 = r18
            goto L_0x03bc
        L_0x03b9:
            r0 = move-exception
            r9 = r3
            r2 = r14
        L_0x03bc:
            r1 = r0
            r10 = r9
        L_0x03be:
            r3 = 0
            goto L_0x03c5
        L_0x03c0:
            r0 = move-exception
            r2 = r14
            r1 = r0
            r3 = 0
            r10 = 0
        L_0x03c5:
            boolean r4 = r1 instanceof java.lang.RuntimeException
            if (r4 == 0) goto L_0x03d2
            com.google.android.gms.internal.zzpe r4 = com.google.android.gms.ads.internal.zzw.zzcQ()
            java.lang.String r5 = "VideoStreamFullFileCache.preload"
            r4.zza((java.lang.Throwable) r1, (java.lang.String) r5)
        L_0x03d2:
            r10.close()     // Catch:{ IOException | NullPointerException -> 0x03d5 }
        L_0x03d5:
            boolean r4 = r7.zzIM
            if (r4 == 0) goto L_0x03fe
            r1 = 26
            java.lang.String r4 = java.lang.String.valueOf(r28)
            int r4 = r4.length()
            int r1 = r1 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            java.lang.String r1 = "Preload aborted for URL \""
            r4.append(r1)
            r4.append(r8)
            java.lang.String r1 = "\""
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.google.android.gms.internal.zzpk.zzbg(r1)
            goto L_0x0422
        L_0x03fe:
            r4 = 25
            java.lang.String r5 = java.lang.String.valueOf(r28)
            int r5 = r5.length()
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "Preload failed for URL \""
            r5.append(r4)
            r5.append(r8)
            java.lang.String r4 = "\""
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            com.google.android.gms.internal.zzpk.zzc(r4, r1)
        L_0x0422:
            boolean r1 = r11.exists()
            if (r1 == 0) goto L_0x044c
            boolean r1 = r11.delete()
            if (r1 != 0) goto L_0x044c
            java.lang.String r1 = "Could not delete partial cache file at "
            java.lang.String r4 = r11.getAbsolutePath()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r5 = r4.length()
            if (r5 == 0) goto L_0x0443
            java.lang.String r1 = r1.concat(r4)
            goto L_0x0449
        L_0x0443:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r1)
            r1 = r4
        L_0x0449:
            com.google.android.gms.internal.zzpk.zzbh(r1)
        L_0x044c:
            java.lang.String r1 = r11.getAbsolutePath()
            r7.zza(r8, r1, r15, r3)
            java.util.Set<java.lang.String> r1 = zzIJ
            r1.remove(r2)
            r1 = 0
            return r1
        L_0x045a:
            r0 = move-exception
            r2 = r0
            monitor-exit(r1)     // Catch:{ all -> 0x045a }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zziv.zzad(java.lang.String):boolean");
    }

    public int zzgs() {
        if (this.zzIL == null) {
            return 0;
        }
        int i = 0;
        for (File name : this.zzIL.listFiles()) {
            if (!name.getName().endsWith(".done")) {
                i++;
            }
        }
        return i;
    }

    public boolean zzgt() {
        if (this.zzIL == null) {
            return false;
        }
        long j = Long.MAX_VALUE;
        File file = null;
        for (File file2 : this.zzIL.listFiles()) {
            if (!file2.getName().endsWith(".done")) {
                long lastModified = file2.lastModified();
                if (lastModified < j) {
                    file = file2;
                    j = lastModified;
                }
            }
        }
        if (file == null) {
            return false;
        }
        boolean delete = file.delete();
        File zzb = zzb(file);
        return zzb.isFile() ? delete & zzb.delete() : delete;
    }
}
