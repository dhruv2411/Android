package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzay;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzbd {
    private static final String TAG = "zzbd";
    protected static final Object zzqC = new Object();
    private static zze zzqE;
    protected static final Object zzqy = new Object();
    private volatile boolean zzpU = false;
    private GoogleApiClient zzqA = null;
    protected boolean zzqB = false;
    protected boolean zzqD = false;
    protected boolean zzqF = false;
    private Map<Pair<String, String>, zzbz> zzqG;
    protected Context zzqn;
    protected Context zzqo;
    private ExecutorService zzqp;
    private DexClassLoader zzqq;
    private zzay zzqr;
    private byte[] zzqs;
    private volatile AdvertisingIdClient zzqt = null;
    private Future zzqu = null;
    private volatile zzag.zza zzqv = null;
    private Future zzqw = null;
    /* access modifiers changed from: private */
    public volatile boolean zzqx = false;
    private zzaq zzqz;

    private zzbd(Context context) {
        this.zzqn = context;
        this.zzqo = context.getApplicationContext();
        this.zzqG = new HashMap();
    }

    public static zzbd zza(Context context, String str, String str2, boolean z) {
        zzbd zzbd = new zzbd(context);
        try {
            zzbd.zzb(str, str2, z);
            return zzbd;
        } catch (zzba unused) {
            return null;
        }
    }

    @NonNull
    private File zza(String str, File file, String str2) throws zzay.zza, IOException {
        File file2 = new File(String.format("%s/%s.jar", new Object[]{file, str2}));
        if (!file2.exists()) {
            byte[] zzc = this.zzqr.zzc(this.zzqs, str);
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(zzc, 0, zzc.length);
            fileOutputStream.close();
        }
        return file2;
    }

    private void zza(File file) {
        if (!file.exists()) {
            Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
            return;
        }
        file.delete();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:20|21|22|23|24|25|26|27|55|57) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0091 */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a0 A[SYNTHETIC, Splitter:B:36:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a5 A[SYNTHETIC, Splitter:B:40:0x00a5] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00af A[SYNTHETIC, Splitter:B:49:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b4 A[SYNTHETIC, Splitter:B:53:0x00b4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zza(java.io.File r8, java.lang.String r9) {
        /*
            r7 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "%s/%s.tmp"
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r4 = 0
            r3[r4] = r8
            r5 = 1
            r3[r5] = r9
            java.lang.String r1 = java.lang.String.format(r1, r3)
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x001b
            return
        L_0x001b:
            java.io.File r1 = new java.io.File
            java.lang.String r3 = "%s/%s.dex"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r4] = r8
            r2[r5] = r9
            java.lang.String r8 = java.lang.String.format(r3, r2)
            r1.<init>(r8)
            boolean r8 = r1.exists()
            if (r8 != 0) goto L_0x0033
            return
        L_0x0033:
            long r2 = r1.length()
            r5 = 0
            int r8 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r8 > 0) goto L_0x003e
            return
        L_0x003e:
            int r8 = (int) r2
            byte[] r8 = new byte[r8]
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ac, all -> 0x009c }
            r3.<init>(r1)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ac, all -> 0x009c }
            int r5 = r3.read(r8)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            if (r5 > 0) goto L_0x0054
            r3.close()     // Catch:{ IOException -> 0x0050 }
        L_0x0050:
            r7.zza(r1)
            return
        L_0x0054:
            com.google.android.gms.internal.zzag$zzd r5 = new com.google.android.gms.internal.zzag$zzd     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            r5.<init>()     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            byte[] r6 = r6.getBytes()     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            r5.zzcx = r6     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            byte[] r9 = r9.getBytes()     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            r5.zzcw = r9     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            com.google.android.gms.internal.zzay r9 = r7.zzqr     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            byte[] r6 = r7.zzqs     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            java.lang.String r8 = r9.zzd(r6, r8)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            byte[] r8 = r8.getBytes()     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            r5.data = r8     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            byte[] r8 = com.google.android.gms.internal.zzao.zzh(r8)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            r5.zzcv = r8     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            r0.createNewFile()     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            r8.<init>(r0)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ad, all -> 0x009a }
            byte[] r9 = com.google.android.gms.internal.zzbxt.zzf(r5)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x0098, all -> 0x0095 }
            int r0 = r9.length     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x0098, all -> 0x0095 }
            r8.write(r9, r4, r0)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x0098, all -> 0x0095 }
            r8.close()     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x0098, all -> 0x0095 }
            r3.close()     // Catch:{ IOException -> 0x0091 }
        L_0x0091:
            r8.close()     // Catch:{ IOException -> 0x00b7 }
            goto L_0x00b7
        L_0x0095:
            r9 = move-exception
            r2 = r8
            goto L_0x009e
        L_0x0098:
            r2 = r8
            goto L_0x00ad
        L_0x009a:
            r9 = move-exception
            goto L_0x009e
        L_0x009c:
            r9 = move-exception
            r3 = r2
        L_0x009e:
            if (r3 == 0) goto L_0x00a3
            r3.close()     // Catch:{ IOException -> 0x00a3 }
        L_0x00a3:
            if (r2 == 0) goto L_0x00a8
            r2.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x00a8:
            r7.zza(r1)
            throw r9
        L_0x00ac:
            r3 = r2
        L_0x00ad:
            if (r3 == 0) goto L_0x00b2
            r3.close()     // Catch:{ IOException -> 0x00b2 }
        L_0x00b2:
            if (r2 == 0) goto L_0x00b7
            r2.close()     // Catch:{ IOException -> 0x00b7 }
        L_0x00b7:
            r7.zza(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbd.zza(java.io.File, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void zzaT() {
        try {
            if (this.zzqt == null && this.zzqo != null) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzqo);
                advertisingIdClient.start();
                this.zzqt = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzqt = null;
        }
    }

    private void zzaU() {
        if (zzgd.zzDV.get().booleanValue()) {
            zzaV();
        }
    }

    /* access modifiers changed from: private */
    public void zzaW() {
        if (this.zzqD) {
            try {
                this.zzqv = zzaqg.zzq(this.zzqn, this.zzqn.getPackageName(), Integer.toString(this.zzqn.getPackageManager().getPackageInfo(this.zzqn.getPackageName(), 0).versionCode));
            } catch (Throwable unused) {
            }
        }
    }

    private void zzaX() {
        this.zzqp.execute(new Runnable() {
            public void run() {
                zzgd.initialize(zzbd.this.zzqn);
            }
        });
        try {
            zzqE = zze.zzuY();
            boolean z = false;
            this.zzqB = zzqE.zzaC(this.zzqn) > 0;
            if (zzqE.isGooglePlayServicesAvailable(this.zzqn) == 0) {
                z = true;
            }
            this.zzqD = z;
            if (this.zzqn.getApplicationContext() != null) {
                this.zzqA = new GoogleApiClient.Builder(this.zzqn).addApi(zzzk.API).build();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00aa */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c0 A[SYNTHETIC, Splitter:B:52:0x00c0] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c5 A[SYNTHETIC, Splitter:B:56:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00cc A[SYNTHETIC, Splitter:B:64:0x00cc] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d1 A[SYNTHETIC, Splitter:B:68:0x00d1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean zzb(java.io.File r10, java.lang.String r11) {
        /*
            r9 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "%s/%s.tmp"
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r4 = 0
            r3[r4] = r10
            r5 = 1
            r3[r5] = r11
            java.lang.String r1 = java.lang.String.format(r1, r3)
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x001b
            return r4
        L_0x001b:
            java.io.File r1 = new java.io.File
            java.lang.String r3 = "%s/%s.dex"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r4] = r10
            r2[r5] = r11
            java.lang.String r10 = java.lang.String.format(r3, r2)
            r1.<init>(r10)
            boolean r10 = r1.exists()
            if (r10 == 0) goto L_0x0033
            return r4
        L_0x0033:
            r10 = 0
            long r2 = r0.length()     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00c9, all -> 0x00bc }
            r6 = 0
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x0042
            r9.zza(r0)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00c9, all -> 0x00bc }
            return r4
        L_0x0042:
            int r2 = (int) r2     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00c9, all -> 0x00bc }
            byte[] r2 = new byte[r2]     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00c9, all -> 0x00bc }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00c9, all -> 0x00bc }
            r3.<init>(r0)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00c9, all -> 0x00bc }
            int r6 = r3.read(r2)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            if (r6 > 0) goto L_0x005e
            java.lang.String r11 = TAG     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            java.lang.String r1 = "Cannot read the cache data."
            android.util.Log.d(r11, r1)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            r9.zza(r0)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            r3.close()     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            return r4
        L_0x005e:
            com.google.android.gms.internal.zzag$zzd r2 = com.google.android.gms.internal.zzag.zzd.zze(r2)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            java.lang.String r6 = new java.lang.String     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            byte[] r7 = r2.zzcw     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            r6.<init>(r7)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            boolean r11 = r11.equals(r6)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            if (r11 == 0) goto L_0x00b3
            byte[] r11 = r2.zzcv     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            byte[] r6 = r2.data     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            byte[] r6 = com.google.android.gms.internal.zzao.zzh(r6)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            boolean r11 = java.util.Arrays.equals(r11, r6)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            if (r11 == 0) goto L_0x00b3
            byte[] r11 = r2.zzcx     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            byte[] r6 = r6.getBytes()     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            boolean r11 = java.util.Arrays.equals(r11, r6)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            if (r11 != 0) goto L_0x008c
            goto L_0x00b3
        L_0x008c:
            com.google.android.gms.internal.zzay r11 = r9.zzqr     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            byte[] r0 = r9.zzqs     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            java.lang.String r6 = new java.lang.String     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            byte[] r2 = r2.data     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            r6.<init>(r2)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            byte[] r11 = r11.zzc(r0, r6)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            r1.createNewFile()     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            r0.<init>(r1)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            int r10 = r11.length     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00b1, all -> 0x00ae }
            r0.write(r11, r4, r10)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00b1, all -> 0x00ae }
            r3.close()     // Catch:{ IOException -> 0x00aa }
        L_0x00aa:
            r0.close()     // Catch:{ IOException -> 0x00ad }
        L_0x00ad:
            return r5
        L_0x00ae:
            r11 = move-exception
            r10 = r0
            goto L_0x00be
        L_0x00b1:
            r10 = r0
            goto L_0x00ca
        L_0x00b3:
            r9.zza(r0)     // Catch:{ zza | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00ba }
            r3.close()     // Catch:{ IOException -> 0x00b9 }
        L_0x00b9:
            return r4
        L_0x00ba:
            r11 = move-exception
            goto L_0x00be
        L_0x00bc:
            r11 = move-exception
            r3 = r10
        L_0x00be:
            if (r3 == 0) goto L_0x00c3
            r3.close()     // Catch:{ IOException -> 0x00c3 }
        L_0x00c3:
            if (r10 == 0) goto L_0x00c8
            r10.close()     // Catch:{ IOException -> 0x00c8 }
        L_0x00c8:
            throw r11
        L_0x00c9:
            r3 = r10
        L_0x00ca:
            if (r3 == 0) goto L_0x00cf
            r3.close()     // Catch:{ IOException -> 0x00cf }
        L_0x00cf:
            if (r10 == 0) goto L_0x00d4
            r10.close()     // Catch:{ IOException -> 0x00d4 }
        L_0x00d4:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbd.zzb(java.io.File, java.lang.String):boolean");
    }

    private boolean zzb(String str, String str2, boolean z) throws zzba {
        this.zzqp = Executors.newCachedThreadPool();
        zzc(z);
        zzaX();
        zzaU();
        if (!zzbf.zzbc() || !zzgd.zzDT.get().booleanValue()) {
            zzo(str);
            zzp(str2);
            this.zzqz = new zzaq(this);
            return true;
        }
        throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
    }

    private void zzc(boolean z) {
        this.zzpU = z;
        if (z) {
            this.zzqu = this.zzqp.submit(new Runnable() {
                public void run() {
                    zzbd.this.zzaT();
                }
            });
        }
    }

    private void zzo(String str) throws zzba {
        this.zzqr = new zzay((SecureRandom) null);
        try {
            this.zzqs = this.zzqr.zzn(str);
        } catch (zzay.zza e) {
            throw new zzba(e);
        }
    }

    private boolean zzp(String str) throws zzba {
        File cacheDir;
        String zzU;
        File zza;
        try {
            cacheDir = this.zzqn.getCacheDir();
            if (cacheDir == null && (cacheDir = this.zzqn.getDir("dex", 0)) == null) {
                throw new zzba();
            }
            zzU = zzaz.zzU();
            zza = zza(str, cacheDir, zzU);
            zzb(cacheDir, zzU);
            this.zzqq = new DexClassLoader(zza.getAbsolutePath(), cacheDir.getAbsolutePath(), (String) null, this.zzqn.getClassLoader());
            zza(zza);
            zza(cacheDir, zzU);
            zzq(String.format("%s/%s.dex", new Object[]{cacheDir, zzU}));
            return true;
        } catch (FileNotFoundException e) {
            throw new zzba(e);
        } catch (IOException e2) {
            throw new zzba(e2);
        } catch (zzay.zza e3) {
            throw new zzba(e3);
        } catch (NullPointerException e4) {
            throw new zzba(e4);
        } catch (Throwable th) {
            zza(zza);
            zza(cacheDir, zzU);
            zzq(String.format("%s/%s.dex", new Object[]{cacheDir, zzU}));
            throw th;
        }
    }

    private void zzq(String str) {
        zza(new File(str));
    }

    public Context getApplicationContext() {
        return this.zzqo;
    }

    public Context getContext() {
        return this.zzqn;
    }

    public int zzQ() {
        zzaq zzaP = zzaP();
        if (zzaP != null) {
            return zzaP.zzQ();
        }
        return Integer.MIN_VALUE;
    }

    public boolean zza(String str, String str2, List<Class> list) {
        if (this.zzqG.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzqG.put(new Pair(str, str2), new zzbz(this, str, str2, list));
        return true;
    }

    public ExecutorService zzaI() {
        return this.zzqp;
    }

    public DexClassLoader zzaJ() {
        return this.zzqq;
    }

    public zzay zzaK() {
        return this.zzqr;
    }

    public byte[] zzaL() {
        return this.zzqs;
    }

    public GoogleApiClient zzaM() {
        return this.zzqA;
    }

    public boolean zzaN() {
        return this.zzqB;
    }

    public boolean zzaO() {
        return this.zzqF;
    }

    public zzaq zzaP() {
        return this.zzqz;
    }

    public boolean zzaQ() {
        return this.zzqD;
    }

    public zzag.zza zzaR() {
        return this.zzqv;
    }

    public Future zzaS() {
        return this.zzqw;
    }

    public void zzaV() {
        synchronized (zzqy) {
            if (!this.zzqx) {
                this.zzqw = this.zzqp.submit(new Runnable() {
                    public void run() {
                        zzbd.this.zzaW();
                        synchronized (zzbd.zzqy) {
                            boolean unused = zzbd.this.zzqx = false;
                        }
                    }
                });
                this.zzqx = true;
            }
        }
    }

    public AdvertisingIdClient zzaY() {
        if (!this.zzpU) {
            return null;
        }
        if (this.zzqt != null) {
            return this.zzqt;
        }
        if (this.zzqu != null) {
            try {
                this.zzqu.get(2000, TimeUnit.MILLISECONDS);
                this.zzqu = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.zzqu.cancel(true);
            }
        }
        return this.zzqt;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzaZ() {
        /*
            r2 = this;
            java.lang.Object r0 = zzqC     // Catch:{ Throwable -> 0x0022 }
            monitor-enter(r0)     // Catch:{ Throwable -> 0x0022 }
            boolean r1 = r2.zzqF     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            return
        L_0x0009:
            boolean r1 = r2.zzqD     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x001a
            com.google.android.gms.common.api.GoogleApiClient r1 = r2.zzqA     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x001a
            com.google.android.gms.common.api.GoogleApiClient r1 = r2.zzqA     // Catch:{ all -> 0x001f }
            r1.connect()     // Catch:{ all -> 0x001f }
            r1 = 1
            r2.zzqF = r1     // Catch:{ all -> 0x001f }
            goto L_0x001d
        L_0x001a:
            r1 = 0
            r2.zzqF = r1     // Catch:{ all -> 0x001f }
        L_0x001d:
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            return
        L_0x001f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            throw r1     // Catch:{ Throwable -> 0x0022 }
        L_0x0022:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbd.zzaZ():void");
    }

    public void zzba() {
        synchronized (zzqC) {
            if (this.zzqF && this.zzqA != null) {
                this.zzqA.disconnect();
                this.zzqF = false;
            }
        }
    }

    public Method zzc(String str, String str2) {
        zzbz zzbz = this.zzqG.get(new Pair(str, str2));
        if (zzbz == null) {
            return null;
        }
        return zzbz.zzbm();
    }
}
