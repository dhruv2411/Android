package com.facebook.ads.internal.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.a.c;
import com.facebook.ads.internal.adapters.k;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.t;
import com.facebook.ads.internal.view.f.c.a;
import com.facebook.ads.internal.view.f.c.d;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.c.j;
import com.facebook.ads.internal.view.f.c.n;
import com.google.android.exoplayer2.util.MimeTypes;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import org.json.JSONObject;

public class l extends j implements View.OnTouchListener, a {
    static final /* synthetic */ boolean i = true;
    private static final String j = "l";
    private int A = -12286980;
    private boolean B = false;
    @Nullable
    private com.facebook.ads.internal.view.f.a.a C;
    final int f = 64;
    final int g = 64;
    final int h = 16;
    @Nullable
    private a.C0008a k;
    /* access modifiers changed from: private */
    @Nullable
    public Activity l;
    private AudienceNetworkActivity.BackButtonInterceptor m = new AudienceNetworkActivity.BackButtonInterceptor() {
        public boolean interceptBackButton() {
            if (l.this.x == null) {
                return false;
            }
            if (!l.this.x.a()) {
                return true;
            }
            if (!(l.this.x.getSkipSeconds() == 0 || l.this.b == null)) {
                l.this.b.e();
            }
            if (l.this.b != null) {
                l.this.b.f();
            }
            return false;
        }
    };
    private final View.OnTouchListener n = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 1) {
                return true;
            }
            if (l.this.x != null) {
                if (!l.this.x.a()) {
                    return true;
                }
                if (!(l.this.x.getSkipSeconds() == 0 || l.this.b == null)) {
                    l.this.b.e();
                }
                if (l.this.b != null) {
                    l.this.b.f();
                }
            }
            l.this.l.finish();
            return true;
        }
    };
    private k.a o = k.a.UNSPECIFIED;
    private com.facebook.ads.internal.view.c.a p;
    private TextView q;
    private TextView r;
    private ImageView s;
    @Nullable
    private a.C0012a t;
    private n u;
    private ViewGroup v;
    private d w;
    /* access modifiers changed from: private */
    public j x;
    private int y = -1;
    private int z = -10525069;

    private void a(int i2) {
        View view;
        int i3;
        View view2;
        int i4 = i2;
        float f2 = x.b;
        int i5 = (int) (56.0f * f2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i5, i5);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        int i6 = (int) (16.0f * f2);
        this.x.setPadding(i6, i6, i6, i6);
        this.x.setLayoutParams(layoutParams);
        d.a aVar = h() ? d.a.FADE_OUT_ON_PLAY : d.a.VISIBLE;
        int id = this.b.getId();
        if (i4 != 1 || (!m() && !n())) {
            if (i4 == 1) {
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams2.addRule(10);
                this.b.setLayoutParams(layoutParams2);
                a((View) this.b);
                a((View) this.x);
                if (this.t != null) {
                    a((View) this.t);
                }
                LinearLayout linearLayout = new LinearLayout(this.d);
                this.v = linearLayout;
                linearLayout.setGravity(112);
                linearLayout.setOrientation(1);
                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
                int i7 = (int) (33.0f * f2);
                layoutParams3.leftMargin = i7;
                layoutParams3.rightMargin = i7;
                layoutParams3.topMargin = (int) (8.0f * f2);
                if (this.p == null) {
                    layoutParams3.bottomMargin = i6;
                } else {
                    layoutParams3.bottomMargin = (int) (80.0f * f2);
                }
                layoutParams3.addRule(3, id);
                linearLayout.setLayoutParams(layoutParams3);
                a((View) linearLayout);
                if (this.p != null) {
                    RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f2));
                    layoutParams4.bottomMargin = i6;
                    layoutParams4.leftMargin = i7;
                    layoutParams4.rightMargin = i7;
                    layoutParams4.addRule(1);
                    layoutParams4.addRule(12);
                    this.p.setLayoutParams(layoutParams4);
                    a((View) this.p);
                }
                if (this.q != null) {
                    LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams5.weight = 2.0f;
                    layoutParams5.gravity = 17;
                    this.q.setEllipsize(TextUtils.TruncateAt.END);
                    this.q.setGravity(17);
                    this.q.setLayoutParams(layoutParams5);
                    this.q.setMaxLines(2);
                    this.q.setPadding(0, 0, 0, 0);
                    this.q.setTextColor(this.z);
                    this.q.setTextSize(24.0f);
                    a(linearLayout, this.q);
                }
                if (this.s != null) {
                    int i8 = (int) (64.0f * f2);
                    LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(i8, i8);
                    layoutParams6.weight = 0.0f;
                    layoutParams6.gravity = 17;
                    this.s.setLayoutParams(layoutParams6);
                    a(linearLayout, this.s);
                }
                if (this.r != null) {
                    LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams7.weight = 2.0f;
                    layoutParams7.gravity = 16;
                    this.r.setEllipsize(TextUtils.TruncateAt.END);
                    this.r.setGravity(16);
                    this.r.setLayoutParams(layoutParams7);
                    this.r.setMaxLines(2);
                    this.r.setPadding(0, 0, 0, 0);
                    this.r.setTextColor(this.z);
                    a(linearLayout, this.r);
                }
                if (this.u != null) {
                    RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f2));
                    layoutParams8.addRule(3, id);
                    this.u.setLayoutParams(layoutParams8);
                    view2 = this.u;
                }
                view = (View) this.b.getParent();
                i3 = this.y;
            } else if (!o() || n()) {
                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, -15658735});
                gradientDrawable.setCornerRadius(0.0f);
                this.b.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                a((View) this.b);
                a((View) this.x);
                if (this.t != null) {
                    a((View) this.t);
                }
                RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-1, (int) (124.0f * f2));
                layoutParams9.addRule(12);
                RelativeLayout relativeLayout = new RelativeLayout(this.d);
                x.a((View) relativeLayout, (Drawable) gradientDrawable);
                relativeLayout.setLayoutParams(layoutParams9);
                relativeLayout.setPadding(i6, 0, i6, i6);
                this.v = relativeLayout;
                if (!this.B) {
                    this.w.a((View) relativeLayout, aVar);
                }
                a((View) relativeLayout);
                if (this.p != null) {
                    RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams((int) (110.0f * f2), i5);
                    layoutParams10.rightMargin = i6;
                    layoutParams10.bottomMargin = i6;
                    layoutParams10.addRule(12);
                    layoutParams10.addRule(11);
                    this.p.setLayoutParams(layoutParams10);
                    a((View) this.p);
                }
                if (this.s != null) {
                    int i9 = (int) (64.0f * f2);
                    RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(i9, i9);
                    layoutParams11.addRule(12);
                    layoutParams11.addRule(9);
                    layoutParams11.bottomMargin = (int) (8.0f * f2);
                    this.s.setLayoutParams(layoutParams11);
                    a(relativeLayout, this.s);
                }
                if (this.q != null) {
                    RelativeLayout.LayoutParams layoutParams12 = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams12.bottomMargin = (int) (48.0f * f2);
                    layoutParams12.addRule(12);
                    layoutParams12.addRule(9);
                    this.q.setEllipsize(TextUtils.TruncateAt.END);
                    this.q.setGravity(GravityCompat.START);
                    this.q.setLayoutParams(layoutParams12);
                    this.q.setMaxLines(1);
                    this.q.setPadding((int) (80.0f * f2), 0, this.p != null ? (int) (126.0f * f2) : 0, 0);
                    this.q.setTextColor(-1);
                    this.q.setTextSize(24.0f);
                    a(relativeLayout, this.q);
                }
                if (this.r != null) {
                    RelativeLayout.LayoutParams layoutParams13 = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams13.addRule(12);
                    layoutParams13.addRule(9);
                    this.r.setEllipsize(TextUtils.TruncateAt.END);
                    this.r.setGravity(GravityCompat.START);
                    this.r.setLayoutParams(layoutParams13);
                    this.r.setMaxLines(2);
                    this.r.setTextColor(-1);
                    this.r.setPadding((int) (80.0f * f2), 0, this.p != null ? (int) (126.0f * f2) : 0, 0);
                    a(relativeLayout, this.r);
                }
                if (this.u != null) {
                    RelativeLayout.LayoutParams layoutParams14 = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f2));
                    layoutParams14.addRule(12);
                    this.u.setLayoutParams(layoutParams14);
                    a((View) this.u);
                }
                view = (View) this.b.getParent();
                i3 = ViewCompat.MEASURED_STATE_MASK;
            } else {
                RelativeLayout.LayoutParams layoutParams15 = new RelativeLayout.LayoutParams(-2, -1);
                layoutParams15.addRule(9);
                this.b.setLayoutParams(layoutParams15);
                a((View) this.b);
                a((View) this.x);
                if (this.t != null) {
                    a((View) this.t);
                }
                LinearLayout linearLayout2 = new LinearLayout(this.d);
                this.v = linearLayout2;
                linearLayout2.setGravity(112);
                linearLayout2.setOrientation(1);
                RelativeLayout.LayoutParams layoutParams16 = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams16.leftMargin = i6;
                layoutParams16.rightMargin = i6;
                layoutParams16.topMargin = (int) (8.0f * f2);
                layoutParams16.bottomMargin = (int) (80.0f * f2);
                layoutParams16.addRule(1, id);
                linearLayout2.setLayoutParams(layoutParams16);
                a((View) linearLayout2);
                if (this.u != null) {
                    RelativeLayout.LayoutParams layoutParams17 = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f2));
                    layoutParams17.addRule(5, id);
                    layoutParams17.addRule(7, id);
                    layoutParams17.addRule(3, id);
                    layoutParams17.topMargin = (int) (-6.0f * f2);
                    this.u.setLayoutParams(layoutParams17);
                    a((View) this.u);
                }
                if (this.q != null) {
                    LinearLayout.LayoutParams layoutParams18 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams18.weight = 2.0f;
                    layoutParams18.gravity = 17;
                    this.q.setEllipsize(TextUtils.TruncateAt.END);
                    this.q.setGravity(17);
                    this.q.setLayoutParams(layoutParams18);
                    this.q.setMaxLines(10);
                    this.q.setPadding(0, 0, 0, 0);
                    this.q.setTextColor(this.z);
                    this.q.setTextSize(24.0f);
                    a(linearLayout2, this.q);
                }
                if (this.s != null) {
                    int i10 = (int) (64.0f * f2);
                    LinearLayout.LayoutParams layoutParams19 = new LinearLayout.LayoutParams(i10, i10);
                    layoutParams19.weight = 0.0f;
                    layoutParams19.gravity = 17;
                    this.s.setLayoutParams(layoutParams19);
                    a(linearLayout2, this.s);
                }
                if (this.r != null) {
                    LinearLayout.LayoutParams layoutParams20 = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams20.weight = 2.0f;
                    layoutParams20.gravity = 16;
                    this.r.setEllipsize(TextUtils.TruncateAt.END);
                    this.r.setGravity(17);
                    this.r.setLayoutParams(layoutParams20);
                    this.r.setMaxLines(10);
                    this.r.setPadding(0, 0, 0, 0);
                    this.r.setTextColor(this.z);
                    a(linearLayout2, this.r);
                }
                if (this.p != null) {
                    RelativeLayout.LayoutParams layoutParams21 = new RelativeLayout.LayoutParams(-1, (int) (f2 * 64.0f));
                    layoutParams21.bottomMargin = i6;
                    layoutParams21.leftMargin = i6;
                    layoutParams21.rightMargin = i6;
                    layoutParams21.addRule(1);
                    layoutParams21.addRule(12);
                    layoutParams21.addRule(1, id);
                    this.p.setLayoutParams(layoutParams21);
                    view2 = this.p;
                }
                view = (View) this.b.getParent();
                i3 = this.y;
            }
            a(view2);
            view = (View) this.b.getParent();
            i3 = this.y;
        } else {
            GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, -15658735});
            gradientDrawable2.setCornerRadius(0.0f);
            RelativeLayout.LayoutParams layoutParams22 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams22.addRule(10);
            this.b.setLayoutParams(layoutParams22);
            a((View) this.b);
            a((View) this.x);
            if (this.t != null) {
                a((View) this.t);
            }
            RelativeLayout.LayoutParams layoutParams23 = new RelativeLayout.LayoutParams(-1, (int) (((float) ((this.p != null ? 64 : 0) + 60 + 16 + 16 + 16)) * f2));
            layoutParams23.addRule(12);
            RelativeLayout relativeLayout2 = new RelativeLayout(this.d);
            x.a((View) relativeLayout2, (Drawable) gradientDrawable2);
            relativeLayout2.setLayoutParams(layoutParams23);
            relativeLayout2.setPadding(i6, 0, i6, (int) (((float) ((this.p != null ? 64 : 0) + 16 + 16)) * f2));
            this.v = relativeLayout2;
            if (!this.B) {
                this.w.a((View) relativeLayout2, aVar);
            }
            a((View) relativeLayout2);
            if (this.u != null) {
                RelativeLayout.LayoutParams layoutParams24 = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f2));
                layoutParams24.addRule(12);
                layoutParams24.topMargin = (int) (-6.0f * f2);
                this.u.setLayoutParams(layoutParams24);
                a((View) this.u);
            }
            if (this.p != null) {
                RelativeLayout.LayoutParams layoutParams25 = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f2));
                layoutParams25.bottomMargin = i6;
                layoutParams25.leftMargin = i6;
                layoutParams25.rightMargin = i6;
                layoutParams25.addRule(1);
                layoutParams25.addRule(12);
                this.p.setLayoutParams(layoutParams25);
                a((View) this.p);
            }
            if (this.s != null) {
                int i11 = (int) (60.0f * f2);
                RelativeLayout.LayoutParams layoutParams26 = new RelativeLayout.LayoutParams(i11, i11);
                layoutParams26.addRule(12);
                layoutParams26.addRule(9);
                this.s.setLayoutParams(layoutParams26);
                a(relativeLayout2, this.s);
            }
            if (this.q != null) {
                RelativeLayout.LayoutParams layoutParams27 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams27.bottomMargin = (int) (36.0f * f2);
                layoutParams27.addRule(12);
                layoutParams27.addRule(9);
                this.q.setEllipsize(TextUtils.TruncateAt.END);
                this.q.setGravity(GravityCompat.START);
                this.q.setLayoutParams(layoutParams27);
                this.q.setMaxLines(1);
                this.q.setPadding((int) (72.0f * f2), 0, 0, 0);
                this.q.setTextColor(-1);
                this.q.setTextSize(18.0f);
                a(relativeLayout2, this.q);
            }
            if (this.r != null) {
                RelativeLayout.LayoutParams layoutParams28 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams28.addRule(12);
                layoutParams28.addRule(9);
                layoutParams28.bottomMargin = (int) (4.0f * f2);
                this.r.setEllipsize(TextUtils.TruncateAt.END);
                this.r.setGravity(GravityCompat.START);
                this.r.setLayoutParams(layoutParams28);
                this.r.setMaxLines(1);
                this.r.setPadding((int) (72.0f * f2), 0, 0, 0);
                this.r.setTextColor(-1);
                a(relativeLayout2, this.r);
            }
            view = (View) this.b.getParent();
            i3 = ViewCompat.MEASURED_STATE_MASK;
        }
        x.a(view, i3);
        View rootView = this.b.getRootView();
        if (rootView != null) {
            rootView.setOnTouchListener(this);
        }
    }

    private void a(View view) {
        if (this.k != null) {
            this.k.a(view);
        }
    }

    private void a(@Nullable ViewGroup viewGroup, @Nullable View view) {
        if (viewGroup != null) {
            viewGroup.addView(view);
        }
    }

    private void b(View view) {
        ViewGroup viewGroup;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeView(view);
        }
    }

    private boolean m() {
        return ((double) (this.b.getVideoHeight() > 0 ? ((float) this.b.getVideoWidth()) / ((float) this.b.getVideoHeight()) : -1.0f)) <= 0.9d;
    }

    private boolean n() {
        if (this.b.getVideoHeight() <= 0) {
            return false;
        }
        Rect rect = new Rect();
        this.l.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if (rect.width() > rect.height()) {
            return ((float) (rect.width() - ((rect.height() * this.b.getVideoWidth()) / this.b.getVideoHeight()))) - (192.0f * x.b) < 0.0f;
        }
        return ((((float) (rect.height() - ((rect.width() * this.b.getVideoHeight()) / this.b.getVideoWidth()))) - (x.b * 64.0f)) - (64.0f * x.b)) - (40.0f * x.b) < 0.0f;
    }

    private boolean o() {
        double videoWidth = (double) (this.b.getVideoHeight() > 0 ? ((float) this.b.getVideoWidth()) / ((float) this.b.getVideoHeight()) : -1.0f);
        return videoWidth > 0.9d && videoWidth < 1.1d;
    }

    private void p() {
        b((View) this.b);
        b((View) this.p);
        b((View) this.q);
        b((View) this.r);
        b((View) this.s);
        b((View) this.u);
        b((View) this.v);
        b((View) this.x);
        if (this.t != null) {
            b((View) this.t);
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.c == null) {
            Log.e(j, "Unable to add UI without a valid ad response.");
            return;
        }
        String string = this.c.getString("ct");
        String optString = this.c.getJSONObject("context").optString("orientation");
        if (!optString.isEmpty()) {
            this.o = k.a.a(Integer.parseInt(optString));
        }
        if (this.c.has(TtmlNode.TAG_LAYOUT) && !this.c.isNull(TtmlNode.TAG_LAYOUT)) {
            JSONObject jSONObject = this.c.getJSONObject(TtmlNode.TAG_LAYOUT);
            this.y = (int) jSONObject.optLong("bgColor", (long) this.y);
            this.z = (int) jSONObject.optLong("textColor", (long) this.z);
            this.A = (int) jSONObject.optLong("accentColor", (long) this.A);
            this.B = jSONObject.optBoolean("persistentAdDetails", this.B);
        }
        JSONObject jSONObject2 = this.c.getJSONObject(MimeTypes.BASE_TYPE_TEXT);
        this.b.setId(Build.VERSION.SDK_INT >= 17 ? View.generateViewId() : x.a());
        int c = c();
        Context context = this.d;
        if (c < 0) {
            c = 0;
        }
        this.x = new j(context, c, this.A);
        this.x.setOnTouchListener(this.n);
        this.b.a((b) this.x);
        if (this.c.has("cta") && !this.c.isNull("cta")) {
            JSONObject jSONObject3 = this.c.getJSONObject("cta");
            this.p = new com.facebook.ads.internal.view.c.a(this.d, jSONObject3.getString("url"), jSONObject3.getString(MimeTypes.BASE_TYPE_TEXT), this.A, this.b, this.a, string);
            c.a(this.d, this.a, string, Uri.parse(jSONObject3.getString("url")), new HashMap());
        }
        if (this.c.has(SettingsJsonConstants.APP_ICON_KEY) && !this.c.isNull(SettingsJsonConstants.APP_ICON_KEY)) {
            JSONObject jSONObject4 = this.c.getJSONObject(SettingsJsonConstants.APP_ICON_KEY);
            this.s = new ImageView(this.d);
            new com.facebook.ads.internal.view.b.d(this.s).a((int) (x.b * 64.0f), (int) (64.0f * x.b)).a(jSONObject4.getString("url"));
        }
        if (this.c.has("image") && !this.c.isNull("image")) {
            JSONObject jSONObject5 = this.c.getJSONObject("image");
            g gVar = new g(this.d);
            this.b.a((b) gVar);
            gVar.setImage(jSONObject5.getString("url"));
        }
        String optString2 = jSONObject2.optString("title");
        if (!optString2.isEmpty()) {
            this.q = new TextView(this.d);
            this.q.setText(optString2);
            this.q.setTypeface(Typeface.defaultFromStyle(1));
        }
        String optString3 = jSONObject2.optString("subtitle");
        if (!optString3.isEmpty()) {
            this.r = new TextView(this.d);
            this.r.setText(optString3);
            this.r.setTextSize(16.0f);
        }
        this.u = new n(this.d);
        this.b.a((b) this.u);
        String d = d();
        if (!TextUtils.isEmpty(d)) {
            this.t = new a.C0012a(this.d, "AdChoices", d, new float[]{0.0f, 0.0f, 8.0f, 0.0f}, string);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            this.t.setLayoutParams(layoutParams);
        }
        this.b.a((b) new com.facebook.ads.internal.view.f.c.k(this.d));
        com.facebook.ads.internal.view.f.c.l lVar = new com.facebook.ads.internal.view.f.c.l(this.d);
        this.b.a((b) lVar);
        d.a aVar = h() ? d.a.FADE_OUT_ON_PLAY : d.a.VISIBLE;
        this.b.a((b) new d(lVar, aVar));
        this.w = new d(new RelativeLayout(this.d), aVar);
        this.b.a((b) this.w);
    }

    @TargetApi(17)
    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        this.l = audienceNetworkActivity;
        if (i || this.k != null) {
            audienceNetworkActivity.addBackButtonInterceptor(this.m);
            p();
            a(this.l.getResources().getConfiguration().orientation);
            if (h()) {
                e();
            } else {
                f();
            }
        } else {
            throw new AssertionError();
        }
    }

    public void a(Configuration configuration) {
        p();
        a(configuration.orientation);
    }

    public void a(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public boolean h() {
        if (i || this.c != null) {
            try {
                return this.c.getJSONObject(MimeTypes.BASE_TYPE_VIDEO).getBoolean(AudienceNetworkActivity.AUTOPLAY);
            } catch (Exception e) {
                Log.w(String.valueOf(l.class), "Invalid JSON", e);
                return true;
            }
        } else {
            throw new AssertionError();
        }
    }

    public void i() {
        if (this.b != null && this.b.getState() == com.facebook.ads.internal.view.f.d.d.STARTED) {
            this.C = this.b.getVideoStartReason();
            this.b.a(false);
        }
    }

    public void j() {
        if (this.b != null && this.C != null) {
            this.b.a(this.C);
        }
    }

    public k.a k() {
        return this.o;
    }

    public void l() {
        if (this.l != null) {
            this.l.finish();
        }
    }

    public void onDestroy() {
        if (!(this.c == null || this.a == null)) {
            String optString = this.c.optString("ct");
            if (!TextUtils.isEmpty(optString)) {
                this.a.i(optString, new HashMap());
            }
        }
        if (this.b != null) {
            this.b.f();
        }
        k.a((com.facebook.ads.internal.view.a) this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.b == null) {
            return true;
        }
        this.b.getEventBus().a(new t(view, motionEvent));
        return true;
    }

    public void setListener(a.C0008a aVar) {
        this.k = aVar;
    }
}
