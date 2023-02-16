package com.facebook.ads.internal.r;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.facebook.ads.internal.q.a.t;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.q.a.y;
import com.facebook.ads.internal.q.a.z;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import org.json.JSONObject;

public class a {
    private static final String a = "a";
    /* access modifiers changed from: private */
    public final View b;
    /* access modifiers changed from: private */
    public final int c;
    /* access modifiers changed from: private */
    public final int d;
    /* access modifiers changed from: private */
    public final WeakReference<C0006a> e;
    /* access modifiers changed from: private */
    public final Handler f;
    /* access modifiers changed from: private */
    public final boolean g;
    /* access modifiers changed from: private */
    public Runnable h;
    private int i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public b l;
    /* access modifiers changed from: private */
    public Map<String, Integer> m;
    /* access modifiers changed from: private */
    public long n;
    /* access modifiers changed from: private */
    public int o;

    /* renamed from: com.facebook.ads.internal.r.a$a  reason: collision with other inner class name */
    public static abstract class C0006a {
        public abstract void a();

        public void b() {
        }
    }

    private static final class b extends y<a> {
        b(a aVar) {
            super(aVar);
        }

        public void run() {
            a aVar = (a) a();
            if (aVar != null) {
                View a = aVar.b;
                C0006a aVar2 = (C0006a) aVar.e.get();
                if (a != null && aVar2 != null) {
                    b a2 = a.a(a, aVar.c);
                    int i = 0;
                    if (a2.a()) {
                        a.d(aVar);
                    } else {
                        int unused = aVar.o = 0;
                    }
                    boolean z = aVar.o > aVar.d;
                    boolean z2 = aVar.l != null && aVar.l.a();
                    if (z || !a2.a()) {
                        b unused2 = aVar.l = a2;
                    }
                    String valueOf = String.valueOf(a2.b());
                    synchronized (aVar) {
                        if (aVar.m.containsKey(valueOf)) {
                            i = ((Integer) aVar.m.get(valueOf)).intValue();
                        }
                        aVar.m.put(valueOf, Integer.valueOf(i + 1));
                    }
                    if (z && !z2) {
                        long unused3 = aVar.n = System.currentTimeMillis();
                        aVar2.a();
                        if (!aVar.g) {
                            return;
                        }
                    } else if (!z && z2) {
                        aVar2.b();
                    }
                    if (!aVar.k && aVar.h != null) {
                        aVar.f.postDelayed(aVar.h, (long) aVar.j);
                    }
                }
            }
        }
    }

    public a(View view, int i2, int i3, boolean z, C0006a aVar) {
        this.f = new Handler();
        this.i = 0;
        this.j = 1000;
        this.k = true;
        this.l = new b(c.UNKNOWN);
        this.m = new HashMap();
        this.n = 0;
        this.o = 0;
        this.b = view;
        if (this.b.getId() == -1) {
            x.a(this.b);
        }
        this.c = i2;
        this.e = new WeakReference<>(aVar);
        this.g = z;
        this.d = i3 < 0 ? 0 : i3;
    }

    public a(View view, int i2, C0006a aVar) {
        this(view, i2, 0, false, aVar);
    }

    public a(View view, int i2, boolean z, C0006a aVar) {
        this(view, i2, 0, z, aVar);
    }

    @VisibleForTesting
    static float a(View view) {
        float alpha = view.getAlpha();
        while (view.getParent() instanceof ViewGroup) {
            view = (View) view.getParent();
            float alpha2 = view.getAlpha();
            if (alpha2 < 0.0f) {
                alpha2 = 0.0f;
            }
            if (alpha2 > 1.0f) {
                alpha2 = 1.0f;
            }
            alpha *= alpha2;
        }
        return alpha;
    }

    @VisibleForTesting
    public static int a(int i2, View view) {
        int width = view.getWidth() * view.getHeight();
        float f2 = 100.0f;
        if (width > 0) {
            f2 = 100.0f / ((float) width);
        }
        return (int) Math.max((double) i2, Math.ceil((double) f2));
    }

    private static int a(Vector<Rect> vector) {
        int size = vector.size();
        int i2 = size * 2;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        boolean[][] zArr = (boolean[][]) Array.newInstance(boolean.class, new int[]{i2, i2});
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < size) {
            Rect elementAt = vector.elementAt(i3);
            int i6 = i4 + 1;
            iArr[i4] = elementAt.left;
            int i7 = i5 + 1;
            iArr2[i5] = elementAt.bottom;
            int i8 = i6 + 1;
            iArr[i6] = elementAt.right;
            int i9 = i7 + 1;
            iArr2[i7] = elementAt.top;
            i3++;
            i4 = i8;
            i5 = i9;
        }
        Arrays.sort(iArr);
        Arrays.sort(iArr2);
        for (int i10 = 0; i10 < size; i10++) {
            Rect elementAt2 = vector.elementAt(i10);
            int a2 = a(iArr, elementAt2.left);
            int a3 = a(iArr, elementAt2.right);
            int a4 = a(iArr2, elementAt2.top);
            int a5 = a(iArr2, elementAt2.bottom);
            for (int i11 = a2 + 1; i11 <= a3; i11++) {
                for (int i12 = a4 + 1; i12 <= a5; i12++) {
                    zArr[i11][i12] = true;
                }
            }
        }
        int i13 = 0;
        int i14 = 0;
        while (i13 < i2) {
            int i15 = i14;
            for (int i16 = 0; i16 < i2; i16++) {
                i15 += zArr[i13][i16] ? (iArr[i13] - iArr[i13 - 1]) * (iArr2[i16] - iArr2[i16 - 1]) : 0;
            }
            i13++;
            i14 = i15;
        }
        return i14;
    }

    private static int a(int[] iArr, int i2) {
        int i3 = 0;
        int length = iArr.length;
        while (i3 < length) {
            int i4 = ((length - i3) / 2) + i3;
            if (iArr[i4] == i2) {
                return i4;
            }
            if (iArr[i4] > i2) {
                length = i4;
            } else {
                i3 = i4 + 1;
            }
        }
        return -1;
    }

    public static b a(View view, int i2) {
        DisplayMetrics displayMetrics;
        View view2 = view;
        boolean z = false;
        if (view2 == null) {
            a((View) null, false, "mAdView is null.");
            return new b(c.AD_IS_NULL);
        } else if (view.getParent() == null) {
            a(view2, false, "mAdView has no parent.");
            return new b(c.INVALID_PARENT);
        } else if (!view.isShown()) {
            a(view2, false, "mAdView parent is not set to VISIBLE.");
            return new b(c.INVALID_PARENT);
        } else if (view.getWindowVisibility() != 0) {
            a(view2, false, "mAdView window is not set to VISIBLE.");
            return new b(c.INVALID_WINDOW);
        } else if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            a(view2, false, "mAdView has invisible dimensions (w=" + view.getMeasuredWidth() + ", h=" + view.getMeasuredHeight());
            return new b(c.INVALID_DIMENSIONS);
        } else if (a(view) < 0.9f) {
            a(view2, false, "mAdView is too transparent.");
            return new b(c.AD_IS_TRANSPARENT);
        } else {
            int width = view.getWidth();
            int height = view.getHeight();
            int[] iArr = new int[2];
            try {
                view2.getLocationOnScreen(iArr);
                Rect rect = new Rect();
                if (!view2.getGlobalVisibleRect(rect)) {
                    return new b(c.AD_IS_NOT_VISIBLE);
                }
                Context context = view.getContext();
                if (Build.VERSION.SDK_INT >= 17) {
                    Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                    displayMetrics = new DisplayMetrics();
                    defaultDisplay.getRealMetrics(displayMetrics);
                } else {
                    displayMetrics = context.getResources().getDisplayMetrics();
                }
                Vector<Rect> b2 = b(view);
                int a2 = a(b2);
                b2.add(rect);
                float a3 = (((float) (a(b2) - a2)) * 1.0f) / ((float) (view.getMeasuredHeight() * view.getMeasuredWidth()));
                boolean t = com.facebook.ads.internal.l.a.t(context);
                int a4 = a(i2, view2);
                float f2 = ((float) a4) / 100.0f;
                if (t) {
                    if (a3 < f2) {
                        a(view2, false, String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{Float.valueOf(a3), Float.valueOf(f2)}));
                        return new b(c.AD_INSUFFICIENT_VISIBLE_AREA, a3);
                    }
                } else if (iArr[0] < 0 || displayMetrics.widthPixels - iArr[0] < width) {
                    a(view2, false, "mAdView is not fully on screen horizontally.");
                    return new b(c.AD_OFFSCREEN_HORIZONTALLY, a3);
                } else {
                    int i3 = (int) ((((double) height) * (100.0d - ((double) a4))) / 100.0d);
                    if (rect.top - iArr[1] > i3) {
                        a(view2, false, "mAdView is not visible from the top.");
                        return new b(c.AD_OFFSCREEN_TOP, a3);
                    }
                    z = false;
                    if ((iArr[1] + height) - rect.bottom > i3) {
                        a(view2, false, "mAdView is not visible from the bottom.");
                        return new b(c.AD_OFFSCREEN_BOTTOM, a3);
                    }
                }
                if (!com.facebook.ads.internal.q.e.a.b(context)) {
                    a(view2, z, "Screen is not interactive.");
                    return new b(c.SCREEN_NOT_INTERACTIVE, a3);
                }
                Map<String, String> a5 = com.facebook.ads.internal.q.e.b.a(context);
                if (z.b(a5)) {
                    a(view2, z, "Keyguard is obstructing view.");
                    return new b(c.AD_IS_OBSTRUCTED_BY_KEYGUARD, a3);
                } else if (!com.facebook.ads.internal.l.a.c(context) || !z.a(a5)) {
                    Float a6 = com.facebook.ads.internal.l.a.r(context) ? d.a(view) : null;
                    if (a6 != null) {
                        if (a6.floatValue() == -1.0f) {
                            a(view2, false, "mAdView is not in the top activity");
                            return new b(c.AD_IS_NOT_IN_ACTIVITY);
                        } else if (a6.floatValue() == 0.0f) {
                            a(view2, false, "mAdView is not visible");
                            return new b(c.AD_IS_NOT_VISIBLE);
                        }
                    }
                    if (!com.facebook.ads.internal.l.a.s(context) || a6 == null || a6.floatValue() >= f2) {
                        a(view2, true, "mAdView is visible.");
                        return new b(c.IS_VIEWABLE, a3, a5);
                    }
                    a(view2, false, String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{a6, Float.valueOf(f2)}));
                    return new b(c.AD_INSUFFICIENT_VISIBLE_AREA, a3, a5);
                } else {
                    a(view2, z, "Ad is on top of the Lockscreen.");
                    return new b(c.AD_IN_LOCKSCREEN, a3, a5);
                }
            } catch (NullPointerException unused) {
                a(view2, false, "Cannot get location on screen.");
                return new b(c.INVALID_DIMENSIONS);
            }
        }
    }

    private static void a(View view, boolean z, String str) {
    }

    private static Vector<Rect> b(View view) {
        Vector<Rect> vector = new Vector<>();
        if (!(view.getParent() instanceof ViewGroup)) {
            return vector;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int indexOfChild = viewGroup.indexOfChild(view);
        while (true) {
            indexOfChild++;
            if (indexOfChild < viewGroup.getChildCount()) {
                vector.addAll(c(viewGroup.getChildAt(indexOfChild)));
            } else {
                vector.addAll(b((View) viewGroup));
                return vector;
            }
        }
    }

    private static Vector<Rect> c(View view) {
        Vector<Rect> vector = new Vector<>();
        if (!view.isShown() || (Build.VERSION.SDK_INT >= 11 && view.getAlpha() <= 0.0f)) {
            return vector;
        }
        if (!(view instanceof ViewGroup) || !d(view)) {
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                vector.add(rect);
            }
            return vector;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            vector.addAll(c(viewGroup.getChildAt(i2)));
        }
        return vector;
    }

    static /* synthetic */ int d(a aVar) {
        int i2 = aVar.o;
        aVar.o = i2 + 1;
        return i2;
    }

    private static boolean d(View view) {
        if (view.getBackground() != null) {
            return Build.VERSION.SDK_INT >= 19 && view.getBackground().getAlpha() <= 0;
        }
        return true;
    }

    public synchronized void a() {
        if (this.h != null) {
            c();
        }
        this.h = new b(this);
        this.f.postDelayed(this.h, (long) this.i);
        this.k = false;
        this.o = 0;
        this.l = new b(c.UNKNOWN);
        this.m = new HashMap();
    }

    public void a(int i2) {
        this.i = i2;
    }

    public synchronized void a(Map<String, String> map) {
        map.put("vrc", String.valueOf(this.l.b()));
        map.put("vp", String.valueOf(this.l.c()));
        map.put("vh", new JSONObject(this.m).toString());
        map.put("vt", t.a(this.n));
        map.putAll(this.l.d());
    }

    public void b(int i2) {
        this.j = i2;
    }

    public synchronized boolean b() {
        return this.k;
    }

    public synchronized void c() {
        this.f.removeCallbacks(this.h);
        this.h = null;
        this.k = true;
        this.o = 0;
    }

    public synchronized String d() {
        c cVar;
        cVar = c.values()[this.l.b()];
        return cVar.toString() + String.format(" (%.1f%%)", new Object[]{Float.valueOf(this.l.c() * 100.0f)});
    }
}
