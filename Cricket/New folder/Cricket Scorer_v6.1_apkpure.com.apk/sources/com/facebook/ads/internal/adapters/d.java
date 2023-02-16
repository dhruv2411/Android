package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.internal.n.c;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.h;
import com.facebook.ads.internal.n.j;
import com.facebook.ads.internal.n.k;
import com.facebook.ads.internal.n.m;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.a;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends y implements u {
    private static final String a = "d";
    private View b;
    /* access modifiers changed from: private */
    public NativeAd c;
    /* access modifiers changed from: private */
    public z d;
    private NativeAdView e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public boolean g;
    /* access modifiers changed from: private */
    public Uri h;
    /* access modifiers changed from: private */
    public Uri i;
    /* access modifiers changed from: private */
    public String j;
    /* access modifiers changed from: private */
    public String k;
    /* access modifiers changed from: private */
    public String l;
    /* access modifiers changed from: private */
    public String m;

    private void a(View view) {
        ViewGroup viewGroup;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeView(view);
        }
    }

    public String A() {
        return null;
    }

    public String B() {
        return null;
    }

    public m C() {
        return m.DEFAULT;
    }

    public int D() {
        return 0;
    }

    public String E() {
        return null;
    }

    public List<f> F() {
        return null;
    }

    public int G() {
        return 0;
    }

    public int H() {
        return 0;
    }

    public c I() {
        return c.ADMOB;
    }

    public f J() {
        return f.ADMOB;
    }

    public void a(int i2) {
    }

    public void a(final Context context, z zVar, com.facebook.ads.internal.m.c cVar, Map<String, Object> map, f.c cVar2) {
        boolean z;
        com.facebook.ads.internal.q.a.d.a(context, v.a(J()) + " Loading");
        JSONObject jSONObject = (JSONObject) map.get("data");
        String optString = jSONObject.optString("ad_unit_id");
        JSONArray optJSONArray = jSONObject.optJSONArray("creative_types");
        boolean z2 = false;
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            int i2 = 0;
            boolean z3 = false;
            z = false;
            while (i2 < length) {
                try {
                    String string = optJSONArray.getString(i2);
                    if (string != null) {
                        char c2 = 65535;
                        int hashCode = string.hashCode();
                        if (hashCode != 704091517) {
                            if (hashCode == 883765328) {
                                if (string.equals("page_post")) {
                                    c2 = 1;
                                }
                            }
                        } else if (string.equals("app_install")) {
                            c2 = 0;
                        }
                        switch (c2) {
                            case 0:
                                z3 = true;
                                break;
                            case 1:
                                z = true;
                                break;
                        }
                    }
                    i2++;
                } catch (JSONException unused) {
                    com.facebook.ads.internal.q.a.d.a(context, v.a(J()) + " AN server error");
                    zVar.a(this, a.a(AdErrorType.SERVER_ERROR, "Server Error"));
                    return;
                }
            }
            z2 = z3;
        } else {
            z = false;
        }
        if (TextUtils.isEmpty(optString) || (!z2 && !z)) {
            com.facebook.ads.internal.q.a.d.a(context, v.a(J()) + " AN server error");
            zVar.a(this, a.a(AdErrorType.SERVER_ERROR, "Server Error"));
            return;
        }
        this.d = zVar;
        AdLoader.Builder builder = new AdLoader.Builder(context, optString);
        if (z2) {
            builder.forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
                public void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
                    NativeAd unused = d.this.c = nativeAppInstallAd;
                    boolean unused2 = d.this.g = true;
                    Uri uri = null;
                    String unused3 = d.this.j = nativeAppInstallAd.getHeadline() != null ? nativeAppInstallAd.getHeadline().toString() : null;
                    String unused4 = d.this.k = nativeAppInstallAd.getBody() != null ? nativeAppInstallAd.getBody().toString() : null;
                    String unused5 = d.this.m = nativeAppInstallAd.getStore() != null ? nativeAppInstallAd.getStore().toString() : null;
                    String unused6 = d.this.l = nativeAppInstallAd.getCallToAction() != null ? nativeAppInstallAd.getCallToAction().toString() : null;
                    List<NativeAd.Image> images = nativeAppInstallAd.getImages();
                    Uri unused7 = d.this.h = (images == null || images.size() <= 0) ? null : images.get(0).getUri();
                    d dVar = d.this;
                    if (nativeAppInstallAd.getIcon() != null) {
                        uri = nativeAppInstallAd.getIcon().getUri();
                    }
                    Uri unused8 = dVar.i = uri;
                    if (d.this.d != null) {
                        Context context = context;
                        com.facebook.ads.internal.q.a.d.a(context, v.a(d.this.J()) + " Loaded");
                        d.this.d.a(d.this);
                    }
                }
            });
        }
        if (z) {
            builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
                public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                    NativeAd unused = d.this.c = nativeContentAd;
                    boolean unused2 = d.this.g = true;
                    Uri uri = null;
                    String unused3 = d.this.j = nativeContentAd.getHeadline() != null ? nativeContentAd.getHeadline().toString() : null;
                    String unused4 = d.this.k = nativeContentAd.getBody() != null ? nativeContentAd.getBody().toString() : null;
                    String unused5 = d.this.m = nativeContentAd.getAdvertiser() != null ? nativeContentAd.getAdvertiser().toString() : null;
                    String unused6 = d.this.l = nativeContentAd.getCallToAction() != null ? nativeContentAd.getCallToAction().toString() : null;
                    List<NativeAd.Image> images = nativeContentAd.getImages();
                    Uri unused7 = d.this.h = (images == null || images.size() <= 0) ? null : images.get(0).getUri();
                    d dVar = d.this;
                    if (nativeContentAd.getLogo() != null) {
                        uri = nativeContentAd.getLogo().getUri();
                    }
                    Uri unused8 = dVar.i = uri;
                    if (d.this.d != null) {
                        Context context = context;
                        com.facebook.ads.internal.q.a.d.a(context, v.a(d.this.J()) + " Loaded");
                        d.this.d.a(d.this);
                    }
                }
            });
        }
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                Context context = context;
                com.facebook.ads.internal.q.a.d.a(context, v.a(d.this.J()) + " Failed with error code: " + i);
                if (d.this.d != null) {
                    z a2 = d.this.d;
                    d dVar = d.this;
                    int errorCode = AdErrorType.MEDIATION_ERROR.getErrorCode();
                    a2.a(dVar, new a(errorCode, "AdMob error code: " + i));
                }
            }

            public void onAdOpened() {
                if (d.this.d != null) {
                    d.this.d.c(d.this);
                }
            }
        }).withNativeAdOptions(new NativeAdOptions.Builder().setReturnUrlsForImageAssets(true).build()).build().loadAd(new AdRequest.Builder().build());
    }

    public void a(View view, List<View> list) {
        this.b = view;
        if (c_() && view != null) {
            ViewGroup viewGroup = null;
            int i2 = -1;
            do {
                ViewGroup viewGroup2 = (ViewGroup) view.getParent();
                if (viewGroup2 != null) {
                    if (viewGroup2 instanceof NativeAdView) {
                        ViewGroup viewGroup3 = (ViewGroup) viewGroup2.getParent();
                        if (viewGroup3 != null) {
                            int indexOfChild = viewGroup3.indexOfChild(viewGroup2);
                            viewGroup2.removeView(view);
                            viewGroup3.removeView(viewGroup2);
                            viewGroup3.addView(view, indexOfChild);
                            continue;
                        }
                    } else {
                        i2 = viewGroup2.indexOfChild(view);
                        viewGroup = viewGroup2;
                        continue;
                    }
                }
                Log.e(a, "View must have valid parent for AdMob registration, skipping registration. Impressions and clicks will not be logged.");
                return;
            } while (viewGroup == null);
            NativeAdView nativeContentAdView = this.c instanceof NativeContentAd ? new NativeContentAdView(view.getContext()) : new NativeAppInstallAdView(view.getContext());
            if (view instanceof ViewGroup) {
                nativeContentAdView.setLayoutParams(view.getLayoutParams());
            }
            a(view);
            nativeContentAdView.addView(view);
            viewGroup.removeView(nativeContentAdView);
            viewGroup.addView(nativeContentAdView, i2);
            this.e = nativeContentAdView;
            this.e.setNativeAd(this.c);
            this.f = new View(view.getContext());
            this.e.addView(this.f);
            this.f.setVisibility(8);
            if (this.e instanceof NativeContentAdView) {
                ((NativeContentAdView) this.e).setCallToActionView(this.f);
            } else if (this.e instanceof NativeAppInstallAdView) {
                ((NativeAppInstallAdView) this.e).setCallToActionView(this.f);
            }
            AnonymousClass4 r6 = new View.OnClickListener() {
                public void onClick(View view) {
                    d.this.f.performClick();
                }
            };
            for (View onClickListener : list) {
                onClickListener.setOnClickListener(r6);
            }
        }
    }

    public void a(z zVar) {
        this.d = zVar;
    }

    public void a(Map<String, String> map) {
        if (c_() && this.d != null) {
            this.d.b(this);
        }
    }

    public void b(Map<String, String> map) {
    }

    public void b_() {
        ViewGroup viewGroup;
        a(this.f);
        this.f = null;
        if (this.b != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.b.getParent();
            if (((viewGroup2 instanceof NativeContentAdView) || (viewGroup2 instanceof NativeAppInstallAdView)) && (viewGroup = (ViewGroup) viewGroup2.getParent()) != null) {
                int indexOfChild = viewGroup.indexOfChild(viewGroup2);
                a(this.b);
                a((View) viewGroup2);
                viewGroup.addView(this.b, indexOfChild);
            }
            this.b = null;
        }
        this.e = null;
    }

    public String c() {
        return null;
    }

    public boolean c_() {
        return this.g && this.c != null;
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return false;
    }

    public boolean g() {
        return false;
    }

    public int h() {
        return 0;
    }

    public int i() {
        return 0;
    }

    public int j() {
        return 0;
    }

    public h k() {
        if (!c_() || this.i == null) {
            return null;
        }
        return new h(this.i.toString(), 50, 50);
    }

    public h l() {
        if (!c_() || this.h == null) {
            return null;
        }
        return new h(this.h.toString(), 1200, SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT);
    }

    public k m() {
        return null;
    }

    public String n() {
        return null;
    }

    public String o() {
        return null;
    }

    public void onDestroy() {
        b_();
        this.d = null;
        this.c = null;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    public String p() {
        return this.k;
    }

    public String q() {
        return this.l;
    }

    public String r() {
        return this.m;
    }

    public String s() {
        return null;
    }

    public String t() {
        return null;
    }

    public String u() {
        return null;
    }

    public String v() {
        return null;
    }

    public j w() {
        return null;
    }

    public h x() {
        return null;
    }

    public String y() {
        return null;
    }

    public String z() {
        return null;
    }
}
