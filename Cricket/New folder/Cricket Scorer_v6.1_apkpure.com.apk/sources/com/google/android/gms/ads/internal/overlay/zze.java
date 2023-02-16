package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzkz;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.internal.zzqw;
import java.util.Collections;
import java.util.Map;

@zzme
public class zze extends zzkz.zza implements zzv {
    static final int zzNf = Color.argb(0, 0, 0, 0);
    /* access modifiers changed from: private */
    public final Activity mActivity;
    zzqw zzIs;
    AdOverlayInfoParcel zzNg;
    zzc zzNh;
    zzp zzNi;
    boolean zzNj = false;
    FrameLayout zzNk;
    WebChromeClient.CustomViewCallback zzNl;
    boolean zzNm = false;
    boolean zzNn = false;
    zzb zzNo;
    boolean zzNp = false;
    int zzNq = 0;
    zzm zzNr;
    private final Object zzNs = new Object();
    private Runnable zzNt;
    private boolean zzNu;
    private boolean zzNv;
    private boolean zzNw = false;
    private boolean zzNx = false;
    private boolean zzNy = true;

    @zzme
    private static final class zza extends Exception {
        public zza(String str) {
            super(str);
        }
    }

    @zzme
    static class zzb extends RelativeLayout {
        boolean zzNA;
        zzpr zzvX;

        public zzb(Context context, String str, String str2) {
            super(context);
            this.zzvX = new zzpr(context, str);
            this.zzvX.zzba(str2);
        }

        /* access modifiers changed from: package-private */
        public void disable() {
            this.zzNA = true;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (this.zzNA) {
                return false;
            }
            this.zzvX.zzg(motionEvent);
            return false;
        }
    }

    @zzme
    public static class zzc {
        public final int index;
        public final ViewGroup parent;
        public final ViewGroup.LayoutParams zzNB;
        public final Context zzqn;

        public zzc(zzqw zzqw) throws zza {
            this.zzNB = zzqw.getLayoutParams();
            ViewParent parent2 = zzqw.getParent();
            this.zzqn = zzqw.zzls();
            if (parent2 == null || !(parent2 instanceof ViewGroup)) {
                throw new zza("Could not get the parent of the WebView for an overlay.");
            }
            this.parent = (ViewGroup) parent2;
            this.index = this.parent.indexOfChild(zzqw.getView());
            this.parent.removeView(zzqw.getView());
            zzqw.zzK(true);
        }
    }

    @zzme
    private class zzd extends zzpj {
        private zzd() {
        }

        public void onStop() {
        }

        public void zzco() {
            Bitmap zza = zzw.zzdh().zza(Integer.valueOf(zze.this.zzNg.zzNQ.zztP));
            if (zza != null) {
                final Drawable zza2 = zzw.zzcO().zza(zze.this.mActivity, zza, zze.this.zzNg.zzNQ.zztN, zze.this.zzNg.zzNQ.zztO);
                zzpo.zzXC.post(new Runnable() {
                    public void run() {
                        zze.this.mActivity.getWindow().setBackgroundDrawable(zza2);
                    }
                });
            }
        }
    }

    public zze(Activity activity) {
        this.mActivity = activity;
        this.zzNr = new zzt();
    }

    public void close() {
        this.zzNq = 2;
        this.mActivity.finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onBackPressed() {
        this.zzNq = 0;
    }

    public void onCreate(Bundle bundle) {
        Activity activity;
        this.mActivity.requestWindowFeature(1);
        this.zzNm = bundle != null ? bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false) : false;
        try {
            this.zzNg = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
            if (this.zzNg == null) {
                throw new zza("Could not get info for ad overlay.");
            }
            if (this.zzNg.zzvn.zzYX > 7500000) {
                this.zzNq = 3;
            }
            if (this.mActivity.getIntent() != null) {
                this.zzNy = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzNg.zzNQ != null) {
                this.zzNn = this.zzNg.zzNQ.zztK;
            } else {
                this.zzNn = false;
            }
            if (zzgd.zzEa.get().booleanValue() && this.zzNn && this.zzNg.zzNQ.zztP != -1) {
                new zzd().zziP();
            }
            if (bundle == null) {
                if (this.zzNg.zzNG != null && this.zzNy) {
                    this.zzNg.zzNG.zzbO();
                }
                if (!(this.zzNg.zzNN == 1 || this.zzNg.zzNF == null)) {
                    this.zzNg.zzNF.onAdClicked();
                }
            }
            this.zzNo = new zzb(this.mActivity, this.zzNg.zzNP, this.zzNg.zzvn.zzba);
            this.zzNo.setId(1000);
            switch (this.zzNg.zzNN) {
                case 1:
                    break;
                case 2:
                    this.zzNh = new zzc(this.zzNg.zzNH);
                    break;
                case 3:
                    zzA(true);
                    return;
                case 4:
                    if (this.zzNm) {
                        this.zzNq = 3;
                        activity = this.mActivity;
                    } else if (!zzw.zzcJ().zza((Context) this.mActivity, this.zzNg.zzNE, this.zzNg.zzNM)) {
                        this.zzNq = 3;
                        activity = this.mActivity;
                    } else {
                        return;
                    }
                    activity.finish();
                    return;
                default:
                    throw new zza("Could not determine ad overlay type.");
            }
            zzA(false);
        } catch (zza e) {
            zzpk.zzbh(e.getMessage());
            this.zzNq = 3;
            this.mActivity.finish();
        }
    }

    public void onDestroy() {
        if (this.zzIs != null) {
            this.zzNo.removeView(this.zzIs.getView());
        }
        zzhH();
    }

    public void onPause() {
        zzhD();
        if (this.zzNg.zzNG != null) {
            this.zzNg.zzNG.onPause();
        }
        if (!zzgd.zzFu.get().booleanValue() && this.zzIs != null && (!this.mActivity.isFinishing() || this.zzNh == null)) {
            zzw.zzcO().zzl(this.zzIs);
        }
        zzhH();
    }

    public void onRestart() {
    }

    public void onResume() {
        if (this.zzNg != null && this.zzNg.zzNN == 4) {
            if (this.zzNm) {
                this.zzNq = 3;
                this.mActivity.finish();
            } else {
                this.zzNm = true;
            }
        }
        if (this.zzNg.zzNG != null) {
            this.zzNg.zzNG.onResume();
        }
        if (zzgd.zzFu.get().booleanValue()) {
            return;
        }
        if (this.zzIs == null || this.zzIs.isDestroyed()) {
            zzpk.zzbh("The webview does not exist. Ignoring action.");
        } else {
            zzw.zzcO().zzm(this.zzIs);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzNm);
    }

    public void onStart() {
        if (!zzgd.zzFu.get().booleanValue()) {
            return;
        }
        if (this.zzIs == null || this.zzIs.isDestroyed()) {
            zzpk.zzbh("The webview does not exist. Ignoring action.");
        } else {
            zzw.zzcO().zzm(this.zzIs);
        }
    }

    public void onStop() {
        if (zzgd.zzFu.get().booleanValue() && this.zzIs != null && (!this.mActivity.isFinishing() || this.zzNh == null)) {
            zzw.zzcO().zzl(this.zzIs);
        }
        zzhH();
    }

    public void setRequestedOrientation(int i) {
        this.mActivity.setRequestedOrientation(i);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0093, code lost:
        if (r0.mActivity.getResources().getConfiguration().orientation == 1) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b4, code lost:
        if (r0.mActivity.getResources().getConfiguration().orientation == 2) goto L_0x0095;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzA(boolean r18) throws com.google.android.gms.ads.internal.overlay.zze.zza {
        /*
            r17 = this;
            r0 = r17
            boolean r2 = r0.zzNv
            r3 = 1
            if (r2 != 0) goto L_0x000c
            android.app.Activity r2 = r0.mActivity
            r2.requestWindowFeature(r3)
        L_0x000c:
            android.app.Activity r2 = r0.mActivity
            android.view.Window r2 = r2.getWindow()
            if (r2 != 0) goto L_0x001c
            com.google.android.gms.ads.internal.overlay.zze$zza r1 = new com.google.android.gms.ads.internal.overlay.zze$zza
            java.lang.String r2 = "Invalid activity, no window available."
            r1.<init>(r2)
            throw r1
        L_0x001c:
            boolean r4 = com.google.android.gms.common.util.zzt.isAtLeastN()
            if (r4 == 0) goto L_0x0045
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r4 = com.google.android.gms.internal.zzgd.zzFt
            java.lang.Object r4 = r4.get()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0045
            com.google.android.gms.internal.zzpo r4 = com.google.android.gms.ads.internal.zzw.zzcM()
            android.app.Activity r5 = r0.mActivity
            android.app.Activity r6 = r0.mActivity
            android.content.res.Resources r6 = r6.getResources()
            android.content.res.Configuration r6 = r6.getConfiguration()
            boolean r4 = r4.zza((android.app.Activity) r5, (android.content.res.Configuration) r6)
            goto L_0x0046
        L_0x0045:
            r4 = r3
        L_0x0046:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r5 = r0.zzNg
            com.google.android.gms.ads.internal.zzn r5 = r5.zzNQ
            r6 = 0
            if (r5 == 0) goto L_0x0057
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r5 = r0.zzNg
            com.google.android.gms.ads.internal.zzn r5 = r5.zzNQ
            boolean r5 = r5.zztL
            if (r5 == 0) goto L_0x0057
            r5 = r3
            goto L_0x0058
        L_0x0057:
            r5 = r6
        L_0x0058:
            boolean r7 = r0.zzNn
            if (r7 == 0) goto L_0x005e
            if (r5 == 0) goto L_0x0065
        L_0x005e:
            if (r4 == 0) goto L_0x0065
            r4 = 1024(0x400, float:1.435E-42)
            r2.setFlags(r4, r4)
        L_0x0065:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r4 = r0.zzNg
            com.google.android.gms.internal.zzqw r4 = r4.zzNH
            com.google.android.gms.internal.zzqx r4 = r4.zzlv()
            if (r4 == 0) goto L_0x0074
            boolean r4 = r4.zzdD()
            goto L_0x0075
        L_0x0074:
            r4 = r6
        L_0x0075:
            r0.zzNp = r6
            if (r4 == 0) goto L_0x00b7
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r5 = r0.zzNg
            int r5 = r5.orientation
            com.google.android.gms.internal.zzpp r7 = com.google.android.gms.ads.internal.zzw.zzcO()
            int r7 = r7.zzkQ()
            if (r5 != r7) goto L_0x0099
            android.app.Activity r5 = r0.mActivity
            android.content.res.Resources r5 = r5.getResources()
            android.content.res.Configuration r5 = r5.getConfiguration()
            int r5 = r5.orientation
            if (r5 != r3) goto L_0x0096
        L_0x0095:
            r6 = r3
        L_0x0096:
            r0.zzNp = r6
            goto L_0x00b7
        L_0x0099:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r5 = r0.zzNg
            int r5 = r5.orientation
            com.google.android.gms.internal.zzpp r7 = com.google.android.gms.ads.internal.zzw.zzcO()
            int r7 = r7.zzkR()
            if (r5 != r7) goto L_0x00b7
            android.app.Activity r5 = r0.mActivity
            android.content.res.Resources r5 = r5.getResources()
            android.content.res.Configuration r5 = r5.getConfiguration()
            int r5 = r5.orientation
            r7 = 2
            if (r5 != r7) goto L_0x0096
            goto L_0x0095
        L_0x00b7:
            boolean r5 = r0.zzNp
            r6 = 46
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.String r6 = "Delay onShow to next orientation change: "
            r7.append(r6)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.google.android.gms.internal.zzpk.zzbf(r5)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r5 = r0.zzNg
            int r5 = r5.orientation
            r0.setRequestedOrientation(r5)
            com.google.android.gms.internal.zzpp r5 = com.google.android.gms.ads.internal.zzw.zzcO()
            boolean r2 = r5.zza((android.view.Window) r2)
            if (r2 == 0) goto L_0x00e5
            java.lang.String r2 = "Hardware acceleration on the AdActivity window enabled."
            com.google.android.gms.internal.zzpk.zzbf(r2)
        L_0x00e5:
            boolean r2 = r0.zzNn
            if (r2 != 0) goto L_0x00f1
            com.google.android.gms.ads.internal.overlay.zze$zzb r2 = r0.zzNo
            r5 = -16777216(0xffffffffff000000, float:-1.7014118E38)
        L_0x00ed:
            r2.setBackgroundColor(r5)
            goto L_0x00f6
        L_0x00f1:
            com.google.android.gms.ads.internal.overlay.zze$zzb r2 = r0.zzNo
            int r5 = zzNf
            goto L_0x00ed
        L_0x00f6:
            android.app.Activity r2 = r0.mActivity
            com.google.android.gms.ads.internal.overlay.zze$zzb r5 = r0.zzNo
            r2.setContentView(r5)
            r17.zzbo()
            if (r18 == 0) goto L_0x0196
            com.google.android.gms.internal.zzqy r7 = com.google.android.gms.ads.internal.zzw.zzcN()
            android.app.Activity r8 = r0.mActivity
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.internal.zzqw r2 = r2.zzNH
            com.google.android.gms.internal.zzeg r9 = r2.zzbC()
            r10 = 1
            r12 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.internal.zzqh r13 = r2.zzvn
            r14 = 0
            r15 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.internal.zzqw r2 = r2.zzNH
            com.google.android.gms.ads.internal.zze r16 = r2.zzby()
            r11 = r4
            com.google.android.gms.internal.zzqw r2 = r7.zza(r8, r9, r10, r11, r12, r13, r14, r15, r16)
            r0.zzIs = r2
            com.google.android.gms.internal.zzqw r2 = r0.zzIs
            com.google.android.gms.internal.zzqx r5 = r2.zzlv()
            r6 = 0
            r7 = 0
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.internal.zzhz r8 = r2.zzNI
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.ads.internal.overlay.zzq r9 = r2.zzNM
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.internal.zzif r11 = r2.zzNO
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.internal.zzqw r2 = r2.zzNH
            com.google.android.gms.internal.zzqx r2 = r2.zzlv()
            com.google.android.gms.ads.internal.zzf r13 = r2.zzlN()
            r5.zza(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            com.google.android.gms.internal.zzqw r2 = r0.zzIs
            com.google.android.gms.internal.zzqx r2 = r2.zzlv()
            com.google.android.gms.ads.internal.overlay.zze$1 r5 = new com.google.android.gms.ads.internal.overlay.zze$1
            r5.<init>(r0)
            r2.zza((com.google.android.gms.internal.zzqx.zza) r5)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            java.lang.String r2 = r2.url
            if (r2 == 0) goto L_0x0168
            com.google.android.gms.internal.zzqw r2 = r0.zzIs
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r5 = r0.zzNg
            java.lang.String r5 = r5.url
            r2.loadUrl(r5)
            goto L_0x0180
        L_0x0168:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            java.lang.String r2 = r2.zzNL
            if (r2 == 0) goto L_0x018e
            com.google.android.gms.internal.zzqw r5 = r0.zzIs
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            java.lang.String r6 = r2.zzNJ
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            java.lang.String r7 = r2.zzNL
            java.lang.String r8 = "text/html"
            java.lang.String r9 = "UTF-8"
            r10 = 0
            r5.loadDataWithBaseURL(r6, r7, r8, r9, r10)
        L_0x0180:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.internal.zzqw r2 = r2.zzNH
            if (r2 == 0) goto L_0x01a3
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.internal.zzqw r2 = r2.zzNH
            r2.zzc(r0)
            goto L_0x01a3
        L_0x018e:
            com.google.android.gms.ads.internal.overlay.zze$zza r1 = new com.google.android.gms.ads.internal.overlay.zze$zza
            java.lang.String r2 = "No URL or HTML to display in ad overlay."
            r1.<init>(r2)
            throw r1
        L_0x0196:
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r2 = r0.zzNg
            com.google.android.gms.internal.zzqw r2 = r2.zzNH
            r0.zzIs = r2
            com.google.android.gms.internal.zzqw r2 = r0.zzIs
            android.app.Activity r5 = r0.mActivity
            r2.setContext(r5)
        L_0x01a3:
            com.google.android.gms.internal.zzqw r2 = r0.zzIs
            r2.zzb((com.google.android.gms.ads.internal.overlay.zze) r0)
            com.google.android.gms.internal.zzqw r2 = r0.zzIs
            android.view.ViewParent r2 = r2.getParent()
            if (r2 == 0) goto L_0x01bf
            boolean r5 = r2 instanceof android.view.ViewGroup
            if (r5 == 0) goto L_0x01bf
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            com.google.android.gms.internal.zzqw r5 = r0.zzIs
            android.view.View r5 = r5.getView()
            r2.removeView(r5)
        L_0x01bf:
            boolean r2 = r0.zzNn
            if (r2 == 0) goto L_0x01c8
            com.google.android.gms.internal.zzqw r2 = r0.zzIs
            r2.zzlM()
        L_0x01c8:
            com.google.android.gms.ads.internal.overlay.zze$zzb r2 = r0.zzNo
            com.google.android.gms.internal.zzqw r5 = r0.zzIs
            android.view.View r5 = r5.getView()
            r6 = -1
            r2.addView(r5, r6, r6)
            if (r18 != 0) goto L_0x01dd
            boolean r1 = r0.zzNp
            if (r1 != 0) goto L_0x01dd
            r17.zzhK()
        L_0x01dd:
            r0.zzz(r4)
            com.google.android.gms.internal.zzqw r1 = r0.zzIs
            boolean r1 = r1.zzlw()
            if (r1 == 0) goto L_0x01eb
            r0.zza((boolean) r4, (boolean) r3)
        L_0x01eb:
            com.google.android.gms.internal.zzqw r1 = r0.zzIs
            com.google.android.gms.ads.internal.zze r1 = r1.zzby()
            if (r1 == 0) goto L_0x01f6
            com.google.android.gms.ads.internal.overlay.zzn r1 = r1.zzsO
            goto L_0x01f7
        L_0x01f6:
            r1 = 0
        L_0x01f7:
            if (r1 == 0) goto L_0x0206
            android.app.Activity r2 = r0.mActivity
            com.google.android.gms.internal.zzqw r3 = r0.zzIs
            com.google.android.gms.ads.internal.overlay.zze$zzb r4 = r0.zzNo
            com.google.android.gms.ads.internal.overlay.zzm r1 = r1.zza(r2, r3, r4)
            r0.zzNr = r1
            return
        L_0x0206:
            java.lang.String r1 = "Appstreaming controller is null."
            com.google.android.gms.internal.zzpk.zzbh(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zze.zzA(boolean):void");
    }

    /* access modifiers changed from: protected */
    public void zzM(int i) {
        this.zzIs.zzM(i);
    }

    public void zza(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.zzNk = new FrameLayout(this.mActivity);
        this.zzNk.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzNk.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzNk);
        zzbo();
        this.zzNl = customViewCallback;
        this.zzNj = true;
    }

    public void zza(boolean z, boolean z2) {
        if (this.zzNi != null) {
            this.zzNi.zza(z, z2);
        }
    }

    public void zzbo() {
        this.zzNv = true;
    }

    public void zzg(zzqw zzqw, Map<String, String> map) {
    }

    public void zzhD() {
        if (this.zzNg != null && this.zzNj) {
            setRequestedOrientation(this.zzNg.orientation);
        }
        if (this.zzNk != null) {
            this.mActivity.setContentView(this.zzNo);
            zzbo();
            this.zzNk.removeAllViews();
            this.zzNk = null;
        }
        if (this.zzNl != null) {
            this.zzNl.onCustomViewHidden();
            this.zzNl = null;
        }
        this.zzNj = false;
    }

    public void zzhE() {
        this.zzNq = 1;
        this.mActivity.finish();
    }

    public boolean zzhF() {
        this.zzNq = 0;
        if (this.zzIs == null) {
            return true;
        }
        boolean zzlB = this.zzIs.zzlB();
        if (!zzlB) {
            this.zzIs.zza("onbackblocked", (Map<String, ?>) Collections.emptyMap());
        }
        return zzlB;
    }

    public void zzhG() {
        this.zzNo.removeView(this.zzNi);
        zzz(true);
    }

    /* access modifiers changed from: protected */
    public void zzhH() {
        if (this.mActivity.isFinishing() && !this.zzNw) {
            this.zzNw = true;
            if (this.zzIs != null) {
                zzM(this.zzNq);
                synchronized (this.zzNs) {
                    if (!this.zzNu && this.zzIs.zzlH()) {
                        this.zzNt = new Runnable() {
                            public void run() {
                                zze.this.zzhI();
                            }
                        };
                        zzpo.zzXC.postDelayed(this.zzNt, zzgd.zzCY.get().longValue());
                        return;
                    }
                }
            }
            zzhI();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzhI() {
        if (!this.zzNx) {
            this.zzNx = true;
            if (this.zzIs != null) {
                this.zzNo.removeView(this.zzIs.getView());
                if (this.zzNh != null) {
                    this.zzIs.setContext(this.zzNh.zzqn);
                    this.zzIs.zzK(false);
                    this.zzNh.parent.addView(this.zzIs.getView(), this.zzNh.index, this.zzNh.zzNB);
                    this.zzNh = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzIs.setContext(this.mActivity.getApplicationContext());
                }
                this.zzIs = null;
            }
            if (this.zzNg != null && this.zzNg.zzNG != null) {
                this.zzNg.zzNG.zzbN();
            }
        }
    }

    public void zzhJ() {
        if (this.zzNp) {
            this.zzNp = false;
            zzhK();
        }
    }

    /* access modifiers changed from: protected */
    public void zzhK() {
        this.zzIs.zzhK();
    }

    public void zzhL() {
        this.zzNo.disable();
    }

    public void zzhM() {
        synchronized (this.zzNs) {
            this.zzNu = true;
            if (this.zzNt != null) {
                zzpo.zzXC.removeCallbacks(this.zzNt);
                zzpo.zzXC.post(this.zzNt);
            }
        }
    }

    public void zzo(IObjectWrapper iObjectWrapper) {
        if (zzgd.zzFt.get().booleanValue() && zzt.isAtLeastN()) {
            if (zzw.zzcM().zza(this.mActivity, (Configuration) com.google.android.gms.dynamic.zzd.zzF(iObjectWrapper))) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(1024);
        }
    }

    public void zzz(boolean z) {
        int intValue = zzgd.zzFv.get().intValue();
        zzp.zza zza2 = new zzp.zza();
        zza2.size = 50;
        zza2.paddingLeft = z ? intValue : 0;
        zza2.paddingRight = z ? 0 : intValue;
        zza2.paddingTop = 0;
        zza2.paddingBottom = intValue;
        this.zzNi = new zzp(this.mActivity, zza2, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.zzNi.zza(z, this.zzNg.zzNK);
        this.zzNo.addView(this.zzNi, layoutParams);
    }
}
