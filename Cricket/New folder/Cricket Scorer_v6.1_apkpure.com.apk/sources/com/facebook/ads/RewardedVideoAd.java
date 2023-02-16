package com.facebook.ads;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.a;
import com.facebook.ads.internal.adapters.ab;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.f;

public class RewardedVideoAd implements Ad {
    public static final int UNSET_VIDEO_DURATION = -1;
    private static final String a = "RewardedVideoAd";
    private final Context b;
    private final String c;
    private DisplayAdController d;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public RewardedVideoAdListener f;
    /* access modifiers changed from: private */
    public RewardData g;
    /* access modifiers changed from: private */
    public int h = -1;

    public RewardedVideoAd(Context context, String str) {
        this.b = context;
        this.c = str;
    }

    private void a(String str, boolean z) {
        try {
            b(str, z);
        } catch (Exception e2) {
            Log.e(a, "Error loading rewarded video ad", e2);
            if (this.f != null) {
                this.f.onError(this, AdError.INTERNAL_ERROR);
            }
        }
    }

    private final void a(boolean z) {
        if (this.d != null) {
            this.d.b(z);
            this.d = null;
        }
    }

    private void b(String str, boolean z) {
        a(false);
        this.e = false;
        this.d = new DisplayAdController(this.b, this.c, f.REWARDED_VIDEO, AdPlacementType.REWARDED_VIDEO, e.INTERSTITIAL, d.ADS, 1, true);
        this.d.a(z);
        this.d.a((a) new a() {
            public void a() {
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onAdClicked(RewardedVideoAd.this);
                }
            }

            public void a(AdAdapter adAdapter) {
                ab abVar = (ab) adAdapter;
                if (RewardedVideoAd.this.g != null) {
                    abVar.a(RewardedVideoAd.this.g);
                }
                int unused = RewardedVideoAd.this.h = abVar.a();
                boolean unused2 = RewardedVideoAd.this.e = true;
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onAdLoaded(RewardedVideoAd.this);
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onError(RewardedVideoAd.this, AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void b() {
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onLoggingImpression(RewardedVideoAd.this);
                }
            }

            public void g() {
                RewardedVideoAd.this.f.onRewardedVideoCompleted();
            }

            public void h() {
                if (RewardedVideoAd.this.f != null) {
                    RewardedVideoAd.this.f.onRewardedVideoClosed();
                }
            }

            public void i() {
                if (RewardedVideoAd.this.f instanceof S2SRewardedVideoAdListener) {
                    ((S2SRewardedVideoAdListener) RewardedVideoAd.this.f).onRewardServerFailed();
                }
            }

            public void j() {
                if (RewardedVideoAd.this.f instanceof S2SRewardedVideoAdListener) {
                    ((S2SRewardedVideoAdListener) RewardedVideoAd.this.f).onRewardServerSuccess();
                }
            }

            public void k() {
                if (RewardedVideoAd.this.f instanceof RewardedVideoAdExtendedListener) {
                    ((RewardedVideoAdExtendedListener) RewardedVideoAd.this.f).onRewardedVideoActivityDestroyed();
                }
            }
        });
        this.d.a(str);
    }

    public void destroy() {
        a(true);
    }

    public String getPlacementId() {
        return this.c;
    }

    public int getVideoDuration() {
        return this.h;
    }

    public boolean isAdInvalidated() {
        return this.d == null || this.d.d();
    }

    public boolean isAdLoaded() {
        return this.e;
    }

    public void loadAd() {
        a((String) null, false);
    }

    public void loadAd(boolean z) {
        a((String) null, z);
    }

    public void loadAdFromBid(String str) {
        a(str, false);
    }

    public void loadAdFromBid(String str, boolean z) {
        a(str, z);
    }

    public void setAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.f = rewardedVideoAdListener;
    }

    public void setRewardData(RewardData rewardData) {
        this.g = rewardData;
        if (this.e) {
            this.d.a(rewardData);
        }
    }

    public boolean show() {
        return show(-1);
    }

    public boolean show(int i) {
        if (!this.e) {
            if (this.f != null) {
                this.f.onError(this, AdError.INTERNAL_ERROR);
            }
            return false;
        }
        this.d.a(i);
        this.d.b();
        this.e = false;
        return true;
    }
}
