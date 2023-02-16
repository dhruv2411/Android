package com.facebook.ads.internal.view.f.d;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.MediaController;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.f.a.a;

@TargetApi(14)
public class b extends TextureView implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener, c {
    private static final String t = "b";
    private Uri a;
    private e b;
    private Surface c;
    /* access modifiers changed from: private */
    @Nullable
    public MediaPlayer d;
    /* access modifiers changed from: private */
    public MediaController e;
    private d f = d.IDLE;
    private d g = d.IDLE;
    private d h = d.IDLE;
    private boolean i = false;
    private View j;
    private int k = 0;
    private long l;
    private int m = 0;
    private int n = 0;
    private float o = 1.0f;
    private boolean p = false;
    private int q = 3;
    private boolean r = false;
    private boolean s = false;
    private int u = 0;
    /* access modifiers changed from: private */
    public boolean v = false;
    private a w = a.NOT_STARTED;
    private final MediaController.MediaPlayerControl x = new MediaController.MediaPlayerControl() {
        public boolean canPause() {
            return true;
        }

        public boolean canSeekBackward() {
            return true;
        }

        public boolean canSeekForward() {
            return true;
        }

        public int getAudioSessionId() {
            if (b.this.d != null) {
                return b.this.d.getAudioSessionId();
            }
            return 0;
        }

        public int getBufferPercentage() {
            return 0;
        }

        public int getCurrentPosition() {
            return b.this.getCurrentPosition();
        }

        public int getDuration() {
            return b.this.getDuration();
        }

        public boolean isPlaying() {
            return b.this.d != null && b.this.d.isPlaying();
        }

        public void pause() {
            b.this.a(true);
        }

        public void seekTo(int i) {
            b.this.a(i);
        }

        public void start() {
            b.this.a(a.USER_STARTED);
        }
    };

    public b(Context context) {
        super(context);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public b(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @TargetApi(21)
    public b(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    private boolean a(@Nullable Surface surface) {
        if (this.d == null) {
            return false;
        }
        try {
            this.d.setSurface(surface);
            return true;
        } catch (IllegalStateException e2) {
            com.facebook.ads.internal.q.d.a.a(getContext(), DBHelper.TABLE_PLAYERS, com.facebook.ads.internal.q.d.b.s, (Exception) e2);
            Log.d(t, "The MediaPlayer failed", e2);
            return false;
        }
    }

    private boolean f() {
        return this.f == d.PREPARED || this.f == d.STARTED || this.f == d.PAUSED || this.f == d.PLAYBACK_COMPLETED;
    }

    private boolean g() {
        if (this.d == null) {
            return false;
        }
        try {
            this.d.reset();
            return true;
        } catch (IllegalStateException e2) {
            com.facebook.ads.internal.q.d.a.a(getContext(), DBHelper.TABLE_PLAYERS, com.facebook.ads.internal.q.d.b.t, (Exception) e2);
            Log.d(t, "The MediaPlayer failed", e2);
            return false;
        }
    }

    private boolean h() {
        return (this.f == d.PREPARING || this.f == d.PREPARED) ? false : true;
    }

    private boolean i() {
        return (this.f == d.PREPARING || this.f == d.PREPARED) ? false : true;
    }

    private void setVideoState(d dVar) {
        if (dVar != this.f) {
            this.f = dVar;
            if (this.b != null) {
                this.b.a(dVar);
            }
        }
    }

    public void a() {
        if (!this.r) {
            a(false);
        }
    }

    public void a(int i2) {
        if (this.d == null || !f()) {
            this.k = i2;
        } else if (i2 < getDuration() && i2 > 0) {
            this.u = getCurrentPosition();
            this.k = i2;
            this.d.seekTo(i2);
        }
    }

    public void a(a aVar) {
        this.g = d.STARTED;
        this.w = aVar;
        if (this.f == d.STARTED || this.f == d.PREPARED || this.f == d.IDLE || this.f == d.PAUSED || this.f == d.PLAYBACK_COMPLETED) {
            if (this.d == null) {
                setup(this.a);
            } else {
                if (this.k > 0) {
                    this.d.seekTo(this.k);
                }
                this.d.start();
                if (this.f != d.PREPARED || this.s) {
                    setVideoState(d.STARTED);
                }
            }
        }
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }

    public void a(boolean z) {
        d dVar;
        this.g = d.PAUSED;
        if (this.d == null) {
            dVar = d.IDLE;
        } else if (i()) {
            if (z) {
                this.h = d.PAUSED;
                this.i = true;
            }
            this.d.pause();
            if (this.f != d.PLAYBACK_COMPLETED) {
                dVar = d.PAUSED;
            } else {
                return;
            }
        } else {
            return;
        }
        setVideoState(dVar);
    }

    public void b() {
        setVideoState(d.PLAYBACK_COMPLETED);
        c();
        this.k = 0;
    }

    public void c() {
        this.g = d.IDLE;
        if (this.d != null) {
            int currentPosition = this.d.getCurrentPosition();
            if (currentPosition > 0) {
                this.k = currentPosition;
            }
            this.d.stop();
            g();
            this.d.release();
            this.d = null;
            if (this.e != null) {
                this.e.hide();
                this.e.setEnabled(false);
            }
        }
        setVideoState(d.IDLE);
    }

    @SuppressLint({"NewApi"})
    public boolean d() {
        if (this.d == null || Build.VERSION.SDK_INT < 16) {
            return false;
        }
        try {
            for (MediaPlayer.TrackInfo trackType : this.d.getTrackInfo()) {
                if (trackType.getTrackType() == 2) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e2) {
            Log.e(t, "Couldn't retrieve video information", e2);
            return true;
        }
    }

    public void e() {
        if (this.d != null) {
            a((Surface) null);
            this.d.setOnBufferingUpdateListener((MediaPlayer.OnBufferingUpdateListener) null);
            this.d.setOnCompletionListener((MediaPlayer.OnCompletionListener) null);
            this.d.setOnErrorListener((MediaPlayer.OnErrorListener) null);
            this.d.setOnInfoListener((MediaPlayer.OnInfoListener) null);
            this.d.setOnPreparedListener((MediaPlayer.OnPreparedListener) null);
            this.d.setOnVideoSizeChangedListener((MediaPlayer.OnVideoSizeChangedListener) null);
            this.d.setOnSeekCompleteListener((MediaPlayer.OnSeekCompleteListener) null);
            g();
            this.d = null;
            setVideoState(d.IDLE);
        }
    }

    public int getCurrentPosition() {
        if (this.d == null || !f()) {
            return 0;
        }
        return this.d.getCurrentPosition();
    }

    public int getDuration() {
        if (this.d == null || !f()) {
            return 0;
        }
        return this.d.getDuration();
    }

    public long getInitialBufferTime() {
        return this.l;
    }

    public a getStartReason() {
        return this.w;
    }

    public d getState() {
        return this.f;
    }

    public d getTargetState() {
        return this.g;
    }

    public int getVideoHeight() {
        return this.n;
    }

    public int getVideoWidth() {
        return this.m;
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.o;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.d != null) {
            this.d.pause();
        }
        setVideoState(d.PLAYBACK_COMPLETED);
        a(0);
        this.k = 0;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        if (this.q <= 0 || getState() != d.STARTED) {
            setVideoState(d.ERROR);
            c();
            return true;
        }
        this.q--;
        c();
        a(this.w);
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
        d dVar;
        if (i2 != 3) {
            switch (i2) {
                case 701:
                    dVar = d.BUFFERING;
                    break;
                case 702:
                    if (h()) {
                        dVar = d.STARTED;
                        break;
                    } else {
                        return false;
                    }
                default:
                    return false;
            }
            setVideoState(dVar);
            return false;
        }
        this.s = true;
        if (this.g == d.STARTED) {
            setVideoState(d.STARTED);
        }
        return true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        setVideoState(d.PREPARED);
        if (this.p && !this.v) {
            this.e = new MediaController(getContext());
            this.e.setAnchorView(this.j == null ? this : this.j);
            this.e.setMediaPlayer(this.x);
            this.e.setEnabled(true);
        }
        setRequestedVolume(this.o);
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        if (this.k > 0) {
            if (this.k >= this.d.getDuration()) {
                this.k = 0;
            }
            this.d.seekTo(this.k);
            this.k = 0;
        }
        if (this.g == d.STARTED) {
            a(this.w);
        }
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        if (this.b != null) {
            this.b.a(this.u, this.k);
            this.k = 0;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        if (this.c == null) {
            this.c = new Surface(surfaceTexture);
        }
        if (!a(this.c)) {
            setVideoState(d.ERROR);
            e();
            return;
        }
        this.i = false;
        if (this.f == d.PAUSED && this.h != d.PAUSED) {
            a(this.w);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        a((Surface) null);
        if (this.c != null) {
            this.c.release();
            this.c = null;
        }
        if (!this.i) {
            this.h = this.p ? d.STARTED : this.f;
            this.i = true;
        }
        if (this.f != d.PAUSED) {
            a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        if (this.m != 0 && this.n != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.d != null) {
            if (this.e != null && this.e.isShowing()) {
                return;
            }
            if (!z) {
                if (!this.i) {
                    this.h = this.p ? d.STARTED : this.f;
                    this.i = true;
                }
                if (this.f != d.PAUSED) {
                    a();
                    return;
                }
                return;
            }
            this.i = false;
            if (this.f == d.PAUSED && this.h != d.PAUSED) {
                a(this.w);
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(t, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.r = z;
    }

    public void setControlsAnchorView(View view) {
        this.j = view;
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!b.this.v && b.this.e != null && motionEvent.getAction() == 1) {
                    if (b.this.e.isShowing()) {
                        b.this.e.hide();
                        return true;
                    }
                    b.this.e.show();
                }
                return true;
            }
        });
    }

    public void setForeground(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 24) {
            super.setForeground(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(t, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    public void setFullScreen(boolean z) {
        this.p = z;
        if (this.p && !this.v) {
            setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!b.this.v && b.this.e != null && motionEvent.getAction() == 1) {
                        if (b.this.e.isShowing()) {
                            b.this.e.hide();
                            return true;
                        }
                        b.this.e.show();
                    }
                    return true;
                }
            });
        }
    }

    public void setRequestedVolume(float f2) {
        this.o = f2;
        if (this.d != null && this.f != d.PREPARING && this.f != d.IDLE) {
            this.d.setVolume(f2, f2);
        }
    }

    public void setVideoMPD(@Nullable String str) {
    }

    public void setVideoStateChangeListener(e eVar) {
        this.b = eVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098 A[SYNTHETIC, Splitter:B:27:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b3 A[SYNTHETIC, Splitter:B:33:0x00b3] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(android.net.Uri r11) {
        /*
            r10 = this;
            r0 = 0
            r10.s = r0
            r10.a = r11
            android.media.MediaPlayer r1 = r10.d
            r2 = 0
            if (r1 == 0) goto L_0x0018
            r10.g()
            r10.a((android.view.Surface) r2)
            android.media.MediaPlayer r1 = r10.d
            com.facebook.ads.internal.view.f.d.d r3 = com.facebook.ads.internal.view.f.d.d.IDLE
            r10.setVideoState(r3)
            goto L_0x001d
        L_0x0018:
            android.media.MediaPlayer r1 = new android.media.MediaPlayer
            r1.<init>()
        L_0x001d:
            java.lang.String r3 = r11.getScheme()     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r4 = "asset"
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x00f9 }
            if (r3 == 0) goto L_0x00cf
            android.content.Context r3 = r10.getContext()     // Catch:{ IOException | SecurityException -> 0x007a }
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch:{ IOException | SecurityException -> 0x007a }
            java.lang.String r11 = r11.getPath()     // Catch:{ IOException | SecurityException -> 0x007a }
            r4 = 1
            java.lang.String r11 = r11.substring(r4)     // Catch:{ IOException | SecurityException -> 0x007a }
            android.content.res.AssetFileDescriptor r11 = r3.openFd(r11)     // Catch:{ IOException | SecurityException -> 0x007a }
            long r5 = r11.getStartOffset()     // Catch:{ IOException | SecurityException -> 0x0073, all -> 0x006e }
            long r7 = r11.getLength()     // Catch:{ IOException | SecurityException -> 0x0073, all -> 0x006e }
            java.io.FileDescriptor r4 = r11.getFileDescriptor()     // Catch:{ IOException | SecurityException -> 0x0073, all -> 0x006e }
            r3 = r1
            r3.setDataSource(r4, r5, r7)     // Catch:{ IOException | SecurityException -> 0x0073, all -> 0x006e }
            if (r11 == 0) goto L_0x00d6
            r11.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x00d6
        L_0x0055:
            r11 = move-exception
            java.lang.String r2 = t     // Catch:{ Exception -> 0x00f9 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f9 }
            r3.<init>()     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r4 = "Unable to close"
            r3.append(r4)     // Catch:{ Exception -> 0x00f9 }
            r3.append(r11)     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r11 = r3.toString()     // Catch:{ Exception -> 0x00f9 }
        L_0x0069:
            android.util.Log.w(r2, r11)     // Catch:{ Exception -> 0x00f9 }
            goto L_0x00d6
        L_0x006e:
            r2 = move-exception
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x00b1
        L_0x0073:
            r2 = move-exception
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x007b
        L_0x0078:
            r11 = move-exception
            goto L_0x00b1
        L_0x007a:
            r11 = move-exception
        L_0x007b:
            java.lang.String r3 = t     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
            r4.<init>()     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = "Failed to open assets "
            r4.append(r5)     // Catch:{ all -> 0x0078 }
            r4.append(r11)     // Catch:{ all -> 0x0078 }
            java.lang.String r11 = r4.toString()     // Catch:{ all -> 0x0078 }
            android.util.Log.w(r3, r11)     // Catch:{ all -> 0x0078 }
            com.facebook.ads.internal.view.f.d.d r11 = com.facebook.ads.internal.view.f.d.d.ERROR     // Catch:{ all -> 0x0078 }
            r10.setVideoState(r11)     // Catch:{ all -> 0x0078 }
            if (r2 == 0) goto L_0x00d6
            r2.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00d6
        L_0x009c:
            r11 = move-exception
            java.lang.String r2 = t     // Catch:{ Exception -> 0x00f9 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f9 }
            r3.<init>()     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r4 = "Unable to close"
            r3.append(r4)     // Catch:{ Exception -> 0x00f9 }
            r3.append(r11)     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r11 = r3.toString()     // Catch:{ Exception -> 0x00f9 }
            goto L_0x0069
        L_0x00b1:
            if (r2 == 0) goto L_0x00ce
            r2.close()     // Catch:{ IOException -> 0x00b7 }
            goto L_0x00ce
        L_0x00b7:
            r2 = move-exception
            java.lang.String r3 = t     // Catch:{ Exception -> 0x00f9 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f9 }
            r4.<init>()     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r5 = "Unable to close"
            r4.append(r5)     // Catch:{ Exception -> 0x00f9 }
            r4.append(r2)     // Catch:{ Exception -> 0x00f9 }
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x00f9 }
            android.util.Log.w(r3, r2)     // Catch:{ Exception -> 0x00f9 }
        L_0x00ce:
            throw r11     // Catch:{ Exception -> 0x00f9 }
        L_0x00cf:
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x00f9 }
            r1.setDataSource(r11)     // Catch:{ Exception -> 0x00f9 }
        L_0x00d6:
            r1.setLooping(r0)     // Catch:{ Exception -> 0x00f9 }
            r1.setOnBufferingUpdateListener(r10)     // Catch:{ Exception -> 0x00f9 }
            r1.setOnCompletionListener(r10)     // Catch:{ Exception -> 0x00f9 }
            r1.setOnErrorListener(r10)     // Catch:{ Exception -> 0x00f9 }
            r1.setOnInfoListener(r10)     // Catch:{ Exception -> 0x00f9 }
            r1.setOnPreparedListener(r10)     // Catch:{ Exception -> 0x00f9 }
            r1.setOnVideoSizeChangedListener(r10)     // Catch:{ Exception -> 0x00f9 }
            r1.setOnSeekCompleteListener(r10)     // Catch:{ Exception -> 0x00f9 }
            r1.prepareAsync()     // Catch:{ Exception -> 0x00f9 }
            r10.d = r1     // Catch:{ Exception -> 0x00f9 }
            com.facebook.ads.internal.view.f.d.d r11 = com.facebook.ads.internal.view.f.d.d.PREPARING     // Catch:{ Exception -> 0x00f9 }
            r10.setVideoState(r11)     // Catch:{ Exception -> 0x00f9 }
            goto L_0x0118
        L_0x00f9:
            r11 = move-exception
            com.facebook.ads.internal.view.f.d.d r2 = com.facebook.ads.internal.view.f.d.d.ERROR
            r10.setVideoState(r2)
            r1.release()
            java.lang.String r1 = t
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Cannot prepare media player with SurfaceTexture: "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            android.util.Log.e(r1, r11)
        L_0x0118:
            r10.setSurfaceTextureListener(r10)
            boolean r11 = r10.isAvailable()
            if (r11 == 0) goto L_0x0128
            android.graphics.SurfaceTexture r11 = r10.getSurfaceTexture()
            r10.onSurfaceTextureAvailable(r11, r0, r0)
        L_0x0128:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.f.d.b.setup(android.net.Uri):void");
    }
}
