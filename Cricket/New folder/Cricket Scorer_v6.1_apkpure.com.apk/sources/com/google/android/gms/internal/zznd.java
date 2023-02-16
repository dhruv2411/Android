package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import com.google.android.gms.internal.zznm;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.itextpdf.text.html.HtmlTags;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

@zzme
public final class zznd {
    private static final SimpleDateFormat zzTJ = new SimpleDateFormat("yyyyMMdd", Locale.US);

    private static Integer zzB(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a7 A[Catch:{ JSONException -> 0x022b }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c7 A[Catch:{ JSONException -> 0x022b }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ce A[Catch:{ JSONException -> 0x022b }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d4 A[Catch:{ JSONException -> 0x022b }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0137 A[Catch:{ JSONException -> 0x022b }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0140 A[Catch:{ JSONException -> 0x022b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzmn zza(android.content.Context r50, com.google.android.gms.internal.zzmk r51, java.lang.String r52) {
        /*
            r9 = r51
            r15 = 0
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ JSONException -> 0x022b }
            r1 = r52
            r10.<init>(r1)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "ad_base_url"
            r11 = 0
            java.lang.String r1 = r10.optString(r1, r11)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r2 = "ad_url"
            java.lang.String r4 = r10.optString(r2, r11)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r2 = "ad_size"
            java.lang.String r13 = r10.optString(r2, r11)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r2 = "ad_slot_size"
            java.lang.String r40 = r10.optString(r2, r13)     // Catch:{ JSONException -> 0x022b }
            r12 = 1
            if (r9 == 0) goto L_0x002d
            int r2 = r9.zzRE     // Catch:{ JSONException -> 0x022b }
            if (r2 == 0) goto L_0x002d
            r24 = r12
            goto L_0x002f
        L_0x002d:
            r24 = r15
        L_0x002f:
            java.lang.String r2 = "ad_json"
            java.lang.String r2 = r10.optString(r2, r11)     // Catch:{ JSONException -> 0x022b }
            if (r2 != 0) goto L_0x003d
            java.lang.String r2 = "ad_html"
            java.lang.String r2 = r10.optString(r2, r11)     // Catch:{ JSONException -> 0x022b }
        L_0x003d:
            if (r2 != 0) goto L_0x0045
            java.lang.String r2 = "body"
            java.lang.String r2 = r10.optString(r2, r11)     // Catch:{ JSONException -> 0x022b }
        L_0x0045:
            java.lang.String r3 = "debug_dialog"
            java.lang.String r19 = r10.optString(r3, r11)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r3 = "debug_signals"
            java.lang.String r42 = r10.optString(r3, r11)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r3 = "interstitial_timeout"
            boolean r3 = r10.has(r3)     // Catch:{ JSONException -> 0x022b }
            r7 = -1
            if (r3 == 0) goto L_0x006c
            java.lang.String r3 = "interstitial_timeout"
            double r5 = r10.getDouble(r3)     // Catch:{ JSONException -> 0x022b }
            r16 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r5 = r5 * r16
            long r5 = (long) r5     // Catch:{ JSONException -> 0x022b }
            r16 = r5
            goto L_0x006e
        L_0x006c:
            r16 = r7
        L_0x006e:
            java.lang.String r3 = "orientation"
            java.lang.String r3 = r10.optString(r3, r11)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r5 = "portrait"
            boolean r5 = r5.equals(r3)     // Catch:{ JSONException -> 0x022b }
            r14 = -1
            if (r5 == 0) goto L_0x0088
            com.google.android.gms.internal.zzpp r3 = com.google.android.gms.ads.internal.zzw.zzcO()     // Catch:{ JSONException -> 0x022b }
            int r3 = r3.zzkR()     // Catch:{ JSONException -> 0x022b }
        L_0x0085:
            r18 = r3
            goto L_0x009b
        L_0x0088:
            java.lang.String r5 = "landscape"
            boolean r3 = r5.equals(r3)     // Catch:{ JSONException -> 0x022b }
            if (r3 == 0) goto L_0x0099
            com.google.android.gms.internal.zzpp r3 = com.google.android.gms.ads.internal.zzw.zzcO()     // Catch:{ JSONException -> 0x022b }
            int r3 = r3.zzkQ()     // Catch:{ JSONException -> 0x022b }
            goto L_0x0085
        L_0x0099:
            r18 = r14
        L_0x009b:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ JSONException -> 0x022b }
            if (r3 == 0) goto L_0x00c7
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch:{ JSONException -> 0x022b }
            if (r3 != 0) goto L_0x00c7
            com.google.android.gms.internal.zzqh r1 = r9.zzvn     // Catch:{ JSONException -> 0x022b }
            java.lang.String r3 = r1.zzba     // Catch:{ JSONException -> 0x022b }
            r5 = 0
            r6 = 0
            r20 = 0
            r21 = 0
            r1 = r9
            r2 = r50
            r7 = r20
            r8 = r21
            com.google.android.gms.internal.zzmn r1 = com.google.android.gms.internal.zznc.zza(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r2 = r1.zzNJ     // Catch:{ JSONException -> 0x022b }
            java.lang.String r3 = r1.body     // Catch:{ JSONException -> 0x022b }
            long r4 = r1.zzSr     // Catch:{ JSONException -> 0x022b }
            r20 = r4
            r4 = r3
            r3 = r2
            goto L_0x00cc
        L_0x00c7:
            r3 = r1
            r4 = r2
            r1 = r11
            r20 = -1
        L_0x00cc:
            if (r4 != 0) goto L_0x00d4
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn     // Catch:{ JSONException -> 0x022b }
            r1.<init>(r15)     // Catch:{ JSONException -> 0x022b }
            return r1
        L_0x00d4:
            java.lang.String r2 = "click_urls"
            org.json.JSONArray r2 = r10.optJSONArray(r2)     // Catch:{ JSONException -> 0x022b }
            if (r1 != 0) goto L_0x00de
            r5 = r11
            goto L_0x00e0
        L_0x00de:
            java.util.List<java.lang.String> r5 = r1.zzKF     // Catch:{ JSONException -> 0x022b }
        L_0x00e0:
            if (r2 == 0) goto L_0x00e7
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r5)     // Catch:{ JSONException -> 0x022b }
            r5 = r2
        L_0x00e7:
            java.lang.String r2 = "impression_urls"
            org.json.JSONArray r2 = r10.optJSONArray(r2)     // Catch:{ JSONException -> 0x022b }
            if (r1 != 0) goto L_0x00f1
            r6 = r11
            goto L_0x00f3
        L_0x00f1:
            java.util.List<java.lang.String> r6 = r1.zzKG     // Catch:{ JSONException -> 0x022b }
        L_0x00f3:
            if (r2 == 0) goto L_0x00fa
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r6)     // Catch:{ JSONException -> 0x022b }
            r6 = r2
        L_0x00fa:
            java.lang.String r2 = "manual_impression_urls"
            org.json.JSONArray r2 = r10.optJSONArray(r2)     // Catch:{ JSONException -> 0x022b }
            if (r1 != 0) goto L_0x0104
            r7 = r11
            goto L_0x0106
        L_0x0104:
            java.util.List<java.lang.String> r7 = r1.zzSp     // Catch:{ JSONException -> 0x022b }
        L_0x0106:
            if (r2 == 0) goto L_0x010f
            java.util.List r2 = zza((org.json.JSONArray) r2, (java.util.List<java.lang.String>) r7)     // Catch:{ JSONException -> 0x022b }
            r22 = r2
            goto L_0x0111
        L_0x010f:
            r22 = r7
        L_0x0111:
            if (r1 == 0) goto L_0x0127
            int r2 = r1.orientation     // Catch:{ JSONException -> 0x022b }
            if (r2 == r14) goto L_0x011b
            int r2 = r1.orientation     // Catch:{ JSONException -> 0x022b }
            r18 = r2
        L_0x011b:
            long r7 = r1.zzSm     // Catch:{ JSONException -> 0x022b }
            r25 = 0
            int r2 = (r7 > r25 ? 1 : (r7 == r25 ? 0 : -1))
            if (r2 <= 0) goto L_0x0127
            long r1 = r1.zzSm     // Catch:{ JSONException -> 0x022b }
            r7 = r1
            goto L_0x0129
        L_0x0127:
            r7 = r16
        L_0x0129:
            java.lang.String r1 = "active_view"
            java.lang.String r23 = r10.optString(r1)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "ad_is_javascript"
            boolean r25 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x022b }
            if (r25 == 0) goto L_0x0140
            java.lang.String r1 = "ad_passback_url"
            java.lang.String r1 = r10.optString(r1, r11)     // Catch:{ JSONException -> 0x022b }
            r26 = r1
            goto L_0x0142
        L_0x0140:
            r26 = r11
        L_0x0142:
            java.lang.String r1 = "mediation"
            boolean r14 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "custom_render_allowed"
            boolean r27 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "content_url_opted_out"
            boolean r28 = r10.optBoolean(r1, r12)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "content_vertical_opted_out"
            boolean r43 = r10.optBoolean(r1, r12)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "prefetch"
            boolean r29 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "refresh_interval_milliseconds"
            r11 = -1
            long r16 = r10.optLong(r1, r11)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "mediation_config_cache_time_milliseconds"
            long r11 = r10.optLong(r1, r11)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "gws_query_id"
            java.lang.String r2 = ""
            java.lang.String r30 = r10.optString(r1, r2)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "height"
            java.lang.String r2 = "fluid"
            java.lang.String r15 = ""
            java.lang.String r2 = r10.optString(r2, r15)     // Catch:{ JSONException -> 0x022b }
            boolean r31 = r1.equals(r2)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "native_express"
            r2 = 0
            boolean r32 = r10.optBoolean(r1, r2)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "video_start_urls"
            org.json.JSONArray r1 = r10.optJSONArray(r1)     // Catch:{ JSONException -> 0x022b }
            r2 = 0
            java.util.List r33 = zza((org.json.JSONArray) r1, (java.util.List<java.lang.String>) r2)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "video_complete_urls"
            org.json.JSONArray r1 = r10.optJSONArray(r1)     // Catch:{ JSONException -> 0x022b }
            java.util.List r34 = zza((org.json.JSONArray) r1, (java.util.List<java.lang.String>) r2)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "rewards"
            org.json.JSONArray r1 = r10.optJSONArray(r1)     // Catch:{ JSONException -> 0x022b }
            com.google.android.gms.internal.zzoo r35 = com.google.android.gms.internal.zzoo.zza(r1)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "use_displayed_impression"
            r15 = 0
            boolean r36 = r10.optBoolean(r1, r15)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "auto_protection_configuration"
            org.json.JSONObject r1 = r10.optJSONObject(r1)     // Catch:{ JSONException -> 0x022b }
            com.google.android.gms.internal.zzmp r37 = com.google.android.gms.internal.zzmp.zzf(r1)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "set_cookie"
            java.lang.String r2 = ""
            java.lang.String r38 = r10.optString(r1, r2)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "remote_ping_urls"
            org.json.JSONArray r1 = r10.optJSONArray(r1)     // Catch:{ JSONException -> 0x022b }
            r2 = 0
            java.util.List r39 = zza((org.json.JSONArray) r1, (java.util.List<java.lang.String>) r2)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "safe_browsing"
            org.json.JSONObject r1 = r10.optJSONObject(r1)     // Catch:{ JSONException -> 0x022b }
            com.google.android.gms.internal.zzor r41 = com.google.android.gms.internal.zzor.zzh(r1)     // Catch:{ JSONException -> 0x022b }
            java.lang.String r1 = "render_in_browser"
            boolean r2 = r9.zzKJ     // Catch:{ JSONException -> 0x022b }
            boolean r44 = r10.optBoolean(r1, r2)     // Catch:{ JSONException -> 0x022b }
            com.google.android.gms.internal.zzmn r45 = new com.google.android.gms.internal.zzmn     // Catch:{ JSONException -> 0x022b }
            boolean r10 = r9.zzRG     // Catch:{ JSONException -> 0x022b }
            boolean r2 = r9.zzRV     // Catch:{ JSONException -> 0x022b }
            boolean r1 = r9.zzSh     // Catch:{ JSONException -> 0x022b }
            r46 = r1
            r1 = r45
            r47 = r2
            r2 = r9
            r9 = r14
            r48 = r10
            r10 = r11
            r12 = r22
            r22 = r13
            r13 = r16
            r15 = r18
            r16 = r22
            r17 = r20
            r20 = r25
            r21 = r26
            r22 = r23
            r23 = r27
            r25 = r48
            r26 = r28
            r27 = r29
            r28 = r30
            r29 = r31
            r30 = r32
            r31 = r35
            r32 = r33
            r33 = r34
            r34 = r36
            r35 = r37
            r36 = r47
            r37 = r38
            r38 = r39
            r39 = r44
            r44 = r46
            r1.<init>(r2, r3, r4, r5, r6, r7, r9, r10, r12, r13, r15, r16, r17, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44)     // Catch:{ JSONException -> 0x022b }
            return r45
        L_0x022b:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "Could not parse the inline ad response: "
            java.lang.String r1 = r1.getMessage()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r3 = r1.length()
            if (r3 == 0) goto L_0x0242
            java.lang.String r1 = r2.concat(r1)
            goto L_0x0247
        L_0x0242:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
        L_0x0247:
            com.google.android.gms.internal.zzpk.zzbh(r1)
            com.google.android.gms.internal.zzmn r1 = new com.google.android.gms.internal.zzmn
            r2 = 0
            r1.<init>(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zznd.zza(android.content.Context, com.google.android.gms.internal.zzmk, java.lang.String):com.google.android.gms.internal.zzmn");
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new LinkedList<>();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x028b A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x02c2 A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x02d3 A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x02f9 A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0311 A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0320 A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x034b A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0366 A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x038c A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03ba A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01f2 A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x020f A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0219 A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x023b A[Catch:{ JSONException -> 0x03e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x025f A[Catch:{ JSONException -> 0x03e8 }] */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject zza(android.content.Context r17, com.google.android.gms.internal.zzna r18) {
        /*
            r1 = r17
            r2 = r18
            com.google.android.gms.internal.zzmk r3 = r2.zzTi
            android.location.Location r4 = r2.zzzb
            com.google.android.gms.internal.zzni r5 = r2.zzTj
            android.os.Bundle r6 = r2.zzRF
            org.json.JSONObject r7 = r2.zzTk
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ JSONException -> 0x03e8 }
            r8.<init>()     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r9 = "extra_caps"
            com.google.android.gms.internal.zzfz<java.lang.String> r10 = com.google.android.gms.internal.zzgd.zzEe     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Object r10 = r10.get()     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            java.util.List<java.lang.String> r9 = r2.zzRM     // Catch:{ JSONException -> 0x03e8 }
            int r9 = r9.size()     // Catch:{ JSONException -> 0x03e8 }
            if (r9 <= 0) goto L_0x0033
            java.lang.String r9 = "eid"
            java.lang.String r10 = ","
            java.util.List<java.lang.String> r11 = r2.zzRM     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r10 = android.text.TextUtils.join(r10, r11)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0033:
            android.os.Bundle r9 = r3.zzRx     // Catch:{ JSONException -> 0x03e8 }
            if (r9 == 0) goto L_0x003e
            java.lang.String r9 = "ad_pos"
            android.os.Bundle r10 = r3.zzRx     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
        L_0x003e:
            com.google.android.gms.internal.zzec r9 = r3.zzRy     // Catch:{ JSONException -> 0x03e8 }
            zza((java.util.HashMap<java.lang.String, java.lang.Object>) r8, (com.google.android.gms.internal.zzec) r9)     // Catch:{ JSONException -> 0x03e8 }
            com.google.android.gms.internal.zzeg r9 = r3.zzvr     // Catch:{ JSONException -> 0x03e8 }
            com.google.android.gms.internal.zzeg[] r9 = r9.zzzA     // Catch:{ JSONException -> 0x03e8 }
            if (r9 != 0) goto L_0x0060
            java.lang.String r9 = "format"
            com.google.android.gms.internal.zzeg r12 = r3.zzvr     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r12 = r12.zzzy     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r12)     // Catch:{ JSONException -> 0x03e8 }
            com.google.android.gms.internal.zzeg r9 = r3.zzvr     // Catch:{ JSONException -> 0x03e8 }
            boolean r9 = r9.zzzC     // Catch:{ JSONException -> 0x03e8 }
            if (r9 == 0) goto L_0x0090
            java.lang.String r9 = "fluid"
            java.lang.String r12 = "height"
            r8.put(r9, r12)     // Catch:{ JSONException -> 0x03e8 }
            goto L_0x0090
        L_0x0060:
            com.google.android.gms.internal.zzeg r9 = r3.zzvr     // Catch:{ JSONException -> 0x03e8 }
            com.google.android.gms.internal.zzeg[] r9 = r9.zzzA     // Catch:{ JSONException -> 0x03e8 }
            int r12 = r9.length     // Catch:{ JSONException -> 0x03e8 }
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x0068:
            if (r13 >= r12) goto L_0x0090
            r10 = r9[r13]     // Catch:{ JSONException -> 0x03e8 }
            boolean r11 = r10.zzzC     // Catch:{ JSONException -> 0x03e8 }
            if (r11 != 0) goto L_0x007a
            if (r14 != 0) goto L_0x007a
            java.lang.String r11 = "format"
            java.lang.String r14 = r10.zzzy     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r11, r14)     // Catch:{ JSONException -> 0x03e8 }
            r14 = 1
        L_0x007a:
            boolean r10 = r10.zzzC     // Catch:{ JSONException -> 0x03e8 }
            if (r10 == 0) goto L_0x0088
            if (r15 != 0) goto L_0x0088
            java.lang.String r10 = "fluid"
            java.lang.String r11 = "height"
            r8.put(r10, r11)     // Catch:{ JSONException -> 0x03e8 }
            r15 = 1
        L_0x0088:
            if (r14 == 0) goto L_0x008d
            if (r15 == 0) goto L_0x008d
            goto L_0x0090
        L_0x008d:
            int r13 = r13 + 1
            goto L_0x0068
        L_0x0090:
            com.google.android.gms.internal.zzeg r9 = r3.zzvr     // Catch:{ JSONException -> 0x03e8 }
            int r9 = r9.width     // Catch:{ JSONException -> 0x03e8 }
            r10 = -1
            if (r9 != r10) goto L_0x009e
            java.lang.String r9 = "smart_w"
            java.lang.String r11 = "full"
            r8.put(r9, r11)     // Catch:{ JSONException -> 0x03e8 }
        L_0x009e:
            com.google.android.gms.internal.zzeg r9 = r3.zzvr     // Catch:{ JSONException -> 0x03e8 }
            int r9 = r9.height     // Catch:{ JSONException -> 0x03e8 }
            r11 = -2
            if (r9 != r11) goto L_0x00ac
            java.lang.String r9 = "smart_h"
            java.lang.String r12 = "auto"
            r8.put(r9, r12)     // Catch:{ JSONException -> 0x03e8 }
        L_0x00ac:
            com.google.android.gms.internal.zzeg r9 = r3.zzvr     // Catch:{ JSONException -> 0x03e8 }
            com.google.android.gms.internal.zzeg[] r9 = r9.zzzA     // Catch:{ JSONException -> 0x03e8 }
            if (r9 == 0) goto L_0x0122
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x03e8 }
            r9.<init>()     // Catch:{ JSONException -> 0x03e8 }
            com.google.android.gms.internal.zzeg r12 = r3.zzvr     // Catch:{ JSONException -> 0x03e8 }
            com.google.android.gms.internal.zzeg[] r12 = r12.zzzA     // Catch:{ JSONException -> 0x03e8 }
            int r13 = r12.length     // Catch:{ JSONException -> 0x03e8 }
            r14 = 0
            r15 = 0
        L_0x00be:
            if (r14 >= r13) goto L_0x0108
            r11 = r12[r14]     // Catch:{ JSONException -> 0x03e8 }
            boolean r10 = r11.zzzC     // Catch:{ JSONException -> 0x03e8 }
            if (r10 == 0) goto L_0x00cb
            r16 = r12
            r12 = -2
            r15 = 1
            goto L_0x0101
        L_0x00cb:
            int r10 = r9.length()     // Catch:{ JSONException -> 0x03e8 }
            if (r10 == 0) goto L_0x00d6
            java.lang.String r10 = "|"
            r9.append(r10)     // Catch:{ JSONException -> 0x03e8 }
        L_0x00d6:
            int r10 = r11.width     // Catch:{ JSONException -> 0x03e8 }
            r16 = r12
            r12 = -1
            if (r10 != r12) goto L_0x00e5
            int r10 = r11.widthPixels     // Catch:{ JSONException -> 0x03e8 }
            float r10 = (float) r10     // Catch:{ JSONException -> 0x03e8 }
            float r12 = r5.zzxk     // Catch:{ JSONException -> 0x03e8 }
            float r10 = r10 / r12
            int r10 = (int) r10     // Catch:{ JSONException -> 0x03e8 }
            goto L_0x00e7
        L_0x00e5:
            int r10 = r11.width     // Catch:{ JSONException -> 0x03e8 }
        L_0x00e7:
            r9.append(r10)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r10 = "x"
            r9.append(r10)     // Catch:{ JSONException -> 0x03e8 }
            int r10 = r11.height     // Catch:{ JSONException -> 0x03e8 }
            r12 = -2
            if (r10 != r12) goto L_0x00fc
            int r10 = r11.heightPixels     // Catch:{ JSONException -> 0x03e8 }
            float r10 = (float) r10     // Catch:{ JSONException -> 0x03e8 }
            float r11 = r5.zzxk     // Catch:{ JSONException -> 0x03e8 }
            float r10 = r10 / r11
            int r10 = (int) r10     // Catch:{ JSONException -> 0x03e8 }
            goto L_0x00fe
        L_0x00fc:
            int r10 = r11.height     // Catch:{ JSONException -> 0x03e8 }
        L_0x00fe:
            r9.append(r10)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0101:
            int r14 = r14 + 1
            r11 = r12
            r12 = r16
            r10 = -1
            goto L_0x00be
        L_0x0108:
            if (r15 == 0) goto L_0x011d
            int r10 = r9.length()     // Catch:{ JSONException -> 0x03e8 }
            if (r10 == 0) goto L_0x0117
            java.lang.String r10 = "|"
            r11 = 0
            r9.insert(r11, r10)     // Catch:{ JSONException -> 0x03e8 }
            goto L_0x0118
        L_0x0117:
            r11 = 0
        L_0x0118:
            java.lang.String r10 = "320x50"
            r9.insert(r11, r10)     // Catch:{ JSONException -> 0x03e8 }
        L_0x011d:
            java.lang.String r10 = "sz"
            r8.put(r10, r9)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0122:
            int r9 = r3.zzRE     // Catch:{ JSONException -> 0x03e8 }
            if (r9 == 0) goto L_0x0152
            java.lang.String r9 = "native_version"
            int r10 = r3.zzRE     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r9 = "native_templates"
            java.util.List<java.lang.String> r10 = r3.zzvK     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r9 = "native_image_orientation"
            com.google.android.gms.internal.zzhc r10 = r3.zzvF     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r10 = zzc(r10)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            java.util.List<java.lang.String> r9 = r3.zzRN     // Catch:{ JSONException -> 0x03e8 }
            boolean r9 = r9.isEmpty()     // Catch:{ JSONException -> 0x03e8 }
            if (r9 != 0) goto L_0x0152
            java.lang.String r9 = "native_custom_templates"
            java.util.List<java.lang.String> r10 = r3.zzRN     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0152:
            com.google.android.gms.internal.zzeg r9 = r3.zzvr     // Catch:{ JSONException -> 0x03e8 }
            boolean r9 = r9.zzzD     // Catch:{ JSONException -> 0x03e8 }
            if (r9 == 0) goto L_0x0162
            java.lang.String r9 = "ene"
            r10 = 1
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r10)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r11)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0162:
            com.google.android.gms.internal.zzfc r9 = r3.zzvH     // Catch:{ JSONException -> 0x03e8 }
            if (r9 == 0) goto L_0x017d
            java.lang.String r9 = "is_icon_ad"
            r10 = 1
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r9 = "icon_ad_expansion_behavior"
            com.google.android.gms.internal.zzfc r10 = r3.zzvH     // Catch:{ JSONException -> 0x03e8 }
            int r10 = r10.zzzZ     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
        L_0x017d:
            java.lang.String r9 = "slotname"
            java.lang.String r10 = r3.zzvl     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r9 = "pn"
            android.content.pm.ApplicationInfo r10 = r3.applicationInfo     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r10 = r10.packageName     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            android.content.pm.PackageInfo r9 = r3.zzRz     // Catch:{ JSONException -> 0x03e8 }
            if (r9 == 0) goto L_0x019e
            java.lang.String r9 = "vc"
            android.content.pm.PackageInfo r10 = r3.zzRz     // Catch:{ JSONException -> 0x03e8 }
            int r10 = r10.versionCode     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
        L_0x019e:
            java.lang.String r9 = "ms"
            java.lang.String r10 = r2.zzRA     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r9 = "seq_num"
            java.lang.String r10 = r3.zzRB     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r9 = "session_id"
            java.lang.String r10 = r3.zzRC     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r9 = "js"
            com.google.android.gms.internal.zzqh r10 = r3.zzvn     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r10 = r10.zzba     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r9, r10)     // Catch:{ JSONException -> 0x03e8 }
            com.google.android.gms.internal.zznm$zza r9 = r2.zzTg     // Catch:{ JSONException -> 0x03e8 }
            android.os.Bundle r10 = r3.zzSa     // Catch:{ JSONException -> 0x03e8 }
            android.os.Bundle r11 = r2.zzTf     // Catch:{ JSONException -> 0x03e8 }
            zza(r8, r5, r9, r10, r11)     // Catch:{ JSONException -> 0x03e8 }
            zza((java.util.HashMap<java.lang.String, java.lang.Object>) r8, (com.google.android.gms.internal.zzna) r2, (android.content.Context) r1)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r2 = "platform"
            java.lang.String r9 = android.os.Build.MANUFACTURER     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r2, r9)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r2 = "submodel"
            java.lang.String r9 = android.os.Build.MODEL     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r2, r9)     // Catch:{ JSONException -> 0x03e8 }
            r2 = 2
            if (r4 == 0) goto L_0x01dd
        L_0x01d9:
            zza((java.util.HashMap<java.lang.String, java.lang.Object>) r8, (android.location.Location) r4)     // Catch:{ JSONException -> 0x03e8 }
            goto L_0x01ee
        L_0x01dd:
            com.google.android.gms.internal.zzec r4 = r3.zzRy     // Catch:{ JSONException -> 0x03e8 }
            int r4 = r4.versionCode     // Catch:{ JSONException -> 0x03e8 }
            if (r4 < r2) goto L_0x01ee
            com.google.android.gms.internal.zzec r4 = r3.zzRy     // Catch:{ JSONException -> 0x03e8 }
            android.location.Location r4 = r4.zzzb     // Catch:{ JSONException -> 0x03e8 }
            if (r4 == 0) goto L_0x01ee
            com.google.android.gms.internal.zzec r4 = r3.zzRy     // Catch:{ JSONException -> 0x03e8 }
            android.location.Location r4 = r4.zzzb     // Catch:{ JSONException -> 0x03e8 }
            goto L_0x01d9
        L_0x01ee:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            if (r4 < r2) goto L_0x01f9
            java.lang.String r4 = "quality_signals"
            android.os.Bundle r9 = r3.zzRD     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r9)     // Catch:{ JSONException -> 0x03e8 }
        L_0x01f9:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r9 = 4
            if (r4 < r9) goto L_0x020d
            boolean r4 = r3.zzRG     // Catch:{ JSONException -> 0x03e8 }
            if (r4 == 0) goto L_0x020d
            java.lang.String r4 = "forceHttps"
            boolean r9 = r3.zzRG     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r9)     // Catch:{ JSONException -> 0x03e8 }
        L_0x020d:
            if (r6 == 0) goto L_0x0214
            java.lang.String r4 = "content_info"
            r8.put(r4, r6)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0214:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r6 = 5
            if (r4 < r6) goto L_0x023b
            java.lang.String r4 = "u_sd"
            float r5 = r3.zzxk     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r4 = "sh"
            int r5 = r3.zzRI     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r4 = "sw"
            int r5 = r3.zzRH     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0237:
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x03e8 }
            goto L_0x025a
        L_0x023b:
            java.lang.String r4 = "u_sd"
            float r6 = r5.zzxk     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r6)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r4 = "sh"
            int r6 = r5.zzRI     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r6)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r4 = "sw"
            int r5 = r5.zzRH     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ JSONException -> 0x03e8 }
            goto L_0x0237
        L_0x025a:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r5 = 6
            if (r4 < r5) goto L_0x0286
            java.lang.String r4 = r3.zzRJ     // Catch:{ JSONException -> 0x03e8 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ JSONException -> 0x03e8 }
            if (r4 != 0) goto L_0x027b
            java.lang.String r4 = "view_hierarchy"
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0274 }
            java.lang.String r6 = r3.zzRJ     // Catch:{ JSONException -> 0x0274 }
            r5.<init>(r6)     // Catch:{ JSONException -> 0x0274 }
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x0274 }
            goto L_0x027b
        L_0x0274:
            r0 = move-exception
            r4 = r0
            java.lang.String r5 = "Problem serializing view hierarchy to JSON"
            com.google.android.gms.internal.zzpk.zzc(r5, r4)     // Catch:{ JSONException -> 0x03e8 }
        L_0x027b:
            java.lang.String r4 = "correlation_id"
            long r5 = r3.zzRK     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0286:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r5 = 7
            if (r4 < r5) goto L_0x0292
            java.lang.String r4 = "request_id"
            java.lang.String r5 = r3.zzRL     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0292:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r5 = 11
            if (r4 < r5) goto L_0x02a7
            com.google.android.gms.internal.zzmr r4 = r3.zzRP     // Catch:{ JSONException -> 0x03e8 }
            if (r4 == 0) goto L_0x02a7
            java.lang.String r4 = "capability"
            com.google.android.gms.internal.zzmr r5 = r3.zzRP     // Catch:{ JSONException -> 0x03e8 }
            android.os.Bundle r5 = r5.toBundle()     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x03e8 }
        L_0x02a7:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r5 = 12
            if (r4 < r5) goto L_0x02bc
            java.lang.String r4 = r3.zzRQ     // Catch:{ JSONException -> 0x03e8 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ JSONException -> 0x03e8 }
            if (r4 != 0) goto L_0x02bc
            java.lang.String r4 = "anchor"
            java.lang.String r5 = r3.zzRQ     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x03e8 }
        L_0x02bc:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r5 = 13
            if (r4 < r5) goto L_0x02cd
            java.lang.String r4 = "android_app_volume"
            float r5 = r3.zzRR     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x03e8 }
        L_0x02cd:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r5 = 18
            if (r4 < r5) goto L_0x02de
            java.lang.String r4 = "android_app_muted"
            boolean r6 = r3.zzRX     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r6)     // Catch:{ JSONException -> 0x03e8 }
        L_0x02de:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r6 = 14
            if (r4 < r6) goto L_0x02f3
            int r4 = r3.zzRS     // Catch:{ JSONException -> 0x03e8 }
            if (r4 <= 0) goto L_0x02f3
            java.lang.String r4 = "target_api"
            int r6 = r3.zzRS     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r6)     // Catch:{ JSONException -> 0x03e8 }
        L_0x02f3:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r6 = 15
            if (r4 < r6) goto L_0x030b
            java.lang.String r4 = "scroll_index"
            int r6 = r3.zzRT     // Catch:{ JSONException -> 0x03e8 }
            r12 = -1
            if (r6 != r12) goto L_0x0301
            goto L_0x0304
        L_0x0301:
            int r10 = r3.zzRT     // Catch:{ JSONException -> 0x03e8 }
            r12 = r10
        L_0x0304:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r12)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r6)     // Catch:{ JSONException -> 0x03e8 }
        L_0x030b:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r6 = 16
            if (r4 < r6) goto L_0x031c
            java.lang.String r4 = "_activity_context"
            boolean r6 = r3.zzRU     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r6)     // Catch:{ JSONException -> 0x03e8 }
        L_0x031c:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            if (r4 < r5) goto L_0x0347
            java.lang.String r4 = r3.zzRY     // Catch:{ JSONException -> 0x03e8 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ JSONException -> 0x03e8 }
            if (r4 != 0) goto L_0x033c
            java.lang.String r4 = "app_settings"
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0335 }
            java.lang.String r9 = r3.zzRY     // Catch:{ JSONException -> 0x0335 }
            r6.<init>(r9)     // Catch:{ JSONException -> 0x0335 }
            r8.put(r4, r6)     // Catch:{ JSONException -> 0x0335 }
            goto L_0x033c
        L_0x0335:
            r0 = move-exception
            r4 = r0
            java.lang.String r6 = "Problem creating json from app settings"
            com.google.android.gms.internal.zzpk.zzc(r6, r4)     // Catch:{ JSONException -> 0x03e8 }
        L_0x033c:
            java.lang.String r4 = "render_in_browser"
            boolean r6 = r3.zzKJ     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r6)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0347:
            int r4 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            if (r4 < r5) goto L_0x0356
            java.lang.String r4 = "android_num_video_cache_tasks"
            int r5 = r3.zzRZ     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r4, r5)     // Catch:{ JSONException -> 0x03e8 }
        L_0x0356:
            com.google.android.gms.internal.zzqh r4 = r3.zzvn     // Catch:{ JSONException -> 0x03e8 }
            zza((android.content.Context) r1, (java.util.HashMap<java.lang.String, java.lang.Object>) r8, (com.google.android.gms.internal.zzqh) r4)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r1 = "cache_state"
            r8.put(r1, r7)     // Catch:{ JSONException -> 0x03e8 }
            int r1 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r4 = 19
            if (r1 < r4) goto L_0x036d
            java.lang.String r1 = "gct"
            java.lang.String r4 = r3.zzSb     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r1, r4)     // Catch:{ JSONException -> 0x03e8 }
        L_0x036d:
            int r1 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r4 = 21
            if (r1 < r4) goto L_0x037e
            boolean r1 = r3.zzSc     // Catch:{ JSONException -> 0x03e8 }
            if (r1 == 0) goto L_0x037e
            java.lang.String r1 = "de"
            java.lang.String r4 = "1"
            r8.put(r1, r4)     // Catch:{ JSONException -> 0x03e8 }
        L_0x037e:
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.internal.zzgd.zzDc     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Object r1 = r1.get()     // Catch:{ JSONException -> 0x03e8 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ JSONException -> 0x03e8 }
            boolean r1 = r1.booleanValue()     // Catch:{ JSONException -> 0x03e8 }
            if (r1 == 0) goto L_0x038f
            zza((java.util.HashMap<java.lang.String, java.lang.Object>) r8, (com.google.android.gms.internal.zzmk) r3)     // Catch:{ JSONException -> 0x03e8 }
        L_0x038f:
            int r1 = r3.versionCode     // Catch:{ JSONException -> 0x03e8 }
            r4 = 22
            if (r1 < r4) goto L_0x03b4
            com.google.android.gms.internal.zzow r1 = com.google.android.gms.ads.internal.zzw.zzdl()     // Catch:{ JSONException -> 0x03e8 }
            boolean r1 = r1.zzjQ()     // Catch:{ JSONException -> 0x03e8 }
            if (r1 == 0) goto L_0x03b4
            java.lang.String r1 = "gmp_app_id"
            java.lang.String r4 = r3.zzSe     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r1, r4)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r1 = "fbs_aiid"
            java.lang.String r4 = r3.zzSf     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r1, r4)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r1 = "fbs_aeid"
            java.lang.String r3 = r3.zzSg     // Catch:{ JSONException -> 0x03e8 }
            r8.put(r1, r3)     // Catch:{ JSONException -> 0x03e8 }
        L_0x03b4:
            boolean r1 = com.google.android.gms.internal.zzpk.zzak(r2)     // Catch:{ JSONException -> 0x03e8 }
            if (r1 == 0) goto L_0x03df
            com.google.android.gms.internal.zzpo r1 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ JSONException -> 0x03e8 }
            org.json.JSONObject r1 = r1.zzQ((java.util.Map<java.lang.String, ?>) r8)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r1 = r1.toString(r2)     // Catch:{ JSONException -> 0x03e8 }
            java.lang.String r2 = "Ad Request JSON: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ JSONException -> 0x03e8 }
            int r3 = r1.length()     // Catch:{ JSONException -> 0x03e8 }
            if (r3 == 0) goto L_0x03d7
            java.lang.String r1 = r2.concat(r1)     // Catch:{ JSONException -> 0x03e8 }
            goto L_0x03dc
        L_0x03d7:
            java.lang.String r1 = new java.lang.String     // Catch:{ JSONException -> 0x03e8 }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x03e8 }
        L_0x03dc:
            com.google.android.gms.internal.zzpk.v(r1)     // Catch:{ JSONException -> 0x03e8 }
        L_0x03df:
            com.google.android.gms.internal.zzpo r1 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ JSONException -> 0x03e8 }
            org.json.JSONObject r1 = r1.zzQ((java.util.Map<java.lang.String, ?>) r8)     // Catch:{ JSONException -> 0x03e8 }
            return r1
        L_0x03e8:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "Problem serializing ad request to JSON: "
            java.lang.String r1 = r1.getMessage()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r3 = r1.length()
            if (r3 == 0) goto L_0x03ff
            java.lang.String r1 = r2.concat(r1)
            goto L_0x0404
        L_0x03ff:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
        L_0x0404:
            com.google.android.gms.internal.zzpk.zzbh(r1)
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zznd.zza(android.content.Context, com.google.android.gms.internal.zzna):org.json.JSONObject");
    }

    private static void zza(Context context, HashMap<String, Object> hashMap, zzqh zzqh) {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle2.putString("cl", "149960398");
        bundle2.putString("rapid_rc", "dev");
        bundle2.putString("rapid_rollup", HttpRequest.METHOD_HEAD);
        bundle.putBundle("build_meta", bundle2);
        bundle.putString("mf", Boolean.toString(zzgd.zzEg.get().booleanValue()));
        bundle.putBoolean("instant_app", zzadg.zzbi(context).zzzx());
        bundle.putBoolean("lite", zzqh.zzYZ);
        hashMap.put("sdk_env", bundle);
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    private static void zza(HashMap<String, Object> hashMap, zzec zzec) {
        String zzkF = zzpi.zzkF();
        if (zzkF != null) {
            hashMap.put("abf", zzkF);
        }
        if (zzec.zzyT != -1) {
            hashMap.put("cust_age", zzTJ.format(new Date(zzec.zzyT)));
        }
        if (zzec.extras != null) {
            hashMap.put("extras", zzec.extras);
        }
        if (zzec.zzyU != -1) {
            hashMap.put("cust_gender", Integer.valueOf(zzec.zzyU));
        }
        if (zzec.zzyV != null) {
            hashMap.put("kw", zzec.zzyV);
        }
        if (zzec.zzyX != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(zzec.zzyX));
        }
        if (zzec.zzyW) {
            hashMap.put("adtest", "on");
        }
        if (zzec.versionCode >= 2) {
            if (zzec.zzyY) {
                hashMap.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty(zzec.zzyZ)) {
                hashMap.put("ppid", zzec.zzyZ);
            }
            if (zzec.zzza != null) {
                zza(hashMap, zzec.zzza);
            }
        }
        if (zzec.versionCode >= 3 && zzec.zzzc != null) {
            hashMap.put("url", zzec.zzzc);
        }
        if (zzec.versionCode >= 5) {
            if (zzec.zzze != null) {
                hashMap.put("custom_targeting", zzec.zzze);
            }
            if (zzec.zzzf != null) {
                hashMap.put("category_exclusions", zzec.zzzf);
            }
            if (zzec.zzzg != null) {
                hashMap.put("request_agent", zzec.zzzg);
            }
        }
        if (zzec.versionCode >= 6 && zzec.zzzh != null) {
            hashMap.put("request_pkg", zzec.zzzh);
        }
        if (zzec.versionCode >= 7) {
            hashMap.put("is_designed_for_families", Boolean.valueOf(zzec.zzzi));
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zzfp zzfp) {
        String str;
        if (Color.alpha(zzfp.zzAH) != 0) {
            hashMap.put("acolor", zzab(zzfp.zzAH));
        }
        if (Color.alpha(zzfp.backgroundColor) != 0) {
            hashMap.put(HtmlTags.BGCOLOR, zzab(zzfp.backgroundColor));
        }
        if (!(Color.alpha(zzfp.zzAI) == 0 || Color.alpha(zzfp.zzAJ) == 0)) {
            hashMap.put("gradientto", zzab(zzfp.zzAI));
            hashMap.put("gradientfrom", zzab(zzfp.zzAJ));
        }
        if (Color.alpha(zzfp.zzAK) != 0) {
            hashMap.put("bcolor", zzab(zzfp.zzAK));
        }
        hashMap.put("bthick", Integer.toString(zzfp.zzAL));
        String str2 = null;
        switch (zzfp.zzAM) {
            case 0:
                str = "none";
                break;
            case 1:
                str = "dashed";
                break;
            case 2:
                str = "dotted";
                break;
            case 3:
                str = "solid";
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            hashMap.put("btype", str);
        }
        switch (zzfp.zzAN) {
            case 0:
                str2 = "light";
                break;
            case 1:
                str2 = FirebaseAnalytics.Param.MEDIUM;
                break;
            case 2:
                str2 = "dark";
                break;
        }
        if (str2 != null) {
            hashMap.put("callbuttoncolor", str2);
        }
        if (zzfp.zzAO != null) {
            hashMap.put("channel", zzfp.zzAO);
        }
        if (Color.alpha(zzfp.zzAP) != 0) {
            hashMap.put("dcolor", zzab(zzfp.zzAP));
        }
        if (zzfp.zzAQ != null) {
            hashMap.put(HtmlTags.FONT, zzfp.zzAQ);
        }
        if (Color.alpha(zzfp.zzAR) != 0) {
            hashMap.put("hcolor", zzab(zzfp.zzAR));
        }
        hashMap.put("headersize", Integer.toString(zzfp.zzAS));
        if (zzfp.zzAT != null) {
            hashMap.put("q", zzfp.zzAT);
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zzmk zzmk) {
        String str = zzmk.zzvr.zzzy;
        boolean z = true;
        boolean z2 = str.equals("interstitial_mb") || str.equals("reward_mb");
        Bundle bundle = zzmk.zzSd;
        if (bundle == null) {
            z = false;
        }
        if (z2 && z) {
            Bundle bundle2 = new Bundle();
            bundle2.putBundle("interstitial_pool", bundle);
            hashMap.put("counters", bundle2);
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zzna zzna, Context context) {
        String str;
        String str2;
        Bundle bundle = new Bundle();
        bundle.putString("doritos", zzna.zzTh);
        if (zzgd.zzCS.get().booleanValue()) {
            String str3 = null;
            boolean z = false;
            if (zzna.zzpR != null) {
                str3 = zzna.zzpR.getId();
                z = zzna.zzpR.isLimitAdTrackingEnabled();
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString("rdid", str3);
                bundle.putBoolean("is_lat", z);
                str = "idtype";
                str2 = "adid";
            } else {
                bundle.putString("pdid", zzel.zzeT().zzae(context));
                str = "pdidtype";
                str2 = "ssaid";
            }
            bundle.putString(str, str2);
        }
        hashMap.put("pii", bundle);
    }

    private static void zza(HashMap<String, Object> hashMap, zzni zzni, zznm.zza zza, Bundle bundle, Bundle bundle2) {
        hashMap.put("am", Integer.valueOf(zzni.zzUE));
        hashMap.put("cog", zzB(zzni.zzUF));
        hashMap.put("coh", zzB(zzni.zzUG));
        if (!TextUtils.isEmpty(zzni.zzUH)) {
            hashMap.put("carrier", zzni.zzUH);
        }
        hashMap.put("gl", zzni.zzUI);
        if (zzni.zzUJ) {
            hashMap.put("simulator", 1);
        }
        if (zzni.zzUK) {
            hashMap.put("is_sidewinder", 1);
        }
        hashMap.put("ma", zzB(zzni.zzUL));
        hashMap.put("sp", zzB(zzni.zzUM));
        hashMap.put("hl", zzni.zzUN);
        if (!TextUtils.isEmpty(zzni.zzUO)) {
            hashMap.put("mv", zzni.zzUO);
        }
        hashMap.put("muv", Integer.valueOf(zzni.zzUP));
        if (zzni.zzUQ != -2) {
            hashMap.put("cnt", Integer.valueOf(zzni.zzUQ));
        }
        hashMap.put("gnt", Integer.valueOf(zzni.zzUR));
        hashMap.put("pt", Integer.valueOf(zzni.zzUS));
        hashMap.put("rm", Integer.valueOf(zzni.zzUT));
        hashMap.put("riv", Integer.valueOf(zzni.zzUU));
        Bundle bundle3 = new Bundle();
        bundle3.putString("build", zzni.zzUZ);
        Bundle bundle4 = new Bundle();
        bundle4.putBoolean("is_charging", zzni.zzUW);
        bundle4.putDouble("battery_level", zzni.zzUV);
        bundle3.putBundle("battery", bundle4);
        Bundle bundle5 = new Bundle();
        bundle5.putInt("active_network_state", zzni.zzUY);
        bundle5.putBoolean("active_network_metered", zzni.zzUX);
        if (zza != null) {
            Bundle bundle6 = new Bundle();
            bundle6.putInt("predicted_latency_micros", zza.zzVf);
            bundle6.putLong("predicted_down_throughput_bps", zza.zzVg);
            bundle6.putLong("predicted_up_throughput_bps", zza.zzVh);
            bundle5.putBundle("predictions", bundle6);
        }
        bundle3.putBundle("network", bundle5);
        Bundle bundle7 = new Bundle();
        bundle7.putBoolean("is_browser_custom_tabs_capable", zzni.zzVa);
        bundle3.putBundle("browser", bundle7);
        if (bundle != null) {
            bundle3.putBundle("android_mem_info", zzg(bundle));
        }
        Bundle bundle8 = new Bundle();
        bundle8.putBundle("parental_controls", bundle2);
        bundle3.putBundle("play_store", bundle8);
        hashMap.put("device", bundle3);
    }

    private static String zzab(int i) {
        return String.format(Locale.US, "#%06x", new Object[]{Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)});
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x014f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject zzb(com.google.android.gms.internal.zzmn r9) throws org.json.JSONException {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = r9.zzNJ
            if (r1 == 0) goto L_0x0010
            java.lang.String r1 = "ad_base_url"
            java.lang.String r2 = r9.zzNJ
            r0.put(r1, r2)
        L_0x0010:
            java.lang.String r1 = r9.zzSq
            if (r1 == 0) goto L_0x001b
            java.lang.String r1 = "ad_size"
            java.lang.String r2 = r9.zzSq
            r0.put(r1, r2)
        L_0x001b:
            java.lang.String r1 = "native"
            boolean r2 = r9.zzzB
            r0.put(r1, r2)
            boolean r1 = r9.zzzB
            if (r1 == 0) goto L_0x002e
            java.lang.String r1 = "ad_json"
        L_0x0028:
            java.lang.String r2 = r9.body
            r0.put(r1, r2)
            goto L_0x0031
        L_0x002e:
            java.lang.String r1 = "ad_html"
            goto L_0x0028
        L_0x0031:
            java.lang.String r1 = r9.zzSs
            if (r1 == 0) goto L_0x003c
            java.lang.String r1 = "debug_dialog"
            java.lang.String r2 = r9.zzSs
            r0.put(r1, r2)
        L_0x003c:
            java.lang.String r1 = r9.zzSJ
            if (r1 == 0) goto L_0x0047
            java.lang.String r1 = "debug_signals"
            java.lang.String r2 = r9.zzSJ
            r0.put(r1, r2)
        L_0x0047:
            long r1 = r9.zzSm
            r3 = -1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x005d
            java.lang.String r1 = "interstitial_timeout"
            long r5 = r9.zzSm
            double r5 = (double) r5
            r7 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r5 = r5 / r7
            r0.put(r1, r5)
        L_0x005d:
            int r1 = r9.orientation
            com.google.android.gms.internal.zzpp r2 = com.google.android.gms.ads.internal.zzw.zzcO()
            int r2 = r2.zzkR()
            if (r1 != r2) goto L_0x0071
            java.lang.String r1 = "orientation"
            java.lang.String r2 = "portrait"
        L_0x006d:
            r0.put(r1, r2)
            goto L_0x0082
        L_0x0071:
            int r1 = r9.orientation
            com.google.android.gms.internal.zzpp r2 = com.google.android.gms.ads.internal.zzw.zzcO()
            int r2 = r2.zzkQ()
            if (r1 != r2) goto L_0x0082
            java.lang.String r1 = "orientation"
            java.lang.String r2 = "landscape"
            goto L_0x006d
        L_0x0082:
            java.util.List<java.lang.String> r1 = r9.zzKF
            if (r1 == 0) goto L_0x0091
            java.lang.String r1 = "click_urls"
            java.util.List<java.lang.String> r2 = r9.zzKF
            org.json.JSONArray r2 = zzl(r2)
            r0.put(r1, r2)
        L_0x0091:
            java.util.List<java.lang.String> r1 = r9.zzKG
            if (r1 == 0) goto L_0x00a0
            java.lang.String r1 = "impression_urls"
            java.util.List<java.lang.String> r2 = r9.zzKG
            org.json.JSONArray r2 = zzl(r2)
            r0.put(r1, r2)
        L_0x00a0:
            java.util.List<java.lang.String> r1 = r9.zzSp
            if (r1 == 0) goto L_0x00af
            java.lang.String r1 = "manual_impression_urls"
            java.util.List<java.lang.String> r2 = r9.zzSp
            org.json.JSONArray r2 = zzl(r2)
            r0.put(r1, r2)
        L_0x00af:
            java.lang.String r1 = r9.zzSv
            if (r1 == 0) goto L_0x00ba
            java.lang.String r1 = "active_view"
            java.lang.String r2 = r9.zzSv
            r0.put(r1, r2)
        L_0x00ba:
            java.lang.String r1 = "ad_is_javascript"
            boolean r2 = r9.zzSt
            r0.put(r1, r2)
            java.lang.String r1 = r9.zzSu
            if (r1 == 0) goto L_0x00cc
            java.lang.String r1 = "ad_passback_url"
            java.lang.String r2 = r9.zzSu
            r0.put(r1, r2)
        L_0x00cc:
            java.lang.String r1 = "mediation"
            boolean r2 = r9.zzSn
            r0.put(r1, r2)
            java.lang.String r1 = "custom_render_allowed"
            boolean r2 = r9.zzSw
            r0.put(r1, r2)
            java.lang.String r1 = "content_url_opted_out"
            boolean r2 = r9.zzSx
            r0.put(r1, r2)
            java.lang.String r1 = "content_vertical_opted_out"
            boolean r2 = r9.zzSK
            r0.put(r1, r2)
            java.lang.String r1 = "prefetch"
            boolean r2 = r9.zzSy
            r0.put(r1, r2)
            long r1 = r9.zzKL
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00fc
            java.lang.String r1 = "refresh_interval_milliseconds"
            long r5 = r9.zzKL
            r0.put(r1, r5)
        L_0x00fc:
            long r1 = r9.zzSo
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0109
            java.lang.String r1 = "mediation_config_cache_time_milliseconds"
            long r2 = r9.zzSo
            r0.put(r1, r2)
        L_0x0109:
            java.lang.String r1 = r9.zzSB
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0118
            java.lang.String r1 = "gws_query_id"
            java.lang.String r2 = r9.zzSB
            r0.put(r1, r2)
        L_0x0118:
            java.lang.String r1 = "fluid"
            boolean r2 = r9.zzzC
            if (r2 == 0) goto L_0x0121
            java.lang.String r2 = "height"
            goto L_0x0123
        L_0x0121:
            java.lang.String r2 = ""
        L_0x0123:
            r0.put(r1, r2)
            java.lang.String r1 = "native_express"
            boolean r2 = r9.zzzD
            r0.put(r1, r2)
            java.util.List<java.lang.String> r1 = r9.zzSD
            if (r1 == 0) goto L_0x013c
            java.lang.String r1 = "video_start_urls"
            java.util.List<java.lang.String> r2 = r9.zzSD
            org.json.JSONArray r2 = zzl(r2)
            r0.put(r1, r2)
        L_0x013c:
            java.util.List<java.lang.String> r1 = r9.zzSE
            if (r1 == 0) goto L_0x014b
            java.lang.String r1 = "video_complete_urls"
            java.util.List<java.lang.String> r2 = r9.zzSE
            org.json.JSONArray r2 = zzl(r2)
            r0.put(r1, r2)
        L_0x014b:
            com.google.android.gms.internal.zzoo r1 = r9.zzSC
            if (r1 == 0) goto L_0x015a
            java.lang.String r1 = "rewards"
            com.google.android.gms.internal.zzoo r2 = r9.zzSC
            org.json.JSONArray r2 = r2.zzjP()
            r0.put(r1, r2)
        L_0x015a:
            java.lang.String r1 = "use_displayed_impression"
            boolean r2 = r9.zzSF
            r0.put(r1, r2)
            java.lang.String r1 = "auto_protection_configuration"
            com.google.android.gms.internal.zzmp r2 = r9.zzSG
            r0.put(r1, r2)
            java.lang.String r1 = "render_in_browser"
            boolean r9 = r9.zzKJ
            r0.put(r1, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zznd.zzb(com.google.android.gms.internal.zzmn):org.json.JSONObject");
    }

    private static String zzc(zzhc zzhc) {
        switch (zzhc != null ? zzhc.zzHb : 0) {
            case 1:
                return "portrait";
            case 2:
                return "landscape";
            default:
                return "any";
        }
    }

    private static Bundle zzg(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("runtime_free", Long.toString(bundle.getLong("runtime_free_memory", -1)));
        bundle2.putString("runtime_max", Long.toString(bundle.getLong("runtime_max_memory", -1)));
        bundle2.putString("runtime_total", Long.toString(bundle.getLong("runtime_total_memory", -1)));
        bundle2.putString("web_view_count", Integer.toString(bundle.getInt("web_view_count", 0)));
        Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo) bundle.getParcelable("debug_memory_info");
        if (memoryInfo != null) {
            bundle2.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
            bundle2.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
            bundle2.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
            bundle2.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
            bundle2.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
            bundle2.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
            bundle2.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
            bundle2.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
            bundle2.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
        }
        return bundle2;
    }

    @Nullable
    static JSONArray zzl(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }
}
