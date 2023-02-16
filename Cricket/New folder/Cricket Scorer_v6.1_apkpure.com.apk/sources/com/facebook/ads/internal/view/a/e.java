package com.facebook.ads.internal.view.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.q.b.b;
import com.facebook.ads.internal.q.b.c;

@TargetApi(19)
public class e extends LinearLayout {
    private TextView a;
    private TextView b;
    private Drawable c;

    public e(Context context) {
        super(context);
        a();
    }

    private void a() {
        float f = getResources().getDisplayMetrics().density;
        setOrientation(1);
        this.a = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.a.setTextSize(2, 20.0f);
        this.a.setEllipsize(TextUtils.TruncateAt.END);
        this.a.setSingleLine(true);
        this.a.setVisibility(8);
        addView(this.a, layoutParams);
        this.b = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        this.b.setAlpha(0.5f);
        this.b.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.b.setTextSize(2, 15.0f);
        this.b.setCompoundDrawablePadding((int) (5.0f * f));
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        this.b.setSingleLine(true);
        this.b.setVisibility(8);
        addView(this.b, layoutParams2);
    }

    private Drawable getPadlockDrawable() {
        if (this.c == null) {
            this.c = c.a(getContext(), b.BROWSER_PADLOCK);
        }
        return this.c;
    }

    public void setSubtitle(String str) {
        TextView textView;
        int i;
        if (TextUtils.isEmpty(str)) {
            this.b.setText((CharSequence) null);
            textView = this.b;
            i = 8;
        } else {
            Uri parse = Uri.parse(str);
            this.b.setText(parse.getHost());
            this.b.setCompoundDrawablesRelativeWithIntrinsicBounds("https".equals(parse.getScheme()) ? getPadlockDrawable() : null, (Drawable) null, (Drawable) null, (Drawable) null);
            textView = this.b;
            i = 0;
        }
        textView.setVisibility(i);
    }

    public void setTitle(String str) {
        TextView textView;
        int i;
        if (TextUtils.isEmpty(str)) {
            this.a.setText((CharSequence) null);
            textView = this.a;
            i = 8;
        } else {
            this.a.setText(str);
            textView = this.a;
            i = 0;
        }
        textView.setVisibility(i);
    }
}
