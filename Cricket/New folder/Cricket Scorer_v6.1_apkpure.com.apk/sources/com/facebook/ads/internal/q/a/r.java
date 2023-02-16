package com.facebook.ads.internal.q.a;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class r implements View.OnSystemUiVisibilityChangeListener {
    private final View a;
    private int b;
    @Nullable
    private Window c;
    private a d = a.DEFAULT;
    private final Runnable e = new Runnable() {
        public void run() {
            r.this.a(false);
        }
    };

    /* renamed from: com.facebook.ads.internal.q.a.r$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.FULL_SCREEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public enum a {
        DEFAULT,
        FULL_SCREEN
    }

    public r(View view) {
        this.a = view;
        this.a.setOnSystemUiVisibilityChangeListener(this);
    }

    private void a(int i, boolean z) {
        int i2;
        if (this.c != null) {
            WindowManager.LayoutParams attributes = this.c.getAttributes();
            if (z) {
                i2 = i | attributes.flags;
            } else {
                i2 = (i ^ -1) & attributes.flags;
            }
            attributes.flags = i2;
            this.c.setAttributes(attributes);
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (!a.DEFAULT.equals(this.d)) {
            int i = 3840;
            if (!z) {
                i = 3847;
            }
            Handler handler = this.a.getHandler();
            if (handler != null && z) {
                handler.removeCallbacks(this.e);
                handler.postDelayed(this.e, 2000);
            }
            this.a.setSystemUiVisibility(i);
        }
    }

    public void a() {
        this.c = null;
    }

    public void a(Window window) {
        this.c = window;
    }

    public void a(a aVar) {
        this.d = aVar;
        if (AnonymousClass2.a[this.d.ordinal()] != 1) {
            a((int) PagedChannelRandomAccessSource.DEFAULT_TOTAL_BUFSIZE, false);
            a(134217728, false);
            this.a.setSystemUiVisibility(0);
            return;
        }
        a((int) PagedChannelRandomAccessSource.DEFAULT_TOTAL_BUFSIZE, true);
        a(134217728, true);
        a(false);
    }

    public void onSystemUiVisibilityChange(int i) {
        this.b = i;
        if (((this.b ^ i) & 2) != 0 && (i & 2) == 0) {
            a(true);
        }
    }
}
