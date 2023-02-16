package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.widget.TextView;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.view.f.b.n;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.util.concurrent.TimeUnit;

public class c extends com.facebook.ads.internal.view.f.a.c {
    /* access modifiers changed from: private */
    public final TextView a;
    private final String b;
    private final f<n> c = new f<n>() {
        public Class<n> a() {
            return n.class;
        }

        public void a(n nVar) {
            if (c.this.getVideoView() != null) {
                c.this.a.setText(c.this.a((long) (c.this.getVideoView().getDuration() - c.this.getVideoView().getCurrentPositionInMillis())));
            }
        }
    };

    public c(Context context, String str) {
        super(context);
        this.a = new TextView(context);
        this.b = str;
        addView(this.a);
    }

    /* access modifiers changed from: private */
    public String a(long j) {
        if (j <= 0) {
            return "00:00";
        }
        long minutes = TimeUnit.MILLISECONDS.toMinutes(j);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(j % ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
        if (this.b.isEmpty()) {
            return String.format("%02d:%02d", new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)});
        }
        return this.b.replace("{{REMAINING_TIME}}", String.format("%02d:%02d", new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)}));
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.c);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b(this.c);
        }
        super.b();
    }

    public void setCountdownTextColor(int i) {
        this.a.setTextColor(i);
    }
}
