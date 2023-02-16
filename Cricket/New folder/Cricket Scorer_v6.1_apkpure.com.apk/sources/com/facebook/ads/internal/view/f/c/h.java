package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.d.d;

public class h extends c implements View.OnTouchListener {
    private final m a;
    private final i b;
    private final k c;
    private final com.facebook.ads.internal.view.f.b.c d;
    /* access modifiers changed from: private */
    public final m e;

    public h(Context context) {
        this(context, (AttributeSet) null);
    }

    public h(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public h(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new m() {
            public void a(l lVar) {
                h.this.setVisibility(0);
            }
        };
        this.b = new i() {
            public void a(com.facebook.ads.internal.view.f.b.h hVar) {
                h.this.e.setChecked(true);
            }
        };
        this.c = new k() {
            public void a(j jVar) {
                h.this.e.setChecked(false);
            }
        };
        this.d = new com.facebook.ads.internal.view.f.b.c() {
            public void a(b bVar) {
                h.this.e.setChecked(true);
            }
        };
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.e = new m(context);
        this.e.setChecked(true);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (displayMetrics.density * 25.0f), (int) (25.0f * displayMetrics.density));
        setVisibility(8);
        addView(this.e, layoutParams);
        setClickable(true);
        setFocusable(true);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.e.setOnTouchListener(this);
        setOnTouchListener(this);
        if (getVideoView() != null) {
            getVideoView().getEventBus().a((T[]) new f[]{this.a, this.d, this.b, this.c});
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b((T[]) new f[]{this.c, this.b, this.d, this.a});
        }
        setOnTouchListener((View.OnTouchListener) null);
        this.e.setOnTouchListener((View.OnTouchListener) null);
        super.b();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        a videoView;
        if (motionEvent.getAction() != 1 || (videoView = getVideoView()) == null) {
            return false;
        }
        if (videoView.getState() == d.PREPARED || videoView.getState() == d.PAUSED || videoView.getState() == d.PLAYBACK_COMPLETED) {
            videoView.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
            return true;
        }
        if (videoView.getState() == d.STARTED) {
            videoView.a(true);
        }
        return false;
    }
}
