package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.adapters.ae;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.settings.a;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.k;
import java.util.UUID;

public class j extends a {
    private final String b = UUID.randomUUID().toString();
    private final k c = new k() {
        public void a(com.facebook.ads.internal.view.f.b.j jVar) {
            if (j.this.n != null) {
                j.this.n.c();
            }
        }
    };
    private final i d = new i() {
        public void a(h hVar) {
            if (j.this.n != null) {
                j.this.n.b();
            }
        }
    };
    private final c e = new c() {
        public void a(b bVar) {
            if (j.this.n != null) {
                j.this.n.h();
            }
        }
    };
    private final ae f;
    private com.facebook.ads.internal.m.c g;
    @Nullable
    private com.facebook.ads.internal.view.f.b h;
    @Nullable
    private String i;
    @Nullable
    private Uri j;
    @Nullable
    private String k;
    @Nullable
    private String l;
    @Nullable
    private String m;
    /* access modifiers changed from: private */
    @Nullable
    public k n;
    @Nullable
    private NativeAd o;

    public j(Context context) {
        super(context);
        this.f = new ae(this, context);
        t();
    }

    public j(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new ae(this, context);
        t();
    }

    public j(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f = new ae(this, context);
        t();
    }

    @TargetApi(21)
    public j(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f = new ae(this, context);
        t();
    }

    private void a(Intent intent) {
        if (this.i == null || this.h == null) {
            throw new IllegalStateException("Must setVideoReportUri first.");
        } else if (this.j == null && this.l == null) {
            throw new IllegalStateException("Must setVideoURI or setVideoMPD first.");
        } else {
            intent.putExtra("useNativeCtaButton", this.m);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, a.C0007a.FULL_SCREEN_VIDEO);
            intent.putExtra(AudienceNetworkActivity.VIDEO_URL, this.j.toString());
            intent.putExtra(AudienceNetworkActivity.CLIENT_TOKEN, this.k == null ? "" : this.k);
            intent.putExtra(AudienceNetworkActivity.VIDEO_MPD, this.l);
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, 13);
            intent.putExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, getCurrentPositionInMillis());
            intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.b);
            intent.putExtra(AudienceNetworkActivity.VIDEO_LOGGER, this.h.g());
            intent.putExtra("video_time_polling_interval", getVideoProgressReportIntervalMs());
            intent.addFlags(268435456);
        }
    }

    private void t() {
        getEventBus().a((T[]) new f[]{this.c, this.d, this.e});
    }

    public void a() {
        Context context = getContext();
        Intent intent = new Intent(context, AudienceNetworkActivity.class);
        a(intent);
        try {
            a(false);
            setVisibility(8);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            try {
                intent.setClass(context, InterstitialAdActivity.class);
                context.startActivity(intent);
            } catch (Exception e2) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(e2, "Error occurred while loading fullscreen video activity."));
            }
        }
    }

    public void a(@Nullable String str, @Nullable String str2) {
        if (this.h != null) {
            this.h.a();
        }
        this.k = str2;
        this.i = str;
        this.h = (str == null || str2 == null) ? null : new com.facebook.ads.internal.view.f.b(getContext(), this.g, this, str2);
    }

    public void b() {
        if (this.o != null) {
            this.o.onCtaBroadcast();
        }
    }

    @Nullable
    public k getListener() {
        return this.n;
    }

    public String getUniqueId() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f.a();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.f.b();
        super.onDetachedFromWindow();
    }

    public void setAdEventManager(com.facebook.ads.internal.m.c cVar) {
        this.g = cVar;
    }

    public void setEnableBackgroundVideo(boolean z) {
        this.a.setBackgroundPlaybackEnabled(z);
    }

    public void setListener(@Nullable k kVar) {
        this.n = kVar;
    }

    public void setNativeAd(@Nullable NativeAd nativeAd) {
        this.o = nativeAd;
    }

    public void setVideoCTA(@Nullable String str) {
        this.m = str;
    }

    public void setVideoMPD(@Nullable String str) {
        if (str == null || this.h != null) {
            this.l = str;
            super.setVideoMPD(str);
            return;
        }
        throw new IllegalStateException("Must setVideoReportUri first.");
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null || this.h != null) {
            this.j = uri;
            super.setVideoURI(uri);
            return;
        }
        throw new IllegalStateException("Must setVideoReportUri first.");
    }
}
