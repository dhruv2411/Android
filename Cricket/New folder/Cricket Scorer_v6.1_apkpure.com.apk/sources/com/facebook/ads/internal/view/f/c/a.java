package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.q.b.b;
import com.facebook.ads.internal.q.c.g;
import com.facebook.ads.internal.view.f.a.c;

public class a extends c {
    private final C0012a a;

    /* renamed from: com.facebook.ads.internal.view.f.c.a$a  reason: collision with other inner class name */
    public static class C0012a extends RelativeLayout {
        private final String a;
        /* access modifiers changed from: private */
        public final String b;
        /* access modifiers changed from: private */
        public final String c;
        private final DisplayMetrics d;
        private ImageView e;
        /* access modifiers changed from: private */
        public TextView f;
        /* access modifiers changed from: private */
        public boolean g = false;

        public C0012a(Context context, String str, String str2, float[] fArr, String str3) {
            super(context);
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = context.getResources().getDisplayMetrics();
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
            gradientDrawable.setAlpha(178);
            gradientDrawable.setCornerRadii(new float[]{fArr[0] * this.d.density, fArr[0] * this.d.density, fArr[1] * this.d.density, fArr[1] * this.d.density, fArr[2] * this.d.density, fArr[2] * this.d.density, fArr[3] * this.d.density, fArr[3] * this.d.density});
            x.a((View) this, (Drawable) gradientDrawable);
            a();
            b();
            c();
            setMinimumWidth(Math.round(20.0f * this.d.density));
            setMinimumHeight(Math.round(18.0f * this.d.density));
        }

        private void a() {
            setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (!C0012a.this.g) {
                        C0012a.this.d();
                        return true;
                    } else if (TextUtils.isEmpty(C0012a.this.b)) {
                        return true;
                    } else {
                        g.a(new g(), C0012a.this.getContext(), Uri.parse(C0012a.this.b), C0012a.this.c);
                        return true;
                    }
                }
            });
        }

        private void b() {
            this.e = new ImageView(getContext());
            this.e.setImageBitmap(com.facebook.ads.internal.q.b.c.a(b.IC_AD_CHOICES));
            addView(this.e);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(this.d.density * 16.0f), Math.round(16.0f * this.d.density));
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            layoutParams.setMargins(Math.round(4.0f * this.d.density), Math.round(this.d.density * 2.0f), Math.round(this.d.density * 2.0f), Math.round(2.0f * this.d.density));
            this.e.setLayoutParams(layoutParams);
        }

        private void c() {
            this.f = new TextView(getContext());
            addView(this.f);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.width = 0;
            layoutParams.leftMargin = (int) (20.0f * this.d.density);
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            this.f.setLayoutParams(layoutParams);
            this.f.setSingleLine();
            this.f.setText(this.a);
            this.f.setTextSize(10.0f);
            this.f.setTextColor(-4341303);
        }

        /* access modifiers changed from: private */
        public void d() {
            Paint paint = new Paint();
            paint.setTextSize(this.f.getTextSize());
            int round = Math.round(paint.measureText(this.a) + (4.0f * this.d.density));
            final int width = getWidth();
            final int i = round + width;
            this.g = true;
            AnonymousClass2 r3 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    int i = (int) (((float) width) + (((float) (i - width)) * f));
                    C0012a.this.getLayoutParams().width = i;
                    C0012a.this.requestLayout();
                    C0012a.this.f.getLayoutParams().width = i - width;
                    C0012a.this.f.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            r3.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (C0012a.this.g) {
                                C0012a.this.e();
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
        public void e() {
            Paint paint = new Paint();
            paint.setTextSize(this.f.getTextSize());
            int round = Math.round(paint.measureText(this.a) + (4.0f * this.d.density));
            final int width = getWidth();
            final int i = width - round;
            AnonymousClass4 r2 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    int i = (int) (((float) width) + (((float) (i - width)) * f));
                    C0012a.this.getLayoutParams().width = i;
                    C0012a.this.requestLayout();
                    C0012a.this.f.getLayoutParams().width = i - i;
                    C0012a.this.f.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            r2.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    boolean unused = C0012a.this.g = false;
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

    public a(Context context, String str, String str2, float[] fArr) {
        super(context);
        this.a = new C0012a(context, "AdChoices", str, fArr, str2);
        addView(this.a);
    }
}
