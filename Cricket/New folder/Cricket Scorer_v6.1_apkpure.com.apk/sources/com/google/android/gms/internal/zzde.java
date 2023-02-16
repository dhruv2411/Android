package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.zzt;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
@TargetApi(14)
public class zzde extends Thread {
    private boolean mStarted = false;
    private boolean zzal = false;
    private final Object zzrJ;
    private boolean zzxO = false;
    private final zzdc zzxP;
    private final zzmd zzxQ;
    private final int zzxR;
    private final int zzxS;
    private final int zzxT;
    private final int zzxU;
    private final int zzxV;
    private final int zzxW;
    private final String zzxX;
    private final int zzxp;
    private final int zzxr;

    @zzme
    class zza {
        final int zzyf;
        final int zzyg;

        zza(zzde zzde, int i, int i2) {
            this.zzyf = i;
            this.zzyg = i2;
        }
    }

    public zzde(zzdc zzdc, zzmd zzmd) {
        this.zzxP = zzdc;
        this.zzxQ = zzmd;
        this.zzrJ = new Object();
        this.zzxp = zzgd.zzCd.get().intValue();
        this.zzxS = zzgd.zzCe.get().intValue();
        this.zzxr = zzgd.zzCf.get().intValue();
        this.zzxT = zzgd.zzCg.get().intValue();
        this.zzxU = zzgd.zzCj.get().intValue();
        this.zzxV = zzgd.zzCl.get().intValue();
        this.zzxW = zzgd.zzCm.get().intValue();
        this.zzxR = zzgd.zzCh.get().intValue();
        this.zzxX = zzgd.zzCo.get();
        setName("ContentFetchTask");
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:368)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:172)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public void run() {
        /*
            r3 = this;
        L_0x0000:
            boolean r0 = r3.zzek()     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
            if (r0 == 0) goto L_0x001d
            com.google.android.gms.internal.zzdd r0 = com.google.android.gms.ads.internal.zzw.zzcP()     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
            android.app.Activity r0 = r0.getActivity()     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
            if (r0 != 0) goto L_0x0019
            java.lang.String r0 = "ContentFetchThread: no activity. Sleeping."
            com.google.android.gms.internal.zzpk.zzbf(r0)     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
        L_0x0015:
            r3.zzem()     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
            goto L_0x0023
        L_0x0019:
            r3.zza((android.app.Activity) r0)     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
            goto L_0x0023
        L_0x001d:
            java.lang.String r0 = "ContentFetchTask: sleeping"
            com.google.android.gms.internal.zzpk.zzbf(r0)     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
            goto L_0x0015
        L_0x0023:
            int r0 = r3.zzxR     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
            int r0 = r0 * 1000
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x003a, Throwable -> 0x002c }
            goto L_0x0040
        L_0x002c:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.zzpk.zzb(r1, r0)
            com.google.android.gms.internal.zzmd r1 = r3.zzxQ
            java.lang.String r2 = "ContentFetchTask.run"
            r1.zza(r0, r2)
            goto L_0x0040
        L_0x003a:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.zzpk.zzb(r1, r0)
        L_0x0040:
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
        L_0x0043:
            boolean r1 = r3.zzxO     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0052
            java.lang.String r1 = "ContentFetchTask: waiting"
            com.google.android.gms.internal.zzpk.zzbf(r1)     // Catch:{ InterruptedException -> 0x0043 }
            java.lang.Object r1 = r3.zzrJ     // Catch:{ InterruptedException -> 0x0043 }
            r1.wait()     // Catch:{ InterruptedException -> 0x0043 }
            goto L_0x0043
        L_0x0052:
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            goto L_0x0000
        L_0x0054:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzde.run():void");
    }

    public void wakeup() {
        synchronized (this.zzrJ) {
            this.zzxO = false;
            this.zzrJ.notifyAll();
            zzpk.zzbf("ContentFetchThread: wakeup");
        }
    }

    /* access modifiers changed from: package-private */
    public zza zza(@Nullable View view, zzdb zzdb) {
        if (view == null) {
            return new zza(this, 0, 0);
        }
        Context context = zzw.zzcP().getContext();
        if (context != null) {
            String str = (String) view.getTag(context.getResources().getIdentifier(zzgd.zzCn.get(), TtmlNode.ATTR_ID, context.getPackageName()));
            if (!TextUtils.isEmpty(this.zzxX) && str != null && str.equals(this.zzxX)) {
                return new zza(this, 0, 0);
            }
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zza(this, 0, 0);
            }
            zzdb.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zza(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzqw)) {
            zzdb.zzef();
            return zza((WebView) view, zzdb, globalVisibleRect) ? new zza(this, 0, 1) : new zza(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zza(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                zza zza2 = zza(viewGroup.getChildAt(i3), zzdb);
                i += zza2.zzyf;
                i2 += zza2.zzyg;
            }
            return new zza(this, i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(@Nullable Activity activity) {
        if (activity != null) {
            View view = null;
            try {
                if (!(activity.getWindow() == null || activity.getWindow().getDecorView() == null)) {
                    view = activity.getWindow().getDecorView().findViewById(16908290);
                }
            } catch (Throwable th) {
                zzw.zzcQ().zza(th, "ContentFetchTask.extractContent");
                zzpk.zzbf("Failed getting root view of activity. Content not extracted.");
            }
            if (view != null) {
                zzh(view);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzdb zzdb, WebView webView, String str, boolean z) {
        zzdb.zzee();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString(MimeTypes.BASE_TYPE_TEXT);
                if (!TextUtils.isEmpty(webView.getTitle())) {
                    String valueOf = String.valueOf(webView.getTitle());
                    StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(optString).length());
                    sb.append(valueOf);
                    sb.append("\n");
                    sb.append(optString);
                    zzdb.zza(sb.toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    zzdb.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (zzdb.zzdZ()) {
                this.zzxP.zzb(zzdb);
            }
        } catch (JSONException unused) {
            zzpk.zzbf("Json string may be malformed.");
        } catch (Throwable th) {
            zzpk.zza("Failed to get webview content.", th);
            this.zzxQ.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zza(ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
        return runningAppProcessInfo.importance == 100;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(19)
    public boolean zza(final WebView webView, final zzdb zzdb, final boolean z) {
        if (!zzt.zzzl()) {
            return false;
        }
        zzdb.zzef();
        webView.post(new Runnable() {
            ValueCallback<String> zzya = new ValueCallback<String>() {
                /* renamed from: zzE */
                public void onReceiveValue(String str) {
                    zzde.this.zza(zzdb, webView, str, z);
                }
            };

            public void run() {
                if (webView.getSettings().getJavaScriptEnabled()) {
                    try {
                        webView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzya);
                    } catch (Throwable unused) {
                        this.zzya.onReceiveValue("");
                    }
                }
            }
        });
        return true;
    }

    public void zzej() {
        synchronized (this.zzrJ) {
            if (this.mStarted) {
                zzpk.zzbf("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzek() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            Context context = zzw.zzcP().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (Process.myPid() == next.pid) {
                    return zza(next) && !keyguardManager.inKeyguardRestrictedInputMode() && zzi(context);
                }
            }
            return false;
        } catch (Throwable th) {
            zzw.zzcQ().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    public zzdb zzel() {
        return this.zzxP.zzei();
    }

    public void zzem() {
        synchronized (this.zzrJ) {
            this.zzxO = true;
            boolean z = this.zzxO;
            StringBuilder sb = new StringBuilder(42);
            sb.append("ContentFetchThread: paused, mPause = ");
            sb.append(z);
            zzpk.zzbf(sb.toString());
        }
    }

    public boolean zzen() {
        return this.zzxO;
    }

    /* access modifiers changed from: package-private */
    public boolean zzh(@Nullable final View view) {
        if (view == null) {
            return false;
        }
        view.post(new Runnable() {
            public void run() {
                zzde.this.zzi(view);
            }
        });
        return true;
    }

    /* access modifiers changed from: package-private */
    public void zzi(View view) {
        try {
            zzdb zzdb = new zzdb(this.zzxp, this.zzxS, this.zzxr, this.zzxT, this.zzxU, this.zzxV, this.zzxW);
            zza zza2 = zza(view, zzdb);
            zzdb.zzeg();
            if (zza2.zzyf != 0 || zza2.zzyg != 0) {
                if (zza2.zzyg != 0 || zzdb.zzeh() != 0) {
                    if (zza2.zzyg != 0 || !this.zzxP.zza(zzdb)) {
                        this.zzxP.zzc(zzdb);
                    }
                }
            }
        } catch (Exception e) {
            zzpk.zzb("Exception in fetchContentOnUIThread", e);
            this.zzxQ.zza(e, "ContentFetchTask.fetchContent");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzi(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return false;
        }
        return powerManager.isScreenOn();
    }
}
