package com.facebook.ads;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.h;
import com.facebook.ads.internal.q.a.j;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.q.c.g;

public class AdChoicesView extends RelativeLayout {
    /* access modifiers changed from: private */
    public final NativeAdBase a;
    private final float b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public TextView d;
    private String e;

    public AdChoicesView(Context context, NativeAdBase nativeAdBase) {
        this(context, nativeAdBase, false);
    }

    public AdChoicesView(Context context, final NativeAdBase nativeAdBase, boolean z) {
        super(context);
        boolean z2 = false;
        this.c = false;
        this.a = nativeAdBase;
        this.b = x.b;
        if (!this.a.isAdLoaded() || this.a.h().g()) {
            this.e = this.a.getAdChoicesText();
            if (TextUtils.isEmpty(this.e)) {
                this.e = "AdChoices";
            }
            h y = this.a.g().y();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (!AdChoicesView.this.c) {
                        AdChoicesView.this.a();
                        return true;
                    } else if (TextUtils.isEmpty(AdChoicesView.this.a.getAdChoicesLinkUrl())) {
                        return true;
                    } else {
                        g.a(new g(), AdChoicesView.this.getContext(), Uri.parse(AdChoicesView.this.a.getAdChoicesLinkUrl()), nativeAdBase.i());
                        return true;
                    }
                }
            });
            this.d = new TextView(getContext());
            addView(this.d);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            if (!z || y == null) {
                z2 = true;
            } else {
                layoutParams2.addRule(11, a(y).getId());
                layoutParams2.width = 0;
                layoutParams.width = Math.round(((float) (y.b() + 4)) * this.b);
                layoutParams.height = Math.round(((float) (y.c() + 2)) * this.b);
            }
            this.c = z2;
            setLayoutParams(layoutParams);
            layoutParams2.addRule(15, -1);
            this.d.setLayoutParams(layoutParams2);
            this.d.setSingleLine();
            this.d.setText(this.e);
            this.d.setTextSize(10.0f);
            this.d.setTextColor(-4341303);
            j.a(this, j.INTERNAL_AD_CHOICES_ICON);
            j.a(this.d, j.INTERNAL_AD_CHOICES_ICON);
            return;
        }
        setVisibility(8);
    }

    private ImageView a(h hVar) {
        ImageView imageView = new ImageView(getContext());
        addView(imageView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(((float) hVar.b()) * this.b), Math.round(((float) hVar.c()) * this.b));
        layoutParams.addRule(9);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(Math.round(4.0f * this.b), Math.round(this.b * 2.0f), Math.round(this.b * 2.0f), Math.round(2.0f * this.b));
        imageView.setLayoutParams(layoutParams);
        f.a(hVar, imageView);
        return imageView;
    }

    /* access modifiers changed from: private */
    public void a() {
        Paint paint = new Paint();
        paint.setTextSize(this.d.getTextSize());
        int round = Math.round(paint.measureText(this.e) + (4.0f * this.b));
        final int width = getWidth();
        final int i = round + width;
        this.c = true;
        AnonymousClass2 r3 = new Animation() {
            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                int i = (int) (((float) width) + (((float) (i - width)) * f));
                AdChoicesView.this.getLayoutParams().width = i;
                AdChoicesView.this.requestLayout();
                AdChoicesView.this.d.getLayoutParams().width = i - width;
                AdChoicesView.this.d.requestLayout();
            }

            public boolean willChangeBounds() {
                return true;
            }
        };
        r3.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (AdChoicesView.this.c) {
                            AdChoicesView.this.b();
                        }
                    }
                }, 3000);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        r3.setDuration(300);
        r3.setFillAfter(true);
        startAnimation(r3);
    }

    /* access modifiers changed from: private */
    public void b() {
        Paint paint = new Paint();
        paint.setTextSize(this.d.getTextSize());
        int round = Math.round(paint.measureText(this.e) + (4.0f * this.b));
        final int width = getWidth();
        final int i = width - round;
        AnonymousClass4 r2 = new Animation() {
            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                int i = (int) (((float) width) + (((float) (i - width)) * f));
                AdChoicesView.this.getLayoutParams().width = i;
                AdChoicesView.this.requestLayout();
                AdChoicesView.this.d.getLayoutParams().width = i - i;
                AdChoicesView.this.d.requestLayout();
            }

            public boolean willChangeBounds() {
                return true;
            }
        };
        r2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                boolean unused = AdChoicesView.this.c = false;
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        r2.setDuration(300);
        r2.setFillAfter(true);
        startAnimation(r2);
    }
}
