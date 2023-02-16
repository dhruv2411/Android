package com.facebook.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.k;
import com.facebook.ads.internal.adapters.l;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.settings.a;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.c.a.f;
import com.facebook.ads.internal.view.e;
import com.facebook.ads.internal.view.f.b.z;
import com.facebook.ads.internal.view.g;
import com.facebook.ads.internal.view.h;
import com.facebook.ads.internal.view.o;
import com.facebook.ads.internal.view.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AudienceNetworkActivity extends Activity {
    @Deprecated
    public static final String AD_ICON_URL = "adIconUrl";
    @Deprecated
    public static final String AD_SUBTITLE = "adSubtitle";
    @Deprecated
    public static final String AD_TITLE = "adTitle";
    public static final String AUDIENCE_NETWORK_UNIQUE_ID_EXTRA = "uniqueId";
    public static final String AUTOPLAY = "autoplay";
    public static final String BROWSER_URL = "browserURL";
    public static final String CLIENT_TOKEN = "clientToken";
    @Deprecated
    public static final String CONTEXT_SWITCH_BEHAVIOR = "contextSwitchBehavior";
    @Deprecated
    public static final String END_CARD_ACTIVATION_COMMAND = "facebookRewardedVideoEndCardActivationCommand";
    @Deprecated
    public static final String END_CARD_MARKUP = "facebookRewardedVideoEndCardMarkup";
    public static final String HANDLER_TIME = "handlerTime";
    public static final String PLACEMENT_ID = "placementId";
    public static final String PREDEFINED_ORIENTATION_KEY = "predefinedOrientationKey";
    public static final String REQUEST_TIME = "requestTime";
    public static final String REWARD_SERVER_URL = "rewardServerURL";
    public static final String SKIP_DELAY_SECONDS_KEY = "skipAfterSeconds";
    public static final String USE_CACHE = "useCache";
    public static final String VIDEO_LOGGER = "videoLogger";
    public static final String VIDEO_MPD = "videoMPD";
    @Deprecated
    public static final String VIDEO_REPORT_URL = "videoReportURL";
    public static final String VIDEO_SEEK_TIME = "videoSeekTime";
    public static final String VIDEO_URL = "videoURL";
    public static final String VIEW_TYPE = "viewType";
    @Deprecated
    public static final String WEBVIEW_ENCODING = "utf-8";
    @Deprecated
    public static final String WEBVIEW_MIME_TYPE = "text/html";
    private final List<BackButtonInterceptor> a = new ArrayList();
    /* access modifiers changed from: private */
    public RelativeLayout b;
    private int c = -1;
    private String d;
    private a.C0007a e;
    private long f;
    private long g;
    private int h;
    private com.facebook.ads.internal.view.a i;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.view.b.c j;

    public interface BackButtonInterceptor {
        boolean interceptBackButton();
    }

    public enum Type {
        INTERSTITIAL_WEB_VIEW(a.C0007a.INTERSTITIAL_WEB_VIEW),
        INTERSTITIAL_OLD_NATIVE_VIDEO(a.C0007a.INTERSTITIAL_OLD_NATIVE_VIDEO),
        INTERSTITIAL_NATIVE_VIDEO(a.C0007a.INTERSTITIAL_NATIVE_VIDEO),
        INTERSTITIAL_NATIVE_IMAGE(a.C0007a.INTERSTITIAL_NATIVE_IMAGE),
        INTERSTITIAL_NATIVE_CAROUSEL(a.C0007a.INTERSTITIAL_NATIVE_CAROUSEL),
        FULL_SCREEN_VIDEO(a.C0007a.FULL_SCREEN_VIDEO),
        REWARDED_VIDEO(a.C0007a.REWARDED_VIDEO),
        BROWSER(a.C0007a.BROWSER);
        
        a.C0007a a;

        private Type(a.C0007a aVar) {
            this.a = aVar;
        }
    }

    private static class a implements a.C0008a {
        final WeakReference<AudienceNetworkActivity> a;

        private a(AudienceNetworkActivity audienceNetworkActivity) {
            this.a = new WeakReference<>(audienceNetworkActivity);
        }

        public void a(View view) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).b.addView(view);
            }
        }

        public void a(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).a(str);
            }
        }

        public void a(String str, com.facebook.ads.internal.j.d dVar) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).a(str, dVar);
            }
        }
    }

    private static class b {
        private final AudienceNetworkActivity a;
        private final Intent b;
        private final com.facebook.ads.internal.m.c c;

        private b(AudienceNetworkActivity audienceNetworkActivity, Intent intent, com.facebook.ads.internal.m.c cVar) {
            this.a = audienceNetworkActivity;
            this.b = intent;
            this.c = cVar;
        }

        /* access modifiers changed from: private */
        public com.facebook.ads.internal.view.a a() {
            h hVar = new h(this.a, this.c, i(), h() ? new com.facebook.ads.internal.d.b(this.a) : null);
            a((com.facebook.ads.internal.view.a) hVar);
            return hVar;
        }

        /* access modifiers changed from: private */
        public com.facebook.ads.internal.view.a a(RelativeLayout relativeLayout) {
            u uVar = new u(this.a, this.c, new a());
            uVar.a((View) relativeLayout);
            uVar.a(this.b.getIntExtra("video_time_polling_interval", 200));
            return uVar;
        }

        private void a(com.facebook.ads.internal.view.a aVar) {
            aVar.setListener(new a());
        }

        /* access modifiers changed from: private */
        @Nullable
        public com.facebook.ads.internal.view.a b() {
            com.facebook.ads.internal.view.a a2 = k.a(this.b.getStringExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA));
            if (a2 == null) {
                return null;
            }
            a(a2);
            return a2;
        }

        /* access modifiers changed from: private */
        public com.facebook.ads.internal.view.a c() {
            return new com.facebook.ads.internal.view.b(this.a, this.c, new a());
        }

        /* JADX WARNING: type inference failed for: r0v7, types: [com.facebook.ads.internal.view.n] */
        /* access modifiers changed from: private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.facebook.ads.internal.view.a d() {
            /*
                r8 = this;
                android.content.Intent r0 = r8.b
                java.lang.String r1 = "rewardedVideoAdDataBundle"
                java.io.Serializable r0 = r0.getSerializableExtra(r1)
                r6 = r0
                com.facebook.ads.internal.adapters.a.k r6 = (com.facebook.ads.internal.adapters.a.k) r6
                com.facebook.ads.internal.adapters.a.b r0 = r6.e()
                com.facebook.ads.internal.adapters.a.j r0 = r0.j()
                r1 = 0
                if (r0 == 0) goto L_0x0027
                com.facebook.ads.internal.view.n r0 = new com.facebook.ads.internal.view.n
                com.facebook.ads.AudienceNetworkActivity r2 = r8.a
                com.facebook.ads.internal.m.c r3 = r8.c
                com.facebook.ads.AudienceNetworkActivity$d r4 = new com.facebook.ads.AudienceNetworkActivity$d
                com.facebook.ads.AudienceNetworkActivity r5 = r8.a
                r4.<init>()
                r0.<init>(r2, r3, r4, r6)
                goto L_0x003f
            L_0x0027:
                com.facebook.ads.internal.view.o r0 = new com.facebook.ads.internal.view.o
                com.facebook.ads.AudienceNetworkActivity r2 = r8.a
                com.facebook.ads.internal.m.c r3 = r8.c
                com.facebook.ads.internal.view.f.a r4 = new com.facebook.ads.internal.view.f.a
                com.facebook.ads.AudienceNetworkActivity r5 = r8.a
                r4.<init>(r5)
                com.facebook.ads.AudienceNetworkActivity$d r5 = new com.facebook.ads.AudienceNetworkActivity$d
                com.facebook.ads.AudienceNetworkActivity r7 = r8.a
                r5.<init>()
                r1 = r0
                r1.<init>(r2, r3, r4, r5, r6)
            L_0x003f:
                com.facebook.ads.internal.view.a r0 = (com.facebook.ads.internal.view.a) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.AudienceNetworkActivity.b.d():com.facebook.ads.internal.view.a");
        }

        /* access modifiers changed from: private */
        public com.facebook.ads.internal.view.a e() {
            return new e(this.a, this.c, new a());
        }

        /* access modifiers changed from: private */
        public com.facebook.ads.internal.view.a f() {
            f fVar = new f(this.a, this.c, h() ? new com.facebook.ads.internal.d.b(this.a) : null);
            a((com.facebook.ads.internal.view.a) fVar);
            return fVar;
        }

        /* access modifiers changed from: private */
        public com.facebook.ads.internal.view.a g() {
            g gVar = new g(this.a, i(), this.c);
            a((com.facebook.ads.internal.view.a) gVar);
            return gVar;
        }

        private boolean h() {
            return this.b.getBooleanExtra(AudienceNetworkActivity.USE_CACHE, false);
        }

        private com.facebook.ads.internal.adapters.a.g i() {
            return (com.facebook.ads.internal.adapters.a.g) this.b.getSerializableExtra("ad_data_bundle");
        }
    }

    private class c implements View.OnLongClickListener {
        private c() {
        }

        public boolean onLongClick(View view) {
            if (!(AudienceNetworkActivity.this.j == null || AudienceNetworkActivity.this.b == null)) {
                AudienceNetworkActivity.this.j.setBounds(0, 0, AudienceNetworkActivity.this.b.getWidth(), AudienceNetworkActivity.this.b.getHeight());
                AudienceNetworkActivity.this.j.a(!AudienceNetworkActivity.this.j.a());
            }
            return true;
        }
    }

    private static class d extends a {
        private d(AudienceNetworkActivity audienceNetworkActivity) {
            super();
        }

        public void a(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).a(str);
                String a = z.REWARDED_VIDEO_END_ACTIVITY.a();
                String a2 = z.REWARDED_VIDEO_ERROR.a();
                if (str.equals(a) || str.equals(a2)) {
                    ((AudienceNetworkActivity) this.a.get()).finish();
                }
            }
        }
    }

    @Nullable
    private com.facebook.ads.internal.view.a a() {
        b bVar = new b(getIntent(), com.facebook.ads.internal.m.d.a((Context) this));
        if (this.e == null) {
            return null;
        }
        switch (this.e) {
            case FULL_SCREEN_VIDEO:
                return bVar.a(this.b);
            case REWARDED_VIDEO:
                return bVar.d();
            case INTERSTITIAL_WEB_VIEW:
                return bVar.e();
            case BROWSER:
                return bVar.c();
            case INTERSTITIAL_OLD_NATIVE_VIDEO:
                return bVar.b();
            case INTERSTITIAL_NATIVE_VIDEO:
                return bVar.a();
            case INTERSTITIAL_NATIVE_IMAGE:
                return bVar.g();
            case INTERSTITIAL_NATIVE_CAROUSEL:
                return bVar.f();
            default:
                return null;
        }
    }

    private void a(Intent intent, Bundle bundle) {
        if (bundle != null) {
            this.c = bundle.getInt(PREDEFINED_ORIENTATION_KEY, -1);
            this.d = bundle.getString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
            this.e = (a.C0007a) bundle.getSerializable(VIEW_TYPE);
            return;
        }
        this.c = intent.getIntExtra(PREDEFINED_ORIENTATION_KEY, -1);
        this.d = intent.getStringExtra(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
        this.e = (a.C0007a) intent.getSerializableExtra(VIEW_TYPE);
        this.h = intent.getIntExtra(SKIP_DELAY_SECONDS_KEY, 0) * 1000;
    }

    private void a(Intent intent, boolean z) {
        if (com.facebook.ads.internal.l.a.b(this) && this.e != a.C0007a.BROWSER) {
            this.j = new com.facebook.ads.internal.view.b.c();
            this.j.a(intent.getStringExtra(PLACEMENT_ID));
            this.j.b(getPackageName());
            long longExtra = intent.getLongExtra(REQUEST_TIME, 0);
            if (longExtra != 0) {
                this.j.a(longExtra);
            }
            TextView textView = new TextView(this);
            textView.setText("Debug");
            textView.setTextColor(-1);
            x.a((View) textView, Color.argb(160, 0, 0, 0));
            textView.setPadding(5, 5, 5, 5);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12, -1);
            layoutParams.addRule(11, -1);
            textView.setLayoutParams(layoutParams);
            c cVar = new c();
            textView.setOnLongClickListener(cVar);
            if (z) {
                this.b.addView(textView);
            } else {
                this.b.setOnLongClickListener(cVar);
            }
            this.b.getOverlay().add(this.j);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(str + ":" + this.d));
    }

    /* access modifiers changed from: private */
    public void a(String str, com.facebook.ads.internal.j.d dVar) {
        Intent intent = new Intent(str + ":" + this.d);
        intent.putExtra("event", dVar);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void addBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.a.add(backButtonInterceptor);
    }

    public void finish() {
        if (!isFinishing()) {
            a(this.e == a.C0007a.REWARDED_VIDEO ? z.REWARDED_VIDEO_CLOSED.a() : "com.facebook.ads.interstitial.dismissed");
            super.finish();
        }
    }

    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        this.g += currentTimeMillis - this.f;
        this.f = currentTimeMillis;
        if (this.g > ((long) this.h)) {
            boolean z = false;
            for (BackButtonInterceptor interceptBackButton : this.a) {
                if (interceptBackButton.interceptBackButton()) {
                    z = true;
                }
            }
            if (!z) {
                super.onBackPressed();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.i instanceof l) {
            ((l) this.i).a(configuration);
        } else if (this.i instanceof o) {
            ((o) this.i).onConfigurationChanged(configuration);
        }
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.facebook.ads.internal.q.a.d.a();
        boolean z = true;
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.b = new RelativeLayout(this);
        x.a((View) this.b, (int) ViewCompat.MEASURED_STATE_MASK);
        setContentView(this.b, new RelativeLayout.LayoutParams(-1, -1));
        Intent intent = getIntent();
        a(intent, bundle);
        this.i = a();
        if (this.i == null) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a((Throwable) null, "Unable to infer viewType from intent or savedInstanceState"));
            a("com.facebook.ads.interstitial.error");
            finish();
            return;
        }
        this.i.a(intent, bundle, this);
        a("com.facebook.ads.interstitial.displayed");
        this.f = System.currentTimeMillis();
        if (this.e != a.C0007a.INTERSTITIAL_WEB_VIEW) {
            z = false;
        }
        a(intent, z);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        a(this.e == a.C0007a.REWARDED_VIDEO ? z.REWARDED_VIDEO_ACTIVITY_DESTROYED.a() : "com.facebook.ads.interstitial.activity_destroyed");
        if (this.b != null) {
            this.b.removeAllViews();
        }
        if (this.i != null) {
            k.a(this.i);
            this.i.onDestroy();
            this.i = null;
        }
        if (this.j != null && com.facebook.ads.internal.l.a.b(this)) {
            this.j.b();
        }
        super.onDestroy();
    }

    public void onPause() {
        this.g += System.currentTimeMillis() - this.f;
        if (this.i != null) {
            this.i.i();
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f = System.currentTimeMillis();
        if (this.i != null) {
            this.i.j();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.i != null) {
            this.i.a(bundle);
        }
        bundle.putInt(PREDEFINED_ORIENTATION_KEY, this.c);
        bundle.putString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.d);
        bundle.putSerializable(VIEW_TYPE, this.e);
    }

    public void onStart() {
        super.onStart();
        if (this.c != -1) {
            setRequestedOrientation(this.c);
        }
    }

    public void removeBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.a.remove(backButtonInterceptor);
    }
}
