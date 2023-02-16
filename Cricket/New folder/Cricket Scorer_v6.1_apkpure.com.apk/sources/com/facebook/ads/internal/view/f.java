package com.facebook.ads.internal.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.i;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.q.c.g;
import com.facebook.ads.internal.view.component.CircularProgressView;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.n;
import com.facebook.ads.internal.view.f.b.o;

public class f extends LinearLayout implements b {
    private static final float c = Resources.getSystem().getDisplayMetrics().density;
    private static final int d = ((int) (40.0f * c));
    private static final int e = ((int) (44.0f * c));
    private static final int f = ((int) (10.0f * c));
    private static final int g = ((int) (16.0f * c));
    private static final int h = (g - f);
    private static final int i = ((2 * g) - f);
    /* access modifiers changed from: private */
    public final o a = new o() {
        public void a(n nVar) {
            if (f.this.q != null && f.this.r != 0 && f.this.m.isShown()) {
                float currentPositionInMillis = ((float) f.this.q.getCurrentPositionInMillis()) / Math.min(((float) f.this.r) * 1000.0f, (float) f.this.q.getDuration());
                f.this.setProgress(100.0f * currentPositionInMillis);
                if (currentPositionInMillis >= 1.0f) {
                    f.this.a(true);
                    f.this.q.getEventBus().b((T[]) new com.facebook.ads.internal.j.f[]{f.this.a, f.this.b});
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final c b = new c() {
        public void a(com.facebook.ads.internal.view.f.b.b bVar) {
            if (f.this.q != null && f.this.r != 0 && f.this.m.isShown() && !f.this.t) {
                f.this.a(true);
                f.this.q.getEventBus().b((T[]) new com.facebook.ads.internal.j.f[]{f.this.a, f.this.b});
            }
        }
    };
    private final ImageView j;
    private final FrameLayout k;
    private final ImageView l;
    /* access modifiers changed from: private */
    public final CircularProgressView m;
    private final com.facebook.ads.internal.view.c.c n;
    /* access modifiers changed from: private */
    public final PopupMenu o;
    /* access modifiers changed from: private */
    @Nullable
    public a p;
    /* access modifiers changed from: private */
    @Nullable
    public a q;
    /* access modifiers changed from: private */
    public int r = 0;
    /* access modifiers changed from: private */
    public boolean s = false;
    /* access modifiers changed from: private */
    public boolean t = false;
    private PopupMenu.OnDismissListener u;

    public interface a {
        void a();
    }

    public f(Context context) {
        super(context);
        setGravity(16);
        if (Build.VERSION.SDK_INT >= 14) {
            this.u = new PopupMenu.OnDismissListener() {
                public void onDismiss(PopupMenu popupMenu) {
                    boolean unused = f.this.s = false;
                }
            };
        }
        this.l = new ImageView(context);
        this.l.setPadding(f, f, f, f);
        this.l.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.l.setImageBitmap(com.facebook.ads.internal.q.b.c.a(com.facebook.ads.internal.q.b.b.INTERSTITIAL_CLOSE));
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (f.this.p != null && f.this.t) {
                    f.this.p.a();
                }
            }
        });
        this.m = new CircularProgressView(context);
        this.m.setPadding(f, f, f, f);
        this.m.setProgress(0.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(h, h, i, h);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(e, e);
        this.k = new FrameLayout(context);
        this.k.setLayoutTransition(new LayoutTransition());
        this.k.addView(this.l, layoutParams2);
        this.k.addView(this.m, layoutParams2);
        addView(this.k, layoutParams);
        this.n = new com.facebook.ads.internal.view.c.c(context);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2);
        layoutParams3.gravity = 17;
        layoutParams3.weight = 1.0f;
        addView(this.n, layoutParams3);
        this.j = new ImageView(context);
        this.j.setPadding(f, f, f, f);
        this.j.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.j.setImageBitmap(com.facebook.ads.internal.q.b.c.a(com.facebook.ads.internal.q.b.b.INTERSTITIAL_AD_CHOICES));
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                f.this.o.show();
                boolean unused = f.this.s = true;
            }
        });
        this.o = new PopupMenu(context, this.j);
        this.o.getMenu().add("Ad Choices");
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(d, d);
        layoutParams4.setMargins(0, g / 2, g / 2, g / 2);
        addView(this.j, layoutParams4);
    }

    public void a(d dVar, boolean z) {
        int a2 = dVar.a(z);
        this.n.a(dVar.g(z), a2);
        this.j.setColorFilter(a2);
        this.l.setColorFilter(a2);
        this.m.a(ColorUtils.setAlphaComponent(a2, 77), a2);
        if (z) {
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-1778384896, 0});
            gradientDrawable.setCornerRadius(0.0f);
            x.a((View) this, (Drawable) gradientDrawable);
            return;
        }
        x.a((View) this, 0);
    }

    public void a(final i iVar, final String str, int i2) {
        this.r = i2;
        this.n.setPageDetails(iVar);
        this.o.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                boolean unused = f.this.s = false;
                if (TextUtils.isEmpty(iVar.c())) {
                    return true;
                }
                g.a(new g(), f.this.getContext(), Uri.parse(iVar.c()), str);
                return true;
            }
        });
        if (Build.VERSION.SDK_INT >= 14) {
            this.o.setOnDismissListener(this.u);
        }
        a(i2 <= 0);
    }

    public void a(a aVar) {
        this.q = aVar;
        this.q.getEventBus().a((T[]) new com.facebook.ads.internal.j.f[]{this.a, this.b});
    }

    public void a(boolean z) {
        this.t = z;
        int i2 = 0;
        this.k.setVisibility(0);
        this.m.setVisibility(z ? 4 : 0);
        ImageView imageView = this.l;
        if (!z) {
            i2 = 4;
        }
        imageView.setVisibility(i2);
    }

    public boolean a() {
        return this.t;
    }

    public void b() {
        this.t = false;
        this.k.setVisibility(4);
        this.m.setVisibility(4);
        this.l.setVisibility(4);
    }

    public void b(a aVar) {
        if (this.q != null) {
            this.q.getEventBus().b((T[]) new com.facebook.ads.internal.j.f[]{this.a, this.b});
            this.q = null;
        }
    }

    public void c() {
        this.n.setVisibility(4);
    }

    public void d() {
        if (Build.VERSION.SDK_INT >= 14) {
            this.o.setOnDismissListener((PopupMenu.OnDismissListener) null);
        }
        this.o.dismiss();
        if (Build.VERSION.SDK_INT >= 14) {
            this.o.setOnDismissListener(this.u);
        }
    }

    public void e() {
        if (this.s && Build.VERSION.SDK_INT >= 14) {
            this.o.show();
        }
    }

    public void setProgress(float f2) {
        this.m.setProgressWithAnimation(f2);
    }

    public void setShowPageDetails(boolean z) {
        this.n.setVisibility(z ? 0 : 4);
    }

    public void setToolbarListener(a aVar) {
        this.p = aVar;
    }
}
