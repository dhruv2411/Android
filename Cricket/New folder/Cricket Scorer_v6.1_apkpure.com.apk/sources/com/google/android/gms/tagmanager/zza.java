package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.io.IOException;

public class zza {
    private static Object zzbEI = new Object();
    private static zza zzbEJ;
    private volatile boolean mClosed;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Thread zzXh;
    private volatile AdvertisingIdClient.Info zzadB;
    private volatile long zzbEC;
    private volatile long zzbED;
    private volatile long zzbEE;
    private volatile long zzbEF;
    private final Object zzbEG;
    private C0150zza zzbEH;
    private final zze zzuP;

    /* renamed from: com.google.android.gms.tagmanager.zza$zza  reason: collision with other inner class name */
    public interface C0150zza {
        AdvertisingIdClient.Info zzQa();
    }

    private zza(Context context) {
        this(context, (C0150zza) null, zzi.zzzc());
    }

    public zza(Context context, C0150zza zza, zze zze) {
        this.zzbEC = 900000;
        this.zzbED = DashMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_FIXED_MS;
        this.mClosed = false;
        this.zzbEG = new Object();
        this.zzbEH = new C0150zza() {
            public AdvertisingIdClient.Info zzQa() {
                String str;
                try {
                    return AdvertisingIdClient.getAdvertisingIdInfo(zza.this.mContext);
                } catch (IllegalStateException e) {
                    e = e;
                    str = "IllegalStateException getting Advertising Id Info";
                    zzbo.zzc(str, e);
                    return null;
                } catch (GooglePlayServicesRepairableException e2) {
                    e = e2;
                    str = "GooglePlayServicesRepairableException getting Advertising Id Info";
                    zzbo.zzc(str, e);
                    return null;
                } catch (IOException e3) {
                    e = e3;
                    str = "IOException getting Ad Id Info";
                    zzbo.zzc(str, e);
                    return null;
                } catch (GooglePlayServicesNotAvailableException e4) {
                    e = e4;
                    str = "GooglePlayServicesNotAvailableException getting Advertising Id Info";
                    zzbo.zzc(str, e);
                    return null;
                } catch (Exception e5) {
                    e = e5;
                    str = "Unknown exception. Could not get the Advertising Id Info.";
                    zzbo.zzc(str, e);
                    return null;
                }
            }
        };
        this.zzuP = zze;
        this.mContext = context != null ? context.getApplicationContext() : context;
        if (zza != null) {
            this.zzbEH = zza;
        }
        this.zzbEE = this.zzuP.currentTimeMillis();
        this.zzXh = new Thread(new Runnable() {
            public void run() {
                zza.this.zzPZ();
            }
        });
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzPW() {
        /*
            r2 = this;
            monitor-enter(r2)
            r2.zzPX()     // Catch:{ InterruptedException -> 0x000c }
            r0 = 500(0x1f4, double:2.47E-321)
            r2.wait(r0)     // Catch:{ InterruptedException -> 0x000c }
            goto L_0x000c
        L_0x000a:
            r0 = move-exception
            goto L_0x000e
        L_0x000c:
            monitor-exit(r2)     // Catch:{ all -> 0x000a }
            return
        L_0x000e:
            monitor-exit(r2)     // Catch:{ all -> 0x000a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zza.zzPW():void");
    }

    private void zzPX() {
        if (this.zzuP.currentTimeMillis() - this.zzbEE > this.zzbED) {
            synchronized (this.zzbEG) {
                this.zzbEG.notify();
            }
            this.zzbEE = this.zzuP.currentTimeMillis();
        }
    }

    private void zzPY() {
        if (this.zzuP.currentTimeMillis() - this.zzbEF > 3600000) {
            this.zzadB = null;
        }
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:368)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:172)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public void zzPZ() {
        /*
            r4 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
        L_0x0005:
            boolean r0 = r4.mClosed
            com.google.android.gms.tagmanager.zza$zza r0 = r4.zzbEH
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r0 = r0.zzQa()
            if (r0 == 0) goto L_0x001e
            r4.zzadB = r0
            com.google.android.gms.common.util.zze r0 = r4.zzuP
            long r0 = r0.currentTimeMillis()
            r4.zzbEF = r0
            java.lang.String r0 = "Obtained fresh AdvertisingId info from GmsCore."
            com.google.android.gms.tagmanager.zzbo.zzbg(r0)
        L_0x001e:
            monitor-enter(r4)
            r4.notifyAll()     // Catch:{ all -> 0x0038 }
            monitor-exit(r4)     // Catch:{ all -> 0x0038 }
            java.lang.Object r0 = r4.zzbEG     // Catch:{ InterruptedException -> 0x0032 }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0032 }
            java.lang.Object r1 = r4.zzbEG     // Catch:{ all -> 0x002f }
            long r2 = r4.zzbEC     // Catch:{ all -> 0x002f }
            r1.wait(r2)     // Catch:{ all -> 0x002f }
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            goto L_0x0005
        L_0x002f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r1     // Catch:{ InterruptedException -> 0x0032 }
        L_0x0032:
            java.lang.String r0 = "sleep interrupted in AdvertiserDataPoller thread; continuing"
            com.google.android.gms.tagmanager.zzbo.zzbg(r0)
            goto L_0x0005
        L_0x0038:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0038 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zza.zzPZ():void");
    }

    public static zza zzbS(Context context) {
        if (zzbEJ == null) {
            synchronized (zzbEI) {
                if (zzbEJ == null) {
                    zzbEJ = new zza(context);
                    zzbEJ.start();
                }
            }
        }
        return zzbEJ;
    }

    public boolean isLimitAdTrackingEnabled() {
        if (this.zzadB == null) {
            zzPW();
        } else {
            zzPX();
        }
        zzPY();
        if (this.zzadB == null) {
            return true;
        }
        return this.zzadB.isLimitAdTrackingEnabled();
    }

    public void start() {
        this.zzXh.start();
    }

    public String zzPV() {
        if (this.zzadB == null) {
            zzPW();
        } else {
            zzPX();
        }
        zzPY();
        if (this.zzadB == null) {
            return null;
        }
        return this.zzadB.getId();
    }
}
