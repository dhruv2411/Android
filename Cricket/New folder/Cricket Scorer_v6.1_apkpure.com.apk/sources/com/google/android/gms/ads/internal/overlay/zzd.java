package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.TextureView;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzds;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import util.IabHelper;

@zzme
@TargetApi(14)
public class zzd extends zzj implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final Map<Integer, String> zzMK = new HashMap();
    private final zzz zzML;
    private final boolean zzMM;
    private int zzMN = 0;
    private int zzMO = 0;
    private MediaPlayer zzMP;
    private Uri zzMQ;
    private int zzMR;
    private int zzMS;
    private int zzMT;
    private int zzMU;
    private int zzMV;
    private zzy zzMW;
    private boolean zzMX;
    private int zzMY;
    /* access modifiers changed from: private */
    public zzi zzMZ;

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            zzMK.put(Integer.valueOf(IabHelper.IABHELPER_SEND_INTENT_FAILED), "MEDIA_ERROR_IO");
            zzMK.put(Integer.valueOf(IabHelper.IABHELPER_MISSING_TOKEN), "MEDIA_ERROR_MALFORMED");
            zzMK.put(Integer.valueOf(IabHelper.IABHELPER_INVALID_CONSUMPTION), "MEDIA_ERROR_UNSUPPORTED");
            zzMK.put(-110, "MEDIA_ERROR_TIMED_OUT");
            zzMK.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzMK.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzMK.put(1, "MEDIA_ERROR_UNKNOWN");
        zzMK.put(1, "MEDIA_INFO_UNKNOWN");
        zzMK.put(700, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzMK.put(701, "MEDIA_INFO_BUFFERING_START");
        zzMK.put(702, "MEDIA_INFO_BUFFERING_END");
        zzMK.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        zzMK.put(801, "MEDIA_INFO_NOT_SEEKABLE");
        zzMK.put(802, "MEDIA_INFO_METADATA_UPDATE");
        if (Build.VERSION.SDK_INT >= 19) {
            zzMK.put(901, "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzMK.put(902, "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzd(Context context, boolean z, boolean z2, zzz zzz) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzML = zzz;
        this.zzMX = z;
        this.zzMM = z2;
        this.zzML.zza((zzj) this);
    }

    private void zzK(int i) {
        if (i == 3) {
            this.zzML.zzix();
            this.zzNS.zzix();
        } else if (this.zzMN == 3) {
            this.zzML.zziy();
            this.zzNS.zziy();
        }
        this.zzMN = i;
    }

    private void zzL(int i) {
        this.zzMO = i;
    }

    private void zza(float f) {
        if (this.zzMP != null) {
            try {
                this.zzMP.setVolume(f, f);
            } catch (IllegalStateException unused) {
            }
        } else {
            zzpk.zzbh("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034 A[LOOP:0: B:10:0x0034->B:15:0x0050, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzhA() {
        /*
            r8 = this;
            boolean r0 = r8.zzMM
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r8.zzhB()
            if (r0 == 0) goto L_0x005a
            android.media.MediaPlayer r0 = r8.zzMP
            int r0 = r0.getCurrentPosition()
            if (r0 <= 0) goto L_0x005a
            int r0 = r8.zzMO
            r1 = 3
            if (r0 == r1) goto L_0x005a
            java.lang.String r0 = "AdMediaPlayerView nudging MediaPlayer"
            com.google.android.gms.internal.zzpk.v(r0)
            r0 = 0
            r8.zza((float) r0)
            android.media.MediaPlayer r0 = r8.zzMP
            r0.start()
            android.media.MediaPlayer r0 = r8.zzMP
            int r0 = r0.getCurrentPosition()
            com.google.android.gms.common.util.zze r1 = com.google.android.gms.ads.internal.zzw.zzcS()
            long r1 = r1.currentTimeMillis()
        L_0x0034:
            boolean r3 = r8.zzhB()
            if (r3 == 0) goto L_0x0052
            android.media.MediaPlayer r3 = r8.zzMP
            int r3 = r3.getCurrentPosition()
            if (r3 != r0) goto L_0x0052
            com.google.android.gms.common.util.zze r3 = com.google.android.gms.ads.internal.zzw.zzcS()
            long r3 = r3.currentTimeMillis()
            long r5 = r3 - r1
            r3 = 250(0xfa, double:1.235E-321)
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x0034
        L_0x0052:
            android.media.MediaPlayer r0 = r8.zzMP
            r0.pause()
            r8.zzhC()
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.zzhA():void");
    }

    private boolean zzhB() {
        return (this.zzMP == null || this.zzMN == -1 || this.zzMN == 0 || this.zzMN == 1) ? false : true;
    }

    private void zzhz() {
        zzpk.v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzMQ != null && surfaceTexture != null) {
            zzy(false);
            try {
                this.zzMP = zzw.zzdd().zzik();
                this.zzMP.setOnBufferingUpdateListener(this);
                this.zzMP.setOnCompletionListener(this);
                this.zzMP.setOnErrorListener(this);
                this.zzMP.setOnInfoListener(this);
                this.zzMP.setOnPreparedListener(this);
                this.zzMP.setOnVideoSizeChangedListener(this);
                this.zzMT = 0;
                if (this.zzMX) {
                    this.zzMW = new zzy(getContext());
                    this.zzMW.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzMW.start();
                    SurfaceTexture zzim = this.zzMW.zzim();
                    if (zzim != null) {
                        surfaceTexture = zzim;
                    } else {
                        this.zzMW.zzil();
                        this.zzMW = null;
                    }
                }
                this.zzMP.setDataSource(getContext(), this.zzMQ);
                this.zzMP.setSurface(zzw.zzde().zza(surfaceTexture));
                this.zzMP.setAudioStreamType(3);
                this.zzMP.setScreenOnWhilePlaying(true);
                this.zzMP.prepareAsync();
                zzK(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzMQ);
                StringBuilder sb = new StringBuilder(36 + String.valueOf(valueOf).length());
                sb.append("Failed to initialize MediaPlayer at ");
                sb.append(valueOf);
                zzpk.zzc(sb.toString(), e);
                onError(this.zzMP, 1, 0);
            }
        }
    }

    private void zzy(boolean z) {
        zzpk.v("AdMediaPlayerView release");
        if (this.zzMW != null) {
            this.zzMW.zzil();
            this.zzMW = null;
        }
        if (this.zzMP != null) {
            this.zzMP.reset();
            this.zzMP.release();
            this.zzMP = null;
            zzK(0);
            if (z) {
                this.zzMO = 0;
                zzL(0);
            }
        }
    }

    public int getCurrentPosition() {
        if (zzhB()) {
            return this.zzMP.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (zzhB()) {
            return this.zzMP.getDuration();
        }
        return -1;
    }

    public int getVideoHeight() {
        if (this.zzMP != null) {
            return this.zzMP.getVideoHeight();
        }
        return 0;
    }

    public int getVideoWidth() {
        if (this.zzMP != null) {
            return this.zzMP.getVideoWidth();
        }
        return 0;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzMT = i;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        zzpk.v("AdMediaPlayerView completion");
        zzK(5);
        zzL(5);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzhW();
                }
            }
        });
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        final String str = zzMK.get(Integer.valueOf(i));
        final String str2 = zzMK.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(38 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer error: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzpk.zzbh(sb.toString());
        zzK(-1);
        zzL(-1);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzl(str, str2);
                }
            }
        });
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzMK.get(Integer.valueOf(i));
        String str2 = zzMK.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(37 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer info: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzpk.v(sb.toString());
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        if ((r5.zzMR * r7) > (r5.zzMS * r6)) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006a, code lost:
        if (r1 > r6) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0082, code lost:
        if (r1 > r6) goto L_0x0047;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = r5.zzMR
            int r0 = getDefaultSize(r0, r6)
            int r1 = r5.zzMS
            int r1 = getDefaultSize(r1, r7)
            int r2 = r5.zzMR
            if (r2 <= 0) goto L_0x0085
            int r2 = r5.zzMS
            if (r2 <= 0) goto L_0x0085
            com.google.android.gms.ads.internal.overlay.zzy r2 = r5.zzMW
            if (r2 != 0) goto L_0x0085
            int r0 = android.view.View.MeasureSpec.getMode(r6)
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 != r2) goto L_0x004f
            if (r1 != r2) goto L_0x004f
            int r0 = r5.zzMR
            int r0 = r0 * r7
            int r1 = r5.zzMS
            int r1 = r1 * r6
            if (r0 >= r1) goto L_0x003f
            int r6 = r5.zzMR
            int r6 = r6 * r7
            int r0 = r5.zzMS
            int r0 = r6 / r0
            r6 = r0
            goto L_0x0087
        L_0x003f:
            int r0 = r5.zzMR
            int r0 = r0 * r7
            int r1 = r5.zzMS
            int r1 = r1 * r6
            if (r0 <= r1) goto L_0x0087
        L_0x0047:
            int r7 = r5.zzMS
            int r7 = r7 * r6
            int r0 = r5.zzMR
            int r1 = r7 / r0
            goto L_0x0086
        L_0x004f:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r2) goto L_0x0060
            int r0 = r5.zzMS
            int r0 = r0 * r6
            int r2 = r5.zzMR
            int r0 = r0 / r2
            if (r1 != r3) goto L_0x005e
            if (r0 <= r7) goto L_0x005e
            goto L_0x0087
        L_0x005e:
            r7 = r0
            goto L_0x0087
        L_0x0060:
            if (r1 != r2) goto L_0x006f
            int r1 = r5.zzMR
            int r1 = r1 * r7
            int r2 = r5.zzMS
            int r1 = r1 / r2
            if (r0 != r3) goto L_0x006d
            if (r1 <= r6) goto L_0x006d
            goto L_0x0087
        L_0x006d:
            r6 = r1
            goto L_0x0087
        L_0x006f:
            int r2 = r5.zzMR
            int r4 = r5.zzMS
            if (r1 != r3) goto L_0x007e
            if (r4 <= r7) goto L_0x007e
            int r1 = r5.zzMR
            int r1 = r1 * r7
            int r2 = r5.zzMS
            int r1 = r1 / r2
            goto L_0x0080
        L_0x007e:
            r1 = r2
            r7 = r4
        L_0x0080:
            if (r0 != r3) goto L_0x006d
            if (r1 <= r6) goto L_0x006d
            goto L_0x0047
        L_0x0085:
            r6 = r0
        L_0x0086:
            r7 = r1
        L_0x0087:
            r5.setMeasuredDimension(r6, r7)
            com.google.android.gms.ads.internal.overlay.zzy r0 = r5.zzMW
            if (r0 == 0) goto L_0x0093
            com.google.android.gms.ads.internal.overlay.zzy r0 = r5.zzMW
            r0.zzj(r6, r7)
        L_0x0093:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 != r1) goto L_0x00b0
            int r0 = r5.zzMU
            if (r0 <= 0) goto L_0x00a1
            int r0 = r5.zzMU
            if (r0 != r6) goto L_0x00a9
        L_0x00a1:
            int r0 = r5.zzMV
            if (r0 <= 0) goto L_0x00ac
            int r0 = r5.zzMV
            if (r0 == r7) goto L_0x00ac
        L_0x00a9:
            r5.zzhA()
        L_0x00ac:
            r5.zzMU = r6
            r5.zzMV = r7
        L_0x00b0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzd.onMeasure(int, int):void");
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        zzpk.v("AdMediaPlayerView prepared");
        zzK(2);
        this.zzML.zzhU();
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzhU();
                }
            }
        });
        this.zzMR = mediaPlayer.getVideoWidth();
        this.zzMS = mediaPlayer.getVideoHeight();
        if (this.zzMY != 0) {
            seekTo(this.zzMY);
        }
        zzhA();
        int i = this.zzMR;
        int i2 = this.zzMS;
        StringBuilder sb = new StringBuilder(62);
        sb.append("AdMediaPlayerView stream dimensions: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        zzpk.zzbg(sb.toString());
        if (this.zzMO == 3) {
            play();
        }
        zzhC();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzpk.v("AdMediaPlayerView surface created");
        zzhz();
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzhT();
                }
            }
        });
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzpk.v("AdMediaPlayerView surface destroyed");
        if (this.zzMP != null && this.zzMY == 0) {
            this.zzMY = this.zzMP.getCurrentPosition();
        }
        if (this.zzMW != null) {
            this.zzMW.zzil();
        }
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.onPaused();
                    zzd.this.zzMZ.zzhX();
                }
            }
        });
        zzy(true);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, final int i, final int i2) {
        zzpk.v("AdMediaPlayerView surface changed");
        boolean z = false;
        boolean z2 = this.zzMO == 3;
        if (this.zzMR == i && this.zzMS == i2) {
            z = true;
        }
        if (this.zzMP != null && z2 && z) {
            if (this.zzMY != 0) {
                seekTo(this.zzMY);
            }
            play();
        }
        if (this.zzMW != null) {
            this.zzMW.zzj(i, i2);
        }
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzg(i, i2);
                }
            }
        });
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzML.zzb(this);
        this.zzNR.zza(surfaceTexture, this.zzMZ);
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdMediaPlayerView size changed: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        zzpk.v(sb.toString());
        this.zzMR = mediaPlayer.getVideoWidth();
        this.zzMS = mediaPlayer.getVideoHeight();
        if (this.zzMR != 0 && this.zzMS != 0) {
            requestLayout();
        }
    }

    public void pause() {
        zzpk.v("AdMediaPlayerView pause");
        if (zzhB() && this.zzMP.isPlaying()) {
            this.zzMP.pause();
            zzK(4);
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    if (zzd.this.zzMZ != null) {
                        zzd.this.zzMZ.onPaused();
                    }
                }
            });
        }
        zzL(4);
    }

    public void play() {
        zzpk.v("AdMediaPlayerView play");
        if (zzhB()) {
            this.zzMP.start();
            zzK(3);
            this.zzNR.zzhV();
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    if (zzd.this.zzMZ != null) {
                        zzd.this.zzMZ.zzhV();
                    }
                }
            });
        }
        zzL(3);
    }

    public void seekTo(int i) {
        StringBuilder sb = new StringBuilder(34);
        sb.append("AdMediaPlayerView seek ");
        sb.append(i);
        zzpk.v(sb.toString());
        if (zzhB()) {
            this.zzMP.seekTo(i);
            i = 0;
        }
        this.zzMY = i;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        zzds zze = zzds.zze(uri);
        if (zze != null) {
            uri = Uri.parse(zze.url);
        }
        this.zzMQ = uri;
        this.zzMY = 0;
        zzhz();
        requestLayout();
        invalidate();
    }

    public void stop() {
        zzpk.v("AdMediaPlayerView stop");
        if (this.zzMP != null) {
            this.zzMP.stop();
            this.zzMP.release();
            this.zzMP = null;
            zzK(0);
            zzL(0);
        }
        this.zzML.onStop();
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getName());
        String valueOf2 = String.valueOf(Integer.toHexString(hashCode()));
        StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append("@");
        sb.append(valueOf2);
        return sb.toString();
    }

    public void zza(float f, float f2) {
        if (this.zzMW != null) {
            this.zzMW.zzb(f, f2);
        }
    }

    public void zza(zzi zzi) {
        this.zzMZ = zzi;
    }

    public void zzhC() {
        zza(this.zzNS.zziA());
    }

    public String zzhy() {
        String valueOf = String.valueOf(this.zzMX ? " spherical" : "");
        return valueOf.length() != 0 ? "MediaPlayer".concat(valueOf) : new String("MediaPlayer");
    }
}
