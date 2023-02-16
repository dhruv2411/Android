package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.ads.internal.n.d;
import com.facebook.ads.internal.n.f;
import java.lang.ref.WeakReference;

public final class DefaultMediaViewVideoRenderer extends MediaViewVideoRenderer {
    private d d;

    private static class a implements d.a {
        private WeakReference<f> a;

        public a(f fVar) {
            this.a = new WeakReference<>(fVar);
        }

        public void a(boolean z) {
            if (this.a.get() != null) {
                ((f) this.a.get()).a(z, false);
            }
        }
    }

    public DefaultMediaViewVideoRenderer(Context context) {
        super(context);
        this.d = new d(context, this);
        b();
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new d(context, this);
        b();
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new d(context, this);
        b();
    }

    @TargetApi(21)
    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.d = new d(context, this);
        b();
    }

    private void b() {
        setVolume(0.0f);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.d.a();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d.c();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.d.d();
        super.onDetachedFromWindow();
    }

    public void onPrepared() {
        super.onPrepared();
        setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.d.b();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.d.e();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.d.f();
    }

    /* access modifiers changed from: protected */
    public void setNativeAd(NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        this.d.a(nativeAd.g(), (d.a) new a(nativeAd.g()));
    }
}
