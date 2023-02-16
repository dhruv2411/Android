package com.facebook.ads.internal.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.c.g;
import com.itextpdf.xmp.XMPConst;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends h {
    private static final String e = "f";
    private final Uri f;
    private final Map<String, String> g;

    public f(Context context, c cVar, String str, Uri uri, Map<String, String> map, l lVar) {
        super(context, cVar, str, lVar);
        this.f = uri;
        this.g = map;
    }

    private Intent a(g gVar) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        if (!TextUtils.isEmpty(gVar.a()) && !TextUtils.isEmpty(gVar.b())) {
            intent.setComponent(new ComponentName(gVar.a(), gVar.b()));
        }
        if (!TextUtils.isEmpty(gVar.c())) {
            intent.setData(Uri.parse(gVar.c()));
        }
        return intent;
    }

    private Intent b(g gVar) {
        if (TextUtils.isEmpty(gVar.a()) || !e.a(this.a, gVar.a())) {
            return null;
        }
        String c = gVar.c();
        if (!TextUtils.isEmpty(c) && (c.startsWith("tel:") || c.startsWith("telprompt:"))) {
            return new Intent("android.intent.action.CALL", Uri.parse(c));
        }
        PackageManager packageManager = this.a.getPackageManager();
        if (TextUtils.isEmpty(gVar.b()) && TextUtils.isEmpty(c)) {
            return packageManager.getLaunchIntentForPackage(gVar.a());
        }
        Intent a = a(gVar);
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(a, 65536);
        if (a.getComponent() == null) {
            Iterator<ResolveInfo> it = queryIntentActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo next = it.next();
                if (next.activityInfo.packageName.equals(gVar.a())) {
                    a.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                    break;
                }
            }
        }
        if (queryIntentActivities.isEmpty() || a.getComponent() == null) {
            return null;
        }
        return a;
    }

    private List<g> f() {
        String queryParameter = this.f.getQueryParameter("appsite_data");
        if (TextUtils.isEmpty(queryParameter) || XMPConst.ARRAY_ITEM_NAME.equals(queryParameter)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(queryParameter).optJSONArray(AbstractSpiCall.ANDROID_CLIENT_TYPE);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    g a = g.a(optJSONArray.optJSONObject(i));
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            }
        } catch (JSONException e2) {
            Log.w(e, "Error parsing appsite_data", e2);
        }
        return arrayList;
    }

    private boolean g() {
        List<Intent> d = d();
        if (d == null) {
            return false;
        }
        for (Intent startActivity : d) {
            try {
                this.a.startActivity(startActivity);
                return true;
            } catch (Exception e2) {
                Log.d(e, "Failed to open app intent, falling back", e2);
            }
        }
        return false;
    }

    private boolean h() {
        g gVar = new g();
        try {
            g.a(gVar, this.a, c(), this.c);
            return true;
        } catch (Exception e2) {
            String str = e;
            Log.d(str, "Failed to open market url: " + this.f.toString(), e2);
            String queryParameter = this.f.getQueryParameter("store_url_web_fallback");
            if (queryParameter == null || queryParameter.length() <= 0) {
                return false;
            }
            g.a(gVar, this.a, Uri.parse(queryParameter), this.c);
            return false;
        }
    }

    public a.C0004a a() {
        return a.C0004a.OPEN_STORE;
    }

    /* access modifiers changed from: protected */
    public Uri c() {
        String queryParameter = this.f.getQueryParameter("store_url");
        if (!TextUtils.isEmpty(queryParameter)) {
            return Uri.parse(queryParameter);
        }
        return Uri.parse(String.format("market://details?id=%s", new Object[]{this.f.getQueryParameter("store_id")}));
    }

    /* access modifiers changed from: protected */
    public List<Intent> d() {
        List<g> f2 = f();
        ArrayList arrayList = new ArrayList();
        if (f2 != null) {
            for (g b : f2) {
                Intent b2 = b(b);
                if (b2 != null) {
                    arrayList.add(b2);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        String str = "opened_deeplink";
        a aVar = null;
        if (!g()) {
            try {
                str = h() ? "opened_store_url" : "opened_store_fallback_url";
            } catch (Exception unused) {
                Log.d(e, "Failed to open all options including fallback url, can't open anything");
                aVar = a.CANNOT_OPEN;
            }
        }
        this.g.put(str, String.valueOf(true));
        a(this.g, aVar);
    }
}
