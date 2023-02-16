package com.facebook.ads.internal.view.f.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.view.f.a;

public abstract class c extends RelativeLayout implements b {
    @Nullable
    private a a;

    public c(Context context) {
        super(context);
    }

    public c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    public void a(a aVar) {
        this.a = aVar;
        a();
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    public void b(a aVar) {
        b();
        this.a = null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public a getVideoView() {
        return this.a;
    }
}
