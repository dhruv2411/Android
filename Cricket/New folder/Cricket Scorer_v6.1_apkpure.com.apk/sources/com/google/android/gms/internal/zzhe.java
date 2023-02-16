package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.AdChoicesView;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzcy;
import com.google.android.gms.internal.zzhh;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@zzme
public class zzhe extends zzhh.zza implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    static final String[] zzHf = {"2011", "1009"};
    @Nullable
    zzha zzGA;
    private final FrameLayout zzHg;
    Map<String, WeakReference<View>> zzHh = new HashMap();
    @Nullable
    View zzHi;
    boolean zzHj = false;
    Point zzHk = new Point();
    Point zzHl = new Point();
    WeakReference<zzcy> zzHm = new WeakReference<>((Object) null);
    private final Object zzrJ = new Object();
    @Nullable
    FrameLayout zzrY;

    public zzhe(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzHg = frameLayout;
        this.zzrY = frameLayout2;
        zzw.zzdk().zza((View) this.zzHg, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzw.zzdk().zza((View) this.zzHg, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzHg.setOnTouchListener(this);
        this.zzHg.setOnClickListener(this);
        zzgd.initialize(this.zzHg.getContext());
    }

    private void zza(zzhb zzhb) {
        boolean zzfY = zzhb.zzfY();
        ViewGroup viewGroup = null;
        if (zzfY && this.zzHh != null) {
            WeakReference weakReference = this.zzHh.get("1098");
            KeyEvent.Callback callback = weakReference != null ? (View) weakReference.get() : null;
            if (callback instanceof ViewGroup) {
                viewGroup = (ViewGroup) callback;
            }
        }
        boolean z = zzfY && viewGroup != null;
        this.zzHi = zza(zzhb, z);
        if (this.zzHi != null) {
            if (this.zzHh != null) {
                this.zzHh.put("1007", new WeakReference(this.zzHi));
            }
            if (z) {
                viewGroup.removeAllViews();
                viewGroup.addView(this.zzHi);
                return;
            }
            AdChoicesView zzp = zzp(zzhb.getContext());
            zzp.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            zzp.addView(this.zzHi);
            if (this.zzrY != null) {
                this.zzrY.addView(zzp);
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzb(zzhb zzhb) {
        synchronized (this.zzrJ) {
            final View zzgk = zzgk();
            if (!(zzgk instanceof FrameLayout)) {
                zzhb.zzgd();
            } else {
                zzhb.zza(zzgk, (zzgy) new zzgy() {
                    public void zzc(MotionEvent motionEvent) {
                        zzhe.this.onTouch((View) null, motionEvent);
                    }

                    public void zzfX() {
                        zzhe.this.onClick(zzgk);
                    }
                });
            }
        }
    }

    private View zzgk() {
        if (this.zzHh == null) {
            return null;
        }
        for (String str : zzHf) {
            WeakReference weakReference = this.zzHh.get(str);
            if (weakReference != null) {
                return (View) weakReference.get();
            }
        }
        return null;
    }

    public void destroy() {
        synchronized (this.zzrJ) {
            if (this.zzrY != null) {
                this.zzrY.removeAllViews();
            }
            this.zzrY = null;
            this.zzHh = null;
            this.zzHi = null;
            this.zzGA = null;
            this.zzHk = null;
            this.zzHl = null;
            this.zzHm = null;
        }
    }

    /* access modifiers changed from: package-private */
    public int getMeasuredHeight() {
        return this.zzHg.getMeasuredHeight();
    }

    /* access modifiers changed from: package-private */
    public int getMeasuredWidth() {
        return this.zzHg.getMeasuredWidth();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.zzrJ
            monitor-enter(r0)
            com.google.android.gms.internal.zzha r1 = r7.zzGA     // Catch:{ all -> 0x008b }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            return
        L_0x0009:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ all -> 0x008b }
            r4.<init>()     // Catch:{ all -> 0x008b }
            java.lang.String r1 = "x"
            android.graphics.Point r2 = r7.zzHk     // Catch:{ JSONException -> 0x0043 }
            int r2 = r2.x     // Catch:{ JSONException -> 0x0043 }
            int r2 = r7.zzC(r2)     // Catch:{ JSONException -> 0x0043 }
            r4.put(r1, r2)     // Catch:{ JSONException -> 0x0043 }
            java.lang.String r1 = "y"
            android.graphics.Point r2 = r7.zzHk     // Catch:{ JSONException -> 0x0043 }
            int r2 = r2.y     // Catch:{ JSONException -> 0x0043 }
            int r2 = r7.zzC(r2)     // Catch:{ JSONException -> 0x0043 }
            r4.put(r1, r2)     // Catch:{ JSONException -> 0x0043 }
            java.lang.String r1 = "start_x"
            android.graphics.Point r2 = r7.zzHl     // Catch:{ JSONException -> 0x0043 }
            int r2 = r2.x     // Catch:{ JSONException -> 0x0043 }
            int r2 = r7.zzC(r2)     // Catch:{ JSONException -> 0x0043 }
            r4.put(r1, r2)     // Catch:{ JSONException -> 0x0043 }
            java.lang.String r1 = "start_y"
            android.graphics.Point r2 = r7.zzHl     // Catch:{ JSONException -> 0x0043 }
            int r2 = r2.y     // Catch:{ JSONException -> 0x0043 }
            int r2 = r7.zzC(r2)     // Catch:{ JSONException -> 0x0043 }
            r4.put(r1, r2)     // Catch:{ JSONException -> 0x0043 }
            goto L_0x0048
        L_0x0043:
            java.lang.String r1 = "Unable to get click location"
            com.google.android.gms.internal.zzpk.zzbh(r1)     // Catch:{ all -> 0x008b }
        L_0x0048:
            android.view.View r1 = r7.zzHi     // Catch:{ all -> 0x008b }
            if (r1 == 0) goto L_0x0080
            android.view.View r1 = r7.zzHi     // Catch:{ all -> 0x008b }
            boolean r1 = r1.equals(r8)     // Catch:{ all -> 0x008b }
            if (r1 == 0) goto L_0x0080
            com.google.android.gms.internal.zzha r1 = r7.zzGA     // Catch:{ all -> 0x008b }
            boolean r1 = r1 instanceof com.google.android.gms.internal.zzgz     // Catch:{ all -> 0x008b }
            if (r1 == 0) goto L_0x0077
            com.google.android.gms.internal.zzha r1 = r7.zzGA     // Catch:{ all -> 0x008b }
            com.google.android.gms.internal.zzgz r1 = (com.google.android.gms.internal.zzgz) r1     // Catch:{ all -> 0x008b }
            com.google.android.gms.internal.zzha r1 = r1.zzga()     // Catch:{ all -> 0x008b }
            if (r1 == 0) goto L_0x0089
            com.google.android.gms.internal.zzha r1 = r7.zzGA     // Catch:{ all -> 0x008b }
            com.google.android.gms.internal.zzgz r1 = (com.google.android.gms.internal.zzgz) r1     // Catch:{ all -> 0x008b }
            com.google.android.gms.internal.zzha r1 = r1.zzga()     // Catch:{ all -> 0x008b }
            java.lang.String r3 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r7.zzHh     // Catch:{ all -> 0x008b }
            android.widget.FrameLayout r6 = r7.zzHg     // Catch:{ all -> 0x008b }
        L_0x0072:
            r2 = r8
            r1.zza(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x008b }
            goto L_0x0089
        L_0x0077:
            com.google.android.gms.internal.zzha r1 = r7.zzGA     // Catch:{ all -> 0x008b }
            java.lang.String r3 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r7.zzHh     // Catch:{ all -> 0x008b }
            android.widget.FrameLayout r6 = r7.zzHg     // Catch:{ all -> 0x008b }
            goto L_0x0072
        L_0x0080:
            com.google.android.gms.internal.zzha r1 = r7.zzGA     // Catch:{ all -> 0x008b }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r7.zzHh     // Catch:{ all -> 0x008b }
            android.widget.FrameLayout r3 = r7.zzHg     // Catch:{ all -> 0x008b }
            r1.zza(r8, r2, r4, r3)     // Catch:{ all -> 0x008b }
        L_0x0089:
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            return
        L_0x008b:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhe.onClick(android.view.View):void");
    }

    public void onGlobalLayout() {
        synchronized (this.zzrJ) {
            if (this.zzHj) {
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzrY == null)) {
                    this.zzrY.setLayoutParams(new FrameLayout.LayoutParams(measuredWidth, measuredHeight));
                    this.zzHj = false;
                }
            }
            if (this.zzGA != null) {
                this.zzGA.zzd(this.zzHg, this.zzHh);
            }
        }
    }

    public void onScrollChanged() {
        synchronized (this.zzrJ) {
            if (this.zzGA != null) {
                this.zzGA.zzd(this.zzHg, this.zzHh);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.zzrJ) {
            if (this.zzGA == null) {
                return false;
            }
            Point zze = zze(motionEvent);
            this.zzHk = zze;
            if (motionEvent.getAction() == 0) {
                this.zzHl = zze;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation((float) zze.x, (float) zze.y);
            this.zzGA.zzd(obtain);
            obtain.recycle();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public int zzC(int i) {
        return zzel.zzeT().zzc(this.zzGA.getContext(), i);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.dynamic.IObjectWrapper zzU(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzHh     // Catch:{ all -> 0x0022 }
            r2 = 0
            if (r1 != 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return r2
        L_0x000a:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzHh     // Catch:{ all -> 0x0022 }
            java.lang.Object r4 = r1.get(r4)     // Catch:{ all -> 0x0022 }
            java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4     // Catch:{ all -> 0x0022 }
            if (r4 != 0) goto L_0x0015
            goto L_0x001c
        L_0x0015:
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0022 }
            r2 = r4
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x0022 }
        L_0x001c:
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.zzd.zzA(r2)     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return r4
        L_0x0022:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhe.zzU(java.lang.String):com.google.android.gms.dynamic.IObjectWrapper");
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public View zza(zzhb zzhb, boolean z) {
        return zzhb.zza((View.OnClickListener) this, z);
    }

    public void zzb(IObjectWrapper iObjectWrapper, int i) {
        zzcy zzcy;
        if (zzw.zzdl().zzjS() && (zzcy = (zzcy) this.zzHm.get()) != null) {
            zzcy.zzdY();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzd(java.lang.String r4, com.google.android.gms.dynamic.IObjectWrapper r5) {
        /*
            r3 = this;
            java.lang.Object r5 = com.google.android.gms.dynamic.zzd.zzF(r5)
            android.view.View r5 = (android.view.View) r5
            java.lang.Object r0 = r3.zzrJ
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzHh     // Catch:{ all -> 0x0037 }
            if (r1 != 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            return
        L_0x000f:
            if (r5 != 0) goto L_0x0017
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r3.zzHh     // Catch:{ all -> 0x0037 }
            r5.remove(r4)     // Catch:{ all -> 0x0037 }
            goto L_0x0035
        L_0x0017:
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r1 = r3.zzHh     // Catch:{ all -> 0x0037 }
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0037 }
            r2.<init>(r5)     // Catch:{ all -> 0x0037 }
            r1.put(r4, r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = "1098"
            boolean r4 = r1.equals(r4)     // Catch:{ all -> 0x0037 }
            if (r4 == 0) goto L_0x002b
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            return
        L_0x002b:
            r5.setOnTouchListener(r3)     // Catch:{ all -> 0x0037 }
            r4 = 1
            r5.setClickable(r4)     // Catch:{ all -> 0x0037 }
            r5.setOnClickListener(r3)     // Catch:{ all -> 0x0037 }
        L_0x0035:
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            return
        L_0x0037:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhe.zzd(java.lang.String, com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* access modifiers changed from: package-private */
    public Point zze(MotionEvent motionEvent) {
        int[] iArr = new int[2];
        this.zzHg.getLocationOnScreen(iArr);
        return new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
    }

    public void zze(IObjectWrapper iObjectWrapper) {
        synchronized (this.zzrJ) {
            zzj((View) null);
            Object zzF = zzd.zzF(iObjectWrapper);
            if (!(zzF instanceof zzhb)) {
                zzpk.zzbh("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            if (this.zzrY != null) {
                this.zzrY.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
                this.zzHg.requestLayout();
            }
            this.zzHj = true;
            final zzhb zzhb = (zzhb) zzF;
            if (this.zzGA != null && zzgd.zzEp.get().booleanValue()) {
                this.zzGA.zzc(this.zzHg, this.zzHh);
            }
            zzgj();
            if (!(this.zzGA instanceof zzgz) || !((zzgz) this.zzGA).zzfZ()) {
                this.zzGA = zzhb;
                if (zzhb instanceof zzgz) {
                    ((zzgz) zzhb).zzc((zzha) null);
                }
            } else {
                ((zzgz) this.zzGA).zzc(zzhb);
            }
            if (zzgd.zzEp.get().booleanValue()) {
                this.zzrY.setClickable(false);
            }
            this.zzrY.removeAllViews();
            zza(zzhb);
            zzhb.zza((View) this.zzHg, this.zzHh, (View.OnTouchListener) this, (View.OnClickListener) this);
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzqw zzgb = zzhb.zzgb();
                    if (!(zzgb == null || zzhe.this.zzrY == null)) {
                        zzhe.this.zzrY.addView(zzgb.getView());
                    }
                    if (!(zzhb instanceof zzgz)) {
                        zzhe.this.zzb(zzhb);
                    }
                }
            });
            zzj(this.zzHg);
            zzgi();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzgi() {
        if (this.zzGA instanceof zzhb) {
            zzhb zzhb = (zzhb) this.zzGA;
            if (zzw.zzdl().zzjS() && zzhb != null && zzhb.getContext() != null) {
                zzcy zzcy = (zzcy) this.zzHm.get();
                if (zzcy == null) {
                    zzcy = new zzcy(this.zzHg.getContext(), this.zzHg);
                    this.zzHm = new WeakReference<>(zzcy);
                }
                zzcy.zza((zzcy.zzb) zzhb.zzgg());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzgj() {
        if (this.zzGA instanceof zzhb) {
            zzhb zzhb = (zzhb) this.zzGA;
            if (zzw.zzdl().zzjS() && zzhb != null && zzhb.getContext() != null) {
                zzov zzgg = zzhb.zzgg();
                if (zzgg != null) {
                    zzgg.zzC(false);
                }
                zzcy zzcy = (zzcy) this.zzHm.get();
                if (zzcy != null && zzgg != null) {
                    zzcy.zzb(zzgg);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzj(@Nullable View view) {
        if (this.zzGA != null) {
            zzha zzga = this.zzGA instanceof zzgz ? ((zzgz) this.zzGA).zzga() : this.zzGA;
            if (zzga != null) {
                zzga.zzj(view);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public AdChoicesView zzp(Context context) {
        return new AdChoicesView(context);
    }
}
