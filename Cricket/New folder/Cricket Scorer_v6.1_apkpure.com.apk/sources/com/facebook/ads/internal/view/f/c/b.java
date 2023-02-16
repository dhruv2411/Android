package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.k;
import com.google.android.exoplayer2.util.MimeTypes;
import java.lang.ref.WeakReference;

public class b extends c {
    /* access modifiers changed from: private */
    public WeakReference<AudioManager.OnAudioFocusChangeListener> a = null;
    private final com.facebook.ads.internal.view.f.b.c b = new com.facebook.ads.internal.view.f.b.c() {
        public void a(com.facebook.ads.internal.view.f.b.b bVar) {
            ((AudioManager) b.this.getContext().getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).abandonAudioFocus(b.this.a == null ? null : (AudioManager.OnAudioFocusChangeListener) b.this.a.get());
        }
    };
    private final i c = new i() {
        public void a(h hVar) {
            ((AudioManager) b.this.getContext().getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).abandonAudioFocus(b.this.a == null ? null : (AudioManager.OnAudioFocusChangeListener) b.this.a.get());
        }
    };
    private final k d = new k() {
        public void a(j jVar) {
            if (b.this.a == null || b.this.a.get() == null) {
                WeakReference unused = b.this.a = new WeakReference(new AudioManager.OnAudioFocusChangeListener() {
                    public void onAudioFocusChange(final int i) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                if (b.this.getVideoView() != null && i <= 0) {
                                    b.this.getVideoView().a(false);
                                }
                            }
                        });
                    }
                });
            }
            ((AudioManager) b.this.getContext().getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).requestAudioFocus((AudioManager.OnAudioFocusChangeListener) b.this.a.get(), 3, 1);
        }
    };

    public b(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a((T[]) new f[]{this.d, this.b, this.c});
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b((T[]) new f[]{this.c, this.b, this.d});
        }
        super.b();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        ((AudioManager) getContext().getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO)).abandonAudioFocus(this.a == null ? null : (AudioManager.OnAudioFocusChangeListener) this.a.get());
        super.onDetachedFromWindow();
    }
}
