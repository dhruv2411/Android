package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.b.e;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.j;

public class g extends c {
    private final ImageView a;
    private final f<j> b = new f<j>() {
        public Class<j> a() {
            return j.class;
        }

        public void a(j jVar) {
            g.this.setVisibility(8);
        }
    };
    private final f<b> c = new f<b>() {
        public Class<b> a() {
            return b.class;
        }

        public void a(b bVar) {
            g.this.setVisibility(0);
        }
    };

    public g(Context context) {
        super(context);
        this.a = new ImageView(context);
        this.a.setScaleType(ImageView.ScaleType.FIT_CENTER);
        x.a((View) this.a, (int) ViewCompat.MEASURED_STATE_MASK);
        this.a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(this.a);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a((T[]) new f[]{this.b, this.c});
        }
    }

    public void a(@Nullable String str, @Nullable e eVar) {
        if (str == null) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        d a2 = new d(this.a).a();
        if (eVar != null) {
            a2.a(eVar);
        }
        a2.a(str);
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b((T[]) new f[]{this.c, this.b});
        }
        super.b();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a.layout(0, 0, i3 - i, i4 - i2);
    }

    public void setImage(@Nullable String str) {
        a(str, (e) null);
    }
}
