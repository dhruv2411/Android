package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzhf;
import com.google.android.gms.internal.zzlw;
import com.google.android.gms.internal.zzqx;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzhb implements zzha {
    private final Context mContext;
    private final zzs zzGJ;
    @Nullable
    private final JSONObject zzGM;
    @Nullable
    private final zzlw zzGN;
    @Nullable
    private final zzha.zza zzGO;
    private final zzaw zzGP;
    boolean zzGQ;
    @Nullable
    private String zzGR;
    private WeakReference<View> zzGS = null;
    private final Object zzrJ = new Object();
    @Nullable
    private zzov zztF;
    @Nullable
    private final zzqh zztt;

    private static class zza {
        /* access modifiers changed from: private */
        public final WeakReference<zzqw> zzGU;
        /* access modifiers changed from: private */
        public String zzGV;

        public zza(zzqw zzqw) {
            this.zzGU = new WeakReference<>(zzqw);
        }

        /* access modifiers changed from: private */
        public zzid zzf(final zzjj zzjj) {
            return new zzid() {
                public void zza(zzqw zzqw, final Map<String, String> map) {
                    zzqw zzqw2 = (zzqw) zza.this.zzGU.get();
                    if (zzqw2 == null) {
                        zzjj.zzb("/loadHtml", (zzid) this);
                        return;
                    }
                    zzqw2.zzlv().zza((zzqx.zza) new zzqx.zza() {
                        public void zza(zzqw zzqw, boolean z) {
                            String unused = zza.this.zzGV = (String) map.get(TtmlNode.ATTR_ID);
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("messageType", "htmlLoaded");
                                jSONObject.put(TtmlNode.ATTR_ID, zza.this.zzGV);
                                zzjj.zzb("sendMessageToNativeJs", jSONObject);
                            } catch (JSONException e) {
                                zzpk.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                            }
                        }
                    });
                    String str = map.get("overlayHtml");
                    String str2 = map.get("baseUrl");
                    if (TextUtils.isEmpty(str2)) {
                        zzqw2.loadData(str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
                    } else {
                        zzqw2.loadDataWithBaseURL(str2, str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", (String) null);
                    }
                }
            };
        }

        /* access modifiers changed from: private */
        public zzid zzg(final zzjj zzjj) {
            return new zzid() {
                public void zza(zzqw zzqw, Map<String, String> map) {
                    zzqw zzqw2 = (zzqw) zza.this.zzGU.get();
                    if (zzqw2 == null) {
                        zzjj.zzb("/showOverlay", (zzid) this);
                    } else {
                        zzqw2.getView().setVisibility(0);
                    }
                }
            };
        }

        /* access modifiers changed from: private */
        public zzid zzh(final zzjj zzjj) {
            return new zzid() {
                public void zza(zzqw zzqw, Map<String, String> map) {
                    zzqw zzqw2 = (zzqw) zza.this.zzGU.get();
                    if (zzqw2 == null) {
                        zzjj.zzb("/hideOverlay", (zzid) this);
                    } else {
                        zzqw2.getView().setVisibility(8);
                    }
                }
            };
        }

        /* access modifiers changed from: private */
        public zzid zzi(final zzjj zzjj) {
            return new zzid() {
                public void zza(zzqw zzqw, Map<String, String> map) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        for (String next : map.keySet()) {
                            jSONObject.put(next, map.get(next));
                        }
                        jSONObject.put(TtmlNode.ATTR_ID, zza.this.zzGV);
                        zzjj.zzb("sendMessageToNativeJs", jSONObject);
                    } catch (JSONException e) {
                        zzpk.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                    }
                }
            };
        }

        public zzlw.zza zzgh() {
            return new zzlw.zza() {
                public void zze(zzjj zzjj) {
                    zzqw zzqw = (zzqw) zza.this.zzGU.get();
                    if (zzqw != null) {
                        zzjj.zza("/loadHtml", zza.this.zzf(zzjj));
                        zzjj.zza("/showOverlay", zza.this.zzg(zzjj));
                        zzjj.zza("/hideOverlay", zza.this.zzh(zzjj));
                        zzqw.zzlv().zza("/sendMessageToSdk", zza.this.zzi(zzjj));
                    }
                }
            };
        }
    }

    public zzhb(Context context, zzs zzs, @Nullable zzlw zzlw, zzaw zzaw, @Nullable JSONObject jSONObject, @Nullable zzha.zza zza2, @Nullable zzqh zzqh, @Nullable String str) {
        this.mContext = context;
        this.zzGJ = zzs;
        this.zzGN = zzlw;
        this.zzGP = zzaw;
        this.zzGM = jSONObject;
        this.zzGO = zza2;
        this.zztt = zzqh;
        this.zzGR = str;
    }

    private JSONObject zza(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObject = new JSONObject();
        if (map == null || view == null) {
            return jSONObject;
        }
        try {
            int[] zzk = zzk(view);
            for (Map.Entry next : map.entrySet()) {
                View view2 = (View) ((WeakReference) next.getValue()).get();
                if (view2 != null) {
                    int[] zzk2 = zzk(view2);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("width", zzC(zzl(view2)));
                    jSONObject2.put("height", zzC(zzm(view2)));
                    jSONObject2.put("x", zzC(zzk2[0] - zzk[0]));
                    jSONObject2.put("y", zzC(zzk2[1] - zzk[1]));
                    jSONObject.put((String) next.getKey(), jSONObject2);
                }
            }
        } catch (JSONException unused) {
            zzpk.zzbh("Unable to get all view rectangles");
        }
        return jSONObject;
    }

    private JSONObject zzb(Rect rect) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("x", zzC(rect.left));
        jSONObject.put("y", zzC(rect.top));
        jSONObject.put("width", zzC(rect.right - rect.left));
        jSONObject.put("height", zzC(rect.bottom - rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private JSONObject zzb(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (map == null || view == null) {
            return jSONObject2;
        }
        int[] zzk = zzk(view);
        for (Map.Entry next : map.entrySet()) {
            View view2 = (View) ((WeakReference) next.getValue()).get();
            if (view2 != null) {
                int[] zzk2 = zzk(view2);
                JSONObject jSONObject3 = new JSONObject();
                JSONObject jSONObject4 = new JSONObject();
                try {
                    jSONObject4.put("width", zzC(zzl(view2)));
                    jSONObject4.put("height", zzC(zzm(view2)));
                    jSONObject4.put("x", zzC(zzk2[0] - zzk[0]));
                    jSONObject4.put("y", zzC(zzk2[1] - zzk[1]));
                    jSONObject4.put("relative_to", "ad_view");
                    jSONObject3.put("frame", jSONObject4);
                    Rect rect = new Rect();
                    if (view2.getLocalVisibleRect(rect)) {
                        jSONObject = zzb(rect);
                    } else {
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put("x", zzC(zzk2[0] - zzk[0]));
                        jSONObject5.put("y", zzC(zzk2[1] - zzk[1]));
                        jSONObject5.put("width", 0);
                        jSONObject5.put("height", 0);
                        jSONObject5.put("relative_to", "ad_view");
                        jSONObject = jSONObject5;
                    }
                    jSONObject3.put("visible_bounds", jSONObject);
                    if (view2 instanceof TextView) {
                        TextView textView = (TextView) view2;
                        jSONObject3.put("text_color", textView.getCurrentTextColor());
                        jSONObject3.put("font_size", (double) textView.getTextSize());
                        jSONObject3.put(MimeTypes.BASE_TYPE_TEXT, textView.getText());
                    }
                    jSONObject2.put((String) next.getKey(), jSONObject3);
                } catch (JSONException unused) {
                    zzpk.zzbh("Unable to get asset views information");
                }
            }
        }
        return jSONObject2;
    }

    private JSONObject zzn(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("width", zzC(zzl(view)));
            jSONObject.put("height", zzC(zzm(view)));
            return jSONObject;
        } catch (Exception unused) {
            zzpk.zzbh("Unable to get native ad view bounding box");
            return jSONObject;
        }
    }

    private JSONObject zzo(View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (view == null) {
            return jSONObject2;
        }
        try {
            int[] zzk = zzk(view);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("width", zzC(zzl(view)));
            jSONObject3.put("height", zzC(zzm(view)));
            jSONObject3.put("x", zzC(zzk[0]));
            jSONObject3.put("y", zzC(zzk[1]));
            jSONObject3.put("relative_to", "window");
            jSONObject2.put("frame", jSONObject3);
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                jSONObject = zzb(rect);
            } else {
                jSONObject = new JSONObject();
                jSONObject.put("x", zzC(zzk[0]));
                jSONObject.put("y", zzC(zzk[1]));
                jSONObject.put("width", 0);
                jSONObject.put("height", 0);
                jSONObject.put("relative_to", "window");
            }
            jSONObject2.put("visible_bounds", jSONObject);
            return jSONObject2;
        } catch (Exception unused) {
            zzpk.zzbh("Unable to get native ad view bounding box");
            return jSONObject2;
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public int zzC(int i) {
        return zzel.zzeT().zzc(this.mContext, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        r1.addRule(11);
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View zza(android.view.View.OnClickListener r7, boolean r8) {
        /*
            r6 = this;
            com.google.android.gms.internal.zzha$zza r0 = r6.zzGO
            com.google.android.gms.internal.zzgs r0 = r0.zzfT()
            if (r0 != 0) goto L_0x000a
            r7 = 0
            return r7
        L_0x000a:
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r2 = -2
            r1.<init>(r2, r2)
            if (r8 != 0) goto L_0x0038
            int r8 = r0.zzfN()
            r2 = 9
            r3 = 10
            if (r8 == 0) goto L_0x0032
            r4 = 12
            r5 = 11
            switch(r8) {
                case 2: goto L_0x002e;
                case 3: goto L_0x002a;
                default: goto L_0x0023;
            }
        L_0x0023:
            r1.addRule(r3)
        L_0x0026:
            r1.addRule(r5)
            goto L_0x0038
        L_0x002a:
            r1.addRule(r4)
            goto L_0x0035
        L_0x002e:
            r1.addRule(r4)
            goto L_0x0026
        L_0x0032:
            r1.addRule(r3)
        L_0x0035:
            r1.addRule(r2)
        L_0x0038:
            com.google.android.gms.internal.zzgt r8 = new com.google.android.gms.internal.zzgt
            android.content.Context r2 = r6.mContext
            r8.<init>(r2, r0, r1)
            r8.setOnClickListener(r7)
            com.google.android.gms.internal.zzfz<java.lang.String> r7 = com.google.android.gms.internal.zzgd.zzEu
            java.lang.Object r7 = r7.get()
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r8.setContentDescription(r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhb.zza(android.view.View$OnClickListener, boolean):android.view.View");
    }

    public void zza(View view, zzgy zzgy) {
        zzhf zze;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        View zzfU = this.zzGO.zzfU();
        if (zzfU != null) {
            ViewParent parent = zzfU.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(zzfU);
            }
            ((FrameLayout) view).addView(zzfU, layoutParams);
            this.zzGJ.zza(zzgy);
        } else if (this.zzGO instanceof zzha.zzb) {
            zzha.zzb zzb = (zzha.zzb) this.zzGO;
            if (zzb.getImages() != null && zzb.getImages().size() > 0 && (zze = zze(zzb.getImages().get(0))) != null) {
                try {
                    IObjectWrapper zzfP = zze.zzfP();
                    if (zzfP != null) {
                        ImageView zzgf = zzgf();
                        zzgf.setImageDrawable((Drawable) zzd.zzF(zzfP));
                        zzgf.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        ((FrameLayout) view).addView(zzgf, layoutParams);
                    }
                } catch (RemoteException unused) {
                    zzpk.zzbh("Could not get drawable from image");
                }
            }
        }
    }

    public void zza(View view, String str, @Nullable JSONObject jSONObject, Map<String, WeakReference<View>> map, View view2) {
        String str2;
        JSONObject zzn;
        zzac.zzdj("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("asset", str);
            jSONObject2.put("template", this.zzGO.zzfS());
            final JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("ad", this.zzGM);
            jSONObject3.put("click", jSONObject2);
            jSONObject3.put("has_custom_click_handler", this.zzGJ.zzz(this.zzGO.getCustomTemplateId()) != null);
            if (zzgd.zzEw.get().booleanValue()) {
                if (zzgd.zzEx.get().booleanValue()) {
                    jSONObject3.put("asset_view_signal", zzb(map, view2));
                    str2 = "ad_view_signal";
                    zzn = zzo(view2);
                } else {
                    jSONObject3.put("view_rectangles", zza(map, view2));
                    str2 = "native_view_rectangle";
                    zzn = zzn(view2);
                }
                jSONObject3.put(str2, zzn);
            }
            if (jSONObject != null) {
                jSONObject3.put("click_point", jSONObject);
            }
            try {
                JSONObject optJSONObject = this.zzGM.optJSONObject("tracking_urls_and_actions");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                jSONObject2.put("click_signals", this.zzGP.zzT().zza(this.mContext, optJSONObject.optString("click_string"), view));
            } catch (Exception e) {
                zzpk.zzb("Exception obtaining click signals", e);
            }
            jSONObject3.put("ads_id", this.zzGR);
            this.zzGN.zza((zzlw.zza) new zzlw.zza(this) {
                public void zze(zzjj zzjj) {
                    zzjj.zza("google.afma.nativeAds.handleClickGmsg", jSONObject3);
                }
            });
        } catch (JSONException e2) {
            zzpk.zzb("Unable to create click JSON.", e2);
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        if (zzgd.zzEr.get().booleanValue()) {
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
            if (map != null) {
                for (Map.Entry<String, WeakReference<View>> value : map.entrySet()) {
                    View view2 = (View) ((WeakReference) value.getValue()).get();
                    if (view2 != null) {
                        view2.setOnTouchListener(onTouchListener);
                        view2.setClickable(true);
                        view2.setOnClickListener(onClickListener);
                    }
                }
            }
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, View view2) {
        String str;
        zzac.zzdj("performClick must be called on the main UI thread.");
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                if (view.equals((View) ((WeakReference) next.getValue()).get())) {
                    zza(view, (String) next.getKey(), jSONObject, map, view2);
                    return;
                }
            }
        }
        if ("2".equals(this.zzGO.zzfS())) {
            str = "2099";
        } else if ("1".equals(this.zzGO.zzfS())) {
            str = "1099";
        } else {
            return;
        }
        zza(view, str, jSONObject, map, view2);
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        String str;
        JSONObject zzn;
        zzac.zzdj("recordImpression must be called on the main UI thread.");
        zzq(true);
        try {
            final JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad", this.zzGM);
            jSONObject.put("ads_id", this.zzGR);
            if (zzgd.zzEw.get().booleanValue()) {
                if (zzgd.zzEx.get().booleanValue()) {
                    jSONObject.put("asset_view_signal", zzb(map, view));
                    str = "ad_view_signal";
                    zzn = zzo(view);
                } else {
                    jSONObject.put("view_rectangles", zza(map, view));
                    str = "native_view_rectangle";
                    zzn = zzn(view);
                }
                jSONObject.put(str, zzn);
            }
            this.zzGN.zza((zzlw.zza) new zzlw.zza(this) {
                public void zze(zzjj zzjj) {
                    zzjj.zza("google.afma.nativeAds.handleImpressionPing", jSONObject);
                }
            });
        } catch (JSONException e) {
            zzpk.zzb("Unable to create impression JSON.", e);
        }
        this.zzGJ.zza((zzha) this);
        this.zzGJ.zzbL();
    }

    public void zzc(View view, Map<String, WeakReference<View>> map) {
        if (!zzgd.zzEq.get().booleanValue()) {
            view.setOnTouchListener((View.OnTouchListener) null);
            view.setClickable(false);
            view.setOnClickListener((View.OnClickListener) null);
            if (map != null) {
                for (Map.Entry<String, WeakReference<View>> value : map.entrySet()) {
                    View view2 = (View) ((WeakReference) value.getValue()).get();
                    if (view2 != null) {
                        view2.setOnTouchListener((View.OnTouchListener) null);
                        view2.setClickable(false);
                        view2.setOnClickListener((View.OnClickListener) null);
                    }
                }
            }
        }
    }

    public void zzd(MotionEvent motionEvent) {
        this.zzGP.zza(motionEvent);
    }

    public void zzd(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.zzrJ) {
            if (!this.zzGQ) {
                if (view.isShown()) {
                    if (view.getGlobalVisibleRect(new Rect(), (Point) null)) {
                        zzb(view, map);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public zzhf zze(Object obj) {
        if (obj instanceof IBinder) {
            return zzhf.zza.zzB((IBinder) obj);
        }
        return null;
    }

    public boolean zzfY() {
        zzgs zzfT = this.zzGO.zzfT();
        return zzfT != null && zzfT.zzfO();
    }

    public zzqw zzgb() {
        if (this.zzGM == null || this.zzGM.optJSONObject("overlay") == null) {
            return null;
        }
        zzqw zzge = zzge();
        zzge.getView().setVisibility(8);
        this.zzGN.zza(new zza(zzge).zzgh());
        return zzge;
    }

    public View zzgc() {
        if (this.zzGS != null) {
            return (View) this.zzGS.get();
        }
        return null;
    }

    public void zzgd() {
        this.zzGJ.zzcv();
    }

    /* access modifiers changed from: package-private */
    public zzqw zzge() {
        return zzw.zzcN().zza(this.mContext, zzeg.zzk(this.mContext), false, false, this.zzGP, this.zztt);
    }

    /* access modifiers changed from: package-private */
    public ImageView zzgf() {
        return new ImageView(this.mContext);
    }

    @Nullable
    public zzov zzgg() {
        if (!zzw.zzdl().zzjS()) {
            return null;
        }
        if (this.zztF == null) {
            this.zztF = new zzov(this.mContext, this.zzGJ.getAdUnitId());
        }
        return this.zztF;
    }

    public void zzj(View view) {
        this.zzGS = new WeakReference<>(view);
    }

    /* access modifiers changed from: package-private */
    public int[] zzk(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    /* access modifiers changed from: package-private */
    public int zzl(View view) {
        return view.getMeasuredWidth();
    }

    /* access modifiers changed from: package-private */
    public int zzm(View view) {
        return view.getMeasuredHeight();
    }

    /* access modifiers changed from: protected */
    public void zzq(boolean z) {
        this.zzGQ = z;
    }
}
