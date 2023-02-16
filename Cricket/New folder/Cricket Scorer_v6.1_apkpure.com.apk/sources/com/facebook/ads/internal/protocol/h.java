package com.facebook.ads.internal.protocol;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.q.d.b;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import org.json.JSONException;
import org.json.JSONObject;

public final class h {
    private final a a;
    @Nullable
    private final Long b;
    @Nullable
    private final String c;
    @Nullable
    private final String d;

    private enum a {
        ID,
        CREATIVE,
        NONE
    }

    public h(Context context, String str, String str2, f fVar) {
        if (TextUtils.isEmpty(str)) {
            this.a = a.NONE;
            this.b = null;
            this.d = null;
            this.c = null;
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (a.valueOf(jSONObject.getString(DublinCoreProperties.TYPE).toUpperCase())) {
                case ID:
                    this.a = a.ID;
                    this.b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.d = jSONObject.getString("device_id");
                    this.c = null;
                    break;
                case CREATIVE:
                    this.a = a.CREATIVE;
                    this.b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.d = jSONObject.getString("device_id");
                    this.c = new JSONObject(jSONObject.getString("payload")).toString();
                    break;
                default:
                    AdErrorType adErrorType = AdErrorType.BID_PAYLOAD_ERROR;
                    throw new b(adErrorType, "Unsupported BidPayload type " + jSONObject.getString(DublinCoreProperties.TYPE));
            }
            if (!jSONObject.getString("sdk_version").equals("4.99.1")) {
                throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format("Bid %d for SDK version %s being used on SDK version %s", new Object[]{this.b, jSONObject.getString("sdk_version"), "4.99.1"}));
            } else if (!jSONObject.getString("resolved_placement_id").equals(str2)) {
                throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format("Bid %d for placement %s being used on placement %s", new Object[]{this.b, jSONObject.getString("resolved_placement_id"), str2}));
            } else if (jSONObject.getInt("template") != fVar.a()) {
                throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format("Bid %d for template %s being used on template %s", new Object[]{this.b, Integer.valueOf(jSONObject.getInt("template")), fVar}));
            }
        } catch (JSONException e) {
            com.facebook.ads.internal.q.d.a.a(context, "api", b.d, (Exception) e);
            throw new b(AdErrorType.BID_PAYLOAD_ERROR, "Invalid BidPayload", e);
        }
    }

    public void a(String str) {
        if (!this.d.equals(str)) {
            throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format("Bid %d for IDFA %s being used on IDFA %s", new Object[]{this.b, this.d, str}));
        }
    }

    public boolean a() {
        return this.a == a.CREATIVE;
    }

    @Nullable
    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.a != a.NONE;
    }

    @Nullable
    public String d() {
        if (this.b == null) {
            return null;
        }
        return this.b.toString();
    }
}
