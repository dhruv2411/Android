package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzlw;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpv;
import com.google.android.gms.internal.zzql;
import com.itextpdf.text.html.HtmlTags;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzlx implements Callable<zzpb> {
    static long zzQl = TimeUnit.SECONDS.toMillis(60);
    private final Context mContext;
    private final zzlw zzGN;
    private final zzaw zzGP;
    /* access modifiers changed from: private */
    public final zzpb.zza zzPR;
    private int zzPY;
    private final zzpv zzQu;
    /* access modifiers changed from: private */
    public final zzs zzQv;
    private boolean zzQw;
    private List<String> zzQx;
    private JSONObject zzQy;
    private String zzQz;
    private final Object zzrJ = new Object();
    private final zzgl zzsn;

    public interface zza<T extends zzha.zza> {
        T zza(zzlx zzlx, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException;
    }

    class zzb {
        public zzid zzQU;

        zzb(zzlx zzlx) {
        }
    }

    public zzlx(Context context, zzs zzs, zzpv zzpv, zzaw zzaw, zzpb.zza zza2, zzgl zzgl) {
        this.mContext = context;
        this.zzQv = zzs;
        this.zzQu = zzpv;
        this.zzPR = zza2;
        this.zzGP = zzaw;
        this.zzsn = zzgl;
        this.zzGN = zza(context, zza2, zzs, zzaw);
        this.zzGN.zziT();
        this.zzQw = false;
        this.zzPY = -2;
        this.zzQx = null;
        this.zzQz = null;
    }

    private zzha.zza zza(zza zza2, JSONObject jSONObject, String str) throws ExecutionException, InterruptedException, JSONException {
        if (zzjf() || zza2 == null || jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("tracking_urls_and_actions");
        String[] zzd = zzd(jSONObject2, "impression_tracking_urls");
        this.zzQx = zzd == null ? null : Arrays.asList(zzd);
        this.zzQy = jSONObject2.optJSONObject("active_view");
        this.zzQz = jSONObject.optString("debug_signals");
        zzha.zza zza3 = zza2.zza(this, jSONObject);
        if (zza3 == null) {
            zzpk.e("Failed to retrieve ad assets.");
            return null;
        }
        zza3.zzb(new zzhb(this.mContext, this.zzQv, this.zzGN, this.zzGP, jSONObject, zza3, this.zzPR.zzTi.zzvn, str));
        return zza3;
    }

    private zzqm<zzgu> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString("url") : jSONObject.optString("url");
        final double optDouble = jSONObject.optDouble("scale", 1.0d);
        final boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (TextUtils.isEmpty(string)) {
            zza(0, z);
            return new zzqk(null);
        } else if (z2) {
            return new zzqk(new zzgu((Drawable) null, Uri.parse(string), optDouble));
        } else {
            final boolean z3 = z;
            final String str = string;
            return this.zzQu.zza(string, new zzpv.zza<zzgu>() {
                @TargetApi(19)
                /* renamed from: zzg */
                public zzgu zzh(InputStream inputStream) {
                    Bitmap bitmap;
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inDensity = (int) (160.0d * optDouble);
                    if (!optBoolean) {
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                    }
                    long uptimeMillis = SystemClock.uptimeMillis();
                    try {
                        bitmap = BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                    } catch (Exception e) {
                        zzpk.zzb("Error grabbing image.", e);
                        bitmap = null;
                    }
                    if (bitmap == null) {
                        zzlx.this.zza(2, z3);
                        return null;
                    }
                    long uptimeMillis2 = SystemClock.uptimeMillis();
                    if (zzt.zzzl() && zzpk.zzkI()) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        int allocationByteCount = bitmap.getAllocationByteCount();
                        long j = uptimeMillis2 - uptimeMillis;
                        boolean z = Looper.getMainLooper().getThread() == Thread.currentThread();
                        StringBuilder sb = new StringBuilder(108);
                        sb.append("Decoded image w: ");
                        sb.append(width);
                        sb.append(" h:");
                        sb.append(height);
                        sb.append(" bytes: ");
                        sb.append(allocationByteCount);
                        sb.append(" time: ");
                        sb.append(j);
                        sb.append(" on ui thread: ");
                        sb.append(z);
                        zzpk.v(sb.toString());
                    }
                    return new zzgu(new BitmapDrawable(Resources.getSystem(), bitmap), Uri.parse(str), optDouble);
                }

                /* renamed from: zzjh */
                public zzgu zzji() {
                    zzlx.this.zza(2, z3);
                    return null;
                }
            });
        }
    }

    private void zza(zzha.zza zza2) {
        if (zza2 instanceof zzgx) {
            final zzgx zzgx = (zzgx) zza2;
            zzb zzb2 = new zzb(this);
            final AnonymousClass3 r1 = new zzid() {
                public void zza(zzqw zzqw, Map<String, String> map) {
                    zzlx.this.zzb((zzhn) zzgx, map.get("asset"));
                }
            };
            zzb2.zzQU = r1;
            this.zzGN.zza((zzlw.zza) new zzlw.zza(this) {
                public void zze(zzjj zzjj) {
                    zzjj.zza("/nativeAdCustomClick", r1);
                }
            });
        }
    }

    private JSONObject zzaH(final String str) throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        if (zzjf()) {
            return null;
        }
        final zzqj zzqj = new zzqj();
        final zzb zzb2 = new zzb(this);
        this.zzGN.zza((zzlw.zza) new zzlw.zza() {
            public void zze(final zzjj zzjj) {
                AnonymousClass1 r0 = new zzid() {
                    public void zza(zzqw zzqw, Map<String, String> map) {
                        JSONObject jSONObject;
                        boolean z;
                        try {
                            String str = map.get("success");
                            String str2 = map.get("failure");
                            if (!TextUtils.isEmpty(str2)) {
                                jSONObject = new JSONObject(str2);
                                z = false;
                            } else {
                                jSONObject = new JSONObject(str);
                                z = true;
                            }
                            if (str.equals(jSONObject.optString("ads_id", ""))) {
                                zzjj.zzb("/nativeAdPreProcess", zzb2.zzQU);
                                if (z) {
                                    JSONArray optJSONArray = jSONObject.optJSONArray("ads");
                                    if (optJSONArray == null || optJSONArray.length() <= 0) {
                                        zzlx.this.zzU(3);
                                        zzqj.zzh(null);
                                        return;
                                    }
                                    zzqj.zzh(optJSONArray.getJSONObject(0));
                                    return;
                                }
                                zzlx.this.zzU(0);
                                zzac.zza(zzlx.this.zzjf(), (Object) "Unable to set the ad state error!");
                                zzqj.zzh(null);
                            }
                        } catch (JSONException e) {
                            zzpk.zzb("Malformed native JSON response.", e);
                        }
                    }
                };
                zzb2.zzQU = r0;
                zzjj.zza("/nativeAdPreProcess", (zzid) r0);
                try {
                    JSONObject jSONObject = new JSONObject(zzlx.this.zzPR.zzWm.body);
                    jSONObject.put("ads_id", str);
                    zzjj.zza("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
                } catch (JSONException e) {
                    zzpk.zzc("Exception occurred while invoking javascript", e);
                    zzqj.zzh(null);
                }
            }

            public void zzjd() {
                zzqj.zzh(null);
            }
        });
        return (JSONObject) zzqj.get(zzQl, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: finally extract failed */
    private zzpb zzb(zzha.zza zza2) {
        int i;
        synchronized (this.zzrJ) {
            try {
                int i2 = this.zzPY;
                if (zza2 == null && this.zzPY == -2) {
                    i2 = 0;
                }
                i = i2;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        zzha.zza zza3 = i != -2 ? null : zza2;
        zzec zzec = this.zzPR.zzTi.zzRy;
        List<String> list = this.zzPR.zzWm.zzKF;
        List<String> list2 = this.zzPR.zzWm.zzKG;
        List<String> list3 = this.zzQx;
        int i3 = this.zzPR.zzWm.orientation;
        long j = this.zzPR.zzWm.zzKL;
        String str = this.zzPR.zzTi.zzRB;
        zzeg zzeg = this.zzPR.zzvr;
        long j2 = this.zzPR.zzWm.zzSm;
        return new zzpb(zzec, (zzqw) null, list, i, list2, list3, i3, j, str, false, (zzjq) null, (zzkb) null, (String) null, (zzjr) null, (zzjt) null, 0, zzeg, j2, this.zzPR.zzWg, this.zzPR.zzWh, this.zzPR.zzWm.zzSs, this.zzQy, zza3, (zzoo) null, (List<String>) null, (List<String>) null, this.zzPR.zzWm.zzSF, this.zzPR.zzWm.zzSG, (String) null, this.zzPR.zzWm.zzKI, this.zzQz);
    }

    static zzqw zzb(zzqm<zzqw> zzqm) {
        try {
            return (zzqw) zzqm.get((long) zzgd.zzEs.get().intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            zzpk.zzc("InterruptedException occurred while waiting for video to load", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzpk.zzc("Exception occurred while waiting for video to load", e2);
            return null;
        }
    }

    private Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt(HtmlTags.B)));
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void zzb(zzhn zzhn, String str) {
        try {
            zzhr zzz = this.zzQv.zzz(zzhn.getCustomTemplateId());
            if (zzz != null) {
                zzz.zza(zzhn, str);
            }
        } catch (RemoteException e) {
            StringBuilder sb = new StringBuilder(40 + String.valueOf(str).length());
            sb.append("Failed to call onCustomClick for asset ");
            sb.append(str);
            sb.append(".");
            zzpk.zzc(sb.toString(), e);
        }
    }

    private String[] zzd(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    /* access modifiers changed from: private */
    public static List<Drawable> zzh(List<zzgu> list) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        for (zzgu zzfP : list) {
            arrayList.add((Drawable) zzd.zzF(zzfP.zzfP()));
        }
        return arrayList;
    }

    public void zzU(int i) {
        synchronized (this.zzrJ) {
            this.zzQw = true;
            this.zzPY = i;
        }
    }

    /* access modifiers changed from: package-private */
    public zzlw zza(Context context, zzpb.zza zza2, zzs zzs, zzaw zzaw) {
        return new zzlw(context, zza2, zzs, zzaw);
    }

    /* access modifiers changed from: package-private */
    public zzly zza(Context context, zzaw zzaw, zzpb.zza zza2, zzgl zzgl, zzs zzs) {
        return new zzly(context, zzaw, zza2, zzgl, zzs);
    }

    public zzqm<zzgu> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public List<zzqm<zzgu>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray jSONArray = z ? jSONObject.getJSONArray(str) : jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            zza(0, z);
            return arrayList;
        }
        int length = z3 ? jSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, z, z2));
        }
        return arrayList;
    }

    public Future<zzgu> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    public void zza(int i, boolean z) {
        if (z) {
            zzU(i);
        }
    }

    /* access modifiers changed from: protected */
    public zza zzc(JSONObject jSONObject) throws ExecutionException, InterruptedException, JSONException, TimeoutException {
        if (zzjf() || jSONObject == null) {
            return null;
        }
        String string = jSONObject.getString("template_id");
        boolean z = this.zzPR.zzTi.zzvF != null ? this.zzPR.zzTi.zzvF.zzHa : false;
        boolean z2 = this.zzPR.zzTi.zzvF != null ? this.zzPR.zzTi.zzvF.zzHc : false;
        if ("2".equals(string)) {
            return new zzlz(z, z2);
        }
        if ("1".equals(string)) {
            return new zzma(z, z2);
        }
        if ("3".equals(string)) {
            final String string2 = jSONObject.getString("custom_template_id");
            final zzqj zzqj = new zzqj();
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzqj.zzh(zzlx.this.zzQv.zzcu().get(string2));
                }
            });
            if (zzqj.get(zzQl, TimeUnit.MILLISECONDS) != null) {
                return new zzmb(z);
            }
            String valueOf = String.valueOf(jSONObject.getString("custom_template_id"));
            zzpk.e(valueOf.length() != 0 ? "No handler for custom template: ".concat(valueOf) : new String("No handler for custom template: "));
            return null;
        }
        zzU(0);
        return null;
    }

    public zzqm<zzqw> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return new zzqk(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzpk.zzbh("Required field 'vast_xml' is missing");
            return new zzqk(null);
        }
        return zza(this.mContext, this.zzGP, this.zzPR, this.zzsn, this.zzQv).zze(optJSONObject);
    }

    public zzqm<zzgs> zzd(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return new zzqk(null);
        }
        String optString = optJSONObject.optString(MimeTypes.BASE_TYPE_TEXT);
        final int optInt = optJSONObject.optInt("text_size", -1);
        final Integer zzb2 = zzb(optJSONObject, "text_color");
        Integer zzb3 = zzb(optJSONObject, "bg_color");
        final int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        final int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        final int i = (this.zzPR.zzTi.zzvF == null || this.zzPR.zzTi.zzvF.versionCode < 2) ? 1 : this.zzPR.zzTi.zzvF.zzHd;
        final boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        final String str = optString;
        final Integer num = zzb3;
        return zzql.zza(zzql.zzo(arrayList), new zzql.zza<List<zzgu>, zzgs>(this) {
            /* renamed from: zzj */
            public zzgs apply(List<zzgu> list) {
                if (list != null) {
                    try {
                        if (list.isEmpty()) {
                            return null;
                        }
                        return new zzgs(str, zzlx.zzh(list), num, zzb2, optInt > 0 ? Integer.valueOf(optInt) : null, optInt3 + optInt2, i, optBoolean);
                    } catch (RemoteException e) {
                        zzpk.zzb("Could not get attribution icon", e);
                    }
                }
                return null;
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002b  */
    /* renamed from: zzje */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzpb call() {
        /*
            r3 = this;
            com.google.android.gms.internal.zzlw r0 = r3.zzGN     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x0027, JSONException -> 0x0021, TimeoutException -> 0x001d }
            r0.zziU()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x0027, JSONException -> 0x0021, TimeoutException -> 0x001d }
            java.lang.String r0 = r3.zzjg()     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x0027, JSONException -> 0x0021, TimeoutException -> 0x001d }
            org.json.JSONObject r1 = r3.zzaH(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x0027, JSONException -> 0x0021, TimeoutException -> 0x001d }
            com.google.android.gms.internal.zzlx$zza r2 = r3.zzc(r1)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x0027, JSONException -> 0x0021, TimeoutException -> 0x001d }
            com.google.android.gms.internal.zzha$zza r0 = r3.zza((com.google.android.gms.internal.zzlx.zza) r2, (org.json.JSONObject) r1, (java.lang.String) r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x0027, JSONException -> 0x0021, TimeoutException -> 0x001d }
            r3.zza((com.google.android.gms.internal.zzha.zza) r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x0027, JSONException -> 0x0021, TimeoutException -> 0x001d }
            com.google.android.gms.internal.zzpb r0 = r3.zzb((com.google.android.gms.internal.zzha.zza) r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException -> 0x0027, JSONException -> 0x0021, TimeoutException -> 0x001d }
            return r0
        L_0x001d:
            r0 = move-exception
            java.lang.String r1 = "Timeout when loading native ad."
            goto L_0x0024
        L_0x0021:
            r0 = move-exception
            java.lang.String r1 = "Malformed native JSON response."
        L_0x0024:
            com.google.android.gms.internal.zzpk.zzc(r1, r0)
        L_0x0027:
            boolean r0 = r3.zzQw
            if (r0 != 0) goto L_0x002f
            r0 = 0
            r3.zzU(r0)
        L_0x002f:
            r0 = 0
            com.google.android.gms.internal.zzpb r0 = r3.zzb((com.google.android.gms.internal.zzha.zza) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlx.call():com.google.android.gms.internal.zzpb");
    }

    public boolean zzjf() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzQw;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public String zzjg() {
        return UUID.randomUUID().toString();
    }
}
