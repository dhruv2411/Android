package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.d;
import com.facebook.ads.internal.view.f.b.e;
import com.facebook.ads.internal.view.f.b.g;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.p;

public class u implements a {
    private final k a = new k() {
        public void a(j jVar) {
            u.this.h.a("videoInterstitalEvent", jVar);
        }
    };
    private final i b = new i() {
        public void a(h hVar) {
            u.this.h.a("videoInterstitalEvent", hVar);
        }
    };
    private final c c = new c() {
        public void a(b bVar) {
            u.this.h.a("videoInterstitalEvent", bVar);
        }
    };
    private final e d = new e() {
        public void a(d dVar) {
            u.this.e.finish();
        }
    };
    /* access modifiers changed from: private */
    public final AudienceNetworkActivity e;
    private final com.facebook.ads.internal.m.c f;
    private final a g;
    /* access modifiers changed from: private */
    public final a.C0008a h;
    private com.facebook.ads.internal.view.f.b i;
    private int j;

    public u(final AudienceNetworkActivity audienceNetworkActivity, com.facebook.ads.internal.m.c cVar, a.C0008a aVar) {
        this.e = audienceNetworkActivity;
        this.f = cVar;
        this.g = new com.facebook.ads.internal.view.f.a(audienceNetworkActivity);
        this.g.a((com.facebook.ads.internal.view.f.a.b) new com.facebook.ads.internal.view.f.c.b(audienceNetworkActivity));
        this.g.getEventBus().a((T[]) new f[]{this.a, this.b, this.c, this.d});
        this.h = aVar;
        this.g.setIsFullScreen(true);
        this.g.setVolume(1.0f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        this.g.setLayoutParams(layoutParams);
        aVar.a((View) this.g);
        d dVar = new d(audienceNetworkActivity);
        dVar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                audienceNetworkActivity.finish();
            }
        });
        aVar.a((View) dVar);
    }

    public void a(int i2) {
        this.g.setVideoProgressReportIntervalMs(i2);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        String stringExtra = intent.getStringExtra("useNativeCtaButton");
        if (stringExtra != null && !stringExtra.isEmpty()) {
            com.facebook.ads.internal.view.c.b bVar = new com.facebook.ads.internal.view.c.b(audienceNetworkActivity, stringExtra);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            int i2 = (int) (16.0f * x.b);
            layoutParams.setMargins(i2, i2, i2, i2);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            bVar.setLayoutParams(layoutParams);
            bVar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    u.this.h.a("performCtaClick");
                }
            });
            this.h.a((View) bVar);
        }
        this.j = intent.getIntExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, 0);
        this.i = new com.facebook.ads.internal.view.f.b((Context) audienceNetworkActivity, this.f, this.g, intent.getStringExtra(AudienceNetworkActivity.CLIENT_TOKEN), intent.getBundleExtra(AudienceNetworkActivity.VIDEO_LOGGER));
        this.g.setVideoMPD(intent.getStringExtra(AudienceNetworkActivity.VIDEO_MPD));
        this.g.setVideoURI(intent.getStringExtra(AudienceNetworkActivity.VIDEO_URL));
        if (this.j > 0) {
            this.g.a(this.j);
        }
        if (intent.getBooleanExtra(AudienceNetworkActivity.AUTOPLAY, false)) {
            this.g.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
        }
    }

    public void a(Bundle bundle) {
    }

    public void a(View view) {
        this.g.setControlsAnchorView(view);
    }

    public void i() {
        this.h.a("videoInterstitalEvent", new com.facebook.ads.internal.view.f.b.f());
        this.g.a(false);
    }

    public void j() {
        this.h.a("videoInterstitalEvent", new g());
        this.g.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
    }

    public void onDestroy() {
        this.h.a("videoInterstitalEvent", new p(this.j, this.g.getCurrentPositionInMillis()));
        this.i.b(this.g.getCurrentPositionInMillis());
        this.g.f();
        this.g.k();
    }

    public void setListener(a.C0008a aVar) {
    }
}
