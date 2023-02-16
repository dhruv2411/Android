package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzcy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
class zzra extends WebView implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzqw {
    @Nullable
    private final zzaw zzGP;
    private int zzMB = -1;
    private int zzMC = -1;
    private int zzMy = -1;
    private int zzMz = -1;
    private String zzOV = "";
    private zzgj zzOW;
    private Boolean zzWO;
    private final zza zzZZ;
    private final zzu zzaaa;
    private zzqx zzaab;
    private zze zzaac;
    private boolean zzaad;
    private boolean zzaae;
    private boolean zzaaf;
    private boolean zzaag;
    private int zzaah;
    private boolean zzaai = true;
    boolean zzaaj = false;
    private zzrb zzaak;
    private boolean zzaal;
    private boolean zzaam;
    private zzgy zzaan;
    private int zzaao;
    /* access modifiers changed from: private */
    public int zzaap;
    private zzgj zzaaq;
    private zzgj zzaar;
    private zzgk zzaas;
    private WeakReference<View.OnClickListener> zzaat;
    private zze zzaau;
    private boolean zzaav;
    private Map<String, zzis> zzaaw;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();
    private final com.google.android.gms.ads.internal.zze zzsv;
    private final zzqh zztt;
    private zzeg zzus;
    private zzqd zzvY;
    private final WindowManager zzwo;

    @zzme
    public static class zza extends MutableContextWrapper {
        private Activity zzYJ;
        private Context zzaay;
        private Context zzwi;

        public zza(Context context) {
            super(context);
            setBaseContext(context);
        }

        public Object getSystemService(String str) {
            return this.zzaay.getSystemService(str);
        }

        public void setBaseContext(Context context) {
            this.zzwi = context.getApplicationContext();
            this.zzYJ = context instanceof Activity ? (Activity) context : null;
            this.zzaay = context;
            super.setBaseContext(this.zzwi);
        }

        public void startActivity(Intent intent) {
            if (this.zzYJ != null) {
                this.zzYJ.startActivity(intent);
                return;
            }
            intent.setFlags(268435456);
            this.zzwi.startActivity(intent);
        }

        public Activity zzlr() {
            return this.zzYJ;
        }

        public Context zzls() {
            return this.zzaay;
        }
    }

    protected zzra(zza zza2, zzeg zzeg, boolean z, boolean z2, @Nullable zzaw zzaw, zzqh zzqh, zzgl zzgl, zzu zzu, com.google.android.gms.ads.internal.zze zze) {
        super(zza2);
        this.zzZZ = zza2;
        this.zzus = zzeg;
        this.zzaaf = z;
        this.zzaah = -1;
        this.zzGP = zzaw;
        this.zztt = zzqh;
        this.zzaaa = zzu;
        this.zzsv = zze;
        this.zzwo = (WindowManager) getContext().getSystemService("window");
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzw.zzcM().zza((Context) zza2, zzqh.zzba, settings);
        zzw.zzcO().zza(getContext(), settings);
        setDownloadListener(this);
        zzmg();
        if (zzt.zzzj()) {
            addJavascriptInterface(new zzrc(this), "googleAdsJsInterface");
        }
        zzt.zzze();
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        this.zzvY = new zzqd(this.zzZZ.zzlr(), this, this, (ViewTreeObserver.OnScrollChangedListener) null);
        zzd(zzgl);
        zzw.zzcO().zzW(zza2);
    }

    private void zzP(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? "1" : "0");
        zza("onAdVisibilityChanged", (Map<String, ?>) hashMap);
    }

    static zzra zzb(Context context, zzeg zzeg, boolean z, boolean z2, @Nullable zzaw zzaw, zzqh zzqh, zzgl zzgl, zzu zzu, com.google.android.gms.ads.internal.zze zze) {
        return new zzra(new zza(context), zzeg, z, z2, zzaw, zzqh, zzgl, zzu, zze);
    }

    private void zzd(zzgl zzgl) {
        zzmk();
        this.zzaas = new zzgk(new zzgl(true, "make_wv", this.zzus.zzzy));
        this.zzaas.zzfA().zzc(zzgl);
        this.zzOW = zzgh.zzb(this.zzaas.zzfA());
        this.zzaas.zza("native:view_create", this.zzOW);
        this.zzaar = null;
        this.zzaaq = null;
    }

    private void zzkC() {
        synchronized (this.zzrJ) {
            if (!this.zzaav) {
                this.zzaav = true;
                zzw.zzcQ().zzkC();
            }
        }
    }

    private void zzmc() {
        synchronized (this.zzrJ) {
            this.zzWO = zzw.zzcQ().zzkq();
            if (this.zzWO == null) {
                try {
                    evaluateJavascript("(function(){})()", (ValueCallback<String>) null);
                    zza((Boolean) true);
                } catch (IllegalStateException unused) {
                    zza((Boolean) false);
                }
            }
        }
    }

    private void zzmd() {
        zzgh.zza(this.zzaas.zzfA(), this.zzOW, "aeh2");
    }

    private void zzme() {
        zzgh.zza(this.zzaas.zzfA(), this.zzOW, "aebb2");
    }

    private void zzmg() {
        synchronized (this.zzrJ) {
            if (!this.zzaaf) {
                if (!this.zzus.zzzz) {
                    if (Build.VERSION.SDK_INT < 18) {
                        zzpk.zzbf("Disabling hardware acceleration on an AdView.");
                        zzmh();
                    } else {
                        zzpk.zzbf("Enabling hardware acceleration on an AdView.");
                        zzmi();
                    }
                }
            }
            int i = Build.VERSION.SDK_INT;
            zzpk.zzbf("Enabling hardware acceleration on an overlay.");
            zzmi();
        }
    }

    private void zzmh() {
        synchronized (this.zzrJ) {
            if (!this.zzaag) {
                zzw.zzcO().zzu(this);
            }
            this.zzaag = true;
        }
    }

    private void zzmi() {
        synchronized (this.zzrJ) {
            if (this.zzaag) {
                zzw.zzcO().zzt(this);
            }
            this.zzaag = false;
        }
    }

    private void zzmj() {
        synchronized (this.zzrJ) {
            this.zzaaw = null;
        }
    }

    private void zzmk() {
        zzgl zzfA;
        if (this.zzaas != null && (zzfA = this.zzaas.zzfA()) != null && zzw.zzcQ().zzkk() != null) {
            zzw.zzcQ().zzkk().zza(zzfA);
        }
    }

    public void destroy() {
        synchronized (this.zzrJ) {
            zzmk();
            this.zzvY.zzlf();
            if (this.zzaac != null) {
                this.zzaac.close();
                this.zzaac.onDestroy();
                this.zzaac = null;
            }
            this.zzaab.reset();
            if (!this.zzaae) {
                zzw.zzdj().zze(this);
                zzmj();
                this.zzaae = true;
                zzpk.v("Initiating WebView self destruct sequence in 3...");
                this.zzaab.zzlS();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return;
     */
    @android.annotation.TargetApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void evaluateJavascript(java.lang.String r3, android.webkit.ValueCallback<java.lang.String> r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.zzrJ
            monitor-enter(r0)
            boolean r1 = r2.isDestroyed()     // Catch:{ all -> 0x001b }
            if (r1 == 0) goto L_0x0016
            java.lang.String r3 = "The webview is destroyed. Ignoring action."
            com.google.android.gms.internal.zzpk.zzbh(r3)     // Catch:{ all -> 0x001b }
            if (r4 == 0) goto L_0x0014
            r3 = 0
            r4.onReceiveValue(r3)     // Catch:{ all -> 0x001b }
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return
        L_0x0016:
            super.evaluateJavascript(r3, r4)     // Catch:{ all -> 0x001b }
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return
        L_0x001b:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzra.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            synchronized (this.zzrJ) {
                if (!this.zzaae) {
                    this.zzaab.reset();
                    zzw.zzdj().zze(this);
                    zzmj();
                    zzkC();
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public String getRequestId() {
        String str;
        synchronized (this.zzrJ) {
            str = this.zzOV;
        }
        return str;
    }

    public int getRequestedOrientation() {
        int i;
        synchronized (this.zzrJ) {
            i = this.zzaah;
        }
        return i;
    }

    public View getView() {
        return this;
    }

    public WebView getWebView() {
        return this;
    }

    public boolean isDestroyed() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaae;
        }
        return z;
    }

    public void loadData(String str, String str2, String str3) {
        synchronized (this.zzrJ) {
            if (!isDestroyed()) {
                super.loadData(str, str2, str3);
            } else {
                zzpk.zzbh("The webview is destroyed. Ignoring action.");
            }
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        synchronized (this.zzrJ) {
            if (!isDestroyed()) {
                super.loadDataWithBaseURL(str, str2, str3, str4, str5);
            } else {
                zzpk.zzbh("The webview is destroyed. Ignoring action.");
            }
        }
    }

    public void loadUrl(String str) {
        synchronized (this.zzrJ) {
            if (!isDestroyed()) {
                try {
                    super.loadUrl(str);
                } catch (Throwable th) {
                    zzw.zzcQ().zza(th, "AdWebViewImpl.loadUrl");
                    zzpk.zzc("Could not call loadUrl. ", th);
                }
            } else {
                zzpk.zzbh("The webview is destroyed. Ignoring action.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        synchronized (this.zzrJ) {
            super.onAttachedToWindow();
            if (!isDestroyed()) {
                this.zzvY.onAttachedToWindow();
            }
            boolean z = this.zzaal;
            if (zzlv() != null && zzlv().zzlO()) {
                if (!this.zzaam) {
                    ViewTreeObserver.OnGlobalLayoutListener zzlP = zzlv().zzlP();
                    if (zzlP != null) {
                        zzw.zzdk().zza(getView(), zzlP);
                    }
                    ViewTreeObserver.OnScrollChangedListener zzlQ = zzlv().zzlQ();
                    if (zzlQ != null) {
                        zzw.zzdk().zza(getView(), zzlQ);
                    }
                    this.zzaam = true;
                }
                z = true;
            }
            zzP(z);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        synchronized (this.zzrJ) {
            if (!isDestroyed()) {
                this.zzvY.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzaam && zzlv() != null && zzlv().zzlO() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                ViewTreeObserver.OnGlobalLayoutListener zzlP = zzlv().zzlP();
                if (zzlP != null) {
                    zzw.zzcO().zza(getViewTreeObserver(), zzlP);
                }
                ViewTreeObserver.OnScrollChangedListener zzlQ = zzlv().zzlQ();
                if (zzlQ != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzlQ);
                }
                this.zzaam = false;
            }
        }
        zzP(false);
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzw.zzcM().zzb(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length() + String.valueOf(str4).length());
            sb.append("Couldn't find an Activity to view url/mimetype: ");
            sb.append(str);
            sb.append(" / ");
            sb.append(str4);
            zzpk.zzbf(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    @TargetApi(21)
    public void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (Build.VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                if (zzlv() != null && zzlv().zzlZ() != null) {
                    zzlv().zzlZ().zzce();
                }
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (zzgd.zzCK.get().booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if ((motionEvent.getActionMasked() == 8) && ((axisValue > 0.0f && !canScrollVertically(-1)) || ((axisValue < 0.0f && !canScrollVertically(1)) || ((axisValue2 > 0.0f && !canScrollHorizontally(-1)) || (axisValue2 < 0.0f && !canScrollHorizontally(1)))))) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void onGlobalLayout() {
        boolean zzmb = zzmb();
        zze zzlt = zzlt();
        if (zzlt != null && zzmb) {
            zzlt.zzhJ();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0139, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0131  */
    @android.annotation.SuppressLint({"DrawAllocation"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.zzrJ
            monitor-enter(r0)
            boolean r1 = r7.isDestroyed()     // Catch:{ all -> 0x013f }
            r2 = 0
            if (r1 == 0) goto L_0x000f
            r7.setMeasuredDimension(r2, r2)     // Catch:{ all -> 0x013f }
            monitor-exit(r0)     // Catch:{ all -> 0x013f }
            return
        L_0x000f:
            boolean r1 = r7.isInEditMode()     // Catch:{ all -> 0x013f }
            if (r1 != 0) goto L_0x013a
            boolean r1 = r7.zzaaf     // Catch:{ all -> 0x013f }
            if (r1 != 0) goto L_0x013a
            com.google.android.gms.internal.zzeg r1 = r7.zzus     // Catch:{ all -> 0x013f }
            boolean r1 = r1.zzzB     // Catch:{ all -> 0x013f }
            if (r1 == 0) goto L_0x0021
            goto L_0x013a
        L_0x0021:
            com.google.android.gms.internal.zzeg r1 = r7.zzus     // Catch:{ all -> 0x013f }
            boolean r1 = r1.zzzC     // Catch:{ all -> 0x013f }
            if (r1 == 0) goto L_0x0073
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.internal.zzgd.zzEy     // Catch:{ all -> 0x013f }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x013f }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x013f }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x013f }
            if (r1 != 0) goto L_0x006e
            boolean r1 = com.google.android.gms.common.util.zzt.zzzj()     // Catch:{ all -> 0x013f }
            if (r1 != 0) goto L_0x003c
            goto L_0x006e
        L_0x003c:
            java.lang.String r1 = "/contentHeight"
            com.google.android.gms.internal.zzid r2 = r7.zzmf()     // Catch:{ all -> 0x013f }
            r7.zza((java.lang.String) r1, (com.google.android.gms.internal.zzid) r2)     // Catch:{ all -> 0x013f }
            java.lang.String r1 = "(function() {  var height = -1;  if (document.body) { height = document.body.offsetHeight;}  else if (document.documentElement) {      height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  window.googleAdsJsInterface.notify(url);  })();"
            r7.zzbm(r1)     // Catch:{ all -> 0x013f }
            com.google.android.gms.internal.zzra$zza r1 = r7.zzZZ     // Catch:{ all -> 0x013f }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ all -> 0x013f }
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()     // Catch:{ all -> 0x013f }
            float r1 = r1.density     // Catch:{ all -> 0x013f }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x013f }
            int r2 = r7.zzaap     // Catch:{ all -> 0x013f }
            r3 = -1
            if (r2 == r3) goto L_0x0065
            int r9 = r7.zzaap     // Catch:{ all -> 0x013f }
            float r9 = (float) r9     // Catch:{ all -> 0x013f }
            float r9 = r9 * r1
            int r9 = (int) r9     // Catch:{ all -> 0x013f }
            goto L_0x0069
        L_0x0065:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x013f }
        L_0x0069:
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x013f }
            monitor-exit(r0)     // Catch:{ all -> 0x013f }
            return
        L_0x006e:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x013f }
            monitor-exit(r0)     // Catch:{ all -> 0x013f }
            return
        L_0x0073:
            com.google.android.gms.internal.zzeg r1 = r7.zzus     // Catch:{ all -> 0x013f }
            boolean r1 = r1.zzzz     // Catch:{ all -> 0x013f }
            if (r1 == 0) goto L_0x0090
            android.util.DisplayMetrics r8 = new android.util.DisplayMetrics     // Catch:{ all -> 0x013f }
            r8.<init>()     // Catch:{ all -> 0x013f }
            android.view.WindowManager r9 = r7.zzwo     // Catch:{ all -> 0x013f }
            android.view.Display r9 = r9.getDefaultDisplay()     // Catch:{ all -> 0x013f }
            r9.getMetrics(r8)     // Catch:{ all -> 0x013f }
            int r9 = r8.widthPixels     // Catch:{ all -> 0x013f }
            int r8 = r8.heightPixels     // Catch:{ all -> 0x013f }
            r7.setMeasuredDimension(r9, r8)     // Catch:{ all -> 0x013f }
            monitor-exit(r0)     // Catch:{ all -> 0x013f }
            return
        L_0x0090:
            int r1 = android.view.View.MeasureSpec.getMode(r8)     // Catch:{ all -> 0x013f }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x013f }
            int r3 = android.view.View.MeasureSpec.getMode(r9)     // Catch:{ all -> 0x013f }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x013f }
            r4 = 1073741824(0x40000000, float:2.0)
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = 2147483647(0x7fffffff, float:NaN)
            if (r1 == r5) goto L_0x00ae
            if (r1 != r4) goto L_0x00ac
            goto L_0x00ae
        L_0x00ac:
            r1 = r6
            goto L_0x00af
        L_0x00ae:
            r1 = r8
        L_0x00af:
            if (r3 == r5) goto L_0x00b3
            if (r3 != r4) goto L_0x00b4
        L_0x00b3:
            r6 = r9
        L_0x00b4:
            com.google.android.gms.internal.zzeg r3 = r7.zzus     // Catch:{ all -> 0x013f }
            int r3 = r3.widthPixels     // Catch:{ all -> 0x013f }
            r4 = 8
            if (r3 > r1) goto L_0x00d8
            com.google.android.gms.internal.zzeg r1 = r7.zzus     // Catch:{ all -> 0x013f }
            int r1 = r1.heightPixels     // Catch:{ all -> 0x013f }
            if (r1 <= r6) goto L_0x00c3
            goto L_0x00d8
        L_0x00c3:
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x013f }
            if (r8 == r4) goto L_0x00cc
            r7.setVisibility(r2)     // Catch:{ all -> 0x013f }
        L_0x00cc:
            com.google.android.gms.internal.zzeg r8 = r7.zzus     // Catch:{ all -> 0x013f }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x013f }
            com.google.android.gms.internal.zzeg r9 = r7.zzus     // Catch:{ all -> 0x013f }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x013f }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x013f }
            goto L_0x0138
        L_0x00d8:
            com.google.android.gms.internal.zzra$zza r1 = r7.zzZZ     // Catch:{ all -> 0x013f }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ all -> 0x013f }
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()     // Catch:{ all -> 0x013f }
            float r1 = r1.density     // Catch:{ all -> 0x013f }
            com.google.android.gms.internal.zzeg r3 = r7.zzus     // Catch:{ all -> 0x013f }
            int r3 = r3.widthPixels     // Catch:{ all -> 0x013f }
            float r3 = (float) r3     // Catch:{ all -> 0x013f }
            float r3 = r3 / r1
            int r3 = (int) r3     // Catch:{ all -> 0x013f }
            com.google.android.gms.internal.zzeg r5 = r7.zzus     // Catch:{ all -> 0x013f }
            int r5 = r5.heightPixels     // Catch:{ all -> 0x013f }
            float r5 = (float) r5     // Catch:{ all -> 0x013f }
            float r5 = r5 / r1
            int r5 = (int) r5     // Catch:{ all -> 0x013f }
            float r8 = (float) r8     // Catch:{ all -> 0x013f }
            float r8 = r8 / r1
            int r8 = (int) r8     // Catch:{ all -> 0x013f }
            float r9 = (float) r9     // Catch:{ all -> 0x013f }
            float r9 = r9 / r1
            int r9 = (int) r9     // Catch:{ all -> 0x013f }
            r1 = 103(0x67, float:1.44E-43)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x013f }
            r6.<init>(r1)     // Catch:{ all -> 0x013f }
            java.lang.String r1 = "Not enough space to show ad. Needs "
            r6.append(r1)     // Catch:{ all -> 0x013f }
            r6.append(r3)     // Catch:{ all -> 0x013f }
            java.lang.String r1 = "x"
            r6.append(r1)     // Catch:{ all -> 0x013f }
            r6.append(r5)     // Catch:{ all -> 0x013f }
            java.lang.String r1 = " dp, but only has "
            r6.append(r1)     // Catch:{ all -> 0x013f }
            r6.append(r8)     // Catch:{ all -> 0x013f }
            java.lang.String r8 = "x"
            r6.append(r8)     // Catch:{ all -> 0x013f }
            r6.append(r9)     // Catch:{ all -> 0x013f }
            java.lang.String r8 = " dp."
            r6.append(r8)     // Catch:{ all -> 0x013f }
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x013f }
            com.google.android.gms.internal.zzpk.zzbh(r8)     // Catch:{ all -> 0x013f }
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x013f }
            if (r8 == r4) goto L_0x0135
            r8 = 4
            r7.setVisibility(r8)     // Catch:{ all -> 0x013f }
        L_0x0135:
            r7.setMeasuredDimension(r2, r2)     // Catch:{ all -> 0x013f }
        L_0x0138:
            monitor-exit(r0)     // Catch:{ all -> 0x013f }
            return
        L_0x013a:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x013f }
            monitor-exit(r0)     // Catch:{ all -> 0x013f }
            return
        L_0x013f:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x013f }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzra.onMeasure(int, int):void");
    }

    public void onPause() {
        if (!isDestroyed()) {
            try {
                zzt.zzze();
                super.onPause();
            } catch (Exception e) {
                zzpk.zzb("Could not pause webview.", e);
            }
        }
    }

    public void onResume() {
        if (!isDestroyed()) {
            try {
                zzt.zzze();
                super.onResume();
            } catch (Exception e) {
                zzpk.zzb("Could not resume webview.", e);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (zzlv().zzlO()) {
            synchronized (this.zzrJ) {
                if (this.zzaan != null) {
                    this.zzaan.zzc(motionEvent);
                }
            }
        } else if (this.zzGP != null) {
            this.zzGP.zza(motionEvent);
        }
        if (isDestroyed()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setContext(Context context) {
        this.zzZZ.setBaseContext(context);
        this.zzvY.zzl(this.zzZZ.zzlr());
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzaat = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public void setRequestedOrientation(int i) {
        synchronized (this.zzrJ) {
            this.zzaah = i;
            if (this.zzaac != null) {
                this.zzaac.setRequestedOrientation(this.zzaah);
            }
        }
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzqx) {
            this.zzaab = (zzqx) webViewClient;
        }
    }

    public void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Exception e) {
                zzpk.zzb("Could not stop loading webview.", e);
            }
        }
    }

    public void zzK(boolean z) {
        synchronized (this.zzrJ) {
            this.zzaaf = z;
            zzmg();
        }
    }

    public void zzL(boolean z) {
        synchronized (this.zzrJ) {
            if (this.zzaac != null) {
                this.zzaac.zza(this.zzaab.zzdD(), z);
            } else {
                this.zzaad = z;
            }
        }
    }

    public void zzM(int i) {
        if (i == 0) {
            zzme();
        }
        zzmd();
        if (this.zzaas.zzfA() != null) {
            this.zzaas.zzfA().zzh("close_type", String.valueOf(i));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zztt.zzba);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public void zzM(boolean z) {
        synchronized (this.zzrJ) {
            this.zzaai = z;
        }
    }

    public void zzN(boolean z) {
        synchronized (this.zzrJ) {
            this.zzaao += z ? 1 : -1;
            if (this.zzaao <= 0 && this.zzaac != null) {
                this.zzaac.zzhM();
            }
        }
    }

    public void zza(Context context, zzeg zzeg, zzgl zzgl) {
        synchronized (this.zzrJ) {
            this.zzvY.zzlf();
            setContext(context);
            this.zzaac = null;
            this.zzus = zzeg;
            this.zzaaf = false;
            this.zzaad = false;
            this.zzOV = "";
            this.zzaah = -1;
            zzw.zzcO().zzm(this);
            loadUrl("about:blank");
            this.zzaab.reset();
            setOnTouchListener((View.OnTouchListener) null);
            setOnClickListener((View.OnClickListener) null);
            this.zzaai = true;
            this.zzaaj = false;
            this.zzaak = null;
            zzd(zzgl);
            this.zzaal = false;
            this.zzaao = 0;
            zzw.zzdj().zze(this);
            zzmj();
        }
    }

    public void zza(zzcy.zza zza2) {
        synchronized (this.zzrJ) {
            this.zzaal = zza2.zzxl;
        }
        zzP(zza2.zzxl);
    }

    public void zza(zzeg zzeg) {
        synchronized (this.zzrJ) {
            this.zzus = zzeg;
            requestLayout();
        }
    }

    public void zza(zzrb zzrb) {
        synchronized (this.zzrJ) {
            if (this.zzaak != null) {
                zzpk.e("Attempt to create multiple AdWebViewVideoControllers.");
            } else {
                this.zzaak = zzrb;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(Boolean bool) {
        synchronized (this.zzrJ) {
            this.zzWO = bool;
        }
        zzw.zzcQ().zza(bool);
    }

    /* access modifiers changed from: protected */
    @TargetApi(19)
    public void zza(String str, ValueCallback<String> valueCallback) {
        synchronized (this.zzrJ) {
            if (!isDestroyed()) {
                evaluateJavascript(str, valueCallback);
            } else {
                zzpk.zzbh("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object) null);
                }
            }
        }
    }

    public void zza(String str, zzid zzid) {
        if (this.zzaab != null) {
            this.zzaab.zza(str, zzid);
        }
    }

    public void zza(String str, Map<String, ?> map) {
        try {
            zzb(str, zzw.zzcM().zzQ(map));
        } catch (JSONException unused) {
            zzpk.zzbh("Could not convert parameters to JSON.");
        }
    }

    public void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzj(str, jSONObject.toString());
    }

    public void zzb(zze zze) {
        synchronized (this.zzrJ) {
            this.zzaac = zze;
        }
    }

    public void zzb(zzgy zzgy) {
        synchronized (this.zzrJ) {
            this.zzaan = zzgy;
        }
    }

    public void zzb(String str, zzid zzid) {
        if (this.zzaab != null) {
            this.zzaab.zzb(str, zzid);
        }
    }

    public void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String valueOf = String.valueOf(sb.toString());
        zzpk.zzbf(valueOf.length() != 0 ? "Dispatching AFMA event: ".concat(valueOf) : new String("Dispatching AFMA event: "));
        zzbm(sb.toString());
    }

    public zzeg zzbC() {
        zzeg zzeg;
        synchronized (this.zzrJ) {
            zzeg = this.zzus;
        }
        return zzeg;
    }

    public void zzbV() {
        synchronized (this.zzrJ) {
            this.zzaaj = true;
            if (this.zzaaa != null) {
                this.zzaaa.zzbV();
            }
        }
    }

    public void zzbW() {
        synchronized (this.zzrJ) {
            this.zzaaj = false;
            if (this.zzaaa != null) {
                this.zzaaa.zzbW();
            }
        }
    }

    public void zzbi(String str) {
        synchronized (this.zzrJ) {
            try {
                super.loadUrl(str);
            } catch (Throwable th) {
                zzw.zzcQ().zza(th, "AdWebViewImpl.loadUrlUnsafe");
                zzpk.zzc("Could not call loadUrl. ", th);
            }
        }
    }

    public void zzbj(String str) {
        synchronized (this.zzrJ) {
            if (str == null) {
                str = "";
            }
            this.zzOV = str;
        }
    }

    /* access modifiers changed from: protected */
    public void zzbl(String str) {
        synchronized (this.zzrJ) {
            if (!isDestroyed()) {
                loadUrl(str);
            } else {
                zzpk.zzbh("The webview is destroyed. Ignoring action.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbm(String str) {
        String str2;
        String str3;
        String str4;
        if (zzt.zzzl()) {
            if (zzkq() == null) {
                zzmc();
            }
            if (zzkq().booleanValue()) {
                zza(str, (ValueCallback<String>) null);
                return;
            }
            str4 = "javascript:";
            str3 = String.valueOf(str);
            if (str3.length() == 0) {
                str2 = new String(str4);
                zzbl(str2);
            }
        } else {
            str4 = "javascript:";
            str3 = String.valueOf(str);
            if (str3.length() == 0) {
                str2 = new String(str4);
                zzbl(str2);
            }
        }
        str2 = str4.concat(str3);
        zzbl(str2);
    }

    public com.google.android.gms.ads.internal.zze zzby() {
        return this.zzsv;
    }

    public void zzc(zze zze) {
        synchronized (this.zzrJ) {
            this.zzaau = zze;
        }
    }

    public void zzhK() {
        if (this.zzaaq == null) {
            zzgh.zza(this.zzaas.zzfA(), this.zzOW, "aes2");
            this.zzaaq = zzgh.zzb(this.zzaas.zzfA());
            this.zzaas.zza("native:view_show", this.zzaaq);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zztt.zzba);
        zza("onshow", (Map<String, ?>) hashMap);
    }

    public void zzj(String str, String str2) {
        StringBuilder sb = new StringBuilder(3 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append(str);
        sb.append("(");
        sb.append(str2);
        sb.append(");");
        zzbm(sb.toString());
    }

    /* access modifiers changed from: package-private */
    public Boolean zzkq() {
        Boolean bool;
        synchronized (this.zzrJ) {
            bool = this.zzWO;
        }
        return bool;
    }

    public void zzlA() {
        synchronized (this.zzrJ) {
            zzpk.v("Destroying WebView!");
            zzkC();
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzra.super.destroy();
                }
            });
        }
    }

    public boolean zzlB() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaai;
        }
        return z;
    }

    public boolean zzlC() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaaj;
        }
        return z;
    }

    public zzqv zzlD() {
        return null;
    }

    public zzgj zzlE() {
        return this.zzOW;
    }

    public zzgk zzlF() {
        return this.zzaas;
    }

    public zzrb zzlG() {
        zzrb zzrb;
        synchronized (this.zzrJ) {
            zzrb = this.zzaak;
        }
        return zzrb;
    }

    public boolean zzlH() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaao > 0;
        }
        return z;
    }

    public void zzlI() {
        this.zzvY.zzle();
    }

    public void zzlJ() {
        if (this.zzaar == null) {
            this.zzaar = zzgh.zzb(this.zzaas.zzfA());
            this.zzaas.zza("native:view_load", this.zzaar);
        }
    }

    public View.OnClickListener zzlK() {
        return (View.OnClickListener) this.zzaat.get();
    }

    public zzgy zzlL() {
        zzgy zzgy;
        synchronized (this.zzrJ) {
            zzgy = this.zzaan;
        }
        return zzgy;
    }

    public void zzlM() {
        setBackgroundColor(0);
    }

    public void zzlp() {
        zzmd();
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zztt.zzba);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public void zzlq() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzw.zzcM().zzcs()));
        hashMap.put("app_volume", String.valueOf(zzw.zzcM().zzcq()));
        hashMap.put("device_volume", String.valueOf(zzw.zzcM().zzS(getContext())));
        zza("volume", (Map<String, ?>) hashMap);
    }

    public Activity zzlr() {
        return this.zzZZ.zzlr();
    }

    public Context zzls() {
        return this.zzZZ.zzls();
    }

    public zze zzlt() {
        zze zze;
        synchronized (this.zzrJ) {
            zze = this.zzaac;
        }
        return zze;
    }

    public zze zzlu() {
        zze zze;
        synchronized (this.zzrJ) {
            zze = this.zzaau;
        }
        return zze;
    }

    public zzqx zzlv() {
        return this.zzaab;
    }

    public boolean zzlw() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaad;
        }
        return z;
    }

    public zzaw zzlx() {
        return this.zzGP;
    }

    public zzqh zzly() {
        return this.zztt;
    }

    public boolean zzlz() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaaf;
        }
        return z;
    }

    public boolean zzmb() {
        int i;
        int i2;
        boolean z = false;
        if (!zzlv().zzdD() && !zzlv().zzlO()) {
            return false;
        }
        DisplayMetrics zza2 = zzw.zzcM().zza(this.zzwo);
        int zzb = zzel.zzeT().zzb(zza2, zza2.widthPixels);
        int zzb2 = zzel.zzeT().zzb(zza2, zza2.heightPixels);
        Activity zzlr = zzlr();
        if (zzlr == null || zzlr.getWindow() == null) {
            i2 = zzb;
            i = zzb2;
        } else {
            int[] zzh = zzw.zzcM().zzh(zzlr);
            int zzb3 = zzel.zzeT().zzb(zza2, zzh[0]);
            i = zzel.zzeT().zzb(zza2, zzh[1]);
            i2 = zzb3;
        }
        if (this.zzMy == zzb && this.zzMz == zzb2 && this.zzMB == i2 && this.zzMC == i) {
            return false;
        }
        if (!(this.zzMy == zzb && this.zzMz == zzb2)) {
            z = true;
        }
        this.zzMy = zzb;
        this.zzMz = zzb2;
        this.zzMB = i2;
        this.zzMC = i;
        new zzkw(this).zza(zzb, zzb2, i2, i, zza2.density, this.zzwo.getDefaultDisplay().getRotation());
        return z;
    }

    /* access modifiers changed from: package-private */
    public zzid zzmf() {
        return new zzid() {
            public void zza(zzqw zzqw, Map<String, String> map) {
                if (map != null) {
                    String str = map.get("height");
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            int parseInt = Integer.parseInt(str);
                            synchronized (zzra.this.zzrJ) {
                                if (zzra.this.zzaap != parseInt) {
                                    int unused = zzra.this.zzaap = parseInt;
                                    zzra.this.requestLayout();
                                }
                            }
                        } catch (Exception e) {
                            zzpk.zzc("Exception occurred while getting webview content height", e);
                        }
                    }
                }
            }
        };
    }
}
