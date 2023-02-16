package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.d.c;
import java.lang.ref.WeakReference;

public class d extends RelativeLayout {
    private final c a;
    @Nullable
    private g b;
    private WeakReference<a> c;

    public interface a {
        void a();
    }

    public d(Context context, c cVar) {
        super(context);
        this.a = cVar;
        x.b((View) this.a);
        addView(this.a.getView(), new RelativeLayout.LayoutParams(-1, -1));
    }

    public void a(com.facebook.ads.internal.view.f.a.c cVar) {
        addView(cVar, new RelativeLayout.LayoutParams(-1, -1));
        this.b = (g) cVar;
    }

    public void b(com.facebook.ads.internal.view.f.a.c cVar) {
        x.b(cVar);
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ((View) this.a).layout(0, 0, getWidth(), getHeight());
        if (this.b != null) {
            this.b.layout(0, 0, getWidth(), getHeight());
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        if (r3 > r10) goto L_0x006f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r9, int r10) {
        /*
            r8 = this;
            com.facebook.ads.internal.view.f.d.c r0 = r8.a
            int r0 = r0.getVideoWidth()
            com.facebook.ads.internal.view.f.d.c r1 = r8.a
            int r1 = r1.getVideoHeight()
            int r2 = getDefaultSize(r0, r9)
            int r3 = getDefaultSize(r1, r10)
            if (r0 <= 0) goto L_0x006a
            if (r1 <= 0) goto L_0x006a
            r2 = 1
            int r3 = android.view.View.MeasureSpec.getMode(r9)
            int r9 = android.view.View.MeasureSpec.getSize(r9)
            int r4 = android.view.View.MeasureSpec.getMode(r10)
            int r10 = android.view.View.MeasureSpec.getSize(r10)
            r5 = 1073741824(0x40000000, float:2.0)
            if (r3 != r5) goto L_0x003d
            if (r4 != r5) goto L_0x003d
            int r3 = r0 * r10
            int r4 = r9 * r1
            if (r3 >= r4) goto L_0x0038
            int r9 = r3 / r1
            goto L_0x006f
        L_0x0038:
            if (r3 <= r4) goto L_0x006f
            int r3 = r4 / r0
            goto L_0x0049
        L_0x003d:
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 != r5) goto L_0x004b
            int r1 = r1 * r9
            int r3 = r1 / r0
            if (r4 != r6) goto L_0x0049
            if (r3 <= r10) goto L_0x0049
            goto L_0x006f
        L_0x0049:
            r10 = r3
            goto L_0x006f
        L_0x004b:
            if (r4 != r5) goto L_0x0056
            int r0 = r0 * r10
            int r0 = r0 / r1
            if (r3 != r6) goto L_0x0054
            if (r0 <= r9) goto L_0x0054
            goto L_0x006f
        L_0x0054:
            r9 = r0
            goto L_0x006f
        L_0x0056:
            if (r4 != r6) goto L_0x005e
            if (r1 <= r10) goto L_0x005e
            int r4 = r10 * r0
            int r4 = r4 / r1
            goto L_0x0060
        L_0x005e:
            r4 = r0
            r10 = r1
        L_0x0060:
            if (r3 != r6) goto L_0x0068
            if (r4 <= r9) goto L_0x0068
            int r1 = r1 * r9
            int r3 = r1 / r0
            goto L_0x0049
        L_0x0068:
            r9 = r4
            goto L_0x006f
        L_0x006a:
            r9 = 0
            r10 = r3
            r7 = r2
            r2 = r9
            r9 = r7
        L_0x006f:
            r8.setMeasuredDimension(r9, r10)
            if (r2 == 0) goto L_0x008b
            java.lang.ref.WeakReference<com.facebook.ads.internal.view.f.d$a> r9 = r8.c
            if (r9 == 0) goto L_0x008b
            java.lang.ref.WeakReference<com.facebook.ads.internal.view.f.d$a> r9 = r8.c
            java.lang.Object r9 = r9.get()
            if (r9 == 0) goto L_0x008b
            java.lang.ref.WeakReference<com.facebook.ads.internal.view.f.d$a> r9 = r8.c
            java.lang.Object r9 = r9.get()
            com.facebook.ads.internal.view.f.d$a r9 = (com.facebook.ads.internal.view.f.d.a) r9
            r9.a()
        L_0x008b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.f.d.onMeasure(int, int):void");
    }

    public void setViewImplInflationListener(a aVar) {
        this.c = new WeakReference<>(aVar);
    }
}
