package com.facebook.ads.internal.n;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.AdIconView;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.component.c;
import com.facebook.ads.internal.view.component.g;
import java.util.ArrayList;

public class a {
    private static final float a = x.b;
    private final k b;
    private final f c;
    private final RelativeLayout d;
    private ArrayList<View> e = new ArrayList<>();

    /* JADX WARNING: type inference failed for: r8v0, types: [com.facebook.ads.AdIconView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public a(android.content.Context r3, com.facebook.ads.internal.n.f r4, android.widget.RelativeLayout r5, android.widget.RelativeLayout r6, @android.support.annotation.Nullable com.facebook.ads.MediaView r7, com.facebook.ads.AdIconView r8, com.facebook.ads.internal.n.l r9, com.facebook.ads.internal.n.k r10) {
        /*
            r2 = this;
            r2.<init>()
            r2.d = r5
            android.widget.RelativeLayout r5 = r2.d
            int r0 = r10.b()
            com.facebook.ads.internal.q.a.x.a((android.view.View) r5, (int) r0)
            r2.b = r10
            r2.c = r4
            int r5 = r9.b()
            android.widget.RelativeLayout r10 = r2.d
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            float r5 = (float) r5
            float r1 = a
            float r5 = r5 * r1
            int r5 = java.lang.Math.round(r5)
            r1 = -1
            r0.<init>(r1, r5)
            r10.setLayoutParams(r0)
            com.facebook.ads.internal.view.r r5 = new com.facebook.ads.internal.view.r
            r5.<init>(r3)
            float r10 = a
            r0 = 1133248512(0x438c0000, float:280.0)
            float r0 = r0 * r10
            int r10 = java.lang.Math.round(r0)
            r5.setMinWidth(r10)
            float r10 = a
            r0 = 1136361472(0x43bb8000, float:375.0)
            float r0 = r0 * r10
            int r10 = java.lang.Math.round(r0)
            r5.setMaxWidth(r10)
            android.widget.RelativeLayout$LayoutParams r10 = new android.widget.RelativeLayout$LayoutParams
            r10.<init>(r1, r1)
            r0 = 13
            r10.addRule(r0, r1)
            r5.setLayoutParams(r10)
            android.widget.RelativeLayout r10 = r2.d
            r10.addView(r5)
            android.widget.LinearLayout r10 = new android.widget.LinearLayout
            r10.<init>(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r2.e = r3
            r3 = 1
            r10.setOrientation(r3)
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            r3.<init>(r1, r1)
            r10.setLayoutParams(r3)
            r5.addView(r10)
            int[] r3 = com.facebook.ads.internal.n.a.AnonymousClass1.a
            int r0 = r9.ordinal()
            r3 = r3[r0]
            switch(r3) {
                case 1: goto L_0x0080;
                case 2: goto L_0x0083;
                default: goto L_0x007f;
            }
        L_0x007f:
            goto L_0x0086
        L_0x0080:
            r2.a((android.view.ViewGroup) r10)
        L_0x0083:
            r2.a(r10, r7)
        L_0x0086:
            r2.a(r10, r9, r8)
            android.widget.RelativeLayout r3 = r2.d
            if (r7 == 0) goto L_0x008e
            goto L_0x008f
        L_0x008e:
            r7 = r8
        L_0x008f:
            java.util.ArrayList<android.view.View> r8 = r2.e
            r4.a((android.view.View) r3, (com.facebook.ads.internal.n.g) r7, (java.util.List<android.view.View>) r8)
            android.view.ViewGroup$LayoutParams r3 = r6.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r3 = (android.widget.RelativeLayout.LayoutParams) r3
            r4 = 11
            r3.addRule(r4)
            float r4 = a
            r7 = 1082130432(0x40800000, float:4.0)
            float r4 = r4 * r7
            int r4 = java.lang.Math.round(r4)
            float r8 = a
            float r8 = r8 * r7
            int r8 = java.lang.Math.round(r8)
            float r9 = a
            float r9 = r9 * r7
            int r9 = java.lang.Math.round(r9)
            float r10 = a
            float r7 = r7 * r10
            int r7 = java.lang.Math.round(r7)
            r3.setMargins(r4, r8, r9, r7)
            r5.addView(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.n.a.<init>(android.content.Context, com.facebook.ads.internal.n.f, android.widget.RelativeLayout, android.widget.RelativeLayout, com.facebook.ads.MediaView, com.facebook.ads.AdIconView, com.facebook.ads.internal.n.l, com.facebook.ads.internal.n.k):void");
    }

    private void a(ViewGroup viewGroup) {
        g gVar = new g(this.d.getContext(), this.c, this.b);
        gVar.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(110.0f * a)));
        viewGroup.addView(gVar);
    }

    private void a(ViewGroup viewGroup, RelativeLayout relativeLayout) {
        RelativeLayout relativeLayout2 = new RelativeLayout(this.d.getContext());
        relativeLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(a * 180.0f)));
        x.a((View) relativeLayout2, this.b.b());
        relativeLayout2.addView(relativeLayout);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (180.0f * a));
        layoutParams.addRule(13, -1);
        relativeLayout.setLayoutParams(layoutParams);
        viewGroup.addView(relativeLayout2);
        this.e.add(relativeLayout);
    }

    private void a(ViewGroup viewGroup, l lVar, AdIconView adIconView) {
        c cVar = new c(this.d.getContext(), this.c, this.b, adIconView, a(lVar), b(lVar));
        cVar.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(((float) b(lVar)) * a)));
        viewGroup.addView(cVar);
        this.e.add(cVar.getIconView());
        this.e.add(cVar.getCallToActionView());
    }

    private boolean a(l lVar) {
        return lVar == l.HEIGHT_300 || lVar == l.HEIGHT_120;
    }

    private int b(l lVar) {
        switch (lVar) {
            case HEIGHT_400:
                return (lVar.b() - 180) / 2;
            case HEIGHT_300:
                return lVar.b() - 180;
            case HEIGHT_100:
            case HEIGHT_120:
                return lVar.b();
            default:
                return 0;
        }
    }

    public void a() {
        this.c.J();
    }
}
