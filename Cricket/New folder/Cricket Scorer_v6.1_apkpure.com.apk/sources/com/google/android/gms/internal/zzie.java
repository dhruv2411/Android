package com.google.android.gms.internal;

import android.content.Context;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzie implements zzid {
    private final Context mContext;
    private final zzqh zztt;

    @zzme
    static class zza {
        private final String mValue;
        private final String zzAX;

        public zza(String str, String str2) {
            this.zzAX = str;
            this.mValue = str2;
        }

        public String getKey() {
            return this.zzAX;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @zzme
    static class zzb {
        private final String zzId;
        private final URL zzIe;
        private final ArrayList<zza> zzIf;
        private final String zzIg;

        public zzb(String str, URL url, ArrayList<zza> arrayList, String str2) {
            this.zzId = str;
            this.zzIe = url;
            if (arrayList == null) {
                this.zzIf = new ArrayList<>();
            } else {
                this.zzIf = arrayList;
            }
            this.zzIg = str2;
        }

        public String zzgl() {
            return this.zzId;
        }

        public URL zzgm() {
            return this.zzIe;
        }

        public ArrayList<zza> zzgn() {
            return this.zzIf;
        }

        public String zzgo() {
            return this.zzIg;
        }
    }

    @zzme
    class zzc {
        private final zzd zzIh;
        private final boolean zzIi;
        private final String zzIj;

        public zzc(zzie zzie, boolean z, zzd zzd, String str) {
            this.zzIi = z;
            this.zzIh = zzd;
            this.zzIj = str;
        }

        public String getReason() {
            return this.zzIj;
        }

        public boolean isSuccess() {
            return this.zzIi;
        }

        public zzd zzgp() {
            return this.zzIh;
        }
    }

    @zzme
    static class zzd {
        private final String zzGr;
        private final String zzId;
        private final int zzIk;
        private final List<zza> zzIl;

        public zzd(String str, int i, List<zza> list, String str2) {
            this.zzId = str;
            this.zzIk = i;
            if (list == null) {
                this.zzIl = new ArrayList();
            } else {
                this.zzIl = list;
            }
            this.zzGr = str2;
        }

        public String getBody() {
            return this.zzGr;
        }

        public int getResponseCode() {
            return this.zzIk;
        }

        public String zzgl() {
            return this.zzId;
        }

        public Iterable<zza> zzgq() {
            return this.zzIl;
        }
    }

    public zzie(Context context, zzqh zzqh) {
        this.mContext = context;
        this.zztt = zzqh;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ec  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzie.zzc zza(com.google.android.gms.internal.zzie.zzb r12) {
        /*
            r11 = this;
            r0 = 0
            r1 = 0
            java.net.URL r2 = r12.zzgm()     // Catch:{ Exception -> 0x00d8, all -> 0x00d5 }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x00d8, all -> 0x00d5 }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x00d8, all -> 0x00d5 }
            com.google.android.gms.internal.zzpo r3 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ Exception -> 0x00d3 }
            android.content.Context r4 = r11.mContext     // Catch:{ Exception -> 0x00d3 }
            com.google.android.gms.internal.zzqh r5 = r11.zztt     // Catch:{ Exception -> 0x00d3 }
            java.lang.String r5 = r5.zzba     // Catch:{ Exception -> 0x00d3 }
            r3.zza((android.content.Context) r4, (java.lang.String) r5, (boolean) r0, (java.net.HttpURLConnection) r2)     // Catch:{ Exception -> 0x00d3 }
            java.util.ArrayList r3 = r12.zzgn()     // Catch:{ Exception -> 0x00d3 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x00d3 }
        L_0x0021:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x00d3 }
            if (r4 == 0) goto L_0x0039
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x00d3 }
            com.google.android.gms.internal.zzie$zza r4 = (com.google.android.gms.internal.zzie.zza) r4     // Catch:{ Exception -> 0x00d3 }
            java.lang.String r5 = r4.getKey()     // Catch:{ Exception -> 0x00d3 }
            java.lang.String r4 = r4.getValue()     // Catch:{ Exception -> 0x00d3 }
            r2.addRequestProperty(r5, r4)     // Catch:{ Exception -> 0x00d3 }
            goto L_0x0021
        L_0x0039:
            java.lang.String r3 = r12.zzgo()     // Catch:{ Exception -> 0x00d3 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x00d3 }
            r4 = 1
            if (r3 != 0) goto L_0x0062
            r2.setDoOutput(r4)     // Catch:{ Exception -> 0x00d3 }
            java.lang.String r3 = r12.zzgo()     // Catch:{ Exception -> 0x00d3 }
            byte[] r3 = r3.getBytes()     // Catch:{ Exception -> 0x00d3 }
            int r5 = r3.length     // Catch:{ Exception -> 0x00d3 }
            r2.setFixedLengthStreamingMode(r5)     // Catch:{ Exception -> 0x00d3 }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x00d3 }
            java.io.OutputStream r6 = r2.getOutputStream()     // Catch:{ Exception -> 0x00d3 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00d3 }
            r5.write(r3)     // Catch:{ Exception -> 0x00d3 }
            r5.close()     // Catch:{ Exception -> 0x00d3 }
        L_0x0062:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x00d3 }
            r3.<init>()     // Catch:{ Exception -> 0x00d3 }
            java.util.Map r5 = r2.getHeaderFields()     // Catch:{ Exception -> 0x00d3 }
            if (r5 == 0) goto L_0x00aa
            java.util.Map r5 = r2.getHeaderFields()     // Catch:{ Exception -> 0x00d3 }
            java.util.Set r5 = r5.entrySet()     // Catch:{ Exception -> 0x00d3 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ Exception -> 0x00d3 }
        L_0x0079:
            boolean r6 = r5.hasNext()     // Catch:{ Exception -> 0x00d3 }
            if (r6 == 0) goto L_0x00aa
            java.lang.Object r6 = r5.next()     // Catch:{ Exception -> 0x00d3 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ Exception -> 0x00d3 }
            java.lang.Object r7 = r6.getValue()     // Catch:{ Exception -> 0x00d3 }
            java.util.List r7 = (java.util.List) r7     // Catch:{ Exception -> 0x00d3 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x00d3 }
        L_0x008f:
            boolean r8 = r7.hasNext()     // Catch:{ Exception -> 0x00d3 }
            if (r8 == 0) goto L_0x0079
            java.lang.Object r8 = r7.next()     // Catch:{ Exception -> 0x00d3 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x00d3 }
            com.google.android.gms.internal.zzie$zza r9 = new com.google.android.gms.internal.zzie$zza     // Catch:{ Exception -> 0x00d3 }
            java.lang.Object r10 = r6.getKey()     // Catch:{ Exception -> 0x00d3 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x00d3 }
            r9.<init>(r10, r8)     // Catch:{ Exception -> 0x00d3 }
            r3.add(r9)     // Catch:{ Exception -> 0x00d3 }
            goto L_0x008f
        L_0x00aa:
            com.google.android.gms.internal.zzie$zzd r5 = new com.google.android.gms.internal.zzie$zzd     // Catch:{ Exception -> 0x00d3 }
            java.lang.String r12 = r12.zzgl()     // Catch:{ Exception -> 0x00d3 }
            int r6 = r2.getResponseCode()     // Catch:{ Exception -> 0x00d3 }
            com.google.android.gms.internal.zzpo r7 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ Exception -> 0x00d3 }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00d3 }
            java.io.InputStream r9 = r2.getInputStream()     // Catch:{ Exception -> 0x00d3 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x00d3 }
            java.lang.String r7 = r7.zza((java.io.InputStreamReader) r8)     // Catch:{ Exception -> 0x00d3 }
            r5.<init>(r12, r6, r3, r7)     // Catch:{ Exception -> 0x00d3 }
            com.google.android.gms.internal.zzie$zzc r12 = new com.google.android.gms.internal.zzie$zzc     // Catch:{ Exception -> 0x00d3 }
            r12.<init>(r11, r4, r5, r1)     // Catch:{ Exception -> 0x00d3 }
            if (r2 == 0) goto L_0x00d2
            r2.disconnect()
        L_0x00d2:
            return r12
        L_0x00d3:
            r12 = move-exception
            goto L_0x00da
        L_0x00d5:
            r12 = move-exception
            r2 = r1
            goto L_0x00ea
        L_0x00d8:
            r12 = move-exception
            r2 = r1
        L_0x00da:
            com.google.android.gms.internal.zzie$zzc r3 = new com.google.android.gms.internal.zzie$zzc     // Catch:{ all -> 0x00e9 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x00e9 }
            r3.<init>(r11, r0, r1, r12)     // Catch:{ all -> 0x00e9 }
            if (r2 == 0) goto L_0x00e8
            r2.disconnect()
        L_0x00e8:
            return r3
        L_0x00e9:
            r12 = move-exception
        L_0x00ea:
            if (r2 == 0) goto L_0x00ef
            r2.disconnect()
        L_0x00ef:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzie.zza(com.google.android.gms.internal.zzie$zzb):com.google.android.gms.internal.zzie$zzc");
    }

    /* access modifiers changed from: protected */
    public JSONObject zza(zzd zzd2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", zzd2.zzgl());
            if (zzd2.getBody() != null) {
                jSONObject.put("body", zzd2.getBody());
            }
            JSONArray jSONArray = new JSONArray();
            for (zza next : zzd2.zzgq()) {
                jSONArray.put(new JSONObject().put("key", next.getKey()).put(FirebaseAnalytics.Param.VALUE, next.getValue()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", zzd2.getResponseCode());
            return jSONObject;
        } catch (JSONException e) {
            zzpk.zzb("Error constructing JSON for http response.", e);
            return jSONObject;
        }
    }

    public void zza(final zzqw zzqw, final Map<String, String> map) {
        zzpn.zza((Runnable) new Runnable() {
            public void run() {
                zzpk.zzbf("Received Http request.");
                final JSONObject zzaa = zzie.this.zzaa((String) map.get("http_request"));
                if (zzaa == null) {
                    zzpk.e("Response should not be null.");
                } else {
                    zzpo.zzXC.post(new Runnable() {
                        public void run() {
                            zzqw.zzb("fetchHttpRequestCompleted", zzaa);
                            zzpk.zzbf("Dispatched http response.");
                        }
                    });
                }
            }
        });
    }

    public JSONObject zzaa(String str) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            try {
                str2 = jSONObject.optString("http_request_id");
                try {
                    zzc zza2 = zza(zzb(jSONObject));
                    if (zza2.isSuccess()) {
                        jSONObject2.put("response", zza(zza2.zzgp()));
                        jSONObject2.put("success", true);
                        return jSONObject2;
                    }
                    jSONObject2.put("response", new JSONObject().put("http_request_id", str2));
                    jSONObject2.put("success", false);
                    jSONObject2.put("reason", zza2.getReason());
                    return jSONObject2;
                } catch (Exception e) {
                    e = e;
                    try {
                        jSONObject2.put("response", new JSONObject().put("http_request_id", str2));
                        jSONObject2.put("success", false);
                        jSONObject2.put("reason", e.toString());
                    } catch (JSONException unused) {
                    }
                    return jSONObject2;
                }
            } catch (Exception e2) {
                e = e2;
                str2 = "";
                jSONObject2.put("response", new JSONObject().put("http_request_id", str2));
                jSONObject2.put("success", false);
                jSONObject2.put("reason", e.toString());
                return jSONObject2;
            }
        } catch (JSONException unused2) {
            zzpk.e("The request is not a valid JSON.");
            try {
                return new JSONObject().put("success", false);
            } catch (JSONException unused3) {
                return new JSONObject();
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzb zzb(JSONObject jSONObject) {
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString("url");
        URL url = null;
        String optString3 = jSONObject.optString("post_body", (String) null);
        try {
            url = new URL(optString2);
        } catch (MalformedURLException e) {
            zzpk.zzb("Error constructing http request.", e);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new zza(optJSONObject.optString("key"), optJSONObject.optString(FirebaseAnalytics.Param.VALUE)));
            }
        }
        return new zzb(optString, url, arrayList, optString3);
    }
}
