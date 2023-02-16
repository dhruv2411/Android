package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.zzf;
import java.util.Map;
import java.util.Set;

@zzme
public class zzkr extends zzkw {
    static final Set<String> zzLY = zzf.zzc("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    private final zzqw zzIs;
    private final Activity zzLO;
    private String zzLZ = "top-right";
    private boolean zzMa = true;
    private int zzMb = 0;
    private int zzMc = 0;
    private int zzMd = 0;
    private int zzMe = 0;
    private ImageView zzMf;
    private LinearLayout zzMg;
    private zzkx zzMh;
    private PopupWindow zzMi;
    private RelativeLayout zzMj;
    private ViewGroup zzMk;
    private int zzrC = -1;
    private int zzrD = -1;
    private final Object zzrJ = new Object();
    private zzeg zzus;

    public zzkr(zzqw zzqw, zzkx zzkx) {
        super(zzqw, "resize");
        this.zzIs = zzqw;
        this.zzLO = zzqw.zzlr();
        this.zzMh = zzkx;
    }

    private int[] zzhl() {
        if (!zzhn()) {
            return null;
        }
        if (this.zzMa) {
            return new int[]{this.zzMb + this.zzMd, this.zzMc + this.zzMe};
        }
        int[] zzi = zzw.zzcM().zzi(this.zzLO);
        int[] zzk = zzw.zzcM().zzk(this.zzLO);
        int i = zzi[0];
        int i2 = this.zzMb + this.zzMd;
        int i3 = this.zzMc + this.zzMe;
        int i4 = i2 < 0 ? 0 : this.zzrC + i2 > i ? i - this.zzrC : i2;
        if (i3 < zzk[0]) {
            i3 = zzk[0];
        } else if (this.zzrD + i3 > zzk[1]) {
            i3 = zzk[1] - this.zzrD;
        }
        return new int[]{i4, i3};
    }

    private void zzj(Map<String, String> map) {
        if (!TextUtils.isEmpty(map.get("width"))) {
            this.zzrC = zzw.zzcM().zzaY(map.get("width"));
        }
        if (!TextUtils.isEmpty(map.get("height"))) {
            this.zzrD = zzw.zzcM().zzaY(map.get("height"));
        }
        if (!TextUtils.isEmpty(map.get("offsetX"))) {
            this.zzMd = zzw.zzcM().zzaY(map.get("offsetX"));
        }
        if (!TextUtils.isEmpty(map.get("offsetY"))) {
            this.zzMe = zzw.zzcM().zzaY(map.get("offsetY"));
        }
        if (!TextUtils.isEmpty(map.get("allowOffscreen"))) {
            this.zzMa = Boolean.parseBoolean(map.get("allowOffscreen"));
        }
        String str = map.get("customClosePosition");
        if (!TextUtils.isEmpty(str)) {
            this.zzLZ = str;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x019a, code lost:
        r2.addRule(11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01a1, code lost:
        r2.addRule(14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01a8, code lost:
        r2.addRule(9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01ba, code lost:
        r11.zzMg.setOnClickListener(new com.google.android.gms.internal.zzkr.AnonymousClass1(r11));
        r11.zzMg.setContentDescription("Close button");
        r11.zzMj.addView(r11.zzMg, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        r11.zzMi.showAtLocation(r12.getDecorView(), 0, com.google.android.gms.internal.zzel.zzeT().zzb((android.content.Context) r11.zzLO, r1[0]), com.google.android.gms.internal.zzel.zzeT().zzb((android.content.Context) r11.zzLO, r1[1]));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        zzc(r1[0], r1[1]);
        r11.zzIs.zza(new com.google.android.gms.internal.zzeg((android.content.Context) r11.zzLO, new com.google.android.gms.ads.AdSize(r11.zzrC, r11.zzrD)));
        zzd(r1[0], r1[1]);
        zzaB("resized");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x021c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x021d, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x021e, code lost:
        r12 = java.lang.String.valueOf(r12.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x022c, code lost:
        if (r12.length() != 0) goto L_0x022e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x022e, code lost:
        r12 = "Cannot show popup window: ".concat(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0233, code lost:
        r12 = new java.lang.String("Cannot show popup window: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0238, code lost:
        zzaz(r12);
        r11.zzMj.removeView(r11.zzIs.getView());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0248, code lost:
        if (r11.zzMk != null) goto L_0x024a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x024a, code lost:
        r11.zzMk.removeView(r11.zzMf);
        r11.zzMk.addView(r11.zzIs.getView());
        r11.zzIs.zza(r11.zzus);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0264, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.zzrJ
            monitor-enter(r0)
            android.app.Activity r1 = r11.zzLO     // Catch:{ all -> 0x0273 }
            if (r1 != 0) goto L_0x000e
            java.lang.String r12 = "Not an activity context. Cannot resize."
            r11.zzaz(r12)     // Catch:{ all -> 0x0273 }
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x000e:
            com.google.android.gms.internal.zzqw r1 = r11.zzIs     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzeg r1 = r1.zzbC()     // Catch:{ all -> 0x0273 }
            if (r1 != 0) goto L_0x001d
            java.lang.String r12 = "Webview is not yet available, size is not set."
            r11.zzaz(r12)     // Catch:{ all -> 0x0273 }
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x001d:
            com.google.android.gms.internal.zzqw r1 = r11.zzIs     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzeg r1 = r1.zzbC()     // Catch:{ all -> 0x0273 }
            boolean r1 = r1.zzzz     // Catch:{ all -> 0x0273 }
            if (r1 == 0) goto L_0x002e
            java.lang.String r12 = "Is interstitial. Cannot resize an interstitial."
            r11.zzaz(r12)     // Catch:{ all -> 0x0273 }
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x002e:
            com.google.android.gms.internal.zzqw r1 = r11.zzIs     // Catch:{ all -> 0x0273 }
            boolean r1 = r1.zzlz()     // Catch:{ all -> 0x0273 }
            if (r1 == 0) goto L_0x003d
            java.lang.String r12 = "Cannot resize an expanded banner."
            r11.zzaz(r12)     // Catch:{ all -> 0x0273 }
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x003d:
            r11.zzj(r12)     // Catch:{ all -> 0x0273 }
            boolean r12 = r11.zzhk()     // Catch:{ all -> 0x0273 }
            if (r12 != 0) goto L_0x004d
            java.lang.String r12 = "Invalid width and height options. Cannot resize."
            r11.zzaz(r12)     // Catch:{ all -> 0x0273 }
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x004d:
            android.app.Activity r12 = r11.zzLO     // Catch:{ all -> 0x0273 }
            android.view.Window r12 = r12.getWindow()     // Catch:{ all -> 0x0273 }
            if (r12 == 0) goto L_0x026c
            android.view.View r1 = r12.getDecorView()     // Catch:{ all -> 0x0273 }
            if (r1 != 0) goto L_0x005d
            goto L_0x026c
        L_0x005d:
            int[] r1 = r11.zzhl()     // Catch:{ all -> 0x0273 }
            if (r1 != 0) goto L_0x006a
            java.lang.String r12 = "Resize location out of screen or close button is not visible."
            r11.zzaz(r12)     // Catch:{ all -> 0x0273 }
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x006a:
            com.google.android.gms.internal.zzqe r2 = com.google.android.gms.internal.zzel.zzeT()     // Catch:{ all -> 0x0273 }
            android.app.Activity r3 = r11.zzLO     // Catch:{ all -> 0x0273 }
            int r4 = r11.zzrC     // Catch:{ all -> 0x0273 }
            int r2 = r2.zzb((android.content.Context) r3, (int) r4)     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqe r3 = com.google.android.gms.internal.zzel.zzeT()     // Catch:{ all -> 0x0273 }
            android.app.Activity r4 = r11.zzLO     // Catch:{ all -> 0x0273 }
            int r5 = r11.zzrD     // Catch:{ all -> 0x0273 }
            int r3 = r3.zzb((android.content.Context) r4, (int) r5)     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqw r4 = r11.zzIs     // Catch:{ all -> 0x0273 }
            android.view.View r4 = r4.getView()     // Catch:{ all -> 0x0273 }
            android.view.ViewParent r4 = r4.getParent()     // Catch:{ all -> 0x0273 }
            if (r4 == 0) goto L_0x0265
            boolean r5 = r4 instanceof android.view.ViewGroup     // Catch:{ all -> 0x0273 }
            if (r5 == 0) goto L_0x0265
            r5 = r4
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqw r6 = r11.zzIs     // Catch:{ all -> 0x0273 }
            android.view.View r6 = r6.getView()     // Catch:{ all -> 0x0273 }
            r5.removeView(r6)     // Catch:{ all -> 0x0273 }
            android.widget.PopupWindow r5 = r11.zzMi     // Catch:{ all -> 0x0273 }
            if (r5 != 0) goto L_0x00d2
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4     // Catch:{ all -> 0x0273 }
            r11.zzMk = r4     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzpo r4 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqw r5 = r11.zzIs     // Catch:{ all -> 0x0273 }
            android.view.View r5 = r5.getView()     // Catch:{ all -> 0x0273 }
            android.graphics.Bitmap r4 = r4.zzp(r5)     // Catch:{ all -> 0x0273 }
            android.widget.ImageView r5 = new android.widget.ImageView     // Catch:{ all -> 0x0273 }
            android.app.Activity r6 = r11.zzLO     // Catch:{ all -> 0x0273 }
            r5.<init>(r6)     // Catch:{ all -> 0x0273 }
            r11.zzMf = r5     // Catch:{ all -> 0x0273 }
            android.widget.ImageView r5 = r11.zzMf     // Catch:{ all -> 0x0273 }
            r5.setImageBitmap(r4)     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqw r4 = r11.zzIs     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzeg r4 = r4.zzbC()     // Catch:{ all -> 0x0273 }
            r11.zzus = r4     // Catch:{ all -> 0x0273 }
            android.view.ViewGroup r4 = r11.zzMk     // Catch:{ all -> 0x0273 }
            android.widget.ImageView r5 = r11.zzMf     // Catch:{ all -> 0x0273 }
            r4.addView(r5)     // Catch:{ all -> 0x0273 }
            goto L_0x00d7
        L_0x00d2:
            android.widget.PopupWindow r4 = r11.zzMi     // Catch:{ all -> 0x0273 }
            r4.dismiss()     // Catch:{ all -> 0x0273 }
        L_0x00d7:
            android.widget.RelativeLayout r4 = new android.widget.RelativeLayout     // Catch:{ all -> 0x0273 }
            android.app.Activity r5 = r11.zzLO     // Catch:{ all -> 0x0273 }
            r4.<init>(r5)     // Catch:{ all -> 0x0273 }
            r11.zzMj = r4     // Catch:{ all -> 0x0273 }
            android.widget.RelativeLayout r4 = r11.zzMj     // Catch:{ all -> 0x0273 }
            r5 = 0
            r4.setBackgroundColor(r5)     // Catch:{ all -> 0x0273 }
            android.widget.RelativeLayout r4 = r11.zzMj     // Catch:{ all -> 0x0273 }
            android.view.ViewGroup$LayoutParams r6 = new android.view.ViewGroup$LayoutParams     // Catch:{ all -> 0x0273 }
            r6.<init>(r2, r3)     // Catch:{ all -> 0x0273 }
            r4.setLayoutParams(r6)     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzpo r4 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x0273 }
            android.widget.RelativeLayout r6 = r11.zzMj     // Catch:{ all -> 0x0273 }
            android.widget.PopupWindow r2 = r4.zza((android.view.View) r6, (int) r2, (int) r3, (boolean) r5)     // Catch:{ all -> 0x0273 }
            r11.zzMi = r2     // Catch:{ all -> 0x0273 }
            android.widget.PopupWindow r2 = r11.zzMi     // Catch:{ all -> 0x0273 }
            r3 = 1
            r2.setOutsideTouchable(r3)     // Catch:{ all -> 0x0273 }
            android.widget.PopupWindow r2 = r11.zzMi     // Catch:{ all -> 0x0273 }
            r2.setTouchable(r3)     // Catch:{ all -> 0x0273 }
            android.widget.PopupWindow r2 = r11.zzMi     // Catch:{ all -> 0x0273 }
            boolean r4 = r11.zzMa     // Catch:{ all -> 0x0273 }
            r4 = r4 ^ r3
            r2.setClippingEnabled(r4)     // Catch:{ all -> 0x0273 }
            android.widget.RelativeLayout r2 = r11.zzMj     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqw r4 = r11.zzIs     // Catch:{ all -> 0x0273 }
            android.view.View r4 = r4.getView()     // Catch:{ all -> 0x0273 }
            r6 = -1
            r2.addView(r4, r6, r6)     // Catch:{ all -> 0x0273 }
            android.widget.LinearLayout r2 = new android.widget.LinearLayout     // Catch:{ all -> 0x0273 }
            android.app.Activity r4 = r11.zzLO     // Catch:{ all -> 0x0273 }
            r2.<init>(r4)     // Catch:{ all -> 0x0273 }
            r11.zzMg = r2     // Catch:{ all -> 0x0273 }
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqe r4 = com.google.android.gms.internal.zzel.zzeT()     // Catch:{ all -> 0x0273 }
            android.app.Activity r7 = r11.zzLO     // Catch:{ all -> 0x0273 }
            r8 = 50
            int r4 = r4.zzb((android.content.Context) r7, (int) r8)     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqe r7 = com.google.android.gms.internal.zzel.zzeT()     // Catch:{ all -> 0x0273 }
            android.app.Activity r9 = r11.zzLO     // Catch:{ all -> 0x0273 }
            int r7 = r7.zzb((android.content.Context) r9, (int) r8)     // Catch:{ all -> 0x0273 }
            r2.<init>(r4, r7)     // Catch:{ all -> 0x0273 }
            java.lang.String r4 = r11.zzLZ     // Catch:{ all -> 0x0273 }
            int r7 = r4.hashCode()     // Catch:{ all -> 0x0273 }
            switch(r7) {
                case -1364013995: goto L_0x017b;
                case -1012429441: goto L_0x0171;
                case -655373719: goto L_0x0167;
                case 1163912186: goto L_0x015d;
                case 1288627767: goto L_0x0153;
                case 1755462605: goto L_0x0149;
                default: goto L_0x0148;
            }     // Catch:{ all -> 0x0273 }
        L_0x0148:
            goto L_0x0185
        L_0x0149:
            java.lang.String r7 = "top-center"
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0273 }
            if (r4 == 0) goto L_0x0185
            r4 = r3
            goto L_0x0186
        L_0x0153:
            java.lang.String r7 = "bottom-center"
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0273 }
            if (r4 == 0) goto L_0x0185
            r4 = 4
            goto L_0x0186
        L_0x015d:
            java.lang.String r7 = "bottom-right"
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0273 }
            if (r4 == 0) goto L_0x0185
            r4 = 5
            goto L_0x0186
        L_0x0167:
            java.lang.String r7 = "bottom-left"
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0273 }
            if (r4 == 0) goto L_0x0185
            r4 = 3
            goto L_0x0186
        L_0x0171:
            java.lang.String r7 = "top-left"
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0273 }
            if (r4 == 0) goto L_0x0185
            r4 = r5
            goto L_0x0186
        L_0x017b:
            java.lang.String r7 = "center"
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0273 }
            if (r4 == 0) goto L_0x0185
            r4 = 2
            goto L_0x0186
        L_0x0185:
            r4 = r6
        L_0x0186:
            r6 = 9
            r7 = 14
            r8 = 11
            r9 = 12
            r10 = 10
            switch(r4) {
                case 0: goto L_0x01b6;
                case 1: goto L_0x01b2;
                case 2: goto L_0x01ac;
                case 3: goto L_0x01a5;
                case 4: goto L_0x019e;
                case 5: goto L_0x0197;
                default: goto L_0x0193;
            }     // Catch:{ all -> 0x0273 }
        L_0x0193:
            r2.addRule(r10)     // Catch:{ all -> 0x0273 }
            goto L_0x019a
        L_0x0197:
            r2.addRule(r9)     // Catch:{ all -> 0x0273 }
        L_0x019a:
            r2.addRule(r8)     // Catch:{ all -> 0x0273 }
            goto L_0x01ba
        L_0x019e:
            r2.addRule(r9)     // Catch:{ all -> 0x0273 }
        L_0x01a1:
            r2.addRule(r7)     // Catch:{ all -> 0x0273 }
            goto L_0x01ba
        L_0x01a5:
            r2.addRule(r9)     // Catch:{ all -> 0x0273 }
        L_0x01a8:
            r2.addRule(r6)     // Catch:{ all -> 0x0273 }
            goto L_0x01ba
        L_0x01ac:
            r4 = 13
            r2.addRule(r4)     // Catch:{ all -> 0x0273 }
            goto L_0x01ba
        L_0x01b2:
            r2.addRule(r10)     // Catch:{ all -> 0x0273 }
            goto L_0x01a1
        L_0x01b6:
            r2.addRule(r10)     // Catch:{ all -> 0x0273 }
            goto L_0x01a8
        L_0x01ba:
            android.widget.LinearLayout r4 = r11.zzMg     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzkr$1 r6 = new com.google.android.gms.internal.zzkr$1     // Catch:{ all -> 0x0273 }
            r6.<init>()     // Catch:{ all -> 0x0273 }
            r4.setOnClickListener(r6)     // Catch:{ all -> 0x0273 }
            android.widget.LinearLayout r4 = r11.zzMg     // Catch:{ all -> 0x0273 }
            java.lang.String r6 = "Close button"
            r4.setContentDescription(r6)     // Catch:{ all -> 0x0273 }
            android.widget.RelativeLayout r4 = r11.zzMj     // Catch:{ all -> 0x0273 }
            android.widget.LinearLayout r6 = r11.zzMg     // Catch:{ all -> 0x0273 }
            r4.addView(r6, r2)     // Catch:{ all -> 0x0273 }
            android.widget.PopupWindow r2 = r11.zzMi     // Catch:{ RuntimeException -> 0x021d }
            android.view.View r12 = r12.getDecorView()     // Catch:{ RuntimeException -> 0x021d }
            com.google.android.gms.internal.zzqe r4 = com.google.android.gms.internal.zzel.zzeT()     // Catch:{ RuntimeException -> 0x021d }
            android.app.Activity r6 = r11.zzLO     // Catch:{ RuntimeException -> 0x021d }
            r7 = r1[r5]     // Catch:{ RuntimeException -> 0x021d }
            int r4 = r4.zzb((android.content.Context) r6, (int) r7)     // Catch:{ RuntimeException -> 0x021d }
            com.google.android.gms.internal.zzqe r6 = com.google.android.gms.internal.zzel.zzeT()     // Catch:{ RuntimeException -> 0x021d }
            android.app.Activity r7 = r11.zzLO     // Catch:{ RuntimeException -> 0x021d }
            r8 = r1[r3]     // Catch:{ RuntimeException -> 0x021d }
            int r6 = r6.zzb((android.content.Context) r7, (int) r8)     // Catch:{ RuntimeException -> 0x021d }
            r2.showAtLocation(r12, r5, r4, r6)     // Catch:{ RuntimeException -> 0x021d }
            r12 = r1[r5]     // Catch:{ all -> 0x0273 }
            r2 = r1[r3]     // Catch:{ all -> 0x0273 }
            r11.zzc(r12, r2)     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqw r12 = r11.zzIs     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzeg r2 = new com.google.android.gms.internal.zzeg     // Catch:{ all -> 0x0273 }
            android.app.Activity r4 = r11.zzLO     // Catch:{ all -> 0x0273 }
            com.google.android.gms.ads.AdSize r6 = new com.google.android.gms.ads.AdSize     // Catch:{ all -> 0x0273 }
            int r7 = r11.zzrC     // Catch:{ all -> 0x0273 }
            int r8 = r11.zzrD     // Catch:{ all -> 0x0273 }
            r6.<init>(r7, r8)     // Catch:{ all -> 0x0273 }
            r2.<init>((android.content.Context) r4, (com.google.android.gms.ads.AdSize) r6)     // Catch:{ all -> 0x0273 }
            r12.zza((com.google.android.gms.internal.zzeg) r2)     // Catch:{ all -> 0x0273 }
            r12 = r1[r5]     // Catch:{ all -> 0x0273 }
            r1 = r1[r3]     // Catch:{ all -> 0x0273 }
            r11.zzd(r12, r1)     // Catch:{ all -> 0x0273 }
            java.lang.String r12 = "resized"
            r11.zzaB(r12)     // Catch:{ all -> 0x0273 }
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x021d:
            r12 = move-exception
            java.lang.String r1 = "Cannot show popup window: "
            java.lang.String r12 = r12.getMessage()     // Catch:{ all -> 0x0273 }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0273 }
            int r2 = r12.length()     // Catch:{ all -> 0x0273 }
            if (r2 == 0) goto L_0x0233
            java.lang.String r12 = r1.concat(r12)     // Catch:{ all -> 0x0273 }
            goto L_0x0238
        L_0x0233:
            java.lang.String r12 = new java.lang.String     // Catch:{ all -> 0x0273 }
            r12.<init>(r1)     // Catch:{ all -> 0x0273 }
        L_0x0238:
            r11.zzaz(r12)     // Catch:{ all -> 0x0273 }
            android.widget.RelativeLayout r12 = r11.zzMj     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqw r1 = r11.zzIs     // Catch:{ all -> 0x0273 }
            android.view.View r1 = r1.getView()     // Catch:{ all -> 0x0273 }
            r12.removeView(r1)     // Catch:{ all -> 0x0273 }
            android.view.ViewGroup r12 = r11.zzMk     // Catch:{ all -> 0x0273 }
            if (r12 == 0) goto L_0x0263
            android.view.ViewGroup r12 = r11.zzMk     // Catch:{ all -> 0x0273 }
            android.widget.ImageView r1 = r11.zzMf     // Catch:{ all -> 0x0273 }
            r12.removeView(r1)     // Catch:{ all -> 0x0273 }
            android.view.ViewGroup r12 = r11.zzMk     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqw r1 = r11.zzIs     // Catch:{ all -> 0x0273 }
            android.view.View r1 = r1.getView()     // Catch:{ all -> 0x0273 }
            r12.addView(r1)     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzqw r12 = r11.zzIs     // Catch:{ all -> 0x0273 }
            com.google.android.gms.internal.zzeg r1 = r11.zzus     // Catch:{ all -> 0x0273 }
            r12.zza((com.google.android.gms.internal.zzeg) r1)     // Catch:{ all -> 0x0273 }
        L_0x0263:
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x0265:
            java.lang.String r12 = "Webview is detached, probably in the middle of a resize or expand."
            r11.zzaz(r12)     // Catch:{ all -> 0x0273 }
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x026c:
            java.lang.String r12 = "Activity context is not ready, cannot get window or decor view."
            r11.zzaz(r12)     // Catch:{ all -> 0x0273 }
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            return
        L_0x0273:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0273 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzkr.execute(java.util.Map):void");
    }

    public void zza(int i, int i2, boolean z) {
        synchronized (this.zzrJ) {
            this.zzMb = i;
            this.zzMc = i2;
            if (this.zzMi != null && z) {
                int[] zzhl = zzhl();
                if (zzhl != null) {
                    this.zzMi.update(zzel.zzeT().zzb((Context) this.zzLO, zzhl[0]), zzel.zzeT().zzb((Context) this.zzLO, zzhl[1]), this.zzMi.getWidth(), this.zzMi.getHeight());
                    zzd(zzhl[0], zzhl[1]);
                } else {
                    zzs(true);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzc(int i, int i2) {
        if (this.zzMh != null) {
            this.zzMh.zza(i, i2, this.zzrC, this.zzrD);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzd(int i, int i2) {
        zzb(i, i2 - zzw.zzcM().zzk(this.zzLO)[0], this.zzrC, this.zzrD);
    }

    public void zze(int i, int i2) {
        this.zzMb = i;
        this.zzMc = i2;
    }

    /* access modifiers changed from: package-private */
    public boolean zzhk() {
        return this.zzrC > -1 && this.zzrD > -1;
    }

    public boolean zzhm() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzMi != null;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0097, code lost:
        r5 = r9.zzMc + r9.zzMe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b8, code lost:
        r5 = ((r9.zzMc + r9.zzMe) + r9.zzrD) - 50;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00eb, code lost:
        if (r0 < 0) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ee, code lost:
        if ((r0 + 50) > r3) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f2, code lost:
        if (r5 < r1[0]) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f7, code lost:
        if ((r5 + 50) <= r1[1]) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f9, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zzhn() {
        /*
            r9 = this;
            com.google.android.gms.internal.zzpo r0 = com.google.android.gms.ads.internal.zzw.zzcM()
            android.app.Activity r1 = r9.zzLO
            int[] r0 = r0.zzi((android.app.Activity) r1)
            com.google.android.gms.internal.zzpo r1 = com.google.android.gms.ads.internal.zzw.zzcM()
            android.app.Activity r2 = r9.zzLO
            int[] r1 = r1.zzk(r2)
            r2 = 0
            r3 = r0[r2]
            r4 = 1
            r0 = r0[r4]
            int r5 = r9.zzrC
            r6 = 50
            if (r5 < r6) goto L_0x00ff
            int r5 = r9.zzrC
            if (r5 <= r3) goto L_0x0026
            goto L_0x00ff
        L_0x0026:
            int r5 = r9.zzrD
            if (r5 < r6) goto L_0x00fb
            int r5 = r9.zzrD
            if (r5 <= r0) goto L_0x0030
            goto L_0x00fb
        L_0x0030:
            int r5 = r9.zzrD
            if (r5 != r0) goto L_0x003e
            int r0 = r9.zzrC
            if (r0 != r3) goto L_0x003e
            java.lang.String r0 = "Cannot resize to a full-screen ad."
        L_0x003a:
            com.google.android.gms.internal.zzpk.zzbh(r0)
            return r2
        L_0x003e:
            boolean r0 = r9.zzMa
            if (r0 == 0) goto L_0x00fa
            java.lang.String r0 = r9.zzLZ
            r5 = -1
            int r7 = r0.hashCode()
            r8 = 2
            switch(r7) {
                case -1364013995: goto L_0x0080;
                case -1012429441: goto L_0x0076;
                case -655373719: goto L_0x006c;
                case 1163912186: goto L_0x0062;
                case 1288627767: goto L_0x0058;
                case 1755462605: goto L_0x004e;
                default: goto L_0x004d;
            }
        L_0x004d:
            goto L_0x008a
        L_0x004e:
            java.lang.String r7 = "top-center"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x008a
            r0 = r4
            goto L_0x008b
        L_0x0058:
            java.lang.String r7 = "bottom-center"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x008a
            r0 = 4
            goto L_0x008b
        L_0x0062:
            java.lang.String r7 = "bottom-right"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x008a
            r0 = 5
            goto L_0x008b
        L_0x006c:
            java.lang.String r7 = "bottom-left"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x008a
            r0 = 3
            goto L_0x008b
        L_0x0076:
            java.lang.String r7 = "top-left"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x008a
            r0 = r2
            goto L_0x008b
        L_0x0080:
            java.lang.String r7 = "center"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x008a
            r0 = r8
            goto L_0x008b
        L_0x008a:
            r0 = r5
        L_0x008b:
            switch(r0) {
                case 0: goto L_0x00e5;
                case 1: goto L_0x00d9;
                case 2: goto L_0x00c2;
                case 3: goto L_0x00b3;
                case 4: goto L_0x00a7;
                case 5: goto L_0x009d;
                default: goto L_0x008e;
            }
        L_0x008e:
            int r0 = r9.zzMb
            int r5 = r9.zzMd
            int r0 = r0 + r5
            int r5 = r9.zzrC
            int r0 = r0 + r5
            int r0 = r0 - r6
        L_0x0097:
            int r5 = r9.zzMc
            int r7 = r9.zzMe
            int r5 = r5 + r7
            goto L_0x00eb
        L_0x009d:
            int r0 = r9.zzMb
            int r5 = r9.zzMd
            int r0 = r0 + r5
            int r5 = r9.zzrC
            int r0 = r0 + r5
            int r0 = r0 - r6
            goto L_0x00b8
        L_0x00a7:
            int r0 = r9.zzMb
            int r5 = r9.zzMd
            int r0 = r0 + r5
            int r5 = r9.zzrC
            int r5 = r5 / r8
            int r0 = r0 + r5
            int r0 = r0 + -25
            goto L_0x00b8
        L_0x00b3:
            int r0 = r9.zzMb
            int r5 = r9.zzMd
            int r0 = r0 + r5
        L_0x00b8:
            int r5 = r9.zzMc
            int r7 = r9.zzMe
            int r5 = r5 + r7
            int r7 = r9.zzrD
            int r5 = r5 + r7
            int r5 = r5 - r6
            goto L_0x00eb
        L_0x00c2:
            int r0 = r9.zzMb
            int r5 = r9.zzMd
            int r0 = r0 + r5
            int r5 = r9.zzrC
            int r5 = r5 / r8
            int r0 = r0 + r5
            int r0 = r0 + -25
            int r5 = r9.zzMc
            int r7 = r9.zzMe
            int r5 = r5 + r7
            int r7 = r9.zzrD
            int r7 = r7 / r8
            int r5 = r5 + r7
            int r5 = r5 + -25
            goto L_0x00eb
        L_0x00d9:
            int r0 = r9.zzMb
            int r5 = r9.zzMd
            int r0 = r0 + r5
            int r5 = r9.zzrC
            int r5 = r5 / r8
            int r0 = r0 + r5
            int r0 = r0 + -25
            goto L_0x0097
        L_0x00e5:
            int r0 = r9.zzMb
            int r5 = r9.zzMd
            int r0 = r0 + r5
            goto L_0x0097
        L_0x00eb:
            if (r0 < 0) goto L_0x00f9
            int r0 = r0 + r6
            if (r0 > r3) goto L_0x00f9
            r0 = r1[r2]
            if (r5 < r0) goto L_0x00f9
            int r5 = r5 + r6
            r0 = r1[r4]
            if (r5 <= r0) goto L_0x00fa
        L_0x00f9:
            return r2
        L_0x00fa:
            return r4
        L_0x00fb:
            java.lang.String r0 = "Height is too small or too large."
            goto L_0x003a
        L_0x00ff:
            java.lang.String r0 = "Width is too small or too large."
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzkr.zzhn():boolean");
    }

    public void zzs(boolean z) {
        synchronized (this.zzrJ) {
            if (this.zzMi != null) {
                this.zzMi.dismiss();
                this.zzMj.removeView(this.zzIs.getView());
                if (this.zzMk != null) {
                    this.zzMk.removeView(this.zzMf);
                    this.zzMk.addView(this.zzIs.getView());
                    this.zzIs.zza(this.zzus);
                }
                if (z) {
                    zzaB("default");
                    if (this.zzMh != null) {
                        this.zzMh.zzcb();
                    }
                }
                this.zzMi = null;
                this.zzMj = null;
                this.zzMk = null;
                this.zzMg = null;
            }
        }
    }
}
