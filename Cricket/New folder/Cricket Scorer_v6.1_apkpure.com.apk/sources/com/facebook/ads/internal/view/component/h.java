package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.q.a.x;

public class h extends LinearLayout {
    private static final float a = Resources.getSystem().getDisplayMetrics().density;
    private static final int b = ((int) (6.0f * a));
    private static final int c = ((int) (8.0f * a));
    private final TextView d;
    private final TextView e;

    public h(Context context, d dVar, boolean z, boolean z2, boolean z3) {
        super(context);
        setOrientation(1);
        this.d = new TextView(context);
        x.a(this.d, true, z2 ? 18 : 22);
        this.d.setTextColor(dVar.c(z));
        this.d.setEllipsize(TextUtils.TruncateAt.END);
        this.d.setLineSpacing((float) b, 1.0f);
        this.e = new TextView(context);
        x.a(this.e, false, z2 ? 14 : 16);
        this.e.setTextColor(dVar.b(z));
        this.e.setEllipsize(TextUtils.TruncateAt.END);
        this.e.setLineSpacing((float) b, 1.0f);
        addView(this.d, new LinearLayout.LayoutParams(-1, -2));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, z3 ? c / 2 : c, 0, 0);
        addView(this.e, layoutParams);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        if (r8 != false) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5, java.lang.String r6, boolean r7, boolean r8) {
        /*
            r4 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            r1 = 1
            r0 = r0 ^ r1
            boolean r2 = android.text.TextUtils.isEmpty(r6)
            r2 = r2 ^ r1
            android.widget.TextView r3 = r4.d
            if (r0 == 0) goto L_0x0010
            goto L_0x0011
        L_0x0010:
            r5 = r6
        L_0x0011:
            r3.setText(r5)
            android.widget.TextView r5 = r4.e
            if (r0 == 0) goto L_0x0019
            goto L_0x001b
        L_0x0019:
            java.lang.String r6 = ""
        L_0x001b:
            r5.setText(r6)
            r5 = 3
            r6 = 2
            if (r0 == 0) goto L_0x0038
            if (r2 != 0) goto L_0x0025
            goto L_0x0038
        L_0x0025:
            android.widget.TextView r0 = r4.d
            if (r7 == 0) goto L_0x002b
            r2 = r1
            goto L_0x002c
        L_0x002b:
            r2 = r6
        L_0x002c:
            r0.setMaxLines(r2)
            android.widget.TextView r0 = r4.e
            if (r7 == 0) goto L_0x0035
            r5 = r1
            goto L_0x0041
        L_0x0035:
            if (r8 == 0) goto L_0x003c
            goto L_0x0041
        L_0x0038:
            android.widget.TextView r0 = r4.d
            if (r7 == 0) goto L_0x003e
        L_0x003c:
            r5 = r6
            goto L_0x0041
        L_0x003e:
            if (r8 == 0) goto L_0x0041
            r5 = 4
        L_0x0041:
            r0.setMaxLines(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.component.h.a(java.lang.String, java.lang.String, boolean, boolean):void");
    }

    public void setAlignment(int i) {
        this.d.setGravity(i);
        this.e.setGravity(i);
    }
}
