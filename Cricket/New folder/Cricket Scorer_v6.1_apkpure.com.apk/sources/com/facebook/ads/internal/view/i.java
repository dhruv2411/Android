package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.a;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.r;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.f;

public abstract class i extends RelativeLayout implements a {
    protected static final int a = ((int) (56.0f * x.b));
    /* access modifiers changed from: protected */
    public final c b;
    protected final f c = new f(getContext());
    protected a d;
    @Nullable
    private a.C0008a e;
    private final r f = new r(this);

    protected i(Context context, c cVar) {
        super(context.getApplicationContext());
        this.b = cVar;
    }

    private void a() {
        removeAllViews();
        x.b(this);
    }

    /* access modifiers changed from: protected */
    public void a(View view, boolean z, int i) {
        int d2;
        f fVar;
        d b2;
        this.f.a(r.a.DEFAULT);
        a();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, z ? 0 : a, 0, 0);
        addView(view, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, a);
        layoutParams2.addRule(10);
        if (i == 1) {
            d2 = this.d.a().d(z);
            fVar = this.c;
            b2 = this.d.a();
        } else {
            d2 = this.d.b().d(z);
            fVar = this.c;
            b2 = this.d.b();
        }
        fVar.a(b2, z);
        addView(this.c, layoutParams2);
        x.a((View) this, d2);
        if (this.e != null) {
            this.e.a((View) this);
            if (z && Build.VERSION.SDK_INT >= 19) {
                this.f.a(r.a.FULL_SCREEN);
            }
        }
    }

    public void a(final AudienceNetworkActivity audienceNetworkActivity, g gVar) {
        this.f.a(audienceNetworkActivity.getWindow());
        this.d = gVar.b();
        this.c.a(gVar.a(), gVar.c(), gVar.d().get(0).c().c());
        this.c.setToolbarListener(new f.a() {
            public void a() {
                audienceNetworkActivity.finish();
            }
        });
    }

    /* access modifiers changed from: protected */
    public a.C0008a getAudienceNetworkListener() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        this.c.d();
        super.onConfigurationChanged(configuration);
        final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = 16)
            public void onGlobalLayout() {
                i.this.c.e();
                if (Build.VERSION.SDK_INT >= 14) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public void onDestroy() {
        this.f.a();
        this.c.setToolbarListener((f.a) null);
        a();
    }

    public void setListener(a.C0008a aVar) {
        this.e = aVar;
    }
}
