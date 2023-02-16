package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.l.a;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class n extends ab {
    private static final String c = "n";
    private static final int[] d = {-1, -6, -7, -8};
    private final String e = UUID.randomUUID().toString();
    private Context f;
    /* access modifiers changed from: private */
    public ac g;
    /* access modifiers changed from: private */
    public boolean h = false;
    private String i;
    private String j;
    private long k;
    /* access modifiers changed from: private */
    public k l;
    private ad m;

    private void a(Context context, final boolean z) {
        if (a.f(context)) {
            Log.d(c, "Playable Ads pre-caching is disabled.");
            this.h = true;
            this.g.a(this);
            return;
        }
        WebView webView = new WebView(context);
        webView.getSettings().setCacheMode(1);
        webView.setWebViewClient(new WebViewClient() {
            boolean a = false;

            private void a() {
                boolean unused = n.this.h = true;
                n.this.g.a(n.this);
            }

            /* access modifiers changed from: private */
            public void a(WebResourceError webResourceError) {
                if (z || !n.this.a(webResourceError)) {
                    n.this.g.a(n.this, AdError.CACHE_ERROR);
                } else {
                    a();
                }
            }

            public void onPageFinished(WebView webView, String str) {
                this.a = true;
                a();
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (!AnonymousClass1.this.a) {
                            AnonymousClass1.this.a((WebResourceError) null);
                        }
                    }
                }, 10000);
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                this.a = true;
                a(webResourceError);
            }
        });
        webView.loadUrl(this.l.e().j().a());
    }

    /* access modifiers changed from: private */
    public boolean a(WebResourceError webResourceError) {
        if (webResourceError == null || Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (int i2 : d) {
            if (webResourceError.getErrorCode() == i2) {
                return true;
            }
        }
        return false;
    }

    private void b(Context context, final boolean z) {
        final b bVar = new b(context);
        bVar.a(this.l.e().a());
        bVar.a(this.l.e().g(), this.l.e().i(), this.l.e().h());
        bVar.a(this.l.a().b(), -1, -1);
        for (String a : this.l.f().d()) {
            bVar.a(a, -1, -1);
        }
        bVar.a((com.facebook.ads.internal.d.a) new com.facebook.ads.internal.d.a() {
            private void c() {
                boolean unused = n.this.h = true;
                n.this.g.a(n.this);
                n.this.l.b(bVar.b(n.this.l.e().a()));
            }

            public void a() {
                c();
            }

            public void b() {
                if (z) {
                    n.this.g.a(n.this, AdError.CACHE_ERROR);
                } else {
                    c();
                }
            }
        });
    }

    private boolean c() {
        return this.l.e().j() != null;
    }

    private void d() {
        LocalBroadcastManager.getInstance(this.f).registerReceiver(this.m, this.m.a());
    }

    private void e() {
        if (this.m != null) {
            try {
                LocalBroadcastManager.getInstance(this.f).unregisterReceiver(this.m);
            } catch (Exception unused) {
            }
        }
    }

    private String f() {
        String str;
        if (this.a == null) {
            return null;
        }
        String urlPrefix = AdSettings.getUrlPrefix();
        if (urlPrefix == null || urlPrefix.isEmpty()) {
            str = "https://www.facebook.com/audience_network/server_side_reward";
        } else {
            str = String.format("https://www.%s.facebook.com/audience_network/server_side_reward", new Object[]{urlPrefix});
        }
        Uri parse = Uri.parse(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(parse.getScheme());
        builder.authority(parse.getAuthority());
        builder.path(parse.getPath());
        builder.query(parse.getQuery());
        builder.fragment(parse.getFragment());
        builder.appendQueryParameter("puid", this.a.getUserID());
        builder.appendQueryParameter("pc", this.a.getCurrency());
        builder.appendQueryParameter("ptid", this.e);
        builder.appendQueryParameter("appid", this.i);
        return builder.build().toString();
    }

    public int a() {
        if (this.l == null) {
            return -1;
        }
        return this.l.e().d();
    }

    public void a(Context context, ac acVar, Map<String, Object> map, boolean z) {
        this.f = context;
        this.g = acVar;
        this.h = false;
        this.j = (String) map.get(AudienceNetworkActivity.PLACEMENT_ID);
        this.k = ((Long) map.get(AudienceNetworkActivity.REQUEST_TIME)).longValue();
        int k2 = ((d) map.get("definition")).k();
        this.i = this.j != null ? this.j.split(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)[0] : "";
        this.l = k.a((JSONObject) map.get("data"));
        this.l.a(k2);
        if (!TextUtils.isEmpty(this.l.e().a()) || c()) {
            this.m = new ad(this.e, this, acVar);
            d();
            if (c()) {
                a(context, z);
            } else {
                b(context, z);
            }
        } else {
            this.g.a(this, AdError.INTERNAL_ERROR);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b() {
        /*
            r5 = this;
            boolean r0 = r5.h
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r0 = r5.f()
            com.facebook.ads.internal.adapters.a.k r2 = r5.l
            r2.a((java.lang.String) r0)
            android.content.Intent r2 = new android.content.Intent
            android.content.Context r3 = r5.f
            java.lang.Class<com.facebook.ads.AudienceNetworkActivity> r4 = com.facebook.ads.AudienceNetworkActivity.class
            r2.<init>(r3, r4)
            java.lang.String r3 = "viewType"
            com.facebook.ads.internal.settings.a$a r4 = com.facebook.ads.internal.settings.a.C0007a.REWARDED_VIDEO
            r2.putExtra(r3, r4)
            java.lang.String r3 = "rewardedVideoAdDataBundle"
            com.facebook.ads.internal.adapters.a.k r4 = r5.l
            r2.putExtra(r3, r4)
            java.lang.String r3 = "uniqueId"
            java.lang.String r4 = r5.e
            r2.putExtra(r3, r4)
            java.lang.String r3 = "rewardServerURL"
            r2.putExtra(r3, r0)
            java.lang.String r0 = "placementId"
            java.lang.String r3 = r5.j
            r2.putExtra(r0, r3)
            java.lang.String r0 = "requestTime"
            long r3 = r5.k
            r2.putExtra(r0, r3)
            int r0 = r5.b
            r3 = -1
            r4 = 1
            if (r0 == r3) goto L_0x005c
            android.content.Context r0 = r5.f
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r3 = "accelerometer_rotation"
            int r0 = android.provider.Settings.System.getInt(r0, r3, r1)
            if (r0 == r4) goto L_0x005c
            java.lang.String r0 = "predefinedOrientationKey"
            int r1 = r5.b
        L_0x0058:
            r2.putExtra(r0, r1)
            goto L_0x0068
        L_0x005c:
            android.content.Context r0 = r5.f
            boolean r0 = com.facebook.ads.internal.l.a.o(r0)
            if (r0 != 0) goto L_0x0068
            java.lang.String r0 = "predefinedOrientationKey"
            r1 = 6
            goto L_0x0058
        L_0x0068:
            android.content.Context r0 = r5.f
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 != 0) goto L_0x0078
            int r0 = r2.getFlags()
            r1 = 268435456(0x10000000, float:2.5243549E-29)
            r0 = r0 | r1
            r2.setFlags(r0)
        L_0x0078:
            android.content.Context r0 = r5.f
            r0.startActivity(r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.n.b():boolean");
    }

    public void onDestroy() {
        e();
    }
}
