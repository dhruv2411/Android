package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.view.f.b.z;

public class ad extends BroadcastReceiver {
    private String a;
    private ac b;
    private ab c;

    public ad(String str, ab abVar, ac acVar) {
        this.c = abVar;
        this.b = acVar;
        this.a = str;
    }

    public IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(z.REWARDED_VIDEO_COMPLETE.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_ERROR.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_AD_CLICK.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_IMPRESSION.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_CLOSED.a(this.a));
        intentFilter.addAction(z.REWARD_SERVER_SUCCESS.a(this.a));
        intentFilter.addAction(z.REWARD_SERVER_FAILED.a(this.a));
        intentFilter.addAction(z.REWARDED_VIDEO_ACTIVITY_DESTROYED.a(this.a));
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (z.REWARDED_VIDEO_COMPLETE.a(this.a).equals(action)) {
            this.b.d(this.c);
        } else if (z.REWARDED_VIDEO_ERROR.a(this.a).equals(action)) {
            this.b.a(this.c, AdError.INTERNAL_ERROR);
        } else if (z.REWARDED_VIDEO_AD_CLICK.a(this.a).equals(action)) {
            this.b.b(this.c);
        } else if (z.REWARDED_VIDEO_IMPRESSION.a(this.a).equals(action)) {
            this.b.c(this.c);
        } else if (z.REWARDED_VIDEO_CLOSED.a(this.a).equals(action)) {
            this.b.a();
        } else if (z.REWARD_SERVER_FAILED.a(this.a).equals(action)) {
            this.b.e(this.c);
        } else if (z.REWARD_SERVER_SUCCESS.a(this.a).equals(action)) {
            this.b.f(this.c);
        } else if (z.REWARDED_VIDEO_ACTIVITY_DESTROYED.a(this.a).equals(action)) {
            this.b.b();
        }
    }
}
