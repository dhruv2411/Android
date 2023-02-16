package com.facebook.ads.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.BannerAdapter;
import com.facebook.ads.internal.adapters.BannerAdapterListener;
import com.facebook.ads.internal.adapters.InterstitialAdapter;
import com.facebook.ads.internal.adapters.InterstitialAdapterListener;
import com.facebook.ads.internal.adapters.ab;
import com.facebook.ads.internal.adapters.ac;
import com.facebook.ads.internal.adapters.s;
import com.facebook.ads.internal.adapters.u;
import com.facebook.ads.internal.adapters.v;
import com.facebook.ads.internal.adapters.z;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.o.g;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.f;
import com.facebook.ads.internal.protocol.h;
import com.facebook.ads.internal.q.a.l;
import com.facebook.ads.internal.q.a.o;
import com.facebook.ads.internal.q.a.y;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayAdController implements c.a {
    private static final String b = "DisplayAdController";
    private static final Handler h = new Handler(Looper.getMainLooper());
    private static boolean i = false;
    private boolean A;
    private final com.facebook.ads.internal.m.c B;
    private final EnumSet<CacheFlag> C;
    protected com.facebook.ads.internal.adapters.a a;
    /* access modifiers changed from: private */
    public final Context c;
    private final String d;
    private final AdPlacementType e;
    private final com.facebook.ads.internal.o.c f;
    /* access modifiers changed from: private */
    public final Handler g;
    private final Runnable j;
    private final Runnable k;
    /* access modifiers changed from: private */
    public volatile boolean l;
    private boolean m;
    /* access modifiers changed from: private */
    public volatile boolean n;
    /* access modifiers changed from: private */
    public AdAdapter o;
    /* access modifiers changed from: private */
    public AdAdapter p;
    /* access modifiers changed from: private */
    public View q;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.h.c r;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.o.b s;
    private f t;
    private d u;
    private e v;
    private int w;
    private boolean x;
    private int y;
    private final c z;

    private static final class a extends y<DisplayAdController> {
        public a(DisplayAdController displayAdController) {
            super(displayAdController);
        }

        public void run() {
            DisplayAdController displayAdController = (DisplayAdController) a();
            if (displayAdController != null) {
                boolean unused = displayAdController.l = false;
                displayAdController.b((String) null);
            }
        }
    }

    private static final class b extends y<DisplayAdController> {
        public b(DisplayAdController displayAdController) {
            super(displayAdController);
        }

        public void run() {
            DisplayAdController displayAdController = (DisplayAdController) a();
            if (displayAdController != null) {
                displayAdController.l();
            }
        }
    }

    private class c extends BroadcastReceiver {
        private c() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                DisplayAdController.this.m();
            } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                DisplayAdController.this.l();
            }
        }
    }

    static {
        com.facebook.ads.internal.q.a.d.a();
    }

    public DisplayAdController(Context context, String str, f fVar, AdPlacementType adPlacementType, e eVar, d dVar, int i2, boolean z2) {
        this(context, str, fVar, adPlacementType, eVar, dVar, i2, z2, EnumSet.of(CacheFlag.NONE));
    }

    public DisplayAdController(Context context, String str, f fVar, AdPlacementType adPlacementType, e eVar, d dVar, int i2, boolean z2, EnumSet<CacheFlag> enumSet) {
        this.g = new Handler();
        this.x = false;
        this.y = -1;
        this.c = context.getApplicationContext();
        this.d = str;
        this.t = fVar;
        this.e = adPlacementType;
        this.v = eVar;
        this.u = dVar;
        this.w = i2;
        this.z = new c();
        this.C = enumSet;
        this.f = new com.facebook.ads.internal.o.c(this.c);
        this.f.a((c.a) this);
        this.j = new a(this);
        this.k = new b(this);
        this.m = z2;
        g();
        try {
            CookieManager.getInstance();
            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(this.c);
            }
        } catch (Exception e2) {
            Log.w(b, "Failed to initialize CookieManager.", e2);
        }
        com.facebook.ads.internal.i.a.a(this.c).a();
        this.B = com.facebook.ads.internal.m.d.a(this.c);
    }

    /* access modifiers changed from: private */
    public Map<String, String> a(long j2) {
        HashMap hashMap = new HashMap();
        hashMap.put("delay", String.valueOf(System.currentTimeMillis() - j2));
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void a(AdAdapter adAdapter) {
        if (adAdapter != null) {
            adAdapter.onDestroy();
        }
    }

    private void a(final BannerAdapter bannerAdapter, com.facebook.ads.internal.h.c cVar, Map<String, Object> map) {
        final AnonymousClass8 r0 = new Runnable() {
            public void run() {
                DisplayAdController.this.a((AdAdapter) bannerAdapter);
                DisplayAdController.this.j();
            }
        };
        this.g.postDelayed(r0, (long) cVar.a().j());
        bannerAdapter.loadBannerAd(this.c, this.B, this.v, new BannerAdapterListener() {
            public void onBannerAdClicked(BannerAdapter bannerAdapter) {
                DisplayAdController.this.a.a();
            }

            public void onBannerAdExpanded(BannerAdapter bannerAdapter) {
            }

            public void onBannerAdLoaded(BannerAdapter bannerAdapter, View view) {
                if (bannerAdapter == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(r0);
                    AdAdapter f = DisplayAdController.this.p;
                    AdAdapter unused = DisplayAdController.this.p = bannerAdapter;
                    View unused2 = DisplayAdController.this.q = view;
                    if (!DisplayAdController.this.n) {
                        DisplayAdController.this.a.a((AdAdapter) bannerAdapter);
                        return;
                    }
                    DisplayAdController.this.a.a(view);
                    DisplayAdController.this.a(f);
                }
            }

            public void onBannerAdMinimized(BannerAdapter bannerAdapter) {
            }

            public void onBannerError(BannerAdapter bannerAdapter, AdError adError) {
                if (bannerAdapter == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(r0);
                    DisplayAdController.this.a((AdAdapter) bannerAdapter);
                    DisplayAdController.this.j();
                }
            }

            public void onBannerLoggingImpression(BannerAdapter bannerAdapter) {
                DisplayAdController.this.a.b();
            }
        }, map);
    }

    private void a(final InterstitialAdapter interstitialAdapter, com.facebook.ads.internal.h.c cVar, Map<String, Object> map) {
        final AnonymousClass10 r0 = new Runnable() {
            public void run() {
                DisplayAdController.this.a((AdAdapter) interstitialAdapter);
                DisplayAdController.this.j();
            }
        };
        this.g.postDelayed(r0, (long) cVar.a().j());
        interstitialAdapter.loadInterstitialAd(this.c, new InterstitialAdapterListener() {
            public void onInterstitialActivityDestroyed() {
                DisplayAdController.this.a.f();
            }

            public void onInterstitialAdClicked(InterstitialAdapter interstitialAdapter, String str, boolean z) {
                DisplayAdController.this.a.a();
                boolean z2 = !TextUtils.isEmpty(str);
                if (z && z2) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    if (!(DisplayAdController.this.s.b instanceof Activity)) {
                        intent.addFlags(268435456);
                    }
                    intent.setData(Uri.parse(str));
                    DisplayAdController.this.s.b.startActivity(intent);
                }
            }

            public void onInterstitialAdDismissed(InterstitialAdapter interstitialAdapter) {
                DisplayAdController.this.a.e();
            }

            public void onInterstitialAdDisplayed(InterstitialAdapter interstitialAdapter) {
                DisplayAdController.this.a.d();
            }

            public void onInterstitialAdLoaded(InterstitialAdapter interstitialAdapter) {
                if (interstitialAdapter == DisplayAdController.this.o) {
                    if (interstitialAdapter == null) {
                        com.facebook.ads.internal.q.d.a.a(DisplayAdController.this.c, "api", com.facebook.ads.internal.q.d.b.b, (Exception) new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_LOAD, "Adapter is null on loadInterstitialAd"));
                        onInterstitialError(interstitialAdapter, AdError.INTERNAL_ERROR);
                        return;
                    }
                    DisplayAdController.this.g.removeCallbacks(r0);
                    AdAdapter unused = DisplayAdController.this.p = interstitialAdapter;
                    DisplayAdController.this.a.a((AdAdapter) interstitialAdapter);
                    DisplayAdController.this.l();
                }
            }

            public void onInterstitialError(InterstitialAdapter interstitialAdapter, AdError adError) {
                if (interstitialAdapter == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(r0);
                    DisplayAdController.this.a((AdAdapter) interstitialAdapter);
                    DisplayAdController.this.j();
                    DisplayAdController.this.a.a(new com.facebook.ads.internal.protocol.a(adError.getErrorCode(), adError.getErrorMessage()));
                }
            }

            public void onInterstitialLoggingImpression(InterstitialAdapter interstitialAdapter) {
                DisplayAdController.this.a.b();
            }
        }, map, this.B, this.C);
    }

    private void a(ab abVar, com.facebook.ads.internal.h.c cVar, Map<String, Object> map) {
        abVar.a(this.c, new ac() {
            public void a() {
                DisplayAdController.this.a.h();
            }

            public void a(ab abVar) {
                AdAdapter unused = DisplayAdController.this.p = abVar;
                DisplayAdController.this.a.a((AdAdapter) abVar);
            }

            public void a(ab abVar, AdError adError) {
                DisplayAdController.this.a.a(new com.facebook.ads.internal.protocol.a(AdErrorType.INTERNAL_ERROR, (String) null));
                DisplayAdController.this.a((AdAdapter) abVar);
                DisplayAdController.this.j();
            }

            public void b() {
                DisplayAdController.this.a.k();
            }

            public void b(ab abVar) {
                DisplayAdController.this.a.a();
            }

            public void c(ab abVar) {
                DisplayAdController.this.a.b();
            }

            public void d(ab abVar) {
                DisplayAdController.this.a.g();
            }

            public void e(ab abVar) {
                DisplayAdController.this.a.i();
            }

            public void f(ab abVar) {
                DisplayAdController.this.a.j();
            }
        }, map, this.x);
    }

    private void a(s sVar, com.facebook.ads.internal.h.c cVar, Map<String, Object> map) {
        sVar.a(this.c, new com.facebook.ads.a.a() {
            public void a(s sVar) {
                AdAdapter unused = DisplayAdController.this.p = sVar;
                boolean unused2 = DisplayAdController.this.n = false;
                DisplayAdController.this.a.a((AdAdapter) sVar);
            }

            public void a(s sVar, View view) {
                DisplayAdController.this.a.a(view);
            }

            public void a(s sVar, AdError adError) {
                DisplayAdController.this.a.a(new com.facebook.ads.internal.protocol.a(adError.getErrorCode(), adError.getErrorMessage()));
            }

            public void b(s sVar) {
                DisplayAdController.this.a.a();
            }

            public void c(s sVar) {
                DisplayAdController.this.a.b();
            }

            public void d(s sVar) {
                DisplayAdController.this.a.c();
            }
        }, map, this.B, this.C);
    }

    private void a(com.facebook.ads.internal.adapters.y yVar, com.facebook.ads.internal.h.c cVar, com.facebook.ads.internal.h.a aVar, Map<String, Object> map) {
        final com.facebook.ads.internal.adapters.y yVar2 = yVar;
        final long currentTimeMillis = System.currentTimeMillis();
        final com.facebook.ads.internal.h.a aVar2 = aVar;
        AnonymousClass12 r0 = new Runnable() {
            public void run() {
                DisplayAdController.this.a((AdAdapter) yVar2);
                if (yVar2 instanceof u) {
                    Context h = DisplayAdController.this.c;
                    com.facebook.ads.internal.q.a.d.a(h, v.a(((u) yVar2).J()) + " Failed. Ad request timed out");
                }
                Map a2 = DisplayAdController.this.a(currentTimeMillis);
                a2.put("error", "-1");
                a2.put(NotificationCompat.CATEGORY_MESSAGE, "timeout");
                DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.h.e.REQUEST), (Map<String, String>) a2);
                DisplayAdController.this.j();
            }
        };
        this.g.postDelayed(r0, (long) cVar.a().j());
        final AnonymousClass12 r2 = r0;
        yVar.a(this.c, new z() {
            boolean a = false;
            boolean b = false;
            boolean c = false;

            public void a(com.facebook.ads.internal.adapters.y yVar) {
                if (yVar == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(r2);
                    AdAdapter unused = DisplayAdController.this.p = yVar;
                    DisplayAdController.this.a.a((AdAdapter) yVar);
                    if (!this.a) {
                        this.a = true;
                        DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.h.e.REQUEST), (Map<String, String>) DisplayAdController.this.a(currentTimeMillis));
                    }
                }
            }

            public void a(com.facebook.ads.internal.adapters.y yVar, com.facebook.ads.internal.protocol.a aVar) {
                if (yVar == DisplayAdController.this.o) {
                    DisplayAdController.this.g.removeCallbacks(r2);
                    DisplayAdController.this.a((AdAdapter) yVar);
                    if (!this.a) {
                        this.a = true;
                        Map a2 = DisplayAdController.this.a(currentTimeMillis);
                        a2.put("error", String.valueOf(aVar.a().getErrorCode()));
                        a2.put(NotificationCompat.CATEGORY_MESSAGE, String.valueOf(aVar.b()));
                        DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.h.e.REQUEST), (Map<String, String>) a2);
                    }
                    DisplayAdController.this.j();
                }
            }

            public void b(com.facebook.ads.internal.adapters.y yVar) {
                if (!this.b) {
                    this.b = true;
                    DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.h.e.IMPRESSION), (Map<String, String>) null);
                }
            }

            public void c(com.facebook.ads.internal.adapters.y yVar) {
                if (!this.c) {
                    this.c = true;
                    DisplayAdController.this.a(aVar2.a(com.facebook.ads.internal.h.e.CLICK), (Map<String, String>) null);
                }
                if (DisplayAdController.this.a != null) {
                    DisplayAdController.this.a.a();
                }
            }
        }, this.B, map, NativeAdBase.getViewTraversalPredicate());
    }

    /* access modifiers changed from: private */
    public void a(List<String> list, Map<String, String> map) {
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                new com.facebook.ads.internal.q.c.e(this.c, map).execute(new String[]{str});
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        try {
            h hVar = new h(this.c, str, this.d, this.t);
            Context context = this.c;
            com.facebook.ads.internal.i.c cVar = new com.facebook.ads.internal.i.c(this.c, false);
            String str2 = this.d;
            l lVar = this.v != null ? new l(this.v.b(), this.v.a()) : null;
            f fVar = this.t;
            d dVar = this.u;
            com.facebook.ads.internal.o.b bVar = r2;
            com.facebook.ads.internal.o.b bVar2 = new com.facebook.ads.internal.o.b(context, cVar, str2, lVar, fVar, dVar, AdSettings.getTestAdType() != AdSettings.TestAdType.DEFAULT ? AdSettings.getTestAdType().getAdTypeString() : null, com.facebook.ads.internal.adapters.e.a(com.facebook.ads.internal.protocol.c.a(this.t).a()), this.w, AdSettings.isTestMode(this.c), AdSettings.isChildDirected(), hVar, o.a(com.facebook.ads.internal.l.a.q(this.c)));
            com.facebook.ads.internal.o.b bVar3 = bVar;
            this.s = bVar3;
            this.f.a(this.s);
        } catch (com.facebook.ads.internal.protocol.b e2) {
            a(com.facebook.ads.internal.protocol.a.a(e2));
        }
    }

    private void g() {
        if (!this.m) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.c.registerReceiver(this.z, intentFilter);
            this.A = true;
        }
    }

    private void h() {
        if (this.A) {
            try {
                this.c.unregisterReceiver(this.z);
                this.A = false;
            } catch (Exception e2) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(e2, "Error unregistering screen state receiever"));
            }
        }
    }

    private AdPlacementType i() {
        return this.e != null ? this.e : this.v == null ? AdPlacementType.NATIVE : this.v == e.INTERSTITIAL ? AdPlacementType.INTERSTITIAL : AdPlacementType.BANNER;
    }

    /* access modifiers changed from: private */
    public synchronized void j() {
        h.post(new Runnable() {
            public void run() {
                try {
                    DisplayAdController.this.k();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void k() {
        this.o = null;
        com.facebook.ads.internal.h.c cVar = this.r;
        com.facebook.ads.internal.h.a d2 = cVar.d();
        if (d2 == null) {
            this.a.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NO_FILL, ""));
            l();
            return;
        }
        String a2 = d2.a();
        AdAdapter a3 = com.facebook.ads.internal.adapters.e.a(a2, cVar.a().b());
        if (a3 == null) {
            String str = b;
            Log.e(str, "Adapter does not exist: " + a2);
            j();
        } else if (i() != a3.getPlacementType()) {
            this.a.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.INTERNAL_ERROR, ""));
        } else {
            this.o = a3;
            HashMap hashMap = new HashMap();
            com.facebook.ads.internal.h.d a4 = cVar.a();
            hashMap.put("data", d2.b());
            hashMap.put("definition", a4);
            hashMap.put(AudienceNetworkActivity.PLACEMENT_ID, this.d);
            hashMap.put(AudienceNetworkActivity.REQUEST_TIME, Long.valueOf(a4.a()));
            if (this.s == null) {
                this.a.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.UNKNOWN_ERROR, "environment is empty"));
                return;
            }
            switch (a3.getPlacementType()) {
                case INTERSTITIAL:
                    a((InterstitialAdapter) a3, cVar, (Map<String, Object>) hashMap);
                    return;
                case BANNER:
                    a((BannerAdapter) a3, cVar, (Map<String, Object>) hashMap);
                    return;
                case NATIVE:
                case NATIVE_BANNER:
                    a((com.facebook.ads.internal.adapters.y) a3, cVar, d2, hashMap);
                    return;
                case INSTREAM:
                    a((s) a3, cVar, (Map<String, Object>) hashMap);
                    return;
                case REWARDED_VIDEO:
                    a((ab) a3, cVar, (Map<String, Object>) hashMap);
                    return;
                default:
                    Log.e(b, "attempt unexpected adapter type");
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        if (!this.m && !this.l && AnonymousClass4.a[i().ordinal()] == 1) {
            if (!com.facebook.ads.internal.q.e.a.a(this.c)) {
                this.g.postDelayed(this.k, 1000);
            }
            long c2 = this.r == null ? DashMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_FIXED_MS : this.r.a().c();
            if (c2 > 0) {
                this.g.postDelayed(this.j, c2);
                this.l = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        if (this.l) {
            this.g.removeCallbacks(this.j);
            this.l = false;
        }
    }

    private Handler n() {
        return !o() ? this.g : h;
    }

    private static synchronized boolean o() {
        boolean z2;
        synchronized (DisplayAdController.class) {
            z2 = i;
        }
        return z2;
    }

    protected static synchronized void setMainThreadForced(boolean z2) {
        synchronized (DisplayAdController.class) {
            String str = b;
            Log.d(str, "DisplayAdController changed main thread forced from " + i + " to " + z2);
            i = z2;
        }
    }

    public com.facebook.ads.internal.h.d a() {
        if (this.r == null) {
            return null;
        }
        return this.r.a();
    }

    public void a(int i2) {
        this.y = i2;
    }

    public void a(RewardData rewardData) {
        if (this.p == null) {
            throw new IllegalStateException("no adapter ready to set reward on");
        } else if (this.p.getPlacementType() != AdPlacementType.REWARDED_VIDEO) {
            throw new IllegalStateException("can only set on rewarded video ads");
        } else {
            ((ab) this.p).a(rewardData);
        }
    }

    public void a(com.facebook.ads.internal.adapters.a aVar) {
        this.a = aVar;
    }

    public synchronized void a(final g gVar) {
        n().post(new Runnable() {
            public void run() {
                com.facebook.ads.internal.h.c a2 = gVar.a();
                if (a2 == null || a2.a() == null) {
                    throw new IllegalStateException("invalid placement in response");
                }
                com.facebook.ads.internal.h.c unused = DisplayAdController.this.r = a2;
                DisplayAdController.this.j();
            }
        });
    }

    public synchronized void a(final com.facebook.ads.internal.protocol.a aVar) {
        n().post(new Runnable() {
            public void run() {
                DisplayAdController.this.a.a(aVar);
            }
        });
    }

    public void a(String str) {
        b(str);
    }

    public void a(boolean z2) {
        this.x = z2;
    }

    public void b() {
        com.facebook.ads.internal.adapters.a aVar;
        AdErrorType adErrorType;
        AdErrorType adErrorType2;
        if (this.p == null) {
            com.facebook.ads.internal.q.d.a.a(this.c, "api", com.facebook.ads.internal.q.d.b.e, (Exception) new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_START, "Adapter is null on startAd"));
            aVar = this.a;
            adErrorType = AdErrorType.INTERNAL_ERROR;
            adErrorType2 = AdErrorType.INTERNAL_ERROR;
        } else if (this.n) {
            com.facebook.ads.internal.q.d.a.a(this.c, "api", com.facebook.ads.internal.q.d.b.c, (Exception) new com.facebook.ads.internal.protocol.b(AdErrorType.AD_ALREADY_STARTED, "ad already started"));
            aVar = this.a;
            adErrorType = AdErrorType.AD_ALREADY_STARTED;
            adErrorType2 = AdErrorType.AD_ALREADY_STARTED;
        } else {
            this.n = true;
            switch (this.p.getPlacementType()) {
                case INTERSTITIAL:
                    ((InterstitialAdapter) this.p).show();
                    return;
                case BANNER:
                    if (this.q != null) {
                        this.a.a(this.q);
                        return;
                    }
                    return;
                case NATIVE:
                case NATIVE_BANNER:
                    com.facebook.ads.internal.adapters.y yVar = (com.facebook.ads.internal.adapters.y) this.p;
                    if (!yVar.c_()) {
                        throw new IllegalStateException("ad is not ready or already displayed");
                    }
                    this.a.a(yVar);
                    return;
                case INSTREAM:
                    ((s) this.p).e();
                    return;
                case REWARDED_VIDEO:
                    ab abVar = (ab) this.p;
                    abVar.a(this.y);
                    abVar.b();
                    return;
                default:
                    Log.e(b, "start unexpected adapter type");
                    return;
            }
        }
        aVar.a(com.facebook.ads.internal.protocol.a.a(adErrorType, adErrorType2.getDefaultErrorMessage()));
    }

    public void b(boolean z2) {
        h();
        if (z2 || this.n) {
            m();
            a(this.p);
            this.f.a();
            this.q = null;
            this.n = false;
        }
    }

    public void c() {
        b(false);
    }

    public boolean d() {
        return this.r == null || this.r.e();
    }

    public com.facebook.ads.internal.m.c e() {
        return this.B;
    }

    public AdAdapter f() {
        return this.p;
    }
}
