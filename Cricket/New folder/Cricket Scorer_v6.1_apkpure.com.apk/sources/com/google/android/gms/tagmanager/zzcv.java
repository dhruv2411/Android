package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.internal.zzaj;
import com.google.android.gms.internal.zzbjd;
import com.google.android.gms.internal.zzbjf;
import com.google.android.gms.internal.zzbxs;
import com.google.android.gms.internal.zzbxt;
import com.google.android.gms.tagmanager.zzp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class zzcv implements zzp.zzf {
    private final Context mContext;
    private final String zzbEU;
    private zzbn<zzbjd.zza> zzbHt;
    private final ExecutorService zzbtI = Executors.newSingleThreadExecutor();

    zzcv(Context context, String str) {
        this.mContext = context;
        this.zzbEU = str;
    }

    private zzbjf.zzc zzM(byte[] bArr) {
        try {
            zzbjf.zzc zzb = zzbjf.zzb(zzaj.zzf.zzf(bArr));
            if (zzb != null) {
                zzbo.v("The container was successfully loaded from the resource (using binary file)");
            }
            return zzb;
        } catch (zzbxs unused) {
            zzbo.e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzbjf.zzg unused2) {
            zzbo.zzbh("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    private zzbjf.zzc zza(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return zzbh.zzhl(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            zzbo.zzbf("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        } catch (JSONException unused2) {
            zzbo.zzbh("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    private void zzd(zzbjd.zza zza) throws IllegalArgumentException {
        if (zza.zzlr == null && zza.zzbNf == null) {
            throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
        }
    }

    public synchronized void release() {
        this.zzbtI.shutdown();
    }

    public void zzQp() {
        this.zzbtI.execute(new Runnable() {
            public void run() {
                zzcv.this.zzRm();
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0063, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3.zzbHt.zza(com.google.android.gms.tagmanager.zzbn.zza.IO_ERROR);
        com.google.android.gms.tagmanager.zzbo.zzbh("Failed to read the resource from disk. The resource is inconsistent");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0091, code lost:
        com.google.android.gms.tagmanager.zzbo.zzbh("Error closing stream for reading resource from disk");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0096, code lost:
        throw r1;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:17:0x0065, B:21:0x0075] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0065 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0075 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0065=Splitter:B:17:0x0065, B:21:0x0075=Splitter:B:21:0x0075} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzRm() {
        /*
            r3 = this;
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzbjd$zza> r0 = r3.zzbHt
            if (r0 != 0) goto L_0x000c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Callback must be set before execute"
            r0.<init>(r1)
            throw r0
        L_0x000c:
            java.lang.String r0 = "Attempting to load resource from disk"
            com.google.android.gms.tagmanager.zzbo.v(r0)
            com.google.android.gms.tagmanager.zzcj r0 = com.google.android.gms.tagmanager.zzcj.zzRe()
            com.google.android.gms.tagmanager.zzcj$zza r0 = r0.zzRf()
            com.google.android.gms.tagmanager.zzcj$zza r1 = com.google.android.gms.tagmanager.zzcj.zza.CONTAINER
            if (r0 == r1) goto L_0x0029
            com.google.android.gms.tagmanager.zzcj r0 = com.google.android.gms.tagmanager.zzcj.zzRe()
            com.google.android.gms.tagmanager.zzcj$zza r0 = r0.zzRf()
            com.google.android.gms.tagmanager.zzcj$zza r1 = com.google.android.gms.tagmanager.zzcj.zza.CONTAINER_DEBUG
            if (r0 != r1) goto L_0x0041
        L_0x0029:
            java.lang.String r0 = r3.zzbEU
            com.google.android.gms.tagmanager.zzcj r1 = com.google.android.gms.tagmanager.zzcj.zzRe()
            java.lang.String r1 = r1.getContainerId()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0041
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzbjd$zza> r0 = r3.zzbHt
            com.google.android.gms.tagmanager.zzbn$zza r1 = com.google.android.gms.tagmanager.zzbn.zza.NOT_AVAILABLE
            r0.zza(r1)
            return
        L_0x0041:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0097 }
            java.io.File r1 = r3.zzRn()     // Catch:{ FileNotFoundException -> 0x0097 }
            r0.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0097 }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0075, IllegalArgumentException -> 0x0065 }
            r1.<init>()     // Catch:{ IOException -> 0x0075, IllegalArgumentException -> 0x0065 }
            com.google.android.gms.internal.zzbjf.zzc(r0, r1)     // Catch:{ IOException -> 0x0075, IllegalArgumentException -> 0x0065 }
            byte[] r1 = r1.toByteArray()     // Catch:{ IOException -> 0x0075, IllegalArgumentException -> 0x0065 }
            com.google.android.gms.internal.zzbjd$zza r1 = com.google.android.gms.internal.zzbjd.zza.zzQ(r1)     // Catch:{ IOException -> 0x0075, IllegalArgumentException -> 0x0065 }
            r3.zzd(r1)     // Catch:{ IOException -> 0x0075, IllegalArgumentException -> 0x0065 }
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzbjd$zza> r2 = r3.zzbHt     // Catch:{ IOException -> 0x0075, IllegalArgumentException -> 0x0065 }
            r2.onSuccess(r1)     // Catch:{ IOException -> 0x0075, IllegalArgumentException -> 0x0065 }
            goto L_0x0071
        L_0x0063:
            r1 = move-exception
            goto L_0x008d
        L_0x0065:
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzbjd$zza> r1 = r3.zzbHt     // Catch:{ all -> 0x0063 }
            com.google.android.gms.tagmanager.zzbn$zza r2 = com.google.android.gms.tagmanager.zzbn.zza.IO_ERROR     // Catch:{ all -> 0x0063 }
            r1.zza(r2)     // Catch:{ all -> 0x0063 }
            java.lang.String r1 = "Failed to read the resource from disk. The resource is inconsistent"
            com.google.android.gms.tagmanager.zzbo.zzbh(r1)     // Catch:{ all -> 0x0063 }
        L_0x0071:
            r0.close()     // Catch:{ IOException -> 0x0082 }
            goto L_0x0087
        L_0x0075:
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzbjd$zza> r1 = r3.zzbHt     // Catch:{ all -> 0x0063 }
            com.google.android.gms.tagmanager.zzbn$zza r2 = com.google.android.gms.tagmanager.zzbn.zza.IO_ERROR     // Catch:{ all -> 0x0063 }
            r1.zza(r2)     // Catch:{ all -> 0x0063 }
            java.lang.String r1 = "Failed to read the resource from disk"
            com.google.android.gms.tagmanager.zzbo.zzbh(r1)     // Catch:{ all -> 0x0063 }
            goto L_0x0071
        L_0x0082:
            java.lang.String r0 = "Error closing stream for reading resource from disk"
            com.google.android.gms.tagmanager.zzbo.zzbh(r0)
        L_0x0087:
            java.lang.String r0 = "The Disk resource was successfully read."
            com.google.android.gms.tagmanager.zzbo.v(r0)
            return
        L_0x008d:
            r0.close()     // Catch:{ IOException -> 0x0091 }
            goto L_0x0096
        L_0x0091:
            java.lang.String r0 = "Error closing stream for reading resource from disk"
            com.google.android.gms.tagmanager.zzbo.zzbh(r0)
        L_0x0096:
            throw r1
        L_0x0097:
            java.lang.String r0 = "Failed to find the resource in the disk"
            com.google.android.gms.tagmanager.zzbo.zzbf(r0)
            com.google.android.gms.tagmanager.zzbn<com.google.android.gms.internal.zzbjd$zza> r0 = r3.zzbHt
            com.google.android.gms.tagmanager.zzbn$zza r1 = com.google.android.gms.tagmanager.zzbn.zza.NOT_AVAILABLE
            r0.zza(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcv.zzRm():void");
    }

    /* access modifiers changed from: package-private */
    public File zzRn() {
        String valueOf = String.valueOf("resource_");
        String valueOf2 = String.valueOf(this.zzbEU);
        return new File(this.mContext.getDir("google_tagmanager", 0), valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public void zza(zzbn<zzbjd.zza> zzbn) {
        this.zzbHt = zzbn;
    }

    public void zzb(final zzbjd.zza zza) {
        this.zzbtI.execute(new Runnable() {
            public void run() {
                zzcv.this.zzc(zza);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean zzc(zzbjd.zza zza) {
        File zzRn = zzRn();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zzRn);
            try {
                fileOutputStream.write(zzbxt.zzf(zza));
                try {
                    fileOutputStream.close();
                    return true;
                } catch (IOException unused) {
                    zzbo.zzbh("error closing stream for writing resource to disk");
                    return true;
                }
            } catch (IOException unused2) {
                zzbo.zzbh("Error writing resource to disk. Removing resource from disk.");
                zzRn.delete();
                try {
                    fileOutputStream.close();
                    return false;
                } catch (IOException unused3) {
                    zzbo.zzbh("error closing stream for writing resource to disk");
                    return false;
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                    zzbo.zzbh("error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException unused5) {
            zzbo.e("Error opening resource file for writing");
            return false;
        }
    }

    public zzbjf.zzc zznz(int i) {
        String sb;
        try {
            InputStream openRawResource = this.mContext.getResources().openRawResource(i);
            String valueOf = String.valueOf(this.mContext.getResources().getResourceName(i));
            StringBuilder sb2 = new StringBuilder(66 + String.valueOf(valueOf).length());
            sb2.append("Attempting to load a container from the resource ID ");
            sb2.append(i);
            sb2.append(" (");
            sb2.append(valueOf);
            sb2.append(")");
            zzbo.v(sb2.toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzbjf.zzc(openRawResource, byteArrayOutputStream);
                zzbjf.zzc zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zzM(byteArrayOutputStream.toByteArray());
                }
                zzbo.v("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException unused) {
                String valueOf2 = String.valueOf(this.mContext.getResources().getResourceName(i));
                StringBuilder sb3 = new StringBuilder(67 + String.valueOf(valueOf2).length());
                sb3.append("Error reading the default container with resource ID ");
                sb3.append(i);
                sb3.append(" (");
                sb3.append(valueOf2);
                sb3.append(")");
                sb = sb3.toString();
                zzbo.zzbh(sb);
                return null;
            }
        } catch (Resources.NotFoundException unused2) {
            StringBuilder sb4 = new StringBuilder(98);
            sb4.append("Failed to load the container. No default container resource found with the resource ID ");
            sb4.append(i);
            sb = sb4.toString();
            zzbo.zzbh(sb);
            return null;
        }
    }
}
