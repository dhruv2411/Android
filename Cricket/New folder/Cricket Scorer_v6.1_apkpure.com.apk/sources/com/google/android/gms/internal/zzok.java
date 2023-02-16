package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzof;
import com.google.android.gms.internal.zzpb;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Future;
import org.json.JSONObject;

@zzme
public class zzok extends zzpj implements zzoj {
    private final Context mContext;
    private final zzpb.zza zzPR;
    private final ArrayList<Future> zzVG;
    private final ArrayList<String> zzVH;
    private final HashMap<String, zzoe> zzVI;
    private final List<zzof> zzVJ;
    private final HashSet<String> zzVK;
    /* access modifiers changed from: private */
    public final zzns zzVL;
    private final long zzVv;
    private final Object zzrJ;

    public zzok(Context context, zzpb.zza zza, zzns zzns) {
        this(context, zza, zzns, zzgd.zzCO.get().longValue());
    }

    zzok(Context context, zzpb.zza zza, zzns zzns, long j) {
        this.zzVG = new ArrayList<>();
        this.zzVH = new ArrayList<>();
        this.zzVI = new HashMap<>();
        this.zzVJ = new ArrayList();
        this.zzVK = new HashSet<>();
        this.zzrJ = new Object();
        this.mContext = context;
        this.zzPR = zza;
        this.zzVL = zzns;
        this.zzVv = j;
    }

    private static int zzT(int i) {
        switch (i) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return 0;
            case 7:
                return 3;
            default:
                return 6;
        }
    }

    private zzpb zza(int i, @Nullable String str, @Nullable zzjq zzjq) {
        zzec zzec = this.zzPR.zzTi.zzRy;
        List<String> list = this.zzPR.zzWm.zzKF;
        List<String> list2 = this.zzPR.zzWm.zzKG;
        List<String> list3 = this.zzPR.zzWm.zzSp;
        int i2 = this.zzPR.zzWm.orientation;
        long j = this.zzPR.zzWm.zzKL;
        String str2 = this.zzPR.zzTi.zzRB;
        boolean z = this.zzPR.zzWm.zzSn;
        zzjr zzjr = this.zzPR.zzWc;
        long j2 = this.zzPR.zzWm.zzSo;
        zzeg zzeg = this.zzPR.zzvr;
        long j3 = j2;
        long j4 = this.zzPR.zzWm.zzSm;
        long j5 = this.zzPR.zzWg;
        long j6 = this.zzPR.zzWm.zzSr;
        String str3 = this.zzPR.zzWm.zzSs;
        JSONObject jSONObject = this.zzPR.zzWa;
        zzoo zzoo = this.zzPR.zzWm.zzSC;
        List<String> list4 = this.zzPR.zzWm.zzSD;
        List<String> list5 = this.zzPR.zzWm.zzSE;
        boolean z2 = this.zzPR.zzWm.zzSF;
        zzmp zzmp = this.zzPR.zzWm.zzSG;
        zzeg zzeg2 = zzeg;
        String str4 = str3;
        zzjr zzjr2 = zzjr;
        return new zzpb(zzec, (zzqw) null, list, i, list2, list3, i2, j, str2, z, zzjq, (zzkb) null, str, zzjr2, (zzjt) null, j3, zzeg2, j4, j5, j6, str4, jSONObject, (zzha.zza) null, zzoo, list4, list5, z2, zzmp, zzjM(), this.zzPR.zzWm.zzKI, this.zzPR.zzWm.zzSJ);
    }

    private zzpb zza(String str, zzjq zzjq) {
        return zza(-2, str, zzjq);
    }

    private static String zza(zzof zzof) {
        String str = zzof.zzKq;
        int zzT = zzT(zzof.errorCode);
        long j = zzof.zzLn;
        StringBuilder sb = new StringBuilder(33 + String.valueOf(str).length());
        sb.append(str);
        sb.append(".");
        sb.append(zzT);
        sb.append(".");
        sb.append(j);
        return sb.toString();
    }

    private void zza(String str, String str2, zzjq zzjq) {
        synchronized (this.zzrJ) {
            zzol zzaN = this.zzVL.zzaN(str);
            if (!(zzaN == null || zzaN.zzjO() == null)) {
                if (zzaN.zzjN() != null) {
                    zzoe zza = zza(str, str2, zzjq, zzaN);
                    this.zzVG.add((Future) zza.zziP());
                    this.zzVH.add(str);
                    this.zzVI.put(str, zza);
                    return;
                }
            }
            this.zzVJ.add(new zzof.zza().zzaQ(zzjq.zzKq).zzaP(str).zzl(0).zzae(7).zzjK());
        }
    }

    private zzpb zzjL() {
        return zza(3, (String) null, (zzjq) null);
    }

    private String zzjM() {
        StringBuilder sb = new StringBuilder("");
        if (this.zzVJ == null) {
            return sb.toString();
        }
        for (zzof next : this.zzVJ) {
            if (next != null && !TextUtils.isEmpty(next.zzKq)) {
                sb.append(String.valueOf(zza(next)).concat(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR));
            }
        }
        return sb.substring(0, Math.max(0, sb.length() - 1));
    }

    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public zzoe zza(String str, String str2, zzjq zzjq, zzol zzol) {
        return new zzoe(this.mContext, str, str2, zzjq, this.zzPR, zzol, this, this.zzVv);
    }

    public void zza(String str, int i) {
    }

    public void zzaO(String str) {
        synchronized (this.zzrJ) {
            this.zzVK.add(str);
        }
    }

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
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Missing exception handler attribute for start block: B:68:0x0104 */
    public void zzco() {
        /*
            r6 = this;
            com.google.android.gms.internal.zzpb$zza r0 = r6.zzPR
            com.google.android.gms.internal.zzjr r0 = r0.zzWc
            java.util.List<com.google.android.gms.internal.zzjq> r0 = r0.zzKD
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0050
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.zzjq r1 = (com.google.android.gms.internal.zzjq) r1
            java.lang.String r2 = r1.zzKv
            java.util.List<java.lang.String> r3 = r1.zzKp
            java.util.Iterator r3 = r3.iterator()
        L_0x001e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x000a
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r5 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x003a
            java.lang.String r5 = "com.google.ads.mediation.customevent.CustomEventAdapter"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0045
        L_0x003a:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0049 }
            r4.<init>(r2)     // Catch:{ JSONException -> 0x0049 }
            java.lang.String r5 = "class_name"
            java.lang.String r4 = r4.getString(r5)     // Catch:{ JSONException -> 0x0049 }
        L_0x0045:
            r6.zza((java.lang.String) r4, (java.lang.String) r2, (com.google.android.gms.internal.zzjq) r1)
            goto L_0x001e
        L_0x0049:
            r4 = move-exception
            java.lang.String r5 = "Unable to determine custom event class name, skipping..."
            com.google.android.gms.internal.zzpk.zzb(r5, r4)
            goto L_0x001e
        L_0x0050:
            r0 = 0
        L_0x0051:
            java.util.ArrayList<java.util.concurrent.Future> r1 = r6.zzVG
            int r1 = r1.size()
            if (r0 >= r1) goto L_0x015d
            java.util.ArrayList<java.util.concurrent.Future> r1 = r6.zzVG     // Catch:{ InterruptedException -> 0x0104, Exception -> 0x00d2 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ InterruptedException -> 0x0104, Exception -> 0x00d2 }
            java.util.concurrent.Future r1 = (java.util.concurrent.Future) r1     // Catch:{ InterruptedException -> 0x0104, Exception -> 0x00d2 }
            r1.get()     // Catch:{ InterruptedException -> 0x0104, Exception -> 0x00d2 }
            java.lang.Object r1 = r6.zzrJ
            monitor-enter(r1)
            java.util.ArrayList<java.lang.String> r2 = r6.zzVH     // Catch:{ all -> 0x00cd }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x00cd }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x00cd }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00cd }
            if (r3 != 0) goto L_0x0088
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r3 = r6.zzVI     // Catch:{ all -> 0x00cd }
            java.lang.Object r2 = r3.get(r2)     // Catch:{ all -> 0x00cd }
            com.google.android.gms.internal.zzoe r2 = (com.google.android.gms.internal.zzoe) r2     // Catch:{ all -> 0x00cd }
            if (r2 == 0) goto L_0x0088
            java.util.List<com.google.android.gms.internal.zzof> r3 = r6.zzVJ     // Catch:{ all -> 0x00cd }
            com.google.android.gms.internal.zzof r2 = r2.zzjH()     // Catch:{ all -> 0x00cd }
            r3.add(r2)     // Catch:{ all -> 0x00cd }
        L_0x0088:
            monitor-exit(r1)     // Catch:{ all -> 0x00cd }
            java.lang.Object r2 = r6.zzrJ
            monitor-enter(r2)
            java.util.HashSet<java.lang.String> r1 = r6.zzVK     // Catch:{ all -> 0x00ca }
            java.util.ArrayList<java.lang.String> r3 = r6.zzVH     // Catch:{ all -> 0x00ca }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ all -> 0x00ca }
            boolean r1 = r1.contains(r3)     // Catch:{ all -> 0x00ca }
            if (r1 == 0) goto L_0x00c8
            java.util.ArrayList<java.lang.String> r1 = r6.zzVH     // Catch:{ all -> 0x00ca }
            java.lang.Object r0 = r1.get(r0)     // Catch:{ all -> 0x00ca }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x00ca }
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r1 = r6.zzVI     // Catch:{ all -> 0x00ca }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x00ca }
            if (r1 == 0) goto L_0x00b7
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r1 = r6.zzVI     // Catch:{ all -> 0x00ca }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x00ca }
            com.google.android.gms.internal.zzoe r1 = (com.google.android.gms.internal.zzoe) r1     // Catch:{ all -> 0x00ca }
            com.google.android.gms.internal.zzjq r1 = r1.zzjI()     // Catch:{ all -> 0x00ca }
            goto L_0x00b8
        L_0x00b7:
            r1 = 0
        L_0x00b8:
            com.google.android.gms.internal.zzpb r0 = r6.zza((java.lang.String) r0, (com.google.android.gms.internal.zzjq) r1)     // Catch:{ all -> 0x00ca }
            android.os.Handler r1 = com.google.android.gms.internal.zzqe.zzYP     // Catch:{ all -> 0x00ca }
            com.google.android.gms.internal.zzok$1 r3 = new com.google.android.gms.internal.zzok$1     // Catch:{ all -> 0x00ca }
            r3.<init>(r0)     // Catch:{ all -> 0x00ca }
            r1.post(r3)     // Catch:{ all -> 0x00ca }
            monitor-exit(r2)     // Catch:{ all -> 0x00ca }
            return
        L_0x00c8:
            monitor-exit(r2)     // Catch:{ all -> 0x00ca }
            goto L_0x00fd
        L_0x00ca:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00ca }
            throw r0
        L_0x00cd:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00cd }
            throw r0
        L_0x00d0:
            r1 = move-exception
            goto L_0x0134
        L_0x00d2:
            r1 = move-exception
            java.lang.String r2 = "Unable to resolve rewarded adapter."
            com.google.android.gms.internal.zzpk.zzc(r2, r1)     // Catch:{ all -> 0x00d0 }
            java.lang.Object r1 = r6.zzrJ
            monitor-enter(r1)
            java.util.ArrayList<java.lang.String> r2 = r6.zzVH     // Catch:{ all -> 0x0101 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x0101 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0101 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0101 }
            if (r3 != 0) goto L_0x00fc
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r3 = r6.zzVI     // Catch:{ all -> 0x0101 }
            java.lang.Object r2 = r3.get(r2)     // Catch:{ all -> 0x0101 }
            com.google.android.gms.internal.zzoe r2 = (com.google.android.gms.internal.zzoe) r2     // Catch:{ all -> 0x0101 }
            if (r2 == 0) goto L_0x00fc
            java.util.List<com.google.android.gms.internal.zzof> r3 = r6.zzVJ     // Catch:{ all -> 0x0101 }
            com.google.android.gms.internal.zzof r2 = r2.zzjH()     // Catch:{ all -> 0x0101 }
            r3.add(r2)     // Catch:{ all -> 0x0101 }
        L_0x00fc:
            monitor-exit(r1)     // Catch:{ all -> 0x0101 }
        L_0x00fd:
            int r0 = r0 + 1
            goto L_0x0051
        L_0x0101:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0101 }
            throw r0
        L_0x0104:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00d0 }
            r1.interrupt()     // Catch:{ all -> 0x00d0 }
            java.lang.Object r1 = r6.zzrJ
            monitor-enter(r1)
            java.util.ArrayList<java.lang.String> r2 = r6.zzVH     // Catch:{ all -> 0x0131 }
            java.lang.Object r0 = r2.get(r0)     // Catch:{ all -> 0x0131 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0131 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0131 }
            if (r2 != 0) goto L_0x012f
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r2 = r6.zzVI     // Catch:{ all -> 0x0131 }
            java.lang.Object r0 = r2.get(r0)     // Catch:{ all -> 0x0131 }
            com.google.android.gms.internal.zzoe r0 = (com.google.android.gms.internal.zzoe) r0     // Catch:{ all -> 0x0131 }
            if (r0 == 0) goto L_0x012f
            java.util.List<com.google.android.gms.internal.zzof> r2 = r6.zzVJ     // Catch:{ all -> 0x0131 }
            com.google.android.gms.internal.zzof r0 = r0.zzjH()     // Catch:{ all -> 0x0131 }
            r2.add(r0)     // Catch:{ all -> 0x0131 }
        L_0x012f:
            monitor-exit(r1)     // Catch:{ all -> 0x0131 }
            goto L_0x015d
        L_0x0131:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0131 }
            throw r0
        L_0x0134:
            java.lang.Object r2 = r6.zzrJ
            monitor-enter(r2)
            java.util.ArrayList<java.lang.String> r3 = r6.zzVH     // Catch:{ all -> 0x015a }
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x015a }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x015a }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x015a }
            if (r3 != 0) goto L_0x0158
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r3 = r6.zzVI     // Catch:{ all -> 0x015a }
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.zzoe r0 = (com.google.android.gms.internal.zzoe) r0     // Catch:{ all -> 0x015a }
            if (r0 == 0) goto L_0x0158
            java.util.List<com.google.android.gms.internal.zzof> r3 = r6.zzVJ     // Catch:{ all -> 0x015a }
            com.google.android.gms.internal.zzof r0 = r0.zzjH()     // Catch:{ all -> 0x015a }
            r3.add(r0)     // Catch:{ all -> 0x015a }
        L_0x0158:
            monitor-exit(r2)     // Catch:{ all -> 0x015a }
            throw r1
        L_0x015a:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x015a }
            throw r0
        L_0x015d:
            com.google.android.gms.internal.zzpb r0 = r6.zzjL()
            android.os.Handler r1 = com.google.android.gms.internal.zzqe.zzYP
            com.google.android.gms.internal.zzok$2 r2 = new com.google.android.gms.internal.zzok$2
            r2.<init>(r0)
            r1.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzok.zzco():void");
    }
}
